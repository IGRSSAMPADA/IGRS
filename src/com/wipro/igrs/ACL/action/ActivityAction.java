/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityAction.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the action class for the Activity part for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  24th MArch,2008 		 
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
import com.wipro.igrs.ACL.dto.ActivityDTO;
import com.wipro.igrs.ACL.form.ActivityForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 *
 */
public class ActivityAction extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("activity");
	private Logger logger = 
		(Logger) Logger.getLogger(ActivityAction.class);
	/** 
	 * Method execute for ACL-Role Creation.
	 * @param mapping 
	 * @param form 
	 * @param request 
	 * @param response 
	 * @param session 
	 * @return ActionForward
	 * @throws Exception 
	 */
	 
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) 
		throws Exception {
		   session=request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		ActivityForm aForm = (ActivityForm) form;
		ActivityDTO adto = aForm.getActDTO();
		if (form != null) {
			
			String action = aForm.getActDTO().getActionActivity();
			logger.debug("Action values are.........." +action);
			ArrayList retrList = new ArrayList();

			RoleManagementBD rolebd = new RoleManagementBD();
			
			ArrayList rList = rolebd.getDistinctRoles();
			logger.debug("List values are.........." +rList);
			aForm.setRoleList(rList);
			
			ArrayList actList = rolebd.getActivity();
			logger.debug("List values are.........." +actList);
				Collections.sort(actList);
				aForm.setActList(actList);
				
			forwardJsp = CommonConstant.ROLES_SUCCESS;
			
			
			if(CommonConstant.SUBMIT_ACTION.equalsIgnoreCase(aForm.getActDTO().getActionActivity())){
				String chosenRole = new String();
				chosenRole = aForm.getActDTO().getRoleName();
				
				request.getSession().setAttribute("role",chosenRole);
				
				adto.setRoleName(chosenRole);
			
			 forwardJsp = CommonConstant.ACTIVITY_RETRIEVE_SUCCESS;
			}	
		
			if(CommonConstant.SAVE_ACTION.equalsIgnoreCase(aForm.getActDTO().getActionActivity())){
			
				String temp = aForm.getSelected();
				String temp_[] = temp.split(",");
				logger.debug("length of selected ..........="+temp_.length);
				int i;
				int x=0;
				String[] arr = new String[temp_.length];
			
				String not = aForm.getNotSelected();
				String notArr[] = not.split(",");
				logger.debug("length of not selected ..........="+notArr.length);
				int e=0;
				int m;
				String[] arrN = new String[notArr.length];
				

				if(actList!=null){
					for( i= 0; i <temp_.length;i++)
					{
						arr[i]= temp_[i];
						x =Integer.parseInt(arr[i]);
						
						ActivityDTO tmpdto = (ActivityDTO)actList.get(x);
						String activity = tmpdto.getActivityName();
						logger.debug("Selected Activity is        "+activity);
							aForm.getActDTO().setSlctFlag("Y");
							tmpdto.setSlctFlag("Y");
							retrList.add(tmpdto);
						
					}
				
						for( m = 0; m <notArr.length;m++)
						{
							arrN[m]= notArr[m];
							if(!"".equals(arrN[m]))
							{
								e=Integer.parseInt(arrN[m]);
								ActivityDTO ndto = (ActivityDTO)actList.get(e);
								String activity = ndto.getActivityName();
								logger.debug("Not selected Activity is         "+activity);
									aForm.getActDTO().setSlctFlag("N");
									ndto.setSlctFlag("N");
									retrList.add(ndto);
							 }	
						}
					
				Collections.sort(retrList); 
				aForm.setActList(retrList);
				request.getSession().setAttribute("activityList",retrList);
				}
				
			forwardJsp = CommonConstant.CONFIRM_ACT_SUCCESS;
		}
			
			else if(CommonConstant.RESET_ACTION.equalsIgnoreCase(aForm.getActDTO().getActionActivity())){
				aForm.getActDTO().setRoleName(null);
				
				 forwardJsp = CommonConstant.RESET_SUCCESS;
			}	
			else if(CommonConstant.ACTIVITY_SUBMIT_ACTION.equalsIgnoreCase(aForm.getActDTO().getActionActivity())){
				String role = aForm.getRole();
				logger.debug("role----------------->   "+role);
				 
				ArrayList menuArray = new ArrayList((ArrayList) session.getAttribute("activityList"));
				if(menuArray!=null  && menuArray.size()>0){
					boolean insertFlag = rolebd.insertRolesActivity(role,menuArray,roleId,funId,userId);
				}	
				forwardJsp = CommonConstant.ACTIVITY_SUCCESS;
			 } 
		}
		return mapping.findForward(forwardJsp);
	}
}
