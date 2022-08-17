/**
 * 
 */
package com.wipro.igrs.poa.sql;

/**
 * @author karteek
 *
 */
public class CommonSQL {
	

	public static final String spLicenseQry = 
		"SELECT LICENSE_NO FROM IGRS_SP_USER_LICENSE_DETAILS WHERE LICENSE_STATUS LIKE " +
		" 'A' AND SP_USER_ID LIKE ";
	/*public static final String SERVICE_FEE_PROCEDURE = "CALL " 
		+"IGRSUSER.IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)";*/
	public static final String stateQry =
		"Select stm.STATE_ID, stm.STATE_NAME " +
		"from IGRS_STATE_MASTER stm,IGRS_COUNTRY_MASTER cm" +
     " Where stm.COUNTRY_ID = cm.COUNTRY_ID and cm.COUNTRY_ID LIKE ";
	
	/* Getting Category Details from person Category table
	 */ 
	public static final String categoryqry = 
		" SELECT CATEGORY_ID,CATEGORY_NAME from IGRS_PERSON_CATEGORY_MASTER" +
		" where CATEGORY_STATUS like 'A' " ;
	
	
	/*****************************************************************************
	 * For Searching poa Details  
	******************************************************************************/ 
     public static final String poadetailQry =
    	" SELECT IRPD.remarks, IRPD.created_date,IRPD.deed_type_id, IRPD.purpose, " +
         " IRPD.poa_txn_id, IRPPD.party_type_id,IRPPD.party_first_name,"+
         " IRPPD.party_middle_name, IRPPD.party_last_name,IRPPD.party_gender, IRPPD.party_age,"+
         " IRPPD.nationality,IRPPD.country_id, IRPPD.state_id, IRPPD.district_id,"+
         " IRPPD.party_address, IRPPD.postal_code, IRPPD.phone_number,  IRPPD.mobile_number,"+ 
         " IRPPD.email_id,  IRPPD.photo_proof_type_id, IRPPD.photo_proof_id, IRPPD.bank_name,"+ 
         " IRPPD.bank_address, IRPPD.organization_name, IRPPD.guardian_name,  IRPPD.mother_name,"+
         " IRPPD.category_id, IRPPD.caste_id,  IRPPD.religion_id,   IRPPD.party_thumb_impression,"+
         " IRPPD.party_photo, IRPPD.party_signature, IRPOM.old_poa_txn_id, IRPRM.reg_initn_id,"+
         " IRPRM.estamp_code, IRPRM.estamp_amount, IRPRM.poa_registration_id,"+
         " IRPRM.execution_date, IRPRM.presentation_date,IRPD.poa_status,IRPPM.payment_txn_id "+ 
         " FROM igrs_reg_poa_details IRPD,igrs_reg_poa_payment_map IRPPM, "+
         " igrs_reg_poa_party_details IRPPD,igrs_reg_poa_old_map IRPOM,igrs_reg_poa_regid_map IRPRM "+
         " WHERE  (IRPD.poa_txn_id = IRPPD.poa_txn_id  ) AND (IRPD.poa_txn_id = IRPOM.poa_txn_id ) "+
         " AND (IRPD.poa_txn_id = IRPOM.poa_txn_id  ) AND (IRPD.poa_txn_id =  IRPRM.poa_txn_id ) "+
          " AND (IRPPD.poa_txn_id= 1100 ) "+ 
         " AND (IRPD.poa_txn_id = IRPPM.poa_txn_id)";
         
	/*****************************************************************************
	 * POA View Search list 
	 *****************************************************************************/
     public static final String poaSearchqry =
     " SELECT  IRPD.poa_txn_id,IRPD.created_date " +
     " FROM igrs_reg_poa_details IRPD where (IRPD.poa_txn_id = ?) AND " +
     " to_date(TO_CHAR(IRPD.created_date,'DD/MM/YY'),'DD/MM/YY') BETWEEN " +
     "  to_date(?,'DD/MM/YY') AND to_date(?,'DD/MM/YY')" ;
     
