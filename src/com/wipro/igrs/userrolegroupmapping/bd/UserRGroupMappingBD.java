/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingBD.java
 * Author      :   Hend M. ismail
 * Description :   Represents BD Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  8th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.bd;

import java.util.ArrayList;

import com.wipro.igrs.userrolegroupmapping.bao.UserRGroupMappingBao;
import com.wipro.igrs.userrolegroupmapping.dto.UserRGroupMappingDTO;
import com.wipro.igrs.userrolegroupmapping.exception.UserRGroupMappingIGRSException;


public class UserRGroupMappingBD {

	public UserRGroupMappingBD() {
	}

	UserRGroupMappingBao userRGroupBao = new UserRGroupMappingBao();

	
	/**
	 * Gets the list of RoleGroupList
	 * 
	 * @param ArrayList
	 *            of roleGroupList
	 * @return ArrayList of UserRGroupMappingDTO
	 * @throws Exception
	 */
	
	public ArrayList getRoleGroupList() throws Exception {
		return userRGroupBao.getRoleGroupList();
	}
	
	
	/**
	 * Gets the list of UsersList
	 * 
	 * @param ArrayList
	 *            of usersList
	 * @return ArrayList of UserRGroupMappingDTO
	 * @throws Exception
	 */
	public ArrayList getUsersList() throws Exception {
		return userRGroupBao.getUsersList();
	}
	


	
	/**
	 * Gets the list of UserRGroupList
	 * 
	 * @param ArrayList
	 *            of userRGroupList
	 * @return ArrayList of UserRGroupMappingDTO
	 * @throws Exception
	 */
	public ArrayList getUserRGroupList() throws Exception {
		return userRGroupBao.getUserRGroupList();
	}



	/**
	 * Gets the List of UserRGroup's Ids
	 * 
	 * @param Array  
	 *            of UserRGroup's Ids
	 * @throws Exception
	 */
	public void deleteUserRGroup(String[] ids) throws Exception {
		userRGroupBao.deleteUserRGroup(ids);
	}
	
	public void updateUserTypemaster(UserRGroupMappingDTO userRGroupDTO)throws Exception {
		userRGroupBao.updateUserRGroupMapping(userRGroupDTO);
	}
	
	public UserRGroupMappingDTO getUserRGroupMappingId(String userRGroupId) throws Exception {
		return userRGroupBao.getUserRGroupMappingId(userRGroupId);
	}
	public void addUserRGroupMappingMaster (UserRGroupMappingDTO expHeadDTO)
	throws UserRGroupMappingIGRSException,Exception {
		userRGroupBao.addUserRGroupMappingMaster(expHeadDTO);
	}
}
