/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   OfficeDeptartmentCommonSQL.java
 * Author      :   Sayed Taha 
 * Description :   Represents the CommonSQL Class for  office Department Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Sayed Taha 11th Aug, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.officedepartmentmaster.sql;

public class OfficeDeptartmentCommonSQL {
	 // Constants ---------------------------------------------------------------
	 //Select Query
	  public static final String SELECT_ALL_OFFICE_DEPT_MASTER = "SELECT DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_OFFICE_DEPT_MASTER WHERE DEPT_STATUS NOT LIKE 'R'";
	  public static final String SELECT_OFFICE_DEPT_BYID="SELECT DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_OFFICE_DEPT_MASTER WHERE DEPT_ID=?";
	  public static final String SELECT_OFFICE_DEPT_BYNAME="SELECT DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE FROM IGRS_OFFICE_DEPT_MASTER WHERE DEPT_NAME=?";
	  public static final String SELECT_USER_NAME_BYID="SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE USER_TXN_ID=?";
	  //Update Query
	  public static final String UPDATE_OFFICE_DEPT_MASTER = "UPDATE IGRS_OFFICE_DEPT_MASTER SET DEPT_NAME=?,DEPT_DESC=?,DEPT_STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE DEPT_ID=?";
	  public static final String DELETE_OFFICE_DEPT_MASTER="UPDATE IGRS_OFFICE_DEPT_MASTER SET DEPT_STATUS='R' WHERE DEPT_ID=?";
	  //Insert Query
	  public static final String INSERT_OFFICE_DEPT_MASTER = "INSERT INTO IGRS_OFFICE_DEPT_MASTER(DEPT_ID,DEPT_NAME,DEPT_DESC,DEPT_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE) VALUES('Dept'||IGRS_OFFICE_DEPT_SEQ.nextval,?,?,'A',?,sysdate,?,sysdate)";       
     
}
