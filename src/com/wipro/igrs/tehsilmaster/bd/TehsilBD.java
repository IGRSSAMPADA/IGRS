/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilBD.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.tehsilmaster.dao.TehsilDAO;
import com.wipro.igrs.tehsilmaster.form.TehsilForm;
import com.wipro.igrs.tehsilmaster.dto.TehsilDTO;

public class TehsilBD {
	public TehsilBD(){
	}
	TehsilDAO tehsildao = new TehsilDAO();
	public void addtehsilmaster(TehsilForm tehsilForm) throws Exception
	{
		tehsildao.addtehsilmaster(tehsilForm);
	}
	public void updatetehsilmaster(TehsilForm tehsilForm) throws Exception
	{
		tehsildao.updatetehsilmaster(tehsilForm);
	}
	public TehsilDTO getTehsilId(String tehsilid) throws Exception
	{
		return tehsildao.getTehsilId(tehsilid);
	}
	public ArrayList getDistrictidList() throws Exception{
	    return tehsildao.getDistrictidList();
	    }
	public ArrayList getTehsilList() throws Exception
	{
		return tehsildao.getTehsilList();
	}
}
