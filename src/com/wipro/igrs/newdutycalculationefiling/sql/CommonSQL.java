/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* 
*==============================================================================
*
* File Name   :  CommonSQL.java 
 * Author      :  Roopam Mehta 
 * Description :   
*/


/**
 * File Name: CommonSQL.java
 * @author Roopam Mehta
 * Created on 26 Feb 2014
 *
 */
package com.wipro.igrs.newdutycalculationefiling.sql;


/**
 * 
 * @author Vinay Sharma
 *
 */
public class CommonSQL {

	public static final String GET_PROP_VAL_FLAG = "SELECT PROP_VAL_REQ_FLAG FROM IGRS_DEED_INSTRUMENT_MASTER WHERE INSTRUMENT_ID=?";
	
	public static final String GET_MARKET_VALUE_EN = "SELECT P.FINAL_MV,N.PROPERTY_TYPE_NAME,P.PROP_TYPE_ID,P.DIST_ID,P.SINGLE_BUYER,P.PROPERTY_TYPE_L1_ID,P.COLONY_ID FROM IGRS_PROP_VAL_TXN_DETLS P,IGRS_PROPERTY_TYPE_MASTER N WHERE P.VAL_TXN_ID=? AND P.PROP_TYPE_ID=N.PROPERTY_TYPE_ID";
	
	public static final String GET_MARKET_VALUE_HI = "SELECT P.FINAL_MV,N.H_PROPERTY_TYPE_NAME,P.PROP_TYPE_ID,P.DIST_ID,P.SINGLE_BUYER,P.PROPERTY_TYPE_L1_ID,P.COLONY_ID FROM IGRS_PROP_VAL_TXN_DETLS P,IGRS_PROPERTY_TYPE_MASTER N WHERE P.VAL_TXN_ID=? AND P.PROP_TYPE_ID=N.PROPERTY_TYPE_ID";

	public static final String GET_VALUATION_ID_AGI = "SELECT VAL_TXN_ID FROM IGRS_PROP_VAL_TXN_DETLS WHERE VAL_TXN_ID=?";

	public static final String GET_VALUATION_ID_PLOT_BUILD = "SELECT VAL_TXN_ID FROM IGRS_PROP_VAL_TXN_DETLS WHERE VAL_TXN_ID=?";

	public static final String GET_USER_ENTERABLe_FIELD_EN="SELECT INST_FIELD_MAP_ID,FIELD_NAME,AS_CONSIDERATION,IN_STAMP_DUTY_CALC,MAPPING_STATUS,REF_FEE_ALREADY_PAID,DUTY_ALREADY_PAID FROM IGRS_INST_FIELD_MAPPING WHERE DEED_ID=? AND INSTRUMENT_ID=? and MAPPING_STATUS!='D' and EFILLING_FLAG ='Y' order by INST_FIELD_MAP_ID  ";
	
	public static final String GET_USER_ENTERABLe_FIELD_HI="SELECT INST_FIELD_MAP_ID,FIELD_NAME_H,AS_CONSIDERATION,IN_STAMP_DUTY_CALC,MAPPING_STATUS,REF_FEE_ALREADY_PAID,DUTY_ALREADY_PAID FROM IGRS_INST_FIELD_MAPPING WHERE DEED_ID=? AND INSTRUMENT_ID=? and MAPPING_STATUS!='D' order by INST_FIELD_MAP_ID";

	public static final String GET_STAMP_DUTY_DETAILS="SELECT INST_OPERATION_MAP_ID,OPERATION_ID,OPERATION_VALUE,UNIT_FRACTION_VALUE,MAX_VALUE,MIN_VALUE FROM IGRS_STAMP_DUTY_FORMULA_MASTER WHERE DEED_ID=? AND INSTRUMENT_ID=? AND STATUS_FLAG='A'";
	
	public static final String GET_REG_FEE_DETAILS="SELECT INST_OPERATION_MAP_ID,OPERATION_ID,OPERATION_VALUE,MAX_VALUE,MIN_VALUE FROM IGRS_REG_FEE_FORMULA_MASTER WHERE DEED_ID=? AND INSTRUMENT_ID=? AND STATUS_FLAG='A'";

	public static final String GET_PROPERTY_NAME_EN="SELECT PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_ID=?";

	public static final String GET_PROPERTY_NAME_HI="SELECT H_PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_ID=?";

	public static final String GET_UPKAR_FLAG="SELECT UPKAR_FLAG FROM IGRS_DEED_INSTRUMENT_MASTER WHERE INSTRUMENT_ID=?";
	
	public static final String GET_UPKAR_VALUE="SELECT ATTRIBUTE_VALUE FROM IGRS_CONFIG_PARAM_LIST WHERE ATTRIBUTE_ID='ATT_167'";
	
	public static final String GET_JANPAD_DETAILS="SELECT S.OPERATION_ID,J.NAGAR_NIGAM,J.NAGAR_PALIKA,J.CANTONMENT_NN,J.PLANNING_NN,J.JANPAD FROM IGRS_MUN_BLOCK_FORMULA_MASTER J,IGRS_STAMP_DUTY_FORMULA_MASTER S WHERE S.DEED_ID=J.DEED_ID AND S.INSTRUMENT_ID=J.INSTRUMENT_ID AND J.DEED_ID=? AND J.INSTRUMENT_ID=?   ";

	public static final String GET_MUNICIPAL_ID="SELECT P.SUB_AREA_TYPE_ID,P.IS_MUNICIPAL,(SELECT MUNICIPAL_BODY_ID FROM IGRS_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_ID=S.MUNICIPAL_BODY_ID) FROM IGRS_PROP_VAL_TXN_DETLS P,IGRS_SUB_AREA_TYPE_MASTER S WHERE P.SUB_AREA_TYPE_ID=S.SUB_AREA_TYPE_ID AND P.VAL_TXN_ID=?";

	public static final String GET_MAIN_DUTY_ID="SELECT IGRS_MAIN_DUTY_TXN_SEQ.NEXTVAL FROM DUAL";
	public static final String GET_PROP_DUTY_ID="SELECT IGRS_PROP_DUTY_SEQ .NEXTVAL FROM DUAL";
	
	//physical stamp code Sqeuence
	public static final String GET_PHYSICAL_ESTAMP_SEQ="SELECT IGRS_PHYSICAL_STAMP_TXN_SEQ.NEXTVAL FROM DUAL";
	
