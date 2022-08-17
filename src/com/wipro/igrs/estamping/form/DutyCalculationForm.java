
package com.wipro.igrs.estamping.form;


import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.estamping.dto.DashBoardDTO;
import com.wipro.igrs.propertyvaluation.dto.DutyCalculationDTO;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.propertyvaluation.dto.InstrumentsDTO;


/**
 * @since 14 jan 2008
 * File Name: DutyCalculationForm.java
 * @author Madan Mohan
 * @version 1.0
 * Created on 14 jan 2008
 *
 */
public class DutyCalculationForm extends ActionForm {

	/**
	 * @serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author Madan Mohan
	 */
	//Court Details
	private String courtName;
	private String courtDocTypeName;
	private String courtType;
	private String courtDistrict;
	
	private  String constantLabelValue  ;
	private  String moduleName  ;
	private String hdnAmount;
	
	//ADDED BY LAVI FOR DEED DURATION
	private String deedDuration;
	private String owmFile;
	private String owmFlag;
	
	public String getOwmFile() {
		return owmFile;
	}

	public void setOwmFile(String owmFile) {
		this.owmFile = owmFile;
	}

	public String getOwmFlag() {
		return owmFlag;
	}

	public void setOwmFlag(String owmFlag) {
		this.owmFlag = owmFlag;
	}


	//added By Mohit Soni
	
	 private String allowPrint ="N";
	   
	   private String printFlag ="N";
	   
	   private String otp;
	   
	   private String printCheck ;
	
	   
	public String getPrintCheck() {
		return printCheck;
	}

	public void setPrintCheck(String printCheck) {
		this.printCheck = printCheck;
	}

	public String getAllowPrint() {
		return allowPrint;
	}

	public void setAllowPrint(String allowPrint) {
		this.allowPrint = allowPrint;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}


	//applicant related
	private String appType;
	private String appTypeName;
	private String appFirsName;
	private String appMiddleName;
	private String appLastName;
	private String appGender;
	private String appGenderDisp;
	private String appAge;
	private String appDay;
	private String appMonth;
	private String appYear;
	private String appCountry;
	private String appCountryName;
	private String appState;
	private String appStateName;
	private String appDistrict;
	private String appDistrictName;
	private String appAddress;
	private String appPostalCode;
	private String appFatherName;
	private String appMotherName;
	private String appSpouseName;
	private String appNationality;
	private String appEmailID;
	private String appPhno;
	private String appMobno;
	private String appPhotoIdName;
	private String appPhotoId;
	private String appPhotoIdno;
	private String appBankName;
	private String appBankAddress;
	private String appPersons;
	private String appOrgName;
	private String appAuthPersonName;
	private String appAuthFirstName;
	private String appAuthMiddleName;
	private String appAuthLastName;
	private String signedPath;
	
	
	//Party related
	private String partyType;
	private String partyTypeName;
	private String partyFirsName;
	private String partyMiddleName;
	private String partyLastName;
	private String partyGender;
	private String partyGenderDisp;
	private String partyAge;
	private String partyDay;
	private String partyMonth;
	private String partyYear;
	private String partyCountry;
	private String partyCountryName;
	private String partyState;
	private String partyStateName;
	private String partyDistrict;
	private String partyDistrictName;
	private String partyAddress;
	private String partyPostalCode;
	private String partyFatherName;
	private String partyMotherName;
	private String partySpouseName;
	private String partyNationality;
	private String partyEmailID;
	private String partyPhno;
	private String partyMobno;
	private String partyPhotoIdName;
	private String partyPhotoId;
	private String partyPhotoIdno;
	private String partyBankName;
	private String partyBankAddress;
	private String partyPersons;
	private String partyOrgName;
	private String partyAuthPersonName;
	private String partyAuthFirstName;
	private String partyAuthMiddleName;
	private String partyAuthLastName;
	
	
	
	private ArrayList photoIdList = new ArrayList();
	private ArrayList countryList = new ArrayList();
	private ArrayList stateList = new ArrayList();
	private ArrayList districtList = new ArrayList();
	private ArrayList appellate = new ArrayList();
	
	
	
	private String estampPurpose;
	private String ecode;
	private String estampType;
	private String estampValidity;
    ArrayList photoLst = new ArrayList();
    private ArrayList dataList3 = new ArrayList();
	FormFile filePhoto2=null;
	String doc ;
	private String docname;
	private String uid;
	private String mainTxnId;
	private String uniqKey;
	private String currentYear;
	private String currentDate;
	private String userName;
	private String officeName;
	private String issuedPlace;
	private int errMsg=0;
	private int isModify=0;
	private int isAuthapp=0;
	private int isAuthparty=0;
	private int isPartial=0;
	private String appNamedsply;
	private String partyNamedsply;
	private int pay;
	private String docPath;
	private String rId;
	
