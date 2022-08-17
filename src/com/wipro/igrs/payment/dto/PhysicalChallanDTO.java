package com.wipro.igrs.payment.dto;

import java.util.ArrayList;

public class PhysicalChallanDTO {
	private String scrollNumber="";
	private String challanDate="";
	private String amount="";	    
	
	
	private String posRefNo;	 
	private ArrayList transactionId;
	private String totalAmt;
	private String txnid;
	private ArrayList physicalChallan;
	private String funcName;
	private String mode;
	private String username;
	private String currentdate;
	private String depositorName;
	private String revMjrHd;
	private String revMnrHd;
	private String revSbMjrHd;
	private String oldNewReceipt;
	
	public String getOldNewReceipt() {
		return oldNewReceipt;
	}
	public void setOldNewReceipt(String oldNewReceipt) {
		this.oldNewReceipt = oldNewReceipt;
	}
	public String getPosRefNo() {
		return posRefNo;
	}
	public void setPosRefNo(String posRefNo) {
		this.posRefNo = posRefNo;
	}
	public String getRevMjrHd() {
		return revMjrHd;
	}
	public void setRevMjrHd(String revMjrHd) {
		this.revMjrHd = revMjrHd;
	}
	public String getRevMnrHd() {
		return revMnrHd;
	}
	public void setRevMnrHd(String revMnrHd) {
		this.revMnrHd = revMnrHd;
	}
	public String getRevSbMjrHd() {
		return revSbMjrHd;
	}
	public void setRevSbMjrHd(String revSbMjrHd) {
		this.revSbMjrHd = revSbMjrHd;
	}
	public String getDepositorName() {
		return depositorName;
	}
	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}
	public ArrayList getPhysicalChallan() {
		return physicalChallan;
	}
	public void setPhysicalChallan(ArrayList physicalChallan) {
		this.physicalChallan = physicalChallan;
	}
	
	public String getScrollNumber() {
		return scrollNumber;
	}
	public void setScrollNumber(String scrollNumber) {
		this.scrollNumber = scrollNumber;
	}
	public ArrayList getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(ArrayList transactionId) {
		this.transactionId = transactionId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTxnid() {
		return txnid;
	}
	public void setTxnid(String txnid) {
		this.txnid = txnid;
	}
	public String getCurrentdate() {
		return currentdate;
	}
	public void setCurrentdate(String currentdate) {
		this.currentdate = currentdate;
	}
	public String getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(String challanDate) {
		this.challanDate = challanDate;
	}
	public String getTotalAmt() {
		return totalAmt;
	}
	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}



}
