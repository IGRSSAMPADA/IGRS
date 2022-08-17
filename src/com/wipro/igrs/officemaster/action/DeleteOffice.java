package com.wipro.igrs.officemaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officemaster.bd.OfficeBD;
import com.wipro.igrs.officemaster.form.OfficeMasterForm;

public class DeleteOffice extends BaseAction {
    
OfficeBD officeBD = new OfficeBD();

    
    public DeleteOffice() {
    }
    
   

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		OfficeMasterForm theForm = (OfficeMasterForm)form;
		//  session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
	//	System.out.println("Selected size = "+theForm.getSelected().length );
		officeBD.deleteOffice(theForm.getSelected(),roleId,funId,userId);
		
		return mapping.findForward("success");
	}

}