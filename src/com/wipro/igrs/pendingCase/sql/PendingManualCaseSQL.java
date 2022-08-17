package com.wipro.igrs.pendingCase.sql;

public class PendingManualCaseSQL {
	public static final String GETREVENUEHEADLIST    ="select revenue_head_name,revenue_head_id from igrs_manual_rev_head_master where status='A'";
	public static final String GETREVENUEHEADLIST_H  ="select h_revenue_head_name,revenue_head_id from igrs_manual_rev_head_master where status='A'";
	public static final String GETPENDINGCASESECTONLIST="select section_name,section_id from igrs_pndng_case_section_master where status='A' and revenue_head_id=?";
	public static final String GETPENDINGCASESECTONLIST_H="select h_section_name,section_id from igrs_pndng_case_section_master where status='A' and revenue_head_id=?";
	public static final String Save_Case_Sequence = "select IGRS_PNDG_MANUAL_CASE_SEQ.NEXTVAL from dual";
	public static final String Save_Case_Data_Section = "INSERT INTO igrs_pending_cases_txn_detls(CASE_TXN_ID,CASE_TYPE,REVENUE_HEAD,SECTION,CASE_NUMBER,DATE_OF_LODGING, MARKET_VALUE_DOCUMENT, " +
				"PRAPOSED_SD_SUB_REG,STATUS,ORDER_DATE,MARKET_VALUE,ORDER_NUMBER, STAMP_DUTY, REG_FEE,PAYMENT_TYPE,PAYABLE_AMOUNT,DATE_OF_PAYMENT,REMARK_OF_PAYMENT,MARKET_VALUE_AUDIT, AUDIT_STAMP_DUTY,AUDIT_REG_FEE," +
				"AUDIT_TOTAL,IMPOUND_STAMP_DUTY, DEFICIT_STAMP_DUTY_AUDIT,DEFICIT_STAMP_DUTY_COLLECTOR,ESTIMATED_DEFICIT_STAMP_DUTY,STAMP_CASE_NUMBER," +
				"RRC_CASE_NUMBER,PAID_AMOUNT,REMAINING_AMOUNT,OFFICE_ID,CREATED_DATE,CREATED_BY,REMARKS,AMOUNT,PROPOSED_MRK_VALUE_SR)" +
				"VALUES( ?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?)";

	public static final String getRevenueHeadName = "select revenue_head_name from igrs_manual_rev_head_master where revenue_head_id=?";
	public static final String getSectionHeadName = "select section_name from igrs_pndng_case_section_master where section_id=?";
	public static final String GET_TODAY_APP_COUNT = "select count(CASE_TXN_ID) from IGRS_PENDING_CASES_TXN_DETLS rt where  to_char(RT.CREATED_DATE  ,'MON-YYYY') = to_char(sysdate ,'MON-YYYY')";
    public static final String DROP_PENDING_SEQ_1 = "DROP SEQUENCE IGRS_PNDG_MANUAL_CASE_SEQ";
    public static final String CREATE_PENDING_ID_SEQ_1="CREATE SEQUENCE IGRS_PNDG_MANUAL_CASE_SEQ START WITH 1 INCREMENT BY 1 MAXVALUE 999999 MINVALUE 1 NOCYCLE  NOCACHE ORDER";

    public static final String get_case_details_1 = "select a.case_txn_id,a.case_number, a.STAMP_CASE_NUMBER ,a.RRC_CASE_NUMBER ,a.CASE_TYPE , c.revenue_head_name,b.section_name,a.STATUS,a.DATE_OF_LODGING  from igrs_pending_cases_txn_detls a, igrs_pndng_case_section_master b, IGRS_MANUAL_REV_HEAD_MASTER c  where a.case_type = ? and a.DATE_OF_LODGING  = to_date(?, 'dd-mm-yyyy') and a.CREATED_BY = ? and a.section =b.section_id and a.revenue_head = c.revenue_head_id and a.OFFICE_ID = ?"; 
    public static final String get_case_details_2 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
    "and a.CREATED_BY = ?" +
    " and a.OFFICE_ID = ?";
    
    
    public static final String get_case_details_3 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
    "AND a.section         =? " +
    " and a.CREATED_BY = ?" +
    " and a.OFFICE_ID = ?";
    
    
    public static final String get_case_details_4 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
    "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
    "AND a.STATUS          =? " +
    " and a.CREATED_BY = ?" +
    " and a.OFFICE_ID = ?" ;
    
    
    public static final String get_case_details_5 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
    "AND a.STATUS          =?" +
    " and a.CREATED_BY = ?" +
    " and a.OFFICE_ID = ?" ;


