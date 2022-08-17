package com.wipro.igrs.propertylock.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class PropertyLockDTO extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	private String funcId;
	private String forwrdAftrPrprtyDisp;
	ArrayList paymentDashbrdDisplayList = new ArrayList();
	
	//--------------------------for lock ----------------------
	private String appType;
	private String appTypeName;
	private ArrayList appellate;
	
	private String orgName;
	private String authName;
	private String orgCountry;
	private String orgCountryName;
	private String orgState;
	private String orgStateName;
	private String orgDistrict;
	private String orgDistrictName;
	private String orgAddress;
	private String orgMobno;
	private String orgPhno;
	private String idProofName;
	private String propertyLockid;
	private String alrdyLockId;
	private HashMap mapPropertyTransPartyDisp = new HashMap();
	private String genClick;
	//---------------------------------------for release-----------------------
	private String propertyReleaseId;
	ArrayList lockDetailsRelSearch = new ArrayList();
	private String lockComb;
	private String relationId;
	private ArrayList relationList;
	private String scriptRelType;
	//---------------------------for View--------------------------------------
	private ArrayList viewResultList=new ArrayList();
	private String viewComb;
	//---------------------------for registration number search details--------
	private String regCompNumber;
	private String regCompDate;
	private String propId;
	private String propStatus;
	private String propDeed;
	private String propAry;
	private String chckBx;
	private String propComb;
	private String isDisable;
	private String radioSelect;
	ArrayList regDetls = new ArrayList();
	ArrayList propList = new ArrayList();
	//---------------------------for payment-------------------
	private String payableFee;
	private String alrdyPaidFee;
	private String totalFee; 
	private String primKeyPymt;
	private String flg;
	//------for Documents--------
	private String photoPath;
	private String thumbPath;
	private String signPath;
	//---for dashboard--------
	ArrayList pendingDetails = new ArrayList();
	private String pendingCumDetl;
	ArrayList pendingDetailsR = new ArrayList();
	private String pendingCumDetlR;
	//------------------------for messages-----------------
	private int isPayment=0;
	private int isRegDetlEmpty = 0;
	private int isAlrdyLocked =0;
	private int notInsertedLock=0;
	private int notInsertedRel=0;
	private int isLockSuccess=0;
	private int isRelSuccess=0;
	private int isLockSave=0;
	private int isReleaseSave=0;
	private int isPayCompl=0;
	private int isPartial =0;
	private int notInsertedP=0;
	private int notUpdatedAfterPay=0;
	private int noRecFound=0;
	
	private int isViewEmpty=0;
	private int isFromView=0;
	
	
	
	
	
	
	
	private String propertyTxnId;
    private String transactionId;
    private String propertyLockFlag;
    private String firstName;
    private String midName;
    private String lastName;
    private String gender;
    private String age;
    private String guardianName;
    private String motherName;
    private String address;    
    private String country;
    private ArrayList countrys;
    private String state;
    private ArrayList states;
    private String district;
    private ArrayList districts;
    private String pin;
    private String phone;
    private String mphone;
    private String email;
    private String idProof;
    private ArrayList idProofs;
    private String idProofNo;
    private String registrationId;
    private String registrationDate;
    private String photoFlg;
    private String thumbFlg;
    private String signFlg;
    private String purpose;
    private String lockStatus;
    private String poaRegNo;
    private String poaRegDate;   
    private String releaseDate;
    private String dateLocking;
    private String reasonForRelease;
    private String remarksForRelease;
    private String functionTxnId;
    private String sroId;   
    private String sroName;
    private String update;
    private String functionId;
    private String bankName;
    private String challanBankName;
    private String bankAddress;
    private String fee;    
    private String otherFee; 
   // private String totalFee;
    private String modeOfPayment;
    private String challanNo;
    private String challanDate;
    private String amount;
    private String appStatus;    
    private String backReleaseAction;
    private String txtReg;
    private String tempTxnId;
    private String marketValue;
    private String considerationAmount;
    private String exemption;
    private String adjudication;
    private String caveats;
    private String remarks;
    private String stampDuty;
    private String registrationFee;
    private String relationship;
    private String relativeFirstName;
    private String relativeMiddleName;
    private String relativeLastName;
    private String relativeGender;
    private String relativeAge;
    private String relativeFatherName;
    private String relativeMotherName;
    private String relativeAddress;
    private String relativeCountry;
    private String relativeState;
    private String relativeCity;
    private String relativePostalCode;
    private String relativePhone;
    private String relativeMobile;
    private String relativeEmail;
    private String relativeIdProof;
    private String relativeIdNo;
    private String relativeBankName;
    private String relativeBankAddress; 

    private String propertyLockDisplayAction;
    private String lockSuccessAction;
    private String paymentSuccessAction;
    
    private String lockModifyAction;
    private String backLockAction;
    private String lockChallanAction;
    private String lockSearchAction;
    private String lockRegSearchAction;

    private String propertyLockForm;
    private String propertyLockInsertAction;

    private String lockViewlID;
    private String lockViewDetailsAction;

    private String createdDt;
    private String fromRequestDate;
    private String toRequestDate;
    private String lockReleaseDetailsAction;
    private String lockReleaseDetail;
    private String unLockDetail;
    private String unLockDetailsView;
    private String unLockSuccessAction;
    private String releaseModifyAction;
    private String unLockPopupAction;
    private String countryName;
    private String countryId;
    private ArrayList countryList;
    
    private String stateName;
    private String stateId;
    private ArrayList stateList;
    
    private String districtName;
    private String districtId;
    private ArrayList districtList;
    
	private FormFile filePhoto = null;
    private FormFile fileThumb = null;
    private FormFile fileSignature = null;
    private String uploadFilePath;
    private String actionName;
    private String documentName;
    private FormFile attachedDoc;
    private byte[] docContents;
    private String photoSize;
    
    private String thunmbName;
    private FormFile attachedthumb;
    private byte[] thumbContents;
    private String thumbSize;
    
    private String signatureName;
    private FormFile attachedSignature;
    private byte[] signatureContents;
    private String signatureSize;
    
    private String parentFunName;
    private String parentModName;
    private String parentFunId;
     private String showDist;
     private String showgender;
     private String photoCheck;
     private String thumbCheck;
     private String signCheck;
     private String personType;

