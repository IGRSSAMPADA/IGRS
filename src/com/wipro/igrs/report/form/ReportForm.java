package com.wipro.igrs.report.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.report.dto.PendingCourtCasesDTO;
import com.wipro.igrs.report.dto.ReportDTO;

public class ReportForm extends ActionForm{
	/**
	 * 
	 */
	
private ArrayList arrYear;
private ArrayList arrMon;
private ArrayList arrAchivement;

private int updateFlag;
public int getUpdateFlag() {
	return updateFlag;
}
public void setUpdateFlag(int updateFlag) {
	this.updateFlag = updateFlag;
}
public int getUpdateFlagForView() {
	return updateFlagForView;
}
public void setUpdateFlagForView(int updateFlagForView) {
	this.updateFlagForView = updateFlagForView;
}

private int updateFlagForView;

public ArrayList getArrAchivement() {
	return arrAchivement;
}
public void setArrAchivement(ArrayList arrAchivement) {
	this.arrAchivement = arrAchivement;
}

	
	public ArrayList getArrMon() {
	return arrMon;
}
public void setArrMon(ArrayList arrMon) {
	this.arrMon = arrMon;
}
	public ArrayList getArrYear() {
		return arrYear;
	}
	public void setArrYear(ArrayList arrYear) {
		this.arrYear = arrYear;
	}
	
	private static final long serialVersionUID = 1L;
	public PendingCourtCasesDTO getPenDTO() {
		return penDTO;
	}
	public void setPenDTO(PendingCourtCasesDTO penDTO) {
		this.penDTO = penDTO;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	PendingCourtCasesDTO penDTO = new PendingCourtCasesDTO();
	ReportDTO reportDTO = new ReportDTO();
	private String totSum;
	public String getTotSum() {
		return totSum;
	}
	public void setTotSum(String totSum) {
		this.totSum = totSum;
	}
	private String radioSearch ;
	private String fromDate;
	private String toDate;
	private String month;
	private String userType ;
	private String actionName;
	private String formName;
	private String financialYear;
	
	
	
	
	//for create target
	private String financialFromYear;
	private String financialToYear;
	
	
	
	
	public String getFinancialFromYear() {
		return financialFromYear;
	}
	public void setFinancialFromYear(String financialFromYear) {
		this.financialFromYear = financialFromYear;
	}
	public String getFinancialToYear() {
		return financialToYear;
	}
	public void setFinancialToYear(String financialToYear) {
		this.financialToYear = financialToYear;
	}
	public String getFinancialYear() {
		return financialYear;
	}
	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}
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
    
    /** the value of droList association */
    private ArrayList sroList = new ArrayList();
    
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


	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
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

	public ReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(ReportDTO reportDTO) {
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
	 * @return the sroList
	 */
	public ArrayList getSroList() {
		return sroList;
	}

	/**
	 * @param sroList the sroList to set
	 */
	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

}
