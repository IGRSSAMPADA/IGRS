/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PhotoIdMasterAction.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
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

import com.wipro.igrs.adminConfig.bd.PhotoIdMasterBD;
import com.wipro.igrs.adminConfig.dto.PhotoIdMasterDTO;
import com.wipro.igrs.adminConfig.form.PhotoIdMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class PhotoIdMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(PhotoIdMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside CategoryMasterAction class...");
		String forward = "PhotoIdMaster";
		//HttpSession session = request.getSession();
		String userId1 = null;
	    String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		userId1 = "ADMIN";
		PhotoIdMasterForm partyForm = (PhotoIdMasterForm) form;
		PhotoIdMasterBD partybd = new PhotoIdMasterBD();
		PhotoIdMasterDTO dto = new PhotoIdMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "PhotoIdMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList  partyList = partybd
						.getPhotoIdList();
				dto.setPhotoIdList(partyList);
				forward = "PhotoIdMasterView";
			}
		}
		if (partyForm.getPhotoIdPageName() != null) {
			if (partyForm.getPhotoIdPageName()
					.equalsIgnoreCase("PhotoIdCreate")) {
				if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[3];
					params[0] = partyForm.getPhotoIdName();
					params[2] = userId1;
					params[1] = partyForm.getPhotoIdStatus();

					boolean flag = partybd.addPhotoIdMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (partyForm.getPhotoIdPageName().equalsIgnoreCase(
						"PhotoIdUpdate")) {
					if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[4];
						params[0] = partyForm.getPhotoIddto().getPhotoIdName();
						params[2] = userId1;

						params[1] = partyForm.getPhotoIddto()
								.getPhotoIdStatus();
						params[3] = partyForm.getPhotoIddto().getPhotoId();
						boolean flag = partybd.updatePhotoIdmaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String partyid = request.getParameter("photoId");
		if (partyid != null) {
			forward = "PhotoIdMasterUpdate";
			dto = partybd.getPhotoIdIdList(partyid);
		}
		partyForm.setPhotoIddto(dto);

		session.setAttribute("PhotoIdList", partyForm.getPhotoIddto()
				.getPhotoIdList());
		session.setAttribute("PhotoIdMasterForm", partyForm);
		partyForm.setPhotoIdPageName(null);
		return mapping.findForward(forward);
	}
}
