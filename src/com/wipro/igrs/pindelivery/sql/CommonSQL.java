package com.wipro.igrs.pindelivery.sql;
/* Copyright (c) 2006-2008 WIPRO. All Rights Reserved.
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


/**
 * @author Madan Mohan
 *
 */
public class CommonSQL {

	public static final String PIN_DELIVERY_QUERY = 
		"SELECT DISTINCT REGISTRATION_ID FROM IGRS_REG_PIN_DETAILS WHERE DELIVERY_STATUS='N' ";
	
	public static final String INSERT_DELIVERY_QUERY = 
		"UPDATE IGRS_REG_PIN_DETAILS SET "
		+"DELIVERY_STATUS='Y',DELIVERY_DATE=SYSDATE,"
		+"DELIVERY_COMMENT=?,DELIVERY_COMMENT_BY=? WHERE REGISTRATION_ID=? "
		+" AND DELIVERY_STATUS='N'";
}
