package com.wipro.igrs.complaintdetails.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.complaintdetails.bd.ComplaintDetailsBD;
import com.wipro.igrs.complaintdetails.form.ComplaintDetailsForm;
import com.wipro.igrs.officemaster.bd.OfficeBD;

public class PreSearchComplaintAction extends BaseAction {
    

	private OfficeBD officeBD = new OfficeBD();
	private ComplaintDetailsBD compBD= new ComplaintDetailsBD();
    
    public PreSearchComplaintAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	
		try
		{
		    List officeTypeList = officeBD.getOfficeTypeList();
		    request.setAttribute("officeTypeList", officeTypeList);
			
			List complaintStatus =compBD.listComplaintStatus();
			request.setAttribute("complaintStatus", complaintStatus);
			
			((ComplaintDetailsForm)form).setPaymentDateFrom("");
			((ComplaintDetailsForm)form).setPaymentDateTo("");
			((ComplaintDetailsForm)form).setEmpCode("");
			((ComplaintDetailsForm)form).setEmpFirstName("");
			((ComplaintDetailsForm)form).setEmpMidName("");
			((ComplaintDetailsForm)form).setEmpLastName("");
			((ComplaintDetailsForm)form).setStatus("");
			((ComplaintDetailsForm)form).setLocation("");
		
		   return mapping.findForward("searchComplaintDetails");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}