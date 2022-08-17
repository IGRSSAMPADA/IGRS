/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupForm.java
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

import com.wipro.igrs.ACL.dto.GroupDTO;

/**
 * @author neegaga
 * 
 */
public class GroupForm extends ActionForm {

	GroupDTO grpcrtDTO;

	private String status;
	private String combinedStr;
	private ArrayList grpList;
	private String grpid;

	/**
	 * @return the grpid
	 */
	public String getGrpid() {
		return grpid;
	}

	/**
	 * @param grpid
	 *            the grpid to set
	 */
	public void setGrpid(String grpid) {
		this.grpid = grpid;
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

	public GroupForm() {
		this.grpcrtDTO = new GroupDTO();

	}

	public String getCombinedStr() {
		return combinedStr;
	}

	public void setCombinedStr(String combinedStr) {
		this.combinedStr = combinedStr;
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
	 * @return the grpcrtDTO
	 */
	public GroupDTO getGrpcrtDTO() {
		return grpcrtDTO;
	}

	/**
	 * @param grpcrtDTO
	 *            the grpcrtDTO to set
	 */
	public void setGrpcrtDTO(GroupDTO grpcrtDTO) {
		this.grpcrtDTO = grpcrtDTO;
	}

}
