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

public class ViewSalaryGradeMappingAction extends com.wipro.igrs.baseaction.action.BaseAction {
    


    
    public ViewSalaryGradeMappingAction() {
    }
    

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();
		ArrayList salaryGradeList=new ArrayList();
		salaryGradeList=salaryGradeBD.viewAllSalaryGrades();
		request.setAttribute("salaryGradeList", salaryGradeList);
		return mapping.findForward("viewsalarygrademappinglist");
	}

}