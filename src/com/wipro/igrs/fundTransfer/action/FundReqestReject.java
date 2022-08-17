package com.wipro.igrs.fundTransfer.action;

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
import com.wipro.igrs.fundTransfer.bd.FundTransferBD;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;

public class FundReqestReject extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(FundReqestReject.class);
	
	public ActionForward execute(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session)
						{
						boolean flag = false;
						FundTransferForm data = (FundTransferForm) form;
						String droId = data.getDro();
						
						//HttpSession session2 = request.getSession();
						String droKeyId = (String) session.getAttribute("key1");
						
						FundTransferBD bd = null;
						try
						{
						bd = new FundTransferBD();
						
						flag = bd.fundReqReject(droKeyId,data);
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
						
						}

}
