
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
 * FILE NAME: RegistrationCompletionSQL.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE SQL FOR REGISTRATION COMPLETION ACTION.
 */

package com.wipro.igrs.RegistrationCompletion.sql;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionSQL {

	public static final String GET_TXN_ID_DETAILS = "SELECT TRANSACTION_ID, to_char(CREATED_DATE), REGISTRATION_STATUS FROM igrs_reg_completion_details WHERE"+" "+
	"decode(transaction_id,   ?,   ?,   'NA') = nvl(?,   'NA')"+
	"AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   ?,   ?,   to_char(created_date,   'DD-MM-YY'),"+
	"decode(?,   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY')"+
	">= to_date(nvl(to_char(to_date(?,   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')"+
	" "+"AND to_date(decode(to_char(created_date,   'DD-MM-YY'),   ?,   ?,   to_char(created_date,   'DD-MM-YY'),"+   
	"decode(?,   NULL,   '1/JAN/2010',   to_char(created_date,   'DD-MM-YY'))),   'DD-MM-YY')" +
	"<= to_date(nvl(to_char(to_date(?,   'DD-MM-YY'),   'DD-MM-YY'),   '1/JAN/2010'),   'DD-MM-YY')";


//	public static final String INDIVIDUAL_REG_ID_DETAILS = "select transaction_id, registration_status from" +" "+
//	"IGRS_REG_COMPLETION_DETAILS where transaction_id = ?";
	
	public static final String INDIVIDUAL_REG_ID_DETAILS = "select transaction_id, REG_STATUS_NAME from IGRS_REG_COMPLETION_DETAILS RC,IGRS_REG_COMP_STATUS_MASTER CM where RC.REGISTRATION_STATUS=CM.REG_STATUS_ID AND transaction_id =?";
	
	//select transaction_id, REG_STATUS_NAME from IGRS_REG_COMPLETION_DETAILS RC,IGRS_REG_COMP_STATUS_MASTER CM where RC.REGISTRATION_STATUS=CM.REG_STATUS_ID AND transaction_id = 'MP123456'

	public static final String UPDATE_REG_TXN_DETAILS = "update IGRS_REG_COMPLETION_DETAILS set registration_status = ?" +" "+
	"where transaction_id = ?";

	public static final String INSERT_REG_DETAILS = "insert into IGRS_REG_COMP_STATUS_DTLS(reg_txn_id, reg_status_id, reg_status_remarks,EXPECTED_DATE_OF_DELIVERY) values(?,?,?,(to_date(?,'DD-MM-RRRR')))";
}