private  String releaserName;
private  String relationType;
private  String relcountryName;
private  String relStateName;
private  String relDistName;
private  String relDistrictId;
private  String relStateId;
private  String relCountryId;
private ArrayList relStateList;
private ArrayList relCountryList;
private ArrayList relDistrictList;
private String relation;

private String relAddress;
private String relEmail;
private String relMobNo;
private String relphone;
private String relMotherName;
private String relFatherName ;
     
private FormFile relFilePhoto;
private FormFile relFileThumb;
private FormFile relFileSig;

private String relPhotoName;
private FormFile relAttachDoc;
private byte[] relPhotoContents;
private String relPhotoSize;
private String relPhotoPath;

private String relThumbName;
private FormFile relAttachthumb;
private byte[] relThumbContents;
private String relThumbSize;
private String relThumbPath;
 
private String  relSignName;
private FormFile relAttaSig;
private byte[] relSignContents;
private String relSigSize;
private String relSignPath;

private String relPhotoCheck;
private String relThumbCheck;
private String relSignCheck;


private String   relDeathCerName;
private FormFile relDeathCerAttach;
private byte[]   relDeathCerContents;
private String   relDeathCerSize;
private String   relDeathCerCheck;
private String relDeathCrtPath;

private String forwardPath;
private String showState;
private String rcountryName;
private String rcountryId;
private ArrayList rcountryList;

private String rstateId;
private String rstate;
private String rstateName;
private ArrayList rstateList;
private String rdistrictId;
private String rdistrictName;
private ArrayList rdistrictList;
private String releaserType;
private String showRelation;
private String roleId;
private String userId;
	
	
	/*Added for device integration*/
	private String guidUpload;
	private String parentPathFP;
	private String fileNameFP;
	
	private String parentPathScan;
	private String fileNameScan;
	
	private String parentPathSign;
	private String fileNameSign;
	private String forwardName;
	
	
	private String photoName;
	private String webcameraPath;
	
	
	
	
	
	//ADDED BY SHRUTI---21 FEB 2014
	private byte[] propImage1DocContents;
	private byte[] propImage2DocContents;
	private byte[] propImage3DocContents;
	private byte[] propMapDocContents;
	private byte[] propRinDocContents;
	private byte[] propKhasraDocContents;
	private String parentOfficeId;
	private String parentOfficeName;
	private String parentDistrictId;
	private String parentDistrictName;
	private String parentReferenceId;
	private String userTypeId;
	
	
	
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
	
	public String getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}
	public byte[] getPropImage1DocContents() {
		return propImage1DocContents;
	}
	public void setPropImage1DocContents(byte[] propImage1DocContents) {
		this.propImage1DocContents = propImage1DocContents;
	}
	public byte[] getPropImage2DocContents() {
		return propImage2DocContents;
	}
	public void setPropImage2DocContents(byte[] propImage2DocContents) {
		this.propImage2DocContents = propImage2DocContents;
	}
	public byte[] getPropImage3DocContents() {
		return propImage3DocContents;
	}
	public void setPropImage3DocContents(byte[] propImage3DocContents) {
		this.propImage3DocContents = propImage3DocContents;
	}
	public byte[] getPropMapDocContents() {
		return propMapDocContents;
	}
	public void setPropMapDocContents(byte[] propMapDocContents) {
		this.propMapDocContents = propMapDocContents;
	}
	public byte[] getPropRinDocContents() {
		return propRinDocContents;
	}
	public void setPropRinDocContents(byte[] propRinDocContents) {
		this.propRinDocContents = propRinDocContents;
	}
	public byte[] getPropKhasraDocContents() {
		return propKhasraDocContents;
	}
	public void setPropKhasraDocContents(byte[] propKhasraDocContents) {
		this.propKhasraDocContents = propKhasraDocContents;
	}
