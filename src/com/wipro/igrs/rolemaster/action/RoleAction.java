/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for RoleMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.rolemaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.rolemaster.bd.RoleBD;
import com.wipro.igrs.rolemaster.dto.RoleDTO;
import com.wipro.igrs.rolemaster.form.RoleForm;

public class RoleAction extends BaseAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {

		String target = "success";
		/* Populate the role form */
		RoleForm roleForm = (RoleForm) form;
		RoleBD rolebd = new RoleBD();
		RoleDTO dto = new RoleDTO();
	//	HttpSession session = request.getSession();
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");

		/* Add Role master */

		if (roleForm.getRolepageName() != null)

			if (roleForm.getRolepageName().equalsIgnoreCase("rolecreate")) {
				rolebd.addRolemaster(roleForm,roleId,funId,userId);
			} else {
				/* Update Role master */
				rolebd.updateRolemaster(roleForm,roleId,funId,userId);
			}

		ArrayList roleList = rolebd.getRoleList();

		/* Setting Role Master Listitems */
		dto.setRoleList(roleList);

		/* Parameter roleId */
		String roleid = request.getParameter("roleId");
		if (roleid != null) {
			dto = rolebd.getRoleId(roleid);
		}
		roleForm.setDto(dto);
		/* Set the sessions */
		//session = request.getSession();
		session.setAttribute("roleList", roleForm.getDto().getRoleList());
		session.setAttribute("RoleForm", roleForm);

		return mapping.findForward(target);
	}

}
