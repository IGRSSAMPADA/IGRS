package com.wipro.igrs.areaManagement.sql;

public class areaManagementSQL {

	public static final String SELECT_COUNTRY_LIST = "select country_id,upper(country_name),h_country_name from igrs_country_master where country_status='A'";
	public static final String SELECT_STATE_LIST = "select state_id,upper(state_name),h_state_name from igrs_state_master where state_status='A' and country_id=? and state_id=1";
	public static final String SELECT_DISTRICT_LIST = "SELECT om.DISTRICT_ID, dm.DISTRICT_NAME,dm.H_DISTRICT_NAME FROM IGRS_OFFICE_MASTER om, IGRS_DISTRICT_MASTER dm WHERE state_id=?  AND OFFICE_ID = ? and om.DISTRICT_ID = dm.DISTRICT_ID order by dm.DISTRICT_NAME";
	public static final String SELECT_TEHSIL_LIST = "select tehsil_id,upper(tehsil_name),h_tehsil_name,tehsil_description from igrs_tehsil_master where district_id=? and tehsil_status='A' order by tehsil_name asc";
	public static final String SELECT_AREA_LIST = "select area_type_id,upper(area_type_name),h_area_type_name,AREA_TYPE_DESCRIPTION from igrs_area_type_master where 1=? and area_type_status='A' order by area_type_name asc";
	public static final String SELECT_URBANAREA_LIST = "select area_type_id,upper(area_type_name),h_area_type_name,AREA_TYPE_DESCRIPTION from igrs_area_type_master where 1=? and area_type_id=2 and area_type_status='A' order by area_type_name asc";
	public static final String SELECT_SUBAREA_LIST = "select sub_area_type_id,upper(sub_area_type_name),h_sub_area_type_name,SUB_AREA_TYPE_DESC from igrs_sub_area_type_master where area_type_id=?  and sub_area_type_status='A' and tehsil_id=? order by sub_area_type_name asc";
	public static final String SELECT_RURALSUBAREA_LIST = "select sub_area_type_id,upper(sub_area_type_name),h_sub_area_type_name,SUB_AREA_TYPE_DESC from igrs_sub_area_type_master where area_type_id=? and sub_area_type_status='A' order by sub_area_type_name asc";
	public static final String SELECT_WARD_LIST = "select ward_patwari_id,upper(ward_patwari_name),h_ward_patwari_name,WARD_PATWARI_DESC,WARD_OR_PATWARI from igrs_ward_patwari_master where tehsil_id=? and ward_patwari_status='A' and area_type_id=? and subname=? order by ward_patwari_name asc";
	public static final String SELECT_COLONY_LIST = "select c.colony_id,upper(c.colony_name),c.h_colony_name,COLONY_DESC,upper(a.APPLICABLE_SUBCLAUSE_NAME),a.H_APPLICABLE_SUBCLAUSE_NAME from igrs_colony_master c,igrs_ward_patwari_master w, igrs_subarea_ward_mapping sw, igrs_colony_ward_mapping cw ,IGRS_APPLI_SUBCLAUSE_MASTER a" +
			" where w.tehsil_id=? and c.colony_status='A' and " +
			" w.area_type_id=? and w.subname=?  and w.ward_patwari_id=? and a.APPLICABLE_SUBCLAUSE_ID=c.APPLICABLE_SUBCLAUSE_ID " +
			" and w.ward_patwari_id=sw.ward_patwari_id and sw.SUBAREA_WARD_MAPPING_ID = cw.SUBAREA_WARD_MAPPING_ID and cw.colony_id=c.colony_id " +
			" order by c.colony_name asc";
	public static final String SELECT_CONSTRUCT_RATES = "select RCC_RATE,RBC_RATE,TINSHADE_RATE,KABELU_RATE from IGRS_CONST_RATES_MASTER where STATUS='A' order by APPLICABLE_SUBCLAUSE_ID asc";
	
