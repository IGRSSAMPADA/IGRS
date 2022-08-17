package com.wipro.igrs.generatecertificate.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.generatecertificate.dto.GenerateCertificateDTO;

public class GenerateCertificateForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GenerateCertificateDTO generateDTO = new GenerateCertificateDTO();
    private ArrayList empList = new ArrayList();
    private ArrayList showEmpList = new ArrayList();
	 
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

	public GenerateCertificateDTO getGenerateDTO() {
		return generateDTO;
	}

	public void setGenerateDTO(GenerateCertificateDTO generateDTO) {
		this.generateDTO = generateDTO;
	}

	public ArrayList getEmpList() {
		return empList;
	}

	public void setEmpList(ArrayList empList) {
		this.empList = empList;
	}

	public ArrayList getShowEmpList() {
		return showEmpList;
	}

	public void setShowEmpList(ArrayList showEmpList) {
		this.showEmpList = showEmpList;
	}
	
}
