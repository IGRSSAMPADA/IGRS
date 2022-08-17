/**
 * SuspensionOrderDTO.java
 */


package com.wipro.igrs.departmentalenquiry.dto;


import java.io.Serializable;

import org.apache.struts.upload.FormFile;


/**
 * @author admin
 * 
 */
public class SuspensionOrderDTO implements Serializable {
	private String strComplaintId;

	private String strIssuingAuthority;

	private String strDateOfIssue;

	private String strPlaceOfIssue;

	private String strAddress;

	private String strDetails;

	private String strComments;

	private String strSignatureFileName;
	
	private String strFinalCharge;
	
	private String natureOfCharge;
	
	private String strEmpId;
	
	private String strEmpDesignation;
	
	private String strEmpName;
	
	private String strDocumentId;
	
	

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
     * @return the strEmpId
     */
    public String getStrEmpId() {
    	return strEmpId;
    }

	/**
     * @param strEmpId the strEmpId to set
     */
    public void setStrEmpId(String strEmpId) {
    	this.strEmpId = strEmpId;
    }

	/**
     * @return the strEmpDesignation
     */
    public String getStrEmpDesignation() {
    	return strEmpDesignation;
    }

	/**
     * @param strEmpDesignation the strEmpDesignation to set
     */
    public void setStrEmpDesignation(String strEmpDesignation) {
    	this.strEmpDesignation = strEmpDesignation;
    }

	/**
     * @return the strEmpName
     */
    public String getStrEmpName() {
    	return strEmpName;
    }

	/**
     * @param strEmpName the strEmpName to set
     */
    public void setStrEmpName(String strEmpName) {
    	this.strEmpName = strEmpName;
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
	 * @return the strIssuingAuthority
	 */
	public String getStrIssuingAuthority() {
		return strIssuingAuthority;
	}

	/**
	 * @param strIssuingAuthority the strIssuingAuthority to set
	 */
	public void setStrIssuingAuthority(String strIssuingAuthority) {
		this.strIssuingAuthority = strIssuingAuthority;
	}

	/**
	 * @return the strDateOfIssue
	 */
	public String getStrDateOfIssue() {
		return strDateOfIssue;
	}

	/**
	 * @param strDateOfIssue the strDateOfIssue to set
	 */
	public void setStrDateOfIssue(String strDateOfIssue) {
		this.strDateOfIssue = strDateOfIssue;
	}

	/**
	 * @return the strPlaceOfIssue
	 */
	public String getStrPlaceOfIssue() {
		return strPlaceOfIssue;
	}

	/**
	 * @param strPlaceOfIssue the strPlaceOfIssue to set
	 */
	public void setStrPlaceOfIssue(String strPlaceOfIssue) {
		this.strPlaceOfIssue = strPlaceOfIssue;
	}

	/**
	 * @return the strAddress
	 */
	public String getStrAddress() {
		return strAddress;
	}

	/**
	 * @param strAddress the strAddress to set
	 */
	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
	}

	/**
	 * @return the strDetails
	 */
	public String getStrDetails() {
		return strDetails;
	}

	/**
	 * @param strDetails the strDetails to set
	 */
	public void setStrDetails(String strDetails) {
		this.strDetails = strDetails;
	}

	/**
	 * @return the strComments
	 */
	public String getStrComments() {
		return strComments;
	}

	/**
	 * @param strComments the strComments to set
	 */
	public void setStrComments(String strComments) {
		this.strComments = strComments;
	}

	/**
	 * @return the strSignatureFileName
	 */
	public String getStrSignatureFileName() {
		return strSignatureFileName;
	}

	/**
	 * @param strSignatureFileName the strSignatureFileName to set
	 */
	public void setStrSignatureFileName(String strSignatureFileName) {
		this.strSignatureFileName = strSignatureFileName;
	}

	/**
     * @return the strFinalCharge
     */
    public String getStrFinalCharge() {
    	return strFinalCharge;
    }

	/**
     * @param strFinalCharge the strFinalCharge to set
     */
    public void setStrFinalCharge(String strFinalCharge) {
    	this.strFinalCharge = strFinalCharge;
    }

	/**
     * @return the natureOfCharge
     */
    public String getNatureOfCharge() {
    	return natureOfCharge;
    }

	/**
     * @param natureOfCharge the natureOfCharge to set
     */
    public void setNatureOfCharge(String natureOfCharge) {
    	this.natureOfCharge = natureOfCharge;
    }

	
}
