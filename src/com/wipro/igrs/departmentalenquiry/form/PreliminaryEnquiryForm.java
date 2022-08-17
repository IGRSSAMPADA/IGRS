/**
 * PreliminaryEnquiryForm.java
 */


package com.wipro.igrs.departmentalenquiry.form;


import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeAcceptChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.EmployeeReleaseChanrgesDTO;
import com.wipro.igrs.departmentalenquiry.dto.PreliminaryEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.SuspensionOrderDTO;


/**
 * @author admin
 * 
 */
public class PreliminaryEnquiryForm extends BaseForm {
	private String strComplaintId;

	private String strPriliminaryDetails;

	private String strPriliminaryComments;

	private String strPreliminaryDate;

	private String strSuuportingDoc;
	
	private String complaintFlag;
	
	private String strComplaintDate;
	
	private FormFile theFile	= null;
	
	private FormFile strSignature = null;
	
	private PreliminaryEnquiryDTO  preEnquiryDTO = new PreliminaryEnquiryDTO();
	
	private EmployeeAcceptChanrgesDTO empAcceptChargesDTO = new EmployeeAcceptChanrgesDTO();
	
	private EmployeeReleaseChanrgesDTO empReleaseDTO      = new EmployeeReleaseChanrgesDTO();
	
	private SuspensionOrderDTO         suspensionDTO      = new SuspensionOrderDTO();

	
   
	/**
     * @return the preEnquiryDTO
     */
    public PreliminaryEnquiryDTO getPreEnquiryDTO() {
    	return preEnquiryDTO;
    }

	/**
     * @param preEnquiryDTO the preEnquiryDTO to set
     */
    public void setPreEnquiryDTO(PreliminaryEnquiryDTO preEnquiryDTO) {
    	this.preEnquiryDTO = preEnquiryDTO;
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
     * @return the complaintFlag
     */
    public String getComplaintFlag() {
    	return complaintFlag;
    }

	/**
     * @param complaintFlag the complaintFlag to set
     */
    public void setComplaintFlag(String complaintFlag) {
    	this.complaintFlag = complaintFlag;
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
     * @return the theFile
     */
    public FormFile getTheFile() {
    	return theFile;
    }

	/**
     * @param theFile the theFile to set
     */
    public void setTheFile(FormFile theFile) {
    	this.theFile = theFile;
    }

	/**
     * @return the empAcceptChargesDTO
     */
    public EmployeeAcceptChanrgesDTO getEmpAcceptChargesDTO() {
    	return empAcceptChargesDTO;
    }

	/**
     * @param empAcceptChargesDTO the empAcceptChargesDTO to set
     */
    public void setEmpAcceptChargesDTO(EmployeeAcceptChanrgesDTO empAcceptChargesDTO) {
    	this.empAcceptChargesDTO = empAcceptChargesDTO;
    }

	/**
     * @return the empReleaseDTO
     */
    public EmployeeReleaseChanrgesDTO getEmpReleaseDTO() {
    	return empReleaseDTO;
    }

	/**
     * @param empReleaseDTO the empReleaseDTO to set
     */
    public void setEmpReleaseDTO(EmployeeReleaseChanrgesDTO empReleaseDTO) {
    	this.empReleaseDTO = empReleaseDTO;
    }

	/**
     * @return the suspensionDTO
     */
    public SuspensionOrderDTO getSuspensionDTO() {
    	return suspensionDTO;
    }

	/**
     * @param suspensionDTO the suspensionDTO to set
     */
    public void setSuspensionDTO(SuspensionOrderDTO suspensionDTO) {
    	this.suspensionDTO = suspensionDTO;
    }

	/**
     * @return the strSignature
     */
    public FormFile getStrSignature() {
    	return strSignature;
    }

	/**
     * @param strSignature the strSignature to set
     */
    public void setStrSignature(FormFile strSignature) {
    	this.strSignature = strSignature;
    }
	
	

	
	
}
