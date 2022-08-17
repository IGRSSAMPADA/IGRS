/**
 * EmployeeAcceptChanrgesDTO.java
 */


package com.wipro.igrs.departmentalenquiry.dto;


import java.io.Serializable;


/**
 * @author admin
 * 
 */
public class EmployeeAcceptChanrgesDTO implements Serializable {
	private String strComplaintId;

	private String strComplaintDate;

	private String strComplainAbout;

	private String strFinalCharge;

	private String strNatureOfCharge;

	private String strCompliantStatus;

	private String strComments;

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
     * @return the strComplaintDate
     */
    public String getStrComplaintDate() {
    	return strComplaintDate;
    }

	/**
     * @param strComplaintDate the strComplaintDate to set
     */
    public void setStrComplaintDate(String strComplaintDate) {
    	this.strComplaintDate = strComplaintDate;
    }

	/**
     * @return the strComplainAbout
     */
    public String getStrComplainAbout() {
    	return strComplainAbout;
    }

	/**
     * @param strComplainAbout the strComplainAbout to set
     */
    public void setStrComplainAbout(String strComplainAbout) {
    	this.strComplainAbout = strComplainAbout;
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
     * @return the strNatureOfCharge
     */
    public String getStrNatureOfCharge() {
    	return strNatureOfCharge;
    }

	/**
     * @param strNatureOfCharge the strNatureOfCharge to set
     */
    public void setStrNatureOfCharge(String strNatureOfCharge) {
    	this.strNatureOfCharge = strNatureOfCharge;
    }

	/**
     * @return the strCompliantStatus
     */
    public String getStrCompliantStatus() {
    	return strCompliantStatus;
    }

	/**
     * @param strCompliantStatus the strCompliantStatus to set
     */
    public void setStrCompliantStatus(String strCompliantStatus) {
    	this.strCompliantStatus = strCompliantStatus;
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

	
}
