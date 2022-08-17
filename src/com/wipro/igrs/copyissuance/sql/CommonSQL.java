package com.wipro.igrs.copyissuance.sql;

/**
 * ===========================================================================
 * File : CommonSQL.java Description : Represents the SQL Class
 * 
 * Author : Dev Pradhan Created Date : Jan 07, 2008
 * 
 * ===========================================================================
 */
public class CommonSQL {

	/*
	 * public static final String REQUISITE_FEE_QUERY =
	 * "SELECT PARAM_VALUE FROM IGRS_SERVICE_FEE_PARAM_MAPPING WHERE PARAM_ID='COPY_ISSUANCE'"
	 * ;
	 */
	public static final String GET_COUNTRY = "select country_name from igrs_country_master where country_id=?";
	public static final String GET_STATE = "select state_name from igrs_state_master where state_id=?";
	public static final String GET_DISTRICT = "select district_name from igrs_district_master where district_id=?";
	public static final String STATUS_QRY = "select decode(TRANSACTION_STATUS,'D','Dispatched','Un-delivered') from IGRS_CERTIFIED_COPY_TXN where transaction_id=? ";

	public static final String REQUISITE_OTHER_FEE_QUERY = "SELECT PARAM_VALUE FROM IGRS_SERVICE_FEE_PARAM_MAPPING WHERE PARAM_ID='COPY_OTHER'";

	/*
	 * public static final String REQUISITE_FEE_QUERY =
	 * "SELECT PARAM_VALUE FROM IGRS_SERVICE_FEE_PARAM_MAPPING WHERE FUNCTION_ID ='FUN_036' AND PARAM_ID ='1'"
	 * ;
	 * 
	 * public static final String REQUISITE_OTHER_FEE_QUERY =
	 * "SELECT PARAM_VALUE FROM IGRS_SERVICE_FEE_PARAM_MAPPING WHERE FUNCTION_ID ='FUN_036' AND PARAM_ID ='2'"
	 * ;
	 */

	public static final String COPY_ISSUANCE_INSERT = " INSERT INTO IGRS_CERCOPY_TXN_PARTY_DETAILS(CER_COPY_TXN_ID,PARTY_TYPE_ID,"
			+ " PARTY_FIRST_NAME,PARTY_MIDDLE_NAME,PARTY_LAST_NAME,PARTY_GENDER,PARTY_AGE,"
			+ " NATIONALITY,COUNTRY_ID,STATE_ID,DISTRICT_ID,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,"
			+ " MOBILE_NUMBER,EMAIL_ID,PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID,BANK_NAME,BANK_ADDRESS,"
			+ " ORGANIZATION_NAME,GUARDIAN_NAME,MOTHER_NAME,CATEGORY_ID,CASTE_ID,RELIGION_ID,"
			+ " PARTY_THUMB_IMPRESSION,PARTY_PHOTO,PARTY_SIGNATURE,FEE,OTHERS,TOTAL,REMARKS,FIRST_NAME, "
			+ " MIDDLE_NAME,LAST_NAME,SPOUSE_NAME,APPLICANT_GUARDIAN_NAME,APPLICANT_MOTHER_NAME,REG_DATE,DOC_DATE,"
			+ " VOLUME_NO,BOOK_NUM,OLD_REG_NUM,OLD_REG_DT,APPLICANT_SPOUSE,CREATED_BY ) "
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

	public static final String COPY_MODIFY_ISSUANCE = ""
			+ "UPDATE IGRS_CERCOPY_TXN_PARTY_DETAILS SET PARTY_TYPE_ID=?,PARTY_FIRST_NAME=?,PARTY_MIDDLE_NAME=?,"
			+ "PARTY_LAST_NAME=?,PARTY_GENDER=?,PARTY_AGE=?,NATIONALITY=?,COUNTRY_ID=?,STATE_ID=?,"
			+ "DISTRICT_ID=?,PARTY_ADDRESS=?,POSTAL_CODE=?,PHONE_NUMBER=?,MOBILE_NUMBER=?,EMAIL_ID=?,"
			+ "PHOTO_PROOF_TYPE_ID=?,PHOTO_PROOF_ID=?,BANK_NAME=?,BANK_ADDRESS=?,"
			+ " ORGANIZATION_NAME=?,GUARDIAN_NAME=?,MOTHER_NAME=?,CATEGORY_ID=?,CASTE_ID=?,RELIGION_ID=?,"
			+ " PARTY_THUMB_IMPRESSION=?,PARTY_PHOTO=?,PARTY_SIGNATURE=?,FEE=?,OTHERS=?,TOTAL=?,REMARKS=?,FIRST_NAME=?, "
			+ " MIDDLE_NAME=?,LAST_NAME=?,SPOUSE_NAME=?,APPLICANT_GUARDIAN_NAME=?,APPLICANT_MOTHER_NAME=?,REG_DATE=?,DOC_DATE=?,"
			+ " VOLUME_NO=?,BOOK_NUM=?,OLD_REG_NUM=?,OLD_REG_DT=?,APPLICANT_SPOUSE=? WHERE CER_COPY_TXN_ID=? ";

