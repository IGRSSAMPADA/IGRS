package com.wipro.igrs.RegCompMaker.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.RegCompMaker.dto.CommonMkrDTO;
import com.wipro.igrs.RegCompMaker.dto.OthrDeedDtlsDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteDutiesDTO;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;

/**
 * @author Ankita
 * 
 */

public class RegCompleteMakerForm extends ActionForm {

	RegCompleteMakerDTO regDTO = new RegCompleteMakerDTO();
	RegCompleteDutiesDTO dutyDTO = new RegCompleteDutiesDTO();
	CommonMkrDTO commonDTO = new CommonMkrDTO();
	OthrDeedDtlsDTO othrDTO = new OthrDeedDtlsDTO();
	
	
	public String oldRegistrationDate;
	
	
	

	public String getOldRegistrationDate() {
		return oldRegistrationDate;
	}

	public void setOldRegistrationDate(String oldRegistrationDate) {
		this.oldRegistrationDate = oldRegistrationDate;
	}

	public String formName;
	public String actionName;
	public String nextPage;
	public String previousPage;
	public String adjNumber;

	private String checkVal;
	private String radVal;
	private String spotCheckVal;
	private String adjCheckVal;
	private String govEmpCheckVal;// check if the parties not present a
	// government official

	public String oldAppNumber;
	public String oldRegNumber;
	public String oldEcode;
	public String oldPstmpCode; // physical stamp code

	public String check;
	public String checkHold;
	public String checkRegNo;
	public String compId;
	public String hldBtnFlg;
	public String lnkFlg;
	public String errLnkFlg;
	public String chkHoldClick;
	public String holdReason;


	// Start:=====ArrayLists
	ArrayList instList = new ArrayList();
	ArrayList exemList = new ArrayList();
	ArrayList deedList = new ArrayList();
	ArrayList complList = new ArrayList();
	ArrayList deedTxnList = new ArrayList();
	ArrayList propList = new ArrayList();
	ArrayList propIdList = new ArrayList();
	ArrayList oldPropIdList = new ArrayList();// this is the property list of
												// the document which has to be
												// searched in history
	// list for retaining arraylists
	ArrayList linkedAmtList = new ArrayList();
	ArrayList retainList = new ArrayList();
	ArrayList finalList = new ArrayList();
	ArrayList transactPartyList = new ArrayList();
	ArrayList witnessList = new ArrayList();
	ArrayList compPartyList = new ArrayList();// for transacting party list in
												// copliance page
	ArrayList chkdComplianceLst = new ArrayList();
	ArrayList holdListDisp = new ArrayList();
	Map myMap = new HashMap();
	Map amtMap = new HashMap();
	private Map uploadWitnessPhotograph = new HashMap();
	private HashMap compliancePropertyRelatedMap = new HashMap();
	private HashMap compliancePartyRelatedMap = new HashMap();
	private ArrayList relationshipList = new ArrayList();
	private ArrayList consenterList = new ArrayList();
	// For other deed related Details
	private ArrayList caveatDetailsList = new ArrayList();
	public ArrayList getCaveatDetailsList() {
		return caveatDetailsList;
	}

	public void setCaveatDetailsList(ArrayList caveatDetailsList) {
		this.caveatDetailsList = caveatDetailsList;
	}

	public String country;
	public String state;
	public String district;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String age;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private int listSize;
	private String propId;
	private String propIdInit;
	private String slctGovId;
	private String nationality;
	private String address;
	private String slctCountry;
	private String slctState;
	private String city;
	private String slctDistrict;
	private String postalCode;
	private String phoneNumber;
	private String loanInfo;
	private String taxDuties;
	private String indCountry;
	private int keyCount = 0;
	private int addMoreCounter;
	private String deleteWitness;
	private String hdnDeleteWitness;
	private String hdnPresentParty;
	private String hdnPresentWitness;
	private String hldComments;
	// New Guardian Field - akansha
	private String guardian ;
	
	public String getGuardian() {
		return guardian;
	}

