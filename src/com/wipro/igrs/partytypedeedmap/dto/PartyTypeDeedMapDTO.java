/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 
 * ===========================================================================
 * File           :   PartyTypeDeedMapDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Sayed Taha
 * Created Date   :   Aug 18, 2008

 * ===========================================================================
 */
package com.wipro.igrs.partytypedeedmap.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class PartyTypeDeedMapDTO implements Serializable{
	private String partyTypeDeedmapID;
	private String deedTypeID;
	private String partyTypeID;
	private String deedTypeName;
	private String partyTypeName;
	public String getPartyTypeDeedmapID() {
		return partyTypeDeedmapID;
	}
	public void setPartyTypeDeedmapID(String partyTypeDeedmapID) {
		this.partyTypeDeedmapID = partyTypeDeedmapID;
	}
	public String getDeedTypeID() {
		return deedTypeID;
	}
	public void setDeedTypeID(String deedTypeID) {
		this.deedTypeID = deedTypeID;
	}
	public String getPartyTypeID() {
		return partyTypeID;
	}
	public void setPartyTypeID(String partyTypeID) {
		this.partyTypeID = partyTypeID;
	}
	public String getDeedTypeName() {
		return deedTypeName;
	}
	public void setDeedTypeName(String deedTypeName) {
		this.deedTypeName = deedTypeName;
	}
	public String getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}
}