	public static final String INSERT_DUTY_MAIN="INSERT INTO IGRS_DUTY_TXN_DETLS_EFILING(DUTY_TXN_ID,STAMP_DUTY,MUNICIPAL_DUTY,BLOCK_DUTY,UPKAR_DUTY,REG_FEE,TOTAL,DEED_ID,INSTRUMENT_ID,REG_FEE_PAID,DUTY_ALREADY_PAID,CREATED_BY,CREATED_DATE,CANCELLATION_FLAG,CANCELLATION_INST_ID,REF_MARKET_VALUE,TOTAL_AFTER_EXEMP,REG_FEE_AFTER_EXEMP,EXEMP_STAMP,EXEMP_REG,FAMILY_FLAG,GOV_FAVOUR,GOV_VALUE,TYPE_OF_TRANSACTION,EWS_APPLIED,DATE_OF_EXECUTION,EXEMPTION_FLAG,DATE_OF_EFILING) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE) ";
	
	//bank details insert
	//public static final String GET_MAIN_BANK_ID="SELECT IGRS_BANK_TXN_SEQ.NEXTVAL FROM DUAL";
	public static final String INSERT_BANK_DTLS="INSERT INTO IGRS_DUTY_TXN_DETLS_EFILING(DUTY_TXN_ID,BANK_NAME,ADDRESS,COUNTRY,STATE,DISTRICT,POSTAL_CODE,PHONE_NUMBER,BANK_AUTHORITY_NAME,BANK_AUTHORITY_DESIGNATION,BANK_AUTHORITY_PHONE_NUMBER,BANK_AUTHORITY_EMAIL_ID,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE) ";
	
	public static final String INSERT_BANK_INFO="INSERT INTO IGRS_EFILING_BANK_DETAILS (DUTY_TXN_ID,BANK_NAME,ADDRESS,COUNTRY,STATE,DISTRICT,POSTAL_CODE,PHONE_NUMBER,BANK_AUTHORITY_NAME,BANK_AUTHORITY_DESIGNATION,BANK_AUTHORITY_PHONE_NUMBER,BANK_AUTHORITY_EMAIL_ID,EFILE_TEMP_ID,CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
	//insert final
	
	public static final String INSERT_FINAL_DTLS="INSERT INTO IGRS_EFILE_FINAL_DTLS(EFILE_REFERNCE,EXC_DATE,EFILE_DATE,STATUS,PAYMENT_STATUS,DATE_OF_SUBMISSION_PO,CREATED_DATE,PO_LOGIN_ID) VALUES(?,?,?,?,?,SYSDATE,SYSDATE,?) ";
	
	public static final String UPDATE_FINAL_DTLS="UPDATE IGRS_EFILE_FINAL_DTLS SET EFILE_TXN_ID=?,SUB_REGISTRAR_OFFICE=?,SR_OFFICE_ID=?,SR_NAME=?,SR_LOGIN_ID=? where EFILE_REFERNCE=?";
	//DB save
	//public static final String INSERT_FIELD_VALUE="INSERT INTO IGRS_PROP_DUTY_FIELD_DETLS(DUTY_FIELD_ID,DUTY_TXN_ID,PROP_DUTY_ID,INST_FIELD_MAP_ID,FIELD_VALUE,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_FIELD_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE)";
	//end
	public static final String INSERT_FIELD_VALUE="INSERT INTO IGRS_PROP_DUTY_FIELD_DETLS_EF(DUTY_FIELD_ID,DUTY_TXN_ID,PROP_DUTY_ID,INST_FIELD_MAP_ID,FIELD_VALUE,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_FIELD_SEQ.NEXTVAL,?,?,?,?,?,SYSDATE)";

	public static final String INSERT_PROP_DUTY_DETAILS="INSERT INTO IGRS_PROP_DUTY_DETLS_EFILING(PROP_DUTY_ID,STAMP_DUTY,MUNICIPAL_DUTY,BLOCK_DUTY,UPKAR_DUTY,TOTAL,REG_FEE,REG_ALREADY_PAID,DUTY_ALREADY_PAID,CREATED_BY,CREATED_DATE,VAL_TXN_ID,DUTY_TXN_ID,MARKET_VALUE,PARTY_TYPE,PAYABLE_DUTY,PAYABLE_REG,GOV_VALUE) VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?) ";

	public static final String GET_VALUATION_ID_AGRI = "SELECT VAL_TXN_ID FROM IGRS_PROP_VAL_TXN_DETLS WHERE REF_VAL_TXN_ID=?";
	
	public static final String GET_MUNICIPAL_CHECK_BOX = "SELECT MUNICIPAL_FLAG_NPV FROM IGRS_DEED_INSTRUMENT_MASTER WHERE INSTRUMENT_ID=?";
	
	public static final String GET_FAMILY_CHECK_BOX = "SELECT FAVOUR_FAMILY FROM IGRS_DEED_INSTRUMENT_MASTER WHERE INSTRUMENT_ID=?";

	public static final String GET_EXEMPTIONS="SELECT E.EXEMPTION_ID,E.EXEMPTION_NAME,E.EXEMPTION_DESC,E.APPLICABLE_ON,E.EXEMPTED_PERCENT FROM IGRS_EXEMPTION_MASTER E,IGRS_INST_EXEMPTION_MAPPING M WHERE M.EXEMPTION_ID=E.EXEMPTION_ID AND M.DEED_ID=? AND M.INSTRUMENT_ID=? AND M.MAPPING_STATUS='A' AND E.EXEMPTION_STATUS='A' AND E.APPLICABLE_ON='E' AND E.EFILING_FLAG='Y' ";

	public static final String GET_EXEMPTIONS_H="SELECT E.EXEMPTION_ID,E.H_EXEMPTION_NAME,E.H_EXEMPTION_DESC,E.APPLICABLE_ON,E.EXEMPTED_PERCENT FROM IGRS_EXEMPTION_MASTER E,IGRS_INST_EXEMPTION_MAPPING M WHERE M.EXEMPTION_ID=E.EXEMPTION_ID AND M.DEED_ID=? AND M.INSTRUMENT_ID=? AND M.MAPPING_STATUS='A' AND E.EXEMPTION_STATUS='A' AND E.APPLICABLE_ON='E' AND E.EFILING_FLAG='Y'";

