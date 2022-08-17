/**
 * 
 */
package com.wipro.igrs.CitizenFeedback.dto;

import java.util.ArrayList;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackDTO {

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
	private String state;
	private String cityDistrict;
	private String postalCode;
	private String phoneNumber;
	private String mobileNumber;
	private String emailID;
	private String idProofType;
	private String idProofNumber;
	private String feedbackCatagory;
	private String typeOfService;
	private String serviceRefNumber;
	private String feedback;
	private String bankName;
	private String bankAddress;
	private String nationality;
	
	private String citizenFeedbackId;
	private String feedbackFunctionName;
	private String feedbackFunctionID;
	private String countryId;
	private String stateId;
	private String cityDistrictId;
	private String referenceId;
	private String createdDate;
	private String hdnreferenceId;
	private String comments;
	
	
	//-----new added variables----
	
	private String searchrefid;
	private String fromrequestdate;
	private String torequestdate;
	private String status;
	private String type;
	private String drcomments;
	private String logic;
	
	
	ArrayList PendingRequests = new ArrayList();
	

//added
private String option;

	
	public String getOption() {
	return option;
}
public void setOption(String option) {
	this.option = option;
}
	public String getFeedbackFunctionName() {
		return feedbackFunctionName;
	}
	public void setFeedbackFunctionName(String feedbackFunctionName) {
		this.feedbackFunctionName = feedbackFunctionName;
	}
	public String getFeedbackFunctionID() {
		return feedbackFunctionID;
	}
	public void setFeedbackFunctionID(String feedbackFunctionID) {
		this.feedbackFunctionID = feedbackFunctionID;
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

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCityDistrict() {
		return cityDistrict;
	}
	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
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
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getIdProofType() {
		return idProofType;
	}
	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}
	public String getIdProofNumber() {
		return idProofNumber;
	}
	public void setIdProofNumber(String idProofNumber) {
		this.idProofNumber = idProofNumber;
	}
	public String getFeedbackCatagory() {
		return feedbackCatagory;
	}
	public void setFeedbackCatagory(String feedbackCatagory) {
		this.feedbackCatagory = feedbackCatagory;
	}
	public String getTypeOfService() {
		return typeOfService;
	}
	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	public String getServiceRefNumber() {
		return serviceRefNumber;
	}
	public void setServiceRefNumber(String serviceRefNumber) {
		this.serviceRefNumber = serviceRefNumber;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCitizenFeedbackId() {
		return citizenFeedbackId;
	}
	public void setCitizenFeedbackId(String citizenFeedbackId) {
		this.citizenFeedbackId = citizenFeedbackId;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
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
	public String getCityDistrictId() {
		return cityDistrictId;
	}
	public void setCityDistrictId(String cityDistrictId) {
		
		System.out.println("the id is"+ cityDistrictId);
		this.cityDistrictId = cityDistrictId;
	}
	
	public ArrayList getPendingRequests() {
		return PendingRequests;
	}
	public void setPendingRequests(ArrayList pendingRequests) {
		PendingRequests = pendingRequests;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getHdnreferenceId() {
		return hdnreferenceId;
	}
	public void setHdnreferenceId(String hdnreferenceId) {
		this.hdnreferenceId = hdnreferenceId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getFromrequestdate() {
		return fromrequestdate;
	}
	public void setFromrequestdate(String fromrequestdate) {
		this.fromrequestdate = fromrequestdate;
	}
	public String getTorequestdate() {
		return torequestdate;
	}
	public void setTorequestdate(String torequestdate) {
		this.torequestdate = torequestdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSearchrefid() {
		return searchrefid;
	}
	public void setSearchrefid(String searchrefid) {
		this.searchrefid = searchrefid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDrcomments() {
		return drcomments;
	}
	public void setDrcomments(String drcomments) {
		this.drcomments = drcomments;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	
	
}