	public static final String SELECT_SUBCLAUSE_LIST ="select APPLICABLE_SUBCLAUSE_ID,upper(APPLICABLE_SUBCLAUSE_NAME),H_APPLICABLE_SUBCLAUSE_NAME from IGRS_APPLI_SUBCLAUSE_MASTER where 1=? and APPLICABLE_SUBCLAUSE_STATUS='A' order by APPLICABLE_SUBCLAUSE_NAME ";
	public static final String SELECT_MUNICIPAL_BODY_LIST="SELECT MUNICIPAL_BODY_ID,upper(MUNICIPAL_BODY_NAME),H_MUNICIPAL_BODY_NAME  FROM IGRS_MUNICIPAL_BODY_MASTER WHERE MUNICIPAL_BODY_STATUS='A' AND 1=? ORDER BY MUNICIPAL_BODY_NAME ";
	public static final String SELECT_WARD_OR_PATWARI_LIST="SELECT WARD_OR_PATWARI_ID,upper(WARD_OR_PATWARI_NAME),H_WARD_OR_PATWARI_NAME  FROM IGRS_WARD_OR_PATWARI_MASTER WHERE  1=? ORDER BY WARD_OR_PATWARI_NAME ";
	
	public static final String GET_SUB_AREA_ID ="SELECT SUBAREA_WARD_MAPPING_ID FROM IGRS_SUBAREA_WARD_MAPPING WHERE WARD_PATWARI_ID=?";
	public static final String GET_WARD_PATWARI_NAME ="SELECT upper(WARD_PATWARI_NAME) FROM IGRS_WARD_PATWARI_MASTER WHERE WARD_PATWARI_ID=?";
	
	public static final String CHECK_EXIST_TEHSIL ="SELECT COUNT(*) FROM IGRS_TEHSIL_MASTER WHERE (UPPER(TEHSIL_NAME)=? OR h_tehsil_name=?)  AND DISTRICT_ID=? and tehsil_status='A' ";
	public static final String CHECK_EXIST_SUBAREA ="SELECT COUNT(*) FROM IGRS_SUB_AREA_TYPE_MASTER WHERE (UPPER(SUB_AREA_TYPE_NAME)=? OR H_SUB_AREA_TYPE_NAME=?) and TEHSIL_ID=? AND AREA_TYPE_ID=? and sub_area_type_status='A' ";
	public static final String CHECK_EXIST_WARD ="SELECT COUNT(*) FROM IGRS_WARD_PATWARI_MASTER WHERE (UPPER(WARD_PATWARI_NAME)=? OR H_WARD_PATWARI_NAME=?)  AND TEHSIL_ID=? AND AREA_TYPE_ID=? AND SUBNAME=? and ward_patwari_status='A' ";
	public static final String CHECK_EXIST_COLONY ="SELECT COUNT(*) FROM IGRS_COLONY_MASTER WHERE tehid=? and subname=? and upper(ward_patwari_id)=? and (UPPER(COLONY_NAME)=? OR H_COLONY_NAME=?) and colony_status='A' ";
	public static final String CHECK_EXIST_WARD_IN_COLONY="select count(*) from igrs_colony_master where colony_id in (select colony_id from" +
	" IGRS_COLONY_WARD_MAPPING where SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID =?))and colony_status='A'  ";
	
	//insertion scripts
	
	public static final String INSERT_TEHSIL_DATA="insert into igrs_tehsil_master(tehsil_id,tehsil_name,h_tehsil_name,TEHSIL_DESCRIPTION,TEHSIL_STATUS," +
			"CREATED_BY,CREATED_DATE,district_id) values (igrs_tehsil_master_seq.nextval,?,?,?,'A',?,sysdate,?)";
	
	public static final String INSERT_SUBAREA_DATA="INSERT INTO IGRS_SUB_AREA_TYPE_MASTER(SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME,SUB_AREA_TYPE_DESC,SUB_AREA_TYPE_STATUS,CREATED_BY" +
			" ,CREATED_DATE,AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME,TEHSIL_ID,MUNICIPAL_BODY_ID) VALUES(IGRS_SUBAREA_MASTER_SEQ.NEXTVAL,?,?,'A',?,SYSDATE,?,?,?,? )";
	
	public static final String INSERT_WARD_DATA="INSERT INTO IGRS_WARD_PATWARI_MASTER(WARD_PATWARI_ID,WARD_PATWARI_NAME,WARD_PATWARI_DESC,WARD_PATWARI_STATUS,TEHSIL_ID,CREATED_BY,CREATED_DATE,AREA_TYPE_ID, "+
												" WARD_OR_PATWARI,H_WARD_PATWARI_NAME,SUBNAME) VALUES(?,?,?,'A',?,?,SYSDATE,?,?,?,?)";
	
	public static final String INSERT_SUBWARD_DATA="INSERT INTO IGRS_SUBAREA_WARD_MAPPING(SUBAREA_WARD_MAPPING_ID,WARD_PATWARI_ID,SUB_AREA_TYPE_ID," +
			" CREATED_BY,CREATED_DATE,MAPPING_STATUS) VALUES (IGRS_SUBAREAWARD_MAP_SEQ.NEXTVAL,?,?,?,SYSDATE,'A')";
	
