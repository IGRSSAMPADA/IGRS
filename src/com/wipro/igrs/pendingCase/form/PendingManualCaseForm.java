package com.wipro.igrs.pendingCase.form;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.caseMonitoring.dto.CaseMonHistoryDTO;
import com.wipro.igrs.pendingCase.dto.PendingManualCaseDTO;

public class PendingManualCaseForm extends ActionForm {
	private PendingManualCaseDTO caseDTO = new PendingManualCaseDTO();
    private String formName = "";
    private String actionName = "";
    private String caseDate = "";
    private String orderDate ="";
    private String paymentDate ="";
    private String hiddenCaseNumber;
    private String caseType;
	private String referenceNo;
	private String caseNumber;
	private String partyDtls;
	private String propDtls;
	private String stampAmt;
    private String caseTxId;
	private String caseStatus;
	private String caseTypeEdit;
	private String remarks;
	private String validateEdit;
	private String sectionId;
	private String sectionName;
	private String errorFound;
	private String buttonDispay;
	private String caseStatusId;
	private String caseStatusName;
	private String user;
     private String zoneId;
     private String districtId;
     private String DistrictZoneId;
     private String DistrictZoneName;
    private String jrxmlName;
    private String reportName;
    private String slotDateSys;
    private String caseSequence;
    private String drOfficeIdSelected;
    private String durationFrom;
    private String durationTo;
    private String praposedMrktValSr;
    
