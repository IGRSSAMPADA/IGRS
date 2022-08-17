package com.wipro.igrs.newPropvaluationefiling.sql;

public class PropertyValuationSQL {

	public static String TEHSIL_NAME_EN = "SELECT TEHSIL_ID,TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE DISTRICT_ID=? AND TEHSIL_STATUS='A'";

	public static String TEHSIL_NAME_HI = "SELECT TEHSIL_ID,H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE DISTRICT_ID=? AND TEHSIL_STATUS='A'";

	public static String AREA_NAME_EN = "SELECT AREA_TYPE_ID,AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE  AREA_TYPE_STATUS='A'";

	public static String AREA_NAME_HI = "SELECT AREA_TYPE_ID,H_AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_STATUS='A'";

	public static String AREA_NAME_EN_PARTN = "SELECT AREA_TYPE_ID,AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE  AREA_TYPE_STATUS='A' AND AREA_TYPE_ID=1";

	public static String AREA_NAME_HI_PARTN = "SELECT AREA_TYPE_ID,H_AREA_TYPE_NAME FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_STATUS='A' AND AREA_TYPE_ID=1";

	public static String SUB_AREA_NAME_EN = "SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

	public static String SUB_AREA_NAME_HI = "SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

	public static String SUB_AREA_NAME_EN_UR = "SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

	public static String SUB_AREA_NAME_HI_UR = "SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

	public static String WARD_PATWARI_NAME_EN = "SELECT WPM.WARD_PATWARI_ID,WPM.WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

	public static String WARD_PATWARI_NAME_HI = "SELECT WPM.WARD_PATWARI_ID ,WPM.H_WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

	public static String DISTRICT_NAME_EN = "SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";

	public static String DISTRICT_CLR_FLAG = "SELECT CLR_FLAG FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=?";

	public static String TEHSIL_CLR_FLAG = "SELECT CLR_FLAG FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=?";

	public static String CLR_FLAG = "select clr_flag from igrs_district_master where district_id = (select DISTRICT_ID from igrs_reg_proprty_dtls where PROPERTY_ID = ?)";

	public static String VAL_TXN_ID = "select val_txn_id from igrs_reg_proprty_dtls where property_id = ?";

	public static String CENSUS_CLR_CODE = "SELECT CENSUS_CLR_CODE FROM IGRS_COLONY_MASTER WHERE COLONY_ID=?";

	public static String clr_flag_valuation = "select clr_flag from igrs_district_master where district_id = (select DIST_ID from igrs_prop_val_txn_detls where VAL_TXN_ID =?)";
	public static String DISTRICT_NAME_HI = "SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=? ";

	public static String PROPERTY_TYPE_NAME_EN = "SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE  PROPERTY_TYPE_STATUS='A'";

	public static String PROPERTY_TYPE_NAME_EN_AGRI = "SELECT PROPERTY_TYPE_ID,PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE  PROPERTY_TYPE_STATUS='A' and PROPERTY_TYPE_ID='3'";

	public static String PROPERTY_TYPE_NAME_HI = "SELECT PROPERTY_TYPE_ID,H_PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A'";

	public static String PROPERTY_TYPE_NAME_HI_AGRI = "SELECT PROPERTY_TYPE_ID,H_PROPERTY_TYPE_NAME FROM IGRS_PROPERTY_TYPE_MASTER WHERE PROPERTY_TYPE_STATUS='A' and PROPERTY_TYPE_ID='3'";

	public static String BUILDING_TYPE_NAME_EN = "SELECT PROP_SUB_TYPE_ID,PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='2'";

	public static String BUILDING_TYPE_NAME_HI = "SELECT PROP_SUB_TYPE_ID,H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='2'";

	public static String L1_NAME_EN = "SELECT PROP_TYPE_L1_ID,PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE  PROP_TYPE_L1_STATUS='A' AND PROPERTY_TYPE_ID=? AND PROP_SUB_TYPE_ID=?";

	public static String L1_NAME_HI = "SELECT PROP_TYPE_L1_ID,H_PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_STATUS='A' AND PROPERTY_TYPE_ID=? AND PROP_SUB_TYPE_ID=?";

	// --added by Satbir kumar for agriLand

	public static String AGRILAND_TYPE_NAME_EN = "SELECT PROP_SUB_TYPE_ID,PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3'";

	public static String AGRILAND_TYPE_NAME_HI = "SELECT PROP_SUB_TYPE_ID,H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3'";
	public static String AGRILAND_TYPE_NAME_EN_AGI = "SELECT PROP_SUB_TYPE_ID,PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3' and PROP_SUB_TYPE_ID='5'";

	public static String AGRILAND_TYPE_NAME_HI_AGI = "SELECT PROP_SUB_TYPE_ID,H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3' and PROP_SUB_TYPE_ID='5'";

	public static String AGRILAND_TYPE_NAME_EN_LEASE = "SELECT PROP_SUB_TYPE_ID,PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3' AND PROP_SUB_TYPE_ID ='6'";

	public static String AGRILAND_TYPE_NAME_HI_LEASE = "SELECT PROP_SUB_TYPE_ID,H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_STATUS='A' AND PROPERTY_TYPE_ID='3' AND PROP_SUB_TYPE_ID ='6'";

