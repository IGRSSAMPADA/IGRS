package com.wipro.igrs.empsalcomp.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empsalcomp.bd.EmpSalaryBD;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;
import com.wipro.igrs.empsalcomp.form.EmpSalaryActionForm;
import com.wipro.igrs.empsalcomp.util.PropertiesFileReader;

public class EmpSalarySearchAction extends BaseAction {

	private PropertiesFileReader pr;
	private Logger logger = (Logger) Logger
	.getLogger(EmpSalarySearchAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		EmpSalaryActionForm empForm = (EmpSalaryActionForm) form;
		String empID = empForm.getEmpID();
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		
		EmpSalaryBD empBD = new EmpSalaryBD();

		int count = empBD.getCheckEmp(empID);
		
		if(count ==0 ){
			ArrayList errorList = new ArrayList();
			errorList.add(pr.getValue("error.basic.empnotfound"));
			request.setAttribute("errorFlag","true");
			request.setAttribute("errorList",errorList);
		}else {
 
			empForm.setSalarySlabs(empBD.getSlabList(empID));

			EmpSalaryDTO empDTO = empBD.getEmpData(empID);
			empForm.setEmpID(empDTO.getEmpID());
			empForm.setEmpFName(empDTO.getEmpFName());
			empForm.setEmpMName(empDTO.getEmpMName());
			empForm.setEmpLName(empDTO.getEmpLName());
			empForm.setBasicValue(empDTO.getCurrentBasic());
			empForm.setEffectiveDate(empDTO.getEffectiveDate());
			empForm.setEffectiveFlag(empDTO.getEffectiveFlag());
			empForm.setSlabId(empDTO.getSlabId());
			empForm.setIncrements(empDTO.getIncrements());
			empForm.setIncrementSal(empDTO.getIncrementSal());
			empForm.setHiddenIncrements(empDTO.getIncrements());
			empForm.setSearched("searched");
			request.setAttribute("empID", empDTO.getEmpID());
			
		}
		return mapping.findForward("empsalary");
	}

}
