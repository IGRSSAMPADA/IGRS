package com.wipro.igrs.newreginit.dto;

public class AadharRespDTO {
	
	private String lk;
	private String ret;
	private String srdhFailureCode;
	private String transactionCode;
	private String transactionId;
	private String auaFailureCode;
	private String ksaFailureCode;
	private String actn;

	private AadharResidentDetails residentDetails;
	
		
	public String getLk() {
		return lk;
	}
	public void setLk(String lk) {
		this.lk = lk;
	}
	public String getRet() {
		return ret;
	}
	public void setRet(String ret) {
		this.ret = ret;
	}
	public String getSrdhFailureCode() {
		return srdhFailureCode;
	}
	public void setSrdhFailureCode(String srdhFailureCode) {
		this.srdhFailureCode = srdhFailureCode;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getAuaFailureCode() {
		return auaFailureCode;
	}
	public void setAuaFailureCode(String auaFailureCode) {
		this.auaFailureCode = auaFailureCode;
	}

	public String getKsaFailureCode() {
		return ksaFailureCode;
	}
	public void setKsaFailureCode(String ksaFailureCode) {
		this.ksaFailureCode = ksaFailureCode;
	}
	public String getActn() {
		return actn;
	}
	public void setActn(String actn) {
		this.actn = actn;
	}
	public AadharResidentDetails getResidentDetails() {
		return residentDetails;
	}
	public void setResidentDetails(AadharResidentDetails residentDetails) {
		this.residentDetails = residentDetails;
	}
	
}
