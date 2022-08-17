/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EditEmployeeSalaryTaxSlab.java
 * Author      :   Sayed Taha
 * Description :   Represents Edit Employee Salary Tax Slab Action
 * Created Date   :   sept 10, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.EmployeeSalaryTaxSlab.bd.EmployeeSalaryTaxSlabBD;
import com.wipro.igrs.EmployeeSalaryTaxSlab.dto.EmployeeSalaryTaxSlabDTO;
import com.wipro.igrs.EmployeeSalaryTaxSlab.form.EmpSalTaxSlabForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class EditEmployeeSalaryTaxSlab extends BaseAction{

	private EmployeeSalaryTaxSlabBD bd=EmployeeSalaryTaxSlabBD.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String taxId=request.getParameter("tax_id");
		session.setAttribute("tax_ID", taxId);
		EmployeeSalaryTaxSlabDTO dto=bd.getSalaryTaxByID(taxId);
		((EmpSalTaxSlabForm)form).setStartSlab(dto.getStartSlab());
		((EmpSalTaxSlabForm)form).setEndSlab(dto.getEndSlab());
		String gender=dto.getPersonType();
		System.out.println("Set This Type  : "+gender);
		((EmpSalTaxSlabForm)form).setPersonType(gender);
		((EmpSalTaxSlabForm)form).setPercentApplicacle(dto.getPercentApplicacle());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+((EmpSalTaxSlabForm)form).getPersonType());
		return mapping.findForward("editemployeesalarytaxslab");
		
	}

}
