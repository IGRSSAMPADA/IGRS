/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :  UserTypeAddAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Add Action Class for UserType Master.
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
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.usertypemaster.bao.UserTypeBao;
import com.wipro.igrs.usertypemaster.bd.UserTypeBD;
import com.wipro.igrs.usertypemaster.dto.UserTypeDTO;
import com.wipro.igrs.usertypemaster.exception.UserTypeIGRSException;
import com.wipro.igrs.usertypemaster.form.UserTypeForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class UserTypeAddAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(UserTypeAddAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		if(isCancelled(request))
			 return mapping.findForward("back");
		logger.info("we are in UserTypeAddAction");
		
		ActionErrors errors =new ActionErrors();
		String target = "success";
		
		String userID = (String)session.getAttribute("UserId");

		/* Populate the uaerTyppe form */
		UserTypeForm userTypeForm = (UserTypeForm) form;
		userTypeForm.setCreatedBy(userID);
		userTypeForm.setUpdatedBy(userID);
		
		
		
		UserTypeDTO dto = new UserTypeDTO();
		
		dto.setUserTypeId(userTypeForm.getUserTypeId());
		dto.setUserTypeName(userTypeForm.getUserTypeName());
		dto.setUserTypeDesc(userTypeForm.getUserTypeDesc());
		dto.setUserTypeStatus(userTypeForm.getUserTypeStatus());
		dto.setCreatedBy(userTypeForm.getCreatedBy());
		dto.setCreatedDate(userTypeForm.getCreatedDate());
		dto.setUpdatedBy(userTypeForm.getUpdatedBy());
		dto.setUpdatedDate(userTypeForm.getUpdatedDate());
		
		
		UserTypeBD usertypebd = new UserTypeBD();
		
		
		if (userTypeForm.getPageName().equalsIgnoreCase("usertypecreate")) {
			{
		
				try {
					usertypebd.addUserTypemaster(dto);
				} catch (UserTypeIGRSException e) {

			       	  errors.add("userTypeError",new ActionError("usertypemaster.error.nameExists"));
			          saveErrors(request, errors);
			          
			          return mapping.findForward("error");
				}
	

				
			}
		}

		return mapping.findForward(target);
	}
}
