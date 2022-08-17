/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.activitymaster.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.activitymaster.dto.ActivityDTO;

public class ActivityForm extends ActionForm {

	private String activityId;
	private String activityName;
	private String activityDesc;
	private String activityStatus;
	private String pageName;
	ActivityDTO dto = new ActivityDTO();

	/**
	 * @return Returns the activityId.
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId
	 *            The activityId to set.
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return Returns the activityName.
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName
	 *            The activityName to set.
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * @return Returns the activityDesc.
	 */
	public String getActivityDesc() {
		return activityDesc;
	}

	/**
	 * @param activityDesc
	 *            The activityDesc to set.
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}

	/**
	 * @return Returns the activityStatus.
	 */
	public String getActivityStatus() {
		return activityStatus;
	}

	/**
	 * @param activityStatus
	 *            The activityStatus to set.
	 */
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}

	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @param pageName
	 *            The pageName to set.
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return Returns the dto.
	 */
	public ActivityDTO getDto() {
		return dto;
	}

	/**
	 * @param dto
	 *            The dto to set.
	 */
	public void setDto(ActivityDTO dto) {
		this.dto = dto;
	}
}