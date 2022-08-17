/**
 * 
 */
package com.wipro.igrs.payments.sql;

/***************************************************************
 * 
 * File : CommonSQL.java Description : Represent the SQL Query Author : Karteek
 * P Created Date : March 18, 2008
 **************************************************************/
public class CommonSQL {
	/***********************************
	 * Update SP Amount Details
	 **********************************/
	public static final String spUpdateQry = "UPDATE IGRS_SP_USER_ACCT_BAL_DETAILS SET SP_ACCT_BALANCE = ?" + " WHERE SP_LICENCE_TXN_ID LIKE ? ";

	public static final String IGRS_GET_TREASURY_LIST = "select TREASURY_ID,TREASURY_NAME from IGRS_MP_TREASURY_MASTER where DISTRICT_ID=?";
	public static final String IGRS_GET_TREASURY_LIST_HI = "select TREASURY_ID,H_TREASURY_NAME from IGRS_MP_TREASURY_MASTER where DISTRICT_ID=?";

	/*******************************
	 * Get the SP Transaction Id
	 *******************************/
	public static final String spTxnIdQry = "SELECT TRANSACTION_ID FROM IGRS_SP_LICENSE_LOG WHERE INSERTED_DATE IN" + "(SELECT MAX(INSERTED_DATE) FROM IGRS_SP_LICENSE_LOG)";

	/*********************************************************
	 * VALUES WILL BE INSERTED INTO IGRS SP LICENSE LOG TABLE
	 *********************************************************/
	public static final String spInsertQry = " INSERT INTO IGRS_SP_LICENSE_LOG(LICENCE_NUMBER,ATTRIBUTE_VALUE," + " ATTRIBUTE_VALUE_FROM,ATTRIBUTE_VALUE_TO,INSERTED_DATE,INSERTED_BY," + " TRANSACTION_ID) VALUES (?,'ACCBALANCE',?,?,SYSDATE,?,IGRS_SP_TXN_ID.NEXTVAL)";

	public static final String spCmsnQry = "CALL IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)";

	public static final String spLicenseQry = "SELECT LD.LICENSE_NO,LL.ATTRIBUTE_VALUE_FROM, LD.LICENSE_TXN_ID FROM IGRS_SP_USER_LICENSE_DETAILS LD INNER JOIN IGRS_SP_LICENSE_LOG LL ON LD.LICENSE_NO = LL.LICENCE_NUMBER" + " WHERE LICENSE_STATUS LIKE  'A' AND SP_USER_ID LIKE ";

	public static final String spLicenseQryNew = "SELECT iclt.LICENSE_NO,iclt.ACCOUNT_BAL,iclt.SR_NO FROM IGRS_CREDIT_LIMIT_TXN_LOG iclt inner join" + " IGRS_SP_USER_LICENSE_DETAILS iusd" + " ON iclt.LICENSE_NO = iusd.LICENse_NO and iclt.SR_NO=(select max(SR_NO)" + " from IGRS_CREDIT_LIMIT_TXN_LOG where CREDIT_OR_DEBIT='C')" + " WHERE LICENSE_STATUS LIKE  'A' AND SP_USER_ID LIKE ";
	public static final String spAmtQry = "SELECT SP_ACCT_BALANCE FROM IGRS_SP_USER_ACCT_BAL_DETAILS WHERE SP_LICENCE_TXN_ID LIKE ";

	/*
	 * public static final String SPActQryO =
	 * "SELECT distinct LICENSE_NO,nvl(ACCOUNT_BAL,0),(select first_name||' '||middle_name||' '||last_name"
	 * + " from IGRS_USER_REG_DETAILS where USER_ID = ?) as ACNT_HOLDER_NAME"+
	 * " FROM IGRS_CREDIT_LIMIT_TXN_LOG WHERE LICENSE_NO= ? AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM"
	 * +
	 * " IGRS_CREDIT_LIMIT_TXN_LOG WHERE LICENSE_NO= ? AND PAYMENT_FLAG='C') ";
	 */
	/*
	 * public static final String SPActQryE =
	 * "SELECT distinct LICENSE_NO,nvl(ACCOUNT_BAL,0),(select first_name||' '||middle_name||' '||last_name"
	 * + " from IGRS_USER_REG_DETAILS where USER_ID = ?) as ACNT_HOLDER_NAME"+
	 * " FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP WHERE LICENSE_NO= ? AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM"
	 * +
	 * " IGRS_CREDIT_LIMIT_TXN_ESTAMP WHERE LICENSE_NO= ? AND PAYMENT_FLAG='C') "
	 * ;
	 */
	// added on 13th Jan'2016
	public static final String SPActQryO = "SELECT DISTINCT LICENSE_NO,  NVL(a.ACCOUNT_BAL,0),first_name    ||' '    ||middle_name    ||' '    ||last_name as ACNT_HOLDER_NAME,c.created_by " + " FROM IGRS_CREDIT_LIMIT_TXN_LOG a,IGRS_USER_REG_DETAILS b,igrs_sp_detls c " + " WHERE  c.created_by=b.user_id AND PAYMENT_FLAG='C' and USER_ID=? and LICENSE_NO= ? and " + " c.license_number=a.LICENSE_NO and c.status='A' and c.applctn_status='8' AND a.UPDATE_DATE =(SELECT MAX(UPDATE_DATE) " + "  FROM IGRS_CREDIT_LIMIT_TXN_LOG  WHERE LICENSE_NO= ?   AND PAYMENT_FLAG='C')";

	public static final String SPActQryE = "SELECT DISTINCT LICENSE_NO,  NVL(a.ACCOUNT_BAL,0),first_name    ||' '    ||middle_name    ||' '    ||last_name as ACNT_HOLDER_NAME,c.created_by " + " FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP a,IGRS_USER_REG_DETAILS b,igrs_sp_detls c " + " WHERE  c.created_by=b.user_id AND PAYMENT_FLAG='C' and USER_ID=? and LICENSE_NO= ? and " + " c.license_number=a.LICENSE_NO and c.status='A' and c.applctn_status='8' AND a.UPDATE_DATE =(SELECT MAX(UPDATE_DATE) " + "  FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP   WHERE LICENSE_NO= ?   AND PAYMENT_FLAG='C' )";

