/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ListRGroupmappingAction.java
 * Author      :   sara hussain
 * Description :   Represents the Action Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 2.0             sara hussain  3th septemper, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.rgroupmappingmaster.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.rgroupmappingmaster.bd.RGroupmappingBD;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;


public class ListRGroupmappingAction extends BaseAction {
	

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		
		RGroupmappingBD bd=new RGroupmappingBD();
		
		ArrayList rgroupmappingList = bd.getRgroupmappingList();
		
		request.setAttribute("rgroupmappingList", rgroupmappingList);
		
		//System.out.println("##data##"+((RGroupmappingDTO)rgroupmappingList.get(0)).getId());
		return mapping.findForward("viewrgroupmappingmaster");
	}
}
