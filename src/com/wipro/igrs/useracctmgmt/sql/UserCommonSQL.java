/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	UserCommonSQL.java
 * Author		:	Rafiq Rahiman.T 
 * Date			: 14/03/2008
 */
package com.wipro.igrs.useracctmgmt.sql;

public class UserCommonSQL {
	//Select Query
	public static final String SELECT_APPL_DETAILS="SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,(SELECT OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER,IGRS_USER_REG_DETAILS WHERE OCCUPATION_ID = IGRS_USER_REG_DETAILS.OCCUPATION AND USER_ID =?),GUARDIAN_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,COUNTRY_ID,STATE_ID,DISTRICT_ID,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,ALTERNATE_EMAIL_ID,PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID,BANK_NAME,BANK_ADDRESS,AGE,USER_HINT_QUESTION_ID,USER_HINT_ANSWER,REGISTRATION_ID,(SELECT H_OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER,IGRS_USER_REG_DETAILS WHERE OCCUPATION_ID = IGRS_USER_REG_DETAILS.OCCUPATION  AND USER_ID=?),SIGNATURE_PATH FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
	public static final String SELECT_COUNTRY_LIST = "SELECT COUNTRY_ID FROM IGRS_COUNTRY_MASTER";
	public static final String SELECT_STATE_LIST = "SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER";
	public static final String SELECT_DISTRICT_LIST = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER";
	public static final String SELECT_PHOTO_PROOF_LIST = "SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME,H_PHOTO_PROOF_TYPE_NAME FROM IGRS_PHOTOID_PROOF_TYPE_MASTER";
	public static final String SELECT_HINT_LIST = "SELECT QUESTION_ID,QUESTION_TEXT,H_QUESTION_TEXT FROM IGRS_USER_HINT_QUESTIONS";
	public static final String SELECT_DOB_INTERNAL = "SELECT upper(TO_CHAR(DATE_OF_BIRTH,'dd-mon-yyyy')) FROM IGRS_EMP_MASTER WHERE EMP_ID=?";
	public static final String SELECT_USER_PASSWORD ="SELECT USER_PASSWORD FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
	
	public static final String SELECT_SP_DETAILS = "SELECT BANK_NAME,AUTHRZD_PERSN_NAME,DESIGNATION,LANGUAGE_KNOWN,QUALIFICATION,ADDRESS,IS_EXPERIENCED,IS_PUNISHED,AMOUNT,(select SP_TYPE_NAME from IGRS_SP_TYPE_MASTER where SP_TYPE_ID= sp.SP_TYPE_ID),(select SP_STATUS_NAME from IGRS_SP_STATUS_MASTER where SP_STATUS_ID=sp.APPLCTN_STATUS)"+
",(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = SP.DISTRICT_ID),(SELECT TEHSIL_NAME FROM "+
" IGRS_TEHSIL_MASTER WHERE TEHSIL_ID = SP.TEHSIL_ID),(SELECT WARD_PATWARI_NAME FROM IGRS_WARD_PATWARI_MASTER WHERE "+
" WARD_PATWARI_ID = SP.WARD_PATWARI_ID),(SELECT MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE MOHALLA_VILLAGE_ID = SP.MOHALLA_VILLAGE_ID) "+
",to_char(LICENSE_FROM_DATE,'DD/MM/YYYY'),to_char(LICENSE_TO_DATE,'DD/MM/YYYY') FROM IGRS_SP_DETLS SP WHERE SP.LICENSE_NUMBER=? AND SP.CREATED_BY = ?";
	
	public static String SELECT_EN_PO_DETAILS= "SELECT FIRST_NAME,LAST_NAME,AGE,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = UR.DISTRICT_ID),ADDRESS,nvl(PHONE_NUMBER,'-'),nvl(MOBILE_NUMBER,'-'),EMAIL_ID,(SELECT OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER WHERE OCCUPATION_ID = UR.OCCUPATION) " +
	
			" FROM IGRS_USER_REG_DETAILS UR WHERE UR.USER_ID =?";
	
	public static String SELECT_HI_PO_DETAILS= "SELECT FIRST_NAME,LAST_NAME,AGE,(SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = UR.DISTRICT_ID),ADDRESS,nvl(PHONE_NUMBER,'-'),nvl(MOBILE_NUMBER,'-'),EMAIL_ID,(SELECT H_OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER WHERE OCCUPATION_ID = UR.OCCUPATION) " +
	
