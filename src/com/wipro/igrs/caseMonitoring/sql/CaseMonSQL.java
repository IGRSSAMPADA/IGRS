/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/
package com.wipro.igrs.caseMonitoring.sql;

public class CaseMonSQL {

	/***************************************************************************
	 * Getting CASETXNID and Created Date and Status of Case from
	 * IGRS_CASE_TXN_DETAILS
	 **************************************************************************/
	public static final String IGRS_CASE_TXN_IDS = " select ICTD.case_txn_id, ICTD.created_date "
			+ " from igrs_case_txn_details ICTD where "
			+ " ICTD.case_status like ? and  trunc(ICTD.created_date) "
			+ " BETWEEN  to_date(? ,'DD/MM/YY')  and  to_date(? ,'DD/MM/YY')"
			+ " and ICTD.ESTAMP_CODE IS NOT NULL and case_type_id  like ? and case_action_id = ? ";

	public static final String IGRS_CASE_VIEW_LIST = " select ICTD.case_txn_id, ICTD.created_date, "
			+ " ICTD.case_status from igrs_case_txn_details ICTD where "
			+ " trunc(ICTD.created_date) "
			+ " BETWEEN  to_date(? ,'DD/MM/YY')  and  to_date(? ,'DD/MM/YY')"
			+ " and ICTD.ESTAMP_CODE IS NOT NULL and case_type_id  like ? ";

