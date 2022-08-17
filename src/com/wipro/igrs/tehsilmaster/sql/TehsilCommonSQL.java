/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	TehsilCommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	03/03/2008
 */
package com.wipro.igrs.tehsilmaster.sql;

public class TehsilCommonSQL {
	//Select Query
	public static final String SELECT_TEHSIL_MASTER = "SELECT TEHSIL_ID,TEHSIL_NAME,TEHSIL_DESCRIPTION,TEHSIL_STATUS FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A'";
	public static final String SELECT_TEHSIL_MASTER_ID = "SELECT TEHSIL_ID,TEHSIL_NAME,TEHSIL_DESCRIPTION,TEHSIL_STATUS FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=? AND TEHSIL_STATUS='A'";
	public static final String SELECT_TEHSIL_MASTER_DISTID = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER";
	//Update Query
	public static final String UPDATE_TEHSIL_MASTER = "UPDATE IGRS_TEHSIL_MASTER SET TEHSIL_NAME=?,TEHSIL_DESCRIPTION=?,TEHSIL_STATUS=? WHERE TEHSIL_ID=?";
	//Insert Query
	public static final String INSERT_TEHSIL_MASTER = "INSERT INTO IGRS_TEHSIL_MASTER(TEHSIL_ID,TEHSIL_NAME,TEHSIL_DESCRIPTION,TEHSIL_STATUS,DISTRICT_ID) VALUES(IGRS_DISTRICT_MASTER_SEQ.nextval,?,?,'A',?)";       
}
