package com.wipro.igrs.report.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.report.dto.MISReportDTO;
import com.wipro.igrs.report.dto.PendingCourtCasesDTO;

public class MISReportForm extends ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PendingCourtCasesDTO penDTO = new PendingCourtCasesDTO();
	MISReportDTO reportDTO = new MISReportDTO();
	private String radioSearch;
	private String regDocSearch;
	private String genderSearch;
	private String fromDate;
	private String toDate;
	private String month;
	private String month1;
	private String month2;
	private String userType;
	private String actionName;
	private String areaType;
	private String formName;
	private String jrxmlName;
	private String reportName;
	private String offcMand;
	private String tehsilName;
    private String districtID;
    private String districtName;
	private String state; // 
	private String district;//
	private String zone; // \\ added by Siddhartha
	private String tehsil; //
	private String user;//
	private String viewType;//
	private String tehsilId;
	private String durFrom;//
	private String durTo; // 
	private ArrayList yearList = new ArrayList();
	private ArrayList finYearList = new ArrayList();
	private String check;
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public ArrayList getFinYearList() {
		return finYearList;
	}

	public void setFinYearList(ArrayList finYearList) {
		this.finYearList = finYearList;
	}

	public ArrayList getYearList() {
		return yearList;
	}

	public void setYearList(ArrayList yearList) {
		this.yearList = yearList;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getYear1() {
		return year1;
	}

	public void setYear1(String year1) {
		this.year1 = year1;
	}

	public String getYear2() {
		return year2;
	}

	public void setYear2(String year2) {
		this.year2 = year2;
	}

	private int offcId;//
	//private String districtId;
	private String officeSROName;
	private String year1;
	private String year2;
	
	
	
	public String getOfficeSROName() {
		return officeSROName;
	}

	public void setOfficeSROName(String officeSROName) {
		this.officeSROName = officeSROName;
	}

	private String municipalCheckDisplay;
	public String getMunicipalCheckDisplay() {
		return municipalCheckDisplay;
	}

	public void setMunicipalCheckDisplay(String municipalCheckDisplay) {
		this.municipalCheckDisplay = municipalCheckDisplay;
	}

	public String getMunicipalFlag() {
		return municipalFlag;
	}

	public void setMunicipalFlag(String municipalFlag) {
		this.municipalFlag = municipalFlag;
	}

	private String municipalFlag;

	private String districtId;
	private String zoneId;

	private String mohallaId;
	private String wardId;
	private String wardPatwariId;
	private String areaTypeId;
	private String[] selectedClauses;
	private ArrayList clauseList;
	private String deedId;
	private ArrayList deedList;
	private String rangeId;
	private ArrayList rangeList;
	private String language;
	private ArrayList PropertyList;
	private String propertyId;
	private String question;
	private String subAreaId;
	private String zoneName;
	private String clauseId;
	private String officeidIns;
	private String officeNameIns;
	
	private String deedID;
	private String instID;
	private ArrayList instList;
	
	private String propertyID;
	
	private String propertySubID;
	private String propertySubName;
	public ArrayList getPropertySubList() {
		return propertySubList;
	}

	public void setPropertySubList(ArrayList propertySubList) {
		this.propertySubList = propertySubList;
	}

	private ArrayList propertySubList;
	
	
	
	
	
	
	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	private String propertyName;
	
	
	
	
	
	
	
	public ArrayList getInstList() {
		return instList;
	}

	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}

	public String getInstID() {
		return instID;
	}

	public void setInstID(String instID) {
		this.instID = instID;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	private String instName;
	
	
	
	
	public String getDeedID() {
		return deedID;
	}

	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}

	public String getDeedName() {
		return deedName;
	}

	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}

	private String deedName;
	
	
	
	public String getOfficeidIns() {
		return officeidIns;
	}

	public void setOfficeidIns(String officeidIns) {
		this.officeidIns = officeidIns;
	}

	public String getOfficeNameIns() {
		return officeNameIns;
	}

	public void setOfficeNameIns(String officeNameIns) {
		this.officeNameIns = officeNameIns;
	}

	private ArrayList srList;
	public ArrayList getSrList() {
		return srList;
	}

	public void setSrList(ArrayList srList) {
		this.srList = srList;
	}

	
	private String srName;
	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	public String getSrId() {
		return srId;
	}

	public void setSrId(String srId) {
		this.srId = srId;
	}

	private String srId;
	
	
	
	

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	private ArrayList sroOfficeList ;
	
	public ArrayList getSroOfficeList() {
		return sroOfficeList;
	}

	public void setSroOfficeList(ArrayList sroOfficeList) {
		this.sroOfficeList = sroOfficeList;
	}

	private String officeId;
	private String officeName;
	
	
	private ArrayList wardPatwariList;
	public ArrayList getWardPatwariList() {
		return wardPatwariList;
	}

	public void setWardPatwariList(ArrayList wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}

	//private String areaTypeId;
	private String areaTypeName;
	//private ArrayList areaTypeList;

	private String propertyL2Id;
	
	private ArrayList propertyL2List;

   private ArrayList subAreaList;
   
   private String districtZoneId;
   private String districtZoneName; 
   
   
   private String ColonyId;
   public String getColonyId() {
	return ColonyId;
}

public void setColonyId(String colonyId) {
	ColonyId = colonyId;
}

public String getWardIds() {
	return WardIds;
}

public void setWardIds(String wardIds) {
	WardIds = wardIds;
}

