package com.wipro.igrs.RegCompMaker.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ankita
 * this Dto is for capturing other deed regarding details
 * Created Date 06/02/2013
 */
public class OthrDeedDtlsDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String nationality;
	private String address;
	private String country;
	private String state;
	private String city;
	private String district;
	private String postalCode;
	private String phoneNumber;
	private String loanInfo;
	private String taxDuties;
	private String indCountry;
	private String relationshipWit;
	private String displayGender;
	
	public String getDisplayGender() {
		return displayGender;
	}

	public void setDisplayGender(String displayGender) {
		this.displayGender = displayGender;
	}
	
	
	
	public String getIndCountry() {
		return indCountry;
	}
	public void setIndCountry(String indCountry) {
		this.indCountry = indCountry;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLoanInfo() {
		return loanInfo;
	}
	public void setLoanInfo(String loanInfo) {
		this.loanInfo = loanInfo;
	}
	public String getTaxDuties() {
		return taxDuties;
	}
	public void setTaxDuties(String taxDuties) {
		this.taxDuties = taxDuties;
	}
	public String getRelationshipWit() {
		return relationshipWit;
	}
	public void setRelationshipWit(String relationshipWit) {
		this.relationshipWit = relationshipWit;
	}
	

}
