package com.wipro.igrs.copyissuance.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.copyissuance.bd.CertifiedBD;
import com.wipro.igrs.login.action.LoginAction;

public class IssuanceFileDownLoadAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wipro.igrs.baseaction.action.BaseAction#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      javax.servlet.http.HttpSession)
	 */
	private Logger logger = Logger.getLogger(LoginAction.class);


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		CertifiedBD deBD = new CertifiedBD();	
		String strReferenceId = request.getParameter("referenceId");
		String strFunctionName = request.getParameter("functionName");
		logger.debug("Print the ID " + strReferenceId +" strFunctionName = "+strFunctionName);
		deBD.displayObjectDetails(response, strReferenceId,strFunctionName);
		String FORWARD = "displayissuanceFile";		
		return mapping.findForward(FORWARD);
	}

}
