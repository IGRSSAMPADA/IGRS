
/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: RegistrationCompletionDTO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th June 2008
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE DTO FOR REGISTRATION COMPLETION ACTION.
 */

package com.wipro.igrs.RegistrationCompletion.dto;

/**
 * @author NIHAR M.
 *
 */
public class RegistrationCompletionDTO {

	private String regTxnId;
	private String fromDate;
	private String toDate;
	private String status;
	private String date;
	
	private String updateStatus;
	private String statusId;
	private String txnRemark;
	private String checkStatus;
	private String expectedDateOfDelivery;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getRegTxnId() {
		return regTxnId;
	}
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}
	public String getUpdateStatus() {
		return updateStatus;
	}
	public void setUpdateStatus(String updateStatus) {
		this.updateStatus = updateStatus;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getTxnRemark() {
		return txnRemark;
	}
	public void setTxnRemark(String txnRemark) {
		this.txnRemark = txnRemark;
	}
	/**
	 * @return the checkStatus
	 */
	/**
	 * @return the checkStatus
	 */
	public String getCheckStatus()
	{
		return checkStatus;
	}
	/**
	 * @param checkStatus the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus)
	{
		this.checkStatus = checkStatus;
	}
	/**
	 * @return the expectedDateOfDelivery
	 */
	public String getExpectedDateOfDelivery()
	{
		return expectedDateOfDelivery;
	}
	/**
	 * @param expectedDateOfDelivery the expectedDateOfDelivery to set
	 */
	public void setExpectedDateOfDelivery(String expectedDateOfDelivery)
	{
		this.expectedDateOfDelivery = expectedDateOfDelivery;
	}
	
}
