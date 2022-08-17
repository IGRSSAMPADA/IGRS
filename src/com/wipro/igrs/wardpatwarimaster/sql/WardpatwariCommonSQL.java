/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	WardpatwariCommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 	04/03/2008
 */
package com.wipro.igrs.wardpatwarimaster.sql;

public class WardpatwariCommonSQL {
	//Select Query
	public static final String SELECT_WARDPATWARI_MASTER_TEHSILID = "SELECT TEHSIL_ID,TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A'";
	public static final String SELECT_WARDPATWARI_MASTER = "SELECT WARD_PATWARI_ID,WARD_PATWARI_NAME,WARD_PATWARI_DESC,WARD_PATWARI_STATUS FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_STATUS = 'A'";
	public static final String SELECT_WARDPATWARI_MASTER_ID = "SELECT WARD_PATWARI_ID,WARD_PATWARI_NAME,WARD_PATWARI_DESC,WARD_PATWARI_STATUS FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_ID=?";
	//Update Query
	public static final String UPDATE_WARDPATWARI_MASTER = "UPDATE IGRS_WARD_PATWARI_MASTER SET WARD_PATWARI_NAME=?,WARD_PATWARI_DESC=?,WARD_PATWARI_STATUS=? WHERE WARD_PATWARI_ID=?";
	//Insert Query
	public static final String INSERT_WARDPATWARI_MASTER ="INSERT INTO IGRS_WARD_PATWARI_MASTER(WARD_PATWARI_ID,WARD_PATWARI_NAME,WARD_PATWARI_DESC,WARD_PATWARI_STATUS,TEHSIL_ID) VALUES(IGRS_WARDPATWARI_SEQ.nextval,?,?,'A',?)";
}
