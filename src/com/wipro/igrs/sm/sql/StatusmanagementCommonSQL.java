/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   StatusmanagementCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  Status Management.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.sm.sql;

public class StatusmanagementCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	public static final String SELECT_EMP_ID = "SELECT EMP.EMP_ID,EMP.FIRST_NAME,EMP.LAST_NAME,EMP.PRESENT_DISTRICT,U.USER_STATUS FROM IGRS_EMP_MASTER EMP INNER JOIN IGRS_USERS_LIST U ON EMP.EMP_ID=U.USER_ID WHERE EMP_ID=?";
    public static final String  SELECT_ACTIVITY_STATUS="SELECT ACTIVITY_STATUS FROM IGRS_ACTIVITY_MASTER";
    //public static final String UPDATE_EMP_DETAILS="UPDATE IGRS_EMP_MASTER SET EMP_MARITAL_STATUS=? WHERE EMP_ID=?";
    public static final String UPDATE_EMP_DETAILS="UPDATE IGRS_USERS_LIST U SET U.USER_STATUS=? WHERE U.USER_ID IN (SELECT EMP_ID FROM IGRS_EMP_MASTER WHERE EMP_ID=?)";
}
