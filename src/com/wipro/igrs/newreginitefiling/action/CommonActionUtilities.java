package com.wipro.igrs.newreginitefiling.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;
import com.wipro.igrs.newreginitefiling.constant.RegInitConstant;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;

public class CommonActionUtilities {
	
	private Logger logger = (Logger) Logger.getLogger(CommonActionUtilities.class);
	
	public void abc(RegCommonForm regForm, RegCommonBO commonBo,
			String languageLocale, RegCommonDTO commonDto){
		regForm.setDeleteOwnerID("");
		regForm.setHdnDeleteOwnerID("");
		regForm.setTrnsOwnerList(new HashMap());

		regForm.setNameOfOfficialTrns("");
		regForm.setDesgOfOfficialTrns("");
		regForm.setAddressOfOfficialTrns("");
		regForm.setAddressGovtOffclOutMpTrns("");
		regForm.setAddressAuthPerOutMpTrns("");
		regForm.setAddressIndOutMpTrns("");
		regForm.setAddressOrgOutMpTrns("");
		regForm.setAddressOwnerOutMpTrns("");

		regForm.setFnameTrns("");
		regForm.setMnameTrns("");
		regForm.setLnameTrns("");
		regForm.setGendarTrns("M");
		regForm.setAgeTrns("");
		regForm.setFatherNameTrns("");
		regForm.setMotherNameTrns("");
		regForm.setSpouseNameTrns("");
		regForm.setNationalityTrns("");
		regForm.setIndaddressTrns("");
		regForm.setIndcountryTrns("");
		regForm.setIndstatenameTrns("");
		regForm.setInddistrictTrns("");
		commonDto.setTehsilId1("");
		regForm.setPostalCodeTrns("");
		regForm.setIndphnoTrns("");
		regForm.setIndmobnoTrns("");
		regForm.setEmailIDTrns("");
		regForm.setListIDTrns("");
		regForm.setIdnoTrns("");

		regForm.setIndCasteTrns("");
		regForm.setIndReligionTrns("");
		regForm.setIndDisabilityTrns("N");
		regForm.setShareOfPropTrns("");
		regForm.setOwnershipShareTrns("");
		regForm.setRelationWithOwnerTrns("");
		regForm.setIndDisabilityDescTrns("");
		commonDto.setStateTrns(commonBo.getState("1", languageLocale));
		commonDto.setDistrictTrns(commonBo.getDistrict("1",
				languageLocale));
		commonDto.setIndstateTrns(commonBo
				.getState("1", languageLocale));
		commonDto.setInddistrictTrns(commonBo.getDistrict("1",
				languageLocale));
		// commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
		commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
		commonDto
				.setIndcountryTrns(commonBo.getCountry(languageLocale));
		commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
		commonDto.setIdProf(commonBo.getIdProf(languageLocale));
		commonDto.setCategoryList(commonBo
				.getCategoryList(languageLocale));
		commonDto.setOccupationList(commonBo
				.getOccupationList(languageLocale));
		commonDto.setRelationshipList(commonBo.getRelationshipList(
				regForm.getGendarTrns(), languageLocale));
		regForm.setRelationshipTrns(0);
		// commonDto.setDistrictTrns(commonBo.getDistrict("1"));

		// regForm.setIndPovertyLine("");
		regForm.setIndPovertyLineTrns("N");
		regForm.setIndMinorityTrns("N");
		regForm.setIndCategoryTrns("");

		regForm.setIndScheduleAreaTrns("");
		regForm.setPermissionNoTrns("");
		regForm.setOccupationIdTrns("");
		regForm.setCertificateTrns(null);
		regForm.setDocumentNameTrns("");
		regForm.setDocumentSizeTrns("");
		regForm.setFilePathTrns("");

		regForm.setOgrNameTrns("");
		regForm.setAuthPerNameTrns("");
		regForm.setOrgaddressTrns("");
		regForm.setCountryTrns("");
		regForm.setStatenameTrns("");
		regForm.setDistrictTrns("");
		commonDto.setTehsilId1("");
		regForm.setPhnoTrns("");
		regForm.setMobnoTrns("");
		regForm.setActionName(RegInitConstant.NO_ACTION);

		regForm.setOwnerNameTrns("");
		regForm.setOwnerOgrNameTrns("");
		regForm.setOwnerAddressTrns("");
		regForm.setOwnerAgeTrns("");
		regForm.setOwnerEmailIDTrns("");
		regForm.setOwnerGendarTrns("M");
		regForm.setOwnerIdnoTrns("");
		regForm.setOwnerListIDTrns("");
		regForm.setOwnerNationalityTrns("");
		regForm.setOwnerPhnoTrns("");
		regForm.setOwnerProofNameTrns("");

		regForm.setCountryTrns("1");
		regForm.setCountryNameTrns("INDIA");
		regForm.setStatenameTrns("1");
		regForm.setStatenameNameTrns("MADHYA PRADESH");
		regForm.setIndcountryTrns("1");
		regForm.setIndcountryNameTrns("INDIA");
		regForm.setIndstatenameTrns("1");
		regForm.setIndstatenameNameTrns("MADHYA PRADESH");
		regForm.setUserDayTrns("");
		regForm.setUserMonthTrns("");
		regForm.setUserYearTrns("");
		regForm.setUserDOBTrns("");

		regForm.setCertificateTrns(null);
		regForm.setDocumentNameTrns("");
		regForm.setDocumentSizeTrns("");
		regForm.setFilePathTrns("");
		regForm.setDocContentsTrns(new byte[0]);
		regForm.setPartyOrPropTrns("");
		regForm.setUploadTypeTrns("");

		regForm.setU2Trns(null);
		regForm.setU2DocumentNameTrns("");
		regForm.setU2DocumentSizeTrns("");
		regForm.setU2FilePathTrns("");
		regForm.setU2DocContentsTrns(new byte[0]);
		regForm.setU2PartyOrPropTrns("");
		regForm.setU2UploadTypeTrns("");

		regForm.setU3Trns(null);
		regForm.setU3DocumentNameTrns("");
		regForm.setU3DocumentSizeTrns("");
		regForm.setU3FilePathTrns("");
		regForm.setU3DocContentsTrns(new byte[0]);
		regForm.setU3PartyOrPropTrns("");
		regForm.setU3UploadTypeTrns("");

		regForm.setU4Trns(null);
		regForm.setU4DocumentNameTrns("");
		regForm.setU4DocumentSizeTrns("");
		regForm.setU4FilePathTrns("");
		regForm.setU4DocContentsTrns(new byte[0]);
		regForm.setU4PartyOrPropTrns("");
		regForm.setU4UploadTypeTrns("");

		regForm.setU5Trns(null);
		regForm.setU5DocumentNameTrns("");
		regForm.setU5DocumentSizeTrns("");
		regForm.setU5FilePathTrns("");
		regForm.setU5DocContentsTrns(new byte[0]);
		regForm.setU5PartyOrPropTrns("");
		regForm.setU5UploadTypeTrns("");

		regForm.setU6Trns(null);
		regForm.setU6DocumentNameTrns("");
		regForm.setU6DocumentSizeTrns("");
		regForm.setU6FilePathTrns("");
		regForm.setU6DocContentsTrns(new byte[0]);
		regForm.setU6PartyOrPropTrns("");
		regForm.setU6UploadTypeTrns("");

		regForm.setU7Trns(null);
		regForm.setU7DocumentNameTrns("");
		regForm.setU7DocumentSizeTrns("");
		regForm.setU7FilePathTrns("");
		regForm.setU7DocContentsTrns(new byte[0]);
		regForm.setU7PartyOrPropTrns("");
		regForm.setU7UploadTypeTrns("");

		regForm.setU8Trns(null);
		regForm.setU8DocumentNameTrns("");
		regForm.setU8DocumentSizeTrns("");
		regForm.setU8FilePathTrns("");
		regForm.setU8DocContentsTrns(new byte[0]);
		regForm.setU8PartyOrPropTrns("");
		regForm.setU8UploadTypeTrns("");

		regForm.setU9Trns(null);
		regForm.setU9DocumentNameTrns("");
		regForm.setU9DocumentSizeTrns("");
		regForm.setU9FilePathTrns("");
		regForm.setU9DocContentsTrns(new byte[0]);
		regForm.setU9PartyOrPropTrns("");
		regForm.setU9UploadTypeTrns("");

		regForm.setU10Trns(null);
		regForm.setU10DocumentNameTrns("");
		regForm.setU10DocumentSizeTrns("");
		regForm.setU10FilePathTrns("");
		regForm.setU10DocContentsTrns(new byte[0]);
		regForm.setU10PartyOrPropTrns("");
		regForm.setU10UploadTypeTrns("");

		regForm.setU11Trns(null);
		regForm.setU11DocumentNameTrns("");
		regForm.setU11DocumentSizeTrns("");
		regForm.setU11FilePathTrns("");
		regForm.setU11DocContentsTrns(new byte[0]);
		regForm.setU11PartyOrPropTrns("");
		regForm.setU11UploadTypeTrns("");

		regForm.setAuthPerAddressTrns("");
		regForm.setAuthPerCountryTrns("1");
		regForm.setAuthPerDistrictTrns("");
		commonDto.setTehsilId1("");
		regForm.setAuthPerStatenameTrns("1");
		regForm.setAuthPerFatherNameTrns("");
		regForm.setAuthPerRelationshipTrns(0);
		regForm.setAuthPerGendarTrns("M");

		// regForm.setIndstatenameTrns("MP");
		// regForm.setIndstatenameNameTrns("MADHYA PRADESH");

		// session.setAttribute("commonDto", commonDto);
		regForm.setCommonDto(commonDto);
	}
	
