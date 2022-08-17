/**
 * 
 */
package com.wipro.igrs.documentsearch.sql;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 *
 */
public class OldDocumentSearchSQL {
	// SQL Query to get all district from old deed registration
	public static final String OLD_SEARCH_DISTRICT_QUERY = "" + "SELECT DISTINCT DISTRICT FROM NDE_DCS_DETAILS "
			+ "UNION " + "SELECT DISTINCT DISTRICT FROM NDE_DEX_DETAILS " + "UNION "
			+ "SELECT DISTINCT DISTRICT FROM NDE_DM_DETAILS";
	// SQL Query to get all SROName from old deed registration
	public static final String OLD_SEARCH_SRO_NAME_QUERY = "" + "SELECT DISTINCT SRO_NAME FROM NDE_DCS_DETAILS "
			+ "UNION " + "SELECT DISTINCT SRO_NAME FROM NDE_DEX_DETAILS " + "UNION "
			+ "SELECT DISTINCT SRO_NAME FROM NDE_DM_DETAILS";

	public static final String OLD_DOC_SEARCH_A_DETAILS_INSERT = "" + "INSERT " + "INTO IGRS_DOC_SEARCH_DETAILS "
			+ "  ( " + "    DOC_SEARCH_TXN_ID, " + "    SEARCHED_USER_ID, " + "    CREATED_BY, " + "    FUNCTION_ID, "
			+ "    CREATED_DATE, " + "    DISTRICT_NAME, " + "    SRO_NAME, " + "    SR_NAME, " + "    BOOK_NO, "
			+ "    VOL_NO, " + "    SERIAL_NO, " + "    REG_DATE, " + "    IS_OLD, " + "    OLD_REGISTRATION_NUMBER "
			+ "  ) " + "  VALUES " + "  ( " + "    ?, " + "    ?, " + "    ?, " + "    ?, " + "    SYSDATE, "
			+ "    ?, " + "    ?, " + "    ?, " + "    ?, " + "    ?, " + "    ?, " + "    to_date(?,'DD-MON-YY'), "
			+ "    ?, " + "    ? " + "  )";
	public static final String BUYER_SELLER_DETAILS = "" + "SELECT A.id, " + "  A.name, " + "  A.ORGANIGATION, "
			+ "  A.age_in_years, " + "  A.father_name, " + "  A.address, " + "  A.tehsil, " + "  A.moholla, "
			+ "  A.ward_number, " + "  A.area_type, " + "  A.district, " + "  A.state, " + "  A.country, "
			+ "  A.email, " + "  A.caste, " + "  A.nationality, " + "  A.id_Name, " + "  A.major_minor, "
			+ "  A.ownership_share, " + "  A.role, " + "  A.individual_organization, " + "  A.SELF_POA, "
			+ "  A.POA_REG_DATE, " + "  A.POA_SRO_NAME, " + "  A.poa_Reg_No,  '-NA-' AS PARTY_TYPE "
			+ "FROM NDE_DCS_BUYER_SELLER_DETAILS A " + "INNER JOIN NDE_DCS_DETAILS B "
			+ "ON A.SALE_DETAIL_FK        = B.ID ";

	public static final String SALE_FLOOR_DETAILS = ""
			+ "SELECT A.ID , A.PROPERTY_NUMBER , A.FLOOR_TYPE , A.CONSTRUCTED_AREA , A.MEASUREMENT_UNIT ,  A.MARKET_VALUE "
			+ "FROM NDE_DCS_FLOOR_DETAILS A " + "INNER JOIN NDE_DCS_DETAILS B " + "ON A.SALE_DETAIL_FK        = B.ID ";
	public static final String SALE_KHASRA_DETAILS = ""
			+ "SELECT  A.ID , A.AREA , A.KHASRA_NUMBER , A.LAND_RECORD , A.MEASUREMENT_UNIT , A.PROPERTY_NUMBER, '-NA-' AS PARTY_ID "
			+ "FROM NDE_DCS_KHASRA_DETAILS A " + "INNER JOIN NDE_DCS_DETAILS B " + "ON A.SALE_DETAIL_FK        = B.ID ";

