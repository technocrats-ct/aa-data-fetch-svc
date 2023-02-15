//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.02.14 at 09:57:05 PM IST 
//


package com.technocrats.fidata.types;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dob" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="mobile" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="nominee" use="required" type="{http://api.rebit.org.in/FISchema/deposit}HoldingNominee" />
 *       &lt;attribute name="landline" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="address" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="email" use="required" type="{http://api.rebit.org.in/FISchema/deposit}HolderEmail" />
 *       &lt;attribute name="pan" use="required" type="{http://api.rebit.org.in/FISchema/deposit}HolderPan" />
 *       &lt;attribute name="ckycCompliance" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Holder")
public class Holder {

    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "dob", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dob;
    @XmlAttribute(name = "mobile", required = true)
    protected BigInteger mobile;
    @XmlAttribute(name = "nominee", required = true)
    protected HoldingNominee nominee;
    @XmlAttribute(name = "landline")
    protected String landline;
    @XmlAttribute(name = "address")
    protected String address;
    @XmlAttribute(name = "email", required = true)
    protected String email;
    @XmlAttribute(name = "pan", required = true)
    protected String pan;
    @XmlAttribute(name = "ckycCompliance", required = true)
    protected boolean ckycCompliance;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDob() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDob(XMLGregorianCalendar value) {
        this.dob = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMobile(BigInteger value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the nominee property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingNominee }
     *     
     */
    public HoldingNominee getNominee() {
        return nominee;
    }

    /**
     * Sets the value of the nominee property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingNominee }
     *     
     */
    public void setNominee(HoldingNominee value) {
        this.nominee = value;
    }

    /**
     * Gets the value of the landline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLandline() {
        return landline;
    }

    /**
     * Sets the value of the landline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLandline(String value) {
        this.landline = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the pan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPan() {
        return pan;
    }

    /**
     * Sets the value of the pan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPan(String value) {
        this.pan = value;
    }

    /**
     * Gets the value of the ckycCompliance property.
     * 
     */
    public boolean isCkycCompliance() {
        return ckycCompliance;
    }

    /**
     * Sets the value of the ckycCompliance property.
     * 
     */
    public void setCkycCompliance(boolean value) {
        this.ckycCompliance = value;
    }

}
