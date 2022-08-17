/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   Emaileventmapping.java
 * Author      :   Sayed Taha  
 * Description :   Represents the Action Class for Show All Events.
 *Created Date :   19 aug 2008
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0                            	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.emaileventmapping.actions;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.emaileventmapping.bd.EmailEventMappingBD;

public class Emaileventmapping extends BaseAction{
	/**This is the main action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     */
	 private EmailEventMappingBD bd=EmailEventMappingBD.getInstance();
	 private Logger logger = (Logger) Logger.getLogger(Emaileventmapping.class);
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response,
				HttpSession session) throws Exception {
			
			logger.info("Email Event Mapping Action");
		ArrayList allEmailEvents=new ArrayList();
		allEmailEvents=bd.getAllEmailEvents();
		request.setAttribute("allMailEvantMappings", allEmailEvents);
		return mapping.findForward("emaileventmappingmaster");
	}

}
