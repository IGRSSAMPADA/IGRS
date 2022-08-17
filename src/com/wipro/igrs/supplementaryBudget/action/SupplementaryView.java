package com.wipro.igrs.supplementaryBudget.action;

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
import com.wipro.igrs.supplementaryBudget.bd.BudgetBD;
import com.wipro.igrs.supplementaryBudget.form.SupplementaryBudgetForm;

public class SupplementaryView extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(SupplementaryView.class);
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session) throws Exception
	{
ArrayList list = new ArrayList();
		
	SupplementaryBudgetForm data = (SupplementaryBudgetForm) form;
		
		String potId = request.getParameter("actionID");
		BudgetBD bd = null;
		try
		{
			bd = new BudgetBD();
			list = bd.supplementaryView(data);
		
			
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
		//request.setAttribute("potList",list);
		return mapping.findForward("success");
		
	}

}
