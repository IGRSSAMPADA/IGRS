/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariBD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.wardpatwarimaster.dao.WardpatwariDAO;
import com.wipro.igrs.wardpatwarimaster.dto.WardpatwariDTO;
import com.wipro.igrs.wardpatwarimaster.form.WardpatwariForm;

public class WardpatwariBD {
	public WardpatwariBD(){
	}
	WardpatwariDAO wardpatwaridao = new WardpatwariDAO();
	public void addwardpatwarimaster(WardpatwariForm wardpatwariForm) throws Exception
	{
		wardpatwaridao.addwardpatwarimaster(wardpatwariForm);
	}
	public void updatepatwarimaster(WardpatwariForm wardpatwariForm) throws Exception
	{
		wardpatwaridao.updatepatwarimaster(wardpatwariForm);
	}
	public ArrayList getTehsilidList() throws Exception{
	    return wardpatwaridao.getTehsilidList();
	    }
	public ArrayList getWardpatwariList() throws Exception{
		return wardpatwaridao.getWardpatwariList();
	}
	public WardpatwariDTO getWardpatwaritId(String wardid) throws Exception
	{
		return wardpatwaridao.getWardpatwariId(wardid);
	}
}
