/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CasteMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.CasteMasterDAO;
import com.wipro.igrs.adminConfig.dto.CasteMasterDTO;

public class CasteMasterBO {

	public boolean addCasteMaster(String p[], String roleId, String funId, String userId) throws Exception {
		CasteMasterDAO castedao = new CasteMasterDAO();
		return castedao.addCasteMaster(p,roleId,funId,userId);
	}

	public boolean updateCastemaster(String p[], String roleId, String funId, String userId) throws Exception {
		CasteMasterDAO castedao = new CasteMasterDAO();
		return castedao.updateCastemaster(p,roleId,funId,userId);

	}

	public ArrayList getCasteList() throws Exception {
		CasteMasterDAO castedao = new CasteMasterDAO();
		return castedao.getCasteList();
	}

	public ArrayList getCategoryList() throws Exception {
		CasteMasterDAO castedao = new CasteMasterDAO();
		return castedao.getCategoryList();
	}

	public ArrayList getCasteIdList(String casteid)
			throws Exception {
		CasteMasterDAO castedao = new CasteMasterDAO();
		return castedao.getCasteIdList(casteid);
	}
}
