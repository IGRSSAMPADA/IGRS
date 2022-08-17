package com.wipro.igrs.transactionHistory.DTO;

import org.apache.struts.upload.FormFile;

public class TransactionHistoryDTO {

	
	private FormFile upload;
	private String fileName;
	private byte[] uploadContent;
	private String firstName;
	private String date;
	private String district;
	private String dro;
	private String sro;
	private String denoStamp;
	private String codeStamp;
	private String seriesNo;
	private String amount;
	private String userId;
	private String districtId;
	private String type;
	private String bookNo;
	private String volumeNo;
	private String serialNo;
	private String radio;
	private String seriesNoF;
	private String seriesNoT;
	private String fromDate;
	private String toDate;
	private int srNo;
	private String createdDate;
	private String createdBy;
	
	
	
	
	
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
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
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
	public String getSeriesNoF() {
		return seriesNoF;
	}
	public void setSeriesNoF(String seriesNoF) {
		this.seriesNoF = seriesNoF;
	}
	public String getSeriesNoT() {
		return seriesNoT;
	}
	public void setSeriesNoT(String seriesNoT) {
		this.seriesNoT = seriesNoT;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getVolumeNo() {
		return volumeNo;
	}
	public void setVolumeNo(String volumeNo) {
		this.volumeNo = volumeNo;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSro() {
		return sro;
	}
	public void setSro(String sro) {
		this.sro = sro;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDro() {
		return dro;
	}
	public void setDro(String dro) {
		this.dro = dro;
	}
	public String getDenoStamp() {
		return denoStamp;
	}
	public void setDenoStamp(String denoStamp) {
		this.denoStamp = denoStamp;
	}
	public String getCodeStamp() {
		return codeStamp;
	}
	public void setCodeStamp(String codeStamp) {
		this.codeStamp = codeStamp;
	}
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public byte[] getUploadContent() {
		return uploadContent;
	}
	public void setUploadContent(byte[] uploadContent) {
		this.uploadContent = uploadContent;
	}
	public FormFile getUpload() {
		return upload;
	}
	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
