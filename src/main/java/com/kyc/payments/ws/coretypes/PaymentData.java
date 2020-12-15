//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.31 a las 01:07:58 PM CST 
//


package com.kyc.payments.ws.coretypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.kyc.payments.ws.adapters.Adapter1;
import com.kyc.payments.ws.adapters.Adapter2;


/**
 * <p>Clase Java para PaymentData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="PaymentData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="motive" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idService" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="idBank" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="destination" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="datePayment" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentData", propOrder = {
    "reference",
    "source",
    "amount",
    "motive",
    "idBank"
})
public class PaymentData
    implements Serializable
{

    @XmlElement(required = true)
    protected String reference;
    @XmlElement(required = true)
    protected String source;
    @XmlElement(required = true)
    protected String amount;
    @XmlElement(required = true)
    protected String motive;
    @XmlElement(required = true)
    protected String idBank;

    /**
     * Obtiene el valor de la propiedad reference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Define el valor de la propiedad reference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Obtiene el valor de la propiedad source.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Define el valor de la propiedad source.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Obtiene el valor de la propiedad amount.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Define el valor de la propiedad amount.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmount(String value) {
        this.amount = value;
    }

    /**
     * Obtiene el valor de la propiedad motive.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotive() {
        return motive;
    }

    /**
     * Define el valor de la propiedad motive.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotive(String value) {
        this.motive = value;
    }

    /**
     * Obtiene el valor de la propiedad idService.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */

    public String getIdBank() {
        return idBank;
    }

    /**
     * Define el valor de la propiedad idBank.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdBank(String value) {
        this.idBank = value;
    }

    /**
     * Obtiene el valor de la propiedad destination.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
}
