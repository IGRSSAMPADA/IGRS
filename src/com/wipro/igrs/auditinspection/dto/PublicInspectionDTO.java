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

/**
 * @author root
 * 
 */
public class PublicInspectionDTO implements Serializable {
	private String pubInspectionId;

	private String pubInspOfficeName;

	private String pubInspOfficeAddress;

	private String pubInspDistName;

	private String pubInspDate;

	private String pubInspEntryDate;

	private String pubInspAuditFromDate;

	private String pubInspAuditToDate;

	private String pubInspAuditComments;

	private String pubInspAuditRepFileName;

	private String pubInspAudtiOtherFileName;

	private String pubInspDispatchDate;
	private String pubInspReportStatus;

	private ArrayList auditParaList;

	/**
	 * @return the pubInspectionId
	 */
	public String getPubInspectionId() {
		return pubInspectionId;
	}

	/**
	 * @param pubInspectionId
	 *            the pubInspectionId to set
	 */
	public void setPubInspectionId(String pubInspectionId) {
		this.pubInspectionId = pubInspectionId;
	}

	/**
	 * @return the pubInspOfficeName
	 */
	public String getPubInspOfficeName() {
		return pubInspOfficeName;
	}

	/**
	 * @param pubInspOfficeName
	 *            the pubInspOfficeName to set
	 */
	public void setPubInspOfficeName(String pubInspOfficeName) {
		this.pubInspOfficeName = pubInspOfficeName;
	}

	/**
	 * @return the pubInspOfficeAddress
	 */
	public String getPubInspOfficeAddress() {
		return pubInspOfficeAddress;
	}

	/**
	 * @param pubInspOfficeAddress
	 *            the pubInspOfficeAddress to set
	 */
	public void setPubInspOfficeAddress(String pubInspOfficeAddress) {
		this.pubInspOfficeAddress = pubInspOfficeAddress;
	}

	/**
	 * @return the pubInspDistName
	 */
	public String getPubInspDistName() {
		return pubInspDistName;
	}

	/**
	 * @param pubInspDistName
	 *            the pubInspDistName to set
	 */
	public void setPubInspDistName(String pubInspDistName) {
		this.pubInspDistName = pubInspDistName;
	}

	/**
	 * @return the pubInspDate
	 */
	public String getPubInspDate() {
		return pubInspDate;
	}

	/**
	 * @param pubInspDate
	 *            the pubInspDate to set
	 */
	public void setPubInspDate(String pubInspDate) {
		this.pubInspDate = pubInspDate;
	}

	/**
	 * @return the pubInspEntryDate
	 */
	public String getPubInspEntryDate() {
		return pubInspEntryDate;
	}

	/**
	 * @param pubInspEntryDate
	 *            the pubInspEntryDate to set
	 */
	public void setPubInspEntryDate(String pubInspEntryDate) {
		this.pubInspEntryDate = pubInspEntryDate;
	}

	/**
	 * @return the pubInspAuditFromDate
	 */
	public String getPubInspAuditFromDate() {
		return pubInspAuditFromDate;
	}

	/**
	 * @param pubInspAuditFromDate
	 *            the pubInspAuditFromDate to set
	 */
	public void setPubInspAuditFromDate(String pubInspAuditFromDate) {
		this.pubInspAuditFromDate = pubInspAuditFromDate;
	}

	/**
	 * @return the pubInspAuditToDate
	 */
	public String getPubInspAuditToDate() {
		return pubInspAuditToDate;
	}

	/**
	 * @param pubInspAuditToDate
	 *            the pubInspAuditToDate to set
	 */
	public void setPubInspAuditToDate(String pubInspAuditToDate) {
		this.pubInspAuditToDate = pubInspAuditToDate;
	}

	/**
	 * @return the pubInspAuditComments
	 */
	public String getPubInspAuditComments() {
		return pubInspAuditComments;
	}

	/**
	 * @param pubInspAuditComments
	 *            the pubInspAuditComments to set
	 */
	public void setPubInspAuditComments(String pubInspAuditComments) {
		this.pubInspAuditComments = pubInspAuditComments;
	}

	/**
	 * @return the pubInspAuditRepFileName
	 */
	public String getPubInspAuditRepFileName() {
		return pubInspAuditRepFileName;
	}

	/**
	 * @param pubInspAuditRepFileName
	 *            the pubInspAuditRepFileName to set
	 */
	public void setPubInspAuditRepFileName(String pubInspAuditRepFileName) {
		this.pubInspAuditRepFileName = pubInspAuditRepFileName;
	}

	/**
	 * @return the pubInspAudtiOtherFileName
	 */
	public String getPubInspAudtiOtherFileName() {
		return pubInspAudtiOtherFileName;
	}

	/**
	 * @param pubInspAudtiOtherFileName
	 *            the pubInspAudtiOtherFileName to set
	 */
	public void setPubInspAudtiOtherFileName(String pubInspAudtiOtherFileName) {
		this.pubInspAudtiOtherFileName = pubInspAudtiOtherFileName;
	}

	/**
	 * @return the auditParaList
	 */
	public ArrayList getAuditParaList() {
		return auditParaList;
	}

	/**
	 * @param auditParaList
	 *            the auditParaList to set
	 */
	public void setAuditParaList(ArrayList auditParaList) {
		this.auditParaList = auditParaList;
	}

	/**
	 * @return the pubInspDispatchDate
	 */
	public String getPubInspDispatchDate() {
		return pubInspDispatchDate;
	}

	/**
	 * @param pubInspDispatchDate
	 *            the pubInspDispatchDate to set
	 */
	public void setPubInspDispatchDate(String pubInspDispatchDate) {
		this.pubInspDispatchDate = pubInspDispatchDate;
	}

	/**
	 * @return the pubInspReportStatus
	 */
	public String getPubInspReportStatus() {
		return pubInspReportStatus;
	}

	/**
	 * @param pubInspReportStatus
	 *            the pubInspReportStatus to set
	 */
	public void setPubInspReportStatus(String pubInspReportStatus) {
		this.pubInspReportStatus = pubInspReportStatus;
	}

}
