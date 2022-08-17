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


package com.wipro.igrs.reginit.action;


//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
//import java.util.Date;
import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.wipro.igrs.DMSConnection.DMSUtility;
//import com.wipro.igrs.UserRegistration.bd.UserRegistrationBD;
//import com.wipro.igrs.common.IGRSCommon;

import com.wipro.igrs.reginit.bo.RegCommonBO;
import com.wipro.igrs.reginit.constant.RegInitConstant;

import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;

//import com.wipro.igrs.propertyvaluation.form.PropertyValuationForm;


public class NonPropertyRegInitAction extends  Action {
	private Logger logger = (Logger) Logger.getLogger(NonPropertyRegInitAction.class);
     /*private HashMap map =null;
     private HashMap map2 =null;
     int duty_txn_id=0;*/
    
  
    //boolean bol = true;
    //static private String key = "key";
    //static private int keyCount=0;
   
	@SuppressWarnings("unchecked")
	public ActionForward execute(
			ActionMapping mapping, 
			ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response ) throws Exception {
			
			 HttpSession session = request.getSession();
		
			 //if(session.getAttribute("forwardPath")==null)
			 //session.setAttribute("forwardPath", "./regInit.do");
			// session.setAttribute("modName","Registration Process");
			// session.setAttribute("fnName","Initiation");
			String forward="";
			RegCommonBO commonBo = new RegCommonBO();
			RegCommonForm regForm;
			CommonAction action=new CommonAction();
			HashMap map =null;
		    HashMap map2 =null;
		     //int duty_txn_id=0;
						
			if(request.getAttribute("regFormDashboard")!=null){
				regForm=(RegCommonForm)request.getAttribute("regFormDashboard");
				
				request.setAttribute("deedId", regForm.getDeedID());
    			request.setAttribute("roleId", regForm.getPartyType());
    			//request.setAttribute("hidnRegTxnId", regForm.getHidnRegTxnId());
    			
			}
			else
			    regForm = (RegCommonForm)form;
			
			String languageLocale="hi";
			if(session.getAttribute("languageLocale")!=null){
				languageLocale=(String)session.getAttribute("languageLocale");
			}
		
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				session.setAttribute("modName",RegInitConstant.MODNAME_ENGLISH);	
			}else{
				session.setAttribute("modName",RegInitConstant.MODNAME_HINDI);
			}
            //--------Only Reg Init Code needed by Title deed
			
