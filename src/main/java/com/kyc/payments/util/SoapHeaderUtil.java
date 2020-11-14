package com.kyc.payments.util;

import com.kyc.payments.ws.headertypes.DeviceData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ws.soap.SoapHeaderElement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.lang.invoke.MethodHandles;

public class SoapHeaderUtil {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static DeviceData getHeaderDeviceData(SoapHeaderElement headerElement){

        if(headerElement!=null){
            try {

                JAXBContext context = JAXBContext.newInstance(DeviceData.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                DeviceData dev = (DeviceData) unmarshaller.unmarshal(headerElement.getSource());
                return dev;

            } catch (JAXBException e) {
                LOGGER.warn("No se pudo procesar el header {}",e);
            }
        }
        DeviceData dev = new DeviceData();
        dev.setDevice("UNKNOWN");
        return dev;
    }

}
