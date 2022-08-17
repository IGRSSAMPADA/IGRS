package com.wipro.igrs.empfund.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empfund.bd.EmpFundBD;
import com.wipro.igrs.empfund.bd.IEmpFundBD;
import com.wipro.igrs.empfund.form.EmpFundForm;

public class PreEditEmpFundAction extends BaseAction {
    
	
	private IEmpFundBD empFundBD = new EmpFundBD();
    
    public PreEditEmpFundAction() {
    }

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		EmpFundForm bean = (EmpFundForm)form;

		List fundTypeList = empFundBD.getFundTypeList();
    	
    	request.setAttribute("fundTypeList", fundTypeList);
    	
    	return mapping.findForward("editEmpFundPage");
	}

}