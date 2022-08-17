package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.DocumentMasterDTO;

public class DocumentMasterForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String documentPageName;
	private String actionValue;

	private String documentName;
	private String documentDesc;
	private String documentStatus;

	private DocumentMasterDTO documentdto;

	public String getDocumentPageName() {
		return documentPageName;
	}

	public void setDocumentPageName(String documentPageName) {
		this.documentPageName = documentPageName;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public DocumentMasterDTO getDocumentdto() {
		return documentdto;
	}

	public void setPartydto(DocumentMasterDTO documentdto) {
		this.documentdto = documentdto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
