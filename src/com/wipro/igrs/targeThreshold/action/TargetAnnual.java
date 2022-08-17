package com.wipro.igrs.targeThreshold.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.targeThreshold.bd.TargetBd;
import com.wipro.igrs.targeThreshold.form.TargetTLform;

public class TargetAnnual extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(TargetAnnual.class);
						public ActionForward execute(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response,HttpSession session)
					{
							TargetTLform formData = (TargetTLform) form;
					TargetBd bd = null;
					boolean flag = false;
					try
							{
							bd = new TargetBd();
							flag = bd.targetAnnual(formData);
							
							
							}
					catch(Exception e)
								{
								e.printStackTrace();
								}
					if(flag)
						{
						formData.setTargetId(bd.getRID());
						return mapping.findForward("success");
						}
					else
						{
						return mapping.findForward("failure");
						}
					}
	

}
