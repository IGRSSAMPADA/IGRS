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

package com.wipro.igrs.religionmaster.bao;

import java.util.ArrayList;

import com.wipro.igrs.religionmaster.dao.ReligionDAO;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.exception.ReligionAlreadyExistsException;
import com.wipro.igrs.religionmaster.form.ReligionForm;

public class ReligionBAO {

	ReligionDAO religiondao = new ReligionDAO();

	/**
	 * Add the list of religionmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */

	public void addReligion(ReligionDTO religionDTO, String roleId, String funId, String userId) throws Exception {
		boolean check=religiondao.isExist(religionDTO);
		
		if(check)
		{
			System.out.println("check true");
			throw new ReligionAlreadyExistsException();
		}
		else
		{
			System.out.println("check false");
			religiondao.addReligion(religionDTO,roleId,funId,userId);
		}
	}

	/**
	 * Gets the list of ReligionList
	 * 
	 * @param ArrayList
	 *            of religionList
	 * @return ArrayList of ReligionDTO
	 * @throws Exception
	 */
	public ArrayList getReligionList() throws Exception {
		return religiondao.getReligionList();
	}

	/**
	 * Update the list of religionmaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */
	public void updateReligion(ReligionDTO religionDTO, String roleId, String funId, String userId)
			throws Exception {
		boolean check=religiondao.isExist(religionDTO);
		//delete
			if(!religionDTO.getReligionStatus().equals("R"))
			{
				if(check && !religionDTO.getOldName().equals(religionDTO.getReligionName()))
				{
					throw new ReligionAlreadyExistsException();
				}
				else
				{
					religiondao.updateReligionMaster(religionDTO,roleId,funId,userId);
				}
		}
			else
			{
				religiondao.updateReligionMaster(religionDTO,roleId,funId,userId);
			}
	}

	/**
	 * Gets the list of getActivityId
	 * 
	 * @param RoleDTO
	 *            of getActivityId
	 * @return ActivityDTO
	 * @throws Exception
	 */
	public ReligionDTO getReligionId(String religionid) throws Exception {
		return religiondao.getReligionId(religionid);
	}
}
