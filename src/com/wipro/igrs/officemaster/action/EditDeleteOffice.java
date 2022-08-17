package com.wipro.igrs.officemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officemaster.bd.OfficeBD;
import com.wipro.igrs.officemaster.exception.OfficeFoundException;
import com.wipro.igrs.officemaster.form.OfficeMasterForm;

public class EditDeleteOffice extends BaseAction {
    
	ActionErrors errors=new ActionErrors();
	OfficeBD officeBD = new OfficeBD();
    
    public EditDeleteOffice() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		OfficeMasterForm theForm = (OfficeMasterForm)form;
		//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		
		//if(theForm.getOfficeDTO().getPageName().equalsIgnoreCase("officeupdate"))
		//{
			theForm.getOfficeDTO().setUpdatedby((String)session.getAttribute("UserId"));
			try
			{
			officeBD.updateOfficemaster(theForm.getOfficeDTO(),roleId,funId,userId);
			return mapping.findForward("success");
			}
			catch(OfficeFoundException e)
			{
				errors.add("officeNameError", new ActionError("error.officeName"));
				saveErrors(request, errors);
				return mapping.findForward("updateofficemaster");
			}
		//}
		//return null;
	}

}