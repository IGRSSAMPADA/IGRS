package com.wipro.igrs.db;

public class OTPSQL {

	
	public static final String IGRS_UPDATE_OTP="UPDATE IGRS_OTP_MASTER set OTP_STATUS='D' where REFERENCE_ID=? and MODULE_ID=?";
	
	public static final String IGRS_GET_EMAIL = "select email_id from IGRS_USER_REG_DETAILS where user_id=?";
	
	public static final String IGRS_GET_MOBILE = "select mobile_number from IGRS_USER_REG_DETAILS where user_id=?";
	
	public static final String IGRS_CHECK_OTP="SELECT OTP from IGRS_OTP_MASTER where OTP=? and OTP_STATUS='A' and to_char(CREATED_DATE,'dd/mm/yyyy')=to_char(sysdate,'dd/mm/yyyy')";

	public static final String IGRS_INSERT_OTP = "insert into igrs_otp_master values(OTP_SEQUENCE.nextval,?,?,?,'A',sysdate,?,?,?,'N','N')";
	
	public static final String IGRS_UPDATE_SMS_SENT = "update igrs_otp_master set SMS_SENT_STATUS='Y' where SMS_SENT_STATUS='N' and reference_id=? and MODULE_ID=? and CREATED_BY=?";
	
	public static final String IGRS_UPDATE_EMAIL_SENT = "update igrs_otp_master set EMAIL_SENT_STATUS='Y' where EMAIL_SENT_STATUS='N' and reference_id=? and MODULE_ID=? and CREATED_BY=?";

	public static final String IGRS_VALIDATE_OTP ="select id from igrs_otp_master where otp_status='A' and reference_id=? and module_id=? and created_by=? and  OTP=? and to_char(created_date,'DD-MON-YYYY')=to_char(sysdate,'DD-MON-YYYY')";

	public static final String IGRS_TIME_VALIDATE_OTP = "select (sysdate - to_date((SELECT to_char(created_date,'dd/mm/yyyy hh24:mi') FROM igrs_otp_master WHERE id=?) ,'dd/mm/yyyy hh24:mi')) * 1440 from dual";

	public static final String IGRS_CONSUME_OTP = "update igrs_otp_master set otp_status='D' where id=?";
}
 