package com.kyc.payments.configuration;

import com.kyc.payments.exceptions.DetailSoapFaultDefinitionExceptionResolver;
import com.kyc.payments.exceptions.KycPaymentExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.bind.JAXBException;
import java.util.Properties;

@Configuration
public class CommonConfig {

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


}
