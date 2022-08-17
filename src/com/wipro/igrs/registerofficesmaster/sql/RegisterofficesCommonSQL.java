/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RegisterofficesCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for RegisterofficeMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  4th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.registerofficesmaster.sql;

public class RegisterofficesCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	public static final String SELECT_OFFICE_MASTER = "SELECT OFFICE_ID,OFFICE_NAME,OFFICE_DESCRIPTION,OFFICE_STATUS,DISTRICT_ID,OFFICE_TYPE_ID FROM IGRS_OFFICE_MASTER";
	public static final String SELECT_OFFICE_MASTER_ID = "SELECT OFFICE_ID,OFFICE_NAME,OFFICE_DESCRIPTION,OFFICE_STATUS,DISTRICT_ID,OFFICE_TYPE_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID=?";
	public static final String SELECT_OFFICE_MASTER_DIS_ID="SELECT DISTRICT_ID FROM IGRS_DISTRICT_MASTER";
	public static final String SELECT_OFFICE_MASTER_WARD_ID="SELECT WARD_PATWARI_ID FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_STATUS = 'A'";
	public static final String SELECT_OFFICE_MASTER_TEHSIL_ID="SELECT TEHSIL_ID FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_STATUS='A'";
	public static final String SELECT_OFFICE_TYPE_ID="SELECT OFFICE_TYPE_ID FROM IGRS_OFFICE_TYPE_MASTER";

	//Update Query
	public static final String UPDATE_OFFICE_MASTER = "UPDATE IGRS_OFFICE_MASTER SET OFFICE_NAME=?,OFFICE_DESCRIPTION=?,OFFICE_STATUS=? WHERE OFFICE_ID=?";
	
	//Insert Query
	public static final String INSERT_OFFICE_MASTER = "INSERT INTO IGRS_OFFICE_MASTER(OFFICE_ID,OFFICE_NAME,OFFICE_DESCRIPTION,OFFICE_STATUS,DISTRICT_ID,TEHSIL_ID,WARD_ID,OFFICE_TYPE_ID) VALUES(IGRS_OFFICE_MASTER_SEQ.nextval,?,?,'A',?,?,?,?)";       

}
