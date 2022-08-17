package com.wipro.igrs.propertytypeuom.dto;

import java.io.Serializable;

public class PropTypeUOMDTO implements Serializable{
	
	
	private String propertyTypeName;
	private String propertyTypeL1Name;
	private String propertyTypeL2Name;
	private String uom;
	private String id;	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPropertyTypeName() {
		return propertyTypeName;
	}
	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}
	public String getPropertyTypeL1Name() {
		return propertyTypeL1Name;
	}
	public void setPropertyTypeL1Name(String propertyTypeL1Name) {
		this.propertyTypeL1Name = propertyTypeL1Name;
	}
	public String getPropertyTypeL2Name() {
		return propertyTypeL2Name;
	}
	public void setPropertyTypeL2Name(String propertyTypeL2Name) {
		this.propertyTypeL2Name = propertyTypeL2Name;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}


}
