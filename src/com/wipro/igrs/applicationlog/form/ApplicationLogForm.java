package com.wipro.igrs.applicationlog.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.useracctmgmt.dto.UserProfileDTO;
import com.wipro.igrs.applicationlog.dto.ApplicationLogDTO;

public class ApplicationLogForm extends org.apache.struts.action.ActionForm {

	private String date;
	private ArrayList apploglist = new ArrayList();
	private String fromdate;
	private String todate;                        //added by satbir kumar
	private String actionName;
	private String language;
	private String logic;
	
	private ApplicationLogDTO appformdto= new ApplicationLogDTO();
	private String fwdName;
	
    
    public String getFwdName() {
		return fwdName;
	}

	public void setFwdName(String fwdName) {
		this.fwdName = fwdName;
	}

	public void reset(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        //throw new UnsupportedOperationException("Method is not implemented");
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest request) {
        // TODO: Write method body
        //throw new UnsupportedOperationException("Method is not implemented");
    	return null;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFromdate() {
		return fromdate;
	}

	public void setFromdate(String fromdate) {
		this.fromdate = fromdate;
	}

	public String getTodate() {
		return todate;
	}

	public void setTodate(String todate) {
		this.todate = todate;
	}

	public ArrayList getApploglist() {
		return apploglist;
	}

	public void setApploglist(ArrayList apploglist) {
		this.apploglist = apploglist;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getLogic() {
		return logic;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return language;
	}

	public void setAppformdto(ApplicationLogDTO appformdto) {
		this.appformdto = appformdto;
	}

	public ApplicationLogDTO getAppformdto() {
		return appformdto;
	}

	
	
	
}