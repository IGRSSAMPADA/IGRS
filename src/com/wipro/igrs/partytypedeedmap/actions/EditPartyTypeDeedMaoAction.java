/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EditPartyTypeDeedMaoAction.java
 * Author      :   Sayed Taha
 * Description :   Represents Map Update Action
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

public class EditPartyTypeDeedMaoAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		PartyTypeDeedMapBD bd=PartyTypeDeedMapBD.getInstance();
		String partyID=((PartyTypeDeedMapForm)form).getDeedTypeID();
	//	System.out.println("partyyyyyyyyyyy      "+partyID);
		String deedID=((PartyTypeDeedMapForm)form).getPartyTypeID();
		//System.out.println("deeeeed      "+deedID);
		String mapId=((PartyTypeDeedMapForm)form).getPartyTypeDeedmapID();
		//System.out.println("idddddddd     "+mapId);
		PartyTypeDeedMapDTO dto=new PartyTypeDeedMapDTO();
		 // session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		dto.setDeedTypeID(deedID);
		dto.setPartyTypeID(partyID);
		dto.setPartyTypeDeedmapID(mapId);
		if(bd.isMapExists(dto)){
			//System.out.println("::::::::::::::::::::::::::EXISTS");
			ActionErrors error=new ActionErrors();
			error.add("partydeedmaperror", new ActionError("errors.partydeedmaperror"));
			saveErrors(request, error);
			return mapping.findForward("editpartydeedmap");
		}
		bd.updatePartyDeedMap(dto,roleId,funId,userId);
		return mapping.findForward("successupdate");
	}

}
