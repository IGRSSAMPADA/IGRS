package com.wipro.igrs.newreginit.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//import org.apache.struts.upload.FormFile;

//import com.wipro.igrs.reginit.form.RegCompletionForm;


public class RegCommonDTO implements Serializable {
	
	
	private String ownerTxnId;
	private String addressIndOutMpTrns;
	private String addressOrgOutMpTrns;
	private String addressAuthPerOutMpTrns;
	private String addressOwnerOutMpTrns;
	private String addressGovtOffclOutMpTrns;
	// Added New Query for POA , Authenticaed POA - Rahul
	private String ownerPartyDesc;
	
	private String ownerPoADesc;
	//for minor Guardian name - Rahul 
	// for POA holder Hindi English NAme for organisation - RAhul
	
	private String ownerPartyDescForOrg;
	
	private String ownerPartyPoaDescForOrg;
	private ArrayList poAOwnerListForOrg;
	
	private String roleNameForPOA ;
	
	private String ageAboveName;
	
	private String dashPartyName;
	
	private ArrayList poAOwnerList;
	
	
	public String getDashPartyName() {
		return dashPartyName;
	}
	public void setDashPartyName(String dashPartyName) {
		this.dashPartyName = dashPartyName;
	}
	// end Rahul
	private HashMap trnsOwnerList=new HashMap();
	
	
	public String getOwnerTxnId() {
		return ownerTxnId;
	}
	public void setOwnerTxnId(String ownerTxnId) {
		this.ownerTxnId = ownerTxnId;
	}
	public HashMap getTrnsOwnerList() {
		return trnsOwnerList;
	}
	public void setTrnsOwnerList(HashMap trnsOwnerList) {
		this.trnsOwnerList = trnsOwnerList;
	}
	public String getAddressIndOutMpTrns() {
		return addressIndOutMpTrns;
	}
	public void setAddressIndOutMpTrns(String addressIndOutMpTrns) {
		this.addressIndOutMpTrns = addressIndOutMpTrns;
	}
	public String getAddressOrgOutMpTrns() {
		return addressOrgOutMpTrns;
	}
	public void setAddressOrgOutMpTrns(String addressOrgOutMpTrns) {
		this.addressOrgOutMpTrns = addressOrgOutMpTrns;
	}
	public String getAddressAuthPerOutMpTrns() {
		return addressAuthPerOutMpTrns;
	}
	public void setAddressAuthPerOutMpTrns(String addressAuthPerOutMpTrns) {
		this.addressAuthPerOutMpTrns = addressAuthPerOutMpTrns;
	}
	public String getAddressOwnerOutMpTrns() {
		return addressOwnerOutMpTrns;
	}
	public void setAddressOwnerOutMpTrns(String addressOwnerOutMpTrns) {
		this.addressOwnerOutMpTrns = addressOwnerOutMpTrns;
	}
	public String getAddressGovtOffclOutMpTrns() {
		return addressGovtOffclOutMpTrns;
	}
	public void setAddressGovtOffclOutMpTrns(String addressGovtOffclOutMpTrns) {
		this.addressGovtOffclOutMpTrns = addressGovtOffclOutMpTrns;
	}
	private ArrayList appType;
	private ArrayList country;
	private ArrayList state;
	private ArrayList district;
	private ArrayList indcountry;
	private ArrayList indstate;
	private ArrayList inddistrict;
	private ArrayList idProf;
	private ArrayList deedType;
	private ArrayList instrument;
	private ArrayList exemption;
	private ArrayList tpartiesList;
	private ArrayList organisationList;
	private ArrayList exemptionList;
	
	//following added by Roopam for deed selection page.
	private String deedName;
	private String instrumentName;
	private String exemptionName;
	private String deedID;
	private String instrumentID;
	private String exemptionID;
	
	//following added for transacting parties details.
	private ArrayList appTypeTrns;
	private ArrayList countryTrns;
	private ArrayList stateTrns;
	private ArrayList districtTrns;
	private ArrayList indcountryTrns;
	private ArrayList indstateTrns;
	private ArrayList inddistrictTrns;
	private ArrayList idProfTrns;
	//private ArrayList deedType;
	//private ArrayList instrument;
	//private ArrayList exemption;
	private ArrayList tpartiesListTrns;
	private ArrayList organisationListTrns;
	//private ArrayList exemptionList;
	
	//following added by Roopam for transacting parties
	//common transacting party details
	private String listAdoptedPartyTrns;
	private String listAdoptedPartyNameTrns;
	private String purposeTrns;
	//transacting party details for individual
	private String fnameTrns;
	private String mnameTrns; 
	private String lnameTrns;
	private String gendarTrns = "M";
	private String ageTrns;
	private String fatherNameTrns;
	private String motherNameTrns;
	private String spouseNameTrns;
	private String nationalityTrns;
	private String postalCodeTrns;
	private String emailIDTrns;
	private String indaddressTrns;
	// new field Guardiantrans  - Rahul
	private String guardianTrans;
	
	
	public String getGuardianTrans() {
		return guardianTrans;
	}
	public void setGuardianTrans(String guardianTrans) {
		this.guardianTrans = guardianTrans;
	}
	//private String indcountryTrns;
	private String indstatenameTrns;
	//private String inddistrictTrns;
	private String indphnoTrns;
	private String indmobnoTrns;
	private String listIDTrns;
	private String idnoTrns;
	private String bnameTrns;
	private String baddressTrns;
	
	//transacting party details for organization
	private String ogrNameTrns;
	private String authPerNameTrns;
	private String orgaddressTrns;
	//private String countryTrns;
	private String statenameTrns;
	//private String districtTrns;
	private String phnoTrns;
	private String mobnoTrns;
	private String marketValueTrns;
	private String consiAmountTrns;
	
	private int addMoreCounter;
	private String selectedPhotoId;
	private String selectedPhotoIdName;
	
	private String selectedCountry;
	private String selectedDistrict;
	private String selectedState;
	private String selectedIndCountry;
	private String selectedIndDistrict;
	private String selectedIndState;
	
	private String selectedCountryName;
	private String selectedDistrictName;
	private String selectedStateName;
	private String selectedIndCountryName;
	private String selectedIndDistrictName;
	private String selectedIndStateName;
	
	//new
	private ArrayList partyType;
	private int deedId;
	private int roleId;
	
	//neww
	private String indAuthPersn;
	private String roleName;
	private String partyTypeTrns;
	private String indReligionTrns;
	private String indCasteTrns;
	private String indDisabilityTrns;
	private String indDisabilityIdTrns;
	private String ownershipShareTrns;
	private String relationWithOwnerTrns;
	private String shareOfPropTrns;
	private String selectedGender;
	private String bname;                //might be removed
	private String baddress;             //might be removed
	private String applicantOrTransParty;
	private String individualOrOrganization;
	private String partyRoleTypeId;
	private String userID;
	private String poaListId;
	private String ownerListId;
	//arraylists
	private ArrayList poaList;
	private ArrayList ownerList;
	private ArrayList poaList1;
	private ArrayList ownerList1;
	//following added after first demo. 
	private Object applicationId;
	private Object applicationIdDisp;
	private Object createdDate;
	private Object deedName1;
	private String propertyId;
	
	//following fields for owner of poa
    private String ownerIdTrns;
	private String ownerNameTrns;
	private String ownerOgrNameTrns;
	private String ownerGendarTrns;
	private String ownerGendarValTrns;
	private String ownerNationalityTrns;
	private String ownerAddressTrns;
	private String ownerPhnoTrns;
	private String ownerEmailIDTrns;
	private String ownerIdnoTrns;
	private String ownerAgeTrns;
	private String ownerDOBTrns;
	private String ownerListIDTrns;
	private String ownerProofNameTrns;
	
	private String partyTypeFlag="";
	
	private ArrayList categoryList;
	private String selectedCategoryName;
	private String indMinorityTrns;
	//private String indDisabilityDesc;
	private String indDisabilityDescTrns;
	//private String indPovertyLine;
	private String indPovertyLineTrns;
	//private String userDay;
	//private String userMonth;
	//private String userYear;
	//private String userDOB;
	private String userDayTrns;
	private String userMonthTrns;
	private String userYearTrns;
	private String userDOBTrns;
	//private String indCategory;
	private String indCategoryTrns;
	
	
	private ArrayList occupationList;
	private String selectedOccupationName;
	private String occupationIdTrns;
	//private ArrayList occupationListTrns;
	
	private String indScheduleAreaTrns;
	private String permissionNoTrns;
	
	private String indScheduleAreaTrnsDisplay;
	
	
	private String documentNameTrns;
	private String documentSizeTrns;
	private byte[] docContentsTrns;
	private String filePathTrns;
	private String partyOrPropTrns;
	private String uploadTypeTrns;
	
	private String hdnDeclareShareCheck;
	private String appStatus;
	
	private String paymentStatus;
	
	//for uploads

