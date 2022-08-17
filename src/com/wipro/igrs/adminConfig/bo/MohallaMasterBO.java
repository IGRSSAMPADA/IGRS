/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MohallaMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.MohallaMasterDAO;
import com.wipro.igrs.adminConfig.dto.MohallaMasterDTO;

public class MohallaMasterBO {

	public boolean addMohallaMaster(String p[], String roleId, String funId, String userId) throws Exception {
		MohallaMasterDAO mohalladao = new MohallaMasterDAO();
		return mohalladao.addMohallaMaster(p,roleId,funId,userId);
	}

	public boolean updateMohallamaster(String p[], String roleId, String funId, String userId) throws Exception {
		MohallaMasterDAO mohalladao = new MohallaMasterDAO();
		return mohalladao.updateMohallamaster(p,roleId,funId,userId);

	}

	public ArrayList getMohallaList() throws Exception {
		MohallaMasterDAO mohalladao = new MohallaMasterDAO();
		return mohalladao.getMohallaList();
	}

	public ArrayList getWardpatwariList() throws Exception {
		MohallaMasterDAO mohalladao = new MohallaMasterDAO();
		return mohalladao.getWardpatwariList();
	}

	public ArrayList getMohallaIdList(String mohallaid)
			throws Exception {
		MohallaMasterDAO mohalladao = new MohallaMasterDAO();
		return mohalladao.getMohallaIdList(mohallaid);
	}
}
