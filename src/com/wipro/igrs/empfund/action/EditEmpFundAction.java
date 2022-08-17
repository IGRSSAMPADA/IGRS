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
import com.wipro.igrs.empfund.dto.FundRangeDTO;
import com.wipro.igrs.empfund.dto.FundTypeDTO;
import com.wipro.igrs.empfund.form.EmpFundForm;

public class EditEmpFundAction extends BaseAction {
    
	private IEmpFundBD empFundBD = new EmpFundBD();

    
    public EditEmpFundAction() {
    }


	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		
		EmpFundForm bean = (EmpFundForm)form;
		
		double fundAmount = Double.parseDouble(bean.getFundAmount());
		
		FundTypeDTO fundTypeDTO = empFundBD.getFundType(bean.getFundTypeId());
		
		if(!empFundBD.isValidFundAmount(fundTypeDTO, fundAmount, bean.getEmpId())) {
			FundRangeDTO fundTypeRange = empFundBD.getFundTypeRange(fundTypeDTO, bean.getEmpId());
			
			System.out.println(fundTypeRange);
			
			ActionErrors errors = new ActionErrors();
			
			if(fundTypeRange.getMinimum().equals(fundTypeRange.getMaximum())) {
				errors.add("fundAmountError", new ActionError("empFund.invalidFundAmountExact",
						fundTypeRange.getMinimum(), fundTypeRange.getMaximum()));
			}
			else
				errors.add("fundAmountError", new ActionError("empFund.invalidFundAmountRange",
						fundTypeRange.getMinimum(), fundTypeRange.getMaximum()));
			
			saveErrors(request, errors);
		}
		else {
			EmpFundDTO fundDTO = new EmpFundDTO();
			
			fundDTO.setEmpId(bean.getEmpId());
			fundDTO.setFundTypeId(bean.getFundTypeId());
			fundDTO.setFundAccountNo(bean.getFundAccountNo());
			fundDTO.setComponentName(bean.getComponentName());
			fundDTO.setFundAmount(new Double(fundAmount) );
			
			empFundBD.editEmpFund(fundDTO);
		}
		
		if(empFundBD.isVariableFundAmount(fundTypeDTO))
			bean.setFundAmountRanged(true);
		
		request.setAttribute("dataFound", "true");
		
		return mapping.findForward("preEditEmpFund");
	}

}