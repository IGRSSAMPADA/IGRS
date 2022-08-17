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


package com.wipro.igrs.auditinspection.form;


import com.wipro.igrs.auditinspection.dto.SROReportDetailsDTO;
import com.wipro.igrs.baseaction.form.BaseForm;


/**
 * @author root May 29, 2008
 */
public class SROInspectionViewForm extends BaseForm {

	private String reportId;

	private String fromDate;

	private String toDate;

	private String sroId;
	
	private String formName;

	private SROReportDetailsDTO sroReport       =  new SROReportDetailsDTO();

	//added by shruti
	private String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the reportId
	 */
	// Added by Vinay 
	
	private String inspectionStatus;
	
	public String getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 *            the reportId to set
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the sroId
	 */
	public String getSroId() {
		return sroId;
	}

	/**
	 * @param sroId
	 *            the sroId to set
	 */
	public void setSroId(String sroId) {
		this.sroId = sroId;
	}

	/**
	 * @return the sroReport
	 */
	public SROReportDetailsDTO getSroReport() {
		return sroReport;
	}

	/**
	 * @param sroReport the sroReport to set
	 */
	public void setSroReport(SROReportDetailsDTO sroReport) {
		this.sroReport = sroReport;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

}
