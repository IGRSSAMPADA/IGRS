/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PhotoIdMasterBO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.PhotoIdMasterDAO;
import com.wipro.igrs.adminConfig.dto.PhotoIdMasterDTO;

public class PhotoIdMasterBO {
	// method for adding master data
	public boolean addPhotoIdMaster(String p[], String roleId, String funId, String userId) throws Exception {
		PhotoIdMasterDAO mohalladao = new PhotoIdMasterDAO();
		return mohalladao.addPhotoIdMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updatePhotoIdmaster(String p[], String roleId, String funId, String userId) throws Exception {
		PhotoIdMasterDAO mohalladao = new PhotoIdMasterDAO();
		return mohalladao.updatePhotoIdmaster(p,roleId,funId,userId);

	}

	// method for getting Party list
	public ArrayList getPhotoIdList() throws Exception {
		PhotoIdMasterDAO mohalladao = new PhotoIdMasterDAO();
		return mohalladao.getPhotoIdList();
	}

	// method for getting list of party type master based on id
	public ArrayList getPhotoIdIdList(String mohallaid)
			throws Exception {
		PhotoIdMasterDAO mohalladao = new PhotoIdMasterDAO();
		return mohalladao.getPhotoIdIdList(mohallaid);
	}
}
