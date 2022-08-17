package com.wipro.igrs.rcms.sql;

public class RevenueCaseSQL {

	public static final String GET_REG_PROP_IDs = " select property_id from igrs_reg_proprty_dtls where reg_txn_id=? AND PROP_TYPE_ID='3' ";
	public static final String GET_REG_COMMON_DETLS = " SELECT a.REGISTRATION_NUMBER,  a.DEED_ID,  b.DEED_TYPE_NAME, a.registration_office_id, a.form_vi, a.SIGNED_FORM_VIA, a.SIGNED_FORM_VIB, a.Registration_Dist_Id FROM igrs_reg_txn_detls a,  IGRS_DEED_TYPE_MASTER b WHERE reg_txn_id=? and a.DEED_ID=b.DEED_TYPE_ID ";
	public static final String GET_DOC_DATE = " select REG_COMP_DATE from IGRS_DEL_DOC_TXN_DETL where REGISTRATION_NUMBER=? ";
	public static final String GET_KHASRA_ID = " select KHASRA_TXN_ID,KHASRA_NUMBER, KHASRA_AREA from IGRS_REG_PROP_KHASARA_DTLS where PROPERTY_ID=? ";
	public static final String GET_KHASRA_ID_CLR = "select a.KHASRA_TXN_ID,a.KHASRA_NUMBER,nvl(b.KHASRA_AREA,a.khasra_area), b.khasra_id, a.KHASRA_AREA,b.total_saleble_area,b.khasra_unq_no, nvl(b.ALPIN_NO,'--'), nvl(b.transaction_type,'--'), b.lgd_code  from IGRS_REG_PROP_KHASARA_DTLS a, igrs_reg_khasra_prop_clr b where a.PROPERTY_ID=? and a.PROPERTY_ID=b.PROPERTY_ID(+) AND A.Khasra_Number=B.Khasra_No(+)";
	public static final String GET_ALL_BUYER_SELLER_PARTY_TXN_LIST = " SELECT DISTINCT PARTY_TXN_ID FROM IGRS_REG_INIT_PROP_TRANS_MAP WHERE reg_txn_id =? and property_id =? ";
	public static final String GET_PARTY_TYPE_ID = "SELECT PARTY_TYPE_ID FROM igrs_reg_txn_party_detls WHERE PARTY_TXN_ID=? ";
	public static final String GET_POA_CHECK = "select POA_HOLDER_FLAG,RCMS_PARTY_TYPE_FLAG from igrs_party_type_master where PARTY_TYPE_ID=? ";
	public static final String GET_PARTY_TYPE_FLAG_POA_OWNER = "SELECT RCMS_PARTY_TYPE_FLAG " + "FROM IGRS_PARTY_TYPE_MASTER " + "WHERE PARTY_TYPE_ID IN " + "  (SELECT PARTY_TYPE_ID " + "  FROM igrs_reg_txn_party_detls " + "  WHERE PARTY_TXN_ID IN " + "    (SELECT parent_id FROM igrs_reg_txn_party_detls WHERE PARTY_TXN_ID=? " + "    ) " + "  )";

	public static final String GET_POA_OWNER = "select PARTY_TXN_ID from IGRS_REG_TXN_PARTY_DETLS where PARENT_ID=? ";

	// public static final String GET_PARTY_TYPE_FLAG=
	// "SELECT RCMS_PARTY_TYPE_FLAG FROM IGRS_PARTY_TYPE_MASTER WHERE PARTY_TYPE_ID=?"
	// ;

	public static final String GET_SELLER_PARTY_LIST = "SELECT APPELLATE_TYPE_ID,PARTY_FIRST_NAME,PARTY_MIDDLE_NAME,PARTY_LAST_NAME,GOV_OFFICE_ID,ORGANIZATION_NAME,DESG_OF_OFFICIAL,ADRS_OF_OFFICIAL,guardian_name FROM igrs_reg_txn_party_detls WHERE party_txn_id =?";

	public static final String GET_BUYER_PARTY_LIST = "SELECT a.APPELLATE_TYPE_ID, " + "  a.PARTY_FIRST_NAME, " + "  a.PARTY_MIDDLE_NAME, " + "  a.PARTY_LAST_NAME, " + "  a.GOV_OFFICE_ID, " + "  a.ORGANIZATION_NAME, " + "  a.DESG_OF_OFFICIAL, " + "  a.COUNTRY_ID, " + "  a.STATE_ID, " + "  a.DISTRICT_ID, " + "  a.PARTY_ADDRESS, " + "  a.PHONE_NUMBER, " + "  a.MOBILE_NUMBER, " + "  a.EMAIL_ID, " + "  a.PARTY_GENDER, " + "  a.RELATION_TYPE_ID, " + "  a.SHARE_OF_PROPERTY, " + "  a.PHOTO_PROOF_ID, " + "  a.ADHAR_NAME, " + "  a.PARTY_TYPE_ID, " + "  a.CATEGORY_ID, " + "  a.GUARDIAN_NAME, " + "  a.PHOTO_PROOF_TYPE_ID, " + "  a.ADRS_OF_OFFICIAL "

