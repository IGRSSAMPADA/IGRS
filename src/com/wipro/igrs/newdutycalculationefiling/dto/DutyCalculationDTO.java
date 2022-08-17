package com.wipro.igrs.newdutycalculationefiling.dto;

import java.io.Serializable;


/**
 * @author PR836429
 * Preeti Kuralkar 20 May 2016
 *
 */
public class DutyCalculationDTO implements Serializable {

	/**
	 * @author Madan Mohan
	 */
	private double baseValue;
	/**
	 * @author Madan Mohan
	 */
	
	//added by preeti
	private String propertyoutMP;
	private String radioResiComm3;
	
	private String radioResiComm;
	public String getRadioResiComm() {
		return radioResiComm;
	}
	public void setRadioResiComm(String radioResiComm) {
		this.radioResiComm = radioResiComm;
	}
	public String getEstampCode() {
		return estampCode;
	}
	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}
	private String estampCode;
	
	private String stampvendor;
	private String codeOfStamps;
	private String seriesNumber;
      private String dateOfStamps;	
	
	//end
	private double annualRent;
	
	private double dutyPaid;
	
	
	private String firstName;
	/**
	 * @author Madan Mohan
	 */
	private String midName;
	/**
	 * @author Madan Mohan
	 */
	private String lastName;
	/**
	 * @author Madan Mohan
	 */
	private String sex;
	/**
	 * @author Madan Mohan
	 */
	private int age;
	/**
	 * @author Madan Mohan
	 */
	
	private String dob;
	private String dobDay;
	private String dobMonth;
	private String dobYear;
	private String currentYear;
	
	private String deedType;
	/**
	 * @author Madan Mohan
	 */
	private String hdnDeedType;
	/**
	 * @author Madan Mohan
	 */
	private String instrumentType;
	/**
	 * @author Madan Mohan
	 */
	private String exemptionType;
	/**
	 * @author Madan Mohan
	 */
	private double stampDuty;
	/**
	 * @author Madan Mohan
	 */
	private String other;
	/**
	 * @author Madan Mohan
	 */
	private String dateCalculation;
	/**
	 * @author Madan Mohan
	 */
	private int deedId;
	/**
	 * @author Madan Mohan
	 */
	private String formName;
	/**
	 * @author Madan Mohan
	 */
	private String actionName;
	/**
	 * @author Madan Mohan
	 */
	private String total;
	
	private double duty;
	
	public double getDuty() {
		return duty;
	}
	public void setDuty(double duty) {
		this.duty = duty;
	}
	/**
	 * @return String
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return String
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * @return String
	 */

	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	public int getDeedId() {
		return deedId;
	}
	public void setDeedId(int deedId) {
		this.deedId = deedId;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return String
	 */
	public String getMidName() {
		return midName;
	}
	/**
	 * @param midName
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}
	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 */
	
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getAnnualRent() {
		return annualRent;
	}
	public void setAnnualRent(double annualRent) {
		this.annualRent = annualRent;
	}
	/**
	 * @return String 
	 */
	
	
	public String getSex() {
		return sex;
	}
	public double getDutyPaid() {
		return dutyPaid;
	}
	public void setDutyPaid(double dutyPaid) {
		this.dutyPaid = dutyPaid;
	}
	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return int
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return String
	 */
	public String getDeedType() {
		return deedType;
	}
	/**
	 * @param deedType
	 */
	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}
	/**
	 * @return String
	 */
	public String getInstrumentType() {
		return instrumentType;
	}
	/**
	 * @param instrumentType
	 */
	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}
	/**
	 * @return String
	 */
	public String getExemptionType() {
		return exemptionType;
	}
	/**
	 * @param exemptionType
	 */
	public void setExemptionType(String exemptionType) {
		this.exemptionType = exemptionType;
	}
	/**
	 * @return String
	 */

	/**
	 * @return String
	 */
	public String getOther() {
		return other;
	}
	public double getStampDuty() {
		return stampDuty;
	}
	public void setStampDuty(double stampDuty) {
		this.stampDuty = stampDuty;
	}
	/**
	 * @param other
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * @return String
	 */
	public String getDateCalculation() {
		return dateCalculation;
	}
	/**
	 * @param dateCalculation
	 */
	public void setDateCalculation(String dateCalculation) {
		this.dateCalculation = dateCalculation;
	}
	/**
	 * @return String
	 */
	public String getHdnDeedType() {
		return hdnDeedType;
	}
	/**
	 * @param hdnDeedType
	 */
	public void setHdnDeedType(String hdnDeedType) {
		this.hdnDeedType = hdnDeedType;
	}
	/**
	 * @return double
	 */
	public double getBaseValue() {
		return baseValue;
	}
	/**
	 * @param baseValue
	 */
	public void setBaseValue(double baseValue) {
		this.baseValue = baseValue;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDobDay() {
		return dobDay;
	}
	public void setDobDay(String dobDay) {
		this.dobDay = dobDay;
	}
	public String getDobMonth() {
		return dobMonth;
	}
	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}
	public String getDobYear() {
		return dobYear;
	}
	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}
	public String getCurrentYear() {
		return currentYear;
	}
	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}
	public void setStampvendor(String stampvendor) {
		this.stampvendor = stampvendor;
	}
	public String getStampvendor() {
		return stampvendor;
	}
	public void setCodeOfStamps(String codeOfStamps) {
		this.codeOfStamps = codeOfStamps;
	}
	public String getCodeOfStamps() {
		return codeOfStamps;
	}
	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	public String getSeriesNumber() {
		return seriesNumber;
	}
	public void setDateOfStamps(String dateOfStamps) {
		this.dateOfStamps = dateOfStamps;
	}
	public String getDateOfStamps() {
		return dateOfStamps;
	}
	public void setRadioResiComm3(String radioResiComm3) {
		this.radioResiComm3 = radioResiComm3;
	}
	public String getRadioResiComm3() {
		return radioResiComm3;
	}
	public void setPropertyoutMP(String propertyoutMP) {
		this.propertyoutMP = propertyoutMP;
	}
	public String getPropertyoutMP() {
		return propertyoutMP;
	}
	
	
	
}
