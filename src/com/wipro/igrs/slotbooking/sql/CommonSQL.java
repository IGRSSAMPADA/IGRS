package com.wipro.igrs.slotbooking.sql;

/**
 * ===========================================================================
 * File : CommonSQL.java Description : Represents the SQL Class
 * 
 * Author : Madan Mohan Created Date : Nov 28, 2007
 * 
 * ===========================================================================
 */
public class CommonSQL {

	public static final String REQUISITE_FEE_QUERY = "SELECT AMOUNT FROM IGR_REQUISITE_FEE_MASTER WHERE REQUISITE_NAME='WILL_DEPOSIT'";
	public static final String REQUISITE_OTHER_FEE_QUERY = "SELECT AMOUNT FROM IGR_REQUISITE_FEE_MASTER WHERE REQUISITE_NAME='WILL_OTHER'";
	public static final String WILL_DEPOSIT_INSERT = "INSERT INTO IGRS_WILL_DEPOSIT_TXN(WILL_ID,DR_ID," + "DEPOSITOR_TYPE,FIRST_NAME,MID_NAME,LAST_NAME,GENDER,AGE," + "FATHER_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,CITY,STATE," + "COUNTRY,PIN,PHONE,MOBILE,EMAIL,ID_PROOF,PHOTO_FLAG,THUMB_FLAG," + "SIGN_FLAG,FEE,OTHER,PAYMENT_MODE,DEPOSIT_DATE,WILL_TYPE_FLAG)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE,?) ";
	public static final String WILL_DEPOST_AGENT_INSERT = "INSERT INTO IGRS_WILL_AGENT_TXN(WILL_ID,FIRST_NAME," + "MID_NAME,LAST_NAME,GENDER,AGE,FATHER_NAME,MOTHER_NAME," + "SPOUSE_NAME,ADDRESS,CITY,STATE,COUNTRY,PIN,PHONE,MOBILE,EMAIL,IDPROOFNO) " + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String WILL_DEPOSIT_QUERY = "SELECT FIRST_NAME,MID_NAME,LAST_NAME,GENDER,AGE," + "FATHER_NAME,MOTHER_NAME,SPOUSE_NAME,ADDRESS,CITY,STATE," + "COUNTRY,PIN,PHONE,MOBILE,EMAIL,ID_PROOF,PHOTO_FLAG,THUMB_FLAG," + "SIGN_FLAG,DEPOSIT_DATE,WILL_ID FROM  IGRS_WILL_DEPOSIT_TXN WHERE WILL_ID=? AND WILL_TYPE_FLAG=?";

	public static final String WILL_WITHDRAWAL_INSERT = "UPDATE IGRS_WILL_DEPOSIT_TXN SET WILL_TYPE_FLAG=?,EXE_TFLAG=?,EXE_SFLAG=?,REASON=?,REMARKS=? WHERE WILL_ID=?";