	public void setGuardian(String guardian) {
		this.guardian = guardian;
	}
	//Start:==== Checklist
	private String exeDate;
	private String exeOutIndDate;
	private String ordrDate;
	private String dcreeOrderDate;
	private String lstAppealDate;


	private String outIndCheck;
	private String crtDcreeCheck;
	private String crtDcreeAppealChk;
	private String docAftrDeathChk;
	private String regByPoa;
	private String comments;
	private String deathCert;
	private String typeOfPoa;
	private String poaAuthNum;
	private String slctGenderRad="Male";
	// for caveats
	

	ArrayList caveatList = new ArrayList();
	
	
	
	
	
	public String getSlctGenderRad() {
		return slctGenderRad;
	}

	public void setSlctGenderRad(String slctGenderRad) {
		this.slctGenderRad = slctGenderRad;
	}

	public String getHoldReason() {
		return holdReason;
	}

	public void setHoldReason(String holdReason) {
		this.holdReason = holdReason;
	}

	public ArrayList getHoldListDisp() {
		return holdListDisp;
	}

	public void setHoldListDisp(ArrayList holdListDisp) {
		this.holdListDisp = holdListDisp;
	}

	private int caveatListSize;
	
	ArrayList bnkCaveatList = new ArrayList();
	private int bnkCaveatListSize;
	

	public String getChkHoldClick() {
		return chkHoldClick;
	}

	public void setChkHoldClick(String chkHoldClick) {
		this.chkHoldClick = chkHoldClick;
	}
	
	public ArrayList getBnkCaveatList() {
		return bnkCaveatList;
	}

	public void setBnkCaveatList(ArrayList bnkCaveatList) {
		this.bnkCaveatList = bnkCaveatList;
	}

	public int getBnkCaveatListSize() {
		return bnkCaveatListSize;
	}

	public void setBnkCaveatListSize(int bnkCaveatListSize) {
		this.bnkCaveatListSize = bnkCaveatListSize;
	}

