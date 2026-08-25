package com.kyc.payments.configuration;

import com.kyc.core.exception.handlers.KycGenericSoapExceptionHandler;
import com.kyc.core.model.XmlMessageData;
import com.kyc.core.properties.KycMessages;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
@Import(value = {
        KycMessages.class
})
@EnableJpaRepositories(basePackages = {"com.kyc.core.persistence.repositories","com.kyc.payments.repositories"})
@EntityScan(basePackages = {"com.kyc.core.persistence.entity","com.kyc.payments.entity"})
@EnableFeignClients(value = "com.kyc.core.rest.feign.common")
public class CommonConfig{

    @Bean
    public Jaxb2Marshaller marshaller(){

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(XmlMessageData.class);
        return marshaller;
    }

    @Bean
    public KycGenericSoapExceptionHandler kycGenericSoapExceptionHandler(KycMessages kycMessages) {

        return new KycGenericSoapExceptionHandler(kycMessages, marshaller());
    }

}
