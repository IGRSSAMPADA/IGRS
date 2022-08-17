/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UpdateEmployeeSalaryTaxSlab.java
 * Author      :   Sayed Taha
 * Description :   Represents Update Employee Salary Tax Slab action
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

public class UpdateEmployeeSalaryTaxSlab extends BaseAction{
    
	private EmployeeSalaryTaxSlabBD bd=EmployeeSalaryTaxSlabBD.getInstance();
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String id=session.getAttribute("tax_ID").toString();
		
		Float startSlab=((EmpSalTaxSlabForm)form).getStartSlab();
		Float endSlab=((EmpSalTaxSlabForm)form).getEndSlab();
		String gender=((EmpSalTaxSlabForm)form).getPersonType();
		Float percent=((EmpSalTaxSlabForm)form).getPercentApplicacle();
		EmployeeSalaryTaxSlabDTO dto = new EmployeeSalaryTaxSlabDTO();
		dto.setStartSlab(startSlab);
		dto.setEndSlab(endSlab);
		if(gender.equals("Female")){
			dto.setPersonType("F");
		}else{
			dto.setPersonType("M");
		}
		dto.setPercentApplicacle(percent);
		dto.setTaxID(id);
		
		if(bd.isSalarySlabFound(dto)){
			System.out.println(">>>>>>>>>>>>Error");
			ActionErrors error=new ActionErrors();
			error.add("taxslaberror", new ActionError("errors.taxslabealreadyxists"));
			saveErrors(request, error);
			return mapping.findForward("editemployeesalarytaxslab");
		}else{
			System.out.println(">>>>>>>>>>>>No Error");
			bd.updateEmployeeSalaryTaxSlab(dto);
			return mapping.findForward("successupdate");
		}
		
		
		
	}

}
