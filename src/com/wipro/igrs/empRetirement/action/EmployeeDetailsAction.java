package com.wipro.igrs.empRetirement.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empRetirement.bd.EmpBd;
import com.wipro.igrs.empRetirement.form.EmployeeForm;


public class EmployeeDetailsAction extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(EmployeeDetailsAction.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		EmployeeForm data = (EmployeeForm) form;
		String empId = data.getEmpId();
		String typeRetir = data.getTypeOfRetirement();
		String date = data.getDateOfretirement();
	//	HttpSession session = request.getSession();
		EmpBd bd = null;
		EmployeeForm empForm = null;
		String forwardTo = null;
		try
		{
			
			empForm = new EmployeeForm();
			bd = new EmpBd();
			empForm = bd.empDetails(empId,typeRetir,date);
			forwardTo = "success";
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("invaliedEmpIdError", ex.getMessage());
			forwardTo = "fail";
		}
		
		
		 
		session.setAttribute("empDetailsKey",empForm);
		return mapping.findForward(forwardTo);
	}
	
}
