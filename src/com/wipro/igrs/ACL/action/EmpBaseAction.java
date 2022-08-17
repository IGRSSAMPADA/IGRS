package com.wipro.igrs.ACL.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.dto.UserLoginDTO;
import com.wipro.igrs.ACL.form.UserLoginForm;
import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.login.form.LoginForm;

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
public abstract class EmpBaseAction extends Action {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(EmpBaseAction.class);

	public EmpBaseAction() {
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
		ActionForward forward = null;

		UserLoginDTO userdto = null;
		HttpSession session = request.getSession(false);
		boolean validSession = false;
		String forwardJsp = mapping.getPath();
		try {
			if (session == null) {
				logger.debug("validSession:" + session);
				forwardJsp = "nullsession";
				userdto.setFrwdPath(forwardJsp);
			} else {
				if (session != null) {
					userdto = (UserLoginDTO) session.getAttribute("dto");
					if (userdto != null) {
						validSession = true;
					}
				}
				logger.debug("validSession:" + validSession);

				if (!validSession && !(form instanceof UserLoginForm)) {
					logger.debug("!validSession && !"
							+ "(form instanceof UserLoginForm)");
					forward = new ActionForward("/login");

				} else if (!validSession && (form instanceof UserLoginForm)) {
					logger.debug("!validSession && "
							+ "(form instanceof UserLoginForm)");
					forward = execute(mapping, form, request, response, session);
				} else if (forwardJsp.equalsIgnoreCase("/logout")) {
					logger.debug("path.equalsIgnoreCase(/logout)");
					forward = execute(mapping, form, request, response, session);
				}

				forward = execute(mapping, form, request, response, session);
			}

		} catch (Throwable e) {
		}
		return forward;
	}

	public abstract ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception;
}
