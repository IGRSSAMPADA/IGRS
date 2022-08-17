package com.wipro.igrs.bankmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.bankmaster.bd.BankBD;
import com.wipro.igrs.bankmaster.dto.BankDTO;
import com.wipro.igrs.bankmaster.form.BankForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class PreUpdateBankAction extends BaseAction{
    

	private BankDTO bankDTO=new BankDTO();
	private BankBD bankBD=BankBD.getInstance();
    
    public PreUpdateBankAction() {
    }

	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String bankId = request.getParameter("bankId");
		bankDTO=bankBD.getBankById(bankId);	
		System.out.println("##Bank Id: "+bankId);
		
		((BankForm)form).setBankId(bankDTO.getBankId());
		((BankForm)form).setBankName(bankDTO.getBankName());
		((BankForm)form).setBankAddress(bankDTO.getBankAddress());
		((BankForm)form).setBankPhoneNumber(bankDTO.getBankPhoneNumber());
		((BankForm)form).setStatus(bankDTO.getStatus());
		((BankForm)form).setOldName(bankDTO.getBankName());
		
		return mapping.findForward("updatebankmaster");
	}

}