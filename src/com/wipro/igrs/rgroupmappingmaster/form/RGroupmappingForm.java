/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rgroupmappingmaster.form;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.rgroupmappingmaster.dto.RGroupmappingDTO;


public class RGroupmappingForm extends ActionForm {
	
	private String roleId;
	private String rolegroupId;
	private String activeFlag;
	private String pageName;
	RGroupmappingDTO dto=new RGroupmappingDTO();
	
	/**
	 * @return Returns the roleId.
	 */
	
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId The roleId to set.
	 */ 
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return Returns the rolegroupId.
	 */
	public String getRolegroupId() {
		return rolegroupId;
	}
	/**
	 * @param rolegroupId The rolegroupId to set.
	 */ 
	public void setRolegroupId(String rolegroupId) {
		this.rolegroupId = rolegroupId;
	}
	/**
	 * @return Returns the activeFlag.
	 */
	public String getActiveFlag() {
		return activeFlag;
	}
	/**
	 * @param activeFlag The activeFlag to set.
	 */ 
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	/**
	 * @return Returns the pageName.
	 */
	public String getPageName() {
		return pageName;
	}
	/**
	 * @param pageName The pageName to set.
	 */ 
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	/**
	 * @return Returns the dto.
	 */
	public RGroupmappingDTO getDto() {
		return dto;
	}
	/**
	 * @param dto The dto to set.
	 */ 
	public void setDto(RGroupmappingDTO dto) {
		this.dto = dto;
	}
    
}