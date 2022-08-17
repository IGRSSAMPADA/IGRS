package com.wipro.igrs.salarygrademaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.salarygrademaster.bd.ISalaryGradeBD;
import com.wipro.igrs.salarygrademaster.bd.SalaryGradeBD;
import com.wipro.igrs.salarygrademaster.dto.ComponentDTO;
import com.wipro.igrs.salarygrademaster.dto.GradeDTO;
import com.wipro.igrs.salarygrademaster.dto.SalaryGradeDTO;
import com.wipro.igrs.salarygrademaster.exception.SalaryGradeAlreadyExistException;
import com.wipro.igrs.salarygrademaster.form.SalaryGradeForm;

public class UpdateSalaryGradeAction extends com.wipro.igrs.baseaction.action.BaseAction {
    


    
    public UpdateSalaryGradeAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		

		ISalaryGradeBD salaryGradeBD=new SalaryGradeBD();
		SalaryGradeDTO salaryGradeDTO=new SalaryGradeDTO();
		SalaryGradeDTO oldSalaryGradeDTO=new SalaryGradeDTO();
	
		salaryGradeDTO.setSalCompGradeId(((SalaryGradeForm)form).getSalCompGradeId());
		salaryGradeDTO.setGradeId(((SalaryGradeForm)form).getSelectedGrade());
		salaryGradeDTO.setComponentId(((SalaryGradeForm)form).getSelectedComponent());
		
		salaryGradeDTO.setComponentValue(null);
		salaryGradeDTO.setEmpId(null);
		
		oldSalaryGradeDTO.setComponentId(((SalaryGradeForm)form).getOldComponentId());
		oldSalaryGradeDTO.setGradeId(((SalaryGradeForm)form).getOldGradeId());
		if(isCancelled(request))
			return mapping.findForward("viewSalaryGradeMapping");
		
		try{
			salaryGradeBD.updateSalaryGrade(salaryGradeDTO, oldSalaryGradeDTO);
			return mapping.findForward("successUpdate");
			
		}catch (SalaryGradeAlreadyExistException e) {
			ActionErrors errors=new ActionErrors();
			errors.add("salaryGradeMappingAlreadyExist",new ActionError("salaryGradeMapping.salaryGradeMappingAlreadyExist"));
			saveErrors(request, errors);
			return mapping.findForward("alreadyExist");
		}
	}
  

}