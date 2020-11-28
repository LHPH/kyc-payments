package com.kyc.payments.security;

import com.kyc.payments.entity.KycUser;
import com.kyc.payments.services.KycAuthService;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

@Setter
@Getter
public class KycAuthProvider implements AuthenticationProvider {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private KycAuthService kycAuthService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String pass = authentication.getCredentials().toString();

        KycUser user = kycAuthService.retrieveCustomerByUser(username);

        if(user!=null){

            if(user.isActive() && !user.isLocked()){

                boolean ind = passwordEncoder.matches(pass,user.getSecret());
                if(ind){
                    LOGGER.info("El usuario {} se autentico correctamente",username);
                    return new UsernamePasswordAuthenticationToken(user.getCustomer(),pass,new ArrayList<>());
                }
            }
            else if(!user.isActive()){
                LOGGER.warn("El usuario {} no esta activo",username);
                throw new DisabledException("Authentication failed for " + username);
            }
            else{
                LOGGER.warn("El usuario {} esta bloqueado",username);
                throw new LockedException("Authentication failed for " + username);
            }
        }
        LOGGER.warn("El usuario {} no se autentico correctamente",username);
        throw new BadCredentialsException("Authentication failed for " + username);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
