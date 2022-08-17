package com.wipro.igrs.payment.dto;

import java.util.ArrayList;
/**
 * ===========================================================================
 * File           :   CashCounterLinkedDTO.java
 * Description    :   Represents the DTO Class
 * Table          :   IGRS_PAYMENT_CASH_DETAILS
 * Author         :   vengamamba P
 * Created Date   :   Feb 07, 2008

 * ===========================================================================
 */

public class CashCounterLinkedDTO {
	
	private String name;
    private String value;
	private ArrayList photoIDList;
	private ArrayList funcIDList; 
	private ArrayList bankIDList;
	private int addMoreCounter; 
	private String listService;
	private String funName;
	private String servFee;
	private String keyId;
	private String deleteService;
	private String otherFee;
	private String serviceFee;
	private String totalFee;
	private String otherFees1;
	private String serviceFees1;
	private String totalFees1;
	private String revenueMjrHd;
	private String revenueSubMjrHd;
	private String revenueMnrHd;
	private String photoIdno;
	 
	/*public String toString(){
		return name+value;
	}
	*/
	
	public String getOtherFees1() {
		return otherFees1;
	}


	public String getRevenueMjrHd() {
		return revenueMjrHd;
	}


	public void setRevenueMjrHd(String revenueMjrHd) {
		this.revenueMjrHd = revenueMjrHd;
	}


	public String getRevenueSubMjrHd() {
		return revenueSubMjrHd;
	}


	public void setRevenueSubMjrHd(String revenueSubMjrHd) {
		this.revenueSubMjrHd = revenueSubMjrHd;
	}


	public String getRevenueMnrHd() {
		return revenueMnrHd;
	}


	public void setRevenueMnrHd(String revenueMnrHd) {
		this.revenueMnrHd = revenueMnrHd;
	}


	public void setOtherFees1(String otherFees1) {
		this.otherFees1 = otherFees1;
	}


	public String getServiceFees1() {
		return serviceFees1;
	}


	public void setServiceFees1(String serviceFees1) {
		this.serviceFees1 = serviceFees1;
	}


	public String getTotalFees1() {
		return totalFees1;
	}


	public void setTotalFees1(String totalFees1) {
		this.totalFees1 = totalFees1;
	}


	public String getOtherFee() {
		return otherFee;
	}


	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}


	public String getServiceFee() {
		return serviceFee;
	}


	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}


	public String getTotalFee() {
		return totalFee;
	}


	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}


	public String getDeleteService() {
		return deleteService;
	}


	public void setDeleteService(String deleteService) {
		this.deleteService = deleteService;
	}


	public String getKeyId() {
		return keyId;
	}


	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}


	public int getAddMoreCounter() {
		return addMoreCounter;
	}


	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}

	public String getListService() {
		return listService;
	}


	public void setListService(String listService) {
		this.listService = listService;
	}


	public String getFunName() {
		return funName;
	}


	public void setFunName(String funName) {
		this.funName = funName;
	}


	public String getServFee() {
		return servFee;
	}


	public void setServFee(String servFee) {
		this.servFee = servFee;
	}
	public ArrayList getBankIDList() {
		return bankIDList;
	}


	public void setBankIDList(ArrayList bankIDList) {
		this.bankIDList = bankIDList;
	}


	public ArrayList getFuncIDList() {
		return funcIDList;
	}


	public void setFuncIDList(ArrayList funcIDList) {
		this.funcIDList = funcIDList;
	}


		
	    

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return value;
	    }

		public ArrayList getPhotoIDList() {
			return photoIDList;
		}


		public void setPhotoIDList(ArrayList photoIDList) {
			this.photoIDList = photoIDList;
		}


		public void setPhotoIdno(String photoIdno) {
			this.photoIdno = photoIdno;
		}


		public String getPhotoIdno() {
			return photoIdno;
		}

	
}
