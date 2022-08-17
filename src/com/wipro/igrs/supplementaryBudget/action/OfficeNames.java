package com.wipro.igrs.supplementaryBudget.action;

import java.io.IOException;

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
import com.wipro.igrs.supplementaryBudget.bd.BudgetBD;
import com.wipro.igrs.supplementaryBudget.form.SupplementaryBudgetForm;


public class OfficeNames extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(OfficeNames.class);
	
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse resopnse,HttpSession session)
	{
		SupplementaryBudgetForm formData = (SupplementaryBudgetForm) form;
		
		try
		{
			logger.info("i am in supplementary action");
			logger.error("I am supplementary error");
			logger.debug("I am in supplementary debug");
			
			
			String majorId = formData.getMajorHeadMaster();
			String subMjorId = formData.getSubMajorHeadMaster();
			String minorId = formData.getMinorHeadMaster();
			String schemeId = formData.getSchemeMaster();
			String segmentId = formData.getSegmentMaster();
			String ojectId = formData.getObjectMaster();
			
			BudgetBD bd = new BudgetBD();
			bd.getOfficeNames(request);
			
			bd.getMajorHeadId(request);
			bd.getSubMajorHeadId(request,majorId);
			bd.getMinorHeadId(request,subMjorId,majorId);
			bd.getSchemeNames(request,minorId,subMjorId,majorId);
			bd.getSegmentNames(request,schemeId,minorId,subMjorId,majorId);
			bd.getObjectNames(request,segmentId,schemeId,minorId,subMjorId,majorId);
			bd.getDHead(request,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
			
			bd.getFiscalYear(request);
			
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
		//request.setAttribute("districtNames",list);
		return mapping.findForward("success");
	}
}


