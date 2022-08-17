package com.wipro.igrs.branchmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.branchmaster.bd.BranchBD;
import com.wipro.igrs.branchmaster.form.BranchForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ViewBranchMasterAction extends BaseAction {
    
   
    public ViewBranchMasterAction() {
    }
    

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String target = "success";
		
		/* Populate the activity form */

		BranchBD branchBD = BranchBD.getInstance();
		String bankId=null;
		if(request.getSession(false).getAttribute("bankId")==null)
		{
			 bankId=(String)request.getParameter("bankId");
			System.out.println("bankId in ViewBranchMasterAction (if) :"+bankId);
			request.getSession(false).setAttribute("bankId", bankId);	
		}
		else
			bankId=(String) request.getSession(false).getAttribute("bankId");
		
		System.out.println("bankId in ViewBranchMasterAction (without if) :"+bankId);
		ArrayList branchList = branchBD.getBranchList(bankId);
		request.setAttribute("branchList", branchList);
		return mapping.findForward(target);
			
	}

}