package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.PhotoIdMasterDTO;

public class PhotoIdMasterForm extends  ActionForm{
	private static final long serialVersionUID = 1L;
	
	private String photoIdPageName;
	private String actionValue;

	private String photoIdName;
	
	private String photoIdStatus;

	private PhotoIdMasterDTO photoIddto;
	
	
	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	
	
	
	

	public String getPhotoIdPageName() {
		return photoIdPageName;
	}

	public void setPhotoIdPageName(String photoIdPageName) {
		this.photoIdPageName = photoIdPageName;
	}

	public String getPhotoIdName() {
		return photoIdName;
	}

	public void setPhotoIdName(String photoIdName) {
		this.photoIdName = photoIdName;
	}

	public String getPhotoIdStatus() {
		return photoIdStatus;
	}

	public void setPhotoIdStatus(String photoIdStatus) {
		this.photoIdStatus = photoIdStatus;
	}

	public PhotoIdMasterDTO getPhotoIddto() {
		return photoIddto;
	}

	public void setPhotoIddto(PhotoIdMasterDTO photoIddto) {
		this.photoIddto = photoIddto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
