/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PartyTypeDeedMapForm.java
 * Author      :   Sayed Taha 
 * Description :   Represents the PartyTypeDeedMapForm Action Form.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 *Created Date :   11 aug 2008
 */
package com.wipro.igrs.partytypedeedmap.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class PartyTypeDeedMapForm extends ActionForm{
	private String partyTypeDeedmapID;
	private String deedTypeID;
	private String partyTypeID;
	private String deedTypeName;
	private String partyTypeName;
	private ArrayList partyTypes;
	private ArrayList deedTypes;
	String [] selectedMappingsForDeletion;
	public String[] getSelectedMappingsForDeletion() {
		return selectedMappingsForDeletion;
	}
	public void setSelectedMappingsForDeletion(String[] selectedMappingsForDeletion) {
		this.selectedMappingsForDeletion = selectedMappingsForDeletion;
	}
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
	public ArrayList getPartyTypes() {
		return partyTypes;
	}
	public void setPartyTypes(ArrayList partyTypes) {
		this.partyTypes = partyTypes;
	}
	public ArrayList getDeedTypes() {
		return deedTypes;
	}
	public void setDeedTypes(ArrayList deedTypes) {
		this.deedTypes = deedTypes;
	}
}
