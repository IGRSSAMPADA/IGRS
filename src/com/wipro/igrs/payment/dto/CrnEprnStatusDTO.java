package com.wipro.igrs.payment.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CrnEprnStatusDTO implements Serializable{
	
	private int paymentModeId;
	private String paymentMode;
	private String crnEprnNo;
	private String formName;
	private String actionName;
	private String amount;
	private Object dateOfPayment;
	private String check;
	
	public int getPaymentModeId() {
		return paymentModeId;
	}

	public void setPaymentModeId(int paymentModeId) {
		this.paymentModeId = paymentModeId;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	private ArrayList viewCrnEprnDetails = new ArrayList();
	public ArrayList getViewCrnEprnDetails() {
		return viewCrnEprnDetails;
	}

	public void setViewCrnEprnDetails(ArrayList viewCrnEprnDetails) {
		this.viewCrnEprnDetails = viewCrnEprnDetails;
	}

	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	private String createdBy;
	private String paymentFlag;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Object getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Object dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getCrnEprnNo() {
		return crnEprnNo;
	}

	public void setCrnEprnNo(String crnEprnNo) {
		this.crnEprnNo = crnEprnNo;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
}
