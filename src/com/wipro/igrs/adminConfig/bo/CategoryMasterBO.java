/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CategoryMasterBO.java
 * Author		:	vengamamba
 * Date			: 	20/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.CategoryMasterDAO;
import com.wipro.igrs.adminConfig.dto.CategoryMasterDTO;

public class CategoryMasterBO {
	// method for adding master data
	public boolean addCategoryMaster(String p[], String roleId, String funId, String userId) throws Exception {
		CategoryMasterDAO mohalladao = new CategoryMasterDAO();
		return mohalladao.addCategoryMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updateCategorymaster(String p[], String roleId, String funId, String userId) throws Exception {
		CategoryMasterDAO mohalladao = new CategoryMasterDAO();
		return mohalladao.updateCategorymaster(p,roleId,funId,userId);

	}

	// method for getting Party list
	public ArrayList getCategoryList() throws Exception {
		CategoryMasterDAO mohalladao = new CategoryMasterDAO();
		return mohalladao.getCategoryList();
	}

	// method for getting list of party type master based on id
	public ArrayList getCategoryIdList(String mohallaid)
			throws Exception {
		CategoryMasterDAO mohalladao = new CategoryMasterDAO();
		return mohalladao.getCategoryIdList(mohallaid);
	}
}
