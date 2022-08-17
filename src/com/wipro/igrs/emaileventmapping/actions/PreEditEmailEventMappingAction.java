/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PreEditDepartmentAction.java
 * Author      :   Sayed Taha  
 * Description :   Represents the Action Class for preparing the Edit page by 
 *                 department to edit data.
 *Created Date :   19 aug 2008
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0                            	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.emaileventmapping.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.emaileventmapping.bd.EmailEventMappingBD;
import com.wipro.igrs.emaileventmapping.dto.EmailEventMappingDTO;
import com.wipro.igrs.emaileventmapping.form.EmailEventMappingForm;
public class PreEditEmailEventMappingAction extends BaseAction{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	
	private EmailEventMappingBD bd=EmailEventMappingBD.getInstance();
	private Logger logger = (Logger) Logger.getLogger(PreEditEmailEventMappingAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String eventtoedit=(String)request.getParameter("eventtoedit");
		session.setAttribute("eventtoedit", eventtoedit);
		
		//get the User want to be edited 
		EmailEventMappingDTO dto = bd.getEmailEventByID(eventtoedit);
		//get the Lists to be fill the Drop down Lists in the edit page
		ArrayList emailEventName=new ArrayList();
		ArrayList allEmailUsers=new ArrayList();
		emailEventName=bd.getEmailEventName();
		allEmailUsers=bd.getAllEmailUsers();
		//set the data to the action form
		EmailEventMappingForm emailForm=(EmailEventMappingForm)form;	
		
		emailForm.setEmailLookupTxnID(dto.getEmailLookupTxnID());
		
		emailForm.setEmailLookupName(dto.getEmailLookupName());
		emailForm.setFromEmailUserID(dto.getFromEmailUserID());
		emailForm.setToEmailUserID(dto.getToEmailUserID());
		emailForm.setCcEmailUserID(dto.getCcEmailUserID());
		
		emailForm.setEmailEventName(emailEventName);
		emailForm.setEmailUsers(allEmailUsers);
		return mapping.findForward("updateemaileventmapping");
	}

}
