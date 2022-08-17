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
* 1.0             SHRUTI           xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.documentsearch.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.apache.struts.upload.FormFile;
public class DocumentSearchDTO implements Serializable{	
	
	private String feeString;
	private String actionName;
	private String formName;
	private String selectRId;
	private String applicationId;
	private String totalFee;
	private String serviceFee;
	private String otherFee;
	private String registNumber;
	private String radiobuttonPayment;
	private String oldRegiNumberOne;
	private String oldRegiNumberTwo;
	private String oldRegiNumberThree;
	private String radiobuttonPayment1;
	private String cashReceiptId;
	private String chkChallanDel;
	private String reason;
	private String radiobutton;
	private String refId;
	private String dateOfReg;
	private String estampCode;
	private String rrNo;
	private String execDate;
	private String presetDate;
	private String adjudicationAppNo;
	private String propLoanAmt;
	private String pendingPropTax;
	private String propCharges;
	private String existPropTransact;
	private String locSplitPart;
	private String distName;
	private String tehisilName;
	private String areaType;
	private String gorvMunicplBody;;
	private String vikasKhandName;
	private String riCircle;
	private String layoutDet;
	private String khasaraNo;
	private String nazoolStreetNo;
	private String wardORpathivasHalka;
	private String wardNo;
	private String mohallaId;
	private String mohallaName;
	private String address;
	private String propType;
	private String useType;
	private String total;
	private String bookId;
	private String bookName;
	private String constuctedArea;
	private String ceilingType;
	private String[] subclause;
	private String subclauseName;
	private String northBoundary;
	private String southBoundary;
	private String eastBoundary;
	private String complianceName;
	private String westBoundary;
	private String[] complianceListName;
	private String[] complianceListStatus;
	private String[] AttachmentTypeName;
	private String[] AttachmentTypeStatus;
	private PartyDetailsDTO partyDTO = new PartyDetailsDTO();
	private ArrayList partyList = new ArrayList();
	private ArrayList propertyList = new ArrayList();
	private ArrayList distList = new ArrayList();
	private ArrayList tehsiList = new ArrayList();
	private ArrayList areaList = new ArrayList();
	private ArrayList wardList = new ArrayList();
	private ArrayList bookList = new ArrayList();
	private ArrayList patwariList = new ArrayList();
	private ArrayList mohallaList = new ArrayList();
	private ArrayList villageList = new ArrayList();
	private ArrayList typeBresultList = new ArrayList();
	private ArrayList typeBresult = new ArrayList();
	private ArrayList subclauseList = new ArrayList();
	private ArrayList caveatslist = new ArrayList();
	private ArrayList complianceList = new ArrayList();
	private ArrayList subAreaTypeList=new ArrayList();
	private String fromDate;
	private String toDate;
	private String distId;
	private String tehisilId;
	private String areaTypeId;
	private String areaTypeName;
	private String villageId;
	private String villageName;
	private String buyerAddress;
	private String buyerParents;
	private String sellerParents;
	private String sellerAddress;
	private String buyerName;
	private String sellerType;
	private String buyerType;
	private String sellerName;
	private String selectedBuyer;
	private String selectedSeller;
	private String buyerGender;
	private String sellerGender;
	private String propertyAddr;
	private String viewFee;
	private String downloadFee;
	private String caveats;
	private String physicChallng;
	private String gender;
	private String maxPropertyVal;
	private String minPropertyVal;
	private String wardId;
	private String wardName;
	private String patwariId;
	private String patwariName;
	private String wardpatwarId;
    private String wardpatwarName;
    private String errorName;
    private String success;
    private String searchType;
    //added by shruti
    private String hdnDocId;
    private String serviceType;
    private String logCriteria; 
    private String userId;
    private String userType;
    private String recordCount;
    private int srno; 
    private String srchdUserId;
    private String regnoFlag;
    
    private String sroName;
    private String srName;
    private String bookNo;
    private String volNo;
    private String serialNo;
    private String regDate;
    private String searchDate;
    private String oldRegNo;
  //added by Shreeraj
    private String wardPatName; 
    private String tehsilName;    
    private String areaName;
    private String warddName;
    private String mohName;
    private String transPartyFirstName;
    private String transPartyMiddName;
    private String transPartyLastName;
    private String transPartyGender;
    private String transPartyMotName;
    private String transPartyFatName;
    private String transPartyAdd;    
    private String propertyId;
    private String regFlag;
    private String khasraNumber;
    private String khasraName;    
    private String lagaan;
    private String rinPushtikaNumber;
    private String distId1;
    private String flag;	
	private String oldDistNameB;
	private String toDate1;
	private String fromDate1;	
	private String fullName;
	private String orgName;
	private ArrayList khasraList=new ArrayList();
	private ArrayList distList1 = new ArrayList();
	private ArrayList protestList=new ArrayList();
	private ArrayList bankChargeList=new ArrayList();
	private ArrayList financialYearList = new ArrayList();
	//added by shruti
	private String parentOfficeId;
	private String parentOfficeName;
	private String parentDistrictId;
	private String parentDistrictName;
	private String parentReferenceId;
	private String userTypeId;
	private String amt;
	
