package com.wipro.igrs.slotbooking.dto;

import java.io.Serializable;
import java.util.HashMap;

public class SlotBookingUrgentDTO implements Serializable{
	private String reasonId;
	private String reasonNameEng;
	private String reasonNameHindi;
	private String paymentRequired;
	private String selectedOfficeId;
	private String payableAmount;
	private HashMap<String, String> officeList;
	private HashMap<String, String> districtList;
	//for report
	private String districtName;
	private String officeName;
	private String regTxnId;
	private String paymentReq;
	private String updateById;
	private String updateByName;
	private String filePath;
	private String reason;
	private String prevSlotDate;
	private String rescheduleSlotDate;
	//for payment details
	private String transactionID;
	private String referenceID;
	private String amountPaid;
	private String paymentDate;
	private String paymentType;
	private String paymentBy;
	
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public String getReasonNameEng() {
		return reasonNameEng;
	}
	public void setReasonNameEng(String reasonNameEng) {
		this.reasonNameEng = reasonNameEng;
	}
	public String getReasonNameHindi() {
		return reasonNameHindi;
	}
	public void setReasonNameHindi(String reasonNameHindi) {
		this.reasonNameHindi = reasonNameHindi;
	}
	public String getSelectedOfficeId() {
		return selectedOfficeId;
	}
	public void setSelectedOfficeId(String selectedOfficeId) {
		this.selectedOfficeId = selectedOfficeId;
	}
	public HashMap<String, String> getOfficeList() {
		return officeList;
	}
	public void setOfficeList(HashMap<String, String> officeList) {
		this.officeList = officeList;
	}
	public HashMap<String, String> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(HashMap<String, String> districtList) {
		this.districtList = districtList;
	}
	public String getPaymentRequired() {
		return paymentRequired;
	}
	public void setPaymentRequired(String paymentRequired) {
		this.paymentRequired = paymentRequired;
	}
	public String getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}
	//for report
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getPaymentReq() {
		return paymentReq;
	}
	public void setPaymentReq(String paymentReq) {
		this.paymentReq = paymentReq;
	}
	public String getUpdateById() {
		return updateById;
	}
	public void setUpdateById(String updateById) {
		this.updateById = updateById;
	}
	public String getUpdateByName() {
		return updateByName;
	}
	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public String getReferenceID() {
		return referenceID;
	}
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}
	public String getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}
	public String getPrevSlotDate() {
		return prevSlotDate;
	}
	public void setPrevSlotDate(String prevSlotDate) {
		this.prevSlotDate = prevSlotDate;
	}
	public String getRescheduleSlotDate() {
		return rescheduleSlotDate;
	}
	public void setRescheduleSlotDate(String rescheduleSlotDate) {
		this.rescheduleSlotDate = rescheduleSlotDate;
	}
	
}
