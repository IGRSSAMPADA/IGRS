package com.wipro.igrs.cybertehsil.forms.dto;

import java.io.Serializable;

public class PartyDetailsDTO implements Serializable {
	private String partyName;
	private String partyAdress;
	private String guardianName;
	private String relationType;
	private String relationTypeHindi;
	private String mobileNo;
	private String emailId;
	private String idProofType;
	private String idProofTypeHindi;
	private String idProof;
	private String fourDigit;
	private String partyTxnId;
	private String partyType;
	private String regTxnId;
	
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

	/**
	 * @return the relationTypeHindi
	 */
	public String getRelationTypeHindi() {
		return relationTypeHindi;
	}
	/**
	 * @param relationTypeHindi the relationTypeHindi to set
	 */
	public void setRelationTypeHindi(String relationTypeHindi) {
		this.relationTypeHindi = relationTypeHindi;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the idProofType
	 */
	public String getIdProofType() {
		return idProofType;
	}
	/**
	 * @param idProofType the idProofType to set
	 */
	public void setIdProofType(String idProofType) {
		this.idProofType = idProofType;
	}
	/**
	 * @return the idProofTypeHindi
	 */
	public String getIdProofTypeHindi() {
		return idProofTypeHindi;
	}
	/**
	 * @param idProofTypeHindi the idProofTypeHindi to set
	 */
	public void setIdProofTypeHindi(String idProofTypeHindi) {
		this.idProofTypeHindi = idProofTypeHindi;
	}
	/**
	 * @return the idProof
	 */
	public String getIdProof() {
		return idProof;
	}
	/**
	 * @param idProof the idProof to set
	 */
	public void setIdProof(String idProof) {
		this.idProof = idProof;
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
	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}
	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	/**
	 * @return the regTxnId
	 */
	public String getRegTxnId() {
		return regTxnId;
	}
	/**
	 * @param regTxnId the regTxnId to set
	 */
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	
}
