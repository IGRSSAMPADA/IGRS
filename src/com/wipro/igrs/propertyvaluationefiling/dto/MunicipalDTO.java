package com.wipro.igrs.propertyvaluationefiling.dto;

import java.io.Serializable;

public class MunicipalDTO implements Serializable{

	private String municipalBody;
	private int municipalBodyID;
	
	public String getMunicipalBody() {
		return municipalBody;
	}

	public void setMunicipalBody(String municipalBody) {
		this.municipalBody = municipalBody;
	}

	public int getMunicipalBodyID() {
		return municipalBodyID;
	}

	public void setMunicipalBodyID(int municipalBodyID) {
		this.municipalBodyID = municipalBodyID;
	}


	
	
	
}
