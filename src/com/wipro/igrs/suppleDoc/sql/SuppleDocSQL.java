package com.wipro.igrs.suppleDoc.sql;

/**
 * ===========================================================================
 * File : SuppleDocSQL.java Description : Represents the SQL Class
 * 
 * Author : Mohit Soni Created Date : Aug 22, 2013
 * 
 * ===========================================================================
 */
public class SuppleDocSQL {
	
	
	public static final String IGRS_CASE_HEAD_MASTER="SELECT CASE_HEAD_ID, CASE_HEAD_NAME FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_STATUS='A'";
	public static final String IGRS_CASE_TYPE_MASTER="SELECT CASE_TYPE_ID, CASE_TYPE_NAME FROM IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID IN (SELECT CASE_TYPE_ID FROM IGRS_CASE_HEAD_TYPE_MAPPING WHERE CASE_HEAD_ID=?)";
	public static final String IGRS_CASE_REVENUE_HEAD_MASTER="SELECT REVENUE_HEAD_ID, REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER WHERE REVENUE_HEAD_STATUS='A'";
	public static final String IGRS_CASE_SECTION_HEAD_MASTER="SELECT SECTION_HEAD_ID, SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER WHERE SECTION_HEAD_STATUS='A'";
	public static final String IGRS_CASE_ACTION_MASTER="SELECT MA.CASE_ACTION_ID, CASE_ACTION_NAME FROM IGRS_CASE_ACTION_MASTER MA, IGRS_CASE_TYPE_ACTION_MAP CT WHERE MA.CASE_ACTION_ID=CT.CASE_ACTION_ID AND CASE_TYPE_ID=? ORDER BY CASE_ACTION_ORDER";
	
	public static final String CAVEATS_CHARGES_INSERT="INSERT INTO IGRS_CAVEAT_TXN(TRANSACTION_ID,INSTITUTE_NAME,INSTITUTE_ADDRESS,INSTITUTE_REP_NAME,COUNTRY_ID,STATE_ID,DISTRICT_ID,POSTAL_CODE,PHONE_NUMBER,CAVEAT_TYPE_ID,STAY_ORDER_NO,STAY_ORDER_DETAILS,CAVEAT_DETAILS,DOCUMENT_NAME,UPLOADED_DOC,REMARKS,REGISTRATION_ID,CAVEAT_STATUS,CAVEAT_LOGGED_DATE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";

    public static final String IGRS_COUNTRY_MASTER="SELECT COUNTRY_ID,COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A'";
    public static final String IGRS_COUNTRY_MASTER_HINDI = "SELECT COUNTRY_ID,H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A'";
    
    public static final String IGRS_COUNTRY_MASTER_INDIA = "SELECT COUNTRY_ID,COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_ID=1 AND COUNTRY_STATUS='A'";
    public static final String IGRS_COUNTRY_MASTER_HINDI_INDIA = "SELECT COUNTRY_ID,H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_ID=1 AND COUNTRY_STATUS='A'";
    
    //By Mohit
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_LIST="SELECT DOCUMENT_ID,DOCUMENT_NAME FROM IGRS_SUPPLI_DOCUMENT_MASTER WHERE DOCUMENT_STATUS='A'";
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_LIST_HINDI="SELECT DOCUMENT_ID,H_DOCUMENT_NAME FROM IGRS_SUPPLI_DOCUMENT_MASTER WHERE DOCUMENT_STATUS='A'";

    
    
    public static final String IGRS_SUPPLI_VIEW_PURPOSE_LIST="SELECT PURPOSE_ID,PURPOSE_NAME FROM IGRS_SUPPLI_AGRIPURPOSE_MASTER WHERE PURPOSE_STATUS='A'";
    public static final String IGRS_SUPPLI_VIEW_PURPOSE_LIST_HINDI="SELECT PURPOSE_ID,H_PURPOSE_NAME FROM IGRS_SUPPLI_AGRIPURPOSE_MASTER WHERE PURPOSE_STATUS='A'";
 
    
    public static final String IGRS_STATE_MASTER="SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_STATUS='A'";
    public static final String IGRS_STATE_MASTER_HINDI="SELECT STATE_ID,H_STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_STATUS='A'";
    
    public static final String IGRS_STATE_MASTER_MP="SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_ID=1";
    public static final String IGRS_STATE_MASTER_MP_HINDI = "SELECT STATE_ID,H_STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_ID=1";
    
    public static final String IGRS_DISTRICT_MASTER="SELECT DISTRICT_ID, DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A'";
    
    
    public static final String IGRS_DISTRICT_MASTER_MP = "SELECT DISTRICT_ID, DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A'";
    public static final String IGRS_DISTRICT_MASTER_HINDI_MP = "SELECT DISTRICT_ID, H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A'";
    
    
    public static final String IGRS_PHOTOID_PROOF_MASTER="SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME FROM IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE PHOTO_PROOF_TYPE_STATUS='A' ORDER BY PHOTO_PROOF_TYPE_NAME ";
    public static final String IGRS_CASTE_MASTER="SELECT CASTE_ID, CASTE_NAME FROM IGRS_CASTE_MASTER WHERE CASTE_STATUS='A' ORDER BY CASTE_NAME";
    public static final String IGRS_RELIGION_MASTER="SELECT RELIGION_ID, RELIGION_NAME FROM IGRS_RELIGION_MASTER WHERE RELIGION_STATUS='A' ORDER BY RELIGION_NAME";
    public static final String IGRS_SUPPLI_PURPOSE_MASTER="SELECT PURPOSE_ID, PURPOSE_NAME FROM IGRS_SUPPLI_PURPOSE_MASTER WHERE PURPOSE_STATUS='A' ORDER BY PURPOSE_NAME";
    public static final String IGRS_DEED_TYPE_MASTER="SELECT DEED_TYPE_ID, DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_STATUS='A' ORDER BY DEED_TYPE_NAME";
    
