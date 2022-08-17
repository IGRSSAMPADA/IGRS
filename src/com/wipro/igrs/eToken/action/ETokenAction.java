package com.wipro.igrs.eToken.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.eToken.form.ETokenForm;

public class ETokenAction extends BaseAction {
	
	String forwardJsp = "";
	private Logger logger = Logger.getLogger(ETokenAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response,HttpSession session) 
	throws Exception {
		

		String page=request.getParameter("page");
		logger.debug("page"+page);
		String language=(String)session.getAttribute("languageLocale");
		
		String roleId = (String)session.getAttribute("role");
		String funId = (String)session.getAttribute("functionId");
		String userId = (String)session.getAttribute("UserId");
		Date createdDate = new Date();
		SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
		String tDate = sdfrmt.format(createdDate);
		
		if(form != null)
		{
			ETokenForm eForm = (ETokenForm)form;
			eForm.setLanguage(language);

			if(page!= null)
			{
				if("".equals(page))
				{
					
				}
			}
		}
	
		return mapping.findForward(forwardJsp);
	}
}
