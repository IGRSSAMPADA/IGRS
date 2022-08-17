/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ExpHeadDeleteAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the Delete Action Class for Exp. Head Mapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  19th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.expheadmapping.action;


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

public class ExpHeadDeleteAction extends BaseAction {

	private Logger logger = (Logger) Logger.getLogger(ExpHeadDeleteAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in ExpHeadDeleteAction");
		
		String target = "loadExpHead";
		ExpHeadMappingBD expHeadbd= new ExpHeadMappingBD();

		expHeadbd.deleteExpHeadMasster(((ExpHeadMappingActionForm)form).getSelected());		

		return mapping.findForward(target);
	}
}
