/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariForm.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.wardpatwarimaster.dto.WardpatwariDTO;

public class WardpatwariForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String wardpatwariId;
	private String wardpatwariName;
	private String wardpatwariDesc;
	private String wardpatwariStatus;
	private String wardpageName;
	private String tehsilId;
	private WardpatwariDTO dto;
	private ArrayList wardpatwariList;
	public String getWardpatwariStatus() {
		return wardpatwariStatus;
	}
	public void setWardpatwariStatus(String wardpatwariStatus) {
		this.wardpatwariStatus = wardpatwariStatus;
	}
	public String getWardpatwariId() {
		return wardpatwariId;
	}
	public void setWardpatwariId(String wardpatwariId) {
		this.wardpatwariId = wardpatwariId;
	}
	public String getWardpatwariName() {
		return wardpatwariName;
	}
	public void setWardpatwariName(String wardpatwariName) {
		this.wardpatwariName = wardpatwariName;
	}
	public String getWardpatwariDesc() {
		return wardpatwariDesc;
	}
	public void setWardpatwariDesc(String wardpatwariDesc) {
		this.wardpatwariDesc = wardpatwariDesc;
	}
	
	public WardpatwariDTO getDto() {
		return dto;
	}
	public void setDto(WardpatwariDTO dto) {
		this.dto = dto;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getWardpageName() {
		return wardpageName;
	}
	public void setWardpageName(String wardpageName) {
		this.wardpageName = wardpageName;
	}
	public ArrayList getWardpatwariList() {
		return wardpatwariList;
	}
	public void setWardpatwariList(ArrayList wardpatwariList) {
		this.wardpatwariList = wardpatwariList;
	}

}