    public static final String IGRS_ESTAMP_CODE_SEARCH="SELECT ESTAMP_CODE, ESTAMP_AMOUNT, ESTAMP_PURPOSE,ESTAMP_STATUS,CONSMPTN_STATUS FROM IGRS_ESTAMP_DETLS WHERE ESTAMP_CODE=?";
    
    public static final String IGRS_ESTAMP_UPDATE_CONSUME = "update IGRS_ESTAMP_DETLS set consmptn_status=? where estamp_code=?";
    
    	 public static final String IGRS_ESTAMP_UPDATE_NOT_CONSUME ="update IGRS_ESTAMP_DETLS set consmptn_status='Not Consumed' where estamp_code=?";

    	 
    public static final String SELECT_UNIT_LIST_DETAILS="SELECT A.UOM_ID,A.UOM_NAME FROM IGRS_UOM_MASTER A, IGRS_PROP_TYPE_L1_MASTER_DUMMY B WHERE A.UOM_ID=B.UOM_ID AND B.PROP_TYPE_L1_ID=? AND B.PROP_TYPE_L1_STATUS='A'";
    public static final String SELECT_UNIT_LIST_DETAILS_HINDI = "SELECT A.UOM_ID,A.H_UOM_NAME FROM IGRS_UOM_MASTER A, IGRS_PROP_TYPE_L1_MASTER_DUMMY B WHERE A.UOM_ID=B.UOM_ID AND B.PROP_TYPE_L1_ID=? AND B.PROP_TYPE_L1_STATUS='A'";
    
    public static final String IGRS_SUPPLI_INSERT_DOCTXN="INSERT INTO IGRS_SUPPLI_DOC_TXN_DETAILS (SUPP_TXN_ID, PROPERTY_TXN_ID, SUPP_STATUS, CREATED_BY,CREATED_DATE)VALUES(?,?,?,?,TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY'))";
    
    public static final String SELECT_USERNAME_DETAILS="SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,TO_CHAR(sysdate,'DD/MM/YYYY') FROM IGRS_USER_REG_DETAILS WHERE USER_ID=?";
    public static final String SELECT_OFFICE_DETAILS="SELECT OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID=?";
    public static final String SELECT_OFFICE_DETAILS_HINDI="SELECT H_OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID=?";

    
    public static final String IGRS_SUPPLI_INSERT_PARTY="INSERT INTO IGRS_SUPPLI_PARTY_DETAILS (REFERENCE_ID,PARTY_FIRST_NAME_H, PARTY_MIDDLE_NAME_H, PARTY_LAST_NAME_H, PARTY_GENDER, PARTY_AGE,COUNTRY_ID,STATE_ID, DISTRICT_ID, PARTY_ADDRESS,POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER,EMAIL_ID,GUARDIAN_NAME, MOTHER_NAME,PARTY_ROLE,CREATED_BY,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
    public static final String IGRS_SUPPLI_INSERT_PARTY_FULL="INSERT INTO IGRS_SUPPLI_MASTER_TABLE (REFERENCE_ID,TPYE_DOCUMENT_ID, OTHER_DOCUMENT_NAME, BANK_NAME, BANK_COUNTRY_ID,BANK_STATE_ID, BANK_DISTRICT_ID, BANK_AUTH_NAME, BANK_AUTH_DESG,BANK_AUTH_NO, BANK_AUTH_EMAIL, BANK_POSTAL_CODE,BANK_PHONE_NO,EXECUTION_DATE, RECIEPT_DATE,TRANSACTION_AMOUNT,CREATED_BY,STATUS,PAGE_NO,BANK_ADDRESS,CREATED_DATE,OFFICE_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?,sysdate,?)";
    
   
    
    public static final String FLOOR_MASTER_QUERY_HINDI =
    	" SELECT IGRS_PROP_L1_FLOOR_MAP_DUMMY.FLOOR_ID,"+
		" IGRS_FLOOR_MASTER.H_FLOOR_NAME, IGRS_FLOOR_MASTER.FLOOR_DESC "+
		" FROM IGRS_FLOOR_MASTER, IGRS_PROP_TYPE_L1_MASTER_DUMMY,"+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY WHERE ((IGRS_FLOOR_MASTER.FLOOR_ID"+
		" = IGRS_PROP_L1_FLOOR_MAP_DUMMY.FLOOR_ID) AND "+
		" (IGRS_PROP_TYPE_L1_MASTER_DUMMY.PROP_TYPE_L1_ID ="+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY.PROPERTY_TYPE_L1_ID)) AND "+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY.PROPERTY_TYPE_L1_ID = ?"+
		"  AND IGRS_FLOOR_MASTER.FLOOR_STATUS='A' ORDER BY IGRS_FLOOR_MASTER.FLOOR_ID ASC";
    
    
    public static final String FLOOR_MASTER_QUERY =
    	" SELECT IGRS_PROP_L1_FLOOR_MAP_DUMMY.FLOOR_ID,"+
		" IGRS_FLOOR_MASTER.FLOOR_NAME, IGRS_FLOOR_MASTER.FLOOR_DESC "+
		" FROM IGRS_FLOOR_MASTER, IGRS_PROP_TYPE_L1_MASTER_DUMMY,"+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY WHERE ((IGRS_FLOOR_MASTER.FLOOR_ID"+
		" = IGRS_PROP_L1_FLOOR_MAP_DUMMY.FLOOR_ID) AND "+
		" (IGRS_PROP_TYPE_L1_MASTER_DUMMY.PROP_TYPE_L1_ID ="+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY.PROPERTY_TYPE_L1_ID)) AND "+
		" IGRS_PROP_L1_FLOOR_MAP_DUMMY.PROPERTY_TYPE_L1_ID = ?"+
		"  AND IGRS_FLOOR_MASTER.FLOOR_STATUS='A' ORDER BY IGRS_FLOOR_MASTER.FLOOR_ID ASC";
    
