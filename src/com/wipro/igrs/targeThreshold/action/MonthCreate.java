package com.wipro.igrs.targeThreshold.action;

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
import com.wipro.igrs.targeThreshold.bd.TargetBd;
import com.wipro.igrs.targeThreshold.form.TargetTLform;

public class MonthCreate extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(MonthCreate.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		boolean flag = false;
		TargetTLform data = (TargetTLform) form;
		TargetBd bd = null;
		String yearId = data.getFinancialYear();
		String droId = data.getOfficeName();
		try
		{
			bd = new TargetBd();
			flag = bd.monthData(request,data,yearId,droId);
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
				data.setTargetId(bd.getRID());
			return mapping.findForward("success");
			}
			else
			{
			return mapping.findForward("fail");
			}
		
	}
	

}
