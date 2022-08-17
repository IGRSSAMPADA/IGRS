package com.wipro.igrs.branchmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.bankmaster.bd.BankBD;
import com.wipro.igrs.bankmaster.bd.IBankBD;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.branchmaster.form.BranchForm;

public class PreCreateBranchAction extends BaseAction {
    
    public PreCreateBranchAction() {
    }
    
    IBankBD bankBD=BankBD.getInstance();
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		((BranchForm)form).setBranchName("");
		((BranchForm)form).setBranchEmail("");
		((BranchForm)form).setBranchPhoneNumber("");
		((BranchForm)form).setBranchAddress("");
		((BranchForm)form).setAllBanks(bankBD.getBankList());
		return mapping.findForward("createbranchmaster");
	}

}