/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonSQL.java
 * Author      :   Nihar Ranjan Mishra 
 * Description :   Represents the common SQL File for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Nihar R Mishra  28th Dec, 2007	 		 
 * --------------------------------------------------------------------------------
 */
  
  
package com.wipro.igrs.UserRegistration.sql;


 /**
 * @author nihraa
 *
 */
public class CommonSQL {

     /**
     * USER_CREDENTIALS_INSERT
     */
    public static final String USER_CREDENTIALS_INSERT = "INSERT INTO"
	    + " IGRS_USERS_LIST(USER_ID,USER_TYPE_ID,CREATED_BY,CREATED_DATE,USER_STATUS)" 
	    + " VALUES(?,(select user_type_id from igrs_user_type_master where user_type_name=?),?,sysdate,'A') ";
     
    
//  public static final String USER_ROLE_INSERT = "INSERT INTO"     
//	    + " IGRS_USER_ROLE_GROUP_MAPPING(USER_ID,ROLE_GROUP_ID,ROLE_ID)" 
//	    + " VALUES(?,(SELECT distinct ROLE_GROUP_ID FROM IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME=?),(SELECT distinct ROLE_ID FROM IGRS_ROLE_MASTER WHERE ROLE_NAME=?)) ";
//  above code commented by Roopam for query correction.    
    
    public static final String USER_ROLE_INSERT = "INSERT INTO"
	    + " IGRS_USER_ROLE_GROUP_MAPPING(USER_ID,ROLE_GROUP_ID,ROLE_ID,ROLE_ACTIVE)" 
	    + " VALUES(?,(SELECT distinct ROLE_GROUP_ID FROM IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_NAME=?),(SELECT distinct ROLE_ID FROM IGRS_ROLE_MASTER WHERE ROLE_NAME=?),'A') ";

     
     
     //Insert User Registration Details into the Database.
     /**
     * USER_DETAILS_INSERT
     */
    public static final String USER_REG_DETAILS_INSERT = "INSERT INTO IGRS_USER_REG_DETAILS" +
    		"(USER_TXN_ID, FIRST_NAME, MIDDLE_NAME , LAST_NAME, GENDER,OCCUPATION,AGE," +		//AGE REPLACES DOB
    		" GUARDIAN_NAME, MOTHER_NAME,SPOUSE_NAME, ADDRESS,COUNTRY_ID,STATE_ID,DISTRICT_ID," +
    		" POSTAL_CODE, PHONE_NUMBER ,MOBILE_NUMBER,EMAIL_ID, ALTERNATE_EMAIL_ID, PHOTO_PROOF_TYPE_ID," +
    		" PHOTO_PROOF_ID,BANK_NAME,BANK_ADDRESS,REGISTRATION_ID,USER_ID,USER_PASSWORD," +
    		" USER_HINT_QUESTION_ID, USER_HINT_ANSWER,USER_STATUS, CREATED_BY,CREATED_DATE,CATEGORY_ID,MINORITY) " +
    		" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?) ";
    
    
    /**
     * USER_DETAILS_FETCH_QUERY
     */
    //Retrieve User Details from the table for SP Licensing.
	public static final String USER_DETAILS_FETCH_QUERY = "SELECT USER_ID,"
		+ " FIRST_NAME,"
    	 + "MIDDLE_NAME,LAST_NAME,GENDER,FATHER_NAME,MOTHER_NAME, SPOUSE_NAME"
		     + ",ADDRESS, " 
        + "COUNTRY,STATE,POSTAL_CODE,PHONE_NUMBER,EMAIL_ID,MOBILE_NUMBER,"
        + "BIRTH_DATE FROM IGRS_REGISTERED_USER_EXTERNAL WHERE USER_ID=?";
    
	
	/**
     * USER_REF_ID_DETAILS_FETCH_QUERY
     */
    //HYPERLINK.
	public static final String USER_REF_ID_DETAILS_FETCH_QUERY = "SELECT"
		+ " USER_ID, FIRST_NAME,"
    	 + "MIDDLE_NAME,LAST_NAME,GENDER,FATHER_NAME,MOTHER_NAME, SPOUSE_NAME"
		     + ",ADDRESS, " 
    	 + "COUNTRY,STATE,POSTAL_CODE,PHONE_NUMBER,EMAIL_ID,MOBILE_NUMBER," 
    	 + "to_char((BIRTH_DATE),'dd/mm/yyyy'), ALT_EMAIL FROM"
		     + " IGRS_REGISTERED_USER_EXTERNAL WHERE USER_ID=" 
    	 + "(select USER_ID from IGRS_REGISTERED_SP_LICENSE where REF_ID = ?)";
      
	
	
