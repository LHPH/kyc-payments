package com.kyc.payments.configuration;

import org.apache.ws.commons.schema.resolver.DefaultURIResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

import static com.kyc.payments.constants.AppConstants.NAME_SPACE_PAYMENTS_URI;

@Configuration
@Profile("dev")
public class WebServiceConfigLocal {

    @Value("${service.url}")
    private String urlService;

    //http://localhost:9008/kyc/soap/KYCPayments
    @Bean(name="KYCPayments")
    public DefaultWsdl11Definition payments(){

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setServiceName("KYCPayments");
        definition.setPortTypeName("PaymentsPortType");
        definition.setLocationUri(urlService);
        definition.setTargetNamespace(NAME_SPACE_PAYMENTS_URI);
        definition.setSchemaCollection(schemasCollection());
        return definition;
    }

    @Bean
    public XsdSchemaCollection schemasCollection(){

        CommonsXsdSchemaCollection collection = new CommonsXsdSchemaCollection();
        ClassPathResource paymentTypes = new ClassPathResource("ws/PaymentTypes.xsd");

        collection.setXsds(paymentTypes);
        collection.setInline(false);
        collection.setUriResolver(new DefaultURIResolver());
        return collection;
    }

    //Exposes the xsd schema in the url
    @Bean(name="CommonTypes")
    public XsdSchema commonTypes(){

        ClassPathResource commonTypes = new ClassPathResource("ws/CommonTypes.xsd");
        SimpleXsdSchema xsd = new SimpleXsdSchema(commonTypes);
        return xsd;
    }

    @Bean(name="HeaderTypes")
    public XsdSchema securityTypes(){

        ClassPathResource headerTypes = new ClassPathResource("ws/HeaderTypes.xsd");
        SimpleXsdSchema xsd = new SimpleXsdSchema(headerTypes);
        return xsd;
    }

}
