/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilAction.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.tehsilmaster.bd.TehsilBD;
import com.wipro.igrs.tehsilmaster.dto.TehsilDTO;
import com.wipro.igrs.tehsilmaster.form.TehsilForm;

public class TehsilAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
		TehsilForm tehsilForm = (TehsilForm)form;
		TehsilBD tehsilbd = new TehsilBD();
		TehsilDTO dto = new TehsilDTO();
		if(tehsilForm.getTehsilpageName()!=null)
		if(tehsilForm.getTehsilpageName().equalsIgnoreCase("tehsilcreate"))
		{
		tehsilbd.addtehsilmaster(tehsilForm);
		}
		else
		{
		tehsilbd.updatetehsilmaster(tehsilForm);
		}
		ArrayList districtidList = tehsilbd.getDistrictidList();
		ArrayList tehsilList = tehsilbd.getTehsilList();
		dto.setTehsilList(tehsilList);
		dto.setDistrictidList(districtidList);
		String tehsilid = request.getParameter("tehsilId");
		if(tehsilid!=null)
		{
			dto = tehsilbd.getTehsilId(tehsilid);
		}
		tehsilForm.setDto(dto);
		String forward = "success";
		//HttpSession session = request.getSession();
		session.setAttribute("tehsilList",tehsilForm.getDto().getTehsilList());
		session.setAttribute("TehsilForm", tehsilForm);
		return mapping.findForward(forward);	
    }
}