private String WardIds;
	
	
	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	
	public String getSubAreaId() {
		return subAreaId;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public String getPropertyL2Id() {
		return propertyL2Id;
	}

	public void setPropertyL2Id(String propertyL2Id) {
		this.propertyL2Id = propertyL2Id;
	}

	
	public ArrayList getPropertyList() {
		return PropertyList;
	}

	public void setPropertyList(ArrayList propertyList) {
		PropertyList = propertyList;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList getRangeList() {
		return rangeList;
	}

	public void setRangeList(ArrayList rangeList) {
		this.rangeList = rangeList;
	}

	public String getRangeId() {
		return rangeId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}

	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public ArrayList getClauseList() {
		return clauseList;
	}

	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardPatwariId() {
		return wardPatwariId;
	}

	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}

	public String getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	private ArrayList zoneList = new ArrayList();

	
	
	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public int getOffcId() {
		return offcId;
	}

	public ArrayList getZoneList() {
		return zoneList;
	}

	public void setZoneList(ArrayList zoneList) {
		this.zoneList = zoneList;
	}

	public void setOffcId(int officeId) {
		this.offcId = officeId;
	}

	public String getDurTo() {
		return durTo;
	}

	public void setDurTo(String durTo) {
		this.durTo = durTo;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getOffcMand() {
		return offcMand;
	}

	public void setOffcMand(String offcMand) {
		this.offcMand = offcMand;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getJrxmlName() {
		return jrxmlName;
	}

	public void setJrxmlName(String jrxmlName) {
		this.jrxmlName = jrxmlName;
	}


	private ArrayList usageReportList = new ArrayList();

	private ArrayList misReportList = new ArrayList();

	private ArrayList sroURReportList = new ArrayList();

	private ArrayList monthlyRevenueList = new ArrayList();

	private ArrayList cessMunicipalList = new ArrayList();

	private ArrayList mohallaList = new ArrayList();

	/** the value of districtList association */
	private ArrayList districtList = new ArrayList();

	private ArrayList districtListZone = new ArrayList();
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

	private String financialYear;

	/**
	 * @return the penDTO
	 */
	public PendingCourtCasesDTO getPenDTO() {
		return penDTO;
	}

	/**
	 * @param penDTO
	 *            the penDTO to set
	 */
	public void setPenDTO(PendingCourtCasesDTO penDTO) {
		this.penDTO = penDTO;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
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
		return actionName;
	}

	public void setActionID(String actionName) {
		this.actionName = actionName;
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

	public MISReportDTO getReportDTO() {
		return reportDTO;
	}

	public void setReportDTO(MISReportDTO reportDTO) {
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
	 * @param regUserIdList
	 *            the regUserIdList to set
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
	 * @param fiscalYearList
	 *            the fiscalYearList to set
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
	 * @param droList
	 *            the droList to set
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
	 * @param regDocSearch
	 *            the regDocSearch to set
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

	public ArrayList getMisReportList() {
		return misReportList;
	}

	public void setMisReportList(ArrayList misReportList) {
		this.misReportList = misReportList;
	}

	public ArrayList getSroURReportList() {
		return sroURReportList;
	}

	public void setSroURReportList(ArrayList sroURReportList) {
		this.sroURReportList = sroURReportList;
	}

	public ArrayList getMonthlyRevenueList() {
		return monthlyRevenueList;
	}

	public void setMonthlyRevenueList(ArrayList monthlyRevenueList) {
		this.monthlyRevenueList = monthlyRevenueList;
	}

	public ArrayList getCessMunicipalList() {
		return cessMunicipalList;
	}

	public void setCessMunicipalList(ArrayList cessMunicipalList) {
		this.cessMunicipalList = cessMunicipalList;
	}

	public void setUsageReportList(ArrayList usageReportList) {
		this.usageReportList = usageReportList;
	}

	public ArrayList getUsageReportList() {
		return usageReportList;
	}

	public void setDurFrom(String durFrom) {
		this.durFrom = durFrom;
	}

	public String getDurFrom() {
		return durFrom;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setPropertyL2List(ArrayList propertyL2List) {
		this.propertyL2List = propertyL2List;
	}

	public ArrayList getPropertyL2List() {
		return propertyL2List;
	}

	public void setSelectedClauses(String[] select) {
		this.selectedClauses = select;
	}

	public String[] getSelectedClauses() {
		return selectedClauses;
	}

	public void setSubAreaList(ArrayList subAreaList) {
		this.subAreaList = subAreaList;
	}

	public ArrayList getSubAreaList() {
		return subAreaList;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setClauseId(String clauseId) {
		this.clauseId = clauseId;
	}

	public String getClauseId() {
		return clauseId;
	}

	public void setDistrictZoneName(String districtZoneName) {
		this.districtZoneName = districtZoneName;
	}

	public String getDistrictZoneName() {
		return districtZoneName;
	}

	public void setDistrictZoneId(String districtZoneId) {
		this.districtZoneId = districtZoneId;
	}

	public String getDistrictZoneId() {
		return districtZoneId;
	}

	public void setDistrictListZone(ArrayList districtListZone) {
		this.districtListZone = districtListZone;
	}

	public ArrayList getDistrictListZone() {
		return districtListZone;
	}

	public void setPropertySubName(String propertySubName) {
		this.propertySubName = propertySubName;
	}

	public String getPropertySubName() {
		return propertySubName;
	}

	public void setPropertySubID(String propertySubID) {
		this.propertySubID = propertySubID;
	}

	public String getPropertySubID() {
		return propertySubID;
	}

	
}
