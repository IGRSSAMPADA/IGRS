package com.wipro.igrs.lastcertificate.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.lastcertificate.dto.LastCertificateDTO;


public class LastCertificateForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private LastCertificateDTO lastDTO = new LastCertificateDTO();
    private ArrayList listSalaryHead = new ArrayList();
    private ArrayList listRecovery = new ArrayList();
    private ArrayList listDeduction = new ArrayList();
    
	private String formName;
	private String actionName;

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
	public LastCertificateDTO getLastDTO() {
		return lastDTO;
	}
	public void setLastDTO(LastCertificateDTO lastDTO) {
		this.lastDTO = lastDTO;
	}
	public ArrayList getListSalaryHead() {
		return listSalaryHead;
	}
	public void setListSalaryHead(ArrayList listSalaryHead) {
		this.listSalaryHead = listSalaryHead;
	}
	public ArrayList getListRecovery() {
		return listRecovery;
	}
	public void setListRecovery(ArrayList listRecovery) {
		this.listRecovery = listRecovery;
	}
	public ArrayList getListDeduction() {
		return listDeduction;
	}
	public void setListDeduction(ArrayList listDeduction) {
		this.listDeduction = listDeduction;
	}
}
