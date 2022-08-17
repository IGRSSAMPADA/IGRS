
package com.wipro.igrs.report.dto;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * 
 * @author madhaty
 * @see ReportDTO class is used for Revenue Proejction Report
 */
public class ReportDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int sno;
	private String createdBy;
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	private String radioSearch;
	private String fromDate;
	private String toDate;
	private String month;
	private String userType;
	private String districtId;
	private String districtName;
	private String toYear;
	private String fromYear;
	private String years;
	
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	
	public String getToYear() {
		return toYear;
	}
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}
	public String getFromYear() {
		return fromYear;
	}
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}
	private String totSum;
	public String getTotSum() {
		return totSum;
	}
	public void setTotSum(String totSum) {
		this.totSum = totSum;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private String officeId;
	private String stateId;
	private String tehsilId;
	private String tehsilName;
	private String areaTypeId;
	private String areaTypeName;
	private String wardPatwariId;
	private String wardPatwariName;
	
	//For Revenue Projection Report
	private String docType;
	private String noOfDoc;
	private String revReceipt;
	private String valuation;
	private String compNoOfDoc;
	private String compValuation;
	private String comprevReceipt;
	private String effFactorVal;
	private String factorName;
	private String facValIncDec;
	private String effFacDoc;
	private String effFacReceipt;
	private String effFacValuation;
	
	//For SRO wise StampDty,Registration Fee Report
	private String sroName;
	private String curMonStampDty;
	private String curMonRegFee;
	private String curMonTot;
	private String preMonStampDty;
	private String preMonRegFee;
	private String preMonTot;
	private String compStampDty;
	private String compRegFee;
	private String compTot;
	private String curNoDocStampDty;
	private String curNoDocRegFee;
	private String curNoDocTot;
	private String preNoDocStampDty;
	private String preNoDocRegFee;
	private String preNoDocTot;
	private String compNoDocStampDty;
	private String compNoDocRegFee;
	private String compNoDocTot;
	
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
	
	// Added by rachita for comparative reports
	private ArrayList viewList = new ArrayList();
	public ArrayList getViewList() {
		return viewList;
	}
	public void setViewList(ArrayList viewList) {
		this.viewList = viewList;
	}
	private String aprTarget="0.0";
	private String mayTarget="0.0";
	private String junTarget="0.0";
	private String julTarget="0.0";
	private String augTarget="0.0";
	private String sepTarget="0.0";
	private String octTarget="0.0";
	private String novTarget="0.0";
	private String decTarget="0.0";
	private String janTarget="0.0";
	private String febTarget="0.0";
	private String marTarget="0.0";
	private String totalTarget="0.0";
	private String totalTargetComp="0.0%";
	private ArrayList viewTargetList = new ArrayList();
	
	
	public ArrayList getViewTargetList() {
		return viewTargetList;
	}
	public void setViewTargetList(ArrayList viewTargetList) {
		this.viewTargetList = viewTargetList;
	}
	public String getTotalTarget() {
		return totalTarget;
	}
	public void setTotalTarget(String totalTarget) {
		this.totalTarget = totalTarget;
	}
	public String getTotalTargetComp() {
		return totalTargetComp;
	}
	public void setTotalTargetComp(String totalTargetComp) {
		this.totalTargetComp = totalTargetComp;
	}
	public String getAprTargetC() {
		return aprTargetC;
	}
	public void setAprTargetC(String aprTargetC) {
		this.aprTargetC = aprTargetC;
	}
	public String getMayTargetC() {
		return mayTargetC;
	}
	public void setMayTargetC(String mayTargetC) {
		this.mayTargetC = mayTargetC;
	}
	public String getJunTargetC() {
		return junTargetC;
	}
	public void setJunTargetC(String junTargetC) {
		this.junTargetC = junTargetC;
	}
	public String getJulTargetC() {
		return julTargetC;
	}
	public void setJulTargetC(String julTargetC) {
		this.julTargetC = julTargetC;
	}
	public String getAugTargetC() {
		return augTargetC;
	}
	public void setAugTargetC(String augTargetC) {
		this.augTargetC = augTargetC;
	}
	public String getSepTargetC() {
		return sepTargetC;
	}
	public void setSepTargetC(String sepTargetC) {
		this.sepTargetC = sepTargetC;
	}
	public String getOctTargetC() {
		return octTargetC;
	}
	public void setOctTargetC(String octTargetC) {
		this.octTargetC = octTargetC;
	}
	public String getNovTargetC() {
		return novTargetC;
	}
	public void setNovTargetC(String novTargetC) {
		this.novTargetC = novTargetC;
	}
	public String getDecTargetC() {
		return decTargetC;
	}
	public void setDecTargetC(String decTargetC) {
		this.decTargetC = decTargetC;
	}
	public String getJanTargetC() {
		return janTargetC;
	}
	public void setJanTargetC(String janTargetC) {
		this.janTargetC = janTargetC;
	}
	public String getFebTargetC() {
		return febTargetC;
	}
	public void setFebTargetC(String febTargetC) {
		this.febTargetC = febTargetC;
	}
	public String getMarTargetC() {
		return marTargetC;
	}
	public void setMarTargetC(String marTargetC) {
		this.marTargetC = marTargetC;
	}
	private String aprTargetC;
	private String mayTargetC;
	private String junTargetC;
	private String julTargetC;
	private String augTargetC;
	private String sepTargetC;
	private String octTargetC;
	private String novTargetC;
	private String decTargetC;
	private String janTargetC;
	private String febTargetC;
	private String marTargetC;
	
	private String aprAchivement;
	private String mayAchivement;
	private String junAchivement;
	private String julAchivement;
	private String augAchivement;
	private String sepAchivement;
	private String octAchivement;
	private String novAchivement;
	private String decAchivement;
	private String janAchivement;
	private String febAchivement;
	private String marAchivement;
	
	
	
	public String getAprAchivement() {
		return aprAchivement;
	}
	public void setAprAchivement(String aprAchivement) {
		this.aprAchivement = aprAchivement;
	}
	public String getMayAchivement() {
		return mayAchivement;
	}
	public void setMayAchivement(String mayAchivement) {
		this.mayAchivement = mayAchivement;
	}
	public String getJunAchivement() {
		return junAchivement;
	}
	public void setJunAchivement(String junAchivement) {
		this.junAchivement = junAchivement;
	}
	public String getJulAchivement() {
		return julAchivement;
	}
	public void setJulAchivement(String julAchivement) {
		this.julAchivement = julAchivement;
	}
	public String getAugAchivement() {
		return augAchivement;
	}
	public void setAugAchivement(String augAchivement) {
		this.augAchivement = augAchivement;
	}
	public String getSepAchivement() {
		return sepAchivement;
	}
	public void setSepAchivement(String sepAchivement) {
		this.sepAchivement = sepAchivement;
	}
	public String getOctAchivement() {
		return octAchivement;
	}
	public void setOctAchivement(String octAchivement) {
		this.octAchivement = octAchivement;
	}
	public String getNovAchivement() {
		return novAchivement;
	}
	public void setNovAchivement(String novAchivement) {
		this.novAchivement = novAchivement;
	}
	public String getDecAchivement() {
		return decAchivement;
	}
	public void setDecAchivement(String decAchivement) {
		this.decAchivement = decAchivement;
	}
	public String getJanAchivement() {
		return janAchivement;
	}
	public void setJanAchivement(String janAchivement) {
		this.janAchivement = janAchivement;
	}
	public String getFebAchivement() {
		return febAchivement;
	}
	public void setFebAchivement(String febAchivement) {
		this.febAchivement = febAchivement;
	}
	public String getMarAchivement() {
		return marAchivement;
	}
	public void setMarAchivement(String marAchivement) {
		this.marAchivement = marAchivement;
	}
	
	
	
	
	
	
	public String getAprTarget() {
		return aprTarget;
	}
	public void setAprTarget(String aprTarget) {
		this.aprTarget = aprTarget;
	}
	public String getMayTarget() {
		return mayTarget;
	}
	public void setMayTarget(String mayTarget) {
		this.mayTarget = mayTarget;
	}
	public String getJunTarget() {
		return junTarget;
	}
	public void setJunTarget(String junTarget) {
		this.junTarget = junTarget;
	}
	public String getJulTarget() {
		return julTarget;
	}
	public void setJulTarget(String julTarget) {
		this.julTarget = julTarget;
	}
	public String getAugTarget() {
		return augTarget;
	}
	public void setAugTarget(String augTarget) {
		this.augTarget = augTarget;
	}
	public String getSepTarget() {
		return sepTarget;
	}
	public void setSepTarget(String sepTarget) {
		this.sepTarget = sepTarget;
	}
	public String getOctTarget() {
		return octTarget;
	}
	public void setOctTarget(String octTarget) {
		this.octTarget = octTarget;
	}
	public String getNovTarget() {
		return novTarget;
	}
	public void setNovTarget(String novTarget) {
		this.novTarget = novTarget;
	}
	public String getDecTarget() {
		return decTarget;
	}
	public void setDecTarget(String decTarget) {
		this.decTarget = decTarget;
	}
	public String getJanTarget() {
		return janTarget;
	}
	public void setJanTarget(String janTarget) {
		this.janTarget = janTarget;
	}
	public String getFebTarget() {
		return febTarget;
	}
	public void setFebTarget(String febTarget) {
		this.febTarget = febTarget;
	}
	public String getMarTarget() {
		return marTarget;
	}
	public void setMarTarget(String marTarget) {
		this.marTarget = marTarget;
	}
	public int getAprExemNoDoc() {
		return aprExemNoDoc;
	}
	public void setAprExemNoDoc(int aprExemNoDoc) {
		this.aprExemNoDoc = aprExemNoDoc;
	}
	public float getAprExemStampDty() {
		return aprExemStampDty;
	}
	public void setAprExemStampDty(float aprExemStampDty) {
		this.aprExemStampDty = aprExemStampDty;
	}
	public float getAprExemRegFee() {
		return aprExemRegFee;
	}
	public void setAprExemRegFee(float aprExemRegFee) {
		this.aprExemRegFee = aprExemRegFee;
	}
	public float getAprExemTot() {
		return aprExemTot;
	}
	public void setAprExemTot(float aprExemTot) {
		this.aprExemTot = aprExemTot;
	}
	public int getAprExemNoDocTot() {
		return aprExemNoDocTot;
	}
	public void setAprExemNoDocTot(int aprExemNoDocTot) {
		this.aprExemNoDocTot = aprExemNoDocTot;
	}
	public float getAprExemStampDtyTot() {
		return aprExemStampDtyTot;
	}
	public void setAprExemStampDtyTot(float aprExemStampDtyTot) {
		this.aprExemStampDtyTot = aprExemStampDtyTot;
	}
	public float getAprExemRegFeeTot() {
		return aprExemRegFeeTot;
	}
	public void setAprExemRegFeeTot(float aprExemRegFeeTot) {
		this.aprExemRegFeeTot = aprExemRegFeeTot;
	}
	public float getAprExemTotTot() {
		return aprExemTotTot;
	}
	public void setAprExemTotTot(float aprExemTotTot) {
		this.aprExemTotTot = aprExemTotTot;
	}
	//For Comparative Figures of RevenueReceipts
	private String droId;
	private String droName;
	private String fiscalYear;
	private String year;
	private String yearValue;
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
	private String fiscalId;
	private String progMonth;
	private float progAchiev;
	private float percentIncDecInAchiev;
	private float progTarg;
	private float progTargTot;
	private float percentIncDecInTarget;
	

	//Registered Document Copy Request
	private String regUserId;
	private String regDocDetails;
	private String regPropOwnerName;
	private int regCount;
	private float regTotal;
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
	public String getCurMonStampDty() {
		return curMonStampDty;
	}
	/**
	 * @param curMonStampDty the curMonStampDty to set
	 */
	public void setCurMonStampDty(String curMonStampDty) {
		this.curMonStampDty = curMonStampDty;
	}
	/**
	 * @return the curMonRegFee
	 */
	public String getCurMonRegFee() {
		return curMonRegFee;
	}
	/**
	 * @param curMonRegFee the curMonRegFee to set
	 */
	public void setCurMonRegFee(String curMonRegFee) {
		this.curMonRegFee = curMonRegFee;
	}
	/**
	 * @return the curMonTot
	 */
	public String getCurMonTot() {
		return curMonTot;
	}
	/**
	 * @param curMonTot the curMonTot to set
	 */
	public void setCurMonTot(String curMonTot) {
		this.curMonTot = curMonTot;
	}
	/**
	 * @return the preMonStampDty
	 */
	public String getPreMonStampDty() {
		return preMonStampDty;
	}
	/**
	 * @param preMonStampDty the preMonStampDty to set
	 */
	public void setPreMonStampDty(String preMonStampDty) {
		this.preMonStampDty = preMonStampDty;
	}
	/**
	 * @return the preMonRegFee
	 */
	public String getPreMonRegFee() {
		return preMonRegFee;
	}
	/**
	 * @param preMonRegFee the preMonRegFee to set
	 */
	public void setPreMonRegFee(String preMonRegFee) {
		this.preMonRegFee = preMonRegFee;
	}
	/**
	 * @return the preMonTot
	 */
	public String getPreMonTot() {
		return preMonTot;
	}
	/**
	 * @param preMonTot the preMonTot to set
	 */
	public void setPreMonTot(String preMonTot) {
		this.preMonTot = preMonTot;
	}
	/**
	 * @return the compStampDty
	 */
	public String getCompStampDty() {
		return compStampDty;
	}
	/**
	 * @param compStampDty the compStampDty to set
	 */
	public void setCompStampDty(String compStampDty) {
		this.compStampDty = compStampDty;
	}
	/**
	 * @return the compRegFee
	 */
	public String getCompRegFee() {
		return compRegFee;
	}
	/**
	 * @param compRegFee the compRegFee to set
	 */
	public void setCompRegFee(String compRegFee) {
		this.compRegFee = compRegFee;
	}
	/**
	 * @return the compTot
	 */
	public String getCompTot() {
		return compTot;
	}
	/**
	 * @param compTot the compTot to set
	 */
	public void setCompTot(String compTot) {
		this.compTot = compTot;
	}
	/**
	 * @return the curNoDocStampDty
	 */
	public String getCurNoDocStampDty() {
		return curNoDocStampDty;
	}
	/**
	 * @param curNoDocStampDty the curNoDocStampDty to set
	 */
	public void setCurNoDocStampDty(String curNoDocStampDty) {
		this.curNoDocStampDty = curNoDocStampDty;
	}
	/**
	 * @return the curNoDocRegFee
	 */
	public String getCurNoDocRegFee() {
		return curNoDocRegFee;
	}
	/**
	 * @param curNoDocRegFee the curNoDocRegFee to set
	 */
	public void setCurNoDocRegFee(String curNoDocRegFee) {
		this.curNoDocRegFee = curNoDocRegFee;
	}
	/**
	 * @return the curNoDocTot
	 */
	public String getCurNoDocTot() {
		return curNoDocTot;
	}
	/**
	 * @param curNoDocTot the curNoDocTot to set
	 */
	public void setCurNoDocTot(String curNoDocTot) {
		this.curNoDocTot = curNoDocTot;
	}
	/**
	 * @return the preNoDocStampDty
	 */
	public String getPreNoDocStampDty() {
		return preNoDocStampDty;
	}
	/**
	 * @param preNoDocStampDty the preNoDocStampDty to set
	 */
	public void setPreNoDocStampDty(String preNoDocStampDty) {
		this.preNoDocStampDty = preNoDocStampDty;
	}
	/**
	 * @return the preNoDocRegFee
	 */
	public String getPreNoDocRegFee() {
		return preNoDocRegFee;
	}
	/**
	 * @param preNoDocRegFee the preNoDocRegFee to set
	 */
	public void setPreNoDocRegFee(String preNoDocRegFee) {
		this.preNoDocRegFee = preNoDocRegFee;
	}
	/**
	 * @return the preNoDocTot
	 */
	public String getPreNoDocTot() {
		return preNoDocTot;
	}
	/**
	 * @param preNoDocTot the preNoDocTot to set
	 */
	public void setPreNoDocTot(String preNoDocTot) {
		this.preNoDocTot = preNoDocTot;
	}
	/**
	 * @return the compNoDocStampDty
	 */
	public String getCompNoDocStampDty() {
		return compNoDocStampDty;
	}
	/**
	 * @param compNoDocStampDty the compNoDocStampDty to set
	 */
	public void setCompNoDocStampDty(String compNoDocStampDty) {
		this.compNoDocStampDty = compNoDocStampDty;
	}
	/**
	 * @return the compNoDocRegFee
	 */
	public String getCompNoDocRegFee() {
		return compNoDocRegFee;
	}
	/**
	 * @param compNoDocRegFee the compNoDocRegFee to set
	 */
	public void setCompNoDocRegFee(String compNoDocRegFee) {
		this.compNoDocRegFee = compNoDocRegFee;
	}
	/**
	 * @return the compNoDocTot
	 */
	public String getCompNoDocTot() {
		return compNoDocTot;
	}
	/**
	 * @param compNoDocTot the compNoDocTot to set
	 */
	public void setCompNoDocTot(String compNoDocTot) {
		this.compNoDocTot = compNoDocTot;
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
	/**
	 * @param exemName
	 */
	public void setExemName(String exemName) {
		this.exemName = exemName;
	}
	/**
	 * @return the fiscalYear
	 */
	public String getFiscalYear() {
		return fiscalYear;
	}
	/**
	 * @param fiscalYear the fiscalYear to set
	 */
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
	/**
	 * @return the fiscalId
	 */
	public String getFiscalId() {
		return fiscalId;
	}
	/**
	 * @param fiscalId the fiscalId to set
	 */
	public void setFiscalId(String fiscalId) {
		this.fiscalId = fiscalId;
	}
	/**
	 * @return the droId
	 */
	public String getDroId() {
		return droId;
	}
	/**
	 * @param droId the droId to set
	 */
	public void setDroId(String droId) {
		this.droId = droId;
	}
	/**
	 * @return the progMonth
	 */
	public String getProgMonth() {
		return progMonth;
	}
	/**
	 * @param progMonth the progMonth to set
	 */
	public void setProgMonth(String progMonth) {
		this.progMonth = progMonth;
	}
	/**
	 * @return the progTargTot
	 */
	public float getProgTargTot() {
		return progTargTot;
	}
	/**
	 * @param progTargTot the progTargTot to set
	 */
	public void setProgTargTot(float progTargTot) {
		this.progTargTot = progTargTot;
	}
	/**
	 * @return the percentIncDecInAchiev
	 */
	public float getPercentIncDecInAchiev() {
		return percentIncDecInAchiev;
	}
	/**
	 * @param percentIncDecInAchiev the percentIncDecInAchiev to set
	 */
	public void setPercentIncDecInAchiev(float percentIncDecInAchiev) {
		this.percentIncDecInAchiev = percentIncDecInAchiev;
	}
	/**
	 * @return the percentIncDecInTarget
	 */
	public float getPercentIncDecInTarget() {
		return percentIncDecInTarget;
	}
	/**
	 * @param percentIncDecInTarget the percentIncDecInTarget to set
	 */
	public void setPercentIncDecInTarget(float percentIncDecInTarget) {
		this.percentIncDecInTarget = percentIncDecInTarget;
	}
	
	/**
	 * @return the comprevReceipt
	 */
	public String getComprevReceipt() {
		return comprevReceipt;
	}
	/**
	 * @param comprevReceipt the comprevReceipt to set
	 */
	public void setComprevReceipt(String comprevReceipt) {
		this.comprevReceipt = comprevReceipt;
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
	public String getRevReceipt() {
		return revReceipt;
	}
	/**
	 * @param revReceipt the revReceipt to set
	 */
	public void setRevReceipt(String revReceipt) {
		this.revReceipt = revReceipt;
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
	 * @return the compNoOfDoc
	 */
	public String getCompNoOfDoc() {
		return compNoOfDoc;
	}
	/**
	 * @param compNoOfDoc the compNoOfDoc to set
	 */
	public void setCompNoOfDoc(String compNoOfDoc) {
		this.compNoOfDoc = compNoOfDoc;
	}
	/**
	 * @return the compValuation
	 */
	public String getCompValuation() {
		return compValuation;
	}
	/**
	 * @param compValuation the compValuation to set
	 */
	public void setCompValuation(String compValuation) {
		this.compValuation = compValuation;
	}
	/**
	 * @return the effFacDoc
	 */
	public String getEffFacDoc() {
		return effFacDoc;
	}
	/**
	 * @param effFacDoc the effFacDoc to set
	 */
	public void setEffFacDoc(String effFacDoc) {
		this.effFacDoc = effFacDoc;
	}
	/**
	 * @return the effFacReceipt
	 */
	public String getEffFacReceipt() {
		return effFacReceipt;
	}
	/**
	 * @param effFacReceipt the effFacReceipt to set
	 */
	public void setEffFacReceipt(String effFacReceipt) {
		this.effFacReceipt = effFacReceipt;
	}
	/**
	 * @return the effFacValuation
	 */
	public String getEffFacValuation() {
		return effFacValuation;
	}
	/**
	 * @param effFacValuation the effFacValuation to set
	 */
	public void setEffFacValuation(String effFacValuation) {
		this.effFacValuation = effFacValuation;
	}
	/**
	 * @return the radioSearch
	 */
	public String getRadioSearch() {
		return radioSearch;
	}
	/**
	 * @param radioSearch the radioSearch to set
	 */
	public void setRadioSearch(String radioSearch) {
		this.radioSearch = radioSearch;
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
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

}
