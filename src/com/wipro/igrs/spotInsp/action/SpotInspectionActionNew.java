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
package com.wipro.igrs.spotInsp.action;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.spotInsp.bd.SpotInspBD;
import com.wipro.igrs.spotInsp.dto.SpotInspNewDTO;
import com.wipro.igrs.spotInsp.form.SpotInspNewForm;
import com.wipro.igrs.UserRegistration.constant.CommonConstant;
import com.wipro.igrs.baseaction.action.BaseAction;

/**
 * @author neegaga
 * 
 */
public class SpotInspectionActionNew extends BaseAction {
	/**
	 * forwardJsp
	 */
	String forwardJsp = new String("rolesGrp");
	private Logger logger = (Logger) Logger.getLogger(SpotInspectionActionNew.class);

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

	
		//SpotInspNewDTO adto = spForm.getSpotDTO();
		String pageName = request.getParameter("pageName");
		String fnName = request.getParameter("fnName");
		String pageTitleView = request.getParameter("pageTitleView");
		String language = (String)session.getAttribute("languageLocale");
		if (form != null) {
			SpotInspNewForm spForm = (SpotInspNewForm) form;
			spForm.setLanguage(language);
			if(pageTitleView!=null&&pageTitleView.equals("AdminCriteria"))
			{
				spForm.getSpotDTO().setActionName("");
                    spForm.getSpotDTO().setActivityId(null);
					spForm.setSelectedActivityList(new ArrayList());
					spForm.setActivityList(new ArrayList());
					spForm.setInsertflag("");
					spForm.setGroup("0");
					forwardJsp = "spotAdminCriteriaCreation";
				
			}
			
			if(pageTitleView!=null&&pageTitleView.equals("AdminCriteriaView"))
			{		spForm.getSpotDTO().setActionName("");
                    spForm.getSpotDTO().setActivityId(null);
					spForm.setSelectedActivityList(new ArrayList());
					spForm.setActivityList(new ArrayList());
					forwardJsp = "spotAdminCriteriaView";
			}
		
		
			String action = spForm.getSpotDTO().getActionName();
			logger.debug("Action values are.........." + action);
			
			spForm.setFlag("");
			logger.debug("Flag......" + spForm.getFlag());
			ArrayList retrList = new ArrayList();
			SpotInspBD spotbd = new SpotInspBD();
			spForm.setSpotAdminList(spotbd.getActivityList(spForm.getLanguage()));
			spForm.setSpotAdminList(spotbd.getActivityList(spForm.getLanguage()));
			logger.debug("List values are.........." + spForm.getSpotAdminList());
			logger.debug("List values are.........." + spForm.getSpotAdminList().size());
		
			
			
			if(pageTitleView!=null&&pageTitleView.equals("AdminCriteriaView"))
			{
                    spForm.getSpotDTO().setActivityId(null);
					spForm.setSelectedActivityList(new ArrayList());
					spForm.setActivityList(new ArrayList());
					forwardJsp = "spotAdminCriteriaView";
				
				
			}
			logger.debug("		spForm.setActivityList(selectedActivityList);" + 		spForm.getActivityList().size());
	
			
			if (CommonConstant.SUBMIT_ACTION.equalsIgnoreCase(spForm.getSpotDTO().getActionName())) {
				spForm.setInsertflag("");
				String selectedActivity = new String();
				String selectedActivityId=new String();
			//	spForm.getSpotDTO().setActivityName("Deed Instrument Criteria");
				selectedActivity = spForm.getSpotDTO().getActivityId();
				logger.debug("selectedActivityId " + selectedActivity);
			if (selectedActivity!=null&&selectedActivity.equals("1")){
				ArrayList selectedActivityList=spotbd.getdeedInstrumentList(spForm.getLanguage());
				ArrayList currentActivityList=spotbd.getcurrentdeedInstrumentList(spForm.getLanguage());
				spForm.setSelectedActivityList(selectedActivityList);
				spForm.setActivityList(currentActivityList);
				logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
				request.setAttribute("activityList", selectedActivityList);
				logger.debug("Activity  List Size is :currentActivityList: " + currentActivityList.size());
				logger.debug("Activity  List Size is:  activityList: " +selectedActivityList.size());
				request.setAttribute("currentActivityList", currentActivityList);
				request.getSession().setAttribute("groupName", selectedActivity);
				spForm.setGroup("1");
				forwardJsp ="spotAdminCriteriaCreation";
			}
			if (selectedActivity!=null&&selectedActivity.equals("2")){
				ArrayList selectedActivityList=spotbd.getSubClauseList(spForm.getLanguage());
				spForm.setSelectedActivityList(selectedActivityList);
				ArrayList currentActivityList=spotbd.getCurrentSubClauseList(spForm.getLanguage());
				spForm.setSelectedActivityList(selectedActivityList);
				spForm.setActivityList(currentActivityList);
				logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
				request.setAttribute("activityList", selectedActivityList);
				request.setAttribute("currentActivityList", currentActivityList);
				request.getSession().setAttribute("groupName", selectedActivity);
				spForm.setActivityList(selectedActivityList);
				spForm.setGroup("2");
				forwardJsp ="spotAdminCriteriaCreation";
			}
			if (selectedActivity!=null&&selectedActivity.equals("3")){
				ArrayList selectedActivityList=spotbd.getpropertyList(spForm.getLanguage());
				ArrayList currentActivityList=spotbd.getCurrentpropertyList(spForm.getLanguage());
				spForm.setSelectedActivityList(selectedActivityList);
				spForm.setActivityList(currentActivityList);
				logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
				request.setAttribute("activityList", selectedActivityList);
				request.setAttribute("currentActivityList", currentActivityList);
				request.getSession().setAttribute("groupName", selectedActivity);
				spForm.setActivityList(selectedActivityList);
				spForm.setGroup("3");
				forwardJsp ="spotAdminCriteriaCreation";
			}
			if (selectedActivity!=null&&selectedActivity.equals("4")){
				ArrayList selectedActivityList=spotbd.getAreaList(spForm.getLanguage());
				ArrayList currentActivityList=spotbd.getCurrentAreaList(spForm.getLanguage());
				spForm.setSelectedActivityList(selectedActivityList);
				spForm.setActivityList(currentActivityList);
				logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
				request.setAttribute("activityList", selectedActivityList);
				request.setAttribute("currentActivityList", currentActivityList);
				request.getSession().setAttribute("groupName", selectedActivity);
				spForm.setActivityList(selectedActivityList);
				spForm.setGroup("4");
				forwardJsp ="spotAdminCriteriaCreation";
			}
			if (selectedActivity!=null&&selectedActivity.equals("5")){
				ArrayList selectedActivityList=spotbd.getRangeList();
				ArrayList currentActivityList=spotbd.getCurrentRangeList();
				spForm.setSelectedActivityList(selectedActivityList);
				spForm.setActivityList(currentActivityList);
				logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
				request.setAttribute("activityList", selectedActivityList);
				request.setAttribute("currentActivityList", currentActivityList);
				request.getSession().setAttribute("groupName", selectedActivity);
				spForm.setActivityList(selectedActivityList);
				spForm.setGroup("5");
				forwardJsp ="spotAdminCriteriaCreation";
			}
						
	/*		if (CommonConstant.RESET_ACTION.equalsIgnoreCase(spForm
					.getRgrpDTO().getActionRolesGrp())) {

				forwardJsp = CommonConstant.RESET_SUCCESS;
			} else if (CommonConstant.ROLE_GRP_SUBMIT_ACTION
					.equalsIgnoreCase(spForm.getRgrpDTO().getActionRolesGrp())) {
				String group = spForm.getGroup();
				logger.debug("group in session----------------->   " + group);

				ArrayList menuArray = new ArrayList((ArrayList) session
						.getAttribute("activityList"));
				if (menuArray != null && menuArray.size() > 0) {
					boolean insertFlag = rolebd
							.insertGrpRoles(group, menuArray);

				}
				forwardJsp = CommonConstant.ROLE_GRP_SUCCESS;
			}
		}
		} */
			
	
			}	
			
			if (CommonConstant.VIEW_ACTION.equalsIgnoreCase(spForm.getSpotDTO().getActionName())) {
				String selectedActivity = new String();
				String selectedActivityId=new String();
			//	spForm.getSpotDTO().setActivityName("Deed Instrument Criteria");
				selectedActivity = spForm.getSpotDTO().getActivityId();
				logger.debug("selectedActivityId " + selectedActivity);
		
				if (selectedActivity!=null&&selectedActivity.equals("1")){
			
				ArrayList currentActivityList=spotbd.getcurrentdeedInstrumentList(spForm.getLanguage());
			    request.setAttribute("currentActivityList", currentActivityList);
				spForm.setGroup("1");
				forwardJsp ="spotAdminCriteriaView";
			}
			if (selectedActivity!=null&&selectedActivity.equals("2")){
				ArrayList currentActivityList=spotbd.getCurrentSubClauseList(spForm.getLanguage());
				 request.setAttribute("currentActivityList", currentActivityList);
					spForm.setGroup("2");
					forwardJsp ="spotAdminCriteriaView";
			}
			if (selectedActivity!=null&&selectedActivity.equals("3")){
			ArrayList currentActivityList=spotbd.getCurrentpropertyList(spForm.getLanguage());
				 request.setAttribute("currentActivityList", currentActivityList);
					spForm.setGroup("3");
					forwardJsp ="spotAdminCriteriaView";
			}
			if (selectedActivity!=null&&selectedActivity.equals("4")){
				ArrayList currentActivityList=spotbd.getCurrentAreaList(spForm.getLanguage());
				 request.setAttribute("currentActivityList", currentActivityList);
					spForm.setGroup("4");
					forwardJsp ="spotAdminCriteriaView";		
			}
			if (selectedActivity!=null&&selectedActivity.equals("5")){
				ArrayList currentActivityList=spotbd.getCurrentRangeList();
				 request.setAttribute("currentActivityList", currentActivityList);
					spForm.setGroup("5");
					forwardJsp ="spotAdminCriteriaView";		
			}
			forwardJsp = "spotAdminCriteriaView";
			}

			logger.debug(" fgjhfjhfjgfgjf"+CommonConstant.SAVE_ACTION.equalsIgnoreCase(spForm.getSpotDTO().getActionName()));
		
			if (CommonConstant.SAVE_ACTION.equalsIgnoreCase(spForm.getSpotDTO().getActionName())) {
				
				logger.debug("group name is" + spForm.getGroup());
				String temp = spForm.getSelected();
				String temp_[] = null;
				if(temp.length() > 0){
					temp_ = temp.split(",");
					logger.debug("length of selected ..........=" + temp_.length);
				}				
				int i;
				int x = 0;
				
				String notArr[] = null;
				String not = spForm.getNotSelected();
		
				logger.debug("     selected  "+spForm.getNotSelected());
				
				
				if(not.length() > 0){
					notArr = not.split(",");
					logger.debug("length of not selected ..........="
							+ notArr.length);
				}
				int e = 0;
				int m;
		ArrayList activityList=  new ArrayList();
		

		activityList=spForm.getSelectedActivityList();		
		logger.debug("Selected activity size is        " + activityList.size());
				
		
		if (activityList != null) {
		
			if(temp_ != null){
		try{
				String[] arr = new String[temp_.length];		
					for (i = 0; i < temp_.length; i++) {
						arr[i] = temp_[i];
						x = Integer.parseInt(arr[i]);

						SpotInspNewDTO tmpdto = (SpotInspNewDTO) activityList.get(x);
						String roles = tmpdto.getSelectedId();
						logger.debug("Selected value is        " + roles);
						spForm.getSpotDTO().setSelectFlag("Y");
						tmpdto.setSelectFlag("Y");
						retrList.add(tmpdto);
					}
		}catch(Exception ae){
			System.out.print(""+ae);
		}	
		}	
			try{
					if(notArr != null){
					String[] arrN = new String[notArr.length];
					for (m = 0; m < notArr.length; m++) {
						arrN[m] = notArr[m];
						e = Integer.parseInt(arrN[m]);
						SpotInspNewDTO ndto = (SpotInspNewDTO) activityList.get(e);
						String roles = ndto.getSelectedId();
						logger.debug("Selected criteria is        " + roles);
						spForm.getSpotDTO().setSelectFlag("N");
						ndto.setSelectFlag("N");
						retrList.add(ndto);
					}
					}	
									
		}catch(Exception ae)
		{
			System.out.print(""+ae);
		}	

		if (retrList != null && retrList.size() > 0) {
				boolean insertFlag = spotbd.insertCriteriaActivity(spForm.getGroup(),retrList);
				if(insertFlag)
				{
					if(spForm.getLanguage()!=null && spForm.getLanguage().equalsIgnoreCase("en"))
					spForm.setInsertflag("Data Updated Succesfully");
					else
						spForm.setInsertflag("डेटा सफलतापूर्वक अपडेट किया गया");
					
				}else
				{
					if(spForm.getLanguage()!=null && spForm.getLanguage().equalsIgnoreCase("en"))
					spForm.setInsertflag("Please try again as data is not updated succesfully");
					else
						spForm.setInsertflag("डेटा सफलतापूर्वक अपडेट नही हो पाया");
				}
				logger.debug("Selected criteria inserted      " + insertFlag);
			}
		
		if(spForm.getGroup().equals("1"))
		{
		ArrayList currentActivityList=spotbd.getcurrentdeedInstrumentList(spForm.getLanguage());
		spForm.setActivityList(currentActivityList);
		}


		if (spForm.getGroup().equals("1")){
			ArrayList selectedActivityList=spotbd.getdeedInstrumentList(spForm.getLanguage());
			ArrayList currentActivityList=spotbd.getcurrentdeedInstrumentList(spForm.getLanguage());
			spForm.setSelectedActivityList(selectedActivityList);
			spForm.setActivityList(currentActivityList);
			logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
			request.setAttribute("activityList", selectedActivityList);
			logger.debug("Activity  List Size is :currentActivityList: " + currentActivityList.size());
			logger.debug("Activity  List Size is:  activityList: " +selectedActivityList.size());
			request.setAttribute("currentActivityList", currentActivityList);
	
		}
		if (spForm.getGroup().equals("2")){
			ArrayList selectedActivityList=spotbd.getSubClauseList(spForm.getLanguage());
			spForm.setSelectedActivityList(selectedActivityList);
			ArrayList currentActivityList=spotbd.getCurrentSubClauseList(spForm.getLanguage());
			spForm.setSelectedActivityList(selectedActivityList);
			spForm.setActivityList(currentActivityList);
			logger.debug("Activity  List Size is:: " + spForm.getSelectedActivityList().size());
			request.setAttribute("activityList", selectedActivityList);
			request.setAttribute("currentActivityList", currentActivityList);
			forwardJsp ="spotAdminCriteriaCreation";
				}
		if (spForm.getGroup().equals("3")){
			ArrayList selectedActivityList=spotbd.getpropertyList(spForm.getLanguage());
			ArrayList currentActivityList=spotbd.getCurrentpropertyList(spForm.getLanguage());
			spForm.setSelectedActivityList(selectedActivityList);
			spForm.setActivityList(currentActivityList);
			request.setAttribute("activityList", selectedActivityList);
			request.setAttribute("currentActivityList", currentActivityList);
			forwardJsp ="spotAdminCriteriaCreation";
		}
		if (spForm.getGroup().equals("4")){
			ArrayList selectedActivityList=spotbd.getAreaList(spForm.getLanguage());
			ArrayList currentActivityList=spotbd.getCurrentAreaList(spForm.getLanguage());
			spForm.setSelectedActivityList(selectedActivityList);
			spForm.setActivityList(currentActivityList);
			request.setAttribute("activityList", selectedActivityList);
			request.setAttribute("currentActivityList", currentActivityList);
			forwardJsp ="spotAdminCriteriaCreation";
		}
		if (spForm.getGroup().equals("5")){
			ArrayList selectedActivityList=spotbd.getRangeList();
			ArrayList currentActivityList=spotbd.getCurrentRangeList();
			spForm.setSelectedActivityList(selectedActivityList);
			spForm.setActivityList(currentActivityList);
			request.setAttribute("activityList", selectedActivityList);
			request.setAttribute("currentActivityList", currentActivityList);
			forwardJsp ="spotAdminCriteriaCreation";
		}
	
		}			

		}
		}
return mapping.findForward(forwardJsp);
}
	}	