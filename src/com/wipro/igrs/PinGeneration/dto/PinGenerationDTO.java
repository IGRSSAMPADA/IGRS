/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PinGenerationDTO.java
 * Author      :   Neesha
 * Description :   Represents the DTO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0            Neesha  15th Jan, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.PinGeneration.dto;

import java.util.ArrayList;
import java.io.File;


//import org.apache.struts.upload.FormFile;



/**
 * @author neegaga
 */

public class PinGenerationDTO {
	private String pinGenAction;
	private String registrationId; 
	private String status;
	private String regTxnNo;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private int noOfPins;
	private ArrayList genPinList;
	private String pinNum;
	private String confirmPIN;	
	private String oldPIN;
	private String updatedPIN;
	private String forwardpath;
	
	
	
	private String pinNo;
	private String pinReqTxnNo;
	private String pinReqStatus;
	private String userID;
	private String officeID;
    private String stateId;
    private String reqDate;
    private String lastUpdateDate;
    private String createUser;  
    private String districtId;
    private ArrayList districtList = new ArrayList();
    private ArrayList CountryList = new ArrayList();
    private ArrayList StateList = new ArrayList();
    private String commonID;
    private String idName;
   	private String countryId;
	private String radioApplicent;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String address;
	private String country;
	private String district;
	private String state;
	private String stateName;
	private String districtName;
	private String postelCode;
	private String phoneNumber;
	private String mobileNumber;
	private String emailID;
	private String listID;
	private String idProofNumber;
	private String registrationNumber;
	private String propertyTransId;
	private String propertyTransName;
	private String deedDocVerify;
	private String remarks;
	private String deathCertificate;
	private String mutationDocument;
	private File mutationfile;
	private File deathfile;
	private String relation;
	private ArrayList idlist=new ArrayList();
	private ArrayList propertylist=new ArrayList();
	private String regNoSearch;
	private ArrayList cloneSelectedItems=new ArrayList();
	private ArrayList selectedItems=new ArrayList();
	private String errorMsg;
	private String encRegDate;
	
	private String propertyTxnId;
	
