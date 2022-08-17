/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the formBean Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;

public class MohallavillageForm extends ActionForm {

	private String mohallavillageId;
	private String mohallavillageName;
	private String mohallavillageDesc;
	private String mohallavillageStatus;
	private String mohallapageName;
	private String wardpatwariId;
	MohallavillageDTO dto = new MohallavillageDTO();

	/**
	 * @return Returns the mohallavillageId.
	 */
	public String getMohallavillageId() {
		return mohallavillageId;
	}

	/**
	 * @param mohallavillageId
	 *            The mohallavillageId to set.
	 */
	public void setMohallavillageId(String mohallavillageId) {
		this.mohallavillageId = mohallavillageId;
	}

	/**
	 * @return Returns the mohallavillageName.
	 */
	public String getMohallavillageName() {
		return mohallavillageName;
	}

	/**
	 * @param mohallavillageName
	 *            The mohallavillageName to set.
	 */
	public void setMohallavillageName(String mohallavillageName) {
		this.mohallavillageName = mohallavillageName;
	}

	/**
	 * @return Returns the mohallavillageDesc.
	 */
	public String getMohallavillageDesc() {
		return mohallavillageDesc;
	}

	/**
	 * @param mohallavillageDesc
	 *            The mohallavillageDesc to set.
	 */
	public void setMohallavillageDesc(String mohallavillageDesc) {
		this.mohallavillageDesc = mohallavillageDesc;
	}

	/**
	 * @return Returns the mohallavillageStatus.
	 */
	public String getMohallavillageStatus() {
		return mohallavillageStatus;
	}

	/**
	 * @param mohallavillageStatus
	 *            The mohallavillageStatus to set.
	 */
	public void setMohallavillageStatus(String mohallavillageStatus) {
		this.mohallavillageStatus = mohallavillageStatus;
	}

	/**
	 * @return Returns the dto.
	 */
	public MohallavillageDTO getDto() {
		return dto;
	}

	/**
	 * @param dto
	 *            The dto to set.
	 */
	public void setDto(MohallavillageDTO dto) {
		this.dto = dto;
	}

	/**
	 * @return Returns the mohallapageName.
	 */
	public String getMohallapageName() {
		return mohallapageName;
	}

	/**
	 * @param mohallapageName
	 *            The mohallapageName to set.
	 */
	public void setMohallapageName(String mohallapageName) {
		this.mohallapageName = mohallapageName;
	}

	/**
	 * @return Returns the wardpatwariId.
	 */
	public String getWardpatwariId() {
		return wardpatwariId;
	}

	/**
	 * @param wardpatwariId
	 *            The wardpatwariId to set.
	 */
	public void setWardpatwariId(String wardpatwariId) {
		this.wardpatwariId = wardpatwariId;
	}

}