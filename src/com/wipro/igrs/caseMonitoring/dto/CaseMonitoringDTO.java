/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.common.dto.CommonDTO;

public class CaseMonitoringDTO implements Serializable {
	
	private String poName;
	
	
	
	public String getPoName() {
		return poName;
	}

	public void setPoName(String poName) {
		this.poName = poName;
	}

	private String caseId;
	private String estampId;
	private String date;
	private String radioCase;
	private String caseNotice;
	private String caseOrderIssue;
	private String caseOrderDr;
	private String caseClose;
	private String status;

	private String caseHead;
	private String caseType1;
	private String caseType2;
	private String caseType3;
	private String rrcCase;
	private String estampType;
	private String estampSubType;
	private String report;
	private String fromDate;
	private String toDate;
	private String lastAction;
	private String caseType;

	private String subType;
	private ArrayList agmpAudits;
	private ArrayList intAudits;
	private String firstName;
	private String midName;
	private String lastName;
	private String gender;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String address;
	private String city;
	private String country;
	private ArrayList countrys;
	private String state;
	private ArrayList states;
	private String pin;
	private String phone;
	private String caseStatus;
	private String finalComment;
	private String drComment;
	private String actionChk;
	private String noticeDetail;
	private String revenueHead;
	private String section;
	private String eamount;
	private String purchaseMode;
	private String estampDate;
	private String expiryDate;
	private String deedType;
	private String drLastCmt;
	private String noticeOne;
	private String radioNotice;
	private String radioClose;
	private String userId;
	private String userCmt;
	private String drValue;
	private String nextDate;
	private String regFee;
	private String stampDuty;
	private String spotCmt;
	private String drCmt;
	private String radioOne;
	private String backOne;
	private String appId;
	private String noticeDate;
	private String hearDate;
	private String recDate;
	private String orderId;
	private String orderDate;
	private String orderDetail;
	private String stampAmount;
	private String refundAmount;
	private String auth;
	private String voucherAmount;
	private String voucherDate;
	private String voucherId;
	private String backbutton;
	private String notice;
	private String stampDetail;
	private String checkbox;
	private String radio;
	private String hrDate;
	private String deductRadio;
	private String amount;
	private String refundAmt;
	private String caseDetail;
	private String issueRadio;
	private String nexthrDate;
	private String cancel;
	private String reportId;
	private String stampType;
	private String stampsubType;
	private String caseSubmit;
	private String revenueId;
	private String sectionId;
	private String sectionHead;
	private String stampsearchAction;
	private String mobNo;
	private String emailId;
	private String proofId;
	private String pmodeDeed;
	private String photoId;
	private String uploadFile;
	private String radioOptMan;
	private String caseView;
	private String backView;
	private String revAction;
	private String refAction;
	private FormFile fileUpload;
	private byte[] fileByte;
	private String fileName;
	private String filePath;
	private HashMap fileDetails;
	private String hdnFileName;
	private String lastActionID;
	// added by shruti
	private String caseregdate;
	private String districtName;
	private String challanNumber;
	private String orderRecDate;
	private String challanDate;
	private String rule;
	private String srname;
	private String sroname;

	private String auditDate;
	private String valAtRegTime;
	private String valFromAgmp;
	private String defStampDuty;
	private String defRegFee;
	private String actionDate;

	// added 4th july 2013
	private String regId;
	private String regDate;
	private String docStampDuty;
	private String docRegFee;

	private String reqStampDuty;
	private String estampNo;
	private String stampNo;
	private String recStampDuty;
	private String poComments;
	private String reqRegFee;
	private String recRegFee;
	private String srComments;

	private String errorMsg;

	// adedd by shruti for estamp refund case
	private String estampTxnId;
	private String estampIssuedOffice;
	private String estampIssuePerson;
	private String estampRefundPerson;
	private String estampRefundPrsnAddress;
	
	
	
	private String partyName;
	private String partyAddress;
	