	public String getRId() {
		return rId;
	}

	public void setRId(String id) {
		rId = id;
	}


	//added by lavi for scanner integration
	private String guidUpload;
	private String parentPathScan;
	private String fileNameScan;
	private int isInternalUser=0;
	private String docPathComplete;
	private String remarks;
	private String refundId;
	//end if variables added by lavi for scanner integration

  public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	//  private DutyCalculationDTO objDutyCalculationDTO = new DutyCalculationDTO();
    private DashBoardDTO objDashBoardDTO = new DashBoardDTO();
	
    
    //----added by satbir kumar----
    public ArrayList getDataList3() {
		return dataList3;
	}
	public void setDataList3(ArrayList dataList3) {
		this.dataList3 = dataList3;
	}
    private FormFile image;
	
    private byte[] image1;
	
	
	private DutyCalculationDTO dutyCalculationDTO =
		new DutyCalculationDTO();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList instrumentDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList exemptionDTOList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList selectedExemptionList = 
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private ArrayList dutycalculationDTOList =
		new ArrayList();
	/**
	 * @author Madan Mohan
	 */
	private InstrumentsDTO instDTO = 
		new InstrumentsDTO();
	/**
	 * @author Madan Mohan
	 */
	private ExemptionDTO exempDTO = 
		new ExemptionDTO();
	/**
	 * @author Madan Mohan
	 */
	private String baseValue;
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getSignedPath() {
		return signedPath;
	}

	public void setSignedPath(String signedPath) {
		this.signedPath = signedPath;
	}

	public int getIsPartial() {
		return isPartial;
	}

	public void setIsPartial(int isPartial) {
		this.isPartial = isPartial;
	}

	public String getEstampValidity() {
		return estampValidity;
	}

	public void setEstampValidity(String estampValidity) {
		this.estampValidity = estampValidity;
	}

	public String getEstampType() {
		return estampType;
	}

	public void setEstampType(String estampType) {
		this.estampType = estampType;
	}

	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getAppNamedsply() {
		return appNamedsply;
	}

	public void setAppNamedsply(String appNamedsply) {
		this.appNamedsply = appNamedsply;
	}

	public String getPartyNamedsply() {
		return partyNamedsply;
	}

	public void setPartyNamedsply(String partyNamedsply) {
		this.partyNamedsply = partyNamedsply;
	}

	public int getIsAuthparty() {
		return isAuthparty;
	}

	public void setIsAuthparty(int isAuthparty) {
		this.isAuthparty = isAuthparty;
	}

	public int getIsAuthapp() {
		return isAuthapp;
	}

