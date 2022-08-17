/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MasterPropertyAction.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.propertytypemaster.bd.MasterPropertyBD;
import com.wipro.igrs.propertytypemaster.dto.PropertyDTO;
import com.wipro.igrs.propertytypemaster.form.MasterPropertyForm;

public class MasterPropertyAction extends BaseAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response,HttpSession session) throws Exception {
		MasterPropertyForm masterPropertyForm = (MasterPropertyForm)form;
		MasterPropertyBD masterPropertyBD = new MasterPropertyBD();
		PropertyDTO dto = new PropertyDTO();
	//	HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		if(masterPropertyForm.getProppageName()!=null)
		if(masterPropertyForm.getProppageName().equalsIgnoreCase("propcreate")){
		masterPropertyBD.addpropertymaster(masterPropertyForm,roleId,funId,userId);
		}else{
			
			masterPropertyBD.updatepropertymaster(masterPropertyForm,roleId,funId,userId);			
		}
		ArrayList propertyList =masterPropertyBD.getPropertyList();
		dto.setPropertyList(propertyList);
		String pid=request.getParameter("propertyid");
		if(pid!=null)
		{
			dto = masterPropertyBD.getProperty(pid);
		}
		masterPropertyForm.setDto(dto);
		String forward = "success";
	//    session = request.getSession();
		session.setAttribute("propertyList",masterPropertyForm.getDto().getPropertyList());
		session.setAttribute("MasterPropertyForm", masterPropertyForm);
		return mapping.findForward(forward);
	}

}
