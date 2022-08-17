

package com.wipro.igrs.DeliveryOfDocuments.sql;

public class DeliveryOfDocumentsSQL {

	
	//modified by simran
	
 //public static final String DOD_STATUS_DRPDOWN_QRY = "SELECT DELIVERY_STATUS_NAME, DELIVERY_STATUS_ID FROM IGRS_DEL_DOC_STATUS_MASTER WHERE STATUS = 'A' ORDER BY DELIVERY_STATUS_NAME";
	
	public static final String DOD_STATUS_DRPDOWN_QRY = "SELECT DELIVERY_STATUS_NAME, DELIVERY_STATUS_ID,H_DELIVERY_STATUS_NAME FROM IGRS_DEL_DOC_STATUS_MASTER WHERE STATUS = 'A' AND DELIVERY_STATUS_ID NOT IN ('9') ORDER BY DELIVERY_STATUS_NAME";
	public static final String DOD_STATUS_CHANGE_QRY ="SELECT DELIVERY_STATUS_NAME, DELIVERY_STATUS_ID,H_DELIVERY_STATUS_NAME FROM IGRS_DEL_DOC_STATUS_MASTER WHERE STATUS = 'A' AND DELIVERY_STATUS_ID  IN ('1','9') ORDER BY DELIVERY_STATUS_NAME";
	

	/*public static final String REG_DETL_QRY = "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'),t.DELIVERY_STATUS_ID, "+
	                                          "m.DELIVERY_STATUS_NAME FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m  WHERE "+
	                                          "t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND (t.REGISTRATION_NUMBER=? OR "+
	                                          "t.DELIVERY_STATUS_ID=? OR t.REG_COMP_DATE BETWEEN ? AND ?)";
*/

 public static final String REG_DETL_QRY = "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'),t.DELIVERY_STATUS_ID, m.DELIVERY_STATUS_NAME, "+
                                              "TO_CHAR(t.ONE_MONTH_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                              "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,t.RECEIPIENT_TYPE_ID,t.DOCKET_NUMBER,m.H_DELIVERY_STATUS_NAME,o.H_OFFICE_NAME "+
                                              "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
                                              "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID "+
                                              "AND (t.REGISTRATION_NUMBER=? OR t.DELIVERY_STATUS_ID=? OR t.REG_COMP_DATE BETWEEN TO_DATE(?, 'dd/mm/yyyy') AND TO_DATE(?, 'dd/mm/yyyy')) "+
                                              "AND t.SR_OFFICE_ID=? ";
	
 public static final String REG_DETL_DR_QRY= "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'),t.DELIVERY_STATUS_ID, m.DELIVERY_STATUS_NAME, "+
                                             "TO_CHAR(t.ONE_MONTH_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                             "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,t.RECEIPIENT_TYPE_ID,t.DOCKET_NUMBER "+
                                             "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
                                             "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID "+
                                             "AND (t.REGISTRATION_NUMBER=? OR t.DELIVERY_STATUS_ID=? OR t.REG_COMP_DATE BETWEEN TO_DATE(?, 'dd/mm/yyyy') AND TO_DATE(?, 'dd/mm/yyyy')) "+
                                             "AND t.SR_OFFICE_ID IN (SELECT OFFICE_ID FROM IGRS_OFFICE_MASTER WHERE DISTRICT_ID=? AND OFFICE_TYPE_ID=3)";
	
 public static final String RPRSNTATV_DETL_QRY = "SELECT RECPNT_FIRST_NAME||'  '||RECPNT_MIDDLE_NAME||'  '||RECPNT_LAST_NAME, AUTH_LETTER_NAME,AUTH_LETTER_PATH "+
                                                 "FROM IGRS_DEL_DOC_RECPNT_DETL WHERE REGISTRATION_NUMBER=? AND RECEIPIENT_TYPE_ID=2";
 
 
 
 public static final String DESTROY_STATUS_UPDATE="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=6, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=? "+
                                                  "WHERE REGISTRATION_NUMBER=? AND DELIVERY_STATUS_ID=5";
	