	/*  For Insert POA ID in IGRS_REG_POA_DETAILS	 
	 */
	 public static final String poaIdQry =
	 " INSERT INTO IGRS_REG_POA_DETAILS(POA_TXN_ID,REMARKS,POA_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY, " +
      " UPDATE_DATE,DEED_TYPE_ID,POA_TYPE_ID,PURPOSE) VALUES (IGRS_POA_TXN_ID.NEXTVAL,?,'A','ADMIN',SYSDATE, " +
      " 'ADMIN',SYSDATE,?,?,?)";
	 /* For Insert POA Party Details in IGRS_REG_POA_PARTY_DETAILS   
	  * */
	 public static final String poaPartyQry =
   " INSERT INTO IGRS_REG_POA_PARTY_DETAILS ( POA_TXN_ID, PARTY_TYPE_ID, PARTY_FIRST_NAME," +
   " PARTY_MIDDLE_NAME, PARTY_LAST_NAME, PARTY_GENDER, PARTY_AGE, NATIONALITY, COUNTRY_ID, " +
   " STATE_ID,DISTRICT_ID,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID," +
   " PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID, BANK_NAME,BANK_ADDRESS,ORGANIZATION_NAME,GUARDIAN_NAME," +
   " MOTHER_NAME,CATEGORY_ID,CASTE_ID,RELIGION_ID,PARTY_THUMB_IMPRESSION,PARTY_PHOTO,PARTY_SIGNATURE)" +
   " VALUES(" +
   " ?,?,?,?,?," +
   " ?,?,?,?,?," +
   " ?,?,?,?,?," +
   " ?,?,?,?,?," +
   " ?,?,?,?,?," +
   " ?,?,?,?)"; 
	 
	/*  FOR Getting party type id form  IGRS_PARTY_TYPE_MASTER */
	 public static final String partyTypeQry =
    " SELECT PARTY_TYPE_ID FROM IGRS_PARTY_TYPE_MASTER " +
    " WHERE PARTY_TYPE_STATUS LIKE 'A' AND PARTY_TYPE_NAME LIKE ";
	 
	 /* FOR MAPPING OLD POA DETAILS IN IGRS_REG_POA_OLD_MAP	  */
	 public static  final String poaOldMpaQry =	
		 "INSERT INTO IGRS_REG_POA_OLD_MAP (POA_TXN_ID,OLD_POA_TXN_ID) VALUES(?,'')";
	
	 /* FOR MAPPING POA ID AND Payment Transaction Id	  */
	 public static  final String poaPayMpaQry =	
		 "INSERT INTO IGRS_REG_POA_PAYMENT_MAP (POA_TXN_ID,PAYMENT_TXN_ID) VALUES(?,?)";
	 
	 /* FOR MPPING POA REG DETAILA */
	 public static final String poaRegMapQry = 
	 "INSERT INTO IGRS_REG_POA_REGID_MAP (POA_TXN_ID,REG_INITN_ID,ESTAMP_CODE," +
	 " ESTAMP_AMOUNT,POA_REGISTRATION_ID,EXECUTION_DATE,PRESENTATION_DATE)" +
	 " VALUES(?,?,?,?,?,to_date(?,'dd/mm/yyyy'),to_date(?,'dd/mm/yyyy'))";
	 
	 
	/*For POA Getting Registration Initiation Number */
	public static final String regNoQry =
		"SELECT TRANSACTION_ID,TO_CHAR(CREATED_DATE,'DD/MM/YYYY')FROM IGRS_REG_INITN_TXN_DETAILS " +
		" WHERE TRANSACTION_ID LIKE "; 
	
  /* FOr Geting POA ID  */
	 public static final String getPoaIdQry = 
		 "SELECT poa_txn_id FROM igrs_reg_poa_details WHERE created_date IN" +
		 " (SELECT MAX(created_date) FROM igrs_reg_poa_details)";
	
	/* For POA geting PhotoId List  */
	public static final String photoIdQry =
		 "SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME" +
		 " FROM IGRS_PHOTOID_PROOF_TYPE_MASTER";
	
	public static final String DISTRICT_CITY_QUERY =
		"SELECT dist.DISTRICT_ID,dist.DISTRICT_NAME " +
		" from IGRS_DISTRICT_MASTER dist,IGRS_STATE_MASTER stm" +
		" where dist.STATE_ID = stm.STATE_ID and stm.State_ID = ? ";		
	
