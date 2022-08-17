/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmailEventMappingSQL.java
 * Author      :   Sayed Taha 
 * Description :   Represents the CommonSQL Class for  Email event mapping .
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.emaileventmapping.sql;

public class EmailEventMappingSQL {
	 // Constants ---------------------------------------------------------------
	 //Select Query
	  public static final String SELECT_ALL_EVENTS_MAPPING = "SELECT el.email_User_Lookup_Dtls_ID,pl.ATTRIBUTE_NAME,el.EMAIL_LOOKUP_TXN_ID,el.TO_EMAIL_USER_ID,el.CC_EMAIL_USER_ID,el.FROM_EMAIL_USER_ID,el.CREATED_DATE FROM IGRS_EMAIL_USER_LOOKUP_DTLS el,IGRS_CONFIG_PARAM_LIST pl,IGRS_EMAIL_LOOKUP_DETAILS ds WHERE el.EMAIL_LOOKUP_TXN_ID=ds.EMAIL_TXN_ID and ds.ATTRIBUTE_ID=pl.ATTRIBUTE_ID";
	  public static final String SELECT_EMAIL_USER_ID="SELECT USER_ID FROM IGRS_USERS_LIST";
	  public static final String SELECT_EMAIL_EVENT_NAME_LIST="SELECT ds.EMAIL_TXN_ID ,pl.ATTRIBUTE_NAME FROM IGRS_CONFIG_PARAM_LIST pl,IGRS_EMAIL_LOOKUP_DETAILS ds WHERE ds.ATTRIBUTE_ID=pl.ATTRIBUTE_ID"; 
	  public static final String SELECT_EMAIL_EVENT_BYID="SELECT el.email_User_Lookup_Dtls_ID,pl.ATTRIBUTE_NAME,el.EMAIL_LOOKUP_TXN_ID,el.TO_EMAIL_USER_ID,el.CC_EMAIL_USER_ID,el.FROM_EMAIL_USER_ID FROM IGRS_EMAIL_USER_LOOKUP_DTLS el,IGRS_CONFIG_PARAM_LIST pl,IGRS_EMAIL_LOOKUP_DETAILS ds WHERE el.email_User_Lookup_Dtls_ID=? AND el.EMAIL_LOOKUP_TXN_ID=ds.EMAIL_TXN_ID and ds.ATTRIBUTE_ID=pl.ATTRIBUTE_ID";
	  //Update Query
	  public static final String UPDATE_EMAIL_EVENT_MAPPING = "UPDATE IGRS_EMAIL_USER_LOOKUP_DTLS SET EMAIL_LOOKUP_TXN_ID=?,TO_EMAIL_USER_ID=?,CC_EMAIL_USER_ID=?,FROM_EMAIL_USER_ID=? WHERE email_User_Lookup_Dtls_ID=?";
	  //Insert Query                    
	  public static final String INSERT_EMAIL_EVENT_MAPPING = "INSERT INTO IGRS_EMAIL_USER_LOOKUP_DTLS(email_User_Lookup_Dtls_ID,EMAIL_LOOKUP_TXN_ID,TO_EMAIL_USER_ID,CC_EMAIL_USER_ID,FROM_EMAIL_USER_ID,CREATED_DATE) VALUES('Event'||IGRS_EMAIL_USER_LOOKUP_DTL_SEQ.nextval,?,?,?,?,sysdate)";       
      //delete Query
	  public static final String DELETE_EMAIL_EVENT_MAPPING="DELETE FROM IGRS_EMAIL_USER_LOOKUP_DTLS WHERE email_User_Lookup_Dtls_ID=?";
}    
