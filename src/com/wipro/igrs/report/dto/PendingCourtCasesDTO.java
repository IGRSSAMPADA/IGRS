package com.wipro.igrs.report.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PendingCourtCasesDTO implements Serializable{
	private ArrayList reportList = new ArrayList();
	private String fromDate;
	private String toDate;
	private String petitionNumber;
	private String petitionerName;
	private String districtId;
	private String districtName;
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	private String petitionDate;
	private String courtName;
	private String officerIncharge;
	private String subjectMatter;
	private String stayOrder;
	private String remarks;
	
	private String value;
	private String name;
	
	
	//For Cess Municipal
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNoOfDocRegMonth() {
		return noOfDocRegMonth;
	}
	public void setNoOfDocRegMonth(String noOfDocRegMonth) {
		this.noOfDocRegMonth = noOfDocRegMonth;
	}
	public String getNoOfDocRegDuration() {
		return noOfDocRegDuration;
	}
	public void setNoOfDocRegDuration(String noOfDocRegDuration) {
		this.noOfDocRegDuration = noOfDocRegDuration;
	}
	public String getStampDutyMonth() {
		return stampDutyMonth;
	}
	public void setStampDutyMonth(String stampDutyMonth) {
		this.stampDutyMonth = stampDutyMonth;
	}
	public String getGramDutyMonth() {
		return gramDutyMonth;
	}
	public void setGramDutyMonth(String gramDutyMonth) {
		this.gramDutyMonth = gramDutyMonth;
	}
	public String getNagarDutyMonth() {
		return nagarDutyMonth;
	}
	public void setNagarDutyMonth(String nagarDutyMonth) {
		this.nagarDutyMonth = nagarDutyMonth;
	}
	public String getUpkarMonth() {
		return upkarMonth;
	}
	public void setUpkarMonth(String upkarMonth) {
		this.upkarMonth = upkarMonth;
	}
	public String getRegFeeMonth() {
		return regFeeMonth;
	}
	public void setRegFeeMonth(String regFeeMonth) {
		this.regFeeMonth = regFeeMonth;
	}
	public String getStampDutyYear() {
		return stampDutyYear;
	}
	public void setStampDutyYear(String stampDutyYear) {
		this.stampDutyYear = stampDutyYear;
	}
	public String getGramDutyYear() {
		return gramDutyYear;
	}
	public void setGramDutyYear(String gramDutyYear) {
		this.gramDutyYear = gramDutyYear;
	}
	public String getNagarDutyYear() {
		return nagarDutyYear;
	}
	public void setNagarDutyYear(String nagarDutyYear) {
		this.nagarDutyYear = nagarDutyYear;
	}
	public String getUpkarYear() {
		return upkarYear;
	}
	public void setUpkarYear(String upkarYear) {
		this.upkarYear = upkarYear;
	}
	public String getRegFeeYear() {
		return regFeeYear;
	}
	public void setRegFeeYear(String regFeeYear) {
		this.regFeeYear = regFeeYear;
	}
	private String noOfDocRegMonth;
	private String noOfDocRegDuration;
	private String stampDutyMonth;
	private String gramDutyMonth;
	private String nagarDutyMonth;
	private String upkarMonth;
	private String regFeeMonth;
	private String stampDutyYear;
	private String gramDutyYear;
	private String nagarDutyYear;
	private String upkarYear;
	private String regFeeYear;
	private String fromMonth;
	private String fromYear;
	private String totSum;
	public String getTotSum() {
		return totSum;
	}
	public void setTotSum(String totSum) {
		this.totSum = totSum;
	}
	public String getToMonth() {
		return toMonth;
	}
	public void setToMonth(String toMonth) {
		this.toMonth = toMonth;
	}
	private String toMonth;
	private String toYear;
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(String fromMonth) {
		this.fromMonth = fromMonth;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	private ArrayList cessReportList = new ArrayList();
	public ArrayList getCessReportList() {
		return cessReportList;
	}
	public void setCessReportList(ArrayList cessReportList) {
		this.cessReportList = cessReportList;
	}
	/**
	 * @return the reportList
	 */
	public ArrayList getReportList() {
		return reportList;
	}
	/**
	 * @param reportList the reportList to set
	 */
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the petitionNumber
	 */
	public String getPetitionNumber() {
		return petitionNumber;
	}
	/**
	 * @param petitionNumber the petitionNumber to set
	 */
	public void setPetitionNumber(String petitionNumber) {
		this.petitionNumber = petitionNumber;
	}
	/**
	 * @return the petitionerName
	 */
	public String getPetitionerName() {
		return petitionerName;
	}
	/**
	 * @param petitionerName the petitionerName to set
	 */
	public void setPetitionerName(String petitionerName) {
		this.petitionerName = petitionerName;
	}
	/**
	 * @return the petitionDate
	 */
	public String getPetitionDate() {
		return petitionDate;
	}
	/**
	 * @param petitionDate the petitionDate to set
	 */
	public void setPetitionDate(String petitionDate) {
		this.petitionDate = petitionDate;
	}
	/**
	 * @return the courtName
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param courtName the courtName to set
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return the officerIncharge
	 */
	public String getOfficerIncharge() {
		return officerIncharge;
	}
	/**
	 * @param officerIncharge the officerIncharge to set
	 */
	public void setOfficerIncharge(String officerIncharge) {
		this.officerIncharge = officerIncharge;
	}
	/**
	 * @return the subjectMatter
	 */
	public String getSubjectMatter() {
		return subjectMatter;
	}
	/**
	 * @param subjectMatter the subjectMatter to set
	 */
	public void setSubjectMatter(String subjectMatter) {
		this.subjectMatter = subjectMatter;
	}
	/**
	 * @return the stayOrder
	 */
	public String getStayOrder() {
		return stayOrder;
	}
	/**
	 * @param stayOrder the stayOrder to set
	 */
	public void setStayOrder(String stayOrder) {
		this.stayOrder = stayOrder;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

}
