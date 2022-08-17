/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   CommonSQL.java
 * Author      :   Neesha
 * Description :   Represents the common SQL File for User Registration.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 * ----------------------------------------------------------------------------
 * 1.0             Neesha  16thJan,2008	 		 
 * --------------------------------------------------------------------------------
 */
  
  
package com.wipro.igrs.PinGeneration.sql;


 /**
 * @author neegaga
 *
 */
public class CommonSQL {
	
	
		public static final String CHECK =
			"SELECT * FROM IGRS_REG_PIN_REQ_DETAILS";
	
		public static final String VIEW =
			"select  B.USER_ID,B.PIN_REQ_TXN_ID,TO_CHAR(B.REQ_DATE,'DD/MM/YYYY'),C.PARTY_FIRST_NAME_H,C.PARTY_MIDDLE_NAME_H,C.PARTY_LAST_NAME_H,C.PARTY_GENDER,C.PARTY_AGE,C.GUARDIAN_NAME,C.MOTHER_NAME,C.SPOUSE_NAME,C.PARTY_ADDRESS,C.COUNTRY_ID,C.STATE_ID,C.DISTRICT_ID,C.POSTAL_CODE,C.PHONE_NUMBER,C.MOBILE_NUMBER,C.EMAIL_ID,C.PHOTO_PROOF_ID,A.REGISTRATION_ID,B.DEED_VERIFIED,B.DR_REMARKS,B.PROPERTY_TXN_ID,B.CERTIFICATE_DETAILS,B.MUTATION_DOC_NO,B.DEED_VERIFIED FROM IGRS_REG_PIN_DETAILS A,IGRS_REG_PIN_REQ_DETAILS B,IGRS_REG_PIN_REQ_PARTY_DTLS C WHERE B.PIN_REQ_TXN_ID=C.PIN_REQ_TXN_ID AND B.PIN_NO=A.PIN_NUMBER";
	
	 public static final String COUNTRY = 
	        "SELECT COUNTRY_ID,COUNTRY_NAME,H_COUNTRY_NAME FROM IGRS_COUNTRY_MASTER";

	    public static final String DISTRICT = 
	        "SELECT DISTRICT_ID,DISTRICT_NAME,H_DISTRICT_NAME FROM IGRS_DISTRICT_MASTER WHERE ";
	    public static final String STATE = 
	        "SELECT STATE_ID,STATE_NAME,H_STATE_NAME FROM IGRS_STATE_MASTER where country_id='";
	    public static final String IGRS_ID_TYPE = 
	        "SELECT PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_TYPE_NAME,H_PHOTO_PROOF_TYPE_NAME FROM IGRS_PHOTOID_PROOF_TYPE_MASTER";
        public static final String PROPERTY =
        	"SELECT  REGI.PROPERTY_ID " +
    		"FROM IGRS_REG_PROPRTY_DTLS REGI, IGRS_PROPERTY_TYPE_MASTER TYP , IGRS_REG_TXN_DETLS REGC " +
    		"WHERE REGI.PROPERTY_ID IS NOT NULL AND " +
    		"REGI.PROP_TYPE_ID = TYP.PROPERTY_TYPE_ID AND " +
    		"REGI.REG_TXN_ID = REGC.REG_TXN_ID AND REGC.REGISTRATION_NUMBER =";
        
        public static final String PINCREATE =
        	"INSERT INTO IGRS_REG_PIN_DETAILS(REGISTRATION_ID,PIN_NUMBER,ACTIVE_FLAG,USER_ID,DELIVERY_STATUS)VALUES(?,?,?,?,'N')";
        
        public static final String PINREQDETAIL =
        	"INSERT INTO IGRS_REG_PIN_REQ_DETAILS(PIN_REQ_TXN_ID,REGISTRATION_ID,PROPERTY_TXN_ID,USER_ID,PIN_NO,PIN_REQ_STATUS,PIN_STATUS,CERTIFICATE_DETAILS,MUTATION_DOC_NO,DEED_VERIFIED,DEATH_CERTIFICATE,MUTATION_DOCUMENT,DR_REMARKS,REQ_DATE)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
        
        public static final String PINREQPARTYDETAIL =
        	"INSERT INTO IGRS_REG_PIN_REQ_PARTY_DTLS(PIN_REQ_TXN_ID,PARTY_TYPE,PARTY_FIRST_NAME_H,PARTY_MIDDLE_NAME_H,PARTY_LAST_NAME_H,PARTY_GENDER,PARTY_AGE,COUNTRY_ID,STATE_ID,DISTRICT_ID,PARTY_ADDRESS,POSTAL_CODE,PHONE_NUMBER,MOBILE_NUMBER,EMAIL_ID,PHOTO_PROOF_TYPE_ID,PHOTO_PROOF_ID,GUARDIAN_NAME,MOTHER_NAME,SPOUSE_NAME,FILE_PATH,FILE_DEATH,FILE_MUTATION,FILE_RELATION,REMARKS,RELATION,REGISTRAION_NUMBER,DEED_VERIFIED)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        public static final String PINREQPARTYOWNERDETAIL =
            	"INSERT INTO IGRS_REG_PIN_REQ_PARTY_DTLS(PIN_REQ_TXN_ID,PARTY_TYPE,REGISTRAION_NUMBER,FILE_PATH,FILE_SUPP_DOC,DEED_VERIFIED,REMARKS) VALUES(?,?,?,?,?,?,?)";
        public static final String PINDOCDETAILS =
        	"INSERT INTO IGRS_REG_PIN_DOC_DETAILS(PIN_REQ_TXN_ID,DOCUMENT_NAME,DOCUMENT_EXTN,DOCUMENT_OBJ)VALUES(?,?,?,?)";
	    
