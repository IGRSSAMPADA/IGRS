package com.wipro.igrs.reconciliation.action;

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
import com.wipro.igrs.reconciliation.bd.ReconciliationBd;
import com.wipro.igrs.reconciliation.form.ReconciliationForm;



public class ReconciliationView extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(ReconciliationView.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		String fwText="search";
		String paramVal=request.getParameter("paramVal");
		if("search".equalsIgnoreCase(paramVal)){
			try{
			ReconciliationBd bd = new ReconciliationBd();
			bd.getDistrict(request);			
			bd.getFinYear(request);
			fwText="search";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			ActionErrors errors = new ActionErrors();
			ActionError error = new ActionError("businessError.msg");
			errors.add("key1",error);
			saveErrors(request,errors);
			return mapping.getInputForward();
					
		}
		}
		if("view".equalsIgnoreCase(paramVal)){
		ReconciliationForm formData = (ReconciliationForm) form;
		ReconciliationBd bd = null;
		
		ArrayList list = new ArrayList();
		try
		{
			
			String dstrct=formData.getDistrict();
			//System.out.println("District Name: " +dstrct );
			String fnclyr=formData.getFinancialYear();
			//System.out.println("District Name: " +fnclyr );
			String durtnFrm=formData.getDurationFrom();
			//System.out.println("District Name: " +durtnFrm );
			String durtnTo=formData.getDurationTo();
			//System.out.println("District Name: " +durtnTo );
			bd = new ReconciliationBd();
			list = bd.getAllHeads(dstrct,fnclyr,durtnFrm,durtnTo);
			
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
		
		request.setAttribute("detailedHeads",list);
		fwText="success";
		}
		return mapping.findForward(fwText);
		
	}

}