	public static final String COPY_ISSUANCE_TXN = " INSERT INTO IGRS_CERTIFIED_COPY_TXN(TRANSACTION_ID,REGISTRATION_ID, "
			+ " FUNCTION_ID,PURPOSE_OF_REQUEST,TRANSACTION_STATUS,CREATED_BY,CREATED_DATE,"
			+ " TXN_ID_STATUS,TYPE_REQ,DOC_TYPE)"
			+ " VALUES(?,?,?,?,?,?,SYSDATE,?,?,?) ";

	public static final String MODIFY_COPY_TXN = "UPDATE IGRS_CERTIFIED_COPY_TXN SET REGISTRATION_ID=?, "
			+ " PURPOSE_OF_REQUEST=?,DOC_TYPE=? WHERE TRANSACTION_ID=?";

	public static final String COPY_PAYMENT_DTLS = "INSERT INTO IGRS_CERT_COPY_PAYMENT_DTLS(CERT_PAYMENT_ID,"
			+ "CERT_TXN_ID,FUNCTION_ID,PAYABLE_AMOUNT,PAYMENT_FLAG,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?,?,SYSDATE)";

	/*
	 * public static final String COPY_ISSUANCE_QUERY =
	 * "SELECT A.CER_COPY_TXN_ID,NVL(A.PARTY_FIRST_NAME,' '), " +
	 * " NVL(A.PARTY_MIDDLE_NAME,' '),NVL(A.PARTY_LAST_NAME,' '), NVL(A.PARTY_GENDER,' '), "
	 * +
	 * " decode(nvl(A.PARTY_AGE,0),0,' ',A.PARTY_AGE) ,NVL(A.GUARDIAN_NAME,' '),NVL(A.MOTHER_NAME,' '), "
	 * +
	 * " NVL(A.PARTY_ADDRESS,' '),NVL(C.DISTRICT_NAME,' '), NVL(A.COUNTRY_ID,' '),NVL(A.STATE_ID,' '), "
	 * +
	 * " decode(nvl(A.POSTAL_CODE,0),0,' ',A.POSTAL_CODE),NVL(A.PHONE_NUMBER,' '), "
	 * +
	 * " NVL(A.MOBILE_NUMBER,' '),NVL(A.EMAIL_ID,' '),NVL(A.PHOTO_PROOF_ID,' '), "
	 * + " NVL(A.PHOTO_PROOF_TYPE_ID,' '),A.FEE,A.OTHERS,A.TOTAL, " +
	 * " NVL(A.REMARKS,' '),B.REGISTRATION_ID,NVL(B.TYPE_REQ,' '),NVL(B.PURPOSE_OF_REQUEST,' '), "
	 * +
	 * " B.CREATED_DATE,decode(B.TRANSACTION_STATUS,'D','Dispatched','Un-delivered'),B.UPDATE_DATE "
	 * +
	 * " FROM IGRS_CERCOPY_TXN_PARTY_DETAILS A,IGRS_CERTIFIED_COPY_TXN B, IGRS_DISTRICT_MASTER C WHERE A.CER_COPY_TXN_ID= ? "
	 * +
	 * " AND A.DISTRICT_ID= c.district_id AND C.district_status = 'A' AND A.cer_copy_txn_id = B.transaction_id "
	 * ;
	 */
	public static final String COPY_ISSUANCE_QUERY = " SELECT A.CER_COPY_TXN_ID,NVL(A.PARTY_FIRST_NAME,' '),  NVL(A.PARTY_MIDDLE_NAME,' '),NVL(A.PARTY_LAST_NAME,' '), "
			+ " NVL(A.PARTY_GENDER,' '),  decode(nvl(A.PARTY_AGE,0),0,' ',A.PARTY_AGE) ,NVL(A.GUARDIAN_NAME,' '), "
			+ " NVL(A.MOTHER_NAME,' '),  NVL(A.PARTY_ADDRESS,' '),NVL(C.DISTRICT_NAME,' '), NVL(CM.COUNTRY_NAME,' '), "
			+ " NVL(S.STATE_NAME,' '),  decode(nvl(A.POSTAL_CODE,0),0,' ',A.POSTAL_CODE),NVL(A.PHONE_NUMBER,' '),   "
			+ " NVL(A.MOBILE_NUMBER,' '),NVL(A.EMAIL_ID,' '),NVL(A.PHOTO_PROOF_ID,' '),  NVL(A.PHOTO_PROOF_TYPE_ID,' '), "
			+ " A.FEE,A.OTHERS,A.TOTAL,  NVL(A.REMARKS,' '),B.REGISTRATION_ID,NVL(B.TYPE_REQ,' '),NVL(B.PURPOSE_OF_REQUEST,' '),   "
			+ " B.CREATED_DATE,decode(B.TRANSACTION_STATUS,'D','Dispatched','Un-delivered'),B.UPDATE_DATE  , "
			+ " B.payment_txn_id,B.PAID_AMOUNT,decode(B.PAYMENT_MODE_ID,'IGRS_PT_001','Cash','IGRS_PT_002','Challan','NA') ,B.DOC_TYPE,B.POSTAL_TRACKING_NUMBER,to_char(B.DISPATCH_DT,'DD/MM/YYYY'),A.BANK_NAME,A.BANK_ADDRESS , "
			+ " A.first_name,A.middle_name,A.last_name,A.spouse_name,A.applicant_guardian_name,A.applicant_mother_name ,A.VOLUME_NO,A.BOOK_NUM,A.OLD_REG_NUM,to_char(A.OLD_REG_DT,'dd/mm/yyyy'),A.DOC_PATH "
			+ " FROM IGRS_CERCOPY_TXN_PARTY_DETAILS A,IGRS_CERTIFIED_COPY_TXN B, IGRS_DISTRICT_MASTER C,IGRS_COUNTRY_MASTER CM,IGRS_STATE_MASTER S "
			+ " WHERE A.CER_COPY_TXN_ID= ?  AND  "
			+ " A.DISTRICT_ID= c.district_id AND CM.COUNTRY_ID=A.COUNTRY_ID AND  "
			+ " a.state_id= S.state_id AND "
			+ " C.district_status = 'A' AND  "
			+ " A.cer_copy_txn_id = B.transaction_id ";
	
