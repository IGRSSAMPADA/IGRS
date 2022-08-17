package com.wipro.igrs.templateDictionary.action;

import java.io.IOException;
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
import com.wipro.igrs.templateDictionary.bd.TemplateBd;
import com.wipro.igrs.templateDictionary.form.TemplateForm;

public class GetModulesAction extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(GetModulesAction.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		TemplateForm data = (TemplateForm) form;
		ArrayList list = new ArrayList();
		TemplateBd bd = null;
		
		try
			{
				bd = new TemplateBd();
				bd.getMoudleName(request);
				bd.getSubMoudle(request);
				bd.getFunction(request);
			}
		catch(IOException ex)
			{
				ex.printStackTrace();
				ActionErrors errors = new ActionErrors();
				ActionError error = new ActionError("businessError.msg");
				errors.add("key1",error);
				saveErrors(request,errors);
				return mapping.getInputForward();
			}
			return mapping.findForward("success");
		}
		
		
	}


