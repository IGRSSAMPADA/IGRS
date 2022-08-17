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
public class Pa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2951487477958579998L;
	protected MatchingStrategy ms;
    protected String co;
    protected String house;
    protected String street;
    protected String lm;
    protected String loc;
    protected String vtc;
    protected String po;
    protected String subdist;
    protected String dist;
    protected String state;
    protected String pc;

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
     * Gets the value of the co property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCo() {
        return co;
    }

    /**
     * Sets the value of the co property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCo(String value) {
        this.co = value;
    }

    /**
     * Gets the value of the house property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets the value of the house property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouse(String value) {
        this.house = value;
    }

    /**
     * Gets the value of the street property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets the value of the street property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreet(String value) {
        this.street = value;
    }

    /**
     * Gets the value of the lm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLm() {
        return lm;
    }

    /**
     * Sets the value of the lm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLm(String value) {
        this.lm = value;
    }

    /**
     * Gets the value of the loc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoc() {
        return loc;
    }

    /**
     * Sets the value of the loc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoc(String value) {
        this.loc = value;
    }

    /**
     * Gets the value of the vtc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVtc() {
        return vtc;
    }

    /**
     * Sets the value of the vtc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVtc(String value) {
        this.vtc = value;
    }

    /**
     * Gets the value of the po property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPo() {
        return po;
    }

    /**
     * Sets the value of the po property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPo(String value) {
        this.po = value;
    }

    /**
     * Gets the value of the subdist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubdist() {
        return subdist;
    }

    /**
     * Sets the value of the subdist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubdist(String value) {
        this.subdist = value;
    }

    /**
     * Gets the value of the dist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDist() {
        return dist;
    }

    /**
     * Sets the value of the dist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDist(String value) {
        this.dist = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the pc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPc() {
        return pc;
    }

    /**
     * Sets the value of the pc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPc(String value) {
        this.pc = value;
    }

}
