package com.wipro.igrs.spotInsp.sql;

/**
 * ===========================================================================
 * File           :   CommonSQL.java
 * Description    :   Represents the SQL Class

 * Author         :   Pavani  Param
 * Created Date   :   Dec  20, 2007

 * ===========================================================================
 */
public class CommonSQL {

	///--START----PARTY DETAILS


	public static final String SPOT_CRITERIA_STATE_DOCUMENT= " select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,IGRS_PROP_SUBCLAUSE_VAL_DTLS sub, "+
	"   IGRS_SPOT_CRITERIA_DEED_MASTER deedc, IGRS_SPOT_CRITERIA_PROPERTY propc, IGRS_SPOT_CRITERIA_SUBCLAUSE subc,IGRS_DISTRICT_MASTER dist,igrs_zone_master ZON,IGRS_STATE_MASTER stat ,IGRS_REG_CHK_STAMP_DUTY_DTLS stmp ,IGRS_SPOT_CRITERIA_AREA area "+
	"  where reg.INSTRUMENTS_ID=deedc.INSTRUMENT_ID  and deedc.status='A'    and  reg.REG_TXN_ID=reginit.REG_TXN_ID  and propc.status='A' "+
	" and	 reginit.L2_TYPE_ID=propc.PROPERTY_TYPE_L2_ID   and    reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID  and ZON.STATE_ID=stat.STATE_ID  and stat.STATE_ID=? "+
	" and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and 	  reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?)  AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') "+
	" group by dist.DISTRICT_NAME,zon.ZONE_NAME,dist.DISTRICT_ID,zon.ZONE_ID ";


	public static final String SPOT_GET_RE_CALCULATION ="select MARKET_VALUE,NEW_MARKET_VALUE,GUIDELINE_VALUE,NEW_GUIDELINE_VALUE from IGRS_SPOT_INSPECTED_DETAILS where PROPERTY_TXN_ID=?";

	public static final String SPOT_CRITERIA_STATE_DOCUMENT1="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID   and "+
	" dist.ZONE_ID=zon.ZONE_ID  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy') AND  sub.SUB_CLAUSE_ID in(";



	public static final String SPOT_CRITERIA_STATE_DOCUMENT_YES = "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG           IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";

	public static final String SPOT_CRITERIA_STATE_DOCUMENT_FLOOR_YES="select distinct a.REGISTRATION_NUMBER,"
		+"a.REGISTRATION_DIST_ID, a.REGISTRATION_ZONE_ID, d.final_market_value, d.reg_fee, d.stamp_duty, a.dr_name,"
		+" a.sr_name, a.registration_office_id, b.reg_txn_id from IGRS_REG_TXN_DETLS a,IGRS_REG_PROPRTY_DTLS b,IGRS_REG_FLOOR_DETAILS c, IGRS_REG_CHK_STAMP_DUTY_DTLS d,"
		+" IGRS_PROP_SUBCLAUSE_VAL_DTLS e"

		+" where a.INSTRUMENTS_ID =?"
		+" and a.SPOT_FLAG IS NULL and a.REGISTRATION_NUMBER IS NOT NULL and d.REG_TXN_ID=a.REG_TXN_ID and a.REG_TXN_ID=b.REG_TXN_ID and b.PROPERTY_ID=c.PROPERTY_ID and"
		+" c.L1_TYPE_ID=? and c.L2_TYPE_ID=? and b.tehsil_id=? and b.AREA_TYPE_ID=? and b.ward_id=? and b.mohalla_id=? and a.REGISTRATION_ZONE_ID=? and a.REGISTRATION_DIST_ID=?"
		+" AND d.FINAL_MARKET_VALUE BETWEEN"

		+"  (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"

		+" AND (SELECT SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"

		+" AND TRUNC(a.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" and e.SUB_CLAUSE_ID IN(";


	public static final String REST_OF_QUERY_FLOOR_FINAL = ") and e.VAL_TXN_ID = b.VAL_TXN_ID" ;

	public static final String SPOT_CRITERIA_STATE_DOCUMENT_L2_YES = "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG           IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";




	public static final String SPOT_CRITERIA_ZONE_DOCUMENT_L2_YES = "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG           IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";




	public static final String SPOT_CRITERIA_ZONE_DOCUMENT_YES = "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG           IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";




	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT_YES = "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG           IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";


	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_YES =  "SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reginit.tehsil_id        =?"
		+" AND reginit.AREA_TYPE_ID     = ?"
		+" AND reginit.WARD_ID          = ?"
		+" AND reginit.MOHALLA_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";



	public static final String SPOT_CRITERIA_STATE_DOCUMENT_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";



	public static final String SPOT_CRITERIA_ZONE_DOCUMENT_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";




	public static final String SPOT_CRITERIA_ZONE_DOCUMENT_L2_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";


	public static final String SPOT_CRITERIA_STATE_DOCUMENT_L2_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";


	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_FLOOR_NO ="select distinct a.REGISTRATION_NUMBER,"

		+"  a.REGISTRATION_DIST_ID, a.REGISTRATION_ZONE_ID,  d.final_market_value, d.reg_fee, d.stamp_duty, a.dr_name, a.sr_name, a.registration_office_id,"
		+" b.reg_txn_id from IGRS_REG_TXN_DETLS a,IGRS_REG_PROPRTY_DTLS b,IGRS_REG_FLOOR_DETAILS c, IGRS_REG_CHK_STAMP_DUTY_DTLS d,"
		+" IGRS_PROP_SUBCLAUSE_VAL_DTLS e"
		+" where a.INSTRUMENTS_ID =? "
		+" and a.SPOT_FLAG IS NULL and a.REGISTRATION_NUMBER IS NOT NULL and d.REG_TXN_ID=a.REG_TXN_ID and a.REG_TXN_ID=b.REG_TXN_ID and b.PROPERTY_ID=c.PROPERTY_ID and"
		+" c.L1_TYPE_ID=? and c.L2_TYPE_ID=? and a.REGISTRATION_ZONE_ID=? and a.REGISTRATION_DIST_ID=? AND d.FINAL_MARKET_VALUE BETWEEN"

		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"

		+" AND (SELECT SLAB_MAX_RANGE"

		+"  FROM IGRS_SPOT_INSP_SLAB_MASTER rangc"

		+"  WHERE rangc.SLAB_ID=?)"

		+" AND TRUNC(a.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "

		+" TO_DATE (?,'dd/mm/yyyy')"

		+" and e.SUB_CLAUSE_ID IN(";



	public static final String SPOT_CRITERIA_ZONE_DOCUMENT_L2_FLOOR_NO ="select distinct a.REGISTRATION_NUMBER,"

		+"  a.REGISTRATION_DIST_ID, a.REGISTRATION_ZONE_ID,  d.final_market_value, d.reg_fee, d.stamp_duty, a.dr_name, a.sr_name, a.registration_office_id,"
		+" b.reg_txn_id from IGRS_REG_TXN_DETLS a,IGRS_REG_PROPRTY_DTLS b,IGRS_REG_FLOOR_DETAILS c, IGRS_REG_CHK_STAMP_DUTY_DTLS d,"
		+" IGRS_PROP_SUBCLAUSE_VAL_DTLS e"
		+" where a.INSTRUMENTS_ID =? "
		+" and a.SPOT_FLAG IS NULL and a.REGISTRATION_NUMBER IS NOT NULL and d.REG_TXN_ID=a.REG_TXN_ID and a.REG_TXN_ID=b.REG_TXN_ID and b.PROPERTY_ID=c.PROPERTY_ID and"
		+" c.L1_TYPE_ID=? and c.L2_TYPE_ID=? and a.REGISTRATION_ZONE_ID=?  AND d.FINAL_MARKET_VALUE BETWEEN"

		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"

		+" AND (SELECT SLAB_MAX_RANGE"

		+"  FROM IGRS_SPOT_INSP_SLAB_MASTER rangc"

		+"  WHERE rangc.SLAB_ID=?)"

		+" AND TRUNC(a.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "

		+" TO_DATE (?,'dd/mm/yyyy')"

		+" and e.SUB_CLAUSE_ID IN(";


	public static final String SPOT_CRITERIA_STATE_DOCUMENT_L2_FLOOR_NO ="select distinct a.REGISTRATION_NUMBER,"

		+"  a.REGISTRATION_DIST_ID, a.REGISTRATION_ZONE_ID,  d.final_market_value, d.reg_fee, d.stamp_duty, a.dr_name, a.sr_name, a.registration_office_id,"
		+" b.reg_txn_id from IGRS_REG_TXN_DETLS a,IGRS_REG_PROPRTY_DTLS b,IGRS_REG_FLOOR_DETAILS c, IGRS_REG_CHK_STAMP_DUTY_DTLS d,"
		+" IGRS_PROP_SUBCLAUSE_VAL_DTLS e"
		+" where a.INSTRUMENTS_ID =? "
		+" and a.SPOT_FLAG IS NULL and a.REGISTRATION_NUMBER IS NOT NULL and d.REG_TXN_ID=a.REG_TXN_ID and a.REG_TXN_ID=b.REG_TXN_ID and b.PROPERTY_ID=c.PROPERTY_ID and"
		+" c.L1_TYPE_ID=? and c.L2_TYPE_ID=?  AND d.FINAL_MARKET_VALUE BETWEEN"

		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"

		+" AND (SELECT SLAB_MAX_RANGE"

		+"  FROM IGRS_SPOT_INSP_SLAB_MASTER rangc"

		+"  WHERE rangc.SLAB_ID=?)"

		+" AND TRUNC(a.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "

		+" TO_DATE (?,'dd/mm/yyyy')"

		+" and e.SUB_CLAUSE_ID IN(";

	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT_L2_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reginit.L2_TYPE_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";


	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT_NO ="SELECT DISTINCT"
		+" reg.REGISTRATION_NUMBER,"
		+"reg.REGISTRATION_DIST_ID,"
		+"reg.REGISTRATION_ZONE_ID,"
		+"stmp.final_market_value,"
		+"stmp.reg_fee,"
		+"stmp.stamp_duty,"
		+"reg.dr_name,"
		+"reg.sr_name,"
		+"reg.registration_office_id,"
		+"reginit.reg_txn_id"
		+" FROM "
		+"IGRS_REG_TXN_DETLS reg,"
		+"IGRS_REG_PROPRTY_DTLS reginit,"
		+"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"
		+"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"
		+" WHERE "
		+" reg.INSTRUMENTS_ID         =?"
		+" AND reg.REG_TXN_ID           =reginit.REG_TXN_ID"
		+" AND reg.REG_TXN_ID           =stmp.REG_TXN_ID"
		+" AND reginit.L1_TYPE_ID       =?"
		+" AND reg.REGISTRATION_ZONE_ID =?"
		+" AND reg.REGISTRATION_DIST_ID =?"
		+" AND reginit.reg_txn_id       =stmp.reg_txn_id"
		+" AND reg.registration_number IS NOT NULL"
		+" AND reg.SPOT_FLAG   IS NULL"
		+" AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND "
		+" TO_DATE (?,'dd/mm/yyyy')"
		+" AND sub.SUB_CLAUSE_ID IN(";


	public static final String REST_OF_QUERY_FINAL = ") and sub.VAL_TXN_ID = reginit.VAL_TXN_ID" ;





	public static final String SPOT_CRITERIA_STATE_DOCUMENTL2="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and	 reginit.L2_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID   and "+
	" dist.ZONE_ID=zon.ZONE_ID  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy') AND  sub.SUB_CLAUSE_ID in(";

	public static final String SPOT_CRITERIA_ZONE_DOCUMENT1="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID   and "+
	" dist.ZONE_ID=zon.ZONE_ID and zon.ZONE_ID=?  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy')AND  sub.SUB_CLAUSE_ID in(";



	public static final String SPOT_CRITERIA_ZONE_DOCUMENTL2="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and	 reginit.L2_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID   and "+
	" dist.ZONE_ID=zon.ZONE_ID and zon.ZONE_ID=?  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy') AND  sub.SUB_CLAUSE_ID in(";


	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT1="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID and dist.DISTRICT_ID=?    and "+
	" dist.ZONE_ID=zon.ZONE_ID and zon.ZONE_ID=?  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy') AND  sub.SUB_CLAUSE_ID in(";



	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENTL2="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,"+
	"zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) "+
	"from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,"+
	"IGRS_PROP_SUBCLAUSE_VAL_DTLS sub,"+
	"IGRS_DISTRICT_MASTER dist,"+
	"igrs_zone_master ZON,"+
	"IGRS_STATE_MASTER stat ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp"+
	",IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=? "+
	" and reg.REG_TXN_ID=reginit.REG_TXN_ID "+
	" and	 reginit.L1_TYPE_ID=?"+
	" and	 reginit.L2_TYPE_ID=?"+
	" and  reginit.DISTRICT_ID=dist.DISTRICT_ID and dist.DISTRICT_ID=?    and "+
	" dist.ZONE_ID=zon.ZONE_ID and zon.ZONE_ID=?  and ZON.STATE_ID=stat.STATE_ID"+
	" and stat.STATE_ID=? and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and "+
	" reg.REG_TXN_ID=stmp.REG_TXN_ID AND reg.SPOT_FLAG is null " +
	"and   stmp.FINAL_MARKET_VALUE between "+
	"(select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER "+
	"rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE "+
	"from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?,'dd/mm/yyyy') AND  sub.SUB_CLAUSE_ID in(";


	public static final String SPOT_CRITERIA_ZONE_DOCUMENT="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,IGRS_PROP_SUBCLAUSE_VAL_DTLS sub, "+
	" IGRS_SPOT_CRITERIA_DEED_MASTER deedc, IGRS_SPOT_CRITERIA_PROPERTY propc, IGRS_SPOT_CRITERIA_SUBCLAUSE subc,IGRS_DISTRICT_MASTER dist,igrs_zone_master ZON ,IGRS_REG_CHK_STAMP_DUTY_DTLS stmp ,IGRS_SPOT_CRITERIA_AREA area "+
	"  where reg.INSTRUMENTS_ID=deedc.INSTRUMENT_ID  and deedc.status='A'    and  reg.REG_TXN_ID=reginit.REG_TXN_ID  and reg.SPOT_FLAG is null  and propc.status='A' "+
	" and	 reginit.L2_TYPE_ID=propc.PROPERTY_TYPE_L2_ID   and    reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID and zon.ZONE_ID=?  	  and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and  	  reg.REG_TXN_ID=stmp.REG_TXN_ID "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?)  AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') "+
	" group by dist.DISTRICT_NAME,zon.ZONE_NAME,dist.DISTRICT_ID,zon.ZONE_ID ";

