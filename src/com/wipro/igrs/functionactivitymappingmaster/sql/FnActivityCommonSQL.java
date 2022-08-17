/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FnActivityCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  Rolefunctionactivitymapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.functionactivitymappingmaster.sql;

public class FnActivityCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	  public static final String SELECT_ROLE_FN__ACTIVITY_MAPPING_MASTER = "SELECT FUNCTION_ID,ACTIVITY_ID,ROLE_ID,MODULE_ID,SUB_MODULE_ID FROM IGRS_ROLE_FN_ACTIVITY_MAPPING";
	  public static final String SELECT_ROLE_FN_ACTIVITY_MAPPING_MASTER_ID = "SELECT FUNCTION_ID,ACTIVITY_ID,ACTIVE_FLAG,ROLE_ID,PAYMENT_FLAG,MODULE_ID,SUB_MODULE_ID FROM IGRS_ROLE_FN_ACTIVITY_MAPPING WHERE FUNCTION_ID=?";
	  //public static final String SELECT_FUCTION_MASTER="SELECT FUNCTION_ID,FUNCTION_NAME FROM IGRS_FUNCTION_MASTER";
	  public static final String SELECT_ROLE_MASTER="SELECT ROLE_ID,UPPER(ROLE_NAME) FROM IGRS_ROLE_MASTER ORDER BY UPPER(ROLE_NAME)";
	  //public static final String SELECT_ACTIVITY_MASTER="SELECT ACTIVITY_ID,ACTIVITY_NAME FROM IGRS_ACTIVITY_MASTER";
	  public static final String SELECT_MODULE_MASTER="SELECT MODULE_ID,MODULE_NAME FROM IGRS_MODULE_MASTER WHERE MODULE_STATUS='A' order by MODULE_NAME asc ";
	  //public static final String SELECT_SUB_MODULE_MASTER="SELECT SUB_MODULE_ID,SUB_MODULE_NAME FROM IGRS_SUB_MODULE_MASTER";
	  
	  public static final String SELECT_SUB_MODULE_LIST="SELECT SUB_MODULE_ID, SUB_MODULE_NAME FROM IGRS_SUB_MODULE_MASTER WHERE MODULE_ID=? AND SUB_MODULE_STATUS='A' order by SUB_MODULE_NAME asc";
	  public static final String SELECT_FUNCTION_LIST="SELECT FUNCTION_ID, FUNCTION_NAME FROM IGRS_FUNCTION_MASTER WHERE SUB_MODULE_ID=? AND FUNCTION_STATUS='A' order by FUNCTION_NAME asc";
	  public static final String SELECT_ACTIVITY_LIST="SELECT ACTIVITY_ID, ACTIVITY_NAME FROM IGRS_ACTIVITY_MASTER WHERE FUNCTION_ID=? AND ACTIVITY_STATUS='A' order by ACTIVITY_NAME asc";
	  public static final String SELECT_MAPPED_ACTIVITY_LIST = "SELECT ACTIVITY_ID FROM IGRS_ROLE_FN_ACTIVITY_MAPPING WHERE FUNCTION_ID=? AND ROLE_ID=?  and activity_id IS NOT NULL";
	  public static final String DELETE_CONTAINS_ACTIVITY_IDS = "DELETE FROM IGRS_ROLE_FN_ACTIVITY_MAPPING WHERE ROLE_ID=? AND MODULE_ID = ? AND SUB_MODULE_ID=? AND FUNCTION_ID=? AND ACTIVITY_ID = ?";
	//Update Query
	public static final String UPDATE_ROLE_FN_ACTIVITY_MAPPING_MASTER = "UPDATE IGRS_ROLE_FN_ACTIVITY_MAPPING SET ACTIVE_FLAG=?,PAYMENT_FLAG=? WHERE FUNCTION_ID=?";
	public static final String SELECT_CONTAINS_ACTIVITY_IDS = "SELECT ACTIVITY_ID FROM IGRS_ROLE_FN_ACTIVITY_MAPPING WHERE ROLE_ID=? AND MODULE_ID = ? AND SUB_MODULE_ID=? AND FUNCTION_ID=?";
	public static final String INSERT_ROLE_FN_ACTIVITY_MAPPING ="INSERT INTO IGRS_ROLE_FN_ACTIVITY_MAPPING(ROLE_ID,MODULE_ID,SUB_MODULE_ID,FUNCTION_ID,ACTIVITY_ID,IGRS_ROLE_FN_ACTIVITY_MAP_ID) VALUES(?,?,?,?,?,IGRS_ROLE_FN_ACTIVITY_MAP_SEQ.NEXTVAL)";
	//Insert Query
	//public static final String INSERT_ROLE_FN_ACTIVITY_MAPPING_MASTER = "INSERT INTO IGRS_ROLE_FN_ACTIVITY_MAPPING(FUNCTION_ID,ACTIVITY_ID,ACTIVE_FLAG,ROLE_ID,PAYMENT_FLAG,MODULE_ID,SUB_MODULE_ID) VALUES(?,?,'D',?,'A',?,?)";       
	public static final String INSERT_ROLE_FN_ACTIVITY_MAPPING_MASTER = "INSERT INTO IGRS_ROLE_FN_ACTIVITY_MAPPING(FUNCTION_ID,ACTIVITY_ID,ROLE_ID,MODULE_ID,SUB_MODULE_ID,IGRS_ROLE_FN_ACTIVITY_MAP_ID) VALUES(?,?,?,?,?,IGRS_ROLE_FN_ACTIVITY_MAP_SEQ.NEXTVAL)";       
	
}
