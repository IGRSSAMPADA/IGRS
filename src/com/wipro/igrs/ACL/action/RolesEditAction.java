/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RolesEditAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class to edit the role details.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  2nd April,2008 		 
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
import com.wipro.igrs.ACL.dto.RoleCreationDTO;
import com.wipro.igrs.ACL.form.RoleCreationForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class RolesEditAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = CommonConstant.ROLE_EXTRACT_SUCCESS;
	private static final Logger logger = Logger
			.getLogger(RolesEditAction.class);

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
		try {
			RoleCreationForm rcForm = (RoleCreationForm) form;
			session = request.getSession();
			String loggedInRole = (String) session.getAttribute("loggedInRole");
			String funId = (String) session.getAttribute("functionId");
			String userId = (String) session.getAttribute("UserId");
			
			String pageNm=request.getParameter("pageName");
			logger.debug("Page Name in Approval Link:" + pageNm);
			if(request.getParameter("pageName")!=null){
			session.setAttribute("PAGENM", pageNm);
			}
			String pStatus="P";
			
			
			if (form != null) {
				logger.debug("Action :   Inside Action Class");
				String actionRC = rcForm
						.getRolecrtDTO().getActionRC();
				String action = actionRC;
				logger.debug("Action values are.........." + action);
				RoleManagementBD roleDelegate = new RoleManagementBD();
				ArrayList rolesList = new ArrayList();

				
				
				if(pageNm!=null){
					if("approveRole".equals(pageNm)){
						rolesList = roleDelegate.getStoredRoles(pStatus);
					}
				}
				else {
					rolesList = roleDelegate.getStoredRoles();
				}
				
				logger.debug(" Data is retrived successfully");
				if (rolesList != null) {
					rcForm.setDetailsList(rolesList);
					forwardJsp = CommonConstant.ROLE_RETRIEVE_SUCCESS;
				} else {
					logger.debug("Data can not be retrieved");
					forwardJsp = CommonConstant.ROLE_RETRIEVE_FAILURE;
				}
				String refID = request.getParameter("refId");
				if (request.getParameter("refId") != null) {
					RoleCreationDTO sesDto = roleDelegate.getRolesDetails(refID);
					sesDto.setOfficeListing(null);
					sesDto.setActivitycount(Integer.parseInt(roleDelegate.getActivityCount(refID)));
					if (sesDto != null && sesDto.getSno() != 0) {
					sesDto.setOfficeListing(roleDelegate.getOfficeListing());
					
						logger.debug(" Data is retrieved successfully");
						session.setAttribute("roleid", loggedInRole);
						rcForm.setRolecrtDTO(sesDto);
						session.setAttribute("roleCrtForm", rcForm);
						forwardJsp = CommonConstant.ROLE_EXTRACT_SUCCESS;
					}
				} else if (CommonConstant.RESET_ACTION.equalsIgnoreCase(actionRC)) {

					rcForm.getRolecrtDTO().setRoleName("");
					rcForm.getRolecrtDTO().setRolDesc("");
					rcForm.getRolecrtDTO().setStatus("");
					rcForm.getRolecrtDTO().setRoleOfficeMapping(new String[]{});
					rcForm.setStatus("");
					session.setAttribute("roleCrtForm", rcForm);
					forwardJsp = CommonConstant.RESET_SUCCESS;
				}

				else if (CommonConstant.ROLES_UPDATE_ACTION
						.equalsIgnoreCase(actionRC)) {
					RoleCreationDTO dtoObject = rcForm.getRolecrtDTO();
					boolean flag = roleDelegate.updateRole(dtoObject, funId, userId, loggedInRole);
					
					if (flag) {
						logger.debug(" Data is inserted successfully");
						forwardJsp = CommonConstant.ROLES_UPDATE_SUCCESS;
					} else {
						logger.debug("cnt add data");
						String failureMsg="Retry";
						request.setAttribute("failMsg", failureMsg);
						forwardJsp = "roleUpdateFailure";
					}
					//TODO show message on screen based on save flag
					
					//roleDelegate.updateRoles(rolname, descRol, roleStatus, rId, roleId1, funId, userId)
//					roleId = rcForm.getId();
//					logger.debug("value of id from form--------->" + roleId);
//					String temp = rcForm.getTemp();
//					String temp_[] = temp.split(",");
//					logger.debug("length of temp ..........=" + temp_.length);
//					for (int i = 0; i < temp_.length;) {
//						String rolname = temp_[i];
//						i++;
//						String descRol = temp_[i];
//						i++;
//						String roleStatus = temp_[i];
//						i++;
//
//						if (rolname != null && descRol != null
//								&& roleStatus != null) {
//
//							roleDelegate.updateRoles(rolname, descRol, roleStatus,
//									roleId, roleId, funId, userId);
//						} else
//							logger.debug("No details updated");
//					}
					
					//forwardJsp = CommonConstant.ROLES_UPDATE_SUCCESS;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return mapping.findForward(forwardJsp);
	}
	
	public void printRequestParams(HttpServletRequest request) {
		try {
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