	public static String AGRILAND_TREE_NAME_EN = "SELECT TREE_CATEGORY_ID,TREE_CATEGORY_NAME FROM IGRS_TREE_CATEGORY_MASTER WHERE TREE_CATEGORY_STATUS='A'";

	public static String AGRILAND_TREE_NAME_HI = "SELECT TREE_CATEGORY_ID,H_TREE_CATEGORY_NAME FROM IGRS_TREE_CATEGORY_MASTER WHERE TREE_CATEGORY_STATUS='A'";

	public static String AGRILAND_SUB_TREE_GRT45_NAME_EN = "SELECT TREE_ID,TREE_NAME FROM IGRS_TREE_MASTER WHERE TREE_STATUS='A' AND TREE_CATEGORY_ID=?";

	public static String AGRILAND_SUB_TREE_GRT45_NAME_HI = "SELECT TREE_ID,H_TREE_NAME FROM IGRS_TREE_MASTER WHERE TREE_STATUS='A' AND TREE_CATEGORY_ID=?";

	public static String AGRILAND_SUB_TREE_LES45_NAME_EN = "SELECT TREE_ID,TREE_NAME FROM IGRS_TREE_MASTER WHERE TREE_STATUS='A' AND TREE_CATEGORY_ID=?";

	public static String AGRILAND_SUB_TREE_LES45_NAME_HI = "SELECT TREE_ID,H_TREE_NAME FROM IGRS_TREE_MASTER WHERE TREE_STATUS='A' AND TREE_CATEGORY_ID=?";

	public static String GET_AGRI_TXN_ID = "SELECT IGRS_AGRI_TXN_ID_SEQ.NEXTVAL FROM DUAL";

	public static String GET_AGRI_TREE_MAP_ID = "SELECT IGRS_AGRI_TREE_MAP_ID_SEQ.NEXTVAL FROM DUAL";

	public static String INSERT_AGRI_TXN_DETLS = "INSERT INTO IGRS_PROP_VAL_TXN_DETLS(VAL_TXN_ID,DIST_ID,TEHSIL_ID,AREA_TYPE_ID,SUB_AREA_TYPE_ID,WARD_PATWARI_ID,COLONY_ID,PROP_TYPE_ID,PROPERTY_TYPE_L1_ID,GUIDELINE_ID,SINGLE_BUYER,SAME_FAMILY,BUYER_COUNT,TREE_PRESENT,REF_VAL_TXN_ID,CREATED_BY,DISCLOSE_SHARE,IS_MUNICIPAL,CREATED_DATE, TOTAL_AREA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";

	// public static String INSERT_AGRI_UNDIV_BUYER_DETLS=
	// "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,TOTAL_AREA,UNIRRIGATED_1_CROP_AREA,UNIRRIGATED_2_CROP_AREA,IRRIGATED_AREA,CONSTRUCTION_DONE,UNDIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)"
	// ;

	// changes by akansha for khasra construction
	public static String INSERT_AGRI_UNDIV_BUYER_DETLS = "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,TOTAL_AREA,UNIRRIGATED_1_CROP_AREA,UNIRRIGATED_2_CROP_AREA,IRRIGATED_AREA,CONSTRUCTION_DONE,UNDIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE,CONSTRUCTION_KHASRA_CLR) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";

	// public static String INSERT_AGRI_DIV_BUYER_DETLS=
	// "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,CONSTRUCTION_DONE,TOTAL_DIVERTED_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,OTHER_AREA,EDUCATION_TCP,HEALTH_TCP,DIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)"
	// ;
	// changes by akansha for khasra construction
	public static String INSERT_AGRI_DIV_BUYER_DETLS = "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,CONSTRUCTION_DONE,TOTAL_DIVERTED_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,OTHER_AREA,EDUCATION_TCP,HEALTH_TCP,DIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE,CONSTRUCTION_KHASRA_CLR) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";

	// public static String INSERT_AGRI_BOTH_BUYER_DETLS=
	// "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,TOTAL_AREA,UNIRRIGATED_1_CROP_AREA,UNIRRIGATED_2_CROP_AREA,IRRIGATED_AREA,CONSTRUCTION_DONE,TOTAL_DIVERTED_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,OTHER_AREA,EDUCATION_TCP,HEALTH_TCP,DIVERTED_VALUE,UNDIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)"
	// ;

	// changes by akansha for khasra construction
	public static String INSERT_AGRI_BOTH_BUYER_DETLS = "INSERT INTO IGRS_PROP_AGRI_DETLS(AGRI_TXN_ID,VAL_TXN_ID,AGRI_SUB_TYPE_ID,TOTAL_AREA,UNIRRIGATED_1_CROP_AREA,UNIRRIGATED_2_CROP_AREA,IRRIGATED_AREA,CONSTRUCTION_DONE,TOTAL_DIVERTED_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,OTHER_AREA,EDUCATION_TCP,HEALTH_TCP,DIVERTED_VALUE,UNDIVERTED_VALUE,CREATED_BY,CONSTRUCTED_AREA,CONSTRUCTION_COST,CREATED_DATE,CONSTRUCTION_KHASRA_CLR) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?)";

	public static String INSERT_AGRI_TREE_DETLS = "INSERT INTO IGRS_AGRI_TREE_MAP(AGRI_TREE_MAP_ID,VAL_TXN_ID,TREE_ID,TREE_COUNT,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?,SYSDATE)";

