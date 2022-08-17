/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RegisterofficesAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for RegisterofficeMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  4th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.registerofficesmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.registerofficesmaster.bd.RegisterofficesBD;
import com.wipro.igrs.registerofficesmaster.dto.RegisterofficesDTO;
import com.wipro.igrs.registerofficesmaster.form.RegisterofficesForm;

public class RegisterofficesAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		String target = "success";
		/* Populate the employee form */
		RegisterofficesForm registerofficesForm = (RegisterofficesForm) form;
		RegisterofficesBD officebd = new RegisterofficesBD();
		RegisterofficesDTO dto = new RegisterofficesDTO();
		//HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");

		/* Add Registeroffices master */

		if (registerofficesForm.getOfficepageName() != null)

			if (registerofficesForm.getOfficepageName().equalsIgnoreCase(
					"officecreate")) {
				officebd.addRegisterofficemaster(registerofficesForm,roleId,funId,userId);
			} else {
				/* Update Registeroffice master */
				officebd.updateRegisterofficemaster(registerofficesForm,roleId,funId,userId);
			}

		ArrayList officeList = officebd.getRegisterofficesList();
		ArrayList districtList = officebd.getDistrictList();
		ArrayList tehsilList = officebd.getTehsilList();
		ArrayList wardList = officebd.getWardList();
		ArrayList typeList = officebd.getTypeList();
		/* Setting Register office Listitems */
		dto.setOfficeList(officeList);
		dto.setDistrictList(districtList);
		dto.setTehsilList(tehsilList);
		dto.setWardList(wardList);
		dto.setTypeList(typeList);
		/* Parameter registerofficeId */
		String officeid = request.getParameter("registerofficeId");
		System.out.println("registeroffice id in action.." + officeid);
		if (officeid != null) {
			dto = officebd.getRegisterofficeId(officeid);
		}
		registerofficesForm.setDto(dto);
		/* Set the sessions */
		// session = request.getSession();
		session.setAttribute("officeList", registerofficesForm.getDto()
				.getOfficeList());
		session.setAttribute("registerofficesForm", registerofficesForm);

		return mapping.findForward(target);
	}

}
