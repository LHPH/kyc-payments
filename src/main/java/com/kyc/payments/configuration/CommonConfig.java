package com.kyc.payments.configuration;

import com.kyc.core.exception.handlers.KycGenericSoapExceptionHandler;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import static com.kyc.payments.constants.AppConstants.ERROR_CODE_001;


@Configuration
@Import(value = {KycMessages.class})
@EnableJpaRepositories(basePackages = {"com.kyc.core.persistence.repositories","com.kyc.payments.repositories"})
@EntityScan(basePackages = {"com.kyc.core.persistence.entity","com.kyc.payments.entity"})
public class CommonConfig{

    @Bean
    public Jaxb2Marshaller marshaller(){

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(MessageData.class);
        return marshaller;
    }

    @Bean
    public KycGenericSoapExceptionHandler kycGenericSoapExceptionHandler(KycMessages kycMessages) {

        return new KycGenericSoapExceptionHandler(kycMessages.getMessage(ERROR_CODE_001), marshaller());
    }

}