			//ArrayList mainList;
			//following code for clearing form beans when the module is hit first time.
			if(request.getParameter("modName")!=null){
				if(request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")&& request.getAttribute("regFormDashboard")==null){
					
					
					
					
					if(regForm!=null)
					{
						
						RegCommonDTO commonDto =new RegCommonDTO();
					    commonDto.setInstrument(new ArrayList());
						commonDto.setExemption(new ArrayList());
						
						regForm.setApplicantUserIdCheck(null);
						regForm.setIndCategory("");
						regForm.setIndCategoryTrns("");
						regForm.setOccupationId("");
						regForm.setOccupationIdTrns("");
						
						regForm.setBankName("");
						regForm.setBranchName("");
						regForm.setBankAddress("");
						regForm.setBankAuthPer("");
						regForm.setBankLoanAmt(0);
						regForm.setBankSancAmt(0);
						
						regForm.setTrustName("");
						regForm.setTrustDate("");
						regForm.setDeedTypeFlag(0);
						
						regForm.setAdoptionDeedParty1Added(0);
						regForm.setAdoptionDeedParty2Added(0);
						regForm.setAdoptionDeedParty3Added(0);
						
						regForm.setRent(0);
						regForm.setAdvance(0);
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
						
						//regForm.setAdvance(0);
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
		        		regForm.setShareOfProp("");
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
					    regForm.setHidnRegTxnId("");
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
					
					//regForm.setHdnDeclareShareCheck("Y");
					
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
               		regForm.setAddMoreTransParty("N");
               		regForm.setDeclareShare("true");
               		regForm.setHdnDeclareShareCheck("Y");
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
               		
               		//for common deeds
               		regForm.setCommonDeed(0);
               		regForm.setAddPartyNewRole(0);					//variable for radio. value 0 for same role
               		regForm.setRoleSameAsPrevious(0);				// flag for above radio. 1 for previous (same) role
               		//regForm.setAddAnotherParty(0);
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
					
				//	if(regForm.getIsMultiplePropsFlag()==0){
						
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
					   /* //added by shruti
					    regForm.setDuty_txn_id(0);
*/			//		}
					}
					session.removeAttribute("commonDto");
					session.removeAttribute("roleId");
					session.removeAttribute("functionId");
					session.removeAttribute("status");
					session.removeAttribute("view");
					session.removeAttribute("regFormProp");
					
					int fromAdju=0;
					if(request.getAttribute("fromAdju")!=null){
					fromAdju=Integer.parseInt(request.getAttribute("fromAdju").toString());
					}
					System.out.println("from Adju flag is: "+fromAdju);
					regForm.setFromAdjudication(fromAdju);
					if(regForm.getFromAdjudication()==1){
						//session.setAttribute("fnName","Adjudication");
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_ENGLISH);
						}else{
							session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_HINDI);
						}
						}
					//added here by shruti,request scope variable will get null
					String duty_id="0";
					if(request.getAttribute("stampId")!=null){
					duty_id=request.getAttribute("stampId").toString();
					}
					logger.info("######------"+duty_id);
					
					int duty_txn_id=Integer.parseInt(duty_id);
					regForm.setDuty_txn_id(duty_txn_id);
					//end 
				}	
			}
			//end of code for clearing form beans
			
			if(regForm.getFromAdjudication()==0){
				//session.setAttribute("fnName","Initiation");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					session.setAttribute("fnName",RegInitConstant.FUNCTION_REGINIT_ENGLISH);
				}else{
					session.setAttribute("fnName",RegInitConstant.FUNCTION_REGINIT_HINDI);
				}
			}else{
				//session.setAttribute("fnName","Adjudication");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_ENGLISH);
				}else{
					session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_HINDI);
				}
			}
			
			RegCommonDTO commonDto;
			
			String userId = (String)session.getAttribute("UserId");			
			regForm.setHidnUserId(userId);
			
			//code added by shruti
				
			if(request.getParameter("modName")!=null && request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")){
				int deed=0;

			
				String temp=commonBo.getDeedId(regForm.getDuty_txn_id());
				
				deed=Integer.parseInt(temp);
				regForm.setDeedtype1(commonBo.getDeedName(temp,languageLocale));
				regForm.setDeedID(deed);
				
				regForm.setCommonDeed(commonBo.getCommonDeedFlag(temp));
				
				regForm.setInstID(Integer.parseInt(commonBo.getInstId(regForm.getDuty_txn_id())));
				regForm.setInstType(commonBo.getInstrumentName(commonBo.getInstId(regForm.getDuty_txn_id()),languageLocale));
				
				regForm.setExmpID(commonBo.getExempId(regForm.getDuty_txn_id()));
				regForm.setSelectedExemptionList(commonBo.getExemptionList(regForm.getExmpID().replace(",", "-")));
			//end of code by shruti
			
			
			}
			//End :====== Added by Ankita
			
			if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ||
					regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV ||
					regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){
				
				if(		regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_1 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_2 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_3 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_4 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_6 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_7 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_9 ||
						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_1 ||
						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_2 ||
						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_1){
					
					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2);
					
				}else if(regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_10 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_5 ||
						regForm.getInstID()==RegInitConstant.INST_AGREEMENT_MEMO_NPV_8 ||
						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_3 ||
						regForm.getInstID()==RegInitConstant.INST_TRANSFER_NPV_4 ||
						regForm.getInstID()==RegInitConstant.INST_FURTHER_CHARGE_NPV_2){
					
					regForm.setAgreeMemoInstFlag(RegInitConstant.NPV_PROP_REQ_CONSTANT_1);
				}
				
				
			}
			
			if((regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_NP)
					|| regForm.getDeedID()==RegInitConstant.DEED_ADOPTION 
					|| regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA
					|| (regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2)
					|| (regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2)
					|| (regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2)
					|| regForm.getCommonDeed()==1
					|| (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
							&& regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)){
				
					regForm.setDeedTypeFlag(1);
					regForm.setHdnDeclareShareCheck("N");
					if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION || 
							regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA)
					{
					regForm.setAddMoreTransParty("Y");
					}
			}else{
				regForm.setDeedTypeFlag(0);
			}
			
			
			
			
			
			
			
			
			
			
			
			if(regForm.getCommonDto()!= null ){
				commonDto = regForm.getCommonDto();
				
			}else{				
				commonDto = new RegCommonDTO();
				commonDto.setState(new ArrayList());
				commonDto.setDistrict(new ArrayList());
				commonDto.setIndstate(new ArrayList());
				commonDto.setInddistrict(new ArrayList());
			}
			
			forward = regForm.getPage();
			
	
			if(request.getParameter("modName")!=null && request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")){  //this line was added for dashboard
				int deed=0;
				
				//added/edited by shruti
				//since no deed id currently coming from duty calculation module,therefore hard coded
				//int duty_txn_id=7;//rishab to send,and to be made dynamic
				
				int duty_txn_id=regForm.getDuty_txn_id();
				String temp=commonBo.getDeedId(duty_txn_id);
				
				deed=Integer.parseInt(temp);
				
				//end of edited code by shruti
				
				request.setAttribute("deedId", deed);
			commonDto.setDeedId(deed);
			if(regForm.getIsDashboardFlag()==0)
			commonDto.setPartyType(commonBo.getPartyType(deed,regForm.getInstID(),languageLocale));
			commonDto.setAppType(commonBo.getAppType(languageLocale));
			commonDto.setCountry(commonBo.getCountry(languageLocale));
			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
			commonDto.setIdProf(commonBo.getIdProf(languageLocale));
			commonDto.setDeedType(commonBo.getDeedType(languageLocale));
			commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
			commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
			commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(),languageLocale));
			commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(),languageLocale));
			if(regForm.getCommonDeed()==1){
			commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(languageLocale,regForm.getInstID()));
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
			//following code for getting state and district of applicant
			//for getting organization state list
			if(regForm.getCountry()!=null && !regForm.getCountry().equalsIgnoreCase("")) {
			    commonDto.setState(commonBo.getState(regForm.getCountry(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setState(new ArrayList());
			}
			//for getting organization district list
			if(regForm.getStatename()!=null && !regForm.getStatename().equalsIgnoreCase("")){
			    commonDto.setDistrict(commonBo.getDistrict(regForm.getStatename(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setDistrict(new ArrayList());
			}
			//for getting individual state list
			if(regForm.getIndcountry()!=null && !regForm.getIndcountry().equalsIgnoreCase("")) {
			    commonDto.setIndstate(commonBo.getState(regForm.getIndcountry(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setIndstate(new ArrayList());
			}
			//for getting individual district list
			if(regForm.getIndstatename()!=null && !regForm.getIndstatename().equalsIgnoreCase("")){
			    commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatename(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setInddistrict(new ArrayList());
			}
			if(regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV)
			{
			//for getting Arbitrator state list
			if(regForm.getIndcountryArb()!=null && !regForm.getIndcountryArb().equalsIgnoreCase("")) {
			    commonDto.setIndstate(commonBo.getState(regForm.getIndcountryArb(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setIndstate(new ArrayList());
			}
			//for getting Arbitrator district list
			if(regForm.getIndstatenameArb()!=null && !regForm.getIndstatenameArb().equalsIgnoreCase("")){
			    commonDto.setInddistrict(commonBo.getDistrict(regForm.getIndstatenameArb(),languageLocale));
			    forward="success";
			}else{
			    commonDto.setInddistrict(new ArrayList());
			}
			}
			
			
			if(regForm.getIsMultiplePropsFlag()==1){
				
				String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
        		
        		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
    			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
    			if(deedInstArray[2].trim().equals("-")){
        			regForm.setExmpID("");
        			regForm.setHdnExAmount("");
        			}else{
        				regForm.setExmpID(deedInstArray[2].trim());
            			regForm.setHdnExAmount(deedInstArray[2].trim());
        			}
				
				
			}
			
			
			
			forward="success";
			
			if(regForm.getPartyType()!=null){
				request.setAttribute("roleId",regForm.getPartyType());
			}
			if(regForm.getPartyTypeTrns()!=null){
				request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
			}
			if(regForm.getDeedID()!=0){
			request.setAttribute("deedId",regForm.getDeedID());
			}
			
			String formName = regForm.getFormName();
			String actionName = regForm.getActionName();
			logger.debug("formName:-"+formName);
			logger.debug("actionName:-"+actionName);
			
			
			
			 
			if(RegInitConstant.PARTY_PAGE_FORM.equals(formName)) {
				
				/*if(RegInitConstant.NO_ACTION.equals(actionName)){
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar()));
				}*/
				if(RegInitConstant.GENDER_ACTION.equals(actionName)){
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="success";
					
					}
				if(RegInitConstant.AUTH_PER_GENDER_ACTION.equals(actionName)){
					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="success";
					
					}
				if(RegInitConstant.GENDER_TRNS_ACTION.equals(actionName)){
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="transactingParty";
					
					}
				if(RegInitConstant.AUTH_PER_GENDER_TRNS_ACTION.equals(actionName)){
					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="transactingParty";
					
					}
				if(RegInitConstant.GENDER_MODIFY_ACTION.equals(actionName)){
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="displayRegDetls";
					//return mapping.findForward(forward);
					}
				if(RegInitConstant.AUTH_PER_GENDER_MODIFY_ACTION.equals(actionName)){
					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
					regForm.setActionName(RegInitConstant.NO_ACTION);
					forward="displayRegDetls";
					//return mapping.findForward(forward);
					}
					
				
				if(RegInitConstant.CHANGE_PARTY_ACTION.equals(actionName)){
					String partyType = regForm.getListAdoptedParty();
					logger.debug("partyType:-"+partyType);
					
					//saveToken(request);
				}
				if(RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)){
					String partyTypeTrns = regForm.getListAdoptedPartyTrns();
					logger.debug("partyTypeTrns:-"+partyTypeTrns);
					
					forward="transactingParty";
					//saveToken(request);
				}
				
				if(RegInitConstant.CHANGE_CLAIMANT_ACTION.equals(actionName)){
					
					//if(regForm.getExecutantClaimant()){
						
						//String[] arr=regForm.getExecutantClaimant().split("~");
						
						int id = regForm.getExecutantClaimant();
						//String name=arr[1];
						
						regForm.setExecutantClaimantName(commonBo.getExecutantClaimantName(Integer.toString(regForm.getExecutantClaimant())));
						
						if(id==RegInitConstant.CLAIMANT_FLAG_2 || id == RegInitConstant.CLAIMANT_FLAG_4){
							
							regForm.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						}else{
							regForm.setPoaHolderFlag(0);
						}
						
						
					//}
					
					forward="success";
					
				}
				if(RegInitConstant.CHANGE_CLAIMANT_TRNS_ACTION.equals(actionName)){
					
					//if(regForm.getExecutantClaimant()){
						
						//String[] arr=regForm.getExecutantClaimant().split("~");
						
						int id = regForm.getExecutantClaimantTrns();
						//String name=arr[1];
						
						regForm.setExecutantClaimantName(commonBo.getExecutantClaimantName(Integer.toString(regForm.getExecutantClaimantTrns())));
						
						if(id==RegInitConstant.CLAIMANT_FLAG_2 || id == RegInitConstant.CLAIMANT_FLAG_4){
							
							regForm.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
						}else{
							regForm.setPoaHolderFlag(0);
						}
						
						
					//}
					
					forward="transactingParty";
					
				}
				
				if(RegInitConstant.NEXT_ACTION.equals(actionName)) {
					

					//following code for populating drop downs
					commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
					commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
					commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
					commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
					commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
					commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
					commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
					
					if(regForm.getCommonDeed()==1){
						commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(languageLocale,regForm.getInstID()));
					}
					
					
					//BELOW CODE FOR INSERTING APPLICANT DETAILS IN DATABASE.
						
					regForm.setHidnUserId(userId);
					if(regForm.getIsMultiplePropsFlag()==0){
					  Calendar currentDate = Calendar.getInstance();
					  SimpleDateFormat formatter= new SimpleDateFormat("ddMMyy");
					  String dateNow = formatter.format(currentDate.getTime());
					  String  regTxnIdSeq= commonBo.getRegInitTxnIdSeq();
					  String regTxnId=null;
					  regTxnId=dateNow+regTxnIdSeq;
					  
					  regForm.setHidnRegTxnId(regTxnId);
					  
					  
					} 
					int roleId=0;
					
					if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("") && !regForm.getPartyType().equalsIgnoreCase("null")){
					roleId=Integer.parseInt(regForm.getPartyType());
					}
					
					
					
					String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
					//String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
             		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
             		
             		//regForm.setClaimantFlag(claimantFlag);
					
					regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
					String filePath;
					String filePathPhotoProof;
					String filePathNotAffExec;
					String filePathExecPhoto;
					String filePathNotAffAttrn;
					String filePathAttrnPhoto;
					String filePathClaimPhoto;
					String filePathPanForm60;
					boolean applicantDetailsInserted=false;
					boolean allUploadSuccessful=false;
					
					CommonAction obj=new CommonAction();
					if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) || 
							regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID))
					{
						if(regForm.getDeedTypeFlag()==0){
							

							filePathPhotoProof=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU2DocContents(),
									regForm.getPartyRoleTypeId(),regForm.getU2PartyOrProp(),regForm.getU2UploadType());
							
	                    	if(filePathPhotoProof!=null){
	                    		regForm.setU2FilePath(filePathPhotoProof);
	                    		//BELOW CODE FOR EXECUTANT
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	                    		{
	                    			
	                    		filePathNotAffExec=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU3DocContents(),
										regForm.getPartyRoleTypeId(),regForm.getU3PartyOrProp(),regForm.getU3UploadType());
								
		                    	if(filePathNotAffExec!=null){
		                    		regForm.setU3FilePath(filePathNotAffExec);
		                    		
		                    		filePathExecPhoto=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU4DocContents(),
											regForm.getPartyRoleTypeId(),regForm.getU4PartyOrProp(),regForm.getU4UploadType());
									
			                    	if(filePathExecPhoto!=null){
			                    		regForm.setU4FilePath(filePathExecPhoto);
			                    		if(!regForm.getListID().equalsIgnoreCase("4")){
			                    			
			                    			filePathPanForm60=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU10DocContents(),
													regForm.getPartyRoleTypeId(),regForm.getU10PartyOrProp(),regForm.getU10UploadType());
											
					                    	if(filePathPanForm60!=null){
					                    		regForm.setU10FilePath(filePathPanForm60);
					                    		allUploadSuccessful=true;
					                    	}else{
					                    		allUploadSuccessful=false;
					                    	}
			                    			
			                    		}else{
			                    			allUploadSuccessful=true;
			                    		}
			                    	}else{
                    					allUploadSuccessful=false;
                    				}
		                    	}
		                    	}
	                    		//BELOW CODE FOR EXECUTANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	                    		{
	                    			
	                    			
	                    filePathExecPhoto=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU7DocContents(),
											regForm.getPartyRoleTypeId(),regForm.getU7PartyOrProp(),regForm.getU7UploadType());
									
			                if(filePathExecPhoto!=null)
			                {
			                    		regForm.setU7FilePath(filePathExecPhoto);
	                    		filePathNotAffAttrn=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU5DocContents(),
										regForm.getPartyRoleTypeId(),regForm.getU5PartyOrProp(),regForm.getU5UploadType());
								
		                    	if(filePathNotAffAttrn!=null)
		                    	{
		                    		regForm.setU5FilePath(filePathNotAffAttrn);
		                    		
		                    		filePathAttrnPhoto=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU6DocContents(),
											regForm.getPartyRoleTypeId(),regForm.getU6PartyOrProp(),regForm.getU6UploadType());
									
			                    	if(filePathAttrnPhoto!=null)
			                    	{
			                    		regForm.setU6FilePath(filePathAttrnPhoto);
			                    		
			                    		allUploadSuccessful=true;
			                    	}else{
                    					allUploadSuccessful=false;
                    				}
		                    	}
		                    	
			                }
		                    	
		                    	
		                    	}
	                    		//BELOW CODE FOR CLAIMANT
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
	                    		{
	                    			
	                    			filePathClaimPhoto=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU8DocContents(),
										regForm.getPartyRoleTypeId(),regForm.getU8PartyOrProp(),regForm.getU8UploadType());
								
		                    	if(filePathClaimPhoto!=null){
		                    		regForm.setU8FilePath(filePathClaimPhoto);
		                    		
		                    		if(!regForm.getListID().equalsIgnoreCase("4")){
		                    			
		                    			filePathPanForm60=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU11DocContents(),
												regForm.getPartyRoleTypeId(),regForm.getU11PartyOrProp(),regForm.getU11UploadType());
										
				                    	if(filePathPanForm60!=null){
				                    		regForm.setU11FilePath(filePathPanForm60);
				                    		allUploadSuccessful=true;
				                    	}else{
				                    		allUploadSuccessful=false;
				                    	}
		                    			
		                    		}else{
		                    			allUploadSuccessful=true;
		                    		}
			                    	
		                    	}else{
                					allUploadSuccessful=false;
                				}
		                    	}
	                    		
	                    		//BELOW CODE FOR CLAIMANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
	                    		{
	                    			
	                    			filePathAttrnPhoto=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU9DocContents(),
										regForm.getPartyRoleTypeId(),regForm.getU9PartyOrProp(),regForm.getU9UploadType());
								
		                    	if(filePathAttrnPhoto!=null){
		                    		regForm.setU9FilePath(filePathAttrnPhoto);
		                    			allUploadSuccessful=true;
			                    	
		                    	}else{
                					allUploadSuccessful=false;
                				}
		                    	}
	                    		
	                    		
	                    		if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) && 
	                    				regForm.getIndCategory().equalsIgnoreCase("1") && 
	                    				regForm.getIndScheduleArea().equalsIgnoreCase("N")){
	                    			
	                    				filePath= obj. uploadFile(regForm.getHidnRegTxnId(),regForm.getDocContents(),
	                    							regForm.getPartyRoleTypeId(),regForm.getPartyOrProp(),regForm.getUploadType());
	                    				if(filePath!=null){
	                    					regForm.setFilePath(filePath);
	                    					allUploadSuccessful=true;
	                    				}else{
	                    					allUploadSuccessful=false;
	                    				}
	                    		}
	                    	}
						
						
						
						
						
							
						}
					else
						{	
						filePathPhotoProof=obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getU2DocContents(),
								regForm.getPartyRoleTypeId(),regForm.getU2PartyOrProp(),regForm.getU2UploadType());
						
                    	if(filePathPhotoProof!=null){
                    		regForm.setU2FilePath(filePathPhotoProof);
                    		
                    		if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID))
                    		{	
                    		if(regForm.getIndCategory().equalsIgnoreCase("1") && regForm.getIndScheduleArea().equalsIgnoreCase("N")){
                    				filePath=  obj.uploadFile(regForm.getHidnRegTxnId(),regForm.getDocContents(),
                    							regForm.getPartyRoleTypeId(),regForm.getPartyOrProp(),regForm.getUploadType());
                    				if(filePath!=null){
                    					regForm.setFilePath(filePath);
                    					allUploadSuccessful=true;
                    				}else{
                    					allUploadSuccessful=false;
                    				}
                    		}else{
                    			allUploadSuccessful=true;
                    		}
                    	}else{
                			allUploadSuccessful=true;
                		}
                    	}
					
					}
					
					
					}else{
						allUploadSuccessful=true;
					}
					
					
					if(allUploadSuccessful){
					
					applicantDetailsInserted = commonBo.insertDepositDeedApplicantAndBankDetails(regForm);
					}
					
					if(applicantDetailsInserted){
						
						 RegCommonDTO mapDto =new RegCommonDTO();
						 
						 mapDto.setClaimantFlag(claimantFlag);
						 
		                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty().trim());
		                 mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
		                 mapDto.setPurposeTrns(regForm.getPurpose().trim());
		                 mapDto.setBname("");
		                 mapDto.setBaddress("");
		                 mapDto.setApplicantOrTransParty("Applicant");
		                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId().trim());
		                 mapDto.setUserID(regForm.getHidnUserId().trim());
		                 
		                 if(regForm.getRelationWithOwner().trim()!=null){
		                   	 if(regForm.getRelationWithOwner().equalsIgnoreCase("")){
		                   		mapDto.setRelationWithOwnerTrns("-");}
		                   	 else{
		                	    mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner().trim());}
		                 }
		                   	 
		                 if(regForm.getDeedTypeFlag()==0){
		                   	 if(regForm.getShareOfProp().equalsIgnoreCase("")){
		                   		mapDto.setShareOfPropTrns("-");
		                   		mapDto.setHdnDeclareShareCheck("N");
		                   	 }
		                   	 else{
		                	    mapDto.setShareOfPropTrns(regForm.getShareOfProp().trim());
		                	    mapDto.setHdnDeclareShareCheck("Y");
		                   	 }
		                 }else{
		                	 mapDto.setShareOfPropTrns("-");
		                   	 mapDto.setHdnDeclareShareCheck("N");
		                 }
		                   	 
		                 if(regForm.getCommonDeed()!=1){
		                   	mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyType().trim(),languageLocale,regForm.getDeedID(),regForm.getInstID()));
		                 }else{
		                	 mapDto.setRoleName(regForm.getCommonDeedRoleName());                      //for common deeds
		                 }
		                 
		                mapDto.setPartyTypeFlag("A");
		                
		                mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimant());
		                   	
		                   	if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
		                	 //organization
		                	 mapDto.setOgrNameTrns(regForm.getOgrName().trim());
		                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerName().trim());
		                	 mapDto.setIndAuthPersn(regForm.getAuthPerName().trim());
		                	 mapDto.setOrgaddressTrns(regForm.getOrgaddress().trim());
		                	 mapDto.setSelectedCountry(regForm.getCountry().trim());
		                	 mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
		                	 mapDto.setSelectedState(regForm.getStatename().trim());
		                	 mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
		                	 mapDto.setSelectedDistrict(regForm.getDistrict().trim());
		                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrict().trim(),languageLocale));
		                	 if(regForm.getMobno().trim().equalsIgnoreCase(""))
		                		 mapDto.setMobnoTrns("-"); 
		                	 else
		                	     mapDto.setMobnoTrns(regForm.getMobno().trim());
		                	 if(regForm.getPhno().trim().equalsIgnoreCase(""))
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
		                
		                	 if(regForm.getPartyType()!=null){
		                   	 mapDto.setPartyTypeTrns(regForm.getPartyType().trim());
		                	 }else{
		                		 mapDto.setPartyTypeTrns("");
		                	 }
		                	
		                	 mapDto.setIndCasteTrns("");
		                	 mapDto.setIndDisabilityTrns("");
		                	
		                	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
		                	 
		                	 mapDto.setUserDOBTrns("-");
		                	 
		                	 mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendar());
		                	 String gendr="";
		                	 if(regForm.getAuthPerGendar().equalsIgnoreCase("m")){
		                		 //mapDto.setSelectedGender("Male");
		                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                		 gendr=RegInitConstant.MALE_ENGLISH;
			                		 }else{
			                			 gendr=RegInitConstant.MALE_HINDI; 
			                		 }
		                	 }
		                	 else{
		                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                		 gendr=RegInitConstant.FEMALE_ENGLISH;
			                		 }else{
			                			 gendr=RegInitConstant.FEMALE_HINDI; 
			                		 }
		                		 //mapDto.setSelectedGender("Female");
		                	 }
		                	 mapDto.setSelectedGender(gendr);
		                	 
		                	 mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherName());
		                	 
		                	 mapDto.setRelationshipTrns(regForm.getAuthPerRelationship());
				             mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationship()),languageLocale));
				             
				             mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddress().trim());
				             mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountry());
				             mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountry(),languageLocale));
				             mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatename());
				             mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatename(),languageLocale));
				             mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrict());
				             mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrict().trim(),languageLocale));
		                	 
		                 }
		                 if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
		                	 //individual
		                	 mapDto.setFnameTrns(regForm.getFname());
		                	 if(regForm.getMname().equalsIgnoreCase(""))
		                		 mapDto.setMnameTrns("-");
		                	 else
		                	     mapDto.setMnameTrns(regForm.getMname());
		                	 mapDto.setLnameTrns(regForm.getLname());
		                	 mapDto.setGendarTrns(regForm.getGendar());
		                	 String gendr="";
		                	 if(regForm.getGendar().equalsIgnoreCase("m")){
		                		 //mapDto.setSelectedGender("Male");
		                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                		 gendr=RegInitConstant.MALE_ENGLISH;
			                		 }else{
			                			 gendr=RegInitConstant.MALE_HINDI; 
			                		 }
		                	 }
		                	 else{
		                		 //mapDto.setSelectedGender("Female");
		                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                		 gendr=RegInitConstant.FEMALE_ENGLISH;
			                		 }else{
			                			 gendr=RegInitConstant.FEMALE_HINDI; 
			                		 } 
		                	 }
		                	 mapDto.setSelectedGender(gendr);
		                	 if(regForm.getAge().equalsIgnoreCase(""))
		                		 mapDto.setAgeTrns("-");
		                	 else
		                	     mapDto.setAgeTrns(regForm.getAge());
		                	 mapDto.setFatherNameTrns(regForm.getFatherName());
		                	 if(regForm.getMotherName().equalsIgnoreCase(""))
		                		 mapDto.setMotherNameTrns("-");
		                	 else
		                	     mapDto.setMotherNameTrns(regForm.getMotherName());
		                	 if(regForm.getSpouseName().equalsIgnoreCase(""))
		                		 mapDto.setSpouseNameTrns("-");
		                	 else
		                	     mapDto.setSpouseNameTrns(regForm.getSpouseName());
		                	 mapDto.setNationalityTrns(regForm.getNationality());
		                	 
		                	 if(regForm.getPostalCode().equalsIgnoreCase(""))
		                		 mapDto.setPostalCodeTrns("-");
		                	 else
		                	     mapDto.setPostalCodeTrns(regForm.getPostalCode());
		                	 
		                	 if(regForm.getIndphno().equalsIgnoreCase(""))
		                		 mapDto.setPhnoTrns("-");
		                	 else
		                	     mapDto.setPhnoTrns(regForm.getIndphno());
		                	 
		                	 if(regForm.getIndmobno().equalsIgnoreCase(""))
		                		mapDto.setMobnoTrns("-");
		                	 else
		                        mapDto.setMobnoTrns(regForm.getIndmobno());
		                	 
		                	 if(regForm.getEmailID().equalsIgnoreCase(""))
		                		 mapDto.setEmailIDTrns("-");
		                	 else
		                	     mapDto.setEmailIDTrns(regForm.getEmailID());
		                	 mapDto.setSelectedPhotoId(regForm.getListID());
		                	 
		                	 mapDto.setSelectedPhotoIdName(regForm.getListIDName());               
		                	 if(regForm.getIdno().equalsIgnoreCase(""))
		                		 mapDto.setIdnoTrns("-");
		                	 else
		                	     mapDto.setIdnoTrns(regForm.getIdno());
		                	 
		                	
		                	 mapDto.setOgrNameTrns("-");
		                	 mapDto.setAuthPerNameTrns("-");
		                	 mapDto.setIndAuthPersn(regForm.getFname()+" "+regForm.getLname());
		                	 mapDto.setOrgaddressTrns(regForm.getIndaddress());
		                	 mapDto.setSelectedCountry(regForm.getIndcountry());
		                	 mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
		                	 mapDto.setSelectedState(regForm.getIndstatename());
		                	 mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
		                	 mapDto.setSelectedDistrict(regForm.getInddistrict());
		                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrict(),languageLocale));
		                	
		                	 mapDto.setSelectedCategoryName(regForm.getSelectedCategoryName())   ;  
		                	 mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationId(),languageLocale))   ;  
		                	 mapDto.setIndCategoryTrns(regForm.getIndCategory());
		                   	 mapDto.setPartyTypeTrns(regForm.getPartyType());
		                   	 mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleArea());
		                   	 
		                   	 if(regForm.getIndScheduleArea()!=null){
		                   	 if(regForm.getIndScheduleArea().equalsIgnoreCase("Y")){
		                   		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
		                   		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
			                   		 }else{
			                   			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
			                   		 }
		                   	 }else{
		                   		//mapDto.setIndScheduleAreaTrnsDisplay("No");
		                   		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
			                   		 }else{
			                   			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
			                   		 }
		                   		mapDto.setPermissionNoTrns(regForm.getPermissionNo());
		                   		mapDto.setDocumentNameTrns(regForm.getDocumentName());
		                   		mapDto.setDocumentSizeTrns(regForm.getDocumentSize());
		                   		mapDto.setFilePathTrns(regForm.getFilePath());
		                   		mapDto.setDocContentsTrns(regForm.getDocContents());
		                   		
		                   	 }
		                   	 }
		                   	 
		                   	 if(regForm.getIndDisability().equalsIgnoreCase(""))
		                   	 {
		                   		mapDto.setIndDisabilityTrns("-");
		                   	 }
		                   	 else if(regForm.getIndDisability().equalsIgnoreCase("Y"))
		                   	 {mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
		                	   // mapDto.setIndDisabilityTrns("Yes");
		                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
			                   		 }else{
			                   			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
			                   		 }
		                	    if(regForm.getIndDisabilityDesc().equalsIgnoreCase(""))
		                	    {
		                	    mapDto.setIndDisabilityDescTrns("-");
		                	    }else
		                	    {
		                	    	mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDesc());
		                	    }
		                   	 }
		                	    else if(regForm.getIndDisability().equalsIgnoreCase("N"))
		                	    {mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
		                	    	//mapDto.setIndDisabilityTrns("No");
		                	    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				                	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
				                   		 }else{
				                   			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
				                   		 }
		                	    	
		                	    }
		                   	 
		                   	 
		                   	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
		                   	//mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDay(), regForm.getUserMonth(), regForm.getUserYear()));
		                   	
		                   	if(regForm.getIndMinority().equalsIgnoreCase(""))
		                   	 {
		                   		mapDto.setIndMinorityTrns("-");
		                   	 }
		                   	 else if(regForm.getIndMinority().equalsIgnoreCase("Y"))
		                   	 {
		                	    //mapDto.setIndMinorityTrns("Yes");
		                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
			                   		 }else{
			                   			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
			                   		 }
		                	    
		                   	 }
		                	    else if(regForm.getIndMinority().equalsIgnoreCase("N"))
		                	    {
		                	    	//mapDto.setIndMinorityTrns("No");
		                	    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				                	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
				                   		 }else{
				                   			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
				                   		 }
		                	    	
		                	    }
		                   	
		              
		                   	if(regForm.getIndPovertyLine().equalsIgnoreCase(""))
		                   	 {
		                   		mapDto.setIndPovertyLineTrns("-");
		                   	 }
		                   	 else if(regForm.getIndPovertyLine().equalsIgnoreCase("Y"))
		                   	 {
		                	    //mapDto.setIndPovertyLineTrns("Yes");
		                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
			                   		 }else{
			                   			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
			                   		 }
		                	    
		                   	 }
		                	    else if(regForm.getIndPovertyLine().equalsIgnoreCase("N"))
		                	    {
		                	    	//mapDto.setIndPovertyLineTrns("No");
		                	    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				                	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
				                   		 }else{
				                   			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
				                   		 }
		                	    	
		                	    }
		                   	
		                mapDto.setRelationshipTrns(regForm.getRelationship());
		                mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationship()),languageLocale));
		                 }
		                 
		               //below code for uploads other than collector's permission no.
		                   	
		                   	mapDto.setU2DocumentNameTrns(regForm.getU2DocumentName());
	                   		mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSize());
	                   		mapDto.setU2FilePathTrns(regForm.getU2FilePath());
	                   		mapDto.setU2DocContentsTrns(regForm.getU2DocContents());
		                 
	                   		if(regForm.getDeedTypeFlag()==0){
	                   			
	                   		//BELOW CODE FOR EXECUTANT
		                   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	                    		{
		                   			mapDto.setU3DocumentNameTrns(regForm.getU3DocumentName());
			                   		mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSize());
			                   		mapDto.setU3FilePathTrns(regForm.getU3FilePath());
			                   		mapDto.setU3DocContentsTrns(regForm.getU3DocContents());
			                   		
			                   		mapDto.setU4DocumentNameTrns(regForm.getU4DocumentName());
			                   		mapDto.setU4DocumentSizeTrns(regForm.getU4DocumentSize());
			                   		mapDto.setU4FilePathTrns(regForm.getU4FilePath());
			                   		mapDto.setU4DocContentsTrns(regForm.getU4DocContents());
			                   		
			                   		mapDto.setU10DocumentNameTrns(regForm.getU10DocumentName());
			                   		mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSize());
			                   		mapDto.setU10FilePathTrns(regForm.getU10FilePath());
			                   		mapDto.setU10DocContentsTrns(regForm.getU10DocContents());
	                    		}
		                   	//BELOW CODE FOR EXECUTANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	                    		{
	                    			mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName());
			                   		mapDto.setU5DocumentSizeTrns(regForm.getU5DocumentSize());
			                   		mapDto.setU5FilePathTrns(regForm.getU5FilePath());
			                   		mapDto.setU5DocContentsTrns(regForm.getU5DocContents());
			                   		
			                   		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
			                   		mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSize());
			                   		mapDto.setU6FilePathTrns(regForm.getU6FilePath());
			                   		mapDto.setU6DocContentsTrns(regForm.getU6DocContents());
			                   		
			                   		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName());
			                   		mapDto.setU7DocumentSizeTrns(regForm.getU7DocumentSize());
			                   		mapDto.setU7FilePathTrns(regForm.getU7FilePath());
			                   		mapDto.setU7DocContentsTrns(regForm.getU7DocContents());
			                   		
			                   		mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
			                   		mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
			                   		mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));
	                    		}
		                   		
	                    		//BELOW CODE FOR CLAIMANT
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
	                    		{
	                    			mapDto.setU8DocumentNameTrns(regForm.getU8DocumentName());
			                   		mapDto.setU8DocumentSizeTrns(regForm.getU8DocumentSize());
			                   		mapDto.setU8FilePathTrns(regForm.getU8FilePath());
			                   		mapDto.setU8DocContentsTrns(regForm.getU8DocContents());
			                   		
			                   		mapDto.setU11DocumentNameTrns(regForm.getU11DocumentName());
			                   		mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSize());
			                   		mapDto.setU11FilePathTrns(regForm.getU11FilePath());
			                   		mapDto.setU11DocContentsTrns(regForm.getU11DocContents());
	                    		}
		                   		
	                    		//BELOW CODE FOR CLAIMANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
	                    		{
		                   		
		                   		mapDto.setU9DocumentNameTrns(regForm.getU9DocumentName());
		                   		mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSize());
		                   		mapDto.setU9FilePathTrns(regForm.getU9FilePath());
		                   		mapDto.setU9DocContentsTrns(regForm.getU9DocContents());
		                   		
	                    		}
	                   			
	                   		}
	                   		
	                   		
		                 
		                 if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
		                		 (regForm.getCommonDeed()==1 && 
		                				 (regForm.getExecutantClaimant()==RegInitConstant.CLAIMANT_FLAG_2 || regForm.getExecutantClaimant()==RegInitConstant.CLAIMANT_FLAG_4)))
		                 {
		                	 
		                	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
		                	 
		                	 mapDto.setOwnerNameTrns(regForm.getOwnerName());
		                	 if(regForm.getOwnerOgrName().equalsIgnoreCase("") || regForm.getOwnerOgrName().equalsIgnoreCase("null"))
			                   		mapDto.setOwnerOgrNameTrns("-");
			                   	 else
			                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrName());
		                	
		                	 /*if(regForm.getOwnerGendar().equalsIgnoreCase("f"))
		                	 mapDto.setOwnerGendarTrns("Female");
		                	 else
		                     mapDto.setOwnerGendarTrns("Male");*/
		                	 
		                	 
		                	 String gendr="";
		                	 
		                	 if(regForm.getOwnerGendar().equalsIgnoreCase("m")){
		                		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		                		 gendr=RegInitConstant.MALE_ENGLISH;
		                		 }else{
		                			 gendr=RegInitConstant.MALE_HINDI; 
		                		 }
		                	 }
		                	 else{
		                		 
		                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			                		 gendr=RegInitConstant.FEMALE_ENGLISH;
			                		 }else{
			                			 gendr=RegInitConstant.FEMALE_HINDI; 
			                		 }
		                		 
		                	 }
		                	 
		                	 mapDto.setOwnerGendarTrns(gendr);
		                	 
		                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationality());
		                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddress());
		                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhno());
		                	 if(regForm.getOwnerEmailID().equalsIgnoreCase("") || regForm.getOwnerEmailID().equalsIgnoreCase("null"))
			                   		mapDto.setOwnerEmailIDTrns("-");
			                   	 else
			                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailID());
		                	
		                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdno());
		                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAge());
		                	// mapDto.setOwnerDOBTrns(UserRegistrationBD.getOracleDate(regForm.getOwnerDay(), regForm.getOwnerMonth(), regForm.getOwnerYear()));
		                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListID());
		                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofName());
		                 }else{
		                	 mapDto.setPoaHolderFlag(0);
		                 }
		                
		                        
		                 map = regForm.getMapTransactingParties();
		                 int count=regForm.getMapKeyCount();
		                 if(count==0)
		                	 count=1;
		                 //else
		                //	 count++;
		                 
		                 if(map.containsKey(Integer.toString(count))){
			                  //keyCount=keyCount+1;
			                //	   key=key+keyCount;
		                	 count++;
		                	 map.put(Integer.toString(count), mapDto);
		                	 
			                 }
		                 else
		                	 map.put(Integer.toString(count), mapDto);
			                  
		                 
		                 regForm.setMapKeyCount(count);
		                 
		                 //regForm.setAddMoreCounter(count);
		                 
		                 regForm.setAddMoreCounter(map.size());
		                 
		                //key="key";
		                         
		                 regForm.setMapBuildingDetails(map);
		                 
		                 
		                 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
		                 regForm.setApplicantRoleId2(Integer.parseInt(applicantRoleId));
		                 if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y"))
		                 {
		                	 if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
		                			 regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
		                			 (regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
		                			 (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
		          							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )))   //FOR TITLE DEED
		                	 {
		                 int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
    						logger.debug("applicant role id---------->"+applicantRoleId);
    						logger.debug("total share---------->"+totalShare);
    						
    						regForm.setTotalShareOfProp(totalShare);
    						
    						if(totalShare==100){
    							request.setAttribute("fromAdju", regForm.getFromAdjudication());
    							forward="reginitProperty";
    							regForm.setActionName(RegInitConstant.NO_ACTION);
    							//actionName=RegInitConstant.NO_ACTION;
    							request.setAttribute("propAction", "propAction");
    							request.setAttribute("regInitForm", regForm);
    						}
    						else{
    							forward="transactingParty";
    							regForm.setActionName(RegInitConstant.NO_ACTION);
    							actionName=RegInitConstant.NO_ACTION;
    						} 
    				    }else{                                                                         // FOR LEASE/MORTGAGE/POA NPV DEEDS ETC.
    				    	
    				    	
    				    	int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
    				    	logger.debug("applicant role id---------->"+applicantRoleId);
    						logger.debug("total share---------->"+totalShare);
    						regForm.setTotalShareOfProp(totalShare);
    				    	
    						if(totalShare==100){
    							regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
    						}
    				    	
    				    	
    				    	
    				    	forward="transactingParty";
							regForm.setActionName(RegInitConstant.NO_ACTION);
							actionName=RegInitConstant.NO_ACTION;
    				    }
		                
		                	 
					}else{
						
						forward="transactingParty";
						regForm.setActionName(RegInitConstant.NO_ACTION);
						actionName=RegInitConstant.NO_ACTION;
						
						if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
								regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
								(regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
								(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
			  							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))){
						if(regForm.getAddMoreTransParty().equalsIgnoreCase("Y")){
							
						}else{
							request.setAttribute("fromAdju", regForm.getFromAdjudication());
							forward="reginitProperty";
							regForm.setActionName(RegInitConstant.NO_ACTION);
							actionName=RegInitConstant.NO_ACTION;
							request.setAttribute("propAction", "propAction");
							request.setAttribute("regInitForm", regForm);
							
						}
						}
						if((regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_NP)
								|| regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
							if(regForm.getAddMoreTransParty().equalsIgnoreCase("Y")){           //FOR CANCELLATION DEED, THIS FLAG WILL ALWAYS BE 'Y'
								
							}else{
								
								actionName=RegInitConstant.NEXT_TO_CONFIRM_ACTION;
									
								
							}
						}
						if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION){
							
							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_ADOPTING_PARENT ||
									Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_ADOPTING_PARENT_POA_HOLDER){
								regForm.setAdoptionDeedParty1Added(1);
							}
							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CHILD_ADOPTED ||
									Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CHILD_ADOPTED_POA_HOLDER){
								regForm.setAdoptionDeedParty2Added(1);
							}
							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CUSTODIAN ||
									Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CUSTODIAN_POA_HOLDER){
								regForm.setAdoptionDeedParty3Added(1);
							}
							
							
							}
						if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
							
							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CANCELLATION_1 ||
									Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CANCELLATION_1_POA_HOLDER){
								regForm.setAdoptionDeedParty1Added(1);
							}
							if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CANCELLATION_2 ||
									Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_CANCELLATION_2_POA_HOLDER){
								regForm.setAdoptionDeedParty2Added(1);
							}
							
							
						
							}
						if(regForm.getCommonDeed()==1){
							if(regForm.getAddMoreTransParty().equalsIgnoreCase("Y")){
								
								if(regForm.getAddPartyNewRole()==1){
									regForm.setCommonDeedRoleName("");
									regForm.setRoleSameAsPrevious(0);
								}else{
									regForm.setRoleSameAsPrevious(1);
								}
								
							}else{
								
								forward="reginitParticular";
								
								//redirect to particulars of transaction page
								
							}
							
							regForm.setAddPartyNewRole(0);
							regForm.setAddMoreTransParty("N");
						}
					}
		              
		                
							
		                 
		                 
					}
					else{
						forward="success";
						request.setAttribute("deedId", regForm.getDeedID());
                		request.setAttribute("roleId", regForm.getPartyType());
                		regForm.setHidnRegTxnId(null);
						return mapping.findForward(forward);
					}
					//actionName=RegInitConstant.NO_ACTION;
					regForm.setActionName(RegInitConstant.NO_ACTION);
					//regForm.setDeedId(request.getAttribute("deedId").toString());
					
					//resetToken(request);
					
				 //}
					regForm.setCountryTrns("1");
					regForm.setCountryNameTrns("INDIA");
					regForm.setStatenameTrns("1");
					regForm.setStatenameNameTrns("MADHYA PRADESH");
					regForm.setIndcountryTrns("1");
					regForm.setIndcountryNameTrns("INDIA");
					regForm.setIndstatenameTrns("1");
					regForm.setIndstatenameNameTrns("MADHYA PRADESH");
					if(regForm.getFromAdjudication()==1){
						//session.setAttribute("fnName","Adjudication");
						if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
							session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_ENGLISH);
						}else{
							session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_HINDI);
						}
						
					}
				//	request.setAttribute("fromAdju", regForm.getFromAdjudication());
				/*	session.setAttribute("fromAdju", regForm.getFromAdjudication());*/
					//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+session.getAttribute("fromAdju"));
					if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
        				if(regForm.getPartyType()!=null && !regForm.getPartyType().equalsIgnoreCase("")){
        				request.setAttribute("roleId", regForm.getPartyType());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}
            		
        				regForm.setClaimantFlag(0);
                		regForm.setPoaHolderFlag(0);
            		if(!actionName.equalsIgnoreCase(RegInitConstant.NEXT_TO_CONFIRM_ACTION))
            		{
            			
					return mapping.findForward(forward);
            		}
						
            		
            		
            		
				}
				
				//below code for getting state and district for transacting party
				//for getting organization state list
		//		if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE)
		//		{
					if(RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || 
							RegInitConstant.NO_ACTION.equals(actionName))
					{	
				if(regForm.getCountryTrns()!=null && !regForm.getCountryTrns().equalsIgnoreCase("")) {
				    commonDto.setStateTrns(commonBo.getState(regForm.getCountryTrns(),languageLocale));
				    forward="transactingParty";
				}else{
				    commonDto.setStateTrns(new ArrayList());
				}
				//for getting organization  district list
				if(regForm.getStatenameTrns()!=null && !regForm.getStatenameTrns().equalsIgnoreCase("")){
				    commonDto.setDistrictTrns(commonBo.getDistrict(regForm.getStatenameTrns(),languageLocale));
				    forward="transactingParty";
				}else{
				    commonDto.setDistrictTrns(new ArrayList());
				}
				//for getting individual state list
				if(regForm.getIndcountryTrns()!=null && !regForm.getIndcountryTrns().equalsIgnoreCase("")) {
				    commonDto.setIndstateTrns(commonBo.getState(regForm.getIndcountryTrns(),languageLocale));
				    forward="transactingParty";
				}else{
				    commonDto.setIndstateTrns(new ArrayList());
				}
				//for getting individual district list
				if(regForm.getIndstatenameTrns()!=null && !regForm.getIndstatenameTrns().equalsIgnoreCase("")){
				    commonDto.setInddistrictTrns(commonBo.getDistrict(regForm.getIndstatenameTrns(),languageLocale));
				    forward="transactingParty";
				}else{
				    commonDto.setInddistrictTrns(new ArrayList());
				}
				//end of code for populating drop downs
				}
		//	}
				
				
			
				
				//NEXT TO PROPERTY DETAILS PAGE THROUGH TRANSACTING PARTIES PAGE
                      if(RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)) {
                    	  
                    	
					//below code is for the insertion of last transacting party in map
					
					
	               
	                 RegCommonDTO mapDto =new RegCommonDTO();
	                 
	               //following code for insertion of owner details into map
	                 
	                 int roleId=0;
	                 
	                 if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")){
	                 roleId=Integer.parseInt(regForm.getPartyTypeTrns());
	                 }
	                 
	                 String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
	                 int claimantFlag=Integer.parseInt(claimantArr[0].trim());
	                 regForm.setClaimantFlag(claimantFlag);
	                 
	                 
	                 if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG || 
	                		 regForm.getCommonDeed()==1 &&
	                		 (regForm.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_2 ||
	                				 regForm.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_4 ))
	                 {
	                	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
	                	 mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
	                	 if(regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null") )
		                   		mapDto.setOwnerOgrNameTrns("-");
		                   	 else
		                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
	                	
	                	 if(regForm.getOwnerGendarTrns().equalsIgnoreCase("f"))
	                	 mapDto.setOwnerGendarTrns("Female");
	                	 else
	                     mapDto.setOwnerGendarTrns("Male");	 
	                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
	                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
	                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
	                	 if(regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
		                   		mapDto.setOwnerEmailIDTrns("-");
		                   	 else
		                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
	                	
	                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns());
	                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
	                	 //mapDto.setOwnerDOBTrns(UserRegistrationBD.getOracleDate(regForm.getOwnerDayTrns(), regForm.getOwnerMonthTrns(), regForm.getOwnerYearTrns()));
	                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
	                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofNameTrns());
	                 }
	                 else{
	                	 mapDto.setPoaHolderFlag(0);
	                 }
	                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
	                 mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
	                 mapDto.setPurposeTrns("");
	                 mapDto.setBname("");                                                //might be changed
	                 mapDto.setBaddress("");                                             //might be changed
	                 mapDto.setApplicantOrTransParty("Transacting");
	                 mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
	                 regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
	                
	                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
	                 mapDto.setUserID(regForm.getHidnUserId());
	                 
	                 if(regForm.getRelationWithOwnerTrns()!=null){
                	 if(regForm.getRelationWithOwnerTrns().equalsIgnoreCase("")){
                		 mapDto.setRelationWithOwnerTrns("-");}
                	 else{
                	     mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());}
	                 }
                	 
                	 if(regForm.getShareOfPropTrns().equalsIgnoreCase("")){
                		 mapDto.setHdnDeclareShareCheck("N");
                		 mapDto.setShareOfPropTrns("-");
                		 }
                	 else{
                	     mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
                	     mapDto.setHdnDeclareShareCheck("Y");    
                	 }
                	 
                	 if(regForm.getCommonDeed()!=1)    //will be true for deeds other than universal deeds
                	 {
                	//FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
                	 int selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());
                	 int applicantRoleId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
                
                		if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV){    //LEASE NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_LESSER_SELF || applicantRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_LESSER_SELF || selectedRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_LESSEE_SELF || applicantRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_LESSEE_SELF || selectedRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
                						 regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){    //MORTGAGE NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_MORTGAGER_SELF || applicantRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGER_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF || applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_POA){    //POA NPV DEED
                                    	 if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF || applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                        					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                         						 mapDto.setPartyTypeFlag("C");
                         					 }
                                    	 }else if(applicantRoleId==RegInitConstant.ROLE_POA_ACCEPTER || applicantRoleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
                                    		 if(selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER || selectedRoleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
                         						 mapDto.setPartyTypeFlag("C");
                         					 }
                                    	 }
                                    	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV){    //SETTLEMENT NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_SETTLER_SELF || applicantRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_SETTLER_SELF || selectedRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV){    //WILL NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_TESTATOR_SELF || applicantRoleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_TESTATOR_SELF || selectedRoleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV){    //TRANSFER OF LEASE NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_TRANSFERER_SELF || applicantRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_TRANSFERER_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF || applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV){    //SURRENDER LEASE NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_SURRENDERER_SELF || applicantRoleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_SURRENDERER_SELF || selectedRoleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_TRUST || (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
			  							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))){    //TRUST NPV DEED
                                	
                					 mapDto.setPartyTypeFlag("C");
                     					 
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV){    //AGREEMENT FO MEMO NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF || applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
                						 regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
                						 regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
                						 regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV)                //COMPOSITION NPV DEED
                				 {    
                                	 if(applicantRoleId==RegInitConstant.ROLE_EXECUTANT_SELF || applicantRoleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_EXECUTANT_SELF || selectedRoleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
                						 && regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV){    //DISSOLUTION NPV DEED
                                	 if(applicantRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF || applicantRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
                     					 if(selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }else if(applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
                                		 if(selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
                     						 mapDto.setPartyTypeFlag("C");
                     					 }
                                	 }
                                	 }
                				 else{
  	            	            		if(selectedRoleId==applicantRoleId ){
  	 	   	         						 mapDto.setPartyTypeFlag("C");
  	 	   	         					 }
  	 	            	            }
                	 
                	 mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(),languageLocale,regForm.getDeedID(),regForm.getInstID()));
                	 
                      }else{
                    	  mapDto.setRoleName(regForm.getCommonDeedRoleName());
                      }
                	 
                	 mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimantTrns());
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	                	 //organization
	                	 mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
	                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
	                	 mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getCountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getCountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getStatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getStatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getDistrictTrns());
	                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(),languageLocale));
	                	 if(regForm.getMobnoTrns().equalsIgnoreCase(""))
	                		 mapDto.setMobnoTrns("-"); 
	                	 else
	                	     mapDto.setMobnoTrns(regForm.getMobnoTrns());
	                	 if(regForm.getPhnoTrns().equalsIgnoreCase(""))
	                		 mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getPhnoTrns());
	                	 //mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
	                	 //mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
	                	 
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
	                	 if(regForm.getAuthPerGendarTrns().equalsIgnoreCase("m"))
	                		 mapDto.setSelectedGender("Male");
	                	 else
	                		 mapDto.setSelectedGender("Female");
	                	 mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());
	                	 
	                	 mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
			             mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()),languageLocale));
			             
			             mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
			             mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
			             mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(),languageLocale));
			             mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
			             mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(),languageLocale));
			             mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
			             mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(),languageLocale));
	                	
	                 }
	                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	                	 //individual
	                	 mapDto.setFnameTrns(regForm.getFnameTrns());
	                	 if(regForm.getMnameTrns().equalsIgnoreCase(""))
	                		 mapDto.setMnameTrns("-");
	                	 else
	                	     mapDto.setMnameTrns(regForm.getMnameTrns());
	                	 mapDto.setLnameTrns(regForm.getLnameTrns());
	                	 mapDto.setGendarTrns(regForm.getGendarTrns());
	                	 if(regForm.getGendarTrns().equalsIgnoreCase("m"))
	                		 mapDto.setSelectedGender("Male");
	                	 else
	                		 mapDto.setSelectedGender("Female");
	                	 if(regForm.getAgeTrns().equalsIgnoreCase(""))
	                		 mapDto.setAgeTrns("-");
	                	 else
	                	     mapDto.setAgeTrns(regForm.getAgeTrns());
	                	 mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
	                	 if(regForm.getMotherNameTrns().equalsIgnoreCase(""))
	                		 mapDto.setMotherNameTrns("-");
	                	 else
	                	     mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
	                	 if(regForm.getSpouseNameTrns().equalsIgnoreCase(""))
	                		 mapDto.setSpouseNameTrns("-");
	                	 else
	                	     mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
	                	 mapDto.setNationalityTrns(regForm.getNationalityTrns());
	                	
	                	 if(regForm.getPostalCodeTrns().equalsIgnoreCase(""))
	                		 mapDto.setPostalCodeTrns("-");
	                	 else
	                	     mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
	                	 if(regForm.getIndphnoTrns().equalsIgnoreCase(""))
	                	     mapDto.setPhnoTrns("-");
	                	 else
	                	     mapDto.setPhnoTrns(regForm.getIndphnoTrns());
	                	 if(regForm.getIndmobnoTrns().equalsIgnoreCase(""))
	                	     mapDto.setMobnoTrns("-");
	                	 else
	                         mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
	                	 if(regForm.getEmailIDTrns().equalsIgnoreCase(""))
	                		 mapDto.setEmailIDTrns("-");
	                	 else
	                	     mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
	                	 mapDto.setSelectedPhotoId(regForm.getListIDTrns());
	                	 mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
	                	 mapDto.setIdnoTrns(regForm.getIdnoTrns());
	                	 
	                	
	                	 mapDto.setOgrNameTrns("-");
	                	 mapDto.setAuthPerNameTrns("-");
	                	 mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
	                	 mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
	                	 mapDto.setSelectedCountry(regForm.getIndcountryTrns());
	                	 mapDto.setSelectedCountryName(regForm.getIndcountryNameTrns());
	                	 mapDto.setSelectedState(regForm.getIndstatenameTrns());
	                	 mapDto.setSelectedStateName(regForm.getIndstatenameNameTrns());
	                	 mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
	                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(),languageLocale));
	                	 
	                	 
	                	 mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
	                	 mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(),languageLocale))   ;
	                	 mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());
	                	 
	                	 mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());
	                   	 
	                   	 if(regForm.getIndScheduleAreaTrns()!=null){
	                   	 if(regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")){
	                   		mapDto.setIndScheduleAreaTrnsDisplay("Yes");
	                   	 }else{
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
	                	 
	                	 
	                	 if(regForm.getIndDisabilityTrns().equalsIgnoreCase(""))
	                   	 {
	                   		mapDto.setIndDisabilityTrns("-");
	                   	 }
	                   	 else if(regForm.getIndDisabilityTrns().equalsIgnoreCase("Y"))
	                   	 {
	                	    mapDto.setIndDisabilityTrns("Yes");
	                	    if(regForm.getIndDisabilityDescTrns().equalsIgnoreCase(""))
	                	    {
	                	    mapDto.setIndDisabilityDescTrns("-");
	                	    }else
	                	    {
	                	    	mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
	                	    }
	                   	 }
	                	    else if(regForm.getIndDisabilityTrns().equalsIgnoreCase("N"))
	                	    {
	                	    	mapDto.setIndDisabilityTrns("No");
	                	    	
	                	    }
	                	 
	                	 mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
	                	 
	                	 //mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDayTrns(), regForm.getUserMonthTrns(), regForm.getUserYearTrns()));
		                   	
		                   	if(regForm.getIndMinorityTrns().equalsIgnoreCase(""))
		                   	 {
		                   		mapDto.setIndMinorityTrns("-");
		                   	 }
		                   	 else if(regForm.getIndMinorityTrns().equalsIgnoreCase("Y"))
		                   	 {
		                	    mapDto.setIndMinorityTrns("Yes");
		                	    
		                   	 }
		                	    else if(regForm.getIndMinorityTrns().equalsIgnoreCase("N"))
		                	    {
		                	    	mapDto.setIndMinorityTrns("No");
		                	    	
		                	    }
		                   	
		                   	//mapDto.setIndMinorityTrns("");
		                   	if(regForm.getIndPovertyLineTrns().equalsIgnoreCase(""))
		                   	 {
		                   		mapDto.setIndPovertyLineTrns("-");
		                   	 }
		                   	 else if(regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y"))
		                   	 {
		                	    mapDto.setIndPovertyLineTrns("Yes");
		                	    
		                   	 }
		                	    else if(regForm.getIndPovertyLineTrns().equalsIgnoreCase("N"))
		                	    {
		                	    	mapDto.setIndPovertyLineTrns("No");
		                	    	
		                	    }
		                   	
		                    mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
				            mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()),languageLocale));
	                 }
	                 //below code for uploads other than collector's permission no.
	                   	
	                   	mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
                		mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
                		mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
                		mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
                		mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
                		mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());
                		
                		if(regForm.getDeedTypeFlag()==0)
                		{
                		//BELOW CODE FOR EXECUTANT
                		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
             		{
                			mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
	                   		mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
	                   		mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
	                   		mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
	                   		mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
	                   		mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());
	                   		
	                   		mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns());
	                   		mapDto.setU4DocumentSizeTrns(regForm.getU4DocumentSizeTrns());
	                   		mapDto.setU4FilePathTrns(regForm.getU4FilePathTrns());
	                   		mapDto.setU4DocContentsTrns(regForm.getU4DocContentsTrns());
	                   		mapDto.setU4PartyOrPropTrns(regForm.getU4PartyOrPropTrns());
	                   		mapDto.setU4UploadTypeTrns(regForm.getU4UploadTypeTrns());
	                   		
	                   		mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
	                   		mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
	                   		mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
	                   		mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
	                   		mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
	                   		mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
             		}
                	//BELOW CODE FOR EXECUTANT POA HOLDER
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
             		{
             			mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns());
	                   		mapDto.setU5DocumentSizeTrns(regForm.getU5DocumentSizeTrns());
	                   		mapDto.setU5FilePathTrns(regForm.getU5FilePathTrns());
	                   		mapDto.setU5DocContentsTrns(regForm.getU5DocContentsTrns());
	                   		mapDto.setU5PartyOrPropTrns(regForm.getU5PartyOrPropTrns());
	                   		mapDto.setU5UploadTypeTrns(regForm.getU5UploadTypeTrns());
	                   		
	                   		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
	                   		mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
	                   		mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
	                   		mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
	                   		mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
	                   		mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());
	                   		
	                   		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns());
	                   		mapDto.setU7DocumentSizeTrns(regForm.getU7DocumentSizeTrns());
	                   		mapDto.setU7FilePathTrns(regForm.getU7FilePathTrns());
	                   		mapDto.setU7DocContentsTrns(regForm.getU7DocContentsTrns());
	                   		mapDto.setU7PartyOrPropTrns(regForm.getU7PartyOrPropTrns());
	                   		mapDto.setU7UploadTypeTrns(regForm.getU7UploadTypeTrns());
	                   		
	                   		mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
	                   		mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
	                   		mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
             		}
                		
             		//BELOW CODE FOR CLAIMANT
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
             		{
             			mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns());
	                   		mapDto.setU8DocumentSizeTrns(regForm.getU8DocumentSizeTrns());
	                   		mapDto.setU8FilePathTrns(regForm.getU8FilePathTrns());
	                   		mapDto.setU8DocContentsTrns(regForm.getU8DocContentsTrns());
	                   		mapDto.setU8PartyOrPropTrns(regForm.getU8PartyOrPropTrns());
	                   		mapDto.setU8UploadTypeTrns(regForm.getU8UploadTypeTrns());
	                   		
	                   		mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
	                   		mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
	                   		mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
	                   		mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
	                   		mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
	                   		mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
             		}
                		
             		//BELOW CODE FOR CLAIMANT POA HOLDER
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
             		{
                		
                		mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
                		mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
                		mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
                		mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
                		mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
                		mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());
                		
             		}
                      }	
                		
	                map = regForm.getMapTransactingParties();
	              
	                int count=regForm.getMapKeyCount();
	                 if(count==0)
	                	 count=1;
	               
	                 if(map.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 map.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 map.put(Integer.toString(count), mapDto);
		                  
	                 
	                 regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
	                 
	                 regForm.setMapKeyCount(count);
	                 
	                 //regForm.setAddMoreCounter(count);
	                 regForm.setAddMoreCounter(map.size());
	                    // key="key";
	                    
	                     regForm.setMapTransactingParties(map);
					
					//end of insertion of last transacting party in map
	                
	                   //below code is for insertion of transacting parties in database.
	                     Collection mapCollection=regForm.getMapTransPartyDbInsertion().values();
	                     Object[] l1=mapCollection.toArray();
	                     RegCommonDTO mapDto1 =new RegCommonDTO();
	                     boolean allUploadSuccessful=false;
	                     boolean transPartyDetailsInserted=false;
	                     
	                     String filePath;
	 					 String filePathPhotoProof;
	                     
	                     //below loop for creating folder structure of uploads
	                     for(int i=0;i<l1.length;i++)
	                     {
	                     	allUploadSuccessful=false;
	                     	mapDto1=(RegCommonDTO)l1[i];
	                     	
	                     	filePath="";
	 						filePathPhotoProof="";
	 						String filePathNotAffExec;
	 						String filePathExecPhoto;
	 						String filePathNotAffAttrn;
	 						String filePathAttrnPhoto;
	 						String filePathClaimPhoto;
	 						String filePathPanForm60;
	 						
	 						int dtoRoleId=0;
	 						if(mapDto1.getPartyTypeTrns()!=null && !mapDto1.getPartyTypeTrns().equalsIgnoreCase("") && !mapDto1.getPartyTypeTrns().equalsIgnoreCase("null")){
	 						dtoRoleId=Integer.parseInt(mapDto1.getPartyTypeTrns());
	 						}
	 						String[] claimantArr1=commonBo.getClaimantFlag(Integer.toString(dtoRoleId));
	                 		int claimantFlag1=Integer.parseInt(claimantArr1[0].trim());
	                 			                     
	 						if(mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) ||
	 								mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)	)
	 						{
	 							
	 							
	 							if(regForm.getDeedTypeFlag()==0){
	 								

	 								filePathPhotoProof=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU2DocContentsTrns(),
	 										mapDto1.getPartyRoleTypeId(),mapDto1.getU2PartyOrPropTrns(),mapDto1.getU2UploadTypeTrns());
	 								
	 		                    	if(filePathPhotoProof!=null && !filePathPhotoProof.equalsIgnoreCase("")){
	 		                    		mapDto1.setU2FilePathTrns(filePathPhotoProof);
	 		                    		//BELOW CODE FOR EXECUTANT
	 		                    		if(claimantFlag1==RegInitConstant.CLAIMANT_FLAG_1)
	 		                    		{
	 		                    			
	 		                    		filePathNotAffExec=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU3DocContentsTrns(),
	 		                    				mapDto1.getPartyRoleTypeId(),mapDto1.getU3PartyOrPropTrns(),mapDto1.getU3UploadTypeTrns());
	 									
	 			                    	if(filePathNotAffExec!=null && !filePathNotAffExec.equalsIgnoreCase("")){
	 			                    		mapDto1.setU3FilePathTrns(filePathNotAffExec);
	 			                    		
	 			                    		filePathExecPhoto=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU4DocContentsTrns(),
	 			                    				mapDto1.getPartyRoleTypeId(),mapDto1.getU4PartyOrPropTrns(),mapDto1.getU4UploadTypeTrns());
	 										
	 				                    	if(filePathExecPhoto!=null && !filePathExecPhoto.equalsIgnoreCase("")){
	 				                    		mapDto1.setU4FilePathTrns(filePathExecPhoto);
	 				                    		if(!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4")){
	 				                    			
	 				                    			filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU10DocContentsTrns(),
	 				                    					mapDto1.getPartyRoleTypeId(),mapDto1.getU10PartyOrPropTrns(),mapDto1.getU10UploadTypeTrns());
	 												
	 						                    	if(filePathPanForm60!=null && !filePathPanForm60.equalsIgnoreCase("") ){
	 						                    		mapDto1.setU10FilePathTrns(filePathPanForm60);
	 						                    		allUploadSuccessful=true;
	 						                    	}else{
	 						                    		allUploadSuccessful=false;
	 						                    		break;
	 						                    	}
	 				                    			
	 				                    		}else{
	 				                    			allUploadSuccessful=true;
	 				                    		}
	 				                    	}else{
	 	                    					allUploadSuccessful=false;
	 	                    					break;
	 	                    				}
	 			                    	}
	 			                    	}
	 		                    		//BELOW CODE FOR EXECUTANT POA HOLDER
	 		                    		if(claimantFlag1==RegInitConstant.CLAIMANT_FLAG_2)
	 		                    		{
	 		                    			
	 		                    			
	 		                    filePathExecPhoto=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU7DocContentsTrns(),
	 		                    		mapDto1.getPartyRoleTypeId(),mapDto1.getU7PartyOrPropTrns(),mapDto1.getU7UploadTypeTrns());
	 										
	 				                if(filePathExecPhoto!=null && !filePathExecPhoto.equalsIgnoreCase(""))
	 				                {
	 				                	mapDto1.setU7FilePathTrns(filePathExecPhoto);
	 		                    		filePathNotAffAttrn=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU5DocContentsTrns(),
	 		                    				mapDto1.getPartyRoleTypeId(),mapDto1.getU5PartyOrPropTrns(),mapDto1.getU5UploadTypeTrns());
	 									
	 			                    	if(filePathNotAffAttrn!=null && !filePathNotAffAttrn.equalsIgnoreCase(""))
	 			                    	{
	 			                    		mapDto1.setU5FilePathTrns(filePathNotAffAttrn);
	 			                    		
	 			                    		filePathAttrnPhoto=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU6DocContentsTrns(),
	 			                    				mapDto1.getPartyRoleTypeId(),mapDto1.getU6PartyOrPropTrns(),mapDto1.getU6UploadTypeTrns());
	 										
	 				                    	if(filePathAttrnPhoto!=null && !filePathAttrnPhoto.equalsIgnoreCase(""))
	 				                    	{
	 				                    		mapDto1.setU6FilePathTrns(filePathAttrnPhoto);
	 				                    		
	 				                    		allUploadSuccessful=true;
	 				                    	}else{
	 	                    					allUploadSuccessful=false;
	 	                    					break;
	 	                    				}
	 			                    	}
	 			                    	
	 				                }
	 			                    	
	 			                    	
	 			                    	}
	 		                    		//BELOW CODE FOR CLAIMANT
	 		                    		if(claimantFlag1==RegInitConstant.CLAIMANT_FLAG_3)
	 		                    		{
	 		                    			
	 		                    			filePathClaimPhoto=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU8DocContentsTrns(),
	 		                    					mapDto1.getPartyRoleTypeId(),mapDto1.getU8PartyOrPropTrns(),mapDto1.getU8UploadTypeTrns());
	 									
	 			                    	if(filePathClaimPhoto!=null && !filePathClaimPhoto.equalsIgnoreCase("")){
	 			                    		mapDto1.setU8FilePathTrns(filePathClaimPhoto);
	 			                    		
	 			                    		if(!mapDto1.getSelectedPhotoId().equalsIgnoreCase("4")){
	 			                    			
	 			                    			filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU11DocContentsTrns(),
	 			                    					mapDto1.getPartyRoleTypeId(),mapDto1.getU11PartyOrPropTrns(),mapDto1.getU11UploadTypeTrns());
	 											
	 					                    	if(filePathPanForm60!=null && !filePathPanForm60.equalsIgnoreCase("")){
	 					                    		mapDto1.setU11FilePathTrns(filePathPanForm60);
	 					                    		allUploadSuccessful=true;
	 					                    	}else{
	 					                    		allUploadSuccessful=false;
	 					                    		break;
	 					                    	}
	 			                    			
	 			                    		}else{
	 			                    			allUploadSuccessful=true;
	 			                    		}
	 				                    	
	 			                    	}else{
	 	                					allUploadSuccessful=false;
	 	                					break;
	 	                				}
	 			                    	}
	 		                    		
	 		                    		//BELOW CODE FOR CLAIMANT POA HOLDER
	 		                    		if(claimantFlag1==RegInitConstant.CLAIMANT_FLAG_4)
	 		                    		{
	 		                    			
	 		                    			filePathAttrnPhoto=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU9DocContentsTrns(),
	 		                    					mapDto1.getPartyRoleTypeId(),mapDto1.getU9PartyOrPropTrns(),mapDto1.getU9UploadTypeTrns());
	 									
	 			                    	if(filePathAttrnPhoto!=null && !filePathAttrnPhoto.equalsIgnoreCase("")){
	 			                    		mapDto1.setU9FilePathTrns(filePathAttrnPhoto);
	 			                    			allUploadSuccessful=true;
	 				                    	
	 			                    	}else{
	 	                					allUploadSuccessful=false;
	 	                					break;
	 	                				}
	 			                    	}
	 		                    		
	 		                    	if(mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	 		                    		if(mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	 		                    				filePath=  action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getDocContentsTrns(),
	 		                    						mapDto1.getPartyRoleTypeId(),mapDto1.getPartyOrPropTrns(),mapDto1.getUploadTypeTrns());
	 		                    				if(filePath!=null && !filePath.equalsIgnoreCase("")){
	 		                    					mapDto1.setFilePathTrns(filePath);
	 		                    					allUploadSuccessful=true;
	 		                    				}else{
	 		                    					allUploadSuccessful=false;
	 		                    					break;
	 		                    				}
	 		                    		}
	 		                    	}
	 		                    	}
	 							
	 							
	 							
	 							
	 							
	 								
	 								
	 							}
	 						else
	 							{	
	 							filePathPhotoProof=action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getU2DocContentsTrns(),
	 									mapDto1.getPartyRoleTypeId(),mapDto1.getU2PartyOrPropTrns(),mapDto1.getU2UploadTypeTrns());
	 							
	 	                    	if(filePathPhotoProof!=null && !filePathPhotoProof.equalsIgnoreCase("")){
	 	                    		mapDto1.setU2FilePathTrns(filePathPhotoProof);
	 	                    	
	 	                    		if(mapDto1.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID))	
	 	                    		{	
	 	                    		if(mapDto1.getIndCategoryTrns().equalsIgnoreCase("1") && mapDto1.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	 	                    				filePath=  action.uploadFile(regForm.getHidnRegTxnId(),mapDto1.getDocContentsTrns(),
	 	                    						mapDto1.getPartyRoleTypeId(),mapDto1.getPartyOrPropTrns(),mapDto1.getUploadTypeTrns());
	 	                    				if(filePath!=null && !filePath.equalsIgnoreCase("")){
	 	                    					mapDto1.setFilePathTrns(filePath);
	 	                    					allUploadSuccessful=true;
	 	                    				}else{
	 	                    					allUploadSuccessful=false;
	 	                    					break;
	 	                    				}
	 	                    		}else{
	 	                    			allUploadSuccessful=true;
	 	                    		}
	 	                    	}else{
 	                    			allUploadSuccessful=true;
 	                    		}
	 	                    	}
	 						
	 						}
	 						
	 						
	 						}else{
	 							allUploadSuccessful=true;
	 						}
	                    }
	                     
	                     
	                     
	                     //below loop for inserting data into database
	                     if(allUploadSuccessful){
	                     for(int i=0;i<l1.length;i++)
	                     {
	                     	mapDto1=(RegCommonDTO)l1[i];
	                         mapDto1.setPropertyId(regForm.getPropertyId());
	                         transPartyDetailsInserted = 
	                        	 commonBo.insertTransPartyDetails(mapDto1, regForm,new ArrayList(),regForm.getIsMultiplePropsFlag());
	                          
	                     	
	                           
	                           if(!transPartyDetailsInserted){
	     							forward="transactingParty";
	     							regForm.setHidnRegTxnId(null);
	     							actionName=RegInitConstant.NO_ACTION;
	     	  						regForm.setActionName(RegInitConstant.NO_ACTION);
	     	  						break;
	     						}
	                     }
	                     if(transPartyDetailsInserted){
	                    	 
	                    	 regForm.setClaimantFlag(0);
	                    	 regForm.setPoaHolderFlag(0);
	                    	 
	                    	// else{
	                    	 
	     							
	     							if(regForm.getCommonDeed()==1){
	     								forward="reginitParticular";
	     								//actionName=RegInitConstant.NO_ACTION;
	     								return mapping.findForward(forward);
	     							}
	     						else if(regForm.getDeedTypeFlag()==0){ 
	     								
	     								//following code for party txn ids
	     								/*HashMap map=regForm.getMapTransactingParties();
	     								String[] partyTxnIdArray=new String[map.size()];
	     								Set mapSet = (Set)map.entrySet();
	     								RegCommonDTO dto1=new RegCommonDTO();
	     				                 Iterator mapIterator = mapSet.iterator();
	     				                 int f=0;
	     				                 while(mapIterator.hasNext())
	     				                  {
	     				                	 Map.Entry mapEntry = (Map.Entry)mapIterator.next();
	     				                	 String key = (String)mapEntry.getKey();
	     				                	  System.out.println("key----->"+key);
	     				                	  dto1=(RegCommonDTO)mapEntry.getValue();
	     				                	  System.out.println("key----->"+dto1.getPartyRoleTypeId());
	     				                	 partyTxnIdArray[f]=dto1.getPartyRoleTypeId();
	     				                	 f++;
	     				                  }
	     								
	     								regForm.setPartyTxnIdArr(partyTxnIdArray);*/
	     								request.setAttribute("fromAdju", regForm.getFromAdjudication());
	     								forward="reginitProperty";
	     							}
	     							
	     							else{
	     								actionName=RegInitConstant.NEXT_TO_CONFIRM_ACTION;      // next to confirmation screen
	     							}
	     						//}
	                     }else{
	                    	 forward="transactingParty";
  							regForm.setHidnRegTxnId(null);
  							actionName=RegInitConstant.NO_ACTION;
  	  						regForm.setActionName(RegInitConstant.NO_ACTION);
  	  						
	                     }
	                     }else{
	                     	forward="transactingParty";
	 						regForm.setHidnRegTxnId(null);
	 						actionName=RegInitConstant.NO_ACTION;
	   						regForm.setActionName(RegInitConstant.NO_ACTION);
	                     }
	 					
	                     regForm.setMapTransPartyDbInsertion(new HashMap());
						regForm.setActionName(RegInitConstant.NO_ACTION);
						request.setAttribute("propAction", "propAction");
						request.setAttribute("regInitForm", regForm);
				}
				
			}
			
			if(RegInitConstant.REG_INIT_CONFIRM_PAGE.equals(formName)) {
				if(RegInitConstant.MODIFY_ACTION.equals(actionName)){
					
					forward="success";
				}
				
if(RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName)){
					
					regForm.setHidnUserId(session.getAttribute("UserId").toString());
					/*session.setAttribute("parentModName", "Registration Process");
					session.setAttribute("parentFunName", "Registration Initiation");
					session.setAttribute("parentFunId", "FUN_023");
					session.setAttribute("parentAmount", regForm.getTotalduty());
             */
					//following added on 13feb for new payment modality.
					
					if(Double.parseDouble(regForm.getTotalBalanceAmount())>0)
					{
					request.setAttribute("parentModName", "Registration Process");
					request.setAttribute("parentFunName", "Registration Initiation");
					request.setAttribute("parentFunId", "FUN_023");
					request.setAttribute("parentAmount", regForm.getTotalBalanceAmount());
					request.setAttribute("parentTable", "IGRS_REG_PAYMENT_DETLS");
					//regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
					//request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
					request.setAttribute("forwardPath", "./nonPropRegInit.do");
					request.setAttribute("parentColumnName", "TXN_ID");
					//end of addition on 13feb for new payment modality.
					
					//code for checking user type of logged in user:
					//below for new payment requirements. completed on 4 sept 2013
					
					String userTypeId=commonBo.getUserTypeId(userId);
					String parentOfficeId="NA";
					String parentOfficeName="NA";
					String parentDistrictId="NA";
					String parentDistrictName="NA";
					String parentReferenceId=regForm.getHidnRegTxnId();
					
					if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_RU)){
						
						//String[] arr=commonBo.getDistIdNameRU(userId);
						
						//if(arr!=null && arr.length==2){
							parentDistrictId=commonBo.getDistIdEstamp(regForm.getHidnRegTxnId());
							parentDistrictName=commonBo.getDistrictName(parentDistrictId,languageLocale);
						//}
						//else{
						//parentDistrictId="NA";
						//parentDistrictName="NA";
						//}
					
					}else if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SP) || userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_SPB)){
						
						String[] arr=commonBo.getDistIdNameSP(userId);
						
						if(arr!=null && arr.length==2){
							parentDistrictId=arr[0].trim();
							parentDistrictName=commonBo.getDistrictName(parentDistrictId,languageLocale);
						}
						else{
						parentDistrictId="NA";
						parentDistrictName="NA";
						}
					
					}else if(userTypeId.equalsIgnoreCase(RegInitConstant.LOGGED_IN_USER_DRS)){
						
						
						String[] arr=commonBo.getDistIdNameOfficeNameDRS(session.getAttribute("loggedToOffice").toString());
						parentOfficeId=session.getAttribute("loggedToOffice").toString();
						
						if(arr!=null && arr.length==3){
							
							parentDistrictId=arr[0].trim();
							parentDistrictName=commonBo.getDistrictName(parentDistrictId,languageLocale);
							parentOfficeName=commonBo.getOfficeName(parentOfficeId,languageLocale);
						}
						else{
							
							parentDistrictId="NA";
							parentDistrictName="NA";
							parentOfficeName="NA";
						}
						
					
					}
					
					
					request.setAttribute("parentOfficeId", parentOfficeId);
					request.setAttribute("parentOfficeName", parentOfficeName);
					request.setAttribute("parentDistrictId", parentDistrictId);
					request.setAttribute("parentDistrictName", parentDistrictName);
					request.setAttribute("parentReferenceId", parentReferenceId);
					
					//end of new payment requirements added on 4th sept 2013
					
					
					//CHECK FOR PAYMENT_STATUS BEFORE REDIRECTING.
					
					String[] paymentArr=commonBo.getPaymentFlag(regForm.getHidnRegTxnId());
					//logger.debug("----------------->payment flag:-"+paymentArr[0]);
					if(paymentArr!=null)
					{
					if(!paymentArr[0].equalsIgnoreCase("p") && !paymentArr[0].equalsIgnoreCase("c")){
						
						if(paymentArr[0].equalsIgnoreCase("I")){
							
							//following code for updating purpose
							if(regForm.getPurpose()!=null && !regForm.getPurpose().equalsIgnoreCase("")){
		                		boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
								if(updatePurpose){
									logger.debug("purpose updated");
									forward="reginitPayment";
								}
								else{
									logger.debug("purpose not updated");
									forward="failure";
								}
		                	}
							
							
							request.setAttribute("parentKey", paymentArr[1]);
							//following code for updating purpose
							/*boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
							if(updatePurpose)
							forward="reginitPayment";
							else
							forward="failure";*/
							
							
						}
						/*else if(paymentArr[0].equals(null)||paymentArr[0].equalsIgnoreCase("")){
							
							regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
							request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
							//insertion code
							//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
							boolean insertStatus=commonBo.insertPaymentDetails(regForm);
							logger.debug("----------------->payment insertion status:-"+insertStatus);
							if(insertStatus)
								forward="reginitPayment";
							else
								forward="failure";
							
						}*/
						
					}
					else if(paymentArr[0].equalsIgnoreCase("p")){
						
						if(regForm.getPurpose()!=null && !regForm.getPurpose().equalsIgnoreCase("")){
	                		boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
							if(updatePurpose){
								logger.debug("purpose updated");
								//forward="reginitPayment";
							}
							else{
								logger.debug("purpose not updated");
								//forward="failure";
							}
	                	}
						
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						//insertion code
						//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus=commonBo.insertPaymentDetails(regForm);
						logger.debug("----------------->payment insertion status:-"+insertStatus);
						if(insertStatus)
							forward="reginitPayment";
						else
							forward="failure";
					}
					else if(paymentArr[0].equalsIgnoreCase("c")){
						forward="success1";
					}else{
						forward="failure";
					}
					}else{
						
						if(regForm.getPurpose()!=null && !regForm.getPurpose().equalsIgnoreCase("")){
	                		boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
							if(updatePurpose){
								logger.debug("purpose updated");
								//forward="reginitPayment";
							}
							else{
								logger.debug("purpose not updated");
								//forward="failure";
							}
	                	}
						
						regForm.setPaymentTxnSeqId(commonBo.getPaymentTxnIdSeq());
						request.setAttribute("parentKey", regForm.getPaymentTxnSeqId());
						//insertion code
						//String param[]={regForm.getHidnRegTxnId(),regForm.getTotalduty(),regForm.getHidnUserId(),regForm.getPurpose()};
						boolean insertStatus=commonBo.insertPaymentDetails(regForm);
						logger.debug("----------------->payment insertion status:-"+insertStatus);
						if(insertStatus)
							forward="reginitPayment";
						else
							forward="failure";
					}
				}else{
					
					actionName=RegInitConstant.FINAL_ACTION;
					
				}
					//forward="reginitPayment";
					//return mapping.findForward(forward);
				}
				  /*if(RegInitConstant.CALCULATE_DUTIES_ACTION.equals(actionName)) {
	                	HashMap propMap =new HashMap();
	            		propMap=regForm.getMapPropertyTransParty();
	            		logger.debug("in confirmation action----------------------->");
	            		
	            		ArrayList propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
	            		double totalMarketVal=0;
	            		for(int i=0;i<propertyIdList.size();i++){
	            			
	            			ArrayList row_list=new ArrayList();
	            			row_list=(ArrayList)propertyIdList.get(i);
	            			String propIds=row_list.toString();
	            			propIds=propIds.substring(1, propIds.length()-1);
	            			logger.debug("property id and market value list-->"+propIds);
	            			String propId[]=propIds.split(",");
	            			
	            			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
	            			ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId());
	            			for(int j=0;j<transPartyDets.size();j++){
	            				CommonDTO dto=new CommonDTO();
	            				dto=(CommonDTO)transPartyDets.get(j);
	            				logger.debug("transacting party list  role------>"+dto.getRole());
	            				logger.debug("transacting party list  name------>"+dto.getName());
	            				logger.debug("transacting party list  id------>"+dto.getId());
	            			
	            			}
	            			logger.debug("property id------>"+propId[0].trim());
	            			logger.debug("market value------>"+propId[1].trim());
	            			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
	            	
	            		}
	            		
	            		NumberFormat obj=new DecimalFormat("#0.00");
	            		regForm.setTotalMarketValue(obj.format(totalMarketVal));
	            		regForm.setMapPropertyTransParty(propMap);
	            		
	            		//FOLLOWING CODE FOR DUTY.
	            		if(regForm.getIsMultiplePropsFlag()!=1){
	            			//IN CASE OF SINGLE PROPERTY
	            			String dutyListArr[]=commonBo.getDutyDetls(regForm.getHidnRegTxnId());
	            			
	            			regForm.setStampduty1(Double.parseDouble(dutyListArr[0].trim()));
	            			regForm.setNagarPalikaDuty(Double.parseDouble(dutyListArr[2].trim()));
	            			regForm.setPanchayatDuty(Double.parseDouble(dutyListArr[1].trim()));
	            			regForm.setUpkarDuty(Double.parseDouble(dutyListArr[3].trim()));
	            			regForm.setTotalduty(Double.parseDouble(dutyListArr[5].trim()));
	            			regForm.setRegistrationFee(Double.parseDouble(dutyListArr[4].trim()));
	            			
	            		}
	            		else{
	            			//IN CASE OF MULTIPLE PROPERTIES
	            		double dutyArray[] = new double[3];
				     	double nagarPalikaDuty = 0.0;
				     	double panchayatDuty = 0.0;
				     	double upkarDuty = 0.0;
				     	double total = 0.0;
	            		double stampDuty=0.0;
	            		double regFee=0.0;
	            		stampDuty=commonBo.getDutyCalculation(regForm);
	            		
	            		logger.debug("after first procedure of duty calculation");
	            		
	            			regForm.setStampduty1(obj.format(stampDuty));
	            			
	            		    dutyArray=commonBo.getMunicipalDutyCalculation(regForm);
	            		    logger.debug("after second procedure of duty calculation");
	            		    if (dutyArray.length >= 1) {
	    						nagarPalikaDuty = (dutyArray[0]);
	    						panchayatDuty = (dutyArray[1]);
	    						upkarDuty = (dutyArray[2]);
	    			    	}
	            			total = nagarPalikaDuty + panchayatDuty + upkarDuty + stampDuty;
	            			regForm.setNagarPalikaDuty(obj.format(nagarPalikaDuty));
	            			regForm.setPanchayatDuty(obj.format(panchayatDuty));
	            			regForm.setUpkarDuty(obj.format(upkarDuty));
	            			regForm.setTotalduty(obj.format(total));
	            			
	            			regFee = commonBo.getRegistrationFee(regForm);
	            			logger.debug("after third procedure of duty calculation");
	            			regForm.setRegistrationFee(obj.format(regFee));
	            	}
	            			regForm.setIsDutyCalculated(1);
	            			regForm.setActionName(RegInitConstant.NO_ACTION);
	            		forward="reginitConfirm";
	                }*/
				
			}
			//FOLLOWING ADDED BY ROOPAM
			if(RegInitConstant.CANCEL_TRANSACTING_PART_ACTION.equals(actionName)||RegInitConstant.CANCEL_ACTION.equals(actionName) &&(request.getParameter("modName")==null)){				
				
				cancelAction(session,regForm);
				if(map!=null){
				if(!map.isEmpty())
					map.clear();
				}
				//count=0;
				//keyCount=0;
				forward="welcome";
				return mapping.findForward(forward);
			}
			
			if(regForm.getMapKeyCount()==0)
           	 regForm.setDeleteTrnsPrtyID("");
			
				 if(RegInitConstant.ADD_MORE_ACTION.equals(actionName)) {
                 /*count = count + 1;
                 
                 if(count==1)
                	 keyCount=1;*/
                
                 RegCommonDTO mapDto =new RegCommonDTO();
                 
                 //following code for insertion of owner details into map
                 
                 int roleId=0;
                 if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")){
                 roleId=Integer.parseInt(regForm.getPartyTypeTrns());
                 }
                 
                 
                 
                 String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
                 int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                 
                 mapDto.setClaimantFlag(claimantFlag);
                 
                 if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG || 
                		 regForm.getCommonDeed()==1 &&
                		 (regForm.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_2 ||
                				 regForm.getExecutantClaimantTrns()==RegInitConstant.CLAIMANT_FLAG_4 ))
                 {
                	 
                	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
                	 
                	 mapDto.setOwnerNameTrns(regForm.getOwnerNameTrns());
                	 if(regForm.getOwnerOgrNameTrns().equalsIgnoreCase("") || regForm.getOwnerOgrNameTrns().equalsIgnoreCase("null"))
	                   		mapDto.setOwnerOgrNameTrns("-");
	                   	 else
	                	    mapDto.setOwnerOgrNameTrns(regForm.getOwnerOgrNameTrns());
                	
                	 if(regForm.getOwnerGendarTrns().equalsIgnoreCase("f")){
                	 //mapDto.setOwnerGendarTrns("Female");
                	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
               			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
               		 
               		 }else{
               			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
               			
               		 }
                	 }
                	 else{
                    // mapDto.setOwnerGendarTrns("Male");
                     if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
              			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
              		 
              		 }else{
              			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
              			
              		 }
                     
                	 }
                	 mapDto.setOwnerNationalityTrns(regForm.getOwnerNationalityTrns());
                	 mapDto.setOwnerAddressTrns(regForm.getOwnerAddressTrns());
                	 mapDto.setOwnerPhnoTrns(regForm.getOwnerPhnoTrns());
                	 if(regForm.getOwnerEmailIDTrns().equalsIgnoreCase("") || regForm.getOwnerEmailIDTrns().equalsIgnoreCase("null"))
	                   		mapDto.setOwnerEmailIDTrns("-");
	                   	 else
	                	    mapDto.setOwnerEmailIDTrns(regForm.getOwnerEmailIDTrns());
                	
                	 mapDto.setOwnerIdnoTrns(regForm.getOwnerIdnoTrns());
                	 mapDto.setOwnerAgeTrns(regForm.getOwnerAgeTrns());
                	 //mapDto.setOwnerDOBTrns(UserRegistrationBD.getOracleDate(regForm.getOwnerDayTrns(), regForm.getOwnerMonthTrns(), regForm.getOwnerYearTrns()));
                	 mapDto.setOwnerListIDTrns(regForm.getOwnerListIDTrns());
                	 mapDto.setOwnerProofNameTrns(regForm.getOwnerProofNameTrns());
                 }
                 else{
                	 mapDto.setPoaHolderFlag(0);
                 }
               
                 mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedPartyTrns());
                 mapDto.setListAdoptedPartyNameTrns(commonBo.getAppleteName(regForm.getListAdoptedPartyTrns().trim(), languageLocale));
                 mapDto.setPurposeTrns("");
                 mapDto.setBname("");                                                //might be changed
                 mapDto.setBaddress("");                                             //might be changed
                 mapDto.setApplicantOrTransParty("Transacting");
                 mapDto.setPartyTypeTrns(regForm.getPartyTypeTrns());
                 regForm.setPartyRoleTypeId(commonBo.getTransactingPartyIdSeq());
               
                 mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
                 mapDto.setUserID(regForm.getHidnUserId());
                 
                 if(regForm.getOwnershipShareTrns()!=null){
                 if(regForm.getOwnershipShareTrns().equalsIgnoreCase(""))
            		 mapDto.setOwnershipShareTrns("-");
            	 else
            		 mapDto.setOwnershipShareTrns(regForm.getOwnershipShareTrns());
                 }
                 
                 
            	 if(regForm.getRelationWithOwnerTrns().equalsIgnoreCase("")){
            		 mapDto.setRelationWithOwnerTrns("-");}
            	 else{
            	     mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwnerTrns());}
            	 if(regForm.getShareOfPropTrns().equalsIgnoreCase("")){
            		 mapDto.setShareOfPropTrns("-");
            		 mapDto.setHdnDeclareShareCheck("N");	 
            	 }
            	 else{
            	     mapDto.setShareOfPropTrns(regForm.getShareOfPropTrns());
            	     mapDto.setHdnDeclareShareCheck("Y");
          }
            	 
            	 
            	 
            	 //following code for share of property validation.
            	 //int appRoleId=regForm.getApplicantRoleId2();
            	// if(regForm.getDeedID()==RegInitConstant.DEED_CONVEYANCE_P){
            	// if(appRoleId==RegInitConstant.ROLE_SELLER_SELF || appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
            		 
            		 String applicantRoleId1=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
 					
 					
 					//following if condition for disabling applicant role once total sum of share is 100%.
            		 
 					if(regForm.getTotalShareOfProp()==100){
 						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId1));
 					}
 					          		 
 					/* }else
            	 if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.getApplicantRoleId2()){
            	 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
					
					
					//following if condition for disabling applicant role once total sum of share is 100%.
					if(regForm.getTotalShareOfProp()==100){
						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
					}
            	 }*/
            	// }
            	 /*if(regForm.getDeedID()==RegInitConstant.DEED_GIFT){
                	 if(appRoleId==RegInitConstant.ROLE_SELLER_SELF || appRoleId==RegInitConstant.ROLE_SELLER_POA_HOLDER){
                		 
                		 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
     					
     					
     					//following if condition for disabling applicant role once total sum of share is 100%.
     					if(regForm.getTotalShareOfProp()==100){
     						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
     					}
     					          		 
                	 }else
                	 if(Integer.parseInt(regForm.getPartyTypeTrns())==regForm.getApplicantRoleId2()){
                	 String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
    					
    					
    					//following if condition for disabling applicant role once total sum of share is 100%.
    					if(regForm.getTotalShareOfProp()==100){
    						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
    					}
                	 }
                	 }*/
				//end of code for share of property validation.
 					
 					if(regForm.getCommonDeed()!=1)
 					{
            	 
            	 //FOLLOWING CODE FOR SETTING PARTY TYPE FLAG
 					int selectedRoleId=Integer.parseInt(regForm.getPartyTypeTrns());
 	            	 int applicantRoleId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
 	            	
 	            	if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV){    //CONVEYANCE DEED
 	            	            	 if(applicantRoleId==RegInitConstant.ROLE_LESSER_SELF || applicantRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
 	            	            		 if(selectedRoleId==RegInitConstant.ROLE_LESSER_SELF || selectedRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
 	            	 						 mapDto.setPartyTypeFlag("C");
 	            	 					 }
 	            	               	 }else if(applicantRoleId==RegInitConstant.ROLE_LESSEE_SELF || applicantRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
 	            	            		 if(selectedRoleId==RegInitConstant.ROLE_LESSEE_SELF || selectedRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
 	            	 						 mapDto.setPartyTypeFlag("C");
 	            	 					 }
 	            	            	 }
 	            	            	 }
 	            				 else if(regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
 	            						regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV){    //MORTGAGE DEED
 	            	            	 if(applicantRoleId==RegInitConstant.ROLE_MORTGAGER_SELF || applicantRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
 	            	            		 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGER_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
 	            	 						 mapDto.setPartyTypeFlag("C");
 	            	 					 }
 	            	               	 }else if(applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF || applicantRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
 	            	            		 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
 	            	 						 mapDto.setPartyTypeFlag("C");
 	            	 					 }
 	            	            	 }
 	            	            	 }
 	            				else
 	   	            			 if(regForm.getDeedID()==RegInitConstant.DEED_POA){    //POA NPV DEED
 	   	                        	 if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF || applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
 	   	             					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
 	   	             						 mapDto.setPartyTypeFlag("C");
 	   	             					 }
 	   	                        	 }else if(applicantRoleId==RegInitConstant.ROLE_POA_ACCEPTER || applicantRoleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
 	   	                        		 if(selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER || selectedRoleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
 	   	             						 mapDto.setPartyTypeFlag("C");
 	   	             					 }
 	   	                        	 }
 	   	                        	 }
 	   	            		 else if(regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV){    //SETTLEMENT DEED
	            	            	 if(applicantRoleId==RegInitConstant.ROLE_SETTLER_SELF || applicantRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
	            	            		 if(selectedRoleId==RegInitConstant.ROLE_SETTLER_SELF || selectedRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
	            	 						 mapDto.setPartyTypeFlag("C");
	            	 					 }
	            	               	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
	            	            		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
	            	 						 mapDto.setPartyTypeFlag("C");
	            	 					 }
	            	            	 }
	            	            	 }
 	   	            		else if(regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV){    //WILL NPV DEED
           	            	 if(applicantRoleId==RegInitConstant.ROLE_TESTATOR_SELF || applicantRoleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
           	            		 if(selectedRoleId==RegInitConstant.ROLE_TESTATOR_SELF || selectedRoleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
           	 						 mapDto.setPartyTypeFlag("C");
           	 					 }
           	               	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
           	            		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
           	 						 mapDto.setPartyTypeFlag("C");
           	 					 }
           	            	 }
           	            	 }
 	   	            	else if(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV){    //TRANSFER OF LEASE NPV DEED
        	            	 if(applicantRoleId==RegInitConstant.ROLE_TRANSFERER_SELF || applicantRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
        	            		 if(selectedRoleId==RegInitConstant.ROLE_TRANSFERER_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
        	 						 mapDto.setPartyTypeFlag("C");
        	 					 }
        	               	 }else if(applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF || applicantRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
        	            		 if(selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
        	 						 mapDto.setPartyTypeFlag("C");
        	 					 }
        	            	 }
        	            	 }
 	   	            else if(regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV){    //SETTLEMENT DEED
   	            	 if(applicantRoleId==RegInitConstant.ROLE_SURRENDERER_SELF || applicantRoleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
   	            		 if(selectedRoleId==RegInitConstant.ROLE_SURRENDERER_SELF || selectedRoleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
   	 						 mapDto.setPartyTypeFlag("C");
   	 					 }
   	               	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
   	            		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
   	 						 mapDto.setPartyTypeFlag("C");
   	 					 }
   	            	 }
   	            	 }
 	   	       
 	   	    else if(regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
 	   	    	(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )) ){    //TRUST NPV DEED
 	   	    regForm.setIsFirstPartyAddedFlag(1);
				 mapDto.setPartyTypeFlag("C");
					 
           	 }
 	   	else if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV){    //SURRENDER_LEASE_NPV DEED
       	 if(applicantRoleId==RegInitConstant.ROLE_OWNER_SELF || applicantRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
       		 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
          	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
       		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
       	 }
       	 }
 	   else if(regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
 			  regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
 			  regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
 			  regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV){    //COMPOSITION DEED
      	 if(applicantRoleId==RegInitConstant.ROLE_EXECUTANT_SELF || applicantRoleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
      		 if(selectedRoleId==RegInitConstant.ROLE_EXECUTANT_SELF || selectedRoleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
         	 }else if(applicantRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || applicantRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
      		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
      	 }
      	 }
 	  else if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
				 && regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV){    //DISSOLUTION NPV DEED
     	 if(applicantRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF || applicantRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
				 if(selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
     	 }else if(applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || applicantRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
     		 if(selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
					 mapDto.setPartyTypeFlag("C");
				 }
     	 }
     	 }
 	            				 else{
 	            	            		if(selectedRoleId==applicantRoleId ){
 	   	         						 mapDto.setPartyTypeFlag("C");
 	   	         					 }
 	            	            	 }
 	            	 
 	            	if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION){
						
						if(selectedRoleId==RegInitConstant.ROLE_ADOPTING_PARENT ||
								selectedRoleId==RegInitConstant.ROLE_ADOPTING_PARENT_POA_HOLDER){
							int count=regForm.getAdoptionDeedParty1Added();
							count++;
							regForm.setAdoptionDeedParty1Added(count);
						}
						if(selectedRoleId==RegInitConstant.ROLE_CHILD_ADOPTED ||
								selectedRoleId==RegInitConstant.ROLE_CHILD_ADOPTED_POA_HOLDER){
							int count=regForm.getAdoptionDeedParty2Added();
							count++;
							regForm.setAdoptionDeedParty2Added(count);
						}
						if(selectedRoleId==RegInitConstant.ROLE_CUSTODIAN || 
								selectedRoleId==RegInitConstant.ROLE_CUSTODIAN_POA_HOLDER){
							int count=regForm.getAdoptionDeedParty3Added();
							count++;
							regForm.setAdoptionDeedParty3Added(count);
						}
						
						
					}
 	            	if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
						
						if(selectedRoleId==RegInitConstant.ROLE_CANCELLATION_1 ||
								selectedRoleId==RegInitConstant.ROLE_CANCELLATION_1_POA_HOLDER){
							int count=regForm.getAdoptionDeedParty1Added();
							count++;
							regForm.setAdoptionDeedParty1Added(count);
						}
						if(selectedRoleId==RegInitConstant.ROLE_CANCELLATION_2 || 
								selectedRoleId==RegInitConstant.ROLE_CANCELLATION_2_POA_HOLDER){
							int count=regForm.getAdoptionDeedParty2Added();
							count++;
							regForm.setAdoptionDeedParty2Added(count);
						}
						
						
						
					}
 	            	mapDto.setRoleName(commonBo.getRoleName(regForm.getPartyTypeTrns(),languageLocale,regForm.getDeedID(),regForm.getInstID()));
				 }else{
					 mapDto.setRoleName(regForm.getCommonDeedRoleName()); 
				 }
            		 
 					mapDto.setExecutantClaimantTrns(regForm.getExecutantClaimantTrns());
            	 
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
                	 //organization
                	 mapDto.setOgrNameTrns(regForm.getOgrNameTrns());
                	 mapDto.setAuthPerNameTrns(regForm.getAuthPerNameTrns());
                	 mapDto.setIndAuthPersn(regForm.getAuthPerNameTrns());
                	 mapDto.setOrgaddressTrns(regForm.getOrgaddressTrns());
                	 mapDto.setSelectedCountry(regForm.getCountryTrns());
                	 mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
                	 mapDto.setSelectedState(regForm.getStatenameTrns());
                	 mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
                	 mapDto.setSelectedDistrict(regForm.getDistrictTrns());
                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getDistrictTrns(),languageLocale));
                	 if(regForm.getMobnoTrns().equalsIgnoreCase(""))
                		 mapDto.setMobnoTrns("-"); 
                	 else
                	     mapDto.setMobnoTrns(regForm.getMobnoTrns());
                	 if(regForm.getPhnoTrns().equalsIgnoreCase(""))
                		 mapDto.setPhnoTrns("-");
                	 else
                	     mapDto.setPhnoTrns(regForm.getPhnoTrns());
                	 //mapDto.setConsiAmountTrns(regForm.getConsiAmountTrns());
                	 //mapDto.setMarketValueTrns(regForm.getMarketValueTrns());
                	 
                	 mapDto.setFnameTrns("");
               		 mapDto.setMnameTrns("");
                	 mapDto.setLnameTrns("");
                	// mapDto.setGendarTrns("-");
                	// mapDto.setSelectedGender("");
                	 mapDto.setAgeTrns("");
                   //	 mapDto.setFatherNameTrns("-");
                	 mapDto.setMotherNameTrns("");
               		 mapDto.setSpouseNameTrns("");
                  	 mapDto.setNationalityTrns("");
                	 mapDto.setPostalCodeTrns("");
               		 mapDto.setEmailIDTrns("");
                	 mapDto.setSelectedPhotoId("");
                	 mapDto.setSelectedPhotoIdName("");
                	 mapDto.setIdnoTrns("-");
                	
                	 //mapDto.setIndReligionTrns("");
                	 mapDto.setIndCasteTrns("");
                	 mapDto.setIndDisabilityTrns("");
                	
                	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
                	 
                	 mapDto.setAuthPerGendarTrns(regForm.getAuthPerGendarTrns());
                	 if(regForm.getAuthPerGendarTrns().equalsIgnoreCase("m")){
                		 //mapDto.setSelectedGender("Male");
                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                 			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
                 		 
                 		 }else{
                 			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
                 			
                 		 }
                	 }
                	 else{
                		// mapDto.setSelectedGender("Female");
                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                 			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
                 		 
                 		 }else{
                 			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
                 			
                 		 }
                	 }
                	 mapDto.setAuthPerFatherNameTrns(regForm.getAuthPerFatherNameTrns());
                	 
                	 mapDto.setRelationshipTrns(regForm.getAuthPerRelationshipTrns());
		             mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getAuthPerRelationshipTrns()),languageLocale));
		             
		             mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddressTrns().trim());
		             mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountryTrns());
		             mapDto.setAuthPerCountryNameTrns(commonBo.getCountryName(regForm.getAuthPerCountryTrns(),languageLocale));
		             mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatenameTrns());
		             mapDto.setAuthPerStatenameNameTrns(commonBo.getStateName(regForm.getAuthPerStatenameTrns(),languageLocale));
		             mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrictTrns());
		             mapDto.setAuthPerDistrictNameTrns(commonBo.getDistrictName(regForm.getAuthPerDistrictTrns().trim(),languageLocale));
                	
                 }
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
                	 //individual
                	 mapDto.setFnameTrns(regForm.getFnameTrns());
                	 if(regForm.getMnameTrns().equalsIgnoreCase(""))
                		 mapDto.setMnameTrns("-");
                	 else
                	     mapDto.setMnameTrns(regForm.getMnameTrns());
                	 mapDto.setLnameTrns(regForm.getLnameTrns());
                	 mapDto.setGendarTrns(regForm.getGendarTrns());
                	 if(regForm.getGendarTrns().equalsIgnoreCase("m")){
                		 //mapDto.setSelectedGender("Male");
                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
                  		 
                  		 }else{
                  			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
                  			
                  		 }
                	 }
                	 else{
                		// mapDto.setSelectedGender("Female");
                		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                  			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
                  		 
                  		 }else{
                  			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
                  			
                  		 }
                	 }
                	 if(regForm.getAgeTrns().equalsIgnoreCase(""))
                		 mapDto.setAgeTrns("-");
                	 else
                	     mapDto.setAgeTrns(regForm.getAgeTrns());
                	 mapDto.setFatherNameTrns(regForm.getFatherNameTrns());
                	 if(regForm.getMotherNameTrns().equalsIgnoreCase(""))
                		 mapDto.setMotherNameTrns("-");
                	 else
                	     mapDto.setMotherNameTrns(regForm.getMotherNameTrns());
                	 if(regForm.getSpouseNameTrns().equalsIgnoreCase(""))
                		 mapDto.setSpouseNameTrns("-");
                	 else
                	     mapDto.setSpouseNameTrns(regForm.getSpouseNameTrns());
                	 mapDto.setNationalityTrns(regForm.getNationalityTrns());
                	 if(regForm.getPostalCodeTrns().equalsIgnoreCase(""))
                		 mapDto.setPostalCodeTrns("-");
                	 else
                	     mapDto.setPostalCodeTrns(regForm.getPostalCodeTrns());
                	 
                	 if(regForm.getIndphnoTrns().equalsIgnoreCase(""))
                		 mapDto.setPhnoTrns("-");
                	 else
                	     mapDto.setPhnoTrns(regForm.getIndphnoTrns());
                	 
                	 if(regForm.getIndmobnoTrns().equalsIgnoreCase(""))
                		 mapDto.setMobnoTrns("-");
                	 else
                         mapDto.setMobnoTrns(regForm.getIndmobnoTrns());
                	 
                	 if(regForm.getEmailIDTrns().equalsIgnoreCase(""))
                		 mapDto.setEmailIDTrns("-");
                	 else
                	     mapDto.setEmailIDTrns(regForm.getEmailIDTrns());
                	 mapDto.setSelectedPhotoId(regForm.getListIDTrns());
                	 mapDto.setSelectedPhotoIdName(regForm.getListIDNameTrns());
                	 mapDto.setIdnoTrns(regForm.getIdnoTrns());
                	 
                	
                	 mapDto.setOgrNameTrns("-");
                	 mapDto.setAuthPerNameTrns("-");
                	 mapDto.setIndAuthPersn(regForm.getFnameTrns()+" "+regForm.getLnameTrns());
                	 mapDto.setOrgaddressTrns(regForm.getIndaddressTrns());
                	 mapDto.setSelectedCountry(regForm.getIndcountryTrns());
                	 mapDto.setSelectedCountryName(commonBo.getCountryName("1", languageLocale));
                	 mapDto.setSelectedState(regForm.getIndstatenameTrns());
                	 mapDto.setSelectedStateName(commonBo.getStateName("1", languageLocale));
                	 mapDto.setSelectedDistrict(regForm.getInddistrictTrns());
                	 mapDto.setSelectedDistrictName(commonBo.getDistrictName(regForm.getInddistrictTrns(),languageLocale));
                	 mapDto.setSelectedCategoryName(regForm.getSelectedCategoryNameTrns())   ;   
                	 mapDto.setIndCategoryTrns(regForm.getIndCategoryTrns());
                	 mapDto.setSelectedOccupationName(commonBo.getOccupationName(regForm.getOccupationIdTrns(),languageLocale));
                	 mapDto.setOccupationIdTrns(regForm.getOccupationIdTrns());
                	// mapDto.setRelationshipList(regForm.)
                	 mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleAreaTrns());
                	 
                   	 if(regForm.getIndCategoryTrns().equalsIgnoreCase(RegInitConstant.ST_CONSTANT)){
                   	 if(regForm.getIndScheduleAreaTrns()!=null){
                   	 if(regForm.getIndScheduleAreaTrns().equalsIgnoreCase("Y")){
                   		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
                   		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
                       		 }
                   	 }else{
                   		//mapDto.setIndScheduleAreaTrnsDisplay("No");
                   		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
                       		 }else{
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
                	 
                	 if(regForm.getIndDisabilityTrns().equalsIgnoreCase(""))
                   	 {
                   		mapDto.setIndDisabilityTrns("-");
                   	 }
                   	 else if(regForm.getIndDisabilityTrns().equalsIgnoreCase("Y"))
                   	 {mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
                	    //mapDto.setIndDisabilityTrns("Yes");
                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
                       		 }
                	    if(regForm.getIndDisabilityDescTrns().equalsIgnoreCase(""))
                	    {
                	    mapDto.setIndDisabilityDescTrns("-");
                	    }else
                	    {
                	    	mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDescTrns());
                	    }
                   	 }
                	    else if(regForm.getIndDisabilityTrns().equalsIgnoreCase("N"))
                	    {mapDto.setIndDisabilityIdTrns(regForm.getIndDisabilityTrns());
                	    	//mapDto.setIndDisabilityTrns("No");
                	    	
                	    	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                          	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
                             		 }else{
                             			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
                             		 }
                	    	
                	    }
                	                 	 
                	 mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
                	 
                	 //mapDto.setUserDOBTrns(UserRegistrationBD.getOracleDate(regForm.getUserDayTrns(), regForm.getUserMonthTrns(), regForm.getUserYearTrns()));
	                   	
	                   	if(regForm.getIndMinorityTrns().equalsIgnoreCase(""))
	                   	 {
	                   		mapDto.setIndMinorityTrns("-");
	                   	 }
	                   	 else if(regForm.getIndMinorityTrns().equalsIgnoreCase("Y"))
	                   	 {
	                	    //mapDto.setIndMinorityTrns("Yes");
	                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
	                       		 }else{
	                       			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
	                       		 }
	                	    
	                   	 }
	                	    else if(regForm.getIndMinorityTrns().equalsIgnoreCase("N"))
	                	    {
	                	    	//mapDto.setIndMinorityTrns("No");
	                	    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                        	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
	                           		 }else{
	                           			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
	                           		 }
	                	    	
	                	    }
	                   	
	                   	//mapDto.setIndMinorityTrns("");
	                   	if(regForm.getIndPovertyLineTrns().equalsIgnoreCase(""))
	                   	 {
	                   		mapDto.setIndPovertyLineTrns("-");
	                   	 }
	                   	 else if(regForm.getIndPovertyLineTrns().equalsIgnoreCase("Y"))
	                   	 {
	                	    //mapDto.setIndPovertyLineTrns("Yes");
	                	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                    	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
	                       		 }else{
	                       			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
	                       		 }
	                	    
	                   	 }
	                	    else if(regForm.getIndPovertyLineTrns().equalsIgnoreCase("N"))
	                	    {
	                	    	//mapDto.setIndPovertyLineTrns("No");
	                	    	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                        	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
	                           		 }else{
	                           			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
	                           		 }
	                	    	
	                	    }
	                   	//mapDto.setIndPovertyLineTrns("");
	                   	
	                	
                	                 	 
                	 mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
                	 
                	 mapDto.setRelationshipTrns(regForm.getRelationshipTrns());
		             mapDto.setRelationshipNameTrns(commonBo.getRelationshipName(Integer.toString(regForm.getRelationshipTrns()),languageLocale));
                 }
               //below code for uploads other than collector's permission no.
                	
                	mapDto.setU2DocumentNameTrns(regForm.getU2DocumentNameTrns());
            		mapDto.setU2DocumentSizeTrns(regForm.getU2DocumentSizeTrns());
            		mapDto.setU2FilePathTrns(regForm.getU2FilePathTrns());
            		mapDto.setU2DocContentsTrns(regForm.getU2DocContentsTrns());
            		mapDto.setU2PartyOrPropTrns(regForm.getU2PartyOrPropTrns());
            		mapDto.setU2UploadTypeTrns(regForm.getU2UploadTypeTrns());
            		
            		if(regForm.getDeedTypeFlag()==0){
            			//BELOW CODE FOR EXECUTANT
                		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
             		{
                			mapDto.setU3DocumentNameTrns(regForm.getU3DocumentNameTrns());
                    		mapDto.setU3DocumentSizeTrns(regForm.getU3DocumentSizeTrns());
                    		mapDto.setU3FilePathTrns(regForm.getU3FilePathTrns());
                    		mapDto.setU3DocContentsTrns(regForm.getU3DocContentsTrns());
                    		mapDto.setU3PartyOrPropTrns(regForm.getU3PartyOrPropTrns());
                    		mapDto.setU3UploadTypeTrns(regForm.getU3UploadTypeTrns());
                    		
                    		mapDto.setU4DocumentNameTrns(regForm.getU4DocumentNameTrns());
                    		mapDto.setU4DocumentSizeTrns(regForm.getU4DocumentSizeTrns());
                    		mapDto.setU4FilePathTrns(regForm.getU4FilePathTrns());
                    		mapDto.setU4DocContentsTrns(regForm.getU4DocContentsTrns());
                    		mapDto.setU4PartyOrPropTrns(regForm.getU4PartyOrPropTrns());
                    		mapDto.setU4UploadTypeTrns(regForm.getU4UploadTypeTrns());
                    		
                    		mapDto.setU10DocumentNameTrns(regForm.getU10DocumentNameTrns());
                    		mapDto.setU10DocumentSizeTrns(regForm.getU10DocumentSizeTrns());
                    		mapDto.setU10FilePathTrns(regForm.getU10FilePathTrns());
                    		mapDto.setU10DocContentsTrns(regForm.getU10DocContentsTrns());
                    		mapDto.setU10PartyOrPropTrns(regForm.getU10PartyOrPropTrns());
                    		mapDto.setU10UploadTypeTrns(regForm.getU10UploadTypeTrns());
             		}
                	//BELOW CODE FOR EXECUTANT POA HOLDER
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
             		{
             			mapDto.setU5DocumentNameTrns(regForm.getU5DocumentNameTrns());
                    		mapDto.setU5DocumentSizeTrns(regForm.getU5DocumentSizeTrns());
                    		mapDto.setU5FilePathTrns(regForm.getU5FilePathTrns());
                    		mapDto.setU5DocContentsTrns(regForm.getU5DocContentsTrns());
                    		mapDto.setU5PartyOrPropTrns(regForm.getU5PartyOrPropTrns());
                    		mapDto.setU5UploadTypeTrns(regForm.getU5UploadTypeTrns());
                    		
                    		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentNameTrns());
                    		mapDto.setU6DocumentSizeTrns(regForm.getU6DocumentSizeTrns());
                    		mapDto.setU6FilePathTrns(regForm.getU6FilePathTrns());
                    		mapDto.setU6DocContentsTrns(regForm.getU6DocContentsTrns());
                    		mapDto.setU6PartyOrPropTrns(regForm.getU6PartyOrPropTrns());
                    		mapDto.setU6UploadTypeTrns(regForm.getU6UploadTypeTrns());
                    		
                    		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentNameTrns());
                    		mapDto.setU7DocumentSizeTrns(regForm.getU7DocumentSizeTrns());
                    		mapDto.setU7FilePathTrns(regForm.getU7FilePathTrns());
                    		mapDto.setU7DocContentsTrns(regForm.getU7DocContentsTrns());
                    		mapDto.setU7PartyOrPropTrns(regForm.getU7PartyOrPropTrns());
                    		mapDto.setU7UploadTypeTrns(regForm.getU7UploadTypeTrns());
                    		
                    		mapDto.setSrOfficeNameTrns(regForm.getSrOfficeNameTrns());
                    		mapDto.setPoaRegNoTrns(regForm.getPoaRegNoTrns());
                    		mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaRegTrns()));
             		}
                		
             		//BELOW CODE FOR CLAIMANT
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
             		{
             			mapDto.setU8DocumentNameTrns(regForm.getU8DocumentNameTrns());
                    		mapDto.setU8DocumentSizeTrns(regForm.getU8DocumentSizeTrns());
                    		mapDto.setU8FilePathTrns(regForm.getU8FilePathTrns());
                    		mapDto.setU8DocContentsTrns(regForm.getU8DocContentsTrns());
                    		mapDto.setU8PartyOrPropTrns(regForm.getU8PartyOrPropTrns());
                    		mapDto.setU8UploadTypeTrns(regForm.getU8UploadTypeTrns());
                    		
                    		mapDto.setU11DocumentNameTrns(regForm.getU11DocumentNameTrns());
                    		mapDto.setU11DocumentSizeTrns(regForm.getU11DocumentSizeTrns());
                    		mapDto.setU11FilePathTrns(regForm.getU11FilePathTrns());
                    		mapDto.setU11DocContentsTrns(regForm.getU11DocContentsTrns());
                    		mapDto.setU11PartyOrPropTrns(regForm.getU11PartyOrPropTrns());
                    		mapDto.setU11UploadTypeTrns(regForm.getU11UploadTypeTrns());
             		}
                		
             		//BELOW CODE FOR CLAIMANT POA HOLDER
             		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
             		{
                		
                		mapDto.setU9DocumentNameTrns(regForm.getU9DocumentNameTrns());
                		mapDto.setU9DocumentSizeTrns(regForm.getU9DocumentSizeTrns());
                		mapDto.setU9FilePathTrns(regForm.getU9FilePathTrns());
                		mapDto.setU9DocContentsTrns(regForm.getU9DocContentsTrns());
                		mapDto.setU9PartyOrPropTrns(regForm.getU9PartyOrPropTrns());
                		mapDto.setU9UploadTypeTrns(regForm.getU9UploadTypeTrns());
                		
             		}
            		}
                 
                
                 map = regForm.getMapTransactingParties();
            
                 int count=regForm.getMapKeyCount();
                 if(count==0)
                	 count=1;
             
                 if(map.containsKey(Integer.toString(count))){
	                 
                	 count++;
                	 map.put(Integer.toString(count), mapDto);
                	 
	                 }
                 else
                	 map.put(Integer.toString(count), mapDto);
	                  
                 
                 regForm.getMapTransPartyDbInsertion().put(Integer.toString(count), mapDto);
                 
                 regForm.setMapKeyCount(count);
                 
                 //regForm.setAddMoreCounter(count);
                 regForm.setAddMoreCounter(map.size());
                                // key="key";
                         
                
                                 regForm.setMapTransactingParties(map);
                                 
                
                 
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
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
                	 regForm.setStatenameNameTrns(commonBo.getStateName("1",languageLocale));
                	 //commonDto.setCountryTrns(commonBo.getCountry());
                	 regForm.setActionName("");
                	 regForm.setOwnershipShareTrns("");
					 regForm.setRelationWithOwnerTrns("");
					 regForm.setShareOfPropTrns("");
					 regForm.setCountryTrns("1");
					 regForm.setCountryNameTrns(commonBo.getCountryName("1",languageLocale));
					 regForm.setExecutantClaimantTrns(0);
					 
					 
					 regForm.setAuthPerGendarTrns("M");
                	 regForm.setAuthPerFatherNameTrns("");
                	 regForm.setAuthPerRelationshipTrns(0);
		             regForm.setAuthPerAddressTrns("");
		             regForm.setAuthPerDistrictTrns("");
		            
		             commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
                	 
                 }
                 if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
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
                	 //commonDto.setIndcountryTrns(commonBo.getCountry());
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
					  //  regForm.setIndMinorityTrns("N");
					    regForm.setIndCategoryTrns("");
					   	regForm.setOccupationIdTrns("");
					 /*regForm.setCountryTrns("INDIA");
						regForm.setCountryNameTrns("INDIA");
						regForm.setStatenameTrns("MP");
						regForm.setStatenameNameTrns("MADHYA PRADESH");*/
						regForm.setIndcountryTrns("1");
						regForm.setIndcountryNameTrns(commonBo.getCountryName("1",languageLocale));
						regForm.setIndstatenameTrns("1");
						regForm.setIndstatenameNameTrns(commonBo.getStateName("1",languageLocale));
						
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
                   		commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
                   		
                   	 regForm.setExecutantClaimantTrns(0);
					  
                 }
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
                 if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
                		 mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG){
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
                 }
                 
                 regForm.setListAdoptedPartyTrns(null);
                 
                 if(regForm.getCommonDeed()==1){
						//if(regForm.getAddMoreTransParty().equalsIgnoreCase("Y")){
                	 //above commented because add more button will only be visible if   AddMoreTransParty is Y
							
							if(regForm.getAddPartyNewRole()==1){
								regForm.setCommonDeedRoleName("");
								regForm.setRoleSameAsPrevious(0);
							}else{
								regForm.setRoleSameAsPrevious(1);
							}
							
						//}else{
							
							//redirect to particulars of transaction page
							
						//}
							regForm.setAddPartyNewRole(0);
							regForm.setAddMoreTransParty("N");
					}
                 
                 regForm.setClaimantFlag(0);
                 regForm.setPoaHolderFlag(0);
                 forward = "transactingParty";
                }
				 if(RegInitConstant.DELETE_MORE_ACTION.equals(actionName)) {
					 
					 RegCommonDTO mapDto =new RegCommonDTO();
					//int roleCount=0;
					String[] trnsPrtyID= regForm.getHdnDeleteTrnsPrtyId().split(",");
					 
					 int count=regForm.getMapKeyCount()-1;
					 map=regForm.getMapTransactingParties();
					 
					 int deedId=regForm.getDeedID();
                     int appId=Integer.parseInt(commonBo.getApplicantRoleId(regForm.getHidnRegTxnId()));
					
					 for(int i = 0 ;i < trnsPrtyID.length ;i++) {
                          //correction of poa and owner count.
                         mapDto=(RegCommonDTO)map.get(trnsPrtyID[i]);
                
                         //FOLLOWING CODE FOR CORRECTION OF TOTAL SHARE OF PROPERTY
                         
                         int selectedRoleId=0;
                         if(mapDto.getPartyTypeTrns()!=null){
                         selectedRoleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                         }
                         int totShare=0;
                         
                         if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y"))
                         {
                       
                         //DEPOSIT OF TITLE DEED
                         if(deedId==RegInitConstant.DEED_DEPOSIT_OF_TITLE || (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
	  							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))
                        		 || deedId==RegInitConstant.DEED_PARTITION_NPV){
                        	 if(appId==RegInitConstant.ROLE_OWNER_SELF || appId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
            					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }
                        	 }
                         
                         }
                         //LEASE NPV DEED
                         if(deedId==RegInitConstant.DEED_LEASE_NPV){
                        	 if(appId==RegInitConstant.ROLE_LESSER_SELF || appId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
            					 if(selectedRoleId==RegInitConstant.ROLE_LESSER_SELF || selectedRoleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
            					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_LESSEE_SELF || appId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_LESSEE_SELF || selectedRoleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         
                         }
                       //MORTGAGE NPV DEED
                         if(deedId==RegInitConstant.DEED_MORTGAGE_NPV
                        		 || (regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
              							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
                        	 if(appId==RegInitConstant.ROLE_MORTGAGER_SELF || appId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGER_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_MORTGAGEE_SELF || appId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_SELF || selectedRoleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                       //POA NPV DEED
                         if(deedId==RegInitConstant.DEED_POA){
                        	 if(appId==RegInitConstant.ROLE_OWNER_SELF || appId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_POA_ACCEPTER || appId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_POA_ACCEPTER || selectedRoleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                       //SETTLEMENT NPV DEED
                         if(deedId==RegInitConstant.DEED_SETTLEMENT_NPV){
                        	 if(appId==RegInitConstant.ROLE_SETTLER_SELF || appId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_SETTLER_SELF || selectedRoleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                       //WILL NPV DEED
                         if(deedId==RegInitConstant.DEED_WILL_NPV){
                        	 if(appId==RegInitConstant.ROLE_TESTATOR_SELF || appId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_TESTATOR_SELF || selectedRoleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                         //TRANSFER OF LEASE NPV DEED
                         if(deedId==RegInitConstant.DEED_TRANSFER_LEASE_NPV){
                        	 if(appId==RegInitConstant.ROLE_TRANSFERER_SELF || appId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_TRANSFERER_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_TRANSFEREE_SELF || appId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_SELF || selectedRoleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                       //RECONV_MORTGAGE_NPV DEED
                         if(deedId==RegInitConstant.DEED_RECONV_MORTGAGE_NPV){
                        	 if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
            					 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }
                        	 }
                         
                         }
                         //SURRENDER OF LEASE NPV DEED
                         if(deedId==RegInitConstant.DEED_SURRENDER_LEASE_NPV){
                        	 if(appId==RegInitConstant.ROLE_SURRENDERER_SELF || appId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_SURRENDERER_SELF || selectedRoleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                         //TRUST NPV NP DEED
                         if(deedId==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P){
                        	 if(appId==RegInitConstant.ROLE_TRUSTEE || appId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
            					 if(selectedRoleId==RegInitConstant.ROLE_TRUSTEE || selectedRoleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
             						 
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }
                        	 }
                         
                         }
                       //DEED_AGREEMENT_MEMO_NPV DEED
                         if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
     							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1){
                        	 if(appId==RegInitConstant.ROLE_OWNER_SELF || appId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_OWNER_SELF || selectedRoleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                         
                         //COMPOSITION NPV DEED
                         if(deedId==RegInitConstant.DEED_COMPOSITION_NPV || deedId==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV
                        		 || deedId==RegInitConstant.DEED_SECURITY_BOND_NPV 
                        		 || (regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
     							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
                        	 
                        	 if(appId==RegInitConstant.ROLE_EXECUTANT_SELF || appId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_EXECUTANT_SELF || selectedRoleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_CLAIMANT_SELF || appId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_CLAIMANT_SELF || selectedRoleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                         //MORTGAGE NPV DEED
                         if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
                        		 && regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV){
                        	 if(appId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || appId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
             					 if(selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
             						totShare=regForm.getTotalShareOfProp();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfProp(totShare);
             						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropBuyer();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropBuyer(totShare);
             					 }
                        	 }else if(appId==RegInitConstant.ROLE_TRANS_PARTY_SELF || appId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
                        		 
                        		 if(selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_SELF || selectedRoleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER){
                        			 totShare=regForm.getTotalShareOfProp();
              						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
              						regForm.setTotalShareOfProp(totShare);
              						if(totShare!=100){
             							regForm.setApplicantRoleId(0);
             						}
             					 }else{
             						totShare=regForm.getTotalShareOfPropSellerSelf();
             						totShare=totShare-Integer.parseInt(mapDto.getShareOfPropTrns());
             						regForm.setTotalShareOfPropSellerSelf(totShare);
             					 }
                        	 }
                         }
                       
					 }else{
						 int count1;
						  //ADOPTION DEED
                         if(regForm.getDeedID()==RegInitConstant.DEED_ADOPTION){
     						
     						if(selectedRoleId==RegInitConstant.ROLE_ADOPTING_PARENT ||
     								selectedRoleId==RegInitConstant.ROLE_ADOPTING_PARENT_POA_HOLDER){
     							count1=regForm.getAdoptionDeedParty1Added();
     							count1--;
     							regForm.setAdoptionDeedParty1Added(count1);
     						}
     						if(selectedRoleId==RegInitConstant.ROLE_CHILD_ADOPTED ||
     								selectedRoleId==RegInitConstant.ROLE_CHILD_ADOPTED_POA_HOLDER){
     							count1=regForm.getAdoptionDeedParty2Added();
     							count1--;
     							regForm.setAdoptionDeedParty2Added(count1);
     						}
     						if(selectedRoleId==RegInitConstant.ROLE_CUSTODIAN ||
     								selectedRoleId==RegInitConstant.ROLE_CUSTODIAN_POA_HOLDER){
     							count1=regForm.getAdoptionDeedParty3Added();
     							count1--;
     							regForm.setAdoptionDeedParty3Added(count1);
     						}
     						
     						
     					}
                         //CANCELLATION DEED
                         if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
     						
     						if(selectedRoleId==RegInitConstant.ROLE_CANCELLATION_1 ||
     								selectedRoleId==RegInitConstant.ROLE_CANCELLATION_1_POA_HOLDER){
     							count1=regForm.getAdoptionDeedParty1Added();
     							count1--;
     							regForm.setAdoptionDeedParty1Added(count1);
     						}
     						if(selectedRoleId==RegInitConstant.ROLE_CANCELLATION_2 ||
     								selectedRoleId==RegInitConstant.ROLE_CANCELLATION_2_POA_HOLDER){
     							count1=regForm.getAdoptionDeedParty2Added();
     							count1--;
     							regForm.setAdoptionDeedParty2Added(count1);
     						}
     						
     						
     						
     					}
					 }
                         map.remove(trnsPrtyID[i]);
                         
                         
                     }
					 regForm.setMapTransactingParties(map);
                     
                     //above for display map
					 //below for insertion map
					 map=new HashMap();
                     map=regForm.getMapTransPartyDbInsertion();
 					
					 for(int j = 0 ;j < trnsPrtyID.length ;j++) {
                         logger.debug(trnsPrtyID[j]+" is removed...");
                         map.remove(trnsPrtyID[j]);
                         
                         
					 }
					 //regForm.setAddMoreCounter(count);
					 regForm.setAddMoreCounter(map.size());
					 regForm.setMapKeyCount(count);
					 
					 if(map.size()==0 && (regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
				 	   	    	(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
										 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )) )){
						 
						 regForm.setIsFirstPartyAddedFlag(0);
						 regForm.setAddMoreCounter(0);
						 
					 }else if(map.size()==0){
						 regForm.setAddMoreCounter(0);
					 }
					 
					 regForm.setMapTransPartyDbInsertion(map);
					 
					 
					
					 forward = "transactingParty";
					// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
					 request.setAttribute("deedId",regForm.getDeedID());
					 regForm.setActionName(RegInitConstant.NO_ACTION);
					 actionName=RegInitConstant.NO_ACTION;
					//return mapping.findForward(forward);		
				 }
				 if(RegInitConstant.ADD_MORE_PARTICULAR_ACTION.equals(actionName)) {
	                
	                 RegCommonDTO mapDto =new RegCommonDTO();
	            
	                mapDto.setParticularName(regForm.getParticularName());
	                mapDto.setParticularDesc(regForm.getParticularDesc());
	                 
	                       
	              HashMap   partMap = regForm.getMapParticulars();
	            
	                 int count=regForm.getAddMoreParticularCounter();
	                 if(count==0)
	                	 count=1;
	             
	                 if(partMap.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 partMap.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 partMap.put(Integer.toString(count), mapDto);
		                  
	                 
	                 
	                 regForm.setAddMoreParticularCounter(count);
	                 regForm.setMapParticulars(partMap);
	                                 
	                regForm.setParticularName("");
	                regForm.setParticularDesc("");
	               
	                 
	                 forward = "reginitParticular";
	                }
				 
				 if(RegInitConstant.DEL_MORE_PARTICULAR_ACTION.equals(actionName)){

					 
					 //RegCommonDTO mapDto =new RegCommonDTO();
					//int roleCount=0;
					String[] trnsPrtyID= regForm.getHdnDeleteParticularID().split(",");
					 
					 //int count=regForm.getMapKeyCount()-1;
					HashMap partMap=regForm.getMapParticulars();
					int count=regForm.getAddMoreParticularCounter();
					
					 for(int i = 0 ;i < trnsPrtyID.length ;i++) {
                         
						 partMap.remove(trnsPrtyID[i]);
                         count--;
                         
                     }
					 regForm.setMapParticulars(partMap);
                     
                     //above for display map
					 //below for insertion map
					
					 
					 
					 regForm.setAddMoreParticularCounter(count);
					 //regForm.setMapKeyCount(count);
					 forward = "reginitParticular";
					// request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
					// request.setAttribute("deedId",regForm.getDeedID());
					// regForm.setActionName(RegInitConstant.NO_ACTION);
					 actionName=RegInitConstant.NO_ACTION;
					//return mapping.findForward(forward);		
				 
				 }
				 if(RegInitConstant.SAVE_PARTICULARS_ACTION.equals(actionName)){
					 

		                
	                 RegCommonDTO mapDto =new RegCommonDTO();
	            
	                mapDto.setParticularName(regForm.getParticularName());
	                mapDto.setParticularDesc(regForm.getParticularDesc());
	                 
	                       
	              HashMap   partMap = regForm.getMapParticulars();
	            
	                 int count=regForm.getAddMoreParticularCounter();
	                 if(count==0)
	                	 count=1;
	             
	                 if(partMap.containsKey(Integer.toString(count))){
		                 
	                	 count++;
	                	 partMap.put(Integer.toString(count), mapDto);
	                	 
		                 }
	                 else
	                	 partMap.put(Integer.toString(count), mapDto);
		                  
	                 
	                 
	                 regForm.setAddMoreParticularCounter(count);
	                 regForm.setMapParticulars(partMap);
	                 
	                 //ABOVE CODE FOR INSERTING LAST PARTICULAR IN MAP
	                 //BELOW CODE FOR INSERTION OF ALL THE PARTICULARS IN DB
	                 
	                
                     boolean insertionStatus=false;

                    
                        
                     	insertionStatus = 
                        	 commonBo.insertParticularsDetails(regForm.getMapParticulars(), regForm.getHidnRegTxnId(),regForm.getHidnUserId()); 
                          
                          if(!insertionStatus){
     							forward="reginitParticular";
     							regForm.setHidnRegTxnId(null);
     							actionName=RegInitConstant.NO_ACTION;
     	  						regForm.setActionName(RegInitConstant.NO_ACTION);
     	  						
     						}
                          else{
                        	  actionName=RegInitConstant.NEXT_TO_CONFIRM_ACTION;
                          }
                    
				 }
				 
				 if(RegInitConstant.CANCEL_PARTICULARS_ACTION.equals(actionName)){
					 
					 cancelAction(session,regForm);
						if(map!=null){
						if(!map.isEmpty())
							map.clear();
						}
						//count=0;
						//keyCount=0;
						forward="welcome";
						return mapping.findForward(forward);
					 
				 }
				 if(RegInitConstant.RESET_PARTICULARS_ACTION.equals(actionName)){
					 
					 regForm.setParticularName("");
					 regForm.setParticularDesc("");
					 
					 forward = "reginitParticular";
					 
				 }
				 
				 //following code for party type
				if(request.getAttribute("regFormDashboard")==null ){   //this line was added for dashboard
				 String roleType=null;
				 String roleTypeTrns=null;
				 if(request.getParameter("partyType")!=null && !request.getParameter("partyType").toString().equalsIgnoreCase("")){
					
					 roleType=(String)request.getParameter("partyType");
				 		regForm.setPartyType(roleType); //setting role id.
				 		if(regForm.getCommonDeed()!=1){
				 		String[] claimantArr=commonBo.getClaimantFlag(roleType);
              		//int claimantFlag=Integer.parseInt(claimantArr[0]);
 			 		regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
 			 		regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				 		}
					 
					
				 }
				 else 
				 {
					 roleType="0";
				 }
				 if(request.getParameter("partyTypeTrns")!=null &&  !request.getParameter("partyTypeTrns").toString().equalsIgnoreCase("")){
					
					 roleTypeTrns=(String)request.getParameter("partyTypeTrns");
				 		regForm.setPartyTypeTrns(roleTypeTrns); //setting role id.
				 		if(regForm.getCommonDeed()!=1){
				 		String[] claimantArr=commonBo.getClaimantFlag(roleTypeTrns);
              		//int claimantFlag=Integer.parseInt(claimantArr[0]);
 			 		regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
 			 		regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				 		}
					 
				 }
				 else 
				 {
					 roleTypeTrns="0";
				 }		
				 
				
				 if((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName)) 
			             || RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)){
		 
		             
					 
					 if((roleType != null || RegInitConstant.NEXT_ACTION.equals(actionName))&& !(RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					 {request.setAttribute("roleId", Integer.parseInt(roleType));
					 request.setAttribute("roleIdTrns", "0"); 
					 }
					 if(roleTypeTrns != null && (RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName)|| RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName)))
					 request.setAttribute("roleIdTrns", Integer.parseInt(roleTypeTrns)); 
					
					 
	             }
				 }
				
				if(RegInitConstant.NO_ACTION.equals(actionName)){
				if(regForm.getPartyRoleId()!=null && !regForm.getPartyRoleId().equalsIgnoreCase("") && !regForm.getPartyRoleId().equalsIgnoreCase("null")){
					request.setAttribute("roleIdTrns", Integer.parseInt(regForm.getPartyRoleId())); 
				}
				else
					request.setAttribute("roleIdTrns", 0); 
				}
				 
				 if(RegInitConstant.NO_ACTION.equals(actionName))
				 { //forward ="reginitMapping";
					 
				 }
				 else if(RegInitConstant.CHANGE_TRANSACTING_PARTY_ACTION.equals(actionName) || 
		            	 RegInitConstant.TRNS_ROLE_TYPE_ACTION.equals(actionName) || 
		            	 RegInitConstant.ADD_MORE_ACTION.equals(actionName) ||
		            	 RegInitConstant.DELETE_MORE_ACTION.equals(actionName))
		            	  forward ="transactingParty";
		         else if(RegInitConstant.NEXT_TO_MAPPING_ACTION.equals(actionName))
		        	 forward ="reginitMapping";
		         else if(RegInitConstant.NEXT_TO_PROPERTY_ACTION.equals(actionName)||
		        		 RegInitConstant.NEXT_TO_PROPERTY_ACTION_THROUGH_MAP.equals(actionName))
		        	 forward ="reginitProperty";
		         else if(RegInitConstant.NEXT_TO_PAYMENT_ACTION.equals(actionName))
		        	 {//forward ="reginitPayment";
		        	 regForm.setActionName(RegInitConstant.NO_ACTION);
		        	 }
		        // else
		        //     forward ="success";
				 
			
			if(RegInitConstant.RESET_APPLICANT_ACTION.equals(actionName)){
				 
				//String partyType = regForm.getListAdoptedParty();
				
				//session.setAttribute("partyType", partyType);	
			    //resetToken(request);
				
				regForm.setBankName("");
				regForm.setBranchName("");
				regForm.setBankAddress("");
				regForm.setBankAuthPer("");
				regForm.setBankLoanAmt(0);
				regForm.setBankSancAmt(0);
				
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
				//regForm.setAuthPerGendar("M");
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
				regForm.setShareOfProp("");
				regForm.setOwnershipShare("");
				regForm.setRelationWithOwner("");
						
				regForm.setInstrument("");				
				commonDto.setState(commonBo.getState("1",languageLocale));
				commonDto.setDistrict(commonBo.getDistrict("1",languageLocale));
				commonDto.setIndstate(commonBo.getState("1",languageLocale));
				commonDto.setInddistrict(commonBo.getDistrict("1",languageLocale));
				commonDto.setAppType(commonBo.getAppType(languageLocale));
				commonDto.setCountry(commonBo.getCountry(languageLocale));
				commonDto.setIndcountry(commonBo.getCountry(languageLocale));
				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
				commonDto.setDeedType(commonBo.getDeedType(languageLocale));	
				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
				commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
				commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(),languageLocale));
				commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendar(),languageLocale));
				if(regForm.getCommonDeed()==1){
					commonDto.setExecutantClaimant(commonBo.getExecutantClaimant(languageLocale,regForm.getInstID()));
					
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
                 //following reseting owner details
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
  				
 				
 				regForm.setDeclareShare("true");
 				regForm.setHdnDeclareShareCheck("Y");
 				
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
               		
               		regForm.setAuthPerAddress("");
          			 regForm.setAuthPerCountry("1");
          			 regForm.setAuthPerDistrict("");
          			 regForm.setAuthPerStatename("1");
          			 regForm.setAuthPerFatherName("");
          			 regForm.setAuthPerRelationship(0);
          			 regForm.setAuthPerGendar("M");
                 //session.setAttribute("commonDto", commonDto);
 				 regForm.setCommonDto(commonDto);
 				 //session.setAttribute("regForm", regForm);
			  
 				forward ="success";
			  
			}		
			if(RegInitConstant.RESET_TRANSACTING_ACTION.equals(actionName)){
				
				if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
					request.setAttribute("roleIdTrns",regForm.getPartyTypeTrns());
				}
					else{
						request.setAttribute("roleIdTrns",0);
					}
					//String partyTypeTrns = regForm.getListAdoptedPartyTrns();
					
					//session.setAttribute("partyType", partyTypeTrns);	
				    //resetToken(request);
			
				if(regForm.getCommonDeed()==1 && regForm.getRoleSameAsPrevious()==0){
					regForm.setCommonDeedRoleName("");
				}
				
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
					commonDto.setStateTrns(commonBo.getState("1",languageLocale));
					commonDto.setDistrictTrns(commonBo.getDistrict("1",languageLocale));
					commonDto.setIndstateTrns(commonBo.getState("1",languageLocale));
					commonDto.setInddistrictTrns(commonBo.getDistrict("1",languageLocale));
					commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
					commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
					commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
					commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
					commonDto.setIdProf(commonBo.getIdProf(languageLocale));
					commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
					commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
					commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
					regForm.setRelationshipTrns(0);
					
       			 regForm.setExecutantClaimantTrns(0);
					//regForm.setIndPovertyLine("");
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
                
				 //session.setAttribute("commonDto", commonDto);
				 regForm.setCommonDto(commonDto);
			
				 //session.setAttribute("regForm", regForm);
			
				 forward ="transactingParty";
			  
			}		
			
			if(RegInitConstant.RESET_MAPPING_ACTION.equals(actionName)){
				regForm.setActionName(RegInitConstant.NO_ACTION);
				regForm.setOwnerId("");
			    //session.setAttribute("regForm", regForm);
			    forward ="reginitMapping";
			  
			}	
            if(RegInitConstant.RESET_PROPERTY_ACTION.equals(actionName)){
            	regForm.setActionName(RegInitConstant.NO_ACTION);
            	PropertyValuationDTO dto= new PropertyValuationDTO();
            	RegCompletDTO comDto= new RegCompletDTO();
            	regForm.setRegcompletDto(comDto);
            	regForm.setPropertyDTO(dto);
            	
			    //session.setAttribute("regForm", regForm);
            	request.setAttribute("fromAdju", regForm.getFromAdjudication());
			    forward ="reginitProperty";
			  
			}
            if(RegInitConstant.APPLICANT_USERID_CHECK_ACTION.equalsIgnoreCase(actionName)){
            	regForm.setActionName(RegInitConstant.NO_ACTION);
            	if(regForm.getHdnapplicantUserIdCheck().equalsIgnoreCase("checked")){
            		
            		String[] appRegDetls=commonBo.getApplicantRegistrationDetls(session.getAttribute("UserId").toString());
            		regForm.setFname(appRegDetls[0].trim());
            		if(appRegDetls[1].trim().equalsIgnoreCase("null")){
            			regForm.setMname("");
            		}else{
            		regForm.setMname(appRegDetls[1].trim());
            		}
            		regForm.setLname(appRegDetls[2].trim());
            		regForm.setGendar(appRegDetls[3].trim());
            		commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(),languageLocale));
            		if(appRegDetls[4].trim().equalsIgnoreCase("null")){
            			regForm.setNationality("");
            		}else{
            		regForm.setNationality(appRegDetls[4].trim());
            		}
            		//regForm.setIndcountry("1");  
            		commonDto.setIndcountry(commonBo.getCountry(languageLocale));
            		commonDto.setIndstate(commonBo.getState("1",languageLocale));
            		commonDto.setInddistrict(commonBo.getDistrict("1",languageLocale));
            		regForm.setCommonDto(commonDto);
            		//regForm.setIndstatename(appRegDetls[6].trim());
            		if(appRegDetls[6].trim().equalsIgnoreCase("1")){
            		regForm.setInddistrict(appRegDetls[7].trim());
            		regForm.setInddistrictNameTrns(commonBo.getDistrictName(appRegDetls[7].trim(),languageLocale));
            		}else{
            			regForm.setInddistrict("");
                		regForm.setInddistrictNameTrns("");
            		}
            		regForm.setIndaddress(appRegDetls[8].trim());
            		if(appRegDetls[9].trim().equalsIgnoreCase("null")){
            			regForm.setPostalCode("");
            		}else{
            		regForm.setPostalCode(appRegDetls[9].trim());
            		}
            		if(appRegDetls[10].trim().equalsIgnoreCase("null")){
            			regForm.setIndphno("");
            		}else{
            		regForm.setIndphno(appRegDetls[10].trim());
            		}
            		//regForm.setIndphno(appRegDetls[10].trim());
            		regForm.setIndmobno(appRegDetls[11].trim());
            		regForm.setEmailID(appRegDetls[12].trim());
            		if(appRegDetls[13].trim().equalsIgnoreCase("null")){
            			regForm.setListID("");
            			regForm.setListIDName("");
            		}else{
            		regForm.setListID(appRegDetls[13].trim());
            		regForm.setListIDName(commonBo.getPhotoIdProofName(appRegDetls[13].trim(),languageLocale));
            		}
            		//regForm.setListID(appRegDetls[13].trim());
            		if(appRegDetls[14].trim().equalsIgnoreCase("null")){
            			regForm.setIdno("");
            		}else{
            		regForm.setIdno(appRegDetls[14].trim());
            		}
            		regForm.setFatherName(appRegDetls[15].trim());
            		if(appRegDetls[16].trim().equalsIgnoreCase("null")){
            			regForm.setMotherName("");
            		}else{
            		regForm.setMotherName(appRegDetls[16].trim());
            		}
            		if(appRegDetls[17].trim().equalsIgnoreCase("null")){
            			regForm.setSpouseName("");
            		}else{
            		regForm.setSpouseName(appRegDetls[17].trim());
            		}
            		
            		if(appRegDetls[18].trim().equalsIgnoreCase("null")){
            			regForm.setAge("");
            		}else{
            		regForm.setAge(appRegDetls[18].trim());
            		}
            		
            		//regForm.setAge(appRegDetls[18].trim());
            		//String dob=appRegDetls[18].trim();
            		/*String[] finalDate=commonBo.parseStringDatefromDB(appRegDetls[18].trim());
            		regForm.setUserDay(finalDate[0]);
            		regForm.setUserMonth(finalDate[1]);
            		regForm.setUserYear(finalDate[2]);*/
            		
            		if(appRegDetls[19].trim().equalsIgnoreCase("null")){
            		regForm.setIndCategory("");
            		regForm.setSelectedCategoryName("");
            		}else{
            			regForm.setIndCategory(appRegDetls[19].trim());
                		regForm.setSelectedCategoryName(commonBo.getCategoryName(appRegDetls[19].trim(),languageLocale));
            		}
            		
            		
            		if(appRegDetls[20].trim().equalsIgnoreCase("null")){
            		regForm.setOccupationId("");
            		regForm.setSelectedOccupationName("");
            		}else{
            			regForm.setOccupationId(appRegDetls[20].trim());
                		regForm.setSelectedOccupationName(commonBo.getOccupationName(appRegDetls[20].trim(),languageLocale));
            		}
            		
            		
            		
            		//occupation
            		//regForm.setIndcountryName(commonBo.getCountryName(appRegDetls[5].trim()));
            		//regForm.setIndstatenameName(commonBo.getStateName(appRegDetls[6].trim()));
            		//regForm.setInddistrictName(commonBo.getDistrictName(appRegDetls[7].trim()));
            		//regForm.setListIDName(commonBo.getPhotoIdProofName(appRegDetls[13].trim(),languageLocale));
            		//regForm.setCountry("INDIA");
        			//regForm.setCountryName("INDIA");
        			//regForm.setStatename("MP");
        			//regForm.setStatenameName("MADHYA PRADESH");
        			regForm.setIndcountry("1");
        			regForm.setIndcountryName(commonBo.getCountryName("1", languageLocale));
        			regForm.setIndstatename("1");
        			regForm.setIndstatenameName(commonBo.getStateName("1", languageLocale));
            }
            	else{
            		regForm.setApplicantUserIdCheck(null);
            		regForm.setFname("");
            		regForm.setMname("");
            		regForm.setLname("");
            		regForm.setGendar("M");
            		commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendar(),languageLocale));
            		regForm.setNationality("");
            		//regForm.setIndcountry("");
            		//regForm.setIndstatename("");
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
            		//commonDto.setIndstate(new ArrayList());
    				//commonDto.setInddistrict(new ArrayList());
    				
    				//commonDto.setIndcountry(commonBo.getCountry());
    				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
    				regForm.setCommonDto(commonDto);
    				//regForm.setIndcountry("INDIA");
        			//regForm.setIndcountryName("");
        			//regForm.setIndstatename("MP");
        			//regForm.setIndstatenameName("");
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
            	
			    //session.setAttribute("regForm", regForm);
			    forward ="success";
			  
			}
			String label=null;
			label=(String)request.getParameter("label");
			if(label!=null && label!="")
			{
			if(label.equalsIgnoreCase("display")){
				
				RegCommonDTO mapDtoDisp =new RegCommonDTO();
				String key=request.getParameter("key");
				map2 = regForm.getMapTransactingPartiesDisp();
				
				if(!map2.isEmpty())
					map2.clear();
				
					mapDtoDisp=(RegCommonDTO)regForm.getMapTransactingParties().get(key);
					map2.put(key,mapDtoDisp);
					
					regForm.setMapTransactingPartiesDisp(map2);
					String confirmationFlag=null;
					confirmationFlag=(String)request.getParameter("confirmationFlag");
					if(confirmationFlag!=null && confirmationFlag!="")
					{
					if(confirmationFlag.equalsIgnoreCase("true")){
						regForm.setConfirmationFlag("01");
					}
					}else
						regForm.setConfirmationFlag("00");
					
					if(regForm.getDeedID()!=0){
        				request.setAttribute("deedId", regForm.getDeedID());
        				}else{
        					request.setAttribute("deedId", 0);
        				}
        				if(mapDtoDisp.getPartyTypeTrns()!=null && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")){
        				request.setAttribute("roleId", mapDtoDisp.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleId", 0);
        				}
        				/*if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("")){
            			request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
        				}else{
        					request.setAttribute("roleIdTrns", 0);
        				}*/
				
        				
        				int roleId=0;
   	                 
   	                 			
					if(mapDtoDisp.getPartyTypeTrns()!=null && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("") && !mapDtoDisp.getPartyTypeTrns().equalsIgnoreCase("null")){
					roleId=Integer.parseInt(mapDtoDisp.getPartyTypeTrns());
					}
   	                 
   	                 //String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
        				
				//String[] claimantArr=commonBo.getClaimantFlag(mapDtoDisp.getPartyTypeTrns());
         		//int claimantFlag=Integer.parseInt(claimantArr[0]);
				//regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
				//regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
				//request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
				
				forward="displayRegDetls";
			}
			}
			

			 //ok action
                if(RegInitConstant.OK_ACTION.equals(actionName)) {
				 
                	request.setAttribute("roleId", regForm.getPartyType());
                	if(regForm.getPartyTypeTrns()!=null && regForm.getPartyTypeTrns()!="")
    				request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
                	else 
                		request.setAttribute("roleIdTrns", "0");
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "transactingParty";
				 
			 }
              //confirmation ok action
                if(RegInitConstant.CONFIRMATION_OK_ACTION.equals(actionName)) {
                	
                	
                	
                	
                	regForm.setPartyModifyFlag(0);
                	regForm.setExtraDetlsModifyFlag(0);
                	
					String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
            		if(deedInstArray!=null && deedInstArray.length>0){
            			
            			request.setAttribute("deedId", deedInstArray[0].trim());
            			request.setAttribute("instId", deedInstArray[1].trim());
            			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
            			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
            			
            		}else {
            			request.setAttribute("deedId", 0);
            			request.setAttribute("instId", 0);
            			regForm.setDeedID(0);
            			regForm.setInstID(0);
            		}
				 
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "reginitConfirm";
				 
				
				 
			 }
                /*if(RegInitConstant.TRANS_PARTY_VIEW_ACTION.equals(actionName)) {
   				 
                 regForm.setActionName(RegInitConstant.NO_ACTION);
				 forward = "transactingPartyView";
				 
			 }*/
		
             //view from confirmation screen.
                if(request.getParameter("view")!=null){
                String view=request.getParameter("view").toString();
                if(view.equalsIgnoreCase(RegInitConstant.APPLICANT_VIEW)){
                	forward="applicantView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.TRANSACTING_PARTY_VIEW)){
                	forward="transactingPartyView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.MAPPING_VIEW)){
                	forward="mappingView";
                }
                if(view.equalsIgnoreCase(RegInitConstant.PROPERTY_VIEW)){
                	forward="propertyView";
                }
                }
                //return from payment
              //return from payment
                //String paymentTxnId=null;
                //String strNonJudECode=null;
            	//String modName=null;
            	
                if(request.getParameter("paymentStatus")!=null && request.getAttribute("eCode")==null)
                {
                	NumberFormat obj=new DecimalFormat("#0.00");
                	double paidAmount=0;
                	if(request.getParameter("paymentStatus").equalsIgnoreCase("P")){
                		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                		if(deedInstArray!=null && deedInstArray.length>0){
                			
                			request.setAttribute("deedId", deedInstArray[0].trim());
                			request.setAttribute("instId", deedInstArray[1].trim());
                			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			
                		}else {
                			request.setAttribute("deedId", 0);
                			request.setAttribute("instId", 0);
                			regForm.setDeedID(0);
                			regForm.setInstID(0);
                		}
                    	
                	//following added on 13feb for new payment modality.
                	//String paymentStatus=request.getParameter("paymentStatus");
                	//logger.debug("payment status---------->"+paymentStatus);
                	//end of addition on 13feb for new payment modality.
                	
                		String[] paymentArray=commonBo.getPaymentTxnId(regForm.getHidnRegTxnId());
                		if(paymentArray!=null && paymentArray.length>0){
                			
                		
                			paidAmount=Double.parseDouble(paymentArray[1].trim());
                			regForm.setRegInitPaymntTxnId(paymentArray[0].trim());
                			
                			double finalPaid=Double.parseDouble(regForm.getTotalPaidAmount())+paidAmount;
                			regForm.setTotalPaidAmount(obj.format(finalPaid));
                			
                			double finalBalance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
                			regForm.setTotalBalanceAmount(obj.format(finalBalance));
                			
                			if(paidAmount>=Double.parseDouble(paymentArray[2].trim()) || finalBalance<=0){
                				regForm.setPaymentCompleteFlag(1);
                				
                				//e stamping redirection at this point.
                				request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
                				request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
                				request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
                				request.setAttribute("eStampRegFuncId", "023");
                				request.setAttribute("eStampRegPurpose", regForm.getPurpose());
                				request.setAttribute("sourceModFlag", "PVN");
                				forward = "reginitEstamp";
                				return mapping.findForward(forward);	
                				
                				//below code hard coded
                				/*boolean insertEstampMapDetls=false;
                        		insertEstampMapDetls=commonBo.insertEstampMappingDetls("111", regForm);
                        		
                        		if(insertEstampMapDetls){
                        		regForm.setRegInitEstampCode("222");
                        		forward = "reginitConfirm";
                        		}else{
                        			forward = "failure";
                        		}*/
                				
                			}
                			
                			
                			
                			
                		}
                      	
                		forward = "reginitConfirm";
                	}
                }
              
                //following code will execute wen flow returns from estamping
                
                if(request.getAttribute("eCode")!=null ){
                	
                	String eStampDets=request.getAttribute("eCode").toString();
                	
                	if(eStampDets!=null && !eStampDets.equalsIgnoreCase("")){
                		
                		String[] eStampDetsArr=eStampDets.split("~");
                		
                		//insertion code
                		boolean insertEstampMapDetls=false;
                		insertEstampMapDetls=commonBo.insertEstampMappingDetls(eStampDetsArr[1].trim(), regForm);
                		
                		if(insertEstampMapDetls){
                		regForm.setRegInitEstampCode(eStampDetsArr[0].trim());
                		forward = "reginitConfirm";
                		}else{
                			forward = "failure";
                		}
                	}
                	
                	
                	
                }
                
                
              //final action after payment.
                	if(RegInitConstant.FINAL_ACTION.equals(actionName)) {
	 
                			commonDto.setIndcountry(commonBo.getCountry(languageLocale));
                			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(),languageLocale));
                			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(),languageLocale));
                			regForm.setPostalCountry("1");
                			regForm.setPostalState("");
                			regForm.setPostalDistrict("");
                			regForm.setHdnPostalAddress1(regForm.getPostalAddress1());
                	
                	
                	//change payment flag to c here.
                	
                	
                	String currDate=commonBo.getCurrDateTime();
                	
                	System.out.println("curr date 2 : "+currDate);
                	
                	regForm.setCurrDateTime(currDate);
                	
                	String msg="";
                	if(Double.parseDouble(regForm.getTotalduty())>0)
                	{
				 
				 
                	String param1[]={RegInitConstant.PAYMENT_FLAG_COMPLETED,regForm.getHidnRegTxnId()};
                	boolean updatePaymentStatus = 
                  	  commonBo.insertTxnDetails(param1);
                	
                	logger.debug("payment status updated as c: "+updatePaymentStatus);
                	
                	if(updatePaymentStatus)
                		msg=regForm.getHidnRegTxnId().toString();
                	
                	else
                		msg=RegInitConstant.ERROR_MSG;
                	}
                	else{
                		
                		boolean updatePaymentStatus = 
                        	  commonBo.insertTxnDetailsFinalAction(regForm.getHidnRegTxnId());
                      	
                      	logger.debug("payment status updated as c: "+updatePaymentStatus);
                		
                      	if(updatePaymentStatus)
                    		msg=regForm.getHidnRegTxnId().toString();
                    	
                    	else
                    		msg=RegInitConstant.ERROR_MSG;
                		
                	      	
                		
                		//msg=regForm.getHidnRegTxnId().toString();
                	}
                	
                 regForm.setActionName(RegInitConstant.NO_ACTION);
                 request.setAttribute("regInitTxnId", regForm.getHidnRegTxnId());
                 forward = "success1";
				 
			 }
                //COMPLETE TRANSACTION
                if(RegInitConstant.COMPLETE_APPLICATION_ACTION.equals(actionName)) {
                	
                	
                	
                	
                	boolean completeTransaction = commonBo.updateTransactionStatus(regForm);
                	logger.debug("complete transactiooooon status: "+completeTransaction);
                	if(completeTransaction){
                		/*request.setAttribute("label", "userSlotbook");
        				request.setAttribute("modName", "Slot");
        				request.setAttribute("from", "regInit");
        				request.setAttribute("regInitId", regForm.getHidnRegTxnId());*/
        				
                		cancelAction(session,regForm);
        				if(map!=null){
        				if(!map.isEmpty())
        					map.clear();
        				}
        				//count=0;
        				//keyCount=0;
        				//forward="welcome";
        				
        				
        				//label=userSlotbook&modName=Slot&from=regInit&regInitId=<%=request.getAttribute("regInitTxnId")%>
        				
        				
        				
        				forward="welcome";
        				//return mapping.findForward(forward);
                	}
                	else
                		forward="failure";
                	
                	

                 //forward = "success1";
				 
			 }
                //for creating dashboard
              /*  if(request.getParameter("modName")!=null ){
                	if(request.getParameter("modName").equalsIgnoreCase("Registration Process")){
                		cancelAction(session,regForm);
                		
                			if(request.getParameter("fromAdju")!=null){
                			
                			if(request.getParameter("fromAdju").equalsIgnoreCase("Y")){
                    			
                    			regForm.setFromAdjudication(1);
                    			
                    			
                    		}else{
                    			regForm.setFromAdjudication(0);
                    		}
                			
                			
                		}else{
                			regForm.setFromAdjudication(0);
                		}
                		ArrayList pendingApplicationList = commonBo.getPendingRegApps(session.getAttribute("UserId").toString(),regForm.);
    					if(pendingApplicationList.size()==0)
    						regForm.setPendingRegApplicationList(null);
    					else
                		regForm.setPendingRegApplicationList(pendingApplicationList);
    					request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
    					forward="reginitDashboard";
    					
    				}
                }*/
                //after click on any pending application if from dashboard
                if(request.getParameter("hdnApplicationId")!=null ){/*
                	
        			int adjuFlag=0;
                	regForm.setHidnRegTxnId(request.getParameter("hdnApplicationId"));
                	regForm.setHidnUserId(userId);
                	//String appStatus[] = new String[5];
                	
                	try{
                	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                	String purpose=commonBo.getEStampPurpose(regForm.getHidnRegTxnId());
                	if(purpose!=null && !purpose.equalsIgnoreCase("")){
                		regForm.setPurpose(purpose);
                	}else{
                		regForm.setPurpose("");
                	}
                	
                		
            		if(deedInstArray!=null && deedInstArray.length>0){
            			
            			request.setAttribute("deedId", deedInstArray[0].trim());
            			request.setAttribute("instId", deedInstArray[1].trim());
            			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
            			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
            			if(deedInstArray[2].trim().equals("-")){
            			regForm.setExmpID("");
            			regForm.setHdnExAmount("");
            			}else{
            				regForm.setExmpID(deedInstArray[2].trim());
                			//regForm.setHdnExAmount(deedInstArray[2].trim());
            			}
            			if(deedInstArray[3].trim()!=null && !deedInstArray[3].trim().equalsIgnoreCase("")){
            				adjuFlag=1;
            				regForm.setAdjudicationNumber(deedInstArray[3].trim());
            			}
            			
            		}else {
            			request.setAttribute("deedId", 0);
            			request.setAttribute("instId", 0);
            			regForm.setDeedID(0);
            			regForm.setInstID(0);
            			regForm.setExmpID("");
            			regForm.setHdnExAmount("");
            		}
            		
            		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID())));
            		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID())));
            		//below code for exemptions
            		
            		String exemptions = regForm.getExmpID();
            		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions));
            		
            		
            		
            		//below code for getting bank details
            		
            		if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
            		
            		
            		String[] bankArr=commonBo.getBankDetails(regForm.getHidnRegTxnId());
            		
            		if(bankArr!=null){
            			
            			regForm.setBankName(bankArr[0].trim());
            			regForm.setBranchName(bankArr[1].trim());
            			regForm.setBankAddress(bankArr[2].trim());
            			regForm.setBankAuthPer(bankArr[3].trim());
            			regForm.setBankLoanAmt(Integer.parseInt(bankArr[4].trim()));
            			regForm.setBankSancAmt(Integer.parseInt(bankArr[5].trim()));
            			
            			
            		}else{
            			regForm.setBankName("-");
            			regForm.setBranchName("-");
            			regForm.setBankAddress("-");
            			regForm.setBankAuthPer("-");
            			regForm.setBankLoanAmt(Integer.parseInt("0"));
            			regForm.setBankSancAmt(Integer.parseInt("0"));
            			
            			
            		}
            		
            		
            		
            		
            		}
            		//end of code for getting bank details
            		
            		
            		
            		
            		
            		
            		
            		
            		HashMap propMap =new HashMap();
            		propMap=regForm.getMapPropertyTransParty();
            		logger.debug("in confirmation action----------------------->");
            		ArrayList propertyIdList=new ArrayList();
            		if(adjuFlag==1){
            			propertyIdList=commonBo.getPropertyIdMarketValAdju(regForm.getAdjudicationNumber());
            		}else{
            		    propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
            		}
            		double totalMarketVal=0;
            		
            		int numberOfProperties=propertyIdList.size();
            		
            		for(int i=0;i<propertyIdList.size();i++){
            			
            			ArrayList row_list=new ArrayList();
            			row_list=(ArrayList)propertyIdList.get(i);
            			String propIds=row_list.toString();
            			propIds=propIds.substring(1, propIds.length()-1);
            			logger.debug("property id and market value list-->"+propIds);
            			String propId[]=propIds.split(",");
            			
            			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
            			
            			
            			
            			ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId());
            			         			
            			
            			for(int j=0;j<transPartyDets.size();j++){
            				
            				CommonDTO dto=new CommonDTO();
            				dto=(CommonDTO)transPartyDets.get(j);
            				logger.debug("transacting party list  role------>"+dto.getRole());
            				logger.debug("transacting party list  name------>"+dto.getName());
            				logger.debug("transacting party list  id------>"+dto.getId());
            			
            			}
            			logger.debug("property id------>"+propId[0].trim());
            			logger.debug("market value------>"+propId[1].trim());
            			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
            	
            		}
            		
            		NumberFormat obj=new DecimalFormat("#0.00");
            		regForm.setTotalMarketValue(obj.format(totalMarketVal));
            		regForm.setMapPropertyTransParty(propMap);
            		
            		String dutyListArr[]=new String[9];
            		
            		if(adjuFlag==1){
            			dutyListArr=commonBo.getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());
            		}
            		else{
            			dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
            		}
            		
            		double totalPaidAmt=0;
            		ArrayList paidAmtList=commonBo.getAllPaidAmounts(regForm.getHidnRegTxnId());
            		for(int i=0;i<paidAmtList.size();i++){
            			ArrayList row_list=new ArrayList();
            			row_list=(ArrayList)paidAmtList.get(i);
            			String amnts=row_list.toString();
            			amnts=amnts.substring(1, amnts.length()-1);
            			logger.debug("paid amount list-->"+amnts);
            			String amntsArr[]=amnts.split(",");
            			
            			totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
            		}
            		regForm.setTotalPaidAmount(obj.format(totalPaidAmt));
            		
            			if(numberOfProperties>0)
            			{
            				
            				regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
            				double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
            				regForm.setTotalBalanceAmount(obj.format(balance));
            				
            				if(Double.parseDouble(regForm.getTotalBalanceAmount())==0){
            					regForm.setPaymentCompleteFlag(1);
            				}else{
            					regForm.setRegInitEstampCode(null);
            				}
            				
            			regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
            			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
            			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
            			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
            			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
            			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
            			            			
            			regForm.setIsMultiplePropsFlag(0);
                		regForm.setIsDutyCalculated(1);
                		
                		//if(adjuFlag==0){
                			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                			else
                				regForm.setMarketValueShares(Double.toString(0.0));	
                			
                			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                			else
                				regForm.setDutyPaid(Double.toString(0.0));
                			
                			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                			else
                				regForm.setRegPaid(Double.toString(0.0));
                			}
                			else{
                				regForm.setMarketValueShares(Double.toString(0.0));	
                				regForm.setDutyPaid(Double.toString(0.0));
                				regForm.setRegPaid(Double.toString(0.0));
                			}
                		
            			}else{
            				
            				regForm.setStampduty1("-");
                			regForm.setNagarPalikaDuty("-");
                			regForm.setPanchayatDuty("-");
                			regForm.setUpkarDuty("-");
                			regForm.setTotalduty("-");
                			regForm.setRegistrationFee("-");
                			regForm.setTotalPayableAmount("-");
                			regForm.setTotalPaidAmount("-");
                			regForm.setTotalBalanceAmount("-");
            				
            			}
            				
            				else if(numberOfProperties>1 && (dutyListArr==null)){
            			
            				
            				regForm.setIsDutyCalculated(0);
            				regForm.setIsMultiplePropsFlag(1);
            				
            			}else if(numberOfProperties>1 && dutyListArr!=null){
            				
            				regForm.setIsDutyCalculated(1);
            				regForm.setIsMultiplePropsFlag(1);
            				
            				regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
                			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
                			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
                			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
                			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
                			
                			//if(adjuFlag==0){
                			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                			else
                				regForm.setMarketValueShares(Double.toString(0.0));	
                			
                			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                			else
                				regForm.setDutyPaid(Double.toString(0.0));
                			
                			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                			else
                				regForm.setRegPaid(Double.toString(0.0));
                			}
                			else{
                				regForm.setMarketValueShares(Double.toString(0.0));	
                				regForm.setDutyPaid(Double.toString(0.0));
                				regForm.setRegPaid(Double.toString(0.0));
                			}
            				
            			}
                		
                		regForm.setRegInitPaymntTxnId(null);
            			
                }catch(Exception e){
                	
                	logger.debug(e);
                	e.printStackTrace();
                	forward="failure";
            		return mapping.findForward(forward);	
                	
                }
            		
            		forward="reginitConfirm";
            		return mapping.findForward(forward);	
                	
              */}
              //delete application from dashboard
              /*  if(request.getParameter("hdnDelApplicationId")!=null ){
                	
                	String appId=request.getParameter("hdnDelApplicationId");
                	
                	boolean applicationDetlsDeltd=commonBo.deleteSelectedAppDetails(appId);
                	
                	logger.debug("Registration Initiation Application deleted :- "+applicationDetlsDeltd);
                	if(applicationDetlsDeltd){
                		ArrayList pendingApplicationList = commonBo.getPendingRegApps(session.getAttribute("UserId").toString());
    					if(pendingApplicationList.size()==0)
    						regForm.setPendingRegApplicationList(null);
    					else
                		regForm.setPendingRegApplicationList(pendingApplicationList);
                	}
                	request.setAttribute("pendingApplicationList", regForm.getPendingRegApplicationList());
					forward="reginitDashboard";
                	
                }*/
              //end of delete application from dashboard
                //ADD MULTIPLE PROPERTIES
                if(request.getParameter("multipleProps")!=null ){
                	if(request.getParameter("multipleProps").equalsIgnoreCase("Y")){
                		regForm.setDeclareShare("true");
                   		regForm.setHdnDeclareShareCheck("Y");
                		regForm.setMapTransactingParties(new HashMap());
                		regForm.setIsMultiplePropsFlag(1);
                		// code for clearing field values.
                		regForm.setListAdoptedPartyTrns(null);
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
                        	 regForm.setStatenameNameTrns("MADHYA PRADESH");
                        	 //commonDto.setCountryTrns(commonBo.getCountry());
                        	 regForm.setActionName("");
                        	 regForm.setOwnershipShareTrns("");
        					 regForm.setRelationWithOwnerTrns("");
        					 regForm.setShareOfPropTrns("");
        					 regForm.setCountryTrns("1");
        					 regForm.setCountryNameTrns("INDIA");
                  
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
                        	 //commonDto.setIndcountryTrns(commonBo.getCountry());
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
        					  //  regForm.setIndMinorityTrns("N");
        					    regForm.setIndCategoryTrns("");
        					   	regForm.setOccupationIdTrns("");
        					 /*regForm.setCountryTrns("INDIA");
        						regForm.setCountryNameTrns("INDIA");
        						regForm.setStatenameTrns("MP");
        						regForm.setStatenameNameTrns("MADHYA PRADESH");*/
        						regForm.setIndcountryTrns("1");
        						regForm.setIndcountryNameTrns("INDIA");
        						regForm.setIndstatenameTrns("1");
        						regForm.setIndstatenameNameTrns("MADHYA PRADESH");
        						
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
                        	 
                        	 regForm.setTotalShareOfPropBuyer(0);
                        	 regForm.setTotalShareOfPropSellerSelf(0);
                         
                		//add code for redirection to transacting parties screen
           				if(regForm.getIsMultiplePropsFlag()==1)
        					{
        						//FOLLOWING CODE FOR INSERTING APPLICANT DETAILS IN HASHMAP
        						map=regForm.getMapTransactingParties();
        						
        						if( (regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P) ||
        								(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
        			  							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))){
        							regForm.setMapTransactingParties(new HashMap());
        							regForm.setTotalShareOfProp(0);
        							regForm.setAddMoreCounter(0);
        						}else{
                        		regForm.setMapTransactingParties(commonBo.insertApplicantDetsInMap
                        				(map,"",
                        				 regForm.getHidnRegTxnId(),regForm,languageLocale));
                        		
                        		//FOLLOWING CODE FOR FETCHING APPLICANT ROLE ID.
        						//disabling applicant role in multiple properties.
        						String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
        						logger.debug("applicant role id---------->"+applicantRoleId);
        						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
        						//
        						int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
    			
        						regForm.setTotalShareOfProp(totalShare);
                        		
                        		
        						}
                        		//regForm.setMapTransPartyDbInsertion(commonBo.insertApplicantDetsInMap
                        		//		(map,key,
                               	//			 regForm.getHidnRegTxnId(),regForm));
                        		//keyCount=regForm.getMapTransactingParties().size();
                        		
                        		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                        		
                        		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                    			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                    			if(deedInstArray[2].trim().equals("-")){
                        			regForm.setExmpID("");
                        			regForm.setHdnExAmount("");
                        			}else{
                        				regForm.setExmpID(deedInstArray[2].trim());
                            			regForm.setHdnExAmount(deedInstArray[2].trim());
                        			}
                        		
                        		commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(),regForm.getInstID(),languageLocale));
        						commonDto.setAppTypeTrns(commonBo.getAppType(languageLocale));
        						commonDto.setCountryTrns(commonBo.getCountry(languageLocale));
        						commonDto.setIndcountryTrns(commonBo.getCountry(languageLocale));
        						commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
        						regForm.setDeedId(Integer.toString(regForm.getDeedID()));
        						regForm.setHidnUserId(userId);
        						
        						//FOLLOWING CODE FOR INSERTING PROPERTY DETAILS THROUGH PV INTO DB
        						//boolean multiplePropDetlsInserted;
      						    //multiplePropDetlsInserted = commonBo.insrtMultiplePropDetls(regForm);
        						//logger.debug("multiple property details insertion status---------->"+multiplePropDetlsInserted);
        						
        						
        						
        						forward ="transactingParty";
        					}
        				
                    
                		
                	}
                		
                	
                }
                //END
              //NEXT TO CONFIRMATION SCREEN
                if(request.getParameter("confirmation")!=null || RegInitConstant.NEXT_TO_CONFIRM_ACTION.equals(actionName)){
                	//if(request.getParameter("confirmation").equalsIgnoreCase("Y")){
                	
                		
                		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                		if(deedInstArray!=null && deedInstArray.length>0){
                			
                			request.setAttribute("deedId", deedInstArray[0].trim());
                			request.setAttribute("instId", deedInstArray[1].trim());
                			
                			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			//regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                			
                			if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
                			
                			regForm.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
                				
                			}
                			
                		}else {
                			request.setAttribute("deedId", 0);
                			request.setAttribute("instId", 0);
                			regForm.setDeedID(0);
                			regForm.setInstID(0);
                			regForm.setExmpID("");
                			regForm.setHdnExAmount("");
                		}
                		
                		logger.debug("deed id"+request.getAttribute("deedId"));
                		logger.debug("inst id"+request.getAttribute("instId"));
                		
                		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),languageLocale));
                		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),languageLocale));
                		//below code for exemptions
                		
                		String exemptions = regForm.getExmpID();
                		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions));
                		
                		
                		
                		//below code for getting extra details
                		
                		//if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
                		//		regForm.getDeedID()==RegInitConstant.DEED_TRUST ){
                		
                		
                		
                		
                		commonBo.getExtraDetls(regForm,languageLocale);
                		
                		if(regForm.getFromAdjudication()==1){
                			//session.setAttribute("fnName","Adjudication");
                			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            					session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_ENGLISH);
            				}else{
            					session.setAttribute("fnName",RegInitConstant.FUNCTION_ADJU_HINDI);
            				}
                		}
                		
                		HashMap propMap =new HashMap();
                		propMap=regForm.getMapPropertyTransParty();
                		//int numberOfProperties;
                		//logger.debug("in confirmation action----------------------->");
                		if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_POA ||
                				regForm.getDeedID()==RegInitConstant.DEED_SETTLEMENT_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_WILL_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_COMPOSITION_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_SECURITY_BOND_NPV ||
                				regForm.getDeedID()==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
                			    (regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P ) ||
                			    (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
             							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )) ||
             					(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
             							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                     			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
                     					&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
                             	(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
                             			&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
                			
                			
                			ArrayList propertyIdList=commonBo.getPropertyIdMarketVal(regForm.getHidnRegTxnId());
                    		//numberOfProperties=propertyIdList.size();
                    		//double totalMarketVal=0;
                    		for(int i=0;i<propertyIdList.size();i++){
                    			
                    			ArrayList row_list=new ArrayList();
                    			row_list=(ArrayList)propertyIdList.get(i);
                    			String propIds=row_list.toString();
                    			propIds=propIds.substring(1, propIds.length()-1);
                    			//logger.debug("property id and market value list-->"+propIds);
                    			String propId[]=propIds.split(",");
                    			
                    			//totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
                    			
                    				
                    			regForm.setPropListPVDeed(null);
                    			ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale,regForm.getDeedID(),regForm.getInstID());
                    			for(int j=0;j<transPartyDets.size();j++){
                    				
                    				CommonDTO dto=new CommonDTO();
                    				dto=(CommonDTO)transPartyDets.get(j);
                    				logger.debug("transacting party list  role------>"+dto.getRole());
                    				logger.debug("transacting party list  name------>"+dto.getName());
                    				logger.debug("transacting party list  id------>"+dto.getId());
                    			
                    			}
                    			logger.debug("property id------>"+propId[0].trim());
                    			//logger.debug("market value------>"+propId[1].trim());
                    			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
                    			
                    	
                    		}
                    		
                    		
                    		
                    		regForm.setMapPropertyTransParty(propMap);	
                			
                			
                			
                		}
                		else
                		{        		
                		if(regForm.getDeedTypeFlag()==0){
                			
                		ArrayList propertyIdList=commonBo.getPropertyListNonPropDeed(regForm.getHidnRegTxnId());
                		regForm.setPropListNonPropDeed(propertyIdList);
                		
                		}
                		else if(regForm.getCommonDeed()==1){
                			ArrayList particularIdList=commonBo.getParticularList(regForm.getHidnRegTxnId());  
                    		regForm.setPropListNonPropDeed(particularIdList);
                		}
                		else{
                			regForm.setPropListNonPropDeed(null);
                		}
                	
                		ArrayList transPartyDets=commonBo.getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale, regForm.getDeedID(), regForm.getInstID());
	         			regForm.setTransPartyListNonPropDeed(transPartyDets);
            			
            			for(int j=0;j<transPartyDets.size();j++){
            				
            				CommonDTO dto=new CommonDTO();
            				dto=(CommonDTO)transPartyDets.get(j);
            				logger.debug("transacting party list  role------>"+dto.getRole());
            				logger.debug("transacting party list  name------>"+dto.getName());
            				logger.debug("transacting party list  id------>"+dto.getId());
            			
            			}
            			
                }
            			
            			
                		
                		NumberFormat obj=new DecimalFormat("#0.00");
                		//regForm.setTotalMarketValue(obj.format(totalMarketVal));
                		//regForm.setMapPropertyTransParty(propMap);
                		
                		//commented by shruti for time being until table is created
                		
                		//FOLLOWING CODE FOR DUTY.
                		//if(regForm.getIsMultiplePropsFlag()!=1){
                			//IN CASE OF SINGLE PROPERTY
                			String dutyListArr[]=commonBo.getNonPropDutyDetls(commonBo.getDutyTxnId(regForm.getHidnRegTxnId()));
                			
                			if(dutyListArr!=null){
                			regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
                			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
                			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
                			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
                			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
                			
                			regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                			regForm.setTotalPaidAmount(obj.format(Double.parseDouble("0")));
                			regForm.setTotalBalanceAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                			}else{

                    			regForm.setStampduty1(obj.format(0));
                    			regForm.setNagarPalikaDuty(obj.format(0));
                    			regForm.setPanchayatDuty(obj.format(0));
                    			regForm.setUpkarDuty(obj.format(0));
                    			regForm.setTotalduty(obj.format(0));
                    			regForm.setRegistrationFee(obj.format(0));
                    			
                    			regForm.setTotalPayableAmount(obj.format(0));
                    			regForm.setTotalPaidAmount(obj.format(Double.parseDouble("0")));
                    			regForm.setTotalBalanceAmount(obj.format(0));
                    			
                			}
                			
                			String considAmt=commonBo.getConsidAmtTitle(regForm.getHidnRegTxnId());
                			if(considAmt!=null){
                				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(considAmt)));
                				regForm.setExtraFieldLabel(commonBo.getExtraFieldLabel(Integer.toString(regForm.getInstID()),languageLocale));
                			}else{
                				regForm.setConsiAmountTrns("-");
                				regForm.setExtraFieldLabel(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE);
                			}
                			/*if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase(""))
                    			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                    			else
                    				regForm.setMarketValueShares(Double.toString(0.0));	
                    			
                    			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase(""))
                    			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                    			else
                    				regForm.setDutyPaid(Double.toString(0.0));
                    			
                    			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase(""))
                    			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                    			else
                    				regForm.setRegPaid(Double.toString(0.0));*/
                			
                			/*String[] param={regForm.getHidnRegTxnId(),dutyListArr[0].trim(),dutyListArr[1].trim(),
                					         dutyListArr[2].trim(),dutyListArr[3].trim(),dutyListArr[4].trim(),
                					         dutyListArr[5].trim(),session.getAttribute("UserId").toString()};*/
                			
                			
                			boolean stampDutiesInserted=false;
                			stampDutiesInserted=commonBo.insertStampDuties(regForm,session.getAttribute("UserId").toString());
                			
                			if(!stampDutiesInserted){
                				regForm.setStampduty1("-");
                    			regForm.setNagarPalikaDuty("-");
                    			regForm.setPanchayatDuty("-");
                    			regForm.setUpkarDuty("-");
                    			regForm.setTotalduty("-");
                    			regForm.setRegistrationFee("-");
                    			regForm.setConsiAmountTrns("-");
                			}/*else{
                				regForm.setStampduty1("-");
                    			regForm.setNagarPalikaDuty("-");
                    			regForm.setPanchayatDuty("-");
                    			regForm.setUpkarDuty("-");
                    			regForm.setTotalduty("-");
                    			regForm.setRegistrationFee("-");
                			}*/
                			regForm.setIsDutyCalculated(1);
                			regForm.setActionName("");
                			if(Double.parseDouble(regForm.getTotalBalanceAmount())==0){
            					regForm.setPaymentCompleteFlag(1);
            					
            					
            					//redirect to estamping for ecode generation here.
            					/*request.setAttribute("eStampRegId", regForm.getHidnRegTxnId());
                				request.setAttribute("eStampRegAmnt", regForm.getTotalPayableAmount());
                				request.setAttribute("eStampRegDistId", commonBo.getDistIdEstamp(regForm.getHidnRegTxnId()));
                				request.setAttribute("eStampRegFuncId", "023");
                				request.setAttribute("eStampRegPurpose", "abc");
                				request.setAttribute("sourceModFlag", "PVN");
                				forward = "reginitEstamp";
                				return mapping.findForward(forward);*/
            					
            					
            					regForm.setRegInitEstampCode(null);
            				}else{
            					regForm.setRegInitEstampCode(null);
            				}
                			
                		//}
                		//end of code commented by Shruti
                		
                		forward="reginitConfirm";
                	//}
                		
                }
                //following code for generating e code for 0 payable amount.
                if(RegInitConstant.GENERATE_ECODE_ZERO_AMNT_ACTION.equals(actionName) && request.getAttribute("eCode")==null){
                	
                	
                	//save purpose here
                	//following code for updating purpose
					if(regForm.getPurpose()!=null && !regForm.getPurpose().equalsIgnoreCase("")){
                		boolean updatePurpose=commonBo.updateEStampPurpose(regForm.getHidnRegTxnId(),regForm.getPurpose());
						if(updatePurpose){
							logger.debug("purpose updated before generating e code for zero amount.");
							
						}
						else{
							logger.debug("purpose not updated before generating e code for zero amount.");
							
						}
                	}
                	
                	
                	regForm.setActionName("");
                	//redirect to estamping for ecode generation here.
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
                	
                	
                }
                if(request.getParameter("label")!=null){
                	if(request.getParameter("label").equalsIgnoreCase("displayParty")){
                		
                		String partyId=request.getParameter("key");
                		request.setAttribute("deedId",regForm.getDeedID());
                		request.setAttribute("roleId",request.getParameter("role"));
                		regForm.setPartyType(request.getParameter("role").toString());
                		
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        logger.debug("request role ---->"+request.getAttribute("roleId"));
                     
                        regForm.setMapTransactingPartiesDisp
                		(commonBo.getPartyDetsForViewConfirm(regForm.getHidnRegTxnId(), partyId,regForm.getDeedID(),regForm.getDeedTypeFlag(),languageLocale,regForm.getInstID()));
                	
                		regForm.setConfirmationFlag("01");
                		
                		//String[] claimantArr=commonBo.getClaimantFlag(request.getParameter("role").toString());
                 		//int claimantFlag=Integer.parseInt(claimantArr[0]);
        				//regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
        				//regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
                		
                		forward="displayRegDetls";
                	}
                	if(request.getParameter("label").equalsIgnoreCase("displayProperty")){
                		
                		String propertyId=request.getParameter("key");
                		
                		regForm.setPropertyId(propertyId);  		
                		regForm.setMapPropertyTransPartyDisp
                		(commonBo.getPropertyDetsForViewConfirmNonProp(regForm.getHidnRegTxnId(), propertyId, languageLocale));
                		
                		//regForm.setConfirmationFlag("01");
                		
                		forward="propertyView";
                	}
                	if(request.getParameter("label").equalsIgnoreCase("displayExtra")){
                		
                		commonBo.getExtraDetls(regForm,languageLocale);
                		request.setAttribute("deedId",regForm.getDeedID());
                		                		
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        
                        regForm.setCallingAction("nonPropRegInit.do");		
                		forward="displayExtraDetls";
                	}
                	if(request.getParameter("label").equalsIgnoreCase("displayParticular")){
                		
                		String particularId=request.getParameter("key");
                		regForm.setParticularTxnId(particularId);
                		commonBo.getParticularDetails(regForm);
                		request.setAttribute("deedId",regForm.getDeedID());
                		                		
                        logger.debug("request deed ---->"+request.getAttribute("deedId"));
                        
                		forward="displayParticularDetls";
                	}
                	if(request.getParameter("label").equalsIgnoreCase("fromAdjudication")){
                   		forward="searchAdju";
                	}
                }
                //END
                //following code for skipping applicant details page in case of multiple properties.
                if(request.getParameter("modName")!=null ){/*
    				if(request.getParameter("modName").equalsIgnoreCase("RegistrationNonProperty")&& request.getAttribute("regFormDashboard")==null){
    					if(regForm.getIsMultiplePropsFlag()==1)
    					{
    						//FOLLOWING CODE FOR INSERTING APPLICANT DETAILS IN HASHMAP
    						map=regForm.getMapTransactingParties();
    						
                    		regForm.setMapTransactingParties(commonBo.insertApplicantDetsInMap
                    				(map,key,
                    				 regForm.getHidnRegTxnId(),regForm));
                    		regForm.setMapTransPartyDbInsertion(commonBo.insertApplicantDetsInMap
                    				(map,key,
                           				 regForm.getHidnRegTxnId(),regForm));
                    		//keyCount=regForm.getMapTransactingParties().size();
                    		
                    		String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                    		
                    		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                    		
                    		commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID()));
    						commonDto.setAppTypeTrns(commonBo.getAppType());
    						commonDto.setCountryTrns(commonBo.getCountry());
    						commonDto.setIndcountryTrns(commonBo.getCountry());
    						commonDto.setIdProfTrns(commonBo.getIdProf());
    						regForm.setDeedId(Integer.toString(regForm.getDeedID()));
    						regForm.setHidnUserId(userId);
    						
    						//FOLLOWING CODE FOR INSERTING PROPERTY DETAILS THROUGH PV INTO DB
    						boolean multiplePropDetlsInserted;
  						    multiplePropDetlsInserted = commonBo.insrtMultiplePropDetls(regForm);
    						logger.debug("multiple property details insertion status---------->"+multiplePropDetlsInserted);
    						
    						//FOLLOWING CODE FOR FETCHING APPLICANT ROLE ID.
    						//disabling applicant role in multiple properties.
    						String applicantRoleId=commonBo.getApplicantRoleId(regForm.getHidnRegTxnId());
    						logger.debug("applicant role id---------->"+applicantRoleId);
    						regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
    						//
    						int totalShare=commonBo.getShareOfPropList(regForm.getHidnRegTxnId());
			
    						regForm.setTotalShareOfProp(totalShare);
    						
    						forward ="transactingParty";
    					}
    				}
                */}
                //edited by shruti because deed id is hardcoded as of now
                commonDto.setPartyType(commonBo.getPartyType(regForm.getDeedID(),regForm.getInstID(),languageLocale));
               // commonDto.setPartyType(commonBo.getPartyType(1006));
                // end of code by shruti
                
                
                
				/*commonDto.setAppTypeTrns(commonBo.getAppType());
				commonDto.setCountryTrns(commonBo.getCountry());
				commonDto.setIndcountryTrns(commonBo.getCountry());
				commonDto.setIdProfTrns(commonBo.getIdProf());*/
                //after searching adjudication number
                if(RegInitConstant.ADJUDICATION_ACTION.equals(actionName)){
                	
        			regForm.setActionName(RegInitConstant.NO_ACTION);
                	String adjudicationId=regForm.getAdjudicationNumber();
                	
                	//following code for searching adjudication number and its status in database.
                	String[] adjuArray=commonBo.getAdjudicationStatus(adjudicationId);
                	
                	if(adjuArray!=null){
                		
                		if(adjuArray[1].trim().equalsIgnoreCase("Y")){
                			
                			regForm.setHidnUserId(userId);
                			
                			//for generating temporary Registration Initiation id.
                			Calendar currentDate = Calendar.getInstance();
  						  SimpleDateFormat formatter= new SimpleDateFormat("ddMMyy");
  						  String dateNow = formatter.format(currentDate.getTime());
  						  String  regTxnIdSeq= commonBo.getRegInitTxnIdSeq();
  						  String regTxnId=null;
  						  regTxnId=dateNow+regTxnIdSeq;
  						  regForm.setHidnRegTxnId(regTxnId);
                        	//code for insertion of registration id and status in initiation tables.
                        	boolean regIdUpdated=commonBo.updateAdjudicationRecords(regForm.getHidnRegTxnId(), adjudicationId, regForm.getHidnUserId());
                        	
  						  
  						  
                        	if(regIdUpdated)
                        	{
                        	try{
                        	String[] deedInstArray=commonBo.getDeedInstId(regForm.getHidnRegTxnId());
                    		if(deedInstArray!=null && deedInstArray.length>0){
                    			
                    			request.setAttribute("deedId", deedInstArray[0].trim());
                    			request.setAttribute("instId", deedInstArray[1].trim());
                    			regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
                    			regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
                    			if(deedInstArray[2].trim().equals("-")){
                    			regForm.setExmpID("");
                    			regForm.setHdnExAmount("");
                    			}else{
                    				regForm.setExmpID(deedInstArray[2].trim());
                        			regForm.setHdnExAmount(deedInstArray[2].trim());
                    			}
                    			
                    		}else {
                    			request.setAttribute("deedId", 0);
                    			request.setAttribute("instId", 0);
                    			regForm.setDeedID(0);
                    			regForm.setInstID(0);
                    			regForm.setExmpID("0");
                    			regForm.setHdnExAmount("0");
                    		}
                    		
                    		regForm.setDeedtype1(commonBo.getDeedName(Integer.toString(regForm.getDeedID()),languageLocale));
                    		regForm.setInstType(commonBo.getInstrumentName(Integer.toString(regForm.getInstID()),languageLocale));
                    		//below code for exemptions
                    		
                    		String exemptions = regForm.getExmpID();
                    		regForm.setSelectedExemptionList(commonBo.getExemptionList(exemptions));
                    		
                    		HashMap propMap =new HashMap();
                    		propMap=regForm.getMapPropertyTransParty();
                    		//logger.debug("in confirmation action----------------------->");
                    		
                    		ArrayList propertyIdList=commonBo.getPropertyIdMarketValAdju(adjudicationId);   //GETTING ADJUDICATED MARKET VALUES
                    		double totalMarketVal=0;
                    		
                    		int numberOfProperties=propertyIdList.size();
                    		
                    		for(int i=0;i<propertyIdList.size();i++){
                    			
                    			ArrayList row_list=new ArrayList();
                    			row_list=(ArrayList)propertyIdList.get(i);
                    			String propIds=row_list.toString();
                    			propIds=propIds.substring(1, propIds.length()-1);
                    			//logger.debug("property id and market value list-->"+propIds);
                    			String propId[]=propIds.split(",");
                    			
                    			totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
                    			
                    			
                    			
                    			ArrayList transPartyDets=commonBo.getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale,regForm.getDeedID(),regForm.getInstID());
                    			         			
                    			
                    			for(int j=0;j<transPartyDets.size();j++){
                    				
                    				CommonDTO dto=new CommonDTO();
                    				dto=(CommonDTO)transPartyDets.get(j);
                    				logger.debug("transacting party list  role------>"+dto.getRole());
                    				logger.debug("transacting party list  name------>"+dto.getName());
                    				logger.debug("transacting party list  id------>"+dto.getId());
                    			
                    			}
                    			logger.debug("property id------>"+propId[0].trim());
                    			//logger.debug("market value------>"+propId[1].trim());
                    			propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
                    	
                    		}
                    		
                    		NumberFormat obj=new DecimalFormat("#0.00");
                    		regForm.setTotalMarketValue(obj.format(totalMarketVal));
                    		regForm.setMapPropertyTransParty(propMap);
                    		
                    		
                    		
                    			String dutyListArr[]=commonBo.getAdjudicatedDutyDetls(adjudicationId);  //GETTING ADJUDICATED DUTIES
                    			
                    			if(numberOfProperties>0)
                    			{
                    			if(dutyListArr!=null){
                    				regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
                        			regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
                        			regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
                        			regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
                        			regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
                        			regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
                        			
                        			if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase("") && !dutyListArr[6].trim().equalsIgnoreCase("null"))
                            			regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
                            			else
                            				regForm.setMarketValueShares(Double.toString(0.0));	
                            			
                            			if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase("") && !dutyListArr[7].trim().equalsIgnoreCase("null"))
                            			regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
                            			else
                            				regForm.setDutyPaid(Double.toString(0.0));
                            			
                            			if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase("") && !dutyListArr[8].trim().equalsIgnoreCase("null"))
                            			regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
                            			else
                            				regForm.setRegPaid(Double.toString(0.0));
                            			
                    			if(numberOfProperties==1)
                    			{
                    			
                    			            			
                    			regForm.setIsMultiplePropsFlag(0);
                        		regForm.setIsDutyCalculated(1);
                        		
                    			}else if(numberOfProperties>1){
                    				
                    				regForm.setIsDutyCalculated(1);
                    				regForm.setIsMultiplePropsFlag(1);
                    				
                    				
                    			}
                    			
                        		regForm.setRegInitPaymntTxnId(null);
                        		

                        		//forward="reginitConfirmAdju";
                        		forward="reginitConfirm";
                        		
                    			}
                    			else{
                    				String msg="No duties found";
                            		regForm.setErrorMsg(msg);
                            		forward="searchAdju";
                    			}
                        		//return mapping.findForward(forward);	
                    			
                        	}else{
                				String msg="No property found";
                        		regForm.setErrorMsg(msg);
                        		forward="searchAdju";
                			}
                    			
                        }catch(Exception e){
                        	
                        	forward="failure";
                    		return mapping.findForward(forward);	
                        	
                        }
                			
                		}else{
                			
                			
                			try{
                				
                				throw new SQLException();
                				
                			}catch(SQLException e){
                            	
                            	forward="failure";
                        		return mapping.findForward(forward);	
                            	
                            }
                			
                			
                		}
                			
                			
                		}else{
                			String msg="Adjudication not completed.";
                    		regForm.setErrorMsg(msg);
                    		forward="searchAdju";
                			
                		}
                		
                		
                	}else{
                		
                		String msg="Invalid Adjudication number";
                		regForm.setErrorMsg(msg);
                		forward="searchAdju";
                		
                	}
                	
                	
            		
                	
              }
                
                if(RegInitConstant.DECLARE_SHARE_CHECK_ACTION.equalsIgnoreCase(actionName)){
                	regForm.setActionName(RegInitConstant.NO_ACTION);
                	if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y")){
                		
                		regForm.setDeclareShare("true");
                	
                }
                	else{
                		regForm.setShareOfProp("");
                		regForm.setShareOfPropTrns("");
                		regForm.setDeclareShare(null);
                	}
                	
                	
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("roleId", regForm.getPartyType());
    			    //forward ="success";
        			forward =regForm.getJspName();
    			  
    			}
                
                if(request.getParameter("dms")!=null ){
                	if(request.getParameter("dms").equalsIgnoreCase("retrieval")){
                		
                		if(request.getParameter("path")!=null){
                			
                			String partyType="";
                			String filePath=request.getParameter("path").toString();
                			logger.debug("retrieval path-->"+filePath);
                			
                			int indexOfDot=filePath.lastIndexOf(".");
                			//int totLen=filePath.length();
                			//dmsUtil.readImage(filePath);
                			String key=request.getParameter("key");
            				
            				String fileName=filePath.substring(indexOfDot-2, indexOfDot)+".";
            				
            				
            				
            				if(request.getParameter("roleType")!=null){
                				partyType=request.getParameter("roleType").toString();
                				regForm.setPartyType(partyType);
                				logger.debug("role type---------->"+partyType);
                				RegCommonDTO dtoObj=(RegCommonDTO)regForm.getMapTransactingPartiesDisp().get(key);
            					
                    			
                    			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CERTIFICATE)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getDocContentsTrns());

                    			}
                    			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PHOTO_PROOF)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getU2DocContentsTrns());

                    			}
                    			
                    			int roleId=Integer.parseInt(partyType);
                    			String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
                         		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                    			
                    			//BELOW CODE FOR EXECUTANT
                    	   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
                    			{
                    	   			
                    	   			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_EXECUTANT)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU3DocContentsTrns());

                        			}
                    	   			
                    	   			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU4DocContentsTrns());

                        			}
                    	   			
                    	   			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU10DocContentsTrns());

                        			}
                    	   			
                    	   			
                    	       		
                    	       	}
                    	   	//BELOW CODE FOR EXECUTANT POA HOLDER
                    			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
                    			{
                    				
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_NOTARIZED_AFFIDAVIT_ATTORNEY)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU5DocContentsTrns());

                        			}
                    				
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU6DocContentsTrns());

                        			}
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_EXECUTANT_PHOTO)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU7DocContentsTrns());

                        			}
                    				
                    				
                    			}
                    	   		
                    			//BELOW CODE FOR CLAIMANT
                    			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
                    			{
                    				
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CLAIMANT_PHOTO)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU8DocContentsTrns());

                        			}
                    				
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PAN_FORM_60)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU11DocContentsTrns());

                        			}
                    				
                    	       		
                    	       	}
                    	   		
                    			//BELOW CODE FOR CLAIMANT POA HOLDER
                    			if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
                    			{
                    	   		
                    				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_ATTORNEY_PHOTO)){
                            			DMSUtility.downloadDocument(response, filePath,dtoObj.getU9DocContentsTrns());

                        			}
                    				
                    			
                    			}
                    			
                				forward ="displayRegDetls";
                    			request.setAttribute("deedId", regForm.getDeedID());
                    			request.setAttribute("roleId", partyType);
                			}else{
                				
                				RegCompletDTO dtoObj=(RegCompletDTO)regForm.getMapPropertyTransPartyDisp().get(key);
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_1)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage1DocContents());

                    			}
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_2)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage2DocContents());

                    			}
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_ANGLE_3)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropImage3DocContents());

                    			}
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_MAP)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropMapDocContents());

                    			}
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_RIN)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropRinDocContents());

                    			}
                				
                				if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PROP_KHASRA)){
                        			DMSUtility.downloadDocument(response, filePath,dtoObj.getPropKhasraDocContents());

                    			}
                				
                				forward="propertyView";
                			}
            				
            				
            				
            				
            				
            				
            				
            				
            				
            				
            				/*RegCommonDTO dtoObj=(RegCommonDTO)regForm.getMapTransactingPartiesDisp().get(key);
            					
                			
                			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_CERTIFICATE)){
                    			DMSUtility.downloadDocument(response, filePath,dtoObj.getDocContentsTrns());

                			}
                			if(fileName.equalsIgnoreCase(RegInitConstant.FILE_NAME_PHOTO_PROOF)){
                    			DMSUtility.downloadDocument(response, filePath,dtoObj.getU2DocContentsTrns());

                			}
                			
                			if(request.getParameter("roleType")!=null){
                				partyType=request.getParameter("roleType").toString();
                				regForm.setPartyType(partyType);
                				logger.debug("role type---------->"+partyType);
                				forward ="displayRegDetls";
                    			request.setAttribute("deedId", regForm.getDeedID());
                    			request.setAttribute("roleId", partyType);
                			}else{
                				forward="propertyView";
                			}*/
                			
                			
                			
                			
                			
                		}
                		
                	}
                }
                if(RegInitConstant.PRINT_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
                	regForm=commonBo.printToPdf(regForm, request, response, languageLocale);
            		
                    request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("instId", regForm.getInstID());
        			forward="reginitConfirm";
        			
            	}
                
                if(RegInitConstant.MODIFY_PARTY_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		commonBo.getPartyDetsForViewConfirmModify(regForm);
            		
            		commonDto.setIdProfTrns(commonBo.getIdProf(languageLocale));
					//commonDto.setCategoryList(commonBo.getCategoryList());
					commonDto.setOccupationList(commonBo.getOccupationList(languageLocale));
            		
            		//regForm.setFnameTrns("Roopam");
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		commonDto.setDistrictTrns(commonBo.getDistrict("1",languageLocale));//state id passed is for MP
            		commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
            		commonDto.setRelationshipList(commonBo.getRelationshipList(regForm.getGendarTrns(),languageLocale));
            		commonDto.setAuthPerRelationshipList(commonBo.getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
            		regForm.setPartyModifyFlag(1);
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("roleId", regForm.getPartyType());
        			
        			if(regForm.getCommonDeed()!=1){
        			String[] claimantArr=commonBo.getClaimantFlag(regForm.getPartyType());
             		regForm.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
    				regForm.setPoaHolderFlag(Integer.parseInt(claimantArr[1].trim()));
        			}
        			forward="displayRegDetls";
        			
            	}
            	if(RegInitConstant.SAVE_PARTY_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		regForm.setPartyModifyFlag(0);
            		
            		
            		//below write code for saving modified party details 
            		
            		
            		boolean allUploadsUpdated=false;
            		boolean allDetailsUpdated=false;
            		
            		String filePath;
					String filePathPhotoProof;
					String filePathNotAffExec;
					String filePathExecPhoto;
					String filePathNotAffAttrn;
					String filePathAttrnPhoto;
					String filePathClaimPhoto;
					String filePathPanForm60;
					
					int roleId=0;
					
					if(regForm.getPartyTypeTrns()!=null && !regForm.getPartyTypeTrns().equalsIgnoreCase("") && !regForm.getPartyTypeTrns().equalsIgnoreCase("null")){
					roleId=Integer.parseInt(regForm.getPartyTypeTrns());
					}
					
					
					 String[] claimantArr=commonBo.getClaimantFlag(Integer.toString(roleId));
		         		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
		         		
					if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID) || 
							regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID))
					{
						
						
						if(regForm.getDeedTypeFlag()==0){
							

							filePathPhotoProof=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU2DocContentsTrns(),
									regForm.getPartyTxnId(),regForm.getU2PartyOrPropTrns(),regForm.getU2UploadTypeTrns());
							
	                    	if(filePathPhotoProof!=null){
	                    		regForm.setU2FilePathTrns(filePathPhotoProof);
	                    		//BELOW CODE FOR EXECUTANT
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	                    		{
	                    			
	                    		filePathNotAffExec=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU3DocContentsTrns(),
										regForm.getPartyTxnId(),regForm.getU3PartyOrPropTrns(),regForm.getU3UploadTypeTrns());
								
		                    	if(filePathNotAffExec!=null){
		                    		regForm.setU3FilePathTrns(filePathNotAffExec);
		                    		
		                    		filePathExecPhoto=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU4DocContentsTrns(),
											regForm.getPartyTxnId(),regForm.getU4PartyOrPropTrns(),regForm.getU4UploadTypeTrns());
									
			                    	if(filePathExecPhoto!=null){
			                    		regForm.setU4FilePathTrns(filePathExecPhoto);
			                    		
			                    		if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID))	 //for individual
			                    		{
			                    		if(!regForm.getListIDTrns().equalsIgnoreCase("4")){
			                    			
			                    			filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU10DocContentsTrns(),
													regForm.getPartyTxnId(),regForm.getU10PartyOrPropTrns(),regForm.getU10UploadTypeTrns());
											
					                    	if(filePathPanForm60!=null){
					                    		regForm.setU10FilePathTrns(filePathPanForm60);
					                    		allUploadsUpdated=true;
					                    	}else{
					                    		allUploadsUpdated=false;
					                    	}
			                    			
			                    		}else{
			                    			allUploadsUpdated=true;
			                    		}
			                    		
			                    	}else{              //for organization
			                    		
			                    		filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU10DocContentsTrns(),
												regForm.getPartyTxnId(),regForm.getU10PartyOrPropTrns(),regForm.getU10UploadTypeTrns());
										
				                    	if(filePathPanForm60!=null){
				                    		regForm.setU10FilePathTrns(filePathPanForm60);
				                    		allUploadsUpdated=true;
				                    	}else{
				                    		allUploadsUpdated=false;
				                    	}
			                    		
			                    		
			                    	}
			                    	}else{
			                    		allUploadsUpdated=false;
                    				}
		                    	}
		                    	}
	                    		//BELOW CODE FOR EXECUTANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	                    		{
	                    			
	                    			
	                    filePathExecPhoto=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU7DocContentsTrns(),
											regForm.getPartyTxnId(),regForm.getU7PartyOrPropTrns(),regForm.getU7UploadTypeTrns());
									
			                if(filePathExecPhoto!=null)
			                {
			                    		regForm.setU7FilePathTrns(filePathExecPhoto);
	                    		filePathNotAffAttrn=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU5DocContentsTrns(),
										regForm.getPartyTxnId(),regForm.getU5PartyOrPropTrns(),regForm.getU5UploadTypeTrns());
								
		                    	if(filePathNotAffAttrn!=null)
		                    	{
		                    		regForm.setU5FilePathTrns(filePathNotAffAttrn);
		                    		
		                    		filePathAttrnPhoto=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU6DocContentsTrns(),
											regForm.getPartyTxnId(),regForm.getU6PartyOrPropTrns(),regForm.getU6UploadTypeTrns());
									
			                    	if(filePathAttrnPhoto!=null)
			                    	{
			                    		regForm.setU6FilePathTrns(filePathAttrnPhoto);
			                    		
			                    		allUploadsUpdated=true;
			                    	}else{
			                    		allUploadsUpdated=false;
                    				}
		                    	}
		                    	
			                }
		                    	
		                    	
		                    	}
	                    		//BELOW CODE FOR CLAIMANT
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
	                    		{
	                    			
	                    			filePathClaimPhoto=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU8DocContentsTrns(),
										regForm.getPartyTxnId(),regForm.getU8PartyOrPropTrns(),regForm.getU8UploadTypeTrns());
								
		                    	if(filePathClaimPhoto!=null){
		                    		regForm.setU8FilePathTrns(filePathClaimPhoto);
		                    		
		                    		if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID))	 //for individual
		                    		{
		                    		if(!regForm.getListID().equalsIgnoreCase("4")){
		                    			
		                    			filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU11DocContentsTrns(),
												regForm.getPartyTxnId(),regForm.getU11PartyOrPropTrns(),regForm.getU11UploadTypeTrns());
										
				                    	if(filePathPanForm60!=null){
				                    		regForm.setU11FilePathTrns(filePathPanForm60);
				                    		allUploadsUpdated=true;
				                    	}else{
				                    		allUploadsUpdated=false;
				                    	}
		                    			
		                    		}else{
		                    			allUploadsUpdated=true;
		                    		}
		                    		
		                    	}else{                                         //for organization
		                    		filePathPanForm60=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU11DocContentsTrns(),
											regForm.getPartyTxnId(),regForm.getU11PartyOrPropTrns(),regForm.getU11UploadTypeTrns());
									
			                    	if(filePathPanForm60!=null){
			                    		regForm.setU11FilePathTrns(filePathPanForm60);
			                    		allUploadsUpdated=true;
			                    	}else{
			                    		allUploadsUpdated=false;
			                    	}
		                    	}
			                    	
		                    	}else{
		                    		allUploadsUpdated=false;
                				}
		                    	}
	                    		
	                    		//BELOW CODE FOR CLAIMANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
	                    		{
	                    			
	                    			filePathAttrnPhoto=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU9DocContentsTrns(),
										regForm.getPartyTxnId(),regForm.getU9PartyOrPropTrns(),regForm.getU9UploadTypeTrns());
								
		                    	if(filePathAttrnPhoto!=null){
		                    		regForm.setU9FilePathTrns(filePathAttrnPhoto);
		                    		allUploadsUpdated=true;
			                    	
		                    	}else{
		                    		allUploadsUpdated=false;
                				}
		                    	}
	                    		
	                    		if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){	
	                    		if(regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	                    				filePath=  action.uploadFile(regForm.getHidnRegTxnId(),regForm.getDocContentsTrns(),
	                    							regForm.getPartyTxnId(),regForm.getPartyOrPropTrns(),regForm.getUploadTypeTrns());
	                    				if(filePath!=null){
	                    					regForm.setFilePathTrns(filePath);
	                    					allUploadsUpdated=true;
	                    				}else{
	                    					allUploadsUpdated=false;
	                    				}
	                    		}
	                    	}
	                    	}
						
						
						
						
						
							
						
						}
					else
						{
						filePathPhotoProof=action.uploadFile(regForm.getHidnRegTxnId(),regForm.getU2DocContentsTrns(),
								regForm.getPartyTxnId(),regForm.getU2PartyOrPropTrns(),regForm.getU2UploadTypeTrns());
						
                    	if(filePathPhotoProof!=null){
                    		regForm.setU2FilePathTrns(filePathPhotoProof);
                    		
                    		if(regForm.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){	            		
                    		if(regForm.getIndCategoryTrns().equalsIgnoreCase("1") && regForm.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                    				filePath=  action.uploadFile(regForm.getHidnRegTxnId(),regForm.getDocContentsTrns(),
                    							regForm.getPartyTxnId(),regForm.getPartyOrPropTrns(),regForm.getUploadTypeTrns());
                    				if(filePath!=null){
                    					regForm.setFilePathTrns(filePath);
                    					allUploadsUpdated=true;
                    				}else{
                    					allUploadsUpdated=false;
                    				}
                    		}else{
            					allUploadsUpdated=true;
            				}
                    		
                    		
                    	}else{
        					allUploadsUpdated=true;
        				}
                    		
                    		
                    	}else{
        					allUploadsUpdated=false;
        				}
					
					
					}
					
					}else{
						allUploadsUpdated=true;
					}
					
					
					if(allUploadsUpdated){
					
						allDetailsUpdated = commonBo.updateTransPartyDetails(regForm);
						logger.debug("transacting party details updation status---->"+allDetailsUpdated);
					}
            		
            		
            		
            		
            		
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("roleId", regForm.getPartyType());
        			request.setAttribute("instId", regForm.getInstID());
        			forward="reginitConfirm";
        			commonBo.landConfirmationScreen(regForm,languageLocale);
        			
            	}
            	if(RegInitConstant.SUBMIT_FORM_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		logger.debug("category value----------->"+regForm.getIndCategoryTrns());
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		commonDto.setDistrictTrns(commonBo.getDistrict("1",languageLocale));//state id passed is for MP
            		commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
            		regForm.setPartyModifyFlag(1);
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("roleId", regForm.getPartyType());
        			forward=regForm.getJspName();
        			
            	}
            	
            	if(RegInitConstant.MODIFY_PROPERTY_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		
            		request.setAttribute("fromAdju", regForm.getFromAdjudication());
            		//commonBo.getPartyDetsForViewConfirmModify(regForm);
            		
            		//commonDto.setIdProfTrns(commonBo.getIdProf());
					//commonDto.setCategoryList(commonBo.getCategoryList());
					//commonDto.setOccupationList(commonBo.getOccupationList());
            		
            		//regForm.setFnameTrns("Roopam");
            		//regForm.setActionName(RegInitConstant.NO_ACTION);
            		//commonDto.setDistrictTrns(commonBo.getDistrict("1"));//state id passed is for MP
            		//commonDto.setCategoryList(commonBo.getCategoryList());
            		regForm.setPropertyModifyFlag(1);
                	//request.setAttribute("deedId", regForm.getDeedID());
        			//request.setAttribute("roleId", regForm.getPartyType());
            		//request.setAttribute("propMap", regForm.getMapPropertyTransPartyDisp());
        			forward="modifyPropDetls";
        			request.setAttribute("regInitForm", regForm);
        			
            	}
            	if(RegInitConstant.MODIFY_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		commonDto.setIndcountry(commonBo.getCountry(languageLocale));
    				commonDto.setIdProf(commonBo.getIdProf(languageLocale));
    				commonDto.setDeedType(commonBo.getDeedType(languageLocale));
    				commonDto.setCategoryList(commonBo.getCategoryList(languageLocale));
    				commonDto.setIndstate(commonBo.getState("1",languageLocale));
    				commonDto.setInddistrict(commonBo.getDistrict("1",languageLocale));
    				
    				if(regForm.getMnameArb().equalsIgnoreCase("-")){
    					regForm.setMnameArb("");
    				}
    				if(regForm.getSpouseNameArb().equalsIgnoreCase("-")){
    					regForm.setSpouseNameArb("");
    				}
    				if(regForm.getMotherNameArb().equalsIgnoreCase("-")){
    					regForm.setMotherNameArb("");
    				}
    				if(regForm.getIndphnoArb().equalsIgnoreCase("-")){
    					regForm.setIndphnoArb("");
    				}
    				if(regForm.getEmailIDArb().equalsIgnoreCase("-")){
    					regForm.setEmailIDArb("");
    				}
    				if(regForm.getContriProp().equalsIgnoreCase("-")){
    					regForm.setContriProp("");
    				}
            		
            		
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		
            		regForm.setExtraDetlsModifyFlag(1);
                	request.setAttribute("deedId", regForm.getDeedID());
                	regForm.setCallingAction("nonPropRegInit.do");
        			forward="displayExtraDetls";
        			
            	}
            	if(RegInitConstant.SAVE_EXTRA_DETLS_ACTION.equalsIgnoreCase(actionName))
                {	
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		regForm.setExtraDetlsModifyFlag(0);
            		//below write code for saving modified bank details 
               		boolean allDetailsUpdated=false;
            		
            		allDetailsUpdated = commonBo.updateBankDetails(regForm);
						logger.debug("bank details updation status---->"+allDetailsUpdated);
			           		
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("instId", regForm.getInstID());
        			//regForm.setCallingAction("regInit.do");
        			forward="reginitConfirm";
        			
            	}
            	if(RegInitConstant.EXTRA_DETLS_NO_ACTION.equalsIgnoreCase(actionName))
                {	
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		regForm.setCallingAction("nonPropRegInit.do");
            		//regForm.setExtraDetlsModifyFlag(0);
            		//below write code for saving modified extra details 
               		//boolean allDetailsUpdated=false;
            		
            		//allDetailsUpdated = commonBo.updateBankDetails(regForm);
					//	logger.debug("extra details updation status---->"+allDetailsUpdated);
			           		
                	//request.setAttribute("deedId", regForm.getDeedID());
        			//request.setAttribute("instId", regForm.getInstID());
        			//regForm.setCallingAction("regInit.do");
        			forward="displayExtraDetls";
        			
            	}
            	if(RegInitConstant.MODIFY_PARTICULAR_DETLS_ACTION.equalsIgnoreCase(actionName))
                	
            	{	
            		
    				if(regForm.getParticularName().equalsIgnoreCase("-")){
    					regForm.setParticularName("");
    				}
    				if(regForm.getParticularDesc().equalsIgnoreCase("-")){
    					regForm.setParticularDesc("");
    				}
    				
            		
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		
            		regForm.setExtraDetlsModifyFlag(1);
                	request.setAttribute("deedId", regForm.getDeedID());
                	forward="displayParticularDetls";
        			
            	}
            	
            	if(RegInitConstant.SAVE_PARTICULAR_DETLS_ACTION.equalsIgnoreCase(actionName))
                {	
            		regForm.setActionName(RegInitConstant.NO_ACTION);
            		regForm.setExtraDetlsModifyFlag(0);
            		//below write code for saving modified bank details 
               		boolean allDetailsUpdated=false;
            		
            		allDetailsUpdated = commonBo.updateParticularDetails(regForm);
						logger.debug("particular details updation status---->"+allDetailsUpdated);
			           		
						
						ArrayList particularIdList=commonBo.getParticularList(regForm.getHidnRegTxnId());  
                		regForm.setPropListNonPropDeed(particularIdList);
                		
                		
                	request.setAttribute("deedId", regForm.getDeedID());
        			request.setAttribute("instId", regForm.getInstID());
        			//regForm.setCallingAction("regInit.do");
        			forward="reginitConfirm";
        			
            	}
            	
            	//added by Shreeraj
				if(RegInitConstant.SUBMIT_ADJU_NON_PROP_ACTION.equals(actionName)){	
					//RegCommonDTO commonDto =new RegCommonDTO();
					//commonDto.ge
					//regForm.getStampDuty()
					String currDate=commonBo.getCurrDateTime();
                	
                	System.out.println("curr date 2 : "+currDate);
                	
                	regForm.setCurrDateTime(currDate);
					//if(regForm.getStampManually().equalsIgnoreCase("Y")){
						boolean insertStatus=commonBo.insertAdjuDuties(regForm);
						
						logger.debug("adjudication duties insertion status---> "+insertStatus);
					/*}
					else
					{
						boolean insertStatus=commonBo.insertAdjuDutiesSys(regForm);
					}*/
					
					
					forward="success1";
				}
				
				if(RegInitConstant.ADJU_COMPLETED.equals(actionName)){				
					forward="welcome";
				}
				if(RegInitConstant.POSTAL_COUNTRY_CHANGE.equals(actionName)){		
					
					commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(),languageLocale));
        			regForm.setPostalState("");
        			regForm.setPostalDistrict("");
					
					forward="success1";
				}
				if(RegInitConstant.POSTAL_STATE_CHANGE.equals(actionName)){			
					
					commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(),languageLocale));
					commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(),languageLocale));
					
					forward="success1";
				}
				//END
            	
            	//FROM DASHBOARD
            	 if(request.getParameter("regStatus")!=null){
                 	if(request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_APP_SAVED)){
                 		forward="transactingParty";
                 	}
                 	if(request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED)){
                 		request.setAttribute("fromAdju", regForm.getFromAdjudication());
                 		forward="reginitProperty";
                 	}
                 	if(request.getParameter("regStatus").equalsIgnoreCase(RegInitConstant.STATUS_FINAL_SCREEN)){
                 		commonDto.setIndcountry(commonBo.getCountry(languageLocale));
            			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(),languageLocale));
            			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(),languageLocale));
                 		forward="success1";
                 	}
            	 }
            	 
            	 //postal address
            	 if(RegInitConstant.POSTAL_CHECK_ACTION.equalsIgnoreCase(actionName))
                 	
             	{	
                 	
         			forward="success1";
         			
             	}
             	if(RegInitConstant.COPY_APPLICANT_ADDRESS_ACTION.equalsIgnoreCase(actionName))
                 	
             	{
             		commonBo.copyPostalAddress(regForm);
             		commonDto.setIndcountry(commonBo.getCountry(languageLocale));
        			commonDto.setIndstate(commonBo.getState(regForm.getPostalCountry(),languageLocale));
        			commonDto.setInddistrict(commonBo.getDistrict(regForm.getPostalState(),languageLocale));
             		
        			
                 	
         			forward="success1";
         			
             	}
             	if(RegInitConstant.CHANGE_CATEGORY_ACTION.equalsIgnoreCase(actionName))
                 	
             	{
             		
         			forward="transactingParty";
         			
             	}
             	
            	
            	if(request.getParameter("label")==null){
                	if(regForm.getDeedID()!=0){
                		request.setAttribute("deedId", regForm.getDeedID());
                	}else{
                		request.setAttribute("deedId", 0);
                	}
                	
                	if(regForm.getPartyType()!=null){
                		request.setAttribute("roleId", regForm.getPartyType());
                	}else{
                		request.setAttribute("roleId", 0);
                	}
                	if(regForm.getPartyTypeTrns()!=null){
                		request.setAttribute("roleIdTrns", regForm.getPartyTypeTrns());
                	}else{
                		request.setAttribute("roleIdTrns", 0);
                	}
                	}

			//session.setAttribute("commonDto", commonDto);
			regForm.setCommonDto(commonDto);
			
			//session.setAttribute("regForm", regForm);
			//request.setAttribute("commonDto", commonDto);
			request.setAttribute("reginit", regForm);
			
			//logger.debug("total poa count-------->"+regForm.getTotalPoaCount());
			
			
			logger.debug("-------------------->"+session.getAttribute("parentModName"));
			logger.debug("-------------------->"+session.getAttribute("parentFunName"));
			logger.debug("-------------------->"+session.getAttribute("parentFunId"));
			logger.debug("-------------------->"+session.getAttribute("parentAmount"));
			
			
 			logger.debug("the forward path from the common action in reg init is ==> " + forward);
 			return mapping.findForward(forward);		
	}
	
	public static void cancelAction(HttpSession session, RegCommonForm regForm)
	{
		//RegCommonForm regForm=(RegCommonForm)session.getAttribute("regForm");
		
		if(regForm!=null)
		{
					    
		 //   RegCommonDTO commonDto = regForm.getCommonDto();
		//    commonDto.setInstrument(new ArrayList());
		//	commonDto.setExemption(new ArrayList());
			
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
			regForm.setShareOfProp("");
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
			 
			//    commonDto.setInstrument(new ArrayList());
			//	commonDto.setExemption(new ArrayList());
				
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
		//
		//private String selectedPoa;
		//private String selectedPoaName;
		regForm.setCurrDateTime("");
		regForm.setDeedID(0);
		
		//Start:==== Added by ankita
		regForm.setDeedtype1("");
		regForm.setInstType("");
		/*private double stampduty1;
		private double nagarPalikaDuty;
		private double panchayatDuty;
		private double upkarDuty;
		private double totalduty ;
		private double registrationFee;*/
		regForm.setStampduty1("");
		regForm.setNagarPalikaDuty("");
		regForm.setPanchayatDuty("");
		regForm.setUpkarDuty("");
		regForm.setTotalduty("") ;
		regForm.setRegistrationFee("");
		regForm.setSelectedExemptionList( new ArrayList());
		regForm.setTotalMarketValue("");
		regForm.setExemType("");
		regForm.setFromModule("");
		regForm.setCheckModule("");
		regForm.setCheckRegNo("");    //TO BE SET AS BLANK
		
		//End :====== Added by ankita
		
		//following added roopam after first demo.
		
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
		regForm.setMapPropertyTransParty ( new HashMap());
		regForm.setMapPropertyTransPartyDisp ( new HashMap());
		//private float totalMarketValue=0;
		/*private String totalMarketValue;
		private int applicantRoleId;
		private int totalShareOfProp;
		private int applicantRoleId2;
		private int mapKeyCount=0;
		private HashMap mapTransPartyDbInsertion = new HashMap();
		
		private int totalShareOfPropBuyer;
		private int totalShareOfPropSellerSelf;
		private int totalShareOfPropSellerPoa;*/
		//private float marketValue;
		regForm.setApplicantRoleId(0);
		regForm.setApplicantRoleId2(0);
		
		//regForm.setTotalShareOfProp(0);
		regForm.setTotalShareOfProp(0);
		regForm.setTotalShareOfPropBuyer(0);
		regForm.setTotalShareOfPropSellerSelf(0);
		regForm.setTotalShareOfPropDonor(0);
		regForm.setTotalShareOfPropDonee(0);
		regForm.setTotalShareOfPropOwnerSelf(0);
		
		
		//following fields for owner of poa
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
		
		/*private String ownerNameTrns;
		private String ownerOgrNameTrns;
		private String ownerGendarTrns="M";
		private String ownerNationalityTrns;
		private String ownerAddressTrns;
		private String ownerPhnoTrns;
		private String ownerEmailIDTrns;
		private String ownerListIDTrns;
		private String ownerIdnoTrns;
		private String ownerAgeTrns;
		private String ownerProofNameTrns;
		*/
		//private String poaOwnerId;
		//private String hdnExAmount;
		regForm.setPaymentCompleteFlag(0);
		regForm.setIsDutyCalculated(0);
		regForm.setMarketValueShares("");
		//private String dutyPaid;
		
		regForm.setLabelAmountFlag("");
		//private double regPaid=0.0;
		
		regForm.setMapPropertyTransParty(new HashMap());
		
		regForm.setAdjudicationNumber("");
		regForm.setErrorMsg("");
		
		
		
		}
		//session.removeAttribute("OrganisationList");
		//session.removeAttribute("TpartiesIndividualList");
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
	
	/*public static String getRoleNameId(RegCommonForm regForm, String action, String partyType)
	{
		
		String partyRoleTypeId=null;
		String roleName=null;
		int count;
		if(action.equalsIgnoreCase(RegInitConstant.NEXT_ACTION)){
		if(regForm.getPartyType().equalsIgnoreCase("50001"))
		{
			roleName=RegInitConstant.PARTY_BUYER;
			
			count=regForm.getBuyerCount();
			partyRoleTypeId="50001"+count;
			
		}
		if(regForm.getPartyType().equalsIgnoreCase("50002"))
		{
			roleName=RegInitConstant.PARTY_SELLER_SELF;
			
			count=regForm.getSellerSelfCount();
			partyRoleTypeId="50002"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50003"))
		{
			roleName=RegInitConstant.PARTY_SELLER_POA;
			
			count=regForm.getSellerPoaCount();
			partyRoleTypeId="50003"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50004"))
		{
			roleName=RegInitConstant.PARTY_OWNER;
			count=regForm.getOwnerCount();
			partyRoleTypeId="50004"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50005"))
		{
			roleName=RegInitConstant.PARTY_DONOR;
			count=regForm.getDonorCount();
			partyRoleTypeId="50005"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50006"))
		{
			roleName=RegInitConstant.PARTY_DONEE;
			count=regForm.getDoneeCount();
			partyRoleTypeId="50006"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50007"))
		{
			roleName=RegInitConstant.PARTY_POA_HOLDER;
			count=regForm.getPoaHolderCount();
			partyRoleTypeId="50007"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50008"))
		{
			roleName=RegInitConstant.PARTY_POA_ACCEPTER;
			count=regForm.getPoaAccepterCount();
			partyRoleTypeId="50008"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50009"))
		{
			roleName=RegInitConstant.PARTY1_OWNER;
			count=regForm.getParty1OwnerCount();
			partyRoleTypeId="50009"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50010"))
		{
			roleName=RegInitConstant.PARTY2_OWNER;
			count=regForm.getParty2OwnerCount();
			partyRoleTypeId="50010"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50011"))
		{
			roleName=RegInitConstant.PARTY1_POA_HOLDER;
			count=regForm.getParty1PoaHolderCount();
			partyRoleTypeId="50011"+count;
		}
		if(regForm.getPartyType().equalsIgnoreCase("50012"))
		{
			roleName=RegInitConstant.PARTY2_POA_HOLDER;
			count=regForm.getParty2PoaHolderCount();
			partyRoleTypeId="50012"+count;
		}
		}
		if(action.equalsIgnoreCase(RegInitConstant.ADD_MORE_ACTION) || 
		   action.equalsIgnoreCase(RegInitConstant.NEXT_TO_MAPPING_ACTION) ||
		   action.equalsIgnoreCase(RegInitConstant.NEXT_TO_PROPERTY_ACTION)){
			if(partyType.equalsIgnoreCase("50001"))
			{
				roleName=RegInitConstant.PARTY_BUYER;
				
				count=regForm.getBuyerCount();
				partyRoleTypeId="50001"+count;
			}
			if(partyType.equalsIgnoreCase("50002"))
			{
				roleName=RegInitConstant.PARTY_SELLER_SELF;
				
				count=regForm.getSellerSelfCount();
				partyRoleTypeId="50002"+count;
			}
			if(partyType.equalsIgnoreCase("50003"))
			{
				roleName=RegInitConstant.PARTY_SELLER_POA;
				
				count=regForm.getSellerPoaCount();
				partyRoleTypeId="50003"+count;
			}
			if(partyType.equalsIgnoreCase("50004"))
			{
				roleName=RegInitConstant.PARTY_OWNER;
				count=regForm.getOwnerCount();
				partyRoleTypeId="50004"+count;
			}
			if(partyType.equalsIgnoreCase("50005"))
			{
				roleName=RegInitConstant.PARTY_DONOR;
				count=regForm.getDonorCount();
				partyRoleTypeId="50005"+count;
			}
			if(partyType.equalsIgnoreCase("50006"))
			{
				roleName=RegInitConstant.PARTY_DONEE;
				count=regForm.getDoneeCount();
				partyRoleTypeId="50006"+count;
			}
			if(partyType.equalsIgnoreCase("50007"))
			{
				roleName=RegInitConstant.PARTY_POA_HOLDER;
				count=regForm.getPoaHolderCount();
				partyRoleTypeId="50007"+count;
			}
			if(partyType.equalsIgnoreCase("50008"))
			{
				roleName=RegInitConstant.PARTY_POA_ACCEPTER;
				count=regForm.getPoaAccepterCount();
				partyRoleTypeId="50008"+count;
			}
			if(partyType.equalsIgnoreCase("50009"))
			{
				roleName=RegInitConstant.PARTY1_OWNER;
				count=regForm.getParty1OwnerCount();
				partyRoleTypeId="50009"+count;
			}
			if(partyType.equalsIgnoreCase("50010"))
			{
				roleName=RegInitConstant.PARTY2_OWNER;
				count=regForm.getParty2OwnerCount();
				partyRoleTypeId="50010"+count;
			}
			if(partyType.equalsIgnoreCase("50011"))
			{
				roleName=RegInitConstant.PARTY1_POA_HOLDER;
				count=regForm.getParty1PoaHolderCount();
				partyRoleTypeId="50011"+count;
			}
			if(partyType.equalsIgnoreCase("50012"))
			{
				roleName=RegInitConstant.PARTY2_POA_HOLDER;
				count=regForm.getParty2PoaHolderCount();
				partyRoleTypeId="50012"+count;
			}
			}
		
		regForm.setPartyRoleTypeId(partyRoleTypeId);
		return roleName;
	}*/
	
	
}
