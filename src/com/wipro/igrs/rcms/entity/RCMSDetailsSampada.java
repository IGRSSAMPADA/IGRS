package com.wipro.igrs.rcms.entity;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="RCMSRequestSampada")
public class RCMSDetailsSampada {

	private String rcmsPassword;
	private String docTypeId;
	private String documentDate;
	private String documentNo;
	private String mutationTypeId;
	private String officeId;
	//added for cyber tehsil by Saurav
	private String URL_Form_6;
	private String URL_Form_6A;
	private String URL_Form_6B;
	private String finalDocumentCopy;
	private String applicationFee;
	private String transactionId;
	private String transactionDate;
	private String districtId;
	private ArrayList<PropertyDetails> Property= new ArrayList<PropertyDetails>() ;
	
	
	public RCMSDetailsSampada() {
		super();
	}
	
	@XmlElement
	public String getRcmsPassword() {
		return rcmsPassword;
	}
	
	public void setRcmsPassword(String rcmsPassword) {
		this.rcmsPassword = rcmsPassword;
	}


	@XmlElement
	public String getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}
	
	@XmlElement
	public String getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	
	@XmlElement
	public String getDocumentNo() {
		return documentNo;
	}
	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	@XmlElement
	public String getMutationTypeId() {
		return mutationTypeId;
	}
	public void setMutationTypeId(String mutationTypeId) {
		this.mutationTypeId = mutationTypeId;
	}

	@XmlElement(name="Property")
	public ArrayList<PropertyDetails> getProperty() {
		return Property;
	}

	public void setProperty(ArrayList<PropertyDetails> Property) {
		this.Property = Property;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	//added by saurav for cyber tehsil

	@XmlElement(name="URL_Form_A")
	public String getURL_Form_6() {
		return URL_Form_6;
	}

	public void setURL_Form_6(String form_6) {
		URL_Form_6 = form_6;
	}

	@XmlElement(name="URL_Form_A1")
	public String getURL_Form_6A() {
		return URL_Form_6A;
	}

	public void setURL_Form_6A(String form_6A) {
		URL_Form_6A = form_6A;
	}

	@XmlElement(name="URL_Form_A2")
	public String getURL_Form_6B() {
		return URL_Form_6B;
	}

	public void setURL_Form_6B(String form_6B) {
		URL_Form_6B = form_6B;
	}

	@XmlElement(name="Registry_URL")
	public String getFinalDocumentCopy() {
		return finalDocumentCopy;
	}

	public void setFinalDocumentCopy(String finalDocumentCopy) {
		this.finalDocumentCopy = finalDocumentCopy;
	}
	@XmlElement(name="ApplicationFee")
	public String getApplicationFee() {
		return applicationFee;
	}

	public void setApplicationFee(String applicationFee) {
		this.applicationFee = applicationFee;
	}
	@XmlElement(name="TransactionID")
	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	@XmlElement(name="TransactionDate")
	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	@XmlElement(name="districtid")
	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	
	
}
