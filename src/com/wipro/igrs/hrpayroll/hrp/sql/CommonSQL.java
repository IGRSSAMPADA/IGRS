

package com.wipro.igrs.hrpayroll.hrp.sql;

public final class CommonSQL {

    public static final String INSERT_BASIC_SLAB_DETAILS="";
	public static final String UPDATE_BASIC_SLAB_DETAILS="";
	//public static final String GET_BASIC_SLAB_DETAILS="";
	public static final String DELETE_SLAB_MASTER="";
	public static final String UPDATE_BASIC_SLAB_MAP="";
	public static final String UPDATE_BASIC_SLAB_MAP_STATUS="";
	public static final String INSERT_BASIC_SLAB_MAP_STATUS="";
	/* CADRES */
	public static final String	RETREIVE_ALL_CADRES							= "SELECT CADRE_ID, CADRE_NAME, NO_OF_POSTS FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	public static final String	RETREIVE_COUNT_CADRES						= "SELECT SUM(NO_OF_POSTS) FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_STATUS ='A'";
	
	public static final String	INSERT_CADRE								= "INSERT INTO IGRS_EMP_CADRE_MASTER(CADRE_ID, CADRE_NAME, CADRE_STATUS, NO_OF_POSTS, CREATED_BY, CREATED_DATE,  UPDATED_BY, UPDATED_DATE)"
																					+ "VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)";
	
	public static final String	RETREIVE_CADRE_SEQ_ID						= "SELECT IGRS_EMP_CADRE_MASTER_SEQ.NEXTVAL FROM DUAL";
	
	public static final String	DELETE_CADRES								= "UPDATE IGRS_EMP_CADRE_MASTER SET CADRE_STATUS = ?, UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE CADRE_ID=?";
	
	public static final String	EDIT_CADRES									= "UPDATE IGRS_EMP_CADRE_MASTER SET NO_OF_POSTS = ?,UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE CADRE_ID=? AND CADRE_STATUS='A'";
	
	public static final String	CHECK_CADRE_NAME							= "SELECT * FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_NAME = ? AND CADRE_STATUS = 'A'";
	
	public static final String	RETREIVE_UN_MAPPED_CADRES					= "SELECT IECM.CADRE_ID, IECM.CADRE_NAME, IECM.NO_OF_POSTS"
																					+ " FROM   IGRS_EMP_CADRE_MASTER IECM, IGRS_GRADE_CADRE_MAPPING IGCM"
																					+ " WHERE  IECM.CADRE_ID(+) != IGCM.CADRE_ID AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";




	public static final String	GET_BASIC_SLAB_DETAILS					= "SELECT IECM.CADRE_ID, IGGM.MIN_SALARY_SLAB, IGGM.MAX_SALARY_SLAB, " +
			"IGGM.INCREMENT_AMOUNT, IECM.CADRE_NAME, IECM.CADRE_STATUS, IECM.NO_OF_POSTS FROM   IGRS_EMP_CADRE_MASTER IECM, " +
			"IGRS_GRADE_CADRE_MAPPING IGCM, IGRS_EMP_GRADE_MASTER IGGM WHERE  IECM.CADRE_ID(+) != IGCM.CADRE_ID " +
			"AND IGGM.GRADE_ID=IGCM.GRADE_ID AND IECM.CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS"; 
		
		/*"SELECT IECM.CADRE_ID, IECM.CADRE_NAME, IECM.NO_OF_POSTS"
																					+ " FROM   IGRS_EMP_CADRE_MASTER IECM, IGRS_GRADE_CADRE_MAPPING IGCM"
																					+ " WHERE  IECM.CADRE_ID(+) != IGCM.CADRE_ID AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
																					*/
	



	
	public static final String	RETREIVE_UN_MAPPED_CADRES_COUNT				= "SELECT SUM(IECM.NO_OF_POSTS)"
																					+ " FROM   IGRS_EMP_CADRE_MASTER IECM,IGRS_GRADE_CADRE_MAPPING IGCM"
																					+ " WHERE  IECM.CADRE_ID(+) != IGCM.CADRE_ID AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	/* GRADES */
	public static final String	RETREIVE_ALL_GRADES							= "SELECT GRADE_ID, GRADE_NAME, MIN_SALARY_SLAB, MAX_SALARY_SLAB, INCREMENT_AMOUNT FROM IGRS_EMP_GRADE_MASTER WHERE GRADE_STATUS = 'A' ORDER BY GRADE_NAME";
	
