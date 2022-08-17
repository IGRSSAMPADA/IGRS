
/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   MohallavillageCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for MohallavillageMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  3rd March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.mohallavillagemaster.sql;

public class MohallavillageCommonSQL {
	//Select Query
	public static final String SELECT_MOHALLAVILAGE_MASTER = "SELECT MOHALLA_VILLAGE_ID,MOHALLA_VILLAGE_NAME,MOHALLA_VILLAGE_DESC,MOHALLA_VILLAGE_STATUS,WARD_PATWARI_ID FROM IGRS_MOHALLA_VILLAGE_MASTER";
	public static final String SELECT_MOHALLAVILAGE_MASTER_ID = "SELECT MOHALLA_VILLAGE_ID,MOHALLA_VILLAGE_NAME,MOHALLA_VILLAGE_DESC,MOHALLA_VILLAGE_STATUS,WARD_PATWARI_ID FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE MOHALLA_VILLAGE_ID=?";
	//Update Query
	public static final String UPDATE_MOHALLAVILAGE_MASTER = "UPDATE IGRS_MOHALLA_VILLAGE_MASTER SET MOHALLA_VILLAGE_NAME=?,MOHALLA_VILLAGE_DESC=?,MOHALLA_VILLAGE_STATUS=? WHERE MOHALLA_VILLAGE_ID=?";
	//Insert Query
	public static final String INSERT_MOHALLAVILAGE_MASTER = "INSERT INTO IGRS_MOHALLA_VILLAGE_MASTER(MOHALLA_VILLAGE_ID,MOHALLA_VILLAGE_NAME,MOHALLA_VILLAGE_DESC,MOHALLA_VILLAGE_STATUS,WARD_PATWARI_ID) VALUES(IGRS_MOH_VIL_MAST_SEQ.nextval,?,?,'A',?)";       
//select wardpatwr id
	public static final String SELECT_MOHALLAVILAGE_WARD_PATWARI="SELECT WARD_PATWARI_ID FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_STATUS = 'A'";
}
