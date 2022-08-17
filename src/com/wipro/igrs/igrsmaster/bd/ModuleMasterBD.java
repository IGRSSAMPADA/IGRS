/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterBD.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master BD Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.igrsmaster.bd;


import java.util.ArrayList;

import com.wipro.igrs.igrsmaster.dao.ModuleMasterDAO;
import com.wipro.igrs.igrsmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.igrsmaster.form.ModuleMasterForm;


public class ModuleMasterBD{
	
	ModuleMasterDAO moduleMaster = null;
	
	public ModuleMasterBD(){
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
	
	public ArrayList getModulesDetails(String listType){
		return moduleMaster.getModulesDetails(listType);
	}
	
	public ModuleMasterForm getExemptionDetails(String listType){
		return moduleMaster.getExemptionDetails(listType);
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
	
	public void addSubModuleDetails(String params[]){
		moduleMaster.addSubModuleDetails(params);
	}
	
	public void addDeedDetails(String params[]){
		moduleMaster.addDeedDetails(params);
	}
	
	public void addFunctionDetails(String params[]){
		moduleMaster.addFunctionDetails(params);
	}
	
	public ModuleMasterForm getModuleDetails(String linkParam){
		return moduleMaster.getModuleDetails(linkParam);
	}
	public ModuleMasterForm getSubClauseDetails(String linkParam, String lang){
		return moduleMaster.getSubClauseDetails(linkParam,lang);
	}
	public ArrayList getSubClauseViewDetails(String linkParam, String lang, String propId){
		return moduleMaster.getSubClauseViewDetails(linkParam,lang,propId);
	}
	
	//added by SHRUTI
	public ModuleMasterDTO getComputationDetails(String sbclauseid)
	{
		return moduleMaster.getComputationDetails(sbclauseid);
	}
	//end of code
	
	
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
	
	public void updateSubModuleDetails(String params[]){
		moduleMaster.updateSubModuleDetails(params);
	}
	
	public void updateFunctionDetails(String params[]){
		moduleMaster.updateFunctionDetails(params);
	}
	
	public void updatedDeedTypeDetails(String params[]){
		moduleMaster.updatedDeedTypeDetails(params);
	}
	
	public void addInstrumentDetails(String params[]){
		moduleMaster.addInstrumentDetails(params);
	}
	
	public void updateInstrumentDetails(String params[]){
		moduleMaster.updateInstrumentDetails(params);
	}
	
	public void updateExemptionDetails(String params[]){
		moduleMaster.updateExemptionDetails(params);
	}
	
	public void addExemptionDetails(String params[]){
		moduleMaster.addExemptionDetails(params);
	}
	
	//added by shruti
	public ArrayList getOperatorList()
	{
		return moduleMaster.getOperatorList();
	}
	public ArrayList getAddFactorList()
	{
		return moduleMaster.getAddFactorList();
	}
	
	public ArrayList getPropertyTypeList(String language)
	{
		return moduleMaster.getPropertyTypeList(language);
	}
	//added by shruti
	public void addSubClauseDetailsNew(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
		moduleMaster.addSubClauseDetailsNew(params,dto,list_propids);
	}
	public void addSubClauseDetailsNew1(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
		moduleMaster.addSubClauseDetailsNew1(params,dto,list_propids);
	}
	
	public ArrayList getPropTypeL1List(String prop_Type_Id,String language)
	{
		return moduleMaster.getPropTypeL1List(prop_Type_Id,language);
	}
	public ArrayList getPropTypeL2List(String prop_Type_L1_Id, String language)
	{
	  return moduleMaster.getPropTypeL2List(prop_Type_L1_Id,language);
	}
	public ArrayList getSubClausePropDetails(String prop_Type_Id, String language)
	{
	return moduleMaster.getSubClausePropDisplay(prop_Type_Id,language);
	}
	public ArrayList getSubClausePropDisplayNew(String prop_Type_Id, String language)
	{
		return moduleMaster.getSubClausePropDisplayNew(prop_Type_Id,language);
	}
	public boolean addSubClauseDetails(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
		return moduleMaster.addSubClauseDetails(params, dto, list_propids);
	}
	
	public ArrayList getSubclaueList(){
		return moduleMaster.getSubclaueList();
	}
	
	public String getPropDetails(String subId){
		return moduleMaster.getPropDetails(subId);
	}
	public boolean deleteSubclause(String subId) throws Exception{
		return moduleMaster.deleteSubclause(subId);
	}
	
	public boolean updateSubClauseDetails(String params[],ModuleMasterForm form,ArrayList list_propids) throws Exception{
		return moduleMaster.updateSubClauseDetails(params, form, list_propids);
		
	}
}