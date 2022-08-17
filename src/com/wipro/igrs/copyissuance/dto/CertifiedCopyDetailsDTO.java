package com.wipro.igrs.copyissuance.dto;

/**
 * ===========================================================================
 * File           :   CertifiedCopyDetailsDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Dev Pradhan
 * Created Date   :   Jan 07, 2008

 * ===========================================================================
 */
 


import java.util.ArrayList;
import java.io.Serializable;

import org.apache.struts.upload.FormFile;



public class CertifiedCopyDetailsDTO implements Serializable{

	private String appellate;
	private String organisationName;
	private String authorizedPerson;
	private String documentType;
	private String purposeDownload;
	private String dispatchType;
	private String language;
	
	//added by satbir kumar--
	
	private transient FormFile attachedDoc;
    private transient byte[] docContents;
    private String docFileSize;
    private String uploaded_doc_path;
    private String documentName;
    private byte[] photo;
    private String documentSavePath;
    //--end of addition----
    
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	private String id;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDispatchType() {
		return dispatchType;
	}

	public void setDispatchType(String dispatchType) {
		this.dispatchType = dispatchType;
	}

	public String getPurposeDownload() {
		return purposeDownload;
	}

	public void setPurposeDownload(String purposeDownload) {
		this.purposeDownload = purposeDownload;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getAppellate() {
		return appellate;
	}

	public void setAppellate(String appellate) {
		this.appellate = appellate;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getAuthorizedPerson() {
		return authorizedPerson;
	}

	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
	}
	private String hdntransactionID;
	public String getHdntransactionID() {
		return hdntransactionID;
	}
	private ArrayList selectedItems;
	public ArrayList getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}

	public void setHdntransactionID(String hdntransactionID) {
		this.hdntransactionID = hdntransactionID;
	}
		private String propertyTypeLabel;
		public String getPropertyTypeLabel() {
			return propertyTypeLabel;
		}

		public void setPropertyTypeLabel(String propertyTypeLabel) {
			this.propertyTypeLabel = propertyTypeLabel;
		}

		public String getPropertyTxnId() {
			return propertyTxnId;
		}

		public void setPropertyTxnId(String propertyTxnId) {
			this.propertyTxnId = propertyTxnId;
		}
		private String propertyTxnId;
		private String certifiedId;
        private String firstName;
        private String middleName;
        private String lastName;
        private String gender;
        private String age;
        private String fatherName;
        private String motherName;
        private String spouseName;
        private String address;
        private String city;
        private String country;
        private ArrayList countrys;
        private String state;
        private ArrayList states;
        private ArrayList districts;
        private String pin;
        private String phone;
        private String mphone;
        private String email;
        private String idProof;
        private ArrayList idProofs;
        private String idProofNo;
        private String regNo;
        private String regNo1;
	private String regNo2;
	private String regNo3;
	private String regNo4;
	private String documentId;
        private String typeReq;
	private String purposeReq;
	
	private String name;
	private String value;
	
        private String fee;
        private String postalFee;
	private String totalFee;
        private String paymentType;
	private String createdBy;
	private String createdDt;
	private String modifiedBy;
	private String modifiedDt;
	private String fromRequestDate;
	private String toRequestDate;
	private String appStatus;
	private String issuanceUpdate;				
	private String issuanceRemark;
	private String issuanceViewId;
	private String issuanceStatus;
	private int serialNo;
	private String paymentTxnId;
	private String statusUpdateFlag;
	private String challanNumber;
	private String challanDate;
	private String challanAmount;
	private String bankName;
	private String bankAddress;
	private String modeOfPayment; 
	private String popupAction;
	private String validPayment;
	private String balanceAmount;
	private String lastTransactionDate;
	private String PaidAmount;
	 private ArrayList pendingApps = new ArrayList();
        public ArrayList getPendingApps() {
		return pendingApps;
	}

	public void setPendingApps(ArrayList pendingApps) {
		this.pendingApps = pendingApps;
	}

		public String getPaidAmount() {
		return PaidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		PaidAmount = paidAmount;
	}