	public static final String SPActQryJ = "SELECT DISTINCT LICENSE_NO,  NVL(a.ACCOUNT_BAL,0),first_name    ||' '    ||middle_name    ||' '    ||last_name as ACNT_HOLDER_NAME,c.created_by " + " FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP a,IGRS_USER_REG_DETAILS b,igrs_sp_detls c " + " WHERE  c.created_by=b.user_id AND PAYMENT_FLAG='C' and USER_ID=? and LICENSE_NO= ? and " + " c.license_number=a.LICENSE_NO and c.status='A' and c.applctn_status='8' AND a.UPDATE_DATE =(SELECT MAX(UPDATE_DATE) " + "  FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP   WHERE LICENSE_NO= ?   AND PAYMENT_FLAG='C' )";

	public static final String spActQRY1 = "SELECT nvl(ACCOUNT_BAL,0) FROM IGRS_CREDIT_LIMIT_TXN_LOG WHERE LICENSE_NO = ?" + " AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM" + " IGRS_CREDIT_LIMIT_TXN_LOG WHERE LICENSE_NO = ? AND PAYMENT_FLAG='C') ";

	public static final String spActQRY1_2 = "SELECT nvl(ACCOUNT_BAL,0) FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP WHERE LICENSE_NO = ?" + " AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM" + " IGRS_CREDIT_LIMIT_TXN_ESTAMP WHERE LICENSE_NO = ? AND PAYMENT_FLAG='C') ";

	public static final String spActQRY1_Judicial = "SELECT nvl(ACCOUNT_BAL,0) FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE LICENSE_NO = ?" + " AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM" + " IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE LICENSE_NO = ? AND PAYMENT_FLAG='C') ";

	public static final String spActQRY = "SELECT nvl(ACCOUNT_BAL,0) FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE LICENSE_NO = ?" + " AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM" + " IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE LICENSE_NO = ? AND PAYMENT_FLAG='C') ";

	/*
	 * public static final String spParentUpdateQRY = "UPDATE IGRS_CREDIT_LIMIT_TXN_LOG SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?,"+ " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP,"+ " PAYMENT_FLAG= 'C' WHERE SR_NO =? and LICENSE_NO=? AND PAYMENT_FLAG = 'I'";
	 */
	public static final String spParentUpdateQRY = "UPDATE IGRS_CREDIT_LIMIT_TXN_LOG SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?," + " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP," + " PAYMENT_FLAG= 'C' WHERE SR_NO =?  AND PAYMENT_FLAG = 'I'";

	public static final String spParentUpdateQRY_1 = "UPDATE IGRS_CREDIT_LIMIT_TXN_ESTAMP SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?," + " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP," + " PAYMENT_FLAG= 'C' WHERE SR_NO =?  AND PAYMENT_FLAG = 'I'";

	/*
	 * public static final String spParentUpdateQRY_1 = "UPDATE IGRS_CREDIT_LIMIT_TXN_ESTAMP SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?,"+ " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP,"+ " PAYMENT_FLAG= 'C' WHERE SR_NO =? and LICENSE_NO=? AND PAYMENT_FLAG = 'I'";
	 */

	public static final String spParentUpdateQRY_Judicial = "UPDATE IGRS_CREDIT_LIMIT_TXN_J_ESTAMP SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?," + " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP," + " PAYMENT_FLAG= 'C' WHERE SR_NO =? AND PAYMENT_FLAG = 'I'";

	public static final String SPInsertQryO = "INSERT INTO IGRS_CREDIT_LIMIT_TXN_LOG (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAYMENT_TXN_ID,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID)" + " VALUES(IGRS_CRDT_LIMIT_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?,?," + " 'PAYMENTS',SYSTIMESTAMP,'PAYMENTS',SYSDATE,'C',?)";

	public static final String SPInsertQryE = "INSERT INTO IGRS_CREDIT_LIMIT_TXN_ESTAMP (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAYMENT_TXN_ID,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?,?," + " 'PAYMENTS',SYSTIMESTAMP,'PAYMENTS',SYSDATE,'C',?)";

	public static final String SPInsertQryJ = "INSERT INTO IGRS_CREDIT_LIMIT_TXN_J_ESTAMP (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAYMENT_TXN_ID,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?,?," + " 'PAYMENTS',SYSTIMESTAMP,'PAYMENTS',SYSDATE,'C',?)";

	public static final String cashTxn = " SELECT TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS WHERE STATUS_FLAG LIKE 'A' AND ";

	public static final String PAYMENTS_BANKID_SELECT = "select BBM.BANK_ID,BM.BANK_NAME||'_'||BBM.BRANCH_NAME||'_'||BBM.BRANCH_CODE  from  IGRS_BANK_BRANCH_MASTER bbm,IGRS_BANK_MASTER bm WHERE BBM.BANK_ID=BM.BANK_ID";

	public static final String PAYMENTS_BANKS_SELECT = "select BANK_ID,BANK_NAME from IGRS_BANK_MASTER WHERE STATUS= 'A'";

	public static final String cashTxnqry = " SELECT TRANSACTION_ID  FROM IGRS_PAYMENT_TXN_DETAILS ";

	public static final String statusUpdatqry = " UPDATE IGRS_PAYMENT_MODE_DETAILS SET STATUS_FLAG = 'D' WHERE TRANSACTION_ID = ? AND STATUS_FLAG = 'A' ";

