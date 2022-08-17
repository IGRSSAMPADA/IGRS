package com.wipro.igrs.db;

import java.io.Serializable;

import com.wipro.igrs.common.PropertiesFileReader;

public class OTPDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Code for SMS Details.
	private String smsUsername = "";
	private String smsPassword = "";
	private String smsTimeout = ""; 
	private String smsUrl = "";
	private String smsSenderId = ""; 
	
	// Code for Email Details.
	
	private String emailUsername = "";
	private String emailPassword = "";
	private String emailHostName = "";
	private String emailPortNo = "";
	private String emailTimeout = "";
	
	
	private String messageOTP;
	private String number;
	private String emailId;
	private String emailFlag;
	private String referenceId;
	public String getSmsUsername() {
		return smsUsername;
	}

	public void setSmsUsername(String smsUsername) {
		this.smsUsername = smsUsername;
	}

	public String getSmsPassword() {
		return smsPassword;
	}

	public void setSmsPassword(String smsPassword) {
		this.smsPassword = smsPassword;
	}

	public String getSmsTimeout() {
		return smsTimeout;
	}

	public void setSmsTimeout(String smsTimeout) {
		this.smsTimeout = smsTimeout;
	}

	public String getSmsUrl() {
		return smsUrl;
	}

	public void setSmsUrl(String smsUrl) {
		this.smsUrl = smsUrl;
	}

	public String getSmsSenderId() {
		return smsSenderId;
	}

	public void setSmsSenderId(String smsSenderId) {
		this.smsSenderId = smsSenderId;
	}

	public String getEmailUsername() {
		return emailUsername;
	}

	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}

	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	public String getEmailHostName() {
		return emailHostName;
	}

	public void setEmailHostName(String emailHostName) {
		this.emailHostName = emailHostName;
	}

	public String getEmailPortNo() {
		return emailPortNo;
	}

	public void setEmailPortNo(String emailPortNo) {
		this.emailPortNo = emailPortNo;
	}

	public String getEmailTimeout() {
		return emailTimeout;
	}

	public void setEmailTimeout(String emailTimeout) {
		this.emailTimeout = emailTimeout;
	}

	public String getMessageOTP() {
		return messageOTP;
	}

	public void setMessageOTP(String messageOTP) {
		this.messageOTP = messageOTP;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public OTPDTO()
	{
		try {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			
			//Getting credentials from Properties File
			smsUsername = pr.getValue("igrs_sms_username");
			smsPassword = pr.getValue("igrs_sms_password");
			smsSenderId = pr.getValue("igrs_sms_senderid");
			smsUrl      = pr.getValue("igrs_sms_url");
			smsTimeout  = pr.getValue("igrs_sms_timeout");
			
			emailHostName =  pr.getValue("igrs_mail_host_name");
			emailPassword =  pr.getValue("igrs_mail_password");
			emailPortNo   =  pr.getValue("igrs_mail_smtp_port");
			emailTimeout  =  pr.getValue("igrs_mail_Smtp_conn_timeout");
			emailUsername =  pr.getValue("igrs_mail_username");
			emailFlag     =  pr.getValue("jms_email_flag");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceId() {
		return referenceId;
	}
	
	
}
