/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityForm.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  28th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.ACL.dto.ActivityDTO;

/**
 * @author neegaga
 * 
 */
public class ActivityForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	ActivityDTO actDTO = new ActivityDTO();
	private ArrayList roleList = new ArrayList();
	private ArrayList actList = new ArrayList();
	private String flag;
	private String selected;
	private String notSelected;
	// private ArrayList confirmList = new ArrayList();
	private String role;

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the selected
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(String selected) {
		this.selected = selected;
	}

	/**
	 * @return the notSelected
	 */
	public String getNotSelected() {
		return notSelected;
	}

	/**
	 * @param notSelected
	 *            the notSelected to set
	 */
	public void setNotSelected(String notSelected) {
		this.notSelected = notSelected;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the roleList
	 */
	public ArrayList getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(ArrayList roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the actDTO
	 */
	public ActivityDTO getActDTO() {
		return actDTO;
	}

	/**
	 * @param actDTO
	 *            the actDTO to set
	 */
	public void setActDTO(ActivityDTO actDTO) {
		this.actDTO = actDTO;
	}

	/**
	 * @return the actList
	 */
	public ArrayList getActList() {
		return actList;
	}

	/**
	 * @param actList
	 *            the actList to set
	 */
	public void setActList(ArrayList actList) {
		this.actList = actList;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
