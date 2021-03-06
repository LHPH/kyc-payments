//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.31 a las 01:07:58 PM CST 
//


package com.kyc.payments.ws.coretypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ErrorWS element declaration.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;element name="ErrorWS">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "numberError",
    "description"
})
@XmlRootElement(name = "ErrorWS")
public class ErrorData
    implements Serializable
{

    @XmlElement(name = "code", required = true)
    protected String numberError;
    @XmlElement(required = true)
    protected String description;

    public ErrorData(){

    }

    public ErrorData(String code, String desc){
        this.numberError = code;
        this.description = desc;
    }

    /**
     * Obtiene el valor de la propiedad numberError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberError() {
        return numberError;
    }

    /**
     * Define el valor de la propiedad numberError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberError(String value) {
        this.numberError = value;
    }

    /**
     * Obtiene el valor de la propiedad description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Define el valor de la propiedad description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
