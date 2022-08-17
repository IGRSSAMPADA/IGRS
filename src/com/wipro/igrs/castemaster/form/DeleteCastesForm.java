package com.wipro.igrs.castemaster.form;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class DeleteCastesForm extends org.apache.struts.action.ActionForm {

	private String[] selectedCasteIdList;

    public DeleteCastesForm () {
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        
    }


	/**
	 * @return the selectedCasteIdList
	 */
	public String[] getSelectedCasteIdList() {
		return selectedCasteIdList;
	}

	/**
	 * @param selectedCasteIdList the selectedCasteIdList to set
	 */
	public void setSelectedCasteIdList(String[] selectedCasteIdList) {
		this.selectedCasteIdList = selectedCasteIdList;
	}

	public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        return null;
    }


}