package com.wipro.igrs.functionDetailedHeadMap.sql;

public class CommonSQL {

	public static final String IGRS_FUNCTION_MAP_LIST_ALL="	select fMaster.FUNCTION_NAME ,dMaster.DETAILED_HEAD_NAME_E ,map.FUNCTION_ID ,map.DETAILED_HEAD_ID ,map.IGRS_EXP_FUNCTION_DH_MAP_ID from IGRS_EXP_DETAILED_HEAD_MASTER dMaster ,IGRS_FUNCTION_MASTER fMaster,IGRS_EXP_FUNCTION_DH_MAP map where map.DETAILED_HEAD_ID = dMaster.DETAILED_HEAD_ID and map.FUNCTION_ID = fMaster.FUNCTION_ID";
	public static final String IGRS_FUNCTION_MAP_DELETE_RECORD="delete from IGRS_EXP_FUNCTION_DH_MAP  where IGRS_EXP_FUNCTION_DH_MAP_ID=?";
	public static final String IGRS_FUNCTION_MAP_INSERT_RECORD="insert into IGRS_EXP_FUNCTION_DH_MAP (FUNCTION_ID,DETAILED_HEAD_ID,IGRS_EXP_FUNCTION_DH_MAP_ID) values (?,?,'FUNC_MAP'||IGRS_EXP_FUNCTION_DH_MAP_SEQ.nextval)";
	public static final String IGRS_FUNCTION_MAP_PREPARE_FUNCTION_ADD_EDIT_PAGE="select fMaster.FUNCTION_NAME ,fMaster.FUNCTION_ID from IGRS_FUNCTION_MASTER fMaster";
	public static final String IGRS_FUNCTION_MAP_PREPARE_HEAD_ADD_EDIT_PAGE="select dMaster.DETAILED_HEAD_NAME_E ,dMaster.DETAILED_HEAD_ID from IGRS_EXP_DETAILED_HEAD_MASTER dMaster";
	public static final String IGRS_FUNCTION_MAP_UPDATE_RECORD="update IGRS_EXP_FUNCTION_DH_MAP set FUNCTION_ID=?,DETAILED_HEAD_ID=? where IGRS_EXP_FUNCTION_DH_MAP_ID=?";
	public static final String IGRS_FUNCTION_MAP_IS_EXIST="select IGRS_EXP_FUNCTION_DH_MAP_ID from IGRS_EXP_FUNCTION_DH_MAP where FUNCTION_ID=? and DETAILED_HEAD_ID=?";
	public static final String IGRS_FUNCTION_MAP_RETRIEVE_RECORD="select FUNCTION_ID , DETAILED_HEAD_ID from IGRS_EXP_FUNCTION_DH_MAP where IGRS_EXP_FUNCTION_DH_MAP_ID=?";
}