	public static final String GET_EXEMPTIONS_REG="SELECT E.EXEMPTION_ID,E.EXEMPTION_NAME,E.EXEMPTION_DESC,E.APPLICABLE_ON,E.EXEMPTED_PERCENT FROM IGRS_EXEMPTION_MASTER E,IGRS_INST_EXEMPTION_MAPPING M WHERE M.EXEMPTION_ID=E.EXEMPTION_ID AND M.DEED_ID=? AND M.INSTRUMENT_ID=? AND M.MAPPING_STATUS='A' AND E.EXEMPTION_STATUS='A' AND E.APPLICABLE_ON='F' AND E.EFILING_FLAG='Y'";

	public static final String GET_EXEMPTIONS_REG_H="SELECT E.EXEMPTION_ID,E.H_EXEMPTION_NAME,E.H_EXEMPTION_DESC,E.APPLICABLE_ON,E.EXEMPTED_PERCENT FROM IGRS_EXEMPTION_MASTER E,IGRS_INST_EXEMPTION_MAPPING M WHERE M.EXEMPTION_ID=E.EXEMPTION_ID AND M.DEED_ID=? AND M.INSTRUMENT_ID=? AND M.MAPPING_STATUS='A' AND E.EXEMPTION_STATUS='A' AND E.APPLICABLE_ON='F' AND E.EFILING_FLAG='Y'";

	
	public static final String GET_DEED_DISCRIPTION="SELECT DEED_TYPE_DESCRIPTION FROM IGRS_DEED_TYPE_MASTER WHERE DEED_TYPE_ID=?";
	
	public static final String GET_DEED_DISCRIPTION_H="SELECT H_DEED_TYPE_DISC FROM IGRS_DEED_TYPE_MASTER WHERE DEED_TYPE_ID=?";

	
	//below query is used for instrument desc
	public static final String GET_INST_DISCRIPTION="SELECT INSTRUMENT_DESCRIPTION FROM IGRS_DEED_INSTRUMENT_MASTER WHERE INSTRUMENT_STATUS ='A'AND EFILING_FLAG='Y' AND INSTRUMENT_ID=?";
	
	public static final String GET_INST_DISCRIPTION_H="SELECT H_INSTRUMENT_DESCRIPTION FROM IGRS_DEED_INSTRUMENT_MASTER WHERE  INSTRUMENT_STATUS ='A' AND EFILING_FLAG='Y' INSTRUMENT_ID=?";

	//end of query is used for instrument desc
	
	public static final String DEED_CATEGORY="SELECT DEED_CATEGORY_ID,DEED_CATEGORY_NAME FROM IGRS_DEED_CATEGORY_MASTER WHERE STATUS='A' ";
		
	public static final String DEED_CATEGORY_HI="SELECT DEED_CATEGORY_ID,DEED_CATEGORY_NAME_H FROM IGRS_DEED_CATEGORY_MASTER WHERE STATUS='A'";
	
	public static final String CANCELATION_DEED_LIST="SELECT DEED_TYPE_ID,DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_STATUS='A' AND DEED_CATEGORY_ID='5'";
	public static final String CANCELATION_DEED_LIST_HI="SELECT DEED_TYPE_ID,H_DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_STATUS='A' AND DEED_CATEGORY_ID='5'";
	
	
	//below query is used for deed type drop down
	public static final String DEED_QUERY_DC_COMMON = "SELECT DEED_TYPE_ID, "
		+"DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
		+"WHERE DEED_STATUS IN ('A','D') AND EFILING_FLAG='Y' AND DEED_CATEGORY_ID=? ORDER BY DEED_TYPE_NAME ASC";

	
	public static final String DEED_QUERY_DC_COMMON_HI = "SELECT DEED_TYPE_ID, "
		+"H_DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
		+"WHERE DEED_STATUS IN ('A','D') AND EFILING_FLAG='Y' AND DEED_CATEGORY_ID=? ORDER BY DEED_TYPE_NAME ASC";
	//end of deed type drop down
	
	public static final String DEED_QUERY_DC_COMMON_CANCEL="SELECT DISTINCT D.DEED_TYPE_ID,D.DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER D,IGRS_DEED_INSTRUMENT_MASTER E WHERE D.DEED_STATUS   ='A' AND D.DEED_CATEGORY_ID=? AND D.DEED_TYPE_ID=E.DEED_TYPE_ID AND E.CANCEL_DEED_APP_FLAG='Y' ORDER BY DEED_TYPE_NAME ASC";
	
	public static final String DEED_QUERY_DC_COMMON_CANCEL_HI="SELECT DISTINCT D.DEED_TYPE_ID,D.H_DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER D,IGRS_DEED_INSTRUMENT_MASTER E WHERE D.DEED_STATUS   ='A' AND D.DEED_CATEGORY_ID=? AND D.DEED_TYPE_ID=E.DEED_TYPE_ID AND E.CANCEL_DEED_APP_FLAG='Y' ORDER BY H_DEED_TYPE_NAME ASC";


	//below query is used for category drop down
	public static final String DEED_QUERY_DEEDCATEGORY_COMMON = "SELECT DEED_CATEGORY_ID, "
		+"DEED_CATEGORY_NAME FROM IGRS_DEED_CATEGORY_MASTER "
		+"WHERE STATUS_EFILE='A' AND EFILING_FLAG='Y' ORDER BY DEED_CATEGORY_NAME ASC";
	
	public static final String DEED_QUERY_DEEDCATEGORY_COMMON_HINDI = "SELECT DEED_CATEGORY_ID, "
		+"DEED_CATEGORY_NAME_H FROM IGRS_DEED_CATEGORY_MASTER "
		+"WHERE STATUS_EFILE='A' AND EFILING_FLAG='Y' ORDER BY DEED_CATEGORY_NAME ASC";
	//end of category drop down
	
	
	public static final String DEED_QUERY_PROPERTY = "SELECT DEED_TYPE_ID, "
		+"DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
		+"WHERE DEED_STATUS='A' AND PROPERTY_RELATED_DEED='Y'  ORDER BY DEED_TYPE_NAME ASC";
	
	/**
	 * 	/**
	 * @author RISHAB
	 */
	public static final String DEED_QUERY_REGISTRATION = "SELECT DEED_TYPE_ID, "
		+"DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
		+"WHERE DEED_STATUS='A' AND PROPERTY_RELATED_DEED='Y' AND ROR_REQUIRED='R' ORDER BY DEED_TYPE_NAME ASC";
	
