package com.wipro.igrs.empDeductions.dto;

import java.util.ArrayList;


public class EmpDeductionsDTO
{
	private String empId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfJoing;
	private String dateOfBirth;
	private String gender;
	private String id;
	private String label;
	private ArrayList deductions;
	private ArrayList subDeductions;
	private EmpDTO empDTO;
	
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	
	
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
	
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
		
	
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	/**
	 * @return the dateOfJoing
	 */
	public String getDateOfJoing() {
		return dateOfJoing;
	}
	
	
	/**
	 * @param dateOfJoing the dateOfJoing to set
	 */
	public void setDateOfJoing(String dateOfJoing) {
		this.dateOfJoing = dateOfJoing;
	}
	
	
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	
	
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * @return the deductions
	 */
	public ArrayList getDeductions() {
		return deductions;
	}


	/**
	 * @param deductions the deductions to set
	 */
	public void setDeductions(ArrayList deductions) {
		this.deductions = deductions;
	}


	/**
	 * @return the subDeductions
	 */
	public ArrayList getSubDeductions() {
		return subDeductions;
	}


	/**
	 * @param subDeductions the subDeductions to set
	 */
	public void setSubDeductions(ArrayList subDeductions) {
		this.subDeductions = subDeductions;
	}


	/**
	 * @return the empDTO
	 */
	public EmpDTO getEmpDTO() {
		return empDTO;
	}


	/**
	 * @param empDTO the empDTO to set
	 */
	public void setEmpDTO(EmpDTO empDTO) {
		this.empDTO = empDTO;
	}
}