    public static final String get_case_details_6 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
    " and a.CREATED_BY = ? " +
    "  and a.OFFICE_ID = ?" ;
   // "AND a.STATUS          =?" ;
    
    public static final String get_case_details_7 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
   // "AND a.PAYMENT_TYPE    =? " ;
    "AND a.STATUS          =?" +
    " and a.CREATED_BY = ? " +
    "  and a.OFFICE_ID = ?" ;


    public static final String get_case_details_8 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
   // "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
    "AND a.STATUS          =?" +
    " and a.CREATED_BY = ? " +
    "  and a.OFFICE_ID = ?" ;

    public static final String get_case_details_9 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
   // "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
    " and a.CREATED_BY = ? " +
    "  and a.OFFICE_ID = ?" ;
  //  "AND a.STATUS          =?" ;


    public static final String get_case_details_10 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
   // "AND a.revenue_head    = ? " +
  //  "AND a.section         =? " +
    //"AND a.PAYMENT_TYPE    =? " ;
    "AND a.STATUS          =?" +
    " and a.CREATED_BY = ? " +
    " and a.OFFICE_ID = ?" ;
    
    public static final String get_case_details_11 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
    "AND a.section         =? " +
    //"AND a.PAYMENT_TYPE    =? " ;
    "AND a.STATUS          =?" +
    " and a.CREATED_BY = ? " +
    " and a.OFFICE_ID = ?" ;

