/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmappingmaster.form;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.userrolegroupmappingmaster.dto.URolegroupDTO;


public class URolegroupForm extends ActionForm {
	
	private String userId;
	private String rolegroupId;
	private String activeFlag;
	private String pageName;
	URolegroupDTO dto=new URolegroupDTO();
	

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
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */ 
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the dto.
	 */
	public URolegroupDTO getDto() {
		return dto;
	}
	/**
	 * @param dto The dto to set.
	 */ 
	public void setDto(URolegroupDTO dto) {
		this.dto = dto;
	}

    
}