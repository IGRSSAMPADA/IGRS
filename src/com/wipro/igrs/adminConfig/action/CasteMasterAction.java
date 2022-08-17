/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CasteMasterAction.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
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

import com.wipro.igrs.adminConfig.bd.CasteMasterBD;
import com.wipro.igrs.adminConfig.dto.CasteMasterDTO;
import com.wipro.igrs.adminConfig.form.CasteMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class CasteMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(CasteMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside CasteMasterAction class...");
		String forward = "CasteMaster";
	//	HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		   String userId = (String)session.getAttribute("UserId");
		   String userId1 = null;

		   userId1 = "ADMIN";

		CasteMasterForm cForm = (CasteMasterForm) form;
		CasteMasterBD mohalbd = new CasteMasterBD();
		CasteMasterDTO dto = new CasteMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				ArrayList cList = mohalbd.getCategoryList();
				dto.setCategoryList(cList);
				forward = "CasteMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList cList = mohalbd.getCasteList();
				dto.setCasteList(cList);
				forward = "CasteMasterView";
			}
		}

		if (cForm.getCastePageName() != null) {
			if (cForm.getCastePageName().equalsIgnoreCase("CasteCreate")) {
				if (cForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[4];
					params[0] = cForm.getCasteName();
					params[1] = cForm.getCastCategoryId();
					params[3] = userId1;
					params[2] = cForm.getCasteStatus();

					boolean flag = mohalbd.addCasteMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (cForm.getCastePageName().equalsIgnoreCase("CasteUpdate")) {
					if (cForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = cForm.getCastedto().getCasteName();
						params[1] = cForm.getCastedto().getCategory();
						params[3] = userId1;
						params[4] = cForm.getCastedto().getCasteId();
						params[2] = cForm.getCastedto().getCasteStatus();

						boolean flag = mohalbd.updateCastemaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String cid = request.getParameter("casteId");
		if (cid != null) {
			ArrayList cList = mohalbd.getCategoryList();

			forward = "CasteMasterUpdate";
			dto = mohalbd.getCasteIdList(cid);
			dto.setCategoryList(cList);
		}
		cForm.setCastedto(dto);

		session.setAttribute("casteList", cForm.getCastedto().getCasteList());
		session.setAttribute("casteMasterForm", cForm);
		cForm.setCastePageName(null);
		return mapping.findForward(forward);
	}
}