	public static final String SALE_PROPERTY_DETAILS = "" + "SELECT " + "  A.ID , " + "  A.NAZOOL , "
			+ "  A.NO_OF_CONTRUCTED_FLOORS , " + "  A.NORTH_DETAIL , " + "  A.ORIGINAL_PLOT_NUMBER , "
			+ "  A.PLOT_NUMBER , " + "  A.PROPERTY_ADDRESS , " + "  A.PROPERTY_AREA_TYPE , "
			+ "  A.PROPERTY_DISTRICT , " + "  A.PROPERTY_GOVERNING_MUNICIPAL , " + "  A.PROPERTY_MOHALLA , "
			+ "  A.PROPERTY_NUMBER , " + "  A.PROPERTY_SUBTYPEI , " + "  A.PROPERTY_SUBTYPEII , "
			+ "  A.PROPERTY_TEHSIL , " + "  A.PROPERTY_TYPE , " + "  A.PROPERTY_WARD_NUMBER , " + "  A.RIC_CIRCLE , "
			+ "  A.SOUTH_DETAIL , " + "  A.TOTAL_LAND_AREA , " + "  A.UNIT_OF_MEASUREMENT , " + "  A.VIKAS_KHAND , "
			+ "  A.WEST_DETAIL, A.EAST_DETAIL, " + "  A.PROPERTY_VILLAGE , " + "  '-NA-' AS PARTY_ID  "
			+ "FROM NDE_DCS_PROPERTY_DETAILS A " + "INNER JOIN NDE_DCS_DETAILS B "
			+ "ON A.SALE_DETAIL_FK        = B.ID ";

	public static final String SALE_STAMP_DETAILS = "" + "SELECT A.ID , " + "  A.STAMP_NUMBER "
			+ "FROM NDE_DCS_STAMP_DETAILS A " + "INNER JOIN NDE_DCS_DETAILS B " + "ON A.SALE_DETAIL_FK        = B.ID ";
	public static final String MASTER_PARTY_DETAILS = "" + "SELECT A.id, " + "  A.name, " + "  A.ORGANIGATION, "
			+ "  A.age_in_years, " + "  A.father_name, " + "  A.address, " + "  A.tehsil, " + "  A.moholla, "
			+ "  A.ward_number, " + "  A.area_type, " + "  A.district, " + "  A.state, " + "  A.country, "
			+ "  A.email, " + "  A.caste, " + "  A.nationality, " + "  A.id_Name, " + "  A.major_minor, "
			+ "  A.ownership_share, " + "  A.role, " + "  A.individual_organization, " + "  A.SELF_POA, "
			+ "  A.POA_REG_DATE, " + "  A.POA_SRO_NAME, " + "  A.poa_Reg_No,  '-NA-' AS PARTY_TYPE "
			+ "FROM NDE_DM_PARTY_DETAILS A " + "INNER JOIN NDE_DM_DETAILS B " + "ON A.MASTER_DETAIL_FK        = B.ID ";

	public static final String MASTER_FLOOR_DETAILS = ""
			+ "SELECT A.ID , A.PROPERTY_NUMBER , A.FLOOR_TYPE , A.CONSTRUCTED_AREA , A.MEASUREMENT_UNIT ,  A.MARKET_VALUE "
			+ "FROM NDE_DM_FLOOR_DETAILS A " + "INNER JOIN NDE_DM_DETAILS B " + "ON A.MASTER_DETAIL_FK        = B.ID ";
	public static final String MASTER_KHASRA_DETAILS = ""
			+ "SELECT  A.ID , A.AREA , A.KHASRA_NUMBER , A.LAND_RECORD , A.MEASUREMENT_UNIT , A.PROPERTY_NUMBER,  '-NA-' AS PARTY_ID "
			+ "FROM NDE_DM_KHASRA_DETAILS A " + "INNER JOIN NDE_DM_DETAILS B " + "ON A.MASTER_DETAIL_FK        = B.ID ";

	public static final String MASTER_PROPERTY_DETAILS = "" + "SELECT " + "  A.ID , " + "  A.NAZOOL , "
			+ "  A.NO_OF_CONTRUCTED_FLOORS , " + "  A.NORTH_DETAIL , " + "  A.ORIGINAL_PLOT_NUMBER , "
			+ "  A.PLOT_NUMBER , " + "  A.PROPERTY_ADDRESS , " + "  A.PROPERTY_AREA_TYPE , "
			+ "  A.PROPERTY_DISTRICT , " + "  A.PROPERTY_GOVERNING_MUNICIPAL , " + "  A.PROPERTY_MOHALLA , "
			+ "  A.PROPERTY_NUMBER , " + "  A.PROPERTY_SUBTYPEI , " + "  A.PROPERTY_SUBTYPEII , "
			+ "  A.PROPERTY_TEHSIL , " + "  A.PROPERTY_TYPE , " + "  A.PROPERTY_WARD_NUMBER , " + "  A.RIC_CIRCLE , "
			+ "  A.SOUTH_DETAIL , " + "  A.TOTAL_LAND_AREA , " + "  A.UNIT_OF_MEASUREMENT , " + "  A.VIKAS_KHAND , "
			+ "  A.WEST_DETAIL, A.EAST_DETAIL, " + "  '-NA-' AS PROPERTY_VILLAGE,  " + "  '-NA-' AS PARTY_ID  "
			+ "FROM NDE_DM_PROPERTY_DETAILS A " + "INNER JOIN NDE_DM_DETAILS B "
			+ "ON A.MASTER_DETAIL_FK        = B.ID ";

