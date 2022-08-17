/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   DeletePartytypeDeedMapAction.java
 * Author      :   Sayed Taha
 * Description :   Represents Delete Map Action
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.partytypedeedmap.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypedeedmap.bd.PartyTypeDeedMapBD;
import com.wipro.igrs.partytypedeedmap.form.PartyTypeDeedMapForm;

public class DeletePartytypeDeedMapAction extends BaseAction{
    PartyTypeDeedMapBD bd=PartyTypeDeedMapBD.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String [] delete=((PartyTypeDeedMapForm)form).getSelectedMappingsForDeletion();
		//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");

		for(int index = 0 ;index<delete.length ; index++){
			bd.deletePartyTypeDeedMaps(delete[index],roleId,funId,userId);
		}
		return mapping.findForward("deletesucceed");
	}

}
