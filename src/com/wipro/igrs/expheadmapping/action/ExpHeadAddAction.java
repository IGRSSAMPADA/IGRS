/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  ExpHeadAddAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Add Action Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  20th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expheadmapping.bd.ExpHeadMappingBD;
import com.wipro.igrs.expheadmapping.dto.ExpHeadMappingDTO;
import com.wipro.igrs.expheadmapping.exception.ExpHeadMappingIGRSException;
import com.wipro.igrs.expheadmapping.form.ExpHeadMappingActionForm;

public class ExpHeadAddAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ExpHeadAddAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		if(isCancelled(request))
			 return mapping.findForward("back");
		
		ActionErrors errors =new ActionErrors();
		
		logger.info("we are in ExpHeadAddAction");
		String target = "success";


		String userID = (String)session.getAttribute("UserId");

		/* Populate the Exp. Head Mapping form */
		ExpHeadMappingActionForm expHeadForm = (ExpHeadMappingActionForm) form;
	
		
		
		ExpHeadMappingDTO dto = new ExpHeadMappingDTO();
		
		dto.setMajorHeadId(expHeadForm.getMajorHeadId());
		System.out.println(expHeadForm.getMajorHeadId());
		dto.setSubMajorHeadId(expHeadForm.getSubMajorHeadId());
		System.out.println(expHeadForm.getSubMajorHeadId());
		dto.setMinorHeadId(expHeadForm.getMinorHeadId());
		dto.setObjectId(expHeadForm.getObjectId());
		dto.setSchemeId(expHeadForm.getSchemeId());
		dto.setSegmentId(expHeadForm.getSegmentId());
		dto.setDetailedHeadId(expHeadForm.getDetailedHeadId());
		dto.setDroId(userID);
		
		ExpHeadMappingBD expheadbd = new ExpHeadMappingBD();
		

		
		
		try {
			
			expheadbd.addExpHeadMappingMster(dto);
			expHeadForm.setPageName("expheadadd");
			
			} catch (ExpHeadMappingIGRSException e) {

		       	  errors.add("expHeadError",new ActionError("expheadmapping.error.IDExists"));

		          saveErrors(request, errors);
		          
		          return mapping.findForward("error");
			}


	

		return mapping.findForward(target);
	}
}
