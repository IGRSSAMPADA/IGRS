/*
 * DepartmentalEnquiryForm.java
 */

package com.wipro.igrs.departmentalenquiry.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;

public class DepartmentalEnquiryForm extends BaseForm {
	/*
	 * Generated Methods
	 */

	private CriminalCaseEnquiryDTO criminalCaseEnquiryDTO = new CriminalCaseEnquiryDTO();

	private String formName;

	/**
	 * Method validate
	 * 
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Method reset
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return
	 */
	public CriminalCaseEnquiryDTO getCriminalCaseEnquiryDTO() {
		return criminalCaseEnquiryDTO;
	}

	/**
	 * @param criminalCaseEnquiryDTO
	 */
	public void setCriminalCaseEnquiryDTO(
			CriminalCaseEnquiryDTO criminalCaseEnquiryDTO) {
		this.criminalCaseEnquiryDTO = criminalCaseEnquiryDTO;
	}

	/**
	 * @return
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
}