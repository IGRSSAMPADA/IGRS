package com.wipro.igrs.budgetRequest.action;



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
import com.wipro.igrs.budgetRequest.bd.BudgetBD;
import com.wipro.igrs.budgetRequest.form.BudgetForm;



public class OfficeMaster extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(OfficeMaster.class);
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response,HttpSession session)
	{
		BudgetForm budgetForm = (BudgetForm) form;
		ArrayList list = new ArrayList();
		try
			{
			logger.info("i am in BudgetRequest action");
			logger.error("I am BudgetRequest error");
			logger.debug("I am in BudgetRequest debug");
			String majorId = budgetForm.getMajorHeadMaster();
			String subMjorId = budgetForm.getSubMajorHeadMaster();
			String minorId = budgetForm.getMinorHeadMaster();
			String schemeId = budgetForm.getSchemeMaster();
			String segmentId = budgetForm.getSegmentMaster();
			String ojectId = budgetForm.getObjectMaster();
			
			
				
				BudgetBD bd = new BudgetBD();
				bd.getOfficeNames(request);
				bd.getFiscalYear(request);
				bd.getMajorHeadId(request);
				bd.getSubMajorHeadId(request,majorId);
				bd.getMinorHeadId(request,subMjorId,majorId);
				bd.getSchemeNames(request,minorId,subMjorId,majorId);
				bd.getSegmentNames(request,schemeId,minorId,subMjorId,majorId);
				bd.getObjectNames(request,segmentId,schemeId,minorId,subMjorId,majorId);
				bd.getDHead(request,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
				//bd.getMonthId(request,yearId);
				
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


