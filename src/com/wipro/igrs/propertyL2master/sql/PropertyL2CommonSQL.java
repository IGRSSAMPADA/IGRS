/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyL2CommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	27/02/2008
 */
package com.wipro.igrs.propertyL2master.sql;

public class PropertyL2CommonSQL {
	// Select Query
	public static final String SELECT_MASTER_L2_PROPERTY = "SELECT PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER";
	public static final String SELECT_MASTER_L2_PROPERTY_ID = "SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME,PROPERTY_TYPE_L2_DESC,PROPERTY_TYPE_L2_STATUS,PROPERTY_TYPE_L1_ID FROM IGRS_PROP_TYPE_L2_MASTER";
	public static final String SELECT_MASTER_L2_PROPERTY_L2ID = "SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME,PROPERTY_TYPE_L2_DESC,PROPERTY_TYPE_L2_STATUS FROM IGRS_PROP_TYPE_L2_MASTER WHERE PROPERTY_TYPE_L2_ID=?";
	// Update Query
	public static final String UPDATE_MASTER_L2_PROPERTYL2_ID = "UPDATE IGRS_PROP_TYPE_L2_MASTER SET PROPERTY_TYPE_L2_NAME=?,PROPERTY_TYPE_L2_DESC=?,PROPERTY_TYPE_L2_STATUS=? WHERE PROPERTY_TYPE_L2_ID=?";
	 //Inserting queries
    public static final String INSERT_MASTER_L2_PROPERTY = "INSERT INTO IGRS_PROP_TYPE_L2_MASTER VALUES(IGRS_PROPERTY_L2_SEQ.nextval,?,?,'y',?,'',SYSDATE,'',SYSDATE)";
}
