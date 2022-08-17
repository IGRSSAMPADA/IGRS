package com.wipro.igrs.propertytypeuom.dto;

import java.io.Serializable;

public class MappingDataDTO implements Serializable{
	
	private String propertyTypeName;
	private String propertyTypeID;
	
	private String propertyTypeL1Name;
	private String propertyTypeL1ID;
	
	private String propertyTypeL2Name;
	private String propertyTypeL2ID;
	
	private String uomName;
	private String uomID;
	
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
	public String getPropertyTypeID() {
		return propertyTypeID;
	}
	public void setPropertyTypeID(String propertyTypeID) {
		this.propertyTypeID = propertyTypeID;
	}
	public String getPropertyTypeL1Name() {
		return propertyTypeL1Name;
	}
	public void setPropertyTypeL1Name(String propertyTypeL1Name) {
		this.propertyTypeL1Name = propertyTypeL1Name;
	}
	public String getPropertyTypeL1ID() {
		return propertyTypeL1ID;
	}
	public void setPropertyTypeL1ID(String propertyTypeL1ID) {
		this.propertyTypeL1ID = propertyTypeL1ID;
	}
	public String getPropertyTypeL2Name() {
		return propertyTypeL2Name;
	}
	public void setPropertyTypeL2Name(String propertyTypeL2Name) {
		this.propertyTypeL2Name = propertyTypeL2Name;
	}
	public String getPropertyTypeL2ID() {
		return propertyTypeL2ID;
	}
	public void setPropertyTypeL2ID(String propertyTypeL2ID) {
		this.propertyTypeL2ID = propertyTypeL2ID;
	}
	public String getUomName() {
		return uomName;
	}
	public void setUomName(String uomName) {
		this.uomName = uomName;
	}
	public String getUomID() {
		return uomID;
	}
	public void setUomID(String uomID) {
		this.uomID = uomID;
	}

}
