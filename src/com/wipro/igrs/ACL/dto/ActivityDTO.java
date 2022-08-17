/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityDTO.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  28th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;
import java.util.ArrayList;


public class ActivityDTO implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;
	private ArrayList rolesList;
	// private ArrayList ListAct;
	private String actionActivity;
	private String roleName;
	private String roleId;
	private String activityId;
	private String activityName;
	private String slctFlag;

	public ActivityDTO() {

	}

	/**
	 * @return the slctFlag
	 */
	public String getSlctFlag() {
		return slctFlag;
	}

	/**
	 * @param slctFlag
	 *            the slctFlag to set
	 */
	public void setSlctFlag(String slctFlag) {
		this.slctFlag = slctFlag;
	}

	/**
	 * @return the roleId
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the actionActivity
	 */
	public String getActionActivity() {
		return actionActivity;
	}

	/**
	 * @param actionActivity
	 *            the actionActivity to set
	 */
	public void setActionActivity(String actionActivity) {
		this.actionActivity = actionActivity;
	}

	/**
	 * @return the rolesList
	 */
	public ArrayList getRolesList() {
		return rolesList;
	}

	/**
	 * @param rolesList
	 *            the rolesList to set
	 */
	public void setRolesList(ArrayList rolesList) {
		this.rolesList = rolesList;
	}

	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}

	/**
	 * @param activityId
	 *            the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	/**
	 * @return the activityName
	 */
	public String getActivityName() {
		return activityName;
	}

	/**
	 * @param activityName
	 *            the activityName to set
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int compareTo(Object obj) {
		ActivityDTO dto = (ActivityDTO) obj;
		int comp = activityName.compareTo(dto.getActivityName());

		return ((comp == 0) ? activityName.compareTo(dto.getActivityName())
				: comp);

	}

}
