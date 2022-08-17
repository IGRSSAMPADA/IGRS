package com.wipro.igrs.empDeductions.action;


import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empDeductions.bd.EmpDeductionsBD;
import com.wipro.igrs.empDeductions.dto.EmpDTO;
import com.wipro.igrs.empDeductions.dto.EmpDeductionsDTO;
import com.wipro.igrs.empDeductions.form.EmpDeductionsForm;


public class EmpDeductionsAction extends BaseAction
{
	
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		EmpDeductionsForm empfm = (EmpDeductionsForm)form;
		EmpDeductionsBD empbd = new EmpDeductionsBD();
		String farwardPage = "fail";
		String view = (String)request.getParameter("view");
		String viewId = (String)request.getParameter("viewId");
		String deduction = (String)request.getParameter("deduction");
		String calc = (String)request.getParameter("calc");;
		
		boolean tempId=false;
		if(view!=null)
		if(view.equalsIgnoreCase("userId")){
			tempId = true;
		}else if(view.equalsIgnoreCase("nameId")){
			Hashtable vals = new Hashtable();
			vals.put("FIRST_NAME", empfm.getFirstName());
			vals.put("MIDDLE_NAME", empfm.getMiddleName());
			vals.put("LAST_NAME", empfm.getLastName());
			ArrayList empList = empbd.getUsersList(vals);
			request.setAttribute("empList", empList);
			farwardPage = "listPage";
		}
		
		if(viewId!=null){
			empfm.setEmpId(viewId);
			tempId = true;
		}
		
		if(deduction!=null){
			tempId = true;
		}
		if(calc!=null){
			tempId = true;
		}
			
		if(tempId){
			ArrayList empList = empbd.getUserDetails(empfm.getEmpId());
			ArrayList deductions = empbd.getDeductions();
			EmpDeductionsDTO dataDTO = new EmpDeductionsDTO();
			dataDTO.setEmpId(empfm.getEmpId());
			dataDTO.setDeductions(deductions);
			if(deduction!=null){
				ArrayList subDeductions = empbd.getDeductionSubType(deduction);
				dataDTO.setSubDeductions(subDeductions);
			}
			if(calc!=null){
					String empDet[] = {empfm.getEmpId(),empfm.getFirstName(),empfm.getMiddleName()};
					EmpDTO empDTO = empbd.getDeductionDetails(empDet);
					dataDTO = (EmpDeductionsDTO)empList.get(0);
					dataDTO.setEmpDTO(empDTO);
					tempId = false;
				}
			if(tempId){
			request.setAttribute("empList", empList);
			farwardPage = "empPage";
			}else{
				farwardPage = "calcPage";
			}
			session.setAttribute("dataDTO", dataDTO);
		}
			return mapping.findForward(farwardPage);
	}
}