	public static final String MASTER_STAMP_DETAILS = "" + "SELECT A.ID , " + "  A.STAMP_NUMBER "
			+ "FROM NDE_DM_STAMP_DETAILS A " + "INNER JOIN NDE_DM_DETAILS B " + "ON A.MASTER_DETAIL_FK        = B.ID ";

	public static final String EXCHANGE_PARTY_DETAILS = "" + "SELECT A.id, " + "  A.PARTY_NAME, "
			+ "  A.PARTY_ORGANIGATION_NAME, " + "  A.PARTY_AGE_IN_YEARS, " + "  A.PARTY_FATHER_NAME, "
			+ "  A.PARTY_ADDRESS, " + "  A.PARTY_TEHSIL, " + "  A.PARTY_MOHOLLA, " + "  A.PARTY_WARD_NUMBER, "
			+ "  A.PARTY_AREA_TYPE, " + "  A.PARTY_DISTRICT, " + "  A.PARTY_STATE, " + "  A.PARTY_COUNTRY, "
			+ "  A.PARTY_EMAIL, " + "  A.PARTY_CASTE, " + "  A.PARTY_NATIONALITY, " + "  A.PARTY_ID_TYPE, "
			+ "  A.PARTY_MAJOR_MINOR, " + "  A.PARTY_OWNERSHIP_SHARE, " + "   '-NA-' AS role, "
			+ "  A.PARTY_INDIVIDUAL_ORGANIZATION, " + "  A.PARTY_SELF_POA, " + "   '-NA-' AS POA_REG_DATE, "
			+ "   '-NA-' AS POA_SRO_NAME, " + "   '-NA-' AS POA_REG_NO, A.PARTY_TYPE " + "FROM NDE_DEX_PARTY_DETAILS A "
			+ "INNER JOIN NDE_DEX_DETAILS B " + "ON A.EXCHANGE_DETAIL_FK      = B.ID ";
	public static final String EXCHANGE_STAMP_DETAILS = "" + "SELECT A.ID , " + "  A.STAMP_NUMBER "
			+ "FROM NDE_DEX_STAMP_DETAILS A " + "INNER JOIN NDE_DEX_DETAILS B "
			+ "ON A.EXCHANGE_DETAIL_FK        = B.ID ";

	public static final String EXCHANGE_KHASRA_DETAILS = ""
			+ "SELECT  A.ID , A.AREA , A.KHASRA_NUMBER , A.LAND_RECORD , A.MEASUREMENT_UNIT , A.PROPERTY_NUMBER, PARTY_ID "
			+ "FROM NDE_DEX_KHASRA_DETAILS A " + "INNER JOIN NDE_DEX_PARTY_DETAILS C "
			+ "ON A.EXCHANGE_PARTY_DTL_FK      = C.ID " + "INNER JOIN NDE_DEX_DETAILS B "
			+ "ON C.EXCHANGE_DETAIL_FK      = B.ID ";
	public static final String EXCHANGE_PROPERTY_DETAILS = "" + "SELECT A.ID , " + "  '-NA-' AS NAZOOL, "
			+ "  A.NO_OF_CONTRUCTED_FLOORS , " + "  A.NORTH_DETAIL , " + "  A.ORIGINAL_PLOT_NUMBER , "
			+ "  A.PLOT_NUMBER , " + "  A.PROPERTY_ADDRESS , " + "  A.PROPERTY_AREA_TYPE , "
			+ "  A.PROPERTY_DISTRICT , " + " '-NA-' AS PROPERTY_GOVERNING_MUNICIPAL , " + "  A.PROPERTY_MOHALLA , "
			+ "  A.PROPERTY_NUMBER , " + "  A.PROPERTY_SUBTYPEI , " + "  A.PROPERTY_SUBTYPEII , "
			+ "  A.PROPERTY_TEHSIL , " + "  A.PROPERTY_TYPE , " + "  A.PROPERTY_WARD_NUMBER , " + "  A.RIC_CIRCLE , "
			+ "  A.SOUTH_DETAIL , " + "  A.TOTAL_LAND_AREA , " + "  A.MEASUREMENT_UNIT , " + "  A.VIKAS_KHAND , "
			+ "  A.WEST_DETAIL, " + "  A.EAST_DETAIL, " + "  A.PROPERTY_VILLAGE_NAME, " + "  A.PARTY_ID "
			+ "FROM NDE_DEX_PROPERTY_DETAILS A " + "INNER JOIN NDE_DEX_PARTY_DETAILS C "
			+ "ON A.EXCHANGE_PARTY_DTL_FK      = C.ID " + "INNER JOIN NDE_DEX_DETAILS B "
			+ "ON C.EXCHANGE_DETAIL_FK      = B.ID ";

