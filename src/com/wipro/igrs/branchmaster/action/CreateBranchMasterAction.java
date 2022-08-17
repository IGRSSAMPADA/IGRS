package com.wipro.igrs.branchmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.branchmaster.bd.BranchBD;
import com.wipro.igrs.branchmaster.dto.BranchDTO;
import com.wipro.igrs.branchmaster.exception.BranchNameAlreadyExistException;
import com.wipro.igrs.branchmaster.form.BranchForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class CreateBranchMasterAction extends BaseAction {
    
	private BranchDTO branchDTO=new BranchDTO();
	private BranchBD branchBD=BranchBD.getInstance();
    
    public CreateBranchMasterAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		BranchForm branchForm=(BranchForm) form;
		
		if(isCancelled(request))
		{
			return mapping.findForward("viewBranchMasterAction");
		}
	
		branchDTO.setBranchName(branchForm.getBranchName());
		branchDTO.setBranchAddress(branchForm.getBranchAddress());
		branchDTO.setBranchPhoneNumber(branchForm.getBranchPhoneNumber());
		branchDTO.setBranchEmail(branchForm.getBranchEmail());
		branchDTO.setBranchCode(branchForm.getBranchCode());
		branchDTO.setBankId(branchForm.getBankId());
		branchDTO.setCreatedBy((String)request.getSession().getAttribute("UserId"));
		branchDTO.setUpdateBy((String)request.getSession().getAttribute("UserId"));
		
		try {	
			branchBD.addBranchMaster(branchDTO);
			System.out.println("## in add action branch name is (try) "+branchDTO.getBranchName());
			return mapping.findForward("successCreate");
		} catch (BranchNameAlreadyExistException e) {
			
			System.out.println("## in add action branch name is (catch) "+branchDTO.getBranchName());
			ActionErrors errors=new ActionErrors();
			errors.add("branchNameAlreadyExist",new ActionError("branch.branchNameAlreadyExist"));
			saveErrors(request, errors);
			return mapping.findForward("alreadyExist");

		}
		
	}

}