/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyL1CommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	26/02/2008
 */
package com.wipro.igrs.propertyL1master.sql;

public class PropertyL1CommonSQL {
	//Select Query
	public static final String SELECT_MASTER_L1_PROPERTY = "SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER";
	public static final String SELECT_MASTER_L1_PROPERTY_ID = "SELECT PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L1_NAME,PROPERTY_TYPE_L1_DESC,PROPERTY_TYPE_L1_STATUS,PROPERTY_TYPE_ID FROM IGRS_PROP_TYPE_L1_MASTER";
	public static final String SELECT_MASTER_L1_PROPERTY_L1ID ="SELECT PROPERTY_TYPE_L1_ID,PROPERTY_TYPE_L1_NAME,PROPERTY_TYPE_L1_DESC,PROPERTY_TYPE_L1_STATUS FROM IGRS_PROP_TYPE_L1_MASTER WHERE PROPERTY_TYPE_L1_ID=?";
	//Update Query
	public static final String UPDATE_MASTER_L1_PROPERTYL1_ID = "UPDATE IGRS_PROP_TYPE_L1_MASTER SET PROPERTY_TYPE_L1_NAME=?,PROPERTY_TYPE_L1_DESC=?,PROPERTY_TYPE_L1_STATUS=? WHERE PROPERTY_TYPE_L1_ID=?";
	 //Inserting queries
    public static final String INSERT_MASTER_L1_PROPERTY = "INSERT INTO IGRS_PROP_TYPE_L1_MASTER VALUES(IGRS_PROPERTY_L1_SEQ.nextval,?,?,'y',?,SYSDATE,'',SYSDATE,'')";
}
