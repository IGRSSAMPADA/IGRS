/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ReligionMasterAction.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
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

import com.wipro.igrs.adminConfig.bd.ReligionMasterBD;
import com.wipro.igrs.adminConfig.dto.ReligionMasterDTO;
import com.wipro.igrs.adminConfig.form.ReligionMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ReligionMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(ReligionMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside ReligionMasterAction class...");
	//	HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		String forward = "ReligionMaster";
		String userId1 = null;
		userId1 = "ADMIN";
		ReligionMasterForm relForm = (ReligionMasterForm) form;
		ReligionMasterBD relbd = new ReligionMasterBD();
		ReligionMasterDTO dto = new ReligionMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "ReligionMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList   relList = relbd.getReligionList();
				dto.setReligionList(relList);
				forward = "ReligionMasterView";
			}
		}
		if (relForm.getReligionPageName() != null) {
			if (relForm.getReligionPageName()
					.equalsIgnoreCase("ReligionCreate")) {
				if (relForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[4];
					params[0] = relForm.getReligionName();
					params[1] = relForm.getReligionDesc();
					params[3] = userId1;
					params[2] = relForm.getReligionStatus();

					boolean flag = relbd.addReligionMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (relForm.getReligionPageName().equalsIgnoreCase(
						"ReligionUpdate")) {
					if (relForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = relForm.getReligiondto().getReligionName();
						params[1] = relForm.getReligiondto().getReligionDesc();
						params[3] = userId1;

						params[2] = relForm.getReligiondto()
								.getReligionStatus();
						params[4] = relForm.getReligiondto().getReligionId();
						boolean flag = relbd.updateReligionmaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String relid = request.getParameter("religionId");
		if (relid != null) {
			forward = "ReligionMasterUpdate";
			dto = relbd.getReligionIdList(relid);
		}
		relForm.setReligiondto(dto);

		session.setAttribute("religionList", relForm.getReligiondto()
				.getReligionList());
		session.setAttribute("religionMasterForm", relForm);
		relForm.setReligionPageName(null);
		return mapping.findForward(forward);
	}
}
