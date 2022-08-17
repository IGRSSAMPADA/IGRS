

package com.wipro.igrs.hrpayroll.hrpl.sql;


public final class CommonSQL {
	
	public static final String RETRIVE_LEAVES_ON_ID1 = "select emldt.EMP_ID,emp.first_name||' '||middle_name||' '||last_name,(emldt.leave_to - emldt.leave_from)+1,leave.leave_type_name,approval_status,trim(to_char(emldt.leave_from,'DD/MM/YYYY')),trim(to_char(emldt.leave_to,'DD/MM/YYYY')),emldt.reason_for_leave,leave.leave_type_id from igrs_emp_leave_details emldt, IGRS_EMP_MASTER emp, IGRS_EMP_LEAVE_TYPE_MASTER leave where emldt.emp_id = emp.emp_id and   emldt.leave_type_id = leave.leave_type_id and emldt.TRANSACTION_ID=?";

	public static final String RETRIVE_ALL_LEAVES = "select emldt.TRANSACTION_ID,(emldt.leave_to - emldt.leave_from)+1,leave.leave_type_name,approval_status,trim(to_char(emldt.leave_from,'DD/MM/YYYY')),trim(to_char(emldt.leave_to,'DD/MM/YYYY')),emldt.reason_for_leave from igrs_emp_leave_details emldt,IGRS_EMP_LEAVE_TYPE_MASTER leave where  emldt.leave_type_id = leave.leave_type_id and emldt.approval_status='PENDING'";

	public static final String RETRIVE_LEAVES = "select ielcm.CONVERT_LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME from IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_CONVERSION_MAP ielcm where ieltm.LEAVE_TYPE_ID=ielcm.CONVERT_LEAVE_TYPE_ID	and ielcm.LEAVE_TYPE_ID=?";

	public static final String UPDATE_LEAVE_STATUS = "UPDATE IGRS_EMP_LEAVE_DETAILS SET UPDATED_BY=?,UPDATED_DATE=SYSDATE,CONVERSION_FLAG=?,CONVERTED_FROM=?,APPROVAL_STATUS=?,APPROVAL_DATE=SYSDATE,APPROVED_BY=?,APPROVAL_REMARKS=? WHERE TRANSACTION_ID=? AND APPROVAL_STATUS='PENDING'";

	public static final String RETRIVE_ALL_LEAVE_CADRE_NOTIN = "select LEAVE_TYPE_ID,LEAVE_TYPE_NAME from IGRS_EMP_LEAVE_TYPE_MASTER where LEAVE_TYPE_ID NOT IN (SELECT DISTINCT(LEAVE_TYPE_ID) FROM IGRS_EMP_LEAVE_TYPE_MAPPING WHERE GRADE_ID =? AND CADRE_ID=? AND LEAVE_TYPE_ID IS NOT NULL ) AND LEAVE_TYPE_STATUS = 'A'";

	public static final String RETRIVE_ALL_LEAVE_ID = "select LEAVE_TYPE_ID,LEAVE_TYPE_NAME from IGRS_EMP_LEAVE_TYPE_MASTER";

	public static final String RETRIVE_ALL_LEAVE_GRADE_CADRE = "select ieltm.LEAVE_TYPE_ID,ieltmaster.LEAVE_TYPE_NAME from IGRS_EMP_LEAVE_TYPE_MAPPING ieltm,IGRS_EMP_LEAVE_TYPE_MASTER ieltmaster where ieltm.GRADE_ID=? AND ieltm.CADRE_ID=? AND ieltm.LEAVE_TYPE_ID=ieltmaster.LEAVE_TYPE_ID";

	public static final String DELETE_LEAVE_GRADE_CADRE = "delete from IGRS_EMP_LEAVE_TYPE_MAPPING where LEAVE_TYPE_ID=? AND GRADE_ID=? AND CADRE_ID=?";

	public static final String RETRIVE_ALL_GRADE_ID = "select GRADE_ID,GRADE_NAME	from IGRS_EMP_GRADE_MASTER";

