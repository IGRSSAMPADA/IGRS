/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRegistrationAction.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the DTO Class for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  27th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.UserRegistration.dto;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;


/**
 * @author nihraa
 */

/**
 * @author neegaga
 *
 */
public class UserRegistrationDTO implements Serializable{

	/**
	 * userLicenseRefID
	 */
	private String userLicenseRefID;
	/**
	 * userFirstName
	 */
	private String userFirstName;
	/**
	 * userMiddleName
	 */
	private String userMiddleName;
	/**
	 * userLastName
	 */
	private String userLastName;
	/**
	 * userGender
	 */
	private String userGender;
	/**
	 * userDOB
	 */
	private String userDOB;
	/**
	 * agentCountry
	 */
	private String agentCountry;
	/**
	 * agentState
	 */
	private String agentState;
	/**
	 * idProof
	 */
	private String idProof;
	
	/**
	 * idProofCode
	 */
	private String idProofCode;
	
	/**
	 * userOccupation
	 */
	private String userOccupation;
	/**
	 * userFatherName
	 */
	private String userFatherName;
	/**
	 * userMotherName
	 */
	private String userMotherName;
	/**
	 * userSpouseName
	 */
	private String userSpouseName;
	/**
	 * userAddress
	 */
	private String userAddress;

	/**
	 * userCity
	 */private String userCity;
	 /**
	  * userCountry
	  */
	private String userCountry;
	 /**
	  * userState
	  */
	private String userState;
	/**
	 * userCityID
	 */private String userCityID;
	 /**
	  * userCountryID
	  */
	private String userCountryID;
	 /**
	  * userStateID
	  */
	private String userStateID;
	 /**
	  * userPostalCode
	  */
	private String userPostalCode;
	 /**
	  * userPhoneNumber
	  */
	private String userPhoneNumber;
	 /**
	  * userMobileNumber
	  */
	private String userMobileNumber;
	 /**
	  * userPrimaryEmail
	  */
	private String userPrimaryEmail;
	 /**
	  * userSecondaryEmail
	  */
	private String userSecondaryEmail;
	 /**
	  * registrationNumber
	  */
	private String registrationNumber;
	 /**
	  * idProofNumber
	  */
	private String idProofNumber;
	
	/**
	 * bankName
	 */
	private String bankName;
	
	/**
	 * termsCond
	 */
	private String termsCond;
	
	/**
	 * bankAddress
	 */
	private String bankAddress;
	 /**
	  * userDay
	  */
	private String userDay;
	 /**
	  * userMonth
	  */
	private String userMonth;
	 /**
	  * userYear
	  */
	private String userYear;
	 /**
	  * refferenceId
	  */
	private String refferenceId;
	 /**
	  * durationFrom
	  */
	private String durationFrom;
	 /**
	  * durationTo
	  */
	private String durationTo;
	 /**
	  * issuanceDate
	  */
	private String issuanceDate;
	 /**
	  * licenseNumber
	  */
	private String licenseNumber;
	 /**
	  * officialAddress
	  */
	private String officialAddress;
	 /**
	  * officialCity
	  */
	private String officialCity;
	 /**
	  * otherInformation
	  */
	private String otherInformation;
	
	
	

	//Login Account DETAILS 
	 /**
	  * userId
	  */
	private String userId;
	 /**
	  * userPass
	  */
	private String userPass;
	
	 /**
	  * newPswd
	  */
	private String newPswd;
	 /**
	  * userConfirmPass
	  */
	private String userConfirmPass;
	 /**
	  * userHintQuestion
	  */
	private String userHintQuestion;
	
	/**
	  * hintQuestID
	  */
	private String hintQuestID;
	
	 /**
	  * userHintAnswer
	  */
	private String userHintAnswer;


	 //User Registration
	 /**
	  * userRegForm
	  */
	private String userRegForm;
	 /**
	  * userRegisterAction
	  */
	private String userRegisterAction;
	 /**
	  * inputUserLIcenseForm
	  */

