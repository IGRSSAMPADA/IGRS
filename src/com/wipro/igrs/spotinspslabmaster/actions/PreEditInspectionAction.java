/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech,Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AddAction.java
 * Author      :   Yousry Ibrahem 
 * Description :   Represents the preparing for editing Action .
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.spotinspslabmaster.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.spotinspslabmaster.actionforms.InspectionActionForm;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;
import com.wipro.igrs.spotinspslabmaster.dto.InspectionDTO;

public class PreEditInspectionAction extends BaseAction{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean*/
		InspectionActionForm insForm=(InspectionActionForm)form;
		String language = (String)session.getAttribute("languageLocale");
		String pagetitle=insForm.getPageTitle();
		
		//String pageName=insForm.getPageName();
		//String pgTitle=(String)request.getParameter("pageTitle");
		/* get parameter from the request*/
		String id=(String)request.getParameter("slabID");
		
		if(id!=null)
		{
			
			session.setAttribute("id", id);	
		}
		
		if(id==null){
			id=(String) session.getAttribute("id");
			}		
		InspectionBD insBD=new InspectionBD();
		InspectionDTO insDTO=insBD.getInspectionById(id,language);
		String forwardPage="";
		/* setting the FormBean  from the retrieving DTO*/
		insForm.setSlabMaxRange(insDTO.getSlabMaxRange());
		insForm.setSlabMinRange(insDTO.getSlabMinRange());
		insForm.setCreatedBy(insDTO.getCreatedBy());
		insForm.setStatus(insDTO.getStatus());		
		insForm.setStatusdesc(insDTO.getStatusDesc());			
		insForm.setCreatedDate(insDTO.getCreatedDate());
		insForm.setPercentage(insDTO.getPercentage());
		insForm.setMinSpotInsp(insDTO.getMinSpotInsp());
		insForm.setTimeFrom(insDTO.getTimeFrom());
		insForm.setTimeTo(insDTO.getTimeTo());
		insForm.setRemarks(insDTO.getRemarks());
		if(pagetitle.equalsIgnoreCase("SLABReqUpdate"))
		{
			
			forwardPage="viewinspectionmaster";
		}
		 if(pagetitle.equalsIgnoreCase("SLABReqSubmit"))
		{
			 //insForm.setRemarks("");
			forwardPage="updateinspectionmaster";
		}
		
		//return mapping.findForward("updateinspectionmaster");//ramesh commented on 3rd Jan 13
		return mapping.findForward(forwardPage);
	}



	
}
