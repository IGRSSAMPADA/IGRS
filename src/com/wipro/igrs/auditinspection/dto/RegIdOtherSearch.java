/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;
import java.util.ArrayList;


public class RegIdOtherSearch implements Serializable {
	private String	filling_id;

	private String	filling_date;

	private String	stamp_details;

	private String	other_fees;

	private String	first_name;

	private String	middle_name;

	private String	last_name;

	private String	age;

	private String	address;

	private String	gender;

	private String	district;

	private String	state;

	private String	country;

	private String	postal_code;

	private String	phone_number;

	private String	bank_name;

	private String	bank_address;

	private String	srName;
	
	private String	abc;
	
	
	
	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	//adedd by shruti
	private String marketVal;
	
	//added by vinay
	

	public String getMarketVal() {
		return marketVal;
	}

	public void setMarketVal(String marketVal) {
		this.marketVal = marketVal;
	}

	/**
	 * @return the filling_id
	 */
	public String getFilling_id() {
		return filling_id;
	}

	/**
	 * @param filling_id
	 *            the filling_id to set
	 */
	public void setFilling_id(String filling_id) {
		this.filling_id = filling_id;
	}

	/**
	 * @return the filling_date
	 */
	public String getFilling_date() {
		return filling_date;
	}

	/**
	 * @param filling_date
	 *            the filling_date to set
	 */
	public void setFilling_date(String filling_date) {
		this.filling_date = filling_date;
	}

	/**
	 * @return the stamp_details
	 */
	public String getStamp_details() {
		return stamp_details;
	}

	/**
	 * @param stamp_details
	 *            the stamp_details to set
	 */
	public void setStamp_details(String stamp_details) {
		this.stamp_details = stamp_details;
	}

	/**
	 * @return the other_fees
	 */
	public String getOther_fees() {
		return other_fees;
	}

	/**
	 * @param other_fees
	 *            the other_fees to set
	 */
	public void setOther_fees(String other_fees) {
		this.other_fees = other_fees;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name
	 *            the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the middle_name
	 */
	public String getMiddle_name() {
		return middle_name;
	}

	/**
	 * @param middle_name
	 *            the middle_name to set
	 */
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name
	 *            the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district
	 *            the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the postal_code
	 */
	public String getPostal_code() {
		return postal_code;
	}

	/**
	 * @param postal_code
	 *            the postal_code to set
	 */
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	/**
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * @param phone_number
	 *            the phone_number to set
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	/**
	 * @return the bank_name
	 */
	public String getBank_name() {
		return bank_name;
	}

	/**
	 * @param bank_name
	 *            the bank_name to set
	 */
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	/**
	 * @return the bank_address
	 */
	public String getBank_address() {
		return bank_address;
	}

	/**
	 * @param bank_address
	 *            the bank_address to set
	 */
	public void setBank_address(String bank_address) {
		this.bank_address = bank_address;
	}

	/**
	 * @return the srName
	 */
	public String getSrName() {
		return srName;
	}

	/**
	 * @param srName the srName to set
	 */
	public void setSrName(String srName) {
		this.srName = srName;
	}

}
