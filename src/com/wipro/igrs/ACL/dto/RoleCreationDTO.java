/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ACLConstants.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  24th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings({ "rawtypes" })
public class RoleCreationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6387634929789636157L;
	private String roleName;
	private String rolDesc;
	private String status;
	private String actionRC;
	private int sno;
	private String roleId;
	private int activitycount;
	private String[] roleOfficeMapping;
	private String equalRoleFlag;
	ArrayList rolesList = new ArrayList();

	private ArrayList<OfficeDTO> officeListing;

	
	
	public String getEqualRoleFlag() {
		return equalRoleFlag;
	}

	public void setEqualRoleFlag(String equalRoleFlag) {
		this.equalRoleFlag = equalRoleFlag;
	}

	public int getActivitycount() {
		return activitycount;
	}

	public void setActivitycount(int activitycount) {
		this.activitycount = activitycount;
	}

	public RoleCreationDTO() {
		this.rolesList = new ArrayList();
	}

	public ArrayList getRolesList() {
		return rolesList;
	}

	public void setRolesList(ArrayList rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the actionRC
	 */
	public String getActionRC() {
		return actionRC;
	}

	/**
	 * @param actionRC
	 *            the actionRC to set
	 */
	public void setActionRC(String actionRC) {
		this.actionRC = actionRC;
	}

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
	 * @return the rolDesc
	 */
	public String getRolDesc() {
		return rolDesc;
	}

	/**
	 * @param rolDesc
	 *            the rolDesc to set
	 */
	public void setRolDesc(String rolDesc) {
		this.rolDesc = rolDesc;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleOfficeMapping
	 */
	public String[] getRoleOfficeMapping() {
		return roleOfficeMapping;
	}

	/**
	 * @param roleOfficeMapping
	 *            the roleOfficeMapping to set
	 */
	public void setRoleOfficeMapping(String[] roleOfficeMapping) {
		this.roleOfficeMapping = roleOfficeMapping;
	}

	/**
	 * @return the officeListing
	 */
	public ArrayList<OfficeDTO> getOfficeListing() {
		return officeListing;
	}

	/**
	 * @param officeListing
	 *            the officeListing to set
	 */
	public void setOfficeListing(ArrayList<OfficeDTO> officeListing) {
		this.officeListing = officeListing;
	}

}
