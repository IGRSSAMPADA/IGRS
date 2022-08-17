package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;

public class PropertyDTO implements Serializable{
	private int propertyTypeID;
	private String propertyType;
	
	public int getPropertyTypeID() {
		return propertyTypeID;
	}
	public void setPropertyTypeID(int propertyTypeID) {
		this.propertyTypeID = propertyTypeID;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
}
