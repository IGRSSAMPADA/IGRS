package com.wipro.igrs.regcompletion.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class Common1DTO implements Serializable{
	private String tempApplNo;
	private String partyId;
	private String partyType;
    private String fname;
    private String mname;
    private String lname;
    private String fatherName;
    private String motherName;
    private String spouseName;
    private String gender;
    private int age;
    private String casteId;
    private String casteName;
    private ArrayList casteList;
    private String religionId;
    private String religionName;
    private ArrayList religionList;
    private String nationality;
    private String phyChallanged;
    private String address;
    private String countryName;
    private String countryId;
    private ArrayList countryList;
    private String stateName;
    private String stateId;
    private ArrayList stateList;
    private String districtName;
    private String districtId;
    private ArrayList districtList;
    private int postalCode;
    private String phoneNo;
    private String mobNo;
    private String mailId;
    private String photoProofTypeName;
    private String photoProofTypeId;
    private String photoId;
    private ArrayList photoIdList;
    private String bankName;
    private String bankAddress;
    private String occupation;
    private String actionName; // Variable for accessing caller(e.g. Button, Component etc) from form component
    private String formName;
    private String saveBtn;
    private String organizationName;
    private String authPersonName;
    private String reportMsg;
    private ArrayList recordsBuffer=new ArrayList();
    private String erroMsg;
    private int serialNo;
    private String radioParty;
    
	/**
	 * @return the tempApplNo
	 */
	public String getTempApplNo() {
		return tempApplNo;
	}
	/**
	 * @param tempApplNo the tempApplNo to set
	 */
	public void setTempApplNo(String tempApplNo) {
		this.tempApplNo = tempApplNo;
	}
	/**
	 * @return the radioParty
	 */
	public String getRadioParty() {
		return radioParty;
	}
	/**
	 * @param radioParty the radioParty to set
	 */
	public void setRadioParty(String radioParty) {
		this.radioParty = radioParty;
	}
	/**
	 * @return the partyId
	 */
	public String getPartyId() {
		return partyId;
	}
	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}
	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}
	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the fatherName
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param fatherName the fatherName to set
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return the motherName
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param motherName the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param spouseName the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
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
	 * @return the casteList
	 */
	public ArrayList getCasteList() {
		return casteList;
	}
	/**
	 * @param casteList the casteList to set
	 */
	public void setCasteList(ArrayList casteList) {
		this.casteList = casteList;
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
	 * @return the religionList
	 */
	public ArrayList getReligionList() {
		return religionList;
	}
	/**
	 * @param religionList the religionList to set
	 */
	public void setReligionList(ArrayList religionList) {
		this.religionList = religionList;
	}
	/**
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}
	/**
	 * @param nationality the nationality to set
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/**
	 * @return the phyChallanged
	 */
	public String getPhyChallanged() {
		return phyChallanged;
	}
	/**
	 * @param phyChallanged the phyChallanged to set
	 */
	public void setPhyChallanged(String phyChallanged) {
		this.phyChallanged = phyChallanged;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}
	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	/**
	 * @return the districtList
	 */
	public ArrayList getDistrictList() {
		return districtList;
	}
	/**
	 * @param districtList the districtList to set
	 */
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	/**
	 * @return the postalCode
	 */
	public int getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return the phoneNo
	 */
	public String getPhoneNo() {
		return phoneNo;
	}
	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	/**
	 * @return the mobNo
	 */
	public String getMobNo() {
		return mobNo;
	}
	/**
	 * @param mobNo the mobNo to set
	 */
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}
	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	/**
	 * @return the photoProofTypeName
	 */
	public String getPhotoProofTypeName() {
		return photoProofTypeName;
	}
	/**
	 * @param photoProofTypeName the photoProofTypeName to set
	 */
	public void setPhotoProofTypeName(String photoProofTypeName) {
		this.photoProofTypeName = photoProofTypeName;
	}
	/**
	 * @return the photoProofTypeId
	 */
	public String getPhotoProofTypeId() {
		return photoProofTypeId;
	}
	/**
	 * @param photoProofTypeId the photoProofTypeId to set
	 */
	public void setPhotoProofTypeId(String photoProofTypeId) {
		this.photoProofTypeId = photoProofTypeId;
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
	 * @return the photoIdList
	 */
	public ArrayList getPhotoIdList() {
		return photoIdList;
	}
	/**
	 * @param photoIdList the photoIdList to set
	 */
	public void setPhotoIdList(ArrayList photoIdList) {
		this.photoIdList = photoIdList;
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
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}
	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	/**
	 * @return the saveBtn
	 */
	public String getSaveBtn() {
		return saveBtn;
	}
	/**
	 * @param saveBtn the saveBtn to set
	 */
	public void setSaveBtn(String saveBtn) {
		this.saveBtn = saveBtn;
	}
	/**
	 * @return the organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}
	/**
	 * @param organizationName the organizationName to set
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	/**
	 * @return the authPersonName
	 */
	public String getAuthPersonName() {
		return authPersonName;
	}
	/**
	 * @param authPersonName the authPersonName to set
	 */
	public void setAuthPersonName(String authPersonName) {
		this.authPersonName = authPersonName;
	}
	/**
	 * @return the reportMsg
	 */
	public String getReportMsg() {
		return reportMsg;
	}
	/**
	 * @param reportMsg the reportMsg to set
	 */
	public void setReportMsg(String reportMsg) {
		this.reportMsg = reportMsg;
	}
	
	/**
	 * @return the serialNo
	 */
	public int getSerialNo() {
		return serialNo;
	}
	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	/**
	 * @return the erroMsg
	 */
	public String getErroMsg() {
		return erroMsg;
	}
	/**
	 * @param erroMsg the erroMsg to set
	 */
	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}
	/**
	 * @return the recordsBuffer
	 */
	public ArrayList getRecordsBuffer() {
		return recordsBuffer;
	}
	/**
	 * @param recordsBuffer the recordsBuffer to set
	 */
	public void setRecordsBuffer(ArrayList recordsBuffer) {
		this.recordsBuffer = recordsBuffer;
	}
	
    
   }