	public static final String statusUpdatqryCash = " UPDATE IGRS_PAYMENT_MODE_DETAILS SET STATUS_FLAG = 'D' WHERE TRANSACTION_ID = ? AND STATUS_FLAG = 'A' and CREATED_BY = ? and CHECKER_REG_TXN_ID = ?";
	// commented for new challan details
	/*
	 * public static final String statusUpdatqry1 =
	 * " UPDATE IGRS_PAYMENT_MODE_DETAILS SET STATUS_FLAG = 'D' WHERE SCROLL_NUMBER = ? AND TRANSACTION_ID = ? AND STATUS_FLAG = 'A' "
	 * ;
	 */
	// added for new challan details
	public static final String statusUpdatqry1 = " UPDATE IGRS_PAYMENT_ECHALLAN_DETAILS SET STATUS_FLAG = 'D' WHERE LOWER(CIN) = ? AND CHALLAN_SERIAL_NUMBER = ? AND STATUS_FLAG = 'A' ";

	public static final String statusUpdatqry2 = " UPDATE IGRS_PAYMENT_ECHALLAN_DETAILS SET STATUS_FLAG = 'D' WHERE LOWER(CIN) = ? AND CHALLAN_SERIAL_NUMBER = ? AND STATUS_FLAG = 'A' AND PAYMENT_MODE_ID='3' ";

	public static final String statusUpdate = "";

	public static final String challanTxnqry = " SELECT TRANSACTION_ID  FROM IGRS_PAYMENT_TXN_DETAILS ";
	public static final String challanTxnInsert = "INSERT INTO IGRS_PAYMENT_TXN_DETAILS(TRANSACTION_ID,CREATED_BY,CREATED_DATE, " + "UPDATE_BY,UPDATE_DATE,AMOUNT_PAID, PAYMENT_TYPE_ID,PAYMENT_PURPOSE, PAYMENT_DATE, USER_ID, PARENT_KEY,PARENT_REF_ID,OFFICE_ID,DISTRICT_ID) VALUES (?,'PAYMENTS',SYSDATE," + "'PAYMENTS',SYSDATE," + "?, ?,?, SYSDATE,?,?,?,?,?)";
	public static final String paymentTxnMapQry = "INSERT INTO IGRS_PAYMENT_TXN_MAP(PAYMENT_TXN_ID," + "PAYMENT_MODE_TXN_ID,CREATED_BY, UPDATE_BY, UPDATE_DATE ) VALUES (?,?,?,'PAYMENTS', SYSDATE)";

	/**
	 * 
	 */

	public static final String getPayTxnIDQry = " SELECT MAX(TRANSACTION_ID)  FROM IGRS_PAYMENT_TXN_DETAILS ";

	public static final String mppingTxnqry = " SELECT DEPENDANT_TXN_ID FROM IGRS_PAYMENT_MODE_MAPPING ";

	public static final String simplePayReceipt = " SELECT TRANSACTION_ID,NET_AMOUNT,OFFICE_ID,DISTRICT_ID,FUNCTION_ID,REG_USER_ID, " + " CREATED_DATE FROM IGRS_PAYMENT_MODE_DETAILS";

	public static final String FUNC_NAME = "select FUNCTION_NAME from IGRS_FUNCTION_MASTER where FUNCTION_ID=?";

	public static final String REV_HEADS = "SELECT REVENUE_MAJOR_HEAD, REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD,SCHEME_HEAD FROM IGRS_FUNCTION_SERVICE_MAPPING" + " WHERE FUNCTION_ID =?";
	public static final String REV_HEADS_OFFLINE = "SELECT REVENUE_MAJOR_HEAD, REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD,SCHEME_HEAD FROM IGRS_OFFLINE_SERVICE_MAPPING" + " WHERE FUNCTION_ID =? AND SERVICE_ID=?";

	public static final String DISTRICT_QRY = "SELECT DISTRICT_ID, DISTRICT_NAME FROM IGRS_DISTRICT_MASTER";

	public static final String OFFICE_QRY = "SELECT OFFICE_ID, OFFICE_NAME FROM IGRS_OFFICE_MASTER where DISTRICT_ID=?";

	public static final String DWNLD_CHALLAN_INSERT_QRY = "INSERT INTO IGRS_PAYMENT_ECHALLAN_DETAILS " + "(CHALLAN_SERIAL_NUMBER, REFERENCE_ID, FUNCTION_ID, REVENUE_MAJOR_HEAD, " + "REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD, PAYABLE_AMOUNT, PAID_AMOUNT, FIRST_NAME, " + "MIDDLE_NAME, LAST_NAME, DISTRICT_ID, OFFICE_ID, PAYMENT_MODE_ID, CREATED_BY, CREATED_DATE, " + "ADDRESS,EPNR_NO)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'2',?,SYSDATE,?,?)";

	public static final String DWNLD_ONLINE_INSERT_QRY = "INSERT INTO IGRS_PAYMENT_ECHALLAN_DETAILS " + "(CHALLAN_SERIAL_NUMBER, REFERENCE_ID, FUNCTION_ID, REVENUE_MAJOR_HEAD, " + "REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD, PAYABLE_AMOUNT, PAID_AMOUNT, FIRST_NAME, " + "MIDDLE_NAME, LAST_NAME, DISTRICT_ID, OFFICE_ID, PAYMENT_MODE_ID, CREATED_BY, CREATED_DATE, " + "ADDRESS,CRN_NUMBER,SCHEME_HEAD_ID,TREASURY_ID,STATUS_FLAG)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'3',?,SYSDATE,?,?,?,?,'A')";

	public static final String DWNLD_ONLINE_INSERT_QRY_IFMIS = "INSERT INTO IGRS_PAYMENT_ECHALLAN_DETAILS " + "(CHALLAN_SERIAL_NUMBER, REFERENCE_ID, FUNCTION_ID, REVENUE_MAJOR_HEAD, " + "REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD, PAYABLE_AMOUNT, PAID_AMOUNT, FIRST_NAME, " + "MIDDLE_NAME, LAST_NAME, DISTRICT_ID, OFFICE_ID, PAYMENT_MODE_ID, CREATED_BY, CREATED_DATE, " + "ADDRESS,CRN_NUMBER,SCHEME_HEAD_ID,TREASURY_ID,STATUS_FLAG,ADD_LINE1,ADD_LINE2,ADD_LINE3,ADD_LINE4,ADD_LINE5,ADD_STATE,ADD_PIN,MOB_NO,URN)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,'3',?,SYSDATE,?,?,?,?,'A',?,?,?,?,?,?,?,?,?)";

