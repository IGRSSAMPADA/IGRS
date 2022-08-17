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

public class PreUpdateSalaryGradeAction extends com.wipro.igrs.baseaction.action.BaseAction {
    


    
    public PreUpdateSalaryGradeAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();
		 SalaryGradeDTO salaryGradeDTO=salaryGradeBD.getSalaryGradeByID(request.getParameter("salCompGradeId"));
		((SalaryGradeForm)form).setSalCompGradeId(request.getParameter("salCompGradeId"));
		 ((SalaryGradeForm)form).setOldComponentId(salaryGradeDTO.getComponentId());
		 ((SalaryGradeForm)form).setSelectedComponent(salaryGradeDTO.getComponentId());
		 System.out.println("REQUEST PARAMETER>>>"+request.getParameter("salCompGradeId"));
		 System.out.println("COMPONENT ID >>>>"+((SalaryGradeForm)form).getSelectedComponent());
		 ((SalaryGradeForm)form).setOldGradeId(salaryGradeDTO.getGradeId());
		 ((SalaryGradeForm)form).setSelectedGrade(salaryGradeDTO.getGradeId());
		 System.out.println("GRADE ID >>>>"+((SalaryGradeForm)form).getSelectedGrade());
		 ((SalaryGradeForm)form).setAllComponents(salaryGradeBD.getComponentsIDs());
		((SalaryGradeForm)form).setAllGrades(salaryGradeBD.getGradeDTOs());
		System.out.println("in action"+request.getParameter("salCompGradeId"));
		return mapping.findForward("updatesalarygrademapping");
		
	}
    
  

}