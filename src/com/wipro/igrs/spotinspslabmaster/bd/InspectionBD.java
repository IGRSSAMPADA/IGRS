/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2008-09
 *==============================================================================
 *
 * File Name   :   InspectionBD.java
 * Author      :   Yousry Ibrahem 
 * Description :   Represents the BD Class for  Spot Inspection Slab master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */



package com.wipro.igrs.spotinspslabmaster.bd;

import java.util.Vector;

import com.wipro.igrs.spotinspslabmaster.bao.InspectionBAO;
import com.wipro.igrs.spotinspslabmaster.dao.InspectionDAO;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;

public class InspectionBD {


	public InspectionBD()
	{
		
	}
	/**
	 * 
	 * @param insDTO
	 */
	public void add(InspectionDTO insDTO)
	{
		InspectionBAO insBAO=new InspectionBAO();
		insBAO.add(insDTO);
	}
	/**
	 * 
	 * @param language 
	 * @return
	 */
	public Vector viewAllInspections(String language)
	{
		InspectionBAO insBAO=new InspectionBAO();
		
		return	insBAO.viewAllInspections(language);
	}
	/**
	 * 
	 * @return
	 */
	public Vector viewAllIActiveInspections()
	{
		InspectionBAO insBAO=new InspectionBAO();
		
		return	insBAO.viewAllActiveInspections();
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean isExist(String id)
	{
		InspectionBAO insBAO=new InspectionBAO();
		return insBAO.isExist(id);
	}
	/**
	 * 
	 * @param id
	 * @param language 
	 * @return
	 */
	public InspectionDTO getInspectionById(String id, String language)
	{
		InspectionBAO insBAO=new InspectionBAO();
		return insBAO.getInspectionById(id,language);
	}
	/**
	 * 
	 * @param insDTO
	 */
	public void updateINspection(InspectionDTO insDTO)
	{
		InspectionBAO insBAO=new InspectionBAO();
		insBAO.updateInspection(insDTO);
	}
	/**
	 * 
	 * @param insDTO
	 * @return
	 */
	public String approveINspection(InspectionDTO insDTO)
	{
		InspectionBAO insBAO=new InspectionBAO();
		return insBAO.approveInspection(insDTO);
	}
	/**
	 * 
	 * @param id
	 */
	public void deleteInspection(String id)
	{
		InspectionBAO insBAO=new InspectionBAO();
		insBAO.deleteInspection(id);
	}
}
