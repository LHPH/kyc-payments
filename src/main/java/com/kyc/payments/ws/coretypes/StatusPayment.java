//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.coretypes;

import java.io.Serializable;
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
 * <p>Clase Java para StatusPayment complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="StatusPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="idTransaction" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="startDateTransaction" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="finishDateTransaction" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusPayment", propOrder = {
    "folio",
    "status",
    "idTransaction",
    "startDateTransaction",
    "finishDateTransaction"
})
public class StatusPayment
    implements Serializable
{

    @XmlElement(required = true)
    protected String folio;
    @XmlElement(required = true)
    protected String status;
    @XmlElement(required = true)
    protected BigInteger idTransaction;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date startDateTransaction;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter .class)
    @XmlSchemaType(name = "date")
    protected Date finishDateTransaction;

    /**
     * Obtiene el valor de la propiedad folio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolio(String value) {
        this.folio = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad idTransaction.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdTransaction() {
        return idTransaction;
    }

    /**
     * Define el valor de la propiedad idTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdTransaction(BigInteger value) {
        this.idTransaction = value;
    }

    /**
     * Obtiene el valor de la propiedad startDateTransaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getStartDateTransaction() {
        return startDateTransaction;
    }

    /**
     * Define el valor de la propiedad startDateTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDateTransaction(Date value) {
        this.startDateTransaction = value;
    }

    /**
     * Obtiene el valor de la propiedad finishDateTransaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFinishDateTransaction() {
        return finishDateTransaction;
    }

    /**
     * Define el valor de la propiedad finishDateTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinishDateTransaction(Date value) {
        this.finishDateTransaction = value;
    }

}
