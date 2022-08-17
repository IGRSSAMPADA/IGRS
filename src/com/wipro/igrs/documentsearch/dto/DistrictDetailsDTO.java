package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class DistrictDetailsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3869351436332162356L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
