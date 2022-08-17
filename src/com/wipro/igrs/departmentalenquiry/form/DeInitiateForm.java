/**
 * DeInitiateForm.java
 */


package com.wipro.igrs.departmentalenquiry.form;


import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;


/**
 * @author oneapps
 *
 */
public class DeInitiateForm extends BaseForm {
	private String	               formName;

	private CriminalCaseEnquiryDTO	criminalCaseDTO	= new CriminalCaseEnquiryDTO();

	private String	               complaintFlag;
	private String complaintNo;

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the criminalCaseDTO
	 */
	public CriminalCaseEnquiryDTO getCriminalCaseDTO() {
		return criminalCaseDTO;
	}

	/**
	 * @param criminalCaseDTO the criminalCaseDTO to set
	 */
	public void setCriminalCaseDTO(CriminalCaseEnquiryDTO criminalCaseDTO) {
		this.criminalCaseDTO = criminalCaseDTO;
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
     * @return the complaintNo
     */
    public String getComplaintNo() {
    	return complaintNo;
    }

	/**
     * @param complaintNo the complaintNo to set
     */
    public void setComplaintNo(String complaintNo) {
    	this.complaintNo = complaintNo;
    }

}
