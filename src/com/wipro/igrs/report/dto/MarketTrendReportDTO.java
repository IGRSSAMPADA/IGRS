package com.wipro.igrs.report.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class MarketTrendReportDTO implements Serializable{
	private int sno;
	private String droName;
	private String districtId;
	private String districtName;
	private String officeTypeId;
	private String officeName;
	private String officeId;	
	private String userType;
	private String searchType;
	private String areaType;
	private String month;
	private String fromDate;
	private String toDate;
	private String fromYear;
	private String toYear; 
	
	private String stateId;
	private String tehsilId;
	private String tehsilName;
	private String bookId;
	private String bookName;

	private String areaTypeName;
	private String wardPatwariId;
	private String wardPatwariName;
	private String mohallaId;
	private String mohallaName;
	private String docType;
	private String noOfDoc;
	private float revReceipt;
	
	private float compNoOfDoc;
	private float compValuation;
	private float comprevReceipt;
	private String effFactorVal;
	private String factorName;
	private String facValIncDec;
	private float effFacDoc;
	private float effFacReceipt;
	private float effFacValuation;
	
	//For SRO wise StampDty,Registration Fee Report
	private String sroName;
	private float curMonStampDty;
	private float curMonRegFee;
	private float curMonTot;
	private float preMonStampDty;
	private float preMonRegFee;
	private float preMonTot;
	private float compStampDty;
	private float compRegFee;
	private float compTot;
	private float curAprStampDty;
	private float curAprRegFee;
	private float curAprTot;
	private float preAprStampDty;
	private float preAprRegFee;
	private float preAprTot;
	private float compAprStampDty;
	private float compAprRegFee;
	private float compAprTot;
	private float curNoDocStampDty;
	private float curNoDocRegFee;
	private float curNoDocTot;
	private float preNoDocStampDty;
	private float preNoDocRegFee;
	private float preNoDocTot;
	private float compNoDocStampDty;
	private float compNoDocRegFee;
	private float compNoDocTot;
	private float curAprNoDocStampDty;
	private float curAprNoDocRegFee;
	private float curAprNoDocTot;
	private float preAprNoDocStampDty;
	private float preAprNoDocRegFee;
	private float preAprNoDocTot;
	private float compAprNoDocStampDty;
	private float compAprNoDocRegFee;
	private float compAprNoDocTot;
	
	//For Subwise StampDty,Registration Fee Report
	
	private String exemName;
	private int exemNoDoc;
	private float exemStampDty;
	private float exemRegFee;
	private float exemTot;
	private int exemNoDocTot;
	private float exemStampDtyTot;
	private float exemRegFeeTot;
	private float exemTotTot;
	private int aprExemNoDoc;
	private float aprExemStampDty;
	private float aprExemRegFee;
	private float aprExemTot;
	private int aprExemNoDocTot;
	private float aprExemStampDtyTot;
	private float aprExemRegFeeTot;
	private float aprExemTotTot;
	private String bookTypeId;
	
	
	//For Comparative Figures of RevenueReceipts
	private float progTarg;
	private float progAchiev;
	private float percentIncDec;

	//Registered Document Copy Request
	private String regDate;
	private String regUserId;
	private String regSearchParam;
	private String regDocDetails;
	private String regPropOwnerName;
	private int regCount;
	private float regTotal;
	
	//Market Trend Report
	private String noOfDocMT;
	private String deedNameMT;
	private String valuationMT;
	private String revenueMT;
	private String noOfDocPrevMT;
	private String valuationPrevMT;
	private String revenuePrevMT;
	//added by ShreeraJ
	  private String radio;
	  private String wardId;
	  private String wardpatwarId;
	  private String areaTypeId;
	  private String tehisilId;
	  private String distId;
	  private String deedId;
	  private String deedTypeName;	 
	  private String revenue;
	  private String valuation;
	  private String noOfDocComp;
	  private String revenueComp;
	  private String valuationComp;
	  private String totnoofDoc;
	  private String totnoofDocComp; 
	  private String totRev;
	  private String totRevComp;
	  private String subAreaId;
	  private String subAreaName;
	  private String totVal;
	  private String totValComp;
	  private String deedCount;
	  private String YearValue ;
	  private ArrayList distList = new ArrayList();
	  private ArrayList tehsiList = new ArrayList();
   	  private ArrayList areaList = new ArrayList();
   	 /** the value of areaTypeList association */
      private ArrayList subAreaTypeList = new ArrayList();
	  private ArrayList wardList = new ArrayList();
	  private ArrayList mohallaList = new ArrayList();
	  private ArrayList deedTypeList = new ArrayList();
	 
	  private ArrayList market23colList = new ArrayList();
	  private ArrayList market45colList = new ArrayList();
	  private ArrayList dutyList = new ArrayList();
	  private String year;
	
	  
	  
	
	public String getSubAreaId() {
		return subAreaId;
	}
	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}
	public String getSubAreaName() {
		return subAreaName;
	}
	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}
	public ArrayList getSubAreaTypeList() {
		return subAreaTypeList;
	}
	public void setSubAreaTypeList(ArrayList subAreaTypeList) {
		this.subAreaTypeList = subAreaTypeList;
	}
	/**
	 * @return the yearValue
	 */
	public String getYearValue() {
		return YearValue;
	}
	/**
	 * @param yearValue the yearValue to set
	 */
	public void setYearValue(String yearValue) {
		YearValue = yearValue;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the totnoofDoc
	 */
	public String getTotnoofDoc() {
		return totnoofDoc;
	}
	/**
	 * @param totnoofDoc the totnoofDoc to set
	 */
	public void setTotnoofDoc(String totnoofDoc) {
		this.totnoofDoc = totnoofDoc;
	}
	/**
	 * @return the totnoofDocComp
	 */
	public String getTotnoofDocComp() {
		return totnoofDocComp;
	}
	/**
	 * @param totnoofDocComp the totnoofDocComp to set
	 */
	public void setTotnoofDocComp(String totnoofDocComp) {
		this.totnoofDocComp = totnoofDocComp;
	}
	/**
	 * @return the totRev
	 */
	public String getTotRev() {
		return totRev;
	}
	/**
	 * @param totRev the totRev to set
	 */
	public void setTotRev(String totRev) {
		this.totRev = totRev;
	}
	/**
	 * @return the totRevComp
	 */
	public String getTotRevComp() {
		return totRevComp;
	}
	/**
	 * @param totRevComp the totRevComp to set
	 */
	public void setTotRevComp(String totRevComp) {
		this.totRevComp = totRevComp;
	}
	/**
	 * @return the totVal
	 */
	public String getTotVal() {
		return totVal;
	}
	/**
	 * @param totVal the totVal to set
	 */
	public void setTotVal(String totVal) {
		this.totVal = totVal;
	}
	/**
	 * @return the totValComp
	 */
	public String getTotValComp() {
		return totValComp;
	}
	/**
	 * @param totValComp the totValComp to set
	 */
	public void setTotValComp(String totValComp) {
		this.totValComp = totValComp;
	}
	/**
	 * @return the deedCount
	 */
	public String getDeedCount() {
		return deedCount;
	}
	/**
	 * @param deedCount the deedCount to set
	 */
	public void setDeedCount(String deedCount) {
		this.deedCount = deedCount;
	}
	/**
	 * @return the dutyList
	 */
	public ArrayList getDutyList() {
		return dutyList;
	}
	/**
	 * @param dutyList the dutyList to set
	 */
	public void setDutyList(ArrayList dutyList) {
		this.dutyList = dutyList;
	}
	/**
	 * @return the revenueComp
	 */
	public String getRevenueComp() {
		return revenueComp;
	}
	/**
	 * @param revenueComp the revenueComp to set
	 */
	public void setRevenueComp(String revenueComp) {
		this.revenueComp = revenueComp;
	}
	/**
	 * @return the valuationComp
	 */
	public String getValuationComp() {
		return valuationComp;
	}
	/**
	 * @param valuationComp the valuationComp to set
	 */
	public void setValuationComp(String valuationComp) {
		this.valuationComp = valuationComp;
	}
	/**
	 * @return the noOfDocComp
	 */
	public String getNoOfDocComp() {
		return noOfDocComp;
	}
	/**
	 * @param noOfDocComp the noOfDocComp to set
	 */
	public void setNoOfDocComp(String noOfDocComp) {
		this.noOfDocComp = noOfDocComp;
	}
	/**
	 * @return the market45colList
	 */
	public ArrayList getMarket45colList() {
		return market45colList;
	}
	/**
	 * @param market45colList the market45colList to set
	 */
	public void setMarket45colList(ArrayList market45colList) {
		this.market45colList = market45colList;
	}
	/**
	 * @return the revenue
	 */
	public String getRevenue() {
		return revenue;
	}
	/**
	 * @param revenue the revenue to set
	 */
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	/**
	 * @return the valuation
	 */
	public String getValuation() {
		return valuation;
	}
	/**
	 * @param valuation the valuation to set
	 */
	public void setValuation(String valuation) {
		this.valuation = valuation;
	}
	/**
	 * @return the market23colList
	 */
	public ArrayList getMarket23colList() {
		return market23colList;
	}
	/**
	 * @param market23colList the market23colList to set
	 */
	public void setMarket23colList(ArrayList market23colList) {
		this.market23colList = market23colList;
	}
	
	
	/**
	 * @return the deedId
	 */
	public String getDeedId() {
		return deedId;
	}
	/**
	 * @param deedId the deedId to set
	 */
	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}
	/**
	 * @return the deedTypeName
	 */
	public String getDeedTypeName() {
		return deedTypeName;
	}
	/**
	 * @param deedTypeName the deedTypeName to set
	 */
	public void setDeedTypeName(String deedTypeName) {
		this.deedTypeName = deedTypeName;
	}
	/**
	 * @return the deedTypeList
	 */
	public ArrayList getDeedTypeList() {
		return deedTypeList;
	}
	/**
	 * @param deedTypeList the deedTypeList to set
	 */
	public void setDeedTypeList(ArrayList deedTypeList) {
		this.deedTypeList = deedTypeList;
	}
	/**
	 * @return the wardId
	 */
	public String getWardId() {
		return wardId;
	}
	/**
	 * @param wardId the wardId to set
	 */
	public void setWardId(String wardId) {
		this.wardId = wardId;
	}
	/**
	 * @return the wardpatwarId
	 */
	public String getWardpatwarId() {
		return wardpatwarId;
	}
	/**
	 * @param wardpatwarId the wardpatwarId to set
	 */
	public void setWardpatwarId(String wardpatwarId) {
		this.wardpatwarId = wardpatwarId;
	}
	/**
	 * @return the tehisilId
	 */
	public String getTehisilId() {
		return tehisilId;
	}
	/**
	 * @param tehisilId the tehisilId to set
	 */
	public void setTehisilId(String tehisilId) {
		this.tehisilId = tehisilId;
	}
	/**
	 * @return the distId
	 */
	public String getDistId() {
		return distId;
	}
	/**
	 * @param distId the distId to set
	 */
	public void setDistId(String distId) {
		this.distId = distId;
	}
	/**
	 * @return the distList
	 */
	public ArrayList getDistList() {
		return distList;
	}
	/**
	 * @param distList the distList to set
	 */
	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}
	/**
	 * @return the tehsiList
	 */
	public ArrayList getTehsiList() {
		return tehsiList;
	}
	/**
	 * @param tehsiList the tehsiList to set
	 */
	public void setTehsiList(ArrayList tehsiList) {
		this.tehsiList = tehsiList;
	}
	/**
	 * @return the areaList
	 */
	public ArrayList getAreaList() {
		return areaList;
	}
	/**
	 * @param areaList the areaList to set
	 */
	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
	}
	/**
	 * @return the wardList
	 */
	public ArrayList getWardList() {
		return wardList;
	}
	/**
	 * @param wardList the wardList to set
	 */
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}
	/**
	 * @return the mohallaList
	 */
	public ArrayList getMohallaList() {
		return mohallaList;
	}
	/**
	 * @param mohallaList the mohallaList to set
	 */
	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}
	/**
	 * @return the radio
	 */
	public String getRadio() {
		return radio;
	}
	/**
	 * @param radio the radio to set
	 */
	public void setRadio(String radio) {
		this.radio = radio;
	}
	/**
	 * @return the progTarg
	 */
	public float getProgTarg() {
		return progTarg;
	}
	/**
	 * @param progTarg the progTarg to set
	 */
	public void setProgTarg(float progTarg) {
		this.progTarg = progTarg;
	}
	/**
	 * @return the progAchiev
	 */
	public float getProgAchiev() {
		return progAchiev;
	}
	/**
	 * @param progAchiev the progAchiev to set
	 */
	public void setProgAchiev(float progAchiev) {
		this.progAchiev = progAchiev;
	}
	/**
	 * @return the percentIncDec
	 */
	public float getPercentIncDec() {
		return percentIncDec;
	}
	/**
	 * @param percentIncDec the percentIncDec to set
	 */
	public void setPercentIncDec(float percentIncDec) {
		this.percentIncDec = percentIncDec;
	}
	/**
	 * @return the sno
	 */
	public int getSno() {
		return sno;
	}
	/**
	 * @param sno the sno to set
	 */
	public void setSno(int sno) {
		this.sno = sno;
	}
	/**
	 * @return the droName
	 */
	public String getDroName() {
		return droName;
	}
	/**
	 * @param droName the droName to set
	 */
	public void setDroName(String droName) {
		this.droName = droName;
	}
	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}
	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}
	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	/**
	 * @return the officeTypeId
	 */
	public String getOfficeTypeId() {
		return officeTypeId;
	}
	/**
	 * @param officeTypeId the officeTypeId to set
	 */
	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}
	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}
	/**
	 * @param officeName the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	/**
	 * @return the officeId
	 */
	public String getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}
	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	/**
	 * @return the tehsilId
	 */
	public String getTehsilId() {
		return tehsilId;
	}
	/**
	 * @param tehsilId the tehsilId to set
	 */
	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}
	/**
	 * @return the tehsilName
	 */
	public String getTehsilName() {
		return tehsilName;
	}
	/**
	 * @param tehsilName the tehsilName to set
	 */
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	/**
	 * @return the areaTypeId
	 */
	public String getAreaTypeId() {
		return areaTypeId;
	}
	/**
	 * @param areaTypeId the areaTypeId to set
	 */
	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}
	/**
	 * @return the areaTypeName
	 */
	public String getAreaTypeName() {
		return areaTypeName;
	}
	/**
	 * @param areaTypeName the areaTypeName to set
	 */
	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}
	/**
	 * @return the wardPatwariId
	 */
	public String getWardPatwariId() {
		return wardPatwariId;
	}
	/**
	 * @param wardPatwariId the wardPatwariId to set
	 */
	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}
	/**
	 * @return the wardPatwariName
	 */
	public String getWardPatwariName() {
		return wardPatwariName;
	}
	/**
	 * @param wardPatwariName the wardPatwariName to set
	 */
	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}
	/**
	 * @return the docType
	 */
	public String getDocType() {
		return docType;
	}
	/**
	 * @param docType the docType to set
	 */
	public void setDocType(String docType) {
		this.docType = docType;
	}
	
	/**
	 * @return the noOfDoc
	 */
	public String getNoOfDoc() {
		return noOfDoc;
	}
	/**
	 * @param noOfDoc the noOfDoc to set
	 */
	public void setNoOfDoc(String noOfDoc) {
		this.noOfDoc = noOfDoc;
	}
	/**
	 * @return the revReceipt
	 */
	public float getRevReceipt() {
		return revReceipt;
	}
	/**
	 * @param revReceipt the revReceipt to set
	 */
	public void setRevReceipt(float revReceipt) {
		this.revReceipt = revReceipt;
	}
	
	/**
	 * @return the compNoOfDoc
	 */
	public float getCompNoOfDoc() {
		return compNoOfDoc;
	}
	/**
	 * @param compNoOfDoc the compNoOfDoc to set
	 */
	public void setCompNoOfDoc(float compNoOfDoc) {
		this.compNoOfDoc = compNoOfDoc;
	}
	/**
	 * @return the compValuation
	 */
	public float getCompValuation() {
		return compValuation;
	}
	/**
	 * @param compValuation the compValuation to set
	 */
	public void setCompValuation(float compValuation) {
		this.compValuation = compValuation;
	}
	/**
	 * @return the comprevReceipt
	 */
	public float getComprevReceipt() {
		return comprevReceipt;
	}
	/**
	 * @param comprevReceipt the comprevReceipt to set
	 */
	public void setComprevReceipt(float comprevReceipt) {
		this.comprevReceipt = comprevReceipt;
	}
	/**
	 * @return the effFactorVal
	 */
	public String getEffFactorVal() {
		return effFactorVal;
	}
	/**
	 * @param effFactorVal the effFactorVal to set
	 */
	public void setEffFactorVal(String effFactorVal) {
		this.effFactorVal = effFactorVal;
	}
	/**
	 * @return the factorName
	 */
	public String getFactorName() {
		return factorName;
	}
	/**
	 * @param factorName the factorName to set
	 */
	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	/**
	 * @return the facValIncDec
	 */
	public String getFacValIncDec() {
		return facValIncDec;
	}
	/**
	 * @param facValIncDec the facValIncDec to set
	 */
	public void setFacValIncDec(String facValIncDec) {
		this.facValIncDec = facValIncDec;
	}
	/**
	 * @return the effFacDoc
	 */
	public float getEffFacDoc() {
		return effFacDoc;
	}
	/**
	 * @param effFacDoc the effFacDoc to set
	 */
	public void setEffFacDoc(float effFacDoc) {
		this.effFacDoc = effFacDoc;
	}
	/**
	 * @return the effFacReceipt
	 */
	public float getEffFacReceipt() {
		return effFacReceipt;
	}
	/**
	 * @param effFacReceipt the effFacReceipt to set
	 */
	public void setEffFacReceipt(float effFacReceipt) {
		this.effFacReceipt = effFacReceipt;
	}
	/**
	 * @return the effFacValuation
	 */
	public float getEffFacValuation() {
		return effFacValuation;
	}
	/**
	 * @param effFacValuation the effFacValuation to set
	 */
	public void setEffFacValuation(float effFacValuation) {
		this.effFacValuation = effFacValuation;
	}
	/**
	 * @return the sroName
	 */
	public String getSroName() {
		return sroName;
	}
	/**
	 * @param sroName the sroName to set
	 */
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}
	/**
	 * @return the curMonStampDty
	 */
	public float getCurMonStampDty() {
		return curMonStampDty;
	}
	/**
	 * @param curMonStampDty the curMonStampDty to set
	 */
	public void setCurMonStampDty(float curMonStampDty) {
		this.curMonStampDty = curMonStampDty;
	}
	/**
	 * @return the curMonRegFee
	 */
	public float getCurMonRegFee() {
		return curMonRegFee;
	}
	/**
	 * @param curMonRegFee the curMonRegFee to set
	 */
	public void setCurMonRegFee(float curMonRegFee) {
		this.curMonRegFee = curMonRegFee;
	}
	/**
	 * @return the curMonTot
	 */
	public float getCurMonTot() {
		return curMonTot;
	}
	/**
	 * @param curMonTot the curMonTot to set
	 */
	public void setCurMonTot(float curMonTot) {
		this.curMonTot = curMonTot;
	}
	/**
	 * @return the preMonStampDty
	 */
	public float getPreMonStampDty() {
		return preMonStampDty;
	}
	/**
	 * @param preMonStampDty the preMonStampDty to set
	 */
	public void setPreMonStampDty(float preMonStampDty) {
		this.preMonStampDty = preMonStampDty;
	}
	/**
	 * @return the preMonRegFee
	 */
	public float getPreMonRegFee() {
		return preMonRegFee;
	}
	/**
	 * @param preMonRegFee the preMonRegFee to set
	 */
	public void setPreMonRegFee(float preMonRegFee) {
		this.preMonRegFee = preMonRegFee;
	}
	/**
	 * @return the preMonTot
	 */
	public float getPreMonTot() {
		return preMonTot;
	}
	/**
	 * @param preMonTot the preMonTot to set
	 */
	public void setPreMonTot(float preMonTot) {
		this.preMonTot = preMonTot;
	}
	/**
	 * @return the compStampDty
	 */
	public float getCompStampDty() {
		return compStampDty;
	}
	/**
	 * @param compStampDty the compStampDty to set
	 */
	public void setCompStampDty(float compStampDty) {
		this.compStampDty = compStampDty;
	}
	/**
	 * @return the compRegFee
	 */
	public float getCompRegFee() {
		return compRegFee;
	}
	/**
	 * @param compRegFee the compRegFee to set
	 */
	public void setCompRegFee(float compRegFee) {
		this.compRegFee = compRegFee;
	}
	/**
	 * @return the compTot
	 */
	public float getCompTot() {
		return compTot;
	}
	/**
	 * @param compTot the compTot to set
	 */
	public void setCompTot(float compTot) {
		this.compTot = compTot;
	}
	/**
	 * @return the curAprStampDty
	 */
	public float getCurAprStampDty() {
		return curAprStampDty;
	}
	/**
	 * @param curAprStampDty the curAprStampDty to set
	 */
	public void setCurAprStampDty(float curAprStampDty) {
		this.curAprStampDty = curAprStampDty;
	}
	/**
	 * @return the curAprRegFee
	 */
	public float getCurAprRegFee() {
		return curAprRegFee;
	}
	/**
	 * @param curAprRegFee the curAprRegFee to set
	 */
	public void setCurAprRegFee(float curAprRegFee) {
		this.curAprRegFee = curAprRegFee;
	}
	/**
	 * @return the curAprTot
	 */
	public float getCurAprTot() {
		return curAprTot;
	}
	/**
	 * @param curAprTot the curAprTot to set
	 */
	public void setCurAprTot(float curAprTot) {
		this.curAprTot = curAprTot;
	}
	/**
	 * @return the preAprStampDty
	 */
	public float getPreAprStampDty() {
		return preAprStampDty;
	}
	/**
	 * @param preAprStampDty the preAprStampDty to set
	 */
	public void setPreAprStampDty(float preAprStampDty) {
		this.preAprStampDty = preAprStampDty;
	}
	/**
	 * @return the preAprRegFee
	 */
	public float getPreAprRegFee() {
		return preAprRegFee;
	}
	/**
	 * @param preAprRegFee the preAprRegFee to set
	 */
	public void setPreAprRegFee(float preAprRegFee) {
		this.preAprRegFee = preAprRegFee;
	}
	/**
	 * @return the preAprTot
	 */
	public float getPreAprTot() {
		return preAprTot;
	}
	/**
	 * @param preAprTot the preAprTot to set
	 */
	public void setPreAprTot(float preAprTot) {
		this.preAprTot = preAprTot;
	}
	/**
	 * @return the compAprStampDty
	 */
	public float getCompAprStampDty() {
		return compAprStampDty;
	}
	/**
	 * @param compAprStampDty the compAprStampDty to set
	 */
	public void setCompAprStampDty(float compAprStampDty) {
		this.compAprStampDty = compAprStampDty;
	}
	/**
	 * @return the compAprRegFee
	 */
	public float getCompAprRegFee() {
		return compAprRegFee;
	}
	/**
	 * @param compAprRegFee the compAprRegFee to set
	 */
	public void setCompAprRegFee(float compAprRegFee) {
		this.compAprRegFee = compAprRegFee;
	}
	/**
	 * @return the compAprTot
	 */
	public float getCompAprTot() {
		return compAprTot;
	}
	/**
	 * @param compAprTot the compAprTot to set
	 */
	public void setCompAprTot(float compAprTot) {
		this.compAprTot = compAprTot;
	}
	/**
	 * @return the curNoDocStampDty
	 */
	public float getCurNoDocStampDty() {
		return curNoDocStampDty;
	}
	/**
	 * @param curNoDocStampDty the curNoDocStampDty to set
	 */
	public void setCurNoDocStampDty(float curNoDocStampDty) {
		this.curNoDocStampDty = curNoDocStampDty;
	}
	/**
	 * @return the curNoDocRegFee
	 */
	public float getCurNoDocRegFee() {
		return curNoDocRegFee;
	}
	/**
	 * @param curNoDocRegFee the curNoDocRegFee to set
	 */
	public void setCurNoDocRegFee(float curNoDocRegFee) {
		this.curNoDocRegFee = curNoDocRegFee;
	}
	/**
	 * @return the curNoDocTot
	 */
	public float getCurNoDocTot() {
		return curNoDocTot;
	}
	/**
	 * @param curNoDocTot the curNoDocTot to set
	 */
	public void setCurNoDocTot(float curNoDocTot) {
		this.curNoDocTot = curNoDocTot;
	}
	/**
	 * @return the preNoDocStampDty
	 */
	public float getPreNoDocStampDty() {
		return preNoDocStampDty;
	}
	/**
	 * @param preNoDocStampDty the preNoDocStampDty to set
	 */
	public void setPreNoDocStampDty(float preNoDocStampDty) {
		this.preNoDocStampDty = preNoDocStampDty;
	}
	/**
	 * @return the preNoDocRegFee
	 */
	public float getPreNoDocRegFee() {
		return preNoDocRegFee;
	}
	/**
	 * @param preNoDocRegFee the preNoDocRegFee to set
	 */
	public void setPreNoDocRegFee(float preNoDocRegFee) {
		this.preNoDocRegFee = preNoDocRegFee;
	}
	/**
	 * @return the preNoDocTot
	 */
	public float getPreNoDocTot() {
		return preNoDocTot;
	}
	/**
	 * @param preNoDocTot the preNoDocTot to set
	 */
	public void setPreNoDocTot(float preNoDocTot) {
		this.preNoDocTot = preNoDocTot;
	}
	/**
	 * @return the compNoDocStampDty
	 */
	public float getCompNoDocStampDty() {
		return compNoDocStampDty;
	}
	/**
	 * @param compNoDocStampDty the compNoDocStampDty to set
	 */
	public void setCompNoDocStampDty(float compNoDocStampDty) {
		this.compNoDocStampDty = compNoDocStampDty;
	}
	/**
	 * @return the compNoDocRegFee
	 */
	public float getCompNoDocRegFee() {
		return compNoDocRegFee;
	}
	/**
	 * @param compNoDocRegFee the compNoDocRegFee to set
	 */
	public void setCompNoDocRegFee(float compNoDocRegFee) {
		this.compNoDocRegFee = compNoDocRegFee;
	}
	/**
	 * @return the compNoDocTot
	 */
	public float getCompNoDocTot() {
		return compNoDocTot;
	}
	/**
	 * @param compNoDocTot the compNoDocTot to set
	 */
	public void setCompNoDocTot(float compNoDocTot) {
		this.compNoDocTot = compNoDocTot;
	}
	/**
	 * @return the curAprNoDocStampDty
	 */
	public float getCurAprNoDocStampDty() {
		return curAprNoDocStampDty;
	}
	/**
	 * @param curAprNoDocStampDty the curAprNoDocStampDty to set
	 */
	public void setCurAprNoDocStampDty(float curAprNoDocStampDty) {
		this.curAprNoDocStampDty = curAprNoDocStampDty;
	}
	/**
	 * @return the curAprNoDocRegFee
	 */
	public float getCurAprNoDocRegFee() {
		return curAprNoDocRegFee;
	}
	/**
	 * @param curAprNoDocRegFee the curAprNoDocRegFee to set
	 */
	public void setCurAprNoDocRegFee(float curAprNoDocRegFee) {
		this.curAprNoDocRegFee = curAprNoDocRegFee;
	}
	/**
	 * @return the curAprNoDocTot
	 */
	public float getCurAprNoDocTot() {
		return curAprNoDocTot;
	}
	/**
	 * @param curAprNoDocTot the curAprNoDocTot to set
	 */
	public void setCurAprNoDocTot(float curAprNoDocTot) {
		this.curAprNoDocTot = curAprNoDocTot;
	}
	/**
	 * @return the preAprNoDocStampDty
	 */
	public float getPreAprNoDocStampDty() {
		return preAprNoDocStampDty;
	}
	/**
	 * @param preAprNoDocStampDty the preAprNoDocStampDty to set
	 */
	public void setPreAprNoDocStampDty(float preAprNoDocStampDty) {
		this.preAprNoDocStampDty = preAprNoDocStampDty;
	}
	/**
	 * @return the preAprNoDocRegFee
	 */
	public float getPreAprNoDocRegFee() {
		return preAprNoDocRegFee;
	}
	/**
	 * @param preAprNoDocRegFee the preAprNoDocRegFee to set
	 */
	public void setPreAprNoDocRegFee(float preAprNoDocRegFee) {
		this.preAprNoDocRegFee = preAprNoDocRegFee;
	}
	/**
	 * @return the preAprNoDocTot
	 */
	public float getPreAprNoDocTot() {
		return preAprNoDocTot;
	}
	/**
	 * @param preAprNoDocTot the preAprNoDocTot to set
	 */
	public void setPreAprNoDocTot(float preAprNoDocTot) {
		this.preAprNoDocTot = preAprNoDocTot;
	}
	/**
	 * @return the compAprNoDocStampDty
	 */
	public float getCompAprNoDocStampDty() {
		return compAprNoDocStampDty;
	}
	/**
	 * @param compAprNoDocStampDty the compAprNoDocStampDty to set
	 */
	public void setCompAprNoDocStampDty(float compAprNoDocStampDty) {
		this.compAprNoDocStampDty = compAprNoDocStampDty;
	}
	/**
	 * @return the compAprNoDocRegFee
	 */
	public float getCompAprNoDocRegFee() {
		return compAprNoDocRegFee;
	}
	/**
	 * @param compAprNoDocRegFee the compAprNoDocRegFee to set
	 */
	public void setCompAprNoDocRegFee(float compAprNoDocRegFee) {
		this.compAprNoDocRegFee = compAprNoDocRegFee;
	}
	/**
	 * @return the compAprNoDocTot
	 */
	public float getCompAprNoDocTot() {
		return compAprNoDocTot;
	}
	/**
	 * @param compAprNoDocTot the compAprNoDocTot to set
	 */
	public void setCompAprNoDocTot(float compAprNoDocTot) {
		this.compAprNoDocTot = compAprNoDocTot;
	}
	/**
	 * @return the exemNoDoc
	 */
	public int getExemNoDoc() {
		return exemNoDoc;
	}
	/**
	 * @param exemNoDoc the exemNoDoc to set
	 */
	public void setExemNoDoc(int exemNoDoc) {
		this.exemNoDoc = exemNoDoc;
	}
	/**
	 * @return the exemStampDty
	 */
	public float getExemStampDty() {
		return exemStampDty;
	}
	/**
	 * @param exemStampDty the exemStampDty to set
	 */
	public void setExemStampDty(float exemStampDty) {
		this.exemStampDty = exemStampDty;
	}
	/**
	 * @return the exemRegFee
	 */
	public float getExemRegFee() {
		return exemRegFee;
	}
	/**
	 * @param exemRegFee the exemRegFee to set
	 */
	public void setExemRegFee(float exemRegFee) {
		this.exemRegFee = exemRegFee;
	}
	/**
	 * @return the exemTot
	 */
	public float getExemTot() {
		return exemTot;
	}
	/**
	 * @param exemTot the exemTot to set
	 */
	public void setExemTot(float exemTot) {
		this.exemTot = exemTot;
	}
	/**
	 * @return the aprExemNoDoc
	 */
	public int getAprExemNoDoc() {
		return aprExemNoDoc;
	}
	/**
	 * @param aprExemNoDoc the aprExemNoDoc to set
	 */
	public void setAprExemNoDoc(int aprExemNoDoc) {
		this.aprExemNoDoc = aprExemNoDoc;
	}
	/**
	 * @return the aprExemStampDty
	 */
	public float getAprExemStampDty() {
		return aprExemStampDty;
	}
	/**
	 * @param aprExemStampDty the aprExemStampDty to set
	 */
	public void setAprExemStampDty(float aprExemStampDty) {
		this.aprExemStampDty = aprExemStampDty;
	}
	/**
	 * @return the aprExemRegFee
	 */
	public float getAprExemRegFee() {
		return aprExemRegFee;
	}
	/**
	 * @param aprExemRegFee the aprExemRegFee to set
	 */
	public void setAprExemRegFee(float aprExemRegFee) {
		this.aprExemRegFee = aprExemRegFee;
	}
	/**
	 * @return the aprExemTot
	 */
	public float getAprExemTot() {
		return aprExemTot;
	}
	/**
	 * @param aprExemTot the aprExemTot to set
	 */
	public void setAprExemTot(float aprExemTot) {
		this.aprExemTot = aprExemTot;
	}
	/**
	 * @return the exemNoDocTot
	 */
	public int getExemNoDocTot() {
		return exemNoDocTot;
	}
	/**
	 * @param exemNoDocTot the exemNoDocTot to set
	 */
	public void setExemNoDocTot(int exemNoDocTot) {
		this.exemNoDocTot = exemNoDocTot;
	}
	/**
	 * @return the exemStampDtyTot
	 */
	public float getExemStampDtyTot() {
		return exemStampDtyTot;
	}
	/**
	 * @param exemStampDtyTot the exemStampDtyTot to set
	 */
	public void setExemStampDtyTot(float exemStampDtyTot) {
		this.exemStampDtyTot = exemStampDtyTot;
	}
	/**
	 * @return the exemRegFeeTot
	 */
	public float getExemRegFeeTot() {
		return exemRegFeeTot;
	}
	/**
	 * @param exemRegFeeTot the exemRegFeeTot to set
	 */
	public void setExemRegFeeTot(float exemRegFeeTot) {
		this.exemRegFeeTot = exemRegFeeTot;
	}
	/**
	 * @return the exemTotTot
	 */
	public float getExemTotTot() {
		return exemTotTot;
	}
	/**
	 * @param exemTotTot the exemTotTot to set
	 */
	public void setExemTotTot(float exemTotTot) {
		this.exemTotTot = exemTotTot;
	}
	/**
	 * @return the aprExemNoDocTot
	 */
	public int getAprExemNoDocTot() {
		return aprExemNoDocTot;
	}
	/**
	 * @param aprExemNoDocTot the aprExemNoDocTot to set
	 */
	public void setAprExemNoDocTot(int aprExemNoDocTot) {
		this.aprExemNoDocTot = aprExemNoDocTot;
	}
	/**
	 * @return the aprExemStampDtyTot
	 */
	public float getAprExemStampDtyTot() {
		return aprExemStampDtyTot;
	}
	/**
	 * @param aprExemStampDtyTot the aprExemStampDtyTot to set
	 */
	public void setAprExemStampDtyTot(float aprExemStampDtyTot) {
		this.aprExemStampDtyTot = aprExemStampDtyTot;
	}
	/**
	 * @return the aprExemRegFeeTot
	 */
	public float getAprExemRegFeeTot() {
		return aprExemRegFeeTot;
	}
	/**
	 * @param aprExemRegFeeTot the aprExemRegFeeTot to set
	 */
	public void setAprExemRegFeeTot(float aprExemRegFeeTot) {
		this.aprExemRegFeeTot = aprExemRegFeeTot;
	}
	/**
	 * @return the aprExemTotTot
	 */
	public float getAprExemTotTot() {
		return aprExemTotTot;
	}
	/**
	 * @param aprExemTotTot the aprExemTotTot to set
	 */
	public void setAprExemTotTot(float aprExemTotTot) {
		this.aprExemTotTot = aprExemTotTot;
	}
	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}
	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	/**
	 * @return the regUser
	 */
	public String getRegUserId() {
		return regUserId;
	}
	/**
	 * @param regUser the regUser to set
	 */
	public void setRegUserId(String regUser) {
		this.regUserId = regUser;
	}
	/**
	 * @return the regSearchParam
	 */
	public String getRegSearchParam() {
		return regSearchParam;
	}
	/**
	 * @param regSearchParam the regSearchParam to set
	 */
	public void setRegSearchParam(String regSearchParam) {
		this.regSearchParam = regSearchParam;
	}
	/**
	 * @return the regDocDetails
	 */
	public String getRegDocDetails() {
		return regDocDetails;
	}
	/**
	 * @param regDocDetails the regDocDetails to set
	 */
	public void setRegDocDetails(String regDocDetails) {
		this.regDocDetails = regDocDetails;
	}
	/**
	 * @return the regPropOwnerName
	 */
	public String getRegPropOwnerName() {
		return regPropOwnerName;
	}
	/**
	 * @param regPropOwnerName the regPropOwnerName to set
	 */
	public void setRegPropOwnerName(String regPropOwnerName) {
		this.regPropOwnerName = regPropOwnerName;
	}
	/**
	 * @return the regCount
	 */
	public int getRegCount() {
		return regCount;
	}
	/**
	 * @param regCount the regCount to set
	 */
	public void setRegCount(int regCount) {
		this.regCount = regCount;
	}
	/**
	 * @return the regTotal
	 */
	public float getRegTotal() {
		return regTotal;
	}
	/**
	 * @param regTotal the regTotal to set
	 */
	public void setRegTotal(float regTotal) {
		this.regTotal = regTotal;
	}
	/**
	 * @return the exemName
	 */
	public String getExemName() {
		return exemName;
	}
	/**
	 * @param exemName the exemName to set
	 */
	public void setExemName(String exemName) {
		this.exemName = exemName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getMohallaId() {
		return mohallaId;
	}
	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}
	public String getMohallaName() {
		return mohallaName;
	}
	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}
	public String getNoOfDocMT() {
		return noOfDocMT;
	}
	public void setNoOfDocMT(String noOfDocMT) {
		this.noOfDocMT = noOfDocMT;
	}
	public String getDeedNameMT() {
		return deedNameMT;
	}
	public void setDeedNameMT(String deedNameMT) {
		this.deedNameMT = deedNameMT;
	}
	public String getValuationMT() {
		return valuationMT;
	}
	public void setValuationMT(String valuationMT) {
		this.valuationMT = valuationMT;
	}
	public String getRevenueMT() {
		return revenueMT;
	}
	public void setRevenueMT(String revenueMT) {
		this.revenueMT = revenueMT;
	}
	public String getNoOfDocPrevMT() {
		return noOfDocPrevMT;
	}
	public void setNoOfDocPrevMT(String noOfDocPrevMT) {
		this.noOfDocPrevMT = noOfDocPrevMT;
	}
	 
	public String getValuationPrevMT() {
		return valuationPrevMT;
	}
	public void setValuationPrevMT(String valuationPrevMT) {
		this.valuationPrevMT = valuationPrevMT;
	}
	public String getRevenuePrevMT() {
		return revenuePrevMT;
	}
	public void setRevenuePrevMT(String revenuePrevMT) {
		this.revenuePrevMT = revenuePrevMT;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
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
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(String bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	
	

}