	public static final String DEED_QUERY = "SELECT DEED_TYPE_ID, "
		+"DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
		+"WHERE DEED_STATUS='A'";
	/*
	public static final String STATE_QUERY = " Select stm.STATE_ID, stm.STATE_NAME " +
			"from IGRS_STATE_MASTER stm,IGRS_COUNTRY_MASTER cm" +
	 " Where stm.COUNTRY_ID = cm.COUNTRY_ID and cm.COUNTRY_ID =?";*/
	
	public static final String cashTxn =
        " SELECT TRANSACTION_ID FROM IGRS_PAYMENT_MODE_DETAILS WHERE STATUS_FLAG LIKE 'A' AND "  ;
	
	public static final String cashTxnqry =
        " SELECT TRANSACTION_ID  FROM IGRS_PAYMENT_TXN_DETAILS "  ;
	
    public static final String statusUpdatqry =
    	" UPDATE IGRS_PAYMENT_MODE_DETAILS SET STATUS_FLAG = 'D' WHERE TRANSACTION_ID = ? " ;	
	
	public static final String challanTxnqry =
        " SELECT TRANSACTION_ID  FROM IGRS_PAYMENT_TXN_DETAILS "  ;
	public static final String challanTxnInsert = 
		"INSERT INTO IGRS_PAYMENT_TXN_DETAILS(TRANSACTION_ID," +
        "CREATED_BY,CREATED_DATE," +
        "UPDATE_BY,UPDATE_DATE,AMOUNT_PAID) VALUES ( IGRS_PAYMENT_TXNID.NEXTVAL,'IGRS',SYSDATE," +
        "'IGRS',SYSDATE," +
        "? )" ;
	public static final String paymentTxnMapQry = 
		"INSERT INTO IGRS_PAYMENT_TXN_MAP(PAYMENT_TXN_ID," +
        "PAYMENT_MODE_TXN_ID) VALUES (?,?)" ;
	public static final String getPayTxnIDQry =
		" SELECT MAX(TRANSACTION_ID)  FROM IGRS_PAYMENT_TXN_DETAILS ";
	
	public static final String mppingTxnqry =
        " SELECT DEPENDANT_TXN_ID FROM IGRS_PAYMENT_MODE_MAPPING "  ;
	
	
	public static final String district_names =
		              "SELECT DISTRICT_ID,DISTRICT_NAME,DISTRICT_STATUS " +
		              "FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS LIKE '%A%'";
	public static final String offType_names =
		             " SELECT OFFICE_TYPE_ID,OFFICE_TYPE_NAME " +
		             "FROM IGRS_OFFICE_TYPE_MASTER WHERE ACTIVE_FLAG LIKE 'Y'";
	public static final String division_names =
		              " SELECT OFFICE_ID,OFFICE_NAME " +
		              " FROM IGRS_OFFICE_MASTER WHERE " ;
	public static final String serviceTypeQry = 
		              " SELECT FUNCTION_ID,FUNCTION_NAME" +
		              " FROM IGRS_FUNCTION_MASTER" +
		              " WHERE FUNCTION_STATUS LIKE 'Y'AND PAYMENT_FLAG LIKE 'Y'";
	public static final String simplePayReceipt =
		              " SELECT TRANSACTION_ID,NET_AMOUNT,OFFICE_ID,DISTRICT_ID,FUNCTION_ID,REG_USER_ID, " +
		              " CREATED_DATE FROM IGRS_PAYMENT_MODE_DETAILS"  ;
	
	public static final String distNameQry =
		              " SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE "; 
	public static final String divisionNameQry =
		              " SELECT OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE ";
	public static final String serviceNameQry =
        " SELECT FUNCTION_NAME FROM IGRS_FUNCTION_MASTER WHERE ";	

