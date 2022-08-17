package com.wipro.igrs.applicationlog.dto;

import java.io.Serializable;

public class ApplicationLogDTO implements Serializable{

	private String tableName;
	private String operationType;
	private String operationStatus;
	private String operationDate;
	private String operatedBy;
	private String operatedFuncID;
	private String operatedRoleID;
	private String fwdName;
	private String operatedActivityId;
	private String opertdFuncID;
	private String grossAmount;
	private String remarks;
	private String createdBy;
	private String createdDate;
	private String service;
	private String status;
	
	private String paymentStatus;
	private String paymentTxnID;
	private String txnAmount;
	private String userType;
	private String searchFlag;
	
	
	
	public String getSearchFlag() {
		return searchFlag;
	}
	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}
	public String getOpertdFuncID() {
		return opertdFuncID;
	}
	public void setOpertdFuncID(String opertdFuncID) {
		this.opertdFuncID = opertdFuncID;
	}
	public String getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(String grossAmount) {
		this.grossAmount = grossAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getFwdName() {
		return fwdName;
	}
	public void setFwdName(String fwdName) {
		this.fwdName = fwdName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getOperatedBy() {
		return operatedBy;
	}
	public void setOperatedBy(String operatedBy) {
		this.operatedBy = operatedBy;
	}
	public String getOperatedFuncID() {
		return operatedFuncID;
	}
	public void setOperatedFuncID(String operatedFuncID) {
		this.operatedFuncID = operatedFuncID;
	}
	public String getOperatedRoleID() {
		return operatedRoleID;
	}
	public void setOperatedRoleID(String operatedRoleID) {
		this.operatedRoleID = operatedRoleID;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getService() {
		return service;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setOperatedActivityId(String operatedActivityId) {
		this.operatedActivityId = operatedActivityId;
	}
	public String getOperatedActivityId() {
		return operatedActivityId;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentTxnID(String paymentTxnID) {
		this.paymentTxnID = paymentTxnID;
	}
	public String getPaymentTxnID() {
		return paymentTxnID;
	}
	public void setTxnAmount(String txnAmount) {
		this.txnAmount = txnAmount;
	}
	public String getTxnAmount() {
		return txnAmount;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserType() {
		return userType;
	}

	
}