	public static final String RETRIVE_ALL_CADRE_ID = "select CSD_MAST.CADRE_ID,CSD_MAST.CADRE_NAME "
													 + " from IGRS_GRADE_CADRE_MAPPING CDR_MAP,"
													 +" IGRS_EMP_CADRE_MASTER CSD_MAST"
													 +" WHERE CSD_MAST.CADRE_ID= CDR_MAP.CADRE_ID"
													 +" AND CDR_MAP.GRADE_ID=?";

	public static final String INSERT_LEAVE_GRADE_CADRE = "insert into IGRS_EMP_LEAVE_TYPE_MAPPING (GRADE_ID,CADRE_ID,LEAVE_TYPE_ID) values(?,?,?)";

	/*public static final String RETRIVE_LEAVE_EMP_REPORT = "SELECT ieltm.LEAVE_TYPE_NAME,"
		+ "NVL(SUM(ield.LEAVE_TO-ield.LEAVE_FROM),0),ielbd.LEAVE_BALANCE"
		+ " FROM  IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield"
		+ " WHERE  ielbd.LEAVE_TYPE_ID   = ieltm.LEAVE_TYPE_ID(+)"
		+ " AND    ielbd.EMP_ID          =ield.EMP_ID(+)"
		+ " AND    ielbd.LEAVE_TYPE_ID   = ield.LEAVE_TYPE_ID(+)"
		+ " AND    ielbd.EMP_ID          =?"
		+ " AND    ield.APPROVAL_STATUS = 'APPROVED'"
		+ " GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE"
		+ " UNION"
		+ " SELECT ieltm.LEAVE_TYPE_NAME,NVL(SUM(ield.LEAVE_TO-ield.LEAVE_FROM),0),ielbd.LEAVE_BALANCE"
		+ " FROM   IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield"
		+ " WHERE  ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)"
		+ " AND    ielbd.EMP_ID        =  ield.EMP_ID(+)"
		+ " AND    ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+)"
		+ " AND    ielbd.EMP_ID =?"
		+ " AND    ield.APPROVAL_STATUS = 'APPROVED'"
		+ " AND    ield.leave_from BETWEEN CONCAT('1-JAN-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))"
		+ " AND CONCAT('31-DEC-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))"
		+ " GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE ORDER BY 1";*/
	
	public static final String RETRIVE_LEAVE_EMP_REPORT="SELECT ieltm.LEAVE_TYPE_NAME"
	+" ,(SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM) +1),0)"
	+" FROM IGRS_EMP_LEAVE_DETAILS ield"
	+" WHERE ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID)"
	+" ,ielbd.LEAVE_BALANCE"
	+" FROM IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd"
	+" ,IGRS_EMP_LEAVE_TYPE_MASTER ieltm"
	+" ,IGRS_EMP_LEAVE_DETAILS ield"
	+" WHERE ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)"
	+" AND ielbd.EMP_ID = ield.EMP_ID(+)"
	+" AND ielbd.EMP_ID = ?"
	+" AND ield.APPROVAL_STATUS = 'APPROVED'"
	+" GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE"
	+" UNION"
	+" SELECT ieltm.LEAVE_TYPE_NAME"
	+" ,(SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM) +1),0)"
	+" FROM IGRS_EMP_LEAVE_DETAILS ield"
	+" WHERE ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID)"
	+" ,ielbd.LEAVE_BALANCE"
	+" FROM IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd"
	+" ,IGRS_EMP_LEAVE_TYPE_MASTER ieltm"
	+" ,IGRS_EMP_LEAVE_DETAILS ield"
	+" WHERE ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)"
	+" AND ielbd.EMP_ID = ield.EMP_ID(+)"
	+" AND ielbd.EMP_ID = ?"
	+" AND ield.APPROVAL_STATUS = 'APPROVED'"
	+" AND ield.leave_from BETWEEN CONCAT('1-JAN-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))"
	+" AND CONCAT('31-DEC-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))"
	+" GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE ORDER BY 1";
	
	
	
	public static final String RETRIVE_ALL_FISCALFROMYEAR = "select FISCAL_YEAR_ID,trim(to_char(FISCALYEAR_FROM,'MONTH-YYYY')),trim(to_char(FISCALYEAR_TO,'MONTH-YYYY')) from IGRS_FISCAL_YEAR";

