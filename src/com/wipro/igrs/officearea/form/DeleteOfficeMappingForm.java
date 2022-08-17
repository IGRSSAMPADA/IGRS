package com.wipro.igrs.officearea.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class DeleteOfficeMappingForm extends org.apache.struts.action.ActionForm {

	String[] officeList; 

    public DeleteOfficeMappingForm () {
    }
    
    

    /**
	 * @return the officeList
	 */
	public String[] getOfficeList() {
		return officeList;
	}



	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(String[] officeList) {
		this.officeList = officeList;
	}



	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }


}