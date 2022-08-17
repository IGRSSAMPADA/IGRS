/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/


package com.wipro.igrs.auditinspection.form;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.dto.DROReportDetailsDTO;


/** 
 * MyEclipse Struts
 * Creation date: 07-03-2008
 * 
 * XDoclet definition:
 * @struts.form name="droInspViewForm"
 */
public class DROInspViewForm extends ActionForm {
	/*
	 * Generated Methods
	 */
	
	DROReportDetailsDTO  droReportDto  =  new DROReportDetailsDTO();
	private String actionType;
	
	//added by shruti
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/** 
	 * Method validate
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
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the droReportDto
	 */
	public DROReportDetailsDTO getDroReportDto() {
		return droReportDto;
	}

	/**
	 * @param droReportDto the droReportDto to set
	 */
	public void setDroReportDto(DROReportDetailsDTO droReportDto) {
		this.droReportDto = droReportDto;
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}