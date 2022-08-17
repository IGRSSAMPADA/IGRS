package com.wipro.igrs.reconciliation.action;

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
import com.wipro.igrs.reconciliation.bd.ReconciliationBd;
import com.wipro.igrs.reconciliation.form.ReconciliationForm;


public class ReconciliationCreate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReconciliationCreate.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		boolean flag = false;
		ReconciliationForm data = (ReconciliationForm) form;
		ReconciliationBd bd = null;
		String fowardTo=null;
		try
		{
			bd = new ReconciliationBd();
			
				flag = bd.reconCreate(request,data);
				fowardTo="success";
			
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
			request.setAttribute("mesgBundle", e.getMessage());
			fowardTo="fail";
		}
		
			
			return mapping.findForward(fowardTo);
			
	}

}