	// added by shubham for slot report
	public static final String SLOT_REPORT = "SELECT TO_CHAR(p.SLOT_DATE,'DD-MM-YYYY'), p.slot_time, p.initiation_no, CASE TO_CHAR(p.user_type_id) WHEN '3' THEN UPPER(p.initiated_by) ELSE p.user_id||' / '||p.empname END, p.DEED_TYPE_NAME, NVL(i.EXEMPTION_NAME,'--') AS EXEMPTION_NAME, NVL( CASE applicable_on WHEN 'R' THEN 'Registration Fees' WHEN 'S' THEN 'Stamp Duty' END,'--') AS Exemption_on, p.INSTRUMENT_NAME, nvl(to_char(p.DEED_DRAFT_ID),'Not Consumed'), nvl(p.DEED_DOC_FILEPATH,'NA') FROM igrs_exemption_master i, (SELECT a.SLOT_DATE, TO_CHAR(b.TIME_FROM,'hh:mi AM') AS slot_time, a.REG_TXN_ID                    AS initiation_no, J.LICENSE_NUMBER ||' / ' ||d.FIRST_NAME ||' ' ||d.LAST_NAME AS initiated_by, f.DEED_TYPE_NAME, g.INSTRUMENT_NAME, e.DEED_DRAFT_ID, h.EXEMPTION_ID, e.DEED_DOC_FILEPATH, i.user_type_id , i.user_id, d.FIRST_NAME ||' ' ||d.LAST_NAME empname FROM igrs_reg_slot_book_txn_dtls a, igrs_reg_slot_master b, IGRS_USER_REG_DETAILS d, IGRS_REG_TXN_DETLS e, IGRS_DEED_TYPE_MASTER f, IGRS_DEED_INSTRUMENT_MASTER g, igrs_reg_exemption_detls h, IGRS_USERS_LIST i, (SELECT CREATED_BY, LICENSE_NUMBER FROM IGRS_SP_DETLS c WHERE c.APPLCTN_STATUS=8 AND c.STATUS        ='A'  AND c.LICENSE_NUMBER        IS NOT NULL ) J WHERE a.OFFICE_ID = ? AND i.USER_ID     =d.USER_ID AND a.slot_date BETWEEN to_date(?,'dd-mm-yyyy') AND to_date(?,'dd-mm-yyyy') AND b.slot_id                =a.slot_id AND a.CREATED_BY          =J.CREATED_BY(+) AND a.CREATED_BY             =d.USER_ID AND a.REG_TXN_ID             =TO_CHAR(e.REG_TXN_ID)  AND f.DEED_TYPE_ID           =e.DEED_ID AND e.INSTRUMENTS_ID         =g.INSTRUMENT_ID AND e.REGISTRATION_TXN_STATUS=10 AND a.REG_TXN_ID             = h.reg_txn_id(+) ORDER BY b.TIME_FROM desc )p WHERE i.EXEMPTION_ID(+)=p.EXEMPTION_ID and a.OFFICE_ID=b.OFFICE_ID ORDER BY p.SLOT_DATE desc";
	public static final String SLOT_REPORT_DETLS = "SELECT TO_CHAR(p.BLOCKED_DATE,'DD-MM-YYYY'), p.slot_time, p.initiation_no, CASE TO_CHAR(p.user_type_id) WHEN '3' THEN UPPER(p.initiated_by) ELSE p.user_id||' / '||p.empname END, p.DEED_TYPE_NAME, NVL(i.EXEMPTION_NAME,'--') AS EXEMPTION_NAME, NVL( CASE applicable_on WHEN 'R' THEN 'Registration Fees' WHEN 'S' THEN 'Stamp Duty' END,'--') AS Exemption_on, p.INSTRUMENT_NAME, nvl(to_char(p.DEED_DRAFT_ID),'Not Consumed'), nvl(p.DEED_DOC_FILEPATH,'NA') FROM igrs_exemption_master i, (SELECT a.BLOCKED_DATE, TO_CHAR(b.TIME_FROM,'hh:mi AM') AS slot_time, a.REG_TXN_ID                    AS initiation_no, J.LICENSE_NUMBER ||' / ' ||d.FIRST_NAME ||' ' ||d.LAST_NAME AS initiated_by, f.DEED_TYPE_NAME, g.INSTRUMENT_NAME, e.DEED_DRAFT_ID, h.EXEMPTION_ID, e.DEED_DOC_FILEPATH, i.user_type_id , i.user_id, d.FIRST_NAME ||' ' ||d.LAST_NAME empname FROM IGRS_REG_SLOT_BLOCK_DETAILS a, igrs_reg_slot_master b, IGRS_USER_REG_DETAILS d, IGRS_REG_TXN_DETLS e, IGRS_DEED_TYPE_MASTER f, IGRS_DEED_INSTRUMENT_MASTER g, igrs_reg_exemption_detls h, IGRS_USERS_LIST i, (SELECT CREATED_BY, LICENSE_NUMBER FROM IGRS_SP_DETLS c WHERE c.APPLCTN_STATUS=8 AND c.STATUS        ='A'  AND c.LICENSE_NUMBER        IS NOT NULL ) J WHERE a.OFFICE_ID = ? AND i.USER_ID     =d.USER_ID AND a.BLOCKED_DATE BETWEEN to_date(?,'dd-mm-yyyy') AND to_date(?,'dd-mm-yyyy') AND b.slot_id                =a.slot_id AND a.CREATED_BY          =J.CREATED_BY(+) AND a.CREATED_BY             =d.USER_ID AND a.REG_TXN_ID             =TO_CHAR(e.REG_TXN_ID)  AND f.DEED_TYPE_ID           =e.DEED_ID AND e.INSTRUMENTS_ID         =g.INSTRUMENT_ID AND e.REGISTRATION_TXN_STATUS=10 AND a.REG_TXN_ID             = h.reg_txn_id(+) ORDER BY b.TIME_FROM desc )p WHERE i.EXEMPTION_ID(+)=p.EXEMPTION_ID ORDER BY p.BLOCKED_DATE desc";
	public static final String PARTY_DETAILS = "SELECT nvl(p.name,'--'), p.duty_txn, NVL(TO_CHAR(b.PROPERTY_ID),'--'), p.REG_TXN_ID, p.PARTY_TXN_ID, p.PARTY_TYPE_ID, p.INSTRUMENTS_ID FROM IGRS_REG_PROPRTY_DTLS b, (SELECT case when a.APPELLATE_TYPE_ID=2 then a.PARTY_FIRST_NAME||' '|| a.PARTY_LAST_NAME  when a.APPELLATE_TYPE_ID=1 then a.ORGANIZATION_NAME else to_char(nvl(a.NAME_OF_OFFICIAL,DESG_OF_OFFICIAL)) end as name, NVL(TO_CHAR(c.REG_INIT_DUTY_TXN_ID),'--') AS duty_txn , NVL(TO_CHAR(a.REG_TXN_ID),'--')           AS REG_TXN_ID, a.PARTY_TYPE_ID, a.PARTY_TXN_ID, d.INSTRUMENTS_ID FROM IGRS_REG_TXN_PARTY_DETLS a, IGRS_REG_INIT_STAMP_DUTY_DETLS c, IGRS_REG_TXN_DETLS d WHERE a.REG_TXN_ID= ? AND c.reg_txn_id  =a.REG_TXN_ID AND a.REG_TXN_ID  =d.REG_TXN_ID ) p WHERE p.REG_TXN_ID =b.reg_txn_id(+)";
	public static final String DUTIES_DETAILS = "select STAMP_DUTY, GRAM_DUTY, NAGAR_DUTY, UPKAR, REG_FEE, TOTAL, DUTY_ALREADY_PAID, REG_ALREADY_PAID, REG_FEE_AFTER_EXEMP, TOTAL_AFTER_EXEMP, EXEMP_DUTY, EXEMP_FEE TOTAL from IGRS_REG_INIT_STAMP_DUTY_DETLS where REG_INIT_DUTY_TXN_ID= ?";
	public static final String PARTY_NAME_DETAILS = "SELECT Party_type,  p.Party_name, p.Party_occoupation,  p.District_name,  p.Party_address,  p.Mobile_number,  p.Guardian_name, p.photo_path,  p.AFFIDAVIT_PATH,   p.claimant_path,  p.Pan_path FROM IGRS_REG_ADDITIONAL_UPLOADS e,  (SELECT b.PARTY_TYPE_NAME AS Party_type, NVL((a.PARTY_FIRST_NAME   ||' '  || a.PARTY_MIDDLE_NAME  ||' ' || a.PARTY_LAST_NAME),'--') AS Party_name,  NVL(c.OCCUPATION_DESC,'--') AS Party_occoupation,  a.PARTY_TXN_ID, NVL(d.DISTRICT_NAME,'--')          AS District_name,  NVL( a.PARTY_ADDRESS,'--')         AS Party_address,   NVL(TO_CHAR(a.MOBILE_NUMBER),'--') AS Mobile_number,  NVL(a.GUARDIAN_NAME,'--')          AS Guardian_name,  NVL(a.PHOTO_PROOF_PATH,'--') as photo_path, NVL(a.NOT_AFFD_EXEC_PATH,'--') as AFFIDAVIT_PATH,  NVL(a.CLAIMNT_PHOTO_PATH,'--')as claimant_path,  NVL (a.PAN_FORM_60_PATH,'--')as Pan_path FROM IGRS_REG_TXN_PARTY_DETLS a , IGRS_PARTY_TYPE_MASTER b , IGRS_OCCUPATION_MASTER c, IGRS_DISTRICT_MASTER d WHERE a.PARTY_TXN_ID=?  AND a.PARTY_TYPE_ID =b.PARTY_TYPE_ID AND a.OCCUPATION_ID =c.OCCUPATION_ID AND a.DISTRICT_ID   =d.DISTRICT_ID  )p WHERE p.PARTY_TXN_ID=e.PARTY_TXN_ID(+) ";
	public static final String CONSENTOR_DETAILS = "SELECT a.CONSENTER_TXN_NUM, a.FATHER_NAME, a.CONSENTER_FIRST_NAME, a.REG_TXN_ID, a.WITH_CONSIDERATION, NVL(TO_CHAR(b.REG_FEE),'--'), NVL(TO_CHAR(b.TOTAL),'--'), NVL(TO_CHAR(b.REG_FEE_AFTER_EXEMP),'--'), NVL(TO_CHAR(b.TOTAL_AFTER_EXEMP),'--') FROM IGRS_REG_CONSENTER_DETAILS a, IGRS_CONSENTER_DUTY_DETAILS b WHERE a.REG_TXN_ID=? AND a.REG_TXN_ID  =b.reg_txn_id(+)";
	// added by shubham for slot report

