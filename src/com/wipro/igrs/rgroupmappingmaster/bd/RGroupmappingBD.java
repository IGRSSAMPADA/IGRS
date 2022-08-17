/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rgroupmappingmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.rgroupmappingmaster.dao.RGroupmappingDAO;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;

public class RGroupmappingBD {

	public RGroupmappingBD() {
	}

	RGroupmappingDAO rgdao = new RGroupmappingDAO();

	/**
	 * Add the list of RGroupmapingmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */

	public void addRgroupmappingmaster(RGroupmappingForm rgroupmappingForm, String roleId, String funId, String userId)
			throws Exception {
		rgdao.addRgroupmappingmaster(rgroupmappingForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of roleList
	 * 
	 * @param ArrayList
	 *            of roleList
	 * @return ArrayList of RGroupmappingDTO
	 * @throws Exception
	 */
	public ArrayList getRoleList() throws Exception {
		return rgdao.getRoleList();
	}

	/**
	 * Gets the list of rolegroupList
	 * 
	 * @param ArrayList
	 *            of rolegroupList
	 * @return ArrayList of RGroupmappingDTO
	 * @throws Exception
	 */
	public ArrayList getRolegroupList() throws Exception {
		return rgdao.getRolegroupList();
	}

	/**
	 * Gets the list of rgroupmappingList
	 * 
	 * @param ArrayList
	 *            of rgroupmappingList
	 * @return ArrayList of RGroupmappingDTO
	 * @throws Exception
	 */
	public ArrayList getRgroupmappingList() throws Exception {
		return rgdao.getRgroupmappingList();
	}

	/**
	 * Update the list of RGroupmapingmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */
	public void updateRgroupmappingmaster(RGroupmappingForm rgroupmappingForm, String roleId, String funId, String userId)
			throws Exception {
		rgdao.updateRgroupmappingmaster(rgroupmappingForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of getRolegrouopId
	 * 
	 * @param RoleDTO
	 *            of rolegrouopId
	 * @return RGroupmappingDTO
	 * @throws Exception
	 */
	public RGroupmappingDTO getRolegrouopId(String rolegroupid)
			throws Exception {
		return rgdao.getRolegrouopId(rolegroupid);
	}
}
