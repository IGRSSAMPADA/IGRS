/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL2BD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.bd;

import java.util.ArrayList;

import com.wipro.igrs.propertyL2master.dto.PropertyL2DTO;
import com.wipro.igrs.propertyL2master.dao.MasterPropertyL2DAO;
import com.wipro.igrs.propertyL2master.form.MasterPropertyL2Form;

public class MasterPropertyL2BD {
	MasterPropertyL2DAO ml2dao=null;
	public MasterPropertyL2BD()
	{
		ml2dao=new MasterPropertyL2DAO();
	}
	public ArrayList getPropertyL1idList() throws Exception{
	    return ml2dao.getPropertyL1idList();
	    }
	public ArrayList getPropertyL2List() throws Exception
	{
		return ml2dao.getPropertyL2List();
	}
	public PropertyL2DTO getL2property(String L2pid)throws Exception
	{
		return ml2dao.getL2property(L2pid);
	}
	public void addpropertyL2master(MasterPropertyL2Form masterl2PropertyForm, String roleId, String funId, String userId)
    {
	ml2dao.addpropertyL2master(masterl2PropertyForm,roleId,funId,userId);
	}
	public void updatepropertyL2master(MasterPropertyL2Form masterl2PropertyForm, String roleId, String funId, String userId)
	{
	ml2dao.updatepropertyL2master(masterl2PropertyForm,roleId,funId,userId);
	}
}
