package com.wipro.igrs.copyissuance.dto;


/**
 * ===========================================================================
 * File           :   DashBoardDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Aakriti
 * Created Date   :   Nov 29, 2012

 * ===========================================================================
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class DashBoardDTO implements Serializable {
	private String ftName;    
	private String ltName;
	private String latestDate;
	private String id;
	private String tempId;
	private String stampAmt;
	private String appStatus;
	private String formName;
	private String actionName;
	private ArrayList pendingEstampApplicationList = new ArrayList();
	private ArrayList detailsTxnID = new ArrayList();
	private ArrayList partyDetails = new ArrayList();
	private ArrayList docDetails = new ArrayList();
	private Object hdntransactionID;
	private Object transactionID;
	private Object deedName;
	private Object docType;
	private Object paidAmount;
	private Object balanceAmount;
	private Object lastTransactionDate;
	private String hidnEstampTxnId;
	private String hidnUserId;
	//private String flag;
	
	// FOR DASHBOARD LANDING PAGE
	
	private Object mainTxnId1;
	private Object deedType;
	private Object instType;
	private Object exemType;
	private Object baseValueDisplay;
	private Object annualRentDisplay;
	private Object dutyPaidDisplay;
	private Object stampDutyDisplay;
	private Object nagarPalikaDutyDisplay;
	private Object panchayatDutyDisplay;
	private Object upkarDutyDisplay;
	private Object totalDisplay;
	private Object dateCalculation;
	private Object estampPurpose;
	
	private Object appTypeName;
	private Object appType;
	private Object appOrgName;
	private Object appAuthFirstName;
	private Object appAuthMiddleName;
	private Object appAuthLastName;
	private Object appCountryName;
	private Object appStateName;
	private Object appDistrictName;
	private Object appAddress;
	private Object appPostalCode;
	private Object appPhno;
	private Object appMobno;
	private Object appEmailID;
	private Object appPersons;
	private Object appFirsName;
	private Object appMiddleName;
	private Object appLastName;
	private Object appGender;
	private Object appAge;
	private Object appFatherName;
	private Object appMotherName;
	private Object appPhotoIdName;
	private Object appPhotoIdno;
	private Object partyTypeName;
	private Object partyOrgName;
	private Object partyAuthFirstName;
	private Object partyAuthMiddleName;
	private Object partyAuthLastName;
	private Object partyCountryName;
	private Object partyStateName;
	private Object partyDistrictName;
	private Object partyAddress;
	private Object partyPostalCode;
	private Object partyPhno;
	private Object partyMobno;
	private Object partyEmailID;
	private Object partyPersons;
	private Object partyFirstName;
	private Object partyMiddleName;
	private Object partyLastName;
	private Object partyGender;
	private Object partyAge;
	private Object partyFatherName;
	private Object partyMotherName;
	private Object partyPhotoIdName;
	private Object partyPhotoIdno;
	private Object doc;
	private Object applicant_ind;
	private Object partyType;
	
	//Added for the search of E-stamp Code for deactivation and search
	private String ecode;
	
	// Added By Lavi for the search and view of E-Stamp Code details
	private String check;
	private Object estampId;
	private Object estampPurchaseDate;
	private Object estampStatus;
	private Object estampType;
	private Object estampBuyerName;
	private Object estampValidity;
	private Object estampAmount;
	private Object estampPaymentMode;
	private Object estampDeactDate;
	private Object estampParty2Name;
	private Object regInitId;
	
	private ArrayList viewRUEcodeDetails = new ArrayList();
	private ArrayList viewRUEcodeType = new ArrayList();
	private ArrayList viewDRSEcodeDetails = new ArrayList();
	private ArrayList partyDetailsDRS = new ArrayList();
	private ArrayList ecodeDeactDetails = new ArrayList();
	private ArrayList ecodeDeactDate = new ArrayList();
	private ArrayList ecodeDeactAppDetails = new ArrayList();
	private ArrayList ecodeDeactPartyDetails = new ArrayList();
	private ArrayList ecodeConStatus = new ArrayList();
	
	//Added by Lavi for E-Stamp Deactivation
	private Object persName;
	private Object persFatherName;
	private Object persMotherName;
	private Object persAddress;
	private Object persMobNo;
	private Object persPhnNo;
	private Object drName;
	private Object estampDeactID;
	private String checkDeact;
	private String checkExpiry;
	private String checkDeactv;
	private String checkConsumd;
	private ArrayList ecodeDeactDRName = new ArrayList();
	private ArrayList deactRequestNo = new ArrayList();
	private String deedDuration;
	private int isjud=0;
	private Object conStatus;
	private int isConsumed=0;
	private int isConsumedChecked=0;
	private String remarks;
	private String role;
	
	
	
	
	public String getLatestDate() {
		return latestDate;
	}
	public void setLatestDate(String latestDate) {
		this.latestDate = latestDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTempId() {
		return tempId;
	}
	public String getFtName() {
		return ftName;
	}
	public void setFtName(String ftName) {
		this.ftName = ftName;
	}
	public String getLtName() {
		return ltName;
	}
	public void setLtName(String ltName) {
		this.ltName = ltName;
	}
	public void setTempId(String tempId) {
		this.tempId = tempId;
	}
	public String getStampAmt() {
		return stampAmt;
	}
	public void setStampAmt(String stampAmt) {
		this.stampAmt = stampAmt;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
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
	public ArrayList getPendingEstampApplicationList() {
		return pendingEstampApplicationList;
	}
	public void setPendingEstampApplicationList(
			ArrayList pendingEstampApplicationList) {
		this.pendingEstampApplicationList = pendingEstampApplicationList;
	}
	public Object getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Object transactionID) {
		this.transactionID = transactionID;
	}
	public Object getDeedName() {
		return deedName;
	}
	public void setDeedName(Object deedName) {
		this.deedName = deedName;
	}
	public Object getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Object paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Object getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Object balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public Object getLastTransactionDate() {
		return lastTransactionDate;
	}
	public void setLastTransactionDate(Object lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}
	public String getHidnEstampTxnId() {
		return hidnEstampTxnId;
	}
	public void setHidnEstampTxnId(String hidnEstampTxnId) {
		this.hidnEstampTxnId = hidnEstampTxnId;
	}
	public String getHidnUserId() {
		return hidnUserId;
	}
	public void setHidnUserId(String hidnUserId) {
		this.hidnUserId = hidnUserId;
	}
	
	public Object getDeedType() {
		return deedType;
	}
	public void setDeedType(Object deedType) {
		this.deedType = deedType;
	}
	public Object getInstType() {
		return instType;
	}
	public void setInstType(Object instType) {
		this.instType = instType;
	}
	public Object getExemType() {
		return exemType;
	}
	public void setExemType(Object exemType) {
		this.exemType = exemType;
	}
	public Object getBaseValueDisplay() {
		return baseValueDisplay;
	}
	public void setBaseValueDisplay(Object baseValueDisplay) {
		this.baseValueDisplay = baseValueDisplay;
	}
	public Object getAnnualRentDisplay() {
		return annualRentDisplay;
	}
	public void setAnnualRentDisplay(Object annualRentDisplay) {
		this.annualRentDisplay = annualRentDisplay;
	}
	public Object getDutyPaidDisplay() {
		return dutyPaidDisplay;
	}
	public void setDutyPaidDisplay(Object dutyPaidDisplay) {
		this.dutyPaidDisplay = dutyPaidDisplay;
	}
	public Object getStampDutyDisplay() {
		return stampDutyDisplay;
	}
	public void setStampDutyDisplay(Object stampDutyDisplay) {
		this.stampDutyDisplay = stampDutyDisplay;
	}
	public Object getNagarPalikaDutyDisplay() {
		return nagarPalikaDutyDisplay;
	}
	public void setNagarPalikaDutyDisplay(Object nagarPalikaDutyDisplay) {
		this.nagarPalikaDutyDisplay = nagarPalikaDutyDisplay;
	}
	public Object getPanchayatDutyDisplay() {
		return panchayatDutyDisplay;
	}
	public void setPanchayatDutyDisplay(Object panchayatDutyDisplay) {
		this.panchayatDutyDisplay = panchayatDutyDisplay;
	}
	public Object getUpkarDutyDisplay() {
		return upkarDutyDisplay;
	}
	public void setUpkarDutyDisplay(Object upkarDutyDisplay) {
		this.upkarDutyDisplay = upkarDutyDisplay;
	}
	public Object getTotalDisplay() {
		return totalDisplay;
	}
	public void setTotalDisplay(Object totalDisplay) {
		this.totalDisplay = totalDisplay;
	}
	public Object getDateCalculation() {
		return dateCalculation;
	}
	public void setDateCalculation(Object dateCalculation) {
		this.dateCalculation = dateCalculation;
	}
	public Object getEstampPurpose() {
		return estampPurpose;
	}
	public void setEstampPurpose(Object estampPurpose) {
		this.estampPurpose = estampPurpose;
	}
	public Object getAppTypeName() {
		return appTypeName;
	}
	public void setAppTypeName(Object appTypeName) {
		this.appTypeName = appTypeName;
	}
	public Object getAppType() {
		return appType;
	}
	public void setAppType(Object appType) {
		this.appType = appType;
	}
	public Object getAppOrgName() {
		return appOrgName;
	}
	public void setAppOrgName(Object appOrgName) {
		this.appOrgName = appOrgName;
	}
	public Object getAppAuthFirstName() {
		return appAuthFirstName;
	}
	public void setAppAuthFirstName(Object appAuthFirstName) {
		this.appAuthFirstName = appAuthFirstName;
	}
	public Object getAppAuthMiddleName() {
		return appAuthMiddleName;
	}
	public void setAppAuthMiddleName(Object appAuthMiddleName) {
		this.appAuthMiddleName = appAuthMiddleName;
	}
	public Object getAppAuthLastName() {
		return appAuthLastName;
	}
	public void setAppAuthLastName(Object appAuthLastName) {
		this.appAuthLastName = appAuthLastName;
	}
	public Object getAppCountryName() {
		return appCountryName;
	}
	public void setAppCountryName(Object appCountryName) {
		this.appCountryName = appCountryName;
	}
	public Object getAppStateName() {
		return appStateName;
	}
	public void setAppStateName(Object appStateName) {
		this.appStateName = appStateName;
	}
	public Object getAppDistrictName() {
		return appDistrictName;
	}
	public void setAppDistrictName(Object appDistrictName) {
		this.appDistrictName = appDistrictName;
	}
	public Object getAppAddress() {
		return appAddress;
	}
	public void setAppAddress(Object appAddress) {
		this.appAddress = appAddress;
	}
	public Object getAppPostalCode() {
		return appPostalCode;
	}
	public void setAppPostalCode(Object appPostalCode) {
		this.appPostalCode = appPostalCode;
	}
	public Object getAppMobno() {
		return appMobno;
	}
	public void setAppMobno(Object appMobno) {
		this.appMobno = appMobno;
	}
	public Object getAppEmailID() {
		return appEmailID;
	}
	public void setAppEmailID(Object appEmailID) {
		this.appEmailID = appEmailID;
	}
	public Object getAppPersons() {
		return appPersons;
	}
	public void setAppPersons(Object appPersons) {
		this.appPersons = appPersons;
	}
	public Object getAppFirsName() {
		return appFirsName;
	}
	public void setAppFirsName(Object appFirsName) {
		this.appFirsName = appFirsName;
	}
	public Object getAppMiddleName() {
		return appMiddleName;
	}
	public void setAppMiddleName(Object appMiddleName) {
		this.appMiddleName = appMiddleName;
	}
	public Object getAppLastName() {
		return appLastName;
	}
	public void setAppLastName(Object appLastName) {
		this.appLastName = appLastName;
	}
	public Object getAppGender() {
		return appGender;
	}
	public void setAppGender(Object appGender) {
		this.appGender = appGender;
	}
	public Object getAppAge() {
		return appAge;
	}
	public void setAppAge(Object appAge) {
		this.appAge = appAge;
	}
	public Object getAppFatherName() {
		return appFatherName;
	}
	public void setAppFatherName(Object appFatherName) {
		this.appFatherName = appFatherName;
	}
	public Object getAppMotherName() {
		return appMotherName;
	}
	public void setAppMotherName(Object appMotherName) {
		this.appMotherName = appMotherName;
	}
	public Object getAppPhotoIdName() {
		return appPhotoIdName;
	}
	public void setAppPhotoIdName(Object appPhotoIdName) {
		this.appPhotoIdName = appPhotoIdName;
	}
	public Object getAppPhotoIdno() {
		return appPhotoIdno;
	}
	public void setAppPhotoIdno(Object appPhotoIdno) {
		this.appPhotoIdno = appPhotoIdno;
	}
	public Object getPartyTypeName() {
		return partyTypeName;
	}
	public void setPartyTypeName(Object partyTypeName) {
		this.partyTypeName = partyTypeName;
	}
	public Object getPartyOrgName() {
		return partyOrgName;
	}
	public void setPartyOrgName(Object partyOrgName) {
		this.partyOrgName = partyOrgName;
	}
	public Object getPartyAuthFirstName() {
		return partyAuthFirstName;
	}
	public void setPartyAuthFirstName(Object partyAuthFirstName) {
		this.partyAuthFirstName = partyAuthFirstName;
	}
	public Object getPartyAuthMiddleName() {
		return partyAuthMiddleName;
	}
	public void setPartyAuthMiddleName(Object partyAuthMiddleName) {
		this.partyAuthMiddleName = partyAuthMiddleName;
	}
	public Object getPartyAuthLastName() {
		return partyAuthLastName;
	}
	public void setPartyAuthLastName(Object partyAuthLastName) {
		this.partyAuthLastName = partyAuthLastName;
	}
	public Object getPartyCountryName() {
		return partyCountryName;
	}
	public void setPartyCountryName(Object partyCountryName) {
		this.partyCountryName = partyCountryName;
	}
	public Object getPartyStateName() {
		return partyStateName;
	}
	public void setPartyStateName(Object partyStateName) {
		this.partyStateName = partyStateName;
	}
	public Object getPartyDistrictName() {
		return partyDistrictName;
	}
	public void setPartyDistrictName(Object partyDistrictName) {
		this.partyDistrictName = partyDistrictName;
	}
	public Object getPartyAddress() {
		return partyAddress;
	}
	public void setPartyAddress(Object partyAddress) {
		this.partyAddress = partyAddress;
	}
	public Object getPartyPostalCode() {
		return partyPostalCode;
	}
	public void setPartyPostalCode(Object partyPostalCode) {
		this.partyPostalCode = partyPostalCode;
	}
	public Object getPartyPhno() {
		return partyPhno;
	}
	public void setPartyPhno(Object partyPhno) {
		this.partyPhno = partyPhno;
	}
	public Object getPartyMobno() {
		return partyMobno;
	}
	public void setPartyMobno(Object partyMobno) {
		this.partyMobno = partyMobno;
	}
	public Object getPartyEmailID() {
		return partyEmailID;
	}
	public void setPartyEmailID(Object partyEmailID) {
		this.partyEmailID = partyEmailID;
	}
	public Object getPartyPersons() {
		return partyPersons;
	}
	public void setPartyPersons(Object partyPersons) {
		this.partyPersons = partyPersons;
	}
	public Object getPartyFirstName() {
		return partyFirstName;
	}
	public void setPartyFirstName(Object partyFirstName) {
		this.partyFirstName = partyFirstName;
	}
	public Object getPartyMiddleName() {
		return partyMiddleName;
	}
	public void setPartyMiddleName(Object partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}
	public Object getPartyLastName() {
		return partyLastName;
	}
	public void setPartyLastName(Object partyLastName) {
		this.partyLastName = partyLastName;
	}
	public Object getPartyGender() {
		return partyGender;
	}
	public void setPartyGender(Object partyGender) {
		this.partyGender = partyGender;
	}
	public Object getPartyAge() {
		return partyAge;
	}
	public void setPartyAge(Object partyAge) {
		this.partyAge = partyAge;
	}
	public Object getPartyFatherName() {
		return partyFatherName;
	}
	public void setPartyFatherName(Object partyFatherName) {
		this.partyFatherName = partyFatherName;
	}
	public Object getPartyMotherName() {
		return partyMotherName;
	}
	public void setPartyMotherName(Object partyMotherName) {
		this.partyMotherName = partyMotherName;
	}
	public Object getPartyPhotoIdName() {
		return partyPhotoIdName;
	}
	public void setPartyPhotoIdName(Object partyPhotoIdName) {
		this.partyPhotoIdName = partyPhotoIdName;
	}
	public Object getPartyPhotoIdno() {
		return partyPhotoIdno;
	}
	public void setPartyPhotoIdno(Object partyPhotoIdno) {
		this.partyPhotoIdno = partyPhotoIdno;
	}
	public Object getDoc() {
		return doc;
	}
	public void setDoc(Object doc) {
		this.doc = doc;
	}
	public Object getAppPhno() {
		return appPhno;
	}
	public void setAppPhno(Object appPhno) {
		this.appPhno = appPhno;
	}
	public Object getApplicant_ind() {
		return applicant_ind;
	}
	public void setApplicant_ind(Object applicant_ind) {
		this.applicant_ind = applicant_ind;
	}
	public ArrayList getDetailsTxnID() {
		return detailsTxnID;
	}
	public void setDetailsTxnID(ArrayList detailsTxnID) {
		this.detailsTxnID = detailsTxnID;
	}
	public ArrayList getPartyDetails() {
		return partyDetails;
	}
	public void setPartyDetails(ArrayList partyDetails) {
		this.partyDetails = partyDetails;
	}
	public ArrayList getDocDetails() {
		return docDetails;
	}
	public void setDocDetails(ArrayList docDetails) {
		this.docDetails = docDetails;
	}
	public Object getPartyType() {
		return partyType;
	}
	public void setPartyType(Object partyType) {
		this.partyType = partyType;
	}
	public Object getMainTxnId1() {
		return mainTxnId1;
	}
	public void setMainTxnId1(Object mainTxnId1) {
		this.mainTxnId1 = mainTxnId1;
	}
	public String getEcode() {
		return ecode;
	}
	public void setEcode(String ecode) {
		this.ecode = ecode;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public Object getEstampId() {
		return estampId;
	}
	public void setEstampId(Object estampId) {
		this.estampId = estampId;
	}
	public Object getEstampPurchaseDate() {
		return estampPurchaseDate;
	}
	public void setEstampPurchaseDate(Object estampPurchaseDate) {
		this.estampPurchaseDate = estampPurchaseDate;
	}
	public Object getEstampStatus() {
		return estampStatus;
	}
	public void setEstampStatus(Object estampStatus) {
		this.estampStatus = estampStatus;
	}
	public Object getEstampType() {
		return estampType;
	}
	public void setEstampType(Object estampType) {
		this.estampType = estampType;
	}
	public Object getEstampBuyerName() {
		return estampBuyerName;
	}
	public void setEstampBuyerName(Object estampBuyerName) {
		this.estampBuyerName = estampBuyerName;
	}
	public Object getEstampValidity() {
		return estampValidity;
	}
	public void setEstampValidity(Object estampValidity) {
		this.estampValidity = estampValidity;
	}
	public ArrayList getViewRUEcodeDetails() {
		return viewRUEcodeDetails;
	}
	public void setViewRUEcodeDetails(ArrayList viewRUEcodeDetails) {
		this.viewRUEcodeDetails = viewRUEcodeDetails;
	}
	public ArrayList getViewRUEcodeType() {
		return viewRUEcodeType;
	}
	public void setViewRUEcodeType(ArrayList viewRUEcodeType) {
		this.viewRUEcodeType = viewRUEcodeType;
	}
	public Object getEstampAmount() {
		return estampAmount;
	}
	public void setEstampAmount(Object estampAmount) {
		this.estampAmount = estampAmount;
	}
	public ArrayList getViewDRSEcodeDetails() {
		return viewDRSEcodeDetails;
	}
	public void setViewDRSEcodeDetails(ArrayList viewDRSEcodeDetails) {
		this.viewDRSEcodeDetails = viewDRSEcodeDetails;
	}
	public ArrayList getPartyDetailsDRS() {
		return partyDetailsDRS;
	}
	public void setPartyDetailsDRS(ArrayList partyDetailsDRS) {
		this.partyDetailsDRS = partyDetailsDRS;
	}
	public Object getEstampPaymentMode() {
		return estampPaymentMode;
	}
	public void setEstampPaymentMode(Object estampPaymentMode) {
		this.estampPaymentMode = estampPaymentMode;
	}
	public Object getEstampDeactDate() {
		return estampDeactDate;
	}
	public void setEstampDeactDate(Object estampDeactDate) {
		this.estampDeactDate = estampDeactDate;
	}
	public ArrayList getEcodeDeactDetails() {
		return ecodeDeactDetails;
	}
	public void setEcodeDeactDetails(ArrayList ecodeDeactDetails) {
		this.ecodeDeactDetails = ecodeDeactDetails;
	}
	public ArrayList getEcodeDeactDate() {
		return ecodeDeactDate;
	}
	public void setEcodeDeactDate(ArrayList ecodeDeactDate) {
		this.ecodeDeactDate = ecodeDeactDate;
	}
	public ArrayList getEcodeDeactAppDetails() {
		return ecodeDeactAppDetails;
	}
	public void setEcodeDeactAppDetails(ArrayList ecodeDeactAppDetails) {
		this.ecodeDeactAppDetails = ecodeDeactAppDetails;
	}
	public ArrayList getEcodeDeactPartyDetails() {
		return ecodeDeactPartyDetails;
	}
	public void setEcodeDeactPartyDetails(ArrayList ecodeDeactPartyDetails) {
		this.ecodeDeactPartyDetails = ecodeDeactPartyDetails;
	}
	public Object getEstampParty2Name() {
		return estampParty2Name;
	}
	public void setEstampParty2Name(Object estampParty2Name) {
		this.estampParty2Name = estampParty2Name;
	}
	public Object getPersName() {
		return persName;
	}
	public void setPersName(Object persName) {
		this.persName = persName;
	}
	public Object getPersFatherName() {
		return persFatherName;
	}
	public void setPersFatherName(Object persFatherName) {
		this.persFatherName = persFatherName;
	}
	public Object getPersMotherName() {
		return persMotherName;
	}
	public void setPersMotherName(Object persMotherName) {
		this.persMotherName = persMotherName;
	}
	public Object getPersAddress() {
		return persAddress;
	}
	public void setPersAddress(Object persAddress) {
		this.persAddress = persAddress;
	}
	public Object getPersMobNo() {
		return persMobNo;
	}
	public void setPersMobNo(Object persMobNo) {
		this.persMobNo = persMobNo;
	}
	public Object getPersPhnNo() {
		return persPhnNo;
	}
	public void setPersPhnNo(Object persPhnNo) {
		this.persPhnNo = persPhnNo;
	}
	public Object getDrName() {
		return drName;
	}
	public void setDrName(Object drName) {
		this.drName = drName;
	}
	public ArrayList getEcodeDeactDRName() {
		return ecodeDeactDRName;
	}
	public void setEcodeDeactDRName(ArrayList ecodeDeactDRName) {
		this.ecodeDeactDRName = ecodeDeactDRName;
	}
	public Object getEstampDeactID() {
		return estampDeactID;
	}
	public void setEstampDeactID(Object estampDeactID) {
		this.estampDeactID = estampDeactID;
	}
	public ArrayList getDeactRequestNo() {
		return deactRequestNo;
	}
	public void setDeactRequestNo(ArrayList deactRequestNo) {
		this.deactRequestNo = deactRequestNo;
	}
	public String getCheckDeact() {
		return checkDeact;
	}
	public void setCheckDeact(String checkDeact) {
		this.checkDeact = checkDeact;
	}
	public String getCheckExpiry() {
		return checkExpiry;
	}
	public void setCheckExpiry(String checkExpiry) {
		this.checkExpiry = checkExpiry;
	}
	public String getDeedDuration() {
		return deedDuration;
	}
	public void setDeedDuration(String deedDuration) {
		this.deedDuration = deedDuration;
	}
	public Object getHdntransactionID() {
		return hdntransactionID;
	}
	public void setHdntransactionID(Object hdntransactionID) {
		this.hdntransactionID = hdntransactionID;
	}
	public Object getRegInitId() {
		return regInitId;
	}
	public void setRegInitId(Object regInitId) {
		this.regInitId = regInitId;
	}
	public String getCheckDeactv() {
		return checkDeactv;
	}
	public void setCheckDeactv(String checkDeactv) {
		this.checkDeactv = checkDeactv;
	}
	public Object getDocType() {
		return docType;
	}
	public void setDocType(Object docType) {
		this.docType = docType;
	}
	public int getIsjud() {
		return isjud;
	}
	public void setIsjud(int isjud) {
		this.isjud = isjud;
	}
	public Object getConStatus() {
		return conStatus;
	}
	public void setConStatus(Object conStatus) {
		this.conStatus = conStatus;
	}
	public ArrayList getEcodeConStatus() {
		return ecodeConStatus;
	}
	public void setEcodeConStatus(ArrayList ecodeConStatus) {
		this.ecodeConStatus = ecodeConStatus;
	}
	public int getIsConsumed() {
		return isConsumed;
	}
	public void setIsConsumed(int isConsumed) {
		this.isConsumed = isConsumed;
	}
	public int getIsConsumedChecked() {
		return isConsumedChecked;
	}
	public void setIsConsumedChecked(int isConsumedChecked) {
		this.isConsumedChecked = isConsumedChecked;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCheckConsumd() {
		return checkConsumd;
	}
	public void setCheckConsumd(String checkConsumd) {
		this.checkConsumd = checkConsumd;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	

	  
}
