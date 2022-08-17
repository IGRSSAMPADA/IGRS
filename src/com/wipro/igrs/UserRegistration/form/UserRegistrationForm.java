/*
 * Copyright Notice
 *=============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *=============================================================================
 *
 * File Name   :   UserRegistrationForm.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the FormClass for UserRegistration
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  26th Dec, 2007	 		 
 * ----------------------------------------------------------------------------
 */


package com.wipro.igrs.UserRegistration.form;


import org.apache.struts.action.ActionForm;
import com.wipro.igrs.UserRegistration.dto.UserRegistrationDTO;
import java.util.ArrayList;


/**
 * @author nihraa
 *
 */

public class UserRegistrationForm extends ActionForm {
   private String selctStateId;
	private static final long serialVersionUID = 1L;
	
	/**
	 * viewLicenseList
	 */
	private ArrayList viewLicenseList;
	private String currentYear;
	private String erroMessage;
	
	/**
	 * userRegDTO
	 */
	UserRegistrationDTO userRegDTO = new UserRegistrationDTO();
	/**
	 * userCountry
	 */
	private ArrayList userCountryList = new ArrayList();
	/**
	 * userState
	 */
	private ArrayList userStateList = new ArrayList();
	
	/**
	 * userCityList
	 */
	private ArrayList userCityList = new ArrayList();
	/**
	 * userPhotoId
	 */
	private ArrayList userPhotoId = new ArrayList();
	/**
	 * userHintQuestion
	 */
	private ArrayList userHintQuestion = new ArrayList();
	
	private ArrayList occupationList = new ArrayList();
	//added by SHREERAJ
		
	private ArrayList categoryList;	



	public ArrayList getCategoryList() {
		return categoryList;
	}
		
	public void setCategoryList(ArrayList categoryList) {
			this.categoryList = categoryList;
		}
		
	
	
	

	public ArrayList getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(ArrayList occupationList) {
		this.occupationList = occupationList;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	/**
	 * @param userRegDTO 
	 */
	public void setUserRegDTO(UserRegistrationDTO userRegDTO) {
		this.userRegDTO = userRegDTO;
	}
	/**
	 * @return UserRegistrationDTO userRegDTO
	 */
	public ArrayList getUserHintQuestion() {
		return userHintQuestion;
	}
	/**
	 * @param userHintQuestion
	 */
	public void setUserHintQuestion(ArrayList userHintQuestion) {
		this.userHintQuestion = userHintQuestion;
	}
	/**
	 * @return userHintQuestion
	 */
	public UserRegistrationDTO getUserRegDTO() {
		return userRegDTO;
	}
	/**
	 * @param userCountry
	 */
	public void setUserCountryList(ArrayList userCountryList) {
		this.userCountryList = userCountryList;
	}
	/**
	 * @return userCountry
	 */
	public ArrayList getUserCountryList() {
		return userCountryList;
	}
	/**
	 * @param userState
	 */
	public void setUserStateList(ArrayList userStateList) {
		this.userStateList = userStateList;
	}
	/**
	 * @return ArrayList userState
	 */
	public ArrayList getUserStateList() {
		return userStateList;
	}
	/**
	 * @param userPhotoId 
	 */
	public void setUserPhotoId(ArrayList userPhotoId) {
		this.userPhotoId = userPhotoId;
	}
	/**
	 * @return getUserPhotoId
	 */
	public ArrayList getUserPhotoId() {
		return userPhotoId;
	}
	public ArrayList getViewLicenseList() {
		return viewLicenseList;
	}
	public void setViewLicenseList(ArrayList viewLicenseList) {
		this.viewLicenseList = viewLicenseList;
	}
	/**
	 * @return the userCityList
	 */
	public ArrayList getUserCityList() {
		return userCityList;
	}
	/**
	 * @param userCityList the userCityList to set
	 */
	public void setUserCityList(ArrayList userCityList) {
		this.userCityList = userCityList;
	}
	/**
	 * @return the selctStateId
	 */
	public String getSelctStateId() {
		return selctStateId;
	}
	/**
	 * @param selctStateId the selctStateId to set
	 */
	public void setSelctStateId(String selctStateId) {
		this.selctStateId = selctStateId;
	}

	public void setErroMessage(String erroMessage) {
		this.erroMessage = erroMessage;
	}

	public String getErroMessage() {
		return erroMessage;
	}
}
