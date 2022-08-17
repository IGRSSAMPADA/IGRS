package com.wipro.igrs.slotbooking.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.slotbooking.dto.SlotBookingUrgentDTO;

public class SlotBookingUrgentForm extends ActionForm {
	private String regTxnId;
	private String reasonId;
	private String reasonDesc;
	private String logedInUser;
	private String loggedInDate;
	private String slotTxnId;
	private ArrayList<String> slotId;
	private String actionName;
	private String ifError;
	private String bookedSROName;
	private String bookedDistrictName;
	private String bookedSlotDate;
	private String bookedSlotTiming;
	private String ifOpenedAtSrOffice;
	private String opendSrOfficeName;
	private String openedByName;
	private String bookedSROId;
	private String bookedDistrictId;
	private String slotBookedBy;
	private String applicationStatus;
	private ArrayList<SlotBookingUrgentDTO> reasonList;
	FormFile uploadFilePath=null;
	private String paymentRequired;
	private String referenceIdForPayment;
	private String userIdForPayment;
	private String payableAmount;
	private String paymentFlag;
	private byte[] offUploadContents;
	private String finalUploadFilePath;
	private String uploadFileName;
	private String uploadStatus;
	private String cashLessEnable;
	private String toBeBookedDate;
	private String dateSelected;
	private String paymentDone;
	private String changeDate;
	private String changedDate;
	private String slotBookTxnId;
	private String updateDate;
	//for enabling/disabling offices

	private SlotBookingUrgentDTO slotBookDto;
	private String selectedDistrictId;
	private String selectedDistrictName;
	private String selectedOfficeId;
	private String selectedOfficeName;
	private String timeInMinute;
	private ArrayList<String> yesNoList;
	private String selectedYesNo;
	private String fileName;
	//FOR REPORT
	private ArrayList<SlotBookingUrgentDTO> reportData;
	private HashMap<String, String> districtList;
	private String frmDate;
	private String toDate;
	
