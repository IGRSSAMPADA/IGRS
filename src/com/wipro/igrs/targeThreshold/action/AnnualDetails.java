package com.wipro.igrs.targeThreshold.action;

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
import com.wipro.igrs.targeThreshold.bd.TargetBd;
import com.wipro.igrs.targeThreshold.form.TargetTLform;

public class AnnualDetails extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(AnnualDetails.class);
	public ActionForward execute(ActionMapping mapping,ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		TargetTLform data = (TargetTLform) form;
		
		String targetId = data.getTargetId();
		
		TargetBd bd = null;
		TargetTLform targetForm = null;
		try
		{
			targetForm = new TargetTLform();
			bd = new TargetBd();
			targetForm = bd.AnnualDetails(request,targetId);
		
			
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
//		HttpSession session2 = request.getSession();
		session.getAttribute("key1");
		
//		HttpSession session1 = request.getSession();
		 
		session.setAttribute("annualDetails",targetForm);
		return mapping.findForward("success");
	}

}
