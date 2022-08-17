/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceMasterBD.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.bd;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.bo.ServiceMasterBO;
import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;

public class ServiceMasterBD {
	ServiceMasterBO partybo = new ServiceMasterBO();

	// method for adding master data
	public boolean addServiceMaster(String[] param, String roleId, String funId, String userId) throws Exception {
		boolean flag = partybo.addServiceMaster(param,roleId,funId,userId);
		return flag;
	}

	// method for updating master data
	public boolean updateServicemaster(String p[], String roleId, String funId, String userId) throws Exception {
		return partybo.updateServicemaster(p,roleId,funId,userId);
	}

	// method for getting Party list
	public ArrayList getServiceList() throws Exception {
		ArrayList plist = partybo.getServiceList();
		ArrayList list = new ArrayList();
		if (plist != null) {
			for (int i = 0; i < plist.size(); i++) {
				ArrayList lst = (ArrayList) plist.get(i);
				ServiceMasterDTO dto = new ServiceMasterDTO();
				dto.setServiceId((String) lst.get(0));
				dto.setServiceName((String) lst.get(1));
				dto.setServiceDesc((String) lst.get(2));
				dto.setServiceStatus((String) lst.get(3));
				list.add(dto);
			}
		}

		return list;

	}

	// method for getting list of party type master based on id
	public ServiceMasterDTO getServiceIdList(String partyid) throws Exception {
		ArrayList plist = partybo.getServiceIdList(partyid);
		ArrayList list1 = (ArrayList) plist.get(0);
		ServiceMasterDTO dto = new ServiceMasterDTO();
		dto.setServiceName(list1.get(1).toString());
		String desc = "";

		if (list1.get(2) != null) {
			desc = list1.get(2).toString();
		}
		dto.setServiceDesc(desc);
		dto.setServiceStatus(list1.get(3).toString());
		dto.setServiceId(list1.get(0).toString());
		return dto;
	}
	
	// method for deleting service master data
	public boolean deleteService(String serviceId, String roleId, String funId, String userId) throws Exception {
		return partybo.deleteService(serviceId,roleId,funId,userId);
	}

}
