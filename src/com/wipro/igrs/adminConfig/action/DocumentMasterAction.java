/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DocumentMasterAction.java
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

import com.wipro.igrs.adminConfig.bd.DocumentMasterBD;
import com.wipro.igrs.adminConfig.dto.DocumentMasterDTO;
import com.wipro.igrs.adminConfig.form.DocumentMasterForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class DocumentMasterAction extends BaseAction {
	private Logger logger = (Logger) Logger
			.getLogger(DocumentMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
	//	HttpSession session = request.getSession();
	    String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		 String userId = (String)session.getAttribute("UserId");
		logger.info("inside DocumentMasterAction class...");
		String forward = "DocumentMaster";
		String userId1 = null;
		userId1 = "ADMIN";
		DocumentMasterForm docForm = (DocumentMasterForm) form;
		DocumentMasterBD docbd = new DocumentMasterBD();
		DocumentMasterDTO dto = new DocumentMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "DocumentMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList  partyList = docbd
						.getDocumentList();
				dto.setDocumentList(partyList);
				forward = "DocumentMasterView";
			}
		}
		if (docForm.getDocumentPageName() != null) {
			if (docForm.getDocumentPageName()
					.equalsIgnoreCase("DocumentCreate")) {
				if (docForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[4];
					params[0] = docForm.getDocumentName();
					params[1] = docForm.getDocumentDesc();
					params[3] = userId1;
					params[2] = docForm.getDocumentStatus();

					boolean flag = docbd.addDocumentMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (docForm.getDocumentPageName().equalsIgnoreCase(
						"DocumentUpdate")) {
					if (docForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[5];
						params[0] = docForm.getDocumentdto().getDocumentName();
						params[1] = docForm.getDocumentdto().getDocumentDesc();
						params[3] = userId1;

						params[2] = docForm.getDocumentdto()
								.getDocumentStatus();
						params[4] = docForm.getDocumentdto().getDocumentId();
						boolean flag = docbd.updateDocumentmaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String documentid = request.getParameter("documentId");
		if (documentid != null) {
			forward = "DocumentMasterUpdate";
			dto = docbd.getDocumentIdList(documentid);
		}
		docForm.setPartydto(dto);

		session.setAttribute("documentList", docForm.getDocumentdto()
				.getDocumentList());
		session.setAttribute("documentMasterForm", docForm);
		docForm.setDocumentPageName(null);
		return mapping.findForward(forward);
	}
}
