package com.wipro.igrs.regcompletion.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.regcompletion.bd.RegCompMailBD;
import com.wipro.igrs.regcompletion.form.RegCompMailForm;

public class RegCompMailAction extends Action
{
	private  Logger logger = 
		(Logger) Logger.getLogger(RegCompMailAction.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response)
	{
		boolean flag = false;
		RegCompMailForm data = (RegCompMailForm) form;
		RegCompMailBD bd = null;
		String fowardTo=null;
		try
		{
			bd = new RegCompMailBD();
			
			flag = bd.mailSendType(data);
			
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("error.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
			
			
		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			request.setAttribute("mesgBundle", e.getMessage());
//			fowardTo="fail";
//		}
		
			
			return mapping.findForward("success");
	}
}
