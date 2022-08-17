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

public class MonthOfficeIds extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(MonthOfficeIds.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		TargetTLform formdata = (TargetTLform) form;
		String yearId = formdata.getFinancialYear();
		String droId = formdata.getOfficeName();
		TargetBd bd = null;
		try
		{
			bd = new TargetBd();
			bd.getOfficeId(request);
			bd.getYear(request);
			//bd.getMonthId(request,yearId);
			formdata = bd.monthValues(droId,yearId);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("businessError.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
					
		}
	//	HttpSession session1 = request.getSession();
		session.setAttribute("monthValueKey",formdata);
		
		return mapping.findForward("success");
		
		
		
	}
	

}
