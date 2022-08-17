package com.wipro.igrs.baseaction.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bouncycastle.asn1.eac.RSAPublicKey;

import com.wipro.igrs.baseaction.constant.Constants;
import com.wipro.igrs.baseaction.constant.WebConstants;
import com.wipro.igrs.baseaction.dto.UserDTO;
import com.wipro.igrs.clr.services.DownloadService;
import com.wipro.igrs.clr.services.PDFFormVI;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.common.PropertiesFileReader;
import com.wipro.igrs.common.dao.CommonDAO;
import com.wipro.igrs.documentsearch.form.DocumentSearchForm;
import com.wipro.igrs.estamping.form.DutyCalculationForm;
import com.wipro.igrs.login.form.LoginForm;
import com.wipro.igrs.login.loadSingleton.LoadMenuExternal;
import com.wipro.igrs.payment.form.OnlinePymtForm;

/**
 * ===========================================================================
 * 
 * @since : Dec 11, 2007 File : BaseAction.java Description : Represents the
 *        base action for all the classes
 * 
 * @author : Madan Mohan
 * 
 * 
 *         
 *         ======================================================================
 *         =====
 */
public abstract class BaseAction extends Action {

	/**
	 * @author Madan Mohan
	 */
	private Logger logger = (Logger) Logger.getLogger(BaseAction.class);
	static String logTime;

	/*
	 * static String byPassModule; static String [] byPassModuleList;
	 */

	static {
		try {
			logTime = PropertiesFileReader.getInstance("resources.igrs").getValue("process_time_log_interval");
			/*
			 * code commmented for Bypassing the CSRF Issue
			 * byPassModule=PropertiesFileReader
			 * .getInstance("resources.igrs").getValue("ActionBypass");
			 * byPassModuleList=byPassModule.split(",");
			 */
		} catch (Exception e) {
			logTime = "60";
		}
	}

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
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isCsrfLinkP = false;
		ActionForward forward = null;
		UserDTO userDO = null;
		String data = request.getParameter("encdata");
		HttpSession session = request.getSession(false);
		System.out.println("Session id at the begining -- " + session.getId());
		/*PDFFormVI formVI = new PDFFormVI();
		
		formVI.generateFormAPDF("300522000011",response,"hi");*/
		boolean validSession = false;
		// boolean checkPrivilege = false;
		String type = mapping.getType();
		String path = mapping.getPath();
		//PDFFormVI.test("220222000001", response,"en");
		String funcID = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");
		if (userId != null) {
			
			CommonDAO dao = new CommonDAO();
			String our = session.getId();
			ArrayList list = dao.getuserDetail(userId, our);
			if (list != null && !list.isEmpty()) {
				ArrayList main = new ArrayList();
				for (int i = 0; i < list.size(); i++) {
					ArrayList li = (ArrayList) list.get(i);

					if (li.get(5).toString().equalsIgnoreCase(session.getId())) {
						main = li;
					}
				}

				if (!main.isEmpty()) {
					String thier = main.get(5).toString();
					String status = main.get(3).toString();
					if (status.equalsIgnoreCase("D")) {
						session.removeAttribute(WebConstants.SES_USER_DO);
						session.invalidate();
						session = null;
						forward = new ActionForward("/jsp/SessionExpired.jsp");
						return forward;
					}
				} else {
					boolean boo1 = dao.updateIntoLogin(our, userId);
					boolean bool1 = dao.insertIntoLogin(our, userId, LoadMenuExternal.getInstance().getIP());
				}
			} else {
				String our1 = session.getId();
				boolean boo1 = dao.updateIntoLogin(our, userId);
				boolean bool1 = dao.insertIntoLogin(our, userId, LoadMenuExternal.getInstance().getIP());
			}
		}

