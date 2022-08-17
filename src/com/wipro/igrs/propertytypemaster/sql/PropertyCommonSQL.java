/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	PropertyCommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	28/02/2008
 */
package com.wipro.igrs.propertytypemaster.sql;

public class PropertyCommonSQL {  
	//Select Querty
	public static final String SELECT_MASTER_PROPERTY = "SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME,PROPERTY_TYPE_DESC,PROPERTY_TYPE_STATUS FROM IGRS_PROPERTY_TYPE_MASTER";
	public static final String SELECT_MASTER_PROPERTY_ID ="SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME,PROPERTY_TYPE_DESC,PROPERTY_TYPE_STATUS FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_ID=?";
	//Update Query
	public static final String UPDATE_MASTER_PROPERTY = "UPDATE IGRS_PROPERTY_TYPE_MASTER SET PROPERTY_TYPE_NAME=?,PROPERTY_TYPE_DESC=?,PROPERTY_TYPE_STATUS=? WHERE PROPERTY_TYPE_ID=?";
    //Inserting queries
    public static final String INSERT_MASTER_PROPERTY = "INSERT INTO IGRS_PROPERTY_TYPE_MASTER VALUES(IGRS_PROPERTYTYPE_SEQ.nextval,?,?,'y','',SYSDATE,'',SYSDATE,'')";       
}