	//UPLOAD PHOTO ID PROOF
	private String u2DocumentNameTrns;
	private String u2DocumentSizeTrns;
	private byte[] u2DocContentsTrns;
	private String u2FilePathTrns;
	private String u2PartyOrPropTrns;
	private String u2UploadTypeTrns;
	
	//UPLOAD NOTARIZED AFFIDAVIT BY EXECUTANT
	
	private String u3DocumentNameTrns;
	private String u3DocumentSizeTrns;
	private byte[] u3DocContentsTrns;
	private String u3FilePathTrns;
	private String u3PartyOrPropTrns;
	private String u3UploadTypeTrns;
	
	//UPLOAD EXECUTANT PHOTO
	
	private String u4DocumentNameTrns;
	private String u4DocumentSizeTrns;
	private byte[] u4DocContentsTrns;
	private String u4FilePathTrns;
	private String u4PartyOrPropTrns;
	private String u4UploadTypeTrns;
	
	//UPLOAD NOTARIZED AFFIDAVIT BY ATTORNEY
	
	private String u5DocumentNameTrns;
	private String u5DocumentSizeTrns;
	private byte[] u5DocContentsTrns;
	private String u5FilePathTrns;
	private String u5PartyOrPropTrns;
	private String u5UploadTypeTrns;
	
	//UPLOAD ATTORNEY PHOTO
	
	private String u6DocumentNameTrns;
	private String u6DocumentSizeTrns;
	private byte[] u6DocContentsTrns;
	private String u6FilePathTrns;
	private String u6PartyOrPropTrns;
	private String u6UploadTypeTrns;
	
	
	//UPLOAD EXECUTANT PHOTO 2
	
	private String u7DocumentNameTrns;
	private String u7DocumentSizeTrns;
	private byte[] u7DocContentsTrns;
	private String u7FilePathTrns;
	private String u7PartyOrPropTrns;
	private String u7UploadTypeTrns;
	
	//UPLOAD CLAIMANT PHOTO
	
	private String u8DocumentNameTrns;
	private String u8DocumentSizeTrns;
	private byte[] u8DocContentsTrns;
	private String u8FilePathTrns;
	private String u8PartyOrPropTrns;
	private String u8UploadTypeTrns;
	
	
	//UPLOAD ATTORNEY PHOTO 2
	
	private String u9DocumentNameTrns;
	private String u9DocumentSizeTrns;
	private byte[] u9DocContentsTrns;
	private String u9FilePathTrns;
	private String u9PartyOrPropTrns;
	private String u9UploadTypeTrns;
	
	//UPLOAD PAN EXECUTANT
	
	private String u10DocumentNameTrns;
	private String u10DocumentSizeTrns;
	private byte[] u10DocContentsTrns;
	private String u10FilePathTrns;
	private String u10PartyOrPropTrns;
	private String u10UploadTypeTrns;
	
	//UPLOAD PAN CLAIMANT
	
	private String u11DocumentNameTrns;
	private String u11DocumentSizeTrns;
	private byte[] u11DocContentsTrns;
	private String u11FilePathTrns;
	private String u11PartyOrPropTrns;
	private String u11UploadTypeTrns;
	
	private String srOfficeNameTrns;
	private String poaRegNoTrns;
	private String datePoaRegTrns;
	private String partyTxnId;
	
	private String particularName;
	private String particularDesc;
	
	private ArrayList relationshipList;
	private ArrayList authPerRelationshipList;
	private ArrayList ownerRelationShipList;
	
	private int relationshipTrns;
	private String relationshipNameTrns;
	
	private ArrayList executantClaimant;
	private int executantClaimantTrns;
	private String executantClaimantNameTrns;
	
	
	private int claimantFlag;
	private int poaHolderFlag;
	
	
	private String authPerAddressTrns;
	private String authPerCountryTrns;
	private String authPerStatenameTrns;
	private String authPerDistrictTrns;
	
	private String authPerFatherNameTrns;
	private int authPerRelationshipTrns;
	private String authPerGendarTrns = "M";
	
	private String authPerCountryNameTrns;
	private String authPerStatenameNameTrns;
	private String authPerDistrictNameTrns;
	
	
	
	private String language ;

	//Get Additional Uploads
	private ArrayList<CommonDTO> listDto;
	
private String govtOfcCheck;
	
	private String nameOfOfficial;
	
	private String desgOfOfficial;
	
	private String addressOfOfficial;
	
	private String officialCheck;
	
	
	
