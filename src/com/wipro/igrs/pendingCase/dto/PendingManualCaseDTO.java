package com.wipro.igrs.pendingCase.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class PendingManualCaseDTO implements Serializable{
	private String caseType;
	private String referenceNo;
	private String caseNumber;
	private String partyDtls;
	private String propDtls;
	private String stampAmt;

	private String caseStatus;
	private String remarks;
	private ArrayList sectionList=new ArrayList();
	private String sectionId;
	private String sectionName;
	private ArrayList caseStatusList=new ArrayList();
	private String caseStatusId;
	private String caseStatusName;
	
	private ArrayList searchList=new ArrayList();
	private String createdDate;
	private String loggedInUser;
	private String zoneId;
	// added by satbir kumar---
	
	private FormFile file;
	private String documentName;
	 private transient byte[] docContents;
	private String docFileSize;
	private String uploadDocPath;
	private int  uploadId;
	private String dbaseUpload;
	private String uploadSrNo;
	private String idCheck;
    private String revHeadName;
    private String revHeadId;
    private String revHeadType;
    private String sectionType;
    private String mktValueDOc;
    private String praposedMktValueAudit;
    private String praposedStampAudit;
    private String praposedMrktValSr;
   
    private String totalAudit;
    private String stampImpound;
    private String deficitStampAudit;
    private String deficitStampCollector;
    private String orderNumber;
    private String stampDuty;
	private String regFee;
	private String mrkValue;
	private String payableAmt;
	private String paidAmt;
	private String balanceAmt;
	private String paymentType;
	private String paymentAmount;
	private String sectionCommon;
	private String termsCond;
	private String loggedInUserOffice;
	private String estimatedDeffStampAudit;
	private String praposedRegAudit;
	private String praposedStampSubReg;
	private String districtName;
	private String districtId;
	private String drOfficeId;
	private String drOfficeName;
	
	 public String getDrOfficeId() {
		return drOfficeId;
	}
	public void setDrOfficeId(String drOfficeId) {
		this.drOfficeId = drOfficeId;
	}
	public String getDrOfficeName() {
		return drOfficeName;
	}
	public void setDrOfficeName(String drOfficeName) {
		this.drOfficeName = drOfficeName;
	}

	private String slotDateSys;
	 
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	//for rrc cases start
	private String stampCaseNumber;
	private String rrcCaseNumber;
	
	// end
	
	
	//for dashboard
	
	private String caseTypeDisplay;
	private String stampCaseDisplay;
	private String rrcCaseDisplay;
	private String revHeadDisplay;
	private String sectionDisplay;
	private String statusDisplay;
	private String dateDisplay;
	private String caseTxId;
	private String caseDate;
	private String orderDate;
	private String paymentDate;
	// end
	
    public String getMktValueDOc() {
		return mktValueDOc;
	}
	public void setMktValueDOc(String mktValueDOc) {
		this.mktValueDOc = mktValueDOc;
	}
	public String getSectionType() {
		return sectionType;
	}
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}
	public String getRevHeadType() {
		return revHeadType;
	}
	public void setRevHeadType(String revHeadType) {
		this.revHeadType = revHeadType;
	}
	public String getRevHeadId() {
		return revHeadId;
	}
	public void setRevHeadId(String revHeadId) {
		this.revHeadId = revHeadId;
	}
	
    private ArrayList revList = new ArrayList();
	
	
	public ArrayList getRevList() {
		return revList;
	}
	public void setRevList(ArrayList revList) {
		this.revList = revList;
	}
	public String getRevHeadName() {
		return revHeadName;
	}
	public void setRevHeadName(String revHeadName) {
		this.revHeadName = revHeadName;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getLoggedInUser() {
		return loggedInUser;
	}
	public void setLoggedInUser(String loggedInUser) {
		this.loggedInUser = loggedInUser;
	}
	public ArrayList getSearchList() {
		return searchList;
	}
	public void setSearchList(ArrayList searchList) {
		this.searchList = searchList;
	}
	
	public ArrayList getSectionList() {
		return sectionList;
	}
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	public String getSectionId() {
		return sectionId;
	}
	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public ArrayList getCaseStatusList() {
		return caseStatusList;
	}
	public void setCaseStatusList(ArrayList caseStatusList) {
		this.caseStatusList = caseStatusList;
	}
	public String getCaseStatusId() {
		return caseStatusId;
	}
	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}
	public String getCaseStatusName() {
		return caseStatusName;
	}
	public void setCaseStatusName(String caseStatusName) {
		this.caseStatusName = caseStatusName;
	}
	
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getPartyDtls() {
		return partyDtls;
	}
	public void setPartyDtls(String partyDtls) {
		this.partyDtls = partyDtls;
	}
	public String getPropDtls() {
		return propDtls;
	}
	public void setPropDtls(String propDtls) {
		this.propDtls = propDtls;
	}
	public String getStampAmt() {
		return stampAmt;
	}
	public void setStampAmt(String stampAmt) {
		this.stampAmt = stampAmt;
	}
	
	public String getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}
	public String getRegFee() {
		return regFee;
	}
	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}
	
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public FormFile getFile() {
		return file;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocFileSize(String docFileSize) {
		this.docFileSize = docFileSize;
	}
	public String getDocFileSize() {
		return docFileSize;
	}
	
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}
	public int getUploadId() {
		return uploadId;
	}
	public void setUploadDocPath(String uploadDocPath) {
		this.uploadDocPath = uploadDocPath;
	}
	public String getUploadDocPath() {
		return uploadDocPath;
	}
	public void setDbaseUpload(String dbaseUpload) {
		this.dbaseUpload = dbaseUpload;
	}
	public String getDbaseUpload() {
		return dbaseUpload;
	}
	public void setUploadSrNo(String uploadSrNo) {
		this.uploadSrNo = uploadSrNo;
	}
	public String getUploadSrNo() {
		return uploadSrNo;
	}
	public void setIdCheck(String idCheck) {
		this.idCheck = idCheck;
	}
	public String getIdCheck() {
		return idCheck;
	}
	public String getPraposedMktValueAudit() {
		return praposedMktValueAudit;
	}
	public void setPraposedMktValueAudit(String praposedMktValueAudit) {
		this.praposedMktValueAudit = praposedMktValueAudit;
	}
	public String getPraposedStampAudit() {
		return praposedStampAudit;
	}
	public void setPraposedStampAudit(String praposedStampAudit) {
		this.praposedStampAudit = praposedStampAudit;
	}
	public String getTotalAudit() {
		return totalAudit;
	}
	public void setTotalAudit(String totalAudit) {
		this.totalAudit = totalAudit;
	}
	public String getStampImpound() {
		return stampImpound;
	}
	public void setStampImpound(String stampImpound) {
		this.stampImpound = stampImpound;
	}
	public String getDeficitStampAudit() {
		return deficitStampAudit;
	}
	public void setDeficitStampAudit(String deficitStampAudit) {
		this.deficitStampAudit = deficitStampAudit;
	}
	public String getDeficitStampCollector() {
		return deficitStampCollector;
	}
	public void setDeficitStampCollector(String deficitStampCollector) {
		this.deficitStampCollector = deficitStampCollector;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getMrkValue() {
		return mrkValue;
	}
	public void setMrkValue(String mrkValue) {
		this.mrkValue = mrkValue;
	}
	public String getPayableAmt() {
		return payableAmt;
	}
	public void setPayableAmt(String payableAmt) {
		this.payableAmt = payableAmt;
	}
	public String getPaidAmt() {
		return paidAmt;
	}
	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}
	public String getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(String balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSectionCommon() {
		return sectionCommon;
	}
	public void setSectionCommon(String sectionCommon) {
		this.sectionCommon = sectionCommon;
	}
	public String getTermsCond() {
		return termsCond;
	}
	public void setTermsCond(String termsCond) {
		this.termsCond = termsCond;
	}
	public String getLoggedInUserOffice() {
		return loggedInUserOffice;
	}
	public void setLoggedInUserOffice(String loggedInUserOffice) {
		this.loggedInUserOffice = loggedInUserOffice;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public String getEstimatedDeffStampAudit() {
		return estimatedDeffStampAudit;
	}
	public void setEstimatedDeffStampAudit(String estimatedDeffStampAudit) {
		this.estimatedDeffStampAudit = estimatedDeffStampAudit;
	}
	public String getStampCaseNumber() {
		return stampCaseNumber;
	}
	public void setStampCaseNumber(String stampCaseNumber) {
		this.stampCaseNumber = stampCaseNumber;
	}
	public String getRrcCaseNumber() {
		return rrcCaseNumber;
	}
	public void setRrcCaseNumber(String rrcCaseNumber) {
		this.rrcCaseNumber = rrcCaseNumber;
	}
	public String getPraposedRegAudit() {
		return praposedRegAudit;
	}
	public void setPraposedRegAudit(String praposedRegAudit) {
		this.praposedRegAudit = praposedRegAudit;
	}
	public String getPraposedStampSubReg() {
		return praposedStampSubReg;
	}
	public void setPraposedStampSubReg(String praposedStampSubReg) {
		this.praposedStampSubReg = praposedStampSubReg;
	}
	public String getCaseTypeDisplay() {
		return caseTypeDisplay;
	}
	public void setCaseTypeDisplay(String caseTypeDisplay) {
		this.caseTypeDisplay = caseTypeDisplay;
	}
	public String getStampCaseDisplay() {
		return stampCaseDisplay;
	}
	public void setStampCaseDisplay(String stampCaseDisplay) {
		this.stampCaseDisplay = stampCaseDisplay;
	}
	public String getRrcCaseDisplay() {
		return rrcCaseDisplay;
	}
	public void setRrcCaseDisplay(String rrcCaseDisplay) {
		this.rrcCaseDisplay = rrcCaseDisplay;
	}
	public String getRevHeadDisplay() {
		return revHeadDisplay;
	}
	public void setRevHeadDisplay(String revHeadDisplay) {
		this.revHeadDisplay = revHeadDisplay;
	}
	public String getSectionDisplay() {
		return sectionDisplay;
	}
	public void setSectionDisplay(String sectionDisplay) {
		this.sectionDisplay = sectionDisplay;
	}
	public String getStatusDisplay() {
		return statusDisplay;
	}
	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}
	public String getDateDisplay() {
		return dateDisplay;
	}
	public void setDateDisplay(String dateDisplay) {
		this.dateDisplay = dateDisplay;
	}
	public String getCaseTxId() {
		return caseTxId;
	}
	public void setCaseTxId(String caseTxId) {
		this.caseTxId = caseTxId;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setSlotDateSys(String slotDateSys) {
		this.slotDateSys = slotDateSys;
	}
	public String getSlotDateSys() {
		return slotDateSys;
	}
	public void setPraposedMrktValSr(String praposedMrktValSr) {
		this.praposedMrktValSr = praposedMrktValSr;
	}
	public String getPraposedMrktValSr() {
		return praposedMrktValSr;
	}
	

}
