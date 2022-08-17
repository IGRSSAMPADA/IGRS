/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupLoadEditAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the pre-Edit Action Class for User RoleGroup Mappings Master.
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
import com.wipro.igrs.userrolegroupmapping.dto.UserRGroupMappingDTO;
import com.wipro.igrs.userrolegroupmapping.form.UserRGroupMappingActionForm;

public class UserRGroupLoadEditAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserRGroupLoadEditAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in UserRGroupLoadEditAction");
		String target = "edituserRGroupmapping";

		
		UserRGroupMappingBD userRGroupdbd = new UserRGroupMappingBD();
		UserRGroupMappingActionForm userrGroupForm=(UserRGroupMappingActionForm)form;
		
		String userRGroupId=(String)request.getParameter("userRGroupId");

		
		
		UserRGroupMappingDTO dto = userRGroupdbd.getUserRGroupMappingId(userRGroupId);
		userrGroupForm.setUserRGroupId(dto.getUserRGroupId());
		userrGroupForm.setRoleGroupId(dto.getRoleGroupId());
		userrGroupForm.setUserId(dto.getUserId());
		
		ArrayList userOptions=userRGroupdbd.getUsersList();
		userrGroupForm.setUserOptions(userOptions);

		ArrayList roleGroupOptions=userRGroupdbd.getRoleGroupList();
		userrGroupForm.setRoleGroupOptions(roleGroupOptions);



		return mapping.findForward(target);
	}


}
