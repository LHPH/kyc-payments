//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.paymenttypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="clientNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="payments" type="{http://kyc-payments.com/CommonTypes}ReceiptData" maxOccurs="unbounded"/>
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
    "clientNumber",
    "payments"
})
@XmlRootElement(name = "GetHistoricalPaymentsResponse")
public class GetHistoricalPaymentsResponse
    implements Serializable
{

    @XmlElement(required = true)
    protected BigInteger clientNumber;
    @XmlElement(required = true)
    protected List<ReceiptData> payments;

    /**
     * Obtiene el valor de la propiedad clientNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getClientNumber() {
        return clientNumber;
    }

    /**
     * Define el valor de la propiedad clientNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setClientNumber(BigInteger value) {
        this.clientNumber = value;
    }

    /**
     * Gets the value of the payments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the payments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPayments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReceiptData }
     * 
     * 
     */
    public List<ReceiptData> getPayments() {
        if (payments == null) {
            payments = new ArrayList<ReceiptData>();
        }
        return this.payments;
    }

}
