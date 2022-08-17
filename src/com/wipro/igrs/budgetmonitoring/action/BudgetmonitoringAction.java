/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   BudgetmonitoringAction.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the Action Class for Budget Monitoring.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  14th April, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.budgetmonitoring.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.budgetmonitoring.bd.BudgetmonitoringBD;
import com.wipro.igrs.budgetmonitoring.dto.BudgetmonitoringDTO;
import com.wipro.igrs.budgetmonitoring.form.BudgetmonitoringForm;

public class BudgetmonitoringAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(BudgetmonitoringAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		logger.info("we are in BudgetmonitoringAction");

		String target = "success";
		/* Populate the employee form */
		BudgetmonitoringForm budgetmonitoringForm = (BudgetmonitoringForm) form;
		BudgetmonitoringBD budgetbd = new BudgetmonitoringBD();
		BudgetmonitoringDTO dto = new BudgetmonitoringDTO();
		ArrayList districtList = budgetbd
				.getDistrictList();
		ArrayList budgetmonitoringList = new ArrayList();
		ArrayList financialList = new ArrayList();
		ArrayList droList = budgetbd.getDroList();
		String fdate = budgetmonitoringForm.getFromDate();
		String tdate = budgetmonitoringForm.getToDate();
		String dro = budgetmonitoringForm.getDroId();
		logger.debug("dro++++++++++++++" + dro);
		String droid = request.getParameter("refId");
		logger.debug("droid+++++++++++++++" + droid);
		if (droid != null) {
			financialList = budgetbd.getFinancialList(droid);
		}
		String fyear = budgetmonitoringForm.getFinancialId();
		logger.debug("fyear++++++++++++++++++++++" + fyear);
		if (fyear != null && fdate != null && tdate != null) {
			budgetmonitoringList = budgetbd.getBudgetmonitoringList(fyear,
					fdate, tdate);
			dto.setBudgetmonitoringList(budgetmonitoringList);

		}
		dto.setDistrictList(districtList);
		dto.setFinancialList(financialList);
		dto.setDroList(droList);

		budgetmonitoringForm.setDto(dto);

		session.setAttribute("budgetmonitoringList", budgetmonitoringForm
				.getDto().getBudgetmonitoringList());
		logger.debug("budgetmonitoringList++++++++++==="
				+ budgetmonitoringList.size());
		session.setAttribute("BudgetmonitoringForm", budgetmonitoringForm);

		return mapping.findForward(target);
	}

}
