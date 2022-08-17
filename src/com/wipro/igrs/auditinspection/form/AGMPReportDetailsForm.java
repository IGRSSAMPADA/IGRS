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
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.auditinspection.dto.AGMPReportDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PartyDetailsDTO;
import com.wipro.igrs.auditinspection.dto.PropertyDetailsDTO;
import com.wipro.igrs.baseaction.form.BaseForm;
import com.wipro.igrs.revenueManagement.dto.RevMgtAdvDTO;

/**
 * @author oneapps
 * 
 */
public class AGMPReportDetailsForm extends BaseForm {

	AGMPReportDetailsDTO agmpreportdetailsdto = new AGMPReportDetailsDTO();

	PartyDetailsDTO partyDto=new PartyDetailsDTO();
	PropertyDetailsDTO propertyDto=new PropertyDetailsDTO();
	//added by SHreeraj
	AGMPReportDetailsDTO agmpDTO=new AGMPReportDetailsDTO();
	private String formName;
	private String actionName;
	private String uId;
	private String toDate;
	private String fromDate;
	private String othersFormRegDate;  
	public String getOthersFormRegDate() {
		return othersFormRegDate;
	}

	public void setOthersFormRegDate(String othersFormRegDate) {
		this.othersFormRegDate = othersFormRegDate;
	}

	//added by shruti
	private String language;
	
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

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
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the uId
	 */
	public String getuId() {
		return uId;
	}

	/**
	 * @param uId the uId to set
	 */
	public void setuId(String uId) {
		this.uId = uId;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the agmpDTO
	 */
	public AGMPReportDetailsDTO getAgmpDTO() {
		return agmpDTO;
	}

	/**
	 * @param agmpDTO the agmpDTO to set
	 */
	public void setAgmpDTO(AGMPReportDetailsDTO agmpDTO) {
		this.agmpDTO = agmpDTO;
	}

	public PropertyDetailsDTO getPropertyDto() {
		return propertyDto;
	}

	public void setPropertyDto(PropertyDetailsDTO propertyDto) {
		this.propertyDto = propertyDto;
	}

	public PartyDetailsDTO getPartyDto() {
		return partyDto;
	}

	public void setPartyDto(PartyDetailsDTO partyDto) {
		this.partyDto = partyDto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String addMore;
	
	private String regFlag;

	

	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		ActionErrors aes = new ActionErrors();
		// action errors.

		return aes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	public AGMPReportDetailsDTO getAgmpreportdetailsdto() {
		return agmpreportdetailsdto;
	}

	/**
	 * @param agmpreportdetailsdto
	 */
	public void setAgmpreportdetailsdto(
			AGMPReportDetailsDTO agmpreportdetailsdto) {
		this.agmpreportdetailsdto = agmpreportdetailsdto;
	}

	/**
	 * @return
	 */
	public String getAddMore() {
		return addMore;
	}

	/**
	 * @param addMore
	 */
	public void setAddMore(String addMore) {
		this.addMore = addMore;
	}

}