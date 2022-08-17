/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ReligionMasterBD.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.ReligionMasterBO;
import com.wipro.igrs.adminConfig.dto.ReligionMasterDTO;

public class ReligionMasterBD {
	ReligionMasterBO regbo = new ReligionMasterBO();

	// method for adding master data
	public boolean addReligionMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = regbo.addReligionMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateReligionmaster(String p[],String roleId, String funId, String userId) throws Exception {
		return regbo.updateReligionmaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getReligionList() throws Exception {
		ArrayList plist = regbo.getReligionList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ReligionMasterDTO dto = new ReligionMasterDTO();
				dto.setReligionId((String) lst.get(0));
				dto.setReligionName((String) lst.get(1));
				dto.setReligionDesc((String) lst.get(2));
				dto.setReligionStatus((String) lst.get(3));
				list.add(dto);
			}
		}
		return list;
	}

	// method for getting list of party type master based on id
	public ReligionMasterDTO getReligionIdList(String rid) throws Exception {
		ArrayList plist = regbo.getReligionIdList(rid);
		ArrayList list1 = (ArrayList) plist.get(0);
		ReligionMasterDTO dto = new ReligionMasterDTO();
		dto.setReligionName(list1.get(1).toString());
		String desc = "";

		if (list1.get(2) != null) {
			desc = list1.get(2).toString();
		}
		dto.setReligionDesc(desc);
		dto.setReligionStatus(list1.get(3).toString());
		dto.setReligionId(list1.get(0).toString());
		return dto;
	}
}