	+ "FROM igrs_reg_txn_party_detls a " + "WHERE a.party_txn_id =?";

	public static final String GET_RELATION_NAME = "select RELATION_TYPE_NAME from IGRS_RELATION_TYPE_MASTER where RELATION_TYPE_ID=?";
	public static final String GET_BUYER_TYPE_ID_NAME = "select PHOTO_PROOF_TYPE_NAME from IGRS_PHOTOID_PROOF_TYPE_MASTER  where PHOTO_PROOF_TYPE_ID=?";
	public static final String GET_BUYER_CASTE_NAME = "select CASTE_NAME from IGRS_CASTE_MASTER  where CASTE_ID=?";

	/*
	 * public static final String GET_AREA_DETAILS =
	 * " select PROP.PROPERTY_ID, DIST.DISTRICT_NAME, TEH.TEHSIL_NAME, PROP.RI_CIRCLE, WARD.WARD_PATWARI_NAME, VIL.COLONY_NAME "
	 * +
	 * " from IGRS_REG_PROPRTY_DTLS PROP, IGRS_DISTRICT_MASTER DIST, IGRS_TEHSIL_MASTER TEH, IGRS_WARD_PATWARI_MASTER WARD, IGRS_COLONY_MASTER VIL "
	 * +
	 * " where PROP.DISTRICT_ID=DIST.DISTRICT_ID and PROP.TEHSIL_ID=TEH.TEHSIL_ID and PROP.WARD_ID=WARD.WARD_PATWARI_ID and PROP.MOHALLA_ID=VIL.COLONY_ID AND "
	 * + " PROPERTY_ID = ? ";
	 */
	public static final String GET_AREA_DETAILS = " select PROPERTY_ID,DISTRICT_ID,TEHSIL_ID,RI_CIRCLE,WARD_ID,MOHALLA_ID,AREA_TYPE_ID from IGRS_REG_PROPRTY_DTLS where PROPERTY_ID=? ";

	/*
	 * public static final String GET_LAND_AREA =
	 * " select AGRI_SUB_TYPE_ID ,TOTAL_AREA, TOTAL_DIVERTED_AREA from IGRS_PROP_AGRI_DETLS where VAL_TXN_ID IN (select VAL_TXN_ID from IGRS_REG_PROPRTY_DTLS where PROPERTY_ID= ? )"
	 * ;
	 */

	public static final String GET_LAND_AREA = " SELECT SUM((NVL(TOTAL_AREA,0)+NVL(TOTAL_DIVERTED_AREA,0))) TOT from IGRS_PROP_AGRI_DETLS where VAL_TXN_ID IN (select VAL_TXN_ID from IGRS_REG_PROPRTY_DTLS where PROPERTY_ID= ? ) ";

	public static final String GET_PARTY_SHARE = " select SHARES from IGRS_REG_INIT_PROP_TRANS_MAP where PARTY_TXN_ID=? and PROPERTY_ID=? ";
	public static final String GET_PARTY_SHARE_CLR = " select SHARES from IGRS_REG_PROP_SHARE_MAP_CLR where PARTY_TXN_ID=? and PROPERTY_ID=? ";

	public static final String GET_RCMS_SEQ_ID = "select IGRS_RCMS_DTLS_SEQ.NEXTVAL from dual";
	public static final String INSERT_RCMS_RESPONSE = "insert into IGRS_RCMS_RESPONSE_DTLS(RCMS_TXN_ID,REG_TXN_ID,PROPERTY_ID,RESP_STATUS,ERROR_DESC,CREATED_DATE,UPDATED_DATE,REQ_COUNT,APPLICATIONNUMBER,CASE_NUMBER,COURT_NAME,PRESENTATIONDATE,TEHSIL,DISTRICT,BUYERNAME,BUYERFATHER,SELLERNAME,SELLERFATHER,GRAMPANCHAYAT,IS_CYBER) values (?,?,?,?,?,SYSDATE,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String UPDATE_REG_TXN_TABLE = "UPDATE IGRS_REG_TXN_DETLS SET RCMS_FLAG=? WHERE REG_TXN_ID=?";

