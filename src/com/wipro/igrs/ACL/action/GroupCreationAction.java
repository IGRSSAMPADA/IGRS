/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupCreationAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the Action class for the Group Creation part of the ACL Module
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
public class GroupCreationAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("group");
	private Logger logger = (Logger) Logger
			.getLogger(GroupCreationAction.class);

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
		GroupForm rcForm = (GroupForm) form;
		ArrayList createdList = new ArrayList();
		ArrayList grpList = new ArrayList();
		session=request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  String pageName = request.getParameter("pageName");
		if (form != null) {

			String action = rcForm.getGrpcrtDTO().getGrpAction();
			logger.debug("Action values are.........." + action);
			RoleManagementBD bd = new RoleManagementBD();
			String pageNm=request.getParameter("pageName");
			if(request.getParameter("pageName")!=null){
				session.setAttribute("PAGENM", pageNm);
				}

			/*
			 * Action written to reset the Update Form to the default values
			 * same as at the time of the loading of page
			 */
			if(pageName != null && pageName.equalsIgnoreCase("createGroup"))
			{
				forwardJsp="group";
			}
			else{
			if (CommonConstant.GROUPS_CREAT_ACTION.equalsIgnoreCase(rcForm
					.getGrpcrtDTO().getGrpAction())) {
				try {
					int counter = 1;
					String temp = rcForm.getCombinedStr();
					String temp_[] = temp.split(",");
					logger.debug("length of temp ..........=" + temp_.length);

					for (int i = 0; i < temp_.length;) {
						String grpname = temp_[i];
						i++;
						String descGrp = temp_[i];
						i++;
//						String grpStatus = temp_[i];
//						i++;
					/*	if()
						{
							
						}
						else*/
						{	
						GroupDTO dto = new GroupDTO();
						dto.setGrpName(grpname);
						dto.setGrpDesc(descGrp);
//						if (grpStatus.equals("A")) {
//							dto.setStatus("Active");
//						} else if (grpStatus.equals("D")) {
//							dto.setStatus("Deactive");
//						}
						dto.setStatus("Pending Approval");
						dto.setSno(counter);
						counter++;
						createdList.add(dto);

						}	}
					grpList.addAll(createdList);
					rcForm.setGrpList(grpList);
					request.getSession()
							.setAttribute("GrpDetailsList", grpList);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				forwardJsp = CommonConstant.GROUPS_CREATE_SUCCESS;
			} else if (CommonConstant.RESET_ACTION.equalsIgnoreCase(rcForm
					.getGrpcrtDTO().getGrpAction())) {

				rcForm.getGrpcrtDTO().setGrpName(null);
				rcForm.getGrpcrtDTO().setGrpDesc(null);
				rcForm.setStatus(null);

				forwardJsp = CommonConstant.RESET_SUCCESS;
			} else if (CommonConstant.GROUPS_SUBMIT_ACTION
					.equalsIgnoreCase(rcForm.getGrpcrtDTO().getGrpAction())) {
				boolean insertFlag = false;

				ArrayList gList = new ArrayList((ArrayList) session
						.getAttribute("GrpDetailsList"));
				rcForm.setGrpList(gList);
				if (gList != null && gList.size() > 0) {
					insertFlag = bd.insertGroups(gList,roleId,funId,userId);
				}
				if (insertFlag) {
					logger.debug(" Data is inserted successfully");
					forwardJsp = CommonConstant.GROUPS_SUBMIT_SUCCESS;
				} else {
					logger.debug("cnt add data");
					String failureMsg="Retry";
					request.setAttribute("failMsg", failureMsg);
					forwardJsp = CommonConstant.GROUPS_CREATE_FAILURE;
				}

			} else if (CommonConstant.GROUPS_EDIT_ACTION
					.equalsIgnoreCase(rcForm.getGrpcrtDTO().getGrpAction())) {

				ArrayList gList = new ArrayList((ArrayList) session
						.getAttribute("GrpDetailsList"));

				if (gList != null && gList.size() > 0) {
					for (int d = 0; d < gList.size(); d++) {
						GroupDTO editDTO = (GroupDTO) gList.get(d);
						if (editDTO.getStatus().equalsIgnoreCase("Active")) {
							editDTO.setStatus("A");
						} else if (editDTO.getStatus().equalsIgnoreCase(
								"Deactive")) {
							editDTO.setStatus("D");
						}
						editDTO.getGrpName();
						editDTO.getGrpDesc();
						createdList.add(editDTO);
					}
					grpList.addAll(createdList);
					rcForm.setGrpList(grpList);

				}

				forwardJsp = CommonConstant.GROUPS_EDIT_SUCCESS;
			}
		}
		}
		return mapping.findForward(forwardJsp);
	}
}
