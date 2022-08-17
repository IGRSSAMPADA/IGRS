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

public class CaseMonHistorySQL {

	public static final String GETCASESECTONLIST="select section_name,section_id from igrs_case_section_master where section_status='A'";
	public static final String GETCASESECTONLIST_H="select h_section_name,section_id from igrs_case_section_master where section_status='A'";
	public static final String GETCASESTATUSLIST="select case_status,sr_no from igrs_case_status_master";
	public static final String GETCASESTATUSLIST_H="select h_case_status,sr_no from igrs_case_status_master";
	public static final String GETCASEDIVISIONLIST="select division_name,division_id from igrs_case_division_master where division_status='A'";
	public static final String GETCASEDIVISIONLIST_H="select h_division_name,division_id from igrs_case_division_master where division_status='A'";
	public static final String SAVECASEDATA="Insert into IGRS_CASE_HISTORY_DTLS(REFERENCE_NO,CASE_TYPE_FLAG,SECTION_NO,CASE_NUMBER,"
											+"PARTY_DETAILS,PROPERTY_DETAILS,STAMP_CASE_AMOUNT,STAMP_DUTY,REG_FEE,REC_STAMP_DUTY,REC_REG_FEE,"
											+"CASE_STATUS,DIVISION,RRC_AMOUNT,REMARKS,CREATED_BY,CREATED_DATE)" 
											+" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
	public static final String GETDATEDATA="select to_char(sysdate,'DDMMYYYY') from dual";
	public static final String GETSERIALNUMBER="SELECT lpad(IGRS_PENDING_CASE_TXN_SEQ.NEXTVAL,4,0) FROM DUAL";
	public static final String REFRENCENOBASEDSEARCH="SELECT REFERENCE_NO,CASE_TYPE_FLAG,SECTION_NO,CASE_NUMBER,PARTY_DETAILS,PROPERTY_DETAILS,STAMP_CASE_AMOUNT,STAMP_DUTY,REG_FEE,REC_STAMP_DUTY,REC_REG_FEE,CASE_STATUS,DIVISION,RRC_AMOUNT,REMARKS,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_HISTORY_DTLS WHERE REFERENCE_NO=?";
	public static final String DURATIONBASEDSEARCH="SELECT REFERENCE_NO,CASE_TYPE_FLAG,SECTION_NO,CASE_NUMBER,PARTY_DETAILS,PROPERTY_DETAILS,STAMP_CASE_AMOUNT,STAMP_DUTY,REG_FEE,REC_STAMP_DUTY,REC_REG_FEE,CASE_STATUS,DIVISION,RRC_AMOUNT,REMARKS,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_HISTORY_DTLS WHERE CREATED_DATE BETWEEN TO_DATE(?,'DD-MM-YY') AND TO_DATE(?,'DD-MM-YY')+1";
	public static final String MIXEDSEARCH="SELECT REFERENCE_NO,CASE_TYPE_FLAG,SECTION_NO,CASE_NUMBER,PARTY_DETAILS,PROPERTY_DETAILS,STAMP_CASE_AMOUNT,STAMP_DUTY,REG_FEE,REC_STAMP_DUTY,REC_REG_FEE,CASE_STATUS,DIVISION,RRC_AMOUNT,REMARKS,TO_CHAR(CREATED_DATE,'DD/MM/YYYY') FROM IGRS_CASE_HISTORY_DTLS WHERE REFERENCE_NO=? AND CREATED_DATE BETWEEN TO_DATE(?,'DD-MM-YY') AND TO_DATE(?,'DD-MM-YY')+1";
	public static final String UPDATECASEDATA="Update IGRS_CASE_HISTORY_DTLS set SECTION_NO=?,CASE_NUMBER=?,PARTY_DETAILS=?,PROPERTY_DETAILS=?,STAMP_CASE_AMOUNT=?,STAMP_DUTY=?,REG_FEE=?,REC_STAMP_DUTY=?,REC_REG_FEE=?,CASE_STATUS=?,DIVISION=?,RRC_AMOUNT=?,REMARKS=?,UPDATE_BY=?,UPDATE_DATE=SYSDATE WHERE REFERENCE_NO=?";
	public static final String INSERTDOCDETLS="INSERT INTO IGRS_CASE_DOC_DTLS(SR_NO,REFERENCE_NO,DOC_NAME,DOC_PATH,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?,SYSDATE)";
	public static final String GETUPLOADSRNO="SELECT IGRS_PEDNING_CASE_DOC_TXN_SEQ.NEXTVAL FROM DUAL";
	public static final String GETSAVEDUPLOADS="SELECT SR_NO,DOC_NAME,DOC_PATH FROM IGRS_CASE_DOC_DTLS WHERE REFERENCE_NO=? ";
	public static final String REMOVESAVEDUPLOADS="DELETE FROM IGRS_CASE_DOC_DTLS WHERE SR_NO=? AND REFERENCE_NO=? ";
	public static final String UPDATESAVEDUPLOADS="INSERT INTO IGRS_CASE_DOC_DTLS(SR_NO,REFERENCE_NO,DOC_NAME,DOC_PATH,UPDATE_BY,UPDATE_DATE) VALUES(?,?,?,?,?,SYSDATE)";
	
	
	
	
	
}