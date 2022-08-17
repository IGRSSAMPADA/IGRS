package com.wipro.igrs.willdeposit.dto;

public class ChallanDTO {	

	    private boolean challanflag = false;

	    private String challanNo="";
	    private String challanDate="";
	    private String amount="";	    
	    private String button;
	        
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

	   



}