    public static final String get_case_details_12 = "SELECT a.case_txn_id, " +
    "  a.case_number, " +
    "  a.STAMP_CASE_NUMBER , " +
    "  a.RRC_CASE_NUMBER , " +
    "  a.CASE_TYPE , " +
    "  c.revenue_head_name, " +
    "  b.section_name, " +
    "  a.STATUS, " +
    "  a.DATE_OF_LODGING " +
    "FROM igrs_pending_cases_txn_detls a, " +
    "  igrs_pndng_case_section_master b, " +
    "  IGRS_MANUAL_REV_HEAD_MASTER c " +
    "WHERE a.case_type     = ? " +
    "AND a.DATE_OF_LODGING = to_date(?, 'dd-mm-yyyy') " +
    "AND a.section         =b.section_id " +
    "AND a.revenue_head    = c.revenue_head_id " +
    "AND a.revenue_head    = ? " +
    "AND a.section         =? " +
    "AND a.PAYMENT_TYPE    =? " +
   // "AND a.STATUS          =?" +
    " and a.CREATED_BY = ? " +
    " and a.OFFICE_ID = ?" ;

    
public static final String caseDetails = "SELECT a.CASE_TXN_ID, " +
"  a.CASE_TYPE, " +
"  c.revenue_head_name, " +
"  a.REVENUE_HEAD, " +
"  a.SECTION, " +
"  b.section_name, " +
"  a.CASE_NUMBER, " +
"  a.DATE_OF_LODGING, " +
"  a.MARKET_VALUE_DOCUMENT, " +
"  a.PRAPOSED_SD_SUB_REG, " +
"  a.STATUS, " +
"  a.ORDER_DATE, " +
"  a.MARKET_VALUE, " +
"  a.ORDER_NUMBER, " +
"  a.STAMP_DUTY, " +
"  a.REG_FEE, " +
"  a.PAYMENT_TYPE, " +
"  a.PAYABLE_AMOUNT, " +
"  a.DATE_OF_PAYMENT, " +
"  a.REMARK_OF_PAYMENT, " +
"  a.MARKET_VALUE_AUDIT, " +
"  a.AUDIT_STAMP_DUTY, " +
"  a.AUDIT_REG_FEE, " +
"  a.AUDIT_TOTAL, " +
"  a.IMPOUND_STAMP_DUTY, " +
"  a.DEFICIT_STAMP_DUTY_AUDIT, " +
"  a.DEFICIT_STAMP_DUTY_COLLECTOR, " +
"  a.ESTIMATED_DEFICIT_STAMP_DUTY, " +
"  a.STAMP_CASE_NUMBER, " +
"  a.RRC_CASE_NUMBER, " +
"  a.PAID_AMOUNT, " +
"  a.REMAINING_AMOUNT, " +
"  a.OFFICE_ID, " +
"  a.CREATED_DATE, " +
"  a.CREATED_BY, " +
"  a.UPDATED_DATE, " +
"  a.UPDATED_BY, " +
"  a.REMARKS, " +
"  a.AMOUNT," +
"  a.UPDATE_REMARKS, " +
"  a.PROPOSED_MRK_VALUE_SR " +
"FROM igrs_pending_cases_txn_detls a, " +
"  igrs_pndng_case_section_master b, " +
"  IGRS_MANUAL_REV_HEAD_MASTER c " +
"WHERE a.case_txn_id = ? " +
"AND a.revenue_head  = c.revenue_head_id " +
"AND a.section       = b.section_id";


public static final String update_stamp_cases = "UPDATE igrs_pending_cases_txn_detls " +
"  SET STATUS     =?, " +
"  ORDER_DATE   =to_date(?,'dd-mm-yyyy'), " +
"  order_number =?, " +
"  MARKET_VALUE =?, " +
"  STAMP_DUTY   =?, " +
"  REG_FEE      =?, " +
"  AMOUNT       =?, " +
"  payment_type  =?, " +
"  DATE_OF_PAYMENT =to_date(?,'dd-mm-yyyy'), " +
"  remarks =?, " +
"  REMARK_OF_PAYMENT =?," +
"  updated_date = sysdate," +
"  UPDATED_BY = ?," +
"  UPDATE_REMARKS =?"+
"  WHERE CASE_TXN_ID =?";


public static final String update_rrc_cases = "UPDATE igrs_pending_cases_txn_detls " +
"  SET STATUS     =?, " +
"  PAYABLE_AMOUNT   =?, " +
"  PAID_AMOUNT =?, " +
"  REMAINING_AMOUNT =?, " +
"  remarks   =?, " +
"  DATE_OF_LODGING  =to_date(?,'dd-mm-yyyy'), " +
"  updated_date = sysdate," +
"  UPDATED_BY = ?," +
"  UPDATE_REMARKS =?," +
"  payment_type =?"+
"  WHERE CASE_TXN_ID =?";




public static final String DISTRICT_QUERY_HINDI = "Select DISTRICT_ID, "
	+ "DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER "
	+ " Where STATE_ID=? AND DISTRICT_STATUS='A' "
	+ "ORDER BY DISTRICT_NAME ASC";
public static final String GET_OFFICE_TYPE_ID = "select office_type_id from igrs_office_master where OFFICE_ID=?";

public static final String GET_DIST_NAME_DR_DIG = "Select DISTRICT_ID,district_name,h_district_name from igrs_district_master where district_status='A' "
	+ " and district_id in( select district_id from igrs_office_master where office_status='A' "
	+ "and parent_id in(?)) ORDER BY DISTRICT_NAME ASC ";

public static final String GET_DIST_NAME_SR = "Select DISTRICT_ID,district_name,h_district_name from igrs_district_master where district_status='A' "
	+ " and district_id in( select district_id from igrs_office_master where office_status='A' "
	+ "	and office_id in(?)) ORDER BY DISTRICT_NAME ASC ";


public static final String GET_DIST_NAME_DIG = "Select DISTRICT_ID,district_name,h_district_name from igrs_district_master where district_status='A' "+
" and district_id in( select district_id from igrs_office_master where office_status='A' "+
"and parent_id in(?)) ORDER BY DISTRICT_NAME ASC ";
public static final String GET_DIST_NAME_DR = "Select DISTRICT_ID,district_name,h_district_name from igrs_district_master where district_status='A' "
	+ " and district_id in( select district_id from igrs_office_master where office_status='A' "
	+ "and office_id in(?)) ORDER BY DISTRICT_NAME ASC ";

public static final String GET_DIST_NAME = "SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = ?";
public static final String  GET_DIST_NAME_HI="SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = ?";
public static final String OFFICE_TYPE = "select office_type_id from igrs_office_master where office_id =? ";
public static final String TEHSIL_LIST = "select tehsil_id,tehsil_name from igrs_tehsil_master where district_id=? ";
public static final String DIST_ID = "select district_id from igrs_office_master where OFFICE_ID=? ";
public static final String OFFICER = "select office_type_name from IGRS_OFFICE_TYPE_MASTER where office_type_id =? ";
public static final String INSP_GET_ZONE_DIG = "SELECT D.ZONE_ID FROM IGRS_DISTRICT_MASTER D,igrs_office_master O WHERE o.district_id=d.district_id AND  o.office_id=? ";

public static final String INSP_GET_DISTRICT_ZONE_LIST = "Select district_name,district_id from igrs_district_master where zone_id=?";

public static final String INSP_GET_DISTRICT_ZONE_LIST_HINDI = "Select h_district_name,district_id from igrs_district_master where zone_id=?";

public static final String INSP_GET_DISTRICT_ZONES = "SELECT D.District_id,D.DISTRICT_NAME,z.zone_id FROM igrs_district_master d, "
	+ " igrs_zone_master z,igrs_office_master o WHERE o.office_id= ? AND d.district_id=o.district_id AND d.zone_id    =z.zone_id ";

public static final String getCaseTxnForReport1 ="SELECT a.CASE_TXN_ID " +
" FROM IGRS_PENDING_CASES_TXN_DETLS a, " +
"  IGRS_OFFICE_MASTER b, " +
"  IGRS_DISTRICT_MASTER c " +
" WHERE c.DISTRICT_ID         =? " +
" AND REVENUE_HEAD            =? " +
" AND SECTION                 =? " +
" AND TRUNC(a.date_of_lodging) between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')  " +
" AND a.OFFICE_ID             =b.OFFICE_ID " +
" AND b.DISTRICT_ID           =c.district_id " +
" AND a.CASE_TYPE           =?" +
" AND a.OFFICE_ID =? ";

public static final String getCaseTxnForReport2 = "SELECT a.CASE_TXN_ID " +
" FROM IGRS_PENDING_CASES_TXN_DETLS a, " +
"  IGRS_OFFICE_MASTER b, " +
"  IGRS_DISTRICT_MASTER c " +
" WHERE c.DISTRICT_ID         =? " +
" AND REVENUE_HEAD            =? " +
" AND SECTION                 =? " +
" AND TRUNC(a.date_of_lodging) between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')  " +
" AND a.OFFICE_ID             =b.OFFICE_ID " +
" AND b.DISTRICT_ID           =c.district_id" +
" AND a.status                =?" +
" AND a.CASE_TYPE           =? " +
" AND a.OFFICE_ID =? ";

public static final String getCaseTxnForReport3 = "SELECT a.CASE_TXN_ID " +
" FROM IGRS_PENDING_CASES_TXN_DETLS a, " +
"  IGRS_OFFICE_MASTER b, " +
"  IGRS_DISTRICT_MASTER c " +
" WHERE c.DISTRICT_ID         =? " +
" AND REVENUE_HEAD            =? " +
" AND SECTION                 =? " +
" AND TRUNC(a.date_of_lodging) between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')  " +
" AND a.OFFICE_ID             =b.OFFICE_ID " +
" AND b.DISTRICT_ID           =c.district_id" +
" AND a.PAYMENT_TYPE                =?" +
" AND a.CASE_TYPE           =? " +
" AND a.OFFICE_ID =? ";

public static final String getCaseTxnForReport4 = "SELECT a.CASE_TXN_ID " +
" FROM IGRS_PENDING_CASES_TXN_DETLS a, " +
"  IGRS_OFFICE_MASTER b, " +
"  IGRS_DISTRICT_MASTER c " +
" WHERE c.DISTRICT_ID         =? " +
" AND REVENUE_HEAD            =? " +
" AND SECTION                 =? " +
" AND TRUNC(a.date_of_lodging) between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')  " +
" AND a.OFFICE_ID             =b.OFFICE_ID " +
" AND b.DISTRICT_ID           =c.district_id" +
" AND a.status                =?" +
" AND a.PAYMENT_TYPE        =?" +
" AND a.CASE_TYPE           =? " +
" AND a.OFFICE_ID =? ";


public static final String getDRofficeName = "SELECT OM.OFFICE_ID,OM.OFFICE_NAME,OM.H_OFFICE_NAME FROM IGRS_OFFICE_MASTER OM,IGRS_OFFICE_TYPE_MASTER OT WHERE OM.OFFICE_TYPE_ID = OT.OFFICE_TYPE_ID AND OT.OFFICE_TYPE_ID=? AND OM.OFFICE_STATUS=? AND OM.DISTRICT_ID=?";
}

