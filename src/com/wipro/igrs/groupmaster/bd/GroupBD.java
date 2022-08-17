/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.groupmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.groupmaster.dao.GroupDAO;
import com.wipro.igrs.groupmaster.dto.GroupDTO;
import com.wipro.igrs.groupmaster.form.GroupForm;

public class GroupBD {

	public GroupBD() {
	}

	GroupDAO groupdao = new GroupDAO();

	/**
	 * Add the list of rolegroupmaster
	 * 
	 * @throws Exception
	 */

	public void addGroupmaster(GroupForm groupForm) throws Exception {
		groupdao.addGroupmaster(groupForm);
	}

	/**
	 * Gets the list of GroupList
	 * 
	 * @param ArrayList
	 *            of groupList
	 * @return ArrayList of GroupDTO
	 * @throws Exception
	 */
	public ArrayList getGroupList() throws Exception {
		return groupdao.getGroupList();
	}

	/**
	 * Update the list of rolegroupmaster
	 * 
	 * @throws Exception
	 */
	public void updateGroupmaster(GroupForm groupForm) throws Exception {
		groupdao.updateGroupmaster(groupForm);
	}

	/**
	 * Gets the list of getGroupId
	 * 
	 * @param RoleDTO
	 *            of getGroupId
	 * @return GroupDTO
	 * @throws Exception
	 */
	public GroupDTO getGroupId(String groupid) throws Exception {
		return groupdao.getGroupId(groupid);
	}
}
