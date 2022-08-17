/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

public class DROReportDetailsDTO implements Serializable {

	private String droId;
	private String droName;
	private String droInspId;
	private String inspStatus;
	private String checkBox;
	private String sroName;
	private String sroId;
	private String empCount;
	private String empMod;
	private String inspectedBy;

	private String authorityName;

	private String inspStartDate;

	private String inspToDate;

	private String fromYear;

	private String toYear;

	private String tenure;

	private String inspDate;

	private String srName;

	private String annualTargetPfy;

	private String cfyRevenueTarget;

	private String reasonCfy;

	private String revenueTargetPfy;

	private String reasonPfy;

	private String revenueCollectionPfy;

	private String targetCompPfy;

	private String incrDcrPfy;

	private String cfyRevenueColl;

	private String districtName;

	private ArrayList sroList;

	private ArrayList droList;

	private ArrayList listDroItemMap;

	private ArrayList listDroPendingTask;

	private ArrayList listEmpMapDto;

	
	
	private ArrayList listDROReasonMap;
	private String[] penChkItem=null;
	private String[] penTxtItem=null;
	private String[] penRemarks=null;
	private String[] mudrankTxt=null;
	private String[] mudFromMonth=null;
	private String[] mudFromYear=null;
	private String[] mudToMonth=null;
	private String[] mudToYear=null;
	private String[] mudrankRemarks=null;
	private String[] mudChkItemName=null;
	private String[] rrcTxtValue=null;
	private String[] chkRrcItem=null;
	private String[] sroTextItem=null;
	private String[] sroChkItem=null;
	private String[] rosTxtItems=null;
	private String[] chkRosItems=null;
	private String[] casTxtVal=null;
	private String[] chkStaItems=null;
	private String[] staRemarks=null;
	private String[] lekItemYear=null;
	private String[] lekTxtItem=null;
	private String[] mahRemarks=null;
	private String[] mahFileNames=null;
	private String[] lekTxtItemYear=null;
	private String[] lekintertxtval=null;
	private String[] txtTesItems=null;
	private String[] tesRemarks=null;
	private String[] repTxtItemYear=null;
	private String[] repTxtfromdate=null;
	private String[] txtItemsDes=null;
	private String[] repTxtItem=null;
	
	private String revenuefrommonth=null;
	private String revenuefromyear=null;
	private String revenuetomonth=null;
	private String revenuetoyear=null;
	private String pfyfrommonth=null;
	private String pfyfromyear=null;
	private String pfytomonth=null;
	private String pfytoyear=null;
	private String frommonth=null;
	private String fromyear=null;
	private String tomonth=null;
	private String toyear=null;
	//private FormFile theFile = null;
	
	// added by vinay
	
	
	private String igComment;
	
	private String inspEntryDate;
	
	private String inspDueDate;
	
	private String pastdetails;
	
	private String objEntryDate;
	private String rrcDetails;
	private String pensionDetails;
	private String deDetails;
	private String pendingDetails;
	private String stampDetails;
	private String drCompliance;
	
	private String digCompliance;
	
	private String igCompliance;
	private String complianceFlag;
	
	
	
	public String getEmpCount() {
		return empCount;
	}

	public void setEmpCount(String empCount) {
		this.empCount = empCount;
	}

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public String getDrCompliance() {
		return drCompliance;
	}

	public void setDrCompliance(String drCompliance) {
		this.drCompliance = drCompliance;
	}

	public String getDigCompliance() {
		return digCompliance;
	}

	public void setDigCompliance(String digCompliance) {
		this.digCompliance = digCompliance;
	}

	public String getIgCompliance() {
		return igCompliance;
	}

	public String getEmpMod() {
		return empMod;
	}

	public void setEmpMod(String empMod) {
		this.empMod = empMod;
	}

	public void setIgCompliance(String igCompliance) {
		this.igCompliance = igCompliance;
	}

	public String getComplianceFlag() {
		return complianceFlag;
	}

	public void setComplianceFlag(String complianceFlag) {
		this.complianceFlag = complianceFlag;
	}

	public String getRrcDetails() {
		return rrcDetails;
	}

	public void setRrcDetails(String rrcDetails) {
		this.rrcDetails = rrcDetails;
	}

