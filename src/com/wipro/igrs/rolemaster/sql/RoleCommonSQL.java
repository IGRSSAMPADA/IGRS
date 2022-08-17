/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RoleCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for RoleMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rolemaster.sql;

public class RoleCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	public static final String SELECT_ROLE_MASTER = "SELECT ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION,ROLE_STATUS FROM IGRS_ROLE_MASTER";
	public static final String SELECT_ROLE_MASTER_ID = "SELECT ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION,ROLE_STATUS FROM IGRS_ROLE_MASTER WHERE ROLE_ID=?";
	

	//Update Query
	public static final String UPDATE_ROLE_MASTER = "UPDATE IGRS_ROLE_MASTER SET ROLE_NAME=?,ROLE_DESCRIPTION=?,ROLE_STATUS=? WHERE ROLE_ID=?";
	
	//Insert Query
	public static final String INSERT_ROLE_MASTER = "INSERT INTO IGRS_ROLE_MASTER(ROLE_ID,ROLE_NAME,ROLE_DESCRIPTION,ROLE_STATUS) VALUES(IGRS_ROLE_MASTER_SEQ.nextval,?,?,'D')";       

}