	public static final String COPY_ISSUANCE_QUERY_HINDI = " SELECT A.CER_COPY_TXN_ID,NVL(A.PARTY_FIRST_NAME,' '),  NVL(A.PARTY_MIDDLE_NAME,' '),NVL(A.PARTY_LAST_NAME,' '), "
		+ " NVL(A.PARTY_GENDER,' '),  decode(nvl(A.PARTY_AGE,0),0,' ',A.PARTY_AGE) ,NVL(A.GUARDIAN_NAME,' '), "
		+ " NVL(A.MOTHER_NAME,' '),  NVL(A.PARTY_ADDRESS,' '),NVL(C.H_DISTRICT_NAME,' '), NVL(CM.H_COUNTRY_NAME,' '), "
		+ " NVL(S.H_STATE_NAME,' '),  decode(nvl(A.POSTAL_CODE,0),0,' ',A.POSTAL_CODE),NVL(A.PHONE_NUMBER,' '),   "
		+ " NVL(A.MOBILE_NUMBER,' '),NVL(A.EMAIL_ID,' '),NVL(A.PHOTO_PROOF_ID,' '),  NVL(A.PHOTO_PROOF_TYPE_ID,' '), "
		+ " A.FEE,A.OTHERS,A.TOTAL,  NVL(A.REMARKS,' '),B.REGISTRATION_ID,NVL(B.TYPE_REQ,' '),NVL(B.PURPOSE_OF_REQUEST,' '),   "
		+ " B.CREATED_DATE,decode(B.TRANSACTION_STATUS,'D','Dispatched','Un-delivered'),B.UPDATE_DATE  , "
		+ " B.payment_txn_id,B.PAID_AMOUNT,decode(B.PAYMENT_MODE_ID,'IGRS_PT_001','Cash','IGRS_PT_002','Challan','NA') ,B.DOC_TYPE,B.POSTAL_TRACKING_NUMBER,to_char(B.DISPATCH_DT,'DD/MM/YYYY'),A.BANK_NAME,A.BANK_ADDRESS , "
		+ " A.first_name,A.middle_name,A.last_name,A.spouse_name,A.applicant_guardian_name,A.applicant_mother_name ,A.VOLUME_NO,A.BOOK_NUM,A.OLD_REG_NUM,to_char(A.OLD_REG_DT,'dd/mm/yyyy'),A.DOC_PATH  "
		+ " FROM IGRS_CERCOPY_TXN_PARTY_DETAILS A,IGRS_CERTIFIED_COPY_TXN B, IGRS_DISTRICT_MASTER C ,IGRS_COUNTRY_MASTER CM,IGRS_STATE_MASTER S "
		+ " WHERE A.CER_COPY_TXN_ID= ?  AND  "
		+ " A.DISTRICT_ID= c.district_id AND CM.COUNTRY_ID=A.COUNTRY_ID AND "
		+ " a.state_id= S.state_id AND "
		+ " C.district_status = 'A' AND  "
		+ " A.cer_copy_txn_id = B.transaction_id ";

	public static final String ISSUANCE_QUERY = "SELECT nvl(TYPE_REQ,' '),CREATED_DATE,REGISTRATION_ID,TRANSACTION_ID,TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN WHERE";

	public static final String ISSUANCE_STATUS_QUERY = "SELECT * FROM IGRS_CERCOPY_TXN_PARTY_DETAILS WHERE CER_COPY_TXN_ID LIKE 'CC%' ";

	public static final String ISSUANCE_ONLINE_QUERY = "SELECT nvl(TYPE_REQ,' '),to_char(CREATED_DATE,'DD/MM/YYYY'),REGISTRATION_ID,TRANSACTION_ID,TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN WHERE 1=1 AND TRANSACTION_STATUS!='U' AND TYPE_REQ='Certified Copy' ";
	public static final String ISSUANCE_ONLINE_QUERY1 = "SELECT nvl(CT.TYPE_REQ,' '),to_char(CT.CREATED_DATE,'DD/MM/YYYY'),CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN CT WHERE CT.TYPE_REQ='Certified Copy' AND ";
	public static final String ISSUANCE_ONLINE_QUERY8 = "SELECT nvl(TYPE_REQ,' '),to_char(CREATED_DATE,'DD/MM/YYYY'),REGISTRATION_ID,TRANSACTION_ID,TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN WHERE TYPE_REQ='No-Encumbrance' AND ";

