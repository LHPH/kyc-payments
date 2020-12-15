package com.kyc.payments.ws.paymenttypes;

import com.kyc.payments.ws.adapters.Adapter2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="folio" type="{http://www.w3.org/2001/XMLSchema}integer"/>
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
        "folio"
})
@XmlRootElement(name = "GetStatusPaymentRequest")
public class GetStatusPaymentRequest
        implements Serializable
{

    @XmlElement(required = true,type = String.class)
    @XmlJavaTypeAdapter(Adapter2.class)
    @XmlSchemaType(name = "integer")
    protected Integer folio;

    /**
     * Obtiene el valor de la propiedad reference.
     *
     * @return
     *     possible object is
     *     {@link Integer }
     *
     */
    public Integer getFolio() {
        return folio;
    }

    /**
     * Define el valor de la propiedad folio.
     *
     * @param value
     *     allowed object is
     *     {@link Integer }
     *
     */
    public void setInteger(Integer value) {
        this.folio = value;
    }

}
