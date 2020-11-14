package com.kyc.payments.configuration;

import com.kyc.payments.exceptions.DetailSoapFaultDefinitionExceptionResolver;
import com.kyc.payments.exceptions.KycPaymentExceptionResolver;
import com.kyc.payments.security.KycAuthProvider;
import com.kyc.payments.services.KycAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;

import org.springframework.ws.soap.security.xwss.XwsSecurityInterceptor;
import org.springframework.ws.soap.security.xwss.callback.SpringPlainTextPasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.Properties;


@Configuration
public class CommonConfig extends WsConfigurerAdapter{

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver() {
        SoapFaultMappingExceptionResolver exceptionResolver = new DetailSoapFaultDefinitionExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        faultDefinition.setFaultStringOrReason("Internal Server Error");
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(2);

        return exceptionResolver;
    }

    @Bean
    public KycPaymentExceptionResolver kycPaymentExceptionResolver() throws JAXBException {

        KycPaymentExceptionResolver kycPaymentExceptionResolver = new KycPaymentExceptionResolver();
        kycPaymentExceptionResolver.setOrder(1);

        return kycPaymentExceptionResolver;
    }

    @Bean
    public ProviderManager authenticationManager(){

        ProviderManager manager = new ProviderManager(authenticationProvider());
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public KycAuthProvider authenticationProvider(){

        KycAuthProvider auth = new KycAuthProvider();
        auth.setKycAuthService(kycAuthService());
        auth.setPasswordEncoder(encoder());
        return auth;
    }

    @Bean
    public KycAuthService kycAuthService(){
        KycAuthService kycAuthService = new KycAuthService();
        return kycAuthService;
    }

    @Bean
    public XwsSecurityInterceptor securityInterceptor(){

        XwsSecurityInterceptor securityInterceptor = new XwsSecurityInterceptor();
        securityInterceptor.setPolicyConfiguration(new ClassPathResource("securityPolicy.xml"));
        securityInterceptor.setCallbackHandler(securityCallbackHandler());
        return securityInterceptor;
    }

    @Bean
    public SpringPlainTextPasswordValidationCallbackHandler securityCallbackHandler() {

        SpringPlainTextPasswordValidationCallbackHandler  handler = new SpringPlainTextPasswordValidationCallbackHandler();
        handler.setAuthenticationManager(authenticationManager());
        return handler;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(securityInterceptor());
    }

}