	/**
	 * @author Madan Mohan
	 */
	public static final String COUNTRY_QUERY = "Select COUNTRY_ID, " 
        		+ "COUNTRY_NAME from IGRS_COUNTRY_MASTER " 
        		+"WHERE COUNTRY_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String STATE_QUERY = "Select STATE_ID, " 
				+"STATE_NAME from IGRS_STATE_MASTER " 
				+"Where COUNTRY_ID=?";
	/**
	 * @author Madan Mohan
	 */
	public static final String DISTRICT_QUERY = "Select DISTRICT_ID, " 
				+"DISTRICT_NAME from IGRS_DISTRICT_MASTER " 
				+" Where STATE_ID=? AND DISTRICT_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String TEHSIL_QUERY = "Select TEHSIL_ID, " 
			    +"TEHSIL_NAME from IGRS_TEHSIL_MASTER " 
			    +"Where DISTRICT_ID=? AND TEHSIL_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String WARD_QUERY = "Select WARD_PATWARI_ID, "
				+"WARD_PATWARI_NAME from IGRS_WARD_PATWARI_MASTER "
				+"Where TEHSIL_ID=? AND WARD_PATWARI_STATUS='A' "
				+"AND AREA_TYPE_ID=? AND WARD_OR_PATWARI=?";
	/**
	 * @author Madan Mohan
	 */
	public static final String MOHALLA_QUERY = "Select MOHALLA_VILLAGE_ID, "
				+"MOHALLA_VILLAGE_NAME from IGRS_MOHALLA_VILLAGE_MASTER "
				+"Where WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String COMMON_QUERY = "Select COMMON_ID, "
				+"COMMON_NAME from IGRS_COMMON_MASTER Where " 
				+"COMMON_RELATED_ID=? AND COMMON_STATUS='A'";
	/**
	 * @author madhaty
	
	public static final String DEED_QUERY = "SELECT DEED_TYPE_ID, "
				+"DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER "
				+"WHERE DEED_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String PROPERTY_TYPE_SELECT_QUERY = 
		"SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME " 
		+"FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String MUNICIPAL_BODY_SELECT_QUERY = 
		 "SELECT MUNICIPAL_BODY_ID,MUNICIPAL_BODY_NAME  FROM "
		+"IGRS_GOV_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String AREA_TYPE_QUERY =
		"SELECT AREA_TYPE_ID,AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER "
		+" WHERE AREA_TYPE_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String PLOT_USE_QUERY = 
		"SELECT PROPERTY_TYPE_L1_ID, PROPERTY_TYPE_L1_NAME," 
		+" TYPE_OF_LABEL FROM IGRS_PROP_TYPE_L1_MASTER "
		+" WHERE PROPERTY_TYPE_L1_STATUS='A' "
		+" AND PROPERTY_TYPE_ID=?";
	/**
	 * @author Madan Mohan
	 */
	public static final String FLOOR_MASTER_QUERY = 
		" SELECT IGRS_PROP_L1_FLOOR_MAPPING.FLOOR_ID," 
		+" IGRS_FLOOR_MASTER.FLOOR_NAME, IGRS_FLOOR_MASTER.FLOOR_DESC"
		+" FROM IGRS_FLOOR_MASTER, IGRS_PROP_TYPE_L1_MASTER,"
		+" IGRS_PROP_L1_FLOOR_MAPPING WHERE ((IGRS_FLOOR_MASTER.FLOOR_ID"
		+" = IGRS_PROP_L1_FLOOR_MAPPING.FLOOR_ID) AND "
		+" (IGRS_PROP_TYPE_L1_MASTER.PROPERTY_TYPE_L1_ID ="
		+" IGRS_PROP_L1_FLOOR_MAPPING.PROPERTY_TYPE_L1_ID)) AND "
		+" IGRS_PROP_L1_FLOOR_MAPPING.PROPERTY_TYPE_L1_ID = ?"
		+ " AND IGRS_FLOOR_MASTER.FLOOR_STATUS='A'";
	
