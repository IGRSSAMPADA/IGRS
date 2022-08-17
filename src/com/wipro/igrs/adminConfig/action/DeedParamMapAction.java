package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.DeedParamMapBD;
import com.wipro.igrs.adminConfig.dto.DeedParamMapDTO;
import com.wipro.igrs.adminConfig.form.DeedParamMapForm;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * ===========================================================================
 * File : DeedMasterAction .java Description : Represents the deed screen Action
 * Class Author : vengamamba P Created Date : Apr 21, 2008
 * 
 * ===========================================================================
 */
public class DeedParamMapAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(DeedParamMapAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.debug("enter in to the DeedMasterAction");
		String forwardName = "success";
		DeedParamMapForm deedform = (DeedParamMapForm) form;
		DeedParamMapBD adBD = new DeedParamMapBD();

		ArrayList deedList = adBD.getDeedList();// getting
																	// list of
																	// moduleids

		ArrayList instList = new ArrayList();
		ArrayList exempList = new ArrayList();
		ArrayList paramList = new ArrayList();
		ArrayList operatorList = new ArrayList();
	//	  HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		String deed = request.getParameter("deed");
		if (deed != null) {
			instList = adBD.getInstrumentList(deed);

		}
		String instrument = request.getParameter("instrument");
		if (instrument != null) // if instrument value not null
		{
			if (!(instrument.equalsIgnoreCase(""))) {

				logger.debug("enter in to instrument" + instrument);
				exempList = adBD.getExemptionList(instrument, "inst");
			} else
				// if instrument value is space i.e,select option selected
				exempList = adBD.getExemptionList(deed, "deed");
		} else
			exempList = adBD.getExemptionList(deed, "deed");

		paramList = adBD.getParamList();
		operatorList = adBD.getOperatorList();

		DeedParamMapDTO dto = new DeedParamMapDTO();
		// setting list of values to deedids,instrumentids,exemptionids
		dto.setDeedIDList(deedList);
		dto.setInstrumentIDList(instList);
		dto.setExemptionIDList(exempList);
		dto.setParamIDList(paramList);
		dto.setOperatorIDList(operatorList);
		deedform.setDeeddto(dto);
		// if operation is save
		if ("MasterForm".equalsIgnoreCase(deedform.getFormName())) {
			String actionValue = deedform.getActionValue();

			// action belong to save button
			if ("Save".equalsIgnoreCase(actionValue)) {
				boolean iflag = adBD.addingData(deedform,roleId,funId,userId);
				if (iflag) {
					forwardName = "Save";
				}
			}
			deedform.setActionValue(null);
		}
		 session = request.getSession(true);
		session.setAttribute("deedform", deedform);
		return mapping.findForward(forwardName);

	}
}
