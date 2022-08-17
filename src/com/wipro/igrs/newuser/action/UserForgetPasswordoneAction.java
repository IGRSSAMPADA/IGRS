/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordoneAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.newuser.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.newuser.bd.UserForgetPasswordBD;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
import com.wipro.igrs.newuser.form.UserForgetPasswordForm;

public class UserForgetPasswordoneAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
	//	LoggerMsg.info("we are in UseroneAction");
		String target = "success";
		
		/* Populate the userForgetPasswordForm form */
		UserForgetPasswordForm userForgetPasswordForm = (UserForgetPasswordForm)form;
		UserForgetPasswordDTO dto=new UserForgetPasswordDTO();
		UserForgetPasswordBD userforgetbd = new UserForgetPasswordBD();
		
		
		
		String strUserName=userForgetPasswordForm.getUserName();
		String strhQustion=userForgetPasswordForm.getHquestionId();
		String  srtAnswer=userForgetPasswordForm.getAnswer();
		if(strUserName!="" && strhQustion!="" && srtAnswer!="")
		{
			//dto=userforgetbd.getUserName(strUserName,strhQustion,srtAnswer);
			
		}	
		if(dto!=null){
			userForgetPasswordForm.setDto(dto);
		}
		
		 
		session.setAttribute("UserForgetPasswordForm", userForgetPasswordForm);   
		
		return mapping.findForward(target);	
	}
}
