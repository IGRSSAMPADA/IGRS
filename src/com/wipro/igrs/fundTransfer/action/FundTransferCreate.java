package com.wipro.igrs.fundTransfer.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.fundTransfer.bd.FundTransferBD;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;

public class FundTransferCreate extends BaseAction 
{
	private  Logger logger = 
		(Logger) Logger.getLogger(FundTransferCreate.class);
	
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		FundTransferForm formData = (FundTransferForm) form;
		FundTransferBD bd = null;
		boolean flag = false;
		String forwardTo = null;
		String dhFrom = formData.getDetaildHeadFrom();
		String dhTo = formData.getDetaildHeadTo();
		try
		{
			
			if(dhFrom.equals(dhTo))
			{
				//System.out.println("I am in action class String are equals-------");
				throw new Exception("DetaliedHeadFrom and DetailedHeadTo Should not be equal");
				
			}
			else
			{
				bd = new FundTransferBD();
				flag = bd.fundTransfer(formData);
				forwardTo="success";
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			request.setAttribute("messageBundle",e.getMessage());
			forwardTo="fail";
			
		}
			//System.out.println("forwardTo value is-----------------"+forwardTo);
			return mapping.findForward(forwardTo);
		
	}
		
	

}
