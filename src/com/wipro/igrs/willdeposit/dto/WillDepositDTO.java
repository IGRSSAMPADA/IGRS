/* Copyright (c) 2007-08 WIPRO INFOTECH. All Rights Reserved.
 * 
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *  
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE 
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE 
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF 
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS 
 * DERIVATIVES.
 */ 

/* 
 * FILE NAME: WillDepositDTO.java
 * @author NIHAR R MISHRA
 * @version 1.0 
 * INITIAL CODING: 28th DEC 2007 
 * MODIFIED ON:    11th MAY 2008 
 * @DESCRIPTION:   THIS CLASS REPRESENTS THE ACTION FOR GUIDELINE DELIEVRY UPDATION 
 */


package com.wipro.igrs.willdeposit.dto;


import java.util.ArrayList;

import org.apache.struts.upload.FormFile;


/**
 * @author NIHAR M.
 *
 */
public class WillDepositDTO {

	private String willId;
	private String drID;
	private String confirmRefId;
	private String agentType;
	private ArrayList agentTypes;
	private String firstName;
	private String midName;
	private String lastName;
	private String gender;
	private String age;
	private String guardianName;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private String address;
	private String city;
	private String district;
	private String country;
	private ArrayList countrys;
	private String state;
	private ArrayList states;
	private String pin;
	private String phone;
	private String mphone;
	private String email;
	private String idProof;
	private ArrayList idProofs;
	private String idProofNo;
	private String despositDate;
	private String deliveredChecked;
	
	private String bankName;
	private String bankAddress;
	private String agentBankName;
	private String agentBankAddress;
	
	private String testator;
	private String agent;
	private String fromDepositeDate;
	private String toDepositeDate;
	private String willWithdrawalFlag;
	private String willRetrievalID;
	private String agentFirstName;
	private String agentMidName;
	private String agentLastName;
	private String agentGender;
	private String agentAge;
	private String agentFatherName;
	private String agentMotherName;
	private String agentSpouseName;
	private String agentAddress;
	private String agentCity;
	private String agentCountry;
	private String agentState;
	private String agentPin;
	private String agentPhone;
	private String agentMPhone;
	private String agentEmail;
	private String agentIdProof;
	private String agentIdProofNo;
	private String fee;
	private String otherFee;
	private String totalFee;
	private String paymentType;
	private String cashReceiptNo;
	private String formName;
	private String withDrawalRemark;
	private String withDrawalReason;
	private String retrievalReason;
	private String hdnWillId;
	
	private String willRetrievalRemark;
	private String willRetrievalReason;
	private String deliveryRemark;
	private String retrieverType;
	private ArrayList retrieverTypes;
	private String retrieverFirstName;
	private String retrieverMidName;
	private String retrieverLastName;
	private String retrieverGender;
	private String retrieverAge;
	private String retrieverFatherName;
	private String retrieverMotherName;
	private String retrieverSpouseName;
	private String retrieverAddress;
	private String retrieverCity;
	private String retrieverCountry;
	private ArrayList retrieverCountrys;
	private String retrieverState;
	private ArrayList retrieverStates;
	private String retrieverPin;
	private String retrieverPhone;
	private String retrieverMPhone;
	private String retrieverEMail;
	private String retrieverIdProof;
	private String retrieverIdProofNo;
	private String retrieverBankName;
	private String retrieverBankAddress;
	
	
	private String testatorRelationship;
	
	
	private String courtName;
	private String repName;
	private String repDesg;
	private String courtAddress;
	private String courtCity;
	private String courtCountry;
	private String courtState;
	private String courtPin;
	private String courtPhone;
	
	private String repFirstName;
	private String repMiddleName;
	private String repLastName;
	
	private String repCountry;
	private String repState;
	private String repCity;
	
	
	private String willWithdrawForm;
	private String willDepositForm;
	private String willViewForm;
	private String willUpdateForm;
	private String willRetrievalForm;
	
	private String willDepositInsertAction;
	private String willDepositWithdrawalAction;
	private String willRetrievalAction;
	private String willIDDetailsRetrievalAction;
	private String willRetrievalDetailsSubmitAction;
	private String willViewDetailsAction;
	private String willWithdrawalAction;
	
	
	private String approvalType;
	private String withdrawlDueDate;
	private String retrievalDueDate;
	private String deliveryDueDate;
	private String radioPayment;
	private String actionName;
	private String remarks;
	
	private String countryId;
	private String stateId;
	private String districtId;
	private String agentCountryId;
	private String agentStateId;
	private String agentDistrictId;
	private ArrayList willRemarksList;
	private String willCommentsUser;
	private String willCommentsDate;
	
	private String thunmbName;
	private String signatureName;
	
	 private String photoCheck;
     private String thumbCheck;
     private String signCheck;
     private String photoSize;
     private String thumbSize;
     private String signatureSize;
     private String deathCertSize;
     
     private String deathCertCheck;
     private String documentName;
 	private FormFile filePhoto;
     private FormFile fileThumb;
     private FormFile fileSignature;
     private FormFile fileDeathCert;
     private byte[] docContents;
 	private byte[] deathCertDocContents;
 	//private String filePhoto;
 		private String filePhotoName;
 		//private String fileThumb;
 		private String fileThumbName;
 		//private String fileSignature;
 		private String fileSignatureName;
 		private String filePath;
 		private String fileCertificate;
 		private String fileCertificateName;
 		
 		private String retFilePhotoName;
 	     private String retFileThumbName;
 	     private String retFileSignatureName;
 	     
 	     private byte[] retPhotoContents;
 	     private byte[] retThumbContents;
 	     private byte[] retSignatureContents;
 	     
 	     private String retPhotoSize;
 	     private String retThumbSize;
 	     private String retSignatureSize;
 	     
 	     private String retPhotoCheck;
 	     private String retThumbCheck;
 	     private String retSignCheck;
 	     
 	     private String showTState;
 	     private String showTDist;
 	     
 	     private String showAState;
 	     private String showADist;
 		
 		private byte[] photoContents;
 		private byte[] thumbContents;
 		private byte[] signatureContents;
 		private byte[] certificateContents;
 		private String retThunmbName;
 	 	private String retSignatureName;
 		
 		
 	    private String parentFunName;
 	    private String parentModName;
 	    private String parentFunId;
 	   private String certificateName;
 	   