	public static final String DWNLD_CHALN_DETLS = "SELECT p.CHALLAN_SERIAL_NUMBER,p.REFERENCE_ID,p.FUNCTION_ID, f.FUNCTION_NAME, p.REVENUE_MAJOR_HEAD,p.REVENUE_SUB_MAJOR_HEAD," + " p.REVENUE_MINOR_HEAD,p.PAID_AMOUNT,p.FIRST_NAME||' '||p.MIDDLE_NAME||' '||p.LAST_NAME,p.DISTRICT_ID, d.DISTRICT_NAME, o.OFFICE_NAME,p.ADDRESS, p.EPNR_NO, major.REVENUE_MAJOR_HEAD_NAME, submjr.REVENUE_SUB_MAJOR_HEAD_NAME, minor.REVENUE_MINOR_HEAD_NAME " + " FROM IGRS_PAYMENT_ECHALLAN_DETAILS p, IGRS_FUNCTION_MASTER f,IGRS_DISTRICT_MASTER d,IGRS_OFFICE_MASTER o, IGRS_REVENUE_MAJOR_HEAD_MASTER major, IGRS_REVENUE_SBMJRHD_MASTER submjr, IGRS_REVENUE_MINOR_HEAD_MASTER minor " + " WHERE major.REVENUE_MAJOR_HEAD=p.REVENUE_MAJOR_HEAD and submjr.REVENUE_SUB_MAJOR_HEAD=p.REVENUE_SUB_MAJOR_HEAD and minor.REVENUE_MINOR_HEAD=p.REVENUE_MINOR_HEAD and  p.FUNCTION_ID=f.FUNCTION_ID AND p.DISTRICT_ID=d.DISTRICT_ID AND p.OFFICE_ID=o.OFFICE_ID AND minor.REVENUE_MINOR_HEAD_STATUS='A' AND p.CHALLAN_SERIAL_NUMBER=?";

	public static final String DWNLD_CHALN_DETLS_INFO = "SELECT p.CHALLAN_SERIAL_NUMBER,p.REFERENCE_ID,p.FUNCTION_ID, f.FUNCTION_NAME, p.REVENUE_MAJOR_HEAD,p.REVENUE_SUB_MAJOR_HEAD," + " p.REVENUE_MINOR_HEAD,p.PAID_AMOUNT,p.FIRST_NAME||' '||p.MIDDLE_NAME||' '||p.LAST_NAME,p.DISTRICT_ID, d.DISTRICT_NAME, o.OFFICE_NAME,p.ADDRESS, p.EPNR_NO, major.REVENUE_MAJOR_HEAD_NAME, submjr.REVENUE_SUB_MAJOR_HEAD_NAME, minor.REVENUE_MINOR_HEAD_NAME " + " FROM IGRS_PAYMENT_ECHALLAN_DETAILS p, IGRS_FUNCTION_MASTER f,IGRS_DISTRICT_MASTER d,IGRS_OFFICE_MASTER o, IGRS_REVENUE_MAJOR_HEAD_MASTER major, IGRS_REVENUE_SBMJRHD_MASTER submjr, IGRS_REVENUE_MINOR_HEAD_MASTER minor " + " WHERE major.REVENUE_MAJOR_HEAD=p.REVENUE_MAJOR_HEAD and submjr.REVENUE_SUB_MAJOR_HEAD=p.REVENUE_SUB_MAJOR_HEAD and minor.REVENUE_MINOR_HEAD=p.REVENUE_MINOR_HEAD and  p.FUNCTION_ID=f.FUNCTION_ID AND p.DISTRICT_ID=d.DISTRICT_ID AND p.OFFICE_ID=o.OFFICE_ID AND minor.REVENUE_MINOR_HEAD_STATUS='A' AND p.CHALLAN_SERIAL_NUMBER=?";

	public static final String ONLINE_SEARCH_QRY = "select CHALLAN_SERIAL_NUMBER,CIN,CHALLAN_AMOUNT,FUNCTION_ID, STATUS_FLAG from IGRS_PAYMENT_ECHALLAN_DETAILS where LOWER(CIN) =? AND PAYMENT_MODE_ID='3'";

	public static final String ONLINE_DETL_FETCH = "select TO_CHAR(CHALLAN_ONLINE_DATE,'dd/MM/yyyy hh:mi:ss AM'),NAME_PAYER " + "from IGRS_PAYMENT_ECHALLAN_DETAILS where STATUS_FLAG ='A' AND CHALLAN_SERIAL_NUMBER=? AND LOWER(CIN)=?";

	public static final String ONLINE_PRNT_QRY = "SELECT FIRST_NAME||' '||LAST_NAME FROM IGRS_PAYMENT_ECHALLAN_DETAILS " + " WHERE CRN_NUMBER=?";

	public static final String SP_SETL_QRY = "SELECT DISTINCT iusd.LICENSE_NUMBER,0 AS ACCOUNT_BAL, " + "(SELECT first_name||' '||middle_name||' '||last_name FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?)AS ACNT_HOLDER_NAME " + "FROM IGRS_SP_DETLS iusd WHERE iusd.LICENSE_NUMBER=(SELECT LICENSE_NUMBER FROM IGRS_SP_DETLS " + "WHERE CREATED_BY=? AND APPLCTN_STATUS='8' AND STATUS='A' AND LICENSE_NUMBER IS NOT NULL) ";

