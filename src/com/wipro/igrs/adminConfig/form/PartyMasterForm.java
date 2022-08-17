package com.wipro.igrs.adminConfig.form;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.adminConfig.dto.PartyMasterDTO;

public class PartyMasterForm extends ActionForm {
	private static final long serialVersionUID = 1L;

	private String PartyPageName;
	private String actionValue;

	private String partyName;
	private String partyDesc;
	private String partyStatus;

	private PartyMasterDTO partydto;

	public String getPartyPageName() {
		return PartyPageName;
	}

	public void setPartyPageName(String partyPageName) {
		PartyPageName = partyPageName;
	}

	public String getActionValue() {
		return actionValue;
	}

	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyDesc() {
		return partyDesc;
	}

	public void setPartyDesc(String partyDesc) {
		this.partyDesc = partyDesc;
	}

	public String getPartyStatus() {
		return partyStatus;
	}

	public void setPartyStatus(String partyStatus) {
		this.partyStatus = partyStatus;
	}

	public PartyMasterDTO getPartydto() {
		return partydto;
	}

	public void setPartydto(PartyMasterDTO partydto) {
		this.partydto = partydto;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
