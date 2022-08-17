package com.wipro.igrs.etaal.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "serviceCode", "serviceCount" })
public class ResponseDTO {

	private String	serviceCode;
	private String	serviceCount;

	@XmlAttribute(name = "ServiceCode")
	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	@XmlAttribute(name = "ServiceCount")
	public String getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(String serviceCount) {
		this.serviceCount = serviceCount;
	}

}
