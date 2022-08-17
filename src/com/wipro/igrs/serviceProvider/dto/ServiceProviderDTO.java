package com.wipro.igrs.serviceProvider.dto;

/**
 * ===========================================================================
 * File           :   ServiceProviderDTO.java
 * Description    :   Represents the DTO Class

 * Author         :   Lavi Tripathi
 * Created Date   :   July 22, 2013

 * ===========================================================================
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.upload.FormFile;

public class ServiceProviderDTO implements Serializable {
	
	private String formName;
	private String actionName;
	private Object uid;
	private Object officeId;
	
	// Variables for criteria and Terms and Conditions
	
	private Object criteria;
	private Object radioSelect;
	private Object criteriaName;
	private Object criteriaId;
	private Object criteriaIdName;
	private Object tcName;
	private Object tcId;
	private Object tcIdName;
	private Object radioSelectCriteria;
	private int ischecked=0;
	private int isempty=0;
	private ArrayList editDeleteCriteria = new ArrayList();
	private ArrayList editDeleteTC = new ArrayList();
    // End of variables for criteria and Terms and Conditions
	
	// Variables for filling online application of Service Provider
	private Object firstName;
	private Object middleName;
	private Object lastName;
	private Object dob;
	private Object address;
	private Object gender;
	private Object category;
	private Object phNumber;
	private Object mobNumber;
	private Object emailId;
	private Object idProofName;
	private Object idProofDetl;
	private String name;
	private String value;
	private String spType;
	private String spTypeName;
	private int isSpInd=0;
	private int isSpBank=0;
	private int isSpAllType=0;
	private Object bankName;
	private Object authPersName;
	private Object designation;
	private Object education;
	private Object addressSp;
	private Object radioSelectExp;
	private Object radioSelectCh;
	private Object amount;
	private String district;
	private String tehsil;
	private String ward;
	private String mohalla;
	private String areaId;
	private String areaName;
	private String subAreaId;
	private String subAreaName;
	private String wardPatwariId;
	private String wardPatwariName;
	private String colonyId;
	private String disttName;
	private String disttValue;
	private String url;
	private String licNo;
	private String otp;
	private String enablePrintFlag;
	private String otpValidateFlag;
	private String otpChk;
	private String mainTxnId;
	
	
	public String getMainTxnId() {
		return mainTxnId;
	}
	public void setMainTxnId(String mainTxnId) {
		this.mainTxnId = mainTxnId;
	}
	public String getOtpChk() {
		return otpChk;
	}
	public void setOtpChk(String otpChk) {
		this.otpChk = otpChk;
	}
	public String getEnablePrintFlag() {
		return enablePrintFlag;
	}
	public void setEnablePrintFlag(String enablePrintFlag) {
		this.enablePrintFlag = enablePrintFlag;
	}
	public String getOtpValidateFlag() {
		return otpValidateFlag;
	}
	public void setOtpValidateFlag(String otpValidateFlag) {
		this.otpValidateFlag = otpValidateFlag;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getLicNo() {
		return licNo;
	}
	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	private String port;
	
	public String getColonyId() {
		return colonyId;
	}
	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}
	public String getColonyName() {
		return colonyName;
	}
	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}
	public ArrayList getColonyList() {
		return colonyList;
	}
	public void setColonyList(ArrayList colonyList) {
		this.colonyList = colonyList;
	}
	private String colonyName;
	
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
	public String getWardPatwariId() {
		return wardPatwariId;
	}
	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}
	public String getWardPatwariName() {
		return wardPatwariName;
	}
	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public ArrayList getAreaList() {
		return areaList;
	}
	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
	}
	private String tehsilName;
	private String tehsilValue;
	private String wardName;
	private String wardValue;
	private String mohallaName;
	private String mohallaValue;
	private String reqNo;
	private Object termsCond;
	private int isFromSpApp=0;
	FormFile affidavitCriminal=null;
	FormFile characterCertiGaz=null;
	FormFile dobCerti=null;
	FormFile rentDetail=null;
	FormFile higherSecCerti=null;
	FormFile photoImg=null;
	FormFile characterCerti=null;
	FormFile ageProof=null;
	FormFile solvencyCerti=null;
	FormFile policeCaseCerti=null;
	FormFile possessionBusinessDoc=null;
	FormFile higherEduCerti=null;
	FormFile affidavitHardware=null;
	//added by shruti
	FormFile pancardUpload=null;
	FormFile form60Upload=null;
	
	//end
	private String affidavitCriminalDoc;
	private String characterCertiGazDoc;
	private String dobCertiDoc;
	private String rentDetailDoc;
	private String higherSecCertiDoc;
	private String photoImgDoc;
	private String characterCertiDoc;
	private String ageProofDoc;
	private String solvencyCertiDoc;
	private String policeCaseCertiDoc;
	private String possessionBusinessDocDoc;
	private String higherEduCertiDoc;
	private String affidavitHardwareDoc;
	//added by shruti
	private String pancardDoc;
	private String form60Doc;
	//end
	private String docPath;
	private String docName;
	private byte[] affidavitCriminal1;
	private byte[] characterCertiGaz1;
	private byte[] dobCerti1;
	private byte[] rentDetail1;
	private byte[] higherSecCerti1;
	private byte[] photoImg1;
	private byte[] characterCerti1;
	private byte[] ageProof1;
	private byte[] solvencyCerti1;
	private byte[] policeCaseCerti1;
	private byte[] possessionBusinessDoc1;
	private byte[] higherEduCerti1;
	private byte[] affidavitHardware1;
	//added by shruti
	private byte[] pancardUpload1;
	private byte[] form60Upload1;
	//end
	private Object langKnown;
	public byte[] getPancardUpload1() {
		return pancardUpload1;
	}
	public void setPancardUpload1(byte[] pancardUpload1) {
		this.pancardUpload1 = pancardUpload1;
	}

	public byte[] getForm60Upload1() {
		return form60Upload1;
	}
	public void setForm60Upload1(byte[] form60Upload1) {
		this.form60Upload1 = form60Upload1;
	}
	private Object drAddress;
	private Object drPhNo;
	private Object radioSelect1;
	private String secondFileSP;
	private String thirdFileSP;
	private String fourthFileSP;
	private String fifthFileSP;
	private String sixthFileSP;
	private String seventhFileSP;
	private String eighthFileSP;
	private String ninthFileSP;
	private String tenthFileSP;
	private String eleventhFileSP;
	private String twelfthFileSP;
	private String thirteenthFileSP;
	private String firstFileSP;
	private int isAlreadyFiled=0;
	
	
	private ArrayList spDetails = new ArrayList();
	private ArrayList spTypeList = new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList tehsilList = new ArrayList();
	private ArrayList areaList=new ArrayList();
	private ArrayList subAreaList=new ArrayList();
	private ArrayList wardPatwariList=new ArrayList();
	private ArrayList colonyList=new ArrayList();
	
	
	public ArrayList getWardPatwariList() {
		return wardPatwariList;
	}
	public void setWardPatwariList(ArrayList wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}
	public ArrayList getSubAreaList() {
		return subAreaList;
	}
	public void setSubAreaList(ArrayList subAreaList) {
		this.subAreaList = subAreaList;
	}
	private ArrayList wardList = new ArrayList();
	private ArrayList mohallaList = new ArrayList();
	private ArrayList droDetails = new ArrayList();
	
	// End of variables for filling online application of Service Provider
	
	//Variables for application approval/rejection by DR
	
	private Object requestNo;
	private Object date;
	private Object applctnStatus;
	private Object fromCreatedDate;
	private Object toCreatedDate;
	private Object applicantStatus;
	private String durationTo;
	private String durationFrom;
	private String statusName;
	private String statusValue;
	private int isReqApprovd = 0;
	private String affidavitCriminalPath;
	private String characterCertiGazPath;
	private String dobCertiPath;
	private String rentDetailPath;
	private String higherSecCertiPath;
	private String photoImgPath;
	private String characterCertiPath;
	private String ageProofPath;
	private String solvencyCertiPath;
	private String policeCaseCertiPath;
	private String possessionBusinessDocPath;
	private String higherEduCertiPath;
	private String affidavitHardwarePath;
	//added by shruti
	private String pancardPath;
	private String form60Path;
	//end
	private String licenseFromDate;
	private String licenseToDate;
	private String fees;
	private String comments;
	private int isDRApproved=0;
	private String rmrksCallForPV;
	private String rmrksCallForAddInfo;
	private String rmrksForRejection;
	private int isCallForPV=0;
	private int isCallForAddInfo=0;
	private int isReject=0;
	private int isDIG=0;
	private int isReactivateByDR=0;
	private String secondFileDR;
	private String thirdFileDR;
	private String fourthFileDR;
	private String fifthFileDR;
	private String sixthFileDR;
	private String seventhFileDR;
	private String eighthFileDR;
	private String ninthFileDR;
	private String tenthFileDR;
	private String eleventhFileDR;
	private String twelfthFileDR;
	private String thirteenthFileDR;
	private String firstFileDR;
	private int isCallForPVRmrks=0;
	private int isRjectRmrks=0;
	private int isReqApprovdRmrks=0;
	private int isCallForAddInfoRmrks=0;
	private int isFeeNotPaid=0;
	
	
	
	
	
	
	private ArrayList appList = new ArrayList();
	private ArrayList statusList = new ArrayList();
	private ArrayList spDetailsAll = new ArrayList();
	private ArrayList spAppDetails = new ArrayList();
	private ArrayList spAppDetailsAll = new ArrayList();
	private ArrayList spAppDetailsBank = new ArrayList();
	private ArrayList spDocDetails = new ArrayList();
	
	//End of variables for application approval/rejection by DR
	
	//Variables for payment done by Registered User in case of filing a license
	
	private Object paidAmount;
	private Object balAmount;
	private Object lastTxnDate;
	private Object payableAmount;
	private Object spPaymentId;
	private Object paymentFlag;
	private Object drOfcId;
	private Object drOfficeName;
	private Object drDistrict;
	private Object drDistrictName;
	private double amountRemaining;
	private int isPaymentComplete=0;
	private int isLicenseFeePaid=0;
	
	//End of Variables for payment done by Registered User in case of filing a license
	

	

	// for cancel License

	private String 	searchType;
	private  String  licenseId;
	private String  searchName;
	private int isDRCancelled;
	private String gistOrder;
	//end of cancel License
	
	// for license number generation by DR
	private Object licenseNumber;
	private int isLicenseNumberPresent=0;
	private Object year;
	private Object month;
	private Object days;
	private Object balAmountCreditEstamp;
	private Object balAmountCreditOthers;
	private Object balAmountCreditEstampJudicial; // added by siddhartha
	private Object balAmountCreditJudicialEstamp; // added by siddhartha
	
	private Object hdnBalAmountCreditOthers;
	private Object hdnBalAmountCreditEstamp;
	private Object hdnBalAmountCreditEstampJudicial;//added by siddhartha
	private Object chkBoxCreditLimit;
	private String selectedValuesOfCreditLimit;
	private Object previousLicenseNoEstamp;
	private Object previousLicenseNoOthers;
	private Object previousLicenseNoEstampJudicial; // added by siddhartha

	
	// end of license number generation by DR
	
	
	// variables for renewal of license
	
	private int isLicenseNotIssued=0;
	private Object criteriaPopUp;
	private String firstFileRenewal;
	private String secondFileRenewal;
	private String thirdFileRenewal;
	private String fourthFileRenewal;
	private String fifthFileRenewal;
	private String sixthFileRenewal;
	private String seventhFileRenewal;
	private String eighthFileRenewal;
	private String ninthFileRenewal;
	private String tenthFileRenewal;
	private String eleventhFileRenewal;
	private String twelfthFileRenewal;
	private String thirteenthFileRenewal;
	private Object newOrRenewalFlag;
	private int printEnableDisable=0;
	
	// end of variables for renewal of license

	//for credit limit integration
	private Object radioBal;
	private Object estampBalanceCreditLimit;
	private Object othersBalanceCreditLimit;
	private Object judicialEstampBalanceCreditLimit; // added by siddhartha
	//end of credit limit integration
	
	//for e-writing pen integration
	private String parentPathSign;
	private String fileNameSign;
	private String forwardPath;
	private String forwardName;
	private String guid;
	private int isSigned=0;
	private String signatureName;
	//end of e-writing pen integration
	
	//added by shruti--18 march 2014
	private String photoLoc;
	private String signLoc;
	private Object occupation;
	private Object fatherName;
	private Object panCard;
	private Object form60;
	
	
	private String pancardFileSP;
	private String form60FileSP;
	private String pancardFileDR;
	private String form60FileDR;
	private String pancardFileRenewal;
	private String form60FileRenewal;
	private String spName;

	
	private String pancardupldchk;
	private String form60upldchk;
	private String drName;
	private String drDesignation;
	private String opticalWatermarkFlag;
	private Object lNo;
	private Object pdfContent;
	
	private String owmFlag;
	private String owmFile;
	
	
	public String getOwmFlag() {
		return owmFlag;
	}
	public void setOwmFlag(String owmFlag) {
		this.owmFlag = owmFlag;
	}
	public String getOwmFile() {
		return owmFile;
	}
	public void setOwmFile(String owmFile) {
		this.owmFile = owmFile;
	}
	
	
	public Object getLNo() {
		return lNo;
	}
	public void setLNo(Object no) {
		lNo = no;
	}
	public Object getPdfContent() {
		return pdfContent;
	}
	public void setPdfContent(Object pdfContent) {
		this.pdfContent = pdfContent;
	}
	

	public void setPdfContent(String pdfContent) {
		this.pdfContent = pdfContent;
	}
	public String getOpticalWatermarkFlag() {
		return opticalWatermarkFlag;
	}
	public void setOpticalWatermarkFlag(String opticalWatermarkFlag) {
		this.opticalWatermarkFlag = opticalWatermarkFlag;
	}
	public String getDrName() {
		return drName;
	}
	public void setDrName(String drName) {
		this.drName = drName;
	}
	public String getDrDesignation() {
		return drDesignation;
	}
	public void setDrDesignation(String drDesignation) {
		this.drDesignation = drDesignation;
	}
	public String getPancardupldchk() {
		return pancardupldchk;
	}
	public void setPancardupldchk(String pancardupldchk) {
		this.pancardupldchk = pancardupldchk;
	}
	public String getForm60upldchk() {
		return form60upldchk;
	}
	public void setForm60upldchk(String form60upldchk) {
		this.form60upldchk = form60upldchk;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getPancardFileSP() {
	return pancardFileSP;
}
public void setPancardFileSP(String pancardFileSP) {
	this.pancardFileSP = pancardFileSP;
}
public String getForm60FileSP() {
	return form60FileSP;
}
public void setForm60FileSP(String form60FileSP) {
	this.form60FileSP = form60FileSP;
}
public String getPancardFileDR() {
	return pancardFileDR;
}
public void setPancardFileDR(String pancardFileDR) {
	this.pancardFileDR = pancardFileDR;
}
public String getForm60FileDR() {
	return form60FileDR;
}
public void setForm60FileDR(String form60FileDR) {
	this.form60FileDR = form60FileDR;
}
public String getPancardFileRenewal() {
	return pancardFileRenewal;
}
public void setPancardFileRenewal(String pancardFileRenewal) {
	this.pancardFileRenewal = pancardFileRenewal;
}
public String getForm60FileRenewal() {
	return form60FileRenewal;
}
public void setForm60FileRenewal(String form60FileRenewal) {
	this.form60FileRenewal = form60FileRenewal;
}
	public String getPancardDoc() {
		return pancardDoc;
	}
	public void setPancardDoc(String pancardDoc) {
		this.pancardDoc = pancardDoc;
	}
	public String getForm60Doc() {
		return form60Doc;
	}
	public void setForm60Doc(String form60Doc) {
		this.form60Doc = form60Doc;
	}
	public Object getForm60() {
		return form60;
	}
	public void setForm60(Object form60) {
		this.form60 = form60;
	}
	private String optionalDocFlag;
	private String pancardFlag;
	private String form60Flag;
	
	public String getPancardFlag() {
		return pancardFlag;
	}
	public void setPancardFlag(String pancardFlag) {
		this.pancardFlag = pancardFlag;
	}
	public String getPancardPath() {
		return pancardPath;
	}
	public void setPancardPath(String pancardPath) {
		this.pancardPath = pancardPath;
	}
	public String getForm60Path() {
		return form60Path;
	}
	public void setForm60Path(String form60Path) {
		this.form60Path = form60Path;
	}
	public String getForm60Flag() {
		return form60Flag;
	}
	public void setForm60Flag(String form60Flag) {
		this.form60Flag = form60Flag;
	}
	public String getOptionalDocFlag() {
		return optionalDocFlag;
	}
	public void setOptionalDocFlag(String optionalDocFlag) {
		this.optionalDocFlag = optionalDocFlag;
	}
	public FormFile getPancardUpload() {
		return pancardUpload;
	}
	public void setPancardUpload(FormFile pancardUpload) {
		this.pancardUpload = pancardUpload;
	}
	public FormFile getForm60Upload() {
		return form60Upload;
	}
	public void setForm60Upload(FormFile form60Upload) {
		this.form60Upload = form60Upload;
	}
	public Object getPanCard() {
		return panCard;
	}
	public void setPanCard(Object panCard) {
		this.panCard = panCard;
	}
	public Object getOccupation() {
		return occupation;
	}
	public void setOccupation(Object occupation) {
		this.occupation = occupation;
	}
	public Object getFatherName() {
		return fatherName;
	}
	public void setFatherName(Object fatherName) {
		this.fatherName = fatherName;
	}
	public String getPhotoLoc() {
		return photoLoc;
	}
	public void setPhotoLoc(String photoLoc) {
		this.photoLoc = photoLoc;
	}
	public String getSignLoc() {
		return signLoc;
	}
	public void setSignLoc(String signLoc) {
		this.signLoc = signLoc;
	}
	public String getGistOrder() {
		return gistOrder;
	}
	public void setGistOrder(String gistOrder) {
		this.gistOrder = gistOrder;
	}
	public int getIsDRCancelled() {
		return isDRCancelled;
	}
	public void setIsDRCancelled(int isDRCancelled) {
		this.isDRCancelled = isDRCancelled;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getLicenseId() {
		return licenseId;
	}
	public void setLicenseId(String licenseId) {
		this.licenseId = licenseId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
	public Object getUid() {
		return uid;
	}
	public void setUid(Object uid) {
		this.uid = uid;
	}
	public int getIschecked() {
		return ischecked;
	}
	public void setIschecked(int ischecked) {
		this.ischecked = ischecked;
	}
	public Object getCriteria() {
		return criteria;
	}
	public void setCriteria(Object criteria) {
		this.criteria = criteria;
	}
	public Object getRadioSelect() {
		return radioSelect;
	}
	public void setRadioSelect(Object radioSelect) {
		this.radioSelect = radioSelect;
	}
	public ArrayList getEditDeleteCriteria() {
		return editDeleteCriteria;
	}
	public void setEditDeleteCriteria(ArrayList editDeleteCriteria) {
		this.editDeleteCriteria = editDeleteCriteria;
	}
	public Object getCriteriaName() {
		return criteriaName;
	}
	public void setCriteriaName(Object criteriaName) {
		this.criteriaName = criteriaName;
	}
	public Object getCriteriaId() {
		return criteriaId;
	}
	public void setCriteriaId(Object criteriaId) {
		this.criteriaId = criteriaId;
	}
	public Object getRadioSelectCriteria() {
		return radioSelectCriteria;
	}
	public void setRadioSelectCriteria(Object radioSelectCriteria) {
		this.radioSelectCriteria = radioSelectCriteria;
	}
	public Object getCriteriaIdName() {
		return criteriaIdName;
	}
	public void setCriteriaIdName(Object criteriaIdName) {
		this.criteriaIdName = criteriaIdName;
	}
	public int getIsempty() {
		return isempty;
	}
	public void setIsempty(int isempty) {
		this.isempty = isempty;
	}
	public ArrayList getEditDeleteTC() {
		return editDeleteTC;
	}
	public void setEditDeleteTC(ArrayList editDeleteTC) {
		this.editDeleteTC = editDeleteTC;
	}
	public Object getTcName() {
		return tcName;
	}
	public void setTcName(Object tcName) {
		this.tcName = tcName;
	}
	public Object getTcId() {
		return tcId;
	}
	public void setTcId(Object tcId) {
		this.tcId = tcId;
	}
	public Object getTcIdName() {
		return tcIdName;
	}
	public void setTcIdName(Object tcIdName) {
		this.tcIdName = tcIdName;
	}
	public Object getFirstName() {
		return firstName;
	}
	public void setFirstName(Object firstName) {
		this.firstName = firstName;
	}
	public Object getMiddleName() {
		return middleName;
	}
	public void setMiddleName(Object middleName) {
		this.middleName = middleName;
	}
	public Object getLastName() {
		return lastName;
	}
	public void setLastName(Object lastName) {
		this.lastName = lastName;
	}
	public Object getDob() {
		return dob;
	}
	public void setDob(Object dob) {
		this.dob = dob;
	}
	public Object getAddress() {
		return address;
	}
	public void setAddress(Object address) {
		this.address = address;
	}
	public Object getGender() {
		return gender;
	}
	public void setGender(Object gender) {
		this.gender = gender;
	}
	public Object getCategory() {
		return category;
	}
	public void setCategory(Object category) {
		this.category = category;
	}
	public Object getPhNumber() {
		return phNumber;
	}
	public void setPhNumber(Object phNumber) {
		this.phNumber = phNumber;
	}
	public Object getMobNumber() {
		return mobNumber;
	}
	public void setMobNumber(Object mobNumber) {
		this.mobNumber = mobNumber;
	}
	public Object getEmailId() {
		return emailId;
	}
	public void setEmailId(Object emailId) {
		this.emailId = emailId;
	}
	public Object getIdProofName() {
		return idProofName;
	}
	public void setIdProofName(Object idProofName) {
		this.idProofName = idProofName;
	}
	public Object getIdProofDetl() {
		return idProofDetl;
	}
	public void setIdProofDetl(Object idProofDetl) {
		this.idProofDetl = idProofDetl;
	}
	public ArrayList getSpDetails() {
		return spDetails;
	}
	public void setSpDetails(ArrayList spDetails) {
		this.spDetails = spDetails;
	}
	public ArrayList getSpTypeList() {
		return spTypeList;
	}
	public void setSpTypeList(ArrayList spTypeList) {
		this.spTypeList = spTypeList;
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
	public int getIsSpInd() {
		return isSpInd;
	}
	public void setIsSpInd(int isSpInd) {
		this.isSpInd = isSpInd;
	}
	public String getSpType() {
		return spType;
	}
	public void setSpType(String spType) {
		this.spType = spType;
	}
	public String getSpTypeName() {
		return spTypeName;
	}
	public void setSpTypeName(String spTypeName) {
		this.spTypeName = spTypeName;
	}
	public int getIsSpBank() {
		return isSpBank;
	}
	public void setIsSpBank(int isSpBank) {
		this.isSpBank = isSpBank;
	}
	public int getIsSpAllType() {
		return isSpAllType;
	}
	public void setIsSpAllType(int isSpAllType) {
		this.isSpAllType = isSpAllType;
	}
	public Object getBankName() {
		return bankName;
	}
	public void setBankName(Object bankName) {
		this.bankName = bankName;
	}
	public Object getAuthPersName() {
		return authPersName;
	}
	public void setAuthPersName(Object authPersName) {
		this.authPersName = authPersName;
	}
	public Object getDesignation() {
		return designation;
	}
	public void setDesignation(Object designation) {
		this.designation = designation;
	}
	public Object getEducation() {
		return education;
	}
	public void setEducation(Object education) {
		this.education = education;
	}
	public Object getAddressSp() {
		return addressSp;
	}
	public void setAddressSp(Object addressSp) {
		this.addressSp = addressSp;
	}
	public Object getRadioSelectExp() {
		return radioSelectExp;
	}
	public void setRadioSelectExp(Object radioSelectExp) {
		this.radioSelectExp = radioSelectExp;
	}
	public Object getRadioSelectCh() {
		return radioSelectCh;
	}
	public void setRadioSelectCh(Object radioSelectCh) {
		this.radioSelectCh = radioSelectCh;
	}
	public Object getAmount() {
		return amount;
	}
	public void setAmount(Object amount) {
		this.amount = amount;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getTehsil() {
		return tehsil;
	}
	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getMohalla() {
		return mohalla;
	}
	public void setMohalla(String mohalla) {
		this.mohalla = mohalla;
	}
	public String getDisttName() {
		return disttName;
	}
	public void setDisttName(String disttName) {
		this.disttName = disttName;
	}
	public String getDisttValue() {
		return disttValue;
	}
	public void setDisttValue(String disttValue) {
		this.disttValue = disttValue;
	}
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getTehsilValue() {
		return tehsilValue;
	}
	public void setTehsilValue(String tehsilValue) {
		this.tehsilValue = tehsilValue;
	}
	public String getWardName() {
		return wardName;
	}
	public void setWardName(String wardName) {
		this.wardName = wardName;
	}
	public String getWardValue() {
		return wardValue;
	}
	public void setWardValue(String wardValue) {
		this.wardValue = wardValue;
	}
	public String getMohallaName() {
		return mohallaName;
	}
	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}
	public String getMohallaValue() {
		return mohallaValue;
	}
	public void setMohallaValue(String mohallaValue) {
		this.mohallaValue = mohallaValue;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	public ArrayList getWardList() {
		return wardList;
	}
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}
	public ArrayList getMohallaList() {
		return mohallaList;
	}
	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}
	public String getReqNo() {
		return reqNo;
	}
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public Object getTermsCond() {
		return termsCond;
	}
	public void setTermsCond(Object termsCond) {
		this.termsCond = termsCond;
	}
	public int getIsFromSpApp() {
		return isFromSpApp;
	}
	public void setIsFromSpApp(int isFromSpApp) {
		this.isFromSpApp = isFromSpApp;
	}
	public FormFile getAffidavitCriminal() {
		return affidavitCriminal;
	}
	public void setAffidavitCriminal(FormFile affidavitCriminal) {
		this.affidavitCriminal = affidavitCriminal;
	}
	public FormFile getCharacterCertiGaz() {
		return characterCertiGaz;
	}
	public void setCharacterCertiGaz(FormFile characterCertiGaz) {
		this.characterCertiGaz = characterCertiGaz;
	}
	public FormFile getDobCerti() {
		return dobCerti;
	}
	public void setDobCerti(FormFile dobCerti) {
		this.dobCerti = dobCerti;
	}
	public FormFile getRentDetail() {
		return rentDetail;
	}
	public void setRentDetail(FormFile rentDetail) {
		this.rentDetail = rentDetail;
	}
	public FormFile getHigherSecCerti() {
		return higherSecCerti;
	}
	public void setHigherSecCerti(FormFile higherSecCerti) {
		this.higherSecCerti = higherSecCerti;
	}
	public FormFile getPhotoImg() {
		return photoImg;
	}
	public void setPhotoImg(FormFile photoImg) {
		this.photoImg = photoImg;
	}
	public FormFile getCharacterCerti() {
		return characterCerti;
	}
	public void setCharacterCerti(FormFile characterCerti) {
		this.characterCerti = characterCerti;
	}
	public FormFile getAgeProof() {
		return ageProof;
	}
	public void setAgeProof(FormFile ageProof) {
		this.ageProof = ageProof;
	}
	public FormFile getSolvencyCerti() {
		return solvencyCerti;
	}
	public void setSolvencyCerti(FormFile solvencyCerti) {
		this.solvencyCerti = solvencyCerti;
	}
	public FormFile getPoliceCaseCerti() {
		return policeCaseCerti;
	}
	public void setPoliceCaseCerti(FormFile policeCaseCerti) {
		this.policeCaseCerti = policeCaseCerti;
	}
	public String getAffidavitCriminalDoc() {
		return affidavitCriminalDoc;
	}
	public void setAffidavitCriminalDoc(String affidavitCriminalDoc) {
		this.affidavitCriminalDoc = affidavitCriminalDoc;
	}
	public String getCharacterCertiGazDoc() {
		return characterCertiGazDoc;
	}
	public void setCharacterCertiGazDoc(String characterCertiGazDoc) {
		this.characterCertiGazDoc = characterCertiGazDoc;
	}
	public String getDobCertiDoc() {
		return dobCertiDoc;
	}
	public void setDobCertiDoc(String dobCertiDoc) {
		this.dobCertiDoc = dobCertiDoc;
	}
	public String getRentDetailDoc() {
		return rentDetailDoc;
	}
	public void setRentDetailDoc(String rentDetailDoc) {
		this.rentDetailDoc = rentDetailDoc;
	}
	public String getHigherSecCertiDoc() {
		return higherSecCertiDoc;
	}
	public void setHigherSecCertiDoc(String higherSecCertiDoc) {
		this.higherSecCertiDoc = higherSecCertiDoc;
	}
	public String getPhotoImgDoc() {
		return photoImgDoc;
	}
	public void setPhotoImgDoc(String photoImgDoc) {
		this.photoImgDoc = photoImgDoc;
	}
	public String getCharacterCertiDoc() {
		return characterCertiDoc;
	}
	public void setCharacterCertiDoc(String characterCertiDoc) {
		this.characterCertiDoc = characterCertiDoc;
	}
	public String getAgeProofDoc() {
		return ageProofDoc;
	}
	public void setAgeProofDoc(String ageProofDoc) {
		this.ageProofDoc = ageProofDoc;
	}
	public String getSolvencyCertiDoc() {
		return solvencyCertiDoc;
	}
	public void setSolvencyCertiDoc(String solvencyCertiDoc) {
		this.solvencyCertiDoc = solvencyCertiDoc;
	}
	public String getPoliceCaseCertiDoc() {
		return policeCaseCertiDoc;
	}
	public void setPoliceCaseCertiDoc(String policeCaseCertiDoc) {
		this.policeCaseCertiDoc = policeCaseCertiDoc;
	}
	public String getHigherEduCertiDoc() {
		return higherEduCertiDoc;
	}
	public void setHigherEduCertiDoc(String higherEduCertiDoc) {
		this.higherEduCertiDoc = higherEduCertiDoc;
	}
	public String getAffidavitHardwareDoc() {
		return affidavitHardwareDoc;
	}
	public void setAffidavitHardwareDoc(String affidavitHardwareDoc) {
		this.affidavitHardwareDoc = affidavitHardwareDoc;
	}
	public FormFile getHigherEduCerti() {
		return higherEduCerti;
	}
	public void setHigherEduCerti(FormFile higherEduCerti) {
		this.higherEduCerti = higherEduCerti;
	}
	public FormFile getAffidavitHardware() {
		return affidavitHardware;
	}
	public void setAffidavitHardware(FormFile affidavitHardware) {
		this.affidavitHardware = affidavitHardware;
	}
	public FormFile getPossessionBusinessDoc() {
		return possessionBusinessDoc;
	}
	public void setPossessionBusinessDoc(FormFile possessionBusinessDoc) {
		this.possessionBusinessDoc = possessionBusinessDoc;
	}
	public String getPossessionBusinessDocDoc() {
		return possessionBusinessDocDoc;
	}
	public void setPossessionBusinessDocDoc(String possessionBusinessDocDoc) {
		this.possessionBusinessDocDoc = possessionBusinessDocDoc;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public byte[] getAffidavitCriminal1() {
		return affidavitCriminal1;
	}
	public void setAffidavitCriminal1(byte[] affidavitCriminal1) {
		this.affidavitCriminal1 = affidavitCriminal1;
	}
	public byte[] getCharacterCertiGaz1() {
		return characterCertiGaz1;
	}
	public void setCharacterCertiGaz1(byte[] characterCertiGaz1) {
		this.characterCertiGaz1 = characterCertiGaz1;
	}
	public byte[] getDobCerti1() {
		return dobCerti1;
	}
	public void setDobCerti1(byte[] dobCerti1) {
		this.dobCerti1 = dobCerti1;
	}
	public byte[] getRentDetail1() {
		return rentDetail1;
	}
	public void setRentDetail1(byte[] rentDetail1) {
		this.rentDetail1 = rentDetail1;
	}
	public byte[] getHigherSecCerti1() {
		return higherSecCerti1;
	}
	public void setHigherSecCerti1(byte[] higherSecCerti1) {
		this.higherSecCerti1 = higherSecCerti1;
	}
	public byte[] getPhotoImg1() {
		return photoImg1;
	}
	public void setPhotoImg1(byte[] photoImg1) {
		this.photoImg1 = photoImg1;
	}
	public byte[] getCharacterCerti1() {
		return characterCerti1;
	}
	public void setCharacterCerti1(byte[] characterCerti1) {
		this.characterCerti1 = characterCerti1;
	}
	public byte[] getAgeProof1() {
		return ageProof1;
	}
	public void setAgeProof1(byte[] ageProof1) {
		this.ageProof1 = ageProof1;
	}
	public byte[] getSolvencyCerti1() {
		return solvencyCerti1;
	}
	public void setSolvencyCerti1(byte[] solvencyCerti1) {
		this.solvencyCerti1 = solvencyCerti1;
	}
	public byte[] getPoliceCaseCerti1() {
		return policeCaseCerti1;
	}
	public void setPoliceCaseCerti1(byte[] policeCaseCerti1) {
		this.policeCaseCerti1 = policeCaseCerti1;
	}
	public byte[] getPossessionBusinessDoc1() {
		return possessionBusinessDoc1;
	}
	public void setPossessionBusinessDoc1(byte[] possessionBusinessDoc1) {
		this.possessionBusinessDoc1 = possessionBusinessDoc1;
	}
	public byte[] getHigherEduCerti1() {
		return higherEduCerti1;
	}
	public void setHigherEduCerti1(byte[] higherEduCerti1) {
		this.higherEduCerti1 = higherEduCerti1;
	}
	public byte[] getAffidavitHardware1() {
		return affidavitHardware1;
	}
	public void setAffidavitHardware1(byte[] affidavitHardware1) {
		this.affidavitHardware1 = affidavitHardware1;
	}
	public Object getLangKnown() {
		return langKnown;
	}
	public void setLangKnown(Object langKnown) {
		this.langKnown = langKnown;
	}
	public Object getDrAddress() {
		return drAddress;
	}
	public void setDrAddress(Object drAddress) {
		this.drAddress = drAddress;
	}
	public Object getDrPhNo() {
		return drPhNo;
	}
	public void setDrPhNo(Object drPhNo) {
		this.drPhNo = drPhNo;
	}
	public ArrayList getDroDetails() {
		return droDetails;
	}
	public void setDroDetails(ArrayList droDetails) {
		this.droDetails = droDetails;
	}
	public Object getRadioSelect1() {
		return radioSelect1;
	}
	public void setRadioSelect1(Object radioSelect1) {
		this.radioSelect1 = radioSelect1;
	}
	public Object getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Object officeId) {
		this.officeId = officeId;
	}
	public Object getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(Object requestNo) {
		this.requestNo = requestNo;
	}
	public Object getDate() {
		return date;
	}
	public void setDate(Object date) {
		this.date = date;
	}
	public Object getApplctnStatus() {
		return applctnStatus;
	}
	public void setApplctnStatus(Object applctnStatus) {
		this.applctnStatus = applctnStatus;
	}
	public ArrayList getAppList() {
		return appList;
	}
	public void setAppList(ArrayList appList) {
		this.appList = appList;
	}
	public Object getFromCreatedDate() {
		return fromCreatedDate;
	}
	public void setFromCreatedDate(Object fromCreatedDate) {
		this.fromCreatedDate = fromCreatedDate;
	}
	public Object getToCreatedDate() {
		return toCreatedDate;
	}
	public void setToCreatedDate(Object toCreatedDate) {
		this.toCreatedDate = toCreatedDate;
	}
	public Object getApplicantStatus() {
		return applicantStatus;
	}
	public void setApplicantStatus(Object applicantStatus) {
		this.applicantStatus = applicantStatus;
	}
	public String getDurationTo() {
		return durationTo;
	}
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}
	public String getDurationFrom() {
		return durationFrom;
	}
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStatusValue() {
		return statusValue;
	}
	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}
	public ArrayList getStatusList() {
		return statusList;
	}
	public void setStatusList(ArrayList statusList) {
		this.statusList = statusList;
	}
	public ArrayList getSpDetailsAll() {
		return spDetailsAll;
	}
	public void setSpDetailsAll(ArrayList spDetailsAll) {
		this.spDetailsAll = spDetailsAll;
	}
	public ArrayList getSpAppDetails() {
		return spAppDetails;
	}
	public void setSpAppDetails(ArrayList spAppDetails) {
		this.spAppDetails = spAppDetails;
	}
	public ArrayList getSpAppDetailsAll() {
		return spAppDetailsAll;
	}
	public void setSpAppDetailsAll(ArrayList spAppDetailsAll) {
		this.spAppDetailsAll = spAppDetailsAll;
	}
	public ArrayList getSpAppDetailsBank() {
		return spAppDetailsBank;
	}
	public void setSpAppDetailsBank(ArrayList spAppDetailsBank) {
		this.spAppDetailsBank = spAppDetailsBank;
	}
	public ArrayList getSpDocDetails() {
		return spDocDetails;
	}
	public void setSpDocDetails(ArrayList spDocDetails) {
		this.spDocDetails = spDocDetails;
	}
	public int getIsReqApprovd() {
		return isReqApprovd;
	}
	public void setIsReqApprovd(int isReqApprovd) {
		this.isReqApprovd = isReqApprovd;
	}
	public String getAffidavitCriminalPath() {
		return affidavitCriminalPath;
	}
	public void setAffidavitCriminalPath(String affidavitCriminalPath) {
		this.affidavitCriminalPath = affidavitCriminalPath;
	}
	public String getCharacterCertiGazPath() {
		return characterCertiGazPath;
	}
	public void setCharacterCertiGazPath(String characterCertiGazPath) {
		this.characterCertiGazPath = characterCertiGazPath;
	}
	public String getDobCertiPath() {
		return dobCertiPath;
	}
	public void setDobCertiPath(String dobCertiPath) {
		this.dobCertiPath = dobCertiPath;
	}
	public String getRentDetailPath() {
		return rentDetailPath;
	}
	public void setRentDetailPath(String rentDetailPath) {
		this.rentDetailPath = rentDetailPath;
	}
	public String getHigherSecCertiPath() {
		return higherSecCertiPath;
	}
	public void setHigherSecCertiPath(String higherSecCertiPath) {
		this.higherSecCertiPath = higherSecCertiPath;
	}
	public String getPhotoImgPath() {
		return photoImgPath;
	}
	public void setPhotoImgPath(String photoImgPath) {
		this.photoImgPath = photoImgPath;
	}
	public String getCharacterCertiPath() {
		return characterCertiPath;
	}
	public void setCharacterCertiPath(String characterCertiPath) {
		this.characterCertiPath = characterCertiPath;
	}
	public String getAgeProofPath() {
		return ageProofPath;
	}
	public void setAgeProofPath(String ageProofPath) {
		this.ageProofPath = ageProofPath;
	}
	public String getSolvencyCertiPath() {
		return solvencyCertiPath;
	}
	public void setSolvencyCertiPath(String solvencyCertiPath) {
		this.solvencyCertiPath = solvencyCertiPath;
	}
	public String getPoliceCaseCertiPath() {
		return policeCaseCertiPath;
	}
	public void setPoliceCaseCertiPath(String policeCaseCertiPath) {
		this.policeCaseCertiPath = policeCaseCertiPath;
	}
	public String getPossessionBusinessDocPath() {
		return possessionBusinessDocPath;
	}
	public void setPossessionBusinessDocPath(String possessionBusinessDocPath) {
		this.possessionBusinessDocPath = possessionBusinessDocPath;
	}
	public String getHigherEduCertiPath() {
		return higherEduCertiPath;
	}
	public void setHigherEduCertiPath(String higherEduCertiPath) {
		this.higherEduCertiPath = higherEduCertiPath;
	}
	public String getAffidavitHardwarePath() {
		return affidavitHardwarePath;
	}
	public void setAffidavitHardwarePath(String affidavitHardwarePath) {
		this.affidavitHardwarePath = affidavitHardwarePath;
	}
	public String getLicenseFromDate() {
		return licenseFromDate;
	}
	public void setLicenseFromDate(String licenseFromDate) {
		this.licenseFromDate = licenseFromDate;
	}
	public String getLicenseToDate() {
		return licenseToDate;
	}
	public void setLicenseToDate(String licenseToDate) {
		this.licenseToDate = licenseToDate;
	}
	public String getFees() {
		return fees;
	}
	public void setFees(String fees) {
		this.fees = fees;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getIsDRApproved() {
		return isDRApproved;
	}
	public void setIsDRApproved(int isDRApproved) {
		this.isDRApproved = isDRApproved;
	}
	public String getRmrksCallForPV() {
		return rmrksCallForPV;
	}
	public void setRmrksCallForPV(String rmrksCallForPV) {
		this.rmrksCallForPV = rmrksCallForPV;
	}
	public String getRmrksCallForAddInfo() {
		return rmrksCallForAddInfo;
	}
	public void setRmrksCallForAddInfo(String rmrksCallForAddInfo) {
		this.rmrksCallForAddInfo = rmrksCallForAddInfo;
	}
	public String getRmrksForRejection() {
		return rmrksForRejection;
	}
	public void setRmrksForRejection(String rmrksForRejection) {
		this.rmrksForRejection = rmrksForRejection;
	}
	public int getIsCallForPV() {
		return isCallForPV;
	}
	public void setIsCallForPV(int isCallForPV) {
		this.isCallForPV = isCallForPV;
	}
	public int getIsCallForAddInfo() {
		return isCallForAddInfo;
	}
	public void setIsCallForAddInfo(int isCallForAddInfo) {
		this.isCallForAddInfo = isCallForAddInfo;
	}
	public int getIsReject() {
		return isReject;
	}
	public void setIsReject(int isReject) {
		this.isReject = isReject;
	}
	public int getIsDIG() {
		return isDIG;
	}
	public void setIsDIG(int isDIG) {
		this.isDIG = isDIG;
	}
	public int getIsReactivateByDR() {
		return isReactivateByDR;
	}
	public void setIsReactivateByDR(int isReactivateByDR) {
		this.isReactivateByDR = isReactivateByDR;
	}
	public String getSecondFileSP() {
		return secondFileSP;
	}
	public void setSecondFileSP(String secondFileSP) {
		this.secondFileSP = secondFileSP;
	}
	public String getThirdFileSP() {
		return thirdFileSP;
	}
	public void setThirdFileSP(String thirdFileSP) {
		this.thirdFileSP = thirdFileSP;
	}
	public String getFourthFileSP() {
		return fourthFileSP;
	}
	public void setFourthFileSP(String fourthFileSP) {
		this.fourthFileSP = fourthFileSP;
	}
	public String getFifthFileSP() {
		return fifthFileSP;
	}
	public void setFifthFileSP(String fifthFileSP) {
		this.fifthFileSP = fifthFileSP;
	}
	public String getSixthFileSP() {
		return sixthFileSP;
	}
	public void setSixthFileSP(String sixthFileSP) {
		this.sixthFileSP = sixthFileSP;
	}
	public String getSeventhFileSP() {
		return seventhFileSP;
	}
	public void setSeventhFileSP(String seventhFileSP) {
		this.seventhFileSP = seventhFileSP;
	}
	public String getEighthFileSP() {
		return eighthFileSP;
	}
	public void setEighthFileSP(String eighthFileSP) {
		this.eighthFileSP = eighthFileSP;
	}
	public String getNinthFileSP() {
		return ninthFileSP;
	}
	public void setNinthFileSP(String ninthFileSP) {
		this.ninthFileSP = ninthFileSP;
	}
	public String getTenthFileSP() {
		return tenthFileSP;
	}
	public void setTenthFileSP(String tenthFileSP) {
		this.tenthFileSP = tenthFileSP;
	}
	public String getEleventhFileSP() {
		return eleventhFileSP;
	}
	public void setEleventhFileSP(String eleventhFileSP) {
		this.eleventhFileSP = eleventhFileSP;
	}
	public String getTwelfthFileSP() {
		return twelfthFileSP;
	}
	public void setTwelfthFileSP(String twelfthFileSP) {
		this.twelfthFileSP = twelfthFileSP;
	}
	public String getThirteenthFileSP() {
		return thirteenthFileSP;
	}
	public void setThirteenthFileSP(String thirteenthFileSP) {
		this.thirteenthFileSP = thirteenthFileSP;
	}
	public String getFirstFileSP() {
		return firstFileSP;
	}
	public void setFirstFileSP(String firstFileSP) {
		this.firstFileSP = firstFileSP;
	}
	public String getSecondFileDR() {
		return secondFileDR;
	}
	public void setSecondFileDR(String secondFileDR) {
		this.secondFileDR = secondFileDR;
	}
	public String getThirdFileDR() {
		return thirdFileDR;
	}
	public void setThirdFileDR(String thirdFileDR) {
		this.thirdFileDR = thirdFileDR;
	}
	public String getFourthFileDR() {
		return fourthFileDR;
	}
	public void setFourthFileDR(String fourthFileDR) {
		this.fourthFileDR = fourthFileDR;
	}
	public String getFifthFileDR() {
		return fifthFileDR;
	}
	public void setFifthFileDR(String fifthFileDR) {
		this.fifthFileDR = fifthFileDR;
	}
	public String getSixthFileDR() {
		return sixthFileDR;
	}
	public void setSixthFileDR(String sixthFileDR) {
		this.sixthFileDR = sixthFileDR;
	}
	public String getSeventhFileDR() {
		return seventhFileDR;
	}
	public void setSeventhFileDR(String seventhFileDR) {
		this.seventhFileDR = seventhFileDR;
	}
	public String getEighthFileDR() {
		return eighthFileDR;
	}
	public void setEighthFileDR(String eighthFileDR) {
		this.eighthFileDR = eighthFileDR;
	}
	public String getNinthFileDR() {
		return ninthFileDR;
	}
	public void setNinthFileDR(String ninthFileDR) {
		this.ninthFileDR = ninthFileDR;
	}
	public String getTenthFileDR() {
		return tenthFileDR;
	}
	public void setTenthFileDR(String tenthFileDR) {
		this.tenthFileDR = tenthFileDR;
	}
	public String getEleventhFileDR() {
		return eleventhFileDR;
	}
	public void setEleventhFileDR(String eleventhFileDR) {
		this.eleventhFileDR = eleventhFileDR;
	}
	public String getTwelfthFileDR() {
		return twelfthFileDR;
	}
	public void setTwelfthFileDR(String twelfthFileDR) {
		this.twelfthFileDR = twelfthFileDR;
	}
	public String getThirteenthFileDR() {
		return thirteenthFileDR;
	}
	public void setThirteenthFileDR(String thirteenthFileDR) {
		this.thirteenthFileDR = thirteenthFileDR;
	}
	public String getFirstFileDR() {
		return firstFileDR;
	}
	public void setFirstFileDR(String firstFileDR) {
		this.firstFileDR = firstFileDR;
	}
	public int getIsAlreadyFiled() {
		return isAlreadyFiled;
	}
	public void setIsAlreadyFiled(int isAlreadyFiled) {
		this.isAlreadyFiled = isAlreadyFiled;
	}
	public Object getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Object paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Object getBalAmount() {
		return balAmount;
	}
	public void setBalAmount(Object balAmount) {
		this.balAmount = balAmount;
	}
	public Object getLastTxnDate() {
		return lastTxnDate;
	}
	public void setLastTxnDate(Object lastTxnDate) {
		this.lastTxnDate = lastTxnDate;
	}
	public Object getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(Object payableAmount) {
		this.payableAmount = payableAmount;
	}
	public Object getSpPaymentId() {
		return spPaymentId;
	}
	public void setSpPaymentId(Object spPaymentId) {
		this.spPaymentId = spPaymentId;
	}
	public Object getPaymentFlag() {
		return paymentFlag;
	}
	public void setPaymentFlag(Object paymentFlag) {
		this.paymentFlag = paymentFlag;
	}
	public Object getDrOfcId() {
		return drOfcId;
	}
	public void setDrOfcId(Object drOfcId) {
		this.drOfcId = drOfcId;
	}
	public Object getDrOfficeName() {
		return drOfficeName;
	}
	public void setDrOfficeName(Object drOfficeName) {
		this.drOfficeName = drOfficeName;
	}
	public Object getDrDistrict() {
		return drDistrict;
	}
	public void setDrDistrict(Object drDistrict) {
		this.drDistrict = drDistrict;
	}
	public Object getDrDistrictName() {
		return drDistrictName;
	}
	public void setDrDistrictName(Object drDistrictName) {
		this.drDistrictName = drDistrictName;
	}
	public double getAmountRemaining() {
		return amountRemaining;
	}
	public void setAmountRemaining(double amountRemaining) {
		this.amountRemaining = amountRemaining;
	}
	public int getIsPaymentComplete() {
		return isPaymentComplete;
	}
	public void setIsPaymentComplete(int isPaymentComplete) {
		this.isPaymentComplete = isPaymentComplete;
	}
	public int getIsLicenseFeePaid() {
		return isLicenseFeePaid;
	}
	public void setIsLicenseFeePaid(int isLicenseFeePaid) {
		this.isLicenseFeePaid = isLicenseFeePaid;
	}
	public int getIsCallForPVRmrks() {
		return isCallForPVRmrks;
	}
	public void setIsCallForPVRmrks(int isCallForPVRmrks) {
		this.isCallForPVRmrks = isCallForPVRmrks;
	}
	public int getIsRjectRmrks() {
		return isRjectRmrks;
	}
	public void setIsRjectRmrks(int isRjectRmrks) {
		this.isRjectRmrks = isRjectRmrks;
	}
	public int getIsReqApprovdRmrks() {
		return isReqApprovdRmrks;
	}
	public void setIsReqApprovdRmrks(int isReqApprovdRmrks) {
		this.isReqApprovdRmrks = isReqApprovdRmrks;
	}
	public int getIsCallForAddInfoRmrks() {
		return isCallForAddInfoRmrks;
	}
	public void setIsCallForAddInfoRmrks(int isCallForAddInfoRmrks) {
		this.isCallForAddInfoRmrks = isCallForAddInfoRmrks;
	}
	public Object getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(Object licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public int getIsLicenseNumberPresent() {
		return isLicenseNumberPresent;
	}
	public void setIsLicenseNumberPresent(int isLicenseNumberPresent) {
		this.isLicenseNumberPresent = isLicenseNumberPresent;
	}
	public Object getYear() {
		return year;
	}
	public void setYear(Object year) {
		this.year = year;
	}
	public Object getMonth() {
		return month;
	}
	public void setMonth(Object month) {
		this.month = month;
	}
	public Object getDays() {
		return days;
	}
	public void setDays(Object days) {
		this.days = days;
	}
	public int getIsLicenseNotIssued() {
		return isLicenseNotIssued;
	}
	public void setIsLicenseNotIssued(int isLicenseNotIssued) {
		this.isLicenseNotIssued = isLicenseNotIssued;
	}
	public Object getCriteriaPopUp() {
		return criteriaPopUp;
	}
	public void setCriteriaPopUp(Object criteriaPopUp) {
		this.criteriaPopUp = criteriaPopUp;
	}
	public String getFirstFileRenewal() {
		return firstFileRenewal;
	}
	public void setFirstFileRenewal(String firstFileRenewal) {
		this.firstFileRenewal = firstFileRenewal;
	}
	public String getSecondFileRenewal() {
		return secondFileRenewal;
	}
	public void setSecondFileRenewal(String secondFileRenewal) {
		this.secondFileRenewal = secondFileRenewal;
	}
	public String getThirdFileRenewal() {
		return thirdFileRenewal;
	}
	public void setThirdFileRenewal(String thirdFileRenewal) {
		this.thirdFileRenewal = thirdFileRenewal;
	}
	public String getFourthFileRenewal() {
		return fourthFileRenewal;
	}
	public void setFourthFileRenewal(String fourthFileRenewal) {
		this.fourthFileRenewal = fourthFileRenewal;
	}
	public String getFifthFileRenewal() {
		return fifthFileRenewal;
	}
	public void setFifthFileRenewal(String fifthFileRenewal) {
		this.fifthFileRenewal = fifthFileRenewal;
	}
	public String getSixthFileRenewal() {
		return sixthFileRenewal;
	}
	public void setSixthFileRenewal(String sixthFileRenewal) {
		this.sixthFileRenewal = sixthFileRenewal;
	}
	public String getSeventhFileRenewal() {
		return seventhFileRenewal;
	}
	public void setSeventhFileRenewal(String seventhFileRenewal) {
		this.seventhFileRenewal = seventhFileRenewal;
	}
	public String getEighthFileRenewal() {
		return eighthFileRenewal;
	}
	public void setEighthFileRenewal(String eighthFileRenewal) {
		this.eighthFileRenewal = eighthFileRenewal;
	}
	public String getNinthFileRenewal() {
		return ninthFileRenewal;
	}
	public void setNinthFileRenewal(String ninthFileRenewal) {
		this.ninthFileRenewal = ninthFileRenewal;
	}
	public String getTenthFileRenewal() {
		return tenthFileRenewal;
	}
	public void setTenthFileRenewal(String tenthFileRenewal) {
		this.tenthFileRenewal = tenthFileRenewal;
	}
	public String getEleventhFileRenewal() {
		return eleventhFileRenewal;
	}
	public void setEleventhFileRenewal(String eleventhFileRenewal) {
		this.eleventhFileRenewal = eleventhFileRenewal;
	}
	public String getTwelfthFileRenewal() {
		return twelfthFileRenewal;
	}
	public void setTwelfthFileRenewal(String twelfthFileRenewal) {
		this.twelfthFileRenewal = twelfthFileRenewal;
	}
	public String getThirteenthFileRenewal() {
		return thirteenthFileRenewal;
	}
	public void setThirteenthFileRenewal(String thirteenthFileRenewal) {
		this.thirteenthFileRenewal = thirteenthFileRenewal;
	}
	public Object getNewOrRenewalFlag() {
		return newOrRenewalFlag;
	}
	public void setNewOrRenewalFlag(Object newOrRenewalFlag) {
		this.newOrRenewalFlag = newOrRenewalFlag;
	}
	public int getPrintEnableDisable() {
		return printEnableDisable;
	}
	public void setPrintEnableDisable(int printEnableDisable) {
		this.printEnableDisable = printEnableDisable;
	}
	public Object getBalAmountCreditEstamp() {
		return balAmountCreditEstamp;
	}
	public void setBalAmountCreditEstamp(Object balAmountCreditEstamp) {
		this.balAmountCreditEstamp = balAmountCreditEstamp;
	}
	public Object getBalAmountCreditOthers() {
		return balAmountCreditOthers;
	}
	public void setBalAmountCreditOthers(Object balAmountCreditOthers) {
		this.balAmountCreditOthers = balAmountCreditOthers;
	}
	public Object getRadioBal() {
		return radioBal;
	}
	public void setRadioBal(Object radioBal) {
		this.radioBal = radioBal;
	}
	public Object getEstampBalanceCreditLimit() {
		return estampBalanceCreditLimit;
	}
	public void setEstampBalanceCreditLimit(Object estampBalanceCreditLimit) {
		this.estampBalanceCreditLimit = estampBalanceCreditLimit;
	}
	public Object getOthersBalanceCreditLimit() {
		return othersBalanceCreditLimit;
	}
	public void setOthersBalanceCreditLimit(Object othersBalanceCreditLimit) {
		this.othersBalanceCreditLimit = othersBalanceCreditLimit;
	}
	public Object getHdnBalAmountCreditOthers() {
		return hdnBalAmountCreditOthers;
	}
	public void setHdnBalAmountCreditOthers(Object hdnBalAmountCreditOthers) {
		this.hdnBalAmountCreditOthers = hdnBalAmountCreditOthers;
	}
	public Object getHdnBalAmountCreditEstamp() {
		return hdnBalAmountCreditEstamp;
	}
	public void setHdnBalAmountCreditEstamp(Object hdnBalAmountCreditEstamp) {
		this.hdnBalAmountCreditEstamp = hdnBalAmountCreditEstamp;
	}
	public Object getChkBoxCreditLimit() {
		return chkBoxCreditLimit;
	}
	public void setChkBoxCreditLimit(Object chkBoxCreditLimit) {
		this.chkBoxCreditLimit = chkBoxCreditLimit;
	}
	public String getSelectedValuesOfCreditLimit() {
		return selectedValuesOfCreditLimit;
	}
	public void setSelectedValuesOfCreditLimit(String selectedValuesOfCreditLimit) {
		this.selectedValuesOfCreditLimit = selectedValuesOfCreditLimit;
	}
	public Object getPreviousLicenseNoEstamp() {
		return previousLicenseNoEstamp;
	}
	public void setPreviousLicenseNoEstamp(Object previousLicenseNoEstamp) {
		this.previousLicenseNoEstamp = previousLicenseNoEstamp;
	}
	public Object getPreviousLicenseNoOthers() {
		return previousLicenseNoOthers;
	}
	public void setPreviousLicenseNoOthers(Object previousLicenseNoOthers) {
		this.previousLicenseNoOthers = previousLicenseNoOthers;
	}
	public int getIsFeeNotPaid() {
		return isFeeNotPaid;
	}
	public void setIsFeeNotPaid(int isFeeNotPaid) {
		this.isFeeNotPaid = isFeeNotPaid;
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
	public String getForwardPath() {
		return forwardPath;
	}
	public void setForwardPath(String forwardPath) {
		this.forwardPath = forwardPath;
	}
	public String getForwardName() {
		return forwardName;
	}
	public void setForwardName(String forwardName) {
		this.forwardName = forwardName;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getIsSigned() {
		return isSigned;
	}
	public void setIsSigned(int isSigned) {
		this.isSigned = isSigned;
	}
	public String getSignatureName() {
		return signatureName;
	}
	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}
	public void setBalAmountCreditJudicialEstamp(
			Object balAmountCreditJudicialEstamp) {
		this.balAmountCreditJudicialEstamp = balAmountCreditJudicialEstamp;
	}
	public Object getBalAmountCreditJudicialEstamp() {
		return balAmountCreditJudicialEstamp;
	}
	public void setJudicialEstampBalanceCreditLimit(
			Object judicialEstampBalanceCreditLimit) {
		this.judicialEstampBalanceCreditLimit = judicialEstampBalanceCreditLimit;
	}
	public Object getJudicialEstampBalanceCreditLimit() {
		return judicialEstampBalanceCreditLimit;
	}
	public void setBalAmountCreditEstampJudicial(
			Object balAmountCreditEstampJudicial) {
		this.balAmountCreditEstampJudicial = balAmountCreditEstampJudicial;
	}
	public Object getBalAmountCreditEstampJudicial() {
		return balAmountCreditEstampJudicial;
	}
	public void setHdnBalAmountCreditEstampJudicial(
			Object hdnBalAmountCreditEstampJudicial) {
		this.hdnBalAmountCreditEstampJudicial = hdnBalAmountCreditEstampJudicial;
	}
	public Object getHdnBalAmountCreditEstampJudicial() {
		return hdnBalAmountCreditEstampJudicial;
	}
	public void setPreviousLicenseNoEstampJudicial(
			Object previousLicenseNoEstampJudicial) {
		this.previousLicenseNoEstampJudicial = previousLicenseNoEstampJudicial;
	}
	public Object getPreviousLicenseNoEstampJudicial() {
		return previousLicenseNoEstampJudicial;
	}	  
}
