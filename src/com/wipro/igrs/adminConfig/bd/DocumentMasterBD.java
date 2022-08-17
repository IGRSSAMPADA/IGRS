/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DocumentMasterBD.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.wipro.igrs.adminConfig.bo.DocumentMasterBO;
import com.wipro.igrs.adminConfig.dto.DocumentMasterDTO;

public class DocumentMasterBD {
	DocumentMasterBO docbo = new DocumentMasterBO();
	
	

	// method for adding master data
	public boolean addDocumentMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = docbo.addDocumentMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateDocumentmaster(String p[], String roleId, String funId, String userId) throws Exception {
		return docbo.updateDocumentmaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getDocumentList() throws Exception {
		ArrayList plist = docbo.getDocumentList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				DocumentMasterDTO dto = new DocumentMasterDTO();
				dto.setDocumentId((String) lst.get(0));
				dto.setDocumentName((String) lst.get(1));
				dto.setDocumentDesc((String) lst.get(2));
				dto.setDocumentStatus((String) lst.get(3));
				// dto.setMohallaType((String)lst.get(4));
				list.add(dto);
			}
		}

		return list;

	}

	// method for getting list of party type master based on id
	public DocumentMasterDTO getDocumentIdList(String docid) throws Exception {
		ArrayList plist = docbo.getDocumentIdList(docid);
		ArrayList list1 = (ArrayList) plist.get(0);
		DocumentMasterDTO dto = new DocumentMasterDTO();
		dto.setDocumentName(list1.get(1).toString());
		String desc = "";

		if (list1.get(2) != null) {
			desc = list1.get(2).toString();
		}
		dto.setDocumentDesc(desc);
		dto.setDocumentStatus(list1.get(3).toString());
		dto.setDocumentId(list1.get(0).toString());
		return dto;
	}
}
