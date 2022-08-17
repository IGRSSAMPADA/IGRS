/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionBD.java
 * Author      :   Sara Hussain 
 * Description :   Represents the Religion Bussiness Delegate Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */

package com.wipro.igrs.religionmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.religionmaster.bao.ReligionBAO;
import com.wipro.igrs.religionmaster.dao.ReligionDAO;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.form.ReligionForm;

public class ReligionBD {

	ReligionBAO religionbao = new ReligionBAO();

	/**
	 * Add the list of religionmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */

	public void addReligionMaster(ReligionDTO religionDTO, String roleId, String funId, String userId) throws Exception{

		religionbao.addReligion(religionDTO,roleId,funId,userId);

		
	}

	/**
	 * Gets the list of ReligionList
	 * 
	 * @param ArrayList
	 *            of religionList
	 * @return ArrayList of ReligionDTO
	 * @throws Exception
	 */
	public ArrayList getReligionList() throws Exception{
		
			return religionbao.getReligionList();

		
	}

	/**
	 * Update the list of religionmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */
	public void updateReligionMaster(ReligionDTO religionDTO, String roleId, String funId, String userId) throws Exception
			 {
		
			religionbao.updateReligion(religionDTO,roleId,funId,userId);

	}

	/**
	 * Gets the list of getActivityId
	 * 
	 * @param RoleDTO
	 *            of getActivityId
	 * @return ActivityDTO
	 * @throws Exception
	 */
	public ReligionDTO getReligionId(String religionid) throws Exception{
		
			return religionbao.getReligionId(religionid);

	}
}