	public static final String	RETREIVE_GRADE_SEQ_ID						= "SELECT IGRS_EMP_GRADE_MASTER_SEQ.NEXTVAL FROM DUAL";
	
	// public static final String RETREIVE_GRADE_NAMES = "SELECT
	// IGRS_EMP_GRADE_MASTER_SEQ.NEXTVAL FROM DUAL";
	public static final String	INSERT_GRADE								= "INSERT INTO IGRS_EMP_GRADE_MASTER(GRADE_ID, GRADE_NAME, MIN_SALARY_SLAB, MAX_SALARY_SLAB, INCREMENT_AMOUNT, GRADE_STATUS, CREATED_BY, CREATED_DATE, UPDATED_BY, UPDATED_DATE)"
																					+ "VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";
	
	public static final String	DELETE_GRADES								= "UPDATE IGRS_EMP_GRADE_MASTER SET GRADE_STATUS = ?, UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE GRADE_ID=?";
	
	public static final String	EDIT_GRADES									= "UPDATE IGRS_EMP_GRADE_MASTER SET  MIN_SALARY_SLAB = ?, MAX_SALARY_SLAB=?, INCREMENT_AMOUNT=?, UPDATED_BY = ?, UPDATED_DATE=SYSDATE WHERE GRADE_ID=? AND GRADE_STATUS='A'";
	
	public static final String	CHECK_GRADE_NAME							= "SELECT * FROM IGRS_EMP_GRADE_MASTER WHERE GRADE_NAME = ? AND GRADE_STATUS = 'A'";
	
	/* LEAVE TYPE */
	public static final String	RETRIVE_ALL_LEAVE							= "SELECT LEAVE_TYPE_ID,LEAVE_TYPE_NAME,GENDER,MAXIMUM_NO_DAYS,LEAVE_TYPE_DESC FROM IGRS_EMP_LEAVE_TYPE_MASTER WHERE LEAVE_TYPE_STATUS ='A' ORDER BY LEAVE_TYPE_ID";
	
	public static final String	INSERT_INTO_LEAVE							= "INSERT INTO IGRS_EMP_LEAVE_TYPE_MASTER (LEAVE_TYPE_ID,LEAVE_TYPE_NAME,GENDER,MAXIMUM_NO_DAYS,LEAVE_TYPE_DESC,LEAVE_TYPE_STATUS,CREATED_BY,CREATED_DATE,UPDATE_BY,UPDATE_DATE) VALUES(?,?,?,?,?,?,?,SYSDATE,?,SYSDATE)";
	
	public static final String	EDIT_LEAVE_MASTER							= "UPDATE IGRS_EMP_LEAVE_TYPE_MASTER SET GENDER=?,MAXIMUM_NO_DAYS=?,LEAVE_TYPE_DESC=?,UPDATE_BY=?,UPDATE_DATE=SYSDATE WHERE  LEAVE_TYPE_ID=? AND LEAVE_TYPE_STATUS='A'";
	
	public static final String	DELETE_LEAVE_MASTER							= "UPDATE IGRS_EMP_LEAVE_TYPE_MASTER SET LEAVE_TYPE_STATUS =?,UPDATE_BY =?,UPDATE_DATE=SYSDATE WHERE LEAVE_TYPE_ID=?";
	
	public static final String	RETREIVE_LEAVE_SEQ_ID						= "SELECT IGRS_EMP_LEAVE_TYPE_MASTER_SEQ.NEXTVAL FROM DUAL";
	
