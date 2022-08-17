package com.wipro.igrs.branchmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.branchmaster.bd.BranchBD;
import com.wipro.igrs.branchmaster.dto.BranchDTO;
import com.wipro.igrs.branchmaster.form.BranchForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class PreUpdateBranchAction extends BaseAction{
    

	private BranchDTO branchDTO=new BranchDTO();
	private BranchBD branchBD=BranchBD.getInstance();
    
    public PreUpdateBranchAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String branchId = request.getParameter("branchId");
		branchDTO=branchBD.getBranchById(branchId);	
		
		((BranchForm)form).setBranchId(branchDTO.getBranchId());
		((BranchForm)form).setBranchName(branchDTO.getBranchName());
		((BranchForm)form).setBranchAddress(branchDTO.getBranchAddress());
		((BranchForm)form).setBranchPhoneNumber(branchDTO.getBranchPhoneNumber());
		((BranchForm)form).setBranchEmail(branchDTO.getBranchEmail());
		((BranchForm)form).setBranchCode(branchDTO.getBranchCode());
		((BranchForm)form).setBankId(branchDTO.getBankId());
		((BranchForm)form).setStatus(branchDTO.getStatus());
		
		((BranchForm)form).setOldName(branchDTO.getBranchName());
		((BranchForm)form).setBankName(branchDTO.getBankName());
		
		return mapping.findForward("updatebranchmaster");
	}

}