	public static final String OLD_DOC_SEARCH_DETAILS_INSERT = "" + "INSERT INTO IGRS_DOC_SEARCH_DETAILS  ( "
			+ "    DOC_SEARCH_TXN_ID, " + "    SEARCHED_USER_ID, " + "    CREATED_BY, " + "    FUNCTION_ID, "
			+ "    CREATED_DATE, " + "    DISTRICT_NAME, " + "    SRO_NAME, " + "    SR_NAME, " + "    BOOK_NO, "
			+ "    VOL_NO, " + "    SERIAL_NO, " + "    REG_DATE " + "  ) " + "  VALUES " + "  ( ?, " + "    ?, "
			+ "    ?, " + "    ?, " + "    SYSDATE, " + "    ?, " + "    ?, " + "    ?, " + "    ?, " + "    ?, "
			+ "    ?, " + "    to_date(?,'DD-MON-YY') " + "  )";
	public static final String SALE_DEED_DETAILS = ""
			+ "SELECT REGISTRATION_NUMBER, SRO_NAME, DISTRICT, BOOK_NUMBER, VOLUME_NUMBER, DOCUMENT_NUMBER, TO_CHAR( DATE_OF_REGISTRATION,'DD-MON-YY'), NAME_OF_SR "
			+ "FROM NDE_DCS_DETAILS";
	public static final String EXCHANGE_DEED_DETAILS = ""
			+ "SELECT REGISTRATION_NUMBER, SRO_NAME, DISTRICT, BOOK_NUMBER, VOLUME_NUMBER, DOCUMENT_NUMBER, TO_CHAR( DATE_OF_REGISTRATION,'DD-MON-YY'), NAME_OF_SR "
			+ "FROM NDE_DEX_DETAILS ";
	public static final String MASTER_DEED_DETAILS = ""
			+ "SELECT REGISTRATION_NUMBER, SRO_NAME, DISTRICT, BOOK_NUMBER, VOLUME_NUMBER, DOCUMENT_NUMBER, TO_CHAR( DATE_OF_REGISTRATION,'DD-MON-YY'), NAME_OF_SR "
			+ "FROM NDE_DM_DETAILS ";
	public static final String GET_OLD_SEARCH_RECORD_LIST_A = "select DISTINCT T1.DOCTXN,T1.REGNO,T1.CDATE,T1.N from ( select  idsd.DOC_SEARCH_TXN_ID DOCTXN,nvl(OLD_REGISTRATION_NUMBER,'-') REGNO,"
			+ " to_char(idsd.REG_DATE,'dd/mm/yyyy') CDATE,(select 'N' from dual) N from IGRS_DOC_SEARCH_DETAILS idsd,"
			+ "IGRS_SEARCH_PAYMENT_DETAILS ispd where idsd.DOC_SEARCH_TXN_ID=ispd.DOC_SEARCH_TXN_ID and"
			+ " idsd.function_id IN ('FUN_062','FUN_230') and ispd.function_id IN ('FUN_062','FUN_230') and ispd.PAYMENT_FLAG='P' and ispd.created_by=? "
			+ " AND idsd.IS_OLD='Y' order by idsd.created_date desc) T1   ORDER BY to_date(T1.CDATE ,'dd-mm-yyyy')  desc, T1.DOCTXN desc";
	public static final String GET_OLD_SEARCH_RECORD_LIST_B = "select DISTINCT T1.DOCTXN,T1.REGNO,T1.CDATE,T1.N from ( select  idsd.DOC_SEARCH_TXN_ID DOCTXN,nvl(OLD_REGISTRATION_NUMBER,'-') REGNO,"
			+ " to_char(idsd.REG_DATE,'dd/mm/yyyy') CDATE,(select 'N' from dual) N from IGRS_DOC_SEARCH_DETAILS idsd,"
			+ "IGRS_SEARCH_PAYMENT_DETAILS ispd where idsd.DOC_SEARCH_TXN_ID=ispd.DOC_SEARCH_TXN_ID and"
			+ " idsd.function_id IN ('FUN_063','FUN_232') and ispd.function_id IN ('FUN_063','FUN_232') and ispd.PAYMENT_FLAG='P' and ispd.created_by=? "
			+ " AND idsd.IS_OLD = 'Y' order by idsd.created_date desc) T1   ORDER BY to_date(T1.CDATE ,'dd-mm-yyyy')  desc, T1.DOCTXN desc";
	public static final String OLD_DOC_SEARCH_A_DETAILS_SELECT = "" + "SELECT DISTRICT_NAME, " + "  SRO_NAME, "
			+ "  SR_NAME, " + "  BOOK_NO, " + "  VOL_NO, " + "  SERIAL_NO, " + "  to_char(REG_DATE,'dd/mm/yyyy'), "
			+ "  IS_OLD " + "FROM IGRS_DOC_SEARCH_DETAILS "
			+ "WHERE DOC_SEARCH_TXN_ID = ? AND CREATED_BY = ? AND FUNCTION_ID = ?";

