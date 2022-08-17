package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

/**
 * ===========================================================================
 * File : DeedMasterDTO.java Description : Represents the DTO Class Author :
 * vengamamba P Created Date : Apr 21, 2008
 * ===========================================================================
 */
public class DeedParamMapDTO {
	private String name;
	private String value;
	private ArrayList deedIDList;
	private ArrayList instrumentIDList;
	private ArrayList exemptionIDList;
	private ArrayList paramIDList;
	private ArrayList operatorIDList;

	public ArrayList getParamIDList() {
		return paramIDList;
	}

	public void setParamIDList(ArrayList paramIDList) {
		this.paramIDList = paramIDList;
	}

	public ArrayList getOperatorIDList() {
		return operatorIDList;
	}

	public void setOperatorIDList(ArrayList operatorIDList) {
		this.operatorIDList = operatorIDList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList getDeedIDList() {
		return deedIDList;
	}

	public void setDeedIDList(ArrayList deedIDList) {
		this.deedIDList = deedIDList;
	}

	public ArrayList getInstrumentIDList() {
		return instrumentIDList;
	}

	public void setInstrumentIDList(ArrayList instrumentIDList) {
		this.instrumentIDList = instrumentIDList;
	}

	public ArrayList getExemptionIDList() {
		return exemptionIDList;
	}

	public void setExemptionIDList(ArrayList exemptionIDList) {
		this.exemptionIDList = exemptionIDList;
	}
}
