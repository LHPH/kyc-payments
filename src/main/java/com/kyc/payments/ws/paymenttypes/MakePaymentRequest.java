//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.paymenttypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.kyc.payments.ws.coretypes.PaymentData;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payment" type="{http://kyc-payments.com/CommonTypes}PaymentData"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "payment"
})
@XmlRootElement(name = "MakePaymentRequest")
public class MakePaymentRequest
    implements Serializable
{

    @XmlElement(required = true)
    protected PaymentData payment;

    /**
     * Obtiene el valor de la propiedad payment.
     * 
     * @return
     *     possible object is
     *     {@link PaymentData }
     *     
     */
    public PaymentData getPayment() {
        return payment;
    }

    /**
     * Define el valor de la propiedad payment.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentData }
     *     
     */
    public void setPayment(PaymentData value) {
        this.payment = value;
    }

}
