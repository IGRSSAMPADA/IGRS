package com.wipro.igrs.report.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.report.dto.ReportingDTO;

public class ReportingForm extends ActionForm{
	ReportingDTO reportDTO = new ReportingDTO();
	private String radioSearch ;
	
	
	private String fromDate;
	private String toDate;
	private String month;
	private String userType ;
	private String actionID;
	private String actionForm;
	//variables related to sro ,dro list
	private	String sroID; 
	private	String droID;
	private String districtID;
   
	/** the value of reportList association */
    private ArrayList reportList = new ArrayList();
    /** the value of fiscalYearList association */
    private ArrayList fiscalYearList = new ArrayList();
    /** the value of fiscalYearList association */
    private ArrayList districtList = new ArrayList();
    
    /** the value of droList association */
    private ArrayList droList = new ArrayList();
    
    private ArrayList sroList=new ArrayList();
    
    
    
    
    
    public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(ArrayList fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	public ArrayList getSroList() {
		return sroList;
	}

	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
    	
    	
        super.reset( mapping, request);
    }

	
	

	

	public String getActionID() {
		return actionID;
	}

	public void setActionID(String actionID) {
		this.actionID = actionID;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
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

	public String getRadioSearch() {
		return radioSearch;
	}

	public void setRadioSearch(String radioSearch) {
		this.radioSearch = radioSearch;
	}

	public ReportingDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportingDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	

	

	public ArrayList getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}

	

	

	
	/**
	 * @return the droList
	 */
	public ArrayList getDroList() {
		return droList;
	}

	/**
	 * @param droList the droList to set
	 */
	public void setDroList(ArrayList droList) {
		this.droList = droList;
	}

	/**
	 * @return the sroID
	 */
	public String getSroID() {
		return sroID;
	}

	/**
	 * @param sroID the sroID to set
	 */
	public void setSroID(String sroID) {
		this.sroID = sroID;
	}

	/**
	 * @return the droID
	 */
	public String getDroID() {
		return droID;
	}

	/**
	 * @param droID the droID to set
	 */
	public void setDroID(String droID) {
		this.droID = droID;
	}

	/**
	 * @return the actionForm
	 */
	public String getActionForm() {
		return actionForm;
	}

	/**
	 * @param actionForm the actionForm to set
	 */
	public void setActionForm(String actionForm) {
		this.actionForm = actionForm;
	}

	/**
	 * @return the districtID
	 */
	public String getDistrictID() {
		return districtID;
	}

	/**
	 * @param districtID the districtID to set
	 */
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	

}
