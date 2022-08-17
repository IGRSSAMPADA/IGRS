package com.wipro.igrs.officearea.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class ViewOfficeMappingForm extends org.apache.struts.action.ActionForm {

	private List officeList = new ArrayList();
	private String officeId;

    public ViewOfficeMappingForm () {
    }
    
    
    

    /**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}




	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}




	/**
	 * @return the officeList
	 */
	public List getOfficeList() {
		return officeList;
	}



	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(List officeList) {
		this.officeList = officeList;
	}



	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
    	return null;
    }


}