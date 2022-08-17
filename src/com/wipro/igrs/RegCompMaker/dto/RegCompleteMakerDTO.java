package com.wipro.igrs.RegCompMaker.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newreginit.dto.CommonDTO;

public class RegCompleteMakerDTO implements Serializable {
	
	
	
	
	//Start :===added for caveat Details
	
	private String transactionId;
	private String oldRegistrationDate;
	private String stayOrdrNum;
	private String stayOrdrDet;
	private String caveatDet;
	private String caveatStatus;
	private String stayOrdrStrtDate;
	private String stayOrdrUptoDate;
	private String propTxnLock;
	ArrayList propIDLocked = new ArrayList();
	private String regNumLocked;
	private String caveatName;
	private String denotingCheck;
	private String denotingFlag;
	private String linkingCheck;
	private String linkingFlag;
	public String getDenotingCheck() {
		return denotingCheck;
	}

	public void setDenotingCheck(String denotingCheck) {
		this.denotingCheck = denotingCheck;
	}

	public String getDenotingFlag() {
		return denotingFlag;
	}

	public void setDenotingFlag(String denotingFlag) {
		this.denotingFlag = denotingFlag;
	}

	public String getLinkingCheck() {
		return linkingCheck;
	}

	public void setLinkingCheck(String linkingCheck) {
		this.linkingCheck = linkingCheck;
	}

	public String getLinkingFlag() {
		return linkingFlag;
	}

	public void setLinkingFlag(String linkingFlag) {
		this.linkingFlag = linkingFlag;
	}
	private byte[] docContents;
	
	// added by Simran
	private String instituteName;
	private String caseNumber;
	private String isCase;
	public String getIsCase() {
		return isCase;
	}

	public void setIsCase(String isCase) {
		this.isCase = isCase;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	private String repName;
	private String caveatId;
	private String execDate;
	private String execRemarks;
	private String timePhotoPath;
	private String timePhotoPathUploaded;

	public String getTimePhotoPathUploaded() {
		return timePhotoPathUploaded;
	}

	public void setTimePhotoPathUploaded(String timePhotoPathUploaded) {
		this.timePhotoPathUploaded = timePhotoPathUploaded;
	}

	public String getExecDate() {
		return execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getExecRemarks() {
		return execRemarks;
	}

	public void setExecRemarks(String execRemarks) {
		this.execRemarks = execRemarks;
	}

	public String getTimePhotoPath() {
		return timePhotoPath;
	}

	public void setTimePhotoPath(String timePhotoPath) {
		this.timePhotoPath = timePhotoPath;
	}

	public String getTimePhotoName() {
		return timePhotoName;
	}

	public void setTimePhotoName(String timePhotoName) {
		this.timePhotoName = timePhotoName;
	}
	private String timePhotoName;
	private String uploadFlag;
	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	private String caveatLoggedDate;
	private int commonDeedFlag;                   // to check commonDeed
	private String consenterCheck;
	private String consenterVal;
	//End:Added for caveat Details
	
	//Start :===added for bank caveat Details
	
	
	private String cvtBnkTxnId;
	private String ottId;
	 private String loanAcntNum;
	 private String bnkName;
	 private String regId;
	 
	
	//end :===added for bank caveat Details
	
	//Start for payment page
	
	private double regfee;
	private double stampduty;
	private double totalamount;
	private String amtTextBox;
	
	
	
	//end for payment page
	
	
	private String partyId;
	private String indx;
	private String regNumber;
	private String instrumentId;
	private String exemptionId;
	private String deedId;
	private String deedTxnId;
	private String propId;
	private String hidnPropId;
	private String propIdInit;
	
	

	//Applicant Details
	private String fname;
    private String mname;
    private String lname;
    private String gender;
    private String age;
    private String indcountry;
	private String indstatename;
	private String inddistrict;
	private String nationality;
	private String indaddress;
	private String indphno;
	private String indmobno;
	private String emailID;
	private String listID;
	private String idno;
	private String bname;
	private String baddress;
	private String fatherName;
	private String motherName;
	private String postalCode;
	private String txtArea;// this stores any justification given by maker on the linking payment page
	private String pinNumber;
	
	
	
	//for presentation page
	private String partyTypeID;
	private String partyRoleTypeId;
	private String partyRole;
	// for both Hindi english RoleName - akansha
	
	private String partyRoleFullNamePOA ;
	 
	

	private String partyFirstName;
	private String partyLastName;
	private String witnessFirstName;
	private String witnessLastName;
	private String witnessSno;
	private String slctParty;
	private String slctWitness;
	ArrayList govOfficialList= new ArrayList();
	private String govtOffId;
	private String govtOffName;
	private String isGovtOfficial;
	private String partyThumbImpression;
	private String partySignature;
	private String witnessThumbImp;
	private String witnessSig;
	private String letterDet;
	// for middle name and Guardian - Rahul
	private String partyMiddleName;
	private String  Guaridan;
	
	public String getPartyMiddleName() {
		return partyMiddleName;
	}

	public void setPartyMiddleName(String partyMiddleName) {
		this.partyMiddleName = partyMiddleName;
	}

	public String getGuaridan() {
		return Guaridan;
	}

	public void setGuaridan(String guaridan) {
		Guaridan = guaridan;
	}
	//start : for compliance List
	private String typeOfArea;
	private String isPoa;
	private String deedId1;
	private String documentName;
	private String photoSize;
	private FormFile filePhoto = null;
    private FormFile fileThumb = null;
 
	private String tname;
	private String 	complianceId;
	
	//consenter fields are used in registration initiation. PLS DO NOT MODIFY...
	private String consenterFirstName;
	private String consenterAge;
	private String consenterRelation;
	private String consenterFatherName;
	private String consenterAddress;
	private String consenterLastName;
	private String consenterState;
	private String consenterDistrict;
	private String consenterPhotoId;
	private String consenterPhotoIdNumber;
	private String consenterSno;
	
	private String consenterStateName;
	private String consenterDistrictName;
	private String consenterPhotoIdName;
	private String consenterRelationName;
	private String consenterCountryName;
	
	private String consenterWithConsid="N";
	private String consenterConsidAmount;
	
	private ArrayList<CommonDTO> listDto = new ArrayList<CommonDTO>();
	
	//END FOR REG INIT- PLS DO NOT MODIFY CONSENTER VARIABLES. PLS

	private FormFile oldRedDoc = null;
	private FormFile propImg = null;
	private FormFile notarizedAffidavit=null;
	private FormFile propmap= null;
	private FormFile photoAffi=null;
	private FormFile poaDet=null;
	private FormFile rinPustika=null;
	private FormFile khasraNum = null;
	
	ArrayList thunmbName = new ArrayList();
	ArrayList oldRegDocLst = new ArrayList();
	ArrayList propImgLst = new ArrayList();
	ArrayList notAffiList = new ArrayList();
	ArrayList propMapLst = new ArrayList();
	ArrayList photoAffiLst = new ArrayList();
	ArrayList poaDetLst = new ArrayList();
	ArrayList rinPustikaLst = new ArrayList();
	ArrayList khasraNumLst = new ArrayList();
	ArrayList photoLst = new ArrayList();
	
	
	
	

	private String partyTxnId;
	private String photoTypeId;
	private String photoProofTypeName;
	private String partyName;
	private String slctRad;
	private String slctRad1;
	private String estampStatus;
	private String estampTxnId;
	private String isLinking;
	
	//Added by Anuj for government official check
	private String govOfcCheck;
	
	

	public ArrayList<CommonDTO> getListDto() {
		return listDto;
	}

	public void setListDto(ArrayList<CommonDTO> listDto) {
		this.listDto = listDto;
	}

	public String getConsenterWithConsid() {
		return consenterWithConsid;
	}

	public void setConsenterWithConsid(String consenterWithConsid) {
		this.consenterWithConsid = consenterWithConsid;
	}

	public String getConsenterConsidAmount() {
		return consenterConsidAmount;
	}

	public void setConsenterConsidAmount(String consenterConsidAmount) {
		this.consenterConsidAmount = consenterConsidAmount;
	}

	public String getConsenterStateName() {
		return consenterStateName;
	}

	public void setConsenterStateName(String consenterStateName) {
		this.consenterStateName = consenterStateName;
	}

	public String getConsenterDistrictName() {
		return consenterDistrictName;
	}

	public void setConsenterDistrictName(String consenterDistrictName) {
		this.consenterDistrictName = consenterDistrictName;
	}

	public String getConsenterPhotoIdName() {
		return consenterPhotoIdName;
	}

	public void setConsenterPhotoIdName(String consenterPhotoIdName) {
		this.consenterPhotoIdName = consenterPhotoIdName;
	}

	public String getConsenterRelationName() {
		return consenterRelationName;
	}

	public void setConsenterRelationName(String consenterRelationName) {
		this.consenterRelationName = consenterRelationName;
	}

	public String getConsenterCountryName() {
		return consenterCountryName;
	}

	public void setConsenterCountryName(String consenterCountryName) {
		this.consenterCountryName = consenterCountryName;
	}

	public String getConsenterAge() {
		return consenterAge;
	}

	public void setConsenterAge(String consenterAge) {
		this.consenterAge = consenterAge;
	}

	public String getConsenterRelation() {
		return consenterRelation;
	}

	public void setConsenterRelation(String consenterRelation) {
		this.consenterRelation = consenterRelation;
	}

	public String getConsenterFatherName() {
		return consenterFatherName;
	}

	public void setConsenterFatherName(String consenterFatherName) {
		this.consenterFatherName = consenterFatherName;
	}

	public String getConsenterAddress() {
		return consenterAddress;
	}

	public void setConsenterAddress(String consenterAddress) {
		this.consenterAddress = consenterAddress;
	}

	public String getConsenterState() {
		return consenterState;
	}

	public void setConsenterState(String consenterState) {
		this.consenterState = consenterState;
	}

	public String getConsenterDistrict() {
		return consenterDistrict;
	}

	public void setConsenterDistrict(String consenterDistrict) {
		this.consenterDistrict = consenterDistrict;
	}

	public String getConsenterPhotoId() {
		return consenterPhotoId;
	}

	public void setConsenterPhotoId(String consenterPhotoId) {
		this.consenterPhotoId = consenterPhotoId;
	}

	public String getConsenterPhotoIdNumber() {
		return consenterPhotoIdNumber;
	}

	public void setConsenterPhotoIdNumber(String consenterPhotoIdNumber) {
		this.consenterPhotoIdNumber = consenterPhotoIdNumber;
	}

	public String getGovOfcCheck() {
		return govOfcCheck;
	}

	public void setGovOfcCheck(String govOfcCheck) {
		this.govOfcCheck = govOfcCheck;
	}

	public String getIsLinking() {
		return isLinking;
	}
	private String isCavets;
	
	public String getIsCavets() {
		return isCavets;
	}

	public void setIsCavets(String isCavets) {
		this.isCavets = isCavets;
	}
	public void setIsLinking(String isLinking) {
		this.isLinking = isLinking;
	}

	public String getEstampStatus() {
		return estampStatus;
	}

	public void setEstampStatus(String estampStatus) {
		this.estampStatus = estampStatus;
	}

	public String getEstampTxnId() {
		return estampTxnId;
	}

	public void setEstampTxnId(String estampTxnId) {
		this.estampTxnId = estampTxnId;
	}

	public String getSlctRad1() {
		return slctRad1;
	}

	public void setSlctRad1(String slctRad1) {
		this.slctRad1 = slctRad1;
	}

	private String hdnCompArray;
	
	//end : for compliance List
	
	//start:===checklist
	
	FormFile deathCertificate=null;
	FormFile relationProof =null;
	FormFile poaDocument =null;
	
	private String upldDeathCert;
	private String uplaReltnProof;
	private String upldDeathCertPath;
	private String uplaReltnProofPath;
	
	private String exeDate;
	private String exeOutIndDate;
	private String ordrDate;
	private String dcreeOrderDate;
	private String lstAppealDate;
	private String poaAuthNum;
	private String comments;
	private String exeOutIndFlg;
	private String courtDecreeFlg;
	private String crtDcrWithAplFlg;
	private String caseCaveatFlg;
	private String docAfterDeathFlg;
	private String poaFlg;

	//end:==checklist
	private Double linkedTotalRegFee;
	private Double linkedTotalDuty;
	
	
	//Start : for HOld List
	
	public String holdId;
	public String holdName;
	public String continueHold;
	public String remarks;
	private String pinRequired;
	private String propIdSelected;
	public String getPropIdSelected() {
		return propIdSelected;
	}

	public void setPropIdSelected(String propIdSelected) {
		this.propIdSelected = propIdSelected;
	}

	public String getPinRequired() {
		return pinRequired;
	}

	public void setPinRequired(String pinRequired) {
		this.pinRequired = pinRequired;
	}

	public byte[] getDocContents() {
		return docContents;
	}

	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}

	public String getHoldId() {
		return holdId;
	}

	public void setHoldId(String holdId) {
		this.holdId = holdId;
	}

	public String getHoldName() {
		return holdName;
	}

	public void setHoldName(String holdName) {
		this.holdName = holdName;
	}

	public String getPartyRole() {
		return partyRole;
	}

	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}
	 public String getCvtBnkTxnId() {
			return cvtBnkTxnId;
		}

