/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the FormBean Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;

public class UserTypeForm extends ActionForm {

	private String userTypeId;
	private String userTypeName;
	private String userTypeDesc;
	private String userTypeStatus;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	
	private String pageName;

	ArrayList userTypeList;
	String [] selected;



	/**
	 * @return Returns the activityName.
	 */
	public String getUserTypeName() {
		return userTypeName;
	}

	/**
	 * @param userTypeName
	 *            The userTypeName to set.
	 */
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	/**
	 * @return Returns the userTypeDesc.
	 */
	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	/**
	 * @param userTypeDesc
	 *            The userTypeDesc to set.
	 */
	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}

	/**
	 * @return Returns the userTypeStatus.
	 */
	public String getUserTypeStatus() {
		return userTypeStatus;
	}

	/**
	 * @param UserTypeStatus
	 *            The UserTypeStatus to set.
	 */
	public void setUserTypeStatus(String userTypeStatus) {
		this.userTypeStatus = userTypeStatus;
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



	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public ArrayList getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(ArrayList userTypeList) {
		this.userTypeList = userTypeList;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String[] getSelected() {
		return selected;
	}

	public void setSelected(String[] selected) {
		this.selected = selected;
	}
}