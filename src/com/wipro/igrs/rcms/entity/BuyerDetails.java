package com.wipro.igrs.rcms.entity;

import javax.xml.bind.annotation.XmlElement;

public class BuyerDetails {

	private String ownerId;
	private String ownershipTypeIdBuyer;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String fatherName;
	private String relationType;
	private String caste;
	private String districtId;
	private String ownerShare;
	private String aadharNumber;
	private String nameAadharCard;
	private String idType;
	private String idNumber;
	private String address;
	private String city;
	private String stateId;
	private String country;
	private String phoneNumber;
	private String emailId;
	private String mobileNumber;
	private String deptCode;
	
	
	
	public BuyerDetails() {
		super();
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnershipTypeIdBuyer() {
		return ownershipTypeIdBuyer;
	}
	public void setOwnershipTypeIdBuyer(String ownershipTypeIdBuyer) {
		this.ownershipTypeIdBuyer = ownershipTypeIdBuyer;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getOwnerShare() {
		return ownerShare;
	}
	public void setOwnerShare(String ownerShare) {
		this.ownerShare = ownerShare;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getNameAadharCard() {
		return nameAadharCard;
	}
	public void setNameAadharCard(String nameAadharCard) {
		this.nameAadharCard = nameAadharCard;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStateId() {
		return stateId;
	}
	@XmlElement
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	
}
