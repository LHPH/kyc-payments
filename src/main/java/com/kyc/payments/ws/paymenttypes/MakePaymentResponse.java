//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.31 a las 01:07:58 PM CST 
//


package com.kyc.payments.ws.paymenttypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.kyc.payments.ws.coretypes.ReceiptData;


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
 *         &lt;element name="receipt" type="{http://kyc-payments.com/CommonTypes}ReceiptData"/>
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
    "receipt"
})
@XmlRootElement(name = "MakePaymentResponse")
public class MakePaymentResponse
    implements Serializable
{

    @XmlElement(required = true)
    protected ReceiptData receipt;

    /**
     * Obtiene el valor de la propiedad receipt.
     * 
     * @return
     *     possible object is
     *     {@link ReceiptData }
     *     
     */
    public ReceiptData getReceipt() {
        return receipt;
    }

    /**
     * Define el valor de la propiedad receipt.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiptData }
     *     
     */
    public void setReceipt(ReceiptData value) {
        this.receipt = value;
    }

}