	public static String INSERT_AGRI_MAIN_TXN_ID = "INSERT INTO IGRS_PROP_VAL_TXN_DETLS(VAL_TXN_ID,CREATED_BY,CREATED_DATE) VALUES(?,?,SYSDATE)";

	public static String GET_GUIDELINE_ALL_UNDIVERTED_VALUES = "SELECT g.GUIDELINE_ID,g2.GUIDELINE_VALUE FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2  WHERE g.district_id = ? AND g.guideline_id = g1.guideline_id AND g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? AND g1.SUB_AREA_TYPE_ID=? AND g1.ward_patwari_id =  ? AND g1.COLONY_ID =? AND g1.COLONY_WARD_MAPPING_ID=? AND g1.guideline_child1_id = g2.guideline_child1_id  AND g2.property_type_id = ?   AND g2.property_type_l1_id in (?,?,?,?) order by g2.property_type_l1_id asc  ";

	public static String GET_GUIDELINE_ALL_TREE_RATES = "SELECT TREE_RATE FROM IGRS_TREE_MASTER WHERE TREE_ID IN(?,?,?,?)";

	public static String GET_SUM_UNDIV_AGRI_VALUE_TXN = "SELECT SUM(UNDIVERTED_VALUE) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String GET_SUM_DIV_AGRI_VALUE_TXN = "SELECT SUM(DIVERTED_VALUE) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String GET_SUM_FIRST_MARKET_VALUE = "SELECT SUM(FINAL_MV) FROM IGRS_PROP_VAL_TXN_DETLS WHERE REF_VAL_TXN_ID=?";

	public static String UPDATE_FIRST_MARKET_VALUE = "UPDATE IGRS_PROP_VAL_TXN_DETLS SET FINAL_MV=? WHERE VAL_TXN_ID=? ";

	public static String UPDATE_FINAL_MARKET_VALUE = "UPDATE IGRS_PROP_VAL_TXN_DETLS SET FINAL_MV=? , PROP_TYPE_ID=? WHERE VAL_TXN_ID=? ";

	public static String AGRI_CALCULATE_WITH_SUBCLAUSS = "SELECT DISTINCT C.SUB_CLAUSE_ID,A.OPERATOR_NAME,B.OPERAND FROM IGRS_OPERATOR_MASTER A, " + "IGRS_COMPUTATION_LOGIC_MASTER B,IGRS_SUB_CLAUSE_MASTER C,IGRS_SUB_CLAUSE_MASTER_FINAL D " + "WHERE C.COMPUTATION_LOGIC_ID=B.COMPUTATION_LOGIC_ID AND B.OPERATOR_ID=A.OPERATOR_ID  " + "AND D.PROPERTY_TYPE_ID='3' AND D.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID ORDER BY C.SUB_CLAUSE_ID";

	public static String GET_SUM_UNDIV_AGRI_TOTAL_AREA_VALUE = "SELECT SUM(TOTAL_AREA - nvl(CONSTRUCTED_AREA,'0')) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String GET_SUM_DIV_AGRI_TOTAL_AREA_VALUE = "SELECT SUM(TOTAL_DIVERTED_AREA - nvl(CONSTRUCTED_AREA,'0')) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String GET_SUM_BOTH_AGRI_TOTAL_AREA_VALUE = "SELECT sum(nvl(TOTAL_AREA,'0') + nvl(TOTAL_DIVERTED_AREA,'0') - nvl(CONSTRUCTED_AREA,'0')) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String UPDATE_AGRI_CONSTRUCTION_DETLS = "UPDATE IGRS_PROP_BUILDING_DETLS SET AGRI_TXN_ID=? , VAL_TXN_ID=? WHERE BUILDING_TXN_ID=?";

	public static String UPDATE_PROP_SUB_CLAUSE_DETLS = "UPDATE IGRS_PROP_SUBCLAUSE_MAP SET AGRI_TXN_ID=? WHERE AGRI_TXN_ID IS NULL AND VAL_TXN_ID is null ";

	public static String GET_SUM_CONSTRUCTED_VALUE = "SELECT sum(nvl(CONSTRUCTION_COST,'0')) FROM IGRS_PROP_AGRI_DETLS WHERE VAL_TXN_ID=?";

	public static String GET_AGRI_INDIV_PROPERTY_VALUE = "SELECT nvl(FINAL_MV,'0') FROM IGRS_PROP_VAL_TXN_DETLS WHERE REF_VAL_TXN_ID=?";

	public static String UPDATE_AGRI_INDIV_GUIDELINE_MV = "UPDATE IGRS_PROP_VAL_TXN_DETLS SET GUIDELINE_MV=? WHERE VAL_TXN_ID=?";

	public static String GET_AGRI_REFRNC_ID_FINAL_MV = "SELECT VAL_TXN_ID,nvl(FINAL_MV,'0') FROM IGRS_PROP_VAL_TXN_DETLS WHERE REF_VAL_TXN_ID=?";
	// ---end of queries of agriland

	public static String MULTI_COMM_PROP_EN_COMM = "SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE  PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV IN ('8','19')";

	public static String MULTI_COMM_PROP_HI_COMM = "SELECT PROPERTY_TYPE_L2_ID,H_PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV   IN ('8','19')";

