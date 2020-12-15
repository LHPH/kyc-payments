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
 * <p>Clase Java para HistoricalPaymentCriteria complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="HistoricalPaymentCriteria">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="finishDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="numRecords" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoricalPaymentCriteria", propOrder = {
    "startDate",
    "finishDate",
    "numRecords"
})
public class HistoricalPaymentCriteria
    implements Serializable
{

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected Date startDate;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "date")
    protected Date finishDate;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter2 .class)
    @XmlSchemaType(name = "integer")
    protected Integer numRecords;

    /**
     * Obtiene el valor de la propiedad startDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Define el valor de la propiedad startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    /**
     * Obtiene el valor de la propiedad finishDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getFinishDate() {
        return finishDate;
    }

    /**
     * Define el valor de la propiedad finishDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinishDate(Date value) {
        this.finishDate = value;
    }

    /**
     * Obtiene el valor de la propiedad numRecords.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Integer getNumRecords() {
        return numRecords;
    }

    /**
     * Define el valor de la propiedad numRecords.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumRecords(Integer value) {
        this.numRecords = value;
    }

}
