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
public class Demo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7034916029028493048L;
	protected Pi pi;
    protected Pa pa;
    protected Pfa pfa;
    protected String lang;

    /**
     * Gets the value of the pi property.
     * 
     * @return
     *     possible object is
     *     {@link Pi }
     *     
     */
    public Pi getPi() {
        return pi;
    }

    /**
     * Sets the value of the pi property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pi }
     *     
     */
    public void setPi(Pi value) {
        this.pi = value;
    }

    /**
     * Gets the value of the pa property.
     * 
     * @return
     *     possible object is
     *     {@link Pa }
     *     
     */
    public Pa getPa() {
        return pa;
    }

    /**
     * Sets the value of the pa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pa }
     *     
     */
    public void setPa(Pa value) {
        this.pa = value;
    }

    /**
     * Gets the value of the pfa property.
     * 
     * @return
     *     possible object is
     *     {@link Pfa }
     *     
     */
    public Pfa getPfa() {
        return pfa;
    }

    /**
     * Sets the value of the pfa property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pfa }
     *     
     */
    public void setPfa(Pfa value) {
        this.pfa = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        if (lang == null) {
            return "23";
        } else {
            return lang;
        }
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

}
