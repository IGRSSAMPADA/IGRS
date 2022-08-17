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

public class DroIdList extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(DroIdList.class);
							public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
						{
						FundTransferForm formdata = (FundTransferForm) form;
						FundTransferBD bd = null;
						try
						{
							bd = new FundTransferBD();
						bd.listOfDros(request);
						
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
						return mapping.findForward("success");




}

}
