/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.sm.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.sm.dto.StatusmanagementDTO;

public class StatusmanagementForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusmanagementForm() {
	}

	private String eno;
	private String firstName;
	private String lastName;
	private String location;
	private String designation;
	private String currentStatus;
	private String rpassword;
	private String pageName;
	private String edit;
	ArrayList statusList;

	StatusmanagementDTO dto = new StatusmanagementDTO();

	/**
	 * @return Returns the eno.
	 */
	public String getEno() {
		return eno;
	}

	/**
	 * @param eno
	 *            The eno to set.
	 */
	public void setEno(String eno) {
		this.eno = eno;
	}

	/**
	 * @return Returns the location.
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            The location to set.
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return Returns the designation.
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            The designation to set.
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return Returns the currentStatus.
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus
	 *            The currentStatus to set.
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return Returns the rpassword.
	 */
	public String getRpassword() {
		return rpassword;
	}

	/**
	 * @param rpassword
	 *            The rpassword to set.
	 */
	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}

	/**
	 * @return Returns the dto.
	 */
	public StatusmanagementDTO getDto() {
		return dto;
	}

	/**
	 * @param dto
	 *            The dto to set.
	 */
	public void setDto(StatusmanagementDTO dto) {
		this.dto = dto;
	}

	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            The pageName to set.
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return Returns the edit.
	 */
	public String getEdit() {
		return edit;
	}

	/**
	 * @param edit
	 *            The edit to set.
	 */
	public void setEdit(String edit) {
		this.edit = edit;
	}

	/**
	 * @return Returns the statusList.
	 */
	public ArrayList getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList
	 *            The statusList to set.
	 */
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}

}