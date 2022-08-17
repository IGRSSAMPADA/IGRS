
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 * 
 */ 

/* 
 * FILE NAME: WillUpdateDAO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28the December 2007
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DATA ACCESS OBJECTS FOR WILL UPDATION.
 */


package com.wipro.igrs.willdeposit.sql;

/**
 * @author NIHAR M.
 *
 */
public class CommonSQL {

	/**
	 * REQUISITE_FEE_QUERY
	 */
	public static final String REQUISITE_FEE_QUERY = 
		"SELECT AMOUNT FROM IGRS_REQUISITE_FEE_MASTER WHERE REQUISITE_NAME"
		+ "='WILL_DEPOSIT'";

	/**
	 * REQUISITE_OTHER_FEE_QUERY
	 */
	public static final String REQUISITE_OTHER_FEE_QUERY = 
		"SELECT AMOUNT FROM IGRS_REQUISITE_FEE_MASTER WHERE REQUISITE_NAME"
		+ "='WILL_OTHER'";

	/**
	 * WILL_DEPOSIT_INSERT
	 */
	public static final String WILL_DEPOSIT_INSERT = 
		"INSERT INTO IGRS_WILL_DEPOSIT_TXN(WILL_ID,DR_ID," 
		+ "DEPOSITOR_TYPE,FIRST_NAME,MID_NAME,LAST_NAME,GENDER,AGE," 
		+ "FATHER_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,CITY,STATE," 
		+ "COUNTRY,PIN,PHONE,MOBILE,EMAIL,ID_PROOF,PHOTO_FLAG,THUMB_FLAG," 
		+ "SIGN_FLAG,FEE,OTHER,PAYMENT_MODE,DEPOSIT_DATE,WILL_TYPE_FLAG)" 
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE"
		+ ",?) ";

	/**
	 * WILL_DEPOST_AGENT_INSERT
	 */
	public static final String WILL_DEPOST_AGENT_INSERT = 
		"INSERT INTO IGRS_WILL_AGENT_TXN(WILL_ID,FIRST_NAME," 
		+ "MID_NAME,LAST_NAME,GENDER,AGE,FATHER_NAME,MOTHER_NAME," 
		+ "SPOUSE_NAME,ADDRESS,CITY,STATE,COUNTRY,PIN,PHONE,MOBILE,EMAIL"
		+ ",IDPROOFNO) " 
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * WILL_DEPOSIT_QUERY
	 */
	public static final String WILL_DEPOSIT_QUERY = 
		"SELECT FIRST_NAME,MID_NAME,LAST_NAME,GENDER,AGE," 
		+ "FATHER_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,CITY,STATE," 
		+ "COUNTRY,PIN,PHONE,MOBILE,EMAIL,ID_PROOF,PHOTO_FLAG,THUMB_FLAG," 
		+ "SIGN_FLAG,TO_CHAR(DEPOSIT_DATE,'dd/mm/yy'),WILL_ID, WILL_TYPE_FLAG,"
		+ " ID_PROOF FROM  IGRS_WILL_DEPOSIT_TXN WHERE"
		+ " WILL_ID=? AND WILL_TYPE_FLAG=?";



	/**
	 * WILL_ID_DETAILS_QUERY
	 */
	public static final String WILL_ID_DETAILS_QUERY = 				
		"SELECT MAX(COL1),MAX(COL2),MAX(COL3),MAX(COL4),"+
		"MAX(COL5),MAX(COL6),MAX(COL7),MAX(COL8),MAX(COL9),MAX(COL10), MAX(COL11), MAX(COL12), MAX(COL13), MAX(COL14), MAX(COL15), MAX(COL16), " +
		"MAX(COL17), MAX(COL18), MAX(COL19), MAX(COL20), MAX(COL21), MAX(COL22),"+
		"MAX(COL23),MAX(COL24),MAX(COL25),MAX(COL26),MAX(COL27),MAX(COL28), MAX(COL29), MAX(COL30), " +
		"MAX(COL31), MAX(COL32), MAX(COL33), MAX(COL34),MAX(COL35),MAX(COL36),MAX(COL37),MAX(COL38)," +
		"MAX(COL39),MAX(COL40), MAX(COL41), MAX(COL42), MAX(COL43), MAX(COL44), MAX(COL45), MAX(COL46) " + 


		" "+"FROM (SELECT igrs_will_txn_party.will_txn_id col1, igrs_will_txn_party.party_type_id col2, igrs_will_txn_party.first_name col3,"+
		"igrs_will_txn_party.middle_name col4, igrs_will_txn_party.last_name col5, igrs_will_txn.will_status col6," +
		"igrs_will_txn_party.gender col7, igrs_will_txn_party.age col8, " +
		"igrs_will_txn_party.guardian_name col9, igrs_will_txn_party.mother_name col10, igrs_will_txn.delivery_status col11,"+

		"to_char(igrs_will_txn.created_date) col12," +
		"igrs_will_txn_party.spouse_name col13, igrs_will_txn_party.address col14, igrs_will_txn_party.country_id col15," +
		" igrs_will_txn_party.state_id col16," +
		"igrs_will_txn_party.district_id col17, igrs_will_txn_party.postal_code col18, igrs_will_txn_party.phone_number col19," +
		" igrs_will_txn_party.mobile_number col20," +
		"igrs_will_txn_party.email_id col21, igrs_will_txn_party.photo_proof_type_id col22, igrs_will_txn_party.photo_proof_id col23,  "+


