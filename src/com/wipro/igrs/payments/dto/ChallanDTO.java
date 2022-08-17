package com.wipro.igrs.payments.dto;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * ===========================================================================
 * File           :   ChallanDTO.java
 * Description    :   Represents the ChalanDTO Class. 
 * Author         :   Karteek P
 * Created Date   :   March 18, 2008

 * ===========================================================================
 */
public class ChallanDTO implements Serializable{	

	    private boolean challanflag = false;

	    private String challanNo="";
	    private String challanDate="";
	    private String amount="";	    
	    private String button;
	    private String delrow;
	    private String bankId;
	    private String bankName;
	    private String bankCode; 
	    private String depositorName;
	    public String getDepositorName() {
			return depositorName;
		}

		public void setDepositorName(String depositorName) {
			this.depositorName = depositorName;
		}

		private ArrayList bankList=new ArrayList();   
	    /**
		 * @return the delrow
		 */
		public String getDelrow() {
			return delrow;
		}

		/**
		 * @param delrow the delrow to set
		 */
		public void setDelrow(String delrow) {
			this.delrow = delrow;
		}

		/**
		 * @return the button
		 */
		public String getButton() {
			return button;
		}

		/**
		 * @param button the button to set
		 */
		public void setButton(String button) {
			this.button = button;
		}

		public void setChallanNo(String challanNo) {
	        this.challanNo = challanNo;
	    }

	    public String getChallanNo() {
	        return challanNo;
	    }
	    
	    public void setChallanDate(String challanDate) {
	        this.challanDate = challanDate;
	    }

	    public String getChallanDate() {
	        return challanDate;
	    }

	    public void setAmount(String amount) {
	        this.amount = amount;
	    }

	    public String getAmount() {
	        return amount;
	    }

	    public void setChallanflag(boolean challanflag) {
	        this.challanflag = challanflag;
	    }

	    public boolean isChallanflag() {
	        return challanflag;
	    }

		public String getBankId() {
			return bankId;
		}

		public void setBankId(String bankId) {
			this.bankId = bankId;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankCode() {
			return bankCode;
		}

		public void setBankCode(String bankCode) {
			this.bankCode = bankCode;
		}

		public ArrayList getBankList() {
			return bankList;
		}

		public void setBankList(ArrayList bankList) {
			this.bankList = bankList;
		}

	   



}
