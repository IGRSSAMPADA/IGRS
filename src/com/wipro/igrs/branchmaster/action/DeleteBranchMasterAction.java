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

public class DeleteBranchMasterAction extends BaseAction {
    
	private BranchDTO branchDTO=new BranchDTO();
	private BranchBD branchBD=BranchBD.getInstance();
  
    public DeleteBranchMasterAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		BranchForm branchForm=(BranchForm) form;
		
		String [] selected=branchForm.getSelected();
		if(selected!=null){
			for (int i = 0; i < selected.length; i++) {
				
				branchDTO=branchBD.getBranchById(selected[i]);
				branchBD.deleteBranchMaster(branchDTO);
			}
		}
		return mapping.findForward("viewBranchMasterAction");
	}

}