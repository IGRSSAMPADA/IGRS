/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for RoleMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rolemaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.rolemaster.dao.RoleDAO;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.rolemaster.form.RoleForm;

public class RoleBD {

	public RoleBD() {

	}

	RoleDAO roledao = new RoleDAO();

	/**
	 * Add the list of rolemaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */

	public void addRolemaster(RoleForm roleForm, String roleId, String funId, String userId) throws Exception {
		roledao.addRolemaster(roleForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of roleList
	 * 
	 * @param ArrayList
	 *            of roleList
	 * @return ArrayList of RoleDTO
	 * @throws Exception
	 */
	public ArrayList getRoleList() throws Exception {
		return roledao.getRoleList();
	}

	/**
	 * Update the list of rolemaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */
	public void updateRolemaster(RoleForm roleForm, String roleId, String funId, String userId) throws Exception {
		roledao.updateRolemaster(roleForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of getRoleId
	 * 
	 * @param RoleDTO
	 *            of getRoleId
	 * @return RegisterofficesDTO
	 * @throws Exception
	 */
	public RoleDTO getRoleId(String roleid) throws Exception {
		return roledao.getRoleId(roleid);
	}
}
