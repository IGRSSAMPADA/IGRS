/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CasteMasterBD.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */
package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.CasteMasterBO;
import com.wipro.igrs.adminConfig.dto.CasteMasterDTO;

public class CasteMasterBD {

	CasteMasterBO bo = new CasteMasterBO();

	// method for adding master data
	public boolean addCasteMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = bo.addCasteMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateCastemaster(String p[], String roleId, String funId, String userId) throws Exception {
		return bo.updateCastemaster(p,roleId,funId,userId);
	}

	// method fro getting mohalla list
	public ArrayList getCasteList() throws Exception {
		ArrayList plist = bo.getCasteList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				CasteMasterDTO dto = new CasteMasterDTO();
				dto.setCasteId((String) lst.get(0));
				dto.setCasteName((String) lst.get(1));
				dto.setCategory((String) lst.get(2));
				dto.setCasteStatus((String) lst.get(3));

				list.add(dto);
			}
		}

		return list;

	}

	// method for id,name list of CategoryList
	public ArrayList getCategoryList() throws Exception {

		ArrayList plist = bo.getCategoryList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				CasteMasterDTO dto = new CasteMasterDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	// method for getting list of mohalla master based on id
	public CasteMasterDTO getCasteIdList(String cid) throws Exception {
		ArrayList plist = bo.getCasteIdList(cid);
		ArrayList list1 = (ArrayList) plist.get(0);
		CasteMasterDTO dto = new CasteMasterDTO();
		dto.setCasteName(list1.get(1).toString());
		String c = "";
		if (list1.get(2) != null)
			c = list1.get(2).toString();
		dto.setCategory(c);
		dto.setCasteStatus(list1.get(3).toString());
		dto.setCasteId(list1.get(0).toString());
		return dto;
	}
}
