package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.DeedParamMapDTO;

/**
 * ===========================================================================
 * File : DeedMasterForm .java Description : Represents the FormBean for
 * deedMasterScreen Author : vengamamba P Created Date : Apr 21, 2008
 * 
 * ===========================================================================
 */
public class DeedParamMapForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String deedID;
	private String instrumentID;
	private String exemptionID;
	private String paramID;
	private String paramValue;
	private String priority;
	private String parentID;
	private String operatorID;
	private String funcOperatorID;

	// variables related to hidden fields
	private String formName;
	private String actionValue;

	// intializing obj to dto
	private DeedParamMapDTO deeddto = new DeedParamMapDTO();

	public String getDeedID() {
		return deedID;
	}

	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}

	public String getInstrumentID() {
		return instrumentID;
	}

	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}

	public String getExemptionID() {
		return exemptionID;
	}

	public void setExemptionID(String exemptionID) {
		this.exemptionID = exemptionID;
	}

	public String getParamID() {
		return paramID;
	}

	public void setParamID(String paramID) {
		this.paramID = paramID;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getOperatorID() {
		return operatorID;
	}

	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public DeedParamMapDTO getDeeddto() {
		return deeddto;
	}

	public void setDeeddto(DeedParamMapDTO deeddto) {
		this.deeddto = deeddto;
	}

	public String getFuncOperatorID() {
		return funcOperatorID;
	}

	public void setFuncOperatorID(String funcOperatorID) {
		this.funcOperatorID = funcOperatorID;
	}

}
