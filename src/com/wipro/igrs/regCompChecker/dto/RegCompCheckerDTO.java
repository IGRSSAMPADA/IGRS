package com.wipro.igrs.regCompChecker.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.newreginit.dto.AadharDTO;
import com.wipro.igrs.newreginit.dto.CommonDTO;
import com.wipro.igrs.reginit.constant.RegInitConstant;

public class RegCompCheckerDTO implements Serializable {
	
	private ArrayList khasraDetlsDisplay=new ArrayList();
	private String propDetails;
	 private HashMap mapBuilding = new HashMap();
	private String regInitNumber;
	private String actionName;
	private String regCompCheckerForm;
	private int deedID;
	private String oldRegistrationNumber;
	private String userError;
	private String lang;
	//akansha
	private ArrayList exemptionName = new ArrayList();
	private ArrayList exemptionON = new ArrayList();
	private ArrayList regExemption=new ArrayList();
	private ArrayList stampExemption=new ArrayList();
	private ArrayList exemptionList=new ArrayList();
	
	public String getUserError() {
		return userError;
	}

	public void setUserError(String userError) {
		this.userError = userError;
	}

	private int instID;
	private String totalMarketValue;
	private boolean isModify; 
	private String stampduty1;
	private String stampdutyToBePaid;
	private String stampdutyAlreadyPaid;
	private String remarks;
	private String nagarPalikaDuty;
	private String panchayatDuty;
	private String upkarDuty;
	private String totalduty ;
	private String purpose;
	private String registrationFee;
	private String registrationFeeToBePaid;
	private String registrationFeeAlreadyPaid;
	private String propId;
	
	private String regInitPaymntTxnId;
	private String regInitPhysicalStamp;
	private String typeFlag;

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public String getRegInitPhysicalStamp() {
		return regInitPhysicalStamp;
	}

	public void setRegInitPhysicalStamp(String regInitPhysicalStamp) {
		this.regInitPhysicalStamp = regInitPhysicalStamp;
	}

	private String regInitEstampCode;
	private String deedType;
	private String instType;
	
	private String propertyTypeId;
	private String propertyTypeName;
	private String propAddress;
	private String municipalBodyName;
    private String patwariStatus;
    private String vikasId;
    private String ricircle;
    private String layoutdet;
    private String khasaraNum;
    private String north;
    private String south;
    private String east;
    private String west;
    private String sheetnum;
	private String plotnum;
	private String wardpatwarName;
	private String mohallaName;
	private String rinpustikaNum;
	private String propertyId;
	private String oldPropertyId;
	private String oldRegInitNumber;
	private String pinNumber;
	private boolean isAdj;
	private String adjNumber;
	private int floorCount;
	private String propLandmark;
	private String estampTxnId;
	private String estampStatus;
	private String isEstamp;
	private ArrayList selectedSubClauseList = new ArrayList();
	private String sroId;
	private String sroName;
	private int commonDeedFlag;
	private String transactionType;
	// for transacting party details
	
	private String lastDueDate;
	//added by vinay
	private String holdCheck;
	
	private String holdDocumentPath;
	private String holdDocumentName;
	private String previousRemarks;
	private String witnessphotoPath;
	private String consenterPhotoPath;
	public String getWitnessphotoPath() {
		return witnessphotoPath;
	}

	public void setWitnessphotoPath(String witnessphotoPath) {
		this.witnessphotoPath = witnessphotoPath;
	}

	public String getConsenterPhotoPath() {
		return consenterPhotoPath;
	}

	public void setConsenterPhotoPath(String consenterPhotoPath) {
		this.consenterPhotoPath = consenterPhotoPath;
	}

	public String getPreviousRemarks() {
		return previousRemarks;
	}

	public void setPreviousRemarks(String previousRemarks) {
		this.previousRemarks = previousRemarks;
	}

	public String getHoldDocumentPath() {
		return holdDocumentPath;
	}

	public void setHoldDocumentPath(String holdDocumentPath) {
		this.holdDocumentPath = holdDocumentPath;
	}

	public String getHoldDocumentName() {
		return holdDocumentName;
	}

	public void setHoldDocumentName(String holdDocumentName) {
		this.holdDocumentName = holdDocumentName;
	}

	public String getHoldCheck() {
		return holdCheck;
	}

	public void setHoldCheck(String holdCheck) {
		this.holdCheck = holdCheck;
	}

	public boolean isAdj() {
		return isAdj;
	}

	public String getAdjNumber() {
		return adjNumber;
	}

	public void setAdjNumber(String adjNumber) {
		this.adjNumber = adjNumber;
	}

	public void setAdj(boolean isAdj) {
		this.isAdj = isAdj;
	}

	private String listAdoptedPartyTrns;
	private String listAdoptedPartyName;
	private String indcountryName;
	private String indstatenameName;
	private String inddistrictName;
	private String countryName;
	private String statenameName;
	private String districtName;
	private String listAdoptedPartyNameTrns;
	private String applicantOrTransParty;
	private String individualOrOrganization;
	private String partyRoleTypeId;
	private String ownershipShareTrns;
	private String relationWithOwnerTrns;
	private String shareOfPropTrns;
	private String ogrNameTrns;
	private String authPerNameTrns;
	private String fnameTrns;
	private String mnameTrns; 
	private String lnameTrns;
	private String gendarTrns = "M";
	private String ageTrns;
	private String fatherNameTrns;
	private String motherNameTrns;
	// added Guardian Field - Rahul
	private String guardianTrns;
	
	public String getGuardianTrns() {
		return guardianTrns;
	}

	public void setGuardianTrns(String guardianTrns) {
		this.guardianTrns = guardianTrns;
	}

	private String spouseNameTrns;
	private String nationalityTrns;
	private String postalCodeTrns;
	private String emailIDTrns;
	private String indaddressTrns;
	//private String indcountryTrns;
	private String indstatenameTrns;
	//private String inddistrictTrns;
	private String indphnoTrns;
	private String indmobnoTrns;
	private String listIDTrns;
	private String idnoTrns;
	private String bnameTrns;
	private String baddressTrns;
	
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
	private String selectedGender;
	private String indReligionTrns;
	private String indCasteTrns;
	private String indDisabilityTrns;
	private String roleName;
	private String orgaddressTrns;
	private String mobnoTrns;
	private String phnoTrns;
	private String partyTypeTrns;
	private String ownerNameTrns;
	private String ownerOgrNameTrns;
	private String ownerGendarTrns;
	private String ownerNationalityTrns;
	private String ownerAddressTrns;
	private String ownerPhnoTrns;
	private String ownerEmailIDTrns;
	private String ownerIdnoTrns;
	private String ownerAgeTrns;
	private String ownerListIDTrns;
	private String ownerProofNameTrns;
	private String partyRoleId;
	private String role;
	private String partyTxnId;
	private String partyId;
	private String paymentType;
	private String sourceMod;
	private String compChcek;
	private String id;
	
	private String indcountryNameTrns;
	private String indstatenameNameTrns;
	private String inddistrictNameTrns;
	
	private String listIDNameTrns;
	
	
	private String countryTrns;
	private String statenameTrns;
	private String districtTrns;
	private String countryNameTrns;
	private String statenameNameTrns;
	private String districtNameTrns;
	
	private int totalPoaCount=0;
	private int totalOwnerCount=0;
	private int ownerCount=0;
	private int sellerSelfCount=0;
	private int sellerPoaCount=0;
	private int buyerCount=0;
	private int donorCount=0;
	private int doneeCount=0;
	private int poaHolderCount=0;
	private int poaAccepterCount=0;
	private int party1OwnerCount=0;
	private int party2OwnerCount=0;
	private int party1PoaHolderCount=0;
	private int party2PoaHolderCount=0;
	private String marketValueTrns;
	private String consiAmountTrns;
	
	private int addMoreCounter;
	private String indAuthPersn;
	private String witnessTxnNummber;
	private String consentorTxnNumber;
	private String selectedWitnessTxnNummber;
	
	private String isGovtOfficial;
	private String typeOfGovtEmp;
	private String govtOfficial;
	private String execDate;
	private String selectedPartyIds;
	private String name;
	private String userDOBTrns;
	private String selectedCategoryName;
	private String indCategoryTrns;
	private String indDisabilityDescTrns;
	private String indMinorityTrns;
	private String indPovertyLineTrns;
	private String selectedOccupationName;
	private String indScheduleAreaTrns;
	private String indScheduleAreaTrnsDisplay;
	private String permissionNoTrns;
	private String ownerDOBTrns;
	private String hdnDeclareShareCheck;
	private String isUpload;
	
	// Added new for Full party name - RAhul
	private String PartyRoleFullNamePOA;
	
	
	public String getIndAuthPersn() {
		return indAuthPersn;
	}

	public void setIndAuthPersn(String indAuthPersn) {
		this.indAuthPersn = indAuthPersn;
	}

	public String getListAdoptedPartyTrns() {
		return listAdoptedPartyTrns;
	}
	
	
	

	public void setListAdoptedPartyTrns(String listAdoptedPartyTrns) {
		this.listAdoptedPartyTrns = listAdoptedPartyTrns;
	}

	public String getListAdoptedPartyNameTrns() {
		return listAdoptedPartyNameTrns;
	}

	public void setListAdoptedPartyNameTrns(String listAdoptedPartyNameTrns) {
		this.listAdoptedPartyNameTrns = listAdoptedPartyNameTrns;
	}

	public String getApplicantOrTransParty() {
		return applicantOrTransParty;
	}

	public void setApplicantOrTransParty(String applicantOrTransParty) {
		this.applicantOrTransParty = applicantOrTransParty;
	}

	public String getIndividualOrOrganization() {
		return individualOrOrganization;
	}

	public void setIndividualOrOrganization(String individualOrOrganization) {
		this.individualOrOrganization = individualOrOrganization;
	}

	public String getPartyRoleTypeId() {
		return partyRoleTypeId;
	}

