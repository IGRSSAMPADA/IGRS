/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PreEditPartyTypeDeedMapAction.java
 * Author      :   Sayed Taha
 * Description :   Represents View Pre Edit Map Action
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
import com.wipro.igrs.partytypedeedmap.dto.PartyTypeDeedMapDTO;
import com.wipro.igrs.partytypedeedmap.form.PartyTypeDeedMapForm;

public class PreEditPartyTypeDeedMapAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		PartyTypeDeedMapBD bd=PartyTypeDeedMapBD.getInstance();
		String mapId=(String)request.getParameter("mappingtoedit");
		PartyTypeDeedMapDTO dto=bd.getPartyDeedMapByID(mapId);
		((PartyTypeDeedMapForm)form).setDeedTypeID(dto.getDeedTypeID());
		((PartyTypeDeedMapForm)form).setPartyTypeID(dto.getPartyTypeID());
		((PartyTypeDeedMapForm)form).setPartyTypeDeedmapID(dto.getPartyTypeDeedmapID());
		((PartyTypeDeedMapForm)form).setDeedTypes(bd.getDeedTypes());
		((PartyTypeDeedMapForm)form).setPartyTypes(bd.getPartyTypes());
		return mapping.findForward("editpartydeedmap");
	}

}