public String getScriptRelType() {
	return scriptRelType;
}
public void setScriptRelType(String scriptRelType) {
	this.scriptRelType = scriptRelType;
}
public int getIsFromView() {
	return isFromView;
}
public void setIsFromView(int isFromView) {
	this.isFromView = isFromView;
}
public ArrayList getViewResultList() {
	return viewResultList;
}
public void setViewResultList(ArrayList viewResultList) {
	this.viewResultList = viewResultList;
}
public String getViewComb() {
	return viewComb;
}
public void setViewComb(String viewComb) {
	this.viewComb = viewComb;
}
public int getIsViewEmpty() {
	return isViewEmpty;
}
public void setIsViewEmpty(int isViewEmpty) {
	this.isViewEmpty = isViewEmpty;
}
public int getIsReleaseSave() {
	return isReleaseSave;
}
public void setIsReleaseSave(int isReleaseSave) {
	this.isReleaseSave = isReleaseSave;
}
public String getForwrdAftrPrprtyDisp() {
	return forwrdAftrPrprtyDisp;
}
public void setForwrdAftrPrprtyDisp(String forwrdAftrPrprtyDisp) {
	this.forwrdAftrPrprtyDisp = forwrdAftrPrprtyDisp;
}
public int getNotInsertedRel() {
	return notInsertedRel;
}
public void setNotInsertedRel(int notInsertedRel) {
	this.notInsertedRel = notInsertedRel;
}
public int getIsRelSuccess() {
	return isRelSuccess;
}
public void setIsRelSuccess(int isRelSuccess) {
	this.isRelSuccess = isRelSuccess;
}
public String getRelPhotoPath() {
	return relPhotoPath;
}
public void setRelPhotoPath(String relPhotoPath) {
	this.relPhotoPath = relPhotoPath;
}
public String getRelThumbPath() {
	return relThumbPath;
}
public void setRelThumbPath(String relThumbPath) {
	this.relThumbPath = relThumbPath;
}
public String getRelSignPath() {
	return relSignPath;
}
public void setRelSignPath(String relSignPath) {
	this.relSignPath = relSignPath;
}
public String getRelDeathCrtPath() {
	return relDeathCrtPath;
}
public void setRelDeathCrtPath(String relDeathCrtPath) {
	this.relDeathCrtPath = relDeathCrtPath;
}
public String getRstateName() {
	return rstateName;
}
public void setRstateName(String rstateName) {
	this.rstateName = rstateName;
}
public String getFuncId() {
	return funcId;
}
public void setFuncId(String funcId) {
	this.funcId = funcId;
}
public String getRelationId() {
	return relationId;
}
public void setRelationId(String relationId) {
	this.relationId = relationId;
}
public ArrayList getRelationList() {
	return relationList;
}
public void setRelationList(ArrayList relationList) {
	this.relationList = relationList;
}
public ArrayList getLockDetailsRelSearch() {
	return lockDetailsRelSearch;
}
public void setLockDetailsRelSearch(ArrayList lockDetailsRelSearch) {
	this.lockDetailsRelSearch = lockDetailsRelSearch;
}
public String getLockComb() {
	return lockComb;
}
public void setLockComb(String lockComb) {
	this.lockComb = lockComb;
}
public String getPropertyReleaseId() {
	return propertyReleaseId;
}
public void setPropertyReleaseId(String propertyReleaseId) {
	this.propertyReleaseId = propertyReleaseId;
}
public ArrayList getPendingDetailsR() {
	return pendingDetailsR;
}
public void setPendingDetailsR(ArrayList pendingDetailsR) {
	this.pendingDetailsR = pendingDetailsR;
}
public String getPendingCumDetlR() {
	return pendingCumDetlR;
}
public void setPendingCumDetlR(String pendingCumDetlR) {
	this.pendingCumDetlR = pendingCumDetlR;
}
public String getPendingCumDetl() {
	return pendingCumDetl;
}
public void setPendingCumDetl(String pendingCumDetl) {
	this.pendingCumDetl = pendingCumDetl;
}
public ArrayList getPendingDetails() {
	return pendingDetails;
}
public void setPendingDetails(ArrayList pendingDetails) {
	this.pendingDetails = pendingDetails;
}
public int getNoRecFound() {
	return noRecFound;
}
public void setNoRecFound(int noRecFound) {
	this.noRecFound = noRecFound;
}
public int getNotUpdatedAfterPay() {
	return notUpdatedAfterPay;
}
public void setNotUpdatedAfterPay(int notUpdatedAfterPay) {
	this.notUpdatedAfterPay = notUpdatedAfterPay;
}
public int getNotInsertedP() {
	return notInsertedP;
}
public void setNotInsertedP(int notInsertedP) {
	this.notInsertedP = notInsertedP;
}
public int getIsPartial() {
	return isPartial;
}
public void setIsPartial(int isPartial) {
	this.isPartial = isPartial;
}
public int getIsPayCompl() {
	return isPayCompl;
}
public void setIsPayCompl(int isPayCompl) {
	this.isPayCompl = isPayCompl;
}
public int getIsLockSave() {
	return isLockSave;
}
public void setIsLockSave(int isLockSave) {
	this.isLockSave = isLockSave;
}
public HashMap getMapPropertyTransPartyDisp() {
	return mapPropertyTransPartyDisp;
}
public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
	this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
}
public int getIsLockSuccess() {
	return isLockSuccess;
}
public void setIsLockSuccess(int isLockSuccess) {
	this.isLockSuccess = isLockSuccess;
}
public String getIdProofName() {
	return idProofName;
}
public void setIdProofName(String idProofName) {
	this.idProofName = idProofName;
}
public int getNotInsertedLock() {
	return notInsertedLock;
}
public void setNotInsertedLock(int notInsertedLock) {
	this.notInsertedLock = notInsertedLock;
}
public String getAlrdyLockId() {
	return alrdyLockId;
}
public void setAlrdyLockId(String alrdyLockId) {
	this.alrdyLockId = alrdyLockId;
}
public String getPropertyLockid() {
	return propertyLockid;
}
public void setPropertyLockid(String propertyLockid) {
	this.propertyLockid = propertyLockid;
}
public int getIsAlrdyLocked() {
	return isAlrdyLocked;
}
public void setIsAlrdyLocked(int isAlrdyLocked) {
	this.isAlrdyLocked = isAlrdyLocked;
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
public String getRadioSelect() {
	return radioSelect;
}
public void setRadioSelect(String radioSelect) {
	this.radioSelect = radioSelect;
}
public String getIsDisable() {
	return isDisable;
}
public void setIsDisable(String isDisable) {
	this.isDisable = isDisable;
}
public ArrayList getPropList() {
	return propList;
}
public void setPropList(ArrayList propList) {
	this.propList = propList;
}
public String getRegCompDate() {
	return regCompDate;
}
public void setRegCompDate(String regCompDate) {
	this.regCompDate = regCompDate;
}
public String getPropComb() {
	return propComb;
}
public void setPropComb(String propComb) {
	this.propComb = propComb;
}
public String getChckBx() {
	return chckBx;
}
public void setChckBx(String chckBx) {
	this.chckBx = chckBx;
}
public int getIsRegDetlEmpty() {
	return isRegDetlEmpty;
}
public void setIsRegDetlEmpty(int isRegDetlEmpty) {
	this.isRegDetlEmpty = isRegDetlEmpty;
}
public String getRegCompNumber() {
	return regCompNumber;
}
public void setRegCompNumber(String regCompNumber) {
	this.regCompNumber = regCompNumber;
}
public String getPropId() {
	return propId;
}
public void setPropId(String propId) {
	this.propId = propId;
}
public String getPropStatus() {
	return propStatus;
}
public void setPropStatus(String propStatus) {
	this.propStatus = propStatus;
}
public String getPropDeed() {
	return propDeed;
}
public void setPropDeed(String propDeed) {
	this.propDeed = propDeed;
}
public String getPropAry() {
	return propAry;
}
public void setPropAry(String propAry) {
	this.propAry = propAry;
}
public ArrayList getRegDetls() {
	return regDetls;
}
public void setRegDetls(ArrayList regDetls) {
	this.regDetls = regDetls;
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
public String getPrimKeyPymt() {
	return primKeyPymt;
}
public void setPrimKeyPymt(String primKeyPymt) {
	this.primKeyPymt = primKeyPymt;
}
public String getFlg() {
	return flg;
}
public void setFlg(String flg) {
	this.flg = flg;
}
public int getIsPayment() {
	return isPayment;
}
public void setIsPayment(int isPayment) {
	this.isPayment = isPayment;
}
public String getOrgCountryName() {
	return orgCountryName;
}
public void setOrgCountryName(String orgCountryName) {
	this.orgCountryName = orgCountryName;
}
public String getOrgStateName() {
	return orgStateName;
}
public void setOrgStateName(String orgStateName) {
	this.orgStateName = orgStateName;
}
public String getOrgDistrictName() {
	return orgDistrictName;
}
public void setOrgDistrictName(String orgDistrictName) {
	this.orgDistrictName = orgDistrictName;
}
public ArrayList getAppellate() {
	return appellate;
}
public void setAppellate(ArrayList appellate) {
	this.appellate = appellate;
}
public String getOrgName() {
	return orgName;
}
public void setOrgName(String orgName) {
	this.orgName = orgName;
}
public String getAuthName() {
	return authName;
}
public void setAuthName(String authName) {
	this.authName = authName;
}
public String getOrgCountry() {
	return orgCountry;
}
public void setOrgCountry(String orgCountry) {
	this.orgCountry = orgCountry;
}
public String getOrgState() {
	return orgState;
}
public void setOrgState(String orgState) {
	this.orgState = orgState;
}
public String getOrgDistrict() {
	return orgDistrict;
}
public void setOrgDistrict(String orgDistrict) {
	this.orgDistrict = orgDistrict;
}
public String getOrgAddress() {
	return orgAddress;
}
public void setOrgAddress(String orgAddress) {
	this.orgAddress = orgAddress;
}
public String getOrgMobno() {
	return orgMobno;
}
public void setOrgMobno(String orgMobno) {
	this.orgMobno = orgMobno;
}
public String getOrgPhno() {
	return orgPhno;
}
public void setOrgPhno(String orgPhno) {
	this.orgPhno = orgPhno;
}
public String getAppTypeName() {
	return appTypeName;
}
public void setAppTypeName(String appTypeName) {
	this.appTypeName = appTypeName;
}

public String getAppType() {
	return appType;
}
public void setAppType(String appType) {
	this.appType = appType;
}
public String getPropertyTxnId() {
	return propertyTxnId;
}
public void setPropertyTxnId(String propertyTxnId) {
	this.propertyTxnId = propertyTxnId;
}
public String getTransactionId() {
	return transactionId;
}
public void setTransactionId(String transactionId) {
	this.transactionId = transactionId;
}
public String getPropertyLockFlag() {
	return propertyLockFlag;
}
public void setPropertyLockFlag(String propertyLockFlag) {
	this.propertyLockFlag = propertyLockFlag;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getMidName() {
	return midName;
}
public void setMidName(String midName) {
	this.midName = midName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getGuardianName() {
	return guardianName;
}
public void setGuardianName(String guardianName) {
	this.guardianName = guardianName;
}
public String getMotherName() {
	return motherName;
}
public void setMotherName(String motherName) {
	this.motherName = motherName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public ArrayList getCountrys() {
	return countrys;
}
public void setCountrys(ArrayList countrys) {
	this.countrys = countrys;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public ArrayList getStates() {
	return states;
}
public void setStates(ArrayList states) {
	this.states = states;
}
public String getDistrict() {
	return district;
}
public void setDistrict(String district) {
	this.district = district;
}
public ArrayList getDistricts() {
	return districts;
}
public void setDistricts(ArrayList districts) {
	this.districts = districts;
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getIdProof() {
	return idProof;
}
public void setIdProof(String idProof) {
	this.idProof = idProof;
}
public ArrayList getIdProofs() {
	return idProofs;
}
public void setIdProofs(ArrayList idProofs) {
	this.idProofs = idProofs;
}
public String getIdProofNo() {
	return idProofNo;
}
public void setIdProofNo(String idProofNo) {
	this.idProofNo = idProofNo;
}
public String getRegistrationId() {
	return registrationId;
}
public void setRegistrationId(String registrationId) {
	this.registrationId = registrationId;
}
public String getRegistrationDate() {
	return registrationDate;
}
public void setRegistrationDate(String registrationDate) {
	this.registrationDate = registrationDate;
}
public String getPhotoFlg() {
	return photoFlg;
}
public void setPhotoFlg(String photoFlg) {
	this.photoFlg = photoFlg;
}
public String getThumbFlg() {
	return thumbFlg;
}
public void setThumbFlg(String thumbFlg) {
	this.thumbFlg = thumbFlg;
}
public String getSignFlg() {
	return signFlg;
}
public void setSignFlg(String signFlg) {
	this.signFlg = signFlg;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getLockStatus() {
	return lockStatus;
}
public void setLockStatus(String lockStatus) {
	this.lockStatus = lockStatus;
}
public String getPoaRegNo() {
	return poaRegNo;
}
public void setPoaRegNo(String poaRegNo) {
	this.poaRegNo = poaRegNo;
}
public String getPoaRegDate() {
	return poaRegDate;
}
public void setPoaRegDate(String poaRegDate) {
	this.poaRegDate = poaRegDate;
}
public String getReleaseDate() {
	return releaseDate;
}
public void setReleaseDate(String releaseDate) {
	this.releaseDate = releaseDate;
}
public String getDateLocking() {
	return dateLocking;
}
public void setDateLocking(String dateLocking) {
	this.dateLocking = dateLocking;
}
public String getReasonForRelease() {
	return reasonForRelease;
}
public void setReasonForRelease(String reasonForRelease) {
	this.reasonForRelease = reasonForRelease;
}
public String getRemarksForRelease() {
	return remarksForRelease;
}
public void setRemarksForRelease(String remarksForRelease) {
	this.remarksForRelease = remarksForRelease;
}
public String getFunctionTxnId() {
	return functionTxnId;
}
public void setFunctionTxnId(String functionTxnId) {
	this.functionTxnId = functionTxnId;
}
public String getSroId() {
	return sroId;
}
public void setSroId(String sroId) {
	this.sroId = sroId;
}
public String getSroName() {
	return sroName;
}
public void setSroName(String sroName) {
	this.sroName = sroName;
}
public String getUpdate() {
	return update;
}
public void setUpdate(String update) {
	this.update = update;
}
public String getFunctionId() {
	return functionId;
}
public void setFunctionId(String functionId) {
	this.functionId = functionId;
}
public String getBankName() {
	return bankName;
}
public void setBankName(String bankName) {
	this.bankName = bankName;
}
public String getChallanBankName() {
	return challanBankName;
}
public void setChallanBankName(String challanBankName) {
	this.challanBankName = challanBankName;
}
public String getBankAddress() {
	return bankAddress;
}
public void setBankAddress(String bankAddress) {
	this.bankAddress = bankAddress;
}
public String getFee() {
	return fee;
}
public void setFee(String fee) {
	this.fee = fee;
}
public String getOtherFee() {
	return otherFee;
}
public void setOtherFee(String otherFee) {
	this.otherFee = otherFee;
}
public String getTotalFee() {
	return totalFee;
}
public void setTotalFee(String totalFee) {
	this.totalFee = totalFee;
}
public String getModeOfPayment() {
	return modeOfPayment;
}
public void setModeOfPayment(String modeOfPayment) {
	this.modeOfPayment = modeOfPayment;
}
public String getChallanNo() {
	return challanNo;
}
public void setChallanNo(String challanNo) {
	this.challanNo = challanNo;
}
public String getChallanDate() {
	return challanDate;
}
public void setChallanDate(String challanDate) {
	this.challanDate = challanDate;
}
public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public String getAppStatus() {
	return appStatus;
}
public void setAppStatus(String appStatus) {
	this.appStatus = appStatus;
}
public String getBackReleaseAction() {
	return backReleaseAction;
}
public void setBackReleaseAction(String backReleaseAction) {
	this.backReleaseAction = backReleaseAction;
}
public String getTxtReg() {
	return txtReg;
}
public void setTxtReg(String txtReg) {
	this.txtReg = txtReg;
}
public String getTempTxnId() {
	return tempTxnId;
}
public void setTempTxnId(String tempTxnId) {
	this.tempTxnId = tempTxnId;
}
public String getMarketValue() {
	return marketValue;
}
public void setMarketValue(String marketValue) {
	this.marketValue = marketValue;
}
public String getConsiderationAmount() {
	return considerationAmount;
}
public void setConsiderationAmount(String considerationAmount) {
	this.considerationAmount = considerationAmount;
}
public String getExemption() {
	return exemption;
}
public void setExemption(String exemption) {
	this.exemption = exemption;
}
public String getAdjudication() {
	return adjudication;
}
public void setAdjudication(String adjudication) {
	this.adjudication = adjudication;
}
public String getCaveats() {
	return caveats;
}
public void setCaveats(String caveats) {
	this.caveats = caveats;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getStampDuty() {
	return stampDuty;
}
public void setStampDuty(String stampDuty) {
	this.stampDuty = stampDuty;
}
public String getRegistrationFee() {
	return registrationFee;
}
public void setRegistrationFee(String registrationFee) {
	this.registrationFee = registrationFee;
}
public String getRelationship() {
	return relationship;
}
public void setRelationship(String relationship) {
	this.relationship = relationship;
}
public String getRelativeFirstName() {
	return relativeFirstName;
}
public void setRelativeFirstName(String relativeFirstName) {
	this.relativeFirstName = relativeFirstName;
}
public String getRelativeMiddleName() {
	return relativeMiddleName;
}
public void setRelativeMiddleName(String relativeMiddleName) {
	this.relativeMiddleName = relativeMiddleName;
}
public String getRelativeLastName() {
	return relativeLastName;
}
public void setRelativeLastName(String relativeLastName) {
	this.relativeLastName = relativeLastName;
}
public String getRelativeGender() {
	return relativeGender;
}
public void setRelativeGender(String relativeGender) {
	this.relativeGender = relativeGender;
}
public String getRelativeAge() {
	return relativeAge;
}
public void setRelativeAge(String relativeAge) {
	this.relativeAge = relativeAge;
}
public String getRelativeFatherName() {
	return relativeFatherName;
}
public void setRelativeFatherName(String relativeFatherName) {
	this.relativeFatherName = relativeFatherName;
}
public String getRelativeMotherName() {
	return relativeMotherName;
}
public void setRelativeMotherName(String relativeMotherName) {
	this.relativeMotherName = relativeMotherName;
}
public String getRelativeAddress() {
	return relativeAddress;
}
public void setRelativeAddress(String relativeAddress) {
	this.relativeAddress = relativeAddress;
}
public String getRelativeCountry() {
	return relativeCountry;
}
public void setRelativeCountry(String relativeCountry) {
	this.relativeCountry = relativeCountry;
}
public String getRelativeState() {
	return relativeState;
}
public void setRelativeState(String relativeState) {
	this.relativeState = relativeState;
}
public String getRelativeCity() {
	return relativeCity;
}
public void setRelativeCity(String relativeCity) {
	this.relativeCity = relativeCity;
}
public String getRelativePostalCode() {
	return relativePostalCode;
}
public void setRelativePostalCode(String relativePostalCode) {
	this.relativePostalCode = relativePostalCode;
}
public String getRelativePhone() {
	return relativePhone;
}
public void setRelativePhone(String relativePhone) {
	this.relativePhone = relativePhone;
}
public String getRelativeMobile() {
	return relativeMobile;
}
public void setRelativeMobile(String relativeMobile) {
	this.relativeMobile = relativeMobile;
}
public String getRelativeEmail() {
	return relativeEmail;
}
public void setRelativeEmail(String relativeEmail) {
	this.relativeEmail = relativeEmail;
}
public String getRelativeIdProof() {
	return relativeIdProof;
}
public void setRelativeIdProof(String relativeIdProof) {
	this.relativeIdProof = relativeIdProof;
}
public String getRelativeIdNo() {
	return relativeIdNo;
}
public void setRelativeIdNo(String relativeIdNo) {
	this.relativeIdNo = relativeIdNo;
}
public String getRelativeBankName() {
	return relativeBankName;
}
public void setRelativeBankName(String relativeBankName) {
	this.relativeBankName = relativeBankName;
}
public String getRelativeBankAddress() {
	return relativeBankAddress;
}
public void setRelativeBankAddress(String relativeBankAddress) {
	this.relativeBankAddress = relativeBankAddress;
}
public String getPropertyLockDisplayAction() {
	return propertyLockDisplayAction;
}
public void setPropertyLockDisplayAction(String propertyLockDisplayAction) {
	this.propertyLockDisplayAction = propertyLockDisplayAction;
}
public String getLockSuccessAction() {
	return lockSuccessAction;
}
public void setLockSuccessAction(String lockSuccessAction) {
	this.lockSuccessAction = lockSuccessAction;
}
public String getPaymentSuccessAction() {
	return paymentSuccessAction;
}
public void setPaymentSuccessAction(String paymentSuccessAction) {
	this.paymentSuccessAction = paymentSuccessAction;
}
public String getLockModifyAction() {
	return lockModifyAction;
}
public void setLockModifyAction(String lockModifyAction) {
	this.lockModifyAction = lockModifyAction;
}
public String getBackLockAction() {
	return backLockAction;
}
public void setBackLockAction(String backLockAction) {
	this.backLockAction = backLockAction;
}
public String getLockChallanAction() {
	return lockChallanAction;
}
public void setLockChallanAction(String lockChallanAction) {
	this.lockChallanAction = lockChallanAction;
}
public String getLockSearchAction() {
	return lockSearchAction;
}
public void setLockSearchAction(String lockSearchAction) {
	this.lockSearchAction = lockSearchAction;
}
public String getLockRegSearchAction() {
	return lockRegSearchAction;
}
public void setLockRegSearchAction(String lockRegSearchAction) {
	this.lockRegSearchAction = lockRegSearchAction;
}
public String getPropertyLockForm() {
	return propertyLockForm;
}
public void setPropertyLockForm(String propertyLockForm) {
	this.propertyLockForm = propertyLockForm;
}
public String getPropertyLockInsertAction() {
	return propertyLockInsertAction;
}
public void setPropertyLockInsertAction(String propertyLockInsertAction) {
	this.propertyLockInsertAction = propertyLockInsertAction;
}
public String getLockViewlID() {
	return lockViewlID;
}
public void setLockViewlID(String lockViewlID) {
	this.lockViewlID = lockViewlID;
}
public String getLockViewDetailsAction() {
	return lockViewDetailsAction;
}
public void setLockViewDetailsAction(String lockViewDetailsAction) {
	this.lockViewDetailsAction = lockViewDetailsAction;
}
public String getCreatedDt() {
	return createdDt;
}
public void setCreatedDt(String createdDt) {
	this.createdDt = createdDt;
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
public String getLockReleaseDetailsAction() {
	return lockReleaseDetailsAction;
}
public void setLockReleaseDetailsAction(String lockReleaseDetailsAction) {
	this.lockReleaseDetailsAction = lockReleaseDetailsAction;
}
public String getLockReleaseDetail() {
	return lockReleaseDetail;
}
public void setLockReleaseDetail(String lockReleaseDetail) {
	this.lockReleaseDetail = lockReleaseDetail;
}
public String getUnLockDetail() {
	return unLockDetail;
}
public void setUnLockDetail(String unLockDetail) {
	this.unLockDetail = unLockDetail;
}
public String getUnLockDetailsView() {
	return unLockDetailsView;
}
public void setUnLockDetailsView(String unLockDetailsView) {
	this.unLockDetailsView = unLockDetailsView;
}
public String getUnLockSuccessAction() {
	return unLockSuccessAction;
}
public void setUnLockSuccessAction(String unLockSuccessAction) {
	this.unLockSuccessAction = unLockSuccessAction;
}
public String getReleaseModifyAction() {
	return releaseModifyAction;
}
public void setReleaseModifyAction(String releaseModifyAction) {
	this.releaseModifyAction = releaseModifyAction;
}
public String getUnLockPopupAction() {
	return unLockPopupAction;
}
public void setUnLockPopupAction(String unLockPopupAction) {
	this.unLockPopupAction = unLockPopupAction;
}
public String getCountryName() {
	return countryName;
}
public void setCountryName(String countryName) {
	this.countryName = countryName;
}
public String getCountryId() {
	return countryId;
}
public void setCountryId(String countryId) {
	this.countryId = countryId;
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
public String getStateId() {
	return stateId;
}
public void setStateId(String stateId) {
	this.stateId = stateId;
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
public String getUploadFilePath() {
	return uploadFilePath;
}
public void setUploadFilePath(String uploadFilePath) {
	this.uploadFilePath = uploadFilePath;
}
public String getActionName() {
	return actionName;
}
public void setActionName(String actionName) {
	this.actionName = actionName;
}
public String getDocumentName() {
	return documentName;
}
public void setDocumentName(String documentName) {
	this.documentName = documentName;
}
public FormFile getAttachedDoc() {
	return attachedDoc;
}
public void setAttachedDoc(FormFile attachedDoc) {
	this.attachedDoc = attachedDoc;
}
public byte[] getDocContents() {
	return docContents;
}
public void setDocContents(byte[] docContents) {
	this.docContents = docContents;
}
public String getPhotoSize() {
	return photoSize;
}
public void setPhotoSize(String photoSize) {
	this.photoSize = photoSize;
}
public String getThunmbName() {
	return thunmbName;
}
public void setThunmbName(String thunmbName) {
	this.thunmbName = thunmbName;
}
public FormFile getAttachedthumb() {
	return attachedthumb;
}
public void setAttachedthumb(FormFile attachedthumb) {
	this.attachedthumb = attachedthumb;
}
public byte[] getThumbContents() {
	return thumbContents;
}
public void setThumbContents(byte[] thumbContents) {
	this.thumbContents = thumbContents;
}
public String getThumbSize() {
	return thumbSize;
}
public void setThumbSize(String thumbSize) {
	this.thumbSize = thumbSize;
}
public String getSignatureName() {
	return signatureName;
}
public void setSignatureName(String signatureName) {
	this.signatureName = signatureName;
}
public FormFile getAttachedSignature() {
	return attachedSignature;
}
public void setAttachedSignature(FormFile attachedSignature) {
	this.attachedSignature = attachedSignature;
}
public byte[] getSignatureContents() {
	return signatureContents;
}
public void setSignatureContents(byte[] signatureContents) {
	this.signatureContents = signatureContents;
}
public String getSignatureSize() {
	return signatureSize;
}
public void setSignatureSize(String signatureSize) {
	this.signatureSize = signatureSize;
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
public String getShowDist() {
	return showDist;
}
public void setShowDist(String showDist) {
	this.showDist = showDist;
}
public String getShowgender() {
	return showgender;
}
public void setShowgender(String showgender) {
	this.showgender = showgender;
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
public String getPersonType() {
	return personType;
}
public void setPersonType(String personType) {
	this.personType = personType;
}
public String getReleaserName() {
	return releaserName;
}
public void setReleaserName(String releaserName) {
	this.releaserName = releaserName;
}
public String getRelationType() {
	return relationType;
}
public void setRelationType(String relationType) {
	this.relationType = relationType;
}
public String getRelcountryName() {
	return relcountryName;
}
public void setRelcountryName(String relcountryName) {
	this.relcountryName = relcountryName;
}
public String getRelStateName() {
	return relStateName;
}
public void setRelStateName(String relStateName) {
	this.relStateName = relStateName;
}
public String getRelDistName() {
	return relDistName;
}
public void setRelDistName(String relDistName) {
	this.relDistName = relDistName;
}
public String getRelDistrictId() {
	return relDistrictId;
}
public void setRelDistrictId(String relDistrictId) {
	this.relDistrictId = relDistrictId;
}
public String getRelStateId() {
	return relStateId;
}
public void setRelStateId(String relStateId) {
	this.relStateId = relStateId;
}
public String getRelCountryId() {
	return relCountryId;
}
public void setRelCountryId(String relCountryId) {
	this.relCountryId = relCountryId;
}
public ArrayList getRelStateList() {
	return relStateList;
}
public void setRelStateList(ArrayList relStateList) {
	this.relStateList = relStateList;
}
public ArrayList getRelCountryList() {
	return relCountryList;
}
public void setRelCountryList(ArrayList relCountryList) {
	this.relCountryList = relCountryList;
}
public ArrayList getRelDistrictList() {
	return relDistrictList;
}
public void setRelDistrictList(ArrayList relDistrictList) {
	this.relDistrictList = relDistrictList;
}
public String getRelation() {
	return relation;
}
public void setRelation(String relation) {
	this.relation = relation;
}
public String getRelAddress() {
	return relAddress;
}
public void setRelAddress(String relAddress) {
	this.relAddress = relAddress;
}
public String getRelEmail() {
	return relEmail;
}
public void setRelEmail(String relEmail) {
	this.relEmail = relEmail;
}
public String getRelMobNo() {
	return relMobNo;
}
public void setRelMobNo(String relMobNo) {
	this.relMobNo = relMobNo;
}
public String getRelphone() {
	return relphone;
}
public void setRelphone(String relphone) {
	this.relphone = relphone;
}
public String getRelMotherName() {
	return relMotherName;
}
public void setRelMotherName(String relMotherName) {
	this.relMotherName = relMotherName;
}
public String getRelFatherName() {
	return relFatherName;
}
public void setRelFatherName(String relFatherName) {
	this.relFatherName = relFatherName;
}
public FormFile getRelFilePhoto() {
	return relFilePhoto;
}
public void setRelFilePhoto(FormFile relFilePhoto) {
	this.relFilePhoto = relFilePhoto;
}
public FormFile getRelFileThumb() {
	return relFileThumb;
}
public void setRelFileThumb(FormFile relFileThumb) {
	this.relFileThumb = relFileThumb;
}
public FormFile getRelFileSig() {
	return relFileSig;
}
public void setRelFileSig(FormFile relFileSig) {
	this.relFileSig = relFileSig;
}
public String getRelPhotoName() {
	return relPhotoName;
}
public void setRelPhotoName(String relPhotoName) {
	this.relPhotoName = relPhotoName;
}
public FormFile getRelAttachDoc() {
	return relAttachDoc;
}
public void setRelAttachDoc(FormFile relAttachDoc) {
	this.relAttachDoc = relAttachDoc;
}
public byte[] getRelPhotoContents() {
	return relPhotoContents;
}
public void setRelPhotoContents(byte[] relPhotoContents) {
	this.relPhotoContents = relPhotoContents;
}
public String getRelPhotoSize() {
	return relPhotoSize;
}
public void setRelPhotoSize(String relPhotoSize) {
	this.relPhotoSize = relPhotoSize;
}
public String getRelThumbName() {
	return relThumbName;
}
public void setRelThumbName(String relThumbName) {
	this.relThumbName = relThumbName;
}
public FormFile getRelAttachthumb() {
	return relAttachthumb;
}
public void setRelAttachthumb(FormFile relAttachthumb) {
	this.relAttachthumb = relAttachthumb;
}
public byte[] getRelThumbContents() {
	return relThumbContents;
}
public void setRelThumbContents(byte[] relThumbContents) {
	this.relThumbContents = relThumbContents;
}
public String getRelThumbSize() {
	return relThumbSize;
}
public void setRelThumbSize(String relThumbSize) {
	this.relThumbSize = relThumbSize;
}
public String getRelSignName() {
	return relSignName;
}
public void setRelSignName(String relSignName) {
	this.relSignName = relSignName;
}
public FormFile getRelAttaSig() {
	return relAttaSig;
}
public void setRelAttaSig(FormFile relAttaSig) {
	this.relAttaSig = relAttaSig;
}
public byte[] getRelSignContents() {
	return relSignContents;
}
public void setRelSignContents(byte[] relSignContents) {
	this.relSignContents = relSignContents;
}
public String getRelSigSize() {
	return relSigSize;
}
public void setRelSigSize(String relSigSize) {
	this.relSigSize = relSigSize;
}
public String getRelPhotoCheck() {
	return relPhotoCheck;
}
public void setRelPhotoCheck(String relPhotoCheck) {
	this.relPhotoCheck = relPhotoCheck;
}
public String getRelThumbCheck() {
	return relThumbCheck;
}
public void setRelThumbCheck(String relThumbCheck) {
	this.relThumbCheck = relThumbCheck;
}
public String getRelSignCheck() {
	return relSignCheck;
}
public void setRelSignCheck(String relSignCheck) {
	this.relSignCheck = relSignCheck;
}
public String getRelDeathCerName() {
	return relDeathCerName;
}
public void setRelDeathCerName(String relDeathCerName) {
	this.relDeathCerName = relDeathCerName;
}
public FormFile getRelDeathCerAttach() {
	return relDeathCerAttach;
}
public void setRelDeathCerAttach(FormFile relDeathCerAttach) {
	this.relDeathCerAttach = relDeathCerAttach;
}
public byte[] getRelDeathCerContents() {
	return relDeathCerContents;
}
public void setRelDeathCerContents(byte[] relDeathCerContents) {
	this.relDeathCerContents = relDeathCerContents;
}
public String getRelDeathCerSize() {
	return relDeathCerSize;
}
public void setRelDeathCerSize(String relDeathCerSize) {
	this.relDeathCerSize = relDeathCerSize;
}
public String getRelDeathCerCheck() {
	return relDeathCerCheck;
}
public void setRelDeathCerCheck(String relDeathCerCheck) {
	this.relDeathCerCheck = relDeathCerCheck;
}
public String getForwardPath() {
	return forwardPath;
}
public void setForwardPath(String forwardPath) {
	this.forwardPath = forwardPath;
}
public String getShowState() {
	return showState;
}
public void setShowState(String showState) {
	this.showState = showState;
}
public String getRcountryName() {
	return rcountryName;
}
public void setRcountryName(String rcountryName) {
	this.rcountryName = rcountryName;
}
public String getRcountryId() {
	return rcountryId;
}
public void setRcountryId(String rcountryId) {
	this.rcountryId = rcountryId;
}
public ArrayList getRcountryList() {
	return rcountryList;
}
public void setRcountryList(ArrayList rcountryList) {
	this.rcountryList = rcountryList;
}
public String getRstateId() {
	return rstateId;
}
public void setRstateId(String rstateId) {
	this.rstateId = rstateId;
}
public String getRstate() {
	return rstate;
}
public void setRstate(String rstate) {
	this.rstate = rstate;
}
public ArrayList getRstateList() {
	return rstateList;
}
public void setRstateList(ArrayList rstateList) {
	this.rstateList = rstateList;
}
public String getRdistrictId() {
	return rdistrictId;
}
public void setRdistrictId(String rdistrictId) {
	this.rdistrictId = rdistrictId;
}
public String getRdistrictName() {
	return rdistrictName;
}
public void setRdistrictName(String rdistrictName) {
	this.rdistrictName = rdistrictName;
}
public ArrayList getRdistrictList() {
	return rdistrictList;
}
public void setRdistrictList(ArrayList rdistrictList) {
	this.rdistrictList = rdistrictList;
}
public String getReleaserType() {
	return releaserType;
}
public void setReleaserType(String releaserType) {
	this.releaserType = releaserType;
}
public String getShowRelation() {
	return showRelation;
}
public void setShowRelation(String showRelation) {
	this.showRelation = showRelation;
}
public String getRoleId() {
	return roleId;
}
public void setRoleId(String roleId) {
	this.roleId = roleId;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
  
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
	public ArrayList getPaymentDashbrdDisplayList() {
		return paymentDashbrdDisplayList;
	}
	public void setPaymentDashbrdDisplayList(ArrayList paymentDashbrdDisplayList) {
		this.paymentDashbrdDisplayList = paymentDashbrdDisplayList;
	}
	public String getGenClick() {
		return genClick;
	}
	public void setGenClick(String genClick) {
		this.genClick = genClick;
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
	public String getGuidUpload() {
		return guidUpload;
	}
	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}
	/**
	 * @return the parentPathScan
	 */
	public String getParentPathScan() {
		return parentPathScan;
	}
	/**
	 * @param parentPathScan the parentPathScan to set
	 */
	public void setParentPathScan(String parentPathScan) {
		this.parentPathScan = parentPathScan;
	}
	/**
	 * @return the fileNameScan
	 */
	public String getFileNameScan() {
		return fileNameScan;
	}
	/**
	 * @param fileNameScan the fileNameScan to set
	 */
	public void setFileNameScan(String fileNameScan) {
		this.fileNameScan = fileNameScan;
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
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getWebcameraPath() {
		return webcameraPath;
	}
	public void setWebcameraPath(String webcameraPath) {
		this.webcameraPath = webcameraPath;
	}
	
}