	public static final String WILL_RETRIEVE_QUERY = "SELECT FIRST_NAME,MID_NAME,LAST_NAME, ADDRESS,DEPOSIT_DATE FROM  IGRS_WILL_DEPOSIT_TXN WHERE WILL_ID LIKE 'MP%' ";

	public static final String WILL_RETRIEVE_CITIZEN_INSERT = "INSERT INTO IGRS_WILL_RETRIEVE_TXN(WILL_ID,FIRST_NAME," + "MID_NAME,LAST_NAME,GENDER,AGE,FATHER_NAME,MOTHER_NAME," + "SPOUSE_NAME,ADDRESS,CITY,STATE,COUNTRY,PIN,PHONE,MOBILE,EMAIL," + "IDPROOFNO,PHOTO_FLG,THUMB_FLG,SIGN_FLG,DEATH_FLG,WILL_RET_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String WILL_RETRIEVE_UPDATE = "UPDATE IGRS_WILL_DEPOSIT_TXN SET WILL_TYPE_FLAG=?,WILL_RET_TYPE_FLAG=?,REASON=?,REMARKS=? WHERE WILL_ID=?";

	public static final String WILL_RETRIEVE_COURT_INSERT = "INSERT IGRS_WILL_COURT_TXN(WILL_ID,COURT_NAME,REP_NAME,DESG,THUMB_FLAG,SIGN_FLAG,ADDRESS,CITY,STATE,COUNTRY,PIN,PHONE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

	public static final String SLOTBOOK_DISTRICT_DROPDOWN_SELECT = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID='MP' AND DISTRICT_STATUS='A' ORDER BY DISTRICT_NAME";

	public static final String SLOTBOOK_DISTRICT_DROPDOWN_SELECT_H = "SELECT DISTRICT_ID,DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE STATE_ID='MP' AND DISTRICT_STATUS='A' ORDER BY DISTRICT_NAME";

	public static final String SLOTBOOK_SRONAME_DROPDOWN_SELECT = "SELECT OFFICE_ID,OFFICE_NAME, H_OFFICE_NAME  FROM IGRS_OFFICE_MASTER WHERE  OFFICE_TYPE_ID='3' AND DISTRICT_ID=";

	// START:======COMMENTED BY ANKITA 19-10-2012
	public static final String SLOTBOOK_USER_TIMESLOT_SELECT = "SELECT SLOT_ID,TO_CHAR(TIME_FROM,'HH12:MI')||' - '||TO_CHAR(TIME_TO,'HH12:MI')  FROM IGRS_REG_SLOT_MASTER WHERE SLOT_STATUS='A' AND OFFICE_ID='";
	// END:=======COMMENTED BY ANKITA 19-10-2012

	// START:======ADDED BY ANKITA 19-10-2012 FOR SELECTING AVAILABLE SR SLOTS
	public static final String SLOTBOOK_USR_TIMESLOT_SELECT = "SELECT SLOT_ID,TO_CHAR(TIME_FROM,'HH12:MI')||' - '||TO_CHAR(TIME_TO,'HH12:MI')  FROM IGRS_REG_SLOT_MASTER " + " WHERE SLOT_ID IN (SELECT SLOT_ID FROM IGRS_REG_SLOT_MASTER MINUS SELECT SLOT_ID FROM IGRS_REG_SLOT_BLOCK_DETAILS" + " WHERE BLOCK_STATUS='A' AND BLOCKED_DATE =";
	// END:======ADDED BY ANKITA 19-10-2012 FOR SELECTING AVAILABLE SR SLOTS