	public static final String SPOT_CRITERIA_DISTRICT_DOCUMENT="select dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT reginit.REG_TXN_ID) from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,IGRS_PROP_SUBCLAUSE_VAL_DTLS sub, "+
	" IGRS_SPOT_CRITERIA_DEED_MASTER deedc, IGRS_SPOT_CRITERIA_PROPERTY propc, IGRS_SPOT_CRITERIA_SUBCLAUSE subc,IGRS_DISTRICT_MASTER dist,igrs_zone_master ZON ,IGRS_REG_CHK_STAMP_DUTY_DTLS stmp,IGRS_SPOT_CRITERIA_AREA area "+
	"  where reg.INSTRUMENTS_ID=deedc.INSTRUMENT_ID  and deedc.status='A'    and  reg.REG_TXN_ID=reginit.REG_TXN_ID and reg.SPOT_FLAG is null and propc.status='A' "+
	" and	 reginit.L2_TYPE_ID=propc.PROPERTY_TYPE_L2_ID   and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and     reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID  and dist.DISTRICT_ID=?  	 and 	  reg.REG_TXN_ID=stmp.REG_TXN_ID "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') "+
	" group by dist.DISTRICT_NAME,zon.ZONE_NAME,dist.DISTRICT_ID,zon.ZONE_ID ";

	public static final String SPOT_ASSIGNED_CRITERIA_DOCUMENT="select DISTINCT dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID, reginit.REG_TXN_ID,teh.TEHSIL_ID,teh.TEHSIL_NAME,stmp.FINAL_MARKET_VALUE,stmp.REG_FEE,stmp.TOTAL from  IGRS_REG_TXN_DETLS reg, IGRS_REG_PROPRTY_DTLS reginit,IGRS_PROP_SUBCLAUSE_VAL_DTLS sub, "+
	"  IGRS_SPOT_CRITERIA_DEED_MASTER deedc, IGRS_SPOT_CRITERIA_PROPERTY propc, IGRS_SPOT_CRITERIA_SUBCLAUSE subc,IGRS_DISTRICT_MASTER dist,IGRS_TEHSIL_MASTER teh ,igrs_zone_master ZON ,IGRS_REG_CHK_STAMP_DUTY_DTLS stmp ,IGRS_SPOT_CRITERIA_AREA area "+
	"  where reg.INSTRUMENTS_ID=deedc.INSTRUMENT_ID  and deedc.status='A'    and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and   reg.REG_TXN_ID=reginit.REG_TXN_ID AND reg.SPOT_FLAG is null and propc.status='A' "+
	" and	 reginit.L2_TYPE_ID=propc.PROPERTY_TYPE_L2_ID   and    reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID AND dist.DISTRICT_ID=teh.DISTRICT_ID AND  dist.DISTRICT_ID=?  	 and 	  reg.REG_TXN_ID=stmp.REG_TXN_ID "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')  order by stmp.FINAL_MARKET_VALUE  desc ";

	public static final String SPOT_reinspection_CRITERIA_DOCUMENT  ="  select  (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),INSP.ZONE_ID ,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIST WHERE DIST.DISTRICT_ID=INSP.DISTRICT_ID),INSP.DISTRICT_ID,COUNT(DISTRICT_ID) " +
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE SPOT_INSP_STATUS=? AND FOUND_CORRECT='N' AND trunc(INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') "+
	" GROUP BY ZONE_ID,DISTRICT_ID ";

	public static final String SPOT_reinspection_zone_CRITERIA_DOCUMENT  ="  select  (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),INSP.ZONE_ID ,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIST WHERE DIST.DISTRICT_ID=INSP.DISTRICT_ID),INSP.DISTRICT_ID,COUNT(DISTRICT_ID) " +
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE SPOT_INSP_STATUS='C' AND FOUND_CORRECT='N' and ZONE_ID=? AND trunc(INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')    "+
	" GROUP BY ZONE_ID,DISTRICT_ID ";

	public static final String SPOT_reinspection_district_CRITERIA_DOCUMENT  ="  select  (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),INSP.ZONE_ID ,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIST WHERE DIST.DISTRICT_ID=INSP.DISTRICT_ID),INSP.DISTRICT_ID,COUNT(DISTRICT_ID) " +
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE SPOT_INSP_STATUS='C' AND FOUND_CORRECT='N'  and DISTRICT_ID=? AND trunc(INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')    "+
	" GROUP BY ZONE_ID,DISTRICT_ID ";




	public static final String SPOT_SRO_LIST= "SELECT OM.OFFICE_ID,OM.OFFICE_NAME FROM " +
	"IGRS_OFFICE_MASTER OM,IGRS_OFFICE_TYPE_MASTER OT WHERE OM.OFFICE_TYPE_ID =" +
	"OT.OFFICE_TYPE_ID AND OFFICE_TYPE_NAME ='SRO' ORDER BY OFFICE_ID";
	//DEED INSTRUMENT DATA
	public static final String SPOT_SR_LIST= " SELECT DISTINCT  USR.FIRST_NAME,USR.USER_ID FROM IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ,IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG, "+
	"  IGRS_OFFICE_MASTER OFFC ,IGRS_ZONE_MASTER ZON,IGRS_DISTRICT_MASTER DIST, IGRS_USER_REG_DETAILS USR "+
	" WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND rm.ROLE_ID='RL1356609581406' "+
	"  AND OFFC.DISTRICT_ID=DIST.DISTRICT_ID AND ZON.ZONE_ID=DIST.ZONE_ID AND UG.USER_ID=USR.USER_ID  AND USR.USER_STATUS='A' AND DIST.DISTRICT_ID= ";

	public static final String SPOT_DR_LIST= " SELECT DISTINCT  USR.FIRST_NAME,USR.USER_ID FROM IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ,IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG, "+
	"  IGRS_OFFICE_MASTER OFFC ,IGRS_ZONE_MASTER ZON,IGRS_DISTRICT_MASTER DIST, IGRS_USER_REG_DETAILS USR "+
	" WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND rm.ROLE_ID='RL1356671129765' "+
	"  AND OFFC.DISTRICT_ID=DIST.DISTRICT_ID AND ZON.ZONE_ID=DIST.ZONE_ID AND UG.USER_ID=USR.USER_ID  AND USR.USER_STATUS='A' AND DIST.DISTRICT_ID= ";


	public static final String SPOT_SR_FOR_DR_LIST= "  SELECT DISTINCT  USR.FIRST_NAME,USR.USER_ID,OFFROL.OFFICE_ID FROM IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ,IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG,IGRS_USER_REG_DETAILS USR "+
	"WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND rm.ROLE_ID='RL1356609581406' "+
	"   AND UG.USER_ID=USR.USER_ID  AND USR.USER_STATUS='A' AND OFFROL.OFFICE_ID IN( SELECT DISTINCT  offrol.OFFICE_ID FROM IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ,IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG,IGRS_USER_REG_DETAILS USR "+
	" WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND  UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND rm.ROLE_ID='RL1356671129765' "+
	"    AND USR.USER_STATUS='A'  AND UG.USER_ID= " ;

	public static final String SPOT_DEED_INSTRUMENT_LIST= "SELECT deed.DEED_TYPE_NAME||'-' ||inst.INSTRUMENT_NAME, inst.INSTRUMENT_ID FROM IGRS_DEED_TYPE_MASTER deed,"+
	" IGRS_DEED_INSTRUMENT_MASTER inst WHERE  inst.PROP_VAL_REQ_FLAG='Y' and inst.INSTRUMENT_STATUS='A' "+
	" AND deed.DEED_TYPE_ID  =inst.DEED_TYPE_ID and deed.deed_status = 'A' ORDER BY deed.DEED_TYPE_ID ";

	public static final String SPOT_DEED_INSTRUMENT_LIST_HINDI= "SELECT deed.H_DEED_TYPE_NAME||'-' ||inst.H_INSTRUMENT_NAME, inst.INSTRUMENT_ID FROM IGRS_DEED_TYPE_MASTER deed,"+
	" IGRS_DEED_INSTRUMENT_MASTER inst WHERE  inst.PROP_VAL_REQ_FLAG='Y' and inst.INSTRUMENT_STATUS='A' "+
	" AND deed.DEED_TYPE_ID  =inst.DEED_TYPE_ID and deed.deed_status = 'A' ORDER BY deed.DEED_TYPE_ID ";

	public static final String SPOT_RANGE_LIST= "SELECT SLAB_MIN_RANGE || ' - ' || SLAB_MAX_RANGE ,SLAB_ID,SLAB_MIN_RANGE,SLAB_MAX_RANGE   FROM IGRS_SPOT_INSP_SLAB_MASTER WHERE SLAB_STATUS ='A' order by SLAB_MIN_RANGE asc " ;


	public static final String SPOT_FLAG_UPDATE="UPDATE IGRS_REG_TXN_DETLS SET SPOT_FLAG='S' WHERE REGISTRATION_NUMBER =? ";

	public static final String SPOT_RE_FLAG_UPDATE="UPDATE IGRS_REG_TXN_DETLS SET SPOT_FLAG='R' WHERE REGISTRATION_NUMBER =? ";


	public static final String SPOT_CURRENT_DEED_INSTRUMENT_LIST= "SELECT   deed.DEED_TYPE_NAME||'-'||inst.INSTRUMENT_NAME, inst.INSTRUMENT_ID " +
	"  FROM IGRS_DEED_TYPE_MASTER deed, IGRS_DEED_INSTRUMENT_MASTER inst,IGRS_SPOT_CRITERIA_DEED_MASTER curr  where deed.SPOT_RELATED='Y' and curr.INSTRUMENT_ID=inst.INSTRUMENT_ID and deed.DEED_TYPE_ID=inst.DEED_TYPE_ID  and curr.STATUS='A'  order by inst.INSTRUMENT_ID ";

	public static final String SPOT_CURRENT_DEED_INSTRUMENT_LIST_HINDI= "SELECT   deed.H_DEED_TYPE_NAME||'-'||inst.H_INSTRUMENT_NAME, inst.INSTRUMENT_ID " +
	"  FROM IGRS_DEED_TYPE_MASTER deed, IGRS_DEED_INSTRUMENT_MASTER inst,IGRS_SPOT_CRITERIA_DEED_MASTER curr  where deed.SPOT_RELATED='Y' and curr.INSTRUMENT_ID=inst.INSTRUMENT_ID and deed.DEED_TYPE_ID=inst.DEED_TYPE_ID  and curr.STATUS='A'  order by inst.INSTRUMENT_ID ";


	public static final String SPOT_CURRENT_RANGE_LIST= " SELECT inst.SLAB_MIN_RANGE || ' - ' || inst.SLAB_MAX_RANGE , inst.SLAB_ID  "+
	"  FROM  IGRS_SPOT_INSP_SLAB_MASTER inst,IGRS_SPOT_CRITERIA_RANGE curr  where  curr.RANGE_ID=inst.SLAB_ID  and curr.STATUS='A' order by inst.SLAB_ID ";

	public static final String SPOT_CURRENT_PROPERTY_LIST="SELECT  IPROP.PROPERTY_TYPE_L2_ID from IGRS_SPOT_CRITERIA_PROPERTY IPROP where IPROP.STATUS   ='A'  ORDER BY IPROP.PROPERTY_TYPE_L2_ID ";

	public static final String SPOT_CURRENT_PROPERTY_LIST_HINDI="SELECT  IPROP.PROPERTY_TYPE_L2_ID from IGRS_SPOT_CRITERIA_PROPERTY IPROP where IPROP.STATUS   ='A'  ORDER BY IPROP.PROPERTY_TYPE_L2_ID  ";

	public static final String SPOT_SUB_CLAUSE_LIST=" SELECT SUB_CLAUSE_NAME || ' - ' || SUB_CLAUSE_DESC,SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER WHERE SUB_CLAUSE_STATUS='A' order by PROP_TYPE_FLAG " ;

	public static final String SPOT_SUB_CLAUSE_LIST_HINDI=" SELECT H_SUB_CLAUSE_DESC,SUB_CLAUSE_ID FROM IGRS_SUB_CLAUSE_MASTER WHERE SUB_CLAUSE_STATUS='A' order by PROP_TYPE_FLAG " ;


	public static final String SPOT_AREA_TYPE_LIST = "Select a.AREA_TYPE_ID,a.AREA_TYPE_NAME from IGRS_AREA_TYPE_MASTER a,IGRS_SPOT_CRITERIA_AREA sc where sc.AREA_TYPE_ID=a.AREA_TYPE_ID and sc.STATUS='A'";

	public static final String MOHALLA_QUERY = "Select MOHALLA_VILLAGE_ID, "
		+"MOHALLA_VILLAGE_NAME from IGRS_MOHALLA_VILLAGE_MASTER "
		+"Where WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_STATUS='A' "
		+"ORDER BY MOHALLA_VILLAGE_NAME ASC";

	public static final String MOHALLA_QUERY_HINDI = "Select MOHALLA_VILLAGE_ID, "
		+"H_MOHALLA_VILLAGE_NAME from IGRS_MOHALLA_VILLAGE_MASTER "
		+"Where WARD_PATWARI_ID=? AND MOHALLA_VILLAGE_STATUS='A' "
		+"ORDER BY H_MOHALLA_VILLAGE_NAME ASC";

	public static final String SPOT_AREA_TYPE_LIST_HINDI = "Select a.AREA_TYPE_ID,a.H_AREA_TYPE_NAME from IGRS_AREA_TYPE_MASTER a,IGRS_SPOT_CRITERIA_AREA sc where sc.AREA_TYPE_ID=a.AREA_TYPE_ID and sc.STATUS='A'";

	public static final String SPOT_CURRENT_SUB_CLAUSE_LIST=" SELECT  ISM.SUB_CLAUSE_DESC,ISM.SUB_CLAUSE_ID " +
	" FROM IGRS_SUB_CLAUSE_MASTER ISM,IGRS_SPOT_CRITERIA_SUBCLAUSE ISC  WHERE SUB_CLAUSE_STATUS='A' AND ISC.SUBCLAUSE_ID=ISM.SUB_CLAUSE_ID AND STATUS='A'  order by sub_clause_id ";

	public static final String ZONE_QUERY = "Select ZONE_ID, "
		+"ZONE_NAME from IGRS_ZONE_MASTER "
		+" Where STATE_ID=? AND ZONE_STATUS='A' "
		+"ORDER BY ZONE_NAME ASC";



	public static final String ZONE_QUERY_HINDI = "Select ZONE_ID, "
		+"H_ZONE_NAME from IGRS_ZONE_MASTER "
		+" Where STATE_ID=? AND ZONE_STATUS='A' "
		+"ORDER BY ZONE_NAME ASC";


	public static final String SPOT_CURRENT_SUB_CLAUSE_LIST_HINDI=" SELECT  ISM.H_SUB_CLAUSE_DESC,ISM.SUB_CLAUSE_ID " +
	" FROM IGRS_SUB_CLAUSE_MASTER ISM,IGRS_SPOT_CRITERIA_SUBCLAUSE ISC  WHERE SUB_CLAUSE_STATUS='A' AND ISC.SUBCLAUSE_ID=ISM.SUB_CLAUSE_ID AND STATUS='A' order by sub_clause_id   ";


	public static final String SPOT_PROPERTY_LIST="SELECT L.PROPERTY_TYPE_NAME ||' - '"+
	" || L1.PROP_TYPE_L1_NAME,"+
	" L1.PROP_TYPE_L1_ID"+
	" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
	"      IGRS_PROP_TYPE_L1_MASTER_DUMMY L1"+
	" WHERE L.PROPERTY_TYPE_ID   =L1.PROPERTY_TYPE_ID"+
	" AND PROP_TYPE_L1_STATUS='A' "+
	" AND PROPERTY_TYPE_STATUS   ='A'"+
	" ORDER BY L1.PROP_TYPE_L1_ID";

	public static final String SPOT_PROPERTY_LIST_HINDI="SELECT L.PROPERTY_TYPE_NAME ||' - '"+
	" || L1.H_PROP_TYPE_L1_NAME,"+
	" L1.PROP_TYPE_L1_ID"+
	" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
	"      IGRS_PROP_TYPE_L1_MASTER_DUMMY L1"+
	" WHERE L.PROPERTY_TYPE_ID   =L1.PROPERTY_TYPE_ID"+
	" AND PROP_TYPE_L1_STATUS='A' "+
	" AND PROPERTY_TYPE_STATUS   ='A'"+
	" ORDER BY L1.PROP_TYPE_L1_ID";


	public static final String SPOT_CURRENT_AREA_LIST=" SELECT ISM.AREA_TYPE_NAME,ISC.AREA_TYPE_ID " +
	" FROM IGRS_AREA_TYPE_MASTER ISM,IGRS_SPOT_CRITERIA_AREA ISC  WHERE ISM.AREA_TYPE_STATUS='A' AND ISC.AREA_TYPE_ID=ISM.AREA_TYPE_ID AND STATUS='A' order by ISC.AREA_TYPE_ID ";

	public static final String SPOT_CURRENT_AREA_LIST_HINDI=" SELECT ISM.H_AREA_TYPE_NAME,ISC.AREA_TYPE_ID " +
	" FROM IGRS_AREA_TYPE_MASTER ISM,IGRS_SPOT_CRITERIA_AREA ISC  WHERE ISM.AREA_TYPE_STATUS='A' AND ISC.AREA_TYPE_ID=ISM.AREA_TYPE_ID AND STATUS='A'  order by ISC.AREA_TYPE_ID ";


	public static final String SPOT_AREA_LIST=" SELECT AREA_TYPE_NAME,AREA_TYPE_ID " +
	" FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_STATUS='A' ";

	public static final String SPOT_AREA_LIST_HINDI=" SELECT H_AREA_TYPE_NAME,AREA_TYPE_ID " +
	" FROM IGRS_AREA_TYPE_MASTER WHERE AREA_TYPE_STATUS='A' ";


	public static final String SPOT_ACTIVITY_LIST=" SELECT ACTIVITY_ID, ACTIVITY_NAME FROM IGRS_SPOT_CRITERIA_ACTIVITY  ";

	public static final String SPOT_ACTIVITY_LIST_HINDI ="SELECT ACTIVITY_ID, H_ACTIVITY_NAME FROM IGRS_SPOT_CRITERIA_ACTIVITY";
	/**
	 * INSERT_GRP_ROLE_MAPPING
	 */
	public static final String INSERT_DEED_CRITERIA_MAPPING = "INSERT INTO IGRS_SPOT_CRITERIA_DEED_MASTER " +
	"(INSTRUMENT_DEED_MAP_ID, INSTRUMENT_ID,STATUS,CREATED_DATE)" +
	"VALUES(SPOT_ID_SEQ.NEXTVAL,?,'A',SYSDATE) ";

	public static final String INSERT_PROPERTY_CRITERIA_MAPPING = "INSERT INTO IGRS_SPOT_CRITERIA_PROPERTY " +
	"(PROPERTY_TYPE_MAP_ID, PROPERTY_TYPE_L2_ID,STATUS,CREATED_DATE)" +
	"VALUES(SPOT_ID_SEQ.NEXTVAL,?,'A',SYSDATE) ";

	public static final String INSERT_AREA_CRITERIA_MAPPING = "INSERT INTO IGRS_SPOT_CRITERIA_AREA " +
	"(AREA_TYPE_MAP_ID, AREA_TYPE_ID,STATUS,CREATED_DATE)" +
	"VALUES(SPOT_ID_SEQ.NEXTVAL,?,'A',SYSDATE) ";

	public static final String INSERT_RANGE_CRITERIA_MAPPING = "INSERT INTO IGRS_SPOT_CRITERIA_RANGE " +
	"(RANGE_MAP_ID, RANGE_ID,STATUS,CREATED_DATE)" +
	"VALUES(SPOT_ID_SEQ.NEXTVAL,?,'A',SYSDATE) ";

	public static final String INSERT_SUBCLAUSE_CRITERIA_MAPPING = "INSERT INTO IGRS_SPOT_CRITERIA_SUBCLAUSE " +
	"(SUB_CLAUSE_MAP_ID, SUBCLAUSE_ID,STATUS,CREATED_DATE)" +
	"VALUES(SPOT_ID_SEQ.NEXTVAL,?,'A',SYSDATE) ";


	//SR View

	//   public static final String SPOT_SRVIEW_APPID_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy')," +
	//		" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS " +
	//	" FROM IGRS_SPOT_INSPECTION_DTLS WHERE  REG_TXN_ID=?   AND spot_insp_status in('C','R') and  AND SR_USER_ID=";

	//   public static final String SPOT_SRVIEW_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy')," +
	//   		" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS " +
	// 		" FROM IGRS_SPOT_INSPECTION_DTLS WHERE  SPOT_INSP_STATUS =? AND SR_USER_ID= ";

	//   public static final String SPOT_SRVIEW_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy')," +
	//    		" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS " +
	//	" FROM IGRS_SPOT_INSPECTION_DTLS WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_status in('C','R') AND SR_USER_ID=";


	//

	public static final String SPOT_SRVIEW_DATE_QUERY="SELECT   REG_COMPLETION_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND  spot_insp_status IN('C') AND REG_COMPLETION_ID is not null AND SR_ASSIGNED_USER_ID=? ";

	public static final String SPOT_SRVIEW_REINSECT_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS  WHERE trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND  spot_insp_status IN('R') AND REINSPECTION_STATUS='C' AND REINSPECT_SR_USER_ID=? ";

	public static final String SPOT_SRVIEW_APPID_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE REG_TXN_ID=? AND  spot_insp_status IN('C') AND SR_USER_ID=? "+
	"UNION "+
	"SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE REG_TXN_ID=? AND  spot_insp_status IN('R') AND REINSPECTION_STATUS='C' AND REINSPECT_SR_USER_ID=? ";
	//Status
	public static final String SPOT_SRVIEW_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE  SPOT_INSP_STATUS =?  and  SR_USER_ID= ";

	public static final String SPOT_SRVIEW_STATUS_QUERY1="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE  SPOT_INSP_STATUS =?  AND REINSPECTION_STATUS='C'  and  REINSPECT_SR_USER_ID= ";

	public static final String SPOT_VIEW_APPID_QUERY="SELECT   REG_TXN_ID," +
	"TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_PLANNED_DATE,'dd/mm/yyyy')," +
	" SPOT_INSP_STATUS FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  REG_TXN_ID=? AND spot_insp_status='P' OR spot_insp_status='D'  ";

	public static final String SPOT_VIEW_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy')," +
	" TO_CHAR(INSPECTION_PLANNED_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS " +
	" FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  SPOT_INSP_STATUS =? AND spot_insp_status='P' OR spot_insp_status='D'  ";

	public static final String SPOT_VIEW_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_PLANNED_DATE,'dd/mm/yyyy'), SPOT_INSP_STATUS  " +
	"FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_status='P' OR spot_insp_status='D'  ";

	//DR Request VIEW
	//Date
	public static final String SPOT_DR_REQVIEW_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID),FOUND_CORRECT,nvl(COMPLAINT_ID,'NA') "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_status IN ('Y','C') " ;

	//Date
	public static final String SPOT_ADMIN_QVIEW_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID),nvl(FOUND_CORRECT,'NA'),nvl(COMPLAINT_ID,'NA') "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') " ;


