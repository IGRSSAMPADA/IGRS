/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RegisterofficesForm.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the FormBean Class for RegisterofficeMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  4th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.registerofficesmaster.form;
import org.apache.struts.action.ActionForm;
import com.wipro.igrs.registerofficesmaster.dto.RegisterofficesDTO;


public class RegisterofficesForm extends ActionForm {
	
	private String registerofficeId;
	private String registerofficeName;
	private String registerofficeDesc;
	private String registerofficeStatus;
	private String registerofficetypeId;
	private String registerofficedisticId;
	private String registerofficewardId;
	private String registerofficetehsilId;
	private String officepageName;
	RegisterofficesDTO dto=new RegisterofficesDTO();
	 /**
	 * @return Returns the registerofficeId.
	 */
	public String getRegisterofficeId() {
		return registerofficeId;
	}
	/**
	 * @param registerofficeId The registerofficeId to set.
	 */  
	public void setRegisterofficeId(String registerofficeId) {
		this.registerofficeId = registerofficeId;
	}
	 /**
	 * @return Returns the registerofficeName.
	 */
	public String getRegisterofficeName() {
		return registerofficeName;
	}
	/**
	 * @param registerofficeName The registerofficeName to set.
	 */  
	public void setRegisterofficeName(String registerofficeName) {
		this.registerofficeName = registerofficeName;
	}
	 /**
	 * @return Returns the registerofficeDesc.
	 */
	public String getRegisterofficeDesc() {
		return registerofficeDesc;
	}
	/**
	 * @param registerofficeDesc The registerofficeDesc to set.
	 */ 
	public void setRegisterofficeDesc(String registerofficeDesc) {
		this.registerofficeDesc = registerofficeDesc;
	}
	 /**
	 * @return Returns the registerofficeStatus.
	 */
	public String getRegisterofficeStatus() {
		return registerofficeStatus;
	}
	/**
	 * @param registerofficeStatus The registerofficeStatus to set.
	 */ 
	public void setRegisterofficeStatus(String registerofficeStatus) {
		this.registerofficeStatus = registerofficeStatus;
	}
	 /**
	 * @return Returns the registerofficetypeId.
	 */
	public String getRegisterofficetypeId() {
		return registerofficetypeId;
	}
	/**
	 * @param registerofficetypeId The registerofficetypeId to set.
	 */ 
	public void setRegisterofficetypeId(String registerofficetypeId) {
		this.registerofficetypeId = registerofficetypeId;
	}
	 /**
	 * @return Returns the registerofficedisticId.
	 */
	public String getRegisterofficedisticId() {
		return registerofficedisticId;
	}
	/**
	 * @param registerofficedisticId The registerofficedisticId to set.
	 */ 
	public void setRegisterofficedisticId(String registerofficedisticId) {
		this.registerofficedisticId = registerofficedisticId;
	}
	 /**
	 * @return Returns the officepageName.
	 */
	public String getOfficepageName() {
		return officepageName;
	}
	/**
	 * @param officepageName The officepageName to set.
	 */ 
	public void setOfficepageName(String officepageName) {
		this.officepageName = officepageName;
	}
	 
	 /**
	 * @return Returns the registerofficewardId.
	 */
	public String getRegisterofficewardId() {
		return registerofficewardId;
	}
	/**
	 * @param registerofficewardId The registerofficewardId to set.
	 */ 
	public void setRegisterofficewardId(String registerofficewardId) {
		this.registerofficewardId = registerofficewardId;
	}
	 /**
	 * @return Returns the registerofficetehsilId.
	 */
	public String getRegisterofficetehsilId() {
		return registerofficetehsilId;
	}
	/**
	 * @param registerofficetehsilId The registerofficetehsilId to set.
	 */ 
	public void setRegisterofficetehsilId(String registerofficetehsilId) {
		this.registerofficetehsilId = registerofficetehsilId;
	}
	/**
	 * @return Returns the dto.
	 */ 
	public RegisterofficesDTO getDto() {
		return dto;
	}
	/**
	 * @param dto The dto to set.
	 */ 
	public void setDto(RegisterofficesDTO dto) {
		this.dto = dto;
	}
	
}