	//below query is used for instruement radio
	public static final String INSTRUMENTS_QUERY_HINDI = "SELECT "
		+ "INSTRUMENT_ID,"
		+ "INSTRUMENT_NAME,Label_Amount,Label_Value_Required,H_LABEL_AMOUNT,H_INSTRUMENT_NAME "
		+ "FROM IGRS_DEED_INSTRUMENT_MASTER "
		+ "WHERE DEED_TYPE_ID=?  AND "
		+ "EFILING_FLAG='Y' AND "
		+ "INSTRUMENT_STATUS IN ('A','D')  ORDER BY "
		+ "INSTRUMENT_ID ASC";
	public static final String INSTRUMENTS_QUERY_HINDI_CANCEL = "SELECT "
		+ "INSTRUMENT_ID,"
		+ "INSTRUMENT_NAME,Label_Amount,Label_Value_Required,H_LABEL_AMOUNT,H_INSTRUMENT_NAME "
		+ "FROM IGRS_DEED_INSTRUMENT_MASTER "
		+ "WHERE DEED_TYPE_ID=? AND "
		+ "EFILING_FLAG='Y' AND "
		+ "INSTRUMENT_STATUS IN ('A','D') AND CANCEL_DEED_APP_FLAG='Y' ORDER BY "
		+ "INSTRUMENT_ID ASC";
	
	//end of query used for instruement radio
	
	public static final String CANCEL_STAMP_VALUE="SELECT CANCEL_STAMP_VALUE FROM IGRS_DEED_INSTRUMENT_MASTER WHERE DEED_TYPE_ID =? AND INSTRUMENT_ID=?";
	public static final String CANCEL_REG_VALUE="SELECT CANCEL_REG_FEE_VALUE FROM IGRS_DEED_INSTRUMENT_MASTER WHERE DEED_TYPE_ID =? AND INSTRUMENT_ID=?";

