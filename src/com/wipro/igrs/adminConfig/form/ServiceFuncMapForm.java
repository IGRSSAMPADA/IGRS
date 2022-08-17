package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.ServiceFuncMapDTO;

public class ServiceFuncMapForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String funcMapPageName;
	private String actionValue;

	private String funcid;
	private String serviceid;

	private ServiceFuncMapDTO mapdto;

	public String getFuncMapPageName() {
		return funcMapPageName;
	}

	public void setFuncMapPageName(String funcMapPageName) {
		this.funcMapPageName = funcMapPageName;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getFuncid() {
		return funcid;
	}

	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public ServiceFuncMapDTO getMapdto() {
		return mapdto;
	}

	public void setMapdto(ServiceFuncMapDTO mapdto) {
		this.mapdto = mapdto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}