    public static final String IGRS_SUPPLI_INSERT_ESTAMP="INSERT INTO IGRS_SUPPLI_DOC_ESTAMP_DETAILS (ESTAMP_CODE,ESTAMP_DUTY, ESTAMP_PURPOSE, CREATED_BY,ESTAMP_DATE,ESTAMP_STATUS,REFERENCE_ID,CREATED_DATE)VALUES(?,?,?,?,?,?,?,sysdate)";
    
    
    public static final String IGRS_SUPPLI_INSERT_FLOOR_DETAILS="INSERT INTO IGRS_SUPPLI_FLOOR_DETAILS (L1_TYPE_ID,L2_TYPE_ID, FLOOR_TYPE_ID, CONSTRUCTED_AREA,AREA,AREA_UNIT_ID,CREATED_BY,REFERENCE_ID,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,sysdate)";
   
    public static final String IGRS_SUPPLI_INSERT_KHASRA_DETAILS="INSERT INTO IGRS_SUPPLI_KHASRA_DETAILS (KHASRA_NUMBER,KHASRA_AREA, LAGAAN, RIN_PUSHTIKA_NUMBER,CREATED_BY,REFERENCE_ID,CREATED_DATE)VALUES(?,?,?,?,?,?,sysdate)";
    
    public static final String IGRS_SUPPLI_INSERT_PROPERTY_DETAILS="INSERT INTO IGRS_SUPPLI_PROPERTY_DETAILS (REFERENCE_ID,VIKAS_KHAND,RI_CIRCLE,LAYOUT_DETAIL,NAZOOL_SHEET_NUMBER,PLOT_NUMBER,EAST_BOUNDARY,WEST_BOUNDARY,NORTH_BOUNDARY,SOUTH_BOUNDARY,AREA_TYPE_ID,GOV_BODY_ID,PROP_TYPE_ID,L1_TYPE_ID,L2_TYPE_ID,AREA_UNIT_ID,AREA,DISTRICT_ID,TEHSIL_ID,WARD_ID,MOHALLA_ID,CREATED_BY,PROPERTY_ADRESS,PROPERTY_LANDMARK,WARD_PATWARI_ID,CREATED_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
    
    public static final String SPLIT_CONSTANT_KHASRA="//";
    
    public static final String IGRS_SUPPLI_INSERT_PSTAMP="INSERT INTO IGRS_SUPPLI_DOC_PSTAMP_DETAILS (PSTAMP_CODE,PSTAMP_DUTY, PSTAMP_SERIES_NUMBER, CREATED_BY,PSTAMP_STATUS,REFERENCE_ID,CREATED_DATE,NAME_OF_VENDOR,DATE_PSTMAP)VALUES(?,?,?,?,?,?,sysdate,?,?)";

    public static final String IGRS_SUPPLI_UPDATE_PARTIAL2 = "UPDATE IGRS_SUPPLI_MASTER_TABLE SET STAMP_DUTY=?,TYPE_AGRI_PURPOSE_ID=?,ACQUSITION_IMP=?,UPDATE_BY=?,STATUS=?,PAGE_NO=?,STAMP_DUTY_NOW=?,DOCUMENT_NUMBER=?,UPDATE_DATE=sysdate,EXEMPTIONS=? where REFERENCE_ID=?";
    public static final String IGRS_SUPPLI_UPDATE_LAST_PAGE="UPDATE IGRS_SUPPLI_MASTER_TABLE SET PRESENTATION_DATE=TO_DATE(?,'DD/MM/YYYY'), SR_NAME=?,SRO_NAME=?,SCANNED_FILE_NAME=?,SCANNED_FILE_PATH=?,UPDATE_BY=?,FILING_NUMBER=?,STATUS=?,PAGE_NO=?,BOOK_TYPE=?,UPDATE_DATE=sysdate where REFERENCE_ID=?";
  
    public static final String IGRS_SUPPLI_UPDATE_REFERENCE_PAGE="UPDATE IGRS_SUPPLI_MASTER_TABLE SET FILING_NUMBER=?,REMARKS=?,UPDATE_DATE=sysdate,STATUS='C' where REFERENCE_ID=?";

    
    public static final String IGRS_SUPPLI_UPDATE_PARTIAL3 = "UPDATE IGRS_SUPPLI_MASTER_TABLE SET STATUS=?,PAGE_NO=?,UPDATE_BY=?,UPDATE_DATE=sysdate where REFERENCE_ID=?";

