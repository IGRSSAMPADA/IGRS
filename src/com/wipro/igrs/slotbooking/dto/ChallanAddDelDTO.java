package com.wipro.igrs.slotbooking.dto;

import java.io.Serializable;

public class ChallanAddDelDTO implements Serializable {
	
	private String[] challanNo= new String[10];
	private String[] challanDate= new String[10];
	private String[] camount= new String[10];
	private String[] chkChallanDel= new String[10];
	public String[] getChallanNo() {
		return challanNo;
	}
	public void setChallanNo(String[] challanNo) {
		this.challanNo = challanNo;
	}
	public String[] getChallanDate() {
		return challanDate;
	}
	public void setChallanDate(String[] challanDate) {
		this.challanDate = challanDate;
	}
	public String[] getCamount() {
		return camount;
	}
	public void setCamount(String[] camount) {
		this.camount = camount;
	}
	public String[] getChkChallanDel() {
		return chkChallanDel;
	}
	public void setChkChallanDel(String[] chkChallanDel) {
		this.chkChallanDel = chkChallanDel;
	}
	
	

}
