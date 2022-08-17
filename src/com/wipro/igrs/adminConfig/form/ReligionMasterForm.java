package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.ReligionMasterDTO;

public class ReligionMasterForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String religionPageName;
	private String actionValue;

	private String religionName;
	private String religionDesc;
	private String religionStatus;

	private ReligionMasterDTO religiondto;

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getReligionPageName() {
		return religionPageName;
	}

	public void setReligionPageName(String religionPageName) {
		this.religionPageName = religionPageName;
	}

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public String getReligionDesc() {
		return religionDesc;
	}

	public void setReligionDesc(String religionDesc) {
		this.religionDesc = religionDesc;
	}

	public String getReligionStatus() {
		return religionStatus;
	}

	public void setReligionStatus(String religionStatus) {
		this.religionStatus = religionStatus;
	}

	public ReligionMasterDTO getReligiondto() {
		return religiondto;
	}

	public void setReligiondto(ReligionMasterDTO religiondto) {
		this.religiondto = religiondto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