	public static final String cashSql = "select GROSS_AMOUNT, FUNCTION_ID,STATUS_FLAG from IGRS_PAYMENT_MODE_DETAILS where LOWER(TRANSACTION_ID)=? AND SAMPADA_SERVICE='Y' AND OFFICE_ID= ?";
	public static final String cashSqlNew = "select GROSS_AMOUNT, FUNCTION_ID,STATUS_FLAG,CREATED_BY,CHECKER_REG_TXN_ID from IGRS_PAYMENT_MODE_DETAILS where LOWER(TRANSACTION_ID)=? AND SAMPADA_SERVICE='Y'";

	public static final String cashCorrectDtl = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'), " + "PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME from " + "IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND LOWER(TRANSACTION_ID)=?";

	// ADDED BY ROOPAM ON AND AFTER 10 OCT 2013
	public static final String getDistIdOfficeIdForRUChallan = "select DISTRICT_ID, OFFICE_ID from IGRS_PAYMENT_ECHALLAN_DETAILS where CHALLAN_SERIAL_NUMBER=?";

	public static final String CURRENT_DATE_TIME = "select TO_CHAR(sysdate,'MM/dd/yyyy HH:mm:ss') from dual";

	public static final String FUNC_NAME_HINDI = "select FUNCTION_NAME_H from IGRS_FUNCTION_MASTER where FUNCTION_ID=?";

	public static final String OFFICE_QRY_HINDI = "SELECT OFFICE_ID, OFFICE_NAME,H_OFFICE_NAME FROM IGRS_OFFICE_MASTER where DISTRICT_ID=?";

	public static final String DISTRICT_QRY_HINDI = "SELECT DISTRICT_ID, DISTRICT_NAME,H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS='A' AND STATE_ID=1 ORDER BY DISTRICT_NAME";

	public static final String cashCorrectDtl_HINDI = "select to_char(to_date(PM.CHALLAN_ONLINE_DATE,'DD/MM/RRRR'),'DD/MM/RRRR'), " + "PM.FIRST_NAME ||' '||PM.MIDDLE_NAME ||' ' || PM.LAST_NAME, OFM.OFFICE_NAME, OFM.H_OFFICE_NAME from " + "IGRS_PAYMENT_MODE_DETAILS PM,IGRS_OFFICE_MASTER OFM where PM.OFFICE_ID = OFM.OFFICE_ID AND LOWER(TRANSACTION_ID)=?";

	// Added by Mohit Soni
	public static final String GET_TREASURY_DISTRICT = "select MP_TREASURY_DISTRICT_CODE from igrs_district_master where district_id=?";

	public static final String GET_ONLINE_SEQUENCE = "select IGRS_ONLINE_PAYMENT_SEQUENCE.nextval from dual";

	public static final String GET_MAJOR_HEAD = "select REVENUE_MAJOR_HEAD_NAME from IGRS_REVENUE_MAJOR_HEAD_MASTER where REVENUE_MAJOR_HEAD=?";

	public static final String GET_SUB_MAJOR_HEAD = "select REVENUE_SUB_MAJOR_HEAD_NAME from IGRS_REVENUE_SBMJRHD_MASTER where REVENUE_SUB_MAJOR_HEAD=?";

	public static final String GET_MINOR_HEAD = "select REVENUE_MINOR_HEAD_NAME from IGRS_REVENUE_MINOR_HEAD_MASTER where REVENUE_MINOR_HEAD=?";

	public static final String GET_PAYMENT_DETAILS = "select CHALLAN_NUMBER,REVENUE_MAJOR_HEAD,revenue_sub_major_head,revenue_minor_head,brn,TRANSACTION_DATE_TIME,SCROLL_DATE,BANK_SCROLL,CIN,ADDRESS from IGRS_PAYMENT_ECHALLAN_DETAILS where CRN_NUMBER=?";
	public static final String UPDATE_ONLINE = "Update IGRS_PAYMENT_ECHALLAN_DETAILS set CHALLAN_NUMBER=?,CIN=?,BRN=?,BANK_SCROLL=?,SCROLL_DATE=?,TRANSACTION_DATE_TIME=?,STATUS_FLAG='D' where CRN_NUMBER=?";
	public static final String UPDATE_ONLINE_IFMIS = "Update IGRS_PAYMENT_ECHALLAN_DETAILS set CHALLAN_NUMBER=?,CIN=?,BRN=?,BANK_SCROLL=?,SCROLL_DATE=?,TRANSACTION_DATE_TIME=?,STATUS_FLAG='D',STATUS_TRS=?,STATUS_DESC_TRS=? where CRN_NUMBER=?";
	public static final String INSERT_INTO_OFFLINE_DATA = "insert into IGRS_OFFLINE_CHALLAN (STATUS,PAYING_AMOUNT,PARENT_TABLE_NAME,PARENT_COLMUMN_NAME,PARENT_KEY_NAME,PAYMENT_TYPE,EPRN_NO,CREATED_BY,CREATED_DATE)values('A',?,?,?,?,?,?,?,sysdate) ";

	public static final String INSERT_INTO_OFFLINE_DATA_CYBER = "insert into IGRS_OFFLINE_CYBER (STATUS,PAYING_AMOUNT,PARENT_TABLE_NAME,PARENT_COLMUMN_NAME,PARENT_KEY_NAME,PAYMENT_TYPE,CRN_NUMBER,CREATED_BY,CREATED_DATE)values('A',?,?,?,?,?,?,?,sysdate) ";

	// Added by preeti on 6 nov 2015
	/*
	 * public static final String AcctBal=
	 * "SELECT Account_bal FROM igrs_credit_limit_txn_estamp WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_estamp WHERE update_date IS NOT NULL AND License_no =?)"
	 * ;
	 * 
	 * public static final String AcctBalO=
	 * "SELECT Account_bal FROM igrs_credit_limit_txn_Log WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_Log WHERE update_date IS NOT NULL AND License_no =?)"
	 * ;
	 */

