package com.wipro.igrs.generatecertificate.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.generatecertificate.bo.GenerateCertificateBO;
import com.wipro.igrs.generatecertificate.constant.CommonConstant;
import com.wipro.igrs.generatecertificate.dto.GenerateCertificateDTO;
import com.wipro.igrs.generatecertificate.form.GenerateCertificateForm;
import com.wipro.igrs.generatecertificate.rule.GenerateCertificateRule;
import com.wipro.igrs.generatecertificate.util.PropertiesFileReader;


public class GenerateCertificateAction extends BaseAction {

	private Logger logger = 
		(Logger) Logger.getLogger(GenerateCertificateAction.class);
	String formName ="";
	String actionName="";
	HashMap map = null;
	private PropertiesFileReader pr ;
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) 
		throws Exception { 
		
		String forwardJsp = new String("popupGenerate");
		logger.debug("Popup window start");
		 
		GenerateCertificateBO bo = new GenerateCertificateBO();
	 
		String param = (String) request.getParameter("param");
		
		if(CommonConstant.PARAM_PROBATION.equals(param)) {
			forwardJsp = CommonConstant.PROBATION_PAGE;
		}
		pr = PropertiesFileReader.getInstance("resources.igrs");
		
		if(form!=null) {
			
			GenerateCertificateForm eForm = (GenerateCertificateForm)form;
			eForm.setEmpList(bo.getEmployeeDetails());
			
			String formName = eForm.getFormName();
			String actionName = eForm.getActionName();
			
			if(CommonConstant.PROBATION_FORM.equals(formName)) {
				if(CommonConstant.PROBATION_ACTION.equals(actionName)) {
					String empID = eForm.getGenerateDTO().getEmpID();
					
					GenerateCertificateRule rule = 
						     new GenerateCertificateRule();
					ArrayList errorList = 
						rule.validateCertificate(empID, pr);
					
					
					logger.debug("isError:-"+rule.isError());
					if(rule.isError()) {
						request.setAttribute("isError","true");
						request.setAttribute("errorList",errorList);
					}
					else {
						GenerateCertificateDTO dto = bo.getProbationCertificate(empID);
						request.setAttribute(
								CommonConstant.PROBATION_HEADER, dto.getHeader());
						request.setAttribute(
								CommonConstant.PROBATION_CONTENT, dto.getContent());
						request.setAttribute(
								CommonConstant.PROBATION_FOOTER, dto.getFooter());
						eForm.setGenerateDTO(dto);
						request.setAttribute("isError","false");
					}	
					forwardJsp = CommonConstant.PROBATION_POPUP_PAGE;
			}
			if(CommonConstant.CONFIRMATION_ACTION.equals(actionName)) {
					String empNames = eForm.getGenerateDTO().getConfirmHdnEmp();
					
					
					ArrayList list = bo.getEmployeeName(empNames);
					
					 
					if(list!=null) {
						
						GenerateCertificateRule rule = 
							     new GenerateCertificateRule();
						ArrayList errorList = 
							rule.validateCertificateName(empNames, pr);
						
						
						
						if(rule.isError()) {
							request.setAttribute("isError","true");
							request.setAttribute("errorList",errorList);
						}
						else {
							GenerateCertificateDTO dto = 
									(GenerateCertificateDTO)list.get(0);	
							request.setAttribute(
								CommonConstant.PROBATION_HEADER, dto.getHeader());
							request.setAttribute(
								CommonConstant.PROBATION_CONTENT, dto.getContent());
							request.setAttribute(
								CommonConstant.PROBATION_FOOTER, dto.getFooter());
						
							eForm.setShowEmpList((ArrayList)list.get(1));
						}	
					
					logger.debug("Selected Employee Name:-"+empNames);
					forwardJsp = CommonConstant.CONFIRMATION_PAGE;
				}
			}
			
			
		}
			if(CommonConstant.VIEW_ACTION.equals(param)) {
				//String empNames = eForm.getGenerateDTO().getConfirmHdnEmp();
				String userID = (String)  session.getAttribute("UserId");
				String empNames="";
				ArrayList empList=eForm.getEmpList();
				GenerateCertificateDTO tempdto=null;
				for(int i=0;i<empList.size();i++){
					tempdto=(GenerateCertificateDTO)empList.get(i);
					if(userID.equalsIgnoreCase((tempdto.getConfirmEmpID().split("~"))[0])){
						empNames=tempdto.getConfirmEmpID();
					}
				}
				
				
				ArrayList list = bo.getEmployeeName(empNames);
				
				 
				if(list!=null) {
					
					GenerateCertificateRule rule = 
						     new GenerateCertificateRule();
					ArrayList errorList = 
						rule.validateCertificateName(empNames, pr);
					
					
					
					if(rule.isError()) {
						request.setAttribute("isError","true");
						request.setAttribute("errorList",errorList);
					}
					else {
						GenerateCertificateDTO dto = 
								(GenerateCertificateDTO)list.get(0);	
						request.setAttribute(
							CommonConstant.PROBATION_HEADER, dto.getHeader());
						request.setAttribute(
							CommonConstant.PROBATION_CONTENT, dto.getContent());
						request.setAttribute(
							CommonConstant.PROBATION_FOOTER, dto.getFooter());
					
						eForm.setShowEmpList((ArrayList)list.get(1));
					}	
				
				logger.debug("Selected Employee Name:-"+empNames);
				forwardJsp = CommonConstant.CONFIRMATION_VIEW_PAGE;
			}
		}
	}
     return mapping.findForward(forwardJsp);
   }	
}		
 
