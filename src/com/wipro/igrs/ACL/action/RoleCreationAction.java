/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ACLConstants.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  24th MArch,2008 		 
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
public class RoleCreationAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("role");
	private Logger logger = (Logger) Logger.getLogger(BaseAction.class);

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
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		if (form != null) {
			RoleCreationForm rcForm = (RoleCreationForm) form;
			String action = rcForm.getRolecrtDTO().getActionRC();
			logger.debug("Action values are.........." + action);

			RoleManagementBD bd = new RoleManagementBD();
			ArrayList createdRlList = new ArrayList();
			ArrayList rrrList = new ArrayList();
			String pageNm=request.getParameter("pageName");
			if(request.getParameter("pageName")!=null){
				session.setAttribute("PAGENM", pageNm);
				}

			/*
			 * Action written to reset the Update Password Form to the default
			 * values same as at the time of the loading of page
			 */
			if(request.getParameter("pageName")!=null && request.getParameter("pageName").equalsIgnoreCase("createRole"))
			{
				forwardJsp="role";
			}
			else if (CommonConstant.ROLE_CREAT_ACTION.equalsIgnoreCase(rcForm
					.getRolecrtDTO().getActionRC())) {
				try {
					int counter = 1;
					String equFlag="";
					String temp = rcForm.getTemp();
					String temp_[] = temp.split(",");
					logger.debug("length of temp ..........=" + temp_.length);
					for(int i=0;i<temp_.length;i=i+2)
					{
						for(int j=i+2;j<temp_.length;j=j+2)
						{
							if(temp_[i].equalsIgnoreCase(temp_[j]))
							{
								equFlag="equal";
							}
						}
					}
					if(equFlag.equalsIgnoreCase("equal"))
					{
						rcForm.getRolecrtDTO().setEqualRoleFlag("equal");
						forwardJsp = CommonConstant.ROLE_CREATE_FAILURE;
					}else{
					
					for (int i = 0; i < temp_.length;) {
						String rolname = temp_[i];
						i++;
						String descRol = temp_[i];
						i++;
						
//						String roleStatus = temp_[i];
//						i++;

						RoleCreationDTO dto = new RoleCreationDTO();
						dto.setRoleName(rolname);
						
						dto.setRolDesc(descRol);
//						if (roleStatus.equalsIgnoreCase("P")) {
//							dto.setStatus("Active");
//						} else if (roleStatus.equalsIgnoreCase("D")) {
//							dto.setStatus("Deactive");
//						}
						dto.setStatus("Pending Approval");
						dto.setSno(counter);
						counter++;
						createdRlList.add(dto);

					}
					
					rrrList.addAll(createdRlList);
					rcForm.setDetailsList(rrrList);
					request.getSession().setAttribute("RolesDetailsList",
							rrrList);

					forwardJsp = CommonConstant.ROLE_CREATE_SUCCESS;
				} }catch (Exception ex) {
					ex.printStackTrace();
				}
			} else if (CommonConstant.RESET_ACTION.equalsIgnoreCase(rcForm
					.getRolecrtDTO().getActionRC())) {

				rcForm.getRolecrtDTO().setRoleName(null);
				rcForm.getRolecrtDTO().setRolDesc(null);
				rcForm.setStatus(null);

				forwardJsp = CommonConstant.RESET_SUCCESS;
			} else if (CommonConstant.ROLES_SUBMIT_ACTION
					.equalsIgnoreCase(rcForm.getRolecrtDTO().getActionRC())) {
				boolean insertFlag = false;
				boolean recExist=false;
				ArrayList rList = new ArrayList((ArrayList) session
						.getAttribute("RolesDetailsList"));
				if (rList != null && rList.size() > 0) {
					/*String value="";
				value= select role_name from table where role_name= ?
				if(value.equalIgnoreCase(dt.getRoleName))
					{
						forwardJsp = CommonConstant.ROLE_CREATE_FAILURE;
					}
				else{
					
					insertFlag = bd.insertRoles(rList,roleId,funId,userId);
					
				}*/
					
					recExist=bd.extractRolesNames(rList);
					if(recExist){
					insertFlag = bd.insertRoles(rList,roleId,funId,userId);
					}/*else{
						rcForm.getRolecrtDTO().setEqualRoleFlag("redundant");
					}*/
				}
				if (insertFlag) {
					logger.debug(" Data is inserted successfully");
					forwardJsp = CommonConstant.ROLES_SUBMIT_SUCCESS;
				} else {
					logger.debug("cnt add data");
					String failureMsg="Retry";
					request.setAttribute("failMsg", failureMsg);
					forwardJsp = CommonConstant.ROLE_CREATE_FAILURE;
				}

			} else if (CommonConstant.ROLES_EDIT_ACTION.equalsIgnoreCase(rcForm
					.getRolecrtDTO().getActionRC())) {

				ArrayList rList = new ArrayList((ArrayList) session
						.getAttribute("RolesDetailsList"));

				if (rList != null && rList.size() > 0) {
					for (int d = 0; d < rList.size(); d++) {
						RoleCreationDTO editDTO = (RoleCreationDTO) rList
								.get(d);
						if (editDTO.getStatus().equalsIgnoreCase("Active")) {
							editDTO.setStatus("P");
						} else if (editDTO.getStatus().equalsIgnoreCase(
								"Deactive")) {
							editDTO.setStatus("D");
						}
						editDTO.getRoleName();
						editDTO.getRolDesc();
						createdRlList.add(editDTO);
					}
					rrrList.addAll(createdRlList);
					rcForm.setDetailsList(rrrList);

				}

				forwardJsp = CommonConstant.ROLES_EDIT_SUCCESS;
			}
		}
		return mapping.findForward(forwardJsp);
	}
}
