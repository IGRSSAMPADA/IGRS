/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   URolegroupCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  Userrolegroupmapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  18th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.userrolegroupmappingmaster.sql;

public class URolegroupCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	  public static final String SELECT_USER_ROLE_GROUP_MAPPING_MASTER = "SELECT USER_ID,ROLE_GROUP_ID,ACTIVE_FLAG FROM IGRS_USER_ROLE_GROUP_MAPPING";
	  public static final String SELECT_USER_GROUP_MAPPING_MASTER_ID = "SELECT USER_ID,ROLE_GROUP_ID,ACTIVE_FLAG FROM IGRS_USER_ROLE_GROUP_MAPPING WHERE USER_ID=?";
	  public static final String SELECT_USER_MASTER="SELECT USER_ID FROM IGRS_USERS_LIST";
	  public static final String SELECT_ROLE_GROUP_MASTER="SELECT ROLE_GROUP_ID,ROLE_GROUP_NAME FROM IGRS_ROLE_GROUP_MASTER";

	//Update Query
	public static final String UPDATE_USER_ROLE_GROUP_MAPPING_MASTER = "UPDATE IGRS_USER_ROLE_GROUP_MAPPING SET ACTIVE_FLAG=? WHERE USER_ID=?";
	
	//Insert Query
	public static final String INSERT_USER_ROLE_GROUP_MAPPING_MASTER = "INSERT INTO IGRS_USER_ROLE_GROUP_MAPPING(USER_ID,ROLE_GROUP_ID,ACTIVE_FLAG) VALUES(?,?,'D')";       

}
