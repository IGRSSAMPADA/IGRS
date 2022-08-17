/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserForgetPasswordDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for UserForgetPassword.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  13th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.newuser.dto;
import java.io.Serializable;
import java.util.ArrayList;
import com.wipro.igrs.newuser.dto.UserForgetPasswordDTO;
//import com.wipro.igrs.Log4J.*;


public class UserForgetPasswordDTO implements Serializable{
	 public UserForgetPasswordDTO(){
    	//LoggerMsg.info("we are in UserForgetPasswordDTO");
    
    }
	 private String userName;
	 private String hquestionId;
	 private String  answer;
	 private String emailId;
	 private String name;
	 private String value;
	 private String actionType;
	 private String pageName;
    ArrayList hquestionList;
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
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */ 
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */ 
	public void setValue(String value) {
		this.value = value;
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
	 * @return Returns the hquestionList.
	 */
	public ArrayList getHquestionList() {
		return hquestionList;
	}
	/**
	 * @param hquestionList The hquestionList to set.
	 */ 
	public void setHquestionList(ArrayList hquestionList) {
		this.hquestionList = hquestionList;
	}
    	
	
}