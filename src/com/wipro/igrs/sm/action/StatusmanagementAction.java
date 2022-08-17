/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.sm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.sm.bd.StatusmanagementBD;
import com.wipro.igrs.sm.dto.StatusmanagementDTO;
import com.wipro.igrs.sm.form.StatusmanagementForm;

public class StatusmanagementAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		String target = "success";
		/* Populate the activity form */
		StatusmanagementForm statusmanagementForm = (StatusmanagementForm) form;
		StatusmanagementDTO dto = new StatusmanagementDTO();
		StatusmanagementBD smbd = new StatusmanagementBD();
		// ArrayList<StatusmanagementDTO> statusList;
		String enumber = statusmanagementForm.getEno();
		//System.out.println("emp no++++=" + enumber);

		if (statusmanagementForm.getPageName() != null)

			if (statusmanagementForm.getPageName().equalsIgnoreCase("create")) {
				dto = smbd.getEmpList(enumber);
			} else {
				//System.out.println("update++++++++++++++++++");
				boolean flag = smbd.updateemp(statusmanagementForm);
				dto = statusmanagementForm.getDto();
				//System.out.println("done" + dto.getEno());
				if (dto.getCurrentStatus().equals("A")) {
					// System.out.println("setCurrentStatus=================="+list1.get(4).toString());
					dto.setCurrentStatus("Activate");
				} else {
					dto.setCurrentStatus("Deactivate");
				}
				// System.out.println("status-----> "+dto.getCurrentStatus());

			}

		// System.out.println("status-----> "+dto.getCurrentStatus());
		statusmanagementForm.setDto(dto);
		//HttpSession session = request.getSession();
		session.setAttribute("StatusmanagementForm", statusmanagementForm);

		return mapping.findForward(target);
	}
}
