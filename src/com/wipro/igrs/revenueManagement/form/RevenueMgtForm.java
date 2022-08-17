package com.wipro.igrs.revenueManagement.form;

import org.apache.struts.action.ActionForm;
import com.wipro.igrs.revenueManagement.dto.RevenueMgtDTO;

public class RevenueMgtForm extends ActionForm{
	
	private static final long serialVersionUID = 1L;
	RevenueMgtDTO revdto=new RevenueMgtDTO();
	private String uid;
	private String offid;
	private String formName;
	private String actionName;
    private String fromDate;
    private String toDate;
    private String userDate;
    private String month;
    private String userType;
    private String radiodate = "date";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public RevenueMgtDTO getRevdto() {
		return revdto;
	}
	public void setRevdto(RevenueMgtDTO revdto) {
		this.revdto = revdto;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getOffid() {
		return offid;
	}
	public void setOffid(String offid) {
		this.offid = offid;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRadiodate() {
		return radiodate;
	}
	public void setRadiodate(String radiodate) {
		this.radiodate = radiodate;
	}

	
	
	
	
	
	
}
