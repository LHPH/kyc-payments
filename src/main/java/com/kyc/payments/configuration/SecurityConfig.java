package com.kyc.payments.configuration;

import com.kyc.core.exception.handlers.KycGenericSoapExceptionHandler;
import com.kyc.core.security.jwt.KycUserTokenSessionService;
import com.kyc.core.services.DefaultKycUserTokenSessionService;
import com.kyc.core.services.KycUserDetailsService;
import com.kyc.core.services.mock.MockKycUserTokenSessionService;
import com.kyc.core.soap.security.CompositeSoapSecurityValidator;
import com.kyc.core.soap.security.CustomWss4jSecurityInterceptor;
import com.kyc.core.soap.security.SpringJwtBinaryTokenValidator;
import com.kyc.core.soap.security.SpringSecurityJwtTokenValidationCallbackHandler;
import com.kyc.core.soap.security.SpringUsernameTokenValidator;
import org.apache.wss4j.common.ConfigurationConstants;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.engine.WSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.ws.config.annotation.WsConfigurer;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SpringSecurityPasswordValidationCallbackHandler;

import javax.security.auth.callback.CallbackHandler;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WsConfigurer {

    @Autowired
    private KycGenericSoapExceptionHandler kycGenericSoapExceptionHandler;

    @Value("${kyc-config.mock.resource-server.enabled:false}")
    private boolean mockResourceServerEnabled;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Spring Security should completely ignore URLs starting with /resources/
        return (web) -> web.ignoring()
                .requestMatchers("/resources/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/kyc/**","/actuator/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .csrf(CsrfConfigurer::disable)
                .httpBasic(withDefaults())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KycUserDetailsService userDetailsService(){
        return new KycUserDetailsService();
    }

    @Bean
    public KycUserTokenSessionService kycUserTokenSessionService(){
        return mockResourceServerEnabled ? new MockKycUserTokenSessionService() : new DefaultKycUserTokenSessionService();
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){

        SpringUsernameTokenValidator springUsernameTokenValidator = springUsernameTokenValidator(encoder());
        SpringJwtBinaryTokenValidator springJwtBinaryTokenValidator = springJwtBinaryTokenValidator(kycUserTokenSessionService());
        CompositeSoapSecurityValidator compositeSoapSecurityValidator = compositeSoapSecurityValidator(springJwtBinaryTokenValidator,springUsernameTokenValidator);

        Wss4jSecurityInterceptor securityInterceptor = new CustomWss4jSecurityInterceptor();
        securityInterceptor.setValidationActions(ConfigurationConstants.USERNAME_TOKEN+" "+ConfigurationConstants.CUSTOM_TOKEN);
        securityInterceptor.setStrictActionChecking(false);
        securityInterceptor.setValidationCallbackHandlers(new CallbackHandler[]{
            springSecurityPasswordValidationCallbackHandler(),
            springJwtTokenValidationCallbackHandler()
        });
        securityInterceptor.setExceptionResolver(kycGenericSoapExceptionHandler);

        WSSConfig wssConfig = WSSConfig.getNewInstance();
        wssConfig.setValidator(WSConstants.USERNAME_TOKEN,compositeSoapSecurityValidator);
        wssConfig.setValidator(WSConstants.BINARY_TOKEN,compositeSoapSecurityValidator);

        securityInterceptor.setWssConfig(wssConfig);
        return securityInterceptor;
    }

    @Bean
    public SpringUsernameTokenValidator springUsernameTokenValidator(PasswordEncoder encoder){
        return new SpringUsernameTokenValidator(encoder);
    }

    @Bean
    public SpringJwtBinaryTokenValidator springJwtBinaryTokenValidator(KycUserTokenSessionService kycUserTokenSessionService){
        return new SpringJwtBinaryTokenValidator(kycUserTokenSessionService);
    }

    @Bean
    public CompositeSoapSecurityValidator compositeSoapSecurityValidator(
            SpringJwtBinaryTokenValidator springJwtBinaryTokenValidator,
            SpringUsernameTokenValidator springUsernameTokenValidator
    ){
        return new CompositeSoapSecurityValidator(springJwtBinaryTokenValidator,springUsernameTokenValidator);
    }

    @Bean
    public SpringSecurityPasswordValidationCallbackHandler springSecurityPasswordValidationCallbackHandler() {

        SpringSecurityPasswordValidationCallbackHandler handler = new SpringSecurityPasswordValidationCallbackHandler();
        handler.setUserDetailsService(userDetailsService());
        return handler;
    }

    @Bean
    public SpringSecurityJwtTokenValidationCallbackHandler springJwtTokenValidationCallbackHandler(){

        return new SpringSecurityJwtTokenValidationCallbackHandler();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

}