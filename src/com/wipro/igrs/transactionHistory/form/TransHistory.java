package com.wipro.igrs.transactionHistory.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.transactionHistory.DTO.TransactionHistoryDTO;

public class TransHistory extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	
	String firstName;
	ArrayList district;

	public ArrayList getDistrict() {
		return district;
	}

	public void setDistrict(ArrayList district) {
		this.district = district;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private TransactionHistoryDTO willDTO = new TransactionHistoryDTO();

	public TransactionHistoryDTO getWillDTO() {
		return willDTO;
	}

	public void setWillDTO(TransactionHistoryDTO willDTO) {
		this.willDTO = willDTO;
	}
}