	public static String MULTI_COMM_PROP_EN_OTHER = "SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE  PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV  ='8'";

	public static String MULTI_COMM_PROP_HI_OTHER = "SELECT PROPERTY_TYPE_L2_ID,H_PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV ='8'";

	public static String L2_NAME_EN = "SELECT PROPERTY_TYPE_L2_ID,PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE  PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV=? order by PROPERTY_TYPE_L2_ID";

	public static String L2_NAME_HI = "SELECT PROPERTY_TYPE_L2_ID,H_PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_STATUS='A' AND PROPERTY_TYPE_L1_ID_PV=? order by PROPERTY_TYPE_L2_ID ";

	public static String FLOOR_NAME_EN = "SELECT F.FLOOR_ID,F.FLOOR_NAME FROM IGRS_FLOOR_MASTER F ,IGRS_PROP_L1_FLOOR_MAP_DUMMY M WHERE M.FLOOR_ID=F.FLOOR_ID AND M.PROPERTY_TYPE_L1_ID=?";

	public static String FLOOR_NAME_HI = "SELECT F.FLOOR_ID,F.H_FLOOR_NAME FROM IGRS_FLOOR_MASTER F ,IGRS_PROP_L1_FLOOR_MAP_DUMMY M WHERE M.FLOOR_ID=F.FLOOR_ID AND M.PROPERTY_TYPE_L1_ID=?";

	public static String COLONY_NAME_EN = "SELECT C.COLONY_ID, C.COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND M.SUBAREA_WARD_MAPPING_ID=? AND C.COLONY_STATUS='A'";

	public static String COLONY_NAME_HI = "SELECT C.COLONY_ID, C.H_COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND M.SUBAREA_WARD_MAPPING_ID=? AND C.COLONY_STATUS='A'";

	public static String COLONY_NAME_EN_PARTN = "SELECT C.COLONY_ID, C.COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND M.SUBAREA_WARD_MAPPING_ID=? AND APPLICABLE_SUBCLAUSE_ID=5 AND C.COLONY_STATUS='A'";

	public static String COLONY_NAME_HI_PARTN = "SELECT C.COLONY_ID, C.H_COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND M.SUBAREA_WARD_MAPPING_ID=? AND APPLICABLE_SUBCLAUSE_ID=5 AND C.COLONY_STATUS='A'";

	public static String GET_VALUATION_ID = "SELECT IGRS_PROP_VAL_TXN_SEQ.NEXTVAL FROM DUAL";

	public static String GET_BUILDING_ID = "SELECT IGRS_BUILD_TXN_SEQ.NEXTVAL FROM DUAL";

	public static String GET_FLOOR_TXN_ID = "SELECT IGRS_FLOOR_TXN_SEQ.NEXTVAL FROM DUAL";

	public static String INSERT_VALUATION_DETAILS = "INSERT INTO IGRS_PROP_VAL_TXN_DETLS(VAL_TXN_ID,DIST_ID,TEHSIL_ID,AREA_TYPE_ID,SUB_AREA_TYPE_ID,WARD_PATWARI_ID,COLONY_ID,PROP_TYPE_ID,GUIDELINE_ID,GUIDELINE_MV,FINAL_MV,CREATED_BY,CREATED_DATE,IS_MUNICIPAL,TOTAL_AREA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)"; // Modified
																																																																																// by
																																																																																// Gulrej
																																																																																// on
																																																																																// 29
																																																																																// th
																																																																																// Aug
																																																																																// ,
																																																																																// 2017

	public static String INSERT_OPEN_TERRACE_DETAILS = "INSERT INTO IGRS_PROP_BUILDING_DETLS(BUILDING_TXN_ID,VAL_TXN_ID,BUILDING_SUB_TYPE_ID,TERRACE_AREA,TERRACE_USAGE,CREATED_BY,CREATED_DATE) VALUES(?,?,?,?,?,?,SYSDATE)";

	public static String INSERT_INDEPENDENT_FLOOR = "INSERT INTO IGRS_PROP_BUILDING_DETLS(BUILDING_TXN_ID,VAL_TXN_ID,BUILDING_SUB_TYPE_ID,FLOOR_AREA,PROP_AT_FLOOR,PROP_OLD_20,PROP_OLD_50,LIFT_PRESENT,TRANSACT_OPEN_TERRACE,TERRACE_AREA,TERRACE_USAGE,CREATED_BY,CREATED_DATE,OLDER_ID,ONLY_RESIDENTIAL) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?)";

	public static String INSERT_MULTISTOREY_DETAILS = "INSERT INTO IGRS_PROP_BUILDING_DETLS(BUILDING_TXN_ID,VAL_TXN_ID,BUILDING_SUB_TYPE_ID,FLOOR_AREA,BUILD_UP_AREA,COMMON_AREA,PROP_AT_FLOOR,PROP_OLD_20,PROP_OLD_50,LIFT_PRESENT,TRANSACT_OPEN_TERRACE,TERRACE_AREA,TERRACE_USAGE,COMMERCIAL_PROP_TYPE,PROP_IN_MALL,PROP_NEAR_ROAD,CREATED_BY,CREATED_DATE,MULTISTOREY_SUB_TYPE,OLDER_ID, ONLY_RESIDENTIAL) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?)";

