/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RolesGroupAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class for the Roles Group part for ACL Module.
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
import com.wipro.igrs.ACL.dto.RolesGroupDTO;
import com.wipro.igrs.ACL.form.RolesGroupForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class RolesGroupAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("rolesGrp");
	private Logger logger = (Logger) Logger.getLogger(RolesGroupAction.class);

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
	
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		RolesGroupForm rgrpForm = (RolesGroupForm) form;
		RolesGroupDTO adto = rgrpForm.getRgrpDTO();
		String pageName = request.getParameter("pageName");
		if (form != null) {

			String action = rgrpForm.getRgrpDTO().getActionRolesGrp();
			logger.debug("Action values are.........." + action);
			ArrayList retrList = new ArrayList();
			RoleManagementBD rolebd = new RoleManagementBD();

			ArrayList rgrpList = rolebd.getDistinctRGrps();
			logger.debug("List values are.........." + rgrpList);
			rgrpForm.setGrpList(rgrpList);

			ArrayList rolesList = rolebd.getNewRoles();
			logger.debug("List values are.........." + rolesList);
			Collections.sort(rolesList);
			rgrpForm.setRolesList(rolesList);
			request.setAttribute("rolesList", rolesList);
			

			forwardJsp = CommonConstant.ROLES_GRP_SUCCESS;
			if(pageName != null && pageName.equalsIgnoreCase("createRoleGroup"))
			{
				forwardJsp="rolesGrp";
			}
			else{
			if (CommonConstant.SUBMIT_ACTION.equalsIgnoreCase(rgrpForm
					.getRgrpDTO().getActionRolesGrp())) {
				String chosenRGrp = new String();
				String chosenGrpID=new String();
				chosenRGrp = rgrpForm.getRgrpDTO().getGrpName();
				chosenGrpID=rolebd.getRoleGrpID(chosenRGrp);
				logger.debug("chosen Role Grp: " + chosenGrpID);
				ArrayList grpRoleList=rolebd.extractRole(chosenGrpID);
				//Collections.sort(grpRoleList);
				rgrpForm.setGrpRoleList(grpRoleList);
				logger.debug("Grp Role Size is:: " + rgrpForm.getGrpRoleList().size());
				request.setAttribute("grpRoleList", grpRoleList);
				
				//rgrpForm.getRolesList().retainAll(rgrpForm.getGrpRoleList());
				
				adto.setGrpName(chosenRGrp);

				request.getSession().setAttribute("groupName", chosenRGrp);

				forwardJsp = CommonConstant.ROLES_GRP_RETRIEVE_SUCCESS;
			}

			if (CommonConstant.SAVE_ACTION.equalsIgnoreCase(rgrpForm
					.getRgrpDTO().getActionRolesGrp())) {
				String temp = rgrpForm.getSelected();
				String temp_[] = null;
				if(temp.length() > 0){
					temp_ = temp.split(",");
					logger.debug("length of selected ..........=" + temp_.length);
				}				
				int i;
				int x = 0;
				
				String notArr[] = null;
				String not = rgrpForm.getNotSelected();
				
				if(not.length() > 0){
					notArr = not.split(",");
					logger.debug("length of not selected ..........="
							+ notArr.length);
				}
				int e = 0;
				int m;
				
				if (rolesList != null) {
					if(temp_ != null){
						String[] arr = new String[temp_.length];		
					for (i = 0; i < temp_.length; i++) {
						arr[i] = temp_[i];
						x = Integer.parseInt(arr[i]);

						RolesGroupDTO tmpdto = (RolesGroupDTO) rolesList.get(x);
						String roles = tmpdto.getRoleName();
						logger.debug("Selected Role is        " + roles);
						rgrpForm.getRgrpDTO().setSlctFlag("Y");
						tmpdto.setSlctFlag("Y");
						retrList.add(tmpdto);
					}
					}	
					if(notArr != null){
					String[] arrN = new String[notArr.length];
					for (m = 0; m < notArr.length; m++) {
						arrN[m] = notArr[m];
						e = Integer.parseInt(arrN[m]);
						RolesGroupDTO ndto = (RolesGroupDTO) rolesList.get(e);
						String roles = ndto.getRoleName();
						logger.debug("Selected Role is        " + roles);
						rgrpForm.getRgrpDTO().setSlctFlag("N");
						ndto.setSlctFlag("N");
						retrList.add(ndto);
					}
					}	
					Collections.sort(retrList);
					rgrpForm.setRolesList(retrList);
					request.getSession().setAttribute("rolesList", retrList);
				}
				forwardJsp = CommonConstant.CONFIRM_ACT_SUCCESS;
			}

			if (CommonConstant.RESET_ACTION.equalsIgnoreCase(rgrpForm
					.getRgrpDTO().getActionRolesGrp())) {

				forwardJsp = CommonConstant.RESET_SUCCESS;
			} else if (CommonConstant.ROLE_GRP_SUBMIT_ACTION
					.equalsIgnoreCase(rgrpForm.getRgrpDTO().getActionRolesGrp())) {
				String group = rgrpForm.getGroup();
				logger.debug("group in session----------------->   " + group);
				boolean flag =rolebd.deleteExistingEntries(group);
				ArrayList menuArray = new ArrayList((ArrayList) session
						.getAttribute("rolesList"));
				if (menuArray != null && menuArray.size() > 0) {
					boolean insertFlag = rolebd
							.insertGrpRoles(group, menuArray);

				}
				forwardJsp = CommonConstant.ROLE_GRP_SUCCESS;
			}
		}
		}
		return mapping.findForward(forwardJsp);
	}
}
