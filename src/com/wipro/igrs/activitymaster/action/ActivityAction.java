/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.activitymaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.activitymaster.bd.ActivityBD;
import com.wipro.igrs.activitymaster.dto.ActivityDTO;
import com.wipro.igrs.activitymaster.form.ActivityForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ActivityAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ActivityAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in ActivityAction");
		String target = "success";
		/* Populate the activity form */
		ActivityForm activityForm = (ActivityForm) form;
		ActivityDTO dto = new ActivityDTO();
		ActivityBD activitybd = new ActivityBD();
		ArrayList activityList = activitybd.getActivityList();

		if (activityForm.getPageName() != null)
			if (activityForm.getPageName().equalsIgnoreCase("activecreate")) {
				/* Add activity master */
				activitybd.addActivitymaster(activityForm);
			} else {
				/* Update activity master */
				activitybd.updateActivitymaster(activityForm);
			}

		/* Setting activity master List items */
		dto.setActivityList(activityList);

		/* Parameter activityId */
		String activeid = request.getParameter("activityId");
		logger.debug("activity id in action.." + activeid);
		if (activeid != null) {
			dto = activitybd.getActivityId(activeid);
		}
		activityForm.setDto(dto);
		/* Set the sessions */

		session.setAttribute("activityList", activityForm.getDto()
				.getActivityList());
		session.setAttribute("ActivityForm", activityForm);

		return mapping.findForward(target);
	}
}
