package com.wipro.igrs.receiptBudget.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;



public class ReceiptBudgetUpdate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReceiptBudgetUpdate.class);
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)
	{
	/*	boolean flag = false;
		ReceiptBudgetForm data = (ReceiptBudgetForm) form;
		BudgetBD bd = null;
		try
		{
			bd = new BudgetBD();
			System.out.println(" i am going to BD dddddddddddddd");
			flag = bd.budgetRequestUpdate(data);
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

			if(flag)
			{
			return mapping.findForward("success");
			}
			else
			{
			return mapping.findForward("fail");
			}
		*/
		return mapping.findForward("success");
	}

}
