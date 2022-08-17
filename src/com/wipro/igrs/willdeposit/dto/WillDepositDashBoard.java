package com.wipro.igrs.willdeposit.dto;

import java.util.ArrayList;

public class WillDepositDashBoard {
	
	private String will_txn_id;
	private String no_of_parties;
	private String paid_amount;
	private String due_amount;
	private String updated_date;
	private String created_date;
	private String payable_amount;
	private ArrayList PendingWithdrawList= new ArrayList();
	private String willStatus;
	private int srNo;
	
	
	
	
	
	public int getSrNo() {
		return srNo;
	}
	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}
	public String getWillStatus() {
		return willStatus;
	}
	public void setWillStatus(String willStatus) {
		this.willStatus = willStatus;
	}
	public ArrayList getPendingWithdrawList() {
		return getPendingWithdrawList();
	}
	public void setPendingWithdrawList(ArrayList pendingWithdrawList) {
		PendingWithdrawList = pendingWithdrawList;
	}
	
	public Object getWill_txn_id() {
		return will_txn_id;
	}
	public void setWill_txn_id(String will_txn_id) {
		this.will_txn_id = will_txn_id;
	}
	public Object getNo_of_parties() {
		return no_of_parties;
	}
	public void setNo_of_parties(String no_of_parties) {
		this.no_of_parties = no_of_parties;
	}
	public Object getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(String paid_amount) {
		this.paid_amount = paid_amount;
	}
	public Object getDue_amount() {
		return due_amount;
	}
	public void setDue_amount(String due_amount) {
		this.due_amount = due_amount;
	}
	public Object getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(String updated_date) {
		this.updated_date = updated_date;
	}
	public Object getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public Object getPayable_amount() {
		return payable_amount;
	}
	public void setPayable_amount(String payable_amount) {
		this.payable_amount = payable_amount;
	}
	
	
	

}
