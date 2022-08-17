/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeLoadAddAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the pre-Add Action Class for userType Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.usertypemaster.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;

public class UserTypeLoadAddAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserTypeLoadAddAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		logger.info("we are in UserTypeAddAction");

		String target = "createusertypemaster";

		return mapping.findForward(target);
	}
}
