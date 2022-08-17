package com.wipro.igrs.empsalcomp.dto;

public class EmpSalaryDTO {
	
	
	private String empID;
	private String EmpFName;
	private String EmpMName;
	private String EmpLName;
	private String BasicValue;
	private String maxSal;
	private String minSal;
	private String incrementSal;
	private String currentBasic;
	private String empNotFound;
	private String effectiveDate;
	private String slabId;
	private String slab;
	private String increments;
	private String effectiveFlag;
	
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
	public String getEmpNotFound() {
		return empNotFound;
	}
	public void setEmpNotFound(String empNotFound) {
		this.empNotFound = empNotFound;
	}
	public String getCurrentBasic() {
		return currentBasic;
	}
	public void setCurrentBasic(String currentBasic) {
		this.currentBasic = currentBasic;
	}
	public String getBasicValue() {
		return BasicValue;
	}
	public void setBasicValue(String basicValue) {
		BasicValue = basicValue;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpFName() {
		return EmpFName;
	}
	public void setEmpFName(String empFName) {
		EmpFName = empFName;
	}
	public String getEmpMName() {
		return EmpMName;
	}
	public void setEmpMName(String empMName) {
		EmpMName = empMName;
	}
	public String getEmpLName() {
		return EmpLName;
	}
	public void setEmpLName(String empLName) {
		EmpLName = empLName;
	}
	public String getMaxSal() {
		return maxSal;
	}
	public void setMaxSal(String maxSal) {
		this.maxSal = maxSal;
	}
	public String getMinSal() {
		return minSal;
	}
	public void setMinSal(String minSal) {
		this.minSal = minSal;
	}
	public String getIncrementSal() {
		return incrementSal;
	}
	public void setIncrementSal(String incrementSal) {
		this.incrementSal = incrementSal;
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

}
