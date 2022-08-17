package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.ServiceParamMapDTO;

public class ServiceParamMapForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String paramMapPageName;
	private String actionValue;

	private String funcid;
	private String serviceid;
	private String paramId;
	private String paramValue;
	private String paramType;
	private String parentParamId;
	private String operatorId;
	private String funcOperatorId;
	private String priority;

	private ServiceParamMapDTO mapdto;

	public String getParamMapPageName() {
		return paramMapPageName;
	}

	public void setParamMapPageName(String paramMapPageName) {
		this.paramMapPageName = paramMapPageName;
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

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParentParamId() {
		return parentParamId;
	}

	public void setParentParamId(String parentParamId) {
		this.parentParamId = parentParamId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getFuncOperatorId() {
		return funcOperatorId;
	}

	public void setFuncOperatorId(String funcOperatorId) {
		this.funcOperatorId = funcOperatorId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public ServiceParamMapDTO getMapdto() {
		return mapdto;
	}

	public void setMapdto(ServiceParamMapDTO mapdto) {
		this.mapdto = mapdto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