	public static final String	CHECK_LEAVE_TYPE_NAME						= "SELECT * FROM IGRS_EMP_LEAVE_TYPE_MASTER WHERE LEAVE_TYPE_NAME = ? AND LEAVE_TYPE_STATUS ='A'";
	
	/* GRADE-CADRE */
	public static final String	RETRIVE_GRADE_FOR_GRADE_CADRE_MAPPING		= "SELECT GRADE_ID, GRADE_NAME FROM IGRS_EMP_GRADE_MASTER WHERE GRADE_STATUS='A' ORDER BY GRADE_NAME";
	
	public static final String	RETREIVE_CADRE_FOR_GRADE_CADRE_MAPPING		= "SELECT CADRE_ID, CADRE_NAME,NO_OF_POSTS FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID NOT IN (SELECT CADRE_ID FROM IGRS_GRADE_CADRE_MAPPING) AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	public static final String	SAVE_GRADE_CADRE_MAP						= "INSERT INTO IGRS_GRADE_CADRE_MAPPING(GRADE_ID, CADRE_ID) VALUES (?,?)";
	
	public static final String	AVAILABLE_CADRES_LIST						= "SELECT CADRE_ID, CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID NOT IN (SELECT DISTINCT(CADRE_ID) FROM IGRS_GRADE_CADRE_MAPPING WHERE GRADE_ID = ?) AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	public static final String	SELECTED_CADRES_LIST						= "SELECT CADRE_ID, CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID IN (SELECT DISTINCT(CADRE_ID) FROM IGRS_GRADE_CADRE_MAPPING WHERE GRADE_ID = ?) AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	public static final String	DELETE_GRADE_CADRES_MAP						= "DELETE  FROM IGRS_GRADE_CADRE_MAPPING WHERE GRADE_ID=?";
	
	/* SALRY MASTER */
	public static final String	RETRIVE_GRADE								= "SELECT GRADE_NAME FROM IGRS_EMP_GRADE_MASTER WHERE GRADE_ID=?";
	
	public static final String	RETRIVE_CADRE								= "SELECT CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID=?";
	
	public static final String	UPDATE_PAYMENT_COMPONENTS					= "UPDATE IGRS_EMP_CADRE_SALARY_MASTER"
																					+ " SET  SALARY_HEAD_VALUE = ?"
																					+ " WHERE GRADE_ID = ?"
																					+ " AND   CADRE_ID = ?"
																					+ " AND   SALARY_TYPE = 'P'";
																					//+ " AND   SALARY_HEAD_ID = ?";
	
	public static final String	UPDATE_DEDUCTION_COMPONENTS					= "UPDATE IGRS_EMP_CADRE_SALARY_MASTER"
																					+ " SET  SALARY_HEAD_VALUE = ?"
																					+ " WHERE GRADE_ID = ?"
																					+ " AND   CADRE_ID = ?"
																					+ " AND   SALARY_TYPE = 'A'";
																				//	+ " AND   SALARY_HEAD_ID = ?";
	
	public static final String	UPDATE_TREASURY_COMPONENTS					= "UPDATE IGRS_EMP_CADRE_SALARY_MASTER"
																					+ " SET  SALARY_HEAD_VALUE = ?"
																					+ " WHERE GRADE_ID = ?"
																					+ " AND   CADRE_ID = ?"
																					+ " AND   SALARY_TYPE = 'T'";
																				//	+ " AND   SALARY_HEAD_ID = ?";
	
	/* SALRY MASTER */
	public static final String	RETRIVE_GRADES_FOR_SALARY_MASTER			= "select grade_id,grade_name from IGRS_EMP_GRADE_MASTER"
																					+ " where grade_id in (select distinct grade_id from IGRS_GRADE_CADRE_MAPPING) order by grade_name ";
	
	public static final String	RETRIVE_CADRES_FOR_SALARY_MASTER			= "SELECT CADRE_ID, CADRE_NAME FROM IGRS_EMP_CADRE_MASTER WHERE CADRE_ID IN (SELECT DISTINCT(CADRE_ID) FROM IGRS_GRADE_CADRE_MAPPING WHERE GRADE_ID = ?) AND CADRE_STATUS = 'A' ORDER BY NO_OF_POSTS";
	
