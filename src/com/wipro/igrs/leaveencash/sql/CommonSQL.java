package com.wipro.igrs.leaveencash.sql;
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

	public static final String EMP_DETAILS_QUERY = 
		"SELECT EMP.FIRST_NAME,EMP.MIDDLE_NAME,EMP.LAST_NAME, "
		+"TO_CHAR(EMP.DATE_OF_BIRTH,'DD/MM/YYYY'),EMP.GENDER, "
		+"TO_CHAR(EMP.DATE_OF_JOINING,'DD/MM/YYYY'), "
		+"TO_CHAR(SYSDATE,'YYYY')-TO_CHAR(DATE_OF_BIRTH,'YYYY')," 
		+" TO_CHAR(SYSDATE,'YYYY')-1 "
		+"FROM IGRS_EMP_MASTER EMP,IGRS_USERS_LIST USERL, "
		+"IGRS_EMP_OFFICIAL_DETAILS OFFD "
		+"WHERE EMP.EMP_ID=USERL.USER_ID AND EMP.EMP_ID=OFFD.EMP_ID "
		+"AND EMP.EMP_ID=? AND OFFD.EMP_STATUS='A'";
	
	 
	
	public static final String EMP_VALIDATE_LEAVEENCASHMENT = 
		"SELECT COUNT(*) FROM IGRS_EMP_LEAVE_ENCASH_DETAILS WHERE "
		+"EMP_ID=? AND TO_CHAR(ENCASHED_DATE,'YYYY')= "
		+"TO_CHAR(SYSDATE,'YYYY')";
	
	public static final String EMP_MAX_ENCASHMENT = 
		"SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST "
		+" WHERE ATTRIBUTE_NAME=? AND ATTRIBUTE_STATUS='A'";
	
	public static final String EMP_LEAVE_DETAILS = 
		"CALL IGRS_EMP_LEAVE_EARNED_DTL_PROC(?,?,?,?,?)";
	
	public static final String FISCAL_YEAR_ID =
		"SELECT  MAX(FISCAL_YEAR_ID) FROM IGRS_FISCAL_YEAR";
	
	public static final String INSERT_ENCASH_LEAVE =
		"INSERT INTO IGRS_EMP_LEAVE_ENCASH_DETAILS(EMP_ID,FISCAL_YEAR_ID,"
		+"LEAVE_ENCASHED,ENCASHED_DATE,REMARKS,CREATED_BY,CREATED_DATE) "
		+"VALUES(?,?,?,SYSDATE,?,?,SYSDATE)";
	
}

