package com.wipro.igrs.visitBooking.form;

/**
 * ===========================================================================
 * File           :   VisitBookingForm.java
 * Description    :   Represents the Bean Class

 * Author         :   Pavani Param
 * Created Date   :   Apr 08,2008.

 * ===========================================================================
 */

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.visitBooking.dto.VisitBookingDTO;


public class VisitBookingForm extends ActionForm {
	// --- START---Applicant Details.
	private Logger logger = (Logger) Logger.getLogger(VisitBookingForm.class);

	private String firstName;
	private String middleName;
	private String lastName;
	private String gender = "M";
	private String age;
	private String fatherName;
	private String motherName;
	private String address;
	private String city;
	private String postalCode;
	private String valFrom;
	private String country;
	private String state;
	private String district;
	private String phoneNumber;
	private String mobileNumber;
	private String emailID;
	private String bankName;
	private String bankAddr;
	private String photoID;
	private String idNo;
	private String pageTitle;

	// --END-- Applicant Details.

	VisitBookingDTO objVBDto = new VisitBookingDTO();

	// ----START--Registration Application Details.
	private String reqApplNo;
	private String reqFirstName;
	private String reqMiddleName;
	private String reqLastName;
	private String reqPhoneNo;
	private String reqMobileNo;
	private String reqReason;
	private String reqDate;
	private String reqTime;
	private String visitReason;
	private String visitDistrict;
	private String visitAddr;
	private String visitPCode;
	private String fee;
	private String others;
	private String total;
	private String visitRemarks;
	private String visitTxnId;
	private String doReq;

	private String PmtTxnId;

	// ----END--Registration Application Details

	// ----START--Registration Application Details.
	private String viewType = "RefId";
	private String refId;
	private String durFrom;
	private String durTo;
	private String status;
	private String referenceID;
	private String applicationNo;
	private String dateOfRequest;
	private String dateOfVisit;

	private ArrayList visitViewList;
	private ArrayList remarksList;

	// ----END--Registration Application Details

	/**
	 * @return the visitViewList
	 */
	public ArrayList getVisitViewList() {
		return visitViewList;
	}

	/**
	 * @param visitViewList
	 *            the visitViewList to set
	 */
	public void setVisitViewList(ArrayList visitViewList) {
		this.visitViewList = visitViewList;
	}

	/**
	 * @return the doReq
	 */
	public String getDoReq() {
		return doReq;
	}

	/**
	 * @param doReq
	 *            the doReq to set
	 */
	public void setDoReq(String doReq) {
		this.doReq = doReq;
	}

	/**
	 * @return the visitTxnId
	 */
	public String getVisitTxnId() {
		return visitTxnId;
	}

	/**
	 * @param visitTxnId
	 *            the visitTxnId to set
	 */
	public void setVisitTxnId(String visitTxnId) {
		this.visitTxnId = visitTxnId;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		logger.info("enter in to  VisitBookingForm");
		super.reset(mapping, request);
	}

	// --To check Alphanucmeric characters
	/**
	 * Check AlphNumeric values.
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkAlphaN(String strValue) {
		boolean blnFlag = false;
		for (int i = 1; i < strValue.length(); i++) {
			char ch = strValue.charAt(i);
			if ((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90)
					|| (ch >= 97 && ch <= 122)) {
				blnFlag = true;
			} else {
				blnFlag = false;
				break;
			}
		}
		return blnFlag;
	}

	// --To check Numbers.

	public boolean checkNum(String strValue) {
		boolean blnFlag = false;
		try {
			int i = Integer.parseInt(strValue);
			logger.info("Age value" + i);
		} catch (NumberFormatException e) {
			logger.info("exception in CheckNum" + e);
			blnFlag = true;
		}
		blnFlag = false;
		return blnFlag;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middleName to set
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
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
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
	 * @param age
	 *            the age to set
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
	 * @param fatherName
	 *            the fatherName to set
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
	 * @param motherName
	 *            the motherName to set
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the valFrom
	 */
	public String getValFrom() {
		return valFrom;
	}

	/**
	 * @param valFrom
	 *            the valFrom to set
	 */
	public void setValFrom(String valFrom) {
		this.valFrom = valFrom;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the phoneNumber
	 */
	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *            the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}

	/**
	 * @param emailID
	 *            the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	/**
	 * @return the bankAddr
	 */
	public String getBankAddr() {
		return bankAddr;
	}

	/**
	 * @param bankAddr
	 *            the bankAddr to set
	 */
	public void setBankAddr(String bankAddr) {
		this.bankAddr = bankAddr;
	}

	/**
	 * @return the photoID
	 */
	public String getPhotoID() {
		return photoID;
	}

	/**
	 * @param photoID
	 *            the photoID to set
	 */
	public void setPhotoID(String photoID) {
		this.photoID = photoID;
	}

	/**
	 * @return the idNo
	 */
	public String getIdNo() {
		return idNo;
	}

	/**
	 * @param idNo
	 *            the idNo to set
	 */
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @return the objVBDto
	 */
	public VisitBookingDTO getObjVBDto() {
		return objVBDto;
	}

