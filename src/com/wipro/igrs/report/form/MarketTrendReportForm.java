package com.wipro.igrs.report.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.report.dto.MarketTrendReportDTO;

public class MarketTrendReportForm extends ActionForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MarketTrendReportDTO reportDTO = new MarketTrendReportDTO();
	
	private String actionName;
	private String radioSearch ;
	private String regDocSearch ;
	private String genderSearch ;
	private String fromDate;
	private String toDate;
	private String month;
	private String userType ;
	private String actionID;
	private String areaType;
	private String formName; 
	
	
	


	private ArrayList marketTrendList = new ArrayList();
	
	private ArrayList mohallaList = new ArrayList();
	
	/** the value of Book Type Id association */
	private ArrayList bookTypeList = new ArrayList();
	
	/** the value of districtList association */
    private ArrayList districtList = new ArrayList(); 
    
    /** the value of officeTypeLst association */
    private ArrayList officeTypeLst = new ArrayList();
    
    /** the value of officeNameList association */
    private ArrayList officeNameList = new ArrayList();
    
    /** the value of tehsilList association */
    private ArrayList tehsilList = new ArrayList();
    
    /** the value of areaTypeList association */
    private ArrayList areaTypeList = new ArrayList();
    
    /** the value of wardList association */
    private ArrayList wardList = new ArrayList();
    
    /** the value of patwariList association */
    private ArrayList patwariList = new ArrayList();

    /** the value of factorList association */
    private ArrayList factorList = new ArrayList();
    
    /** the value of reportList association */
    private ArrayList reportList = new ArrayList();
    
    /** the value of regUserIdList association */
    private ArrayList regUserIdList = new ArrayList();
    
    /** the value of fiscalYearList association */
    private ArrayList fiscalYearList = new ArrayList();
    
    /** the value of droList association */
    private ArrayList droList = new ArrayList();
    
    
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset( mapping, request);
    }

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getOfficeTypeLst() {
		return officeTypeLst;
	}

	public void setOfficeTypeLst(ArrayList officeTypeLst) {
		this.officeTypeLst = officeTypeLst;
	}

	public ArrayList getOfficeNameList() {
		return officeNameList;
	}

	public void setOfficeNameList(ArrayList officeNameList) {
		this.officeNameList = officeNameList;
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

	public MarketTrendReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(MarketTrendReportDTO reportDTO) {
		this.reportDTO = reportDTO;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public ArrayList getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}

	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}

	public ArrayList getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}

	public ArrayList getPatwariList() {
		return patwariList;
	}

	public void setPatwariList(ArrayList patwariList) {
		this.patwariList = patwariList;
	}

	public String getGenderSearch() {
		return genderSearch;
	}

	public void setGenderSearch(String genderSearch) {
		this.genderSearch = genderSearch;
	}

	public ArrayList getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}

	public ArrayList getFactorList() {
		return factorList;
	}

	public void setFactorList(ArrayList factorList) {
		this.factorList = factorList;
	}

	/**
	 * @return the regUserIdList
	 */
	public ArrayList getRegUserIdList() {
		return regUserIdList;
	}

	/**
	 * @param regUserIdList the regUserIdList to set
	 */
	public void setRegUserIdList(ArrayList regUserIdList) {
		this.regUserIdList = regUserIdList;
	}

	/**
	 * @return the fiscalYearList
	 */
	public ArrayList getFiscalYearList() {
		return fiscalYearList;
	}

	/**
	 * @param fiscalYearList the fiscalYearList to set
	 */
	public void setFiscalYearList(ArrayList fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
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
	 * @return the regDocSearch
	 */
	public String getRegDocSearch() {
		return regDocSearch;
	}

	/**
	 * @param regDocSearch the regDocSearch to set
	 */
	public void setRegDocSearch(String regDocSearch) {
		this.regDocSearch = regDocSearch;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public ArrayList getMohallaList() {
		return mohallaList;
	}

	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	public ArrayList getMarketTrendList() {
		return marketTrendList;
	}

	public void setMarketTrendList(ArrayList marketTrendList) {
		this.marketTrendList = marketTrendList;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public ArrayList getBookTypeList() {
		return bookTypeList;
	}

	public void setBookTypeList(ArrayList bookTypeList) {
		this.bookTypeList = bookTypeList;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