		"NULL col24, NULL col25, NULL col26, NULL col27, NULL col28, NULL col29, NULL col30, NULL col31, NULL col32, NULL col33, NULL col34, "+
		"NULL col35, NULL col36, NULL col37, NULL col38, NULL col39, NULL col40, NULL col41,"+
		"NULL col42, NULL col43, NULL col44, NULL col45, NULL col46 "+
		" "+"FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'A' and ";


	/**
	 * WILL_ID_VIEW_QUERY
	 */
	public static final String WILL_ID_VIEW_QUERY_1 = 				
		"SELECT MAX(COL1),MAX(COL2),MAX(COL3),MAX(COL4),"+
		"MAX(COL5),MAX(COL6),MAX(COL7),MAX(COL8),MAX(COL9),MAX(COL10), MAX(COL11), MAX(COL12), MAX(COL13), MAX(COL14), MAX(COL15), MAX(COL16), " +
		"MAX(COL17), MAX(COL18), MAX(COL19), MAX(COL20), MAX(COL21), MAX(COL22),"+
		"MAX(COL23),MAX(COL24),MAX(COL25),MAX(COL26),MAX(COL27),MAX(COL28), MAX(COL29), MAX(COL30), " +
		"MAX(COL31), MAX(COL32), MAX(COL33), MAX(COL34),MAX(COL35),MAX(COL36),MAX(COL37),MAX(COL38)," +
		"MAX(COL39),MAX(COL40), MAX(COL41), MAX(COL42), MAX(COL43), MAX(COL44), MAX(COL45), MAX(COL46) " + 


		" "+"FROM (SELECT igrs_will_txn_party.will_txn_id col1, igrs_will_txn_party.party_type_id col2, igrs_will_txn_party.first_name col3,"+
		"igrs_will_txn_party.middle_name col4, igrs_will_txn_party.last_name col5, igrs_will_txn.will_status col6," +
		"igrs_will_txn_party.gender col7, igrs_will_txn_party.age col8, " +
		"igrs_will_txn_party.guardian_name col9, igrs_will_txn_party.mother_name col10, igrs_will_txn.delivery_status col11,"+

		"to_char(igrs_will_txn.created_date) col12," +
		"igrs_will_txn_party.spouse_name col13, igrs_will_txn_party.address col14, igrs_will_txn_party.country_id col15," +
		" igrs_will_txn_party.state_id col16," +
		"igrs_will_txn_party.district_id col17, igrs_will_txn_party.postal_code col18, igrs_will_txn_party.phone_number col19," +
		" igrs_will_txn_party.mobile_number col20," +
		"igrs_will_txn_party.email_id col21, igrs_will_txn_party.photo_proof_type_id col22, igrs_will_txn_party.photo_proof_id col23,  "+


		"NULL col24, NULL col25, NULL col26, NULL col27, NULL col28, NULL col29, NULL col30, NULL col31, NULL col32, NULL col33, NULL col34, "+
		"NULL col35, NULL col36, NULL col37, NULL col38, NULL col39, NULL col40, NULL col41,"+
		"NULL col42, NULL col43, NULL col44, NULL col45, NULL col46 "+
		" "+"FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'A' and ";

	public static final String WILL_ID_VIEW_QUERY ="SELECT igrs_will_txn.will_txn_id, igrs_will_txn_party.party_type_id, " +
			"(SELECT " +
			"    SUM(IGRS_WILL_TXN_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT" +
			"     FROM IGRS_WILL_TXN_PAYMENT_DTLS where igrs_will_txn.will_txn_id = igrs_will_txn_payment_dtls.will_txn_id" +
			"  GROUP BY IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID), to_char(igrs_will_txn.created_date,'DD/MM/YYYY'), igrs_will_txn.dr_id, " +
			"igrs_district_master.district_name, igrs_will_txn_party.first_name, igrs_will_txn_party.middle_name, " +
			"igrs_will_txn_party.last_name, igrs_will_txn_party.gender,  igrs_will_txn_party.age, igrs_will_txn_party.guardian_name, " +
			"igrs_will_txn_party.mother_name,  igrs_will_txn_party.spouse_name, igrs_will_txn_party.address, " +
			"igrs_will_txn_party.country_id, (select country_name from igrs_country_master where " +
			"igrs_will_txn_party.country_id= igrs_country_master.country_id) country_name, igrs_will_txn_party.state_id, " +
			"(select state_name from igrs_state_master where igrs_state_master.state_id=igrs_will_txn_party.state_id) state_name, " +
			"igrs_will_txn_party.district_id, igrs_district_master.district_name,igrs_will_txn_party.postal_code," +
			"igrs_will_txn_party.phone_number, igrs_will_txn_party.mobile_number, igrs_will_txn_party.email_id, " +
			"igrs_will_txn_party.photo_proof_type_id, igrs_will_txn_party.photo_proof_id, igrs_will_txn_party.PHOTO_PATH, " +
			"igrs_will_txn_party.THUMB_PATH, igrs_will_txn_party.SIGNATURE_PATH, to_char(igrs_will_txn.withdraw_due_date,'DD/MM/YYYY'), " +
			"igrs_will_comments.user_comments, igrs_will_txn.WILL_STATUS,igrs_will_txn_party.PHOTO_NAME,igrs_will_txn_party.THUMB_NAME," +
			"igrs_will_txn_party.SIGNATURE_NAME,igrs_will_txn.REMARKS,igrs_will_txn.TREASURYID,igrs_will_txn_party.AGENT_PROOF_PATH,igrs_will_txn_party.AGENT_PROOF_NAME,to_char(igrs_will_txn.RETRIEVAL_DUE_DATE,'DD/MM/YYYY'),igrs_will_txn_party.COURT_NAME," +
			"igrs_will_txn_party.DESIGNATION,igrs_will_txn_party.DEATH_CERTI_PATH,igrs_will_txn_party.DEATH_CERTIFICATE_NAME,igrs_will_txn_party.BANK_NAME,igrs_will_txn_party.BANK_ADDRESS, IGRS_WILL_TXN_PARTY.WILL_COPY_NAME,IGRS_WILL_TXN_PARTY.WILL_COPY_PATH, IGRS_WILL_TXN_PARTY.CATEGORY_ID " +
			" FROM " +
			"(igrs_will_txn JOIN igrs_will_txn_party ON(igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)) LEFT OUTER JOIN " +
			"igrs_will_comments ON(igrs_will_txn.will_txn_id = igrs_will_comments.will_txn_id) LEFT OUTER JOIN " +
			"igrs_district_master ON(igrs_will_txn_party.district_id = igrs_district_master.district_id) WHERE " +
			"((igrs_will_txn.will_txn_id = ?)) ";
	
	