	/**
	 * @author Madan Mohan
	 */
	public static final String PLOT_L2_USE_QUERY = 
		"SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME," 
		+"PROPERTY_TYPE_L2_DESC FROM IGRS_PROP_TYPE_L2_MASTER "
		+"WHERE PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID=?";
	/**
	 *  @author Madan Mohan
	 */ 
	public static final String INSTRUMENTS_QUERY = "SELECT " 
										+ "INSTRUMENT_ID," 
										+ "INSTRUMENT_NAME "  
										+ "FROM IGRS_DEED_INSTRUMENT_MASTER " 
										+ "WHERE DEED_TYPE_ID=? AND " 
										+ "INSTRUMENT_STATUS=?";
	/**
	 * @author Madan Mohan
	 */
	public static final String EXEMPTIONS_QUERY = "SELECT " 
										+ "EXEMPTION_ID," 
										+ "EXEMPTION_NAME " 
										+ "FROM IGRS_DEED_EXEMPTION_MASTER "
										+ "WHERE DEED_TYPE_ID=? AND " 
										+ "INSTRUMENT_ID=? AND "
										+ "EXEMPTION_STATUS=?";
	/**
	 * @author Madan Mohan
	 */
	public static final String DUTY_CALCULATION_PROCEDURE = "CALL " 
				+ "IGRS_STAMP_DUTY.IGRS_STAMP_DUTY_CALC3(?,?,?,?,?)";
	/**
	 * @author Madan Mohan
	 */
	public static final String REGISTRATION_FEE_PROCEDURE = "CALL " 
				+ "IGRS_REGISTRATION_FEE_PKG.IGRS_REG_FEE_PROC"
				+ "(?,?,?,?,?,?,?)";
	/**
	 * @author Madan Mohan
	 */
	public static final String JUDICIAL_DUTY_CALCULATION_PROCEDURE = 
		"CALL IGRS_STAMP_DUTY_JUDICIAL_CALC.IGRS_JUDICIAL_SDUTY_CALC(?,?,?,?)";
	/**
	 * @author Madan Mohan
	 */
	public static final String SERVICE_FEE_PROCEDURE = "CALL " 
			+"IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)";
	/**
	 * @author Madan Mohan
	 */
	public static final String PROPERTY_VALUATION_PROCEDURE = 
		"CALL IGRS_PROPERTY_VALUATION_PKG." 
		+"IGRS_PROP_VAL_CALC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String PROPERTY_FLOOR_VALUATION_PROCEDURE = 
		"CALL IGRS_PROPERTY_VALUATION_PKG1." 
		+"IGRS_PROP_VAL_CALC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	/**
	 * @author Madan Mohan
	 */
	public static final String SUB_CLAUSE_DETAILS_QUERY = "SELECT "
		+"DISTINCT M.SUB_CLAUSE_ID,S.SUB_CLAUSE_NAME,S.UNIT_FLAG FROM "
		+"IGRS_SUB_CLAUSE_MASTER S"
		+" ,IGRS_SUB_CLAUSE_AREA_MAPPING M WHERE S.SUB_CLAUSE_ID="
		+"M.SUB_CLAUSE_ID AND M.DISTRICT_ID=? AND M.TEHSIL_ID=? AND "
		+"M.WARD_PATWARI_ID=? AND M.MOHALLA_VILLAGE_ID=? AND "
		+"M.PROPERTY_TYPE_ID=? AND PROPERTY_TYPE_L1_ID = ? "
		+"AND S.SUB_CLAUSE_STATUS='A'";
	/**
	 * @author Madan Mohan
	 */
	public static final String SUB_CLAUSE_BUILDING_QUERY = 
	"SELECT DISTINCT S.SUB_CLAUSE_ID,M.SUB_CLAUSE_NAME,M.UNIT_FLAG" 
	+" FROM IGRS_SUB_CLAUSE_AREA_MAPPING S,IGRS_SUB_CLAUSE_MASTER M"
	+" WHERE S.SUB_CLAUSE_ID IN (SELECT SUB_CLAUSE_ID FROM "
	+" IGRS_SUB_CLAUSE_AREA_MAPPING WHERE SUB_CLAUSE_ID "
	+" IN (SELECT SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_FLOOR_MAPPING "
	+" WHERE FLOOR_ID=?)) AND DISTRICT_ID=? AND TEHSIL_ID=? " 
	+" AND WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_ID=? AND "
	+" PROPERTY_TYPE_ID=? AND PROPERTY_TYPE_L1_ID=? AND "
	+" PROPERTY_TYPE_L2_ID=? AND S.SUB_CLAUSE_ID=M.SUB_CLAUSE_ID";

	//modified by Shruti
	public static final String SELECTPOATYPES="SELECT POA_TYPE_ID,POA_TYPE_NAME FROM IGRS_REG_POA_TYPE_MASTER WHERE POA_TYPE_STATUS='A'";
	