	/**
     * USER_LICENSE_DETAILS_FETCH_QUERY
     */
    //HYPERLINK.
	public static final String USER_LICENSE_DETAILS_FETCH_QUERY = "SELECT"
		+ " to_char((DURATION_FROM),'dd/mm/yyyy'), to_char((DURATION_TO),'dd"
			+ "/mm/yyyy'),"
    	 + "to_char((ISSUANCE_DATE),'dd/mm/yyyy'),LICENSE_NO,OFFICIAL_ADDRESS,"
    	 + "OFFICIAL_CITY, OTHER_INFM FROM IGRS_REGISTERED_SP_LICENSE WHERE"
		     + " USER_ID=" 
    	 + "(select USER_ID from IGRS_REGISTERED_SP_LICENSE where REF_ID = ?)";
      
      
	
	
	
	/**
     * USER_SP_LICENSING_DETAILS_INSERT
     */
    public static final String 
	USER_SP_LICENSING_DETAILS_INSERT = "INSERT INTO"
		+ " IGRS_REGISTERED_SP_LICENSE(REF_ID, USER_ID, "
    	 + "DURATION_FROM, DURATION_TO, ISSUANCE_DATE, LICENSE_NO,"
		     + " OFFICIAL_ADDRESS, " 
    	 + "OFFICIAL_CITY,  OTHER_INFM" 
    	 + ") " 
    	 +  "VALUES(?,?,?,?,?,?,?,?,?) ";
     
    
    /**
     * USER_SP_LICENSE_VIEW
     */
    public static final String 
	USER_SP_LICENSE_VIEW = "SELECT REF_ID, USER_ID, FIRST_NAME, LICENSE_NO"
		+ " FROM IGRS_REGISTERED_SP_LICENSE WHERE  ";
    
    
    /**
     * USER_VALIDATION_QUERY
     */
    public static final String
    USER_VALIDATION_QUERY = "SELECT UPPER(USER_ID) FROM IGRS_USERS_LIST where UPPER(USER_ID) = UPPER(?)";
    
    /**
     * USER_GENDER_TYPE
     */
    public static final String
    USER_GENDER_TYPE = "SELECT GENDER FROM IGRS_REGISTERED_USER_EXTERNAL where"
	    + " USER_ID = ?";
    
    /**
     * USER_ROLE_ID_INSERT
     */ 
    public static final String
    USER_ROLE_ID_INSERT = "INSERT INTO IGRS_USER_LIST(USER_ID, USER_ROLE_ID)"
	    + " VALUES(?,?)";
    
    /**
     * USER_DETAILS_UPDATE
     */
	public static final String
    USER_DETAILS_UPDATE = "UPDATE IGRS_REGISTERED_USER_EXTERNAL SET FIRST_NAME"
	    + " = ? WHERE USER_ID = ?";
	
	
	/**
     * USER_LICENSED_ROLE_UPDATE
     */
	public static final String
	USER_LICENSED_ROLE_UPDATE = "UPDATE IGRS_USER_LIST SET USER_ROLE_ID = ?"
		+ " WHERE USER_ID = ?";
	
	public static final String DISTRICT_CITY_QUERY = "Select dist.DISTRICT_ID, dist.DISTRICT_NAME from IGRS_DISTRICT_MASTER dist, IGRS_STATE_MASTER state" +
													 " Where state.STATE_ID = dist.STATE_ID and state.STATE_ID = ? AND dist.DISTRICT_STATUS='A'";
	
	public static final String STATE_QUERY = " Select stm.STATE_ID, stm.STATE_NAME from IGRS_STATE_MASTER stm,IGRS_COUNTRY_MASTER cm" +
											 " Where stm.COUNTRY_ID = cm.COUNTRY_ID and cm.COUNTRY_ID =?";
	
	public static final String UPDATE_PSWD_CREDENTIALS = "update IGRS_USER_REG_DETAILS set USER_PASSWORD = ? where USER_ID =? and USER_PASSWORD = ?";


	public static final String USER_PSWD_QUERY = " Select urg.USER_ID, urg.USER_PASSWORD from IGRS_USER_REG_DETAILS urg,IGRS_USERS_LIST usrl" +
	 " Where urg.USER_ID = usrl.USER_ID and urg.USER_ID =?";
	
	
	/*Queries Starting for the ACL Module*/
	
