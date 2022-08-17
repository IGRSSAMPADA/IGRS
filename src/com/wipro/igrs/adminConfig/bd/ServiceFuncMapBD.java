/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceFuncMapBD.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.ServiceFuncMapBO;
import com.wipro.igrs.adminConfig.dto.ServiceFuncMapDTO;

public class ServiceFuncMapBD {

	ServiceFuncMapBO mapbo = new ServiceFuncMapBO();

	// method for adding master data
	public boolean addFuncMapping(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = mapbo.addFuncMapping(param,roleId,funId,userId);
		return flag;
	}

	// method for id,name list of function master
	public ArrayList getFuncIdList() throws Exception {

		ArrayList plist = mapbo.getFuncIdList();
		ArrayList list = new ArrayList();

		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceFuncMapDTO dto = new ServiceFuncMapDTO();
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
				ServiceFuncMapDTO dto = new ServiceFuncMapDTO();
				dto.setValue((String) lst.get(0));
				dto.setName((String) lst.get(1));
				list.add(dto);
			}
		}
		return list;
	}

}
