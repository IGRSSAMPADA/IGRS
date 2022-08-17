/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.rgroupmappingmaster.dto;

import java.util.ArrayList;

public class RGroupmappingDTO {
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the rolegroupName
	 */
	public String getRolegroupName() {
		return rolegroupName;
	}

	/**
	 * @param rolegroupName
	 *            the rolegroupName to set
	 */
	public void setRolegroupName(String rolegroupName) {
		this.rolegroupName = rolegroupName;
	}

	public RGroupmappingDTO() {

	}

	private String roleName;
	private String rolegroupName;
	private String roleId;
	private String rolegroupId;
	private String activeFlag;
	private String pageName;
	private String name;
	private String value;
	ArrayList roleList;
	ArrayList rolegroupList;
	ArrayList rgroupmappingList;
	
	private String id; // added by roopam for removing jsp error.viewrgroupmappingmaster.jsp

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the roleId.
	 */

	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            The roleId to set.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
	 * @return Returns the roleList.
	 */
	public ArrayList getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            The roleList to set.
	 */
	public void setRoleList(ArrayList roleList) {
		this.roleList = roleList;
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
	 * @return Returns the rgroupmappingList.
	 */
	public ArrayList getRgroupmappingList() {
		return rgroupmappingList;
	}

	/**
	 * @param rgroupmappingList
	 *            The rgroupmappingList to set.
	 */
	public void setRgroupmappingList(ArrayList rgroupmappingList) {
		this.rgroupmappingList = rgroupmappingList;
	}

}