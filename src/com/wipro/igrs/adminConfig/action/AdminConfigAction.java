package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.AdminAttrBD;
import com.wipro.igrs.adminConfig.dto.AdminAttrDTO;
import com.wipro.igrs.adminConfig.form.AdminAttrForm;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * ===========================================================================
 * File : AdminConfigAction.java Description : Represents the Admin
 * configuration Action Class Author : vengamamba P Created Date : Apr 06, 2008
 * 
 * ===========================================================================
 */

public class AdminConfigAction extends BaseAction {
	/**
	 * This is the main action called from the Struts framework.
	 * 
	 * @param mapping
	 *            The ActionMapping used to select this instance.
	 * @param form
	 *            The optional ActionForm bean for this request.
	 * @param request
	 *            The HTTP Request we are processing.
	 * @param response
	 *            The HTTP Response we are processing.
	 */
	private Logger logger = (Logger) Logger.getLogger(AdminConfigAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		AdminAttrForm adform = (AdminAttrForm) form;
		AdminAttrBD adBD = new AdminAttrBD();

		ArrayList moduleidList = adBD.getModuleList();// getting
																	// list of
																	// moduleids

		ArrayList submoduleList = new ArrayList();
		ArrayList functionList = new ArrayList();

		String module = request.getParameter("module");
		if (module != null) {
			submoduleList = adBD.getSubModuleList(module);

		}
		String subModule = request.getParameter("submodule");
		if (subModule != null) {
			functionList = adBD.getFunctionList(subModule);
		}
		String fid = request.getParameter("function");

		AdminAttrDTO dto = new AdminAttrDTO();
		// setting list of values to moduleids,submoduleids,functionids
		dto.setModuleIDList(moduleidList);
		dto.setSubModuleIDList(submoduleList);
		dto.setFunctionIDList(functionList);
		adform.setAdmindto(dto);

	//	HttpSession session = request.getSession(false);
		session.setAttribute("adminForm", adform);

		return mapping.findForward("success");

	}
}
