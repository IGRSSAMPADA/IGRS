/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Egypt.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2007-08
 *==============================================================================
 *
 * File Name   :   EmailEventMappingForm.java
 * Author      :   Sayed Taha 
 * Description :   Represents the Email Event Department Action Form.
 * ----------------------------------------------------------------------------
 * Version         Modified By     Modified On           Modification
 *Created Date :   19 aug 2008
 */
package com.wipro.igrs.emaileventmapping.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class EmailEventMappingForm extends ActionForm{
	private String emailLookupTxnID;         
	private String toEmailUserID;
	private String fromEmailUserID;
	private String ccEmailUserID;
	private String emailLookupName;   
	private String [] selectedEventsForDeletion;
	private ArrayList emailEventName;
	private ArrayList allMailEvantMappings;
	private ArrayList emailUsers;
	
	public ArrayList getEmailUsers() {
		return emailUsers;
	}
	public void setEmailUsers(ArrayList emailUsers) {
		this.emailUsers = emailUsers;
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
	public String[] getSelectedEventsForDeletion() {
		return selectedEventsForDeletion;
	}
	public void setSelectedEventsForDeletion(String[] selectedEventsForDeletion) {
		this.selectedEventsForDeletion = selectedEventsForDeletion;
	}
	public ArrayList getEmailEventName() {
		return emailEventName;
	}
	public void setEmailEventName(ArrayList emailEventName) {
		this.emailEventName = emailEventName;
	}
	public String getEmailLookupName() {
		return emailLookupName;
	}
	public void setEmailLookupName(String emailLookupName) {
		this.emailLookupName = emailLookupName;
	}
	public String getEmailLookupTxnID() {
		return emailLookupTxnID;
	}
	public void setEmailLookupTxnID(String emailLookupTxnID) {
		this.emailLookupTxnID = emailLookupTxnID;
	}
	
}