	public String getOwnerGendarValTrns() {
		return ownerGendarValTrns;
	}
	public void setOwnerGendarValTrns(String ownerGendarValTrns) {
		this.ownerGendarValTrns = ownerGendarValTrns;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getGovtOfcCheck() {
		return govtOfcCheck;
	}
	public void setGovtOfcCheck(String govtOfcCheck) {
		this.govtOfcCheck = govtOfcCheck;
	}
	public String getNameOfOfficial() {
		return nameOfOfficial;
	}
	public void setNameOfOfficial(String nameOfOfficial) {
		this.nameOfOfficial = nameOfOfficial;
	}
	public String getDesgOfOfficial() {
		return desgOfOfficial;
	}
	public void setDesgOfOfficial(String desgOfOfficial) {
		this.desgOfOfficial = desgOfOfficial;
	}
	public String getAddressOfOfficial() {
		return addressOfOfficial;
	}
	public void setAddressOfOfficial(String addressOfOfficial) {
		this.addressOfOfficial = addressOfOfficial;
	}
	public String getOfficialCheck() {
		return officialCheck;
	}
	public void setOfficialCheck(String officialCheck) {
		this.officialCheck = officialCheck;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getIndDisabilityIdTrns() {
		return indDisabilityIdTrns;
	}
	public void setIndDisabilityIdTrns(String indDisabilityIdTrns) {
		this.indDisabilityIdTrns = indDisabilityIdTrns;
	}
	public ArrayList getAuthPerRelationshipList() {
		return authPerRelationshipList;
	}
	public void setAuthPerRelationshipList(ArrayList authPerRelationshipList) {
		this.authPerRelationshipList = authPerRelationshipList;
	}
	public String getAuthPerCountryNameTrns() {
		return authPerCountryNameTrns;
	}
	public void setAuthPerCountryNameTrns(String authPerCountryNameTrns) {
		this.authPerCountryNameTrns = authPerCountryNameTrns;
	}
	public String getAuthPerStatenameNameTrns() {
		return authPerStatenameNameTrns;
	}
	public void setAuthPerStatenameNameTrns(String authPerStatenameNameTrns) {
		this.authPerStatenameNameTrns = authPerStatenameNameTrns;
	}
	public String getAuthPerDistrictNameTrns() {
		return authPerDistrictNameTrns;
	}
	public void setAuthPerDistrictNameTrns(String authPerDistrictNameTrns) {
		this.authPerDistrictNameTrns = authPerDistrictNameTrns;
	}
	public String getAuthPerAddressTrns() {
		return authPerAddressTrns;
	}
	public void setAuthPerAddressTrns(String authPerAddressTrns) {
		this.authPerAddressTrns = authPerAddressTrns;
	}
	public String getAuthPerCountryTrns() {
		return authPerCountryTrns;
	}
	public void setAuthPerCountryTrns(String authPerCountryTrns) {
		this.authPerCountryTrns = authPerCountryTrns;
	}
	public String getAuthPerStatenameTrns() {
		return authPerStatenameTrns;
	}
	public void setAuthPerStatenameTrns(String authPerStatenameTrns) {
		this.authPerStatenameTrns = authPerStatenameTrns;
	}
	public String getAuthPerDistrictTrns() {
		return authPerDistrictTrns;
	}
	public void setAuthPerDistrictTrns(String authPerDistrictTrns) {
		this.authPerDistrictTrns = authPerDistrictTrns;
	}
	public String getAuthPerFatherNameTrns() {
		return authPerFatherNameTrns;
	}
	public void setAuthPerFatherNameTrns(String authPerFatherNameTrns) {
		this.authPerFatherNameTrns = authPerFatherNameTrns;
	}
	public int getAuthPerRelationshipTrns() {
		return authPerRelationshipTrns;
	}
	public void setAuthPerRelationshipTrns(int authPerRelationshipTrns) {
		this.authPerRelationshipTrns = authPerRelationshipTrns;
	}
	public String getAuthPerGendarTrns() {
		return authPerGendarTrns;
	}
	public void setAuthPerGendarTrns(String authPerGendarTrns) {
		this.authPerGendarTrns = authPerGendarTrns;
	}
	public int getClaimantFlag() {
		return claimantFlag;
	}
	public void setClaimantFlag(int claimantFlag) {
		this.claimantFlag = claimantFlag;
	}
	public int getPoaHolderFlag() {
		return poaHolderFlag;
	}
	public void setPoaHolderFlag(int poaHolderFlag) {
		this.poaHolderFlag = poaHolderFlag;
	}
	public int getExecutantClaimantTrns() {
		return executantClaimantTrns;
	}
	public void setExecutantClaimantTrns(int executantClaimantTrns) {
		this.executantClaimantTrns = executantClaimantTrns;
	}
	public ArrayList getExecutantClaimant() {
		return executantClaimant;
	}
	public void setExecutantClaimant(ArrayList executantClaimant) {
		this.executantClaimant = executantClaimant;
	}
	
	public String getExecutantClaimantNameTrns() {
		return executantClaimantNameTrns;
	}
	public void setExecutantClaimantNameTrns(String executantClaimantNameTrns) {
		this.executantClaimantNameTrns = executantClaimantNameTrns;
	}
	public String getRelationshipNameTrns() {
		return relationshipNameTrns;
	}
	public void setRelationshipNameTrns(String relationshipNameTrns) {
		this.relationshipNameTrns = relationshipNameTrns;
	}
	public ArrayList getRelationshipList() {
		return relationshipList;
	}
	public void setRelationshipList(ArrayList relationshipList) {
		this.relationshipList = relationshipList;
	}
	
	public int getRelationshipTrns() {
		return relationshipTrns;
	}
	public void setRelationshipTrns(int relationshipTrns) {
		this.relationshipTrns = relationshipTrns;
	}
	public String getParticularName() {
		return particularName;
	}
	public void setParticularName(String particularName) {
		this.particularName = particularName;
	}
	public String getParticularDesc() {
		return particularDesc;
	}
	public void setParticularDesc(String particularDesc) {
		this.particularDesc = particularDesc;
	}
	public Object getApplicationIdDisp() {
		return applicationIdDisp;
	}
	public void setApplicationIdDisp(Object applicationIdDisp) {
		this.applicationIdDisp = applicationIdDisp;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getOwnerIdTrns() {
		return ownerIdTrns;
	}
	public void setOwnerIdTrns(String ownerIdTrns) {
		this.ownerIdTrns = ownerIdTrns;
	}
	public String getPartyTxnId() {
		return partyTxnId;
	}
	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}
	public String getUserDayTrns() {
		return userDayTrns;
	}
	public void setUserDayTrns(String userDayTrns) {
		this.userDayTrns = userDayTrns;
	}
	public String getUserMonthTrns() {
		return userMonthTrns;
	}
	public void setUserMonthTrns(String userMonthTrns) {
		this.userMonthTrns = userMonthTrns;
	}
	public String getUserYearTrns() {
		return userYearTrns;
	}
	public void setUserYearTrns(String userYearTrns) {
		this.userYearTrns = userYearTrns;
	}
	public String getSrOfficeNameTrns() {
		return srOfficeNameTrns;
	}
	public void setSrOfficeNameTrns(String srOfficeNameTrns) {
		this.srOfficeNameTrns = srOfficeNameTrns;
	}
	public String getPoaRegNoTrns() {
		return poaRegNoTrns;
	}
	public void setPoaRegNoTrns(String poaRegNoTrns) {
		this.poaRegNoTrns = poaRegNoTrns;
	}
	public String getDatePoaRegTrns() {
		return datePoaRegTrns;
	}
	public void setDatePoaRegTrns(String datePoaRegTrns) {
		this.datePoaRegTrns = datePoaRegTrns;
	}
	public String getU2DocumentNameTrns() {
		return u2DocumentNameTrns;
	}
	public void setU2DocumentNameTrns(String documentNameTrns) {
		u2DocumentNameTrns = documentNameTrns;
	}
	public String getU2DocumentSizeTrns() {
		return u2DocumentSizeTrns;
	}
	public void setU2DocumentSizeTrns(String documentSizeTrns) {
		u2DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU2DocContentsTrns() {
		return u2DocContentsTrns;
	}
	public void setU2DocContentsTrns(byte[] docContentsTrns) {
		u2DocContentsTrns = docContentsTrns;
	}
	public String getU2FilePathTrns() {
		return u2FilePathTrns;
	}
	public void setU2FilePathTrns(String filePathTrns) {
		u2FilePathTrns = filePathTrns;
	}
	public String getU2PartyOrPropTrns() {
		return u2PartyOrPropTrns;
	}
	public void setU2PartyOrPropTrns(String partyOrPropTrns) {
		u2PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU2UploadTypeTrns() {
		return u2UploadTypeTrns;
	}
	public void setU2UploadTypeTrns(String uploadTypeTrns) {
		u2UploadTypeTrns = uploadTypeTrns;
	}
	public String getU3DocumentNameTrns() {
		return u3DocumentNameTrns;
	}
	public void setU3DocumentNameTrns(String documentNameTrns) {
		u3DocumentNameTrns = documentNameTrns;
	}
	public String getU3DocumentSizeTrns() {
		return u3DocumentSizeTrns;
	}
	public void setU3DocumentSizeTrns(String documentSizeTrns) {
		u3DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU3DocContentsTrns() {
		return u3DocContentsTrns;
	}
	public void setU3DocContentsTrns(byte[] docContentsTrns) {
		u3DocContentsTrns = docContentsTrns;
	}
	public String getU3FilePathTrns() {
		return u3FilePathTrns;
	}
	public void setU3FilePathTrns(String filePathTrns) {
		u3FilePathTrns = filePathTrns;
	}
	public String getU3PartyOrPropTrns() {
		return u3PartyOrPropTrns;
	}
	public void setU3PartyOrPropTrns(String partyOrPropTrns) {
		u3PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU3UploadTypeTrns() {
		return u3UploadTypeTrns;
	}
	public void setU3UploadTypeTrns(String uploadTypeTrns) {
		u3UploadTypeTrns = uploadTypeTrns;
	}
	public String getU4DocumentNameTrns() {
		return u4DocumentNameTrns;
	}
	public void setU4DocumentNameTrns(String documentNameTrns) {
		u4DocumentNameTrns = documentNameTrns;
	}
	public String getU4DocumentSizeTrns() {
		return u4DocumentSizeTrns;
	}
	public void setU4DocumentSizeTrns(String documentSizeTrns) {
		u4DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU4DocContentsTrns() {
		return u4DocContentsTrns;
	}
	public void setU4DocContentsTrns(byte[] docContentsTrns) {
		u4DocContentsTrns = docContentsTrns;
	}
	public String getU4FilePathTrns() {
		return u4FilePathTrns;
	}
	public void setU4FilePathTrns(String filePathTrns) {
		u4FilePathTrns = filePathTrns;
	}
	public String getU4PartyOrPropTrns() {
		return u4PartyOrPropTrns;
	}
	public void setU4PartyOrPropTrns(String partyOrPropTrns) {
		u4PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU4UploadTypeTrns() {
		return u4UploadTypeTrns;
	}
	public void setU4UploadTypeTrns(String uploadTypeTrns) {
		u4UploadTypeTrns = uploadTypeTrns;
	}
	public String getU5DocumentNameTrns() {
		return u5DocumentNameTrns;
	}
	public void setU5DocumentNameTrns(String documentNameTrns) {
		u5DocumentNameTrns = documentNameTrns;
	}
	public String getU5DocumentSizeTrns() {
		return u5DocumentSizeTrns;
	}
	public void setU5DocumentSizeTrns(String documentSizeTrns) {
		u5DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU5DocContentsTrns() {
		return u5DocContentsTrns;
	}
	public void setU5DocContentsTrns(byte[] docContentsTrns) {
		u5DocContentsTrns = docContentsTrns;
	}
	public String getU5FilePathTrns() {
		return u5FilePathTrns;
	}
	public void setU5FilePathTrns(String filePathTrns) {
		u5FilePathTrns = filePathTrns;
	}
	public String getU5PartyOrPropTrns() {
		return u5PartyOrPropTrns;
	}
	public void setU5PartyOrPropTrns(String partyOrPropTrns) {
		u5PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU5UploadTypeTrns() {
		return u5UploadTypeTrns;
	}
	public void setU5UploadTypeTrns(String uploadTypeTrns) {
		u5UploadTypeTrns = uploadTypeTrns;
	}
	public String getU6DocumentNameTrns() {
		return u6DocumentNameTrns;
	}
	public void setU6DocumentNameTrns(String documentNameTrns) {
		u6DocumentNameTrns = documentNameTrns;
	}
	public String getU6DocumentSizeTrns() {
		return u6DocumentSizeTrns;
	}
	public void setU6DocumentSizeTrns(String documentSizeTrns) {
		u6DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU6DocContentsTrns() {
		return u6DocContentsTrns;
	}
	public void setU6DocContentsTrns(byte[] docContentsTrns) {
		u6DocContentsTrns = docContentsTrns;
	}
	public String getU6FilePathTrns() {
		return u6FilePathTrns;
	}
	public void setU6FilePathTrns(String filePathTrns) {
		u6FilePathTrns = filePathTrns;
	}
	public String getU6PartyOrPropTrns() {
		return u6PartyOrPropTrns;
	}
	public void setU6PartyOrPropTrns(String partyOrPropTrns) {
		u6PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU6UploadTypeTrns() {
		return u6UploadTypeTrns;
	}
	public void setU6UploadTypeTrns(String uploadTypeTrns) {
		u6UploadTypeTrns = uploadTypeTrns;
	}
	public String getU7DocumentNameTrns() {
		return u7DocumentNameTrns;
	}
	public void setU7DocumentNameTrns(String documentNameTrns) {
		u7DocumentNameTrns = documentNameTrns;
	}
	public String getU7DocumentSizeTrns() {
		return u7DocumentSizeTrns;
	}
	public void setU7DocumentSizeTrns(String documentSizeTrns) {
		u7DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU7DocContentsTrns() {
		return u7DocContentsTrns;
	}
	public void setU7DocContentsTrns(byte[] docContentsTrns) {
		u7DocContentsTrns = docContentsTrns;
	}
	public String getU7FilePathTrns() {
		return u7FilePathTrns;
	}
	public void setU7FilePathTrns(String filePathTrns) {
		u7FilePathTrns = filePathTrns;
	}
	public String getU7PartyOrPropTrns() {
		return u7PartyOrPropTrns;
	}
	public void setU7PartyOrPropTrns(String partyOrPropTrns) {
		u7PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU7UploadTypeTrns() {
		return u7UploadTypeTrns;
	}
	public void setU7UploadTypeTrns(String uploadTypeTrns) {
		u7UploadTypeTrns = uploadTypeTrns;
	}
	public String getU8DocumentNameTrns() {
		return u8DocumentNameTrns;
	}
	public void setU8DocumentNameTrns(String documentNameTrns) {
		u8DocumentNameTrns = documentNameTrns;
	}
	public String getU8DocumentSizeTrns() {
		return u8DocumentSizeTrns;
	}
	public void setU8DocumentSizeTrns(String documentSizeTrns) {
		u8DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU8DocContentsTrns() {
		return u8DocContentsTrns;
	}
	public void setU8DocContentsTrns(byte[] docContentsTrns) {
		u8DocContentsTrns = docContentsTrns;
	}
	public String getU8FilePathTrns() {
		return u8FilePathTrns;
	}
	public void setU8FilePathTrns(String filePathTrns) {
		u8FilePathTrns = filePathTrns;
	}
	public String getU8PartyOrPropTrns() {
		return u8PartyOrPropTrns;
	}
	public void setU8PartyOrPropTrns(String partyOrPropTrns) {
		u8PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU8UploadTypeTrns() {
		return u8UploadTypeTrns;
	}
	public void setU8UploadTypeTrns(String uploadTypeTrns) {
		u8UploadTypeTrns = uploadTypeTrns;
	}
	public String getU9DocumentNameTrns() {
		return u9DocumentNameTrns;
	}
	public void setU9DocumentNameTrns(String documentNameTrns) {
		u9DocumentNameTrns = documentNameTrns;
	}
	public String getU9DocumentSizeTrns() {
		return u9DocumentSizeTrns;
	}
	public void setU9DocumentSizeTrns(String documentSizeTrns) {
		u9DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU9DocContentsTrns() {
		return u9DocContentsTrns;
	}
	public void setU9DocContentsTrns(byte[] docContentsTrns) {
		u9DocContentsTrns = docContentsTrns;
	}
	public String getU9FilePathTrns() {
		return u9FilePathTrns;
	}
	public void setU9FilePathTrns(String filePathTrns) {
		u9FilePathTrns = filePathTrns;
	}
	public String getU9PartyOrPropTrns() {
		return u9PartyOrPropTrns;
	}
	public void setU9PartyOrPropTrns(String partyOrPropTrns) {
		u9PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU9UploadTypeTrns() {
		return u9UploadTypeTrns;
	}
	public void setU9UploadTypeTrns(String uploadTypeTrns) {
		u9UploadTypeTrns = uploadTypeTrns;
	}
	public String getU10DocumentNameTrns() {
		return u10DocumentNameTrns;
	}
	public void setU10DocumentNameTrns(String documentNameTrns) {
		u10DocumentNameTrns = documentNameTrns;
	}
	public String getU10DocumentSizeTrns() {
		return u10DocumentSizeTrns;
	}
	public void setU10DocumentSizeTrns(String documentSizeTrns) {
		u10DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU10DocContentsTrns() {
		return u10DocContentsTrns;
	}
	public void setU10DocContentsTrns(byte[] docContentsTrns) {
		u10DocContentsTrns = docContentsTrns;
	}
	public String getU10FilePathTrns() {
		return u10FilePathTrns;
	}
	public void setU10FilePathTrns(String filePathTrns) {
		u10FilePathTrns = filePathTrns;
	}
	public String getU10PartyOrPropTrns() {
		return u10PartyOrPropTrns;
	}
	public void setU10PartyOrPropTrns(String partyOrPropTrns) {
		u10PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU10UploadTypeTrns() {
		return u10UploadTypeTrns;
	}
	public void setU10UploadTypeTrns(String uploadTypeTrns) {
		u10UploadTypeTrns = uploadTypeTrns;
	}
	public String getU11DocumentNameTrns() {
		return u11DocumentNameTrns;
	}
	public void setU11DocumentNameTrns(String documentNameTrns) {
		u11DocumentNameTrns = documentNameTrns;
	}
	public String getU11DocumentSizeTrns() {
		return u11DocumentSizeTrns;
	}
	public void setU11DocumentSizeTrns(String documentSizeTrns) {
		u11DocumentSizeTrns = documentSizeTrns;
	}
	public byte[] getU11DocContentsTrns() {
		return u11DocContentsTrns;
	}
	public void setU11DocContentsTrns(byte[] docContentsTrns) {
		u11DocContentsTrns = docContentsTrns;
	}
	public String getU11FilePathTrns() {
		return u11FilePathTrns;
	}
	public void setU11FilePathTrns(String filePathTrns) {
		u11FilePathTrns = filePathTrns;
	}
	public String getU11PartyOrPropTrns() {
		return u11PartyOrPropTrns;
	}
	public void setU11PartyOrPropTrns(String partyOrPropTrns) {
		u11PartyOrPropTrns = partyOrPropTrns;
	}
	public String getU11UploadTypeTrns() {
		return u11UploadTypeTrns;
	}
	public void setU11UploadTypeTrns(String uploadTypeTrns) {
		u11UploadTypeTrns = uploadTypeTrns;
	}
	public String getPartyOrPropTrns() {
		return partyOrPropTrns;
	}
	public void setPartyOrPropTrns(String partyOrPropTrns) {
		this.partyOrPropTrns = partyOrPropTrns;
	}
	public String getUploadTypeTrns() {
		return uploadTypeTrns;
	}
	public void setUploadTypeTrns(String uploadTypeTrns) {
		this.uploadTypeTrns = uploadTypeTrns;
	}
	public byte[] getDocContentsTrns() {
		return docContentsTrns;
	}
	public void setDocContentsTrns(byte[] docContentsTrns) {
		this.docContentsTrns = docContentsTrns;
	}
	public String getFilePathTrns() {
		return filePathTrns;
	}
	public void setFilePathTrns(String filePathTrns) {
		this.filePathTrns = filePathTrns;
	}
	public String getHdnDeclareShareCheck() {
		return hdnDeclareShareCheck;
	}
	public void setHdnDeclareShareCheck(String hdnDeclareShareCheck) {
		this.hdnDeclareShareCheck = hdnDeclareShareCheck;
	}
	public String getOwnerDOBTrns() {
		return ownerDOBTrns;
	}
	public void setOwnerDOBTrns(String ownerDOBTrns) {
		this.ownerDOBTrns = ownerDOBTrns;
	}
	public String getDocumentNameTrns() {
		return documentNameTrns;
	}
	public void setDocumentNameTrns(String documentNameTrns) {
		this.documentNameTrns = documentNameTrns;
	}
	public String getDocumentSizeTrns() {
		return documentSizeTrns;
	}
	public void setDocumentSizeTrns(String documentSizeTrns) {
		this.documentSizeTrns = documentSizeTrns;
	}
	public String getIndScheduleAreaTrnsDisplay() {
		return indScheduleAreaTrnsDisplay;
	}
	public void setIndScheduleAreaTrnsDisplay(String indScheduleAreaTrnsDisplay) {
		this.indScheduleAreaTrnsDisplay = indScheduleAreaTrnsDisplay;
	}
	public String getIndScheduleAreaTrns() {
		return indScheduleAreaTrns;
	}
	public void setIndScheduleAreaTrns(String indScheduleAreaTrns) {
		this.indScheduleAreaTrns = indScheduleAreaTrns;
	}
	public String getPermissionNoTrns() {
		return permissionNoTrns;
	}
	public void setPermissionNoTrns(String permissionNoTrns) {
		this.permissionNoTrns = permissionNoTrns;
	}
	/*public ArrayList getOccupationListTrns() {
		return occupationListTrns;
	}
	public void setOccupationListTrns(ArrayList occupationListTrns) {
		this.occupationListTrns = occupationListTrns;
	}*/
	public String getOccupationIdTrns() {
		return occupationIdTrns;
	}
	public void setOccupationIdTrns(String occupationIdTrns) {
		this.occupationIdTrns = occupationIdTrns;
	}
	public String getSelectedOccupationName() {
		return selectedOccupationName;
	}
	public void setSelectedOccupationName(String selectedOccupationName) {
		this.selectedOccupationName = selectedOccupationName;
	}
	public ArrayList getOccupationList() {
		return occupationList;
	}
	public void setOccupationList(ArrayList occupationList) {
		this.occupationList = occupationList;
	}
	public String getIndMinorityTrns() {
		return indMinorityTrns;
	}
	public void setIndMinorityTrns(String indMinorityTrns) {
		this.indMinorityTrns = indMinorityTrns;
	}
	public String getIndDisabilityDescTrns() {
		return indDisabilityDescTrns;
	}
	public void setIndDisabilityDescTrns(String indDisabilityDescTrns) {
		this.indDisabilityDescTrns = indDisabilityDescTrns;
	}
	public String getIndPovertyLineTrns() {
		return indPovertyLineTrns;
	}
	public void setIndPovertyLineTrns(String indPovertyLineTrns) {
		this.indPovertyLineTrns = indPovertyLineTrns;
	}
	public String getUserDOBTrns() {
		return userDOBTrns;
	}
	public void setUserDOBTrns(String userDOBTrns) {
		this.userDOBTrns = userDOBTrns;
	}
	public String getIndCategoryTrns() {
		return indCategoryTrns;
	}
	public void setIndCategoryTrns(String indCategoryTrns) {
		this.indCategoryTrns = indCategoryTrns;
	}
	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}
	public void setSelectedCategoryName(String selectedCategoryName) {
		this.selectedCategoryName = selectedCategoryName;
	}
	public ArrayList getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(ArrayList categoryList) {
		this.categoryList = categoryList;
	}
	public String getPartyTypeFlag() {
		return partyTypeFlag;
	}
	public void setPartyTypeFlag(String partyTypeFlag) {
		this.partyTypeFlag = partyTypeFlag;
	}
	public String getOwnerProofNameTrns() {
		return ownerProofNameTrns;
	}
	public void setOwnerProofNameTrns(String ownerProofNameTrns) {
		this.ownerProofNameTrns = ownerProofNameTrns;
	}
	public String getOwnerListIDTrns() {
		return ownerListIDTrns;
	}
	public void setOwnerListIDTrns(String ownerListIDTrns) {
		this.ownerListIDTrns = ownerListIDTrns;
	}
	public String getOwnerNameTrns() {
		return ownerNameTrns;
	}
	public void setOwnerNameTrns(String ownerNameTrns) {
		this.ownerNameTrns = ownerNameTrns;
	}
	public String getOwnerOgrNameTrns() {
		return ownerOgrNameTrns;
	}
	public void setOwnerOgrNameTrns(String ownerOgrNameTrns) {
		this.ownerOgrNameTrns = ownerOgrNameTrns;
	}
	public String getOwnerGendarTrns() {
		return ownerGendarTrns;
	}
	public void setOwnerGendarTrns(String ownerGendarTrns) {
		this.ownerGendarTrns = ownerGendarTrns;
	}
	public String getOwnerNationalityTrns() {
		return ownerNationalityTrns;
	}
	public void setOwnerNationalityTrns(String ownerNationalityTrns) {
		this.ownerNationalityTrns = ownerNationalityTrns;
	}
	public String getOwnerAddressTrns() {
		return ownerAddressTrns;
	}
	public void setOwnerAddressTrns(String ownerAddressTrns) {
		this.ownerAddressTrns = ownerAddressTrns;
	}
	public String getOwnerPhnoTrns() {
		return ownerPhnoTrns;
	}
	public void setOwnerPhnoTrns(String ownerPhnoTrns) {
		this.ownerPhnoTrns = ownerPhnoTrns;
	}
	public String getOwnerEmailIDTrns() {
		return ownerEmailIDTrns;
	}
	public void setOwnerEmailIDTrns(String ownerEmailIDTrns) {
		this.ownerEmailIDTrns = ownerEmailIDTrns;
	}
	public String getOwnerIdnoTrns() {
		return ownerIdnoTrns;
	}
	public void setOwnerIdnoTrns(String ownerIdnoTrns) {
		this.ownerIdnoTrns = ownerIdnoTrns;
	}
	public String getOwnerAgeTrns() {
		return ownerAgeTrns;
	}
	public void setOwnerAgeTrns(String ownerAgeTrns) {
		this.ownerAgeTrns = ownerAgeTrns;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public Object getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Object applicationId) {
		this.applicationId = applicationId;
	}
	public Object getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Object createdDate) {
		this.createdDate = createdDate;
	}
	public Object getDeedName1() {
		return deedName1;
	}
	public void setDeedName1(Object deedName1) {
		this.deedName1 = deedName1;
	}
	public ArrayList getPoaList1() {
		return poaList1;
	}
	public void setPoaList1(ArrayList poaList1) {
		this.poaList1 = poaList1;
	}
	public ArrayList getOwnerList1() {
		return ownerList1;
	}
	public void setOwnerList1(ArrayList ownerList1) {
		this.ownerList1 = ownerList1;
	}
	public String getOwnerListId() {
		return ownerListId;
	}
	public void setOwnerListId(String ownerListId) {
		this.ownerListId = ownerListId;
	}
	public String getPoaListId() {
		return poaListId;
	}
	public void setPoaListId(String poaListId) {
		this.poaListId = poaListId;
	}
	public ArrayList getPoaList() {
		return poaList;
	}
	public void setPoaList(ArrayList poaList) {
		this.poaList = poaList;
	}
	public ArrayList getOwnerList() {
		return ownerList;
	}
	public void setOwnerList(ArrayList ownerList) {
		this.ownerList = ownerList;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}
	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
	}
	public String getIndividualOrOrganization() {
		return individualOrOrganization;
	}
	public void setIndividualOrOrganization(String individualOrOrganization) {
		this.individualOrOrganization = individualOrOrganization;
	}
	public String getApplicantOrTransParty() {
		return applicantOrTransParty;
	}
	public void setApplicantOrTransParty(String applicantOrTransParty) {
		this.applicantOrTransParty = applicantOrTransParty;
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
	public String getSelectedGender() {
		return selectedGender;
	}
	public void setSelectedGender(String selectedGender) {
		this.selectedGender = selectedGender;
	}
	public String getPartyTypeTrns() {
		return partyTypeTrns;
	}
	public void setPartyTypeTrns(String partyTypeTrns) {
		this.partyTypeTrns = partyTypeTrns;
	}
	public String getIndReligionTrns() {
		return indReligionTrns;
	}
	public void setIndReligionTrns(String indReligionTrns) {
		this.indReligionTrns = indReligionTrns;
	}
	public String getIndCasteTrns() {
		return indCasteTrns;
	}
	public void setIndCasteTrns(String indCasteTrns) {
		this.indCasteTrns = indCasteTrns;
	}
	public String getIndDisabilityTrns() {
		return indDisabilityTrns;
	}
	public void setIndDisabilityTrns(String indDisabilityTrns) {
		this.indDisabilityTrns = indDisabilityTrns;
	}
	public String getOwnershipShareTrns() {
		return ownershipShareTrns;
	}
	public void setOwnershipShareTrns(String ownershipShareTrns) {
		this.ownershipShareTrns = ownershipShareTrns;
	}
	public String getRelationWithOwnerTrns() {
		return relationWithOwnerTrns;
	}
	public void setRelationWithOwnerTrns(String relationWithOwnerTrns) {
		this.relationWithOwnerTrns = relationWithOwnerTrns;
	}
	public String getShareOfPropTrns() {
		return shareOfPropTrns;
	}
	public void setShareOfPropTrns(String shareOfPropTrns) {
		this.shareOfPropTrns = shareOfPropTrns;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getIndAuthPersn() {
		return indAuthPersn;
	}
	public void setIndAuthPersn(String indAuthPersn) {
		this.indAuthPersn = indAuthPersn;
	}
	public int getDeedId() {
		return deedId;
	}
	public void setDeedId(int deedId) {
		this.deedId = deedId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public ArrayList getPartyType() {
		return partyType;
	}
	public void setPartyType(ArrayList partyType) {
		this.partyType = partyType;
	}
	public String getSelectedIndCountry() {
		return selectedIndCountry;
	}
	public void setSelectedIndCountry(String selectedIndCountry) {
		this.selectedIndCountry = selectedIndCountry;
	}
	public String getSelectedIndDistrict() {
		return selectedIndDistrict;
	}
	public void setSelectedIndDistrict(String selectedIndDistrict) {
		this.selectedIndDistrict = selectedIndDistrict;
	}
	public String getSelectedIndState() {
		return selectedIndState;
	}
	public void setSelectedIndState(String selectedIndState) {
		this.selectedIndState = selectedIndState;
	}
	public String getSelectedIndCountryName() {
		return selectedIndCountryName;
	}
	public void setSelectedIndCountryName(String selectedIndCountryName) {
		this.selectedIndCountryName = selectedIndCountryName;
	}
	public String getSelectedIndDistrictName() {
		return selectedIndDistrictName;
	}
	public void setSelectedIndDistrictName(String selectedIndDistrictName) {
		this.selectedIndDistrictName = selectedIndDistrictName;
	}
	public String getSelectedIndStateName() {
		return selectedIndStateName;
	}
	public void setSelectedIndStateName(String selectedIndStateName) {
		this.selectedIndStateName = selectedIndStateName;
	}
	
	
	public String getSelectedCountryName() {
		return selectedCountryName;
	}
	public void setSelectedCountryName(String selectedCountryName) {
		this.selectedCountryName = selectedCountryName;
	}
	public String getSelectedDistrictName() {
		return selectedDistrictName;
	}
	public void setSelectedDistrictName(String selectedDistrictName) {
		this.selectedDistrictName = selectedDistrictName;
	}
	public String getSelectedStateName() {
		return selectedStateName;
	}
	public void setSelectedStateName(String selectedStateName) {
		this.selectedStateName = selectedStateName;
	}
	public String getSelectedPhotoIdName() {
		return selectedPhotoIdName;
	}
	public void setSelectedPhotoIdName(String selectedPhotoIdName) {
		this.selectedPhotoIdName = selectedPhotoIdName;
	}
	private String deleteTrnsPrtyID;
	
	
	public String getDeleteTrnsPrtyID() {
		return deleteTrnsPrtyID;
	}
	public void setDeleteTrnsPrtyID(String deleteTrnsPrtyID) {
		this.deleteTrnsPrtyID = deleteTrnsPrtyID;
	}
	public String getSelectedPhotoId() {
		return selectedPhotoId;
	}
	public void setSelectedPhotoId(String selectedPhotoId) {
		this.selectedPhotoId = selectedPhotoId;
	}
	public String getSelectedDistrict() {
		return selectedDistrict;
	}
	public void setSelectedDistrict(String selectedDistrict) {
		this.selectedDistrict = selectedDistrict;
	}
	public String getSelectedState() {
		return selectedState;
	}
	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}
	public String getSelectedCountry() {
		return selectedCountry;
	}
	public void setSelectedCountry(String selectedCountry) {
		this.selectedCountry = selectedCountry;
	}
	public int getAddMoreCounter() {
		return addMoreCounter;
	}
	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}
	public String getDeedName() {
		return deedName;
	}
	public void setDeedName(String deedName) {
		this.deedName = deedName;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	public String getExemptionName() {
		return exemptionName;
	}
	public void setExemptionName(String exemptionName) {
		this.exemptionName = exemptionName;
	}
	public String getDeedID() {
		return deedID;
	}
	public void setDeedID(String deedID) {
		this.deedID = deedID;
	}
	public String getInstrumentID() {
		return instrumentID;
	}
	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}
	public String getExemptionID() {
		return exemptionID;
	}
	public void setExemptionID(String exemptionID) {
		this.exemptionID = exemptionID;
	}
	//end of addition by roopam
	/**
	 * @return the appType
	 */
	public ArrayList getAppType() {
		return appType;
	}
	/**
	 * @param appType the appType to set
	 */
	public void setAppType(ArrayList appType) {
		this.appType = appType;
	}
	/**
	 * @return the country
	 */
	public ArrayList getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(ArrayList country) {
		this.country = country;
	}
	/**
	 * @return the state
	 */
	public ArrayList getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ArrayList state) {
		this.state = state;
	}
	/**
	 * @return the district
	 */
	public ArrayList getDistrict() {
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(ArrayList district) {
		this.district = district;
	}
	/**
	 * @return the idProf
	 */
	public ArrayList getIdProf() {
		return idProf;
	}
	/**
	 * @param idProf the idProf to set
	 */
	public void setIdProf(ArrayList idProf) {
		this.idProf = idProf;
	}
	/**
	 * @return the deedType
	 */
	public ArrayList getDeedType() {
		return deedType;
	}
	/**
	 * @param deedType the deedType to set
	 */
	public void setDeedType(ArrayList deedType) {
		this.deedType = deedType;
	}
	/**
	 * @return the instrument
	 */
	public ArrayList getInstrument() {
		return instrument;
	}
	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(ArrayList instrument) {
		this.instrument = instrument;
	}
	/**
	 * @return the exemption
	 */
	public ArrayList getExemption() {
		return exemption;
	}
	/**
	 * @param exemption the exemption to set
	 */
	public void setExemption(ArrayList exemption) {
		this.exemption = exemption;
	}
	/**
	 * @return the tpartiesList
	 */
	public ArrayList getTpartiesList() {
		return tpartiesList;
	}
	/**
	 * @param tpartiesList the tpartiesList to set
	 */
	public void setTpartiesList(ArrayList tpartiesList) {
		this.tpartiesList = tpartiesList;
	}
	/**
	 * @return the organisationList
	 */
	public ArrayList getOrganisationList() {
		return organisationList;
	}
	/**
	 * @param organisationList the organisationList to set
	 */
	public void setOrganisationList(ArrayList organisationList) {
		this.organisationList = organisationList;
	}
	/**
	 * @return the exemptionList
	 */
	public ArrayList getExemptionList() {
		return exemptionList;
	}
	/**
	 * @param exemptionList the exemptionList to set
	 */
	public void setExemptionList(ArrayList exemptionList) {
		this.exemptionList = exemptionList;
	}
	/**
	 * @return the indcountry
	 */
	public ArrayList getIndcountry() {
	    return indcountry;
	}
	/**
	 * @param indcountry the indcountry to set
	 */
	public void setIndcountry(ArrayList indcountry) {
	    this.indcountry = indcountry;
	}
	/**
	 * @return the indstate
	 */
	public ArrayList getIndstate() {
	    return indstate;
	}
	/**
	 * @param indstate the indstate to set
	 */
	public void setIndstate(ArrayList indstate) {
	    this.indstate = indstate;
	}
	/**
	 * @return the inddistrict
	 */
	public ArrayList getInddistrict() {
	    return inddistrict;
	}
	/**
	 * @param inddistrict the inddistrict to set
	 */
	public void setInddistrict(ArrayList inddistrict) {
	    this.inddistrict = inddistrict;
	}
	public ArrayList getAppTypeTrns() {
		return appTypeTrns;
	}
	public void setAppTypeTrns(ArrayList appTypeTrns) {
		this.appTypeTrns = appTypeTrns;
	}
	public ArrayList getCountryTrns() {
		return countryTrns;
	}
	public void setCountryTrns(ArrayList countryTrns) {
		this.countryTrns = countryTrns;
	}
	public ArrayList getStateTrns() {
		return stateTrns;
	}
	public void setStateTrns(ArrayList stateTrns) {
		this.stateTrns = stateTrns;
	}
	public ArrayList getDistrictTrns() {
		return districtTrns;
	}
	public void setDistrictTrns(ArrayList districtTrns) {
		this.districtTrns = districtTrns;
	}
	public ArrayList getIndcountryTrns() {
		return indcountryTrns;
	}
	public void setIndcountryTrns(ArrayList indcountryTrns) {
		this.indcountryTrns = indcountryTrns;
	}
	public ArrayList getIndstateTrns() {
		return indstateTrns;
	}
	public void setIndstateTrns(ArrayList indstateTrns) {
		this.indstateTrns = indstateTrns;
	}
	public ArrayList getInddistrictTrns() {
		return inddistrictTrns;
	}
	public void setInddistrictTrns(ArrayList inddistrictTrns) {
		this.inddistrictTrns = inddistrictTrns;
	}
	public ArrayList getIdProfTrns() {
		return idProfTrns;
	}
	public void setIdProfTrns(ArrayList idProfTrns) {
		this.idProfTrns = idProfTrns;
	}
	public ArrayList getTpartiesListTrns() {
		return tpartiesListTrns;
	}
	public void setTpartiesListTrns(ArrayList tpartiesListTrns) {
		this.tpartiesListTrns = tpartiesListTrns;
	}
	public ArrayList getOrganisationListTrns() {
		return organisationListTrns;
	}
	public void setOrganisationListTrns(ArrayList organisationListTrns) {
		this.organisationListTrns = organisationListTrns;
	}
	public String getListAdoptedPartyTrns() {
		return listAdoptedPartyTrns;
	}
	public void setListAdoptedPartyTrns(String listAdoptedPartyTrns) {
		this.listAdoptedPartyTrns = listAdoptedPartyTrns;
	}
	public String getPurposeTrns() {
		return purposeTrns;
	}
	public void setPurposeTrns(String purposeTrns) {
		this.purposeTrns = purposeTrns;
	}
	public String getFnameTrns() {
		return fnameTrns;
	}
	public void setFnameTrns(String fnameTrns) {
		this.fnameTrns = fnameTrns;
	}
	public String getMnameTrns() {
		return mnameTrns;
	}
	public void setMnameTrns(String mnameTrns) {
		this.mnameTrns = mnameTrns;
	}
	public String getLnameTrns() {
		return lnameTrns;
	}
	public void setLnameTrns(String lnameTrns) {
		this.lnameTrns = lnameTrns;
	}
	public String getGendarTrns() {
		return gendarTrns;
	}
	public void setGendarTrns(String gendarTrns) {
		this.gendarTrns = gendarTrns;
	}
	public String getAgeTrns() {
		return ageTrns;
	}
	public void setAgeTrns(String ageTrns) {
		this.ageTrns = ageTrns;
	}
	public String getFatherNameTrns() {
		return fatherNameTrns;
	}
	public void setFatherNameTrns(String fatherNameTrns) {
		this.fatherNameTrns = fatherNameTrns;
	}
	public String getMotherNameTrns() {
		return motherNameTrns;
	}
	public void setMotherNameTrns(String motherNameTrns) {
		this.motherNameTrns = motherNameTrns;
	}
	public String getSpouseNameTrns() {
		return spouseNameTrns;
	}
	public void setSpouseNameTrns(String spouseNameTrns) {
		this.spouseNameTrns = spouseNameTrns;
	}
	public String getNationalityTrns() {
		return nationalityTrns;
	}
	public void setNationalityTrns(String nationalityTrns) {
		this.nationalityTrns = nationalityTrns;
	}
	public String getPostalCodeTrns() {
		return postalCodeTrns;
	}
	public void setPostalCodeTrns(String postalCodeTrns) {
		this.postalCodeTrns = postalCodeTrns;
	}
	public String getEmailIDTrns() {
		return emailIDTrns;
	}
	public void setEmailIDTrns(String emailIDTrns) {
		this.emailIDTrns = emailIDTrns;
	}
	public String getIndaddressTrns() {
		return indaddressTrns;
	}
	public void setIndaddressTrns(String indaddressTrns) {
		this.indaddressTrns = indaddressTrns;
	}
	public String getIndstatenameTrns() {
		return indstatenameTrns;
	}
	public void setIndstatenameTrns(String indstatenameTrns) {
		this.indstatenameTrns = indstatenameTrns;
	}
	public String getIndphnoTrns() {
		return indphnoTrns;
	}
	public void setIndphnoTrns(String indphnoTrns) {
		this.indphnoTrns = indphnoTrns;
	}
	public String getIndmobnoTrns() {
		return indmobnoTrns;
	}
	public void setIndmobnoTrns(String indmobnoTrns) {
		this.indmobnoTrns = indmobnoTrns;
	}
	public String getListIDTrns() {
		return listIDTrns;
	}
	public void setListIDTrns(String listIDTrns) {
		this.listIDTrns = listIDTrns;
	}
	public String getIdnoTrns() {
		return idnoTrns;
	}
	public void setIdnoTrns(String idnoTrns) {
		this.idnoTrns = idnoTrns;
	}
	public String getBnameTrns() {
		return bnameTrns;
	}
	public void setBnameTrns(String bnameTrns) {
		this.bnameTrns = bnameTrns;
	}
	public String getBaddressTrns() {
		return baddressTrns;
	}
	public void setBaddressTrns(String baddressTrns) {
		this.baddressTrns = baddressTrns;
	}
	public String getOgrNameTrns() {
		return ogrNameTrns;
	}
	public void setOgrNameTrns(String ogrNameTrns) {
		this.ogrNameTrns = ogrNameTrns;
	}
	public String getAuthPerNameTrns() {
		return authPerNameTrns;
	}
	public void setAuthPerNameTrns(String authPerNameTrns) {
		this.authPerNameTrns = authPerNameTrns;
	}
	public String getOrgaddressTrns() {
		return orgaddressTrns;
	}
	public void setOrgaddressTrns(String orgaddressTrns) {
		this.orgaddressTrns = orgaddressTrns;
	}
	public String getStatenameTrns() {
		return statenameTrns;
	}
	public void setStatenameTrns(String statenameTrns) {
		this.statenameTrns = statenameTrns;
	}
	public String getPhnoTrns() {
		return phnoTrns;
	}
	public void setPhnoTrns(String phnoTrns) {
		this.phnoTrns = phnoTrns;
	}
	public String getMobnoTrns() {
		return mobnoTrns;
	}
	public void setMobnoTrns(String mobnoTrns) {
		this.mobnoTrns = mobnoTrns;
	}
	public String getMarketValueTrns() {
		return marketValueTrns;
	}
	public void setMarketValueTrns(String marketValueTrns) {
		this.marketValueTrns = marketValueTrns;
	}
	public String getConsiAmountTrns() {
		return consiAmountTrns;
	}
	public void setConsiAmountTrns(String consiAmountTrns) {
		this.consiAmountTrns = consiAmountTrns;
	}
	
	public String getListAdoptedPartyNameTrns() {
		return listAdoptedPartyNameTrns;
	}
	public void setListAdoptedPartyNameTrns(String listAdoptedPartyNameTrns) {
		this.listAdoptedPartyNameTrns = listAdoptedPartyNameTrns;
	}
	public void setListDto(ArrayList<CommonDTO> listDto) {
		this.listDto = listDto;
	}
	public ArrayList<CommonDTO> getListDto() {
		return listDto;
	}
	public void setAgeAboveName(String ageAboveName) {
		this.ageAboveName = ageAboveName;
	}
	public String getAgeAboveName() {
		return ageAboveName;
	}
	public void setOwnerPartyDesc(String ownerPartyDesc) {
		this.ownerPartyDesc = ownerPartyDesc;
	}
	public String getOwnerPartyDesc() {
		return ownerPartyDesc;
	}
	public void setPoAOwnerList(ArrayList poAOwnerList) {
		this.poAOwnerList = poAOwnerList;
	}
	public ArrayList getPoAOwnerList() {
		return poAOwnerList;
	}
	public void setRoleNameForPOA(String roleNameForPOA) {
		this.roleNameForPOA = roleNameForPOA;
	}
	public String getRoleNameForPOA() {
		return roleNameForPOA;
	}
	public void setPoAOwnerListForOrg(ArrayList poAOwnerListForOrg) {
		this.poAOwnerListForOrg = poAOwnerListForOrg;
	}
	public ArrayList getPoAOwnerListForOrg() {
		return poAOwnerListForOrg;
	}
	public void setOwnerPartyDescForOrg(String ownerPartyDescForOrg) {
		this.ownerPartyDescForOrg = ownerPartyDescForOrg;
	}
	public String getOwnerPartyDescForOrg() {
		return ownerPartyDescForOrg;
	}
	public void setOwnerPoADesc(String ownerPoADesc) {
		this.ownerPoADesc = ownerPoADesc;
	}
	public String getOwnerPoADesc() {
		return ownerPoADesc;
	}
	public void setOwnerPartyPoaDescForOrg(String ownerPartyPoaDescForOrg) {
		this.ownerPartyPoaDescForOrg = ownerPartyPoaDescForOrg;
	}
	public String getOwnerPartyPoaDescForOrg() {
		return ownerPartyPoaDescForOrg;
	}
	
	public String aadharName;


	public String getAadharName() {
		return aadharName;
	}
	public void setAadharName(String aadharName) {
		this.aadharName = aadharName;
	}
	
	
		//Akansha
	
	private ArrayList governmentOfficeName;
	private String aadharNameOwner;
	public String getAadharNameOwner() {
		return aadharNameOwner;
	}
	public void setAadharNameOwner(String aadharNameOwner) {
		this.aadharNameOwner = aadharNameOwner;
	}
	public ArrayList getGovernmentOfficeName() {
	    return governmentOfficeName;
	}
	/**
	 * @param inddistrict the inddistrict to set
	 */
	public void setGovernmentOfficeName(ArrayList governmentOfficeName) {
	    this.governmentOfficeName = governmentOfficeName;    
	
}

	public String govtOffcNameTrns;
	
	


	public String getGovtOffcNameTrns() {
		return govtOffcNameTrns;
	}
	public void setGovtOffcNameTrns(String govtOffcNameTrns) {
		this.govtOffcNameTrns = govtOffcNameTrns;
	}
	
	  //Added for aadhar integration 
    private String partyDisplayAadhar;


	public String getPartyDisplayAadhar() {
		return partyDisplayAadhar;
	}
	public void setPartyDisplayAadhar(String partyDisplayAadhar) {
		this.partyDisplayAadhar = partyDisplayAadhar;
	}
		
	private String aadharNameTrns;
	
	private String aadharNameTrnsOwner;

	private AadharRespDTO aadharRespDto;
	private AadharDTO aadharDto;
	private String acknowledgementId;
	private String transactionId;
	
	private String govtOfficeName;
	
	public String getGovtOfficeName() {
		return govtOfficeName;
	}
	public void setGovtOfficeName(String govtOfficeName) {
		this.govtOfficeName = govtOfficeName;
	}
	public String getAadharNameTrnsOwner() {
		return aadharNameTrnsOwner;
	}
	public void setAadharNameTrnsOwner(String aadharNameTrnsOwner) {
		this.aadharNameTrnsOwner = aadharNameTrnsOwner;
	}
	public String getAadharNameTrns() {
		return aadharNameTrns;
	}
	public void setAadharNameTrns(String aadharNameTrns) {
		this.aadharNameTrns = aadharNameTrns;
	}
	public AadharRespDTO getAadharRespDto() {
		return aadharRespDto;
	}
	public void setAadharRespDto(AadharRespDTO aadharRespDto) {
		this.aadharRespDto = aadharRespDto;
	}
	public AadharDTO getAadharDto() {
		return aadharDto;
	}
	public void setAadharDto(AadharDTO aadharDto) {
		this.aadharDto = aadharDto;
	}
	public String getAcknowledgementId() {
		return acknowledgementId;
	}
	public void setAcknowledgementId(String acknowledgementId) {
		this.acknowledgementId = acknowledgementId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
	public String ownerIndstatenameTrns;
	public String ownerInddistrictTrns;
	public String ownerInddistrictTrnsName;
	public String ownerIndphnoTrns;
	public String ownerPostalCodeTrns;
	public String ownerIndCategoryTrns;
	public String ownerIndCategoryTrnsName;
	public String getOwnerIndCategoryTrnsName() {
		return ownerIndCategoryTrnsName;
	}
	public String getOwnerInddistrictTrnsName() {
		return ownerInddistrictTrnsName;
	}
	public void setOwnerInddistrictTrnsName(String ownerInddistrictTrnsName) {
		this.ownerInddistrictTrnsName = ownerInddistrictTrnsName;
	}
	public String getOwnerRelationshipTrnsName() {
		return ownerRelationshipTrnsName;
	}
	public void setOwnerRelationshipTrnsName(String ownerRelationshipTrnsName) {
		this.ownerRelationshipTrnsName = ownerRelationshipTrnsName;
	}
	public void setOwnerIndCategoryTrnsName(String ownerIndCategoryTrnsName) {
		this.ownerIndCategoryTrnsName = ownerIndCategoryTrnsName;
	}
	public String ownerIndDisabilityTrns;
	public String ownerIndDisabilityDescTrns;
	public String ownerIndMinorityTrns;
	public String ownerIndPovertyLineTrns;
	public String ownerOccupationIdTrns;
	public String getOwnerOccupationIdTrnsName() {
		return ownerOccupationIdTrnsName;
	}
	public void setOwnerOccupationIdTrnsName(String ownerOccupationIdTrnsName) {
		this.ownerOccupationIdTrnsName = ownerOccupationIdTrnsName;
	}
	public String ownerOccupationIdTrnsName;
	public String ownerRelationshipTrns;
	public String ownerRelationshipTrnsName;
	public String ownerFatherNameTrns;
	public String ownerMotherNameTrns;
	public String ownerSpouseNameTrns;
	public String ownerIndcountryTrns;
	
	
	private String ownerIndScheduleAreaTrns;
	private String ownerPermissionNoTrns;
	private String ownerCertificateTrns;
	
	private String ownerPanNumberTrns;
	public String getOwnerPanNumberTrns() {
		return ownerPanNumberTrns;
	}
	public void setOwnerPanNumberTrns(String ownerPanNumberTrns) {
		this.ownerPanNumberTrns = ownerPanNumberTrns;
	}
	public String getOwnerIndcountryTrns() {
		return ownerIndcountryTrns;
	}
	public void setOwnerIndcountryTrns(String ownerIndcountryTrns) {
		this.ownerIndcountryTrns = ownerIndcountryTrns;
	}
	public String getOwnerIndstatenameTrns() {
		return ownerIndstatenameTrns;
	}
	public void setOwnerIndstatenameTrns(String ownerIndstatenameTrns) {
		this.ownerIndstatenameTrns = ownerIndstatenameTrns;
	}
	public String getOwnerInddistrictTrns() {
		return ownerInddistrictTrns;
	}
	public void setOwnerInddistrictTrns(String ownerInddistrictTrns) {
		this.ownerInddistrictTrns = ownerInddistrictTrns;
	}
	public String getOwnerIndphnoTrns() {
		return ownerIndphnoTrns;
	}
	public void setOwnerIndphnoTrns(String ownerIndphnoTrns) {
		this.ownerIndphnoTrns = ownerIndphnoTrns;
	}
	public String getOwnerPostalCodeTrns() {
		return ownerPostalCodeTrns;
	}
	public void setOwnerPostalCodeTrns(String ownerPostalCodeTrns) {
		this.ownerPostalCodeTrns = ownerPostalCodeTrns;
	}
	public String getOwnerIndCategoryTrns() {
		return ownerIndCategoryTrns;
	}
	public void setOwnerIndCategoryTrns(String ownerIndCategoryTrns) {
		this.ownerIndCategoryTrns = ownerIndCategoryTrns;
	}
	public String getOwnerIndDisabilityTrns() {
		return ownerIndDisabilityTrns;
	}
	public void setOwnerIndDisabilityTrns(String ownerIndDisabilityTrns) {
		this.ownerIndDisabilityTrns = ownerIndDisabilityTrns;
	}
	public String getOwnerIndDisabilityDescTrns() {
		return ownerIndDisabilityDescTrns;
	}
	public void setOwnerIndDisabilityDescTrns(String ownerIndDisabilityDescTrns) {
		this.ownerIndDisabilityDescTrns = ownerIndDisabilityDescTrns;
	}
	public String getOwnerIndMinorityTrns() {
		return ownerIndMinorityTrns;
	}
	public void setOwnerIndMinorityTrns(String ownerIndMinorityTrns) {
		this.ownerIndMinorityTrns = ownerIndMinorityTrns;
	}
	public String getOwnerIndPovertyLineTrns() {
		return ownerIndPovertyLineTrns;
	}
	public void setOwnerIndPovertyLineTrns(String ownerIndPovertyLineTrns) {
		this.ownerIndPovertyLineTrns = ownerIndPovertyLineTrns;
	}
	public String getOwnerOccupationIdTrns() {
		return ownerOccupationIdTrns;
	}
	public void setOwnerOccupationIdTrns(String ownerOccupationIdTrns) {
		this.ownerOccupationIdTrns = ownerOccupationIdTrns;
	}
	public String getOwnerRelationshipTrns() {
		return ownerRelationshipTrns;
	}
	public void setOwnerRelationshipTrns(String ownerRelationshipTrns) {
		this.ownerRelationshipTrns = ownerRelationshipTrns;
	}
	public String getOwnerFatherNameTrns() {
		return ownerFatherNameTrns;
	}
	public void setOwnerFatherNameTrns(String ownerFatherNameTrns) {
		this.ownerFatherNameTrns = ownerFatherNameTrns;
	}
	public String getOwnerMotherNameTrns() {
		return ownerMotherNameTrns;
	}
	public void setOwnerMotherNameTrns(String ownerMotherNameTrns) {
		this.ownerMotherNameTrns = ownerMotherNameTrns;
	}
	public String getOwnerSpouseNameTrns() {
		return ownerSpouseNameTrns;
	}
	public void setOwnerSpouseNameTrns(String ownerSpouseNameTrns) {
		this.ownerSpouseNameTrns = ownerSpouseNameTrns;
	}
	public String getOwnerIndScheduleAreaTrns() {
		return ownerIndScheduleAreaTrns;
	}
	public void setOwnerIndScheduleAreaTrns(String ownerIndScheduleAreaTrns) {
		this.ownerIndScheduleAreaTrns = ownerIndScheduleAreaTrns;
	}
	public String getOwnerPermissionNoTrns() {
		return ownerPermissionNoTrns;
	}
	public void setOwnerPermissionNoTrns(String ownerPermissionNoTrns) {
		this.ownerPermissionNoTrns = ownerPermissionNoTrns;
	}
	public String getOwnerCertificateTrns() {
		return ownerCertificateTrns;
	}
	public void setOwnerCertificateTrns(String ownerCertificateTrns) {
		this.ownerCertificateTrns = ownerCertificateTrns;
	}
	public void setOwnerRelationShipList(ArrayList ownerRelationShipList) {
		this.ownerRelationShipList = ownerRelationShipList;
	}
	public ArrayList getOwnerRelationShipList() {
		return ownerRelationShipList;
	}
	
}