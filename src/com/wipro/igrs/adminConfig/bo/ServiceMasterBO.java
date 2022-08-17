/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceMasterBO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.ServiceMasterDAO;
import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;

public class ServiceMasterBO {
	// method for adding master data
	public boolean addServiceMaster(String p[], String roleId, String funId, String userId) throws Exception {
		ServiceMasterDAO mohalladao = new ServiceMasterDAO();
		return mohalladao.addServiceMaster(p,roleId,funId,userId);
	}

	// method for updating master dat
	public boolean updateServicemaster(String p[], String roleId, String funId, String userId) throws Exception {
		ServiceMasterDAO mohalladao = new ServiceMasterDAO();
		return mohalladao.updateServicemaster(p,roleId,funId,userId);

	}

	// method for getting Party list
	public ArrayList getServiceList() throws Exception {
		ServiceMasterDAO mohalladao = new ServiceMasterDAO();
		return mohalladao.getServiceList();
	}

	// method for getting list of party type master based on id
	public ArrayList getServiceIdList(String mohallaid)
			throws Exception {
		ServiceMasterDAO mohalladao = new ServiceMasterDAO();
		return mohalladao.getServiceIdList(mohallaid);
	}
	// method for deleting service master data
	public boolean deleteService(String serviceId, String roleId, String funId, String userId) throws Exception {
		ServiceMasterDAO mohalladao = new ServiceMasterDAO();
		return mohalladao.deleteService(serviceId,roleId,funId,userId);
	}

}
