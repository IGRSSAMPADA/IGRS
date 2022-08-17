package com.wipro.igrs.fundTransfer.action;

import java.sql.SQLException;
import java.util.ArrayList;

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

public class DroDetails extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(DroDetails.class);
			public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)
					{
					FundTransferForm formData = (FundTransferForm) form;
					String droId = formData.getDro();
					ArrayList list = new ArrayList();
					FundTransferBD bd = null;
					FundTransferForm fundForm = null;
					try
					{
						bd = new FundTransferBD();
						list = bd.getHeadList(droId);
						
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
					
					request.setAttribute("droIds",list);
					return mapping.findForward("success");
					}

}