	public static final String SELECTCOUNTRY="SELECT COUNTRY_ID,COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A' ORDER BY COUNTRY_ID ASC";
	
	public static final String SELECTSTATE="SELECT STATE_ID,STATE_NAME FROM IGRS_STATE_MASTER WHERE COUNTRY_ID=? AND STATE_STATUS='A' ORDER BY STATE_ID";
	
	public static final String SELECTDISTRICT="SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID=? AND DISTRICT_STATUS='A' ORDER BY DISTRICT_ID";
	
	public static final String INSERTPOAAPPLICANTDETAILS="INSERT INTO IGRS_POA_PARTY_DTLS ( POA_TXN_ID, POA_PARTY_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME,"
			+" GENDER, DOB, GUARDIAN_NAME, MOTHER_NAME, SPOUSE_NAME, ADDRESS, PHONE_NUMBER, MOBILE_NUMBER,"
			+" EMAIL_ID, POA_TYPE_ID,"
			+" SR_USER_ID, SR_OFFICE_ID, CREATED_BY, "
			+" CREATED_DATE, UPDATE_BY,UPDATE_DATE,EXEC_ON_MANUAL_STAMP ) VALUES ( ?, IGRS_POA_ID_SEQ.NEXTVAL, ?, ?, ?,"
			+" ?, ?, ?, ?, ?, ?, ?, ?,"
			+" ?,?,"
			+" ?, ?,?,"
			+" sysdate, ?, sysdate,?)"; 
	
	public static final String INSERTPOAACCEPTORDETAILS="INSERT INTO IGRS_POA_PARTY_DTLS ( POA_TXN_ID, POA_PARTY_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME,"
		+" GENDER, DOB, GUARDIAN_NAME, MOTHER_NAME, SPOUSE_NAME, ADDRESS, PHONE_NUMBER, MOBILE_NUMBER,"
		+" EMAIL_ID, POA_TYPE_ID, POA_FROM_DATE, POA_TO_DATE, STAMP_DUTY_PAID, STAMP_CODE,"
		+" DATE_OF_EXEC_OF_POA, DOCUMENT_NAME, " 
		+" DATE_OF_AUTHENTICATION, SR_USER_ID, SR_OFFICE_ID, CREATED_BY, "
		+" CREATED_DATE, UPDATE_BY,UPDATE_DATE,PARTY_TYPE_ID,REG_APP_NO,E_STAMP_APP_NO,STAMPCASE_POA_TYPE_ID ) VALUES ( ?, IGRS_POA_ID_SEQ.NEXTVAL, ?, ?, ?,"
		+" ?, ?, ?, ?, ?, ?, ?, ?,"
		+" ?,?, ?, ?, ?, ?,"
		+" ?, ?,"
		+" ?, ?,?,?, sysdate, ?, sysdate,?,?,?)"; 

	public static final String INSERTPOAAWARDERDETAILS="INSERT INTO IGRS_POA_PARTY_DTLS ( POA_TXN_ID, POA_PARTY_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME,"
			+" GENDER, DOB, GUARDIAN_NAME, MOTHER_NAME, SPOUSE_NAME, ADDRESS, PHONE_NUMBER, MOBILE_NUMBER,"
			+" EMAIL_ID, POA_TYPE_ID, POA_FROM_DATE, POA_TO_DATE, STAMP_DUTY_PAID, STAMP_CODE,"
			+" DATE_OF_EXEC_OF_POA, DOCUMENT_NAME, " 
			+" DATE_OF_AUTHENTICATION, SR_USER_ID, SR_OFFICE_ID, CREATED_BY, "
			+" CREATED_DATE, UPDATE_BY,UPDATE_DATE,PARTY_TYPE_ID,REG_APP_NO,E_STAMP_APP_NO) VALUES ( ?, IGRS_POA_ID_SEQ.NEXTVAL, ?, ?, ?,"
			+" ?, ?, ?, ?, ?, ?, ?, ?,"
			+" ?,?, ?, ?, ?, ?,"
			+" ?, ?,"
			+" ?, ?,?,?, sysdate, ?, sysdate,?,?,?)"; 
	
