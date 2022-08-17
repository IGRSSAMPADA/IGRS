package com.wipro.igrs.caveatsMaster.sql;

/**
 * ===========================================================================
 * File : CommonSQL.java Description : Represents the SQL Class
 * 
 * Author : Piyush Created Date : Dec 04, 2007
 * 
 * ===========================================================================
 */
public class CommonSQL {

	public static final String CAVEATS_CHARGES_INSERT = "INSERT INTO IGRS_CAVEAT_TXN(TRANSACTION_ID,INSTITUTE_NAME,INSTITUTE_ADDRESS,INSTITUTE_REP_NAME,COUNTRY_ID,STATE_ID,DISTRICT_ID,POSTAL_CODE,PHONE_NUMBER,CAVEAT_TYPE_ID,STAY_ORDER_NO,STAY_ORDER_DETAILS,CAVEAT_DETAILS,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,CAVEAT_LOGGED_DATE,CREATED_BY,CREATED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY'),?,SYSDATE)";
	public static final String CAVEATS_CHARGES_INSERT_PROPERTYID = "INSERT INTO IGRS_CAVEAT_PROPERTY_MAP (CAVEAT_TXN_ID,PROPERTY_TXN_ID)VALUES(?,?)";

	public static final String IGRS_CAVEATS_CAVEAT_MASTER = "SELECT CAVEAT_TYPE_NAME,CAVEAT_TYPE_ID,STAY_ORDER_STATUS FROM IGRS_CAVEAT_TYPE_MASTER WHERE CAVEAT_TYPE_STATUS='A'";
	public static final String IGRS_COUNTRY_MASTER = "SELECT COUNTRY_ID,COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A'";
	public static final String IGRS_STATE_MASTER = "SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_STATUS='A'";
	public static final String IGRS_DISTRICT_MASTER = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A'";

	public static final String IGRS_CAVEATS_SEARCH_REFID = "SELECT TRANSACTION_ID,INSTITUTE_NAME,INSTITUTE_ADDRESS,INSTITUTE_REP_NAME,(SELECT COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_ID=IGRS_CAVEAT_TXN.COUNTRY_ID ),(SELECT STATE_NAME FROM IGRS_STATE_MASTER WHERE STATE_ID=IGRS_CAVEAT_TXN.STATE_ID ),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=IGRS_CAVEAT_TXN.DISTRICT_ID),POSTAL_CODE,PHONE_NUMBER,(SELECT CAVEAT_TYPE_NAME FROM IGRS_CAVEAT_TYPE_MASTER WHERE CAVEAT_TYPE_ID=IGRS_CAVEAT_TXN.CAVEAT_TYPE_ID),STAY_ORDER_NO,STAY_ORDER_DETAILS,CAVEAT_DETAILS,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY'),TO_CHAR(CAVEAT_RELEASE_DATE,'DD/MM/YYYY'),REASON_FOR_RELEASE,REMARKS_FOR_RELEASE FROM IGRS_CAVEAT_TXN WHERE TRANSACTION_ID=?";
	public static final String IGRS_CAVEATS_RELEASE_BY_REGNO = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE REGISTRATION_ID=? AND CAVEAT_STATUS='LOGGED'";
	public static final String IGRS_CAVEATS_RELEASE_BY_DURATION = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE CAVEAT_LOGGED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')AND CAVEAT_STATUS='LOGGED' ORDER BY TRANSACTION_ID ";
	public static final String IGRS_CAVEATS_UPDATE_RELEASE = "UPDATE IGRS_CAVEAT_TXN SET CAVEAT_STATUS='RELEASED', REASON_FOR_RELEASE=?, REMARKS_FOR_RELEASE=?, UPDATE_BY=?, CAVEAT_RELEASE_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY'),UPDATE_DATE=SYSDATE WHERE TRANSACTION_ID=?";

