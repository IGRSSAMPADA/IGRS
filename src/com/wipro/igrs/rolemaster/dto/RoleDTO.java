/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for RoleMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.rolemaster.dto;
import java.util.ArrayList;
import com.wipro.igrs.rolemaster.dto.RoleDTO;


public class RoleDTO{
	private String roleId;
	private String roleName;
	private String roleDesc;
	private String roleStatus;
    private String rolepageName;
    ArrayList roleList;
	
	/**
	 * @return Returns the roleId.
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId The roleId to set.
	 */  
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName The roleName to set.
	 */  
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return Returns the roleDesc.
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * @param roleDesc The roleDesc to set.
	 */  
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/**
	 * @return Returns the roleStatus.
	 */
	public String getRoleStatus() {
		return roleStatus;
	}
	/**
	 * @param roleStatus The roleStatus to set.
	 */  
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
	/**
	 * @return Returns the rolepageName.
	 */
	public String getRolepageName() {
		return rolepageName;
	}
	/**
	 * @param rolepageName The rolepageName to set.
	 */  
	public void setRolepageName(String rolepageName) {
		this.rolepageName = rolepageName;
	}
	/**
	 * @return Returns the roleList.
	 */
	public ArrayList getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList The roleList to set.
	 */
	public void setRoleList(ArrayList roleList) {
		this.roleList = roleList;
	}
}