package com.wipro.igrs.regTime.action;

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
import com.wipro.igrs.regTime.bd.RegTimeingsBd;
import com.wipro.igrs.regTime.form.RegTimeForm;



public class ModuleUpdate extends BaseAction
{
	
	
	private  Logger logger = 
		(Logger) Logger.getLogger(ModuleUpdate.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		boolean flag = false;
		RegTimeForm data = (RegTimeForm) form;
		String function = data.getFunction();
	//	HttpSession session2 = request.getSession();
		String droKeyId = (String) session.getAttribute("key1");
		RegTimeingsBd bd = null;
		try
		{
			bd = new RegTimeingsBd();
			flag = bd.moudleUpdate(function,data);
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
