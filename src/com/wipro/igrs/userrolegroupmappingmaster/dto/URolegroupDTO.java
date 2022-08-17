/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmappingmaster.dto;

import java.util.ArrayList;

public class URolegroupDTO {
	public URolegroupDTO() {

	}

	private String userId;
	private String rolegroupId;
	private String activeFlag;
	private String pageName;
	private String name;
	private String value;
	ArrayList userList;
	ArrayList rolegroupList;
	ArrayList userroleList;

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the rolegroupId.
	 */
	public String getRolegroupId() {
		return rolegroupId;
	}

	/**
	 * @param rolegroupId
	 *            The rolegroupId to set.
	 */
	public void setRolegroupId(String rolegroupId) {
		this.rolegroupId = rolegroupId;
	}

	/**
	 * @return Returns the activeFlag.
	 */
	public String getActiveFlag() {
		return activeFlag;
	}

	/**
	 * @param activeFlag
	 *            The activeFlag to set.
	 */
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return Returns the userList.
	 */
	public ArrayList getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            The userList to set.
	 */
	public void setUserList(ArrayList userList) {
		this.userList = userList;
	}

	/**
	 * @return Returns the rolegroupList.
	 */
	public ArrayList getRolegroupList() {
		return rolegroupList;
	}

	/**
	 * @param rolegroupList
	 *            The rolegroupList to set.
	 */
	public void setRolegroupList(ArrayList rolegroupList) {
		this.rolegroupList = rolegroupList;
	}

	/**
	 * @return Returns the userroleList.
	 */
	public ArrayList getUserroleList() {
		return userroleList;
	}

	/**
	 * @param userroleList
	 *            The userroleList to set.
	 */
	public void setUserroleList(ArrayList userroleList) {
		this.userroleList = userroleList;
	}

}