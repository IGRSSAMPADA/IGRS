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
 * Description :   Represents the DTO Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class UserTypeDTO implements Serializable{
	public UserTypeDTO() {

	}

	private String userTypeId;
	private String userTypeName;
	private String userTypeDesc;
	private String userTypeStatus;
	private String createdBy;
	private String createdDate; 
	private String updatedBy;
	private String updatedDate; 
	



	/**
	 * @return Returns the userTypeId.
	 */
	public String getUserTypeId() {
		return userTypeId;
	}

	/**
	 * @param userTypeId
	 *            The userTypeId to set.
	 */
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

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






}