package com.wipro.igrs.empAttendance.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empAttendance.bd.EmpBD;
import com.wipro.igrs.empAttendance.dto.EmpDTO;
import com.wipro.igrs.empAttendance.form.EmpFORM;



public class EmployeeAttendanceAction extends BaseAction
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(EmployeeAttendanceAction.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		
		
		boolean flag = false;
		EmpFORM data = (EmpFORM) form;
		String date = data.getDate();
		String empRecords = data.getEmpDetailsJs();
		String noOfRec = data.getNoOfRecords();
		EmpDTO empDto = data.getEmpDto();
		EmpBD bd = null;
		String fowardTo=null;
		try
		{
			bd = new EmpBD();
			
				flag = bd.empAttendanceCreate(empDto,date,empRecords,noOfRec);
				fowardTo = "success";
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("error.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("empIdError", e.getMessage());
			fowardTo="fail";
		}
		
			return mapping.findForward(fowardTo);
			
	}

}
