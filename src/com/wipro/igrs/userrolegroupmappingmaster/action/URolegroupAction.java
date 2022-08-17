/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */


package com.wipro.igrs.userrolegroupmappingmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.userrolegroupmappingmaster.bd.URolegroupBD;
import com.wipro.igrs.userrolegroupmappingmaster.dto.URolegroupDTO;
import com.wipro.igrs.userrolegroupmappingmaster.form.URolegroupForm;
//import com.wipro.igrs.Log4J.LoggerMsg;

public class URolegroupAction extends BaseAction {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception 
    {
		//LoggerMsg.info("we are in URolegroupAction");
		String target = "success";
		/* Populate the activity form */
		URolegroupForm urolegroupForm = (URolegroupForm)form;
		URolegroupDTO dto = new URolegroupDTO();
		URolegroupBD urgbd=new URolegroupBD();
		ArrayList userList = urgbd.getUserList();
		ArrayList rolegroupList = urgbd.getRolegroupList();
		ArrayList userroleList = urgbd.getUserroleList();
		
		            if(urolegroupForm.getPageName()!=null)
			        if(urolegroupForm.getPageName().equalsIgnoreCase("activecreate"))
			            {
			        	          /*Add Userrolemapping master*/
			        	urgbd.addUserrolemappingmaster(urolegroupForm);
			            }
			        else{
			        	         /*Update Userrolemapping master*/
			        	urgbd.updateUserrolemappingmaster(urolegroupForm);
			            }
		            /* Parameter userId */
		    		String userid = request.getParameter("userId");
		    		//LoggerMsg.debug("registeroffice id in action.."+rolegroupid);
		    		if(userid!=null)
		    		{
		    			dto = urgbd.getUserId(userid);
		    		}
		         
	                 /*Setting master List items*/
		                dto.setUserList(userList);
		                dto.setRolegroupList(rolegroupList);
		                dto.setUserroleList(userroleList);
		
		                
		                urolegroupForm.setDto(dto);
		                 /* Set the sessions */
//		                HttpSession session = request.getSession();
		                session.setAttribute("userroleList",urolegroupForm.getDto().getUserroleList());
		                session.setAttribute("URolegroupForm", urolegroupForm);
		            
		                
		
		return mapping.findForward(target);	
	}
}
