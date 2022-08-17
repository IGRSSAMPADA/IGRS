package com.wipro.igrs.suspensionenquiry.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officemaster.bd.OfficeBD;

public class PreSearchSuspensionAction extends BaseAction {
	
	private OfficeBD officeBD = new OfficeBD();
    
    public PreSearchSuspensionAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		try
		{
		List officeTypeList = officeBD.getOfficeTypeList();
		
		request.setAttribute("officeTypeList", officeTypeList);
		
		return mapping.findForward("suspensionEnquirySearch");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
    

}