	" FROM IGRS_USER_REG_DETAILS UR WHERE UR.USER_ID =?";
	
	public static String SELECT_DATES_DETAILS ="SELECT to_char(RIGHTS_FROM,'DD/MM/YYYY'),to_char(RIGHTS_TO,'DD/MM/YYYY') FROM IGRS_PO_RIGHTS_DURATION WHERE USER_ID = ? and RIGHTS_STATUS = ?";
	
	//public static String SELECT_CHECK_USERID ="SELECT USER_ID FROM IGRS_PO_RIGHTS_DURATION WHERE USER_ID = ?";
	
	public static final String SELECT_ROLE_GROUP="SELECT ROLE_GROUP_ID FROM IGRS_USER_ROLE_GROUP_MAPPING WHERE USER_ID =?";
	//UpdateQuery
	public static final String UPDATE_APPL_DETAILS="UPDATE IGRS_USER_REG_DETAILS SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,ADDRESS=?,GUARDIAN_NAME=?,STATE_ID=?,DISTRICT_ID=?,POSTAL_CODE=?,PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,ALTERNATE_EMAIL_ID=?,PHOTO_PROOF_ID=?,BANK_NAME=?,BANK_ADDRESS=?,USER_HINT_QUESTION_ID=?,USER_HINT_ANSWER=?,REGISTRATION_ID=?,SPOUSE_NAME=?,PHOTO_PROOF_TYPE_ID=?,COUNTRY_ID=?,SIGNATURE_PATH=? WHERE USER_ID=?";
	public static final String GET_LOCKED_USER="SELECT USER_ID,USER_STATUS FROM IGRS_USERS_LIST WHERE USER_STATUS='L' AND USER_ID =?";
	public static final String UNLOCK_USER="UPDATE IGRS_USERS_LIST SET USER_STATUS='A',NO_OF_ATTEMPT_COUNT='0',UPDATE_BY=?,UDPATE_DATE=SYSDATE WHERE USER_ID=?";
	
	public static final String UPDATE_USER_PASSWORD ="UPDATE IGRS_USER_REG_DETAILS SET USER_PASSWORD =? WHERE USER_ID=?";
	
	public static final String UPDATE_ROLEGROUP = "UPDATE IGRS_USER_ROLE_GROUP_MAPPING SET ROLE_GROUP_ID=? WHERE USER_ID = ?";
	
	public static final String UPDATE_USERLIST ="UPDATE IGRS_USERS_LIST SET USER_TYPE_ID=? WHERE USER_ID=?";

	public static final String UPDATE_RIGHTS_DURATION = "UPDATE IGRS_PO_RIGHTS_DURATION SET RIGHTS_FROM = to_date(?,'DD/MM/YYYY'),RIGHTS_TO = to_date(?,'DD/MM/YYYY'),UPDATED_BY=?,UPDATED_DATE=SYSDATE WHERE USER_ID=?";
	
	public static final String REVOKE_PROCEDURE = "CALL " +"IGRS_REVOKE_PORIGHTS(?)";
	
	public static final String GET_ROLEGROUP_ID = "select ROLE_GROUP_ID from IGRS_ROLE_GROUP_MASTER where ROLE_GROUP_NAME = ?";
	
	public static final String GET_USERTYPE_ID = "select USER_TYPE_ID from IGRS_USER_TYPE_MASTER where USER_TYPE_NAME = ? ";
	public static final String UPDATE_APPL_DETAILS_URL="UPDATE IGRS_USER_REG_DETAILS SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,ADDRESS=?,GUARDIAN_NAME=?,STATE_ID=?,DISTRICT_ID=?,POSTAL_CODE=?,PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,ALTERNATE_EMAIL_ID=?,PHOTO_PROOF_ID=?,BANK_NAME=?,BANK_ADDRESS=?,USER_HINT_QUESTION_ID=?,USER_HINT_ANSWER=?,REGISTRATION_ID=?,SPOUSE_NAME=?,PHOTO_PROOF_TYPE_ID=?,COUNTRY_ID=? WHERE USER_ID=?";
	
	//insert query
	
	public static final String INSERT_PO_DURATION="INSERT INTO IGRS_PO_RIGHTS_DURATION (RIGHTS_SEQUENCE,RIGHTS_FROM,RIGHTS_TO,USER_ID,RIGHTS_STATUS,CREATED_BY,CREATED_DATE) VALUES(?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),?,?,?,sysdate)";
	
	public static final String GET_USER_TYPE= "SELECT USER_TYPE_ID FROM IGRS_USERS_LIST WHERE USER_ID=?";
	
