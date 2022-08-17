package com.wipro.igrs.caveatsMaster.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CaveatEditForm extends org.apache.struts.action.ActionForm {

	private String caveatsName;
	private String caveatsDesc;
	private String id;
	private String caveatsStatus;
	private boolean flag;

	public String getCaveatsStatus() {
		return caveatsStatus;
	}

	public void setCaveatsStatus(String caveatsStatus) {
		this.caveatsStatus = caveatsStatus;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCaveatsName() {
		return caveatsName;
	}

	public void setCaveatsName(String caveatsName) {
		this.caveatsName = caveatsName;
	}

	public String getCaveatsDesc() {
		return caveatsDesc;
	}

	public void setCaveatsDesc(String caveatsDesc) {
		this.caveatsDesc = caveatsDesc;
	}

	public CaveatEditForm() {
		flag = false;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {

	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		return null;
	}

}