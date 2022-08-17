/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   ReligionCommonSQL.java
 * Author      :   Sara Hussain 
 * Description :   Represents the CommonSQL Class for  Religion Master.
 * ----------------------------------------------------------------------------
 */

package com.wipro.igrs.religionmaster.sql;

public class ReligionCommonSQL {
	// Select Query
	public static final String SELECT_Religion_MASTER = "SELECT RELIGION_ID,RELIGION_NAME,RELIGION_DESCRIPTION,RELIGION_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_RELIGION_MASTER WHERE RELIGION_STATUS NOT LIKE 'R'";
	public static final String SELECT_Religion_MASTER_ID = "SELECT RELIGION_ID,RELIGION_NAME,RELIGION_DESCRIPTION,RELIGION_STATUS FROM IGRS_RELIGION_MASTER WHERE RELIGION_ID=?";
	public static final String SELECT_Religion_MASTER_Name = "SELECT RELIGION_ID FROM IGRS_RELIGION_MASTER WHERE RELIGION_NAME LIKE ?";

	// Update Query
	public static final String UPDATE_Religion_MASTER = "UPDATE IGRS_RELIGION_MASTER SET RELIGION_NAME=?,RELIGION_DESCRIPTION=?,RELIGION_STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE RELIGION_ID=?";

	// Insert Query
	public static final String INSERT_Religion_MASTER = "INSERT INTO IGRS_RELIGION_MASTER(RELIGION_ID,RELIGION_NAME,RELIGION_DESCRIPTION,RELIGION_STATUS,CREATED_BY,CREATED_DATE) VALUES('REL'||IGRS_RELIGION_MASTER_SEQ.nextval,?,?,'A',?,sysdate)";
}
