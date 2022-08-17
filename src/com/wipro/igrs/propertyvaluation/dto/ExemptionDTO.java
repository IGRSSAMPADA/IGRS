package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;


public class ExemptionDTO implements Serializable{
 
	private String exemId;
	private String exemType;
	private String exemAmt;
	private String exemAmtType;
	private String exemDeedId;
	private String hdnExempAmount;
	private String hdnExAmount;
	private String hdnExemptions;
	
	public String getHdnExemptions() {
		return hdnExemptions;
	}
	public void setHdnExemptions(String hdnExemptions) {
		this.hdnExemptions = hdnExemptions;
	}
	public String getHdnExAmount() {
		return hdnExAmount;
	}
	public void setHdnExAmount(String hdnExAmount) {
		this.hdnExAmount = hdnExAmount;
	}
	public String getHdnExempAmount() {
		return hdnExempAmount;
	}
	public void setHdnExempAmount(String hdnExempAmount) {
		this.hdnExempAmount = hdnExempAmount;
	}
	public String getExemId() {
		return exemId;
	}
	public void setExemId(String exemId) {
		this.exemId = exemId;
	}
	public String getExemType() {
		return exemType;
	}
	public void setExemType(String exemType) {
		this.exemType = exemType;
	}
	public String getExemAmt() {
		return exemAmt;
	}
	public void setExemAmt(String exemAmt) {
		this.exemAmt = exemAmt;
	}
	public String getExemAmtType() {
		return exemAmtType;
	}
	public void setExemAmtType(String exemAmtType) {
		this.exemAmtType = exemAmtType;
	}
	public String getExemDeedId() {
		return exemDeedId;
	}
	public void setExemDeedId(String exemDeedId) {
		this.exemDeedId = exemDeedId;
	}
}
