package com.wipro.igrs.empmgmt.sql;



	public class CommonSQL
	{
		public static final String INSERT_EMP_PERSONAL_DETAILS ="INSERT INTO IGRS_EMP_MASTER(EMP_ID, COMP_EMP_ID,FIRST_NAME,"+
		        "MIDDLE_NAME,LAST_NAME,GENDER,DATE_OF_BIRTH,DOB_IN_WORDS,GUARDIAN_NAME,MOTHER_NAME,EMP_MARITAL_STATUS,"+                      
		        "EMP_RELIGION_ID,HOME_DISTRICT,EMP_CASTE_ID,PHYSICALLY_CHALLANGED,PH_CHALLANGE_DESC,EMP_HEIGHT,NATIONALITY,EMP_IDENTITY_MARK,"+ 
		        "PHONE_NUMBER,MOBILE_NUMBER,EMAIL,PERM_ADDRESS,PERM_COUNTRY,PERM_STATE,PERM_DISTRICT,PERM_PINCODE,PRESENT_ADDRESS,"+ 
		        "PRESENT_COUNTRY,PRESENT_STATE,PRESENT_DISTRICT,PRESENT_PINCODE,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,HOME_STATE)"+
		        "VALUES(?,?,?,?,?,?,TO_DATE(?,'DD/MON/YYYY'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?)" ; 
								

		public static final String INSERT_EMP_CHILD_DETAILS = "INSERT INTO IGRS_EMP_CHILD_DETAILS" +
									  "(EMP_ID, CHILID_NAME,  CHILD_DOB, CHILD_GENDER,  CREATED_BY, CREATED_DATE,UPDATE_BY,UPDATE_DATE)" +
		    					      "VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)"; 

		public static final String SEARCH_EMP_EMPID = "SELECT FIRST_NAME, MIDDLE_NAME FROM IGRS_EMP_MASTER WHERE EMP_ID = ?";
		
		public static final String INSERT_EMP_ACADEMIC_DETAILS = "INSERT INTO IGRS_EMP_TALENT_DETAILS(" +
															 "EMP_ID,QUALIFICATION_TYPE_ID,"+        
	            											 "STREAM_MAJOR,YEAR_OF_PASSING,GRADE,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,EMP_TALENT_ID)"+
	            											 "VALUES(?,?,?,?,?,?,SYSDATE,?,SYSDATE,EMP_TALENT_ID_SEQ.NEXTVAL)";
	                   
		public static final String INSERT_EMP_PREVIOUSEMPLOYMENT_DETAILS = "INSERT INTO IGRS_EMP_PREV_EMPMT_DETAILS("+
																	   "EMP_ID,ORGANIZATION_NAME, EMP_DESIGNATION, DURATION, COMPENSATION,"+
																	   "PF_ACCOUNT_NO,PF_ACCOUNT_LOCATION,REASON_FOR_SEPERATION, TAX_DEDUCTION,CREATED_BY," +
																	   "CEATED_DATE,UPDATE_BY,UPDATE_DATE, EMP_PREV_EMPMT_ID, JOINING_DATE,RELEASE_DATE)"+
																	   "VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,EMP_PREV_EMPMT_ID_SEQ.NEXTVAL,TO_DATE(?,'DD/MM/YYYY'),TO_DATE(?,'DD/MM/YYYY'))";
		
	
		
		public static final String INSERT_EMP_TRAINING_MAP_DETAILS = "INSERT INTO IGRS_EMP_TRAINING_MAPPING"+
																"(EMP_ID, TRAINING_TXT_ID, TRAINING_RESULT,CREATED_BY,CREATED_DATE)"+
																"VALUES(?,?,?,?,SYSDATE)";
		
		
																 
		public static final String INSERT_EMP_DEPT_EXAM_MAP_DETAILS = "INSERT INTO IGRS_EMP_DEPT_EXAM_EMP_MAPPING" +
															 "(EMP_ID, EMP_RESULT,CREATED_BY,CREATED_DATE)" +
															 "VALUES(?,?,?,SYSDATE)";
		//===========================Bank Details=========================================
		public static final String INSERT_EMP_BANK_ACCOUNT_DETAILS ="INSERT INTO IGRS_EMP_BANK_DETAILS"+
		"(EMP_ID,BANK_NAME,BANK_BRANCH,BANK_ADDRESS,EMP_NAME_IN_BANK,EMP_PAN_NO,BANK_ACC_NO,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE,BANK_IFSC_CODE)"+
		"VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,?)";

		public static final String	INSERT_EMP_FUND_DETAILS="INSERT INTO IGRS_EMP_FUND_DETAILS"+
		"(EMP_ID,FUND_TYPE_ID,FUND_ACCOUNT_NO,FUND_LOCATION,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE)"+
		"VALUES(?,?,?,?,?,?,SYSDATE,SYSDATE)";

		public static final String INSERT_EMP_NOMINEE_DETAILS="INSERT INTO IGRS_EMP_FUND_NOMINEE_DETAILS"+
		  "(EMP_ID,FUND_TYPE_ID,NOMINEE_NAME,NOMINEE_RELATIONSHIP,NOMINEE_ADDRESS,NOMINEE_AGE,CREATED_BY,UPDATE_BY,FUND_ACCOUNT_NO,CREATED_DATE,UPDATE_DATE,EMP_FUND_NOM_ID,NOMINEE_SHARE_PCT)"+
		  "VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE,EMP_FUND_NOM_ID_SEQ.NEXTVAL,?)";
				
		public static final String SELECT_EMP_FUND_TYPE ="SELECT FUND_TYPE_ID,FUND_NAME FROM IGRS_EMP_FUND_TYPE_MASTER";
		
		public static final String DELETE_EMP_BANK_NOMINEES = "DELETE FROM IGRS_EMP_FUND_NOMINEE_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_BANK_FUNDDETAILS = "DELETE FROM IGRS_EMP_FUND_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_BANK_DETAILS = "DELETE FROM IGRS_EMP_BANK_DETAILS WHERE EMP_ID = ?";
		
		//EMPLOYEE OFFICIAL DETAILS PAGE
		public static final String INSERT_EMP_OFFICIAL_DETAILS = "INSERT INTO IGRS_EMP_OFFICIAL_DETAILS"+
															     "(EMP_ID, EMP_DESIGNATION_ID, EMP_CLASS_ID, OFFICIATING, EMP_STATUS, SUPERVISOR_ID, DATE_OF_JOINING, DATE_OF_SEPARATION, EMP_OFFICIAL_STATUS, CREATED_BY, CREATED_DATE)"+
															     "VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE)";
		
		public static final String INSERT_EMP_VERFIFICATION_DETAILS = "INSERT INTO IGRS_EMP_VERIFICATION_DETAILS" +
																	  "(EMP_ID, VERIFYING_AUTH_NAME, VERIFICATION_DATE, COMMENTS, VERIFICATION_STATUS, CREATED_BY, CREATED_DATE)" +
																	  "VALUES(?,?,?,?,?,?,SYSDATE)";
		
		
		public static final String INSERT_EMP_DOCUMENT_DETAILS = "INSERT INTO IGRS_EMP_DOCUMENT_DETAILS"+
																 "(EMP_ID, EMP_DOC_ID, EMP_DOC_VALUE, EMP_DOC_STATUS, CREATED_BY, CREATED_DATE)"+	
																 "VALUES(?,?,?,?,?,SYSDATE)";
		
		
		
		//for dept
		
		public static final String INSERT_EMP_PROPERTY_DETAILS = "INSERT INTO IGRS_EMP_ASSET_DETAILS(EMP_ID,PROPERTY_ADDRESS,COUNTRY_ID,STATE_ID,DISTRICT_ID,POSTAL_CODE,SHARE_IN_ASSET,REGISTRATION_ID,REGISTRATION_DATE,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,ASSET_PROP_FLAG) VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?)";
		public static final String INSERT_EMP_ASSET_DETAILS = "INSERT INTO IGRS_EMP_ASSET_DETAILS(EMP_ID,ASSET_TYPE_ID,ASSET_DESCRIPTION,ASSET_VALUE,ASSET_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,ASSET_PROP_FLAG)VALUES(?,?,?,?,'A',?,SYSDATE,?,SYSDATE,?)";
		public static final String INSERT_EMP_TRAINING_DET_DETAILS = "INSERT INTO IGRS_EMP_TRAINING_DETAILS"+
		 "(TRAINING_TXN_ID,TRAINING_NUMBER,TRAINING_NAME,TRAINEE_LEVEL,ORGANISING_AUTHORITY," +
		 "ORGANISATION_BODY,PLACE_OF_TRAINING,FINANCIAL_YEAR," +
		 "TRAINING_START_DATE,TRAINING_END_DATE,AUTHORISING_AUTHORITY,AUTHORISATION_DATE,TRAINING_COST,TRAINING_COMMENTS,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE)" +
		 "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE)";
		
		
		
		public static final String INSERT_EMP_DEPT_EXAM_DET_DETAILS = "INSERT INTO IGRS_EMP_DEPT_EXAM_DETAILS"+
	    "(DEPT_EXAM_TXN_ID,DEPT_EXAM_NAME,DATE_OF_EXAM," +
	     "ORGANISING_AUTHORITY,PLACE_OF_EXAM,DATE_OF_RESULT,EXAM_DETAILS,CREATED_BY,UPDATE_BY," +
	     "CREATED_DATE,UPDATE_DATE)" +
	     "values(?,?,?,?,?,?,?,?,?,SYSDATE,SYSDATE)";
		public static final String INSERT_EMP_DEPT_EXAM_MAPPING_DETAILS="INSERT INTO IGRS_EMP_DEPT_EXAM_EMP_MAPPING" +
				"(DEPT_EXAM_TXN_ID,EMP_ID,EMP_RESULT,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE) "+
				"VALUES(?,?,?,?,?,SYSDATE,SYSDATE)";
		

		//public static final String INSERT_REPORTING_HIRACHY="INSERT INTO IGRS_EMP_RPTMGR_MASTER(EMP_ID,EMP_SUPERVISOR_NAME,EMP_SUPERVISOR_DESIGNATION,EMP_SUPERVISOR_ID)VALUES(?,?,?,?)";
		public static final String INSERT_SERVICE="INSERT INTO IGRS_EMP_VERIFICATION_DETAILS(EMP_ID,VERIFYING_AUTH_NAME,VERIFICATION_DATE,COMMENTS,VERIFICATION_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE, EMP_VERIFICATION_ID)VALUES(?,?,?,?,?,?,sysdate,?,sysdate, EMP_VERIFICATION_ID_SEQ.NEXTVAL)";
		public static final String INSERT_OFFICAL_DETAILS="INSERT INTO IGRS_EMP_OFFICIAL_DETAILS (EMP_ID,EMP_DESIGNATION_ID,EMP_CLASS_ID,OFFICIATING,EMP_STATUS,SUPERVISOR_ID,DATE_OF_JOINING,DATE_OF_SEPARATION,COMP_EMP_NO,EMP_OFFICIAL_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,OFFICATING_ID,DATE_JOIN_GOVT_SERV)VALUES(?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?)";
		

		
		public static final String INSERT_OFFICAL_FILE="INSERT INTO IGRS_EMP_DOCUMENT_DETAILS(EMP_ID,EMP_DOC_ID,EMP_DOC_VALUE,EMP_DOC_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)VALUES()";
		public static final String SELECT_ALL_GRADE="SELECT GRADE_ID,GRADE_NAME,H_GRADE_NAME FROM IGRS_EMP_GRADE_MASTER WHERE UPPER(GRADE_STATUS) = 'A' ORDER BY GRADE_ID";
		public static final String SELECT_ALL_GRADE_CADRE="select CADRE_ID from IGRS_GRADE_CADRE_MAPPING where GRADE_ID=?";
		public static final String  INSERT_FILE_DOCUMENT="INSERT INTO IGRS_EMP_DOCUMENT_DETAILS(EMP_ID,EMP_DOC_ID,EMP_DOC_VALUE,EMP_DOC_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)VALUES(?,?,?,?,?,sysdate,?,?)";
		//public static final String SELECT_REPORTING_HIRACHY="SELECT EMP_ID,FIRST_NAME ||' '|| LAST_NAME FROM IGRS_EMP_MASTER";
		  public static final String SELECT_DESIGNATION_NAME="select iecm.CADRE_NAME,iegcm.CADRE_ID from IGRS_EMP_GRADE_CADRE_MAPPING iegcm,IGRS_EMP_CADRE_MASTER iecm where iegcm.cadre_id=iecm.cadre_id AND iegcm.emp_id=?";
          public static final String SELECT_DESIGNATION_CADRE_GRADE="SELECT IGCM.CADRE_ID, IECM.CADRE_NAME, IECM.H_CADRE_NAME FROM IGRS_GRADE_CADRE_MAPPING IGCM, IGRS_EMP_CADRE_MASTER IECM WHERE IGCM.CADRE_ID = IECM.CADRE_ID AND IGCM.GRADE_ID = ?  ";
          public static final String SELECT_ALL_DOCUMENTLIST="select DOCUMENT_ID,DOCUMENT_TYPE,H_DOCUMENT_NAME from IGRS_DOCUMENT_MASTER";
          public static final String SELECT_REPORTING_HIRACHY="select iegcm.emp_id,iem.FIRST_NAME||' '||iem.LAST_NAME from IGRS_EMP_GRADE_CADRE_MAPPING iegcm,IGRS_EMP_MASTER iem where iegcm.cadre_id in (select cadre_id from igrs_emp_cadre_master where precedance_id <= (select precedance_id from igrs_emp_cadre_master where cadre_id =?))AND iegcm.emp_id=iem.emp_id";
          public static final String SELECT_REPORTING_HIRACHY_DESIGNATION="select igcm.CADRE_ID,iecm.CADRE_NAME   from IGRS_EMP_GRADE_CADRE_MAPPING igcm,IGRS_EMP_CADRE_MASTER iecm WHERE igcm.CADRE_ID=iecm.CADRE_ID AND igcm.EMP_ID=?";
          public static final String INSERT_OFFICAL_MAPPING="INSERT INTO IGRS_EMP_GRADE_CADRE_MAPPING(EMP_ID,GRADE_ID,CADRE_ID,STATUS)VALUES(?,?,?,?)";
		
		
		public static final String SELECT_DESIGNATION_OFFICIATING="select distinct(iecm.CADRE_NAME),iegcm.CADRE_ID from IGRS_EMP_GRADE_CADRE_MAPPING iegcm,IGRS_EMP_MASTER iem,IGRS_EMP_CADRE_MASTER iecm	where iegcm.cadre_id in (select cadre_id from igrs_emp_cadre_master where precedance_id <= (select precedance_id from igrs_emp_cadre_master where cadre_id =?))AND iegcm.emp_id=iem.emp_id AND iecm.CADRE_ID=iegcm.CADRE_ID";
		public static final String SELECT_DESIGNATION_OFFICIATING_FALLBACK="SELECT CADRE_NAME, CADRE_ID FROM igrs_emp_cadre_master WHERE cadre_id =  ?";


		public static final String GENERATE_SEQUENCE_ID = "SELECT TRAINNING_TXN_ID_SEQ.NEXTVAL FROM DUAL";
		public static final String GENERATE_EXAM_SEQUENCE_ID = "SELECT DEPT_TXN_ID_SEQ.NEXTVAL FROM DUAL";
		
		public static final String SELECT_ALL_FUNDACCNO="SELECT FUND_ACCOUNT_NO FROM IGRS_EMP_FUND_DETAILS ";
		
		public static final String SELECT_EXCLUDED_FUNDACCNO="SELECT FUND_ACCOUNT_NO FROM IGRS_EMP_FUND_DETAILS WHERE EMP_ID <> ?";    
		

		

                  
		public static final String SELECT_EMPID = "SELECT EMP_ID FROM IGRS_EMP_MASTER";
		public static final String CHECK_EMPID= "SELECT EMP_ID FROM IGRS_EMP_MASTER where EMP_ID=?";
		
		//==============================DEPARTMENTAL EXAMS===========================================
	    public static final String SELECT_DEPT_EXAM_ID_SEQ ="SELECT IGRS_EMP_DEPTEXAM_ID_SEQ.NEXTVAL FROM DUAL";


		public static final String	INSERT_DEPT_EXAM_DETAILS	= "INSERT INTO IGRS_EMP_DEPT_EXAM_DETAILS (DEPT_EXAM_TXN_ID,DEPT_EXAM_NAME,DATE_OF_EXAM,ORGANISING_AUTHORITY,"
                                                                  +" ORGANISIN_BODY,PLACE_OF_EXAM,DATE_OF_RESULT,EXAM_DETAILS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE) VALUES(?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";

        public static final String INSERT_DEPT_EXAM_MAPPING_DETAILS ="INSERT INTO IGRS_EMP_DEPT_EXAM_EMP_MAPPING (DEPT_EXAM_TXN_ID,EMP_ID,EMP_RESULT,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)"
                                                                     +" VALUES (?,?,?,?,SYSDATE,?,SYSDATE)";
		
		public static final String RETRIEVE_PERSONAL_DETAILS = "SELECT iem.EMP_ID, iem.FIRST_NAME, iem.MIDDLE_NAME, iem.LAST_NAME, " +
				"UPPER(iem.GENDER), TO_CHAR(iem.DATE_OF_BIRTH,'dd/mm/yyyy'), iem.DOB_IN_WORDS, iem.EMP_HEIGHT, iem.EMP_IDENTITY_MARK, " +
				"iem.PHYSICALLY_CHALLANGED, iem.EMP_MARITAL_STATUS, irg.religion_name, icm.CATEGORY_NAME, iem.GUARDIAN_NAME, " +
				"iem.MOTHER_NAME, iem.HOME_DISTRICT, iem.PERM_ADDRESS, con.country_name, stat.state_name, igdm.district_name, " +
				"iem.NATIONALITY, iem.PERM_PINCODE, iem.PRESENT_ADDRESS, igco.country_name, igst.state_name, igd.district_name, " +
				"iem.PRESENT_PINCODE, iem.PHONE_NUMBER, iem.MOBILE_NUMBER, iem.EMAIL, IEM.COMP_EMP_ID, IEM.PH_CHALLANGE_DESC, " +
				"DECODE(UPPER(IEM.GENDER), 'M', 'Male', 'F', 'Female') AS GENDER_LABEL, " +
				"DECODE(UPPER(IEM.PHYSICALLY_CHALLANGED), 'Y', 'Yes', 'No') AS PHY_CH_LABEL,irg.H_RELIGION_NAME,icm.H_CATEGORY_NAME, " +
				"dis.H_DISTRICT_NAME,con.H_COUNTRY_NAME,stat.H_STATE_NAME,igdm.H_DISTRICT_NAME,igco.H_COUNTRY_NAME,igst.H_STATE_NAME, "+
				"igd.H_DISTRICT_NAME,iem.HOME_STATE "+
				"FROM IGRS_EMP_MASTER iem, IGRS_RELIGION_MASTER irg, IGRS_PERSON_CATEGORY_MASTER icm, IGRS_DISTRICT_MASTER dis, " +
				"IGRS_COUNTRY_MASTER con, IGRS_STATE_MASTER stat, IGRS_DISTRICT_MASTER igdm, IGRS_COUNTRY_MASTER igco, " +
				"IGRS_STATE_MASTER igst, IGRS_DISTRICT_MASTER igd " +
				"WHERE " +
				//"NVL(iem.HOME_STATE,'%') =stat.state_id(+) AND " +
				"iem.EMP_RELIGION_ID =irg.religion_id(+) AND " +
				"iem.EMP_CASTE_ID =icm.CATEGORY_ID(+) AND " +
				"NVL(iem.HOME_DISTRICT,'%') =dis.district_id(+) AND " +
				"NVL(iem.perm_country,'%') =con.country_id(+) AND " +
				"NVL(iem.perm_state,'%') =stat.state_id(+) AND " +
				"NVL(iem.perm_district,'%') =igdm.district_id(+) AND " +
				"NVL(iem.present_country,'%')=igco.country_id(+) AND " +
				"NVL(iem.present_state,'%') =igst.state_id(+) AND " +
				"NVL(IEM.PRESENT_DISTRICT,'%')=IGD.DISTRICT_ID(+) AND " +
				"EMP_ID = ?";
		    


		public static final String RETRIEVE_CHILD_DETAILS = "SELECT CHILID_NAME,TO_CHAR(CHILD_DOB,'dd/mm/yyyy'),CHILD_GENDER FROM IGRS_EMP_CHILD_DETAILS WHERE EMP_ID = ?";
		
		public static final String RETRIEVE_PROPERTY_DETAILS = "select iead.property_address,icm.country_name,ism.state_name,idm.district_name,"
															    +"iead.postal_code,iead.share_in_asset,iead.registration_id,to_char(iead.registration_date,'DD/MM/YYYY')"
															     +" FROM igrs_emp_asset_details iead ,IGRS_COUNTRY_MASTER icm,IGRS_STATE_MASTER ism,"
															    +"IGRS_DISTRICT_MASTER idm WHERE iead.country_id = icm.country_id"
															    +" AND iead.state_id=ism.state_id "
															     +"AND iead.district_id=idm.district_id AND EMP_ID = ?";
		
		public static final String RETRIEVE_ASSET_DETAILS = "SELECT IEAM.ASSET_TYPE_NAME,IEAD.ASSET_DESCRIPTION,IEAD.ASSET_VALUE FROM"
			+" IGRS_EMP_ASSET_DETAILS IEAD,IGRS_EMP_ASSET_MASTER IEAM"
			+" WHERE IEAD.ASSET_TYPE_ID = IEAM.ASSET_TYPE_ID"
			+" AND IEAD.EMP_ID=?";
		
		public static final String RETRIEVE_ACADEMIC_DETAILS ="SELECT ieqm.QUALIFICATION_NAME,ietd.GRADE,ietd.STREAM_MAJOR,ietd.YEAR_OF_PASSING FROM IGRS_EMP_TALENT_DETAILS ietd,IGRS_EMP_QUALIFICATION_MASTER ieqm WHERE EMP_ID=? AND ietd.QUALIFICATION_TYPE_ID=ieqm.QUALIFICATION_TYPE_ID";
                                                                																
		public static final String RETRIEVE_PREVIOUS_DETAILS ="SELECT ORGANIZATION_NAME,EMP_DESIGNATION,DURATION,COMPENSATION,PF_ACCOUNT_NO,"
                                                             +"PF_ACCOUNT_LOCATION,REASON_FOR_SEPERATION,TAX_DEDUCTION FROM IGRS_EMP_PREV_EMPMT_DETAILS WHERE EMP_ID=?";
		public static final String RETRIEVE_TRAINING_DETAILS = "SELECT IETD.TRAINING_TXN_ID,IETD.ORGANISING_AUTHORITY,TO_CHAR(IETD.TRAINING_START_DATE,'dd/mm/yyyy'),TO_CHAR(IETD.TRAINING_END_DATE,'dd/mm/yyyy'),"
																+"IETD.PLACE_OF_TRAINING,IETD.AUTHORISING_AUTHORITY,TO_CHAR(IETD.AUTHORISATION_DATE,'dd/mm/yyyy'),"
																+"IETD.TRAINEE_LEVEL,IETD.ORGANISATION_BODY,IETD.TRAINING_COST,IETD.TRAINING_NAME,IETD.TRAINING_COMMENTS,"
																+"IETD.FINANCIAL_YEAR,IETM.TRAINING_RESULT FROM IGRS_EMP_TRAINING_DETAILS IETD,IGRS_EMP_TRAINING_MAPPING IETM,IGRS_EMP_MASTER iem"
																+" WHERE IETD.TRAINING_TXN_ID=IETM.TRAINING_TXN_ID AND "
																 +"iem.EMP_ID=IETM.EMP_ID AND IETM.EMP_ID=?";
		
		public static final String RETRIEVE_EXAM_DETAILS =" select ieded.DEPT_EXAM_NAME,TO_CHAR(ieded.DATE_OF_EXAM,'dd/mm/yyyy'),ieded.ORGANISING_AUTHORITY,"
															+"ieded.PLACE_OF_EXAM,ieded.ORGANISIN_BODY,TO_CHAR(ieded.DATE_OF_RESULT,'dd/mm/yyyy'),ieded.EXAM_DETAILS"
															+" FROM IGRS_EMP_DEPT_EXAM_DETAILS ieded,IGRS_EMP_DEPT_EXAM_EMP_MAPPING iedeem"
															+" where ieded.DEPT_EXAM_TXN_ID=iedeem.DEPT_EXAM_TXN_ID AND iedeem.EMP_ID=?";
															
															
		public static final String RETRIEVE_BANK_DETAILS ="SELECT BANK_NAME,BANK_BRANCH,BANK_ADDRESS,EMP_NAME_IN_BANK,EMP_PAN_NO,BANK_ACC_NO "
                                                           +"FROM IGRS_EMP_BANK_DETAILS WHERE EMP_ID = ?";

		public static final String RETRIEVE_FUND_DETAILS ="SELECT IEFTM.FUND_NAME,IEFD.FUND_ACCOUNT_NO,IEFD.FUND_LOCATION, IEFTM.FUND_TYPE_ID"

			+" FROM IGRS_EMP_FUND_DETAILS IEFD,IGRS_EMP_FUND_TYPE_MASTER IEFTM"

			+" WHERE IEFD.FUND_TYPE_ID = IEFTM.FUND_TYPE_ID(+)"

			+"AND IEFD.EMP_ID=?";

			
		
		public static final String RETRIEVE_NOMINEE_DETAILS = "SELECT DISTINCT IEFTM.FUND_NAME, IEFND.NOMINEE_NAME, " +
				"IEFND.NOMINEE_RELATIONSHIP, IEFND.NOMINEE_ADDRESS, IEFND.NOMINEE_AGE, FUNDTLS.FUND_ACCOUNT_NO, IEFTM.FUND_TYPE_ID " +
				"FROM IGRS_EMP_FUND_NOMINEE_DETAILS IEFND, IGRS_EMP_FUND_TYPE_MASTER IEFTM, IGRS_EMP_FUND_DETAILS FUNDTLS " +
				"WHERE IEFND.FUND_TYPE_ID = IEFTM.FUND_TYPE_ID AND IEFND.FUND_TYPE_ID = FUNDTLS.FUND_TYPE_ID " +
				"AND IEFND.EMP_ID = FUNDTLS.EMP_ID AND IEFND.FUND_ACCOUNT_NO = FUNDTLS.FUND_ACCOUNT_NO AND IEFND.EMP_ID = ?";
																

                  
	
		
		public static final String RETRIEVE_OFFICE_DETAILS = "SELECT IEOD.OFFICIATING, IEGM.GRADE_NAME, IECM.CADRE_NAME, " +
				"(SELECT CADRE_NAME FROM igrs_emp_cadre_master where cadre_id = IEOD.OFFICATING_ID) AS OFFICATING_ID_TEXT, " +			
				"TO_CHAR(IEOD.DATE_OF_JOINING,'dd/mm/yyyy') AS JOINDATE, TO_CHAR(IEOD.DATE_OF_SEPARATION,'dd/mm/yyyy') AS SEPDATE," +
				"IEOD.COMP_EMP_NO, IEOD.EMP_STATUS, IEOD.EMP_CLASS_ID, IEOD.OFFICATING_ID, IEOD.EMP_DESIGNATION_ID, "+
				"IEOD.SUPERVISOR_ID, TO_CHAR(IEOD.DATE_JOIN_GOVT_SERV,'dd/mm/yyyy') AS DATE_JOIN_GOVT_SERV " +
				",IEGM.H_GRADE_NAME,IECM.H_CADRE_NAME,(SELECT H_CADRE_NAME FROM igrs_emp_cadre_master where cadre_id = IEOD.OFFICATING_ID) AS OFFICATING_ID_TEXT_HINDI "+
				"FROM IGRS_EMP_OFFICIAL_DETAILS IEOD, IGRS_EMP_GRADE_MASTER IEGM, IGRS_EMP_CADRE_MASTER IECM, IGRS_EMP_MASTER IEM " +
				"WHERE IEOD.EMP_ID = IEM.EMP_ID(+) AND IEOD.EMP_CLASS_ID = IEGM.GRADE_ID(+) AND IEOD.EMP_DESIGNATION_ID = IECM.CADRE_ID(+) AND IEOD.EMP_ID =?";
													
															
		
		//public static final String RETRIEVE_DOCUMENT_DETAILS = " SELECT  IEDD.EMP_DOC_ID ,IEDD.EMP_DOC_VALUE FROM  IGRS_EMP_DOCUMENT_DETAILS  IEDD  WHERE  IEDD.EMP_ID=?";                  
		public static final String RETRIEVE_DOCUMENT_DETAILS = " SELECT IEDD.DOCUMENT_ID,DOC_MAST.DOCUMENT_TYPE,IEDD.DOCUMENT_NAME,DOC_MAST.H_DOCUMENT_NAME FROM IGRS_EMP_DOCUMENT_DETAILS  IEDD, IGRS_DOCUMENT_MASTER DOC_MAST"
			+" WHERE  IEDD.EMP_DOC_TYPE_ID = DOC_MAST.DOCUMENT_ID"
			+" AND IEDD.EMP_ID=?";                  
        
		public static final String RETRIEVE_EMP_DOC_OBJECT = "SELECT EMP_DOC_VALUE FROM IGRS_EMP_DOCUMENT_DETAILS WHERE DOCUMENT_ID = ?";
		
		public static final String RETRIEVE_SERVICE_DETAILS = " SELECT VERIFYING_AUTH_NAME,TO_CHAR(VERIFICATION_DATE,'dd/mm/yyyy'),COMMENTS FROM IGRS_EMP_VERIFICATION_DETAILS  WHERE EMP_ID=?";
		
		public static final String RETRIEVE_REPORTING_DETAILS = " select IEM.FIRST_NAME||' '||IEM.MIDDLE_NAME||' '||IEM.LAST_NAME,IECM.CADRE_NAME,IEOD.SUPERVISOR_ID"
																+" FROM IGRS_EMP_MASTER IEM,IGRS_EMP_OFFICIAL_DETAILS IEOD,IGRS_EMP_CADRE_MASTER IECM"
																+" WHERE IECM.CADRE_ID=IEOD.EMP_DESIGNATION_ID AND "
																+" IEOD.SUPERVISOR_ID=IEM.EMP_ID AND IEOD.EMP_ID = ?";
																			

		public static final String INSERT_EMP_TRAINING_DETAILS="INSERT INTO IGRS_EMP_TRAINING_DETAILS (TRAINING_TXN_ID,ORGANISING_AUTHORITY,TRAINING_START_DATE,TRAINING_END_DATE,PLACE_OF_TRAINING,TRAINING_BODY,AUTHORISING_AUTHORITY,AUTHORISATION_DATE,TRAINEE_LEVEL,ORGANISATION_BODY,TRAINING_COST,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,TRAINING_NAME,TRAINING_COMMENTS,FINANCIAL_YEAR,TRAINING_NUMBER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?)";
		
		//public static final String INSERT_EMP_TRAINING_MAPPING_DETAILS="INSERT INTO IGRS_EMP_TRAINING_MAPPING(TRAINING_TXN_ID,EMP_ID,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,TRAINING_RESULT)VALUES(?,?,?,sysdate,?,sysdate,?)";
	
		public static final String SELECT_TRAINING_TRANS_ID_SEQ="SELECT TRAINNING_TXN_ID_SEQ.NEXTVAL FROM DUAL";

	/*	public static final String INSERT_EMP_TRAINING_MAPPING_DETAILS="INSERT INTO IGRS_EMP_TRAINING_MAPPING"+
		"(TRAINING_TXN_ID,EMP_ID,TRAINING_RESULT,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE) " +
		"VALUES(?,?,?,?,?,SYSDATE,SYSDATE)";
*/
		
		public static final String INSERT_EMP_TRAINING_MAPPING_DETAILS="INSERT INTO IGRS_EMP_TRAINING_MAPPING"+
		"(TRAINING_TXN_ID,EMP_ID,TRAINING_RESULT,CREATED_BY,UPDATE_BY,CREATED_DATE,UPDATE_DATE) " +
		"VALUES(?,?,?,?,?,SYSDATE,SYSDATE)";


		public static final String SELECTREFERENCE="select IEM.FIRST_NAME,IEM.MIDDLE_NAME,IEM.LAST_NAME,IOM.OFFICE_NAME,IOM.OFFICE_ADDRESS from IGRS_EMP_MASTER IEM,IGRS_OFFICE_MASTER IOM,IGRS_EMP_OFFICE_MAPPING IEOM WHERE IEOM.EMP_ID=IEM.EMP_ID AND IEOM.OFFICE_ID=IOM.OFFICE_ID AND IEM.EMP_ID=?";
		
		public static final String STATE_ALL_QUERY="Select COUNTRY_ID,STATE_ID,STATE_NAME,H_STATE_NAME from IGRS_STATE_MASTER WHERE STATE_STATUS = 'A'";

		public static final String COUNTRY_ALL_QUERY="SELECT COUNTRY_ID, COUNTRY_NAME,H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER WHERE COUNTRY_STATUS='A'";

		public static final String DISTRICT_ALL_QUERY = "Select STATE_ID,DISTRICT_ID,DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER WHERE DISTRICT_STATUS = 'A'";
		
		public static final String RETRIVE_ALL_LOCATIONS="  select office_name,office_id from igrs_office_master";
		/*public static final String RETRIVE_TRANSFER_EMPLOYEE_DETAILS=" select iem.FIRST_NAME,iecm.CADRE_NAME,iom.OFFICE_NAME"
                                                                     +" from IGRS_EMP_MASTER iem,"
                                                                     +" IGRS_EMP_GRADE_CADRE_MAPPING iegcm,"
                                                                     +" IGRS_OFFICE_MASTER iom,"
                                                                     +" IGRS_EMP_OFFICE_MAPPING ieom,"
                                                                     +" IGRS_EMP_CADRE_MASTER iecm"
                                                                     +" where iem.EMP_ID=iegcm.EMP_ID and"
                                                                     +" iegcm.CADRE_ID =iecm.CADRE_ID and"
                                                                     +" iem.EMP_ID=ieom.EMP_ID and"
                                                                     +" ieom.OFFICE_ID =iom.OFFICE_ID and"
                                                                     +" iem.EMP_ID=?";
                                                                     */
		
		public static final String RETRIVE_TRANSFER_EMPLOYEE_DETAILS=" select iem.FIRST_NAME,iecm.CADRE_NAME,iom.OFFICE_NAME, iodm.dept_name "
				+" from IGRS_EMP_MASTER iem, IGRS_EMP_GRADE_CADRE_MAPPING iegcm, "
				+" IGRS_OFFICE_MASTER iom, IGRS_EMP_OFFICE_MAPPING ieom, IGRS_EMP_CADRE_MASTER iecm, igrs_office_dept_master iodm "
				+" where iem.EMP_ID=iegcm.EMP_ID and iegcm.CADRE_ID =iecm.CADRE_ID "
				+" and iem.EMP_ID=ieom.EMP_ID "
				+" and ieom.OFFICE_ID =iom.OFFICE_ID and iem.EMP_ID=? "
				+" and ieom.dept_id = iodm.dept_id ";
			
		
		public static final String INSERT_EMPMGMT_TRANSFER_DETAILS="INSERT INTO IGRS_EMP_TRASFER_DETAILS(	EMP_ID,DEPT_FROM,DEPT_TO,DATE_OF_TRANSFER,COMMENTS,TRANSFER_TYPE,TRANSFER_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)VALUES(?,?,?,?,?,?,?,?,sysdate,?,sysdate)";
		public static final String INSERT_EMPMGMT_INTRATRANSFER_DETAILS="INSERT INTO IGRS_EMP_TRASFER_DETAILS(	EMP_ID,DEPT_FROM,COMMENTS,TRANSFER_TYPE,TRANSFER_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE)VALUES(?,?,?,?,?,?,sysdate,?,sysdate)";
		/**** Training start **/
		public static final String INSERT_EMPMGMT_TRAINING_DETAILS="INSERT INTO IGRS_EMP_TRAINING_DETAILS(TRAINING_TXN_ID," +
		" ORGANISING_AUTHORITY,TRAINING_START_DATE,TRAINING_END_DATE,PLACE_OF_TRAINING,TRAINING_BODY," +
		"AUTHORISING_AUTHORITY,AUTHORISATION_DATE,TRAINEE_LEVEL,ORGANISATION_BODY," +
		"TRAINING_COST,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE," +
		"TRAINING_NAME,TRAINING_COMMENTS,FINANCIAL_YEAR,TRAINING_NUMBER)" +
		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,sysdate,?,?,?,?)";
		public static final String INSERT_EMPMGMT_EMP_TRAINING_MAPPING_DETAILS="INSERT INTO  IGRS_EMP_TRAINING_MAPPING (TRAINING_TXN_ID,EMP_ID,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE,TRAINING_RESULT)VALUES(?,?,?,sysdate,?,sysdate,?)";


		/*public static final String RETRIVE_EMPLOYEE_DETAILS="select emp.FIRST_NAME ||' '|| LAST_NAME,"
			+ " iecm.CADRE_NAME,"
			+ " TO_CHAR(IEOD.DATE_OF_JOINING,'dd/mm/yyyy'),"
			+ " TO_CHAR(IEOD.DATE_OF_SEPARATION,'dd/mm/yyyy'),"
			+ " iom.OFFICE_NAME,"
			+ " iom.OFFICE_ADDRESS,"
			+ " emp.PERM_ADDRESS,"			
			+ "	emp.PHONE_NUMBER,"
			+ " emp.EMAIL"
			+ " FROM IGRS_EMP_MASTER emp,IGRS_EMP_CADRE_MASTER iecm,"
			+ " IGRS_EMP_GRADE_CADRE_MAPPING iegcm,"
			+ " IGRS_OFFICE_MASTER iom,"
			+ " IGRS_EMP_OFFICE_MAPPING ieom,IGRS_EMP_OFFICIAL_DETAILS IEOD"
			+ " WHERE emp.EMP_ID=iegcm.EMP_ID AND "
			+ " iegcm.CADRE_ID=iecm.CADRE_ID AND"   
			+ " emp.EMP_ID=ieom.EMP_ID AND emp.EMP_ID= IEOD.EMP_ID AND"
			+ " ieom.OFFICE_ID=iom.OFFICE_ID AND"
			+ " emp.EMP_ID=?";
		*/
		
		public static final String RETRIVE_EMPLOYEE_DETAILS="select emp.FIRST_NAME ||' '|| LAST_NAME, "
				+ " iecm.CADRE_NAME, "
				+ " TO_CHAR(IEOD.DATE_OF_JOINING,'dd/mm/yyyy'), "
				+ " TO_CHAR(IEOD.DATE_OF_SEPARATION,'dd/mm/yyyy'), "
				+ " iom.OFFICE_NAME, "
				+ " iom.OFFICE_ADDRESS, "
				+ " emp.PERM_ADDRESS,	"		
				+ " emp.PHONE_NUMBER, " 
				+ " emp.EMAIL,IEOD.officiating,round(IESMD.sal_amount) "
				+ " FROM IGRS_EMP_MASTER emp,IGRS_EMP_CADRE_MASTER iecm, "
				+ " IGRS_EMP_GRADE_CADRE_MAPPING iegcm, "
				+ " IGRS_OFFICE_MASTER iom, "
				+ " IGRS_EMP_OFFICE_MAPPING ieom,IGRS_EMP_OFFICIAL_DETAILS IEOD,IGRS_EMP_SALARY_MONTHLY_DTL IESMD "
				+ " WHERE emp.EMP_ID=iegcm.EMP_ID AND "
				+ " iegcm.CADRE_ID=iecm.CADRE_ID AND  "
				+ " emp.EMP_ID=ieom.EMP_ID AND emp.EMP_ID= IEOD.EMP_ID AND "
				+ " ieom.OFFICE_ID=iom.OFFICE_ID AND "
				+ " IESMD.emp_id=emp.EMP_ID AND "
				+ " to_char(IESMD.created_date, 'MON') ='DEC' AND "
				+ " emp.EMP_ID=?";
		

		
		public static final String INSERT_EMP_USER_DETAILS = "INSERT INTO IGRS_USERS_LIST " +
				"( USER_ID, USER_TYPE_ID, CREATED_BY, CREATED_DATE, UPDATE_BY, UDPATE_DATE, USER_STATUS ) " +
				"VALUES ( ?, ?, ?, SYSDATE, ?, SYSDATE, ? )";
		
		//DRS employees to be added into the Employee Management Screen..
		public static final String INSERT_EMP_TO_REG_DETAILS_TABLE="INSERT INTO IGRS_USER_REG_DETAILS " +
				"( FIRST_NAME, MIDDLE_NAME, LAST_NAME, GENDER, DOB, GUARDIAN_NAME, MOTHER_NAME, RELIGION_ID, " +
				"CASTE_ID, NATIONALITY, PHONE_NUMBER, MOBILE_NUMBER, EMAIL_ID, ADDRESS, COUNTRY_ID, STATE_ID, " +
				"DISTRICT_ID, POSTAL_CODE, USER_ID, USER_PASSWORD, USER_STATUS, CREATED_BY, CREATED_DATE, " +
				"UPDATE_BY, UPDATE_DATE, USER_TXN_ID, USER_HINT_QUESTION_ID, USER_HINT_ANSWER ) " +
				"VALUES " +
				"( ?, ?, ?, ?, TO_DATE(?,'DD/MON/YYYY'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, SYSDATE, IGRS_USER_TXN_SEQ.NEXTVAL, ?, ? )";
		
		public static final String USER_ROLE_INSERT = "INSERT INTO"
			    + " IGRS_USER_ROLE_GROUP_MAPPING(USER_ID,ROLE_GROUP_ID,ROLE_ID,ROLE_ACTIVE)" 
			    + " VALUES(?,(SELECT distinct ROLE_GROUP_ID FROM IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME=?),(SELECT distinct ROLE_ID FROM IGRS_ROLE_MASTER WHERE ROLE_NAME=?),'A') ";
		
		
		/*** Training End	 */
		
		public static String RETRIVE_EMPLOYEE_SEARCH="SELECT EMP_ID FROM IGRS_EMP_MASTER WHERE EMP_ID=?";
		
		public static final String GET_EMP_PROPERTY_LIST = "SELECT PROPERTY_ADDRESS, COUNTRY_ID, STATE_ID, DISTRICT_ID, POSTAL_CODE, REGISTRATION_ID, REGISTRATION_DATE, SHARE_IN_ASSET FROM IGRS_EMP_ASSET_DETAILS WHERE EMP_ID = ? AND ASSET_PROP_FLAG = ?";
		
		public static final String GET_EMP_ASSET_LIST = "SELECT ASSET_TYPE_ID, ASSET_DESCRIPTION, ASSET_VALUE, ASSET_STATUS FROM IGRS_EMP_ASSET_DETAILS WHERE EMP_ID = ? AND ASSET_PROP_FLAG = ?";
		
		public static final String GET_EMP_PROPASSET_COUNT = "SELECT COUNT(*) FROM IGRS_EMP_ASSET_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_PROPASSET = "DELETE FROM IGRS_EMP_ASSET_DETAILS WHERE EMP_ID = ?";
		
		public static final String GET_EMP_ACDPRV_COUNT = "SELECT (PRV + ACD) AS TOTCOUNT FROM (SELECT COUNT(*) AS PRV FROM IGRS_EMP_PREV_EMPMT_DETAILS WHERE EMP_ID = ?), (SELECT COUNT(*) AS ACD FROM IGRS_EMP_TALENT_DETAILS WHERE EMP_ID = ?)";
		
		public static final String GET_EMP_ACD_LIST = "SELECT QUALIFICATION_TYPE_ID, GRADE, STREAM_MAJOR, YEAR_OF_PASSING FROM IGRS_EMP_TALENT_DETAILS WHERE EMP_ID = ?";
		
		public static final String GET_EMP_PRV_LIST = "SELECT ORGANIZATION_NAME, EMP_DESIGNATION, ROUND((DURATION/12),0) AS YEARS, MOD(DURATION,12) AS MONTHS, " +
				"COMPENSATION, PF_ACCOUNT_NO, PF_ACCOUNT_LOCATION, REASON_FOR_SEPERATION, TAX_DEDUCTION, " +
				"  TO_CHAR(JOINING_DATE, 'DD/MM/YYYY') AS JOINING_DATE, TO_CHAR(RELEASE_DATE, 'DD/MM/YYYY') AS RELEASE_DATE " +
				"FROM IGRS_EMP_PREV_EMPMT_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_ACD = "DELETE FROM IGRS_EMP_TALENT_DETAILS ACD WHERE ACD.EMP_ID = ?";

		public static final String DELETE_EMP_PRV = "DELETE FROM IGRS_EMP_PREV_EMPMT_DETAILS PRV WHERE PRV.EMP_ID = ?";
		
		public static final String DELETE_EMP_OFFICE_DTLS = "DELETE FROM  IGRS_EMP_OFFICIAL_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_GRADE_CADRE_MAP = "DELETE FROM  IGRS_EMP_GRADE_CADRE_MAPPING WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_SERVICE = "DELETE FROM  IGRS_EMP_VERIFICATION_DETAILS WHERE EMP_ID = ?";
		
		public static final String DELETE_EMP_DOCUMENT = "DELETE FROM  IGRS_EMP_DOCUMENT_DETAILS WHERE EMP_ID = ?";
		
		public static final String GET_DOC_TYPE_ID = "SELECT DOCUMENT_ID FROM IGRS_DOCUMENT_MASTER WHERE UPPER(DOCUMENT_TYPE) = UPPER(?)";
		
		public static final String GET_DOC_TYPE_ID_HINDI = "SELECT DOCUMENT_ID FROM IGRS_DOCUMENT_MASTER WHERE UPPER(H_DOCUMENT_NAME) = UPPER(?)";

		public static final String GET_DISTRICT_LABEL = "SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER where DISTRICT_ID = ?";

		public static final String GET_DISTRICT_LABEL_HINDI = "SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER where DISTRICT_ID = ?";
		
		public static final String GET_HOME_STATE_LABEL = "SELECT STATE_NAME FROM IGRS_STATE_MASTER where STATE_ID = ?";

		public static final String GET_HOME_STATE_LABEL_HINDI = "SELECT H_STATE_NAME FROM IGRS_STATE_MASTER where STATE_ID = ?";
		
		public static final String GET_FUND_DATA_ACCOUNT_NO = "SELECT FTM.FUND_TYPE_ID, FTM.FUND_NAME FROM IGRS_EMP_FUND_TYPE_MASTER FTM, IGRS_EMP_FUND_DETAILS EFD WHERE FTM.FUND_TYPE_ID = EFD.FUND_TYPE_ID AND EFD.FUND_ACCOUNT_NO = ?";
		
		public static final String GET_FUND_NAME_TYPE = "SELECT FTM.FUND_NAME FROM IGRS_EMP_FUND_TYPE_MASTER FTM WHERE FTM.FUND_TYPE_ID = ?";
		
		public static final String SEARCH_EMP_BASE_QUERY = "SELECT EMP_ID, FIRST_NAME, LAST_NAME FROM IGRS_EMP_MASTER WHERE 1=1 ";
		
		public static final String SEARCH_EMP_FIRSTNAME_CLAUSE = "AND UPPER(FIRST_NAME) LIKE UPPER(?) ";
		
		public static final String SEARCH_EMP_LASTNAME_CLAUSE = "AND UPPER(LAST_NAME) LIKE UPPER(?) ";


		public static final String GET_BANKMASTERLISTING = "SELECT BANK_ID, BANK_NAME, BANK_PHONE_NUMBER, BANK_ADDRESS FROM IGRS_BANK_MASTER WHERE STATUS = 'A'";
		
		public static final String GET_BANKLABEL = "SELECT BANK_NAME FROM IGRS_BANK_MASTER WHERE BANK_ID = ?";


		public static final String GET_RELATIVETYPEMASTERLISTING = "SELECT RELATIVE_TYPE_ID, RELATIVE_TYPE_NAME,H_RELATIVE_TYPE_NAME FROM IGRS_RELATIVE_TYPE_MASTER";


		 public static final String INSERT_EMP_FAMILY_DETAILS = "INSERT INTO IGRS_EMP_FAMILY_DETAILS " +
					"( EMP_FAMILY_ID, EMP_ID, RELATIVE_TYPE_ID, RELATIVE_NAME, RELATIVE_DOB, CREATED_BY, CREATED_DATE ) " +
					"VALUES ( IGRS_EMP_FAMILY_SEQ.NEXTVAL, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY'), ?, SYSDATE )";
		
		 public static final String GENERATE_NEW_EMP_ID = "SELECT CONCAT('IGRS', LPAD(IGRS_EMP_ID_SEQ.NEXTVAL, 6, '0')) AS EMP_ID FROM DUAL";


		public static final String GET_FAMILYDETAILS = "SELECT EM.EMP_FAMILY_ID, EM.EMP_ID, RM.RELATIVE_TYPE_ID, EM.RELATIVE_NAME, " +
				"TO_CHAR(EM.RELATIVE_DOB, 'dd/mm/yyyy') AS RELATIVE_DOB, RM.RELATIVE_TYPE_NAME, " +
				"TO_NUMBER(TO_CHAR(EM.RELATIVE_DOB, 'dd')) AS RELATIVE_DAY, TO_NUMBER(TO_CHAR(EM.RELATIVE_DOB, 'mm')) AS RELATIVE_MONTH, " +
				"TO_NUMBER(TO_CHAR(EM.RELATIVE_DOB, 'yyyy')) AS RELATIVE_YEAR ,H_RELATIVE_TYPE_NAME " +
				"FROM IGRS_EMP_FAMILY_DETAILS EM, IGRS_RELATIVE_TYPE_MASTER RM " +
				"WHERE EM.RELATIVE_TYPE_ID = RM.RELATIVE_TYPE_ID AND EMP_ID = ? " +
				"ORDER BY EM.EMP_FAMILY_ID";


		public static final String UPDATE_EMP_PERSONAL_DETAILS =  "UPDATE IGRS_EMP_MASTER SET " +
				"FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, DATE_OF_BIRTH = TO_DATE(?,'DD/MON/YYYY'), DOB_IN_WORDS = ?, EMP_RELIGION_ID = ?, " +
				"EMP_CASTE_ID = ?, EMP_HEIGHT = ?, EMP_IDENTITY_MARK = ?, PHYSICALLY_CHALLANGED = ?, PH_CHALLANGE_DESC = ?, " +
				"EMP_MARITAL_STATUS = ?, GUARDIAN_NAME = ?, MOTHER_NAME = ?, HOME_DISTRICT = ?, PERM_ADDRESS = ?, NATIONALITY = ?, " +
				"PERM_DISTRICT = ?, PERM_STATE = ?, PERM_COUNTRY = ?, PERM_PINCODE = ?, PRESENT_ADDRESS = ?, PRESENT_DISTRICT = ?, " +
				"PRESENT_STATE = ?, PRESENT_COUNTRY = ?, PRESENT_PINCODE = ?, UPDATE_BY = ?, UPDATE_DATE = SYSDATE, PHONE_NUMBER = ?, " +
				"MOBILE_NUMBER = ?, EMAIL = ?, COMP_EMP_ID = ?, GENDER = ?, HOME_STATE = ? WHERE EMP_ID = ? ";


		public static final String UPDATE_EMP_REGISTRATION_DETAILS = "UPDATE IGRS_USER_REG_DETAILS SET " +
				"FIRST_NAME = ?, MIDDLE_NAME = ?, LAST_NAME = ?, GENDER = ?, DOB = TO_DATE(?,'DD/MON/YYYY'), NATIONALITY = ?, COUNTRY_ID = ?, " +
				"STATE_ID = ?, DISTRICT_ID = ?, ADDRESS = ?, POSTAL_CODE = ?, PHONE_NUMBER = ?, MOBILE_NUMBER = ?, EMAIL_ID = ?, " +
				"GUARDIAN_NAME = ?, MOTHER_NAME = ?, CASTE_ID = ?, RELIGION_ID = ?, UPDATE_BY = ?, UPDATE_DATE = SYSDATE " +
				"WHERE USER_ID = ?";


		public static final String DELETE_EMP_FAMILY_DETAILS = "DELETE FROM IGRS_EMP_FAMILY_DETAILS WHERE EMP_ID = ?";


		public static final String GET_OFFC_SUBS_LIST = "SELECT OFFC_SUBS_VAL, OFFC_SUBS_LABEL,H_OFFC_SUBS_LABEL FROM IGRS_EMP_OFFC_SUBS_MASTER WHERE UPPER(STATUS) = 'A'";


		public static final String SELECT_ALL_CADRE = "SELECT CADRE_ID, CADRE_NAME,H_CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE UPPER(CADRE_STATUS) = 'A' ORDER BY CADRE_ID";
		
		
	}
	

