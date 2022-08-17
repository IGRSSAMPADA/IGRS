/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceParamMapBO.java
 * Author		:	vengamamba
 * Date			: 	23/06/2008
 */
package com.wipro.igrs.adminConfig.bo;

import java.util.ArrayList;

import com.wipro.igrs.adminConfig.dao.ServiceParamMapDAO;

public class ServiceParamMapBO {
	public boolean addParamMapping(String p[], String roleId, String funId, String userId) throws Exception {
		ServiceParamMapDAO mapdao = new ServiceParamMapDAO();
		return mapdao.addParamMapping(p,roleId,funId,userId);
	}

	public ArrayList getFuncIdList() throws Exception {
		ServiceParamMapDAO mohalladao = new ServiceParamMapDAO();
		return mohalladao.getFuncIdList();
	}

	public ArrayList getServiceIdList() throws Exception {
		ServiceParamMapDAO mohalladao = new ServiceParamMapDAO();
		return mohalladao.getServiceIdList();
	}

	public ArrayList getServiceParamIdList() throws Exception {
		ServiceParamMapDAO mohalladao = new ServiceParamMapDAO();
		return mohalladao.getServiceParamIdList();
	}

	public ArrayList getOperatorIdList() throws Exception {
		ServiceParamMapDAO mohalladao = new ServiceParamMapDAO();
		return mohalladao.getOperatorIdList();
	}

}