	public static String INSERT_FLOOR_TXN_DETAILS = "INSERT INTO IGRS_FLOOR_TXN_DETLS(FLOOR_TXN_ID,BUILDING_TXN_ID,FLOOR_TYPE_ID,FLOOR_MV,CREATED_BY,CREATED_DATE) VALUES (?,?,?,?,?,sysdate)";

	public static String INSERT_FLOOR_AREA_DETAILS = "INSERT INTO IGRS_FLOOR_AREA_DETLS(FLOOR_AREA_TXN_ID,FLOOR_TXN_ID,SUB_PROP_TYPE_ID,RCC_AREA,RBC_AREA,TIN_AREA,KACHA_AREA,SHOP_AREA,OFFICE_AREA,GODOWN_AREA,CREATED_BY,CREATED_DATE) VALUES (IGRS_FLOOR_AREA_TXN_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,sysdate)";

	public static String INSERT_BUILDING_DETAILS = "INSERT INTO IGRS_PROP_BUILDING_DETLS(BUILDING_TXN_ID,VAL_TXN_ID,BUILDING_SUB_TYPE_ID,TOTAL_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,OTHER_AREA,EDUCATION_TCP,HEALTH_TCP,PLOT_COST,CONSTRUCTION_COST,CREATED_BY,CREATED_DATE,IS_AKVN,IS_CONSTRUCTION,IS_HOUSING_BOARD,RESI_COMM_AREA,OLDER_ID, ONLY_RESIDENTIAL) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?)";

	public static String SELECT_SUBCLAUSE_MASTER_AGRI = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC " + "FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUB_CLAUSE_MASTER_FINAL B " + "WHERE A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' " + "AND A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND B.DISTRICT_ID=? AND " + "B.TEHSIL_ID=? AND B.WARD_PATWARI_ID=? AND B.COLONY_ID=? " + "AND B.PROPERTY_TYPE_ID=? AND B.SUB_AREA_TYPE_ID=? AND B.AREA_TYPE_ID=? AND B.SUB_PROPERTY_TYPE=?";

	// added by ShreeraJ for Plot
	public static String SELECT_PLOT_ID = "SELECT IGRS_PROP_PLOT_ID_SEQ.nextval FROM dual";
	public static String PLOT_SYS_DATE = "SELECT to_char(SYSDATE,'yyyyMMDD') FROM dual";
	public static String INSERT_PLOT_DETAILS = "INSERT INTO IGRS_PROP_PLOT_DETLS(PLOT_TXN_ID,VAL_TXN_ID,TOTAL_AREA,RESIDENTIAL_AREA,COMMERCIAL_AREA,INDUSTRIAL_AREA,EDUCATION_AREA,HEALTH_AREA,EDUCATION_TCP,HEALTH_TCP,OTHER_AREA,CREATED_BY,RESICUMM_AREA,RESICUMM_FLAG,CREATED_DATE) VALUES(IGRS_PROP_PLOT_ID_SEQ.nextval,IGRS_PROP_VAL_TXN_SEQ.currval,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
	public static String INSERT_PROP_VAL_PLOT_DETAILS = "INSERT INTO IGRS_PROP_VAL_TXN_DETLS(VAL_TXN_ID,DIST_ID,TEHSIL_ID,AREA_TYPE_ID,SUB_AREA_TYPE_ID,WARD_PATWARI_ID,COLONY_ID,PROP_TYPE_ID,CREATED_BY,CREATED_DATE,IS_MUNICIPAL, TOTAL_AREA) VALUES(IGRS_PROP_VAL_TXN_SEQ.nextval,?,?,?,?,?,?,?,?,sysdate,?, ?)"; // Modified
																																																																														// By
																																																																														// Gulrej
																																																																														// on
																																																																														// 29
																																																																														// th
																																																																														// Aug
																																																																														// ,
																																																																														// 2017
	public static String SELECT_PLOT_GUIDELINE_VALUE = "SELECT g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id IN (?,?,?,?,?,?,?)  ORDER BY g2.property_type_l1_id";

	public static String SELECT_GUIDELINE_ID = "SELECT g.GUIDELINE_ID FROM IGRS_GUIDELINE_master_parent g, " + "IGRS_GUIDELINE_master_CHILD1 g1   WHERE g.district_id =?  " + "AND g.guideline_id = g1.guideline_id 	 and g1.AREA_TYPE_ID=? " + "AND g1.tehsil_id =? and g1.SUB_AREA_TYPE_ID=?  AND g1.ward_patwari_id =? " + " AND g1.COLONY_ID =? AND g1.COLONY_WARD_MAPPING_ID=?";

	public static String SELECT_SUBCLAUSE_MASTER_PLOT = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ?";
	public static String SELECT_SUBCLAUSE_MASTER_AGRI_BUILD = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ? AND SUB_PROPERTY_TYPE = ?";
	public static String SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_Multi = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ? AND SUB_PROPERTY_TYPE = ? AND PROPERTY_TYPE_L1_ID = ?";

	public static String SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_AGRI = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ? AND SUB_PROPERTY_TYPE = ? AND A.SUB_CLAUSE_ID IN ('9','10')";

