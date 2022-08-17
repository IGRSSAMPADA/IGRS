/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DocumentMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.DocumentMasterDAO;
import com.wipro.igrs.adminConfig.dto.DocumentMasterDTO;

public class DocumentMasterBO {
	// method for adding master data
	public boolean addDocumentMaster(String p[], String roleId, String funId, String userId) throws Exception {
		DocumentMasterDAO docdao = new DocumentMasterDAO();
		return docdao.addDocumentMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updateDocumentmaster(String p[], String roleId, String funId, String userId) throws Exception {
		DocumentMasterDAO docdao = new DocumentMasterDAO();
		return docdao.updateDocumentmaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getDocumentList() throws Exception {
		DocumentMasterDAO docdao = new DocumentMasterDAO();
		return docdao.getDocumentList();
	}

	// method for getting list of party type master based on id
	public ArrayList getDocumentIdList(String mohallaid)
			throws Exception {
		DocumentMasterDAO docdao = new DocumentMasterDAO();
		return docdao.getDocumentIdList(mohallaid);
	}
}