	// START:======= ADDED BY ANKITA 29-10-2012 FOR APPLICATION ID CHECK

	public static final String SLOTBOOK_APPID_CHECK = "SELECT SLOT_ID,REG_TXN_ID FROM  IGRS_REG_SLOT_BOOK_TXN_DTLS WHERE REG_TXN_ID =";

	// END:======= ADDED BY ANKITA 29-10-2012 FOR APPLICATION ID CHECK

	// START:====== ADDED BY ANKITA FOR USER TABLE UPDATION WHEN SR CANCELS HIS
	// SLOT
	public static final String USER_UPDATE_SRCANCEL = "UPDATE IGRS_REG_SLOT_BOOK_TXN_DTLS SET REMARKS='";

	public static final String GET_TXN_ID = "SELECT TXN_ID FROM IGRS_REG_SLOT_BLOCK_HISTORY WHERE OFFICE_ID='";

	public static final String UPDATE_HISTORY = "UPDATE IGRS_REG_SLOT_BLOCK_HISTORY SET STATUS_TO='D',";

	public static final String CALL_IGRS_INSERT_EMAIL_DATA = "call IGRS_INSERT_EMAIL_DATA(?,?,?,?)";
	// END:====== ADDED BY ANKITA FOR USER TABLE UPDATION WHEN SR CANCELS HIS
	// SLOT

	// START:=====ADDED BY ANKITA FOR WEEKEND SELECT 11/6/2012
	public static final String SLOT_WEEKEND = "SELECT HOLIDAY_DATE FROM IGRS_HOLIDAY_LIST WHERE HOLIDAY_DATE=";
	// END:=====ADDED BY ANKITA FOR WEEKEND SELECT 11/6/2012

	public static final String SLOTBOOK_SRNAME_DROPDOWN_SELECT = "select FIRST_NAME||'  '||LAST_NAME NAME,EMP_ID from IGRS_EMP_MASTER where EMP_ID in (select EMP_ID from IGRS_EMP_OFFICE_MAPPING  where OFFICE_ID='";

	// start:===commented by ankita 19-10-2012 (original query)for user slot
	// booking
	public static final String SLOTBOOK_USER_INSERT = "INSERT INTO IGRS_REG_SLOT_BOOK_TXN_DTLS (SLOT_TXN_ID,USER_ID,OFFICE_ID,DISTRICT_ID,SLOT_ID,REG_TXN_ID,CREATED_BY,CREATED_DATE,SLOT_DATE,count) VALUES(?,?,?,?,?,?,?,sysdate,?,1)";

	// end:===commented by ankita

	public static final String SLOTBOOK_USER_INSERT_BLOCK = "INSERT INTO IGRS_REG_SLOT_BLOCK_DETAILS (SLOT_TXN_ID,USER_ID,OFFICE_ID,SLOT_ID,REG_TXN_ID,CREATED_BY,CREATED_DATE,BLOCKED_DATE,BLOCK_STATUS,REG_SLOT_BLOCK_ID) VALUES(?,?,?,?,?,?,SYSDATE,?,?,?)";
	// start:===added by ankita for user slot booking
	// public static final String SLOTBOOK_USER_INSERT=
	// "INSERT INTO IGRS_REG_SLOT_BOOK_TXN_DTLS (SLOT_TXN_ID,USER_ID,OFFICE_ID,DISTRICT_ID,SLOT_ID,REG_TXN_ID,CREATED_BY,CREATED_DATE,SLOT_DATE,SLOT_STATUS) VALUES(?,?,?,?,?,?,?,?,?,?)";
	// end:===added by ankita for user slot booking

	// start:===added by Sree Latha for slot booking confirmation id
	public static final String PARAM_ID_GENERATION = "select IGRS_REG_SLOT_BOOK_TXN_SEQ.NEXTVAL from dual";
	// end:===added by Sree Latha for slot booking confirmation id

	// start:=== added by Sree Latha for getting amount from db using procedure
	public static final String SERVICE_FEE_PROCEDURE = "CALL " + "IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)";
	// End:=== added by Sree Latha for getting amount from db using procedure

	public static final String USER_SLOT_BOOK_SELECT = "SELECT  SERVICE_ID FROM IGRS_SERVICE_FUNCTION_MAP WHERE FUNCTION_ID=";
	// Start:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS
	public static final String PAY_TXN_SLOT_TXN_DETAILS = "INSERT INTO IGRS_REG_SLOT_PAYMENT_DTLS VALUES(?,?)";
	// End:==Added By SreeLatha for inserting paymentTxn_in and Slot_Id into
	// IGRS_REG_SLOT_PAYMENT_DTLS

	// Start:====Added by SreeLatha for SlotBooking View
	public static final String SLOT_BOOKING_VIEW_DETAILS = "select b.SLOT_TXN_ID,b.REG_TXN_ID,to_char(b.SLOT_DATE,'DD-MON-YYYY') SLOT_DATE," + " (SELECT distinct TO_CHAR(a.TIME_FROM,'HH:MI AM') FROM IGRS_REG_SLOT_MASTER a WHERE  a.SLOT_ID=b.SLOT_ID) SLOT_TIME," + " (select d.OFFICE_NAME from IGRS_OFFICE_MASTER d where d.OFFICE_ID=b.OFFICE_ID) SRO_OFFICE, (select d.H_OFFICE_NAME from IGRS_OFFICE_MASTER d where d.OFFICE_ID=b.OFFICE_ID) H_SRO_OFFICE, " + " (select c.FIRST_NAME||' '||c.MIDDLE_NAME||' '|| c.LAST_NAME from IGRS_EMP_MASTER c where c.EMP_ID=b.USER_ID) SR_NAME," + " b.SLOT_STATUS from IGRS_REG_SLOT_BOOK_TXN_DTLS b ";

