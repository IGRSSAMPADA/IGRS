/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ARNAV.NEGI
 *
 */
@XmlRootElement
public class Pi implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6488011548241764027L;
	protected MatchingStrategy ms;
    protected Integer mv;
    protected String name;
    protected Integer lmv;
    protected String lname;
    protected Gender gender;
    protected String dob;
    protected String dobt;
    protected Integer age;
    protected String phone;
    protected String email;

    /**
     * Gets the value of the ms property.
     * 
     * @return
     *     possible object is
     *     {@link MatchingStrategy }
     *     
     */
    public MatchingStrategy getMs() {
        if (ms == null) {
            return MatchingStrategy.E;
        } else {
            return ms;
        }
    }

    /**
     * Sets the value of the ms property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchingStrategy }
     *     
     */
    public void setMs(MatchingStrategy value) {
        this.ms = value;
    }

    /**
     * Gets the value of the mv property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getMv() {
        if (mv == null) {
            return  100;
        } else {
            return mv;
        }
    }

    /**
     * Sets the value of the mv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMv(Integer value) {
        this.mv = value;
    }

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
     * Gets the value of the lmv property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLmv() {
        return lmv;
    }

    /**
     * Sets the value of the lmv property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLmv(Integer value) {
        this.lmv = value;
    }

    /**
     * Gets the value of the lname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLname() {
        return lname;
    }

    /**
     * Sets the value of the lname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLname(String value) {
        this.lname = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

    /**
     * Gets the value of the dob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDob() {
        return dob;
    }

    /**
     * Sets the value of the dob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDob(String value) {
        this.dob = value;
    }

    /**
     * Gets the value of the dobt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDobt() {
        return dobt;
    }

    /**
     * Sets the value of the dobt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDobt(String value) {
        this.dobt = value;
    }

    /**
     * Gets the value of the age property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAge(Integer value) {
        this.age = value;
    }

    /**
     * Gets the value of the phone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
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

}
