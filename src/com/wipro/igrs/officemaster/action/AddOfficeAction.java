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

public class AddOfficeAction extends BaseAction {
    
ActionErrors errors=new ActionErrors();
OfficeBD officeBD = new OfficeBD();
    
    public AddOfficeAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		OfficeMasterForm newOffice = (OfficeMasterForm)form;
    	newOffice.getOfficeDTO().setCreatedby((String)session.getAttribute("UserId"));
    	//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
        try
        {
        //	System.out.println("Office to add "+ newOffice.getOfficeDTO());
        	officeBD.addOfficemaster(newOffice.getOfficeDTO(),roleId,funId,userId);
        }
        catch(OfficeFoundException e)
        {
        	errors.add("officeNameError", new ActionError("error.officeName"));
        	saveErrors(request, errors);
        	return mapping.findForward("createofficemaster");
        }
        return mapping.findForward("success");
	}

}