	public static final String SLOT_BOOKING_VIEW_DETLS = "select b.SLOT_TXN_ID,b.REG_TXN_ID,to_char(b.BLOCKED_DATE,'DD-MON-YYYY') BLOCKED_DATE," + " (SELECT distinct TO_CHAR(a.TIME_FROM,'HH:MI AM') FROM IGRS_REG_SLOT_MASTER a WHERE  a.SLOT_ID=b.SLOT_ID) SLOT_TIME," + " (select d.OFFICE_NAME from IGRS_OFFICE_MASTER d where d.OFFICE_ID=b.OFFICE_ID) SRO_OFFICE, (select d.H_OFFICE_NAME from IGRS_OFFICE_MASTER d where d.OFFICE_ID=b.OFFICE_ID) H_SRO_OFFICE, " + " (select c.FIRST_NAME||' '||c.MIDDLE_NAME||' '|| c.LAST_NAME from IGRS_EMP_MASTER c where c.EMP_ID=b.USER_ID) SR_NAME," + " b.BLOCK_STATUS from IGRS_REG_SLOT_BLOCK_DETAILS b ";

	// End:====Added by SreeLatha for SlotBooking View

	public static final String SLOTBOOK_SR_BLOCK_INSERT = "INSERT INTO IGRS_REG_SLOT_BLOCK_DETAILS (OFFICE_ID,USER_ID,SLOT_ID,BLOCK_STATUS,BLOCKED_DATE,REMARKS,REG_SLOT_BLOCK_ID) VALUES (?,?,?,?,?,?,?)";
	public static final String SLOTBOOK_SR_BLOCKHISTORY_INSERT = "INSERT INTO IGRS_REG_SLOT_BLOCK_HISTORY (TXN_ID,USER_ID,OFFICE_ID,SLOT_ID,STATUS_FROM,STATUS_TO,CREATED_BY,CREATED_DATE,BLOCKED_DATE,REMARKS) VALUES(?,?,?,?,?,?,?,?,?,?)";;

	public static final String SLOTBOOK_SR_SLOTHISTORY_INSERT = "INSERT INTO IGRS_REG_SLOT_BLOCK_HISTORY (TXN_ID,USER_ID,OFFICE_ID,SLOT_ID,CREATED_BY,CREATED_DATE,BLOCKED_DATE,REG_TXN_ID) VALUES(?,?,?,?,?,sysdate,?,?)";;

	public static final String SLOTBOOK_SROTIMESLOT_SELECT = "select  SLOT_TIME_RANGE  from IGRS_SLOT_TIME_CONFIG_MASTER where SLOT_TIME_ID=";
	public static final String SLOTBOOK_TIMESLOT_SELECT = "select  SLOT_TIME_ID,SLOT_TIME_RANGE  from IGRS_SLOT_TIME_CONFIG_MASTER";
	// public static final String
	// SLOTBOOK_SRO_BOOKED_SEARCH="select distinct USER_ID,SLOT_ID from IGRS_REG_SLOT_BOOK_TXN_DTLS where  DISTRICT_ID=";

	// Start:=== Added by Ankita for USER SLOT BOOING VIEW
	public static final String SLOTBOOK_USER_BOOKED_SEARCH = "select distinct USER_ID,SLOT_ID,SLOT_STATUS from IGRS_REG_SLOT_BOOK_TXN_DTLS where  DISTRICT_ID=";
	public static final String SLOTBOOK_SRO_BOOKED_SEARCH = "select distinct USER_ID,SLOT_ID,BLOCK_STATUS from IGRS_REG_SLOT_BLOCK_DETAILS where  USER_ID='";
	// End:=== Added by Ankita
	public static final String SLOTBOOK_UPDATE = "UPDATE IGRS_SLOT_BOOKING_MASTER SET SLOT_STATUS='OPEN', UPDATED_BY= ";
	// public static final String SLOT_BOOKING_OFF_ID =
	// "SELECT OFFICE_ID FROM IGRS_USER_OFFICE_MAPPING WHERE USER_ID =";

	// Changed on 25-06-2013 due to DB restructurng
	public static final String SLOT_BOOKING_OFF_ID = "SELECT DISTINCT  RG.ROLE_GROUP_ID,offrol.ROLE_ID,offrol.OFFICE_ID FROM " + "IGRS_ROLE_MASTER RM, IGRS_ROLE_GROUP_MASTER GM,IGRS_RGROUP_ROLE_MAPPING RG ,IGRS_ROLE_OFFICE_MAPPING offrol, IGRS_USER_ROLE_GROUP_MAPPING UG " + "WHERE   offrol.ROLE_OFFICE_MAP_ID=RG.ROLE_OFFICE_MAP_ID and offrol.ROLE_ID=rm.ROLE_ID " + "AND RM.ROLE_STATUS = 'A'  AND GM.ROLE_GROUP_STATUS = 'A' AND   UG.ROLE_GROUP_ID = RG.ROLE_GROUP_ID AND UG.USER_ID=";

	public static final String GET_DISTRICT = "SELECT a.DISTRICT_ID FROM IGRS_OFFICE_MASTER a WHERE  a.OFFICE_ID= ";

	public static final String GET_DISRICT_NAME = "SELECT DISTRICT_ID,DISTRICT_NAME, H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = ";

