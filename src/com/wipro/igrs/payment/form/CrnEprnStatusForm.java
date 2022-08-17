package com.wipro.igrs.payment.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.payment.dto.CrnEprnStatusDTO;

public class CrnEprnStatusForm extends ActionForm{
	
	private String paymentMode;
	private String crnEprnNo;
	private String userId;
	private String amount;
	private Object dateOfPayment;
	private String createdBy;
	private String paymentFlag;
	private ArrayList paymentModeList =
		new ArrayList();


	public ArrayList getPaymentModeList() {
		return paymentModeList;
	}

	public void setPaymentModeList(ArrayList paymentModeList) {
		this.paymentModeList = paymentModeList;
	}

	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private CrnEprnStatusDTO crnEprnStatusDTO = 
		new CrnEprnStatusDTO();
	private ArrayList crnEprnStatusDTOList = new ArrayList();

	public CrnEprnStatusDTO getCrnEprnStatusDTO() {
		return crnEprnStatusDTO;
	}

	public void setCrnEprnStatusDTO(CrnEprnStatusDTO crnEprnStatusDTO) {
		this.crnEprnStatusDTO = crnEprnStatusDTO;
	}

	public ArrayList getCrnEprnStatusDTOList() {
		return crnEprnStatusDTOList;
	}

	public void setCrnEprnStatusDTOList(ArrayList crnEprnStatusDTOList) {
		this.crnEprnStatusDTOList = crnEprnStatusDTOList;
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
