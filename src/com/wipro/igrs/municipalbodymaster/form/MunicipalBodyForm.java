package com.wipro.igrs.municipalbodymaster.form;

import com.wipro.igrs.baseaction.form.BaseForm;

public class MunicipalBodyForm extends BaseForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8082315304451521676L;
	
	private String municipalBodyId;
	private String municipalBodyName;
	private String municipalBodyDesc;
	private String municipalBodyStatus;
	
	public MunicipalBodyForm() {
		
	}

	public String getMunicipalBodyId() {
		return municipalBodyId;
	}
	public void setMunicipalBodyId(String municipalBodyId) {
		this.municipalBodyId = municipalBodyId;
	}
	public String getMunicipalBodyName() {
		return municipalBodyName;
	}
	public void setMunicipalBodyName(String municipalBodyName) {
		this.municipalBodyName = municipalBodyName;
	}
	public String getMunicipalBodyDesc() {
		return municipalBodyDesc;
	}
	public void setMunicipalBodyDesc(String municipalBodyDesc) {
		this.municipalBodyDesc = municipalBodyDesc;
	}
	public String getMunicipalBodyStatus() {
		return municipalBodyStatus;
	}
	public void setMunicipalBodyStatus(String municipalBodyStatus) {
		this.municipalBodyStatus = municipalBodyStatus;
	}
}