	private String penaltyAmt;
	//adedd bys hruti-19th july 2013
	private ArrayList caseIdList;
	private String stampAmt;
	
	private String penaltyId;
	
	private String licenseId;
	private String validTo;
	
	private String spName;
	private String spAddress;
	
	private String rrcId;
	
	//added-22 oct 2013
	private String paraId;
	private String objId;
	private String reportDate;
	private String hdnParam;
	private String durationFrom;
	private String durationTo;
	
	private String currentOffId;
	private String recPenaltyAmt;
	
	private String durationChk;
	//added by satbir kumar for case views
	
	private String caseDate;
	
	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getDurationChk() {
		return durationChk;
	}

	public void setDurationChk(String durationChk) {
		this.durationChk = durationChk;
	}

	public String getRecPenaltyAmt() {
		return recPenaltyAmt;
	}

	public void setRecPenaltyAmt(String recPenaltyAmt) {
		this.recPenaltyAmt = recPenaltyAmt;
	}

	public String getCurrentOffId() {
		return currentOffId;
	}

	public void setCurrentOffId(String currentOffId) {
		this.currentOffId = currentOffId;
	}

	private ArrayList<CaseMonitoringDTO>caseDetailsList=new ArrayList<CaseMonitoringDTO>();
	private ArrayList<CaseMonitoringDTO>otherDetailsList=new ArrayList<CaseMonitoringDTO>();
	
	private String viewCaseId;
	private String viewCaseStatus;
	private String viewCaseRevHead;
	private String viewCaseSec;
	
	private String viewCaseSpot;
	private String viewCaseValDR;
	private String viewCaseHearDate;
	private String viewCaseRegFee;
	private String viewCaseStampDut;
	private String viewCaseDRComments;
	
	private String viewCaseActComments;
	private String viewCasePartyRes;
	private String viewCaseActName;
	private String viewCasePartwill;
	private String viewCaseNoticeDate;
	private String viewCaseAttachment;

	private String estampAmt;
	private String estampRefundAmt;
	private String estampFactor;
	// end of addition
	
	public String getEstampFactor() {
		return estampFactor;
	}

	public void setEstampFactor(String estampFactor) {
		this.estampFactor = estampFactor;
	}

	public String getEstampRefundAmt() {
		return estampRefundAmt;
	}

	public void setEstampRefundAmt(String estampRefundAmt) {
		this.estampRefundAmt = estampRefundAmt;
	}

	public String getEstampAmt() {
		return estampAmt;
	}

	public void setEstampAmt(String estampAmt) {
		this.estampAmt = estampAmt;
	}

