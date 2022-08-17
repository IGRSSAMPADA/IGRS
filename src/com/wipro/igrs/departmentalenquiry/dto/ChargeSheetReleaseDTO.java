/**
 * ChanrgeSheetReleaseDTO.java
 */

package com.wipro.igrs.departmentalenquiry.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author admin
 * 
 */
public class ChargeSheetReleaseDTO implements Serializable {
	private String strComplaintId;

	private String strCharge;

	private String strDateOfIssue;

	private String strIssuingAuthority;

	private String strPlaceOfIssue;

	private String strDetails;

	private String strBasisOfCharges;

	private String strDocumentId;

	private ArrayList alWitnessList;

	/**
	 * @return the strComplaintId
	 */
	public String getStrComplaintId() {
		return strComplaintId;
	}

	/**
	 * @param strComplaintId
	 *            the strComplaintId to set
	 */
	public void setStrComplaintId(String strComplaintId) {
		this.strComplaintId = strComplaintId;
	}

	/**
	 * @return the strCharge
	 */
	public String getStrCharge() {
		return strCharge;
	}

	/**
	 * @param strCharge
	 *            the strCharge to set
	 */
	public void setStrCharge(String strCharge) {
		this.strCharge = strCharge;
	}

	/**
	 * @return the strDateOfIssue
	 */
	public String getStrDateOfIssue() {
		return strDateOfIssue;
	}

	/**
	 * @param strDateOfIssue
	 *            the strDateOfIssue to set
	 */
	public void setStrDateOfIssue(String strDateOfIssue) {
		this.strDateOfIssue = strDateOfIssue;
	}

	/**
	 * @return the strIssuingAuthority
	 */
	public String getStrIssuingAuthority() {
		return strIssuingAuthority;
	}

	/**
	 * @param strIssuingAuthority
	 *            the strIssuingAuthority to set
	 */
	public void setStrIssuingAuthority(String strIssuingAuthority) {
		this.strIssuingAuthority = strIssuingAuthority;
	}

	/**
	 * @return the strPlaceOfIssue
	 */
	public String getStrPlaceOfIssue() {
		return strPlaceOfIssue;
	}

	/**
	 * @param strPlaceOfIssue
	 *            the strPlaceOfIssue to set
	 */
	public void setStrPlaceOfIssue(String strPlaceOfIssue) {
		this.strPlaceOfIssue = strPlaceOfIssue;
	}

	/**
	 * @return the strDetails
	 */
	public String getStrDetails() {
		return strDetails;
	}

	/**
	 * @param strDetails
	 *            the strDetails to set
	 */
	public void setStrDetails(String strDetails) {
		this.strDetails = strDetails;
	}

	/**
	 * @return the strBasisOfCharges
	 */
	public String getStrBasisOfCharges() {
		return strBasisOfCharges;
	}

	/**
	 * @param strBasisOfCharges
	 *            the strBasisOfCharges to set
	 */
	public void setStrBasisOfCharges(String strBasisOfCharges) {
		this.strBasisOfCharges = strBasisOfCharges;
	}

	/**
	 * @return the alWitnessList
	 */
	public ArrayList getAlWitnessList() {
		return alWitnessList;
	}

	/**
	 * @param alWitnessList
	 *            the alWitnessList to set
	 */
	public void setAlWitnessList(ArrayList alWitnessList) {
		this.alWitnessList = alWitnessList;
	}

	/**
	 * @return the strDocumentId
	 */
	public String getStrDocumentId() {
		return strDocumentId;
	}

	/**
	 * @param strDocumentId
	 *            the strDocumentId to set
	 */
	public void setStrDocumentId(String strDocumentId) {
		this.strDocumentId = strDocumentId;
	}

}
