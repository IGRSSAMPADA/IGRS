/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementBD.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the BD Class for Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.sm.bd;

import com.wipro.igrs.sm.dao.StatusmanagementDAO;
import com.wipro.igrs.sm.dto.StatusmanagementDTO;
import com.wipro.igrs.sm.form.StatusmanagementForm;

public class StatusmanagementBD {

	public StatusmanagementBD() {
	}

	StatusmanagementDAO smdao = new StatusmanagementDAO();

	/**
	 * Gets the list of EmpList
	 * 
	 * @param eno
	 * @return ArrayList of AclcreateDTO
	 * @throws Exception
	 */

	public StatusmanagementDTO getEmpList(String eno) throws Exception {
		return smdao.getEmpList(eno);
	}

	/**
	 * Gets the list of StatusList
	 * 
	 * @return ArrayList of AclcreateDTO
	 * @throws Exception
	 */
	/*
	 * public ArrayList<StatusmanagementDTO> getStatusList() throws Exception {
	 * return smdao.getStatusList(); }
	 */
	/**
	 * update Employee
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateemp(StatusmanagementForm statusmanagementForm)
			throws Exception {
		return smdao.updateemp(statusmanagementForm);
	}
}
