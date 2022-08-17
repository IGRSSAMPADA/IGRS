package com.wipro.igrs.payment.form;

import org.apache.struts.action.ActionForm;

import org.apache.struts.upload.FormFile;




/**
 * ===========================================================================
 * File : FileUploadForm.java 
 * Description : Represents the Fileupload FormBean
 *  Author : vengamamba P 
 * Created Date : Mar 28, 2008
 * 
 * ===========================================================================
 */
public class FileUploadForm extends ActionForm
{
  private FormFile theFile;

  private String transactionId;
  private String cin;
  private String amount;
  private String scrollno;
  private String depositedDate;
  private String depositedBy;
  private String treasuryName;
  
  private String actionValue;
  
  
  public String getActionValue() {
	return actionValue;
}
public void setActionValue(String actionValue) {
	this.actionValue = actionValue;
}
/**
   * @return Returns the theFile.
   */
  public FormFile getTheFile() {
    return theFile;
  }
  /**
   * @param theFile The FormFile to set.
   */
  public void setTheFile(FormFile theFile) {
    this.theFile = theFile;
  }
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String txnid) {
	transactionId = txnid;
}
public String getCin() {
	return cin;
}
public void setCin(String cin) {
	this.cin = cin;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amt) {
	amount = amt;
}
public String getScrollno() {
	return scrollno;
}
public void setScrollno(String scrollno) {
	this.scrollno = scrollno;
}
public String getDepositedDate() {
	return depositedDate;
}
public void setDepositedDate(String depositedDate) {
	this.depositedDate = depositedDate;
}
public String getDepositedBy() {
	return depositedBy;
}
public void setDepositedBy(String depositedby) {
	this.depositedBy = depositedby;
}
public String getTreasuryName() {
	return treasuryName;
}
public void setTreasuryName(String trsyName) {
	this.treasuryName = trsyName;
}
} 