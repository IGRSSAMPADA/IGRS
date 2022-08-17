package com.wipro.igrs.departmentalenquiry.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.departmentalenquiry.bd.DepartmentalEnquirySearchBD;
import com.wipro.igrs.officemaster.bd.OfficeBD;

public class PreDeptEnquirySearchAction extends BaseAction {
    

	private OfficeBD officeBD = new OfficeBD();
	private DepartmentalEnquirySearchBD deptEnquirySearchBD = new DepartmentalEnquirySearchBD();
    
    public PreDeptEnquirySearchAction() {
    }
    
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
    	
    	List officeTypeList = officeBD.getOfficeTypeList();
    	List complaintStatusList = deptEnquirySearchBD.getComplaintStatusList();
		
		request.setAttribute("officeTypeList", officeTypeList);
		request.setAttribute("complaintStatusList", complaintStatusList);
		
		return mapping.findForward("departmentalEnquiryPage");
    }

}