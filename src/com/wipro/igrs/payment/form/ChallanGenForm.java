package com.wipro.igrs.payment.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.payment.dto.ChallanGenDTO;

/**
 * ===========================================================================
 * File : CashCounterForm.java 
 * Description : Represents the Cash Counter FormBean
 *  Author : vengamamba P 
 * Created Date : Feb 07, 2008
 * 
 * ===========================================================================
 */

public class ChallanGenForm extends ActionForm {
	//variables related to hidden action values
	private String challanForm;
	private String actionValue;
    //variables related to challangenview
	private String officeid;
	private String officeName;
	private String chdate;
	private String txnid;
	private String createdby;
	private String amt;
	private String delivaryStatus;
    //variables related to challangenupdate
	private String scrollno;
	private String depositdate;
	private String bankid;
	private String depositedby;
	private String error;
	
	private ChallanGenDTO chgendto = new ChallanGenDTO(); 
	
	public String getChallanForm() {
		return challanForm;
	}
	public void setChallanForm(String challanForm) {
		this.challanForm = challanForm;
	}
	public String getActionValue() {
		return actionValue;
	}
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getChdate() {
		return chdate;
	}
	public void setChdate(String chdate) {
		this.chdate = chdate;
	}

	public ChallanGenDTO getChgendto() {
		return chgendto;
	}

	public void setChgendto(ChallanGenDTO chdto) {
		this.chgendto = chdto;
	}
	public String getTxnid() {
		return txnid;
	}
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getOfficeid() {
		return officeid;
	}
	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}
	public String getScrollno() {
		return scrollno;
	}
	public void setScrollno(String scrollno) {
		this.scrollno = scrollno;
	}
	public String getDepositdate() {
		return depositdate;
	}
	public void setDepositdate(String depositdate) {
		this.depositdate = depositdate;
	}
	public String getBankid() {
		return bankid;
	}
	public void setBankid(String bankid) {
		this.bankid = bankid;
	}
	public String getDepositedby() {
		return depositedby;
	}
	public void setDepositedby(String depositedby) {
		this.depositedby = depositedby;
	}
	public String getDelivaryStatus() {
		return delivaryStatus;
	}
	public void setDelivaryStatus(String delivaryStatus) {
		this.delivaryStatus = delivaryStatus;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}


}