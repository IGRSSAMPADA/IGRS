package com.wipro.igrs.caveatsMaster.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.caveatsMaster.bd.CaveatsDelegate;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.form.CaveatsForm;

public class CaveatReleaseDetailAction extends BaseAction {
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
	private Logger logger = (Logger) Logger
			.getLogger(CaveatActionConfirm.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		logger.debug("WE ARE IN CaveatReleaseDetailAction Debug");
		logger.info("WE ARE IN  CaveatReleaseDetailAction INFO");
		String FORWAD_SUCCESS;
		try {
			CaveatsDelegate cavBD = new CaveatsDelegate();
			CaveatsForm fb = (CaveatsForm) form;
			CaveatsDTO dtoRelease = fb.getCaveatsDTO();
			String loggedIn = (String) session.getAttribute(
					"Logged_In");
			if (loggedIn == null || "".equalsIgnoreCase(loggedIn))
				loggedIn = "igrs";
			dtoRelease.setLoggedIn(loggedIn);
			String refID = request.getParameter("referenceID");
			if (refID != null)
				dtoRelease.setReferenceID(refID);
			boolean log = cavBD.updateFlag(dtoRelease);
			if (log) {
				FORWAD_SUCCESS = "success";
			} else {
				FORWAD_SUCCESS = "faliure";
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("Failure");
		}
	}
}
