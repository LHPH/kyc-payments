//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.coretypes;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.kyc.payments.ws.coretypes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.kyc.payments.ws.coretypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceiptData }
     * 
     */
    public ReceiptData createReceiptData() {
        return new ReceiptData();
    }

    /**
     * Create an instance of {@link PaymentData }
     * 
     */
    public PaymentData createPaymentData() {
        return new PaymentData();
    }

    /**
     * Create an instance of {@link HistoricalPaymentCriteria }
     * 
     */
    public HistoricalPaymentCriteria createHistoricalPaymentCriteria() {
        return new HistoricalPaymentCriteria();
    }

    /**
     * Create an instance of {@link StatusPayment }
     * 
     */
    public StatusPayment createStatusPayment() {
        return new StatusPayment();
    }

    /**
     * Create an instance of {@link ErrorData }
     * 
     */
    public ErrorData createErrorData() {
        return new ErrorData();
    }

}
