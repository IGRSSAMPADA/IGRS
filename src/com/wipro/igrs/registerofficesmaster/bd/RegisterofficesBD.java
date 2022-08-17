/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RegisterofficesBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for RegisterofficeMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  4th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.registerofficesmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.registerofficesmaster.dao.RegisterofficesDAO;
import com.wipro.igrs.registerofficesmaster.dto.RegisterofficesDTO;
import com.wipro.igrs.registerofficesmaster.form.RegisterofficesForm;

public class RegisterofficesBD {

	public RegisterofficesBD() {
	}

	RegisterofficesDAO officedao = new RegisterofficesDAO();

	/**
	 * Add the list of Registerofficemaster
	 * @param userId 
	 * @param funId 
	 * @param roleId 
	 * 
	 * @throws Exception
	 */

	public void addRegisterofficemaster(RegisterofficesForm registerofficesForm,String roleId, String funId, String userId)
			throws Exception {
		officedao.addRegisterofficemaster(registerofficesForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of RegisterofficesList
	 * 
	 * @param ArrayList
	 *            of RegisterofficesList
	 * @return ArrayList of RegisterofficesDTO
	 * @throws Exception
	 */
	public ArrayList getRegisterofficesList() throws Exception {
		return officedao.getRegisterofficesList();
	}

	/**
	 * Update the list of Registerofficemaster
	 * 
	 * @throws Exception
	 */
	public void updateRegisterofficemaster(
			RegisterofficesForm registerofficesForm,String roleId, String funId, String userId) throws Exception {
		officedao.updateRegisterofficemaster(registerofficesForm,roleId,funId,userId);
	}

	/**
	 * Gets the list of getRegisterofficeId
	 * 
	 * @param RegisterofficesDTO
	 *            of getRegisterofficeId
	 * @return RegisterofficesDTO
	 * @throws Exception
	 */
	public RegisterofficesDTO getRegisterofficeId(String officeid)
			throws Exception {
		return officedao.getRegisterofficeId(officeid);
	}

	/**
	 * Gets the list of DistrictList
	 * 
	 * @param ArrayList
	 *            of DistrictList
	 * @return ArrayList of RegisterofficesDTO
	 * @throws Exception
	 */
	public ArrayList getDistrictList() throws Exception {
		return officedao.getDistrictList();
	}

	/**
	 * Gets the list of TehsilList
	 * 
	 * @param ArrayList
	 *            of TehsilList
	 * @return ArrayList of RegisterofficesDTO
	 * @throws Exception
	 */
	public ArrayList getTehsilList() throws Exception {
		return officedao.getTehsilList();
	}

	/**
	 * Gets the list of WardList
	 * 
	 * @param ArrayList
	 *            of WardList
	 * @return ArrayList of RegisterofficesDTO
	 * @throws Exception
	 */
	public ArrayList getWardList() throws Exception {
		return officedao.getWardList();
	}

	/**
	 * Gets the list of TypeList
	 * 
	 * @param ArrayList
	 *            of TypeList
	 * @return ArrayList of RegisterofficesDTO
	 * @throws Exception
	 */
	public ArrayList getTypeList() throws Exception {
		return officedao.getTypeList();
	}

}
