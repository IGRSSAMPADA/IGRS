/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictBD.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 01/03/2008
 */
package com.wipro.igrs.districtmaster.bd;

import java.util.ArrayList;

import com.wipro.igrs.districtmaster.dao.DistrictDAO;
import com.wipro.igrs.districtmaster.form.DistrictForm;
import com.wipro.igrs.districtmaster.dto.DistrictDTO;
public class DistrictBD {
	public DistrictBD()
	{
	}
	DistrictDAO distdao = new DistrictDAO();
	public void adddistrictmaster(DistrictForm districtForm) throws Exception
	{
		distdao.adddistrictmaster(districtForm);
	}
	public void updatedistrictmaster(DistrictForm districtForm) throws Exception
	{
		distdao.updatedistrictmaster(districtForm);
	}
	public DistrictDTO getDistrictId(String distid) throws Exception
	{
		return distdao.getDistrictId(distid);
	}
	public ArrayList getDistrictList() throws Exception
	{
		return distdao.getDistrictList();
	}
}