		// POC for XSS
		if (IGRSCommon.checkXSSData(form)) {
			forward = new ActionForward("/jsp/FailureXSS.jsp");
			System.out.println("Invalid XSS Data Token.. login.." + IGRSCommon.xssFieldName);
			request.setAttribute("xssData", IGRSCommon.xssFieldName);
			logger.debug("Invalid XSS Data Token.. login..");
			session.invalidate();
			return forward;
		}
		try {
			if (session == null) {
				forward = new ActionForward("/login.do");
			} else {
				if (session != null) {
					userDO = (UserDTO) session.getAttribute(WebConstants.SES_USER_DO);

					if (userDO != null) {
						validSession = true;
					}
				}

				/*
				 * if(validSession) { isCsrfLinkP =
				 * Constants.CSRF_HLINK_IGNORE.equalsIgnoreCase
				 * (request.getParameter(Constants.CSRF_HLINK_IDENTIFIER)) ?
				 * true : false; if(!isCsrfLinkP ){
				 * if(!(Constants.CSRF_IGNORE.equalsIgnoreCase
				 * (request.getParameter(Constants.CSRF_IDENTIFIER))) &&
				 * !isTokenValid(request) && data == null) { forward = new
				 * ActionForward("/jsp/FailureCSRF.jsp"); session.invalidate();
				 * logger.debug("Invalid CSRF Token" ); return forward; } } }
				 */
				if (validSession && !(Constants.CSRF_IGNORE.equalsIgnoreCase(request.getParameter(Constants.CSRF_IDENTIFIER))) && type != null && type.toLowerCase().contains("guideline")) {
					if (!isTokenValid(request)) {
						forward = new ActionForward("/jsp/FailureCSRF.jsp");
						session.invalidate();
						logger.debug("Invalid CSRF Token");
						return forward;
					}
				}
				resetToken(request);
				saveToken(request);
				String getFilePath = request.getParameter("downloadFilePath");
				String downloadType = request.getParameter("type");
				if (null != getFilePath) {
					if (!"".equals(getFilePath)) {
						logger.debug("download request received...");
						//getFilePath=DownloadService.encryptFilePath(getFilePath);
						DownloadService.downloadFilesForRCMS(getFilePath, response,downloadType);
					}
				} else {
					if (!validSession && !(form instanceof LoginForm)) {
						logger.debug("!validSession && !" + "(form instanceof LoginForm)");

						if (!validSession && (form instanceof OnlinePymtForm)) {
							logger.debug("!OMG " + "(form instanceof LoginForm)");
							forward = execute(mapping, form, request, response, session);
						} else if (!validSession && (form instanceof DutyCalculationForm)) {
							forward = execute(mapping, form, request, response, session);
						} else if (!validSession && (form instanceof DocumentSearchForm)) {
							forward = execute(mapping, form, request, response, session);
						} else
							forward = new ActionForward("/jsp/SessionExpired.jsp");

					} else if (!validSession && (form instanceof LoginForm)) {

						/**
						 * Start Code added By Manish for Security Audit work,
						 * 1. To implement separate
						 * 
						 * <pre> login and post login cookie.
						 */
						Long startTime = (Long) session.getAttribute("StartTimeStamp");
						String attribs = (String) session.getAttribute("QARFAD");
						session.invalidate();
						session = request.getSession(true);
						session.setAttribute("StartTimeStamp", startTime);
						session.setAttribute("QARFAD", attribs);

						/**
						 * End Code added By Manish for Security Audit work, 1.
						 * To implement separate
						 * 
						 * <pre> login and post login cookie.
						 */

						request.setAttribute("pymt", "Online");
						forward = execute(mapping, form, request, response, session);

					}

					else if (path.equalsIgnoreCase("/logout")) {
						forward = execute(mapping, form, request, response, session);
					} else {

						/*
						 * Check whether the pasword has to been changed or not
						 * for that user.
						 */
						String passwordChangeFlag = userDO.getChangePasswordFlag();
						if (passwordChangeFlag != null && passwordChangeFlag.equalsIgnoreCase(Constants.YES_VALUE) && (!path.equalsIgnoreCase("/changePassword"))) {
							// forward = new
							// ActionForward("/changePassword.do");
						}
						String funName = (String) request.getParameter("fnName");
						String modName = (String) request.getParameter("modName");
						String funId = (String) request.getParameter("funId");

						logger.debug("fnName :-" + funName + "modName:-" + modName);
						if (funName != null && modName != null) {
							logger.debug("inside function");
							session.setAttribute("fnName", funName);
							session.setAttribute("modName", modName);
							session.setAttribute("functionId", funId);
						}

						String userID = (String) session.getAttribute("UserId");
						String functionID = "";
						if (funId == null) {
							functionID = (String) session.getAttribute("functionId");
						} else {
							functionID = funId;
						}

						String role = (String) session.getAttribute("role");
						ArrayList list = (ArrayList) session.getAttribute("fncList");
						logger.debug("In BaseAction :-" + userID + ":" + functionID + ":" + role);
						String checkStatus = validateBaseAction(userID, role, functionID, list);
						// forward = execute(mapping, form, request, response,
						// session);

						if (Constants.LOGIN_SUCCESS.equals(checkStatus)) {
							forward = execute(mapping, form, request, response, session);
						} else if (Constants.ROLE_NOT_AUTHORIZED.equals(checkStatus) || Constants.SESSION_TIME_OUT.equals(checkStatus)) {
							session.setAttribute("checkStatus", checkStatus);
							forward = new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
						}
						/*
						 * if(validSession){ String userID = (String)
						 * session.getAttribute("UserId");
						 * logger.debug("userID:-"+userID); if(userID !=null) {
						 * 
						 * session.setAttribute("UserID", userID); } }
						 */
						// logger.debug("forward:-"+forward);
					}
				}
			}

		} catch (Throwable e) {
			session.setAttribute("checkStatus", Constants.PROBLEM_IN_AS);
			forward = new ActionForward("/jsp/login/UserRoleValidationPage.jsp");
			logger.debug(e);
			e.printStackTrace();
		}
		/*
		 * if(session != null){ try { Long processTime =
		 * (System.currentTimeMillis() -
		 * (Long)session.getAttribute("StartTimeStamp")); Float pss =
		 * processTime.floatValue(); pss /= 1000;
		 * if(pss>Integer.parseInt(logTime)){
		 * logger.debug("application processing time greater than 60 seconds.");
		 * } session.setAttribute("RequestProcessTime", pss.toString());
		 * }catch(IllegalStateException ex){} }
		 */

		/*
		 * String actPath = forward.getPath(); if(!isCsrfLinkP && (actPath !=
		 * null && actPath.toLowerCase().contains(".jsp"))){
		 * resetToken(request); saveToken(request); }
		 */

		logger.debug("forward Name :- " + forward);
		return forward;
	}

	private String validateBaseAction(String userID, String roleID, String funID, ArrayList list) {
		String returnCode = Constants.LOGIN_SUCCESS;

		// LoginBD bd = new LoginBD();
		// int roleCheck = bd.validateRole(roleID, funID);
		// Added Debug statements to indentify
		if (funID != null && !list.contains(funID)) {
			logger.debug(" ROLE_NOT_AUTHORIZED Function ID not found list is : " + list);
			returnCode = Constants.ROLE_NOT_AUTHORIZED;
		}
		if (userID == null) {
			returnCode = Constants.SESSION_TIME_OUT;
		}

		return returnCode;
	}

	/*
	 * public boolean isByPassModule(String functionId) { if(functionId != null
	 * && !functionId.isEmpty()){ for (int i=0;i<byPassModuleList.length;i++){
	 * if(byPassModuleList[i].equalsIgnoreCase(functionId)){ return true; } } }
	 * return false; }
	 */

	public abstract ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception;
}
