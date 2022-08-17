package com.wipro.igrs.officemaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.officemaster.bd.OfficeBD;
import com.wipro.igrs.officemaster.form.OfficeMasterForm;

public class DispatchOfficeMaster extends BaseAction {
    
OfficeBD officeBD = new OfficeBD();

    
    public DispatchOfficeMaster() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		//if(((OfficeMasterForm)form).getOfficeDTO().getPageName() == null)
		//{
			//((OfficeMasterForm)form).getOfficeDTO().setOfficeList((ArrayList)officeBD.getOfficeList());
			//System.out.println("Office number :>>>>>>>>>>>>>>>>>"+((ArrayList)officeBD.getOfficeList()).size());
		((OfficeMasterForm)form).setOfficeList(null);
		((OfficeMasterForm)form).setOfficeList((ArrayList)officeBD.getOfficeList());
		System.out.println("size<<<<<>>>>>>>>>"+((ArrayList)officeBD.getOfficeList()).size());
		request.removeAttribute("officeList");
		request.setAttribute("officeList", ((OfficeMasterForm)form).getOfficeList());
		//}
		//else 
		//	if(((OfficeMasterForm)form).getOfficeDTO().getPageName().equalsIgnoreCase("officecreate"))
		//{
			
			//mapping.findForward("createofficemaster");
		//}
		
		
		
		return mapping.findForward("viewofficemaster");
	}

}