/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyL2Action.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertyL2master.bd.MasterPropertyL2BD;
import com.wipro.igrs.propertyL2master.dto.PropertyL2DTO;
import com.wipro.igrs.propertyL2master.form.MasterPropertyL2Form;

public class MasterPropertyL2Action extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		MasterPropertyL2Form masterl2PropertyForm = (MasterPropertyL2Form)form;
		MasterPropertyL2BD masterl2PropertyBD = new MasterPropertyL2BD();
		PropertyL2DTO dto = new PropertyL2DTO();
	//	HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		if(masterl2PropertyForm.getPageNameL2()!=null)
		if(masterl2PropertyForm.getPageNameL2().equalsIgnoreCase("createL2"))
		{
		masterl2PropertyBD.addpropertyL2master(masterl2PropertyForm,roleId,funId,userId);
		}
		else
		{
		masterl2PropertyBD.updatepropertyL2master(masterl2PropertyForm,roleId,funId,userId);
		}
		ArrayList propertyL1idList = masterl2PropertyBD.getPropertyL1idList();
        ArrayList propertyL2List = masterl2PropertyBD.getPropertyL2List();
        dto.setPropertyL1idList(propertyL1idList);
        dto.setPropertyL2List(propertyL2List);
        String L2pid = request.getParameter("propertyL2id");
        if(L2pid!=null)
        {
        	dto = masterl2PropertyBD.getL2property(L2pid);
        }
        masterl2PropertyForm.setDto(dto);
		String forward = "success";
	//	session = request.getSession();
		session.setAttribute("propertyL2List",masterl2PropertyForm.getDto().getPropertyL2List());
		session.setAttribute("MasterPropertyL2Form", masterl2PropertyForm);
		return mapping.findForward(forward);
	}
}
