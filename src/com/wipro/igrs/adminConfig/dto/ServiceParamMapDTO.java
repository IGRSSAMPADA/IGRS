/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceParamMapDTO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class ServiceParamMapDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	private ArrayList funcIdList;
	private ArrayList serviceIdList;
	private ArrayList paramIdList;
	private ArrayList operatorIdList;

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

	public ArrayList getFuncIdList() {
		return funcIdList;
	}

	public void setFuncIdList(ArrayList funcIdList) {
		this.funcIdList = funcIdList;
	}

	public ArrayList getServiceIdList() {
		return serviceIdList;
	}

	public void setServiceIdList(ArrayList serviceIdList) {
		this.serviceIdList = serviceIdList;
	}

	public ArrayList getParamIdList() {
		return paramIdList;
	}

	public void setParamIdList(ArrayList paramIdList) {
		this.paramIdList = paramIdList;
	}

	public ArrayList getOperatorIdList() {
		return operatorIdList;
	}

	public void setOperatorIdList(ArrayList operatorIdList) {
		this.operatorIdList = operatorIdList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
