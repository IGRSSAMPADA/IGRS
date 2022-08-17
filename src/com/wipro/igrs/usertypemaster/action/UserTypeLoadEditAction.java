/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeLoadEditAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the pre-Edit Action Class for UserType Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.usertypemaster.bao.UserTypeBao;
import com.wipro.igrs.usertypemaster.bd.UserTypeBD;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.form.UserTypeForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class UserTypeLoadEditAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserTypeLoadEditAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in UserTypeLoadEditAction");
		String target = "updateusertypemaster";
		/* Populate the uaerTyppe form */
		UserTypeForm userTypeForm = (UserTypeForm) form;
		String usertypeid=(String)request.getParameter("usertypeid");

		
		UserTypeBD usertyprbd= new UserTypeBD();
		UserTypeDTO dto =usertyprbd.getUserTypeId(usertypeid);
		
		userTypeForm.setUserTypeId(dto.getUserTypeId());
		userTypeForm.setUserTypeName(dto.getUserTypeName());
		userTypeForm.setUserTypeStatus(dto.getUserTypeStatus());
		userTypeForm.setUserTypeDesc(dto.getUserTypeDesc());
		

		return mapping.findForward(target);
	}
}
