/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for ACL.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class AclcreateDTO implements Serializable {
	public AclcreateDTO() {

	}

	private String firstName;
	private String lastName;
	private String midName;
	private String creatUser;
	private String userId;
	private String phNumber;
	private String mobNumber;
	private String emailId;
	private String gender;
	private String designation;
	private String dob;
	private String doj;
	private String loginName;
	private String pageName;
	
	private String address;
	private String country;
	private String stateName;
	private String districtName;
	private String userCityID;
	private String userCountryID;
	private String userStateID;
	
	private String postalCode;
	private String alterEmailId;
	private String password;
	private String repassword;
	private String hintQuestion;
	private String hintAnswer;
	private String hintQuestID;
	
	private ArrayList userCountryList = new ArrayList();
	private ArrayList userStateList = new ArrayList();
	private ArrayList userCityList = new ArrayList();
	private ArrayList userHintQuestion = new ArrayList();
	
	private String aclUserAction;
	private String fwdName;
	
	private String cntry;
	private String state;
	private String dstrct;
	
	private String userStatus;
	
	private String name="";
	private String value="";
	private ArrayList officeList = new ArrayList();
	private String officeName="";
	private String resetPass;
	
	public String getResetPass() {
		return resetPass;
	}

	public void setResetPass(String resetPass) {
		this.resetPass = resetPass;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	 
	
	
	
	public ArrayList getOfficeList() {
		return officeList;
	}

	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getCntry() {
		return cntry;
	}

	public void setCntry(String cntry) {
		this.cntry = cntry;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDstrct() {
		return dstrct;
	}

	public void setDstrct(String dstrct) {
		this.dstrct = dstrct;
	}

	public String getFwdName() {
		return fwdName;
	}

	public void setFwdName(String fwdName) {
		this.fwdName = fwdName;
	}

	public String getAclUserAction() {
		return aclUserAction;
	}

	public void setAclUserAction(String aclUserAction) {
		this.aclUserAction = aclUserAction;
	}

	public ArrayList getUserCountryList() {
		return userCountryList;
	}

	public void setUserCountryList(ArrayList userCountryList) {
		this.userCountryList = userCountryList;
	}

	public ArrayList getUserStateList() {
		return userStateList;
	}

	public void setUserStateList(ArrayList userStateList) {
		this.userStateList = userStateList;
	}

	public ArrayList getUserCityList() {
		return userCityList;
	}

	public void setUserCityList(ArrayList userCityList) {
		this.userCityList = userCityList;
	}

	public ArrayList getUserHintQuestion() {
		return userHintQuestion;
	}

	public void setUserHintQuestion(ArrayList userHintQuestion) {
		this.userHintQuestion = userHintQuestion;
	}

	public String getUserCityID() {
		return userCityID;
	}

	public void setUserCityID(String userCityID) {
		this.userCityID = userCityID;
	}

	public String getUserCountryID() {
		return userCountryID;
	}

	public void setUserCountryID(String userCountryID) {
		this.userCountryID = userCountryID;
	}

	public String getUserStateID() {
		return userStateID;
	}

	public void setUserStateID(String userStateID) {
		this.userStateID = userStateID;
	}

	public String getHintQuestID() {
		return hintQuestID;
	}

	public void setHintQuestID(String hintQuestID) {
		this.hintQuestID = hintQuestID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAlterEmailId() {
		return alterEmailId;
	}

	public void setAlterEmailId(String alterEmailId) {
		this.alterEmailId = alterEmailId;
	}

	public String getHintQuestion() {
		return hintQuestion;
	}

	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}

	public String getHintAnswer() {
		return hintAnswer;
	}

	public void setHintAnswer(String hintAnswer) {
		this.hintAnswer = hintAnswer;
	}

	ArrayList userList;
	ArrayList useraclList;

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the creatUser.
	 */
	public String getCreatUser() {
		return creatUser;
	}

	/**
	 * @param creatUser
	 *            The creatUser to set.
	 */
	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}

	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            The pageName to set.
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the phNumber.
	 */
	public String getPhNumber() {
		return phNumber;
	}

	/**
	 * @param phNumber
	 *            The phNumber to set.
	 */
	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	/**
	 * @return Returns the mobNumber.
	 */
	public String getMobNumber() {
		return mobNumber;
	}

	/**
	 * @param mobNumber
	 *            The mobNumber to set.
	 */
	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	/**
	 * @return Returns the emailId.
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            The emailId to set.
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return Returns the designation.
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            The designation to set.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return Returns the dob.
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            The dob to set.
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return Returns the doj.
	 */
	public String getDoj() {
		return doj;
	}

	/**
	 * @param doj
	 *            The doj to set.
	 */
	public void setDoj(String doj) {
		this.doj = doj;
	}

	/**
	 * @return Returns the loginName.
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName
	 *            The loginName to set.
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the repassword.
	 */
	public String getRepassword() {
		return repassword;
	}

	/**
	 * @param repassword
	 *            The repassword to set.
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	/**
	 * @return Returns the midName.
	 */
	public String getMidName() {
		return midName;
	}

	/**
	 * @param midName
	 *            The midName to set.
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}

	/**
	 * @return Returns the gender.
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            The gender to set.
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param userList
	 *            The userList to set.
	 */
	public void setUserList(ArrayList userList) {
		this.userList = userList;
	}

	/**
	 * @return Returns the userList.
	 */
	public ArrayList getUserList() {
		return userList;
	}

	/**
	 * @return Returns the useraclList.
	 */
	public ArrayList getUseraclList() {
		return useraclList;
	}

	/**
	 * @param useraclList
	 *            The useraclList to set.
	 */
	public void setUseraclList(ArrayList useraclList) {
		this.useraclList = useraclList;
	}
}