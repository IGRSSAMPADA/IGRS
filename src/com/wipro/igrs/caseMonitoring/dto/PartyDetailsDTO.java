/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.caseMonitoring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

public class PartyDetailsDTO implements Serializable {
	
	
	
	private String partyName;
	private String partyAddress;
	private String partyTypeName;
	private String firstName;
	ArrayList partyList=new ArrayList();
	
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyAddress() {
		return partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

	public ArrayList getPartyList() {
		return partyList;
	}

	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}

	public String getPartyTypeName() {
		return partyTypeName;
	}

	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}


}