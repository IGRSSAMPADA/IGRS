/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingBao.java
 * Author      :   Hend M. ismail
 * Description :   Represents BAO Class for UserRoleGroup Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmapping.bao;

import java.util.ArrayList;

import com.wipro.igrs.userrolegroupmapping.dao.UserRGroupMappingDAO;
import com.wipro.igrs.userrolegroupmapping.dto.UserRGroupMappingDTO;
import com.wipro.igrs.userrolegroupmapping.exception.UserRGroupMappingIGRSException;




public class UserRGroupMappingBao {

	public UserRGroupMappingBao() {
	}

	UserRGroupMappingDAO userRGroupDao = new UserRGroupMappingDAO();


	public boolean isUserRGroupMappingExists(UserRGroupMappingDTO userRGroupDTO) throws Exception{
		
		
		ArrayList rowforuser=userRGroupDao.getUserRGroupMappingUserID(userRGroupDTO.getUserId());
		
		if(!rowforuser.isEmpty())
		{
			
			for(int i=0;i<rowforuser.size();i++)
			{
				UserRGroupMappingDTO tmpDTO=(UserRGroupMappingDTO)(rowforuser.get(i));
				if(tmpDTO.getRoleGroupId().equals(userRGroupDTO.getRoleGroupId()) && !(tmpDTO.getUserRGroupId().equals(userRGroupDTO.getUserRGroupId())))
					return true;
				
			}
			
		}

		else 
			return false;
		
		return false;
		
	}

	public ArrayList getUsersList() throws Exception {
		return userRGroupDao.getUsersList();
	}

	public ArrayList getUserRGroupList() throws Exception {
		return userRGroupDao.getUserRGroupList();
	}

	public ArrayList getRoleGroupList() throws Exception {
		return userRGroupDao.getRoleGroupList();
	}

	public void deleteUserRGroup(String[] selected) throws Exception {
			 
			for(int i=0;i<selected.length;i++)
				userRGroupDao.deleteUserRGroup(selected[i]);
			
	}
	
	public void updateUserRGroupMapping(UserRGroupMappingDTO userRGroupDTO)throws Exception {
		if(isUserRGroupMappingExists(userRGroupDTO))
			  throw new UserRGroupMappingIGRSException();

			else			
				userRGroupDao.updateUserRGroupMapping(userRGroupDTO);
	}

	
	public UserRGroupMappingDTO getUserRGroupMappingId(String userRGroupId) throws Exception {
		return userRGroupDao.getUserRGroupMappingId(userRGroupId);
	}
	
	public void addUserRGroupMappingMaster (UserRGroupMappingDTO userRGroupDTO)
	throws UserRGroupMappingIGRSException,Exception {
		
		if(isUserRGroupMappingExists(userRGroupDTO))
		  throw new UserRGroupMappingIGRSException();

		else
			userRGroupDao.insertUserRGroupMappingMaster(userRGroupDTO);
	}

	public UserRGroupMappingDTO getExpHeadMappingId(String userRGroupId) throws Exception {
		return userRGroupDao.getUserRGroupMappingId(userRGroupId);
	}

}
