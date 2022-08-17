/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   UserTypeAction.java
 * Author      :   Hend M. ismail
 * Description :   Represents the CommonSQL Class for UserType  Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0              Hend M. ismail  12th Augest, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.usertypemaster.sql;

public class UserTypeCommonSQL {
	// Constants ---------------------------------------------------------------
	// Select Query
	public static final String SELECT_USER_TYPE_MASTER = "SELECT USER_TYPE_ID,USER_TYPE_NAME,USER_TYPE_DESC ,USER_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_USER_TYPE_MASTER WHERE USER_TYPE_STATUS <>'R'";
	public static final String SELECT_USER_TYPE_MASTER_ID = "SELECT USER_TYPE_ID,USER_TYPE_NAME,USER_TYPE_DESC ,USER_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_USER_TYPE_MASTER WHERE USER_TYPE_ID=?";
	public static final String SELECT_USER_TYPE_MASTER_NAME = "SELECT USER_TYPE_ID,USER_TYPE_NAME,USER_TYPE_DESC ,USER_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_USER_TYPE_MASTER WHERE lower(USER_TYPE_NAME)=lower(?) ";
	// Update Query
	public static final String UPDATE_USER_TYPE_MASTER = "UPDATE IGRS_USER_TYPE_MASTER SET USER_TYPE_NAME=?,USER_TYPE_DESC=?,USER_TYPE_STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE USER_TYPE_ID=?";

	// Insert Query
	public static final String INSERT_USER_TYPE_MASTER = "INSERT INTO IGRS_USER_TYPE_MASTER(USER_TYPE_ID,USER_TYPE_NAME,USER_TYPE_DESC,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE,USER_TYPE_STATUS) VALUES('ACT'||IGRS_USER_TYPE_MASTER_SEQ.nextval,?,?,?,?,sysdate,sysdate,'D')";

}
