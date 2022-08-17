

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
 */ 

/* 
 * FILE NAME: CitizenFeedbackSQL.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 23th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE SQL FOR CITIZEN FEEDBACK.
 */

package com.wipro.igrs.CitizenFeedback.sql;

/**
 * @author NIHAR M.
 *
 */
public class CitizenFeedbackSQL {

	public static final String INSERT_CITIZEN_FFEDBACK_DETAILS = "INSERT INTO IGRS_CITIZEN_FEEDBACK_DETAILS(PARTY_FIRST_NAME_H, PARTY_MIDDLE_NAME_H, PARTY_LAST_NAME_H, " +
	"PARTY_GENDER, PARTY_AGE, NATIONALITY, COUNTRY_ID, STATE_ID, DISTRICT_ID, PARTY_ADDRESS, POSTAL_CODE, PHONE_NUMBER, MOBILE_NUMBER, " +
	"EMAIL_ID, PHOTO_PROOF_TYPE_ID, PHOTO_PROOF_ID, BANK_NAME, BANK_ADDRESS, GUARDIAN_NAME, MOTHER_NAME, SERVICE_FUNCTION_ID, SERVICE_TXN_NO, FEEDBACK, " +
	"FEEDBACK_STATUS, CREATED_BY, TRANSACTION_ID, CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,IGRS_CTZN_FDBK_SEQ.NEXTVAL, sysdate)";


	public static final String VIEW_CITIZEN_FEEDBACK_DETAILS = "SELECT *FROM IGRS_CITIZEN_FEEDBACK_DETAILS WHERE TRANSACTION_ID = ?";
	
	public static final String INSERT="insert into TESTHINDI(COMMENTS) values(?)";


	public static final String CHECK_FEEDBACK_ID = "SELECT * FROM IGRS_CITIZEN_FEEDBACK_DETAILS WHERE TRANSACTION_ID = ?";
	
	public static final String GET_SEARCH_DETAILS_QUERY = 
			
			"Select F.CASE_REF_NO,F.CLIENT_NAME,F.CLIENT_EMAIL_ID,to_char(F.CREATED_DATE,'DD/MM/YYYY'),F.FEEDBACK_COMPLAINT_FLAG,M.MODULE_NAME,F.F_C_SATUS,M.H_MODULE_NAME,F.H_F_C_SATUS,F.H_FEEDBACK_COMPLAINT_FLAG from IGRS_FEEDBACK_COMPLAINT_MASTER F,igrs_module_master M where 1=1  AND F.SERVICE_NAME = M.MODULE_ID ";

	
	public static final String FEEDBACK_QUERY_OFFCID_CLAUSE = " AND F.DISTRICT_NAME IN(select DISTRICT_ID from IGRS_OFFICE_MASTER where OFFICE_ID = ?)";
	public static final String FEEDBACK_QUERY_REFID_CLAUSE = " AND F.CASE_REF_NO = ?";
	public static final String FEEDBACK_QUERY_STATUS_CLAUSE = " AND F.F_C_SATUS = ?";
	public static final String FEEDBACK_QUERY_DATE_BOTH_CLAUSE = " AND (F.CREATED_DATE BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')+1)";
	public static final String FEEDBACK_ORDER_BY_DATE_CLAUSE = " ORDER BY F.CREATED_DATE DESC";
	
	public static final String GET_DISTRICT_WISE_PENDING_FEEDBACK_DETAILS = "Select F.CASE_REF_NO,F.CLIENT_NAME,F.CLIENT_EMAIL_ID,to_char(F.CREATED_DATE,'DD/MM/YYYY'),F.FEEDBACK_COMPLAINT_FLAG" +
																			",M.MODULE_NAME,F.F_C_SATUS,M.H_MODULE_NAME from IGRS_FEEDBACK_COMPLAINT_MASTER F,igrs_module_master M where F.F_C_SATUS = ? AND " +
																			"F.DISTRICT_NAME IN(select DISTRICT_ID from IGRS_OFFICE_MASTER where OFFICE_ID = ?) AND F.SERVICE_NAME = M.MODULE_ID ORDER BY F.CREATED_DATE DESC ";
	
	
	public static final String GET_PENDING_FEEDBACK_DETAILS = "Select F.CASE_REF_NO,F.CLIENT_NAME,F.CLIENT_EMAIL_ID,to_char(F.CREATED_DATE,'DD/MM/YYYY'),F.FEEDBACK_COMPLAINT_FLAG" +
																",M.MODULE_NAME,F.F_C_SATUS,M.H_MODULE_NAME from IGRS_FEEDBACK_COMPLAINT_MASTER F,igrs_module_master M where F.F_C_SATUS = ? AND F.SERVICE_NAME = M.MODULE_ID ORDER BY F.CREATED_DATE DESC ";


	public static final String GET_VIEW_DETAILS = "SELECT UNIQUE F.CLIENT_NAME ,F.CLIENT_EMAIL_ID,F.DISTRICT_NAME,M.MODULE_NAME,F.FEED_COMP_COMMENTS,F.F_C_SATUS,F.H_DR_EMAIL_CONTENT,M.H_MODULE_NAME FROM IGRS_FEEDBACK_COMPLAINT_MASTER F, IGRS_DISTRICT_MASTER D,"+