	// $11Query for Caveat Release by Bank
	public static final String IGRS_CAVEATS_BANK_RELEASE_REFID = "SELECT CAVEAT_BANK_TXN_ID,OTT_ID,LOAN_ACCOUNT_NO,TO_CHAR(LOAN_START_DATE,'DD/MM/YYYY'),TO_CHAR(LOAN_END_DATE,'DD/MM/YYYY'),LOAN_AMOUNT,LOAN_SECURED_AMOUNT,NO_OF_INSTALLMENTS,BANK_NAME,BANK_ADDRESS,BANK_REP_NAME,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY'),BANK_PERSON_CONTACT_NO,BANK_PERSON_EMAIL_ID FROM IGRS_CAVEAT_BANK_TXN WHERE CAVEAT_BANK_TXN_ID=?";
	public static final String IGRS_CAVEATS_BANK_RELEASE_REGNO = "SELECT REGISTRATION_ID,CAVEAT_STATUS,CAVEAT_BANK_TXN_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_BANK_TXN WHERE REGISTRATION_ID=? AND CAVEAT_STATUS='LOGGED'";
	public static final String IGRS_CAVEATS_BANK_RELEASE_DURATION = "SELECT REGISTRATION_ID,CAVEAT_STATUS,CAVEAT_BANK_TXN_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_BANK_TXN WHERE CAVEAT_LOGGED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')AND CAVEAT_STATUS='LOGGED' ORDER BY CAVEAT_BANK_TXN_ID";
	public static final String IGRS_CAVEATS_BANK_RELEASE_UPDATE = "UPDATE IGRS_CAVEAT_BANK_TXN SET CAVEAT_STATUS='RELEASED', REASON_FOR_RELEASE=?, REMARKS_FOR_RELEASE=?, UPDATE_BY=?,CAVEAT_RELEASE_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY'),UPDATE_DATE=SYSDATE WHERE CAVEAT_BANK_TXN_ID=?";
	// $11End
	// query without Property ID
	// public static final String IGRS_CAVEATS_SEARCH_REFID_VIEW="SELECT
	// TRANSACTION_ID,INSTITUTE_NAME,INSTITUTE_ADDRESS,INSTITUTE_REP_NAME,(SELECT
	// COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE
	// COUNTRY_ID=IGRS_CAVEAT_TXN.COUNTRY_ID ),(SELECT STATE_NAME FROM
	// IGRS_STATE_MASTER WHERE STATE_ID=IGRS_CAVEAT_TXN.STATE_ID ),(SELECT
	// DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE
	// DISTRICT_ID=IGRS_CAVEAT_TXN.DISTRICT_ID),POSTAL_CODE,PHONE_NUMBER,(SELECT
	// CAVEAT_TYPE_NAME FROM IGRS_CAVEAT_TYPE_MASTER WHERE
	// CAVEAT_TYPE_ID=IGRS_CAVEAT_TXN.CAVEAT_TYPE_ID),STAY_ORDER_NO,STAY_ORDER_DETAILS,CAVEAT_DETAILS,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY'),TO_CHAR(CAVEAT_RELEASE_DATE,'DD/MM/YYYY'),REASON_FOR_RELEASE,REMARKS_FOR_RELEASE
	// FROM IGRS_CAVEAT_TXN WHERE TRANSACTION_ID=?";
	public static final String IGRS_CAVEATS_SEARCH_REFID_VIEW = "SELECT TRANSACTION_ID,INSTITUTE_NAME,INSTITUTE_ADDRESS,INSTITUTE_REP_NAME,(SELECT COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_ID=IGRS_CAVEAT_TXN.COUNTRY_ID ),(SELECT STATE_NAME FROM IGRS_STATE_MASTER WHERE STATE_ID=IGRS_CAVEAT_TXN.STATE_ID ),(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=IGRS_CAVEAT_TXN.DISTRICT_ID),POSTAL_CODE,PHONE_NUMBER,(SELECT CAVEAT_TYPE_NAME FROM IGRS_CAVEAT_TYPE_MASTER WHERE CAVEAT_TYPE_ID=IGRS_CAVEAT_TXN.CAVEAT_TYPE_ID),STAY_ORDER_NO,STAY_ORDER_DETAILS,CAVEAT_DETAILS,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY'),TO_CHAR(CAVEAT_RELEASE_DATE,'DD/MM/YYYY'),REASON_FOR_RELEASE,REMARKS_FOR_RELEASE,(SELECT PROPERTY_TXN_ID FROM IGRS_CAVEAT_PROPERTY_MAP PM WHERE PM.CAVEAT_TXN_ID=TRANSACTION_ID) FROM IGRS_CAVEAT_TXN  WHERE TRANSACTION_ID=?";
	public static final String IGRS_CAVEATS_SEARCH_TXNID_VIEW = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE TRANSACTION_ID=?";
	public static final String IGRS_CAVEATS_SEARCH_REGNO_VIEW = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE REGISTRATION_ID=?";
	public static final String IGRS_CAVEATS_SEARCH_STATUS_VIEW = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE CAVEAT_STATUS=?";
	public static final String IGRS_CAVEATS_SEARCH_ALL_VIEW = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE TRANSACTION_ID=? OR REGISTRATION_ID=? OR CAVEAT_STATUS=? OR CAVEAT_LOGGED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') ORDER BY TRANSACTION_ID";
	public static final String IGRS_CAVEATS_SEARCH_DURATION_VIEW = "SELECT REGISTRATION_ID,CAVEAT_STATUS,TRANSACTION_ID,TO_CHAR(CAVEAT_LOGGED_DATE,'DD/MM/YYYY')FROM IGRS_CAVEAT_TXN WHERE CAVEAT_LOGGED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY') ORDER BY TRANSACTION_ID";

	public static final String IGRS_CAVEATS_CHECK_PIN = "SELECT REG_NO,PIN,CAVEATS FROM CAVEATS_REGISTRATION WHERE  REG_NO=? AND PIN=?";

