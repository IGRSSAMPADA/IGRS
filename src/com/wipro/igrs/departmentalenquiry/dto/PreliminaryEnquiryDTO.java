/**
 * PreliminaryEnquiryDTO.java
 */
package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class PreliminaryEnquiryDTO implements Serializable {
	private String strComplaintId;

	private String strPriliminaryDetails;

	private String strPriliminaryComments;

	private String strPreliminaryDate;

	private String strSuuportingDoc;
	
	private String strDocumentType;
	
	private String strDocumentId;
	
	private String strMajorStatus;
	
	private String strFinalCharge;

	private String natureOfCharge;
	public String getNatureOfCharge() {
		return natureOfCharge;
	}

	public void setNatureOfCharge(String natureOfCharge) {
		this.natureOfCharge = natureOfCharge;
	}

	/**
     * @return the strDocumentType
     */
    public String getStrDocumentType() {
    	return strDocumentType;
    }

	/**
     * @param strDocumentType the strDocumentType to set
     */
    public void setStrDocumentType(String strDocumentType) {
    	this.strDocumentType = strDocumentType;
    }

	/**
     * @return the strDocumentId
     */
    public String getStrDocumentId() {
    	return strDocumentId;
    }

	/**
     * @param strDocumentId the strDocumentId to set
     */
    public void setStrDocumentId(String strDocumentId) {
    	this.strDocumentId = strDocumentId;
    }

	/**
     * @return the strComplaintId
     */
    public String getStrComplaintId() {
    	return strComplaintId;
    }

	/**
     * @param strComplaintId the strComplaintId to set
     */
    public void setStrComplaintId(String strComplaintId) {
    	this.strComplaintId = strComplaintId;
    }

	/**
     * @return the strPriliminaryDetails
     */
    public String getStrPriliminaryDetails() {
    	return strPriliminaryDetails;
    }

	/**
     * @param strPriliminaryDetails the strPriliminaryDetails to set
     */
    public void setStrPriliminaryDetails(String strPriliminaryDetails) {
    	this.strPriliminaryDetails = strPriliminaryDetails;
    }

	/**
     * @return the strPriliminaryComments
     */
    public String getStrPriliminaryComments() {
    	return strPriliminaryComments;
    }

	/**
     * @param strPriliminaryComments the strPriliminaryComments to set
     */
    public void setStrPriliminaryComments(String strPriliminaryComments) {
    	this.strPriliminaryComments = strPriliminaryComments;
    }

	/**
     * @return the strPreliminaryDate
     */
    public String getStrPreliminaryDate() {
    	return strPreliminaryDate;
    }

	/**
     * @param strPreliminaryDate the strPreliminaryDate to set
     */
    public void setStrPreliminaryDate(String strPreliminaryDate) {
    	this.strPreliminaryDate = strPreliminaryDate;
    }

	/**
     * @return the strSuuportingDoc
     */
    public String getStrSuuportingDoc() {
    	return strSuuportingDoc;
    }

	/**
     * @param strSuuportingDoc the strSuuportingDoc to set
     */
    public void setStrSuuportingDoc(String strSuuportingDoc) {
    	this.strSuuportingDoc = strSuuportingDoc;
    }

	/**
	 * @return the strMajorStatus
	 */
	public String getStrMajorStatus() {
		return strMajorStatus;
	}

	/**
	 * @param strMajorStatus the strMajorStatus to set
	 */
	public void setStrMajorStatus(String strMajorStatus) {
		this.strMajorStatus = strMajorStatus;
	}

	public String getStrFinalCharge() {
		return strFinalCharge;
	}

	public void setStrFinalCharge(String strFinalCharge) {
		this.strFinalCharge = strFinalCharge;
	}

	

    
}
