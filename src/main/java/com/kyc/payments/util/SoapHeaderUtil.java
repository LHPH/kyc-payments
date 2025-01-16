package com.kyc.payments.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

public class SoapHeaderUtil {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /*
    public static DeviceData getHeaderDeviceData(SoapHeaderElement headerElement){

        if(headerElement!=null){
            return unmarshallerHeader(headerElement);
        }
        DeviceData dev = new DeviceData();
        dev.setDevice("UNKNOWN");
        return dev;
    }

    public static DeviceData getInfoHeaders(SoapHeader soapHeader){

        DeviceData dev = new DeviceData();
        dev.setDevice("UNKNOW");
        if(soapHeader!=null){
            QName qName = new QName(NAME_SPACE_HEADER_URI,"DeviceData");
            Iterator<SoapHeaderElement> it = soapHeader.examineHeaderElements(qName);
            while(it.hasNext()) {
                dev = unmarshallerHeader(it.next());
            }
        }
        return dev;
    }
    
    public static DeviceData unmarshallerHeader(SoapHeaderElement headerElement){
        try {

            JAXBContext context = JAXBContext.newInstance(DeviceData.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DeviceData dev = (DeviceData) unmarshaller.unmarshal(headerElement.getSource());
            return dev;

        } catch (JAXBException e) {
            LOGGER.warn("No se pudo procesar el header {}",e);
        }
        DeviceData dev = new DeviceData();
        dev.setDevice("");
        return dev;
    }*/

}