		public void setCvtBnkTxnId(String cvtBnkTxnId) {
			this.cvtBnkTxnId = cvtBnkTxnId;
		}

		public String getOttId() {
			return ottId;
		}

		public void setOttId(String ottId) {
			this.ottId = ottId;
		}

		public String getLoanAcntNum() {
			return loanAcntNum;
		}

		public void setLoanAcntNum(String loanAcntNum) {
			this.loanAcntNum = loanAcntNum;
		}

		public String getBnkName() {
			return bnkName;
		}

		public void setBnkName(String bnkName) {
			this.bnkName = bnkName;
		}

		public String getRegId() {
			return regId;
		}

		public void setRegId(String regId) {
			this.regId = regId;
		}
	

	public Double getLinkedTotalRegFee() {
		return linkedTotalRegFee;
	}

	public void setLinkedTotalRegFee(Double linkedTotalRegFee) {
		this.linkedTotalRegFee = linkedTotalRegFee;
	}

	public Double getLinkedTotalDuty() {
		return linkedTotalDuty;
	}

	public void setLinkedTotalDuty(Double linkedTotalDuty) {
		this.linkedTotalDuty = linkedTotalDuty;
	}

	public String getRegNumLocked() {
		return regNumLocked;
	}

	public ArrayList getPropIDLocked() {
		return propIDLocked;
	}

	public void setPropIDLocked(ArrayList propIDLocked) {
		this.propIDLocked = propIDLocked;
	}

	public void setRegNumLocked(String regNumLocked) {
		this.regNumLocked = regNumLocked;
	}

	public String getAmtTextBox() {
		return amtTextBox;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStayOrdrNum() {
		return stayOrdrNum;
	}

	public void setStayOrdrNum(String stayOrdrNum) {
		this.stayOrdrNum = stayOrdrNum;
	}

	public String getStayOrdrDet() {
		return stayOrdrDet;
	}

	public void setStayOrdrDet(String stayOrdrDet) {
		this.stayOrdrDet = stayOrdrDet;
	}

	

	public String getCaveatDet() {
		return caveatDet;
	}

	public void setCaveatDet(String caveatDet) {
		this.caveatDet = caveatDet;
	}

	public String getCaveatName() {
		return caveatName;
	}

	public void setCaveatName(String caveatName) {
		this.caveatName = caveatName;
	}

	public String getCaveatStatus() {
		return caveatStatus;
	}

	public void setCaveatStatus(String caveatStatus) {
		this.caveatStatus = caveatStatus;
	}

	
	public String getStayOrdrStrtDate() {
		return stayOrdrStrtDate;
	}

	public void setStayOrdrStrtDate(String stayOrdrStrtDate) {
		this.stayOrdrStrtDate = stayOrdrStrtDate;
	}

	public String getStayOrdrUptoDate() {
		return stayOrdrUptoDate;
	}

	public void setStayOrdrUptoDate(String stayOrdrUptoDate) {
		this.stayOrdrUptoDate = stayOrdrUptoDate;
	}

	public String getPropTxnLock() {
		return propTxnLock;
	}

	public void setPropTxnLock(String propTxnLock) {
		this.propTxnLock = propTxnLock;
	}

	public void setAmtTextBox(String amtTextBox) {
		this.amtTextBox = amtTextBox;
	}
	
	
	public String getPropIdInit() {
		return propIdInit;
	}

	public double getRegfee() {
		return regfee;
	}

	public void setRegfee(double regfee) {
		this.regfee = regfee;
	}

	public double getStampduty() {
		return stampduty;
	}

	public void setStampduty(double stampduty) {
		this.stampduty = stampduty;
	}

	public double getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}