	public static final String SELECTSROOFFICECODE="select distinct iotm.OFFICE_TYPE_ID from IGRS_OFFICE_MASTER iom,"
			+" IGRS_OFFICE_TYPE_MASTER iotm where iom.DISTRICT_ID=? AND  iotm.OFFICE_TYPE_NAME = 'SRO'"
					+" AND iom.OFFICE_TYPE_ID=iotm.OFFICE_TYPE_ID";
	
	public static final String SELECTSRDISTRICT="select DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER"
	+" WHERE DISTRICT_STATUS='A' AND DISTRICT_ID=(SELECT IGRS_OFFICE_MASTER.DISTRICT_ID FROM IGRS_OFFICE_MASTER,"
	+" IGRS_EMP_OFFICE_MAPPING WHERE IGRS_OFFICE_MASTER.OFFICE_ID=IGRS_EMP_OFFICE_MAPPING.OFFICE_ID"
		  +" AND IGRS_EMP_OFFICE_MAPPING.EMP_ID=?)";
	
	public static final String SELECTPAOSRNO="SELECT IGRS_POA_SR_NO_SEQ.NEXTVAL FROM DUAL";
	
	public static final String GETSRNAME="select iem.first_name ||' '"
		+" || iem.middle_name from igrs_emp_master iem where emp_id=?";
	
	public static final String SELECTSROOFFICENAME="select iom.OFFICE_ID,iom.office_name FROM IGRS_OFFICE_MASTER iom,"
			+" IGRS_EMP_OFFICE_MAPPING ieom WHERE iom.OFFICE_ID=ieom.OFFICE_ID AND ieom.EMP_ID=?";
	
	public static final String CURRENT_DATE_TIME="SELECT TO_CHAR(SYSDATE,'dd/MM/yyy') from dual";
	
	public static final String SELECTPOAAUTHNUMBER="SELECT subtrMAX(POA_TXN_ID) from IGRS_POA_PARTY_DTLS";
	
	public static final String SELECTYEAROFPOANO="SELECT substr(MAX(POA_TXN_ID),8,4) from IGRS_POA_PARTY_DTLS";
	
	public static final String SELECTSYSDATEYEAR="select to_char(sysdate,'yyyy') from dual";
	
	public static final String RESETPOASRNOSEQ="drop sequence IGRS_POA_SR_NO_SEQ";
	
	public static final String RECREATEPOASRNO="CREATE SEQUENCE IGRS_POA_SR_NO_SEQ START WITH 1 MAXVALUE 999999999999999999999999999 MINVALUE 1 NOCYCLE NOCACHE ORDER";
	
	public static final String GETCURRENTDATE="SELECT TO_CHAR(SYSDATE,'dd/MM/yyyy') from dual";
	//end of code By Shruti
	
	//***********************ADDED BY SIMRAN*********************************//
	
	/*public static final String GET_POA_APPLICANT_DETAILS = "SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,DOB," +
			"GUARDIAN_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID," +
			"POA_TYPE_ID,EXEC_ON_MANUAL_STAMP FROM IGRS_POA_PARTY_DTLS WHERE " +
			"PARTY_TYPE_ID IS NULL AND POA_TXN_ID =";*/
	
	public static final String GET_POA_APPLICANT_DETAILS = "SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,DOB, GUARDIAN_NAME,MOTHER_NAME," +
			"SPOUSE_NAME,ADDRESS,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID, b.POA_TYPE_NAME ," +
			"EXEC_ON_MANUAL_STAMP,REG_APP_NO,E_STAMP_APP_NO FROM " +
			"IGRS_POA_PARTY_DTLS a, IGRS_REG_POA_TYPE_MASTER b WHERE  " +
			"PARTY_TYPE_ID IS NULL AND a.POA_TYPE_ID = b.POA_TYPE_ID AND POA_TXN_ID =?";
	
	public static final String GET_POA_AWARDEE_DETLS = "SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,DOB," +
			"GUARDIAN_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,PHONE_NUMBER,MOBILE_NUMBER," +
			"EMAIL_ID FROM IGRS_POA_PARTY_DTLS WHERE PARTY_TYPE_ID = '50004' AND POA_TXN_ID =?";
	
