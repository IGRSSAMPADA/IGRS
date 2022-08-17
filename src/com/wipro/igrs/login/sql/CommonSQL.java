package com.wipro.igrs.login.sql;

public class CommonSQL {
	public static final String UPDATE_USER_LIST_LOGIN = 
		"UPDATE IGRS_USERS_LIST SET "
		+"USER_LOGIN_STATUS = ?, "
		+"USER_LOGIN_TIME=SYSDATE WHERE USER_ID =?";
	
	public static final String SELECT_ROLE_NAME = 
		"SELECT ROLE_ID,ROLE_NAME FROM IGRS_ROLE_MASTER "
		+"WHERE ROLE_STATUS='A' ORDER BY ROLE_NAME ASC";
	
	public static final String CHECK_PROBATION = 
			"SELECT COUNT(*) FROM IGRS_EMP_OFFICIAL_DETAILS WHERE "		
		+"(IGRS_EMP_OFFICIAL_DETAILS.EMP_STATUS = 'A') ";
	
	public static final String GET_EMP_DESIGNATION = "SELECT EM.FIRST_NAME, EM.MIDDLE_NAME, EM.LAST_NAME, CM.CADRE_NAME FROM IGRS_EMP_OFFICIAL_DETAILS EO, IGRS_EMP_MASTER EM, IGRS_EMP_CADRE_MASTER CM, IGRS_USERS_LIST UL, IGRS_USER_REG_DETAILS UR WHERE EO.EMP_ID = EM.EMP_ID AND EO.EMP_DESIGNATION_ID = CM.CADRE_ID AND UL.USER_ID = EM.EMP_ID AND UR.USER_ID = UL.USER_ID AND UR.USER_ID = ?";
	
	/* FOLLOWING ADDED BY ROOPAM
	 * FOR GETTING OFFICE ID OF LOGGED IN USER FROM DATABASE
	 */
	public static final String GET_OFFICE_ID="SELECT A.OFFICE_ID FROM IGRS_ROLE_OFFICE_MAPPING A,IGRS_RGROUP_ROLE_MAPPING B,IGRS_USER_ROLE_GROUP_MAPPING C WHERE A.ROLE_ID=B.ROLE_ID AND B.ROLE_GROUP_ID=C.ROLE_GROUP_ID AND C.USER_ID=";
	public static final String GET_ROLE_ID_LIST="SELECT DISTINCT RM.ROLE_ID, RM.ROLE_NAME FROM   "+
			" IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM, IGRS_RGROUP_ROLE_MAPPING GP, IGRS_USER_ROLE_GROUP_MAPPING UG "+
							" ,IGRS_OFFICE_MASTER OFF, IGRS_ROLE_OFFICE_MAPPING OFFROL  "+
					" WHERE RM.ROLE_ID = OFFROL.ROLE_ID AND GM.ROLE_GROUP_ID = GP.ROLE_GROUP_ID AND  "+
					" OFFROL.ROLE_OFFICE_MAP_ID=GP.ROLE_OFFICE_MAP_ID "+
					" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  "+
					" UG.ROLE_GROUP_ID = GM.ROLE_GROUP_ID AND UG.USER_ID = ";
	public static final String GET_OFFICE_DIST_ID_LIST="SELECT B.OFFICE_ID,A.DISTRICT_ID FROM IGRS_OFFICE_MASTER A, IGRS_ROLE_OFFICE_MAPPING B WHERE A.OFFICE_ID=B.OFFICE_ID AND B.ROLE_ID=";
	
	
	public static final String GET_NUMBER_ATTEMPTS="SELECT NO_OF_ATTEMPT_COUNT,USER_TYPE_ID FROM IGRS_USERS_LIST WHERE USER_ID=";
	public static final String INCREMENT_NUMBER_ATTEMPT="UPDATE IGRS_USERS_LIST SET FAILED_ACCESS_TIMESTAMP=sysdate,NO_OF_ATTEMPT_COUNT=? WHERE USER_ID =?";
	public static final String GET_USER_TYPE="SELECT USER_TYPE FROM IGRS_USER_TYPE_MASTER WHERE USER_TYPE_ID =(	SELECT USER_TYPE_ID FROM IGRS_USERS_LIST 	WHERE USER_ID =?)";
	public static final String LOCK_ACCOUNT="UPDATE IGRS_USERS_LIST SET USER_STATUS ='L' WHERE USER_ID =?";
	public static final String GET_OLD_TIMESTAMP="SELECT TO_CHAR(FAILED_ACCESS_TIMESTAMP,'dd/mm/yyyy hh:mm:ss') FROM  IGRS_USERS_LIST WHERE USER_STATUS='A' AND   USER_ID =";
	public static final String GET_MAPPED_OFFICE_ID="SELECT DISTINCT  offrol.OFFICE_ID FROM  " +
													"IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ," +
													"IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG   " +
													"WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID" +
													" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  " +
													"UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND offrol.STATUS='A' AND RG.STATUS='A' AND UG.USER_ID=?";
	//AND offrol.STATUS='A' ADDED BY ROOPAM-10MARCH2015 IN GET_MAPPED_OFFICE_ID
	public static final String GET_MAPPED_OFFICES ="SELECT OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_STATUS ='A' AND OFFICE_ID=? ";
	
	public static final String RESET_ATTEMPT_COUNT="UPDATE IGRS_USERS_LIST SET USER_STATUS ='A' AND NO_OF_ATTEMPT_COUNT=0 WHERE USER_ID =?";

//Added by Shreeraj
	public static final String GET_SYS_DATE="SELECT TO_CHAR(SYSDATE,'dd/mm/yyyy hh:mm:ss') FROM DUAL";
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

public static final String GET_OFFC_NAME = "select office_name from igrs_office_master where office_id = ?";

public static final String GET_OFFC_NAME_HI = "select h_office_name from igrs_office_master where office_id = ?";

public static final String GET_DESIGNATION_DETLS = "Select Cadre_name from igrs_emp_cadre_master mas, igrs_emp_official_details " +
		"offc where offc.emp_id = ? and offc.emp_designation_id = mas.cadre_id ";

public static final String GET_DESIGNATION_DETLS_HI = "Select h_Cadre_name from igrs_emp_cadre_master mas, igrs_emp_official_details " +
"offc where offc.emp_id = ? and offc.emp_designation_id = mas.cadre_id ";

public static final String GET_LOGGED_IN_USER_DETLS = "SELECT DISTINCT FIRST_NAME,MIDDLE_NAME,LAST_NAME,SIGNATURE_PATH,PEN_TYPE FROM IGRS_USER_REG_DETAILS WHERE USER_ID = ?";
}
