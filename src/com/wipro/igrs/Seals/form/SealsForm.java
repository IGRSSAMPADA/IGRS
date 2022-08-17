package com.wipro.igrs.Seals.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.Seals.dto.SealsDTO;

public class SealsForm extends ActionForm {

	private SealsDTO sealDTO = new SealsDTO();
	private ArrayList subSealList = new ArrayList(); 
	private ArrayList presentationPartyDetails = new ArrayList();
	private ArrayList witnessList = new ArrayList(); 
	private ArrayList consenterList = new ArrayList();
	private String sealsContent;
	private String presentParty;
	private String headerImage;
	private String radioChk;
	private String correction;
	public ArrayList getBookDetailsList() {
		return bookDetailsList;
	}

	public void setBookDetailsList(ArrayList bookDetailsList) {
		this.bookDetailsList = bookDetailsList;
	}

	private String langauge;
	private ArrayList bookDetailsList = new ArrayList();
	public String getLangauge() {
		return langauge;
	}

	public void setLangauge(String langauge) {
		this.langauge = langauge;
	}

	public SealsDTO getSealDTO() {
		return sealDTO;
	}

	public void setSealDTO(SealsDTO sealDTO) {
		this.sealDTO = sealDTO;
	}

	public ArrayList getSubSealList() {
		return subSealList;
	}

	public void setSubSealList(ArrayList subSealList) {
		this.subSealList = subSealList;
	}

	public ArrayList getPresentationPartyDetails() {
		return presentationPartyDetails;
	}

	public void setPresentationPartyDetails(ArrayList presentationPartyDetails) {
		this.presentationPartyDetails = presentationPartyDetails;
	}

	public ArrayList getWitnessList() {
		return witnessList;
	}

	public void setWitnessList(ArrayList witnessList) {
		this.witnessList = witnessList;
	}

	public ArrayList getConsenterList() {
		return consenterList;
	}

	public void setConsenterList(ArrayList consenterList) {
		this.consenterList = consenterList;
	}

	public String getSealsContent() {
		return sealsContent;
	}

	public void setSealsContent(String sealsContent) {
		this.sealsContent = sealsContent;
	}

	public String getPresentParty() {
		return presentParty;
	}

	public void setPresentParty(String presentParty) {
		this.presentParty = presentParty;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	public String getRadioChk() {
		return radioChk;
	}

	public void setRadioChk(String radioChk) {
		this.radioChk = radioChk;
	}
	
	private String backPage;


	public String getBackPage() {
		return backPage;
	}

	public void setBackPage(String backPage) {
		this.backPage = backPage;
	}

	public void setCorrection(String correction) {
		this.correction = correction;
	}

	public String getCorrection() {
		return correction;
	}
	
	
}
