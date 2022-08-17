/**
 * EmployeeReleaseChanrgesDTO.java
 */
package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class EmployeeSearchDTO implements Serializable
{
	private String strEmpId;
	private String strEmpName;
	private String strEmpGender;
	private String strEmpDesig;
	
 
	/**
	 * 
	 */
	public EmployeeSearchDTO() { 
		
	}


	/**
	 * @return the strEmpId
	 */
	public String getStrEmpId() {
		return strEmpId;
	}


	/**
	 * @param strEmpId the strEmpId to set
	 */
	public void setStrEmpId(String strEmpId) {
		this.strEmpId = strEmpId;
	}


	/**
	 * @return the strEmpName
	 */
	public String getStrEmpName() {
		return strEmpName;
	}


	/**
	 * @param strEmpName the strEmpName to set
	 */
	public void setStrEmpName(String strEmpName) {
		this.strEmpName = strEmpName;
	}


	

	/**
	 * @return the strEmpGender
	 */
	public String getStrEmpGender() {
		return strEmpGender;
	}


	/**
	 * @param strEmpGender the strEmpGender to set
	 */
	public void setStrEmpGender(String strEmpGender) {
		this.strEmpGender = strEmpGender;
	}


	/**
	 * @return the strEmpDesig
	 */
	public String getStrEmpDesig() {
		return strEmpDesig;
	}


	/**
	 * @param strEmpDesig the strEmpDesig to set
	 */
	public void setStrEmpDesig(String strEmpDesig) {
		this.strEmpDesig = strEmpDesig;
	}
		
	
	
	
	
}
