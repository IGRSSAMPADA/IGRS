/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.mohallavillagemaster.bd.MohallavillageBD;
import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;
import com.wipro.igrs.mohallavillagemaster.form.MohallavillageForm;

public class MohallavillageAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String target = "success";
		/* Populate the employee form */
		MohallavillageForm mohallavillageForm = (MohallavillageForm) form;
		MohallavillageBD mohallabd = new MohallavillageBD();
		MohallavillageDTO dto = new MohallavillageDTO();
		/* Add Mohallavillage master */
		if (mohallavillageForm.getMohallapageName() != null)
			if (mohallavillageForm.getMohallapageName().equalsIgnoreCase(
					"mohallacreate")) {
				mohallabd.addMohallavilagemaster(mohallavillageForm);
			} else {
				/* Update Mohallavillage master */
				mohallabd.updateMohallavilagemaster(mohallavillageForm);
			}

		ArrayList mohallaList = mohallabd.getMohallavillageList();
		ArrayList wardpatwariList = mohallabd.getWardpatwariList();
		/* Setting Mohallavillage Listitems */
		dto.setMohallaList(mohallaList);
		dto.setWardpatwariList(wardpatwariList);
		/* Parameter mohallavillageId */
		String mohallaid = request.getParameter("mohallavillageId");
		if (mohallaid != null) {
			dto = mohallabd.getMohallavillageId(mohallaid);
		}
		mohallavillageForm.setDto(dto);
		/* Set the sessions */

		session.setAttribute("mohallaList", mohallavillageForm.getDto()
				.getMohallaList());
		session.setAttribute("MohallavillageForm", mohallavillageForm);
		return mapping.findForward(target);
	}

}
