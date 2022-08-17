package com.wipro.igrs.baseaction.form;

import org.apache.struts.action.ActionForm;

/**
 * ===========================================================================
 * File : BaseForm.java Description : Represents the user form for all the
 * classes
 * 
 * Author : Madan Mohan Created Date : Dec 11, 2007
 * 
 * ===========================================================================
 */
public class BaseForm extends ActionForm {

	private String actionType;
	private String mode;

	private String CSRFTOKEN;
	
	public String getCSRFTOKEN() {
		return CSRFTOKEN;
	}

	public void setCSRFTOKEN(String csrftoken) {
		CSRFTOKEN = csrftoken;
	}

	public BaseForm() {
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getActionType() {
		return actionType;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

}
