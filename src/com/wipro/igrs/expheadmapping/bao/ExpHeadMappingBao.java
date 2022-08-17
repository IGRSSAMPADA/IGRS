/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingBao.java
 * Author      :   Hend M. ismail
 * Description :   Represents BAO Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.expheadmapping.bao;

import java.util.ArrayList;

import com.wipro.igrs.expheadmapping.dao.ExpHeadMappingDAO;
import com.wipro.igrs.expheadmapping.dto.ExpHeadMappingDTO;
import com.wipro.igrs.expheadmapping.exception.ExpHeadMappingIGRSException;



public class ExpHeadMappingBao {

	public ExpHeadMappingBao() {
	}

	ExpHeadMappingDAO expheaddao = new ExpHeadMappingDAO();


	public boolean isExpHeadMappingExists(ExpHeadMappingDTO expHeadDTO) throws Exception{
		
		ExpHeadMappingDTO tmpDTO=null;
		
			tmpDTO=expheaddao.getExpHeadMappingId(expHeadDTO.getMajorHeadId());
			
			if(tmpDTO!=null)
						return true;

			else
				return false;

		
	}
	/**
	 * Gets the list of MajorHeadList
	 * 
	 * @param ArrayList
	 *            of MajorHeadList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getMajorHeadList() throws Exception {
		return expheaddao.getMajorHeadList();
	}

	
	/**
	 * Gets the list of SubMajorHeadList
	 * 
	 * @param ArrayList
	 *            of SubMajorHeadList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getSubMajorHeadList() throws Exception {
		return expheaddao.getSubMajorHeadList();
	}
	
	

	/**
	 * Gets the list of ExpHeadList
	 * 
	 * @param ArrayList
	 *            of ExpHeadList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getExpHeadList() throws Exception {
		return expheaddao.getExpHeadList();
	}
	
	/**
	 * Gets the list of MinorHeadList
	 * 
	 * @param ArrayList
	 *            of MinorHeadList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getMinorHeadList() throws Exception {
		return expheaddao.getMinorHeadList();
	}
	
	/**
	 * Gets the list of objectList
	 * 
	 * @param ArrayList
	 *            of objectList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getObjectList() throws Exception {
		return expheaddao.getObjectList();
	}
	
	/**
	 * Gets the list of schemeList
	 * 
	 * @param ArrayList
	 *            of schemeList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getSchemeList() throws Exception {
		return expheaddao.getSchemeList();
	}
	
	/**
	 * Gets the list of segmentList
	 * 
	 * @param ArrayList
	 *            of segmentList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getSegmentList() throws Exception {
		return expheaddao.getSegmentList();
	}
	/**
	 * Gets the list of detailedList
	 * 
	 * @param ArrayList
	 *            of detailedList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	public ArrayList getDetailedHeadList() throws Exception {
		return expheaddao.getDetailedHeadList();
	}

	
	/**
	 * Gets object of getExpHeadId
	 * 
	 * @param ExpHeadDTO
	 *            of getExpHeadId
	 * @return ExpHeadDTO
	 * @throws Exception
	 */
	public void deleteExpHeadMasster(String[] selected) throws Exception {
			 
			for(int i=0;i<selected.length;i++)
				 expheaddao.deleteExpHeadMaster(selected[i]);
			
	}
	
	public void updateUserTypemaster(ExpHeadMappingDTO expHeadDTO)throws Exception {
		expheaddao.updateExpHeadMappingMaster(expHeadDTO);
	}
	public ArrayList getMinorBySubMajor(String subMajorid)throws Exception{
		
		return expheaddao.getMinorBySubMajor(subMajorid);
	}
	
	public ExpHeadMappingDTO getExpHeadMappingId(String majorid) throws Exception {
		return expheaddao.getExpHeadMappingId(majorid);
	}
	
	public void addExpHeadMappingMster (ExpHeadMappingDTO expHeadDTO)
	throws ExpHeadMappingIGRSException,Exception {
		
		if(isExpHeadMappingExists(expHeadDTO))
		  throw new ExpHeadMappingIGRSException();

		else
				expheaddao.insertExpHeadMappingMster(expHeadDTO);
	}
}
