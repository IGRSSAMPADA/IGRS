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
 * Description :   Represents the Master screen to see all inspections.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Yousry Ibrahem  12th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.spotinspslabmaster.actions;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.spotinspslabmaster.bd.InspectionBD;

public class ViewAllInspection extends BaseAction {
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {

    	InspectionBD insBD=new InspectionBD();
    	String language = (String)session.getAttribute("languageLocale");
    	Vector returned=insBD.viewAllInspections(language);
    	request.setAttribute("allDTOs", returned);
    	
    	request.setAttribute("language", language);
    	
    	return mapping.findForward("inspectionmaster");
    }

}