     /**
     *Insert Pin No. into table corresponding to each Registration Id
     */
    public static final String PIN_CREDENTIALS_INSERT = "INSERT INTO PIN_GENERATION(REGISTRATION_ID,PIN_NO)VALUES(?,?)";
    public static final String SEARCH_PIN_CREDENTIALS = "SELECT PIN_NO FROM PIN_GENERATION WHERE REGISTRATION_ID=?";
    public static final String UPDATE_PIN_CREDENTIALS = "update pin_generation set pin_no = ? where registration_id =? and pin_no = ?";

    public static final String IGRS_NOENCUM_REGID_SEARCH="SELECT REGC.REG_TXN_ID, REGI.PROPERTY_ID, REGI.PROP_TYPE_ID, TYP.PROPERTY_TYPE_DESC,REGI.PIN_FLAG " +
    		"FROM IGRS_REG_PROPRTY_DTLS REGI, IGRS_PROPERTY_TYPE_MASTER TYP , IGRS_REG_TXN_DETLS REGC " +
    		"WHERE REGI.PROPERTY_ID IS NOT NULL AND " +
    		"REGI.PROP_TYPE_ID = TYP.PROPERTY_TYPE_ID AND " +
    		"REGI.REG_TXN_ID = REGC.REG_TXN_ID AND REGC.REGISTRATION_NUMBER = ?";

    public static final String CLAIMAINT_DETAILS="select  det.PARTY_FIRST_NAME,det.PARTY_MIDDLE_NAME,det.PARTY_LAST_NAME,det.PARTY_GENDER,det.PARTY_AGE,det.PARTY_ADDRESS,det.PHONE_NUMBER,det.EMAIL_ID,det.ORGANIZATION_NAME,det.AUTHRZD_PERSN_NAME,det.GUARDIAN_NAME,det.APPELLATE_TYPE_ID,det.MOBILE_NUMBER from "+ 
    		"IGRS_REG_TXN_PARTY_DETLS det,IGRS_REG_INIT_PROP_TRANS_MAP map ,IGRS_PARTY_TYPE_MASTER mast "+
    		"where det.PARTY_TXN_ID=map.PARTY_TXN_ID and mast.CLAIMANT_FLAG in (3,4) and det.party_type_id=mast.party_type_id and map.PROPERTY_ID=? ";

    public static final String GET_DEED_ID="SELECT DEED_ID FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER=?";

    public static final String GET_PROPRTY_DETIALS_FOR_PIN = "SELECT DISTINCT DISTRICT_ID,TEHSIL_ID,PROP_TYPE_ID,PROPERTY_ID FROM IGRS_REG_PROPRTY_DTLS WHERE PROPERTY_ID IN(";
	 
	 public static final String UPADTE_PIN_DETAILS = "UPDATE IGRS_REG_PROPRTY_DTLS SET PIN_NO = ?, PIN_FLAG = ? WHERE PROPERTY_ID = ?";

	 public static final String GET_PIN_DETAILS_FOR_PRINT = "SELECT DISTINCT distt.DISTRICT_NAME,tehs.TEHSIL_NAME,prop.PROPERTY_TYPE_NAME,reg.PROPERTY_ID,reg.PIN_NO FROM " +
		"IGRS_REG_PROPRTY_DTLS reg,IGRS_DISTRICT_MASTER distt, IGRS_TEHSIL_MASTER tehs ,IGRS_PROPERTY_TYPE_MASTER prop " +
		"WHERE reg.PROPERTY_ID IN(";
	 public static final String GET_PIN_DETAILS = "and reg.District_id = distt.DISTRICT_ID and reg.TEHSIL_ID = tehs.TEHSIL_ID and " +
		"reg.PROP_TYPE_ID = prop.PROPERTY_TYPE_ID";

	 public static final String CHANGE_PIN="UPDATE IGRS_REG_PROPRTY_DTLS SET PIN_NO=? WHERE PROPERTY_ID=? AND PIN_NO=? AND PIN_FLAG='A'";
	 public static final String CHECK_PIN="SELECT PIN_FLAG FROM IGRS_REG_PROPRTY_DTLS WHERE PROPERTY_ID=? ";

	 public static final String GET_REG_TXN_NO="SELECT REG_TXN_ID FROM IGRS_REG_TXN_DETLS WHERE REGISTRATION_NUMBER=? ";

	 
	 public static final String INSERT_SMS_DETAILS = "INSERT INTO IGRS_SMS_TABLE(SMS_TXN_ID ,MOBILE_NUMBER ,SMS_STATUS ,CREATED_DATE,CREATED_BY,MESSAGE)" +
		" VALUES(IGRS_SMS_SEQUENCE.NEXTVAL,?,1,SYSDATE,?,?)";
}
