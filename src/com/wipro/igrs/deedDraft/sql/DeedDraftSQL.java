package com.wipro.igrs.deedDraft.sql;

public class DeedDraftSQL {
	public static final String INSERT_DEED_CONTENT_DTLS = " INSERT INTO IGRS_DEED_DRAFT_CONTENT_DTLS(DEED_PAGE_ID,DEED_DRAFT_ID,DEED_CONTENT,CREATED_DATE,CREATED_BY)VALUES(?,?,EMPTY_CLOB(),SYSDATE,?)";
	public static final String INSERT_DEED_TXN_DTLS = "INSERT INTO IGRS_DEED_DRAFT_TXN_DTLS(DEED_DRAFT_ID,DEED_NAME,DEED_DRAFT_STATUS,CREATED_DATE,CREATED_BY,APPLICATION_TYPE, APPLICATION_NUMBER) VALUES(?,?,'I',SYSDATE,?,?,?)";
	public static final String GET_DEED_DRAFT_ID = "SELECT IGRS_DEED_DRAFT_SEQ.NEXTVAL FROM DUAL";
	public static final String GET_DEED_CONTENT = "SELECT DEED_CONTENT FROM IGRS_DEED_DRAFT_CONTENT_DTLS cntnt WHERE cntnt.DEED_DRAFT_ID =? AND cntnt.DEED_PAGE_ID = ?";
	public static final String GET_DEED_RECORD = "SELECT COUNT(*) FROM IGRS_DEED_DRAFT_CONTENT_DTLS WHERE DEED_DRAFT_ID =? AND DEED_PAGE_ID = ?";
	public static final String UPADTE_DEED_CONTENT_DTLS = " UPDATE IGRS_DEED_DRAFT_CONTENT_DTLS SET DEED_CONTENT =? ,UPDATE_DATE = sysdate ,UPDATE_BY = ? WHERE DEED_DRAFT_ID =? AND DEED_PAGE_ID = ?";
	public static final String UPDATE_DEED_TXN_DTLS = "UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET DEED_NAME =?, CREATED_DATE = sysdate,CREATED_BY =?, APPLICATION_TYPE=?, APPLICATION_NUMBER=? WHERE DEED_DRAFT_ID =?";
	public static final String GET_DEED_DRAFT_VIEW_DETAILS = "SELECT DEED_DRAFT_ID, DEED_NAME, DEED_DRAFT_STATUS,CREATED_BY, to_char(CREATED_DATE,'dd/MM/yyyy'), NVL(APPLICATION_TYPE,'--') FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE isactive='Y' AND CREATED_BY = ? OR CREATED_BY IS NULL ORDER BY  CREATED_BY DESC,CREATED_DATE DESC";
	public static final String GET_DEED_DRAFT_DERIVE_DETAILS = "SELECT DEED_DRAFT_ID, DEED_NAME,CREATED_BY, to_char(CREATED_DATE,'dd/MM/yyyy') FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE isactive='Y' AND DEED_DRAFT_STATUS in ('A','I') AND (CREATED_BY = ? OR CREATED_BY IS NULL) ORDER BY  CREATED_BY DESC,CREATED_DATE DESC";
	public static final String GET_DEED_NAME_CONTENT = "SELECT DEED_NAME,DEED_CONTENT,DEED_DRAFT_STATUS,txn.CREATED_BY FROM IGRS_DEED_DRAFT_CONTENT_DTLS cntnt,IGRS_DEED_DRAFT_TXN_DTLS txn WHERE cntnt.DEED_DRAFT_ID =? AND cntnt.DEED_PAGE_ID = ? AND cntnt.DEED_DRAFT_ID = txn.DEED_DRAFT_ID";
	public static final String GET_MAX_PAGE_NUMBER = "SELECT MAX(DEED_PAGE_ID) FROM IGRS_DEED_DRAFT_CONTENT_DTLS WHERE DEED_DRAFT_ID = ?";
	public static final String COPY_DEED_DRAFT_TXN_DTLS = "INSERT ALL INTO IGRS_DEED_DRAFT_TXN_DTLS (DEED_DRAFT_ID,DEED_NAME,DEED_DRAFT_STATUS,CREATED_DATE,CREATED_BY) VALUES(?,DEED_NAME,'I',SYSDATE,?) SELECT DEED_NAME FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE DEED_DRAFT_ID =?";
	public static final String COPY_DEED_DRAFT_CONTENT_DTLS = "INSERT ALL INTO IGRS_DEED_DRAFT_CONTENT_DTLS(DEED_PAGE_ID,DEED_DRAFT_ID,DEED_CONTENT,CREATED_DATE,CREATED_BY) VALUES(DEED_PAGE_ID,?,DEED_CONTENT,SYSDATE,?) SELECT DEED_PAGE_ID, DEED_CONTENT FROM IGRS_DEED_DRAFT_CONTENT_DTLS WHERE DEED_DRAFT_ID = ?";
	public static final String GET_DEED_ID = "select DEED_DRAFT_ID,DEED_DRAFT_STATUS from IGRS_DEED_DRAFT_TXN_DTLS where CREATED_BY=?";
	public static final String UPDATE_DEED_TXN_DTLS_STATUS = "UPDATE IGRS_DEED_DRAFT_TXN_DTLS SET DEED_DRAFT_STATUS = 'A' WHERE DEED_DRAFT_ID = ?";
	public static final String UPDATE_DEED_CONTENT_CLOB = "UPDATE IGRS_DEED_DRAFT_CONTENT_DTLS SET DEED_CONTENT=? WHERE DEED_PAGE_ID=? AND DEED_DRAFT_ID=?";
	public static final String GET_DEED_CONTENT_PDF = "SELECT A.DEED_CONTENT FROM IGRS_DEED_DRAFT_CONTENT_DTLS A,IGRS_DEED_DRAFT_TXN_DTLS B WHERE A.DEED_DRAFT_ID=B.DEED_DRAFT_ID AND A.DEED_DRAFT_ID=? AND A.DEED_PAGE_ID NOT IN('1') ORDER BY A.DEED_PAGE_ID";
	public static final String GET_DEED_CONTENT_PDF_FIRST = "SELECT A.DEED_CONTENT FROM IGRS_DEED_DRAFT_CONTENT_DTLS A,IGRS_DEED_DRAFT_TXN_DTLS B WHERE A.DEED_DRAFT_ID=B.DEED_DRAFT_ID AND A.DEED_DRAFT_ID=? AND A.DEED_PAGE_ID IN('1') ORDER BY A.DEED_PAGE_ID";
	public static final String GET_DEED_Delete_VIEW_DETAILS = "select * from (select dt.deed_draft_id, DT.DEED_NAME , to_char(DT.CREATED_DATE , 'dd-mm-yy') Created_date , T.REG_TXN_ID \"TXN_ID\" " + "from igrs_reg_txn_detls t , IGRS_DEED_DRAFT_TXN_DTLS  dt where t.deed_draft_id = DT.DEED_DRAFT_ID " + "and DT.DEED_DRAFT_STATUS='D' and dt.ISACTIVE ='Y' and DT.created_by=? UNION " + "select dt.deed_draft_id, DT.DEED_NAME , to_char(DT.CREATED_DATE , 'dd-mm-yy') Created_date , E.ESTAMP_TXN_ID  \"TXN_ID\" " + "from IGRS_ESTAMP_TXN_DETLS e , IGRS_DEED_DRAFT_TXN_DTLS  dt where E.DEED_draft_ID = DT.DEED_DRAFT_ID " + "and DT.DEED_DRAFT_STATUS='D' and dt.ISACTIVE ='Y' and DT.created_by=? ) ORDER BY to_date(CREATED_DATE,'DD-MM-YYYY') DESC";
	public static final String DELETE_DEED = "update IGRS_DEED_DRAFT_TXN_DTLS set ISACTIVE='N' where deed_draft_id in ";
	//SAURAV
	public static final String GET_PROPERTY_DETAILS = "select prop.reg_txn_id, prop.property_id, proptype.property_type_name, nvl(prop.vikas_khand,'--'), nvl(prop.ri_circle,'--'), nvl(prop.layout_detail,'--'), nvl(prop.nazool_sheet_number,'--'), nvl(prop.plot_number,'--'), prop.property_address, nvl(prop.property_landmark,'--'), prop.val_txn_id, prop.prop_type_id, proptype.h_property_type_name, prop.area_type_id FROM igrs_reg_proprty_dtls prop, igrs_property_type_master proptype WHERE prop.reg_txn_id=? and prop.prop_type_id  = proptype.property_type_id";
	public static final String GET_DUTY_DETAILS = "SELECT d.DUTY_TXN_ID, d.STAMP_DUTY, d.MUNICIPAL_DUTY, d.BLOCK_DUTY, d.UPKAR_DUTY, d.REG_FEE, d.TOTAL, d.REG_FEE_AFTER_EXEMP, d.TOTAL_AFTER_EXEMP, d.exemp_stamp, d.exemp_reg FROM igrs_duty_txn_detls d, igrs_reg_txn_detls r WHERE r.duty_txn_id = d.duty_txn_id and r.reg_txn_id=?";
	public static final String GET_APP_DETAILS = "SELECT d.deed_type_name, d.h_deed_type_name, i.instrument_name, i.h_instrument_name, nvl(s.sub_instrument_name,'--'), nvl(s.sub_instrument_name_h,'--') FROM igrs_reg_txn_detls R, igrs_deed_type_master D, igrs_deed_instrument_master I, igrs_sub_instrument_list S, igrs_reg_sub_instrument_detls RS WHERE r.reg_txn_id      = rs.reg_txn_id(+) AND r.deed_id            = d.deed_type_id AND r.instruments_id     = i.instrument_id AND rs.sub_instrument_id = s.sub_inst_id(+) AND R.REG_TXN_ID = ?";
	public static final String GET_VAL_DETAILS_E = "SELECT DISTINCT d.district_name,t.tehsil_name,  a.area_type_name,  wp.ward_patwari_name,  sm.sub_area_type_name,  c.colony_name,  nvl(Pl.TOTAL_AREA,0),  nvl(Pl.RESIDENTIAL_AREA,0),  nvl(Pl.COMMERCIAL_AREA,0),  nvl(Pl.INDUSTRIAL_AREA,0),  nvl(Pl.EDUCATION_AREA,0),  nvl(Pl.HEALTH_AREA,0),  nvl(Pl.OTHER_AREA,0),  nvl(Pl.EDUCATION_TCP,0),  nvl(Pl.HEALTH_TCP,0),  Pl.RESICUMM_FLAG,  nvl(Pl.RESICUMM_AREA,0) FROM igrs_prop_val_txn_detls p,  IGRS_PROP_PLOT_DETLS pl,  igrs_district_master d,  igrs_tehsil_master t,  igrs_area_type_master a,  igrs_municipal_body_master m,  igrs_ward_patwari_master wp,  igrs_colony_master c, igrs_sub_area_type_master sm WHERE p.dist_id       =d.district_id AND p.tehsil_id       = t.tehsil_id AND p.area_type_id    = a.area_type_id AND p.ward_patwari_id = wp.ward_patwari_id AND p.colony_id       = c.colony_id AND p.val_txn_id      = pl.val_txn_id and p.sub_area_type_id = sm.sub_area_type_id AND p.val_txn_id =? ";
	public static final String GET_VAL_DETAILS_H = "SELECT DISTINCT d.h_district_name,  t.h_tehsil_name,  a.h_area_type_name,  wp.h_ward_patwari_name,  sm.H_SUB_AREA_TYPE_NAME,  c.h_colony_name,  nvl(Pl.TOTAL_AREA,0),  nvl(Pl.RESIDENTIAL_AREA,0),  nvl(Pl.COMMERCIAL_AREA,0),  nvl(Pl.INDUSTRIAL_AREA,0),  nvl(Pl.EDUCATION_AREA,0),  nvl(Pl.HEALTH_AREA,0),  nvl(Pl.OTHER_AREA,0),  nvl(Pl.EDUCATION_TCP,0),  nvl(Pl.HEALTH_TCP,0),  Pl.RESICUMM_FLAG,  nvl(Pl.RESICUMM_AREA,0) FROM igrs_prop_val_txn_detls p,  IGRS_PROP_PLOT_DETLS pl,  igrs_district_master d,  igrs_tehsil_master t,  igrs_area_type_master a,  igrs_municipal_body_master m,  igrs_ward_patwari_master wp,  igrs_colony_master c, igrs_sub_area_type_master sm WHERE p.dist_id       =d.district_id AND p.tehsil_id       = t.tehsil_id AND p.area_type_id    = a.area_type_id AND p.ward_patwari_id = wp.ward_patwari_id AND p.colony_id       = c.colony_id AND p.val_txn_id      = pl.val_txn_id and p.sub_area_type_id = sm.sub_area_type_id AND p.val_txn_id =?";
	public static final String GET_EXEMPTION_DETAILS = "select B.EXEMPTION_NAME, B.H_EXEMPTION_NAME from IGRS_REG_EXEMPTION_DETLS A, IGRS_EXEMPTION_MASTER B where A.EXEMPTION_ID = B.EXEMPTION_ID AND B.APPLICABLE_ON=? AND A.REG_TXN_ID=?";
	public static final String GET_PROP_TYPE = "SELECT PROP_TYPE_ID FROM IGRS_REG_PROPRTY_DTLS WHERE VAL_TXN_ID=?";
	public static final String GET_BUILD_DETAILS = "SELECT d.district_name, t.tehsil_name,  a.area_type_name,  wp.ward_patwari_name,  sm.sub_area_type_name,  c.colony_name,  B.TOTAL_AREA,  b.residential_area,  fm.floor_name,  psm.prop_sub_type_name,  psm.prop_sub_type_id,  NVL(FA.RCC_AREA,0),  NVL(FA.RBC_AREA,0),  NVL(FA.TIN_AREA,0),  NVL(FA.KACHA_AREA,0),  NVL(FA.SHOP_AREA,0),  NVL(FA.OFFICE_AREA,0),  NVL(FA.GODOWN_AREA,0),  PROP_OLD_20,  PROP_OLD_50,  LIFT_PRESENT,  NVL(BUILD_UP_AREA,0),  NVL(COMMON_AREA,0) FROM igrs_prop_val_txn_detls P,  IGRS_PROP_BUILDING_DETLS B,  IGRS_PROP_SUB_TYPE_MASTER psm,  IGRS_FLOOR_TXN_DETLS F,  IGRS_FLOOR_MASTER FM,  igrs_floor_area_detls FA,  igrs_district_master d,  igrs_tehsil_master t,  igrs_area_type_master A,  igrs_ward_patwari_master wp,  igrs_colony_master c,  igrs_sub_area_type_master sm WHERE p.val_txn_id         = b.val_txn_id AND b.building_txn_id      = f.building_txn_id(+) AND b.BUILDING_SUB_TYPE_ID = psm.prop_sub_type_id AND f.floor_type_id        = fm.floor_id(+) AND f.floor_txn_id         = fa.floor_txn_id(+) AND p.dist_id              = d.district_id AND p.tehsil_id            = t.tehsil_id AND p.area_type_id         = a.area_type_id AND p.ward_patwari_id      = wp.ward_patwari_id AND p.colony_id            = c.colony_id AND p.sub_area_type_id     = sm.sub_area_type_id AND p.val_txn_id = ";
	public static final String GET_EN_SUB_CLAUSES_LIST = "SELECT m.SUB_CLAUSE_NAME,sm.SUB_CLAUSE_ID FROM igrs_sub_clause_master m ,igrs_prop_subclause_map sm WHERE sm.SUB_CLAUSE_ID=m.sub_clause_id AND sm.VAL_TXN_ID=?";
	public static final String GET_HI_SUB_CLAUSES_LIST = "SELECT m.H_SUB_CLAUSE_DESC,sm.SUB_CLAUSE_ID FROM igrs_sub_clause_master m ,igrs_prop_subclause_map sm WHERE sm.SUB_CLAUSE_ID=m.sub_clause_id AND sm.VAL_TXN_ID=?";
	public static final String GET_BUILDING_VIEW_DETAILS = "SELECT DISTINCT D.DISTRICT_NAME,T.TEHSIL_NAME,A.AREA_TYPE_NAME,SA.SUB_AREA_TYPE_NAME, " + " WP.WARD_PATWARI_NAME,C.COLONY_NAME,B.BUILDING_TXN_ID,B.BUILDING_SUB_TYPE_ID,B.FLOOR_AREA,F.FLOOR_NAME,B.PROP_OLD_20,B.PROP_OLD_50,B.LIFT_PRESENT," + " B.TRANSACT_OPEN_TERRACE,(SELECT PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.TERRACE_USAGE),B.TERRACE_AREA," + " (SELECT PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.MULTISTOREY_SUB_TYPE),B.TOTAL_AREA," + " B.BUILD_UP_AREA,B.COMMON_AREA,(SELECT PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_ID=B.COMMERCIAL_PROP_TYPE)" + ",B.PROP_IN_MALL,B.PROP_NEAR_ROAD,B.RESIDENTIAL_AREA,B.COMMERCIAL_AREA,B.INDUSTRIAL_AREA,B.EDUCATION_AREA,B.HEALTH_AREA,B.OTHER_AREA,B.EDUCATION_TCP,B.HEALTH_TCP,B.IS_CONSTRUCTION,B.IS_AKVN,B.IS_HOUSING_BOARD,B.MULTISTOREY_SUB_TYPE,B.RESI_COMM_AREA,a.area_type_id, NVL(b.older_id,0),nvl(B.Only_Residential,'NR')" + " FROM IGRS_PROP_BUILDING_DETLS B,IGRS_FLOOR_MASTER F,IGRS_DISTRICT_MASTER D,IGRS_PROP_VAL_TXN_DETLS M,IGRS_TEHSIL_MASTER T,IGRS_AREA_TYPE_MASTER A,IGRS_SUB_AREA_TYPE_MASTER SA,IGRS_WARD_PATWARI_MASTER WP,IGRS_COLONY_MASTER C " + " WHERE D.DISTRICT_ID =M.DIST_ID AND T.TEHSIL_ID=M.TEHSIL_ID  AND B.VAL_TXN_ID = M.VAL_TXN_ID AND A.AREA_TYPE_ID = M.AREA_TYPE_ID AND SA.SUB_AREA_TYPE_ID = M.SUB_AREA_TYPE_ID AND WP.WARD_PATWARI_ID = M.WARD_PATWARI_ID AND C.COLONY_ID = M.COLONY_ID AND F.FLOOR_ID = NVL(B.PROP_AT_FLOOR,3) AND B.VAL_TXN_ID=?"; //modified for construction slab changes by saurav
	public static final String GET_BUILDING_VIEW_DETAILS_HINDI = "SELECT DISTINCT D.H_DISTRICT_NAME,T.H_TEHSIL_NAME,A.H_AREA_TYPE_NAME,SA.H_SUB_AREA_TYPE_NAME, " + " WP.H_WARD_PATWARI_NAME,C.H_COLONY_NAME,B.BUILDING_TXN_ID,B.BUILDING_SUB_TYPE_ID,B.FLOOR_AREA,F.H_FLOOR_NAME,B.PROP_OLD_20,B.PROP_OLD_50,B.LIFT_PRESENT," + " B.TRANSACT_OPEN_TERRACE,(SELECT H_PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.TERRACE_USAGE),B.TERRACE_AREA," + "(SELECT H_PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.MULTISTOREY_SUB_TYPE),B.TOTAL_AREA," + " B.BUILD_UP_AREA,B.COMMON_AREA,(SELECT H_PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_ID=B.COMMERCIAL_PROP_TYPE)" + ",B.PROP_IN_MALL,B.PROP_NEAR_ROAD,B.RESIDENTIAL_AREA,B.COMMERCIAL_AREA,B.INDUSTRIAL_AREA,B.EDUCATION_AREA,B.HEALTH_AREA,B.OTHER_AREA,B.EDUCATION_TCP,B.HEALTH_TCP,B.IS_CONSTRUCTION,B.IS_AKVN,B.IS_HOUSING_BOARD,B.MULTISTOREY_SUB_TYPE,B.RESI_COMM_AREA,a.area_type_id,NVL(b.older_id,0),nvl(B.Only_Residential,'NR')" + " FROM IGRS_PROP_BUILDING_DETLS B,IGRS_FLOOR_MASTER F,IGRS_DISTRICT_MASTER D,IGRS_PROP_VAL_TXN_DETLS M,IGRS_TEHSIL_MASTER T,IGRS_AREA_TYPE_MASTER A,IGRS_SUB_AREA_TYPE_MASTER SA,IGRS_WARD_PATWARI_MASTER WP,IGRS_COLONY_MASTER C " + " WHERE D.DISTRICT_ID =M.DIST_ID AND T.TEHSIL_ID=M.TEHSIL_ID  AND B.VAL_TXN_ID = M.VAL_TXN_ID AND A.AREA_TYPE_ID = M.AREA_TYPE_ID AND SA.SUB_AREA_TYPE_ID = M.SUB_AREA_TYPE_ID AND WP.WARD_PATWARI_ID = M.WARD_PATWARI_ID AND C.COLONY_ID = M.COLONY_ID  AND F.FLOOR_ID = NVL(B.PROP_AT_FLOOR,3) AND B.VAL_TXN_ID=?"; //modified for construction slab changes by saurav
	public static final String GET_BUILDING_FLOOR_DETAILS = "SELECT B.SUB_PROP_TYPE_ID,C.FLOOR_NAME, B.RCC_AREA,B.RBC_AREA,B.TIN_AREA,B.KACHA_AREA,B.SHOP_AREA,B.OFFICE_AREA,B.GODOWN_AREA FROM IGRS_FLOOR_AREA_DETLS B,IGRS_FLOOR_TXN_DETLS A,IGRS_FLOOR_MASTER C WHERE A.FLOOR_TXN_ID=B.FLOOR_TXN_ID and A.FLOOR_TYPE_ID=C.FLOOR_ID AND A.building_txn_id=?";
	public static final String GET_BUILDING_FLOOR_DETAILS_HINDI = "SELECT B.SUB_PROP_TYPE_ID,C.H_FLOOR_NAME, B.RCC_AREA,B.RBC_AREA,B.TIN_AREA,B.KACHA_AREA,B.SHOP_AREA,B.OFFICE_AREA,B.GODOWN_AREA FROM IGRS_FLOOR_AREA_DETLS B,IGRS_FLOOR_TXN_DETLS A,IGRS_FLOOR_MASTER C WHERE A.FLOOR_TXN_ID=B.FLOOR_TXN_ID and A.FLOOR_TYPE_ID=C.FLOOR_ID AND A.building_txn_id=?";
	public static final String GET_AGRI_EN_VIEW_AREA_DETAILS = "SELECT DISTINCT D.DISTRICT_NAME,T.TEHSIL_NAME,A.AREA_TYPE_NAME,SA.SUB_AREA_TYPE_NAME,WP.WARD_PATWARI_NAME,C.COLONY_NAME,M.SINGLE_BUYER,M.SAME_FAMILY,M.BUYER_COUNT,M.TREE_PRESENT,M.DISCLOSE_SHARE,M.AREA_TYPE_ID " + " FROM IGRS_PROP_VAL_TXN_DETLS M,IGRS_DISTRICT_MASTER D,IGRS_TEHSIL_MASTER T, " + " IGRS_AREA_TYPE_MASTER A,IGRS_SUB_AREA_TYPE_MASTER SA,IGRS_WARD_PATWARI_MASTER WP,IGRS_COLONY_MASTER C WHERE D.DISTRICT_ID=M.DIST_ID AND T.TEHSIL_ID =M.TEHSIL_ID " + " AND A.AREA_TYPE_ID = M.AREA_TYPE_ID AND SA.SUB_AREA_TYPE_ID = M.SUB_AREA_TYPE_ID AND WP.WARD_PATWARI_ID= M.WARD_PATWARI_ID AND C.COLONY_ID= M.COLONY_ID AND M.VAL_TXN_ID=?";
	public static final String GET_AGRI_HI_VIEW_AREA_DETAILS = "SELECT DISTINCT D.H_DISTRICT_NAME,T.H_TEHSIL_NAME,A.H_AREA_TYPE_NAME,SA.H_SUB_AREA_TYPE_NAME,WP.H_WARD_PATWARI_NAME,C.H_COLONY_NAME,M.SINGLE_BUYER,M.SAME_FAMILY,M.BUYER_COUNT,M.TREE_PRESENT,M.DISCLOSE_SHARE,M.AREA_TYPE_ID " + " FROM IGRS_PROP_VAL_TXN_DETLS M,IGRS_DISTRICT_MASTER D,IGRS_TEHSIL_MASTER T, " + " IGRS_AREA_TYPE_MASTER A,IGRS_SUB_AREA_TYPE_MASTER SA,IGRS_WARD_PATWARI_MASTER WP,IGRS_COLONY_MASTER C WHERE D.DISTRICT_ID=M.DIST_ID AND T.TEHSIL_ID =M.TEHSIL_ID " + " AND A.AREA_TYPE_ID = M.AREA_TYPE_ID AND SA.SUB_AREA_TYPE_ID = M.SUB_AREA_TYPE_ID AND WP.WARD_PATWARI_ID= M.WARD_PATWARI_ID AND C.COLONY_ID= M.COLONY_ID AND M.VAL_TXN_ID=?";
	public static final String GET_AGRI_HI_VIEW_PROP_DETAILS = "SELECT A.AGRI_TXN_ID,A.AGRI_SUB_TYPE_ID,SB.H_PROP_SUB_TYPE_NAME,A.TOTAL_AREA,A.UNIRRIGATED_1_CROP_AREA, " + " A.UNIRRIGATED_2_CROP_AREA,A.IRRIGATED_AREA,A.CONSTRUCTION_DONE,A.TOTAL_DIVERTED_AREA,A.RESIDENTIAL_AREA,A.COMMERCIAL_AREA,A.INDUSTRIAL_AREA,A.EDUCATION_AREA,A.HEALTH_AREA,A.OTHER_AREA,A.EDUCATION_TCP,A.HEALTH_TCP " + " FROM IGRS_PROP_AGRI_DETLS A,IGRS_PROP_SUB_TYPE_MASTER SB WHERE A.AGRI_SUB_TYPE_ID=SB.PROP_SUB_TYPE_ID AND A.VAL_TXN_ID=?";
	public static final String GET_AGRI_EN_VIEW_PROP_DETAILS = "SELECT A.AGRI_TXN_ID,A.AGRI_SUB_TYPE_ID,SB.PROP_SUB_TYPE_NAME,A.TOTAL_AREA,A.UNIRRIGATED_1_CROP_AREA, " + " A.UNIRRIGATED_2_CROP_AREA,A.IRRIGATED_AREA,A.CONSTRUCTION_DONE,A.TOTAL_DIVERTED_AREA,A.RESIDENTIAL_AREA,A.COMMERCIAL_AREA,A.INDUSTRIAL_AREA,A.EDUCATION_AREA,A.HEALTH_AREA,A.OTHER_AREA,A.EDUCATION_TCP,A.HEALTH_TCP " + " FROM IGRS_PROP_AGRI_DETLS A,IGRS_PROP_SUB_TYPE_MASTER SB WHERE A.AGRI_SUB_TYPE_ID=SB.PROP_SUB_TYPE_ID AND A.VAL_TXN_ID=?";
	public static final String GET_EN_AGRI_TREE_DETAILS = "SELECT M.TREE_NAME,TREE_COUNT FROM IGRS_AGRI_TREE_MAP T,IGRS_TREE_MASTER M WHERE T.TREE_ID=m.tree_id AND VAL_TXN_ID=?";
	public static final String GET_HI_AGRI_TREE_DETAILS = "SELECT M.H_TREE_NAME,TREE_COUNT FROM IGRS_AGRI_TREE_MAP T,IGRS_TREE_MASTER M WHERE T.TREE_ID=m.tree_id AND VAL_TXN_ID=?";
	public static final String GET_KHASHRA_DETAILS = "select khas.khasra_area, khas.khasra_number, khas.lagaan, khas.rin_pushtika_number, khas.north_boundary, khas.south_boundary, khas.west_boundary, khas.east_boundary FROM igrs_reg_proprty_dtls prop, IGRS_REG_PROP_KHASARA_DTLS khas WHERE prop.property_id = khas.property_id and prop.property_id=?";
	public static final String GET_APPLICATION_DETAILS = "select reg_txn_id, DEED_DRAFT_ID, created_by, deed_id, instruments_id, REGISTRATION_TXN_STATUS from igrs_reg_txn_detls where reg_txn_id=?";
	public static final String GET_DEED_DETAILS = "SELECT COUNT(*) from IGRS_DEED_DRAFT_TXN_DTLS WHERE DEED_DRAFT_STATUS in ('A','I') and DEED_DRAFT_ID=? and APPLICATION_TYPE=? and APPLICATION_NUMBER=?";
	public static final String GET_DEED_APP_ID = "SELECT distinct APPLICATION_NUMBER FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE APPLICATION_TYPE in ('R','A') AND DEED_DRAFT_ID=?";
	public static final String GET_DEED_APP_TYPE = "SELECT distinct APPLICATION_TYPE FROM IGRS_DEED_DRAFT_TXN_DTLS WHERE  DEED_DRAFT_ID=?";
	public static final String GET_PROP_DUTY_DETAILS = "SELECT A.PROPERTY_ID,b.val_txn_id,b.PROP_DUTY_ID,b.stamp_duty,b.municipal_duty,b.block_duty,b.upkar_duty,b.total,b.reg_fee,b.duty_already_paid,b.reg_already_paid,b.payable_duty,b.payable_reg,a.market_value FROM IGRS_REG_PROPRTY_DTLS A,IGRS_PROP_DUTY_DETLS B WHERE a.val_txn_id=b.val_txn_id AND b.duty_txn_id=? AND a.REG_TXN_ID=? AND A.PROPERTY_ID=?";
	public static final String GET_PROP_WISE_USER_ENTERABLE = "SELECT B.FIELD_NAME," + "A.FIELD_VALUE " + "FROM IGRS_PROP_DUTY_FIELD_DETLS A," + "IGRS_INST_FIELD_MAPPING B " + "WHERE A.INST_FIELD_MAP_ID = B.INST_FIELD_MAP_ID " + "AND A.DUTY_TXN_ID=? ";
	public static final String GET_PROP_WISE_USER_ENTERABLE_HI = "SELECT B.FIELD_NAME_H," + "A.FIELD_VALUE " + "FROM IGRS_PROP_DUTY_FIELD_DETLS A," + "IGRS_INST_FIELD_MAPPING B " + "WHERE A.INST_FIELD_MAP_ID = B.INST_FIELD_MAP_ID " + "AND A.DUTY_TXN_ID=? ";
	public static final String PROP_DUTY_AVAILABLE = "AND A.PROP_DUTY_ID=?";
	public static final String PROP_DUTY_NOT_AVAILABLE = "AND A.PROP_DUTY_ID is null";
	public static final String GET_AGRI_CONSTRUCTION_DETAILS = "SELECT DISTINCT D.DISTRICT_NAME,T.TEHSIL_NAME,A.AREA_TYPE_NAME,SA.SUB_AREA_TYPE_NAME,  WP.WARD_PATWARI_NAME,C.COLONY_NAME,B.BUILDING_TXN_ID,B.BUILDING_SUB_TYPE_ID,B.FLOOR_AREA,F.FLOOR_NAME,B.PROP_OLD_20,B.PROP_OLD_50,B.LIFT_PRESENT, B.TRANSACT_OPEN_TERRACE,(SELECT PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.TERRACE_USAGE),B.TERRACE_AREA,(SELECT PROP_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=B.MULTISTOREY_SUB_TYPE),B.TOTAL_AREA, B.BUILD_UP_AREA,B.COMMON_AREA,(SELECT PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY WHERE PROPERTY_TYPE_L2_ID=B.COMMERCIAL_PROP_TYPE),B.PROP_IN_MALL,B.PROP_NEAR_ROAD,B.RESIDENTIAL_AREA,B.COMMERCIAL_AREA,B.INDUSTRIAL_AREA,B.EDUCATION_AREA,B.HEALTH_AREA,B.OTHER_AREA,B.EDUCATION_TCP,B.HEALTH_TCP,B.IS_CONSTRUCTION,B.IS_AKVN,B.IS_HOUSING_BOARD,B.MULTISTOREY_SUB_TYPE,B.RESI_COMM_AREA FROM IGRS_PROP_BUILDING_DETLS B,IGRS_FLOOR_MASTER F,IGRS_DISTRICT_MASTER D,IGRS_PROP_VAL_TXN_DETLS M,IGRS_TEHSIL_MASTER T,IGRS_AREA_TYPE_MASTER A,IGRS_SUB_AREA_TYPE_MASTER SA,IGRS_WARD_PATWARI_MASTER WP,IGRS_COLONY_MASTER C  WHERE D.DISTRICT_ID =M.DIST_ID AND T.TEHSIL_ID=M.TEHSIL_ID  AND B.VAL_TXN_ID = M.VAL_TXN_ID AND A.AREA_TYPE_ID = M.AREA_TYPE_ID AND SA.SUB_AREA_TYPE_ID = M.SUB_AREA_TYPE_ID AND WP.WARD_PATWARI_ID = M.WARD_PATWARI_ID AND C.COLONY_ID = M.COLONY_ID AND  F.FLOOR_ID = NVL(B.PROP_AT_FLOOR,3) AND B.AGRI_TXN_ID=?";
	public static final String GET_CONSENTER_DETLS = "select r.consenter_first_name||' '||r.consenter_middle_name||' '||r.consenter_last_name, d.STAMP_DUTY, d.MUNICIPAL_DUTY, d.JANPAD_DUTY, d.UPKAR_DUTY, d.TOTAL, d.REG_FEE, d.TOTAL_AFTER_EXEMP, d.REG_FEE_AFTER_EXEMP, d.EXEMPTED_DUTY, d.EXEMPTED_REG_FEE from igrs_reg_consenter_details r, igrs_consenter_duty_details d where  r.created_by=? and r.reg_txn_id=? and r.CONSENTER_TXN_NUM=d.CONSENTER_TXN_NUM";
	public static final String GET_ADJU_FLAG = "SELECT adjudication_flag FROM igrs_reg_txn_detls WHERE reg_txn_id=?";
	public static final String GET_MASTER_CHECK = "select created_by from igrs_deed_draft_txn_dtls where deed_Draft_id=?";
	public static final String GET_DEED_STATUS = "SELECT DEED_DRAFT_STATUS FROM igrs_deed_draft_txn_dtls WHERE DEED_DRAFT_ID=?";
	public static final String GET_EN_SUB_AGRI_CLAUSES_LIST = "SELECT DISTINCT m.SUB_CLAUSE_NAME,sm.SUB_CLAUSE_ID FROM igrs_sub_clause_master m ,igrs_prop_subclause_map sm WHERE sm.SUB_CLAUSE_ID=m.sub_clause_id AND sm.AGRI_TXN_ID=?";
	public static final String GET_HI_SUB_AGRI_CLAUSES_LIST = "SELECT DISTINCT m.h_sub_clause_desc, sm.SUB_CLAUSE_ID FROM igrs_sub_clause_master m ,igrs_prop_subclause_map sm WHERE sm.SUB_CLAUSE_ID=m.sub_clause_id AND sm.AGRI_TXN_ID=?";
	//added by ankit for prop val changes
	public static final String GET_DEED_CONSUMED = "SELECT DEED_DRAFT_STATUS from IGRS_DEED_DRAFT_TXN_DTLS WHERE APPLICATION_TYPE=? and APPLICATION_NUMBER=?";
	//construction slab changes by saurav
	public static final String GET_CONST_SLAB_DESC_E="Select Duration_Year From Igrs_Const_Slab_Logic_Master where older_id=?";
	public static final String GET_CONST_SLAB_DESC_H="Select Duration_Year_Hindi From Igrs_Const_Slab_Logic_Master where older_id=?";
	public static final String GET_AGRI_CONSTRUCTION_CONST_SLAB="select Nvl(Older_Id,0),NVL(Only_Residential,'NR')  from IGRS_PROP_BUILDING_DETLS where AGRI_TXN_ID=?";
}