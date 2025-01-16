package com.kyc.payments.configuration;

import com.kyc.core.exception.handlers.KycGenericSoapExceptionHandler;
import com.kyc.core.services.KycUserDetailsService;
import com.kyc.core.soap.security.SpringUsernameTokenValidator;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.engine.WSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SpringSecurityPasswordValidationCallbackHandler;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WsConfigurerAdapter {

    @Autowired
    private KycGenericSoapExceptionHandler kycGenericSoapExceptionHandler;

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
    public UserDetailsService userDetailsService(){
        return new KycUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor(){

        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setValidationActions("UsernameToken");
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());
        securityInterceptor.setExceptionResolver(kycGenericSoapExceptionHandler);
        WSSConfig wssConfig = WSSConfig.getNewInstance();
        wssConfig.setValidator(WSConstants.USERNAME_TOKEN,new SpringUsernameTokenValidator(encoder()));
        securityInterceptor.setWssConfig(wssConfig);
        return securityInterceptor;
    }

    @Bean
    public SpringSecurityPasswordValidationCallbackHandler securityCallbackHandler() {

        SpringSecurityPasswordValidationCallbackHandler handler = new SpringSecurityPasswordValidationCallbackHandler();
        handler.setUserDetailsService(userDetailsService());
        return handler;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

}