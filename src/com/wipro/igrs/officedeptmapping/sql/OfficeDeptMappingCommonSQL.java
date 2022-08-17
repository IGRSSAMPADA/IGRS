/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   FunctionDeptCommonSQL.java
 * Author      :   Mahmoud Eid 
 * Description :   Represents the CommonSQL Class for  Office Department Mapping.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mahmoud Eid  17th August, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officedeptmapping.sql;

public class OfficeDeptMappingCommonSQL {
	

		// Constants ---------------------------------------------------------------
		// Select Query
		public static final String INSERT_OFFICE_DEPT = "INSERT INTO IGRS_OFFICE_DEPT_MAPPING (OFFICE_DEPT_ID,OFFICE_ID,"+
				"DEPT_ID) VALUES (?,?,?)";
		
		public static final String SELECT_OFFICE_DEPT = "SELECT OFFICE_ID,DEPT_ID FROM IGRS_OFFICE_DEPT_MAPPING WHERE OFFICE_ID = ? and DEPT_ID = ?";
		
		public static final String SELECT_OFFICE_DEPT_ALL = "SELECT OFFICE_DEPT_ID,IGRS_OFFICE_DEPT_MAPPING.OFFICE_ID,IGRS_OFFICE_DEPT_MAPPING.DEPT_ID,OFFICE_NAME,DEPT_NAME FROM IGRS_OFFICE_DEPT_MAPPING,IGRS_OFFICE_MASTER,IGRS_OFFICE_DEPT_MASTER WHERE IGRS_OFFICE_DEPT_MAPPING.OFFICE_ID = IGRS_OFFICE_MASTER.OFFICE_ID and IGRS_OFFICE_DEPT_MAPPING.DEPT_ID = IGRS_OFFICE_DEPT_MASTER.DEPT_ID";
		
		public static final String DELETE_OFFICE_DEPT = "DELETE FROM IGRS_OFFICE_DEPT_MAPPING WHERE OFFICE_ID = ? and DEPT_ID  = ?";
		
		public static final String DELETE_OFFICE_DEPT_BY_ID = "DELETE FROM IGRS_OFFICE_DEPT_MAPPING WHERE OFFICE_DEPT_ID = ?";
		
		public static final String UPDATE_OFFICE_DEPT_MAP = "UPDATE IGRS_OFFICE_DEPT_MAPPING SET OFFICE_ID = ? , DEPT_ID = ? WHERE OFFICE_DEPT_ID = ?";
		
		public static final String SELECT_OFFICE_DEPT_ID = "SELECT OFFICE_ID,DEPT_ID FROM IGRS_OFFICE_DEPT_MAPPING WHERE OFFICE_DEPT_ID = ?";
}
