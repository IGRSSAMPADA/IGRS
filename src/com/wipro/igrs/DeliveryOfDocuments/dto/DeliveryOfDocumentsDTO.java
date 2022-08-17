package com.wipro.igrs.DeliveryOfDocuments.dto;


import java.util.ArrayList;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;



public class DeliveryOfDocumentsDTO extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	//----as entered by user
	private String regNo;
	private String statusSelected;
	private String statusSelectedName;
	private String fromRequestDate;
	private String toRequestDate;
	private String repFname;
	private String repMname;
	private String repLname;
	//--for displaying in display tag
	private String selecRegNo;
	private String regCompDt;
	private String docStatusID;
	private String docStatusName;
	private String regCumStatus;
	private String[]regCumDetls;
	private ArrayList regDetails=new ArrayList();
	//------to diaplay in iterator---
	private String partyNm;
	private String partyNmDisplay;
	private ArrayList transDetails=new ArrayList();
	private ArrayList transDetailsDisplay=new ArrayList();
	private String chckBx;
	private String chckArry;
	//-----payment related-----------
	private String payableFee;
	private String alrdyPaidFee;
	private String totalFee; 
	private String primKeyPymt;
	private String flg;
	//--------for confirmation Page----
	private String regNoDisplay;
	private String regComDateDisplay;
	private String lastDelDateDisplay;
	private String lastDueDateDisplay;
	private String statusDisplay;
	private String statusDateDisplay;
	private String userNameDisplay;
	private String officeNameDisplay;
	private String drNameDisplay;
	private String drOfficeDisplay;
	private String docDelToDisplay;
	private String otherNameDisplay;
	private String authLetterNmDisplay;
	private String radioSelect;
	private FormFile filePhoto2;
	private String docPath;
	//--for notice-------
	private String noticeRegNoDisplay;
	private String noticeRegComDateDisplay;
	private String noticeLastDelDateDisplay;
	private String noticeLastDueDateDisplay;
	private String noticeStatusDisplay;
	private String noticeStatusDateDisplay;
	private String noticeUserNameDisplay;
	private String noticeOfficeNameDisplay;
	private String noticeRemarks;
	private String noticePartyRole;
	private String noticePartyNm;
	private String noticePartyAdrs;
	private String noticecumdtls;
	private ArrayList noticeDetails=new ArrayList();
	//--for postal delivery-----
	private String postlAddrs;
	private String postlDist;
	private String postlState;
	private String postlCntry;
	private String postlDocketNo;
	//--for messages-----
	private int noRecFound=0;
	private int isrepresentative=0;
	private int pendingApproval=0;
	private int notInsertedDestroy=0;
	private int isdestroyed=0;
	private int partyOrRep=0;
	private int isOneMnOver=0;
	private int isPartial=0;
	private int notInsertedDestroyP=0;
	private int isApproved=0;
	private int notInsertedApprove=0;
	private int notInsertedNotice=0;
	private int isNoticeIssued=0;
	private int isIssued=0;
	private int iscompletePay=0;
	private int isLinkToBeShown=0;
	private int notInsertedPost =0;
	private int isSavedPost = 0;
	
	
	//added by Shreeraj
	private String fileNameOwm;
	private String regInitNumber;
	private String owmChk;
	private String signatureChkFlag="N";
	private String signatureChkhand="N";

	private String parentPathSign;
	private String fileNameSign;
	private String forwardName;
	private String forwardPath;
	private String partyIdUpload;
	private String witIdUpload;
	private String fingerPrintName;
	private String fingerPrintPath;
	private String signatureName;
	private String parentPathSrSign;
	private String fileNameSrSign;
	private String forwardPathSr;
	private String srSign;
	private String fileNameGovtLttr;
	private String parentPathFP;
	private String fileNameFP;
	private String parentPathGovtLttr;
	private String guidUpload;
	private String deedID;
	private String instID;
	private String pinRequired;
	private String propertyID;
	private String claimantName;
	private String claimantNumber;
	private ArrayList claimantList=new ArrayList();
	private String signStatus;
	private String recptName;
	private String handPath;
	private String[] arr;
	private String regCompAppletNo;
	
	//for print status change--Rupali
	private String deliveryStatusName;
	private String srID;
	//by Shreeraj
	private String funID;
	
	
	
	public String getFunID() {
		return funID;
	}
	public void setFunID(String funID) {
		this.funID = funID;
	}
	public String getRegCompAppletNo() {
		return regCompAppletNo;
	}
	public void setRegCompAppletNo(String regCompAppletNo) {
		this.regCompAppletNo = regCompAppletNo;
	}
	public String[] getArr() {
		return arr;
	}
	public void setArr(String[] arr) {
		this.arr = arr;
	}
	public String getRecptName() {
		return recptName;
	}
	public void setRecptName(String recptName) {
		this.recptName = recptName;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public ArrayList getClaimantList() {
		return claimantList;
	}
	public void setClaimantList(ArrayList claimantList) {
		this.claimantList = claimantList;
	}
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getClaimantName() {
		return claimantName;
	}
	public void setClaimantName(String claimantName) {
		this.claimantName = claimantName;
	}
	public String getClaimantNumber() {
		return claimantNumber;
	}
	public void setClaimantNumber(String claimantNumber) {
		this.claimantNumber = claimantNumber;
	}
	public String getPinRequired() {
		return pinRequired;
	}
	public void setPinRequired(String pinRequired) {
		this.pinRequired = pinRequired;
	}
	public String getDeedID() {
		return deedID;
	}
	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}
	public String getInstID() {
		return instID;
	}
	public void setInstID(String instID) {
		this.instID = instID;
	}
	public String getGuidUpload() {
		return guidUpload;
	}
	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}
	public String getParentPathGovtLttr() {
		return parentPathGovtLttr;
	}
	public void setParentPathGovtLttr(String parentPathGovtLttr) {
		this.parentPathGovtLttr = parentPathGovtLttr;
	}
	public String getFileNameGovtLttr() {
		return fileNameGovtLttr;
	}
	public void setFileNameGovtLttr(String fileNameGovtLttr) {
		this.fileNameGovtLttr = fileNameGovtLttr;
	}
	public String getParentPathFP() {
		return parentPathFP;
	}
	public void setParentPathFP(String parentPathFP) {
		this.parentPathFP = parentPathFP;
	}
	public String getFileNameFP() {
		return fileNameFP;
	}
	public void setFileNameFP(String fileNameFP) {
		this.fileNameFP = fileNameFP;
	}
	public String getParentPathSign() {
		return parentPathSign;
	}
	public void setParentPathSign(String parentPathSign) {
		this.parentPathSign = parentPathSign;
	}
	public String getFileNameSign() {
		return fileNameSign;
	}
	public void setFileNameSign(String fileNameSign) {
		this.fileNameSign = fileNameSign;
	}
	public String getForwardName() {
		return forwardName;
	}
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}
	public String getForwardPath() {
		return forwardPath;
	}
	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}
	public String getPartyIdUpload() {
		return partyIdUpload;
	}
	public void setPartyIdUpload(String partyIdUpload) {
		this.partyIdUpload = partyIdUpload;
	}
	public String getWitIdUpload() {
		return witIdUpload;
	}
	public void setWitIdUpload(String witIdUpload) {
		this.witIdUpload = witIdUpload;
	}
	public String getFingerPrintName() {
		return fingerPrintName;
	}
	public void setFingerPrintName(String fingerPrintName) {
		this.fingerPrintName = fingerPrintName;
	}
	public String getFingerPrintPath() {
		return fingerPrintPath;
	}
	public void setFingerPrintPath(String fingerPrintPath) {
		this.fingerPrintPath = fingerPrintPath;
	}
	public String getSignatureName() {
		return signatureName;
	}
	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}
	public String getParentPathSrSign() {
		return parentPathSrSign;
	}
	public void setParentPathSrSign(String parentPathSrSign) {
		this.parentPathSrSign = parentPathSrSign;
	}
	public String getFileNameSrSign() {
		return fileNameSrSign;
	}
	public void setFileNameSrSign(String fileNameSrSign) {
		this.fileNameSrSign = fileNameSrSign;
	}
	public String getForwardPathSr() {
		return forwardPathSr;
	}
	public void setForwardPathSr(String forwardPathSr) {
		this.forwardPathSr = forwardPathSr;
	}
	public String getSrSign() {
		return srSign;
	}
	public void setSrSign(String srSign) {
		this.srSign = srSign;
	}
	public String getSignatureChkFlag() {
		return signatureChkFlag;
	}
	public void setSignatureChkFlag(String signatureChkFlag) {
		this.signatureChkFlag = signatureChkFlag;
	}
	public String getFileNameOwm() {
		return fileNameOwm;
	}
	public void setFileNameOwm(String fileNameOwm) {
		this.fileNameOwm = fileNameOwm;
	}
	public String getRegInitNumber() {
		return regInitNumber;
	}
	public void setRegInitNumber(String regInitNumber) {
		this.regInitNumber = regInitNumber;
	}

	public String getOwmChk() {
		return owmChk;
	}
	public void setOwmChk(String owmChk) {
		this.owmChk = owmChk;
	}
	public int getNotInsertedPost() {
		return notInsertedPost;
	}
	public void setNotInsertedPost(int notInsertedPost) {
		this.notInsertedPost = notInsertedPost;
	}
	public int getIsSavedPost() {
		return isSavedPost;
	}
	public void setIsSavedPost(int isSavedPost) {
		this.isSavedPost = isSavedPost;
	}
	public String getPostlAddrs() {
		return postlAddrs;
	}
	public void setPostlAddrs(String postlAddrs) {
		this.postlAddrs = postlAddrs;
	}
	public String getPostlDist() {
		return postlDist;
	}
	public void setPostlDist(String postlDist) {
		this.postlDist = postlDist;
	}
	public String getPostlState() {
		return postlState;
	}
	public void setPostlState(String postlState) {
		this.postlState = postlState;
	}
	public String getPostlCntry() {
		return postlCntry;
	}
	public void setPostlCntry(String postlCntry) {
		this.postlCntry = postlCntry;
	}
	public String getPostlDocketNo() {
		return postlDocketNo;
	}
	public void setPostlDocketNo(String postlDocketNo) {
		this.postlDocketNo = postlDocketNo;
	}
	public int getIsLinkToBeShown() {
		return isLinkToBeShown;
	}
	public void setIsLinkToBeShown(int isLinkToBeShown) {
		this.isLinkToBeShown = isLinkToBeShown;
	}
	public int getIscompletePay() {
		return iscompletePay;
	}
	public void setIscompletePay(int iscompletePay) {
		this.iscompletePay = iscompletePay;
	}
	public int getIsIssued() {
		return isIssued;
	}
	public void setIsIssued(int isIssued) {
		this.isIssued = isIssued;
	}
	public String getNoticeRemarks() {
		return noticeRemarks;
	}
	public void setNoticeRemarks(String noticeRemarks) {
		this.noticeRemarks = noticeRemarks;
	}
	public String getNoticecumdtls() {
		return noticecumdtls;
	}
	public void setNoticecumdtls(String noticecumdtls) {
		this.noticecumdtls = noticecumdtls;
	}
	public ArrayList getNoticeDetails() {
		return noticeDetails;
	}
	public void setNoticeDetails(ArrayList noticeDetails) {
		this.noticeDetails = noticeDetails;
	}
	public String getNoticeRegNoDisplay() {
		return noticeRegNoDisplay;
	}
	public void setNoticeRegNoDisplay(String noticeRegNoDisplay) {
		this.noticeRegNoDisplay = noticeRegNoDisplay;
	}
	public String getNoticeRegComDateDisplay() {
		return noticeRegComDateDisplay;
	}
	public void setNoticeRegComDateDisplay(String noticeRegComDateDisplay) {
		this.noticeRegComDateDisplay = noticeRegComDateDisplay;
	}
	public String getNoticeLastDelDateDisplay() {
		return noticeLastDelDateDisplay;
	}
	public void setNoticeLastDelDateDisplay(String noticeLastDelDateDisplay) {
		this.noticeLastDelDateDisplay = noticeLastDelDateDisplay;
	}
	public String getNoticeLastDueDateDisplay() {
		return noticeLastDueDateDisplay;
	}
	public void setNoticeLastDueDateDisplay(String noticeLastDueDateDisplay) {
		this.noticeLastDueDateDisplay = noticeLastDueDateDisplay;
	}
	public String getNoticeStatusDisplay() {
		return noticeStatusDisplay;
	}
	public void setNoticeStatusDisplay(String noticeStatusDisplay) {
		this.noticeStatusDisplay = noticeStatusDisplay;
	}
	public String getNoticeStatusDateDisplay() {
		return noticeStatusDateDisplay;
	}
	public void setNoticeStatusDateDisplay(String noticeStatusDateDisplay) {
		this.noticeStatusDateDisplay = noticeStatusDateDisplay;
	}
	public String getNoticeUserNameDisplay() {
		return noticeUserNameDisplay;
	}
	public void setNoticeUserNameDisplay(String noticeUserNameDisplay) {
		this.noticeUserNameDisplay = noticeUserNameDisplay;
	}
	public String getNoticeOfficeNameDisplay() {
		return noticeOfficeNameDisplay;
	}
	public void setNoticeOfficeNameDisplay(String noticeOfficeNameDisplay) {
		this.noticeOfficeNameDisplay = noticeOfficeNameDisplay;
	}
	public String getNoticePartyRole() {
		return noticePartyRole;
	}
	public void setNoticePartyRole(String noticePartyRole) {
		this.noticePartyRole = noticePartyRole;
	}
	public String getNoticePartyNm() {
		return noticePartyNm;
	}
	public void setNoticePartyNm(String noticePartyNm) {
		this.noticePartyNm = noticePartyNm;
	}
	public String getNoticePartyAdrs() {
		return noticePartyAdrs;
	}
	public void setNoticePartyAdrs(String noticePartyAdrs) {
		this.noticePartyAdrs = noticePartyAdrs;
	}
	public int getNotInsertedNotice() {
		return notInsertedNotice;
	}
	public void setNotInsertedNotice(int notInsertedNotice) {
		this.notInsertedNotice = notInsertedNotice;
	}
	public int getIsNoticeIssued() {
		return isNoticeIssued;
	}
	public void setIsNoticeIssued(int isNoticeIssued) {
		this.isNoticeIssued = isNoticeIssued;
	}
	public int getNotInsertedApprove() {
		return notInsertedApprove;
	}
	public void setNotInsertedApprove(int notInsertedApprove) {
		this.notInsertedApprove = notInsertedApprove;
	}
	public int getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}
	public int getNotInsertedDestroyP() {
		return notInsertedDestroyP;
	}
	public void setNotInsertedDestroyP(int notInsertedDestroyP) {
		this.notInsertedDestroyP = notInsertedDestroyP;
	}
	public int getIsPartial() {
		return isPartial;
	}
	public void setIsPartial(int isPartial) {
		this.isPartial = isPartial;
	}
	public String getFlg() {
		return flg;
	}
	public void setFlg(String flg) {
		this.flg = flg;
	}
	public String getPrimKeyPymt() {
		return primKeyPymt;
	}
	public void setPrimKeyPymt(String primKeyPymt) {
		this.primKeyPymt = primKeyPymt;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public FormFile getFilePhoto2() {
		return filePhoto2;
	}
	public void setFilePhoto2(FormFile filePhoto2) {
		this.filePhoto2 = filePhoto2;
	}
	public String getPayableFee() {
		return payableFee;
	}
	public void setPayableFee(String payableFee) {
		this.payableFee = payableFee;
	}
	public String getAlrdyPaidFee() {
		return alrdyPaidFee;
	}
	public void setAlrdyPaidFee(String alrdyPaidFee) {
		this.alrdyPaidFee = alrdyPaidFee;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	public int getIsOneMnOver() {
		return isOneMnOver;
	}
	public void setIsOneMnOver(int isOneMnOver) {
		this.isOneMnOver = isOneMnOver;
	}
	public ArrayList getTransDetailsDisplay() {
		return transDetailsDisplay;
	}
	public void setTransDetailsDisplay(ArrayList transDetailsDisplay) {
		this.transDetailsDisplay = transDetailsDisplay;
	}
	public String getPartyNmDisplay() {
		return partyNmDisplay;
	}
	public void setPartyNmDisplay(String partyNmDisplay) {
		this.partyNmDisplay = partyNmDisplay;
	}
	public String getChckArry() {
		return chckArry;
	}
	public void setChckArry(String chckArry) {
		this.chckArry = chckArry;
	}
	public String getChckBx() {
		return chckBx;
	}
	public void setChckBx(String chckBx) {
		this.chckBx = chckBx;
	}
	public ArrayList getTransDetails() {
		return transDetails;
	}
	public void setTransDetails(ArrayList transDetails) {
		this.transDetails = transDetails;
	}
	public String getPartyNm() {
		return partyNm;
	}
	public void setPartyNm(String partyNm) {
		this.partyNm = partyNm;
	}
	public String getRepFname() {
		return repFname;
	}
	public void setRepFname(String repFname) {
		this.repFname = repFname;
	}
	public String getRepMname() {
		return repMname;
	}
	public void setRepMname(String repMname) {
		this.repMname = repMname;
	}
	public String getRepLname() {
		return repLname;
	}
	public void setRepLname(String repLname) {
		this.repLname = repLname;
	}
	public int getPartyOrRep() {
		return partyOrRep;
	}
	public void setPartyOrRep(int partyOrRep) {
		this.partyOrRep = partyOrRep;
	}
	public String getRadioSelect() {
		return radioSelect;
	}
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}
	public String getDrNameDisplay() {
		return drNameDisplay;
	}
	public void setDrNameDisplay(String drNameDisplay) {
		this.drNameDisplay = drNameDisplay;
	}
	public String getDrOfficeDisplay() {
		return drOfficeDisplay;
	}
	public void setDrOfficeDisplay(String drOfficeDisplay) {
		this.drOfficeDisplay = drOfficeDisplay;
	}
	public int getNotInsertedDestroy() {
		return notInsertedDestroy;
	}
	public void setNotInsertedDestroy(int notInsertedDestroy) {
		this.notInsertedDestroy = notInsertedDestroy;
	}
	public int getIsdestroyed() {
		return isdestroyed;
	}
	public void setIsdestroyed(int isdestroyed) {
		this.isdestroyed = isdestroyed;
	}
	public String[] getRegCumDetls() {
		return regCumDetls;
	}
	public void setRegCumDetls(String[] regCumDetls) {
		this.regCumDetls = regCumDetls;
	}
	public ArrayList getRegDetails() {
		return regDetails;
	}
	public void setRegDetails(ArrayList regDetails) {
		this.regDetails = regDetails;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getPendingApproval() {
		return pendingApproval;
	}
	public void setPendingApproval(int pendingApproval) {
		this.pendingApproval = pendingApproval;
	}
	public String getLastDueDateDisplay() {
		return lastDueDateDisplay;
	}
	public void setLastDueDateDisplay(String lastDueDateDisplay) {
		this.lastDueDateDisplay = lastDueDateDisplay;
	}
	public String getAuthLetterNmDisplay() {
		return authLetterNmDisplay;
	}
	public void setAuthLetterNmDisplay(String authLetterNmDisplay) {
		this.authLetterNmDisplay = authLetterNmDisplay;
	}
	public String getRegNoDisplay() {
		return regNoDisplay;
	}
	public void setRegNoDisplay(String regNoDisplay) {
		this.regNoDisplay = regNoDisplay;
	}
	public String getRegComDateDisplay() {
		return regComDateDisplay;
	}
	public void setRegComDateDisplay(String regComDateDisplay) {
		this.regComDateDisplay = regComDateDisplay;
	}
	public String getLastDelDateDisplay() {
		return lastDelDateDisplay;
	}
	public void setLastDelDateDisplay(String lastDelDateDisplay) {
		this.lastDelDateDisplay = lastDelDateDisplay;
	}
	public String getStatusDisplay() {
		return statusDisplay;
	}
	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}
	public String getStatusDateDisplay() {
		return statusDateDisplay;
	}
	public void setStatusDateDisplay(String statusDateDisplay) {
		this.statusDateDisplay = statusDateDisplay;
	}
	public String getUserNameDisplay() {
		return userNameDisplay;
	}
	public void setUserNameDisplay(String userNameDisplay) {
		this.userNameDisplay = userNameDisplay;
	}
	public String getOfficeNameDisplay() {
		return officeNameDisplay;
	}
	public void setOfficeNameDisplay(String officeNameDisplay) {
		this.officeNameDisplay = officeNameDisplay;
	}
	public String getDocDelToDisplay() {
		return docDelToDisplay;
	}
	public void setDocDelToDisplay(String docDelToDisplay) {
		this.docDelToDisplay = docDelToDisplay;
	}
	public String getOtherNameDisplay() {
		return otherNameDisplay;
	}
	public void setOtherNameDisplay(String otherNameDisplay) {
		this.otherNameDisplay = otherNameDisplay;
	}
	public int getIsrepresentative() {
		return isrepresentative;
	}
	public void setIsrepresentative(int isrepresentative) {
		this.isrepresentative = isrepresentative;
	}
	public String getRegCumStatus() {
		return regCumStatus;
	}
	public void setRegCumStatus(String regCumStatus) {
		this.regCumStatus = regCumStatus;
	}
	public int getNoRecFound() {
		return noRecFound;
	}
	public void setNoRecFound(int noRecFound) {
		this.noRecFound = noRecFound;
	}
	public String getDocStatusID() {
		return docStatusID;
	}
	public void setDocStatusID(String docStatusID) {
		this.docStatusID = docStatusID;
	}
	public String getDocStatusName() {
		return docStatusName;
	}
	public void setDocStatusName(String docStatusName) {
		this.docStatusName = docStatusName;
	}
	public String getSelecRegNo() {
		return selecRegNo;
	}
	public void setSelecRegNo(String selecRegNo) {
		this.selecRegNo = selecRegNo;
	}
	public String getRegCompDt() {
		return regCompDt;
	}
	public void setRegCompDt(String regCompDt) {
		this.regCompDt = regCompDt;
	}
	
	public String getStatusSelected() {
		return statusSelected;
	}
	public void setStatusSelected(String statusSelected) {
		this.statusSelected = statusSelected;
	}
	public String getStatusSelectedName() {
		return statusSelectedName;
	}
	public void setStatusSelectedName(String statusSelectedName) {
		this.statusSelectedName = statusSelectedName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getFromRequestDate() {
		return fromRequestDate;
	}
	public void setFromRequestDate(String fromRequestDate) {
		this.fromRequestDate = fromRequestDate;
	}
	public String getToRequestDate() {
		return toRequestDate;
	}
	public void setToRequestDate(String toRequestDate) {
		this.toRequestDate = toRequestDate;
	}
	
	
	/********* Language Parameter added by SIMRAN****************/
	
	private String language;

	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setSignatureChkhand(String signatureChkhand) {
		this.signatureChkhand = signatureChkhand;
	}
	public String getSignatureChkhand() {
		return signatureChkhand;
	}
	public void setHandPath(String handPath) {
		this.handPath = handPath;
	}
	public String getHandPath() {
		return handPath;
	}
	public String getSrID() {
		return srID;
	}
	public void setSrID(String srID) {
		this.srID = srID;
	}
	
	
	
	
}