	private String  subAreaTypeId;
	private String subAreaTypeName;
	private String subAreaWardMappingId;
	private String hdnSubAreaWardMappingId;
	
	private String propMap;
	private String dms;
	private String port;
	
	FormFile offUpload=null;
	private byte[] offUploadContents;
	private String offUploadDoc;
	
	private String docName;
	private String docPath;
	
	private String regDistId;
	private String regDistName;
	private ArrayList regDistList=new ArrayList();
	
	private String regTehsilId;
	private String regTehsilName;
	private ArrayList regTehsilList=new ArrayList();
	private String hdnRegDistId;
	private String hdnRegTehsilId;
	
	private String hdnDistName;
	private String hdnTehsilName;
	private String goLiveDate;
	private String radioButton1;
	private String regNo;
	private float paidAmount=0;
	private float amounttobePaid=0;
	private int isCompletePay=0;
	private String courtName;
	private String courtOrderNo;
	private String courtOrderDate;
	private String nullVoidFlag;
	private String protestFlag;
	private String protestId;
	private String protestDocPath;
	private String protestDocName;
	private String path;
	private String bankChargeId;
	private String caveatStatus;
	private String caveatUploadedDoc;
	private String protestTxnId;
	private String courtOrderDocPath;
	private String courtOrderDoc;
	private String protestStatus;
	private String fiscalYearId;
	private String fiscalYearName;
	

	

	public int getIsCompletePay() {
		return isCompletePay;
	}

	public void setIsCompletePay(int isCompletePay) {
		this.isCompletePay = isCompletePay;
	}

	public float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public float getAmounttobePaid() {
		return amounttobePaid;
	}

	public void setAmounttobePaid(float amounttobePaid) {
		this.amounttobePaid = amounttobePaid;
	}

	public String getRadioButton1() {
		return radioButton1;
	}