	public static final String	RETRIVE_ONLY_GRADE							= "SELECT GRADE_ID, GRADE_NAME, MIN_SALARY_SLAB, MAX_SALARY_SLAB, INCREMENT_AMOUNT FROM IGRS_EMP_GRADE_MASTER WHERE GRADE_STATUS = 'A' AND GRADE_ID=? ORDER BY GRADE_NAME";
	
                        	/* public static final String	RETRIVE_PAYMENT_COMPONENTS					= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E, C.SALARY_HEAD_VALUE"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='P'AND C.STATUS='A'"; */
       public static final String	RETRIVE_PAYMENT_COMPONENTS = "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID ";
                        //+ "  AND A.COMPONENT_TYPE=?";
                        
                        /* public static final String	RETRIVE_AGDEDUCTIONS_COMPONENTS				= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E, C.SALARY_HEAD_VALUE"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='A'AND C.STATUS='A'"; */
     public static final String	RETRIVE_AGDEDUCTIONS_COMPONENTS	= "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID ";
                        //+ "  AND A.COMPONENT_TYPE=?";
                        
                        /* public static final String	RETRIVE_TREASURY_COMPONENTS					= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E, C.SALARY_HEAD_VALUE"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='T'AND C.STATUS='A'"; */
      public static final String	RETRIVE_TREASURY_COMPONENTS = "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID ";
                       // + "  AND A.COMPONENT_TYPE=?";

	
	public static final String	RETRIVE_PAYMENT_TOTAL						= "SELECT SUM(SALARY_HEAD_VALUE) FROM"
																					+ " IGRS_EMP_CADRE_SALARY_MASTER WHERE GRADE_ID = ? AND CADRE_ID = ? AND SALARY_TYPE = 'P'";
	
	public static final String	RETRIVE_AGDEDUCTION_TOTAL					= "SELECT SUM(SALARY_HEAD_VALUE) FROM"
																					+ " IGRS_EMP_CADRE_SALARY_MASTER WHERE GRADE_ID = ? AND CADRE_ID = ? AND SALARY_TYPE = 'A'";
	
	public static final String	RETRIVE_TREASURY_TOTAL						= "SELECT SUM(SALARY_HEAD_VALUE) FROM"
																					+ " IGRS_EMP_CADRE_SALARY_MASTER WHERE GRADE_ID = ? AND CADRE_ID = ? AND SALARY_TYPE = 'T'";
	
	public static final String	AVAILABLE_SALARY_COMPONENTS					= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E"
																					+ " FROM IGRS_EXP_HEAD_MAPPING A,IGRS_EXP_DETAILED_HEAD_MASTER B"
																					+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
																					+ " AND   A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
																					+ " AND   A.MINOR_HEAD_ID = 'MINOR_001'"
																					+ " AND   A.SCHEME_ID = 'SCHEME_001'"
																					+ " AND   A.SEGMENT_ID = 'SEG_001'"
																					+ " AND   A.OBJECT_ID = 'OBJ_001'"
																					+ " AND   A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID(+)"
																					+ " MINUS"
																					+ " SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E"
																					+ " FROM IGRS_EXP_HEAD_MAPPING A,IGRS_EXP_DETAILED_HEAD_MASTER B,IGRS_EMP_CADRE_SALARY_MASTER C"
																					+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
																					+ " AND   A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
																					+ " AND   A.MINOR_HEAD_ID = 'MINOR_001'"
																					+ " AND   A.SCHEME_ID = 'SCHEME_001'"
																					+ " AND   A.SEGMENT_ID = 'SEG_001'"
																					+ " AND   A.OBJECT_ID = 'OBJ_001'"
																					+ " AND   A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID(+)"
																					+ " AND   A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID(+)"
																					+ " AND   C.GRADE_ID  IN ?"
																					+ " AND   C.CADRE_ID IN ?"
																					+ " AND   C.STATUS  IN ('A')";
	
