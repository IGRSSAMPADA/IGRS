/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceFuncMapDTO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */
package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class ServiceFuncMapDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;

	private ArrayList funcIdList;
	private ArrayList serviceIdList;

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}