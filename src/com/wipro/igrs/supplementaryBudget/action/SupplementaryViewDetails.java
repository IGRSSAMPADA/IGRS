package com.wipro.igrs.supplementaryBudget.action;

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



public class SupplementaryViewDetails extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(SupplementaryViewDetails.class);
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)
	{
		ArrayList list = new ArrayList();
		
		SupplementaryBudgetForm budget = (SupplementaryBudgetForm)form;
		BudgetBD bd = null;
		//HttpSession session = null;
		try
		{
			bd = new BudgetBD();
		
			String budgetReqId = request.getParameter("transactionId");
			
		
			budget = bd.supplementaryViewDetails(budgetReqId);
			list = bd.supplementaryViewBetMonths(budgetReqId);
				 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("businessError.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
		}
		request.setAttribute("budget",budget);
//		HttpSession session1 = request.getSession();
		session.setAttribute("monthAmountKey",list);
		budget.setTransactionId("transactionId");
		return mapping.findForward("success");
	}

}