	public static String SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_AGRI_resi = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ? AND SUB_PROPERTY_TYPE = ? AND A.SUB_CLAUSE_ID IN ('9','10') AND A.APPLICABLE_ON = 'R'";

	public static String PLOT_CALCULATE_WITH_SUBCLAUSS = "SELECT DISTINCT C.SUB_CLAUSE_ID,A.OPERATOR_NAME,B.OPERAND FROM IGRS_OPERATOR_MASTER A, " + "IGRS_COMPUTATION_LOGIC_MASTER B,IGRS_SUB_CLAUSE_MASTER C,IGRS_SUB_CLAUSE_MASTER_FINAL D " + "WHERE C.COMPUTATION_LOGIC_ID=B.COMPUTATION_LOGIC_ID AND B.OPERATOR_ID=A.OPERATOR_ID  " + "AND D.PROPERTY_TYPE_ID='1' AND D.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID ORDER BY C.SUB_CLAUSE_ID";

	public static String B_CALCULATE_WITH_SUBCLAUSS = "SELECT DISTINCT C.SUB_CLAUSE_ID,A.OPERATOR_NAME,B.OPERAND FROM IGRS_OPERATOR_MASTER A, " + "IGRS_COMPUTATION_LOGIC_MASTER B,IGRS_SUB_CLAUSE_MASTER C,IGRS_SUB_CLAUSE_MASTER_FINAL D " + "WHERE C.COMPUTATION_LOGIC_ID=B.COMPUTATION_LOGIC_ID AND B.OPERATOR_ID=A.OPERATOR_ID  " + "AND D.PROPERTY_TYPE_ID='2' AND D.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID ORDER BY C.SUB_CLAUSE_ID";

	public static String SELECT_SUBCLAUSE_MASTER_BUILDING = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC " + "FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUB_CLAUSE_MASTER_FINAL B " + "WHERE A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' " + "AND A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND B.DISTRICT_ID=? AND " + "B.TEHSIL_ID=? AND B.WARD_PATWARI_ID=? AND B.COLONY_ID=? " + "AND B.PROPERTY_TYPE_ID=? AND B.SUB_AREA_TYPE_ID=? AND B.AREA_TYPE_ID=? AND B.SUB_PROPERTY_TYPE=?";

	public static String UPDATE_PROP_VAL_PLOT_DETAILS = "UPDATE IGRS_PROP_VAL_TXN_DETLS SET GUIDELINE_ID=? , GUIDELINE_MV=?, FINAL_MV=? WHERE VAL_TXN_ID=?";

	public static String GET_VALUATION_ID_CURR = "SELECT IGRS_PROP_VAL_TXN_SEQ.CURRVAL FROM DUAL";

	public static String OPEN_TERRACE_GUIDELINE_VALUE = "SELECT g.GUIDELINE_ID,g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? ";

	public static String INDEPENDENT_FLOOR_GUIDELINE_VALUE = "SELECT g.GUIDELINE_ID,g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? AND  g2.PROPERTY_TYPE_L2_ID=? ";

	public static String FLOOR_REBATE = "select REBATE_OPERAND FROM IGRS_PROP_L1_FLOOR_MAP_DUMMY WHERE FLOOR_ID=? AND PROPERTY_TYPE_L1_ID=?";

	public static String L2_GUIDELINE_VALUE = "SELECT g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? AND  g2.PROPERTY_TYPE_L2_ID=? ";
	public static String L1_GUIDELINE_VALUE = "SELECT g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? ";
	public static String MULTI_RESI_GUIDELINE_VALUE = "SELECT g.GUIDELINE_ID,g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? ";

	// below created by Roopam for building
	public static String SELECT_BUILDING_GUIDELINE_VALUE = "SELECT g2.guideline_value,g.GUIDELINE_ID,g2.property_type_l1_id  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id IN (?,?,?,?,?,?)  ORDER BY g2.property_type_l1_id";

	// added by SIMRAN for building Multistorey-commercial
	public static String MULTI_COMM_GUIDELINE_VALUE = "SELECT g.GUIDELINE_ID,g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? AND  g2.PROPERTY_TYPE_L2_ID=? ";

	public static String MULTI_COMMMERCIAL_GUIDELINE_VALUE_L1 = "SELECT g.GUIDELINE_ID,g2.guideline_value  " + " FROM IGRS_GUIDELINE_master_parent g,IGRS_GUIDELINE_master_CHILD1 g1 ,IGRS_GUIDElINE_master_CHILD2 g2 " + " WHERE g.district_id = ? " + " AND g.guideline_id = g1.guideline_id " + " and g1.AREA_TYPE_ID=? AND g1.tehsil_id = ? and g1.SUB_AREA_TYPE_ID=?" + " AND g1.ward_patwari_id =  ?" + " AND g1.COLONY_ID =?" + "AND g1.COLONY_WARD_MAPPING_ID=?" + " AND g1.guideline_child1_id = g2.guideline_child1_id " + " AND g2.property_type_id = ?  " + " AND g2.property_type_l1_id  =? ";