	/**
	 * WILL_WITHDRAWAL_ID_DETAILS_QUERY
	 */
	public static final String WILL_WITHDRAWAL_ID_DETAILS_QUERY = 				//NEW
		"SELECT MAX(COL1),MAX(COL2),MAX(COL3),MAX(COL4),"+
		"MAX(COL5),MAX(COL6),MAX(COL7),MAX(COL8),MAX(COL9),MAX(COL10), MAX(COL11), MAX(COL12), MAX(COL13), MAX(COL14), MAX(COL15), MAX(COL16), " +
		"MAX(COL17), MAX(COL18), MAX(COL19), MAX(COL20), MAX(COL21), MAX(COL22)"+
		" "+"FROM (SELECT igrs_will_txn_party.will_txn_id col1, igrs_will_txn_party.party_type_id col2, igrs_will_txn_party.first_name col3,"+
		"igrs_will_txn_party.middle_name col4, igrs_will_txn_party.last_name col5, igrs_will_txn.will_status col6," +
		"igrs_will_txn_party.gender col7, igrs_will_txn_party.age col8, " +
		"igrs_will_txn_party.guardian_name col9, igrs_will_txn_party.mother_name col10, igrs_will_txn.delivery_status col11,"+
		"NULL col12, NULL col13, NULL col14, NULL col15, NULL col16, NULL col17, NULL col18, NULL col19, NULL col20, NULL col21, NULL col22"+
		" "+"FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'A' and igrs_will_txn_party.will_txn_id=?";




	/**
	 * WILL_ID_DETAILS_QUERY_1
	 */
	public static final String WILL_ID_DETAILS_QUERY_1 = "SELECT MAX(COL1),MAX(COL2),MAX(COL3),MAX(COL4),"+
	"MAX(COL5),MAX(COL6),MAX(COL7),MAX(COL8),MAX(COL9),MAX(COL10), MAX(COL11), MAX(COL12), MAX(COL13), MAX(COL14), MAX(COL15), MAX(COL16), " +
	"MAX(COL17), MAX(COL18), MAX(COL19), MAX(COL20)"+
	" "+"FROM (SELECT igrs_will_txn_party.will_txn_id col1, igrs_will_txn_party.party_type_id col2, igrs_will_txn_party.first_name col3,"+
	"igrs_will_txn_party.middle_name col4, igrs_will_txn_party.last_name col5, igrs_will_txn.will_status col6," +
	"igrs_will_txn_party.gender col7, igrs_will_txn_party.age col8, " +
	"igrs_will_txn_party.guardian_name col9, igrs_will_txn_party.mother_name col10,"+
	"NULL col11, NULL col12, NULL col13, NULL col14, NULL col15, NULL col16, NULL col17, NULL col18, NULL col19, NULL col20"+
	" "+"FROM igrs_will_txn_party, igrs_will_txn where igrs_will_txn_party.PARTY_TYPE_ID = 'A' and ";



	/**
	 * WILL_WITHDRAWAL_INSERT
	 */
	public static final String WILL_WITHDRAWAL_INSERT = 
		"UPDATE IGRS_WILL_DEPOSIT_TXN SET WILL_TYPE_FLAG=?,EXE_TFLAG=?"
		+ ",EXE_SFLAG=?,REASON=?,REMARKS=? WHERE WILL_ID=?";


	/**
	 * WILL_DELIVERY_UPDATE
	 */
	public static final String WILL_DELIVERY_UPDATE = 
		"UPDATE IGRS_WILL_COMMENTS SET USER_COMMENTS = ?,COMMENTS_DATE = SYSDATE, USER_ID=? WHERE WILL_TXN_ID = ?";


	/**
	 * WILL_RETRIEVE_QUERY
	 */
	public static final String WILL_RETRIEVE_QUERY = 
		"SELECT FIRST_NAME,MID_NAME,LAST_NAME, ADDRESS,TO_CHAR(DEPOSIT_DATE,"
		+ "'dd/mm/yy'),WILL_ID, WILL_TYPE_FLAG"
		+ " FROM  IGRS_WILL_DEPOSIT_TXN WHERE WILL_ID LIKE 'MP%' ";


