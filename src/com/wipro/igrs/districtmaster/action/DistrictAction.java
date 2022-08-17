/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictAction.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 01/03/2008
 */
package com.wipro.igrs.districtmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.districtmaster.bd.DistrictBD;
import com.wipro.igrs.districtmaster.dto.DistrictDTO;
import com.wipro.igrs.districtmaster.form.DistrictForm;

public class DistrictAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
		DistrictForm districtForm = (DistrictForm)form;
		DistrictBD districtbd = new DistrictBD();
		DistrictDTO dto = new DistrictDTO();
		if(districtForm.getDistpageName() != null)
		if(districtForm.getDistpageName().equalsIgnoreCase("distcreate"))
		{
		districtbd.adddistrictmaster(districtForm);
		}
		else
		{
		districtbd.updatedistrictmaster(districtForm);
		}
		String forward = "success";
		ArrayList  districtList = districtbd.getDistrictList();
		dto.setDistrictList(districtList);
		String distid = request.getParameter("districtId");
		if(distid!=null)
		{
			dto = districtbd.getDistrictId(distid);
		}
		districtForm.setDto(dto);
		 
		session.setAttribute("districtList", districtForm.getDto().getDistrictList());
		session.setAttribute("DistrictForm", districtForm);
		return mapping.findForward(forward);	
	}

}
