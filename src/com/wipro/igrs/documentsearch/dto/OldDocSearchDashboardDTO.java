/**
 * 
 */
package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;

/**
 * @author Aakash Agarwal
 * @email aakash.agarwal1@wipro.com
 */
public class OldDocSearchDashboardDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7201493740689525184L;

	private String referenceNumber;
	private String oldRegistrationNumber;
	private String dateOfCreation;

	/**
	 * @return the referenceNumber
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * @param referenceNumber
	 *            the referenceNumber to set
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @return the dateOfCreation
	 */
	public String getDateOfCreation() {
		return dateOfCreation;
	}

	/**
	 * @param dateOfCreation
	 *            the dateOfCreation to set
	 */
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	/**
	 * @param oldRegistrationNumber
	 *            the oldRegistrationNumber to set
	 */
	public void setOldRegistrationNumber(String oldRegistrationNumber) {
		this.oldRegistrationNumber = oldRegistrationNumber;
	}

	/**
	 * @return the oldRegistrationNumber
	 */
	public String getOldRegistrationNumber() {
		return oldRegistrationNumber;
	}

}
