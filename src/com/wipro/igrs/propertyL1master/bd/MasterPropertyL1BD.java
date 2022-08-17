/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL1BD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	26/02/2008
 */
package com.wipro.igrs.propertyL1master.bd;

import java.util.ArrayList;

import com.wipro.igrs.propertyL1master.dao.MasterPropertyL1DAO;
import com.wipro.igrs.propertyL1master.dto.PropertyL1DTO;
import com.wipro.igrs.propertyL1master.form.MasterPropertyL1Form;

public class MasterPropertyL1BD {
	MasterPropertyL1DAO ml1dao=null;
	public MasterPropertyL1BD()
	{
		ml1dao=new MasterPropertyL1DAO();
	}
	public ArrayList getPropertyidList() throws Exception{
	    return ml1dao.getPropertyidList();
	    }
	public ArrayList getPropertyL1List() throws Exception{
		return ml1dao.getpropertyL1List();
	}
	public PropertyL1DTO getL1property(String L1pid) throws Exception
    {
    	return ml1dao.getL1property(L1pid);
    }
public void addpropertyL1master(MasterPropertyL1Form masterl1PropertyForm, String roleId, String funId, String userId)
    {
	ml1dao.addpropertyL1master(masterl1PropertyForm,roleId,funId,userId);
	}
public void updatepropertyL1master(MasterPropertyL1Form masterl1PropertyForm, String roleId, String funId, String userId)
{
	ml1dao.updatepropertyL1master(masterl1PropertyForm,roleId,funId,userId);
}
}
