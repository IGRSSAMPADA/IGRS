/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	CategoryMasterAction.java
 * Author		:	vengamamba
 * Date			: 	20/06/2008
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

import com.wipro.igrs.adminConfig.bd.CategoryMasterBD;
import com.wipro.igrs.adminConfig.dto.CategoryMasterDTO;
import com.wipro.igrs.adminConfig.form.PersonCatgryForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class CategoryMasterAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(CategoryMasterAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,HttpSession session)
			throws Exception {
		logger.info("inside CategoryMasterAction class...");
		String forward = "CategoryMaster";
	//	HttpSession session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		String userId1 = null;
		userId1 = "ADMIN";
		PersonCatgryForm partyForm = (PersonCatgryForm) form;
		CategoryMasterBD partybd = new CategoryMasterBD();
		CategoryMasterDTO dto = new CategoryMasterDTO();
		String typeofopr = request.getParameter("typeofaction");
		if (typeofopr != null) {
			if (typeofopr.equals("create")) {
				forward = "CategoryMaster";
			} else if (typeofopr.equals("view")) {
				ArrayList partyList = partybd
						.getCategoryList();
				dto.setCategoryList(partyList);
				forward = "CategoryMasterView";
			}
		}
		if (partyForm.getCategoryPageName() != null) {
			if (partyForm.getCategoryPageName().equalsIgnoreCase(
					"CategoryCreate")) {
				if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

					String params[] = new String[3];
					params[0] = partyForm.getCategoryName();
					params[2] = userId1;
					params[1] = partyForm.getCategoryStatus();

					boolean flag = partybd.addCategoryMaster(params,roleId,funId,userId);
					if (flag)
						forward = "success";
					else
						forward = "fail";
				} else
					forward = "start";
			} else {
				if (partyForm.getCategoryPageName().equalsIgnoreCase(
						"CategoryUpdate")) {
					if (partyForm.getActionValue().equalsIgnoreCase("Submit")) {

						String params[] = new String[4];
						params[0] = partyForm.getCategorydto()
								.getCategoryName();
						params[2] = userId1;

						params[1] = partyForm.getCategorydto()
								.getCategoryStatus();
						params[3] = partyForm.getCategorydto().getCategoryId();
						boolean flag = partybd.updateCategorymaster(params,roleId,funId,userId);
						if (flag)
							forward = "success";
						else
							forward = "fail";
					} else
						forward = "start";
				}
			}
		}

		String partyid = request.getParameter("categoryId");
		if (partyid != null) {
			forward = "CategoryMasterUpdate";
			dto = partybd.getCategoryIdList(partyid);
		}
		partyForm.setCategorydto(dto);

		session.setAttribute("categoryList", partyForm.getCategorydto()
				.getCategoryList());
		session.setAttribute("personCatgryForm", partyForm);
		partyForm.setCategoryPageName(null);
		return mapping.findForward(forward);
	}
}