	public static final String AGRI_AREA="SELECT TOTAL_AREA FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";
	public static final String GET_MIN_STAMP_DUTY="SELECT MIN_VALUE FROM IGRS_STAMP_DUTY_FORMULA_MASTER WHERE DEED_ID=? AND INSTRUMENT_ID=? AND STATUS_FLAG='A'";
	public static final String GET_MIN_REG_FEE="SELECT MIN_VALUE FROM IGRS_REG_FEE_FORMULA_MASTER WHERE DEED_ID=? AND INSTRUMENT_ID=? AND STATUS_FLAG='A'";
	
	
	//change for db save
	//public static final String INSERT_DUTY_PREMIUM_DETAILS="INSERT INTO IGRS_DUTY_RENT_DETLS(RENT_ID,DUTY_TXN_ID,PROP_VAL_ID,DEVELOPMENT_CHARGES,ADITTIONAL,PERMIUM,OTHER_CHARGES,RENT_PREMIUM_FLAG,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_RENT_SEQ.NEXTVAL,?,?,?,?,?,?,'P',?,SYSDATE)";
	//public static final String INSERT_DUTY_RENT_DETAILS="INSERT INTO IGRS_DUTY_RENT_DETLS(RENT_ID,DUTY_TXN_ID,PROP_VAL_ID,MONTHLY_RENT,MAINTENCE_CHARGES,YEAR,MONTH,RENT_PREMIUM_FLAG,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_RENT_SEQ.NEXTVAL,?,?,?,?,?,?,'R',?,SYSDATE)";
	//end 
	
	
	public static final String INSERT_DUTY_PREMIUM_DETAILS="INSERT INTO IGRS_DUTY_RENT_DETLS_EFILING(RENT_ID,DUTY_TXN_ID,PROP_VAL_ID,DEVELOPMENT_CHARGES,ADITTIONAL,PERMIUM,OTHER_CHARGES,RENT_PREMIUM_FLAG,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_RENT_SEQ.NEXTVAL,?,?,?,?,?,?,'P',?,SYSDATE)";
	public static final String INSERT_DUTY_RENT_DETAILS="INSERT INTO IGRS_DUTY_RENT_DETLS_EFILING(RENT_ID,DUTY_TXN_ID,PROP_VAL_ID,MONTHLY_RENT,MAINTENCE_CHARGES,YEAR,MONTH,RENT_PREMIUM_FLAG,CREATED_BY,CREATED_DATE) VALUES(IGRS_DUTY_RENT_SEQ.NEXTVAL,?,?,?,?,?,?,'R',?,SYSDATE)";
	public static final String   INSERT_EXEMPTION_DETAILS="INSERT INTO IGRS_DUTY_EXEMPTION_DETLS_EF(EXEMPTION_TXN_ID,DUTY_TXN_ID,EXEMPTION_ID,CREATED_BY,CREATED_DATE) VALUES(IGRS_EXEMPTION_SEQ.NEXTVAL,?,?,?,SYSDATE)";
	public static final String DEED_QUERY_DC_ESTAMP="SELECT DISTINCT D.DEED_TYPE_ID,D.DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER D,IGRS_DEED_INSTRUMENT_MASTER E WHERE D.DEED_STATUS   ='A' AND D.DEED_CATEGORY_ID=? AND D.DEED_TYPE_ID=E.DEED_TYPE_ID AND E.INSTRUMENT_TYPE='O' and E.INSTRUMENT_STATUS='A' ORDER BY DEED_TYPE_NAME ASC";
	public static final String DEED_QUERY_DC_ESTAMP_HI="SELECT DISTINCT D.DEED_TYPE_ID,D.H_DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER D,IGRS_DEED_INSTRUMENT_MASTER E WHERE D.DEED_STATUS   ='A' AND D.DEED_CATEGORY_ID=? AND D.DEED_TYPE_ID=E.DEED_TYPE_ID AND E.INSTRUMENT_TYPE='O' and E.INSTRUMENT_STATUS='A' ORDER BY D.H_DEED_TYPE_NAME ASC";
	public static final String INSTRUMENTS_QUERY_HINDI_ESTAMP = "SELECT "
		+ "INSTRUMENT_ID,"
		+ "INSTRUMENT_NAME,Label_Amount,Label_Value_Required,H_LABEL_AMOUNT,H_INSTRUMENT_NAME "
		+ "FROM IGRS_DEED_INSTRUMENT_MASTER "
		+ "WHERE DEED_TYPE_ID=? AND "
		+ "INSTRUMENT_STATUS='A' AND INSTRUMENT_TYPE='O' ORDER BY "
		+ "INSTRUMENT_ID ASC";
	public static final String INSTRUMENTS_QUERY_ESTAMP = "SELECT "
		+ "INSTRUMENT_ID,"
		+ "INSTRUMENT_NAME,Label_Amount,Label_Value_Required,H_LABEL_AMOUNT,H_INSTRUMENT_NAME "
		+ "FROM IGRS_DEED_INSTRUMENT_MASTER "
		+ "WHERE DEED_TYPE_ID=? AND "
		+ "INSTRUMENT_STATUS='A' AND INSTRUMENT_TYPE='O' ORDER BY "
		+ "INSTRUMENT_ID ASC";
	public static final String CURRENT_YEAR = "select to_char(sysdate,'yyyy') from dual";
	public static final String CURRENT_DATE = "select to_char(sysdate,'dd/mm/yyyy') from dual";
	public static final String DEED_INSTRUMENT_REG="SELECT R.DEED_ID,R.INSTRUMENTS_ID,R.CANCELLATION_FLAG,D.FAMILY_FLAG,D.DUTY_TXN_ID,D.MUNICIPAL_DUTY FROM IGRS_REG_TXN_DETLS R,IGRS_DUTY_TXN_DETLS_EFILING D WHERE REG_TXN_ID=? AND D.DUTY_TXN_ID=R.DUTY_TXN_ID";
	public static final String CONSNTER_DETAILS_DUTY="SELECT CONSENTER_TXN_NUM,WITH_CONSIDERATION,CONSIDERATION_AMOUNT FROM IGRS_REG_CONSENTER_DETAILS WHERE REG_TXN_ID=?";
	public static final String CONSENTER_FIXED_DUTIES="SELECT STAMP,JANPAD,MUNICIPAL,UPKAR,REG_FEE FROM IGRS_CONST_DUTY_MASTER WHERE INSTRUMENT_ID=?"; 
	public static final String	CONS_EXEMPTION ="SELECT E.EXEMPTED_PERCENT,E.EXEMPTION_ID FROM IGRS_EXEMPTION_MASTER E,IGRS_DUTY_EXEMPTION_DETLS_EF D WHERE E.EXEMPTION_ID=D.EXEMPTION_ID AND E.APPLICABLE_ON=? AND D.DUTY_TXN_ID=? "; 
	public static final String	GET_APPLY_EWS ="SELECT EWS_APPLIED FROM IGRS_DUTY_TXN_DETLS_EFILING WHERE DUTY_TXN_ID=? ";
	public static final String CONS_DUTY_UPDATE="UPDATE IGRS_DUTY_TXN_DETLS_EFILING SET STAMP_DUTY=STAMP_DUTY+?,BLOCK_DUTY=BLOCK_DUTY+?,MUNICIPAL_DUTY=MUNICIPAL_DUTY+?,UPKAR_DUTY=UPKAR_DUTY+?,TOTAL=TOTAL+?,TOTAL_AFTER_EXEMP=TOTAL_AFTER_EXEMP+?,REG_FEE=REG_FEE+?,REG_FEE_AFTER_EXEMP=REG_FEE_AFTER_EXEMP+?,EXEMP_STAMP=EXEMP_STAMP+?,EXEMP_REG=EXEMP_REG+? where DUTY_TXN_ID=? ";
	public static final String CONS_DUTY_INSERT="INSERT INTO IGRS_CONSENTER_DUTY_DETAILS (CON_DUTY_TXN_ID,STAMP_DUTY,JANPAD_DUTY,MUNICIPAL_DUTY,UPKAR_DUTY,TOTAL,REG_FEE,TOTAL_AFTER_EXEMP,REG_FEE_AFTER_EXEMP,EXEMPTED_DUTY,EXEMPTED_REG_FEE,CONSENTER_TXN_NUM,REG_TXN_ID) VALUES(IGRS_CONSENTER_DUTY_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String GET_APP_SUBCLAUSE = "select NVL(APPLICABLE_SUBCLAUSE_ID,'') from IGRS_COLONY_MASTER WHERE COLONY_ID=?";
	public static final String GET_PROP_TYPE = "select PROP_TYPE_ID from IGRS_PROP_VAL_TXN_DETLS WHERE VAL_TXN_ID=?";
	
	public static final String GET_PROP_TYPE_FURTHER_DETLS = "select BUILDING_SUB_TYPE_ID,FLOOR_AREA,MULTISTOREY_SUB_TYPE,BUILD_UP_AREA,IS_HOUSING_BOARD from IGRS_PROP_BUILDING_DETLS WHERE VAL_TXN_ID=?";
	public static final String GET_PROP_TYPE_FURTHER_FLOOR_DETLS="SELECT (nvl(RCC_AREA,0)+nvl(RBC_AREA,0)+nvl(TIN_AREA,0)+nvl(KACHA_AREA,0)) "+
																 "FROM IGRS_FLOOR_AREA_DETLS, "+
																 "IGRS_FLOOR_TXN_DETLS, "+
																 "IGRS_PROP_BUILDING_DETLS "+
															     "WHERE IGRS_FLOOR_TXN_DETLS.FLOOR_TXN_ID=IGRS_FLOOR_AREA_DETLS.FLOOR_TXN_ID "+
																 "AND IGRS_FLOOR_TXN_DETLS.FLOOR_TYPE_ID='3' "+ //ground floor
																 "AND IGRS_FLOOR_AREA_DETLS.SUB_PROP_TYPE_ID='7' "+  //residential
																 "AND IGRS_PROP_BUILDING_DETLS.BUILDING_TXN_ID=IGRS_FLOOR_TXN_DETLS.BUILDING_TXN_ID "+
																 "AND IGRS_PROP_BUILDING_DETLS.VAL_TXN_ID=?";
	
