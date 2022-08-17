package com.wipro.igrs.payments.dto;

import java.io.Serializable;

public class OnlineDTO implements Serializable{
	
	
	    private boolean challanflag = false;
        private String onlineCIN="";
	    private String onlineDate="";
	    private String onlineAmount="";	    
	    private String onlineDepositorName;
	    
	    
	    
	    
		public boolean isChallanflag() {
			return challanflag;
		}
		public void setChallanflag(boolean challanflag) {
			this.challanflag = challanflag;
		}
		public String getOnlineCIN() {
			return onlineCIN;
		}
		public void setOnlineCIN(String onlineCIN) {
			this.onlineCIN = onlineCIN;
		}
		public String getOnlineDate() {
			return onlineDate;
		}
		public void setOnlineDate(String onlineDate) {
			this.onlineDate = onlineDate;
		}
		public String getOnlineAmount() {
			return onlineAmount;
		}
		public void setOnlineAmount(String onlineAmount) {
			this.onlineAmount = onlineAmount;
		}
		public String getOnlineDepositorName() {
			return onlineDepositorName;
		}
		public void setOnlineDepositorName(String onlineDepositorName) {
			this.onlineDepositorName = onlineDepositorName;
		}
}
