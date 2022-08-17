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
package com.wipro.igrs.poa.dto;

import java.util.ArrayList; 
import java.io.Serializable;

public class POAAuthenticationDTO implements Serializable{
	
	private String awderFName;
	private String awderMName;
	private String awderLName;
	private String awderGender;
	private int awderAge;
	private String awderFatherName;
	private String awderMotherName;
	private String awderSpouseName;
	private String awderCaste;
	private String awderCasteName;
	private String awderReligion;
	private String awderReligionName;	
	private String awderNationality;
	private String awderPhysicallyChallnged;
	private String awderAddress;
	private String awderCountry;
	private String awderCountryName;
	private String awderState;
	private String awderStateName;
	private String awderDist;
	private String awderDistName;
	private String awderPostPin;
	private String awderPhoneNo;
	private String awderMobilNo;
	private String awderEmail;
	private String awderPhotoIDNo;
	private String awderPhotoId;
	private String awderPhotoName;
	private String awderBank;
	private String awderBankAddress;
	private String firstCountry;
	private String firstDist;
	private String awaderId;
	private String awaderCategory;
	private String awderPartyId;
	
	private String poaStatus;	
	private String category;
	private String poaRegNo;
	private String poaEstamCode;
	private String estampAmt;
	private String purpose;
	private String regNo;	
	private String regCreatedDate;
	private String executeDate;
	private String presentationDate;
	private String remarks;
	private String fee;
	private String others;
	private String totalAmt;
	private String popupRegNo;
	private String popHidRegNo;
	private String createPoaNext;
	private String poaNextview;
	private String searchPoaId;
	private String poaFromDate;
	private String poaToDate;
	private String createdDate;
	private String poaTxnId;
	private String orgName;
	private String thumImpression;
	private String partyphoto;
	private String partySignature;
	private String payTxnId;
	private String otherFee;
	private String totFee;
	private String action;
	private String poaId;
	private String poaCreateDate;
	
	
	private String firstState;
	private String secondState;
	private String secondCountry;
	private String secondDist;
		
	private String awdeeFName;
	private String awdeeMName;
	private String awdeeLName;
	private String awdeeGender;
	private int awdeeAge;
	private String awdeeFatherName;
	private String awdeeMotherName;
	private String awdeeSpouseName;
	private String awdeeCaste;
	private String awdeeReligion;
	private String awdeeNationality;
	private String awdeePhysicallyChallenged;
	private String awdeeAddress;
	private String awdeeCountry;
	private String awdeeState;
	private String awdeeDist;
	private String awdeePostPin;
	private String awdeePhoneNo;
	private String awdeeMobileNo;
	private String awdeeEmail;
	private String awdeePhotoIDNo;
	private String awdeePhotoId;
	private String awdeePhotoName;
	private String awdeeBank;
	private String awdeeBankAddress; 
	private String awdeeStateId;
	private String awdeeStateName;
	private String awdeeId;
	private String awadeeCategory;
	private String awdeePartyId;
	
	
	private String countryId;
	private String countryName;
	
	private String stateId;
	private String stateName;
	
	private String distId;
	private String distName;
	
	private String photoId;
	private String photoName;
	
	private String deedId;
	private String deedName;
	
	private String casteId;
	private String casteName;
	
	private String religionId;
	private String religionName;
	
	private String categoryId;
	private String categoryName;
	
    private String authCreation;	
    
	
	private ArrayList countryList;
	private ArrayList awderCountryList;
	private ArrayList awdeeCountryList;	
	private ArrayList stateList;
	private ArrayList awderStateList;
	private ArrayList awdeeStateList;
	private ArrayList distList;	
	private ArrayList awderDistList;
	private ArrayList awdeeDistList;
	private ArrayList awderCasteList;
	private ArrayList awdeeCasteList;	
	private ArrayList awderReligionList;
	private ArrayList awdeeReligionList;
	private ArrayList awderPhotoList;
	private ArrayList awdeePhotoList;
	private ArrayList awderCatoryId;
	private ArrayList awderCategoryList;
	private ArrayList awdeeCategoryList;
	private ArrayList photoList;
	private ArrayList regList;
	private ArrayList deedList;
	private ArrayList poaList;
	
