package com.kyc.payments.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@Configuration
@EnableWs
@Profile("dev2")
public class WebServiceConfig extends WsConfigurerAdapter {

    @Value("${service.url}")
    private String urlService;

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext ctx){

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(ctx);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet,"/ws/*");
    }

    //http://localhost:9000/ws/paymentService/KYCPayments.wsdl
    @Bean(name="KYCPayments")
    public SimpleWsdl11Definition payments(){

        SimpleWsdl11Definition definition = new SimpleWsdl11Definition();
        definition.setWsdl(new ClassPathResource("ws/KYCPayments.wsdl"));

        return definition;
    }

    //Exposes the xsd schema in the url
    @Bean(name="CommonTypes")
    public XsdSchema commonTypes(){

        ClassPathResource commonTypes = new ClassPathResource("ws/CommonTypes.xsd");
        SimpleXsdSchema xsd = new SimpleXsdSchema(commonTypes);
        return xsd;
    }

    //Exposes the xsd schema in the url
    @Bean(name="PaymentTypes")
    public XsdSchema paymentTypes(){

        ClassPathResource paymentTypes = new ClassPathResource("ws/PaymentTypes.xsd");
        SimpleXsdSchema xsd = new SimpleXsdSchema(paymentTypes);
        return xsd;
    }

    @Bean(name="HeaderTypes")
    public XsdSchema securityTypes(){

        ClassPathResource headerTypes = new ClassPathResource("ws/HeaderTypes.xsd");
        SimpleXsdSchema xsd = new SimpleXsdSchema(headerTypes);
        return xsd;
    }



}
