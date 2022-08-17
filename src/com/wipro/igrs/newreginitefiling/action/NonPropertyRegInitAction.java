/*
 * Copyright Notice
 *==============================================================================
 * This file contains proprietary information of Wipro Infotech, Bangalore.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2013-14
 *==============================================================================
 *
 * File Name   :   NonPropertyRegInitAction.java
 * Author      :   Roopam Mehta
 * Description :   Represents the Common Action Class for Registration Initiation, non PV related deeds.
 * ----------------------------------------------------------------------------
 * Version         Last Modified By     Last Modified On           Modification
 * ----------------------------------------------------------------------------
 * 	0.0             Roopam Mehta		 26th Jul, 2013	 		 
 * --------------------------------------------------------------------------------
 */

package com.wipro.igrs.newreginitefiling.action;

//import java.sql.ResultSetMetaData;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.RegCompMaker.dto.RegCompleteMakerDTO;
import com.wipro.igrs.baseaction.action.BaseAction;
import com.wipro.igrs.commonEfiling.PropertiesFileReader;
import com.wipro.igrs.deedDraft.bd.DeedDraftBD;
import com.wipro.igrs.newdutycalculationefiling.bo.DutyCalculationBO;
import com.wipro.igrs.newdutycalculationefiling.constant.CommonConstant;
import com.wipro.igrs.newdutycalculationefiling.form.DutyCalculationForm;
import com.wipro.igrs.newreginitefiling.bo.RegCommonBO;
import com.wipro.igrs.newreginitefiling.constant.RegInitConstant;
import com.wipro.igrs.newreginitefiling.dto.CommonDTO;
import com.wipro.igrs.newreginitefiling.dto.PropertyValuationDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCommonDTO;
import com.wipro.igrs.newreginitefiling.dto.RegCompletDTO;
import com.wipro.igrs.newreginitefiling.form.RegCommonForm;

//import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;