	public void setIsAuthapp(int isAuthapp) {
		this.isAuthapp = isAuthapp;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getIssuedPlace() {
		return issuedPlace;
	}

	public void setIssuedPlace(String issuedPlace) {
		this.issuedPlace = issuedPlace;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getUniqKey() {
		return uniqKey;
	}

	public void setUniqKey(String uniqKey) {
		this.uniqKey = uniqKey;
	}

	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public int getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(int errMsg) {
		this.errMsg = errMsg;
	}

	public int getIsModify() {
		return isModify;
	}

	public void setIsModify(int isModify) {
		this.isModify = isModify;
	}

	public String getMainTxnId() {
		return mainTxnId;
	}

	public void setMainTxnId(String mainTxnId) {
		this.mainTxnId = mainTxnId;
	}

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAppDay() {
		return appDay;
	}

	public void setAppDay(String appDay) {
		this.appDay = appDay;
	}

	public String getAppMonth() {
		return appMonth;
	}

	public void setAppMonth(String appMonth) {
		this.appMonth = appMonth;
	}

	public String getAppYear() {
		return appYear;
	}

	public void setAppYear(String appYear) {
		this.appYear = appYear;
	}

	public String getPartyDay() {
		return partyDay;
	}

	public void setPartyDay(String partyDay) {
		this.partyDay = partyDay;
	}

	public String getPartyMonth() {
		return partyMonth;
	}

	public void setPartyMonth(String partyMonth) {
		this.partyMonth = partyMonth;
	}

	public String getPartyYear() {
		return partyYear;
	}

	public void setPartyYear(String partyYear) {
		this.partyYear = partyYear;
	}

	public ArrayList getPhotoLst() {
		return photoLst;
	}

	public void setPhotoLst(ArrayList photoLst) {
		this.photoLst = photoLst;
	}

	public FormFile getFilePhoto2() {
		return filePhoto2;
	}

	public void setFilePhoto2(FormFile filePhoto2) {
		this.filePhoto2 = filePhoto2;
	}

	public String getAppPhotoId() {
		return appPhotoId;
	}

	public void setAppPhotoId(String appPhotoId) {
		this.appPhotoId = appPhotoId;
	}

	public String getPartyPhotoId() {
		return partyPhotoId;
	}

	public void setPartyPhotoId(String partyPhotoId) {
		this.partyPhotoId = partyPhotoId;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getAppTypeName() {
		return appTypeName;
	}

	public void setAppTypeName(String appTypeName) {
		this.appTypeName = appTypeName;
	}

	public String getPartyTypeName() {
		return partyTypeName;
	}

	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}

	public String getAppFirsName() {
		return appFirsName;
	}

	public void setAppFirsName(String appFirsName) {
		this.appFirsName = appFirsName;
	}

	public String getAppMiddleName() {
		return appMiddleName;
	}

	public void setAppMiddleName(String appMiddleName) {
		this.appMiddleName = appMiddleName;
	}

	public String getAppLastName() {
		return appLastName;
	}

	public void setAppLastName(String appLastName) {
		this.appLastName = appLastName;
	}

	public String getAppGender() {
		return appGender;
	}

	public void setAppGender(String appGender) {
		this.appGender = appGender;
	}

	public String getAppAge() {
		return appAge;
	}

	public void setAppAge(String appAge) {
		this.appAge = appAge;
	}

	public String getAppCountry() {
		return appCountry;
	}

	public void setAppCountry(String appCountry) {
		this.appCountry = appCountry;
	}

	public String getAppState() {
		return appState;
	}

	public void setAppState(String appState) {
		this.appState = appState;
	}

	public String getAppDistrict() {
		return appDistrict;
	}

	public void setAppDistrict(String appDistrict) {
		this.appDistrict = appDistrict;
	}

	public String getAppCountryName() {
		return appCountryName;
	}

	public void setAppCountryName(String appCountryName) {
		this.appCountryName = appCountryName;
	}

	public String getAppStateName() {
		return appStateName;
	}

	public void setAppStateName(String appStateName) {
		this.appStateName = appStateName;
	}

	public String getAppDistrictName() {
		return appDistrictName;
	}

	public void setAppDistrictName(String appDistrictName) {
		this.appDistrictName = appDistrictName;
	}

	public String getPartyCountryName() {
		return partyCountryName;
	}

	public void setPartyCountryName(String partyCountryName) {
		this.partyCountryName = partyCountryName;
	}

	public String getPartyStateName() {
		return partyStateName;
	}

	public void setPartyStateName(String partyStateName) {
		this.partyStateName = partyStateName;
	}

	public String getPartyDistrictName() {
		return partyDistrictName;
	}

	public void setPartyDistrictName(String partyDistrictName) {
		this.partyDistrictName = partyDistrictName;
	}

	public String getAppAddress() {
		return appAddress;
	}

	public void setAppAddress(String appAddress) {
		this.appAddress = appAddress;
	}

	public String getAppPostalCode() {
		return appPostalCode;
	}

	public void setAppPostalCode(String appPostalCode) {
		this.appPostalCode = appPostalCode;
	}

	public String getAppFatherName() {
		return appFatherName;
	}

	public void setAppFatherName(String appFatherName) {
		this.appFatherName = appFatherName;
	}

	public String getAppMotherName() {
		return appMotherName;
	}

	public void setAppMotherName(String appMotherName) {
		this.appMotherName = appMotherName;
	}

	public String getAppSpouseName() {
		return appSpouseName;
	}

	public void setAppSpouseName(String appSpouseName) {
		this.appSpouseName = appSpouseName;
	}

	public String getAppNationality() {
		return appNationality;
	}

	public void setAppNationality(String appNationality) {
		this.appNationality = appNationality;
	}

	public String getAppEmailID() {
		return appEmailID;
	}

	public void setAppEmailID(String appEmailID) {
		this.appEmailID = appEmailID;
	}

	public String getAppPhno() {
		return appPhno;
	}

	public void setAppPhno(String appPhno) {
		this.appPhno = appPhno;
	}

	public String getAppMobno() {
		return appMobno;
	}

	public void setAppMobno(String appMobno) {
		this.appMobno = appMobno;
	}

	public String getAppPhotoIdName() {
		return appPhotoIdName;
	}

	public void setAppPhotoIdName(String appPhotoIdName) {
		this.appPhotoIdName = appPhotoIdName;
	}

	public String getAppPhotoIdno() {
		return appPhotoIdno;
	}

	public void setAppPhotoIdno(String appPhotoIdno) {
		this.appPhotoIdno = appPhotoIdno;
	}

	public String getAppBankName() {
		return appBankName;
	}

	public void setAppBankName(String appBankName) {
		this.appBankName = appBankName;
	}

	public String getAppBankAddress() {
		return appBankAddress;
	}

	public void setAppBankAddress(String appBankAddress) {
		this.appBankAddress = appBankAddress;
	}

	public String getAppPersons() {
		return appPersons;
	}

	public void setAppPersons(String appPersons) {
		this.appPersons = appPersons;
	}

	public String getAppOrgName() {
		return appOrgName;
	}

	public void setAppOrgName(String appOrgName) {
		this.appOrgName = appOrgName;
	}

	public String getAppAuthPersonName() {
		return appAuthPersonName;
	}

	public void setAppAuthPersonName(String appAuthPersonName) {
		this.appAuthPersonName = appAuthPersonName;
	}

	
	public String getAppAuthFirstName() {
		return appAuthFirstName;
	}

	public void setAppAuthFirstName(String appAuthFirstName) {
		this.appAuthFirstName = appAuthFirstName;
	}

	public String getAppAuthMiddleName() {
		return appAuthMiddleName;
	}

	public void setAppAuthMiddleName(String appAuthMiddleName) {
		this.appAuthMiddleName = appAuthMiddleName;
	}

	public String getAppAuthLastName() {
		return appAuthLastName;
	}

	public void setAppAuthLastName(String appAuthLastName) {
		this.appAuthLastName = appAuthLastName;
	}

	public String getPartyAuthFirstName() {
		return partyAuthFirstName;
	}

	public void setPartyAuthFirstName(String partyAuthFirstName) {
		this.partyAuthFirstName = partyAuthFirstName;
	}

	public String getPartyAuthMiddleName() {
		return partyAuthMiddleName;
	}

	public void setPartyAuthMiddleName(String partyAuthMiddleName) {
		this.partyAuthMiddleName = partyAuthMiddleName;
	}

	public String getPartyAuthLastName() {
		return partyAuthLastName;
	}

	public void setPartyAuthLastName(String partyAuthLastName) {
		this.partyAuthLastName = partyAuthLastName;
	}

	public String getPartyType() {
		return partyType;
	}

	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

	public String getPartyFirsName() {
		return partyFirsName;
	}

	public void setPartyFirsName(String partyFirsName) {
		this.partyFirsName = partyFirsName;
	}

	public String getPartyMiddleName() {
		return partyMiddleName;
	}

	public void setPartyMiddleName(String partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}

	public String getPartyLastName() {
		return partyLastName;
	}

	public void setPartyLastName(String partyLastName) {
		this.partyLastName = partyLastName;
	}

	public String getPartyGender() {
		return partyGender;
	}

	public void setPartyGender(String partyGender) {
		this.partyGender = partyGender;
	}

	public String getPartyAge() {
		return partyAge;
	}

	public void setPartyAge(String partyAge) {
		this.partyAge = partyAge;
	}

	public String getPartyCountry() {
		return partyCountry;
	}

	public void setPartyCountry(String partyCountry) {
		this.partyCountry = partyCountry;
	}

	public String getPartyState() {
		return partyState;
	}

	public void setPartyState(String partyState) {
		this.partyState = partyState;
	}

	public String getPartyDistrict() {
		return partyDistrict;
	}

	public void setPartyDistrict(String partyDistrict) {
		this.partyDistrict = partyDistrict;
	}

	public String getPartyAddress() {
		return partyAddress;
	}

	public void setPartyAddress(String partyAddress) {
		this.partyAddress = partyAddress;
	}

	public String getPartyPostalCode() {
		return partyPostalCode;
	}

	public void setPartyPostalCode(String partyPostalCode) {
		this.partyPostalCode = partyPostalCode;
	}

	public String getPartyFatherName() {
		return partyFatherName;
	}

	public void setPartyFatherName(String partyFatherName) {
		this.partyFatherName = partyFatherName;
	}

	public String getPartyMotherName() {
		return partyMotherName;
	}

	public void setPartyMotherName(String partyMotherName) {
		this.partyMotherName = partyMotherName;
	}

	public String getPartySpouseName() {
		return partySpouseName;
	}

	public void setPartySpouseName(String partySpouseName) {
		this.partySpouseName = partySpouseName;
	}

	public String getPartyNationality() {
		return partyNationality;
	}

	public void setPartyNationality(String partyNationality) {
		this.partyNationality = partyNationality;
	}

	public String getPartyEmailID() {
		return partyEmailID;
	}

	public void setPartyEmailID(String partyEmailID) {
		this.partyEmailID = partyEmailID;
	}

	public String getPartyPhno() {
		return partyPhno;
	}

	public void setPartyPhno(String partyPhno) {
		this.partyPhno = partyPhno;
	}

	public String getPartyMobno() {
		return partyMobno;
	}

	public void setPartyMobno(String partyMobno) {
		this.partyMobno = partyMobno;
	}

	public String getPartyPhotoIdName() {
		return partyPhotoIdName;
	}

	public void setPartyPhotoIdName(String partyPhotoIdName) {
		this.partyPhotoIdName = partyPhotoIdName;
	}

	public String getPartyPhotoIdno() {
		return partyPhotoIdno;
	}

	public void setPartyPhotoIdno(String partyPhotoIdno) {
		this.partyPhotoIdno = partyPhotoIdno;
	}

	public String getPartyBankName() {
		return partyBankName;
	}

	public void setPartyBankName(String partyBankName) {
		this.partyBankName = partyBankName;
	}

	public String getPartyBankAddress() {
		return partyBankAddress;
	}

	public void setPartyBankAddress(String partyBankAddress) {
		this.partyBankAddress = partyBankAddress;
	}

	public String getPartyPersons() {
		return partyPersons;
	}

	public void setPartyPersons(String partyPersons) {
		this.partyPersons = partyPersons;
	}

	public String getPartyOrgName() {
		return partyOrgName;
	}

	public void setPartyOrgName(String partyOrgName) {
		this.partyOrgName = partyOrgName;
	}

	public String getPartyAuthPersonName() {
		return partyAuthPersonName;
	}

	public void setPartyAuthPersonName(String partyAuthPersonName) {
		this.partyAuthPersonName = partyAuthPersonName;
	}

	public ArrayList getPhotoIdList() {
		return photoIdList;
	}

	public void setPhotoIdList(ArrayList photoIdList) {
		this.photoIdList = photoIdList;
	}

	public ArrayList getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList countryList) {
		this.countryList = countryList;
	}

	public ArrayList getStateList() {
		return stateList;
	}

	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getAppellate() {
		return appellate;
	}

	public void setAppellate(ArrayList appellate) {
		this.appellate = appellate;
	}

	public String getEstampPurpose() {
		return estampPurpose;
	}

	public void setEstampPurpose(String estampPurpose) {
		this.estampPurpose = estampPurpose;
	}

	public String getBaseValue() {
		return baseValue;
	}
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public void setBaseValue(String baseValue) {
		this.baseValue = baseValue;
	}
	/**
	 * 
	 * @return InstrumentsDTO
	 */
	public InstrumentsDTO getInstDTO() {
		return instDTO;
	}
	/**
	 * 
	 * @param instDTO
	 */
	public void setInstDTO(InstrumentsDTO instDTO) {
		this.instDTO = instDTO;
	}
	/**
	 * 
	 * @return DutyCalculationDTO
	 */
	public DutyCalculationDTO getDutyCalculationDTO() {
		return dutyCalculationDTO;
	}
	/**
	 * 
	 * @param dutyCalculationDTO
	 */
	public void setDutyCalculationDTO(DutyCalculationDTO dutyCalculationDTO) {
		this.dutyCalculationDTO = dutyCalculationDTO;
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getInstrumentDTOList() {
		return instrumentDTOList;
	}
	/**
	 * 
	 * @param instrumentDTOList
	 */
	public void setInstrumentDTOList(
			ArrayList instrumentDTOList) {
		this.instrumentDTOList = instrumentDTOList;
	}
	/**
	 * 
	 * @return ArrayList
	 */ 
	public ArrayList getDutycalculationDTOList() {
		return dutycalculationDTOList;
	}
	/**
	 * 
	 * @param dutycalculationDTOList
	 */
	public void setDutycalculationDTOList(
			ArrayList dutycalculationDTOList) {
		this.dutycalculationDTOList = dutycalculationDTOList;
	}
	/**
	 * 
	 * @return ExemptionDTO
	 */
	public ExemptionDTO getExempDTO() {
		return exempDTO;
	}
	/**
	 * 
	 * @param exempDTO
	 */
	public void setExempDTO(ExemptionDTO exempDTO) {
		this.exempDTO = exempDTO;
	}
	
	public String getHdnAmount() {
		return hdnAmount;
	}
	public void setHdnAmount(String hdnAmount) {
		this.hdnAmount = hdnAmount;
	}
	public String getConstantLabelValue() {
		return constantLabelValue;
	}
	public void setConstantLabelValue(String constantLabelValue) {
		this.constantLabelValue = "Y";
	}
	/**
	 * 
	 * @return ArrayList
	 */
	public ArrayList getExemptionDTOList() {
		return exemptionDTOList;
	}
	/**
	 * @param exemptionDTOList
	 */
	public void setExemptionDTOList(ArrayList exemptionDTOList) {
		this.exemptionDTOList = exemptionDTOList;
	}
	/**
	 * @return ArrayList
	 */
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}
	/**
	 * @param selectedExemptionList
	 */
	public void setSelectedExemptionList(
			ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}

	public DashBoardDTO getObjDashBoardDTO() {
		return objDashBoardDTO;
	}

	public void setObjDashBoardDTO(DashBoardDTO objDashBoardDTO) {
		this.objDashBoardDTO = objDashBoardDTO;
	}

	public String getDeedDuration() {
		return deedDuration;
	}

	public void setDeedDuration(String deedDuration) {
		this.deedDuration = deedDuration;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	public FormFile getImage() {
		return image;
	}

	public void setImage(FormFile image) {
		this.image = image;
	}

	public byte[] getImage1() {
		return image1;
	}

	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}

	public String getGuidUpload() {
		return guidUpload;
	}

	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}

