package com.wipro.igrs.regcompletion.form;


/**
 * ===========================================================================
 * File           :   ApplicantForm.java
 * Description    :   Represents the Applicant Form Class
 * @author        :   Imran Shaik
 * Created Date   :   April 02, 2008
 * Updated Date			Version			UpdatedBy
 * April 14, 2008		1.0				Imran Shaik
 * May 27, 2008			1.1				Imran Shaik 
 * ===========================================================================
 */


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.regcompletion.dto.Common1DTO;
import com.wipro.igrs.regcompletion.dto.CommonDTO;
import com.wipro.igrs.regcompletion.dto.RegInitCompleteDTO;

/**
 * @author Imran Shaik
 *
 */
public class ApplicantForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String regNo;
	private String userId;
	private String compId;
	private String fname;
	private String mname; 
	private String lname;
	private String gendar  ;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String nationality;
	private String postalCode;
	private String emailID;
	private String indaddress;
	private String indcountry;
	private String indstatename;
	private String inddistrict;
	private String indphno;
	private String indmobno;
	private String listID;
	private String idno;
	private String bname;
	private String baddress;
	private String deed;
	private String instrument;
	private String exemption;
	private String pageTitle;
	private ArrayList formFields;
	private String nofProperty;
	private CommonDTO commonDTO;
	private Common1DTO common1DTO; 
	private String ecode;
	private String otherFee;
	private String slotDate;
	private String slotRefId;
	private String slotTime;
	private String stampDuty;
	private String total;
	private String spotStatus;
	private ArrayList partiesList;
	//to upload file
	private String uploadFile;
	private String gMarketVal;
	private String regFee;
	
	//payment details
	private String payId;
	private String payDate;
	private String payAmt;
	private String bankName;
	private String bankAddress;
	private String formName;
	private String actionName;
	private String checkRegNo  ;
	// Added By Aruna
	RegInitCompleteDTO regInitCompDTO=new RegInitCompleteDTO();
	
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return the nofProperty
	 */
	public String getNofProperty() {
		return nofProperty;
	}
	/**
	 * @param nofProperty the nofProperty to set
	 */
	public void setNofProperty(String nofProperty) {
		this.nofProperty = nofProperty;
	}
	/**
	 * @param commonDTO the commonDTO to set
	 */
	public void setCommonDTO(CommonDTO commonDTO) {
		this.commonDTO = commonDTO;
	}
	 
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	/**
	 * @return the gendar
	 */
	public String getGendar() {
		return gendar;
	}
	/**
	 * @param gendar the gendar to set
	 */
	public void setGendar(String gendar) {
		this.gendar = gendar;
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
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return the indaddress
	 */
	public String getIndaddress() {
		return indaddress;
	}
	/**
	 * @param indaddress the indaddress to set
	 */
	public void setIndaddress(String indaddress) {
		this.indaddress = indaddress;
	}
	/**
	 * @return the indcountry
	 */
	public String getIndcountry() {
		return indcountry;
	}
	/**
	 * @param indcountry the indcountry to set
	 */
	public void setIndcountry(String indcountry) {
		this.indcountry = indcountry;
	}
	/**
	 * @return the indstatename
	 */
	public String getIndstatename() {
		return indstatename;
	}
	/**
	 * @param indstatename the indstatename to set
	 */
	public void setIndstatename(String indstatename) {
		this.indstatename = indstatename;
	}
	/**
	 * @return the inddistrict
	 */
	public String getInddistrict() {
		return inddistrict;
	}
	/**
	 * @param inddistrict the inddistrict to set
	 */
	public void setInddistrict(String inddistrict) {
		this.inddistrict = inddistrict;
	}
	/**
	 * @return the indphno
	 */
	public String getIndphno() {
		return indphno;
	}
	/**
	 * @param indphno the indphno to set
	 */
	public void setIndphno(String indphno) {
		this.indphno = indphno;
	}
	/**
	 * @return the indmobno
	 */
	public String getIndmobno() {
		return indmobno;
	}
	/**
	 * @param indmobno the indmobno to set
	 */
	public void setIndmobno(String indmobno) {
		this.indmobno = indmobno;
	}
	/**
	 * @return the listID
	 */
	public String getListID() {
		return listID;
	}
	/**
	 * @param listID the listID to set
	 */
	public void setListID(String listID) {
		this.listID = listID;
	}
	/**
	 * @return the idno
	 */
	public String getIdno() {
		return idno;
	}
	/**
	 * @param idno the idno to set
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	/**
	 * @return the bname
	 */
	public String getBname() {
		return bname;
	}
	/**
	 * @param bname the bname to set
	 */
	public void setBname(String bname) {
		this.bname = bname;
	}
	/**
	 * @return the baddress
	 */
	public String getBaddress() {
		return baddress;
	}
	/**
	 * @param baddress the baddress to set
	 */
	public void setBaddress(String baddress) {
		this.baddress = baddress;
	}
	public CommonDTO getCommonDTO() {
		return new CommonDTO();
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
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return the exemption
	 */
	public String getExemption() {
		return exemption;
	}
	/**
	 * @param exemption the exemption to set
	 */
	public void setExemption(String exemption) {
		this.exemption = exemption;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}
	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	/**
	 * @return the formFields
	 */
	public ArrayList getFormFields() {
		return formFields;
	}
	/**
	 * @param formFields the formFields to set
	 */
	public void setFormFields(ArrayList formFields) {
		this.formFields = formFields;
	}
	/**
	 * @return the ecode
	 */
	public String getEcode() {
		return ecode;
	}
	/**
	 * @param ecode the ecode to set
	 */
	public void setEcode(String ecode) {
		this.ecode = ecode;
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
	 * @return the slotDate
	 */
	public String getSlotDate() {
		return slotDate;
	}
	/**
	 * @param slotDate the slotDate to set
	 */
	public void setSlotDate(String slotDate) {
		this.slotDate = slotDate;
	}
	/**
	 * @return the slotRefId
	 */
	public String getSlotRefId() {
		return slotRefId;
	}
	/**
	 * @param slotRefId the slotRefId to set
	 */
	public void setSlotRefId(String slotRefId) {
		this.slotRefId = slotRefId;
	}
	/**
	 * @return the slotTime
	 */
	public String getSlotTime() {
		return slotTime;
	}
	/**
	 * @param slotTime the slotTime to set
	 */
	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
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
	 * @return the partiesList
	 */
	public ArrayList getPartiesList() {
		return partiesList;
	}
	/**
	 * @param partiesList the partiesList to set
	 */
	public void setPartiesList(ArrayList partiesList) {
		this.partiesList = partiesList;
	}
	/**
	 * @return the common1DTO
	 */
	public Common1DTO getCommon1DTO() {
		return common1DTO;
	}
	/**
	 * @param common1DTO the common1DTO to set
	 */
	public void setCommon1DTO(Common1DTO common1DTO) {
		this.common1DTO = common1DTO;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return the compId
	 */
	public String getCompId() {
		return compId;
	}
	/**
	 * @param compId the compId to set
	 */
	public void setCompId(String compId) {
		this.compId = compId;
	}
	/**
	 * @return the spotStatus
	 */
	public String getSpotStatus() {
		return spotStatus;
	}
	/**
	 * @param spotStatus the spotStatus to set
	 */
	public void setSpotStatus(String spotStatus) {
		this.spotStatus = spotStatus;
	}
	/**
	 * @return the uploadFile
	 */
	public String getUploadFile() {
		return uploadFile;
	}
	/**
	 * @param uploadFile the uploadFile to set
	 */
	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	/**
	 * @return the gMarketVal
	 */
	public String getGMarketVal() {
		return gMarketVal;
	}
	/**
	 * @param marketVal the gMarketVal to set
	 */
	public void setGMarketVal(String marketVal) {
		gMarketVal = marketVal;
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
	/**
	 * @return the payId
	 */
	public String getPayId() {
	    return payId;
	}
	/**
	 * @param payId the payId to set
	 */
	public void setPayId(String payId) {
	    this.payId = payId;
	}
	/**
	 * @return the payDate
	 */
	public String getPayDate() {
	    return payDate;
	}
	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(String payDate) {
	    this.payDate = payDate;
	}
	/**
	 * @return the payAmt
	 */
	public String getPayAmt() {
	    return payAmt;
	}
	/**
	 * @param payAmt the payAmt to set
	 */
	public void setPayAmt(String payAmt) {
	    this.payAmt = payAmt;
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
	public String getCheckRegNo() {
		return checkRegNo;
	}
	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}
	public RegInitCompleteDTO getRegInitCompDTO() {
		return regInitCompDTO;
	}
	public void setRegInitCompDTO(RegInitCompleteDTO regInitCompDTO) {
		this.regInitCompDTO = regInitCompDTO;
	}
}
