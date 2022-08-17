package com.wipro.igrs.login.form;

import com.wipro.igrs.baseaction.form.BaseForm;

public class UserBlockedDisplayForm extends BaseForm{
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private String actionName;
	
	

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
