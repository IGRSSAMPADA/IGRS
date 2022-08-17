package com.wipro.igrs.empsalcomp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.empsalcomp.bd.EmpSalaryBD;
import com.wipro.igrs.empsalcomp.dto.EmpSalaryDTO;
import com.wipro.igrs.empsalcomp.form.EmpSalaryActionForm;
import com.wipro.igrs.empsalcomp.rule.BasicVerificationRule;

public class SaveOrUpdateEmpAction extends BaseAction {

	private Logger logger = (Logger) Logger
			.getLogger(SaveOrUpdateEmpAction.class);

	private String forwardPage = "";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String roleId = (String) session.getAttribute("role");
		String funId = (String) session.getAttribute("functionId");
		String userId = (String) session.getAttribute("UserId");

		EmpSalaryActionForm empForm = (EmpSalaryActionForm) form;
		EmpSalaryBD empBD = new EmpSalaryBD();
		EmpSalaryDTO empDTO = new EmpSalaryDTO();
		empDTO.setEmpID(empForm.getEmpID());
		empDTO.setBasicValue(empForm.getBasicValue());
		empDTO.setEffectiveDate(empForm.getEffectiveDate());
		empDTO.setEffectiveFlag(empForm.getEffectiveFlag());
		empDTO.setSlabId(empForm.getSlabId());
		empDTO.setIncrements(empForm.getIncrements());
		empDTO.setIncrementSal(empForm.getIncrementSal());

		EmpSalaryDTO dto = empBD.getEmpData(empForm.getEmpID());
		// empForm.setBasicValue(dto.getCurrentBasic());
		dto.setBasicValue(empForm.getBasicValue());
		logger.debug("from the action" + empForm.getBasicValue());
		BasicVerificationRule rule = new BasicVerificationRule();

		// ArrayList errorList = rule.getVerifiedBasic(dto);
		// rule.setError(false);
		// if(rule.isError()) {
		// Write the session for error
		// request.setAttribute("errorFlag","true");
		// request.setAttribute("errorList",errorList);

		// forwardPage = "empBasicPage";
		// }else {

		empBD.SaveOrUpdateEmpSalaryBase(empDTO, userId);
		empForm.setEmpID("");
		empForm.setSearched(null);
		forwardPage = "successpage";
		// }
		logger.debug("forwardPage:-" + forwardPage);
		return mapping.findForward(forwardPage);
	}

}