 public static final String APPROVE_STATUS_UPDATE="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=5, UPDATE_BY=?, UPDATE_DATE=SYSDATE, DR_OFFICE_ID=?,DR_USER_ID=?  "+
                                                  "WHERE REGISTRATION_NUMBER=? AND DELIVERY_STATUS_ID=4";

 public static final String DELIVERED_STATUS_UPDATE="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=7, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?, RECEIPIENT_TYPE_ID=1 "+
                                                    "WHERE REGISTRATION_NUMBER=? AND (DELIVERY_STATUS_ID=8 OR DELIVERY_STATUS_ID=3) ";
 
 
 public static final String DELIVERED_STATUS_UPDATE_POST ="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=7, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?, DOCKET_NUMBER=? "+
                                                          "WHERE REGISTRATION_NUMBER=? AND DELIVERY_STATUS_ID=8 AND BY_POST='Y'";

 public static final String DELIVERED_STATUS_UPDATE1="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=7, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?, RECEIPIENT_TYPE_ID=2 "+
                                                     "WHERE REGISTRATION_NUMBER=? AND (DELIVERY_STATUS_ID=8 OR DELIVERY_STATUS_ID=3)";
 
 public static final String NOTICE_STATUS_UPDATE= "UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=3, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?"+
                                                  "WHERE REGISTRATION_NUMBER=? AND DELIVERY_STATUS_ID=2";

 public static final String PRINTED_STATUS_UPDATE1="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=9, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?, RECEIPIENT_TYPE_ID=2 "+
 												"WHERE REGISTRATION_NUMBER=? AND (DELIVERY_STATUS_ID=1 OR DELIVERY_STATUS_ID=3)";
 
 public static final String UNDELIVERED_STATUS_UPDATE1="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=8, UPDATE_BY=?, UPDATE_DATE=SYSDATE, SR_OFFICE_ID=?, RECEIPIENT_TYPE_ID=2 "+
												"WHERE REGISTRATION_NUMBER=? AND (DELIVERY_STATUS_ID=9 OR DELIVERY_STATUS_ID=3)";
 
 public static final String RECPNT_INSERT="INSERT INTO IGRS_DEL_DOC_RECPNT_DETL (RECPNT_DETL_ID, REGISTRATION_NUMBER,RECPNT_FIRST_NAME,CREATED_BY,CREATED_DATE,RECEIPIENT_TYPE_ID,RECPNT_SIGN_PATH_HAND) "+
                                          "VALUES(?,?,?,?,SYSDATE,1,?)";
 
 public static final String NOTICE_INSERT="INSERT INTO IGRS_DEL_DOC_NOTICE_DETL (NOTICE_DETL_ID, REGISTRATION_NUMBER,NOTICE_REMARKS,CREATED_BY,CREATED_DATE, OFFICE_ID) "+
                                          "VALUES(?,?,?,?,SYSDATE,?)";

 public static final String SIGNATURE_TIME_UPDATE="UPDATE IGRS_REG_TXN_DETLS SET SIGNATURE_TIME=SYSTIMESTAMP WHERE REGISTRATION_NUMBER=?";
 
