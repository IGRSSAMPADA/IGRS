/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionDTO.java
 * Author      :   Sara Hussain 
 * Description :   Represents the Data Transfer Object Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */

package com.wipro.igrs.religionmaster.dto;

import java.util.ArrayList;

public class ReligionDTO {

	public ReligionDTO() {
		
	}
	
	private String religionId;
	private String religionName;
	private String religionDesc;
	private String religionStatus;
	private String pageName;
	private String createdBy;
	private String updateBy;
	private String createdDate;
	private String updateDate;
	private String oldName;
	private ArrayList religionList ;
	/**
	 * @return the religionId
	 */
	public String getReligionId() {
		return religionId;
	}
	/**
	 * @param religionId the religionId to set
	 */
	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}
	/**
	 * @return the religionName
	 */
	public String getReligionName() {
		return religionName;
	}
	/**
	 * @param religionName the religionName to set
	 */
	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}
	/**
	 * @return the religionDesc
	 */
	public String getReligionDesc() {
		return religionDesc;
	}
	/**
	 * @param religionDesc the religionDesc to set
	 */
	public void setReligionDesc(String religionDesc) {
		this.religionDesc = religionDesc;
	}
	/**
	 * @return the religionStatus
	 */
	public String getReligionStatus() {
		return religionStatus;
	}
	/**
	 * @param religionStatus the religionStatus to set
	 */
	public void setReligionStatus(String religionStatus) {
		this.religionStatus = religionStatus;
	}
	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}
	/**
	 * @param pageName the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	/**
	 * @return the religionList
	 */
	/**
	 * @return the religionList
	 */
	public ArrayList getReligionList() {
		return religionList;
	}
	/**
	 * @param religionList the religionList to set
	 */
	public void setReligionList(ArrayList religionList) {
		this.religionList = religionList;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;

	}

	/**
	 * @return the updateBy
	 */
	public String getUpdateBy() {
		return updateBy;
	}
	/**
	 * @param updateBy the updateBy to set
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the oldName
	 */
	public String getOldName() {
		return oldName;
	}
	/**
	 * @param oldName the oldName to set
	 */
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	
}