    public static final String INSERT_SUPPLI_PROP_KHASRA_DETLS = "INSERT INTO IGRS_SUPPLI_KHASRA_DETAILS(REFERENCE_ID,KHASRA_NUMBER,RIN_PUSHTIKA_NUMBER,KHASRA_AREA,LAGAAN,CREATED_BY,CREATED_DATE) VALUES (?,?,?,?,?,?,sysdate)";
    
    public static final String REST_OF_QUERY =" ORDER BY CREATED_DATE DESC";
    
    
    public static final String SELECT_PROPERTY_TYPEL1_DETAILS=
    "SELECT PROP_TYPE_L1_ID,PROP_TYPE_L1_NAME,H_PROP_TYPE_L1_NAME "+
    " FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY" +
    " WHERE PROPERTY_TYPE_ID=?" +
    " AND PROP_TYPE_L1_STATUS='A'" +
   "  AND DISPLAY_FLAG_GUIDLN='Y'" ;

    public static final String SELECT_PROPERTY_TYPEL1_DETAILS_HINDI=  "SELECT PROP_TYPE_L1_ID,H_PROP_TYPE_L1_NAME "+
    " FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY" +
    " WHERE PROPERTY_TYPE_ID=?" +
    " AND PROP_TYPE_L1_STATUS='A'" +
   "  AND DISPLAY_FLAG_GUIDLN='Y'" ;
  
    public static final String SELECT_BOOK_TYPE_DETAILS="SELECT BOOK_TYPE_ID,BOOK_TYPE_NAME FROM IGRS_REG_BOOK_TYPE_MASTER where book_type_name='S1'";
    
    
    
    public static final String SELECT_PROPERTY_TYPE_L2_DETAILS="SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME "
+ " FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY "+
" WHERE PROPERTY_TYPE_L1_ID=? "+
" AND PROPERTY_TYPE_L2_STATUS='A'"+
" AND DISPLAY_FLAG IN ('G','B')";
    
    public static final String SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI="SELECT PROPERTY_TYPE_L2_ID,H_PROPERTY_TYPE_L2_NAME "
    	+ " FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY "+
    	" WHERE PROPERTY_TYPE_L1_ID=? "+
    	" AND PROPERTY_TYPE_L2_STATUS='A'"+
    	" AND DISPLAY_FLAG IN ('G','B')";;

    
    
    public static final String IGRS_SUPPLI_INSERT_DEED_DETAILS="INSERT INTO IGRS_SUPPLI_DOC_DEED_DETAILS(SUPPLI_DOC_TXN_ID, DEED_TYPE_ID, EXECUTION_DATE, PRESENTATION_DATE, STAMP_DUTY_PAID, DOCUMENT_NO,ESTAMP_CODE, LOAN_PURPOSE_ID, LOAN_AMOUNT) VALUES(?,?,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'),?,?,?,?,?)";
    public static final String IGRS_SUPPLI_INSERT_BANK_DETAILS="INSERT INTO IGRS_SUPPLI_DOC_BANK_DETAILS (SUPPLI_DOC_TXN_ID, BANK_NAME, BANK_ADDRESS, DISTRICT_ID, POSTAL_CODE,NAME_OF_AUTHORITY, DESIGNATION, PHONE_NUMBER,MOBILE_NUMBER, EMAIL_ID) VALUES(?,?,?,?,?,?,?,?,?,?)";
    public static final String IGRS_SUPPLI_VIEW_DOC_DETAILS="SELECT SUPP_TXN_ID,ESTAMP_CODE, SUPP_STATUS, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_SUPPLI_DOC_DEED_DETAILS DD,IGRS_SUPPLI_DOC_TXN_DETAILS TXN WHERE SUPPLI_DOC_TXN_ID=SUPP_TXN_ID AND (SUPP_TXN_ID=? OR CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY'))";
    public static final String IGRS_SUPPLI_VIEW_ENTIRE_DETAILS="SELECT ISDDD.suppli_doc_txn_id,ISDDD.deed_type_id,(SELECT deed_type_name FROM igrs_deed_type_master WHERE deed_type_id = ISDDD.deed_type_id) ,TO_CHAR(ISDDD.execution_date,'DD/MM/YYYY'),TO_CHAR(ISDDD.presentation_date,'DD/MM/YYYY'),ISDDD.stamp_duty_paid,ISDDD.document_no,ISDDD.estamp_code,(SELECT PURPOSE_NAME FROM IGRS_SUPPLI_PURPOSE_MASTER WHERE PURPOSE_ID=ISDDD.loan_purpose_id),ISDDD.loan_amount,ISDDD.deed_document,ISDDD.deed_doc_extn,ISDDD.deed_doc_name,ISDTD.supp_txn_id,ISDTD.property_txn_id,ISDTD.supp_status,ISDTD.created_by,TO_CHAR(ISDTD.created_date,'DD/MM/YYYY'),ISDTD.update_by,ISDTD.update_date,ISDBD.suppli_doc_txn_id,ISDBD.bank_name,ISDBD.bank_branch,ISDBD.bank_address,ISDBD.district_id,(SELECT district_name FROM igrs_district_master WHERE district_id = isdbd.district_id) ,ISDBD.postal_code,ISDBD.name_of_authority,ISDBD.designation,ISDBD.phone_number,ISDBD.mobile_number,ISDBD.email_id,ISPD.suppli_txn_id,ISPD.party_type_id,ISPD.party_first_name_h,ISPD.party_middle_name_h,ISPD.party_last_name_h,ISPD.party_gender," +
    		" ISPD.party_age, ISPD.nationality,ISPD.country_id, (SELECT country_name FROM igrs_country_master WHERE country_id = ISPD.country_id), ISPD.state_id, (SELECT state_name FROM igrs_state_master WHERE state_id  = ISPD.state_id), ISPD.district_id, (SELECT district_name FROM igrs_district_master WHERE district_id = ISPD.district_id) , ISPD.party_address, ISPD.postal_code, ISPD.phone_number, ISPD.mobile_number, ISPD.email_id,(SELECT photo_proof_type_NAME FROM IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE photo_proof_type_id=ISPD.photo_proof_type_id), ISPD.photo_proof_id, ISPD.guardian_name, ISPD.mother_name, ISPD.party_txn_id, ISPD.occupation,"+
    		" ISPD.physically_challenged,ISPD.user_id,ISPD.religion_id,(SELECT religion_name FROM igrs_religion_master WHERE religion_id = ISPD.religion_id),ISPD.caste_id,(SELECT caste_name FROM igrs_caste_master WHERE caste_id = ISPD.caste_id) FROM igrs_suppli_doc_deed_details ISDDD,igrs_suppli_doc_txn_details ISDTD, " +
    		" igrs_suppli_doc_bank_details ISDBD,igrs_suppli_party_details ISPD WHERE ((ISDTD.supp_txn_id = ISDDD.suppli_doc_txn_id ) AND (ISDTD.supp_txn_id = ISDBD.suppli_doc_txn_id) AND (ISDTD.supp_txn_id =ISPD.suppli_txn_id) AND (ISPD.suppli_txn_id =?  ))";
	
    
    