	/*
	public static final String	SELECTED_SALARY_COMPONENTS					= "SELECT DISTINCT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_NAME_E, C.SALARY_HEAD_VALUE"
																					+ " FROM IGRS_EXP_HEAD_MAPPING A,"
																					+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
																					+ " IGRS_EMP_CADRE_SALARY_MASTER C"
																					+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
																					+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
																					+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
																					+ " AND A.SCHEME_ID = 'SCHEME_001'"
																					+ " AND A.SEGMENT_ID = 'SEG_001'"
																					+ " AND A.OBJECT_ID = 'OBJ_001'"
																					+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
																					+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
																					+ " AND C.GRADE_ID = ?"
																					+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE= ? AND C.STATUS ='A'"; */
	
	
	public static final String	SELECTED_SALARY_COMPONENTS				= "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
	    													+ "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                                                                                                                  + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                                                                                                                  + "  AND A.COMPONENT_TYPE=?";
	
	
	public static final String	RETRIVE_EMPLOYEES_FOR_SALARY_MASTER			= " SELECT EMP_ID FROM IGRS_EMP_GRADE_CADRE_MAPPING ";
	
	public static final String	RETRIVE_GRADE_CADRE_FOR_SALARY_MASTER		= "SELECT G.GRADE_NAME,C.CADRE_NAME,G.GRADE_ID,C.CADRE_ID"
																					+ " FROM   IGRS_EMP_GRADE_MASTER G,"
																					+ " IGRS_EMP_CADRE_MASTER C, "
																					+ " IGRS_EMP_GRADE_CADRE_MAPPING M"
																					+ " WHERE  M.GRADE_ID = G.GRADE_ID(+)"
																					+ " AND    M.CADRE_ID = C.CADRE_ID(+)"
																					+ " AND    M.EMP_ID   = ?";
	
	public static final String	INSERT_SALARY_AMOUNT						= "INSERT INTO IGRS_EMP_SALARY_MAPPING(EMP_ID,GRADE_ID,CADRE_ID, SALARY_HEAD_ID, SALARY_HEAD_VALUE, SALARY_HEAD_STATUS, EFFECTIVE_FROM) VALUES(?,?,?,?,?,?,SYSDATE)";
	
	//public static final String	INSERT_SELECTED_SALARY_COMPONENT			= "INSERT INTO IGRS_EMP_CADRE_SALARY_MASTER (GRADE_ID,CADRE_ID,SALARY_TYPE,SALARY_HEAD_ID,SALARY_TXN_ID,SALARY_HEAD_VALUE,STATUS)"
	//																				+ " VALUES (?,?,?,?,?,'0','A')";
	
	public static final String	INSERT_SELECTED_SALARY_COMPONENT			= "INSERT INTO IGRS_EMP_SAL_COMP_GRADE_MAP (GRADE_ID,CADRE_ID,SAL_COMPONENT_ID,EMP_ID,COMPONENT_VALUE)"
													+ " VALUES (?,?,?,?,'0')";
	
	public static final String	DELETE_SELECTED_SALARY_COMPONENT			= "DELETE FROM IGRS_EMP_CADRE_SALARY_MASTER"
																					+ " WHERE GRADE_ID=? AND CADRE_ID=?"
																					+ " AND SALARY_TYPE=?";
	
	public static final String	RETREIVE_SALARY_SEQ_ID						= "SELECT IGRS_EMP_CADRE_SAL_SEQ.NEXTVAL FROM DUAL";
	
                        	/*	public static final String	RETREIVE_PAYMENT_COMPONENTS_FOR_DISPLAY		= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_DESC, C.SALARY_HEAD_VALUE"  
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='P' AND C.STATUS='A' "; */
                        
       public static final String	RETREIVE_PAYMENT_COMPONENTS_FOR_DISPLAY	 = "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                        + "  AND A.COMPONENT_TYPE=?";
                        
                        /*
                        public static final String	RETREIVE_AGDEDUCTION_COMPONENTS_FOR_DISPLAY	= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_DESC, C.SALARY_HEAD_VALUE"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='A' AND C.STATUS='A'";
                        
                        */
                        
