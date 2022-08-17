package com.wipro.igrs.propertyvaluationefiling.dto;

import java.io.Serializable;

public class CeilingDTO implements Serializable {
	private int ceilingTypeId;
	private String ceilingType;
	
	public int getCeilingTypeId() {
		return ceilingTypeId;
	}
	public void setCeilingTypeId(int ceilingTypeId) {
		this.ceilingTypeId = ceilingTypeId;
	}
	public String getCeilingType() {
		return ceilingType;
	}
	public void setCeilingType(String ceilingType) {
		this.ceilingType = ceilingType;
	}
	
	
}
