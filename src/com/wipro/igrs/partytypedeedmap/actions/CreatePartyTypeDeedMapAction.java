/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CreatePartyTypeDeedMapAction.java
 * Author      :   Sayed Taha
 * Description :   Represents Map adding Action
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.partytypedeedmap.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.partytypedeedmap.bd.PartyTypeDeedMapBD;
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;
import com.wipro.igrs.partytypedeedmap.form.PartyTypeDeedMapForm;

public class CreatePartyTypeDeedMapAction extends BaseAction{
  
	PartyTypeDeedMapBD bd=PartyTypeDeedMapBD.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		ActionErrors error=new ActionErrors();
		String partyID=((PartyTypeDeedMapForm)form).getDeedTypeID();
	//	System.out.println("partyyyyyyyyyyy      "+partyID);
		String deedID=((PartyTypeDeedMapForm)form).getPartyTypeID();
		//System.out.println("deeeeed      "+deedID);
		PartyTypeDeedMapDTO dto=new PartyTypeDeedMapDTO();
		//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		dto.setDeedTypeID(deedID);
		dto.setPartyTypeID(partyID);
		if(bd.isMapExists(dto)){
		//	System.out.println("::::::::::::::::::::::::::EXISTS");
			error.add("partydeedmaperror", new ActionError("errors.partydeedmaperror"));
			saveErrors(request, error);
			return mapping.findForward("createpartydeedmap");
		}
	//	System.out.println("::::::::::::::::::::::::::NOT--EXISTS");
		bd.addPartyDeedMapping(dto,roleId,funId,userId);
		return mapping.findForward("successcreate");
	}

}