	public static final String ISSUANCE_ONLINE_QUERY_CERID = "AND TRANSACTION_ID=?";
	public static final String ISSUANCE_ONLINE_QUERY_REGID = "AND REGISTRATION_ID=?";
	public static final String ISSUANCE_ONLINE_QUERY_STATUS = "AND TRANSACTION_STATUS=?";
	public static final String ISSUANCE_ONLINE_QUERY_DATE = "AND CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1";

	public static final String ISSUANCE_ONLINE_QUERY_OLD = "SELECT nvl(txn.TYPE_REQ,' '),to_char(txn.CREATED_DATE,'DD/MM/YYYY'),txn.REGISTRATION_ID,txn.TRANSACTION_ID,txn.TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN txn,IGRS_CERCOPY_TXN_PARTY_DETAILS pd WHERE  and ";

	public static final String ISSUANCE_REQUEST_UPDATE_QUERY = "SELECT nvl(TYPE_REQ,' '),to_char(CREATED_DATE,'dd/mm/yyyy'),REGISTRATION_ID,TRANSACTION_ID,TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN WHERE TYPE_REQ='Certified Copy' AND ";

	public static final String ISSUANCE_REQUEST_UPDATE_QUERY8 = "SELECT nvl(TYPE_REQ,' '),to_char(CREATED_DATE,'dd/mm/yyyy'),REGISTRATION_ID,TRANSACTION_ID,TRANSACTION_STATUS FROM IGRS_CERTIFIED_COPY_TXN WHERE TYPE_REQ='No-Encumbrance' AND";

	public static final String ISSUANCE_UPDATE_QUERY = "UPDATE IGRS_CERCOPY_TXN_PARTY_DETAILS SET REMARKS=?,DOC_PATH=? WHERE CER_COPY_TXN_ID=? ";

	public static final String ISSUANCE_DELIVERY_QUERY = "UPDATE IGRS_CERTIFIED_COPY_TXN SET TRANSACTION_STATUS=?,POSTAL_TRACKING_NUMBER=?, DISPATCH_DT=? , UPDATE_DATE=SYSDATE WHERE TRANSACTION_ID=?";

	public static final String COPY_UPDATE_QUERY = "UPDATE IGRS_CERCOPY_TXN_PARTY_DETAILS SET REMARKS=? WHERE CER_COPY_TXN_ID=?";

	public static final String COPY_DATE_QUERY = "UPDATE IGRS_CERTIFIED_COPY_TXN SET UPDATE_DATE=SYSDATE WHERE TRANSACTION_ID=?";

	public static final String ISSUANCE_CHALLAN_PAYMENT_INSERT = "INSERT INTO IGRS_PAYMENT_CHALLAN_DETAILS (CHALLAN_ID,CHALLAN_AMOUNT,CHALLAN_DATE) VALUES(?,?,SYSDATE)";

	public static final String ISSUANCE_CHALLAN_PAYMENT_VALIDATE = "SELECT CHALLAN_ONLINE_NUMBER,CHALLAN_ONLINE_DATE,NET_AMOUNT,BANK_NAME,TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS ";

	public static final String PAYMENT_QUERY = "SELECT P.CHALLAN_ONLINE_NUMBER,P.CHALLAN_ONLINE_DATE,P.NET_AMOUNT,C.CREATED_DATE FROM IGRS_PAYMENT_MODE_DETAILS P, IGRS_CERTIFIED_COPY_TXN C WHERE P.TRANSACTION_ID=C.PAYMENT_TXN_ID ";

	public static final String CHALLAN_QUERY = "SELECT TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS ";

	/*public static final String COPY_ISSUANCE_FEE_QUERY = " SELECT IMAP.FUNCTION_ID,IMAP.PARAM_VALUE,IMAS.PARAM_NAME"
			+ " FROM IGRS_SERVICE_FEE_PARAM_MAPPING IMAP,IGRS_SERVICE_FEE_PARAM_MASTER IMAS"
			+ " WHERE PARENT_ID IS NULL  AND IMAS.PARAM_ID = IMAP.PARAM_ID AND FUNCTION_ID=";*/

	public static final String COPY_ISSUANCE_FEE_QUERY_SP="select FUNCTION_ID,FEE_SP from IGRS_FUNCTION_SERVICE_MAPPING where FUNCTION_ID=";
	public static final String COPY_ISSUANCE_FEE_QUERY_RU="select FUNCTION_ID,FEE_RU from IGRS_FUNCTION_SERVICE_MAPPING where FUNCTION_ID=";;
	public static final String COPY_ISSUANCE_FEE_QUERY_INTERNAL="select FUNCTION_ID,FEE_DR from IGRS_FUNCTION_SERVICE_MAPPING where FUNCTION_ID=";;
	public static final String GET_FUNID_QUERY = " select fm.function_name, mm.module_name "
			+ " from igrs_function_master fm ,"
			+ " igrs_sub_module_master sm,"
			+ " igrs_module_master mm "
			+ " where "
			+ " fm.sub_module_id= sm.sub_module_id   AND "
			+ " sm.module_id= mm.module_id AND " + " fm.function_id=? ";
	
