/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictForm.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 01/03/2008
 */
package com.wipro.igrs.districtmaster.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.districtmaster.dto.DistrictDTO;

public class DistrictForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String districtId;
	private String districtName;
	private String districtDesc;
	private String districtStatus;
	private String stateId;
	private String distpageName;
	private ArrayList districtList;
	private String getDistrictId;
	private DistrictDTO dto;
	public String getDistrictName() {
		return districtName;
	}
	public String getGetDistrictId() {
		return getDistrictId;
	}
	public void setGetDistrictId(String getDistrictId) {
		this.getDistrictId = getDistrictId;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictDesc() {
		return districtDesc;
	}
	public void setDistrictDesc(String districtDesc) {
		this.districtDesc = districtDesc;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictStatus() {
		return districtStatus;
	}
	public void setDistrictStatus(String districtStatus) {
		this.districtStatus = districtStatus;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public DistrictDTO getDto() {
		return dto;
	}
	public void setDto(DistrictDTO dto) {
		this.dto = dto;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public String getDistpageName() {
		return distpageName;
	}
	public void setDistpageName(String distpageName) {
		this.distpageName = distpageName;
	}
}
