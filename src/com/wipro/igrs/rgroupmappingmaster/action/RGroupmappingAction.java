/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.rgroupmappingmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.rgroupmappingmaster.bd.RGroupmappingBD;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;
import com.wipro.igrs.rgroupmappingmaster.form.RGroupmappingForm;

public class RGroupmappingAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		String target = "success";
		/* Populate the activity form */
		RGroupmappingForm rgroupmappingForm = (RGroupmappingForm) form;
		RGroupmappingDTO dto = new RGroupmappingDTO();
		RGroupmappingBD rgbd = new RGroupmappingBD();
		ArrayList roleList = rgbd.getRoleList();
		ArrayList rolegroupList = rgbd.getRolegroupList();
		ArrayList rgroupmappingList = rgbd.getRgroupmappingList();
	//	HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		System.out.println("rgroupmappingList in Action rgroupmappingList==="
				+ rgroupmappingList);
		if (rgroupmappingForm.getPageName() != null)
			if (rgroupmappingForm.getPageName()
					.equalsIgnoreCase("activecreate")) {
				/* Add RGroupmapping master */
				rgbd.addRgroupmappingmaster(rgroupmappingForm,roleId,funId,userId);
			} else {
				/* Update RGroupmapping master */

			}
		/* Parameter roleId */
		String rolegroupid = request.getParameter("rolegroupId");
		if (rolegroupid != null) {
			dto = rgbd.getRolegrouopId(rolegroupid);
		}
		if (rgroupmappingForm.getPageName() != null
				&& rgroupmappingForm.getPageName().equalsIgnoreCase("update")) {
			System.out.println("update pages");
			rgbd.updateRgroupmappingmaster(rgroupmappingForm,roleId,funId,userId);
		}
		/* Setting activity master List items */
		dto.setRoleList(roleList);
		dto.setRolegroupList(rolegroupList);
		dto.setRgroupmappingList(rgroupmappingList);

		rgroupmappingForm.setDto(dto);
		/* Set the sessions */
	//	session = request.getSession();
		session.setAttribute("roleList", rgroupmappingForm.getDto()
				.getRoleList());
		session.setAttribute("rolegroupList", rgroupmappingForm.getDto()
				.getRolegroupList());
		session.setAttribute("rgroupmappingList", rgroupmappingForm.getDto()
				.getRgroupmappingList());
		session.setAttribute("RGroupmappingForm", rgroupmappingForm);

		return mapping.findForward(target);
	}
}
