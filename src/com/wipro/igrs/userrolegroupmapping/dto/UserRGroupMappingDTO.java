/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingDTO.java
 * Author      :   Hend M. ismail
 * Description :   Represents the DTO Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  7th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.dto;

import java.io.Serializable;



public class UserRGroupMappingDTO implements Serializable{
	public UserRGroupMappingDTO() {

	}
	
	private String userRGroupId;
	
	private String userId;
	private String userName;
	
	private String roleGroupId;
	private String roleGroupName;
	
	
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleGroupId() {
		return roleGroupId;
	}
	public void setRoleGroupId(String roleGroupId) {
		this.roleGroupId = roleGroupId;
	}
	public String getRoleGroupName() {
		return roleGroupName;
	}
	public void setRoleGroupName(String roleGroupName) {
		this.roleGroupName = roleGroupName;
	}
	public String getUserRGroupId() {
		return userRGroupId;
	}
	public void setUserRGroupId(String userRGroupId) {
		this.userRGroupId = userRGroupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}


	













}