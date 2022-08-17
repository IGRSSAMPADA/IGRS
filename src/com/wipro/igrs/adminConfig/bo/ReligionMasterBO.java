/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ReligionMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.ReligionMasterDAO;
import com.wipro.igrs.adminConfig.dto.ReligionMasterDTO;

public class ReligionMasterBO {
	// method for adding master data
	public boolean addReligionMaster(String p[], String roleId, String funId, String userId) throws Exception {
		ReligionMasterDAO regdao = new ReligionMasterDAO();
		return regdao.addReligionMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updateReligionmaster(String p[], String roleId, String funId, String userId) throws Exception {
		ReligionMasterDAO regdao = new ReligionMasterDAO();
		return regdao.updateReligionmaster(p,roleId,funId,userId);

	}

	// method for getting Party list
	public ArrayList getReligionList() throws Exception {
		ReligionMasterDAO regdao = new ReligionMasterDAO();
		return regdao.getReligionList();
	}

	// method for getting list of party type master based on id
	public ArrayList getReligionIdList(String regid)
			throws Exception {
		ReligionMasterDAO regdao = new ReligionMasterDAO();
		return regdao.getReligionIdList(regid);
	}
}
