package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;

public class MapDTO implements Serializable {

	private String partyName;
	/**
	 * @author Rishab
	 */
	private String partyValue;
	
	private String partyID;
	
	private String partyLabel;
	
	private String partyValuationId;
	
	
	
	
	public String getPartyLabel() {
		return partyLabel;
	}
	public void setPartyLabel(String partyLabel) {
		this.partyLabel = partyLabel;
	}
	public String getPartyID() {
		return partyID;
	}
	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	/**
	 * @return the partyValue
	 */
	public String getPartyValue() {
		return partyValue;
	}
	/**
	 * @param partyValue the partyValue to set
	 */
	public void setPartyValue(String partyValue) {
		this.partyValue = partyValue;
	}
	public String getPartyValuationId() {
		return partyValuationId;
	}
	public void setPartyValuationId(String partyValuationId) {
		this.partyValuationId = partyValuationId;
	}


	
	
		
	
}
