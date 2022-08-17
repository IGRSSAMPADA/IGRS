package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.SubClauseMapDTO;

/**
 * ===========================================================================
 * File : SubClauseMapForm .java Description : Represents the FormBean for
 * SubCluseMapScreen Author : vengamamba P Created Date : Apr 27, 2008
 * 
 * ===========================================================================
 */
public class SubClauseMapForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String districtID;
	private String tehsilID;
	private String patwariID;
	private String villageID;
	private String propertyID;
	private String propertyL1ID;
	private String propertyL2ID;
	private String subClauseID;
	private String subClauseValue;
	private String operatorID;
	private String funcOperatorID;

	// variables related to hidden fields
	private String formName;
	private String actionValue;

	// intializing obj to dto
	private SubClauseMapDTO clausedto = new SubClauseMapDTO();

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

	public String getFuncOperatorID() {
		return funcOperatorID;
	}

	public void setFuncOperatorID(String funcOperatorID) {
		this.funcOperatorID = funcOperatorID;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getTehsilID() {
		return tehsilID;
	}

	public void setTehsilID(String tehsilID) {
		this.tehsilID = tehsilID;
	}

	public String getPatwariID() {
		return patwariID;
	}

	public void setPatwariID(String patwariID) {
		this.patwariID = patwariID;
	}

	public String getVillageID() {
		return villageID;
	}

	public void setVillageID(String villageID) {
		this.villageID = villageID;
	}

	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	public String getPropertyL1ID() {
		return propertyL1ID;
	}

	public void setPropertyL1ID(String propertyL1ID) {
		this.propertyL1ID = propertyL1ID;
	}

	public String getPropertyL2ID() {
		return propertyL2ID;
	}

	public void setPropertyL2ID(String propertyL2ID) {
		this.propertyL2ID = propertyL2ID;
	}

	public String getSubClauseID() {
		return subClauseID;
	}

	public void setSubClauseID(String subClauseID) {
		this.subClauseID = subClauseID;
	}

	public String getSubClauseValue() {
		return subClauseValue;
	}

	public void setSubClauseValue(String subClauseValue) {
		this.subClauseValue = subClauseValue;
	}

	public SubClauseMapDTO getClausedto() {
		return clausedto;
	}

	public void setClausedto(SubClauseMapDTO clausedto) {
		this.clausedto = clausedto;
	}

}