     private String retDocumentName;
     private byte[] retDocContents;

     private FormFile retFilePhoto = null;
     private FormFile retFileThumb = null;
     private FormFile retFileSignature = null;
     
     
     private String withWillId;
     private String retWillId;
     
     private String depositRemarks;
     private String treasuryId;
     
     private String agentProofName;
     private FormFile agentProof = null;
     private byte[] agentProofContents;
     private String agentProofSize;
     private String agentProofCheck;
     
     private boolean checkPhoto;
     private boolean checkSignature;
     private boolean checkThumb;
     private String dependantwillId;
     
     private String agentCategory;
     private int amount;
     
     private String docName;
     private String thumb;
     private String Signature;
     private String deathCerti;
     private String agentsproof;
     
     private String createdBy;
     
     private String photoPath;
     private String thumbPath;
     private String signPath;
     private String deathCertiPath;
     private String agentProofPath;
     
     private String retPhotoPath;
     private String retThumbPath;
     private String retSignPath;
     private String retDeathCertiPath;
     private String uniqueId;
     private String forwardName;
     private String forwardPath;
     
     
     private String payWillId;
     
     private String agentsName;
     private String compPhotoPath;
     private String compSignPath;
     private String compThumbPath;
     private String compCertiPath;
     
     private String dphotoPath;
     private String dsignPath;
     private String dthumbPath;
     private String dproofPath;
     
     private String scanName;
	private String scannedWill;
     private String compScanPath;
     private String scanCheck;
     
     private String showACountry;
     private String showTCountry;
     private String showGender;
   //DMS start
     private String serverIP;
     private String port;
     private String moduleNo;
     //DMS end
     
     private String retrieverIdProofName;
     private String will_stat_view;
     private String show_photo_id_proof;
     
     private String tryy;
     
     private String show;
     
     
     
     
     public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public String getTryy() {
		return tryy;
	}
	public void setTryy(String tryy) {
		this.tryy = tryy;
	}
	public String getShow_photo_id_proof() {
		return show_photo_id_proof;
	}
	public void setShow_photo_id_proof(String show_photo_id_proof) {
		this.show_photo_id_proof = show_photo_id_proof;
	}
	public String getWill_stat_view() {
		return will_stat_view;
	}
	public void setWill_stat_view(String will_stat_view) {
		this.will_stat_view = will_stat_view;
	}
	public String getRetrieverIdProofName() {
		return retrieverIdProofName;
	}
	public void setRetrieverIdProofName(String retrieverIdProofName) {
		this.retrieverIdProofName = retrieverIdProofName;
	}
	public String getShowGender() {
		return showGender;
	}
	public void setShowGender(String showGender) {
		this.showGender = showGender;
	}
     
     public String getScanName() {
 		return scanName;
 	}
 	public void setScanName(String scanName) {
 		this.scanName = scanName;
 	}
     
