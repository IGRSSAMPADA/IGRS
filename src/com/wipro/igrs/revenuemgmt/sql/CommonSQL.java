/* 
 * <Copyright information>
 *
 * <Customer specific copyright notice (if any) >
 *
 * ==============================================================================
 * This file contains proprietary information of Wipro Infotech Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007
 * ==============================================================================
 * 
 * File Name	   		: CommonSQL.java
 *
 * Description	   		: This class defines RevenueMgmt specific keys/constants 
 * 					 		for Business Events, Queries and Query parameters
 *
 * Version Number  		: 1.0 
 *
 * Created Date	   		: 28 11 2007  
 *
 * Modification History	: Created
 */


package com.wipro.igrs.revenuemgmt.sql;


/**
* @author  Wipro Infotech, Sreelatha M
* @version 1.0, 28/11/2007
* @since   1.0
*/

public class CommonSQL {
	
    public CommonSQL() {
    }
    
    /**
	* ...String ADVANCE_PAYMENTS documentation comment
	* for displaying the service provider details...
	*/
    public static final String ADVANCE_PAYMENTS = "SELECT CREDIT_AMOUNT," +
    									"TO_CHAR(CREDIT_DATE,'dd/mm/yyyy')," +
							    		"PAYMENT_MODE,RECEIPT_NO_SP,DEBIT_AMOUNT," +
							    		"TO_CHAR(DEBIT_DATE,'dd/mm/yyyy')," +
							    		"PURPOSE,RECEIPT_NO_USER,BALANCE_AMOUNT FROM " +
							    		"IGRS_ADVANCE_PAYMENTS WHERE SP_NAME = ";
    /**
	* ...String REVENUE_COLLECTION_REPORTS documentation comment
	* for displaying the data relating to all transactions types...
	*/
    public static final String REVENUE_COLLECTION_REPORTS = "SELECT ifm.FUNCTION_NAME,ipmd.TRANSACTION_ID,iptm.PAYMENT_TYPE_NAME,TO_CHAR(ipmd.CREATED_DATE,'DD/MM/YYYY'),ipmd.GROSS_AMOUNT,ipmd.USER_ID FROM IGRS_PAYMENT_MODE_DETAILS ipmd,IGRS_OFFICE_MASTER iom,IGRS_PAYMENT_TYPE_MASTER iptm,IGRS_FUNCTION_MASTER ifm";
    /**
	* ...String REV_RECON_REPORT documentation comment
	* for selecting data relating to rev.ReconciliationRpt...
	*/
    public static final String REV_RECON_REPORT = "SELECT sum(GROSS_AMOUNT) FROM IGRS_PAYMENT_MODE_DETAILS ipmd";
    /**
	* ...String CHALLAN_PAYMENTS documentation comment
	* for selecting data relating to challan payments...
	*/    
    public static final String CHALLAN_PAYMENTS = "SELECT TO_CHAR(ipmd.CREATED_DATE,'DD/MM/YYYY'),ipmd.GROSS_AMOUNT,ipmd.TRANSACTION_ID,iptm.PAYMENT_TYPE_NAME,ifm.FUNCTION_NAME,iom.OFFICE_ADDRESS,ipmd.USER_ID,ipmd.CHALLAN_ONLINE_NUMBER,iom.OFFICE_NAME FROM IGRS_PAYMENT_MODE_DETAILS ipmd,IGRS_OFFICE_MASTER iom,IGRS_PAYMENT_TYPE_MASTER iptm,IGRS_FUNCTION_MASTER ifm";
    /**
	* ...String CASH_ONLINE_PAYMENTS documentation comment
	* for selecting data relating to cash and online payments...
	*/
    public static final String CASH_ONLINE_PAYMENTS = "SELECT TO_CHAR(ipmd.CREATED_DATE,'DD/MM/YYYY'),ipmd.GROSS_AMOUNT,ipmd.TRANSACTION_ID,iptm.PAYMENT_TYPE_NAME,ifm.FUNCTION_NAME,iom.OFFICE_ADDRESS,ipmd.USER_ID,iom.OFFICE_NAME FROM IGRS_PAYMENT_MODE_DETAILS ipmd,IGRS_OFFICE_MASTER iom,IGRS_PAYMENT_TYPE_MASTER iptm,IGRS_FUNCTION_MASTER ifm";
    /**
	* ...String SP_DETAILS documentation comment...
	*/
    public static final String SP_DETAILS = "SELECT a.USER_ID,b.FIRST_NAME FROM IGRS_USERS_LIST a , IGRS_USER_REG_DETAILS b WHERE USER_TYPE_ID='3' and a.USER_ID = B.USER_ID order by a.user_id asc ";
    /**
	* ...String DISTRICT_DETAILS documentation comment
	* for retrieving District values from IGRS_DISTRICT_MASTER...
	*/
    public static final String DISTRICT_DETAILS = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER";
    /**
	* ...String OFFICE_TYPE_DETAILS documentation comment
	* for retrieving Office Types from IGRS_OFFICE_MASTER...
	*/
    public static final String OFFICE_TYPE_DETAILS = "SELECT OFFICE_TYPE_ID,OFFICE_TYPE_NAME FROM IGRS_OFFICE_TYPE_MASTER WHERE ACTIVE_FLAG = 'A' ";
    /**
	* ...String OFFICE_NAME_DETAILS documentation comment
	* for retrieving Office Names from IGRS_OFFICE_MASTER...
	*/
    public static final String OFFICE_NAME_DETAILS = "SELECT OFFICE_ID,OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_TYPE_ID =";
    /**
	* ...String INSERT_SERVICE_FEE_PARAM documentation comment
	* for inserting values into IGRS_SERVICE_FEE_PARAM_MASTER...
	*/
    public static final String INSERT_SERVICE_FEE_PARAM = "INSERT INTO IGRS_SERVICE_FEE_PARAM_MASTER(PARAM_ID,PARAM_NAME,PARAM_STATUS,CREATED_BY,CREATED_DATE,PARAM_DESCRIPTION) VALUES(?,?,?,?,SYSDATE,?)";

