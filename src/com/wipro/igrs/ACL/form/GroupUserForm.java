/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupUserForm.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the form for the Group User-acl module 
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  31st MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.ACL.dto.GroupsUserDTO;

/**
 * @author neegaga
 * 
 */
public class GroupUserForm extends ActionForm {

	GroupsUserDTO grpUserDTO = new GroupsUserDTO();
	private ArrayList grpList = new ArrayList();
	private String userNo;
	private String flag;
	private String selected;
	private String notSelected;
	private String empId;

	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}

	/**
	 * @param empId
	 *            the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
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
	 * @return the actDTO
	 */
	public GroupsUserDTO getGrpUserDTO() {
		return grpUserDTO;
	}

	/**
	 * @return the userNo
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * @param userNo
	 *            the userNo to set
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * @param actDTO
	 *            the actDTO to set
	 */
	public void setGrpUserDTO(GroupsUserDTO grpUserDTO) {
		this.grpUserDTO = grpUserDTO;
	}

	/**
	 * @return the grpList
	 */
	public ArrayList getGrpList() {
		return grpList;
	}

	/**
	 * @param grpList
	 *            the grpList to set
	 */
	public void setGrpList(ArrayList grpList) {
		this.grpList = grpList;
	}

}
