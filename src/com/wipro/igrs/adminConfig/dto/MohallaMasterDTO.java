/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	MohallaMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	17/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class MohallaMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String mohallaName;
	private String mohallaDesc;
	private String mohallaStatus;
	private String mohallaId;
	private String mohallaType;

	private ArrayList patwariIdList;
	private ArrayList mohallaList;

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

	public ArrayList getPatwariIdList() {
		return patwariIdList;
	}

	public void setPatwariIdList(ArrayList patwariIdList) {
		this.patwariIdList = patwariIdList;
	}

	public ArrayList getMohallaList() {
		return mohallaList;
	}

	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	public String getMohallaName() {
		return mohallaName;
	}

	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}

	public String getMohallaDesc() {
		return mohallaDesc;
	}

	public void setMohallaDesc(String mohallaDesc) {
		this.mohallaDesc = mohallaDesc;
	}

	public String getMohallaStatus() {
		return mohallaStatus;
	}

	public void setMohallaStatus(String mohallaStatus) {
		this.mohallaStatus = mohallaStatus;
	}

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}

	public String getMohallaType() {
		return mohallaType;
	}

	public void setMohallaType(String mohallaType) {
		this.mohallaType = mohallaType;
	}

}
