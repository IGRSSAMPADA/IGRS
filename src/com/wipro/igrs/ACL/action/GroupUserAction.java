/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupUserAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class for the Group user part for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  31st MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.ACL.bd.RoleManagementBD;
import com.wipro.igrs.ACL.dto.GroupsUserDTO;
import com.wipro.igrs.ACL.form.GroupUserForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class GroupUserAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("grpUser");
	private Logger logger = (Logger) Logger.getLogger(GroupUserAction.class);

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
		 session=request.getSession();
	      String roleId = (String)session.getAttribute("loggedInRole");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		  String pageName = request.getParameter("pageName");
		GroupUserForm userForm = (GroupUserForm) form;
		GroupsUserDTO adto = userForm.getGrpUserDTO();
		if (form != null) {

			String action = userForm.getGrpUserDTO().getGrpUserAction();
			logger.debug("Action values are.........." + action);
			ArrayList retrList = new ArrayList();
			RoleManagementBD rolebd = new RoleManagementBD();
			ArrayList grpList = rolebd.getDistinctGrps();
			boolean isempAvail=false;
			logger.debug("List values are.........." + grpList);
			//Collections.sort(grpList);
			userForm.setGrpList(grpList);
			request.setAttribute("userGroupList",grpList);

			forwardJsp = CommonConstant.GRP_USER_SUCCESS;
			if(pageName != null && pageName.equalsIgnoreCase("createRolesUser"))
			{
				forwardJsp="grpUser";
			}
			else{
			if (CommonConstant.SUBMIT_ACTION.equalsIgnoreCase(userForm
					.getGrpUserDTO().getGrpUserAction())) {
				String chosenEmp = new String();
				
				if(userForm.getGrpUserDTO().getEmpName()!=null){
				chosenEmp = userForm.getGrpUserDTO().getEmpName().trim();
				}
				else {
					chosenEmp="";
				}
				//
				isempAvail=rolebd.checkEmployeeAvail(chosenEmp);
				if(isempAvail){
				adto.setEmpName(chosenEmp);
				request.getSession().setAttribute("user", chosenEmp);
				
				logger.debug("Selected Emp ID: " + chosenEmp);
				String grpID=rolebd.getUserRoleGrpMapping(chosenEmp);
				request.setAttribute("userGroupId", grpID);
				logger.debug("group ID  For user is: " + grpID);

				forwardJsp = CommonConstant.GRP_USER_RETRIEVE_SUCCESS;
				}
				else {
					
					forwardJsp=CommonConstant.GRP_USER_RETRIEVE_FAILURE;
					request.setAttribute("isempAvail", "false");
					
				}
			}

			if (CommonConstant.SAVE_ACTION.equalsIgnoreCase(userForm
					.getGrpUserDTO().getGrpUserAction())) {

				String temp = userForm.getSelected();
				String temp_[] = null;
				String notArr[] = null;
				if(temp.length() > 0){
					temp_ = temp.split(",");
					logger.debug("length of selected ..........=" + temp_.length);
				}				
				int i;
				int x = 0;

				String not = userForm.getNotSelected();
				if(not.length() > 0){
					notArr = not.split(",");
					logger.debug("length of not selected ..........="
							+ notArr.length);
				}
				int e = 0;
				int m;

				if (grpList != null) {
					if(temp_ != null){
						String[] arr = new String[temp_.length];		
					for (i = 0; i < temp_.length; i++) {
						arr[i] = temp_[i];
						x = Integer.parseInt(arr[i]);

						GroupsUserDTO gpdto = (GroupsUserDTO) grpList.get(x);
						String grpName = gpdto.getGroupName();
						logger.debug("Selected Group is        " + grpName);
						userForm.getGrpUserDTO().setSlctFlag("Y");
						gpdto.setSlctFlag("Y");
						retrList.add(gpdto);
						}
					}
					if(notArr != null){
						String[] arrN = new String[notArr.length];
					for (m = 0; m < notArr.length; m++) {
						arrN[m] = notArr[m];
						e = Integer.parseInt(arrN[m]);
						GroupsUserDTO ndto = (GroupsUserDTO) grpList.get(e);
						String grpName = ndto.getGroupName();
						logger
								.debug("Not selected Group is         "
										+ grpName);
						userForm.getGrpUserDTO().setSlctFlag("N");
						ndto.setSlctFlag("N");
						retrList.add(ndto);
					}
					}
//					Collections.sort(retrList);
					userForm.setGrpList(retrList);
					request.getSession().setAttribute("groupList", retrList);
				}

				forwardJsp = CommonConstant.CONFIRM_ACT_SUCCESS;
			} else if (CommonConstant.GRP_USER_SUBMIT_ACTION
					.equalsIgnoreCase(userForm.getGrpUserDTO()
							.getGrpUserAction())) {
				String userID = userForm.getUserNo();
				boolean insertFlag = false;
				logger.debug("role----------------->   " + userID);

				ArrayList menuArray = new ArrayList((ArrayList) session
						.getAttribute("groupList"));
				//rolebd.isEmpExist(userID);
				
				if (menuArray != null && menuArray.size() > 0) {
//					boolean boo=false;
//					
//					boo=rolebd.isEmpExist(userID);
//					if(boo){
//					rolebd.deleteEmp(userID);
//					insertFlag = rolebd
//								.insertGrpUserActivity(userID, menuArray,roleId,funId,userId);
//					}
//					else {
					insertFlag = rolebd
								.insertGrpUserActivity(userID, menuArray,roleId,funId,userId);
//					}
					forwardJsp = CommonConstant.GRP_USER_SUBMIT_SUCCESS;
				}
			}
		}
		}
		return mapping.findForward(forwardJsp);
	}
}
