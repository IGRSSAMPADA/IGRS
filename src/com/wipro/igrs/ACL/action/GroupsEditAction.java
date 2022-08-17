/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupsEditAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class to edit the groups details.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  3nd April,2008 		 
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
public class GroupsEditAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("extractRoleSuccess");
	private Logger logger = (Logger) Logger.getLogger(GroupsEditAction.class);

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
		GroupForm grpForm = (GroupForm) form;
		GroupDTO rdto = grpForm.getGrpcrtDTO();
		  session=request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  
		  String pStatus="P";
		  
		  String pageNm=request.getParameter("pageName");
		  if(request.getParameter("pageName")!=null){
				session.setAttribute("PAGENM", pageNm);
				}

		if (form != null) {

			logger.debug("Action :   Inside Action Class");

			String action = grpForm.getGrpcrtDTO().getGrpAction();
			logger.debug("Action values are.........." + action);

			RoleManagementBD bd = new RoleManagementBD();
			ArrayList Listgrps = new ArrayList();
			
			if(pageNm!=null){
				if("approveRole".equals(pageNm)){
					Listgrps = bd.getStoredGroups(pStatus);
				}
			}
			else {
			Listgrps = bd.getStoredGroups();
			}

			if (Listgrps != null) {
				grpForm.setGrpList(Listgrps);
				logger.debug(" Data is retrived successfully");
				forwardJsp = CommonConstant.GROUPS_RETRIEVE_SUCCESS;
			}

			String grpId = request.getParameter("grpId");

			GroupDTO sesDto = bd.getGroupDetails(grpId);

			if (sesDto != null && sesDto.getSno() != 0) {
				logger.debug(" Data is retrieved successfully");
				String id = sesDto.getGrpId();
				session.setAttribute("groupID", id);

				logger.debug("value from session----------->"
						+ session.getAttribute("groupID"));
				grpForm.setGrpcrtDTO(sesDto);
				forwardJsp = CommonConstant.GROUPS_EXTRACT_SUCCESS;
			}

			if (CommonConstant.RESET_ACTION.equalsIgnoreCase(grpForm
					.getGrpcrtDTO().getGrpAction())) {

				grpForm.getGrpcrtDTO().setGrpName(null);
				grpForm.getGrpcrtDTO().setGrpDesc(null);
				grpForm.setStatus(null);

				forwardJsp = CommonConstant.RESET_SUCCESS;
			}

			if (CommonConstant.GROUPS_UPDATE_ACTION.equalsIgnoreCase(grpForm
					.getGrpcrtDTO().getGrpAction())) {

				grpId = grpForm.getGrpid();

				logger.debug("value of id from form--------->" + grpId);
				int counter = 1;
				String temp = grpForm.getCombinedStr();
				String temp_[] = temp.split(",");

				logger.debug("length of temp ..........=" + temp_.length);

				for (int i = 0; i < temp_.length;) {
					String gname = temp_[i];
					i++;
					String gdesc = temp_[i];
					i++;
					String gStatus = temp_[i];
					i++;

					if (gname != null && gdesc != null && gStatus != null) {
						boolean updated = bd.updateGroup(gname, gdesc, gStatus,
								grpId,roleId,funId,userId);
						
						if(updated){
						//request.setAttribute("failMsg", null);	
						forwardJsp = CommonConstant.GROUPS_UPDATE_SUCCESS;
						}
						else {
							String failureMsg="Retry";
							request.setAttribute("failMsg", failureMsg);
							forwardJsp = "grpEditFailure";
						}
					} 
//					else
//						logger.debug("No details updated");
//						forwardJsp = "grpEditFailure";
						

				}
				
			}
		}
		return mapping.findForward(forwardJsp);
	}
}
