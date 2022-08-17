/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   PartyTypeDeedMapSQL.java
 * Author      :   Sayed Taha 
 * Description :   Represents the CommonSQL Class for  PARTY TYPE DEED  mapping .
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.partytypedeedmap.sql;

public class PartyTypeDeedMapSQL {
	 // Constants ---------------------------------------------------------------
	 //Select Query
	  public static final String SELECT_ALL_PARTY_DEED_MAPS="SELECT M.IGRS_PARTY_TYPE_DEED_MAP_ID,D.DEED_TYPE_NAME,P.PARTY_TYPE_NAME FROM IGRS_PARTY_TYPE_DEED_MAP M,IGRS_DEED_TYPE_MASTER D,IGRS_PARTY_TYPE_MASTER P WHERE M.DEED_TYPE_ID=D.DEED_TYPE_ID AND M.PARTY_TYPE_ID=P.PARTY_TYPE_ID";
      public static final String SELECT_DEED_TYPES="SELECT DEED_TYPE_ID,DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER";
	  public static final String SELECT_PARTY_TYPES="SELECT PARTY_TYPE_ID,PARTY_TYPE_NAME FROM IGRS_PARTY_TYPE_MASTER";
      public static final String GET_MAP_BY_ID="SELECT DEED_TYPE_ID,PARTY_TYPE_ID FROM IGRS_PARTY_TYPE_DEED_MAP WHERE IGRS_PARTY_TYPE_DEED_MAP_ID=?";
	  public static final String IS_MAP_EXISTS="SELECT * FROM IGRS_PARTY_TYPE_DEED_MAP WHERE DEED_TYPE_ID=? AND PARTY_TYPE_ID=?";
      //delete Query
	  public static final String DELETE_EPARTY_DEED_MAPS="DELETE FROM IGRS_PARTY_TYPE_DEED_MAP WHERE IGRS_PARTY_TYPE_DEED_MAP_ID=?";
	//Insert Query                    
	  public static final String INSERT_PART_DEED_MAPPING = "INSERT INTO IGRS_PARTY_TYPE_DEED_MAP(IGRS_PARTY_TYPE_DEED_MAP_ID,DEED_TYPE_ID,PARTY_TYPE_ID) VALUES('PD-MAP'||IGRS_PARTY_TYPE_DEED_MAP_SEQ.nextval,?,?)";
	//Update Query
	  public static final String UPDATE_PARTY_DEED_MAPPING="UPDATE IGRS_PARTY_TYPE_DEED_MAP SET DEED_TYPE_ID=?,PARTY_TYPE_ID=? WHERE IGRS_PARTY_TYPE_DEED_MAP_ID=?";
	
	
	
	
	
	
	
	
	
	
	
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
