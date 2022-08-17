/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the BD Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.usertypemaster.bao.UserTypeBao;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.exception.UserTypeIGRSException;


public class UserTypeBD {

	public UserTypeBD() {
	}

	UserTypeBao usertypeBao = new UserTypeBao();

	/**
	 * Add the list of usertypemaster
	 * @throws Exception 
	 * 
	 * @throws Exception
	 */

	public void addUserTypemaster(UserTypeDTO userTypeDTO)throws Exception{
		usertypeBao.addUserTypemaster(userTypeDTO);
	}

	/**
	 * Gets the list of UserTypeList
	 * 
	 * @param ArrayList
	 *            of usertypeList
	 * @return ArrayList of ActivityDTO
	 * @throws Exception
	 */
	public ArrayList getUserTypeList() throws Exception {
		return usertypeBao.getUserTypeList();
	}

	/**
	 * Update the list of usertyprmaster
	 * 
	 * @throws Exception
	 */
	public void updateUserTypemaster(UserTypeDTO userTypeDTO)
			throws UserTypeIGRSException,Exception {
		usertypeBao.updateUserTypemaster(userTypeDTO);
	}

	/**
	 * Gets the list of getActivityId
	 * 
	 * @param RoleDTO
	 *            of getActivityId
	 * @return ActivityDTO
	 * @throws Exception
	 */
	public UserTypeDTO getUserTypeId(String usertypeid) throws Exception {
		return usertypeBao.getUserTypeId(usertypeid);
	}

	public void deletUserTypemaster(String[] selected) throws Exception {

		usertypeBao.deletUserTypemaster(selected);
		
	}
}