	/*
	 * Attentandce
	 */
	public static final String UPDATE_ATTENDANCE_QUERY = "UPDATE IGRS_EMP_ATTANDANCE_DETAILS SET LOGGED_IN_TIME = TO_TIMESTAMP(?,'DD/MM/RRRR HH:MIPM'),LOGGED_OUT_TIME=TO_TIMESTAMP(?,'DD/MM/RRRR HH:MIPM'),UPDATED_BY=?,UPDATED_DATE=SYSDATE,REMARKS=? WHERE (TRUNC(LOGGED_IN_TIME)=TO_TIMESTAMP(?,'DD/MM/RRRR') OR TRUNC(LOGGED_OUT_TIME)=TO_TIMESTAMP(?,'DD/MM/RRRR')) AND EMP_ID=?";

	public static final String RETRIVE_ATTENDANCE_DETAILS = "select to_char(logged_in_time, 'DD/MM/YYYY'),"
		+ "to_char(logged_in_time,'Day'),holiday_status,to_char(LOGGED_IN_TIME,'HH12:MIAM'),"
		+ "to_char(LOGGED_OUT_TIME,'HH12:MIAM'),ROUND((LOGGED_OUT_TIME - LOGGED_IN_TIME)*24,2)"
		+ " FROM IGRS_EMP_ATTANDANCE_DETAILS igrs, IGRS_EMP_MASTER emp "
		+ "	WHERE igrs.emp_id = emp.emp_id(+) "
		+ "AND igrs.emp_id =? AND   trim(TO_CHAR(TO_DATE(logged_in_time),'MON'))= UPPER(?)"
		+ " AND trim(TO_CHAR(TO_DATE(logged_in_time),'RRRR'))=? ORDER BY logged_in_time";

	public static final String RETREIVE_ALL_CADRES = "SELECT CADRE_ID, CADRE_NAME, NO_OF_POSTS FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_STATUS = 'A' ORDER BY CADRE_ID";

	public static final String RETREIVE_COUNT_CADRES = "SELECT SUM(NO_OF_POSTS) FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_STATUS ='A'";

	public static final String INSERT_CADRE = "INSERT INTO IGRS_EMP_CADRE_MASTER(CADRE_ID, CADRE_NAME, CADRE_STATUS, NO_OF_POSTS, CREATED_BY, CREATED_DATE)"
		+ "VALUES(?,?,?,?,?,SYSDATE)";

	public static final String RETREIVE_CADRE_SEQ_ID = "SELECT IGRS_EMP_CADRE_MASTER_SEQ.NEXTVAL FROM DUAL";

	public static final String DELETE_CADRES = "UPDATE IGRS_EMP_CADRE_MASTER SET CADRE_STATUS = ?, UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE CADRE_ID=?";

	public static final String EDIT_CADRES = "UPDATE IGRS_EMP_CADRE_MASTER SET NO_OF_POSTS = ?,UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE CADRE_ID=? AND CADRE_STATUS='A'";

	/*
	 * Arrears SQL
	 */
	public static final String SELECT_ARREARS_EMPID = "SELECT EMP_ID FROM IGRS_EMP_MASTER";

	public static final String INSERT_ARREARS = "insert into IGRS_EMP_PAY_ARREARS_DETAILS (EMP_ID, ARREAR_TYPE, ARREAR_AMOUNT,ARREAR_PAY_MONTH, ARREAR_STATUS, EMP_PAY_ARREARS_ID,"
		+ "CREATED_BY,UPDATED_BY,CREATED_DATE,UPDATED_DATE,ARREAR_COMMENTS) VALUES(?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?)";

	/*
	 * sequence for penalty and arrears
	 */
	public static final String SELECT_ARREARS_TRANS_ID_SEQ = "SELECT IGRS_EMP_ARREARS_SEQ.NEXTVAL FROM DUAL";

	public static final String SELECT_PENALTY_TRANS_ID_SEQ = "SELECT IGRS_EMP_PENALITY_TXN_SEQ.NEXTVAL FROM DUAL";

