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
 * Description :   Represents the BAO Class for  Spot Inspection Slab master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.spotinspslabmaster.bao;

import java.util.Vector;

import com.wipro.igrs.spotinspslabmaster.dao.InspectionDAO;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;

public class InspectionBAO {
	
		/**
		 * 
		 * @param insDTO
		 */
		public void add(InspectionDTO insDTO)
		{
			InspectionDAO insDAO=new InspectionDAO();
			insDAO.addInspection(insDTO);
		}
		/**
		 * 
		 * @param language 
		 * @return
		 */
		public Vector viewAllInspections(String language)
		{
			InspectionDAO insDAO=new InspectionDAO();
			
			return	insDAO.getAllInspections(language);
		}
		/**
		 * 
		 * @return
		 */
		public Vector viewAllActiveInspections()
		{
			InspectionDAO insDAO=new InspectionDAO();
			
			return	insDAO.getAllActiveInspections();
		}
		
		/**
		 * 
		 * @param id
		 * @return
		 */
		public boolean isExist(String id)
		{
			InspectionDAO insDAO=new InspectionDAO();
			return insDAO.isExist(id);
		}
		
		/**
		 * 
		 * @param id
		 * @param language 
		 * @return
		 */
		public InspectionDTO getInspectionById(String id, String language)
		{
			InspectionDAO insDAO=new InspectionDAO();
			return insDAO.getInspectionById(id,language);
		}
		
		/**
		 * 
		 * @param insDTO
		 */
		public void updateInspection(InspectionDTO insDTO)
		{
			InspectionDAO insDAO=new InspectionDAO();
			insDAO.updateInspection(insDTO);
		}
		
		/**
		 * 
		 * @param insDTO
		 * @return
		 */
		public String approveInspection(InspectionDTO insDTO)
		{
			InspectionDAO insDAO=new InspectionDAO();
			return insDAO.updtApproveInspection(insDTO);
		}
		
		/**
		 * 
		 * @param id
		 */
		public void deleteInspection(String id)
		{
			InspectionDAO insDAO=new InspectionDAO();
			insDAO.deleteInspection(id);
		}
}