	public static final String INSERT_MOHALLA_DATA="INSERT INTO IGRS_COLONY_MASTER (COLONY_ID,COLONY_NAME,COLONY_DESC,COLONY_STATUS,H_COLONY_NAME,CREATED_BY," +
			"CREATED_DATE,APPLICABLE_SUBCLAUSE_ID,SUBNAME,TEHID,WARD_PATWARI_ID) VALUES(?,?,?,'A',?,?,SYSDATE,?,?,?,?)";
	
	public static final String INSERT_COLWARD_DATA="INSERT INTO IGRS_COLONY_WARD_MAPPING(COLONY_WARD_MAPPING_ID,COLONY_ID," +
			" SUBAREA_WARD_MAPPING_ID,CREATED_BY,CREATED_DATE,MAPPING_STATUS) VALUES(IGRS_COLONYWARD_MAP_SEQ.NEXTVAL,?,?,?,SYSDATE,'A')";
	
	public static final String INSERT_CONST_RATES=" Insert into IGRS_CONST_RATES_MASTER (ID,APPLICABLE_SUBCLAUSE_ID,RCC_RATE,RBC_RATE,TINSHADE_RATE,KABELU_RATE,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,STATUS) "+ 
													" values (IGRS_CONST_MASTER_SEQ.NEXTVAL,?,?,?,?,?,?,sysdate,null,null,'A') ";
	
	
	// END OF INSERTION SCRIPTS
	
	//GETTING SEQUENCES//
	
	public static final String GET_WARD_SEQ="SELECT IGRS_WARD_MASTER_SEQ.NEXTVAL FROM DUAL";
	public static final String GET_COLONY_SEQ="SELECT IGRS_COLONY_MASTER_SEQ.NEXTVAL FROM DUAL";
	
	
	//END OF SECTION
	
	
	//DELETION SCRIPTS
	
	public static final String DELETE_TEHSIL ="update igrs_tehsil_master set tehsil_status='D' where tehsil_id in ";
	public static final String DELETE_TEHSIL_SUBAREA_LINKAGE="update igrs_sub_area_type_master  set SUB_AREA_TYPE_STATUS='D' where tehsil_id in ";
	public static final String DELETE_TEHSIL_WARD_LINKAGE="update igrs_ward_patwari_master set WARD_PATWARI_STATUS='D' where tehsil_id in ";
	public static final String DELETE_TEHSIL_SUBAREA_WARD_LINKAGE="update IGRS_SUBAREA_WARD_MAPPING set MAPPING_STATUS='D' where WARD_PATWARI_ID in " +
																	"(select WARD_PATWARI_ID from  igrs_ward_patwari_master where tehsil_id in ";
	public static final String DELETE_TEHSIL_COLONY_LINKAGE="update igrs_colony_master set colony_status='D' where tehid in ";
	public static final String DELETE_TEHSIL_COLONY_WARD_LINKAGE="update IGRS_COLONY_WARD_MAPPING set MAPPING_STATUS='D' where SUBAREA_WARD_MAPPING_ID in " +
																	"(select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID in " +
																	"(select WARD_PATWARI_ID from igrs_ward_patwari_master where tehsil_id in";
	
	public static final String DELETE_SUBAREA ="update igrs_sub_area_type_master set SUB_AREA_TYPE_STATUS='D' where SUB_AREA_TYPE_ID in ";
	public static final String DELETE_SUBAREA_WARD_LINKAGE ="update igrs_ward_patwari_master set WARD_PATWARI_STATUS='D' where SUBNAME IN ";
	public static final String DELETE_SUBAREA_WARD_MAPPING_LINKAGE ="update IGRS_SUBAREA_WARD_MAPPING set MAPPING_STATUS='D' where SUB_AREA_TYPE_ID IN ";
	public static final String DELETE_SUBAREA_COLONY_LINKAGE ="update igrs_colony_master set colony_status='D' where colony id in (select colony_id from" +
			" IGRS_COLONY_WARD_MAPPING where SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where SUBNAME in ";
	public static final String DELETE_SUBAREA_COLONY_MAPPING_LINKAGE ="update IGRS_COLONY_WARD_MAPPING set MAPPING_STATUS='D' where" +
			" SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where SUBNAME in ";
	
