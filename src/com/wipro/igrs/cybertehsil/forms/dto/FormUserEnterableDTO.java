package com.wipro.igrs.cybertehsil.forms.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class FormUserEnterableDTO implements Serializable{
	private String form_txn_id;
	private String userEnterableTxnId;
	private String userEnterableId;
	private String userEnterableFieldNameEnglish;
	private String userEnterableFieldNameHindi;
	private String valueEntered;
	public String getUserEnterableId() {
		return userEnterableId;
	}
	public void setUserEnterableId(String userEnterableId) {
		this.userEnterableId = userEnterableId;
	}
	public String getUserEnterableFieldNameEnglish() {
		return userEnterableFieldNameEnglish;
	}
	public void setUserEnterableFieldNameEnglish(String userEnterableFieldNameEnglish) {
		this.userEnterableFieldNameEnglish = userEnterableFieldNameEnglish;
	}
	public String getUserEnterableFieldNameHindi() {
		return userEnterableFieldNameHindi;
	}
	public void setUserEnterableFieldNameHindi(String userEnterableFieldNameHindi) {
		this.userEnterableFieldNameHindi = userEnterableFieldNameHindi;
	}
	public String getValueEntered() {
		return valueEntered;
	}
	public void setValueEntered(String valueEntered) {
		this.valueEntered = valueEntered;
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
	/**
	 * @return the userEnterableTxnId
	 */
	public String getUserEnterableTxnId() {
		return userEnterableTxnId;
	}
	/**
	 * @param userEnterableTxnId the userEnterableTxnId to set
	 */
	public void setUserEnterableTxnId(String userEnterableTxnId) {
		this.userEnterableTxnId = userEnterableTxnId;
	}
	
}
