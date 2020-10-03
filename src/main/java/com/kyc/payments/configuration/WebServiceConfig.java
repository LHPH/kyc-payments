package com.kyc.payments.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import static com.kyc.payments.constants.Constants.NAME_SPACE_PAYMENTS_URI;

@EnableWs
@Configuration
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
    //http://localhost:9000/ws/paymentService/payments.wsdl
    @Bean(name="payments")
    public DefaultWsdl11Definition payments(XsdSchema paymentSchema){

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("Payments");
        definition.setLocationUri(urlService);
        definition.setTargetNamespace(NAME_SPACE_PAYMENTS_URI);
        definition.setSchema(paymentSchema);
        return definition;
    }

    @Bean
    public XsdSchema paymentSchema(){

        ClassPathResource cp = new ClassPathResource("ws/PaymentTypes.xsd");
        return new SimpleXsdSchema(cp);
    }
}
