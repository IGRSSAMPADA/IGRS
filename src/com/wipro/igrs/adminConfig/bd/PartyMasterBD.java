/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PartyMasterBD.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.PartyMasterBO;
import com.wipro.igrs.adminConfig.dto.PartyMasterDTO;

public class PartyMasterBD {
	PartyMasterBO partybo = new PartyMasterBO();

	// method for adding master data
	public boolean addPartyMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = partybo.addPartyMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updatePartymaster(String p[], String roleId, String funId, String userId) throws Exception {
		return partybo.updatePartymaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getPartyList() throws Exception {
		ArrayList plist = partybo.getPartyList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				PartyMasterDTO dto = new PartyMasterDTO();
				dto.setPartyId((String) lst.get(0));
				dto.setPartyName((String) lst.get(1));
				dto.setPartyDesc((String) lst.get(2));
				dto.setPartyStatus((String) lst.get(3));
				// dto.setMohallaType((String)lst.get(4));
				list.add(dto);
			}
		}

		return list;

	}

	// method for getting list of party type master based on id
	public PartyMasterDTO getPartyIdList(String partyid) throws Exception {
		ArrayList plist = partybo.getPartyIdList(partyid);
		ArrayList list1 = (ArrayList) plist.get(0);
		PartyMasterDTO dto = new PartyMasterDTO();
		dto.setPartyName(list1.get(1).toString());
		String desc = "";

		if (list1.get(2) != null) {
			desc = list1.get(2).toString();
		}
		dto.setPartyDesc(desc);
		dto.setPartyStatus(list1.get(3).toString());
		dto.setPartyId(list1.get(0).toString());
		return dto;
	}
}
