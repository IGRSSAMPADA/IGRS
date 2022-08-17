/**
 * 
 */
package com.wipro.igrs.suspensionenquiry.dto;

import java.io.Serializable;

/**
 * @author MRAMAD
 *
 */
public class SuspensionDTO implements Serializable{

	private String empName;
	private String designation;
	private String office;
	private String suspensionDate;
	private String suspensionOrderNo;
	private String revocationDate;
	private String revocationOrderNo;
	private String competentAuthorityName;
	private String competentAuthorityDesignation;
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	/**
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * @return the suspensionDate
	 */
	public String getSuspensionDate() {
		return suspensionDate;
	}
	/**
	 * @param suspensionDate the suspensionDate to set
	 */
	public void setSuspensionDate(String suspensionDate) {
		this.suspensionDate = suspensionDate;
	}
	/**
	 * @return the suspensionOrderNo
	 */
	public String getSuspensionOrderNo() {
		return suspensionOrderNo;
	}
	/**
	 * @param suspensionOrderNo the suspensionOrderNo to set
	 */
	public void setSuspensionOrderNo(String suspensionOrderNo) {
		this.suspensionOrderNo = suspensionOrderNo;
	}
	/**
	 * @return the revocationDate
	 */
	public String getRevocationDate() {
		return revocationDate;
	}
	/**
	 * @param revocationDate the revocationDate to set
	 */
	public void setRevocationDate(String revocationDate) {
		this.revocationDate = revocationDate;
	}
	/**
	 * @return the revocationOrderNo
	 */
	public String getRevocationOrderNo() {
		return revocationOrderNo;
	}
	/**
	 * @param revocationOrderNo the revocationOrderNo to set
	 */
	public void setRevocationOrderNo(String revocationOrderNo) {
		this.revocationOrderNo = revocationOrderNo;
	}
	/**
	 * @return the competentAuthorityName
	 */
	public String getCompetentAuthorityName() {
		return competentAuthorityName;
	}
	/**
	 * @param competentAuthorityName the competentAuthorityName to set
	 */
	public void setCompetentAuthorityName(String competentAuthorityName) {
		this.competentAuthorityName = competentAuthorityName;
	}
	/**
	 * @return the competentAuthorityDesignation
	 */
	public String getCompetentAuthorityDesignation() {
		return competentAuthorityDesignation;
	}
	/**
	 * @param competentAuthorityDesignation the competentAuthorityDesignation to set
	 */
	public void setCompetentAuthorityDesignation(
			String competentAuthorityDesignation) {
		this.competentAuthorityDesignation = competentAuthorityDesignation;
	}

	
}