	// Added by preeti on 27 nov 2015
	/*
	 * public static final String AcctBal=
	 * "SELECT nvl(ACCOUNT_BAL,0)  FROM igrs_credit_limit_txn_estamp WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_estamp WHERE update_date IS NOT NULL AND License_no  =? )AND License_no  =?"
	 * ;
	 * 
	 * public static final String AcctBalO=
	 * "SELECT nvl(ACCOUNT_BAL,0) FROM igrs_credit_limit_txn_Log WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_Log WHERE update_date IS NOT NULL AND License_no  =? )AND License_no  =?"
	 * ;
	 */
	// Added by Shreeraj 13th Jan
	public static final String AcctBal = "SELECT nvl(ACCOUNT_BAL,0)  FROM igrs_credit_limit_txn_estamp WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_estamp " + " WHERE update_date IS NOT NULL AND License_no  =? AND PAYMENT_FLAG='C') AND " + " License_no  =? AND PAYMENT_FLAG='C'";

	public static final String AcctBalJ = "SELECT nvl(ACCOUNT_BAL,0)  FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE update_date IN (SELECT MAX(update_date)FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP " + " WHERE update_date IS NOT NULL AND License_no  =? AND PAYMENT_FLAG='C') AND " + " License_no  =? AND PAYMENT_FLAG='C'";

	public static final String AcctBalO = "SELECT nvl(ACCOUNT_BAL,0) FROM igrs_credit_limit_txn_Log WHERE update_date IN (SELECT MAX(update_date)FROM igrs_credit_limit_txn_Log WHERE update_date IS NOT NULL AND License_no  =? AND PAYMENT_FLAG='C') AND License_no  =? AND PAYMENT_FLAG='C'";

	// Added by Shreeraj 7th Dec
	public static final String GET_LAST_PAYMENT_DATE_BYPASS = "SELECT to_date,  fro_date " + "FROM  " + "  (SELECT TO_CHAR(sysdate+ (1 / ATTRIBUTE_VALUE) ,'hh:mi:ss AM') fro_date " + "  FROM dual,(SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_174') " + "  ), " + "  (SELECT TO_CHAR(sysdate,'hh:mi AM') to_date " + "  FROM dual  ) ";

	public static final String GET_LAST_PAYMENT_DETAILS_BYPASS_E = "SELECT * FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP " + "WHERE LICENSE_NO =? and " + "to_char(TRANSACTION_DATE,'dd-mm-yyyy')=(select to_char(max(TRANSACTION_DATE),'dd-mm-yyyy') from IGRS_CREDIT_LIMIT_TXN_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C') " + " AND TO_CHAR(TRANSACTION_DATE,'hh:mi AM') BETWEEN ? AND ? " + "AND function_id=? AND paid_amount=? ";

	public static final String GET_LAST_PAYMENT_DETAILS_BYPASS_O = "SELECT * FROM igrs_credit_limit_txn_Log " + "WHERE LICENSE_NO =? and " + "to_char(TRANSACTION_DATE,'dd-mm-yyyy')=(select to_char(max(TRANSACTION_DATE),'dd-mm-yyyy') from igrs_credit_limit_txn_Log " + " where LICENSE_NO     =? and payment_flag='C') " + " AND TO_CHAR(TRANSACTION_DATE,'hh:mi AM') BETWEEN ? AND ? " + "AND function_id=? AND paid_amount=? ";

	public static final String INSERT_MULTIPLE_PAY_LOG = "INSERT INTO IGRS_PAYMENT_MULTIPLE_LOG (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID,PAYMENT_TXN_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?," + " 'PAYMENTS',SYSTIMESTAMP,'PAYMENTS',SYSDATE,'C',?,?)";

	public static final String INSERT_MULTIPLE_PAY_LOG_SWAP_DEBIT = "INSERT INTO IGRS_PAYMENT_MULTIPLE_LOG (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID,PAYMENT_TXN_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?," + " 'DEBIT',SYSTIMESTAMP,'SWAP',SYSDATE,'C',?,?)";

	public static final String INSERT_MULTIPLE_PAY_LOG_SWAP_CREDIT = "INSERT INTO IGRS_PAYMENT_MULTIPLE_LOG (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID,PAYMENT_TXN_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'C',?,?,?,?," + " 'CREDIT',SYSTIMESTAMP,'SWAP',SYSDATE,'C',?,?)";

	public static final String INSERT_MULTIPLE_PAY_LOG_SWAP_ONLINE = "INSERT INTO IGRS_PAYMENT_MULTIPLE_LOG (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID,PAYMENT_TXN_ID)" + " VALUES(IGRS_CRDT_LIMIT_ESTAMP_TXN_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?," + " 'CRNSWAP',SYSTIMESTAMP,'ONLINE',SYSDATE,'C',?,?)";

	public static final String GET_PAYTXN_ID_E = "SELECT min(payment_txn_id) FROM IGRS_CREDIT_LIMIT_TXN_ESTAMP a,IGRS_PAYMENT_TXN_DETAILS b " + "where LICENSE_NO     =? and a.PAYMENT_TXN_ID=b.TRANSACTION_ID " + "and TO_CHAR(TRANSACTION_DATE,'hh:mi AM')  between ? and ? " + "and function_id=? and paid_amount=? ";

	public static final String GET_PAYTXN_ID_O = "SELECT min(payment_txn_id) FROM IGRS_CREDIT_LIMIT_TXN_LOG a,IGRS_PAYMENT_TXN_DETAILS b " + "where LICENSE_NO     =? and a.PAYMENT_TXN_ID=b.TRANSACTION_ID " + "and TO_CHAR(TRANSACTION_DATE,'hh:mi AM')  between ? and ? " + "and function_id=? and paid_amount=? ";

