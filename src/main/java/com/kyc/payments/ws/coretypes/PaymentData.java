//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.coretypes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.kyc.payments.util.DateAdapter;


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
 *         &lt;element name="motive" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
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
    "idService",
    "idBank",
    "destination",
    "datePayment"
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
    protected BigDecimal motive;
    @XmlElement(required = true)
    protected BigInteger idService;
    @XmlElement(required = true)
    protected String idBank;
    @XmlElement(required = true)
    protected String destination;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date datePayment;

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
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMotive() {
        return motive;
    }

    /**
     * Define el valor de la propiedad motive.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMotive(BigDecimal value) {
        this.motive = value;
    }

    /**
     * Obtiene el valor de la propiedad idService.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdService() {
        return idService;
    }

    /**
     * Define el valor de la propiedad idService.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdService(BigInteger value) {
        this.idService = value;
    }

    /**
     * Obtiene el valor de la propiedad idBank.
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
    public String getDestination() {
        return destination;
    }

    /**
     * Define el valor de la propiedad destination.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestination(String value) {
        this.destination = value;
    }

    /**
     * Obtiene el valor de la propiedad datePayment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDatePayment() {
        return datePayment;
    }

    /**
     * Define el valor de la propiedad datePayment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatePayment(Date value) {
        this.datePayment = value;
    }

}
