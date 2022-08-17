package com.wipro.igrs.bankingestamp.dto;

import java.io.Serializable;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationAction.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
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
	private String value;
	private String name;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
	
}
