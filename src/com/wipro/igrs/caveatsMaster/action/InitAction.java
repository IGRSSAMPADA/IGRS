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
import com.wipro.igrs.caveatsMaster.dao.CaveatsDAO;
import com.wipro.igrs.caveatsMaster.dto.CaveatsDTO;
import com.wipro.igrs.caveatsMaster.form.CaveatsForm;

public class InitAction extends BaseAction {
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
		logger.debug("WE ARE IN InitAction Debug");
		logger.info("WE ARE IN  InitAction INFO");
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO cDto = new CaveatsDTO();
			cDto = fm.getCaveatsDTO();
			String FORWAD_SUCCESS = "";
			String actionName = cDto.getActionName();
			CaveatsDAO refDAO = new CaveatsDAO();
			if ("create".equalsIgnoreCase(request.getParameter("pageName"))) {
				if (actionName == null || "".equalsIgnoreCase(actionName)) {
					logger.info("Inside Country, CaveatList Action ");
					cDto.setCaveatTypeList(refDAO.createCaveatsList());
					cDto.setCountryMasterList(refDAO.countryList());
				} else if ("State".equalsIgnoreCase(actionName)) {
					logger.info("Inside State Action");
					cDto.setStateMasterList(refDAO.stateList(cDto
							.getCountryId()));
					cDto.setDistrictMasterList(null);
				} else if ("District".equalsIgnoreCase(actionName)) {
					logger.info("Inside District Action");
					cDto.setDistrictMasterList(refDAO.districtStack(cDto
							.getStateId()));
				}
				session.setAttribute("caveatfrm", fm);
				FORWAD_SUCCESS = "CreateScreen";
			} else if ("modify".equalsIgnoreCase(request
					.getParameter("pageName"))) {
				fm.setCaveatsDTO((CaveatsDTO) session
						.getAttribute("suppleDetails"));
				cDto.setCaveatTypeList(refDAO.createCaveatsList());
				cDto.setCountryMasterList(refDAO.countryList());
				cDto.setStateMasterList(refDAO.stateList(cDto.getCountryId()));
				cDto.setDistrictMasterList(refDAO.districtStack(cDto
						.getStateId()));

				session.setAttribute("frwdedByEdit", "yes");
				FORWAD_SUCCESS = "CreateScreen";
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("Failure");
		}
	}
}
