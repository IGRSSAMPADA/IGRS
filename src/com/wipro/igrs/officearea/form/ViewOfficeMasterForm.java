package com.wipro.igrs.officearea.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class ViewOfficeMasterForm extends org.apache.struts.action.ActionForm {

	
	private List officeMappingList = new ArrayList();

    public ViewOfficeMasterForm () {
    }

    
    /**
	 * @return the officeMappingList
	 */
	public List getOfficeMappingList() {
		return officeMappingList;
	}


	/**
	 * @param officeMappingList the officeMappingList to set
	 */
	public void setOfficeMappingList(List officeMappingList) {
		this.officeMappingList = officeMappingList;
	}


	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }


}