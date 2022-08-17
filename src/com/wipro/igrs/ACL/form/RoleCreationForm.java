/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ACLConstants.java
 * Author      :   Neesha Aggarwal
 * Description :   Represents the mapped resources for ACL Module.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha Aggarwal  24th MArch,2008 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.ACL.dto.RoleCreationDTO;

// import java.util.ArrayList;

/**
 * @author neegaga
 * 
 */
public class RoleCreationForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	RoleCreationDTO rolecrtDTO = new RoleCreationDTO();

	private String status;
	private int rowCount;
	private String temp;
	private String roleName;
	 
	private ArrayList detailsList = new ArrayList();
	private String roleDesc;
	private String id;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc
	 *            the roleDesc to set
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the rolecrtDTO
	 */
	public RoleCreationDTO getRolecrtDTO() {
		return rolecrtDTO;
	}

	/**
	 * @param rolecrtDTO
	 *            the rolecrtDTO to set
	 */
	public void setRolecrtDTO(RoleCreationDTO rolecrtDTO) {
		this.rolecrtDTO = rolecrtDTO;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * @return the detailsList
	 */
	public ArrayList getDetailsList() {
		return detailsList;
	}

	/**
	 * @param detailsList
	 *            the detailsList to set
	 */
	public void setDetailsList(ArrayList detailsList) {
		this.detailsList = detailsList;
	}

}
