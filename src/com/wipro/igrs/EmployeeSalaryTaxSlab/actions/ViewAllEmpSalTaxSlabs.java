/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ViewAllPartyTypeDeedMap.java
 * Author      :   Sayed Taha
 * Description :   Represents View All Maps Action
 * Created Date   :   sept 3, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.EmployeeSalaryTaxSlab.bd.EmployeeSalaryTaxSlabBD;
import com.wipro.igrs.EmployeeSalaryTaxSlab.dto.EmployeeSalaryTaxSlabDTO;
import com.wipro.igrs.EmployeeSalaryTaxSlab.form.EmpSalTaxSlabForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ViewAllEmpSalTaxSlabs extends BaseAction{

	private EmployeeSalaryTaxSlabBD bd=EmployeeSalaryTaxSlabBD.getInstance();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ArrayList allEmpSalSlabs=bd.getAllEmployeeSalaryTaxSlabs();
		((EmpSalTaxSlabForm)form).setAllEmpSalTaxSlab(allEmpSalSlabs);
		((EmpSalTaxSlabForm)form).setEndSlab(null);
		((EmpSalTaxSlabForm)form).setStartSlab(null);
		((EmpSalTaxSlabForm)form).setPersonType("Male");
		((EmpSalTaxSlabForm)form).setPercentApplicacle(null);
		return mapping.findForward("employeeSalaryTaxSlabMaster");
	}

}
