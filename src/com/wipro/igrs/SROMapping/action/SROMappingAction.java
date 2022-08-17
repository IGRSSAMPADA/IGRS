/** Copyright Notice
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


package com.wipro.igrs.SROMapping.action;


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

import com.wipro.igrs.SROMapping.bd.SROMappingBD;
import com.wipro.igrs.SROMapping.dto.SROMappingDTO;
import com.wipro.igrs.SROMapping.form.SROMappingForm;
import com.wipro.igrs.areaManagement.bd.areaManagementBD;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.common.IGRSCommon;
import com.wipro.igrs.util.JrxmlExportUtility;
import com.wipro.igrs.util.PropertiesFileReader;



public class SROMappingAction extends BaseAction {
	String forward_Jsp;
	private Logger logger = org.apache.log4j.Logger.getLogger(SROMappingAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		logger.info("we are in SROMappingAction");
		
		String frdName=request.getParameter("pageName");
		String funName=request.getParameter("funId");
		
		String linkViewName=request.getParameter("LinkView");

		String language = (String) session.getAttribute("languageLocale");
		String userId=(String) session.getAttribute("UserId");
		SROMappingForm SROForm = (SROMappingForm) form;
		SROMappingDTO dto = SROForm.getAppdto();
		SROMappingBD SRObd = new SROMappingBD();
		String lang = (String)session.getAttribute("languageLocale");
		
		String activityid = request.getParameter("actid");
		
        String officeId = (String)session.getAttribute("loggedToOffice");
		IGRSCommon save =  new IGRSCommon();
		dto.setLogic("");
		dto.setLinkClick("");
		dto.setNowAdd("");
		SROForm.getAppdto().setErrorFlag("");
		
		if(frdName!=null)
		 {
		 
			if(frdName.equalsIgnoreCase("sroMapping") && funName.equalsIgnoreCase("FUN_502"))
			 {
			
				 logger.debug("frdName");
				 SROForm.setActionName(null);
				 SROForm.getAppdto().setType("");
				 
				 ArrayList statelist = SRObd.getState(lang,SROForm);
				 
				 SROForm.setStateList(statelist);
				 String StateId= SROForm.getAppdto().getStateId(); 
				 SROForm.setDistrictList(new ArrayList());
				 save.saveactivitylog(userId, activityid);
				 dto.setLogicBtn("all");
				 dto.setNowAdd("");
				 SROForm.setPageType("");
				 
				 SROForm.getAppdto().setDistrictId("");
					SROForm.getAppdto().setDistrictName("");
					SROForm.getAppdto().setTehsilListId("");
					SROForm.getAppdto().setTehsilListName("");
					SROForm.getAppdto().setAreaId("");
					SROForm.getAppdto().setAreaName("");
					SROForm.getAppdto().setSubAreaListId("");
					SROForm.getAppdto().setSubAreaListName("");
					SROForm.getAppdto().setWardListId("");
					SROForm.getAppdto().setWardListName("");						
					//SROForm.getAppdto().setWardorPatwariListId("");
					//SROForm.getAppdto().setWardorPatwariListName("");
					SROForm.getAppdto().setWardHindiName("");
					SROForm.getAppdto().setWardName("");
					SROForm.getAppdto().setErrorFlag("");
					SROForm.getAppdto().setNowAdd("");
					SROForm.getAppdto().setSroListId("");
					SROForm.getAppdto().setSroListName("");
					SROForm.getAppdto().setWardListEdId("");
					SROForm.getAppdto().setSroListId("");
					SROForm.getAppdto().setSroListName("");
					SROForm.setSroList(new ArrayList());
					SROForm.setTehsilList(new ArrayList());
					SROForm.setAreaList(new ArrayList());
					SROForm.setSubareaList(new ArrayList());
					SROForm.setWardList(new ArrayList());
					SROForm.setWardListED(new ArrayList());
					SROForm.setWardorPatwariList(new ArrayList());
				
					 
					ArrayList districtlist = new ArrayList();
					districtlist = SROMappingBD.getDistrict(lang,StateId,officeId);
					SROForm.setDistrictList(districtlist);
					
					logger.debug("sucess");
					 dto.setLogic("Ward");
					 SROForm.setActionName(null);
					 dto.setLogicBtn("");
					 if("delete".equalsIgnoreCase(SROForm.getPageType()))
					 {
						 SROForm.setPageType("");
						 forward_Jsp="deletearea";
					 }
					 else if ("update".equalsIgnoreCase(SROForm.getPageType()))
					 {
						 dto.setClickRadio("");
						 SROForm.setPageType("");
						 forward_Jsp="editarea";
					 }
					 else
					 {
						 forward_Jsp="sroMapping";
					 }
					 
					 
				 frdName="";
				 forward_Jsp="sroMapping";
			 }
			else if(frdName.equalsIgnoreCase("sroMappingEdit") && funName.equalsIgnoreCase("FUN_502"))
			 {
			
				 logger.debug("frdName");
				 SROForm.setActionName(null);
				 SROForm.getAppdto().setType("");
				 
				 ArrayList statelist = SRObd.getState(lang,SROForm);
				 
				 SROForm.setStateList(statelist);
				 String StateId= SROForm.getAppdto().getStateId(); 
				 SROForm.setDistrictList(new ArrayList());
				 save.saveactivitylog(userId, activityid);
				 dto.setLogicBtn("all");
				 dto.setNowAdd("");
				 SROForm.setPageType("");
				 
				 	SROForm.getAppdto().setDistrictId("");
					SROForm.getAppdto().setDistrictName("");
					SROForm.getAppdto().setTehsilListId("");
					SROForm.getAppdto().setTehsilListName("");
					SROForm.getAppdto().setAreaId("");
					SROForm.getAppdto().setAreaName("");
					SROForm.getAppdto().setSubAreaListId("");
					SROForm.getAppdto().setSubAreaListName("");
					SROForm.getAppdto().setWardListId("");
					SROForm.getAppdto().setWardListName("");						
					SROForm.getAppdto().setWardorPatwariListId("");
					SROForm.getAppdto().setWardListEdId("");
					//SROForm.getAppdto().setWardorPatwariListName("");
					SROForm.getAppdto().setWardHindiName("");
					SROForm.getAppdto().setWardName("");
					SROForm.getAppdto().setErrorFlag("");
					SROForm.getAppdto().setNowAdd("");
					SROForm.getAppdto().setWardListEdId("");
					SROForm.setTehsilList(new ArrayList());
					SROForm.setAreaList(new ArrayList());
					SROForm.setSubareaList(new ArrayList());
					SROForm.setWardList(new ArrayList());
					SROForm.setWardListED(new ArrayList());
					SROForm.setWardorPatwariList(new ArrayList());
					SROForm.setSroList(new ArrayList());
					SROForm.getAppdto().setSroListId("");
					SROForm.getAppdto().setSroListName("");
					ArrayList districtlist = new ArrayList();
					districtlist = SROMappingBD.getDistrictEdit(lang,StateId,officeId);
					SROForm.setDistrictList(districtlist);
					
					logger.debug("sucess");
					 dto.setLogic("Ward");
					 SROForm.setActionName(null);
					 dto.setLogicBtn("");
					 if ("update".equalsIgnoreCase(SROForm.getPageType()))
					 {
						 dto.setClickRadio("");
						 SROForm.setPageType("");
						 forward_Jsp="sroMappingEdit";
					 }
					 else
					 {
						 forward_Jsp="sroMappingEdit";
					 }
					 
					 
			/* frdName="";
			 forward_Jsp="sroMappingEdit";*/
			 }
			
		}

		if("gettehsil".equalsIgnoreCase(SROForm.getActionName()))
		{
			String distId= SROForm.getAppdto().getDistrictId();
			String logicId= SROForm.getAppdto().getType();
			ArrayList tehsillist = new ArrayList();
			tehsillist = SRObd.getTehsil(lang,distId);
			SROForm.setTehsilList(tehsillist);
			SROForm.getAppdto().setAreaId("");
			SROForm.getAppdto().setAreaName("");
			SROForm.getAppdto().setSubAreaListId("");
			SROForm.getAppdto().setSubAreaListName("");
			SROForm.getAppdto().setWardListId("");
			SROForm.getAppdto().setWardListName("");
			SROForm.getAppdto().setType("");
			SROForm.getAppdto().setSroListId("");
			SROForm.getAppdto().setSroListName("");
			SROForm.setSroList(new ArrayList());
			SROForm.setAreaList(new ArrayList());
			SROForm.setSubareaList(new ArrayList());
			SROForm.setWardList(new ArrayList());
			dto.setLogic("Ward");
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess for ward");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
				 if("delete".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 SROForm.setPageType("");
					 forward_Jsp="deletearea";
				 }
				 else if ("update".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 dto.setClickRadio("");
					 SROForm.setPageType("");
					 forward_Jsp="editarea";
				 }
				 else
				 {
					 forward_Jsp="sroMapping";
				 }
			}
		}
		else if("gettehsil_ed".equalsIgnoreCase(SROForm.getActionName()))
		{
			String distId= SROForm.getAppdto().getDistrictId();
			String logicId= SROForm.getAppdto().getType();
			ArrayList tehsillist = new ArrayList();
			tehsillist = SRObd.getTehsilEdit(lang,distId);
			SROForm.setTehsilList(tehsillist);
			SROForm.getAppdto().setAreaId("");
			SROForm.getAppdto().setAreaName("");
			SROForm.getAppdto().setSubAreaListId("");
			SROForm.getAppdto().setSubAreaListName("");
			SROForm.getAppdto().setWardListId("");
			SROForm.getAppdto().setWardListName("");
			SROForm.getAppdto().setSroListId("");
			SROForm.getAppdto().setSroListName("");
			SROForm.getAppdto().setWardListEdId("");
			SROForm.getAppdto().setType("");
			SROForm.setSroList(new ArrayList());
			SROForm.getAppdto().setSroListId("");
			SROForm.getAppdto().setSroListName("");
			SROForm.setAreaList(new ArrayList());
			SROForm.setSubareaList(new ArrayList());
			SROForm.setWardList(new ArrayList());
			SROForm.setWardListED(new ArrayList());
			dto.setLogic("Ward");
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess for ward");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
				 /*if("delete".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 SROForm.setPageType("");
					 forward_Jsp="deletearea";
				 }
				 else if ("update".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 dto.setClickRadio("");
					 SROForm.setPageType("");
					 forward_Jsp="editarea";
				 }
				 else
				 {*/
					 forward_Jsp="sroMappingEdit";
				// }
			}
		}
		else if("getarea_ed".equalsIgnoreCase(SROForm.getActionName()))
		{
			String tehId= SROForm.getAppdto().getTehsilListId();
			String distId= SROForm.getAppdto().getDistrictId();
			String logicId= SROForm.getAppdto().getType();
			ArrayList arealist = new ArrayList();
			arealist = SRObd.getAreaEdit(lang,distId,tehId);
			SROForm.setAreaList(arealist);
			SROForm.getAppdto().setSubAreaListId("");
			SROForm.getAppdto().setSubAreaListName("");
			SROForm.getAppdto().setWardListId("");
			SROForm.getAppdto().setWardListName("");
			SROForm.getAppdto().setSroListId("");
			SROForm.getAppdto().setSroListName("");
			SROForm.getAppdto().setWardListEdId("");
			SROForm.getAppdto().setType("");
			SROForm.setSroList(new ArrayList());
			SROForm.getAppdto().setSroListId("");
			SROForm.getAppdto().setSroListName("");
			SROForm.setSubareaList(new ArrayList());
			SROForm.setWardList(new ArrayList());
			SROForm.setWardListED(new ArrayList());
			SROForm.getAppdto().setAreaId("");
			dto.setClickId("");
			dto.setLogic("Ward");
				if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
				{
					logger.debug("sucess for ward");
					 dto.setLogic("Ward");
					 SROForm.setActionName(null);
					 dto.setLogicBtn("");
					 dto.setClickId("");
						 if ("update".equalsIgnoreCase(SROForm.getPageType()))
						 {
							 dto.setClickRadio("");
							 SROForm.setPageType("");
							 forward_Jsp="sroMappingEdit";
						 }
						 else
						 {
							 forward_Jsp="sroMappingEdit";
						 }
				}
		}
	
		else if("getarea".equalsIgnoreCase(SROForm.getActionName()))
		{
		
		String tehId= SROForm.getAppdto().getTehsilListId();
		String distId= SROForm.getAppdto().getDistrictId();
		ArrayList arealist = new ArrayList();
		arealist = SRObd.getArea(lang,tehId,distId);
		SROForm.setAreaList(arealist);
		SROForm.getAppdto().setSubAreaListId("");
		SROForm.getAppdto().setSubAreaListName("");
		SROForm.getAppdto().setWardListId("");
		SROForm.getAppdto().setWardListName("");
		SROForm.getAppdto().setWardListEdId("");
		SROForm.getAppdto().setSroListId("");
		SROForm.getAppdto().setSroListName("");
		SROForm.setSroList(new ArrayList());
		SROForm.getAppdto().setType("");
		SROForm.setSubareaList(new ArrayList());
		SROForm.setWardList(new ArrayList());
		SROForm.getAppdto().setAreaId("");
		dto.setClickId("");
		dto.setLogic("Ward");
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess for ward");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
					 if ("update".equalsIgnoreCase(SROForm.getPageType()))
					 {
						 dto.setClickRadio("");
						 SROForm.setPageType("");
						 forward_Jsp="sroMappingEdit";
					 }
					 else
					 {
						 forward_Jsp="sroMappingEdit";
					 }
			}
		
			
		}
	

		
		else if ("getsubarea".equalsIgnoreCase(SROForm.getActionName()))
		{
		
		String areaId= SROForm.getAppdto().getAreaId();
		String distId=SROForm.getAppdto().getDistrictId();
		String tehId= SROForm.getAppdto().getTehsilListId();
		ArrayList subarealist = new ArrayList();
		subarealist = SRObd.getSubArea(lang,areaId,tehId,distId);
		SROForm.setSubareaList(subarealist);
		SROForm.getAppdto().setWardListId("");
		SROForm.getAppdto().setWardListName("");
		SROForm.getAppdto().setSroListId("");
		SROForm.getAppdto().setSroListName("");
		SROForm.setWardList(new ArrayList());
		SROForm.setSroList(new ArrayList());
		dto.setLogic("Ward");
		if ("viewPDF".equalsIgnoreCase(SROForm.getPageType()))
		 {
			 dto.setClickRadio("");
			 SROForm.setPageType("");
			 forward_Jsp="ViewPDF";
		 }
		else
		{
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
				 if("delete".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 SROForm.setPageType("");
					 forward_Jsp="deletearea";
				 }
				 else if ("update".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 dto.setClickRadio("");
					 SROForm.setPageType("");
					 forward_Jsp="editarea";
				 }
				 else
				 {
					 forward_Jsp="sroMapping";
				 }
			}
		}
	}
		else if ("getsubarea_ed".equalsIgnoreCase(SROForm.getActionName()))
		{
		
		String areaId= SROForm.getAppdto().getAreaId();
		String tehId= SROForm.getAppdto().getTehsilListId();
		ArrayList subarealist = new ArrayList();
		subarealist = SRObd.getSubAreaEdit(lang,areaId,tehId);
		SROForm.setSubareaList(subarealist);
		SROForm.getAppdto().setWardListId("");
		SROForm.getAppdto().setWardListName("");
		SROForm.getAppdto().setSroListId("");
		SROForm.getAppdto().setSroListName("");
		SROForm.getAppdto().setWardListEdId("");
		SROForm.setSroList(new ArrayList());
		SROForm.getAppdto().setSroListId("");
		SROForm.getAppdto().setSroListName("");
		SROForm.setWardList(new ArrayList());
		SROForm.setWardListED(new ArrayList());
		
		dto.setLogic("Ward");
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
				 if ("update".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 dto.setClickRadio("");
					 SROForm.setPageType("");
					 forward_Jsp="sroMappingEdit";
				 }
				 else
				 {
					 forward_Jsp="sroMappingEdit";
				 }
			}
		}
		
		else if ("getOffice_ed".equalsIgnoreCase(SROForm.getActionName()))
		{
		
		String areaId= SROForm.getAppdto().getAreaId();
		String tehId= SROForm.getAppdto().getTehsilListId();
		String subAreaId=SROForm.getAppdto().getSubAreaListId();
		String distId=SROForm.getAppdto().getDistrictId();
		ArrayList sroList = new ArrayList();
		sroList = SROMappingBD.getsroOfcEdit(lang, distId, tehId, areaId, subAreaId);
		SROForm.setSroList(sroList);
		
		
		SROForm.getAppdto().setWardListId("");
		SROForm.getAppdto().setWardListName("");
		SROForm.getAppdto().setWardListEdId("");
		SROForm.getAppdto().setWardListNameEd("");
		SROForm.setWardList(new ArrayList());
		SROForm.setWardListED(new ArrayList());
		dto.setLogic("Ward");
			if("Ward".equalsIgnoreCase(SROForm.getAppdto().getType()))
			{
				logger.debug("sucess");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 dto.setClickId("");
				 if ("update".equalsIgnoreCase(SROForm.getPageType()))
				 {
					 dto.setClickRadio("");
					 SROForm.setPageType("");
					 forward_Jsp="sroMappingEdit";
				 }
				 else
				 {
					 forward_Jsp="sroMappingEdit";
				 }
			}
		}
		
		
		
		
		
		else if("WARD_VIEW".equalsIgnoreCase(SROForm.getActionName()))
		{
			String distId= SROForm.getAppdto().getDistrictId();
			String areaId= SROForm.getAppdto().getAreaId();
			String tehId= SROForm.getAppdto().getTehsilListId();
			String subareaId= SROForm.getAppdto().getSubAreaListId();
			
			
			ArrayList Wardlist = new ArrayList();
			SROForm.setWardorPatwariList(new ArrayList());
			
			ArrayList sroList = new ArrayList();
			sroList = SROMappingBD.getsroOfc(lang,distId);
			SROForm.setSroList(sroList);
			
			Wardlist = SRObd.getWard(lang,subareaId,areaId,tehId);
	
			SROForm.setWardList(Wardlist);
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 SROForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if ("update".equalsIgnoreCase(SROForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 SROForm.setPageType("");
				 forward_Jsp="editarea";
			 }
			 else
			 {
				 forward_Jsp="sroMapping";
			 }
		}
		else if("WARD_VIEW_ED".equalsIgnoreCase(SROForm.getActionName()))
		{
			String areaId= SROForm.getAppdto().getAreaId();
			String tehId= SROForm.getAppdto().getTehsilListId();
			String subareaId= SROForm.getAppdto().getSubAreaListId();
			String sroId= SROForm.getAppdto().getSroListId();
			
			ArrayList wardListED = new ArrayList();
			wardListED = SROMappingBD.getwardEdit(lang,tehId,areaId,subareaId,sroId);
			SROForm.setWardListED(wardListED);
			
			logger.debug("sucess");
			 dto.setLogic("Ward");
			 SROForm.setActionName(null);
			 dto.setLogicBtn("");
			 dto.setClickId("");
			 if ("update".equalsIgnoreCase(SROForm.getPageType()))
			 {
				 dto.setClickRadio("");
				 SROForm.setPageType("");
				 forward_Jsp="sroMappingEdit";
			 }
			 else
			 {
				 forward_Jsp="sroMappingEdit";
			 }
		}
		if("SAVE_DATA".equalsIgnoreCase(SROForm.getActionName()))
		{
			System.out.println("we are in SAVE_DATA ");
			String saveType= SROForm.getSaveType();
			SROForm.getAppdto().setType("");
			
			String ward_data[] = SROForm.getValueCheckBoxWard().split(",");
		//	String ofc_data[] = SROForm.getValueCheckBoxOfc().split(",");
			
			//System.out.println("ward_data "+ward_data);
			//System.out.println("ward_data "+ofc_data);
			//HashMap<String[], String[]> offcWardMapping=new HashMap<String[], String[]>();
			//offcWardMapping.put(ward_data, ofc_data);
			
			boolean flag=false;
			
			//flag= SRObd.saveAllWard(ward_data,ofc_data);
		//	boolean A=SRObd.saveAllWard(ward_data,ofc_data);
			boolean insertData = SRObd.saveWardData(SROForm,userId,ward_data);
			if(insertData){
			dto.setErrorFlag("ConstN");
			}
			else{
				dto.setErrorFlag("ConstY");
			}
			
		
			 forward_Jsp="SROMessage";
			
				}
		else if("UPDATE_DATA".equalsIgnoreCase(SROForm.getActionName()))
			
		{
			
			boolean flag=false;
			String abc[] = SROForm.getValueCheckBox().split(",");
			
			flag=SRObd.updateWard(abc);
			
			if(flag)
			{
				dto.setErrorFlag("ConstUS");
			
				logger.debug("sucess");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				 SROForm.setValueCheckBox("");
				 forward_Jsp="SROMessage";
			}
			
			else
			{
				dto.setErrorFlag("ConstUF");
				SROForm.setValueCheckBox("");
				logger.debug("Failure");
				 dto.setLogic("Ward");
				 SROForm.setActionName(null);
				 dto.setLogicBtn("");
				forward_Jsp="SROMessage";
			}
			
				}
			
			
			
		return mapping.findForward(forward_Jsp);	
	
}
}
	
	
