package com.wipro.igrs.receiptBudget.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.receiptBudget.bd.BudgetBD;
import com.wipro.igrs.receiptBudget.form.ReceiptBudgetForm;

public class ReceiptBudgetCreate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReceiptBudgetCreate.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		ReceiptBudgetForm formData = (ReceiptBudgetForm) form;
		boolean flag = false;
		BudgetBD bd = null;
		String yearId = formData.getFinancialYear();
		try
		{
			bd = new BudgetBD();
			//bd.getMonthId(request,yearId);
			flag = bd.receiptBudgetCreate(request,formData);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(flag)
		{
			formData.setTransactionId(bd.RID);
			return mapping.findForward("success");
		}
		else
		{
			return mapping.findForward("failure");
		}
		
	}

}
