/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PartyMasterAction.java
 * Author		:	vengamamba
 * Date			: 	17/06/2008
 */
package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.PartyMasterBD;
import com.wipro.igrs.adminConfig.dto.PartyMasterDTO;
import com.wipro.igrs.adminConfig.form.PartyMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class PartyMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(PartyMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside MohallaMasterAction class...");
		String forward = "PartyMaster";
		//HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");

		String userId1 = null;
		userId1 = "ADMIN";
		PartyMasterForm partyForm = (PartyMasterForm) form;
		PartyMasterBD partybd = new PartyMasterBD();
		PartyMasterDTO dto = new PartyMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "PartyMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList  partyList = partybd.getPartyList();
				dto.setPartyList(partyList);
				forward = "PartyMasterView";
			}
		}
		if (partyForm.getPartyPageName() != null) {
			if (partyForm.getPartyPageName().equalsIgnoreCase("PartyCreate")) {
				if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[4];
					params[0] = partyForm.getPartyName();
					params[1] = partyForm.getPartyDesc();
					params[3] = userId1;
					params[2] = partyForm.getPartyStatus();

					boolean flag = partybd.addPartyMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (partyForm.getPartyPageName()
						.equalsIgnoreCase("PartyUpdate")) {
					if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = partyForm.getPartydto().getPartyName();
						params[1] = partyForm.getPartydto().getPartyDesc();
						params[3] = userId1;

						params[2] = partyForm.getPartydto().getPartyStatus();
						params[4] = partyForm.getPartydto().getPartyId();
						boolean flag = partybd.updatePartymaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String partyid = request.getParameter("partyId");
		if (partyid != null) {
			forward = "PartyMasterUpdate";
			dto = partybd.getPartyIdList(partyid);
		}
		partyForm.setPartydto(dto);

		session.setAttribute("partyList", partyForm.getPartydto()
				.getPartyList());
		session.setAttribute("partyMasterForm", partyForm);
		partyForm.setPartyPageName(null);
		return mapping.findForward(forward);
	}
}
