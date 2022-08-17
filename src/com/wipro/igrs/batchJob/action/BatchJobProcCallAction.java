package com.wipro.igrs.batchJob.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.batchJob.bd.BatchJobBD;
import com.wipro.igrs.batchJob.form.BatchJobForm;
import com.wipro.igrs.empRetirement.bd.EmpBd;
import com.wipro.igrs.empRetirement.form.EmployeeForm;

public class BatchJobProcCallAction extends Action
{
		
			public ActionForward execute(ActionMapping mapping,
											ActionForm form,
											HttpServletRequest request,
											HttpServletResponse response)
		{
				BatchJobForm data = (BatchJobForm) form;
			
		//	HttpSession session = request.getSession();
				BatchJobBD bd = null;
			EmployeeForm empForm = null;
			String forwardTo = null;
		try
		{
		
				bd = new BatchJobBD();
				//bd.checkEmpId(empId);
				 bd.batchJobCallProc();
				forwardTo = "success";
		
		}
			catch(Exception ex)
			{
					ex.printStackTrace();
					request.setAttribute("invaliedEmpIdError", ex.getMessage());
					forwardTo = "fail";
			}
		
		
		
		//session.setAttribute("empDetailsKey",empForm);
		return mapping.findForward(forwardTo);
		}
	
}
