package com.wipro.igrs.caveatsMaster.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CaveatAddForm extends org.apache.struts.action.ActionForm {

	private String caveatsName;
	private String caveatsDesc;
	private boolean flag;
	private String[] select;

	public String[] getSelect() {
		return select;
	}

	public void setSelect(String[] select) {
		this.select = select;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getCaveatsName() {
		return caveatsName;
	}

	public void setCaveatsName(String caveatsName) {
		this.caveatsName = caveatsName;
	}

	public CaveatAddForm() {
		flag = false;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {

	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		return null;
	}

	public String getCaveatsDesc() {
		return caveatsDesc;
	}

	public void setCaveatsDesc(String caveatsDesc) {
		this.caveatsDesc = caveatsDesc;
	}

}