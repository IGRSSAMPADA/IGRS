package com.wipro.igrs.bankmaster.action;

import java.util.StringTokenizer;

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

public class DeleteBankMasterAction extends BaseAction {
    
	private BankDTO bankDTO=new BankDTO();
	private BankBD bankBD=BankBD.getInstance();
  
    public DeleteBankMasterAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
	      String roleId = (String)session.getAttribute("role");
		  String funId = (String)session.getAttribute("functionId");
		  String userId = (String)session.getAttribute("UserId");
		
		String deleteBank = request.getParameter("deleteBank");
		StringTokenizer st = new StringTokenizer(deleteBank, "*");

		while (st.hasMoreTokens()) {
			bankBD.deleteBankMaster(st.nextToken(),roleId,funId,userId);

		}

		return mapping.findForward("viewBankMasterAction");
	}

}