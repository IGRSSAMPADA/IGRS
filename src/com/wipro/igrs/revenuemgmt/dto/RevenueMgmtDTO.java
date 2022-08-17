/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: RevenueMgmtDAO.java
 *
 * Description	   		: A class that represents a row in the Revenue Mgmt tables.
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 28 11 2007  
 *
 * Modification History	: Created
 */


package com.wipro.igrs.revenuemgmt.dto;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class applies the basic coding guidelines mentioned in this document.
 * A quick view to this class will let one know how the code should look like.
 *
 * @author  Wipro Infotech, Sreelatha M
 * @version 1.0, 24/12/2007
 * @since   1.0
 */


public class RevenueMgmtDTO implements Serializable{
    private String purpose;
    private String receiptID;
    private String paymentType;
    private String transactionDate;
    private float transactionAmount;
    private String transactionPurpose;
    private String userId;
    private int sno ;   
    private String transactionId;
    private String transactionType;
    private String transactionLocation;    
    private String sroDroName;
    //variables for Reconciliation Report
    private float cashAtSRO;
    private float cashAtDRO;
    private float onlinePayments;
    private float creditLimitPayments;
    private float challanPayments;
    private float registrationFee;
    private float stampDuty;  
    private float refunds;
    private float stampCases;
    private float rrc; 
    private String offTypeId = null;    
    private float creditAmount;
    private String creditDate = null;
    private String paymentMode= null;
    private String receiptNumber = null;
    private float debitAmount;
    private String debitDate = null;
    private float balanceAmount;
    private String userReceiptNumber = null;
    private String offiId;
    private String offType;
    private String offLocation;
    //newly added
   
    private String districtName;    
    private String districtId;
    private String officeName;    
    private String officeId;
    private String officeType;
    private String officeTypeId;
    //for Service Fee Matrix
    private String paramId;
    private String paramName;
    private String paramDescription;
    private String paramStatus;
    private String createdBy;    
    private ArrayList serviceList;
    
	public float getCashAtSRO() {
		return cashAtSRO;
	}
	public void setCashAtSRO(float cashAtSRO) {
		this.cashAtSRO = cashAtSRO;
	}
	public float getCashAtDRO() {
		return cashAtDRO;
	}
	public void setCashAtDRO(float cashAtDRO) {
		this.cashAtDRO = cashAtDRO;
	}
	public float getOnlinePayments() {
		return onlinePayments;
	}
	public void setOnlinePayments(float onlinePayments) {
		this.onlinePayments = onlinePayments;
	}
	public float getCreditLimitPayments() {
		return creditLimitPayments;
	}
	public void setCreditLimitPayments(float creditLimitPayments) {
		this.creditLimitPayments = creditLimitPayments;
	}
	public float getChallanPayments() {
		return challanPayments;
	}
	public void setChallanPayments(float challanPayments) {
		this.challanPayments = challanPayments;
	}
	public float getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(float registrationFee) {
		this.registrationFee = registrationFee;
	}
	public float getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(float stampDuty) {
		this.stampDuty = stampDuty;
	}
	public float getRefunds() {
		return refunds;
	}
	public void setRefunds(float refunds) {
		this.refunds = refunds;
	}
	public float getStampCases() {
		return stampCases;
	}
	public void setStampCases(float stampCases) {
		this.stampCases = stampCases;
	}
	public float getRrc() {
		return rrc;
	}
	public void setRrc(float rrc) {
		this.rrc = rrc;
	}
	public String getOffTypeId() {
		return offTypeId;
	}
	public void setOffTypeId(String offTypeId) {
		this.offTypeId = offTypeId;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}	
	public float getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionPurpose() {
		return transactionPurpose;
	}
	public void setTransactionPurpose(String transactionPurpose) {
		this.transactionPurpose = transactionPurpose;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionLocation() {
		return transactionLocation;
	}
	public void setTransactionLocation(String transactionLocation) {
		this.transactionLocation = transactionLocation;
	}	
	public String getSroDroName() {
		return sroDroName;
	}
	public void setSroDroName(String sroDroName) {
		this.sroDroName = sroDroName;
	}
	public float getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(float creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getCreditDate() {
		return creditDate;
	}
	public void setCreditDate(String creditDate) {
		this.creditDate = creditDate;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public float getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(float debitAmount) {
		this.debitAmount = debitAmount;
	}
	public String getDebitDate() {
		return debitDate;
	}
	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}
	public float getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(float balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getUserReceiptNumber() {
		return userReceiptNumber;
	}
	public void setUserReceiptNumber(String userReceiptNumber) {
		this.userReceiptNumber = userReceiptNumber;
	}
	public String getOffiId() {
		return offiId;
	}
	public void setOffiId(String offiId) {
		this.offiId = offiId;
	}
	public String getOffType() {
		return offType;
	}
	public void setOffType(String offType) {
		this.offType = offType;
	}
	public String getOffLocation() {
		return offLocation;
	}
	public void setOffLocation(String offLocation) {
		this.offLocation = offLocation;
	}
	
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
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	public String getOfficeTypeId() {
		return officeTypeId;
	}
	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamDescription() {
		return paramDescription;
	}
	public void setParamDescription(String paramDescription) {
		this.paramDescription = paramDescription;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getParamStatus() {
		return paramStatus;
	}
	public void setParamStatus(String paramStatus) {
		this.paramStatus = paramStatus;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the serviceList
	 */
	public ArrayList getServiceList() {
		return serviceList;
	}
	/**
	 * @param serviceList the serviceList to set
	 */
	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}
	

}