	private String propertyTypeId;
	private String propertyTypeLabel;
	private String ownerType;
	private String organisationName;
	private String autherisedName;
	private ArrayList ownerDetails=new ArrayList();
	private String agentProofPath;
	public String getAgentProofPath() {
		return agentProofPath;
	}
	public void setAgentProofPath(String agentProofPath) {
		this.agentProofPath = agentProofPath;
	}
	public String getAgentProofNameDoc() {
		return agentProofNameDoc;
	}
	public void setAgentProofNameDoc(String agentProofNameDoc) {
		this.agentProofNameDoc = agentProofNameDoc;
	}
	public String getAgentProofNameDeath() {
		return agentProofNameDeath;
	}
	public void setAgentProofNameDeath(String agentProofNameDeath) {
		this.agentProofNameDeath = agentProofNameDeath;
	}
	public String getAgentProofNameMutation() {
		return agentProofNameMutation;
	}
	public void setAgentProofNameMutation(String agentProofNameMutation) {
		this.agentProofNameMutation = agentProofNameMutation;
	}
	public String getAgentProofNameRelation() {
		return agentProofNameRelation;
	}
	public void setAgentProofNameRelation(String agentProofNameRelation) {
		this.agentProofNameRelation = agentProofNameRelation;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	private String agentProofNameDoc;
	private String agentProofNameDeath;
	private String agentProofNameMutation;
	private String agentProofNameRelation;
	private String uniqueId;
	public String getAutherisedName() {
		return autherisedName;
	}
	public void setAutherisedName(String autherisedName) {
		this.autherisedName = autherisedName;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public String getPropertyTxnId() {
		return propertyTxnId;
	}
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}
	public String getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}
	public String getPropertyTypeLabel() {
		return propertyTypeLabel;
	}
	public void setPropertyTypeLabel(String propertyTypeLabel) {
		this.propertyTypeLabel = propertyTypeLabel;
	}
	public String getEncRegDate() {
		return encRegDate;
	}
	public void setEncRegDate(String encRegDate) {
		this.encRegDate = encRegDate;
	}
	public String getEncRegNo() {
		return encRegNo;
	}
	public void setEncRegNo(String encRegNo) {
		this.encRegNo = encRegNo;
	}
	private String encRegNo;
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public ArrayList getCloneSelectedItems() {
		return cloneSelectedItems;
	}
	public void setCloneSelectedItems(ArrayList cloneSelectedItems) {
		this.cloneSelectedItems = cloneSelectedItems;
	}
	public ArrayList getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}
	public String getRegNoSearch() {
		return regNoSearch;
	}
	public void setRegNoSearch(String regNoSearch) {
		this.regNoSearch = regNoSearch;
	}
	private String action;
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public ArrayList getIdlist() {
		return idlist;
	}
	public void setIdlist(ArrayList idlist) {
		this.idlist = idlist;
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
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostelCode() {
		return postelCode;
	}
	public void setPostelCode(String postelCode) {
		this.postelCode = postelCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getListID() {
		return listID;
	}
	public void setListID(String listID) {
		this.listID = listID;
	}
	public String getIdProofNumber() {
		return idProofNumber;
	}
	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getDeedDocVerify() {
		return deedDocVerify;
	}
	public void setDeedDocVerify(String deedDocVerify) {
		this.deedDocVerify = deedDocVerify;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRadioApplicent() {
		return radioApplicent;
	}
	public void setRadioApplicent(String radioApplicent) {
		this.radioApplicent = radioApplicent;
	}
	/**
	 * @return the forwardpath
	 */
	public String getForwardpath() {
		return forwardpath;
	}
	/**
	 * @param forwardpath the forwardpath to set
	 */
	public void setForwardpath(String forwardpath) {
		this.forwardpath = forwardpath;
	}
	/**
	 * @return the pinNum
	 */
	public String getPinNum() {
		return pinNum;
	}
	/**
	 * @param pinNum the pinNum to set
	 */
	public void setPinNum(String pinNum) {
		this.pinNum = pinNum;
	}
	/**
	 * @return the pinGenAction
	 */
	public String getPinGenAction() {
		return pinGenAction;
	}
	/**
	 * @param pinGenAction the pinGenAction to set
	 */
	public void setPinGenAction(String pinGenAction) {
		this.pinGenAction = pinGenAction;
	}
	
	/**
	 * @return the registrationId
	 */
	public String getRegistrationId() {
		return registrationId;
	}
	/**
	 * @param registrationId the registrationId to set
	 */
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	/**
	 * @return the noOfPins
	 */
	public int getNoOfPins() {
		return noOfPins;
	}
	/**
	 * @param noOfPins the noOfPins to set
	 */
	public void setNoOfPins(int noOfPins) {
		this.noOfPins = noOfPins;
	}
	/**
	 * @return the genPinList
	 */
	public ArrayList getGenPinList() {
		return genPinList;
	}
	/**
	 * @param genPinList the genPinList to set
	 */
	public void setGenPinList(ArrayList genPinList) {
		this.genPinList = genPinList;
	}
	/**
	 * @return the updatedPin
	 */
	public String getUpdatedPIN() {
		return updatedPIN;
	}
	/**
	 * @param updatedPin the updatedPin to set
	 */
	public void setUpdatedPIN(String updatedPIN) {
		this.updatedPIN = updatedPIN;
	}
	/**
	 * @return the confirmPIN
	 */
	public String getConfirmPIN() {
		return confirmPIN;
	}
	/**
	 * @param confirmPIN the confirmPIN to set
	 */
	public void setConfirmPIN(String confirmPIN) {
		this.confirmPIN = confirmPIN;
	}
	/**
	 * @return the oldPIN
	 */
	public String getOldPIN() {
		return oldPIN;
	}
	/**
	 * @param oldPIN the oldPIN to set
	 */
	public void setOldPIN(String oldPIN) {
		this.oldPIN = oldPIN;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getCountryList() {
		return CountryList;
	}
	public void setCountryList(ArrayList countryList) {
		CountryList = countryList;
	}
	public ArrayList getStateList() {
		return StateList;
	}
	public void setStateList(ArrayList stateList) {
		StateList = stateList;
	}
	public String getCommonID() {
		return commonID;
	}
	public void setCommonID(String commonID) {
		this.commonID = commonID;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getPropertyTransId() {
		return propertyTransId;
	}
	public void setPropertyTransId(String propertyTransId) {
		this.propertyTransId = propertyTransId;
	}
	public ArrayList getPropertylist() {
		return propertylist;
	}
	public void setPropertylist(ArrayList propertylist) {
		this.propertylist = propertylist;
	}
	public String getDeathCertificate() {
		return deathCertificate;
	}
	public void setDeathCertificate(String deathCertificate) {
		this.deathCertificate = deathCertificate;
	}
	public String getMutationDocument() {
		return mutationDocument;
	}
	public void setMutationDocument(String mutationDocument) {
		this.mutationDocument = mutationDocument;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getPropertyTransName() {
		return propertyTransName;
	}
	public void setPropertyTransName(String propertyTransName) {
		this.propertyTransName = propertyTransName;
	}
	public File getMutationfile() {
		return mutationfile;
	}
	public void setMutationfile(File mutationfile) {
		this.mutationfile = mutationfile;
	}
	public File getDeathfile() {
		return deathfile;
	}
	public void setDeathfile(File deathfile) {
		this.deathfile = deathfile;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getPinNo() {
		return pinNo;
	}
	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}
	public String getPinReqTxnNo() {
		return pinReqTxnNo;
	}
	public void setPinReqTxnNo(String pinReqTxnNo) {
		this.pinReqTxnNo = pinReqTxnNo;
	}
	public String getPinReqStatus() {
		return pinReqStatus;
	}
	public void setPinReqStatus(String pinReqStatus) {
		this.pinReqStatus = pinReqStatus;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getOfficeID() {
		return officeID;
	}
	public void setOfficeID(String officeID) {
		this.officeID = officeID;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	
	private String completePath;
	private String documentUploaded;
	private String documentPath;
	private String relationPath;
	private String DeathPath;
	private String mutationPath;
	private String documentName;
	private String relationName;
	private String DeathName;
	private String mutationName;
	private String deathChk;
	private String relationChk;
	private String suppDocChk;
	
	public String getCompletePath() {
		return completePath;
	}
	public void setCompletePath(String completePath) {
		this.completePath = completePath;
	}
	public String getDocumentUploaded() {
		return documentUploaded;
	}
	public void setDocumentUploaded(String documentUploaded) {
		this.documentUploaded = documentUploaded;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	public String getRelationPath() {
		return relationPath;
	}
	public void setRelationPath(String relationPath) {
		this.relationPath = relationPath;
	}
	public String getDeathPath() {
		return DeathPath;
	}
	public void setDeathPath(String deathPath) {
		DeathPath = deathPath;
	}
	public String getMutationPath() {
		return mutationPath;
	}
	public void setMutationPath(String mutationPath) {
		this.mutationPath = mutationPath;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getRelationName() {
		return relationName;
	}
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	public String getDeathName() {
		return DeathName;
	}
	public void setDeathName(String deathName) {
		DeathName = deathName;
	}
	public String getMutationName() {
		return mutationName;
	}
	public void setMutationName(String mutationName) {
		this.mutationName = mutationName;
	}
	public String getDeathChk() {
		return deathChk;
	}
	public void setDeathChk(String deathChk) {
		this.deathChk = deathChk;
	}
	public String getRelationChk() {
		return relationChk;
	}
	public void setRelationChk(String relationChk) {
		this.relationChk = relationChk;
	}
	public String getSuppDocChk() {
		return suppDocChk;
	}
	public void setSuppDocChk(String suppDocChk) {
		this.suppDocChk = suppDocChk;
	}
	public void setRegTxnNo(String regTxnNo) {
		this.regTxnNo = regTxnNo;
	}
	public String getRegTxnNo() {
		return regTxnNo;
	}
	public void setOwnerDetails(ArrayList ownerDetails) {
		this.ownerDetails = ownerDetails;
	}
	public ArrayList getOwnerDetails() {
		return ownerDetails;
	}
	
	
}