	public static final String GET_FUNID_QUERY_HINDI = " select fm.function_name_h, mm.h_module_name "
		+ " from igrs_function_master fm ,"
		+ " igrs_sub_module_master sm,"
		+ " igrs_module_master mm "
		+ " where "
		+ " fm.sub_module_id= sm.sub_module_id   AND "
		+ " sm.module_id= mm.module_id AND " + " fm.function_id=? ";

	public static final String TXN_PAYMENT_UPDATE = "UPDATE IGRS_CERTIFIED_COPY_TXN set PAYMENT_FLAG=?,TRANSACTION_STATUS='P' WHERE TRANSACTION_ID =?";
	public static final String ICOPY_PYMNT_UPDATE = " UPDATE IGRS_CERT_COPY_PAYMENT_DTLS set PAYMENT_FLAG=? WHERE "
			+ " CERT_TXN_ID =? AND PAYMENT_FLAG=? ";
	public static final String PYMNT_UPDATE = " UPDATE IGRS_CERT_COPY_PAYMENT_DTLS set PAYMENT_FLAG=? WHERE "
			+ " CERT_TXN_ID =? ";

	public static final String SEARCH_QUERY = "SELECT REGISTRATION_ID,TO_CHAR(REGISTRATION_DATE, 'dd/MM/yyyy') FROM IGRS_PROPERTY_REGISTRATION";
	public static final String SEARCH_QUERY_CERCOPY = "SELECT RT.REGISTRATION_NUMBER,TO_CHAR(RT.CREATED_DATE,'DD/MM/YYYY') CREATED_DATE, (SELECT DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_TYPE_ID=RT.DEED_ID) DEED_TYPE,RT.DEED_ID FROM IGRS_REG_TXN_DETLS RT ";

	public static final String FLOOR_MASTER_QUERY = "SELECT FLOOR_ID,FLOOR_NAME FROM IGRS_FLOOR_MASTER WHERE UPPER(FLOOR_STATUS)='A' ";
	public static final String FLOOR_MASTER_QUERY_HINDI = "SELECT FLOOR_ID,H_FLOOR_NAME FROM IGRS_FLOOR_MASTER WHERE UPPER(FLOOR_STATUS)='A' ";

	public static final String FLOORDTLS_VIEW_QUERY = " SELECT FM.FLOOR_NAME,FD.TOTAL_AREA,FD.CONSTRUCTED_AREA "
			+ " FROM IGRS_REG_INIT_PROP_FLOOR_DTLS FD ,IGRS_FLOOR_MASTER   FM "
			+ " WHERE  "
			+ " FD.FLOOR_ID=FM.FLOOR_ID AND "
			+ " PROPERTY_TXN_ID= ? ";

	public static final String PIN_SEARCH_QUERY = " SELECT PN.PIN_NUMBER AS PIN  "
			+ " FROM   "
			+ " IGRS_REG_COMPLETION_DETAILS CD,   "
			+ " IGRS_REG_PROPERTY_DETAILS PD,  "
			+ " IGRS_REG_PIN_DETAILS PN   "
			+ " WHERE   "
			+ " CD.REGISTRATION_ID = PD.REG_TXN_ID AND  "
			+ " PD.PROPERTY_TXN_ID = PN.PROPERTY_TXN_ID AND   "
			+ " PN.REGISTRATION_ID = CD.TRANSACTION_ID AND  ";

	public static final String PENDING_APPLICATION = "SELECT WT.TRANSACTION_ID,  WP.PAID_AMOUNT,(WP.PAYABLE_AMOUNT-WP.PAID_AMOUNT) DUE_AMOUNT,  WP.UPDATE_DATE,TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE,WP.PAYABLE_AMOUNT"
			+ " FROM IGRS_CERTIFIED_COPY_TXN WT,(SELECT IGRS_CERT_COPY_PAYMENT_DTLS.CERT_TXN_ID,SUM(IGRS_CERT_COPY_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT,MAX(IGRS_CERT_COPY_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT,TO_CHAR(MAX(IGRS_CERT_COPY_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATE_DATE"
			+ " FROM IGRS_CERT_COPY_PAYMENT_DTLS where IGRS_CERT_COPY_PAYMENT_DTLS.PAYMENT_FLAG !='C' GROUP BY IGRS_CERT_COPY_PAYMENT_DTLS.CERT_TXN_ID) WP WHERE WP.CERT_TXN_ID = WT.TRANSACTION_ID AND WT.TRANSACTION_STATUS = 'U' AND WT.TYPE_REQ='Certified Copy' AND WT.CREATED_BY=?";

	public static final String PENDING_APPLICATION_DETAILS = "SELECT *  FROM  IGRS_CER_COPY_TXN_PARTY_DETAILS WHERE CER_COPY_TXN_ID=?";

