package com.kyc.payments.security;

import com.kyc.payments.services.KycAuthService;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
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

        String passEncoded = passwordEncoder.encode(pass);
        //$2a$10$KJsjXh68CUFNTLDpwEQzheL.RQIlF04QuWqonECSnTUyQmrNY8wn.
        LOGGER.info("The pass encoded is {}",passEncoded);

        User user = kycAuthService.retrieveCustomerByUser(username);
        boolean ind = passwordEncoder.matches(pass,user.getPassword());

        if(ind){
            return new UsernamePasswordAuthenticationToken(username,pass,new ArrayList<>());
        }
        throw new BadCredentialsException("Authentication failed for " + username);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
