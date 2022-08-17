/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceParamMapBD.java
 * Author		:	vengamamba
 * Date			: 	23/06/2008
 */
package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.ServiceParamMapBO;
import com.wipro.igrs.adminConfig.dto.ServiceParamMapDTO;

public class ServiceParamMapBD {
	ServiceParamMapBO mapbo = new ServiceParamMapBO();

	// method for adding master data
	public boolean addParamMapping(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = mapbo.addParamMapping(param,roleId,funId,userId);
		return flag;
	}

	// method for id,name list of function master
	public ArrayList getFuncIdList() throws Exception {

		ArrayList plist = mapbo.getFuncIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceParamMapDTO dto = new ServiceParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}
		return list;
	}

	// method for id,name list of service master
	public ArrayList getServiceIdList() throws Exception {

		ArrayList plist = mapbo.getServiceIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceParamMapDTO dto = new ServiceParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	// method for id,name list of service Param master
	public ArrayList getServiceParamIdList()
			throws Exception {

		ArrayList plist = mapbo.getServiceParamIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceParamMapDTO dto = new ServiceParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}

		return list;
	}

	// method for id,name list of service master
	public ArrayList getOperatorIdList() throws Exception {

		ArrayList plist = mapbo.getOperatorIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceParamMapDTO dto = new ServiceParamMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));

				list.add(dto);
			}
		}
		return list;
	}
}