	//for payment
	private ArrayList<SlotBookingUrgentDTO> paymentData;
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getReasonId() {
		return reasonId;
	}
	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public String getLogedInUser() {
		return logedInUser;
	}
	public void setLogedInUser(String logedInUser) {
		this.logedInUser = logedInUser;
	}
	public String getLoggedInDate() {
		return loggedInDate;
	}
	public void setLoggedInDate(String loggedInDate) {
		this.loggedInDate = loggedInDate;
	}
	public String getSlotTxnId() {
		return slotTxnId;
	}
	public void setSlotTxnId(String slotTxnId) {
		this.slotTxnId = slotTxnId;
	}
	public ArrayList<String> getSlotId() {
		return slotId;
	}
	public void setSlotId(ArrayList<String> slotId) {
		this.slotId = slotId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getIfError() {
		return ifError;
	}
	public void setIfError(String ifError) {
		this.ifError = ifError;
	}
	public String getBookedSROName() {
		return bookedSROName;
	}
	public void setBookedSROName(String bookedSROName) {
		this.bookedSROName = bookedSROName;
	}
	public String getBookedDistrictName() {
		return bookedDistrictName;
	}
	public void setBookedDistrictName(String bookedDistrictName) {
		this.bookedDistrictName = bookedDistrictName;
	}
	
	public String getChangeDate() {
		return changeDate;
	}
	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}
	public String getBookedSlotDate() {
		return bookedSlotDate;
	}
	public void setBookedSlotDate(String bookedSlotDate) {
		this.bookedSlotDate = bookedSlotDate;
	}
	public String getBookedSlotTiming() {
		return bookedSlotTiming;
	}
	public void setBookedSlotTiming(String bookedSlotTiming) {
		this.bookedSlotTiming = bookedSlotTiming;
	}
	public String getIfOpenedAtSrOffice() {
		return ifOpenedAtSrOffice;
	}
	public void setIfOpenedAtSrOffice(String ifOpenedAtSrOffice) {
		this.ifOpenedAtSrOffice = ifOpenedAtSrOffice;
	}
	public String getOpendSrOfficeName() {
		return opendSrOfficeName;
	}
	public void setOpendSrOfficeName(String opendSrOfficeName) {
		this.opendSrOfficeName = opendSrOfficeName;
	}
	public String getOpenedByName() {
		return openedByName;
	}
	public void setOpenedByName(String openedByName) {
		this.openedByName = openedByName;
	}
	public String getBookedSROId() {
		return bookedSROId;
	}
	public void setBookedSROId(String bookedSROId) {
		this.bookedSROId = bookedSROId;
	}
	public String getBookedDistrictId() {
		return bookedDistrictId;
	}
	public void setBookedDistrictId(String bookedDistrictId) {
		this.bookedDistrictId = bookedDistrictId;
	}
	public String getSlotBookedBy() {
		return slotBookedBy;
	}
	public void setSlotBookedBy(String slotBookedBy) {
		this.slotBookedBy = slotBookedBy;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public ArrayList<SlotBookingUrgentDTO> getReasonList() {
		return reasonList;
	}
	public void setReasonList(ArrayList<SlotBookingUrgentDTO> reasonList) {
		this.reasonList = reasonList;
	}
	public SlotBookingUrgentDTO getSlotBookDto() {
		return slotBookDto;
	}
	public void setSlotBookDto(SlotBookingUrgentDTO slotBookDto) {
		this.slotBookDto = slotBookDto;
	}
	public String getSelectedDistrictId() {
		return selectedDistrictId;
	}
	public void setSelectedDistrictId(String selectedDistrictId) {
		this.selectedDistrictId = selectedDistrictId;
	}
	public String getSelectedDistrictName() {
		return selectedDistrictName;
	}
	public void setSelectedDistrictName(String selectedDistrictName) {
		this.selectedDistrictName = selectedDistrictName;
	}
	public String getSelectedOfficeId() {
		return selectedOfficeId;
	}
	public void setSelectedOfficeId(String selectedOfficeId) {
		this.selectedOfficeId = selectedOfficeId;
	}
	public String getSelectedOfficeName() {
		return selectedOfficeName;
	}
	public void setSelectedOfficeName(String selectedOfficeName) {
		this.selectedOfficeName = selectedOfficeName;
	}
	public String getTimeInMinute() {
		return timeInMinute;
	}
	public void setTimeInMinute(String timeInMinute) {
		this.timeInMinute = timeInMinute;
	}
	public ArrayList<String> getYesNoList() {
		return yesNoList;
	}
	public void setYesNoList(ArrayList<String> yesNoList) {
		this.yesNoList = yesNoList;
	}
	public String getSelectedYesNo() {
		return selectedYesNo;
	}
	public void setSelectedYesNo(String selectedYesNo) {
		this.selectedYesNo = selectedYesNo;
	}
	public String getPaymentRequired() {
		return paymentRequired;
	}
	public void setPaymentRequired(String paymentRequired) {
		this.paymentRequired = paymentRequired;
	}
	public String getReferenceIdForPayment() {
		return referenceIdForPayment;
	}
	public void setReferenceIdForPayment(String referenceIdForPayment) {
		this.referenceIdForPayment = referenceIdForPayment;
	}
	public String getUserIdForPayment() {
		return userIdForPayment;
	}
	public void setUserIdForPayment(String userIdForPayment) {
		this.userIdForPayment = userIdForPayment;
	}
	public String getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}
	public String getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public FormFile getUploadFilePath() {
		return uploadFilePath;
	}
	public void setUploadFilePath(FormFile uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	public byte[] getOffUploadContents() {
		return offUploadContents;
	}
	public void setOffUploadContents(byte[] offUploadContents) {
		this.offUploadContents = offUploadContents;
	}
	public String getFinalUploadFilePath() {
		return finalUploadFilePath;
	}
	public void setFinalUploadFilePath(String finalUploadFilePath) {
		this.finalUploadFilePath = finalUploadFilePath;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getCashLessEnable() {
		return cashLessEnable;
	}
	public void setCashLessEnable(String cashLessEnable) {
		this.cashLessEnable = cashLessEnable;
	}
	//report
	public ArrayList<SlotBookingUrgentDTO> getReportData() {
		return reportData;
	}
	public void setReportData(ArrayList<SlotBookingUrgentDTO> reportData) {
		this.reportData = reportData;
	}
	public String getToBeBookedDate() {
		return toBeBookedDate;
	}
	public void setToBeBookedDate(String toBeBookedDate) {
		this.toBeBookedDate = toBeBookedDate;
	}
	public String getDateSelected() {
		return dateSelected;
	}
	public void setDateSelected(String dateSelected) {
		this.dateSelected = dateSelected;
	}
	public String getPaymentDone() {
		return paymentDone;
	}
	public void setPaymentDone(String paymentDone) {
		this.paymentDone = paymentDone;
	}
	public HashMap<String, String> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(HashMap<String, String> districtList) {
		this.districtList = districtList;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public ArrayList<SlotBookingUrgentDTO> getPaymentData() {
		return paymentData;
	}
	public void setPaymentData(ArrayList<SlotBookingUrgentDTO> paymentData) {
		this.paymentData = paymentData;
	}
	public String getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}
	public String getSlotBookTxnId() {
		return slotBookTxnId;
	}
	public void setSlotBookTxnId(String slotBookTxnId) {
		this.slotBookTxnId = slotBookTxnId;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
}