	public static final String SALE_DEED_DETAILS_B = "" + "SELECT A.REGISTRATION_NUMBER, A.SRO_NAME, A.DISTRICT, "
			+ "  A.BOOK_NUMBER, " + "  A.VOLUME_NUMBER, " + "  A.DOCUMENT_NUMBER, "
			+ "  TO_CHAR( A.DATE_OF_REGISTRATION,'DD-MON-YY'), A.NAME_OF_SR, " + "  B.NAME, " + "  B.ORGANIGATION, "
			+ "  D.PROPERTY_WARD_NUMBER, " + "  C.KHASRA_NUMBER " + "FROM NDE_DCS_DETAILS A "
			+ "INNER JOIN NDE_DCS_BUYER_SELLER_DETAILS B " + "ON A.ID=B.SALE_DETAIL_FK "
			+ "INNER JOIN NDE_DCS_KHASRA_DETAILS C "
			+ "ON A.ID          =C.SALE_DETAIL_FK INNER JOIN NDE_DCS_PROPERTY_DETAILS D ON A.ID = D.SALE_DETAIL_FK";
	public static final String EXCHANGE_DEED_DETAILS_B = "" + "SELECT A.REGISTRATION_NUMBER, A.SRO_NAME, A.DISTRICT, "
			+ "  A.BOOK_NUMBER, " + "  A.VOLUME_NUMBER, " + "  A.DOCUMENT_NUMBER, "
			+ "  TO_CHAR( A.DATE_OF_REGISTRATION,'DD-MON-YY'), A.NAME_OF_SR, " + "  B.PARTY_NAME, "
			+ "  B.PARTY_ORGANIGATION_NAME, " + "  D.PROPERTY_WARD_NUMBER, " + "  C.KHASRA_NUMBER "
			+ "FROM NDE_DEX_DETAILS A " + "INNER JOIN NDE_DEX_PARTY_DETAILS B " + "ON A.ID=B.EXCHANGE_DETAIL_FK "
			+ "INNER JOIN NDE_DEX_KHASRA_DETAILS C "
			+ "ON B.ID          =C.EXCHANGE_PARTY_DTL_FK  INNER JOIN NDE_DEX_PROPERTY_DETAILS D ON B.ID=D.EXCHANGE_PARTY_DTL_FK ";
	public static final String MASTER_DEED_DETAILS_B = "" + "SELECT A.REGISTRATION_NUMBER, A.SRO_NAME, A.DISTRICT, "
			+ "  A.BOOK_NUMBER, " + "  A.VOLUME_NUMBER, " + "  A.DOCUMENT_NUMBER, "
			+ "  TO_CHAR( A.DATE_OF_REGISTRATION,'DD-MON-YY'), A.NAME_OF_SR, " + "  B.NAME, " + "  B.ORGANIGATION, "
			+ "  D.PROPERTY_WARD_NUMBER, " + "  C.KHASRA_NUMBER " + "FROM NDE_DM_DETAILS A "
			+ "INNER JOIN NDE_DM_PARTY_DETAILS B " + "ON A.ID=B.MASTER_DETAIL_FK "
			+ "INNER JOIN NDE_DM_KHASRA_DETAILS C "
			+ "ON A.ID          =C.MASTER_DETAIL_FK INNER JOIN NDE_DM_PROPERTY_DETAILS D ON A.ID=D.MASTER_DETAIL_FK ";
	public static final String OLD_DOC_OFFICE_SEARCH_DETAILS_UPDATE = "UPDATE IGRS_DOC_SEARCH_DETAILS set ";
}