	//Txn id
	public static final String SPOT_DR_REQVIEW_TXN_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID) "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  REG_TXN_ID=?  AND spot_insp_status IN ('Y','C') " ;
	//Status
	public static final String SPOT_DR_REQVIEW_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID) "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  SPOT_INSP_STATUS =? " ;

	//DR APPROVAL
	//Date
	public static final String SPOT_DRAPPROVE_VIEW_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	"TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(SELECT TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID=INSP.TEHSIL_ID) TEHSIL_NAME ,INSP.VALUATION_AMOUNT,INSP.REG_FEE,INSP.STAMP_DUTY ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID),(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.dR_USER_ID) DR__NAME "+
	"		  FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_status IN ('C') and  FOUND_CORRECT!='N' and INSP.DISTRICT_ID=? " ;
	//TxnId
	public static final String SPOT_DRAPPROVE_VIEW_TXNID_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID) "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE  REG_TXN_ID=?  AND spot_insp_status IN ('C')and FOUND_CORRECT!='N' " ;
	//Status
	public static final String SPOT_DRAPPROVE_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME "+
	" ,(select FIRST_NAME from  IGRS_USER_REG_DETAILS where USER_ID=insp.SR_USER_ID) "+
	" FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE FOUND_CORRECT!='N' and SPOT_INSP_STATUS =? " ;

	//Scheduled
	//Date
	public static final String SPOT_VIEW_SCHUDLE_DATE_QUERY="SELECT     (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),ZONE_ID,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIS WHERE DIS.DISTRICT_ID=INSP.DISTRICT_ID),DISTRICT_ID,SPOT_INSP_STATUS,COUNT(SPOT_INSP_STATUS),nvl(FOUND_CORRECT,'NA')          FROM IGRS_SPOT_INSPECTION_DTLS INSP "+
	" WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') "+
	" GROUP BY SPOT_INSP_STATUS,ZONE_ID,DISTRICT_ID,FOUND_CORRECT";

	public static final String SPOT_VIEW_INSPECTION_DATE_QUERY="SELECT     (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),ZONE_ID,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIS WHERE DIS.DISTRICT_ID=INSP.DISTRICT_ID),DISTRICT_ID,SPOT_INSP_STATUS,COUNT(SPOT_INSP_STATUS),nvl(FOUND_CORRECT,'NA'),NULL         FROM IGRS_SPOT_INSPECTION_DTLS INSP "+
	" WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_STATUS IN('Y','C') "+
	" GROUP BY SPOT_INSP_STATUS,ZONE_ID,DISTRICT_ID,FOUND_CORRECT";

	public static final String SPOT_VIEW_REINSPECTION_DATE_QUERY="SELECT     (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=INSP.ZONE_ID),ZONE_ID,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER DIS WHERE DIS.DISTRICT_ID=INSP.DISTRICT_ID),DISTRICT_ID,SPOT_INSP_STATUS,COUNT(SPOT_INSP_STATUS),nvl(FOUND_CORRECT,'NA'),REINSPECTION_STATUS         FROM IGRS_SPOT_INSPECTION_DTLS INSP "+
	" WHERE  trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_STATUS IN('R')"+
	" GROUP BY SPOT_INSP_STATUS,ZONE_ID,DISTRICT_ID,FOUND_CORRECT,REINSPECTION_STATUS";

	public static final String SPOT_VIEW_SCHUDLE_TXNID_QUERY="SELECT   REG_TXN_ID," +
	"TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_PLANNED_DATE,'dd/mm/yyyy')," +
	" SPOT_INSP_STATUS FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  REG_TXN_ID=? AND spot_insp_status='A' ";

	//Status
	public static final String SPOT_SCHUDLE_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy')," +
	" TO_CHAR(INSPECTION_PLANNED_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS " +
	" FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  SPOT_INSP_STATUS =? AND spot_insp_status='A' ";
	//Completion

	//  public static final String SPOT_COMPLETION_DATE_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND spot_insp_status IN('Y','R')  and  SR_USER_ID= ";
	//txnId
	//Super Imporatant
	public static final String SPOT_GET_REG_COMPLETION = " Select reg_txn_id  from igrs_reg_txn_detls where REGISTRATION_NUMBER=?";

	public static final String SPOT_COMPLETION_DATE_QUERY="SELECT   REG_COMPLETION_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  dist WHERE dist.DISTRICT_ID=insp.DISTRICT_ID) district_name," +
	"(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER  ZON WHERE ZON.ZONE_ID=insp.ZONE_ID) ZONE_name,VALUATION_AMOUNT,reg_fee,stamp_duty,SR_USER_ID, DR_USER_ID,SPOT_INSPECTION_TXN_ID   FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND  spot_insp_status IN('Y') AND (insp.FOUND_CORRECT is null OR insp.FOUND_CORRECT!='Y')  AND SR_ASSIGNED_USER_ID=? and insp.district_id=? AND insp.office_id=? ";

	public static final String SPOT_COMPLETION_DATE_QUERY_HINDI="SELECT   REG_COMPLETION_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS,(SELECT H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  dist WHERE dist.DISTRICT_ID=insp.DISTRICT_ID) district_name," +
	"(SELECT H_ZONE_NAME FROM IGRS_ZONE_MASTER  ZON WHERE ZON.ZONE_ID=insp.ZONE_ID) ZONE_name,VALUATION_AMOUNT,reg_fee,stamp_duty,SR_USER_ID, DR_USER_ID,SPOT_INSPECTION_TXN_ID   FROM IGRS_SPOT_INSPECTION_DTLS INSP WHERE trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') AND  spot_insp_status IN('Y') AND (insp.FOUND_CORRECT is null OR insp.FOUND_CORRECT!='Y')  AND SR_ASSIGNED_USER_ID=? and insp.district_id=?  AND insp.office_id=? ";

	public static final String SPOT_RECOMPLETION_DATE_QUERY ="SELECT "
		+"  REG_COMPLETION_ID,"
		+"  TO_CHAR(RE_CREATED_DATE,'dd/mm/yyyy'),"
		+"  TO_CHAR(REINSPECTION_DATE,'dd/mm/yyyy') ,"
		+"  SPOT_INSP_STATUS,"
		+"  (SELECT DISTRICT_NAME"
		+"  FROM IGRS_DISTRICT_MASTER dist"
		+"  WHERE dist.DISTRICT_ID=insp.DISTRICT_ID"
		+"  ) district_name,"
		+"  (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=insp.ZONE_ID"
		+"  ) ZONE_name,"
		+"  VALUATION_AMOUNT,"
		+"  reg_fee,"
		+"  stamp_duty,sr_user_id,dr_user_id,"
		+"  RE_SPOT_INSPECTION_TXN_ID"
		+" FROM IGRS_SPOT_INSPECTION_DTLS insp"
		+" WHERE TRUNC(RE_CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')"
		+" AND spot_insp_status   IN('R')"
		+" AND REINSPECTION_STATUS ='Y' AND    (insp.FOUND_CORRECT is null OR insp.FOUND_CORRECT!='Y')     "
		+" AND REINSPECT_SR_USER_ID=?  AND insp.office_id=? ";


	public static final String SPOT_RECOMPLETION_DATE_QUERY_HI ="SELECT "
		+"  REG_COMPLETION_ID,"
		+"  TO_CHAR(RE_CREATED_DATE,'dd/mm/yyyy'),"
		+"  TO_CHAR(REINSPECTION_DATE,'dd/mm/yyyy') ,"
		+"  SPOT_INSP_STATUS,"
		+"  (SELECT H_DISTRICT_NAME"
		+"  FROM IGRS_DISTRICT_MASTER dist"
		+"  WHERE dist.DISTRICT_ID=insp.DISTRICT_ID"
		+"  ) district_name,"
		+"  (SELECT H_ZONE_NAME FROM IGRS_ZONE_MASTER ZON WHERE ZON.ZONE_ID=insp.ZONE_ID"
		+"  ) ZONE_name,"
		+"  VALUATION_AMOUNT,"
		+"  reg_fee,"
		+"  stamp_duty,sr_user_id,dr_user_id,"
		+"  RE_SPOT_INSPECTION_TXN_ID"
		+" FROM IGRS_SPOT_INSPECTION_DTLS insp"
		+" WHERE TRUNC(RE_CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')"
		+" AND spot_insp_status   IN('R')"
		+" AND REINSPECTION_STATUS ='Y'    AND    (insp.FOUND_CORRECT is null OR insp.FOUND_CORRECT!='Y') "
		+" AND REINSPECT_SR_USER_ID=?  AND insp.office_id=? ";

	public static final String SPOT_COMPLETION_TXN_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE REG_TXN_ID=? AND  spot_insp_status IN('Y') AND SR_USER_ID=? "+
	"UNION "+
	"SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE REG_TXN_ID=? AND  spot_insp_status IN('R') AND REINSPECTION_STATUS='A' AND REINSPECT_SR_USER_ID=? ";
	//Status
	public static final String SPOT_COMPLETION_STATUS_QUERY="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE  SPOT_INSP_STATUS =?  and  SR_USER_ID= ";

	public static final String SPOT_COMPLETION_STATUS_QUERY1="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy') , SPOT_INSP_STATUS   FROM IGRS_SPOT_INSPECTION_DTLS WHERE  SPOT_INSP_STATUS =?  AND REINSPECTION_STATUS='A'  and  REINSPECT_SR_USER_ID= ";

	public static final String SPOT_VIEW_DET="SELECT  distinct ispctd.REG_TXN_ID,PROPERTY_TXN_ID,NEW_TOTAL_AREA,NEW_CONSTRUCTED_AREA,NEW_L1_TYPE,NEW_L2_TYPE,insp.CREATED_BY,TO_CHAR(insp.CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_ACTUAL_DATE,'dd/mm/yyyy'),CHANGE_IN_VALUATION,INSPECTION_COMMENTS,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  dist WHERE dist.DISTRICT_ID=insp.DISTRICT_ID) district_name,OFFICE_ID,DR_USER_ID,TOTAL_AREA,CONSTRUCTED_AREA,L1_TYPE,L2_TYPE,FLOOR_TXN_ID,RE_TOTAL_AREA,RE_CONSTRUCTED_AREA,RE_L1_TYPE,RE_L2_TYPE,RE_CHANGE_IN_VALUATION,RE_INSPECTION_COMMENTS,REINSPECTION_DATE,insp.update_date,SPOT_INSP_STATUS,insp.district_id ,ispctd.MARKET_VALUE,ispctd.NEW_MARKET_VALUE,ispctd.GUIDELINE_VALUE,ispctd.NEW_GUIDELINE_VALUE"+
	"  FROM IGRS_SPOT_INSPECTION_DTLS insp,IGRS_SPOT_INSPECTED_DETAILS ispctd WHERE "+
	" insp.REG_COMPLETION_ID=ispctd.REG_COMPLETION_ID "+
	" and insp.REG_COMPLETION_ID = ";


	public static final String SPOT_SCH_DET=" SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(CREATED_DATE,'dd/mm/yyyy'), SPOT_INSP_STATUS,PROPERTY_TXN_ID " +
	"FROM IGRS_SPOT_INSPECTION_DETAILS WHERE  REG_TXN_ID= ";

	public static final String SPOT_COMP_DET="SELECT distinct  spot.REG_TXN_ID,TO_CHAR(spot.CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(reg.CREATED_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy'),reg.PROPERTY_ID,SPOT_INSP_STATUS,SR_USER_ID,REG.DISTRICT_ID  "+
	" FROM  IGRS_SPOT_INSPECTION_DTLS spot,IGRS_REG_PROPRTY_DTLS reg"+
	" WHERE   spot.REG_TXN_ID=reg.REG_TXN_ID  and  spot.REG_TXN_ID= ";

	public static final String SPOT_SCH_UPDATE="UPDATE  IGRS_SPOT_INSPECTION_DETAILS SET  SPOT_INSP_STATUS='S', " +
	" INSPECTION_PLANNED_DATE =?, SR_SCHEDULE_REMARKS=?  WHERE REG_TXN_ID =? ";

	public static final String SPOT_COMP_UPDATE1="UPDATE IGRS_SPOT_INSPECTION_DTLS SET REINSPECTION_STATUS='C',RE_INSPECTION_ACTUAL_DATE=?, " +
	"RE_CHANGE_IN_VALUATION =?,REINSPECT_SR_USER_ID=?,FOUND_CORRECT=?,COMPLAINT_ID=? , RE_UPLOAD_PATH=? " +
	"  WHERE REG_COMPLETION_ID =?"; //Ramesh commented on 07 Jan 13

	public static final String SPOT_COMP_UPDATE="UPDATE IGRS_SPOT_INSPECTION_DTLS SET SPOT_INSP_STATUS='C',INSPECTION_ACTUAL_DATE=?, " +
	" CHANGE_IN_VALUATION =?, INSPECTED_SR_USER_ID=?,FOUND_CORRECT=?,COMPLAINT_ID=?,UPLOAD_PATH=?, PHOTO_FILENAME=?, " +
	" UPDATE_DATE = SYSDATE WHERE REG_COMPLETION_ID =? ";


	public static final String SPOT_INSPECTION_INSERT="INSERT INTO IGRS_SPOT_OBSERVATION_TABLE(REGISTRATION_FEE,NEW_REGISTRATION_FEE,STAMP_DUTY,NEW_STAMP_DUTY,REMARK,NEW_REMARK,REG_COMPLETION_ID,JANPADK_DUTY,NEW_JANPAD_DUTY,MUNICIPAL_DUTY,NEW_MUNICIPAL_DUTY,UPKAR_DUTY,NEW_UPKAR_DUTY,TOTAL_DUTY,NEW_TOTAL_DUTY) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


	public static final String SPOT_RE_INSPECTION_INSERT="INSERT INTO IGRS_SPOT_REOBSERVATION_TABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SPOT_COMP_INSERT = "INSERT INTO IGRS_SPOT_INSPECTED_DETAILS(REG_TXN_ID," +
	" PROPERTY_TXN_ID,FLOOR_TXN_ID,NEW_TOTAL_AREA,NEW_CONSTRUCTED_AREA, NEW_L1_TYPE,NEW_L2_TYPE,CREATED_DATE,CREATED_BY,TOTAL_AREA,CONSTRUCTED_AREA,L1_TYPE,L2_TYPE,NEW_GUIDELINE_VALUE,GUIDELINE_VALUE,MARKET_VALUE,NEW_MARKET_VALUE,REG_COMPLETION_ID)" +
	" VALUES(?,?,?,?,?,?,?,SYSDATE,?,?,?,?,?,?,?,?,?,?)";

	public static final String SPOT_COMP_INSERT1 = "UPDATE IGRS_SPOT_INSPECTED_DETAILS SET RE_TOTAL_AREA =?,RE_CONSTRUCTED_AREA=?, RE_L1_TYPE=?,RE_L2_TYPE=?,UPDATE_DATE=SYSDATE,UPDATE_BY=? where REG_COMPLETION_ID=? and " +
	" PROPERTY_TXN_ID =? and FLOOR_TXN_ID =";

	public static final String SPOT_COMP_INSERT_NO_FLOOR = "UPDATE IGRS_SPOT_INSPECTED_DETAILS SET RE_TOTAL_AREA =?,RE_CONSTRUCTED_AREA=?, RE_L1_TYPE=?,RE_L2_TYPE=?,UPDATE_DATE=SYSDATE,UPDATE_BY=?,RE_MARKET_VALUE=?,RE_GUIDELINE_VALUE=? where REG_COMPLETION_ID=? and " +
	" PROPERTY_TXN_ID =? ";

	public static final String SPOT_DR_REQ_DET="SELECT   REG_TXN_ID,TO_CHAR(CREATED_DATE,'dd/mm/yyyy'),SPOT_INSP_STATUS ,OFFICE_ID," +
	"  SR_USER_ID,INSPECTION_COMMENTS FROM IGRS_SPOT_INSPECTION_DTLS WHERE  REG_TXN_ID= ";

	public static final String SPOT_DR_VIEW_DET="SELECT  distinct insp.REG_TXN_ID,PROPERTY_TXN_ID,NEW_TOTAL_AREA,NEW_CONSTRUCTED_AREA,NEW_L1_TYPE,NEW_L2_TYPE,insp.CREATED_BY,TO_CHAR(insp.CREATED_DATE,'dd/mm/yyyy'), "+
	" TO_CHAR(INSPECTION_DATE,'dd/mm/yyyy'),TO_CHAR(INSPECTION_ACTUAL_DATE,'dd/mm/yyyy'),CHANGE_IN_VALUATION,INSPECTION_COMMENTS,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER  dist WHERE dist.DISTRICT_ID=insp.DISTRICT_ID) district_name,OFFICE_ID,DR_USER_ID,TOTAL_AREA,CONSTRUCTED_AREA,L1_TYPE,L2_TYPE "+
	"  FROM IGRS_SPOT_INSPECTION_DTLS insp,IGRS_SPOT_INSPECTED_DETAILS ispctd WHERE "+
	" insp.REG_TXN_ID=ispctd.REG_TXN_ID "+
	" and insp.REG_TXN_ID = ";

	public static final String DR_REQ_UPDATE = "UPDATE IGRS_SPOT_INSPECTION_DTLS SET REINSPECTION_DATE =SYSDATE,SPOT_INSP_STATUS='R',REINSPECTION_STATUS = ?,REINSPECT_SR_USER_ID =?," +
	"DR_REASON_FOR_RESPOT_INSP=? ,DR_REINSPECTION_REMARKS =? WHERE  REG_TXN_ID=?";

	public static final String GET_FLOOR_DATA = "select (select PROPERTY_TYPE_L1_NAME from igrs_prop_type_l1_master where PROPERTY_TYPE_L1_ID=regf.l1_type_id)L1,(select PROPERTY_TYPE_L2_NAME from igrs_prop_type_l2_master where PROPERTY_TYPE_L2_ID=regf.l2_type_id)L2, regf.CONSTRUCTED_AREA,regf.AREA from IGRS_REG_FLOOR_DETAILS regf where regf.PROPERTY_ID=?";

	public static final String GET_FLOOR_RE_DATA = "SELECT L1_TYPE,"
		+ " L2_TYPE,"
		+ " NEW_L1_TYPE,"
		+"  NEW_L2_TYPE,"
		+ " CONSTRUCTED_AREA,"
		+ " NEW_CONSTRUCTED_AREA,"
		+ " TOTAL_AREA,"
		+ " NEW_TOTAL_AREA"
		+ " from IGRS_SPOT_PROPERTY_DETAILS"
		+ " where property_id =?"
		+ " and property_type_id='2'";

	public static final String DR_REQ_UPDATE_DEACT = "UPDATE IGRS_SPOT_INSPECTION_DTLS SET REINSPECTION_STATUS = ? " +
	" WHERE  REG_TXN_ID=?";



	public static final String SELECT_SPOT_PROP_DET = "SELECT  RPD.DISTRICT_ID,RPD.TEHSIL_ID,RPD.AREA_TYPE_ID," +
	" RPD.MUNCIPAL_BODY_TYPE_ID,RPD.VIKAS_KHAND, RPD.LAYOUT_DETAIL,RPD.NAZOOL_SHEET_NUMBER," +
	" RPD.WARD_PATWARI_ID, RPD.MOHALLA_VILLAGE_ID,RPD.PROPERTY_ADDRESS,RPD.RI_CIRCLE," +
	" RPD.KHASRA_NUMBER,RPD.PROPERTY_TYPE_ID,RPD.TOTAL_AREA FROM IGRS_REG_PROPERTY_DETAILS RPD" +
	" WHERE   RPD.PROPERTY_TXN_ID  =";



	public static final String SELECT_SPOT_PROP_ID = " SELECT  DISTINCT  PROPERTY_ID FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID  = " ;



	public static final String SELECT_SPOT_PROP_DETAILS= "SELECT  DISTINCT  regp.PROPERTY_ID,valf.AREA,valf.CONSTRUCTED_AREA," +
	"(SELECT PROPERTY_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER P1  WHERE P1.PROPERTY_TYPE_L1_ID= valf.PROP_L1_TYPE__ID)  PROPERTY_TYPE_L1_NAME , " +
	"(SELECT PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER P2  WHERE P2.PROPERTY_TYPE_L2_ID= valf.PROP_L2_TYPE__ID)  PROPERTY_TYPE_L2_NAME,valf.FLOOR_TXN_ID,REG_TXN_ID,regp.market_value FROM IGRS_REG_PROPRTY_DTLS  regp,IGRS_PROP_FLOOR_DETAILS valf "+
	" WHERE REG_TXN_ID=? and regp.VAL_TXN_ID=valf.VAL_TXN_ID "+
	" union "+
	" SELECT  DISTINCT  regp.PROPERTY_ID,regp.AREA,null,(SELECT PROPERTY_TYPE_L1_NAME FROM IGRS_PROP_TYPE_L1_MASTER P1  WHERE P1.PROPERTY_TYPE_L1_ID= regp.L1_TYPE_ID)  PROPERTY_TYPE_NAME, "+
	" (SELECT PROPERTY_TYPE_L2_NAME FROM IGRS_PROP_TYPE_L2_MASTER P2  WHERE P2.PROPERTY_TYPE_L2_ID= regp.L2_TYPE_ID)  PROPERTY_TYPE_L2_NAME,null,REG_TXN_ID,regp.market_value FROM IGRS_REG_PROPRTY_DTLS  regp,IGRS_PROP_FLOOR_DETAILS valf "+
	" WHERE REG_TXN_ID=?  and regp.VAL_TXN_ID not in (select VAL_TXN_ID from IGRS_PROP_FLOOR_DETAILS )";

	public static final String SELECT_SPOT_PROP_STATUS = "SELECT  SPOT_FLAG  FROM IGRS_REG_PROPERTY_DETAILS WHERE PROPERTY_TXN_ID  = ? " ;

	public static final String SELECT_MARKET_VALUE = "select MARKET_VALUE,REG_TXN_ID from igrs_reg_proprty_dtls where property_id=?";

	public static final String SELECT_RE_MARKET_VALUE ="SELECT market_value,new_market_value,guideline_value,new_guideline_value,reg_txn_id from"
		+" IGRS_SPOT_INSPECTED_DETAILS where property_txn_id=?";

	public static final String SELECT_SPOT_GET_REG_INFO = "select reg_fee,stamp_duty from igrs_reg_chk_stamp_duty_dtls where reg_txn_id=?";

	public static final String SELECT_RE_SPOT_GET_REG_INFO = "SELECT REGISTRATION_FEE,STAMP_DUTY,REMARK,NEW_REGISTRATION_FEE,NEW_STAMP_DUTY,NEW_REMARK,JANPADK_DUTY,NEW_JANPAD_DUTY,MUNICIPAL_DUTY,NEW_MUNICIPAL_DUTY,UPKAR_DUTY,NEW_UPKAR_DUTY,TOTAL_DUTY,NEW_TOTAL_DUTY "+
	" FROM IGRS_SPOT_OBSERVATION_TABLE "+
	" WHERE REG_COMPLETION_ID=?";

	public static final String SELECT_WITHOUT_FLOOR_DETAILS = "select (select PROPERTY_TYPE_L1_NAME from igrs_prop_type_l1_master where PROPERTY_TYPE_L1_ID=regf.l1_type_id)L1,(select PROPERTY_TYPE_L2_NAME from igrs_prop_type_l2_master where PROPERTY_TYPE_L2_ID=regf.l2_type_id)L2, regf.market_value,regf.REG_TXN_ID,(select area from IGRS_REG_PROPRTY_DTLS where property_id=regf.PROPERTY_ID) from igrs_reg_proprty_dtls regf where regf.PROPERTY_ID=?";


	public static final String SELECT_RE_WITHOUT_FLOOR_DETAILS ="SELECT L1_TYPE,L2_TYPE, NEW_L1_TYPE, NEW_L2_TYPE,REG_TXN_ID,TOTAL_AREA,NEW_TOTAL_AREA FROM igrs_spot_property_details WHERE PROPERTY_ID=?";

	public static final String SP_DET_INSERT = "INSERT INTO IGRS_SPOT_INSPECTION_DETAILS(REG_TXN_ID," +
	" PROPERTY_TXN_ID,OFFICE_ID,SR_USER_ID,SR_REMARKS,SPOT_INSP_STATUS,SPOT_INSPECTION_TXN_ID,CREATED_DATE, REASON,REQUEST_TYPE_ID,REQUISITION_DATE)" +
	" VALUES(?,?,(SELECT OFFICE_ID FROM IGRS_USER_OFFICE_MAPPING WHERE USER_ID = ?),?,?,'Y'," +
	" ?,SYSDATE,?,?,?)";


	public static final String IGRS_CHECK_CASE_MONITORING ="select * from IGRS_CASE_IMPOUND_DOCUMENT where spot_status is not null and status in('I','O') and reg_txn_id=?";

	public static final String IGRS_GET_STAMP_DETAILS ="select reg_fee,GRAM_DUTY,STAMP_DUTY,NAGAR_DUTY,UPKAR,TOTAL from igrs_reg_chk_stamp_duty_dtls where REG_TXN_ID=?";

	public static final String SP_SPOT_ASSIGN_INSERT = "INSERT INTO IGRS_SPOT_INSPECTION_DTLS(SPOT_INSPECTION_TXN_ID,REG_TXN_ID," +
	" ZONE_ID,DISTRICT_ID,OFFICE_ID,DR_USER_ID,SR_USER_ID,SPOT_INSP_STATUS,CREATED_DATE, INSPECTION_DATE,CREATED_BY,VALUATION_AMOUNT,REG_FEE,STAMP_DUTY,TEHSIL_ID)" +
	" VALUES(?,?,?,?,(SELECT OFFICE_ID FROM IGRS_USER_OFFICE_MAPPING WHERE USER_ID = ?),?,?,'Y',SYSDATE,SYSDATE+?,?,?,?,?,?)";

	public static final String SP_SPOT_ASSIGN_INSERT_DATA = "INSERT INTO IGRS_SPOT_INSPECTION_DTLS"
		+"  ("
		+"      SPOT_INSPECTION_TXN_ID,"
		+"     REG_COMPLETION_ID,"
		+"     ZONE_ID,"
		+"      DISTRICT_ID,"
		+"     OFFICE_ID,"
		+"     DR_USER_ID,"
		+"     SR_ASSIGNED_USER_ID,"
		+"     SPOT_INSP_STATUS,"
		+"     INSPECTION_DATE,"
		+"     CREATED_BY,"
		+"     CREATED_DATE,"
		+"     VALUATION_AMOUNT,"
		+"      REG_FEE,"
		+"     STAMP_DUTY,"
		+"     TEHSIL_ID,"
		+"      SR_USER_ID,DIG_NAME"
		+"    )"
		+"   VALUES"
		+"   (?,?,?,?,?,?,?,'Y',SYSDATE+?,?,SYSDATE,?,?,?,?,?,?)";


	public static final String SP_SPOT_ASSIGN_INSERT_TRANSACTION_DATA = "INSERT INTO IGRS_SPOT_TRANSACTION_TABLE"
		+"  ("
		+"      SPOT_TXN_ID,"
		+"     DISTRICT_ID,"
		+"     ZONE_ID,"
		+"      DR_NAME,"
		+"     SR_ASSIGNED_NAME,"
		+"     CREATED_BY,"
		+"     CREATED_DATE,"
		+"     INSPECTION_DATE,"
		+"     STATUS_SPOT,SR_ASSIGNED_USER_ID,DIG_NAME"
		+"    )"
		+"   VALUES"
		+"   (?,?,?,?,?,?,SYSDATE,SYSDATE+?,'Y',?,?)";


	public static final String SP_DET_UPDATE = "UPDATE IGRS_SPOT_INSPECTION_DETAILS SET " +
	"PROPERTY_TXN_ID=?, " +
	//"OFFICE_ID=(SELECT OFFICE_ID FROM IGRS_USER_OFFICE_MAPPING WHERE USER_ID = ?), " +
	"SR_USER_ID=?, " +
	"SR_REMARKS=?, " +
	"SPOT_INSP_STATUS='Y', " +
	//"SPOT_INSPECTION_TXN_ID=?, " +
	"CREATED_DATE=SYSDATE, " +
	"REASON=?, " +
	"REQUEST_TYPE_ID=?, " +
	"REQUISITION_DATE=? where REG_TXN_ID =?";

	public static final String PROP_FLAG_UPDATE = "UPDATE IGRS_REG_PROPERTY_DETAILS set spot_flag ='C' WHERE  PROPERTY_TXN_ID =? ";

	///Added by Mohit soni
	public static final String SELECT_PROPERTY_TYPE_L2_DETAILS="SELECT PROPERTY_TYPE_L2_ID, PROPERTY_TYPE_L2_NAME"+
	" FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY"+
	" WHERE PROPERTY_TYPE_L1_ID_PV  =?"+
	" AND PROPERTY_TYPE_L2_STATUS='A'";


	public static final String SELECT_PROPERTY_TYPE_L2_DETAILS_HINDI="SELECT PROPERTY_TYPE_L2_ID, H_PROPERTY_TYPE_L2_NAME"+
	" FROM IGRS_PROP_TYPE_L2_MASTER_DUMMY"+
	" WHERE PROPERTY_TYPE_L1_ID_PV  =?"+
	" AND PROPERTY_TYPE_L2_STATUS='A'";

	public static final String SPOT_CHECK_ROLE ="Select b.OFFICE_TYPE_NAME from IGRS_OFFICE_MASTER a ,IGRS_OFFICE_TYPE_MASTER b where a.OFFICE_TYPE_ID=b.OFFICE_TYPE_ID and a.OFFICE_ID=?";

	public static final String SPOT_GET_ZONE_DIG ="SELECT D.ZONE_ID FROM IGRS_DISTRICT_MASTER D,igrs_office_master O WHERE o.district_id=d.district_id AND  o.office_id=?";

	public static final String SPOT_GET_DISTRICT_ZONE_LIST = "Select district_name,district_id from igrs_district_master where zone_id=?";

	public static final String SPOT_GET_DISTRICT_ZONE_LIST_HINDI = "Select h_district_name,district_id from igrs_district_master where zone_id=?";

	public static final String WARD_QUERY = "Select WARD_PATWARI_ID, "
		+"WARD_PATWARI_NAME from IGRS_WARD_PATWARI_MASTER "
		+"Where TEHSIL_ID=? AND WARD_PATWARI_STATUS='A' "
		+"AND AREA_TYPE_ID=? AND WARD_OR_PATWARI=? "
		+"ORDER BY WARD_PATWARI_NAME ASC";

	public static final String WARD_QUERY_HINDI = "Select WARD_PATWARI_ID, "
		+"H_WARD_PATWARI_NAME from IGRS_WARD_PATWARI_MASTER "
		+"Where TEHSIL_ID=? AND WARD_PATWARI_STATUS='A' "
		+"AND AREA_TYPE_ID=? AND WARD_OR_PATWARI=? "
		+"ORDER BY H_WARD_PATWARI_NAME ASC";

	public static final String SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L2 ="select DISTINCT dist.DISTRICT_NAME,"+
	"dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID, reginit.REG_TXN_ID,teh.TEHSIL_ID,teh.TEHSIL_NAME,stmp.FINAL_MARKET_VALUE,stmp.REG_FEE,stmp.TOTAL "+
	"from  IGRS_REG_TXN_DETLS reg,IGRS_REG_PROPRTY_DTLS reginit,IGRS_DISTRICT_MASTER dist,IGRS_TEHSIL_MASTER teh ,igrs_zone_master ZON ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp , IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=?   and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and   reg.REG_TXN_ID=reginit.REG_TXN_ID AND reg.SPOT_FLAG is null "+
	" and	 reginit.L1_TYPE_ID=?  and	 reginit.L2_TYPE_ID=?  and    reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID AND dist.DISTRICT_ID=teh.DISTRICT_ID AND  dist.DISTRICT_ID=?  	 and 	  reg.REG_TXN_ID=stmp.REG_TXN_ID "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) " +
	" and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND " +
	"trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')  order by stmp.FINAL_MARKET_VALUE  desc ";

	public static final String SPOT_ASSIGNED_CRITERIA_DOCUMENT_PROPERTY_L1 ="select DISTINCT dist.DISTRICT_NAME,"+
	"dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID, reginit.REG_TXN_ID,teh.TEHSIL_ID,teh.TEHSIL_NAME,stmp.FINAL_MARKET_VALUE,stmp.REG_FEE,stmp.TOTAL "+
	"from  IGRS_REG_TXN_DETLS reg,IGRS_REG_PROPRTY_DTLS reginit,IGRS_DISTRICT_MASTER dist,IGRS_TEHSIL_MASTER teh ,igrs_zone_master ZON ,"+
	"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp , IGRS_SPOT_CRITERIA_AREA area "+
	"where reg.INSTRUMENTS_ID=?  and	 reginit.AREA_TYPE_ID=area.AREA_TYPE_ID and   reg.REG_TXN_ID=reginit.REG_TXN_ID AND reg.SPOT_FLAG is null "+
	" and	 reginit.L1_TYPE_ID=?    and    reginit.DISTRICT_ID=dist.DISTRICT_ID   and  dist.ZONE_ID=zon.ZONE_ID AND dist.DISTRICT_ID=teh.DISTRICT_ID AND  dist.DISTRICT_ID=?  	 and 	  reg.REG_TXN_ID=stmp.REG_TXN_ID "+
	" and   stmp.FINAL_MARKET_VALUE between (select SLAB_MIN_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) " +
	" and (select SLAB_MAX_RANGE from  IGRS_SPOT_INSP_SLAB_MASTER rangc where   rangc.SLAB_ID=?) AND " +
	"trunc(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')  order by stmp.FINAL_MARKET_VALUE  desc ";

	public static final String SPOT_GET_DISTRICT_DRO = "SELECT D.DISTRICT_ID,D.DISTRICT_NAME FROM "+
	"igrs_district_master D, "+
	"igrs_user_role_group_mapping USR, "+
	"igrs_rgroup_role_mapping RGG "+
	",igrs_role_office_mapping ROFC, "+
	"igrs_office_master OM " +

	"WHERE usr.user_id=? "+
	" AND rgg.role_group_id in (usr.role_group_id)"+
	" AND rofc.role_id in (rgg.role_id)"+
	" AND om.office_id in (rofc.office_id)"+
	" AND d.district_id in (om.district_id);";


	public static final String SPOT_GET_DISTRICT_ZONES = "SELECT D.District_id,z.zone_id from igrs_district_master d,igrs_zone_master z,igrs_office_master o where o.office_id=?"+
	" and d.district_id=o.district_id and d.zone_id=z.zone_id";


	public static final String REST_OF_QUERY = ") and sub.VAL_TXN_ID = reginit.VAL_TXN_ID group by dist.DISTRICT_NAME,zon.ZONE_NAME,"+
	"dist.DISTRICT_ID,zon.ZONE_ID";


	public static final String IGRS_SPOT_FINAL_QUERY = "SELECT dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" group by dist.DISTRICT_NAME,zon.ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";

	public static final String IGRS_SPOT_FINAL_QUERY_HINDI = "SELECT dist.H_DISTRICT_NAME,dist.DISTRICT_ID,zon.H_ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" group by dist.H_DISTRICT_NAME,zon.H_ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";


	public static final String IGRS_SPOT_FINAL_QUERY_ZONE="SELECT dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" AND tmp.REGISTRATION_ZONE_ID =?"
		+" group by dist.DISTRICT_NAME,zon.ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";


	public static final String IGRS_SPOT_FINAL_QUERY_ZONE_HINDI="SELECT dist.H_DISTRICT_NAME,dist.DISTRICT_ID,zon.H_ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" AND tmp.REGISTRATION_ZONE_ID =?"
		+" group by dist.H_DISTRICT_NAME,zon.H_ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";

	public static final String IGRS_SPOT_FINAL_QUERY_DISTRICT="SELECT dist.DISTRICT_NAME,dist.DISTRICT_ID,zon.ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" AND tmp.REGISTRATION_DIST_ID =?"
		+" group by dist.DISTRICT_NAME,zon.ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";

	public static final String IGRS_SPOT_FINAL_QUERY_DISTRICT_HINDI="SELECT dist.H_DISTRICT_NAME,dist.DISTRICT_ID,zon.H_ZONE_NAME,zon.ZONE_ID,COUNT(DISTINCT tmp.REG_NO) FROM IGRS_SPOT_TEMP_DTLS tmp, IGRS_DISTRICT_MASTER dist, igrs_zone_master ZON, igrs_state_master stat WHERE dist.DISTRICT_ID = tmp.REGISTRATION_DIST_ID "
		+" AND dist.ZONE_ID        =zon.ZONE_ID"
		+" AND ZON.STATE_ID        =stat.STATE_ID"
		+" AND stat.STATE_ID       ='1'"
		+" AND tmp.REGISTRATION_DIST_ID =?"
		+" group by dist.H_DISTRICT_NAME,zon.H_ZONE_NAME,"
		+" dist.DISTRICT_ID,zon.ZONE_ID";

	public static final String IGRS_SPOT_ADD_TEMP = "INSERT INTO IGRS_SPOT_TEMP_DTLS(REG_NO,REGISTRATION_DIST_ID,REGISTRATION_ZONE_ID,FINAL_MARKET_VALUE,REG_FEE,STAMP_DUTY,DR_NAME,SR_NAME,REGISTRATION_OFFICE_ID,REG_TXN_ID,DIG_NAME,REG_COMPLETED_BY,PROPERTY_TYPE_ID,TEHSIL_ID,AREA_TYPE_ID,VILLAGE_MOHALLA_ID,WARD_PATWARI_ID,SUB_AREA_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String IGRS_SPOT_ADD_TEMP_NO = "INSERT INTO IGRS_SPOT_TEMP_DTLS(REG_NO,REGISTRATION_DIST_ID,REGISTRATION_ZONE_ID,FINAL_MARKET_VALUE,REG_FEE,STAMP_DUTY,DR_NAME,SR_NAME,REGISTRATION_OFFICE_ID,REG_TXN_ID,DIG_NAME,REG_COMPLETED_BY,PROPERTY_TYPE_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";



	public static final String IGRS_SPOT_GET_SR_LIST1 = "SELECT E.USER_ID,E.FIRST_NAME"

		+" FROM IGRS_ROLE_FN_ACTIVITY_MAPPING A,"

		+" IGRS_RGROUP_ROLE_MAPPING B,"

		+" IGRS_USER_ROLE_GROUP_MAPPING C,"

		+" IGRS_ROLE_OFFICE_MAPPING D,"

		+" IGRS_USER_REG_DETAILS E"

		+" WHERE E.USER_ID=C.USER_ID"

		+" AND C.ROLE_GROUP_ID=B.ROLE_GROUP_ID"

		+" AND B.ROLE_ID=A.ROLE_ID"

		+" AND D.ROLE_ID=A.ROLE_ID"

		+" AND A.ACTIVITY_ID='ACT_287'"

		+" AND D.OFFICE_ID=?";


	/*public static final String IGRS_SPOT_GET_SR_LIST="SELECT distinct  a.user_id,j.first_name"
+" FROM IGRS_USER_ROLE_GROUP_MAPPING a,"
+"  IGRS_ROLE_FN_ACTIVITY_MAPPING b,"
+"  IGRS_RGROUP_ROLE_MAPPING d,"
+"  IGRS_ROLE_OFFICE_MAPPING e,"
+"  IGRS_OFFICE_MASTER o,"
+"  IGRS_USER_REG_DETAILS j"
+" WHERE a.ROLE_GROUP_ID=d.ROLE_GROUP_ID"
+" AND  e.ROLE_OFFICE_MAP_ID = d.ROLE_OFFICE_MAP_ID"
+" AND j.user_id=a.user_id"
+" AND o.office_id= ?"
+" AND e.office_id = o.office_id"
+" AND  e.ROLE_ID = b.ROLE_ID"
+" AND b.ACTIVITY_ID='ACT_287'";*/

	public static final String IGRS_SPOT_GET_SR_LIST = "SELECT DISTINCT C.USER_ID, E.FIRST_NAME "
		+ "FROM IGRS_USER_ROLE_GROUP_MAPPING C, "
		+ "  IGRS_RGROUP_ROLE_MAPPING D, "
		+ "  IGRS_USER_REG_DETAILS E "
		+ "WHERE C.ROLE_GROUP_ID     = D.ROLE_GROUP_ID "
		+ "AND E.USER_ID = C.USER_ID "
		+ "AND C.ROLE_ACTIVE         = 'A' "
		+ "AND D.STATUS              = 'A' "
		+ "AND D.ROLE_OFFICE_MAP_ID IN "
		+ "  ((SELECT A.ROLE_OFFICE_MAP_ID "
		+ "  FROM IGRS_ROLE_OFFICE_MAPPING A, "
		+ "    IGRS_ROLE_FN_ACTIVITY_MAPPING B "
		+ "  WHERE A.ROLE_ID   = B.ROLE_ID "
		+ "  AND A.OFFICE_ID   = ? "
		+ "  AND A.STATUS      = 'A' "
		+ "  AND B.ACTIVITY_ID = 'ACT_287' "
		+ "  )union (SELECT A.ROLE_OFFICE_MAP_ID "
		+ "  FROM IGRS_ROLE_OFFICE_MAPPING A, "
		+ "    IGRS_ROLE_FN_ACTIVITY_MAPPING B "
		+ "  WHERE A.ROLE_ID   = B.ROLE_ID "
		+ "  AND A.OFFICE_ID   = ? "
		+ "  AND A.STATUS      = 'A' "
		+ "  AND B.ACTIVITY_ID = 'ACT_233' "
		+ "  ))";;
		public static final String IGRS_GET_ALL_INFO =" SELECT tmp.registration_dist_id,"+
		" (SELECT district_name"+
		"	  FROM igrs_district_master"+
		"	  WHERE district_id=tmp.registration_dist_id"+
		"	  ), tmp.registration_zone_id,"+
		"	  (SELECT zone_name FROM igrs_zone_master where zone_id=tmp.registration_zone_id),"+
		"	  ptm.property_type_id,"+
		"	  ptm.property_type_name,"+
		"	  tmp.tehsil_id,"+
		"	  (SELECT tehsil_name FROM igrs_tehsil_master where tehsil_id=tmp.tehsil_id"+
		"	  ) ,"+
		"	  tmp.ward_patwari_id,"+
		"	  (SELECT ward_patwari_name"+
		"	  FROM igrs_ward_patwari_master where ward_patwari_id=tmp.ward_patwari_id"+
		"	  ),"+
		"	  tmp.area_type_id,"+
		"	  (SELECT area_type_name"+
		"	  FROM igrs_area_type_master"+
		"	  WHERE area_type_id=tmp.area_type_id"+
		"	  ),"+
		"	  tmp.VILLAGE_MOHALLA_ID,"+
		"	  (SELECT COLONY_NAME FROM IGRS_COLONY_MASTER  where COLONY_ID=tmp.VILLAGE_MOHALLA_ID"+
		"	  ),"+//changed by akansha on 26th july
		"	  tmp.SUB_AREA_ID,"+
		"	  (SELECT SUB_AREA_TYPE_NAME"+
		"	  FROM IGRS_SUB_AREA_TYPE_MASTER"+
		"	  WHERE SUB_AREA_TYPE_ID=tmp.SUB_AREA_ID"+
		"	  ),"+
		"	  tmp.reg_no,"+
		"	  tmp.registration_office_id,"+
		"	  tmp.reg_txn_id,"+
		"	  tmp.sr_name,"+
		"	  tmp.dr_name,"+
		"	  tmp.final_market_value,"+
		"	  tmp.stamp_duty,"+
		"	  tmp.reg_fee,tmp.DIG_NAME,tmp.reg_completed_by"+
		"	FROM IGRS_SPOT_TEMP_DTLS tmp,"+

		"	  igrs_property_type_master ptm"+

		"	WHERE tmp.REGISTRATION_DIST_ID=?"+
		"	AND ptm.property_type_id      = tmp.property_type_id";


		public static final String IGRS_GET_ALL_INFO_HINDI = " SELECT tmp.registration_dist_id,"+
		" (SELECT h_district_name"+
		"	  FROM igrs_district_master"+
		"	  WHERE district_id=tmp.registration_dist_id"+
		"	  ), tmp.registration_zone_id,"+
		"	  (SELECT H_zone_name FROM igrs_zone_master where zone_id=tmp.registration_zone_id),"+
		"	  ptm.property_type_id,"+
		"	  ptm.property_type_name,"+
		"	  tmp.tehsil_id,"+
		"	  (SELECT H_tehsil_name FROM igrs_tehsil_master where tehsil_id=tmp.tehsil_id"+
		"	  ) ,"+
		"	  tmp.ward_patwari_id,"+
		"	  (SELECT H_ward_patwari_name"+
		"	  FROM igrs_ward_patwari_master where ward_patwari_id=tmp.ward_patwari_id"+
		"	  ),"+
		"	  tmp.area_type_id,"+
		"	  (SELECT H_area_type_name"+
		"	  FROM igrs_area_type_master"+
		"	  WHERE area_type_id=tmp.area_type_id"+
		"	  ),"+
		"	  tmp.VILLAGE_MOHALLA_ID,"+
		"	(SELECT H_COLONY_NAME FROM IGRS_COLONY_MASTER  where COLONY_ID=tmp.VILLAGE_MOHALLA_ID"+
		"	  ),"+//changed by akansha on 26th july
		"	  tmp.SUB_AREA_ID,"+
		"	  (SELECT H_SUB_AREA_TYPE_NAME"+
		"	  FROM IGRS_SUB_AREA_TYPE_MASTER"+
		"	  WHERE SUB_AREA_TYPE_ID=tmp.SUB_AREA_ID"+
		"	  ),"+
		"	  tmp.reg_no,"+
		"	  tmp.registration_office_id,"+
		"	  tmp.reg_txn_id,"+
		"	  tmp.sr_name,"+
		"	  tmp.dr_name,"+
		"	  tmp.final_market_value,"+
		"	  tmp.stamp_duty,"+
		"	  tmp.reg_fee,tmp.DIG_NAME,tmp.reg_completed_by"+
		"	FROM IGRS_SPOT_TEMP_DTLS tmp,"+

		"	  igrs_property_type_master ptm"+

		"	WHERE tmp.REGISTRATION_DIST_ID=?"+
		"	AND ptm.property_type_id      = tmp.property_type_id";


		public static final String IGRS_GET_ALL_INFO_NO = "SELECT"
			+"  dm.district_id,"
			+"  dm.district_name,"
			+"  zm.zone_id,"
			+"  zm.zone_name,"
			+"  ptm.property_type_id,"
			+"  ptm.property_type_name,"
			+"  tmp.reg_no,"
			+"  tmp.registration_office_id,"
			+"  tmp.reg_txn_id,"
			+" tmp.sr_name,"
			+" tmp.dr_name,"
			+" tmp.final_market_value,"
			+"  tmp.stamp_duty,"
			+"  tmp.reg_fee,tmp.DIG_NAME,tmp.REG_COMPLETED_BY"
			+" FROM IGRS_SPOT_TEMP_DTLS tmp,"
			+"   igrs_district_master dm,"
			+"  igrs_zone_master zm,"
			+"  igrs_property_type_master ptm"
			+" WHERE tmp.REGISTRATION_DIST_ID=?"
			+"   AND dm.district_id=tmp.registration_dist_id"
			+"   AND zm.zone_id=tmp.registration_zone_id"
			+"  AND ptm.property_type_id= tmp.property_type_id";

		public static final String IGRS_GET_ALL_INFO_NO_HINDI = "SELECT"
			+"  dm.district_id,"
			+"  dm.h_district_name,"
			+"  zm.zone_id,"
			+"  zm.h_zone_name,"
			+"  ptm.property_type_id,"
			+"  ptm.h_property_type_name,"
			+"  tmp.reg_no,"
			+"  tmp.registration_office_id,"
			+"  tmp.reg_txn_id,"
			+" tmp.sr_name,"
			+" tmp.dr_name,"
			+" tmp.final_market_value,"
			+"  tmp.stamp_duty,"
			+"  tmp.reg_fee,tmp.DIG_NAME, tmp.REG_COMPLETED_BY"
			+" FROM IGRS_SPOT_TEMP_DTLS tmp,"
			+"   igrs_district_master dm,"
			+"  igrs_zone_master zm,"
			+"  igrs_property_type_master ptm"
			+" WHERE tmp.REGISTRATION_DIST_ID=?"
			+"   AND dm.district_id=tmp.registration_dist_id"
			+"   AND zm.zone_id=tmp.registration_zone_id"
			+"  AND ptm.property_type_id= tmp.property_type_id";

		public static final String IGRS_GET_TEMP_DISTRICTS = "SELECT DISTINCT REGISTRATION_DIST_ID,REGISTRATION_ZONE_ID  FROM IGRS_SPOT_TEMP_DTLS";



		public static final String IGRS_SPOT_TEMP_EMPTY ="DELETE IGRS_SPOT_TEMP_DTLS";

		public static final String IGRS_SPOT_TEMP_GET_PROPERTY ="SELECT PROPERTY_TYPE_ID FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=?";

		public static final String IGRS_SPOT_GET_TOTAL_COUNT = "select count(reg_completion_id) from igrs_spot_inspection_dtls where created_by=?";
		public static final String IGRS_SPOT_GET_COMPLETED_COUNT="select count(reg_completion_id) from igrs_spot_inspection_dtls where created_by=? and spot_insp_status='C'";
		public static final String IGRS_SPOT_GET_PENDING = "select count(reg_completion_id) from igrs_spot_inspection_dtls where created_by=? and spot_insp_status='Y';";

		public static final String IGRS_SPOT_GET_VIEW_DETAILS="select distinct a.zone_id,(select zone_name from igrs_zone_master where zone_id=a.zone_id),a.DIG_NAME,a.DR_USER_ID,(select first_name||' '||last_name from igrs_user_reg_details where user_id=a.sr_assigned_user_id),a.sr_assigned_user_id,a.total,b.comp from "
			+" (select distinct zone_id,DIG_NAME,DR_USER_ID,sr_assigned_user_id,count(reg_completion_id) as total from igrs_spot_inspection_dtls where created_by=? and office_id IN (select OFFICE_ID from igrs_office_master where parent_id=?) and trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') group by DIG_NAME,DR_USER_ID,sr_assigned_user_id,zone_id) a "
			+" left join "
			+" (select distinct zone_id,DIG_NAME,DR_USER_ID,sr_assigned_user_id,count(reg_completion_id) as comp from igrs_spot_inspection_dtls where created_by=? and office_id IN (select OFFICE_ID from igrs_office_master where parent_id=?)  and trunc(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and spot_insp_status in ('C','R') group by DIG_NAME,DR_USER_ID,sr_assigned_user_id,zone_id) b "
			+"on "
			+" a.zone_id=b.zone_id and "
			+" nvl(a.DIG_NAME,'app')           =nvl(b.DIG_NAME,'app') and "
			+" nvl(a.DR_USER_ID,'ass') =nvl(b.DR_USER_ID,'ass') and "
			+" a.sr_assigned_user_id=b.sr_assigned_user_id";


		public static final String IGRS_RE_SPOT_GET_VIEW_DETAILS = "select distinct a.zone_id,(select zone_name from igrs_zone_master where zone_id=a.zone_id),a.DIG_NAME,a.DR_USER_ID,(select first_name||' '||last_name from igrs_user_reg_details where user_id=a.REINSPECT_SR_USER_ID),a.REINSPECT_SR_USER_ID,a.total,b.comp from "
			+" (select distinct zone_id,DIG_NAME,DR_USER_ID,REINSPECT_SR_USER_ID,count(reg_completion_id) as total from igrs_spot_inspection_dtls where RE_CREATED_BY=? and trunc(RE_CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') group by DIG_NAME,DR_USER_ID,REINSPECT_SR_USER_ID,zone_id) a "
			+" left join "
			+" (select distinct zone_id,DIG_NAME,DR_USER_ID,REINSPECT_SR_USER_ID,count(reg_completion_id) as comp from igrs_spot_inspection_dtls where RE_CREATED_BY=? and trunc(RE_CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and REINSPECTION_STATUS='C' group by DIG_NAME,DR_USER_ID,REINSPECT_SR_USER_ID,zone_id) b "
			+" on "
			+" a.zone_id=b.zone_id and  "
			+" nvl(a.DIG_NAME,'jmdshikvinmoh')=nvl(b.DIG_NAME,'jmdshikvinmoh') and "
			+" nvl(a.DR_USER_ID,'jmdshikvinmoh')=nvl(b.DR_USER_ID,'jmdshikvinmoh') and "
			+" a.REINSPECT_SR_USER_ID=b.REINSPECT_SR_USER_ID";

		public static final String IGRS_SPOT_GET_DASHBOARD_VALUES="select distinct ae.SPOT_TXN_ID,ae.DR_NAME,(select first_name||' '||last_name from igrs_user_reg_details where user_id=ae.SR_ASSIGNED_USER_ID),to_char(ae.CREATED_DATE,'dd-mon-yy'),to_char(ae.INSPECTION_DATE,'dd-mon-yy'),ae.STATUS_SPOT,ae.Dig_name,(select zone_name from igrs_zone_master where zone_id=ae.zone_id) FROM IGRS_SPOT_TRANSACTION_TABLE ae, igrs_spot_inspection_dtls spi where trunc(ae.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and ae.SPOT_TXN_ID=spi.SPOT_INSPECTION_TXN_ID ";
		
		//added by saurav
		public static final String IGRS_SPOT_DASHBOARD_VALUES_DRO="  AND ae.created_by=? and spi.OFFICE_ID IN (SELECT OFFICE_ID FROM IGRS_OFFICE_MASTER WHERE PARENT_ID=?)";
		public static final String IGRS_SPOT_DASHBOARD_VALUES_IGO="  and spi.OFFICE_ID IN (SELECT OFFICE_ID FROM IGRS_OFFICE_MASTER WHERE district_id in (SELECT district_id FROM igrs_district_master where zone_id=?))";	
		public static final String IGRS_SPOT_DASHBOARD_VALUES_SRO="  and ae.SR_ASSIGNED_USER_ID=? and spi.OFFICE_ID=?";

		public static final String IGRS_RE_SPOT_GET_DASHBOARD_VALUES="SELECT DISTINCT ae.RE_SPOT_TXN_ID,"
			+"  ae.DR_NAME,"
			+"  (SELECT first_name   ||' '||last_name  FROM igrs_user_reg_details  WHERE user_id=ae.RE_INSPECTOR_ASSIGNED_NAME  ),"
			+"  TRUNC(ae.CREATED_DATE),"
			+"  ae.RE_INSPECTION_DATE,"
			+"  ae.STATUS_SPOT,"
			+"  ae.Dig_name,"
			+"  (SELECT zone_name FROM igrs_zone_master WHERE zone_id=ae.zone_id )"
			+"  FROM IGRS_SPOT_RE_TRANSACTION_TABLE ae"
			+" WHERE TRUNC(CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy')"
			+" AND created_by=?";


		public static final String IGRS_SPOT_GET_DRILL_DASHBOARD_VALUES = "select INSP.REG_COMPLETION_ID,(SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID) ZONE_NAME,(SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID=INSP.DISTRICT_ID) DISTRICT_NAME,(select first_name||' '||last_name from igrs_user_reg_details where user_id=INSP.SR_ASSIGNED_USER_ID),sr_user_id,TO_CHAR(INSP.CREATED_DATE,'dd/mm/yyyy'),  TO_CHAR(INSP.INSPECTION_DATE,'dd/mm/yyyy'),INSP.found_correct,INSP.COMPLAINT_ID from IGRS_SPOT_INSPECTION_DTLS INSP where INSP.SPOT_INSP_STATUS in ('C','Y','R') and SPOT_INSPECTION_TXN_ID=?";

		public static final String IGRS_RE_SPOT_GET_DRILL_DASHBOARD_VALUES = "SELECT INSP.REG_COMPLETION_ID,"
			+ " (SELECT ZONE_NAME FROM IGRS_ZONE_MASTER WHERE ZONE_ID=INSP.ZONE_ID"
			+ " ) ZONE_NAME,"
			+ " (SELECT DISTRICT_NAME"
			+ " FROM IGRS_DISTRICT_MASTER"
			+ " WHERE DISTRICT_ID=INSP.DISTRICT_ID"
			+ " ) DISTRICT_NAME,"
			+ " (SELECT first_name  ||' ' ||last_name  FROM igrs_user_reg_details  WHERE user_id=INSP.REINSPECT_SR_USER_ID  ),"
			+ " sr_user_id,"
			+ " TO_CHAR(INSP.RE_CREATED_DATE,'dd/mm/yyyy'),"
			+ " TO_CHAR(INSP.REINSPECTION_DATE,'dd/mm/yyyy'),"
			+ " INSP.found_correct,"
			+ " INSP.COMPLAINT_ID,(SELECT first_name  ||' ' ||last_name  FROM igrs_user_reg_details  WHERE user_id=INSP.SR_ASSIGNED_USER_ID  ),INSP.REINSPECTION_STATUS"
			+"  FROM IGRS_SPOT_INSPECTION_DTLS INSP"
			+" WHERE INSP.REINSPECTION_STATUS IN ('C','Y')"
			+" AND RE_SPOT_INSPECTION_TXN_ID   =?";



		public static final String IGRS_SPOT_GET_RE_INPECTION_OFFICERS1 ="   SELECT distinct E.USER_ID,E.FIRST_NAME"

			+" FROM IGRS_ROLE_FN_ACTIVITY_MAPPING A,"

			+" IGRS_RGROUP_ROLE_MAPPING B,"

			+" IGRS_USER_ROLE_GROUP_MAPPING C,"

			+" IGRS_ROLE_OFFICE_MAPPING D,"

			+" IGRS_USER_REG_DETAILS E"

			+" WHERE E.USER_ID=C.USER_ID"

			+" AND C.ROLE_GROUP_ID=B.ROLE_GROUP_ID"

			+" AND B.ROLE_ID=A.ROLE_ID"

			+" AND D.ROLE_ID=A.ROLE_ID"

			+" AND A.ACTIVITY_ID='ACT_501'"

			+" AND D.OFFICE_ID=?";


		public static final String IGRS_SPOT_GET_RE_INPECTION_OFFICERS = "SELECT  DISTINCT a.user_id, "+
		" upper(j.first_name||' '||j.middle_name||' '||j.last_name) FROM IGRS_USER_ROLE_GROUP_MAPPING a,  IGRS_ROLE_FN_ACTIVITY_MAPPING b,  IGRS_RGROUP_ROLE_MAPPING d,"
		+"  IGRS_ROLE_OFFICE_MAPPING e,"
		+"  IGRS_OFFICE_MASTER o,"
		+"  IGRS_USER_REG_DETAILS j"
		+" WHERE a.ROLE_GROUP_ID=d.ROLE_GROUP_ID"
		+" AND  e.ROLE_OFFICE_MAP_ID = d.ROLE_OFFICE_MAP_ID"
		+" AND j.user_id=a.user_id"
		+" AND o.office_id=?"
		+" AND e.office_id = o.office_id"
		+" AND  e.ROLE_ID = b.ROLE_ID"
		+" AND b.ACTIVITY_ID='ACT_501'";

		public static final String IGRS_SPOT_UPDATE_FLAG_SELECT= "select SPOT_INSPECTION_TXN_ID from igrs_spot_inspection_dtls where reg_completion_id=?";

		public static final String IGRS_RE_SPOT_UPDATE_FLAG_SELECT= "select RE_SPOT_INSPECTION_TXN_ID from igrs_spot_inspection_dtls where reg_completion_id=?";

		public static final String IGRS_SPOT_GET_FLAG="select SPOT_INSP_STATUS from IGRS_SPOT_INSPECTION_DTLS where SPOT_INSPECTION_TXN_ID = ? and SPOT_INSP_STATUS='Y'";


		public static final String IGRS_RE_SPOT_GET_FLAG="select REINSPECTION_STATUS from IGRS_SPOT_INSPECTION_DTLS where RE_SPOT_INSPECTION_TXN_ID = ? and REINSPECTION_STATUS='Y'";


		public static final String IGRS_SPOT_UPDATE_FLAGS = "update IGRS_SPOT_TRANSACTION_TABLE set STATUS_SPOT='C' where SPOT_TXN_ID=? ";

		public static final String IGRS_RE_SPOT_UPDATE_FLAGS = "update IGRS_SPOT_RE_TRANSACTION_TABLE set STATUS_SPOT='C' where RE_SPOT_TXN_ID=? ";


		//commented by akansha
		/*public static final String IGRS_RE_GET_FULL_DATA_STATE = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='N' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id" ;


		public static final String IGRS_RE_GET_FULL_DATA_ZONE = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='N' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id and insp.zone_id=?" ;


		public static final String IGRS_RE_GET_FULL_DATA_DISTRICT = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='N' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id and insp.district_id=?" ;
		 */

		public static final String IGRS_RE_GET_FULL_DATA_STATE = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='Y' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id" ;


		public static final String IGRS_RE_GET_FULL_DATA_ZONE = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='Y' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id and insp.zone_id=?" ;


		public static final String IGRS_RE_GET_FULL_DATA_DISTRICT = "select z.zone_name ,insp.district_id,insp.DR_USER_ID,insp.SR_USER_ID,insp.SR_ASSIGNED_USER_ID,"
			+" insp.REG_COMPLETION_ID,d.district_name,insp.VALUATION_AMOUNT,insp.REG_FEE,"
			+" insp.STAMP_DUTY,insp.created_date,insp.inspection_actual_date,insp.DIG_NAME,insp.zone_id from igrs_spot_inspection_dtls insp, igrs_district_master d,"
			+" igrs_zone_master z where  TRUNC(insp.INSPECTION_ACTUAL_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND TO_DATE (?, 'dd/mm/yyyy') and  SPOT_INSP_STATUS='C'"
			+" AND FOUND_CORRECT ='Y' and  insp.DISTRICT_ID=d.district_id and insp.zone_id=z.zone_id and insp.district_id=?" ;

		public static final String IGRS_GET_OFFICE_NAMES = "select office_id,office_name from igrs_office_master where district_id=? and office_status='A'";


		public static final String IGRS_SPOT_RE_ISNP_INSERT = "UPDATE igrs_spot_inspection_dtls"
			+" SET re_created_date    =SYSDATE,"
			+"  re_created_by        =?,"
			+"  SPOT_INSP_STATUS     ='R' ,"
			+"  REINSPECTION_STATUS  ='Y',"
			+"  REINSPECT_SR_USER_ID =?,"
			+"  REINSPECTION_DATE    =SYSDATE + ?,"
			+"  RE_SPOT_INSPECTION_TXN_ID = ?"
			+"  WHERE REG_COMPLETION_ID=?";


		public static final String IGRS_SPOT_RE_TRANSACTION_TABLE = "";


		public static final String IGRS_SPOT_GET_USER = "Select FIRST_NAME || ' ' || LAST_NAME from IGRS_USER_REG_DETAILS where USER_ID=?";

		public static final String IGRS_SPOT_INSERT_RE_TRANSACTION = "INSERT into IGRS_SPOT_RE_TRANSACTION_TABLE (RE_SPOT_TXN_ID,RE_INSPECTION_DATE,RE_INSPECTOR_ASSIGNED_NAME,CREATED_BY,CREATED_DATE,STATUS_SPOT,DISTRICT_ID,ZONE_ID,DIG_NAME,DR_NAME) values(?,sysdate+?,?,?,sysdate,'Y',?,?,?,?)";


		public static final String IGRS_SPOT_GET_PERCENTAGE = "select attribute_value from  IGRS_CONFIG_PARAM_LIST where attribute_id ='ATT_159'";

		public static final String IGRS_INSERT_INTO_CASE_MONITORING ="insert into IGRS_CASE_IMPOUND_DOCUMENT (REG_TXN_ID,COMPLAINT_ID,STATUS,CREATED_BY,CREATED_DATE,SPOT_POST_INSPECTION_REG_FEE,SPOT_POST_INSPECTION_DUTY,SPOT_STATUS)values(?,?,?,?,SYSDATE,?,?,?)";


		public static final String IGRS_SELECT_PROPERTY_ID="select property_id,prop_type_id from igrs_reg_proprty_dtls where reg_txn_id=?";


		// Dateeeee   3 Decemeber Mohit

		public static final String IGRS_SPOT_INSERT_PROPERTY_DETAILS = "insert into igrs_spot_property_details (REG_TXN_ID,property_id,NEW_TOTAL_AREA,TOTAL_AREA,NEW_CONSTRUCTED_AREA,CONSTRUCTED_AREA,NEW_L2_TYPE,L2_TYPE,NEW_L1_TYPE,L1_TYPE,CREATED_BY,CREATED_DATE,PROPERTY_TYPE_ID) values (?,?,?,?,?,?,?,?,?,?,?,sysdate,?)";

		public static final String IGRS_SPOT_INSERT_INPECTION_DETAILS = "insert into igrs_spot_property_details (REG_TXN_ID,property_id,POST_INSPECTION_COMMENTS,CREATED_BY,CREATED_DATE)values(?,?,?,?,sysdate)";
		public static final String IGRS_SPOT_UPDATE_PROPERTY_DETAILS = "update igrs_spot_property_details set REG_TXN_ID=?,RE_TOTAL_AREA=?,RE_CONSTRUCTED_AREA=?,RE_L1_TYPE=?,RE_L2_TYPE=?,RE_CREATED_BY=?,RE_CREATED_DATE=SYSDATE  where PROPERTY_ID=?";

		public static final String IGRS_SPOT_RE_UPDATE ="update igrs_spot_property_details set RE_INSPECTION_COMMENTS=?,RE_CREATED_BY=?,RE_CREATED_DATE=SYSDATE where PROPERTY_ID=?";


		public static final String SPOT_COMP_INSERT_WO = "INSERT INTO IGRS_SPOT_INSPECTED_DETAILS(REG_TXN_ID," +
		" PROPERTY_TXN_ID,CREATED_DATE,CREATED_BY,NEW_GUIDELINE_VALUE,GUIDELINE_VALUE,MARKET_VALUE,NEW_MARKET_VALUE,REG_COMPLETION_ID)" +
		" VALUES(?,?,SYSDATE,?,?,?,?,?,?)";

		public static final String SPOT_COMP_UPDATE_WO = "update IGRS_SPOT_INSPECTED_DETAILS set REG_TXN_ID=?,PROPERTY_TXN_ID=?,UPDATE_BY=?,RE_MARKET_VALUE=?,RE_GUIDELINE_VALUE=? where REG_COMPLETION_ID=?";



		public static final String GET_GUIDELINE_VALUE ="select District_id,TEHSIL_ID,WARD_ID,MOHALLA_ID,PROP_TYPE_ID,L1_TYPE_ID,L2_TYPE_ID from igrs_reg_proprty_dtls where property_id=?";

		//Changes Property Valuation

		public static String SUB_AREA_NAME_EN_UR="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

		public static String SUB_AREA_NAME_HI_UR="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A' AND TEHSIL_ID=?";

		public static String SUB_AREA_NAME_EN="SELECT SUB_AREA_TYPE_ID,SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";

		public static String SUB_AREA_NAME_HI="SELECT SUB_AREA_TYPE_ID,H_SUB_AREA_TYPE_NAME FROM IGRS_SUB_AREA_TYPE_MASTER WHERE AREA_TYPE_ID=? AND SUB_AREA_TYPE_STATUS='A'";


		public static String WARD_PATWARI_NAME_EN="SELECT WPM.WARD_PATWARI_ID,WPM.WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

		public static String WARD_PATWARI_NAME_HI="SELECT WPM.WARD_PATWARI_ID ,WPM.H_WARD_PATWARI_NAME,WM.SUBAREA_WARD_MAPPING_ID FROM IGRS_WARD_PATWARI_MASTER WPM,IGRS_SUBAREA_WARD_MAPPING WM WHERE WM.WARD_PATWARI_ID=WPM.WARD_PATWARI_ID AND WM.SUB_AREA_TYPE_ID=? AND TEHSIL_ID =? AND WARD_PATWARI_STATUS='A'";

		public static String COLONY_NAME_EN="SELECT C.COLONY_ID, C.COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";

		public static String COLONY_NAME_HI="SELECT C.COLONY_ID, C.H_COLONY_NAME,M.COLONY_WARD_MAPPING_ID,C.APPLICABLE_SUBCLAUSE_ID FROM IGRS_COLONY_MASTER C ,IGRS_COLONY_WARD_MAPPING M WHERE M.COLONY_ID=C.COLONY_ID AND C.COLONY_STATUS='A' AND M.SUBAREA_WARD_MAPPING_ID=?";

		//for changes

		public static final String SPOT_CURRENT_PROPERTY_LIST_PLOT="SELECT L.PROPERTY_TYPE_NAME ||' - ' || L1.PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID"+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 " +
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID"+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" AND  L.PROPERTY_TYPE_ID=1";
		public static final String SPOT_CURRENT_PROPERTY_LIST_PLOT_HI="SELECT L.H_PROPERTY_TYPE_NAME ||' - ' || L1.H_PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID"+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 " +
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID"+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" AND  L.PROPERTY_TYPE_ID=1";

		public static final String SPOT_CURRENT_PROPERTY_LIST_BUILD="SELECT L.PROPERTY_TYPE_NAME ||' - ' || sub.PROP_SUB_TYPE_NAME||' - ' || L1.PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID "+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 ,"+
		" IGRS_PROP_SUB_TYPE_MASTER SUB"+
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID "+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND l1.prop_sub_type_id= sub.prop_sub_type_id"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" and l.PROPERTY_TYPE_ID=2";

		public static final String SPOT_CURRENT_PROPERTY_LIST_BUILD_HI="SELECT L.H_PROPERTY_TYPE_NAME ||' - ' || sub.H_PROP_SUB_TYPE_NAME||' - ' || L1.H_PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID "+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 ,"+
		" IGRS_PROP_SUB_TYPE_MASTER SUB"+
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID "+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND l1.prop_sub_type_id= sub.prop_sub_type_id"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" and l.PROPERTY_TYPE_ID=2";

		public static final String SPOT_CURRENT_PROPERTY_LIST_AGRI="SELECT L.PROPERTY_TYPE_NAME ||' - ' || sub.PROP_SUB_TYPE_NAME||' - ' || L1.PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID "+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 ,"+
		" IGRS_PROP_SUB_TYPE_MASTER SUB "+
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID "+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND l1.prop_sub_type_id= sub.prop_sub_type_id"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" and l.PROPERTY_TYPE_ID=3";
		public static final String SPOT_CURRENT_PROPERTY_LIST_AGRI_HI="SELECT L.H_PROPERTY_TYPE_NAME ||' - ' || sub.H_PROP_SUB_TYPE_NAME||' - ' || L1.H_PROP_TYPE_L1_NAME, L1.PROP_TYPE_L1_ID "+
		" FROM IGRS_PROPERTY_TYPE_MASTER L,"+
		" IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 ,"+
		" IGRS_PROP_SUB_TYPE_MASTER SUB "+
		" WHERE L.PROPERTY_TYPE_ID     =L1.PROPERTY_TYPE_ID "+
		" AND PROP_TYPE_L1_STATUS  ='A'"+
		" AND l1.prop_sub_type_id= sub.prop_sub_type_id"+
		" AND PROPERTY_TYPE_STATUS     ='A'"+
		" and l.PROPERTY_TYPE_ID=3";
		public static final String GET_PROPERTY_NAME_SINGLE=" SELECT P.PROPERTY_TYPE_NAME||'-'||(SELECT PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_ID=L1.PROP_SUB_TYPE_ID)||'-'||L1.PROP_TYPE_L1_NAME FROM IGRS_PROPERTY_TYPE_MASTER P,IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 WHERE L1.PROPERTY_TYPE_ID=P.PROPERTY_TYPE_ID AND L1.PROP_TYPE_L1_ID=?";

		public static final String GET_PROPERTY_NAME_SINGLE_HI=" SELECT P.H_PROPERTY_TYPE_NAME||'-'||(SELECT H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_ID=L1.PROP_SUB_TYPE_ID)||'-'||L1.H_PROP_TYPE_L1_NAME FROM IGRS_PROPERTY_TYPE_MASTER P,IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 WHERE L1.PROPERTY_TYPE_ID=P.PROPERTY_TYPE_ID AND L1.PROP_TYPE_L1_ID=?";


		public static final String GET_PROPERTY_NAME_THREE=" SELECT P.PROPERTY_TYPE_NAME||'-'||(SELECT PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_ID=?)||'-'||L1.PROP_TYPE_L1_NAME FROM IGRS_PROPERTY_TYPE_MASTER P,IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 WHERE P.PROPERTY_TYPE_ID=? AND L1.PROP_TYPE_L1_ID=?";

		public static final String GET_PROPERTY_NAME_THREE_HI=" SELECT P.H_PROPERTY_TYPE_NAME||'-'||(SELECT H_PROP_SUB_TYPE_NAME FROM IGRS_PROP_SUB_TYPE_MASTER WHERE  PROP_SUB_TYPE_ID=?)||'-'||L1.H_PROP_TYPE_L1_NAME FROM IGRS_PROPERTY_TYPE_MASTER P,IGRS_PROP_TYPE_L1_MASTER_DUMMY L1 WHERE P.PROPERTY_TYPE_ID=? AND L1.PROP_TYPE_L1_ID=?";



		public static final String GET_PROPERTY_TYPE=" SELECT PROPERTY_TYPE_ID FROM IGRS_PROP_TYPE_L1_MASTER_DUMMY WHERE PROP_TYPE_L1_ID=?";

		public static final String SEARCH_PLOT="SELECT DISTINCT reg.REGISTRATION_NUMBER,reg.REGISTRATION_DIST_ID,reg.REGISTRATION_ZONE_ID,"+
		"stmp.final_market_value,"+
		"stmp.reg_fee,"+
		"stmp.stamp_duty,"+
		"reg.dr_name,reg.sr_name,reg.registration_office_id,reginit.reg_txn_id,reg.DIG_NAME, reg.update_by FROM "+
		"IGRS_REG_TXN_DETLS reg,"+
		"IGRS_REG_PROPRTY_DTLS reginit,"+
		"IGRS_PROP_SUBCLAUSE_MAP sub,"+
		"IGRS_PROP_PLOT_DETLS plot,"+
		"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp "+
		"WHERE reg.INSTRUMENTS_ID =? AND reg.REG_TXN_ID = reginit.REG_TXN_ID"+
		" AND reg.REG_TXN_ID   =stmp.REG_TXN_ID "+
		"AND reg.registration_number IS NOT NULL "+
		"AND reg.SPOT_FLAG is null "
		+"and reginit.VAL_TXN_ID=plot.VAL_TXN_ID "+
		"AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		//+" AND TRUNC(reg.CREATED_DATE) BETWEEN TO_DATE (?, 'dd/mm/yyyy') AND  TO_DATE (?,'dd/mm/yyyy')"
		+" AND reg.update_date BETWEEN TO_DATE (?, 'dd/mm/yyyy hh24:mi:ss') AND  TO_DATE (?,'dd/mm/yyyy hh24:mi:ss')"
		+" AND reg.registration_office_id IN (SELECT OFFICE_ID FROM igrs_office_master WHERE PARENT_ID=?) ";



		public static final String SEARCH_AGRI="SELECT DISTINCT reg.REGISTRATION_NUMBER,reg.REGISTRATION_DIST_ID,reg.REGISTRATION_ZONE_ID,"+
		"stmp.final_market_value,"+
		"stmp.reg_fee,"+
		"stmp.stamp_duty,"+
		"reg.dr_name,reg.sr_name,reg.registration_office_id,reginit.reg_txn_id,reg.DIG_NAME FROM "+
		"IGRS_REG_TXN_DETLS reg,"+
		"IGRS_REG_PROPRTY_DTLS reginit,"+
		"IGRS_PROP_SUBCLAUSE_MAP sub,"+
		"IGRS_PROP_AGRI_DETLS agri,"+
		"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp "+
		"WHERE reg.INSTRUMENTS_ID =? AND reg.REG_TXN_ID = reginit.REG_TXN_ID"+
		" AND reg.REG_TXN_ID   =stmp.REG_TXN_ID "+
		"AND reg.registration_number IS NOT NULL "+
		" AND reg.SPOT_FLAG is null "
		+"and reginit.VAL_TXN_ID=agri.VAL_TXN_ID "+
		"AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?)"
		+" AND reg.update_date BETWEEN TO_DATE (?, 'dd/mm/yyyy hh24:mi:ss') AND  TO_DATE (?,'dd/mm/yyyy hh24:mi:ss')"
		+" AND reg.registration_office_id IN (SELECT OFFICE_ID FROM igrs_office_master WHERE PARENT_ID=?) ";

		public static final String SEARCH_BUILD="SELECT DISTINCT reg.REGISTRATION_NUMBER,reg.REGISTRATION_DIST_ID,reg.REGISTRATION_ZONE_ID,"+
		"stmp.final_market_value,"+
		"stmp.reg_fee,"+
		"stmp.stamp_duty,"+
		"reg.dr_name,reg.sr_name,reg.registration_office_id,reginit.reg_txn_id,reg.DIG_NAME FROM "+
		"IGRS_REG_TXN_DETLS reg,"+
		"IGRS_REG_PROPRTY_DTLS reginit,"+
		"IGRS_PROP_SUBCLAUSE_MAP sub,"+
		"IGRS_PROP_BUILDING_DETLS build,"+
		"IGRS_REG_CHK_STAMP_DUTY_DTLS stmp ";

		public static final String SEARCH_BUILD1=" WHERE reg.INSTRUMENTS_ID =? AND reg.REG_TXN_ID = reginit.REG_TXN_ID"+
		" AND reg.REG_TXN_ID   =stmp.REG_TXN_ID "+
		"AND reg.registration_number IS NOT NULL "+
		" AND reg.SPOT_FLAG is null "
		+"and reginit.VAL_TXN_ID=build.VAL_TXN_ID "+
		"AND stmp.FINAL_MARKET_VALUE BETWEEN"
		+" (SELECT SLAB_MIN_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) AND "

		+" ( SELECT   SLAB_MAX_RANGE FROM IGRS_SPOT_INSP_SLAB_MASTER rangc WHERE rangc.SLAB_ID=?) "
		+" AND reg.update_date BETWEEN TO_DATE (?, 'dd/mm/yyyy hh24:mi:ss') AND  TO_DATE (?,'dd/mm/yyyy hh24:mi:ss') "
		+" AND reg.registration_office_id IN (SELECT OFFICE_ID FROM igrs_office_master WHERE PARENT_ID=?) ";

		public static final String GET_EMAIL_ID = "select EMAIL from IGRS_EMP_MASTER where EMP_ID=?";

		public static final String INSERT_EMAIL_DATA = "insert into IGRS_EMAIL_DATA_CONTENT (EMAIL_DISPATCH_ID,EMAIL_TO_ADDRESS,EMAIL_CC_ADDRESS,EMAIL_SUBJECT,EMAIL_CONTENT,EMAIL_FROM_ADDRESS,EMAIL_SENT_FLAG,CREATED_BY,CREATED_DATE,EMAIL_HEADER,EMAIL_FOOTER)values(IGRS_SPOT_INS_TXN_ID_SEQ.nextval,?,?,?,?,?,?,?,sysdate,?,?)";

		public static final String GET_PROPERTY_IDS = "SELECT prop.property_id FROM igrs_reg_proprty_dtls prop  WHERE prop.reg_txn_id=?";

		public static final String GET_PROPERTY_RE_IDS = "SELECT property_id,post_inspection_comments from IGRS_SPOT_PROPERTY_DETAILS where reg_txn_id =?";

		public static final String GET_REG_TXN_ID = "SELECT REG_TXN_ID FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER = ?";

		public static final String GET_OFFICE_TYPE_ID = "select office_type_id from igrs_office_master where OFFICE_ID=?";

		public static final String GET_DIST_NAME_DR = " select OFFICE_ID,OFFICE_NAME from igrs_office_master where office_status='A' and parent_id in(?)";

		public static final String GET_DIST_NAME_DIG = "SELECT OFFICE_ID,OFFICE_NAME from igrs_office_master where office_status='A' " +
		"  AND OFFICE_ID IN(  select OFFICE_ID from igrs_office_master where office_status='A' and parent_id in(?)) " +
		"  OR PARENT_ID IN(  select OFFICE_ID from igrs_office_master where office_status='A' and parent_id in(?)) " +
		"  ORDER BY OFFICE_NAME";
		public static final String GET_DIST_NAME_IG = "  select OFFICE_ID,OFFICE_NAME from igrs_office_master where office_TYPE_ID!=?  ORDER BY OFFICE_NAME";

		public static final String DISTRICT_QUERY_HINDI = "Select DISTRICT_ID, "
			+"DISTRICT_NAME,H_DISTRICT_NAME from IGRS_DISTRICT_MASTER "
			+" Where STATE_ID=? AND DISTRICT_STATUS='A' "
			+"ORDER BY DISTRICT_NAME ASC";

		//Added By Akansha --- STQC

		public static final String POST_INSPECTION_COMMENTS = " select post_inspection_comments from igrs_spot_property_details where reg_txn_id = ? ";

		public static final String SELECT_OTHER_INFO = "select TO_CHAR(inspection_actual_date),found_correct, change_in_valuation,upload_path,photo_filename  from IGRS_SPOT_INSPECTION_DTLS where reg_completion_id=? " ;

		public static final String GET_DURATION = "SELECT ATTRIBUTE_VALUE FROM igrs_config_param_list WHERE attribute_id='ATT_204'";

		public static final String REPORT_DATE_DIFF = "select to_date(?,'dd-mm-yy')-to_date(?,'dd-mm-yy') from dual";
}