	public static final String PENDING_BALANCE_DETAILS = "SELECT (TO_NUMBER(WP.PAYABLE_AMOUNT)-nvl((WP.PAID_AMOUNT),'0'))DUE_AMOUNT "
			+ " FROM IGRS_CERTIFIED_COPY_TXN WT,(SELECT IGRS_CERT_COPY_PAYMENT_DTLS.CERT_TXN_ID,SUM(IGRS_CERT_COPY_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT,MAX(IGRS_CERT_COPY_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT,TO_CHAR(MAX(IGRS_CERT_COPY_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATE_DATE"
			+ " FROM IGRS_CERT_COPY_PAYMENT_DTLS GROUP BY IGRS_CERT_COPY_PAYMENT_DTLS.CERT_TXN_ID) WP WHERE WP.CERT_TXN_ID = WT.TRANSACTION_ID AND WT.TRANSACTION_ID=?";
	public static final String PRESENT_PAYMENT_ENTRY = "select payment_flag,cert_payment_id from igrs_cert_copy_payment_dtls where cert_payment_id=(select max(cert_payment_id) from igrs_cert_copy_payment_dtls where CERT_TXN_ID=?)";

	public static final String COPY_DETAILS = "SELECT CP.CER_COPY_TXN_ID,CP.PARTY_TYPE_ID,CP.PARTY_FIRST_NAME,CP.PARTY_MIDDLE_NAME,"
			+ "CP.PARTY_LAST_NAME,CP.PARTY_GENDER,CP.PARTY_AGE,CP.NATIONALITY,(select country_name from igrs_country_master where country_id=cp.country_id ) country,(select state_name from igrs_state_master where state_id=cp.state_id) state,(select district_name from igrs_district_master where district_id=cp.district_id) district,CP.PARTY_ADDRESS,CP.POSTAL_CODE,"
			+ "CP.PHONE_NUMBER,CP.MOBILE_NUMBER,CP.EMAIL_ID,CP.PHOTO_PROOF_TYPE_ID,CP.PHOTO_PROOF_ID,	CP.BANK_NAME,CP.BANK_ADDRESS,CP.ORGANIZATION_NAME,CP.GUARDIAN_NAME,CP.MOTHER_NAME,"
			+ "CP.CATEGORY_ID,CP.CASTE_ID,CP.RELIGION_ID,CP.PARTY_THUMB_IMPRESSION,CP.PARTY_PHOTO,CP.PARTY_SIGNATURE,CP.REMARKS,CP.TOTAL,CP.OTHERS,"
			+ "CP.FEE,CP.PARTY_THUMB_IMPRESSION_ID,CP.PARTY_PHOTO_ID,CP.PARTY_SIGNATURE_ID,CP.REG_DATE,CP.FIRST_NAME,	CP.MIDDLE_NAME,CP.LAST_NAME,"
			+ "CP.SPOUSE_NAME,CP.APPLICANT_GUARDIAN_NAME,CP.APPLICANT_MOTHER_NAME,CP.DOC_DATE,CP.UPDATE_BY,CP.UPDATE_DATE,CP.CREATED_BY,CP.CREATED_DATE,CP.VOLUME_NO,CP.BOOK_NUM,CP.OLD_REG_NUM,CP.OLD_REG_DT,CP.APPLICANT_SPOUSE,"
			+ "CT.PURPOSE_OF_REQUEST,CT.TYPE_REQ,CT.DOC_TYPE FROM IGRS_CERCOPY_TXN_PARTY_DETAILS CP,IGRS_CERTIFIED_COPY_TXN CT WHERE CP.cer_copy_txn_id=CT.transaction_id and CP.cer_copy_txn_id=?";

	public static final String GET_CAVEATS_REGID_PROPERTYID = "SELECT REGC.REG_TXN_ID, REGI.PROPERTY_ID, REGI.PROP_TYPE_ID, TYP.PROPERTY_TYPE_DESC,TYP.H_PROPERTY_TYPE_NAME "
			+ "FROM IGRS_REG_PROPRTY_DTLS REGI, IGRS_PROPERTY_TYPE_MASTER TYP , IGRS_REG_TXN_DETLS REGC "
			+ "WHERE REGI.PROPERTY_ID IS NOT NULL AND "
			+ "REGI.PROP_TYPE_ID = TYP.PROPERTY_TYPE_ID AND "
			+ "REGI.REG_TXN_ID = REGC.REG_TXN_ID AND REGC.REGISTRATION_NUMBER = ?";

	public static final String PARTY_DETAILS = "SELECT (SELECT APPELLATE_TYPE_NAME FROM igrs_appellate_type_master WHERE igrs_appellate_type_master.appellate_type_id=igrs_reg_txn_party_detls.appellate_type_id)APPELLATE_TYPE,"
			+ "PARTY_FIRST_NAME||PARTY_MIDDLE_NAME||PARTY_LAST_NAME,mother_name,guardian_name,spouse_name,"
			+ "organization_name,authrzd_persn_name FROM igrs_reg_txn_party_detls WHERE "
			+ "REG_TXN_ID=(SELECT REG_TXN_ID FROM igrs_reg_proprty_dtls WHERE PROPERTY_ID=?)";

	public static final String PAYMENT_SEQ_NEXT = "SELECT IGRS_CERT_PAYMENT_SEQ.NEXTVAL FROM DUAL";

	public static final String COUNTRY_NAME = "SELECT COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE  COUNTRY_ID=?";
	public static final String COUNTRY_NAME_HINDI = "SELECT H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE  COUNTRY_ID=?";

	public static final String STATE_NAME = "SELECT STATE_NAME FROM IGRS_STATE_MASTER WHERE STATE_ID=?";
	public static final String STATE_NAME_HINDI = "SELECT H_STATE_NAME FROM IGRS_STATE_MASTER WHERE STATE_ID=?";