	public String getParentPathScan() {
		return parentPathScan;
	}

	public void setParentPathScan(String parentPathScan) {
		this.parentPathScan = parentPathScan;
	}

	public String getFileNameScan() {
		return fileNameScan;
	}

	public void setFileNameScan(String fileNameScan) {
		this.fileNameScan = fileNameScan;
	}

	public int getIsInternalUser() {
		return isInternalUser;
	}

	public void setIsInternalUser(int isInternalUser) {
		this.isInternalUser = isInternalUser;
	}

	public String getDocPathComplete() {
		return docPathComplete;
	}

	public void setDocPathComplete(String docPathComplete) {
		this.docPathComplete = docPathComplete;
	}

	public String getAppGenderDisp() {
		return appGenderDisp;
	}

	public void setAppGenderDisp(String appGenderDisp) {
		this.appGenderDisp = appGenderDisp;
	}

	public String getPartyGenderDisp() {
		return partyGenderDisp;
	}

	public void setPartyGenderDisp(String partyGenderDisp) {
		this.partyGenderDisp = partyGenderDisp;
	}
	
	
	private String adoptFirstName;
	
	private String adoptMiddleName;
	
	private String adoptLastName;
	
	private String adoptGender;
	private String adoptGenderDisplay;
	public String getAdoptGenderDisplay() {
		return adoptGenderDisplay;
	}

