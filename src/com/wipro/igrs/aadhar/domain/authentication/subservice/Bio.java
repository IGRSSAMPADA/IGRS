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
public class Bio implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7050303827764627538L;
	protected byte[] value;
    protected BioMetricType type;
    protected BiometricPosition posh;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setValue(byte[] value) {
        this.value = ((byte[]) value);
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link BioMetricType }
     *     
     */
    public BioMetricType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link BioMetricType }
     *     
     */
    public void setType(BioMetricType value) {
        this.type = value;
    }

    /**
     * Gets the value of the posh property.
     * 
     * @return
     *     possible object is
     *     {@link BiometricPosition }
     *     
     */
    public BiometricPosition getPosh() {
        return posh;
    }

    /**
     * Sets the value of the posh property.
     * 
     * @param value
     *     allowed object is
     *     {@link BiometricPosition }
     *     
     */
    public void setPosh(BiometricPosition value) {
        this.posh = value;
    }

}
