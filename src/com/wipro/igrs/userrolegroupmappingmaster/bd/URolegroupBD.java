/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmappingmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.userrolegroupmappingmaster.dao.URolegroupDAO;
import com.wipro.igrs.userrolegroupmappingmaster.dto.URolegroupDTO;
import com.wipro.igrs.userrolegroupmappingmaster.form.URolegroupForm;

public class URolegroupBD {

	public URolegroupBD() {
		// LoggerMsg.info("we are in URolegroupBD");
	}

	URolegroupDAO urgdao = new URolegroupDAO();

	/**
	 * Add the list of Userrolemappingmaster
	 * 
	 * @throws Exception
	 */

	public void addUserrolemappingmaster(URolegroupForm urolegroupForm)
			throws Exception {
		urgdao.addUserrolemappingmaster(urolegroupForm);
	}

	/**
	 * Gets the list of UserList
	 * 
	 * @param ArrayList
	 *            of UserList
	 * @return ArrayList of URolegroupDTO
	 * @throws Exception
	 */
	public ArrayList getUserList() throws Exception {
		return urgdao.getUserList();
	}

	/**
	 * Gets the list of rolegroupList
	 * 
	 * @param ArrayList
	 *            of rolegroupList
	 * @return ArrayList of URolegroupDTO
	 * @throws Exception
	 */
	public ArrayList getRolegroupList() throws Exception {
		return urgdao.getRolegroupList();
	}

	/**
	 * Gets the list of UserroleList
	 * 
	 * @param ArrayList
	 *            of UserroleList
	 * @return ArrayList of URolegroupDTO
	 * @throws Exception
	 */
	public ArrayList getUserroleList() throws Exception {
		return urgdao.getUserroleList();
	}

	/**
	 * Update the list of Userrolemappingmaster
	 * 
	 * @throws Exception
	 */
	public void updateUserrolemappingmaster(URolegroupForm urolegroupForm)
			throws Exception {
		urgdao.updateUserrolemappingmaster(urolegroupForm);
	}

	/**
	 * Gets the list of getRolegrouopId
	 * 
	 * @param RoleDTO
	 *            of rolegrouopId
	 * @return RGroupmappingDTO
	 * @throws Exception
	 */
	public URolegroupDTO getUserId(String userid) throws Exception {
		return urgdao.getUserId(userid);
	}
}
