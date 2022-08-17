/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MohallaMasterAction.java
 * Author		:	vengamamba
 * Date			: 	17/06/2008
 */

package com.wipro.igrs.adminConfig.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.adminConfig.bd.MohallaMasterBD;
import com.wipro.igrs.adminConfig.dto.MohallaMasterDTO;
import com.wipro.igrs.adminConfig.form.MohallaMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class MohallaMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(MohallaMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		 // HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		logger.info("inside MohallaMasterAction class...");
		String forward = "MohallaMaster";
		String userId1 = null;
		userId1 = "ADMIN";
		MohallaMasterForm mohallaForm = (MohallaMasterForm) form;
		MohallaMasterBD mohalbd = new MohallaMasterBD();
		MohallaMasterDTO dto = new MohallaMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				ArrayList wardpatwariList = mohalbd
						.getWardpatwariList();
				dto.setPatwariIdList(wardpatwariList);
				forward = "MohallaMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList mohallaList = mohalbd
						.getMohallaList();
				dto.setMohallaList(mohallaList);
				forward = "MohallaMasterView";
			}
		}

		if (mohallaForm.getMohallaPageName() != null) {
			if (mohallaForm.getMohallaPageName().equalsIgnoreCase(
					"MohallaCreate")) {
				if (mohallaForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[6];
					params[0] = mohallaForm.getMohallaVillageName();
					params[1] = mohallaForm.getMohallaDesc();
					params[4] = userId1;
					params[3] = mohallaForm.getPatwariId();
					params[2] = mohallaForm.getMohallaStatus();
					params[5] = mohallaForm.getType();

					boolean flag = mohalbd.addMohallaMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (mohallaForm.getMohallaPageName().equalsIgnoreCase(
						"MohallaUpdate")) {
					if (mohallaForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = mohallaForm.getMohalladto()
								.getMohallaName();
						params[1] = mohallaForm.getMohalladto()
								.getMohallaDesc();
						params[3] = userId1;

						params[2] = mohallaForm.getMohalladto()
								.getMohallaStatus();
						params[4] = mohallaForm.getMohalladto().getMohallaId();
						boolean flag = mohalbd.updateMohallamaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String mohallaid = request.getParameter("mohallaId");
		if (mohallaid != null) {
			forward = "MohallaMasterUpdate";
			dto = mohalbd.getMohallaIdList(mohallaid);
		}
		mohallaForm.setMohalladto(dto);

		session.setAttribute("mohallaList", mohallaForm.getMohalladto()
				.getMohallaList());
		session.setAttribute("mohalvillageForm", mohallaForm);
		mohallaForm.setMohallaPageName(null);
		return mapping.findForward(forward);
	}
}
