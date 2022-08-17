package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

/**
 * ===========================================================================
 * File : SubClauseMapDTO.java Description : Represents the DTO Class Author :
 * vengamamba P Created Date : Apr 28, 2008
 * ===========================================================================
 */
public class SubClauseMapDTO {
	private String name;
	private String value;
	private ArrayList districtIDList;
	private ArrayList tehsilIDList;
	private ArrayList patwariIDList;
	private ArrayList villageIDList;
	private ArrayList propertyIDList;
	private ArrayList propertyL1List;
	private ArrayList propertyL2List;
	private ArrayList subClauseIDList;
	private ArrayList operatorIDList;

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

	public ArrayList getDistrictIDList() {
		return districtIDList;
	}

	public void setDistrictIDList(ArrayList districtIDList) {
		this.districtIDList = districtIDList;
	}

	public ArrayList getTehsilIDList() {
		return tehsilIDList;
	}

	public void setTehsilIDList(ArrayList tehsilIDList) {
		this.tehsilIDList = tehsilIDList;
	}

	public ArrayList getPatwariIDList() {
		return patwariIDList;
	}

	public void setPatwariIDList(ArrayList patwariIDList) {
		this.patwariIDList = patwariIDList;
	}

	public ArrayList getVillageIDList() {
		return villageIDList;
	}

	public void setVillageIDList(ArrayList villageIDList) {
		this.villageIDList = villageIDList;
	}

	public ArrayList getPropertyIDList() {
		return propertyIDList;
	}

	public void setPropertyIDList(ArrayList propertyIDList) {
		this.propertyIDList = propertyIDList;
	}

	public ArrayList getPropertyL1List() {
		return propertyL1List;
	}

	public void setPropertyL1List(ArrayList propertyL1List) {
		this.propertyL1List = propertyL1List;
	}

	public ArrayList getPropertyL2List() {
		return propertyL2List;
	}

	public void setPropertyL2List(ArrayList propertyL2List) {
		this.propertyL2List = propertyL2List;
	}

	public ArrayList getSubClauseIDList() {
		return subClauseIDList;
	}

	public void setSubClauseIDList(ArrayList subClauseIDList) {
		this.subClauseIDList = subClauseIDList;
	}

}
