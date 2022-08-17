package com.wipro.igrs.newreginit.dto;

import java.io.Serializable;
import java.util.ArrayList;

import com.wipro.igrs.newdutycalculation.dto.UserEnterableDTO;

public class DeclarationDTO implements Serializable{
	private String declarationTxnId;
	private String declarationId;
	private String declarationSubId;
	private String declarationNameEnglish;
	private String declarationNameHindi;
	private String userEntrableFlag;
	private ArrayList<FormUserEnterableDTO> userEnterableData;

	public String getDeclarationTxnId() {
		return declarationTxnId;
	}

	public void setDeclarationTxnId(String declarationTxnId) {
		this.declarationTxnId = declarationTxnId;
	}

	public String getDeclarationId() {
		return declarationId;
	}

	public void setDeclarationId(String declarationId) {
		this.declarationId = declarationId;
	}

	public String getDeclarationSubId() {
		return declarationSubId;
	}

	public void setDeclarationSubId(String declarationSubId) {
		this.declarationSubId = declarationSubId;
	}

	public String getDeclarationNameEnglish() {
		return declarationNameEnglish;
	}

	public void setDeclarationNameEnglish(String declarationNameEnglish) {
		this.declarationNameEnglish = declarationNameEnglish;
	}

	public String getDeclarationNameHindi() {
		return declarationNameHindi;
	}

	public void setDeclarationNameHindi(String declarationNameHindi) {
		this.declarationNameHindi = declarationNameHindi;
	}

	public String getUserEntrableFlag() {
		return userEntrableFlag;
	}

	public void setUserEntrableFlag(String userEntrableFlag) {
		this.userEntrableFlag = userEntrableFlag;
	}

	public ArrayList<FormUserEnterableDTO> getUserEnterableData() {
		return userEnterableData;
	}

	public void setUserEnterableData(ArrayList<FormUserEnterableDTO> userEnterableData) {
		this.userEnterableData = userEnterableData;
	}
	
	
}
