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

public class CaseMonDTO implements Serializable{

	private String caseID;
	private String caseCreatedDate;
	private String caseComments;
	private String caseStampId;
	private String caseStampAmt;
	private String casePenalty;
	private String caseDrComments;
	private String caseType;
	
	private String caseFrmDate;
	private String caseToDate;
	private String revisedPenalty;
	private String caseHearDate;
	private String caseNotice;
	private String radioSelect;
	private String nxtActionName;
	private String casenoticeDate;

	private ArrayList caseIdList;


	// Start Piyush DTo
	private String actionName; // Variable for accessing caller(e.g. Button, Component etc) from form component
	private String formName;

	private String caseHeadName;
	private String caseHeadId;
	private ArrayList caseHeadList;
	private String caseTypeName;
	private String caseTypeId;
	private ArrayList caseTypeList;
	private String sectionHeadName;
	private String sectionHeadId;
	private ArrayList sectionTypeList;
	private String revenueHeadName;
	private String revenueHeadId;
	private ArrayList revenueTypeList;
	private String licenseId;
	private String licenseIdSearch;
	private String caseCriteria;
	private String errorMsg;
	private String spName;
	private String spAddress;
	private String licenseValidTo;
	private String licenseValidFrom;
	private String caseStatus;
	private String caseCloseDate;
	private String finalCaseComments;
	private String caseTxnId;
	private String axnDescr;
	private String axnDescrDate;
	private String receivingDate;
	private String drCommentsDate;
	private ArrayList listComments;
	private ArrayList majorList;
	private ArrayList listRemarks;
	private String compliance;
	private String complianceDate;
	private String complianceBy;

	private String spUserId;
	private String drComments;
	private String caseId;

	private String currentUserId;
	private String currentOffId;
	public String getCurrentOffId() {
		return currentOffId;
	}
	public void setCurrentOffId(String currentOffId) {
		this.currentOffId = currentOffId;
	}
	private String caseActionTypeName;
	private String caseActionTypeNameRef;
	private String caseActionTypeId;
	private ArrayList caseActionTypeList;

	private ArrayList recordsBuffer;
	private ArrayList attachmentsRecordsBuffer;
	private int serialNo;
	private String fromDateSearch;
	private String toDateSearch;
	private String nextPgae;

	private String nxtActionId;

	private String actionChk;
	private String nextAxnRadio;
	private String confirmMsg;
	private String flag;

	//added by shruti
	 private String paymentAction;
	 private String paymentway;
	 private String instone;
	 private String instwo;
	 private String insthird; 
	 private String partyresponse;
	 private String partypaydec;
	 private String lastAction;
	 private String revCommDec;
	 private String revBoardDec;
	 private String highCourtDec;
	 private String supremeCourtDec;
	 private String authDec;
	 private String actionVal;
	 private String balAmt;
	 private String  finalAmt;
	 private String totalRecAmt;
	 private String recStampAmt;
	 private String recRegAmt;
	 private String propTotalRecAmt;
	 private String propRecStampAmt;
	 private String propRecRegAmt;
	 private String pymtresponse;
	 private HashMap fileDetails;
	 private FormFile fileUpload;
	 private byte[] fileByte;
	 private String fileName;
	 private String filePath;
	 private String fileSize;
	 private String docsrno;
	 private String estampType;
	 private String complaintId;
	 private String refundBillNo;
	 private String refundAmount;
	 private String partyresponsedb;
	 private String refundDate;
	 private String finalRecAmt;
	 private String paidAmt;
	 private String penaltyAmt;
	 private String caseCriteria1;
	 private String caseCriteria2;
	 private String revisionNo;
	 private String caseCriteria3;
	 private String newLicenseId; 
	 private String rrcno;
	 private String rrcDate;
	 private String rrcComments; 
	 private String infomsg;
	 private String rrcId;
	 private String caseDueDate; 
	 private String nxtHearDate;
	 
	   //added by shruti
	 private String partyName;
	 private String partyAddress;
	 private String srName;
	 private String deedInsName;
	 private String regDate;
	 private String officeId;
	 private String currentDate;
	 private String officeName;
	 //added--28th oct 2013
	 private String parentDistrictId;
	 private String parentDistrictName;
	 private String parentOfficeId;
	 private String parentOfficeName;
	 private String parentReferenceId;
	 //end
	 