	public static String IND_BUILDING_CALCULATE_WITH_SUBCLAUSS = "SELECT DISTINCT C.SUB_CLAUSE_ID,A.OPERATOR_NAME,B.OPERAND FROM IGRS_OPERATOR_MASTER A, " + "IGRS_COMPUTATION_LOGIC_MASTER B,IGRS_SUB_CLAUSE_MASTER C,IGRS_SUB_CLAUSE_MASTER_FINAL D " + "WHERE C.COMPUTATION_LOGIC_ID=B.COMPUTATION_LOGIC_ID AND B.OPERATOR_ID=A.OPERATOR_ID  " + "AND D.PROPERTY_TYPE_ID='2' AND D.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID ORDER BY C.SUB_CLAUSE_ID";

	public static final String INSERT_INTO_SUBCLAUSE_PROP_MAP = "INSERT INTO IGRS_PROP_SUBCLAUSE_MAP(PROP_SUBCLAUSE_MAP_ID,VAL_TXN_ID,SUB_CLAUSE_ID,CREATED_BY,CREATED_DATE)" + "VALUES(IGRS_PROP_SUB_MAP_SEQ.NEXTVAL,?,?,?,sysdate)";
	/* Added By Rustam */
	public static final String INSERT_INTO_SUBCLAUSE_PROP_MAP_WITH_SLAB_ID = "INSERT INTO IGRS_PROP_SUBCLAUSE_MAP(PROP_SUBCLAUSE_MAP_ID,VAL_TXN_ID,SUB_CLAUSE_ID,CREATED_BY,CREATED_DATE,OLDER_ID)" + "VALUES(IGRS_PROP_SUB_MAP_SEQ.NEXTVAL,?,?,?,sysdate,?)";

	public static final String GET_BUILDING_NAME_EN = "SELECT PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE PROP_SUB_TYPE_ID=?";

	public static final String GET_BUILDING_NAME_HI = "SELECT H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE PROP_SUB_TYPE_ID=?";

	public static String SELECT_SUBCLAUSE_MASTER_BUILDING_AGRI = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC " + "FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUB_CLAUSE_MASTER_FINAL B " + "WHERE A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' " + "AND A.SUB_CLAUSE_ID=B.SUB_CLAUSE_ID AND B.DISTRICT_ID=? AND " + "B.TEHSIL_ID=? AND B.WARD_PATWARI_ID=? AND B.COLONY_ID=? " + "AND B.PROPERTY_TYPE_ID=? AND B.SUB_AREA_TYPE_ID=? AND B.AREA_TYPE_ID=? AND B.SUB_PROPERTY_TYPE=? AND A.SUB_CLAUSE_ID IN ('9','10')";

	public static String IND_PLOT_CALCULATE_WITH_SUBCLAUSS = "SELECT DISTINCT C.SUB_CLAUSE_ID,A.OPERATOR_NAME,B.OPERAND FROM IGRS_OPERATOR_MASTER A, " + "IGRS_COMPUTATION_LOGIC_MASTER B,IGRS_SUB_CLAUSE_MASTER C,IGRS_SUB_CLAUSE_MASTER_FINAL D " + "WHERE C.COMPUTATION_LOGIC_ID=B.COMPUTATION_LOGIC_ID AND B.OPERATOR_ID=A.OPERATOR_ID  " + "AND D.PROPERTY_TYPE_ID='1' AND D.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID ORDER BY C.SUB_CLAUSE_ID";

	public static String GET_MUNICIPAL_ID = "SELECT MUNICIPAL_BODY_ID FROM IGRS_SUB_AREA_TYPE_MASTER WHERE SUB_AREA_TYPE_ID=?";

