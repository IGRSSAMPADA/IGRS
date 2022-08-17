/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementoneAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  28th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

/*
package com.wipro.igrs.sm.action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.wipro.igrs.sm.bd.StatusmanagementBD;
import com.wipro.igrs.sm.dto.StatusmanagementDTO;
import com.wipro.igrs.sm.form.StatusmanagementForm;

import com.wipro.igrs.Log4J.LoggerMsg;

public class StatusmanagementoneAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, 
            HttpServletRequest request,HttpServletResponse response) throws Exception 
    {
		LoggerMsg.info("we are in StatusmanagementoneAction");
		String target = "success";
		/* Populate the activity form */
		/*StatusmanagementForm statusmanagementForm = (StatusmanagementForm)form;
		StatusmanagementDTO dto = new StatusmanagementDTO();
		StatusmanagementBD smbd=new StatusmanagementBD();
		ArrayList<StatusmanagementDTO> statusList;
		
			
		
						    	 
		               statusList=smbd.getStatusList();    
		               dto.setStatusList(statusList);
		               statusmanagementForm.setDto(dto);
		               HttpSession session = request.getSession();
		               
	                session.setAttribute("StatusmanagementForm", statusmanagementForm);
		            
		                
		
		return mapping.findForward(target);	
	}
}*/
