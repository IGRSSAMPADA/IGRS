/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupDTO.java
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

public class GroupDTO implements Serializable {

	private String grpName;
	private String grpDesc;
	private String status;
	private String grpAction;
	private int sno;
	ArrayList groupsList = new ArrayList();
	String grpId;

	/**
	 * @return the grpId
	 */
	public String getGrpId() {
		return grpId;
	}

	/**
	 * @param grpId
	 *            the grpId to set
	 */
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public GroupDTO() {
		this.groupsList = new ArrayList();
	}

	/**
	 * @return the grpName
	 */
	public String getGrpName() {
		return grpName;
	}

	/**
	 * @param grpName
	 *            the grpName to set
	 */
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	/**
	 * @return the grpDesc
	 */
	public String getGrpDesc() {
		return grpDesc;
	}

	/**
	 * @param grpDesc
	 *            the grpDesc to set
	 */
	public void setGrpDesc(String grpDesc) {
		this.grpDesc = grpDesc;
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
	 * @return the grpAction
	 */
	public String getGrpAction() {
		return grpAction;
	}

	/**
	 * @param grpAction
	 *            the grpAction to set
	 */
	public void setGrpAction(String grpAction) {
		this.grpAction = grpAction;
	}

	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}

	/**
	 * @param sno
	 *            the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}

	/**
	 * @return the groupsList
	 */
	public ArrayList getGroupsList() {
		return groupsList;
	}

	/**
	 * @param groupsList
	 *            the groupsList to set
	 */
	public void setGroupsList(ArrayList groupsList) {
		this.groupsList = groupsList;
	}

}