	public static final String GET_RESI_FLOOR_COUNT="SELECT count(FLOOR_AREA_TXN_ID) "+
	 												"FROM IGRS_FLOOR_AREA_DETLS, "+
	 												"IGRS_FLOOR_TXN_DETLS, "+
	 												"IGRS_PROP_BUILDING_DETLS "+
	 												"WHERE IGRS_FLOOR_TXN_DETLS.FLOOR_TXN_ID=IGRS_FLOOR_AREA_DETLS.FLOOR_TXN_ID "+
	 												"AND IGRS_FLOOR_TXN_DETLS.FLOOR_TYPE_ID='3' "+ //ground floor
	 												"AND IGRS_PROP_BUILDING_DETLS.BUILDING_TXN_ID=IGRS_FLOOR_TXN_DETLS.BUILDING_TXN_ID "+
	 												"AND IGRS_PROP_BUILDING_DETLS.VAL_TXN_ID=?";


//changes by preeti for estamp code
	
	public static final String GET_ESTAMP="select ESTAMP_CODE from igrs_estamp_detls  where CONSMPTN_STATUS='Not Consumed' and estamp_status='A' and ESTAMP_CODE=?";

	public static final String 	GET_ESTAMP_DEACTIVATED="select estamp_status from igrs_estamp_detls where ESTAMP_CODE=? ";
	
	public static final String 	GET_ESTAMP_TYPE="select ESTAMP_TYPE_ID from igrs_estamp_detls where ESTAMP_CODE=? ";
	
	public static final String	GET_ESTAMP_TYPE_ACTIVATE="select ESTAMP_STATUS from igrs_estamp_detls where ESTAMP_CODE=?";
	
	public static final String	GET_ESTAMP_SOURCE_TYPE="select SOURCE_MOD_FLAG from igrs_estamp_detls where ESTAMP_CODE=? ";
	
	//Changes for physical estamp
	public static final String	GET_PHYSICAL_ESTAMP="INSERT INTO IGRS_PHYSICAL_STAMP_DETLS (PHYSICAL_STAMP_TXN_ID,STAMP_VENDOR_NAME,CODE_OF_STAMP,SERIES_NUMBER,DATE_OF_STAMP,EFILING_DUTY_ID,DEED_ID,INST_ID,EFILING_TEMP_ID,PHYSICAL_FLAG,CREATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,SYSDATE) ";
	public static final String ADD_MORE_PHYSICAL_ESTAMP="INSERT INTO IGRS_PHYSICAL_STAMP_DETLS (PHYSICAL_STAMP_TXN_ID,STAMP_VENDOR_NAME,CODE_OF_STAMP,SERIES_NUMBER,DATE_OF_STAMP,DEED_ID,INST_ID,EFILING_TEMP_ID,PHYSICAL_FLAG,CREATED_DATE) VALUES (?,?,?,?,?,?,?,?,?,SYSDATE)";
	public static final String GET_ESTAMP_UPDATE="UPDATE igrs_estamp_detls SET CONSMPTN_STATUS='Consumed' ,ESTAMP_STATUS='D',EFILING_FLAG='Y',EFILING_DUTY_ID=?,EFILING_TEMP_ID=? where ESTAMP_CODE=?";
	//end of changes
	public static final String	GET_STATUS_UPDATE="INSERT INTO IGRS_REG_TXN_DETLS_EFILING(REG_TXN_ID,REGISTRATION_TXN_STATUS,DUTY_TXN_ID,DEED_ID,INSTRUMENTS_ID,CREATED_DATE,CREATED_BY) VALUES (?,?,?,?,?,SYSDATE,?) ";
	
	public static final String	GET_STATUS_UPDATE_BANK="UPDATE  IGRS_REG_TXN_DETLS_EFILING SET REGISTRATION_TXN_STATUS=? where REG_TXN_ID=?";
	
	
	public static final String		GET_INST_DASHBOARD="select INSTRUMENTS_ID from IGRS_REG_TXN_DETLS_EFILING where REG_TXN_ID=?";
	
	public static final String	GET_DEED_DASHBOARD="select DEED_ID from IGRS_REG_TXN_DETLS_EFILING where REG_TXN_ID=?";
	public static final String GET_UPDATE_LOAN="UPDATE igrs_estamp_detls SET EFILING_PURPOSE_LOAN=? where EFILING_DUTY_ID=? and EFILING_TEMP_ID=? ";
	
	public static final String	GET_UPDATE_LOAN_NON_PAYMENT="UPDATE igrs_duty_txn_detls_efiling set PURPOSE_LOAN_NON_PAYMENT=? where DUTY_TXN_ID=?";
	
	public static final String GET_UPDATE_LOAN_PHYSICAL="UPDATE igrs_physical_stamp_detls SET EFILING_PURPOSE_LOAN=? where EFILING_DUTY_ID=? and EFILING_TEMP_ID=? ";
	public static final String GET_PHYSICAL_FLAG=" select PHYSICAL_FLAG from igrs_physical_stamp_detls where EFILING_DUTY_ID=? and EFILING_TEMP_ID=? ";
	public static final String	GET_UPDATE_LOAN_PHYSICAL1="UPDATE igrs_duty_txn_detls_efiling set PURPOSE_LOAN_NON_PAYMENT=? where DUTY_TXN_ID=?";
	//efile
	
	 //efile seq
    
    public static final String GET_TODAY_APP_COUNT_efile="SELECT COUNT(REG_TXN_ID) FROM IGRS_REG_TXN_DETLS_EFILING WHERE TRUNC(CREATED_DATE)= TRUNC(SYSDATE)";
    public static final String DROP_REG_TXN_ID_SEQ_1_efile="DROP SEQUENCE IGRS_REG_TXN_ID_EF_SEQ";
    public static final String CREATE_REG_TXN_ID_SEQ_1_efile="CREATE SEQUENCE  IGRS_EFILE_FIRST_SEQ  MINVALUE 1 MAXVALUE 9999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  ORDER  NOCYCLE ;";
    public static final String GET_REG_TXN_ID_SEQ_efile="SELECT IGRS_EFILE_FIRST_SEQ.NEXTVAL FROM DUAL";
	
	//efile district
    
    public static final String GET_DISTRICT="select  max(DISTRICT_ID) from igrs_reg_txn_party_detls_ef where REG_TXN_ID=?";

//efile bank district
    public static final String DISTRICT="Select DISTRICT_NAME,DISTRICT_ID "
		+" from IGRS_DISTRICT_MASTER "
		+" Where STATE_ID=? AND DISTRICT_STATUS='A' "
		+"ORDER BY DISTRICT_NAME ASC";
    
