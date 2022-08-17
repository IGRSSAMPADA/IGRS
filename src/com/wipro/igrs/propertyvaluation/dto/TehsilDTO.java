package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;

public class TehsilDTO implements Serializable {
	private Integer tehsilID;
	/**
	 * @author Rishab
	 */
	
	private String tehsil;
	/**
	 * @author Rishab
	 */
	public Integer getTehsilID() {
		return tehsilID;
	}
	public void setTehsilID(Integer tehsilID) {
		this.tehsilID = tehsilID;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}


}