 public static final String RECPNT_INSERT1="INSERT INTO IGRS_DEL_DOC_RECPNT_DETL (RECPNT_DETL_ID, REGISTRATION_NUMBER,RECPNT_FIRST_NAME,RECPNT_MIDDLE_NAME,RECPNT_LAST_NAME,CREATED_BY,AUTH_LETTER_NAME,AUTH_LETTER_PATH,CREATED_DATE,RECEIPIENT_TYPE_ID) "+
                                           "VALUES(?,?,?,?,?,?,?,?,SYSDATE,2)";

public static final String DESTROY_DETL="SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'), m.DELIVERY_STATUS_NAME, "+
                                        "TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                        "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME, "+
                                        "v.FIRST_NAME||'  '||v.MIDDLE_NAME||'  '||v.LAST_NAME, om.OFFICE_NAME, m.H_DELIVERY_STATUS_NAME,o.H_OFFICE_NAME, om.H_OFFICE_NAME "+
                                        "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u, IGRS_USER_REG_DETAILS v,IGRS_OFFICE_MASTER om "+
                                        "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID AND t.DR_USER_ID=v.USER_ID AND t.DR_OFFICE_ID=om.OFFICE_ID "+
                                        "AND t.REGISTRATION_NUMBER=? AND t.DELIVERY_STATUS_ID=6";

public static final String ISSUE_DETL= "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'), m.DELIVERY_STATUS_NAME, "+
                                       "TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                       "TO_CHAR(n.CREATED_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME, n.NOTICE_REMARKS,m.H_DELIVERY_STATUS_NAME, o.H_OFFICE_NAME ,T.RECPNT_SIGN_PATH_HAND "+
                                       "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u, IGRS_DEL_DOC_NOTICE_DETL n "+
                                       "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND n.OFFICE_ID=o.OFFICE_ID AND n.CREATED_BY=u.USER_ID AND t.REGISTRATION_NUMBER = n.REGISTRATION_NUMBER "+
                                       "AND t.REGISTRATION_NUMBER=? AND (t.DELIVERY_STATUS_ID=3 OR t.DELIVERY_STATUS_ID=4 OR t.DELIVERY_STATUS_ID=5 OR t.DELIVERY_STATUS_ID=6 OR t.DELIVERY_STATUS_ID=7)";


public static final String APPROVE_DETL="SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'), m.DELIVERY_STATUS_NAME, "+
                                        "TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                        "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),v.FIRST_NAME||'  '||v.MIDDLE_NAME||'  '||v.LAST_NAME, om.OFFICE_NAME "+
                                        "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_USER_REG_DETAILS v,IGRS_OFFICE_MASTER om "+
                                        "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.DR_USER_ID=v.USER_ID AND t.DR_OFFICE_ID=om.OFFICE_ID "+
                                        "AND t.REGISTRATION_NUMBER=? AND t.DELIVERY_STATUS_ID=5";


public static final String DELIVERED_DETL="SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'), m.DELIVERY_STATUS_NAME, "+
                                          "TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                          "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,m.H_DELIVERY_STATUS_NAME,o.H_OFFICE_NAME "+
                                          "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
                                          "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID "+
                                          "AND t.REGISTRATION_NUMBER=? AND t.DELIVERY_STATUS_ID=7";


public static final String DELIVERED_DETL_POST="SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'), m.DELIVERY_STATUS_NAME, "+
                                               "TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
                                               "TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME, t.DOCKET_NUMBER "+
                                               "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
                                               "WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID "+
                                               "AND t.REGISTRATION_NUMBER=? AND t.DELIVERY_STATUS_ID=7 AND BY_POST='Y'";

public static final String DR_DETL="SELECT u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,o.H_OFFICE_NAME "+
                                   "FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
                                   "WHERE t.DR_OFFICE_ID=o.OFFICE_ID AND t.DR_USER_ID=u.USER_ID AND t.REGISTRATION_NUMBER=?";

public static final String TRANS_ID = "SELECT REG_TXN_ID FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER=?";

public static final String TRANS_DETL="SELECT PARTY_FIRST_NAME||'  '||PARTY_MIDDLE_NAME||'  '||PARTY_LAST_NAME, AUTHRZD_PERSN_NAME "+
                                      "FROM IGRS_REG_TXN_PARTY_DETLS WHERE REG_TXN_ID = ? "+
                                      "AND (IS_APPLICANT='Y' OR IS_APPLICANT='N' OR IS_APPLICANT='C')";


public static final String NOTICE_TRANS_DETL= "SELECT p.PARTY_TYPE_NAME, r.PARTY_FIRST_NAME||'  '||r.PARTY_MIDDLE_NAME||'  '||r.PARTY_LAST_NAME, "+
                                              "r.AUTHRZD_PERSN_NAME, r.PARTY_ADDRESS,p.PARTY_TYPE_ID,r.ROLE_NAME,p.h_PARTY_TYPE_NAME,r.party_txn_id "+ 
                                              "FROM IGRS_REG_TXN_PARTY_DETLS r, IGRS_PARTY_TYPE_MASTER p "+ 
                                              "WHERE REG_TXN_ID = ? AND r.PARTY_TYPE_ID=p.PARTY_TYPE_ID  "+
                                              "AND (IS_APPLICANT='Y' OR IS_APPLICANT='N' OR IS_APPLICANT='C')";



public static final String RECP_DETL= "SELECT RECPNT_FIRST_NAME "+
                                      "FROM IGRS_DEL_DOC_RECPNT_DETL WHERE REGISTRATION_NUMBER = ? "+
                                      "AND RECEIPIENT_TYPE_ID=1";
/*public static final String TRANS_DETL="SELECT PARTY_FIRST_NAME||' '||PARTY_MIDDLE_NAME||' '||PARTY_LAST_NAME, AUTHRZD_PERSN_NAME "+
"FROM IGRS_REG_TXN_PARTY_DETLS WHERE REG_TXN_ID = ?";*/

public static final String GET_PAY_DTLS = "SELECT PAYMENT_DETL_ID, PAYABLE_AMOUNT, PAID_AMOUNT, PAYMENT_FLAG FROM IGRS_DEL_DOC_PAYMENT_DETLS "+
                                          "WHERE REGISTRATION_NUMBER = ? AND CREATED_DATE = (SELECT MAX(CREATED_DATE)FROM IGRS_DEL_DOC_PAYMENT_DETLS WHERE REGISTRATION_NUMBER = ?)";

public static final String PAY_TABLE_INSERT = "INSERT INTO IGRS_DEL_DOC_PAYMENT_DETLS"+
                                              " (PAYMENT_DETL_ID, REGISTRATION_NUMBER, PAYABLE_AMOUNT,"+
                                              "  PAYMENT_FLAG, CREATED_BY, CREATED_DATE)"+
                                              " VALUES(?,?,?,?,?, SYSDATE)";

public static final String GET_MAIN_ID = "SELECT REGISTRATION_NUMBER FROM IGRS_DEL_DOC_PAYMENT_DETLS WHERE PAYMENT_DETL_ID = ? ";

public static final String PAY_FLAG_UPDTAE = "UPDATE IGRS_DEL_DOC_PAYMENT_DETLS SET PAYMENT_FLAG='C',UPDATE_BY=?, UPDATE_DATE=SYSDATE "+ 
                                             "WHERE REGISTRATION_NUMBER=? AND PAYMENT_FLAG = 'P'";

public static final String MODE_DETL = "SELECT r.POSTAL_ADDRESS, d.DISTRICT_NAME, s.STATE_NAME,c.COUNTRY_NAME,d.H_DISTRICT_NAME, s.H_STATE_NAME,c.H_COUNTRY_NAME "+ 
                                       "FROM IGRS_REG_TXN_DETLS r,IGRS_COUNTRY_MASTER c,IGRS_STATE_MASTER s,IGRS_DISTRICT_MASTER d "+ 
                                       "WHERE REGISTRATION_NUMBER = ? AND r.POSTAL_DISTRICT_ID=d.DISTRICT_ID AND r.POSTAL_STATE_ID=s.STATE_ID "+
                                       "AND r.POSTAL_COUNTRY_ID=c.COUNTRY_ID ";



public static final String REQ_DETL_QRY= "SELECT m.OFFICE_NAME, m.DISTRICT_ID, d.DISTRICT_NAME FROM IGRS_OFFICE_MASTER m, IGRS_DISTRICT_MASTER d " +
                                         "WHERE  m.OFFICE_ID=? AND m.DISTRICT_ID =d.DISTRICT_ID";
//*****added by SIMRAN**********//
public static final String GET_DEL_DOC_RECPNT_ID_SEQ  = "select IGRS_DEL_DOC_RECPNT_ID_SEQ.nextval from dual";

public static final String GET_DEL_PAYMENT_ID_SEQ  = "select IGRS_DEL_DOC_PAYMENT_ID_SEQ.nextval from dual";

//following query for fee matrix -- copied from adjudicatioN package
public static final String SERVICE_FEE_PROCEDURE = "CALL " 
	+"IGRS_SERVICE_FEE_PKG.IGRS_SERVICE_FEE_PROC(?,?,?,?,?,?)";

// ADDED BY SIMRAN

public static final String GET_OFFC_TYPE_ID = "SELECT OFFICE_TYPE_ID FROM IGRS_OFFICE_MASTER WHERE OFFICE_ID = ?";

public static final String GET_CHILD_OFFICE = "SELECT OFFICE_ID FROM IGRS_OFFICE_MASTER WHERE PARENT_ID = ?";

public static final String REG_DETL_QRY_FIRST_DR = "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'),t.DELIVERY_STATUS_ID, m.DELIVERY_STATUS_NAME, "+
"TO_CHAR(t.ONE_MONTH_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
"TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,t.RECEIPIENT_TYPE_ID,t.DOCKET_NUMBER,m.H_DELIVERY_STATUS_NAME,o.H_OFFICE_NAME "+
"FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u "+
"WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID ";


public static final String REG_DETL_QRY_REG_NUM_DR = " AND t.REGISTRATION_NUMBER=? ";

public static final String REG_DETL_QRY_DEL_STATUS_DR = " AND t.DELIVERY_STATUS_ID=? ";

//public static final String REG_DETL_QRY_DEL_STATUS_TOBEPRINTED = " AND t.DELIVERY_STATUS_ID IN(?,?) ";

public static final String REG_DETL_QRY_DEL_DATE_DR = " AND (t.REG_COMP_DATE BETWEEN TO_DATE(?, 'dd/mm/yyyy') AND TO_DATE(?, 'dd/mm/yyyy')+1) "; 

public static final String REG_DETL_QRY_SEC_DR = " AND t.SR_OFFICE_ID in ( ";

public static final String REG_DETL_QRY_THIRD_DR = ")";

public static final String REG_DETL_QRY_FIRST = "SELECT t.REGISTRATION_NUMBER, TO_CHAR(t.REG_COMP_DATE,'dd/MM/yyyy hh:mi:ss AM'),t.DELIVERY_STATUS_ID, m.DELIVERY_STATUS_NAME, "+
"TO_CHAR(t.ONE_MONTH_DATE,'dd/MM/yyyy hh:mi:ss AM'),TO_CHAR(t.LAST_DELIVERY_DATE,'dd/MM/yyyy hh:mi:ss AM'), TO_CHAR(t.LAST_DUE_DATE,'dd/MM/yyyy hh:mi:ss AM'), "+
"TO_CHAR(t.UPDATE_DATE,'dd/MM/yyyy hh:mi:ss AM'),u.FIRST_NAME||'  '||u.MIDDLE_NAME||'  '||u.LAST_NAME, o.OFFICE_NAME,t.RECEIPIENT_TYPE_ID,t.DOCKET_NUMBER,m.H_DELIVERY_STATUS_NAME,o.H_OFFICE_NAME,a.update_by "+
"FROM IGRS_DEL_DOC_TXN_DETL t, IGRS_DEL_DOC_STATUS_MASTER m, IGRS_OFFICE_MASTER o, IGRS_USER_REG_DETAILS u ,igrs_reg_txn_detls a "+
"WHERE t.DELIVERY_STATUS_ID=m.DELIVERY_STATUS_ID AND t.SR_OFFICE_ID=o.OFFICE_ID AND t.UPDATE_BY=u.USER_ID and a.registration_number=t.registration_number ";

public static final String REG_DETL_QRY_REG_NUM = " AND t.REGISTRATION_NUMBER = ? ";

public static final String REG_DETL_QRY_DEL_STATUS = " AND t.DELIVERY_STATUS_ID=? ";

public static final String REG_DETL_QRY_DEL_DATE = " AND (t.REG_COMP_DATE BETWEEN TO_DATE(?, 'dd/mm/yyyy') AND TO_DATE(?, 'dd/mm/yyyy')+1) "; 

public static final String REG_DETL_QRY_SEC = " AND t.SR_OFFICE_ID=? ";

public static final String GET_STATE_DETLS = "SELECT STATE_NAME FROM IGRS_REG_TXN_PARTY_DETLS p, IGRS_STATE_MASTER d WHERE d.STATE_ID = p.STATE_ID AND PARTY_TXN_ID = ?";

public static final String GET_COUNTRY_DETLS = "SELECT COUNTRY_NAME FROM IGRS_REG_TXN_PARTY_DETLS p, IGRS_COUNTRY_MASTER d WHERE d.COUNTRY_ID = p.COUNTRY_ID AND PARTY_TXN_ID = ?";

public static final String GET_DISTRICT_DETLS = "SELECT DISTRICT_NAME FROM IGRS_REG_TXN_PARTY_DETLS p, IGRS_DISTRICT_MASTER d WHERE d.DISTRICT_ID = p.DISTRICT_ID AND PARTY_TXN_ID = ?";

public static final String GET_REG_TXN_ID = "SELECT REG_TXN_ID FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER = ?";

public static final String GET_DEED_ID = "SELECT DEED_ID FROM  IGRS_REG_TXN_DETLS R,IGRS_REG_PROPRTY_DTLS P WHERE R.REG_TXN_ID = P.REG_TXN_ID AND P.PROPERTY_ID=?";

public static final String GET_REG_TXN_PROP_ID = "SELECT P.REG_TXN_ID FROM IGRS_REG_PROPRTY_DTLS P WHERE P.PROPERTY_ID=?";

public static final String INSERT_SMS_CLAIMANT_TRUST ="insert into IGRS_SMS_TABLE (SMS_TXN_ID,MOBILE_NUMBER,SMS_STATUS,CREATED_DATE,CREATED_BY,MESSAGE) "+
											"select IGRS_SMS_SEQUENCE.nextval, det.MOBILE_NUMBER ,1,sysdate,?,'Your pin number for property ID '|| map.PROPERTY_ID||' is:'|| prop.PIN_NO "+
											"from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map,IGRS_REG_PROPRTY_DTLS prop  "+
											"where det.PARTY_TXN_ID=map.PARTY_TXN_ID  and map.PROPERTY_ID=? "+
											"and prop.PROPERTY_ID=map.PROPERTY_ID and det.MOBILE_NUMBER is not null";

public static final String INSERT_SMS_CLAIMANT_CONVEYANCE ="insert into IGRS_SMS_TABLE (SMS_TXN_ID,MOBILE_NUMBER,SMS_STATUS,CREATED_DATE,CREATED_BY,MESSAGE) "+
															"select IGRS_SMS_SEQUENCE.nextval, det.MOBILE_NUMBER ,1,sysdate,?,'Your pin number for property ID '|| map.PROPERTY_ID||' is:'|| prop.PIN_NO "+
															"from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map ,IGRS_PARTY_TYPE_MASTER mast,IGRS_REG_PROPRTY_DTLS prop  "+
															"where det.PARTY_TXN_ID=map.PARTY_TXN_ID and mast.CLAIMANT_FLAG in (3,4) and det.party_type_id=mast.party_type_id and prop.PROPERTY_ID=map.PROPERTY_ID "+
															"and map.PROPERTY_ID=? and det.MOBILE_NUMBER is not null";

public static final String INSERT_SMS_CLAIMANT_EXCHANGE ="insert into IGRS_SMS_TABLE (SMS_TXN_ID,MOBILE_NUMBER,SMS_STATUS,CREATED_DATE,CREATED_BY,MESSAGE) "+
														"select IGRS_SMS_SEQUENCE.nextval, det.MOBILE_NUMBER ,1,sysdate,?,'Your pin number for property ID '|| map.PROPERTY_ID||' is:'|| prop.PIN_NO "+
														"from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map,IGRS_REG_TXN_DETLS reg ,IGRS_REG_PROPRTY_DTLS prop  "+
														"where det.PARTY_TXN_ID=map.PARTY_TXN_ID and reg.REG_TXN_ID=map.REG_TXN_ID "+
														"and prop.PROPERTY_ID=map.PROPERTY_ID  and map.PROPERTY_ID not in(select distinct PROPERTY_ID  "+
														"from IGRS_REG_INIT_PROP_TRANS_MAP where PARTY_TXN_ID in(select PARTY_TXN_ID from IGRS_REG_INIT_PROP_TRANS_MAP where PROPERTY_ID=?)) and reg.REG_TXN_ID=?  and det.MOBILE_NUMBER is not null";



public static final String GET_PIN_PROPERTY_ID="select PROPERTY_ID from IGRS_REG_PROPRTY_DTLS where REG_TXN_ID=? and PIN_NO is not null and PIN_FLAG='A'";

public static final String GET_PIN_PROPERTY_ID_LINKING="select OLD_PROP_ID from IGRS_REG_LNKNG_PROP_HISTORY l,IGRS_REG_PROPRTY_DTLS p where l.INITIATED_PROP_ID in(select PROPERTY_ID from IGRS_REG_PROPRTY_DTLS where reg_txn_id=?) and p.PIN_NO is not null and p.PIN_FLAG='A' and l.OLD_PROP_ID=p.PROPERTY_ID";

public static final String VIEW_CLAIMANI_DOD_TRUST ="select  map.PROPERTY_ID,case det.APPELLATE_TYPE_ID when 2 then NVL(to_char(det.PARTY_FIRST_NAME),'')||' '||nvl(to_char(det.PARTY_MIDDLE_NAME),'')||' '||NVL(to_char(det.PARTY_LAST_NAME),'') when "+
													"1 then NVL(to_char(det.AUTHRZD_PERSN_NAME),'')  ELSE NVL(to_char(det.NAME_OF_OFFICIAL),'') end,det.MOBILE_NUMBER "+
													"from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map,IGRS_REG_PROPRTY_DTLS prop  "+
													"where det.PARTY_TXN_ID=map.PARTY_TXN_ID  and map.PROPERTY_ID=? "+
													"and prop.PROPERTY_ID=map.PROPERTY_ID and prop.PIN_NO is not null and prop.PIN_FLAG='A' ";

public static final String VIEW_CLAIMANI_DOD_CONVEYANCE ="select  map.PROPERTY_ID,case det.APPELLATE_TYPE_ID when 2 then NVL(to_char(det.PARTY_FIRST_NAME),'')||' '||nvl(to_char(det.PARTY_MIDDLE_NAME),'')||' '||NVL(to_char(det.PARTY_LAST_NAME),'') when "+
														 "1 then nvl(to_char(det.AUTHRZD_PERSN_NAME),'')  ELSE NVL(to_char(det.NAME_OF_OFFICIAL),'') end,det.MOBILE_NUMBER "+
														 "from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map ,IGRS_PARTY_TYPE_MASTER mast,IGRS_REG_PROPRTY_DTLS prop "+
														 "where det.PARTY_TXN_ID=map.PARTY_TXN_ID  and det.party_type_id=mast.party_type_id and map.PROPERTY_ID=? "+
														 "and prop.PROPERTY_ID=map.PROPERTY_ID and prop.PIN_NO is not null and prop.PIN_FLAG='A' and mast.CLAIMANT_FLAG in (3,4) ";

public static final String VIEW_CLAIMANI_DOD_EXCHANGE="select  map.PROPERTY_ID,case det.APPELLATE_TYPE_ID when 2 then nvl(to_char(det.PARTY_FIRST_NAME),'')||' '||nvl(to_char(det.PARTY_MIDDLE_NAME),'')||' '||nvl(to_char(det.PARTY_LAST_NAME),'') when "+
													 "1 then NVL(to_char(det.AUTHRZD_PERSN_NAME),'')  ELSE NVL(to_char(det.NAME_OF_OFFICIAL),'') end,det.MOBILE_NUMBER "+
													 "from IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map,IGRS_REG_TXN_DETLS reg ,IGRS_REG_PROPRTY_DTLS prop  "+
													 "where det.PARTY_TXN_ID=map.PARTY_TXN_ID and reg.REG_TXN_ID=map.REG_TXN_ID "+
													 " and prop.PROPERTY_ID=map.PROPERTY_ID and prop.PIN_NO is not null and prop.PIN_FLAG='A' "+
													 " and map.PROPERTY_ID not in(select distinct PROPERTY_ID "+
													 "from IGRS_REG_INIT_PROP_TRANS_MAP where PARTY_TXN_ID in(select PARTY_TXN_ID from IGRS_REG_INIT_PROP_TRANS_MAP where PROPERTY_ID=?)) and reg.REG_TXN_ID=?  ";

public static final String INSERT_SMS_DOD_HAND="insert into IGRS_SMS_TABLE (SMS_TXN_ID,MOBILE_NUMBER,SMS_STATUS,CREATED_DATE,CREATED_BY,MESSAGE) "+
												"select IGRS_SMS_SEQUENCE.nextval, ? ,1,sysdate,?,'Your pin number for property ID '|| ?||' is:'|| prop.PIN_NO "+
												"from IGRS_REG_PROPRTY_DTLS prop  where prop.PROPERTY_ID=? and prop.PIN_NO is not null and prop.PIN_FLAG='A' ";

public static final String GET_SIGNED_STATUS_NAME = "SELECT DELIVERY_STATUS_NAME,H_DELIVERY_STATUS_NAME FROM IGRS_DEL_DOC_STATUS_MASTER WHERE DELIVERY_STATUS_ID = 9";

public static final String GET_SIGN_FILE_PATH = "SELECT RECPNT_SIGN_PATH_HAND FROM IGRS_DEL_DOC_RECPNT_DETL WHERE REGISTRATION_NUMBER = ? ";

public static final String GET_FEE = "SELECT FEE_DR FROM IGRS_FUNCTION_SERVICE_MAPPING WHERE FUNCTION_ID=?";

public static final String GET_PRINT_STATUS = "select delivery_status_name,delivery_status_id from igrs_del_doc_status_master where delivery_status_id in(select delivery_status_id from igrs_del_doc_txn_detl where registration_number=?)";

public static final String GET_DIST_NAME = "SELECT DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE DISTRICT_ID = ?";

public static final String GET_DEED_NAME = "SELECT DEED_TYPE_NAME FROM IGRS_DEED_TYPE_MASTER WHERE DEED_TYPE_ID = ?";

public static final String CHANGE_STATUS_UPDATE="UPDATE IGRS_DEL_DOC_TXN_DETL SET DELIVERY_STATUS_ID=?, UPDATE_DATE=SYSDATE  WHERE REGISTRATION_NUMBER=? AND DELIVERY_STATUS_ID=?";
public static final String SELECT_UPDATED_STATUS="select delivery_status_id from IGRS_DEL_DOC_TXN_DETL WHERE REGISTRATION_NUMBER=?";
public static final String CHECK_PRINT_FLAG = "SELECT PRINT_FLAG FROM IGRS_REG_TXN_DETLS  WHERE REGISTRATION_NUMBER=?";
public static final String UPDATED_PRINTED_FLAG="UPDATE IGRS_REG_TXN_DETLS SET PRINT_FLAG='Y'  WHERE REGISTRATION_NUMBER=?";
public static final String UPDATED_READY_TOPRINT_FLAG="UPDATE IGRS_REG_TXN_DETLS SET PRINT_FLAG='N'  WHERE REGISTRATION_NUMBER=?";

//Start : Changes added by Neeti for RCMS

public static final String CHECK_DEED_TYPE= " select DEED.DEED_TYPE_ID from IGRS_REG_TXN_DETLS REG, IGRS_DEED_TYPE_MASTER DEED where REG_TXN_ID = ? "+ 
											" AND REG.DEED_ID=DEED.DEED_TYPE_ID AND DEED.RCMS_FLAG='Y' AND REG.CANCELLATION_FLAG='N'";

public static final String CHECK_AGRI_LAND = " select PROPERTY_ID  from IGRS_REG_PROPRTY_DTLS where REG_TXN_ID=? AND PROP_TYPE_ID='3' " ;

public static final String GET_RCMS_FLAG = " SELECT RCMS_FLAG FROM IGRS_REG_TXN_DETLS WHERE REG_TXN_ID= ? ";

public static final String GET_TEHSIL_CHECK = " select TEHSIL_ID from IGRS_OFFICE_MASTER where OFFICE_ID in (select REGISTRATION_OFFICE_ID from IGRS_REG_TXN_DETLS where REGISTRATION_NUMBER=? )";

//End : Changes added by Neeti for RCMS
//Start : Changes added by Rupali for QMS

public static final String GET_STATUS_DELIVERED="SELECT A.DELIVERY_STATUS_ID,B.REG_TXN_ID FROM IGRS_DEL_DOC_TXN_DETL A,IGRS_REG_TXN_DETLS B WHERE  A.REGISTRATION_NUMBER=? " +
		"AND A.DELIVERY_STATUS_ID=7 AND A.REGISTRATION_NUMBER=B.REGISTRATION_NUMBER";
//End : Changes added by Rupali for QMS

}

