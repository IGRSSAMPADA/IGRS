package com.wipro.igrs.cybertehsil.forms.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class DeclarationDTO implements Serializable{
	private String declarationTxnId;
	private String declarationId;
	private String declarationSubId;
	private String declarationNameEnglish;
	private String declarationNameHindi;
	private String userEntrableFlag;
	private String userEntrableFieldId;
	private String form_txn_id;
	
	private ArrayList<FormUserEnterableDTO> userEnterableData;

	public String getDeclarationTxnId() {
		return declarationTxnId;
	}

	public void setDeclarationTxnId(String declarationTxnId) {
		this.declarationTxnId = declarationTxnId;
	}

	public String getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}

	public String getDeclarationSubId() {
		return declarationSubId;
	}

	public void setDeclarationSubId(String declarationSubId) {
		this.declarationSubId = declarationSubId;
	}

	public String getDeclarationNameEnglish() {
		return declarationNameEnglish;
	}

	public void setDeclarationNameEnglish(String declarationNameEnglish) {
		this.declarationNameEnglish = declarationNameEnglish;
	}

	public String getDeclarationNameHindi() {
		return declarationNameHindi;
	}

	public void setDeclarationNameHindi(String declarationNameHindi) {
		this.declarationNameHindi = declarationNameHindi;
	}

	public String getUserEntrableFlag() {
		return userEntrableFlag;
	}

	public void setUserEntrableFlag(String userEntrableFlag) {
		this.userEntrableFlag = userEntrableFlag;
	}

	public ArrayList<FormUserEnterableDTO> getUserEnterableData() {
		return userEnterableData;
	}

	public void setUserEnterableData(ArrayList<FormUserEnterableDTO> userEnterableData) {
		this.userEnterableData = userEnterableData;
	}

	/**
	 * @return the userEntrableFieldId
	 */
	public String getUserEntrableFieldId() {
		return userEntrableFieldId;
	}

	/**
	 * @param userEntrableFieldId the userEntrableFieldId to set
	 */
	public void setUserEntrableFieldId(String userEntrableFieldId) {
		this.userEntrableFieldId = userEntrableFieldId;
	}

	/**
	 * @return the form_txn_id
	 */
	public String getForm_txn_id() {
		return form_txn_id;
	}

	/**
	 * @param form_txn_id the form_txn_id to set
	 */
	public void setForm_txn_id(String form_txn_id) {
		this.form_txn_id = form_txn_id;
	}
	
}