	public void setAdoptGenderDisplay(String adoptGenderDisplay) {
		this.adoptGenderDisplay = adoptGenderDisplay;
	}

	public String getAdoptAge() {
		return adoptAge;
	}

	public void setAdoptAge(String adoptAge) {
		this.adoptAge = adoptAge;
	}


	private String adoptAge;

	private String adoptDay;

	private String adoptMonth;

	private String adoptYear;
	
	private String adoptFatherName;
	
	private String adoptMotherName;
	
	private String adoptCountry;
	
	private String adoptState;
	
	private String adoptDistrict;
	
	private String adoptAddress;

	private String adoptPostalcode;

	private String adoptMoblie;

	private String adoptPhone;

	private String adoptEmail;

	private String adoptPhotoId;
	
	private String adoptPhotoIdNumber;

	private String adoptNoPerson;

	private String adoptCountryName;
	
	private String adoptStateName;
	
	private String adoptDistrictName;

	private String adoptPhotoIdName;
	
	
	private String adoptNameDisplay;
	
	private String isAdoption;























	public String getIsAdoption() {
		return isAdoption;
	}

	public void setIsAdoption(String isAdoption) {
		this.isAdoption = isAdoption;
	}

	public String getAdoptNameDisplay() {
		return adoptNameDisplay;
	}

