package com.wipro.igrs.empsalcomp.form;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.struts.action.ActionForm;

public class EmpSalaryActionForm extends ActionForm{
	
	private String empID;
	private String empFName;
	private String empMName;
	private String empLName;
	private String basicValue;
	private String effectiveDate;
	private String slabId;
	private String slab;
	private String increments;
	private String effectiveFlag;
	private String incrementSal;
	private String hiddenIncrements;
	
	private ArrayList salarySlabs;
	
	private String searched=null;
	
	private Vector components;


	public String getEmpID() {
		return empID;
	}


	public void setEmpID(String empID) {
		this.empID = empID;
	}


	public String getEmpFName() {
		return empFName;
	}


	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}


	public String getEmpMName() {
		return empMName;
	}


	public void setEmpMName(String empMName) {
		this.empMName = empMName;
	}


	public String getEmpLName() {
		return empLName;
	}


	public void setEmpLName(String empLName) {
		this.empLName = empLName;
	}


	public String getBasicValue() {
		return basicValue;
	}


	public void setBasicValue(String basicValue) {
		this.basicValue = basicValue;
	}


	public Vector getComponents() {
		return components;
	}


	public void setComponents(Vector components) {
		this.components = components;
	}


	public String getSearched() {
		return searched;
	}


	public void setSearched(String searched) {
		this.searched = searched;
	}


	/**
	 * @return the effectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}


	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}


	/**
	 * @return the slabId
	 */
	public String getSlabId() {
		return slabId;
	}


	/**
	 * @param slabId the slabId to set
	 */
	public void setSlabId(String slabId) {
		this.slabId = slabId;
	}


	/**
	 * @return the slab
	 */
	public String getSlab() {
		return slab;
	}


	/**
	 * @param slab the slab to set
	 */
	public void setSlab(String slab) {
		this.slab = slab;
	}


	/**
	 * @return the increments
	 */
	public String getIncrements() {
		return increments;
	}


	/**
	 * @param increments the increments to set
	 */
	public void setIncrements(String increments) {
		this.increments = increments;
	}


	/**
	 * @return the effectiveFlag
	 */
	public String getEffectiveFlag() {
		return effectiveFlag;
	}


	/**
	 * @param effectiveFlag the effectiveFlag to set
	 */
	public void setEffectiveFlag(String effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}


	/**
	 * @return the salarySlabs
	 */
	public ArrayList getSalarySlabs() {
		return salarySlabs;
	}


	/**
	 * @param salarySlabs the salarySlabs to set
	 */
	public void setSalarySlabs(ArrayList salarySlabs) {
		this.salarySlabs = salarySlabs;
	}


	/**
	 * @return the incrementSal
	 */
	public String getIncrementSal() {
		return incrementSal;
	}


	/**
	 * @param incrementSal the incrementSal to set
	 */
	public void setIncrementSal(String incrementSal) {
		this.incrementSal = incrementSal;
	}


	/**
	 * @return the hiddenIncrements
	 */
	public String getHiddenIncrements() {
		return hiddenIncrements;
	}


	/**
	 * @param hiddenIncrements the hiddenIncrements to set
	 */
	public void setHiddenIncrements(String hiddenIncrements) {
		this.hiddenIncrements = hiddenIncrements;
	}


	

}
