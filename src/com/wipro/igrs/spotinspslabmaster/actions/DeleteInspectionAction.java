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
 * Description :   Represents the Deleting Action for Deleting Inspection.
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

public class DeleteInspectionAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean */
		InspectionActionForm insForm = (InspectionActionForm) form;
String language = (String)session.getAttribute("languageLocale");
insForm.setLanguage(language);
		InspectionBD insBD = new InspectionBD();

		/* knowing the number of selected inspections will be deleted */
		if (insForm.getSelected() != null || insForm.getSelected().length != 0) {
			for (int i = 0; i < insForm.getSelected().length; i++) {
				/*
				 * invoking Business Delegate Method that delete one Inspection
				 * at a time
				 */
				insBD.deleteInspection(insForm.getSelected()[i]);
			}
		}

		return mapping.findForward("viewAllInspection");

	}

}
