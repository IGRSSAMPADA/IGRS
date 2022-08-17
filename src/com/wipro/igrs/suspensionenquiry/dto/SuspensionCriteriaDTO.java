/**
 * 
 */
package com.wipro.igrs.suspensionenquiry.dto;

import java.io.Serializable;

/**
 * @author HMOHAM
 *
 */
public class SuspensionCriteriaDTO implements Serializable{
	
	private String suspensionOrderDateFrom;
	private String suspensionOrderDateTo;
	private String revocationOrderDateFrom;
	private String revocationOrderDateTo;
	private String location;
	private String officeId;
	/**
	 * @return the suspensionOrderDateFrom
	 */
	public String getSuspensionOrderDateFrom() {
		return suspensionOrderDateFrom;
	}
	/**
	 * @param suspensionOrderDateFrom the suspensionOrderDateFrom to set
	 */
	public void setSuspensionOrderDateFrom(String suspensionOrderDateFrom) {
		this.suspensionOrderDateFrom = suspensionOrderDateFrom;
	}
	/**
	 * @return the suspensionOrderDateTo
	 */
	public String getSuspensionOrderDateTo() {
		return suspensionOrderDateTo;
	}
	/**
	 * @param suspensionOrderDateTo the suspensionOrderDateTo to set
	 */
	public void setSuspensionOrderDateTo(String suspensionOrderDateTo) {
		this.suspensionOrderDateTo = suspensionOrderDateTo;
	}
	/**
	 * @return the revocationOrderDateFrom
	 */
	public String getRevocationOrderDateFrom() {
		return revocationOrderDateFrom;
	}
	/**
	 * @param revocationOrderDateFrom the revocationOrderDateFrom to set
	 */
	public void setRevocationOrderDateFrom(String revocationOrderDateFrom) {
		this.revocationOrderDateFrom = revocationOrderDateFrom;
	}
	/**
	 * @return the revocationOrderDateTo
	 */
	public String getRevocationOrderDateTo() {
		return revocationOrderDateTo;
	}
	/**
	 * @param revocationOrderDateTo the revocationOrderDateTo to set
	 */
	public void setRevocationOrderDateTo(String revocationOrderDateTo) {
		this.revocationOrderDateTo = revocationOrderDateTo;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
	
	

}
