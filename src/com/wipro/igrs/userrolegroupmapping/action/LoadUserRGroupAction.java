/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   LoadUserRGroupAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Action Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  8th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.userrolegroupmapping.bd.UserRGroupMappingBD;
import com.wipro.igrs.userrolegroupmapping.form.UserRGroupMappingActionForm;

public class LoadUserRGroupAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(LoadUserRGroupAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		logger.info("we are in LoadUserRGroupMappingAction");
		String target = "viewuserolergroup";
		
		/* Populate the uaerTyppe form */
		UserRGroupMappingActionForm userRGroupForm = (UserRGroupMappingActionForm) form;
		
		UserRGroupMappingBD userrgroupbd = new UserRGroupMappingBD();

			ArrayList userRGroupList = userrgroupbd.getUserRGroupList();
			userRGroupForm.setUserRGroupList(userRGroupList);


		request.setAttribute("userRGroupList", userRGroupList);
		
		return mapping.findForward(target);
	}
}
