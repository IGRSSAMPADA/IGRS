/**
 * 
 */
package com.wipro.igrs.officearea.dto;

import java.io.Serializable;

/**
 * @author HMOHAM
 *
 */
public class WardPatwariDTO implements Serializable {

	private String name;
	private String id;
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
