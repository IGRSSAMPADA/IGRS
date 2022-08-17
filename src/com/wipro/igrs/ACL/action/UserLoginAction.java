/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserLoginAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class for the User Login 
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  11th April,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.bd.RoleManagementBD;
import com.wipro.igrs.ACL.dto.UserLoginDTO;
import com.wipro.igrs.ACL.form.UserLoginForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class UserLoginAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("login");
	private Logger logger = (Logger) Logger.getLogger(UserLoginAction.class);

	/**
	 * Method execute for ACL-Role Creation.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		UserLoginForm userForm = (UserLoginForm) form;
		UserLoginDTO userdto = userForm.getLogindto();

		if (form != null) {

			String action = userForm.getLogindto().getLoginAction();
			logger.debug("Action values are.........." + action);
			RoleManagementBD rolebd = new RoleManagementBD();
			forwardJsp = CommonConstant.USER_SUCCESS;
			if (CommonConstant.LOGIN_ACTION.equalsIgnoreCase(userForm
					.getLogindto().getLoginAction())) {
				String uName = userForm.getLogindto().getUserName();
				String pswd = userForm.getLogindto().getPassword();
				session.setAttribute("userID", uName);
				boolean flag = rolebd.chkAuthentication(uName, pswd);

				if (flag == true) {
					forwardJsp = CommonConstant.LOGIN_SUCCESS;
					userdto.setFrwdPath(forwardJsp);
				} else {
					logger.debug("Passwords are not confirmed.");
					forwardJsp = "loginFail";
					userdto.setFrwdPath(forwardJsp);
				}
			} else if (CommonConstant.RESET_ACTION.equalsIgnoreCase(userForm
					.getLogindto().getLoginAction())) {
				if (userForm != null) {
					userForm.getLogindto().setUserName(null);
					userForm.getLogindto().setPassword(null);
				}
				logger.debug("Page is reseted to default values successfully");
				forwardJsp = CommonConstant.RESET_SUCCESS;
			}
		}
		return mapping.findForward(forwardJsp);
	}
}
