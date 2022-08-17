/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserProfileForm.java
 * Author		:	Rafiq Rahiman.T 
 * Date			:   14/03/2008
 */
package com.wipro.igrs.useracctmgmt.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.ACL.dto.AclcreateDTO;
import com.wipro.igrs.useracctmgmt.dto.UserAccntUnlockDTO;
import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;

public class UserProfileForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private ArrayList countryList;
	private ArrayList stateList;
	private ArrayList districtList;
	private ArrayList photoproofList;
	private ArrayList hintList;
	private ArrayList tehsilDetails; //Added By Praveen
	private ArrayList tehsilNameList; //Added By Praveen
	private String tehsilID;//Added By Praveen
	private FormFile certificate;

	private String tehsilName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String occupation;
	private String motherName;
	private String fatherName;
	private String spouseName;
	//private String address;
	//private String country;
	private String state;
	private String city;
	//private String type;
	//private String postalCode;
	private String phoneNumber;

	private String actionName;
	
	private String mobileNumber;
	private String primaryEmail;
	private String alternateEmail;
	private String photoProof;
	private String idNo;
	private String bankName;
	private String bankAddr;
	private String regNo;
	private String day;
	private String month;
	private String year;
	private String dob;
	private String hintQuestion;
	private String hintAnswer;
	private String termsCond;
	private String userId;
	private String Userdetails;
	private String getUserId;
	//private UserProfileDTO dto;
	private String page;
	
	private String address;
	private String country;
	private String stateName;
	private String districtName;
	private String postalCode;
	private String mainCheck;
	private ArrayList userCountryList = new ArrayList();
	private ArrayList userStateList = new ArrayList();
	private ArrayList userCityList = new ArrayList();
	private ArrayList userHintQuestion = new ArrayList();
	private String saveUrl;
	private ArrayList userDetail = new ArrayList();
	// praveen file upload
	private String FilePath ;
	private String documentName;
	private byte[] docContents;
	private String DocumentSize;
	private String FromDate;
	private String ToDate;
	public String getFromDate() {
		return FromDate;
	}
	public void setFromDate(String fromDate) {
		FromDate = fromDate;
	}
	public String getToDate() {
		return ToDate;
	}
	public void setToDate(String toDate) {
		ToDate = toDate;
	}
	/*private String UploadDocument;
	 
	
	

	public String getUploadDocument() {
		return UploadDocument;
	}
	public void setUploadDocument(String uploadDocument) {
		UploadDocument = uploadDocument;
	}*/
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public String getDocumentSize() {
		return DocumentSize;
	}
	public void setDocumentSize(String documentSize) {
		DocumentSize = documentSize;
	}
	//UserProfileDTO dto= new UserProfileDTO();
	UserProfileDTO usrProDTO = new UserProfileDTO();
	public String getFilePath() {
		return FilePath;
	}
	public void setFilePath(String filePath) {
		FilePath = filePath;
	}
	UserAccntUnlockDTO uuDTO = new UserAccntUnlockDTO();// added by ankita 
	

	public FormFile getCertificate() {
		return certificate;
	}
	public void setCertificate(FormFile certificate) {
		this.certificate = certificate;
	}
	public String getTehsilID() {
		return tehsilID;
	}
	public void setTehsilID(String tehsilID) {
		this.tehsilID = tehsilID;
	}

	public ArrayList getTehsilDetails() {
		return tehsilDetails;
	}
	public void setTehsilDetails(ArrayList tehsilDetails) {
		this.tehsilDetails = tehsilDetails;
	}
	public ArrayList getTehsilNameList() {
		return tehsilNameList;
	}
	public void setTehsilNameList(ArrayList tehsilNameList) {
		this.tehsilNameList = tehsilNameList;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getUserId() {
		return userId;
	}
	public UserAccntUnlockDTO getUuDTO() {
		return uuDTO;
	}
	public void setUuDTO(UserAccntUnlockDTO uuDTO) {
		this.uuDTO = uuDTO;
	}
	public UserProfileDTO getUsrProDTO() {
		return usrProDTO;
	}
	public void setUsrProDTO(UserProfileDTO usrProDTO) {
		this.usrProDTO = usrProDTO;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getHintAnswer()
	{
		return hintAnswer;
	}
	public void setHintAnswer(String hintAnswer)
	{
		this.hintAnswer = hintAnswer;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
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
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
	public String getPrimaryEmail() {
		return primaryEmail;
	}
	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}
	public String getAlternateEmail() {
		return alternateEmail;
	}
	public void setAlternateEmail(String alternateEmail) {
		this.alternateEmail = alternateEmail;
	}
	public String getPhotoProof() {
		return photoProof;
	}
	public void setPhotoProof(String photoProof) {
		this.photoProof = photoProof;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
/*	public UserProfileDTO getDto() {
		return dto;
	}
	public void setDto(UserProfileDTO dto) {
		this.dto = dto;
	}
	public String getUserId() {
		return userId;
	}*/
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGetUserId() {
		return getUserId;
	}
	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
	}
	public ArrayList getCountryList() {
		return countryList;
	}
	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}
	public ArrayList getStateList() {
		return stateList;
	}
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getPhotoproofList() {
		return photoproofList;
	}
	public void setPhotoproofList(ArrayList photoproofList) {
		this.photoproofList = photoproofList;
	}
	/**
	 * @return the page
	 */
	public String getPage()
	{
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(String page)
	{
		this.page = page;
	}
	public String getBankName()
	{
		return bankName;
	}
	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}
	public String getBankAddr()
	{
		return bankAddr;
	}
	public void setBankAddr(String bankAddr)
	{
		this.bankAddr = bankAddr;
	}
	public String getUserdetails()
	{
		return Userdetails;
	}
	public void setUserdetails(String userdetails)
	{
		Userdetails = userdetails;
	}
	public String getDob()
	{
		return dob;
	}
	public void setDob(String dob)
	{
		this.dob = dob;
	}
	public String getTermsCond()
	{
		return termsCond;
	}
	public void setTermsCond(String termsCond)
	{
		this.termsCond = termsCond;
	}
	public String getHintQuestion()
	{
		return hintQuestion;
	}
	public void setHintQuestion(String hintQuestion)
	{
		this.hintQuestion = hintQuestion;
	}
	public ArrayList getHintList()
	{
		return hintList;
	}
	public void setHintList(ArrayList hintList)
	{
		this.hintList = hintList;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public void setMainCheck(String mainCheck) {
		this.mainCheck = mainCheck;
	}
	public String getMainCheck() {
		return mainCheck;
	}
	public void setSaveUrl(String saveUrl) {
		this.saveUrl = saveUrl;
	}
	public String getSaveUrl() {
		return saveUrl;
	}
	public void setUserDetail(ArrayList userDetail) {
		this.userDetail = userDetail;
	}
	public ArrayList getUserDetail() {
		return userDetail;
	}

}
