package com.wipro.igrs.titlecertificate.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.titlecertificate.dto.TitleCertificateDTO;

public class TitleCertificateForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TitleCertificateDTO titleDTO = new TitleCertificateDTO();

	private ArrayList proprietorList = new ArrayList(); 
	private String formName;
	private String actionName;
	public ArrayList getProprietorList() {
		return proprietorList;
	}

	public void setProprietorList(ArrayList proprietorList) {
		this.proprietorList = proprietorList;
	}

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

	public TitleCertificateDTO getTitleDTO() {
		return titleDTO;
	}

	public void setTitleDTO(TitleCertificateDTO titleDTO) {
		this.titleDTO = titleDTO;
	}
	
}