	public static final String PENDING_DETL = "SELECT REFERENCE_ID,TO_CHAR(CREATED_DATE,'dd/MM/yyyy hh:mi:ss AM'),STATUS,PAGE_NO FROM IGRS_SUPPLI_MASTER_TABLE WHERE CREATED_BY=? AND (STATUS=? OR STATUS=?) AND OFFICE_ID=? ORDER BY CREATED_DATE DESC";

    public static final String IGRS_SUPPLI_SELECT_STAMPDUTY  = "SELECT STAMP_DUTY,STAMP_DUTY_NOW FROM IGRS_SUPPLI_MASTER_TABLE WHERE REFERENCE_ID=?";	
    
    
    public static final String IGRS_SUPPLI_SELECT_DISTRICT_ID  = "SELECT DISTRICT_ID FROM IGRS_SUPPLI_PROPERTY_DETAILS WHERE REFERENCE_ID=?";	

    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_1="SELECT FILING_NUMBER, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_SUPPLI_MASTER_TABLE  WHERE FILING_NUMBER=? AND STATUS='C' AND OFFICE_ID=? AND FILING_NUMBER IS NOT NULL";
    
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_2="SELECT FILING_NUMBER, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_SUPPLI_MASTER_TABLE  WHERE (TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'DD/MM/YYYY')) AND TO_CHAR(TO_DATE(?,'DD/MM/YYYY'))) AND STATUS='C' AND OFFICE_ID=? AND FILING_NUMBER IS NOT NULL  ";
    
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_DETAILS_3="SELECT FILING_NUMBER, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_SUPPLI_MASTER_TABLE  WHERE FILING_NUMBER=? AND (TRUNC(CREATED_DATE) BETWEEN TO_CHAR(TO_DATE(?,'DD/MM/YYYY')) AND TO_CHAR(TO_DATE(?,'DD/MM/YYYY'))) AND STATUS='C' AND OFFICE_ID=? AND FILING_NUMBER IS NOT NULL ";
    
    public static final String IGRS_SUPPLI_VIEW_SINGLE_DETAILS="SELECT REFERENCE_ID,TPYE_DOCUMENT_ID,OTHER_DOCUMENT_NAME,BANK_NAME,BANK_COUNTRY_ID,BANK_DISTRICT_ID,BANK_STATE_ID,BANK_AUTH_NAME,BANK_AUTH_DESG,BANK_AUTH_NO,BANK_AUTH_EMAIL,BANK_POSTAL_CODE,BANK_PHONE_NO,TO_CHAR(EXECUTION_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(RECIEPT_DATE,'dd/MM/yyyy hh:mi:ss AM'),TRANSACTION_AMOUNT,TYPE_AGRI_PURPOSE_ID,ACQUSITION_IMP,TO_CHAR(PRESENTATION_DATE,'dd/MM/yyyy hh:mi:ss AM'),SR_NAME,SRO_NAME,SCANNED_FILE_NAME,STAMP_DUTY,BANK_ADDRESS,BOOK_TYPE,SCANNED_FILE_PATH,DOCUMENT_NUMBER,STAMP_DUTY_NOW,REMARKS,EXEMPTIONS FROM IGRS_SUPPLI_MASTER_TABLE WHERE FILING_NUMBER=?";
    