												"IGRS_MODULE_MASTER M WHERE F.CASE_REF_NO = ?  AND F.SERVICE_NAME = M.MODULE_ID";


	public static final String INSERT_EMAIL_CONTENT ="INSERT INTO IGRS_EMAIL_DATA_CONTENT(EMAIL_DISPATCH_ID, EMAIL_TO_ADDRESS,"+
    	    										"EMAIL_SUBJECT, EMAIL_CONTENT, EMAIL_HEADER, EMAIL_FOOTER, EMAIL_SENT_FLAG, CREATED_BY," + 
    	    										"CREATED_DATE, UPDATE_BY, UPDATE_DATE, EMAIL_FROM_ADDRESS)"+
    	    										"VALUES(IGRS_EMAIL_SENT_SEQ1.NEXTVAL,?,?,"+
    	    										"?,'Dear Sir/Madam','Thanks & Regards','N','System',SYSDATE,'System',SYSDATE,'igrs@mp.gov')";;

   public static final String INSERT_UPDATE_COMMENTS = "UPDATE IGRS_FEEDBACK_COMPLAINT_MASTER SET H_DR_EMAIL_CONTENT = ? WHERE CASE_REF_NO = ?";
   
   public static final String UPDATE_SET_STATUS = "update IGRS_FEEDBACK_COMPLAINT_MASTER SET F_C_SATUS = ? WHERE CASE_REF_NO=?";

   public static final String SELECT_MODULE_NAME = "select module_id, module_name,H_MODULE_NAME from igrs_module_master where FEEDBACK_FLAG = ? ORDER BY MODULE_NAME ASC ";
   
   public static final String SELECT_DISTRICT_NAME = "select DISTRICT_ID,H_DISTRICT_NAME from igrs_district_master WHERE STATE_ID =1";
   
   public static final String SELECT_SEQUENCE1 = "select IGRS_BANKCAVEAT_SEQ.NEXTVAL from dual";
   
   public static final String SELECT_SEQUENCE2 = "select IGRS_FEED_COMP_PRI_KEY.nextval from dual";
   
   public static final String INSERT_REQUESTS = "insert into IGRS_FEEDBACK_COMPLAINT_MASTER"+
			 									"(CLIENT_NAME,CLIENT_EMAIL_ID,SERVICE_NAME,DISTRICT_NAME,FEED_COMP_COMMENTS,CREATED_DATE,CREATED_BY,CASE_REF_NO,F_C_SEQ_NO,F_C_SATUS,FEEDBACK_COMPLAINT_FLAG)"+
			 									"VALUES (?,?,?,?,?,SYSDATE,?,?,?,?,?)";
   
   public static final String GET_CLIENT_NAME = "select CLIENT_NAME,CLIENT_EMAIL_ID from IGRS_FEEDBACK_COMPLAINT_MASTER where CASE_REF_NO = ?";
   
   public static final String GET_FUNCTION_NAME ="Select FUNCTION_ID, FUNCTION_NAME from  IGRS_FUNCTION_MASTER";
   
   public static final String GET_MODULE_NAME = "select MODULE_NAME from IGRS_MODULE_MASTER where MODULE_ID = ?";
   
   public static final String INSERT_FIRST_EMAIL_CONTENT = "INSERT INTO IGRS_EMAIL_DATA_CONTENT(EMAIL_DISPATCH_ID, EMAIL_TO_ADDRESS,"+
   	    													"EMAIL_SUBJECT, EMAIL_CONTENT, EMAIL_HEADER, EMAIL_FOOTER, EMAIL_SENT_FLAG, CREATED_BY,"+ 
   	    													"CREATED_DATE, UPDATE_BY, UPDATE_DATE, EMAIL_FROM_ADDRESS)"+
   	    													"VALUES(IGRS_EMAIL_SENT_SEQ1.NEXTVAL,?,?,?,'Dear Sir/Madam','Thanks & Regards','N','System',SYSDATE,'System',SYSDATE,'igrs@mp.gov')";
   
   public static final String SELECT_SERVICE_NAME = "select service_name,service_id from igrs_service_master";
   public static final String SELECT_DISTRICT_NAME_H_E="SELECT H_DISTRICT_NAME, DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";
   public static final String SELECT_DISTRICT_NAME2 = "select district_name from igrs_district_master";
   
   public static final String SELECT_ROLEGROUP_NAME = "select RG.ROLE_GROUP_NAME from IGRS_ROLE_GROUP_MASTER RG," +
													" IGRS_USER_ROLE_GROUP_MAPPING URG where URG.USER_ID = ? AND URG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID";
   public static final String SELECT_DISTRICT="select DISTRICT_ID,H_DISTRICT_NAME, DISTRICT_NAME from igrs_district_master WHERE STATE_ID =1 order by DISTRICT_NAME asc";
}