	public void setPropIdInit(String propIdInit) {
		this.propIdInit = propIdInit;
	}
	
	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}

	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}

	
	public ArrayList getPhotoLst() {
		return photoLst;
	}

	public String getUpldDeathCert() {
		return upldDeathCert;
	}

	public void setUpldDeathCert(String upldDeathCert) {
		this.upldDeathCert = upldDeathCert;
	}

	public String getUplaReltnProof() {
		return uplaReltnProof;
	}

	public void setUplaReltnProof(String uplaReltnProof) {
		this.uplaReltnProof = uplaReltnProof;
	}

	public String getExeDate() {
		return exeDate;
	}

	public void setExeDate(String exeDate) {
		this.exeDate = exeDate;
	}

	public String getExeOutIndDate() {
		return exeOutIndDate;
	}

	public void setExeOutIndDate(String exeOutIndDate) {
		this.exeOutIndDate = exeOutIndDate;
	}

	public String getOrdrDate() {
		return ordrDate;
	}

	public void setOrdrDate(String ordrDate) {
		this.ordrDate = ordrDate;
	}

	public String getDcreeOrderDate() {
		return dcreeOrderDate;
	}

	public void setDcreeOrderDate(String dcreeOrderDate) {
		this.dcreeOrderDate = dcreeOrderDate;
	}

	public String getLstAppealDate() {
		return lstAppealDate;
	}

	public void setLstAppealDate(String lstAppealDate) {
		this.lstAppealDate = lstAppealDate;
	}

	public String getPoaAuthNum() {
		return poaAuthNum;
	}

	public void setPoaAuthNum(String poaAuthNum) {
		this.poaAuthNum = poaAuthNum;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getExeOutIndFlg() {
		return exeOutIndFlg;
	}

	public void setExeOutIndFlg(String exeOutIndFlg) {
		this.exeOutIndFlg = exeOutIndFlg;
	}

	public String getCourtDecreeFlg() {
		return courtDecreeFlg;
	}

	public void setCourtDecreeFlg(String courtDecreeFlg) {
		this.courtDecreeFlg = courtDecreeFlg;
	}

	public String getCrtDcrWithAplFlg() {
		return crtDcrWithAplFlg;
	}

	public void setCrtDcrWithAplFlg(String crtDcrWithAplFlg) {
		this.crtDcrWithAplFlg = crtDcrWithAplFlg;
	}

	public String getCaseCaveatFlg() {
		return caseCaveatFlg;
	}

	public void setCaseCaveatFlg(String caseCaveatFlg) {
		this.caseCaveatFlg = caseCaveatFlg;
	}

	public void setPhotoLst(ArrayList photoLst) {
		this.photoLst = photoLst;
	}

	 

	public FormFile getDeathCertificate() {
		return deathCertificate;
	}

	public void setDeathCertificate(FormFile deathCertificate) {
		this.deathCertificate = deathCertificate;
	}

	public FormFile getRelationProof() {
		return relationProof;
	}

	public void setRelationProof(FormFile relationProof) {
		this.relationProof = relationProof;
	}

	public String getHdnCompArray() {
		return hdnCompArray;
	}

	public void setHdnCompArray(String hdnCompArray) {
		this.hdnCompArray = hdnCompArray;
	}

	public String getSlctRad() {
		return slctRad;
	}

	public void setSlctRad(String slctRad) {
		this.slctRad = slctRad;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getComplianceId() {
		return complianceId;
	}

	public String getPartyTxnId() {
		return partyTxnId;
	}

	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}

	public String getPhotoTypeId() {
		return photoTypeId;
	}

	public void setPhotoTypeId(String photoTypeId) {
		this.photoTypeId = photoTypeId;
	}

	public String getPhotoProofTypeName() {
		return photoProofTypeName;
	}

	public void setPhotoProofTypeName(String photoProofTypeName) {
		this.photoProofTypeName = photoProofTypeName;
	}

	public String getIsPoa() {
		return isPoa;
	}

	public void setIsPoa(String isPoa) {
		this.isPoa = isPoa;
	}

	public String getDeedId1() {
		return deedId1;
	}

	public void setDeedId1(String deedId1) {
		this.deedId1 = deedId1;
	}

	public FormFile getRinPustika() {
		return rinPustika;
	}

	public void setRinPustika(FormFile rinPustika) {
		this.rinPustika = rinPustika;
	}

	public FormFile getKhasraNum() {
		return khasraNum;
	}

	public void setKhasraNum(FormFile khasraNum) {
		this.khasraNum = khasraNum;
	}

	public ArrayList getRinPustikaLst() {
		return rinPustikaLst;
	}

	public void setRinPustikaLst(ArrayList rinPustikaLst) {
		this.rinPustikaLst = rinPustikaLst;
	}

	public ArrayList getKhasraNumLst() {
		return khasraNumLst;
	}

	public void setKhasraNumLst(ArrayList khasraNumLst) {
		this.khasraNumLst = khasraNumLst;
	}

	public FormFile getPoaDet() {
		return poaDet;
	}

	public void setPoaDet(FormFile poaDet) {
		this.poaDet = poaDet;
	}

	public ArrayList getPoaDetLst() {
		return poaDetLst;
	}

	public void setPoaDetLst(ArrayList poaDetLst) {
		this.poaDetLst = poaDetLst;
	}

	public void setComplianceId(String complianceId) {
		this.complianceId = complianceId;
	}
	public FormFile getPhotoAffi() {
		return photoAffi;
	}

	public void setPhotoAffi(FormFile photoAffi) {
		this.photoAffi = photoAffi;
	}

	public ArrayList getPhotoAffiLst() {
		return photoAffiLst;
	}

	public void setPhotoAffiLst(ArrayList photoAffiLst) {
		this.photoAffiLst = photoAffiLst;
	}
	
	
	
	public FormFile getOldRedDoc() {
		return oldRedDoc;
	}

	public ArrayList getOldRegDocLst() {
		return oldRegDocLst;
	}

	public void setOldRegDocLst(ArrayList oldRegDocLst) {
		this.oldRegDocLst = oldRegDocLst;
	}

	public ArrayList getPropImgLst() {
		return propImgLst;
	}

	public void setPropImgLst(ArrayList propImgLst) {
		this.propImgLst = propImgLst;
	}

	public ArrayList getNotAffiList() {
		return notAffiList;
	}

	public void setNotAffiList(ArrayList notAffiList) {
		this.notAffiList = notAffiList;
	}

	public ArrayList getPropMapLst() {
		return propMapLst;
	}

	public void setPropMapLst(ArrayList propMapLst) {
		this.propMapLst = propMapLst;
	}

	public void setOldRedDoc(FormFile oldRedDoc) {
		this.oldRedDoc = oldRedDoc;
	}

	public FormFile getPropImg() {
		return propImg;
	}

	public void setPropImg(FormFile propImg) {
		this.propImg = propImg;
	}

	public FormFile getNotarizedAffidavit() {
		return notarizedAffidavit;
	}

	public void setNotarizedAffidavit(FormFile notarizedAffidavit) {
		this.notarizedAffidavit = notarizedAffidavit;
	}

	public FormFile getPropmap() {
		return propmap;
	}

	public void setPropmap(FormFile propmap) {
		this.propmap = propmap;
	}

	
	
	
	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	
	
	
	
	
	//for linking payment
	
	public String getTypeOfArea() {
		return typeOfArea;
	}

	public void setTypeOfArea(String typeOfArea) {
		this.typeOfArea = typeOfArea;
	}

	

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getPhotoSize() {
		return photoSize;
	}

	public void setPhotoSize(String photoSize) {
		this.photoSize = photoSize;
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

	public ArrayList getThunmbName() {
		return thunmbName;
	}

	public void setThunmbName(ArrayList thunmbName) {
		this.thunmbName = thunmbName;
	}

	public String getSlctWitness() {
		return slctWitness;
	}

	public void setSlctWitness(String slctWitness) {
		this.slctWitness = slctWitness;
	}

	public String getLetterDet() {
		return letterDet;
	}

	public void setLetterDet(String letterDet) {
		this.letterDet = letterDet;
	}

	public String getPartyThumbImpression() {
		return partyThumbImpression;
	}

	public void setPartyThumbImpression(String partyThumbImpression) {
		this.partyThumbImpression = partyThumbImpression;
	}

	public String getPartySignature() {
		return partySignature;
	}

	public void setPartySignature(String partySignature) {
		this.partySignature = partySignature;
	}

	public String getWitnessThumbImp() {
		return witnessThumbImp;
	}

	public void setWitnessThumbImp(String witnessThumbImp) {
		this.witnessThumbImp = witnessThumbImp;
	}

	public String getWitnessSig() {
		return witnessSig;
	}

	public void setWitnessSig(String witnessSig) {
		this.witnessSig = witnessSig;
	}

	public String getIsGovtOfficial() {
		return isGovtOfficial;
	}

	public void setIsGovtOfficial(String isGovtOfficial) {
		this.isGovtOfficial = isGovtOfficial;
	}

	public String getGovtOffId() {
		return govtOffId;
	}

	public void setGovtOffId(String govtOffId) {
		this.govtOffId = govtOffId;
	}

	public String getGovtOffName() {
		return govtOffName;
	}

	public void setGovtOffName(String govtOffName) {
		this.govtOffName = govtOffName;
	}

	public ArrayList getGovOfficialList() {
		return govOfficialList;
	}

	public void setGovOfficialList(ArrayList govOfficialList) {
		this.govOfficialList = govOfficialList;
	}

	public String getSlctParty() {
		return slctParty;
	}

	public void setSlctParty(String slctParty) {
		this.slctParty = slctParty;
	}

	public String getPartyTypeID() {
		return partyTypeID;
	}

	public void setPartyTypeID(String partyTypeID) {
		this.partyTypeID = partyTypeID;
	}

	public String getPartyFirstName() {
		return partyFirstName;
	}

	public void setPartyFirstName(String partyFirstName) {
		this.partyFirstName = partyFirstName;
	}

	public String getPartyLastName() {
		return partyLastName;
	}

	public void setPartyLastName(String partyLastName) {
		this.partyLastName = partyLastName;
	}

	public String getWitnessFirstName() {
		return witnessFirstName;
	}

	public void setWitnessFirstName(String witnessFirstName) {
		this.witnessFirstName = witnessFirstName;
	}

	public String getWitnessLastName() {
		return witnessLastName;
	}

	public void setWitnessLastName(String witnessLastName) {
		this.witnessLastName = witnessLastName;
	}

	public String getWitnessSno() {
		return witnessSno;
	}

	public void setWitnessSno(String witnessSno) {
		this.witnessSno = witnessSno;
	}

	
	
	public String getConsenterFirstName() {
		return consenterFirstName;
	}

	public void setConsenterFirstName(String consenterFirstName) {
		this.consenterFirstName = consenterFirstName;
	}

	public String getConsenterLastName() {
		return consenterLastName;
	}

	public void setConsenterLastName(String consenterLastName) {
		this.consenterLastName = consenterLastName;
	}

	public String getConsenterSno() {
		return consenterSno;
	}

	public void setConsenterSno(String consenterSno) {
		this.consenterSno = consenterSno;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	private String oldRegNumber; // this registration number is the reg number of the registered associated executed deed
	
	
	
	public String getTxtArea() {
		return txtArea;
	}

	public void setTxtArea(String txtArea) {
		this.txtArea = txtArea;
	}

	public String getHidnPropId() {
		return hidnPropId;
	}

	public void setHidnPropId(String hidnPropId) {
		this.hidnPropId = hidnPropId;
	}

	public String getOldRegNumber() {
		return oldRegNumber;
	}

	public void setOldRegNumber(String oldRegNumber) {
		this.oldRegNumber = oldRegNumber;
	}

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getDeedTxnId() {
		return deedTxnId;
	}

	public void setDeedTxnId(String deedTxnId) {
		this.deedTxnId = deedTxnId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public ArrayList mapList = new ArrayList();


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
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

	public String getIndcountry() {
		return indcountry;
	}

	public void setIndcountry(String indcountry) {
		this.indcountry = indcountry;
	}

	public String getIndstatename() {
		return indstatename;
	}

	public void setIndstatename(String indstatename) {
		this.indstatename = indstatename;
	}

	public String getInddistrict() {
		return inddistrict;
	}

	public void setInddistrict(String inddistrict) {
		this.inddistrict = inddistrict;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getIndaddress() {
		return indaddress;
	}

	public void setIndaddress(String indaddress) {
		this.indaddress = indaddress;
	}

	public String getIndphno() {
		return indphno;
	}

	public void setIndphno(String indphno) {
		this.indphno = indphno;
	}

	public String getIndmobno() {
		return indmobno;
	}

	public void setIndmobno(String indmobno) {
		this.indmobno = indmobno;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getListID() {
		return listID;
	}

	public void setListID(String listID) {
		this.listID = listID;
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBaddress() {
		return baddress;
	}

	public void setBaddress(String baddress) {
		this.baddress = baddress;
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

	public ArrayList getMapList() {
		return mapList;
	}

	public void setMapList(ArrayList mapList) {
		this.mapList = mapList;
	}

	public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public String getExemptionId() {
		return exemptionId;
	}

	public void setExemptionId(String exemptionId) {
		this.exemptionId = exemptionId;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	
	//ADDED BY SIMRAN
	
	private String checker;
	private String chkChecker;
	private String deedDocName;
	private String deedDocPath;

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getChkChecker() {
		return chkChecker;
	}

	public void setChkChecker(String chkChecker) {
		this.chkChecker = chkChecker;
	}
	
	
	// Added by Simran For maker
	private double dutyAlreadyPaid;
	private double regFeeAlreadyPaid;
	private String regStampArr;
	private String storeAction;
	private String deedID;
	private String pendingTaxDuties;
	private String loanInfo;
	private String chargeOrCase;
	private String rightToSale;
	private byte deatCertContents[];
	private byte reltnProofContents[];
	private byte poaUploadContents[];
	private String deathCertSize;
	private String reltnProofSize;
	private String poaUploadSize;
	private String filePath;
	private String filePathRltn;
	private String uploadPoaDoc;
	private String uploadPoaDocPath;
	private String filePathPOA;
	private String linkCheck;
	private String scanName;
	private String scanPath;
	private String uploadAction;
	
	public String getUploadAction() {
		return uploadAction;
	}

	public void setUploadAction(String uploadAction) {
		this.uploadAction = uploadAction;
	}

	public String getScanName() {
		return scanName;
	}

	public void setScanName(String scanName) {
		this.scanName = scanName;
	}

	public String getScanPath() {
		return scanPath;
	}

	public void setScanPath(String scanPath) {
		this.scanPath = scanPath;
	}

	public double getDutyAlreadyPaid() {
		return dutyAlreadyPaid;
	}

	public void setDutyAlreadyPaid(double dutyAlreadyPaid) {
		this.dutyAlreadyPaid = dutyAlreadyPaid;
	}

	public double getRegFeeAlreadyPaid() {
		return regFeeAlreadyPaid;
	}

	public void setRegFeeAlreadyPaid(double regFeeAlreadyPaid) {
		this.regFeeAlreadyPaid = regFeeAlreadyPaid;
	}

	public String getRegStampArr() {
		return regStampArr;
	}

	public void setRegStampArr(String regStampArr) {
		this.regStampArr = regStampArr;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getRepName() {
		return repName;
	}

	public void setRepName(String repName) {
		this.repName = repName;
	}

	public String getCaveatId() {
		return caveatId;
	}

	public void setCaveatId(String caveatId) {
		this.caveatId = caveatId;
	}

	public String getCaveatLoggedDate() {
		return caveatLoggedDate;
	}

	public void setCaveatLoggedDate(String caveatLoggedDate) {
		this.caveatLoggedDate = caveatLoggedDate;
	}

	public String getStoreAction() {
		return storeAction;
	}

	public void setStoreAction(String storeAction) {
		this.storeAction = storeAction;
	}

	public String getDeedID() {
		return deedID;
	}

	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}

	public String getPendingTaxDuties() {
		return pendingTaxDuties;
	}

	public void setPendingTaxDuties(String pendingTaxDuties) {
		this.pendingTaxDuties = pendingTaxDuties;
	}

	public String getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(String loanInfo) {
		this.loanInfo = loanInfo;
	}

	public String getChargeOrCase() {
		return chargeOrCase;
	}

	public void setChargeOrCase(String chargeOrCase) {
		this.chargeOrCase = chargeOrCase;
	}

	public String getRightToSale() {
		return rightToSale;
	}

	public void setRightToSale(String rightToSale) {
		this.rightToSale = rightToSale;
	}

	public byte[] getDeatCertContents() {
		return deatCertContents;
	}

	public void setDeatCertContents(byte[] deatCertContents) {
		this.deatCertContents = deatCertContents;
	}

	public byte[] getReltnProofContents() {
		return reltnProofContents;
	}

	public void setReltnProofContents(byte[] reltnProofContents) {
		this.reltnProofContents = reltnProofContents;
	}

	public String getDeathCertSize() {
		return deathCertSize;
	}

	public void setDeathCertSize(String deathCertSize) {
		this.deathCertSize = deathCertSize;
	}

	public String getReltnProofSize() {
		return reltnProofSize;
	}

	public void setReltnProofSize(String reltnProofSize) {
		this.reltnProofSize = reltnProofSize;
	}

	public String getDocAfterDeathFlg() {
		return docAfterDeathFlg;
	}

	public void setDocAfterDeathFlg(String docAfterDeathFlg) {
		this.docAfterDeathFlg = docAfterDeathFlg;
	}

	public String getPoaFlg() {
		return poaFlg;
	}

	public void setPoaFlg(String poaFlg) {
		this.poaFlg = poaFlg;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePathRltn() {
		return filePathRltn;
	}

	public void setFilePathRltn(String filePathRltn) {
		this.filePathRltn = filePathRltn;
	}

	public byte[] getPoaUploadContents() {
		return poaUploadContents;
	}

	public void setPoaUploadContents(byte[] poaUploadContents) {
		this.poaUploadContents = poaUploadContents;
	}

	public String getPoaUploadSize() {
		return poaUploadSize;
	}

	public void setPoaUploadSize(String poaUploadSize) {
		this.poaUploadSize = poaUploadSize;
	}

	public String getUploadPoaDoc() {
		return uploadPoaDoc;
	}

	public void setUploadPoaDoc(String uploadPoaDoc) {
		this.uploadPoaDoc = uploadPoaDoc;
	}

	public FormFile getPoaDocument() {
		return poaDocument;
	}

	public void setPoaDocument(FormFile poaDocument) {
		this.poaDocument = poaDocument;
	}

	public String getFilePathPOA() {
		return filePathPOA;
	}

	public void setFilePathPOA(String filePathPOA) {
		this.filePathPOA = filePathPOA;
	}
//**************************************COMPLIANCE*******************************************//
	private FormFile propAngle1[] = new FormFile[100];
	private FormFile propAngle2[] = new FormFile[100];
	private FormFile propAngle3[] = new FormFile[100];
	private FormFile propMap[] = new FormFile[100];
	private FormFile propKhasra[] = new FormFile[100];
	private FormFile propRin[] = new FormFile[100];
	private FormFile partyCollectorCertDoc[] = new FormFile[100];
	private FormFile partyPhotoProofDoc[] = new FormFile[100];
	private FormFile partyNotAffdExecDoc[] = new FormFile[100];
	private FormFile partyExecPhoto[] = new FormFile[100];
	private FormFile partyNotAffdAttr[] = new FormFile[100];
	private FormFile partyAttrPhoto[] = new FormFile[100];
	private FormFile partyClaimntPhoto[] = new FormFile[100];
	private FormFile partyPanForm60[] = new FormFile[100];
	private FormFile witnessPhotograph[] = new FormFile[100];
	private String witnessDocName;
	private String witnessDocPath;
	private byte[] witnessDocContents;
	private String witnessDocSize;
	private String witnessDocName1[] = new String[100];

	
	private String collectorCertDocName;
	private String collectorCertDocPath;
	private String photoProofDocName;
	private String photoProofDocPath;
	private String notAffdExecDocName;
	private String notAffdExecDocPath;
	private String execPhotoName;
	private String execPhotoPath;
	private String notAffdAttrName;
	private String notAffdAttrPath;
	private String attrPhotoPath;
	private String attrPhotoName;
	private String claimntPhotoName;
	private String claimntPhotoPath;
	private String panForm60Name;
	private String panForm60Path;
	 private FormFile propImage1 = null;
		private String propImage1DocumentName;
		private String propImage1DocumentSize;
		private byte[] propImage1DocContents;
		//private String prop1PhotoCheck;
		private String propImage1FilePath;
		private String propImage1PartyOrProp;
		private String propImage1UploadType;
		
		private FormFile propImage2 = null;
		private String propImage2DocumentName;
		private String propImage2DocumentSize;
		private byte[] propImage2DocContents;
		//private String prop1PhotoCheck;
		private String propImage2FilePath;
		private String propImage2PartyOrProp;
		private String propImage2UploadType;
		
		private FormFile propImage3 = null;
		private String propImage3DocumentName;
		private String propImage3DocumentSize;
		private byte[] propImage3DocContents;
		//private String prop1PhotoCheck;
		private String propImage3FilePath;
		private String propImage3PartyOrProp;
		private String propImage3UploadType;

		private FormFile propertyMap = null;
		private String propMapDocumentName;
		private String propMapDocumentSize;
		private byte[] propMapDocContents;
		//private String prop1PhotoCheck;
		private String propMapFilePath;
		private String propMapPartyOrProp;
		private String propMapUploadType;
		
		
		private FormFile propertyRin = null;
		private String propRinDocumentName;
		private String propRinDocumentSize;
		private byte[] propRinDocContents;
		//private String prop1PhotoCheck;
		private String propRinFilePath;
		private String propRinPartyOrProp;
		private String propRinUploadType;
		
		
		private FormFile propertyKhasra = null;
		private String propKhasraDocumentName;
		private String propKhasraDocumentSize;
		private byte[] propKhasraDocContents;
		//private String prop1PhotoCheck;
		private String propKhasraFilePath;
		private String propKhasraPartyOrProp;
		private String propKhasraUploadType;
		private String isUpload;
		private String propertyTypeId;
		private String role;
		private String partyRoleId;
		private String witnessTxnNummber;
		private String fnameTrns;
		private String lnameTrns;
		private String roleName;
		private String selectedWitnessTxnNummber;
		
		private String oldPropId;
		private String oldRegNoLink;
		private ArrayList linkingDashboardList;
		public String getOldPropId() {
			return oldPropId;
		}

		public void setOldPropId(String oldPropId) {
			this.oldPropId = oldPropId;
		}

		public String getOldRegNoLink() {
			return oldRegNoLink;
		}

		public void setOldRegNoLink(String oldRegNoLink) {
			this.oldRegNoLink = oldRegNoLink;
		}

		public String getNewPropId() {
			return newPropId;
		}

		public void setNewPropId(String newPropId) {
			this.newPropId = newPropId;
		}

		public String getPinLinkStatus() {
			return pinLinkStatus;
		}

		public void setPinLinkStatus(String pinLinkStatus) {
			this.pinLinkStatus = pinLinkStatus;
		}
		private String newPropId;
		private String pinLinkStatus; 
		
	public FormFile[] getPropAngle1() {
		return propAngle1;
	}

	public FormFile[] getPropAngle2() {
		return propAngle2;
	}

	public void setPropAngle2(FormFile[] propAngle2) {
		this.propAngle2 = propAngle2;
	}

	public FormFile[] getPropAngle3() {
		return propAngle3;
	}

	public void setPropAngle3(FormFile[] propAngle3) {
		this.propAngle3 = propAngle3;
	}

	public FormFile[] getPropMap() {
		return propMap;
	}

	public void setPropMap(FormFile[] propMap) {
		this.propMap = propMap;
	}

	public FormFile[] getPropKhasra() {
		return propKhasra;
	}

	public void setPropKhasra(FormFile[] propKhasra) {
		this.propKhasra = propKhasra;
	}

	public FormFile[] getPropRin() {
		return propRin;
	}

	public void setPropRin(FormFile[] propRin) {
		this.propRin = propRin;
	}

	public FormFile[] getPartyCollectorCertDoc() {
		return partyCollectorCertDoc;
	}

	public void setPartyCollectorCertDoc(FormFile[] partyCollectorCertDoc) {
		this.partyCollectorCertDoc = partyCollectorCertDoc;
	}

	public FormFile[] getPartyPhotoProofDoc() {
		return partyPhotoProofDoc;
	}

	public void setPartyPhotoProofDoc(FormFile[] partyPhotoProofDoc) {
		this.partyPhotoProofDoc = partyPhotoProofDoc;
	}

	public FormFile[] getPartyNotAffdExecDoc() {
		return partyNotAffdExecDoc;
	}

	public void setPartyNotAffdExecDoc(FormFile[] partyNotAffdExecDoc) {
		this.partyNotAffdExecDoc = partyNotAffdExecDoc;
	}

	public FormFile[] getPartyExecPhoto() {
		return partyExecPhoto;
	}

	public void setPartyExecPhoto(FormFile[] partyExecPhoto) {
		this.partyExecPhoto = partyExecPhoto;
	}

	public FormFile[] getPartyNotAffdAttr() {
		return partyNotAffdAttr;
	}

	public void setPartyNotAffdAttr(FormFile[] partyNotAffdAttr) {
		this.partyNotAffdAttr = partyNotAffdAttr;
	}

	public FormFile[] getPartyAttrPhoto() {
		return partyAttrPhoto;
	}

	public void setPartyAttrPhoto(FormFile[] partyAttrPhoto) {
		this.partyAttrPhoto = partyAttrPhoto;
	}

	public FormFile[] getPartyClaimntPhoto() {
		return partyClaimntPhoto;
	}

	public void setPartyClaimntPhoto(FormFile[] partyClaimntPhoto) {
		this.partyClaimntPhoto = partyClaimntPhoto;
	}

	public FormFile[] getPartyPanForm60() {
		return partyPanForm60;
	}

	public void setPartyPanForm60(FormFile[] partyPanForm60) {
		this.partyPanForm60 = partyPanForm60;
	}

	public String[] getWitnessDocName1() {
		return witnessDocName1;
	}

	public void setWitnessDocName1(String[] witnessDocName1) {
		this.witnessDocName1 = witnessDocName1;
	}

	public void setPropAngle1(FormFile[] propAngle1) {
		this.propAngle1 = propAngle1;
	}
	
	public FormFile getPropAngle1(int index) {
		return propAngle1[index];
	}

	public void setPropAngle1(int index,FormFile propAngle1) {
		this.propAngle1[index] = propAngle1;
	}

	public FormFile getPropAngle2(int index) {
		return propAngle2[index];
	}

	public void setPropAngle2(int index,FormFile propAngle2) {
		this.propAngle2[index] = propAngle2;
	}

	public FormFile getPropAngle3(int index) {
		return propAngle3[index];
	}

	public void setPropAngle3(int index,FormFile propAngle3) {
		this.propAngle3[index] = propAngle3;
	}

	public FormFile getPropMap(int index) {
		return propMap[index];
	}

	public void setPropMap(int index,FormFile propMap) {
		this.propMap[index] = propMap;
	}

	public FormFile getPropKhasra(int index) {
		return propKhasra[index];
	}

	public void setPropKhasra(int index,FormFile propKhasra) {
		this.propKhasra[index] = propKhasra;
	}

	public FormFile getPropRin(int index) {
		return propRin[index];
	}

	public void setPropRin(int index,FormFile propRin) {
		this.propRin[index] = propRin;
	}

	public String getCollectorCertDocName() {
		return collectorCertDocName;
	}

	public void setCollectorCertDocName(String collectorCertDocName) {
		this.collectorCertDocName = collectorCertDocName;
	}

	public String getCollectorCertDocPath() {
		return collectorCertDocPath;
	}

	public void setCollectorCertDocPath(String collectorCertDocPath) {
		this.collectorCertDocPath = collectorCertDocPath;
	}

	public String getPhotoProofDocName() {
		return photoProofDocName;
	}

	public void setPhotoProofDocName(String photoProofDocName) {
		this.photoProofDocName = photoProofDocName;
	}

	public String getPhotoProofDocPath() {
		return photoProofDocPath;
	}

	public void setPhotoProofDocPath(String photoProofDocPath) {
		this.photoProofDocPath = photoProofDocPath;
	}

	public String getNotAffdExecDocName() {
		return notAffdExecDocName;
	}

	public void setNotAffdExecDocName(String notAffdExecDocName) {
		this.notAffdExecDocName = notAffdExecDocName;
	}

	public String getNotAffdExecDocPath() {
		return notAffdExecDocPath;
	}

	public void setNotAffdExecDocPath(String notAffdExecDocPath) {
		this.notAffdExecDocPath = notAffdExecDocPath;
	}

	public String getExecPhotoName() {
		return execPhotoName;
	}

	public void setExecPhotoName(String execPhotoName) {
		this.execPhotoName = execPhotoName;
	}

	public String getExecPhotoPath() {
		return execPhotoPath;
	}

	public void setExecPhotoPath(String execPhotoPath) {
		this.execPhotoPath = execPhotoPath;
	}

	public String getNotAffdAttrName() {
		return notAffdAttrName;
	}

	public void setNotAffdAttrName(String notAffdAttrName) {
		this.notAffdAttrName = notAffdAttrName;
	}

	public String getNotAffdAttrPath() {
		return notAffdAttrPath;
	}

	public void setNotAffdAttrPath(String notAffdAttrPath) {
		this.notAffdAttrPath = notAffdAttrPath;
	}

	public String getAttrPhotoPath() {
		return attrPhotoPath;
	}

	public void setAttrPhotoPath(String attrPhotoPath) {
		this.attrPhotoPath = attrPhotoPath;
	}

	public String getAttrPhotoName() {
		return attrPhotoName;
	}

	public void setAttrPhotoName(String attrPhotoName) {
		this.attrPhotoName = attrPhotoName;
	}

	public String getClaimntPhotoName() {
		return claimntPhotoName;
	}

	public void setClaimntPhotoName(String claimntPhotoName) {
		this.claimntPhotoName = claimntPhotoName;
	}

	public String getClaimntPhotoPath() {
		return claimntPhotoPath;
	}

	public void setClaimntPhotoPath(String claimntPhotoPath) {
		this.claimntPhotoPath = claimntPhotoPath;
	}

	public String getPanForm60Name() {
		return panForm60Name;
	}

	public void setPanForm60Name(String panForm60Name) {
		this.panForm60Name = panForm60Name;
	}

	public String getPanForm60Path() {
		return panForm60Path;
	}

	public void setPanForm60Path(String panForm60Path) {
		this.panForm60Path = panForm60Path;
	}

	public FormFile getPartyCollectorCertDoc(int index) {
		return partyCollectorCertDoc[index];
	}

	public void setPartyCollectorCertDoc(int index,FormFile partyCollectorCertDoc) {
		this.partyCollectorCertDoc[index] = partyCollectorCertDoc;
	}

	public FormFile getPartyPhotoProofDoc(int index) {
		return partyPhotoProofDoc[index];
	}

	public void setPartyPhotoProofDoc(int index,FormFile partyPhotoProofDoc) {
		this.partyPhotoProofDoc[index]= partyPhotoProofDoc;
	}

	public FormFile getPartyNotAffdExecDoc(int index) {
		return partyNotAffdExecDoc[index];
	}

	public void setPartyNotAffdExecDoc(int index,FormFile partyNotAffdExecDoc) {
		this.partyNotAffdExecDoc[index] = partyNotAffdExecDoc;
	}

	public FormFile getPartyExecPhoto(int index) {
		return partyExecPhoto[index];
	}

	public void setPartyExecPhoto(int index,FormFile partyExecPhoto) {
		this.partyExecPhoto[index] = partyExecPhoto;
	}

	public FormFile getPartyNotAffdAttr(int index) {
		return partyNotAffdAttr[index];
	}

	public void setPartyNotAffdAttr(int index,FormFile partyNotAffdAttr) {
		this.partyNotAffdAttr[index] = partyNotAffdAttr;
	}

	public FormFile getPartyAttrPhoto(int index) {
		return partyAttrPhoto[index];
	}

	public void setPartyAttrPhoto(int index,FormFile partyAttrPhoto) {
		this.partyAttrPhoto[index] = partyAttrPhoto;
	}

	public FormFile getPartyClaimntPhoto(int index) {
		return partyClaimntPhoto[index];
	}

	public void setPartyClaimntPhoto(int index,FormFile partyClaimntPhoto) {
		this.partyClaimntPhoto[index] = partyClaimntPhoto;
	}

	public FormFile getPartyPanForm60(int index) {
		return partyPanForm60[index];
	}

	public void setPartyPanForm60(int index,FormFile partyPanForm60) {
		this.partyPanForm60[index] = partyPanForm60;
	}

	public FormFile[] getWitnessPhotograph() {
		return witnessPhotograph;
	}

	public void setWitnessPhotograph(FormFile[] witnessPhotograph) {
		this.witnessPhotograph = witnessPhotograph;
	}

	public String getWitnessDocName() {
		return witnessDocName;
	}

	public void setWitnessDocName(String witnessDocName) {
		this.witnessDocName = witnessDocName;
	}

	public String getWitnessDocPath() {
		return witnessDocPath;
	}

	public void setWitnessDocPath(String witnessDocPath) {
		this.witnessDocPath = witnessDocPath;
	}

	public byte[] getWitnessDocContents() {
		return witnessDocContents;
	}

	public void setWitnessDocContents(byte[] witnessDocContents) {
		this.witnessDocContents = witnessDocContents;
	}

	public String getWitnessDocSize() {
		return witnessDocSize;
	}

	public void setWitnessDocSize(String witnessDocSize) {
		this.witnessDocSize = witnessDocSize;
	}

	public String getWitnessDocName1(int index) {
		return witnessDocName1[index];
	}

	public void setWitnessDocName1(String witnessDocName1, int index) {
		this.witnessDocName1[index] = witnessDocName1;
	}

	public FormFile getPropImage1() {
		return propImage1;
	}

	public void setPropImage1(FormFile propImage1) {
		this.propImage1 = propImage1;
	}

	public String getPropImage1DocumentName() {
		return propImage1DocumentName;
	}

	public void setPropImage1DocumentName(String propImage1DocumentName) {
		this.propImage1DocumentName = propImage1DocumentName;
	}

	public String getPropImage1DocumentSize() {
		return propImage1DocumentSize;
	}

	public void setPropImage1DocumentSize(String propImage1DocumentSize) {
		this.propImage1DocumentSize = propImage1DocumentSize;
	}

	public byte[] getPropImage1DocContents() {
		return propImage1DocContents;
	}

	public void setPropImage1DocContents(byte[] propImage1DocContents) {
		this.propImage1DocContents = propImage1DocContents;
	}

	public String getPropImage1FilePath() {
		return propImage1FilePath;
	}

	public void setPropImage1FilePath(String propImage1FilePath) {
		this.propImage1FilePath = propImage1FilePath;
	}

	public String getPropImage1PartyOrProp() {
		return propImage1PartyOrProp;
	}

	public void setPropImage1PartyOrProp(String propImage1PartyOrProp) {
		this.propImage1PartyOrProp = propImage1PartyOrProp;
	}

	public String getPropImage1UploadType() {
		return propImage1UploadType;
	}

	public void setPropImage1UploadType(String propImage1UploadType) {
		this.propImage1UploadType = propImage1UploadType;
	}

	public FormFile getPropImage2() {
		return propImage2;
	}

	public void setPropImage2(FormFile propImage2) {
		this.propImage2 = propImage2;
	}

	public String getPropImage2DocumentName() {
		return propImage2DocumentName;
	}

	public void setPropImage2DocumentName(String propImage2DocumentName) {
		this.propImage2DocumentName = propImage2DocumentName;
	}

	public String getPropImage2DocumentSize() {
		return propImage2DocumentSize;
	}

	public void setPropImage2DocumentSize(String propImage2DocumentSize) {
		this.propImage2DocumentSize = propImage2DocumentSize;
	}

	public byte[] getPropImage2DocContents() {
		return propImage2DocContents;
	}

	public void setPropImage2DocContents(byte[] propImage2DocContents) {
		this.propImage2DocContents = propImage2DocContents;
	}

	public String getPropImage2FilePath() {
		return propImage2FilePath;
	}

	public void setPropImage2FilePath(String propImage2FilePath) {
		this.propImage2FilePath = propImage2FilePath;
	}

	public String getPropImage2PartyOrProp() {
		return propImage2PartyOrProp;
	}

	public void setPropImage2PartyOrProp(String propImage2PartyOrProp) {
		this.propImage2PartyOrProp = propImage2PartyOrProp;
	}

	public String getPropImage2UploadType() {
		return propImage2UploadType;
	}

	public void setPropImage2UploadType(String propImage2UploadType) {
		this.propImage2UploadType = propImage2UploadType;
	}

	public FormFile getPropImage3() {
		return propImage3;
	}

	public void setPropImage3(FormFile propImage3) {
		this.propImage3 = propImage3;
	}

	public String getPropImage3DocumentName() {
		return propImage3DocumentName;
	}

	public void setPropImage3DocumentName(String propImage3DocumentName) {
		this.propImage3DocumentName = propImage3DocumentName;
	}

	public String getPropImage3DocumentSize() {
		return propImage3DocumentSize;
	}

	public void setPropImage3DocumentSize(String propImage3DocumentSize) {
		this.propImage3DocumentSize = propImage3DocumentSize;
	}

	public byte[] getPropImage3DocContents() {
		return propImage3DocContents;
	}

	public void setPropImage3DocContents(byte[] propImage3DocContents) {
		this.propImage3DocContents = propImage3DocContents;
	}

	public String getPropImage3FilePath() {
		return propImage3FilePath;
	}

	public void setPropImage3FilePath(String propImage3FilePath) {
		this.propImage3FilePath = propImage3FilePath;
	}

	public String getPropImage3PartyOrProp() {
		return propImage3PartyOrProp;
	}

	public void setPropImage3PartyOrProp(String propImage3PartyOrProp) {
		this.propImage3PartyOrProp = propImage3PartyOrProp;
	}

	public String getPropImage3UploadType() {
		return propImage3UploadType;
	}

	public void setPropImage3UploadType(String propImage3UploadType) {
		this.propImage3UploadType = propImage3UploadType;
	}

	public FormFile getPropertyMap() {
		return propertyMap;
	}

	public void setPropertyMap(FormFile propertyMap) {
		this.propertyMap = propertyMap;
	}

	public String getPropMapDocumentName() {
		return propMapDocumentName;
	}

	public void setPropMapDocumentName(String propMapDocumentName) {
		this.propMapDocumentName = propMapDocumentName;
	}

	public String getPropMapDocumentSize() {
		return propMapDocumentSize;
	}

	public void setPropMapDocumentSize(String propMapDocumentSize) {
		this.propMapDocumentSize = propMapDocumentSize;
	}

	public byte[] getPropMapDocContents() {
		return propMapDocContents;
	}

	public void setPropMapDocContents(byte[] propMapDocContents) {
		this.propMapDocContents = propMapDocContents;
	}

	public String getPropMapFilePath() {
		return propMapFilePath;
	}

	public void setPropMapFilePath(String propMapFilePath) {
		this.propMapFilePath = propMapFilePath;
	}

	public String getPropMapPartyOrProp() {
		return propMapPartyOrProp;
	}

	public void setPropMapPartyOrProp(String propMapPartyOrProp) {
		this.propMapPartyOrProp = propMapPartyOrProp;
	}

	public String getPropMapUploadType() {
		return propMapUploadType;
	}

	public void setPropMapUploadType(String propMapUploadType) {
		this.propMapUploadType = propMapUploadType;
	}

	public FormFile getPropertyRin() {
		return propertyRin;
	}

	public void setPropertyRin(FormFile propertyRin) {
		this.propertyRin = propertyRin;
	}

	public String getPropRinDocumentName() {
		return propRinDocumentName;
	}

	public void setPropRinDocumentName(String propRinDocumentName) {
		this.propRinDocumentName = propRinDocumentName;
	}

	public String getPropRinDocumentSize() {
		return propRinDocumentSize;
	}

	public void setPropRinDocumentSize(String propRinDocumentSize) {
		this.propRinDocumentSize = propRinDocumentSize;
	}

	public byte[] getPropRinDocContents() {
		return propRinDocContents;
	}

	public void setPropRinDocContents(byte[] propRinDocContents) {
		this.propRinDocContents = propRinDocContents;
	}

	public String getPropRinFilePath() {
		return propRinFilePath;
	}

	public void setPropRinFilePath(String propRinFilePath) {
		this.propRinFilePath = propRinFilePath;
	}

	public String getPropRinPartyOrProp() {
		return propRinPartyOrProp;
	}

	public void setPropRinPartyOrProp(String propRinPartyOrProp) {
		this.propRinPartyOrProp = propRinPartyOrProp;
	}

	public String getPropRinUploadType() {
		return propRinUploadType;
	}

	public void setPropRinUploadType(String propRinUploadType) {
		this.propRinUploadType = propRinUploadType;
	}

	public FormFile getPropertyKhasra() {
		return propertyKhasra;
	}

	public void setPropertyKhasra(FormFile propertyKhasra) {
		this.propertyKhasra = propertyKhasra;
	}

	public String getPropKhasraDocumentName() {
		return propKhasraDocumentName;
	}

	public void setPropKhasraDocumentName(String propKhasraDocumentName) {
		this.propKhasraDocumentName = propKhasraDocumentName;
	}

	public String getPropKhasraDocumentSize() {
		return propKhasraDocumentSize;
	}

	public void setPropKhasraDocumentSize(String propKhasraDocumentSize) {
		this.propKhasraDocumentSize = propKhasraDocumentSize;
	}

	public byte[] getPropKhasraDocContents() {
		return propKhasraDocContents;
	}

	public void setPropKhasraDocContents(byte[] propKhasraDocContents) {
		this.propKhasraDocContents = propKhasraDocContents;
	}

	public String getPropKhasraFilePath() {
		return propKhasraFilePath;
	}

	public void setPropKhasraFilePath(String propKhasraFilePath) {
		this.propKhasraFilePath = propKhasraFilePath;
	}

	public String getPropKhasraPartyOrProp() {
		return propKhasraPartyOrProp;
	}

	public void setPropKhasraPartyOrProp(String propKhasraPartyOrProp) {
		this.propKhasraPartyOrProp = propKhasraPartyOrProp;
	}

	public String getPropKhasraUploadType() {
		return propKhasraUploadType;
	}

	public void setPropKhasraUploadType(String propKhasraUploadType) {
		this.propKhasraUploadType = propKhasraUploadType;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public String getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPartyRoleId() {
		return partyRoleId;
	}

	public void setPartyRoleId(String partyRoleId) {
		this.partyRoleId = partyRoleId;
	}

	public String getWitnessTxnNummber() {
		return witnessTxnNummber;
	}

	public void setWitnessTxnNummber(String witnessTxnNummber) {
		this.witnessTxnNummber = witnessTxnNummber;
	}

	


	public String getFnameTrns() {
		return fnameTrns;
	}

	public void setFnameTrns(String fnameTrns) {
		this.fnameTrns = fnameTrns;
	}

	public String getLnameTrns() {
		return lnameTrns;
	}

	public void setLnameTrns(String lnameTrns) {
		this.lnameTrns = lnameTrns;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String getSelectedWitnessTxnNummber() {
		return selectedWitnessTxnNummber;
	}

	public void setSelectedWitnessTxnNummber(String selectedWitnessTxnNummber) {
		this.selectedWitnessTxnNummber = selectedWitnessTxnNummber;
	}

	private String cancelledPage;
	public String getCancelledPage() {
		return cancelledPage;
	}

	public void setCancelledPage(String cancelledPage) {
		this.cancelledPage = cancelledPage;
	}

	public int getCommonDeedFlag() {
		return commonDeedFlag;
	}

	public void setCommonDeedFlag(int commonDeedFlag) {
		this.commonDeedFlag = commonDeedFlag;
	}

	public String getConsenterCheck() {
		return consenterCheck;
	}

	public void setConsenterCheck(String consenterCheck) {
		this.consenterCheck = consenterCheck;
	}

	public String getConsenterVal() {
		return consenterVal;
	}

	public void setConsenterVal(String consenterVal) {
		this.consenterVal = consenterVal;
	}
	
	
	//***********scan*****************
	private String parentPathScan;
	private String fileNameScan;
	private String guidUpload;
	private String deedDoc;
	private String propPage;
	private String propPageOld;
	private int agreeMemoInstFlag;
	private int deedTypeFlag;
	private String id;
	private String name;
	private String relationshipWit;
	private String uploadDoc;
	private String deathCert;
	private String relationProofName;
	private String poaUploadFilename;
	private String linkOnloadChk;
	private String transactionType;
	private String suppDocFileName;
	private String suppDocFilePath;
	private String uploadSuppDoc;
	private String uploadSuppDocPath; 
	private String verifyCheckbox;
	
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

	public String getGuidUpload() {
		return guidUpload;
	}

	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
	}

	

	public String getPropPage() {
		return propPage;
	}

	public void setPropPage(String propPage) {
		this.propPage = propPage;
	}

	public String getPropPageOld() {
		return propPageOld;
	}

	public void setPropPageOld(String propPageOld) {
		this.propPageOld = propPageOld;
	}

	public int getAgreeMemoInstFlag() {
		return agreeMemoInstFlag;
	}

	public void setAgreeMemoInstFlag(int agreeMemoInstFlag) {
		this.agreeMemoInstFlag = agreeMemoInstFlag;
	}

	public int getDeedTypeFlag() {
		return deedTypeFlag;
	}

	public void setDeedTypeFlag(int deedTypeFlag) {
		this.deedTypeFlag = deedTypeFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationshipWit() {
		return relationshipWit;
	}

	public void setRelationshipWit(String relationshipWit) {
		this.relationshipWit = relationshipWit;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
	}

	public String getDeathCert() {
		return deathCert;
	}

	public void setDeathCert(String deathCert) {
		this.deathCert = deathCert;
	}

	public String getRelationProofName() {
		return relationProofName;
	}

	public void setRelationProofName(String relationProofName) {
		this.relationProofName = relationProofName;
	}

	public String getPoaUploadFilename() {
		return poaUploadFilename;
	}

	public void setPoaUploadFilename(String poaUploadFilename) {
		this.poaUploadFilename = poaUploadFilename;
	}

	public String getLinkCheck() {
		return linkCheck;
	}

	public void setLinkCheck(String linkCheck) {
		this.linkCheck = linkCheck;
	}

	public String getLinkOnloadChk() {
		return linkOnloadChk;
	}

	public void setLinkOnloadChk(String linkOnloadChk) {
		this.linkOnloadChk = linkOnloadChk;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getContinueHold() {
		return continueHold;
	}

	public void setContinueHold(String continueHold) {
		this.continueHold = continueHold;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSuppDocFileName() {
		return suppDocFileName;
	}

	public void setSuppDocFileName(String suppDocFileName) {
		this.suppDocFileName = suppDocFileName;
	}

	public String getSuppDocFilePath() {
		return suppDocFilePath;
	}

	public void setSuppDocFilePath(String suppDocFilePath) {
		this.suppDocFilePath = suppDocFilePath;
	}

	public String getUploadSuppDoc() {
		return uploadSuppDoc;
	}

	public void setUploadSuppDoc(String uploadSuppDoc) {
		this.uploadSuppDoc = uploadSuppDoc;
	}

	//****photograph******//
	
	private String partyPhotoName;
	private String partyPhotoPath;
	private String witnessPhotoName;
	private String witnessPhotoPath;
	private String consenterPhotoName;
	private String consenterPhotoPath;
	private String parentPathPhoto;
	private String fileNamePhoto;
	private String witIdUpload;
	private String partyIdUpload;
	private String consenterIdUpload;
	private String photoChkParty;
	private String photoChkWitness;
	private String photoChkConsenter;
	private String consenterChk;

	public String getPartyPhotoName() {
		return partyPhotoName;
	}

	public void setPartyPhotoName(String partyPhotoName) {
		this.partyPhotoName = partyPhotoName;
	}

	public String getPartyPhotoPath() {
		return partyPhotoPath;
	}

	public void setPartyPhotoPath(String partyPhotoPath) {
		this.partyPhotoPath = partyPhotoPath;
	}

	public String getWitnessPhotoName() {
		return witnessPhotoName;
	}

	public void setWitnessPhotoName(String witnessPhotoName) {
		this.witnessPhotoName = witnessPhotoName;
	}

	public String getWitnessPhotoPath() {
		return witnessPhotoPath;
	}

	public void setWitnessPhotoPath(String witnessPhotoPath) {
		this.witnessPhotoPath = witnessPhotoPath;
	}

	public String getConsenterPhotoName() {
		return consenterPhotoName;
	}

	public void setConsenterPhotoName(String consenterPhotoName) {
		this.consenterPhotoName = consenterPhotoName;
	}

	public String getConsenterPhotoPath() {
		return consenterPhotoPath;
	}

	public void setConsenterPhotoPath(String consenterPhotoPath) {
		this.consenterPhotoPath = consenterPhotoPath;
	}

	public String getParentPathPhoto() {
		return parentPathPhoto;
	}

	public void setParentPathPhoto(String parentPathPhoto) {
		this.parentPathPhoto = parentPathPhoto;
	}

	public String getFileNamePhoto() {
		return fileNamePhoto;
	}

	public void setFileNamePhoto(String fileNamePhoto) {
		this.fileNamePhoto = fileNamePhoto;
	}

	public String getWitIdUpload() {
		return witIdUpload;
	}

	public void setWitIdUpload(String witIdUpload) {
		this.witIdUpload = witIdUpload;
	}

	public String getPartyIdUpload() {
		return partyIdUpload;
	}

	public void setPartyIdUpload(String partyIdUpload) {
		this.partyIdUpload = partyIdUpload;
	}

	public String getConsenterIdUpload() {
		return consenterIdUpload;
	}

	public void setConsenterIdUpload(String consenterIdUpload) {
		this.consenterIdUpload = consenterIdUpload;
	}

	public String getDeedDocName() {
		return deedDocName;
	}

	public void setDeedDocName(String deedDocName) {
		this.deedDocName = deedDocName;
	}

	public String getDeedDocPath() {
		return deedDocPath;
	}

	public void setDeedDocPath(String deedDocPath) {
		this.deedDocPath = deedDocPath;
	}

	public String getUpldDeathCertPath() {
		return upldDeathCertPath;
	}

	public void setUpldDeathCertPath(String upldDeathCertPath) {
		this.upldDeathCertPath = upldDeathCertPath;
	}

	public String getUplaReltnProofPath() {
		return uplaReltnProofPath;
	}

	public void setUplaReltnProofPath(String uplaReltnProofPath) {
		this.uplaReltnProofPath = uplaReltnProofPath;
	}

	public String getUploadPoaDocPath() {
		return uploadPoaDocPath;
	}

	public void setUploadPoaDocPath(String uploadPoaDocPath) {
		this.uploadPoaDocPath = uploadPoaDocPath;
	}

	public String getUploadSuppDocPath() {
		return uploadSuppDocPath;
	}

	public void setUploadSuppDocPath(String uploadSuppDocPath) {
		this.uploadSuppDocPath = uploadSuppDocPath;
	}

	public String getPhotoChkParty() {
		return photoChkParty;
	}

	public void setPhotoChkParty(String photoChkParty) {
		this.photoChkParty = photoChkParty;
	}

	public String getPhotoChkWitness() {
		return photoChkWitness;
	}

	public void setPhotoChkWitness(String photoChkWitness) {
		this.photoChkWitness = photoChkWitness;
	}

	public String getPhotoChkConsenter() {
		return photoChkConsenter;
	}

	public void setPhotoChkConsenter(String photoChkConsenter) {
		this.photoChkConsenter = photoChkConsenter;
	}

	public String getConsenterChk() {
		return consenterChk;
	}

	public void setConsenterChk(String consenterChk) {
		this.consenterChk = consenterChk;
	}

	public String getDeedDoc() {
		return deedDoc;
	}

	public void setDeedDoc(String deedDoc) {
		this.deedDoc = deedDoc;
	}

	public String getVerifyCheckbox() {
		return verifyCheckbox;
	}

	public void setVerifyCheckbox(String verifyCheckbox) {
		this.verifyCheckbox = verifyCheckbox;
	}

	public void setLinkingDashboardList(ArrayList linkingDashboardList) {
		this.linkingDashboardList = linkingDashboardList;
	}

	public ArrayList getLinkingDashboardList() {
		return linkingDashboardList;
	}

	public void setOldRegistrationDate(String oldRegistrationDate) {
		this.oldRegistrationDate = oldRegistrationDate;
	}

	public String getOldRegistrationDate() {
		return oldRegistrationDate;
	}

	public void setPartyRoleFullNamePOA(String partyRoleFullNamePOA) {
		this.partyRoleFullNamePOA = partyRoleFullNamePOA;
	}

	public String getPartyRoleFullNamePOA() {
		return partyRoleFullNamePOA;
	}

	
	//for adhar
	
	public void setAadharName(String aadharName) {
		this.aadharName = aadharName;
	}

	public String getAadharName() {
		return aadharName;
	}
	private String aadharName;
	
	
	
	
	
	
}