	public String getPensionDetails() {
		return pensionDetails;
	}

	public void setPensionDetails(String pensionDetails) {
		this.pensionDetails = pensionDetails;
	}

	public String getDeDetails() {
		return deDetails;
	}

	public void setDeDetails(String deDetails) {
		this.deDetails = deDetails;
	}

	public String getPendingDetails() {
		return pendingDetails;
	}

	public void setPendingDetails(String pendingDetails) {
		this.pendingDetails = pendingDetails;
	}

	public String getStampDetails() {
		return stampDetails;
	}

	public void setStampDetails(String stampDetails) {
		this.stampDetails = stampDetails;
	}

	public String getPastdetails() {
		return pastdetails;
	}

	public void setPastdetails(String pastdetails) {
		this.pastdetails = pastdetails;
	}

	public String getObjEntryDate() {
		return objEntryDate;
	}

	public void setObjEntryDate(String objEntryDate) {
		this.objEntryDate = objEntryDate;
	}

	private String inspectionStatus;
	public String getInspectionStatus() {
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getInspDueDate() {
		return inspDueDate;
	}

	public void setInspDueDate(String inspDueDate) {
		this.inspDueDate = inspDueDate;
	}

	public String getInspEntryDate() {
		return inspEntryDate;
	}

	public void setInspEntryDate(String inspEntryDate) {
		this.inspEntryDate = inspEntryDate;
	}

	public String getIgComment() {
		return igComment;
	}

	public void setIgComment(String igComment) {
		this.igComment = igComment;
	}

	public String getTomonth() {
		return tomonth;
	}

	public void setTomonth(String tomonth) {
		this.tomonth = tomonth;
	}

	public String getToyear() {
		return toyear;
	}

	public void setToyear(String toyear) {
		this.toyear = toyear;
	}

	public String[] getRepTxtItem() {
		return repTxtItem;
	}

	public void setRepTxtItem(String[] repTxtItem) {
		this.repTxtItem = repTxtItem;
	}

	/**
	 * @return the penRemarks
	 */
	public String[] getPenRemarks() {
		return this.penRemarks;
	}

	/**
	 * @param penRemarks the penRemarks to set
	 */
	public void setPenRemarks(String[] penRemarks) {
		this.penRemarks = penRemarks;
	}

	/**
	 * @return the penTxtItem
	 */
	public String[] getPenTxtItem() {
		return this.penTxtItem;
	}

	/**
	 * @param penTxtItem the penTxtItem to set
	 */
	public void setPenTxtItem(String[] penTxtItem) {
		this.penTxtItem = penTxtItem;
	}

	public String getDroName() {
		return droName;
	}

	public void setDroName(String droName) {
		this.droName = droName;
	}

	public String getSroName() {
		return sroName;
	}

	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	public String getSroId() {
		return sroId;
	}

	public void setSroId(String sroId) {
		this.sroId = sroId;
	}

	public ArrayList getSroList() {
		return sroList;
	}

	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	/**
	 * @return the authorityName
	 */
	public String getAuthorityName() {
		return authorityName;
	}

	/**
	 * @param authorityName
	 *            the authorityName to set
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	/**
	 * @return the inspStartDate
	 */
	public String getInspStartDate() {
		return inspStartDate;
	}

	/**
	 * @param inspStartDate
	 *            the inspStartDate to set
	 */
	public void setInspStartDate(String inspStartDate) {
		this.inspStartDate = inspStartDate;
	}

	/**
	 * @return the inspDate
	 */
	public String getInspDate() {
		return inspDate;
	}

	/**
	 * @param inspDate
	 *            the inspDate to set
	 */
	public void setInspDate(String inspDate) {
		this.inspDate = inspDate;
	}

	/**
	 * @return the inspToDate
	 */
	public String getInspToDate() {
		return inspToDate;
	}

	/**
	 * @param inspToDate
	 *            the inspToDate to set
	 */
	public void setInspToDate(String inspToDate) {
		this.inspToDate = inspToDate;
	}

	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return fromYear;
	}

	/**
	 * @param fromYear
	 *            the fromYear to set
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return toYear;
	}

	/**
	 * @param toYear
	 *            the toYear to set
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	/**
	 * @return the droId
	 */
	public String getDroId() {
		return droId;
	}

