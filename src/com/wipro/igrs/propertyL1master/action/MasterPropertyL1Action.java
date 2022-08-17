/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL1Action.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 26/02/2008
 */
package com.wipro.igrs.propertyL1master.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertyL1master.bd.MasterPropertyL1BD;
import com.wipro.igrs.propertyL1master.dto.PropertyL1DTO;
import com.wipro.igrs.propertyL1master.form.MasterPropertyL1Form;

public class MasterPropertyL1Action extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		MasterPropertyL1Form masterl1PropertyForm = (MasterPropertyL1Form)form;
		MasterPropertyL1BD masterl1PropertyBD = new MasterPropertyL1BD();
		PropertyL1DTO dto = new PropertyL1DTO();
		//HttpSession session = request.getSession();
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		if(masterl1PropertyForm.getPageNameL1()!=null)
		if(masterl1PropertyForm.getPageNameL1().equalsIgnoreCase("createL1"))
		{
			masterl1PropertyBD.addpropertyL1master(masterl1PropertyForm,roleId,funId,userId);	
		}
		else
		{
			masterl1PropertyBD.updatepropertyL1master(masterl1PropertyForm,roleId,funId,userId);
		}
		ArrayList propertyidList = masterl1PropertyBD.getPropertyidList();
        ArrayList propertyL1List =masterl1PropertyBD.getPropertyL1List();
        dto.setPropertyidList(propertyidList);
        dto.setPropertyL1List(propertyL1List);
        String L1pid = request.getParameter("propertyL1id");
        if(L1pid!=null)
		{
			dto=masterl1PropertyBD.getL1property(L1pid);
		}
        masterl1PropertyForm.setDto(dto);
		String forward = "success";
	//	session = request.getSession();
		session.setAttribute("propertyL1List",masterl1PropertyForm.getDto().getPropertyL1List());
		session.setAttribute("MasterPropertyL1Form", masterl1PropertyForm);
		return mapping.findForward(forward);
	}
}
