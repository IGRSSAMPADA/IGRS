package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.CasteMasterDTO;

public class CasteMasterForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String castePageName;
	private String actionValue;

	private String casteName;
	private String castCategoryId;
	private String casteStatus;

	private CasteMasterDTO castedto;

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getCastePageName() {
		return castePageName;
	}

	public void setCastePageName(String castePageName) {
		this.castePageName = castePageName;
	}

	public String getCasteName() {
		return casteName;
	}

	public void setCasteName(String casteName) {
		this.casteName = casteName;
	}

	public String getCastCategoryId() {
		return castCategoryId;
	}

	public void setCastCategoryId(String castCategoryId) {
		this.castCategoryId = castCategoryId;
	}

	public String getCasteStatus() {
		return casteStatus;
	}

	public void setCasteStatus(String casteStatus) {
		this.casteStatus = casteStatus;
	}

	public CasteMasterDTO getCastedto() {
		return castedto;
	}

	public void setCastedto(CasteMasterDTO castedto) {
		this.castedto = castedto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