    public static final String IGRS_SUPPLI_VIEW_SINGLE_DETAILS_HINDI="SELECT REFERENCE_ID,TPYE_DOCUMENT_ID,OTHER_DOCUMENT_NAME,BANK_NAME,BANK_COUNTRY_ID,BANK_DISTRICT_ID,BANK_STATE_ID,BANK_AUTH_NAME,BANK_AUTH_DESG,BANK_AUTH_NO,BANK_AUTH_EMAIL,BANK_POSTAL_CODE,BANK_PHONE_NO,TO_CHAR(EXECUTION_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(RECIEPT_DATE,'dd/MM/yyyy hh:mi:ss AM'),TRANSACTION_AMOUNT,TYPE_AGRI_PURPOSE_ID,ACQUSITION_IMP,TO_CHAR(PRESENTATION_DATE,'dd/MM/yyyy hh:mi:ss AM'),SR_NAME,SRO_NAME,SCANNED_FILE_NAME,STAMP_DUTY,BANK_ADDRESS,BOOK_TYPE,SCANNED_FILE_PATH,DOCUMENT_NUMBER,STAMP_DUTY_NOW,REMARKS,EXEMPTIONS FROM IGRS_SUPPLI_MASTER_TABLE WHERE FILING_NUMBER=?";
    
    public static final String IGRS_SUPPLI_VIEW_SINGLE_DETAILS_REF="SELECT REFERENCE_ID,TPYE_DOCUMENT_ID,OTHER_DOCUMENT_NAME,BANK_NAME,BANK_COUNTRY_ID,BANK_DISTRICT_ID,BANK_STATE_ID,BANK_AUTH_NAME,BANK_AUTH_DESG,BANK_AUTH_NO,BANK_AUTH_EMAIL,BANK_POSTAL_CODE,BANK_PHONE_NO,TO_CHAR(EXECUTION_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(RECIEPT_DATE,'dd/MM/yyyy hh:mi:ss AM'),TRANSACTION_AMOUNT,TYPE_AGRI_PURPOSE_ID,ACQUSITION_IMP,TO_CHAR(PRESENTATION_DATE,'dd/MM/yyyy hh:mi:ss AM'),SR_NAME,SRO_NAME,SCANNED_FILE_NAME,STAMP_DUTY,BANK_ADDRESS,BOOK_TYPE,SCANNED_FILE_PATH,DOCUMENT_NUMBER,STAMP_DUTY_NOW,EXEMPTIONS FROM IGRS_SUPPLI_MASTER_TABLE WHERE REFERENCE_ID=?";
 
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS="SELECT DOCUMENT_NAME FROM IGRS_SUPPLI_DOCUMENT_MASTER WHERE DOCUMENT_STATUS='A' AND DOCUMENT_ID=?";
    
    public static final String IGRS_SUPPLI_VIEW_DOCUMENT_TYPE_DETAILS_HINDI="SELECT H_DOCUMENT_NAME FROM IGRS_SUPPLI_DOCUMENT_MASTER WHERE DOCUMENT_STATUS='A' AND DOCUMENT_ID=?";

    public static final String IGRS_SUPPLI_VIEW_PARTY_TYPE_DETAILS_LIST = "SELECT PARTY_FIRST_NAME_H,PARTY_LAST_NAME_H,GUARDIAN_NAME FROM IGRS_SUPPLI_PARTY_DETAILS WHERE REFERENCE_ID=?";

    public static final String IGRS_SUPPLI_VIEW_PSTAMP_DETAILS = "SELECT PSTAMP_CODE,PSTAMP_DUTY,PSTAMP_SERIES_NUMBER,NAME_OF_VENDOR,DATE_PSTMAP FROM IGRS_SUPPLI_DOC_PSTAMP_DETAILS WHERE REFERENCE_ID=? AND PSTAMP_STATUS='A'";
    public static final String IGRS_SUPPLI_VIEW_ESTAMP_DETAILS = "SELECT ESTAMP_CODE,ESTAMP_DUTY,ESTAMP_PURPOSE FROM IGRS_SUPPLI_DOC_ESTAMP_DETAILS WHERE REFERENCE_ID=? AND ESTAMP_STATUS='A'";
 public static final String IGRS_SUPPLI_VIEW_DISTRICT_DETAILS  = "SELECT  DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=? AND DISTRICT_STATUS='A'";

 public static final String IGRS_SUPPLI_VIEW_DISTRICT_DETAILS_HINDI  = "SELECT  H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=? AND DISTRICT_STATUS='A'";


 public static final String IGRS_SUPPLI_SELECT_DISTRICT_ID_VIEW  = "SELECT DISTRICT_ID FROM IGRS_SUPPLI_PROPERTY_DETAILS WHERE REFERENCE_ID=?";	

 public static final String IGRS_SUPPLI_VIEW_ENTIRE_PARTY_DETAILS="SELECT PARTY_FIRST_NAME_H, PARTY_MIDDLE_NAME_H, PARTY_LAST_NAME_H, PARTY_GENDER, PARTY_AGE,COUNTRY_ID,STATE_ID, DISTRICT_ID, PARTY_ADDRESS,POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER,EMAIL_ID,GUARDIAN_NAME, MOTHER_NAME,PARTY_ROLE,CREATED_BY,CREATED_DATE,PARTY_ADDRESS FROM IGRS_SUPPLI_PARTY_DETAILS WHERE PARTY_FIRST_NAME_H=? AND PARTY_LAST_NAME_H=? AND GUARDIAN_NAME=? AND REFERENCE_ID=?";
 
 public static final String IGRS_SUPPLI_GET_DISTRICT_NAME="SELECT  DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=? AND DISTRICT_STATUS='A'";
 
