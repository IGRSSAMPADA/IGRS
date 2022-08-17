package com.wipro.igrs.treasuryMaster.dto;




public class TreasureDTO 
{
	private String treasuryId;
	private String treasuryName;
	private String treasuryAddress;
	private String treasuryPhone;
	private String status;
	private String createdBy;
	private String updatedBy;
	private String updateDate;
	private String creationDate;
	
	
	
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public TreasureDTO() {
		
	}
	public TreasureDTO(String treasuryId) {
		super();
		this.treasuryId = treasuryId;
	}
	public String getTreasuryId() {
		return treasuryId;
	}
	public void setTreasuryId(String treasuryId) {
		this.treasuryId = treasuryId;
	}
	public String getTreasuryName() {
		return treasuryName;
	}
	public void setTreasuryName(String treasuryName) {
		this.treasuryName = treasuryName;
	}
	public String getTreasuryAddress() {
		return treasuryAddress;
	}
	public void setTreasuryAddress(String treasuryAddress) {
		this.treasuryAddress = treasuryAddress;
	}
	public String getTreasuryPhone() {
		return treasuryPhone;
	}
	public void setTreasuryPhone(String treasuryPhone) {
		this.treasuryPhone = treasuryPhone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
