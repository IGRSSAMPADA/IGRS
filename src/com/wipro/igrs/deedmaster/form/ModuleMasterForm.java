/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterForm.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008  		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedmaster.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.deedmaster.dto.ModuleMasterDTO;

public class ModuleMasterForm extends ActionForm {

	private ArrayList periorites;
	private String deedPeriority;
	private boolean deedreq; // equivelent to PIN_REQUIRED in DB
	private boolean deedlinkprop; // equivelent to PROPERTY_RELATED_DEED in DB
	private boolean deedRor; // equivelent to ROR_REQUIRED in DB
	private boolean propertyValuerequired;
	private boolean dutyCelRequired;
	/* values represent the hidden fields */
	private String deedreqstr; // equivelent to PIN_REQUIRED in DB
	private String deedlinkpropstr; // equivelent to PROPERTY_RELATED_DEED in DB
	private String deedRorstr; // equivelent to ROR_REQUIRED in DB
	private String propertyValuerequiredstr;
	private String dutyCelRequiredstr;

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String description;
	private boolean status;
	private String deedStatus;
	private String deedCategory;

	private String pageTitle;
	private ModuleMasterDTO dto = new ModuleMasterDTO();
	private String modulename;
	private boolean payment;
	private boolean intimation;
	private String parentId;
	private String subname;
	private String createdDate;
	private ArrayList deedList = new ArrayList();

	public ModuleMasterForm() {
		periorites = new ArrayList();
		periorites.add(new String("[Select]"));
		for (int index = 1; index <= 60; index++) {
			// periorites.add(index);
		}

	}

	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	/**
	 * 
	 * @return parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return modulename
	 */
	public String getModulename() {
		return modulename;
	}

	/**
	 * 
	 * @param modulename
	 */
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	/**
	 * 
	 * @return pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * 
	 * @param pageTitle
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return ModuleMasterDTO
	 */
	public ModuleMasterDTO getDto() {
		return dto;
	}

	/**
	 * 
	 * @param ModuleMasterDTO
	 */
	public void setDto(ModuleMasterDTO dto) {
		this.dto = dto;
	}

	/**
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * 
	 * @return payment
	 */
	public boolean isPayment() {
		return payment;
	}

	/**
	 * 
	 * @param payment
	 */
	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	/**
	 * 
	 * @return intimation
	 */
	public boolean isIntimation() {
		return intimation;
	}

	/**
	 * 
	 * @param intimation
	 */
	public void setIntimation(boolean intimation) {
		this.intimation = intimation;
	}

	/**
	 * 
	 * @return subname
	 */
	public String getSubname() {
		return subname;
	}

	/**
	 * 
	 * @param subname
	 */
	public void setSubname(String subname) {
		this.subname = subname;
	}

	/**
	 * Method validate
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param HttpServletRequest
	 *            request
	 * @return ActionErrors
	 */

	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		super.validate(mapping, request);
		ActionErrors errors = new ActionErrors();
		if (getName() == null || getName().equals("")) {
			// errors.add("name", new ActionError("error.requiredfield"));
		}

		return errors;
	}

	public String getDeedCategory() {
		return deedCategory;
	}

	public boolean isDeedreq() {
		return deedreq;
	}

	public boolean isDeedlinkprop() {
		return deedlinkprop;
	}

	public boolean isDeedRor() {
		return deedRor;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDeedStatus() {
		return deedStatus;
	}

	public void setDeedStatus(String deedStatus) {
		this.deedStatus = deedStatus;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getDeedPeriority() {
		return deedPeriority;
	}

	public void setDeedPeriority(String deedPeriority) {
		this.deedPeriority = deedPeriority;
	}

	public boolean isPropertyValuerequired() {
		return propertyValuerequired;
	}

	public void setPropertyValuerequired(boolean propertyValuerequired) {
		this.propertyValuerequired = propertyValuerequired;
	}

	public boolean isDutyCelRequired() {
		return dutyCelRequired;
	}

	public void setDeedreq(boolean deedreq) {
		System.out.println("set deddReq...............................");
		this.deedreq = deedreq;
	}

	public void setDeedlinkprop(boolean deedlinkprop) {
		System.out.println("set deddLink");
		this.deedlinkprop = deedlinkprop;
	}

	public void setDeedRor(boolean deedRor) {
		System.out.println("set deeddROR");
		this.deedRor = deedRor;
	}

	public void setDutyCelRequired(boolean dutyCelRequired) {
		System.out.println("set dedddty");
		this.dutyCelRequired = dutyCelRequired;
	}

	public void setDeedCategory(String deedCategory) {
		this.deedCategory = deedCategory;
	}

	public ArrayList getPeriorites() {
		return periorites;
	}

	public void setPeriorites(ArrayList periorites) {
		this.periorites = periorites;
	}

	public String getDeedreqstr() {
		return deedreqstr;
	}

	public void setDeedreqstr(String deedreqstr) {
		this.deedreqstr = deedreqstr;
	}

	public String getDeedlinkpropstr() {
		return deedlinkpropstr;
	}

	public void setDeedlinkpropstr(String deedlinkpropstr) {
		this.deedlinkpropstr = deedlinkpropstr;
	}

	public String getDeedRorstr() {
		return deedRorstr;
	}

	public void setDeedRorstr(String deedRorstr) {
		this.deedRorstr = deedRorstr;
	}

	public String getPropertyValuerequiredstr() {
		return propertyValuerequiredstr;
	}

	public void setPropertyValuerequiredstr(String propertyValuerequiredstr) {
		this.propertyValuerequiredstr = propertyValuerequiredstr;
	}

	public String getDutyCelRequiredstr() {
		return dutyCelRequiredstr;
	}

	public void setDutyCelRequiredstr(String dutyCelRequiredstr) {
		this.dutyCelRequiredstr = dutyCelRequiredstr;
	}

}