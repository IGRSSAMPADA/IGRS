/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MenuAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class for the LHS Menu Creation
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  18th April,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.action;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.bd.MenuBD;
import com.wipro.igrs.ACL.dto.UserLoginDTO;
import com.wipro.igrs.ACL.form.UserLoginForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.baseaction.bd.LoginBD;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.login.action.LoginAction;
import com.wipro.igrs.login.constant.LoginConstant;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.login.rule.LoginRule;

/**
 * @author neegaga
 * 
 */
public class MenuAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("menu");
	private int noAttempt = 1;
	private Logger logger = (Logger) Logger.getLogger(LoginAction.class);

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
		// HttpSession session = request.getSession(false);
		// Start - Neesha's code
		noAttempt = 1;
		LoginForm loginForm;
		boolean flag = false;
		MenuBD menubd = new MenuBD();
		UserDTO userDO = null;
		// End - Neesha's code

		if (form != null) {

			String action = userForm.getLogindto().getLoginAction();
			logger.debug("Action values are.........." + action);
			// MenuBD menubd = new MenuBD();
			forwardJsp = CommonConstant.LOGIN_SCREEN;

			if (CommonConstant.LOGIN_ACTION.equalsIgnoreCase(userForm
					.getLogindto().getLoginAction())) {
				String empID = userdto.getUserName();
				session.setAttribute("EmpId", empID);
				session.setAttribute("UserId", empID);
				String pswd = userdto.getPassword();
				// boolean flag = menubd.chkAuthentication(empID,pswd);
				session.setAttribute("dto", userdto);
				userDO = new UserDTO();
				userDO.setUserId(empID);
				userDO.setPassword(pswd);

				// Start - Neesha's code

				if (session != null) {
					if (session.getAttribute("attempts") != null) {
						noAttempt = Integer.parseInt((String)session.getAttribute("attempts"));
					}
				}
				String status = "";
				logger.debug("LoginAction :-" + userDO.getUserId());

				LoginBD loginBO = new LoginBD();
				String userType = loginBO.getusertype(userDO.getUserId());
				logger.debug("user type is==> :  " + userType);
				String userLockStatus = loginBO.getuserLockStatus(userType,
						userDO.getUserId());

				logger.debug("lock status of the user-->" + userLockStatus);

				if (!userLockStatus.equals("L") || userLockStatus.equals("A")) {
					if (noAttempt > 3) {
						noAttempt = 1;
					}
					status = loginBO.authenticateUser(userType, userDO
							.getUserId(), userDO.getPassword(), String
							.valueOf(noAttempt))[0];
					logger.debug("status:-" + status);

				} else if (userLockStatus.equals("L")) {
					status = LoginConstant.DB_LOCK_USER;
				}

				logger.debug("status:   " + status);
				if (status.equals(LoginConstant.DB_SUCCESSFULLY)) {
					noAttempt = 1;
					flag = true;
				} else {
					noAttempt = noAttempt + 1;
				}
				session.setAttribute("attempts", ""+noAttempt);
				logger.debug("Next attempt to be made is:-" + noAttempt);

				LoginRule rule = new LoginRule();
				ArrayList errorList = rule.validateUser(status, userDO);

				/*
				 * if(session.getAttribute("UserId")!=null) {
				 * if("".equals(userDO.getUserId()) || userDO.getUserId()==null ){
				 * session.removeAttribute("UserId"); }
				 * if("".equals(userDO.getPassword()) ||
				 * userDO.getPassword()==null ){
				 * session.removeAttribute("UserId"); } }
				 */
				request.removeAttribute(LoginConstant.USER_ERROR);
				request.removeAttribute(LoginConstant.USER_ERROR_LIST);
				if (rule.isError()) {
					request.setAttribute(LoginConstant.USER_ERROR, "true");
					request.setAttribute(LoginConstant.USER_ERROR_LIST,
							errorList);
					forwardJsp = "loginFail";
				} else {
					request.removeAttribute(LoginConstant.USER_ERROR);
					request.removeAttribute(LoginConstant.USER_ERROR_LIST);

					// End - Neesha's code

					if (flag == true) {
						// session = request.getSession(true);
						/*String roleId = menubd.getEmpRole(empID);
						String roleName = menubd.getEmpRoleName(empID);
						session.setAttribute("user", roleName);
						logger.debug("user role==>" + roleId
								+ "  user roleName==>" + roleName);
						session.setAttribute("role", roleId);

						ArrayList combinedSMList = new ArrayList();
						ArrayList combinedFList = new ArrayList();
						ArrayList combinedAList = new ArrayList();

						ArrayList moduleList = menubd.getUserModules(roleId);

						if (moduleList != null && moduleList.size() > 0) {
							ArrayList subModList = new ArrayList();
							ArrayList mIdList = new ArrayList();
							for (int k = 0; k < moduleList.size(); k++) {
								ArrayList tmpList = (ArrayList) moduleList
										.get(k);
								String modID = (String) tmpList.get(0);
								mIdList.add(modID);
								subModList = menubd.getUserSubMods(modID,
										roleId);
								combinedSMList.addAll(subModList);
							}
							logger.debug("combined list==>" + combinedSMList);
							logger.debug("Final Module IDs List -->" + mIdList);
							session.setAttribute("ModList", mIdList);
						}
						if (combinedSMList.size() > 0 && combinedSMList != null) {
							ArrayList fncList = new ArrayList();
							ArrayList smIdList = new ArrayList();
							for (int s = 0; s < combinedSMList.size(); s++) {
								ArrayList tmpSMList = (ArrayList) combinedSMList
										.get(s);
								String smID = (String) tmpSMList.get(0);
								smIdList.add(smID);
								fncList = menubd.getUserFunctions(smID, roleId);
								combinedFList.addAll(fncList);
							}
							logger.debug("combined function list==>"
									+ combinedFList);
							logger.debug("Final Submodule IDs List -->"
									+ smIdList);
							session.setAttribute("SubModList", smIdList);
						}
						if (combinedFList != null && combinedFList.size() > 0) {
							ArrayList activityList = new ArrayList();
							ArrayList fncIdList = new ArrayList();
							for (int f = 0; f < combinedFList.size(); f++) {
								ArrayList tmpfncList = (ArrayList) combinedFList
										.get(f);
								String fncId = (String) tmpfncList.get(0);
								fncIdList.add(fncId);
								activityList = menubd.getUserActivities(fncId,
										roleId);
								combinedAList.addAll(activityList);
							}
							logger.debug("combined activity list is:   "
									+ combinedAList);
							logger.debug("Function Ids List is:===>"
									+ fncIdList);
							session.setAttribute("fncList", fncIdList);
						}

						if (combinedAList != null && combinedAList.size() > 0) {
							ArrayList actIdList = new ArrayList();
							Set uniqueActIds = new TreeSet();
							for (int f = 0; f < combinedAList.size(); f++) {
								ArrayList tmpactList = (ArrayList) combinedAList
										.get(f);
								String actId = (String) tmpactList.get(0);
								actIdList.add(actId);
								uniqueActIds.addAll(actIdList);
								actIdList.clear();
							}
							actIdList.addAll(uniqueActIds);
							logger.debug("Final Activity Ids List is:===>"
									+ actIdList);

							session.setAttribute("activityList", actIdList);
						}
						forwardJsp = CommonConstant.LOGIN_SUCCESS;
						userdto.setFrwdPath(forwardJsp);
						session.setAttribute(WebConstants.SES_USER_DO, userDO);*/
					} else {
						logger.debug("Passwords are not confirmed.");
						errorList
								.add("<li><font color='red'>Invalid User Name or Password. Please try again.</font></li>");
						request.setAttribute(LoginConstant.USER_ERROR, "true");
						request.setAttribute(LoginConstant.USER_ERROR_LIST,
								errorList);

						forwardJsp = "loginFail";
						// session = null;
						userdto.setFrwdPath(forwardJsp);
					}
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

			else if ("logout".equalsIgnoreCase(userForm.getLogindto()
					.getLoginAction())) {
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
