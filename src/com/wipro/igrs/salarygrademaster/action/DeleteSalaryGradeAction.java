package com.wipro.igrs.salarygrademaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.salarygrademaster.bd.ISalaryGradeBD;
import com.wipro.igrs.salarygrademaster.bd.SalaryGradeBD;
import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;
import com.wipro.igrs.salarygrademaster.form.SalaryGradeForm;

public class DeleteSalaryGradeAction extends com.wipro.igrs.baseaction.action.BaseAction {
    


    
    public DeleteSalaryGradeAction() {
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();
		SalaryGradeDTO salaryGradeDTO=new SalaryGradeDTO();
	
		SalaryGradeForm salaryGradeForm=(SalaryGradeForm) form;
		
		String [] selected=salaryGradeForm.getSelected();
		
		for (int i = 0; i < selected.length; i++) {
			
			salaryGradeDTO=salaryGradeBD.getSalaryGradeByID(selected[i]);
			System.out.println("selected to delete>>>"+selected[i]);
			salaryGradeBD.deleteSalaryGrade(salaryGradeDTO);
			
		}
		
		return mapping.findForward("viewSalaryGradeMapping");
		
	}

}