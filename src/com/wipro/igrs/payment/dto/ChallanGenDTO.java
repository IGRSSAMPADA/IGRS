package com.wipro.igrs.payment.dto;

import java.util.ArrayList;
/**
 * ===========================================================================
 * File           :   ChallanGenDTO.java
 * Description    :   Represents the DTO Class
 * Table          :   IGRS_CHALLAN_GEN_DETAILS
 * Author         :   vengamamba P
 * Created Date   :   Feb 07, 2008

 * ===========================================================================
 */

public class ChallanGenDTO {
	
	private String name;
    private String value;
	private ArrayList officeNameList;
	private ArrayList bankidList;
	private ArrayList useridList;
	   
	
	public ChallanGenDTO () {
	    
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