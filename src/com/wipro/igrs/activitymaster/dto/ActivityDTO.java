/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.activitymaster.dto;

import java.util.ArrayList;

public class ActivityDTO {
	public ActivityDTO() {

	}

	private String activityId;
	private String activityName;
	private String activityDesc;
	private String activityStatus;
	private String pageName;
	ArrayList activityList;

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
	 * @return Returns the activityList.
	 */
	public ArrayList getActivityList() {
		return activityList;
	}

	/**
	 * 
	 * @param activityList
	 *            The activityList to set
	 */

	public void setActivityList(ArrayList activityList) {
		this.activityList = activityList;
	}

}