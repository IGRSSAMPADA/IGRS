package com.wipro.igrs.fundTransfer.action;

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
import com.wipro.igrs.fundTransfer.bd.FundTransferBD;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;



public class HeadDetails extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(HeadDetails.class);
	
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		FundTransferForm formData = (FundTransferForm) form;
		String droId = formData.getDro();
		FundTransferBD bd = null;
		FundTransferForm fundForm = null;
		try
		{
			fundForm = new FundTransferForm();
			bd = new FundTransferBD();
			fundForm = bd.headDetails(request,droId);
		
			
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
	//	HttpSession session2 = request.getSession();
		session.getAttribute("key1");
		//HttpSession session1 = request.getSession();
		 
		session.setAttribute("fundDetailsKey",fundForm);
		return mapping.findForward("success");
	}

}

