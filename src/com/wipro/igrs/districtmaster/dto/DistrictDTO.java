/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictDTO.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 02/03/2008
 */
package com.wipro.igrs.districtmaster.dto;

import java.util.ArrayList;
public class DistrictDTO {
	public DistrictDTO(){
	}
	private String districtId;
	private String districtName;
	private String districtDesc;
	private String districtStatus;
	private String stateId;
	private String distpageName;
	private ArrayList districtList;
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistpageName() {
		return distpageName;
	}
	public void setDistpageName(String distpageName) {
		this.distpageName = distpageName;
	}
	public String getDistrictName() {
		return districtName;
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
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
}
