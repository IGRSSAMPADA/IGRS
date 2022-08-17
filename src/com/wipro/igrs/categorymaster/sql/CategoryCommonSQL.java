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
package com.wipro.igrs.categorymaster.sql;

public class CategoryCommonSQL {
	// Constants ---------------------------------------------------------------
	
	// Select Query
	public static final String SELECT_CATEGORY_MASTER = "SELECT CATEGORY_ID,CATEGORY_NAME,CATEGORY_STATUS FROM IGRS_PERSON_CATEGORY_MASTER WHERE CATEGORY_STATUS <>'R'";
	public static final String SELECT_CATEGORY_MASTER_ID = "SELECT CATEGORY_ID,CATEGORY_NAME,CATEGORY_STATUS FROM IGRS_PERSON_CATEGORY_MASTER WHERE CATEGORY_ID=?";

	// Update Query
	public static final String UPDATE_CATEGORY_MASTER = "UPDATE IGRS_PERSON_CATEGORY_MASTER SET  UPDATED_DATE=SYSDATE,CATEGORY_NAME=?,CATEGORY_STATUS=?,UPDATED_BY=? WHERE CATEGORY_ID=?";

	// Insert Query
	public static final String INSERT_CATEGORY_MASTER = "INSERT INTO IGRS_PERSON_CATEGORY_MASTER(CATEGORY_ID,CATEGORY_NAME,CATEGORY_STATUS,CREATED_DATE,UPDATED_DATE,CREATED_BY,UPDATED_BY) VALUES(?||IGRS_CATEGORY_MASTER_SEQ.nextval,?,'A',SYSDATE,SYSDATE,?,?)";
    // Delete Query
	public static final String DELETE_CATEGORY_MASTER = "UPDATE IGRS_PERSON_CATEGORY_MASTER SET UPDATED_DATE=SYSDATE,CATEGORY_STATUS=? WHERE CATEGORY_ID=?";

	//  check Query
	public static final String CHECK_CATEGORY_MASTER = "SELECT *  FROM IGRS_PERSON_CATEGORY_MASTER WHERE CATEGORY_NAME =?";
    // Select Query
	public static final String SELECT_CATEGORY_MASTER_NAME = "SELECT CATEGORY_ID,CATEGORY_NAME,CATEGORY_STATUS FROM IGRS_PERSON_CATEGORY_MASTER WHERE CATEGORY_NAME=?";

	
	
}