	public String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return "";
	}
	
	
	
	public void cancelAction(HttpSession session, RegCommonForm regForm) {
		if (regForm != null) {
			regForm.setFname("");
			regForm.setMname("");
			regForm.setLname("");
			regForm.setGendar("M");
			regForm.setAge("");
			regForm.setFatherName("");
			regForm.setMotherName("");
			regForm.setSpouseName("");
			regForm.setNationality("");
			regForm.setIndaddress("");
			regForm.setIndcountry("");
			regForm.setIndstatename("");
			regForm.setInddistrict("");
			regForm.setPostalCode("");
			regForm.setIndphno("");
			regForm.setIndmobno("");
			regForm.setEmailID("");
			regForm.setListID("");
			regForm.setIdno("");
			regForm.setDeedType("");
			regForm.setPurpose("");
			regForm.setInstrument("");
			regForm.setIndCaste("");
			regForm.setIndReligion("");
			regForm.setIndDisability("N");
			regForm.setIndMinority("");

			regForm.setOwnershipShare("");
			regForm.setRelationWithOwner("");

			regForm.setOgrName("");
			regForm.setAuthPerName("");
			regForm.setOrgaddress("");
			regForm.setCountry("");
			regForm.setStatename("");
			regForm.setDistrict("");
			regForm.setPhno("");
			regForm.setMobno("");
			regForm.setDeedType("");
			regForm.setPurpose("");
			regForm.setFnameTrns("");
			regForm.setMnameTrns("");
			regForm.setLnameTrns("");
			regForm.setGendarTrns("M");
			regForm.setAgeTrns("");
			regForm.setFatherNameTrns("");
			regForm.setMotherNameTrns("");
			regForm.setSpouseNameTrns("");
			regForm.setNationalityTrns("");
			regForm.setIndaddressTrns("");
			regForm.setIndcountryTrns("");
			regForm.setIndcountryNameTrns("");
			regForm.setIndstatenameTrns("");
			regForm.setIndstatenameNameTrns("");
			regForm.setInddistrictTrns("");
			
			regForm.setInddistrictNameTrns("");
			regForm.setIndMinorityTrns("");
			regForm.setPostalCodeTrns("");
			regForm.setIndphnoTrns("");
			regForm.setIndmobnoTrns("");
			regForm.setEmailIDTrns("");
			regForm.setListIDTrns("");
			regForm.setListIDNameTrns("");
			regForm.setIdnoTrns("");

			regForm.setPurposeTrns("");
			regForm.setDeleteTrnsPrtyID("");
			regForm.setIndCasteTrns("");
			regForm.setIndReligionTrns("");
			regForm.setIndDisabilityTrns("N");
			regForm.setShareOfPropTrns("");
			regForm.setOwnershipShareTrns("");
			regForm.setRelationWithOwnerTrns("");

			regForm.setIndDisabilityDesc("");
			regForm.setIndDisabilityDescTrns("");

			regForm.setOgrNameTrns("");
			regForm.setAuthPerNameTrns("");
			regForm.setOrgaddressTrns("");
			regForm.setCountryTrns("");
			regForm.setCountry("");
			regForm.setCountryNameTrns("");
			regForm.setStatenameTrns("");
			regForm.setStatenameNameTrns("");
			regForm.setDistrictTrns("");
			regForm.setDistrictNameTrns("");
			regForm.setPhnoTrns("");
			regForm.setMobnoTrns("");
			regForm.setDeleteTrnsPrtyID("");
			regForm.setPartyType(null);
			regForm.setPartyTypeTrns(null);
			regForm.setListAdoptedParty(null);
			regForm.setListAdoptedPartyTrns(null);
			regForm.setOwnerList(new ArrayList());
			regForm.setPoaList(new ArrayList());
			regForm.setSelectedPoa(null);
			regForm.setSelectedPoaName(null);
			regForm.setParty1OwnerCount(0);
			regForm.setParty1PoaHolderCount(0);
			regForm.setParty2OwnerCount(0);
			regForm.setParty2PoaHolderCount(0);
			regForm.setDoneeCount(0);
			regForm.setDonorCount(0);
			regForm.setBuyerCount(0);
			regForm.setSellerPoaCount(0);
			regForm.setSellerSelfCount(0);
			regForm.setOwnerCount(0);
			regForm.setPoaAccepterCount(0);
			regForm.setPoaHolderCount(0);
			regForm.setOwnerId("");
			regForm.setHdnDeleteTrnsPrtyId("");
			regForm.setHdnOwnerId("");
			regForm.setHidnRegTxnId("");
			regForm.setHidnUserId("");
			regForm.setMapTransactingParties(new HashMap());
			regForm.setMapTransactingPartiesDisp(new HashMap());
			regForm.setMapTransPartyDbInsertion(new HashMap());
			regForm.setRegInitEstampCode(null);
			regForm.setRegInitPaymntTxnId(null);
			regForm.setRegInitPermTxnId("");
			regForm.setCurrDateTime("");
			regForm.setIsMultiplePropsFlag(0);
			regForm.setIndPovertyLine("");
			regForm.setIndPovertyLineTrns("");
			regForm.setActionName(" ");
			regForm.setFormName(" ");
			regForm.setPage("success");
			regForm.setListAdoptedPartyNameTrns("");
			regForm.setListAdoptedPartyName("");
			regForm.setAddMoreCounter(0);
			regForm.setRegInitPermTxnId("");
			regForm.setMapRegTxnIdDisp(new HashMap());
			regForm.setOwnerId("");
			regForm.setHdnOwnerId("");
			regForm.setHdnDeleteTrnsPrtyId("");
			regForm.setAbc("");
			regForm.setCurrDateTime("");
			regForm.setDeedID(0);
			regForm.setDeedtype1("");
			regForm.setInstType("");
			regForm.setStampduty1("");
			regForm.setNagarPalikaDuty("");
			regForm.setPanchayatDuty("");
			regForm.setUpkarDuty("");
			regForm.setTotalduty("");
			regForm.setRegistrationFee("");
			regForm.setSelectedExemptionList(new ArrayList());
			regForm.setTotalMarketValue("");
			regForm.setExemType("");
			regForm.setFromModule("");
			regForm.setCheckModule("");
			regForm.setCheckRegNo(""); // TO BE SET AS BLANK
			regForm.setApplicantUserIdCheck("");
			regForm.setHdnapplicantUserIdCheck("");
			regForm.setInstID(0);
			regForm.setExmpID("");
			// regForm.setPendingRegApplicationList(new ArrayList());
			regForm.setPropertyId("");
			regForm.setValuationId("");
			regForm.setIsDashboardFlag(0);
			regForm.setIsMultiplePropsFlag(0);
			regForm.setIsTransactingPartyAddedFlag(0);
			regForm.setMapPropertyTransParty(new HashMap());
			regForm.setMapPropertyTransPartyDisp(new HashMap());
			regForm.setApplicantRoleId(0);
			regForm.setApplicantRoleId2(0);
			regForm.setTotalShareOfProp(0);
			regForm.setTotalShareOfPropBuyer(0);
			regForm.setTotalShareOfPropSellerSelf(0);
			regForm.setTotalShareOfPropDonor(0);
			regForm.setTotalShareOfPropDonee(0);
			regForm.setTotalShareOfPropOwnerSelf(0);
			regForm.setTotalShareOfPropOwnerSelfParty1(0);
			regForm.setTotalShareOfPropOwnerSelfParty2(0);
			regForm.setTotalShareOfPropLesser(0);
			regForm.setTotalShareOfPropLessee(0);
			regForm.setOwnerName("");
			regForm.setOwnerOgrName("");
			regForm.setOwnerGendar("M");
			regForm.setOwnerNationality("");
			regForm.setOwnerAddress("");
			regForm.setOwnerPhno("");
			regForm.setOwnerEmailID("");
			regForm.setOwnerListID("");
			regForm.setOwnerIdno("");
			regForm.setOwnerAge("");
			regForm.setOwnerProofName("");
			regForm.setOwnerNameTrns("");
			regForm.setOwnerOgrNameTrns("");
			regForm.setOwnerGendarTrns("M");
			regForm.setOwnerNationalityTrns("");
			regForm.setOwnerAddressTrns("");
			regForm.setOwnerPhnoTrns("");
			regForm.setOwnerEmailIDTrns("");
			regForm.setOwnerListIDTrns("");
			regForm.setOwnerIdnoTrns("");
			regForm.setOwnerAgeTrns("");
			regForm.setOwnerProofNameTrns("");
			regForm.setPaymentCompleteFlag(0);
			regForm.setIsDutyCalculated(0);
			regForm.setMarketValueShares("");
			regForm.setDutyPaid("");
			regForm.setLabelAmountFlag("");
			regForm.setMapPropertyTransParty(new HashMap());
			regForm.setAdjudicationNumber("");
			regForm.setErrorMsg("");
			regForm.setExchangePropertyList(new ArrayList());
			regForm.setAgriPropertyList(new ArrayList());
			regForm.setIndMinority("N");
			regForm.setIndMinorityTrns("N");
			regForm.setIndPovertyLine("N");
			regForm.setIndPovertyLineTrns("N");
			regForm.setSelectedCategoryName("");
			regForm.setSelectedCategoryNameTrns("");
			regForm.setIndDisabilityDesc("");
			regForm.setIndDisabilityDescTrns("");
			regForm.setUserDayTrns("");
			regForm.setUserMonthTrns("");
			regForm.setUserYearTrns("");
			regForm.setUserDOBTrns("");
			regForm.setUserDay("");
			regForm.setUserMonth("");
			regForm.setUserYear("");
			regForm.setUserDOB("");
			regForm.setOwnerDayTrns("");
			regForm.setOwnerMonthTrns("");
			regForm.setOwnerYearTrns("");
			regForm.setOwnerDOBTrns("");
			regForm.setOwnerDay("");
			regForm.setOwnerMonth("");
			regForm.setOwnerYear("");
			regForm.setOwnerDOB("");
			regForm.setHdnDeclareShareCheck("Y");
		}
		session.removeAttribute("commonDto");
		session.removeAttribute("roleId");
		session.removeAttribute("functionId");
		session.removeAttribute("partyType");
		session.removeAttribute("regForm");
		session.removeAttribute("status");
		session.removeAttribute("view");
		session.removeAttribute("eform");
		session.removeAttribute("labelAmountFlag");

	}

	 

}