	public static final String GET_DATE_E = "select DT,ATTRIBUTE_VALUE,(   select  max(payment_txn_id) from IGRS_CREDIT_LIMIT_TXN_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=? and " + "  to_char(update_date,'dd-mm-yyyy')=(select to_char(max(update_date),'dd-mm-yyyy') from IGRS_CREDIT_LIMIT_TXN_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C')) from " + " ( " + " (select  (sysdate    - (   select  max(update_date) from IGRS_CREDIT_LIMIT_TXN_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=?)) * 24 * 60 * 60 dt " + "       from dual) ),(SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_174') ";

	public static final String GET_DATE_J = "select DT,ATTRIBUTE_VALUE,(   select  max(payment_txn_id) from IGRS_CREDIT_LIMIT_TXN_J_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=? and " + "  to_char(update_date,'dd-mm-yyyy')=(select to_char(max(update_date),'dd-mm-yyyy') from IGRS_CREDIT_LIMIT_TXN_J_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C')) from " + " ( " + " (select  (sysdate    - (   select  max(update_date) from IGRS_CREDIT_LIMIT_TXN_J_ESTAMP " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=?)) * 24 * 60 * 60 dt " + "       from dual) ),(SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_174') ";

	public static final String GET_DATE_O = "select DT,ATTRIBUTE_VALUE,(   select  max(payment_txn_id) from igrs_credit_limit_txn_Log " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=? and " + "  to_char(update_date,'dd-mm-yyyy')=(select to_char(max(update_date),'dd-mm-yyyy') from igrs_credit_limit_txn_Log " + " where LICENSE_NO     =? and payment_flag='C')) from " + " ( " + " (select  (sysdate    - (   select  max(update_date) from igrs_credit_limit_txn_Log " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=?)) * 24 * 60 * 60 dt " + "       from dual) ),(SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_174') ";

	public static final String UPDATE_OUTPUT_TO_TREASURY_URL = "UPDATE IGRS_OFFLINE_CYBER SET OUTPUT_TO_TRS_URL=? WHERE CRN_NUMBER=?";

	public static final String UPDATE_INPUT_FROM_TREASURY_URL = "UPDATE IGRS_OFFLINE_CYBER SET INPUT_FROM_TRS_URL=? WHERE CRN_NUMBER=?";

	public static final String SELECTPAYINGAMOUNT_CRN = "select trim(paying_amount) from igrs_offline_cyber where CRN_NUMBER=?";

	public static final String SELECTUSERID_CRN = "select trim(created_by) from igrs_offline_cyber where CRN_NUMBER=?";

	public static final String TREASURY_URL_DATA = "select REVENUE_MAJOR_HEAD, REVENUE_SUB_MAJOR_HEAD, REVENUE_MINOR_HEAD,SCHEME_HEAD_ID,TREASURY_ID,FIRST_NAME||' '||MIDDLE_NAME||' '||LAST_NAME,b.paying_amount,district_name,office_name FROM " + "igrs_payment_echallan_details a,IGRS_OFFLINE_CYBER B ,igrs_district_master c, igrs_office_master d " + " where a.crn_number=b.crn_number and a.crn_number=? and " + " a.district_id=c.district_id and a.office_id=d.office_id ";

	public static final String DELETE_FROM_CREDIT_LIMIT_ESTAMP = "DELETE FROM igrs_credit_limit_txn_estamp WHERE SR_NO=? AND LICENSE_NO=? AND PAYMENT_TXN_ID=? AND PAID_AMOUNT=?";

	public static final String DELETE_FROM_CREDIT_LIMIT_J_ESTAMP = "DELETE FROM IGRS_CREDIT_LIMIT_TXN_J_ESTAMP WHERE SR_NO=? AND LICENSE_NO=? AND PAYMENT_TXN_ID=? AND PAID_AMOUNT=?";

	public static final String DELETE_FROM_CREDIT_LIMIT_LOG = "DELETE FROM igrs_credit_limit_txn_log WHERE SR_NO=? AND LICENSE_NO=? AND PAYMENT_TXN_ID=? AND PAID_AMOUNT=?";

	public static final String DELETE_FROM_PAY_TXN_MAP = "DELETE FROM IGRS_PAYMENT_TXN_MAP WHERE PAYMENT_TXN_ID=?";

	public static final String DELETE_FROM_PAY_TXN_DETAILS = "DELETE FROM IGRS_PAYMENT_TXN_DETAILS WHERE transaction_id=?";

	public static final String PAID_AMOUNT = "select AMOUNT_PAID from IGRS_PAYMENT_TXN_DETAILS where TRANSACTION_ID=?";

	public static final String VALIDATE_CRN = "SELECT a.CREATED_BY FROM IGRS_PAYMENT_ECHALLAN_DETAILS A,IGRS_OFFLINE_CYBER B " + " WHERE A.CRN_NUMBER=B.CRN_NUMBER and a.CREATED_BY=b.CREATED_BY and A.CRN_NUMBER=? and OUTPUT_TO_TRS_URL is not null ";

	public static final String Cash_Amount = "select PAYABLE_AMOUNT" + " from  IGRS_REG_PAYMENT_DETLS " + " where REG_TXN_ID = ? AND PAYMENT_FLAG =? order by TXN_ID desc";

	public static final String Cash_Check = "select PAID_AMOUNT" + " from  IGRS_REG_PAYMENT_DETLS " + " where REG_TXN_ID = ? AND PAYMENT_FLAG ='C' and PAYMENT_MODE_ID =? and PAYMENT_TXN_ID is not null and PAYABLE_AMOUNT= ? and PAID_AMOUNT =? and PAYMENT_TXN_ID = ?";
	/*
	 * public static final String
	 * Url_Check="select count(*) from igrs_offline_cyber "+
	 * "where parent_key_name=? and PARENT_TABLE_NAME=? "+
	 * "and INPUT_FROM_TRS_URL is not null and output_to_trs_url is not  null ";
	 * 
	 * public static final String
	 * User_CRN_Check="select  count(distinct created_by) from igrs_offline_cyber "
	 * + "where parent_key_name=?  and PARENT_TABLE_NAME=? ";
	 */

	public static final String CASH_ENABLE_CHECK = "select ATTRIBUTE_VALUE from IGRS_CONFIG_PARAM_LIST where attribute_id='138'";
		// Added by Ajit for double verification
	public static final String INSERT_PAYMENT_DATA_VERIFICATION = "INSERT INTO IGRS_PAYMENT_DATA_VERIFICATION (CHALLAN_SERIAL_NUMBER,FUNCTION_ID,PARENT_AMOUNT,PARENT_TABLE,PARENT_KEY,FORWARD_PATH,PARENT_COLUMN,PARENT_OFFICE_ID,PARENT_OFFICE_NAME,PARENT_DISTRICT_ID,PARENT_DISTRICT_NAME,PARENT_REF_ID,MODULE_NAME,FUNCTION_NAME,CRN_NUMBER,PAYING_AMOUNT,LANGUAGE,USERID,FORM_PATH,PAYMENT_FORM_PATH,FORM_NAME,OLD_FORM_NAME,CREATED_DATE)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
	// public static final String GET_PAYMENT_DATA_VERIFICATION="SELECT CHALLAN_SERIAL_NUMBER,FUNCTION_ID,PARENT_AMOUNT,PARENT_TABLE,PARENT_KEY,FORWARD_PATH,PARENT_COLUMN,PARENT_OFFICE_ID,PARENT_OFFICE_NAME,PARENT_DISTRICT_ID,PARENT_DISTRICT_NAME,PARENT_REF_ID,MODULE_NAME,FUNCTION_NAME,CRN_NUMBER,PAYING_AMOUNT,LANGUAGE,USERID,FORM_PATH,PAYMENT_FORM_PATH,FORM_NAME,OLD_FORM_NAME,CREATED_DATE FROM IGRS_PAYMENT_DATA_VERIFICATION WHERE CRN_NUMBER=?";

	
	public static final String spMutationAccountQry = "SELECT nvl(ACCOUNT_BAL,0) FROM IGRS_CREDIT_LIMIT_TXN_MUTATION WHERE LICENSE_NO = ?" + " AND PAYMENT_FLAG='C' AND UPDATE_DATE=(SELECT MAX(UPDATE_DATE) FROM" + " IGRS_CREDIT_LIMIT_TXN_MUTATION WHERE LICENSE_NO = ? AND PAYMENT_FLAG='C') ";

	public static final String spMutationAccountUpdateQry = "UPDATE IGRS_CREDIT_LIMIT_TXN_MUTATION SET TRANSACTION_DATE=SYSDATE,PAYMENT_MODE_ID=?,PAYMENT_TXN_ID=?," + " PAID_AMOUNT=?,ACCOUNT_BAL=?,SP_AMOUNT=?,UPDATE_BY='PAYMENTS',UPDATE_DATE=SYSTIMESTAMP," + " PAYMENT_FLAG= 'C' WHERE SR_NO =? AND PAYMENT_FLAG = 'I'";
	public static final String SPActQryMutation = "SELECT DISTINCT LICENSE_NO,  NVL(a.ACCOUNT_BAL,0),first_name    ||' '    ||middle_name    ||' '    ||last_name as ACNT_HOLDER_NAME,c.created_by " + " FROM IGRS_CREDIT_LIMIT_TXN_MUTATION a,IGRS_USER_REG_DETAILS b,igrs_sp_detls c " + " WHERE  c.created_by=b.user_id AND PAYMENT_FLAG='C' and USER_ID=? and LICENSE_NO= ? and " + " c.license_number=a.LICENSE_NO and c.status='A' and c.applctn_status='8' AND a.UPDATE_DATE =(SELECT MAX(UPDATE_DATE) " + "  FROM IGRS_CREDIT_LIMIT_TXN_MUTATION   WHERE LICENSE_NO= ?   AND PAYMENT_FLAG='C' )";
	public static final String AcctBalMutation = "SELECT nvl(ACCOUNT_BAL,0)  FROM IGRS_CREDIT_LIMIT_TXN_MUTATION WHERE update_date IN (SELECT MAX(update_date)FROM IGRS_CREDIT_LIMIT_TXN_MUTATION " + " WHERE update_date IS NOT NULL AND License_no  =? AND PAYMENT_FLAG='C') AND " + " License_no  =? AND PAYMENT_FLAG='C'";
	public static final String GET_DATE_MUTATION = "select DT,ATTRIBUTE_VALUE,(   select  max(payment_txn_id) from IGRS_CREDIT_LIMIT_TXN_MUTATION " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=? and " + "  to_char(update_date,'dd-mm-yyyy')=(select to_char(max(update_date),'dd-mm-yyyy') from IGRS_CREDIT_LIMIT_TXN_MUTATION " + " where LICENSE_NO     =? and payment_flag='C')) from " + " ( " + " (select  (sysdate    - (   select  max(update_date) from IGRS_CREDIT_LIMIT_TXN_MUTATION " + " where LICENSE_NO     =? and payment_flag='C' and function_id=? and paid_amount=?)) * 24 * 60 * 60 dt " + "       from dual) ),(SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_174') ";
	public static final String SPInsertQryMutation = "INSERT INTO IGRS_CREDIT_LIMIT_TXN_MUTATION (SR_NO,LICENSE_NO,TRANSACTION_DATE," + " PAYMENT_MODE_ID,CREDIT_OR_DEBIT,PAYMENT_TXN_ID,PAID_AMOUNT, ACCOUNT_BAL," + " SP_AMOUNT,SP_COMM ,UPDATE_BY,UPDATE_DATE,CREATED_BY,CREATED_DATE,PAYMENT_FLAG,FUNCTION_ID)" + " VALUES(IGRS_CRDT_LIMIT_MUTATION_SEQ.NEXTVAL,?,SYSDATE,?,'D',?,?,?,?,?," + " 'PAYMENTS',SYSTIMESTAMP,'PAYMENTS',SYSDATE,'C',?)";

}