	public static final String GET_SR_MAPPED_OFFICES = "SELECT OFFICE_ID ,OFFICE_NAME, H_OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_STATUS ='A' AND OFFICE_ID= ";
	public static final String Check_Reg_Number_Status = "SELECT REG_TXN_ID,REGISTRATION_TXN_STATUS FROM   IGRS_REG_TXN_DETLS WHERE REGISTRATION_TXN_STATUS=? AND  REG_TXN_ID =?";
	public static final String GET_COMPLETION_STATUS_FROM_MASTER = "SELECT REGISTRATION_TXN_STATUS FROM  IGRS_REG_STATUS_MASTER WHERE STATUS_NAME='initiation-10' ";
	public static final String GET_ESTAMP_CODE = "SELECT ESTAMP_CODE FROM IGRS_ESTAMP_DETLS WHERE  ESTAMP_TXN_ID = (SELECT MIN(ESTAMP_TXN_ID) FROM  IGRS_REG_ESTAMP_MAP WHERE REG_TXN_ID =?)";
	public static final String TEHSIL = "SELECT TEHSIL_ID, TEHSIL_NAME, H_TEHSIL_NAME FROM" + " IGRS_TEHSIL_MASTER WHERE DISTRICT_ID = ? and tehsil_status = 'A'";
	public static final String TEHSIL_PROPERTY = "SELECT DISTINCT( TEHSIL_ID), TEHSIL_NAME, H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID IN ( SELECT DISTINCT(TEHSIL_ID) FROM IGRS_OFFICE_WARD_MAPPING WHERE DISTRICT_ID=? AND STATUS_FLAG  ='A' AND WARD_PATWARI_ID IN(SELECT DISTINCT(WARD_ID) FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID=? )) AND TEHSIL_STATUS='A'";
	public static final String TEHSIL_PROPERTY_MULTIPLE = "SELECT DISTINCT( TEHSIL_ID), TEHSIL_NAME, H_TEHSIL_NAME FROM IGRS_TEHSIL_MASTER WHERE TEHSIL_ID IN (SELECT DISTINCT(TEHSIL_ID) FROM IGRS_OFFICE_WARD_MAPPING WHERE DISTRICT_ID =? AND STATUS_FLAG ='A' AND WARD_PATWARI_ID IN (SELECT DISTINCT(WARD_ID) FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID=? AND ROWNUM <2 ) ) AND TEHSIL_STATUS='A'";
	public static final String OFFICE_MAPPED_TO_TEHSIL = "SELECT OFFICE_ID,OFFICE_NAME, H_OFFICE_NAME FROM   IGRS_OFFICE_MASTER 	WHERE TEHSIL_ID =? AND OFFICE_TYPE_ID =?";
	public static final String GET_OFFICE_TYPE_ID = "SELECT OFFICE_TYPE_ID FROM IGRS_OFFICE_TYPE_MASTER WHERE OFFICE_TYPE_NAME ='SRO' AND ACTIVE_FLAG='A'";
	public static final String ESTAMP_Check = "select count(*) from IGRS_REG_ESTAMP_MAP  where REG_TXN_ID = ?";

	public static final String ESTAMP_Check_again = "select count(*) from IGRS_REG_ESTAMP_MAP  where REG_TXN_ID = ?";
	public static final String Transaction = "select TRANSACTION_ID from IGRS_REG_ESTAMP_MAP where REG_TXN_ID = ? order by TRANSACTION_ID";
	public static final String GetDuplicateEstamp = "SELECT TRANSACTION_ID FROM IGRS_REG_ESTAMP_MAP WHERE  ESTAMP_TXN_ID =" + "(SELECT ESTAMP_TXN_ID FROM IGRS_REG_ESTAMP_MAP WHERE   REG_TXN_ID = ? GROUP BY ESTAMP_TXN_ID having count(ESTAMP_TXN_ID)>1) order by TRANSACTION_ID ";

	public static final String INSERT_ESTAMP_LOG_MAP = "insert into igrs_estamp_map_log (TRANSACTION_ID,ESTAMP_TXN_ID,REG_TXN_ID,DELETED_DATE)  values (?,?,?,sysdate)";

	public static final String INSERT_ESTAMP_DETLS_LOG = "insert into igrs_estamp_detls_log(TRANSACTION_ID,ESTAMP_TXN_ID,ESTAMP_CODE,REG_INIT_ID,estamp_amount,deleted_date,ESTAMP_PATH) values(?,?,?,?,?,sysdate,?) ";
	public static final String getTransactionEstampDetls = "select ESTAMP_TXN_ID,ESTAMP_CODE,estamp_amount from igrs_estamp_detls where ESTAMP_TXN_ID= ? and REG_INIT_ID= ? ";