	public static final String IGRS_CAVEATS_OTT_ACTIVE = "SELECT * FROM IGRS_CAVEATS_OTT WHERE  REG_NO=? AND OTT_STATUS='Active' ";
	public static final String IGRS_CAVEATS_OTT_INSERT = "INSERT INTO IGRS_CAVEATS_OTT(REG_NO,PIN,OTT,EXP_DAE_OF_OTT,OTT_STATUS,OTT_DATE)VALUES(?,?,?,TO_DATE(?,'DD/MM/YYYY'),?,TO_DATE(?,'DD/MM/YYYY'))";
	public static final String IGRS_CAVEATS_OTT_EXDAYS = "SELECT DAYS FROM IGRS_CAVEATS_OTT_EXDATE";
	public static final String IGRS_CAVEATS_OTT_SEARCH_BYBANK = "SELECT REGISTRATION_ID,OTT_TXN_ID FROM IGRS_REG_OTT_DETAILS WHERE REGISTRATION_ID=? AND OTT_TXN_ID=? AND OTT_STATUS='A'";
	public static final String IGRS_CAVEATS_OTT_UPDATE = "UPDATE IGRS_REG_OTT_DETAILS SET OTT_STATUS='D' WHERE REGISTRATION_ID=? AND OTT_TXN_ID=?";

	// (OLD)public static final String IGRS_CAVEATS_INSERT_BYBANK="INSERT INTO
	// IGRS_CAVEAT_BANK_TXN
	// (REG_NO,LOGGED_DATE,OTT,NAME_OF_INSTI,ADDRESS_OF_INSTI,CONTACT_NO_INSTI,NAME_OF_AUTH,MOBILE_NO_AUTH,EMAIL_OF_AUTH,EMAIL_OF_INSTI,LOAN_AMOUNT,SECURED_AMOUNT,INSTALL_AMOUNT,NO_OF_INSTALL,LOAN_DUEDATE)
	// VALUES(?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'))";

	public static final String IGRS_CAVEATS_INSERT_BYBANK = "INSERT INTO IGRS_CAVEAT_BANK_TXN (CAVEAT_BANK_TXN_ID,OTT_ID,LOAN_ACCOUNT_NO,LOAN_START_DATE,LOAN_END_DATE,LOAN_AMOUNT,LOAN_SECURED_AMOUNT,NO_OF_INSTALLMENTS,BANK_NAME,BANK_ADDRESS,BANK_REP_NAME,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,CAVEAT_LOGGED_DATE,BANK_PERSON_CONTACT_NO,BANK_PERSON_EMAIL_ID,CREATED_BY,CREATED_DATE)VALUES(?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,?,?,?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY'),?,?,?,SYSDATE)";

	// Query For Searching Registration ID from Registration Transaction Table.
	public static final String IGRS_CAVEATS_REGID_SEARCH_IN_REG_TABLE = "SELECT REGISTRATION_ID FROM IGRS_REG_COMPLETION_DETAILS WHERE REGISTRATION_ID=?";

	public static final String IGRS_CAVEATS_CAVEAT_MASTER_RETRIEVE_ALL = "SELECT CAVEAT_TYPE_ID,CAVEAT_TYPE_NAME,CAVEAT_TYPE_DESC,CAVEAT_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STAY_ORDER_STATUS FROM IGRS_CAVEAT_TYPE_MASTER WHERE CAVEAT_TYPE_STATUS not in('R')";// mona
	public static final String IGRS_CAVEATS_CAVEAT_MASTER_INSERT_ALL_FIELDS = "insert into  IGRS_CAVEAT_TYPE_MASTER(CAVEAT_TYPE_ID,CAVEAT_TYPE_NAME,CAVEAT_TYPE_DESC,CAVEAT_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,STAY_ORDER_STATUS) values('cav'||IGRS_CAVEAT_MASTER_SEQ.nextval,?,?,?,?,sysdate,?,sysdate,?)";
	public static final String IGRS_CAVEATS_CAVEAT_MASTER_GET_NAME = "SELECT * FROM IGRS_CAVEAT_TYPE_MASTER WHERE CAVEAT_TYPE_NAME like ? ";// mona
	public static final String IGRS_CAVEATS_CAVEAT_MASTER_UPDATE = "update  IGRS_CAVEAT_TYPE_MASTER set CAVEAT_TYPE_NAME=?, CAVEAT_TYPE_DESC=?,CAVEAT_TYPE_STATUS=?,UPDATE_BY=?,UPDATE_DATE=sysdate where CAVEAT_TYPE_ID=?";
	public static final String IGRS_CAVEATS_CAVEAT_MASTER_DELETE = "update IGRS_CAVEAT_TYPE_MASTER set CAVEAT_TYPE_STATUS='R'  where CAVEAT_TYPE_ID=?";
}