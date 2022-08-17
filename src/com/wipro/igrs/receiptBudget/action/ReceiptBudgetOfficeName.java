package com.wipro.igrs.receiptBudget.action;

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
import com.wipro.igrs.receiptBudget.bd.BudgetBD;
import com.wipro.igrs.receiptBudget.form.ReceiptBudgetForm;

public class ReceiptBudgetOfficeName extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReceiptBudgetOfficeName.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		ReceiptBudgetForm receiptForm = (ReceiptBudgetForm) form;
		String yearId = receiptForm.getFinancialYear();
		BudgetBD bd = null;
		try
		{
		    String majorId = receiptForm.getMajorHeadMaster();
			String subMjorId = receiptForm.getSubMajorHeadMaster();
			String minorId = receiptForm.getMinorHeadMaster();
			String schemeId = receiptForm.getSchemeMaster();
			String segmentId = receiptForm.getSegmentMaster();
			String ojectId = receiptForm.getObjectMaster();
			
			bd = new BudgetBD();
			bd.getOfficeNames(request);
			bd.getFiscalYear(request);
			
			bd.getMajorHeadId(request);
			bd.getSubMajorHeadId(request,majorId);
			bd.getMinorHeadId(request,subMjorId,majorId);
			bd.getSchemeNames(request,minorId,subMjorId,majorId);
			bd.getSegmentNames(request,schemeId,minorId,subMjorId,majorId);
			bd.getObjectNames(request,segmentId,schemeId,minorId,subMjorId,majorId);
			bd.getDHead(request,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
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


