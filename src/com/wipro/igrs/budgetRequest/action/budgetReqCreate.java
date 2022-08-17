package com.wipro.igrs.budgetRequest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.budgetRequest.bd.BudgetBD;
import com.wipro.igrs.budgetRequest.form.BudgetForm;

public class budgetReqCreate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(budgetReqCreate.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		
		logger.info("i am in BudgetRequest action");
		logger.error("I am BudgetRequest error");
		logger.debug("I am in BudgetRequest debug");
		
		BudgetForm formData = (BudgetForm) form;
		boolean flag = false;
		BudgetBD bd = null;
		String yearId = formData.getFinancialYear();
		String forwardTo = null;
		try
		{
			bd = new BudgetBD();
			flag = bd.budgetCreate(request,formData);
			forwardTo="success";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
			formData.setTransactionId(bd.getRID());
			return mapping.findForward(forwardTo);
		
		
	}

}