	 //Insert User Registration Details into the Database.
    /**
    * INSERT_ROLES
    */
   public static final String INSERT_ROLES = "INSERT INTO IGRS_ROLE_MASTER" +
   		"(ROLE_ID, ROLE_NAME, ROLE_DESCRIPTION , ROLE_STATUS, CREATED_BY, CREATED_DATE) VALUES(?,?,?,?,?,sysdate) ";
   
   /**
    * SEARCH_ROLES_DETAILS
    */
   public static final String SEARCH_ROLES_DETAILS = "Select UPPER(ROLE_NAME), ROLE_DESCRIPTION, ROLE_STATUS,ROLE_ID" +
   													 " from IGRS_ROLE_MASTER WHERE ROLE_STATUS = ?" +
   													 " or ROLE_STATUS = ? ORDER BY UPPER(ROLE_NAME)";
   
   public static final String SEARCH_PENDING_ROLES_DETAILS = "Select UPPER(ROLE_NAME), ROLE_DESCRIPTION, ROLE_STATUS,ROLE_ID" +
				 " from IGRS_ROLE_MASTER WHERE ROLE_STATUS = ? ORDER BY UPPER(ROLE_NAME)";
   
   
   /**
    * INSERT_GROUPS
    */
   public static final String INSERT_GROUPS = "INSERT INTO IGRS_ROLE_GROUP_MASTER" +
									"(ROLE_GROUP_ID, ROLE_GROUP_NAME, ROLE_GROUP_DESCRIPTION , ROLE_GROUP_STATUS, " +
									" CREATED_BY, CREATED_DATE) VALUES(?,?,?,?,?,sysdate) ";
   
   /**
    * SEARCH_GROUPS_DETAILS
    */
   public static final String SEARCH_GROUPS_DETAILS = "Select UPPER(ROLE_GROUP_NAME), ROLE_GROUP_DESCRIPTION, ROLE_GROUP_STATUS," +
   									" ROLE_GROUP_ID from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_STATUS = ? or ROLE_GROUP_STATUS = ? ORDER BY UPPER(ROLE_GROUP_NAME) ";
   
   public static final String SEARCH_PENDING_GROUPS_DETAILS = "Select UPPER(ROLE_GROUP_NAME), ROLE_GROUP_DESCRIPTION, ROLE_GROUP_STATUS," +
				" ROLE_GROUP_ID from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_STATUS = ? ORDER BY UPPER(ROLE_GROUP_NAME) ";
   
   
   /**
    * SEARCH_ACTIVE_ROLES
    */
   public static final String SEARCH_ACTIVE_ROLES = "Select distinct ROLE_ID,NVL(trim(ROLE_NAME),'NA') " +
   													 " from IGRS_ROLE_MASTER WHERE ROLE_STATUS = ?";
   
   
   /**
    * SEARCH_ACTIVE_ROLEGROUPS
    */
   public static final String SEARCH_ACTIVE_ROLEGROUPS = "Select distinct ROLE_GROUP_ID,UPPER(ROLE_GROUP_NAME) " +
   													 " from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_STATUS = ? ORDER BY UPPER(ROLE_GROUP_NAME) ";
   
   /**
    * INSERT_ROLE_ACTIVITY_MAPPING
    */
   public static final String INSERT_ROLE_ACTIVITY_MAPPING = "INSERT INTO IGRS_ROLE_FN_ACTIVITY_MAPPING" +
									"(ROLE_ID , ACTIVITY_ID)VALUES(?,?) ";
   
   /**
    * INSERT_GRP_ROLE_MAPPING
    */
   public static final String INSERT_GRP_ROLE_MAPPING = "INSERT INTO IGRS_RGROUP_ROLE_MAPPING " +
   		"(IGRS_RGROUP_ROLE_MAPPING_ID, ROLE_GROUP_ID , ROLE_OFFICE_MAP_ID,STATUS)" +
   		"VALUES(IGRS_RGROUP_ROLE_MAPPING_SEQ.NEXTVAL,?,?,'A') ";
   
   /**
    * INSERT_GRP_ROLE_MAPPING
    */
   public static final String INSERT_GRP_USER_MAPPING = "INSERT INTO IGRS_USER_ROLE_GROUP_MAPPING" +
									"(USER_ID, ROLE_GROUP_ID, ROLE_ACTIVE )VALUES(?,?,?) ";
   