	public static final String GET_POA_ACCEPTOR_DETLS = "SELECT FIRST_NAME,MIDDLE_NAME,LAST_NAME,GENDER,DOB," +
	"GUARDIAN_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,PHONE_NUMBER,MOBILE_NUMBER," +
	"EMAIL_ID FROM IGRS_POA_PARTY_DTLS WHERE PARTY_TYPE_ID = '50008' AND POA_TXN_ID =?";
	
	public static final String GET_POA_COMMON_DETLS = "SELECT to_char(POA_FROM_DATE,'dd/MM/yyyy'),to_char(POA_TO_DATE,'dd/MM/yyyy')," +
			"STAMP_DUTY_PAID,STAMP_CODE, to_char(DATE_OF_EXEC_OF_POA,'dd/MM/yyyy')," +
			"to_char(DATE_OF_AUTHENTICATION,'dd/MM/yyyy'),SR_USER_ID,SR_OFFICE_ID  " +
			"FROM IGRS_POA_PARTY_DTLS WHERE PARTY_TYPE_ID = '50004' AND POA_TXN_ID =?";
	
	//****************************************************added by shruti
	public static final String GETPOAVIEWDTLS="SELECT  distinct IRPD.poa_txn_id,to_char(IRPD.created_date,'dd/MM/yyyy'),to_char(IRPD.POA_FROM_DATE,'dd/MM/yyyy'),to_char(IRPD.POA_TO_DATE,'dd/MM,yyyy') FROM igrs_poa_party_dtls IRPD where IRPD.poa_txn_id = ? order by poa_txn_id";

	public static final String GETPOAVIEWDTLS2="SELECT  distinct IRPD.poa_txn_id,to_char(IRPD.created_date,'dd/MM/yyyy'),to_char(IRPD.POA_FROM_DATE,'dd/MM/yyyy'),to_char(IRPD.POA_TO_DATE,'dd/MM/yyyy') FROM IGRS_POA_PARTY_DTLS IRPD where CREATED_DATE between to_date(?,'DD-MM-RRRR') and to_date(?,'DD-MM-RRRR') order by POA_TXN_ID";  
	
	public static final String GETPOADTLS="SELECT IRPD.remarks, IRPD.created_date,IRPD.deed_type_id," +
			"IRPD.purpose,IRPD.poa_txn_id, IRPPD.party_type_id,IRPPD.party_first_name," +
			"IRPPD.party_middle_name, IRPPD.party_last_name,IRPPD.party_gender," +
			"IRPPD.party_age,IRPPD.nationality,IRPPD.country_id, IRPPD.state_id," +
			"IRPPD.district_id,IRPPD.party_address, IRPPD.postal_code, IRPPD.phone_number," +
			"IRPPD.mobile_number,IRPPD.email_id,  IRPPD.photo_proof_type_id, IRPPD.photo_proof_id," +
			"IRPPD.bank_name,IRPPD.bank_address, IRPPD.organization_name, IRPPD.guardian_name," +
			"IRPPD.mother_name,IRPPD.category_id, IRPPD.caste_id,  IRPPD.religion_id," +
			"IRPPD.party_thumb_impression,IRPPD.party_photo, IRPPD.party_signature, IRPOM.old_poa_txn_id," +
			"IRPRM.reg_initn_id,IRPRM.estamp_code, IRPRM.estamp_amount, IRPRM.poa_registration_id," +
			"IRPRM.execution_date, IRPRM.presentation_date,IRPD.poa_status,IRPPM.payment_txn_id" +
			"FROM igrs_reg_poa_details IRPD,igrs_reg_poa_payment_map IRPPM,igrs_reg_poa_party_details IRPPD," +
			"igrs_reg_poa_old_map IRPOM,igrs_reg_poa_regid_map IRPRM WHERE  " +
			"(IRPD.poa_txn_id = IRPPD.poa_txn_id  ) AND (IRPD.poa_txn_id = IRPOM.poa_txn_id )" +
			" AND (IRPD.poa_txn_id = IRPOM.poa_txn_id  ) AND (IRPD.poa_txn_id =  IRPRM.poa_txn_id )" +
			" AND (IRPPD.poa_txn_id=? ) AND (IRPD.poa_txn_id = IRPPM.poa_txn_id)";

}
