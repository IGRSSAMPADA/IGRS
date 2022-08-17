package com.wipro.igrs.reconciliation.action;

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

public class GetOfficeDistrictId extends BaseAction
{
	private  Logger logger = 
		(Logger) Logger.getLogger(GetOfficeDistrictId.class);
	public ActionForward execute(ActionMapping mapping,
									ActionForm form,
									HttpServletRequest request,
									HttpServletResponse response,HttpSession session)
	{
		ReconciliationForm formData = (ReconciliationForm) form;
		ReconciliationBd bd = null;
		String majorId = formData.getMajorHead();
		String subMjorId = formData.getSubMajorHead();
		String minorId = formData.getMinorHead();
		String schemeId = formData.getScheme();
		String segmentId = formData.getSegment();
		String ojectId = formData.getObject();
		try
		{
			bd = new ReconciliationBd();
			bd.getDistrict(request);
			bd.getDro(request);
			bd.getFinYear(request);
			bd.getDetailedTo(request);
			
			bd.getMajorHeadId(request);
			bd.getSubMajorHeadId(request,majorId);
			bd.getMinorHeadId(request,subMjorId,majorId);
			bd.getScheme(request,minorId,subMjorId,majorId);
			bd.getSegmentNames(request,schemeId,minorId,subMjorId,majorId);
			bd.getObjectNames(request,segmentId,schemeId,minorId,subMjorId,majorId);
			bd.getDHead(request,ojectId,segmentId,schemeId,minorId,subMjorId,majorId);
			
			
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