   public static final String PARAM_ID_GENERATION = "select IGRS_SERVICE_PARAM_MASTER_SEQ.NEXTVAL from dual";
    /**
	* ...String VIEW_SERVICE_FEE_DETAILS documentation comment
	* for retrieving values from IGRS_SERVICE_FEE_PARAM_MASTER...
	*/
    public static final String VIEW_SERVICE_FEE_DETAILS = "SELECT PARAM_ID,PARAM_NAME,PARAM_DESCRIPTION,PARAM_STATUS FROM IGRS_SERVICE_FEE_PARAM_MASTER" ;
    /**
	* ...String UPDATE_SERVICE_FEE_DETAILS documentation comment
	* for updating values from IGRS_SERVICE_FEE_PARAM_MASTER...
	*/
    public static final String UPDATE_SERVICE_FEE_DETAILS = "UPDATE IGRS_SERVICE_FEE_PARAM_MASTER SET PARAM_NAME = ?,PARAM_DESCRIPTION = ?,PARAM_STATUS = ? WHERE PARAM_ID = ? ";

    public static final String VIEW_SERVICE_PARAM="SELECT PARAM_ID, PARAM_NAME, "
			+ " PARAM_DESCRIPTION, PARAM_STATUS,CREATED_BY FROM IGRS_SERVICE_FEE_PARAM_MASTER WHERE PARAM_STATUS <> 'R'";
    
    public static final String DELETE_SERVICE_MASTER="update IGRS_SERVICE_FEE_PARAM_MASTER set PARAM_STATUS='R' where PARAM_ID=?";
    
    public static final String GET_SERVICE_PROVIDER_DETAILS_1="SELECT MAX(A.GROSS_AMOUNT)CREDITEDAMOUNT,MAX(TO_CHAR(a.created_date,'dd/mm/yyyy'))CREDITEDDATE," +
    		"MAX(D.PAYMENT_TYPE_NAME)PAYMENTTYPE, MAX(A.TRANSACTION_ID),MAX(B.SP_ACCT_BALANCE)BALANCEAMOUNT FROM IGRS_PAYMENT_MODE_DETAILS A, " +
    		"IGRS_SP_USER_ACCT_BAL_DETAILS B, IGRS_SP_USER_LICENSE_DETAILS C, " +
    		"IGRS_PAYMENT_TYPE_MASTER D WHERE A.REG_USER_ID= C.SP_USER_ID AND B.SP_LICENCE_TXN_ID = C.LICENSE_TXN_ID AND " +
    		"A.PAYMENT_TYPE_ID=D.PAYMENT_TYPE_ID AND A.REG_USER_ID=? GROUP BY A.REG_USER_ID,A.CREATED_DATE";
    
    public static final String GET_SERVICE_PROVIDER_DETAILS_2="SELECT (A.GROSS_AMOUNT)DEBITAMOUNT,TO_CHAR(a.created_date,'dd/mm/yyyy')DEBITDATE," +
    		"(B.FUNCTION_NAME) PURPOSE ,(A.TRANSACTION_ID) RECIPTNO FROM IGRS_PAYMENT_MODE_DETAILS A, IGRS_FUNCTION_MASTER B WHERE " +
    		"REG_USER_ID=? AND A.FUNCTION_ID=B.FUNCTION_ID ORDER BY A.CREATED_DATE";
}