	public static final String INSERT_PENALTY = "INSERT INTO IGRS_EMP_PENALTY_DETAILS (EMPLOYEE_ID, PENALTY_COMMENTS,PENALTY_ACTION,PENALTY_AMT,PENALTY_AMT_TYPE,PENALTY_DURATION_TYPE,PENALTY_DURATION,PENALTY_STATUS,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,EMP_PENLATY_ID,PENALTY_TYPE_ID) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?)";

	/*
	 * 
	 * Leave Application Form
	 */

	public static final String RETRIVE_ALL_LEAVES_ID = "select ieltmap.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME from IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_TYPE_MAPPING ieltmap,IGRS_EMP_GRADE_CADRE_MAPPING iegcm where ieltmap.LEAVE_TYPE_ID=ieltm.LEAVE_TYPE_ID AND ieltmap.GRADE_ID=iegcm.GRADE_ID AND ieltmap.CADRE_ID=iegcm.CADRE_ID AND ieltm.GENDER in ((Select GENDER from IGRS_EMP_MASTER where EMP_ID=iegcm.EMP_ID),'A') AND iegcm.EMP_ID=?";
	
	public static final String INSERT_IGRS_EMP_LEAVE_DETAILS = "INSERT INTO IGRS_EMP_LEAVE_DETAILS(EMP_ID,LEAVE_TYPE_ID,LEAVE_FROM,LEAVE_TO,REASON_FOR_LEAVE,ADDRESS_ON_LEAVE,CONTACT_NUMBER,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,TRANSACTION_ID,CONVERSION_FLAG,CONVERTED_FROM,APPROVAL_STATUS,APPROVAL_DATE,APPROVED_BY,APPROVAL_REMARKS) VALUES(?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?,?,?,?)";

	/*public static final String SELECT_USED_AVAIL_LEAVE = "SELECT NVL(SUM(ield.LEAVE_TO-ield.LEAVE_FROM),0),ielbd.LEAVE_BALANCE FROM IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield "
		+ "	WHERE  ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+) AND    ielbd.EMP_ID =  ield.EMP_ID(+) AND ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+) AND ielbd.EMP_ID =? AND ielbd.LEAVE_TYPE_ID=? "
		+ "	GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE"
		+ "	UNION SELECT NVL(SUM(ield.LEAVE_TO-ield.LEAVE_FROM),0),ielbd.LEAVE_BALANCE FROM   IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield WHERE  ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+) AND    ielbd.EMP_ID =  ield.EMP_ID(+) AND    ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+) AND    ielbd.EMP_ID =? AND   "
		+ " 	ield.leave_from BETWEEN CONCAT('1-JAN-',(TO_CHAR(TO_DATE(sysdate),'RRRR'))) AND CONCAT('31-DEC-',(TO_CHAR(TO_DATE(sysdate),'RRRR'))) AND    ield.LEAVE_TYPE_ID=? GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE";*/
 //new
	
