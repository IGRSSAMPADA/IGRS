/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CategoryMasterBD.java
 * Author		:	vengamamba
 * Date			: 	20/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.CategoryMasterBO;
import com.wipro.igrs.adminConfig.dto.CategoryMasterDTO;

public class CategoryMasterBD {
	CategoryMasterBO partybo = new CategoryMasterBO();

	// method for adding master data
	public boolean addCategoryMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = partybo.addCategoryMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateCategorymaster(String p[], String roleId, String funId, String userId) throws Exception {
		return partybo.updateCategorymaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getCategoryList() throws Exception {
		ArrayList plist = partybo.getCategoryList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				CategoryMasterDTO dto = new CategoryMasterDTO();
				dto.setCategoryId((String) lst.get(0));
				dto.setCategoryName((String) lst.get(1));
				dto.setCategoryStatus((String) lst.get(2));
				// dto.setMohallaType((String)lst.get(4));
				list.add(dto);
			}
		}

		return list;

	}

	// method for getting list of party type master based on id
	public CategoryMasterDTO getCategoryIdList(String partyid) throws Exception {
		ArrayList plist = partybo.getCategoryIdList(partyid);
		ArrayList list1 = (ArrayList) plist.get(0);
		CategoryMasterDTO dto = new CategoryMasterDTO();
		dto.setCategoryName(list1.get(1).toString());

		dto.setCategoryStatus(list1.get(2).toString());
		dto.setCategoryId(list1.get(0).toString());
		return dto;
	}
}
