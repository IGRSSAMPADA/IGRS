package com.wipro.igrs.regTime.action;

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
import com.wipro.igrs.regTime.bd.RegTimeingsBd;
import com.wipro.igrs.regTime.form.RegTimeForm;

public class ModuleView extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ModuleView.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		RegTimeForm formData = (RegTimeForm) form;
		RegTimeingsBd bd = null;
		ArrayList list = new ArrayList();
		try
		{
			bd = new RegTimeingsBd();
			list = bd.getModules();
			
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
		
		request.setAttribute("modulesKey",list);
		return mapping.findForward("success");
	}

}
