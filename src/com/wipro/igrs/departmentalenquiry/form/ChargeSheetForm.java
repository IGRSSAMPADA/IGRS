/*
 * ChargeSheetForm.java
 */

package com.wipro.igrs.departmentalenquiry.form;


import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.departmentalenquiry.dto.ChargeSheetReleaseDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;
import com.wipro.igrs.departmentalenquiry.dto.WitnessDTO;


public class ChargeSheetForm extends BaseForm {
	private String	               formName;

	private ChargeSheetReleaseDTO	chargeSheetDTO	= new ChargeSheetReleaseDTO();

	private CriminalCaseEnquiryDTO	criminalCaseDTO	= new CriminalCaseEnquiryDTO();

	private WitnessDTO	           witnessDTO	    = new WitnessDTO();
	private FormFile theFile                        = null;

	/**
	 * 
	 */
	public ChargeSheetForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the chargeSheetDTO
	 */
	public ChargeSheetReleaseDTO getChargeSheetDTO() {
		return chargeSheetDTO;
	}

	/**
	 * @param chargeSheetDTO
	 *            the chargeSheetDTO to set
	 */
	public void setChargeSheetDTO(ChargeSheetReleaseDTO chargeSheetDTO) {
		this.chargeSheetDTO = chargeSheetDTO;
	}

	/**
	 * @return the criminalCaseDTO
	 */
	public CriminalCaseEnquiryDTO getCriminalCaseDTO() {
		return criminalCaseDTO;
	}

	/**
	 * @param criminalCaseDTO
	 *            the criminalCaseDTO to set
	 */
	public void setCriminalCaseDTO(CriminalCaseEnquiryDTO criminalCaseDTO) {
		this.criminalCaseDTO = criminalCaseDTO;
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

}//Action Form