    public String getPraposedMrktValSr() {
		return praposedMrktValSr;
	}
	public void setPraposedMrktValSr(String praposedMrktValSr) {
		this.praposedMrktValSr = praposedMrktValSr;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	private String language;
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public String getZoneId() {
	return zoneId;
}
public void setZoneId(String zoneId) {
	this.zoneId = zoneId;
}
	private String createdDate;
	private String loggedInUser;
	
	// added by satbir kumar---
	
	private FormFile file;
	private String documentName;

	private String docFileSize;
	private String uploadDocPath;
	private int  uploadId;
	private String dbaseUpload;
	private String uploadSrNo;
	private String idCheck;
	
	private HashMap mapCaseDisp = new HashMap();
    public ArrayList getRevList() {
		return revList;
	}
	public void setRevList(ArrayList revList) {
		this.revList = revList;
	}
	public ArrayList getSectionList() {
		return sectionList;
	}
	public void setSectionList(ArrayList sectionList) {
		this.sectionList = sectionList;
	}
	public ArrayList getCaseStatusList() {
		return caseStatusList;
	}
	public void setCaseStatusList(ArrayList caseStatusList) {
		this.caseStatusList = caseStatusList;
	}
	private String revHeadName;
    private String revHeadId;
    private String revHeadType;
    private String updateRemarks;
    
    private ArrayList revList = new ArrayList();
    private ArrayList sectionList=new ArrayList();
	private ArrayList caseStatusList=new ArrayList();
	private ArrayList PendingApplicationList =new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList dRofficeList = new ArrayList();
	private ArrayList drOfficeNameList = new ArrayList();
	private ArrayList paymentList = new ArrayList();
	private ArrayList statusList = new ArrayList();
	private String caseDetails;
	
 

	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
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
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getDocFileSize() {
		return docFileSize;
	}
	public void setDocFileSize(String docFileSize) {
		this.docFileSize = docFileSize;
	}
	public String getDbaseUpload() {
		return dbaseUpload;
	}
	public void setDbaseUpload(String dbaseUpload) {
		this.dbaseUpload = dbaseUpload;
	}
	public String getRevHeadName() {
		return revHeadName;
	}
	public void setRevHeadName(String revHeadName) {
		this.revHeadName = revHeadName;
	}
	public String getRevHeadId() {
		return revHeadId;
	}
	public void setRevHeadId(String revHeadId) {
		this.revHeadId = revHeadId;
	}
	public String getRevHeadType() {
		return revHeadType;
	}
	public void setRevHeadType(String revHeadType) {
		this.revHeadType = revHeadType;
	}
	public String getSectionType() {
		return sectionType;
	}
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}
	public String getMktValueDOc() {
		return mktValueDOc;
	}
	public void setMktValueDOc(String mktValueDOc) {
		this.mktValueDOc = mktValueDOc;
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
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
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
	public String getEstimatedDeffStampAudit() {
		return estimatedDeffStampAudit;
	}
	public void setEstimatedDeffStampAudit(String estimatedDeffStampAudit) {
		this.estimatedDeffStampAudit = estimatedDeffStampAudit;
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
	private String sectionType;
    private String mktValueDOc;
    private String praposedMktValueAudit;
    private String praposedStampAudit;
   
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
	private String paymentRemarks;
	private String sectionCommon;
	private String termsCond;
	private String loggedInUserOffice;
	private String estimatedDeffStampAudit;
	private String distSelected;
	 private String praposedRegAudit;
	    private String praposedStampSubReg;
	
	//for rrc cases start
	private String stampCaseNumber;
	private String rrcCaseNumber;

	private ArrayList uploadList = new ArrayList();
    private ArrayList urlList = new ArrayList();
    public String getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public PendingManualCaseDTO getCaseDTO() {
		return caseDTO;
	}
	public void setCaseDTO(PendingManualCaseDTO caseDTO) {
		this.caseDTO = caseDTO;
	}
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
	public void setUploadList(ArrayList uploadList) {
		this.uploadList = uploadList;
	}
	public ArrayList getUploadList() {
		return uploadList;
	}
	public void setUrlList(ArrayList urlList) {
		this.urlList = urlList;
	}
	public ArrayList getUrlList() {
		return urlList;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public void setPendingApplicationList(ArrayList pendingApplicationList) {
		PendingApplicationList = pendingApplicationList;
	}
	public ArrayList getPendingApplicationList() {
		return PendingApplicationList;
	}
	public String getReferenceNo() {
		return referenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}
	public String getHiddenCaseNumber() {
		return hiddenCaseNumber;
	}
	public void setHiddenCaseNumber(String hiddenCaseNumber) {
		this.hiddenCaseNumber = hiddenCaseNumber;
	}
	public void setMapCaseDisp(HashMap mapCaseDisp) {
		this.mapCaseDisp = mapCaseDisp;
	}
	public HashMap getMapCaseDisp() {
		return mapCaseDisp;
	}
	public void setCaseDetails(String caseDetails) {
		this.caseDetails = caseDetails;
	}
	public String getCaseDetails() {
		return caseDetails;
	}
	public void setCaseTxId(String caseTxId) {
		this.caseTxId = caseTxId;
	}
	public String getCaseTxId() {
		return caseTxId;
	}
	public void setCaseTypeEdit(String caseTypeEdit) {
		this.caseTypeEdit = caseTypeEdit;
	}
	public String getCaseTypeEdit() {
		return caseTypeEdit;
	}
	public void setUpdateRemarks(String updateRemarks) {
		this.updateRemarks = updateRemarks;
	}
	public String getUpdateRemarks() {
		return updateRemarks;
	}
	public void setValidateEdit(String validateEdit) {
		this.validateEdit = validateEdit;
	}
	public String getValidateEdit() {
		return validateEdit;
	}
	public void setErrorFound(String errorFound) {
		this.errorFound = errorFound;
	}
	public String getErrorFound() {
		return errorFound;
	}
	public void setButtonDispay(String buttonDispay) {
		this.buttonDispay = buttonDispay;
	}
	public String getButtonDispay() {
		return buttonDispay;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUser() {
		return user;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictZoneName(String districtZoneName) {
		DistrictZoneName = districtZoneName;
	}
	public String getDistrictZoneName() {
		return DistrictZoneName;
	}
	public void setDistrictZoneId(String districtZoneId) {
		DistrictZoneId = districtZoneId;
	}
	public String getDistrictZoneId() {
		return DistrictZoneId;
	}
	public void setJrxmlName(String jrxmlName) {
		this.jrxmlName = jrxmlName;
	}
	public String getJrxmlName() {
		return jrxmlName;
	}
	public void setPaymentRemarks(String paymentRemarks) {
		this.paymentRemarks = paymentRemarks;
	}
	public String getPaymentRemarks() {
		return paymentRemarks;
	}
	public void setCaseSequence(String caseSequence) {
		this.caseSequence = caseSequence;
	}
	public String getCaseSequence() {
		return caseSequence;
	}
	public void setSlotDateSys(String slotDateSys) {
		this.slotDateSys = slotDateSys;
	}
	public String getSlotDateSys() {
		return slotDateSys;
	}
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}
	public String getDurationFrom() {
		return durationFrom;
	}
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}
	public String getDurationTo() {
		return durationTo;
	}
	public void setDRofficeList(ArrayList dRofficeList) {
		this.dRofficeList = dRofficeList;
	}
	public ArrayList getDRofficeList() {
		return dRofficeList;
	}
	public void setDrOfficeIdSelected(String drOfficeIdSelected) {
		this.drOfficeIdSelected = drOfficeIdSelected;
	}
	public String getDrOfficeIdSelected() {
		return drOfficeIdSelected;
	}
	public void setDistSelected(String distSelected) {
		this.distSelected = distSelected;
	}
	public String getDistSelected() {
		return distSelected;
	}
	public void setDrOfficeNameList(ArrayList drOfficeNameList) {
		this.drOfficeNameList = drOfficeNameList;
	}
	public ArrayList getDrOfficeNameList() {
		return drOfficeNameList;
	}
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}
	public ArrayList getStatusList() {
		return statusList;
	}
	public void setPaymentList(ArrayList paymentList) {
		this.paymentList = paymentList;
	}
	public ArrayList getPaymentList() {
		return paymentList;
	}


}
