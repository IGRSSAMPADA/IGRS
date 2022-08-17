/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceFuncMapBO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */

package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.ServiceFuncMapDAO;

public class ServiceFuncMapBO {

	public boolean addFuncMapping(String p[], String roleId, String funId, String userId) throws Exception {
		ServiceFuncMapDAO mapdao = new ServiceFuncMapDAO();
		return mapdao.addFuncMapping(p,roleId,funId,userId);
	}

	public ArrayList getFuncIdList() throws Exception {
		ServiceFuncMapDAO mohalladao = new ServiceFuncMapDAO();
		return mohalladao.getFuncIdList();
	}

	public ArrayList getServiceIdList() throws Exception {
		ServiceFuncMapDAO mohalladao = new ServiceFuncMapDAO();
		return mohalladao.getServiceIdList();
	}
}
