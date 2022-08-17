/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupEditAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Edit Action Class for User RoleGroup Mappings Master.
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.userrolegroupmapping.bd.UserRGroupMappingBD;
import com.wipro.igrs.userrolegroupmapping.dto.UserRGroupMappingDTO;
import com.wipro.igrs.userrolegroupmapping.exception.UserRGroupMappingIGRSException;
import com.wipro.igrs.userrolegroupmapping.form.UserRGroupMappingActionForm;

public class UserRGroupEditAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserRGroupEditAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		if(isCancelled(request))
			 return mapping.findForward("back");
		
		
		logger.info("we are in UserRGroupEditAction");
		ActionErrors errors =new ActionErrors();
		
		String target = "successup";

		String userID = (String)session.getAttribute("UserId");

		UserRGroupMappingActionForm userRGroupFrom = (UserRGroupMappingActionForm) form;
	
		
		UserRGroupMappingDTO dto = new UserRGroupMappingDTO();
		
		dto.setUserRGroupId(userRGroupFrom.getUserRGroupId());
		System.out.println(userRGroupFrom.getUserRGroupId());
		
		dto.setRoleGroupId(userRGroupFrom.getRoleGroupId());
		System.out.println(userRGroupFrom.getRoleGroupId());
		
		
		dto.setUserId(userRGroupFrom.getUserId());
		System.out.println(userRGroupFrom.getUserId());

		
		UserRGroupMappingBD userRGroupbd = new UserRGroupMappingBD();
		
		
		try {
			
			userRGroupbd.updateUserTypemaster(dto);
			userRGroupFrom.setPageName("userRGroupedit");
			
			} catch (UserRGroupMappingIGRSException e) {

		       	  errors.add("userRGroupError",new ActionError("userrolegroupmappingmaster.error.IDExists"));

		          saveErrors(request, errors);
		          
		          return mapping.findForward("error");
			}
		

		

		return mapping.findForward(target);
		

	}
}
