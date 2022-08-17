package com.wipro.igrs.suppleDoc.form;

import java.util.HashMap;

import com.wipro.igrs.suppleDoc.dto.SuppleDocDTO;
import org.apache.struts.action.ActionForm;
public class SuppleDocForm extends ActionForm {
    private SuppleDocDTO suppleDocDTO=new SuppleDocDTO();
    private HashMap partyDetails = new HashMap();
    
    private String checkBoxDth;
    private String keysString;
    
    private String language;
    
    private HashMap pstampDetails = new HashMap();
    private String checkBoxPStamp;
    private String keysStringPStamp;
    
    private HashMap estampDetails = new HashMap();
    private String checkBoxEStamp;
    private String keysStringEStamp;
    
	public HashMap getPstampDetails() {
		return pstampDetails;
	}

	public void setPstampDetails(HashMap pstampDetails) {
		this.pstampDetails = pstampDetails;
	}

	public String getCheckBoxPStamp() {
		return checkBoxPStamp;
	}

	public void setCheckBoxPStamp(String checkBoxPStamp) {
		this.checkBoxPStamp = checkBoxPStamp;
	}

	public String getKeysStringPStamp() {
		return keysStringPStamp;
	}

	public void setKeysStringPStamp(String keysStringPStamp) {
		this.keysStringPStamp = keysStringPStamp;
	}

	public SuppleDocDTO getSuppleDocDTO() {
		return suppleDocDTO;
	}

	public void setSuppleDocDTO(SuppleDocDTO suppleDocDTO) {
		this.suppleDocDTO = suppleDocDTO;
	}
    
	
	public HashMap getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(HashMap partyDetails) {
		this.partyDetails = partyDetails;
	}

	public String getCheckBoxDth() {
		return checkBoxDth;
	}

	public void setCheckBoxDth(String checkBoxDth) {
		this.checkBoxDth = checkBoxDth;
	}

	public String getKeysString() {
		return keysString;
	}

	public void setKeysString(String keysString) {
		this.keysString = keysString;
	}

	public HashMap getEstampDetails() {
		return estampDetails;
	}

	public void setEstampDetails(HashMap estampDetails) {
		this.estampDetails = estampDetails;
	}

	public String getKeysStringEStamp() {
		return keysStringEStamp;
	}

	public void setKeysStringEStamp(String keysStringEStamp) {
		this.keysStringEStamp = keysStringEStamp;
	}

	public String getCheckBoxEStamp() {
		return checkBoxEStamp;
	}

	public void setCheckBoxEStamp(String checkBoxEStamp) {
		this.checkBoxEStamp = checkBoxEStamp;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}
}
