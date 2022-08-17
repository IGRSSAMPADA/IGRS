/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDTO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DTO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedmaster.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ModuleMasterDTO implements Serializable {
	/**
	 * 
	 */
	private String deedPeriority;
	private boolean deedreq; // equivelent to PIN_REQUIRED in DB
	private boolean deedlinkprop; // equivelent to PROPERTY_RELATED_DEED in DB
	private boolean deedRor; // equivelent to ROR_REQUIRED in DB
	private boolean propertyValuerequired;
	private boolean dutyCelRequired;

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private ArrayList moduleList;
	private ArrayList deedViewList;
	private String description;
	private boolean status;
	private String modulename;
	private boolean payment;
	private boolean intimation;
	private ArrayList subList;

	public ArrayList getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList moduleList) {
		this.moduleList = moduleList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public boolean isPayment() {
		return payment;
	}

	public void setPayment(boolean payment) {
		this.payment = payment;
	}

	public boolean isIntimation() {
		return intimation;
	}

	public void setIntimation(boolean intimation) {
		this.intimation = intimation;
	}

	public ArrayList getSubList() {
		return subList;
	}

	public void setSubList(ArrayList subList) {
		this.subList = subList;
	}

	public ArrayList getDeedViewList() {
		return deedViewList;
	}

	public void setDeedViewList(ArrayList deedViewList) {
		this.deedViewList = deedViewList;
	}

	public String getDeedPeriority() {
		return deedPeriority;
	}

	public void setDeedPeriority(String deedPeriority) {
		this.deedPeriority = deedPeriority;
	}

	public boolean isDeedreq() {
		return deedreq;
	}

	public void setDeedreq(boolean deedreq) {
		this.deedreq = deedreq;
	}

	public boolean isDeedlinkprop() {
		return deedlinkprop;
	}

	public void setDeedlinkprop(boolean deedlinkprop) {
		this.deedlinkprop = deedlinkprop;
	}

	public boolean isDeedRor() {
		return deedRor;
	}

	public void setDeedRor(boolean deedRor) {
		this.deedRor = deedRor;
	}

	public boolean isPropertyValuerequired() {
		return propertyValuerequired;
	}

	public void setPropertyValuerequired(boolean propertyValuerequired) {
		this.propertyValuerequired = propertyValuerequired;
	}

	public boolean isDutyCelRequired() {
		return dutyCelRequired;
	}

	public void setDutyCelRequired(boolean dutyCelRequired) {
		this.dutyCelRequired = dutyCelRequired;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}