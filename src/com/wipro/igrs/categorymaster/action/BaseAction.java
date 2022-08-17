package com.wipro.igrs.categorymaster.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.categorymaster.action.*;
;



/**
 * ===========================================================================
 * 
 * @since : Dec 11, 2007 File : BaseAction.java Description : Represents the
 *        base action for all the classes
 * 
 * @author : Madan Mohan
 * 
 * 
 * ===========================================================================
 */
public abstract class BaseAction extends Action {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(BaseAction.class);

	public BaseAction() {
	}

	/**
	 * @author Madan Mohan
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @param session
	 * @exception Exception
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		ActionForward forward = null;
//		UserDTO userDO = null;
//
//		HttpSession session = request.getSession(false);
//		boolean validSession = false;
//		boolean checkPrivilege = false;
//		String path = mapping.getPath();
//		try {
//			if (session == null) {
//				// forward = new ActionForward("/login");
//				logger.debug("validSession:" + session);
//			} else {
//				logger.debug("validSession:" + session);
//
//				if (session != null) {
//					userDO = (UserDTO) session
//							.getAttribute(WebConstants.SES_USER_DO);
//
//					if (userDO != null) {
//						validSession = true;
//					}
//				}
//
//				logger.debug("validSession:" + validSession);
//				if (!validSession && !(form instanceof LoginForm)) {
//					logger.debug("!validSession && !"
//							+ "(form instanceof LoginForm)");
//					forward = new ActionForward("/login");
//
//				} else if (!validSession && (form instanceof LoginForm)) {
//					logger.debug("!validSession && "
//							+ "(form instanceof LoginForm)");
//					/*
//					 * String funct = (String)
//					 * request.getParameter("function_Name"); String act =
//					 * (String) request.getParameter("activity");
//					 * 
//					 * logger.debug("function :-"+funct+"activity:-"+act);
//					 * if(funct!=null & act !=null ) { logger.debug("inside
//					 * function"); session.setAttribute("function", funct+" -
//					 * "+act); }
//					 */
//
//					forward = execute(mapping, form, request, response, session);
//
//				} else if (path.equalsIgnoreCase("/logout")) {
//					logger.debug("path.equalsIgnoreCase(/logout)");
//					forward = execute(mapping, form, request, response, session);
//				} else {
//
//					/*
//					 * Check whether the pasword has to been changed or not for
//					 * that user.
//					 */
//					logger.debug("change password");
//					String passwordChangeFlag = userDO.getChangePasswordFlag();
//					if (passwordChangeFlag != null
//							&& passwordChangeFlag
//									.equalsIgnoreCase(Constants.YES_VALUE)
//							&& (!path.equalsIgnoreCase("/changePassword"))) {
//						// forward = new ActionForward("/changePassword.do");
//					}
//					String funName = (String) request.getParameter("fnName");
//					String modName = (String) request.getParameter("modName");
//					String funId = (String) request.getParameter("funId");
//
//					logger.debug("fnName :-" + funName + "modName:-" + modName);
//					if (funName != null && modName != null) {
//						logger.debug("inside function");
//						session.setAttribute("fnName", funName);
//						session.setAttribute("modName", modName);
//						session.setAttribute("functionId", funId);
//					}
//
//					String mode = request.getParameter(Constants.MODE);
//
//					if (mode == null) {
//						mode = (String) request.getAttribute(Constants.MODE);
//					}
//
//					forward = execute(mapping, form, request, response, session);
//
//				}
//			}
//
//		} catch (Throwable e) {
//			// return mapping.findForward("error");
//			logger.debug(e);
//		}
//		return forward;
		HttpSession session = request.getSession(false);
		return execute(mapping, form, request, response,session);
	}

	public abstract ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception;
}