	public void setRadioButton1(String radioButton1) {
		this.radioButton1 = radioButton1;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getGoLiveDate() {
		return goLiveDate;
	}

	public void setGoLiveDate(String goLiveDate) {
		this.goLiveDate = goLiveDate;
	}

	public String getHdnDistName() {
		return hdnDistName;
	}

	public void setHdnDistName(String hdnDistName) {
		this.hdnDistName = hdnDistName;
	}

	public String getHdnTehsilName() {
		return hdnTehsilName;
	}

	public void setHdnTehsilName(String hdnTehsilName) {
		this.hdnTehsilName = hdnTehsilName;
	}

	public String getHdnRegDistId() {
		return hdnRegDistId;
	}

	public void setHdnRegDistId(String hdnRegDistId) {
		this.hdnRegDistId = hdnRegDistId;
	}

	public String getHdnRegTehsilId() {
		return hdnRegTehsilId;
	}

	public void setHdnRegTehsilId(String hdnRegTehsilId) {
		this.hdnRegTehsilId = hdnRegTehsilId;
	}

	public String getRegTehsilId() {
		return regTehsilId;
	}

	public void setRegTehsilId(String regTehsilId) {
		this.regTehsilId = regTehsilId;
	}

	public String getRegTehsilName() {
		return regTehsilName;
	}

	public void setRegTehsilName(String regTehsilName) {
		this.regTehsilName = regTehsilName;
	}

	public ArrayList getRegTehsilList() {
		return regTehsilList;
	}

	public void setRegTehsilList(ArrayList regTehsilList) {
		this.regTehsilList = regTehsilList;
	}

	public ArrayList getRegDistList() {
		return regDistList;
	}

	public void setRegDistList(ArrayList regDistList) {
		this.regDistList = regDistList;
	}

	public String getRegDistId() {
		return regDistId;
	}

	public void setRegDistId(String regDistId) {
		this.regDistId = regDistId;
	}

	public String getRegDistName() {
		return regDistName;
	}

	public void setRegDistName(String regDistName) {
		this.regDistName = regDistName;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	
	public String getOffUploadDoc() {
		return offUploadDoc;
	}

	public void setOffUploadDoc(String offUploadDoc) {
		this.offUploadDoc = offUploadDoc;
	}

	public byte[] getOffUploadContents() {
		return offUploadContents;
	}

	public void setOffUploadContents(byte[] offUploadContents) {
		this.offUploadContents = offUploadContents;
	}

	public FormFile getOffUpload() {
		return offUpload;
	}

	public void setOffUpload(FormFile offUpload) {
		this.offUpload = offUpload;
	}

	public String getDms() {
		return dms;
	}

	public void setDms(String dms) {
		this.dms = dms;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPropMap() {
		return propMap;
	}

	public void setPropMap(String propMap) {
		this.propMap = propMap;
	}

	public String getHdnSubAreaWardMappingId() {
		return hdnSubAreaWardMappingId;
	}

	public void setHdnSubAreaWardMappingId(String hdnSubAreaWardMappingId) {
		this.hdnSubAreaWardMappingId = hdnSubAreaWardMappingId;
	}

	public String getSubAreaWardMappingId() {
		return subAreaWardMappingId;
	}

	public void setSubAreaWardMappingId(String subAreaWardMappingId) {
		this.subAreaWardMappingId = subAreaWardMappingId;
	}

	public ArrayList getSubAreaTypeList() {
		return subAreaTypeList;
	}

	public void setSubAreaTypeList(ArrayList subAreaTypeList) {
		this.subAreaTypeList = subAreaTypeList;
	}
	
	public String getSubAreaTypeId() {
		return subAreaTypeId;
	}

	public void setSubAreaTypeId(String subAreaTypeId) {
		this.subAreaTypeId = subAreaTypeId;
	}

	public String getSubAreaTypeName() {
		return subAreaTypeName;
	}

	public void setSubAreaTypeName(String subAreaTypeName) {
		this.subAreaTypeName = subAreaTypeName;
	}
	
    public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getParentOfficeId() {
		return parentOfficeId;
	}

	public void setParentOfficeId(String parentOfficeId) {
		this.parentOfficeId = parentOfficeId;
	}

	public String getParentOfficeName() {
		return parentOfficeName;
	}

	public void setParentOfficeName(String parentOfficeName) {
		this.parentOfficeName = parentOfficeName;
	}

	public String getParentDistrictId() {
		return parentDistrictId;
	}

	public void setParentDistrictId(String parentDistrictId) {
		this.parentDistrictId = parentDistrictId;
	}

	public String getParentDistrictName() {
		return parentDistrictName;
	}

	public void setParentDistrictName(String parentDistrictName) {
		this.parentDistrictName = parentDistrictName;
	}

	public String getParentReferenceId() {
		return parentReferenceId;
	}

	public void setParentReferenceId(String parentReferenceId) {
		this.parentReferenceId = parentReferenceId;
	}

	public String getWardPatName() {
		return wardPatName;
	}

	public void setWardPatName(String wardPatName) {
		this.wardPatName = wardPatName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getWarddName() {
		return warddName;
	}

	public void setWarddName(String warddName) {
		this.warddName = warddName;
	}

	public String getMohName() {
		return mohName;
	}

	public void setMohName(String mohName) {
		this.mohName = mohName;
	}

	public String getTransPartyFirstName() {
		return transPartyFirstName;
	}

	public void setTransPartyFirstName(String transPartyFirstName) {
		this.transPartyFirstName = transPartyFirstName;
	}

	public String getTransPartyMiddName() {
		return transPartyMiddName;
	}

	public void setTransPartyMiddName(String transPartyMiddName) {
		this.transPartyMiddName = transPartyMiddName;
	}

	public String getTransPartyLastName() {
		return transPartyLastName;
	}

	public void setTransPartyLastName(String transPartyLastName) {
		this.transPartyLastName = transPartyLastName;
	}

	public String getTransPartyGender() {
		return transPartyGender;
	}

	public void setTransPartyGender(String transPartyGender) {
		this.transPartyGender = transPartyGender;
	}

	public String getTransPartyMotName() {
		return transPartyMotName;
	}

	public void setTransPartyMotName(String transPartyMotName) {
		this.transPartyMotName = transPartyMotName;
	}

	public String getTransPartyFatName() {
		return transPartyFatName;
	}

	public void setTransPartyFatName(String transPartyFatName) {
		this.transPartyFatName = transPartyFatName;
	}

	public String getTransPartyAdd() {
		return transPartyAdd;
	}

	public void setTransPartyAdd(String transPartyAdd) {
		this.transPartyAdd = transPartyAdd;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	public String getKhasraNumber() {
		return khasraNumber;
	}

	public void setKhasraNumber(String khasraNumber) {
		this.khasraNumber = khasraNumber;
	}

	public String getKhasraName() {
		return khasraName;
	}

	public void setKhasraName(String khasraName) {
		this.khasraName = khasraName;
	}

	public String getLagaan() {
		return lagaan;
	}

	public void setLagaan(String lagaan) {
		this.lagaan = lagaan;
	}

	public String getRinPushtikaNumber() {
		return rinPushtikaNumber;
	}

	public void setRinPushtikaNumber(String rinPushtikaNumber) {
		this.rinPushtikaNumber = rinPushtikaNumber;
	}

	public String getDistId1() {
		return distId1;
	}

	public void setDistId1(String distId1) {
		this.distId1 = distId1;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOldDistNameB() {
		return oldDistNameB;
	}

	public void setOldDistNameB(String oldDistNameB) {
		this.oldDistNameB = oldDistNameB;
	}

	public String getToDate1() {
		return toDate1;
	}

	public void setToDate1(String toDate1) {
		this.toDate1 = toDate1;
	}

	public String getFromDate1() {
		return fromDate1;
	}

	public void setFromDate1(String fromDate1) {
		this.fromDate1 = fromDate1;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public ArrayList getKhasraList() {
		return khasraList;
	}

	public void setKhasraList(ArrayList khasraList) {
		this.khasraList = khasraList;
	}

	public ArrayList getDistList1() {
		return distList1;
	}

	public void setDistList1(ArrayList distList1) {
		this.distList1 = distList1;
	}

	public String getOldRegNo() {
		return oldRegNo;
	}

	public void setOldRegNo(String oldRegNo) {
		this.oldRegNo = oldRegNo;
	}

	public String getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}

	private ArrayList sroList = new ArrayList();
    
	
	public ArrayList getSroList() {
		return sroList;
	}

	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	public String getSroName() {
		return sroName;
	}

	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	public String getSrName() {
		return srName;
	}

	public void setSrName(String srName) {
		this.srName = srName;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getVolNo() {
		return volNo;
	}

	public void setVolNo(String volNo) {
		this.volNo = volNo;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegnoFlag() {
		return regnoFlag;
	}

	public void setRegnoFlag(String regnoFlag) {
		this.regnoFlag = regnoFlag;
	}

	public String getSrchdUserId() {
		return srchdUserId;
	}

	public void setSrchdUserId(String srchdUserId) {
		this.srchdUserId = srchdUserId;
	}

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getLogCriteria() {
		return logCriteria;
	}

	public void setLogCriteria(String logCriteria) {
		this.logCriteria = logCriteria;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getHdnDocId() {
		return hdnDocId;
	}

	public void setHdnDocId(String hdnDocId) {
		this.hdnDocId = hdnDocId;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getWardpatwarId() {
		return wardpatwarId;
	}

	public void setWardpatwarId(String wardpatwarId) {
		this.wardpatwarId = wardpatwarId;
	}

	public String getWardpatwarName() {
		return wardpatwarName;
	}

	public void setWardpatwarName(String wardpatwarName) {
		this.wardpatwarName = wardpatwarName;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getPatwariId() {
		return patwariId;
	}

	public void setPatwariId(String patwariId) {
		this.patwariId = patwariId;
	}

	public String getPatwariName() {
		return patwariName;
	}

	public void setPatwariName(String patwariName) {
		this.patwariName = patwariName;
	}

	public ArrayList getPartyList() {
		return partyList;
	}

	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}

	public ArrayList getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}

	public String getRegistNumber() {
		return registNumber;
	}

	public void setRegistNumber(String registNumber) {
		this.registNumber = registNumber;
	}

	public String getRadiobuttonPayment() {
		return radiobuttonPayment;
	}

	public void setRadiobuttonPayment(String radiobuttonPayment) {
		this.radiobuttonPayment = radiobuttonPayment;
	}

	public String getOldRegiNumberOne() {
		return oldRegiNumberOne;
	}

	public void setOldRegiNumberOne(String oldRegiNumberOne) {
		this.oldRegiNumberOne = oldRegiNumberOne;
	}

	public String getOldRegiNumberTwo() {
		return oldRegiNumberTwo;
	}

	public void setOldRegiNumberTwo(String oldRegiNumberTwo) {
		this.oldRegiNumberTwo = oldRegiNumberTwo;
	}

	public String getOldRegiNumberThree() {
		return oldRegiNumberThree;
	}

	public void setOldRegiNumberThree(String oldRegiNumberThree) {
		this.oldRegiNumberThree = oldRegiNumberThree;
	}

	public String getRadiobuttonPayment1() {
		return radiobuttonPayment1;
	}

	public void setRadiobuttonPayment1(String modeofPaymentradio) {
		this.radiobuttonPayment1 = modeofPaymentradio;
	}

	public String getCashReceiptId() {
		return cashReceiptId;
	}

	public void setCashReceiptId(String cashReceiptId) {
		this.cashReceiptId = cashReceiptId;
	}

	public String getChkChallanDel() {
		return chkChallanDel;
	}

	public void setChkChallanDel(String chkChallanDel) {
		this.chkChallanDel = chkChallanDel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	

	public String getRadiobutton() {
		return radiobutton;
	}

	public void setRadiobutton(String radiobutton) {
		this.radiobutton = radiobutton;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getDateOfReg() {
		return dateOfReg;
	}

	public void setDateOfReg(String dateOfReg) {
		this.dateOfReg = dateOfReg;
	}

	public String getEstampCode() {
		return estampCode;
	}

	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}

	public String getRrNo() {
		return rrNo;
	}

	public void setRrNo(String rrNo) {
		this.rrNo = rrNo;
	}

	public String getExecDate() {
		return execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getPresetDate() {
		return presetDate;
	}

	public void setPresetDate(String presetDate) {
		this.presetDate = presetDate;
	}

	public String getAdjudicationAppNo() {
		return adjudicationAppNo;
	}

	public void setAdjudicationAppNo(String adjudicationAppNo) {
		this.adjudicationAppNo = adjudicationAppNo;
	}

	public String getPropLoanAmt() {
		return propLoanAmt;
	}

	public void setPropLoanAmt(String propLoanAmt) {
		this.propLoanAmt = propLoanAmt;
	}

	public String getPendingPropTax() {
		return pendingPropTax;
	}

	public void setPendingPropTax(String pendingPropTax) {
		this.pendingPropTax = pendingPropTax;
	}

	public String getPropCharges() {
		return propCharges;
	}

	public void setPropCharges(String propCharges) {
		this.propCharges = propCharges;
	}

	public String getExistPropTransact() {
		return existPropTransact;
	}

	public void setExistPropTransact(String existPropTransact) {
		this.existPropTransact = existPropTransact;
	}

	public String getLocSplitPart() {
		return locSplitPart;
	}

	public void setLocSplitPart(String locSplitPart) {
		this.locSplitPart = locSplitPart;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getTehisilName() {
		return tehisilName;
	}

	public void setTehisilName(String tehisilName) {
		this.tehisilName = tehisilName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getGorvMunicplBody() {
		return gorvMunicplBody;
	}

	public void setGorvMunicplBody(String gorvMunicplBody) {
		this.gorvMunicplBody = gorvMunicplBody;
	}

	public String getVikasKhandName() {
		return vikasKhandName;
	}

	public void setVikasKhandName(String vikasKhandName) {
		this.vikasKhandName = vikasKhandName;
	}

	public String getRiCircle() {
		return riCircle;
	}

	public ArrayList getComplianceList() {
		return complianceList;
	}

	public void setComplianceList(ArrayList complianceList) {
		this.complianceList = complianceList;
	}

	public void setRiCircle(String riCircle) {
		this.riCircle = riCircle;
	}

	public String getLayoutDet() {
		return layoutDet;
	}

	public void setLayoutDet(String layoutDet) {
		this.layoutDet = layoutDet;
	}

	public String getKhasaraNo() {
		return khasaraNo;
	}

	public void setKhasaraNo(String khasaraNo) {
		this.khasaraNo = khasaraNo;
	}

	public String getNazoolStreetNo() {
		return nazoolStreetNo;
	}

	public void setNazoolStreetNo(String nazoolStreetNo) {
		this.nazoolStreetNo = nazoolStreetNo;
	}

	public String getWardORpathivasHalka() {
		return wardORpathivasHalka;
	}

	public void setWardORpathivasHalka(String wardORpathivasHalka) {
		this.wardORpathivasHalka = wardORpathivasHalka;
	}

	public String getWardNo() {
		return wardNo;
	}

	public void setWardNo(String wardNo) {
		this.wardNo = wardNo;
	}

	public String getMohallaName() {
		return mohallaName;
	}

	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getConstuctedArea() {
		return constuctedArea;
	}

	public void setConstuctedArea(String constuctedArea) {
		this.constuctedArea = constuctedArea;
	}

	public String getCeilingType() {
		return ceilingType;
	}

	public void setCeilingType(String ceilingType) {
		this.ceilingType = ceilingType;
	}

	public String[] getSubclause() {
		return subclause;
	}

	public void setSubclause(String[] subclause) {
		this.subclause = subclause;
	}

	public String getNorthBoundary() {
		return northBoundary;
	}

	public void setNorthBoundary(String northBoundary) {
		this.northBoundary = northBoundary;
	}

	public String getSouthBoundary() {
		return southBoundary;
	}

	public void setSouthBoundary(String southBoundary) {
		this.southBoundary = southBoundary;
	}

	public String getEastBoundary() {
		return eastBoundary;
	}

	public void setEastBoundary(String eastBoundary) {
		this.eastBoundary = eastBoundary;
	}

	public String getWestBoundary() {
		return westBoundary;
	}

	public void setWestBoundary(String westBoundary) {
		this.westBoundary = westBoundary;
	}

	public String[] getComplianceListName() {
		return complianceListName;
	}

	public void setComplianceListName(String[] complianceListName) {
		this.complianceListName = complianceListName;
	}

	public String[] getComplianceListStatus() {
		return complianceListStatus;
	}

	public void setComplianceListStatus(String[] complianceListStatus) {
		this.complianceListStatus = complianceListStatus;
	}

	public String[] getAttachmentTypeName() {
		return AttachmentTypeName;
	}

	public void setAttachmentTypeName(String[] attachmentTypeName) {
		AttachmentTypeName = attachmentTypeName;
	}

	public String[] getAttachmentTypeStatus() {
		return AttachmentTypeStatus;
	}

	public void setAttachmentTypeStatus(String[] attachmentTypeStatus) {
		AttachmentTypeStatus = attachmentTypeStatus;
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

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getTehisilId() {
		return tehisilId;
	}

	public void setTehisilId(String tehisilId) {
		this.tehisilId = tehisilId;
	}

	public ArrayList getDistList() {
		return distList;
	}

	public void setDistList(ArrayList distList) {
		this.distList = distList;
	}

	public ArrayList getTehsiList() {
		return tehsiList;
	}

	public void setTehsiList(ArrayList tehsiList) {
		this.tehsiList = tehsiList;
	}

	public String getAreaTypeId() {
		return areaTypeId;
	}

	public void setAreaTypeId(String areaTypeId) {
		this.areaTypeId = areaTypeId;
	}

	public String getAreaTypeName() {
		return areaTypeName;
	}

	public void setAreaTypeName(String areaTypeName) {
		this.areaTypeName = areaTypeName;
	}

	public ArrayList getAreaList() {
		return areaList;
	}

	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
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

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
	}

	public ArrayList getMohallaList() {
		return mohallaList;
	}

	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	public ArrayList getVillageList() {
		return villageList;
	}

	public void setVillageList(ArrayList villageList) {
		this.villageList = villageList;
	}

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getBuyerGender() {
		return buyerGender;
	}

	public void setBuyerGender(String buyerGender) {
		this.buyerGender = buyerGender;
	}

	public String getSellerGender() {
		return sellerGender;
	}

	public void setSellerGender(String sellerGender) {
		this.sellerGender = sellerGender;
	}

	public String getPropertyAddr() {
		return propertyAddr;
	}

	public void setPropertyAddr(String propertyAddr) {
		this.propertyAddr = propertyAddr;
	}

	public ArrayList getTypeBresultList() {
		return typeBresultList;
	}

	public void setTypeBresultList(ArrayList typeBresultList) {
		this.typeBresultList = typeBresultList;
	}

	public String getViewFee() {
		return viewFee;
	}

	public void setViewFee(String viewFee) {
		this.viewFee = viewFee;
	}

	public String getDownloadFee() {
		return downloadFee;
	}

	public void setDownloadFee(String downloadFee) {
		this.downloadFee = downloadFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getFeeString() {
		return feeString;
	}

	public void setFeeString(String feeString) {
		this.feeString = feeString;
	}

	public String getSelectRId() {
		return selectRId;
	}

	public void setSelectRId(String selectRId) {
		this.selectRId = selectRId;
	}

	public ArrayList getTypeBresult() {
		return typeBresult;
	}

	public void setTypeBresult(ArrayList typeBresult) {
		this.typeBresult = typeBresult;
	}

	public String getSubclauseName() {
		return subclauseName;
	}

	public void setSubclauseName(String subclauseName) {
		this.subclauseName = subclauseName;
	}
	
	public ArrayList getCaveatslist() {
		return caveatslist;
	}

	public void setCaveatslist(ArrayList caveatslist) {
		this.caveatslist = caveatslist;
	}

	public ArrayList getSubclauseList() {
		return subclauseList;
	}

	public void setSubclauseList(ArrayList subclauseList) {
		this.subclauseList = subclauseList;
	}

	public String getComplianceName() {
		return complianceName;
	}

	public void setComplianceName(String complianceName) {
		this.complianceName = complianceName;
	}

	public PartyDetailsDTO getPartyDTO() {
		return partyDTO;
	}

	public void setPartyDTO(PartyDetailsDTO partyDTO) {
		this.partyDTO = partyDTO;
	}

	public String getSelectedBuyer() {
		return selectedBuyer;
	}

	public void setSelectedBuyer(String selectedBuyer) {
		this.selectedBuyer = selectedBuyer;
	}

	public String getSelectedSeller() {
		return selectedSeller;
	}

	public void setSelectedSeller(String selectedSeller) {
		this.selectedSeller = selectedSeller;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerParents() {
		return buyerParents;
	}

	public void setBuyerParents(String buyerParents) {
		this.buyerParents = buyerParents;
	}

	public String getSellerParents() {
		return sellerParents;
	}

	public void setSellerParents(String sellerParents) {
		this.sellerParents = sellerParents;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
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

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getBuyerType() {
		return buyerType;
	}

	public void setBuyerType(String buyerType) {
		this.buyerType = buyerType;
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

	public ArrayList getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList bookList) {
		this.bookList = bookList;
	}

	public String getCaveats() {
		return caveats;
	}

	public void setCaveats(String caveats) {
		this.caveats = caveats;
	}

	public String getPhysicChallng() {
		return physicChallng;
	}

	public void setPhysicChallng(String physicChallng) {
		this.physicChallng = physicChallng;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaxPropertyVal() {
		return maxPropertyVal;
	}

	public void setMaxPropertyVal(String maxPropertyVal) {
		this.maxPropertyVal = maxPropertyVal;
	}

	public String getMinPropertyVal() {
		return minPropertyVal;
	}

	public void setMinPropertyVal(String minPropertyVal) {
		this.minPropertyVal = minPropertyVal;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getCourtOrderNo() {
		return courtOrderNo;
	}

	public void setCourtOrderNo(String courtOrderNo) {
		this.courtOrderNo = courtOrderNo;
	}

	public String getCourtOrderDate() {
		return courtOrderDate;
	}

	public void setCourtOrderDate(String courtOrderDate) {
		this.courtOrderDate = courtOrderDate;
	}

	public String getNullVoidFlag() {
		return nullVoidFlag;
	}

	public void setNullVoidFlag(String nullVoidFlag) {
		this.nullVoidFlag = nullVoidFlag;
	}

	public String getProtestFlag() {
		return protestFlag;
	}

	public void setProtestFlag(String protestFlag) {
		this.protestFlag = protestFlag;
	}

	public String getProtestId() {
		return protestId;
	}

	public void setProtestId(String protestId) {
		this.protestId = protestId;
	}

	public String getProtestDocPath() {
		return protestDocPath;
	}

	public void setProtestDocPath(String protestDocPath) {
		this.protestDocPath = protestDocPath;
	}

	public String getProtestDocName() {
		return protestDocName;
	}

	public void setProtestDocName(String protestDocName) {
		this.protestDocName = protestDocName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBankChargeId() {
		return bankChargeId;
	}

	public void setBankChargeId(String bankChargeId) {
		this.bankChargeId = bankChargeId;
	}

	public String getCaveatStatus() {
		return caveatStatus;
	}

	public void setCaveatStatus(String caveatStatus) {
		this.caveatStatus = caveatStatus;
	}

	public String getProtestTxnId() {
		return protestTxnId;
	}

	public void setProtestTxnId(String protestTxnId) {
		this.protestTxnId = protestTxnId;
	}

	public String getCourtOrderDocPath() {
		return courtOrderDocPath;
	}

	public void setCourtOrderDocPath(String courtOrderDocPath) {
		this.courtOrderDocPath = courtOrderDocPath;
	}

	public String getCourtOrderDoc() {
		return courtOrderDoc;
	}

	public void setCourtOrderDoc(String courtOrderDoc) {
		this.courtOrderDoc = courtOrderDoc;
	}

	public String getProtestStatus() {
		return protestStatus;
	}

	public void setProtestStatus(String protestStatus) {
		this.protestStatus = protestStatus;
	}

	public ArrayList getProtestList() {
		return protestList;
	}

	public void setProtestList(ArrayList protestList) {
		this.protestList = protestList;
	}

	public String getCaveatUploadedDoc() {
		return caveatUploadedDoc;
	}

	public void setCaveatUploadedDoc(String caveatUploadedDoc) {
		this.caveatUploadedDoc = caveatUploadedDoc;
	}

	public ArrayList getBankChargeList() {
		return bankChargeList;
	}

	public void setBankChargeList(ArrayList bankChargeList) {
		this.bankChargeList = bankChargeList;
	}

	public String getFiscalYearId() {
		return fiscalYearId;
	}

	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	public String getFiscalYearName() {
		return fiscalYearName;
	}

	public void setFiscalYearName(String fiscalYearName) {
		this.fiscalYearName = fiscalYearName;
	}

	public ArrayList getFinancialYearList() {
		return financialYearList;
	}

	public void setFinancialYearList(ArrayList financialYearList) {
		this.financialYearList = financialYearList;
	}
	
	//added by saurav for language selection in document search type A
	private String langSelect;
	public String getLangSelect() {
		return langSelect;
	}

	public void setLangSelect(String langSelect) {
		this.langSelect = langSelect;
	}
	private String errorMessage;
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	//added by saurav for segregating financial year wise and property wise data
	private String docSearchType;


	public String getDocSearchType() {
		return docSearchType;
	}

	public void setDocSearchType(String docSearchType) {
		this.docSearchType = docSearchType;
	}
	
	//added by saurav for adding party name and address in document search
	private String partyName;
	private String propertyAddress;
	private String partyAddress;
	/*private ArrayList<String> partyData;
	private ArrayList<String> propertyData;

	private ArrayList<String> orgData;
	private ArrayList<String> indvData;
	private ArrayList<String> govtData;*/


	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public String getPartyAddress() {
		return partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}
	
	private String partySearchCheck;




	public String getPartySearchCheck() {
		return partySearchCheck;
	}

	public void setPartySearchCheck(String partySearchCheck) {
		this.partySearchCheck = partySearchCheck;
	}
	
	private String oldDocFilePath;




	public String getOldDocFilePath() {
		return oldDocFilePath;
	}

	public void setOldDocFilePath(String oldDocFilePath) {
		this.oldDocFilePath = oldDocFilePath;
	}
	
	public String oldDocBuyerName;
	public String oldDocSellerName;
	public String oldDocBuyerAddress;
	public String oldDocSellerAddress;
	public String oldDocDistrict;
	public String oldDocTehsil;
	public String oldDocArea;
	public String oldDocWardName;
	public String oldDocColonyName;
	public String oldDocPropAddress;
	public String oldPropType;
	public String oldDocType;
	public String oldDocKhasraNum;
	public String oldDocConsiVal;
	public String oldDocMarketValue;
	public ArrayList oldPropList;
	public String oldDocPlotNum;
	public String oldDocFY;
	public String oldDocRegDate;
	

	public String getOldDocRegDate() {
		return oldDocRegDate;
	}

	public void setOldDocRegDate(String oldDocRegDate) {
		this.oldDocRegDate = oldDocRegDate;
	}

	public String getOldDocFY() {
		return oldDocFY;
	}

	public void setOldDocFY(String oldDocFY) {
		this.oldDocFY = oldDocFY;
	}

	public String getOldDocPlotNum() {
		return oldDocPlotNum;
	}

	public void setOldDocPlotNum(String oldDocPlotNum) {
		this.oldDocPlotNum = oldDocPlotNum;
	}

	public ArrayList getOldPropList() {
		return oldPropList;
	}

	public void setOldPropList(ArrayList oldPropList) {
		this.oldPropList = oldPropList;
	}

	public String getOldDocBuyerName() {
		return oldDocBuyerName;
	}

	public void setOldDocBuyerName(String oldDocBuyerName) {
		this.oldDocBuyerName = oldDocBuyerName;
	}

	public String getOldDocSellerName() {
		return oldDocSellerName;
	}

	public void setOldDocSellerName(String oldDocSellerName) {
		this.oldDocSellerName = oldDocSellerName;
	}

	

	public String getOldDocBuyerAddress() {
		return oldDocBuyerAddress;
	}

	public void setOldDocBuyerAddress(String oldDocBuyerAddress) {
		this.oldDocBuyerAddress = oldDocBuyerAddress;
	}

	public String getOldDocSellerAddress() {
		return oldDocSellerAddress;
	}

	public void setOldDocSellerAddress(String oldDocSellerAddress) {
		this.oldDocSellerAddress = oldDocSellerAddress;
	}

	public String getOldDocDistrict() {
		return oldDocDistrict;
	}

	public void setOldDocDistrict(String oldDocDistrict) {
		this.oldDocDistrict = oldDocDistrict;
	}

	public String getOldDocTehsil() {
		return oldDocTehsil;
	}

	public void setOldDocTehsil(String oldDocTehsil) {
		this.oldDocTehsil = oldDocTehsil;
	}

	public String getOldDocArea() {
		return oldDocArea;
	}

	public void setOldDocArea(String oldDocArea) {
		this.oldDocArea = oldDocArea;
	}

	public String getOldDocWardName() {
		return oldDocWardName;
	}

	public void setOldDocWardName(String oldDocWardName) {
		this.oldDocWardName = oldDocWardName;
	}

	public String getOldDocColonyName() {
		return oldDocColonyName;
	}

	public void setOldDocColonyName(String oldDocColonyName) {
		this.oldDocColonyName = oldDocColonyName;
	}

	public String getOldDocPropAddress() {
		return oldDocPropAddress;
	}

	public void setOldDocPropAddress(String oldDocPropAddress) {
		this.oldDocPropAddress = oldDocPropAddress;
	}

	public String getOldPropType() {
		return oldPropType;
	}

	public void setOldPropType(String oldPropType) {
		this.oldPropType = oldPropType;
	}

	public String getOldDocType() {
		return oldDocType;
	}

	public void setOldDocType(String oldDocType) {
		this.oldDocType = oldDocType;
	}

	public String getOldDocKhasraNum() {
		return oldDocKhasraNum;
	}

	public void setOldDocKhasraNum(String oldDocKhasraNum) {
		this.oldDocKhasraNum = oldDocKhasraNum;
	}

	public String getOldDocConsiVal() {
		return oldDocConsiVal;
	}

	public void setOldDocConsiVal(String oldDocConsiVal) {
		this.oldDocConsiVal = oldDocConsiVal;
	}

	public String getOldDocMarketValue() {
		return oldDocMarketValue;
	}

	public void setOldDocMarketValue(String oldDocMarketValue) {
		this.oldDocMarketValue = oldDocMarketValue;
	}
	
	
/*
	public ArrayList<String> getPartyData() {
		return partyData;
	}

	public void setPartyData(ArrayList<String> partyData) {
		this.partyData = partyData;
	}

	public ArrayList<String> getPropertyData() {
		return propertyData;
	}

	public void setPropertyData(ArrayList<String> propertyData) {
		this.propertyData = propertyData;
	}

	public ArrayList<String> getOrgData() {
		return orgData;
	}

	public void setOrgData(ArrayList<String> orgData) {
		this.orgData = orgData;
	}

	public ArrayList<String> getIndvData() {
		return indvData;
	}

	public void setIndvData(ArrayList<String> indvData) {
		this.indvData = indvData;
	}

	public ArrayList<String> getGovtData() {
		return govtData;
	}

	public void setGovtData(ArrayList<String> govtData) {
		this.govtData = govtData;
	}
	*/
	
}