 public static final String IGRS_SUPPLI_GET_DISTRICT_NAME_HINDI="SELECT  H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=? AND DISTRICT_STATUS='A'";
 
 public static final String IGRS_SUPPLI_GET_TEHSIL_NAME="SELECT  TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=? AND TEHSIL_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_TEHSIL_NAME_HINDI="SELECT  H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=? AND TEHSIL_STATUS='A'";

 
 public static final String IGRS_SUPPLI_GET_PROPERTY_TYPE_NAME="SELECT  PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_ID=? AND PROPERTY_TYPE_STATUS='A'";
 
 public static final String IGRS_SUPPLI_GET_PROPERTY_TYPE_NAME_HINDI="SELECT  H_PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_ID=? AND PROPERTY_TYPE_STATUS='A'";
 
 public static final String IGRS_SUPPLI_GET_AREA_TYPE_NAME="SELECT AREA_TYPE_NAME  FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND AREA_TYPE_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_AREA_TYPE_NAME_HINDI="SELECT H_AREA_TYPE_NAME  FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND AREA_TYPE_STATUS='A'";

 
 public static final String IGRS_SUPPLI_GET_MUNICIPAL_BODY_NAME="SELECT SUB_AREA_TYPE_NAME  FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_MUNICIPAL_BODY_NAME_HINDI="SELECT H_SUB_AREA_TYPE_NAME  FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

 
 public static final String IGRS_SUPPLI_GET_MOHALLA_NAME="SELECT COLONY_NAME  FROM IGRS_COLONY_MASTER WHERE COLONY_ID=? AND COLONY_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_MOHALLA_NAME_HINDI="SELECT H_COLONY_NAME  FROM IGRS_COLONY_MASTER WHERE COLONY_ID=? AND COLONY_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_PW_NAME="SELECT WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_ID=? AND WARD_PATWARI_STATUS='A'";

 public static final String IGRS_SUPPLI_GET_PW_NAME_HINDI="SELECT H_WARD_PATWARI_NAME  FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_ID=? AND WARD_PATWARI_STATUS='A'";

 
 public static final String IGRS_SUPPLI_GET_REFERENCE_ID="SELECT REFERENCE_ID  FROM IGRS_SUPPLI_MASTER_TABLE WHERE FILING_NUMBER=?";

 
 
public static final String IGRS_SUPPLI_GET_PURPOSES_NAME="SELECT PURPOSE_NAME FROM IGRS_SUPPLI_AGRIPURPOSE_MASTER WHERE PURPOSE_ID=?";


public static final String IGRS_SUPPLI_GET_PURPOSES_NAME_HINDI="SELECT H_PURPOSE_NAME FROM IGRS_SUPPLI_AGRIPURPOSE_MASTER WHERE PURPOSE_ID=?";


public static final String GET_PROP_KHASRA_DETLS_DISPLAY ="SELECT KHASRA_NUMBER,KHASRA_AREA,LAGAAN,RIN_PUSHTIKA_NUMBER "+
		  "FROM IGRS_SUPPLI_KHASRA_DETAILS "+
		  "WHERE REFERENCE_ID=?";

public static final String GET_PROP_DETLS_DISPLAY = "SELECT VIKAS_KHAND,RI_CIRCLE,LAYOUT_DETAIL,NAZOOL_SHEET_NUMBER,PLOT_NUMBER,EAST_BOUNDARY,WEST_BOUNDARY,NORTH_BOUNDARY,SOUTH_BOUNDARY,PROPERTY_ADRESS,PROPERTY_LANDMARK,AREA_TYPE_ID,GOV_BODY_ID,PROP_TYPE_ID,L1_TYPE_ID,L2_TYPE_ID,AREA_UNIT_ID,AREA,DISTRICT_ID,TEHSIL_ID,WARD_ID,MOHALLA_ID,WARD_PATWARI_ID FROM IGRS_SUPPLI_PROPERTY_DETAILS WHERE REFERENCE_ID=?";

public static final String GET_FLOOR_DETLS = "select (select floor_name from igrs_floor_master where floor_id=d.floor_type_id),(select PROP_TYPE_L1_NAME from IGRS_PROP_TYPE_L1_MASTER_DUMMY where PROP_TYPE_L1_ID=d.l1_type_id),(select PROPERTY_TYPE_L2_NAME from IGRS_PROP_TYPE_L2_MASTER_DUMMY where PROPERTY_TYPE_L2_ID=d.l2_type_id),(select UOM_NAME from IGRS_UOM_MASTER where UOM_ID=d.area_unit_id),d.constructed_area,d.area from igrs_suppli_floor_details d where d.reference_id=?";
public static final String GET_FLOOR_DETLS_HINDI = "select (select h_floor_name from igrs_floor_master where floor_id=d.floor_type_id),(select H_PROP_TYPE_L1_NAME from IGRS_PROP_TYPE_L1_MASTER_DUMMY where PROP_TYPE_L1_ID=d.l1_type_id),(select H_PROPERTY_TYPE_L2_NAME from IGRS_PROP_TYPE_L2_MASTER_DUMMY where PROPERTY_TYPE_L2_ID=d.l2_type_id),(select h_UOM_NAME from IGRS_UOM_MASTER where UOM_ID=d.area_unit_id),d.constructed_area,d.area from igrs_suppli_floor_details d where d.reference_id=?";

public static final String GET_MAX_DATE  = "SELECT max(TO_CHAR(UPDATE_DATE,'DD/MM/YYYY')) from IGRS_SUPPLI_MASTER_TABLE";
	public static final String CREATE_SEQUENCE_FILING = "CREATE SEQUENCE IGRS_SUPPLEDOC_FILING_ID INCREMENT BY 1 START WITH 1 MAXVALUE 999999 MINVALUE 1 NOCACHE";

