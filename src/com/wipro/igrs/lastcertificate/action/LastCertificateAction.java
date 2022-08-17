package com.wipro.igrs.lastcertificate.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.lastcertificate.bo.LastCertificateBO;
import com.wipro.igrs.lastcertificate.constant.CommonConstant;
import com.wipro.igrs.lastcertificate.dto.LastCertificateDTO;
import com.wipro.igrs.lastcertificate.form.LastCertificateForm;
import com.wipro.igrs.lastcertificate.rule.LastCertificateRule;
import com.wipro.igrs.lastcertificate.util.PropertiesFileReader;


public class LastCertificateAction extends BaseAction {

	private Logger logger = 
		(Logger) Logger.getLogger(LastCertificateAction.class);
	String formName ="";
	String actionName="";
	PropertiesFileReader pr;
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) 
		throws Exception { 
		
		String forwardJsp = new String("");
		logger.debug("Popup window start");
		
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		
		LastCertificateBO bo = new LastCertificateBO();
		String urlParam = request.getParameter("param");
		if("lastPay".equals(urlParam)) {
			forwardJsp = CommonConstant.LAST_PAY_PAGE;
		}
		if(form !=null) {
			LastCertificateForm eForm = (LastCertificateForm) form;
			formName = eForm.getFormName();
			actionName = eForm.getActionName();
			
			if(CommonConstant.LAST_PAY_PAGE.equals(formName)) {
				if(CommonConstant.SHOW_EMP_ACTION.equals(actionName)) {
					LastCertificateDTO dto = eForm.getLastDTO();
					String empId = dto.getEmpID();
					logger.debug("Employee ID:-"+empId);
					LastCertificateRule rule = new LastCertificateRule();
					ArrayList errorList = rule.validateLastPay(empId, pr);
					if(rule.isError()) {
						request.setAttribute("isError","true");
						request.setAttribute("errorList",errorList);
						forwardJsp = CommonConstant.LAST_PAY_POPUP_PAGE;
						 
					}else {
						LastCertificateDTO dtoReturn = bo.getEmpSalary(empId);						
						eForm.setLastDTO(dtoReturn);
						eForm.setListSalaryHead(dtoReturn.getSalaryHeadList());
						eForm.setListRecovery(dtoReturn.getRecoveryDetails());
						eForm.setListDeduction(dtoReturn.getMonthlyDeduction());
						request.setAttribute("isError","false");
						forwardJsp = CommonConstant.LAST_PAY_POPUP_PAGE;
					}	
				}
			}
		}
		
		return mapping.findForward(forwardJsp);
	}
	
}
