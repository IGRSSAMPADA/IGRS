package com.wipro.igrs.salarygrademaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.salarygrademaster.bd.ISalaryGradeBD;
import com.wipro.igrs.salarygrademaster.bd.SalaryGradeBD;
import com.wipro.igrs.salarygrademaster.form.SalaryGradeForm;

public class PreCreateSalaryGradeAction extends com.wipro.igrs.baseaction.action.BaseAction {
    


    
    public PreCreateSalaryGradeAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();
		
		((SalaryGradeForm)form).setAllComponents(salaryGradeBD.getComponentsIDs());
		((SalaryGradeForm)form).setAllGrades(salaryGradeBD.getGradeDTOs());
		
		return mapping.findForward("createsalarygrademapping");
	}

}