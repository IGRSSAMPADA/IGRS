/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.mohallavillagemaster.dao.MohallavillageDAO;
import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;
import com.wipro.igrs.mohallavillagemaster.form.MohallavillageForm;

public class MohallavillageBD {

	public MohallavillageBD() {

	}

	MohallavillageDAO mohalladao = new MohallavillageDAO();

	/**
	 * Add the list of Mohallavilagemaster
	 * 
	 * @throws Exception
	 */

	public void addMohallavilagemaster(MohallavillageForm mohallavillageForm)
			throws Exception {
		mohalladao.addMohallavilagemaster(mohallavillageForm);
	}

	/**
	 * Update the list of Mohallavilagemaster
	 * 
	 * @throws Exception
	 */
	public void updateMohallavilagemaster(MohallavillageForm mohallavillageForm)
			throws Exception {
		mohalladao.updateMohallavilagemaster(mohallavillageForm);
	}

	/**
	 * Gets the list of getRegisterofficeId
	 * 
	 * @param MohallavillageDTO
	 *            of getMohallavillageId
	 * @return MohallavillageDTO
	 * @throws Exception
	 */
	public MohallavillageDTO getMohallavillageId(String mohallaid)
			throws Exception {
		return mohalladao.getMohallavillageId(mohallaid);
	}

	/**
	 * Gets the list of MohallavillageList
	 * 
	 * @param ArrayList
	 *            of getMohallavillageList
	 * @return ArrayList of MohallavillageDTO
	 * @throws Exception
	 */
	public ArrayList getMohallavillageList() throws Exception {
		return mohalladao.getMohallavillageList();
	}

	/**
	 * Gets the list of WardpatwariList
	 * 
	 * @param ArrayList
	 *            of getWardpatwariList
	 * @return ArrayList of MohallavillageDTO
	 * @throws Exception
	 */
	public ArrayList getWardpatwariList() throws Exception {
		return mohalladao.getWardpatwariList();
	}
}
