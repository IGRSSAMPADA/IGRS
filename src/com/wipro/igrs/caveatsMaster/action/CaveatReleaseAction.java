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

public class CaveatReleaseAction extends BaseAction {
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
		logger.debug("WE ARE IN CaveatReleaseAction Debug");
		logger.info("WE ARE IN  CaveatReleaseAction INFO");
		try {
			CaveatsForm fm = (CaveatsForm) form;
			CaveatsDTO dtoRelease = fm.getCaveatsDTO();
			CaveatsDelegate cavBD = new CaveatsDelegate();
			ArrayList releaseSearchList = new ArrayList();
			String FORWAD_SUCCESS = "failure";
			String refID = request.getParameter("referenceID");
			if (refID != null)
				dtoRelease.setReferenceID(refID);
			refID = dtoRelease.getReferenceID();
			if (!(refID.equalsIgnoreCase("") || refID == null)) {
				logger.info("Inside REFID");
				dtoRelease = cavBD.releaseCaveat(refID);
				if (dtoRelease == null) {
					dtoRelease = fm.getCaveatsDTO();
					dtoRelease.setErrorMsg("Error: Refernce ID is not found");
					releaseSearchList.add(dtoRelease);
					dtoRelease.setRecordsBuffer(releaseSearchList);
					request.setAttribute("releaseList", fm);
					FORWAD_SUCCESS = "regNo";
				} else {
					fm.setCaveatsDTO(dtoRelease);
					FORWAD_SUCCESS = "refId";
				}
			} else if (true) {
				logger.info("Inside Global Search");
				releaseSearchList = cavBD.releaseSearchByAll(dtoRelease
						.getReferenceID(), dtoRelease.getRegistrationNumber(),
						dtoRelease.getFromDate(), dtoRelease.getToDate());
				fm.getCaveatsDTO().setErrorMsg(
						(String) releaseSearchList.get(0));
				fm.getCaveatsDTO().setRecordsBuffer(releaseSearchList);
				request.setAttribute("releaseList", fm);
				FORWAD_SUCCESS = "regNo";
			}
			return mapping.findForward(FORWAD_SUCCESS);
		} catch (Exception e) {
			logger.error(e);
			return mapping.findForward("Failure");
		}
	}
}