	public void setAdoptNameDisplay(String adoptNameDisplay) {
		this.adoptNameDisplay = adoptNameDisplay;
	}

	public String getAdoptPhotoIdName() {
		return adoptPhotoIdName;
	}

	public void setAdoptPhotoIdName(String adoptPhotoIdName) {
		this.adoptPhotoIdName = adoptPhotoIdName;
	}

	public String getAdoptCountryName() {
		return adoptCountryName;
	}

	public void setAdoptCountryName(String adoptCountryName) {
		this.adoptCountryName = adoptCountryName;
	}

	public String getAdoptStateName() {
		return adoptStateName;
	}

	public void setAdoptStateName(String adoptStateName) {
		this.adoptStateName = adoptStateName;
	}

	public String getAdoptDistrictName() {
		return adoptDistrictName;
	}

	public void setAdoptDistrictName(String adoptDistrictName) {
		this.adoptDistrictName = adoptDistrictName;
	}

	public String getAdoptFirstName() {
		return adoptFirstName;
	}

	public void setAdoptFirstName(String adoptFirstName) {
		this.adoptFirstName = adoptFirstName;
	}

	public String getAdoptMiddleName() {
		return adoptMiddleName;
	}

	public void setAdoptMiddleName(String adoptMiddleName) {
		this.adoptMiddleName = adoptMiddleName;
	}

