package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class SRONameDetailsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573451387045701527L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
