package com.wipro.igrs.empmgmt.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PropertyDetailsDTO implements Serializable
{

	private String address;
	private String country;
	private String state;
	private String district;
	private String postalcode;
	private String regCode;
	private String shareofProperty;
	private String regDate;
	
	
	private String assetType;
	private String assetDetails;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAssetDetails() {
		return assetDetails;
	}
	public void setAssetDetails(String assetDetails) {
		this.assetDetails = assetDetails;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostalcode() {
		return postalcode;
	}
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}
	public String getRegCode() {
		return regCode;
	}
	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getShareofProperty() {
		return shareofProperty;
	}
	public void setShareofProperty(String shareofProperty) {
		this.shareofProperty = shareofProperty;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
	