/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RolesGroupForm.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the Action class for the ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  31st MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.ACL.dto.RolesGroupDTO;

/**
 * @author neegaga
 * 
 */
public class RolesGroupForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	RolesGroupDTO rgrpDTO = new RolesGroupDTO();
	private ArrayList grpList = new ArrayList();
	private ArrayList rolesList = new ArrayList();
	private ArrayList grpRoleList=new ArrayList();
	private String flag;
	private String selected;
	
	public ArrayList getGrpRoleList() {
		return grpRoleList;
	}

	public void setGrpRoleList(ArrayList grpRoleList) {
		this.grpRoleList = grpRoleList;
	}

	private String notSelected;
	private String group;

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
	 * @return the rgrpDTO
	 */
	public RolesGroupDTO getRgrpDTO() {
		return rgrpDTO;
	}

	/**
	 * @param rgrpDTO
	 *            the rgrpDTO to set
	 */
	public void setRgrpDTO(RolesGroupDTO rgrpDTO) {
		this.rgrpDTO = rgrpDTO;
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
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

}
