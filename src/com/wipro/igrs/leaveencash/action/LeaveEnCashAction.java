package com.wipro.igrs.leaveencash.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.leaveencash.bo.LeaveEnCashmentBO;
import com.wipro.igrs.leaveencash.constant.CommonConstant;
import com.wipro.igrs.leaveencash.dto.LeaveEnCashmentDTO;
import com.wipro.igrs.leaveencash.form.LeaveEnCashmentForm;
import com.wipro.igrs.leaveencash.rule.LeaveEnCashRule;
import com.wipro.igrs.util.PropertiesFileReader;


public class LeaveEnCashAction extends BaseAction {
	
	
	private Logger logger = (Logger) Logger
	.getLogger(LeaveEnCashAction.class);
	
	String formName ="";
	String actionName="";
	
	private PropertiesFileReader pr;
	
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession session) 
		throws Exception { 
		
		String forwardJsp = new String(CommonConstant.LEAVE_ENCASH_PAGE);
		pr = PropertiesFileReader.getInstance("com.wipro.igrs.igrs");
		
		if(form!=null) {
			LeaveEnCashmentForm eForm = (LeaveEnCashmentForm)form;
			LeaveEnCashmentBO bo = new LeaveEnCashmentBO();
			LeaveEnCashmentDTO dtoForm = eForm.getLeaveDTO();
			formName = eForm.getFormName();
			actionName = eForm.getActionName();
			
			String roleName = (String)session.getAttribute("roleName");
			String empID =  (String)session.getAttribute("UserId");
			
			logger.debug("empID:-"+empID+":"+roleName);
			if(!CommonConstant.ROLE_OIC.equals(roleName)) {
				
				LeaveEnCashmentDTO dto = bo.getEmpDetails(empID);
				session.setAttribute("LeaveDTO", dto);
			    eForm.setLeaveDTO(dto);
			    forwardJsp = CommonConstant.LEAVE_ENCASH_PAGE;
			}
			
			if(CommonConstant.FORM_NAME.equals(formName)) {
				if(CommonConstant.ROLE_OIC.equals(roleName)) {
					if(CommonConstant.EMP_SHOW_ACTION.equals(actionName)) {
				 
						LeaveEnCashmentDTO dto = bo.getEmpDetails(dtoForm.getEmpID());
						session.setAttribute("LeaveDTO", dto);
						dto.setEmpID(dto.getEmpID());
						dto.setMaxFiscalYearID(dto.getMaxFiscalYearID());
						
						eForm.setLeaveDTO(dto);
						forwardJsp = CommonConstant.LEAVE_ENCASH_PAGE;
					}
				}
				if(CommonConstant.EMP_SAVE_ACTION.equals(actionName)) {
					LeaveEnCashRule rule = new LeaveEnCashRule();
					
					//LeaveEnCashmentDTO dto = eForm.getLeaveDTO();
					LeaveEnCashmentDTO dtoRequest = (LeaveEnCashmentDTO)
										session.getAttribute("LeaveDTO");
					logger.debug("dtoRequest.getEmpID():="
							+dtoRequest.getEmpID()
							+":"+dtoForm.getLeaveEnCash()
							+":"+dtoForm.getRemarks());
					
					dtoForm.setEmpID(dtoRequest.getEmpID());
					dtoForm.setMaxFiscalYearID(dtoRequest.getMaxFiscalYearID());
					
					dtoRequest.setLeaveEnCash(dtoForm.getLeaveEnCash());
					dtoRequest.setRemarks(dtoForm.getRemarks());
					
					ArrayList errorList = rule.validateLeaveEnCash(dtoRequest,pr);
					
					if(rule.isError()) {
						request.setAttribute("isError","true");
						request.setAttribute("errorList",errorList);
						eForm.setLeaveDTO(dtoRequest);
						forwardJsp = CommonConstant.LEAVE_ENCASH_PAGE;
					}else {
						 
						dtoForm.setCreatedBy(empID);
						
						boolean bol = bo.insertEnCashLeave(dtoForm);
						if(bol) {
							dtoForm.setMsg(pr.getValue("error.leave.success"));
						}else {
							dtoForm.setMsg(pr.getValue("error.leave.faliure"));
						}
						forwardJsp = CommonConstant.LEAVE_ENCASH_SUCCESS_PAGE;
					}
				}
				if(CommonConstant.EMP_RESET_ACTION.equals(actionName)) {
					if(CommonConstant.ROLE_OIC.equals(roleName)) {
						eForm.setLeaveDTO(new LeaveEnCashmentDTO());
					}else {
						dtoForm.setLeaveEnCash(0);
						dtoForm.setRemarks("");
						eForm.setLeaveDTO(dtoForm);
					}
					forwardJsp = CommonConstant.LEAVE_ENCASH_PAGE;
				}
			}
			 
			
		}
		return mapping.findForward(forwardJsp);
	}
		
	
	
	
	
}
