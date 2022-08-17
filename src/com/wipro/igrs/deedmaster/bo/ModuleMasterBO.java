/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterBO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master BO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By        Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		        18th Mar, 2008	 		 
 *  1.0         Sayed Taha(Wipro-egypt)     27th Aug, 2008 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.deedmaster.bo;


import java.util.ArrayList;

import com.wipro.igrs.deedmaster.dao.ModuleMasterDAO;
import com.wipro.igrs.deedmaster.form.ModuleMasterForm;


public class ModuleMasterBO{
	
	ModuleMasterDAO moduleMaster = null;
	
	public ModuleMasterBO(){
		moduleMaster = new ModuleMasterDAO();
	}
	
	public void addModuleDetails(String params[]){
		moduleMaster.addModuleDetails(params);
	}
	
	public void addSubClauseDetails(String params[]){
		moduleMaster.addSubClauseDetails(params);
	}
	
	public ArrayList getModulesList(String listType){
		return moduleMaster.getModulesList(listType);
	}
	
	public void addSubModuleDetails(String params[]){
		moduleMaster.addSubModuleDetails(params);
	}
	
	public void addDeedDetails(String params[]){
		moduleMaster.addDeedDetails(params);
	}
	
	public void addFunctionDetails(String params[]){
		moduleMaster.addFunctionDetails(params);
	}
	
	public ArrayList getModulesDetails(String listType){
		return moduleMaster.getModulesDetails(listType);
	}
	
	public ModuleMasterForm getExemptionDetails(String listType){
		return moduleMaster.getExemptionDetails(listType);
	}
	
	
	public ModuleMasterForm getModuleDetails(String linkParam){
		return moduleMaster.getModuleDetails(linkParam);
	}
	
	public ModuleMasterForm getSubClauseDetails(String linkParam){
		return moduleMaster.getSubClauseDetails(linkParam);
	}
	
	public ModuleMasterForm getDeedTypeDetails(String linkParam){
		return moduleMaster.getDeedTypeDetails(linkParam);
	}
	public ArrayList getDeedNameDetails(){
		return moduleMaster.getDeedNameDetails();
	}
	public ArrayList getDeedIdDetails(String _linkParam){
		return moduleMaster.getDeedIdDetails(_linkParam);
	}
	public ModuleMasterForm getInstrumentDetails(String linkParam){
		return moduleMaster.getInstrumentDetails(linkParam);
	}
	
	public String getTrueOrFalse(boolean boo){
		return moduleMaster.getTrueOrFalse(boo);
	}
	
	public boolean getTrueOrFalse(String temp){
		return moduleMaster.getTrueOrFalse(temp);
	}
	public String getYOrN(String _tmp){
		return moduleMaster.getYOrN(_tmp);
	}
	public ModuleMasterForm getSubModuleDetails(String linkParam){
		return moduleMaster.getSubModuleDetails(linkParam);
	}
	
	public ModuleMasterForm getFunctionDetails(String linkParam){
		return moduleMaster.getFunctionDetails(linkParam);
	}
	
	public void updateModuleDetails(String params[]){
		moduleMaster.updateModuleDetails(params);
	}
	
	public void updateSubClauseDetails(String params[]){
		moduleMaster.updateSubClauseDetails(params);
	}
	
	public void updatedDeedTypeDetails(String params[]){
		moduleMaster.updatedDeedTypeDetails(params);
	}
	public void updateInstrumentDetails(String params[]){
		moduleMaster.updateInstrumentDetails(params);
	}
	
	public void updateExemptionDetails(String params[]){
		moduleMaster.updateExemptionDetails(params);
	}
	
	public void updateSubModuleDetails(String params[]){
		moduleMaster.updateSubModuleDetails(params);
	}
	
	public void updateFunctionDetails(String params[]){
		moduleMaster.updateFunctionDetails(params);
	}
	
	public void addInstrumentDetails(String params[]){
		moduleMaster.addInstrumentDetails(params);
	}
	
	public void addExemptionDetails(String params[]){
		moduleMaster.addExemptionDetails(params);
	}

	public boolean isDeepTypeExists(String name) {
		return moduleMaster.isDeepTypeExists(name);
	}

	public String getDeedTypeByName(String string) {
		return moduleMaster.getDeedTypeByName(string);
	}
	
}