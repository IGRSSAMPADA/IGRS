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

public class ExistingAuditBean implements Serializable{

	private String	auditTxnId;

	private String	audit_date;

	private String	dispatch_date;

	private String	comments;

	/**
	 * @return the auditTxnId
	 */
	public String getAuditTxnId() {
		return auditTxnId;
	}

	/**
	 * @param auditTxnId the auditTxnId to set
	 */
	public void setAuditTxnId(String auditTxnId) {
		this.auditTxnId = auditTxnId;
	}

	/**
	 * @return the audit_date
	 */
	public String getAudit_date() {
		return audit_date;
	}

	/**
	 * @param audit_date the audit_date to set
	 */
	public void setAudit_date(String audit_date) {
		this.audit_date = audit_date;
	}

	/**
	 * @return the dispatch_date
	 */
	public String getDispatch_date() {
		return dispatch_date;
	}

	/**
	 * @param dispatch_date the dispatch_date to set
	 */
	public void setDispatch_date(String dispatch_date) {
		this.dispatch_date = dispatch_date;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

}