    public static final String   DISTRICT_HINDI="select H_DISTRICT_NAME,DISTRICT_ID from igrs_district_master Where STATE_ID=? AND DISTRICT_STATUS='A' ORDER BY DISTRICT_NAME ASC";
    //efile
    
    public static final String UPDATE_FINAL_STATUS="UPDATE  IGRS_REG_TXN_DETLS_EFILING SET REGISTRATION_TXN_STATUS=? where REG_TXN_ID=?"; 
    public static final String UPDATE_FINAL_COUNT="UPDATE IGRS_USER_ROLE_GROUP_MAPPING ttt SET ttt.COUNT=(SELECT MIN(u.COUNT) +1 CNT FROM IGRS_USER_ROLE_GROUP_MAPPING u,IGRS_USER_REG_DETAILS urd,IGRS_RGROUP_ROLE_MAPPING rrm,IGRS_ROLE_OFFICE_MAPPING b,IGRS_ROLE_FN_ACTIVITY_MAPPING fm,igrs_activity_master im WHERE u.role_group_id      = rrm.role_group_id AND rrm.status             = 'A' AND rrm.role_office_map_id = b.role_office_map_id AND U.USER_ID              = URD.USER_ID AND U.USER_ID              =TTT.USER_ID AND u.role_group_id        = ttt.role_group_id AND URD.USER_STATUS        = 'A' AND b.status               = 'A' AND b.role_id              = fm.role_id AND im.activity_id         = 'ACT_1900'AND fm.activity_id         = im.activity_id) WHERE TTT.USER_ID     =? AND TTT.ROLE_GROUP_ID = ?"; 
    public static final String   GET_PROPERTY_TYPE="select REG_TXN_ID from igrs_reg_init_prop_trans_map_e where REG_TXN_ID=?";
    
    public static final String   UPDATE_UPLOAD_PATH="UPDATE IGRS_DUTY_TXN_DETLS_EFILING SET UPLOAD_PATH=? where DUTY_TXN_ID=? ";
    public static final String   UPDATE_UPLOAD_PATH1="UPDATE igrs_physical_stamp_detls SET UPLOAD_PATH=? where EFILING_DUTY_ID=? ";
    
    public static final String     UPDATE_UPLOAD_FORM_PATH="INSERT INTO IGRS_EFILE_UPLOADS_FORM(UPLOAD_TXN_ID,EFILE_TXN_ID,FILE_PATH,CREATED_DATE) VALUES (?,?,?,SYSDATE)";
    public static final String GET_MAIN_UPLOAD_ID="SELECT IGRS_MAIN_UPLOAD_TXN_SEQ.NEXTVAL FROM DUAL";
    public static final String   GET_DEED_ID="SELECT DEED_ID from IGRS_DUTY_TXN_DETLS_EFILING where DUTY_TXN_ID=?";

    public static final String    GET_VAL_ID="select VAL_TXN_ID from igrs_reg_proprty_dtls_ef where REG_TXN_ID=?";
    
    public static final String   GET_VAL_TEHSIL_ID="select TEHSIL_ID from igrs_prop_val_txn_detls where VAL_TXN_ID=?";
    
    public static final String    GET_INST_ID="SELECT INSTRUMENT_ID from IGRS_DUTY_TXN_DETLS_EFILING where DUTY_TXN_ID=?";

    public static final String    DISTRICT_LIST="select  DISTRICT_ID from igrs_reg_txn_party_detls_ef where REG_TXN_ID=?  order by PARTY_TXN_ID"; // Modified on 23rd Aug, 2017// By Gulrej
    
    public static final String District_Property = "select  DISTRICT_ID from IGRS_REG_PROPRTY_DTLS_EF where REG_TXN_ID=?";
    
  //  public static final String    GET_OFFICE_NAME_EFILE="select OFFICE_NAME from igrs_office_master where OFFICE_STATUS='A' and OFFICE_TYPE_ID='3' and  TEHSIL_ID=?";
   
    public static final String    GET_OFFICE_NAME_EFILE="select office_name,office_id from (SELECT DISTINCT D.USER_ID AS c2, e.office_name, maker.activity_name activity_name,e.office_id FROM igrs_reg_slot_master a,IGRS_EMP_MASTER EMP,IGRS_ROLE_OFFICE_MAPPING b,IGRS_RGROUP_ROLE_MAPPING c,IGRS_USER_ROLE_GROUP_MAPPING d,igrs_office_master e, igrs_district_master f,igrs_tehsil_master g,(SELECT role_id, activity_name FROM IGRS_ROLE_FN_ACTIVITY_MAPPING fm,  igrs_activity_master im WHERE im.activity_id = 'ACT_1900' AND fm.activity_id = im.activity_id) maker WHERE     slot_status = 'A' AND d.role_active = 'A' AND a.office_id = b.office_id AND c.role_office_map_id = b.role_office_map_id AND EMP.EMP_ID = D.USER_ID AND b.role_id = maker.role_id AND d.role_group_id = c.role_group_id AND e.office_id = a.office_id AND e.district_id = f.district_id and g.tehsil_id=e.tehsil_id and e.tehsil_id=? AND e.office_type_id='3' AND C.STATUS = 'A') group by office_name,office_id order by office_name";
    
    public static final String    GET_OFFICE_NAME_EFILE_HINDI="select h_office_name,office_id from (SELECT DISTINCT D.USER_ID AS c2, e.h_office_name, maker.activity_name activity_name,e.office_id FROM igrs_reg_slot_master a,IGRS_EMP_MASTER EMP,IGRS_ROLE_OFFICE_MAPPING b,IGRS_RGROUP_ROLE_MAPPING c,IGRS_USER_ROLE_GROUP_MAPPING d,igrs_office_master e, igrs_district_master f,igrs_tehsil_master g,(SELECT role_id, activity_name FROM IGRS_ROLE_FN_ACTIVITY_MAPPING fm,  igrs_activity_master im WHERE im.activity_id = 'ACT_1900' AND fm.activity_id = im.activity_id) maker WHERE     slot_status = 'A' AND d.role_active = 'A' AND a.office_id = b.office_id AND c.role_office_map_id = b.role_office_map_id AND EMP.EMP_ID = D.USER_ID AND b.role_id = maker.role_id AND d.role_group_id = c.role_group_id AND e.office_id = a.office_id AND e.district_id = f.district_id and g.tehsil_id=e.tehsil_id and e.tehsil_id=? AND e.office_type_id='3' AND C.STATUS = 'A') group by h_office_name,office_id order by h_office_name";
    