	public static String clr_for_consenter = "SELECT clr_flag from igrs_reg_proprty_dtls where reg_txn_id = ? and clr_flag = 'Y'";
	// CLR first web service data insertion
	public static String INSERT_UNDIV_AGRI_KHASRA_CLR_DETLS = "INSERT INTO IGRS_REG_PROP_KHASRA_CLR_DETLS( KHASRA_TXN_ID,VAL_TXN_ID,KHASRA_NO,KHASRA_ID,KHASRA_AREA,IRRIGATED_FLAG, IRRIGATED_AREA, UNIRRI_SINGLE_CROP_FLAG,UNIRRI_SINGLE_CROP_AREA,UNIRRI_DOUBLE_CROP_FLAG, UNIRRI_DOUBLE_CROP_AREA,PADTI_FLAG,PADTI_AREA,OWNERSHIP_TYPE,NOHIYAT,CURRENT_STATUS,REMARKS,CLR_FLAG,SAMPADA_TOTAL_UNDIVERTED_AREA,MORTGAGE_INFORMATION,CREATED_BY,CREATED_DATE,CENSUS_CLR_CODE,SAMPADA_SINGLE_CROP_AREA,SAMPADA_DOUBLE_CROP_AREA,SAMPADA_IRRIGATED_AREA,RIN_PUSHTIKA_NUMBER,TOTAL_SALEBLE_AREA,FIRST_INSERT_FLAG)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?)";
	public static String INSERT_DIV_AGRI_KHASRA_CLR_DETLS = "INSERT INTO IGRS_REG_PROP_KHASRA_CLR_DETLS( KHASRA_TXN_ID,VAL_TXN_ID,KHASRA_NO,KHASRA_ID,KHASRA_AREA,IRRIGATED_FLAG, IRRIGATED_AREA, UNIRRI_SINGLE_CROP_FLAG,UNIRRI_SINGLE_CROP_AREA,UNIRRI_DOUBLE_CROP_FLAG, UNIRRI_DOUBLE_CROP_AREA,PADTI_FLAG,PADTI_AREA,OWNERSHIP_TYPE,NOHIYAT,CURRENT_STATUS,REMARKS,CLR_FLAG,SAMPADA_TOTAL_UNDIVERTED_AREA,MORTGAGE_INFORMATION,CREATED_BY,CREATED_DATE,CENSUS_CLR_CODE,RIN_PUSHTIKA_NUMBER,SAMPADA_TOTAL_DIVERTED_AREA,SAMPADA_DIV_RESIDENTIAL_AREA,SAMPADA_DIV_COMMERCIAL_AREA,SAMPADA_DIV_INDUSTRIAL_AREA,SAMPADA_DIV_EDUCATIONAL_AREA,SAMPADA_DIV_HEALTH_AREA,SAMPADA_DIV_OTHER_AREA,TOTAL_SALEBLE_AREA,FIRST_INSERT_FLAG)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?)";
	public static String INSERT_BOTH_AGRI_KHASRA_CLR_DETLS = "INSERT INTO IGRS_REG_PROP_KHASRA_CLR_DETLS( KHASRA_TXN_ID,VAL_TXN_ID,KHASRA_NO,KHASRA_ID,KHASRA_AREA,IRRIGATED_FLAG, IRRIGATED_AREA, UNIRRI_SINGLE_CROP_FLAG,UNIRRI_SINGLE_CROP_AREA,UNIRRI_DOUBLE_CROP_FLAG, UNIRRI_DOUBLE_CROP_AREA,PADTI_FLAG,PADTI_AREA,OWNERSHIP_TYPE,NOHIYAT,CURRENT_STATUS,REMARKS,CLR_FLAG,SAMPADA_TOTAL_UNDIVERTED_AREA,MORTGAGE_INFORMATION,CREATED_BY,CREATED_DATE,CENSUS_CLR_CODE,RIN_PUSHTIKA_NUMBER,SAMPADA_TOTAL_DIVERTED_AREA,SAMPADA_DIV_RESIDENTIAL_AREA,SAMPADA_DIV_COMMERCIAL_AREA,SAMPADA_DIV_INDUSTRIAL_AREA,SAMPADA_DIV_EDUCATIONAL_AREA,SAMPADA_DIV_HEALTH_AREA,SAMPADA_DIV_OTHER_AREA,SAMPADA_SINGLE_CROP_AREA, SAMPADA_DOUBLE_CROP_AREA,SAMPADA_IRRIGATED_AREA ,TOTAL_SALEBLE_AREA,FIRST_INSERT_FLAG)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static String GET_KHASRA_TXN_ID = "SELECT IGRS_KHASRA_CLR_SEQ.NEXTVAL FROM DUAL";

	public static String GET_RIN_PUSTIKA_CLR = "select distinct RIN_PUSHTIKA_NUMBER from IGRS_REG_PROP_KHASRA_CLR_DETLS where REG_TXN_ID=?";
	public static String GET_VAL_TXN_ID_CLR = "select distinct VAL_TXN_ID from IGRS_REG_PROP_KHASRA_CLR_DETLS where REG_TXN_ID=?";
	public static String GET_COLONY_ID_CLR = "select distinct COLONY_ID from igrs_prop_val_txn_detls where VAL_TXN_ID=?";
	public static String GET_ALL_KHASRA_LIST_WRT_VALTXNID = "select khasra_no from igrs_reg_prop_khasra_clr_detls where val_txn_id=?";
	// create by Rustam for Construction cost slab changes required
	public static String CONST_SLAB_LIST_eng = "SELECT OLDER_ID, DURATION_YEAR, OLDER_OPERAND, OPERATOR_NAME FROM IGRS_CONST_SLAB_LOGIC_MASTER  WHERE STATUS='A'  ORDER BY OLDER_ID ASC";

	public static String CONST_SLAB_LIST_hin = "SELECT OLDER_ID, DURATION_YEAR_HINDI, OLDER_OPERAND, OPERATOR_NAME FROM IGRS_CONST_SLAB_LOGIC_MASTER  WHERE STATUS='A'  ORDER BY OLDER_ID ASC";

	public static String CONST_SLAB_LIST_BY_SLAB_MAP_ID = "SELECT OLDER_ID, DURATION_YEAR, OLDER_OPERAND, OPERATOR_NAME FROM IGRS_CONST_SLAB_LOGIC_MASTER  WHERE STATUS='A' AND OLDER_ID=?";
	public static String SELECT_SUBCLAUSE_MASTER_AGRI_BUILD_RESI = "SELECT A.SUB_CLAUSE_ID,A.SUB_CLAUSE_NAME,A.COMPUTATION_LOGIC_ID,A.H_SUB_CLAUSE_DESC FROM IGRS_SUB_CLAUSE_MASTER A, IGRS_SUBCLAUSE_PROP_MAPPING C" + " WHERE A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID AND A.SUB_CLAUSE_STATUS='A' AND A.SUB_CLAUSE_ID=C.SUB_CLAUSE_ID " + " AND C.PROPERTY_TYPE_ID= ? AND SUB_PROPERTY_TYPE = ? AND A.APPLICABLE_ON = 'R'";
}
