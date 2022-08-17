package com.wipro.igrs.templateDictionary.action;

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
import com.wipro.igrs.templateDictionary.bd.TemplateBd;
import com.wipro.igrs.templateDictionary.form.TemplateForm;

public class TemplateCreateAction extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TemplateCreateAction.class);
	
		public ActionForward execute(ActionMapping mapping,
							ActionForm form,
							HttpServletRequest request,
							HttpServletResponse response,HttpSession session)
				{
				
				
				boolean flag = false;
				TemplateForm data = (TemplateForm) form;
				TemplateBd bd = null;
					try
					{
							bd = new TemplateBd();
							flag = bd.tempCreate(data);
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
				catch(Exception e)
						{
								e.printStackTrace();
								//request.setAttribute("mesgBundle", e.getMessage());
								//fowardTo="fail";
						}
				
				if(flag)
					{
						
						return mapping.findForward("success");
					}
				else
						{
							return mapping.findForward("failed");
						}


				}
	

}