	/**
	 * @return the poaList
	 */
	public ArrayList getPoaList() {
		return poaList;
	}
	/**
	 * @param poaList the poaList to set
	 */
	public void setPoaList(ArrayList poaList) {
		this.poaList = poaList;
	}
	/**
	 * @return the awderFName
	 */
	public String getAwderFName() {
		return awderFName;
	}
	/**
	 * @param awderFName the awderFName to set
	 */
	public void setAwderFName(String awderFName) {
		this.awderFName = awderFName;
	}
	/**
	 * @return the awderMName
	 */
	public String getAwderMName() {
		return awderMName;
	}
	/**
	 * @param awderMName the awderMName to set
	 */
	public void setAwderMName(String awderMName) {
		this.awderMName = awderMName;
	}
	/**
	 * @return the awderLName
	 */
	public String getAwderLName() {
		return awderLName;
	}
	/**
	 * @param awderLName the awderLName to set
	 */
	public void setAwderLName(String awderLName) {
		this.awderLName = awderLName;
	}
	/**
	 * @return the awderGender
	 */
	public String getAwderGender() {
		return awderGender;
	}
	/**
	 * @param awderGender the awderGender to set
	 */
	public void setAwderGender(String awderGender) {
		this.awderGender = awderGender;
	}
	/**
	 * @return the awderAge
	 */
	public int getAwderAge() {
		return awderAge;
	}
	/**
	 * @param awderAge the awderAge to set
	 */
	public void setAwderAge(int awderAge) {
		this.awderAge = awderAge;
	}
	/**
	 * @return the awderFatherName
	 */
	public String getAwderFatherName() {
		return awderFatherName;
	}
	/**
	 * @param awderFatherName the awderFatherName to set
	 */
	public void setAwderFatherName(String awderFatherName) {
		this.awderFatherName = awderFatherName;
	}
	/**
	 * @return the awderMotherName
	 */
	public String getAwderMotherName() {
		return awderMotherName;
	}
	/**
	 * @param awderMotherName the awderMotherName to set
	 */
	public void setAwderMotherName(String awderMotherName) {
		this.awderMotherName = awderMotherName;
	}
	/**
	 * @return the awderSpouseName
	 */
	public String getAwderSpouseName() {
		return awderSpouseName;
	}
	/**
	 * @param awderSpouseName the awderSpouseName to set
	 */
	public void setAwderSpouseName(String awderSpouseName) {
		this.awderSpouseName = awderSpouseName;
	}
	/**
	 * @return the awderCaste
	 */
	public String getAwderCaste() {
		return awderCaste;
	}
	/**
	 * @param awderCaste the awderCaste to set
	 */
	public void setAwderCaste(String awderCaste) {
		this.awderCaste = awderCaste;
	}
	/**
	 * @return the awderReligion
	 */
	public String getAwderReligion() {
		return awderReligion;
	}
	/**
	 * @param awderReligion the awderReligion to set
	 */
	public void setAwderReligion(String awderReligion) {
		this.awderReligion = awderReligion;
	}
	/**
	 * @return the awderNationality
	 */
	public String getAwderNationality() {
		return awderNationality;
	}
	/**
	 * @param awderNationality the awderNationality to set
	 */
	public void setAwderNationality(String awderNationality) {
		this.awderNationality = awderNationality;
	}	

