package com.wipro.igrs.empfund.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empfund.bd.EmpFundBD;
import com.wipro.igrs.empfund.bd.IEmpFundBD;
import com.wipro.igrs.empfund.dto.EmpFundDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;
import com.wipro.igrs.empfund.form.EmpFundForm;

public class DisplayEmpFundAction extends BaseAction {
    
	private IEmpFundBD empFundBD = new EmpFundBD();

    
    public DisplayEmpFundAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		EmpFundForm bean = (EmpFundForm)form;
		
		EmpFundDTO empFund = empFundBD.getEmpFund(bean.getEmpId(), bean.getFundTypeId());
		
		if(empFund != null) {
			bean.setComponentName(empFund.getComponentName());
			bean.setFundAccountNo(empFund.getFundAccountNo());
			bean.setFundLocation(empFund.getFundLocation());
			
			if(empFund.getFundAmount() != null)
				bean.setFundAmount(empFund.getFundAmount().toString());
			
			bean.setEmpName(empFund.getEmpName());
			
			bean.setNomneeName(empFund.getNomneeName());
			bean.setNomneeRelationship(empFund.getNomneeRelationship());
			bean.setNomneeAddress(empFund.getNomneeAddress());
			bean.setNomneeAge(empFund.getNomneeAge());
			bean.setNomneeSharePCT(empFund.getNomneeSharePCT());
			
			FundTypeDTO fundTypeDTO = empFundBD.getFundType(bean.getFundTypeId());
			
			if(empFundBD.isVariableFundAmount(fundTypeDTO))
				bean.setFundAmountRanged(true);
			else
				bean.setFundAmountRanged(false);
			
			request.setAttribute("dataFound", "true");
		}
		else {
			ActionErrors errors = new ActionErrors();
			errors.add("noDataFound", new ActionError("empFund.noDataFound"));
			
			saveErrors(request, errors);
		}
		
		return mapping.findForward("preEditEmpFund");
	}

}