	public static final String SELECT_USED_AVAIL_LEAVE="SELECT (SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM) +1),0) FROM IGRS_EMP_LEAVE_DETAILS ield"
		 +"	 WHERE ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID),ielbd.LEAVE_BALANCE" 
		 +"	 FROM IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield" 
		 +"	 WHERE  ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)" 
	     +"    AND    ielbd.EMP_ID =  ield.EMP_ID(+)" 
		 +"     AND ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+)" 
		 +"   AND ielbd.EMP_ID =?"
		 +"  AND ielbd.LEAVE_TYPE_ID=?" 
		 +" GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE"
		 +" UNION" 
		 +" SELECT (SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM) +1),0) FROM IGRS_EMP_LEAVE_DETAILS ield"
		 +" WHERE ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID),ielbd.LEAVE_BALANCE" 
		 +"  FROM   IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield" 
		 +"  WHERE  ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)" 
		 +"  AND    ielbd.EMP_ID =  ield.EMP_ID(+)" 
		 +" AND    ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+) AND    ielbd.EMP_ID =?" 
		 +" AND"   
		 +" ield.leave_from" 
		 +"  BETWEEN CONCAT('1-JAN-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))" 
		 +" AND CONCAT('31-DEC-',(TO_CHAR(TO_DATE(sysdate),'RRRR')))" 
		 +" AND    ield.LEAVE_TYPE_ID=?"
		 +" GROUP BY ielbd.EMP_ID,ielbd.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE";
			                        
	
	public static final String SELECT_BALANCED_LEAVE = "SELECT NVL(SUM(ield.LEAVE_TO-ield.LEAVE_FROM),0),ielbd.LEAVE_BALANCE From  IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield" +
			" WHERE ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+) AND   ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+) AND ielbd.EMP_ID = ield.EMP_ID(+) AND ielbd.EMP_ID=? AND ielbd.LEAVE_TYPE_ID=? GROUP BY ield.EMP_ID,ield.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE";
	//new
  /*  public static final String SELECT_BALANCED_LEAVE = "  SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM)),0),ielbd.LEAVE_BALANCE "
                        + " From  IGRS_EMP_LEAVE_BALANCE_DETAILS ielbd,IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_DETAILS ield"
			            + " WHERE ielbd.LEAVE_TYPE_ID = ieltm.LEAVE_TYPE_ID(+)"
                       + " AND   ielbd.LEAVE_TYPE_ID = ield.LEAVE_TYPE_ID(+)" 
                        + " AND ielbd.EMP_ID = ield.EMP_ID(+)" 
                        +" AND ielbd.EMP_ID=?" 
                        +" AND ielbd.LEAVE_TYPE_ID=?"
                        +" GROUP BY ield.EMP_ID,ield.LEAVE_TYPE_ID,ieltm.LEAVE_TYPE_NAME,IELBD.LEAVE_BALANCE";*/
    
    
	public static final String SELECT_BALANCE_LEAVES = "SELECT LEAVE_BALANCE from IGRS_EMP_LEAVE_BALANCE_DETAILS where EMP_ID=? AND LEAVE_TYPE_ID=?";

	public static final String RETREIVE_LEAVE_TRANSACTIONID = "SELECT IGRS_LEAVE_DETAILS_TXN_SEQ.NEXTVAL FROM DUAL";

	public static final String UPDATE_LEAVE_BALANCE = "UPDATE IGRS_EMP_LEAVE_BALANCE_DETAILS SET LEAVE_BALANCE = ? WHERE EMP_ID=? AND LEAVE_TYPE_ID=?";

	/*public static final String SELECT_USED_LEAVES ="SELECT NVL(SUM((ield.LEAVE_TO-ield.LEAVE_FROM) +1),0) FROM IGRS_EMP_LEAVE_DETAILS ield	 WHERE ield.LEAVE_TYPE_ID =?" +
			" and APPROVAL_STATUS not in ('REJECTED') AND ield.leave_from BETWEEN CONCAT('1-JAN-',(TO_CHAR(TO_DATE(sysdate),'RRRR'))) AND CONCAT('31-DEC-',(TO_CHAR(TO_DATE(sysdate),'RRRR'))) and emp_id=?";

	public static final String SELECT_AVAILABLE_LEAVE_MASTER = "select ieltm.MAXIMUM_NO_DAYS from IGRS_EMP_LEAVE_TYPE_MASTER ieltm,IGRS_EMP_LEAVE_TYPE_MAPPING ieltmap," +
			"IGRS_EMP_GRADE_CADRE_MAPPING iegcm where ieltm.LEAVE_TYPE_ID=? AND ieltmap.GRADE_ID=iegcm.GRADE_ID AND ieltmap.CADRE_ID=iegcm.CADRE_ID " +
			"AND ieltmap.LEAVE_TYPE_ID=ieltm.LEAVE_TYPE_ID AND ieltm.GENDER in ((Select GENDER from IGRS_EMP_MASTER where EMP_ID=iegcm.EMP_ID),'A') AND iegcm.EMP_ID=?";

	public static final String SELECT_LEAVE_BALANCE = "select LEAVE_BALANCE from IGRS_EMP_LEAVE_BALANCE_DETAILS where EMP_ID=? and LEAVE_TYPE_ID=?";

	public static final String INSERT_IGRS_EMP_LEAVE_BALANCE_DETAILS = "insert into IGRS_EMP_LEAVE_BALANCE_DETAILS(EMP_ID,LEAVE_TYPE_ID,LEAVE_BALANCE,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE) values(?,?,?)";
*/
}
