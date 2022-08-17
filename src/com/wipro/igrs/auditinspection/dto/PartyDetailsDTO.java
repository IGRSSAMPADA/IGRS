/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;
import java.util.ArrayList;


public class PartyDetailsDTO implements Serializable {

	private String partyTypeName;
	private String mobNo;
	private String email;
	private String motherName;
	private String fatherName;
	private String typeId;
	private String orgName;
	private String authPrsnName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String age;
	private String nationality;
	private String country;
	private String state;
	private String city;
	private String address;
	private String postalCode;
	private String landNo;
	private String mobileNo;
	private String emailId;
	private String partyChk;
	//added by shruti-18/10/2013
	private String spouseName;
	private String caste;
	private String religion;
	private String physicallChallenged;
	//end
	
	public String getPhysicallChallenged() {
		return physicallChallenged;
	}
	public void setPhysicallChallenged(String physicallChallenged) {
		this.physicallChallenged = physicallChallenged;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	ArrayList partyList=new ArrayList();

	public ArrayList getPartyList() {
		return partyList;
	}
	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}
	public String getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLandNo() {
		return landNo;
	}
	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPartyChk() {
		return partyChk;
	}
	public void setPartyChk(String partyChk) {
		this.partyChk = partyChk;
	}
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAuthPrsnName() {
		return authPrsnName;
	}
	public void setAuthPrsnName(String authPrsnName) {
		this.authPrsnName = authPrsnName;
	}

}