	/**
	 * @param objVBDto
	 *            the objVBDto to set
	 */
	public void setObjVBDto(VisitBookingDTO objVBDto) {
		this.objVBDto = objVBDto;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the reqApplNo
	 */
	public String getReqApplNo() {
		return reqApplNo;
	}

	/**
	 * @param reqApplNo
	 *            the reqApplNo to set
	 */
	public void setReqApplNo(String reqApplNo) {
		this.reqApplNo = reqApplNo;
	}

	/**
	 * @return the reqFirstName
	 */
	public String getReqFirstName() {
		return reqFirstName;
	}

	/**
	 * @param reqFirstName
	 *            the reqFirstName to set
	 */
	public void setReqFirstName(String reqFirstName) {
		this.reqFirstName = reqFirstName;
	}

	/**
	 * @return the reqMiddleName
	 */
	public String getReqMiddleName() {
		return reqMiddleName;
	}

	/**
	 * @param reqMiddleName
	 *            the reqMiddleName to set
	 */
	public void setReqMiddleName(String reqMiddleName) {
		this.reqMiddleName = reqMiddleName;
	}

	/**
	 * @return the reqLastName
	 */
	public String getReqLastName() {
		return reqLastName;
	}

	/**
	 * @param reqLastName
	 *            the reqLastName to set
	 */
	public void setReqLastName(String reqLastName) {
		this.reqLastName = reqLastName;
	}

	/**
	 * @return the reqPhoneNo
	 */
	public String getReqPhoneNo() {
		return reqPhoneNo;
	}

	/**
	 * @param reqPhoneNo
	 *            the reqPhoneNo to set
	 */
	public void setReqPhoneNo(String reqPhoneNo) {
		this.reqPhoneNo = reqPhoneNo;
	}

	/**
	 * @return the reqmobileNo
	 */
	public String getReqMobileNo() {
		return reqMobileNo;
	}

	/**
	 * @param reqmobileNo
	 *            the reqmobileNo to set
	 */
	public void setReqMobileNo(String reqMobileNo) {
		this.reqMobileNo = reqMobileNo;
	}

	/**
	 * @return the reqReason
	 */
	public String getReqReason() {
		return reqReason;
	}

	/**
	 * @param reqReason
	 *            the reqReason to set
	 */
	public void setReqReason(String reqReason) {
		this.reqReason = reqReason;
	}

	/**
	 * @return the reqDate
	 */
	public String getReqDate() {
		return reqDate;
	}

	/**
	 * @param reqDate
	 *            the reqDate to set
	 */
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	/**
	 * @return the reqTime
	 */
	public String getReqTime() {
		return reqTime;
	}

	/**
	 * @param reqTime
	 *            the reqTime to set
	 */
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	/**
	 * @return the visitReason
	 */
	public String getVisitReason() {
		return visitReason;
	}

	/**
	 * @param visitReason
	 *            the visitReason to set
	 */
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	/**
	 * @return the visitDistrict
	 */
	public String getVisitDistrict() {
		return visitDistrict;
	}

	/**
	 * @param visitDistrict
	 *            the visitDistrict to set
	 */
	public void setVisitDistrict(String visitDistrict) {
		this.visitDistrict = visitDistrict;
	}

	/**
	 * @return the visitAddr
	 */
	public String getVisitAddr() {
		return visitAddr;
	}

	/**
	 * @param visitAddr
	 *            the visitAddr to set
	 */
	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}

	/**
	 * @return the fee
	 */
	public String getFee() {
		return fee;
	}

	/**
	 * @param fee
	 *            the fee to set
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
	 * @param others
	 *            the others to set
	 */
	public void setOthers(String others) {
		this.others = others;
	}

	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * @return the visitRemarks
	 */
	public String getVisitRemarks() {
		return visitRemarks;
	}

	/**
	 * @param visitRemarks
	 *            the visitRemarks to set
	 */
	public void setVisitRemarks(String visitRemarks) {
		this.visitRemarks = visitRemarks;
	}

	/**
	 * @return the visitPCode
	 */
	public String getVisitPCode() {
		return visitPCode;
	}

	/**
	 * @param visitPCode
	 *            the visitPCode to set
	 */
	public void setVisitPCode(String visitPCode) {
		this.visitPCode = visitPCode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * @param viewType
	 *            the viewType to set
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * @param refId
	 *            the refId to set
	 */
	public void setRefId(String refId) {
		this.refId = refId;
	}

	/**
	 * @return the referenceID
	 */
	public String getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID
	 *            the referenceID to set
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}

	/**
	 * @return the applicationNo
	 */
	public String getApplicationNo() {
		return applicationNo;
	}

	/**
	 * @param applicationNo
	 *            the applicationNo to set
	 */
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	/**
	 * @return the dateOfRequest
	 */
	public String getDateOfRequest() {
		return dateOfRequest;
	}

	/**
	 * @param dateOfRequest
	 *            the dateOfRequest to set
	 */
	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	/**
	 * @return the dateOfVisit
	 */
	public String getDateOfVisit() {
		return dateOfVisit;
	}

	/**
	 * @param dateOfVisit
	 *            the dateOfVisit to set
	 */
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	/**
	 * @return the durFrom
	 */
	public String getDurFrom() {
		return durFrom;
	}

	/**
	 * @param durFrom
	 *            the durFrom to set
	 */
	public void setDurFrom(String durFrom) {
		this.durFrom = durFrom;
	}

	/**
	 * @return the durTo
	 */
	public String getDurTo() {
		return durTo;
	}

	/**
	 * @param durTo
	 *            the durTo to set
	 */
	public void setDurTo(String durTo) {
		this.durTo = durTo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the remarksList
	 */
	public ArrayList getRemarksList() {
		return remarksList;
	}

	/**
	 * @param remarksList
	 *            the remarksList to set
	 */
	public void setRemarksList(ArrayList remarksList) {
		this.remarksList = remarksList;
	}

	/**
	 * @return the pmtTxnId
	 */
	public String getPmtTxnId() {
		return PmtTxnId;
	}

	/**
	 * @param pmtTxnId
	 *            the pmtTxnId to set
	 */
	public void setPmtTxnId(String pmtTxnId) {
		PmtTxnId = pmtTxnId;
	}

}