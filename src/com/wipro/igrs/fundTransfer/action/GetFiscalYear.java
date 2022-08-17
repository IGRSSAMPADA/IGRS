package com.wipro.igrs.fundTransfer.action;

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
import com.wipro.igrs.fundTransfer.bd.FundTransferBD;
import com.wipro.igrs.fundTransfer.form.FundTransferForm;

public class GetFiscalYear extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(GetFiscalYear.class);
	
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		FundTransferForm formdata = (FundTransferForm) form;
		FundTransferBD bd = null;
		String majorId = formdata.getMajorHead();
		String subMjorId = formdata.getSubMajorHead();
		String minorId = formdata.getMinorHead();
		String schemeId = formdata.getScheme();
		String segmentId = formdata.getSegment();
		String ojectId = formdata.getObject();
		try
		{
			bd = new FundTransferBD();
			bd.getDro(request);
			bd.getDistrict(request);
			//formdata.setFundType("Object");
			bd.getFinYear(request);
			bd.getMajorHeadId(request);
			bd.getSubMajorHeadId(request,majorId);
			bd.getMinorHeadId(request,subMjorId,majorId);
			bd.getSchemeNames(request,minorId,subMjorId,majorId);
			bd.getSegmentNames(request,schemeId,minorId,subMjorId,majorId);
			bd.getObjectNames(request,segmentId,schemeId,minorId,subMjorId,majorId);
			bd.getDHead(request,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
			String dhId = formdata.getDetaildHeadFrom();
			bd.getDHeadTo(request,dhId);
		
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
		return mapping.findForward("success");
	
	}

}
