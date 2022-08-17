package com.wipro.igrs.branchmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.wipro.igrs.branchmaster.bd.BranchBD;
import com.wipro.igrs.branchmaster.dto.BranchDTO;
import com.wipro.igrs.branchmaster.exception.BranchNameAlreadyExistException;
import com.wipro.igrs.branchmaster.form.BranchForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class UpdateBranchMasterAction extends BaseAction {
    

	private BranchDTO branchDTO=new BranchDTO();
	private BranchBD branchBD=BranchBD.getInstance();
    
    public UpdateBranchMasterAction() {
    }
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		BranchForm branchForm=(BranchForm) form;
		
		if(isCancelled(request))
		{
			return mapping.findForward("viewBranchMasterAction");
		}
		
		System.out.println("###Update Branch Action");
		
		
		branchDTO.setBranchId(branchForm.getBranchId());
		branchDTO.setBranchName(branchForm.getBranchName());
		branchDTO.setBranchAddress(branchForm.getBranchAddress());
		branchDTO.setBranchPhoneNumber(branchForm.getBranchPhoneNumber());
		branchDTO.setBranchEmail(branchForm.getBranchEmail());
		branchDTO.setStatus(branchForm.getStatus());
		branchDTO.setBankId(branchForm.getBankId());
		branchDTO.setUpdateBy((String)request.getSession().getAttribute("UserId"));
		
		System.out.println("Branch Address:"+branchDTO.getBranchAddress());
		try {
			System.out.println("##"+branchForm.getOldName());
			branchBD.updateBranchMaster(branchDTO, branchForm.getOldName());
			
			return mapping.findForward("successUpdate");
			
		} catch (BranchNameAlreadyExistException e) {
			
			ActionErrors errors=new ActionErrors();
			errors.add("branchNameAlreadyExist",new ActionError("branch.branchNameAlreadyExist"));
			saveErrors(request, errors);
			return mapping.findForward("alreadyExist");
		}
	}

}