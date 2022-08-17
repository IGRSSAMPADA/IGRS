/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.groupmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.groupmaster.bd.GroupBD;
import com.wipro.igrs.groupmaster.dto.GroupDTO;
import com.wipro.igrs.groupmaster.form.GroupForm;

public class GroupAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(GroupAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in GroupAction");
		String target = "success";
		/* Populate the group form */
		GroupForm groupForm = (GroupForm) form;
		GroupBD groupbd = new GroupBD();
		GroupDTO dto = new GroupDTO();
		ArrayList groupList = groupbd.getGroupList();

		/* Add Rolegroup master */

		if (groupForm.getRgrouppageName() != null)
			if (groupForm.getRgrouppageName().equalsIgnoreCase("groupcreate")) {
				groupbd.addGroupmaster(groupForm);
			} else {
				/* Update Role master */
				groupbd.updateGroupmaster(groupForm);
			}

		/* Setting Role Master Listitems */
		dto.setGroupList(groupList);

		/* Parameter roleId */
		String groupid = request.getParameter("rgroupId");
		logger.debug("group id in action.." + groupid);
		if (groupid != null) {
			dto = groupbd.getGroupId(groupid);
		}
		groupForm.setDto(dto);
		/* Set the sessions */

		session.setAttribute("groupList", groupForm.getDto().getGroupList());
		session.setAttribute("GroupForm", groupForm);

		return mapping.findForward(target);
	}
}
