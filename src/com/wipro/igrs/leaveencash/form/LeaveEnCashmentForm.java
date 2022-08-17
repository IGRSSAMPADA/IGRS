package com.wipro.igrs.leaveencash.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.leaveencash.dto.LeaveEnCashmentDTO;

public class LeaveEnCashmentForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String formName;
	private String actionName;
	private LeaveEnCashmentDTO leaveDTO = new LeaveEnCashmentDTO();
	
	
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public LeaveEnCashmentDTO getLeaveDTO() {
		return leaveDTO;
	}
	public void setLeaveDTO(LeaveEnCashmentDTO leaveDTO) {
		this.leaveDTO = leaveDTO;
	}

}
