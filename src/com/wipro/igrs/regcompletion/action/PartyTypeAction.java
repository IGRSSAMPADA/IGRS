package com.wipro.igrs.regcompletion.action;

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


public class PartyTypeAction extends Action 
{
	private  Logger logger = 
		(Logger) Logger.getLogger(PartyTypeAction.class);
			public ActionForward execute(ActionMapping mapping,
										ActionForm form,
										HttpServletRequest request,
										HttpServletResponse response)
			{
				RegCompMailForm data = (RegCompMailForm) form;
				RegCompMailBD bd = null;
					try
					{
					bd = new RegCompMailBD();
					data.setMailSendType("A");
					bd.getPartyTxnId(request);
								
					
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
