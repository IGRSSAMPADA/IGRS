/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.groupmaster.form;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.groupmaster.dto.GroupDTO;


public class GroupForm extends ActionForm {
	
	private String rgroupId;
	private String rgroupName;
	private String rgroupDesc;
	private String rgroupStatus;
    private String rgrouppageName;
	GroupDTO dto=new GroupDTO();
	 /**
	 * @return Returns the rgroupId.
	 */
	public String getRgroupId() {
		return rgroupId;
	}
/**
 * @param rgroupId The rgroupId to set.
 */  
	public void setRgroupId(String rgroupId) {
		this.rgroupId = rgroupId;
	}
	/**
	 * @return Returns the rgroupName.
	 */
	public String getRgroupName() {
		return rgroupName;
	}
	/**
	 * @param rgroupName The rgroupName to set.
	 */  
	public void setRgroupName(String rgroupName) {
		this.rgroupName = rgroupName;
	}
	/**
	 * @return Returns the rgroupDesc.
	 */
	public String getRgroupDesc() {
		return rgroupDesc;
	}
	/**
	 * @param rgroupDesc The rgroupDesc to set.
	 */  
	public void setRgroupDesc(String rgroupDesc) {
		this.rgroupDesc = rgroupDesc;
	}
	/**
	 * @return Returns the rgroupStatus.
	 */
	public String getRgroupStatus() {
		return rgroupStatus;
	}
	/**
	 * @param rgroupStatus The rgroupStatus to set.
	 */  
	public void setRgroupStatus(String rgroupStatus) {
		this.rgroupStatus = rgroupStatus;
	}
	/**
	 * @return Returns the rgrouppageName.
	 */
	public String getRgrouppageName() {
		return rgrouppageName;
	}
	/**
	 * @param rgrouppageName The rgrouppageName to set.
	 */  
	public void setRgrouppageName(String rgrouppageName) {
		this.rgrouppageName = rgrouppageName;
	}
	/**
	 * @return Returns the dto.
	 */
	public GroupDTO getDto() {
		return dto;
	}
	/**
	 * @param dto The dto to set.
	 */  
	public void setDto(GroupDTO dto) {
		this.dto = dto;
	}
	
}