	public static final String GET_REFERENCE_SEQ_ID = "select IGRS_SUPPLEDOC_REF_ID_SEQ.nextval from dual";
	public static final String GET_SEQUENCE_NUMBER ="select IGRS_SUPPLEDOC_TXN_ID.nextval from dual";
	
	
	public static final String TEHSIL_QUERY = "Select  TEHSIL_ID, "
	    +"TEHSIL_NAME from IGRS_TEHSIL_MASTER "
	    +"Where DISTRICT_ID=? AND TEHSIL_STATUS='A' "
	    +"ORDER BY TEHSIL_NAME ASC";
	
	public static final String TEHSIL_QUERY_HINDI = "Select  TEHSIL_ID, "
	    +"H_TEHSIL_NAME from IGRS_TEHSIL_MASTER "
	    +"Where DISTRICT_ID=? AND TEHSIL_STATUS='A' "
	    +"ORDER BY H_TEHSIL_NAME ASC";
	
	public static final String AREA_TYPE_QUERY =
		"SELECT AREA_TYPE_ID,AREA_TYPE_NAME,H_AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER "
		+" WHERE AREA_TYPE_STATUS='A' ORDER BY AREA_TYPE_NAME ASC";
	
	public static final String AREA_TYPE_QUERY_HINDI =
		"SELECT AREA_TYPE_ID,H_AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER "
		+" WHERE AREA_TYPE_STATUS='A' ORDER BY H_AREA_TYPE_NAME ASC";
	
	public static final String WARD_QUERY = "Select WARD_PATWARI_ID, "
		+"WARD_PATWARI_NAME from IGRS_WARD_PATWARI_MASTER "
		+"Where TEHSIL_ID=? AND WARD_PATWARI_STATUS='A' "
		+"AND AREA_TYPE_ID=? AND WARD_OR_PATWARI=? "
		+"ORDER BY WARD_PATWARI_NAME ASC";
	
	public static final String WARD_QUERY_HINDI = "Select WARD_PATWARI_ID, "
		+"H_WARD_PATWARI_NAME from IGRS_WARD_PATWARI_MASTER "
		+"Where TEHSIL_ID=? AND WARD_PATWARI_STATUS='A' "
		+"AND AREA_TYPE_ID=? AND WARD_OR_PATWARI=? "
		+"ORDER BY H_WARD_PATWARI_NAME ASC";
	
	
	public static final String MOHALLA_QUERY = "Select MOHALLA_VILLAGE_ID, "
		+"MOHALLA_VILLAGE_NAME from IGRS_MOHALLA_VILLAGE_MASTER "
		+"Where WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_STATUS='A' "
		+"ORDER BY MOHALLA_VILLAGE_NAME ASC";
	
	public static final String MOHALLA_QUERY_HINDI = "Select MOHALLA_VILLAGE_ID, "
		+"H_MOHALLA_VILLAGE_NAME from IGRS_MOHALLA_VILLAGE_MASTER "
		+"Where WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_STATUS='A' "
		+"ORDER BY H_MOHALLA_VILLAGE_NAME ASC";
	
	
	public static final String PROPERTY_TYPE_SELECT_QUERY =
		"SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME "
		+"FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A' "
		+"ORDER BY PROPERTY_TYPE_NAME ASC";
	
	public static final String PROPERTY_TYPE_SELECT_QUERY_HINDI =
		"SELECT PROPERTY_TYPE_ID,H_PROPERTY_TYPE_NAME "
		+"FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A' "
		+"ORDER BY H_PROPERTY_TYPE_NAME ASC";
	
	public static final String MUNICIPAL_BODY_SELECT_QUERY =
		 "SELECT MUNICIPAL_BODY_ID,MUNICIPAL_BODY_NAME  FROM "
		+"IGRS_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_STATUS='A' "
		+"ORDER BY MUNICIPAL_BODY_NAME ASC";
	
	public static final String MUNICIPAL_BODY_SELECT_QUERY_HINDI =
		 "SELECT MUNICIPAL_BODY_ID,H_MUNICIPAL_BODY_NAME  FROM "
		+"IGRS_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_STATUS='A' "
		+"ORDER BY H_MUNICIPAL_BODY_NAME ASC";
	
	
	//Added for changes in property page
	
	public static String SUB_AREA_NAME_EN="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";
	
	public static String SUB_AREA_NAME_HI="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

	public static String SUB_AREA_NAME_EN_UR="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";
	
	public static String SUB_AREA_NAME_HI_UR="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

	public static String GET_MUNICIPAL_ID="SELECT MUNICIPAL_BODY_ID FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=?";

	public static String WARD_PATWARI_NAME_EN="SELECT WPM.WARD_PATWARI_ID,WPM.WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";
	
	public static String WARD_PATWARI_NAME_HI="SELECT WPM.WARD_PATWARI_ID ,WPM.H_WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";
	
	public static String COLONY_NAME_EN="SELECT C.COLONY_ID, C.COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";
	
	public static String COLONY_NAME_HI="SELECT C.COLONY_ID, C.H_COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";
}