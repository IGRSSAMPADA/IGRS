package com.wipro.igrs.bankmaster.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.bankmaster.bd.BankBD;
import com.wipro.igrs.bankmaster.form.BankForm;
import com.wipro.igrs.baseaction.action.BaseAction;

public class ViewBankMasterAction extends BaseAction {
    
   
    public ViewBankMasterAction() {
    }
    

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String target = "success";
		
		/* Populate the activity form */

		BankBD bankBD = BankBD.getInstance();
		request.getSession(false).setAttribute("bankId", null);
		ArrayList bankList = bankBD.getBankList();
		

		
//		if (bankForm.getPageName() != null)
//			if (bankForm.getPageName().equalsIgnoreCase("addbankmaster")) {
//				/* Add bank master */
//				bankDTO.setBankName(bankForm.getBankName());
//				bankDTO.setBankAddress(bankForm.getBankAddress());
//				bankDTO.setBankPhoneNumber(bankForm.getBankPhoneNumber());
//				bankDTO.setCreatedBy((String)request.getSession().getAttribute("userid"));
//				bankDTO.setUpdateBy((String)request.getSession().getAttribute("userid"));
//				
//				bankBD.addBankMaster(bankDTO);	
//				
//				target="successAdd";
//			} 
//			
//			else if (bankForm.getPageName().equalsIgnoreCase("updatebankmaster")){
//				/* Update bank master */
//				
//				bankDTO.setBankId(bankForm.getBankId());
//				bankDTO.setBankName(bankForm.getBankName());
//				bankDTO.setBankAddress(bankForm.getBankAddress());
//				bankDTO.setBankPhoneNumber(bankForm.getBankPhoneNumber());
//				bankDTO.setStatus(bankForm.getStatus());
//				bankDTO.setUpdateBy((String)request.getSession().getAttribute("userid"));
//				
//				bankBD.updateBankMaster(bankDTO);
//				
//				target="successUpdate";
//			}
//			else if (bankForm.getPageName().equalsIgnoreCase("bankmaster")){
//				/* Delete bank master */
//				
//				String [] selected=bankForm.getSelected();
//				
//				for (int i = 0; i < selected.length; i++) {
//					bankDTO=bankBD.getBankById(selected[i]);
//					bankBD.deleteBankMaster(bankDTO);
//				}
//					
//				target="successDelete";
//			}

		
	//	((BankForm)form).setBankList(bankList);
		request.setAttribute("bankList", bankList);
		return mapping.findForward(target);
			
	}

}