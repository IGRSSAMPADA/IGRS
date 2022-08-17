/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PhotoIdMasterBD.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.PhotoIdMasterBO;
import com.wipro.igrs.adminConfig.dto.PhotoIdMasterDTO;

public class PhotoIdMasterBD {
	PhotoIdMasterBO partybo = new PhotoIdMasterBO();

	
	// method for adding master data
	public boolean addPhotoIdMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = partybo.addPhotoIdMaster(param,roleId,funId,userId);
		return flag;
	}
	// method for updating master data
	public boolean updatePhotoIdmaster(String p[], String roleId, String funId, String userId) throws Exception {
		return partybo.updatePhotoIdmaster(p,roleId,funId,userId);
	}
	// method for getting Party list
	public ArrayList getPhotoIdList() throws Exception {
		ArrayList plist = partybo.getPhotoIdList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				PhotoIdMasterDTO dto = new PhotoIdMasterDTO();
				dto.setPhotoId((String) lst.get(0));
				dto.setPhotoIdName((String) lst.get(1));
				dto.setPhotoIdStatus((String) lst.get(2));
				list.add(dto);
			}
		}
		return list;
	}
	// method for getting list of party type master based on id
	public PhotoIdMasterDTO getPhotoIdIdList(String partyid) throws Exception {
		ArrayList plist = partybo.getPhotoIdIdList(partyid);
		ArrayList list1 = (ArrayList) plist.get(0);
		PhotoIdMasterDTO dto = new PhotoIdMasterDTO();
		dto.setPhotoIdName(list1.get(1).toString());
		dto.setPhotoIdStatus(list1.get(2).toString());
		dto.setPhotoId(list1.get(0).toString());
		return dto;
	}
}
