package com.wipro.igrs.supplementaryBudget.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.supplementaryBudget.bd.BudgetBD;
import com.wipro.igrs.supplementaryBudget.form.SupplementaryBudgetForm;

public class SupplementaryBudgetCreate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(SupplementaryBudgetCreate.class);
	public ActionForward execute(ActionMapping mapping,
						ActionForm form,
						HttpServletRequest request,
						HttpServletResponse response,HttpSession session)
		{
		SupplementaryBudgetForm formData = (SupplementaryBudgetForm) form;
			boolean flag = false;
			BudgetBD bd = null;
			String yearId = formData.getFinancialYear();
			try
			{
				bd = new BudgetBD();
				//bd.getMonthId(request,yearId);
				flag = bd.supCreate(request,formData);
				
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			if(flag)
			{
				formData.setTransactionId(bd.getRID());
				return mapping.findForward("success");
			}
			else
			{
				return mapping.findForward("failure");
			}
			
		}

}
