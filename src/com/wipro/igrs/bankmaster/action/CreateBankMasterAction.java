package com.wipro.igrs.bankmaster.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.bankmaster.bd.BankBD;
import com.wipro.igrs.bankmaster.dto.BankDTO;
import com.wipro.igrs.bankmaster.exception.BankNameAlreadyExistException;
import com.wipro.igrs.bankmaster.form.BankForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class CreateBankMasterAction extends BaseAction {
    
	private BankDTO bankDTO=new BankDTO();
	private BankBD bankBD=BankBD.getInstance();
    
    public CreateBankMasterAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		 session = request.getSession();
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		if(isCancelled(request))
		{
			return mapping.findForward("viewBankMasterAction");
		}
		
		BankForm bankForm=(BankForm) form;
		
		bankDTO.setBankName(bankForm.getBankName());
		bankDTO.setBankAddress(bankForm.getBankAddress());
		bankDTO.setBankPhoneNumber(bankForm.getBankPhoneNumber());
		bankDTO.setCreatedBy((String)request.getSession().getAttribute("UserId"));
		bankDTO.setUpdateBy((String)request.getSession().getAttribute("UserId"));
		
		try {	
			bankBD.addBankMaster(bankDTO,roleId,funId,userId);
			return mapping.findForward("successCreate");
		} catch (BankNameAlreadyExistException e) {
			
			ActionErrors errors=new ActionErrors();
			errors.add("bankNameAlreadyExist",new ActionError("bank.bankNameAlreadyExist"));
			saveErrors(request, errors);
			return mapping.findForward("alreadyExist");

		}
		
	}

}