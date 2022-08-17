/**
 * 
 */
package com.wipro.igrs.aadhar.domain.authentication.subservice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ARNAV.NEGI
 *
 */
@XmlRootElement
public class Bios implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1592743618349053154L;
	protected List<Bio> bio;

    /**
     * Gets the value of the bio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Bio }
     * 
     * 
     */
    public List<Bio> getBio() {
        if (bio == null) {
            bio = new ArrayList<Bio>();
        }
        return this.bio;
    }

}
