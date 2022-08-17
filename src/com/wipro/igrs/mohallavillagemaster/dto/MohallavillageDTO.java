/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageDTO.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the DTO Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.dto;
import java.util.ArrayList;
import com.wipro.igrs.mohallavillagemaster.dto.MohallavillageDTO;

public class MohallavillageDTO{
	private static final long serialVersionUID = 1L;
	private String mohallavillageId;
	private String mohallavillageName;
	private String mohallavillageDesc;
	private String mohallavillageStatus;
	private String wardpatwariId;
	private String name;
	private String value;
	private ArrayList  mohallaList;
	private ArrayList  wardpatwariList;
	/**
	 * @return Returns the mohallavillageId.
	 */
	public String getMohallavillageId() {
		return mohallavillageId;
	}
	/**
	 * @param mohallavillageId The mohallavillageId to set.
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
	 * @param mohallavillageName The mohallavillageName to set.
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
	 * @param mohallavillageDesc The mohallavillageDesc to set.
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
	 * @param mohallavillageStatus The mohallavillageStatus to set.
	 */ 
	public void setMohallavillageStatus(String mohallavillageStatus) {
		this.mohallavillageStatus = mohallavillageStatus;
	}
	/**
	 * @return Returns the mohallaList.
	 */
	public ArrayList getMohallaList() {
		return mohallaList;
	}
	/**
	 * @param mohallaList The mohallaList to set.
	 */ 
	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}
	/**
	 * @return Returns the wardpatwariId.
	 */
	public String getWardpatwariId() {
		return wardpatwariId;
	}
	/**
	 * @param wardpatwariId The wardpatwariId to set.
	 */ 
	public void setWardpatwariId(String wardpatwariId) {
		this.wardpatwariId = wardpatwariId;
	}
	/**
	 * @return Returns the wardpatwariList.
	 */
	public ArrayList getWardpatwariList() {
		return wardpatwariList;
	}
	/**
	 * @param wardpatwariList The wardpatwariList to set.
	 */ 
	public void setWardpatwariList(ArrayList wardpatwariList) {
		this.wardpatwariList = wardpatwariList;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */ 
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */ 
	public void setValue(String value) {
		this.value = value;
	}
	
}