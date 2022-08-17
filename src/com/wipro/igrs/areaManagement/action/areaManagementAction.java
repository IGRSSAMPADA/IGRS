/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateAction.java
 * Author      :   Mallikarjun.M
 * Description :   Represents the Action Class for ACL.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.areaManagement.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.areaManagement.bd.areaManagementBD;
import com.wipro.igrs.areaManagement.dto.areaManagementDTO;
import com.wipro.igrs.areaManagement.form.areaManagementForm;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.report.common.MarketTrendReportConstants;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;



public class areaManagementAction extends BaseAction {
	String forward_Jsp;
	private Logger logger = (Logger) Logger.getLogger(areaManagementAction.class);
	ActionForward forward = null;
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		try{

		logger.info("we are in areaManagementAction");
		
		String frdName=request.getParameter("pageName");
		String funName=request.getParameter("funId");
		
		String linkViewName=request.getParameter("LinkView");

		String language = (String) session.getAttribute("languageLocale");
		String userId=(String) session.getAttribute("UserId");
		areaManagementForm eForm = (areaManagementForm) form;
		areaManagementDTO dto = eForm.getAppdto();
		areaManagementBD areaManagebd = new areaManagementBD();
		String activityid = request.getParameter("actid");
		String lang = (String)session.getAttribute("languageLocale");
        String officeId = (String)session.getAttribute("loggedToOffice");
		IGRSCommon save =  new IGRSCommon();
		dto.setLogic("");
		dto.setLinkClick("");
		dto.setNowAdd("");
		eForm.getAppdto().setErrorFlag("");
		
		if(frdName!=null)
		{
			if(frdName.equalsIgnoreCase("create") && funName.equalsIgnoreCase("FUN_500"))
			 {
			
				 logger.debug("sucess");
				 eForm.setActionName(null);
				 eForm.getAppdto().setType("");
				 eForm.setStateList(new ArrayList());
				 eForm.setDistrictList(new ArrayList());
				 save.saveactivitylog(userId, activityid);
				 dto.setLogicBtn("all");
				 dto.setNowAdd("");
				 eForm.setPageType("");
				 frdName="";
				 forward_Jsp="createarea";
			 }
			if(frdName.equalsIgnoreCase("Construct") && funName.equalsIgnoreCase("FUN_500"))
			 {
			
				 logger.debug("sucess");
				 eForm.setActionName(null);
				 eForm.getAppdto().setType("");
				 eForm.setStateList(new ArrayList());
				 eForm.setDistrictList(new ArrayList());
				 eForm.setConstructionRatesList(new ArrayList());
				 dto.setRcc41("");
				 dto.setRcc42("");
				 dto.setRcc43("");
				 dto.setRcc44("");
				 dto.setRccOthers("");
				 dto.setRbc41("");
				 dto.setRbc42("");
				 dto.setRbc43("");
				 dto.setRbc44("");
				 dto.setRbcOthers("");
				 dto.setTin41("");
				 dto.setTin42("");
				 dto.setTin43("");
				 dto.setTin44("");
				 dto.setTinOthers("");
				 dto.setKabelu41("");
				 dto.setKabelu42("");
				 dto.setKabelu43("");
				 dto.setKabelu44("");
				 dto.setKabeluOthers("");
				 dto.setLgdCode("");
				 save.saveactivitylog(userId, activityid);
				 eForm.setAppdto(areaManagebd.getConstRate(dto));
				 dto.setLogicBtn("all");
				 dto.setNowAdd("");
				 eForm.setPageType("");
				 frdName="";
				 forward_Jsp="construct";
			 }
		 if(frdName.equalsIgnoreCase("Edit") && funName.equalsIgnoreCase("FUN_500"))
		 {
			 
			 dto.setLogicBtn("all");
			 eForm.getAppdto().setType("");
			 eForm.setStateList(new ArrayList());
			 eForm.setDistrictList(new ArrayList());
			 eForm.setActionName(null);
			 dto.setLogicBtn("all");
			 eForm.setPageType("");
			 dto.setClickRadio("");
			 save.saveactivitylog(userId, activityid);
			 
			 forward_Jsp="editarea";
		 }
		 
		 if(frdName.equalsIgnoreCase("delete") && funName.equalsIgnoreCase("FUN_500"))
		 {
			 
			 dto.setLogicBtn("all");
			 eForm.getAppdto().setType("");
			 eForm.setStateList(new ArrayList());
			 eForm.setDistrictList(new ArrayList());
			 eForm.setActionName(null);
			 eForm.setPageType("");
			 dto.setLogicBtn("all");
			 save.saveactivitylog(userId, activityid);
			 
			 forward_Jsp="deletearea";
		 }
		 if(frdName.equalsIgnoreCase("ViewPDF") && funName.equalsIgnoreCase("FUN_500"))
		 {
			 
			 dto.setLogicBtn("all");
			 eForm.getAppdto().setType("");
			 eForm.setStateList(new ArrayList());
			 eForm.setDistrictList(new ArrayList());
			 eForm.getAppdto().setDistrictId("");
				eForm.getAppdto().setDistrictName("");
				eForm.getAppdto().setTehsilListId("");
				eForm.getAppdto().setTehsilListName("");
				eForm.getAppdto().setAreaId("");
				eForm.getAppdto().setAreaName("");
				eForm.getAppdto().setSubAreaListId("");
				eForm.getAppdto().setSubAreaListName("");
				eForm.getAppdto().setWardListId("");
				eForm.getAppdto().setWardListName("");
				eForm.getAppdto().setMohallaListId("");
				eForm.getAppdto().setMohallaListName("");
				eForm.getAppdto().setSubClauseListId("");
				eForm.getAppdto().setSubClauseListName("");
				eForm.getAppdto().setMohallaDesc("");
				eForm.getAppdto().setMohallaHindiName("");
				eForm.getAppdto().setMohallaName("");
				eForm.setTehsilList(new ArrayList());
				eForm.setAreaList(new ArrayList());
				eForm.setSubareaList(new ArrayList());
				eForm.setWardList(new ArrayList());
				eForm.setMohallaList(new ArrayList());
				eForm.setSubClauseList(new ArrayList());
			 ArrayList statelist = new ArrayList();
			 statelist = areaManagebd.getState(lang,eForm);
			eForm.setStateList(statelist);
			String StateId= eForm.getAppdto().getStateId(); 
			ArrayList districtlist = new ArrayList();
			districtlist = areaManagebd.getDistrict(lang,StateId,officeId);
			eForm.setDistrictList(districtlist);
			 eForm.setActionName(null);
			 eForm.setPageType("");
			 dto.setLogicBtn("all");
			 save.saveactivitylog(userId, activityid);
			 
			 forward_Jsp="ViewPDF";
		 }
		
		}
		
		if("CONST_RATES_UPDATE".equalsIgnoreCase(eForm.getActionName()))
		{
			
			boolean updateData = areaManagebd.updateConstData(eForm,userId);
			if(updateData)
			{
			logger.debug("Construction rates updated");
			 dto.setErrorFlag("ConstN");
			forward_Jsp="message";
			}
			
			else
			{
				logger.debug("Construction rates not updated");
				 dto.setErrorFlag("ConstY");
				forward_Jsp="message";
			}
			
		}
		
		
		if("CreateTehsil".equalsIgnoreCase(eForm.getActionName()))
		{
			
			eForm.getAppdto().setDistrictId("");
			eForm.getAppdto().setDistrictName("");
			eForm.getAppdto().setTehsilListId("");
			eForm.getAppdto().setTehsilListName("");
			eForm.getAppdto().setAreaId("");
			eForm.getAppdto().setAreaName("");
			eForm.getAppdto().setSubAreaListId("");
			eForm.getAppdto().setSubAreaListName("");
			eForm.getAppdto().setWardListId("");
			eForm.getAppdto().setWardListName("");
			eForm.getAppdto().setMohallaListId("");
			eForm.getAppdto().setMohallaListName("");
			eForm.getAppdto().setErrorFlag("");
			eForm.getAppdto().setTehsilDesc("");
			eForm.getAppdto().setTehsilHindiName("");
			eForm.getAppdto().setTehsilName("");
			eForm.getAppdto().setNowAdd("");
			eForm.setTehsilList(new ArrayList());
			
			
			eForm.setTehsilList(new ArrayList());
			eForm.setAreaList(new ArrayList());
			eForm.setSubareaList(new ArrayList());
			eForm.setWardList(new ArrayList());
			eForm.setMohallaList(new ArrayList());
			
			 ArrayList statelist = new ArrayList();
			 statelist = areaManagebd.getState(lang,eForm);
			eForm.setStateList(statelist);
			String StateId= eForm.getAppdto().getStateId(); 
			ArrayList districtlist = new ArrayList();
			districtlist = areaManagebd.getDistrict(lang,StateId,officeId);
			eForm.setDistrictList(districtlist);
			
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
			 
		}
		
		if("CreateSubArea".equalsIgnoreCase(eForm.getActionName()))
		{
			eForm.getAppdto().setDistrictId("");
			eForm.getAppdto().setDistrictName("");
			eForm.getAppdto().setTehsilListId("");
			eForm.getAppdto().setTehsilListName("");
			eForm.getAppdto().setAreaId("");
			eForm.getAppdto().setAreaName("");
			eForm.getAppdto().setSubAreaListId("");
			eForm.getAppdto().setSubAreaListName("");
			eForm.getAppdto().setWardListId("");
			eForm.getAppdto().setWardListName("");
			eForm.getAppdto().setMohallaListId("");
			eForm.getAppdto().setMohallaListName("");
			eForm.getAppdto().setMunciBodyListId("");
			eForm.getAppdto().setMunciBodyListName("");
			eForm.getAppdto().setErrorFlag("");
			eForm.getAppdto().setSubAreaName("");
			eForm.getAppdto().setSubAreaDesc("");
			eForm.getAppdto().setSubAreaHindiName("");
			eForm.getAppdto().setNowAdd("");
			eForm.setTehsilList(new ArrayList());
			eForm.setAreaList(new ArrayList());
			eForm.setSubareaList(new ArrayList());
			eForm.setWardList(new ArrayList());
			eForm.setMohallaList(new ArrayList());
			eForm.setMunicipalBodyList(new ArrayList());
			 ArrayList statelist = new ArrayList();
			 statelist = areaManagebd.getState(lang,eForm);
			eForm.setStateList(statelist);
			String StateId= eForm.getAppdto().getStateId();
			ArrayList districtlist = new ArrayList();
			districtlist = areaManagebd.getDistrict(lang,StateId,officeId);
			eForm.setDistrictList(districtlist);
			
			ArrayList municipalBodylist = new ArrayList();
			municipalBodylist = areaManagebd.getmunicipalBody(lang);
			eForm.setMunicipalBodyList(municipalBodylist);
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("CreateWard".equalsIgnoreCase(eForm.getActionName()))
		{
			eForm.getAppdto().setDistrictId("");
			eForm.getAppdto().setDistrictName("");
			eForm.getAppdto().setTehsilListId("");
			eForm.getAppdto().setTehsilListName("");
			eForm.getAppdto().setAreaId("");
			eForm.getAppdto().setAreaName("");
			eForm.getAppdto().setSubAreaListId("");
			eForm.getAppdto().setSubAreaListName("");
			eForm.getAppdto().setWardListId("");
			eForm.getAppdto().setWardListName("");
			eForm.getAppdto().setMohallaListId("");
			eForm.getAppdto().setMohallaListName("");
			eForm.getAppdto().setWardorPatwariListId("");
			eForm.getAppdto().setWardorPatwariListName("");
			eForm.getAppdto().setWardDesc("");
			eForm.getAppdto().setWardHindiName("");
			eForm.getAppdto().setWardName("");
			eForm.getAppdto().setErrorFlag("");
			eForm.getAppdto().setNowAdd("");
			eForm.setTehsilList(new ArrayList());
			eForm.setAreaList(new ArrayList());
			eForm.setSubareaList(new ArrayList());
			eForm.setWardList(new ArrayList());
			eForm.setMohallaList(new ArrayList());
			eForm.setWardorPatwariList(new ArrayList());
			
			 ArrayList statelist = new ArrayList();
			 statelist = areaManagebd.getState(lang,eForm);
			eForm.setStateList(statelist);
			String StateId= eForm.getAppdto().getStateId();
			ArrayList districtlist = new ArrayList();
			districtlist = areaManagebd.getDistrict(lang,StateId,officeId);
			eForm.setDistrictList(districtlist);
			
			ArrayList wardorPatwarilist = new ArrayList();
			wardorPatwarilist = areaManagebd.getwardorPatwari(lang,StateId);
			eForm.setWardorPatwariList(wardorPatwarilist);
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("CreateMohalla".equalsIgnoreCase(eForm.getActionName()))
		{
			eForm.getAppdto().setDistrictId("");
			eForm.getAppdto().setDistrictName("");
			eForm.getAppdto().setTehsilListId("");
			eForm.getAppdto().setTehsilListName("");
			eForm.getAppdto().setAreaId("");
			eForm.getAppdto().setAreaName("");
			eForm.getAppdto().setSubAreaListId("");
			eForm.getAppdto().setSubAreaListName("");
			eForm.getAppdto().setWardListId("");
			eForm.getAppdto().setWardListName("");
			eForm.getAppdto().setMohallaListId("");
			eForm.getAppdto().setMohallaListName("");
			eForm.getAppdto().setSubClauseListId("");
			eForm.getAppdto().setSubClauseListName("");
			eForm.getAppdto().setMohallaDesc("");
			eForm.getAppdto().setMohallaHindiName("");
			eForm.getAppdto().setMohallaName("");
			eForm.setTehsilList(new ArrayList());
			eForm.setAreaList(new ArrayList());
			eForm.setSubareaList(new ArrayList());
			eForm.setWardList(new ArrayList());
			eForm.setMohallaList(new ArrayList());
			eForm.setSubClauseList(new ArrayList());
			eForm.getAppdto().setNowAdd("");
			
			 ArrayList statelist = new ArrayList();
			 statelist = areaManagebd.getState(lang,eForm);
			 eForm.setStateList(statelist);
			
			String StateId= eForm.getAppdto().getStateId();
			ArrayList districtlist = new ArrayList();
			districtlist = areaManagebd.getDistrict(lang,StateId,officeId);
			eForm.setDistrictList(districtlist);
			
			ArrayList subClauselist = new ArrayList();
			subClauselist = areaManagebd.getsubClause(lang);
			eForm.setSubClauseList(subClauselist);
	
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setLogicBtn("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		
	 if("gettehsil".equalsIgnoreCase(eForm.getActionName()))
		{
		String distId= eForm.getAppdto().getDistrictId();
		String logicId= eForm.getAppdto().getType();
		ArrayList tehsillist = new ArrayList();
		tehsillist = areaManagebd.getTehsil(lang,distId);
		eForm.setTehsilList(tehsillist);
		eForm.getAppdto().setAreaId("");
		eForm.getAppdto().setAreaName("");
		eForm.getAppdto().setSubAreaListId("");
		eForm.getAppdto().setSubAreaListName("");
		eForm.getAppdto().setWardListId("");
		eForm.getAppdto().setWardListName("");
		eForm.getAppdto().setMohallaListId("");
		eForm.getAppdto().setMohallaListName("");
		eForm.getAppdto().setSubClauseListId("");
		eForm.getAppdto().setSubClauseListName("");
		eForm.getAppdto().setMohallaDesc("");
		eForm.getAppdto().setMohallaHindiName("");
		eForm.getAppdto().setMohallaName("");
		eForm.setAreaList(new ArrayList());
		eForm.setSubareaList(new ArrayList());
		eForm.setWardList(new ArrayList());
		eForm.setMohallaList(new ArrayList());
		if ("viewPDF".equalsIgnoreCase(eForm.getPageType()))
		 {
			 dto.setClickRadio("");
			 eForm.setPageType("");
			 forward_Jsp="ViewPDF";
		 }
		else
		{
		if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		}
		}
		else if("getarea".equalsIgnoreCase(eForm.getActionName()))
		{
		
		String tehId= eForm.getAppdto().getTehsilListId();
		if("".equalsIgnoreCase(tehId))
		{
			eForm.setAreaList(new ArrayList());
			eForm.setSubareaList(new ArrayList());
			eForm.setWardList(new ArrayList());
			eForm.setMohallaList(new ArrayList());
			eForm.getAppdto().setSubAreaListId("");
			eForm.getAppdto().setSubAreaListName("");
			eForm.getAppdto().setWardListId("");
			eForm.getAppdto().setWardListName("");
			eForm.getAppdto().setMohallaListId("");
			eForm.getAppdto().setMohallaListName("");
			eForm.getAppdto().setSubClauseListId("");
			eForm.getAppdto().setSubClauseListName("");
			eForm.getAppdto().setMohallaDesc("");
			eForm.getAppdto().setMohallaHindiName("");
			eForm.getAppdto().setMohallaName("");
			eForm.getAppdto().setAreaId("");
			eForm.getAppdto().setAreaName("");
			dto.setClickId("");
		}
		else
		{
		String logicId= eForm.getAppdto().getType();
		ArrayList arealist = new ArrayList();
		arealist = areaManagebd.getArea(lang,tehId,logicId);
		eForm.setAreaList(arealist);
		eForm.getAppdto().setSubAreaListId("");
		eForm.getAppdto().setSubAreaListName("");
		eForm.getAppdto().setWardListId("");
		eForm.getAppdto().setWardListName("");
		eForm.getAppdto().setMohallaListId("");
		eForm.getAppdto().setMohallaListName("");
		eForm.getAppdto().setSubClauseListId("");
		eForm.getAppdto().setSubClauseListName("");
		eForm.getAppdto().setMohallaDesc("");
		eForm.getAppdto().setMohallaHindiName("");
		eForm.getAppdto().setMohallaName("");
		eForm.setSubareaList(new ArrayList());
		eForm.setWardList(new ArrayList());
		eForm.setMohallaList(new ArrayList());
		
		eForm.getAppdto().setAreaId("");
		dto.setClickId("");
		}
		if ("viewPDF".equalsIgnoreCase(eForm.getPageType()))
		 {
			 dto.setClickRadio("");
			 eForm.setPageType("");
			 forward_Jsp="ViewPDF";
		 }
		else
		{
		if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if(eForm.getAreaList()!=null)
				{
					dto.setNowAdd("SUBAREAY");
				}
				else
				{
					dto.setNowAdd("");
				}
				
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		}
		}
		
		else if ("getsubarea".equalsIgnoreCase(eForm.getActionName()))
		{
		
		String areaId= eForm.getAppdto().getAreaId();
		String tehId= eForm.getAppdto().getTehsilListId();
		ArrayList subarealist = new ArrayList();
		subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
		eForm.setSubareaList(subarealist);
		eForm.getAppdto().setWardListId("");
		eForm.getAppdto().setWardListName("");
		eForm.getAppdto().setMohallaListId("");
		eForm.getAppdto().setMohallaListName("");
		eForm.getAppdto().setSubClauseListId("");
		eForm.getAppdto().setSubClauseListName("");
		eForm.getAppdto().setMohallaDesc("");
		eForm.getAppdto().setMohallaHindiName("");
		eForm.getAppdto().setMohallaName("");
		eForm.setWardList(new ArrayList());
		eForm.setMohallaList(new ArrayList());
		if ("viewPDF".equalsIgnoreCase(eForm.getPageType()))
		 {
			 dto.setClickRadio("");
			 eForm.setPageType("");
			 forward_Jsp="ViewPDF";
		 }
		else
		{
		if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		}
		}
		
		else if ("getward".equalsIgnoreCase(eForm.getActionName()))
		{
		
		String areaId= eForm.getAppdto().getAreaId();
		String tehId= eForm.getAppdto().getTehsilListId();
		String subareaId= eForm.getAppdto().getSubAreaListId();
		ArrayList Wardlist = new ArrayList();
		Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
		eForm.setWardList(Wardlist);
		eForm.getAppdto().setMohallaListId("");
		eForm.getAppdto().setMohallaListName("");
		eForm.getAppdto().setSubClauseListId("");
		eForm.getAppdto().setSubClauseListName("");
		eForm.getAppdto().setMohallaDesc("");
		eForm.getAppdto().setMohallaHindiName("");
		eForm.getAppdto().setMohallaName("");
		eForm.setMohallaList(new ArrayList());
		if ("viewPDF".equalsIgnoreCase(eForm.getPageType()))
		 {
			 dto.setClickRadio("");
			 eForm.setPageType("");
			 forward_Jsp="ViewPDF";
		 }
		else
		{
		if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
		{
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		}
		}
		
	/*	else if ("getmohalla".equalsIgnoreCase(eForm.getActionName()))
		{
		String areaId= eForm.getAppdto().getAreaId();
		String tehId= eForm.getAppdto().getTehsilListId();
		String subareaId= eForm.getAppdto().getSubAreaListId();
		String wardId= eForm.getAppdto().getWardListId();
		ArrayList Mohallalist = new ArrayList();
		Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
		eForm.setMohallaList(Mohallalist);
		}*/
		

		
		
		
		if("TEHSIL_VIEW".equalsIgnoreCase(eForm.getActionName()))
		{
			 
			String distId= eForm.getAppdto().getDistrictId();
			
			ArrayList tehsillist = new ArrayList();
			tehsillist = areaManagebd.getTehsil(lang,distId);
			eForm.setTehsilList(tehsillist);
			logger.debug("sucess");
			 dto.setLogic("Tehsil");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SUBAREA_VIEW".equalsIgnoreCase(eForm.getActionName()))
		{
			 
			String areaId= eForm.getAppdto().getAreaId();
			String tehId= eForm.getAppdto().getTehsilListId();
			ArrayList subarealist = new ArrayList();
			subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
			eForm.setSubareaList(subarealist);
			logger.debug("sucess");
			 dto.setLogic("SubArea");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		
		if("WARD_VIEW".equalsIgnoreCase(eForm.getActionName()))
		{
			 
			String areaId= eForm.getAppdto().getAreaId();
			String tehId= eForm.getAppdto().getTehsilListId();
			String subareaId= eForm.getAppdto().getSubAreaListId();
			ArrayList Wardlist = new ArrayList();
			Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
			eForm.setWardList(Wardlist);
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 eForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		if("SHOW_RADIO".equalsIgnoreCase(eForm.getActionName()))
		{
	
			logger.debug("sucess");
			ArrayList Mohallalist = new ArrayList();
			eForm.setMohallaList(Mohallalist);
			eForm.getAppdto().setMohallaId("");
			eForm.getAppdto().setMohallaName("");
			eForm.getAppdto().setMohallaHindiName("");
			eForm.getAppdto().setMohallaDesc("");
			dto.setClickRadio("");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 eForm.setPageType("");
			 forward_Jsp="editarea";
		}
		
		if("MOHALLA_VIEW".equalsIgnoreCase(eForm.getActionName()))
		{
			eForm.setMohallaList(new ArrayList());
			String areaId= eForm.getAppdto().getAreaId();
			String tehId= eForm.getAppdto().getTehsilListId();
			String subareaId= eForm.getAppdto().getSubAreaListId();
			String wardId= eForm.getAppdto().getWardListId();
			ArrayList Mohallalist = new ArrayList();
			Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
			eForm.setMohallaList(Mohallalist);
			logger.debug("sucess");
			 dto.setLogic("Mohalla");
			 eForm.setActionName(null);
			 eForm.setGetList(null);
			 dto.setClickRadio("N");
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if("delete".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="deletearea";
			 }
			 else if ("update".equalsIgnoreCase(eForm.getPageType()))
			 {
				 eForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="createarea";
			 }
		}
		
		

		if("SAVE_DATA".equalsIgnoreCase(eForm.getActionName()))
		{
			
			String saveType= eForm.getSaveType();
			
			if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				
				boolean insertData = areaManagebd.saveTehsilData(eForm,userId);
				
				if(insertData)
				{
					
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setTehsilId("");
						eForm.getAppdto().setTehsilName("");
						eForm.getAppdto().setTehsilHindiName("");
						eForm.getAppdto().setTehsilDesc("");
						
						logger.debug("sucess");
						 dto.setLogic("Tehsil");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="message"; 
					}
					
					else
					{
						String distId= eForm.getAppdto().getDistrictId();
						
						ArrayList tehsillist = new ArrayList();
						tehsillist = areaManagebd.getTehsil(lang,distId);
						eForm.setTehsilList(tehsillist);
						eForm.getAppdto().setTehsilId("");
						eForm.getAppdto().setTehsilName("");
						eForm.getAppdto().setTehsilHindiName("");
						eForm.getAppdto().setTehsilDesc("");
					logger.debug("sucess");
					 dto.setLogic("Tehsil");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					 dto.setErrorFlag("N");
					 forward_Jsp="createarea";
					}
				}

				else
				
				{
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						
						eForm.getAppdto().setTehsilId("");
					eForm.getAppdto().setTehsilName("");
					eForm.getAppdto().setTehsilHindiName("");
					eForm.getAppdto().setTehsilDesc("");
						logger.debug("sucess");
						 dto.setLogic("Tehsil");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 
						 forward_Jsp="message";
					}
					
					else
					{
						String distId= eForm.getAppdto().getDistrictId();
						
						ArrayList tehsillist = new ArrayList();
						tehsillist = areaManagebd.getTehsil(lang,distId);
						eForm.setTehsilList(tehsillist);
					
						eForm.getAppdto().setTehsilId("");
						eForm.getAppdto().setTehsilName("");
						eForm.getAppdto().setTehsilHindiName("");
						eForm.getAppdto().setTehsilDesc("");
					logger.debug("sucess");
					 dto.setLogic("Tehsil");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
					 {
						 dto.setErrorFlag("S");
					 }
					 else
					 {
						 dto.setErrorFlag("Y");
					 }
					 forward_Jsp="createarea";
					}
				}
				
			}
			
			if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				boolean insertData = areaManagebd.saveSubareaData(eForm,userId);
				
				if(insertData)
				{
					
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setSubAreaId("");
						eForm.getAppdto().setSubAreaName("");
						eForm.getAppdto().setSubAreaHindiName("");
						eForm.getAppdto().setSubAreaDesc("");
						eForm.getAppdto().setMunciBodyListId("");
						logger.debug("sucess");
						 dto.setLogic("SubArea");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="message";
					}
					else
					{
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						ArrayList subarealist = new ArrayList();
						subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
						eForm.setSubareaList(subarealist);
						eForm.getAppdto().setSubAreaId("");
						eForm.getAppdto().setSubAreaName("");
						eForm.getAppdto().setSubAreaHindiName("");
						eForm.getAppdto().setSubAreaDesc("");
						eForm.getAppdto().setMunciBodyListId("");
						logger.debug("sucess");
						 dto.setLogic("SubArea");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="createarea";
					}
				}
				else
				{
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setSubAreaId("");
						eForm.getAppdto().setSubAreaName("");
						eForm.getAppdto().setSubAreaHindiName("");
						eForm.getAppdto().setSubAreaDesc("");
						eForm.getAppdto().setMunciBodyListId("");
						logger.debug("sucess");
						 dto.setLogic("SubArea");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 forward_Jsp="message";
					}
					else
					{
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						ArrayList subarealist = new ArrayList();
						subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
						eForm.setSubareaList(subarealist);
						eForm.getAppdto().setSubAreaId("");
						eForm.getAppdto().setSubAreaName("");
						eForm.getAppdto().setSubAreaHindiName("");
						eForm.getAppdto().setSubAreaDesc("");
						eForm.getAppdto().setMunciBodyListId("");
						logger.debug("sucess");
						 dto.setLogic("SubArea");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 forward_Jsp="createarea";
					}
				}
				
			}
			
			if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				boolean insertData = areaManagebd.saveWardData(eForm,userId);
				
				if(insertData)
				{
					
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setWardId("");
						eForm.getAppdto().setWardName("");
						eForm.getAppdto().setWardHindiName("");
						eForm.getAppdto().setWardDesc("");
						eForm.getAppdto().setWardorPatwariListId("");
						eForm.getAppdto().setWardorPatwariListName("");
						logger.debug("sucess");
						 dto.setLogic("Ward");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="message";
					}
					else
					{
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						String subareaId= eForm.getAppdto().getSubAreaListId();
						ArrayList Wardlist = new ArrayList();
						Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
						eForm.setWardList(Wardlist);
						eForm.getAppdto().setWardId("");
						eForm.getAppdto().setWardName("");
						eForm.getAppdto().setWardHindiName("");
						eForm.getAppdto().setWardDesc("");
						eForm.getAppdto().setWardorPatwariListId("");
						eForm.getAppdto().setWardorPatwariListName("");
						logger.debug("sucess");
						 dto.setLogic("Ward");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="createarea";
					}
					
				}
				
				else
				{
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setWardId("");
						eForm.getAppdto().setWardName("");
						eForm.getAppdto().setWardHindiName("");
						eForm.getAppdto().setWardDesc("");
						eForm.getAppdto().setWardorPatwariListId("");
						eForm.getAppdto().setWardorPatwariListName("");
						logger.debug("sucess");
						 dto.setLogic("Ward");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 forward_Jsp="message";
					}
					else
					{
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						String subareaId= eForm.getAppdto().getSubAreaListId();
						ArrayList Wardlist = new ArrayList();
						Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
						eForm.setWardList(Wardlist);
						eForm.getAppdto().setWardId("");
						eForm.getAppdto().setWardName("");
						eForm.getAppdto().setWardHindiName("");
						eForm.getAppdto().setWardDesc("");
						eForm.getAppdto().setWardorPatwariListId("");
						eForm.getAppdto().setWardorPatwariListName("");
						logger.debug("sucess");
						 dto.setLogic("Ward");
						 eForm.setActionName(null);
						 dto.setLogicBtn("");
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 forward_Jsp="createarea";
					}
					
				}
				
			}
			
			if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
			
				boolean insertData = areaManagebd.saveMohallaData(eForm,userId);
				
				if(insertData)
				{
					
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setMohallaId("");
						eForm.getAppdto().setMohallaName("");
						eForm.getAppdto().setMohallaHindiName("");
						eForm.getAppdto().setMohallaDesc("");
						eForm.getAppdto().setSubClauseListId("");
						logger.debug("sucess");
						 dto.setLogic("Mohalla");
						 eForm.setActionName(null);
						 eForm.setGetList(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="message";
					}
					
					else
					{
						eForm.setMohallaList(new ArrayList());
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						String subareaId= eForm.getAppdto().getSubAreaListId();
						String wardId= eForm.getAppdto().getWardListId();
						ArrayList Mohallalist = new ArrayList();
						Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
						eForm.setMohallaList(Mohallalist);
						eForm.getAppdto().setMohallaId("");
						eForm.getAppdto().setMohallaName("");
						eForm.getAppdto().setMohallaHindiName("");
						eForm.getAppdto().setMohallaDesc("");
						eForm.getAppdto().setSubClauseListId("");
						logger.debug("sucess");
						 dto.setLogic("Mohalla");
						 eForm.setActionName(null);
						 eForm.setGetList(null);
						 dto.setLogicBtn("");
						 dto.setErrorFlag("N");
						 forward_Jsp="createarea";
					}
					
				}
				
				else
				{
					if("NO_ADD_MORE".equalsIgnoreCase(saveType))
					{
						eForm.getAppdto().setMohallaId("");
						eForm.getAppdto().setMohallaName("");
						eForm.getAppdto().setMohallaHindiName("");
						eForm.getAppdto().setMohallaDesc("");
						eForm.getAppdto().setSubClauseListId("");
						logger.debug("sucess");
						 dto.setLogic("Mohalla");
						 eForm.setActionName(null);
						 eForm.setGetList(null);
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 dto.setLogicBtn("");
						 forward_Jsp="message";
					}
					
					else
					{
						eForm.setMohallaList(new ArrayList());
						String areaId= eForm.getAppdto().getAreaId();
						String tehId= eForm.getAppdto().getTehsilListId();
						String subareaId= eForm.getAppdto().getSubAreaListId();
						String wardId= eForm.getAppdto().getWardListId();
						ArrayList Mohallalist = new ArrayList();
						Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
						eForm.setMohallaList(Mohallalist);
						eForm.getAppdto().setMohallaId("");
						eForm.getAppdto().setMohallaName("");
						eForm.getAppdto().setMohallaHindiName("");
						eForm.getAppdto().setMohallaDesc("");
						eForm.getAppdto().setSubClauseListId("");
						logger.debug("sucess");
						 dto.setLogic("Mohalla");
						 eForm.setActionName(null);
						 eForm.setGetList(null);
						 if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
						 {
							 dto.setErrorFlag("S");
						 }
						 else
						 {
							 dto.setErrorFlag("Y");
						 }
						 dto.setLogicBtn("");
						 forward_Jsp="createarea";
					}
				}
				
			}
			
			
		}
		
		
		if ("DELETE_STATE".equalsIgnoreCase(eForm.getActionName()))
		{
			
			if("Tehsil".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				String abc[] = eForm.getValueCheckBox().split(",");
				
				boolean flag=false;
				
				flag= areaManagebd.deleteTehsil(abc);
				String distId= eForm.getAppdto().getDistrictId();
					
				if(flag)
				{
					dto.setErrorFlag("DN");
					eForm.setValueCheckBox("");
					logger.debug("sucess");
					 dto.setLogic("Tehsil");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					
					 forward_Jsp="message";
				}
				
				else
				{
					dto.setErrorFlag("DY");
					eForm.setValueCheckBox("");
					logger.debug("sucess");
					 dto.setLogic("Tehsil");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					forward_Jsp="message";
				}
			}
			
			if("SubArea".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				String abc[] = eForm.getValueCheckBox().split(",");
				
				boolean flag=false;
				
				flag= areaManagebd.deleteSubArea(abc);
				String distId= eForm.getAppdto().getDistrictId();
					
				if(flag)
				{
					dto.setErrorFlag("DN");
				
					eForm.setValueCheckBox("");
					logger.debug("sucess");
					 dto.setLogic("SubArea");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					
					 forward_Jsp="message";
				}
				
				else
				{
					eForm.setValueCheckBox("");
					logger.debug("sucess");
					 dto.setLogic("SubArea");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					dto.setErrorFlag("DY");
					forward_Jsp="message";
				}
			}
			
			if("Ward".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				String abc[] = eForm.getValueCheckBox().split(",");
				
				boolean flag=false;
				
				flag= areaManagebd.deleteWard(abc);
				String distId= eForm.getAppdto().getDistrictId();
					
				if(flag)
				{
					dto.setErrorFlag("DN");
				
					logger.debug("sucess");
					 dto.setLogic("Ward");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					 eForm.setValueCheckBox("");
					 forward_Jsp="message";
				}
				
				else
				{
					dto.setErrorFlag("DY");
					eForm.setValueCheckBox("");
					logger.debug("sucess");
					 dto.setLogic("Ward");
					 eForm.setActionName(null);
					 dto.setLogicBtn("");
					forward_Jsp="message";
				}
			}
			 
			
			
			
			if("Mohalla".equalsIgnoreCase(eForm.getAppdto().getType()))
			{
				String abc[] = eForm.getValueCheckBox().split(",");
				
				boolean flag=false;
				
				flag= areaManagebd.deleteMohalla(abc);
				String distId= eForm.getAppdto().getDistrictId();
					
				if(flag)
				{
					 dto.setLogic("Mohalla");
					 eForm.setActionName(null);
					 eForm.setGetList(null);
					 dto.setLogicBtn("");
					 dto.setErrorFlag("DN");
					 eForm.setValueCheckBox("");
					 forward_Jsp="message";
				}
				
				else
				{
					 dto.setLogic("Mohalla");
					 eForm.setActionName(null);
					 eForm.setGetList(null);
					 dto.setLogicBtn("");
					 dto.setErrorFlag("DY");
					 eForm.setValueCheckBox("");
					 forward_Jsp="message";
				}
			}
			
		}

		
		
		if("LINK_VIEW".equalsIgnoreCase(eForm.getActionName()))
		{
			String id =eForm.getAppdto().getClickId();
			String type = eForm.getAppdto().getType();
			eForm.setAppdto(areaManagebd.getdetails(lang,id,type,dto));
			
			if("Tehsil".equalsIgnoreCase(type))
			{
				dto.setLogic("Tehsil");
				dto.setLinkClick("show");
				eForm.setActionName(null);
				eForm.setLinkClickId("");
				forward_Jsp="editarea";
			}
			if("SubArea".equalsIgnoreCase(type))
			{
				dto.setLogic("SubArea");
				eForm.setActionName(null);
				dto.setLinkClick("show");
				eForm.setLinkClickId("");
				forward_Jsp="editarea";
			}
			if("Ward".equalsIgnoreCase(type))
			{
				dto.setLogic("Ward");
				dto.setLinkClick("show");
				eForm.setActionName(null);
				eForm.setLinkClickId("");
				forward_Jsp="editarea";
			}
			if("Mohalla".equalsIgnoreCase(type))
			{
				dto.setLogic("Mohalla");
				dto.setLinkClick("show");
				eForm.setActionName(null);;
				eForm.setLinkClickId("");
				forward_Jsp="editarea";
			}
			
		}
		
		if("MOHALLA_UPDATE_ALL".equalsIgnoreCase(eForm.getActionName()))
		{
			 dto.setSubClauseListId("");
			 dto.setSubClauseListName("");
			dto.setLogic("Mohalla");
			eForm.setActionName(null);
			dto.setClickRadio("Y");
			forward_Jsp="editarea";
		}
		if("GENERATE_PDF".equalsIgnoreCase(eForm.getActionName()))
		{
			
			String distId = eForm.getAppdto().getDistrictId();
			
			String tehId = eForm.getAppdto().getTehsilListId();
			String areaId = eForm.getAppdto().getAreaId();
			String SubId = eForm.getAppdto().getSubAreaListId();
			String WardId = eForm.getAppdto().getWardListId();
			
			String areaType="";
			PropertiesFileReader prop = PropertiesFileReader.getInstance("resources.igrs");
			String path=prop.getValue("pdf.location");
			String reportPath =""; 
			
			if ("en".equalsIgnoreCase(language))
			{
				path = path +"ENGLISH//";
				if (!"".equalsIgnoreCase(distId) &&"".equalsIgnoreCase(tehId)&& 
						"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("districtWiseGuidelineReport2.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					areaType="DISTRICT";
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
				}
				else if (!"".equalsIgnoreCase(tehId)&& 
						"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("tehsilWiseGuidelineReport2.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="TEHSIL";
				}
				else if (!"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("areaWiseGuidelineReport2.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="AREA";
				}
				else if (!"".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("subAreaWiseguidelinereport2.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					jasperMap.put("SUB_AREA",Integer.parseInt(SubId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="SUBAREA";
				}
				else 
				{
					reportPath=path.concat("wardWiseGuidelineReport2.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					jasperMap.put("SUB_AREA",Integer.parseInt(SubId));
					jasperMap.put("WARD_ID",Integer.parseInt(WardId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="WARD";
				}
			}
			else
			{
				path =  path +"HINDI//";
				if (!"".equalsIgnoreCase(distId) &&"".equalsIgnoreCase(tehId)&& 
						"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("districtWiseGuidelineReport1.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					areaType="DISTRICT";
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
				}
				else if (!"".equalsIgnoreCase(tehId)&& 
						"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("tehsilWiseGuidelineReport1.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="TEHSIL";
				}
				else if (!"".equalsIgnoreCase(areaId) && "".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("areaWiseGuidelineReport1.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="AREA";
				}
				else if (!"".equalsIgnoreCase(SubId) && "".equalsIgnoreCase(WardId))
				{
					reportPath=path.concat("subAreaWiseguidelinereport1.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					jasperMap.put("SUBAREA_TYPE",Integer.parseInt(SubId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="SUBAREA";
				}
				else 
				{
					reportPath=path.concat("wardWiseGuidelineReport1.jrxml");
				 	
				 	HashMap jasperMap=new HashMap();
				 	JrxmlExportUtility export=new JrxmlExportUtility();
					jasperMap.put("path",path);
					jasperMap.put("DISTRICT_ID",Integer.parseInt(distId));
					jasperMap.put("TEHSIL_ID",Integer.parseInt(tehId));
					jasperMap.put("AREA_TYPE",Integer.parseInt(areaId));
					jasperMap.put("SUBAREA_TYPE",Integer.parseInt(SubId));
					jasperMap.put("WARD_PATWARI",Integer.parseInt(WardId));
					export.getJasperPdfviewerFromImg(jasperMap, reportPath, "AREAPDF", response,path,"L");
					areaType="WARD";
				}
			}
			
			

			
			
			
			eForm.setActionName(null);
			forward_Jsp="ViewPDF";
		}
		
		if("UPDATE_DATA".equalsIgnoreCase(eForm.getActionName()))
		{
			String id =eForm.getAppdto().getClickId();
			String type = eForm.getAppdto().getType();
			boolean flag = areaManagebd.updatedetails(lang,id,type,dto);
			if("Tehsil".equalsIgnoreCase(type))
			{
				if(flag)
				{
					String distId= eForm.getAppdto().getDistrictId();
					
					ArrayList tehsillist = new ArrayList();
					tehsillist = areaManagebd.getTehsil(lang,distId);
					eForm.setTehsilList(tehsillist);
					eForm.getAppdto().setTehsilId("");
					eForm.getAppdto().setTehsilName("");
					eForm.getAppdto().setTehsilHindiName("");
					eForm.getAppdto().setTehsilDesc("");
					dto.setLogic("Tehsil");
					dto.setLinkClick("");
					dto.setErrorFlag("UN");
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
				else
				{
					String distId= eForm.getAppdto().getDistrictId();
					
					ArrayList tehsillist = new ArrayList();
					tehsillist = areaManagebd.getTehsil(lang,distId);
					eForm.setTehsilList(tehsillist);
					eForm.getAppdto().setTehsilId("");
					eForm.getAppdto().setTehsilName("");
					eForm.getAppdto().setTehsilHindiName("");
					eForm.getAppdto().setTehsilDesc("");
				dto.setLogic("Tehsil");
				dto.setLinkClick("");
				if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
				 {
					 dto.setErrorFlag("SY");
				 }else
				 {
					 dto.setErrorFlag("UY");
				 }
				
				eForm.setActionName(null);
				eForm.setLinkClickId("");
				forward_Jsp="editarea";
				}
			}
			if("SubArea".equalsIgnoreCase(type))
			{
				if(flag)
				{
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					ArrayList subarealist = new ArrayList();
					subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
					eForm.setSubareaList(subarealist);
					eForm.getAppdto().setSubAreaId("");
					eForm.getAppdto().setSubAreaName("");
					eForm.getAppdto().setSubAreaHindiName("");
					eForm.getAppdto().setSubAreaDesc("");
					eForm.getAppdto().setMunciBodyListId("");
					dto.setLogic("SubArea");
					dto.setLinkClick("");
					dto.setErrorFlag("UN");
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
				else
				{
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					ArrayList subarealist = new ArrayList();
					subarealist = areaManagebd.getSubArea(lang,areaId,tehId);
					eForm.setSubareaList(subarealist);
					eForm.getAppdto().setSubAreaId("");
					eForm.getAppdto().setSubAreaName("");
					eForm.getAppdto().setSubAreaHindiName("");
					eForm.getAppdto().setSubAreaDesc("");
					eForm.getAppdto().setMunciBodyListId("");
					dto.setLogic("SubArea");
					dto.setLinkClick("");
					eForm.setActionName(null);
					if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
					 {
						 dto.setErrorFlag("SY");
					 }else
					 {
						 dto.setErrorFlag("UY");
					 }
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
			
			}
			if("Ward".equalsIgnoreCase(type))
			{
				if(flag)
				{
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					String subareaId= eForm.getAppdto().getSubAreaListId();
					ArrayList Wardlist = new ArrayList();
					Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
					eForm.setWardList(Wardlist);
					eForm.getAppdto().setWardId("");
					eForm.getAppdto().setWardName("");
					eForm.getAppdto().setWardHindiName("");
					eForm.getAppdto().setWardDesc("");
					eForm.getAppdto().setWardorPatwariListId("");
					eForm.getAppdto().setWardorPatwariListName("");
					dto.setLogic("Ward");
					dto.setLinkClick("");
					dto.setErrorFlag("UN");
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
				else
				{
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					String subareaId= eForm.getAppdto().getSubAreaListId();
					ArrayList Wardlist = new ArrayList();
					Wardlist = areaManagebd.getWard(lang,subareaId,areaId,tehId);
					eForm.setWardList(Wardlist);
					eForm.getAppdto().setWardId("");
					eForm.getAppdto().setWardName("");
					eForm.getAppdto().setWardHindiName("");
					eForm.getAppdto().setWardDesc("");
					eForm.getAppdto().setWardorPatwariListId("");
					eForm.getAppdto().setWardorPatwariListName("");
					dto.setLogic("Ward");
					dto.setLinkClick("");
					if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
					 {
						 dto.setErrorFlag("SY");
					 }
					else if("child1Exists".equalsIgnoreCase(eForm.getAppdto().getCheck())){
						 dto.setErrorFlag("KY");
					}
					else
					 {
						 dto.setErrorFlag("UY");
					 }
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
			}
			if("Mohalla".equalsIgnoreCase(type))
			{
				if(flag)
				{
					eForm.setMohallaList(new ArrayList());
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					String subareaId= eForm.getAppdto().getSubAreaListId();
					String wardId= eForm.getAppdto().getWardListId();
					ArrayList Mohallalist = new ArrayList();
					Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
					eForm.setMohallaList(Mohallalist);
					eForm.getAppdto().setMohallaId("");
					eForm.getAppdto().setMohallaName("");
					eForm.getAppdto().setMohallaHindiName("");
					eForm.getAppdto().setMohallaDesc("");
					eForm.getAppdto().setSubClauseListId("");
					dto.setLogic("Mohalla");
					dto.setLinkClick("");
					dto.setErrorFlag("UN");
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
				else
				{
					
					eForm.setMohallaList(new ArrayList());
					String areaId= eForm.getAppdto().getAreaId();
					String tehId= eForm.getAppdto().getTehsilListId();
					String subareaId= eForm.getAppdto().getSubAreaListId();
					String wardId= eForm.getAppdto().getWardListId();
					ArrayList Mohallalist = new ArrayList();
					Mohallalist = areaManagebd.getMohalla(lang,tehId,areaId,subareaId,wardId);
					eForm.setMohallaList(Mohallalist);
					eForm.getAppdto().setMohallaId("");
					eForm.getAppdto().setMohallaName("");
					eForm.getAppdto().setMohallaHindiName("");
					eForm.getAppdto().setMohallaDesc("");
					eForm.getAppdto().setSubClauseListId("");
					dto.setLogic("Mohalla");
					dto.setLinkClick("");
					if("exists".equalsIgnoreCase(eForm.getAppdto().getCheck()))
					 {
						 dto.setErrorFlag("SY");
					 }
					else if("child1Exists".equalsIgnoreCase(eForm.getAppdto().getCheck())){
						 dto.setErrorFlag("KY");
					}
					else
					 {
						 dto.setErrorFlag("UY");
					 }
					eForm.setActionName(null);
					eForm.setLinkClickId("");
					forward_Jsp="editarea";
				}
				
			}
		}
}
	catch(Exception e)
	{
		if("PDFVIEWEXCEPTION".equalsIgnoreCase(e.getMessage())){
			return new ActionForward("/jsp/areaManagement/FailureViewPDF.jsp");
		}
		else
		{
			throw e;
			
		}
		
	}
		
		return mapping.findForward(forward_Jsp);
	}
}
