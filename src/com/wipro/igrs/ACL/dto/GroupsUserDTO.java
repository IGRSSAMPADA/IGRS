/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupsUserDTO.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the DTO class for the Groups User-ACL Module
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  31st MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.dto;

import java.io.Serializable;

public class GroupsUserDTO implements Serializable, Comparable {
	private String empName;
	private String grpUserAction;
	private String groupName;
	private String groupId;
	private String slctFlag;

	public GroupsUserDTO() {

	}

	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}

	/**
	 * @param empName
	 *            the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}

	/**
	 * @return the grpUserAction
	 */
	public String getGrpUserAction() {
		return grpUserAction;
	}

	/**
	 * @param grpUserAction
	 *            the grpUserAction to set
	 */
	public void setGrpUserAction(String grpUserAction) {
		this.grpUserAction = grpUserAction;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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

	public int compareTo(Object obj) {
		GroupsUserDTO dto = (GroupsUserDTO) obj;
		int comp = groupName.compareTo(dto.getGroupName());

		return ((comp == 0) ? groupName.compareTo(dto.getGroupName()) : comp);

	}
}
