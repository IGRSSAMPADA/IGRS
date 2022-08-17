package com.wipro.igrs.RegCompMaker.dto;

import java.io.Serializable;


public class RegCompleteDutiesDTO implements Serializable{
	
	private String  stampduty;
	private String	nagarPalikaDuty;
	private String	panchayatDuty;
	private String municipalDuty;
	private String  janpadDuty;
	private String	upkarDuty;
	private String  totalduty;
	private String  registrationFee;
	private String 	balStampDuty;
	private String  adjStampDuty;
	private String  ecode;
	
	private String  paymentAmt;
	private String 	estampCode;
	private String 	paymentId;
	
	public String getPaymentAmt() {
		return paymentAmt;
	}
	public void setPaymentAmt(String paymentAmt) {
		this.paymentAmt = paymentAmt;
	}
	public String getMunicipalDuty() {
		return municipalDuty;
	}
	public void setMunicipalDuty(String municipalDuty) {
		this.municipalDuty = municipalDuty;
	}
	public String getJanpadDuty() {
		return janpadDuty;
	}
	public void setJanpadDuty(String janpadDuty) {
		this.janpadDuty = janpadDuty;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getAdjStampDuty() {
		return adjStampDuty;
	}
	public void setAdjStampDuty(String adjStampDuty) {
		this.adjStampDuty = adjStampDuty;
	}
	public String getBalStampDuty() {
		return balStampDuty;
	}
	public void setBalStampDuty(String balStampDuty) {
		this.balStampDuty = balStampDuty;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	
	
	
	public String getEstampCode() {
		return estampCode;
	}
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}
	public String getStampduty() {
		return stampduty;
	}
	public void setStampduty(String stampduty) {
		this.stampduty = stampduty;
	}
	public String getNagarPalikaDuty() {
		return nagarPalikaDuty;
	}
	public void setNagarPalikaDuty(String nagarPalikaDuty) {
		this.nagarPalikaDuty = nagarPalikaDuty;
	}
	public String getPanchayatDuty() {
		return panchayatDuty;
	}
	public void setPanchayatDuty(String panchayatDuty) {
		this.panchayatDuty = panchayatDuty;
	}
	public String getUpkarDuty() {
		return upkarDuty;
	}
	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}
	public String getTotalduty() {
		return totalduty;
	}
	public void setTotalduty(String totalduty) {
		this.totalduty = totalduty;
	}
	public String getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

}