	public static final String CHECK_CLR_FLAG = "select clr_flag From igrs_reg_proprty_dtls where property_id=?";
	public static final String GET_ALL_BUYER_SELLER_PARTY_TXN_LIST_CLR = " SELECT DISTINCT PARTY_TXN_ID FROM IGRS_REG_PROP_SHARE_MAP_CLR WHERE reg_txn_id =? and property_id =?";
	public static final String GET_PARTY_SHARE_CLR_KHASRA = " select SHARES from IGRS_REG_PROP_SHARE_MAP_CLR where PARTY_TXN_ID=? and PROPERTY_ID=? and khasra_no=?";
	public static final String GET_RCMS_RECEIPT_DATA = "select applicationnumber, case_number,COURT_NAME, PRESENTATIONDATE, TEHSIL, DISTRICT, BUYERNAME, BUYERFATHER, SELLERNAME, SELLERFATHER, GRAMPANCHAYAT from igrs_rcms_response_dtls where reg_txn_id=? ";
	// public static final String
	// GET_ALL_PROPID="select property_id From igrs_reg_proprty_dtls where REG_TXN_ID=?";
	public static final String GET_DOC_DETAIL = "select a.registration_number, b.deed_type_name, to_char(a.update_date,'dd-mon-yy') from igrs_reg_txn_detls a, igrs_deed_type_master b where a.deed_id=b.deed_type_id and a.reg_txn_id=?";
	public static final String CHECK_DOC_STATUS = "select count(1) from igrs_rcms_response_dtls where reg_txn_id=? and property_id=?";
	public static final String UPDATE_FAILED_RCMS_STATUS = "UPDATE igrs_rcms_response_dtls SET error_desc=?,REQ_COUNT=?,RESP_STATUS=?,APPLICATIONNUMBER=?,CASE_NUMBER=?,COURT_NAME=?, PRESENTATIONDATE=?, TEHSIL=?, DISTRICT=?, BUYERNAME=?, BUYERFATHER=?, SELLERNAME=?, SELLERFATHER=?, GRAMPANCHAYAT=?, IS_CYBER=? where REG_TXN_ID=? and PROPERTY_ID=? ";
	public static final String FETCH_PAYMENT_DETAIL = "select a.payment_txn_id, a.paid_amount, to_char(a.update_date,'yyyy-mm-dd') from igrs_reg_payment_detls a where A.REG_TXN_ID=? and a.payment_txn_id is not null and a.PAYMENT_TYPE_ID = '4'";
	public static final String FETCH_FORMA_DATA = "select a.registration_number, b.office_name, c.district_name, d.tehsil_name, f.deed_type_name, b.h_office_name, c.h_district_name, d.h_tehsil_name, f.h_deed_type_name, a.update_by from igrs_reg_txn_detls a, igrs_office_master b, igrs_district_master c, igrs_tehsil_master d, igrs_reg_proprty_dtls e, igrs_deed_type_master f where a.reg_txn_id=? and a.registration_office_id=b.office_id and a.registration_dist_id=c.district_id and a.reg_txn_id=e.reg_txn_id and e.tehsil_id=d.tehsil_id and a.deed_id = f.deed_type_id";
	public static final String GET_ALL_PROPID = "select property_id From igrs_reg_proprty_dtls where REG_TXN_ID=?";

	// district check for cyber tehsil
	public static final String GET_CYBER_DISTRICT_CHECK = "select nvl(rcms_cyber,'N') From igrs_district_master where district_id=?";
	public static final String GET_KHASRA_TRANSACTION_TYPE = "select nvl(transaction_type,'PARTIAL') From igrs_reg_khasra_prop_clr where reg_txn_id=?";

	// get party sign path
	public static final String GET_SELLER_SIGN_PATH = "select A.User_Field_Id, A.Party_Name, C.Sign_File_Path, c.FIMGER_PRINT_FILE_PATH From Igrs_Cyber_Form_User_Enterable a, Igrs_Reg_Txn_Party_Detls b, Igrs_Reg_Txn_Party_Other_Dtls c where a.reg_txn_id=? and A.Party_Txn_Id=B.Party_Txn_Id and B.Party_Other_Detls_Id=C.Party_Other_Detls_Id";
	public static final String GET_FORMVIA_PATH = "select Form_Via from Igrs_Reg_Txn_Detls where reg_txn_id=?";
	public static final String GET_FORMVIB_PATH = "select Form_Vib from Igrs_Reg_Txn_Detls where reg_txn_id=?";

	// for rcms receipt
	public static final String GET_DISTRICT_TEHSIL_INFO = "SELECT B.District_Name, " + "  C.Tehsil_Name, " + "  D.Sub_Area_Type_Name, " + "  E.Ward_Patwari_Name, " + "  B.H_District_Name, " + "  C.H_Tehsil_Name, " + "  D.H_Sub_Area_Type_Name, " + "  E.H_Ward_Patwari_Name " + "FROM Igrs_Reg_Proprty_Dtls A, " + "  Igrs_District_Master B, " + "  Igrs_Tehsil_Master C, " + "  Igrs_Sub_Area_Type_Master D, " + "  Igrs_Ward_Patwari_Master E " + "WHERE A.District_Id=B.District_Id " + "AND A.Tehsil_Id    =C.Tehsil_Id " + "AND A.Gov_Body_Id  =D.Sub_Area_Type_Id " + "AND A.Ward_Id      =E.Ward_Patwari_Id " + "AND a.Property_Id   =?";
}
