/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ServiceMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	21/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class ServiceMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String serviceName;
	private String serviceDesc;
	private String serviceStatus;
	private String serviceId;

	private ArrayList serviceList;

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

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public ArrayList getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
