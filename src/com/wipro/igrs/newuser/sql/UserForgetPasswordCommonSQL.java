/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   GroupCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for RolegroupMaster.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  11th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.newuser.sql;

public class UserForgetPasswordCommonSQL {
	// Constants ---------------------------------------------------------------
	//Select Query
	public static final String SELECT_USER_HQUESION_ID = "SELECT QUESTION_ID,QUESTION_TEXT FROM IGRS_USER_HINT_QUESTIONS";
	//public static final String SELECT_USER_MATCH = "SELECT U.EMAIL_ID FROM IGRS_USER_REG_DETAILS U WHERE U.USER_ID=? and U.USER_HINT_QUESTION_ID=? and U.USER_HINT_ANSWER=?";
   public static final String SELECT_USER_MATCH="SELECT R.EMAIL_ID FROM IGRS_USER_REG_DETAILS R  INNER JOIN IGRS_USERS_LIST U ON R.USER_ID = U.USER_ID WHERE R.USER_ID=? AND R.USER_HINT_QUESTION_ID=? AND R.USER_HINT_ANSWER=?";
   
   public static final String CALL_FORGOT_PWD = "CALL IGRS_USER_FORGOT_MAILPWD_PROC(?,?,?,?,?,?,?)";
   
   public static final String SELECT_USER_HQUESION_ID_HINDI = "SELECT QUESTION_ID,QUESTION_TEXT,H_QUESTION_TEXT FROM IGRS_USER_HINT_QUESTIONS";
}