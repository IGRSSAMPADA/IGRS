package com.wipro.igrs.SMSAlerts.dto;

import com.wipro.igrs.SMSAlerts.form.SMSAlertsForm;

public class SmsDetails
{
  private String smsTxnId;
  

  private String smsMessasgeContent;
  

  private String smsMobileNumber;
  

  private String smsDistrict;
  

  private String smsZone;
  
  private String smsState;
  
  private String sms_type;
  
  private String estamps_count;
  
  private String estamps_amount;
  
  private String current_date;
  
  private String firstName;
  
  
  //Added by Neeti
  SMSAlertsForm smsAlertsForm = new SMSAlertsForm();
  private String userId;

  public SMSAlertsForm getSmsAlertsForm() {
	return smsAlertsForm;
  }

	public void setSmsAlertsForm(SMSAlertsForm smsAlertsForm) {
		this.smsAlertsForm = smsAlertsForm;
	}

public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

public String getFirstName()
  {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  


  public SmsDetails() {}
  

  public String getSmsTxnId()
  {
    return smsTxnId;
  }
  


  public void setSmsTxnId(String smsTxnId)
  {
    this.smsTxnId = smsTxnId;
  }
  


  public String getSmsMessasgeContent()
  {
    return smsMessasgeContent;
  }
  


  public void setSmsMessasgeContent(String smsMessasgeContent)
  {
    this.smsMessasgeContent = smsMessasgeContent;
  }
  


  public String getSmsMobileNumber()
  {
    return smsMobileNumber;
  }
  


  public void setSmsMobileNumber(String smsMobileNumber)
  {
    this.smsMobileNumber = smsMobileNumber;
  }
  
  public String getSmsDistrict() {
    return smsDistrict;
  }
  
  public void setSmsDistrict(String smsDistrict) {
    this.smsDistrict = smsDistrict;
  }
  
  public String getSmsZone() {
    return smsZone;
  }
  
  public void setSmsZone(String smsZone) {
    this.smsZone = smsZone;
  }
  
  public String getSmsState() {
    return smsState;
  }
  
  public void setSmsState(String smsState) {
    this.smsState = smsState;
  }
  
  public String getSms_type() {
    return sms_type;
  }
  
  public void setSms_type(String sms_type) {
    this.sms_type = sms_type;
  }
  
  public String getEstamps_count() {
    return estamps_count;
  }
  
  public void setEstamps_count(String estamps_count) {
    this.estamps_count = estamps_count;
  }
  
  public String getEstamps_amount() {
    return estamps_amount;
  }
  
  public void setEstamps_amount(String estamps_amount) {
    this.estamps_amount = estamps_amount;
  }
  
  public String getCurrent_date() {
    return current_date;
  }
  
  public void setCurrent_date(String current_date) {
    this.current_date = current_date;
  }
}
