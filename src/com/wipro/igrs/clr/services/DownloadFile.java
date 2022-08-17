package com.wipro.igrs.clr.services;

import org.apache.struts.action.ActionForm;

public class DownloadFile extends ActionForm{
	private String documentNumber;
	private String filePath;
	//private string 
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
