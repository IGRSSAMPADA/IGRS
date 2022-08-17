/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserRGroupMappingCommonSQL.java
 * Author      :   Hend M. ismail
 * Description :   Represents the CommonSQL Class for User RoleGroup Mappings Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  7th September, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmapping.sql;

public class UserRGroupMappingCommonSQL {
	// Constants ---------------------------------------------------------------
	
	
	//IGRS_USER_ROLE_GROUP_MAPPING
	// Select Query
	public static final String SELECT_USER_ROLE_GROUP_MAPPING_JOIN = "SELECT T1.USER_ROLE_GROUP_MAPPING_ID,T1.USER_ID,T2.FIRST_NAME ||' '|| T2.MIDDLE_NAME ||' '|| T2.LAST_NAME,T1.ROLE_GROUP_ID ,T3.ROLE_GROUP_NAME "+
															  "FROM IGRS_USER_ROLE_GROUP_MAPPING T1 "+
															  "LEFT OUTER JOIN IGRS_EMP_MASTER T2 ON T1.USER_ID=T2.EMP_ID "+
															  "LEFT OUTER JOIN IGRS_ROLE_GROUP_MASTER T3 ON T1.ROLE_GROUP_ID=T3.ROLE_GROUP_ID";
	
	public static final String SELECT_USER_ROLE_GROUP_MAPPING_ID="SELECT T1.USER_ROLE_GROUP_MAPPING_ID,T1.USER_ID,T2.FIRST_NAME ||' '|| T2.MIDDLE_NAME ||' '|| T2.LAST_NAME,T1.ROLE_GROUP_ID ,T3.ROLE_GROUP_NAME "+
	  															"FROM IGRS_USER_ROLE_GROUP_MAPPING T1 "+
	  															"LEFT OUTER JOIN IGRS_EMP_MASTER T2 ON T1.USER_ID=T2.EMP_ID "+
	  															"LEFT OUTER JOIN IGRS_ROLE_GROUP_MASTER T3 ON T1.ROLE_GROUP_ID=T3.ROLE_GROUP_ID WHERE T1.USER_ROLE_GROUP_MAPPING_ID=?";
	
	public static final String SELECT_USER_ROLE_GROUP_MAPPING_USER_ID="SELECT T1.USER_ROLE_GROUP_MAPPING_ID,T1.USER_ID,T2.FIRST_NAME ||' '|| T2.MIDDLE_NAME ||' '|| T2.LAST_NAME,T1.ROLE_GROUP_ID ,T3.ROLE_GROUP_NAME "+
																		"FROM IGRS_USER_ROLE_GROUP_MAPPING T1 "+
																		"LEFT OUTER JOIN IGRS_EMP_MASTER T2 ON T1.USER_ID=T2.EMP_ID "+
																		"LEFT OUTER JOIN IGRS_ROLE_GROUP_MASTER T3 ON T1.ROLE_GROUP_ID=T3.ROLE_GROUP_ID WHERE T1.USER_ID=?";
	public static final String SELECT_USER_ROLE_GROUP_MAPPING_Role_ID="SELECT T1.USER_ROLE_GROUP_MAPPING_ID,T1.USER_ID,T2.FIRST_NAME ||' '|| T2.MIDDLE_NAME ||' '|| T2.LAST_NAME,T1.ROLE_GROUP_ID ,T3.ROLE_GROUP_NAME "+
																		"FROM IGRS_USER_ROLE_GROUP_MAPPING T1 "+
																		"LEFT OUTER JOIN IGRS_EMP_MASTER T2 ON T1.USER_ID=T2.EMP_ID "+
																		"LEFT OUTER JOIN IGRS_ROLE_GROUP_MASTER T3 ON T1.ROLE_GROUP_ID=T3.ROLE_GROUP_ID WHERE T1.ROLE_GROUP_ID =?";
	//Insert Query
	public static final String INSERT_USER_ROLE_GROUP_MAPPING ="INSERT INTO IGRS_USER_ROLE_GROUP_MAPPING(USER_ROLE_GROUP_MAPPING_ID,USER_ID,ROLE_GROUP_ID)VALUES (IGRS_USER_ROLE_GROUP_MAP_SEQ.nextval,?,?)";
	
	//Update Query
	public static final String UPDATE_USER_ROLE_GROUP_MAPPING="UPDATE IGRS_USER_ROLE_GROUP_MAPPING SET USER_ID=?,ROLE_GROUP_ID=? WHERE USER_ROLE_GROUP_MAPPING_ID=?";
	
	// Delete Query
	public static final String DELETE_USER_ROLE_GROUP_MAPPING="DELETE FROM IGRS_USER_ROLE_GROUP_MAPPING WHERE USER_ROLE_GROUP_MAPPING_ID=?";
	
	
	//IGRS_ROLE_GROUP_MASTER
	//Select Query
	public static final String SELECT_ROLE_GROUP_MASTER="SELECT ROLE_GROUP_ID,ROLE_GROUP_NAME FROM IGRS_ROLE_GROUP_MASTER";
	
	
	//IGRS_USERS_LIST
	//Select Query
	public static final String SELECT_USERS_LIST_JOIN="SELECT USER_ID,FIRST_NAME ||' '|| MIDDLE_NAME ||' '|| LAST_NAME FROM IGRS_USER_REG_DETAILS " +
														"UNION SELECT EMP_ID,FIRST_NAME ||' '|| MIDDLE_NAME ||' '|| LAST_NAME FROM IGRS_EMP_MASTER";
	
}
