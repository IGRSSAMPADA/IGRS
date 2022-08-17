/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  13th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.newuser.form;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;


public class UserForgetPasswordForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String hquestionId;
	private String answer;
	private String emailId;
	private String actionType;
	private String pageName;
	private String formName; 
	private String actionName;
	private String erroMessage;
	private String userSessionName;
	private String salt1;
	private String salt2;
	public String getSalt1() {
		return salt1;
	}
	public void setSalt1(String salt1) {
		this.salt1 = salt1;
	}
	public String getSalt2() {
		return salt2;
	}
	public void setSalt2(String salt2) {
		this.salt2 = salt2;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	UserForgetPasswordDTO dto=new UserForgetPasswordDTO();
	
	
	
	
	
	public String getUserSessionName() {
		return userSessionName;
	}
	public void setUserSessionName(String userSessionName) {
		this.userSessionName = userSessionName;
	}
	private ArrayList listForgotPassword = new ArrayList();
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */ 
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the answer.
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer The answer to set.
	 */ 
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return Returns the hquestionId.
	 */
		public String getHquestionId() {
		return hquestionId;
	}
		/**
		 * @param hquestionId The hquestionId to set.
		 */ 
	public void setHquestionId(String hquestionId) {
		this.hquestionId = hquestionId;
	}
	
	/**
	 * @return Returns the emailId.
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId The emailId to set.
	 */ 
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return Returns the actionType.
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType The actionType to set.
	 */ 
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}
	/**
	 * @param pageName The pageName to set.
	 */ 
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	/**
	 * @return Returns the pageName.
	 */
	public UserForgetPasswordDTO getDto() {
		return dto;
	}
	/**
	 * @param pageName The pageName to set.
	 */ 
	public void setDto(UserForgetPasswordDTO dto) {
		this.dto = dto;
	}
	public ArrayList getListForgotPassword() {
		return listForgotPassword;
	}
	public void setListForgotPassword(ArrayList listForgotPassword) {
		this.listForgotPassword = listForgotPassword;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}
	public String getErroMessage() {
		return erroMessage;
	}
	
	
}