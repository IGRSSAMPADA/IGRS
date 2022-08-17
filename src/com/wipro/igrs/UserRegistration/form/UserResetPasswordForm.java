package com.wipro.igrs.UserRegistration.form;

import com.wipro.igrs.baseaction.form.BaseForm;

public class UserResetPasswordForm extends BaseForm {
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String newPaswrd;
	private String confmPaswrd;
	private String actionName;
	private String formName;
	private String photoIdProof;
	
	private String key;
	
	private int topMenuFlag=1;
	
	
	public int getTopMenuFlag() {
		return topMenuFlag;
	}
	public void setTopMenuFlag(int topMenuFlag) {
		this.topMenuFlag = topMenuFlag;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getPhotoIdProof() {
		return photoIdProof;
	}
	public void setPhotoIdProof(String photoIdProof) {
		this.photoIdProof = photoIdProof;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getNewPaswrd() {
		return newPaswrd;
	}
	public void setNewPaswrd(String newPaswrd) {
		this.newPaswrd = newPaswrd;
	}
	public String getConfmPaswrd() {
		return confmPaswrd;
	}
	public void setConfmPaswrd(String confmPaswrd) {
		this.confmPaswrd = confmPaswrd;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	

}
