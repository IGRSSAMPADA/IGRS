/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupUpdationAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the Action class for updating the Groups in ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  28th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.bd.RoleManagementBD;
import com.wipro.igrs.ACL.dto.GroupDTO;
import com.wipro.igrs.ACL.form.GroupForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class GroupUpdationAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("grpUpdate");
	private Logger logger = (Logger) Logger
			.getLogger(GroupUpdationAction.class);

	/**
	 * Method execute for ACL-Role Creation.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		if (form != null) {
			GroupForm rcForm = (GroupForm) form;
			GroupDTO rdto = rcForm.getGrpcrtDTO();
			logger.debug("Action :   Inside Action Class");
			String action = rcForm.getGrpcrtDTO().getGrpAction();
			logger.debug("Action values are.........." + action);
			RoleManagementBD bd = new RoleManagementBD();
			ArrayList rolesList = new ArrayList();

			rolesList = bd.getStoredGroups();
			logger.debug(" Data is retrived successfully");
			if (rolesList != null) {
				rdto.setGroupsList(rolesList);
				forwardJsp = CommonConstant.GROUPS_RETRIEVE_SUCCESS;
			} else {
				logger.debug("Data can not be retrieved");
				forwardJsp = CommonConstant.GROUPS_RETRIEVE_FAILURE;
			}
			if (CommonConstant.RESET_ACTION.equalsIgnoreCase(rcForm
					.getGrpcrtDTO().getGrpAction())) {

				rcForm.getGrpcrtDTO().setGrpName(null);
				rcForm.getGrpcrtDTO().setGrpDesc(null);
				rcForm.setStatus(null);

				forwardJsp = CommonConstant.RESET_SUCCESS;
			}
		}
		return mapping.findForward(forwardJsp);
	}
}
