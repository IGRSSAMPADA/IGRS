package com.wipro.igrs.RegCompMaker.dto;


/**
 * ===========================================================================
 * File           :   CommonDTO.java
 * Description    :   Represents the Common DTO Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class CommonMkrDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regNo;
	private String deed;
	private String propId;
	private String name;
	private String id;
	private ArrayList deedList;
	private ArrayList instList;
	private ArrayList exmpList;
	private String middleName;
	private String lastName;
	/*private String checked="";
	private String script="";*/
	private String hdnDeleteDeed;
	private HashMap mapProperty = new HashMap();
	private ArrayList propertyList = new ArrayList();
	private String hdnDeleteProperty;
	private String addMoreCounter;
//  piyush data
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
    //private String photoIdProof;
    private String photoProofTypeName;
    private String photoProofTypeId;
    private String photoId;
    private ArrayList photoIdList;
    
    private ArrayList deedProList = new ArrayList();
    private ArrayList proDeedList = new ArrayList();
    private ArrayList partyDeedList = new ArrayList();
    private ArrayList partyTypeList = new ArrayList();
    private String partyTypeName;
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
    private String tempApplNo;
    //--pav data
    private String stampDuty;
    private String otherFee;
    private String total;
    private ArrayList mapList;
    private ArrayList complList; 
    private String compliance;
    private String sysAmt;
    private String consAmt;
    private String dutyVal;
    private String dutyName;
    private String regFee;
    private String checkRegNo  ;
    private String propertyTxnId;
    private String hdnPropertyTxnId;
   /* private String checkProperty = "R";*/
    private String partyTxnID;
    private String remarks;
	private String siReason;
	private String userId;
	private String spotInspTxnId;
	private String partyTypeId;
	private String partyOwnerFlag;
	private String chkOwnerFlag;
	private String deedTxnId;
	private String verifyPinParty;
	private String oldRegNo;
	private String oldPropertyId;
	private String pinNumber;
	private String oldPartyId;
	private String pinStatus;
	private String orgName ;
	// Added By aruna
	private String rorRequired;
	private String deedTypeName;
	private String oldRegNum;
	private String oldReg;
	
	
	//Added By Ankita 
	
	//Start:=== For handling radio button
	//and fields of LinkingPaymentPopup.jsp
	private String radioVal;
	private String linkedRegNum;
	private String linkedEstampNum;
	private String linkedPhysclStmpNum;
	private String oldRegistrationNumber;
	private String oldRegistrationDate;
	private Double linkedTotalRegFee;
	private Double linkedTotalDuty;
	private Double dutyAlrdyPaid;
	private Double regFeeAlrdyPaid;
	/*linked number may contain linkedRegNum or linkedEstampNum
	 * or linkedPhysicalStampNum and the value of flag variable will tell 
	 * what number it contains 
	 * if flag==1 then reg number if flag==2 then estamp and is flag==3 then physical stamp  */
	private String linkedNum;
	private String linkedFlag;
	private double tempDuty;
	private double tempFee;
	//End
	
	public double getTempDuty() {
		return tempDuty;
	}
	public String getOldRegNum() {
		return oldRegNum;
	}
	public void setOldRegNum(String oldRegNum) {
		this.oldRegNum = oldRegNum;
	}
	public void setTempDuty(double tempDuty) {
		this.tempDuty = tempDuty;
	}
	public double getTempFee() {
		return tempFee;
	}
	public void setTempFee(double tempFee) {
		this.tempFee = tempFee;
	}
	public String getRadioVal() {
		return radioVal;
	}
	public Double getRegFeeAlrdyPaid() {
		return regFeeAlrdyPaid;
	}
	public void setRegFeeAlrdyPaid(Double regFeeAlrdyPaid) {
		this.regFeeAlrdyPaid = regFeeAlrdyPaid;
	}
	public Double getDutyAlrdyPaid() {
		return dutyAlrdyPaid;
	}
	public void setDutyAlrdyPaid(Double dutyAlrdyPaid) {
		this.dutyAlrdyPaid = dutyAlrdyPaid;
	}
	
	public String getLinkedNum() {
		return linkedNum;
	}
	public void setLinkedNum(String linkedNum) {
		this.linkedNum = linkedNum;
	}
	public String getLinkedFlag() {
		return linkedFlag;
	}
	public void setLinkedFlag(String linkedFlag) {
		this.linkedFlag = linkedFlag;
	}
	public Double getLinkedTotalRegFee() {
		return linkedTotalRegFee;
	}
	public void setLinkedTotalRegFee(Double linkedTotalRegFee) {
		this.linkedTotalRegFee = linkedTotalRegFee;
	}
	public Double getLinkedTotalDuty() {
		return linkedTotalDuty;
	}
	public void setLinkedTotalDuty(Double linkedTotalDuty) {
		this.linkedTotalDuty = linkedTotalDuty;
	}
	public String getLinkedRegNum() {
		return linkedRegNum;
	}
	public void setLinkedRegNum(String linkedRegNum) {
		this.linkedRegNum = linkedRegNum;
	}
	public String getLinkedEstampNum() {
		return linkedEstampNum;
	}
	public void setLinkedEstampNum(String linkedEstampNum) {
		this.linkedEstampNum = linkedEstampNum;
	}
	public String getLinkedPhysclStmpNum() {
		return linkedPhysclStmpNum;
	}
	public void setLinkedPhysclStmpNum(String linkedPhysclStmpNum) {
		this.linkedPhysclStmpNum = linkedPhysclStmpNum;
	}
	public void setRadioVal(String radioVal) {
		this.radioVal = radioVal;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	 
	public String getOldPartyId() {
		return oldPartyId;
	}
	public void setOldPartyId(String oldPartyId) {
		this.oldPartyId = oldPartyId;
	}
	public String getOldRegNo() {
		return oldRegNo;
	}
	public void setOldRegNo(String oldRegNo) {
		this.oldRegNo = oldRegNo;
	}
	public String getOldPropertyId() {
		return oldPropertyId;
	}
	public void setOldPropertyId(String oldPropertyId) {
		this.oldPropertyId = oldPropertyId;
	}
	public String getPinNumber() {
		return pinNumber;
	}
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}
	public String getVerifyPinParty() {
		return verifyPinParty;
	}
	public void setVerifyPinParty(String verifyPinParty) {
		this.verifyPinParty = verifyPinParty;
	}
	public String getDeedTxnId() {
		return deedTxnId;
	}
	public void setDeedTxnId(String deedTxnId) {
		this.deedTxnId = deedTxnId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSiReason() {
		return siReason;
	}
	public void setSiReason(String siReason) {
		this.siReason = siReason;
	}
	public String getPartyTxnID() {
		return partyTxnID;
	}
	public void setPartyTxnID(String partyTxnID) {
		this.partyTxnID = partyTxnID;
	}
	/*public String getCheckProperty() {
		return checkProperty;
	}
	public void setCheckProperty(String checkProperty) {
		this.checkProperty = checkProperty;
	}*/
	public String getHdnPropertyTxnId() {
		return hdnPropertyTxnId;
	}
	public void setHdnPropertyTxnId(String hdnPropertyTxnId) {
		this.hdnPropertyTxnId = hdnPropertyTxnId;
	}
	public String getPropertyTxnId() {
		return propertyTxnId;
	}
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}
	public ArrayList getRecordsBuffer() {
		return recordsBuffer;
	}
	public void setRecordsBuffer(ArrayList recordsBuffer) {
		this.recordsBuffer = recordsBuffer;
	}
	 public String toString()
	 {
		 return linkedNum+"~"+linkedFlag+"~"+linkedTotalRegFee+"~"+linkedTotalDuty;
	 }
	 
	public String getCheckRegNo() {
		return checkRegNo;
	}
	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}
	/**
	 * @return the complList
	 */
	public ArrayList getComplList() {
		return complList;
	}
	/**
	 * @param complList the complList to set
	 */
	public void setComplList(ArrayList complList) {
		this.complList = complList;
	}
	/**
	 * @return the compliance
	 */
	public String getCompliance() {
		return compliance;
	}
	/**
	 * @param compliance the compliance to set
	 */
	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the instList
	 */
	public ArrayList getInstList() {
		return instList;
	}
	/**
	 * @param instList the instList to set
	 */
	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}
	/**
	 * @return the exmpList
	 */
	public ArrayList getExmpList() {
		return exmpList;
	}
	/**
	 * @param exmpList the exmpList to set
	 */
	public void setExmpList(ArrayList exmpList) {
		this.exmpList = exmpList;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the checked
	 */
	/*public String getChecked() {
		return checked;
	}
	*//**
	 * @param checked the checked to set
	 *//*
	public void setChecked(String checked) {
		this.checked = checked;
	}*/
	/**
	 * @return the script
	 */
	/*public String getScript() {
		return script;
	}
	*//**
	 * @param script the script to set
	 *//*
	public void setScript(String script) {
		this.script = script;
	}*/
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
	 * @return the deed
	 */
	public String getDeed() {
		return deed;
	}
	/**
	 * @param deed the deed to set
	 */
	public void setDeed(String deed) {
		this.deed = deed;
	}
	/**
	 * @return the propId
	 */
	public String getPropId() {
		return propId;
	}
	/**
	 * @param propId the propId to set
	 */
	public void setPropId(String propId) {
		this.propId = propId;
	}
	/**
	 * @param mapList the mapList to set
	 */
	/**
	 * @return the mapList
	 */
	public ArrayList getMapList() {
		return mapList;
	}
	/**
	 * @param mapList the mapList to set
	 */
	public void setMapList(ArrayList mapList) {
		this.mapList = mapList;
	}
	/**
	 * @return the stampDuty
	 */
	public String getStampDuty() {
		return stampDuty;
	}
	/**
	 * @param stampDuty the stampDuty to set
	 */
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
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
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the sysAmt
	 */
	public String getSysAmt() {
		return sysAmt;
	}
	/**
	 * @param sysAmt the sysAmt to set
	 */
	public void setSysAmt(String sysAmt) {
		this.sysAmt = sysAmt;
	}
	/**
	 * @return the consAmt
	 */
	public String getConsAmt() {
		return consAmt;
	}
	/**
	 * @param consAmt the consAmt to set
	 */
	public void setConsAmt(String consAmt) {
		this.consAmt = consAmt;
	}
	/**
	 * @return the dutyVal
	 */
	public String getDutyVal() {
		return dutyVal;
	}
	/**
	 * @param dutyVal the dutyVal to set
	 */
	public void setDutyVal(String dutyVal) {
		this.dutyVal = dutyVal;
	}
	/**
	 * @return the dutyName
	 */
	public String getDutyName() {
		return dutyName;
	}
	/**
	 * @param dutyName the dutyName to set
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	/**
	 * @return the regFee
	 */
	public String getRegFee() {
		return regFee;
	}
	/**
	 * @param regFee the regFee to set
	 */
	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}
	public String getSpotInspTxnId() {
		return spotInspTxnId;
	}
	public void setSpotInspTxnId(String spotInspTxnId) {
		this.spotInspTxnId = spotInspTxnId;
	}
	public String getPartyTypeId() {
		return partyTypeId;
	}
	public void setPartyTypeId(String partyTypeId) {
		this.partyTypeId = partyTypeId;
	}
	public ArrayList getDeedProList() {
		return deedProList;
	}
	public void setDeedProList(ArrayList deedProList) {
		this.deedProList = deedProList;
	}
	public ArrayList getProDeedList() {
		return proDeedList;
	}
	public void setProDeedList(ArrayList proDeedList) {
		this.proDeedList = proDeedList;
	}
	public ArrayList getPartyDeedList() {
		return partyDeedList;
	}
	public void setPartyDeedList(ArrayList partyDeedList) {
		this.partyDeedList = partyDeedList;
	}
	public ArrayList getPartyTypeList() {
		return partyTypeList;
	}
	public void setPartyTypeList(ArrayList partyTypeList) {
		this.partyTypeList = partyTypeList;
	}
	public HashMap getMapProperty() {
		return mapProperty;
	}
	public void setMapProperty(HashMap mapProperty) {
		this.mapProperty = mapProperty;
	}
	public ArrayList getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}
	public String getHdnDeleteProperty() {
		return hdnDeleteProperty;
	}
	public void setHdnDeleteProperty(String hdnDeleteProperty) {
		this.hdnDeleteProperty = hdnDeleteProperty;
	}
	public String getAddMoreCounter() {
		return addMoreCounter;
	}
	public void setAddMoreCounter(String addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}
	public String getHdnDeleteDeed() {
		return hdnDeleteDeed;
	}
	public void setHdnDeleteDeed(String hdnDeleteDeed) {
		this.hdnDeleteDeed = hdnDeleteDeed;
	}
	public String getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}
	public String getPartyOwnerFlag() {
		return partyOwnerFlag;
	}
	public void setPartyOwnerFlag(String partyOwnerFlag) {
		this.partyOwnerFlag = partyOwnerFlag;
	}
	public String getChkOwnerFlag() {
		return chkOwnerFlag;
	}
	public void setChkOwnerFlag(String chkOwnerFlag) {
		this.chkOwnerFlag = chkOwnerFlag;
	}
	public String getPinStatus() {
		return pinStatus;
	}
	public void setPinStatus(String pinStatus) {
		this.pinStatus = pinStatus;
	}
	public String getRorRequired() {
		return rorRequired;
	}
	public void setRorRequired(String rorRequired) {
		this.rorRequired = rorRequired;
	}
	public String getDeedTypeName() {
		return deedTypeName;
	}
	public void setDeedTypeName(String deedTypeName) {
		this.deedTypeName = deedTypeName;
	}
	
	private boolean alreadyPaid;

	public boolean isAlreadyPaid() {
		return alreadyPaid;
	}
	public void setAlreadyPaid(boolean alreadyPaid) {
		this.alreadyPaid = alreadyPaid;
	}
	public String getOldReg() {
		return oldReg;
	}
	public void setOldReg(String oldReg) {
		this.oldReg = oldReg;
	}
	public void setOldRegistrationNumber(String oldRegistrationNumber) {
		this.oldRegistrationNumber = oldRegistrationNumber;
	}
	public String getOldRegistrationNumber() {
		return oldRegistrationNumber;
	}
	public void setOldRegistrationDate(String oldRegistrationDate) {
		this.oldRegistrationDate = oldRegistrationDate;
	}
	public String getOldRegistrationDate() {
		return oldRegistrationDate;
	}
	
	
	
}