/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ModuleMasterDAO.java
 * Author      :   Imran Shaik
 * Description :   Represents the Master DAO Class for Registration Initiation.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Imran Shaik		     18th Mar, 2008	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.igrsmaster.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.security.auth.callback.LanguageCallback;

import org.apache.log4j.Logger;

import com.wipro.igrs.UserRegistration.action.UserRegistrationAction;
import com.wipro.igrs.db.DBUtility;
import com.wipro.igrs.igrsmaster.constant.SubClauseConstant;
import com.wipro.igrs.igrsmaster.dto.ModuleMasterDTO;
import com.wipro.igrs.igrsmaster.form.ModuleMasterForm;
import com.wipro.igrs.igrsmaster.sql.CommonSQL;
import com.wipro.igrs.sp.dto.ServiceProviderDTO;


public class ModuleMasterDAO{
	
	DBUtility dbUtility = null;
	Connection con = null;
	Statement st = null;
	
	private Logger logger = 
		(Logger) Logger.getLogger(ModuleMasterDAO.class);
	/**
	 * Inserting Module Details 
	 * @param params
	 */
	public void addModuleDetails(String params[]){
		String SQL=CommonSQL.INSERT_MODULE_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * returns the selected list of records based on listType submodule/function for drop box
	 * @param listType
	 * @return ArrayList
	 */
	public ArrayList getModulesList(String listType){
		String SQL = null;
		if(listType.equalsIgnoreCase("submodule")){
			SQL=CommonSQL.SELECT_MODULES_LIST;
		}
		if(listType.equalsIgnoreCase("function")){
			SQL=CommonSQL.SELECT_SUB_MODULES_LIST;
		}
		if(listType.equalsIgnoreCase("instrument")){
			SQL=CommonSQL.SELECT_DEEDS_LIST;
		}
		if(listType.equalsIgnoreCase("exemption")){
			SQL=CommonSQL.SELECT_INSTRUMENTS_LIST;
		}
		
		ArrayList mainlist = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			mainlist = new ArrayList();
			ArrayList list1= dbUtility.executeQuery(SQL);
            for (int i=0;i<list1.size();i++){ 
             list = (ArrayList)list1.get(i);
             ModuleMasterDTO dto = new ModuleMasterDTO();
             dto.setId((String)list.get(0));
             dto.setName((String)list.get(1));
             mainlist.add(dto);
             }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return mainlist;
	}
	/**
	 * Inserting sub module details
	 * @param params
	 */
	public void addSubModuleDetails(String params[]){
		String SQL=CommonSQL.INSERT_SUB_MODULE_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * Inserting function details
	 * @param params
	 */
	public void addFunctionDetails(String params[]){
		String SQL=CommonSQL.INSERT_FUNCTION_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * Inserting deed type details
	 * @param params
	 */
	public void addDeedDetails(String params[]){
		String SQL=CommonSQL.INSERT_DEED_TYPE_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
		    dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * returns the selected list of records based on listType moduleList/submoduleList/functionList/clauseList/deedList
	 * @param listType
	 * @return ArrayList
	 */
	public ArrayList getModulesDetails(String listType){
		String SQL = null;
		if(listType.equalsIgnoreCase("moduleList")){
			SQL=CommonSQL.SELECT_MODULE_MASTER_LIST;
		}if(listType.equalsIgnoreCase("submoduleList")){
			SQL=CommonSQL.SELECT_SUB_MODULE_MASTER_LIST;
		}if(listType.equalsIgnoreCase("functionList")){
			SQL=CommonSQL.SELECT_FUNCTION_MASTER_LIST;
		}
		if(listType.equalsIgnoreCase("clauseList")){
			//SQL=CommonSQL.SELECT_SUB_CLAUSE_MASTER_LIST_NEW;
			SQL=CommonSQL.SELECT_SUCLAUSE_DETLS_FOR_DASHBOARD;
		}
		if(listType.equalsIgnoreCase("deedlist")){
			SQL=CommonSQL.SELECT_DEED_TYPE_MASTER_LIST;
		}
		if(listType.equalsIgnoreCase("instrumentList")){
			SQL=CommonSQL.SELECT_INSTRUMENT_MASTER_LIST;
		}
		if(listType.equalsIgnoreCase("exemptionList")){
			SQL=CommonSQL.SELECT_EXEMPTION_MASTER_LIST;
		}
		if(listType.equalsIgnoreCase("test")){
			SQL=CommonSQL.SELECT_EXEMPTION_MASTER_LIST;
		}
		if(listType.equalsIgnoreCase("deedview")){
			SQL=CommonSQL.SELECT_DEED_TYPE_NAME;
		}
		ArrayList mainlist = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			mainlist = new ArrayList();
			ArrayList list1= dbUtility.executeQuery(SQL);
            for (int i=0;i<list1.size();i++){ 
             list = (ArrayList)list1.get(i);  
             ModuleMasterDTO dto = new ModuleMasterDTO();
             dto.setId((String)list.get(0));
             dto.setName((String)list.get(1));
             dto.setDescription((String)list.get(2));
             mainlist.add(dto);
             }
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return mainlist;
	}
	
	/**
	 * returns the selected module details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getModuleDetails(String linkParam){
		String SQL = CommonSQL.SELECT_MODULE;
			
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
             form = new ModuleMasterForm();
             form.setId((String)list.get(0));
             form.setName((String)list.get(1));
             form.setStatus(getTrueOrFalse((String)list.get(3)));
             form.setDescription((String)list.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	
	/**
	 * returns Y/N depends upon the boolean value
	 * @param boo
	 * @return String
	 */
	public String getTrueOrFalse(boolean boo){
		String temp = null;
		if(boo){
			temp = "A";
		}else{
			temp = "D";
		}
		return temp;
	}
	/**
	 * returns Y/N depends upon the boolean value
	 * @param boo
	 * @return String
	 */
	public String getYOrN(String _tmp){
		
		if(_tmp!=null && _tmp.equalsIgnoreCase("YES")){
			_tmp = "Y";
		}else{
			_tmp = "N";
		}
		return _tmp;
	}
	/**
	 * returns true/false depends upon the Y/N value
	 * @param temp
	 * @return boolean
	 */
	public boolean getTrueOrFalse(String temp){
		boolean boo;
		if(temp.equalsIgnoreCase("A")){
			boo=true;;
		}else{
			boo=false;
		}
		return boo;
	}
	/**
	 * returns true/false depends upon the Y/N value
	 * @param temp
	 * @return boolean
	 */
	public String getYesOrNo(String temp){
		String boo;
		if(temp==null)
			temp="";
		if(temp.equalsIgnoreCase("Y")){
			boo="YES";;
		}else{
			boo="NO";
		}
		return boo;
	}
	/**
	 * returns the selected sub module details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getSubModuleDetails(String linkParam){
		String SQL = CommonSQL.SELECT_SUB_MODULE;
			
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
              form = new ModuleMasterForm();
              form.setId((String)list.get(0));
              form.setName((String)list.get(1));
              form.setStatus(getTrueOrFalse((String)list.get(3)));
              form.setModulename((String)list.get(4));
              form.setDescription((String)list.get(2));
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	/**
	 * returns the selected function details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getFunctionDetails(String linkParam){
		String SQL = CommonSQL.SELECT_FUNCTION;
			
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
              form = new ModuleMasterForm();
              form.setId((String)list.get(0));
              form.setName((String)list.get(1));
              form.setStatus(getTrueOrFalse((String)list.get(3)));
              form.setModulename((String)list.get(4));
              form.setPayment(getTrueOrFalse((String)list.get(5)));
              form.setIntimation(getTrueOrFalse((String)list.get(6)));
              form.setDescription((String)list.get(2));


		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	/**
	 * updates the module details
	 * @param params
	 */
	public void updateModuleDetails(String params[]){
		
		String SQL=CommonSQL.UPDATE_MODULE;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * updates the sub module details
	 * @param params
	 */
	public void updateSubModuleDetails(String params[]){
		String SQL=CommonSQL.UPDATE_SUB_MODULE;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * updates the function details
	 * @param params
	 */
	public void updateFunctionDetails(String params[]){
		String SQL=CommonSQL.UPDATE_FUNCTION;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * Inserting Sub Clause Details
	 * @param params
	 */
	public void addSubClauseDetails(String params[]){
		String SQL=CommonSQL.INSERT_SUB_CLAUSE_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * returns the selected sub clause details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getSubClauseDetails(String linkParam, String lang){
		//String SQL = CommonSQL.SELECT_SUB_CLAUSE;
		//String SQL=CommonSQL.SELECT_SUB_CLAUSE_NEW1;
		String SQL = CommonSQL.GET_SUBCLAUSE_COMPLETE_DETLS;
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
             form = new ModuleMasterForm();
             form.setId((String)list.get(0));
             form.setName((String)list.get(1));
             form.setDescription((String)list.get(2));
             form.setDescriptionHindi((String)list.get(3));
             form.setStatus(getTrueOrFalse((String)list.get(4)));
             form.setOperatorName((String)list.get(5));
             form.setOperandValue((String)list.get(6));
             form.setCompBasedOn((String)list.get(7));
             form.setApplicableBaseValue((String)list.get(8));
             form.setOperatorId((String)list.get(9));
             /*if(lang.equalsIgnoreCase("en"))
             {
            	 form.setMapAll((String)list.get(6));
            	 form.setUserDefinedField((String)list.get(7));
                 form.setNormalVal((String)list.get(8));
                 form.setAreaBasedDiv((String)list.get(11));
             }
             else
             {
            	 String map = (String)list.get(6);
            	 String userDe = (String)list.get(7);
            	 String normalVal =  (String)list.get(8);
            	 String areaBased = (String)list.get(11);
            	 if(map.equalsIgnoreCase("Y"))
            	 {
            		 form.setMapAll("हां");
            	 }
            	 else
            	 {
            		 form.setMapAll("नहीं");
            	 }
            	 if(userDe.equalsIgnoreCase("Y"))
            	 {
            		 form.setUserDefinedField("हां");
            	 }
            	 else
            	 {
            		 form.setUserDefinedField("नहीं");
            	 }
            	 if(normalVal.equalsIgnoreCase("Y"))
            	 {
            		 form.setNormalVal("हां");
            	 }
            	 else
            	 {
            		 form.setNormalVal("नहीं");
            	 }
            	 if(areaBased.equalsIgnoreCase("Y"))
            	 {
            		 form.setAreaBasedDiv("हां");
            	 }
            	 else
            	 {
            		 form.setAreaBasedDiv("नहीं");
            	 }
            	
             }
             
             
             form.setAddFactorArea((String)list.get(9));
             form.setAddFactorOprndValue((String)list.get(10));*/
             
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}

	public ArrayList getSubClauseViewDetails(String linkParam, String lang, String propId){
		//String SQL = CommonSQL.SELECT_SUB_CLAUSE;
		ArrayList list=null;
		ArrayList list1=null;
		ArrayList list2=new ArrayList();
		//String SQL=CommonSQL.VIEWPROPFORSUBCLAUSE;
		String SQL = CommonSQL.GET_SUBCLAUSE_PROP_DETLS;
		String arr[]= new String[1];
		arr[0]=linkParam;
		//ModuleMasterForm form = null;
		ModuleMasterDTO dto=null;
		ArrayList completePropDetails = getSubClausePropDisplayNew(propId,lang);
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			list= dbUtility.executeQuery(arr);
             //form = new ModuleMasterForm();
            
             for(int i=0;i<list.size();i++)
             {
             dto=new ModuleMasterDTO();
             list1 = (ArrayList)list.get(i);  
             /*if(lang.equals("en"))
             {
            	 dto.setPropertyTypeName((String)list1.get(0));
                 dto.setSubPropTypeName((String)list1.get(1));
                 dto.setPropTypeL1Name((String)list1.get(2));
             }
             else
             {
            	 dto.setPropertyTypeName((String)list1.get(3));
                 dto.setSubPropTypeName((String)list1.get(4));
                 dto.setPropTypeL1Name((String)list1.get(5));
             }
             dto.setHdnchkboxval((String)list1.get(6)+"~"+(String)list1.get(7)+"~"+(String)list1.get(8));*/
             dto.setSubPropTypeId((String)list1.get(7));
             dto.setPropTypeL1Id((String)list1.get(8));
             list2.add(dto);
             dbUtility.commit();
             }
             
             for(int j = 0 ; j < completePropDetails.size();j++)
             {
            	 logger.debug("comparinggggggggggggggggggggggggggg");
            	 ModuleMasterDTO mDto = (ModuleMasterDTO)completePropDetails.get(j);
            	 //logger.debug("************"+propSubTypeId);
            	 //logger.debug("************"+propL1Id);
            	
            	 for(int k= 0 ; k < list2.size(); k++)
            	 {
            		 ModuleMasterDTO ddto = (ModuleMasterDTO)list2.get(k);
            		 
            		 String propSubTypeId = ddto.getSubPropTypeId();
                     String propL1Id = ddto.getPropTypeL1Id();
                     if(mDto.getSubPropTypeId().equals(propSubTypeId) && mDto.getPropTypeL1Id().equals(propL1Id))
                	 {
                		 logger.debug("matchhhhhhhhhhhhhhhh");
                		 mDto.setStatusChk("Y");
                		 break;
                	 }
                		
                	 
            	 }
            	 
            	 
             }
           //form.setViewPropDetails(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try
			{
			dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				System.out.println("in DAO"+e);
			}
		}
		
		return completePropDetails;
	}
	
	//added for computation details-SHRUTI
	public ModuleMasterDTO getComputationDetails(String sbclauseid){
		
		String SQL=CommonSQL.SELECT_COMPUTATION_DETAILS;
		String arr[]= new String[1];
		arr[0]=sbclauseid;
		//ModuleMasterForm form = null;
		ModuleMasterDTO compdto=null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);
             compdto=new ModuleMasterDTO();
            // form = new ModuleMasterForm();
             
             compdto.setOperatorName((String)list.get(0));
             compdto.setOperandValue((String)list.get(1));
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return compdto;
	}
	/**
	 * UPDATE SUB CLAUSE DETAILS
	 * @param params
	 */
	public void updateSubClauseDetails(String params[]){
		//String SQL=CommonSQL.UPDATE_SUB_CLAUSE;
		String SQL=CommonSQL.UPDATE_SUB_CLAUSE_NEW;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
		
	/**
	 * returns the selected deed type details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ArrayList getDeedNameDetails(){
		String SQL = CommonSQL.SELECT_DEED_TYPE_NAME;	
//DEED_TYPE_ID, DEED_TYPE_NAME,DEED_STATUS 		
		
		ArrayList retList=new ArrayList(); 
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createStatement();
			ArrayList list1= dbUtility.executeQuery(SQL);
			if(list1.size()>0){
			 ArrayList list = (ArrayList)list1.get(0);  
			 ModuleMasterForm form = new ModuleMasterForm();
             form.setId((String)list.get(0));
             form.setName((String)list.get(1));     
             retList.add(form);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return retList;
	}
	
	
	/**
	 * returns the selected deed type details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getDeedTypeDetails(String linkParam){
		String SQL = CommonSQL.SELECT_DEED_TYPE;
		
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
             form = new ModuleMasterForm();
             form.setId((String)list.get(0));
             form.setName((String)list.get(1));
             form.setStatus(getTrueOrFalse((String)list.get(3)));
             form.setPayment(getTrueOrFalse((String)list.get(4)));
             form.setDescription((String)list.get(2));
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	/**
	 * returns the selected deed type details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ArrayList getDeedIdDetails(String linkParam){
		String SQL = CommonSQL.SELECT_DEED_TYPE_SEARCH;
	
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList retList = new ArrayList();
		
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
			if(list1.size()>0){		
			for(int i=0;i<list1.size();i++){
// DEED_TYPE_ID, DEED_TYPE_NAME, DEED_TYPE_DESCRIPTION,DEED_STATUS, CREATED_BY, CREATED_DATE, 
// DEED_CATEGORY,PIN_REQUIRED, PROPERTY_RELATED_DEED, ROR_REQUIRE	
			 ArrayList list = (ArrayList)list1.get(i);  
	             form = new ModuleMasterForm();
	             form.setId((String)list.get(0));
	             form.setName((String)list.get(1));
	             form.setDescription((String)list.get(2));
	             form.setDeedStatus(getYesOrNo((String)list.get(3)));
	             form.setSubname((String)list.get(4));
	             form.setCreatedDate((String)list.get(5));	             
	             String Category=(String)list.get(6);
	             if(Category!=null){
	             if(Category.equalsIgnoreCase("M")){	            	 
	            	 Category="Mandatory";	            	 
	             }else if(Category.equalsIgnoreCase("O")){	            	 
	            	 Category="Optional";
	             }else if(Category.equalsIgnoreCase("")){	            	 
	            	 Category="Data Not Available";
	             }}else{
	            	 Category="Data Not Available";
	             }
	             form.setDeedCategory(Category);             
	             form.setDeedreq(getYesOrNo((String)list.get(7)));
	             form.setDeedlinkprop(getYesOrNo((String)list.get(8)));
	             form.setDeedRor(getYesOrNo((String)list.get(9)));
             retList.add(form);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return retList;
	}
	/**
	 * UPDATE DEED TYPE DETAILS
	 * @param params
	 */
	public void updatedDeedTypeDetails(String params[]){
		String SQL=CommonSQL.UPDATE_DEED_TYPE;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * Inserting Instrument Details
	 * @param params
	 */
	public void addInstrumentDetails(String params[]){
		String SQL=CommonSQL.INSERT_INSTRUMENT_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * returns the selected Instrument details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getInstrumentDetails(String linkParam){
		String SQL =CommonSQL.SELECT_INSTRUMENT;
			
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
              form = new ModuleMasterForm();
              form.setId((String)list.get(0));
              form.setName((String)list.get(1));
              form.setStatus(getTrueOrFalse((String)list.get(3)));
              form.setModulename((String)list.get(4));
              form.setDescription((String)list.get(2));

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	
	/**
	 * updates the Instrument details
	 * @param params
	 */
	public void updateInstrumentDetails(String params[]){
		String SQL=CommonSQL.UPDATE_INSTRUMENT;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeUpdate(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
	/**
	 * INSERTS the Exemption details
	 * @param params
	 */
	public void addExemptionDetails(String params[]){
		String SQL=CommonSQL.INSERT_EXEMPTION_MASTER;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	/**
	 * returns the selected Exemption details
	 * @param linkParam
	 * @return ModuleMasterForm
	 */
	public ModuleMasterForm getExemptionDetails(String linkParam){
		String SQL =CommonSQL.SELECT_EXEMPTION;
		
		String arr[]= new String[1];
		arr[0]=linkParam;
		ModuleMasterForm form = null;
		ArrayList list = null;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			ArrayList list1= dbUtility.executeQuery(arr);
             list = (ArrayList)list1.get(0);  
              form = new ModuleMasterForm();
              form.setId((String)list.get(0));
              form.setName((String)list.get(1));
              form.setStatus(getTrueOrFalse((String)list.get(5)));
              form.setSubname((String)list.get(3));
              if(list.get(4)!=null)
              form.setModulename((String)list.get(4));
              else
            	  form.setModulename("");
              form.setDescription((String)list.get(2));

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return form;
	}
	/**
	 * updates the Exemption Details
	 * @param params
	 */
	
	public void updateExemptionDetails(String params[]){
		String SQL=CommonSQL.UPDATE_EXEMPTION;
		try {
			dbUtility = new DBUtility();
			dbUtility.createPreparedStatement(SQL);
			dbUtility.executeQry(params);
			dbUtility.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	
//ADDED BY SHRUTI
	
	
	
	public ArrayList getOperatorList() 
	{
		String SQL=CommonSQL.SELECTOPERATORS;
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList operatorList=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
			dbUtility.createStatement();
			
			list1=dbUtility.executeQuery(SQL); 
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setOperatorId((String)list.get(0));
			dto.setOperatorName((String)list.get(1));
			operatorList.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return operatorList;
		
	}

	public ArrayList getAddFactorList() 
	{
		String SQL=CommonSQL.SELECTOPERATORS;
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList addFactorList=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
			dbUtility.createStatement();
			
			list1=dbUtility.executeQuery(SQL); 
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setAddFactorId((String)list.get(0));
			dto.setAddFactorName((String)list.get(1));
			addFactorList.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return addFactorList;
		
	}
	
	public ArrayList getPropertyTypeList(String language) 
	{
		String SQL=CommonSQL.SELECTPROPERTY;
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList propertyTypeList=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
			dbUtility.createStatement();
			list1=dbUtility.executeQuery(SQL); 
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setPropertyTypeId((String)list.get(0));
			if(language.equalsIgnoreCase("en"))
			dto.setPropertyTypeName((String)list.get(1)+" / "+ (String)list.get(2));
			else
			dto.setPropertyTypeName((String)list.get(1)+" / "+ (String)list.get(2));
			propertyTypeList.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return propertyTypeList;
		
	}
	
	public ArrayList getPropTypeL1List(String prop_Type_Id,String language)
	{
		String[] proptype={prop_Type_Id};
		
		//String[] proptype={ dto.getPropertyTypeId()};
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList propTypeL1List=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.SELECTPROPTYPEL1);
			list1=dbUtility.executeQuery(proptype);
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setPropTypeL1Id((String)list.get(0));
			if(language.equalsIgnoreCase("en"))
			dto.setPropTypeL1Name((String)list.get(1));
			else
				dto.setPropTypeL1Name((String)list.get(2));	
			propTypeL1List.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return propTypeL1List;
		
		
	}
	
	public ArrayList getPropTypeL2List(String prop_Type_L1_Id, String language)
	{
		String[] proptypeL1={prop_Type_L1_Id};
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList propTypeL2List=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
			dbUtility.createPreparedStatement(CommonSQL.SELECTPROPTYPEL2);
			list1=dbUtility.executeQuery(proptypeL1); 
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setPropTypeL2Id((String)list.get(0));
			if(language.equalsIgnoreCase("en"))
			dto.setPropTypeL2Name((String)list.get(1));
			else 
				dto.setPropTypeL2Name((String)list.get(2));		
			propTypeL2List.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return propTypeL2List;
		
		
	}
	
	public ArrayList getSubClausePropDisplay(String prop_Type_Id, String language)
	{
		String[] proptype={prop_Type_Id};
		ArrayList list=new ArrayList();
		ArrayList list1=new ArrayList();
		ArrayList subClausePropList=new ArrayList();
		try
		{
			dbUtility=new DBUtility();
			if(language.equalsIgnoreCase("en"))
			dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSEPROPDISPLAY);
			else
				dbUtility.createPreparedStatement(CommonSQL.SUBCLAUSEPROPDISPLAY_H);
			list1=dbUtility.executeQuery(proptype); 
			for(int i=0;i<list1.size();i++)
			{
			list=(ArrayList)list1.get(i);
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setPropertyTypeId((String)list.get(0));
			dto.setPropertyTypeName((String)list.get(1));
			dto.setPropTypeL1Id((String)list.get(2));
			dto.setPropTypeL1Name((String)list.get(3));
			dto.setPropTypeL2Id((String)list.get(4));
			dto.setPropTypeL2Name((String)list.get(5));
			dto.setHdnpropids((String)list.get(0)+"~"+(String)list.get(2)+"~"+(String)list.get(4));
			subClausePropList.add(dto);
			dbUtility.commit();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
		return subClausePropList;
		
	}
	
public void addSubClauseDetailsNew(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
		
		try {
			dbUtility=new DBUtility();
			dbUtility.setAutoCommit(false);
			ArrayList list=null;
			ArrayList list1=null;
			ArrayList list2=null;
			ArrayList list3=null;
			ArrayList list4=null;
			ArrayList list5=null;
			
			
			dbUtility.createStatement();
			list = dbUtility.executeQuery(CommonSQL.SELECTCOMPUTATIONID);
			list.trimToSize();
			for(int i = 0;i<list.size();i++)
			{list2 = (ArrayList)list.get(i);}
			String comp_id=list2.get(0).toString();
		
			dbUtility.createStatement();
			list1 = dbUtility.executeQuery(CommonSQL.SELECTSUBCLAUSEID);
			list1.trimToSize();
			for(int i=0;i<list1.size();i++)
			{list3=(ArrayList)list1.get(i);}
			String sub_clause_id=list3.get(0).toString();
			
			/*dbUtility.createStatement();
			list4 = dbUtility.executeQuery(CommonSQL.SELECTSUBCLAUSEMAPPINGID);
			list4.trimToSize();
			for(int i = 0;i<list4.size();i++)
			{list5 = (ArrayList)list4.get(i);}
			String mapping_id=list5.get(0).toString();*/
			
			String[] params1={
					sub_clause_id,
					dto.getName(),
					dto.getDescription(),
					comp_id,
					"Y",
					params[0].toString(),
					params[1].toString(),
					dto.getUserDefinedField(),
					dto.getNormalVal(),
					dto.getPropTypeFlag()
			};
			
			dbUtility.createPreparedStatement(CommonSQL.INSERT_SUB_CLAUSE_MASTER_TEMP);
			dbUtility.executeUpdate(params1);
			
				String[]params2={
					comp_id,
					dto.getOperatorId(),
					dto.getOperandValue(),
					dto.getAddFactorArea(),
					dto.getAddFactorOprndValue(),
					dto.getPropTypeL1Id(),
					dto.getPropTypeL2Id(),
					dto.getPropTypeId(),
					sub_clause_id,
					dto.getAreaBasedDiv()
			};
			dbUtility.createPreparedStatement(CommonSQL.INSERT_COMPUTATION_LOGIC_MASTER);
			dbUtility.executeUpdate(params2);
			
				for(int i=0;i<list_propids.size();i++)
				{dto=(ModuleMasterDTO)list_propids.get(i);	
					if(dto.getChkdPropTypeL2Id().equalsIgnoreCase("null"))
					{
					String[] params3={
							getSubClausePropMappingId(),
							sub_clause_id,
							dto.getChkdPropTypeId(),
							dto.getChkdPropTypeL1Id(),
							
						};

					dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETAILS1);
					dbUtility.executeUpdate(params3);
					}
					else
					{
						String[] params3={
								getSubClausePropMappingId(),
											sub_clause_id,
											dto.getChkdPropTypeId(),
											dto.getChkdPropTypeL1Id(),
											dto.getChkdPropTypeL2Id()
										};
				
					dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETAILS);
					dbUtility.executeUpdate(params3);
					}
				}
		
			String[] params4={
					//getSubClauseDistrictMappingId(),
					sub_clause_id
			};
			
			dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_DISTRICT_MAPPING);
			dbUtility.executeUpdate(params4);
			dbUtility.commit();
			
		} catch (Exception e) {
			dbUtility.rollback();
			//dbUtility.closeConnection();
			e.printStackTrace();
		}
		finally
		{
			try
			{
				dbUtility.closeConnection();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				
		}
	}
	


public void addSubClauseDetailsNew1(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
	
	try {
		dbUtility=new DBUtility();
		dbUtility.setAutoCommit(false);
		ArrayList list=null;
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;
		ArrayList list5=null;
		
		
		
		dbUtility.createStatement();
		list = dbUtility.executeQuery(CommonSQL.SELECTCOMPUTATIONID);
		list.trimToSize();
		
		for(int i = 0;i<list.size();i++)
		{list2 = (ArrayList)list.get(i);}
		String comp_id=list2.get(0).toString();
	
		dbUtility.createStatement();
		list1 = dbUtility.executeQuery(CommonSQL.SELECTSUBCLAUSEID);
		list1.trimToSize();
		for(int i=0;i<list1.size();i++)
		{list3=(ArrayList)list1.get(i);}
		String sub_clause_id=list3.get(0).toString();
		
		dbUtility.createStatement();
		list4 = dbUtility.executeQuery(CommonSQL.SELECTSUBCLAUSEMAPPINGID);
		list4.trimToSize();
		for(int i = 0;i<list4.size();i++)
		{list5 = (ArrayList)list4.get(i);}
		String mapping_id=list5.get(0).toString();
		
		String[] params1={
				sub_clause_id,
				dto.getName(),
				dto.getDescription(),
				comp_id,
				"N",
				params[0].toString(),
				params[1].toString(),
				dto.getUserDefinedField(),
				dto.getNormalVal(),
				dto.getPropTypeFlag()
		};
		
		dbUtility.createPreparedStatement(CommonSQL.INSERT_SUB_CLAUSE_MASTER_TEMP);
		dbUtility.executeUpdate(params1);
		
		
		String[]params2={
				comp_id,
				dto.getOperatorId(),
				dto.getOperandValue(),
				dto.getAddFactorArea(),
				dto.getAddFactorOprndValue(),
				dto.getPropTypeL1Id(),
				dto.getPropTypeL2Id(),
				dto.getPropTypeId(),
				sub_clause_id,
				dto.getAreaBasedDiv()
		};
	
		dbUtility.createPreparedStatement(CommonSQL.INSERT_COMPUTATION_LOGIC_MASTER);
		dbUtility.executeUpdate(params2);
		
		for(int i=0;i<list_propids.size();i++)
		{dto=(ModuleMasterDTO)list_propids.get(i);	
			if(dto.getChkdPropTypeL2Id().equalsIgnoreCase("null"))
			{
			String[] params3={
					getSubClausePropMappingId(),
					sub_clause_id,
					dto.getChkdPropTypeId(),
					dto.getChkdPropTypeL1Id(),
					
				};

			dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETAILS1);
			dbUtility.executeUpdate(params3);
			}
			else
			{
				String[] params3={
						getSubClausePropMappingId(),
									sub_clause_id,
									dto.getChkdPropTypeId(),
									dto.getChkdPropTypeL1Id(),
									dto.getChkdPropTypeL2Id()
								};
		
			dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETAILS);
			dbUtility.executeUpdate(params3);
			}
			dbUtility.commit();
		}

		//end of code
		
	} catch (Exception e) {
		dbUtility.rollback();
		//dbUtility.closeConnection();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
}


private String getSubClausePropMappingId() throws Exception {
	String subclausepropmappingid = "";
	try {
		dbUtility.createStatement();
		subclausepropmappingid = dbUtility
				.executeQry(CommonSQL.SELECTSUBCLAUSEPROPMAPPINGID);
	} catch (Exception exception) {
		dbUtility.rollback();
		System.out.println("Exception in getSPusers-SPDAo" + exception);
	}
	return subclausepropmappingid;
}

/*private String getSubClauseDistrictMappingId() throws Exception {
	String subclausedistrictmappingid = "";
	try {
		dbUtility.createStatement();
		subclausedistrictmappingid = dbUtility
				.executeQry(CommonSQL.SELECTSUBCLAUSEDISTRICTMAPPINGID);
	} catch (Exception exception) {
		System.out.println("Exception in getSPusers-SPDAo" + exception);
	}
	return subclausedistrictmappingid;
}*/


// added by simran
public ArrayList getSubClausePropDisplayNew(String prop_Type_Id, String language)
{
	String[] proptype={prop_Type_Id};
	ArrayList list=new ArrayList();
	ArrayList list1=new ArrayList();
	ArrayList list2=new ArrayList();
	ArrayList list3=new ArrayList();
	ArrayList subClausePropList=new ArrayList();
	try
	{
		dbUtility=new DBUtility();
		
		dbUtility.createPreparedStatement(CommonSQL.GET_PROP_SUBTYPE_DETAILS);
		
		list1=dbUtility.executeQuery(proptype); 
		for(int i=0;i<list1.size();i++)
		{
		list=(ArrayList)list1.get(i);
		
		
		String subPropId = (String)list.get(3);
		//dto.setPropTypeL1Id((String)list.get(2));
		//dto.setPropTypeL1Name((String)list.get(3));
		//dto.setPropTypeL2Id((String)list.get(4));
		//dto.setPropTypeL2Name((String)list.get(5));
		if(subPropId.equalsIgnoreCase(SubClauseConstant.MULTISTOREY_SUB_TYPE_ID))
		{
			dbUtility.createPreparedStatement(CommonSQL.GET_PROP_TYPE_L1_DETAILS);
			String subIdArr[] = {subPropId};
			list2=dbUtility.executeQuery(subIdArr);
			for(int j=0;j<list2.size();j++)
			{
				ModuleMasterDTO dto=new ModuleMasterDTO();
				list3=(ArrayList)list2.get(j);
				dto.setPropertyTypeId((String)list.get(0));
				dto.setSubPropTypeId((String)list.get(3));
				if(language.equalsIgnoreCase("en"))
				{
					dto.setPropertyTypeName((String)list.get(1)+" / "+(String)list.get(2));
					dto.setSubPropTypeName((String)list.get(4)+" / "+(String)list.get(5));
				}
				else
				{
					dto.setPropertyTypeName((String)list.get(1)+" / "+(String)list.get(2));
					dto.setSubPropTypeName((String)list.get(4)+" / "+(String)list.get(5));
				}
				dto.setPropTypeL1Id((String)list3.get(0));
				dto.setPropTypeL1Name((String)list3.get(1)+" / "+(String)list3.get(2));
				dto.setHdnpropids((String)list.get(0)+"~"+(String)list.get(3)+"~"+dto.getPropTypeL1Id());
				dto.setStatusChk("N");
				subClausePropList.add(dto);
			}
			
		}
		else
		{
			ModuleMasterDTO dto=new ModuleMasterDTO();
			dto.setPropertyTypeId((String)list.get(0));
			dto.setSubPropTypeId((String)list.get(3));
			if(language.equalsIgnoreCase("en"))
			{
				dto.setPropertyTypeName((String)list.get(1)+" / "+(String)list.get(2));
				dto.setSubPropTypeName((String)list.get(4)+" / "+(String)list.get(5));
			}
			else
			{
				dto.setPropertyTypeName((String)list.get(1)+" / "+(String)list.get(2));
				dto.setSubPropTypeName((String)list.get(4)+" / "+(String)list.get(5));
			}
			dto.setPropTypeL1Id("0");
			dto.setPropTypeL1Name("-");
			dto.setHdnpropids((String)list.get(0)+"~"+(String)list.get(3)+"~"+dto.getPropTypeL1Id());
			dto.setStatusChk("N");
			subClausePropList.add(dto);
		}
		
		
		dbUtility.commit();
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	return subClausePropList;
	
}

public boolean addSubClauseDetails(String params[],ModuleMasterDTO dto,ArrayList list_propids) throws Exception{
	boolean flag = true;
	boolean flag1 = true;
	boolean flag2 = true;
	boolean flag3 = false;
	try {
		dbUtility=new DBUtility();
		dbUtility.setAutoCommit(false);
		String compId  = "";
		String subId  = "";
		String subMappingId  = "";
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;
		ArrayList list5=null;
		
		dbUtility.createStatement();
		compId = dbUtility.executeQry(CommonSQL.SELECTCOMPUTATIONID);
		
		dbUtility.createStatement();
		subId = dbUtility.executeQry(CommonSQL.SELECTSUBCLAUSEID);
		
		dbUtility.createStatement();
		subMappingId = dbUtility.executeQry(CommonSQL.SELECTSUBCLAUSEMAPPINGID);
		
		String[]params2={
				compId,
				dto.getOperatorId(),
				dto.getOperandValue(),
				dto.getPropTypeId(),
				dto.getApplicableBaseValue(),
				params[0].toString()
		};
	
		dbUtility.createPreparedStatement(CommonSQL.INSERT_COMPUTATION_LOGIC_DETAILS);
		flag = dbUtility.executeUpdate(params2);
		
		String[] params1={
				subId,
				dto.getName(),
				dto.getDescription(),
				dto.getDescriptionHindi(),
				compId,
				params[0].toString(),
				dto.getPropertyTypeId()
				
		};
		
		dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_MASTER_DETAILS);
		flag1 = dbUtility.executeUpdate(params1);
		if(!dto.getPropertyTypeId().equals("1"))
		{
			for(int i=0;i<list_propids.size();i++)
			{dto=(ModuleMasterDTO)list_propids.get(i);	
				if(dto.getChkdPropTypeL2Id().equalsIgnoreCase("0"))
				{
				String[] params3={
						getSubClausePropMappingId(),
						subId,
						dto.getChkdPropTypeId(),
						dto.getChkdPropTypeL1Id(),
						params[0],
						
					};

				dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETLS);
				flag2 = dbUtility.executeUpdate(params3);
				}
				else
				{
					String[] params3={
							getSubClausePropMappingId(),
							subId,
							dto.getChkdPropTypeId(),
							dto.getChkdPropTypeL1Id(),
							params[0],
							dto.getChkdPropTypeL2Id()
									};
			
				dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETLS_L1);
				flag2 = dbUtility.executeUpdate(params3);
				}
				
			}
		}
		if(flag && flag1 && flag2)
		{
			flag3 = true;
			dbUtility.commit();
		}
		

		//end of code
		
	} catch (Exception e) {
		dbUtility.rollback();
		//dbUtility.closeConnection();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	return flag3;
}

/**
 * 
 * @param listType
 * @return
 */
public ArrayList getSubclaueList(){
	String SQL = null;
	
	SQL=CommonSQL.SELECT_SUCLAUSE_DETLS_FOR_DASHBOARD;
	
	ArrayList mainlist = null;
	ArrayList list = null;
	try {
		dbUtility = new DBUtility();
		dbUtility.createStatement();
		mainlist = new ArrayList();
		ArrayList list1= dbUtility.executeQuery(SQL);
        for (int i=0;i<list1.size();i++){ 
         list = (ArrayList)list1.get(i);  
         ModuleMasterDTO dto = new ModuleMasterDTO();
         dto.setId((String)list.get(0));
         dto.setName((String)list.get(1));
         dto.setDescription((String)list.get(2));
         dto.setDescriptionHindi((String)list.get(3));
         dto.setDeleteSubId("Delete/हटाएं");
         mainlist.add(dto);
         }
	} catch (Exception e) {
		e.printStackTrace();
	}
	finally{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.debug("exception in closing connection");
			e.printStackTrace();
		}
		
	}
	return mainlist;
}

public String getPropDetails(String subId){
	String SQL = "";
	SQL = CommonSQL.GET_PROPERTY_TYPE_ID_FOR_SUBCLAUSE;
	String propId = "";
	
	try
	{
		dbUtility = new DBUtility();
		dbUtility.createPreparedStatement(SQL);
		String arr[] = {subId};
		propId = dbUtility.executeQry(arr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.debug("exception in closing connection");
			e.printStackTrace();
		}
	}
	return propId;
}

public boolean deleteSubclause(String subId) throws Exception{
	boolean flag = true;
	try {
		dbUtility=new DBUtility();
		dbUtility.createPreparedStatement(CommonSQL.DEACTIVATE_SUBCLAUSE);
		String subArr[] = {subId};
		flag = dbUtility.executeUpdate(subArr);
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			logger.debug("exception in closing connection");
			e.printStackTrace();
		}
	
	return flag;
		}
	}

public boolean updateSubClauseDetails(String params[],ModuleMasterForm form,ArrayList list_propids) throws Exception{
	boolean flag = true;
	boolean flag1 = true;
	boolean flag2 = true;
	boolean flag3 = false;
	try {
		dbUtility=new DBUtility();
		dbUtility.setAutoCommit(false);
		String compId  = "";
		String subId  = "";
		String subMappingId  = "";
		ArrayList list1=null;
		ArrayList list2=null;
		ArrayList list3=null;
		ArrayList list4=null;
		ArrayList list5=null;
		
		dbUtility.createPreparedStatement(CommonSQL.DEACTIVATE_SUBCLAUSE);
		String subArr[] = {form.getSubId()};
		flag = dbUtility.executeUpdate(subArr);
		
		dbUtility.createStatement();
		compId = dbUtility.executeQry(CommonSQL.SELECTCOMPUTATIONID);
		
		dbUtility.createStatement();
		subId = dbUtility.executeQry(CommonSQL.SELECTSUBCLAUSEID);
		
		dbUtility.createStatement();
		subMappingId = dbUtility.executeQry(CommonSQL.SELECTSUBCLAUSEMAPPINGID);
		
		String[]params2={
				compId,
				form.getOperatorId(),
				form.getOperandValue(),
				form.getCompBasedOn(),
				form.getApplicableBaseValue(),
				params[0].toString()
		};
	
		dbUtility.createPreparedStatement(CommonSQL.INSERT_COMPUTATION_LOGIC_DETAILS);
		flag = dbUtility.executeUpdate(params2);
		
		String[] params1={
				subId,
				form.getName(),
				form.getDescription(),
				form.getDescriptionHindi(),
				compId,
				params[0].toString(),
				form.getPropertyId()
				
		};
		
		dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_MASTER_DETAILS);
		flag1 = dbUtility.executeUpdate(params1);
		if(!form.getPropertyId().equals("1"))
		{
			for(int i=0;i<list_propids.size();i++)
			{ModuleMasterDTO dto=(ModuleMasterDTO)list_propids.get(i);	
				if(dto.getChkdPropTypeL2Id().equalsIgnoreCase("0"))
				{
				String[] params3={
						getSubClausePropMappingId(),
						subId,
						dto.getChkdPropTypeId(),
						dto.getChkdPropTypeL1Id(),
						params[0],
						
					};

				dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETLS);
				flag2 = dbUtility.executeUpdate(params3);
				}
				else
				{
					String[] params3={
							getSubClausePropMappingId(),
							subId,
							dto.getChkdPropTypeId(),
							dto.getChkdPropTypeL1Id(),
							params[0],
							dto.getChkdPropTypeL2Id()
									};
			
				dbUtility.createPreparedStatement(CommonSQL.INSERT_SUBCLAUSE_PROP_MAPPING_DETLS_L1);
				flag2 = dbUtility.executeUpdate(params3);
				}
				
			}
		}
		if(flag && flag1 && flag2)
		{
			flag3 = true;
			dbUtility.commit();
		}
		

		//end of code
		
	} catch (Exception e) {
		dbUtility.rollback();
		//dbUtility.closeConnection();
		e.printStackTrace();
	}
	finally
	{
		try
		{
			dbUtility.closeConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}
	return flag3;
}
}