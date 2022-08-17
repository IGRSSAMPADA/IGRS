/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupDeleteAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Delete Action Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  8th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.userrolegroupmapping.action;


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

public class UserRGroupDeleteAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserRGroupDeleteAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in UserRGroupDeleteAction");
		
		String target = "confirmadd";
		UserRGroupMappingBD userRGroupdbd= new UserRGroupMappingBD();

		userRGroupdbd.deleteUserRGroup(((UserRGroupMappingActionForm)form).getSelected());		

		return mapping.findForward(target);
	}
}
