package com.wipro.igrs.payment.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

//import com.wipro.igrs.payment.dto.ChallanGenDTO;
import com.wipro.igrs.payment.dto.EODChallanDTO;

/**
 * ===========================================================================
 * File : EODChallanForm.java 
 * Description : Represents the EOD Challan generation FormBean
 *  Author : Aakriti
 * Created Date : Dec 21, 2012
 * 
 * ===========================================================================
 */

public class EODChallanForm extends ActionForm {
	
	//variables related to hidden action values
	private String revMjrHeadId;
	private String revSubMjrHeadId;
	private String revMnrHeadId;
	/*private String revMjrHeadName;
	private String revSubMjrHeadName;
	private String revMnrHeadName;*/
	private String challanForm;
	private String actionValue;
    //variables related to challangenview
	private String officeid;
	private String officeName;
	private String srName;
	
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
	private String currDate;
	private String disId;
	private String disName;
	private String minDate;
	private String transId;
	private String chdate;
	private String oldNewReceipt="Y";
	private String eprn;
	private int sNo;
	
	

	public String getEprn() {
		return eprn;
	}
	public void setEprn(String eprn) {
		this.eprn = eprn;
	}
	public String getOldNewReceipt() {
		return oldNewReceipt;
	}
	public void setOldNewReceipt(String oldNewReceipt) {
		this.oldNewReceipt = oldNewReceipt;
	}
	public String getRevMjrHeadId() {
		return revMjrHeadId;
	}
	public void setRevMjrHeadId(String revMjrHeadId) {
		this.revMjrHeadId = revMjrHeadId;
	}
	public String getRevSubMjrHeadId() {
		return revSubMjrHeadId;
	}
	public void setRevSubMjrHeadId(String revSubMjrHeadId) {
		this.revSubMjrHeadId = revSubMjrHeadId;
	}
	public String getRevMnrHeadId() {
		return revMnrHeadId;
	}
	public void setRevMnrHeadId(String revMnrHeadId) {
		this.revMnrHeadId = revMnrHeadId;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getCurrDate() {
		return currDate;
	}
	public void setCurrDate(String currDate) {
		this.currDate = currDate;
	}
	private EODChallanDTO chgendto = new EODChallanDTO(); 
	
	
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
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	public String getChdate() {
		return chdate;
	}
	public void setChdate(String chdate) {
		this.chdate = chdate;
	}

	public EODChallanDTO getChgendto() {
		return chgendto;
	}

	public void setChgendto(EODChallanDTO chdto) {
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
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	
	
}
