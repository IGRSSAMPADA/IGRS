/**
 * DisplayObjectAction.java
 */

package com.wipro.igrs.departmentalenquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquiryBD;
import com.wipro.igrs.departmentalenquiry.form.SuspensionForm;
import com.wipro.igrs.login.action.LoginAction;

/**
 * @author oneapps
 * 
 */
public class DisplayObjectAction extends BaseAction {

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
		String FORWARD = "displayFile";
		
		
		//should be removed
		//session = request.getSession(true);
		
		try {
			DepartmentalEnquiryBD deBD = new DepartmentalEnquiryBD();
			SuspensionForm susForm = (SuspensionForm) form;
			String strDocumentId = request.getParameter("documentId");
			String strFunctionName = request.getParameter("functionName");
			// System.out.println("Print the ID " + strDocumentId);
			deBD.displayObjectDetails(response, strDocumentId, strFunctionName);
			// System.out.println("made a changes here for testing ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// System.out.println("aT LAST FORWARDING SOME WHERE" + FORWARD);

		return mapping.findForward(FORWARD);
	}

}
