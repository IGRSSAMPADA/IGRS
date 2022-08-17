/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadMappingBD.java
 * Author      :   Hend M. ismail
 * Description :   Represents BD Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.bd;

import java.util.ArrayList;

import com.wipro.igrs.expheadmapping.bao.ExpHeadMappingBao;
import com.wipro.igrs.expheadmapping.dto.ExpHeadMappingDTO;
import com.wipro.igrs.expheadmapping.exception.ExpHeadMappingIGRSException;


public class ExpHeadMappingBD {

	public ExpHeadMappingBD() {
	}

	ExpHeadMappingBao expHeadBao = new ExpHeadMappingBao();



	/**
	 * Gets the list of UserTypeList
	 * 
	 * @param ArrayList
	 *            of majorHeadList
	 * @return ArrayList of MajorDTO
	 * @throws Exception
	 */
	
	
	public ArrayList getMajorHeadList() throws Exception {
		return expHeadBao.getMajorHeadList();
	}
	/**
	 * Gets the list of UserTypeList
	 * 
	 * @param ArrayList
	 *            of subMajorHeadList
	 * @return ArrayList of subMajorDTO
	 * @throws Exception
	 */
	public ArrayList getSubMajorHeadList() throws Exception {
		return expHeadBao.getSubMajorHeadList();
	}
	
	/**
	 * Gets the list of ExpHeadList
	 * 
	 * @param ArrayList
	 *            of expHeadList
	 * @return ArrayList of ExpHeadDTO
	 * @throws Exception
	 */
	
	
	public ArrayList getExpHeadList() throws Exception {
		return expHeadBao.getExpHeadList();
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
		return expHeadBao.getMinorHeadList();
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
		return expHeadBao.getObjectList();
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
		return expHeadBao.getSchemeList();
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
		return expHeadBao.getSegmentList();
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
		return expHeadBao.getDetailedHeadList();
	}



	/**
	 * Delet list of ExpHeadDTO
	 * 
	 * @param ExpHeadDTO
	 *            of getExpHeadId
	 * @throws Exception
	 */
	public void deleteExpHeadMasster(String[] ids) throws Exception {
		 expHeadBao.deleteExpHeadMasster(ids);
	}
	
	public void updateUserTypemaster(ExpHeadMappingDTO expHeadDTO)throws Exception {
		expHeadBao.updateUserTypemaster(expHeadDTO);
	}
	public ArrayList getMinorBySubMajor(String subMajorid)throws Exception{
		
		return expHeadBao.getMinorBySubMajor(subMajorid);
	}
	
	public ExpHeadMappingDTO getExpHeadMappingId(String majorid) throws Exception {
		return expHeadBao.getExpHeadMappingId(majorid);
	}
	public void addExpHeadMappingMster (ExpHeadMappingDTO expHeadDTO)
	throws ExpHeadMappingIGRSException,Exception {
		expHeadBao.addExpHeadMappingMster(expHeadDTO);
	}
}
