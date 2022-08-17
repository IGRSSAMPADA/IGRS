/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallaMasterForm.java
 * Author      :   vengamamba.p
 * Description :   
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0           vengamamba	     16 jun, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.MohallaMasterDTO;

public class MohallaMasterForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String mohallaPageName;
	private String actionValue;

	private String patwariId;
	private String mohallaDesc;
	private String mohallaStatus;
	private String mohallaVillageName;
	private String type;
	private String mohallaId;

	private MohallaMasterDTO mohalladto;

	public String getMohallaPageName() {
		return mohallaPageName;
	}

	public void setMohallaPageName(String mohallaPageName) {
		this.mohallaPageName = mohallaPageName;
	}

	public String getPatwariId() {
		return patwariId;
	}

	public void setPatwariId(String patwariId) {
		this.patwariId = patwariId;
	}

	public String getMohallaDesc() {
		return mohallaDesc;
	}

	public void setMohallaDesc(String mohallaDesc) {
		this.mohallaDesc = mohallaDesc;
	}

	public String getMohallaStatus() {
		return mohallaStatus;
	}

	public void setMohallaStatus(String mohallaStatus) {
		this.mohallaStatus = mohallaStatus;
	}

	public String getMohallaVillageName() {
		return mohallaVillageName;
	}

	public void setMohallaVillageName(String mohallaVillageName) {
		this.mohallaVillageName = mohallaVillageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public MohallaMasterDTO getMohalladto() {
		return mohalladto;
	}

	public void setMohalladto(MohallaMasterDTO mohalladto) {
		this.mohalladto = mohalladto;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}
}