	public String getDurationFrom() {
		return durationFrom;
	}

	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}

	public String getDurationTo() {
		return durationTo;
	}

	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}

	public String getHdnParam() {
		return hdnParam;
	}

	public void setHdnParam(String hdnParam) {
		this.hdnParam = hdnParam;
	}

	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	private String dueDate;
	private String partyTypeName;
	ArrayList partyList=new ArrayList();

	public ArrayList getPartyList() {
		return partyList;
	}

	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}

	public String getPartyTypeName() {
		return partyTypeName;
	}

	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getRrcId() {
		return rrcId;
	}

	public void setRrcId(String rrcId) {
		this.rrcId = rrcId;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	public String getSpAddress() {
		return spAddress;
	}

	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}

	public String getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public String getPenaltyId() {
		return penaltyId;
	}

	public void setPenaltyId(String penaltyId) {
		this.penaltyId = penaltyId;
	}

	public String getStampAmt() {
		return stampAmt;
	}

	public void setStampAmt(String stampAmt) {
		this.stampAmt = stampAmt;
	}

	public ArrayList getCaseIdList() {
		return caseIdList;
	}

	public void setCaseIdList(ArrayList caseIdList) {
		this.caseIdList = caseIdList;
	}

	public String getPenaltyAmt() {
		return penaltyAmt;
	}

	public void setPenaltyAmt(String penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyAddress() {
		return partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public String getEstampIssuedOffice() {
		return estampIssuedOffice;
	}

	public void setEstampIssuedOffice(String estampIssuedOffice) {
		this.estampIssuedOffice = estampIssuedOffice;
	}

	public String getEstampIssuePerson() {
		return estampIssuePerson;
	}

	public void setEstampIssuePerson(String estampIssuePerson) {
		this.estampIssuePerson = estampIssuePerson;
	}

	public String getEstampRefundPerson() {
		return estampRefundPerson;
	}

	public void setEstampRefundPerson(String estampRefundPerson) {
		this.estampRefundPerson = estampRefundPerson;
	}

	public String getEstampRefundPrsnAddress() {
		return estampRefundPrsnAddress;
	}

	public void setEstampRefundPrsnAddress(String estampRefundPrsnAddress) {
		this.estampRefundPrsnAddress = estampRefundPrsnAddress;
	}

	public String getEstampTxnId() {
		return estampTxnId;
	}

	public void setEstampTxnId(String estampTxnId) {
		this.estampTxnId = estampTxnId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSrComments() {
		return srComments;
	}

	public void setSrComments(String srComments) {
		this.srComments = srComments;
	}

	public String getRecRegFee() {
		return recRegFee;
	}

	public void setRecRegFee(String recRegFee) {
		this.recRegFee = recRegFee;
	}

	public String getReqRegFee() {
		return reqRegFee;
	}

	public void setReqRegFee(String reqRegFee) {
		this.reqRegFee = reqRegFee;
	}

	public String getPoComments() {
		return poComments;
	}

	public void setPoComments(String poComments) {
		this.poComments = poComments;
	}

	public String getRecStampDuty() {
		return recStampDuty;
	}

	public void setRecStampDuty(String recStampDuty) {
		this.recStampDuty = recStampDuty;
	}

	public String getEstampNo() {
		return estampNo;
	}

	public void setEstampNo(String estampNo) {
		this.estampNo = estampNo;
	}

	public String getStampNo() {
		return stampNo;
	}

	public void setStampNo(String stampNo) {
		this.stampNo = stampNo;
	}

	public String getReqStampDuty() {
		return reqStampDuty;
	}

	public void setReqStampDuty(String reqStampDuty) {
		this.reqStampDuty = reqStampDuty;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDocStampDuty() {
		return docStampDuty;
	}

	public void setDocStampDuty(String docStampDuty) {
		this.docStampDuty = docStampDuty;
	}

	public String getDocRegFee() {
		return docRegFee;
	}

	public void setDocRegFee(String docRegFee) {
		this.docRegFee = docRegFee;
	}

	public String getActionDate() {
		return actionDate;
	}

	public void setActionDate(String actionDate) {
		this.actionDate = actionDate;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getValAtRegTime() {
		return valAtRegTime;
	}

	public void setValAtRegTime(String valAtRegTime) {
		this.valAtRegTime = valAtRegTime;
	}

	public String getValFromAgmp() {
		return valFromAgmp;
	}

	public void setValFromAgmp(String valFromAgmp) {
		this.valFromAgmp = valFromAgmp;
	}

	public String getDefStampDuty() {
		return defStampDuty;
	}

	public void setDefStampDuty(String defStampDuty) {
		this.defStampDuty = defStampDuty;
	}

	public String getDefRegFee() {
		return defRegFee;
	}

	public void setDefRegFee(String defRegFee) {
		this.defRegFee = defRegFee;
	}

	public String getSrname() {
		return srname;
	}

	public void setSrname(String srname) {
		this.srname = srname;
	}

	public String getSroname() {
		return sroname;
	}

	public void setSroname(String sroname) {
		this.sroname = sroname;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getChallanDate() {
		return challanDate;
	}

	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}

	public String getOrderRecDate() {
		return orderRecDate;
	}

	public void setOrderRecDate(String orderRecDate) {
		this.orderRecDate = orderRecDate;
	}

	public String getChallanNumber() {
		return challanNumber;
	}

	public void setChallanNumber(String challanNumber) {
		this.challanNumber = challanNumber;
	}

	public String getCaseregdate() {
		return caseregdate;
	}

	public void setCaseregdate(String caseregdate) {
		this.caseregdate = caseregdate;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getLastActionID() {
		return lastActionID;
	}

	public void setLastActionID(String lastActionID) {
		this.lastActionID = lastActionID;
	}

	public HashMap getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(HashMap fileDetails) {
		this.fileDetails = fileDetails;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FormFile getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FormFile fileUpload) {
		this.fileUpload = fileUpload;
	}

	/**
	 * @return
	 */
	public String getCaseId() {
		return caseId;
	}

	/**
	 * @return
	 */
	public String getEstampId() {
		return estampId;
	}

	/**
	 * @return
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public String getCaseNotice() {
		return caseNotice;
	}

	/**
	 * @return
	 */
	public String getCaseOrderIssue() {
		return caseOrderIssue;
	}

	/**
	 * @return
	 */
	public String getcaseOrderDr() {
		return caseOrderDr;
	}

	/**
	 * @return
	 */
	public String getCaseClose() {
		return caseClose;
	}

	/**
	 * @return
	 */
	public String getRadioCase() {
		return radioCase;
	}

	/**
	 * @return
	 */
	public ArrayList getAgmpAudits() {
		return agmpAudits;
	}

	/**
	 * @return
	 */
	public String getCaseType() {
		return caseType;
	}

	/**
	 * @return
	 */
	public ArrayList getIntAudits() {
		return intAudits;
	}

	/**
	 * @return
	 */
	public String getSubType() {
		return subType;
	}

	/**
	 * @param string
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	/**
	 * @param string
	 */
	public void setEstampId(String estampId) {
		this.estampId = estampId;
	}

	/**
	 * @param string
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @param string
	 */
	public void setCaseNotice(String caseNotice) {
		this.caseNotice = caseNotice;
	}

	/**
	 * @param string
	 */
	public void setCaseOrderIssue(String caseOrderIssue) {
		this.caseOrderIssue = caseOrderIssue;
	}

	/**
	 * @param string
	 */
	public void CaseOrderDr(String caseOrderDr) {
		this.caseOrderDr = caseOrderDr;
	}

	/**
	 * @param string
	 */
	public void setCaseClose(String caseClose) {
		this.caseClose = caseClose;
	}

	/**
	 * @param string
	 */
	public void setRadioCase(String radioCase) {
		this.radioCase = radioCase;
	}

	/**
	 * @param list
	 */
	public void setAgmpAudits(ArrayList list) {
		agmpAudits = list;
	}

	/**
	 * @param string
	 */
	public void setCaseType(String string) {
		caseType = string;
	}

	/**
	 * @param list
	 */
	public void setIntAudits(ArrayList list) {
		intAudits = list;
	}

	/**
	 * @param string
	 */
	public void setSubType(String string) {
		subType = string;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getMidName() {
		return midName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountrys(ArrayList countrys) {
		this.countrys = countrys;
	}

	public ArrayList getCountrys() {
		return countrys;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setStates(ArrayList states) {
		this.states = states;
	}

	public ArrayList getStates() {
		return states;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin() {
		return pin;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setFinalComment(String finalComment) {
		this.finalComment = finalComment;
	}

	public String getFinalComment() {
		return finalComment;
	}

	public void setDrComment(String drComment) {
		this.drComment = drComment;
	}

	public String getDrComment() {
		return drComment;
	}

	public void setActionChk(String actionChk) {
		this.actionChk = actionChk;
	}

	public String getActionChk() {
		return actionChk;
	}

	public void setNoticeDetail(String noticeDetail) {
		this.noticeDetail = noticeDetail;
	}

	public String getNoticeDetail() {
		return noticeDetail;
	}

	public void setCaseSubmit(String caseSubmit) {
		this.caseSubmit = caseSubmit;
	}

	public String getCaseSubmit() {
		return caseSubmit;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public String getStampType() {
		return stampType;
	}

	public void setStampsubType(String stampsubType) {
		this.stampsubType = stampsubType;
	}

	public String getStampsubType() {
		return stampsubType;
	}

	public void setRevenueId(String revenueId) {
		this.revenueId = revenueId;
	}

	public String getRevenueId() {
		return revenueId;
	}

	public void setRevenueHead(String revenueHead) {
		this.revenueHead = revenueHead;
	}

	public String getRevenueHead() {
		return revenueHead;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSection() {
		return section;
	}

	public void setEamount(String eamount) {
		this.eamount = eamount;
	}

	public String getEamount() {
		return eamount;
	}

	public void setPurchaseMode(String purchaseMode) {
		this.purchaseMode = purchaseMode;
	}

	public String getPurchaseMode() {
		return purchaseMode;
	}

	public void setEstampDate(String estampDate) {
		this.estampDate = estampDate;
	}

	public String getEstampDate() {
		return estampDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDrLastCmt(String drLastCmt) {
		this.drLastCmt = drLastCmt;
	}

	public String getDrLastCmt() {
		return drLastCmt;
	}

	public void setNoticeOne(String noticeOne) {
		this.noticeOne = noticeOne;
	}

	public String getNoticeOne() {
		return noticeOne;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setRadioNotice(String radioNotice) {
		this.radioNotice = radioNotice;
	}

	public String getRadioNotice() {
		return radioNotice;
	}

	public void setRadioClose(String radioClose) {
		this.radioClose = radioClose;
	}

	public String getRadioClose() {
		return radioClose;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserCmt(String userCmt) {
		this.userCmt = userCmt;
	}

	public String getUserCmt() {
		return userCmt;
	}

	public void setDrValue(String drValue) {
		this.drValue = drValue;
	}

	public String getDrValue() {
		return drValue;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getStampDuty() {
		return stampDuty;
	}

	public void setSpotCmt(String spotCmt) {
		this.spotCmt = spotCmt;
	}

	public String getSpotCmt() {
		return spotCmt;
	}

	public void setDrCmt(String drCmt) {
		this.drCmt = drCmt;
	}

	public String getDrCmt() {
		return drCmt;
	}

	public void setRadioOne(String radioOne) {
		this.radioOne = radioOne;
	}

	public String getRadioOne() {
		return radioOne;
	}

	public void setBackOne(String backOne) {
		this.backOne = backOne;
	}

	public String getBackOne() {
		return backOne;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppId() {
		return appId;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setRecDate(String recDate) {
		this.recDate = recDate;
	}

	public String getRecDate() {
		return recDate;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setHearDate(String hearDate) {
		this.hearDate = hearDate;
	}

	public String getHearDate() {
		return hearDate;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setStampAmount(String stampAmount) {
		this.stampAmount = stampAmount;
	}

	public String getStampAmount() {
		return stampAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getAuth() {
		return auth;
	}

	public void setStampDetail(String stampDetail) {
		this.stampDetail = stampDetail;
	}

	public String getStampDetail() {
		return stampDetail;
	}

	public void setDeductRadio(String deductRadio) {
		this.deductRadio = deductRadio;
	}

	public String getDeductRadio() {
		return deductRadio;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmount() {
		return amount;
	}

	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getRefundAmt() {
		return refundAmt;
	}

	public void setCaseDetail(String caseDetail) {
		this.caseDetail = caseDetail;
	}

	public String getCaseDetail() {
		return caseDetail;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public String getCancel() {
		return cancel;
	}

	public void setVoucherAmount(String voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	public String getVoucherAmount() {
		return voucherAmount;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucherId() {
		return voucherId;
	}

	public void setBackbutton(String backbutton) {
		this.backbutton = backbutton;
	}

	public String getBackbutton() {
		return backbutton;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getNotice() {
		return notice;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setRadio(String radio) {
		this.radio = radio;
	}

	public String getRadio() {
		return radio;
	}

	public void setHrDate(String hrDate) {
		this.hrDate = hrDate;
	}

	public String getHrDate() {
		return hrDate;
	}

	public void setIssueRadio(String issueRadio) {
		this.issueRadio = issueRadio;
	}

	public String getIssueRadio() {
		return issueRadio;
	}

	public void setNexthrDate(String nexthrDate) {
		this.nexthrDate = nexthrDate;
	}

	public String getNexthrDate() {
		return nexthrDate;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setSectionHead(String sectionHead) {
		this.sectionHead = sectionHead;
	}

	public String getSectionHead() {
		return sectionHead;
	}

	public void setStampsearchAction(String stampsearchAction) {
		this.stampsearchAction = stampsearchAction;
	}

	public String getStampsearchAction() {
		return stampsearchAction;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setProofId(String proofId) {
		this.proofId = proofId;
	}

	public String getProofId() {
		return proofId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setPmodeDeed(String pmodeDeed) {
		this.pmodeDeed = pmodeDeed;
	}

	public String getPmodeDeed() {
		return pmodeDeed;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setRadioOptMan(String radioOptMan) {
		this.radioOptMan = radioOptMan;
	}

	public String getRadioOptMan() {
		return radioOptMan;
	}

	public void setCaseView(String caseView) {
		this.caseView = caseView;
	}

	public String getCaseView() {
		return caseView;
	}

	public void setBackView(String backView) {
		this.backView = backView;
	}

	public String getBackView() {
		return backView;
	}

	public void setRevAction(String revAction) {
		this.revAction = revAction;
	}

	public String getRevAction() {
		return revAction;
	}

	public void setRefAction(String refAction) {
		this.refAction = refAction;
	}

	public String getRefAction() {
		return refAction;
	}

	/**
	 * @return
	 */
	public String getCaseHead() {
		return caseHead;
	}

	/**
	 * @return
	 */
	public String getCaseOrderDr() {
		return caseOrderDr;
	}

	/**
	 * @return
	 */
	public String getCaseType1() {
		return caseType1;
	}

	/**
	 * @return
	 */
	public String getCaseType2() {
		return caseType2;
	}

	/**
	 * @return
	 */
	public String getCaseType3() {
		return caseType3;
	}

	/**
	 * @return
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @return
	 */
	public String getLastAction() {
		return lastAction;
	}

	/**
	 * @return
	 */
	public String getEstampType() {
		return estampType;
	}

	/**
	 * @return
	 */
	public String getEstampSubType() {
		return estampSubType;
	}

	/**
	 * @return
	 */
	public String getReport() {
		return report;
	}

	/**
	 * @return
	 */
	public String getRrcCase() {
		return rrcCase;
	}

	/**
	 * @return
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param string
	 */
	public void setCaseHead(String string) {
		caseHead = string;
	}

	/**
	 * @param string
	 */
	public void setCaseOrderDr(String string) {
		caseOrderDr = string;
	}

	/**
	 * @param string
	 */
	public void setCaseType1(String string) {
		caseType1 = string;
	}

	/**
	 * @param string
	 */
	public void setCaseType2(String string) {
		caseType2 = string;
	}

	/**
	 * @param string
	 */
	public void setCaseType3(String string) {
		caseType3 = string;
	}

	/**
	 * @param string
	 */
	public void setFromDate(String string) {
		fromDate = string;
	}

	/**
	 * @param string
	 */
	public void setLastAction(String string) {
		lastAction = string;
	}

	/**
	 * @param string
	 */
	public void setEstampType(String string) {
		estampType = string;
	}

	/**
	 * @param string
	 */
	public void setEstampSubType(String string) {
		estampSubType = string;
	}

	/**
	 * @param string
	 */
	public void setReport(String string) {
		report = string;
	}

	/**
	 * @param string
	 */
	public void setRrcCase(String string) {
		rrcCase = string;
	}

	/**
	 * @param string
	 */
	public void setToDate(String string) {
		toDate = string;
	}

	public String getHdnFileName() {
		return hdnFileName;
	}

	public void setHdnFileName(String hdnFileName) {
		this.hdnFileName = hdnFileName;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public void setViewCaseId(String viewCaseId) {
		this.viewCaseId = viewCaseId;
	}

	public String getViewCaseId() {
		return viewCaseId;
	}

	public void setViewCaseSec(String viewCaseSec) {
		this.viewCaseSec = viewCaseSec;
	}

	public String getViewCaseSec() {
		return viewCaseSec;
	}

	public void setViewCaseRevHead(String viewCaseRevHead) {
		this.viewCaseRevHead = viewCaseRevHead;
	}

	public String getViewCaseRevHead() {
		return viewCaseRevHead;
	}

	public void setViewCaseStatus(String viewCaseStatus) {
		this.viewCaseStatus = viewCaseStatus;
	}

	public String getViewCaseStatus() {
		return viewCaseStatus;
	}

	public void setViewCaseSpot(String viewCaseSpot) {
		this.viewCaseSpot = viewCaseSpot;
	}

	public String getViewCaseSpot() {
		return viewCaseSpot;
	}

	public void setViewCaseValDR(String viewCaseValDR) {
		this.viewCaseValDR = viewCaseValDR;
	}

	public String getViewCaseValDR() {
		return viewCaseValDR;
	}

	public void setViewCaseHearDate(String viewCaseHearDate) {
		this.viewCaseHearDate = viewCaseHearDate;
	}

	public String getViewCaseHearDate() {
		return viewCaseHearDate;
	}

	public void setViewCaseRegFee(String viewCaseRegFee) {
		this.viewCaseRegFee = viewCaseRegFee;
	}

	public String getViewCaseRegFee() {
		return viewCaseRegFee;
	}

	public void setViewCaseStampDut(String viewCaseStampDut) {
		this.viewCaseStampDut = viewCaseStampDut;
	}

	public String getViewCaseStampDut() {
		return viewCaseStampDut;
	}

	public void setViewCaseDRComments(String viewCaseDRComments) {
		this.viewCaseDRComments = viewCaseDRComments;
	}

	public String getViewCaseDRComments() {
		return viewCaseDRComments;
	}

	public void setViewCaseActComments(String viewCaseActComments) {
		this.viewCaseActComments = viewCaseActComments;
	}

	public String getViewCaseActComments() {
		return viewCaseActComments;
	}


	public void setViewCaseActName(String viewCaseActName) {
		this.viewCaseActName = viewCaseActName;
	}

	public String getViewCaseActName() {
		return viewCaseActName;
	}

	public void setViewCaseNoticeDate(String viewCaseNoticeDate) {
		this.viewCaseNoticeDate = viewCaseNoticeDate;
	}

	public String getViewCaseNoticeDate() {
		return viewCaseNoticeDate;
	}

	public void setViewCaseAttachment(String viewCaseAttachment) {
		this.viewCaseAttachment = viewCaseAttachment;
	}

	public String getViewCaseAttachment() {
		return viewCaseAttachment;
	}

	public void setCaseDetailsList(ArrayList<CaseMonitoringDTO> caseDetailsList) {
		this.caseDetailsList = caseDetailsList;
	}

	public ArrayList<CaseMonitoringDTO> getCaseDetailsList() {
		return caseDetailsList;
	}

	public void setOtherDetailsList(ArrayList<CaseMonitoringDTO> otherDetailsList) {
		this.otherDetailsList = otherDetailsList;
	}

	public ArrayList<CaseMonitoringDTO> getOtherDetailsList() {
		return otherDetailsList;
	}

	public void setViewCasePartyRes(String viewCasePartyRes) {
		this.viewCasePartyRes = viewCasePartyRes;
	}

	public String getViewCasePartyRes() {
		return viewCasePartyRes;
	}

	public void setViewCasePartwill(String viewCasePartwill) {
		this.viewCasePartwill = viewCasePartwill;
	}

	public String getViewCasePartwill() {
		return viewCasePartwill;
	}

}