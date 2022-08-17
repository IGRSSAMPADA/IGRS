/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeBao .java
 * Author      :   Hend M. ismail
 * Description :   Represents BAO Class for UserType Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.usertypemaster.bao;

import java.util.ArrayList;

import com.wipro.igrs.usertypemaster.dao.UserTypeDAO;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.exception.UserTypeIGRSException;


public class UserTypeBao {

	public UserTypeBao() {
	}

	UserTypeDAO usertypedao = new UserTypeDAO();

	/**
	 * Add the list of usertypemaster
	 * @throws Exception 
	 * 
	 * @throws Exception
	 */
	public boolean isUserTypeNameExists(UserTypeDTO userTypeDTO) throws Exception{
		
		UserTypeDTO tmpDTO=null;
		
			tmpDTO=usertypedao.getUserTypeName(userTypeDTO.getUserTypeName());
			
			if(tmpDTO!=null)
				{
					if(tmpDTO.getUserTypeId().equals(userTypeDTO.getUserTypeId()))
						return false;
					else
						return true;
				}
			else
				return false;

		
	}
	public void addUserTypemaster(UserTypeDTO userTypeDTO) throws Exception{
	

		if(isUserTypeNameExists(userTypeDTO))
			{
				System.out.println("****************exception will be thrown");
				throw new UserTypeIGRSException("User Type Name already exsists");
				
			}
		else
			usertypedao.addUserTypemaster(userTypeDTO);

		

	}

	/**
	 * Gets the list of ActivityList
	 * 
	 * @param ArrayList
	 *            of activityList
	 * @return ArrayList of ActivityDTO
	 * @throws Exception
	 */
	public ArrayList getUserTypeList() throws Exception {
		return usertypedao.getUserTypeList();
	}

	/**
	 * Update the list of usertyprmaster
	 * 
	 * @throws Exception
	 */
	public void updateUserTypemaster(UserTypeDTO userTypeDTO)
			throws UserTypeIGRSException,Exception {
		
		
		if(isUserTypeNameExists(userTypeDTO))
		{
			System.out.println("****************exception will be thrown");
			throw new UserTypeIGRSException("User Type Name already exsists");
			
		}
	else
		usertypedao.updateUserTypemaster(userTypeDTO);
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
		return usertypedao.getUserTypeId(usertypeid);
	}

	public void deletUserTypemaster(String[] selected)throws Exception {

		for(int i=0;i<selected.length;i++)
		{
			System.out.println("********item  "+selected[i]);
			UserTypeDTO userTypeDTO= new UserTypeDTO();
			userTypeDTO=usertypedao.getUserTypeId(selected[i]);
			userTypeDTO.setUserTypeStatus("R");
			usertypedao.updateUserTypemaster(userTypeDTO);
		}
		
		
	}
}
