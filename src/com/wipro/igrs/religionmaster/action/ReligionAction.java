/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionAction.java
 * Author      :   Sara Hussain 
 * Description :   Represents the Religion Action Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */

package com.wipro.igrs.religionmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.religionmaster.bd.ReligionBD;
import com.wipro.igrs.religionmaster.dto.ReligionDTO;
import com.wipro.igrs.religionmaster.form.ReligionForm;



public class ReligionAction extends BaseAction{
	//private Logger logger = (Logger) Logger.getLogger(ReligionAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		//logger.info("we are in ReligionAction");
		String target = "success";
		
		//session.setAttribute("UserId", "sara");
		
		/* Populate the religion form */
		ReligionForm religionForm = (ReligionForm) form;
		
		
		ReligionDTO dto = new ReligionDTO();
		ReligionBD religionbd = new ReligionBD();
		ArrayList religionList = religionbd.getReligionList();



		

		/* Setting religion master List items */
		//dto.setReligionList(religionList);
		religionForm.setReligionList(religionList);
		
		/* Parameter religionId */
		//String religionId = request.getParameter("religionId");
		//logger.debug("religion id in action.." + religionId);
		//if (religionId != null) {
		//	dto = religionbd.getReligionId(religionId);
		//}
		//religionForm.setDto(dto);
		/* Set the sessions */

		//session.setAttribute("religionList",dto.getReligionList());
		//session.setAttribute("ReligionForm", religionForm);

		request.setAttribute("religionList", religionList);
		
		return mapping.findForward(target);
	}

}
