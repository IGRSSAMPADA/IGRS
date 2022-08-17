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
public class Pfa implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3861783225665589482L;
	protected Integer mv;
    protected MatchingStrategy ms;
    protected String av;
    protected Integer lmv;
    protected String lav;

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
     * Gets the value of the av property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAv() {
        return av;
    }

    /**
     * Sets the value of the av property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAv(String value) {
        this.av = value;
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
     * Gets the value of the lav property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLav() {
        return lav;
    }

    /**
     * Sets the value of the lav property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLav(String value) {
        this.lav = value;
    }

}
