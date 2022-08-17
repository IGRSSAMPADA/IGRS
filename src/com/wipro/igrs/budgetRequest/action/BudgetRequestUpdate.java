package com.wipro.igrs.budgetRequest.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;



public class BudgetRequestUpdate extends BaseAction
{
	
	private  Logger logger = 
		(Logger) Logger.getLogger(BudgetRequestUpdate.class);
	
	public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)
	{
	/*	boolean flag = false;
		BudgetForm data = (BudgetForm) form;
		BudgetBD bd = null;
		try
		{
			bd = new BudgetBD();
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
			}*/
		return mapping.findForward("success");
		
	}
								

}