	public static final String DISTRICT_NAME = "SELECT  DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";
	public static final String DISTRICT_NAME_HINDI = "SELECT  H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";

	public static final String TEHSIL_NAME = "SELECT TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=?";
	public static final String TEHSIL_NAME_HINDI = "SELECT H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=?";

	public static final String WARD_PATWARI_NAME = "SELECT WARD_PATWARI_NAME FROM IGRS_WARD_PATWARI_MASTER  WHERE WARD_PATWARI_ID=?";
	public static final String WARD_PATWARI_NAME_HINDI = "SELECT H_WARD_PATWARI_NAME FROM IGRS_WARD_PATWARI_MASTER  WHERE WARD_PATWARI_ID=?";

	public static final String MOHALLA_VILLAGE_NAME = "SELECT MOHALLA_VILLAGE_NAME FROM IGRS_MOHALLA_VILLAGE_MASTER WHERE MOHALLA_VILLAGE_ID=?";

	public static final String MUNICIPAL_BODY_NAME = "SELECT MUNICIPAL_BODY_NAME  FROM IGRS_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_ID=?";

	public static final String OFFICE_NAME = "select office_name from igrs_office_master where office_id =?";
	public static final String OFFICE_NAME_HINDI = "select h_office_name from igrs_office_master where office_id =?";

	 public static final String UPDATE_TRAN_STATUS="UPDATE IGRS_CERTIFIED_COPY_TXN SET TRANSACTION_STATUS='C',PURPOSE_DOWNLOAD=? where TRANSACTION_ID =?";
	 
	 public static final String PROPERTY_DISTRICT_ID="Select upper(DISTRICT_ID) from IGRS_NOECUM_PROPERTY_DTLS where TXN_ID=?";
	 
	 public static final String OFFICE_DISTRICT_ID="Select upper(DISTRICT_ID) from IGRS_OFFICE_MASTER where OFFICE_ID=?";
	 public static final String GET_COLLECTION_OFFICE="select upper(REGISTRATION_OFFICE_ID) from IGRS_REG_TXN_DETLS where REGISTRATION_NUMBER =?";

	 public static final String GET_PAYMENT_PARAMETER="select wt.REGISTRATION_OFFICE_ID,(select office_name from igrs_office_master where office_id= upper(wt.REGISTRATION_OFFICE_ID)) name,(select district_id from igrs_office_master where office_id= upper(wt.REGISTRATION_OFFICE_ID)) district_id,(select district_name from igrs_district_master where district_id =(select district_id from igrs_office_master where office_id=upper(wt.REGISTRATION_OFFICE_ID))) from igrs_reg_txn_detls wt where registration_number=?";

	 public static final String GET_SR_DASH_UPDATE="select txn.transaction_id,TO_CHAR(txn.created_date,'DD/MM/YYYY') from igrs_certified_copy_txn txn ,igrs_noecum_property_dtls pd where pd.txn_id=txn.transaction_id and txn.transaction_status='C' and pd.district_id=(select district_id from igrs_office_master where upper(office_id)=?)";

	 public static final String GET_SR_DASH="select txn.transaction_id,TO_CHAR(txn.created_date,'DD/MM/YYYY') from igrs_certified_copy_txn txn ,igrs_noecum_property_dtls pd where pd.txn_id=txn.transaction_id and txn.transaction_status='P' and pd.district_id=(select district_id from igrs_office_master where upper(office_id)=?)";

	 public static final String GET_SR_DASH_NOENCUM_UPDATE="SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='C' and type_req='No-Encumbrance'";
	 
	 public static final String GET_SR_DAS_NOENCUM_UPDATE_OFC="SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,TO_CHAR(CT.CREATED_DATE,'DD/MM/YYYY'),cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='C'AND CT.TYPE_REQ='No-Encumbrance' and cp.registration_number=ct.registration_id AND upper(cp.registration_office_id)=?";

	 public static final String GET_SR_DASH_NOENCUM="SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='P' and type_req='No-Encumbrance'";

	 public static final String GET_SR_DASH_NOENCUM_OFC="SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,TO_CHAR(CT.CREATED_DATE,'DD/MM/YYYY'),cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='P'AND CT.TYPE_REQ='No-Encumbrance' and cp.registration_number=ct.registration_id AND Upper(cp.registration_office_id)=?";

	 public static final String GET_SR_DASH_UPDATE1="SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='C' and type_req='Certified Copy'";
			 
	 public static final String GET_SR_DASH_UPDATE1_OFC="SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,TO_CHAR(CT.CREATED_DATE,'DD/MM/YYYY'),cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='C'AND CT.TYPE_REQ='Certified Copy' and cp.registration_number=ct.registration_id AND upper(cp.registration_office_id)=?";		 

	 public static final String SR_DASH="SELECT REGISTRATION_ID,TRANSACTION_ID,TYPE_REQ,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM igrs_certified_copy_txn WHERE TRANSACTION_STATUS='P' and type_req='Certified Copy'";
			 