	public static final String DELETE_WARD ="update igrs_ward_patwari_master set WARD_PATWARI_STATUS='D' where WARD_PATWARI_ID in ";
	public static final String DELETE_SUBWARD ="update IGRS_SUBAREA_WARD_MAPPING set MAPPING_STATUS='D' where WARD_PATWARI_ID in ";
	public static final String DELETE_WARD_COLONY_LINKAGE ="update igrs_colony_master set colony_status='D' where colony_id in (select colony_id from" +
	" IGRS_COLONY_WARD_MAPPING where SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID in ";
	public static final String DELETE_WARD_COLONY_MAPPING_LINKAGE ="update IGRS_COLONY_WARD_MAPPING set MAPPING_STATUS='D' where" +
	" SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID in ";
	
	
	public static final String DELETE_COLONY ="update igrs_colony_master set colony_status='D' where colony_id in ";
	public static final String DELETE_COLONYWARD ="update IGRS_COLONY_WARD_MAPPING set MAPPING_STATUS='D' where colony_id in ";
	
	
	// END OF SECTION
	 
	
	public static final String SELECT_TEHSIL_DETLS="select tehsil_id,upper(tehsil_name),h_tehsil_name,tehsil_description from igrs_tehsil_master where tehsil_id =?";
	public static final String SELECT_SUBAREA_DETLS="select sub_area_type_id,upper(sub_area_type_name),h_sub_area_type_name,SUB_AREA_TYPE_DESC,MUNICIPAL_BODY_ID from igrs_sub_area_type_master where SUB_AREA_TYPE_ID =?";
	public static final String SELECT_WARD_DETLS="select ward_patwari_id,upper(ward_patwari_name),h_ward_patwari_name,WARD_PATWARI_DESC,WARD_OR_PATWARI from igrs_ward_patwari_master where ward_patwari_id=?";
	public static final String SELECT_COLONY_DETLS="select c.colony_id,upper(c.colony_name),c.h_colony_name,c.COLONY_DESC,c.APPLICABLE_SUBCLAUSE_ID from igrs_colony_master c where c.colony_id=?";
	
	//UPDATE SCRIPTS
	public static final String UPDATE_TEHSIL="update igrs_tehsil_master set tehsil_name=?,h_tehsil_name=?, tehsil_description=? where tehsil_id=?";
	public static final String UPDATE_SUBAREA="update igrs_sub_area_type_master set sub_area_type_name=?,h_sub_area_type_name=?, SUB_AREA_TYPE_DESC=? ,MUNICIPAL_BODY_ID=? where sub_area_type_id=?";
	public static final String UPDATE_WARD="update igrs_ward_patwari_master set ward_patwari_name=?,h_ward_patwari_name=?, WARD_PATWARI_DESC=? ,WARD_OR_PATWARI=? where ward_patwari_id=?";
	public static final String UPDATE_COLONY_WARD="update igrs_colony_master set ward_patwari_id=? where colony_id in (select colony_id from" +
	" IGRS_COLONY_WARD_MAPPING where SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID =?)) ";
	public static final String UPDATE_MOHALLA="update igrs_colony_master set colony_name=?,h_colony_name=?, COLONY_DESC=?,APPLICABLE_SUBCLAUSE_ID=? where colony_id=?";
	public static final String UPDATE_MOHALLA_ALL="update igrs_colony_master set APPLICABLE_SUBCLAUSE_ID=? where colony_id in (select colony_id from" +
	" IGRS_COLONY_WARD_MAPPING where SUBAREA_WARD_MAPPING_ID in (select SUBAREA_WARD_MAPPING_ID from IGRS_SUBAREA_WARD_MAPPING where WARD_PATWARI_ID =?))";
	
	public static final String DEACT_CONST_RATES ="UPDATE IGRS_CONST_RATES_MASTER SET STATUS='D'";
	public static final String CHK_EXIST_MOHALLA_CHILD1 ="SELECT COUNT(*) FROM IGRS_GUIDELINE_MASTER_CHILD1 WHERE COLONY_ID=?";
	public static final String CHK_EXIST_wardPatwari_CHILD1 ="SELECT COUNT(*) FROM IGRS_GUIDELINE_MASTER_CHILD1 WHERE WARD_PATWARI_ID=?";
	public static final String CHK_EXIST_WARD_CHILD1 ="SELECT COUNT(*) FROM IGRS_GUIDELINE_MASTER_CHILD1 WHERE TEHSIL_ID=? AND AREA_TYPE_ID=? AND sub_area_type_id=?";
	//END OF SECTION
		
	
			
}
