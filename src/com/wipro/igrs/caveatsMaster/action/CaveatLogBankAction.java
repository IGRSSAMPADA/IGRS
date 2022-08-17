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

public class CaveatLogBankAction extends BaseAction {
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
		logger.debug("WE ARE IN CaveatLogBankAction Debug");
		logger.info("WE ARE IN  CaveatLogBankAction INFO");
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO dtoOtt = fm.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();

			String FORWAD_SUCCESS = "failure";

			ArrayList list = new ArrayList();
			String regno = dtoOtt.getRegistrationNumber();
			String ott = dtoOtt.getOttNumber();
			list = cavBD.searchForOtt(regno, ott);
			logger.info(" Resultant List " + list);
			if (list == null || list.isEmpty()) {
				dtoOtt.setErrorMsg("Registration Number not found.");
				fm.getCaveatsDTO().setRegistrationNumber(
						fm.getCaveatsDTO().getRegistrationNumber());
			} else
				FORWAD_SUCCESS = "success";
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("failure");
		}
	}
}