	public static final String getTransactionDetails = "select ESTAMP_TXN_ID, ESTAMP_PDF_PATH from IGRS_REG_ESTAMP_MAP where TRANSACTION_ID = ? and REG_TXN_ID = ?";
	public static final String DELETE_DETAILS = "delete from IGRS_REG_ESTAMP_MAP where TRANSACTION_ID= ? and REG_TXN_ID=?";
	public static final String DELETE_DETAILS_estamp = "delete from IGRS_ESTAMP_DETLS where ESTAMP_TXN_ID= ? and REG_INIT_ID=? and ESTAMP_CODE =?";
	public static final String CHECK_PROPERTY_OF_REG_TXN = "SELECT count( PROPERTY_ID) FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID=?";
	public static final String SRO_WARD = "SELECT OFFICE_ID,OFFICE_NAME, H_OFFICE_NAME FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID IN ( SELECT DISTINCT(OFFICE_ID) FROM IGRS_OFFICE_WARD_MAPPING WHERE TEHSIL_ID =? AND WARD_PATWARI_ID IN (SELECT WARD_ID FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID=?) AND OFFICE_ID IS NOT NULL AND STATUS_FLAG='A') ORDER BY OFFICE_ID";
	public static final String CHECK_DISTRICT_MAPPED = "select count(*) from igrs_district_master where sro_flag='A' and district_id=?";
	public static final String CHECK_WARD_MAPPED = "SELECT COUNT (*) FROM IGRS_OFFICE_WARD_MAPPING WHERE WARD_PATWARI_Id IN (SELECT WARD_ID FROM IGRS_REG_PROPRTY_DTLS WHERE REG_TXN_ID=? AND ROWNUM <2)and STATUS_FLAG='A'";
	// added by Saurav for Urgent Slot booking
	public static final String SLOT_BOOKED_DETAIL = "SELECT a.SLOT_TXN_ID,a.REG_TXN_ID, to_char(a.SLOT_DATE,'DD-MON-YYYY'),  nvl(to_char(b.TIME_FROM,'HH:MI'),''),  a.CREATED_BY, a.OFFICE_ID,  c.OFFICE_NAME,  d.DISTRICT_NAME,  d.DISTRICT_ID, e.REGISTRATION_TXN_STATUS, d.h_district_name, c.h_office_name, e.instruments_id FROM IGRS_REG_SLOT_BOOK_TXN_DTLS a,IGRS_REG_SLOT_MASTER b,IGRS_OFFICE_MASTER c,IGRS_DISTRICT_MASTER d, IGRS_REG_TXN_DETLS e WHERE a.REG_TXN_ID=?  and a.slot_id=b.slot_id(+) and a.OFFICE_ID=c.OFFICE_ID and a.DISTRICT_ID=d.DISTRICT_ID and a.reg_txn_id=e.REG_TXN_ID";
	public static final String CHECK_OFFICE_TYPE_ID = "select OFFICE_TYPE_ID||'-'||district_id from IGRS_OFFICE_MASTER where OFFICE_ID=?";
	public static final String GET_SLOT_RESCHEDULE_REASON_LIST = "select reasonid, reason_desc_en, reason_desc_hi,PAYMENT_REQUIRED, nvl(PAYABLE_AMOUNT,0) from IGRS_RESCHEDULE_REASON_MASTER where status='A' AND REASON_STATUS IN (0,?)";
	public static final String GET_APPLICATION_STATUS="select registration_txn_status from igrs_reg_txn_detls where reg_txn_id=?";
	public static final String UPDATE_SLOT_DETAIL="UPDATE IGRS_REG_SLOT_BOOK_TXN_DTLS set slot_id='No Slot Id', slot_date=to_date(?,'dd-mm-yy') where reg_txn_id=?";
	public static final String UPDATE_SLOT_BLOCK_DETAIL="UPDATE igrs_reg_slot_block_details SET BLOCK_STATUS='R',SLOT_ID='No Slot Id' WHERE REG_TXN_ID=? and block_status in ('A','R')";
	public static final String STATUS_SLOT_RESCHEDULE="select count(1) from IGRS_REG_SLOT_BOOK_TXN_DTLS where slot_date=TO_CHAR(SYSDATE,'DD-MM-YY') AND SLOT_ID='No Slot Id' AND reg_txn_id=?";
	public static final String INSERT_SLOT_RESCHEDULE_DETAIL="insert into igrs_slot_rescheduled_data (TXN_ID,REG_TXN_ID,SLOTDATE,CREATED_BY,CREATED_DATE,REASONID,DISTRICT_ID,OFFICE_ID, payment_ref_id) values(IGRS_SLOT_RESCHEDULE_SEQ.nextval,?,to_date(?,'dd/mm/yyyy'),?,sysdate,?,(select district_id from igrs_reg_slot_book_txn_dtls where reg_txn_id=?),(select OFFICE_ID from igrs_reg_slot_book_txn_dtls where reg_txn_id=?),?)";
	public static final String CHECK_OFFICE_STATUS="select count(1) from IGRS_OFFICE_MASTER where office_id=? and office_status='A' and district_id=?";
	public static final String GET_OFFICE_DETAIL_ADMIN="SELECT office_id, office_name, h_office_name FROM igrs_office_master WHERE OFFICE_TYPE_ID=? AND office_status='A' order by office_name";
	public static final String GET_OFFICE_DETAIL="SELECT office_id, office_name, h_office_name FROM igrs_office_master WHERE OFFICE_TYPE_ID=? AND office_status='A' and district_id=? order by office_name";
	public static final String GET_DISTRICT_LIST="select district_id, district_name, h_district_name from igrs_district_master where state_id=1 and district_status='A'";
	public static final String ADD_OFFICE_CHECK=" and district_id in (select district_id from igrs_office_master where office_id=?)  order by district_name";
	public static final String UPDATE_OFFICE_SLOT_DATA_1="update igrs_reg_slot_master set ";
	public static final String UPDATE_OFFICE_SLOT_DATA_2="slot_upper_time=? , ";
	public static final String UPDATE_OFFICE_SLOT_DATA_3=", slot_upper_time_status=? ";
	public static final String UPDATE_OFFICE_SLOT_DATA_4=" slot_lower_time_status=? ";
	public static final String UPDATE_OFFICE_SLOT_DATA_5=" where  office_id=? and slot_status='A'";
	public static final String UPDATE_OFFICE_SLOT_DATA_6=" where slot_status='A'";
	public static final String UPDATE_OFFICE_SLOT_DATA_7=" where slot_status='A' and office_id in (select office_id from igrs_office_master where district_id =?)";
	public static final String UDATE_ALL_DISTRICT="update igrs_reg_slot_master set slot_upper_time=?, slot_upper_time_status=?,slot_upper_time_status=? where  DISTRICT_ID=? and slot_status='A'";
	public static final String GET_PAYMENT_COUNT_DETAIL="select NVL(payment_status,'NA') from igrs_slot_rescheduled_data where reg_txn_id=? and reasonid in (select reasonid from igrs_reschedule_reason_master where payment_required = 'Y')";
	public static final String GET_PAYMENT_REQUIRED="select payment_required from igrs_reschedule_reason_master where reasonid = ?";
	public static final String UPDATE_PAYMENT_RECORD="update igrs_slt_booking_pmt_dtl set paid_amount=0 where reg_txn_id=? and payment_type_id=5 AND source_mod_flag='C'";
	public static final String CHECK_PAYMENT_STATUS="SELECT PAYMENT_TXN_ID,nvl(PAID_AMOUNT,0),nvl(PAYABLE_AMOUNT,0), TXN_ID FROM IGRS_REG_PAYMENT_DETLS WHERE CREATED_DATE=(SELECT MAX(CREATED_DATE) FROM IGRS_REG_PAYMENT_DETLS WHERE REG_TXN_ID=?) AND REG_TXN_ID=?  and SOURCE_MOD_FLAG='C' AND PAYMENT_TYPE_ID=5";
	public static final String INSERT_SLOT_RESCHEDULE_DETAIL_PAYMENT="INSERT INTO IGRS_SLOT_BOOK_PMT_STATUS (REG_TXN_ID, PAYMENT_REF_ID, PAYMENT_AMOUNT, CREATED_DATE,PMT_STATUS,PAYMENT_REQ,UPLOADFILEPATH,REASONID,CREATED_BY, booked_date) VALUES (?,?,?,systimestamp,'I',(SELECT PAYMENT_REQUIRED FROM igrs_reschedule_reason_master WHERE reasonid=?),?,?,?, to_date(?,'dd-mm-yy'))";
	public static final String GET_PAYMENT_DETAIL="select nvl(sum(paid_amount),0) from igrs_slt_booking_pmt_dtl where reg_txn_id=? and payment_txn_id is not null";
	public static final String GET_PAYMENT_STATUS="select payment_ref_id from IGRS_SLOT_BOOK_PMT_STATUS where pmt_status='I' and reg_txn_id=?";
	public static final String UPDATE_PAYMENT_STATUS="update IGRS_SLOT_BOOK_PMT_STATUS set pmt_status='S', reasonid=?,UPDATE_BY=? where reg_txn_id=? and  pmt_status='I' ";
	public static final String GET_UPLOAD_FILE_PATH = "select a.payment_ref_id,a.UPLOADFILEPATH,a.REASONID,a.PAYMENT_REQ,a.PAYMENT_AMOUNT,b.REASON_DESC_EN,b.REASON_DESC_HI, to_char(a.booked_date,'DD/MM/YYYY'),a.PAYMENT_REQ from IGRS_SLOT_BOOK_PMT_STATUS a, igrs_reschedule_reason_master b where a.pmt_status='I' and a.reg_txn_id=? and a.REASONID=b.reasonid";
	public static final String GET_INITIATED_PAYMENT_COUNT="select count(1) from IGRS_SLOT_BOOK_PMT_STATUS where pmt_status='I' and reg_txn_id=?";
	public static final String SLOT_RESCHEDULE_REPORT="select distinct c.district_name, d.office_name, a.reg_txn_id, a.payment_req, a.update_by, a.uploadfilepath, e.reason_desc_en, e.reason_desc_hi, f.first_name||' '||f.middle_name||' '||f.last_name,  to_char(a.booked_date,'DD-MON-YYYY'), to_char(g.slotdate,'DD-MON-YYYY') from igrs_slot_book_pmt_status a, igrs_reg_slot_book_txn_dtls b, igrs_district_master c, igrs_office_master d, igrs_reschedule_reason_master e, igrs_user_reg_details f, igrs_slot_rescheduled_data g where a.reg_txn_id=b.reg_Txn_id and b.district_id=c.district_id and b.office_id=d.office_id and a.reasonid=e.reasonid and a.update_by=f.user_id and b.district_id=? and a.reg_txn_id=g.reg_txn_id and g.created_date between to_Date(?,'dd-mm-yy hh24:mi:ss') and to_date(?,'dd-mm-yy hh24:mi:ss') and a.payment_ref_id=g.payment_ref_id and a.pmt_Status='S'";
	public static final String CHK_HOLIDAY="SELECT COUNT(1) FROM IGRS_HOLIDAY_LIST WHERE holiday_date=?";
	public static final String CHECK_VALID_APP="SELECT instruments_id FROM igrs_reg_txn_detls WHERE reg_txn_id=?";
	public static final String CHECK_IF_ALREADY_RESCHEDULED="SELECT COUNT(1) FROM igrs_slot_rescheduled_data WHERE reg_txn_id=?";
	public static final String CHECK_IF_WILL="SELECT COUNT(1) FROM igrs_slot_rescheduled_data WHERE reg_txn_id=? AND instruments_id='2225'";
	public static final String PAYMENT_RECORD = "select distinct PAYMENT_MODE,amount_paid,to_Char(payment_date,'dd-mon-yyyy hh:mi:ss AM'),c.transaction_id,REFERENCE_ID,PAYMENT_MODE_H ,d.first_name||' '||d.middle_name||' '||d.last_name" + " from IGRS_PAYMENT_MODE_MASTER a,IGRS_PAYMENT_SERVICE_LOG b,igrs_payment_txn_details c, igrs_user_reg_details d " + " where a.PAYMENT_MODE_ID=c.PAYMENT_TYPE_ID and b.REFERENCE_ID=c.PARENT_REF_ID " + " and b.PARENT_FUNID=c.payment_purpose and b.REFERENCE_ID=? " + " and parent_key=PARENT_KEY_COL and c.payment_purpose=? and c.user_id=d.user_id";
	
}