     public String getCompScanPath() {
		return compScanPath;
	}
	public void setCompScanPath(String compScanPath) {
		this.compScanPath = compScanPath;
	}
	public String getScanCheck() {
		return scanCheck;
	}
	public void setScanCheck(String scanCheck) {
		this.scanCheck = scanCheck;
	}
	public String getScannedWill() {
		return scannedWill;
	}
	public void setScannedWill(String scannedWill) {
		this.scannedWill = scannedWill;
	}
	public String getPayWillId() {
		return payWillId;
	}
	public void setPayWillId(String payWillId) {
		this.payWillId = payWillId;
	}
	public String getRetPhotoPath() {
		return retPhotoPath;
	}
	public void setRetPhotoPath(String retPhotoPath) {
		this.retPhotoPath = retPhotoPath;
	}
	public String getRetThumbPath() {
		return retThumbPath;
	}
	public void setRetThumbPath(String retThumbPath) {
		this.retThumbPath = retThumbPath;
	}
	public String getRetSignPath() {
		return retSignPath;
	}
	public void setRetSignPath(String retSignPath) {
		this.retSignPath = retSignPath;
	}
	public String getRetDeathCertiPath() {
		return retDeathCertiPath;
	}
	public void setRetDeathCertiPath(String retDeathCertiPath) {
		this.retDeathCertiPath = retDeathCertiPath;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getThumbPath() {
		return thumbPath;
	}
	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	public String getSignPath() {
		return signPath;
	}
	public void setSignPath(String signPath) {
		this.signPath = signPath;
	}
	public String getDeathCertiPath() {
		return deathCertiPath;
	}
	public void setDeathCertiPath(String deathCertiPath) {
		this.deathCertiPath = deathCertiPath;
	}
	public String getAgentProofPath() {
		return agentProofPath;
	}
	public void setAgentProofPath(String agentProofPath) {
		this.agentProofPath = agentProofPath;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAgentsproof() {
		return agentsproof;
	}
	public void setAgentsproof(String agentsproof) {
		this.agentsproof = agentsproof;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getDeathCerti() {
		return deathCerti;
	}
	public void setDeathCerti(String deathCerti) {
		this.deathCerti = deathCerti;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getAgentCategory() {
		return agentCategory;
	}
	public void setAgentCategory(String agentCategory) {
		this.agentCategory = agentCategory;
	}
	public String getAgentProofName() {
		return agentProofName;
	}
	public void setAgentProofName(String agentProofName) {
		this.agentProofName = agentProofName;
	}
	public FormFile getAgentProof() {
		return agentProof;
	}
	public void setAgentProof(FormFile agentProof) {
		this.agentProof = agentProof;
	}
	public byte[] getAgentProofContents() {
		return agentProofContents;
	}
	public void setAgentProofContents(byte[] agentProofContents) {
		this.agentProofContents = agentProofContents;
	}
	public String getAgentProofSize() {
		return agentProofSize;
	}
	public void setAgentProofSize(String agentProofSize) {
		this.agentProofSize = agentProofSize;
	}
	public String getAgentProofCheck() {
		return agentProofCheck;
	}
	public void setAgentProofCheck(String agentProofCheck) {
		this.agentProofCheck = agentProofCheck;
	}
	
     
     
     
     public String getTreasuryId() {
		return treasuryId;
	}
	public void setTreasuryId(String treasuryId) {
		this.treasuryId = treasuryId;
	}
	
	public String getWithWillId() {
		return withWillId;
	}
	public void setWithWillId(String withWillId) {
		this.withWillId = withWillId;
	}
	public String getRetWillId() {
		return retWillId;
	}
	public void setRetWillId(String retWillId) {
		this.retWillId = retWillId;
	}
	public FormFile getRetFileThumb() {
		return retFileThumb;
	}
	public void setRetFileThumb(FormFile retFileThumb) {
		this.retFileThumb = retFileThumb;
	}
	public FormFile getRetFileSignature() {
		return retFileSignature;
	}
	public void setRetFileSignature(FormFile retFileSignature) {
		this.retFileSignature = retFileSignature;
	}
	
     
     public String getRetThunmbName() {
		return retThunmbName;
	}
	public void setRetThunmbName(String retThunmbName) {
		this.retThunmbName = retThunmbName;
	}
	public String getRetSignatureName() {
		return retSignatureName;
	}
	public void setRetSignatureName(String retSignatureName) {
		this.retSignatureName = retSignatureName;
	}
	
     
     public String getShowTState() {
		return showTState;
	}
	public void setShowTState(String showTState) {
		this.showTState = showTState;
	}
	public String getShowTDist() {
		return showTDist;
	}
	public void setShowTDist(String showTDist) {
		this.showTDist = showTDist;
	}
	public String getShowAState() {
		return showAState;
	}
	public void setShowAState(String showAState) {
		this.showAState = showAState;
	}
	public String getShowADist() {
		return showADist;
	}
	public void setShowADist(String showADist) {
		this.showADist = showADist;
	}
	public String getRetDocumentName() {
		return retDocumentName;
	}
	public void setRetDocumentName(String retDocumentName) {
		this.retDocumentName = retDocumentName;
	}
	public byte[] getRetDocContents() {
		return retDocContents;
	}
	public void setRetDocContents(byte[] retDocContents) {
		this.retDocContents = retDocContents;
	}
	public FormFile getRetFilePhoto() {
		return retFilePhoto;
	}
	public void setRetFilePhoto(FormFile retFilePhoto) {
		this.retFilePhoto = retFilePhoto;
	}
	public String getRetFilePhotoName() {
		return retFilePhotoName;
	}
	public void setRetFilePhotoName(String retFilePhotoName) {
		this.retFilePhotoName = retFilePhotoName;
	}
	public String getRetFileThumbName() {
		return retFileThumbName;
	}
	public void setRetFileThumbName(String retFileThumbName) {
		this.retFileThumbName = retFileThumbName;
	}
	public String getRetFileSignatureName() {
		return retFileSignatureName;
	}
	public void setRetFileSignatureName(String retFileSignatureName) {
		this.retFileSignatureName = retFileSignatureName;
	}
	public byte[] getRetPhotoContents() {
		return retPhotoContents;
	}
	public void setRetPhotoContents(byte[] retPhotoContents) {
		this.retPhotoContents = retPhotoContents;
	}
	public byte[] getRetThumbContents() {
		return retThumbContents;
	}
	public void setRetThumbContents(byte[] retThumbContents) {
		this.retThumbContents = retThumbContents;
	}
	public byte[] getRetSignatureContents() {
		return retSignatureContents;
	}
	public void setRetSignatureContents(byte[] retSignatureContents) {
		this.retSignatureContents = retSignatureContents;
	}
	public String getRetPhotoSize() {
		return retPhotoSize;
	}
	public void setRetPhotoSize(String retPhotoSize) {
		this.retPhotoSize = retPhotoSize;
	}
	public String getRetThumbSize() {
		return retThumbSize;
	}
	public void setRetThumbSize(String retThumbSize) {
		this.retThumbSize = retThumbSize;
	}
	public String getRetSignatureSize() {
		return retSignatureSize;
	}
	public void setRetSignatureSize(String retSignatureSize) {
		this.retSignatureSize = retSignatureSize;
	}
	public String getRetPhotoCheck() {
		return retPhotoCheck;
	}
	public void setRetPhotoCheck(String retPhotoCheck) {
		this.retPhotoCheck = retPhotoCheck;
	}
	public String getRetThumbCheck() {
		return retThumbCheck;
	}
	public void setRetThumbCheck(String retThumbCheck) {
		this.retThumbCheck = retThumbCheck;
	}
	public String getRetSignCheck() {
		return retSignCheck;
	}
	public void setRetSignCheck(String retSignCheck) {
		this.retSignCheck = retSignCheck;
	}
	
     
     
     
     

	public String getDeathCertCheck() {
		return deathCertCheck;
	}
	public void setDeathCertCheck(String deathCertCheck) {
		this.deathCertCheck = deathCertCheck;
	}
	public String getDeathCertSize() {
		return deathCertSize;
	}
	public void setDeathCertSize(String deathCertSize) {
		this.deathCertSize = deathCertSize;
	}
	

	public FormFile getFileDeathCert() {
		return fileDeathCert;
	}
	public void setFileDeathCert(FormFile fileDeathCert) {
		this.fileDeathCert = fileDeathCert;
	}
	
	public byte[] getDeathCertDocContents() {
		return deathCertDocContents;
	}
	public void setDeathCertDocContents(byte[] deathCertDocContents) {
		this.deathCertDocContents = deathCertDocContents;
	}
	
    
    
   
	
	public String getSignatureName() {
		return signatureName;
	}
	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	public String getThunmbName() {
		return thunmbName;
	}
	public void setThunmbName(String thunmbName) {
		this.thunmbName = thunmbName;
	}

	public String getPhotoCheck() {
		return photoCheck;
	}
	public void setPhotoCheck(String photoCheck) {
		this.photoCheck = photoCheck;
	}
	public String getThumbCheck() {
		return thumbCheck;
	}
	public void setThumbCheck(String thumbCheck) {
		this.thumbCheck = thumbCheck;
	}
	public String getSignCheck() {
		return signCheck;
	}
	public void setSignCheck(String signCheck) {
		this.signCheck = signCheck;
	}
	public String getPhotoSize() {
		return photoSize;
	}
	public void setPhotoSize(String photoSize) {
		this.photoSize = photoSize;
	}
	public String getThumbSize() {
		return thumbSize;
	}
	public void setThumbSize(String thumbSize) {
		this.thumbSize = thumbSize;
	}
	public String getSignatureSize() {
		return signatureSize;
	}
	public void setSignatureSize(String signatureSize) {
		this.signatureSize = signatureSize;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getParentFunName() {
		return parentFunName;
	}
	public void setParentFunName(String parentFunName) {
		this.parentFunName = parentFunName;
	}
	public String getParentModName() {
		return parentModName;
	}
	public void setParentModName(String parentModName) {
		this.parentModName = parentModName;
	}
	public String getParentFunId() {
		return parentFunId;
	}
	public void setParentFunId(String parentFunId) {
		this.parentFunId = parentFunId;
	}
	/**
	 * willStatus
	 */
	private String willStatus;

	/**
	 * @param agentType
	 */
	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	/**
	 * @return String
	 */
	public String getAgentType() {
		return agentType;
	}
	/**
	 * @param agentTypes
	 */
	public void setAgentTypes(ArrayList agentTypes) {
		this.agentTypes = agentTypes;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getAgentTypes() {
		return agentTypes;
	}
	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param midName
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}
	/**
	 * @return String
	 */
	public String getMidName() {
		return midName;
	}
	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return String
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param fatherName
	 */
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	/**
	 * @return String
	 */
	public String getFatherName() {
		return fatherName;
	}
	/**
	 * @param motherName
	 */
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	/**
	 * @return String
	 */
	public String getMotherName() {
		return motherName;
	}
	/**
	 * @param spouseName
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	/**
	 * @return String
	 */
	public String getSpouseName() {
		return spouseName;
	}
	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return String 
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return String
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return String
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param countrys
	 */
	public void setCountrys(ArrayList countrys) {
		this.countrys = countrys;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getCountrys() {
		return countrys;
	}
	/**
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return String
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param states
	 */
	public void setStates(ArrayList states) {
		this.states = states;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getStates() {
		return states;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param idProof
	 */
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	/**
	 * @return String
	 */
	public String getIdProof() {
		return idProof;
	}
	/**
	 * @param idProofs
	 */
	public void setIdProofs(ArrayList idProofs) {
		this.idProofs = idProofs;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getIdProofs() {
		return idProofs;
	}
	/**
	 * @param idProofNo
	 */
	public void setIdProofNo(String idProofNo) {
		this.idProofNo = idProofNo;
	}
	/**
	 * @return String
	 */
	public String getIdProofNo() {
		return idProofNo;
	}
	/**
	 * @param agentFirstName
	 */
	public void setAgentFirstName(String agentFirstName) {
		this.agentFirstName = agentFirstName;
	}
	/**
	 * @return String
	 */
	public String getAgentFirstName() {
		return agentFirstName;
	}
	/**
	 * @param agentMidName
	 */
	public void setAgentMidName(String agentMidName) {
		this.agentMidName = agentMidName;
	}
	/**
	 * @return String
	 */
	public String getAgentMidName() {
		return agentMidName;
	}
	/**
	 * @param agentLastName
	 */
	public void setAgentLastName(String agentLastName) {
		this.agentLastName = agentLastName;
	}
	/**
	 * @return String
	 */
	public String getAgentLastName() {
		return agentLastName;
	}
	/**
	 * @param agentGender
	 */
	public void setAgentGender(String agentGender) {
		this.agentGender = agentGender;
	}
	/**
	 * @return String
	 */
	public String getAgentGender() {
		return agentGender;
	}

	/**
	 * @param agentFatherName
	 */
	public void setAgentFatherName(String agentFatherName) {
		this.agentFatherName = agentFatherName;
	}
	/**
	 * @return String
	 */
	public String getAgentFatherName() {
		return agentFatherName;
	}
	/**
	 * @param agentMotherName
	 */
	public void setAgentMotherName(String agentMotherName) {
		this.agentMotherName = agentMotherName;
	}
	/**
	 * @return String
	 */
	public String getAgentMotherName() {
		return agentMotherName;
	}
	/**
	 * @param agentSpouseName
	 */
	public void setAgentSpouseName(String agentSpouseName) {
		this.agentSpouseName = agentSpouseName;
	}
	/**
	 * @return String
	 */
	public String getAgentSpouseName() {
		return agentSpouseName;
	}
	/**
	 * @param agentAddress
	 */
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}
	/**
	 * @return String
	 */
	public String getAgentAddress() {
		return agentAddress;
	}
	/**
	 * @param agentCity
	 */
	public void setAgentCity(String agentCity) {
		this.agentCity = agentCity;
	}
	/**
	 * @return String
	 */
	public String getAgentCity() {
		return agentCity;
	}
	/**
	 * @param agentCountry
	 */
	public void setAgentCountry(String agentCountry) {
		this.agentCountry = agentCountry;
	}
	/**
	 * @return String
	 */
	public String getAgentCountry() {
		return agentCountry;
	}
	/**
	 * @param agentState
	 */
	public void setAgentState(String agentState) {
		this.agentState = agentState;
	}
	/**
	 * @return String
	 */
	public String getAgentState() {
		return agentState;
	}

	/**
	 * @param agentEmail
	 */
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	/**
	 * @return String
	 */
	public String getAgentEmail() {
		return agentEmail;
	}
	/**
	 * @param agentIdProofNo
	 */
	public void setAgentIdProofNo(String agentIdProofNo) {
		this.agentIdProofNo = agentIdProofNo;
	}
	/**
	 * @return String
	 */
	public String getAgentIdProofNo() {
		return agentIdProofNo;
	}
	/**
	 * @param fee
	 */
	public void setFee(String fee) {
		this.fee = fee;
	}
	/**
	 * @return String
	 */
	public String getFee() {
		return fee;
	}
	/**
	 * @param otherFee
	 */
	public void setOtherFee(String otherFee) {
		this.otherFee = otherFee;
	}
	/**
	 * @return String
	 */
	public String getOtherFee() {
		return otherFee;
	}
	/**
	 * @param total
	 */
	public void setTotalFee(String total) {
		this.totalFee = total;
	}
	/**
	 * @return String
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param paymentType
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	/**
	 * @return String
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param cashReceiptNo
	 */
	public void setCashReceiptNo(String cashReceiptNo) {
		this.cashReceiptNo = cashReceiptNo;
	}
	/**
	 * @return String
	 */
	public String getCashReceiptNo() {
		return cashReceiptNo;
	}
	/**
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}
	/**
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param withDrawalRemark
	 */
	public void setWithDrawalRemark(String withDrawalRemark) {
		this.withDrawalRemark = withDrawalRemark;
	}
	/**
	 * @return String
	 */
	public String getWithDrawalRemark() {
		return withDrawalRemark;
	}
	/**
	 * @param withDrawalReason
	 */
	public void setWithDrawalReason(String withDrawalReason) {
		this.withDrawalReason = withDrawalReason;
	}
	/**
	 * @return String
	 */
	public String getWithDrawalReason() {
		return withDrawalReason;
	}
	/**
	 * @param willId 
	 */
	public void setWillId(String willId) {
		this.willId = willId;
	}
	/**
	 * @return String
	 */
	public String getWillId() {
		return willId;
	}
	/**
	 * @param drID
	 */
	public void setDrID(String drID) {
		this.drID = drID;
	}
	/**
	 * @return String
	 */
	public String getDrID() {
		return drID;
	}
	/**
	 * @param retrieverType
	 */
	public void setRetrieverType(String retrieverType) {
		this.retrieverType = retrieverType;
	}
	/**
	 * @return String
	 */
	public String getRetrieverType() {
		return retrieverType;
	}
	/**
	 * @param retrieverTypes
	 */
	public void setRetrieverTypes(ArrayList retrieverTypes) {
		this.retrieverTypes = retrieverTypes;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getRetrieverTypes() {
		return retrieverTypes;
	}
	/**
	 * @param retrieverFirstName
	 */
	public void setRetrieverFirstName(String retrieverFirstName) {
		this.retrieverFirstName = retrieverFirstName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverFirstName() {
		return retrieverFirstName;
	}
	/**
	 * @param retrieverMidName
	 */
	public void setRetrieverMidName(String retrieverMidName) {
		this.retrieverMidName = retrieverMidName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverMidName() {
		return retrieverMidName;
	}
	/**
	 * @param retrieverLastName
	 */
	public void setRetrieverLastName(String retrieverLastName) {
		this.retrieverLastName = retrieverLastName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverLastName() {
		return retrieverLastName;
	}
	/**
	 * @param retrieverGender
	 */
	public void setRetrieverGender(String retrieverGender) {
		this.retrieverGender = retrieverGender;
	}
	/**
	 * @return String
	 */
	public String getRetrieverGender() {
		return retrieverGender;
	}

	/**
	 * @param retrieverFatherName
	 */
	public void setRetrieverFatherName(String retrieverFatherName) {
		this.retrieverFatherName = retrieverFatherName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverFatherName() {
		return retrieverFatherName;
	}
	/**
	 * @param retrieverMotherName
	 */
	public void setRetrieverMotherName(String retrieverMotherName) {
		this.retrieverMotherName = retrieverMotherName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverMotherName() {
		return retrieverMotherName;
	}
	/**
	 * @param retrieverSpouseName
	 */
	public void setRetrieverSpouseName(String retrieverSpouseName) {
		this.retrieverSpouseName = retrieverSpouseName;
	}
	/**
	 * @return String
	 */
	public String getRetrieverSpouseName() {
		return retrieverSpouseName;
	}
	/**
	 * @param retrieverAddress
	 */
	public void setRetrieverAddress(String retrieverAddress) {
		this.retrieverAddress = retrieverAddress;
	}
	/**
	 * @return String
	 */
	public String getRetrieverAddress() {
		return retrieverAddress;
	}
	/**
	 * @param retrieverCity
	 */
	public void setRetrieverCity(String retrieverCity) {
		this.retrieverCity = retrieverCity;
	}
	/**
	 * @return String
	 */
	public String getRetrieverCity() {
		return retrieverCity;
	}
	/**
	 * @param retrieverCountry
	 */
	public void setRetrieverCountry(String retrieverCountry) {
		this.retrieverCountry = retrieverCountry;
	}
	/**
	 * @return String
	 */
	public String getRetrieverCountry() {
		return retrieverCountry;
	}
	/**
	 * @param retrieverCountrys
	 */
	public void setRetrieverCountrys(ArrayList retrieverCountrys) {
		this.retrieverCountrys = retrieverCountrys;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getRetrieverCountrys() {
		return retrieverCountrys;
	}
	/**
	 * @param retrieverState
	 */
	public void setRetrieverState(String retrieverState) {
		this.retrieverState = retrieverState;
	}
	/**
	 * @return String
	 */
	public String getRetrieverState() {
		return retrieverState;
	}
	/**
	 * @param retrieverStates
	 */
	public void setRetrieverStates(ArrayList retrieverStates) {
		this.retrieverStates = retrieverStates;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getRetrieverStates() {
		return retrieverStates;
	}

	/**
	 * @param retrieverEMail
	 */
	public void setRetrieverEMail(String retrieverEMail) {
		this.retrieverEMail = retrieverEMail;
	}
	/**
	 * @return String
	 */
	public String getRetrieverEMail() {
		return retrieverEMail;
	}
	/**
	 * @param retrieverIdProof
	 */
	public void setRetrieverIdProof(String retrieverIdProof) {
		this.retrieverIdProof = retrieverIdProof;
	}
	/**
	 * @return String
	 */
	public String getRetrieverIdProof() {
		return retrieverIdProof;
	}
	/**
	 * @param courtName
	 */
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	/**
	 * @return String
	 */
	public String getCourtName() {
		return courtName;
	}
	/**
	 * @param repName
	 */
	public void setRepName(String repName) {
		this.repName = repName;
	}
	/**
	 * @return String
	 */
	public String getRepName() {
		return repName;
	}
	/**
	 * @param repDesg
	 */
	public void setRepDesg(String repDesg) {
		this.repDesg = repDesg;
	}
	/**
	 * @return String
	 */
	public String getRepDesg() {
		return repDesg;
	}
	/**
	 * @param courtAddress
	 */
	public void setCourtAddress(String courtAddress) {
		this.courtAddress = courtAddress;
	}
	/**
	 * @return String
	 */
	public String getCourtAddress() {
		return courtAddress;
	}
	/**
	 * @param courtCity 
	 */
	public void setCourtCity(String courtCity) {
		this.courtCity = courtCity;
	}
	/**
	 * @return String
	 */
	public String getCourtCity() {
		return courtCity;
	}
	/**
	 * @param courtCountry
	 */
	public void setCourtCountry(String courtCountry) {
		this.courtCountry = courtCountry;
	}
	/**
	 * @return String
	 */
	public String getCourtCountry() {
		return courtCountry;
	}
	/**
	 * @param courtState
	 */
	public void setCourtState(String courtState) {
		this.courtState = courtState;
	}
	/**
	 * @return String
	 */
	public String getCourtState() {
		return courtState;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getAgentAge() {
		return agentAge;
	}
	public void setAgentAge(String agentAge) {
		this.agentAge = agentAge;
	}
	public String getAgentPin() {
		return agentPin;
	}
	public void setAgentPin(String agentPin) {
		this.agentPin = agentPin;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getAgentMPhone() {
		return agentMPhone;
	}
	public void setAgentMPhone(String agentMPhone) {
		this.agentMPhone = agentMPhone;
	}
	public String getRetrieverAge() {
		return retrieverAge;
	}
	public void setRetrieverAge(String retrieverAge) {
		this.retrieverAge = retrieverAge;
	}
	public String getRetrieverPin() {
		return retrieverPin;
	}
	public void setRetrieverPin(String retrieverPin) {
		this.retrieverPin = retrieverPin;
	}
	public String getRetrieverPhone() {
		return retrieverPhone;
	}
	public void setRetrieverPhone(String retrieverPhone) {
		this.retrieverPhone = retrieverPhone;
	}
	public String getRetrieverMPhone() {
		return retrieverMPhone;
	}
	public void setRetrieverMPhone(String retrieverMPhone) {
		this.retrieverMPhone = retrieverMPhone;
	}
	public String getCourtPin() {
		return courtPin;
	}
	public void setCourtPin(String courtPin) {
		this.courtPin = courtPin;
	}
	public String getCourtPhone() {
		return courtPhone;
	}
	public void setCourtPhone(String courtPhone) {
		this.courtPhone = courtPhone;
	}
	/**
	 * @param despositDate
	 */
	public void setDespositDate(String despositDate) {
		this.despositDate = despositDate;
	}
	/**
	 * @return String
	 */
	public String getDespositDate() {
		return despositDate;
	}
	/**
	 * @param willDepositForm
	 */
	public void setWillDepositForm(String willDepositForm) {
		this.willDepositForm = willDepositForm;
	}
	/**
	 * @return String
	 */
	public String getWillDepositForm() {
		return willDepositForm;
	}
	/**
	 * @param willDepositInsertAction
	 */
	public void setWillDepositInsertAction(String willDepositInsertAction) {
		this.willDepositInsertAction = willDepositInsertAction;
	}
	/**
	 * @return String
	 */
	public String getWillDepositInsertAction() {
		return willDepositInsertAction;
	}
	/**
	 * @param willDepositWithdrawalAction
	 */
	public void setWillDepositWithdrawalAction(String
		 willDepositWithdrawalAction) {
		this.willDepositWithdrawalAction = willDepositWithdrawalAction;
	}
	/**
	 * @return String
	 */
	public String getWillDepositWithdrawalAction() {
		return willDepositWithdrawalAction;
	}
	/**
	 * @param testator
	 */
	public void setTestator(String testator) {
		this.testator = testator;
	}
	/**
	 * @return String
	 */
	public String getTestator() {
		return testator;
	}
	/**
	 * @param fromDepositeDate
	 */
	public void setFromDepositeDate(String fromDepositeDate) {
		this.fromDepositeDate = fromDepositeDate;
	}
	/**
	 * @return String
	 */
	public String getFromDepositeDate() {
		return fromDepositeDate;
	}
	/**
	 * @param toDepositeDate
	 */
	public void setToDepositeDate(String toDepositeDate) {
		this.toDepositeDate = toDepositeDate;
	}
	/**
	 * @return String
	 */
	public String getToDepositeDate() {
		return toDepositeDate;
	}
	/**
	 * @param willRetrievalAction
	 */
	public void setWillRetrievalAction(String willRetrievalAction) {
		this.willRetrievalAction = willRetrievalAction;
	}
	/**
	 * @return String
	 */
	public String getWillRetrievalAction() {
		return willRetrievalAction;
	}
	/**
	 * @param hdnWillId
	 */
	public void setHdnWillId(String hdnWillId) {
		this.hdnWillId = hdnWillId;
	}
	/**
	 * @return String
	 */
	public String getHdnWillId() {
		return hdnWillId;
	}
	/**
	 * @param willWithdrawalFlag
	 */
	public void setWillWithdrawalFlag(String willWithdrawalFlag) {
		this.willWithdrawalFlag = willWithdrawalFlag;
	}
	/**
	 * @return String
	 */
	public String getWillWithdrawalFlag() {
		return willWithdrawalFlag;
	}
	/**
	 * @param willRetrievalID
	 */
	public void setWillRetrievalID(String willRetrievalID) {
		this.willRetrievalID = willRetrievalID;
	}
	/**
	 * @return String
	 */
	public String getWillRetrievalID() {
		return willRetrievalID;
	}
	/**
	 * @param willIDDetailsRetrievalAction
	 */
	public void setWillIDDetailsRetrievalAction(String
		 willIDDetailsRetrievalAction) {
		this.willIDDetailsRetrievalAction = willIDDetailsRetrievalAction;
	}
	/**
	 * @return String
	 */
	public String getWillIDDetailsRetrievalAction() {
		return willIDDetailsRetrievalAction;
	}
	/**
	 * @param willRetrievalRemark
	 */
	public void setWillRetrievalRemark(String willRetrievalRemark) {
		this.willRetrievalRemark = willRetrievalRemark;
	}
	/**
	 * @return String
	 */
	public String getWillRetrievalRemark() {
		return willRetrievalRemark;
	}
	/**
	 * @param willRetrievalReason
	 */
	public void setWillRetrievalReason(String willRetrievalReason) {
		this.willRetrievalReason = willRetrievalReason;
	}
	/**
	 * @return String
	 */
	public String getWillRetrievalReason() {
		return willRetrievalReason;
	}
	/**
	 * @param willRetrievalDetailsSubmitAction
	 */
	public void setWillRetrievalDetailsSubmitAction(String
		 willRetrievalDetailsSubmitAction) {
		this.willRetrievalDetailsSubmitAction =
			 willRetrievalDetailsSubmitAction;
	}
	/**
	 * @return String
	 */
	public String getWillRetrievalDetailsSubmitAction() {
		return willRetrievalDetailsSubmitAction;
	}
	/**
	 * @param willViewDetailsAction
	 */
	public void setWillViewDetailsAction(String willViewDetailsAction) {
		this.willViewDetailsAction = willViewDetailsAction;
	}
	/**
	 * @return String
	 */
	public String getWillViewDetailsAction() {
		return willViewDetailsAction;
	}
	
	/**
	 * @return String
	 */
	public String getRadioPayment() {
		return radioPayment;
	}
	
	/**
	 * @param radioPayment
	 */
	public void setRadioPayment(String radioPayment) {
		this.radioPayment = radioPayment;
	}
	
	/**
	 * @return String
	 */
	public String getWillStatus() {
		return willStatus;
	}
	
	/**
	 * @param willStatus
	 */
	public void setWillStatus(String willStatus) {
		this.willStatus = willStatus;
	}
	/**
	 * @return String
	 */
	public String getConfirmRefId() {
		return confirmRefId;
	}
	/**
	 * @param confirmRefId
	 */
	public void setConfirmRefId(String confirmRefId) {
		this.confirmRefId = confirmRefId;
	}
	/**
	 * @return String
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return String
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
	/**
	 * @return String
	 */
	public String getWillViewForm() {
		return willViewForm;
	}
	
	/**
	 * @param willViewForm
	 */
	public void setWillViewForm(String willViewForm) {
		this.willViewForm = willViewForm;
	}
	public String getWillWithdrawalAction() {
		return willWithdrawalAction;
	}
	public void setWillWithdrawalAction(String willWithdrawalAction) {
		this.willWithdrawalAction = willWithdrawalAction;
	}
	public String getWillWithdrawForm() {
		return willWithdrawForm;
	}
	public void setWillWithdrawForm(String willWithdrawForm) {
		this.willWithdrawForm = willWithdrawForm;
	}
	public String getGuardianName() {
		return guardianName;
	}
	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}
	public String getWithdrawlDueDate() {
		return withdrawlDueDate;
	}
	public void setWithdrawlDueDate(String withdrawlDueDate) {
		this.withdrawlDueDate = withdrawlDueDate;
	}
	public String getWillUpdateForm() {
		return willUpdateForm;
	}
	public void setWillUpdateForm(String willUpdateForm) {
		this.willUpdateForm = willUpdateForm;
	}
	public String getRetrievalDueDate() {
		return retrievalDueDate;
	}
	public void setRetrievalDueDate(String retrievalDueDate) {
		this.retrievalDueDate = retrievalDueDate;
	}
	public String getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
	public String getAgentIdProof() {
		return agentIdProof;
	}
	public void setAgentIdProof(String agentIdProof) {
		this.agentIdProof = agentIdProof;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getDeliveredChecked() {
		return deliveredChecked;
	}
	public void setDeliveredChecked(String deliveredChecked) {
		this.deliveredChecked = deliveredChecked;
	}
	public String getDeliveryRemark() {
		return deliveryRemark;
	}
	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}
	public String getDeliveryDueDate() {
		return deliveryDueDate;
	}
	public void setDeliveryDueDate(String deliveryDueDate) {
		this.deliveryDueDate = deliveryDueDate;
	}
	public String getWillRetrievalForm() {
		return willRetrievalForm;
	}
	public void setWillRetrievalForm(String willRetrievalForm) {
		this.willRetrievalForm = willRetrievalForm;
	}
	public String getTestatorRelationship() {
		return testatorRelationship;
	}
	public void setTestatorRelationship(String testatorRelationship) {
		this.testatorRelationship = testatorRelationship;
	}
	public String getRepFirstName() {
		return repFirstName;
	}
	public void setRepFirstName(String repFirstName) {
		this.repFirstName = repFirstName;
	}
	public String getRepMiddleName() {
		return repMiddleName;
	}
	public void setRepMiddleName(String repMiddleName) {
		this.repMiddleName = repMiddleName;
	}
	public String getRepLastName() {
		return repLastName;
	}
	public void setRepLastName(String repLastName) {
		this.repLastName = repLastName;
	}
	public String getRetrievalReason() {
		return retrievalReason;
	}
	public void setRetrievalReason(String retrievalReason) {
		this.retrievalReason = retrievalReason;
	}
	public String getRetrieverIdProofNo() {
		return retrieverIdProofNo;
	}
	public void setRetrieverIdProofNo(String retrieverIdProofNo) {
		this.retrieverIdProofNo = retrieverIdProofNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getAgentBankName() {
		return agentBankName;
	}
	public void setAgentBankName(String agentBankName) {
		this.agentBankName = agentBankName;
	}
	public String getAgentBankAddress() {
		return agentBankAddress;
	}
	public void setAgentBankAddress(String agentBankAddress) {
		this.agentBankAddress = agentBankAddress;
	}
	public String getRetrieverBankName() {
		return retrieverBankName;
	}
	public void setRetrieverBankName(String retrieverBankName) {
		this.retrieverBankName = retrieverBankName;
	}
	public String getRetrieverBankAddress() {
		return retrieverBankAddress;
	}
	public void setRetrieverBankAddress(String retrieverBankAddress) {
		this.retrieverBankAddress = retrieverBankAddress;
	}
	public String getRepCountry() {
		return repCountry;
	}
	public void setRepCountry(String repCountry) {
		this.repCountry = repCountry;
	}
	public String getRepState() {
		return repState;
	}
	public void setRepState(String repState) {
		this.repState = repState;
	}
	public String getRepCity() {
		return repCity;
	}
	public void setRepCity(String repCity) {
		this.repCity = repCity;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getAgentCountryId() {
		return agentCountryId;
	}
	public void setAgentCountryId(String agentCountryId) {
		this.agentCountryId = agentCountryId;
	}
	public String getAgentStateId() {
		return agentStateId;
	}
	public void setAgentStateId(String agentStateId) {
		this.agentStateId = agentStateId;
	}
	public String getAgentDistrictId() {
		return agentDistrictId;
	}
	public void setAgentDistrictId(String agentDistrictId) {
		this.agentDistrictId = agentDistrictId;
	}
	public ArrayList getWillRemarksList() {
		return willRemarksList;
	}
	public void setWillRemarksList(ArrayList willRemarksList) {
		this.willRemarksList = willRemarksList;
	}
	public String getWillCommentsUser() {
		return willCommentsUser;
	}
	public void setWillCommentsUser(String willCommentsUser) {
		this.willCommentsUser = willCommentsUser;
	}
	public String getWillCommentsDate() {
		return willCommentsDate;
	}
	public void setWillCommentsDate(String willCommentsDate) {
		this.willCommentsDate = willCommentsDate;
	}
	public String getFilePhotoName() {
		return filePhotoName;
	}
	public void setFilePhotoName(String filePhotoName) {
		this.filePhotoName = filePhotoName;
	}
	
	public String getFileThumbName() {
		return fileThumbName;
	}
	public void setFileThumbName(String fileThumbName) {
		this.fileThumbName = fileThumbName;
	}
	
	public FormFile getFilePhoto() {
		return filePhoto;
	}
	public void setFilePhoto(FormFile filePhoto) {
		this.filePhoto = filePhoto;
	}
	public FormFile getFileThumb() {
		return fileThumb;
	}
	public void setFileThumb(FormFile fileThumb) {
		this.fileThumb = fileThumb;
	}
	public FormFile getFileSignature() {
		return fileSignature;
	}
	public void setFileSignature(FormFile fileSignature) {
		this.fileSignature = fileSignature;
	}
	public byte[] getDocContents() {
		return docContents;
	}
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}
	public String getFileSignatureName() {
		return fileSignatureName;
	}
	public void setFileSignatureName(String fileSignatureName) {
		this.fileSignatureName = fileSignatureName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileCertificate() {
		return fileCertificate;
	}
	public void setFileCertificate(String fileCertificate) {
		this.fileCertificate = fileCertificate;
	}
	public String getFileCertificateName() {
		return fileCertificateName;
	}
	public void setFileCertificateName(String fileCertificateName) {
		this.fileCertificateName = fileCertificateName;
	}
	/**
	 * @return the photoContents
	 */
	public byte[] getPhotoContents() {
		return photoContents;
	}
	/**
	 * @param photoContents the photoContents to set
	 */
	public void setPhotoContents(byte[] photoContents) {
		this.photoContents = photoContents;
	}
	/**
	 * @return the thumbContents
	 */
	public byte[] getThumbContents() {
		return thumbContents;
	}
	/**
	 * @param thumbContents the thumbContents to set
	 */
	public void setThumbContents(byte[] thumbContents) {
		this.thumbContents = thumbContents;
	}
	/**
	 * @return the signatureContents
	 */
	public byte[] getSignatureContents() {
		return signatureContents;
	}
	/**
	 * @param signatureContents the signatureContents to set
	 */
	public void setSignatureContents(byte[] signatureContents) {
		this.signatureContents = signatureContents;
	}
	/**
	 * @return the certificateContents
	 */
	public byte[] getCertificateContents() {
		return certificateContents;
	}
	/**
	 * @param certificateContents the certificateContents to set
	 */
	public void setCertificateContents(byte[] certificateContents) {
		this.certificateContents = certificateContents;
	}
	public String getDepositRemarks() {
		return depositRemarks;
	}
	public void setDepositRemarks(String depositRemarks) {
		this.depositRemarks = depositRemarks;
	}
	public boolean isCheckPhoto() {
		return checkPhoto;
	}
	public void setCheckPhoto(boolean checkPhoto) {
		this.checkPhoto = checkPhoto;
	}
	public boolean isCheckSignature() {
		return checkSignature;
	}
	public void setCheckSignature(boolean checkSignature) {
		this.checkSignature = checkSignature;
	}
	public boolean isCheckThumb() {
		return checkThumb;
	}
	public void setCheckThumb(boolean checkThumb) {
		this.checkThumb = checkThumb;
	}
	public String getDependantwillId() {
		return dependantwillId;
	}
	public void setDependantwillId(String dependantwillId) {
		this.dependantwillId = dependantwillId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
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
	public String getAgentsName() {
		return agentsName;
	}
	public void setAgentsName(String agentsName) {
		this.agentsName = agentsName;
	}
	public String getCompPhotoPath() {
		return compPhotoPath;
	}
	public void setCompPhotoPath(String compPhotoPath) {
		this.compPhotoPath = compPhotoPath;
	}
	public String getCompSignPath() {
		return compSignPath;
	}
	public void setCompSignPath(String compSignPath) {
		this.compSignPath = compSignPath;
	}
	public String getCompThumbPath() {
		return compThumbPath;
	}
	public void setCompThumbPath(String compThumbPath) {
		this.compThumbPath = compThumbPath;
	}
	public String getCompCertiPath() {
		return compCertiPath;
	}
	public void setCompCertiPath(String compCertiPath) {
		this.compCertiPath = compCertiPath;
	}
	public String getDphotoPath() {
		return dphotoPath;
	}
	public void setDphotoPath(String dphotoPath) {
		this.dphotoPath = dphotoPath;
	}
	public String getDsignPath() {
		return dsignPath;
	}
	public void setDsignPath(String dsignPath) {
		this.dsignPath = dsignPath;
	}
	public String getDthumbPath() {
		return dthumbPath;
	}
	public void setDthumbPath(String dthumbPath) {
		this.dthumbPath = dthumbPath;
	}
	public String getDproofPath() {
		return dproofPath;
	}
	public void setDproofPath(String dproofPath) {
		this.dproofPath = dproofPath;
	}
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getModuleNo() {
		return moduleNo;
	}
	public void setModuleNo(String moduleNo) {
		this.moduleNo = moduleNo;
	}
	public String getShowACountry() {
		return showACountry;
	}
	public void setShowACountry(String showACountry) {
		this.showACountry = showACountry;
	}
	public String getShowTCountry() {
		return showTCountry;
	}
	public void setShowTCountry(String showTCountry) {
		this.showTCountry = showTCountry;
	}


	
}
