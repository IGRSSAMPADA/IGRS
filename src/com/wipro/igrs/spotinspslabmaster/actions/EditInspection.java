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
 * Description :   Represents the Editing Action for Editing  Inspection.
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

public class EditInspection extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		/* get the inspection form bean */
		InspectionActionForm insForm = (InspectionActionForm) form;
		String slabID = (String) session.getAttribute("id");

		InspectionBD insBD = new InspectionBD();
		InspectionDTO insDTO = new InspectionDTO();

		/* setting the DTO from the FormBean */
		insDTO.setSlabID(slabID);
		insDTO.setSlabMaxRange(insForm.getSlabMaxRange());
		insDTO.setSlabMinRange(insForm.getSlabMinRange());
		insDTO.setCreatedBy(insForm.getCreatedBy());
		insDTO.setModifiedBy((String) session.getAttribute(
				"UserId"));
		insDTO.setCreatedDate(insForm.getCreatedDate());
			insDTO.setStatus(insForm.getStatus());
		

		/* invoking Business Delegate Method that updates one Inspection */
		insBD.updateINspection(insDTO);

		return mapping.findForward("successupdate");
	}

}