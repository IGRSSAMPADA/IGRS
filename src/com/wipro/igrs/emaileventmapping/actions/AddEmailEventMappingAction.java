/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AddEmailEventMappingAction.java
 * Author      :   Sayed Taha  
 * Description :   Represents the Action Class for Add new Event.
 *Created Date :   19 aug 2008 
 **/
package com.wipro.igrs.emaileventmapping.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.emaileventmapping.bd.EmailEventMappingBD;
import com.wipro.igrs.emaileventmapping.dto.EmailEventMappingDTO;
import com.wipro.igrs.emaileventmapping.form.EmailEventMappingForm;

public class AddEmailEventMappingAction extends BaseAction{
	 /**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	private EmailEventMappingBD bd=EmailEventMappingBD.getInstance();
    private Logger logger = (Logger) Logger.getLogger(AddEmailEventMappingAction.class);
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		logger.info("Department Action");
				
		EmailEventMappingForm deptForm = (EmailEventMappingForm) form;
		
		String emailID=deptForm.getEmailLookupTxnID();
		String toUser=deptForm.getToEmailUserID();
		String ccUser=deptForm.getCcEmailUserID();
		String fromUser=deptForm.getFromEmailUserID();
		EmailEventMappingDTO dto=new EmailEventMappingDTO();
		dto.setEmailLookupTxnID(emailID);
		dto.setToEmailUserID(toUser);
		dto.setFromEmailUserID(fromUser);
		if(ccUser.equals("Select User >>>")||ccUser.equals("-non-")){
			
			dto.setCcEmailUserID(null);
		}else{
			dto.setCcEmailUserID(ccUser);
		}
		
		bd.addEmailEventMapping(dto);
		return mapping.findForward("successcreate");
	}

}
