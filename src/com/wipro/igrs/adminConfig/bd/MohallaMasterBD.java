/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MohallaMasterBD.java
 * Author		:	vengamamba
 * Date			: 	17/06/2008
 */
package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.MohallaMasterBO;
import com.wipro.igrs.adminConfig.dto.MohallaMasterDTO;

public class MohallaMasterBD {

	MohallaMasterBO mohallabo = new MohallaMasterBO();

	// method for adding master data
	public boolean addMohallaMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = mohallabo.addMohallaMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateMohallamaster(String p[], String roleId, String funId, String userId) throws Exception {
		return mohallabo.updateMohallamaster(p,roleId,funId,userId);
	}

	// method fro getting mohalla list
	public ArrayList getMohallaList() throws Exception {
		ArrayList plist = mohallabo.getMohallaList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				MohallaMasterDTO dto = new MohallaMasterDTO();
				dto.setMohallaId((String) lst.get(0));
				dto.setMohallaName((String) lst.get(1));
				dto.setMohallaDesc((String) lst.get(2));
				dto.setMohallaStatus((String) lst.get(3));
				dto.setMohallaType((String) lst.get(4));
				list.add(dto);
			}
		}

		return list;

	}

	// method for id,name list of patwarimaster
	public ArrayList getWardpatwariList() throws Exception {

		ArrayList plist = mohallabo.getWardpatwariList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				MohallaMasterDTO dto = new MohallaMasterDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	// method for getting list of mohalla master based on id
	public MohallaMasterDTO getMohallaIdList(String mohallaid) throws Exception {
		ArrayList plist = mohallabo.getMohallaIdList(mohallaid);
		ArrayList list1 = (ArrayList) plist.get(0);
		MohallaMasterDTO dto = new MohallaMasterDTO();
		dto.setMohallaName(list1.get(1).toString());
		String desc = "";

		if (list1.get(2) != null) {
			desc = list1.get(2).toString();
		}

		dto.setMohallaDesc(desc);
		dto.setMohallaStatus(list1.get(3).toString());
		dto.setMohallaId(list1.get(0).toString());
		return dto;
	}
}
