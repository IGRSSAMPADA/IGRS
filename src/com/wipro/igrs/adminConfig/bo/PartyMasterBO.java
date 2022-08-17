/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PartyMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.PartyMasterDAO;
import com.wipro.igrs.adminConfig.dto.PartyMasterDTO;

public class PartyMasterBO {
	// method for adding master data
	public boolean addPartyMaster(String p[], String roleId, String funId, String userId) throws Exception {
		PartyMasterDAO mohalladao = new PartyMasterDAO();
		return mohalladao.addPartyMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updatePartymaster(String p[], String roleId, String funId, String userId) throws Exception {
		PartyMasterDAO mohalladao = new PartyMasterDAO();
		return mohalladao.updatePartymaster(p,roleId,funId,userId);

	}

	// method for getting Party list
	public ArrayList getPartyList() throws Exception {
		PartyMasterDAO mohalladao = new PartyMasterDAO();
		return mohalladao.getPartyList();
	}

	// method for getting list of party type master based on id
	public ArrayList getPartyIdList(String mohallaid)
			throws Exception {
		PartyMasterDAO mohalladao = new PartyMasterDAO();
		return mohalladao.getPartyIdList(mohallaid);
	}
}
