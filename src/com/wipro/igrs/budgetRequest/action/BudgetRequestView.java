package com.wipro.igrs.budgetRequest.action;

import java.sql.SQLException;
import java.util.ArrayList;

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
import com.wipro.igrs.budgetRequest.bd.BudgetBD;
import com.wipro.igrs.budgetRequest.form.BudgetForm;



public class BudgetRequestView extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(BudgetRequestView.class);
	
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)throws Exception
	{
		ArrayList list = new ArrayList();
		
		BudgetForm data = (BudgetForm) form;
		
		String potId = request.getParameter("actionID");
		BudgetBD bd = null;
		try
		{
			bd = new BudgetBD();
			list = bd.getBudgerRequestList(data);
		
			
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
		//HttpSession session = request.getSession();
		session.setAttribute("budgetRequestList",list);
		return mapping.findForward("success");
		
	}

}
