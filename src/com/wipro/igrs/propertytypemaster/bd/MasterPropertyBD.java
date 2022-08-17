/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyBD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.bd;
import java.util.ArrayList;

import com.wipro.igrs.propertytypemaster.dao.MasterPropertyDAO;
import com.wipro.igrs.propertytypemaster.dto.PropertyDTO;
import com.wipro.igrs.propertytypemaster.form.MasterPropertyForm;

public class MasterPropertyBD {
	MasterPropertyDAO mdao = new MasterPropertyDAO();
	public MasterPropertyBD()
	{
	}
	
	public ArrayList getPropertyList() throws Exception{
	    return mdao.getPropertyList();
	    }
	public PropertyDTO getProperty(String pid) throws Exception
    {
    	return mdao.getProperty(pid);
    }
	public void addpropertymaster(MasterPropertyForm masterPropertyForm, String roleId, String funId, String userId)
    {
	mdao.addpropertymaster(masterPropertyForm,roleId,funId,userId);
	}
	public void updatepropertymaster(MasterPropertyForm masterPropertyForm, String roleId, String funId, String userId)
{
	mdao.updatepropertymaster(masterPropertyForm,roleId,funId,userId);
}
}
