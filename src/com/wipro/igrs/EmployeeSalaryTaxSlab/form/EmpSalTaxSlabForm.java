/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmpSalTaxSlabForm.java
 * Author      :   Sayed Taha
 * Description :   Represents the Action Form class.
 * Created Date   :   sept 10, 2008         	 		 
 * --------------------------------------------------------------------------------
 */
package com.wipro.igrs.EmployeeSalaryTaxSlab.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class EmpSalTaxSlabForm extends ActionForm{
	private String taxID;
	private Float startSlab;
	private Float endSlab;
	private String personType;
	private Float percentApplicacle;
	private ArrayList allEmpSalTaxSlab;
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
	public ArrayList getAllEmpSalTaxSlab() {
		return allEmpSalTaxSlab;
	}
	public void setAllEmpSalTaxSlab(ArrayList allEmpSalTaxSlab) {
		this.allEmpSalTaxSlab = allEmpSalTaxSlab;
	}
}
