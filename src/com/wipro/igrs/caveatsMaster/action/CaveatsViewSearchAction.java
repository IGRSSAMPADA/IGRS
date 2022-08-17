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

public class CaveatsViewSearchAction extends BaseAction {
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
		logger.debug("WE ARE IN CaveatsViewSearchAction Debug");
		logger.info("WE ARE IN  CaveatsViewSearchAction INFO");
		String FORWAD_SUCCESS = "failure";
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO cDTO = fm.getCaveatsDTO();
			CaveatsDelegate cBD = new CaveatsDelegate();
			ArrayList viewSearchlist = new ArrayList();
			if ("view".equalsIgnoreCase(request.getParameter("pageName"))) {
				cDTO = cBD.searchByRefID(request.getParameter("referenceID"));
				session.setAttribute("propTxnNo",
						cDTO.getPropertyTxnId());
				fm.setCaveatsDTO(cDTO);
				FORWAD_SUCCESS = "refId";
			} else {
				logger.info("Inside Search by All other fields");

				viewSearchlist = cBD.searchByAll(cDTO.getReferenceIDSearch(),
						cDTO.getRegistrationNoSearch(), cDTO.getFlag(), cDTO
								.getFromDate(), cDTO.getToDate());
				fm.getCaveatsDTO().setErrorMsg((String) viewSearchlist.get(0));
				session.setAttribute("viewSearchlist",
						viewSearchlist);
				FORWAD_SUCCESS = "regNo";
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("faliure");
		}
	}
}
