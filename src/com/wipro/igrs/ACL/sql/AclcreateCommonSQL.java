/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   AclcreateCommonSQL.java
 * Author      :   Mallikarjun.M 
 * Description :   Represents the CommonSQL Class for  Activity Master.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Mallikarjun.M  24th March, 2008	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.ACL.sql;

public class AclcreateCommonSQL {
	// Constants ---------------------------------------------------------------
	public static final String UPDATE_SEARCH_PWD_DATA = "UPDATE IGRS_USER_REG_DETAILS SET " +
			"PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,ALTERNATE_EMAIL_ID=?,ADDRESS=?,COUNTRY_ID=?," +
			"STATE_ID=?,DISTRICT_ID=?,POSTAL_CODE=? , USER_PASSWORD=? WHERE USER_ID=?";
	// Select Query
//	public static final String SELECT_FL_LIST = "SELECT REG.USER_ID,REG.FIRST_NAME,REG.LAST_NAME FROM IGRS_USER_REG_DETAILS REG INNER JOIN IGRS_USERS_LIST U ON REG.USER_ID=U.USER_ID WHERE REG.FIRST_NAME=? AND REG.LAST_NAME=? AND REG.USER_STATUS='A'";
//	public static final String SELECT_USER_LIST = "SELECT USER_ID,FIRST_NAME,LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE USER_STATUS='A' ORDER BY first_name ";
	public static final String SELECT_FL_LIST = "SELECT REG.USER_ID,REG.FIRST_NAME,REG.LAST_NAME, REG.USER_STATUS FROM IGRS_USER_REG_DETAILS REG INNER JOIN IGRS_USERS_LIST U ON REG.USER_ID=U.USER_ID WHERE REG.FIRST_NAME=? AND REG.LAST_NAME=? AND REG.USER_STATUS='A' ";
	public static final String SELECT_FL_LIST1 = "SELECT REG.USER_ID,REG.FIRST_NAME,REG.LAST_NAME, REG.USER_STATUS FROM IGRS_USER_REG_DETAILS REG INNER JOIN IGRS_USERS_LIST U ON REG.USER_ID=U.USER_ID WHERE REG.FIRST_NAME=? AND REG.LAST_NAME=? AND REG.USER_STATUS='P' ORDER BY REG.CREATED_DATE DESC";
	public static final String SELECT_USER_LIST = "SELECT USER_ID,FIRST_NAME,LAST_NAME, USER_STATUS FROM IGRS_USER_REG_DETAILS  WHERE USER_STATUS='A' ORDER BY CREATED_DATE DESC";
	public static final String SELECT_USER_LIST1 = "SELECT USER_ID,FIRST_NAME,LAST_NAME, USER_STATUS FROM IGRS_USER_REG_DETAILS WHERE USER_STATUS='P' ORDER BY CREATED_DATE DESC ";
	//public static final String SELECT_USER_ID="SELECT user_id,FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,ALTERNATE_EMAIL_ID,TO_CHAR(DOB,'mm/dd/yyyy'),OCCUPATION,ADDRESS,COUNTRY_ID,STATE_ID,DISTRICT_ID,POSTAL_CODE FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
	//public static final String SELECT_USER_ID = "SELECT USER_ID,FIRST_NAME,LAST_NAME,MIDDLE_NAME,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,GENDER,OCCUPATION,DOB,ADDRESS,POSTAL_CODE,ALTERNATE_EMAIL_ID, USER_PASSWORD FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
//	public static final String SELECT_USER_ID="SELECT iurd.USER_ID,iurd.FIRST_NAME,iurd.LAST_NAME,iurd.MIDDLE_NAME, iurd.PHONE_NUMBER,iurd.MOBILE_NUMBER,iurd.EMAIL_ID,iurd.GENDER,iurd.OCCUPATION,iurd.DOB,iurd.ADDRESS,ism.state_name,idm.district_name,iurd.POSTAL_CODE,iurd.ALTERNATE_EMAIL_ID, iurd.USER_PASSWORD, icm.country_name," +
//			"icm.country_id, ism.state_id, idm.district_id " + 
//	"FROM IGRS_USER_REG_DETAILS iurd, igrs_state_master ism,igrs_district_master idm, igrs_country_master icm WHERE USER_ID=? and iurd.STATE_ID=ism.STATE_ID and iurd.district_id=idm.district_id and iurd.country_id=icm.country_id";
	// Update Query
//	public static final String UPDATE_SEARCH_DATA = "UPDATE IGRS_USER_REG_DETAILS SET FIRST_NAME=?,LAST_NAME=?,MIDDLE_NAME=?,PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,GENDER=?,OCCUPATION=?,DOB=?," +
//			"DATE_OF_JOINING=?,ALTERNATE_EMAIL_ID=?,ADDRESS=?,COUNTRY_ID=?,STATE_ID=?,DISTRICT_ID=?,POSTAL_CODE=? " +
//			"WHERE USER_ID=?";
	public static final String UPDATE_SEARCH_DATA = "UPDATE IGRS_USER_REG_DETAILS SET " +
			"PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,ALTERNATE_EMAIL_ID=?,ADDRESS=?,COUNTRY_ID=?," +
			"STATE_ID=?,DISTRICT_ID=?,POSTAL_CODE=? WHERE USER_ID=?";
//	public static final String UPDATE_SEARCH_DATA = "UPDATE IGRS_USER_REG_DETAILS SET FIRST_NAME=?,LAST_NAME=?,MIDDLE_NAME=?,PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,GENDER=?,OCCUPATION=?,DOB=?,USER_PASSWORD=?,DATE_OF_JOINING=? WHERE USER_ID=?";
	// Insert Query
	public static final String INSERT_LOGIN_DETAILS = "INSERT INTO IGRS_USER_REG_DETAILS(FIRST_NAME,LAST_NAME,MIDDLE_NAME,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,GENDER,OCCUPATION,DOB,) VALUES(IGRS_ACTIVITY_MASTER_SEQ.nextval,?,?,'D')";
	public static final String SELECT_USER_ID="SELECT iurd.user_id,iurd.FIRST_NAME,iurd.MIDDLE_NAME," +
			"iurd.LAST_NAME,iurd.GENDER,iurd.PHONE_NUMBER,iurd.MOBILE_NUMBER,iurd.EMAIL_ID," +
			"iurd.ALTERNATE_EMAIL_ID,iurd.DOB,iurd.OCCUPATION,iurd.ADDRESS,iurd.COUNTRY_ID," +
			"iurd.STATE_ID,iurd.DISTRICT_ID,iurd.POSTAL_CODE, " +
			"icm.country_name, ism.state_name, idm.district_name  ,icm.h_country_name,ism.h_state_name,idm.h_district_name, " +
			"(SELECT CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID IN(SELECT CADRE_ID FROM IGRS_EMP_GRADE_CADRE_MAPPING "+
			"WHERE EMP_ID=?)) AS DES ,(SELECT H_CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID IN(SELECT CADRE_ID "+
			"FROM IGRS_EMP_GRADE_CADRE_MAPPING WHERE EMP_ID=?)) AS DESH "+
			"FROM IGRS_USER_REG_DETAILS iurd, igrs_country_master icm, igrs_state_master ism, " +
			"igrs_district_master idm WHERE USER_ID=? AND " +
			"iurd.country_id= icm.country_id AND iurd.state_id= ism.state_id AND " +
			"iurd.district_id= idm.district_id";
	
	public static final String APPROVE_UPDATE_USER="UPDATE IGRS_USER_REG_DETAILS SET USER_STATUS='A' WHERE USER_ID=?";
	public static final String REJECT_UPDATE_USER="UPDATE IGRS_USER_REG_DETAILS SET USER_STATUS='R' WHERE USER_ID=?";
	
	public static final String GET_EMP_DESIGNATION="SELECT CADRE_NAME,H_CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID IN(SELECT CADRE_ID FROM IGRS_EMP_GRADE_CADRE_MAPPING WHERE EMP_ID=?)";
}
