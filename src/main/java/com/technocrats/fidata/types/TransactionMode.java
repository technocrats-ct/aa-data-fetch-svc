//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.14 at 09:57:05 PM IST 
//


package com.technocrats.fidata.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransactionMode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransactionMode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CASH"/>
 *     &lt;enumeration value="ATM"/>
 *     &lt;enumeration value="CARD"/>
 *     &lt;enumeration value="UPI"/>
 *     &lt;enumeration value="FT"/>
 *     &lt;enumeration value="OTHERS"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransactionMode")
@XmlEnum
public enum TransactionMode {

    CASH,
    ATM,
    CARD,
    UPI,
    FT,
    OTHERS;

    public String value() {
        return name();
    }

    public static TransactionMode fromValue(String v) {
        return valueOf(v);
    }

}