	/**
	 * @return the awderAddress
	 */
	public String getAwderAddress() {
		return awderAddress;
	}
	/**
	 * @param awderAddress the awderAddress to set
	 */
	public void setAwderAddress(String awderAddress) {
		this.awderAddress = awderAddress;
	}
	/**
	 * @return the awderPostPin
	 */
	public String getAwderPostPin() {
		return awderPostPin;
	}
	/**
	 * @param awderPostPin the awderPostPin to set
	 */
	public void setAwderPostPin(String awderPostPin) {
		this.awderPostPin = awderPostPin;
	}
	/**
	 * @return the awderPhoneNo
	 */
	public String getAwderPhoneNo() {
		return awderPhoneNo;
	}
	/**
	 * @param awderPhoneNo the awderPhoneNo to set
	 */
	public void setAwderPhoneNo(String awderPhoneNo) {
		this.awderPhoneNo = awderPhoneNo;
	}
	/**
	 * @return the awderMobilNo
	 */
	public String getAwderMobilNo() {
		return awderMobilNo;
	}
	/**
	 * @param awderMobilNo the awderMobilNo to set
	 */
	public void setAwderMobilNo(String awderMobilNo) {
		this.awderMobilNo = awderMobilNo;
	}
	/**
	 * @return the awderEmail
	 */
	public String getAwderEmail() {
		return awderEmail;
	}
	/**
	 * @param awderEmail the awderEmail to set
	 */
	public void setAwderEmail(String awderEmail) {
		this.awderEmail = awderEmail;
	}
	/**
	 * @return the awderPhotoIDNo
	 */
	public String getAwderPhotoIDNo() {
		return awderPhotoIDNo;
	}
	/**
	 * @param awderPhotoIDNo the awderPhotoIDNo to set
	 */
	public void setAwderPhotoIDNo(String awderPhotoIDNo) {
		this.awderPhotoIDNo = awderPhotoIDNo;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the poaRegNo
	 */
	public String getPoaRegNo() {
		return poaRegNo;
	}
	/**
	 * @param poaRegNo the poaRegNo to set
	 */
	public void setPoaRegNo(String poaRegNo) {
		this.poaRegNo = poaRegNo;
	}
	/**
	 * @return the poaEstamCode
	 */
	public String getPoaEstamCode() {
		return poaEstamCode;
	}
	/**
	 * @param poaEstamCode the poaEstamCode to set
	 */
	public void setPoaEstamCode(String poaEstamCode) {
		this.poaEstamCode = poaEstamCode;
	}
	/**
	 * @return the estampAmt
	 */
	public String getEstampAmt() {
		return estampAmt;
	}
	/**
	 * @param estampAmt the estampAmt to set
	 */
	public void setEstampAmt(String estampAmt) {
		this.estampAmt = estampAmt;
	}
	/**
	 * @return the purpose
	 */
	public String getPurpose() {
		return purpose;
	}
	/**
	 * @param purpose the purpose to set
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	/**
	 * @return the regNo
	 */
	public String getRegNo() {
		return regNo;
	}
	/**
	 * @param regNo the regNo to set
	 */
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	/**
	 * @return the regCreatedDate
	 */
	public String getRegCreatedDate() {
		return regCreatedDate;
	}
	/**
	 * @param regCreatedDate the regCreatedDate to set
	 */
	public void setRegCreatedDate(String regCreatedDate) {
		this.regCreatedDate = regCreatedDate;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}
	/**
	 * @param fee the fee to set
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}
	/**
	 * @return the others
	 */
	public String getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}
	/**
	 * @return the totalAmt
	 */
	public String getTotalAmt() {
		return totalAmt;
	}
	/**
	 * @param totalAmt the totalAmt to set
	 */
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	/**
	 * @return the awdeeFName
	 */
	public String getAwdeeFName() {
		return awdeeFName;
	}
	/**
	 * @param awdeeFName the awdeeFName to set
	 */
	public void setAwdeeFName(String awdeeFName) {
		this.awdeeFName = awdeeFName;
	}
	/**
	 * @return the awdeeMName
	 */
	public String getAwdeeMName() {
		return awdeeMName;
	}
	/**
	 * @param awdeeMName the awdeeMName to set
	 */
	public void setAwdeeMName(String awdeeMName) {
		this.awdeeMName = awdeeMName;
	}
	/**
	 * @return the awdeeLName
	 */
	public String getAwdeeLName() {
		return awdeeLName;
	}
	/**
	 * @param awdeeLName the awdeeLName to set
	 */
	public void setAwdeeLName(String awdeeLName) {
		this.awdeeLName = awdeeLName;
	}
	/**
	 * @return the awdeeGender
	 */
	public String getAwdeeGender() {
		return awdeeGender;
	}
	/**
	 * @param awdeeGender the awdeeGender to set
	 */
	public void setAwdeeGender(String awdeeGender) {
		this.awdeeGender = awdeeGender;
	}
	/**
	 * @return the awdeeAge
	 */
	public int getAwdeeAge() {
		return awdeeAge;
	}
	/**
	 * @param awdeeAge the awdeeAge to set
	 */
	public void setAwdeeAge(int awdeeAge) {
		this.awdeeAge = awdeeAge;
	}
	/**
	 * @return the awdeeFatherName
	 */
	public String getAwdeeFatherName() {
		return awdeeFatherName;
	}
	/**
	 * @param awdeeFatherName the awdeeFatherName to set
	 */
	public void setAwdeeFatherName(String awdeeFatherName) {
		this.awdeeFatherName = awdeeFatherName;
	}
	/**
	 * @return the awdeeMotherName
	 */
	public String getAwdeeMotherName() {
		return awdeeMotherName;
	}
	/**
	 * @param awdeeMotherName the awdeeMotherName to set
	 */
	public void setAwdeeMotherName(String awdeeMotherName) {
		this.awdeeMotherName = awdeeMotherName;
	}
	/**
	 * @return the awdeeSpouseName
	 */
	public String getAwdeeSpouseName() {
		return awdeeSpouseName;
	}
	/**
	 * @param awdeeSpouseName the awdeeSpouseName to set
	 */
	public void setAwdeeSpouseName(String awdeeSpouseName) {
		this.awdeeSpouseName = awdeeSpouseName;
	}
	/**
	 * @return the awdeeCaste
	 */
	public String getAwdeeCaste() {
		return awdeeCaste;
	}
	/**
	 * @param awdeeCaste the awdeeCaste to set
	 */
	public void setAwdeeCaste(String awdeeCaste) {
		this.awdeeCaste = awdeeCaste;
	}
	/**
	 * @return the awdeeReligion
	 */
	public String getAwdeeReligion() {
		return awdeeReligion;
	}
	/**
	 * @param awdeeReligion the awdeeReligion to set
	 */
	public void setAwdeeReligion(String awdeeReligion) {
		this.awdeeReligion = awdeeReligion;
	}
	/**
	 * @return the awdeeNationality
	 */
	public String getAwdeeNationality() {
		return awdeeNationality;
	}
	/**
	 * @param awdeeNationality the awdeeNationality to set
	 */
	public void setAwdeeNationality(String awdeeNationality) {
		this.awdeeNationality = awdeeNationality;
	}
	

