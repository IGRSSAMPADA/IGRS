/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingActionForm.java
 * Author      :   Hend M. ismail
 * Description :   Represents the FormBean Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  8th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;



public class UserRGroupMappingActionForm extends ActionForm {

	
	private String userId;
	private String roleGroupId;
	private String userRGroupId;
	
	ArrayList userRGroupList;	
	ArrayList userOptions;
	ArrayList roleGroupOptions;
	

	
	String pageName;
	String[] selected;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleGroupId() {
		return roleGroupId;
	}
	public void setRoleGroupId(String roleGroupId) {
		this.roleGroupId = roleGroupId;
	}
	public String getUserRGroupId() {
		return userRGroupId;
	}
	public void setUserRGroupId(String userRGroupId) {
		this.userRGroupId = userRGroupId;
	}
	public ArrayList getUserOptions() {
		return userOptions;
	}
	public void setUserOptions(ArrayList userOptions) {
		this.userOptions = userOptions;
	}


	public ArrayList getRoleGroupOptions() {
		return roleGroupOptions;
	}
	public void setRoleGroupOptions(ArrayList roleGroupOptions) {
		this.roleGroupOptions = roleGroupOptions;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String[] getSelected() {
		return selected;
	}
	public void setSelected(String[] selected) {
		this.selected = selected;
	}
	public ArrayList getUserRGroupList() {
		return userRGroupList;
	}
	public void setUserRGroupList(ArrayList userRGroupList) {
		this.userRGroupList = userRGroupList;
	}

	
}