package com.wipro.igrs.regcompletion.dto;


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


public class RegInitCompleteDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regNo;
	private String regNumber;
	private String deed;
	private String propId;
	private String name;
	private String id;
	private ArrayList deedList;
	private ArrayList instList;
	private ArrayList exmpList;
	private String instrumentId;
	private String instrumentName;
	private String exemptionId;
	private String exemptionName;
	
	private String middleName;
	private String lastName;
	private String checked="";
	private String script="";
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
    private String age;
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
    private String postalCode;
    private String phoneNo;
    private String mobNo;
    private String mailId;
    private String emailID;
    private String listID;
	private String idno;
	private String bname;
	private String baddress;
	private String hdnExemption;
    //private String photoIdProof;
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
    private ArrayList partiesList;
    private String payId;
	private String payDate;
	private String payAmt;
	private String indaddress;
	private String indcountry;
	private String indstatename;
	private String inddistrict;
	private String indphno;
	private String indmobno;
	private String ecode;
	private String slotDate;
	private String slotRefId;
	private String slotTime;
	private String inputTypeId;
	private String inputType;
	private String inputlabel;
	private String inputAlt;
	private String inputScript;
	private String deedId;
	private String deedName;
	private String deedValReq;
	private String dutyCalReq;
	private String propertyRelatedDeed;
	private ArrayList formList = new ArrayList();
	private HashMap mapForm = new HashMap(); 
	private String deedTxnId;
	// Added By Aruna
	private ArrayList attributeList=new ArrayList();
	
	public String getDeedTxnId() {
		return deedTxnId;
	}
	public void setDeedTxnId(String deedTxnId) {
		this.deedTxnId = deedTxnId;
	}
	public ArrayList getFormList() {
		return formList;
	}
	public void setFormList(ArrayList formList) {
		this.formList = formList;
	}
	public String getSlotDate() {
		return slotDate;
	}
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	public String getSlotRefId() {
		return slotRefId;
	}
	public void setSlotRefId(String slotRefId) {
		this.slotRefId = slotRefId;
	}
	public String getSlotTime() {
		return slotTime;
	}
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public ArrayList getRecordsBuffer() {
		return recordsBuffer;
	}
	public void setRecordsBuffer(ArrayList recordsBuffer) {
		this.recordsBuffer = recordsBuffer;
	}
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	public String getIndaddress() {
		return indaddress;
	}
	public void setIndaddress(String indaddress) {
		this.indaddress = indaddress;
	}
	public String getIndcountry() {
		return indcountry;
	}
	public void setIndcountry(String indcountry) {
		this.indcountry = indcountry;
	}
	public String getIndstatename() {
		return indstatename;
	}
	public void setIndstatename(String indstatename) {
		this.indstatename = indstatename;
	}
	public String getInddistrict() {
		return inddistrict;
	}
	public void setInddistrict(String inddistrict) {
		this.inddistrict = inddistrict;
	}
	public String getIndphno() {
		return indphno;
	}
	public void setIndphno(String indphno) {
		this.indphno = indphno;
	}
	public String getIndmobno() {
		return indmobno;
	}
	public void setIndmobno(String indmobno) {
		this.indmobno = indmobno;
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
	public String getChecked() {
		return checked;
	}
	/**
	 * @param checked the checked to set
	 */
	public void setChecked(String checked) {
		this.checked = checked;
	}
	/**
	 * @return the script
	 */
	public String getScript() {
		return script;
	}
	/**
	 * @param script the script to set
	 */
	public void setScript(String script) {
		this.script = script;
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
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
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
	public String getPostalCode() {
		return postalCode;
	}
	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
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
	public ArrayList getPartiesList() {
		return partiesList;
	}
	public void setPartiesList(ArrayList partiesList) {
		this.partiesList = partiesList;
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
	public String getIdno() {
		return idno;
	}
	public void setIdno(String idno) {
		this.idno = idno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBaddress() {
		return baddress;
	}
	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getDeedId() {
		return deedId;
	}
	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}
	public String getDeedName() {
		return deedName;
	}
	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}
	public String getDeedValReq() {
		return deedValReq;
	}
	public void setDeedValReq(String deedValReq) {
		this.deedValReq = deedValReq;
	}
	public String getDutyCalReq() {
		return dutyCalReq;
	}
	public void setDutyCalReq(String dutyCalReq) {
		this.dutyCalReq = dutyCalReq;
	}
	public String getPropertyRelatedDeed() {
		return propertyRelatedDeed;
	}
	public void setPropertyRelatedDeed(String propertyRelatedDeed) {
		this.propertyRelatedDeed = propertyRelatedDeed;
	}
	public String getInstrumentId() {
		return instrumentId;
	}
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public String getExemptionId() {
		return exemptionId;
	}
	public void setExemptionId(String exemptionId) {
		this.exemptionId = exemptionId;
	}
	public String getExemptionName() {
		return exemptionName;
	}
	public void setExemptionName(String exemptionName) {
		this.exemptionName = exemptionName;
	}
	public String getInputTypeId() {
		return inputTypeId;
	}
	public void setInputTypeId(String inputTypeId) {
		this.inputTypeId = inputTypeId;
	}
	public String getInputType() {
		return inputType;
	}
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	public String getInputlabel() {
		return inputlabel;
	}
	public void setInputlabel(String inputlabel) {
		this.inputlabel = inputlabel;
	}
	public String getInputAlt() {
		return inputAlt;
	}
	public void setInputAlt(String inputAlt) {
		this.inputAlt = inputAlt;
	}
	public String getInputScript() {
		return inputScript;
	}
	public void setInputScript(String inputScript) {
		this.inputScript = inputScript;
	}
	public String getHdnExemption() {
		return hdnExemption;
	}
	public void setHdnExemption(String hdnExemption) {
		this.hdnExemption = hdnExemption;
	}
	public HashMap getMapForm() {
		return mapForm;
	}
	public void setMapForm(HashMap mapForm) {
		this.mapForm = mapForm;
	}
	public ArrayList getAttributeList() {
		return attributeList;
	}
	public void setAttributeList(ArrayList attributeList) {
		this.attributeList = attributeList;
	}
	 
	
}