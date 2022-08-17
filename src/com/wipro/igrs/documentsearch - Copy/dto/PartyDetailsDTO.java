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
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PartyDetailsDTO implements Serializable{

	
	public PartyDetailsDTO() {
		
		
		
	}
	
	private String firstName;
	private String lastName;
	private String middleName;
	private String partyTypeName;
	private String gender;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String caste;
	private String religion;
	private String nationality;
	private String physicallChallenged;
	private String address;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String landNo;
	private String mobileNo;
	private String emailId;
	private String proofTypeId;
	private String proofNumber;
	private String ocupation;
	private String propShare;
	private String thumbImg;
	private String photoImg;
	private String signImg;
	private ArrayList partyList = new ArrayList();
	//added by shruti-5th aug 2013
	private String partyChk;
	private String orgName;
	private String authPrsnName;
	
	private String govtOffName;
	private String govtOffDesg;
	private String govtOffAddress;
	
	public String getGovtOffName() {
		return govtOffName;
	}
	public void setGovtOffName(String govtOffName) {
		this.govtOffName = govtOffName;
	}
	public String getGovtOffDesg() {
		return govtOffDesg;
	}
	public void setGovtOffDesg(String govtOffDesg) {
		this.govtOffDesg = govtOffDesg;
	}
	public String getGovtOffAddress() {
		return govtOffAddress;
	}
	public void setGovtOffAddress(String govtOffAddress) {
		this.govtOffAddress = govtOffAddress;
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
	public String getPartyChk() {
		return partyChk;
	}
	public void setPartyChk(String partyChk) {
		this.partyChk = partyChk;
	}
	public ArrayList getPartyList() {
		return partyList;
	}
	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}
	
	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @return
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param spouseName
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	/**
	 * @return
	 */
	public String getCaste() {
		return caste;
	}
	/**
	 * @param caste
	 */
	public void setCaste(String caste) {
		this.caste = caste;
	}
	/**
	 * @return
	 */
	public String getReligion() {
		return religion;
	}
	/**
	 * @param religion
	 */
	public void setReligion(String religion) {
		this.religion = religion;
	}
	/**
	 * @return
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality
	 */
	public void setNationality(String _nationality) {
		nationality = _nationality;
	}
	/**
	 * @return
	 */
	public String getPhysicallChallenged() {
		return physicallChallenged;
	}
	/**
	 * @param physicallChallenged
	 */
	public void setPhysicallChallenged(String physicallChallenged) {
		this.physicallChallenged = physicallChallenged;
	}
	/**
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return
	 */
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return
	 */
	public String getLandNo() {
		return landNo;
	}
	/**
	 * @param landNo
	 */
	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}
	/**
	 * @return
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return
	 */
	public String getProofTypeId() {
		return proofTypeId;
	}
	/**
	 * @param proofTypeId
	 */
	public void setProofTypeId(String proofTypeId) {
		this.proofTypeId = proofTypeId;
	}
	/**
	 * @return
	 */
	public String getProofNumber() {
		return proofNumber;
	}
	/**
	 * @param proofNumber
	 */
	public void setProofNumber(String proofNumber) {
		this.proofNumber = proofNumber;
	}
	/**
	 * @return
	 */
	public String getOcupation() {
		return ocupation;
	}
	/**
	 * @param ocupation
	 */
	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}
	/**
	 * @return
	 */
	public String getPropShare() {
		return propShare;
	}
	/**
	 * @param propShare
	 */
	public void setPropShare(String propShare) {
		this.propShare = propShare;
	}
	/**
	 * @return
	 */
	public String getThumbImg() {
		return thumbImg;
	}
	/**
	 * @param thumbImg
	 */
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	/**
	 * @return
	 */
	public String getPhotoImg() {
		return photoImg;
	}
	/**
	 * @param photoImg
	 */
	public void setPhotoImg(String photoImg) {
		this.photoImg = photoImg;
	}
	/**
	 * @return
	 */
	public String getSignImg() {
		return signImg;
	}
	/**
	 * @param signImg
	 */
	public void setSignImg(String signImg) {
		this.signImg = signImg;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}	
	

}
