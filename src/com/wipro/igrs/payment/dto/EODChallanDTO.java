package com.wipro.igrs.payment.dto;

import java.util.ArrayList;
/**
 * ===========================================================================
 * File           :   EODChallanDTO.java
 * Description    :   Represents the DTO Class
 * Table          :   IGRS_CHALLAN_GEN_DETAILS
 * Author         :   Aakriti
 * Created Date   :   Dec 21, 2012

 * ===========================================================================
 */

public class EODChallanDTO {
	private String name;
    private String value;
	private ArrayList officeNameList;
	private ArrayList bankidList;
	private ArrayList useridList;
	private String amountCollected;
	private String language;
	   
	private ArrayList revMjrHeadList;
	private ArrayList revSubMjrHeadList;
	private ArrayList revMnrHeadList;
	
	
	

	public ArrayList getRevMjrHeadList() {
		return revMjrHeadList;
	}
	public void setRevMjrHeadList(ArrayList revMjrHeadList) {
		this.revMjrHeadList = revMjrHeadList;
	}
	public ArrayList getRevSubMjrHeadList() {
		return revSubMjrHeadList;
	}
	public void setRevSubMjrHeadList(ArrayList revSubMjrHeadList) {
		this.revSubMjrHeadList = revSubMjrHeadList;
	}
	public ArrayList getRevMnrHeadList() {
		return revMnrHeadList;
	}
	public void setRevMnrHeadList(ArrayList revMnrHeadList) {
		this.revMnrHeadList = revMnrHeadList;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getAmountCollected() {
		return amountCollected;
	}
	public void setAmountCollected(String amountCollected) {
		this.amountCollected = amountCollected;
	}
	public EODChallanDTO () {
	    
    }
	public ArrayList getBankidList() {
		return bankidList;
	}

	public void setBankidList(ArrayList bankidList) {
		this.bankidList = bankidList;
	}

	public ArrayList getUseridList() {
		return useridList;
	}

	public void setUseridList(ArrayList useridList) {
		this.useridList = useridList;
	}

	
	
	public ArrayList getOfficeNameList() {
		return officeNameList;
	}


	public void setOfficeNameList(ArrayList officeNameList) {
		this.officeNameList = officeNameList;
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

		
	    

}