	/**
	 * @param droId
	 *            the droId to set
	 */
	public void setDroId(String droId) {
		this.droId = droId;
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
	 * @return the srName
	 */
	public String getSrName() {
		return srName;
	}

	/**
	 * @param srName
	 *            the srName to set
	 */
	public void setSrName(String srName) {
		this.srName = srName;
	}

	/**
	 * @return the revenueCollectionPfy
	 */
	public String getRevenueCollectionPfy() {
		return revenueCollectionPfy;
	}

	/**
	 * @param revenueCollectionPfy
	 *            the revenueCollectionPfy to set
	 */
	public void setRevenueCollectionPfy(String revenueCollectionPfy) {
		this.revenueCollectionPfy = revenueCollectionPfy;
	}

	/**
	 * @return the annualTargetPfy
	 */
	public String getAnnualTargetPfy() {
		return annualTargetPfy;
	}

	/**
	 * @param annualTargetPfy
	 *            the annualTargetPfy to set
	 */
	public void setAnnualTargetPfy(String annualTargetPfy) {
		this.annualTargetPfy = annualTargetPfy;
	}

	/**
	 * @return the targetCompPfy
	 */
	public String getTargetCompPfy() {
		return targetCompPfy;
	}

	/**
	 * @param targetCompPfy
	 *            the targetCompPfy to set
	 */
	public void setTargetCompPfy(String targetCompPfy) {
		this.targetCompPfy = targetCompPfy;
	}

	/**
	 * @return the incrDcrPfy
	 */
	public String getIncrDcrPfy() {
		return incrDcrPfy;
	}

	/**
	 * @param incrDcrPfy
	 *            the incrDcrPfy to set
	 */
	public void setIncrDcrPfy(String incrDcrPfy) {
		this.incrDcrPfy = incrDcrPfy;
	}

	/**
	 * @return the cfyRevenueTarget
	 */
	public String getCfyRevenueTarget() {
		return cfyRevenueTarget;
	}

	/**
	 * @param cfyRevenueTarget
	 *            the cfyRevenueTarget to set
	 */
	public void setCfyRevenueTarget(String cfyRevenueTarget) {
		this.cfyRevenueTarget = cfyRevenueTarget;
	}

	/**
	 * @return the cfyRevenueColl
	 */
	public String getCfyRevenueColl() {
		return cfyRevenueColl;
	}

	/**
	 * @param cfyRevenueColl
	 *            the cfyRevenueColl to set
	 */
	public void setCfyRevenueColl(String cfyRevenueColl) {
		this.cfyRevenueColl = cfyRevenueColl;
	}

	/**
	 * @return the reasonCfy
	 */
	public String getReasonCfy() {
		return reasonCfy;
	}

	/**
	 * @param reasonCfy
	 *            the reasonCfy to set
	 */
	public void setReasonCfy(String reasonCfy) {
		this.reasonCfy = reasonCfy;
	}

	/**
	 * @return the revenueTargetPfy
	 */
	public String getRevenueTargetPfy() {
		return revenueTargetPfy;
	}

	/**
	 * @param revenueTargetPfy
	 *            the revenueTargetPfy to set
	 */
	public void setRevenueTargetPfy(String revenueTargetPfy) {
		this.revenueTargetPfy = revenueTargetPfy;
	}

	/**
	 * @return the reasonPfy
	 */
	public String getReasonPfy() {
		return reasonPfy;
	}

	/**
	 * @param reasonPfy
	 *            the reasonPfy to set
	 */
	public void setReasonPfy(String reasonPfy) {
		this.reasonPfy = reasonPfy;
	}

	/**
	 * @return the tenure
	 */
	public String getTenure() {
		return tenure;
	}

	/**
	 * @param tenure
	 *            the tenure to set
	 */
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the listDroItemMap
	 */
	public ArrayList getListDroItemMap() {
		return listDroItemMap;
	}

	/**
	 * @param listDroItemMap
	 *            the listDroItemMap to set
	 */
	public void setListDroItemMap(ArrayList listDroItemMap) {
		this.listDroItemMap = listDroItemMap;
	}

	/**
	 * @return the listDroPendingTask
	 */
	public ArrayList getListDroPendingTask() {
		return listDroPendingTask;
	}

	/**
	 * @param listDroPendingTask
	 *            the listDroPendingTask to set
	 */
	public void setListDroPendingTask(ArrayList listDroPendingTask) {
		this.listDroPendingTask = listDroPendingTask;
	}

	/**
	 * @return the inspectedBy
	 */
	public String getInspectedBy() {
		return inspectedBy;
	}

	/**
	 * @param inspectedBy
	 *            the inspectedBy to set
	 */
	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	/**
	 * @return the listEmpMapDto
	 */
	public ArrayList getListEmpMapDto() {
		return listEmpMapDto;
	}

	/**
	 * @param listEmpMapDto
	 *            the listEmpMapDto to set
	 */
	public void setListEmpMapDto(ArrayList listEmpMapDto) {
		this.listEmpMapDto = listEmpMapDto;
	}

	/**
	 * @return the listDROReasonMap
	 */
	public ArrayList getListDROReasonMap() {
		return listDROReasonMap;
	}

	/**
	 * @param listDROReasonMap
	 *            the listDROReasonMap to set
	 */
	public void setListDROReasonMap(ArrayList listDROReasonMap) {
		this.listDROReasonMap = listDROReasonMap;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName
	 *            the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the droInspId
	 */
	public String getDroInspId() {
		return droInspId;
	}

	/**
	 * @param droInspId
	 *            the droInspId to set
	 */
	public void setDroInspId(String droInspId) {
		this.droInspId = droInspId;
	}

	/**
	 * @return the inspStatus
	 */
	public String getInspStatus() {
		return inspStatus;
	}

	/**
	 * @param inspStatus
	 *            the inspStatus to set
	 */
	public void setInspStatus(String inspStatus) {
		this.inspStatus = inspStatus;
	}

	/**
	 * @return the mudrankTxt
	 */
	public String[] getMudrankTxt() {
		return this.mudrankTxt;
	}

	/**
	 * @param mudrankTxt the mudrankTxt to set
	 */
	public void setMudrankTxt(String[] mudrankTxt) {
		this.mudrankTxt = mudrankTxt;
	}

	/**
	 * @return the mudFromMonth
	 */
	public String[] getMudFromMonth() {
		return this.mudFromMonth;
	}

	/**
	 * @param mudFromMonth the mudFromMonth to set
	 */
	public void setMudFromMonth(String[] mudFromMonth) {
		this.mudFromMonth = mudFromMonth;
	}

	/**
	 * @return the mudFromYear
	 */
	public String[] getMudFromYear() {
		return this.mudFromYear;
	}

	/**
	 * @param mudFromYear the mudFromYear to set
	 */
	public void setMudFromYear(String[] mudFromYear) {
		this.mudFromYear = mudFromYear;
	}

	/**
	 * @return the mudToMonth
	 */
	public String[] getMudToMonth() {
		return this.mudToMonth;
	}

	/**
	 * @param mudToMonth the mudToMonth to set
	 */
	public void setMudToMonth(String[] mudToMonth) {
		this.mudToMonth = mudToMonth;
	}

	/**
	 * @return the mudToYear
	 */
	public String[] getMudToYear() {
		return this.mudToYear;
	}

	/**
	 * @param mudToYear the mudToYear to set
	 */
	public void setMudToYear(String[] mudToYear) {
		this.mudToYear = mudToYear;
	}

	/**
	 * @return the mudrankRemarks
	 */
	public String[] getMudrankRemarks() {
		return this.mudrankRemarks;
	}

	/**
	 * @param mudrankRemarks the mudrankRemarks to set
	 */
	public void setMudrankRemarks(String[] mudrankRemarks) {
		this.mudrankRemarks = mudrankRemarks;
	}

	/**
	 * @return the mudChkItemName
	 */
	public String[] getMudChkItemName() {
		return this.mudChkItemName;
	}

	/**
	 * @param mudChkItemName the mudChkItemName to set
	 */
	public void setMudChkItemName(String[] mudChkItemName) {
		this.mudChkItemName = mudChkItemName;
	}

	/**
	 * @return the rrcTxtValue
	 */
	public String[] getRrcTxtValue() {
		return this.rrcTxtValue;
	}

	/**
	 * @param rrcTxtValue the rrcTxtValue to set
	 */
	public void setRrcTxtValue(String[] rrcTxtValue) {
		this.rrcTxtValue = rrcTxtValue;
	}

	/**
	 * @return the chkRrcItem
	 */
	public String[] getChkRrcItem() {
		return this.chkRrcItem;
	}

	/**
	 * @param chkRrcItem the chkRrcItem to set
	 */
	public void setChkRrcItem(String[] chkRrcItem) {
		this.chkRrcItem = chkRrcItem;
	}

	/**
	 * @return the sroTextItem
	 */
	public String[] getSroTextItem() {
		return this.sroTextItem;
	}

	/**
	 * @param sroTextItem the sroTextItem to set
	 */
	public void setSroTextItem(String[] sroTextItem) {
		this.sroTextItem = sroTextItem;
	}

	/**
	 * @return the sroChkItem
	 */
	public String[] getSroChkItem() {
		return this.sroChkItem;
	}

	/**
	 * @param sroChkItem the sroChkItem to set
	 */
	public void setSroChkItem(String[] sroChkItem) {
		this.sroChkItem = sroChkItem;
	}

	/**
	 * @return the rosTxtItems
	 */
	public String[] getRosTxtItems() {
		return this.rosTxtItems;
	}

	/**
	 * @param rosTxtItems the rosTxtItems to set
	 */
	public void setRosTxtItems(String[] rosTxtItems) {
		this.rosTxtItems = rosTxtItems;
	}

	/**
	 * @return the chkRosItems
	 */
	public String[] getChkRosItems() {
		return this.chkRosItems;
	}

	/**
	 * @param chkRosItems the chkRosItems to set
	 */
	public void setChkRosItems(String[] chkRosItems) {
		this.chkRosItems = chkRosItems;
	}

	/**
	 * @return the casTxtVal
	 */
	public String[] getCasTxtVal() {
		return this.casTxtVal;
	}

	/**
	 * @param casTxtVal the casTxtVal to set
	 */
	public void setCasTxtVal(String[] casTxtVal) {
		this.casTxtVal = casTxtVal;
	}

	/**
	 * @return the chkStaItems
	 */
	public String[] getChkStaItems() {
		return this.chkStaItems;
	}

	/**
	 * @param chkStaItems the chkStaItems to set
	 */
	public void setChkStaItems(String[] chkStaItems) {
		this.chkStaItems = chkStaItems;
	}

	/**
	 * @return the staRemarks
	 */
	public String[] getStaRemarks() {
		return this.staRemarks;
	}

	/**
	 * @param staRemarks the staRemarks to set
	 */
	public void setStaRemarks(String[] staRemarks) {
		this.staRemarks = staRemarks;
	}

	/**
	 * @return the lekItemYear
	 */
	public String[] getLekItemYear() {
		return this.lekItemYear;
	}

	/**
	 * @param lekItemYear the lekItemYear to set
	 */
	public void setLekItemYear(String[] lekItemYear) {
		this.lekItemYear = lekItemYear;
	}

	/**
	 * @return the lekTxtItem
	 */
	public String[] getLekTxtItem() {
		return this.lekTxtItem;
	}

	/**
	 * @param lekTxtItem the lekTxtItem to set
	 */
	public void setLekTxtItem(String[] lekTxtItem) {
		this.lekTxtItem = lekTxtItem;
	}

	/**
	 * @return the mahRemarks
	 */
	public String[] getMahRemarks() {
		return this.mahRemarks;
	}

	/**
	 * @param mahRemarks the mahRemarks to set
	 */
	public void setMahRemarks(String[] mahRemarks) {
		this.mahRemarks = mahRemarks;
	}

	/**
	 * @return the mahFileNames
	 */
	public String[] getMahFileNames() {
		return this.mahFileNames;
	}

	/**
	 * @param mahFileNames the mahFileNames to set
	 */
	public void setMahFileNames(String[] mahFileNames) {
		this.mahFileNames = mahFileNames;
	}

	/**
	 * @return the lekTxtItemYear
	 */
	public String[] getLekTxtItemYear() {
		return this.lekTxtItemYear;
	}

	/**
	 * @param lekTxtItemYear the lekTxtItemYear to set
	 */
	public void setLekTxtItemYear(String[] lekTxtItemYear) {
		this.lekTxtItemYear = lekTxtItemYear;
	}

	/**
	 * @return the txtTesItems
	 */
	public String[] getTxtTesItems() {
		return this.txtTesItems;
	}

	/**
	 * @param txtTesItems the txtTesItems to set
	 */
	public void setTxtTesItems(String[] txtTesItems) {
		this.txtTesItems = txtTesItems;
	}

	/**
	 * @return the tesRemarks
	 */
	public String[] getTesRemarks() {
		return this.tesRemarks;
	}

	/**
	 * @param tesRemarks the tesRemarks to set
	 */
	public void setTesRemarks(String[] tesRemarks) {
		this.tesRemarks = tesRemarks;
	}

	/**
	 * @return the repTxtItemYear
	 */
	public String[] getRepTxtItemYear() {
		return this.repTxtItemYear;
	}

	/**
	 * @param repTxtItemYear the repTxtItemYear to set
	 */
	public void setRepTxtItemYear(String[] repTxtItemYear) {
		this.repTxtItemYear = repTxtItemYear;
	}

	/**
	 * @return the repTxtfromdate
	 */
	public String[] getRepTxtfromdate() {
		return this.repTxtfromdate;
	}

	/**
	 * @param repTxtfromdate the repTxtfromdate to set
	 */
	public void setRepTxtfromdate(String[] repTxtfromdate) {
		this.repTxtfromdate = repTxtfromdate;
	}

	/**
	 * @return the txtItemsDes
	 */
	public String[] getTxtItemsDes() {
		return this.txtItemsDes;
	}

	/**
	 * @param txtItemsDes the txtItemsDes to set
	 */
	public void setTxtItemsDes(String[] txtItemsDes) {
		this.txtItemsDes = txtItemsDes;
	}

	public String[] getPenChkItem() {
		return penChkItem;
	}

	public void setPenChkItem(String[] penChkItem) {
		this.penChkItem = penChkItem;
	}

	public String getRevenuefrommonth() {
		return revenuefrommonth;
	}

	public void setRevenuefrommonth(String revenuefrommonth) {
		this.revenuefrommonth = revenuefrommonth;
	}

	public String getRevenuefromyear() {
		return revenuefromyear;
	}

	public void setRevenuefromyear(String revenuefromyear) {
		this.revenuefromyear = revenuefromyear;
	}

	public String getRevenuetomonth() {
		return revenuetomonth;
	}

	public void setRevenuetomonth(String revenuetomonth) {
		this.revenuetomonth = revenuetomonth;
	}

	public String getRevenuetoyear() {
		return revenuetoyear;
	}

	public void setRevenuetoyear(String revenuetoyear) {
		this.revenuetoyear = revenuetoyear;
	}

	public String getPfyfrommonth() {
		return pfyfrommonth;
	}

	public void setPfyfrommonth(String pfyfrommonth) {
		this.pfyfrommonth = pfyfrommonth;
	}

	public String getPfyfromyear() {
		return pfyfromyear;
	}

	public void setPfyfromyear(String pfyfromyear) {
		this.pfyfromyear = pfyfromyear;
	}

	public String getPfytomonth() {
		return pfytomonth;
	}

	public void setPfytomonth(String pfytomonth) {
		this.pfytomonth = pfytomonth;
	}

	public String getPfytoyear() {
		return pfytoyear;
	}

	public void setPfytoyear(String pfytoyear) {
		this.pfytoyear = pfytoyear;
	}

	public String getFrommonth() {
		return frommonth;
	}

	public void setFrommonth(String frommonth) {
		this.frommonth = frommonth;
	}

	public String getFromyear() {
		return fromyear;
	}

	public void setFromyear(String fromyear) {
		this.fromyear = fromyear;
	}

	public String[] getLekintertxtval() {
		return lekintertxtval;
	}

	public void setLekintertxtval(String[] lekintertxtval) {
		this.lekintertxtval = lekintertxtval;
	}

}