	public String getAdoptLastName() {
		return adoptLastName;
	}

	public void setAdoptLastName(String adoptLastName) {
		this.adoptLastName = adoptLastName;
	}

	public String getAdoptGender() {
		return adoptGender;
	}

	public void setAdoptGender(String adoptGender) {
		this.adoptGender = adoptGender;
	}

	public String getAdoptDay() {
		return adoptDay;
	}

	public void setAdoptDay(String adoptDay) {
		this.adoptDay = adoptDay;
	}

	public String getAdoptMonth() {
		return adoptMonth;
	}

	public void setAdoptMonth(String adoptMonth) {
		this.adoptMonth = adoptMonth;
	}

	public String getAdoptYear() {
		return adoptYear;
	}

	public void setAdoptYear(String adoptYear) {
		this.adoptYear = adoptYear;
	}

	public String getAdoptFatherName() {
		return adoptFatherName;
	}

	public void setAdoptFatherName(String adoptFatherName) {
		this.adoptFatherName = adoptFatherName;
	}

	public String getAdoptMotherName() {
		return adoptMotherName;
	}

	public void setAdoptMotherName(String adoptMotherName) {
		this.adoptMotherName = adoptMotherName;
	}

	public String getAdoptCountry() {
		return adoptCountry;
	}

	public void setAdoptCountry(String adoptCountry) {
		this.adoptCountry = adoptCountry;
	}

	public String getAdoptState() {
		return adoptState;
	}

	public void setAdoptState(String adoptState) {
		this.adoptState = adoptState;
	}

	public String getAdoptDistrict() {
		return adoptDistrict;
	}

	public void setAdoptDistrict(String adoptDistrict) {
		this.adoptDistrict = adoptDistrict;
	}

	public String getAdoptAddress() {
		return adoptAddress;
	}

	public void setAdoptAddress(String adoptAddress) {
		this.adoptAddress = adoptAddress;
	}

	public String getAdoptPostalcode() {
		return adoptPostalcode;
	}

	public void setAdoptPostalcode(String adoptPostalcode) {
		this.adoptPostalcode = adoptPostalcode;
	}

	public String getAdoptMoblie() {
		return adoptMoblie;
	}

	public void setAdoptMoblie(String adoptMoblie) {
		this.adoptMoblie = adoptMoblie;
	}

	public String getAdoptPhone() {
		return adoptPhone;
	}

	public void setAdoptPhone(String adoptPhone) {
		this.adoptPhone = adoptPhone;
	}

	public String getAdoptEmail() {
		return adoptEmail;
	}

	public void setAdoptEmail(String adoptEmail) {
		this.adoptEmail = adoptEmail;
	}

	public String getAdoptPhotoId() {
		return adoptPhotoId;
	}

	public void setAdoptPhotoId(String adoptPhotoId) {
		this.adoptPhotoId = adoptPhotoId;
	}

	public String getAdoptPhotoIdNumber() {
		return adoptPhotoIdNumber;
	}

