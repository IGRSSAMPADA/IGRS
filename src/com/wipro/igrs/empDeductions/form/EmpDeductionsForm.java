package com.wipro.igrs.empDeductions.form;

import org.apache.struts.action.ActionForm;

public class EmpDeductionsForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String viewType="userId";
	private String empId;
	private String firstName;
	private String middleName;
	private String lastName;
	
	
	/**
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}
	
	
	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}
	
	
	/**
	 * @return the empId
	 */
	public String getEmpId() {
		return empId;
	}
	
	
	/**
	 * @param empId the refId to set
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

}
