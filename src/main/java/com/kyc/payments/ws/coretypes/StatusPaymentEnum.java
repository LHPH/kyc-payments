//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.03 a las 01:08:11 PM CDT 
//


package com.kyc.payments.ws.coretypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para StatusPaymentCategory.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="StatusPaymentCategory">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="REJECTED"/>
 *     &lt;enumeration value="PAID"/>
 *     &lt;enumeration value="ONGOING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StatusPaymentCategory")
@XmlEnum
public enum StatusPaymentEnum {

    @XmlEnumValue("REJECTED")
    PAYMENT_REJECTED("REJECTED"),
    @XmlEnumValue("PAID")
    PAYMENT_PAID("PAID"),
    @XmlEnumValue("ONGOING")
    PAYMENT_ONGOING("ONGOING");
    private final String value;

    StatusPaymentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusPaymentEnum fromValue(String v) {
        for (StatusPaymentEnum c: StatusPaymentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
