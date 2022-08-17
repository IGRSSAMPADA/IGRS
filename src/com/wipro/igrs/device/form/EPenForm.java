package com.wipro.igrs.device.form;

import org.apache.struts.action.ActionForm;

public class EPenForm extends ActionForm {

	private static final long serialVersionUID = 1258361856602660476L;
	private String name = null;

	private String uploadGUID;
	private String fileNameSign;
	private String parentPathSign;
	private String forwardPath;
	private String forwardName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUploadGUID() {
		return uploadGUID;
	}

	public void setUploadGUID(String uploadGUID) {
		this.uploadGUID = uploadGUID;
	}

	public String getFileNameSign() {
		return fileNameSign;
	}

	public void setFileNameSign(String fileNameSign) {
		this.fileNameSign = fileNameSign;
	}

	public String getParentPathSign() {
		return parentPathSign;
	}

	public void setParentPathSign(String parentPathSign) {
		this.parentPathSign = parentPathSign;
	}

	public String getForwardPath() {
		return forwardPath;
	}

	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}

	public String getForwardName() {
		return forwardName;
	}

	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}

}
