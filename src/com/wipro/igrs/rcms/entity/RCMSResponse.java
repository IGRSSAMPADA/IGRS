package com.wipro.igrs.rcms.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="RCMSResponse")
public class RCMSResponse {

	private String status;
	private String errorType;
	private String applicationNumber;
	private String caseNumber;
	private String courtName;
	private String dateOfPresentation;
	private String tehsil;
	private String district;
	private String buyerName;
	private String buyerFather;
	private String sellerName;
	private String sellerFather;
	private String gramPanchayat;
	
	//for document detail
	private String registrationNumber;
	private String registrationDate;
	private String documentType;
	//case type
	private String cyberCase;

	@XmlElement(name="CyberCase")
	public String getCyberCase() {
		return cyberCase;
	}

	public void setCyberCase(String cyberCase) {
		this.cyberCase = cyberCase;
	}

	public RCMSResponse(){
		
	}
	
	@XmlElement(name="status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@XmlElement(name="errorType")
	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@XmlElement(name="ApplicationNumb")
	public String getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	@XmlElement(name="CaseNumb")
	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@XmlElement(name="CourtName")
	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	@XmlElement(name="DateofPresentation")
	public String getDateOfPresentation() {
		return dateOfPresentation;
	}

	public void setDateOfPresentation(String dateOfPresentation) {
		this.dateOfPresentation = dateOfPresentation;
	}

	@XmlElement(name="Tehsil")
	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	@XmlElement(name="District")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@XmlElement(name="buyerName")
	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	@XmlElement(name="buyerFather")
	public String getBuyerFather() {
		return buyerFather;
	}

	public void setBuyerFather(String buyerFather) {
		this.buyerFather = buyerFather;
	}

	@XmlElement(name="sellerName")
	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	@XmlElement(name="sellerFather")
	public String getSellerFather() {
		return sellerFather;
	}

	public void setSellerFather(String sellerFather) {
		this.sellerFather = sellerFather;
	}

	@XmlElement(name="GramPanchayat")
	public String getGramPanchayat() {
		return gramPanchayat;
	}
	
	public void setGramPanchayat(String gramPanchayat) {
		this.gramPanchayat = gramPanchayat;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
}
