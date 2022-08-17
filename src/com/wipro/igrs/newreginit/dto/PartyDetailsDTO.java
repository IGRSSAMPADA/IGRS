package com.wipro.igrs.newreginit.dto;

import java.io.Serializable;

public class PartyDetailsDTO implements Serializable {
	private String partyName;
	private String partyAdress;
	private String guardianName;
	private String relationType;
	private String proofid;
	private String fourDigit;
	private String partyTxnId;
	private String emailId;
	private String mobileNumber;
	private String photoProodId;
	private String photoProofTypeName;
	private String categorName;
	private String partyAge;
	private String partyRemarks;
	private String partyType;
	private String esignStatus;
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyAdress() {
		return partyAdress;
	}
	public void setPartyAdress(String partyAdress) {
		this.partyAdress = partyAdress;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getRelationType() {
		return relationType;
	}
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	public String getProofid() {
		return proofid;
	}
	public void setProofid(String proofid) {
		this.proofid = proofid;
	}
	public String getFourDigit() {
		return fourDigit;
	}
	public void setFourDigit(String fourDigit) {
		this.fourDigit = fourDigit;
	}
	public String getPartyTxnId() {
		return partyTxnId;
	}
	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhotoProodId() {
		return photoProodId;
	}
	public void setPhotoProodId(String photoProodId) {
		this.photoProodId = photoProodId;
	}
	public String getPhotoProofTypeName() {
		return photoProofTypeName;
	}
	public void setPhotoProofTypeName(String photoProofTypeName) {
		this.photoProofTypeName = photoProofTypeName;
	}
	public String getCategorName() {
		return categorName;
	}
	public void setCategorName(String categorName) {
		this.categorName = categorName;
	}
	public String getPartyAge() {
		return partyAge;
	}
	public void setPartyAge(String partyAge) {
		this.partyAge = partyAge;
	}
	public String getPartyRemarks() {
		return partyRemarks;
	}
	public void setPartyRemarks(String partyRemarks) {
		this.partyRemarks = partyRemarks;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	/**
	 * @return the esignStatus
	 */
	public String getEsignStatus() {
		return esignStatus;
	}
	/**
	 * @param esignStatus the esignStatus to set
	 */
	public void setEsignStatus(String esignStatus) {
		this.esignStatus = esignStatus;
	}
	
}
