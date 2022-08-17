package com.wipro.igrs.report.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportingDTO implements Serializable{
     
	private String name;
    private String value;
	private ArrayList sroIDList;
	private ArrayList droIDList;
	private ArrayList districtIDList;
	
	//variables belong to RevenueReceipt Report
	private String sno;
	private String nameofint;
	private String noofdocs;
	private String revrep;
	
	//variables belong to comparative inf of revenue Receipt and reg doc
	private String distid;
	private String annualTarget;
	private String monthTarget;
	private String stampduty;
	private String regfee;
	private String stampdutyPrev; 
	private String regfeePrev;
	private String total;
	private String totalPrev; 
	private String stampdutyComp;
	private String regfeeComp;
	private String totalComp;
	private String rural;
	private String urban;
	private String areaTotal;
	private String ruralPrev;
	private String urbanPrev;
	private String  areaTotalPrev;
	private String ruralComp;
	private String urbanComp;
	private String areaTotalComp;
	private String noofdoc;
	private String noofdocPrev;
	private String noofdocComp;
	
	//varaiables related to RRc cases
	private String anualTarget;
	private String mthTarget;
	private String pendingCases;
	private String caseRegisd;
	private String amt;
	private String casesDisp;
	private String amtRecover;
	private String casesPend;
	private String amtPend;
	private String noCasesone;
	private String amtone;
	private String noCasestwo;
	private String amttwo;
	private String noCasesthree;
	private String amtthree;
	private String noCasesfour;
	private String amtfour;
	private String noCasesfive;
	private String amtfive;
	
	//variables related to cess wise inf
	private String casenoSc;
	private String amtRecoverSc;
	private String actionSc;
	private String actionHc;
	private String actionRb;
	private String actionRc;
	
	//added by Shreeraj
	private String year;
	private String yearValue;
	private String districtID;
	private String districtName;
	private String institutionName;
	private String noDocMonth;
	private String revRcptMonth;
	private String noDocPeriod;
	private String revRcptPeriod;
	private String totNoDocMonth;
	private String totRevRcptMonth;
	private String totNoDocPeriod;
	private String totRevRcptPeriod;
	private ArrayList reportList;
	private String exemptionName;
	private String exeStampMonth;
	private String exeStampPeriod;
	private String exeRegFeePeriod;
	private String exeRegFeeMonth;
	private String exeTotalMonth;
	private String exeTotalPeriod;
	private String exeRegFeeComp;
	private String exeStampComp;
	private String exeTotalComp;
	private String totMonth;
	private String totPeriod;
	private String totExeStampMonth;
	private String totExeStampPeriod;
	private String totExeRegFeeMonth;
	private String totExeRegFeePeriod;
	private String totTotalMonth;
	private String totTotalPeriod;
	
	private String noDocRuralMonth;
	private String noDocUrbanMonth;
	private String noDocTotalMonth;
	private String noDocRuralPeriod;
	private String noDocUrbanPeriod;
	private String noDocTotalPeriod;
	private String noDocRuralPeriodApr;
	private String noDocUrbanPeriodApr;
	private String noDocTotalPeriodApr;
	private String noDocRuralPeriodPrv;
	private String noDocUrbanPeriodPrv;
	private String noDocTotalPeriodPrv;
	private String noDocRuralPeriodComp;
	private String noDocUrbanPeriodComp;
	private String noDocTotalPeriodComp;
	private String exeRegFeeApr;
	private String exeStampApr;
	private String exeTotalApr;
	private String exeRegFeePrvApr;
	private String exeStampPrvApr;
	private String exeTotalPrvApr;
	private String exeRegFeeCompApr;
	private String exeStampComApr;
	private String exeTotalCompApr;
	private ArrayList noDocReportList;

	
	
	
	public String getExeTotalMonth() {
		return exeTotalMonth;
	}
	public void setExeTotalMonth(String exeTotalMonth) {
		this.exeTotalMonth = exeTotalMonth;
	}
	public String getExeTotalPeriod() {
		return exeTotalPeriod;
	}
	public void setExeTotalPeriod(String exeTotalPeriod) {
		this.exeTotalPeriod = exeTotalPeriod;
	}
	public String getExeRegFeeComp() {
		return exeRegFeeComp;
	}
	public void setExeRegFeeComp(String exeRegFeeComp) {
		this.exeRegFeeComp = exeRegFeeComp;
	}
	public String getExeStampComp() {
		return exeStampComp;
	}
	public void setExeStampComp(String exeStampComp) {
		this.exeStampComp = exeStampComp;
	}
	public String getExeTotalComp() {
		return exeTotalComp;
	}
	public void setExeTotalComp(String exeTotalComp) {
		this.exeTotalComp = exeTotalComp;
	}
	
	public String getNoDocRuralMonth() {
		return noDocRuralMonth;
	}
	public void setNoDocRuralMonth(String noDocRuralMonth) {
		this.noDocRuralMonth = noDocRuralMonth;
	}
	public String getNoDocUrbanMonth() {
		return noDocUrbanMonth;
	}
	public void setNoDocUrbanMonth(String noDocUrbanMonth) {
		this.noDocUrbanMonth = noDocUrbanMonth;
	}
	public String getNoDocTotalMonth() {
		return noDocTotalMonth;
	}
	public void setNoDocTotalMonth(String noDocTotalMonth) {
		this.noDocTotalMonth = noDocTotalMonth;
	}
	public String getNoDocRuralPeriod() {
		return noDocRuralPeriod;
	}
	public void setNoDocRuralPeriod(String noDocRuralPeriod) {
		this.noDocRuralPeriod = noDocRuralPeriod;
	}
	public String getNoDocUrbanPeriod() {
		return noDocUrbanPeriod;
	}
	public void setNoDocUrbanPeriod(String noDocUrbanPeriod) {
		this.noDocUrbanPeriod = noDocUrbanPeriod;
	}
	public String getNoDocTotalPeriod() {
		return noDocTotalPeriod;
	}
	public void setNoDocTotalPeriod(String noDocTotalPeriod) {
		this.noDocTotalPeriod = noDocTotalPeriod;
	}
	public String getNoDocRuralPeriodApr() {
		return noDocRuralPeriodApr;
	}
	public void setNoDocRuralPeriodApr(String noDocRuralPeriodApr) {
		this.noDocRuralPeriodApr = noDocRuralPeriodApr;
	}
	public String getNoDocUrbanPeriodApr() {
		return noDocUrbanPeriodApr;
	}
	public void setNoDocUrbanPeriodApr(String noDocUrbanPeriodApr) {
		this.noDocUrbanPeriodApr = noDocUrbanPeriodApr;
	}
	public String getNoDocTotalPeriodApr() {
		return noDocTotalPeriodApr;
	}
	public void setNoDocTotalPeriodApr(String noDocTotalPeriodApr) {
		this.noDocTotalPeriodApr = noDocTotalPeriodApr;
	}
	public String getNoDocRuralPeriodPrv() {
		return noDocRuralPeriodPrv;
	}
	public void setNoDocRuralPeriodPrv(String noDocRuralPeriodPrv) {
		this.noDocRuralPeriodPrv = noDocRuralPeriodPrv;
	}
	public String getNoDocUrbanPeriodPrv() {
		return noDocUrbanPeriodPrv;
	}
	public void setNoDocUrbanPeriodPrv(String noDocUrbanPeriodPrv) {
		this.noDocUrbanPeriodPrv = noDocUrbanPeriodPrv;
	}
	public String getNoDocTotalPeriodPrv() {
		return noDocTotalPeriodPrv;
	}
	public void setNoDocTotalPeriodPrv(String noDocTotalPeriodPrv) {
		this.noDocTotalPeriodPrv = noDocTotalPeriodPrv;
	}
	public String getNoDocRuralPeriodComp() {
		return noDocRuralPeriodComp;
	}
	public void setNoDocRuralPeriodComp(String noDocRuralPeriodComp) {
		this.noDocRuralPeriodComp = noDocRuralPeriodComp;
	}
	public String getNoDocUrbanPeriodComp() {
		return noDocUrbanPeriodComp;
	}
	public void setNoDocUrbanPeriodComp(String noDocUrbanPeriodComp) {
		this.noDocUrbanPeriodComp = noDocUrbanPeriodComp;
	}
	public String getNoDocTotalPeriodComp() {
		return noDocTotalPeriodComp;
	}
	public void setNoDocTotalPeriodComp(String noDocTotalPeriodComp) {
		this.noDocTotalPeriodComp = noDocTotalPeriodComp;
	}
	public String getExeRegFeeApr() {
		return exeRegFeeApr;
	}
	public void setExeRegFeeApr(String exeRegFeeApr) {
		this.exeRegFeeApr = exeRegFeeApr;
	}
	public String getExeStampApr() {
		return exeStampApr;
	}
	public void setExeStampApr(String exeStampApr) {
		this.exeStampApr = exeStampApr;
	}
	public String getExeTotalApr() {
		return exeTotalApr;
	}
	public void setExeTotalApr(String exeTotalApr) {
		this.exeTotalApr = exeTotalApr;
	}
	public String getExeRegFeePrvApr() {
		return exeRegFeePrvApr;
	}
	public void setExeRegFeePrvApr(String exeRegFeePrvApr) {
		this.exeRegFeePrvApr = exeRegFeePrvApr;
	}
	public String getExeStampPrvApr() {
		return exeStampPrvApr;
	}
	public void setExeStampPrvApr(String exeStampPrvApr) {
		this.exeStampPrvApr = exeStampPrvApr;
	}
	public String getExeTotalPrvApr() {
		return exeTotalPrvApr;
	}
	public void setExeTotalPrvApr(String exeTotalPrvApr) {
		this.exeTotalPrvApr = exeTotalPrvApr;
	}
	public String getExeRegFeeCompApr() {
		return exeRegFeeCompApr;
	}
	public void setExeRegFeeCompApr(String exeRegFeeCompApr) {
		this.exeRegFeeCompApr = exeRegFeeCompApr;
	}
	public String getExeStampComApr() {
		return exeStampComApr;
	}
	public void setExeStampComApr(String exeStampComApr) {
		this.exeStampComApr = exeStampComApr;
	}
	public String getExeTotalCompApr() {
		return exeTotalCompApr;
	}
	public void setExeTotalCompApr(String exeTotalCompApr) {
		this.exeTotalCompApr = exeTotalCompApr;
	}
	public ArrayList getNoDocReportList() {
		return noDocReportList;
	}
	public void setNoDocReportList(ArrayList noDocReportList) {
		this.noDocReportList = noDocReportList;
	}
	public String getTotExeStampMonth() {
		return totExeStampMonth;
	}
	public void setTotExeStampMonth(String totExeStampMonth) {
		this.totExeStampMonth = totExeStampMonth;
	}
	public String getTotExeStampPeriod() {
		return totExeStampPeriod;
	}
	public void setTotExeStampPeriod(String totExeStampPeriod) {
		this.totExeStampPeriod = totExeStampPeriod;
	}
	public String getTotExeRegFeeMonth() {
		return totExeRegFeeMonth;
	}
	public void setTotExeRegFeeMonth(String totExeRegFeeMonth) {
		this.totExeRegFeeMonth = totExeRegFeeMonth;
	}
	public String getTotExeRegFeePeriod() {
		return totExeRegFeePeriod;
	}
	public void setTotExeRegFeePeriod(String totExeRegFeePeriod) {
		this.totExeRegFeePeriod = totExeRegFeePeriod;
	}
	public String getTotTotalMonth() {
		return totTotalMonth;
	}
	public void setTotTotalMonth(String totTotalMonth) {
		this.totTotalMonth = totTotalMonth;
	}
	public String getTotTotalPeriod() {
		return totTotalPeriod;
	}
	public void setTotTotalPeriod(String totTotalPeriod) {
		this.totTotalPeriod = totTotalPeriod;
	}
	public String getExemptionName() {
		return exemptionName;
	}
	public void setExemptionName(String exemptionName) {
		this.exemptionName = exemptionName;
	}
	public String getExeStampMonth() {
		return exeStampMonth;
	}
	public void setExeStampMonth(String exeStampMonth) {
		this.exeStampMonth = exeStampMonth;
	}
	public String getExeStampPeriod() {
		return exeStampPeriod;
	}
	public void setExeStampPeriod(String exeStampPeriod) {
		this.exeStampPeriod = exeStampPeriod;
	}
	public String getExeRegFeePeriod() {
		return exeRegFeePeriod;
	}
	public void setExeRegFeePeriod(String exeRegFeePeriod) {
		this.exeRegFeePeriod = exeRegFeePeriod;
	}
	public String getExeRegFeeMonth() {
		return exeRegFeeMonth;
	}
	public void setExeRegFeeMonth(String exeRegFeeMonth) {
		this.exeRegFeeMonth = exeRegFeeMonth;
	}
	public String getTotMonth() {
		return totMonth;
	}
	public void setTotMonth(String totMonth) {
		this.totMonth = totMonth;
	}
	public String getTotPeriod() {
		return totPeriod;
	}
	public void setTotPeriod(String totPeriod) {
		this.totPeriod = totPeriod;
	}
	public ArrayList getReportList() {
		return reportList;
	}
	public void setReportList(ArrayList reportList) {
		this.reportList = reportList;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getNoDocMonth() {
		return noDocMonth;
	}
	public void setNoDocMonth(String noDocMonth) {
		this.noDocMonth = noDocMonth;
	}
	public String getRevRcptMonth() {
		return revRcptMonth;
	}
	public void setRevRcptMonth(String revRcptMonth) {
		this.revRcptMonth = revRcptMonth;
	}
	public String getNoDocPeriod() {
		return noDocPeriod;
	}
	public void setNoDocPeriod(String noDocPeriod) {
		this.noDocPeriod = noDocPeriod;
	}
	public String getRevRcptPeriod() {
		return revRcptPeriod;
	}
	public void setRevRcptPeriod(String revRcptPeriod) {
		this.revRcptPeriod = revRcptPeriod;
	}
	public String getTotNoDocMonth() {
		return totNoDocMonth;
	}
	public void setTotNoDocMonth(String totNoDocMonth) {
		this.totNoDocMonth = totNoDocMonth;
	}
	public String getTotRevRcptMonth() {
		return totRevRcptMonth;
	}
	public void setTotRevRcptMonth(String totRevRcptMonth) {
		this.totRevRcptMonth = totRevRcptMonth;
	}
	public String getTotNoDocPeriod() {
		return totNoDocPeriod;
	}
	public void setTotNoDocPeriod(String totNoDocPeriod) {
		this.totNoDocPeriod = totNoDocPeriod;
	}
	public String getTotRevRcptPeriod() {
		return totRevRcptPeriod;
	}
	public void setTotRevRcptPeriod(String totRevRcptPeriod) {
		this.totRevRcptPeriod = totRevRcptPeriod;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getYearValue() {
		return yearValue;
	}
	public void setYearValue(String yearValue) {
		this.yearValue = yearValue;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getActionSc() {
		return actionSc;
	}
	public void setActionSc(String actionSc) {
		this.actionSc = actionSc;
	}
	public String getActionHc() {
		return actionHc;
	}
	public void setActionHc(String actionHc) {
		this.actionHc = actionHc;
	}
	public String getActionRb() {
		return actionRb;
	}
	public void setActionRb(String actionRb) {
		this.actionRb = actionRb;
	}
	public String getActionRc() {
		return actionRc;
	}
	public void setActionRc(String actionRc) {
		this.actionRc = actionRc;
	}
	private String latestCaseSc;
	private String currentcaseSc;
	private String casenoHc;
	private String amtRecoverHc;
	private String latestCaseHc;
	private String currentcaseHc;
	private String casenoBr;
	private String amtRecoverBr;
	private String latestCaseBr;
	private String currentcaseBr;
	private String casenoC;
	private String amtRecoverC;
	private String latestCaseC;
	private String currentcaseC;
	private String remarks;
	private String remarksHC;
	private String remarksRB;
	private String remarksRC;
	public String getRemarksHC() {
		return remarksHC;
	}
	public void setRemarksHC(String remarksHC) {
		this.remarksHC = remarksHC;
	}
	public String getRemarksRB() {
		return remarksRB;
	}
	public void setRemarksRB(String remarksRB) {
		this.remarksRB = remarksRB;
	}
	public String getRemarksRC() {
		return remarksRC;
	}
	public void setRemarksRC(String remarksRC) {
		this.remarksRC = remarksRC;
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
	/**
	 * @return the anualTarget
	 */
	public String getAnualTarget() {
		return anualTarget;
	}
	/**
	 * @param anualTarget the anualTarget to set
	 */
	public void setAnualTarget(String anualTarget) {
		this.anualTarget = anualTarget;
	}
	/**
	 * @return the mthTarget
	 */
	public String getMthTarget() {
		return mthTarget;
	}
	/**
	 * @param mthTarget the mthTarget to set
	 */
	public void setMthTarget(String mthTarget) {
		this.mthTarget = mthTarget;
	}
	/**
	 * @return the pendingCases
	 */
	public String getPendingCases() {
		return pendingCases;
	}
	/**
	 * @param pendingCases the pendingCases to set
	 */
	public void setPendingCases(String pendingCases) {
		this.pendingCases = pendingCases;
	}
	/**
	 * @return the caseRegisd
	 */
	public String getCaseRegisd() {
		return caseRegisd;
	}
	/**
	 * @param caseRegisd the caseRegisd to set
	 */
	public void setCaseRegisd(String caseRegisd) {
		this.caseRegisd = caseRegisd;
	}
	/**
	 * @return the amt
	 */
	public String getAmt() {
		return amt;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}
	/**
	 * @return the casesDisp
	 */
	public String getCasesDisp() {
		return casesDisp;
	}
	/**
	 * @param casesDisp the casesDisp to set
	 */
	public void setCasesDisp(String casesDisp) {
		this.casesDisp = casesDisp;
	}
	/**
	 * @return the amtRecover
	 */
	public String getAmtRecover() {
		return amtRecover;
	}
	/**
	 * @param amtRecover the amtRecover to set
	 */
	public void setAmtRecover(String amtRecover) {
		this.amtRecover = amtRecover;
	}
	/**
	 * @return the casesPend
	 */
	public String getCasesPend() {
		return casesPend;
	}
	/**
	 * @param casesPend the casesPend to set
	 */
	public void setCasesPend(String casesPend) {
		this.casesPend = casesPend;
	}
	/**
	 * @return the amtPend
	 */
	public String getAmtPend() {
		return amtPend;
	}
	/**
	 * @param amtPend the amtPend to set
	 */
	public void setAmtPend(String amtPend) {
		this.amtPend = amtPend;
	}
	/**
	 * @return the distid
	 */
	public String getDistid() {
		return distid;
	}
	/**
	 * @param distid the distid to set
	 */
	public void setDistid(String distid) {
		this.distid = distid;
	}
	/**
	 * @return the annualTarget
	 */
	public String getAnnualTarget() {
		return annualTarget;
	}
	/**
	 * @param annualTarget the annualTarget to set
	 */
	public void setAnnualTarget(String annualTarget) {
		this.annualTarget = annualTarget;
	}
	/**
	 * @return the monthTarget
	 */
	public String getMonthTarget() {
		return monthTarget;
	}
	/**
	 * @param monthTarget the monthTarget to set
	 */
	public void setMonthTarget(String monthTarget) {
		this.monthTarget = monthTarget;
	}
	/**
	 * @return the stampduty
	 */
	public String getStampduty() {
		return stampduty;
	}
	/**
	 * @param stampduty the stampduty to set
	 */
	public void setStampduty(String stampduty) {
		this.stampduty = stampduty;
	}
	/**
	 * @return the regfee
	 */
	public String getRegfee() {
		return regfee;
	}
	/**
	 * @param regfee the regfee to set
	 */
	public void setRegfee(String regfee) {
		this.regfee = regfee;
	}
	/**
	 * @return the stampdutyPrev
	 */
	public String getStampdutyPrev() {
		return stampdutyPrev;
	}
	/**
	 * @param stampdutyPrev the stampdutyPrev to set
	 */
	public void setStampdutyPrev(String stampdutyPrev) {
		this.stampdutyPrev = stampdutyPrev;
	}
	/**
	 * @return the regfeePrev
	 */
	public String getRegfeePrev() {
		return regfeePrev;
	}
	/**
	 * @param regfeePrev the regfeePrev to set
	 */
	public void setRegfeePrev(String regfeePrev) {
		this.regfeePrev = regfeePrev;
	}
	/**
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return the totalPrev
	 */
	public String getTotalPrev() {
		return totalPrev;
	}
	/**
	 * @param totalPrev the totalPrev to set
	 */
	public void setTotalPrev(String totalPrev) {
		this.totalPrev = totalPrev;
	}
	/**
	 * @return the stampdutyComp
	 */
	public String getStampdutyComp() {
		return stampdutyComp;
	}
	/**
	 * @param stampdutyComp the stampdutyComp to set
	 */
	public void setStampdutyComp(String stampdutyComp) {
		this.stampdutyComp = stampdutyComp;
	}
	/**
	 * @return the regfeeComp
	 */
	public String getRegfeeComp() {
		return regfeeComp;
	}
	/**
	 * @param regfeeComp the regfeeComp to set
	 */
	public void setRegfeeComp(String regfeeComp) {
		this.regfeeComp = regfeeComp;
	}
	/**
	 * @return the totalComp
	 */
	public String getTotalComp() {
		return totalComp;
	}
	/**
	 * @param totalComp the totalComp to set
	 */
	public void setTotalComp(String totalComp) {
		this.totalComp = totalComp;
	}
	/**
	 * @return the rural
	 */
	public String getRural() {
		return rural;
	}
	/**
	 * @param rural the rural to set
	 */
	public void setRural(String rural) {
		this.rural = rural;
	}
	/**
	 * @return the urban
	 */
	public String getUrban() {
		return urban;
	}
	/**
	 * @param urban the urban to set
	 */
	public void setUrban(String urban) {
		this.urban = urban;
	}
	/**
	 * @return the areaTotal
	 */
	public String getAreaTotal() {
		return areaTotal;
	}
	/**
	 * @param areaTotal the areaTotal to set
	 */
	public void setAreaTotal(String areaTotal) {
		this.areaTotal = areaTotal;
	}
	/**
	 * @return the ruralPrev
	 */
	public String getRuralPrev() {
		return ruralPrev;
	}
	/**
	 * @param ruralPrev the ruralPrev to set
	 */
	public void setRuralPrev(String ruralPrev) {
		this.ruralPrev = ruralPrev;
	}
	/**
	 * @return the urbanPrev
	 */
	public String getUrbanPrev() {
		return urbanPrev;
	}
	/**
	 * @param urbanPrev the urbanPrev to set
	 */
	public void setUrbanPrev(String urbanPrev) {
		this.urbanPrev = urbanPrev;
	}
	/**
	 * @return the areaTotalPrev
	 */
	public String getAreaTotalPrev() {
		return areaTotalPrev;
	}
	/**
	 * @param areaTotalPrev the areaTotalPrev to set
	 */
	public void setAreaTotalPrev(String areaTotalPrev) {
		this.areaTotalPrev = areaTotalPrev;
	}
	/**
	 * @return the ruralComp
	 */
	public String getRuralComp() {
		return ruralComp;
	}
	/**
	 * @param ruralComp the ruralComp to set
	 */
	public void setRuralComp(String ruralComp) {
		this.ruralComp = ruralComp;
	}
	/**
	 * @return the urbanComp
	 */
	public String getUrbanComp() {
		return urbanComp;
	}
	/**
	 * @param urbanComp the urbanComp to set
	 */
	public void setUrbanComp(String urbanComp) {
		this.urbanComp = urbanComp;
	}
	/**
	 * @return the areaTotalComp
	 */
	public String getAreaTotalComp() {
		return areaTotalComp;
	}
	/**
	 * @param areaTotalComp the areaTotalComp to set
	 */
	public void setAreaTotalComp(String areaTotalComp) {
		this.areaTotalComp = areaTotalComp;
	}
	/**
	 * @return the noofdoc
	 */
	public String getNoofdoc() {
		return noofdoc;
	}
	/**
	 * @param noofdoc the noofdoc to set
	 */
	public void setNoofdoc(String noofdoc) {
		this.noofdoc = noofdoc;
	}
	/**
	 * @return the noofdocPrev
	 */
	public String getNoofdocPrev() {
		return noofdocPrev;
	}
	/**
	 * @param noofdocPrev the noofdocPrev to set
	 */
	public void setNoofdocPrev(String noofdocPrev) {
		this.noofdocPrev = noofdocPrev;
	}
	/**
	 * @return the noofdocComp
	 */
	public String getNoofdocComp() {
		return noofdocComp;
	}
	/**
	 * @param noofdocComp the noofdocComp to set
	 */
	public void setNoofdocComp(String noofdocComp) {
		this.noofdocComp = noofdocComp;
	}
	/**
	 * @return the sno
	 */
	public String getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(String sno) {
		this.sno = sno;
	}
	/**
	 * @return the nameofint
	 */
	public String getNameofint() {
		return nameofint;
	}
	/**
	 * @param nameofint the nameofint to set
	 */
	public void setNameofint(String nameofint) {
		this.nameofint = nameofint;
	}
	/**
	 * @return the noofdocs
	 */
	public String getNoofdocs() {
		return noofdocs;
	}
	/**
	 * @param noofdocs the noofdocs to set
	 */
	public void setNoofdocs(String noofdocs) {
		this.noofdocs = noofdocs;
	}
	/**
	 * @return the revrep
	 */
	public String getRevrep() {
		return revrep;
	}
	/**
	 * @param revrep the revrep to set
	 */
	public void setRevrep(String revrep) {
		this.revrep = revrep;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList getSroIDList() {
		return sroIDList;
	}
	public void setSroIDList(ArrayList sroIDList) {
		this.sroIDList = sroIDList;
	}
	public ArrayList getDroIDList() {
		return droIDList;
	}
	public void setDroIDList(ArrayList droIDList) {
		this.droIDList = droIDList;
	}
	/**
	 * @return the districtIDList
	 */
	public ArrayList getDistrictIDList() {
		return districtIDList;
	}
	/**
	 * @param districtIDList the districtIDList to set
	 */
	public void setDistrictIDList(ArrayList districtIDList) {
		this.districtIDList = districtIDList;
	}
	/**
	 * @return the noCasesone
	 */
	public String getNoCasesone() {
		return noCasesone;
	}
	/**
	 * @param noCasesone the noCasesone to set
	 */
	public void setNoCasesone(String noCasesone) {
		this.noCasesone = noCasesone;
	}
	/**
	 * @return the amtone
	 */
	public String getAmtone() {
		return amtone;
	}
	/**
	 * @param amtone the amtone to set
	 */
	public void setAmtone(String amtone) {
		this.amtone = amtone;
	}
	/**
	 * @return the noCasestwo
	 */
	public String getNoCasestwo() {
		return noCasestwo;
	}
	/**
	 * @param noCasestwo the noCasestwo to set
	 */
	public void setNoCasestwo(String noCasestwo) {
		this.noCasestwo = noCasestwo;
	}
	/**
	 * @return the amttwo
	 */
	public String getAmttwo() {
		return amttwo;
	}
	/**
	 * @param amttwo the amttwo to set
	 */
	public void setAmttwo(String amttwo) {
		this.amttwo = amttwo;
	}
	/**
	 * @return the noCasesthree
	 */
	public String getNoCasesthree() {
		return noCasesthree;
	}
	/**
	 * @param noCasesthree the noCasesthree to set
	 */
	public void setNoCasesthree(String noCasesthree) {
		this.noCasesthree = noCasesthree;
	}
	/**
	 * @return the amtthree
	 */
	public String getAmtthree() {
		return amtthree;
	}
	/**
	 * @param amtthree the amtthree to set
	 */
	public void setAmtthree(String amtthree) {
		this.amtthree = amtthree;
	}
	/**
	 * @return the noCasesfour
	 */
	public String getNoCasesfour() {
		return noCasesfour;
	}
	/**
	 * @param noCasesfour the noCasesfour to set
	 */
	public void setNoCasesfour(String noCasesfour) {
		this.noCasesfour = noCasesfour;
	}
	/**
	 * @return the amtfour
	 */
	public String getAmtfour() {
		return amtfour;
	}
	/**
	 * @param amtfour the amtfour to set
	 */
	public void setAmtfour(String amtfour) {
		this.amtfour = amtfour;
	}
	/**
	 * @return the noCasesfive
	 */
	public String getNoCasesfive() {
		return noCasesfive;
	}
	/**
	 * @param noCasesfive the noCasesfive to set
	 */
	public void setNoCasesfive(String noCasesfive) {
		this.noCasesfive = noCasesfive;
	}
	/**
	 * @return the amtfive
	 */
	public String getAmtfive() {
		return amtfive;
	}
	/**
	 * @param amtfive the amtfive to set
	 */
	public void setAmtfive(String amtfive) {
		this.amtfive = amtfive;
	}
	/**
	 * @return the casenoSc
	 */
	public String getCasenoSc() {
		return casenoSc;
	}
	/**
	 * @param casenoSc the casenoSc to set
	 */
	public void setCasenoSc(String casenoSc) {
		this.casenoSc = casenoSc;
	}
	/**
	 * @return the amtRecoverSc
	 */
	public String getAmtRecoverSc() {
		return amtRecoverSc;
	}
	/**
	 * @param amtRecoverSc the amtRecoverSc to set
	 */
	public void setAmtRecoverSc(String amtRecoverSc) {
		this.amtRecoverSc = amtRecoverSc;
	}
	/**
	 * @return the latestCaseSc
	 */
	public String getLatestCaseSc() {
		return latestCaseSc;
	}
	/**
	 * @param latestCaseSc the latestCaseSc to set
	 */
	public void setLatestCaseSc(String latestCaseSc) {
		this.latestCaseSc = latestCaseSc;
	}
	/**
	 * @return the currentcaseSc
	 */
	public String getCurrentcaseSc() {
		return currentcaseSc;
	}
	/**
	 * @param currentcaseSc the currentcaseSc to set
	 */
	public void setCurrentcaseSc(String currentcaseSc) {
		this.currentcaseSc = currentcaseSc;
	}
	/**
	 * @return the casenoHc
	 */
	public String getCasenoHc() {
		return casenoHc;
	}
	/**
	 * @param casenoHc the casenoHc to set
	 */
	public void setCasenoHc(String casenoHc) {
		this.casenoHc = casenoHc;
	}
	/**
	 * @return the amtRecoverHc
	 */
	public String getAmtRecoverHc() {
		return amtRecoverHc;
	}
	/**
	 * @param amtRecoverHc the amtRecoverHc to set
	 */
	public void setAmtRecoverHc(String amtRecoverHc) {
		this.amtRecoverHc = amtRecoverHc;
	}
	/**
	 * @return the latestCaseHc
	 */
	public String getLatestCaseHc() {
		return latestCaseHc;
	}
	/**
	 * @param latestCaseHc the latestCaseHc to set
	 */
	public void setLatestCaseHc(String latestCaseHc) {
		this.latestCaseHc = latestCaseHc;
	}
	/**
	 * @return the currentcaseHc
	 */
	public String getCurrentcaseHc() {
		return currentcaseHc;
	}
	/**
	 * @param currentcaseHc the currentcaseHc to set
	 */
	public void setCurrentcaseHc(String currentcaseHc) {
		this.currentcaseHc = currentcaseHc;
	}
	/**
	 * @return the casenoBr
	 */
	public String getCasenoBr() {
		return casenoBr;
	}
	/**
	 * @param casenoBr the casenoBr to set
	 */
	public void setCasenoBr(String casenoBr) {
		this.casenoBr = casenoBr;
	}
	/**
	 * @return the amtRecoverBr
	 */
	public String getAmtRecoverBr() {
		return amtRecoverBr;
	}
	/**
	 * @param amtRecoverBr the amtRecoverBr to set
	 */
	public void setAmtRecoverBr(String amtRecoverBr) {
		this.amtRecoverBr = amtRecoverBr;
	}
	/**
	 * @return the latestCaseBr
	 */
	public String getLatestCaseBr() {
		return latestCaseBr;
	}
	/**
	 * @param latestCaseBr the latestCaseBr to set
	 */
	public void setLatestCaseBr(String latestCaseBr) {
		this.latestCaseBr = latestCaseBr;
	}
	/**
	 * @return the currentcaseBr
	 */
	public String getCurrentcaseBr() {
		return currentcaseBr;
	}
	/**
	 * @param currentcaseBr the currentcaseBr to set
	 */
	public void setCurrentcaseBr(String currentcaseBr) {
		this.currentcaseBr = currentcaseBr;
	}
	/**
	 * @return the casenoC
	 */
	public String getCasenoC() {
		return casenoC;
	}
	/**
	 * @param casenoC the casenoC to set
	 */
	public void setCasenoC(String casenoC) {
		this.casenoC = casenoC;
	}
	/**
	 * @return the amtRecoverC
	 */
	public String getAmtRecoverC() {
		return amtRecoverC;
	}
	/**
	 * @param amtRecoverC the amtRecoverC to set
	 */
	public void setAmtRecoverC(String amtRecoverC) {
		this.amtRecoverC = amtRecoverC;
	}
	/**
	 * @return the latestCaseC
	 */
	public String getLatestCaseC() {
		return latestCaseC;
	}
	/**
	 * @param latestCaseC the latestCaseC to set
	 */
	public void setLatestCaseC(String latestCaseC) {
		this.latestCaseC = latestCaseC;
	}
	/**
	 * @return the currentcaseC
	 */
	public String getCurrentcaseC() {
		return currentcaseC;
	}
	/**
	 * @param currentcaseC the currentcaseC to set
	 */
	public void setCurrentcaseC(String currentcaseC) {
		this.currentcaseC = currentcaseC;
	}
	
	
	
	
}	