/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  UserRGroupAddAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Add Action Class for User RoleGroup Mappings Master.
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


public class UserRGroupAddAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserRGroupAddAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		if(isCancelled(request))
			 return mapping.findForward("back");
		
		ActionErrors errors =new ActionErrors();
		
		logger.info("we are in UserRGroupAddAction");
		String target = "success";


		String userID = (String)session.getAttribute("UserId");

		/* Populate the User RGroup Action form */
		UserRGroupMappingActionForm userrGroupForm = (UserRGroupMappingActionForm) form;
	
		
		
		UserRGroupMappingDTO dto = new UserRGroupMappingDTO();
		
		dto.setUserId(userrGroupForm.getUserId());
		System.out.println(userrGroupForm.getUserId());
		dto.setRoleGroupId(userrGroupForm.getRoleGroupId());
		System.out.println(userrGroupForm.getRoleGroupId());
		
		UserRGroupMappingBD userRGroupdbd = new UserRGroupMappingBD();
		

		
		
		try {
			
			userRGroupdbd.addUserRGroupMappingMaster(dto);
			userrGroupForm.setPageName("userRGroupadd");
			
			} catch (UserRGroupMappingIGRSException e) {

		       	  errors.add("userRGroupError",new ActionError("userrolegroupmappingmaster.error.IDExists"));

		          saveErrors(request, errors);
		          
		          return mapping.findForward("error");
			}


	

		return mapping.findForward(target);
	}
}
