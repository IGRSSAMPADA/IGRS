/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   RGroupmappingCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  RGrouprolemapping Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  17th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.rgroupmappingmaster.sql;

public class RGroupmappingCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	  //public static final String SELECT_RGROUP_MAPPING_MASTER = "SELECT ROLE_GROUP_ID,ROLE_ID,ACTIVE_FLAG FROM IGRS_RGROUP_ROLE_MAPPING";
	//public static final String SELECT_RGROUP_MAPPING_MASTER = "SELECT ROLE_GROUP_ID,ROLE_ID FROM IGRS_RGROUP_ROLE_MAPPING";
	
	public static final String SELECT_RGROUP_MAPPING_MASTER = "SELECT RGM.ROLE_GROUP_NAME,RGM.ROLE_GROUP_ID,RM.ROLE_NAME,RM.ROLE_ID " +
			" FROM IGRS_ROLE_MASTER RM,IGRS_RGROUP_ROLE_MAPPING RMAP,IGRS_ROLE_GROUP_MASTER RGM WHERE RMAP.ROLE_GROUP_ID=RGM.ROLE_GROUP_ID AND RMAP.ROLE_ID=RM.ROLE_ID";
	  public static final String SELECT_RGROUP_MAPPING_MASTER_ID = "SELECT ROLE_GROUP_ID,ROLE_ID,ACTIVE_FLAG FROM IGRS_RGROUP_ROLE_MAPPING WHERE ROLE_GROUP_ID=?";
	  public static final String SELECT_ROLE_MASTER="SELECT ROLE_ID,ROLE_NAME  FROM IGRS_ROLE_MASTER";
	  public static final String SELECT_ROLE_GROUP_MASTER="SELECT ROLE_GROUP_ID,ROLE_GROUP_NAME  FROM IGRS_ROLE_GROUP_MASTER";

	//Update Query
	public static final String UPDATE_RGROUP_MAPPING_MASTER = "UPDATE IGRS_RGROUP_ROLE_MAPPING SET ROLE_ID=? WHERE ROLE_GROUP_ID=?";
	
	//Insert Query
	//public static final String INSERT_RGROUP_ROLE_MAPPING_MASTER = "INSERT INTO IGRS_RGROUP_ROLE_MAPPING(ROLE_GROUP_ID,ROLE_ID,ACTIVE_FLAG) VALUES(?,?,'D')";       
	public static final String INSERT_RGROUP_ROLE_MAPPING_MASTER = "INSERT INTO IGRS_RGROUP_ROLE_MAPPING(ROLE_GROUP_ID,ROLE_ID) VALUES(?,?)";
}