	/**
	 * WILL_VIEW_QUERY
	 */
	public static final String WILL_VIEW_QUERY =                     //NEW
		"SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"+
		"igrs_will_txn_party.middle_name, igrs_will_txn_party.last_name,"+
		"TO_CHAR(igrs_will_txn.created_date, 'mm/dd/yy'), igrs_will_txn_party.address,"+
		"igrs_will_txn.will_status FROM igrs_will_txn, igrs_will_txn_party"+" "+
		"WHERE igrs_will_txn_party.will_txn_id = ? AND"+""+
		"((igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)) AND	" +
		"TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'mm/dd/yy'))"+" "+ 
		"AND TO_CHAR(TO_DATE(? ,'mm/dd/yy')) and igrs_will_txn.will_status = ?";


	/**
	 * WILL_UPDATE_VIEW_QUERY_R
	 */
	public static final String WILL_UPDATE_VIEW_QUERY_R =                     //NEW
		"SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"+
		"igrs_will_txn_party.middle_name, igrs_will_txn_party.last_name,"+
		"TO_CHAR(igrs_will_txn.created_date, 'mm/dd/yy'), igrs_will_txn_party.address,"+
		"igrs_will_txn.will_status, igrs_will_txn.retrieval_due_date FROM igrs_will_txn, igrs_will_txn_party"+" "+
		"WHERE igrs_will_txn_party.will_txn_id = ? AND"+""+
		"((igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)) AND	" +
		"TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'mm/dd/yy'))"+" "+ 
		"AND TO_CHAR(TO_DATE(? ,'mm/dd/yy')) and igrs_will_txn.will_status = ?";


	/**
	 * WILL_UPDATE_VIEW_QUERY_W
	 */
	public static final String WILL_UPDATE_VIEW_QUERY_W =                     //NEW
		"SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"+
		"igrs_will_txn_party.middle_name, igrs_will_txn_party.last_name,"+
		"TO_CHAR(igrs_will_txn.created_date, 'mm/dd/yy'), igrs_will_txn_party.address,"+
		"igrs_will_txn.will_status, igrs_will_txn.withdraw_due_date FROM igrs_will_txn, igrs_will_txn_party"+" "+
		"WHERE igrs_will_txn_party.will_txn_id = ? AND"+""+
		"((igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)) AND	" +
		"TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'mm/dd/yy'))"+" "+ 
		"AND TO_CHAR(TO_DATE(? ,'mm/dd/yy')) and igrs_will_txn.will_status = ?";



	/**
	 * WILL_WITHDRAWAL_VIEW_QUERY
	 */
	public static final String WILL_WITHDRAWAL_VIEW_QUERY = 
		"SELECT FIRST_NAME,MID_NAME,LAST_NAME, ADDRESS,TO_CHAR(DEPOSIT_DATE,"
		+ "'dd/mm/yy'),WILL_ID"
		+ " FROM  IGRS_WILL_DEPOSIT_TXN WHERE WILL_ID LIKE 'MP%' ";


	/**
	 * WILL_CHALLAN_PAYMENT_INSERT
	 */
	public static final String WILL_CHALLAN_PAYMENT_INSERT = 
		"INSERT INTO IGRS_PAYMENT_CHALLAN_DETAILS (CHALLAN_ID"
		+ ",CHALLAN_AMOUNT,CHALLAN_DATE) VALUES(?,?,SYSDATE)";

	/**
	 * WILL_RETRIEVE_CITIZEN_INSERT
	 */
	public static final String WILL_RETRIEVE_CITIZEN_INSERT = 
		"INSERT INTO IGRS_WILL_TXN_PARTY(PARTY_TYPE_ID, FIRST_NAME," 
		+ "MIDDLE_NAME,LAST_NAME,GENDER,AGE,GUARDIAN_NAME,MOTHER_NAME," 
		+ "SPOUSE_NAME,ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER, EMAIL_ID," 
		+ "PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID, WILL_TXN_ID, BANK_NAME, BANK_ADDRESS, CATEGORY_ID)"
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";



