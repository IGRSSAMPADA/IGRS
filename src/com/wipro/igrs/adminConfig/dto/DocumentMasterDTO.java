/**
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright   2007-08
 *==============================================================================
 * File Name	:	DocumentMasterDTO.java
 * Author		:	vengamamba
 * Date			: 	18/06/2008
 */

package com.wipro.igrs.adminConfig.dto;

import java.util.ArrayList;

public class DocumentMasterDTO {
	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	private String documentName;
	private String documentDesc;
	private String documentStatus;
	private String documentId;

	private ArrayList documentList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentDesc() {
		return documentDesc;
	}

	public void setDocumentDesc(String documentDesc) {
		this.documentDesc = documentDesc;
	}

	public String getDocumentStatus() {
		return documentStatus;
	}

	public void setDocumentStatus(String documentStatus) {
		this.documentStatus = documentStatus;
	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public ArrayList getDocumentList() {
		return documentList;
	}

	public void setDocumentList(ArrayList documentList) {
		this.documentList = documentList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