	private HashMap mapPropertyTransPartyDisp = new HashMap();
	
	

	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}

	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}

	public String getErrLnkFlg() {
		return errLnkFlg;
	}

	public void setErrLnkFlg(String errLnkFlg) {
		this.errLnkFlg = errLnkFlg;
	}

	public int getCaveatListSize() {
		return caveatListSize;
	}

	public void setCaveatListSize(int caveatListSize) {
		this.caveatListSize = caveatListSize;
	}

	public ArrayList getCaveatList() {
		return caveatList;
	}

	public void setCaveatList(ArrayList caveatList) {
		this.caveatList = caveatList;
	}

	public String getPoaAuthNum() {
		return poaAuthNum;
	}

	public void setPoaAuthNum(String poaAuthNum) {
		this.poaAuthNum = poaAuthNum;
	}

	public String getTypeOfPoa() {
		return typeOfPoa;
	}

	public void setTypeOfPoa(String typeOfPoa) {
		this.typeOfPoa = typeOfPoa;
	}

	public String getRegByPoa() {
		return regByPoa;
	}

	public void setRegByPoa(String regByPoa) {
		this.regByPoa = regByPoa;
	}

	public String getDeathCert() {
		return deathCert;
	}

	public void setDeathCert(String deathCert) {
		this.deathCert = deathCert;
	}

	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDocAftrDeathChk() {
		return docAftrDeathChk;
	}

	public void setDocAftrDeathChk(String docAftrDeathChk) {
		this.docAftrDeathChk = docAftrDeathChk;
	}

	public String getLstAppealDate() {
		return lstAppealDate;
	}

	public void setLstAppealDate(String lstAppealDate) {
		this.lstAppealDate = lstAppealDate;
	}

	public String getDcreeOrderDate() {
		return dcreeOrderDate;
	}

	public void setDcreeOrderDate(String dcreeOrderDate) {
		this.dcreeOrderDate = dcreeOrderDate;
	}
	
	public String getCrtDcreeAppealChk() {
		return crtDcreeAppealChk;
	}

	public void setCrtDcreeAppealChk(String crtDcreeAppealChk) {
		this.crtDcreeAppealChk = crtDcreeAppealChk;
	}

	public String getOrdrDate() {
		return ordrDate;
	}

	public void setOrdrDate(String ordrDate) {
		this.ordrDate = ordrDate;
	}
	
	public String getCrtDcreeCheck() {
		return crtDcreeCheck;
	}

	public void setCrtDcreeCheck(String crtDcreeCheck) {
		this.crtDcreeCheck = crtDcreeCheck;
	}

	public String getExeOutIndDate() {
		return exeOutIndDate;
	}

	public void setExeOutIndDate(String exeOutIndDate) {
		this.exeOutIndDate = exeOutIndDate;
	}
	public String getOutIndCheck() {
		return outIndCheck;
	}

	public void setOutIndCheck(String outIndCheck) {
		this.outIndCheck = outIndCheck;
	}

	public String getExeDate() {
		return exeDate;
	}

	public void setExeDate(String exeDate) {
		this.exeDate = exeDate;
	}

	public String getHldComments() {
		return hldComments;
	}

	public void setHldComments(String hldComments) {
		this.hldComments = hldComments;
	}

	private String slctUser;
	private int countFile = 0;

	Map photoMap = new HashMap();
	Map idMap = new HashMap();

	public Map getPhotoMap() {
		return photoMap;
	}

	public void setPhotoMap(Map photoMap) {
		this.photoMap = photoMap;
	}

	public Map getIdMap() {
		return idMap;
	}

	public void setIdMap(Map idMap) {
		this.idMap = idMap;
	}

	public String getSlctUser() {
		return slctUser;
	}

	public void setSlctUser(String slctUser) {
		this.slctUser = slctUser;
	}

	public ArrayList getChkdComplianceLst() {
		return chkdComplianceLst;
	}

	public void setChkdComplianceLst(ArrayList chkdComplianceLst) {
		this.chkdComplianceLst = chkdComplianceLst;
	}

	public ArrayList getCompPartyList() {
		return compPartyList;
	}

	public void setCompPartyList(ArrayList compPartyList) {
		this.compPartyList = compPartyList;
	}

	public int getCountFile() {
		return countFile;
	}

	public void setCountFile(int countFile) {
		this.countFile = countFile;
	}

	public String getSlctGovId() {
		return slctGovId;
	}

	public void setSlctGovId(String slctGovId) {
		this.slctGovId = slctGovId;
	}

	public ArrayList getTransactPartyList() {
		return transactPartyList;
	}

	public void setTransactPartyList(ArrayList transactPartyList) {
		this.transactPartyList = transactPartyList;
	}

	public ArrayList getWitnessList() {
		return witnessList;
	}

	public void setWitnessList(ArrayList witnessList) {
		this.witnessList = witnessList;
	}

	public String getLnkFlg() {
		return lnkFlg;
	}

	public void setLnkFlg(String lnkFlg) {
		this.lnkFlg = lnkFlg;
	}

	public String getPropIdInit() {
		return propIdInit;
	}

	public void setPropIdInit(String propIdInit) {
		this.propIdInit = propIdInit;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int i) {
		this.listSize = i;
	}

	public ArrayList getOldPropIdList() {
		return oldPropIdList;
	}

	public void setOldPropIdList(ArrayList oldPropIdList) {
		this.oldPropIdList = oldPropIdList;
	}

	public String getHdnPresentParty() {
		return hdnPresentParty;
	}

	public void setHdnPresentParty(String hdnPresentParty) {
		this.hdnPresentParty = hdnPresentParty;
	}

	public String getHdnPresentWitness() {
		return hdnPresentWitness;
	}

	public void setHdnPresentWitness(String hdnPresentWitness) {
		this.hdnPresentWitness = hdnPresentWitness;
	}

	public String getHdnDeleteWitness() {
		return hdnDeleteWitness;
	}

	public void setHdnDeleteWitness(String hdnDeleteWitness) {
		this.hdnDeleteWitness = hdnDeleteWitness;
	}

	public String getDeleteWitness() {
		return deleteWitness;
	}

	public void setDeleteWitness(String deleteWitness) {
		this.deleteWitness = deleteWitness;
	}

	Map dtlsMap = new HashMap();

	public int getKeyCount() {
		return keyCount;
	}

	public void setKeyCount(int keyCount) {
		this.keyCount = keyCount;
	}

	public int getAddMoreCounter() {
		return addMoreCounter;
	}

	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}

	public Map getDtlsMap() {
		return dtlsMap;
	}

	public void setDtlsMap(Map dtlsMap) {
		this.dtlsMap = dtlsMap;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSlctCountry() {
		return slctCountry;
	}

	public void setSlctCountry(String slctCountry) {
		this.slctCountry = slctCountry;
	}

	public String getSlctState() {
		return slctState;
	}

	public void setSlctState(String slctState) {
		this.slctState = slctState;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSlctDistrict() {
		return slctDistrict;
	}

	public void setSlctDistrict(String slctDistrict) {
		this.slctDistrict = slctDistrict;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(String loanInfo) {
		this.loanInfo = loanInfo;
	}

	public String getTaxDuties() {
		return taxDuties;
	}

	public void setTaxDuties(String taxDuties) {
		this.taxDuties = taxDuties;
	}

	public String getIndCountry() {
		return indCountry;
	}

	public void setIndCountry(String indCountry) {
		this.indCountry = indCountry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHldBtnFlg() {
		return hldBtnFlg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setHldBtnFlg(String hldBtnFlg) {
		this.hldBtnFlg = hldBtnFlg;
	}

	public String getCheckHold() {
		return checkHold;
	}

	public void setCheckHold(String checkHold) {
		this.checkHold = checkHold;
	}

	public Map getAmtMap() {
		return amtMap;
	}

	public void setAmtMap(Map amtMap) {
		this.amtMap = amtMap;
	}

	public Map getMyMap() {
		return myMap;
	}

	public void setMyMap(Map myMap) {
		this.myMap = myMap;
	}

	public ArrayList getFinalList() {
		return finalList;
	}

	public void setFinalList(ArrayList finalList) {
		this.finalList = finalList;
	}

	public ArrayList getLinkedAmtList() {
		return linkedAmtList;
	}

	public void setLinkedAmtList(ArrayList linkedAmtList) {
		this.linkedAmtList = linkedAmtList;
	}

	public ArrayList getRetainList() {
		return retainList;
	}

	public void setRetainList(ArrayList retainList) {
		this.retainList = retainList;
	}

	public ArrayList getPropIdList() {
		return propIdList;
	}

	public void setPropIdList(ArrayList propIdList) {
		this.propIdList = propIdList;
	}

	public CommonMkrDTO getCommonDTO() {
		return commonDTO;
	}

	public void setCommonDTO(CommonMkrDTO commonDTO) {
		this.commonDTO = commonDTO;
	}

	public OthrDeedDtlsDTO getOthrDTO() {
		return othrDTO;
	}

	public void setOthrDTO(OthrDeedDtlsDTO othrDTO) {
		this.othrDTO = othrDTO;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public ArrayList getPropList() {
		return propList;
	}

	public void setPropList(ArrayList propList) {
		this.propList = propList;
	}

	ArrayList formList = new ArrayList();

	// End:=====ArrayLists

	public String getCheckVal() {
		return checkVal;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public ArrayList getFormList() {
		return formList;
	}

	public void setFormList(ArrayList formList) {
		this.formList = formList;
	}

	public ArrayList getDeedTxnList() {
		return deedTxnList;
	}

	public void setDeedTxnList(ArrayList deedTxnList) {
		this.deedTxnList = deedTxnList;
	}

	public ArrayList getComplList() {
		return complList;
	}

	public void setComplList(ArrayList complList) {
		this.complList = complList;
	}

	public String getCheckRegNo() {
		return checkRegNo;
	}

	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}

	public String getGovEmpCheckVal() {
		return govEmpCheckVal;
	}

	public void setGovEmpCheckVal(String govEmpCheckVal) {
		this.govEmpCheckVal = govEmpCheckVal;
	}

	public void setCheckVal(String checkVal) {
		this.checkVal = checkVal;
	}

	public String getRadVal() {
		return radVal;
	}

	public void setRadVal(String radVal) {
		this.radVal = radVal;
	}

	public String getSpotCheckVal() {
		return spotCheckVal;
	}

	public void setSpotCheckVal(String spotCheckVal) {
		this.spotCheckVal = spotCheckVal;
	}

	public String getAdjCheckVal() {
		return adjCheckVal;
	}

	public void setAdjCheckVal(String adjCheckVal) {
		this.adjCheckVal = adjCheckVal;
	}

	public String getAdjNumber() {
		return adjNumber;
	}

	public void setAdjNumber(String adjNumber) {
		this.adjNumber = adjNumber;
	}

	public RegCompleteDutiesDTO getDutyDTO() {
		return dutyDTO;
	}

	public void setDutyDTO(RegCompleteDutiesDTO dutyDTO) {
		this.dutyDTO = dutyDTO;
	}

	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	public RegCompleteMakerDTO getRegDTO() {
		return regDTO;
	}

	public void setRegDTO(RegCompleteMakerDTO regDTO) {
		this.regDTO = regDTO;
	}

	public ArrayList getInstList() {
		return instList;
	}

	public void setInstList(ArrayList instList) {
		this.instList = instList;
	}

	public ArrayList getExemList() {
		return exemList;
	}

	public void setExemList(ArrayList exemList) {
		this.exemList = exemList;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
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

	public String getOldAppNumber() {
		return oldAppNumber;
	}

	public void setOldAppNumber(String oldAppNumber) {
		this.oldAppNumber = oldAppNumber;
	}

	public String getOldRegNumber() {
		return oldRegNumber;
	}

	public void setOldRegNumber(String oldRegNumber) {
		this.oldRegNumber = oldRegNumber;
	}

	public String getOldEcode() {
		return oldEcode;
	}

	public void setOldEcode(String oldEcode) {
		this.oldEcode = oldEcode;
	}

	public String getOldPstmpCode() {
		return oldPstmpCode;
	}

	public void setOldPstmpCode(String oldPstmpCode) {
		this.oldPstmpCode = oldPstmpCode;
	}

	
	///// Added By Simran////
	
	private ArrayList caveatDetailList = new ArrayList();
	private ArrayList caveatBankDetailList = new ArrayList();
	private Map poaList = new HashMap();
	private String poaFlg;
	private int poaCount;
	private String checkBoxPOA;
	private String checkBoxDth;
	private String poaKeys;
	private String dthCertKeys;
	private Map uploadDthList = new HashMap();
	private int deathCertCount;
	private ArrayList partyList = new ArrayList();
	private ArrayList partyIDList = new ArrayList();
	private String filePathPOA;
	private String witAdd;
	private Map dtlsMapConsntr = new HashMap();
	



	public ArrayList getCaveatDetailList() {
		return caveatDetailList;
	}

	public void setCaveatDetailList(ArrayList caveatDetailList) {
		this.caveatDetailList = caveatDetailList;
	}

	public ArrayList getCaveatBankDetailList() {
		return caveatBankDetailList;
	}

	public void setCaveatBankDetailList(ArrayList caveatBankDetailList) {
		this.caveatBankDetailList = caveatBankDetailList;
	}

	public String getPoaFlg() {
		return poaFlg;
	}

	public void setPoaFlg(String poaFlg) {
		this.poaFlg = poaFlg;
	}

	public Map getPoaList() {
		return poaList;
	}

	public void setPoaList(Map poaList) {
		this.poaList = poaList;
	}

	public int getPoaCount() {
		return poaCount;
	}

	public void setPoaCount(int poaCount) {
		this.poaCount = poaCount;
	}

	public String getCheckBoxPOA() {
		return checkBoxPOA;
	}

	public void setCheckBoxPOA(String checkBoxPOA) {
		this.checkBoxPOA = checkBoxPOA;
	}

	public String getPoaKeys() {
		return poaKeys;
	}

	public void setPoaKeys(String poaKeys) {
		this.poaKeys = poaKeys;
	}

	

	public int getDeathCertCount() {
		return deathCertCount;
	}

	public void setDeathCertCount(int deathCertCount) {
		this.deathCertCount = deathCertCount;
	}

	

	

	public Map getUploadDthList() {
		return uploadDthList;
	}

	public void setUploadDthList(Map uploadDthList) {
		this.uploadDthList = uploadDthList;
	}

	public ArrayList getPartyList() {
		return partyList;
	}

	public void setPartyList(ArrayList partyList) {
		this.partyList = partyList;
	}

	public ArrayList getPartyIDList() {
		return partyIDList;
	}

	public void setPartyIDList(ArrayList partyIDList) {
		this.partyIDList = partyIDList;
	}

	public String getCheckBoxDth() {
		return checkBoxDth;
	}

	public void setCheckBoxDth(String checkBoxDth) {
		this.checkBoxDth = checkBoxDth;
	}

	public String getDthCertKeys() {
		return dthCertKeys;
	}

	public void setDthCertKeys(String dthCertKeys) {
		this.dthCertKeys = dthCertKeys;
	}

	public String getFilePathPOA() {
		return filePathPOA;
	}

	public void setFilePathPOA(String filePathPOA) {
		this.filePathPOA = filePathPOA;
	}

	public Map getUploadWitnessPhotograph() {
		return uploadWitnessPhotograph;
	}

	public void setUploadWitnessPhotograph(Map uploadWitnessPhotograph) {
		this.uploadWitnessPhotograph = uploadWitnessPhotograph;
	}

	public HashMap getCompliancePropertyRelatedMap() {
		return compliancePropertyRelatedMap;
	}

	public void setCompliancePropertyRelatedMap(HashMap compliancePropertyRelatedMap) {
		this.compliancePropertyRelatedMap = compliancePropertyRelatedMap;
	}

	public HashMap getCompliancePartyRelatedMap() {
		return compliancePartyRelatedMap;
	}

	public void setCompliancePartyRelatedMap(HashMap compliancePartyRelatedMap) {
		this.compliancePartyRelatedMap = compliancePartyRelatedMap;
	}

	public ArrayList getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(ArrayList relationshipList) {
		this.relationshipList = relationshipList;
	}

	public ArrayList getConsenterList() {
		return consenterList;
	}

	public void setConsenterList(ArrayList consenterList) {
		this.consenterList = consenterList;
	}

	private String witnessAdd;
	public String getWitnessAdd() {
		return witnessAdd;
	}

	public void setWitnessAdd(String witnessAdd) {
		this.witnessAdd = witnessAdd;
	}

	public String getWitAdd() {
		return witAdd;
	}

	public void setWitAdd(String witAdd) {
		this.witAdd = witAdd;
	}

	public Map getDtlsMapConsntr() {
		return dtlsMapConsntr;
	}

	public void setDtlsMapConsntr(Map dtlsMapConsntr) {
		this.dtlsMapConsntr = dtlsMapConsntr;
	}

	private String deathCertChk;
	private String poaChk;
	
	public String getDeathCertChk() {
		return deathCertChk;
	}

	public void setDeathCertChk(String deathCertChk) {
		this.deathCertChk = deathCertChk;
	}

	public String getPoaChk() {
		return poaChk;
	}

	public void setPoaChk(String poaChk) {
		this.poaChk = poaChk;
	}
	
	
	//added by saurav for multiple table different application id check
	//private String regTxnId;
	private String dupTab;



/*	public String getRegTxnId() {
		return regTxnId;
	}

	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}*/

	public String getDupTab() {
		return dupTab;
	}

	public void setDupTab(String dupTab) {
		this.dupTab = dupTab;
	}
	
	
	
}