	private String inputUserLIcenseForm;
	 /**
	  * userLicensingAction
	  */
	private String userLicensingAction;
	 /**
	  * userLicenseGenerateAction
	  */
	private String userLicenseGenerateAction;
	 /**
	  * userLicenseViewForm
	  */
	private String userLicenseViewForm;
	 /**
	  * userLicenseViewAction
	  */
	private String userLicenseViewAction;
	 /**
	  * userSPRegLicenseDetailAction
	  */
	private String userSPRegLicenseDetailAction;
	 /**
	  * uploadFile
	  */
	private FormFile uploadFile;
	 /**
	  * uploadFile
	  */
	private String userAvailabilityCheckAction;
	
	//following added by roopam
	
	private String currentYear;
	private String occupationId;
	private String id;
	private String name;

	//following added by SHREE_RAJ_K
	private String minorityRad;
	private String indCategory;
	private String age;
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	public String getIndCategory() {
		return indCategory;
	}
	public void setIndCategory(String indCategory) {
		this.indCategory = indCategory;
	}
	
	public String getMinorityRad() {
		return minorityRad;
	}
	public void setMinorityRad(String minorityRad) {
		this.minorityRad = minorityRad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	 public String getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(String occupationId) {
		this.occupationId = occupationId;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	/**
	  * @return userLicenseViewForm
	  */
	public String getUserLicenseViewForm() {
		return userLicenseViewForm;
	}
	 /**
	  * @param userLicenseViewForm
	  */
	public void setUserLicenseViewForm(String userLicenseViewForm) {
		this.userLicenseViewForm = userLicenseViewForm;
	}
	 /**
	  * @return String UserRegistrationDTO
	  */
	public String getUserLicenseViewAction() {
		return userLicenseViewAction;
	}
	 /**
	  * @param userLicenseViewAction
	  */
	public void setUserLicenseViewAction(String userLicenseViewAction) {
		this.userLicenseViewAction = userLicenseViewAction;
	}
	 /**
	  * @param userFirstName
	  */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	 /**
	  * @return String userFirstName
	  */
	public String getUserFirstName() {
		return userFirstName;
	}
	 /**
	  * @param userMiddleName
	  */
	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}
	 /** 
	  * @return userMiddleName
	  */
	public String getUserMiddleName() {
		return userMiddleName;
	}
	 /**
	  * @param userLastName
	  */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	 /**
	  * @return String userLastName
	  */
	public String getUserLastName() {
		return userLastName;
	}
	 /**
	  * @param userGender
	  */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	 /**
	  * @return String userGender
	  */
	public String getUserGender() {
		return userGender;
	}
	 /**
	  * @param userDOB
	  */
	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}
	 /**
	  * @return String UserRegistrationDTO
	  */
	public String getUserDOB() {
		return userDOB;
	}
	 /**
	  * @param userOccupation
	  */
	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}
	 /**
	  * @return String userOccupation
	  */
	public String getUserOccupation() {
		return userOccupation;
	}
	 /**
	  * @param userFatherName
	  */
	public void setUserFatherName(String userFatherName) {
		this.userFatherName = userFatherName;
	}
	 /**
	  * @return String userFatherName
	  */
	public String getUserFatherName() {
		return userFatherName;
	}
	 /**
	  * @param userMotherName
	  */
	public void setUserMotherName(String userMotherName) {
		this.userMotherName = userMotherName;
	}
	 /**
	  * @return String userMotherName
	  */
	public String getUserMotherName() {
		return userMotherName;
	}
	 /**
	  * @param userSpouseName
	  */
	public void setUserSpouseName(String userSpouseName) {
		this.userSpouseName = userSpouseName;
	}
	 /**
	  * @return String userSpouseName
	  */
	public String getUserSpouseName() {
		return userSpouseName;
	}
	 /**
	  * @param userAddress
	  */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	 /**
	  * @return userAddress
	  */
	public String getUserAddress() {
		return userAddress;
	}
	 /**
	  * @param userCity
	  */
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	 /**
	  * @return String userCity
	  */
	public String getUserCity() {
		return userCity;
	}
	 /**
	  * @param userCountry
	  */
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}
	 /**
	  * @return String userCountry
	  */
	public String getUserCountry() {
		return userCountry;
	}
	 /**
	  * @param userState
	  */
	public void setUserState(String userState) {
		this.userState = userState;
	}
	 /**
	  * @return String userState
	  */
	public String getUserState() {
		return userState;
	}
	 /**
	  * @param userPostalCode
	  */
	public void setUserPostalCode(String userPostalCode) {
		this.userPostalCode = userPostalCode;
	}
	 /**
	  * @return userPostalCode
	  */
	public String getUserPostalCode() {
		return userPostalCode;
	}
	 /**
	  * @param userPhoneNumber
	  */
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	 /**
	  * @return String userPhoneNumber
	  */
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	 /**
	  * @param userPrimaryEmail
	  */
	public void setUserPrimaryEmail(String userPrimaryEmail) {
		this.userPrimaryEmail = userPrimaryEmail;
	}
	 /**
	  * @return String userPrimaryEmail
	  */
	public String getUserPrimaryEmail() {
		return userPrimaryEmail;
	}
	 /**
	  * @param userSecondaryEmail
	  */
	public void setUserSecondaryEmail(String userSecondaryEmail) {
		this.userSecondaryEmail = userSecondaryEmail;
	}
	 /**
	  * @return userSecondaryEmail
	  */
	public String getUserSecondaryEmail() {
		return userSecondaryEmail;
	}
	 /**
	  * @param userId
	  */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	 /**
	  * @return String userId
	  */
	public String getUserId() {
		return userId;
	}
	 /**
	  * @param userPass
	  */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	 /**
	  * @return String getUserPass
	  */
	public String getUserPass() {
		return userPass;
	}
	 /**
	  * @param userConfirmPass
	  */
	public void setUserConfirmPass(String userConfirmPass) {
		this.userConfirmPass = userConfirmPass;
	}
	 /**
	  * @return userConfirmPass
	  */
	public String getUserConfirmPass() {
		return userConfirmPass;
	}
	 /**
	  * @param userHintQuestion
	  */
	public void setUserHintQuestion(String userHintQuestion) {
		this.userHintQuestion = userHintQuestion;
	}
	 /**
	  * @return String userHintQuestion
	  */
	public String getUserHintQuestion() {
		return userHintQuestion;
	}
	 /**
	  * @param userHintAnswer
	  */
	public void setUserHintAnswer(String userHintAnswer) {
		this.userHintAnswer = userHintAnswer;
	}
	 /**
	  * @return String userHintAnswer
	  */
	public String getUserHintAnswer() {
		return userHintAnswer;
	}
	 /**
	  * @param agentCountry 
	  */
	public void setAgentCountry(String agentCountry) {
		this.agentCountry = agentCountry;
	}
	 /**
	  * @return String agentCountry
	  */
	public String getAgentCountry() {
		return agentCountry;
	}
	 /**
	  * @param agentState 
	  */
	public void setAgentState(String agentState) {
		this.agentState = agentState;
	}
	 /**
	  * @return agentState
	  */
	public String getAgentState() {
		return agentState;
	}
	 /**
	  * @param idProof
	  */
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	 /**
	  * @return idProof
	  */
	public String getIdProof() {
		return idProof;
	}
	 /**
	  * @param userRegForm
	  */
	public void setUserRegForm(String userRegForm) {
		this.userRegForm = userRegForm;
	}
	 /**
	  * @return String userRegForm
	  */
	public String getUserRegForm() {
		return userRegForm;
	}
	 /**
	  * @param userRegisterAction
	  */
	public void setUserRegisterAction(String userRegisterAction) {
		this.userRegisterAction = userRegisterAction;
	}
	 /**
	  * @return String userRegisterAction
	  */
	public String getUserRegisterAction() {
		return userRegisterAction;
	}
	 /**
	  * @param userDay
	  */
	public void setUserDay(String userDay) {
		this.userDay = userDay;
	}
	 /**
	  * @return String userDay
	  */
	public String getUserDay() {
		return userDay;
	}
	 /**
	  * @param userMonth
	  */
	public void setUserMonth(String userMonth) {
		this.userMonth = userMonth;
	}
	 /**
	  * @return String userMonth
	  */
	public String getUserMonth() {
		return userMonth;
	}
	 /**
	  * @param userYear
	  */
	public void setUserYear(String userYear) {
		this.userYear = userYear;
	}
	 /**
	  * @return String userYear
	  */
	public String getUserYear() {
		return userYear;
	}
	 /**
	  * @return String userLicensingAction
	  */
	public String getUserLicensingAction() {
		return userLicensingAction;
	}
	 /**
	  * @param userLicensingAction
	  */
	public void setUserLicensingAction(String userLicensingAction) {
		this.userLicensingAction = userLicensingAction;
	}
	 /**
	  * @return inputUserLIcenseForm
	  */
	public String getInputUserLIcenseForm() {
		return inputUserLIcenseForm;
	}
	 /**
	  * @param inputUserLIcenseForm
	  */
	public void setInputUserLIcenseForm(String inputUserLIcenseForm) {
		this.inputUserLIcenseForm = inputUserLIcenseForm;
	}
	 /**
	  * @return durationFrom
	  */
	public String getDurationFrom() {
		return durationFrom;
	}
	 /**
	  * @param durationFrom
	  */
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}
	 /**
	  * @return String durationTo
	  */
	public String getDurationTo() {
		return durationTo;
	}
	 /**
	  * @param durationTo
	  */
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}
	 /**
	  * @return issuanceDate
	  */
	public String getIssuanceDate() {
		return issuanceDate;
	}
	 /**
	  * @param issuanceDate
	  */
	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}
	 /**
	  * @return officialAddress
	  */
	public String getOfficialAddress() {
		return officialAddress;
	}
	 /**
	  * @param officialAddress
	  */
	public void setOfficialAddress(String officialAddress) {
		this.officialAddress = officialAddress;
	}
	 /**
	  * @return String officialCity
	  */
	public String getOfficialCity() {
		return officialCity;
	}
	 /**
	 * @param officialCity 
	  */
	public void setOfficialCity(String officialCity) {
		this.officialCity = officialCity;
	}
	 /**
	  * @return String otherInformation
	  */
	public String getOtherInformation() {
		return otherInformation;
	}
	 /**
	  * @param otherInformation 
	  */
	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}
	 /**
	  * @return String licenseNumber
	  */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	 /**
	 * @param licenseNumber 
	  */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	 /**
	  * @return String userLicenseGenerateAction
	  */
	public String getUserLicenseGenerateAction() {
		return userLicenseGenerateAction;
	}
	 /**
	 * @param userLicenseGenerateAction 
	  */
	public void setUserLicenseGenerateAction(String userLicenseGenerateAction) {
		this.userLicenseGenerateAction = userLicenseGenerateAction;
	}
	 /**
	  * @return String refferenceId
	  */
	public String getRefferenceId() {
		return refferenceId;
	}
	 /**
	 * @param refferenceId 
	  */
	public void setRefferenceId(String refferenceId) {
		this.refferenceId = refferenceId;
	}
	 /**
	  * @return String registrationNumber
	  */
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	 /**
	 * @param ownerPropertyRegistrationNumber 
	  */
	public void setRegistrationNumber(
			 String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	 /**
	  * @return String idProofNumber
	  */
	public String getIdProofNumber() {
		return idProofNumber;
	}
	 /**
	  * @param idProofNumber 
	  */
	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}
	 /**
	  * @return String userMobileNumber
	  */
	public String getUserMobileNumber() {
		return userMobileNumber;
	}
	 /**
	  * @param userMobileNumber 
	  */
	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}
	 /**
	  * @return String userLicenseRefID
	  */
	public String getUserLicenseRefID() {
		return userLicenseRefID;
	}
	 /**
	 * @param userLicenseRefID 
	  */
	public void setUserLicenseRefID(String userLicenseRefID) {
		this.userLicenseRefID = userLicenseRefID;
	}
	 /**
	  * @return String userSPRegLicenseDetailAction
	  */
	public String getUserSPRegLicenseDetailAction() {
		return userSPRegLicenseDetailAction;
	}
	 /**
	 * @param userSPRegLicenseDetailAction 
	  */
	public void setUserSPRegLicenseDetailAction(String
		 userSPRegLicenseDetailAction) {
		this.userSPRegLicenseDetailAction = userSPRegLicenseDetailAction;
	}
	 /**
	  * @return FormFile uploadFile
	  */
	public FormFile getUploadFile() {
		return uploadFile;
	}
	 /**
	 * @param uploadFile 
	  */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	 /**
	  * @return String userSPRegLicenseDetailAction
	  */
	public String getUserAvailabilityCheckAction() {
		return userAvailabilityCheckAction;
	}
	/**
	  * @param userAvailabilityCheckAction
	 */
	public void setUserAvailabilityCheckAction(String
		 userAvailabilityCheckAction) {
		this.userAvailabilityCheckAction = userAvailabilityCheckAction;
	}
	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}
	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	/**
	 * @return the bankAddress
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	/**
	 * @param bankAddress the bankAddress to set
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	/**
	 * @return the termsCond
	 */
	public String getTermsCond() {
		return termsCond;
	}
	/**
	 * @param termsCond the termsCond to set
	 */
	public void setTermsCond(String termsCond) {
		this.termsCond = termsCond;
	}
	/**
	 * @return the userCityID
	 */
	public String getUserCityID() {
		return userCityID;
	}
	/**
	 * @param userCityID the userCityID to set
	 */
	public void setUserCityID(String userCityID) {
		this.userCityID = userCityID;
	}
	/**
	 * @return the userCountryID
	 */
	public String getUserCountryID() {
		return userCountryID;
	}
	/**
	 * @param userCountryID the userCountryID to set
	 */
	public void setUserCountryID(String userCountryID) {
		this.userCountryID = userCountryID;
	}
	/**
	 * @return the userStateID
	 */
	public String getUserStateID() {
		return userStateID;
	}
	/**
	 * @param userStateID the userStateID to set
	 */
	public void setUserStateID(String userStateID) {
		this.userStateID = userStateID;
	}
	/**
	 * @return the idProofCode
	 */
	public String getIdProofCode() {
		return idProofCode;
	}
	/**
	 * @param idProofCode the idProofCode to set
	 */
	public void setIdProofCode(String idProofCode) {
		this.idProofCode = idProofCode;
	}
	/**
	 * @return the hintQuestID
	 */
	public String getHintQuestID() {
		return hintQuestID;
	}
	/**
	 * @param hintQuestID the hintQuestID to set
	 */
	public void setHintQuestID(String hintQuestID) {
		this.hintQuestID = hintQuestID;
	}
	/**
	 * @return the newPswd
	 */
	public String getNewPswd() {
		return newPswd;
	}
	/**
	 * @param newPswd the newPswd to set
	 */
	public void setNewPswd(String newPswd) {
		this.newPswd = newPswd;
	}
	
	/**
	 * forwardPath
	 */
	private String forwardPath;

	/**
	 * @return the forwardPath
	 */
	public String getForwardPath() {
		return forwardPath;
	}
	/**
	 * @param forwardPath the forwardPath to set
	 */
	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}
}
