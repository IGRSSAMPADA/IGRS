/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   LoadExpHeadMappingAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Action Class for Exp. Head Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  18th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.expheadmapping.bd.ExpHeadMappingBD;
import com.wipro.igrs.expheadmapping.form.ExpHeadMappingActionForm;

public class LoadExpHeadMappingAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(LoadExpHeadMappingAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		
		logger.info("we are in LoadExpHeadMappingAction");
		String target = "viewexpheadmapping";
		
		/* Populate the uaerTyppe form */
		ExpHeadMappingActionForm expHeadForm = (ExpHeadMappingActionForm) form;
		
		ExpHeadMappingBD expHeadbd = new ExpHeadMappingBD();

			ArrayList expHeadList = expHeadbd.getExpHeadList();
			expHeadForm.setExpHeadList(expHeadList);


		request.setAttribute("expHeadList", expHeadList);
		
		return mapping.findForward(target);
	}
}
