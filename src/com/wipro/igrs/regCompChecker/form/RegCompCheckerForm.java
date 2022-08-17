package com.wipro.igrs.regCompChecker.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newreginit.dto.AadharDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.regCompChecker.dto.RegCompCheckerDTO;

public class RegCompCheckerForm extends ActionForm {
	
	RegCompCheckerDTO regDTO = new RegCompCheckerDTO();
	
	private ArrayList listDtoAdju=new ArrayList();
	
	public ArrayList getRegFeeExemptionDiscriptionList;
	
	public ArrayList getListDtoAdju() {
		return listDtoAdju;
	}

	public void setListDtoAdju(ArrayList listDtoAdju) {
		this.listDtoAdju = listDtoAdju;
	}


	
	private HashMap dtlsMapConsntr = new HashMap();
	private HashMap mapPropertyTransParty = new HashMap();
	private ArrayList selectedExemptionList = new ArrayList();
	private HashMap mapPropertyTransPartyDisp = new HashMap();
	private HashMap mapTransactingPartiesDisp = new HashMap();
	private HashMap mapLinkingDutiesDisp = new HashMap();
	private ArrayList witnessDetailsList = new ArrayList();
	public ArrayList witnessCompDetailsLst = new ArrayList();
	private ArrayList linkHistoryList = new ArrayList();
	private ArrayList partiesPresentList = new ArrayList();
	private ArrayList partiesDetailList = new ArrayList();
	private ArrayList complianceListDisp = new ArrayList();
	private ArrayList caveatDetailsList = new ArrayList();
	private ArrayList caveatBankDetailsList = new ArrayList();
	private ArrayList checkListDetailsList = new ArrayList();
	private ArrayList linkingDutiesDisp  = new ArrayList();
	private ArrayList holdListDisp = new ArrayList();
	private ArrayList govtOfficialList = new ArrayList();
	private ArrayList dutyList = new ArrayList();
	private ArrayList dutyListAtMaker = new ArrayList();
	private ArrayList bookDetailsList = new ArrayList();
	private ArrayList linkingDutiesDispList = new ArrayList();
	private ArrayList deathUploadList = new ArrayList();
	private ArrayList poaChecklist = new ArrayList();
	private ArrayList propLockDetlsList = new ArrayList();
	private ArrayList caseDetails = new ArrayList();
	private ArrayList propList = new ArrayList();
	private Map UploadDthList = new HashMap();
	private Map uploadGovtLetter = new HashMap();
	private Map uploadWitnessPhotograph = new HashMap();
	private HashMap compliancePropertyRelatedMap = new HashMap();
	private HashMap compliancePartyRelatedMap = new HashMap();
	private ArrayList estampDetLst = new ArrayList();
	private ArrayList sealSubTypeList = new ArrayList();
	private ArrayList regSealSubTypeList = new ArrayList();
	private ArrayList stampSealSubTypeList = new ArrayList();
	private ArrayList transPartyListPVDeed = new ArrayList();
	private ArrayList propListPVDeed = new ArrayList();
	private ArrayList transPartyListNonPropDeed;
	private ArrayList propListNonPropDeed;
	private ArrayList consenterDetailsList = new ArrayList();
	public ArrayList consenterCompDetailsLst = new ArrayList();
	public ArrayList relationshipList = new ArrayList();
	public ArrayList caveatDetailList = new ArrayList();
	public ArrayList caveatBankDetailList = new ArrayList();
	private ArrayList userEnterableList= new ArrayList();
	private String estampCode;
	private String estampAmt;
	private String correctionSeal;
	private String estampDateTime;
	private String exemType;
	private String headerImg;
	private String holdChk;
	private String owmChk;
	private String cancellationLabel;	
	private String regSealCheck; 
	private String exeSealCheck; 

	private String userId;
	private String printFlag="N";
	private String allowPrint="N";
	private String otp;
	
	private RegCommonForm regInitForm=new RegCommonForm();
	
	
	public RegCommonForm getRegInitForm() {
		return regInitForm;
	}

	public void setRegInitForm(RegCommonForm regInitForm) {
		this.regInitForm = regInitForm;
	}

	public HashMap getDtlsMapConsntr() {
		return dtlsMapConsntr;
	}

