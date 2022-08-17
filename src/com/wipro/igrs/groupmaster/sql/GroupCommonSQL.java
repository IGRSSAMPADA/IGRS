/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.groupmaster.sql;

public class GroupCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	public static final String SELECT_ROLE_GROUP_MASTER = "SELECT ROLE_GROUP_ID,ROLE_GROUP_NAME,ROLE_GROUP_DESCRIPTION,ROLE_GROUP_STATUS FROM IGRS_ROLE_GROUP_MASTER";
	public static final String SELECT_ROLE_GROUP_MASTER_ID = "SELECT ROLE_GROUP_ID,ROLE_GROUP_NAME,ROLE_GROUP_DESCRIPTION,ROLE_GROUP_STATUS FROM IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_ID=?";
	

	//Update Query
	public static final String UPDATE_ROLE_GROUP_MASTER = "UPDATE IGRS_ROLE_GROUP_MASTER SET ROLE_GROUP_NAME=?,ROLE_GROUP_DESCRIPTION=?,ROLE_GROUP_STATUS=? WHERE ROLE_GROUP_ID=?";
	
	//Insert Query
	public static final String INSERT_ROLE_GROUP_MASTER = "INSERT INTO IGRS_ROLE_GROUP_MASTER(ROLE_GROUP_ID,ROLE_GROUP_NAME,ROLE_GROUP_DESCRIPTION,ROLE_GROUP_STATUS) VALUES(IGRS_ROLE_GROUP_MASTER_SEQ.nextval,?,?,'D')";       

}