	public static final String IGRS_CASE_TXN_DETAILSFOR_VIEW = "SELECT CASE_ACTION_ID,(SELECT CASE_HEAD_NAME FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_ID=TXN.CASE_HEAD_ID),CASE_TYPE_ID , (SELECT REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER  WHERE REVENUE_HEAD_ID=TXN.REVENUE_HEAD_ID),(SELECT SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER  WHERE SECTION_HEAD_ID=TXN.SECTION_HEAD_ID), CASE_STATUS,CASE_FINAL_COMMENTS,TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),(SELECT CASE_TYPE_NAME FROM IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID=TXN.CASE_TYPE_ID) FROM IGRS_CASE_TXN_DETAILS TXN WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_CASE_TXN_DETAILS_REFUND_VIEW = "SELECT CASE_ACTION_ID,(SELECT CASE_HEAD_NAME FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_ID=TXN.CASE_HEAD_ID),CASE_TYPE_ID , (SELECT REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER  WHERE REVENUE_HEAD_ID=TXN.REVENUE_HEAD_ID),(SELECT SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER  WHERE SECTION_HEAD_ID=TXN.SECTION_HEAD_ID), CASE_STATUS,CASE_FINAL_COMMENTS,TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),(SELECT CASE_TYPE_NAME FROM IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID=TXN.CASE_TYPE_ID) FROM IGRS_CASE_REFUND_TXN_DTLS TXN WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_CASE_AXNID_RETRIEVEFOR_VIEW = "SELECT CASE_ACTION_ID, CASE_ACTION_NAME FROM IGRS_CASE_ACTION_MASTER WHERE CASE_ACTION_ID IN (SELECT CASE_ACTION_ID FROM IGRS_CASE_TYPE_ACTION_MAP MP WHERE CASE_TYPE_ID=? AND MP.CASE_ACTION_ORDER <= (SELECT CASE_ACTION_ORDER FROM IGRS_CASE_TYPE_ACTION_MAP WHERE CASE_TYPE_ID=? AND CASE_ACTION_ID=(SELECT CASE_ACTION_ID FROM IGRS_CASE_TXN_DETAILS WHERE CASE_TXN_ID=?)))";

	public static final String IGRS_CASE_AXN_REMARKSFOR_VIEW = "SELECT CASE_ACTION_TXN_ID,ACTION_DESC,TO_CHAR(ACTION_DATE,'DD/MM/YYYY'),TO_CHAR(ACTION_RECEIVING_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_COMMENTS WHERE CASE_ACTION_ID=? and CASE_TXN_ID=?";

	public static final String IGRS_CASE_TXN_COMMENTSFOR_VIEW = "SELECT CASE_ACTION_TXN_ID,USER_COMMENTS, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_COMMENTS WHERE CASE_ACTION_TXN_ID=?";
	
	public static final String IGRS_CASE_UPDATE_COMPLIANCE = "UPDATE IGRS_CASE_TXN_DETAILS SET COMPLIANCE_DETAILS=?,COMPLIANCE_BY=?, COMPLIANCE_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_SEARCH_LIST_FOR_ADDCOMP = "SELECT CASE_TXN_ID, SP_LICENSE_NO, TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),CASE_STATUS, (SELECT CASE_ACTION_NAME FROM IGRS_CASE_ACTION_MASTER WHERE CASE_ACTION_ID=IGRS_CASE_TXN_DETAILS.CASE_ACTION_ID) FROM IGRS_CASE_TXN_DETAILS WHERE CASE_HEAD_ID=? AND CASE_TYPE_ID=? AND CASE_STATUS LIKE '%OPEN%'  AND ((CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')) OR CASE_TXN_ID=?)";

	public static final String IGRS_CASE_TXN_LIST = " select ICTD.case_txn_id,ICTD.created_date,ICTD.ESTAMP_CODE,ICTD.PENALTY_AMOUNT,"
			+ " ICTC.user_comments from "
			+ " igrs_case_txn_details ICTD,igrs_case_txn_comments ICTC "
			+ " where ICTD.case_txn_id = ICTC.case_txn_id AND ICTD.case_txn_id =  ";
	
	public static final String IGRS_CASE_TXN_LIST1 = "select ICTD.case_txn_id,ICTD.created_date,'',ICTD.PENALTY_AMOUNT,"
	+" ICTC.user_comments from IGRS_CASE_OTHERS_TXN_DTLS ICTD,igrs_case_txn_comments ICTC" 
	 +" where ICTD.case_txn_id = ICTC.case_txn_id AND ICTD.case_txn_id =";
	
	public static final String IGRS_CASE_NOTICE_DETAILS = " select ICTD.case_txn_id,ICTD.created_status,ICTD.ESTAMP_CODE,ICTD.PENALTY_AMOUNT,"
			+ " ICTC.user_comments ,ICAM.case_action_remarks,ICAM.case_action_date from"
			+ " igrs_case_txn_details ICTD,igrs_case_txn_comments ICTC,igrs_case_action_map ICAM "
			+ " where ICTD.case_txn_id = ICTC.case_txn_id AND ICTD.case_txn_id = ICAM.case_txn_id AND ICTD.case_txn_id =  ";

	public static final String IGRS_CASE_ID_ACTION = " select ICAM.case_action_id "
			+ " from igrs_case_action_master ICAM where"
			+ "  ICAM.case_action_name =  ";

	public static final String IGRS_CASE_ACTION_MASTER = "SELECT MA.CASE_ACTION_ID, CASE_ACTION_NAME FROM IGRS_CASE_ACTION_MASTER MA,"
			+ " IGRS_CASE_TYPE_ACTION_MAP CT WHERE MA.CASE_ACTION_ID=CT.CASE_ACTION_ID AND CASE_TYPE_ID=? "
			+ " ORDER BY CASE_ACTION_ORDER";
	
	public static final String IGRS_CASE_LAST_ACTION = "select DISTINCT IGRS_CASE_ACTION_MASTER.CASE_ACTION_ID,initcap(IGRS_CASE_ACTION_MASTER.CASE_ACTION_NAME) from"
			+ " IGRS_CASE_ACTION_MASTER,IGRS_CASE_ACTION_MAP where  IGRS_CASE_ACTION_MASTER.CASE_ACTION_ID="
			+ "(select CASE_ACTION_ID from igrs_case_action_map where CASE_TXN_ID=?"
			+ " and created_date=(select max(created_date) from igrs_case_action_map where CASE_TXN_ID=?))";

	public static final String IGRS_CASE_LIST_INSERT = "INSERT INTO IGRS_CASE_TXN_DETAILS (CASE_TXN_ID,CASE_HEAD_ID,CASE_TYPE_ID,"
			+ " CASE_STATUS, CREATED_BY,CREATED_DATE,ESTAMP_CODE,PENALTY_AMOUNT,CASE_ACTION_ID)VALUES(?,?,?,?,?,SYSDATE,?,?,?)";

	public static final String IGRS_CASE_UPDATE = " update igrs_case_txn_details set PENALTY_AMOUNT = ? ,CASE_ACTION_ID = ? ,"
			+ " UPDATE_BY = 'IGRS', UPDATE_DATE = SYSDATE "
			+ " where CASE_TXN_ID = ? ";

	public static final String IGRS_CASE_NOTICE = " update igrs_case_txn_details set PENALTY_AMOUNT = ? ,CASE_ACTION_ID = ? ,"
			+ " NEXT_HEARING_DATE = tO_DATE(?,'DD/MM/YYYY'),UPDATE_BY = 'IGRS', UPDATE_DATE = TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') "
			+ " where CASE_TXN_ID = ? ";

	public static final String IGRS_CASE_CLOSE = " update igrs_case_txn_details set PENALTY_AMOUNT = ? ,CASE_ACTION_ID = ? ,"
			+ " case_final_comments = ?, UPDATE_BY = 'IGRS', UPDATE_DATE = SYSDATE, CASE_STATUS='CLOSE'  "
			+ " where CASE_TXN_ID = ? ";

	public static final String IGRS_CASE_LIST_COMMENTS = " insert into igrs_case_txn_comments (case_txn_id,user_comments,user_comments_date,case_action_txn_id,user_id)"
			+ " values(?,?,SYSDATE,?,'igrs')";
	
	public static final String IGRS_CASE_ACTION_MAP = " insert into igrs_case_action_map (case_txn_id,case_action_id,case_action_by,"
			+ " case_action_date,case_action_receive_date,case_action_txn_id,case_action_remarks) "
			+ " values(?,?,'IGRS',SYSDATE,SYSDATE,?,?) ";

	public static final String IGRS_CASE_ACTION_ID_GET = " select case_action_id from igrs_case_action_master"
			+ " where case_action_name like  ";

	public static final String IGRS_CASE_ID_GET = "SELECT case_txn_id FROM igrs_case_txn_details WHERE created_date IN"
			+ " (SELECT MAX(created_date) FROM igrs_case_txn_details )";

	public static final String IGRS_CASE_HEAD_MASTER = ""
			+ " SELECT CASE_HEAD_ID, CASE_HEAD_NAME "
			+ " FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_STATUS='A'";
	
	public static final String IGRS_CASE_HEAD_MASTER_IG = ""
		+ " SELECT CASE_HEAD_ID, CASE_HEAD_NAME "
		+ " FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_STATUS='A'";// case head I is maintained for IG
	
	//added by shruti
	public static final String IGRS_CASE_HEAD_MASTER_H = ""
		+ " SELECT CASE_HEAD_ID, H_CASE_HEAD_NAME "
		+ " FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_STATUS='A'";
	//end
	
	public static final String IGRS_CASE_HEAD_MASTER_H_IG = ""
		+ " SELECT CASE_HEAD_ID, H_CASE_HEAD_NAME "
		+ " FROM IGRS_CASE_HEAD_MASTER WHERE CASE_HEAD_STATUS='A'";
	

	public static final String IGRS_CASE_STAMP_AMT = " select IETD.estamp_total from igrs_estamp_txn_details IETD "
			+ " where estamp_code like ";

	public static final String IGRS_CASE_TYPE_MASTER = " SELECT CASE_TYPE_ID, CASE_TYPE_NAME FROM "
			+ " IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID "
			+ " IN (SELECT CASE_TYPE_ID FROM IGRS_CASE_HEAD_TYPE_MAPPING"
			+ " WHERE CASE_HEAD_ID= ?) and case_type_status='A'";
	
	public static final String IGRS_CASE_TYPE_MASTER_IG = " SELECT CASE_TYPE_ID, CASE_TYPE_NAME FROM "
		+ " IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID "
		+ " IN (SELECT CASE_TYPE_ID FROM IGRS_CASE_HEAD_TYPE_MAPPING"
		+ " WHERE CASE_HEAD_ID= ?) and case_type_status='I'";
	
	
	public static final String IGRS_CASE_TYPE_MASTER_H = " SELECT CASE_TYPE_ID, H_CASE_TYPE_NAME FROM "
		+ " IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID "
		+ " IN (SELECT CASE_TYPE_ID FROM IGRS_CASE_HEAD_TYPE_MAPPING"
		+ " WHERE CASE_HEAD_ID= ?) and case_type_status='A'";
	
	public static final String IGRS_CASE_TYPE_MASTER_H_IG = " SELECT CASE_TYPE_ID, H_CASE_TYPE_NAME FROM "
		+ " IGRS_CASE_TYPE_MASTER WHERE CASE_TYPE_ID "
		+ " IN (SELECT CASE_TYPE_ID FROM IGRS_CASE_HEAD_TYPE_MAPPING"
		+ " WHERE CASE_HEAD_ID= ?) and case_type_status='I'";
	

	public static final String IGRS_CASE_TYPE_ID = "select case_type_id from igrs_case_type_master where "
			+ " case_type_name like ";

	public static final String IGRS_COUNTRY_MASTER = " SELECT COUNTRY_ID,COUNTRY_NAME FROM IGRS_COUNTRY_MASTER "
			+ " WHERE COUNTRY_STATUS='A'";

	public static final String IGRS_STATE_MASTER = " SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER WHERE"
			+ " COUNTRY_ID=? AND STATE_STATUS='A'";

	public static final String IGRS_DISTRICT_MASTER = " SELECT DISTRICT_ID, DISTRICT_NAME FROM "
			+ " IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A'";

	public static final String IGRS_PHOTOID_PROOF_MASTER = " SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME "
			+ " FROM IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE PHOTO_PROOF_TYPE_STATUS='A'"
			+ " ORDER BY PHOTO_PROOF_TYPE_NAME ";

	public static final String IGRS_CASTE_MASTER = " SELECT CASTE_ID, CASTE_NAME FROM IGRS_CASTE_MASTER WHERE"
			+ " CASTE_STATUS='A' ORDER BY CASTE_NAME";
	
	public static final String IGRS_RELIGION_MASTER = " SELECT RELIGION_ID, RELIGION_NAME FROM IGRS_RELIGION_MASTER "
			+ " WHERE RELIGION_STATUS='A' ORDER BY RELIGION_NAME";

	public static final String IGRS_CASE_REVENUE_HEAD_MASTER = "SELECT REVENUE_HEAD_ID, REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER WHERE REVENUE_HEAD_STATUS='A'";
	
	public static final String IGRS_CASE_SECTION_HEAD_MASTER = "SELECT SECTION_HEAD_ID, SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER WHERE SECTION_HEAD_STATUS='A'";

	public static final String IGRS_CASE_LICENSEID_SEARCH = "SELECT LICENSE_NO, FIRST_NAME||' '||MIDDLE_NAME||' '||LAST_NAME, SP_OFFICE_ADDR, TO_CHAR(VALID_TO,'DD/MM/YYYY'),SP_USER_ID FROM IGRS_SP_USER_LICENSE_DETAILS,IGRS_USER_REG_DETAILS  WHERE LICENSE_NO=? and USER_ID=SP_USER_ID";
	
	public static final String IGRS_CASE_LICENSE_INSERT = "INSERT INTO IGRS_CASE_TXN_DETAILS (SR_NO,CASE_TXN_ID,CASE_HEAD_ID,"
			+ "CASE_TYPE_ID,REVENUE_HEAD_ID,SECTION_HEAD_ID,CASE_STATUS,CREATED_BY,"
			+ "CREATED_DATE) VALUES(?,?,?,?,?,?,?,SYSDATE))";

	public static final String IGRS_CASE_LICENSE_DETAILS = "SELECT CASE_STATUS, (SELECT REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER  WHERE REVENUE_HEAD_ID=TXN.REVENUE_HEAD_ID),(SELECT SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER  WHERE SECTION_HEAD_ID=TXN.SECTION_HEAD_ID),TO_CHAR(CREATED_DATE,'DD/MM/YYYY'), TO_CHAR(NEXT_HEARING_DATE,'DD/MM/YYYY'),TO_CHAR(DUE_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_DETAILS TXN WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_RECOVERABLE_AMT_CASE_TXN_DETAILS = "SELECT nvl(PROPOSED_STAMP_DUTY,0)+nvl(PROPOSED_REG_FEE,0) from IGRS_CASE_TXN_DETAILS"
			+" where CASE_TXN_ID=?";
	
	public static final String IGRS_RECOVERABLE_AMT_CASE_TXN_COMMENTS = 
		"select (PROPOSED_RECVRBLE_STAMP_DUTY+PROPOSED_RECVRBLE_REG_FEE) from IGRS_CASE_TXN_COMMENTS"
		+" where CASE_TXN_ID=? and created_date"
		+" =(select max(created_date) from IGRS_CASE_TXN_COMMENTS where CASE_TXN_ID=?" 
		+" and case_action_id in('CASE_015','CASE_016','CASE_017','CASE_018'))";
	
	public static final String RRC_NO_CHK ="select RRC_CASE FROM IGRS_CASE_TXN_DETAILS WHERE CASE_TXN_ID=?" ;
	
	public static final String ADESHIKA_AMT_CHK="SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_NAME='ADESHIKA SHULK'";
	
	public static final String IGRS_RECOVERABLE_AMT_CASE_OTHERS_TXN = "select PENALTY_AMOUNT from IGRS_CASE_OTHERS_TXN_DTLS"
		+" where CASE_TXN_ID=?";

	public static final String IGRS_CASE_LICENSE_DETAILS1 = "SELECT CASE_STATUS, (SELECT REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER  WHERE REVENUE_HEAD_ID=TXN.REVENUE_HEAD_ID),(SELECT SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER  WHERE SECTION_HEAD_ID=TXN.SECTION_HEAD_ID),TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),RECOVERABLE_AMT,'','' FROM IGRS_CASE_REFUND_TXN_DTLS TXN WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_CASE_PENALTY45_DETAILS = "SELECT CASE_STATUS,REVISION_NUMBER,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_REV_PNLTY_TXN_DTLS WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_CASE_PENALTY70_DETAILS = "SELECT CASE_STATUS,REVISION_NUMBER,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_PNLTY70_TXN_DTLS WHERE CASE_TXN_ID =?";
	
	public static final String IGRS_CASE_LICENSE_DETAILS2 = "SELECT CASE_STATUS, (SELECT REVENUE_HEAD_NAME FROM IGRS_CASE_REVENUE_HEAD_MASTER  WHERE REVENUE_HEAD_ID=TXN.REVENUE_HEAD_ID),(SELECT SECTION_HEAD_NAME FROM IGRS_CASE_SECTION_HEAD_MASTER  WHERE SECTION_HEAD_ID=TXN.SECTION_HEAD_ID),TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),PENALTY_AMOUNT FROM IGRS_CASE_OTHERS_TXN_DTLS TXN WHERE CASE_TXN_ID =?";

	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE = "SELECT CASE_TXN_ID, TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_DETAILS WHERE CASE_ACTION_ID=? AND CASE_HEAD_ID=? AND CASE_TYPE_ID=? AND UPPER(CASE_STATUS) LIKE '%OPEN%' AND ((CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY'))";
	
	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE1 = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
			+ " initcap(icam.CASE_ACTION_NAME),nvl(RRC_CASE,'-') FROM IGRS_CASE_TXN_DETAILS ictd,IGRS_CASE_ACTION_MASTER icam"
			+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
			+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND ((ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY'))";
	
	public static final String IGRS_CASE_SEARCH_LIST_UPDATE_REVENUEID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),nvl(RRC_CASE,'-'),H_CASE_ACTION_NAME FROM IGRS_CASE_TXN_DETAILS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";
	
	public static final String IGRS_CASE_SEARCH_LIST_UPDATE_REVENUERRCID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),nvl(RRC_CASE,'-'),H_CASE_ACTION_NAME FROM IGRS_CASE_TXN_DETAILS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(RRC_CASE) like UPPER(?)";

	public static final String IGRS_CASE_SEARCH_LIST_UPDATE_REVENUEDUR ="SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
	+" initcap(icam.CASE_ACTION_NAME),nvl(RRC_CASE,'-'),H_CASE_ACTION_NAME FROM IGRS_CASE_TXN_DETAILS ictd,IGRS_CASE_ACTION_MASTER icam"
	+" WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID and ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
	+" AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN" 
	+" TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1) order by ictd.CREATED_DATE DESC";

	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE2 = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
			+ " initcap(icam.CASE_ACTION_NAME) FROM IGRS_CASE_REFUND_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
			+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
			+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND ((ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY'))";
	
	public static final String IGRS_SEARCH_LIST_FOR_ESTAMPID_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
			+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REFUND_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
			+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
			+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";
	
	public static final String IGRS_SEARCH_LIST_FOR_REGFEEID_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REFUND_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";
	
	public static final String IGRS_SEARCH_LIST_FOR_ESTAMPDUR_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REFUND_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') " +
				"AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_SEARCH_LIST_FOR_REGFEEDUR_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REFUND_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') " +
				"AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_SEARCH_LIST_TIMEBARREDID_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_OTHERS_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";

	public static final String IGRS_CASE_SEARCH_LIST_TIMEBARREDDUR_UPDATE = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_OTHERS_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE3 = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME) FROM IGRS_CASE_OTHERS_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE icam.CASE_ACTION_ID=? AND ictd.CASE_HEAD_ID=? AND ictd.CASE_TYPE_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND ((ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE4 = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME) FROM IGRS_CASE_REV_PNLTY_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID and icam.CASE_ACTION_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND ((ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";

	public static final String IGRS_CASE_SEARCH_LIST_PENALTY45ID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REV_PNLTY_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";
	
	public static final String IGRS_CASE_SEARCH_LIST_PENALTY45REVISIONID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REV_PNLTY_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(REVISION_NUMBER) like UPPER(?)";

	public static final String IGRS_CASE_SEARCH_LIST_PENALTY45DUR = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_REV_PNLTY_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_SEARCH_LIST_PENALTY70ID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_PNLTY70_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(CASE_TXN_ID) like UPPER(?)";
	
	public static final String IGRS_CASE_SEARCH_LIST_PENALTY70REVISIONID = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_PNLTY70_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND UPPER(REVISION_NUMBER) like UPPER(?)";

	public static final String IGRS_CASE_SEARCH_LIST_PENALTY70DUR = "SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME),H_CASE_ACTION_NAME FROM IGRS_CASE_PNLTY70_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND (ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_SEARCH_LIST_FOR_UPDATE5 ="SELECT ictd.CASE_TXN_ID, TO_CHAR(ictd.CREATED_DATE,'DD/MM/YYYY'),"
		+ " initcap(icam.CASE_ACTION_NAME) FROM IGRS_CASE_PNLTY70_TXN_DTLS ictd,IGRS_CASE_ACTION_MASTER icam"
		+ " WHERE ictd.CASE_ACTION_ID=icam.CASE_ACTION_ID and icam.CASE_ACTION_ID=?"
		+ " AND UPPER(ictd.CASE_STATUS) LIKE '%OPEN%' AND ((ictd.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	
	public static final String IGRS_CASE_AXN_DETAILS_INSERT = "INSERT INTO IGRS_CASE_ACTION_MAP (SR_NO,CASE_ACTION_TXN_ID, CASE_TXN_ID, CASE_ACTION_ID,CASE_ACTION_REMARKS,PARTY_RESPONSE_FLAG,CREATED_BY,CREATED_DATE,PARTY_PAY_DEC_FLAG,AUTH_DEC_FLAG,RRC_CASE)VALUES(?,?,?,?,?,?,?,SYSDATE,?,?,?)";

	public static final String IGRS_CASE_AXNID_CATCH = "SELECT CASE_ACTION_ID FROM IGRS_CASE_ACTION_MASTER WHERE CASE_ACTION_NAME LIKE '%INITIATE%'";

	public static final String IGRS_CASE_COMMENTS_DETAILS_INSERT = "INSERT INTO IGRS_CASE_TXN_COMMENTS (SR_NO,CASE_TXN_ID, CASE_ACTION_TXN_ID, USER_COMMENTS,CREATED_BY,CREATED_DATE,ACTION_DATE,ACTION_DESC,ACTION_RECEIVING_DATE,CASE_ACTION_ID,PROPOSED_RECOVERABLE_AMT,PROPOSED_RECVRBLE_STAMP_DUTY,PROPOSED_RECVRBLE_REG_FEE) VALUES(?,?,?,?,?,sysdate,to_date(?,'dd/mm/yyyy'),?,to_date(?,'dd/mm/yyyy'),?,?,?,?)";

	public static final String IGRS_CASE_COMMENTS_RETRIEVE = "SELECT USER_COMMENTS, TO_CHAR(CO.CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_COMMENTS CO, IGRS_CASE_ACTION_MAP MA WHERE CO.CASE_ACTION_TXN_ID=MA.CASE_ACTION_TXN_ID AND MA.CASE_TXN_ID=? AND MA.CASE_ACTION_ID=?";
	
	public static final String IGRS_CASE_ATTACHMENTS_RETRIEVE = "SELECT DOC_FILE_LOC, TO_CHAR(CO.CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_TXN_DOC_DETAILS CO, IGRS_CASE_ACTION_MAP MA WHERE CO.CASE_ACTION_TXN_ID=MA.CASE_ACTION_TXN_ID AND MA.CASE_TXN_ID=? ";

	public static final String IGRS_CASE_AXN_DESCRIPTION_INSERT = "INSERT INTO IGRS_CASE_ACTION_MAP (SR_NO,CASE_ACTION_TXN_ID, CASE_TXN_ID, CASE_ACTION_ID,CASE_ACTION_REMARKS,PARTY_RESPONSE_FLAG,CREATED_DATE,CREATED_BY,PARTY_PAY_DEC_FLAG,AUTH_DEC_FLAG,RRC_CASE)VALUES(?,?,?,?,?,?,SYSDATE,?,?,?,?)";
	
	public static final String IGRS_CASE_ACTIONID_UPDATE = "UPDATE IGRS_CASE_TXN_DETAILS SET CASE_ACTION_ID=?,NEXT_HEARING_DATE=? WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_FINALAMOUNT_UPDATE = "UPDATE IGRS_CASE_TXN_DETAILS SET FINAL_RECOVERABLE_AMT=? WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_ACTIONID_UPDATE1 = "UPDATE IGRS_CASE_REFUND_TXN_DTLS SET CASE_ACTION_ID=? WHERE CASE_TXN_ID=?";
	
	//modified by shruti---29 oct 2014
	
	public static final String IGRS_CASE_ACTIONID_UPDATE_PENALTY45 = "UPDATE IGRS_CASE_REV_PNLTY_TXN_DTLS SET CASE_ACTION_ID=? WHERE REVISION_NUMBER=?";

	//END
	
	//public static final String IGRS_CASE_ACTIONID_UPDATE_PENALTY70 = "UPDATE IGRS_CASE_PNLTY70_TXN_DTLS SET CASE_ACTION_ID=? WHERE CASE_TXN_ID=?";
	public static final String IGRS_CASE_ACTIONID_UPDATE_PENALTY70 = "UPDATE IGRS_CASE_PNLTY70_TXN_DTLS SET CASE_ACTION_ID=? WHERE REVISION_NUMBER=?";
	
	public static final String IGRS_CASE_CLOSE_UPDATE = "UPDATE IGRS_CASE_TXN_DETAILS SET CASE_FINAL_COMMENTS=?,CASE_STATUS='CLOSE', CASE_ACTION_ID=?, UPDATE_DATE=TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY')  WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_CLOSE_UPDATE1 = "UPDATE IGRS_CASE_REFUND_TXN_DTLS SET CASE_FINAL_COMMENTS=?,CASE_STATUS='CLOSE', CASE_ACTION_ID=?, UPDATE_DATE=sysdate,REFUND_AMOUNT=?,REFUND_BILL_NO=?,REFUND_BILL_DATE=to_date(?,'DD/MM/YYYY') WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_CLOSE_UPDATE_PENALTY45 = "UPDATE IGRS_CASE_REV_PNLTY_TXN_DTLS SET CASE_FINAL_COMMENTS=?,CASE_STATUS='CLOSE', CASE_ACTION_ID=?, UPDATE_DATE=sysdate,REFUND_AMOUNT=?,REFUND_BILL_NO=?,REFUND_BILL_DATE=to_date(?,'DD/MM/YYYY') WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_CLOSE_UPDATE_PENALTY70 = "UPDATE IGRS_CASE_PNLTY70_TXN_DTLS SET CASE_FINAL_COMMENTS=?,CASE_STATUS='CLOSE', CASE_ACTION_ID=?, UPDATE_DATE=sysdate,REFUND_AMOUNT=?,REFUND_BILL_NO=?,REFUND_BILL_DATE=to_date(?,'DD/MM/YYYY') WHERE CASE_TXN_ID=?";
	
	public static final String IGRS_CASE_CLOSE_UPDATE2 = "UPDATE IGRS_CASE_OTHERS_TXN_DTLS SET CASE_FINAL_COMMENTS=?,CASE_STATUS='CLOSE', CASE_ACTION_ID=?, UPDATE_DATE=sysdate WHERE CASE_TXN_ID=?";

	public static final String IGRS_CASE_NEXT_PROCESS_ACTION = "SELECT (SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) caid,"
			+ " (SELECT initcap(case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )case_action_name,"
			+ " (SELECT initcap(h_case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )h_case_action_name"
			+ " FROM igrs_case_type_action_map ictam WHERE ictam.response_flag='Y' and"
			+ " ictam.case_type_id = ? AND ictam.case_action_id =?";

	public static final String IGRS_CASE_NEXT_PROCESS_ACTION1 = "SELECT (SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) caid,"
			+ " (SELECT initcap(case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )case_action_name,"
			+ " (SELECT initcap(h_case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )h_case_action_name"
			+ " FROM igrs_case_type_action_map ictam WHERE ictam.response_flag='N' and"
			+ " ictam.case_type_id = ? AND ictam.case_action_id =?";

	public static final String IGRS_CASE_NEXT_PROCESS_ACTION2 = "SELECT (SELECT case_action_id FROM igrs_case_type_action_map1"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) caid,"
			+ " (SELECT initcap(case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map1"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )case_action_name,"
			+ " (SELECT initcap(h_case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='N' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )h_case_action_name"
			+ " FROM igrs_case_type_action_map1 ictam WHERE ictam.response_flag='N' and"
			+ " ictam.case_type_id = ? AND ictam.case_action_id =?";

	public static final String IGRS_CASE_NEXT_PROCESS_ACTION3 = "SELECT (SELECT case_action_id FROM igrs_case_type_action_map2"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) caid,"
			+ " (SELECT initcap(case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map2"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )case_action_name,"
			+ " (SELECT initcap(h_case_action_name) FROM igrs_case_action_master WHERE"
			+ " case_action_id =(SELECT case_action_id FROM igrs_case_type_action_map"
			+ " WHERE response_flag='Y' and (case_type_id = ictam.case_type_id AND"
			+ " case_action_order = ictam.case_action_order + 1)) )h_case_action_name"
			+ " FROM igrs_case_type_action_map2 ictam WHERE ictam.response_flag='Y' and"
			+ " ictam.case_type_id = ? AND ictam.case_action_id =?";

	public static final String IGRS_CASE_SEARCH_LIST_FOR_VIEW = "SELECT CASE_TXN_ID, TO_CHAR(CREATED_DATE,'DD/MM/YYYY'),CASE_STATUS FROM IGRS_CASE_TXN_DETAILS WHERE CASE_HEAD_ID=? AND CASE_TYPE_ID=?  AND ((CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')) OR CASE_TXN_ID=?)";
	
	public static final String INSERT_ACTION_MAP_UPDATE_RECORD = "INSERT INTO IGRS_CASE_ACTION_MAP(CASE_TXN_ID,CASE_ACTION_ID,CASE_ACTION_BY,CASE_ACTION_DATE,CASE_ACTION_REMARKS,CASE_ACTION_RECEIVE_DATE,CASE_ACTION_TXN_ID,CREATED_BY,CREATED_DATE,PARTY_RESPONSE_FLAG) VALUES (?,?,?,SYSDATE,?,SYSDATE,?,?,SYSDATE,?)";

	public static final String GETNEXTACTNDTLS = "SELECT distinct icam.CASE_ACTION_ID,icam.PARTY_RESPONSE_FLAG,icam.AUTH_DEC_FLAG FROM"
			+ " IGRS_CASE_TYPE_ACTION_MAP ictam,IGRS_CASE_ACTION_MAP icam WHERE icam.created_date=(select max(created_date)"
			+ " from igrs_case_action_map where case_txn_id=?) and"
			+ " ictam.CASE_ACTION_ID=icam.CASE_ACTION_ID AND icam.CASE_TXN_ID=?";

	public static final String GETACTIONMAPSRNO = "SELECT IGRS_ACTION_MAP_SRNO.NEXTVAL FROM DUAL";
	
	public static final String GETCASETXNSRNO = "SELECT IGRS_CASE_TXN_SRNO.NEXTVAL FROM DUAL";
	
	public static final String GETCASECMNTSSRNO = "SELECT IGRS_CASE_CMNTS_SRNO.NEXTVAL FROM DUAL";

	public static final String GETCASEPENALTYSRNO = "SELECT IGRS_CASE_PENALTY_SRNO.NEXTVAL FROM DUAL";
	
	public static final String GETCASEPENALTYSEC70SRNO = "SELECT IGRS_CASE_PENALTY70_SRNO.NEXTVAL FROM DUAL";
	
	public static final String GETCASEPYMTTXNID = "SELECT IGRS_CASE_PYMT_SRNO_SEQ.NEXTVAL FROM DUAL";

	public static final String INSERTCASEPYMTDTLS = "INSERT INTO IGRS_CASE_PYMT_TXN_DTLS (SR_NO, "
			+ "CASE_TXN_ID, CASE_ACTION_ID, TRANSACTION_DATE, PAYMENT_FLAG, CREATED_BY, CREATED_DATE)"
			+ " VALUES (?,?,?,sysdate,?,?,sysdate )";

	public static final String GETPAIDAMOUNT = "SELECT PAID_AMOUNT FROM IGRS_CASE_PYMT_TXN_DTLS WHERE CASE_TXN_ID=? AND SR_NO=?";

	public static final String GETCASEPYMTDTLS = "SELECT sum(PAID_AMOUNT) FROM IGRS_CASE_PYMT_TXN_DTLS WHERE PAYMENT_FLAG='P' AND CASE_TXN_ID=?";

	//added by shruti---21 oct 2014
	public static final String GETRECAMTESTAMP="SELECT RECOVERABLE_AMT FROM IGRS_CASE_REFUND_TXN_DTLS WHERE CASE_TXN_ID=?";
	//added by shruti---6 oct 2014
	public static final String GETREGDOCDTLS = "SELECT STAMP_DUTY,TOTAL FROM igrs_reg_init_stamp_duty_detls WHERE REG_TXN_ID=(SELECT REG_TXN_ID FROM igrs_reg_txn_detls WHERE REGISTRATION_NUMBER=(SELECT REGISTRATION_ID FROM igrs_case_txn_details WHERE CASE_TXN_ID=?))";

	public static final String GETREGDOCDTLSIMPOUNDCASE = "SELECT STAMP_DUTY,TOTAL FROM igrs_reg_init_stamp_duty_detls WHERE REG_TXN_ID=(SELECT REG_TXN_ID FROM igrs_case_impound_document" 
			+" WHERE COMPLAINT_ID=(SELECT REPORT_ID FROM IGRS_CASE_TXN_DETAILS WHERE CASE_TXN_ID=? ))";
	
	//END
	
	public static final String GETFINALRECOVERABLEAMOUNT="SELECT FINAL_RECOVERABLE_AMT FROM IGRS_CASE_TXN_DETAILS WHERE CASE_TXN_ID=?";

	public static final String GETCASEDOCSRNO = "SELECT IGRS_DOC_SR_NO_SEQ.NEXTVAL FROM DUAL";
	
	public static final String GETREVISEDRECOPVERABLEAMOUNTS="select PROPOSED_RECOVERABLE_AMT,PROPOSED_RECVRBLE_STAMP_DUTY,PROPOSED_RECVRBLE_REG_FEE" 
+" from IGRS_CASE_TXN_COMMENTS where CASE_ACTION_ID=? and CASE_TXN_ID=?";
	
	public static final String SELECTESTAMPTXNID="select iedd.ESTAMP_TXN_ID FROM IGRS_ESTAMP_DEACTIVATION_DETLS iedd,IGRS_CASE_REFUND_TXN_DTLS icrtd"  
					+" WHERE iedd.DEACT_REQUEST_ID=icrtd.ESTAMP_REQ_ID and icrtd.CASE_TXN_ID=?";
	
	public static final String UPDATEESTAMPSTATUS="UPDATE IGRS_ESTAMP_DETLS SET ESTAMP_STATUS='D',UPDATE_BY=?,UPDATE_DATE=SYSDATE WHERE ESTAMP_TXN_ID=?";

	public static final String CLOSETIMEBARREDCASE="UPDATE IGRS_CASE_TIME_BARRED_APP SET STATUS='C' WHERE PENALTY_ID=(select penalty_id from IGRS_CASE_OTHERS_TXN_DTLS where case_txn_id=?)";

	public static final String GETREGID="select REG_TXN_ID from IGRS_CASE_IMPOUND_DOCUMENT icid,IGRS_CASE_TXN_DETAILS ictd"
 +" where icid.COMPLAINT_ID=ictd.REPORT_ID and ictd.CASE_TXN_ID=?";
	
	//added by shruti---29 oct 2014
	public static final String GETREGNUMBER="select REGISTRATION_ID from IGRS_CASE_TXN_DETAILS ictd where ictd.CASE_TXN_ID=?";
	//end
	
	public static final String GETSALUTATIONDTLS="SELECT nvl(PARTY_FIRST_NAME,'')||' '||nvl(PARTY_LAST_NAME,''),"
	+"ORGANIZATION_NAME,PARTY_ADDRESS,iurd.FIRST_NAME ||' '||iurd.LAST_NAME,ltrim(rtrim(DEED_TYPE_NAME||' '||INSTRUMENT_NAME)),"
	+"to_char(irtd.CREATED_DATE,'dd/mm/yyyy'),to_char(SYSDATE,'dd/mm/yyyy'),iom.OFFICE_NAME from IGRS_REG_TXN_PARTY_DETLS rtpd,"
	+"IGRS_USER_REG_DETAILS iurd,IGRS_REG_TXN_DETLS irtd,IGRS_DEED_TYPE_MASTER idtm,IGRS_DEED_INSTRUMENT_MASTER iditm,"
	+"IGRS_OFFICE_MASTER iom where irtd.DEED_ID=idtm.DEED_TYPE_ID and irtd.INSTRUMENTS_ID=iditm.INSTRUMENT_ID and iurd.USER_ID= rtpd.CREATED_BY" 
	+" and rtpd.REG_TXN_ID=irtd.REG_TXN_ID and rtpd.REG_TXN_ID=trim(?) and iom.office_id=trim(?) order by PARTY_TYPE_ID"; 
	
	public static final String GETREVSALUTATIONDTLS="SELECT nvl(PARTY_FIRST_NAME,'')||' '||nvl(PARTY_LAST_NAME,''),"
		+"ORGANIZATION_NAME,PARTY_ADDRESS,iurd.FIRST_NAME ||' '||iurd.LAST_NAME,ltrim(rtrim(DEED_TYPE_NAME||' '||INSTRUMENT_NAME)),"
		+"to_char(irtd.UPDATE_DATE,'dd/mm/yyyy'),to_char(SYSDATE,'dd/mm/yyyy'),iom.OFFICE_NAME,irtd.REGISTRATION_NUMBER,to_char(ictd.NEXT_HEARING_DATE,'dd/mm/yyyy') from IGRS_CASE_TXN_DETAILS ictd,IGRS_REG_TXN_PARTY_DETLS rtpd,"
		+"IGRS_USER_REG_DETAILS iurd,IGRS_REG_TXN_DETLS irtd,IGRS_DEED_TYPE_MASTER idtm,IGRS_DEED_INSTRUMENT_MASTER iditm,"
		+"IGRS_OFFICE_MASTER iom where ictd.CASE_TXN_ID=trim(?) and irtd.DEED_ID=idtm.DEED_TYPE_ID and irtd.INSTRUMENTS_ID=iditm.INSTRUMENT_ID and iurd.USER_ID= rtpd.CREATED_BY" 
		+" and rtpd.REG_TXN_ID=irtd.REG_TXN_ID and irtd.REGISTRATION_NUMBER=trim(?) and iom.office_id=trim(?) order by PARTY_TYPE_ID"; 
		
	
	  public static final String GETOFFICENAME="SELECT iom.office_name,iom.DISTRICT_ID,idm.district_name FROM igrs_office_master iom,igrs_district_master idm"
			+" where iom.district_id=idm.district_id and iom.office_id=?";
	  
	  public static final String GETOFFICENAME_H="SELECT iom.h_office_name,iom.DISTRICT_ID,idm.h_district_name FROM igrs_office_master iom,igrs_district_master idm"
			+" where iom.district_id=idm.district_id and iom.office_id=?";
	  
	  
	  public static final String GETOFFICETYPEID="SELECT OFFICE_TYPE_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID=?";
	  
	  public static final String GETSECTIONHEADID="SELECT SECTION_HEAD_ID FROM IGRS_CASE_TXN_DETAILS WHERE CASE_TXN_ID=?";

}