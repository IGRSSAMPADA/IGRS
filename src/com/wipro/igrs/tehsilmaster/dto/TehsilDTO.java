/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilDTO.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.dto;

import java.util.ArrayList;

public class TehsilDTO implements java.io.Serializable{
	public TehsilDTO(){
	}
	private String districtId;
	private String tehsilId;
	private String tehsilName;
	private String tehsilDesc;
	private String tehsilStatus;
	private String tehsilpageName;
	private String getTehsilId;
	private String name;
	private String value;
	private ArrayList districtidList;
	private ArrayList tehsilList;
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	public String getTehsilId() {
		return tehsilId;
	}
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getTehsilDesc() {
		return tehsilDesc;
	}
	public void setTehsilDesc(String tehsilDesc) {
		this.tehsilDesc = tehsilDesc;
	}
	public String getTehsilStatus() {
		return tehsilStatus;
	}
	public void setTehsilStatus(String tehsilStatus) {
		this.tehsilStatus = tehsilStatus;
	}
	public String getTehsilpageName() {
		return tehsilpageName;
	}
	public void setTehsilpageName(String tehsilpageName) {
		this.tehsilpageName = tehsilpageName;
	}
	public String getGetTehsilId() {
		return getTehsilId;
	}
	public void setGetTehsilId(String getTehsilId) {
		this.getTehsilId = getTehsilId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public ArrayList getDistrictidList() {
		return districtidList;
	}
	public void setDistrictidList(ArrayList districtidList) {
		this.districtidList = districtidList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
