
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
 * FILE NAME: HolidayListSQL.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 8th AUGUST 2008
 * MODIFIED ON:	   None
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE SQL FOR HOLIDAY LIST.
 */


package com.wipro.igrs.holidaylist.sql;

/**
 * @author NIHAR M.
 *
 */
public class HolidayListSQL {


	public static final String CREATE_NEW_HOLIDAY = "INSERT INTO IGRS_HOLIDAY_LIST(HOLIDAY_ID, CALENDAR_YEAR, HOLIDAY_DATE, HOLIDAY_DESC, HOLIDAY_STATUS, " +
	"CREATED_BY, APPROVAL_STATUS, CREATED_DATE) VALUES(IGRS_HOLIDAY_ID_SEQ.NEXTVAL, ?, to_date(?, 'DD/MM/YY'), ?, ?, ?, ?, SYSDATE) ";

	public static final String GET_HOLIDAY_LIST = "SELECT holiday_id, to_char(holiday_date,'dd/mm/yy'), holiday_desc,"+       
	"holiday_status, created_by , created_date, update_by, update_date, approved_by, approved_date, approver_remarks,"+   
	"calendar_year, approval_status FROM IGRS_HOLIDAY_LIST";

	public static final String REMOVE_HOLIDAY = "DELETE IGRS_HOLIDAY_LIST USERS WHERE HOLIDAY_ID = ?";

	public static final String GET_IND_HOLIDAY_DETAILS = "select HOLIDAY_ID, CALENDAR_YEAR, to_char(HOLIDAY_DATE, 'dd/mm/yy'), HOLIDAY_DESC, HOLIDAY_STATUS " +
	"from IGRS_HOLIDAY_LIST where HOLIDAY_ID= ?"; 

	public static final String UPDATE_HOLIDAY_DETAILS = "UPDATE IGRS_HOLIDAY_LIST SET HOLIDAY_DESC = ?, HOLIDAY_STATUS = ?, " +
	"HOLIDAY_DATE = to_date(?, 'dd/mm/yy'), UPDATE_BY = ?, UPDATE_DATE = sysdate WHERE HOLIDAY_ID = ?";


	public static final String APPROVE_HOLIDAY_DETAILS = "UPDATE IGRS_HOLIDAY_LIST SET HOLIDAY_STATUS = ?, " +
	"UPDATE_BY = ?,UPDATE_DATE = SYSDATE WHERE HOLIDAY_ID =?";
}