	//Added By Praveen : Start
	public static final String Registered_User_Details=" select FIRST_NAME, NVL(MIDDLE_NAME,'-'), LAST_NAME, GENDER, AGE, (SELECT OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER WHERE OCCUPATION_ID = UR.OCCUPATION), Guardian_name, NVL(Mother_Name,'-'), NVL(spouse_name,'-'), (select category_name from IGRS_Person_category_Master where category_id=UR.category_id),MINORITY, ADDRESS, (SELECT Country_Name FROM IGRS_COUNTRY_MASTER where country_id=UR.Country_id),	(SELECT STATE_NAME FROM IGRS_STATE_MASTER where STATE_ID=UR.state_id),  (SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = UR.DISTRICT_ID),NVL(to_char(POSTAL_CODE),'-'),NVL(PHONE_NUMBER,'-'), MOBILE_NUMBER, EMAIL_ID, NVL(ALTERNATE_EMAIL_ID,'-')	from  IGRS_USER_REG_DETAILS UR WHERE UR.state_id=? and  USER_ID =?";
	public static final String Registered_User_Details_hindi=" select FIRST_NAME, NVL(MIDDLE_NAME,'-'), LAST_NAME, GENDER, AGE, (SELECT H_OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER WHERE OCCUPATION_ID = UR.OCCUPATION), Guardian_name, NVL(Mother_Name,'-'), NVL(spouse_name,'-'),(select H_CATEGORY_NAME from IGRS_Person_category_Master where category_id=UR.category_id),MINORITY, ADDRESS, (SELECT H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER where country_id=UR.Country_id),	(SELECT H_STATE_NAME FROM IGRS_STATE_MASTER where STATE_ID=UR.state_id),  (SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = UR.DISTRICT_ID),NVL(to_char(POSTAL_CODE),'-'),NVL(PHONE_NUMBER,'-'), MOBILE_NUMBER, EMAIL_ID, NVL(ALTERNATE_EMAIL_ID,'-')	from  IGRS_USER_REG_DETAILS UR WHERE UR.state_id=? and  USER_ID =?";
	public static final String Tehsil_list="select tehsil_id, tehsil_name from IGRS_tehsil_MASTER where district_id=(select district_id from IGRS_USER_REG_DETAILS where user_id=?)";
	public static final String Tehsil_list_hindi="select tehsil_id, H_tehsil_name from IGRS_tehsil_MASTER where district_id=(select district_id from IGRS_USER_REG_DETAILS where user_id=?)";
	// Added tehsil id ,document name ,document path
	public static String SELECT_DATES_DETAILS_RU ="SELECT to_char(RIGHTS_FROM,'DD/MM/YYYY'),to_char(RIGHTS_TO,'DD/MM/YYYY'),TEHSIL_ID ,FILEPATH, FILENAME FROM IGRS_TEHSILDAR_RIGHTS_DURATION WHERE USER_ID =? and RIGHTS_STATUS = ? and IsTehsildar=?";
	public static final String UPDATE_Tehsildar_RIGHTS_DURATION = "UPDATE IGRS_TEHSILDAR_RIGHTS_DURATION SET RIGHTS_FROM = to_date(?,'DD/MM/YYYY'),RIGHTS_TO = to_date(?,'DD/MM/YYYY'),UPDATED_BY=?,UPDATED_DATE=SYSDATE, TEHSIL_ID=?,FILEPATH=? WHERE USER_ID=? AND ISTEHSILDAR=? AND Rights_status=?";
	public static final String REVOKE_TEHSILDAR_RIGHTS = "CALL " +"IGRS_REVOKE_TEHSILDAR_RIGHTS(?)";
	public static final String INSERT_Tehsildar_DURATION="INSERT INTO IGRS_TEHSILDAR_RIGHTS_DURATION (RIGHTS_SEQUENCE,RIGHTS_FROM,RIGHTS_TO,USER_ID,RIGHTS_STATUS,CREATED_BY,CREATED_DATE, ISTEHSILDAR, TEHSIL_ID,FILEPATH, FILENAME) VALUES(?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),?,?,?,sysdate,?,?,?,?)";
	public static final String GET_LOGGED_USER_DISTRICT="SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID IN (select DISTRICT_ID from igrs_office_master where office_id =?)";
	public static final String GET_LOGGED_USER_DISTRICT_HINDI="SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID IN (select DISTRICT_ID from igrs_office_master where office_id =?)";
	//End
}
