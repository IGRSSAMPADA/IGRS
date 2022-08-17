package com.wipro.igrs.newreginit.dto;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonGetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonSetter;
import org.codehaus.jackson.map.annotate.JsonSerialize;


public class AadharResidentDetails {
	
	private String aadhaarId;
	private String resName;
	private String residentPhoto;
	private String dob;
	private String gender;
	private String mobile;
	private String emailId;
	private String guardianName;
	private String guardianRelationType;
	private String careof;
	private String building;
	private String street;	
	private String landmark;
	private String locality;
	private String vtc;
	private String subdistrict;
	private String district;
	private String state;
	private String pincode;
	private String PO;
	//private String presentAddress;
	//private String permanentAddress;
	public String getAadhaarId() {
		return aadhaarId;
	}
	public void setAadhaarId(String aadhaarId) {
		this.aadhaarId = aadhaarId;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResidentPhoto() {
		return residentPhoto;
	}
	public void setResidentPhoto(String residentPhoto) {
		this.residentPhoto = residentPhoto;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getGuardianRelationType() {
		return guardianRelationType;
	}
	public void setGuardianRelationType(String guardianRelationType) {
		this.guardianRelationType = guardianRelationType;
	}
	public String getCareof() {
		return careof;
	}
	public void setCareof(String careof) {
		this.careof = careof;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getVtc() {
		return vtc;
	}
	public void setVtc(String vtc) {
		this.vtc = vtc;
	}
	public String getSubdistrict() {
		return subdistrict;
	}
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@JsonGetter("PO")
	public String getPO() {
		return PO;
	}
	@JsonSetter("PO")
	public void setPO(String po) {
		PO = po;
	}
	
	


	
/*	public String getPresentAddress() {
		return presentAddress;
	}
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	*/


}
