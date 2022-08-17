/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Action Class for UserType Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.usertypemaster.bd.UserTypeBD;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.exception.UserTypeIGRSException;
import com.wipro.igrs.usertypemaster.form.UserTypeForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class UserTypeAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserTypeAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		logger.info("we are in UserTypeAction");
		String target = "usertypemaster";
		
		/* Populate the uaerTyppe form */
		UserTypeForm userTypeForm = (UserTypeForm) form;
		
		UserTypeBD usertypebd = new UserTypeBD();

		
		if (userTypeForm.getPageName() != null && userTypeForm.getPageName().equalsIgnoreCase("usertypedelete"))
				usertypebd.deletUserTypemaster(userTypeForm.getSelected());


		ArrayList usertypeList = usertypebd.getUserTypeList();
		userTypeForm.setUserTypeList(usertypeList);

	/*	 Parameter usertypeId 
		String usertypeId = request.getParameter("usertypeId");
		logger.debug("userType id in action.." + usertypeId);
		if (usertypeId != null) {
			dto = usertypebd.getUserTypeId(usertypeId);
		}
		*/
		//TODO:delete this and read form the form bean
		request.setAttribute("usertypeList", usertypeList);
		return mapping.findForward(target);
	}
}