    public static final String  GET_OFFICE_ID_EFILE="select OFFICE_ID from igrs_office_master where OFFICE_STATUS='A' and OFFICE_NAME=?";
    
    public static final String  GET_OFFICE_ID_EFILE_HINDI="select OFFICE_ID from igrs_office_master where OFFICE_STATUS='A' and H_OFFICE_NAME=?";
    
public static final String  GET_OFFICE_NAME_EFILING="select OFFICE_NAME from igrs_office_master where OFFICE_STATUS='A' and OFFICE_ID=?";

public static final String  GET_OFFICE_NAME_EFILING_BANK="select district_name from igrs_district_master where district_id=?";

public static final String  GET_OFFICE_NAME_EFILING_BANK_HINDI="select h_district_name from igrs_district_master where district_id=?";
    
    public static final String  GET_OFFICE_NAME_EFILING_HINDI="select H_OFFICE_NAME from igrs_office_master where OFFICE_STATUS='A' and OFFICE_ID=?";

    public static final String   GET_PROPERTY_FLAG="select DEED_ID from igrs_reg_txn_detls_efiling where REG_TXN_ID=? ";
    
    public static final String  GET_PROPERTY_FLAG_DEED="select PROPERTY_VAL_REQD from igrs_deed_type_master where DEED_TYPE_ID=?";
    
    public static final String  GET_ESTAMP_AMOUNT="select TOTAL_AFTER_EXEMP from igrs_duty_txn_detls_efiling where DUTY_TXN_ID=?";
    public static final String   GET_ESTAMP_AMOUNT1="select ESTAMP_AMOUNT from igrs_estamp_detls where ESTAMP_CODE=?";
    
    public static final String    GET_PHYSICAL_AMOUNT1="select sum(CODE_OF_STAMP) from igrs_physical_stamp_detls where EFILING_TEMP_ID=?";
    
    //public static final String  OFFICE_MAP_EFILE="SELECT  First_name  ||' '  ||LAST_NAME AS SR_NAME,user_id FROM igrs_user_reg_details  WHERE user_id IN  (SELECT user_id FROM igrs_user_role_group_mapping  WHERE role_group_id IN    (SELECT role_group_id FROM igrs_Rgroup_role_mapping WHERE ROLE_OFFICE_MAP_ID IN  (SELECT role_office_map_id   FROM igrs_role_office_mapping  WHERE office_id= ?   AND status     ='A'  )  AND status='A' ))order by LOWER(SR_NAME)  ";
 // public static final String  OFFICE_MAP_EFILE="SELECT URD.FIRST_NAME || ' '||URD.MIDDLE_NAME ||' '||URD.LAST_NAME NAME ,u.user_id FROM IGRS_USER_ROLE_GROUP_MAPPING u,  IGRS_USER_REG_DETAILS urd WHERE role_group_id IN (SELECT role_group_id FROM IGRS_RGROUP_ROLE_MAPPING WHERE status = 'A' AND role_office_map_id IN (SELECT role_office_map_id FROM IGRS_ROLE_OFFICE_MAPPING b WHERE office_id = ? AND status = 'A' AND role_id IN (SELECT role_id FROM IGRS_ROLE_FN_ACTIVITY_MAPPING fm, igrs_activity_master im WHERE im.activity_id = 'ACT_1900'  AND fm.activity_id =im.activity_id))) and U.USER_ID = URD.USER_ID and URD.USER_STATUS='A'";
   public static final String  OFFICE_MAP_EFILE="select * from (SELECT URD.FIRST_NAME || ' '||URD.MIDDLE_NAME ||' '||URD.LAST_NAME NAME ,u.user_id,u.role_group_id,u.count FROM IGRS_USER_ROLE_GROUP_MAPPING u,  IGRS_USER_REG_DETAILS urd WHERE role_group_id IN (SELECT role_group_id FROM IGRS_RGROUP_ROLE_MAPPING WHERE status = 'A' AND role_office_map_id IN (SELECT role_office_map_id FROM IGRS_ROLE_OFFICE_MAPPING b WHERE office_id = ? AND status = 'A' AND role_id IN (SELECT role_id FROM IGRS_ROLE_FN_ACTIVITY_MAPPING fm, igrs_activity_master im WHERE im.activity_id = 'ACT_1900' AND fm.activity_id =im.activity_id))) and U.USER_ID = URD.USER_ID and URD.USER_STATUS='A'  order by count asc) where rownum=1";

    
    public static final String GET_count="select count(*) from igrs_physical_stamp_detls where  EFILING_TEMP_ID=?";
    public static final String  GET_UPDATE="update igrs_physical_stamp_detls set  EFILING_DUTY_ID=? where EFILING_TEMP_ID=?";
    public static final String    GET_UPDATE_PROPERTY_OUT_MP="update igrs_duty_txn_detls_efiling set PROPRTY_OUT_MP=? where DUTY_TXN_ID=?";
    public static final String    CHECK_DUTY="select EFILING_TEMP_ID from igrs_estamp_detls where EFILING_DUTY_ID=?";
    public static final String  GET_DEED_TYPE_DASHBOARD="select DEED_TYPE_NAME from igrs_deed_type_master where DEED_TYPE_ID=?";
    public static final String  GET_IST_TYPE_DASHBOARD="select INSTRUMENT_NAME from igrs_deed_instrument_master where INSTRUMENT_ID=?";
    
    public static final String DELET_STAMP = "delete from igrs_physical_stamp_detls where EFILING_TEMP_ID=? and SERIES_NUMBER=?";

	public static final String GET_REMAINING_DETAILS = "select STAMP_VENDOR_NAME,CODE_OF_STAMP,SERIES_NUMBER,DATE_OF_STAMP from IGRS_PHYSICAL_STAMP_DETLS where EFILING_TEMP_ID=?"; 

	//added by saurav
	public static final String GET_PAYABLE_DUTY="SELECT TOTAL_AFTER_EXEMP from IGRS_DUTY_TXN_DETLS_EFILING where DUTY_TXN_ID=?";
	public static final String GET_AVAILABLE_REG_TXN_ID="select REG_TXN_ID from IGRS_REG_TXN_DETLS_EFILING where DUTY_TXN_ID=?";
}
