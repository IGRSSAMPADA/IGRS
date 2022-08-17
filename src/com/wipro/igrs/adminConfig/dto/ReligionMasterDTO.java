/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	ReligionMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	19/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class ReligionMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String religionName;
	private String religionDesc;
	private String religionStatus;
	private String religionId;

	private ArrayList religionList;

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

	public String getReligionName() {
		return religionName;
	}

	public void setReligionName(String religionName) {
		this.religionName = religionName;
	}

	public String getReligionDesc() {
		return religionDesc;
	}

	public void setReligionDesc(String religionDesc) {
		this.religionDesc = religionDesc;
	}

	public String getReligionStatus() {
		return religionStatus;
	}

	public void setReligionStatus(String religionStatus) {
		this.religionStatus = religionStatus;
	}

	public String getReligionId() {
		return religionId;
	}

	public void setReligionId(String religionId) {
		this.religionId = religionId;
	}

	public ArrayList getReligionList() {
		return religionList;
	}

	public void setReligionList(ArrayList religionList) {
		this.religionList = religionList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
