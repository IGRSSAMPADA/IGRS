package com.wipro.igrs.loginInternal.sql;

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
	
	
	public static final String GET_NUMBER_ATTEMPTS="SELECT NO_OF_ATTEMPT_COUNT FROM IGRS_USERS_LIST WHERE USER_ID=";
	public static final String INCREMENT_NUMBER_ATTEMPT="UPDATE IGRS_USERS_LIST SET FAILED_ACCESS_TIMESTAMP=sysdate,NO_OF_ATTEMPT_COUNT=? WHERE USER_ID =?";
	public static final String GET_USER_TYPE="SELECT USER_TYPE FROM IGRS_USER_TYPE_MASTER WHERE USER_TYPE_ID =(	SELECT USER_TYPE_ID FROM IGRS_USERS_LIST 	WHERE USER_ID =?)";
	public static final String LOCK_ACCOUNT="UPDATE IGRS_USERS_LIST SET USER_STATUS ='L' WHERE USER_ID =?";
	public static final String GET_OLD_TIMESTAMP="SELECT FAILED_ACCESS_TIMESTAMP FROM  IGRS_USERS_LIST WHERE USER_STATUS='A' AND   USER_ID =";
	public static final String GET_MAPPED_OFFICE_ID="SELECT DISTINCT  offrol.OFFICE_ID FROM  " +
													"IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ," +
													"IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG   " +
													"WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID" +
													" AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  " +
													"UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND UG.USER_ID=?";
	public static final String GET_MAPPED_OFFICES ="SELECT OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_STATUS ='A' AND OFFICE_ID= ";
	
	public static final String RESET_ATTEMPT_COUNT="UPDATE IGRS_USERS_LIST SET USER_STATUS ='A' AND NO_OF_ATTEMPT_COUNT=0 WHERE USER_ID =?";

//Added by Shreeraj
	public static final String GET_SYS_DATE="SELECT SYSDATE FROM DUAL";
}
