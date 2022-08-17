package com.wipro.igrs.departmentalenquiry.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.departmentalenquiry.dto.ComplaintRegDTO;
import com.wipro.igrs.departmentalenquiry.dto.CriminalCaseEnquiryDTO;

public class ComplaintRegForm extends BaseForm
{
	
	
	private String formName;
	private ComplaintRegDTO complainRegDTO = new ComplaintRegDTO();
	
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

	
	
	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public ComplaintRegDTO getComplainRegDTO() {
		return complainRegDTO;
	}

	public void setComplainRegDTO(ComplaintRegDTO complainRegDTO) {
		this.complainRegDTO = complainRegDTO;
	}
	
}
