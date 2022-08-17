/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DistrictCommonSQL.java
 * Author	:	Rafiq Rahiman.T 
 * Date		: 01/03/2008
 */
package com.wipro.igrs.districtmaster.sql;
public class DistrictCommonSQL {
	//Select Query
	public static final String SELECT_DISTRICT_MASTER = "SELECT DISTRICT_ID,DISTRICT_NAME,DISTRICT_DESCRIPTION,DISTRICT_STATUS,STATE_ID FROM IGRS_DISTRICT_MASTER";
	public static final String SELECT_DISTRICT_MASTER_ID = "SELECT DISTRICT_ID,DISTRICT_NAME,DISTRICT_DESCRIPTION,DISTRICT_STATUS,STATE_ID FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";
	//Update Query
	public static final String UPDATE_DISTRICT_MASTER = "UPDATE IGRS_DISTRICT_MASTER SET DISTRICT_NAME=?,DISTRICT_DESCRIPTION=?,DISTRICT_STATUS=? WHERE DISTRICT_ID=?";
	//Insert Query
	public static final String INSERT_DISTRICT_MASTER = "INSERT INTO IGRS_DISTRICT_MASTER(DISTRICT_ID,DISTRICT_NAME,DISTRICT_DESCRIPTION,DISTRICT_STATUS,STATE_ID,VISIT_FLAG) VALUES(IGRS_DISTRICT_MASTER_SEQ.nextval,?,?,'A','MP','Y')";       

}
