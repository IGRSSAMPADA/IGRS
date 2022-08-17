/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	partyMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class PartyMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String partyName;
	private String partyDesc;
	private String partyStatus;
	private String partyId;

	private ArrayList partyList;

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

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyDesc() {
		return partyDesc;
	}

	public void setPartyDesc(String partyDesc) {
		this.partyDesc = partyDesc;
	}

	public String getPartyStatus() {
		return partyStatus;
	}

	public void setPartyStatus(String partyStatus) {
		this.partyStatus = partyStatus;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public ArrayList getPartyList() {
		return partyList;
	}

	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}

}