	 public static final String SR_DASH_OFFC="SELECT CT.REGISTRATION_ID,CT.TRANSACTION_ID,CT.TYPE_REQ,TO_CHAR(CT.CREATED_DATE,'DD/MM/YYYY'),cp.registration_office_id FROM igrs_certified_copy_txn CT,igrs_reg_txn_detls CP WHERE CT.TRANSACTION_STATUS='P'AND CT.TYPE_REQ='Certified Copy' and cp.registration_number=ct.registration_id AND Upper(cp.registration_office_id)=?";
		
	  public static final String TOTAL_PAYMENT="select total from igrs_cercopy_txn_party_details where cer_copy_txn_id=?";
	  
	  public static final String PENDING_APPS="select  cer_copy_txn_id,total,TO_CHAR((CREATED_DATE),'dd/mm/yyyy') CREATED_DATE from  igrs_cercopy_txn_party_details where cer_copy_txn_id not in (select cert_txn_id from igrs_cert_copy_payment_dtls) and cer_copy_txn_id like 'CCI%' and created_by=?"; 

	  // hindi
	  
	  public static final String GET_COUNTRY_HI = "select h_country_name from igrs_country_master where country_id=?";
		public static final String GET_STATE_HI = "select h_state_name from igrs_state_master where state_id=?";
		public static final String GET_DISTRICT_HI = "select h_district_name from igrs_district_master where district_id=?";
 
		public static final String COPY_DETAILS_HI = "SELECT CP.CER_COPY_TXN_ID,CP.PARTY_TYPE_ID,CP.PARTY_FIRST_NAME,CP.PARTY_MIDDLE_NAME,"
			+ "CP.PARTY_LAST_NAME,CP.PARTY_GENDER,CP.PARTY_AGE,CP.NATIONALITY,(select h_country_name from igrs_country_master where country_id=cp.country_id ) country,(select h_state_name from igrs_state_master where state_id=cp.state_id) state,(select h_district_name from igrs_district_master where district_id=cp.district_id) district,CP.PARTY_ADDRESS,CP.POSTAL_CODE,"
			+ "CP.PHONE_NUMBER,CP.MOBILE_NUMBER,CP.EMAIL_ID,CP.PHOTO_PROOF_TYPE_ID,CP.PHOTO_PROOF_ID,	CP.BANK_NAME,CP.BANK_ADDRESS,CP.ORGANIZATION_NAME,CP.GUARDIAN_NAME,CP.MOTHER_NAME,"
			+ "CP.CATEGORY_ID,CP.CASTE_ID,CP.RELIGION_ID,CP.PARTY_THUMB_IMPRESSION,CP.PARTY_PHOTO,CP.PARTY_SIGNATURE,CP.REMARKS,CP.TOTAL,CP.OTHERS,"
			+ "CP.FEE,CP.PARTY_THUMB_IMPRESSION_ID,CP.PARTY_PHOTO_ID,CP.PARTY_SIGNATURE_ID,CP.REG_DATE,CP.FIRST_NAME,	CP.MIDDLE_NAME,CP.LAST_NAME,"
			+ "CP.SPOUSE_NAME,CP.APPLICANT_GUARDIAN_NAME,CP.APPLICANT_MOTHER_NAME,CP.DOC_DATE,CP.UPDATE_BY,CP.UPDATE_DATE,CP.CREATED_BY,CP.CREATED_DATE,CP.VOLUME_NO,CP.BOOK_NUM,CP.OLD_REG_NUM,CP.OLD_REG_DT,CP.APPLICANT_SPOUSE,"
			+ "CT.PURPOSE_OF_REQUEST,CT.TYPE_REQ,CT.DOC_TYPE FROM IGRS_CERCOPY_TXN_PARTY_DETAILS CP,IGRS_CERTIFIED_COPY_TXN CT WHERE CP.cer_copy_txn_id=CT.transaction_id and CP.cer_copy_txn_id=?";
		
		
		public static final String SUB_AREA_NAME = "SELECT SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=?";
		public static final String SUB_AREA_NAME_HINDI = "SELECT H_SUB_AREA_TYPE_NAME  FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=?";
		public static final String GRAM_NAME = "SELECT COLONY_NAME FROM IGRS_COLONY_MASTER WHERE COLONY_ID=?";
		public static final String GRAM_NAME_HINDI = "SELECT H_COLONY_NAME FROM IGRS_COLONY_MASTER WHERE COLONY_ID=?";
		
		public static final String GET_TEHSIL_NAME="select b.tehsil_name from IGRS_OFFICE_MASTER a,IGRS_TEHSIL_MASTER b where a.tehsil_id=b.tehsil_id and a.OFFICE_ID=?";
		public static final String GET_TEHSIL_NAME_HINDI="select b.h_tehsil_name from IGRS_OFFICE_MASTER a,IGRS_TEHSIL_MASTER b where a.tehsil_id=b.tehsil_id and a.OFFICE_ID=?";

		public static final String GET_REG_DATE="select UPDATE_DATE from IGRS_REG_TXN_DETLS where REGISTRATION_NUMBER=?";
		public static final String GET_NE_DATE="select CREATED_DATE from IGRS_CERTIFIED_COPY_TXN where TRANSACTION_ID=?";

		public static final String GET_USER_TYPE = "select USER_TYPE_ID from IGRS_USERS_LIST where USER_ID=?";
}