	public void setAdoptPhotoIdNumber(String adoptPhotoIdNumber) {
		this.adoptPhotoIdNumber = adoptPhotoIdNumber;
	}

	public String getAdoptNoPerson() {
		return adoptNoPerson;
	}

	public void setAdoptNoPerson(String adoptNoPerson) {
		this.adoptNoPerson = adoptNoPerson;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private String deedDraftId;
	
	private String deedConsumeFlag;

	private String deedDraftError;
	
	public String getDeedDraftId() {
		return deedDraftId;
	}

	public void setDeedDraftId(String deedDraftId) {
		this.deedDraftId = deedDraftId;
	}

	public String getDeedConsumeFlag() {
		return deedConsumeFlag;
	}

	public void setDeedConsumeFlag(String deedConsumeFlag) {
		this.deedConsumeFlag = deedConsumeFlag;
	}

	public String getDeedDraftError() {
		return deedDraftError;
	}

	public void setDeedDraftError(String deedDraftError) {
		this.deedDraftError = deedDraftError;
	}

	public String getDeedDraftStatus() {
		return deedDraftStatus;
	}

	public void setDeedDraftStatus(String deedDraftStatus) {
		this.deedDraftStatus = deedDraftStatus;
	}

	public String getDeedDraftErrorFlag() {
		return deedDraftErrorFlag;
	}

	public void setDeedDraftErrorFlag(String deedDraftErrorFlag) {
		this.deedDraftErrorFlag = deedDraftErrorFlag;
	}


	private String deedDraftStatus;

	private String deedDraftErrorFlag;


	private String janpad;
	
	public String getJanpad() {
		return janpad;
	}

	public void setJanpad(String janpad) {
		this.janpad = janpad;
	}

	public String getJanpadDisplay() {
		return janpadDisplay;
	}

	public void setJanpadDisplay(String janpadDisplay) {
		this.janpadDisplay = janpadDisplay;
	}

	public String getMuncipality() {
		return muncipality;
	}

	public void setMuncipality(String muncipality) {
		this.muncipality = muncipality;
	}

	public String getMuncipalityDIspaly() {
		return muncipalityDIspaly;
	}

	public void setMuncipalityDIspaly(String muncipalityDIspaly) {
		this.muncipalityDIspaly = muncipalityDIspaly;
	}

	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getStampDisplay() {
		return stampDisplay;
	}

	public void setStampDisplay(String stampDisplay) {
		this.stampDisplay = stampDisplay;
	}

	public String getUpkar() {
		return upkar;
	}

	public void setUpkar(String upkar) {
		this.upkar = upkar;
	}

	public String getUpkarDisplay() {
		return upkarDisplay;
	}

	public void setUpkarDisplay(String upkarDisplay) {
		this.upkarDisplay = upkarDisplay;
	}

	public String getExemptedAmount() {
		return exemptedAmount;
	}

	public void setExemptedAmount(String exemptedAmount) {
		this.exemptedAmount = exemptedAmount;
	}

	public String getExemptedAmountDisplay() {
		return exemptedAmountDisplay;
	}

	public void setExemptedAmountDisplay(String exemptedAmountDisplay) {
		this.exemptedAmountDisplay = exemptedAmountDisplay;
	}


	public void setEstampTypeCheck(String estampTypeCheck) {
		this.estampTypeCheck = estampTypeCheck;
	}

	public String getEstampTypeCheck() {
		return estampTypeCheck;
	}


	public void setBreifDocument(String breifDocument) {
		this.breifDocument = breifDocument;
	}

	public String getBreifDocument() {
		return breifDocument;
	}


	private String janpadDisplay;
	
	private String muncipality;

	private String muncipalityDIspaly;
	
	private String stamp;
	
	private String stampDisplay; 
	
	private String upkar;
	
	private String upkarDisplay;
	
	private String exemptedAmount;
	
	private String exemptedAmountDisplay;
	
	private String estampTypeCheck;
	
	private String breifDocument;

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public String getCourtDocTypeName() {
		return courtDocTypeName;
	}

	public void setCourtDocTypeName(String courtDocTypeName) {
		this.courtDocTypeName = courtDocTypeName;
	}

	public String getCourtType() {
		return courtType;
	}

	public void setCourtType(String courtType) {
		this.courtType = courtType;
	}

	public String getCourtDistrict() {
		return courtDistrict;
	}

	public void setCourtDistrict(String courtDistrict) {
		this.courtDistrict = courtDistrict;
	} 
	
}
