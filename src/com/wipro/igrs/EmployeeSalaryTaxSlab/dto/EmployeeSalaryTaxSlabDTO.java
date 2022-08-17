/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 
 * ===========================================================================
 * File           :   EmployeeSalaryTaxSlabDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Sayed Taha
 * Created Date   :   Sept 10, 2008

 * ===========================================================================
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeSalaryTaxSlabDTO implements Serializable{
	private String taxID;
	private Float startSlab;
	private Float endSlab;
	private String personType;
	private Float percentApplicacle;
	public String getTaxID() {
		return taxID;
	}
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	public Float getStartSlab() {
		return startSlab;
	}
	public void setStartSlab(Float startSlab) {
		this.startSlab = startSlab;
	}
	public Float getEndSlab() {
		return endSlab;
	}
	public void setEndSlab(Float endSlab) {
		this.endSlab = endSlab;
	}
	public String getPersonType() {
		return personType;
	}
	public void setPersonType(String personType) {
		this.personType = personType;
	}
	public Float getPercentApplicacle() {
		return percentApplicacle;
	}
	public void setPercentApplicacle(Float percentApplicacle) {
		this.percentApplicacle = percentApplicacle;
	}
	
	
}