	/**
	 * @return the awdeePhysicallyChallenged
	 */
	public String getAwdeePhysicallyChallenged() {
		return awdeePhysicallyChallenged;
	}
	/**
	 * @param awdeePhysicallyChallenged the awdeePhysicallyChallenged to set
	 */
	public void setAwdeePhysicallyChallenged(String awdeePhysicallyChallenged) {
		this.awdeePhysicallyChallenged = awdeePhysicallyChallenged;
	}
	/**
	 * @return the awdeeAddress
	 */
	public String getAwdeeAddress() {
		return awdeeAddress;
	}
	/**
	 * @param awdeeAddress the awdeeAddress to set
	 */
	public void setAwdeeAddress(String awdeeAddress) {
		this.awdeeAddress = awdeeAddress;
	}
	/**
	 * @return the awdeePostPin
	 */
	public String getAwdeePostPin() {
		return awdeePostPin;
	}
	/**
	 * @param awdeePostPin the awdeePostPin to set
	 */
	public void setAwdeePostPin(String awdeePostPin) {
		this.awdeePostPin = awdeePostPin;
	}
	/**
	 * @return the awdeePhoneNo
	 */
	public String getAwdeePhoneNo() {
		return awdeePhoneNo;
	}
	/**
	 * @param awdeePhoneNo the awdeePhoneNo to set
	 */
	public void setAwdeePhoneNo(String awdeePhoneNo) {
		this.awdeePhoneNo = awdeePhoneNo;
	}

