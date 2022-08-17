package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.ServiceMasterDTO;

public class ServiceMasterForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String servicePageName;
	private String actionValue;

	private String serviceName;
	private String serviceDesc;
	private String serviceStatus;

	private ServiceMasterDTO servicedto;

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getServicePageName() {
		return servicePageName;
	}

	public void setServicePageName(String servicePageName) {
		this.servicePageName = servicePageName;
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

	public ServiceMasterDTO getServicedto() {
		return servicedto;
	}

	public void setServicedto(ServiceMasterDTO servicedto) {
		this.servicedto = servicedto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