	/**
	 * WILL_DEPOSITE_TESTATOR_INSERT
	 */
	public static final String WILL_DEPOSITE_TESTATOR_INSERT = 
		"INSERT INTO IGRS_WILL_TXN_PARTY(PARTY_TYPE_ID, FIRST_NAME," 
		+ "MIDDLE_NAME,LAST_NAME,GENDER,AGE,GUARDIAN_NAME,MOTHER_NAME," 
		+ "SPOUSE_NAME,ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER, EMAIL_ID," 
		+ "PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID, BANK_NAME, BANK_ADDRESS, WILL_TXN_ID,PARTY_THUMB_IMPRESSION,PARTY_PHOTO,PARTY_SIGNATURE)"
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,EMPTY_BLOB(),EMPTY_BLOB(),EMPTY_BLOB())";



	/**
	 * WILL_DEPOSITE_AGENT_INSERT
	 */
	public static final String WILL_DEPOSITE_AGENT_INSERT = 
		"INSERT INTO IGRS_WILL_TXN_PARTY(PARTY_TYPE_ID, FIRST_NAME," 
		+ "MIDDLE_NAME,LAST_NAME,GENDER,AGE,GUARDIAN_NAME,MOTHER_NAME," 
		+ "SPOUSE_NAME,ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER, EMAIL_ID," 
		+ "PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID, BANK_NAME, BANK_ADDRESS, WILL_TXN_ID)"
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	
	/**
	 * WILL_RETRIEVE_UPDATE
	 */
	public static final String WILL_RETRIEVE_UPDATE = 
		"UPDATE IGRS_WILL_TXN SET RETRIEVAL_DUE_DATE=?, RETRIEVER_RELATION=? WHERE DEPENDANT_WILL_TXN_ID=?";


	/**
	 * WILL_RETRIEVE_COURT_INSERT
	 */
	public static final String WILL_RETRIEVE_COURT_INSERT = 
		"INSERT INTO IGRS_WILL_TXN_PARTY(PARTY_TYPE_ID, COURT_NAME," 
		+ "ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, PHONE_NUMBER, FIRST_NAME, MIDDLE_NAME, LAST_NAME," 
		+ "DESIGNATION, WILL_TXN_ID)"
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";



	/**
	 * WILL_CASH_PAYMENT_INSERT
	 */
	public static final String WILL_CASH_PAYMENT_INSERT = 
		"INSERT INTO IGRS_PAYMENT_CASH_DETAILS (CASH_RECEIPT_ID) VALUES(?)";


	/**
	 * VISIT_BOOK_FEE_QUERY
	 */
	public static final String VISIT_BOOK_FEE_QUERY=" SELECT IMAP.FUNCTION_ID,IMAP.PARAM_VALUE,IMAS.PARAM_NAME" +
 		" FROM IGRS_SERVICE_FEE_PARAM_MAPPING IMAP,IGRS_SERVICE_FEE_PARAM_MASTER IMAS" +
 		" WHERE PARENT_ID IS NULL AND IMAS.PARAM_ID = IMAP.PARAM_ID AND FUNCTION_ID=?"; 


	/**
	 * INQUIRE_PAY_TXN_ID
	 */
	public static final String INQUIRE_PAY_TXN_ID =
		" SELECT MAX(TRANSACTION_ID)  FROM IGRS_PAYMENT_TXN_DETAILS ";


	/**
	 * STATUS_UPDATE_QUERY
	 */
	public static final String STATUS_UPDATE_QUERY =
		" UPDATE IGRS_PAYMENT_MODE_DETAILS SET STATUS_FLAG = 'D' WHERE TRANSACTION_ID = ? " ;	


	/**
	 * CASH_TXN
	 */
	public static final String CASH_TXN =
		" SELECT TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS WHERE STATUS_FLAG LIKE 'A' AND "  ;


	/**
	 * MAPPING_TXN_QUERY
	 */
	public static final String MAPPING_TXN_QUERY =
		" SELECT DEPENDANT_TXN_ID FROM IGRS_PAYMENT_MODE_MAPPING "  ;


	/**
	 * CHALLAN_TXN_INSERT
	 */
	public static final String CHALLAN_TXN_INSERT = 
		"INSERT INTO IGRS_PAYMENT_TXN_DETAILS(TRANSACTION_ID," +
		"CREATED_BY,CREATED_DATE," +
		"UPDATE_BY,UPDATE_DATE,AMOUNT_PAID) VALUES ( IGRS_PAYMENT_TXNID.NEXTVAL,'IGRS',SYSDATE," +
		"'IGRS',SYSDATE," +
		"? )" ;


	/**
	 * PAYMENT_TXN_MAP
	 */
	public static final String PAYMENT_TXN_MAP = 
		"INSERT INTO IGRS_PAYMENT_TXN_MAP(PAYMENT_TXN_ID," +
		"PAYMENT_MODE_TXN_ID) VALUES (?,?)" ;
	
	/**
	 * WILL_DEPOSITE_TESTATOR_INSERT
	 */
	public static final String WILL_DEPOSITION_INSERT = 
		"INSERT INTO IGRS_WILL_TXN(WILL_TXN_ID, PAYMENT_TXN_ID," 
		+ "WILL_STATUS,CREATED_BY,REMARKS,CREATED_DATE)"
		+ " VALUES(?,?,?,?,?,SYSDATE)";
	
	public static final String WILL_WITHDRAWL_INSERT ="INSERT INTO IGRS_WILL_COMMENTS (WILL_TXN_ID,USER_ID,USER_COMMENTS,COMMENTS_DATE) " +
			"VALUES(?,?,?,SYSDATE)";

	public static final String WILL_WITHDRAWL_STATUS_UPDATE ="UPDATE IGRS_WILL_TXN SET WILL_STATUS=?,UPDATE_BY=?,WITHDRAW_DUE_DATE=?," +
			"UPDATE_DATE=SYSDATE WHERE WILL_TXN_ID=?";
	

	public static final String WILL_WITHDRAWL_RECORD_INSERT = 
		"INSERT INTO IGRS_WILL_TXN(WILL_TXN_ID, PAYMENT_TXN_ID," 
		+ "WILL_STATUS,CREATED_BY,WITHDRAW_DUE_DATE, WITHDRAW_FLAG,DEPENDANT_WILL_TXN_ID,CREATED_DATE)"
		+ " VALUES(?,?,?,?,?,?,?,SYSDATE)";

	public static final String WILL_RETRIVAL_RECORD_INSERT = 
		"INSERT INTO IGRS_WILL_TXN(WILL_TXN_ID, PAYMENT_TXN_ID," 
		+ "WILL_STATUS,CREATED_BY,RETRIEVAL_DUE_DATE, RETRIEVAL_FLAG,DEPENDANT_WILL_TXN_ID,CREATED_DATE)"
		+ " VALUES(?,?,?,?,?,?,?,SYSDATE)";

	public static final String RETRIVE_WILL_REMARKS = 
		"SELECT USER_COMMENTS,USER_ID,TO_CHAR(COMMENTS_DATE,'dd/mm/yyyy') FROM IGRS_WILL_COMMENTS WHERE WILL_TXN_ID=?";
	
	public static final String UPDATE_WILL_REMARKS = "UPDATE IGRS_WILL_TXN_PARTY SET PARTY_THUMB_IMPRESSION=?, PARTY_PHOTO=?, PARTY_SIGNATURE=?" +
			"WHERE WILL_TXN_ID=?";
	
	 public static final String GET_FUNID_QUERY =" select fm.function_name, mm.module_name " +
				" from igrs_function_master fm ," +
				" igrs_sub_module_master sm," +
				" igrs_module_master mm " +
				" where " +
				" fm.sub_module_id= sm.sub_module_id   AND " +
				" sm.module_id= mm.module_id AND " +
				" fm.function_id=? ";
	 
	 public static final String WILL_TREASURY_UPDATE ="UPDATE IGRS_WILL_TXN SET TREASURYID=? WHERE WILL_TXN_ID=? ";
	 public static final String GET_REASON_TREASURY="Select remarks, treasuryid from igrs_will_txn where will_txn_id in (Select dependant_will_txn_id from igrs_will_txn where will_txn_id=?)";
	 public static final String GET_PENDING_DEPOSIT="SELECT WT.WILL_TXN_ID,WY.NO_OF_PARTIES,WP.PAID_AMOUNT,(WP.PAYABLE_AMOUNT-WP.PAID_AMOUNT) DUE_AMOUNT,WP.UPDATE_DATE,TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE,WP.PAYABLE_AMOUNT FROM IGRS_WILL_TXN WT,(SELECT IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID,SUM(IGRS_WILL_TXN_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT,MAX(IGRS_WILL_TXN_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT,TO_CHAR(MAX(IGRS_WILL_TXN_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATE_DATE FROM IGRS_WILL_TXN_PAYMENT_DTLS GROUP BY IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID) WP,(SELECT IGRS_WILL_TXN_PARTY.WILL_TXN_ID, COUNT(IGRS_WILL_TXN_PARTY.PARTY_TYPE_ID) NO_OF_PARTIES FROM IGRS_WILL_TXN_PARTY GROUP BY WILL_TXN_ID) WY WHERE WP.WILL_TXN_ID = WT.WILL_TXN_ID AND WT.WILL_TXN_ID   = WY.WILL_TXN_ID AND WT.WILL_STATUS   = 'DI'  AND SUBSTR(WT.WILL_TXN_ID,3,2)=? ORDER BY WT.WILL_TXN_ID";
	 public static final String seqCall="SELECT IGRS_WILL_PAYMENT_SEQ.NEXTVAL FROM DUAL";
	 public static final String INSERT_PYMNT_TBL="INSERT INTO IGRS_WILL_TXN_PAYMENT_DTLS (WILL_PAYMENT_ID, WILL_TXN_ID,  FUNCTION_ID,  PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG, CREATED_BY, CREATED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)";
	 public static final String CHECK_BAL="SELECT MAX(PAYABLE_AMOUNT), SUM (PAID_AMOUNT) FROM igrs_will_txn_payment_dtls WHERE will_txn_id=? ";
	 public static final String UPDATE_RET_STATUS="UPDATE IGRS_WILL_TXN SET WILL_STATUS='Pending Retrieve' WHERE WILL_TXN_ID = ?";
	 public static final String UPDATE_WITH_STATUS="UPDATE IGRS_WILL_TXN SET WILL_STATUS='Pending Withdraw' WHERE WILL_TXN_ID = ?";
	 public static final String UPDATE_DEP_STATUS="UPDATE IGRS_WILL_TXN SET WILL_STATUS='Pending Deposit' WHERE WILL_TXN_ID = ?";
	 public static final String DEL_DETLS="DELETE FROM IGRS_WILL_TXN WHERE WILL_TXN_ID=?";
	 public static final String DEL_DTLS1="DELETE FROM IGRS_WILL_TXN_PARTY WHERE WILL_TXN_ID=?";
	 public static final String DEL_DTLS2="DELETE FROM IGRS_WILL_COMMENTS WHERE WILL_TXN_ID=?";
	 public static final String OFC_DTLS="select a.office_id , a.office_name, a.district_id, b.district_name from igrs_office_master a, igrs_district_master b where a.district_id=b.district_id and a.office_id = ?";
	 public static final String PENDING_WITH_LIST="SELECT WT.WILL_TXN_ID,WY.NO_OF_PARTIES,WP.PAID_AMOUNT,(WP.PAYABLE_AMOUNT-WP.PAID_AMOUNT) DUE_AMOUNT,WP.UPDATE_DATE,TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE,WP.PAYABLE_AMOUNT FROM IGRS_WILL_TXN WT,(SELECT IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID,SUM(IGRS_WILL_TXN_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT,MAX(IGRS_WILL_TXN_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT,TO_CHAR(MAX(IGRS_WILL_TXN_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATE_DATE FROM IGRS_WILL_TXN_PAYMENT_DTLS GROUP BY IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID) WP,(SELECT IGRS_WILL_TXN_PARTY.WILL_TXN_ID, COUNT(IGRS_WILL_TXN_PARTY.PARTY_TYPE_ID) NO_OF_PARTIES FROM IGRS_WILL_TXN_PARTY GROUP BY WILL_TXN_ID) WY WHERE WP.WILL_TXN_ID = WT.WILL_TXN_ID AND WT.WILL_TXN_ID   = WY.WILL_TXN_ID AND WT.WILL_STATUS   = 'WI' AND SUBSTR(WT.WILL_TXN_ID,3,2) = ? ORDER BY WT.WILL_TXN_ID";
	 public static final String SELECT_OFFICE="Select state_ID from igrs_state_Master where state_name=? ";
	 public static final String SELECT_OFFICE_H="Select state_ID from igrs_state_Master where h_state_name=? ";
	 public static final String DISTRICT="Select district_id from igrs_district_master where district_name=? ";
	 public static final String DISTRICT_H="Select district_id from igrs_district_master where h_district_name=? ";
	 public static final String COUNTRY="Select Country_ID from igrs_country_Master where country_name=? ";
	 public static final String COUNTRY_H="Select Country_ID from igrs_country_Master where h_country_name=? ";
	 public static final String INSERT_WITH_PYMNT="INSERT INTO IGRS_WILL_TXN_PAYMENT_DTLS (WILL_PAYMENT_ID, WILL_TXN_ID,  FUNCTION_ID,  PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG, CREATED_BY, CREATED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE)";
	 public static final String GET_NAME="SELECT FIRST_NAME, LAST_NAME FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
	 public static final String PENDING_RET_DTLS="SELECT WT.WILL_TXN_ID,WY.NO_OF_PARTIES,WP.PAID_AMOUNT,(WP.PAYABLE_AMOUNT-WP.PAID_AMOUNT) DUE_AMOUNT,WP.UPDATE_DATE,TO_CHAR((WT.CREATED_DATE),'dd/mm/yyyy') CREATED_DATE,WP.PAYABLE_AMOUNT FROM IGRS_WILL_TXN WT,(SELECT IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID,SUM(IGRS_WILL_TXN_PAYMENT_DTLS.PAID_AMOUNT) PAID_AMOUNT,MAX(IGRS_WILL_TXN_PAYMENT_DTLS.PAYABLE_AMOUNT) PAYABLE_AMOUNT,TO_CHAR(MAX(IGRS_WILL_TXN_PAYMENT_DTLS.UPDATE_DATE), 'dd/mm/yyyy') UPDATE_DATE FROM IGRS_WILL_TXN_PAYMENT_DTLS GROUP BY IGRS_WILL_TXN_PAYMENT_DTLS.WILL_TXN_ID) WP,(SELECT IGRS_WILL_TXN_PARTY.WILL_TXN_ID, COUNT(IGRS_WILL_TXN_PARTY.PARTY_TYPE_ID) NO_OF_PARTIES FROM IGRS_WILL_TXN_PARTY GROUP BY WILL_TXN_ID) WY WHERE WP.WILL_TXN_ID = WT.WILL_TXN_ID AND WT.WILL_TXN_ID   = WY.WILL_TXN_ID AND WT.WILL_STATUS   = 'RI' AND SUBSTR(WT.WILL_TXN_ID,3,2)=? ORDER BY WT.WILL_TXN_ID";
	 public static final String WILL_WITH_DTLS= "SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"
				+ "igrs_will_txn_party.middle_name,  igrs_will_txn_party.last_name,"
				+ "igrs_will_txn_party.address, to_char(igrs_will_txn.created_date, 'dd/mm/yy'), igrs_will_txn.will_status"
				+ " "
				+ "FROM igrs_will_txn, igrs_will_txn_party WHERE(igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)"
				+ " "
				+ "AND decode(nvl(SUBSTR(igrs_will_txn_party.will_txn_id,   1,   LENGTH("
				+ "?"
				+ ")),   'NA'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   'NA') "
				+ "LIKE nvl("
				+ "?"
				+ ",   'NA') || '%'"
				+ " "
				+ "AND decode(igrs_will_txn.will_status,   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   'NA') = nvl("
				+ "?"
				+ ",   'NA')"
				+ " "
				+ "AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   to_char(created_date,   'DD-MM-YY'),"
				+ "decode("
				+ "?"
				+ ",   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY') >="
				+ " to_date(nvl(to_char(to_date("
				+ "?"
				+ ",   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')"
				+ "AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   to_char(created_date,   'DD-MM-YY'),"
				+ "decode("
				+ "?"
				+ ",   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY') <="
				+ "to_date(nvl(to_char(to_date("
				+ "?"
				+ ",   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')";

	 
	 public static final String RET_VIEW_DTLS="SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"
				+ "igrs_will_txn_party.middle_name,  igrs_will_txn_party.last_name,"
				+ "igrs_will_txn_party.address, to_char(igrs_will_txn.created_date), igrs_will_txn.will_status"
				+ " "
				+ "FROM igrs_will_txn, igrs_will_txn_party WHERE(igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id)"
				+ " "
				+ "AND decode(nvl(SUBSTR(igrs_will_txn_party.will_txn_id,   1,   LENGTH("
				+ "?"
				+ "')),   'NA'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   'NA') "
				+ "LIKE nvl("
				+ "?"
				+ ",   'NA') || '%'"
				+ " "
				+ "AND decode(igrs_will_txn.will_status,   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   'NA') = nvl('"
				+ "?"
				+ ",   'NA')"
				+ " "
				+ "AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   to_char(created_date,   'DD-MM-YY'),"
				+ "decode("
				+ "?"
				+ ",   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY') >="
				+ " to_date(nvl(to_char(to_date('"
				+ "?"
				+ ",   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')"
				+ "AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   "
				+ "?"
				+ ",   "
				+ "?"
				+ ",   to_char(created_date,   'DD-MM-YY'),"
				+ "decode("
				+ "?"
				+ ",   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY') <="
				+ "to_date(nvl(to_char(to_date("
				+ "?"
				+ ",   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')";
	 
	 
	 public static final String RET_DTLS= "SELECT igrs_will_txn_party.will_txn_id, igrs_will_txn_party.first_name,"
				+ "igrs_will_txn_party.middle_name,  igrs_will_txn_party.last_name,"
				+ "igrs_will_txn_party.address, to_char(igrs_will_txn.created_date), igrs_will_txn.will_status"
				+ " "
				+ "FROM igrs_will_txn, igrs_will_txn_party  WHERE igrs_will_txn.will_status ="
				+ "?"
				+ " AND "
				+ "igrs_will_txn.will_txn_id = igrs_will_txn_party.will_txn_id";
	 
	 public static final String UPDT_PRTY_DTLS1="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
				"SIGNATURE_PATH=?,THUMB_PATH=?,PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?,AGENT_PROOF_PATH=?, AGENT_PROOF_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='AGENT'";


	 

	 public static final String UPDT_PRTY_DTLS2="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?," +
				"SIGNATURE_PATH=?,THUMB_PATH=?,PHOTO_NAME=? ,SIGNATURE_NAME=?,THUMB_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='TESTATOR'";


	 public static final String UPDT_PRTY_DTLS3="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
				+ "THUMB_PATH=?,SIGNATURE_PATH=?,DEATH_CERTI_PATH=?,"
				+ "PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?,"
				+ "DEATH_CERTIFICATE_NAME=? WHERE WILL_TXN_ID=? and "
				+ "PARTY_TYPE_ID=?";


	 public static final String UPDT_PRTY_DTLS4="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
				+ "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?, SIGNATURE_NAME=?,AGENT_PROOF_PATH=?,AGENT_PROOF_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='AGENT'";


	 public static final String UPDT_PRTY_DTLS5="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
				+ "THUMB_PATH=?,SIGNATURE_PATH=?,PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=? WHERE WILL_TXN_ID=? and PARTY_TYPE_ID='TESTATOR'";


	 public static final String UPDATE_STATUS_W="Update IGRS_WILL_TXN SET WILL_STATUS='Withdrawn' WHERE WILL_TXN_ID=? ";

	 public static final String UPDATE_STATUS_R="Update IGRS_WILL_TXN SET WILL_STATUS='Retrieved' WHERE WILL_TXN_ID=? ";

	 public static final String UPDT_PRTY_DTLS6="Update IGRS_WILL_TXN_PARTY SET PHOTO_PATH=?,"
			+ "THUMB_PATH=?,SIGNATURE_PATH=?,"
			+ "PHOTO_NAME=?,THUMB_NAME=?,SIGNATURE_NAME=?"
			+ " WHERE WILL_TXN_ID=? and "
			+ "PARTY_TYPE_ID=?";

	 public static final String COUNTRY_QUERY_HINDI_WILL = "Select "
			+ "COUNTRY_NAME,H_COUNTRY_NAME from IGRS_COUNTRY_MASTER "
			+"WHERE COUNTRY_ID=?";

	 public static final String STATE_QUERY_HINDI_WILL = "Select "
			+"STATE_NAME,H_STATE_NAME from IGRS_STATE_MASTER "
			+"Where  STATE_ID=? AND COUNTRY_ID=? ";
	 
	 public static final String DISTRICT_QUERY_HINDI_WILL = "Select "
			+"DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER "
			+" Where STATE_ID=? AND DISTRICT_ID=?";
	 
	 public static final String PHOTOID_PROOF_QUERY_WILL = "Select PHOTO_PROOF_TYPE_ID, "
			+ "PHOTO_PROOF_TYPE_NAME, H_PHOTO_PROOF_TYPE_NAME from IGRS_PHOTOID_PROOF_TYPE_MASTER "
			+"WHERE PHOTO_PROOF_TYPE_STATUS='A' ORDER BY PHOTO_PROOF_TYPE_NAME ASC";
			
	 public static final String PHOTOID_PROOF_NAME = "SELECT TYPE_NAME, H_TYPE_NAME FROM IGRS_COMMON_MASTER WHERE TYPE_ID = ?";
	 
	 public static final String PHOTO_ID_PROOF_TYPE_E = "SELECT TYPE_ID FROM IGRS_COMMON_MASTER WHERE TYPE_NAME = ?";
	 public static final String PHOTO_ID_PROOF_TYPE_H = "SELECT TYPE_ID FROM IGRS_COMMON_MASTER WHERE H_TYPE_NAME = ?";
}