	public void setDtlsMapConsntr(HashMap dtlsMapConsntr) {
		this.dtlsMapConsntr = dtlsMapConsntr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	//added by vinay
	private String holdCheck;

	private String disableBook;
	
	public String getHoldCheck() {
		return holdCheck;
	}

	public void setHoldCheck(String holdCheck) {
		this.holdCheck = holdCheck;
	}

	private ArrayList partyList = new ArrayList();
	private ArrayList partyIDList = new ArrayList();
	
	public int getPoaCount() {
		return poaCount;
	}

	public void setPoaCount(int poaCount) {
		this.poaCount = poaCount;
	}

	private Map UploadPOAList = new HashMap();

	private String check;
	private String check2;
	private String checkRegNo;
	private int poaCount;
	public String errLnkFlg;
	private String dthCertKeys;
	private String poaKeys;
	private Double finalDuty;
	private Double finalRegFee;
	private Double finalStampDuty;
	private String finalDocPath;
	private long timeTaken;
	
	

	

	public long getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

	public RegCompCheckerDTO getRegDTO() {
		return regDTO;
	}

	public void setRegDTO(RegCompCheckerDTO regDTO) {
		this.regDTO = regDTO;
	}
	
	
	
	
	public ArrayList getSelectedExemptionList() {
		return selectedExemptionList;
	}

	public void setSelectedExemptionList(ArrayList selectedExemptionList) {
		this.selectedExemptionList = selectedExemptionList;
	}
	
	
	public HashMap getMapPropertyTransParty() {
		return mapPropertyTransParty;
	}

	public void setMapPropertyTransParty(HashMap mapPropertyTransParty) {
		this.mapPropertyTransParty = mapPropertyTransParty;
	}
	
	

	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}

	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}
	
	

	public HashMap getMapTransactingPartiesDisp() {
		return mapTransactingPartiesDisp;
	}

	public void setMapTransactingPartiesDisp(HashMap mapTransactingPartiesDisp) {
		this.mapTransactingPartiesDisp = mapTransactingPartiesDisp;
	}
	
	

	public ArrayList getWitnessDetailsList() {
		return witnessDetailsList;
	}

	public void setWitnessDetailsList(ArrayList witnessDetailsList) {
		this.witnessDetailsList = witnessDetailsList;
	}
	
	

	public ArrayList getLinkHistoryList() {
		return linkHistoryList;
	}

	public void setLinkHistoryList(ArrayList linkHistoryList) {
		this.linkHistoryList = linkHistoryList;
	}

	
	
	public ArrayList getPartiesPresentList() {
		return partiesPresentList;
	}

	public void setPartiesPresentList(ArrayList partiesPresentList) {
		this.partiesPresentList = partiesPresentList;
	}
	
	

	public ArrayList getPartiesDetailList() {
		return partiesDetailList;
	}

	public void setPartiesDetailList(ArrayList partiesDetailList) {
		this.partiesDetailList = partiesDetailList;
	}
	
	

	public ArrayList getComplianceListDisp() {
		return complianceListDisp;
	}

	public void setComplianceListDisp(ArrayList complianceListDisp) {
		this.complianceListDisp = complianceListDisp;
	}
	
	

	public ArrayList getCaveatDetailsList() {
		return caveatDetailsList;
	}

	public void setCaveatDetailsList(ArrayList caveatDetailsList) {
		this.caveatDetailsList = caveatDetailsList;
	}

	public ArrayList getCheckListDetailsList() {
		return checkListDetailsList;
	}

	public void setCheckListDetailsList(ArrayList checkListDetailsList) {
		this.checkListDetailsList = checkListDetailsList;
	}
	
	

	public ArrayList getLinkingDutiesDisp() {
		return linkingDutiesDisp;
	}

	public void setLinkingDutiesDisp(ArrayList linkingDutiesDisp) {
		this.linkingDutiesDisp = linkingDutiesDisp;
	}

	
	
	public ArrayList getHoldListDisp() {
		return holdListDisp;
	}

	public void setHoldListDisp(ArrayList holdListDisp) {
		this.holdListDisp = holdListDisp;
	}
	
	

	public ArrayList getGovtOfficialList() {
		return govtOfficialList;
	}

	public void setGovtOfficialList(ArrayList govtOfficialList) {
		this.govtOfficialList = govtOfficialList;
	}
	
	

	public ArrayList getBookDetailsList() {
		return bookDetailsList;
	}

	public void setBookDetailsList(ArrayList bookDetailsList) {
		this.bookDetailsList = bookDetailsList;
	}
	
	

	public ArrayList getWitnessCompDetailsLst() {
		return witnessCompDetailsLst;
	}

	public void setWitnessCompDetailsLst(ArrayList witnessCompDetailsLst) {
		this.witnessCompDetailsLst = witnessCompDetailsLst;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
	
	

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public String getCheckRegNo() {
		return checkRegNo;
	}

	public void setCheckRegNo(String checkRegNo) {
		this.checkRegNo = checkRegNo;
	}

	public HashMap getMapLinkingDutiesDisp() {
		return mapLinkingDutiesDisp;
	}

	public void setMapLinkingDutiesDisp(HashMap mapLinkingDutiesDisp) {
		this.mapLinkingDutiesDisp = mapLinkingDutiesDisp;
	}

	public ArrayList getDutyList() {
		return dutyList;
	}

	public void setDutyList(ArrayList dutyList) {
		this.dutyList = dutyList;
	}

	public ArrayList getDutyListAtMaker() {
		return dutyListAtMaker;
	}

	public void setDutyListAtMaker(ArrayList dutyListAtMaker) {
		this.dutyListAtMaker = dutyListAtMaker;
	}

	public ArrayList getLinkingDutiesDispList() {
		return linkingDutiesDispList;
	}

	public void setLinkingDutiesDispList(ArrayList linkingDutiesDispList) {
		this.linkingDutiesDispList = linkingDutiesDispList;
	}

	public ArrayList getCaveatBankDetailsList() {
		return caveatBankDetailsList;
	}

	public void setCaveatBankDetailsList(ArrayList caveatBankDetailsList) {
		this.caveatBankDetailsList = caveatBankDetailsList;
	}

	public ArrayList getDeathUploadList() {
		return deathUploadList;
	}

	public void setDeathUploadList(ArrayList deathUploadList) {
		this.deathUploadList = deathUploadList;
	}

	public ArrayList getPoaChecklist() {
		return poaChecklist;
	}

	public void setPoaChecklist(ArrayList poaChecklist) {
		this.poaChecklist = poaChecklist;
	}

	public ArrayList getPropList() {
		return propList;
	}

	public void setPropList(ArrayList propList) {
		this.propList = propList;
	}

	public Map getUploadDthList() {
		return UploadDthList;
	}

	public void setUploadDthList(Map uploadDthList) {
		UploadDthList = uploadDthList;
	}

	public Map getUploadPOAList() {
		return UploadPOAList;
	}

	public void setUploadPOAList(Map uploadPOAList) {
		UploadPOAList = uploadPOAList;
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

	public String getErrLnkFlg() {
		return errLnkFlg;
	}

	public void setErrLnkFlg(String errLnkFlg) {
		this.errLnkFlg = errLnkFlg;
	}

	public String getDthCertKeys() {
		return dthCertKeys;
	}

	public void setDthCertKeys(String dthCertKeys) {
		this.dthCertKeys = dthCertKeys;
	}

	public String getPoaKeys() {
		return poaKeys;
	}

	public void setPoaKeys(String poaKeys) {
		this.poaKeys = poaKeys;
	}

	public ArrayList getPropLockDetlsList() {
		return propLockDetlsList;
	}

	public void setPropLockDetlsList(ArrayList propLockDetlsList) {
		this.propLockDetlsList = propLockDetlsList;
	}

	public ArrayList getCaseDetails() {
		return caseDetails;
	}

	public void setCaseDetails(ArrayList caseDetails) {
		this.caseDetails = caseDetails;
	}

	public Map getUploadGovtLetter() {
		return uploadGovtLetter;
	}

	public void setUploadGovtLetter(Map uploadGovtLetter) {
		this.uploadGovtLetter = uploadGovtLetter;
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

	public Map getUploadWitnessPhotograph() {
		return uploadWitnessPhotograph;
	}

	public void setUploadWitnessPhotograph(Map uploadWitnessPhotograph) {
		this.uploadWitnessPhotograph = uploadWitnessPhotograph;
	}
	
	private String regInitEstampCode;

	public String getRegInitEstampCode() {
		return regInitEstampCode;
	}

	public void setRegInitEstampCode(String regInitEstampCode) {
		this.regInitEstampCode = regInitEstampCode;
	}

	public ArrayList getEstampDetLst() {
		return estampDetLst;
	}

	public void setEstampDetLst(ArrayList estampDetLst) {
		this.estampDetLst = estampDetLst;
	}

	public String getEstampCode() {
		return estampCode;
	}

	public void setEstampCode(String estampCode) {
		this.estampCode = estampCode;
	}

	public String getEstampAmt() {
		return estampAmt;
	}

	public void setEstampAmt(String estampAmt) {
		this.estampAmt = estampAmt;
	}

	public String getEstampDateTime() {
		return estampDateTime;
	}

	public void setEstampDateTime(String estampDateTime) {
		this.estampDateTime = estampDateTime;
	}

	public Double getFinalDuty() {
		return finalDuty;
	}

	public void setFinalDuty(Double finalDuty) {
		this.finalDuty = finalDuty;
	}

	public Double getFinalRegFee() {
		return finalRegFee;
	}

	public void setFinalRegFee(Double finalRegFee) {
		this.finalRegFee = finalRegFee;
	}

	public Double getFinalStampDuty() {
		return finalStampDuty;
	}

	public void setFinalStampDuty(Double finalStampDuty) {
		this.finalStampDuty = finalStampDuty;
	}

	public ArrayList getSealSubTypeList() {
		return sealSubTypeList;
	}

	public void setSealSubTypeList(ArrayList sealSubTypeList) {
		this.sealSubTypeList = sealSubTypeList;
	}

	public ArrayList getRegSealSubTypeList() {
		return regSealSubTypeList;
	}

	public void setRegSealSubTypeList(ArrayList regSealSubTypeList) {
		this.regSealSubTypeList = regSealSubTypeList;
	}

	public ArrayList getStampSealSubTypeList() {
		return stampSealSubTypeList;
	}

	public void setStampSealSubTypeList(ArrayList stampSealSubTypeList) {
		this.stampSealSubTypeList = stampSealSubTypeList;
	}

	public ArrayList getTransPartyListPVDeed() {
		return transPartyListPVDeed;
	}

	public void setTransPartyListPVDeed(ArrayList transPartyListPVDeed) {
		this.transPartyListPVDeed = transPartyListPVDeed;
	}

	public ArrayList getPropListPVDeed() {
		return propListPVDeed;
	}

	public void setPropListPVDeed(ArrayList propListPVDeed) {
		this.propListPVDeed = propListPVDeed;
	}

	public ArrayList getTransPartyListNonPropDeed() {
		return transPartyListNonPropDeed;
	}

	public void setTransPartyListNonPropDeed(ArrayList transPartyListNonPropDeed) {
		this.transPartyListNonPropDeed = transPartyListNonPropDeed;
	}

	public ArrayList getPropListNonPropDeed() {
		return propListNonPropDeed;
	}

	public void setPropListNonPropDeed(ArrayList propListNonPropDeed) {
		this.propListNonPropDeed = propListNonPropDeed;
	}

	public String getExemType() {
		return exemType;
	}

	public void setExemType(String exemType) {
		this.exemType = exemType;
	}

	public ArrayList getConsenterDetailsList() {
		return consenterDetailsList;
	}

	public void setConsenterDetailsList(ArrayList consenterDetailsList) {
		this.consenterDetailsList = consenterDetailsList;
	}

	public ArrayList getConsenterCompDetailsLst() {
		return consenterCompDetailsLst;
	}

	public void setConsenterCompDetailsLst(ArrayList consenterCompDetailsLst) {
		this.consenterCompDetailsLst = consenterCompDetailsLst;
	}

	public ArrayList getRelationshipList() {
		return relationshipList;
	}

	public void setRelationshipList(ArrayList relationshipList) {
		this.relationshipList = relationshipList;
	}

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
	
	///for reg init changes////
	private String pvReqFlag;
	private int particularCount;
	private int commonDeed;
	private int duty_txn_id;
	private int fromAdjudication;
	private int cancelDeedRadio;
	private int initiateAdjuApp;
	private String adjuRemarks;
	private String propReqFlag;
	private String fileNameOwm;
	
	public String getPvReqFlag() {
		return pvReqFlag;
	}

	public void setPvReqFlag(String pvReqFlag) {
		this.pvReqFlag = pvReqFlag;
	}

	public int getParticularCount() {
		return particularCount;
	}

	public void setParticularCount(int particularCount) {
		this.particularCount = particularCount;
	}

	

	public int getDuty_txn_id() {
		return duty_txn_id;
	}

	public void setDuty_txn_id(int duty_txn_id) {
		this.duty_txn_id = duty_txn_id;
	}

	public int getCommonDeed() {
		return commonDeed;
	}

	public void setCommonDeed(int commonDeed) {
		this.commonDeed = commonDeed;
	}

	public int getFromAdjudication() {
		return fromAdjudication;
	}

	public void setFromAdjudication(int fromAdjudication) {
		this.fromAdjudication = fromAdjudication;
	}

	public int getCancelDeedRadio() {
		return cancelDeedRadio;
	}

	public void setCancelDeedRadio(int cancelDeedRadio) {
		this.cancelDeedRadio = cancelDeedRadio;
	}

	public int getInitiateAdjuApp() {
		return initiateAdjuApp;
	}

	public void setInitiateAdjuApp(int initiateAdjuApp) {
		this.initiateAdjuApp = initiateAdjuApp;
	}

	public String getAdjuRemarks() {
		return adjuRemarks;
	}

	public void setAdjuRemarks(String adjuRemarks) {
		this.adjuRemarks = adjuRemarks;
	}

	public String getPropReqFlag() {
		return propReqFlag;
	}

	public void setPropReqFlag(String propReqFlag) {
		this.propReqFlag = propReqFlag;
	}

	public ArrayList getUserEnterableList() {
		return userEnterableList;
	}

	public void setUserEnterableList(ArrayList userEnterableList) {
		this.userEnterableList = userEnterableList;
	}

	public String getFinalDocPath() {
		return finalDocPath;
	}

	public void setFinalDocPath(String finalDocPath) {
		this.finalDocPath = finalDocPath;
	}

	public String getHeaderImg() {
		return headerImg;
	}

	public void setHeaderImg(String headerImg) {
		this.headerImg = headerImg;
	}

	public String getHoldChk() {
		return holdChk;
	}

	public void setHoldChk(String holdChk) {
		this.holdChk = holdChk;
	}

	public String getOwmChk() {
		return owmChk;
	}

	public void setOwmChk(String owmChk) {
		this.owmChk = owmChk;
	}

	public String getFileNameOwm() {
		return fileNameOwm;
	}

	public void setFileNameOwm(String fileNameOwm) {
		this.fileNameOwm = fileNameOwm;
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

	public String getCancellationLabel() {
		return cancellationLabel;
	}

	public void setCancellationLabel(String cancellationLabel) {
		this.cancellationLabel = cancellationLabel;
	}

	public void setRegSealCheck(String regSealCheck) {
		this.regSealCheck = regSealCheck;
	}

	public String getRegSealCheck() {
		return regSealCheck;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setAllowPrint(String allowPrint) {
		this.allowPrint = allowPrint;
	}

	public String getAllowPrint() {
		return allowPrint;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtp() {
		return otp;
	}
	public String consnterDuteisFlag;
	public String consnterAmount;
	public String consStamp;
	public String consMunicipal;
	public String consJanpad;
	public String consUpkar;
	public String consTotal;
	public String consRegFee;


	public String getConsnterDuteisFlag() {
		return consnterDuteisFlag;
	}

	public void setConsnterDuteisFlag(String consnterDuteisFlag) {
		this.consnterDuteisFlag = consnterDuteisFlag;
	}

	public String getConsnterAmount() {
		return consnterAmount;
	}

	public void setConsnterAmount(String consnterAmount) {
		this.consnterAmount = consnterAmount;
	}

	public String getConsStamp() {
		return consStamp;
	}

	public void setConsStamp(String consStamp) {
		this.consStamp = consStamp;
	}

	public String getConsMunicipal() {
		return consMunicipal;
	}

	public void setConsMunicipal(String consMunicipal) {
		this.consMunicipal = consMunicipal;
	}

	public String getConsJanpad() {
		return consJanpad;
	}

	public void setConsJanpad(String consJanpad) {
		this.consJanpad = consJanpad;
	}

	public String getConsUpkar() {
		return consUpkar;
	}

	public void setConsUpkar(String consUpkar) {
		this.consUpkar = consUpkar;
	}

	public String getConsTotal() {
		return consTotal;
	}

	public void setConsTotal(String consTotal) {
		this.consTotal = consTotal;
	}

	public String getConsRegFee() {
		return consRegFee;
	}

	public void setConsRegFee(String consRegFee) {
		this.consRegFee = consRegFee;
	}

	public void setDisableBook(String disableBook) {
		this.disableBook = disableBook;
	}

	public String getDisableBook() {
		return disableBook;
	}

	public void setExeSealCheck(String exeSealCheck) {
		this.exeSealCheck = exeSealCheck;
	}

	public String getExeSealCheck() {
		return exeSealCheck;
	}

	public void setCorrectionSeal(String correctionSeal) {
		this.correctionSeal = correctionSeal;
	}

	public String getCorrectionSeal() {
		return correctionSeal;
	}


}
