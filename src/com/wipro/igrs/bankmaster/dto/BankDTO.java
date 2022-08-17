package com.wipro.igrs.bankmaster.dto;

import java.io.Serializable;

public class BankDTO implements Serializable{
	public BankDTO(){}
	
	private String bankId;
	private String bankName;
	private String bankAddress;
	private String bankPhoneNumber;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updateBy;
	private String updateDate;
	
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
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankPhoneNumber() {
		return bankPhoneNumber;
	}
	public void setBankPhoneNumber(String bankPhoneNumber) {
		this.bankPhoneNumber = bankPhoneNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