       public static final String	RETREIVE_AGDEDUCTION_COMPONENTS_FOR_DISPLAY = "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                        + "  AND A.COMPONENT_TYPE=?";
                        
                        /*
                        
                        public static final String	RETREIVE_TREASURY_COMPONENTS_FOR_DISPLAY	= "SELECT A.DETAILED_HEAD_ID,B.DETAILED_HEAD_DESC, C.SALARY_HEAD_VALUE"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE='T' AND C.STATUS='A'";
                        */
                        
     public static final String	RETREIVE_TREASURY_COMPONENTS_FOR_DISPLAY	=  "SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                        + "  AND A.COMPONENT_TYPE=?";
                        /* public static final String	SELECTED_OLD_SALARY_COMPONENTS				= "SELECT A.DETAILED_HEAD_ID"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ? AND C.SALARY_TYPE= ? AND C.STATUS ='A'"; */
                        
     public static final String	SELECTED_OLD_SALARY_COMPONENTS = "SELECT A.COMPONENT_ID AS COMPONENT_ID "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                        + "  AND A.COMPONENT_TYPE=?";
                        
    public static final String	UPDATE_SALARY_COMPONENTS					= "UPDATE IGRS_EMP_CADRE_SALARY_MASTER"
                        	+ " SET  STATUS ='D'"
                        	+ " WHERE GRADE_ID = ?"
                        	+ " AND   CADRE_ID = ?"
                        	+ " AND   SALARY_TYPE = ?"
                        	+ " AND   SALARY_HEAD_ID = ?";
                        
                        /* public static final String	SELECTED_OLD_SALARY_COMPONENTS_WITH_STATUS	= "SELECT A.DETAILED_HEAD_ID"
                        	+ " FROM IGRS_EXP_HEAD_MAPPING A,"
                        	+ " IGRS_EXP_DETAILED_HEAD_MASTER B,"
                        	+ " IGRS_EMP_CADRE_SALARY_MASTER C"
                        	+ " WHERE A.MAJOR_HEAD_ID = 'MAJOR_001'"
                        	+ " AND A.SUB_MAJOR_HEAD_ID = 'SUB_MAJOR_001'"
                        	+ " AND A.MINOR_HEAD_ID = 'MINOR_001'"
                        	+ " AND A.SCHEME_ID = 'SCHEME_001'"
                        	+ " AND A.SEGMENT_ID = 'SEG_001'"
                        	+ " AND A.OBJECT_ID = 'OBJ_001'"
                        	+ " AND A.DETAILED_HEAD_ID = B.DETAILED_HEAD_ID"
                        	+ " AND A.DETAILED_HEAD_ID = C.SALARY_HEAD_ID"
                        	+ " AND C.GRADE_ID = ?"
                        	+ " AND C.CADRE_ID = ?  AND C.STATUS ='D'"; */
                        
      public static final String	SELECTED_OLD_SALARY_COMPONENTS_WITH_STATUS  ="SELECT A.COMPONENT_ID AS COMPONENT_ID, B.DETAILED_HEAD_NAME_E AS DETAILED_HEAD_NAME "
                        + "FROM IGRS_EMP_SALARY_COMPONENTS A, IGRS_eXP_DETAILED_HEAD_MASTER B "
                        + "  WHERE A.COMPONENT_ID = B.DETAILED_HEAD_ID "
                        + "  AND A.COMPONENT_TYPE=?";
                        
                        /*public static final String	UPDATE_DEACTIVATE_SALARY_COMPONENTS			= "UPDATE IGRS_EMP_CADRE_SALARY_MASTER"
                        	+ " SET  STATUS ='A',"
                        	+ " SALARY_TYPE = ?"
                        	+ " WHERE GRADE_ID = ?"
                        	+ " AND CADRE_ID = ?"
                        	+ " AND SALARY_HEAD_ID = ?"; */
      public static final String	UPDATE_DEACTIVATE_SALARY_COMPONENTS = " ";
	
}