		public String getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(String lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

		public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

		private String copyIssuanceForm;
        private String copyIssuanceInsertAction;
	private String copyIssuanceSuccessAction;
	private String copyIssuanceDisplayAction;
	private String paymentSuccessAction;
	private String paymentDisplayAction;
	private String backAction;
	
	private String issuanceViewDetailsAction;
 	private String issuanceOnlineAction;
	private String issuanceUpdateAction;
	private String issuanceOnlineSubmitAction;
	private String issuanceRequestUpdateAction;
	private String issuanceRemarkUpdateAction;	
	private String issuanceStatusAction;
	private String issuanceStatusChangeAction;
	private String issuanceModifyAction;
	private String copyStatusAction;
	private String copyChallanAction;
	private String onlineBackAction;
	private String requestBackAction;
	private String statusBackAction;
	private String issuanceAction;
 	private String volume;
    private String bookNo;
    private String num;
	private String countryId;
	private String stateId;
	private String cityId;
    private String regDate;
    private String numberDate;
    private String transPartyFirstName;
    private String transPartyMidName;
    private String transPartyLastName;
    private String transPartySpouseName;
    private String transPartyFGHName;
    private String transPartyMotherName;
    
    private String countryName;
    private String contryId;
    private ArrayList countryList;
    
    private String stateName;
    private String stId;
    private ArrayList stateList;
    
    private String districtName;
    private String districtId;
    private ArrayList districtList;
    private String submitstatus;
    
	private FormFile filePhoto = null;
    private FormFile fileThumb = null;
    private FormFile fileSignature = null;
    
    private String receiptTaken;
    private String districtName2;
    private String gender1;
    private String gender2;
    private String parentFunName;
    private String parentModName;
    private String parentFunId;
    private String deedType;
    private String onlineDownloadAction;
    private String deedId;
    public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public String getOnlineDownloadAction() {
		return onlineDownloadAction;
	}

	public void setOnlineDownloadAction(String onlineDownloadAction) {
		this.onlineDownloadAction = onlineDownloadAction;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}
	private ArrayList partyDetailList;
	
	public ArrayList getPartyDetailList() {
		return partyDetailList;
	}

	public void setPartyDetailList(ArrayList partyDetailList) {
		this.partyDetailList = partyDetailList;
	}
	private ArrayList srDashBoardList;
	public ArrayList getSrDashBoardList() {
		return srDashBoardList;
	}

	public void setSrDashBoardList(ArrayList srDashBoardList) {
		this.srDashBoardList = srDashBoardList;
	}
	private String paymentAmount;
    private String paymentMode;
    private String documetId1;
    private String showState;
    private String showDist;
    private String hidGender;
    private String appSopuse;
    private String postalTrakingNum;
    private String disDate;
    private String updtdRemarks;
    private String txtReg;
    private String searchAction;
    private String pinNo;
    //Used to hold hidden pin number
    private String validPin;
	private String docReg;
	private String searchNo;
	private String oldRegNo;
	private String oldRegDate;
	private String noEncumTxn;
	
	public String getNoEncumTxn() {
		return noEncumTxn;
	}

	public void setNoEncumTxn(String noEncumTxn) {
		this.noEncumTxn = noEncumTxn;
	}

	public String getOldRegNo() {
		return oldRegNo;
	}

	public void setOldRegNo(String oldRegNo) {
		this.oldRegNo = oldRegNo;
	}

	public String getOldRegDate() {
		return oldRegDate;
	}

	public void setOldRegDate(String oldRegDate) {
		this.oldRegDate = oldRegDate;
	}

	public String getDocReg() {
		return docReg;
	}

	public void setDocReg(String docReg) {
		this.docReg = docReg;
	}

	public String getSearchNo() {
		return searchNo;
	}

	public void setSearchNo(String searchNo) {
		this.searchNo = searchNo;
	}

	public String getValidPin() {
		return validPin;
	}

	public void setValidPin(String validPin) {
		this.validPin = validPin;
	}

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getSearchAction() {
		return searchAction;
	}

	public void setSearchAction(String searchAction) {
		this.searchAction = searchAction;
	}

	public String getTxtReg() {
		return txtReg;
	}

	public void setTxtReg(String txtReg) {
		this.txtReg = txtReg;
	}

	public String getUpdtdRemarks() {
		return updtdRemarks;
	}

	public void setUpdtdRemarks(String updtdRemarks) {
		this.updtdRemarks = updtdRemarks;
	}

	public String getPostalTrakingNum() {
		return postalTrakingNum;
	}

	public void setPostalTrakingNum(String postalTrakingNum) {
		this.postalTrakingNum = postalTrakingNum;
	}

	public String getDisDate() {
		return disDate;
	}

	public void setDisDate(String disDate) {
		this.disDate = disDate;
	}

	public String getAppSopuse() {
		return appSopuse;
	}

	public void setAppSopuse(String appSopuse) {
		this.appSopuse = appSopuse;
	}

	public String getHidGender() {
		return hidGender;
	}

	public void setHidGender(String hidGender) {
		this.hidGender = hidGender;
	}

	public String getShowDist() {
		return showDist;
	}

	public void setShowDist(String showDist) {
		this.showDist = showDist;
	}

	public String getShowState() {
		return showState;
	}

	public void setShowState(String showState) {
		this.showState = showState;
	}

	public String getDocumetId1() {
		return documetId1;
	}

	public void setDocumetId1(String documetId1) {
		this.documetId1 = documetId1;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	public String getGender1() {
		return gender1;
	}

	public void setGender1(String gender1) {
		this.gender1 = gender1;
	}

	public String getGender2() {
		return gender2;
	}

	public void setGender2(String gender2) {
		this.gender2 = gender2;
	}

	public String getDistrictName2() {
		return districtName2;
	}

	public void setDistrictName2(String districtName2) {
		this.districtName2 = districtName2;
	}

	public String getReceiptTaken() {
		return receiptTaken;
	}

	public void setReceiptTaken(String receiptTaken) {
		this.receiptTaken = receiptTaken;
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

	public String getSubmitstatus() {
		return submitstatus;
	}

	public void setSubmitstatus(String submitstatus) {
		this.submitstatus = submitstatus;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getContryId() {
		return contryId;
	}

	public void setContryId(String contryId) {
		this.contryId = contryId;
	}

	public ArrayList getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public String getTransPartyFirstName() {
		return transPartyFirstName;
	}

	public void setTransPartyFirstName(String transPartyFirstName) {
		this.transPartyFirstName = transPartyFirstName;
	}

	public String getTransPartyMidName() {
		return transPartyMidName;
	}

	public void setTransPartyMidName(String transPartyMidName) {
		this.transPartyMidName = transPartyMidName;
	}

	public String getTransPartyLastName() {
		return transPartyLastName;
	}

	public void setTransPartyLastName(String transPartyLastName) {
		this.transPartyLastName = transPartyLastName;
	}

	public String getTransPartySpouseName() {
		return transPartySpouseName;
	}

	public void setTransPartySpouseName(String transPartySpouseName) {
		this.transPartySpouseName = transPartySpouseName;
	}

	public String getTransPartyFGHName() {
		return transPartyFGHName;
	}

	public void setTransPartyFGHName(String transPartyFGHName) {
		this.transPartyFGHName = transPartyFGHName;
	}

	public String getTransPartyMotherName() {
		return transPartyMotherName;
	}

	public void setTransPartyMotherName(String transPartyMotherName) {
		this.transPartyMotherName = transPartyMotherName;
	}

	public String getNumberDate() {
		return numberDate;
	}

	public void setNumberDate(String numberDate) {
		this.numberDate = numberDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public void setCertifiedId(String certifiedId) {
		this.certifiedId = certifiedId;
	}

	public String getCertifiedId() {
		return certifiedId;
	}
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    
        public String getFirstName() {
            return firstName;
        }
    
        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }
    
        public String getMiddleName() {
            return middleName;
        }
    
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    
        public String getLastName() {
            return lastName;
        }
    
        public void setGender(String gender) {
            this.gender = gender;
        }
    
        public String getGender() {
            return gender;
        }
    
        public void setAge(String age) {
            this.age = age;
        }
    
        public String getAge() {
            return age;
        }
    
        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }
    
        public String getFatherName() {
            return fatherName;
        }
    
        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }
    
        public String getMotherName() {
            return motherName;
        }
    
        public void setSpouseName(String spouseName) {
            this.spouseName = spouseName;
        }
    
        public String getSpouseName() {
            return spouseName;
        }
    
        public void setAddress(String address) {
            this.address = address;
        }
    
        public String getAddress() {
            return address;
        }
    
        public void setCity(String city) {
            this.city = city;
        }
    
        public String getCity() {
            return city;
        }
    
        public void setCountry(String country) {
            this.country = country;
        }
    
        public String getCountry() {
            return country;
        }
    
        public void setCountrys(ArrayList countrys) {
            this.countrys = countrys;
        }
    
        public ArrayList getCountrys() {
            return countrys;
        }
    
        public void setState(String state) {
            this.state = state;
        }
    
        public String getState() {
            return state;
        }
        
        public void setDistricts(ArrayList districts) {
            this.districts = districts;
        }
    
        public ArrayList getDistricts() {
            return districts;
        }
    
        public void setStates(ArrayList states) {
            this.states = states;
        }
    
        public ArrayList getStates() {
            return states;
        }
    
        public void setPin(String pin) {
            this.pin = pin;
        }
    
        public String getPin() {
            return pin;
        }
    
        public void setPhone(String phone) {
            this.phone = phone;
        }
    
        public String getPhone() {
            return phone;
        }
    
        public void setMphone(String mphone) {
            this.mphone = mphone;
        }
    
        public String getMphone() {
            return mphone;
        }
    
        public void setEmail(String email) {
            this.email = email;
        }
    
        public String getEmail() {
            return email;
        }
    
        public void setIdProof(String idProof) {
            this.idProof = idProof;
        }
    
        public String getIdProof() {
            return idProof;
        }
    
        public void setIdProofs(ArrayList idProofs) {
            this.idProofs = idProofs;
        }
    
        public ArrayList getIdProofs() {
            return idProofs;
        }
    
        public void setIdProofNo(String idProofNo) {
            this.idProofNo = idProofNo;
        }
    
        public String getIdProofNo() {
            return idProofNo;
        }
        
    	public void setRegNo(String regNo) {
    		this.regNo = regNo;
    	}
    
    	public String getRegNo() {
    		return regNo;
    	}
    	
    	public void setRegNo1(String regNo1) {
    		this.regNo1 = regNo1;
    	}
    
    	public String getRegNo1() {
    		return regNo1;
    	}
    		
    	public void setRegNo2(String regNo2) {
    		this.regNo2 = regNo2;
    	}
    
    	public String getRegNo2() {
    		return regNo2;
    	}
    		
    	public void setRegNo3(String regNo3) {
    		this.regNo3 = regNo3;
    	}
    
    	public String getRegNo3() {
    		return regNo3;
    	}
    			
    	public void setRegNo4(String regNo4) {
    		this.regNo4 = regNo4;
    	}
    
    	public String getRegNo4() {
    		return regNo4;
    	}
    		
    	public void setDocumentId(String documentId) {
    		this.documentId = documentId;
    	}
    
    	public String getDocumentId() {
    		return documentId;
    	}
    						
    	public void setTypeReq(String typeReq) {
    		this.typeReq = typeReq;
    	}
    
        public String getTypeReq() {
    		return typeReq;
    	}
    
    	public void setPurposeReq(String purposeReq) {
    		this.purposeReq = purposeReq;
    	}
    
    	public String getPurposeReq() {
    		return purposeReq;
    	}
    
        public void setFee(String fee) {
            this.fee = fee;
        }
    
        public String getFee() {
            return fee;
        }
    
        public void setPostalFee(String postalFee) {
            this.postalFee = postalFee;
        }
    
        public String getPostalFee() {
            return postalFee;
        }
    
    	public void setTotalFee(String totalFee) {
         	this.totalFee = totalFee;
        }
    
        public String getTotalFee() {
    		return totalFee;
        }
    
        public void setPaymentType(String paymentType) {
            this.paymentType = paymentType;
        }
    
        public String getPaymentType() {
            return paymentType;
        }
        
        public void setCopyIssuanceForm(String copyIssuanceForm) {
            this.copyIssuanceForm = copyIssuanceForm;
        }
    
        public String getCopyIssuanceForm() {
            return copyIssuanceForm;
        }
    
        public void setCopyIssuanceInsertAction(String copyIssuanceInsertAction) {
            this.copyIssuanceInsertAction = copyIssuanceInsertAction;
        }
    
        public String getCopyIssuanceInsertAction() {
            return copyIssuanceInsertAction;
        }
        
      
    	public void setCopyIssuanceSuccessAction(String copyIssuanceSuccessAction) {
    		this.copyIssuanceSuccessAction = copyIssuanceSuccessAction;
    	}
    
    	public String getCopyIssuanceSuccessAction() {
    		return copyIssuanceSuccessAction;
    	}
    
    	public void setCopyIssuanceDisplayAction(String copyIssuanceDisplayAction) {
    		this.copyIssuanceDisplayAction = copyIssuanceDisplayAction;
    	}
    
    	public String getCopyIssuanceDisplayAction() {
    		return copyIssuanceDisplayAction;
    	}
    		
       	public void setPaymentSuccessAction(String paymentSuccessAction) {
    		this.paymentSuccessAction = paymentSuccessAction;
    	}
    
    	public String getPaymentSuccessAction() {
    		return paymentSuccessAction;
    	}
    	
    	public void setPaymentDisplayAction(String paymentDisplayAction) {
    		this.paymentDisplayAction = paymentDisplayAction;
    	}
    
    	public String getPaymentDisplayAction() {
    		return paymentDisplayAction;
    	}
    	
    	public void setBackAction(String backAction) {
    		this.backAction = backAction;
    	}
    
    	public String getBackAction() {
    		return backAction;
    	}
    				
    	public String getCreatedBy() {
    		return createdBy;
    	}
    
    	public String getCreatedDt() {
    		return createdDt;
    	}
    
    	public String getModifiedBy() {
    		return modifiedBy;
    	}
    
    	public String getModifiedDt() {
    		return modifiedDt;
    	}
    	
    	public String getFromRequestDate() {
    		return fromRequestDate;
    	}
    	
    	public String getToRequestDate() {
    		return toRequestDate;
    	}
    	
    	public String getAppStatus() {
    		return appStatus;
    	}
    		
    	public void setCreatedBy(String createdBy) {
    		this.createdBy = createdBy;
    	}
    
    	public void setCreatedDt(String createdDt) {
    		this.createdDt = createdDt;
    	}
    
    	public void setModifiedBy(String modifiedBy) {
    		this.modifiedBy = modifiedBy;
    	}
    
    	public void setModifiedDt(String modifiedDt) {
    		this.modifiedDt = modifiedDt;
    	}
    	
    	public void setFromRequestDate(String fromRequestDate) {
    		this.fromRequestDate = fromRequestDate;
    	}
    	
    	public void setToRequestDate(String toRequestDate) {
    		this.toRequestDate = toRequestDate;
    	}
    	
    	public void setAppStatus(String appStatus) {
    		this.appStatus = appStatus;
    	}
    			
    	
    	public void setIssuanceViewDetailsAction(String issuanceViewDetailsAction) {
    		this.issuanceViewDetailsAction = issuanceViewDetailsAction;
    	}
    
    	public String getIssuanceViewDetailsAction() {
    		return issuanceViewDetailsAction;
    	}
    	
    	
    	public void setIssuanceOnlineAction(String issuanceOnlineAction) {
    		this.issuanceOnlineAction = issuanceOnlineAction;
    	}
    
    	public String getIssuanceOnlineAction() {
    		return issuanceOnlineAction;
    	}
        
    	public void setIssuanceUpdateAction(String issuanceUpdateAction) {
    		this.issuanceUpdateAction = issuanceUpdateAction;
    	}
    
    	public String getIssuanceUpdateAction() {
    		return issuanceUpdateAction;
    	}
    	
    	public void setIssuanceOnlineSubmitAction(String issuanceOnlineSubmitAction) {
    		this.issuanceOnlineSubmitAction = issuanceOnlineSubmitAction;
    	}
    
    	public String getIssuanceOnlineSubmitAction() {
    		return issuanceOnlineSubmitAction;
    	}
    			
    	public void setIssuanceRequestUpdateAction(String issuanceRequestUpdateAction) {
    		this.issuanceRequestUpdateAction = issuanceRequestUpdateAction;
    	}
    
    	public String getIssuanceRequestUpdateAction() {
    		return issuanceRequestUpdateAction;
    	}
    		
    	public void setIssuanceRemarkUpdateAction(String issuanceRemarkUpdateAction) {
    		this.issuanceRemarkUpdateAction = issuanceRemarkUpdateAction;
    	}
    
    	public String getIssuanceRemarkUpdateAction() {
    		return issuanceRemarkUpdateAction;
    	}
    	
    	public void setIssuanceStatusAction(String issuanceStatusAction) {
    		this.issuanceStatusAction = issuanceStatusAction;
    	}
    
    	public String getIssuanceStatusAction() {
    		return issuanceStatusAction;
    	}
    	
    	public void setIssuanceStatusChangeAction(String issuanceStatusChangeAction) {
    		this.issuanceStatusChangeAction = issuanceStatusChangeAction;
    	}
    
    	public String getIssuanceStatusChangeAction() {
    		return issuanceStatusChangeAction;
    	}
    		
    	public void setIssuanceModifyAction(String issuanceModifyAction) {
    		this.issuanceModifyAction = issuanceModifyAction;
    	}
    
    	public String getIssuanceModifyAction() {
    		return issuanceModifyAction;
    	}
    			
    	public void setCopyStatusAction(String copyStatusAction) {
    		this.copyStatusAction = copyStatusAction;
    	}
    
    	public String getCopyStatusAction() {
    		return copyStatusAction;
    	}
    			
    	public void setCopyChallanAction(String copyChallanAction) {
    		this.copyChallanAction = copyChallanAction;
    	}
    
    	public String getCopyChallanAction() {
    		return copyChallanAction;
    	}
    		
    	public void setOnlineBackAction(String onlineBackAction) {
    		this.onlineBackAction = onlineBackAction;
    	}
    
    	public String getOnlineBackAction() {
    		return onlineBackAction;
    	}
    	
    	public void setRequestBackAction(String requestBackAction) {
    		this.requestBackAction = requestBackAction;
    	}
    
    	public String getRequestBackAction() {
    		return requestBackAction;
    	}
    			
    	public void setStatusBackAction(String statusBackAction) {
    		this.statusBackAction = statusBackAction;
    	}
    
    	public String getStatusBackAction() {
    		return statusBackAction;
    	}
    						
    	public void setIssuanceAction(String issuanceAction) {
    		this.issuanceAction = issuanceAction;
    	}
    
    	public String getIssuanceAction() {
    		return issuanceAction;
    	}
    		
    	public void setIssuanceUpdate(String issuanceUpdate) {
    		this.issuanceUpdate = issuanceUpdate;
    	}
    
    	public String getIssuanceUpdate() {
    		return issuanceUpdate;
    	}
    	
    	public void setIssuanceRemark(String issuanceRemark) {
    		this.issuanceRemark = issuanceRemark;
    	}
    
    	public String getIssuanceRemark() {
    		return issuanceRemark;
    	}
    		
    	public void setIssuanceViewId(String issuanceViewId) {
    		this.issuanceViewId = issuanceViewId;
    	}
    
    	public String getIssuanceViewId() {
    		return issuanceViewId;
    	}	
    	
    	public void setIssuanceStatus(String issuanceStatus) {
    		this.issuanceStatus = issuanceStatus;
    	}
    
    	public String getIssuanceStatus() {
    		return issuanceStatus;
    	}
    	
    	public void setSerialNo(int serialNo) {
    		this.serialNo = serialNo;
    	}
    
    	public int getSerialNo() {
    		return serialNo;
    	}	
    	
    	public void setPaymentTxnId(String paymentTxnId) {
    		this.paymentTxnId = paymentTxnId;
    	}
    
    	public String getPaymentTxnId() {
    		return paymentTxnId;
    	}	
    		
    	public void setChallanNumber(String challanNumber) {
    		this.challanNumber = challanNumber;
    	}
    
    	public String getChallanNumber() {
    		return challanNumber;
    	}	
    	
    	public void setChallanDate(String challanDate) {
    		this.challanDate = challanDate;
    	}
    
    	public String getChallanDate() {
    		return challanDate;
    	}	
    		
    	public void setChallanAmount(String challanAmount) {
    		this.challanAmount = challanAmount;
    	}
    
    	public String getChallanAmount() {
    		return challanAmount;
    	}
    		
    	public void setBankName(String bankName) {
    		this.bankName = bankName;
    	}
    
    	public String getBankName() {
    		return bankName;
    	}	
    
    	public void setBankAddress(String bankAddress) {
    		this.bankAddress = bankAddress;
    	}
    
    	public String getBankAddress() {
    		return bankAddress;
    	}	
    	
    	public void setModeOfPayment(String modeOfPayment) {
    		this.modeOfPayment = modeOfPayment;
    	}
    
    	public String getModeOfPayment() {
    		return modeOfPayment;
    	}	
    		
    	public void setPopupAction(String popupAction) {
    		this.popupAction = popupAction;
    	}
    
    	public String getPopupAction() {
    		return popupAction;
    	}	
    	
    	public void setValidPayment(String validPayment) {
    		this.validPayment = validPayment;
    	}
    
    	public String getValidPayment() {
    		return validPayment;
    	}	
    
            public void setVolume(String volume) {
    		this.volume = volume;
    	}
    
    	public String getVolume() {
    		return volume;
    	}
    
            public void setBookNo(String bookNo) {
    		this.bookNo = bookNo;
    	}
    
    	public String getBookNo() {
    		return bookNo;
    	}
    
            public void setNum(String num) {
    		this.num = num;
    	}
    
    	public String getNum() {
    		return num;
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
    
    	public String getCityId() {
    		return cityId;
    	}
    
    	public void setCityId(String cityId) {
    		this.cityId = cityId;
    	}

		public void setStatusUpdateFlag(String statusUpdateFlag) {
			this.statusUpdateFlag = statusUpdateFlag;
		}

		public String getStatusUpdateFlag() {
			return statusUpdateFlag;
		}
		private String port;
		private String dms;


		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getDms() {
			return dms;
		}

		public void setDms(String dms) {
			this.dms = dms;
		}

	
		public void setDocContents(byte[] docContents) {
			this.docContents = docContents;
		}

		public byte[] getDocContents() {
			return docContents;
		}

		public void setAttachedDoc(FormFile attachedDoc) {
			this.attachedDoc = attachedDoc;
		}

		public FormFile getAttachedDoc() {
			return attachedDoc;
		}

		public void setDocFileSize(String docFileSize) {
			this.docFileSize = docFileSize;
		}

		public String getDocFileSize() {
			return docFileSize;
		}

		public void setUploaded_doc_path(String uploaded_doc_path) {
			this.uploaded_doc_path = uploaded_doc_path;
		}

		public String getUploaded_doc_path() {
			return uploaded_doc_path;
		}

		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}

		public String getDocumentName() {
			return documentName;
		}

		public void setPhoto(byte[] photo) {
			this.photo = photo;
		}

		public byte[] getPhoto() {
			return photo;
		}

		public void setDocumentSavePath(String documentSavePath) {
			this.documentSavePath = documentSavePath;
		}

		public String getDocumentSavePath() {
			return documentSavePath;
		}
}
