package com.wipro.igrs.propertyvaluationefiling.dto;

import java.io.Serializable;

public class AreaDTO implements Serializable{
	private String areaType;
	private int areaId;
	
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	
	
}