	 private String hdnCaseParam;
	 private String caseRevId;
	 private String caseRefundId;
	 private String caseOthersId;
	 private String proceeding1; 
	 private String midWayClose;
	 private String hiLastAction;
	 private String hiNxtActionName;
	 private String stampDuty;
	 private String mrktVal;
	 private String caseHead;
	 private String adeshikaShulk;
	
	//added by shruti---30 oct 2014
	 private String regNo;
	 
	 public ArrayList getAttachmentsRecordsBuffer() {
		return attachmentsRecordsBuffer;
	}
	public void setAttachmentsRecordsBuffer(ArrayList attachmentsRecordsBuffer) {
		this.attachmentsRecordsBuffer = attachmentsRecordsBuffer;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getAdeshikaShulk() {
		return adeshikaShulk;
	}
	public void setAdeshikaShulk(String adeshikaShulk) {
		this.adeshikaShulk = adeshikaShulk;
	}
	public String getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	public String getMrktVal() {
		return mrktVal;
	}
	public void setMrktVal(String mrktVal) {
		this.mrktVal = mrktVal;
	}
	
	
	public String getCaseHead() {
		return caseHead;
	}
	public void setCaseHead(String caseHead) {
		this.caseHead = caseHead;
	}
	public String getHiNxtActionName() {
		return hiNxtActionName;
	}
	public void setHiNxtActionName(String hiNxtActionName) {
		this.hiNxtActionName = hiNxtActionName;
	}
	public String getHiLastAction() {
		return hiLastAction;
	}
	public void setHiLastAction(String hiLastAction) {
		this.hiLastAction = hiLastAction;
	}
	public String getMidWayClose() {
		return midWayClose;
	}
	public void setMidWayClose(String midWayClose) {
		this.midWayClose = midWayClose;
	}
	public String getProceeding1() {
		return proceeding1;
	}
	public void setProceeding1(String proceeding1) {
		this.proceeding1 = proceeding1;
	}
	public String getCaseRevId() {
		return caseRevId;
	}
	public void setCaseRevId(String caseRevId) {
		this.caseRevId = caseRevId;
	}
	public String getCaseRefundId() {
		return caseRefundId;
	}
	public void setCaseRefundId(String caseRefundId) {
		this.caseRefundId = caseRefundId;
	}
	public String getCaseOthersId() {
		return caseOthersId;
	}
	public void setCaseOthersId(String caseOthersId) {
		this.caseOthersId = caseOthersId;
	}
	public String getHdnCaseParam() {
		return hdnCaseParam;
	}
	public void setHdnCaseParam(String hdnCaseParam) {
		this.hdnCaseParam = hdnCaseParam;
	}
	public String getParentDistrictId() {
		return parentDistrictId;
	}
	public void setParentDistrictId(String parentDistrictId) {
		this.parentDistrictId = parentDistrictId;
	}
	public String getParentDistrictName() {
		return parentDistrictName;
	}
	public void setParentDistrictName(String parentDistrictName) {
		this.parentDistrictName = parentDistrictName;
	}
	public String getParentOfficeId() {
		return parentOfficeId;
	}
	public void setParentOfficeId(String parentOfficeId) {
		this.parentOfficeId = parentOfficeId;
	}
	public String getParentOfficeName() {
		return parentOfficeName;
	}
	public void setParentOfficeName(String parentOfficeName) {
		this.parentOfficeName = parentOfficeName;
	}
	public String getParentReferenceId() {
		return parentReferenceId;
	}
	public void setParentReferenceId(String parentReferenceId) {
		this.parentReferenceId = parentReferenceId;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public String getDeedInsName() {
		return deedInsName;
	}
	public void setDeedInsName(String deedInsName) {
		this.deedInsName = deedInsName;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	public String getNxtHearDate() {
		return nxtHearDate;
	}
	public void setNxtHearDate(String nxtHearDate) {
		this.nxtHearDate = nxtHearDate;
	}
	public String getCaseDueDate() {
		return caseDueDate;
	}
	public void setCaseDueDate(String caseDueDate) {
		this.caseDueDate = caseDueDate;
	}
	public String getRrcId() {
		return rrcId;
	}
	public void setRrcId(String rrcId) {
		this.rrcId = rrcId;
	}
	public String getInfomsg() {
		return infomsg;
	}
	public void setInfomsg(String infomsg) {
		this.infomsg = infomsg;
	}
	public String getRrcComments() {
		return rrcComments;
	}
	public void setRrcComments(String rrcComments) {
		this.rrcComments = rrcComments;
	}
	public String getRrcno() {
		return rrcno;
	}
	public void setRrcno(String rrcno) {
		this.rrcno = rrcno;
	}
	public String getRrcDate() {
		return rrcDate;
	}
	public void setRrcDate(String rrcDate) {
		this.rrcDate = rrcDate;
	}
	public String getNewLicenseId() {
		return newLicenseId;
	}
	public void setNewLicenseId(String newLicenseId) {
		this.newLicenseId = newLicenseId;
	}
	public String getCaseCriteria3() {
		return caseCriteria3;
	}
	public void setCaseCriteria3(String caseCriteria3) {
		this.caseCriteria3 = caseCriteria3;
	}
	public String getRevisionNo() {
		return revisionNo;
	}
	public void setRevisionNo(String revisionNo) {
		this.revisionNo = revisionNo;
	}
	public String getCaseCriteria2() {
		return caseCriteria2;
	}
	public void setCaseCriteria2(String caseCriteria2) {
		this.caseCriteria2 = caseCriteria2;
	}
	public String getCaseCriteria1() {
		return caseCriteria1;
	}
	public void setCaseCriteria1(String caseCriteria1) {
		this.caseCriteria1 = caseCriteria1;
	}
	public String getPenaltyAmt() {
		return penaltyAmt;
	}
	public void setPenaltyAmt(String penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}
	public String getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}
	public String getPropTotalRecAmt() {
		return propTotalRecAmt;
	}
	public void setPropTotalRecAmt(String propTotalRecAmt) {
		this.propTotalRecAmt = propTotalRecAmt;
	}
	public String getPropRecStampAmt() {
		return propRecStampAmt;
	}
	public void setPropRecStampAmt(String propRecStampAmt) {
		this.propRecStampAmt = propRecStampAmt;
	}
	public String getPropRecRegAmt() {
		return propRecRegAmt;
	}
	public void setPropRecRegAmt(String propRecRegAmt) {
		this.propRecRegAmt = propRecRegAmt;
	}
	private String totalPaidAmt;
	 
	
		public String getFinalRecAmt() {
		return finalRecAmt;
	}
	public void setFinalRecAmt(String finalRecAmt) {
		this.finalRecAmt = finalRecAmt;
	}
		public String getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(String refundDate) {
		this.refundDate = refundDate;
	}
		public String getPartyresponsedb() {
		return partyresponsedb;
	}
	public void setPartyresponsedb(String partyresponsedb) {
		this.partyresponsedb = partyresponsedb;
	}
		public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
		public String getRefundBillNo() {
		return refundBillNo;
	}
	public void setRefundBillNo(String refundBillNo) {
		this.refundBillNo = refundBillNo;
	}
		public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(String complaintId) {
		this.complaintId = complaintId;
	}
		public String getEstampType() {
		return estampType;
	}
	public void setEstampType(String estampType) {
		this.estampType = estampType;
	}
		public String getDocsrno() {
		return docsrno;
	}
	public void setDocsrno(String docsrno) {
		this.docsrno = docsrno;
	}
		public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
		public FormFile getFileUpload() {
			return fileUpload;
		}
		public void setFileUpload(FormFile fileUpload) {
			this.fileUpload = fileUpload;
		}
		public byte[] getFileByte() {
			return fileByte;
		}
		public void setFileByte(byte[] fileByte) {
			this.fileByte = fileByte;
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
		public String getHdnFileName() {
			return hdnFileName;
		}
		public void setHdnFileName(String hdnFileName) {
			this.hdnFileName = hdnFileName;
		}
		private String hdnFileName;
	 
	 
	 public HashMap getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(HashMap fileDetails) {
		this.fileDetails = fileDetails;
	}
	public String getPymtresponse() {
		return pymtresponse;
	}
	public void setPymtresponse(String pymtresponse) {
		this.pymtresponse = pymtresponse;
	}
	public String getTotalPaidAmt() {
		return totalPaidAmt;
	}
	public void setTotalPaidAmt(String totalPaidAmt) {
		this.totalPaidAmt = totalPaidAmt;
	}
	private String lastActionId;
	 
	 public String getTotalRecAmt() {
		return totalRecAmt;
	}
	public void setTotalRecAmt(String totalRecAmt) {
		this.totalRecAmt = totalRecAmt;
	}
	public String getRecStampAmt() {
		return recStampAmt;
	}
	public void setRecStampAmt(String recStampAmt) {
		this.recStampAmt = recStampAmt;
	}
	public String getRecRegAmt() {
		return recRegAmt;
	}
	public void setRecRegAmt(String recRegAmt) {
		this.recRegAmt = recRegAmt;
	} 
	 public String getBalAmt() {
		return balAmt;
	}
	public void setBalAmt(String balAmt) {
		this.balAmt = balAmt;
	}
	public String getFinalAmt() {
		return finalAmt;
	}
	public void setFinalAmt(String finalAmt) {
		this.finalAmt = finalAmt;
	}
	 public String getActionVal() {
		return actionVal;
	}
	public void setActionVal(String actionVal) {
		this.actionVal = actionVal;
	}
	public String getAuthDec() {
		return authDec;
	}
	public void setAuthDec(String authDec) {
		this.authDec = authDec;
	}
	public String getLastActionId() {
		return lastActionId;
	}
	public void setLastActionId(String lastActionId) {
		this.lastActionId = lastActionId;
	}
	public String getRevBoardDec() {
		return revBoardDec;
	}
	public void setRevBoardDec(String revBoardDec) {
		this.revBoardDec = revBoardDec;
	}
	public String getHighCourtDec() {
		return highCourtDec;
	}
	public void setHighCourtDec(String highCourtDec) {
		this.highCourtDec = highCourtDec;
	}
	public String getSupremeCourtDec() {
		return supremeCourtDec;
	}
	public void setSupremeCourtDec(String supremeCourtDec) {
		this.supremeCourtDec = supremeCourtDec;
	}
	public String getRevCommDec() {
		return revCommDec;
	}
	public void setRevCommDec(String revCommDec) {
		this.revCommDec = revCommDec;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public String getPartypaydec() {
		return partypaydec;
	}
	public void setPartypaydec(String partypaydec) {
		this.partypaydec = partypaydec;
	}
	public String getPartyresponse() {
		return partyresponse;
	}
	public void setPartyresponse(String partyresponse) {
		this.partyresponse = partyresponse;
	}
	public String getPaymentAction() {
			return paymentAction;
		}
		public void setPaymentAction(String paymentAction) {
			this.paymentAction = paymentAction;
		}
		public String getPaymentway() {
			return paymentway;
		}
		public void setPaymentway(String paymentway) {
			this.paymentway = paymentway;
		}
		public String getInstone() {
			return instone;
		}
		public void setInstone(String instone) {
			this.instone = instone;
		}
		public String getInstwo() {
			return instwo;
		}
		public void setInstwo(String instwo) {
			this.instwo = instwo;
		}
		public String getInsthird() {
			return insthird;
		}
		public void setInsthird(String insthird) {
			this.insthird = insthird;
		}
	/**
	 * @return the nextPgae
	 */
	public String getNextPgae() {
		return nextPgae;
	}
	/**
	 * @param nextPgae the nextPgae to set
	 */
	public void setNextPgae(String nextPgae) {
		this.nextPgae = nextPgae;
	}
	//End Piyush DTO
	/**
	 * @return the caseID
	 */
	public String getCaseID() {
		return caseID;
	}
	/**
	 * @param caseID the caseID to set
	 */
	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}
	/**
	 * @return the caseCreatedDate
	 */
	public String getCaseCreatedDate() {
		return caseCreatedDate;
	}
	/**
	 * @param caseCreatedDate the caseCreatedDate to set
	 */
	public void setCaseCreatedDate(String caseCreatedDate) {
		this.caseCreatedDate = caseCreatedDate;
	}
	/**
	 * @return the caseIdList
	 */
	public ArrayList getCaseIdList() {
		return caseIdList;
	}
	/**
	 * @param caseIdList the caseIdList to set
	 */
	public void setCaseIdList(ArrayList caseIdList) {
		this.caseIdList = caseIdList;
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
	 * @return the caseHeadName
	 */
	public String getCaseHeadName() {
		return caseHeadName;
	}
	/**
	 * @param caseHeadName the caseHeadName to set
	 */
	public void setCaseHeadName(String caseHeadName) {
		this.caseHeadName = caseHeadName;
	}
	/**
	 * @return the caseHeadId
	 */
	public String getCaseHeadId() {
		return caseHeadId;
	}
	/**
	 * @param caseHeadId the caseHeadId to set
	 */
	public void setCaseHeadId(String caseHeadId) {
		this.caseHeadId = caseHeadId;
	}
	/**
	 * @return the caseHeadList
	 */
	public ArrayList getCaseHeadList() {
		return caseHeadList;
	}
	/**
	 * @param caseHeadList the caseHeadList to set
	 */
	public void setCaseHeadList(ArrayList caseHeadList) {
		this.caseHeadList = caseHeadList;
	}
	/**
	 * @return the caseTypeName
	 */
	public String getCaseTypeName() {
		return caseTypeName;
	}
	/**
	 * @param caseTypeName the caseTypeName to set
	 */
	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}
	/**
	 * @return the caseTypeId
	 */
	public String getCaseTypeId() {
		return caseTypeId;
	}
	/**
	 * @param caseTypeId the caseTypeId to set
	 */
	public void setCaseTypeId(String caseTypeId) {
		this.caseTypeId = caseTypeId;
	}
	/**
	 * @return the caseTypeList
	 */
	public ArrayList getCaseTypeList() {
		return caseTypeList;
	}
	/**
	 * @param caseTypeList the caseTypeList to set
	 */
	public void setCaseTypeList(ArrayList caseTypeList) {
		this.caseTypeList = caseTypeList;
	}
	/**
	 * @return the sectionHeadName
	 */
	public String getSectionHeadName() {
		return sectionHeadName;
	}
	/**
	 * @param sectionHeadName the sectionHeadName to set
	 */
	public void setSectionHeadName(String sectionHeadName) {
		this.sectionHeadName = sectionHeadName;
	}
	/**
	 * @return the sectionHeadId
	 */
	public String getSectionHeadId() {
		return sectionHeadId;
	}
	/**
	 * @param sectionHeadId the sectionHeadId to set
	 */
	public void setSectionHeadId(String sectionHeadId) {
		this.sectionHeadId = sectionHeadId;
	}
	/**
	 * @return the sectionTypeList
	 */
	public ArrayList getSectionTypeList() {
		return sectionTypeList;
	}
	/**
	 * @param sectionTypeList the sectionTypeList to set
	 */
	public void setSectionTypeList(ArrayList sectionTypeList) {
		this.sectionTypeList = sectionTypeList;
	}
	/**
	 * @return the revenueHeadName
	 */
	public String getRevenueHeadName() {
		return revenueHeadName;
	}
	/**
	 * @param revenueHeadName the revenueHeadName to set
	 */
	public void setRevenueHeadName(String revenueHeadName) {
		this.revenueHeadName = revenueHeadName;
	}
	/**
	 * @return the revenueHeadId
	 */
	public String getRevenueHeadId() {
		return revenueHeadId;
	}
	/**
	 * @param revenueHeadId the revenueHeadId to set
	 */
	public void setRevenueHeadId(String revenueHeadId) {
		this.revenueHeadId = revenueHeadId;
	}
	/**
	 * @return the revenueTypeList
	 */
	public ArrayList getRevenueTypeList() {
		return revenueTypeList;
	}
	/**
	 * @param revenueTypeList the revenueTypeList to set
	 */
	public void setRevenueTypeList(ArrayList revenueTypeList) {
		this.revenueTypeList = revenueTypeList;
	}
	/**
	 * @return the licenseId
	 */
	public String getLicenseId() {
		return licenseId;
	}
	/**
	 * @param licenseId the licenseId to set
	 */
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	/**
	 * @return the licenseIdSearch
	 */
	public String getLicenseIdSearch() {
		return licenseIdSearch;
	}
	/**
	 * @param licenseIdSearch the licenseIdSearch to set
	 */
	public void setLicenseIdSearch(String licenseIdSearch) {
		this.licenseIdSearch = licenseIdSearch;
	}
	/**
	 * @return the caseCriteria
	 */
	public String getCaseCriteria() {
		return caseCriteria;
	}
	/**
	 * @param caseCriteria the caseCriteria to set
	 */
	public void setCaseCriteria(String caseCriteria) {
		this.caseCriteria = caseCriteria;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the spName
	 */
	public String getSpName() {
		return spName;
	}
	/**
	 * @param spName the spName to set
	 */
	public void setSpName(String spName) {
		this.spName = spName;
	}
	/**
	 * @return the spAddress
	 */
	public String getSpAddress() {
		return spAddress;
	}
	/**
	 * @param spAddress the spAddress to set
	 */
	public void setSpAddress(String spAddress) {
		this.spAddress = spAddress;
	}
	/**
	 * @return the licenseValidTo
	 */
	public String getLicenseValidTo() {
		return licenseValidTo;
	}
	/**
	 * @param licenseValidTo the licenseValidTo to set
	 */
	public void setLicenseValidTo(String licenseValidTo) {
		this.licenseValidTo = licenseValidTo;
	}
	/**
	 * @return the licenseValidFrom
	 */
	public String getLicenseValidFrom() {
		return licenseValidFrom;
	}
	/**
	 * @param licenseValidFrom the licenseValidFrom to set
	 */
	public void setLicenseValidFrom(String licenseValidFrom) {
		this.licenseValidFrom = licenseValidFrom;
	}
	/**
	 * @return the caseComments
	 */
	public String getCaseComments() {
		return caseComments;
	}
	/**
	 * @param caseComments the caseComments to set
	 */
	public void setCaseComments(String caseComments) {
		this.caseComments = caseComments;
	}
	/**
	 * @return the caseStampId
	 */
	public String getCaseStampId() {
		return caseStampId;
	}
	/**
	 * @param caseStampId the caseStampId to set
	 */
	public void setCaseStampId(String caseStampId) {
		this.caseStampId = caseStampId;
	}
	/**
	 * @return the caseStampAmt
	 */
	public String getCaseStampAmt() {
		return caseStampAmt;
	}
	/**
	 * @param caseStampAmt the caseStampAmt to set
	 */
	public void setCaseStampAmt(String caseStampAmt) {
		this.caseStampAmt = caseStampAmt;
	}
	/**
	 * @return the casePenalty
	 */
	public String getCasePenalty() {
		return casePenalty;
	}
	/**
	 * @param casePenalty the casePenalty to set
	 */
	public void setCasePenalty(String casePenalty) {
		this.casePenalty = casePenalty;
	}
	/**
	 * @return the caseDrComments
	 */
	public String getCaseDrComments() {
		return caseDrComments;
	}
	/**
	 * @param caseDrComments the caseDrComments to set
	 */
	public void setCaseDrComments(String caseDrComments) {
		this.caseDrComments = caseDrComments;
	}
	/**
	 * @return the caseType
	 */
	public String getCaseType() {
		return caseType;
	}
	/**
	 * @param caseType the caseType to set
	 */
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	/**
	 * @return the caseFrmDate
	 */
	public String getCaseFrmDate() {
		return caseFrmDate;
	}
	/**
	 * @param caseFrmDate the caseFrmDate to set
	 */
	public void setCaseFrmDate(String caseFrmDate) {
		this.caseFrmDate = caseFrmDate;
	}
	/**
	 * @return the caseToDate
	 */
	public String getCaseToDate() {
		return caseToDate;
	}
	/**
	 * @param caseToDate the caseToDate to set
	 */
	public void setCaseToDate(String caseToDate) {
		this.caseToDate = caseToDate;
	}
	/**
	 * @return the spUserId
	 */
	public String getSpUserId() {
		return spUserId;
	}
	/**
	 * @param spUserId the spUserId to set
	 */
	public void setSpUserId(String spUserId) {
		this.spUserId = spUserId;
	}
	/**
	 * @return the drComments
	 */
	public String getDrComments() {
		return drComments;
	}
	/**
	 * @param drComments the drComments to set
	 */
	public void setDrComments(String drComments) {
		this.drComments = drComments;
	}
	/**
	 * @return the caseId
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId the caseId to set
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return the caseActionTypeName
	 */
	public String getCaseActionTypeName() {
		return caseActionTypeName;
	}
	/**
	 * @param caseActionTypeName the caseActionTypeName to set
	 */
	public void setCaseActionTypeName(String caseActionTypeName) {
		this.caseActionTypeName = caseActionTypeName;
	}
	/**
	 * @return the caseActionTypeNameRef
	 */
	public String getCaseActionTypeNameRef() {
		return caseActionTypeNameRef;
	}
	/**
	 * @param caseActionTypeNameRef the caseActionTypeNameRef to set
	 */
	public void setCaseActionTypeNameRef(String caseActionTypeNameRef) {
		this.caseActionTypeNameRef = caseActionTypeNameRef;
	}
	/**
	 * @return the caseActionTypeId
	 */
	public String getCaseActionTypeId() {
		return caseActionTypeId;
	}
	/**
	 * @param caseActionTypeId the caseActionTypeId to set
	 */
	public void setCaseActionTypeId(String caseActionTypeId) {
		this.caseActionTypeId = caseActionTypeId;
	}
	/**
	 * @return the caseActionTypeList
	 */
	public ArrayList getCaseActionTypeList() {
		return caseActionTypeList;
	}
	/**
	 * @param caseActionTypeList the caseActionTypeList to set
	 */
	public void setCaseActionTypeList(ArrayList caseActionTypeList) {
		this.caseActionTypeList = caseActionTypeList;
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
	 * @return the fromDateSearch
	 */
	public String getFromDateSearch() {
		return fromDateSearch;
	}
	/**
	 * @param fromDateSearch the fromDateSearch to set
	 */
	public void setFromDateSearch(String fromDateSearch) {
		this.fromDateSearch = fromDateSearch;
	}
	/**
	 * @return the toDateSearch
	 */
	public String getToDateSearch() {
		return toDateSearch;
	}
	/**
	 * @param toDateSearch the toDateSearch to set
	 */
	public void setToDateSearch(String toDateSearch) {
		this.toDateSearch = toDateSearch;
	}
	/**
	 * @return the revisedPenalty
	 */
	public String getRevisedPenalty() {
		return revisedPenalty;
	}
	/**
	 * @param revisedPenalty the revisedPenalty to set
	 */
	public void setRevisedPenalty(String revisedPenalty) {
		this.revisedPenalty = revisedPenalty;
	}
	/**
	 * @return the caseHearDate
	 */
	public String getCaseHearDate() {
		return caseHearDate;
	}
	/**
	 * @param caseHearDate the caseHearDate to set
	 */
	public void setCaseHearDate(String caseHearDate) {
		this.caseHearDate = caseHearDate;
	}
	/**
	 * @return the caseNotice
	 */
	public String getCaseNotice() {
		return caseNotice;
	}
	/**
	 * @param caseNotice the caseNotice to set
	 */
	public void setCaseNotice(String caseNotice) {
		this.caseNotice = caseNotice;
	}
	/**
	 * @return the radioSelect
	 */
	public String getRadioSelect() {
		return radioSelect;
	}
	/**
	 * @param radioSelect the radioSelect to set
	 */
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}
	/**
	 * @return the caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}
	/**
	 * @param caseStatus the caseStatus to set
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	/**
	 * @return the caseCloseDate
	 */
	public String getCaseCloseDate() {
		return caseCloseDate;
	}
	/**
	 * @param caseCloseDate the caseCloseDate to set
	 */
	public void setCaseCloseDate(String caseCloseDate) {
		this.caseCloseDate = caseCloseDate;
	}
	/**
	 * @return the finalCaseComments
	 */
	public String getFinalCaseComments() {
		return finalCaseComments;
	}
	/**
	 * @param finalCaseComments the finalCaseComments to set
	 */
	public void setFinalCaseComments(String finalCaseComments) {
		this.finalCaseComments = finalCaseComments;
	}
	/**
	 * @return the caseTxnId
	 */
	public String getCaseTxnId() {
		return caseTxnId;
	}
	/**
	 * @param caseTxnId the caseTxnId to set
	 */
	public void setCaseTxnId(String caseTxnId) {
		this.caseTxnId = caseTxnId;
	}
	/**
	 * @return the axnDescr
	 */
	public String getAxnDescr() {
		return axnDescr;
	}
	/**
	 * @param axnDescr the axnDescr to set
	 */
	public void setAxnDescr(String axnDescr) {
		this.axnDescr = axnDescr;
	}
	/**
	 * @return the axnDescrDate
	 */
	public String getAxnDescrDate() {
		return axnDescrDate;
	}
	/**
	 * @param axnDescrDate the axnDescrDate to set
	 */
	public void setAxnDescrDate(String axnDescrDate) {
		this.axnDescrDate = axnDescrDate;
	}
	/**
	 * @return the receivingDate
	 */
	public String getReceivingDate() {
		return receivingDate;
	}
	/**
	 * @param receivingDate the receivingDate to set
	 */
	public void setReceivingDate(String receivingDate) {
		this.receivingDate = receivingDate;
	}
	/**
	 * @return the drCommentsDate
	 */
	public String getDrCommentsDate() {
		return drCommentsDate;
	}
	/**
	 * @param drCommentsDate the drCommentsDate to set
	 */
	public void setDrCommentsDate(String drCommentsDate) {
		this.drCommentsDate = drCommentsDate;
	}
	/**
	 * @return the listComments
	 */
	public ArrayList getListComments() {
		return listComments;
	}
	/**
	 * @param listComments the listComments to set
	 */
	public void setListComments(ArrayList listComments) {
		this.listComments = listComments;
	}
	/**
	 * @return the majorList
	 */
	public ArrayList getMajorList() {
		return majorList;
	}
	/**
	 * @param majorList the majorList to set
	 */
	public void setMajorList(ArrayList majorList) {
		this.majorList = majorList;
	}
	/**
	 * @return the listRemarks
	 */
	public ArrayList getListRemarks() {
		return listRemarks;
	}
	/**
	 * @param listRemarks the listRemarks to set
	 */
	public void setListRemarks(ArrayList listRemarks) {
		this.listRemarks = listRemarks;
	}
	/**
	 * @return the nxtActionName
	 */
	public String getNxtActionName() {
		return nxtActionName;
	}
	/**
	 * @param nxtActionName the nxtActionName to set
	 */
	public void setNxtActionName(String nxtActionName) {
		this.nxtActionName = nxtActionName;
	}
	/**
	 * @return the casenoticeDate
	 */
	public String getCasenoticeDate() {
		return casenoticeDate;
	}
	/**
	 * @param casenoticeDate the casenoticeDate to set
	 */
	public void setCasenoticeDate(String casenoticeDate) {
		this.casenoticeDate = casenoticeDate;
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
	 * @return the complianceDate
	 */
	public String getComplianceDate() {
		return complianceDate;
	}
	/**
	 * @param complianceDate the complianceDate to set
	 */
	public void setComplianceDate(String complianceDate) {
		this.complianceDate = complianceDate;
	}
	/**
	 * @return the complianceBy
	 */
	public String getComplianceBy() {
		return complianceBy;
	}
	/**
	 * @param complianceBy the complianceBy to set
	 */
	public void setComplianceBy(String complianceBy) {
		this.complianceBy = complianceBy;
	}
	/**
	 * @return the currentUserId
	 */
	public String getCurrentUserId() {
		return currentUserId;
	}
	/**
	 * @param currentUserId the currentUserId to set
	 */
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	public String getNxtActionId() {
		return nxtActionId;
	}
	public void setNxtActionId(String nxtActionId) {
		this.nxtActionId = nxtActionId;
	}
	public String getActionChk() {
		return actionChk;
	}
	public void setActionChk(String actionChk) {
		this.actionChk = actionChk;
	}
	public String getNextAxnRadio() {
		return nextAxnRadio;
	}
	public void setNextAxnRadio(String nextAxnRadio) {
		this.nextAxnRadio = nextAxnRadio;
	}
	public String getConfirmMsg() {
		return confirmMsg;
	}
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

	//End Piyush code



}// DTO Close
