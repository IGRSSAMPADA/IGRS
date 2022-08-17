package com.wipro.igrs.caveatsMaster.action;

import java.io.IOException;
import java.util.ArrayList;

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

public class CaveatsOttAction extends BaseAction {
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

	private Logger logger = (Logger) Logger.getLogger(CaveatsOttAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws IOException, ServletException, Exception {
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO dtoOtt = fm.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();
			//HttpSession requestSession = request.getSession(true);
			String FORWAD_SUCCESS = "failure";

			ArrayList list = new ArrayList();
			list = cavBD.searchForPin(dtoOtt.getRegistrationNumber(), dtoOtt
					.getPinNumber());
			if (list == null || list.isEmpty()) {
				logger.info("List is Empty");
			} else {
				dtoOtt = cavBD.insertott(dtoOtt.getRegistrationNumber(), dtoOtt
						.getPinNumber());
			}
			if (dtoOtt != null) {
				fm.setCaveatsDTO(dtoOtt);
				FORWAD_SUCCESS = "success";
			}
			session.setAttribute("result", (Object) list);
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("failure");
		}
	}
}
