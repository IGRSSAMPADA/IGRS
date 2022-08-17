/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 
 * ===========================================================================
 * File           :   EmailEventDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Sayed Taha
 * Created Date   :   Aug 19, 2008

 * ===========================================================================
 */
package com.wipro.igrs.emaileventmapping.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class EmailEventMappingDTO implements Serializable{
	private String emailUserLookupDtlsID;
	private String emailLookupTxnID;         
	private String toEmailUserID;
	private String fromEmailUserID;
	private String ccEmailUserID;
	private String emailLookupName;        
	private ArrayList allMailEvantMappings;
	private Date creationDate;
	public String getEmailLookupTxnID() {
		return emailLookupTxnID;
	}
	public void setEmailLookupTxnID(String emailLookupTxnID) {
		this.emailLookupTxnID = emailLookupTxnID;
	}
	public String getToEmailUserID() {
		return toEmailUserID;
	}
	public void setToEmailUserID(String toEmailUserID) {
		this.toEmailUserID = toEmailUserID;
	}
	public String getFromEmailUserID() {
		return fromEmailUserID;
	}
	public void setFromEmailUserID(String fromEmailUserID) {
		this.fromEmailUserID = fromEmailUserID;
	}
	public String getCcEmailUserID() {
		return ccEmailUserID;
	}
	public void setCcEmailUserID(String ccEmailUserID) {
		this.ccEmailUserID = ccEmailUserID;
	}
	public ArrayList getAllMailEvantMappings() {
		return allMailEvantMappings;
	}
	public void setAllMailEvantMappings(ArrayList allMailEvantMappings) {
		this.allMailEvantMappings = allMailEvantMappings;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getEmailUserLookupDtlsID() {
		return emailUserLookupDtlsID;
	}
	public void setEmailUserLookupDtlsID(String emailUserLookupDtlsID) {
		this.emailUserLookupDtlsID = emailUserLookupDtlsID;
	}
	public String getEmailLookupName() {
		return emailLookupName;
	}
	public void setEmailLookupName(String emailLookupName) {
		this.emailLookupName = emailLookupName;
	}
}
