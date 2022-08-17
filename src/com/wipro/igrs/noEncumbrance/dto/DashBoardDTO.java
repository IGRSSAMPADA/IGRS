package com.wipro.igrs.noEncumbrance.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class DashBoardDTO implements Serializable {

	private Object transactionID;
	private Object hdntransactionID;
	private Object docType;
	private Object paidAmount;
	private Object balanceAmount;
	private Object lastTransactionDate;
	private String actionName;

	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	ArrayList pendingNoEncumApplicationList;
	public ArrayList getPendingNoEncumApplicationList() {
		return pendingNoEncumApplicationList;
	}
	public void setPendingNoEncumApplicationList(
			ArrayList pendingNoEncumApplicationList) {
		this.pendingNoEncumApplicationList = pendingNoEncumApplicationList;
	}
	public Object getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Object transactionID) {
		this.transactionID = transactionID;
	}
	
	public Object getHdntransactionID() {
		return hdntransactionID;
	}
	public void setHdntransactionID(Object hdntransactionID) {
		this.hdntransactionID = hdntransactionID;
	}
	public Object getDocType() {
		return docType;
	}
	public void setDocType(Object docType) {
		this.docType = docType;
	}
	public Object getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Object paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Object getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Object balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public Object getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(Object lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
}
