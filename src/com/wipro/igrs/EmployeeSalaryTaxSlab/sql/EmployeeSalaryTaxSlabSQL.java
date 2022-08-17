/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmployeeSalaryTaxSlabSQL.java
 * Author      :   Sayed Taha 
 * Description :   Represents the CommonSQL Class for  Employee Salary Tax Slab .
 * Creation Date:  Sept 10 2008
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.sql;

public class EmployeeSalaryTaxSlabSQL {
	 // Constants ---------------------------------------------------------------
	 //Select Query
	  public static final String SELECT_ALL_EMPLOYEE_SALARY_TAX_SLABs="SELECT TAX_ID,START_SLAB,END_SLAB,PERSON_TYPE,PERCENT_APPLICACLE FROM IGRS_EMP_SAL_TAX_SLAB";
	  public static final String GET_EMPLOYEE_SALARY_TAX_SLAB_BY_ID="SELECT TAX_ID,START_SLAB,END_SLAB,PERSON_TYPE,PERCENT_APPLICACLE FROM IGRS_EMP_SAL_TAX_SLAB WHERE TAX_ID=?";
	  public static final String IS_SLAB_EXISTS="SELECT TAX_ID FROM IGRS_EMP_SAL_TAX_SLAB WHERE START_SLAB=? AND END_SLAB=? AND PERSON_TYPE=? AND PERCENT_APPLICACLE=?";
	  
	 
      //delete Query
	  public static final String DELETE_EPARTY_DEED_MAPS="DELETE FROM IGRS_PARTY_TYPE_DEED_MAP WHERE IGRS_PARTY_TYPE_DEED_MAP_ID=?";
	//Insert Query                    
	  public static final String INSERT_EMPLOYEE_SALARY_TAX_SLAB = "INSERT INTO IGRS_EMP_SAL_TAX_SLAB(TAX_ID,START_SLAB,END_SLAB,PERSON_TYPE,PERCENT_APPLICACLE) VALUES('TAX'||IGRS_EMP_SAL_TAX_SLAB_SEQ.NEXTVAL,?,?,?,?)";
	//Update Query
	  public static final String UPDATE_EMPLOYEE_SALARY_TAX_SLAB="UPDATE IGRS_EMP_SAL_TAX_SLAB SET START_SLAB=?,END_SLAB=?,PERSON_TYPE=?,PERCENT_APPLICACLE=? WHERE TAX_ID=?";
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	
	
	public static final String SELECT_ALL_EVENTS_MAPPING = "SELECT el.email_User_Lookup_Dtls_ID,pl.ATTRIBUTE_NAME,el.EMAIL_LOOKUP_TXN_ID,el.TO_EMAIL_USER_ID,el.CC_EMAIL_USER_ID,el.FROM_EMAIL_USER_ID,el.CREATED_DATE FROM IGRS_EMAIL_USER_LOOKUP_DTLS el,IGRS_CONFIG_PARAM_LIST pl,IGRS_EMAIL_LOOKUP_DETAILS ds WHERE el.EMAIL_LOOKUP_TXN_ID=ds.EMAIL_TXN_ID and ds.ATTRIBUTE_ID=pl.ATTRIBUTE_ID";
	  public static final String SELECT_EMAIL_USER_ID="SELECT USER_ID FROM IGRS_USERS_LIST";
	  public static final String SELECT_CONFIG_PARAM_LIST="SELECT ATTRIBUTE_ID,ATTRIBUTE_NAME FROM IGRS_CONFIG_PARAM_LIST";
	  public static final String SELECT_EMAIL_EVENT_NAME_LIST="SELECT ds.EMAIL_TXN_ID ,pl.ATTRIBUTE_NAME FROM IGRS_CONFIG_PARAM_LIST pl,IGRS_EMAIL_LOOKUP_DETAILS ds WHERE ds.ATTRIBUTE_ID=pl.ATTRIBUTE_ID"; 
	  /*  public static final String SELECT_USER_NAME_BYID="SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE USER_TXN_ID=?";
	  //Update Query
	  public static final String UPDATE_OFFICE_DEPT_MASTER = "UPDATE IGRS_OFFICE_DEPT_MASTER SET DEPT_NAME=?,DEPT_DESC=?,DEPT_STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE DEPT_ID=?";
	  public static final String DELETE_OFFICE_DEPT_MASTER="UPDATE IGRS_OFFICE_DEPT_MASTER SET DEPT_STATUS='R' WHERE DEPT_ID=?";
	 
	         
      
*/
}    