   /**
    * SEARCH_ROLES_DETAILS
    */
   public static final String SEARCH_ROLE_DETAILS = "Select ROLE_NAME, ROLE_DESCRIPTION, ROLE_STATUS,ROLE_ID" +
   													 " from IGRS_ROLE_MASTER WHERE ROLE_ID = ?" ;
   
   public static final String UPDATE_ROLE_MASTER_DETAILS = "update IGRS_ROLE_MASTER set ROLE_NAME = ?, ROLE_DESCRIPTION = ?," +
   															" ROLE_STATUS = ? , UPDATE_BY = ?, UPDATE_DATE = sysdate where ROLE_ID =? ";
   /**
    * SEARCH_GRP_DETAILS
    */
   public static final String SEARCH_GRP_DETAILS = "Select ROLE_GROUP_NAME, ROLE_GROUP_DESCRIPTION, ROLE_GROUP_STATUS," +
   												"ROLE_GROUP_ID from IGRS_ROLE_GROUP_MASTER WHERE ROLE_GROUP_ID = ?" ;
   
   
   /**
    * UPDATE_ROLE_GRP_MASTER_DETLS
    */
   
   public static final String UPDATE_ROLE_GRP_MASTER_DETLS = "update IGRS_ROLE_GROUP_MASTER set ROLE_GROUP_NAME = ?, ROLE_GROUP_DESCRIPTION = ?," +
   															" ROLE_GROUP_STATUS = ? , UPDATE_BY = ?, UPDATE_DATE = sysdate where ROLE_GROUP_ID =? ";
   							
   /**
    * SEARCH_USER_DETAILS
    */
   
   public static final String SEARCH_USER_DETAILS ="Select * from IGRS_USER_REG_DETAILS where " +
   													" USER_ID=? and USER_PASSWORD = ? ";
   
   
   /**
    * SEARCH_USER_GROUP
    */
   
   public static final String SEARCH_USER_GROUP=" select distinct ugrp.ROLE_GROUP_ID, grp.ROLE_GROUP_NAME from IGRS_USER_ROLE_GROUP_MAPPING ugrp, " +
   												" IGRS_ROLE_GROUP_MASTER grp where user_id= ? and ugrp.ROLE_GROUP_ID =grp.ROLE_GROUP_ID ";
   /**
    * SEARCH_ROLE_GROUP
    */
  public static final String SEARCH_ROLE_GROUP = "select distinct rmast.ROLE_OFFICE_MAP_ID, rol.ROLE_NAME||' - '||offc.OFFICE_NAME, rgrp.ROLE_GROUP_ID "+
" from IGRS_RGROUP_ROLE_MAPPING rgrp, IGRS_ROLE_OFFICE_MAPPING  rmast ,IGRS_ROLE_MASTER rol ,IGRS_OFFICE_MASTER offc where rgrp.ROLE_GROUP_ID =? and rgrp.ROLE_OFFICE_MAP_ID = rmast.ROLE_OFFICE_MAP_ID "+
" AND rmast.role_id=rol.role_id and rmast.office_id=offc.office_id and rgrp.STATUS='A' and rol.role_status='A' AND rmast.STATUS='A'  ";
  // AND rmast.STATUS='A' IN ABOVE QUERY(SEARCH_ROLE_GROUP) ADDED BY ROOPAM-10MARCH2015
  /**
   * SEARCH_ROLE_ACTIVITY
   */
 public static final String SEARCH_ROLE_ACTIVITY = " Select  actmast.activity_id, actmast.ACTIVITY_NAME, rgmap.ROLE_ID,rgmap.ROLE_GROUP_ID " +
 												   " from IGRS_ROLE_FN_ACTIVITY_MAPPING rfn, IGRS_RGROUP_ROLE_MAPPING rgmap, IGRS_ACTIVITY_MASTER actmast where "+
	 											   " rfn.ROLE_ID = rgmap.ROLE_ID  and  actmast.ACTIVITY_ID = rfn.ACTIVITY_ID  and rfn.ROLE_ID =? and " +
	 											   " rgmap.ROLE_GROUP_ID =? ";