	/**
	 * @return the awdeeEmail
	 */
	public String getAwdeeEmail() {
		return awdeeEmail;
	}
	/**
	 * @param awdeeEmail the awdeeEmail to set
	 */
	public void setAwdeeEmail(String awdeeEmail) {
		this.awdeeEmail = awdeeEmail;
	}
	/**
	 * @return the awdeePhotoIDNo
	 */
	public String getAwdeePhotoIDNo() {
		return awdeePhotoIDNo;
	}
	/**
	 * @param awdeePhotoIDNo the awdeePhotoIDNo to set
	 */
	public void setAwdeePhotoIDNo(String awdeePhotoIDNo) {
		this.awdeePhotoIDNo = awdeePhotoIDNo;
	}
	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	/**
	 * @return the distId
	 */
	public String getDistId() {
		return distId;
	}
	/**
	 * @param distId the distId to set
	 */
	public void setDistId(String distId) {
		this.distId = distId;
	}
	/**
	 * @return the distName
	 */
	public String getDistName() {
		return distName;
	}
	/**
	 * @param distName the distName to set
	 */
	public void setDistName(String distName) {
		this.distName = distName;
	}
	/**
	 * @return the photoId
	 */
	public String getPhotoId() {
		return photoId;
	}
	/**
	 * @param photoId the photoId to set
	 */
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	/**
	 * @return the photoName
	 */
	public String getPhotoName() {
		return photoName;
	}
	/**
	 * @param photoName the photoName to set
	 */
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	/**
	 * @return the deedId
	 */
	public String getDeedId() {
		return deedId;
	}
	/**
	 * @param deedId the deedId to set
	 */
	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}
	
	/**
	 * @return the authCreation
	 */
	public String getAuthCreation() {
		return authCreation;
	}
	/**
	 * @param authCreation the authCreation to set
	 */
	public void setAuthCreation(String authCreation) {
		this.authCreation = authCreation;
	}
	/**
	 * @return the countryList
	 */
	public ArrayList getCountryList() {
		return countryList;
	}
	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}
	/**
	 * @return the stateList
	 */
	public ArrayList getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}
	/**
	 * @return the distList
	 */
	public ArrayList getDistList() {
		return distList;
	}
	/**
	 * @param distList the distList to set
	 */
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	/**
	 * @return the photoList
	 */
	public ArrayList getPhotoList() {
		return photoList;
	}
	/**
	 * @param photoList the photoList to set
	 */
	public void setPhotoList(ArrayList photoList) {
		this.photoList = photoList;
	}
	/**
	 * @return the regList
	 */
	public ArrayList getRegList() {
		return regList;
	}
	/**
	 * @param regList the regList to set
	 */
	public void setRegList(ArrayList regList) {
		this.regList = regList;
	}
	/**
	 * @return the deedList
	 */
	public ArrayList getDeedList() {
		return deedList;
	}
	/**
	 * @param deedList the deedList to set
	 */
	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}
	/**
	 * @return the deedName
	 */
	public String getDeedName() {
		return deedName;
	}
	/**
	 * @param deedName the deedName to set
	 */
	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}
	/**
	 * @return the awderPhysicallyChallnged
	 */
	public String getAwderPhysicallyChallnged() {
		return awderPhysicallyChallnged;
	}
	/**
	 * @param awderPhysicallyChallnged the awderPhysicallyChallnged to set
	 */
	public void setAwderPhysicallyChallnged(String awderPhysicallyChallnged) {
		this.awderPhysicallyChallnged = awderPhysicallyChallnged;
	}
	/**
	 * @return the awderBank
	 */
	public String getAwderBank() {
		return awderBank;
	}
	/**
	 * @param awderBank the awderBank to set
	 */
	public void setAwderBank(String awderBank) {
		this.awderBank = awderBank;
	}
	/**
	 * @return the awderBankAddress
	 */
	public String getAwderBankAddress() {
		return awderBankAddress;
	}
	/**
	 * @param awderBankAddress the awderBankAddress to set
	 */
	public void setAwderBankAddress(String awderBankAddress) {
		this.awderBankAddress = awderBankAddress;
	}
	/**
	 * @return the awdeeBank
	 */
	public String getAwdeeBank() {
		return awdeeBank;
	}
	/**
	 * @param awdeeBank the awdeeBank to set
	 */
	public void setAwdeeBank(String awdeeBank) {
		this.awdeeBank = awdeeBank;
	}
	/**
	 * @return the awdeeBankAddress
	 */
	public String getAwdeeBankAddress() {
		return awdeeBankAddress;
	}
	/**
	 * @param awdeeBankAddress the awdeeBankAddress to set
	 */
	public void setAwdeeBankAddress(String awdeeBankAddress) {
		this.awdeeBankAddress = awdeeBankAddress;
	}
	/**
	 * @return the executeDate
	 */
	public String getExecuteDate() {
		return executeDate;
	}
	/**
	 * @param executeDate the executeDate to set
	 */
	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}
	/**
	 * @return the presentationDate
	 */
	public String getPresentationDate() {
		return presentationDate;
	}
	/**
	 * @param presentationDate the presentationDate to set
	 */
	public void setPresentationDate(String presentationDate) {
		this.presentationDate = presentationDate;
	}
	/**
	 * @return the awderCountry
	 */
	public String getAwderCountry() {
		return awderCountry;
	}
	/**
	 * @param awderCountry the awderCountry to set
	 */
	public void setAwderCountry(String awderCountry) {
		this.awderCountry = awderCountry;
	}
	/**
	 * @return the awderState
	 */
	public String getAwderState() {
		return awderState;
	}
	/**
	 * @param awderState the awderState to set
	 */
	public void setAwderState(String awderState) {
		this.awderState = awderState;
	}
	/**
	 * @return the awderDist
	 */
	public String getAwderDist() {
		return awderDist;
	}
	/**
	 * @param awderDist the awderDist to set
	 */
	public void setAwderDist(String awderDist) {
		this.awderDist = awderDist;
	}
	/**
	 * @return the awdeeCountry
	 */
	public String getAwdeeCountry() {
		return awdeeCountry;
	}
	/**
	 * @param awdeeCountry the awdeeCountry to set
	 */
	public void setAwdeeCountry(String awdeeCountry) {
		this.awdeeCountry = awdeeCountry;
	}
	/**
	 * @return the awdeeState
	 */
	public String getAwdeeState() {
		return awdeeState;
	}
	/**
	 * @param awdeeState the awdeeState to set
	 */
	public void setAwdeeState(String awdeeState) {
		this.awdeeState = awdeeState;
	}
	/**
	 * @return the awdeeDist
	 */
	public String getAwdeeDist() {
		return awdeeDist;
	}
	/**
	 * @param awdeeDist the awdeeDist to set
	 */
	public void setAwdeeDist(String awdeeDist) {
		this.awdeeDist = awdeeDist;
	}
	
	/**
	 * @return the awderStateList
	 */
	public ArrayList getAwderStateList() {
		return awderStateList;
	}
	/**
	 * @param awderStateList the awderStateList to set
	 */
	public void setAwderStateList(ArrayList awderStateList) {
		this.awderStateList = awderStateList;
	}
	/**
	 * @return the awdeeStateList
	 */
	public ArrayList getAwdeeStateList() {
		return awdeeStateList;
	}
	/**
	 * @param awdeeStateList the awdeeStateList to set
	 */
	public void setAwdeeStateList(ArrayList awdeeStateList) {
		this.awdeeStateList = awdeeStateList;
	}
	/**
	 * @return the awderDistList
	 */
	public ArrayList getAwderDistList() {
		return awderDistList;
	}
	/**
	 * @param awderDistList the awderDistList to set
	 */
	public void setAwderDistList(ArrayList awderDistList) {
		this.awderDistList = awderDistList;
	}
	/**
	 * @return the awdeeDistList
	 */
	public ArrayList getAwdeeDistList() {
		return awdeeDistList;
	}
	/**
	 * @param awdeeDistList the awdeeDistList to set
	 */
	public void setAwdeeDistList(ArrayList awdeeDistList) {
		this.awdeeDistList = awdeeDistList;
	}
	/**
	 * @return the awderCountryList
	 */
	public ArrayList getAwderCountryList() {
		return awderCountryList;
	}
	/**
	 * @param awderCountryList the awderCountryList to set
	 */
	public void setAwderCountryList(ArrayList awderCountryList) {
		this.awderCountryList = awderCountryList;
	}
	/**
	 * @return the awdeeCountryList
	 */
	public ArrayList getAwdeeCountryList() {
		return awdeeCountryList;
	}
	/**
	 * @param awdeeCountryList the awdeeCountryList to set
	 */
	public void setAwdeeCountryList(ArrayList awdeeCountryList) {
		this.awdeeCountryList = awdeeCountryList;
	}
	/**
	 * @return the awdeeMobileNo
	 */
	public String getAwdeeMobileNo() {
		return awdeeMobileNo;
	}
	/**
	 * @param awdeeMobileNo the awdeeMobileNo to set
	 */
	public void setAwdeeMobileNo(String awdeeMobileNo) {
		this.awdeeMobileNo = awdeeMobileNo;
	}
	/**
	 * @return the popupRegNo
	 */
	public String getPopupRegNo() {
		return popupRegNo;
	}
	/**popupRegNO
	 * @param popupRegNo the popupRegNo to set
	 */
	public void setPopupRegNo(String popupRegNo) {
		this.popupRegNo = popupRegNo;
	}
	/**
	 * @return the popHidRegNo
	 */
	public String getPopHidRegNo() {
		return popHidRegNo;
	}
	/**
	 * @param popHidRegNo the popHidRegNo to set
	 */
	public void setPopHidRegNo(String popHidRegNo) {
		this.popHidRegNo = popHidRegNo;
	}
	/**
	 * @return the casteId
	 */
	public String getCasteId() {
		return casteId;
	}
	/**
	 * @param casteId the casteId to set
	 */
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}
	/**
	 * @return the casteName
	 */
	public String getCasteName() {
		return casteName;
	}
	/**
	 * @param casteName the casteName to set
	 */
	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}
	/**
	 * @return the religionId
	 */
	public String getReligionId() {
		return religionId;
	}
	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}
	/**
	 * @return the religionName
	 */
	public String getReligionName() {
		return religionName;
	}
	/**
	 * @param religionName the religionName to set
	 */
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
	/**
	 * @return the awderCasteList
	 */
	public ArrayList getAwderCasteList() {
		return awderCasteList;
	}
	/**
	 * @param awderCasteList the awderCasteList to set
	 */
	public void setAwderCasteList(ArrayList awderCasteList) {
		this.awderCasteList = awderCasteList;
	}
	/**
	 * @return the awdeeCasteList
	 */
	public ArrayList getAwdeeCasteList() {
		return awdeeCasteList;
	}
	/**
	 * @param awdeeCasteList the awdeeCasteList to set
	 */
	public void setAwdeeCasteList(ArrayList awdeeCasteList) {
		this.awdeeCasteList = awdeeCasteList;
	}
	/**
	 * @return the awderReligionList
	 */
	public ArrayList getAwderReligionList() {
		return awderReligionList;
	}
	/**
	 * @param awderReligionList the awderReligionList to set
	 */
	public void setAwderReligionList(ArrayList awderReligionList) {
		this.awderReligionList = awderReligionList;
	}
	/**
	 * @return the awdeeReligionList
	 */
	public ArrayList getAwdeeReligionList() {
		return awdeeReligionList;
	}
	/**
	 * @param awdeeReligionList the awdeeReligionList to set
	 */
	public void setAwdeeReligionList(ArrayList awdeeReligionList) {
		this.awdeeReligionList = awdeeReligionList;
	}
	/**
	 * @return the createPoaNext
	 */
	public String getCreatePoaNext() {
		return createPoaNext;
	}
	/**
	 * @param createPoaNext the createPoaNext to set
	 */
	public void setCreatePoaNext(String createPoaNext) {
		this.createPoaNext = createPoaNext;
	}
	/**
	 * @return the awderStateName
	 */
	public String getAwderStateName() {
		return awderStateName;
	}
	/**
	 * @param awderStateName the awderStateName to set
	 */
	public void setAwderStateName(String awderStateName) {
		this.awderStateName = awderStateName;
	}
	/**
	 * @return the awderCountryName
	 */
	public String getAwderCountryName() {
		return awderCountryName;
	}
	/**
	 * @param awderCountryName the awderCountryName to set
	 */
	public void setAwderCountryName(String awderCountryName) {
		this.awderCountryName = awderCountryName;
	}
	/**
	 * @return the awderDistName
	 */
	public String getAwderDistName() {
		return awderDistName;
	}
	/**
	 * @param awderDistName the awderDistName to set
	 */
	public void setAwderDistName(String awderDistName) {
		this.awderDistName = awderDistName;
	}
	/**
	 * @return the awderCasteName
	 */
	public String getAwderCasteName() {
		return awderCasteName;
	}
	/**
	 * @param awderCasteName the awderCasteName to set
	 */
	public void setAwderCasteName(String awderCasteName) {
		this.awderCasteName = awderCasteName;
	}
	/**
	 * @return the awderReligionName
	 */
	public String getAwderReligionName() {
		return awderReligionName;
	}
	/**
	 * @param awderReligionName the awderReligionName to set
	 */
	public void setAwderReligionName(String awderReligionName) {
		this.awderReligionName = awderReligionName;
	}
	/**
	 * @return the awderPhotoId
	 */
	public String getAwderPhotoId() {
		return awderPhotoId;
	}
	/**
	 * @param awderPhotoId the awderPhotoId to set
	 */
	public void setAwderPhotoId(String awderPhotoId) {
		this.awderPhotoId = awderPhotoId;
	}
	/**
	 * @return the awderPhotoName
	 */
	public String getAwderPhotoName() {
		return awderPhotoName;
	}
	/**
	 * @param awderPhotoName the awderPhotoName to set
	 */
	public void setAwderPhotoName(String awderPhotoName) {
		this.awderPhotoName = awderPhotoName;
	}
	/**
	 * @return the awdeePhotoId
	 */
	public String getAwdeePhotoId() {
		return awdeePhotoId;
	}
	/**
	 * @param awdeePhotoId the awdeePhotoId to set
	 */
	public void setAwdeePhotoId(String awdeePhotoId) {
		this.awdeePhotoId = awdeePhotoId;
	}
	/**
	 * @return the awdeePhotoName
	 */
	public String getAwdeePhotoName() {
		return awdeePhotoName;
	}
	/**
	 * @param awdeePhotoName the awdeePhotoName to set
	 */
	public void setAwdeePhotoName(String awdeePhotoName) {
		this.awdeePhotoName = awdeePhotoName;
	}
	/**
	 * @return the awderPhotoList
	 */
	public ArrayList getAwderPhotoList() {
		return awderPhotoList;
	}
	/**
	 * @param awderPhotoList the awderPhotoList to set
	 */
	public void setAwderPhotoList(ArrayList awderPhotoList) {
		this.awderPhotoList = awderPhotoList;
	}
	/**
	 * @return the awdeePhotoList
	 */
	public ArrayList getAwdeePhotoList() {
		return awdeePhotoList;
	}
	/**
	 * @param awdeePhotoList the awdeePhotoList to set
	 */
	public void setAwdeePhotoList(ArrayList awdeePhotoList) {
		this.awdeePhotoList = awdeePhotoList;
	}
	
	/**
	 * @return the awdeeStateId
	 */
	public String getAwdeeStateId() {
		return awdeeStateId;
	}
	/**
	 * @param awdeeStateId the awdeeStateId to set
	 */
	public void setAwdeeStateId(String awdeeStateId) {
		this.awdeeStateId = awdeeStateId;
	}
	/**
	 * @return the awdeeStateName
	 */
	public String getAwdeeStateName() {
		return awdeeStateName;
	}
	/**
	 * @param awdeeStateName the awdeeStateName to set
	 */
	public void setAwdeeStateName(String awdeeStateName) {
		this.awdeeStateName = awdeeStateName;
	}
	/**
	 * @return the firstState
	 */
	public String getFirstState() {
		return firstState;
	}
	/**
	 * @param firstState the firstState to set
	 */
	public void setFirstState(String firstState) {
		this.firstState = firstState;
	}
	/**
	 * @return the secondState
	 */
	public String getSecondState() {
		return secondState;
	}
	/**
	 * @param secondState the secondState to set
	 */
	public void setSecondState(String secondState) {
		this.secondState = secondState;
	}
	/**
	 * @return the firstCountry
	 */
	public String getFirstCountry() {
		return firstCountry;
	}
	/**
	 * @param firstCountry the firstCountry to set
	 */
	public void setFirstCountry(String firstCountry) {
		this.firstCountry = firstCountry;
	}
	/**
	 * @return the firstDist
	 */
	public String getFirstDist() {
		return firstDist;
	}
	/**
	 * @param firstDist the firstDist to set
	 */
	public void setFirstDist(String firstDist) {
		this.firstDist = firstDist;
	}
	/**
	 * @return the secondCountry
	 */
	public String getSecondCountry() {
		return secondCountry;
	}
	/**
	 * @param secondCountry the secondCountry to set
	 */
	public void setSecondCountry(String secondCountry) {
		this.secondCountry = secondCountry;
	}
	/**
	 * @return the secondDist
	 */
	public String getSecondDist() {
		return secondDist;
	}
	/**
	 * @param secondDist the secondDist to set
	 */
	public void setSecondDist(String secondDist) {
		this.secondDist = secondDist;
	}
	/**
	 * @return the awaderId
	 */
	public String getAwaderId() {
		return awaderId;
	}
	/**
	 * @param awaderId the awaderId to set
	 */
	public void setAwaderId(String awaderId) {
		this.awaderId = awaderId;
	}
	/**
	 * @return the awdeeId
	 */
	public String getAwdeeId() {
		return awdeeId;
	}
	/**
	 * @param awdeeId the awdeeId to set
	 */
	public void setAwdeeId(String awdeeId) {
		this.awdeeId = awdeeId;
	}
	/**
	 * @return the awaderCategory
	 */
	public String getAwaderCategory() {
		return awaderCategory;
	}
	/**
	 * @param awaderCategory the awaderCategory to set
	 */
	public void setAwaderCategory(String awaderCategory) {
		this.awaderCategory = awaderCategory;
	}
	/**
	 * @return the awadeeCategory
	 */
	public String getAwadeeCategory() {
		return awadeeCategory;
	}
	/**
	 * @param awadeeCategory the awadeeCategory to set
	 */
	public void setAwadeeCategory(String awadeeCategory) {
		this.awadeeCategory = awadeeCategory;
	}
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return the awderCatoryId
	 */
	public ArrayList getAwderCatoryId() {
		return awderCatoryId;
	}
	/**
	 * @param awderCatoryId the awderCatoryId to set
	 */
	public void setAwderCatoryId(ArrayList awderCatoryId) {
		this.awderCatoryId = awderCatoryId;
	}
	/**
	 * @return the awderCategoryList
	 */
	public ArrayList getAwderCategoryList() {
		return awderCategoryList;
	}
	/**
	 * @param awderCategoryList the awderCategoryList to set
	 */
	public void setAwderCategoryList(ArrayList awderCategoryList) {
		this.awderCategoryList = awderCategoryList;
	}
	/**
	 * @return the awdeeCategoryList
	 */
	public ArrayList getAwdeeCategoryList() {
		return awdeeCategoryList;
	}
	/**
	 * @param awdeeCategoryList the awdeeCategoryList to set
	 */
	public void setAwdeeCategoryList(ArrayList awdeeCategoryList) {
		this.awdeeCategoryList = awdeeCategoryList;
	}
	/**
	 * @return the searchPoaId
	 */
	public String getSearchPoaId() {
		return searchPoaId;
	}
	/**
	 * @param searchPoaId the searchPoaId to set
	 */
	public void setSearchPoaId(String searchPoaId) {
		this.searchPoaId = searchPoaId;
	}
	/**
	 * @return the poaFromDate
	 */
	public String getPoaFromDate() {
		return poaFromDate;
	}
	/**
	 * @param poaFromDate the poaFromDate to set
	 */
	public void setPoaFromDate(String poaFromDate) {
		this.poaFromDate = poaFromDate;
	}
	/**
	 * @return the poaToDate
	 */
	public String getPoaToDate() {
		return poaToDate;
	}
	/**
	 * @param poaToDate the poaToDate to set
	 */
	public void setPoaToDate(String poaToDate) {
		this.poaToDate = poaToDate;
	}
	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the poaTxnId
	 */
	public String getPoaTxnId() {
		return poaTxnId;
	}
	/**
	 * @param poaTxnId the poaTxnId to set
	 */
	public void setPoaTxnId(String poaTxnId) {
		this.poaTxnId = poaTxnId;
	}
	/**
	 * @return the awderPartyId
	 */
	public String getAwderPartyId() {
		return awderPartyId;
	}
	/**
	 * @param awderPartyId the awderPartyId to set
	 */
	public void setAwderPartyId(String awderPartyId) {
		this.awderPartyId = awderPartyId;
	}
	/**
	 * @return the awdeePartyId
	 */
	public String getAwdeePartyId() {
		return awdeePartyId;
	}
	/**
	 * @param awdeePartyId the awdeePartyId to set
	 */
	public void setAwdeePartyId(String awdeePartyId) {
		this.awdeePartyId = awdeePartyId;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return the thumImpression
	 */
	public String getThumImpression() {
		return thumImpression;
	}
	/**
	 * @param thumImpression the thumImpression to set
	 */
	public void setThumImpression(String thumImpression) {
		this.thumImpression = thumImpression;
	}
	/**
	 * @return the partyphoto
	 */
	public String getPartyphoto() {
		return partyphoto;
	}
	/**
	 * @param partyphoto the partyphoto to set
	 */
	public void setPartyphoto(String partyphoto) {
		this.partyphoto = partyphoto;
	}
	/**
	 * @return the partySignature
	 */
	public String getPartySignature() {
		return partySignature;
	}
	/**
	 * @param partySignature the partySignature to set
	 */
	public void setPartySignature(String partySignature) {
		this.partySignature = partySignature;
	}
	/**
	 * @return the poaStatus
	 */
	public String getPoaStatus() {
		return poaStatus;
	}
	/**
	 * @param poaStatus the poaStatus to set
	 */
	public void setPoaStatus(String poaStatus) {
		this.poaStatus = poaStatus;
	}
	/**
	 * @return the payTxnId
	 */
	public String getPayTxnId() {
		return payTxnId;
	}
	/**
	 * @param payTxnId the payTxnId to set
	 */
	public void setPayTxnId(String payTxnId) {
		this.payTxnId = payTxnId;
	}
	/**
	 * @return the otherFee
	 */
	public String getOtherFee() {
		return otherFee;
	}
	/**
	 * @param otherFee the otherFee to set
	 */
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	/**
	 * @return the totFee
	 */
	public String getTotFee() {
		return totFee;
	}
	/**
	 * @param totFee the totFee to set
	 */
	public void setTotFee(String totFee) {
		this.totFee = totFee;
	}
	/**
	 * @return the poaNextview
	 */
	public String getPoaNextview() {
		return poaNextview;
	}
	/**
	 * @param poaNextview the poaNextview to set
	 */
	public void setPoaNextview(String poaNextview) {
		this.poaNextview = poaNextview;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return the poaId
	 */
	public String getPoaId() {
		return poaId;
	}
	/**
	 * @param poaId the poaId to set
	 */
	public void setPoaId(String poaId) {
		this.poaId = poaId;
	}
	/**
	 * @return the poaCreateDate
	 */
	public String getPoaCreateDate() {
		return poaCreateDate;
	}
	/**
	 * @param poaCreateDate the poaCreateDate to set
	 */
	public void setPoaCreateDate(String poaCreateDate) {
		this.poaCreateDate = poaCreateDate;
	}


	
	

}// End of Form
