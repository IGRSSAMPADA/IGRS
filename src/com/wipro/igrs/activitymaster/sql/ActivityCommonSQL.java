/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ActivityCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.activitymaster.sql;

public class ActivityCommonSQL {
	// Constants ---------------------------------------------------------------
	// Select Query
	public static final String SELECT_ACTIVITY_MASTER = "SELECT ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_DESCRIPTION,ACTIVITY_STATUS FROM IGRS_ACTIVITY_MASTER";
	public static final String SELECT_ACTIVITY_MASTER_ID = "SELECT ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_DESCRIPTION,ACTIVITY_STATUS FROM IGRS_ACTIVITY_MASTER WHERE ACTIVITY_ID=?";

	// Update Query
	public static final String UPDATE_ACTIVITY_MASTER = "UPDATE IGRS_ACTIVITY_MASTER SET ACTIVITY_NAME=?,ACTIVITY_DESCRIPTION=?,ACTIVITY_STATUS=? WHERE ACTIVITY_ID=?";

	// Insert Query
	public static final String INSERT_ACTIVITY_MASTER = "INSERT INTO IGRS_ACTIVITY_MASTER(ACTIVITY_ID,ACTIVITY_NAME,ACTIVITY_DESCRIPTION,ACTIVITY_STATUS) VALUES('ACT'||IGRS_ACTIVITY_MASTER_SEQ.nextval,?,?,'D')";

}
