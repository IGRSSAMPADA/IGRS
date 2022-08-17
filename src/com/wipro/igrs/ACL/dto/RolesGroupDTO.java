/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RolesGroupDTO.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the DTO class for the RolesGroup under ACL Module
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  31st MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class RolesGroupDTO implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;
	private ArrayList rolesList;
	private String actionRolesGrp;
	private String roleName;
	private String roleId;
	private String grpId;
	private String grpName;
	private String slctFlag;

	/**
	 * @return the slctFlag
	 */
	public String getSlctFlag() {
		return slctFlag;
	}

	/**
	 * @param slctFlag
	 *            the slctFlag to set
	 */
	public void setSlctFlag(String slctFlag) {
		this.slctFlag = slctFlag;
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
	 * @return the actionRolesGrp
	 */
	public String getActionRolesGrp() {
		return actionRolesGrp;
	}

	/**
	 * @param actionRolesGrp
	 *            the actionRolesGrp to set
	 */
	public void setActionRolesGrp(String actionRolesGrp) {
		this.actionRolesGrp = actionRolesGrp;
	}

	/**
	 * @return the grpId
	 */
	public String getGrpId() {
		return grpId;
	}

	/**
	 * @param grpId
	 *            the grpId to set
	 */
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	/**
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * @param grpName
	 *            the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * @return the rolesList
	 */
	public ArrayList getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList
	 *            the rolesList to set
	 */
	public void setRolesList(ArrayList rolesList) {
		this.rolesList = rolesList;
	}

	public int compareTo(Object obj) {
		RolesGroupDTO dto = (RolesGroupDTO) obj;
		int comp = roleName.compareTo(dto.getRoleName());

		return ((comp == 0) ? roleName.compareTo(dto.getRoleName()) : comp);

	}

}