	public void setPartyRoleTypeId(String partyRoleTypeId) {
		this.partyRoleTypeId = partyRoleTypeId;
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
	
	
	
	
	
	    
	    public String getSelectedWitnessTxnNummber() {
		return selectedWitnessTxnNummber;
	}

	public void setSelectedWitnessTxnNummber(String selectedWitnessTxnNummber) {
		this.selectedWitnessTxnNummber = selectedWitnessTxnNummber;
	}

		public String getPartyRoleId() {
		return partyRoleId;
	}

	public void setPartyRoleId(String partyRoleId) {
		this.partyRoleId = partyRoleId;
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

		public ArrayList getKhasraDetlsDisplay() {
		return khasraDetlsDisplay;
	}
		
		

	public String getSelectedPhotoId() {
			return selectedPhotoId;
		}

		public void setSelectedPhotoId(String selectedPhotoId) {
			this.selectedPhotoId = selectedPhotoId;
		}

		public String getSelectedPhotoIdName() {
			return selectedPhotoIdName;
		}

		public void setSelectedPhotoIdName(String selectedPhotoIdName) {
			this.selectedPhotoIdName = selectedPhotoIdName;
		}

		public String getSelectedCountry() {
			return selectedCountry;
		}

		public void setSelectedCountry(String selectedCountry) {
			this.selectedCountry = selectedCountry;
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
		
		
		
		

	public String getSelectedGender() {
			return selectedGender;
		}

		public void setSelectedGender(String selectedGender) {
			this.selectedGender = selectedGender;
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

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getOrgaddressTrns() {
			return orgaddressTrns;
		}

		public void setOrgaddressTrns(String orgaddressTrns) {
			this.orgaddressTrns = orgaddressTrns;
		}

		public String getMobnoTrns() {
			return mobnoTrns;
		}

		public void setMobnoTrns(String mobnoTrns) {
			this.mobnoTrns = mobnoTrns;
		}

		public String getPhnoTrns() {
			return phnoTrns;
		}

		public void setPhnoTrns(String phnoTrns) {
			this.phnoTrns = phnoTrns;
		}

		public String getPartyTypeTrns() {
			return partyTypeTrns;
		}

		public void setPartyTypeTrns(String partyTypeTrns) {
			this.partyTypeTrns = partyTypeTrns;
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

		public String getOwnerListIDTrns() {
			return ownerListIDTrns;
		}

		public void setOwnerListIDTrns(String ownerListIDTrns) {
			this.ownerListIDTrns = ownerListIDTrns;
		}

		public String getOwnerProofNameTrns() {
			return ownerProofNameTrns;
		}

		public void setOwnerProofNameTrns(String ownerProofNameTrns) {
			this.ownerProofNameTrns = ownerProofNameTrns;
		}

	public void setKhasraDetlsDisplay(ArrayList khasraDetlsDisplay) {
		this.khasraDetlsDisplay = khasraDetlsDisplay;
	}
	
	

		public HashMap getMapBuilding() {
		return mapBuilding;
	}

	public void setMapBuilding(HashMap mapBuilding) {
		this.mapBuilding = mapBuilding;
	}

		public String getSheetnum() {
		return sheetnum;
	}

	public void setSheetnum(String sheetnum) {
		this.sheetnum = sheetnum;
	}

	public String getPlotnum() {
		return plotnum;
	}

	public void setPlotnum(String plotnum) {
		this.plotnum = plotnum;
	}

		public String getPropAddress() {
		return propAddress;
	}

	public void setPropAddress(String propAddress) {
		this.propAddress = propAddress;
	}

	public String getMunicipalBodyName() {
		return municipalBodyName;
	}

	public void setMunicipalBodyName(String municipalBodyName) {
		this.municipalBodyName = municipalBodyName;
	}

	public String getPatwariStatus() {
		return patwariStatus;
	}

	public void setPatwariStatus(String patwariStatus) {
		this.patwariStatus = patwariStatus;
	}

	public String getVikasId() {
		return vikasId;
	}

	public void setVikasId(String vikasId) {
		this.vikasId = vikasId;
	}

	public String getRicircle() {
		return ricircle;
	}

	public void setRicircle(String ricircle) {
		this.ricircle = ricircle;
	}

	public String getLayoutdet() {
		return layoutdet;
	}

	public void setLayoutdet(String layoutdet) {
		this.layoutdet = layoutdet;
	}

	public String getKhasaraNum() {
		return khasaraNum;
	}

	public void setKhasaraNum(String khasaraNum) {
		this.khasaraNum = khasaraNum;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

		public String getPropertyTypeId() {
			return propertyTypeId;
		}

		public void setPropertyTypeId(String propertyTypeId) {
			this.propertyTypeId = propertyTypeId;
		}

		public String getPropertyTypeName() {
			return propertyTypeName;
		}

		public void setPropertyTypeName(String propertyTypeName) {
			this.propertyTypeName = propertyTypeName;
		}

		public String getDistId() {
			return distId;
		}

		public void setDistId(String distId) {
			this.distId = distId;
		}

		public String getDistName() {
			return distName;
		}

		public void setDistName(String distName) {
			this.distName = distName;
		}

		public String getTehsilId() {
			return tehsilId;
		}

		public void setTehsilId(String tehsilId) {
			this.tehsilId = tehsilId;
		}

		public String getTehsilName() {
			return tehsilName;
		}

		public void setTehsilName(String tehsilName) {
			this.tehsilName = tehsilName;
		}

		public String getAreaTypeId() {
			return areaTypeId;
		}

		public void setAreaTypeId(String areaTypeId) {
			this.areaTypeId = areaTypeId;
		}

		public String getAreaTypeName() {
			return areaTypeName;
		}

		public void setAreaTypeName(String areaTypeName) {
			this.areaTypeName = areaTypeName;
		}

		public double getTotalSqMeter() {
			return totalSqMeter;
		}

		public void setTotalSqMeter(double totalSqMeter) {
			this.totalSqMeter = totalSqMeter;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		private String distId;
	    
	    private String distName;
	    private String tehsilId;
	    private String tehsilName;
	    private String areaTypeId;
	    private String areaTypeName;
	    
	    private double totalSqMeter;
	    private String unit;
	    private String bookId;
	    private String bookName;
	    private String page;
	    private String totalSqMeterDisplay;
	
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	

	

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getDeedType() {
		return deedType;
	}

	public void setDeedType(String deedType) {
		this.deedType = deedType;
	}

	public String getInstType() {
		return instType;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}

	public String getRegInitPaymntTxnId() {
		return regInitPaymntTxnId;
	}

	public void setRegInitPaymntTxnId(String regInitPaymntTxnId) {
		this.regInitPaymntTxnId = regInitPaymntTxnId;
	}

	public String getRegInitEstampCode() {
		return regInitEstampCode;
	}

	public void setRegInitEstampCode(String regInitEstampCode) {
		this.regInitEstampCode = regInitEstampCode;
	}

	
	
	
	

	public String getStampduty1() {
		return stampduty1;
	}

	public void setStampduty1(String stampduty1) {
		this.stampduty1 = stampduty1;
	}

	public String getNagarPalikaDuty() {
		return nagarPalikaDuty;
	}

	public void setNagarPalikaDuty(String nagarPalikaDuty) {
		this.nagarPalikaDuty = nagarPalikaDuty;
	}

	public String getPanchayatDuty() {
		return panchayatDuty;
	}

	public void setPanchayatDuty(String panchayatDuty) {
		this.panchayatDuty = panchayatDuty;
	}

	public String getUpkarDuty() {
		return upkarDuty;
	}

	public void setUpkarDuty(String upkarDuty) {
		this.upkarDuty = upkarDuty;
	}

	public String getTotalduty() {
		return totalduty;
	}

	public void setTotalduty(String totalduty) {
		this.totalduty = totalduty;
	}

	public String getRegistrationFee() {
		return registrationFee;
	}

	public void setRegistrationFee(String registrationFee) {
		this.registrationFee = registrationFee;
	}

	public int getDeedID() {
		return deedID;
	}

	public void setDeedID(int deedID) {
		this.deedID = deedID;
	}

	public int getInstID() {
		return instID;
	}

	public void setInstID(int instID) {
		this.instID = instID;
	}

	public String getTotalMarketValue() {
		return totalMarketValue;
	}

	public void setTotalMarketValue(String totalMarketValue) {
		this.totalMarketValue = totalMarketValue;
	}

	public String getRegCompCheckerForm() {
		return regCompCheckerForm;
	}

	public void setRegCompCheckerForm(String regCompCheckerForm) {
		this.regCompCheckerForm = regCompCheckerForm;
	}

	public String getRegInitNumber() {
		return regInitNumber;
	}

	public void setRegInitNumber(String regInitNumber) {
		this.regInitNumber = regInitNumber;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getWardpatwarName() {
		return wardpatwarName;
	}

	public void setWardpatwarName(String wardpatwarName) {
		this.wardpatwarName = wardpatwarName;
	}

	public String getMohallaName() {
		return mohallaName;
	}

	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}

	public String getRinpustikaNum() {
		return rinpustikaNum;
	}

	public void setRinpustikaNum(String rinpustikaNum) {
		this.rinpustikaNum = rinpustikaNum;
	}

	public String getIndcountryNameTrns() {
		return indcountryNameTrns;
	}

	public void setIndcountryNameTrns(String indcountryNameTrns) {
		this.indcountryNameTrns = indcountryNameTrns;
	}

	public String getIndstatenameNameTrns() {
		return indstatenameNameTrns;
	}

	public void setIndstatenameNameTrns(String indstatenameNameTrns) {
		this.indstatenameNameTrns = indstatenameNameTrns;
	}

	public String getInddistrictNameTrns() {
		return inddistrictNameTrns;
	}

	public void setInddistrictNameTrns(String inddistrictNameTrns) {
		this.inddistrictNameTrns = inddistrictNameTrns;
	}

	public String getListIDNameTrns() {
		return listIDNameTrns;
	}

	public void setListIDNameTrns(String listIDNameTrns) {
		this.listIDNameTrns = listIDNameTrns;
	}

	public String getCountryTrns() {
		return countryTrns;
	}

	public void setCountryTrns(String countryTrns) {
		this.countryTrns = countryTrns;
	}

	public String getStatenameTrns() {
		return statenameTrns;
	}

	public void setStatenameTrns(String statenameTrns) {
		this.statenameTrns = statenameTrns;
	}

	public String getDistrictTrns() {
		return districtTrns;
	}

	public void setDistrictTrns(String districtTrns) {
		this.districtTrns = districtTrns;
	}

	public String getCountryNameTrns() {
		return countryNameTrns;
	}

	public void setCountryNameTrns(String countryNameTrns) {
		this.countryNameTrns = countryNameTrns;
	}

	public String getStatenameNameTrns() {
		return statenameNameTrns;
	}

	public void setStatenameNameTrns(String statenameNameTrns) {
		this.statenameNameTrns = statenameNameTrns;
	}

	public String getDistrictNameTrns() {
		return districtNameTrns;
	}

	public void setDistrictNameTrns(String districtNameTrns) {
		this.districtNameTrns = districtNameTrns;
	}

	public int getTotalPoaCount() {
		return totalPoaCount;
	}

	public void setTotalPoaCount(int totalPoaCount) {
		this.totalPoaCount = totalPoaCount;
	}

	public int getTotalOwnerCount() {
		return totalOwnerCount;
	}

	public void setTotalOwnerCount(int totalOwnerCount) {
		this.totalOwnerCount = totalOwnerCount;
	}

	public int getOwnerCount() {
		return ownerCount;
	}

	public void setOwnerCount(int ownerCount) {
		this.ownerCount = ownerCount;
	}

	public int getSellerSelfCount() {
		return sellerSelfCount;
	}

	public void setSellerSelfCount(int sellerSelfCount) {
		this.sellerSelfCount = sellerSelfCount;
	}

	public int getSellerPoaCount() {
		return sellerPoaCount;
	}

	public void setSellerPoaCount(int sellerPoaCount) {
		this.sellerPoaCount = sellerPoaCount;
	}

	public int getBuyerCount() {
		return buyerCount;
	}

	public void setBuyerCount(int buyerCount) {
		this.buyerCount = buyerCount;
	}

	public int getDonorCount() {
		return donorCount;
	}

	public void setDonorCount(int donorCount) {
		this.donorCount = donorCount;
	}

	public int getDoneeCount() {
		return doneeCount;
	}

	public void setDoneeCount(int doneeCount) {
		this.doneeCount = doneeCount;
	}

	public int getPoaHolderCount() {
		return poaHolderCount;
	}

	public void setPoaHolderCount(int poaHolderCount) {
		this.poaHolderCount = poaHolderCount;
	}

	public int getPoaAccepterCount() {
		return poaAccepterCount;
	}

	public void setPoaAccepterCount(int poaAccepterCount) {
		this.poaAccepterCount = poaAccepterCount;
	}

	public int getParty1OwnerCount() {
		return party1OwnerCount;
	}

	public void setParty1OwnerCount(int party1OwnerCount) {
		this.party1OwnerCount = party1OwnerCount;
	}

	public int getParty2OwnerCount() {
		return party2OwnerCount;
	}

	public void setParty2OwnerCount(int party2OwnerCount) {
		this.party2OwnerCount = party2OwnerCount;
	}

	public int getParty1PoaHolderCount() {
		return party1PoaHolderCount;
	}

	public void setParty1PoaHolderCount(int party1PoaHolderCount) {
		this.party1PoaHolderCount = party1PoaHolderCount;
	}

	public int getParty2PoaHolderCount() {
		return party2PoaHolderCount;
	}

	public void setParty2PoaHolderCount(int party2PoaHolderCount) {
		this.party2PoaHolderCount = party2PoaHolderCount;
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

	public int getAddMoreCounter() {
		return addMoreCounter;
	}

	public void setAddMoreCounter(int addMoreCounter) {
		this.addMoreCounter = addMoreCounter;
	}

	public String getListAdoptedPartyName() {
		return listAdoptedPartyName;
	}

	public void setListAdoptedPartyName(String listAdoptedPartyName) {
		this.listAdoptedPartyName = listAdoptedPartyName;
	}

	

	public String getIndcountryName() {
		return indcountryName;
	}

	public void setIndcountryName(String indcountryName) {
		this.indcountryName = indcountryName;
	}

	public String getIndstatenameName() {
		return indstatenameName;
	}

	public void setIndstatenameName(String indstatenameName) {
		this.indstatenameName = indstatenameName;
	}

	public String getInddistrictName() {
		return inddistrictName;
	}

	public void setInddistrictName(String inddistrictName) {
		this.inddistrictName = inddistrictName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getStatenameName() {
		return statenameName;
	}

	public void setStatenameName(String statenameName) {
		this.statenameName = statenameName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPartyTxnId() {
		return partyTxnId;
	}

	public void setPartyTxnId(String partyTxnId) {
		this.partyTxnId = partyTxnId;
	}

	public boolean isModify() {
		return isModify;
	}

	public void setModify(boolean isModify) {
		this.isModify = isModify;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropDetails() {
		return propDetails;
	}

	public void setPropDetails(String propDetails) {
		this.propDetails = propDetails;
	}

	public String getStampdutyToBePaid() {
		return stampdutyToBePaid;
	}

	public void setStampdutyToBePaid(String stampdutyToBePaid) {
		this.stampdutyToBePaid = stampdutyToBePaid;
	}

	public String getStampdutyAlreadyPaid() {
		return stampdutyAlreadyPaid;
	}

	public void setStampdutyAlreadyPaid(String stampdutyAlreadyPaid) {
		this.stampdutyAlreadyPaid = stampdutyAlreadyPaid;
	}

	public String getRegistrationFeeToBePaid() {
		return registrationFeeToBePaid;
	}

	public void setRegistrationFeeToBePaid(String registrationFeeToBePaid) {
		this.registrationFeeToBePaid = registrationFeeToBePaid;
	}

	public String getRegistrationFeeAlreadyPaid() {
		return registrationFeeAlreadyPaid;
	}

	public void setRegistrationFeeAlreadyPaid(String registrationFeeAlreadyPaid) {
		this.registrationFeeAlreadyPaid = registrationFeeAlreadyPaid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getWitnessTxnNummber() {
		return witnessTxnNummber;
	}

	public void setWitnessTxnNummber(String witnessTxnNummber) {
		this.witnessTxnNummber = witnessTxnNummber;
	}

	public String getOldPropertyId() {
		return oldPropertyId;
	}

	public void setOldPropertyId(String oldPropertyId) {
		this.oldPropertyId = oldPropertyId;
	}

	public String getOldRegInitNumber() {
		return oldRegInitNumber;
	}

	public void setOldRegInitNumber(String oldRegInitNumber) {
		this.oldRegInitNumber = oldRegInitNumber;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getIsGovtOfficial() {
		return isGovtOfficial;
	}

	public void setIsGovtOfficial(String isGovtOfficial) {
		this.isGovtOfficial = isGovtOfficial;
	}

	public String getTypeOfGovtEmp() {
		return typeOfGovtEmp;
	}

	public void setTypeOfGovtEmp(String typeOfGovtEmp) {
		this.typeOfGovtEmp = typeOfGovtEmp;
	}

	public String getGovtOfficial() {
		return govtOfficial;
	}

	public void setGovtOfficial(String govtOfficial) {
		this.govtOfficial = govtOfficial;
	}

	public String getExecDate() {
		return execDate;
	}

	public void setExecDate(String execDate) {
		this.execDate = execDate;
	}

	public String getSelectedPartyIds() {
		return selectedPartyIds;
	}

	public void setSelectedPartyIds(String selectedPartyIds) {
		this.selectedPartyIds = selectedPartyIds;
	}
	 
	
	
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
	private String firstCheck;
	private String status;
	private String govtOfficial2;
	private String govtLetter;
	private String document;
	private String photoTypeId;
	private String photoProofTypeName;
	private String complianceId;
	private String complianceName;
	private String selectedCompliance;
	private String caveatTxnId;
	private String caveatId;
	private String caveatName;
	private String checkListTxnId;
	private String deedOutIndiaFlg;
	private String courtDecFlg;
	private String courtDecAplFlg;
	private String docAftrDeathFlg;
	private String poaFlg;
	private String poaMpFlg;
	private String poaMpFlg1;
	private String deedOutIndDate;
	private String orderDate;
	private String lastAplDate;
	private String deathCert;
	private String relationProof;
	private String comments;
	private String poaAuthNo;
	private String uplaReltnProof;
	private String upldDeathCert;
	private String uplaReltnProofPath;
	private String upldDeathCertPath;
	private String totalBal;
	private String netBal;
	private String regCompleteId;
	private String dutyAtRegInit;
	private String regFeeAtRegInit;
	private String dutyAtRegMkr;
	private String regFeeAtRegMkr;
	private String dutyAtRegChkr;
	private String regFeeAtRegChkr;
	private String netStampDuty;
	private String netRegFee;
	private String netPaidAmt;
	private String netBalAmt;
	private String stampDutyPmt;
	private String regFeePmt;
	private String stmp;
	private String holdId;
	private String holdPageId;
	private String holdName;
	private String holdFlag;
	private String govtOffId;
	private String govtOffName;
	private String reprsnName;
	private String upload;
	private String modify;
	private String checkBox;
	private String filePathPOA;
	private String filePath;
	private String filePathRltn;
	
	
	
	
	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getReprsnName() {
		return reprsnName;
	}

	public void setReprsnName(String reprsnName) {
		this.reprsnName = reprsnName;
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

	public String getStmp() {
		return stmp;
	}

	public void setStmp(String stmp) {
		this.stmp = stmp;
	}

	public String getStampDutyPmt() {
		return stampDutyPmt;
	}

	public void setStampDutyPmt(String stampDutyPmt) {
		this.stampDutyPmt = stampDutyPmt;
	}

	public String getRegFeePmt() {
		return regFeePmt;
	}

	public void setRegFeePmt(String regFeePmt) {
		this.regFeePmt = regFeePmt;
	}

	public String getHoldFlag() {
		return holdFlag;
	}

	public void setHoldFlag(String holdFlag) {
		this.holdFlag = holdFlag;
	}

	public String getNetStampDuty() {
		return netStampDuty;
	}

	public void setNetStampDuty(String netStampDuty) {
		this.netStampDuty = netStampDuty;
	}

	public String getNetRegFee() {
		return netRegFee;
	}

	public void setNetRegFee(String netRegFee) {
		this.netRegFee = netRegFee;
	}

	public String getDutyAtRegChkr() {
		return dutyAtRegChkr;
	}

	public void setDutyAtRegChkr(String dutyAtRegChkr) {
		this.dutyAtRegChkr = dutyAtRegChkr;
	}

	public String getRegFeeAtRegChkr() {
		return regFeeAtRegChkr;
	}

	public void setRegFeeAtRegChkr(String regFeeAtRegChkr) {
		this.regFeeAtRegChkr = regFeeAtRegChkr;
	}

	public String getDutyAtRegMkr() {
		return dutyAtRegMkr;
	}

	public void setDutyAtRegMkr(String dutyAtRegMkr) {
		this.dutyAtRegMkr = dutyAtRegMkr;
	}

	public String getRegFeeAtRegMkr() {
		return regFeeAtRegMkr;
	}

	public void setRegFeeAtRegMkr(String regFeeAtRegMkr) {
		this.regFeeAtRegMkr = regFeeAtRegMkr;
	}

	public String getDutyAtRegInit() {
		return dutyAtRegInit;
	}

	public void setDutyAtRegInit(String dutyAtRegInit) {
		this.dutyAtRegInit = dutyAtRegInit;
	}

	public String getRegFeeAtRegInit() {
		return regFeeAtRegInit;
	}

	public void setRegFeeAtRegInit(String regFeeAtRegInit) {
		this.regFeeAtRegInit = regFeeAtRegInit;
	}

	public String getRegCompleteId() {
		return regCompleteId;
	}

	public void setRegCompleteId(String regCompleteId) {
		this.regCompleteId = regCompleteId;
	}

	public String getNetBal() {
		return netBal;
	}

	public void setNetBal(String netBal) {
		this.netBal = netBal;
	}

	public String getTotalBal() {
		return totalBal;
	}

	public void setTotalBal(String totalBal) {
		this.totalBal = totalBal;
	}

	public String getFirstCheck() {
		return firstCheck;
	}

	public void setFirstCheck(String firstCheck) {
		this.firstCheck = firstCheck;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGovtOfficial2() {
		return govtOfficial2;
	}

	public void setGovtOfficial2(String govtOfficial2) {
		this.govtOfficial2 = govtOfficial2;
	}

	public String getGovtLetter() {
		return govtLetter;
	}

	public void setGovtLetter(String govtLetter) {
		this.govtLetter = govtLetter;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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

	public String getComplianceId() {
		return complianceId;
	}

	public void setComplianceId(String complianceId) {
		this.complianceId = complianceId;
	}

	public String getComplianceName() {
		return complianceName;
	}

	public void setComplianceName(String complianceName) {
		this.complianceName = complianceName;
	}

	public String getSelectedCompliance() {
		return selectedCompliance;
	}

	public void setSelectedCompliance(String selectedCompliance) {
		this.selectedCompliance = selectedCompliance;
	}
	
	
	
	public String getCaveatTxnId() {
		return caveatTxnId;
	}

	public void setCaveatTxnId(String caveatTxnId) {
		this.caveatTxnId = caveatTxnId;
	}

	public String getCaveatName() {
		return caveatName;
	}

	public void setCaveatName(String caveatName) {
		this.caveatName = caveatName;
	}
	
	

	public String getCaveatId() {
		return caveatId;
	}

	public void setCaveatId(String caveatId) {
		this.caveatId = caveatId;
	}
	
	

	public String getCheckListTxnId() {
		return checkListTxnId;
	}

	public void setCheckListTxnId(String checkListTxnId) {
		this.checkListTxnId = checkListTxnId;
	}

	public String getDeedOutIndiaFlg() {
		return deedOutIndiaFlg;
	}

	public void setDeedOutIndiaFlg(String deedOutIndiaFlg) {
		this.deedOutIndiaFlg = deedOutIndiaFlg;
	}

	public String getCourtDecFlg() {
		return courtDecFlg;
	}

	public void setCourtDecFlg(String courtDecFlg) {
		this.courtDecFlg = courtDecFlg;
	}

	public String getCourtDecAplFlg() {
		return courtDecAplFlg;
	}

	public void setCourtDecAplFlg(String courtDecAplFlg) {
		this.courtDecAplFlg = courtDecAplFlg;
	}

	public String getDocAftrDeathFlg() {
		return docAftrDeathFlg;
	}

	public void setDocAftrDeathFlg(String docAftrDeathFlg) {
		this.docAftrDeathFlg = docAftrDeathFlg;
	}

	public String getPoaFlg() {
		return poaFlg;
	}

	public void setPoaFlg(String poaFlg) {
		this.poaFlg = poaFlg;
	}

	public String getPoaMpFlg() {
		return poaMpFlg;
	}

	public void setPoaMpFlg(String poaMpFlg) {
		this.poaMpFlg = poaMpFlg;
	}

	public String getDeedOutIndDate() {
		return deedOutIndDate;
	}

	public void setDeedOutIndDate(String deedOutIndDate) {
		this.deedOutIndDate = deedOutIndDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getLastAplDate() {
		return lastAplDate;
	}

	public void setLastAplDate(String lastAplDate) {
		this.lastAplDate = lastAplDate;
	}

	public String getDeathCert() {
		return deathCert;
	}

	public void setDeathCert(String deathCert) {
		this.deathCert = deathCert;
	}

	public String getRelationProof() {
		return relationProof;
	}

	public void setRelationProof(String relationProof) {
		this.relationProof = relationProof;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPoaAuthNo() {
		return poaAuthNo;
	}

	public void setPoaAuthNo(String poaAuthNo) {
		this.poaAuthNo = poaAuthNo;
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
	
	
	

	public String getModify() {
		return modify;
	}

	public void setModify(String modify) {
		this.modify = modify;
	}
	
	

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	////////////////////////////////////////////UPLOAD DOCS//////////////////////////////////////////////
	private FormFile supportingDoc = null;
	private String fwdName;
	ArrayList photoLst = new ArrayList();
	private String supportingDocName;
	private String supportingDocPath;
	private byte[] supportingDocContents;
	private String supportingDocSize;
	private String suppDocUploadCheck;
	private String checkBoxDth;
	private String checkBoxPOA;
	private byte deatCertContents[];
	private byte reltnProofContents[];
	private byte poaUploadContents[];
	private String deathCertSize;
	private String reltnProofSize;
	private String poaUploadSize;
	private String uploadPoaDoc;
	private String uploadPoaDocPath;
	private FormFile poaDocument = null;
	private FormFile deathCertificate = null;
	private FormFile reltnProof = null;
	
	private FormFile paymentReceipt = null;
	private String paymentReceiptName;
	private String paymentReceiptPath;
	private byte[] paymentReceiptContents;
	private String paymentReceiptSize;
	
	private FormFile pnaltyReceipt = null;
	private String pnaltyReceiptName;
	private String pnaltyReceiptPath;
	private byte[] pnaltyReceiptContents;
	private String pnaltyReceiptSize;
	
	private FormFile govtOffLetter = null;
	private FormFile govtOffLetter1[] = new FormFile[100];
	private String govtOffLetterName;
	private String govtOffLetterPath;
	private byte[] govtOffLetterContents;
	private String govtOffLetterSize;
	private String govtOffLttrName[] = new String[100];
	private String govtOffLttrPath[] = new String[100];
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
	private String fnameArb;
	private String mnameArb;
	private String lnameArb;
	private String gendarArb;
	private String ageArb;
	private String fatherNameArb;
	private String motherNameArb;
	private String spouseNameArb;
	private String nationalityArb;
	private String indaddressArb;
	private String indcountryArb;
	private String indstatenameArb;
	private String inddistrictArb;
	private String indphnoArb;
	private String indmobnoArb;
	private String emailIDArb;
	private String indCategoryArb;
	private String indDisabilityArb;
	private String indDisabilityDescArb;
	private String listIDArb;
	private String ldnoArb;
	private String bankName;
	private String branchName;
	private String bankAddress;
	private String bankAuthPer;
	private double bankLoanAmt;
	private double bankSancAmt;
	private String trustName;
	private String trustDate;
	private double rent;
	private double advance;
	private double devlpmtCharge;
	private double otherRecCharge;
	private double premium;
	private double termLease;
	private String withPos;
	private double secLoanAmt;
	private String gendarNameArb;
	private String inddistrictNameArb;
	private String indstatenameNameArb;
	private String indcountryNameArb;
	private String idnoArb;
	private String advancePaymntDate;
	private String possGiven;
	private String possGivenName;
	private String poaWithConsid;
	private double poaPeriod;
	private String indDisabilityNameArb;
	private String hdnIndDisabilityArb;
	private String indCategoryNameArb;
	private String listIDNameArb;
	private int isMultiplePropsFlag=0;
	private int isDutyCalculated=0;
	private String totalBalanceAmount;
	private int paymentCompleteFlag=0;
	private String totalPayableAmount;
	private String totalPaidAmount;
	private String exmpID;
	private String hdnExAmount;
	private String exchangeDeedMrktValue;  
	private int deedTypeFlag=0;
	private int agreeMemoInstFlag;
	private int cancelDeedRadio=0;  
	private String deedtype1;
	private String extraFieldLabel;
	private String marketValueShares;
	private String dutyPaid;
	private String regPaid;
	// added vby vinay
	private String scanPath;
	private String scanName;
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

	private String uploadAction;
    
	public String getUploadAction() {
		return uploadAction;
	}

	public void setUploadAction(String uploadAction) {
		this.uploadAction = uploadAction;
	}

	public FormFile getSupportingDoc() {
		return supportingDoc;
	}

	public void setSupportingDoc(FormFile supportingDoc) {
		this.supportingDoc = supportingDoc;
	}

	public String getFwdName() {
		return fwdName;
	}

	public void setFwdName(String fwdName) {
		this.fwdName = fwdName;
	}

	public ArrayList getPhotoLst() {
		return photoLst;
	}

	public void setPhotoLst(ArrayList photoLst) {
		this.photoLst = photoLst;
	}

	public String getSupportingDocName() {
		return supportingDocName;
	}

	public void setSupportingDocName(String supportingDocName) {
		this.supportingDocName = supportingDocName;
	}


	public String getSupportingDocSize() {
		return supportingDocSize;
	}

	public void setSupportingDocSize(String supportingDocSize) {
		this.supportingDocSize = supportingDocSize;
	}
	
	
	public byte[] getSupportingDocContents() {
		return supportingDocContents;
	}

	public void setSupportingDocContents(byte[] supportingDocContents) {
		this.supportingDocContents = supportingDocContents;
	}

	public String getUplaReltnProof() {
		return uplaReltnProof;
	}

	public void setUplaReltnProof(String uplaReltnProof) {
		this.uplaReltnProof = uplaReltnProof;
	}

	public String getUpldDeathCert() {
		return upldDeathCert;
	}

	public void setUpldDeathCert(String upldDeathCert) {
		this.upldDeathCert = upldDeathCert;
	}
	
	///////////////FLAGS FOR CHECKLIST//////////////////////
	private String execOutIndiaFlag;
	private String courtOrderFlag;
	private String courtAplFlag;
	private String deathFlag;
	private String poaFlag;
	private String poaMpFlag;
	private String poaUploadFilename;
	private String poaId;
	private String holdfileName;
	private String holdfilePath;
	private String isMplr;
	


	public String getIsMplr() {
		return isMplr;
	}

	public void setIsMplr(String isMplr) {
		this.isMplr = isMplr;
	}

	public String getHoldfilePath() {
		return holdfilePath;
	}

	public void setHoldfilePath(String holdfilePath) {
		this.holdfilePath = holdfilePath;
	}

	public String getHoldfileName() {
		return holdfileName;
	}

	public void setHoldfileName(String holdfileName) {
		this.holdfileName = holdfileName;
	}

	public String getExecOutIndiaFlag() {
		return execOutIndiaFlag;
	}

	public void setExecOutIndiaFlag(String execOutIndiaFlag) {
		this.execOutIndiaFlag = execOutIndiaFlag;
	}

	public String getCourtOrderFlag() {
		return courtOrderFlag;
	}

	public void setCourtOrderFlag(String courtOrderFlag) {
		this.courtOrderFlag = courtOrderFlag;
	}

	public String getCourtAplFlag() {
		return courtAplFlag;
	}

	public void setCourtAplFlag(String courtAplFlag) {
		this.courtAplFlag = courtAplFlag;
	}

	public String getDeathFlag() {
		return deathFlag;
	}

	public void setDeathFlag(String deathFlag) {
		this.deathFlag = deathFlag;
	}

	public String getPoaFlag() {
		return poaFlag;
	}

	public void setPoaFlag(String poaFlag) {
		this.poaFlag = poaFlag;
	}

	public String getPoaMpFlag() {
		return poaMpFlag;
	}

	public void setPoaMpFlag(String poaMpFlag) {
		this.poaMpFlag = poaMpFlag;
	}
	
	public String getPoaUploadFilename() {
		return poaUploadFilename;
	}

	public void setPoaUploadFilename(String poaUploadFilename) {
		this.poaUploadFilename = poaUploadFilename;
	}

	public String getPoaId() {
		return poaId;
	}

	public void setPoaId(String poaId) {
		this.poaId = poaId;
	}
	
	/////////////////////WITNESS DETAILS MODIFY////////////////////////////////////
	
	

	private String witFirstName;
	private String witMiddleName;
	private String witLastName;
	private String witGender;
	private String witAge;
	private String witFatherName;
	private String witMotherName;
	private String witSpouseName;
	private String witNationality;
	private String witAddress;
	private String witPhoneNumber;
	private String witLoanInfo;
	private String witPendingTaxDuties;
	private String holdReason;
	private String holdpress="";
	private String unholdRemarks;
	private String isImpound;
	private String isLinking;
	
	private String thumbName;
	private String thumbPath;

	private String photoName;
	
	private String photoPath;
	private String conIdUpload;
	public String getConIdUpload() {
		return conIdUpload;
	}

	public void setConIdUpload(String conIdUpload) {
		this.conIdUpload = conIdUpload;
	}

	private String signaturePath;
	
	public String getThumbName() {
		return thumbName;
	}

	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}

	public String getThumbPath() {
		return thumbPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getSignaturePath() {
		return signaturePath;
	}

	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
	}

	public String getIsLinking() {
		return isLinking;
	}

	public void setIsLinking(String isLinking) {
		this.isLinking = isLinking;
	}

	public String getIsImpound() {
		return isImpound;
	}

	public void setIsImpound(String isImpound) {
		this.isImpound = isImpound;
	}

	public String getUnholdRemarks() {
		return unholdRemarks;
	}

	public void setUnholdRemarks(String unholdRemarks) {
		this.unholdRemarks = unholdRemarks;
	}

	public String getHoldpress() {
		return holdpress;
	}

	public void setHoldpress(String holdpress) {
		this.holdpress = holdpress;
	}

	public String getHoldReason() {
		return holdReason;
	}

	public void setHoldReason(String holdReason) {
		this.holdReason = holdReason;
	}

	public String getWitFirstName() {
		return witFirstName;
	}

	public void setWitFirstName(String witFirstName) {
		this.witFirstName = witFirstName;
	}

	public String getWitMiddleName() {
		return witMiddleName;
	}

	public void setWitMiddleName(String witMiddleName) {
		this.witMiddleName = witMiddleName;
	}

	public String getWitLastName() {
		return witLastName;
	}

	public void setWitLastName(String witLastName) {
		this.witLastName = witLastName;
	}

	public String getWitGender() {
		return witGender;
	}

	public void setWitGender(String witGender) {
		this.witGender = witGender;
	}

	public String getWitAge() {
		return witAge;
	}

	public void setWitAge(String witAge) {
		this.witAge = witAge;
	}

	public String getWitFatherName() {
		return witFatherName;
	}

	public void setWitFatherName(String witFatherName) {
		this.witFatherName = witFatherName;
	}

	public String getWitMotherName() {
		return witMotherName;
	}

	public void setWitMotherName(String witMotherName) {
		this.witMotherName = witMotherName;
	}

	public String getWitSpouseName() {
		return witSpouseName;
	}

	public void setWitSpouseName(String witSpouseName) {
		this.witSpouseName = witSpouseName;
	}

	public String getWitNationality() {
		return witNationality;
	}

	public void setWitNationality(String witNationality) {
		this.witNationality = witNationality;
	}

	public String getWitAddress() {
		return witAddress;
	}

	public void setWitAddress(String witAddress) {
		this.witAddress = witAddress;
	}

	public String getWitPhoneNumber() {
		return witPhoneNumber;
	}

	public void setWitPhoneNumber(String witPhoneNumber) {
		this.witPhoneNumber = witPhoneNumber;
	}

	public String getWitLoanInfo() {
		return witLoanInfo;
	}

	public void setWitLoanInfo(String witLoanInfo) {
		this.witLoanInfo = witLoanInfo;
	}

	public String getWitPendingTaxDuties() {
		return witPendingTaxDuties;
	}

	public void setWitPendingTaxDuties(String witPendingTaxDuties) {
		this.witPendingTaxDuties = witPendingTaxDuties;
	}

	public int getFloorCount() {
		return floorCount;
	}

	public void setFloorCount(int floorCount) {
		this.floorCount = floorCount;
	}

	public String getPropLandmark() {
		return propLandmark;
	}

	public void setPropLandmark(String propLandmark) {
		this.propLandmark = propLandmark;
	}

	public ArrayList getSelectedSubClauseList() {
		return selectedSubClauseList;
	}

	public void setSelectedSubClauseList(ArrayList selectedSubClauseList) {
		this.selectedSubClauseList = selectedSubClauseList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserDOBTrns() {
		return userDOBTrns;
	}

	public void setUserDOBTrns(String userDOBTrns) {
		this.userDOBTrns = userDOBTrns;
	}

	public String getSelectedCategoryName() {
		return selectedCategoryName;
	}

	public void setSelectedCategoryName(String selectedCategoryName) {
		this.selectedCategoryName = selectedCategoryName;
	}

	public String getIndCategoryTrns() {
		return indCategoryTrns;
	}

	public void setIndCategoryTrns(String indCategoryTrns) {
		this.indCategoryTrns = indCategoryTrns;
	}

	public String getIndDisabilityDescTrns() {
		return indDisabilityDescTrns;
	}

	public void setIndDisabilityDescTrns(String indDisabilityDescTrns) {
		this.indDisabilityDescTrns = indDisabilityDescTrns;
	}

	public String getIndMinorityTrns() {
		return indMinorityTrns;
	}

	public void setIndMinorityTrns(String indMinorityTrns) {
		this.indMinorityTrns = indMinorityTrns;
	}

	public String getIndPovertyLineTrns() {
		return indPovertyLineTrns;
	}

	public void setIndPovertyLineTrns(String indPovertyLineTrns) {
		this.indPovertyLineTrns = indPovertyLineTrns;
	}

	public String getSelectedOccupationName() {
		return selectedOccupationName;
	}

	public void setSelectedOccupationName(String selectedOccupationName) {
		this.selectedOccupationName = selectedOccupationName;
	}

	public String getIndScheduleAreaTrns() {
		return indScheduleAreaTrns;
	}

	public void setIndScheduleAreaTrns(String indScheduleAreaTrns) {
		this.indScheduleAreaTrns = indScheduleAreaTrns;
	}

	public String getIndScheduleAreaTrnsDisplay() {
		return indScheduleAreaTrnsDisplay;
	}

	public void setIndScheduleAreaTrnsDisplay(String indScheduleAreaTrnsDisplay) {
		this.indScheduleAreaTrnsDisplay = indScheduleAreaTrnsDisplay;
	}

	public String getPermissionNoTrns() {
		return permissionNoTrns;
	}

	public void setPermissionNoTrns(String permissionNoTrns) {
		this.permissionNoTrns = permissionNoTrns;
	}

	public String getOwnerDOBTrns() {
		return ownerDOBTrns;
	}

	public void setOwnerDOBTrns(String ownerDOBTrns) {
		this.ownerDOBTrns = ownerDOBTrns;
	}

	public String getHdnDeclareShareCheck() {
		return hdnDeclareShareCheck;
	}

	public void setHdnDeclareShareCheck(String hdnDeclareShareCheck) {
		this.hdnDeclareShareCheck = hdnDeclareShareCheck;
	}
	
	/////////////////////////////added by SIMRAN/////////////
	private String stampdutyM;
	private String nagarPalikaDutyM;
	private String panchayatDutyM;
	private String upkarDutyM;
	private String totaldutyM;
	private String registrationFeeM;
	private String marketValueTrnsM;
	private String totalMarketValueM;
	private String stampDutyBalMkr;
	private String regFeeDutyBalMkr;
	private String complaintNo;
	private String complaint;
	private String dutyTobePaid;
	private String regFeeTobePaid;
	private Double regFeePaid;
	private Double stampDutyPaid;

	public Double getStampDutyPaid() {
		return stampDutyPaid;
	}

	public void setStampDutyPaid(Double stampDutyPaid) {
		this.stampDutyPaid = stampDutyPaid;
	}

	public String getDutyTobePaid() {
		return dutyTobePaid;
	}

	public void setDutyTobePaid(String dutyTobePaid) {
		this.dutyTobePaid = dutyTobePaid;
	}

	public String getRegFeeTobePaid() {
		return regFeeTobePaid;
	}

	public void setRegFeeTobePaid(String regFeeTobePaid) {
		this.regFeeTobePaid = regFeeTobePaid;
	}

	public String getStampdutyM() {
		return stampdutyM;
	}

	public void setStampdutyM(String stampdutyM) {
		this.stampdutyM = stampdutyM;
	}

	public String getNagarPalikaDutyM() {
		return nagarPalikaDutyM;
	}

	public void setNagarPalikaDutyM(String nagarPalikaDutyM) {
		this.nagarPalikaDutyM = nagarPalikaDutyM;
	}

	public String getPanchayatDutyM() {
		return panchayatDutyM;
	}

	public void setPanchayatDutyM(String panchayatDutyM) {
		this.panchayatDutyM = panchayatDutyM;
	}

	public String getUpkarDutyM() {
		return upkarDutyM;
	}

	public void setUpkarDutyM(String upkarDutyM) {
		this.upkarDutyM = upkarDutyM;
	}

	public String getTotaldutyM() {
		return totaldutyM;
	}

	public void setTotaldutyM(String totaldutyM) {
		this.totaldutyM = totaldutyM;
	}

	public String getRegistrationFeeM() {
		return registrationFeeM;
	}

	public void setRegistrationFeeM(String registrationFeeM) {
		this.registrationFeeM = registrationFeeM;
	}

	public String getMarketValueTrnsM() {
		return marketValueTrnsM;
	}

	public void setMarketValueTrnsM(String marketValueTrnsM) {
		this.marketValueTrnsM = marketValueTrnsM;
	}

	public String getTotalMarketValueM() {
		return totalMarketValueM;
	}

	public void setTotalMarketValueM(String totalMarketValueM) {
		this.totalMarketValueM = totalMarketValueM;
	}

	public String getStampDutyBalMkr() {
		return stampDutyBalMkr;
	}

	public void setStampDutyBalMkr(String stampDutyBalMkr) {
		this.stampDutyBalMkr = stampDutyBalMkr;
	}

	public String getRegFeeDutyBalMkr() {
		return regFeeDutyBalMkr;
	}

	public void setRegFeeDutyBalMkr(String regFeeDutyBalMkr) {
		this.regFeeDutyBalMkr = regFeeDutyBalMkr;
	}
	
	private String transactionId;
	private String stayOrdrNum;
	private String stayOrdrDet;
	private String caveatDet;
	private String caveatStatus;
	private String stayOrdrStrtDate;
	private String stayOrdrUptoDate;
	private String propTxnLock;
	ArrayList propIDLocked = new ArrayList();
	private String regNumLocked;
	private String partyFirstName;
	private String addDeathCert;
	private String addPOA;
	private String check;
	private String purposeOfLocking;
	private String poaRegId;

//Start :===added for bank caveat Details
	
	
	 private String cvtBnkTxnId;
	 private String ottId;
	 private String loanAcntNum;
	 private String bnkName;
	 private String regId;
	 private String pmtTxnId;
	 private String caseId;
	 private String caseName;
		
	//end :===added for bank caveat Details

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

	public ArrayList getPropIDLocked() {
		return propIDLocked;
	}

	public void setPropIDLocked(ArrayList propIDLocked) {
		this.propIDLocked = propIDLocked;
	}

	public String getRegNumLocked() {
		return regNumLocked;
	}

	public void setRegNumLocked(String regNumLocked) {
		this.regNumLocked = regNumLocked;
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

	public String getFilePathPOA() {
		return filePathPOA;
	}

	public void setFilePathPOA(String filePathPOA) {
		this.filePathPOA = filePathPOA;
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

	public String getHoldPageId() {
		return holdPageId;
	}

	public void setHoldPageId(String holdPageId) {
		this.holdPageId = holdPageId;
	}

	public String getSuppDocUploadCheck() {
		return suppDocUploadCheck;
	}

	public void setSuppDocUploadCheck(String suppDocUploadCheck) {
		this.suppDocUploadCheck = suppDocUploadCheck;
	}

	public String getSupportingDocPath() {
		return supportingDocPath;
	}

	public void setSupportingDocPath(String supportingDocPath) {
		this.supportingDocPath = supportingDocPath;
	}

	public String getCheckBoxDth() {
		return checkBoxDth;
	}

	public void setCheckBoxDth(String checkBoxDth) {
		this.checkBoxDth = checkBoxDth;
	}

	public String getCheckBoxPOA() {
		return checkBoxPOA;
	}

	public void setCheckBoxPOA(String checkBoxPOA) {
		this.checkBoxPOA = checkBoxPOA;
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

	public byte[] getPoaUploadContents() {
		return poaUploadContents;
	}

	public void setPoaUploadContents(byte[] poaUploadContents) {
		this.poaUploadContents = poaUploadContents;
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

	public String getPartyFirstName() {
		return partyFirstName;
	}

	public void setPartyFirstName(String partyFirstName) {
		this.partyFirstName = partyFirstName;
	}

	public FormFile getPoaDocument() {
		return poaDocument;
	}

	public void setPoaDocument(FormFile poaDocument) {
		this.poaDocument = poaDocument;
	}

	public String getAddDeathCert() {
		return addDeathCert;
	}

	public void setAddDeathCert(String addDeathCert) {
		this.addDeathCert = addDeathCert;
	}

	public String getAddPOA() {
		return addPOA;
	}

	public void setAddPOA(String addPOA) {
		this.addPOA = addPOA;
	}

	public FormFile getDeathCertificate() {
		return deathCertificate;
	}

	public void setDeathCertificate(FormFile deathCertificate) {
		this.deathCertificate = deathCertificate;
	}

	public FormFile getReltnProof() {
		return reltnProof;
	}

	public void setReltnProof(FormFile reltnProof) {
		this.reltnProof = reltnProof;
	}

	public String getPoaMpFlg1() {
		return poaMpFlg1;
	}

	public void setPoaMpFlg1(String poaMpFlg1) {
		this.poaMpFlg1 = poaMpFlg1;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getComplaintNo() {
		return complaintNo;
	}

	public void setComplaintNo(String complaintNo) {
		this.complaintNo = complaintNo;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	public FormFile getPaymentReceipt() {
		return paymentReceipt;
	}

	public void setPaymentReceipt(FormFile paymentReceipt) {
		this.paymentReceipt = paymentReceipt;
	}

	public String getPaymentReceiptName() {
		return paymentReceiptName;
	}

	public void setPaymentReceiptName(String paymentReceiptName) {
		this.paymentReceiptName = paymentReceiptName;
	}

	public String getPaymentReceiptPath() {
		return paymentReceiptPath;
	}

	public void setPaymentReceiptPath(String paymentReceiptPath) {
		this.paymentReceiptPath = paymentReceiptPath;
	}

	public byte[] getPaymentReceiptContents() {
		return paymentReceiptContents;
	}

	public void setPaymentReceiptContents(byte[] paymentReceiptContents) {
		this.paymentReceiptContents = paymentReceiptContents;
	}

	public String getPaymentReceiptSize() {
		return paymentReceiptSize;
	}

	public void setPaymentReceiptSize(String paymentReceiptSize) {
		this.paymentReceiptSize = paymentReceiptSize;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSourceMod() {
		return sourceMod;
	}

	public void setSourceMod(String sourceMod) {
		this.sourceMod = sourceMod;
	}

	public String getCompChcek() {
		return compChcek;
	}

	public void setCompChcek(String compChcek) {
		this.compChcek = compChcek;
	}

	public String getPurposeOfLocking() {
		return purposeOfLocking;
	}

	public void setPurposeOfLocking(String purposeOfLocking) {
		this.purposeOfLocking = purposeOfLocking;
	}

	public String getPoaRegId() {
		return poaRegId;
	}

	public void setPoaRegId(String poaRegId) {
		this.poaRegId = poaRegId;
	}

	public String getPmtTxnId() {
		return pmtTxnId;
	}

	public void setPmtTxnId(String pmtTxnId) {
		this.pmtTxnId = pmtTxnId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getNetPaidAmt() {
		return netPaidAmt;
	}

	public void setNetPaidAmt(String netPaidAmt) {
		this.netPaidAmt = netPaidAmt;
	}

	public String getNetBalAmt() {
		return netBalAmt;
	}

	public void setNetBalAmt(String netBalAmt) {
		this.netBalAmt = netBalAmt;
	}

	//Property image taken from three different angles
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

	public FormFile getPnaltyReceipt() {
		return pnaltyReceipt;
	}

	public void setPnaltyReceipt(FormFile pnaltyReceipt) {
		this.pnaltyReceipt = pnaltyReceipt;
	}

	public String getPnaltyReceiptName() {
		return pnaltyReceiptName;
	}

	public void setPnaltyReceiptName(String pnaltyReceiptName) {
		this.pnaltyReceiptName = pnaltyReceiptName;
	}

	public String getPnaltyReceiptPath() {
		return pnaltyReceiptPath;
	}

	public void setPnaltyReceiptPath(String pnaltyReceiptPath) {
		this.pnaltyReceiptPath = pnaltyReceiptPath;
	}

	public byte[] getPnaltyReceiptContents() {
		return pnaltyReceiptContents;
	}

	public void setPnaltyReceiptContents(byte[] pnaltyReceiptContents) {
		this.pnaltyReceiptContents = pnaltyReceiptContents;
	}

	public String getPnaltyReceiptSize() {
		return pnaltyReceiptSize;
	}

	public void setPnaltyReceiptSize(String pnaltyReceiptSize) {
		this.pnaltyReceiptSize = pnaltyReceiptSize;
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public FormFile getGovtOffLetter() {
		return govtOffLetter;
	}

	public void setGovtOffLetter(FormFile govtOffLetter) {
		this.govtOffLetter = govtOffLetter;
	}

	public String getGovtOffLetterName() {
		return govtOffLetterName;
	}

	public void setGovtOffLetterName(String govtOffLetterName) {
		this.govtOffLetterName = govtOffLetterName;
	}

	public String getGovtOffLetterPath() {
		return govtOffLetterPath;
	}

	public void setGovtOffLetterPath(String govtOffLetterPath) {
		this.govtOffLetterPath = govtOffLetterPath;
	}

	public byte[] getGovtOffLetterContents() {
		return govtOffLetterContents;
	}

	public void setGovtOffLetterContents(byte[] govtOffLetterContents) {
		this.govtOffLetterContents = govtOffLetterContents;
	}

	public String getGovtOffLetterSize() {
		return govtOffLetterSize;
	}

	public void setGovtOffLetterSize(String govtOffLetterSize) {
		this.govtOffLetterSize = govtOffLetterSize;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public FormFile[] getGovtOffLetter1() {
		return govtOffLetter1;
	}

	public void setGovtOffLetter1(FormFile[] govtOffLetter1) {
		this.govtOffLetter1 = govtOffLetter1;
	}

	public void setGovtOffLetter1(int index, FormFile file )
	{
		govtOffLetter1[index] = file;
	}
	public  FormFile getGovtOffLetter1(int index)
	{
		return govtOffLetter1[index];
	}
	
	private String indx;



	public String getIndx() {
		return indx;
	}

	public void setIndx(String indx) {
		this.indx = indx;
	}

	public String[] getGovtOffLttrName() {
		return govtOffLttrName;
	}

	public void setGovtOffLttrName(String[] govtOffLttrName) {
		this.govtOffLttrName = govtOffLttrName;
	}
	
	public String getGovtOffLttrName(int index) {
		return govtOffLttrName[index];
	}

	public void setGovtOffLttrName(int index,String govtOffLttrName) {
		this.govtOffLttrName[index] = govtOffLttrName;
	}
	
	public String[] getGovtOffLttrPath() {
		return govtOffLttrPath;
	}

	public void setGovtOffLttrPath(String[] govtOffLttrPath) {
		this.govtOffLttrPath = govtOffLttrPath;
	}
	
	public String getGovtOffLttrPath(int index) {
		return govtOffLttrPath[index];
	}

	public void setGovtOffLttrPath(int index,String govtOffLttrPath) {
		this.govtOffLttrPath[index] = govtOffLttrPath;
	}

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

	public String getPropId() {
		return propId;
	}

	public void setPropId(String propId) {
		this.propId = propId;
	}

	public String getEstampTxnId() {
		return estampTxnId;
	}

	public void setEstampTxnId(String estampTxnId) {
		this.estampTxnId = estampTxnId;
	}

	public String getEstampStatus() {
		return estampStatus;
	}

	public void setEstampStatus(String estampStatus) {
		this.estampStatus = estampStatus;
	}
	
	
	/////*****************************Partial Save*****************************************/////
	
	private String cancelledPage;
	private String elvnMonthchk;
	private String titleCertCheck;
	private String convynceDeedCheck;
	private String rejectionId;
	private String rejectionDesc;
	private String sealId;
	private String seal;
	private String sealSubTypeId;
	private String sealSubTypeName;
	private String regSealId;
	private String regSealSubTypeId;
	private String regSeal;
	private String stampSealSubTypeId;
	private String stampSeal;
	private String stampSealId;
	private String sealCheck;
	private String ParticularTxnId;
	private String ParticularName;
	private String ParticularDesc;
	private String regFeeRadio;
	private double paidLoanAmt;
	private String contriProp;
	private String dissolutionDate;
	private String retirmentDate;
	

	public String getCancelledPage() {
		return cancelledPage;
	}

	public void setCancelledPage(String cancelledPage) {
		this.cancelledPage = cancelledPage;
	}

	public String getIsEstamp() {
		return isEstamp;
	}

	public void setIsEstamp(String isEstamp) {
		this.isEstamp = isEstamp;
	}

	public String getLastDueDate() {
		return lastDueDate;
	}

	public void setLastDueDate(String lastDueDate) {
		this.lastDueDate = lastDueDate;
	}

	public String getElvnMonthchk() {
		return elvnMonthchk;
	}

	public void setElvnMonthchk(String elvnMonthchk) {
		this.elvnMonthchk = elvnMonthchk;
	}

	public String getTitleCertCheck() {
		return titleCertCheck;
	}

	public void setTitleCertCheck(String titleCertCheck) {
		this.titleCertCheck = titleCertCheck;
	}

	public String getConvynceDeedCheck() {
		return convynceDeedCheck;
	}

	public void setConvynceDeedCheck(String convynceDeedCheck) {
		this.convynceDeedCheck = convynceDeedCheck;
	}

	public String getRejectionId() {
		return rejectionId;
	}

	public void setRejectionId(String rejectionId) {
		this.rejectionId = rejectionId;
	}

	public String getRejectionDesc() {
		return rejectionDesc;
	}

	public void setRejectionDesc(String rejectionDesc) {
		this.rejectionDesc = rejectionDesc;
	}

	public String getSealId() {
		return sealId;
	}

	public void setSealId(String sealId) {
		this.sealId = sealId;
	}

	public String getSealSubTypeId() {
		return sealSubTypeId;
	}

	public void setSealSubTypeId(String sealSubTypeId) {
		this.sealSubTypeId = sealSubTypeId;
	}

	public String getSealSubTypeName() {
		return sealSubTypeName;
	}

	public void setSealSubTypeName(String sealSubTypeName) {
		this.sealSubTypeName = sealSubTypeName;
	}

	public String getSeal() {
		return seal;
	}

	public void setSeal(String seal) {
		this.seal = seal;
	}

	public String getRegSealId() {
		return regSealId;
	}

	public void setRegSealId(String regSealId) {
		this.regSealId = regSealId;
	}

	public String getRegSealSubTypeId() {
		return regSealSubTypeId;
	}

	public void setRegSealSubTypeId(String regSealSubTypeId) {
		this.regSealSubTypeId = regSealSubTypeId;
	}

	public String getRegSeal() {
		return regSeal;
	}

	public void setRegSeal(String regSeal) {
		this.regSeal = regSeal;
	}

	public String getStampSealSubTypeId() {
		return stampSealSubTypeId;
	}

	public void setStampSealSubTypeId(String stampSealSubTypeId) {
		this.stampSealSubTypeId = stampSealSubTypeId;
	}

	public String getStampSeal() {
		return stampSeal;
	}

	public void setStampSeal(String stampSeal) {
		this.stampSeal = stampSeal;
	}

	public String getStampSealId() {
		return stampSealId;
	}

	public void setStampSealId(String stampSealId) {
		this.stampSealId = stampSealId;
	}

	public String getSealCheck() {
		return sealCheck;
	}

	public void setSealCheck(String sealCheck) {
		this.sealCheck = sealCheck;
	}

	public String getFnameArb() {
		return fnameArb;
	}

	public void setFnameArb(String fnameArb) {
		this.fnameArb = fnameArb;
	}

	public String getMnameArb() {
		return mnameArb;
	}

	public void setMnameArb(String mnameArb) {
		this.mnameArb = mnameArb;
	}

	public String getLnameArb() {
		return lnameArb;
	}

	public void setLnameArb(String lnameArb) {
		this.lnameArb = lnameArb;
	}

	public String getGendarArb() {
		return gendarArb;
	}

	public void setGendarArb(String gendarArb) {
		this.gendarArb = gendarArb;
	}

	public String getAgeArb() {
		return ageArb;
	}

	public void setAgeArb(String ageArb) {
		this.ageArb = ageArb;
	}

	public String getFatherNameArb() {
		return fatherNameArb;
	}

	public void setFatherNameArb(String fatherNameArb) {
		this.fatherNameArb = fatherNameArb;
	}

	public String getMotherNameArb() {
		return motherNameArb;
	}

	public void setMotherNameArb(String motherNameArb) {
		this.motherNameArb = motherNameArb;
	}

	public String getSpouseNameArb() {
		return spouseNameArb;
	}

	public void setSpouseNameArb(String spouseNameArb) {
		this.spouseNameArb = spouseNameArb;
	}

	public String getNationalityArb() {
		return nationalityArb;
	}

	public void setNationalityArb(String nationalityArb) {
		this.nationalityArb = nationalityArb;
	}

	public String getIndaddressArb() {
		return indaddressArb;
	}

	public void setIndaddressArb(String indaddressArb) {
		this.indaddressArb = indaddressArb;
	}

	public String getIndcountryArb() {
		return indcountryArb;
	}

	public void setIndcountryArb(String indcountryArb) {
		this.indcountryArb = indcountryArb;
	}

	public String getIndstatenameArb() {
		return indstatenameArb;
	}

	public void setIndstatenameArb(String indstatenameArb) {
		this.indstatenameArb = indstatenameArb;
	}

	public String getInddistrictArb() {
		return inddistrictArb;
	}

	public void setInddistrictArb(String inddistrictArb) {
		this.inddistrictArb = inddistrictArb;
	}

	public String getIndphnoArb() {
		return indphnoArb;
	}

	public void setIndphnoArb(String indphnoArb) {
		this.indphnoArb = indphnoArb;
	}

	public String getIndmobnoArb() {
		return indmobnoArb;
	}

	public void setIndmobnoArb(String indmobnoArb) {
		this.indmobnoArb = indmobnoArb;
	}

	public String getEmailIDArb() {
		return emailIDArb;
	}

	public void setEmailIDArb(String emailIDArb) {
		this.emailIDArb = emailIDArb;
	}

	public String getIndCategoryArb() {
		return indCategoryArb;
	}

	public void setIndCategoryArb(String indCategoryArb) {
		this.indCategoryArb = indCategoryArb;
	}

	public String getIndDisabilityArb() {
		return indDisabilityArb;
	}

	public void setIndDisabilityArb(String indDisabilityArb) {
		this.indDisabilityArb = indDisabilityArb;
	}

	public String getIndDisabilityDescArb() {
		return indDisabilityDescArb;
	}

	public void setIndDisabilityDescArb(String indDisabilityDescArb) {
		this.indDisabilityDescArb = indDisabilityDescArb;
	}

	public String getListIDArb() {
		return listIDArb;
	}

	public void setListIDArb(String listIDArb) {
		this.listIDArb = listIDArb;
	}

	public String getLdnoArb() {
		return ldnoArb;
	}

	public void setLdnoArb(String ldnoArb) {
		this.ldnoArb = ldnoArb;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankAuthPer() {
		return bankAuthPer;
	}

	public void setBankAuthPer(String bankAuthPer) {
		this.bankAuthPer = bankAuthPer;
	}

	public double getBankLoanAmt() {
		return bankLoanAmt;
	}

	public void setBankLoanAmt(double bankLoanAmt) {
		this.bankLoanAmt = bankLoanAmt;
	}

	public double getBankSancAmt() {
		return bankSancAmt;
	}

	public void setBankSancAmt(double bankSancAmt) {
		this.bankSancAmt = bankSancAmt;
	}

	public String getTrustName() {
		return trustName;
	}

	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}

	public String getTrustDate() {
		return trustDate;
	}

	public void setTrustDate(String trustDate) {
		this.trustDate = trustDate;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public double getAdvance() {
		return advance;
	}

	public void setAdvance(double advance) {
		this.advance = advance;
	}

	public double getDevlpmtCharge() {
		return devlpmtCharge;
	}

	public void setDevlpmtCharge(double devlpmtCharge) {
		this.devlpmtCharge = devlpmtCharge;
	}

	public double getOtherRecCharge() {
		return otherRecCharge;
	}

	public void setOtherRecCharge(double otherRecCharge) {
		this.otherRecCharge = otherRecCharge;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public double getTermLease() {
		return termLease;
	}

	public void setTermLease(double termLease) {
		this.termLease = termLease;
	}

	public String getWithPos() {
		return withPos;
	}

	public void setWithPos(String withPos) {
		this.withPos = withPos;
	}

	public double getSecLoanAmt() {
		return secLoanAmt;
	}

	public void setSecLoanAmt(double secLoanAmt) {
		this.secLoanAmt = secLoanAmt;
	}

	public String getGendarNameArb() {
		return gendarNameArb;
	}

	public void setGendarNameArb(String gendarNameArb) {
		this.gendarNameArb = gendarNameArb;
	}

	public String getInddistrictNameArb() {
		return inddistrictNameArb;
	}

	public void setInddistrictNameArb(String inddistrictNameArb) {
		this.inddistrictNameArb = inddistrictNameArb;
	}

	public String getIndstatenameNameArb() {
		return indstatenameNameArb;
	}

	public void setIndstatenameNameArb(String indstatenameNameArb) {
		this.indstatenameNameArb = indstatenameNameArb;
	}

	public String getIndcountryNameArb() {
		return indcountryNameArb;
	}

	public void setIndcountryNameArb(String indcountryNameArb) {
		this.indcountryNameArb = indcountryNameArb;
	}

	public String getIdnoArb() {
		return idnoArb;
	}

	public void setIdnoArb(String idnoArb) {
		this.idnoArb = idnoArb;
	}

	public String getAdvancePaymntDate() {
		return advancePaymntDate;
	}

	public void setAdvancePaymntDate(String advancePaymntDate) {
		this.advancePaymntDate = advancePaymntDate;
	}

	public String getPossGiven() {
		return possGiven;
	}

	public void setPossGiven(String possGiven) {
		this.possGiven = possGiven;
	}

	public String getPossGivenName() {
		return possGivenName;
	}

	public void setPossGivenName(String possGivenName) {
		this.possGivenName = possGivenName;
	}

	public String getPoaWithConsid() {
		return poaWithConsid;
	}

	public void setPoaWithConsid(String poaWithConsid) {
		this.poaWithConsid = poaWithConsid;
	}

	public double getPoaPeriod() {
		return poaPeriod;
	}

	public void setPoaPeriod(double poaPeriod) {
		this.poaPeriod = poaPeriod;
	}

	public String getIndDisabilityNameArb() {
		return indDisabilityNameArb;
	}

	public void setIndDisabilityNameArb(String indDisabilityNameArb) {
		this.indDisabilityNameArb = indDisabilityNameArb;
	}

	public String getHdnIndDisabilityArb() {
		return hdnIndDisabilityArb;
	}

	public void setHdnIndDisabilityArb(String hdnIndDisabilityArb) {
		this.hdnIndDisabilityArb = hdnIndDisabilityArb;
	}

	public String getIndCategoryNameArb() {
		return indCategoryNameArb;
	}

	public void setIndCategoryNameArb(String indCategoryNameArb) {
		this.indCategoryNameArb = indCategoryNameArb;
	}

	public String getListIDNameArb() {
		return listIDNameArb;
	}

	public void setListIDNameArb(String listIDNameArb) {
		this.listIDNameArb = listIDNameArb;
	}

	public int getIsMultiplePropsFlag() {
		return isMultiplePropsFlag;
	}

	public void setIsMultiplePropsFlag(int isMultiplePropsFlag) {
		this.isMultiplePropsFlag = isMultiplePropsFlag;
	}

	public int getIsDutyCalculated() {
		return isDutyCalculated;
	}

	public void setIsDutyCalculated(int isDutyCalculated) {
		this.isDutyCalculated = isDutyCalculated;
	}

	public String getTotalBalanceAmount() {
		return totalBalanceAmount;
	}

	public void setTotalBalanceAmount(String totalBalanceAmount) {
		this.totalBalanceAmount = totalBalanceAmount;
	}

	public int getPaymentCompleteFlag() {
		return paymentCompleteFlag;
	}

	public void setPaymentCompleteFlag(int paymentCompleteFlag) {
		this.paymentCompleteFlag = paymentCompleteFlag;
	}

	public String getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(String totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	public String getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(String totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public String getExmpID() {
		return exmpID;
	}

	public void setExmpID(String exmpID) {
		this.exmpID = exmpID;
	}

	public String getHdnExAmount() {
		return hdnExAmount;
	}

	public void setHdnExAmount(String hdnExAmount) {
		this.hdnExAmount = hdnExAmount;
	}

	public String getExchangeDeedMrktValue() {
		return exchangeDeedMrktValue;
	}

	public void setExchangeDeedMrktValue(String exchangeDeedMrktValue) {
		this.exchangeDeedMrktValue = exchangeDeedMrktValue;
	}

	public int getDeedTypeFlag() {
		return deedTypeFlag;
	}

	public void setDeedTypeFlag(int deedTypeFlag) {
		this.deedTypeFlag = deedTypeFlag;
	}

	public int getAgreeMemoInstFlag() {
		return agreeMemoInstFlag;
	}

	public void setAgreeMemoInstFlag(int agreeMemoInstFlag) {
		this.agreeMemoInstFlag = agreeMemoInstFlag;
	}

	public int getCancelDeedRadio() {
		return cancelDeedRadio;
	}

	public void setCancelDeedRadio(int cancelDeedRadio) {
		this.cancelDeedRadio = cancelDeedRadio;
	}

	public String getDeedtype1() {
		return deedtype1;
	}

	public void setDeedtype1(String deedtype1) {
		this.deedtype1 = deedtype1;
	}

	public String getExtraFieldLabel() {
		return extraFieldLabel;
	}

	public void setExtraFieldLabel(String extraFieldLabel) {
		this.extraFieldLabel = extraFieldLabel;
	}

	public String getMarketValueShares() {
		return marketValueShares;
	}

	public void setMarketValueShares(String marketValueShares) {
		this.marketValueShares = marketValueShares;
	}

	public String getDutyPaid() {
		return dutyPaid;
	}

	public void setDutyPaid(String dutyPaid) {
		this.dutyPaid = dutyPaid;
	}

	public String getRegPaid() {
		return regPaid;
	}

	public void setRegPaid(String regPaid) {
		this.regPaid = regPaid;
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

	public int getCommonDeedFlag() {
		return commonDeedFlag;
	}

	public void setCommonDeedFlag(int commonDeedFlag) {
		this.commonDeedFlag = commonDeedFlag;
	}

	public String getParticularTxnId() {
		return ParticularTxnId;
	}

	public void setParticularTxnId(String particularTxnId) {
		ParticularTxnId = particularTxnId;
	}

	public String getParticularName() {
		return ParticularName;
	}

	public void setParticularName(String particularName) {
		ParticularName = particularName;
	}

	public String getParticularDesc() {
		return ParticularDesc;
	}

	public void setParticularDesc(String particularDesc) {
		ParticularDesc = particularDesc;
	}

	public String getRegFeeRadio() {
		return regFeeRadio;
	}

	public void setRegFeeRadio(String regFeeRadio) {
		this.regFeeRadio = regFeeRadio;
	}

	public double getPaidLoanAmt() {
		return paidLoanAmt;
	}

	public void setPaidLoanAmt(double paidLoanAmt) {
		this.paidLoanAmt = paidLoanAmt;
	}

	public String getContriProp() {
		return contriProp;
	}

	public void setContriProp(String contriProp) {
		this.contriProp = contriProp;
	}

	public String getDissolutionDate() {
		return dissolutionDate;
	}

	public void setDissolutionDate(String dissolutionDate) {
		this.dissolutionDate = dissolutionDate;
	}

	public String getRetirmentDate() {
		return retirmentDate;
	}

	public void setRetirmentDate(String retirmentDate) {
		this.retirmentDate = retirmentDate;
	}

	//*****************device integration**************//
	
	private String guidUpload;
	private String parentPathFP;
	private String fileNameFP;
	
	private String parentPathScan;
	private String fileNameScan;
	
	private String parentPathSign;
	private String fileNameSign;
	private String forwardName;
	private String forwardPath;
	private String partyIdUpload;
	private String thumbRemarkIndex;
	private String witIdUpload;
	private String fingerPrintName;
	private String fingerPrintPath;
	private String signatureName;
	private String parentPathSrSign;
	private String fileNameSrSign;
	private String forwardPathSr;
	private String srSign;
	private String relationshipWit;
	private String uploadDoc;
	private String parentPathGovtLttr;
	private String fileNameGovtLttr;
	private String uploadIndex;

	public String getGuidUpload() {
		return guidUpload;
	}

	public void setGuidUpload(String guidUpload) {
		this.guidUpload = guidUpload;
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

	public String getSignatureName() {
		return signatureName;
	}

	public void setSignatureName(String signatureName) {
		this.signatureName = signatureName;
	}
	
	//****consenter Details***//
	private String consenterCheck;
	private String consenterVal;

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

	
	private String propPage;
	private String propPageOld;
	private String pinGenerationCheck;
	private String deedId;
	private String instId;
	private String pinPrintChk;

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

	public String getRelationshipWit() {
		return relationshipWit;
	}

	public void setRelationshipWit(String relationshipWit) {
		this.relationshipWit = relationshipWit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUploadDoc() {
		return uploadDoc;
	}

	public void setUploadDoc(String uploadDoc) {
		this.uploadDoc = uploadDoc;
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

	public String getUploadIndex() {
		return uploadIndex;
	}

	public void setUploadIndex(String uploadIndex) {
		this.uploadIndex = uploadIndex;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getPinGenerationCheck() {
		return pinGenerationCheck;
	}

	public void setPinGenerationCheck(String pinGenerationCheck) {
		this.pinGenerationCheck = pinGenerationCheck;
	}

	public String getDeedId() {
		return deedId;
	}

	public void setDeedId(String deedId) {
		this.deedId = deedId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	public String getPinPrintChk() {
		return pinPrintChk;
	}

	public void setPinPrintChk(String pinPrintChk) {
		this.pinPrintChk = pinPrintChk;
	}

	public String getTotalSqMeterDisplay() {
		return totalSqMeterDisplay;
	}

	public void setTotalSqMeterDisplay(String totalSqMeterDisplay) {
		this.totalSqMeterDisplay = totalSqMeterDisplay;
	}

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
	
	private int relationshipTrns;
	private String relationshipNameTrns;
	private String indDisabilityIdTrns;
	private String occupationIdTrns;
	private String documentNameTrns;
	private String documentSizeTrns;
	private byte[] docContentsTrns;
	private String filePathTrns;
	private String partyOrPropTrns;
	private String uploadTypeTrns;
	
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
	
	private int claimantFlag;
	private int poaHolderFlag;
	 private String ownerIdTrns;
	
	
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

	public int getRelationshipTrns() {
		return relationshipTrns;
	}

	public void setRelationshipTrns(int relationshipTrns) {
		this.relationshipTrns = relationshipTrns;
	}

	public String getRelationshipNameTrns() {
		return relationshipNameTrns;
	}

	public void setRelationshipNameTrns(String relationshipNameTrns) {
		this.relationshipNameTrns = relationshipNameTrns;
	}

	public String getIndDisabilityIdTrns() {
		return indDisabilityIdTrns;
	}

	public void setIndDisabilityIdTrns(String indDisabilityIdTrns) {
		this.indDisabilityIdTrns = indDisabilityIdTrns;
	}

	public String getOccupationIdTrns() {
		return occupationIdTrns;
	}

	public void setOccupationIdTrns(String occupationIdTrns) {
		this.occupationIdTrns = occupationIdTrns;
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

	public String getOwnerIdTrns() {
		return ownerIdTrns;
	}

	public void setOwnerIdTrns(String ownerIdTrns) {
		this.ownerIdTrns = ownerIdTrns;
	}

	public String getFingerPrintPath() {
		return fingerPrintPath;
	}

	public void setFingerPrintPath(String fingerPrintPath) {
		this.fingerPrintPath = fingerPrintPath;
	}

	public String getSrSign() {
		return srSign;
	}

	public void setSrSign(String srSign) {
		this.srSign = srSign;
	}

	public String getUplaReltnProofPath() {
		return uplaReltnProofPath;
	}

	public void setUplaReltnProofPath(String uplaReltnProofPath) {
		this.uplaReltnProofPath = uplaReltnProofPath;
	}

	public String getUpldDeathCertPath() {
		return upldDeathCertPath;
	}

	public void setUpldDeathCertPath(String upldDeathCertPath) {
		this.upldDeathCertPath = upldDeathCertPath;
	}

	public String getUploadPoaDocPath() {
		return uploadPoaDocPath;
	}

	public void setUploadPoaDocPath(String uploadPoaDocPath) {
		this.uploadPoaDocPath = uploadPoaDocPath;
	}

	public String getConsentorTxnNumber() {
		return consentorTxnNumber;
	}

	public void setConsentorTxnNumber(String consentorTxnNumber) {
		this.consentorTxnNumber = consentorTxnNumber;
	}
	
	private String instituteName;
	private String repName;
	private String caveatLoggedDate;
	private String propIdInit;
	private String partyPicChk;
	private String partyThumbChk;
	private String partySignChk;
	private String wittPicChk;
	private String wittThumbChk;
	private String wittSignChk;
	private String cnsntrPicChk;
	private String cnsntrThumbChk;
	private String cnsntrSignChk;
	private String cnsntrChk;
	
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

	public String getCaveatLoggedDate() {
		return caveatLoggedDate;
	}

	public void setCaveatLoggedDate(String caveatLoggedDate) {
		this.caveatLoggedDate = caveatLoggedDate;
	}

	public String getPropIdInit() {
		return propIdInit;
	}

	public void setPropIdInit(String propIdInit) {
		this.propIdInit = propIdInit;
	}

	public String getPartyPicChk() {
		return partyPicChk;
	}

	public void setPartyPicChk(String partyPicChk) {
		this.partyPicChk = partyPicChk;
	}

	public String getPartyThumbChk() {
		return partyThumbChk;
	}

	public void setPartyThumbChk(String partyThumbChk) {
		this.partyThumbChk = partyThumbChk;
	}

	public String getPartySignChk() {
		return partySignChk;
	}

	public void setPartySignChk(String partySignChk) {
		this.partySignChk = partySignChk;
	}

	public String getWittPicChk() {
		return wittPicChk;
	}

	public void setWittPicChk(String wittPicChk) {
		this.wittPicChk = wittPicChk;
	}

	public String getWittThumbChk() {
		return wittThumbChk;
	}

	public void setWittThumbChk(String wittThumbChk) {
		this.wittThumbChk = wittThumbChk;
	}

	public String getWittSignChk() {
		return wittSignChk;
	}

	public void setWittSignChk(String wittSignChk) {
		this.wittSignChk = wittSignChk;
	}

	public String getCnsntrPicChk() {
		return cnsntrPicChk;
	}

	public void setCnsntrPicChk(String cnsntrPicChk) {
		this.cnsntrPicChk = cnsntrPicChk;
	}

	public String getCnsntrThumbChk() {
		return cnsntrThumbChk;
	}

	public void setCnsntrThumbChk(String cnsntrThumbChk) {
		this.cnsntrThumbChk = cnsntrThumbChk;
	}

	public String getCnsntrSignChk() {
		return cnsntrSignChk;
	}

	public void setCnsntrSignChk(String cnsntrSignChk) {
		this.cnsntrSignChk = cnsntrSignChk;
	}

	public String getCnsntrChk() {
		return cnsntrChk;
	}

	public void setCnsntrChk(String cnsntrChk) {
		this.cnsntrChk = cnsntrChk;
	}
	
	private String executionDate; 
	private String registrationDate;
	private String registrationNo;
	private double receiptAmount;
	private String receiptAmountDisp;
	private String propDetls;
	private String deedNamePreReg;
	private String deedTypePreReg;
	  private String mapOrderNo;
		private String mapOrderDate;
		private String tcpOrderNo;
		private String tcpOrderDate;
		
	
	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(String executionDate) {
		this.executionDate = executionDate;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public double getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public String getReceiptAmountDisp() {
		return receiptAmountDisp;
	}

	public void setReceiptAmountDisp(String receiptAmountDisp) {
		this.receiptAmountDisp = receiptAmountDisp;
	}

	public String getPropDetls() {
		return propDetls;
	}

	public void setPropDetls(String propDetls) {
		this.propDetls = propDetls;
	}

	public String getDeedNamePreReg() {
		return deedNamePreReg;
	}

	public void setDeedNamePreReg(String deedNamePreReg) {
		this.deedNamePreReg = deedNamePreReg;
	}

	public String getDeedTypePreReg() {
		return deedTypePreReg;
	}

	public void setDeedTypePreReg(String deedTypePreReg) {
		this.deedTypePreReg = deedTypePreReg;
	}

	public String getMapOrderNo() {
		return mapOrderNo;
	}

	public void setMapOrderNo(String mapOrderNo) {
		this.mapOrderNo = mapOrderNo;
	}

	public String getMapOrderDate() {
		return mapOrderDate;
	}

	public void setMapOrderDate(String mapOrderDate) {
		this.mapOrderDate = mapOrderDate;
	}

	public String getTcpOrderNo() {
		return tcpOrderNo;
	}

	public void setTcpOrderNo(String tcpOrderNo) {
		this.tcpOrderNo = tcpOrderNo;
	}

	public String getTcpOrderDate() {
		return tcpOrderDate;
	}

	public void setTcpOrderDate(String tcpOrderDate) {
		this.tcpOrderDate = tcpOrderDate;
	}
	
	private String parentPathScanDeed;
	private String fileNameScanDeed;
	private String deedDocName;
	private String deedDocPath;
	private String scannedDoc;
	private String thumbRemarks;
	private String thumbRemarksWit;
	private String thumbRemarksCns;
	private String partyRemarks;
	private String witRemarks;
	private String cnsRemarks;
	private String deedDocCheckBox;
	private String advancePaymntDetails;
	private String totaldutyBeforeExemp ;
	private String registrationFeeBeforeExemp;
	
	private String exempStamp;
	private String exempReg;
	// new field for instrument
     private String agreementDetails;
     private String contractDetails;
	
   
	
	
	
	public String getParentPathScanDeed() {
		return parentPathScanDeed;
	}

	public void setParentPathScanDeed(String parentPathScanDeed) {
		this.parentPathScanDeed = parentPathScanDeed;
	}

	public String getFileNameScanDeed() {
		return fileNameScanDeed;
	}

	public void setFileNameScanDeed(String fileNameScanDeed) {
		this.fileNameScanDeed = fileNameScanDeed;
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

	public String getScannedDoc() {
		return scannedDoc;
	}

	public void setScannedDoc(String scannedDoc) {
		this.scannedDoc = scannedDoc;
	}

	public String getThumbRemarks() {
		return thumbRemarks;
	}

	public void setThumbRemarks(String thumbRemarks) {
		this.thumbRemarks = thumbRemarks;
	}

	public String getPartyRemarks() {
		return partyRemarks;
	}

	public void setPartyRemarks(String partyRemarks) {
		this.partyRemarks = partyRemarks;
	}

	public String getWitRemarks() {
		return witRemarks;
	}

	public void setWitRemarks(String witRemarks) {
		this.witRemarks = witRemarks;
	}

	public String getCnsRemarks() {
		return cnsRemarks;
	}

	public void setCnsRemarks(String cnsRemarks) {
		this.cnsRemarks = cnsRemarks;
	}

	public String getThumbRemarksWit() {
		return thumbRemarksWit;
	}

	public void setThumbRemarksWit(String thumbRemarksWit) {
		this.thumbRemarksWit = thumbRemarksWit;
	}

	public String getThumbRemarksCns() {
		return thumbRemarksCns;
	}

	public void setThumbRemarksCns(String thumbRemarksCns) {
		this.thumbRemarksCns = thumbRemarksCns;
	}

	public String getDeedDocCheckBox() {
		return deedDocCheckBox;
	}

	public void setDeedDocCheckBox(String deedDocCheckBox) {
		this.deedDocCheckBox = deedDocCheckBox;
	}

	public String getAdvancePaymntDetails() {
		return advancePaymntDetails;
	}

	public void setAdvancePaymntDetails(String advancePaymntDetails) {
		this.advancePaymntDetails = advancePaymntDetails;
	}

	public String getTotaldutyBeforeExemp() {
		return totaldutyBeforeExemp;
	}

	public void setTotaldutyBeforeExemp(String totaldutyBeforeExemp) {
		this.totaldutyBeforeExemp = totaldutyBeforeExemp;
	}

	public String getRegistrationFeeBeforeExemp() {
		return registrationFeeBeforeExemp;
	}

	public void setRegistrationFeeBeforeExemp(String registrationFeeBeforeExemp) {
		this.registrationFeeBeforeExemp = registrationFeeBeforeExemp;
	}

	public String getExempStamp() {
		return exempStamp;
	}

	public void setExempStamp(String exempStamp) {
		this.exempStamp = exempStamp;
	}

	public String getExempReg() {
		return exempReg;
	}

	public void setExempReg(String exempReg) {
		this.exempReg = exempReg;
	}
	
	private ArrayList<CommonDTO> listDto;
	private String govtOfcCheck;
	
	private String nameOfOfficial;
	
	private String desgOfOfficial;
	
	private String addressOfOfficial;
	
	private String officialCheck;
	private String deedStat;
	private String deedDoc;
	private String pdfFlag;
	private String forwardPage;
	private String deedDraftId;
	
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

	public ArrayList<CommonDTO> getListDto() {
		return listDto;
	}

	public void setListDto(ArrayList<CommonDTO> listDto) {
		this.listDto = listDto;
	}

	public String getDeedStat() {
		return deedStat;
	}

	public void setDeedStat(String deedStat) {
		this.deedStat = deedStat;
	}

	public String getDeedDoc() {
		return deedDoc;
	}

	public void setDeedDoc(String deedDoc) {
		this.deedDoc = deedDoc;
	}

	public String getPdfFlag() {
		return pdfFlag;
	}

	public void setPdfFlag(String pdfFlag) {
		this.pdfFlag = pdfFlag;
	}

	public String getForwardPage() {
		return forwardPage;
	}

	public void setForwardPage(String forwardPage) {
		this.forwardPage = forwardPage;
	}

	public String getDeedDraftId() {
		return deedDraftId;
	}

	public void setDeedDraftId(String deedDraftId) {
		this.deedDraftId = deedDraftId;
	}

	public void setRegFeePaid(Double regFeePaid) {
		this.regFeePaid = regFeePaid;
	}

	public Double getRegFeePaid() {
		return regFeePaid;
	}

	public void setThumbRemarkIndex(String thumbRemarkIndex) {
		this.thumbRemarkIndex = thumbRemarkIndex;
	}

	public String getThumbRemarkIndex() {
		return thumbRemarkIndex;
	}

	public void setOldRegistrationNumber(String oldRegistrationNumber) {
		this.oldRegistrationNumber = oldRegistrationNumber;
	}

	public String getOldRegistrationNumber() {
		return oldRegistrationNumber;
	}

	

	public void setRegExemption(ArrayList regExemption) {
		this.regExemption = regExemption;
	}

	public ArrayList getRegExemption() {
		return regExemption;
	}

	public void setStampExemption(ArrayList stampExemption) {
		this.stampExemption = stampExemption;
	}

	public ArrayList getStampExemption() {
		return stampExemption;
	}

	public void setExemptionList(ArrayList exemptionList) {
		this.exemptionList = exemptionList;
	}

	public ArrayList getExemptionList() {
		return exemptionList;
	}

	public void setExemptionName(ArrayList exemptionName) {
		this.exemptionName = exemptionName;
	}

	public ArrayList getExemptionName() {
		return exemptionName;
	}

	public void setExemptionON(ArrayList exemptionON) {
		this.exemptionON = exemptionON;
	}

	public ArrayList getExemptionON() {
		return exemptionON;
	}
	public String getAgreementDetails() {
		return agreementDetails;
	}
	public void setAgreementDetails(String agreementDetails) {
		this.agreementDetails = agreementDetails;
	}

	public void setContractDetails(String contractDetails) {
		this.contractDetails = contractDetails;
	}

	public String getContractDetails() {
		return contractDetails;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}
	
	
	public void setPartyRoleFullNamePOA(String partyRoleFullNamePOA) {
		PartyRoleFullNamePOA = partyRoleFullNamePOA;
	}

	public String getPartyRoleFullNamePOA() {
		return PartyRoleFullNamePOA;
	}
	
	
	//Added for aadhar EKYC by Ankit -- start
	
	//private String partyIdForValidation;
	private String isAadharValidated;
	private String detailsVerified;
	private String conDetailsVerified;
	
	private String aadharRespCode;
	private String aadharErrMsg;
	private AadharDTO aadharDto ;
	private String ErrAndExpMsg;
	private String base64String;
	private String model;
	private String serialNo;
	
	public String getAadharRespCode() {
		return aadharRespCode;
	}

	public void setAadharRespCode(String aadharRespCode) {
		this.aadharRespCode = aadharRespCode;
	}

	public String getAadharErrMsg() {
		return aadharErrMsg;
	}

	public void setAadharErrMsg(String aadharErrMsg) {
		this.aadharErrMsg = aadharErrMsg;
	}

	public AadharDTO getAadharDto() {
		return aadharDto;
	}

	public void setAadharDto(AadharDTO aadharDto) {
		this.aadharDto = aadharDto;
	}	
	

/*	public String getPartyIdForValidation() {
		return partyIdForValidation;
	}

	public void setPartyIdForValidation(String partyIdForValidation) {
		this.partyIdForValidation = partyIdForValidation;
	}
*/

	public String getDetailsVerified() {
		return detailsVerified;
	}

	public void setDetailsVerified(String detailsVerified) {
		this.detailsVerified = detailsVerified;
	}

	public String getConDetailsVerified() {
		return conDetailsVerified;
	}

	public void setConDetailsVerified(String conDetailsVerified) {
		this.conDetailsVerified = conDetailsVerified;
	}

	public String getIsAadharValidated() {
		return isAadharValidated;
	}

	public void setIsAadharValidated(String isAadharValidated) {
		this.isAadharValidated = isAadharValidated;
	}

	public String getErrAndExpMsg() {
		return ErrAndExpMsg;
	}

	public void setErrAndExpMsg(String errAndExpMsg) {
		ErrAndExpMsg = errAndExpMsg;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public void setParentFuncID(String parentFuncID) {
		this.parentFuncID = parentFuncID;
	}

	public String getParentFuncID() {
		return parentFuncID;
	}

	private String parentFuncID;
	
	//Added for aadhar EKYC by Ankit -- end		
	
	private String newSignPartyTxnId;
	private String newPartySignPath;


	public String getNewSignPartyTxnId() {
		return newSignPartyTxnId;
	}

	public void setNewSignPartyTxnId(String newSignPartyTxnId) {
		this.newSignPartyTxnId = newSignPartyTxnId;
	}

	public String getNewPartySignPath() {
		return newPartySignPath;
	}

	public void setNewPartySignPath(String newPartySignPath) {
		this.newPartySignPath = newPartySignPath;
	}
	
	
	//for cyber tehsil
	private String formAPath;
	private String formA1Path;
	private String formA2Path;

	public String getFormAPath() {
		return formAPath;
	}

	public void setFormAPath(String formAPath) {
		this.formAPath = formAPath;
	}

	public String getFormA1Path() {
		return formA1Path;
	}

	public void setFormA1Path(String formA1Path) {
		this.formA1Path = formA1Path;
	}

	public String getFormA2Path() {
		return formA2Path;
	}

	public void setFormA2Path(String formA2Path) {
		this.formA2Path = formA2Path;
	}
	
}
