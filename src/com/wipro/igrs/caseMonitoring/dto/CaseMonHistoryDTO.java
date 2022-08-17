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

import org.apache.struts.upload.FormFile;


public class CaseMonHistoryDTO implements Serializable {
	private String caseType;
	private String referenceNo;
	private String caseNumber;
	private String partyDtls;
	private String propDtls;
	private String stampAmt;
	private String rrcAmt;
	private String stampDuty;
	private String regFee;
	private String recStampDuty;
	private String recRegFee;
	private String caseStatus;
	private String remarks;
	private ArrayList sectionList=new ArrayList();
	private String sectionId;
	private String sectionName;
	private ArrayList caseStatusList=new ArrayList();
	private String caseStatusId;
	private String caseStatusName;
	private ArrayList divisionList=new ArrayList();
	private String divisionName;
	private String divisionId;
	private String hdnCaseType;
	private String fromDate;
	private String toDate;
	private ArrayList searchList=new ArrayList();
	private String createdDate;
	private String loggedInUser;
	
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
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getHdnCaseType() {
		return hdnCaseType;
	}
	public void setHdnCaseType(String hdnCaseType) {
		this.hdnCaseType = hdnCaseType;
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
	public ArrayList getDivisionList() {
		return divisionList;
	}
	public void setDivisionList(ArrayList divisionList) {
		this.divisionList = divisionList;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
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
	public String getRrcAmt() {
		return rrcAmt;
	}
	public void setRrcAmt(String rrcAmt) {
		this.rrcAmt = rrcAmt;
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
	public String getRecStampDuty() {
		return recStampDuty;
	}
	public void setRecStampDuty(String recStampDuty) {
		this.recStampDuty = recStampDuty;
	}
	public String getRecRegFee() {
		return recRegFee;
	}
	public void setRecRegFee(String recRegFee) {
		this.recRegFee = recRegFee;
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
	

	
	
	
	
}