public class NonPropertyRegInitAction extends BaseAction {
	private Logger logger = (Logger) Logger.getLogger(NonPropertyRegInitAction.class);

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		String forward = "";
		RegCommonBO commonBo = new RegCommonBO();
		RegCommonForm regForm;
		CommonAction action = new CommonAction();
		HashMap map = null;
		HashMap map2 = null;
		if (request.getAttribute("regFormDashboard") != null) {
			regForm = (RegCommonForm) request.getAttribute("regFormDashboard");

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());

		} else
			regForm = (RegCommonForm) form;

		String languageLocale = "hi";
		if (session.getAttribute("languageLocale") != null) {
			languageLocale = (String) session.getAttribute("languageLocale");
		}

		if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
			session.setAttribute("modName", RegInitConstant.MODNAME_ENGLISH);
		} else {
			session.setAttribute("modName", RegInitConstant.MODNAME_HINDI);
		}
		// --------Only Reg Init Code needed by Title deed

		// ArrayList mainList;
		// following code for clearing form beans when the module is hit first
		// time.
		if (request.getParameter("regStatus") != null) {
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK2)) {
				if (regForm != null) {

					RegCommonDTO commonDto = new RegCommonDTO();
					commonDto.setInstrument(new ArrayList());
					commonDto.setExemption(new ArrayList());

					regForm.setDeleteOwnerID("");
					regForm.setHdnDeleteOwnerID("");
					regForm.setOwnerGendarVal("");
					regForm.setOwnerModifyFlag(0);

					regForm.setAppOwnerList(new HashMap());
					regForm.setTrnsOwnerList(new HashMap());

					regForm.setTotalConsenterConsid("");
					regForm.setAddressIndOutMp("");
					regForm.setAddressOrgOutMp("");
					regForm.setAddressAuthPerOutMp("");
					regForm.setAddressOwnerOutMp("");
					regForm.setAddressGovtOffclOutMp("");

					regForm.setPoaShareDebenture("");

					regForm.setCancelDeedRadio(0);

					regForm.setAddressIndOutMpTrns("");
					regForm.setAddressOrgOutMpTrns("");
					regForm.setAddressAuthPerOutMpTrns("");
					regForm.setAddressOwnerOutMpTrns("");
					regForm.setAddressGovtOffclOutMpTrns("");
					regForm.setConsenterUploadList(new ArrayList());
					regForm.setRegDTO(new RegCompleteMakerDTO());
					regForm.setUpOrNot("");
					regForm.setConsenterFlag("N");
					regForm.setConsenterWithConsidFlag("N");
					regForm.setConsenterCount(0);
					regForm.setConsenterAddedCount(0);
					regForm.setDtlsMapConsntr(new HashMap());
					regForm.setConsenterId("");
					regForm.setDeleteConsenter("");
					regForm.setHdnDeleteConsenter("");
					regForm.setConsenterWiseDutyList(new ArrayList());
					regForm.setFamilyFlag("N");
					regForm.setIsDashboardFlag(3);
					regForm.setMergeSuccess("N");
					regForm.setTermsCondPartyApp("");
					regForm.setTermsCondPartyTrns("");
					regForm.setTermsCondProp("");
					regForm.setFinalDisclaimerCheck("off");
					regForm.setGovtOfcCheck("");
					regForm.setNameOfOfficial("");
					regForm.setDesgOfOfficial("");
					regForm.setAddressOfOfficial("");
					regForm.setFinalPage("");
					regForm.setTransCheck("");
					regForm.setConsUploadFlag("0");
					regForm.setContriProp("");

					regForm.setDissolutionDate("");
					regForm.setRetirmentDate("");

					regForm.setNameOfOfficialTrns("");
					regForm.setDesgOfOfficialTrns("");
					regForm.setAddressOfOfficialTrns("");

					regForm.setListDto(new ArrayList());
					regForm.setListDtoAdju(new ArrayList());
					regForm.setListDtoP(new ArrayList());

					regForm.setDeedDraftId("");

					regForm.setAddMoreTransParty("Y");

					regForm.setApplicantUserIdCheck(null);
					regForm.setIndCategory("");
					regForm.setIndCategoryTrns("");
					regForm.setOccupationId("");
					regForm.setOccupationIdTrns("");

					regForm.setBankName("");
					regForm.setBranchName("");
					regForm.setBankAddress("");
					regForm.setBankAuthPer("");
					regForm.setBankLoanAmt("0");
					regForm.setBankSancAmt("0");

					regForm.setTrustName("");
					regForm.setTrustDate("");
					regForm.setDeedTypeFlag(0);

					regForm.setAdoptionDeedParty1Added(0);
					regForm.setAdoptionDeedParty2Added(0);
					regForm.setAdoptionDeedParty3Added(0);

					regForm.setRent(0);
					regForm.setAdvance(0);
					regForm.setAdvancePaymntDetails("");
					regForm.setDevlpmtCharge(0);
					regForm.setOtherRecCharge(0);
					regForm.setPremium(0);
					regForm.setTermLease(0);
					regForm.setCallingAction("");

					regForm.setWithPos("");
					regForm.setSecLoanAmt(0);
					regForm.setPoaWithConsid("");
					regForm.setPoaPeriod(0);

					regForm.setPaidLoanAmt(0);

					regForm.setConsiAmountTrns("");
					regForm.setExtraFieldLabel(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE);
					regForm.setContriProp("");
					regForm.setAdvancePaymntDate("");
					regForm.setPossGiven("N");
					regForm.setPossGivenName("");

					regForm.setAgreeMemoInstFlag(0);
					regForm.setClaimantFlag(0);
					regForm.setPoaHolderFlag(0);

					regForm.setIndcountryArb("1");
					regForm.setIndstatenameArb("1");
					regForm.setInddistrictArb("");
					regForm.setFnameArb("");
					regForm.setMnameArb("");
					regForm.setLnameArb("");
					regForm.setGendarArb("");
					regForm.setAgeArb("");
					regForm.setFatherNameArb("");
					regForm.setMotherNameArb("");
					regForm.setSpouseNameArb("");
					regForm.setNationalityArb("");
					regForm.setIndaddressArb("");
					regForm.setIndcountryArb("");
					regForm.setIndstatenameArb("");
					regForm.setInddistrictArb("");
					regForm.setIndphnoArb("");
					regForm.setIndmobnoArb("");
					regForm.setEmailIDArb("");
					regForm.setIndCategoryArb("");
					regForm.setIndDisabilityArb("");
					regForm.setIndDisabilityDescArb("");
					regForm.setListIDArb("");
					regForm.setIdnoArb("");

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

					regForm.setInstrument("");
					regForm.setIndCaste("");
					regForm.setIndReligion("");
					regForm.setIndDisability("N");
					regForm.setIndDisabilityDesc("");
					regForm.setIndDisabilityDescTrns("");
					// regForm.setShareOfProp("");
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

					regForm.setRelationshipTrns(0);
					regForm.setRelationship(0);
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
					regForm.setRelationWithOwner("");

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
					regForm.setOwnerId("");
					regForm.setHdnDeleteTrnsPrtyId("");
					regForm.setHdnOwnerId("");
					// regForm.setHidnRegTxnId("");
					regForm.setHidnUserId("");
					regForm.setPropertyDTO(new PropertyValuationDTO());
					regForm.setRegcompletDto(new RegCompletDTO());
					regForm.setMapTransactingParties(new HashMap());
					regForm.setMapTransactingPartiesDisp(new HashMap());
					regForm.setMapTransPartyDbInsertion(new HashMap());
					regForm.setRegInitEstampCode(null);
					regForm.setRegInitPaymntTxnId(null);
					regForm.setRegInitPermTxnId("");
					regForm.setCurrDateTime("");
					regForm.setPropertyId("");
					regForm.setTotalPoaCount(0);
					regForm.setTotalOwnerCount(0);

					regForm.setActionName(" ");
					regForm.setFormName(" ");
					regForm.setPage("success");

					regForm.setListAdoptedPartyNameTrns("");
					regForm.setListAdoptedPartyName("");
					regForm.setAddMoreCounter(0);
					regForm.setTotalShareOfProp(0);
					regForm.setTotalShareOfPropBuyer(0);
					regForm.setTotalShareOfPropSellerSelf(0);
					regForm.setTotalShareOfPropDonor(0);
					regForm.setTotalShareOfPropDonee(0);
					regForm.setTotalShareOfPropOwnerSelf(0);
					regForm.setOwnerNameTrns("");
					regForm.setOwnerOgrNameTrns("");
					regForm.setOwnerGendarTrns("M");
					regForm.setOwnerNationalityTrns("");
					regForm.setOwnerAddressTrns("");
					regForm.setOwnerPhnoTrns("");
					regForm.setOwnerEmailIDTrns("");
					regForm.setOwnerIdnoTrns("");
					regForm.setOwnerAgeTrns("");
					regForm.setOwnerListIDTrns("");
					regForm.setOwnerProofNameTrns("");
					regForm.setIsTransactingPartyAddedFlag(0);
					regForm.setIsDashboardFlag(0);
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

					regForm.setPaymentCompleteFlag(0);
					regForm.setAdjuDutyAddedFlag("N");
					regForm.setIsDutyCalculated(0);
					regForm.setMarketValueShares("");

					regForm.setLabelAmountFlag("");
					regForm.setAdjudicationNumber("");

					regForm.setErrorMsg("");

					regForm.setMapPropertyTransParty(new HashMap());

					regForm.setPostalCountry("1");
					regForm.setPostalState("1");
					regForm.setPostalDistrict("");
					regForm.setPostalAddress("");
					regForm.setAddMoreTransParty("Y");
					regForm.setPostalAddress1("Y");
					regForm.setPostalAddress2("N");
					regForm.setHdnPostalAddress1("Y");

					regForm.setIndScheduleArea("Y");
					regForm.setPermissionNo("");
					regForm.setCertificate(null);
					regForm.setDocumentName("");
					regForm.setDocumentSize("");
					regForm.setFilePath("");
					regForm.setDocContents(new byte[0]);
					regForm.setPartyOrProp("");
					regForm.setUploadType("");

					regForm.setU2(null);
					regForm.setU2DocumentName("");
					regForm.setU2DocumentSize("");
					regForm.setU2FilePath("");
					regForm.setU2DocContents(new byte[0]);
					regForm.setU2PartyOrProp("");
					regForm.setU2UploadType("");

					regForm.setU3(null);
					regForm.setU3DocumentName("");
					regForm.setU3DocumentSize("");
					regForm.setU3FilePath("");
					regForm.setU3DocContents(new byte[0]);
					regForm.setU3PartyOrProp("");
					regForm.setU3UploadType("");

					regForm.setU4(null);
					regForm.setU4DocumentName("");
					regForm.setU4DocumentSize("");
					regForm.setU4FilePath("");
					regForm.setU4DocContents(new byte[0]);
					regForm.setU4PartyOrProp("");
					regForm.setU4UploadType("");

					regForm.setU5(null);
					regForm.setU5DocumentName("");
					regForm.setU5DocumentSize("");
					regForm.setU5FilePath("");
					regForm.setU5DocContents(new byte[0]);
					regForm.setU5PartyOrProp("");
					regForm.setU5UploadType("");

					regForm.setU6(null);
					regForm.setU6DocumentName("");
					regForm.setU6DocumentSize("");
					regForm.setU6FilePath("");
					regForm.setU6DocContents(new byte[0]);
					regForm.setU6PartyOrProp("");
					regForm.setU6UploadType("");

					regForm.setU7(null);
					regForm.setU7DocumentName("");
					regForm.setU7DocumentSize("");
					regForm.setU7FilePath("");
					regForm.setU7DocContents(new byte[0]);
					regForm.setU7PartyOrProp("");
					regForm.setU7UploadType("");

					regForm.setU8(null);
					regForm.setU8DocumentName("");
					regForm.setU8DocumentSize("");
					regForm.setU8FilePath("");
					regForm.setU8DocContents(new byte[0]);
					regForm.setU8PartyOrProp("");
					regForm.setU8UploadType("");

					regForm.setU9(null);
					regForm.setU9DocumentName("");
					regForm.setU9DocumentSize("");
					regForm.setU9FilePath("");
					regForm.setU9DocContents(new byte[0]);
					regForm.setU9PartyOrProp("");
					regForm.setU9UploadType("");

					regForm.setU10(null);
					regForm.setU10DocumentName("");
					regForm.setU10DocumentSize("");
					regForm.setU10FilePath("");
					regForm.setU10DocContents(new byte[0]);
					regForm.setU10PartyOrProp("");
					regForm.setU10UploadType("");

					regForm.setU11(null);
					regForm.setU11DocumentName("");
					regForm.setU11DocumentSize("");
					regForm.setU11FilePath("");
					regForm.setU11DocContents(new byte[0]);
					regForm.setU11PartyOrProp("");
					regForm.setU11UploadType("");

					regForm.setIndScheduleAreaTrns("Y");
					regForm.setPermissionNoTrns("");
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

					regForm.setSrOfficeNameTrns("");
					regForm.setPoaRegNoTrns("");
					regForm.setDatePoaRegTrns("");

					regForm.setSrOfficeName("");
					regForm.setPoaRegNo("");
					regForm.setDatePoaReg("");

					// for common deeds
					regForm.setCommonDeed(0);
					regForm.setAddPartyNewRole(0); // variable for radio. value
													// 0 for same role
					regForm.setRoleSameAsPrevious(0); // flag for above radio. 1
														// for previous (same)
														// role
					// regForm.setAddAnotherParty(0);
					regForm.setCommonDeedRoleName("");
					regForm.setAddMoreParticularCounter(0);
					regForm.setMapParticulars(new HashMap());
					regForm.setParticularName("");
					regForm.setParticularDesc("");
					regForm.setDeleteParticularID("");
					regForm.setHdnDeleteParticularID("");

					regForm.setAuthPerAddress("");
					regForm.setAuthPerCountry("1");
					regForm.setAuthPerDistrict("");
					regForm.setAuthPerStatename("1");
					regForm.setAuthPerFatherName("");
					regForm.setAuthPerRelationship(0);
					regForm.setAuthPerGendar("M");

					regForm.setAuthPerAddressTrns("");
					regForm.setAuthPerCountryTrns("1");
					regForm.setAuthPerDistrictTrns("");
					regForm.setAuthPerStatenameTrns("1");
					regForm.setAuthPerFatherNameTrns("");
					regForm.setAuthPerRelationshipTrns(0);
					regForm.setAuthPerGendarTrns("M");

					regForm.setFromAdjudication(0);
					regForm.setInitiateAdjuApp(0);
					regForm.setStampManually("N");
					regForm.setStampduty1("");
					regForm.setNagarPalikaDuty("");
					regForm.setPanchayatDuty("");
					regForm.setUpkarDuty("");
					regForm.setTotalduty("");
					regForm.setRegistrationFee("");
					regForm.setStampduty1Adju("");
					regForm.setNagarPalikaDutyAdju("");
					regForm.setPanchayatDutyAdju("");
					regForm.setUpkarDutyAdju("");
					regForm.setTotaldutyAdju("");
					regForm.setRegistrationFeeAdju("");

					regForm.setIsFirstPartyAddedFlag(0);

					regForm.setExecutantClaimant(0);
					regForm.setExecutantClaimantTrns(0);

					regForm.setAdjuRemarks("");

					regForm.setExecutionDate("");
					regForm.setRegistrationDate("");
					regForm.setRegistrationNo("");
					regForm.setReceiptAmount(0);
					regForm.setReceiptAmountDisp("");
					regForm.setBankName("");
					regForm.setExecutionDate("");
					regForm.setReceiptAmount(0);
					regForm.setReceiptAmountDisp("");
					regForm.setPropDetls("");
					regForm.setRegistrationDate("");
					regForm.setRegistrationNo("");

					regForm.setDeedNamePreReg("");
					regForm.setDeedTypePreReg("");

					// if(regForm.getIsMultiplePropsFlag()==0){

					regForm.setHidnRegTxnId("");
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

					regForm.setIsMultiplePropsFlag(0);

					regForm.setCancellationLabel("");

					regForm.setAddPropertyOptional("");
					regForm.setHdnAddPropertyOptional("off");
					commonDto.setIndtehsil(new ArrayList());
				}
				session.removeAttribute("commonDto");
				session.removeAttribute("roleId");
				session.removeAttribute("functionId");
				session.removeAttribute("status");
				session.removeAttribute("view");
				session.removeAttribute("regFormProp");

				// efile emp id
				// String regTxnId=(String) session.getAttribute("regTxnId");
				String regTxnId = (String) session.getAttribute("tempefileidbankpage");
				regForm.setHidnRegTxnId(regTxnId);

				int fromAdju = 0;
				if (request.getAttribute("fromAdju") != null) {
					fromAdju = Integer.parseInt(request.getAttribute("fromAdju").toString());
				}
				System.out.println("from Adju flag is: " + fromAdju);
				regForm.setFromAdjudication(fromAdju);
				if (regForm.getFromAdjudication() == 1) {
					// session.setAttribute("fnName","Adjudication");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
					}
				}

			}
		}

		if (request.getParameter("modName") != null) {
			if (request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty") && request.getAttribute("regFormDashboard") == null) {

				if (regForm != null) {

					RegCommonDTO commonDto = new RegCommonDTO();
					commonDto.setInstrument(new ArrayList());
					commonDto.setExemption(new ArrayList());

					regForm.setDeleteOwnerID("");
					regForm.setHdnDeleteOwnerID("");
					regForm.setOwnerGendarVal("");
					regForm.setOwnerModifyFlag(0);

					regForm.setAppOwnerList(new HashMap());
					regForm.setTrnsOwnerList(new HashMap());

					regForm.setTotalConsenterConsid("");
					regForm.setAddressIndOutMp("");
					regForm.setAddressOrgOutMp("");
					regForm.setAddressAuthPerOutMp("");
					regForm.setAddressOwnerOutMp("");
					regForm.setAddressGovtOffclOutMp("");

					regForm.setPoaShareDebenture("");

					regForm.setCancelDeedRadio(0);

					regForm.setAddressIndOutMpTrns("");
					regForm.setAddressOrgOutMpTrns("");
					regForm.setAddressAuthPerOutMpTrns("");
					regForm.setAddressOwnerOutMpTrns("");
					regForm.setAddressGovtOffclOutMpTrns("");
					regForm.setConsenterUploadList(new ArrayList());
					regForm.setRegDTO(new RegCompleteMakerDTO());
					regForm.setUpOrNot("");
					regForm.setConsenterFlag("N");
					regForm.setConsenterWithConsidFlag("N");
					regForm.setConsenterCount(0);
					regForm.setConsenterAddedCount(0);
					regForm.setDtlsMapConsntr(new HashMap());
					regForm.setConsenterId("");
					regForm.setDeleteConsenter("");
					regForm.setHdnDeleteConsenter("");
					regForm.setConsenterWiseDutyList(new ArrayList());
					regForm.setFamilyFlag("N");
					regForm.setIsDashboardFlag(3);
					regForm.setMergeSuccess("N");
					regForm.setTermsCondPartyApp("");
					regForm.setTermsCondPartyTrns("");
					regForm.setTermsCondProp("");
					regForm.setFinalDisclaimerCheck("off");
					regForm.setGovtOfcCheck("");
					regForm.setNameOfOfficial("");
					regForm.setDesgOfOfficial("");
					regForm.setAddressOfOfficial("");
					regForm.setFinalPage("");
					regForm.setTransCheck("");
					regForm.setConsUploadFlag("0");
					regForm.setContriProp("");

					regForm.setDissolutionDate("");
					regForm.setRetirmentDate("");

					regForm.setNameOfOfficialTrns("");
					regForm.setDesgOfOfficialTrns("");
					regForm.setAddressOfOfficialTrns("");

					regForm.setListDto(new ArrayList());
					regForm.setListDtoAdju(new ArrayList());
					regForm.setListDtoP(new ArrayList());

					regForm.setDeedDraftId("");

					regForm.setAddMoreTransParty("Y");

					regForm.setApplicantUserIdCheck(null);
					regForm.setIndCategory("");
					regForm.setIndCategoryTrns("");
					regForm.setOccupationId("");
					regForm.setOccupationIdTrns("");

					regForm.setBankName("");
					regForm.setBranchName("");
					regForm.setBankAddress("");
					regForm.setBankAuthPer("");
					regForm.setBankLoanAmt("0");
					regForm.setBankSancAmt("0");

					regForm.setTrustName("");
					regForm.setTrustDate("");
					regForm.setDeedTypeFlag(0);

					regForm.setAdoptionDeedParty1Added(0);
					regForm.setAdoptionDeedParty2Added(0);
					regForm.setAdoptionDeedParty3Added(0);

					regForm.setRent(0);
					regForm.setAdvance(0);
					regForm.setAdvancePaymntDetails("");
					regForm.setDevlpmtCharge(0);
					regForm.setOtherRecCharge(0);
					regForm.setPremium(0);
					regForm.setTermLease(0);
					regForm.setCallingAction("");

					regForm.setWithPos("");
					regForm.setSecLoanAmt(0);
					regForm.setPoaWithConsid("");
					regForm.setPoaPeriod(0);

					regForm.setPaidLoanAmt(0);

					regForm.setConsiAmountTrns("");
					regForm.setExtraFieldLabel(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE);
					regForm.setContriProp("");

					// regForm.setAdvance(0);
					regForm.setAdvancePaymntDate("");
					regForm.setPossGiven("N");
					regForm.setPossGivenName("");

					regForm.setAgreeMemoInstFlag(0);
					regForm.setClaimantFlag(0);
					regForm.setPoaHolderFlag(0);

					regForm.setIndcountryArb("1");
					regForm.setIndstatenameArb("1");
					regForm.setInddistrictArb("");
					regForm.setFnameArb("");
					regForm.setMnameArb("");
					regForm.setLnameArb("");
					regForm.setGendarArb("");
					regForm.setAgeArb("");
					regForm.setFatherNameArb("");
					regForm.setMotherNameArb("");
					regForm.setSpouseNameArb("");
					regForm.setNationalityArb("");
					regForm.setIndaddressArb("");
					regForm.setIndcountryArb("");
					regForm.setIndstatenameArb("");
					regForm.setInddistrictArb("");
					regForm.setIndphnoArb("");
					regForm.setIndmobnoArb("");
					regForm.setEmailIDArb("");
					regForm.setIndCategoryArb("");
					regForm.setIndDisabilityArb("");
					regForm.setIndDisabilityDescArb("");
					regForm.setListIDArb("");
					regForm.setIdnoArb("");

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

					regForm.setInstrument("");
					regForm.setIndCaste("");
					regForm.setIndReligion("");
					regForm.setIndDisability("N");
					regForm.setIndDisabilityDesc("");
					regForm.setIndDisabilityDescTrns("");
					// regForm.setShareOfProp("");
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

					regForm.setRelationshipTrns(0);
					regForm.setRelationship(0);
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
					regForm.setRelationWithOwner("");

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
					regForm.setOwnerId("");
					regForm.setHdnDeleteTrnsPrtyId("");
					regForm.setHdnOwnerId("");
					// regForm.setHidnRegTxnId(""); // Commented by Gulrej on
					// 9th June, 2017
					regForm.setHidnUserId("");
					regForm.setPropertyDTO(new PropertyValuationDTO());
					regForm.setRegcompletDto(new RegCompletDTO());
					regForm.setMapTransactingParties(new HashMap());
					regForm.setMapTransactingPartiesDisp(new HashMap());
					regForm.setMapTransPartyDbInsertion(new HashMap());
					regForm.setRegInitEstampCode(null);
					regForm.setRegInitPaymntTxnId(null);
					regForm.setRegInitPermTxnId("");
					regForm.setCurrDateTime("");
					regForm.setPropertyId("");
					regForm.setTotalPoaCount(0);
					regForm.setTotalOwnerCount(0);

					regForm.setActionName(" ");
					regForm.setFormName(" ");
					regForm.setPage("success");

					regForm.setListAdoptedPartyNameTrns("");
					regForm.setListAdoptedPartyName("");
					regForm.setAddMoreCounter(0);
					regForm.setTotalShareOfProp(0);
					regForm.setTotalShareOfPropBuyer(0);
					regForm.setTotalShareOfPropSellerSelf(0);
					regForm.setTotalShareOfPropDonor(0);
					regForm.setTotalShareOfPropDonee(0);
					regForm.setTotalShareOfPropOwnerSelf(0);

					// regForm.setHdnDeclareShareCheck("Y");

					regForm.setOwnerNameTrns("");
					regForm.setOwnerOgrNameTrns("");
					regForm.setOwnerGendarTrns("M");
					regForm.setOwnerNationalityTrns("");
					regForm.setOwnerAddressTrns("");
					regForm.setOwnerPhnoTrns("");
					regForm.setOwnerEmailIDTrns("");
					regForm.setOwnerIdnoTrns("");
					regForm.setOwnerAgeTrns("");
					regForm.setOwnerListIDTrns("");
					regForm.setOwnerProofNameTrns("");
					regForm.setIsTransactingPartyAddedFlag(0);
					regForm.setIsDashboardFlag(0);
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

					regForm.setPaymentCompleteFlag(0);
					regForm.setAdjuDutyAddedFlag("N");
					regForm.setIsDutyCalculated(0);
					regForm.setMarketValueShares("");

					regForm.setLabelAmountFlag("");
					regForm.setAdjudicationNumber("");

					regForm.setErrorMsg("");

					regForm.setMapPropertyTransParty(new HashMap());

					regForm.setPostalCountry("1");
					regForm.setPostalState("1");
					regForm.setPostalDistrict("");
					regForm.setPostalAddress("");
					regForm.setAddMoreTransParty("Y");
					// regForm.setDeclareShare("true");
					// regForm.setHdnDeclareShareCheck("Y");
					regForm.setPostalAddress1("Y");
					regForm.setPostalAddress2("N");
					regForm.setHdnPostalAddress1("Y");

					regForm.setIndScheduleArea("Y");
					regForm.setPermissionNo("");
					regForm.setCertificate(null);
					regForm.setDocumentName("");
					regForm.setDocumentSize("");
					regForm.setFilePath("");
					regForm.setDocContents(new byte[0]);
					regForm.setPartyOrProp("");
					regForm.setUploadType("");

					regForm.setU2(null);
					regForm.setU2DocumentName("");
					regForm.setU2DocumentSize("");
					regForm.setU2FilePath("");
					regForm.setU2DocContents(new byte[0]);
					regForm.setU2PartyOrProp("");
					regForm.setU2UploadType("");

					regForm.setU3(null);
					regForm.setU3DocumentName("");
					regForm.setU3DocumentSize("");
					regForm.setU3FilePath("");
					regForm.setU3DocContents(new byte[0]);
					regForm.setU3PartyOrProp("");
					regForm.setU3UploadType("");

					regForm.setU4(null);
					regForm.setU4DocumentName("");
					regForm.setU4DocumentSize("");
					regForm.setU4FilePath("");
					regForm.setU4DocContents(new byte[0]);
					regForm.setU4PartyOrProp("");
					regForm.setU4UploadType("");

					regForm.setU5(null);
					regForm.setU5DocumentName("");
					regForm.setU5DocumentSize("");
					regForm.setU5FilePath("");
					regForm.setU5DocContents(new byte[0]);
					regForm.setU5PartyOrProp("");
					regForm.setU5UploadType("");

					regForm.setU6(null);
					regForm.setU6DocumentName("");
					regForm.setU6DocumentSize("");
					regForm.setU6FilePath("");
					regForm.setU6DocContents(new byte[0]);
					regForm.setU6PartyOrProp("");
					regForm.setU6UploadType("");

					regForm.setU7(null);
					regForm.setU7DocumentName("");
					regForm.setU7DocumentSize("");
					regForm.setU7FilePath("");
					regForm.setU7DocContents(new byte[0]);
					regForm.setU7PartyOrProp("");
					regForm.setU7UploadType("");

					regForm.setU8(null);
					regForm.setU8DocumentName("");
					regForm.setU8DocumentSize("");
					regForm.setU8FilePath("");
					regForm.setU8DocContents(new byte[0]);
					regForm.setU8PartyOrProp("");
					regForm.setU8UploadType("");

					regForm.setU9(null);
					regForm.setU9DocumentName("");
					regForm.setU9DocumentSize("");
					regForm.setU9FilePath("");
					regForm.setU9DocContents(new byte[0]);
					regForm.setU9PartyOrProp("");
					regForm.setU9UploadType("");

					regForm.setU10(null);
					regForm.setU10DocumentName("");
					regForm.setU10DocumentSize("");
					regForm.setU10FilePath("");
					regForm.setU10DocContents(new byte[0]);
					regForm.setU10PartyOrProp("");
					regForm.setU10UploadType("");

					regForm.setU11(null);
					regForm.setU11DocumentName("");
					regForm.setU11DocumentSize("");
					regForm.setU11FilePath("");
					regForm.setU11DocContents(new byte[0]);
					regForm.setU11PartyOrProp("");
					regForm.setU11UploadType("");

					regForm.setIndScheduleAreaTrns("Y");
					regForm.setPermissionNoTrns("");
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

					regForm.setSrOfficeNameTrns("");
					regForm.setPoaRegNoTrns("");
					regForm.setDatePoaRegTrns("");

					regForm.setSrOfficeName("");
					regForm.setPoaRegNo("");
					regForm.setDatePoaReg("");

					// for common deeds
					regForm.setCommonDeed(0);
					regForm.setAddPartyNewRole(0); // variable for radio. value
													// 0 for same role
					regForm.setRoleSameAsPrevious(0); // flag for above radio. 1
														// for previous (same)
														// role
					// regForm.setAddAnotherParty(0);
					regForm.setCommonDeedRoleName("");
					regForm.setAddMoreParticularCounter(0);
					regForm.setMapParticulars(new HashMap());
					regForm.setParticularName("");
					regForm.setParticularDesc("");
					regForm.setDeleteParticularID("");
					regForm.setHdnDeleteParticularID("");

					regForm.setAuthPerAddress("");
					regForm.setAuthPerCountry("1");
					regForm.setAuthPerDistrict("");
					regForm.setAuthPerStatename("1");
					regForm.setAuthPerFatherName("");
					regForm.setAuthPerRelationship(0);
					regForm.setAuthPerGendar("M");

					regForm.setAuthPerAddressTrns("");
					regForm.setAuthPerCountryTrns("1");
					regForm.setAuthPerDistrictTrns("");
					regForm.setAuthPerStatenameTrns("1");
					regForm.setAuthPerFatherNameTrns("");
					regForm.setAuthPerRelationshipTrns(0);
					regForm.setAuthPerGendarTrns("M");

					regForm.setFromAdjudication(0);
					regForm.setInitiateAdjuApp(0);
					regForm.setStampManually("N");
					regForm.setStampduty1("");
					regForm.setNagarPalikaDuty("");
					regForm.setPanchayatDuty("");
					regForm.setUpkarDuty("");
					regForm.setTotalduty("");
					regForm.setRegistrationFee("");
					regForm.setStampduty1Adju("");
					regForm.setNagarPalikaDutyAdju("");
					regForm.setPanchayatDutyAdju("");
					regForm.setUpkarDutyAdju("");
					regForm.setTotaldutyAdju("");
					regForm.setRegistrationFeeAdju("");

					regForm.setIsFirstPartyAddedFlag(0);

					regForm.setExecutantClaimant(0);
					regForm.setExecutantClaimantTrns(0);

					regForm.setAdjuRemarks("");

					regForm.setExecutionDate("");
					regForm.setRegistrationDate("");
					regForm.setRegistrationNo("");
					regForm.setReceiptAmount(0);
					regForm.setReceiptAmountDisp("");
					regForm.setBankName("");
					regForm.setExecutionDate("");
					regForm.setReceiptAmount(0);
					regForm.setReceiptAmountDisp("");
					regForm.setPropDetls("");
					regForm.setRegistrationDate("");
					regForm.setRegistrationNo("");

					regForm.setDeedNamePreReg("");
					regForm.setDeedTypePreReg("");

					// if(regForm.getIsMultiplePropsFlag()==0){

					// regForm.setHidnRegTxnId(""); // Commented by Gulrej on
					// 9th June, 2017
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

					regForm.setIsMultiplePropsFlag(0);

					regForm.setCancellationLabel("");

					regForm.setAddPropertyOptional("");
					regForm.setHdnAddPropertyOptional("off");
					commonDto.setIndtehsil(new ArrayList());

					/*
					 * //added by shruti regForm.setDuty_txn_id(0);
					 */// }
				}
				session.removeAttribute("commonDto");
				session.removeAttribute("roleId");
				session.removeAttribute("functionId");
				session.removeAttribute("status");
				session.removeAttribute("view");
				session.removeAttribute("regFormProp");

				// efile emp id
				String regTxnId = (String) session.getAttribute("regTxnId");

				// Modified by Gulrej on 9th June, 2017
				if (regTxnId != null) {
					regForm.setHidnRegTxnId(regTxnId);
				}

				int fromAdju = 0;
				if (request.getAttribute("fromAdju") != null) {
					fromAdju = Integer.parseInt(request.getAttribute("fromAdju").toString());
				}
				System.out.println("from Adju flag is: " + fromAdju);
				regForm.setFromAdjudication(fromAdju);
				if (regForm.getFromAdjudication() == 1) {
					// session.setAttribute("fnName","Adjudication");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
					}
				}

			}
		}

		RegCommonDTO commonDto;

		if (regForm.getCommonDto() != null) {
			commonDto = regForm.getCommonDto();

		} else {
			commonDto = new RegCommonDTO();
			commonDto.setState(new ArrayList());
			commonDto.setDistrict(new ArrayList());
			commonDto.setIndstate(new ArrayList());
			commonDto.setInddistrict(new ArrayList());
		}

		String userId = (String) session.getAttribute("UserId");
		regForm.setHidnUserId(userId);

		if ((String) session.getAttribute("regTxnId") != null) {
			regForm.setHidnRegTxnId((String) session.getAttribute("regTxnId"));
		}

		// code added by shruti

		if (request.getAttribute("eForm") != null) {

			DutyCalculationForm eForm = (DutyCalculationForm) request.getAttribute("eForm");

			if (eForm.getDutyCalculationDTO().getMainDutyTxnId() != null)
				regForm.setDuty_txn_id(Integer.parseInt(eForm.getDutyCalculationDTO().getMainDutyTxnId()));
			else
				regForm.setDuty_txn_id(0);
			logger.debug("duty txn id----------->" + regForm.getDuty_txn_id());

			if (eForm.getDutyCalculationDTO().getCancellationFlag() != null) {

				if (eForm.getDutyCalculationDTO().getCancellationFlag().equalsIgnoreCase("Y")) {

					commonBo.getCancellationLabel(Integer.toString(regForm.getDuty_txn_id()), regForm, languageLocale);

				} else {
					regForm.setCancellationLabel("");
				}

			} else {
				regForm.setCancellationLabel("");
			}

			// if(regForm.getIsMultiplePropsFlag()==0){

			regForm.setDeedtype1(eForm.getDutyCalculationDTO().getDeedType());
			regForm.setDeedID(eForm.getDutyCalculationDTO().getDeedId());
			regForm.setInstType(eForm.getInstDTO().getInstType());
			regForm.setInstID(eForm.getInstDTO().getInstId());
			logger.debug("inst id----------->" + regForm.getInstID());
			// regForm.setExmpID(eForm.getExempDTO().getHdnExAmount()); //comma
			// separated exemption ids.

			regForm.setExmpID(commonBo.getExempId(regForm.getDuty_txn_id()));
			regForm.setSelectedExemptionList(commonBo.getExemptionList(Integer.toString(regForm.getDuty_txn_id()), languageLocale));
			regForm.setFamilyFlag(commonBo.getFamilyFlag(Integer.toString(regForm.getDuty_txn_id())));

			String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));

			if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

				if (flags[2].trim().equalsIgnoreCase("Y")) {
					regForm.setCommonDeed(1);
				} else {
					regForm.setCommonDeed(0);
				}

				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());

			} else {
				regForm.setCommonDeed(0);
				regForm.setPvReqFlag("");
				regForm.setPropReqFlag("");
			}

			// commonBo.getAllPropertyList(regForm,0,null,languageLocale);

			// following code for property id generation in case of other than
			// exchange deed

			regForm.setFromAdjudication(eForm.getFromAdjudication());
			if (regForm.getFromAdjudication() == 1) {
				// session.setAttribute("fnName","Adjudication");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}

			} else {
				// session.setAttribute("fnName","Initiation");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}

			int deed = 0;
			deed = regForm.getDeedID();
			request.setAttribute("deedId", deed);
			commonDto.setDeedId(deed);
			if (regForm.getIsDashboardFlag() == 0)
				commonDto.setPartyType(commonBo.getPartyType(deed, regForm.getInstID(), languageLocale));
			// commonDto.setAppType(commonBo.getAppType(languageLocale));
			// logger.debug("drop down:--->"+commonDto.getAppType());
			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));// hindi
																		// missing
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			//commonDto.setRelationshipList(commonBo.getRelationshipList(regForm
			// .getGendar()));
			regForm.setCountry("1");
			regForm.setCountryName("INDIA");
			regForm.setStatename("1");
			regForm.setStatenameName("MADHYA PRADESH");
			regForm.setAuthPerCountry("1");
			regForm.setAuthPerCountryName("INDIA");
			regForm.setAuthPerStatename("1");
			regForm.setAuthPerStatenameName("MADHYA PRADESH");
			regForm.setIndcountry("1");
			regForm.setIndcountryName("INDIA");
			regForm.setIndstatename("1");
			regForm.setIndstatenameName("MADHYA PRADESH");
			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");

		}

		if (request.getParameter("regStatus") != null) {
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK2)) {

				String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));

				if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

					if (flags[2].trim().equalsIgnoreCase("Y")) {
						regForm.setCommonDeed(1);
					} else {
						regForm.setCommonDeed(0);
					}

					regForm.setPvReqFlag(flags[1].trim());
					regForm.setPropReqFlag(flags[0].trim());

				} else {
					regForm.setCommonDeed(0);
					regForm.setPvReqFlag("");
					regForm.setPropReqFlag("");
				}
			}
		}

		if (request.getParameter("modName") != null && request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")) {

			String flags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));

			if (flags != null && flags[0] != null && flags[1] != null && flags[2] != null) {

				if (flags[2].trim().equalsIgnoreCase("Y")) {
					regForm.setCommonDeed(1);
				} else {
					regForm.setCommonDeed(0);
				}

				regForm.setPvReqFlag(flags[1].trim());
				regForm.setPropReqFlag(flags[0].trim());

			} else {
				regForm.setCommonDeed(0);
				regForm.setPvReqFlag("");
				regForm.setPropReqFlag("");
			}

		}

		forward = regForm.getPage();

		if (request.getParameter("regStatus") != null) {
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK2)) {

				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				commonDto.setDeedId(regForm.getDeedID());
				if (regForm.getIsDashboardFlag() == 0)
					commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(), regForm.getInstID(), languageLocale));
				// commonDto.setAppType(commonBo.getAppType(languageLocale));
				commonDto.setCountry(commonBo.getCountry(languageLocale));
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setDeedType(commonBo.getDeedType(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				if (regForm.getCommonDeed() == 1) {
					// commonDto.setExecutantClaimant(commonBo.
					// getExecutantClaimant
					// (languageLocale,regForm.getInstID()));
					commonDto.setExecutantClaimant(new ArrayList());
					commonDto.setAppType(commonBo.getAppType(languageLocale, 0, 0));
				}
				regForm.setCountry("1");
				regForm.setCountryName("INDIA");
				regForm.setStatename("1");
				regForm.setStatenameName("MADHYA PRADESH");
				regForm.setAuthPerCountry("1");
				regForm.setAuthPerCountryName("INDIA");
				regForm.setAuthPerStatename("1");
				regForm.setAuthPerStatenameName("MADHYA PRADESH");
				regForm.setIndcountry("1");
				regForm.setIndcountryName("INDIA");
				regForm.setIndstatename("1");
				regForm.setIndstatenameName("MADHYA PRADESH");
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");

			}
		}

		if (request.getParameter("modName") != null && request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")) {

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			commonDto.setDeedId(regForm.getDeedID());
			if (regForm.getIsDashboardFlag() == 0)
				commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(), regForm.getInstID(), languageLocale));
			// commonDto.setAppType(commonBo.getAppType(languageLocale));
			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
			if (regForm.getCommonDeed() == 1) {
				// commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(
				// languageLocale,regForm.getInstID()));
				commonDto.setExecutantClaimant(new ArrayList());
				commonDto.setAppType(commonBo.getAppType(languageLocale, 0, 0));
			}
			regForm.setCountry("1");
			regForm.setCountryName("INDIA");
			regForm.setStatename("1");
			regForm.setStatenameName("MADHYA PRADESH");
			regForm.setAuthPerCountry("1");
			regForm.setAuthPerCountryName("INDIA");
			regForm.setAuthPerStatename("1");
			regForm.setAuthPerStatenameName("MADHYA PRADESH");
			regForm.setIndcountry("1");
			regForm.setIndcountryName("INDIA");
			regForm.setIndstatename("1");
			regForm.setIndstatenameName("MADHYA PRADESH");
			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");

		}

		// following code for getting state and district of applicant
		// for getting organization state list
		if (regForm.getCountry() != null && !regForm.getCountry().equalsIgnoreCase("")) {
			commonDto.setState(commonBo.getState(regForm.getCountry(), languageLocale));
			forward = "success";
		} else {
			commonDto.setState(new ArrayList());
		}
		// for getting organization district list
		if (regForm.getStatename() != null && !regForm.getStatename().equalsIgnoreCase("")) {
			commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(), languageLocale));

			forward = "success";
		} else {
			commonDto.setDistrict(new ArrayList());
		}
		// for getting individual state list
		if (regForm.getIndcountry() != null && !regForm.getIndcountry().equalsIgnoreCase("")) {
			commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(), languageLocale));
			forward = "success";
		} else {
			commonDto.setIndstate(new ArrayList());
		}
		// for getting individual district list
		if (regForm.getIndstatename() != null && !regForm.getIndstatename().equalsIgnoreCase("")) {
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(), languageLocale));
			// changes on 27 june 2016
			// commonDto.setIndtehsil(new ArrayList());
			forward = "success";
		} else {
			commonDto.setInddistrict(new ArrayList());
			commonDto.setIndtehsil(new ArrayList());
		}

		// for tehsil

		String districtId = null;
		String languageLocale1 = (String) session.getAttribute("languageLocale");
		// below code for modify tehsil
		if (request.getParameter("districtIdModify") != null) {

			districtId = request.getParameter("districtIdModify").toString();

			ArrayList tehsilListModify = commonBo.getTehsilListModify(districtId, languageLocale1);

			commonDto.setTehsilTrns(tehsilListModify);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			// regForm.getCommonDto().setTehsilTrns(tehsilListModify);
			System.out.println("display tehsil modify" + tehsilListModify);

			forward = "displayRegDetls";

			return mapping.findForward(forward);

		}
		if (request.getParameter("districtIdModify2") != null) {

			districtId = request.getParameter("districtIdModify2").toString();

			ArrayList tehsilListModify = commonBo.getTehsilListModify(districtId, languageLocale1);

			commonDto.setTehsilTrns(tehsilListModify);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			// regForm.getCommonDto().setTehsilTrns(tehsilListModify);
			System.out.println("display tehsil modify" + tehsilListModify);

			forward = "displayRegDetls";

			return mapping.findForward(forward);

		}
		if (request.getParameter("districtIdModify1") != null) {

			districtId = request.getParameter("districtIdModify1").toString();

			ArrayList tehsilListModify = commonBo.getTehsilListModify1(districtId, languageLocale1);

			commonDto.setTehsilTrns1(tehsilListModify);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			// regForm.getCommonDto().setTehsilTrns(tehsilListModify);
			System.out.println("display tehsil modify" + tehsilListModify);

			forward = "displayRegDetls";

			return mapping.findForward(forward);

		}
		// end of code
		if (request.getParameter("tehsilID") != null) {

			districtId = request.getParameter("tehsilID").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);

			regForm.getCommonDto().setIndtehsil(tehsilList);
			regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (request.getParameter("tehsilID1") != null) {

			districtId = request.getParameter("tehsilID1").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList2(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);

			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setIndtehsil(tehsilList);
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}
		if (request.getParameter("tehsilID01") != null) {

			districtId = request.getParameter("tehsilID01").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList2(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setIndtehsil(tehsilList);
			regForm.getCommonDto().setTehsilId2("-1");
			regForm.getCommonDto().setTehsilId7("-1");
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (request.getParameter("tehsilIDPOA") != null) {

			districtId = request.getParameter("tehsilIDPOA").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);

			regForm.getCommonDto().setIndtehsilpoa(tehsilList);
			System.out.println("display tehsil" + tehsilList);
			regForm.getCommonDto().setTehsilId("-1"); // added by siddhartha

			forward = "success";

		}

		if (request.getParameter("tehsilIDPoa") != null) {

			districtId = request.getParameter("tehsilIDPoa").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList2(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setIndtehsilpoaInd(tehsilList);
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (request.getParameter("tehsilIDOrg") != null) {

			districtId = request.getParameter("tehsilIDOrg").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList1 = commonBo.getTehsilList6(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList1);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setIndtehsilpoaOrg(tehsilList1);
			System.out.println("display tehsil" + tehsilList1);

			forward = "success";

		}

		if (request.getParameter("tehsilIDOrg1") != null) {

			districtId = request.getParameter("tehsilIDOrg1").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setIndtehsilOrg1(tehsilList);
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (request.getParameter("tehsilIDORG") != null) {

			districtId = request.getParameter("tehsilIDORG").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList7(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setTehsilName("");
			regForm.getCommonDto().setTehsilName6("");
			regForm.getCommonDto().setIndtehsilOrg(tehsilList);
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (request.getParameter("tehsilIDORG1") != null) {

			districtId = request.getParameter("tehsilIDORG1").toString();
			System.out.println("district ID" + districtId);
			session.setAttribute("districtId", districtId);
			ArrayList tehsilList = commonBo.getTehsilList2(districtId, languageLocale1);
			System.out.println("tehsil list value" + tehsilList);
			regForm.getCommonDto().setTehsilName2("");
			regForm.getCommonDto().setTehsilId2("-1");
			regForm.getCommonDto().setIndtehsilOrg5(tehsilList);
			System.out.println("display tehsil" + tehsilList);

			forward = "success";

		}

		if (regForm.getDeedID() == RegInitConstant.DEED_SURRENDER_LEASE_NPV) {
			// for getting Arbitrator state list
			if (regForm.getIndcountryArb() != null && !regForm.getIndcountryArb().equalsIgnoreCase("")) {
				commonDto.setIndstate(commonBo.getState(regForm.getIndcountryArb(), languageLocale));
				forward = "success";
			} else {
				commonDto.setIndstate(new ArrayList());
			}
			// for getting Arbitrator district list
			if (regForm.getIndstatenameArb() != null && !regForm.getIndstatenameArb().equalsIgnoreCase("")) {
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatenameArb(), languageLocale));
				commonDto.setIndtehsil(new ArrayList());
				forward = "success";
			} else {
				commonDto.setInddistrict(new ArrayList());
			}
		}

		if (regForm.getIsMultiplePropsFlag() == 1) {

			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());

			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
			if (deedInstArray[2].trim().equals("-")) {
				regForm.setExmpID("");
				regForm.setHdnExAmount("");
			} else {
				regForm.setExmpID(deedInstArray[2].trim());
				regForm.setHdnExAmount(deedInstArray[2].trim());
			}

		}

		forward = "success";

		if (regForm.getPartyType() != null) {
			request.setAttribute("roleId", regForm.getPartyType());
		}
		if (regForm.getPartyTypeTrns() != null) {
			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
		}
		if (regForm.getDeedID() != 0) {
			request.setAttribute("deedId", regForm.getDeedID());
		}
		if (regForm.getInstID() != 0) {
			request.setAttribute("instId", regForm.getInstID());
		}

		String formName = regForm.getFormName();
		String actionName = regForm.getActionName();
		logger.debug("formName:-" + formName);
		logger.debug("actionName:-" + actionName);
		// efile number

		if (RegInitConstant.DUTY_CALCULATE_ACTION_efilenumber.equals(actionName)) {
			System.out.println("inside efile number method");
			Calendar currentDate = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
			String dateNow = formatter.format(currentDate.getTime());

			String regTxnId = null;

		}

		if ("VALIDATE".equalsIgnoreCase(actionName)) {
			commonBo.validateDeedDraftId(regForm, languageLocale);
			regForm.setRegInitEstampCode(null);
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			forward = "reginitConfirm";
		}
		if ("CONSUME".equalsIgnoreCase(actionName)) {

			String deedId = null;
			String filePath = null;
			ArrayList list = commonBo.getDeedDraftId(regForm.getHidnRegTxnId());
			if (list != null && list.size() == 1) {

				ArrayList rowList = (ArrayList) list.get(0);
				if (rowList != null && rowList.size() == 2) {
					deedId = (String) rowList.get(0);
					filePath = (String) rowList.get(1);

				}

			}

			if (deedId != null && filePath != null) {
				logger.debug("deed id already mapped. retrived already mapped deed id.");
				regForm.setDeedStat("5");
				regForm.setDeedDraftId(deedId);
				// set deed draft path and contents in form
				regForm.setDeedPath(filePath);
				regForm.setDeedContents(DMSUtility.getDocumentBytes(filePath));
				regForm.setDeedPageNo(commonBo.getMaxDeedPage(deedId));

			} else {
				logger.debug("consuming deed id.");
				try {

					commonBo.consumeDeedDraftId(regForm, languageLocale, response);

				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
					regForm.setDeedStat("3");

					if ("en".equalsIgnoreCase(languageLocale)) {
						regForm.setErMsg("Could not consume Deed Id.");
					} else {
						regForm.setErMsg("डीड आईडी का उपभोग नहीं किया जा सका");
					}

				}
			}

			regForm.setRegInitEstampCode(null);
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.OWNER_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.MODIFY_OWNER_ACTION.equals(actionName)) {

			}

			if (RegInitConstant.SAVE_OWNER_ACTION.equals(actionName)) {

				boolean boo = commonBo.updateOwnerDetails(regForm);
				if (boo) {
					// regForm.setPartyModifyFlag(0);
					regForm.setOwnerModifyFlag(0);
					commonBo.getOwnerDetails(regForm.getOwnerId(), regForm, languageLocale);
					regForm.setActionName("");
					forward = "displayOwner";
				} else {
					forward = "failure";
				}
				// SAVE OWNER DETAILS

			}
		}

		if (RegInitConstant.CONSENTER_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.BACK_TO_CONSENTER_DISPLAY_ACTION.equals(actionName)) {

				regForm.setActionName("");
				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, languageLocale);
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.MODIFY_CONSENTER_ACTION.equals(actionName)) {

				regForm.setActionName("");
				regForm.setPartyModifyFlag(1);
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				if (regForm.getRegDTO().getConsenterPhotoIdNumber().equalsIgnoreCase("-")) {
					regForm.getRegDTO().setConsenterPhotoIdNumber("");
				}

				forward = "displayConsenterDetls";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.GET_CONSENTER_DISTRICT_MODIFY.equals(actionName)) {

				regForm.setActionName("");
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				forward = "displayConsenterDetls";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.GET_CONSENTER_DISTRICT.equals(actionName)) {

				regForm.setActionName("");
				commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
				forward = "reginitConsenter";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.DEL_CONSENTER_ACTION.equals(actionName)) {

				// delete code
				regForm.setActionName("");
				String[] consenterID = regForm.getHdnDeleteConsenter().split(",");

				boolean consentersDeltd = commonBo.deleteConsenterDetails(consenterID, regForm.getHidnRegTxnId());

				if (consentersDeltd) {
					HashMap consenterMap = regForm.getDtlsMapConsntr();

					for (int i = 0; i < consenterID.length; i++) {

						consenterMap.remove(consenterID[i]);

					}
					regForm.setDtlsMapConsntr(consenterMap);

					commonDto.setDistrict(commonBo.getDistrict(regForm.getRegDTO().getConsenterState(), languageLocale));
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					commonDto.setState(commonBo.getState("1", languageLocale));
					commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
					regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
					forward = "reginitConsenter";
					return mapping.findForward(forward);

				} else {
					logger.debug("unable to delete consenters.");
					forward = "failure";

				}

			}

			if (RegInitConstant.ADD_CONSENTER_ACTION.equals(actionName)) {

				RegCompleteMakerDTO mapDto = new RegCompleteMakerDTO();

				regForm.setActionName("");

				// individual

				mapDto.setConsenterFirstName(regForm.getRegDTO().getConsenterFirstName());

				mapDto.setConsenterAge(regForm.getRegDTO().getConsenterAge());
				mapDto.setConsenterFatherName(regForm.getRegDTO().getConsenterFatherName());

				regForm.setConsenterId(commonBo.getConsenterIdSeq());

				mapDto.setConsenterSno(regForm.getConsenterId());

				map = regForm.getDtlsMapConsntr();

				map.put(regForm.getConsenterId(), mapDto);

				regForm.setDtlsMapConsntr(map);

				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				ArrayList<CommonDTO> dto = regForm.getListDto();
				if (dto != null && dto.size() > 0) {
					for (int i = 0; i < dto.size(); i++) {
						CommonDTO dtos = dto.get(i);
						dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
						String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
						if (checkUpload == null) {
							check = false;
							break;
						} else {
							dtos.setDocumentPath(checkUpload);
							check = true;
						}

					}

					if (check == true) {
						check = commonBo.insertAdditionalUploadsConsenter(regForm);
					}

				} else {
					check = true;
				}

				if (check) {
					check = commonBo.insertConsenterDetails(regForm, null);
					if (check) {
						forward = "reginitConsenter";
						regForm.setListDto(new ArrayList());
						regForm.setRegDTO(new RegCompleteMakerDTO());
						regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
						commonDto.setIdProf(commonBo.getIdProf(languageLocale));
						commonDto.setState(commonBo.getState("1", languageLocale));
						commonDto.setDistrict(new ArrayList());
						commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
						return mapping.findForward(forward);
					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}

			}

			if (RegInitConstant.SAVE_LAST_CONSENTER.equals(actionName)) {

				regForm.setActionName("");

				regForm.setConsenterId(commonBo.getConsenterIdSeq());

				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				ArrayList<CommonDTO> dto = regForm.getListDto();
				if (dto != null && dto.size() > 0) {
					for (int i = 0; i < dto.size(); i++) {
						CommonDTO dtos = dto.get(i);
						dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
						String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
						if (checkUpload == null) {
							check = false;
							break;
						} else {
							dtos.setDocumentPath(checkUpload);
							check = true;
						}

					}

					if (check == true) {
						check = commonBo.insertAdditionalUploadsConsenter(regForm);
					}

				} else {
					check = true;
				}

				if (check) {
					check = commonBo.insertConsenterDetails(regForm, "save");
					if (check) {

						boolean calConsenterDuty = true;

						if (regForm.getConsenterWithConsidFlag().equalsIgnoreCase("N")) {
							DutyCalculationBO dutyBo = new DutyCalculationBO();
							calConsenterDuty = dutyBo.consenterInsertions(regForm.getHidnRegTxnId());
						} else {
							calConsenterDuty = true;
						}
						// cal dc method here
						if (calConsenterDuty) {
							logger.debug("dc successful.");
							// copy new final duties in reg init duty table
							calConsenterDuty = commonBo.copyFinalConsenterDuties(regForm);

							if (calConsenterDuty) {
								logger.debug("save new duty details from dc successful.");
								DutyCalculationForm dutyForm = new DutyCalculationForm();
								dutyForm.setPropImage1DocumentName("");
								dutyForm.setEmptyFile("");
								forward = "reginitConfirm";
								request.setAttribute("deedId", regForm.getDeedID());
								request.setAttribute("instId", regForm.getInstID());
								commonBo.landConfirmationScreen(regForm, languageLocale, request);
								return mapping.findForward(forward);

							} else {
								logger.debug("unable to save new duty details from dc.");
								forward = "failure";

							}

						} else {

							logger.debug("failure in dc method while calculating consenter duties.");
							forward = "failure";

						}

					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}

			}

			if (RegInitConstant.RESET_CONSENTER_ACTION.equals(actionName)) {

				regForm.setActionName("");
				forward = "reginitConsenter";
				regForm.setListDto(new ArrayList());
				regForm.setRegDTO(new RegCompleteMakerDTO());
				if (regForm.getDtlsMapConsntr() != null) {
					regForm.setConsenterAddedCount(regForm.getDtlsMapConsntr().size());
				} else {
					regForm.setConsenterAddedCount(0);
				}
				commonDto.setState(commonBo.getState("1", languageLocale));
				commonDto.setDistrict(new ArrayList());

				return mapping.findForward(forward);

			}

			if (RegInitConstant.SAVE_CONSENTER_ACTION.equals(actionName)) {

				regForm.setActionName("");

				regForm.setConsenterId(regForm.getRegDTO().getConsenterSno());

				String path = "";

				try {
					PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

					path = pr.getValue("upload.location");
				} catch (Exception e) {
					logger.debug("exception in uploadFile : " + e);
					logger.debug(e.getMessage(), e);
				}

				boolean checkAdditionalUploads = true;
				path = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_CONSENTER + regForm.getConsenterId() + "/";
				try {

					// By Mohit
					File f = new File(path);
					FileUtils.cleanDirectory(f);

				} catch (Exception e) {
					request.setAttribute("error", "Unable to clean directory.");
					logger.debug(e.getMessage(), e);

				} finally {
					try {

						checkAdditionalUploads = true;
						commonBo.deleteAllRemovedUploadsConsenter(regForm.getHidnRegTxnId(), regForm.getConsenterId());
					} catch (Exception e) {
						request.setAttribute("error", "Unable to delete records.");
						checkAdditionalUploads = false;
						logger.debug(e.getMessage(), e);
					}
				}

				// CONSENTER INSERTION
				boolean check = false;
				CommonAction obj = new CommonAction();
				if (checkAdditionalUploads) {
					ArrayList<CommonDTO> dto = regForm.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int i = 0; i < dto.size(); i++) {
							CommonDTO dtos = dto.get(i);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
							String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getConsenterId(), "04", dtos.getDocumentName());
							if (checkUpload == null) {
								check = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								check = true;
							}

						}

						if (check == true) {
							check = commonBo.insertAdditionalUploadsConsenter(regForm);
						}

					} else {
						check = true;
					}
				}

				if (check) {
					check = commonBo.updateConsenterDetails(regForm);
					if (check) {
						regForm.setPartyModifyFlag(0);
						commonBo.openConsenterView(request, regForm, languageLocale);
						forward = "displayConsenterDetls";
						return mapping.findForward(forward);
					} else {
						forward = "failure";
					}
				} else {
					forward = "failure";
				}

			}

		}

		if (RegInitConstant.PARTY_PAGE_FORM.equals(formName)) {

			if (RegInitConstant.OWNER_POPUP_MODIFY_CLOSE_ACTION.equals(actionName)) {
				// regForm.setPartyModifyFlag(1);
				String partyId = request.getParameter("key");
				String roleId = request.getParameter("role");

				if (partyId != null && roleId != null) {
					regForm.setPartyTxnId(partyId);
					regForm.setPartyType(roleId);
				}

				HashMap partyDispMap = regForm.getMapTransactingPartiesDisp();
				RegCommonDTO mapDto = (RegCommonDTO) partyDispMap.get(regForm.getPartyTxnId());

				mapDto.setTrnsOwnerList(commonBo.getOwnersHashMap(regForm.getPartyTxnId(), regForm.getHidnRegTxnId()));

				// commonBo.openPartyView(request, regForm, languageLocale);
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
					// commonBo.getPartyDetsForViewConfirmModify(regForm);
					regForm.setTrnsOwnerList(mapDto.getTrnsOwnerList());

				} else {
					regForm.setOwnerModifyFlag(0);
				}
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				request.setAttribute("roleId", regForm.getPartyType());
				forward = "displayRegDetls";
				actionName = "";
				regForm.setActionName("");
				return mapping.findForward(forward);
			}

			if (RegInitConstant.ADD_MORE_APP_OWNER_ACTION.equals(actionName)) {

				RegCommonDTO mapDto = new RegCommonDTO();

				mapDto.setOwnerNameTrns(regForm.getOwnerName());
				if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());

				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
				if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
				if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());

				mapDto.setOwnerAgeTrns(regForm.getOwnerAge());

				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());

				if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}

				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

				regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

				regForm.setOwnerName("");
				regForm.setOwnerOgrName("");
				regForm.setOwnerGendar("M");
				regForm.setOwnerNationality("");
				regForm.setOwnerAddress("");
				regForm.setOwnerPhno("");
				regForm.setOwnerEmailID("");
				regForm.setOwnerAge("");
				regForm.setOwnerIdno("");
				regForm.setOwnerListID("");
				regForm.setOwnerProofName("");
				regForm.setAddressOwnerOutMp("");

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}

			if (RegInitConstant.DELETE_MORE_APP_OWNER_ACTION.equals(actionName)) {

				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");

				HashMap ownerMap = regForm.getAppOwnerList();

				for (int i = 0; i < trnsPrtyID.length; i++) {

					ownerMap.remove(trnsPrtyID[i]);

				}
				regForm.setAppOwnerList(ownerMap);

				forward = "success";

				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;

			}

			if (RegInitConstant.GENDER_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}
			if (RegInitConstant.AUTH_PER_GENDER_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "success";

			}
			if (RegInitConstant.GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";

			}
			if (RegInitConstant.AUTH_PER_GENDER_TRNS_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";

			}
			if (RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}
			if (RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)) {
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "displayRegDetls";
				// return mapping.findForward(forward);
			}

			if (RegInitConstant.CHANGE_PARTY_ACTION.equals(actionName)) {
				String partyType = regForm.getListAdoptedParty();
				logger.debug("partyType:-" + partyType);

				if (regForm.getCommonDeed() == 1) {
					commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(languageLocale, regForm.getInstID(), partyType));

				}

			}
			if (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {
				String partyTypeTrns = regForm.getListAdoptedPartyTrns();
				logger.debug("partyTypeTrns:-" + partyTypeTrns);
				if (regForm.getCommonDeed() == 1) {
					commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(languageLocale, regForm.getInstID(), partyTypeTrns));

				}
				// commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale,
				// regForm.getPoaHolderFlag()));
				// HERE GOVT

				forward = "transactingParty";
				// saveToken(request);
			}

			if (RegInitConstant.CHANGE_CLAIMANT_ACTION.equals(actionName)) {

				// if(regForm.getExecutantClaimant()){

				// String[] arr=regForm.getExecutantClaimant().split("~");

				int id = regForm.getExecutantClaimant();
				// String name=arr[1];

				regForm.setExecutantClaimantName(commonBo.getExecutantClaimantName(Integer.toString(regForm.getExecutantClaimant())));

				if (id == RegInitConstant.CLAIMANT_FLAG_2 || id == RegInitConstant.CLAIMANT_FLAG_4) {

					regForm.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				} else {
					regForm.setPoaHolderFlag(0);
				}

				// }

				forward = "success";

			}
			if (RegInitConstant.CHANGE_CLAIMANT_TRNS_ACTION.equals(actionName)) {

				// if(regForm.getExecutantClaimant()){

				// String[] arr=regForm.getExecutantClaimant().split("~");

				int id = regForm.getExecutantClaimantTrns();
				// String name=arr[1];

				regForm.setExecutantClaimantName(commonBo.getExecutantClaimantName(Integer.toString(regForm.getExecutantClaimantTrns())));

				if (id == RegInitConstant.CLAIMANT_FLAG_2 || id == RegInitConstant.CLAIMANT_FLAG_4) {

					regForm.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
				} else {
					regForm.setPoaHolderFlag(0);
				}

				// }

				forward = "transactingParty";

			}

			if ("CHECK_ACTION".equalsIgnoreCase(actionName)) {

				forward = "success";
			}

			if (RegInitConstant.NEXT_ACTION.equals(actionName)) { // applicant
																	// details
																	// are
																	// geting
																	// saved
																	// here

				// following code for populating drop downs
				//commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
				commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
				commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));

				if (regForm.getCommonDeed() == 1) {
					// commonDto.setExecutantClaimant(commonBo.
					// getExecutantClaimant
					// (languageLocale,regForm.getInstID()));
					commonDto.setExecutantClaimant(new ArrayList());
					commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale, 0, 0));
				}

				// BELOW CODE FOR INSERTING APPLICANT DETAILS IN DATABASE.

				regForm.setHidnUserId(userId);

				if (regForm.getIsMultiplePropsFlag() == 0) {

				}
				int roleId = 0;

				if (regForm.getPartyType() != null && !regForm.getPartyType().equalsIgnoreCase("") && !regForm.getPartyType().equalsIgnoreCase("null")) {
					roleId = Integer.parseInt(regForm.getPartyType());
				}

				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				// String[]
				// claimantArr=commonBo.getClaimantFlag(Integer.toString
				// (roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());

				// regForm.setClaimantFlag(claimantFlag);

				regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;

				regForm.setU2FilePath("");
				regForm.setU3FilePath("");
				regForm.setU10FilePath("");
				regForm.setU6FilePath("");
				regForm.setU11FilePath("");
				regForm.setU9FilePath("");
				regForm.setFilePath("");

				boolean applicantDetailsInserted = false;
				boolean allUploadSuccessful = false;

				CommonAction obj = new CommonAction();
				/*
				 * if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant
				 * .INDIVIDUAL_ID) ||
				 * regForm.getListAdoptedParty().equalsIgnoreCase
				 * (RegInitConstant.ORGANISATION_ID))
				 */
				if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					if (regForm.getPropReqFlag().equalsIgnoreCase("Y")) {

						// filePathPhotoProof=obj.uploadFile(regForm.
						// getHidnRegTxnId(),regForm.getU2DocContents(),
						//regForm.getPartyRoleTypeId(),regForm.getU2PartyOrProp(
						// ),regForm.getU2UploadType());

						if ((regForm.getU2DocumentName() != null && !regForm.getU2DocumentName().equalsIgnoreCase(""))) {
							filePathPhotoProof = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContents(), regForm.getPartyRoleTypeId(), regForm.getU2PartyOrProp(), regForm.getU2UploadType()); // for
																																																				// pan
						} else {
							filePathPhotoProof = "";
						}

						if (filePathPhotoProof != null) {
							regForm.setU2FilePath(filePathPhotoProof);
							// BELOW CODE FOR EXECUTANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

								if (regForm.getU3DocumentName() != null && !regForm.getU3DocumentName().equalsIgnoreCase("")) {
									filePathNotAffExec = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContents(), regForm.getPartyRoleTypeId(), regForm.getU3PartyOrProp(), regForm.getU3UploadType());
								} else {
									filePathNotAffExec = "";
								}

								if (filePathNotAffExec != null) {
									regForm.setU3FilePath(filePathNotAffExec);

									// filePathExecPhoto=obj.uploadFile(regForm.
									// getHidnRegTxnId
									// (),regForm.getU4DocContents(),
									// regForm.getPartyRoleTypeId(),regForm.
									// getU4PartyOrProp
									// (),regForm.getU4UploadType());

									// if(filePathExecPhoto!=null){
									// regForm.setU4FilePath(filePathExecPhoto);
									if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
										if (!regForm.getListID().equalsIgnoreCase("4")) {
											if (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase(""))) {

												filePathPanForm60 = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());
											} else {
												filePathPanForm60 = "";
											}
											if (filePathPanForm60 != null) {
												regForm.setU10FilePath(filePathPanForm60);
												allUploadSuccessful = true;
											} else {
												allUploadSuccessful = false;
											}

										} else {
											allUploadSuccessful = true;
										}

									} else {
										if (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentName() != null && !regForm.getU10DocumentName().equalsIgnoreCase(""))) {

											filePathPanForm60 = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContents(), regForm.getPartyRoleTypeId(), regForm.getU10PartyOrProp(), regForm.getU10UploadType());
										} else {
											filePathPanForm60 = "";
										}
										if (filePathPanForm60 != null) {
											regForm.setU10FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}

									}

									// }else{
									//	
									// }
								} else {
									allUploadSuccessful = false;
								}
							}
							// BELOW CODE FOR EXECUTANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

								filePathAttrnPhoto = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContents(), regForm.getPartyRoleTypeId(), regForm.getU6PartyOrProp(), regForm.getU6UploadType());

								if (filePathAttrnPhoto != null) {
									regForm.setU6FilePath(filePathAttrnPhoto);

									allUploadSuccessful = true;
								} else {
									allUploadSuccessful = false;
								}
								// }

								// }

							}
							// BELOW CODE FOR CLAIMANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

								// filePathClaimPhoto=obj.uploadFile(regForm.
								// getHidnRegTxnId(),regForm.getU8DocContents(),
								// regForm.getPartyRoleTypeId(),regForm.
								// getU8PartyOrProp
								// (),regForm.getU8UploadType());

								// if(filePathClaimPhoto!=null){
								// regForm.setU8FilePath(filePathClaimPhoto);
								if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
									if (!regForm.getListID().equalsIgnoreCase("4")) {
										if (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase(""))) {
											filePathPanForm60 = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());
										} else {
											filePathPanForm60 = "";
										}
										if (filePathPanForm60 != null) {
											regForm.setU11FilePath(filePathPanForm60);
											allUploadSuccessful = true;
										} else {
											allUploadSuccessful = false;
										}

									} else {
										allUploadSuccessful = true;
									}

								} else {

									if (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentName() != null && !regForm.getU11DocumentName().equalsIgnoreCase(""))) {
										filePathPanForm60 = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContents(), regForm.getPartyRoleTypeId(), regForm.getU11PartyOrProp(), regForm.getU11UploadType());
									} else {
										filePathPanForm60 = "";
									}
									if (filePathPanForm60 != null) {
										regForm.setU11FilePath(filePathPanForm60);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}

								}

								// }else{
								// allUploadSuccessful=false;
								// }
							}

							// BELOW CODE FOR CLAIMANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

								filePathAttrnPhoto = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContents(), regForm.getPartyRoleTypeId(), regForm.getU9PartyOrProp(), regForm.getU9UploadType());

								if (filePathAttrnPhoto != null) {
									regForm.setU9FilePath(filePathAttrnPhoto);
									allUploadSuccessful = true;

								} else {
									allUploadSuccessful = false;
								}
							}

							if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) && regForm.getIndCategory().equalsIgnoreCase("1") && regForm.getIndScheduleArea().equalsIgnoreCase("N")) {

								filePath = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContents(), regForm.getPartyRoleTypeId(), regForm.getPartyOrProp(), regForm.getUploadType());
								if (filePath != null) {
									regForm.setFilePath(filePath);
									allUploadSuccessful = true;
								} else {
									allUploadSuccessful = false;
								}
							}
						}

					} else {
						// filePathPhotoProof=obj.uploadFile(regForm.
						// getHidnRegTxnId(),regForm.getU2DocContents(),
						//regForm.getPartyRoleTypeId(),regForm.getU2PartyOrProp(
						// ),regForm.getU2UploadType());

						if ((regForm.getU2DocumentName() != null && !regForm.getU2DocumentName().equalsIgnoreCase(""))) {
							filePathPhotoProof = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContents(), regForm.getPartyRoleTypeId(), regForm.getU2PartyOrProp(), regForm.getU2UploadType()); // for
																																																				// pan
						} else {
							filePathPhotoProof = "";
						}

						if (filePathPhotoProof != null) {
							regForm.setU2FilePath(filePathPhotoProof);

							if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
								if (regForm.getIndCategory().equalsIgnoreCase("1") && regForm.getIndScheduleArea().equalsIgnoreCase("N")) {
									filePath = obj.uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContents(), regForm.getPartyRoleTypeId(), regForm.getPartyOrProp(), regForm.getUploadType());
									if (filePath != null) {
										regForm.setFilePath(filePath);
										allUploadSuccessful = true;
									} else {
										allUploadSuccessful = false;
									}
								} else {
									allUploadSuccessful = true;
								}
							} else {
								allUploadSuccessful = true;
							}
						}

					}

				} else {
					allUploadSuccessful = true;
				}

				boolean check = false;
				if (allUploadSuccessful) {
					// Mohit Here Whuppe!

					ArrayList<CommonDTO> dto = regForm.getListDto();
					if (dto != null && dto.size() > 0) {
						for (int i = 0; i < dto.size(); i++) {
							CommonDTO dtos = dto.get(i);
							dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(i)));
							String checkUpload = obj.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), regForm.getPartyRoleTypeId(), "01", dtos.getDocumentName());
							if (checkUpload == null) {
								check = false;
								break;
							} else {
								dtos.setDocumentPath(checkUpload);
								check = true;
							}

						}

						/*
						 * if(check==true) { check =
						 * commonBo.insertAdditionalUploads(regForm); }
						 */

					} else {
						check = true;
					}

					// if(check)
					// applicantDetailsInserted =
					// commonBo.insertApplicantAndPropertyDetails(regForm);
				}

				if (check) {

					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG) {

						RegCommonDTO mapDto = new RegCommonDTO();

						mapDto.setOwnerNameTrns(regForm.getOwnerName());
						if (regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
							mapDto.setOwnerOgrNameTrns("-");
						else
							mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());

						mapDto.setOwnerGendarValTrns(regForm.getOwnerGendar());
						if (regForm.getOwnerGendar().equalsIgnoreCase("f")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

							} else {
								mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

							} else {
								mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

							}
						}
						mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
						mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
						mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
						if (regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
							mapDto.setOwnerEmailIDTrns("-");
						else
							mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());

						mapDto.setOwnerAgeTrns(regForm.getOwnerAge());

						mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdno());
						mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
						mapDto.setOwnerProofNameTrns(regForm.getOwnerListID().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofName());

						if (regForm.getAddressOwnerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMp())) {
							mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMp());
						} else {
							mapDto.setAddressOwnerOutMpTrns("-");
						}

						mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

						regForm.getAppOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

					}

					// changes by preeti on 13 july 2016 fror tehsil
					String Tehsil = regForm.getCommonDto().getTehsilId();
					// end

					applicantDetailsInserted = commonBo.insertDepositDeedApplicantAndBankDetails(regForm);
					commonDto.setTehsilTrns(new ArrayList());
					commonDto.setTehsilTrns1(new ArrayList());
					commonDto.setIndtehsilpoa(new ArrayList());
					commonDto.setIndtehsilpoaInd(new ArrayList());
					commonDto.setIndtehsilOrg5(new ArrayList());
					commonDto.setIndtehsilOrg(new ArrayList());
					commonDto.setIndtehsil(new ArrayList());
					if (applicantDetailsInserted) {

						if (check) {
							check = commonBo.insertAdditionalUploads(regForm);
						} else {
							applicantDetailsInserted = false;
						}

					}

				}

				if (applicantDetailsInserted) {

					RegCommonDTO mapDto = new RegCommonDTO();

					//

					mapDto.setClaimantFlag(claimantFlag);

					mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty().trim());
					mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
					mapDto.setPurposeTrns(regForm.getPurpose().trim());
					mapDto.setBname("");
					mapDto.setBaddress("");
					mapDto.setApplicantOrTransParty("Applicant");
					mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId().trim());
					mapDto.setUserID(regForm.getHidnUserId().trim());

					if (regForm.getRelationWithOwner().trim() != null) {
						if (regForm.getRelationWithOwner().equalsIgnoreCase("")) {
							mapDto.setRelationWithOwnerTrns("-");
						} else {
							mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner().trim());
						}
					}
					// commented by shreeraj
					/*
					 * if(regForm.getDeedTypeFlag()==0){
					 * if(regForm.getShareOfProp().equalsIgnoreCase("")){
					 * mapDto.setShareOfPropTrns("-");
					 * mapDto.setHdnDeclareShareCheck("N"); } else{
					 * mapDto.setShareOfPropTrns
					 * (regForm.getShareOfProp().trim());
					 * mapDto.setHdnDeclareShareCheck("Y"); } }else{
					 * mapDto.setShareOfPropTrns("-");
					 * mapDto.setHdnDeclareShareCheck("N"); }
					 */

					if (regForm.getCommonDeed() != 1) {
						mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyType().trim(), languageLocale, regForm.getDeedID(), regForm.getInstID()));
					} else {
						mapDto.setRoleName(regForm.getCommonDeedRoleName()); // for
																				// common
																				// deeds
					}

					mapDto.setPartyTypeFlag("A");

					mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimant());

					if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

						// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
						if (regForm.getNameOfOfficial() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficial())) {
							mapDto.setNameOfOfficial(regForm.getNameOfOfficial());
						} else {
							mapDto.setNameOfOfficial("-");
						}
						mapDto.setDesgOfOfficial(regForm.getDesgOfOfficial());
						mapDto.setAddressOfOfficial(regForm.getAddressOfOfficial());
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale));
						mapDto.setOgrNameTrns("-");
						//mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim
						// ());
						mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
						mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);
						if (regForm.getPartyType() != null) {
							mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						} else {
							mapDto.setPartyTypeTrns("");
						}
						if (regForm.getAddressGovtOffclOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMp())) {
							mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMp());
						} else {
							mapDto.setAddressGovtOffclOutMpTrns("-");
						}
					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
						// organization
						mapDto.setOgrNameTrns(regForm.getOgrName().trim());
						mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
						mapDto.setIndAuthPersn(regForm.getAuthPerName().trim());
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale));
						mapDto.setTehsilGov1(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId6(), languageLocale));
						mapDto.setOrgaddressTrns(regForm.getOrgaddress().trim());
						mapDto.setSelectedCountry(regForm.getCountry().trim());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getStatename().trim());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getDistrict().trim());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrict().trim(), languageLocale));
						if (regForm.getMobno().trim().equalsIgnoreCase(""))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getMobno().trim());
						if (regForm.getPhno().trim().equalsIgnoreCase(""))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getPhno().trim());

						mapDto.setFnameTrns("");
						mapDto.setMnameTrns("");
						mapDto.setLnameTrns("");
						mapDto.setGendarTrns("-");
						mapDto.setSelectedGender("");
						mapDto.setAgeTrns("-");
						mapDto.setFatherNameTrns("-");
						mapDto.setMotherNameTrns("");
						mapDto.setSpouseNameTrns("");
						mapDto.setNationalityTrns("");
						mapDto.setPostalCodeTrns("");
						mapDto.setEmailIDTrns("");
						mapDto.setSelectedPhotoId("");
						mapDto.setSelectedPhotoIdName("-");
						mapDto.setIdnoTrns("-");

						if (regForm.getPartyType() != null) {
							mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
						} else {
							mapDto.setPartyTypeTrns("");
						}

						mapDto.setIndCasteTrns("");
						mapDto.setIndDisabilityTrns("");

						mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);

						mapDto.setUserDOBTrns("-");

						mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendar());
						String gendr = "";
						if (regForm.getAuthPerGendar().equalsIgnoreCase("m")) {
							// mapDto.setSelectedGender("Male");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}
							// mapDto.setSelectedGender("Female");
						}
						mapDto.setSelectedGender(gendr);

						mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherName());

						mapDto.setRelationshipTrns(regForm.getAuthPerRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationship()), languageLocale));

						mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddress().trim());
						mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountry());
						mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountry(), languageLocale));
						mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatename());
						mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatename(), languageLocale));
						mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrict());
						mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrict().trim(), languageLocale));

						if (regForm.getAddressOrgOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMp())) {
							mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMp());
						} else {
							mapDto.setAddressOrgOutMpTrns("-");
						}

						if (regForm.getAddressAuthPerOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMp())) {
							mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMp());
						} else {
							mapDto.setAddressAuthPerOutMpTrns("-");
						}

					} else if (regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
						// individual
						mapDto.setFnameTrns(regForm.getFname());
						if (regForm.getMname().equalsIgnoreCase(""))
							mapDto.setMnameTrns("-");
						else
							mapDto.setMnameTrns(regForm.getMname());
						mapDto.setLnameTrns(regForm.getLname());
						mapDto.setGendarTrns(regForm.getGendar());
						String gendr = "";
						if (regForm.getGendar().equalsIgnoreCase("m")) {
							// mapDto.setSelectedGender("Male");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.MALE_ENGLISH;
							} else {
								gendr = RegInitConstant.MALE_HINDI;
							}
						} else {
							// mapDto.setSelectedGender("Female");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								gendr = RegInitConstant.FEMALE_ENGLISH;
							} else {
								gendr = RegInitConstant.FEMALE_HINDI;
							}
						}
						mapDto.setSelectedGender(gendr);
						if (regForm.getAge().equalsIgnoreCase(""))
							mapDto.setAgeTrns("-");
						else
							mapDto.setAgeTrns(regForm.getAge());
						mapDto.setFatherNameTrns(regForm.getFatherName());
						if (regForm.getMotherName().equalsIgnoreCase(""))
							mapDto.setMotherNameTrns("-");
						else
							mapDto.setMotherNameTrns(regForm.getMotherName());
						if (regForm.getSpouseName().equalsIgnoreCase(""))
							mapDto.setSpouseNameTrns("-");
						else
							mapDto.setSpouseNameTrns(regForm.getSpouseName());
						mapDto.setNationalityTrns(regForm.getNationality());

						if (regForm.getPostalCode().equalsIgnoreCase(""))
							mapDto.setPostalCodeTrns("-");
						else
							mapDto.setPostalCodeTrns(regForm.getPostalCode());

						if (regForm.getIndphno().equalsIgnoreCase(""))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getIndphno());

						if (regForm.getIndmobno().equalsIgnoreCase(""))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getIndmobno());

						if (regForm.getEmailID().equalsIgnoreCase(""))
							mapDto.setEmailIDTrns("-");
						else
							mapDto.setEmailIDTrns(regForm.getEmailID());
						mapDto.setSelectedPhotoId(regForm.getListID());

						mapDto.setSelectedPhotoIdName(regForm.getListIDName());
						if (regForm.getIdno().equalsIgnoreCase(""))
							mapDto.setIdnoTrns("-");
						else
							mapDto.setIdnoTrns(regForm.getIdno());

						mapDto.setOgrNameTrns("-");
						mapDto.setAuthPerNameTrns("-");
						mapDto.setIndAuthPersn(regForm.getFname() + " " + regForm.getLname());
						mapDto.setOrgaddressTrns(regForm.getIndaddress());
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale));
						mapDto.setSelectedCountry(regForm.getIndcountry());
						mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
						mapDto.setSelectedState(regForm.getIndstatename());
						mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
						mapDto.setSelectedDistrict(regForm.getInddistrict());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(), languageLocale));
						mapDto.setSelectedCategoryName(regForm.getSelectedCategoryName());
						mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationId(), languageLocale));
						mapDto.setIndCategoryTrns(regForm.getIndCategory());
						mapDto.setPartyTypeTrns(regForm.getPartyType());
						mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleArea());

						if (regForm.getIndScheduleArea() != null) {
							if (regForm.getIndScheduleArea().equalsIgnoreCase("Y")) {
								// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
								}
							} else {
								// mapDto.setIndScheduleAreaTrnsDisplay("No");
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
								} else {
									mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
								}
								mapDto.setPermissionNoTrns(regForm.getPermissionNo());
								mapDto.setDocumentNameTrns(regForm.getDocumentName());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSize());
								mapDto.setFilePathTrns(regForm.getFilePath());
								mapDto.setDocContentsTrns(regForm.getDocContents());

							}
						}

						if (regForm.getIndDisability().equalsIgnoreCase("")) {
							mapDto.setIndDisabilityTrns("-");
						} else if (regForm.getIndDisability().equalsIgnoreCase("Y")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
							}
							if (regForm.getIndDisabilityDesc().equalsIgnoreCase("")) {
								mapDto.setIndDisabilityDescTrns("-");
							} else {
								mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDesc());
							}
						} else if (regForm.getIndDisability().equalsIgnoreCase("N")) {
							mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
							// mapDto.setIndDisabilityTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
							}

						}

						mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
						//mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate
						// (regForm.getUserDay(), regForm.getUserMonth(),
						// regForm.getUserYear()));

						if (regForm.getIndMinority().equalsIgnoreCase("")) {
							mapDto.setIndMinorityTrns("-");
						} else if (regForm.getIndMinority().equalsIgnoreCase("Y")) {
							// mapDto.setIndMinorityTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
							}

						} else if (regForm.getIndMinority().equalsIgnoreCase("N")) {
							// mapDto.setIndMinorityTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
							}

						}

						if (regForm.getIndPovertyLine().equalsIgnoreCase("")) {
							mapDto.setIndPovertyLineTrns("-");
						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("Y")) {
							// mapDto.setIndPovertyLineTrns("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
							}

						} else if (regForm.getIndPovertyLine().equalsIgnoreCase("N")) {
							// mapDto.setIndPovertyLineTrns("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
							}

						}

						mapDto.setRelationshipTrns(regForm.getRelationship());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationship()), languageLocale));

						if (regForm.getAddressIndOutMp() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMp())) {
							mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMp());
						} else {
							mapDto.setAddressIndOutMpTrns("-");
						}
					}

					// below code for uploads other than collector's permission
					// no.

					if (!regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						mapDto.setU2DocumentNameTrns(regForm.getU2DocumentName());
						mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSize());
						mapDto.setU2FilePathTrns(regForm.getU2FilePath());
						mapDto.setU2DocContentsTrns(regForm.getU2DocContents());

						if (regForm.getDeedTypeFlag() == 0) {

							// BELOW CODE FOR EXECUTANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
								mapDto.setU3DocumentNameTrns(regForm.getU3DocumentName());
								mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSize());
								mapDto.setU3FilePathTrns(regForm.getU3FilePath());
								mapDto.setU3DocContentsTrns(regForm.getU3DocContents());

								/*
								 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentName
								 * ());mapDto.setU4DocumentSizeTrns(regForm.
								 * getU4DocumentSize());
								 * mapDto.setU4FilePathTrns
								 * (regForm.getU4FilePath());
								 * mapDto.setU4DocContentsTrns
								 * (regForm.getU4DocContents());
								 */

								mapDto.setU10DocumentNameTrns(regForm.getU10DocumentName());
								mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSize());
								mapDto.setU10FilePathTrns(regForm.getU10FilePath());
								mapDto.setU10DocContentsTrns(regForm.getU10DocContents());
							}
							// BELOW CODE FOR EXECUTANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {
								/*
								 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName
								 * ());mapDto.setU5DocumentSizeTrns(regForm.
								 * getU5DocumentSize());
								 * mapDto.setU5FilePathTrns
								 * (regForm.getU5FilePath());
								 * mapDto.setU5DocContentsTrns
								 * (regForm.getU5DocContents());
								 */

								mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
								mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSize());
								mapDto.setU6FilePathTrns(regForm.getU6FilePath());
								mapDto.setU6DocContentsTrns(regForm.getU6DocContents());

								/*
								 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName
								 * ());mapDto.setU7DocumentSizeTrns(regForm.
								 * getU7DocumentSize());
								 * mapDto.setU7FilePathTrns
								 * (regForm.getU7FilePath());
								 * mapDto.setU7DocContentsTrns
								 * (regForm.getU7DocContents());
								 */

								mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
								if (regForm.getPoaRegNo() != null && !regForm.getPoaRegNo().equalsIgnoreCase("")) {
									mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
								} else {
									mapDto.setPoaRegNoTrns("-");
								}
								if (regForm.getDatePoaReg() != null && !regForm.getDatePoaReg().equalsIgnoreCase("")) {
									mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));
								} else {
									mapDto.setDatePoaRegTrns("-");
								}
							}

							// BELOW CODE FOR CLAIMANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
								/*
								 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentName
								 * ());mapDto.setU8DocumentSizeTrns(regForm.
								 * getU8DocumentSize());
								 * mapDto.setU8FilePathTrns
								 * (regForm.getU8FilePath());
								 * mapDto.setU8DocContentsTrns
								 * (regForm.getU8DocContents());
								 */

								mapDto.setU11DocumentNameTrns(regForm.getU11DocumentName());
								mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSize());
								mapDto.setU11FilePathTrns(regForm.getU11FilePath());
								mapDto.setU11DocContentsTrns(regForm.getU11DocContents());
							}

							// BELOW CODE FOR CLAIMANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

								mapDto.setU9DocumentNameTrns(regForm.getU9DocumentName());
								mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSize());
								mapDto.setU9FilePathTrns(regForm.getU9FilePath());
								mapDto.setU9DocContentsTrns(regForm.getU9DocContents());

							}

						}
					}

					mapDto.setListDto(regForm.getListDto());
					regForm.setListDto(new ArrayList<CommonDTO>());

					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || (regForm.getCommonDeed() == 1 && (regForm.getExecutantClaimant() == RegInitConstant.CLAIMANT_FLAG_2 || regForm.getExecutantClaimant() == RegInitConstant.CLAIMANT_FLAG_4))) {

						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						mapDto.setTrnsOwnerList(regForm.getAppOwnerList());

						regForm.setDeleteOwnerID("");
						regForm.setHdnDeleteOwnerID("");
					} else {
						mapDto.setPoaHolderFlag(0);
					}

					map = regForm.getMapTransactingParties();// jo div mai ae
					System.out.print("EEEE  " + regForm.getMapTransactingParties().get(1));
					int count = regForm.getMapKeyCount();
					if (count == 0)
						count = 1;
					// else
					// count++;

					if (map.containsKey(Integer.toString(count))) {
						// keyCount=keyCount+1;
						// key=key+keyCount;
						count++;
						map.put(Integer.toString(count), mapDto);

					} else
						map.put(Integer.toString(count), mapDto);

					regForm.setMapKeyCount(count);

					// regForm.setAddMoreCounter(count);

					regForm.setAddMoreCounter(map.size());

					// key="key";

					regForm.setMapBuildingDetails(map);

					{

						// forward="transactingParty";
						regForm.setActionName(RegInitConstant.NO_ACTION);
						actionName = RegInitConstant.NO_ACTION;

						/*
						 * if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE
						 * ||regForm.getDeedID()==RegInitConstant.
						 * DEED_RECONV_MORTGAGE_NPV ||
						 * (regForm.getDeedID()==RegInitConstant.DEED_TRUST &&
						 * regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P
						 * ) ||(regForm.getDeedID()==RegInitConstant.
						 * DEED_PARTNERSHIP_NPV &&
						 * (regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_1 ||
						 * regForm.getInstID()==RegInitConstant
						 * .INST_PARTNERSHIP_NPV_2 ))){
						 */
						if (regForm.getAddMoreTransParty().equalsIgnoreCase("Y")) {
							forward = "transactingParty";
						} else { // if user skips to add transacting party
							String instrumentFlags[] = commonBo.getInstrumentFlag(String.valueOf(regForm.getInstID()));
							String propRequired = instrumentFlags[0];
							String commonFlow = instrumentFlags[2];

							if (propRequired.equalsIgnoreCase("N")) { // if
																		// instrument
																		// does
																		// not
																		// require
																		// property

								logger.debug("INSTRUMENT NOT REQUIREING PROPERTY");
								if (commonFlow.equalsIgnoreCase("Y")) {
									logger.debug("INSTRUMENT common flow");
									forward = "reginitParticular";
								} else {
									logger.debug("INSTRUMENT NOT REQUIREING PROPERTY and no common flow");
									DutyCalculationForm dutyForm = new DutyCalculationForm();
									dutyForm.setPropImage1DocumentName("");
									dutyForm.setEmptyFile("");
									forward = "reginitConfirm";
									request.setAttribute("deedId", regForm.getDeedID());
									request.setAttribute("instId", regForm.getInstID());
									commonBo.landConfirmationScreen(regForm, languageLocale, request);
									return mapping.findForward(forward);
								}

							} else {
								logger.debug("INSTRUMENT REQUIREING PROPERTY");
								request.setAttribute("fromAdju", regForm.getFromAdjudication());
								forward = "reginitProperty";
								regForm.setActionName(RegInitConstant.NO_ACTION);
								actionName = RegInitConstant.NO_ACTION;
								request.setAttribute("propAction", "propAction");
								request.setAttribute("regInitForm", regForm);
							}

						}

						if (regForm.getCommonDeed() == 1) {
							if (regForm.getAddMoreTransParty().equalsIgnoreCase("Y")) {

								if (regForm.getAddPartyNewRole() == 1) {
									regForm.setCommonDeedRoleName("");
									regForm.setRoleSameAsPrevious(0);
								} else {
									regForm.setRoleSameAsPrevious(1);
								}

							}

							regForm.setAddPartyNewRole(0);
							regForm.setAddMoreTransParty("N");
						}
					}

				} else {
					forward = "failure";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					request.setAttribute("roleId", regForm.getPartyType());
					regForm.setHidnRegTxnId(null);
					return mapping.findForward(forward);
				}
				// actionName=RegInitConstant.NO_ACTION;
				regForm.setActionName(RegInitConstant.NO_ACTION);
				// regForm.setDeedId(request.getAttribute("deedId").toString());

				// resetToken(request);

				// }
				regForm.setCountryTrns("1");
				regForm.setCountryNameTrns("INDIA");
				regForm.setStatenameTrns("1");
				regForm.setStatenameNameTrns("MADHYA PRADESH");
				regForm.setIndcountryTrns("1");
				regForm.setIndcountryNameTrns("INDIA");
				regForm.setIndstatenameTrns("1");
				regForm.setIndstatenameNameTrns("MADHYA PRADESH");
				if (regForm.getFromAdjudication() == 1) {
					// session.setAttribute("fnName","Adjudication");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
					} else {
						session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
					}

				}
				// request.setAttribute("fromAdju",
				// regForm.getFromAdjudication());
				/*
				 * session.setAttribute("fromAdju",
				 * regForm.getFromAdjudication());
				 */
				// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.
				// getAttribute("fromAdju"));
				if (regForm.getDeedID() != 0) {
					request.setAttribute("deedId", regForm.getDeedID());
				} else {
					request.setAttribute("deedId", 0);
				}
				if (regForm.getInstID() != 0) {
					request.setAttribute("instId", regForm.getInstID());
				} else {
					request.setAttribute("instId", 0);
				}
				if (regForm.getPartyType() != null && !regForm.getPartyType().equalsIgnoreCase("")) {
					request.setAttribute("roleId", regForm.getPartyType());
				} else {
					request.setAttribute("roleId", 0);
				}
				if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")) {
					request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
				} else {
					request.setAttribute("roleIdTrns", 0);
				}

				regForm.setClaimantFlag(0);
				regForm.setPoaHolderFlag(0);
				if (!actionName.equalsIgnoreCase(RegInitConstant.NEXT_TO_CONFIRM_ACTION)) {

					return mapping.findForward(forward);
				}

			}

			// below code for getting state and district for transacting party
			// for getting organization state list
			// if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE)
			// {
			if (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.NO_ACTION.equals(actionName)) {
				if (regForm.getCountryTrns() != null && !regForm.getCountryTrns().equalsIgnoreCase("")) {
					commonDto.setStateTrns(commonBo.getState(regForm.getCountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setStateTrns(new ArrayList());
				}
				// for getting organization district list
				if (regForm.getStatenameTrns() != null && !regForm.getStatenameTrns().equalsIgnoreCase("")) {
					commonDto.setDistrictTrns(commonBo.getDistrict(regForm.getStatenameTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setDistrictTrns(new ArrayList());
				}
				// for getting individual state list
				if (regForm.getIndcountryTrns() != null && !regForm.getIndcountryTrns().equalsIgnoreCase("")) {
					commonDto.setIndstateTrns(commonBo.getState(regForm.getIndcountryTrns(), languageLocale));
					forward = "transactingParty";
				} else {
					commonDto.setIndstateTrns(new ArrayList());
				}
				// for getting individual district list
				if (regForm.getIndstatenameTrns() != null && !regForm.getIndstatenameTrns().equalsIgnoreCase("")) {
					commonDto.setInddistrictTrns(commonBo.getDistrict(regForm.getIndstatenameTrns(), languageLocale));

					forward = "transactingParty";
				} else {
					commonDto.setInddistrictTrns(new ArrayList());
				}
				// end of code for populating drop downs
			}
			// }

			if (RegInitConstant.ADD_MORE_TRNS_OWNER_ACTION.equals(actionName)) {

				RegCommonDTO mapDto = new RegCommonDTO();

				mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
				if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerOgrNameTrns("-");
				else
					mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

				mapDto.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
				if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

					}
				}
				mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
				mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
				mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
				if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
					mapDto.setOwnerEmailIDTrns("-");
				else
					mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

				mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

				mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
				mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
				mapDto.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

				if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
					mapDto.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
				} else {
					mapDto.setAddressOwnerOutMpTrns("-");
				}

				mapDto.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

				regForm.getTrnsOwnerList().put(mapDto.getOwnerTxnId(), mapDto);

				regForm.setOwnerNameTrns("");
				regForm.setOwnerOgrNameTrns("");
				regForm.setOwnerGendarTrns("M");
				regForm.setOwnerNationalityTrns("");
				regForm.setOwnerAddressTrns("");
				regForm.setOwnerPhnoTrns("");
				regForm.setOwnerEmailIDTrns("");
				regForm.setOwnerAgeTrns("");
				regForm.setOwnerIdnoTrns("");
				regForm.setOwnerListIDTrns("");
				regForm.setOwnerProofNameTrns("");
				regForm.setAddressOwnerOutMpTrns("");

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "transactingParty";

			}

			if (RegInitConstant.DELETE_MORE_TRNS_OWNER_ACTION.equals(actionName)) {

				String[] trnsPrtyID = regForm.getHdnDeleteOwnerID().split(",");

				HashMap ownerMap = regForm.getTrnsOwnerList();

				for (int i = 0; i < trnsPrtyID.length; i++) {

					ownerMap.remove(trnsPrtyID[i]);

				}
				regForm.setTrnsOwnerList(ownerMap);

				forward = "transactingParty";

				regForm.setActionName(RegInitConstant.NO_ACTION);
				actionName = RegInitConstant.NO_ACTION;

			}

			// NEXT TO PROPERTY DETAILS PAGE THROUGH TRANSACTING PARTIES PAGE
			if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)) {

				logger.debug("add property : " + regForm.getHdnAddPropertyOptional());

				regForm.setActionName("");
				// below code is for the insertion of last transacting party in
				// map

				// String partytypetrn==null?:regForm.getPartyTypeTrns();

				if (regForm.getPartyTypeTrns() == null && regForm.getListAdoptedPartyTrns() == null) {
					regForm.setPartyTypeTrns("");
					regForm.setListAdoptedPartyTrns("");
					DutyCalculationForm dutyForm = new DutyCalculationForm();
					dutyForm.setPropImage1DocumentName("");
					dutyForm.setEmptyFile("");
					forward = "reginitConfirm";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					commonBo.landConfirmationScreen(regForm, languageLocale, request);
					return mapping.findForward(forward);

				} else {

					RegCommonDTO mapDto = new RegCommonDTO();

					// following code for insertion of owner details into map

					int roleId = 0;

					if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")) {
						roleId = Integer.parseInt(regForm.getPartyTypeTrns());
					}

					String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
					int claimantFlag = Integer.parseInt(claimantArr[0].trim());
					regForm.setClaimantFlag(claimantFlag);

					if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || regForm.getCommonDeed() == 1 && (regForm.getExecutantClaimantTrns() == RegInitConstant.CLAIMANT_FLAG_2 || regForm.getExecutantClaimantTrns() == RegInitConstant.CLAIMANT_FLAG_4)) {
						mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);

						RegCommonDTO mapDto1 = new RegCommonDTO();

						mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
						if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
							mapDto1.setOwnerOgrNameTrns("-");
						else
							mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

						mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
						if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

							} else {
								mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

							}
						} else {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

							} else {
								mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

							}
						}
						mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
						mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
						mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
						if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
							mapDto1.setOwnerEmailIDTrns("-");
						else
							mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

						mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

						mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
						mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
						mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

						if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
							mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
						} else {
							mapDto1.setAddressOwnerOutMpTrns("-");
						}

						mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

						regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);

						mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
					} else {
						mapDto.setPoaHolderFlag(0);
					}
					mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
					mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
					mapDto.setPurposeTrns("");
					mapDto.setBname(""); // might be changed
					mapDto.setBaddress(""); // might be changed
					mapDto.setApplicantOrTransParty("Transacting");
					mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
					regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());

					mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
					mapDto.setUserID(regForm.getHidnUserId());

					if (regForm.getRelationWithOwnerTrns() != null) {
						if (regForm.getRelationWithOwnerTrns().equalsIgnoreCase("")) {
							mapDto.setRelationWithOwnerTrns("-");
						} else {
							mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
						}
					}

					if (regForm.getCommonDeed() != 1) // will be true for deeds
														// other than universal
														// deeds
					{
					} else {
						mapDto.setRoleName(regForm.getCommonDeedRoleName());
					}

					mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimantTrns());

					if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

						// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
						if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
							mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
						} else {
							mapDto.setNameOfOfficial("-");
						}
						mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
						mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
						mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale)); // Added
																															// by
																															// Gulrej
																															// on
																															// 26
																															// th
																															// May
																															// ,
																															// 2017
						mapDto.setOgrNameTrns("-");
						//mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim
						// ());
						mapDto.setIndAuthPersn(mapDto.getNameOfOfficial() + " " + mapDto.getDesgOfOfficial());
						mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);

						if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
							mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
						} else {
							mapDto.setAddressGovtOffclOutMpTrns("-");
						}

					} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
						// organization
						mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
						mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
						mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
						mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
						mapDto.setSelectedCountry(regForm.getCountryTrns());
						mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
						mapDto.setSelectedState(regForm.getStatenameTrns());
						mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
						mapDto.setSelectedDistrict(regForm.getDistrictTrns());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale)); // Added
																															// by
																															// Gulrej
																															// on
																															// 26
																															// th
																															// May
																															// ,
																															// 2017
						if (regForm.getMobnoTrns().equalsIgnoreCase(""))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getMobnoTrns());
						if (regForm.getPhnoTrns().equalsIgnoreCase(""))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getPhnoTrns());
						//mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns()
						// );
						//mapDto.setMarketValueTrns(regForm.getMarketValueTrns()
						// );

						mapDto.setFnameTrns("");
						mapDto.setMnameTrns("");
						mapDto.setLnameTrns("");
						// mapDto.setGendarTrns("-");
						// mapDto.setSelectedGender("");
						mapDto.setAgeTrns("");
						// mapDto.setFatherNameTrns("-");
						mapDto.setMotherNameTrns("");
						mapDto.setSpouseNameTrns("");
						mapDto.setNationalityTrns("");
						mapDto.setPostalCodeTrns("");
						mapDto.setEmailIDTrns("");
						mapDto.setSelectedPhotoId("");
						mapDto.setSelectedPhotoIdName("");
						mapDto.setIdnoTrns("-");
						mapDto.setIndReligionTrns("");
						mapDto.setIndCasteTrns("");
						mapDto.setIndDisabilityTrns("");

						mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);

						mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
						if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m"))
							mapDto.setSelectedGender("Male");
						else
							mapDto.setSelectedGender("Female");
						mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());

						mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));

						mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
						mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
						mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
						mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
						mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
						mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
						mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));

						if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
							mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
						} else {
							mapDto.setAddressOrgOutMpTrns("-");
						}

						if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
							mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
						} else {
							mapDto.setAddressAuthPerOutMpTrns("-");
						}

					} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
						// individual
						mapDto.setFnameTrns(regForm.getFnameTrns());
						if (regForm.getMnameTrns().equalsIgnoreCase(""))
							mapDto.setMnameTrns("-");
						else
							mapDto.setMnameTrns(regForm.getMnameTrns());
						mapDto.setLnameTrns(regForm.getLnameTrns());
						mapDto.setGendarTrns(regForm.getGendarTrns());
						if (regForm.getGendarTrns().equalsIgnoreCase("m"))
							mapDto.setSelectedGender("Male");
						else
							mapDto.setSelectedGender("Female");
						if (regForm.getAgeTrns().equalsIgnoreCase(""))
							mapDto.setAgeTrns("-");
						else
							mapDto.setAgeTrns(regForm.getAgeTrns());
						mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
						if (regForm.getMotherNameTrns().equalsIgnoreCase(""))
							mapDto.setMotherNameTrns("-");
						else
							mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
						if (regForm.getSpouseNameTrns().equalsIgnoreCase(""))
							mapDto.setSpouseNameTrns("-");
						else
							mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
						mapDto.setNationalityTrns(regForm.getNationalityTrns());

						if (regForm.getPostalCodeTrns().equalsIgnoreCase(""))
							mapDto.setPostalCodeTrns("-");
						else
							mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
						if (regForm.getIndphnoTrns().equalsIgnoreCase(""))
							mapDto.setPhnoTrns("-");
						else
							mapDto.setPhnoTrns(regForm.getIndphnoTrns());
						if (regForm.getIndmobnoTrns().equalsIgnoreCase(""))
							mapDto.setMobnoTrns("-");
						else
							mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
						if (regForm.getEmailIDTrns().equalsIgnoreCase(""))
							mapDto.setEmailIDTrns("-");
						else
							mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
						mapDto.setSelectedPhotoId(regForm.getListIDTrns());
						mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
						mapDto.setIdnoTrns(regForm.getIdnoTrns());

						mapDto.setOgrNameTrns("-");
						mapDto.setAuthPerNameTrns("-");
						mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
						mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
						mapDto.setSelectedCountry(regForm.getIndcountryTrns());
						mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
						mapDto.setSelectedState(regForm.getIndstatenameTrns());
						mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
						mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
						mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
						mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId(), languageLocale)); // Added
																															// by
																															// Gulrej
																															// on
																															// 26
																															// th
																															// May
																															// ,
																															// 2017

						mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
						mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
						mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());

						mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());

						if (regForm.getIndScheduleAreaTrns() != null) {
							if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
								mapDto.setIndScheduleAreaTrnsDisplay("Yes");
							} else {
								mapDto.setIndScheduleAreaTrnsDisplay("No");
								mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
								mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
								mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
								mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
								mapDto.setFilePathTrns(regForm.getFilePathTrns());
								mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
								mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());
							}
						}

						if (regForm.getIndDisabilityTrns().equalsIgnoreCase("")) {
							mapDto.setIndDisabilityTrns("-");
						} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
							mapDto.setIndDisabilityTrns("Yes");
							if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("")) {
								mapDto.setIndDisabilityDescTrns("-");
							} else {
								mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
							}
						} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
							mapDto.setIndDisabilityTrns("No");

						}

						mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

						//mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate
						// (regForm.getUserDayTrns(),
						// regForm.getUserMonthTrns(),
						// regForm.getUserYearTrns()));

						if (regForm.getIndMinorityTrns().equalsIgnoreCase("")) {
							mapDto.setIndMinorityTrns("-");
						} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
							mapDto.setIndMinorityTrns("Yes");

						} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
							mapDto.setIndMinorityTrns("No");

						}

						// mapDto.setIndMinorityTrns("");
						if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("")) {
							mapDto.setIndPovertyLineTrns("-");
						} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
							mapDto.setIndPovertyLineTrns("Yes");

						} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
							mapDto.setIndPovertyLineTrns("No");

						}

						mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
						mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));

						if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
							mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
						} else {
							mapDto.setAddressIndOutMpTrns("-");
						}
					}

					// below code for uploads other than collector's permission
					// no.

					// mohit

					if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
						mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
						mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
						mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
						mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
						mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
						mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());

						if (regForm.getDeedTypeFlag() == 0) {
							// BELOW CODE FOR EXECUTANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
								mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
								mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
								mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
								mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
								mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
								mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());

								/*
								 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns
								 * ());mapDto.setU4DocumentSizeTrns(regForm.
								 * getU4DocumentSizeTrns());
								 * mapDto.setU4FilePathTrns
								 * (regForm.getU4FilePathTrns());
								 * mapDto.setU4DocContentsTrns
								 * (regForm.getU4DocContentsTrns());
								 * mapDto.setU4PartyOrPropTrns
								 * (regForm.getU4PartyOrPropTrns());
								 * mapDto.setU4UploadTypeTrns
								 * (regForm.getU4UploadTypeTrns());
								 */

								mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
								mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
								mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
								mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
								mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
								mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
							}
							// BELOW CODE FOR EXECUTANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {
								/*
								 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns
								 * ());mapDto.setU5DocumentSizeTrns(regForm.
								 * getU5DocumentSizeTrns());
								 * mapDto.setU5FilePathTrns
								 * (regForm.getU5FilePathTrns());
								 * mapDto.setU5DocContentsTrns
								 * (regForm.getU5DocContentsTrns());
								 * mapDto.setU5PartyOrPropTrns
								 * (regForm.getU5PartyOrPropTrns());
								 * mapDto.setU5UploadTypeTrns
								 * (regForm.getU5UploadTypeTrns());
								 */

								mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
								mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
								mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
								mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
								mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
								mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());

								/*
								 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns
								 * ());mapDto.setU7DocumentSizeTrns(regForm.
								 * getU7DocumentSizeTrns());
								 * mapDto.setU7FilePathTrns
								 * (regForm.getU7FilePathTrns());
								 * mapDto.setU7DocContentsTrns
								 * (regForm.getU7DocContentsTrns());
								 * mapDto.setU7PartyOrPropTrns
								 * (regForm.getU7PartyOrPropTrns());
								 * mapDto.setU7UploadTypeTrns
								 * (regForm.getU7UploadTypeTrns());
								 */

								mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
								if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
									mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
								} else {
									mapDto.setPoaRegNoTrns("-");
								}
								if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
									mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
								} else {
									mapDto.setDatePoaRegTrns("-");
								}
							}

							// BELOW CODE FOR CLAIMANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
								/*
								 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns
								 * ());mapDto.setU8DocumentSizeTrns(regForm.
								 * getU8DocumentSizeTrns());
								 * mapDto.setU8FilePathTrns
								 * (regForm.getU8FilePathTrns());
								 * mapDto.setU8DocContentsTrns
								 * (regForm.getU8DocContentsTrns());
								 * mapDto.setU8PartyOrPropTrns
								 * (regForm.getU8PartyOrPropTrns());
								 * mapDto.setU8UploadTypeTrns
								 * (regForm.getU8UploadTypeTrns());
								 */

								mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
								mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
								mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
								mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
								mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
								mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
							}

							// BELOW CODE FOR CLAIMANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

								mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
								mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
								mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
								mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
								mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
								mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());

							}
						}
					}

					mapDto.setListDto(regForm.getListDto());
					regForm.setListDto(new ArrayList<CommonDTO>());

					map = regForm.getMapTransactingParties();

					int count = regForm.getMapKeyCount();
					if (count == 0)
						count = 1;

					if (map.containsKey(Integer.toString(count))) {

						count++;
						map.put(Integer.toString(count), mapDto);

					} else
						map.put(Integer.toString(count), mapDto);

					//regForm.getMapTransPartyDbInsertion().put(Integer.toString
					// (count), mapDto);

					regForm.setMapKeyCount(count);

					// regForm.setAddMoreCounter(count);
					regForm.setAddMoreCounter(map.size());
					// key="key";

					regForm.setMapTransactingParties(map);

					// end of insertion of last transacting party in map

					// below for check of party added

					/*
					 * roleId=Integer.parseInt( dto.getRoleId());
					 * 
					 * 
					 * partyType1Or2=commonBo.getPartyType1Or2(regForm.getDeedID(
					 * ), regForm.getInstID(), roleId);
					 * 
					 * if(saveMappingFlag==1){
					 * 
					 * if(partyType1Or2==1){
					 * if(dto.getPartyCheck().equalsIgnoreCase("Y"))
					 * party1Added=1; }else{
					 * if(dto.getPartyCheck().equalsIgnoreCase("Y"))
					 * party2Added=1; }
					 * 
					 * }
					 */

					if (regForm.getCommonDeed() != 1) {
						Collection mapCol = regForm.getMapTransactingParties().values();
						// System.out.print("cgsgsfhgsfgsfgha  "+regForm.
						// getMapTransactingParties().get(1));

						Object[] l2 = mapCol.toArray();
						RegCommonDTO mapDto2 = new RegCommonDTO();
						int roleId1 = 0;
						int partyType1Or2 = 0;
						int party1Added = 0;
						int party2Added = 0;
						int party3Added = 0;

						for (int i = 0; i < l2.length; i++) {

							mapDto2 = (RegCommonDTO) l2[i];
							/*
							 * if(mapDto2.getPartyTypeTrns()!=null &&
							 * !mapDto2.getPartyTypeTrns
							 * ().equalsIgnoreCase("")){
							 * roleId1=Integer.parseInt
							 * (mapDto2.getPartyTypeTrns());
							 * 
							 * }
							 */
							if (mapDto2.getPartyTypeTrns() != null && !mapDto2.getPartyTypeTrns().equalsIgnoreCase("")) {
								roleId1 = Integer.parseInt(mapDto2.getPartyTypeTrns());

								partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId1);
								if (partyType1Or2 == 1) {
									party1Added = 1;
								} else if (partyType1Or2 == 2) {
									party2Added = 1;
								} else {
									party3Added = 1;
								}
							}
						}
						if (regForm.getDeedID() == RegInitConstant.DEED_ADOPTION) {
							if (party1Added == 0 || party2Added == 0 || party3Added == 0) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
								} else {
									request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
								}

								map.remove(Integer.toString(count));
								regForm.setMapKeyCount(--count);
								regForm.setAddMoreCounter(map.size());
								regForm.setMapTransactingParties(map);
								actionName = RegInitConstant.NO_ACTION;
								regForm.setActionName(RegInitConstant.NO_ACTION);
								forward = "transactingParty";
								return mapping.findForward(forward);
							}
						} else if (regForm.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE || regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {
							if (party1Added == 0) {
								if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
									request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
								} else {
									request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
								}
								map.remove(Integer.toString(count));
								regForm.setMapKeyCount(--count);
								regForm.setAddMoreCounter(map.size());
								regForm.setMapTransactingParties(map);
								actionName = RegInitConstant.NO_ACTION;
								regForm.setActionName(RegInitConstant.NO_ACTION);
								forward = "transactingParty";
								return mapping.findForward(forward);
							}
						} else if (party1Added == 0 || party2Added == 0) {
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_EN);
							} else {
								request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_3_HI);
							}

							map.remove(Integer.toString(count));
							regForm.setMapKeyCount(--count);
							regForm.setAddMoreCounter(map.size());
							regForm.setMapTransactingParties(map);
							actionName = RegInitConstant.NO_ACTION;
							regForm.setActionName(RegInitConstant.NO_ACTION);
							forward = "transactingParty";
							return mapping.findForward(forward);
						}

					}

					regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);

					// below code is for insertion of transacting parties in
					// database.
					Collection mapCollection = regForm.getMapTransPartyDbInsertion().values();
					Object[] l1 = mapCollection.toArray();
					RegCommonDTO mapDto1 = new RegCommonDTO();

					boolean allUploadSuccessful = false;
					boolean transPartyDetailsInserted = false;

					String filePath;
					String filePathPhotoProof;

					// below loop for creating folder structure of uploads

					/*
					 * if(!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant
					 * .GOVT_OFFCL_ID)) {
					 */
					for (int i = 0; i < l1.length; i++) {
						allUploadSuccessful = false;
						mapDto1 = (RegCommonDTO) l1[i];

						mapDto1.setU2FilePathTrns("");
						mapDto1.setU3FilePathTrns("");
						mapDto1.setU10FilePathTrns("");
						mapDto1.setU6FilePathTrns("");
						mapDto1.setU11FilePathTrns("");
						mapDto1.setU9FilePathTrns("");
						mapDto1.setFilePathTrns("");

						filePath = "";
						filePathPhotoProof = "";
						String filePathNotAffExec;
						String filePathExecPhoto;
						String filePathNotAffAttrn;
						String filePathAttrnPhoto;
						String filePathClaimPhoto;
						String filePathPanForm60;

						int dtoRoleId = 0;
						if (mapDto1.getPartyTypeTrns() != null && !mapDto1.getPartyTypeTrns().equalsIgnoreCase("") && !mapDto1.getPartyTypeTrns().equalsIgnoreCase("null")) {
							dtoRoleId = Integer.parseInt(mapDto1.getPartyTypeTrns());
						}
						String[] claimantArr1 = commonBo.getClaimantFlag(Integer.toString(dtoRoleId));
						int claimantFlag1 = Integer.parseInt(claimantArr1[0].trim());

						boolean additionalUpload = false;

						ArrayList<CommonDTO> dto = mapDto1.getListDto();

						if (dto != null && dto.size() > 0) {
							for (int j = 0; j < dto.size(); j++) {
								CommonDTO dtos = dto.get(j);
								dtos.setDocumentName(commonBo.getNewFileName(dtos.getDocumentName(), Integer.toString(j)));
								String checkUpload = action.uploadFile(regForm.getHidnRegTxnId(), dtos.getDocContents(), mapDto1.getPartyRoleTypeId(), "01", dtos.getDocumentName());
								if (checkUpload == null) {
									additionalUpload = false;
									break;
								} else {
									dtos.setDocumentPath(checkUpload);
									additionalUpload = true;
								}

							}

						} else {
							additionalUpload = true;
						}
						// mohit additional upload
						// mohit
						if (!mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
							if (additionalUpload) {
								if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) || mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {

									if (regForm.getPropReqFlag().equalsIgnoreCase("Y")) {

										// filePathPhotoProof=action.uploadFile(
										// regForm.getHidnRegTxnId(),mapDto1.
										// getU2DocContentsTrns(),
										// mapDto1.getPartyRoleTypeId(),mapDto1.
										// getU2PartyOrPropTrns
										// (),mapDto1.getU2UploadTypeTrns());

										if ((mapDto1.getU2DocumentNameTrns() != null && !mapDto1.getU2DocumentNameTrns().equalsIgnoreCase(""))) {
											filePathPhotoProof = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU2DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU2PartyOrPropTrns(), mapDto1.getU2UploadTypeTrns());
										} else {
											filePathPhotoProof = "";
										}

										if (filePathPhotoProof != null) {
											mapDto1.setU2FilePathTrns(filePathPhotoProof);
											// BELOW CODE FOR EXECUTANT
											if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_1) {
												// mohit
												if (mapDto1.getU3DocumentNameTrns() != null && !mapDto1.getU3DocumentNameTrns().equalsIgnoreCase("")) {
													filePathNotAffExec = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU3DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU3PartyOrPropTrns(), mapDto1.getU3UploadTypeTrns());
												} else {
													filePathNotAffExec = "";
												}

												if (filePathNotAffExec != null) {
													mapDto1.setU3FilePathTrns(filePathNotAffExec);

													// filePathExecPhoto=action.
													// uploadFile
													// (regForm.getHidnRegTxnId
													// (),mapDto1.
													// getU4DocContentsTrns(),
													//mapDto1.getPartyRoleTypeId
													// (),mapDto1.
													// getU4PartyOrPropTrns
													// (),mapDto1
													// .getU4UploadTypeTrns());

													//if(filePathExecPhoto!=null
													// && !filePathExecPhoto.
													// equalsIgnoreCase("")){
													//mapDto1.setU4FilePathTrns(
													// filePathExecPhoto);
													if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
														if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4")) {
															if (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase(""))) {

																filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());

															} else {
																filePathPanForm60 = "";
															}
															if (filePathPanForm60 != null) {
																mapDto1.setU10FilePathTrns(filePathPanForm60);
																allUploadSuccessful = true;
															} else {
																allUploadSuccessful = false;
																break;
															}

														} else {
															allUploadSuccessful = true;
														}

													} else {

														if (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU10DocumentNameTrns() != null && !mapDto1.getU10DocumentNameTrns().equalsIgnoreCase(""))) {

															filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU10DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU10PartyOrPropTrns(), mapDto1.getU10UploadTypeTrns());

														} else {
															filePathPanForm60 = "";
														}
														if (filePathPanForm60 != null) {
															mapDto1.setU10FilePathTrns(filePathPanForm60);
															allUploadSuccessful = true;
														} else {
															allUploadSuccessful = false;
															break;
														}

													}
													// }else{
													//allUploadSuccessful=false;
													// break;
													// }
												} else {
													allUploadSuccessful = false;
													break;
												}
											}
											// BELOW CODE FOR EXECUTANT POA
											// HOLDER
											if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_2) {

												// filePathExecPhoto=action.
												// uploadFile
												// (regForm.getHidnRegTxnId
												// (),mapDto1
												// .getU7DocContentsTrns(),
												// mapDto1.getPartyRoleTypeId(),
												// mapDto1
												// .getU7PartyOrPropTrns()
												// ,mapDto1
												// .getU7UploadTypeTrns());

												// if(filePathExecPhoto!=null &&
												// !filePathExecPhoto.
												// equalsIgnoreCase(""))
												// {
												// mapDto1.setU7FilePathTrns(
												// filePathExecPhoto);
												/*
												 * filePathNotAffAttrn=action.uploadFile
												 * (
												 * regForm.getHidnRegTxnId(),mapDto1
												 * .getU5DocContentsTrns(),
												 * mapDto1
												 * .getPartyRoleTypeId(),mapDto1
												 * .
												 * getU5PartyOrPropTrns(),mapDto1
												 * .getU5UploadTypeTrns());
												 * 
												 * if(filePathNotAffAttrn!=null
												 * &&!filePathNotAffAttrn.
												 * equalsIgnoreCase("")) {
												 * mapDto1.setU5FilePathTrns(
												 * filePathNotAffAttrn);
												 */

												filePathAttrnPhoto = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU6DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU6PartyOrPropTrns(), mapDto1.getU6UploadTypeTrns());

												if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
													mapDto1.setU6FilePathTrns(filePathAttrnPhoto);

													allUploadSuccessful = true;
												} else {
													allUploadSuccessful = false;
													break;
												}
												// }

												// }

											}
											// BELOW CODE FOR CLAIMANT
											if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_3) {

												// filePathClaimPhoto=action.
												// uploadFile
												// (regForm.getHidnRegTxnId
												// (),mapDto1
												// .getU8DocContentsTrns(),
												// mapDto1.getPartyRoleTypeId(),
												// mapDto1
												// .getU8PartyOrPropTrns()
												// ,mapDto1
												// .getU8UploadTypeTrns());

												// if(filePathClaimPhoto!=null
												// && !filePathClaimPhoto.
												// equalsIgnoreCase("")){
												// mapDto1.setU8FilePathTrns(
												// filePathClaimPhoto);

												if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
													if (!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4")) {

														if (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase(""))) {
															filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());

														} else {
															filePathPanForm60 = "";
														}
														if (filePathPanForm60 != null) {
															mapDto1.setU11FilePathTrns(filePathPanForm60);
															allUploadSuccessful = true;
														} else {
															allUploadSuccessful = false;
															break;
														}

													} else {
														allUploadSuccessful = true;
													}
												} else {

													if (regForm.getTransCheck().equalsIgnoreCase("Y") || (mapDto1.getU11DocumentNameTrns() != null && !mapDto1.getU11DocumentNameTrns().equalsIgnoreCase(""))) {
														filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU11DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU11PartyOrPropTrns(), mapDto1.getU11UploadTypeTrns());

													} else {
														filePathPanForm60 = "";
													}
													if (filePathPanForm60 != null) {
														mapDto1.setU11FilePathTrns(filePathPanForm60);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}

												}
												// }else{
												// allUploadSuccessful=false;
												// break;
												// }
											}

											// BELOW CODE FOR CLAIMANT POA
											// HOLDER
											if (claimantFlag1 == RegInitConstant.CLAIMANT_FLAG_4) {

												filePathAttrnPhoto = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU9DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU9PartyOrPropTrns(), mapDto1.getU9UploadTypeTrns());

												if (filePathAttrnPhoto != null && !filePathAttrnPhoto.equalsIgnoreCase("")) {
													mapDto1.setU9FilePathTrns(filePathAttrnPhoto);
													allUploadSuccessful = true;

												} else {
													allUploadSuccessful = false;
													break;
												}
											}

											if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
												if (mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
													filePath = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getDocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getPartyOrPropTrns(), mapDto1.getUploadTypeTrns());
													if (filePath != null && !filePath.equalsIgnoreCase("")) {
														mapDto1.setFilePathTrns(filePath);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}
												} else {
													allUploadSuccessful = true;
												}
											} else {
												allUploadSuccessful = true;
											}
										}

									} else {
										// filePathPhotoProof=action.uploadFile(
										// regForm.getHidnRegTxnId(),mapDto1.
										// getU2DocContentsTrns(),
										// mapDto1.getPartyRoleTypeId(),mapDto1.
										// getU2PartyOrPropTrns
										// (),mapDto1.getU2UploadTypeTrns());

										if ((mapDto1.getU2DocumentNameTrns() != null && !mapDto1.getU2DocumentNameTrns().equalsIgnoreCase(""))) {
											filePathPhotoProof = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getU2DocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getU2PartyOrPropTrns(), mapDto1.getU2UploadTypeTrns());
										} else {
											filePathPhotoProof = "";
										}

										if (filePathPhotoProof != null) {
											mapDto1.setU2FilePathTrns(filePathPhotoProof);

											if (mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
												if (mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
													filePath = action.uploadFile(regForm.getHidnRegTxnId(), mapDto1.getDocContentsTrns(), mapDto1.getPartyRoleTypeId(), mapDto1.getPartyOrPropTrns(), mapDto1.getUploadTypeTrns());
													if (filePath != null && !filePath.equalsIgnoreCase("")) {
														mapDto1.setFilePathTrns(filePath);
														allUploadSuccessful = true;
													} else {
														allUploadSuccessful = false;
														break;
													}
												} else {
													allUploadSuccessful = true;
												}
											} else {
												allUploadSuccessful = true;
											}
										}

									}

								} else {
									allUploadSuccessful = true;
								}
							}
						} else {
							allUploadSuccessful = true;
						}
					}
					/*
					 * }else{ allUploadSuccessful=true; }
					 */

					// below loop for inserting data into database
					if (allUploadSuccessful) {
						for (int i = 0; i < l1.length; i++) {
							mapDto1 = (RegCommonDTO) l1[i];

							mapDto1.setPropertyId(regForm.getPropertyId());

							boolean insertAdditionalUploads = false;
							// mohit database insertion

							if (mapDto1.getListDto() != null && mapDto1.getListDto().size() > 0) {
								insertAdditionalUploads = commonBo.insertAdditionalUploads(mapDto1, regForm);
							} else {
								insertAdditionalUploads = true;
							}

							// mohit database
							if (insertAdditionalUploads) {
								transPartyDetailsInserted = commonBo.insertTransPartyDetails(mapDto1, regForm, new ArrayList(), regForm.getIsMultiplePropsFlag(), null);

								if (!transPartyDetailsInserted) {
									forward = "transactingParty";
									regForm.setHidnRegTxnId(null);
									actionName = RegInitConstant.NO_ACTION;
									regForm.setActionName(RegInitConstant.NO_ACTION);
									break;
								}
							}
						}
						// ADDED BY SHREERAJ
						// String
						// instrumentFlags[]=commonBo.getInstrumentFlag(String
						// .valueOf(regForm.getInstID()));
						// String propRequired=instrumentFlags[0];
						// String propValRequired=instrumentFlags[1];
						// String commonFlag=instrumentFlags[2];
						if (transPartyDetailsInserted) {

							regForm.setClaimantFlag(0);
							regForm.setPoaHolderFlag(0);

							// else{

							// redirection on the basis of flags retrieved from
							// instrument_master
							// if(regForm.getCommonDeed()==1){

							/*
							 * if(regForm.getAddMoreTransParty().equalsIgnoreCase
							 * ("Y")){
							 * logger.debug("MORE TRANSACTING PARTY ADDED "
							 * +regForm.getAddMoreTransParty());
							 * forward="transactingParty";
							 * actionName=RegInitConstant.NO_ACTION;
							 * regForm.setActionName(RegInitConstant.NO_ACTION);
							 * }else{
							 */

							/*
							 * if(propRequired.equalsIgnoreCase("N")){
							 * logger.debug
							 * ("INSTRUMENT NOT REQUIREING PROPERTY "
							 * +regForm.getAddMoreTransParty());
							 * forward="reginitParticular";
							 * //actionName=RegInitConstant.NO_ACTION; return
							 * mapping.findForward(forward); }
							 */
							// else if(regForm.getDeedTypeFlag()==0){
							// else
							if (regForm.getInstID() == RegInitConstant.INST_ATTCH_ORDER || regForm.getInstID() == RegInitConstant.INST_WRITTEN_DEMAND || regForm.getInstID() == RegInitConstant.INST_OREDER_RELEASE || regForm.getInstID() == RegInitConstant.INST_MEMO) {
								regForm.setPropReqFlag("Y");
							}

							if (regForm.getPropReqFlag().equalsIgnoreCase("Y")) {

								request.setAttribute("fromAdju", regForm.getFromAdjudication());

								if ((regForm.getInstID() == RegInitConstant.INST_PARTNERSHIP_NPV_2 || (regForm.getDeedID() == RegInitConstant.DEED_POA && regForm.getInstID() != RegInitConstant.INST_SHARE_DEBENTURE && regForm.getInstID() != RegInitConstant.INST_AUTHENTICATE_POA)) && regForm.getHdnAddPropertyOptional().equalsIgnoreCase("OFF")) {

									commonBo.insertTxnDetailsFinalAction(RegInitConstant.STATUS_SHARES_SAVED, regForm.getHidnRegTxnId());
									DutyCalculationForm dutyForm = new DutyCalculationForm();
									dutyForm.setPropImage1DocumentName("");
									dutyForm.setEmptyFile("");
									forward = "reginitConfirm";
									request.setAttribute("deedId", regForm.getDeedID());
									request.setAttribute("instId", regForm.getInstID());
									commonBo.landConfirmationScreen(regForm, languageLocale, request);
									return mapping.findForward(forward);

								} else {
									forward = "reginitProperty";
									session.setAttribute("regTxnId", regForm.getHidnRegTxnId()); // Added
																									// by
																									// Gulrej
																									// on
																									// 9
																									// th
																									// June
																									// ,
																									// 2017
									return mapping.findForward(forward);
								}

							} else if (regForm.getCommonDeed() == 1) {

								forward = "reginitParticular";
								return mapping.findForward(forward);

							} else {
								DutyCalculationForm dutyForm = new DutyCalculationForm();
								dutyForm.setPropImage1DocumentName("");
								dutyForm.setEmptyFile("");
								// forward="reginitConfirm"; // Commented By
								// Gulrej on 6th June, 2017
								forward = "reginitProperty"; // Added By Gulrej
																// on 6th June,
																// 2017
								request.setAttribute("deedId", regForm.getDeedID());
								request.setAttribute("instId", regForm.getInstID());
								session.setAttribute("regTxnId", regForm.getHidnRegTxnId()); // Added
																								// by
																								// Gulrej
																								// on
																								// 9
																								// th
																								// June
																								// ,
																								// 2017
								commonBo.landConfirmationScreen(regForm, languageLocale, request);
								return mapping.findForward(forward);

							}

						} else {
							forward = "transactingParty";
							regForm.setHidnRegTxnId(null);
							actionName = RegInitConstant.NO_ACTION;
							regForm.setActionName(RegInitConstant.NO_ACTION);

						}
					} else {
						forward = "transactingParty";
						regForm.setHidnRegTxnId(null);
						actionName = RegInitConstant.NO_ACTION;
						regForm.setActionName(RegInitConstant.NO_ACTION);
					}

					regForm.setMapTransPartyDbInsertion(new HashMap());
					regForm.setActionName(RegInitConstant.NO_ACTION);
					request.setAttribute("propAction", "propAction");
					request.setAttribute("regInitForm", regForm);
					regForm.setAddMoreTransParty("");
				}
			}

		}
		if (RegInitConstant.PARTY_POPUP_CLOSE_CONFIRM_ACTION.equals(actionName)) {
			commonBo.landConfirmationScreen(regForm, languageLocale, request);
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.REG_INIT_CONFIRM_PAGE.equals(formName)) {

			/*
			 * if(RegInitConstant.PARTY_POPUP_CLOSE_CONFIRM_ACTION.equals(actionName
			 * )){ commonBo.landConfirmationScreen(regForm, languageLocale,
			 * request); regForm.setActionName(""); forward="reginitConfirm"; }
			 */

			if (RegInitConstant.MODIFY_ACTION.equals(actionName)) {

				forward = "success";
			}

			if (RegInitConstant.BACK_TO_EXTRA_DISPLAY_ACTION.equals(actionName)) {

				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setCallingAction("nonPropRegInit.do?TRFS=NGI");
				regForm.setExtraDetlsModifyFlag(0);
				forward = "displayExtraDetls";

			}

			if (RegInitConstant.NEXT_TO_EFILE_PAYMENT_ACTION.equals(actionName)) {

				regForm.setPurposeAddedFlag("y");
				regForm.setHidnUserId(session.getAttribute("UserId").toString());
				/*
				 * session.setAttribute("parentModName",
				 * "Registration Process");
				 * session.setAttribute("parentFunName",
				 * "Registration Initiation");
				 * session.setAttribute("parentFunId", "FUN_023");
				 * session.setAttribute("parentAmount", regForm.getTotalduty());
				 */
				// following added on 13feb for new payment modality.
				if (Double.parseDouble(regForm.getTotalBalanceAmount()) > 0) {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_023");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmount());
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					//regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					// request.setAttribute("parentKey",
					// regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./nonPropRegInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					// end of addition on 13feb for new payment modality.

					// code for checking user type of logged in user:
					// below for new payment requirements. completed on 4 sept
					// 2013

					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();

					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {

						// String[] arr=commonBo.getDistIdNameRU(userId);

						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {

						String[] arr = commonBo.getDistIdNameSP(userId);

						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {

						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();

						if (arr != null && arr.length == 3) {

							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {

							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}

					}

					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);

					String paymentType = "1";

					regForm.setPaymentType(paymentType);

					// end of new payment requirements added on 4th sept 2013

					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.

					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					//logger.debug("----------------->payment flag:-"+paymentArr
					// [0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {

							if (paymentArr[0].equalsIgnoreCase("I")) {

								// following code for updating purpose
								if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
									boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
									if (updatePurpose) {
										logger.debug("purpose updated");
										forward = "reginitPayment";
									} else {
										logger.debug("purpose not updated");
										forward = "failure";
									}
								}

								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm
								// .getTotalduty
								// (),regForm.getHidnUserId(),regForm
								// .getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";

							}

						} else if (paymentArr[0].equalsIgnoreCase("p")) {

							if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
								boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
								if (updatePurpose) {
									logger.debug("purpose updated");
									// forward="reginitPayment";
								} else {
									logger.debug("purpose not updated");
									// forward="failure";
								}
							}

							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.
							// getTotalduty
							// (),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {

						if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
							boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
							if (updatePurpose) {
								logger.debug("purpose updated");
								// forward="reginitPayment";
							} else {
								logger.debug("purpose not updated");
								// forward="failure";
							}
						}

						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty
						// (),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
				} else {

					actionName = RegInitConstant.FINAL_ACTION;

				}
				// forward="reginitPayment";
				// return mapping.findForward(forward);
			}

			if (RegInitConstant.NEXT_TO_ADJU_PAYMENT_ACTION.equals(actionName)) {

				boolean insertStatusDuty = false;

				if (regForm.getAdjuDutyAddedFlag() != null && !("").equalsIgnoreCase(regForm.getAdjuDutyAddedFlag()) && ("Y").equalsIgnoreCase(regForm.getAdjuDutyAddedFlag())) {
					insertStatusDuty = true;
				} else {

					if (regForm.getListDtoAdju() != null && regForm.getListDtoAdju().size() > 0) {

						logger.debug("adjudication additional uploads found---> ");

						boolean checkAdditionalUploads = false;

						for (int i = 0; i < regForm.getListDtoAdju().size(); i++) {
							CommonDTO dto = regForm.getListDtoAdju().get(i);
							dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
							String filepath = action.uploadFile(regForm.getHidnRegTxnId(), dto.getDocContents(), null, null, dto.getDocumentName());
							if (filepath != null) {
								checkAdditionalUploads = true;
								dto.setDocumentPath(filepath);
							} else {
								checkAdditionalUploads = false;
								break;
							}
						}
						if (checkAdditionalUploads) {

							checkAdditionalUploads = commonBo.insertAdditionalUploadsAdju(regForm);

							if (checkAdditionalUploads) {

								insertStatusDuty = commonBo.insertAdjuDuties(regForm);

								if (!insertStatusDuty) {

									logger.debug("adjudication duty insertion failure---> ");

									forward = "failure";

								}

							} else {

								logger.debug("adjudication additional upload table insertion failure---> ");

								forward = "failure";

							}

						} else {

							logger.debug("adjudication additional upload file creation failure---> ");
							forward = "failure";
						}

					} else {

						logger.debug("adjudication additional uploads not found---> ");
						insertStatusDuty = commonBo.insertAdjuDuties(regForm);

					}

				}

				logger.debug("adjudication duties insertion status---> " + insertStatusDuty);

				if (insertStatusDuty) {

					regForm.setActionName("");

					regForm.setHidnUserId(session.getAttribute("UserId").toString());
					/*
					 * session.setAttribute("parentModName",
					 * "Registration Process");
					 * session.setAttribute("parentFunName",
					 * "Registration Initiation");
					 * session.setAttribute("parentFunId", "FUN_023");
					 * session.setAttribute("parentAmount",
					 * regForm.getTotalduty());
					 */
					// following added on 13feb for new payment modality.
					//if(Double.parseDouble(regForm.getTotalBalanceAmountFee())>
					// 0)
					// {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_029");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmountAdjuFee());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					//regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					// request.setAttribute("parentKey",
					// regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./nonPropRegInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					// end of addition on 13feb for new payment modality.

					// code for checking user type of logged in user:
					// below for new payment requirements. completed on 4 sept
					// 2013

					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();

					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {

						// String[] arr=commonBo.getDistIdNameRU(userId);

						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {

						String[] arr = commonBo.getDistIdNameSP(userId);

						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {

						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();

						if (arr != null && arr.length == 3) {

							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {

							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}

					}

					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);

					// end of new payment requirements added on 4th sept 2013

					String paymentType = "3";

					regForm.setPaymentType(paymentType);

					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.

					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					//logger.debug("----------------->payment flag:-"+paymentArr
					// [0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {

							if (paymentArr[0].equalsIgnoreCase("I")) {

								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm
								// .getTotalduty
								// (),regForm.getHidnUserId(),regForm
								// .getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";

							}

						} else if (paymentArr[0].equalsIgnoreCase("p")) {

							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.
							// getTotalduty
							// (),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {

						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty
						// (),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
					/*
					 * }else{
					 * 
					 * actionName=RegInitConstant.FINAL_ACTION;
					 * 
					 * }
					 */
					// forward="reginitPayment";
					// return mapping.findForward(forward);
				} else {
					logger.debug("adjudication duty insertion failure---> ");
					forward = "failure";
				}

			}

			// by anuj
			if ("checkRegFeeBox".equals(actionName)) {

				regForm.setTotalBalanceAmountFee(regForm.getRegistrationFee());
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";
			}
			if (RegInitConstant.NEXT_TO_FEE_PAYMENT_ACTION.equals(actionName)) {

				regForm.setActionName("");

				regForm.setHidnUserId(session.getAttribute("UserId").toString());
				/*
				 * session.setAttribute("parentModName",
				 * "Registration Process");
				 * session.setAttribute("parentFunName",
				 * "Registration Initiation");
				 * session.setAttribute("parentFunId", "FUN_023");
				 * session.setAttribute("parentAmount", regForm.getTotalduty());
				 */
				// following added on 13feb for new payment modality.
				if (Double.parseDouble(regForm.getTotalBalanceAmountFee()) > 0) {
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("formName", "reginit");
					request.setAttribute("parentFunId", "FUN_206");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmountFee());// CHANGE
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					//regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					// request.setAttribute("parentKey",
					// regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./nonPropRegInit.do?TRFS=NGI");
					request.setAttribute("parentColumnName", "TXN_ID");
					// end of addition on 13feb for new payment modality.

					// code for checking user type of logged in user:
					// below for new payment requirements. completed on 4 sept
					// 2013

					String userTypeId = commonBo.getUserTypeId(userId);
					String parentOfficeId = "NA";
					String parentOfficeName = "NA";
					String parentDistrictId = "NA";
					String parentDistrictName = "NA";
					String parentReferenceId = regForm.getHidnRegTxnId();

					if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)) {

						// String[] arr=commonBo.getDistIdNameRU(userId);

						// if(arr!=null && arr.length==2){
						parentDistrictId = commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
						parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						// }
						// else{
						// parentDistrictId="NA";
						// parentDistrictName="NA";
						// }

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_FI) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_PO)) {

						String[] arr = commonBo.getDistIdNameSP(userId);

						if (arr != null && arr.length == 2) {
							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
						} else {
							parentDistrictId = "NA";
							parentDistrictName = "NA";
						}

					} else if (userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)) {

						String[] arr = commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId = session.getAttribute("loggedToOffice").toString();

						if (arr != null && arr.length == 3) {

							parentDistrictId = arr[0].trim();
							parentDistrictName = commonBo.getDistrictName(parentDistrictId, languageLocale);
							parentOfficeName = commonBo.getOfficeName(parentOfficeId, languageLocale);
						} else {

							parentDistrictId = "NA";
							parentDistrictName = "NA";
							parentOfficeName = "NA";
						}

					}

					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);

					// end of new payment requirements added on 4th sept 2013

					String paymentType = "2";

					regForm.setPaymentType(paymentType);

					// CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.

					String[] paymentArr = commonBo.getPaymentFlag(regForm.getHidnRegTxnId(), paymentType);
					//logger.debug("----------------->payment flag:-"+paymentArr
					// [0]);
					if (paymentArr != null) {
						if (!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")) {

							if (paymentArr[0].equalsIgnoreCase("I")) {

								regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
								request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
								// insertion code
								// String
								// param[]={regForm.getHidnRegTxnId(),regForm
								// .getTotalduty
								// (),regForm.getHidnUserId(),regForm
								// .getPurpose()};
								boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
								logger.debug("----------------->payment insertion status:-" + insertStatus);
								if (insertStatus)
									forward = "reginitPayment";
								else
									forward = "failure";

							}

						} else if (paymentArr[0].equalsIgnoreCase("p")) {

							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							// insertion code
							// String
							// param[]={regForm.getHidnRegTxnId(),regForm.
							// getTotalduty
							// (),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
							logger.debug("----------------->payment insertion status:-" + insertStatus);
							if (insertStatus)
								forward = "reginitPayment";
							else
								forward = "failure";
						} else if (paymentArr[0].equalsIgnoreCase("c")) {
							forward = "success1";
						} else {
							forward = "failure";
						}
					} else {

						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						// insertion code
						// String
						// param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty
						// (),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus = commonBo.insertPaymentDetails(regForm, paymentType);
						logger.debug("----------------->payment insertion status:-" + insertStatus);
						if (insertStatus)
							forward = "reginitPayment";
						else
							forward = "failure";
					}
				} else {

					actionName = RegInitConstant.FINAL_ACTION;

				}
				// forward="reginitPayment";
				// return mapping.findForward(forward);
			}

			/*
			 * if(RegInitConstant.CALCULATE_DUTIES_ACTION.equals(actionName)) {
			 * HashMap propMap =new HashMap();
			 * propMap=regForm.getMapPropertyTransParty();
			 * logger.debug("in confirmation action----------------------->");
			 * 
			 * ArrayListpropertyIdList=commonBo.getPropertyIdMarketVal(regForm.
			 * getHidnRegTxnId()); double totalMarketVal=0; for(int
			 * i=0;i<propertyIdList.size();i++){
			 * 
			 * ArrayList row_list=new ArrayList();
			 * row_list=(ArrayList)propertyIdList.get(i); String
			 * propIds=row_list.toString(); propIds=propIds.substring(1,
			 * propIds.length()-1);
			 * logger.debug("property id and market value list-->"+propIds);
			 * String propId[]=propIds.split(",");
			 * 
			 * 
			 * totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim())
			 * ; ArrayList
			 * transPartyDets=commonBo.getTransPartyDets(propId[0].trim
			 * (),regForm.getHidnRegTxnId()); for(int
			 * j=0;j<transPartyDets.size();j++){ CommonDTO dto=new CommonDTO();
			 * dto=(CommonDTO)transPartyDets.get(j);
			 * logger.debug("transacting party list  role------>"
			 * +dto.getRole());
			 * logger.debug("transacting party list  name------>"
			 * +dto.getName());
			 * logger.debug("transacting party list  id------>"+dto.getId());
			 * 
			 * } logger.debug("property id------>"+propId[0].trim());
			 * logger.debug("market value------>"+propId[1].trim());
			 * propMap.put(propId[0].trim()+","+propId[1].trim(),
			 * transPartyDets);
			 * 
			 * }
			 * 
			 * NumberFormat obj=new DecimalFormat("#0.00");
			 * regForm.setTotalMarketValue(obj.format(totalMarketVal));
			 * regForm.setMapPropertyTransParty(propMap);
			 * 
			 * //FOLLOWING CODE FOR DUTY.
			 * if(regForm.getIsMultiplePropsFlag()!=1){ //IN CASE OF SINGLE
			 * PROPERTY String
			 * dutyListArr[]=commonBo.getDutyDetls(regForm.getHidnRegTxnId());
			 * 
			 * regForm.setStampduty1(Double.parseDouble(dutyListArr[0].trim()));
			 * regForm
			 * .setNagarPalikaDuty(Double.parseDouble(dutyListArr[2].trim()));
			 * regForm
			 * .setPanchayatDuty(Double.parseDouble(dutyListArr[1].trim()));
			 * regForm.setUpkarDuty(Double.parseDouble(dutyListArr[3].trim()));
			 * regForm.setTotalduty(Double.parseDouble(dutyListArr[5].trim()));
			 * regForm
			 * .setRegistrationFee(Double.parseDouble(dutyListArr[4].trim()));
			 * 
			 * } else{ //IN CASE OF MULTIPLE PROPERTIES double dutyArray[] = new
			 * double[3]; double nagarPalikaDuty = 0.0; double panchayatDuty =
			 * 0.0; double upkarDuty = 0.0; double total = 0.0; double
			 * stampDuty=0.0; double regFee=0.0;
			 * stampDuty=commonBo.getDutyCalculation(regForm);
			 * 
			 * logger.debug("after first procedure of duty calculation");
			 * 
			 * regForm.setStampduty1(obj.format(stampDuty));
			 * 
			 * dutyArray=commonBo.getMunicipalDutyCalculation(regForm);
			 * logger.debug("after second procedure of duty calculation"); if
			 * (dutyArray.length >= 1) { nagarPalikaDuty = (dutyArray[0]);
			 * panchayatDuty = (dutyArray[1]); upkarDuty = (dutyArray[2]); }
			 * total = nagarPalikaDuty + panchayatDuty + upkarDuty + stampDuty;
			 * regForm.setNagarPalikaDuty(obj.format(nagarPalikaDuty));
			 * regForm.setPanchayatDuty(obj.format(panchayatDuty));
			 * regForm.setUpkarDuty(obj.format(upkarDuty));
			 * regForm.setTotalduty(obj.format(total));
			 * 
			 * regFee = commonBo.getRegistrationFee(regForm);
			 * logger.debug("after third procedure of duty calculation");
			 * regForm.setRegistrationFee(obj.format(regFee)); }
			 * regForm.setIsDutyCalculated(1);
			 * regForm.setActionName(RegInitConstant.NO_ACTION);
			 * forward="reginitConfirm"; }
			 */

		}
		// FOLLOWING ADDED BY ROOPAM
		if (RegInitConstant.CANCEL_TRANSACTING_PART_ACTION.equals(actionName) || RegInitConstant.CANCEL_ACTION.equals(actionName) && (request.getParameter("modName") == null)) {

			cancelAction(session, regForm);
			if (map != null) {
				if (!map.isEmpty())
					map.clear();
			}
			// count=0;
			// keyCount=0;
			forward = "welcome";
			return mapping.findForward(forward);
		}

		if (regForm.getMapKeyCount() == 0)
			regForm.setDeleteTrnsPrtyID("");

		if (RegInitConstant.ADD_MORE_ACTION.equals(actionName)) {

			RegCommonDTO mapDto = new RegCommonDTO();

			regForm.setActionName("");

			// following code for insertion of owner details into map

			int roleId = 0;
			if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")) {
				roleId = Integer.parseInt(regForm.getPartyTypeTrns());
			}

			String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
			int claimantFlag = Integer.parseInt(claimantArr[0].trim());

			mapDto.setClaimantFlag(claimantFlag);

			if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || regForm.getCommonDeed() == 1 && (regForm.getExecutantClaimantTrns() == RegInitConstant.CLAIMANT_FLAG_2 || regForm.getExecutantClaimantTrns() == RegInitConstant.CLAIMANT_FLAG_4)) {
				mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);

				RegCommonDTO mapDto1 = new RegCommonDTO();

				mapDto1.setOwnerNameTrns(regForm.getOwnerNameTrns());
				if (regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
					mapDto1.setOwnerOgrNameTrns("-");
				else
					mapDto1.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());

				mapDto1.setOwnerGendarValTrns(regForm.getOwnerGendarTrns());
				if (regForm.getOwnerGendarTrns().equalsIgnoreCase("f")) {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto1.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);

					}
				} else {
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto1.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);

					}
				}
				mapDto1.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
				mapDto1.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
				mapDto1.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
				if (regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
					mapDto1.setOwnerEmailIDTrns("-");
				else
					mapDto1.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());

				mapDto1.setOwnerAgeTrns(regForm.getOwnerAgeTrns());

				mapDto1.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerIdnoTrns());
				mapDto1.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
				mapDto1.setOwnerProofNameTrns(regForm.getOwnerListIDTrns().equalsIgnoreCase("") ? "-" : regForm.getOwnerProofNameTrns());

				if (regForm.getAddressOwnerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOwnerOutMpTrns())) {
					mapDto1.setAddressOwnerOutMpTrns(regForm.getAddressOwnerOutMpTrns());
				} else {
					mapDto1.setAddressOwnerOutMpTrns("-");
				}

				mapDto1.setOwnerTxnId(commonBo.getTransactingPartyIdSeq());

				regForm.getTrnsOwnerList().put(mapDto1.getOwnerTxnId(), mapDto1);

				mapDto.setTrnsOwnerList(regForm.getTrnsOwnerList());
			} else {
				mapDto.setPoaHolderFlag(0);
			}

			mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
			mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
			mapDto.setPurposeTrns("");
			mapDto.setBname(""); // might be changed
			mapDto.setBaddress(""); // might be changed
			mapDto.setApplicantOrTransParty("Transacting");
			mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
			regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());

			mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
			mapDto.setUserID(regForm.getHidnUserId());

			if (regForm.getOwnershipShareTrns() != null) {
				if (regForm.getOwnershipShareTrns().equalsIgnoreCase(""))
					mapDto.setOwnershipShareTrns("-");
				else
					mapDto.setOwnershipShareTrns(regForm.getOwnershipShareTrns());
			}

			if (regForm.getRelationWithOwnerTrns() != null && regForm.getRelationWithOwnerTrns().equalsIgnoreCase("")) {
				mapDto.setRelationWithOwnerTrns("-");
			} else {
				mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());
			}
			// by shreeraj
			/*
			 * if(regForm.getShareOfPropTrns().equalsIgnoreCase("")){
			 * mapDto.setShareOfPropTrns("-");
			 * mapDto.setHdnDeclareShareCheck("N"); } else{
			 * mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
			 * mapDto.setHdnDeclareShareCheck("Y"); }
			 */

			// following code for share of property validation.
			// int appRoleId=regForm.getApplicantRoleId2();
			// if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){
			// if(appRoleId==RegInitConstant.ROLE_SELLER_SELF ||
			// appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
			String applicantRoleId1 = commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());

			// following if condition for disabling applicant role once total
			// sum of share is 100%.

			if (regForm.getTotalShareOfProp() == 100) {
				regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId1));
			}

			/*
			 * }elseif(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.
			 * getApplicantRoleId2()){ String
			 * applicantRoleId=commonBo.getApplicantRoleId
			 * (regForm.getHidnRegTxnId());
			 * 
			 * 
			 * //following if condition for disabling applicant role once total
			 * sum of share is 100%. if(regForm.getTotalShareOfProp()==100){
			 * regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId)); }
			 * }
			 */
			// }
			/*
			 * if(regForm.getDeedID()==RegInitConstant.DEED_GIFT){
			 * if(appRoleId==RegInitConstant.ROLE_SELLER_SELF ||
			 * appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
			 * 
			 * String
			 * applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId
			 * ());
			 * 
			 * 
			 * //following if condition for disabling applicant role once total
			 * sum of share is 100%. if(regForm.getTotalShareOfProp()==100){
			 * regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId)); }
			 * 
			 * }elseif(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.
			 * getApplicantRoleId2()){ String
			 * applicantRoleId=commonBo.getApplicantRoleId
			 * (regForm.getHidnRegTxnId());
			 * 
			 * 
			 * //following if condition for disabling applicant role once total
			 * sum of share is 100%. if(regForm.getTotalShareOfProp()==100){
			 * regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId)); }
			 * } }
			 */
			// end of code for share of property validation.
			if (regForm.getCommonDeed() != 1) {
				mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(), languageLocale, regForm.getDeedID(), regForm.getInstID()));
			} else {
				mapDto.setRoleName(regForm.getCommonDeedRoleName());
			}

			mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimantTrns());
			if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {

				// mapDto.setGovtOfcCheck(regForm.getGovtOfcCheck());
				if (regForm.getNameOfOfficialTrns() != null && !("").equalsIgnoreCase(regForm.getNameOfOfficialTrns())) {
					mapDto.setNameOfOfficial(regForm.getNameOfOfficialTrns());
				} else {
					mapDto.setNameOfOfficial("-");
				}
				mapDto.setDesgOfOfficial(regForm.getDesgOfOfficialTrns());
				mapDto.setAddressOfOfficial(regForm.getAddressOfOfficialTrns());
				mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
				mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
				mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId2(), languageLocale)); // Added
																														// by
																														// Gulrej
																														// on
																														// 26
																														// th
																														// may
																														// ,
																														// 2017
				mapDto.setOgrNameTrns("-");
				// mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
				mapDto.setIndAuthPersn(mapDto.getDesgOfOfficial());
				mapDto.setIndividualOrOrganization(RegInitConstant.GOVT_OFFCL_ID);

				if (regForm.getAddressGovtOffclOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressGovtOffclOutMpTrns())) {
					mapDto.setAddressGovtOffclOutMpTrns(regForm.getAddressGovtOffclOutMpTrns());
				} else {
					mapDto.setAddressGovtOffclOutMpTrns("-");
				}

			} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
				// organization
				mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
				mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
				mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
				mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
				mapDto.setSelectedCountry(regForm.getCountryTrns());
				mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
				mapDto.setSelectedState(regForm.getStatenameTrns());
				mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
				mapDto.setSelectedDistrict(regForm.getDistrictTrns());
				mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(), languageLocale));
				mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId2(), languageLocale)); // Added
																														// by
																														// Gulrej
																														// on
																														// 26
																														// th
																														// May
																														// ,
																														// 2017
				mapDto.setTehsilGov1(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId7(), languageLocale)); // Added
																														// by
																														// Gulrej
																														// on
																														// 26
																														// th
																														// May
																														// ,
																														// 2017
				if (regForm.getMobnoTrns().equalsIgnoreCase(""))
					mapDto.setMobnoTrns("-");
				else
					mapDto.setMobnoTrns(regForm.getMobnoTrns());
				if (regForm.getPhnoTrns().equalsIgnoreCase(""))
					mapDto.setPhnoTrns("-");
				else
					mapDto.setPhnoTrns(regForm.getPhnoTrns());
				// mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
				// mapDto.setMarketValueTrns(regForm.getMarketValueTrns());

				mapDto.setFnameTrns("");
				mapDto.setMnameTrns("");
				mapDto.setLnameTrns("");
				// mapDto.setGendarTrns("-");
				// mapDto.setSelectedGender("");
				mapDto.setAgeTrns("");
				// mapDto.setFatherNameTrns("-");
				mapDto.setMotherNameTrns("");
				mapDto.setSpouseNameTrns("");
				mapDto.setNationalityTrns("");
				mapDto.setPostalCodeTrns("");
				mapDto.setEmailIDTrns("");
				mapDto.setSelectedPhotoId("");
				mapDto.setSelectedPhotoIdName("");
				mapDto.setIdnoTrns("-");

				// mapDto.setIndReligionTrns("");
				mapDto.setIndCasteTrns("");
				mapDto.setIndDisabilityTrns("");

				mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);

				mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
				if (regForm.getAuthPerGendarTrns().equalsIgnoreCase("m")) {
					// mapDto.setSelectedGender("Male");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);

					}
				} else {
					// mapDto.setSelectedGender("Female");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);

					}
				}
				mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());

				mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
				mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()), languageLocale));

				mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
				mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
				mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(), languageLocale));
				mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
				mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(), languageLocale));
				mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
				mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(), languageLocale));

				if (regForm.getAddressOrgOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressOrgOutMpTrns())) {
					mapDto.setAddressOrgOutMpTrns(regForm.getAddressOrgOutMpTrns());
				} else {
					mapDto.setAddressOrgOutMpTrns("-");
				}

				if (regForm.getAddressAuthPerOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressAuthPerOutMpTrns())) {
					mapDto.setAddressAuthPerOutMpTrns(regForm.getAddressAuthPerOutMpTrns());
				} else {
					mapDto.setAddressAuthPerOutMpTrns("-");
				}

			} else if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				// individual
				mapDto.setFnameTrns(regForm.getFnameTrns());
				if (regForm.getMnameTrns().equalsIgnoreCase(""))
					mapDto.setMnameTrns("-");
				else
					mapDto.setMnameTrns(regForm.getMnameTrns());
				mapDto.setLnameTrns(regForm.getLnameTrns());
				mapDto.setGendarTrns(regForm.getGendarTrns());
				if (regForm.getGendarTrns().equalsIgnoreCase("m")) {
					// mapDto.setSelectedGender("Male");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);

					} else {
						mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);

					}
				} else {
					// mapDto.setSelectedGender("Female");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);

					} else {
						mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);

					}
				}
				if (regForm.getAgeTrns().equalsIgnoreCase(""))
					mapDto.setAgeTrns("-");
				else
					mapDto.setAgeTrns(regForm.getAgeTrns());
				mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
				if (regForm.getMotherNameTrns().equalsIgnoreCase(""))
					mapDto.setMotherNameTrns("-");
				else
					mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
				if (regForm.getSpouseNameTrns().equalsIgnoreCase(""))
					mapDto.setSpouseNameTrns("-");
				else
					mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
				mapDto.setNationalityTrns(regForm.getNationalityTrns());
				if (regForm.getPostalCodeTrns().equalsIgnoreCase(""))
					mapDto.setPostalCodeTrns("-");
				else
					mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());

				if (regForm.getIndphnoTrns().equalsIgnoreCase(""))
					mapDto.setPhnoTrns("-");
				else
					mapDto.setPhnoTrns(regForm.getIndphnoTrns());

				if (regForm.getIndmobnoTrns().equalsIgnoreCase(""))
					mapDto.setMobnoTrns("-");
				else
					mapDto.setMobnoTrns(regForm.getIndmobnoTrns());

				if (regForm.getEmailIDTrns().equalsIgnoreCase(""))
					mapDto.setEmailIDTrns("-");
				else
					mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
				mapDto.setSelectedPhotoId(regForm.getListIDTrns());
				mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
				mapDto.setIdnoTrns(regForm.getIdnoTrns());

				mapDto.setOgrNameTrns("-");
				mapDto.setAuthPerNameTrns("-");
				mapDto.setIndAuthPersn(regForm.getFnameTrns() + " " + regForm.getLnameTrns());
				mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
				mapDto.setSelectedCountry(regForm.getIndcountryTrns());
				mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
				mapDto.setSelectedState(regForm.getIndstatenameTrns());
				mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
				mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
				mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(), languageLocale));
				mapDto.setTehsilGov(commonBo.getTehsilName1(regForm.getCommonDto().getTehsilId2(), languageLocale)); // Added
																														// by
																														// Gulrej
																														// on
																														// 26
																														// th
																														// May
																														// ,
																														// 2017
				mapDto.setSelectedCategoryName(regForm.getSelectedCategoryNameTrns());
				mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
				mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(), languageLocale));
				mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());
				// mapDto.setRelationshipList(regForm.)
				mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());

				if (regForm.getIndCategoryTrns().equalsIgnoreCase(RegInitConstant.ST_CONSTANT)) {
					if (regForm.getIndScheduleAreaTrns() != null) {
						if (regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")) {
							// mapDto.setIndScheduleAreaTrnsDisplay("Yes");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
							} else {
								mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
							}
						} else {
							// mapDto.setIndScheduleAreaTrnsDisplay("No");
							if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
								mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
							} else {
								mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
							}
							mapDto.setPermissionNoTrns(regForm.getPermissionNoTrns());
							mapDto.setDocumentNameTrns(regForm.getDocumentNameTrns());
							mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
							mapDto.setFilePathTrns(regForm.getFilePathTrns());
							mapDto.setDocContentsTrns(regForm.getDocContentsTrns());
							mapDto.setPartyOrPropTrns(regForm.getPartyOrPropTrns());
							mapDto.setUploadTypeTrns(regForm.getUploadTypeTrns());

						}
					}
				}

				if (regForm.getIndDisabilityTrns().equalsIgnoreCase("")) {
					mapDto.setIndDisabilityTrns("-");
				} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("Y")) {
					mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
					// mapDto.setIndDisabilityTrns("Yes");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
					} else {
						mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
					}
					if (regForm.getIndDisabilityDescTrns().equalsIgnoreCase("")) {
						mapDto.setIndDisabilityDescTrns("-");
					} else {
						mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
					}
				} else if (regForm.getIndDisabilityTrns().equalsIgnoreCase("N")) {
					mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
					// mapDto.setIndDisabilityTrns("No");

					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
					} else {
						mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
					}

				}

				mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

				//mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm
				// .getUserDayTrns(), regForm.getUserMonthTrns(),
				// regForm.getUserYearTrns()));

				if (regForm.getIndMinorityTrns().equalsIgnoreCase("")) {
					mapDto.setIndMinorityTrns("-");
				} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("Y")) {
					// mapDto.setIndMinorityTrns("Yes");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
					} else {
						mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
					}

				} else if (regForm.getIndMinorityTrns().equalsIgnoreCase("N")) {
					// mapDto.setIndMinorityTrns("No");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
					} else {
						mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
					}

				}

				// mapDto.setIndMinorityTrns("");
				if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("")) {
					mapDto.setIndPovertyLineTrns("-");
				} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y")) {
					// mapDto.setIndPovertyLineTrns("Yes");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
					} else {
						mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
					}

				} else if (regForm.getIndPovertyLineTrns().equalsIgnoreCase("N")) {
					// mapDto.setIndPovertyLineTrns("No");
					if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
						mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
					} else {
						mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
					}

				}
				// mapDto.setIndPovertyLineTrns("");

				mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);

				mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
				mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()), languageLocale));

				if (regForm.getAddressIndOutMpTrns() != null && !("").equalsIgnoreCase(regForm.getAddressIndOutMpTrns())) {
					mapDto.setAddressIndOutMpTrns(regForm.getAddressIndOutMpTrns());
				} else {
					mapDto.setAddressIndOutMpTrns("-");
				}
			}
			// below code for uploads other than collector's permission no.

			// mohit
			if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
				mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
				mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
				mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
				mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
				mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
				mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());

				if (regForm.getPropReqFlag().equalsIgnoreCase("Y")) {
					// BELOW CODE FOR EXECUTANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {
						mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
						mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
						mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
						mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
						mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
						mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());

						/*
						 * mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns
						 * ());mapDto.setU4DocumentSizeTrns(regForm.
						 * getU4DocumentSizeTrns());
						 * mapDto.setU4FilePathTrns(regForm
						 * .getU4FilePathTrns());
						 * mapDto.setU4DocContentsTrns(regForm
						 * .getU4DocContentsTrns());
						 * mapDto.setU4PartyOrPropTrns(
						 * regForm.getU4PartyOrPropTrns());
						 * mapDto.setU4UploadTypeTrns
						 * (regForm.getU4UploadTypeTrns());
						 */

						mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
						mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
						mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
						mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
						mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
						mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
					}
					// BELOW CODE FOR EXECUTANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {
						/*
						 * mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns
						 * ());mapDto.setU5DocumentSizeTrns(regForm.
						 * getU5DocumentSizeTrns());
						 * mapDto.setU5FilePathTrns(regForm
						 * .getU5FilePathTrns());
						 * mapDto.setU5DocContentsTrns(regForm
						 * .getU5DocContentsTrns());
						 * mapDto.setU5PartyOrPropTrns(
						 * regForm.getU5PartyOrPropTrns());
						 * mapDto.setU5UploadTypeTrns
						 * (regForm.getU5UploadTypeTrns());
						 */

						mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
						mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
						mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
						mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
						mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
						mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());

						/*
						 * mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns
						 * ());mapDto.setU7DocumentSizeTrns(regForm.
						 * getU7DocumentSizeTrns());
						 * mapDto.setU7FilePathTrns(regForm
						 * .getU7FilePathTrns());
						 * mapDto.setU7DocContentsTrns(regForm
						 * .getU7DocContentsTrns());
						 * mapDto.setU7PartyOrPropTrns(
						 * regForm.getU7PartyOrPropTrns());
						 * mapDto.setU7UploadTypeTrns
						 * (regForm.getU7UploadTypeTrns());
						 */

						mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
						if (regForm.getPoaRegNoTrns() != null && !regForm.getPoaRegNoTrns().equalsIgnoreCase("")) {
							mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
						} else {
							mapDto.setPoaRegNoTrns("-");
						}
						if (regForm.getDatePoaRegTrns() != null && !regForm.getDatePoaRegTrns().equalsIgnoreCase("")) {
							mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
						} else {
							mapDto.setDatePoaRegTrns("-");
						}
					}

					// BELOW CODE FOR CLAIMANT
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {
						/*
						 * mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns
						 * ());mapDto.setU8DocumentSizeTrns(regForm.
						 * getU8DocumentSizeTrns());
						 * mapDto.setU8FilePathTrns(regForm
						 * .getU8FilePathTrns());
						 * mapDto.setU8DocContentsTrns(regForm
						 * .getU8DocContentsTrns());
						 * mapDto.setU8PartyOrPropTrns(
						 * regForm.getU8PartyOrPropTrns());
						 * mapDto.setU8UploadTypeTrns
						 * (regForm.getU8UploadTypeTrns());
						 */

						mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
						mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
						mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
						mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
						mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
						mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
					}

					// BELOW CODE FOR CLAIMANT POA HOLDER
					if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

						mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
						mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
						mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
						mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
						mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
						mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());

					}
				}
			}

			map = regForm.getMapTransactingParties();

			int count = regForm.getMapKeyCount();
			if (count == 0)
				count = 1;

			if (map.containsKey(Integer.toString(count))) {

				count++;
				map.put(Integer.toString(count), mapDto);

			} else
				map.put(Integer.toString(count), mapDto);

			regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);

			regForm.setMapKeyCount(count);

			// regForm.setAddMoreCounter(count);
			regForm.setAddMoreCounter(map.size());
			// key="key";

			mapDto.setListDto(regForm.getListDto());
			regForm.setListDto(new ArrayList<CommonDTO>());
			regForm.setMapTransactingParties(map);

			if (regForm.getCommonDeed() == 1) {
				regForm.setExecutantClaimantTrns(0);
				commonDto.setExecutantClaimant(new ArrayList());
			}
			if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
				regForm.setGovtOfcCheckTrns("");
				regForm.setNameOfOfficialTrns("");
				regForm.setDesgOfOfficialTrns("");
				regForm.setAddressOfOfficialTrns("");
				regForm.setInddistrictTrns("");
				regForm.setAddressGovtOffclOutMpTrns("");
			}
			if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)) {
				regForm.setPartyTypeTrns(null);
				regForm.setOgrNameTrns("");
				regForm.setAuthPerNameTrns("");
				regForm.setOrgaddressTrns("");
				regForm.setPhnoTrns("");
				regForm.setMobnoTrns("");
				regForm.setConsiAmountTrns("");
				regForm.setMarketValueTrns("");
				regForm.setCountryTrns("");
				regForm.setDistrictTrns("");
				regForm.setStatenameTrns("1");
				regForm.setStatenameNameTrns(commonBo.getStateName("1", languageLocale));
				// commonDto.setCountryTrns(commonBo.getCountry());
				regForm.setActionName("");
				regForm.setOwnershipShareTrns("");
				regForm.setRelationWithOwnerTrns("");
				regForm.setShareOfPropTrns("");
				regForm.setCountryTrns("1");
				regForm.setCountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setExecutantClaimantTrns(0);

				regForm.setAuthPerGendarTrns("M");
				regForm.setAuthPerFatherNameTrns("");
				regForm.setAuthPerRelationshipTrns(0);
				regForm.setAuthPerAddressTrns("");
				regForm.setAuthPerDistrictTrns("");

				regForm.setAddressOrgOutMpTrns("");
				regForm.setAddressAuthPerOutMpTrns("");

				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));

			}
			if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
				regForm.setPartyTypeTrns(null);
				regForm.setFnameTrns("");
				regForm.setMnameTrns("");
				regForm.setLnameTrns("");
				regForm.setGendarTrns("M");
				regForm.setAgeTrns("");
				regForm.setFatherNameTrns("");
				regForm.setMotherNameTrns("");
				regForm.setSpouseNameTrns("");
				regForm.setNationalityTrns("");
				regForm.setPostalCodeTrns("");
				regForm.setIndphnoTrns("");
				regForm.setIndmobnoTrns("");
				regForm.setEmailIDTrns("");
				regForm.setListIDTrns("");
				regForm.setIdnoTrns("");
				regForm.setIndaddressTrns("");
				regForm.setIndcountryTrns("");
				regForm.setInddistrictTrns("");
				regForm.setIndstatenameTrns("");
				// commonDto.setIndcountryTrns(commonBo.getCountry());
				// commonDto.setIdProfTrns(commonBo.getIdProf());
				regForm.setActionName("");
				regForm.setIndReligionTrns("");
				regForm.setIndCasteTrns("");
				regForm.setIndDisabilityTrns("N");
				regForm.setIndDisabilityDescTrns("");
				regForm.setIndMinorityTrns("N");
				regForm.setOwnershipShareTrns("");
				regForm.setRelationWithOwnerTrns("");
				regForm.setShareOfPropTrns("");
				regForm.setIndPovertyLineTrns("N");
				// regForm.setIndMinorityTrns("N");
				regForm.setIndCategoryTrns("");
				regForm.setOccupationIdTrns("");
				/*
				 * regForm.setCountryTrns("INDIA");
				 * regForm.setCountryNameTrns("INDIA");
				 * regForm.setStatenameTrns("MP");
				 * regForm.setStatenameNameTrns("MADHYA PRADESH");
				 */
				regForm.setIndcountryTrns("1");
				regForm.setIndcountryNameTrns(commonBo.getCountryName("1", languageLocale));
				regForm.setIndstatenameTrns("1");
				regForm.setIndstatenameNameTrns(commonBo.getStateName("1", languageLocale));

				regForm.setUserDayTrns("");
				regForm.setUserMonthTrns("");
				regForm.setUserYearTrns("");
				regForm.setUserDOBTrns("");
				regForm.setIndScheduleAreaTrns("");
				regForm.setPermissionNoTrns("");
				regForm.setCertificateTrns(null);
				regForm.setDocumentNameTrns("");
				regForm.setDocumentSizeTrns("");
				regForm.setFilePathTrns("");
				regForm.setDocContentsTrns(new byte[0]);
				regForm.setPartyOrPropTrns("");
				regForm.setUploadTypeTrns("");

				regForm.setRelationshipTrns(0);
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));

				regForm.setAddressIndOutMpTrns("");

				regForm.setExecutantClaimantTrns(0);

			}
			if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
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

				regForm.setSrOfficeNameTrns("");
				regForm.setPoaRegNoTrns("");
				regForm.setDatePoaRegTrns("");
				if (Integer.parseInt(claimantArr[1].trim()) == RegInitConstant.POA_HOLDER_FLAG || mapDto.getPoaHolderFlag() == RegInitConstant.POA_HOLDER_FLAG) {
					regForm.setTrnsOwnerList(new HashMap());
					regForm.setOwnerNameTrns("");
					regForm.setOwnerOgrNameTrns("");
					regForm.setOwnerGendarTrns("M");
					regForm.setOwnerNationalityTrns("");
					regForm.setOwnerAddressTrns("");
					regForm.setOwnerPhnoTrns("");
					regForm.setOwnerEmailIDTrns("");
					regForm.setOwnerIdnoTrns("");
					regForm.setOwnerAgeTrns("");
					regForm.setOwnerListIDTrns("");
					regForm.setOwnerProofNameTrns("");
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					regForm.setOwnerDayTrns("");
					regForm.setOwnerMonthTrns("");
					regForm.setOwnerYearTrns("");
					regForm.setAddressOwnerOutMpTrns("");
				}
			}
			regForm.setTermsCondPartyTrns("");
			regForm.setListAdoptedPartyTrns(null);

			if (regForm.getCommonDeed() == 1) {
				// if(regForm.getAddMoreTransParty().equalsIgnoreCase("Y")){
				// above commented because add more button will only be visible
				// if AddMoreTransParty is Y

				if (regForm.getAddPartyNewRole() == 1) {
					regForm.setCommonDeedRoleName("");
					regForm.setRoleSameAsPrevious(0);
				} else {
					regForm.setRoleSameAsPrevious(1);
				}

				// }else{

				// redirect to particulars of transaction page

				// }
				regForm.setAddPartyNewRole(0);
				regForm.setAddMoreTransParty("N");
			}

			regForm.setClaimantFlag(0);
			regForm.setPoaHolderFlag(0);
			forward = "transactingParty";
		}
		if (RegInitConstant.DELETE_MORE_ACTION.equals(actionName)) {

			String[] trnsPrtyID = regForm.getHdnDeleteTrnsPrtyId().split(",");

			int count = regForm.getMapKeyCount() - 1;
			map = regForm.getMapTransactingParties();

			for (int i = 0; i < trnsPrtyID.length; i++) {

				map.remove(trnsPrtyID[i]);

			}
			regForm.setMapTransactingParties(map);

			// above for display map
			// below for insertion map
			map = new HashMap();
			map = regForm.getMapTransPartyDbInsertion();

			for (int j = 0; j < trnsPrtyID.length; j++) {
				logger.debug(trnsPrtyID[j] + " is removed...");
				map.remove(trnsPrtyID[j]);

			}
			// regForm.setAddMoreCounter(count);
			regForm.setAddMoreCounter(map.size());
			regForm.setMapKeyCount(count);

			/*
			 * if(map.size()==0 &&
			 * (regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
			 * (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV &&
			 * (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 ||
			 * regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))
			 * )){
			 * 
			 * regForm.setIsFirstPartyAddedFlag(0);
			 * regForm.setAddMoreCounter(0);
			 * 
			 * }else
			 */
			if (map.size() == 0) {
				regForm.setAddMoreCounter(0);
			}

			regForm.setMapTransPartyDbInsertion(map);

			forward = "transactingParty";
			// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			regForm.setActionName(RegInitConstant.NO_ACTION);
			actionName = RegInitConstant.NO_ACTION;
			// return mapping.findForward(forward);
		}
		if (RegInitConstant.ADD_MORE_PARTICULAR_ACTION.equals(actionName)) {

			RegCommonDTO mapDto = new RegCommonDTO();

			mapDto.setParticularName(regForm.getParticularName());
			mapDto.setParticularDesc(regForm.getParticularDesc());

			HashMap partMap = regForm.getMapParticulars();

			int count = regForm.getAddMoreParticularCounter();
			if (count == 0)
				count = 1;

			if (partMap.containsKey(Integer.toString(count))) {

				count++;
				partMap.put(Integer.toString(count), mapDto);

			} else
				partMap.put(Integer.toString(count), mapDto);

			regForm.setAddMoreParticularCounter(count);
			regForm.setMapParticulars(partMap);

			regForm.setParticularName("");
			regForm.setParticularDesc("");

			forward = "reginitParticular";
		}

		if (RegInitConstant.DEL_MORE_PARTICULAR_ACTION.equals(actionName)) {

			// RegCommonDTO mapDto =new RegCommonDTO();
			// int roleCount=0;
			String[] trnsPrtyID = regForm.getHdnDeleteParticularID().split(",");

			// int count=regForm.getMapKeyCount()-1;
			HashMap partMap = regForm.getMapParticulars();
			int count = regForm.getAddMoreParticularCounter();

			for (int i = 0; i < trnsPrtyID.length; i++) {

				partMap.remove(trnsPrtyID[i]);
				count--;

			}
			regForm.setMapParticulars(partMap);

			// above for display map
			// below for insertion map

			regForm.setAddMoreParticularCounter(count);
			// regForm.setMapKeyCount(count);
			forward = "reginitParticular";
			// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
			// request.setAttribute("deedId",regForm.getDeedID());
			// regForm.setActionName(RegInitConstant.NO_ACTION);
			actionName = RegInitConstant.NO_ACTION;
			// return mapping.findForward(forward);

		}
		if (RegInitConstant.SAVE_PARTICULARS_ACTION.equals(actionName)) {

			// int
			// particularCount=commonBo.getParticularCount(regForm.getHidnRegTxnId
			// ());

			int particularCount = 0;
			if (regForm.getMapParticulars() != null && regForm.getMapParticulars().size() > 0)
				particularCount = regForm.getMapParticulars().size();

			if ((regForm.getParticularName() == null || regForm.getParticularDesc() == null || regForm.getParticularName().equalsIgnoreCase("") || regForm.getParticularDesc().equalsIgnoreCase("")) && (particularCount == 0)) {
				// forward="reginitConfirm"
				boolean updateStatus = commonBo.insertTxnDetailsFinalAction(RegInitConstant.STATUS_SHARES_SAVED, regForm.getHidnRegTxnId());
				if (updateStatus) {
					actionName = RegInitConstant.NEXT_TO_CONFIRM_ACTION;
				} else {
					forward = "failure";
				}
			} else {

				HashMap partMap = regForm.getMapParticulars();

				int count = regForm.getAddMoreParticularCounter();

				if (regForm.getParticularName() != null && regForm.getParticularDesc() != null && !regForm.getParticularName().equalsIgnoreCase("") && !regForm.getParticularDesc().equalsIgnoreCase("")) {
					RegCommonDTO mapDto = new RegCommonDTO();

					mapDto.setParticularName(regForm.getParticularName());
					mapDto.setParticularDesc(regForm.getParticularDesc());

					if (count == 0)
						count = 1;

					if (partMap.containsKey(Integer.toString(count))) {

						count++;
						partMap.put(Integer.toString(count), mapDto);

					} else
						partMap.put(Integer.toString(count), mapDto);
				}

				regForm.setAddMoreParticularCounter(count);
				regForm.setMapParticulars(partMap);

				// ABOVE CODE FOR INSERTING LAST PARTICULAR IN MAP
				// BELOW CODE FOR INSERTION OF ALL THE PARTICULARS IN DB

				boolean insertionStatus = false;

				insertionStatus = commonBo.insertParticularsDetails(regForm.getMapParticulars(), regForm.getHidnRegTxnId(), regForm.getHidnUserId());

				if (!insertionStatus) {
					forward = "reginitParticular";
					regForm.setHidnRegTxnId(null);
					actionName = RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);

				} else {
					actionName = RegInitConstant.NEXT_TO_MAPPING_ACTION;
				}
			}
		}
		// added by Shreeraj for particular
		if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);

			int particularCount = commonBo.getParticularCount(regForm.getHidnRegTxnId());
			int partyCount = commonBo.getPartyCount(regForm.getHidnRegTxnId());

			String checkParticular = regForm.getRegcompletDto().getCheckboxParticular();
			if (checkParticular == null) {

				checkParticular = "N";
			}
			boolean mapInsert = false;
			if (checkParticular.equalsIgnoreCase("Y") || particularCount == 1 || partyCount == 1) {

				mapInsert = commonBo.getAllPartiesPrarticulars(regForm);

				logger.debug("mapping insertion status : " + mapInsert);
			}

			// if((particularCount==1) || (particularCount>1 &&
			// checkParticular.equalsIgnoreCase("Y")) ){
			// method to insert mapping of all the parties to all the prop
			if ((particularCount == 1 && partyCount == 1) || (particularCount == 1 && partyCount > 1) || (partyCount == 1 && particularCount > 1) || checkParticular.equalsIgnoreCase("Y") && checkParticular != null && mapInsert) {

				commonBo.landConfirmationScreen(regForm, languageLocale, request);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";

				// regForm.setParticularWithMapping(commonBo.
				// getAllPartiesPrarticularsAlreadyMap(regForm,languageLocale));
				// regForm.setParticularWithoutMapping(null);
			}

			else {
				// regForm.setParticularWithMapping(null);

				regForm.setParticularWithoutMapping(commonBo.getAllPartiesParticularsMap(regForm, languageLocale));
				regForm.setDeclareShareSize(regForm.getParticularWithoutMapping().size());
				forward = "reginitMapping";
			}
			return mapping.findForward(forward);
		}

		// ADDED BY SHREERAJ FOR NON PROP MAPPING
		if (request.getParameter("mappingnonprop") != null && request.getParameter("mappingnonprop").equalsIgnoreCase("Y")) {
			String mapCheck;
			int count = commonBo.getPropertyCount(regForm.getHidnRegTxnId());
			int partyCount = commonBo.getPartyCount(regForm.getHidnRegTxnId());

			if (request.getAttribute("mapCheck") != null) {
				mapCheck = (String) request.getAttribute("mapCheck");
			} else {
				mapCheck = "";
			}

			boolean mapInsert = false;

			if ((mapCheck != null && mapCheck.equalsIgnoreCase("ON")) || (partyCount == 1 && count > 1) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {

				mapInsert = commonBo.getAllPartiesProperties(regForm);

				logger.debug("mapping insertion status : " + mapInsert);

			}

			if ((count == 1 && partyCount == 1) || (count == 1 && partyCount > 1) || (partyCount == 1 && count > 1) || (mapCheck != null && mapCheck.equalsIgnoreCase("ON") && mapInsert) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {

				// hashmap already containing mappings

				regForm.setPropWithMapping(commonBo.getAllPartiesPropertiesAlreadyMap(regForm, languageLocale));
				regForm.setPropWithoutMapping(null);
				regForm.setDeclareShareSize(regForm.getPropWithMapping().size());
				regForm.setCheckParty("true");
				regForm.setAppStatus(RegInitConstant.STATUS_BACKED_MAPPING_SAVED);
				// Set set=regForm.getPropWithMapping().keySet();
				// Iterator itr=set.iterator();
				// ArrayList
				// lis=(ArrayList)regForm.getPropWithMapping().get(itr.next());
				//regForm.setShareOfPropSize((regForm.getPropWithMapping().size(
				// ))*(lis.size()));
				forward = "reginitPropMapping";

			} else {

				regForm.setPropWithMapping(null);
				regForm.setPropWithoutMapping(commonBo.getAllPartiesPropertiesMap(regForm, languageLocale));
				regForm.setDeclareShareSize(regForm.getPropWithoutMapping().size());
				regForm.setAppStatus(RegInitConstant.STATUS_PROP_SAVED);
				// Set set=regForm.getPropWithoutMapping().keySet();
				// Iterator itr=set.iterator();
				// ArrayList
				// lis=(ArrayList)regForm.getPropWithoutMapping().get(itr
				// .next());
				// regForm.setShareOfPropSize((regForm.getPropWithoutMapping().
				// size())*(lis.size()));
				forward = "reginitPropMapping";

			}
			return mapping.findForward(forward);
		}

		if (RegInitConstant.MAPPING_FORM.equals(formName)) {

			if (RegInitConstant.PARTY_POPUP_CLOSE_MAPPING_ACTION.equals(actionName)) {

				forward = commonBo.landMappingScreen(regForm, languageLocale);
				if (forward.equalsIgnoreCase("alreadyMappedPropNonPV") || forward.equalsIgnoreCase("getMappingPropNonPV")) {
					forward = "reginitPropMapping";
				} else if (forward.equalsIgnoreCase("getMappingParticularNonPV")) {
					forward = "reginitMapping";
				}
				regForm.setActionName("");
				return mapping.findForward(forward);

			}

			if (RegInitConstant.RESET_SHARES_ACTION.equals(actionName)) {

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "reginitPropMapping";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.RESET_PARTICULAR_MAPPING_ACTION.equals(actionName)) {

				regForm.setActionName(RegInitConstant.NO_ACTION);
				forward = "reginitMapping";
				return mapping.findForward(forward);

			}

			if (RegInitConstant.VALIDATE_PARTICULAR_MAP_ACTION.equals(actionName)) {

				regForm.setActionName("");

				HashMap activeMap = regForm.getParticularWithoutMapping();
				// int saveMappingFlag=0;

				commonBo.refreshParticularHashMap(activeMap, regForm);

				HashMap shareMap = activeMap;
				/*
				 * double shareParty1=0; double shareParty2=0; int
				 * party1Added=0; int party2Added=0;
				 */

				if (shareMap != null && shareMap.size() > 0) {

					/*
					 * Set set=shareMap.keySet(); Iterator itr=set.iterator();
					 * ArrayList partyList; String key; CommonDTO dto; int
					 * roleId; double share; int partyType1Or2;
					 * while(itr.hasNext()){ party1Added=0; party2Added=0;
					 * shareParty1=0; shareParty2=0; key=(String)itr.next();
					 * partyList=(ArrayList)shareMap.get(key);
					 * if(partyList!=null && partyList.size()>0){
					 * 
					 * for(int i=0;i<partyList.size();i++){
					 * 
					 * dto=(CommonDTO)partyList.get(i);
					 * 
					 * roleId=Integer.parseInt( dto.getRoleId());
					 * 
					 * 
					 * partyType1Or2=commonBo.getPartyType1Or2(regForm.getDeedID(
					 * ), regForm.getInstID(), roleId);
					 * 
					 * if(saveMappingFlag==1){
					 * 
					 * if(partyType1Or2==1){
					 * if(dto.getPartyCheck().equalsIgnoreCase("Y"))
					 * party1Added=1; }else{
					 * if(dto.getPartyCheck().equalsIgnoreCase("Y"))
					 * party2Added=1; }
					 * 
					 * }
					 * 
					 * if(dto.getShareOfProp()!=null &&
					 * !dto.getShareOfProp().equalsIgnoreCase("")){
					 * share=Double.parseDouble(dto.getShareOfProp()); }else{
					 * share=0; } if(partyType1Or2==1){ shareParty1 += share;
					 * }else{ shareParty2 += share; }
					 * 
					 * }
					 * 
					 * if(saveMappingFlag==1){
					 * 
					 * 
					 * if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE
					 * || regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV
					 * ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV){
					 * if(party1Added==0){
					 * if(languageLocale.equalsIgnoreCase(RegInitConstant
					 * .LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_1_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_1_HI); }
					 * forward="reginitPropMapping"; return
					 * mapping.findForward(forward); } }else if(party1Added==0
					 * || party2Added==0){
					 * if(languageLocale.equalsIgnoreCase(RegInitConstant
					 * .LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_1_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_1_HI); }
					 * forward="reginitPropMapping"; return
					 * mapping.findForward(forward); }
					 * 
					 * }
					 * 
					 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE){
					 * if(shareParty2!=100 && shareParty1!=100){
					 * if(languageLocale
					 * .equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_HI); } forward="alreadyMapped";
					 * return mapping.findForward(forward); } }else
					 * if(regForm.getDeedID
					 * ()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
					 * regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
					 * regForm
					 * .getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV
					 * ||
					 * regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV){
					 * if(shareParty1!=0 && shareParty1!=100){
					 * if(languageLocale.
					 * equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_HI); }
					 * forward="reginitPropMapping"; return
					 * mapping.findForward(forward); } }else{ if((shareParty1!=0
					 * && shareParty2!=0) && (shareParty1!=100 ||
					 * shareParty2!=100)){
					 * if(languageLocale.equalsIgnoreCase(RegInitConstant
					 * .LANGUAGE_ENGLISH)){
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_EN); }else{
					 * request.setAttribute("shareErrorMsg",
					 * RegInitConstant.MESSAGE_2_HI); }
					 * forward="reginitPropMapping"; return
					 * mapping.findForward(forward); } }
					 * 
					 * 
					 * }
					 * 
					 * 
					 * }
					 */

					// boolean
					// shareUpdate=commonBo.updatePartyPropShares(shareMap,
					// regForm, saveMappingFlag);
					boolean shareUpdate = commonBo.insertPartyParticularMap(shareMap, regForm);

					if (shareUpdate) {
						commonBo.landConfirmationScreen(regForm, languageLocale, request);
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						DutyCalculationForm dutyForm = new DutyCalculationForm();
						dutyForm.setPropImage1DocumentName("");
						dutyForm.setEmptyFile("");
						forward = "reginitConfirm";
					} else {
						forward = "failure";
					}
					return mapping.findForward(forward);

					// insert shares here

				}

			}
			if (RegInitConstant.VALIDATE_SHARES_ACTION.equals(actionName)) {

				regForm.setActionName("");

				HashMap activeMap = new HashMap();
				int saveMappingFlag = 0;

				if (regForm.getPropWithMapping() != null && regForm.getPropWithMapping().size() > 0) {
					activeMap = regForm.getPropWithMapping();
				} else {
					activeMap = regForm.getPropWithoutMapping();
					saveMappingFlag = 1;
				}

				commonBo.refreshHashMap(activeMap, request, regForm, saveMappingFlag);

				HashMap shareMap = activeMap;
				double shareParty1 = 0;
				double shareParty2 = 0;
				int party1Added = 0;
				int party2Added = 0;

				if (shareMap != null && shareMap.size() > 0) {

					Set set = shareMap.keySet();
					Iterator itr = set.iterator();
					ArrayList partyList;
					String key;
					CommonDTO dto;
					int roleId;
					double share;
					int partyType1Or2;
					while (itr.hasNext()) {
						party1Added = 0;
						party2Added = 0;
						shareParty1 = 0;
						shareParty2 = 0;
						key = (String) itr.next();
						partyList = (ArrayList) shareMap.get(key);
						if (partyList != null && partyList.size() > 0) {

							for (int i = 0; i < partyList.size(); i++) {

								dto = (CommonDTO) partyList.get(i);

								roleId = Integer.parseInt(dto.getRoleId());

								partyType1Or2 = commonBo.getPartyType1Or2(regForm.getDeedID(), regForm.getInstID(), roleId);

								if (saveMappingFlag == 1) {

									if (partyType1Or2 == 1) {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party1Added = 1;
									} else {
										if (dto.getPartyCheck().equalsIgnoreCase("Y"))
											party2Added = 1;
									}

								}

								if (dto.getShareOfProp() != null && !dto.getShareOfProp().equalsIgnoreCase("")) {
									share = Double.parseDouble(dto.getShareOfProp());
								} else {
									share = 0;
								}
								if (partyType1Or2 == 1) {
									shareParty1 += share;
								} else {
									shareParty2 += share;
								}

							}

							if (saveMappingFlag == 1) {

								if (regForm.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE || regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {
									if (party1Added == 0) {
										if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
										} else {
											request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
										}
										forward = "reginitPropMapping";
										return mapping.findForward(forward);
									}
								} else if (party1Added == 0 || party2Added == 0) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_1_HI);
									}
									forward = "reginitPropMapping";
									return mapping.findForward(forward);
								}

							}

							/*
							 * if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE
							 * ){ if(shareParty2!=100 && shareParty1!=100){
							 * if(languageLocale
							 * .equalsIgnoreCase(RegInitConstant
							 * .LANGUAGE_ENGLISH)){
							 * request.setAttribute("shareErrorMsg",
							 * RegInitConstant.MESSAGE_2_EN); }else{
							 * request.setAttribute("shareErrorMsg",
							 * RegInitConstant.MESSAGE_2_HI); }
							 * forward="alreadyMapped"; return
							 * mapping.findForward(forward); } }else
							 */
							if (regForm.getDeedID() == RegInitConstant.DEED_DEPOSIT_OF_TITLE || regForm.getDeedID() == RegInitConstant.DEED_TRUST || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || regForm.getDeedID() == RegInitConstant.DEED_RECONV_MORTGAGE_NPV || (regForm.getDeedID() == RegInitConstant.DEED_PARTNERSHIP_NPV && !(regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_NPV || regForm.getInstID() == RegInitConstant.INST_DISSOLUTION_2)) || regForm.getDeedID() == RegInitConstant.DEED_PARTITION_NPV) {
								if (shareParty1 != 0 && shareParty1 != 100) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
									}
									forward = "reginitPropMapping";
									return mapping.findForward(forward);
								}
							} else {
								if ((shareParty1 != 0 && shareParty2 != 0) && (shareParty1 != 100 || shareParty2 != 100)) {
									if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_EN);
									} else {
										request.setAttribute("shareErrorMsg", RegInitConstant.MESSAGE_2_HI);
									}
									forward = "reginitPropMapping";
									return mapping.findForward(forward);
								}
							}

						}

					}

					boolean shareUpdate = commonBo.updatePartyPropShares(shareMap, regForm, saveMappingFlag);

					if (shareUpdate) {
						/*
						 * commonBo.landConfirmationScreen(regForm,
						 * languageLocale,request);
						 * request.setAttribute("deedId", regForm.getDeedID());
						 * request.setAttribute("instId", regForm.getInstID());
						 * forward="reginitConfirm";
						 */
						regForm.setActionName("");
						/*
						 * String[]
						 * param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId
						 * ()); regForm.setConsenterFlag(param[0]);
						 * regForm.setConsenterCount
						 * (Integer.parseInt(param[1]));
						 */

						if (regForm.getConsenterFlag() == null) {
							regForm.setConsenterFlag("NA");
						}

						if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
							boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
							if (updateStatus) {
								forward = "reginitConsenter";
								regForm.setListDto(new ArrayList());
								regForm.setRegDTO(new RegCompleteMakerDTO());
								regForm.setConsenterAddedCount(0);
								commonDto.setIdProf(commonBo.getIdProf(languageLocale));
								commonDto.setState(commonBo.getState("1", languageLocale));
								commonDto.setDistrict(new ArrayList());
								commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
								// return mapping.findForward(forward);
							}
							/*
							 * else
							 * if(regForm.getConsenterFlag().equalsIgnoreCase
							 * ("NA")){ forward="reginitConfirm";
							 * request.setAttribute("deedId",
							 * regForm.getDeedID());
							 * request.setAttribute("instId",
							 * regForm.getInstID());
							 * commonBo.landConfirmationScreen(regForm,
							 * languageLocale,request); return
							 * mapping.findForward(forward); }
							 */

							else {
								logger.debug("unable to update reg status for consenter.");
								forward = "failure";

							}
						} else {
							DutyCalculationForm dutyForm = new DutyCalculationForm();
							dutyForm.setPropImage1DocumentName("");
							dutyForm.setEmptyFile("");

							if (regForm.getConsenterFlag().equalsIgnoreCase("NA")) {
								// if(request.getParameter("regStatus").
								// equalsIgnoreCase
								// (RegInitConstant.STATUS_TRNS_BANK2)){
								String regTxnId = regForm.getHidnRegTxnId();
								dutyForm.setHidnRegTxnId(regTxnId);
								session.setAttribute("regTxnId", regTxnId);
								request.setAttribute("regTxnId", regTxnId);
								// regForm.setHidnRegTxnId(regTxnId);
								forward = "reginitConfirm2";
								// }
							} else {
								forward = "reginitConfirm";
								request.setAttribute("deedId", regForm.getDeedID());
								request.setAttribute("instId", regForm.getInstID());
								// commonBo.landConfirmationScreen(regForm,
								// languageLocale,request);
								return mapping.findForward(forward);
							}

						}
					} else {
						forward = "failure";
					}
					return mapping.findForward(forward);

					// insert shares here

				}

			}

		}
		if (RegInitConstant.CANCEL_PARTICULARS_ACTION.equals(actionName)) {

			cancelAction(session, regForm);
			if (map != null) {
				if (!map.isEmpty())
					map.clear();
			}
			// count=0;
			// keyCount=0;
			forward = "welcome";
			return mapping.findForward(forward);

		}
		if (RegInitConstant.RESET_PARTICULARS_ACTION.equals(actionName)) {

			regForm.setParticularName("");
			regForm.setParticularDesc("");

			forward = "reginitParticular";

		}

		// following code for party type
		if (request.getAttribute("regFormDashboard") == null) { // this line was
																// added for
																// dashboard
			String roleType = null;
			String roleTypeTrns = null;
			if (request.getParameter("partyType") != null && !request.getParameter("partyType").toString().equalsIgnoreCase("")) {

				roleType = (String) request.getParameter("partyType");
				regForm.setPartyType(roleType); // setting role id.
				if (regForm.getCommonDeed() != 1) {
					String[] claimantArr = commonBo.getClaimantFlag(roleType);
					// int claimantFlag=Integer.parseInt(claimantArr[0]);
					regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				}

				commonDto.setAppType(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), Integer.parseInt(roleType)));

			} else {
				roleType = "0";
			}
			if (request.getParameter("partyTypeTrns") != null && !request.getParameter("partyTypeTrns").toString().equalsIgnoreCase("")) {

				roleTypeTrns = (String) request.getParameter("partyTypeTrns");
				regForm.setPartyTypeTrns(roleTypeTrns); // setting role id.
				if (regForm.getCommonDeed() != 1) {
					String[] claimantArr = commonBo.getClaimantFlag(roleTypeTrns);
					// int claimantFlag=Integer.parseInt(claimantArr[0]);
					regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
					regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				}

				commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale, regForm.getPoaHolderFlag(), Integer.parseInt(roleTypeTrns)));

			} else {
				roleTypeTrns = "0";
			}

			if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)) {

				if ((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) && !(RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName))) {
					request.setAttribute("roleId", Integer.parseInt(roleType));
					request.setAttribute("roleIdTrns", "0");
				}
				if (roleTypeTrns != null && (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					request.setAttribute("roleIdTrns", Integer.parseInt(roleTypeTrns));

			}
		}

		if (RegInitConstant.NO_ACTION.equals(actionName)) {
			if (regForm.getPartyRoleId() != null && !regForm.getPartyRoleId().equalsIgnoreCase("") && !regForm.getPartyRoleId().equalsIgnoreCase("null")) {
				request.setAttribute("roleIdTrns", Integer.parseInt(regForm.getPartyRoleId()));
			} else
				request.setAttribute("roleIdTrns", 0);
		}

		if (RegInitConstant.NO_ACTION.equals(actionName)) { // forward
															// ="reginitMapping"
															// ;

		} else if (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName) || RegInitConstant.ADD_MORE_ACTION.equals(actionName) || RegInitConstant.DELETE_MORE_ACTION.equals(actionName))
			forward = "transactingParty";
		else if (RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName))
			forward = "reginitMapping";
		else if (RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName) || RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName))
			forward = "reginitProperty";
		else if (RegInitConstant.NEXT_TO_EFILE_PAYMENT_ACTION.equals(actionName)) {// forward
																					// =
																					// "reginitPayment"
																					// ;
			regForm.setActionName(RegInitConstant.NO_ACTION);
		}
		// else
		// forward ="success";

		if (RegInitConstant.RESET_APPLICANT_ACTION.equals(actionName)) {
			regForm.setDeleteOwnerID("");
			regForm.setHdnDeleteOwnerID("");
			regForm.setAppOwnerList(new HashMap());
			regForm.getCommonDto().setIndtehsilOrg1(new ArrayList());
			regForm.getCommonDto().setIndtehsilpoaOrg(new ArrayList());
			regForm.getCommonDto().setIndtehsilpoa(new ArrayList());
			regForm.getCommonDto().setIndtehsil(new ArrayList());
			regForm.getCommonDto().setIndtehsilOrg5(new ArrayList());
			regForm.getCommonDto().setIndtehsilOrg(new ArrayList());
			regForm.getCommonDto().setIndtehsilpoaInd(new ArrayList());

			regForm.setFnameArb("");
			regForm.setMnameArb("");
			regForm.setLnameArb("");
			regForm.setGendarArb("M");
			regForm.setAgeArb("");
			regForm.setFatherNameArb("");
			regForm.setMotherNameArb("");
			regForm.setSpouseNameArb("");
			regForm.setNationalityArb("");
			regForm.setIndaddressArb("");
			regForm.setInddistrictArb("");
			regForm.setEmailIDArb("");
			regForm.setIndCategoryArb("");
			regForm.setIndDisabilityArb("N");
			regForm.setIndDisabilityDescArb("");
			regForm.setListIDArb("");
			regForm.setIdnoArb("");

			regForm.setCancelDeedRadio(0);
			regForm.setBankName("");
			regForm.setBranchName("");
			regForm.setBankAddress("");
			regForm.setBankAuthPer("");
			regForm.setBankLoanAmt("0");
			regForm.setBankSancAmt("0");
			regForm.setTrustName("");
			regForm.setTrustDate("");
			regForm.setAdvance(0);
			regForm.setDevlpmtCharge(0);
			regForm.setOtherRecCharge(0);
			regForm.setPremium(0);
			regForm.setTermLease(0);
			regForm.setWithPos("");
			regForm.setSecLoanAmt(0);
			regForm.setPoaWithConsid("");
			regForm.setPoaPeriod(0);
			regForm.setPaidLoanAmt(0);
			regForm.setContriProp("");
			regForm.setDissolutionDate("");
			regForm.setRetirmentDate("");
			regForm.setAdvancePaymntDetails("");
			regForm.setPossGiven("N");
			regForm.setExecutionDate("");
			regForm.setRegistrationDate("");
			regForm.setRegistrationNo("");
			regForm.setReceiptAmount(0);
			regForm.setPropDetls("");
			regForm.setDeedNamePreReg("");
			regForm.setDeedTypePreReg("");
			regForm.setMapOrderNo("");
			regForm.setMapOrderDate("");
			regForm.setTcpOrderDate("");
			regForm.setTcpOrderNo("");

			regForm.setPoaShareDebenture("");
			regForm.setNameOfOfficial("");
			regForm.setDesgOfOfficial("");
			regForm.setAddressOfOfficial("");
			regForm.setAddressGovtOffclOutMp("");
			regForm.setAddressAuthPerOutMp("");
			regForm.setAddressIndOutMp("");
			regForm.setAddressOrgOutMp("");
			regForm.setAddressOwnerOutMp("");

			regForm.setBankName("");
			regForm.setBranchName("");
			regForm.setBankAddress("");
			regForm.setBankAuthPer("");
			regForm.setBankLoanAmt("0");
			regForm.setBankSancAmt("0");

			regForm.setTrustName("");
			regForm.setTrustDate("");

			regForm.setRent(0);
			regForm.setAdvance(0);
			regForm.setDevlpmtCharge(0);
			regForm.setOtherRecCharge(0);
			regForm.setPremium(0);
			regForm.setTermLease(0);

			regForm.setWithPos("");
			regForm.setSecLoanAmt(0);
			regForm.setPoaWithConsid("");
			regForm.setPoaPeriod(0);

			regForm.setPaidLoanAmt(0);

			regForm.setAdvancePaymntDate("");
			regForm.setPossGiven("N");
			regForm.setPossGivenName("");

			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");
			regForm.setInddistrictArb("");
			regForm.setFnameArb("");
			regForm.setMnameArb("");
			regForm.setLnameArb("");
			regForm.setGendarArb("");
			regForm.setAgeArb("");
			regForm.setFatherNameArb("");
			regForm.setMotherNameArb("");
			regForm.setSpouseNameArb("");
			regForm.setNationalityArb("");
			regForm.setIndaddressArb("");
			regForm.setIndcountryArb("");
			regForm.setIndstatenameArb("");
			regForm.setInddistrictArb("");
			regForm.setIndphnoArb("");
			regForm.setIndmobnoArb("");
			regForm.setEmailIDArb("");
			regForm.setIndCategoryArb("");
			regForm.setIndDisabilityArb("");
			regForm.setIndDisabilityDescArb("");
			regForm.setListIDArb("");
			regForm.setIdnoArb("");

			commonDto.setInstrument(new ArrayList());
			commonDto.setExemption(new ArrayList());
			regForm.setFname("");
			regForm.setMname("");
			regForm.setLname("");
			regForm.setGendar("M");
			// regForm.setAuthPerGendar("M");
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
			regForm.setIndCaste("");
			regForm.setIndReligion("");
			regForm.setIndDisability("");
			// regForm.setShareOfProp("");
			regForm.setOwnershipShare("");
			regForm.setRelationWithOwner("");

			regForm.setInstrument("");
			commonDto.setState(commonBo.getState("1", languageLocale));
			commonDto.setDistrict(commonBo.getDistrict("1", languageLocale));
			commonDto.setIndstate(commonBo.getState("1", languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
			// commonDto.setAppType(commonBo.getAppType(languageLocale));
			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(), languageLocale));
			if (regForm.getCommonDeed() == 1) {
				// commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(
				// languageLocale,regForm.getInstID()));
				commonDto.setExecutantClaimant(new ArrayList());
				commonDto.setAppType(commonBo.getAppType(languageLocale, 0, 0));
			}
			regForm.setExecutantClaimant(0);
			regForm.setRelationship(0);
			regForm.setOgrName("");
			regForm.setAuthPerName("");
			regForm.setOrgaddress("");
			regForm.setCountry("");
			regForm.setStatename("");
			regForm.setDistrict("");
			regForm.setPhno("");
			regForm.setMobno("");
			regForm.setApplicantUserIdCheck(null);
			regForm.setActionName(RegInitConstant.NO_ACTION);
			// following reseting owner details
			regForm.setOwnerName("");
			regForm.setOwnerOgrName("");
			regForm.setOwnerAddress("");
			regForm.setOwnerAge("");
			regForm.setOwnerEmailID("");
			regForm.setOwnerGendar("M");
			regForm.setOwnerIdno("");
			regForm.setOwnerListID("");
			regForm.setOwnerNationality("");
			regForm.setOwnerPhno("");
			regForm.setOwnerProofName("");

			regForm.setCountry("1");
			regForm.setCountryName("INDIA");
			regForm.setStatename("1");
			regForm.setStatenameName("MADHYA PRADESH");
			regForm.setIndcountry("1");
			regForm.setIndcountryName("INDIA");
			regForm.setIndstatename("1");
			regForm.setIndstatenameName("MADHYA PRADESH");
			regForm.setIndcountryArb("1");
			regForm.setIndstatenameArb("1");

			regForm.setUserDay("");
			regForm.setUserMonth("");
			regForm.setUserYear("");
			regForm.setUserDOB("");

			// regForm.setDeclareShare("true");
			// regForm.setHdnDeclareShareCheck("Y");

			regForm.setCertificate(null);
			regForm.setDocumentName("");
			regForm.setDocumentSize("");
			regForm.setFilePath("");
			regForm.setDocContents(new byte[0]);
			regForm.setPartyOrProp("");
			regForm.setUploadType("");

			regForm.setU2(null);
			regForm.setU2DocumentName("");
			regForm.setU2DocumentSize("");
			regForm.setU2FilePath("");
			regForm.setU2DocContents(new byte[0]);
			regForm.setU2PartyOrProp("");
			regForm.setU2UploadType("");

			regForm.setU3(null);
			regForm.setU3DocumentName("");
			regForm.setU3DocumentSize("");
			regForm.setU3FilePath("");
			regForm.setU3DocContents(new byte[0]);
			regForm.setU3PartyOrProp("");
			regForm.setU3UploadType("");

			regForm.setU4(null);
			regForm.setU4DocumentName("");
			regForm.setU4DocumentSize("");
			regForm.setU4FilePath("");
			regForm.setU4DocContents(new byte[0]);
			regForm.setU4PartyOrProp("");
			regForm.setU4UploadType("");

			regForm.setU5(null);
			regForm.setU5DocumentName("");
			regForm.setU5DocumentSize("");
			regForm.setU5FilePath("");
			regForm.setU5DocContents(new byte[0]);
			regForm.setU5PartyOrProp("");
			regForm.setU5UploadType("");

			regForm.setU6(null);
			regForm.setU6DocumentName("");
			regForm.setU6DocumentSize("");
			regForm.setU6FilePath("");
			regForm.setU6DocContents(new byte[0]);
			regForm.setU6PartyOrProp("");
			regForm.setU6UploadType("");

			regForm.setU7(null);
			regForm.setU7DocumentName("");
			regForm.setU7DocumentSize("");
			regForm.setU7FilePath("");
			regForm.setU7DocContents(new byte[0]);
			regForm.setU7PartyOrProp("");
			regForm.setU7UploadType("");

			regForm.setU8(null);
			regForm.setU8DocumentName("");
			regForm.setU8DocumentSize("");
			regForm.setU8FilePath("");
			regForm.setU8DocContents(new byte[0]);
			regForm.setU8PartyOrProp("");
			regForm.setU8UploadType("");

			regForm.setU9(null);
			regForm.setU9DocumentName("");
			regForm.setU9DocumentSize("");
			regForm.setU9FilePath("");
			regForm.setU9DocContents(new byte[0]);
			regForm.setU9PartyOrProp("");
			regForm.setU9UploadType("");

			regForm.setU10(null);
			regForm.setU10DocumentName("");
			regForm.setU10DocumentSize("");
			regForm.setU10FilePath("");
			regForm.setU10DocContents(new byte[0]);
			regForm.setU10PartyOrProp("");
			regForm.setU10UploadType("");

			regForm.setU11(null);
			regForm.setU11DocumentName("");
			regForm.setU11DocumentSize("");
			regForm.setU11FilePath("");
			regForm.setU11DocContents(new byte[0]);
			regForm.setU11PartyOrProp("");
			regForm.setU11UploadType("");

			regForm.setSrOfficeName("");
			regForm.setPoaRegNo("");
			regForm.setDatePoaReg("");

			regForm.setAuthPerAddress("");
			regForm.setAuthPerCountry("1");
			regForm.setAuthPerDistrict("");
			regForm.setAuthPerStatename("1");
			regForm.setAuthPerFatherName("");
			regForm.setAuthPerRelationship(0);
			regForm.setAuthPerGendar("M");
			// session.setAttribute("commonDto", commonDto);
			regForm.setCommonDto(commonDto);

			if (regForm.getCommonDeed() == 1) {
				regForm.setCommonDeedRoleName("");
			}

			// session.setAttribute("regForm", regForm);

			forward = "success";

		}
		if (RegInitConstant.RESET_TRANSACTING_ACTION.equals(actionName)) {

			if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")) {
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
			} else {
				request.setAttribute("roleIdTrns", 0);
			}
			// String partyTypeTrns = regForm.getListAdoptedPartyTrns();

			// session.setAttribute("partyType", partyTypeTrns);
			// resetToken(request);

			if (regForm.getCommonDeed() == 1 && regForm.getRoleSameAsPrevious() == 0) {
				regForm.setCommonDeedRoleName("");
			}
			regForm.setDeleteOwnerID("");
			regForm.setHdnDeleteOwnerID("");
			// regForm.setOwnerGendarVal("");
			// regForm.setOwnerModifyFlag(0);

			// regForm.setAppOwnerList(new HashMap());
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
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));
			commonDto.setIndstateTrns(commonBo.getState("1", languageLocale));
			commonDto.setInddistrictTrns(commonBo.getDistrict("1", languageLocale));
			// commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
			commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
			commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			regForm.setRelationshipTrns(0);

			regForm.setExecutantClaimantTrns(0);
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
			regForm.setAuthPerStatenameTrns("1");
			regForm.setAuthPerFatherNameTrns("");
			regForm.setAuthPerRelationshipTrns(0);
			regForm.setAuthPerGendarTrns("M");

			// session.setAttribute("commonDto", commonDto);
			regForm.setCommonDto(commonDto);

			// session.setAttribute("regForm", regForm);

			forward = "transactingParty";

		}

		if (RegInitConstant.RESET_MAPPING_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setOwnerId("");
			// session.setAttribute("regForm", regForm);
			forward = "reginitMapping";

		}
		if (RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			PropertyValuationDTO dto = new PropertyValuationDTO();
			RegCompletDTO comDto = new RegCompletDTO();
			regForm.setRegcompletDto(comDto);
			regForm.setPropertyDTO(dto);

			// session.setAttribute("regForm", regForm);
			request.setAttribute("fromAdju", regForm.getFromAdjudication());
			forward = "reginitProperty";

		}
		if (RegInitConstant.APPLICANT_USERID_CHECK_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			if (regForm.getHdnapplicantUserIdCheck().equalsIgnoreCase("checked")) {

				commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString(), regForm, languageLocale);
				/*
				 * regForm.setFname(appRegDetls[0].trim());
				 * if(appRegDetls[1].trim().equalsIgnoreCase("null")){
				 * regForm.setMname(""); }else{
				 * regForm.setMname(appRegDetls[1].trim()); }
				 * regForm.setLname(appRegDetls[2].trim());
				 * regForm.setGendar(appRegDetls[3].trim());
				 * commonDto.setRelationshipList
				 * (commonBo.getRelationshipList(regForm
				 * .getGendar(),languageLocale));
				 * if(appRegDetls[4].trim().equalsIgnoreCase("null")){
				 * regForm.setNationality(""); }else{
				 * regForm.setNationality(appRegDetls[4].trim()); }
				 */
				// regForm.setIndcountry("1");
				regForm.getCommonDto().setIndtehsilpoa(new ArrayList());
				if (regForm.getInddistrict() != null) {
					// String languageLocale1 =
					// (String)session.getAttribute("languageLocale");
					ArrayList tehsilList = commonBo.getTehsilList(regForm.getInddistrict(), languageLocale1);
					System.out.println("tehsil list value" + tehsilList);

					regForm.getCommonDto().setIndtehsilpoa(tehsilList);
					System.out.println("display tehsil" + tehsilList);
					regForm.getCommonDto().setTehsilId("-1"); // added by
																// siddhartha
					// ArrayList tehsilListInd =
					// commonBo.getTehsilListModify(regForm.getInddistrict());

					// commonDto.setIndtehsilpoa(tehsilListInd);
				}
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIndstate(commonBo.getState("1", languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));
				regForm.setCommonDto(commonDto);
				// regForm.setIndstatename(appRegDetls[6].trim());
				/*
				 * if(appRegDetls[6].trim().equalsIgnoreCase("1")){
				 * regForm.setInddistrict(appRegDetls[7].trim());
				 * regForm.setInddistrictNameTrns
				 * (commonBo.getDistrictName(appRegDetls
				 * [7].trim(),languageLocale)); }else{
				 * regForm.setInddistrict("");
				 * regForm.setInddistrictNameTrns(""); }
				 * regForm.setIndaddress(appRegDetls[8].trim());
				 * if(appRegDetls[9].trim().equalsIgnoreCase("null")){
				 * regForm.setPostalCode(""); }else{
				 * regForm.setPostalCode(appRegDetls[9].trim()); }
				 * if(appRegDetls[10].trim().equalsIgnoreCase("null")){
				 * regForm.setIndphno(""); }else{
				 * regForm.setIndphno(appRegDetls[10].trim()); }
				 * //regForm.setIndphno(appRegDetls[10].trim());
				 * regForm.setIndmobno(appRegDetls[11].trim());
				 * //regForm.setEmailID(appRegDetls[12].trim());
				 * if(appRegDetls[12]!=null &&
				 * !appRegDetls[12].trim().equalsIgnoreCase("") &&
				 * !appRegDetls[12].trim().equalsIgnoreCase("null")){
				 * regForm.setEmailID(appRegDetls[12].trim()); } else{
				 * regForm.setEmailID(""); }
				 * if(appRegDetls[13].trim().equalsIgnoreCase("null")){
				 * regForm.setListID(""); regForm.setListIDName(""); }else{
				 * regForm.setListID(appRegDetls[13].trim());
				 * regForm.setListIDName
				 * (commonBo.getPhotoIdProofName(appRegDetls
				 * [13].trim(),languageLocale)); }
				 * //regForm.setListID(appRegDetls[13].trim());
				 * if(appRegDetls[14].trim().equalsIgnoreCase("null")){
				 * regForm.setIdno(""); }else{
				 * regForm.setIdno(appRegDetls[14].trim()); }
				 * regForm.setFatherName(appRegDetls[15].trim());
				 * if(appRegDetls[16].trim().equalsIgnoreCase("null")){
				 * regForm.setMotherName(""); }else{
				 * regForm.setMotherName(appRegDetls[16].trim()); }
				 * if(appRegDetls[17].trim().equalsIgnoreCase("null")){
				 * regForm.setSpouseName(""); }else{
				 * regForm.setSpouseName(appRegDetls[17].trim()); }
				 * 
				 * if(appRegDetls[18].trim().equalsIgnoreCase("null")){
				 * regForm.setAge(""); }else{
				 * regForm.setAge(appRegDetls[18].trim()); }
				 */

				// regForm.setAge(appRegDetls[18].trim());
				// String dob=appRegDetls[18].trim();
				/*
				 * String[]
				 * finalDate=commonBo.parseStringDatefromDB(appRegDetls[
				 * 18].trim()); regForm.setUserDay(finalDate[0]);
				 * regForm.setUserMonth(finalDate[1]);
				 * regForm.setUserYear(finalDate[2]);
				 */

				/*
				 * if(appRegDetls[19].trim().equalsIgnoreCase("null")){
				 * regForm.setIndCategory("");
				 * regForm.setSelectedCategoryName(""); }else{
				 * regForm.setIndCategory(appRegDetls[19].trim());
				 * regForm.setSelectedCategoryName
				 * (commonBo.getCategoryName(appRegDetls
				 * [19].trim(),languageLocale)); }
				 * 
				 * 
				 * if(appRegDetls[20].trim().equalsIgnoreCase("null")){
				 * regForm.setOccupationId("");
				 * regForm.setSelectedOccupationName(""); }else{
				 * regForm.setOccupationId(appRegDetls[20].trim());
				 * regForm.setSelectedOccupationName
				 * (commonBo.getOccupationName(appRegDetls
				 * [20].trim(),languageLocale)); }
				 */

				// occupation
				//regForm.setIndcountryName(commonBo.getCountryName(appRegDetls[
				// 5].trim()));
				//regForm.setIndstatenameName(commonBo.getStateName(appRegDetls[
				// 6].trim()));
				//regForm.setInddistrictName(commonBo.getDistrictName(appRegDetls
				// [7].trim()));
				//regForm.setListIDName(commonBo.getPhotoIdProofName(appRegDetls
				// [13].trim(),languageLocale));
				// regForm.setCountry("INDIA");
				// regForm.setCountryName("INDIA");
				// regForm.setStatename("MP");
				// regForm.setStatenameName("MADHYA PRADESH");
				/*
				 * regForm.setIndcountry("1");
				 * regForm.setIndcountryName(commonBo.getCountryName("1",
				 * languageLocale)); regForm.setIndstatename("1");
				 * regForm.setIndstatenameName(commonBo.getStateName("1",
				 * languageLocale));
				 */
			} else {
				regForm.setApplicantUserIdCheck(null);
				regForm.getCommonDto().setIndtehsilpoa(new ArrayList());
				regForm.setFname("");
				regForm.setMname("");
				regForm.setLname("");
				regForm.setGendar("M");
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(), languageLocale));
				regForm.setNationality("");
				// regForm.setIndcountry("");
				// regForm.setIndstatename("");
				regForm.setInddistrict("");
				regForm.setInddistrictNameTrns("");
				regForm.setIndaddress("");
				regForm.setPostalCode("");
				regForm.setIndphno("");
				regForm.setIndmobno("");
				regForm.setEmailID("");
				regForm.setListID("");
				regForm.setIdno("");
				regForm.setFatherName("");
				regForm.setMotherName("");
				regForm.setSpouseName("");
				regForm.setAge("");
				// commonDto.setIndstate(new ArrayList());
				// commonDto.setInddistrict(new ArrayList());

				// commonDto.setIndcountry(commonBo.getCountry());
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				regForm.setCommonDto(commonDto);
				// regForm.setIndcountry("INDIA");
				// regForm.setIndcountryName("");
				// regForm.setIndstatename("MP");
				// regForm.setIndstatenameName("");
				regForm.setIndcountry("1");
				regForm.setIndcountryName(commonBo.getCountryName("1", languageLocale));
				regForm.setIndstatename("1");
				regForm.setIndstatenameName(commonBo.getStateName("1", languageLocale));
				regForm.setUserDay("");
				regForm.setUserMonth("");
				regForm.setUserYear("");
				regForm.setIndCategory("");
				regForm.setOccupationId("");

			}

			// session.setAttribute("regForm", regForm);
			forward = "success";

		}
		String label = null;
		label = (String) request.getParameter("label");
		if (label != null && label != "") {
			if (label.equalsIgnoreCase("display")) {

				RegCommonDTO mapDtoDisp = new RegCommonDTO();
				String key = request.getParameter("key");
				request.setAttribute("displayOnwerPartyKey", key);

				map2 = regForm.getMapTransactingPartiesDisp();

				if (!map2.isEmpty())
					map2.clear();

				mapDtoDisp = (RegCommonDTO) regForm.getMapTransactingParties().get(key);
				map2.put(key, mapDtoDisp);

				regForm.setMapTransactingPartiesDisp(map2);
				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("true")) {
						regForm.setConfirmationFlag("01");
					}
				} else
					regForm.setConfirmationFlag("00");

				if (regForm.getDeedID() != 0) {
					request.setAttribute("deedId", regForm.getDeedID());
				} else {
					request.setAttribute("deedId", 0);
				}
				if (mapDtoDisp.getPartyTypeTrns() != null && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")) {
					request.setAttribute("roleId", mapDtoDisp.getPartyTypeTrns());
				} else {
					request.setAttribute("roleId", 0);
				}

				// int roleId=0;

				/*
				 * if(mapDtoDisp.getPartyTypeTrns()!=null &&
				 * !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") &&
				 * !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")){
				 * roleId=Integer.parseInt(mapDtoDisp.getPartyTypeTrns()); }
				 */

				forward = "displayRegDetls";
			}

			if (label.equalsIgnoreCase("displayApp")) {
				forward = "success";
			}
			if (label.equalsIgnoreCase("displayTrns")) {
				forward = "transactingParty";
			}
			if (label.equalsIgnoreCase("displayPartyEdit")) {
				forward = "displayRegDetls";
			}

			if (label.equalsIgnoreCase("deedDraftView")) {

				DeedDraftBD bd = new DeedDraftBD();
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
				// boolean validID=bd.checkID(regForm.getDeedDraftId());
				// if(validID)
				// {
				String path = pr.getValue("pdf.location");
				// bd.generateDeedPDF(path,regForm.getDeedDraftId(),
				// response,0);

				// }else{
				// formDTO.setValidDeed("Invalid");
				// }
				regForm.setActionName("");
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";

			}
			if (label.equalsIgnoreCase("deedDraftViewFolder")) {

				DMSUtility.downloadDocument(response, regForm.getDeedPath(), regForm.getDeedContents());

				regForm.setActionName("");
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";

			}
			if (label.equalsIgnoreCase("displayOwner")) {
				// open owner view here
				String key = request.getParameter("key");
				String reqAtt = request.getParameter("displayOnwerPartyKey");
				if (reqAtt != null) {
					commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, null, reqAtt);
				} else {
					commonBo.getOwnerDetails(key, regForm, languageLocale);
				}
				regForm.setActionName("");
				if (regForm.getPartyModifyFlag() == 1) {
					regForm.setOwnerModifyFlag(1);
				} else {
					regForm.setOwnerModifyFlag(0);
				}

				if (regForm.getOwnerModifyFlag() == 1) {

					if (regForm.getOwnerOgrName().equals("-")) {
						regForm.setOwnerOgrName("");
					}
					if (regForm.getOwnerEmailID().equals("-")) {
						regForm.setOwnerEmailID("");
					}
					if (regForm.getOwnerIdno().equals("-")) {
						regForm.setOwnerIdno("");
					}
					if (regForm.getAddressOwnerOutMp().equals("-")) {
						regForm.setAddressOwnerOutMp("");
					}

					commonDto.setIdProf(commonBo.getIdProf(languageLocale));

				}

				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerAppLive")) {
				// open owner view here
				String key = request.getParameter("key");

				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "app", null);
				regForm.setActionName("");

				forward = "displayOwner";
			}
			if (label.equalsIgnoreCase("displayOwnerTrnsLive")) {
				// open owner view here
				String key = request.getParameter("key");

				commonBo.getOwnerDetailsFromHashmap(key, regForm, languageLocale, "Trns", null);
				regForm.setActionName("");

				forward = "displayOwner";
			}
		}

		// ok action
		if (RegInitConstant.OK_ACTION.equals(actionName)) {

			request.setAttribute("roleId", regForm.getPartyType());
			if (regForm.getPartyTypeTrns() != null && regForm.getPartyTypeTrns() != "")
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
			else
				request.setAttribute("roleIdTrns", "0");
			regForm.setActionName(RegInitConstant.NO_ACTION);
			forward = "transactingParty";

		}
		// confirmation ok action
		if (RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {

			regForm.setPartyModifyFlag(0);
			regForm.setExtraDetlsModifyFlag(0);

			String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
			if (deedInstArray != null && deedInstArray.length > 0) {

				request.setAttribute("deedId", deedInstArray[0].trim());
				request.setAttribute("instId", deedInstArray[1].trim());
				regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
				regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));

			} else {
				request.setAttribute("deedId", 0);
				request.setAttribute("instId", 0);
				regForm.setDeedID(0);
				regForm.setInstID(0);
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			forward = "reginitConfirm";

		}

		if (RegInitConstant.BACK_TO_REG_DISPLAY_ACTION.equals(actionName)) {

			commonBo.openPartyView(request, regForm, languageLocale);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
																					// id
																					// passed
																					// is
																					// for
																					// MP
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));

			forward = "displayRegDetls";
		}

		// view from confirmation screen.
		if (request.getParameter("view") != null) {
			String view = request.getParameter("view").toString();
			if (view.equalsIgnoreCase(RegInitConstant.APPLICANT_VIEW)) {
				forward = "applicantView";
			}
			if (view.equalsIgnoreCase(RegInitConstant.TRANSACTING_PARTY_VIEW)) {
				forward = "transactingPartyView";
			}
			if (view.equalsIgnoreCase(RegInitConstant.MAPPING_VIEW)) {
				forward = "mappingView";
			}
			if (view.equalsIgnoreCase(RegInitConstant.PROPERTY_VIEW)) {
				forward = "propertyView";
			}
		}
		// return from payment
		// return from payment
		// String paymentTxnId=null;
		// String strNonJudECode=null;
		// String modName=null;

		if (request.getParameter("paymentStatus") != null && request.getAttribute("eCode") == null) {

			if (regForm.getFromAdjudication() == 1) {
				// session.setAttribute("fnName","Adjudication");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_ADJU_HINDI);
				}

			} else {
				// session.setAttribute("fnName","Initiation");
				if (languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)) {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				} else {
					session.setAttribute("fnName", RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}

			NumberFormat obj = new DecimalFormat("#0.00");
			double paidAmount = 0;
			if (request.getParameter("paymentStatus").equalsIgnoreCase("P")) {
				String[] deedInstArray = commonBo.getDeedInstId(regForm.getHidnRegTxnId());
				if (deedInstArray != null && deedInstArray.length > 0) {

					request.setAttribute("deedId", deedInstArray[0].trim());
					request.setAttribute("instId", deedInstArray[1].trim());
					regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
					regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));

				} else {
					request.setAttribute("deedId", 0);
					request.setAttribute("instId", 0);
					regForm.setDeedID(0);
					regForm.setInstID(0);
				}

				// following added on 13feb for new payment modality.
				// String paymentStatus=request.getParameter("paymentStatus");
				// logger.debug("payment status---------->"+paymentStatus);
				// end of addition on 13feb for new payment modality.

				if (regForm.getPaymentType().equalsIgnoreCase("1")) {
					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {

						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());

						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmount()) + paidAmount;
						regForm.setTotalPaidAmount(obj.format(finalPaid));

						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmount()) - Double.parseDouble(regForm.getTotalPaidAmount());
						regForm.setTotalBalanceAmount(obj.format(finalBalance));

						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {

							// set p to c
							String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
							boolean updatePaymentStatus = commonBo.updatePaymentStatus(param1);

							logger.debug("payment status updated as c: " + updatePaymentStatus);

							if (updatePaymentStatus) {
								regForm.setPaymentCompleteFlag(1);

								// e stamping redirection at this point.
								request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
								request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
								request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
								request.setAttribute("eStampRegFuncId", "023");
								request.setAttribute("eStampRegPurpose", regForm.getPurpose());
								request.setAttribute("sourceModFlag", "PVN");
								forward = "reginitEstamp";
								return mapping.findForward(forward);

							} else {
								forward = "failure";
								regForm.setRegInitEstampCode(null);
								return mapping.findForward(forward);
							}

						} else {
							regForm.setRegInitEstampCode(null);
						}

					}
					DutyCalculationForm dutyForm = new DutyCalculationForm();
					dutyForm.setPropImage1DocumentName("");
					dutyForm.setEmptyFile("");
					forward = "reginitConfirm";

				} else if (regForm.getPaymentType().equalsIgnoreCase("2")) {

					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {

						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());

						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmountFee()) + paidAmount;
						regForm.setTotalPaidAmountFee(obj.format(finalPaid));

						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmountFee()) - Double.parseDouble(regForm.getTotalPaidAmountFee());
						regForm.setTotalBalanceAmountFee(obj.format(finalBalance));

						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {
							regForm.setPaymentCompleteFlagFee(1);

							// e stamping redirection at this point.
							/*
							 * request.setAttribute("eStampRegId",
							 * regForm.getHidnRegTxnId());
							 * request.setAttribute("eStampRegAmnt",
							 * regForm.getTotalPayableAmount());
							 * request.setAttribute("eStampRegDistId",
							 * commonBo.getDistIdEstamp
							 * (regForm.getHidnRegTxnId()));
							 * request.setAttribute("eStampRegFuncId", "023");
							 * request.setAttribute("eStampRegPurpose",
							 * regForm.getPurpose());
							 * request.setAttribute("sourceModFlag", "PVN");
							 * forward = "reginitEstamp"; return
							 * mapping.findForward(forward);
							 */

							// below code hard coded
							/*
							 * boolean insertEstampMapDetls=false;
							 * insertEstampMapDetls
							 * =commonBo.insertEstampMappingDetls("111",
							 * regForm);
							 * 
							 * if(insertEstampMapDetls){
							 * regForm.setRegInitEstampCode("222"); forward =
							 * "reginitConfirm"; }else{ forward = "failure"; }
							 */

						}

					}

					forward = "regFeePageNPV";

					// return after reg fee payment -- anuj

				} else {

					regForm.setRegInitEstampCode(null);

					String[] paymentArray = commonBo.getPaymentTxnId(regForm.getHidnRegTxnId(), regForm.getPaymentType());
					if (paymentArray != null && paymentArray.length > 0) {

						paidAmount = Double.parseDouble(paymentArray[1].trim());
						regForm.setRegInitPaymntTxnId(paymentArray[0].trim());

						double finalPaid = Double.parseDouble(regForm.getTotalPaidAmountAdjuFee()) + paidAmount;
						regForm.setTotalPaidAmountAdjuFee(obj.format(finalPaid));

						double finalBalance = Double.parseDouble(regForm.getTotalPayableAmountAdjuFee()) - Double.parseDouble(regForm.getTotalPaidAmountAdjuFee());
						regForm.setTotalBalanceAmountAdjuFee(obj.format(finalBalance));

						if (paidAmount >= Double.parseDouble(paymentArray[2].trim()) || finalBalance <= 0) {
							regForm.setPaymentCompleteFlagAdjuFee(1);

						}

					}
					DutyCalculationForm dutyForm = new DutyCalculationForm();
					dutyForm.setPropImage1DocumentName("");
					dutyForm.setEmptyFile("");
					forward = "reginitConfirm";

				}

				// forward = "reginitConfirm";

			}
		}

		// following code will execute wen flow returns from estamping

		if (request.getAttribute("eCode") != null) {

			String eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
			if (eCode != null && !("").equalsIgnoreCase(eCode)) {

				regForm.setRegInitEstampCode(eCode);
				regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";

			} else {
				String eStampPdfPath = "";
				String eStampDets = request.getAttribute("eCode").toString();
				if (request.getAttribute("eStampPdfPath") != null)
					eStampPdfPath = request.getAttribute("eStampPdfPath").toString();

				if (eStampDets != null && !eStampDets.equalsIgnoreCase("") && !("").equalsIgnoreCase(eStampPdfPath)) {

					String[] eStampDetsArr = eStampDets.split("~");

					// insertion code
					boolean insertEstampMapDetls = false;
					insertEstampMapDetls = commonBo.insertEstampMappingDetls(eStampDetsArr[1].trim(), regForm, eStampPdfPath);

					if (insertEstampMapDetls) {
						//regForm.setRegInitEstampCode(eStampDetsArr[0].trim());
						// forward = "reginitConfirm";

						eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
						if (eCode != null && !("").equalsIgnoreCase(eCode)) {
							regForm.setRegInitEstampCode(eCode);
							// regForm.setRegInitEstampCode(null);
							regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
							DutyCalculationForm dutyForm = new DutyCalculationForm();
							dutyForm.setPropImage1DocumentName("");
							dutyForm.setEmptyFile("");
							forward = "reginitConfirm";
						} else {
							regForm.setRegInitEstampCode(null);
							forward = "failure";
						}

					} else {
						regForm.setRegInitEstampCode(null);
						forward = "failure";
					}
				} else {

					regForm.setRegInitEstampCode(null);
					forward = "failure";

				}

			}

		}
		if (request.getAttribute("noECode") != null) {
			String eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
			if (eCode != null && !("").equalsIgnoreCase(eCode)) {
				regForm.setRegInitEstampCode(eCode);
				// regForm.setRegInitEstampCode(null);
				DutyCalculationForm dutyForm = new DutyCalculationForm();
				dutyForm.setPropImage1DocumentName("");
				dutyForm.setEmptyFile("");
				forward = "reginitConfirm";
			} else {
				PropertiesFileReader proper = PropertiesFileReader.getInstance("resources.igrs");
				String[] eStampDetsNew = commonBo.getEstampTxnId(regForm.getHidnRegTxnId());
				String downloadPath = proper.getValue("igrs_upload_path");
				String eStampPdfPath = "";
				String eStampDetsCode = eStampDetsNew[0];

				System.out.println("eStampDetsNew  0 " + eStampDetsNew[0]);

				System.out.println("eStampDetsNew  1 " + eStampDetsNew[1]);

				eStampPdfPath = downloadPath + File.separator + "IGRS" + File.separator + eStampDetsCode + File.separator + "EStamp.PDF";

				// String eStampDets=request.getAttribute("eCode").toString();
				// if(request.getAttribute("eStampPdfPath")!=null)
				//eStampPdfPath=request.getAttribute("eStampPdfPath").toString()
				// ;

				// if(eStampDets!=null && !eStampDets.equalsIgnoreCase("") &&
				// !("").equalsIgnoreCase(eStampPdfPath)){

				// String[] eStampDetsArr=eStampDets.split("~");

				// insertion code
				boolean insertEstampMapDetls = false;
				insertEstampMapDetls = commonBo.insertEstampMappingDetls(eStampDetsNew[1], regForm, eStampPdfPath);

				if (insertEstampMapDetls) {
					// regForm.setRegInitEstampCode(eStampDetsArr[0].trim());
					// forward = "reginitConfirm";

					eCode = commonBo.getEstampCode(regForm.getHidnRegTxnId());
					if (eCode != null && !("").equalsIgnoreCase(eCode)) {
						regForm.setRegInitEstampCode(eCode);
						// regForm.setRegInitEstampCode(null);
						regForm.setDeedPageNo(commonBo.getMaxDeedPage(regForm.getDeedDraftId()));
						DutyCalculationForm dutyForm = new DutyCalculationForm();
						dutyForm.setPropImage1DocumentName("");
						dutyForm.setEmptyFile("");
						forward = "reginitConfirm";
					} else {
						regForm.setRegInitEstampCode(null);
						forward = "failure";
					}

				} else {
					regForm.setRegInitEstampCode(null);
					forward = "failure";
				}
				// forward = "reginitConfirm";

			}

			/*
			 * regForm.setRegInitEstampCode(eCode);
			 * regForm.setDeedPageNo(commonBo
			 * .getMaxDeedPage(regForm.getDeedDraftId())); forward =
			 * "reginitConfirm";
			 */

			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			forward = "reginitConfirm";
		}

		// final action after payment.
		if (RegInitConstant.FINAL_ACTION.equals(actionName)) {

			try {

				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
				regForm.setPostalCountry("1");
				regForm.setPostalState("");
				regForm.setPostalDistrict("");
				regForm.setHdnPostalAddress1(regForm.getPostalAddress1());

				// change payment flag to c here.

				String currDate = commonBo.getCurrDateTime();

				logger.debug("curr date 2 : " + currDate);

				regForm.setCurrDateTime(currDate);

				String msg = "";
				// added by Anuj for ref_gee_pay_check checkbox

				String regFeeCheck = (String) regForm.getCheckRegFeeBox();
				if ("on".equalsIgnoreCase(regFeeCheck)) {
					regFeeCheck = "Y";
				} else {
					regFeeCheck = "N";
				}
				if (Double.parseDouble(regForm.getTotalduty()) > 0) {

					String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
					boolean updatePaymentStatus = commonBo.insertTxnDetails(param1, regFeeCheck);

					logger.debug("payment status updated as c: " + updatePaymentStatus);

					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();

					else
						msg = RegInitConstant.ERROR_MSG;
				} else {

					boolean updatePaymentStatus = commonBo.insertTxnDetailsFinalActionWithRegFee(RegInitConstant.STATUS_FINAL_SCREEN, regForm.getHidnRegTxnId(), regFeeCheck);

					logger.debug("payment status updated as c: " + updatePaymentStatus);

					if (updatePaymentStatus)
						msg = regForm.getHidnRegTxnId().toString();

					else
						msg = RegInitConstant.ERROR_MSG;

					// msg=regForm.getHidnRegTxnId().toString();
				}

				regForm.setActionName(RegInitConstant.NO_ACTION);
				request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
				if ("Y".equalsIgnoreCase(regFeeCheck)) {
					RegCommonBO bo = new RegCommonBO();
					bo.getPaymentAmounts(regForm);
					forward = "regFeePageNPV";
				} else {
					forward = "success1";
				}
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";

			}
		}

		// final action after payment.
		if (RegInitConstant.FINAL_ACTION_FEE.equals(actionName)) {

			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
			regForm.setPostalCountry("1");
			regForm.setPostalState("");
			regForm.setPostalDistrict("");
			regForm.setHdnPostalAddress1(regForm.getPostalAddress1());

			// change payment flag to c here.

			String currDate = commonBo.getCurrDateTime();

			System.out.println("curr date 2 : " + currDate);

			regForm.setCurrDateTime(currDate);

			String msg = "";

			String param1[] = {RegInitConstant.PAYMENT_FLAG_COMPLETED, regForm.getHidnRegTxnId()};
			boolean updatePaymentStatus = commonBo.insertTxnDetailsFee(param1);

			logger.debug("fee payment status updated as c: " + updatePaymentStatus);

			if (updatePaymentStatus)
				msg = regForm.getHidnRegTxnId().toString();

			else
				msg = RegInitConstant.ERROR_MSG;

			regForm.setActionName(RegInitConstant.NO_ACTION);
			request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
			forward = "success1";

		}

		// COMPLETE TRANSACTION
		if (RegInitConstant.COMPLETE_APPLICATION_ACTION.equals(actionName)) {

			try {
				ArrayList list = commonBo.getAllUploadsCountAndSize(regForm.getHidnRegTxnId());

				boolean completeTransaction = commonBo.updateTransactionStatus(regForm, ((Integer) list.get(0)).toString(), ((Double) list.get(1)).toString());
				logger.debug("complete transactiooooon status: " + completeTransaction);
				if (completeTransaction) {
					/*
					 * request.setAttribute("label", "userSlotbook");
					 * request.setAttribute("modName", "Slot");
					 * request.setAttribute("from", "regInit");
					 * request.setAttribute("regInitId",
					 * regForm.getHidnRegTxnId());
					 */

					cancelAction(session, regForm);
					if (map != null) {
						if (!map.isEmpty())
							map.clear();
					}
					// count=0;
					// keyCount=0;
					// forward="welcome";

					//label=userSlotbook&modName=Slot&from=regInit&regInitId=<%=
					// request.getAttribute("regInitTxnId")%>

					forward = "welcome";
					// return mapping.findForward(forward);
				} else
					forward = "failure";
			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
				forward = "failure";
			}

			// forward = "success1";

		}

		// NEXT TO CONFIRMATION SCREEN
		if (request.getParameter("confirmation") != null || RegInitConstant.NEXT_TO_CONFIRM_ACTION.equals(actionName)) {

			regForm.setActionName("");
			/*
			 * String[]
			 * param=commonBo.getConsenterFlag(regForm.getHidnRegTxnId());
			 * regForm.setConsenterFlag(param[0]);
			 * regForm.setConsenterCount(Integer.parseInt(param[1]));
			 */

			if (regForm.getPropReqFlag().equalsIgnoreCase("Y") && regForm.getConsenterFlag().equalsIgnoreCase("Y")) {
				boolean updateStatus = commonBo.updateConsenterFlag(RegInitConstant.CONSENTER_IN_PROGRESS, regForm.getHidnRegTxnId(), "Y", regForm.getConsenterWithConsidFlag());
				if (updateStatus) {
					forward = "reginitConsenter";
					regForm.setListDto(new ArrayList());
					regForm.setRegDTO(new RegCompleteMakerDTO());
					regForm.setConsenterAddedCount(0);
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					commonDto.setState(commonBo.getState("1", languageLocale));
					commonDto.setDistrict(new ArrayList());
					commonDto.setRelationshipList(commonBo.getRelationshipList(null, languageLocale));
					return mapping.findForward(forward);
				} else {
					logger.debug("unable to update reg status for consenter.");
					forward = "failure";

				}
			} else {

				forward = "reginitConfirm";
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				commonBo.landConfirmationScreen(regForm, languageLocale, request);
				return mapping.findForward(forward);
			}

		}
		// following code for generating e code for 0 payable amount.
		if (RegInitConstant.GENERATE_ECODE_ZERO_AMNT_ACTION.equals(actionName) && request.getAttribute("eCode") == null) {

			// save purpose here
			// following code for updating purpose
			if (regForm.getPurpose() != null && !regForm.getPurpose().equalsIgnoreCase("")) {
				boolean updatePurpose = commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(), regForm.getPurpose());
				if (updatePurpose) {
					regForm.setPurposeAddedFlag("Y");
					logger.debug("purpose updated before generating e code for zero amount.");
					regForm.setActionName("");
					// redirect to estamping for ecode generation here.
					request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
					request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
					request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
					request.setAttribute("eStampRegFuncId", "023");
					request.setAttribute("eStampRegPurpose", regForm.getPurpose());
					request.setAttribute("sourceModFlag", "PVN");
					forward = "reginitEstamp";
					request.setAttribute("deedId", regForm.getDeedID());
					request.setAttribute("instId", regForm.getInstID());
					return mapping.findForward(forward);

				} else {
					forward = "failure";
					logger.debug("purpose not updated before generating e code for zero amount.");

				}
			}

		}
		if (request.getParameter("label") != null) {

			if (request.getParameter("label").equalsIgnoreCase("displayConsenterDuties")) {

				commonBo.getConsenterDutiesView(regForm, languageLocale);

				forward = "displayConsenterDuties";
			}

			if (request.getParameter("label").equalsIgnoreCase("displayConsenter")) {

				if (request.getParameter("fromPage") != null && request.getParameter("fromPage").toString().equalsIgnoreCase("consenterDetls")) {
					regForm.setConfirmationFlag("00");
				} else {
					regForm.setConfirmationFlag("01");
				}

				regForm.setPartyModifyFlag(0);
				commonBo.openConsenterView(request, regForm, languageLocale);

				forward = "displayConsenterDetls";
			}

			if (request.getParameter("label").equalsIgnoreCase("displayParty")) {

				regForm.setPartyModifyFlag(0);
				regForm.setOwnerModifyFlag(0);
				commonBo.openPartyView(request, regForm, languageLocale);

				forward = "displayRegDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayProperty")) {

				// String propertyId=request.getParameter("key");

				String confirmationFlag = null;
				confirmationFlag = (String) request.getParameter("confirmationFlag");
				if (confirmationFlag != null && confirmationFlag != "") {
					if (confirmationFlag.equalsIgnoreCase("false")) {
						regForm.setConfirmationFlag("00");
					} else {
						regForm.setConfirmationFlag("01");
					}
				} else {
					regForm.setConfirmationFlag("01");
				}

				String propertyId = "";
				if (request.getParameter("key") != null)
					propertyId = request.getParameter("key");
				else if (request.getAttribute("key") != null)
					propertyId = (String) request.getAttribute("key");

				regForm.setPropertyId(propertyId);
				regForm.setMapPropertyTransPartyDisp
				// (commonBo.getPropertyDetsForViewConfirmNonProp(regForm.
				// getHidnRegTxnId(), propertyId, languageLocale));
				(commonBo.getPropertyDetsForViewConfirm(regForm.getHidnRegTxnId(), propertyId, languageLocale));

				// regForm.setConfirmationFlag("01");

				forward = "propertyView";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayExtra")) {

				commonBo.getExtraDetls(regForm, languageLocale);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				logger.debug("request deed ---->" + request.getAttribute("deedId"));
				regForm.setExtraDetlsModifyFlag(0);
				regForm.setCallingAction("nonPropRegInit.do?TRFS=NGI");
				forward = "displayExtraDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("displayParticular")) {

				String particularId = request.getParameter("key");
				regForm.setParticularTxnId(particularId);
				commonBo.getParticularDetails(regForm);
				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("instId", regForm.getInstID());
				regForm.setExtraDetlsModifyFlag(0);
				logger.debug("request deed ---->" + request.getAttribute("deedId"));

				forward = "displayParticularDetls";
			}
			if (request.getParameter("label").equalsIgnoreCase("fromAdjudication")) {
				forward = "searchAdju";
			}
		}
		// END
		// following code for skipping applicant details page in case of
		// multiple properties.
		if (request.getParameter("modName") != null) {/*
													 * if(request.getParameter("modName"
													 * ).equalsIgnoreCase(
													 * "RegistrationNonProperty"
													 * )&&request.getAttribute(
													 * "regFormDashboard"
													 * )==null){if(regForm.
													 * getIsMultiplePropsFlag
													 * ()==1) { //FOLLOWING CODE
													 * FOR INSERTING APPLICANT
													 * DETAILS IN HASHMAP
													 * map=regForm
													 * .getMapTransactingParties
													 * ();
													 * 
													 * regForm.
													 * setMapTransactingParties
													 * (commonBo
													 * .insertApplicantDetsInMap
													 * (map,key,
													 * regForm.getHidnRegTxnId
													 * (),regForm));regForm.
													 * setMapTransPartyDbInsertion
													 * (commonBo.
													 * insertApplicantDetsInMap
													 * (map,key,
													 * regForm.getHidnRegTxnId
													 * (),regForm));
													 * //keyCount=regForm
													 * .getMapTransactingParties
													 * ().size();
													 * 
													 * String[]
													 * deedInstArray=commonBo
													 * .getDeedInstId
													 * (regForm.getHidnRegTxnId
													 * ());
													 * 
													 * 
													 * regForm.setDeedID(Integer.
													 * parseInt
													 * (deedInstArray[0].
													 * trim()));
													 * regForm.setInstID
													 * (Integer.
													 * parseInt(deedInstArray
													 * [1].trim()));
													 * if(deedInstArray
													 * [2].trim().equals("-")){
													 * regForm.setExmpID("");
													 * regForm
													 * .setHdnExAmount("");
													 * }else{regForm.setExmpID(
													 * deedInstArray[2].trim());
													 * regForm
													 * .setHdnExAmount(deedInstArray
													 * [2].trim()); }
													 * 
													 * commonDto.setPartyType(
													 * commonBo
													 * .getPartyType(regForm
													 * .getDeedID()));
													 * commonDto.
													 * setAppTypeTrns(commonBo
													 * .getAppType());
													 * commonDto.
													 * setCountryTrns(commonBo
													 * .getCountry());
													 * commonDto.
													 * setIndcountryTrns
													 * (commonBo.getCountry());
													 * commonDto
													 * .setIdProfTrns(commonBo
													 * .getIdProf());
													 * regForm.setDeedId
													 * (Integer.
													 * toString(regForm.
													 * getDeedID()));
													 * regForm.setHidnUserId
													 * (userId);
													 * 
													 * //FOLLOWING CODE FOR
													 * INSERTING PROPERTY
													 * DETAILS THROUGH PV INTO
													 * DB boolean
													 * multiplePropDetlsInserted
													 * ;
													 * multiplePropDetlsInserted
													 * =commonBo.
													 * insrtMultiplePropDetls
													 * (regForm);logger.debug(
													 * "multiple property details insertion status---------->"
													 * +
													 * multiplePropDetlsInserted
													 * );
													 * 
													 * //FOLLOWING CODE FOR
													 * FETCHING APPLICANT ROLE
													 * ID. //disabling applicant
													 * role in multiple
													 * properties. String
													 * applicantRoleId
													 * =commonBo.getApplicantRoleId
													 * (
													 * regForm.getHidnRegTxnId()
													 * );logger.debug(
													 * "applicant role id---------->"
													 * +applicantRoleId);
													 * regForm
													 * .setApplicantRoleId
													 * (Integer
													 * .parseInt(applicantRoleId
													 * )); // int
													 * totalShare=commonBo
													 * .getShareOfPropList
													 * (regForm
													 * .getHidnRegTxnId());
													 * 
													 * 
													 * regForm.setTotalShareOfProp
													 * (totalShare);
													 * 
													 * forward
													 * ="transactingParty"; } }
													 */
		}
		// edited by shruti because deed id is hardcoded as of now
		commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(), regForm.getInstID(), languageLocale));

		if (RegInitConstant.DECLARE_SHARE_CHECK_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			// forward ="success";
			forward = regForm.getJspName();

		}

		if (request.getParameter("dms") != null) {
			if (request.getParameter("dms").equalsIgnoreCase("retrieval")) {

				if (request.getParameter("path") != null) {

					String partyType = "";
					String filePath = request.getParameter("path").toString();
					logger.debug("retrieval path-->" + filePath);

					int indexOfDot = filePath.lastIndexOf(".");
					// int totLen=filePath.length();
					// dmsUtil.readImage(filePath);
					String key = request.getParameter("key");

					String fileName = filePath.substring(indexOfDot - 2, indexOfDot) + ".";

					if (request.getParameter("roleType") != null) {
						partyType = request.getParameter("roleType").toString();
						regForm.setPartyType(partyType);
						logger.debug("role type---------->" + partyType);
						RegCommonDTO dtoObj = (RegCommonDTO) regForm.getMapTransactingPartiesDisp().get(key);

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CERTIFICATE)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getDocContentsTrns());

						}
						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PHOTO_PROOF)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getU2DocContentsTrns());

						}

						int roleId = Integer.parseInt(partyType);
						String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
						int claimantFlag = Integer.parseInt(claimantArr[0].trim());

						// BELOW CODE FOR EXECUTANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU3DocContentsTrns());

							}

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU4DocContentsTrns());

							}

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU10DocContentsTrns());

							}

						}
						// BELOW CODE FOR EXECUTANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU5DocContentsTrns());

							}

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU6DocContentsTrns());

							}
							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU7DocContentsTrns());

							}

						}

						// BELOW CODE FOR CLAIMANT
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU8DocContentsTrns());

							}

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU11DocContentsTrns());

							}

						}

						// BELOW CODE FOR CLAIMANT POA HOLDER
						if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

							if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)) {
								DMSUtility.downloadDocument(response, filePath, dtoObj.getU9DocContentsTrns());

							}

						}

						forward = "displayRegDetls";
						request.setAttribute("deedId", regForm.getDeedID());
						request.setAttribute("instId", regForm.getInstID());
						request.setAttribute("roleId", partyType);
					} else {

						RegCompletDTO dtoObj = (RegCompletDTO) regForm.getMapPropertyTransPartyDisp().get(key);

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_1)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage1DocContents());

						}

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_2)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage2DocContents());

						}

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_3)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropImage3DocContents());

						}

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropMapDocContents());

						}

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropRinDocContents());

						}

						if (fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_KHASRA)) {
							DMSUtility.downloadDocument(response, filePath, dtoObj.getPropKhasraDocContents());

						}

						forward = "propertyView";
					}

				}

			}
			if (request.getParameter("dms").equalsIgnoreCase("retrievalLive")) {

				if (request.getParameter("retrievalType") != null && request.getParameter("retrievalType").toString().equalsIgnoreCase("1")) {

					// below for applicant screen
					if (request.getParameter("appFlag") != null && request.getParameter("appFlag").toString().equalsIgnoreCase("Y")) {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
							DMSUtility.downloadDocument(response, regForm.getFilePath(), regForm.getDocContents());

						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
							DMSUtility.downloadDocument(response, regForm.getU2FilePath(), regForm.getU2DocContents());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
							DMSUtility.downloadDocument(response, regForm.getU3FilePath(), regForm.getU3DocContents());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
							DMSUtility.downloadDocument(response, regForm.getU4FilePath(), regForm.getU4DocContents());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
							DMSUtility.downloadDocument(response, regForm.getU10FilePath(), regForm.getU10DocContents());

						}

						// }
						// BELOW CODE FOR EXECUTANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
							DMSUtility.downloadDocument(response, regForm.getU5FilePath(), regForm.getU5DocContents());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
							DMSUtility.downloadDocument(response, regForm.getU6FilePath(), regForm.getU6DocContents());

						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
							DMSUtility.downloadDocument(response, regForm.getU7FilePath(), regForm.getU7DocContents());

						}

						// }

						// BELOW CODE FOR CLAIMANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
							DMSUtility.downloadDocument(response, regForm.getU8FilePath(), regForm.getU8DocContents());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
							DMSUtility.downloadDocument(response, regForm.getU11FilePath(), regForm.getU11DocContents());

						}

						// }

						// BELOW CODE FOR CLAIMANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
							DMSUtility.downloadDocument(response, regForm.getU9FilePath(), regForm.getU9DocContents());

						}

						// }

						forward = "success";

					}
					// below for transacting party screen
					if (request.getParameter("appFlag") != null && (request.getParameter("appFlag").toString().equalsIgnoreCase("N") || request.getParameter("appFlag").toString().equalsIgnoreCase("E"))) {
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("1")) {
							DMSUtility.downloadDocument(response, regForm.getFilePathTrns(), regForm.getDocContentsTrns());

						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("2")) {
							DMSUtility.downloadDocument(response, regForm.getU2FilePathTrns(), regForm.getU2DocContentsTrns());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("3")) {
							DMSUtility.downloadDocument(response, regForm.getU3FilePathTrns(), regForm.getU3DocContentsTrns());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("4")) {
							DMSUtility.downloadDocument(response, regForm.getU4FilePathTrns(), regForm.getU4DocContentsTrns());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("10")) {
							DMSUtility.downloadDocument(response, regForm.getU10FilePathTrns(), regForm.getU10DocContentsTrns());

						}

						// }
						// BELOW CODE FOR EXECUTANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("5")) {
							DMSUtility.downloadDocument(response, regForm.getU5FilePathTrns(), regForm.getU5DocContentsTrns());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("6")) {
							DMSUtility.downloadDocument(response, regForm.getU6FilePathTrns(), regForm.getU6DocContentsTrns());

						}
						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("7")) {
							DMSUtility.downloadDocument(response, regForm.getU7FilePathTrns(), regForm.getU7DocContentsTrns());

						}

						// }

						// BELOW CODE FOR CLAIMANT
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("8")) {
							DMSUtility.downloadDocument(response, regForm.getU8FilePathTrns(), regForm.getU8DocContentsTrns());

						}

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("11")) {
							DMSUtility.downloadDocument(response, regForm.getU11FilePathTrns(), regForm.getU11DocContentsTrns());

						}

						// }

						// BELOW CODE FOR CLAIMANT POA HOLDER
						// if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
						// {

						if (request.getParameter("retrievalId") != null && request.getParameter("retrievalId").toString().equalsIgnoreCase("9")) {
							DMSUtility.downloadDocument(response, regForm.getU9FilePathTrns(), regForm.getU9DocContentsTrns());

						}

						// }
						if (request.getParameter("appFlag").toString().equalsIgnoreCase("N")) {
							forward = "transactingParty";
						} else {
							forward = "displayRegDetls";
						}

					}

				}

			}
		}
		if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName) || RegInitConstant.PRINT_ACTION_FINAL.equalsIgnoreCase(actionName))

		{
			// regForm=commonBo.printToPdf(regForm, request, response,
			// languageLocale);
			regForm.setActionName("");
			if (RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName)) {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "N");
				regForm.setRegInitEstampCode(null);
			} else {
				commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");
			}

			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			dutyForm.setEmptyFile("");
			// forward="reginitConfirm";
			forward = regForm.getJspName();

		}
		if (RegInitConstant.PRINT_PAYMENT_ACTION.equalsIgnoreCase(actionName))

		{
			// regForm=commonBo.printToPdf(regForm, request, response,
			// languageLocale);

			commonBo.printToPdfJasper(regForm, request, response, languageLocale, "Y");

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = regForm.getJspName();

		}

		if (RegInitConstant.MODIFY_PARTY_ACTION.equalsIgnoreCase(actionName))

		{
			actionName = "";
			regForm.setInddistrictTrns("");
			regForm.setAuthPerDistrictTrns("");
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonBo.getPartyDetsForViewConfirmModify(regForm, languageLocale);

			commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
			// commonDto.setCategoryList(commonBo.getCategoryList());
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));

			// regForm.setFnameTrns("Roopam");

			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
																					// id
																					// passed
																					// is
																					// for
																					// MP
			// to set tehsil drop down as per selected district

			if (regForm.getInddistrictTrns() != null && !regForm.getInddistrictTrns().isEmpty()) {
				commonDto.setTehsilTrns(commonBo.getTehsildrop(regForm.getInddistrictTrns(), languageLocale));
			} else {
				commonDto.setTehsilTrns(commonBo.getTehsildrop(regForm.getDistrictTrns(), languageLocale));
			}

			if (regForm.getAuthPerDistrictTrns() != null && !regForm.getAuthPerDistrictTrns().isEmpty()) {
				commonDto.setTehsilTrns1(commonBo.getTehsildrop1(regForm.getAuthPerDistrictTrns()));
			}

			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(), languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(), languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());

			if (regForm.getCommonDeed() != 1) {
				String[] claimantArr = commonBo.getClaimantFlag(regForm.getPartyType());
				regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
			}
			forward = "displayRegDetls";

		}
		if (RegInitConstant.SAVE_PARTY_ACTION.equalsIgnoreCase(actionName))

		{
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);
			String path = "";

			try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

				path = pr.getValue("upload.location");
			} catch (Exception e) {
				logger.debug("exception in uploadFile : " + e);
				logger.debug(e.getMessage(), e);
			}

			boolean checkAdditionalUploads = true;
			path = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_PARTY + regForm.getPartyTxnId() + "/";
			try {

				// By Mohit
				File f = new File(path);
				FileUtils.cleanDirectory(f);
				// commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(),
				// regForm.getPartyTxnId());
			} catch (Exception e) {
				request.setAttribute("error", "Unable to clean directory.");
				logger.debug(e.getMessage(), e);
				// checkAdditionalUploads = true;
			} finally {
				try {
					// By Mohit
					// File f = new File(path);
					// FileUtils.cleanDirectory(f);
					checkAdditionalUploads = true;
					commonBo.deleteAllRemovedUploads(regForm.getHidnRegTxnId(), regForm.getPartyTxnId());
				} catch (Exception e) {
					request.setAttribute("error", "Unable to delete records.");
					checkAdditionalUploads = false;
					logger.debug(e.getMessage(), e);
				}
			}
			// below write code for saving modified party details
			if (checkAdditionalUploads) {
				ArrayList<CommonDTO> listDto = regForm.getListDto();
				if (listDto != null && listDto.size() > 0) {

					// checkAdditionalUploads =
					// commonBo.deleteAllRemovedUploads(
					// regForm.getHidnRegTxnId(),regForm.getPartyTxnId());
					if (checkAdditionalUploads) {
						for (int i = 0; i < listDto.size(); i++) {
							CommonDTO dto = listDto.get(i);
							dto.setDocumentName(commonBo.getNewFileName(dto.getDocumentName(), Integer.toString(i)));
							String filepath = action.uploadFile(regForm.getHidnRegTxnId(), dto.getDocContents(), regForm.getPartyTxnId(), "01", dto.getDocumentName());
							if (filepath != null) {
								checkAdditionalUploads = true;
								dto.setDocumentPath(filepath);
							} else {
								checkAdditionalUploads = false;
								break;
							}
						}

					}
					if (checkAdditionalUploads) {
						regForm.setPartyRoleTypeId(regForm.getPartyTxnId());
						checkAdditionalUploads = commonBo.insertAdditionalUploads(regForm);

					}

				} else {

					checkAdditionalUploads = true;
				}
			}
			// mohit

			// below write code for saving modified party details

			if (checkAdditionalUploads) {
				boolean allUploadsUpdated = false;
				boolean allDetailsUpdated = false;

				String filePath;
				String filePathPhotoProof;
				String filePathNotAffExec;
				String filePathExecPhoto;
				String filePathNotAffAttrn;
				String filePathAttrnPhoto;
				String filePathClaimPhoto;
				String filePathPanForm60;

				regForm.setU2FilePathTrns("");
				regForm.setU3FilePathTrns("");
				regForm.setU10FilePathTrns("");
				regForm.setU6FilePathTrns("");
				regForm.setU11FilePathTrns("");
				regForm.setU9FilePathTrns("");
				regForm.setFilePathTrns("");

				int roleId = 0;

				if (regForm.getPartyTypeTrns() != null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")) {
					roleId = Integer.parseInt(regForm.getPartyTypeTrns());
				}

				String[] claimantArr = commonBo.getClaimantFlag(Integer.toString(roleId));
				int claimantFlag = Integer.parseInt(claimantArr[0].trim());

				if (!regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.GOVT_OFFCL_ID)) {
					allUploadsUpdated = true; // Added By Gulrej on 15th June,
												// 2017

					if (regForm.getPropReqFlag().equalsIgnoreCase("Y")) {

						// filePathPhotoProof=action.uploadFile(regForm.
						// getHidnRegTxnId(),regForm.getU2DocContentsTrns(),
						//regForm.getPartyTxnId(),regForm.getU2PartyOrPropTrns()
						// ,regForm.getU2UploadTypeTrns());

						if (!regForm.getU2DocumentNameTrns().equalsIgnoreCase("")) {
							filePathPhotoProof = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU2PartyOrPropTrns(), regForm.getU2UploadTypeTrns());
						} else {
							filePathPhotoProof = "";

						}

						if (filePathPhotoProof != null) {
							regForm.setU2FilePathTrns(filePathPhotoProof);
							// BELOW CODE FOR EXECUTANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_1) {

								if (regForm.getU3DocumentNameTrns() != null && !regForm.getU3DocumentNameTrns().equalsIgnoreCase("")) {
									filePathNotAffExec = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU3DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU3PartyOrPropTrns(), regForm.getU3UploadTypeTrns());
								} else {
									filePathNotAffExec = "";
								}
								if (filePathNotAffExec != null) {
									regForm.setU3FilePathTrns(filePathNotAffExec);

									//filePathExecPhoto=action.uploadFile(regForm
									// .getHidnRegTxnId(),regForm.
									// getU4DocContentsTrns(),
									// regForm.getPartyTxnId(),regForm.
									// getU4PartyOrPropTrns
									// (),regForm.getU4UploadTypeTrns());

									// if(filePathExecPhoto!=null){
									//regForm.setU4FilePathTrns(filePathExecPhoto
									// );

									if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
																															// individual
									{
										if (!regForm.getListIDTrns().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

											filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());

											if (filePathPanForm60 != null) {
												regForm.setU10FilePathTrns(filePathPanForm60);
												allUploadsUpdated = true;
											} else {
												allUploadsUpdated = false;
											}

										} else {
											allUploadsUpdated = true;
										}

									} else { // for organization

										/*
										 * filePathPanForm60=action.uploadFile(regForm
										 * .getHidnRegTxnId(),regForm.
										 * getU10DocContentsTrns(),
										 * regForm.getPartyTxnId
										 * (),regForm.getU10PartyOrPropTrns
										 * (),regForm.getU10UploadTypeTrns());
										 * 
										 * if(filePathPanForm60!=null){
										 * regForm.setU10FilePathTrns
										 * (filePathPanForm60);
										 * allUploadsUpdated=true; }else{
										 * allUploadsUpdated=false; }
										 */

										if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU10DocumentNameTrns() != null && !regForm.getU10DocumentNameTrns().equalsIgnoreCase("")))) {

											filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU10DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU10PartyOrPropTrns(), regForm.getU10UploadTypeTrns());

											if (filePathPanForm60 != null) {
												regForm.setU10FilePathTrns(filePathPanForm60);
												allUploadsUpdated = true;
											} else {
												allUploadsUpdated = false;
											}

										} else {
											allUploadsUpdated = true;
										}

									}
									// }else{
									// allUploadsUpdated=false;
									// }
								} else {
									allUploadsUpdated = false;
								}
							}
							// BELOW CODE FOR EXECUTANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_2) {

								// filePathExecPhoto=action.uploadFile(regForm.
								// getHidnRegTxnId
								// (),regForm.getU7DocContentsTrns(),
								// regForm.getPartyTxnId(),regForm.
								// getU7PartyOrPropTrns
								// (),regForm.getU7UploadTypeTrns());

								// if(filePathExecPhoto!=null)
								// {
								// regForm.setU7FilePathTrns(filePathExecPhoto);
								/*
								 * filePathNotAffAttrn=action.uploadFile(regForm.
								 * getHidnRegTxnId
								 * (),regForm.getU5DocContentsTrns(),
								 * regForm.getPartyTxnId
								 * (),regForm.getU5PartyOrPropTrns
								 * (),regForm.getU5UploadTypeTrns());
								 * 
								 * if(filePathNotAffAttrn!=null) {
								 * regForm.setU5FilePathTrns
								 * (filePathNotAffAttrn);
								 */

								filePathAttrnPhoto = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU6DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU6PartyOrPropTrns(), regForm.getU6UploadTypeTrns());

								if (filePathAttrnPhoto != null) {
									regForm.setU6FilePathTrns(filePathAttrnPhoto);

									allUploadsUpdated = true;
								} else {
									allUploadsUpdated = false;
								}
								// }

								// }

							}
							// BELOW CODE FOR CLAIMANT
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_3) {

								// filePathClaimPhoto=action.uploadFile(regForm.
								// getHidnRegTxnId
								// (),regForm.getU8DocContentsTrns(),
								// regForm.getPartyTxnId(),regForm.
								// getU8PartyOrPropTrns
								// (),regForm.getU8UploadTypeTrns());

								// if(filePathClaimPhoto!=null){
								//regForm.setU8FilePathTrns(filePathClaimPhoto);

								if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) // for
																														// individual
								{
									if (!regForm.getListID().equalsIgnoreCase("4") && (regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

										filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());

										if (filePathPanForm60 != null) {
											regForm.setU11FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}

									} else {
										allUploadsUpdated = true;
									}

								} else { // for organization
									/*
									 * filePathPanForm60=action.uploadFile(regForm
									 * .getHidnRegTxnId(),regForm.
									 * getU11DocContentsTrns(),
									 * regForm.getPartyTxnId
									 * (),regForm.getU11PartyOrPropTrns
									 * (),regForm.getU11UploadTypeTrns());
									 * 
									 * if(filePathPanForm60!=null){
									 * regForm.setU11FilePathTrns
									 * (filePathPanForm60);
									 * allUploadsUpdated=true; }else{
									 * allUploadsUpdated=false; }
									 */

									if ((regForm.getTransCheck().equalsIgnoreCase("Y") || (regForm.getU11DocumentNameTrns() != null && !regForm.getU11DocumentNameTrns().equalsIgnoreCase("")))) {

										filePathPanForm60 = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU11DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU11PartyOrPropTrns(), regForm.getU11UploadTypeTrns());

										if (filePathPanForm60 != null) {
											regForm.setU11FilePathTrns(filePathPanForm60);
											allUploadsUpdated = true;
										} else {
											allUploadsUpdated = false;
										}

									} else {
										allUploadsUpdated = true;
									}

								}

								// }else{
								// allUploadsUpdated=false;
								// }
							}

							// BELOW CODE FOR CLAIMANT POA HOLDER
							if (claimantFlag == RegInitConstant.CLAIMANT_FLAG_4) {

								filePathAttrnPhoto = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU9DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU9PartyOrPropTrns(), regForm.getU9UploadTypeTrns());

								if (filePathAttrnPhoto != null) {
									regForm.setU9FilePathTrns(filePathAttrnPhoto);
									allUploadsUpdated = true;

								} else {
									allUploadsUpdated = false;
								}
							}

							if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
								if (regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
									filePath = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContentsTrns(), regForm.getPartyTxnId(), regForm.getPartyOrPropTrns(), regForm.getUploadTypeTrns());
									if (filePath != null) {
										regForm.setFilePathTrns(filePath);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}
								} else {
									allUploadsUpdated = true;
								}
							}
						}

					} else {
						// filePathPhotoProof=action.uploadFile(regForm.
						// getHidnRegTxnId(),regForm.getU2DocContentsTrns(),
						//regForm.getPartyTxnId(),regForm.getU2PartyOrPropTrns()
						// ,regForm.getU2UploadTypeTrns());
						if (regForm.getU2DocumentNameTrns() != null && !regForm.getU2DocumentNameTrns().equalsIgnoreCase("")) {
							filePathPhotoProof = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getU2DocContentsTrns(), regForm.getPartyTxnId(), regForm.getU2PartyOrPropTrns(), regForm.getU2UploadTypeTrns());
						} else {
							filePathPhotoProof = "";

						}
						if (filePathPhotoProof != null) {
							regForm.setU2FilePathTrns(filePathPhotoProof);

							if (regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)) {
								if (regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")) {
									filePath = action.uploadFile(regForm.getHidnRegTxnId(), regForm.getDocContentsTrns(), regForm.getPartyTxnId(), regForm.getPartyOrPropTrns(), regForm.getUploadTypeTrns());
									if (filePath != null) {
										regForm.setFilePathTrns(filePath);
										allUploadsUpdated = true;
									} else {
										allUploadsUpdated = false;
									}
								} else {
									allUploadsUpdated = true;
								}

							} else {
								allUploadsUpdated = true;
							}

						} else {
							allUploadsUpdated = false;
						}

					}

				} else {
					allUploadsUpdated = true;
				}

				if (allUploadsUpdated) {

					allDetailsUpdated = commonBo.updateTransPartyDetails(regForm);
					logger.debug("transacting party details updation status---->" + allDetailsUpdated);
				}

				request.setAttribute("deedId", regForm.getDeedID());
				request.setAttribute("roleId", regForm.getPartyType());
				request.setAttribute("instId", regForm.getInstID());

			} else {
				request.setAttribute("error", "Unable To Save the Document");
			}

			forward = "displayRegDetls";
			commonBo.openPartyView(request, regForm, languageLocale);
			regForm.setPartyModifyFlag(0);
			regForm.setOwnerModifyFlag(0);

		}
		if (RegInitConstant.SUBMIT_FORM_ACTION.equalsIgnoreCase(actionName))

		{
			logger.debug("category value----------->" + regForm.getIndCategoryTrns());
			regForm.setActionName(RegInitConstant.NO_ACTION);
			commonDto.setDistrictTrns(commonBo.getDistrict("1", languageLocale));// state
																					// id
																					// passed
																					// is
																					// for
																					// MP
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			regForm.setPartyModifyFlag(1);
			regForm.setOwnerModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			request.setAttribute("roleId", regForm.getPartyType());
			forward = regForm.getJspName();

		}

		if (RegInitConstant.MODIFY_PROPERTY_ACTION.equalsIgnoreCase(actionName))

		{

			request.setAttribute("fromAdju", regForm.getFromAdjudication());

			regForm.setPropertyModifyFlag(1);

			forward = "modifyPropDetls";
			request.setAttribute("regInitForm", regForm);

		}
		if (RegInitConstant.MODIFY_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName))

		{
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setIndstate(commonBo.getState("1", languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict("1", languageLocale));

			if (regForm.getPoaShareDebenture().equalsIgnoreCase("-")) {
				regForm.setPoaShareDebenture("");
			}
			if (regForm.getIndDisabilityDescArb().equalsIgnoreCase("-")) {
				regForm.setIndDisabilityDescArb("");
			}
			if (regForm.getMnameArb().equalsIgnoreCase("-")) {
				regForm.setMnameArb("");
			}
			if (regForm.getSpouseNameArb().equalsIgnoreCase("-")) {
				regForm.setSpouseNameArb("");
			}
			if (regForm.getMotherNameArb().equalsIgnoreCase("-")) {
				regForm.setMotherNameArb("");
			}
			if (regForm.getIndphnoArb().equalsIgnoreCase("-")) {
				regForm.setIndphnoArb("");
			}
			if (regForm.getEmailIDArb().equalsIgnoreCase("-")) {
				regForm.setEmailIDArb("");
			}
			if (regForm.getContriProp().equalsIgnoreCase("-")) {
				regForm.setContriProp("");
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);

			regForm.setExtraDetlsModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			regForm.setCallingAction("nonPropRegInit.do?TRFS=NGI");
			forward = "displayExtraDetls";

		}
		if (RegInitConstant.SAVE_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified bank details
			boolean allDetailsUpdated = false;

			allDetailsUpdated = commonBo.updateBankDetails(regForm);
			logger.debug("bank details updation status---->" + allDetailsUpdated);

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setCallingAction("regInit.do");
			// forward="reginitConfirm";
			commonBo.getExtraDetls(regForm, languageLocale);
			forward = "displayExtraDetls";

		}
		if (RegInitConstant.EXTRA_DETLS_NO_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setCallingAction("nonPropRegInit.do?TRFS=NGI");
			// regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified extra details
			// boolean allDetailsUpdated=false;

			// allDetailsUpdated = commonBo.updateBankDetails(regForm);
			//logger.debug("extra details updation status---->"+allDetailsUpdated
			// );

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setCallingAction("regInit.do");
			forward = "displayExtraDetls";

		}
		if (RegInitConstant.MODIFY_PARTICULAR_DETLS_ACTION.equalsIgnoreCase(actionName))

		{

			if (regForm.getParticularName().equalsIgnoreCase("-")) {
				regForm.setParticularName("");
			}
			if (regForm.getParticularDesc().equalsIgnoreCase("-")) {
				regForm.setParticularDesc("");
			}

			regForm.setActionName(RegInitConstant.NO_ACTION);

			regForm.setExtraDetlsModifyFlag(1);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			forward = "displayParticularDetls";

		}
		// modified by Shreeraj
		if (RegInitConstant.SAVE_PARTICULAR_DETLS_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setExtraDetlsModifyFlag(0);
			// below write code for saving modified bank details
			boolean allDetailsUpdated = false;

			allDetailsUpdated = commonBo.updateParticularDetails(regForm);
			logger.debug("particular details updation status---->" + allDetailsUpdated);

			ArrayList particularIdList = commonBo.getParticularList(regForm.getHidnRegTxnId());
			regForm.setPropListNonPropDeed(particularIdList);

			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			// regForm.setCallingAction("regInit.do");
			// forward=RegInitConstant.REG_INIT_CONFIRM_PAGE;
			commonBo.getParticularDetails(regForm);
			// request.setAttribute("deedId",regForm.getDeedID());

			//logger.debug("request deed ---->"+request.getAttribute("deedId"));

			forward = "displayParticularDetls";

		}
		if (RegInitConstant.BACK_TO_PARTICULAR_DISP_ACTION.equalsIgnoreCase(actionName)) {
			regForm.setActionName(RegInitConstant.NO_ACTION);
			regForm.setExtraDetlsModifyFlag(0);
			request.setAttribute("deedId", regForm.getDeedID());
			request.setAttribute("instId", regForm.getInstID());
			commonBo.getParticularDetails(regForm);
			forward = "displayParticularDetls";

		}

		// added by Shreeraj
		if (RegInitConstant.SUBMIT_ADJU_NON_PROP_ACTION.equals(actionName)) {
			// RegCommonDTO commonDto =new RegCommonDTO();
			// commonDto.ge
			// regForm.getStampDuty()
			String currDate = commonBo.getCurrDateTime();

			System.out.println("curr date 2 : " + currDate);

			regForm.setCurrDateTime(currDate);
			// if(regForm.getStampManually().equalsIgnoreCase("Y")){
			// reg txn status set 10 here

			boolean updateTxnStatus = commonBo.insertTxnDetailsFinalAction(RegInitConstant.STATUS_COMPLETE, regForm.getHidnRegTxnId());

			logger.debug("txn status updated as 10 after complete adju payment: " + updateTxnStatus);
			if (!updateTxnStatus) {
				forward = "failure";
				return mapping.findForward(forward);
			}
			/*
			 * } else { boolean
			 * insertStatus=commonBo.insertAdjuDutiesSys(regForm); }
			 */

			forward = "success1";
		}

		if (RegInitConstant.ADJU_COMPLETED.equals(actionName)) {
			forward = "welcome";
		}
		if (RegInitConstant.POSTAL_COUNTRY_CHANGE.equals(actionName)) {

			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			regForm.setPostalState("");
			regForm.setPostalDistrict("");

			forward = "success1";
		}
		if (RegInitConstant.POSTAL_STATE_CHANGE.equals(actionName)) {

			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

			forward = "success1";
		}
		// END

		// FROM DASHBOARD
		if (request.getParameter("regStatus") != null) {
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_APP_SAVED)) {
				forward = "transactingParty";
			}

			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_BANK)) {
				regForm.setCountry("1");
				regForm.setCountryName("INDIA");
				regForm.setStatename("1");
				regForm.setStatenameName("MADHYA PRADESH");
				regForm.setAuthPerCountry("1");
				regForm.setAuthPerCountryName("INDIA");
				regForm.setAuthPerStatename("1");
				regForm.setAuthPerStatenameName("MADHYA PRADESH");
				regForm.setIndcountry("1");
				regForm.setIndcountryName("INDIA");
				regForm.setIndstatename("1");
				regForm.setIndstatenameName("MADHYA PRADESH");
				regForm.setIndcountryArb("1");
				regForm.setIndstatenameArb("1");

				if (regForm.getCountry() != null && !regForm.getCountry().equalsIgnoreCase("")) {
					commonDto.setState(commonBo.getState(regForm.getCountry(), languageLocale));
					forward = "success";
				} else {
					commonDto.setState(new ArrayList());
				}
				// for getting organization district list
				if (regForm.getStatename() != null && !regForm.getStatename().equalsIgnoreCase("")) {
					commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(), languageLocale));
					forward = "success";
				} else {
					commonDto.setDistrict(new ArrayList());
				}
				// for getting individual state list
				if (regForm.getIndcountry() != null && !regForm.getIndcountry().equalsIgnoreCase("")) {
					commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(), languageLocale));
					forward = "success";
				} else {
					commonDto.setIndstate(new ArrayList());
				}
				// for getting individual district list
				if (regForm.getIndstatename() != null && !regForm.getIndstatename().equalsIgnoreCase("")) {
					commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(), languageLocale));
					forward = "success";
				} else {
					commonDto.setInddistrict(new ArrayList());
				}
				forward = "success";

			}
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED)) {
				request.setAttribute("fromAdju", regForm.getFromAdjudication());
				forward = "reginitProperty";
			}
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS)) {
				request.setAttribute("fromAdju", regForm.getFromAdjudication());
				request.setAttribute("multiProp", "Y");
				// commonBo.landPropertyScreen(regForm,RegInitConstant.
				// STATUS_MULTI_PROP_IN_PROGRESS,languageLocale);
				forward = "reginitMultiProperty";
			}
			if (request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_FINAL_SCREEN)) {
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));
				forward = "success1";
			}
		}

		// postal address
		if (RegInitConstant.POSTAL_CHECK_ACTION.equalsIgnoreCase(actionName))

		{

			forward = "success1";

		}
		if (RegInitConstant.COPY_APPLICANT_ADDRESS_ACTION.equalsIgnoreCase(actionName))

		{
			commonBo.copyPostalAddress(regForm);
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(), languageLocale));
			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(), languageLocale));

			forward = "success1";

		}
		if (RegInitConstant.CHANGE_CATEGORY_ACTION.equalsIgnoreCase(actionName))

		{

			forward = "transactingParty";

		}
		// ADDED BY SHREERAJ
		// following code will run when the deed draft is uploaded
		if (RegInitConstant.UPLOAD_DEED_DRAFT.equalsIgnoreCase(actionName)) {
			FormFile uploadFile = regForm.getDeedUploadPath();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			boolean ret = false;
			String path = pr.getValue("upload.location");
			// String deedName=pr.getValue("reg.deedDraftFileName");
			// String filePath =
			// path+regForm.getDeedId()+"/"+RegInitConstant.UPLOAD_NAME_SIGN_DOC
			// ;
			String filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.UPLOAD_DEED_UPLOADED + "/";

			String ext = getFileExtension((String) uploadFile.getFileName());
			if (uploadFile != null) {
				if ("pdf".equalsIgnoreCase(ext)) {

					ret = commonBo.generatePDF2(filePath, uploadFile.getFileData());
					if (ret) {
						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext)) {
					ArrayList imageList = new ArrayList();
					imageList.add(uploadFile.getFileData());
					ret = commonBo.generatePDF(filePath, imageList);
					if (ret) {
						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else {
					regForm.setUpOrNot("0");
				}
			}
			if (ret) {
				logger.debug("Uploaded successfully");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + "/" + regForm.getHidnRegTxnId() + "/" + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/";

				byte arr[] = commonBo.mergeDeedPDF(filepath, response, deedName);
				if (arr != null) {
					logger.debug("Merged successfully");
					int noofPages = regForm.getConsenterNoPages();

					boolean boo = commonBo.updateDeedDetails(deedName, filepath, regForm.getHidnRegTxnId(), noofPages);

					if (boo) {
						regForm.setUpOrNot("1");
						logger.debug("ALL THE UPLOAD PATH AND NAMES ARE STORED IN DB!!!!!!!!!!!!!!!UPDATION SUCCESSFULL");
					} else {
						regForm.setUpOrNot("0");
						logger.debug("ALL THE UPLOAD PATH AND NAMES ARE NOT STORED IN DB!!!!!!!!!!!!!!!UPDATION FAILURE");
					}

					regForm.setDeedContents(arr);

				} else {
					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be merged");
					else
						request.setAttribute(RegInitConstant.FAILURE_MSG, "विलेख विलय नहीं हो पाया है।");
					regForm.setUpOrNot("0");
					logger.debug("Merging Failure");
				}
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be uploaded");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "विलेख अपलोड नहीं हो पाया है।");

				regForm.setUpOrNot("0");
				logger.debug("Upload Failure");
			}
			logger.debug("Uploaded successfully");

			regForm.setActionName("");
			regForm.setUploadCheck("0");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		// ADDED BY SHREERAJ
		// following code will run when the document with consenter details is
		// downloaded
		if (RegInitConstant.PRINT_CONSENTER_PDF.equalsIgnoreCase(actionName)) {
			boolean pdfFlag = false;
			/*
			 * PropertiesFileReader pr =
			 * PropertiesFileReader.getInstance("resources.igrs"); String
			 * filepath="";
			 * 
			 * if(languageLocale.equalsIgnoreCase("en"))
			 * filepath=pr.getValue("pdf.location"
			 * )+RegInitConstant.PRINT_CONSENTER_PDF_ENGLISH_JRXML; else
			 * filepath=pr.getValue("pdf.location")+RegInitConstant.
			 * PRINT_CONSENTER_PDF_HINDI_JRXML;
			 */

			pdfFlag = commonBo.generateGraphicsConsenterPDF(response, regForm, languageLocale);

			int noOfPages = regForm.getConsenterNoPages();
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsenterUploadPage(1);
			if (pdfFlag) {

			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The document could not be downloaded");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "दस्तावेज डाउनलोड नहीं हो पाया है।");
			}
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";

		}

		if (RegInitConstant.UPLOAD_CONSENTER.equalsIgnoreCase(actionName)) {

			regForm.setIsDashboardFlag(3);
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsenterUploadPage(1);
			regForm.setConsBrowseFlag("Y");
			regForm.setActionName("");
			regForm.setConsenterUploadList(new ArrayList());
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.UPLOAD_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			FormFile uploadFile = regForm.getConsenterUploadPath();
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			boolean ret = false;
			String path = pr.getValue("upload.location");
			System.out.println(regForm.getIndexCons());
			String filePath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + RegInitConstant.CONSENTER_PAGE + regForm.getConsenterUploadPage() + ".pdf";

			String ext = getFileExtension((String) uploadFile.getFileName());
			if (uploadFile != null) {
				if ("jpg".equalsIgnoreCase(ext) || "jpeg".equalsIgnoreCase(ext) || "tiff".equalsIgnoreCase(ext) || "bmp".equalsIgnoreCase(ext)) {
					ArrayList imageList = new ArrayList();
					imageList.add(uploadFile.getFileData());
					ret = commonBo.generatePDF(filePath, imageList);
					if (ret) {

						regForm.setUpOrNot("1");
					} else {
						regForm.setUpOrNot("0");
					}
				} else {
					regForm.setFinalPage("N");
					if (languageLocale.equalsIgnoreCase("en"))
						request.setAttribute(RegInitConstant.FAILURE_MSG, ext + " file cannot be uploaded. Kindly upload JPEG file");
					else
						request.setAttribute(RegInitConstant.FAILURE_MSG, "विलेख अपलोड नहीं हो पाया है।");

				}

			}
			if (ret) {
				logger.debug("Uploaded successfully");

				regForm.setConsUploadSucc("Y");
				if (regForm.getConsDeleteFlag() != null && regForm.getConsDeleteFlag().equalsIgnoreCase("Y")) {

					ArrayList list = regForm.getConsenterUploadList();
					RegCommonForm regF = new RegCommonForm();
					regF.setConsenterPages(regForm.getConsenterUploadPage());
					list.add(regF);
					regForm.setConsenterUploadList(list);

					if (regForm.getConsenterNoPages() == list.size()) {
						regForm.setConsBrowseFlag("N");
						regForm.setMergeSuccess("Y");
					}
					if (list.size() == 0) {
						regForm.setConsenterUploadPage(1);
					} else {
						// regForm.setConsenterUploadPage(regForm.
						// getConsenterUploadPage()+1);
						for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
							boolean flag = false;
							for (int j = 0; j < list.size(); j++) {
								RegCommonForm rForm = (RegCommonForm) list.get(j);
								int pageNo = rForm.getConsenterPages();
								if (i == pageNo)
									flag = true;
							}
							if (flag == false) {
								regForm.setConsenterUploadPage(i);
								break;
							}
						}
					}
				} else {
					regForm.setConsenterUploadPage(regForm.getConsenterUploadPage() + 1);
					ArrayList uploadList = new ArrayList();
					for (int i = 1; i < regForm.getConsenterUploadPage(); i++) {
						RegCommonForm regF = new RegCommonForm();
						regF.setConsenterPages(i);
						uploadList.add(regF);

						if (regForm.getConsenterNoPages() + 1 == regForm.getConsenterUploadPage()) {
							regForm.setConsBrowseFlag("N");
							regForm.setMergeSuccess("Y");
						}
					}
					regForm.setConsenterUploadList(uploadList);
				}

			} else {

				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The document could not be uploaded.Kindly upload JPEG file");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "विलेख विलय नहीं हो पाया है।");
				regForm.setUpOrNot("0");
				logger.debug("Upload Failure PAGE NUMBER:::::" + regForm.getConsenterUploadPage());
			}
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.DELETE_CONSENTER_DOC.equalsIgnoreCase(actionName)) {
			String deletePageNo = regForm.getIndexCons();
			ArrayList arr = regForm.getConsenterUploadList();
			for (int i = 0; i < arr.size(); i++) {
				RegCommonForm rForm = (RegCommonForm) arr.get(i);
				if (rForm.getConsenterPages() == Integer.parseInt(deletePageNo))
					arr.remove(i);
			}
			regForm.setConsenterUploadList(arr);
			regForm.setConsBrowseFlag("Y");
			regForm.setConsDeleteFlag("Y");
			regForm.setMergeSuccess("");
			ArrayList list = regForm.getConsenterUploadList();
			for (int i = 1; i <= regForm.getConsenterNoPages(); i++) {
				boolean flag = false;

				for (int j = 0; j < list.size(); j++) {
					RegCommonForm rForm = (RegCommonForm) list.get(j);
					int pageNo = rForm.getConsenterPages();
					if (i == pageNo)
						flag = true;
				}
				if (flag == false) {
					regForm.setConsenterUploadPage(i);
					break;
				}
			}
			regForm.setActionName("");
			if (arr.size() == 0) {
				regForm.setConsenterUploadPage(1);
			}
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		if (RegInitConstant.MERGE_CONSENTER_DEEDDRAFT.equalsIgnoreCase(actionName)) {
			PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");
			String path = pr.getValue("upload.location");
			String deedName = pr.getValue("reg.deedDraftFileName");
			String filepath = path + RegInitConstant.FILE_UPLOAD_PATH + regForm.getHidnRegTxnId() + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/";

			byte arr[] = commonBo.mergeConsenterDeedPDF(filepath, response, regForm.getConsenterNoPages(), deedName);
			if (arr != null) {
				logger.debug("Merged successfully");
				int noofPages = regForm.getConsenterNoPages();

				boolean boo = commonBo.updateDeedDetails(deedName, filepath, regForm.getHidnRegTxnId(), noofPages);

				if (boo) {
					regForm.setUpOrNot("1");
					regForm.setFinalPage("Y");
					logger.debug("ALL THE UPLOAD PATH AND NAMES ARE STORED IN DB!!!!!!!!!!!!!!!UPDATION SUCCESSFULL");
				} else {
					regForm.setUpOrNot("0");
					regForm.setFinalPage("N");
					logger.debug("ALL THE UPLOAD PATH AND NAMES ARE NOT STORED IN DB!!!!!!!!!!!!!!!UPDATION FAILURE");
				}

				regForm.setDeedContents(arr);
				regForm.setMergeSuccess("merged");
				regForm.setConsUploadFlag("2");
				regForm.setConsUploadSucc("N");
				regForm.setConsBrowseFlag("N");
			} else {
				if (languageLocale.equalsIgnoreCase("en"))
					request.setAttribute(RegInitConstant.FAILURE_MSG, "The deed could not be merged");
				else
					request.setAttribute(RegInitConstant.FAILURE_MSG, "विलेख विलय नहीं हो पाया है।");
				regForm.setUpOrNot("0");
				logger.debug("Merging Failure");
			}
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}
		if (RegInitConstant.ADD_UPLOADS_AGAIN.equalsIgnoreCase(actionName)) {
			regForm.setConsenterUploadList(new ArrayList());
			int noOfPages = regForm.getConsenterNoPages();
			regForm.setConsUploadFlag("1");// uploads are present
			regForm.setConsBrowseFlag("Y");
			regForm.setMergeSuccess("N");
			regForm.setFinalPage("N");
			regForm.setConsenterUploadPage(1);
			regForm.setActionName("");
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		String viewUpload = (String) request.getParameter("fwdName");
		if (viewUpload != null && "viewUpload".equalsIgnoreCase(viewUpload)) {
			try {
				PropertiesFileReader pr = PropertiesFileReader.getInstance("resources.igrs");

				String path = pr.getValue("upload.location");
				String deedName = pr.getValue("reg.deedDraftFileName");
				String filename = path + RegInitConstant.FILE_UPLOAD_PATH + "/" + regForm.getHidnRegTxnId() + "/" + RegInitConstant.FILE_UPLOAD_PATH_DEED_DRAFT + "/" + deedName;

				byte[] arr = DMSUtility.getDocumentBytes(filename);
				DMSUtility.downloadDocument(response, filename, arr);

			} catch (Exception e) {
				logger.debug(e.getMessage(), e);
			}
			DutyCalculationForm dutyForm = new DutyCalculationForm();
			dutyForm.setPropImage1DocumentName("");
			forward = "reginitConfirm";
		}

		if (request.getParameter("label") == null) {
			if (regForm.getDeedID() != 0) {
				request.setAttribute("deedId", regForm.getDeedID());
			} else {
				request.setAttribute("deedId", 0);
			}

			if (regForm.getPartyType() != null) {
				request.setAttribute("roleId", regForm.getPartyType());
			} else {
				request.setAttribute("roleId", 0);
			}
			if (regForm.getPartyTypeTrns() != null) {
				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
			} else {
				request.setAttribute("roleIdTrns", 0);
			}
		}

		// session.setAttribute("commonDto", commonDto);
		regForm.setCommonDto(commonDto);

		request.setAttribute("reginit", regForm);

		logger.debug("-------------------->" + session.getAttribute("parentModName"));
		logger.debug("-------------------->" + session.getAttribute("parentFunName"));
		logger.debug("-------------------->" + session.getAttribute("parentFunId"));
		logger.debug("-------------------->" + session.getAttribute("parentAmount"));

		logger.debug("the forward path from the common action in reg init is ==> " + forward);
		return mapping.findForward(forward);
	}

	public static void cancelAction(HttpSession session, RegCommonForm regForm) {

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
			regForm.setIndDisability("");
			// regForm.setShareOfProp("");
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
			regForm.setIndDisabilityTrns("");
			regForm.setShareOfPropTrns("");
			regForm.setOwnershipShareTrns("");
			regForm.setRelationWithOwnerTrns("");

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
			regForm.setPropertyDTO(new PropertyValuationDTO());
			regForm.setMapTransactingParties(new HashMap());
			regForm.setMapTransactingPartiesDisp(new HashMap());
			regForm.setMapTransPartyDbInsertion(new HashMap());
			regForm.setRegInitEstampCode(null);
			regForm.setRegInitPaymntTxnId(null);
			regForm.setRegInitPermTxnId("");
			regForm.setCurrDateTime("");
			regForm.setIsMultiplePropsFlag(0);

			regForm.setActionName(" ");
			regForm.setFormName(" ");
			regForm.setPage("success");

			regForm.setListAdoptedPartyNameTrns("");
			regForm.setListAdoptedPartyName("");
			regForm.setAddMoreCounter(0);
			//
			regForm.setRegInitPermTxnId("");
			regForm.setRegInitPaymntTxnId("");
			regForm.setRegInitEstampCode("");
			regForm.setMapRegTxnIdDisp(new HashMap());
			//
			regForm.setOwnerId("");
			regForm.setHdnOwnerId("");
			regForm.setHdnDeleteTrnsPrtyId("");
			regForm.setAbc("");

			regForm.setCurrDateTime("");
			regForm.setDeedID(0);

			// Start:==== Added by ankita
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

			// End :====== Added by ankita

			// following added roopam after first demo.

			regForm.setApplicantUserIdCheck("");
			regForm.setHdnapplicantUserIdCheck("");
			regForm.setInstID(0);
			regForm.setExmpID("");
			regForm.setPendingRegApplicationList(new ArrayList());
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

			// following fields for owner of poa
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
			// private String dutyPaid;

			regForm.setLabelAmountFlag("");
			// private double regPaid=0.0;

			regForm.setMapPropertyTransParty(new HashMap());

			regForm.setAdjudicationNumber("");
			regForm.setErrorMsg("");

		}
		// session.removeAttribute("OrganisationList");
		// session.removeAttribute("TpartiesIndividualList");
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
	private String getFileExtension(String fileName) {
		try {
			int pos = fileName.lastIndexOf(".");
			String strExt = fileName.substring(pos + 1, fileName.length());
			return strExt;
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
		return "";
	}

}
