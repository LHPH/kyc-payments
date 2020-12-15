package com.kyc.payments.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

import javax.xml.namespace.QName;
import java.lang.invoke.MethodHandles;

public class DetailSoapFaultDefinitionExceptionResolver extends SoapFaultMappingExceptionResolver {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private static final QName CODE = new QName("code");
    private static final QName MESSAGE = new QName("description");


    @Override
    protected void customizeFault(Object endpoint, Exception ex, SoapFault fault) {
        LOGGER.error("Procesando exception ",ex);
        SoapFaultDetail detail = fault.addFaultDetail();

        detail.addFaultDetailElement(CODE).addText("500");
        detail.addFaultDetailElement(MESSAGE).addText(ex.getMessage());
    }

}
