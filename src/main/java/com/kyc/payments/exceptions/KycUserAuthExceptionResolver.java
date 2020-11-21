package com.kyc.payments.exceptions;

import com.kyc.payments.ws.coretypes.ErrorData;
import com.kyc.payments.ws.coretypes.ObjectFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.AbstractEndpointExceptionResolver;
import org.springframework.ws.soap.SoapBody;
import org.springframework.ws.soap.SoapFault;
import org.springframework.ws.soap.SoapFaultDetail;
import org.springframework.ws.soap.SoapMessage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import java.lang.invoke.MethodHandles;
import java.util.Locale;

import static com.kyc.payments.constants.Constants.ERROR_CODE_02;
import static com.kyc.payments.constants.Constants.ERROR_DESC_02;

public class KycUserAuthExceptionResolver extends AbstractEndpointExceptionResolver {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private final Marshaller marshaller;
    private ObjectFactory objectFactory = new ObjectFactory();

    public KycUserAuthExceptionResolver()  {

        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(ErrorData.class);
            marshaller = jaxbContext.createMarshaller();
        }
        catch(JAXBException e){
            LOGGER.info("No se pudo cargar el marshaller",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean resolveExceptionInternal(MessageContext messageContext, Object o, Exception e) {

        LOGGER.error("No se pudo autenticar al usuario ",e);

        ErrorData errorData = new ErrorData();
        errorData.setNumberError(ERROR_CODE_02);
        errorData.setDescription(ERROR_DESC_02);

        SoapMessage soapMessage = (SoapMessage) messageContext.getResponse();
        SoapBody soapBody = soapMessage.getSoapBody();

        SoapFault soapFault = soapBody.addClientOrSenderFault("Error de Autenticacion", Locale.ENGLISH);

        SoapFaultDetail detail = soapFault.addFaultDetail();
        Result result = detail.getResult();

        try{
            marshaller.marshal(errorData,result);
            return true;
        }
        catch (JAXBException jaxbex){
            LOGGER.error("No se pudo hacer la conversion ",jaxbex);
        }
        return false;
    }
}
