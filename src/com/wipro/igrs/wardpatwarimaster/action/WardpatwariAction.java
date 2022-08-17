/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariAction.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.wardpatwarimaster.bd.WardpatwariBD;
import com.wipro.igrs.wardpatwarimaster.dto.WardpatwariDTO;
import com.wipro.igrs.wardpatwarimaster.form.WardpatwariForm;

public class WardpatwariAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
		WardpatwariForm wardpatwariForm = (WardpatwariForm)form;
		WardpatwariBD wardpatwariBD = new WardpatwariBD();
		WardpatwariDTO dto = new WardpatwariDTO();
		if(wardpatwariForm.getWardpageName()!=null)
		if(wardpatwariForm.getWardpageName().equalsIgnoreCase("wardcreate"))
		{
		wardpatwariBD.addwardpatwarimaster(wardpatwariForm);
		}
		else
		{
			wardpatwariBD.updatepatwarimaster(wardpatwariForm);
		}
		ArrayList tehsilidList = wardpatwariBD.getTehsilidList();
		ArrayList wardpatwariList = wardpatwariBD.getWardpatwariList();
		dto.setTehsilidList(tehsilidList);
		dto.setWardpatwariList(wardpatwariList);
		String wardid = request.getParameter("wardpatwariId");
		if(wardid!=null)
		{
			dto = wardpatwariBD.getWardpatwaritId(wardid);
		}
		wardpatwariForm.setDto(dto);
		String forward = "success";
//		HttpSession session = request.getSession();
		session.setAttribute("wardpatwariList",wardpatwariForm.getDto().getWardpatwariList());
		session.setAttribute("WardpatwariForm", wardpatwariForm);
		return mapping.findForward(forward);	
    }
}