 /**
  * SEARCH_ROLE_MODULE
  */
public static final String SEARCH_ROLE_MODULE = "  Select distinct modmast.Module_ID , modmast.MODULE_NAME from " +
												"  IGRS_ROLE_FN_ACTIVITY_MAPPING rfn, IGRS_MODULE_MASTER modmast where rfn.ROLE_ID = rfn.ROLE_ID " +
												"  and  modmast.MODULE_ID = rfn.MODULE_ID   ";

/**
 * SEARCH_SUB_MODULE
 */
public static final String SEARCH_SUB_MODULE =  " Select distinct submod.SUB_MODULE_ID, submod.SUB_MODULE_NAME from " +
												" IGRS_ROLE_FN_ACTIVITY_MAPPING rfn, IGRS_SUB_MODULE_MASTER submod where " +
												" submod.SUB_MODULE_ID = rfn.SUB_MODULE_ID   ";

/**
 * SEARCH_FUNCTION
 */
public static final String SEARCH_FUNCTION = " Select distinct fnc.FUNCTION_ID, fnc.FUNCTION_NAME from IGRS_ROLE_FN_ACTIVITY_MAPPING rfn, " +
											 " IGRS_FUNCTION_MASTER fnc where fnc.FUNCTION_ID = rfn.FUNCTION_ID  " ;

/**
 * SEARCH_FUNCTION
 */
public static final String SEARCH_ACTIVITIES = " Select distinct act.activity_id, act.activity_name from IGRS_ROLE_FN_ACTIVITY_MAPPING rfn," +
											   " IGRS_ACTIVITY_MASTER act where act.activity_id = rfn.activity_id   ";
/**
 * SEARCH_EMP_DETAILS
 */

public static final String SEARCH_EMP_DETAILS = " Select * from IGRS_EMP_MASTER where EMP_ID=? and EMP_PASSWORD = ? ";


/**
 * @author Madan Mohan
 */
public static final String COUNTRY_QUERY = "Select COUNTRY_ID, " 
    		+ "COUNTRY_NAME from IGRS_COUNTRY_MASTER " 
    		+"WHERE COUNTRY_STATUS='A'";
/**
 * @author Madan Mohan
 */
/*public static final String STATE_QUERY = "Select STATE_ID, " 
			+"STATE_NAME from IGRS_STATE_MASTER " 
			+"Where COUNTRY_ID=?";
*//**
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
 */
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
		+"IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?)";
/**
 * @author Madan Mohan
 */
public static final String PROPERTY_VALUATION_PROCEDURE = 
	"CALL IGRS_PROPERTY_VALUATION_PKG." 
	+"IGRS_PROP_VAL_CALC_PROC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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

/**
 * @author Roopam Mehta
 */
//public static final String UPDATE_USER_PASSWORD ="UPDATE IGRS_USER_REG_DETAILS SET USER_PASSWORD=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE USER_ID=?";
public static final String CALL_UPDATE_USER_PASSWORD ="CALL IGRS_UPDATE_PASWRD_PROC(?,?,?,?)";

/**
 * @author Roopam Mehta
 */
//public static final String UPDATE_USER_PASSWORD ="UPDATE IGRS_USER_REG_DETAILS SET USER_PASSWORD=?,UPDATE_BY=?,UPDATE_DATE=sysdate WHERE USER_ID=?";
public static final String INSERT_RESET_PASWRD_REQ_DETLS ="INSERT INTO IGRS_PASWRD_RESET_REQ_DETAIL(USER_ID,RESET_PASSWRD_KEY,BLOCKD_USER_RESET_REQ_FLAG,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,sysdate)";
/**
 * @author Roopam Mehta
 */
public static final String CALL_IGRS_INSERT_EMAIL_DATA="call IGRS_INSERT_EMAIL_DATA(?,?,?,?)";
/**
 * @author Roopam Mehta
 */
public static final String USER_ID_RESET_KEY="SELECT USER_ID FROM IGRS_PASWRD_RESET_REQ_DETAIL WHERE RESET_PASSWRD_KEY=?";

public static final String IS_EMP_AVAIL="select User_id from igrs_user_reg_details where user_id=? and User_status='A'";


public static final String IS_EMP_MAPPED="select count(user_id) user_cnt from IGRS_USER_ROLE_GROUP_MAPPING where user_id=?";

public static final String DLT_EMP_MAPPED="delete from IGRS_USER_ROLE_GROUP_MAPPING where user_Id=?";

public static final String ROLES_UNDER_GROUP="select role_id from igrs_rgroup_role_mapping where role_group_id=?";

public static final String SEARCH_ROLE_GROUP_ID="select role_group_id from igrs_role_group_master where role_group_name=?";

public static final String ACTIVITY_COUNT_ROLE="select count(1) from IGRS_ROLE_FN_ACTIVITY_MAPPING where role_id=?";


public static final String ROLE_GROUP_UNDER_USER="Select role_group_id from igrs_user_role_group_mapping where user_id=?";

public static final String IS_GRP_ROLE_MAPPED="select count(role_group_id) group_cnt from igrs_rgroup_role_mapping where role_group_id in (Select role_group_id from igrs_role_group_master where role_group_name=?)";

public static final String DLT_GRP_ROLE_MAPPED="delete from igrs_rgroup_role_mapping where role_group_id=?";


public static final String GET_ALL_OFFICE_LIST = "SELECT OFFICE_ID, OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_STATUS = 'A'";


public static final String DELETE_ROLE_OFFICE_MAPPING = "DELETE FROM IGRS_ROLE_OFFICE_MAPPING WHERE ROLE_ID = ?";

public static final String SELECT_ROLE_OFFICE_MAPPING = "SELECT ROLE_OFFICE_MAP_ID FROM IGRS_ROLE_OFFICE_MAPPING WHERE ROLE_ID = ? and OFFICE_ID=? and STATUS='A'";


public static final String INSERT_ROLE_OFFICE_MAPPING = "INSERT INTO IGRS_ROLE_OFFICE_MAPPING " +
		"( ROLE_OFFICE_MAP_ID, ROLE_ID, OFFICE_ID, STATUS, CREATED_BY, CREATED_DATE ) VALUES " +
		"( IGRS_ROLE_OFFICE_SEQ.NEXTVAL, ?, ?, 'A', ?, SYSDATE )";


public static final String GET_ROLE_OFFICE_MAPPING = "SELECT OFFICE_ID FROM IGRS_ROLE_OFFICE_MAPPING WHERE ROLE_ID = ? AND STATUS='A'";

//added by roopam
public static final String GET_USER_TXN_ID_SEQ="SELECT IGRS_USER_TXN_SEQ.NEXTVAL FROM DUAL";
public static final String GET_OCCUPATION_LIST = "SELECT O.OCCUPATION_ID,O.OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER O ORDER BY O.OCCUPATION_ID ASC";


public static final String GET_ROLE_MAPPED_TO_OFFICE = "select ROLE_ID from  IGRS_ROLE_OFFICE_MAPPING where STATUS='A' and OFFICE_ID =";

//added by Shreeraj
//ADDED BY SHREERAJ
public static final String SELECT_PHOTO_PROOF_TYPE="SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME " +
													"FROM IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE PHOTO_PROOF_TYPE_STATUS='A' "+
													"ORDER BY PHOTO_PROOF_TYPE_ID ASC";

public static final String SELECT_QUESTION="SELECT QUESTION_ID,QUESTION_TEXT FROM IGRS_USER_HINT_QUESTIONS WHERE QUESTION_STATUS='A'";
public static final String SELECT_CATEGORY = "SELECT A.CATEGORY_ID,A.CATEGORY_NAME FROM IGRS_PERSON_CATEGORY_MASTER A WHERE A.CATEGORY_STATUS='A' ORDER BY A.CATEGORY_ID DESC";
public static final String CURRENT_DATE_TIME="select sysdate from dual";

public static final String GET_OCCUPATION_LIST_HINDI = "SELECT O.OCCUPATION_ID,O.OCCUPATION_NAME,O.H_OCCUPATION_NAME FROM IGRS_OCCUPATION_MASTER O ORDER BY O.OCCUPATION_ID ASC";
public static final String SELECT_CATEGORY_HINDI = "SELECT A.CATEGORY_ID,A.CATEGORY_NAME,A.H_CATEGORY_NAME FROM IGRS_PERSON_CATEGORY_MASTER A WHERE A.CATEGORY_STATUS='A' ORDER BY A.CATEGORY_ID DESC";
public static final String SELECT_PHOTO_PROOF_TYPE_HINDI="SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME,H_PHOTO_PROOF_TYPE_NAME " +
														"FROM IGRS_PHOTOID_PROOF_TYPE_MASTER WHERE PHOTO_PROOF_TYPE_STATUS='A' "+
														"ORDER BY PHOTO_PROOF_TYPE_ID ASC";
public static final String SELECT_QUESTION_HINDI="SELECT QUESTION_ID,QUESTION_TEXT,H_QUESTION_TEXT FROM IGRS_USER_HINT_QUESTIONS WHERE QUESTION_STATUS='A'";

public static final String DELETE_GROUP_ENTIRES="DELETE FROM IGRS_RGROUP_ROLE_MAPPING WHERE  ROLE_GROUP_ID=? and STATUS='A' ";
}
