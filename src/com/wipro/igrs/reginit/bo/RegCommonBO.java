package com.wipro.igrs.reginit.bo;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.wipro.igrs.DMSConnection.DMSUtility;
import com.wipro.igrs.common.CustomArrayList;
import com.wipro.igrs.common.IGRSCommon;
//import com.wipro.igrs.common.customArrayList;
//import com.wipro.igrs.noEncumbrance.dto.KhasraDTO;
import com.wipro.igrs.propertyvaluation.constant.CommonConstant;
//import com.wipro.igrs.common.CommonConstant;
import com.wipro.igrs.propertyvaluation.dto.ExemptionDTO;
import com.wipro.igrs.reginit.bd.RegCommonBD;
import com.wipro.igrs.reginit.constant.RegInitConstant;
import com.wipro.igrs.reginit.dto.CommonDTO;
import com.wipro.igrs.reginit.dto.PropertyValuationDTO;
import com.wipro.igrs.reginit.dto.RegCommonDTO;
import com.wipro.igrs.reginit.dto.RegCompletDTO;
import com.wipro.igrs.reginit.form.RegCommonForm;
import com.wipro.igrs.reginit.form.RegCompletionForm;
import com.wipro.igrs.reginit.sql.RegCommonSQL;


public class RegCommonBO{

	RegCommonBD commonBd = null;
	public RegCommonBO() {
		commonBd = new RegCommonBD();
	}
	
	/**
	 * for getting all Applicant Types
	 * @return ArrayList
	 */
	public ArrayList getAppType(String languageLocale) {
		return commonBd.getAppType(languageLocale);
	}
	
	/**
	 * for getting all Country details
	 * @return ArrayList
	 */
	public ArrayList getCountry(String languageLocale) {
		return commonBd.getCountry(languageLocale);
	}
	
	/**
	 * for getting all State details
	 * @param param 
	 * @return ArrayList
	 */
	public ArrayList getState(String param,String languageLocale) {
		return commonBd.getState(param,languageLocale);
	}
	
	/**
	 * for getting all District details
	 * @param param1 
	 * @return ArrayList
	 */
	public ArrayList getDistrict(String stateId,String languageLocale) {
		return commonBd.getDistrict(stateId,languageLocale);
	}
	
	/**
	 * for getting all ID Proof details
	 * @return ArrayList
	 */
	public ArrayList getIdProf(String languageLocale) {
		return commonBd.getIdProf(languageLocale);
	}
	
	/**
	 * for getting All Deed Details
	 * @return ArrayList
	 */
	public ArrayList getDeedType(String languageLocale) {
		return commonBd.getDeedType(languageLocale);
	}
	
	//following method created by roopam
	public ArrayList getDeedTypeNew(String flag) {
		return commonBd.getDeedTypeNew(flag);
	}
	
	/**
	 * for getting Instruments list based on deed
	 * @param deed
	 * @return ArrayList
	 */
	public ArrayList getInstrument(String deed) {
		return commonBd.getInstrument(deed);
	}
	
	/**
	 * form setting Instrument/Exemptions Details based on deed/instrument
	 * @param deed
	 * @param deed1
	 * @return ArrayList
	 */
	public ArrayList getExemption(String deed,String deed1) {
		return commonBd.getExemption(deed,deed1);
	}
	
	/**
	 * form setting selected Exemptions Details(in confirmation page)
	 * @param exemptions
	 * @return ArrayList
	 */
	public ArrayList setExemptionList(String exemptions){
		return commonBd.setExemptionList(exemptions);
	}
	
	/**
	 * form setting selected Exemptions Details(in form)
	 * @param exemption
	 * @param list
	 * @return ArrayList
	 */
	public ArrayList getExemptions(ArrayList exemption,ArrayList list){
		return commonBd.getExemptions(exemption,list);
	}
	
	/**
	 * for setting Transaction Party details
	 * @param tparties
	 * @return ArrayList
	 */
	public ArrayList setTpartiesList(String tparties){
		return commonBd.setTpartiesList(tparties);
	}
	
	/**
	 * for setting organization details  
	 * @param organisation
	 * @return ArrayList
	 */
	public ArrayList setOrganisationList(String organisation){
		return commonBd.setOrganisationList(organisation);
	}
	
	/**
	 * for calculating Stamp Duty
	 * @param deed
	 * @param instrument
	 * @param exemption
	 * @param marketValue 
	 * @return stampDuty
	 */
	/*public String getStampDuty(String deed,String instrument,String exemption, String marketValue){
		return commonBd.getStampDuty(deed,instrument,exemption,marketValue);
	}*/
	
	/**
	 * for calculating Other Fee for reg init
	 * @param function_id
	 * @param user_id 
	 * @param service_id 
	 * @return otherFee
	 */
	public ArrayList getOthersDuty(String function_id, String service_id, String user_id){
		return commonBd.getOthersDuty(function_id,service_id,user_id);
	}
	
	public PropertyValuationDTO getPropertyValuationDTO(
						PropertyValuationDTO dto)throws Exception {
		return commonBd.getPropertyValuationDTO(dto);
	}
	/**@auther : ROOPAM MEHTA
	   @Created : 16 Nov.2012
	   @Description : checks weather the given deed requires property valuation or duty calculation
	   @Paratmeter : String
	*/
	public boolean propertyValReqCheck(String deed) {
		return commonBd.propertyValReqCheck(deed);
	}
	
	/**
	 * for getting party type list based on deed
	 * @param deed
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPartyType(int deed,int inst,String languageLocale) {
		return commonBd.getPartyType(deed,inst,languageLocale);
	}
	
	/**
	 * insertApplicantAndPropertyDetails
	 * Returns boolean value in order to check the insertion.
	 * @param form
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insertApplicantAndPropertyDetails(RegCommonForm form) throws Exception {
		return commonBd.insertApplicantAndPropertyDetails(form);
	}
	/**
	 * getRegTxnIdSeq
	 * Returns String value of daily sequence
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public String getRegTxnIdSeq() throws Exception {
		return commonBd.getRegTxnIdSeq();
	}
	/**
	 * getNewRegTxnIdSeq
	 * Returns String value of daily sequence
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public String getNewRegTxnIdSeq() throws Exception {
		return commonBd.getNewRegTxnIdSeq();
	}
	/**
	 * insertTransPartyDetails
	 * Returns boolean value in order to check the insertion.
	 * @param dto
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insertTransPartyDetails(RegCommonDTO dto, RegCommonForm regForm,ArrayList list,int multiPropFlag) throws Exception {
		return commonBd.insertTransPartyDetails(dto,regForm,list,multiPropFlag);
	}
	/**
	 * insertTxnDetails
	 * Returns boolean value in order to check the insertion.
	 * @param String[]
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insertTxnDetails(String[] param) throws Exception {
		return commonBd.insertTxnDetails(param);
	}
	/**
	 * form setting selected Owners Details(in confirmation page)
	 * @param String
	 * @author ROOPAM
	 * @return ArrayList
	 */
	public ArrayList setOwnerList(String ownerList){
		return commonBd.setOwnerList(ownerList);
	}
	/**
	 * insertMappingDetails
	 * Returns boolean value in order to check the insertion.
	 * @param 
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insertMappingDetails(String txnId, String poaId, String[] ownerId, String userId) throws Exception {
		return commonBd.insertMappingDetails(txnId,poaId,ownerId,userId);
	}
	/**
	 * getRegInitTxnIdSeq
	 * Returns String value of daily sequence
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public String getRegInitTxnIdSeq() throws Exception {
		return commonBd.getRegInitTxnIdSeq();
	}
	/**
	 * getCurrDateTime
	 * for getting current system date/time from db.
	 * @param 
	 * @return String
	 * @author ROOPAM 
	 *
	 */
	public String getCurrDateTime() throws Exception {
		
		String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("MM/dd/yyyy KK:mm:ss");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        Date d1 = date1.parse(sysdate);
        String formatDate = date2.format(d1);

	//	System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
	}
	/**
	 * getApplicantRegistrationDetls
	 * for getting applicant details registration initiation from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getApplicantRegistrationDetls(String userId) throws
	Exception   //main
	{
		
		return commonBd.getApplicantRegistrationDetls(userId);
	}
	/**
	 * getPendingRegApps
	 * for getting pending applications of registration initiation from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPendingRegApps(String userId,int fromAdju)
	{
		
		
		ArrayList pendingAppListFinal = new ArrayList();
		ArrayList list=commonBd.getPendingRegApps(userId,fromAdju);
		
		if(list!=null && list.size()>0){
			
			ArrayList rowList;
            RegCommonDTO regDto;
            try{
			for (int i = 0; i < list.size(); i++) {
				rowList = (ArrayList)list.get(i);
				regDto = new RegCommonDTO();
			
				if(rowList.get(0).toString().length()==11){
					regDto.setApplicationId("0"+rowList.get(0));
					regDto.setApplicationIdDisp(RegInitConstant.TXN_ID_PREFIX+"0"+rowList.get(0));
				}else{
				regDto.setApplicationId(rowList.get(0));
				regDto.setApplicationIdDisp(RegInitConstant.TXN_ID_PREFIX+rowList.get(0));
				}
				
				regDto.setCreatedDate(convertDOB2(rowList.get(1)));
				regDto.setDeedName1(rowList.get(2));
				
				
				if(rowList.get(4).equals("10")){
					regDto.setAppStatus("Complete");
				}else{
				regDto.setAppStatus("Pending");
				}
				
				
				pendingAppListFinal.add(regDto);
		
				
				
				
				
			}
			
            }
            catch(Exception e){
            	e.printStackTrace();
            	
            }
		}
		return pendingAppListFinal;
	}
	/**
	 * getSavedRegInitApplication
	 * for getting pending applications of registration initiation from db.
	 * @param String
	 * @return RegCommonForm
	 * @author ROOPAM
	 */
	public RegCommonForm getSavedRegInitApplication(String appId)
	{
		return commonBd.getSavedRegInitApplication(appId);
	}
	/**
	 * insertPropertyDetails
	 * for inserting registration initiation other property details in db.
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPropertyDetails(RegCommonForm regInitForm, RegCompletionForm regCompForm)throws
	Exception  
	{
		return commonBd.insertPropertyDetails(regInitForm, regCompForm);
	}
	
	/**
	 * insertPropertyDetails
	 * for inserting registration initiation other property details in db.
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public String getPropertyIdSequence()throws
	Exception  
	{
		String propId=null;
		
		String currDate=commonBd.getCurrDateTime();
		DateFormat formatter= new SimpleDateFormat("mm/dd/yyyy hh:mm:ss"); 
		//formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Date date = (Date)formatter.parse(currDate);
		SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
		String currentYear = formatYear.format(date);
		//System.out.println("year----------**********************>"+currentYear);
		
		String seq=commonBd.getPropIdSeq();
		
		propId=currentYear+seq;
		
		return propId;
	}
	/**
	 * for deleting partial saved applications from db.
	 * @param String
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean deleteSelectedAppDetails(String appId) throws
	Exception   //main
	{
		
		return commonBd.deleteSelectedAppDetails(appId);
	}
	/**
	 * getRegInitPendingAppStatus
	 * for getting pending reg init application details from db
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	/*public String[] getRegInitPendingAppStatus(String appId) throws Exception {
		
		return commonBd.getRegInitPendingAppStatus(appId);
	}*/
	/**
	 * insertApplicantDetsInMap
	 * for setting applicant details from database into Hashmap
	 * @param HashMap, String , String ,RegCommonForm 
	 * @return HashMap
	 * @author ROOPAM
	 */
	public HashMap insertApplicantDetsInMap(HashMap mapTransParties, String key, 
			                                String appId,RegCommonForm form,String languageLocale) throws Exception
	{
		
		
		
		RegCommonForm regForm=commonBd.getSavedRegInitApplication(appId);
		
		
             
             RegCommonDTO mapDto =new RegCommonDTO();
             mapDto.setListAdoptedPartyTrns(regForm.getListAdoptedParty());
            
             mapDto.setPurposeTrns(regForm.getPurpose());
            
             mapDto.setApplicantOrTransParty("Applicant");
             mapDto.setPartyTypeFlag("A");
             mapDto.setPartyRoleTypeId(regForm.getPartyRoleTypeId());
             mapDto.setUserID(regForm.getHidnUserId());
            
            	 if(regForm.getRelationWithOwner().equals("") || regForm.getRelationWithOwner().equals("null"))
            		mapDto.setRelationWithOwnerTrns("-");
            	 else
         	    mapDto.setRelationWithOwnerTrns(regForm.getRelationWithOwner());
            	 if(regForm.getShareOfProp().equals("") || regForm.getShareOfProp().equals("null")){
            		mapDto.setShareOfPropTrns("-");
            		mapDto.setHdnDeclareShareCheck("N");
            		form.setHdnDeclareShareCheck("N");
            	 }else if(regForm.getPartyType().equalsIgnoreCase(Integer.toString(RegInitConstant.ROLE_POA_ACCEPTER)) 
            			 && regForm.getShareOfProp().equalsIgnoreCase("0")){
            		 	mapDto.setHdnDeclareShareCheck("Y");
                	    form.setHdnDeclareShareCheck("Y");
            	 }
            	 else{
         	    mapDto.setShareOfPropTrns(regForm.getShareOfProp());
         	   mapDto.setHdnDeclareShareCheck("Y");
         	  form.setHdnDeclareShareCheck("Y");
            	 }
            	 
            	 if(regForm.getMobno().equals("") || regForm.getMobno().equals("null"))
            		 mapDto.setMobnoTrns("-"); 
            	 else
            	     mapDto.setMobnoTrns(regForm.getMobno());
            	 if(regForm.getPhno().equals("") || regForm.getPhno().equals("null"))
            		 mapDto.setPhnoTrns("-");
            	 else
            	     mapDto.setPhnoTrns(regForm.getPhno());
            	
            	 if(form.getCommonDeed()!=1){
            	     mapDto.setRoleName(getRoleName(regForm.getPartyType(),languageLocale,form.getDeedID(),form.getInstID()));
            	 }else{
            		 mapDto.setRoleName(regForm.getCommonDeedRoleName());
            	 }
            	 
            	 mapDto.setPartyTypeTrns(regForm.getPartyType());
            	 
            	
            	 
             if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
            	 //organization
            	 
            	 mapDto.setListAdoptedPartyNameTrns(getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
            	 mapDto.setOgrNameTrns(regForm.getOgrName());
            	 mapDto.setAuthPerNameTrns(regForm.getAuthPerName());
            	 mapDto.setIndAuthPersn(regForm.getAuthPerName());
            	 mapDto.setOrgaddressTrns(regForm.getOrgaddress());
            	 mapDto.setSelectedCountry(regForm.getCountry());
            	 mapDto.setSelectedCountryName(getCountryName("1",languageLocale));
            	 mapDto.setSelectedState(regForm.getStatename());
            	 mapDto.setSelectedStateName(getStateName("1",languageLocale));
            	 mapDto.setSelectedDistrict(regForm.getDistrict());
            	 mapDto.setSelectedDistrictName(getDistrictName(regForm.getDistrict(),languageLocale));
            	
            	 mapDto.setFnameTrns("");
           		 mapDto.setMnameTrns("");
            	 mapDto.setLnameTrns("");
            	 //mapDto.setGendarTrns("-");
            	 mapDto.setSelectedGender("");
            	 mapDto.setAgeTrns("-");        //AGE
               	 mapDto.setFatherNameTrns("-");
            	 mapDto.setMotherNameTrns("");
           		 mapDto.setSpouseNameTrns("");
              	 mapDto.setNationalityTrns("");
            	 mapDto.setPostalCodeTrns("");
           		 mapDto.setEmailIDTrns("");
            	 mapDto.setSelectedPhotoId("");
            	 mapDto.setSelectedPhotoIdName("-");
            	 mapDto.setIdnoTrns("-");
            	 
            	// mapDto.setIndReligionTrns("");
            	 mapDto.setIndCategoryTrns("");
            	 mapDto.setIndDisabilityTrns("");
            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
            	 mapDto.setIndDisabilityDescTrns("");
            	 
            	 mapDto.setIndPovertyLineTrns("");
               	 mapDto.setIndMinorityTrns("");
               	 
               	 
               	mapDto.setAuthPerGendarTrns(regForm.getGendar());
           	 if(regForm.getAuthPerGendar().equalsIgnoreCase("m")){
           		 //mapDto.setSelectedGender("Male");
           		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
           			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
           		 
           		 }else{
           			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
           			
           		 }
           	 }
           	 else{
           		 //mapDto.setSelectedGender("Female");
           		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
           			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
           		 
           		 }else{
           			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
           			
           		 }
           	 }
           	 mapDto.setAuthPerFatherNameTrns(regForm.getFatherName());
           	 
           	 mapDto.setRelationshipTrns(regForm.getRelationship());
	             mapDto.setRelationshipNameTrns(getRelationshipName(Integer.toString(regForm.getRelationship()),languageLocale));
	             
	             mapDto.setAuthPerAddressTrns(regForm.getAuthPerAddress().trim());
	             mapDto.setAuthPerCountryTrns(regForm.getAuthPerCountry());
	             mapDto.setAuthPerCountryNameTrns(getCountryName("1",languageLocale));
	             mapDto.setAuthPerStatenameTrns(regForm.getAuthPerStatename());
	             mapDto.setAuthPerStatenameNameTrns(getStateName("1",languageLocale));
	             mapDto.setAuthPerDistrictTrns(regForm.getAuthPerDistrict());
	             mapDto.setAuthPerDistrictNameTrns(getDistrictName(regForm.getAuthPerDistrict().trim(),languageLocale));
            	 
             }
             if(regForm.getListAdoptedParty().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
            	 //individual
            	 
            	 mapDto.setListAdoptedPartyNameTrns(getAppleteName(regForm.getListAdoptedParty().trim(), languageLocale));
            	 
            	 mapDto.setFnameTrns(regForm.getFname());
            	 if(regForm.getMname().equals("") || regForm.getMname().equals("null"))
            		 mapDto.setMnameTrns("-");
            	 else
            	     mapDto.setMnameTrns(regForm.getMname());
            	 mapDto.setLnameTrns(regForm.getLname());
            	 mapDto.setGendarTrns(regForm.getGendar());
            	 if(regForm.getGendar().equalsIgnoreCase("m")){
            		// mapDto.setSelectedGender("Male");
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
            	 if(regForm.getAge().equals("") || regForm.getAge().equals("null"))
            		 mapDto.setAgeTrns("-");
            	 else
            	     mapDto.setAgeTrns(regForm.getAge());
            	 mapDto.setFatherNameTrns(regForm.getFatherName());
            	 if(regForm.getMotherName().equals("") || regForm.getMotherName().equals("null"))
            		 mapDto.setMotherNameTrns("-");
            	 else
            	     mapDto.setMotherNameTrns(regForm.getMotherName());
            	 if(regForm.getSpouseName().equals("") || regForm.getSpouseName().equals("null"))
            		 mapDto.setSpouseNameTrns("-");
            	 else
            	     mapDto.setSpouseNameTrns(regForm.getSpouseName());
            	 mapDto.setNationalityTrns(regForm.getNationality());
            	 
            	 if(regForm.getPostalCode().equals("") || regForm.getPostalCode().equals("null"))
            		 mapDto.setPostalCodeTrns("-");
            	 else
            	     mapDto.setPostalCodeTrns(regForm.getPostalCode());
            	 
            	 if(regForm.getEmailID().equals("") || regForm.getEmailID().equals("null"))
            		 mapDto.setEmailIDTrns("-");
            	 else
            	     mapDto.setEmailIDTrns(regForm.getEmailID());
            	 mapDto.setSelectedPhotoId(regForm.getListID());
            	 
            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(regForm.getListID(),languageLocale));               
            	 if(regForm.getIdno().equals("") || regForm.getIdno().equals("null"))
            		 mapDto.setIdnoTrns("-");
            	 else
            	     mapDto.setIdnoTrns(regForm.getIdno());
            	 
            	
            	 mapDto.setOgrNameTrns("-");
            	 mapDto.setAuthPerNameTrns("-");
            	 mapDto.setIndAuthPersn(regForm.getFname()+" "+regForm.getLname());
            	 mapDto.setOrgaddressTrns(regForm.getIndaddress());
            	 mapDto.setSelectedCountry(regForm.getIndcountry());
            	 mapDto.setSelectedCountryName(getCountryName(regForm.getIndcountry(),languageLocale));
            	 mapDto.setSelectedState(regForm.getIndstatename());
            	 mapDto.setSelectedStateName(getStateName(regForm.getIndstatename(),languageLocale));
            	 mapDto.setSelectedDistrict(regForm.getInddistrict());
            	 mapDto.setSelectedDistrictName(getDistrictName(regForm.getInddistrict(),languageLocale));
            	 
               	 /*if(regForm.getIndReligion().equals("") || regForm.getIndReligion().equals("null"))
               		mapDto.setIndReligionTrns("-");
               	 else
            	    mapDto.setIndReligionTrns(regForm.getIndReligion());*/
               	 if(regForm.getIndCategory().equals("") || regForm.getIndCategory().equals("null"))
               		mapDto.setIndCategoryTrns("-");
               	 else
            	    mapDto.setIndCategoryTrns(regForm.getIndCategory());
               	 mapDto.setSelectedCategoryName(getCategoryName(regForm.getIndCategory(),languageLocale));
               	 if(regForm.getIndDisability().equals("") || regForm.getIndDisability().equals("null")){
               		mapDto.setIndDisabilityTrns("-");}
               	 else if(regForm.getIndDisability().equalsIgnoreCase("Y") ){
            	    //mapDto.setIndDisabilityTrns("Yes");
               		 mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
                   		 }else{
                   			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
                   		 }
            	    if(regForm.getIndDisabilityDesc().equals("") || regForm.getIndDisabilityDesc().equals("null")){
            	    	mapDto.setIndDisabilityDescTrns("-");
            	    }else{
            	    	mapDto.setIndDisabilityDescTrns(regForm.getIndDisabilityDesc());
            	    }
               	 
               	 }
               	else if(regForm.getIndDisability().equalsIgnoreCase("N") ){
            	   // mapDto.setIndDisabilityTrns("No");
               		mapDto.setIndDisabilityIdTrns(regForm.getIndDisability());
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
                   		 }else{
                   			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
                   		 }
            	    
               	}
               	     
               	 
               	if(regForm.getIndPovertyLine().equals("") || regForm.getIndPovertyLine().equals("null")){
               		mapDto.setIndPovertyLineTrns("-");}
               	 else if(regForm.getIndPovertyLine().equalsIgnoreCase("Y") ){
            	    //mapDto.setIndPovertyLineTrns("Yes");
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
                   		 }else{
                   			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
                   		 }
            	     }
               	else if(regForm.getIndPovertyLine().equalsIgnoreCase("N") ){
            	   // mapDto.setIndPovertyLineTrns("No");
            	    
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
                   		 }else{
                   			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
                   		 }
            	    
               	}
               	
               	if(regForm.getIndMinority().equals("") || regForm.getIndMinority().equals("null")){
               		mapDto.setIndMinorityTrns("-");}
               	 else if(regForm.getIndMinority().equalsIgnoreCase("Y") ){
            	   // mapDto.setIndMinorityTrns("Yes");
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
                   		 }else{
                   			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
                   		 }
            	     }
               	else if(regForm.getIndMinority().equalsIgnoreCase("N") ){
               		
            	   // mapDto.setIndMinorityTrns("No");
            	    
            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
                   		 }else{
                   			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
                   		 }
            	    
               	}
               	mapDto.setSelectedOccupationName(getOccupationName(regForm.getOccupationId(),languageLocale));
               	
               	mapDto.setIndScheduleAreaTrns(regForm.getIndScheduleArea());
              	if(regForm.getIndScheduleArea().equalsIgnoreCase("N")){
              		//mapDto.setIndScheduleAreaTrnsDisplay("No");
              		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
                   		 }else{
                   			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
                   		 }
              		if(regForm.getIndScheduleArea().equalsIgnoreCase("") || regForm.getIndScheduleArea().trim().equals("null")){
              			mapDto.setPermissionNoTrns("-");
              		}else{
              			mapDto.setPermissionNoTrns(regForm.getPermissionNo());
              		}
              		
              		mapDto.setDocumentNameTrns("CERTIFICATE");
              		mapDto.setFilePathTrns(regForm.getFilePath());
              		if(regForm.getFilePath()!=null && !regForm.getFilePath().equalsIgnoreCase("") && !regForm.getFilePath().equalsIgnoreCase("null")){
	               		mapDto.setDocContentsTrns(DMSUtility.getDocumentBytes(regForm.getFilePath()));
	               		}else{
	                   		mapDto.setDocContentsTrns(new byte[0]);
	        	
	               		}
              		
              	}else{
              		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
              		
              		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
                   		 }else{
                   			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
                   		 }
              	}
               	
               	/*
               	 mapDto.setIndPovertyLineTrns(regForm.getIndPovertyLine());
               	 mapDto.setIndMinorityTrns(regForm.getIndMinority());*/
               	 
               	 
               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
               	mapDto.setRelationshipTrns(regForm.getRelationship());
               	if(regForm.getRelationship()!=0){
               	mapDto.setRelationshipNameTrns(getRelationshipName(Integer.toString(regForm.getRelationship()),languageLocale));
               	}else{
               		mapDto.setRelationshipNameTrns("");
               	}
             }
             
             //for uploads
            mapDto.setU2DocumentNameTrns("PHOTO PROOF");
       		mapDto.setU2FilePathTrns(regForm.getU2FilePath());
       		if(regForm.getU2FilePath()!=null && !regForm.getU2FilePath().equalsIgnoreCase("") && !regForm.getU2FilePath().equalsIgnoreCase("null")){
       		mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU2FilePath()));
       		}else{
           		mapDto.setU2DocContentsTrns(new byte[0]);
	
       		}
       		
       		mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");
       		mapDto.setU3FilePathTrns(regForm.getU3FilePath());
       		if(regForm.getU3FilePath()!=null && !regForm.getU3FilePath().equalsIgnoreCase("") && !regForm.getU3FilePath().equalsIgnoreCase("null")){
           		mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU3FilePath()));
           		}else{
               		mapDto.setU3DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU4DocumentNameTrns("EXECUTANT PHOT0");
       		mapDto.setU4FilePathTrns(regForm.getU4FilePath());
       		if(regForm.getU4FilePath()!=null && !regForm.getU4FilePath().equalsIgnoreCase("") && !regForm.getU4FilePath().equalsIgnoreCase("null")){
           		mapDto.setU4DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU4FilePath()));
           		}else{
               		mapDto.setU4DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY");
       		mapDto.setU5FilePathTrns(regForm.getU5FilePath());
       		if(regForm.getU5FilePath()!=null && !regForm.getU5FilePath().equalsIgnoreCase("") && !regForm.getU5FilePath().equalsIgnoreCase("null")){
           		mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU5FilePath()));
           		}else{
               		mapDto.setU5DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");
       		mapDto.setU6FilePathTrns(regForm.getU6FilePath());
       		if(regForm.getU6FilePath()!=null && !regForm.getU6FilePath().equalsIgnoreCase("") && !regForm.getU6FilePath().equalsIgnoreCase("null")){
           		mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU6FilePath()));
           		}else{
               		mapDto.setU6DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU7DocumentNameTrns("EXECUTANT PHOT0");
       		mapDto.setU7FilePathTrns(regForm.getU7FilePath());
       		if(regForm.getU7FilePath()!=null && !regForm.getU7FilePath().equalsIgnoreCase("") && !regForm.getU7FilePath().equalsIgnoreCase("null")){
           		mapDto.setU7DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU7FilePath()));
           		}else{
               		mapDto.setU7DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");
       		mapDto.setU8FilePathTrns(regForm.getU8FilePath());
       		if(regForm.getU8FilePath()!=null && !regForm.getU8FilePath().equalsIgnoreCase("") && !regForm.getU8FilePath().equalsIgnoreCase("null")){
           		mapDto.setU8DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU8FilePath()));
           		}else{
               		mapDto.setU8DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");
       		mapDto.setU9FilePathTrns(regForm.getU9FilePath());
       		if(regForm.getU9FilePath()!=null && !regForm.getU9FilePath().equalsIgnoreCase("") && !regForm.getU9FilePath().equalsIgnoreCase("null")){
           		mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU9FilePath()));
           		}else{
               		mapDto.setU9DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");
       		mapDto.setU10FilePathTrns(regForm.getU10FilePath());
       		if(regForm.getU10FilePath()!=null && !regForm.getU10FilePath().equalsIgnoreCase("") && !regForm.getU10FilePath().equalsIgnoreCase("null")){
           		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU10FilePath()));
           		}else{
               		mapDto.setU10DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");
       		mapDto.setU11FilePathTrns(regForm.getU11FilePath());
       		if(regForm.getU11FilePath()!=null && !regForm.getU11FilePath().equalsIgnoreCase("") && !regForm.getU11FilePath().equalsIgnoreCase("null")){
           		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(regForm.getU11FilePath()));
           		}else{
               		mapDto.setU11DocContentsTrns(new byte[0]);
    	
           		}
       		
       		mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
       		mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
       		if(regForm.getDatePoaReg()==null || regForm.getDatePoaReg().equalsIgnoreCase("") || regForm.getDatePoaReg().equalsIgnoreCase("null")){
       		
       		mapDto.setDatePoaRegTrns("");
       		}
       		else{
       			mapDto.setDatePoaRegTrns(convertDOB2(regForm.getDatePoaReg()));
       		}
       		
       		//end for uploads
             
          	int roleId=Integer.parseInt(regForm.getPartyType());
          	String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
          	
          	mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
          	
     		//int claimantFlag=Integer.parseInt(claimantArr[0].trim());
            if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG || 
            		regForm.getExecutantClaimant()==RegInitConstant.CLAIMANT_FLAG_2 ||  
            		regForm.getExecutantClaimant()==RegInitConstant.CLAIMANT_FLAG_4)
            {
            	
            	mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
            	
            	String ownerId=regForm.getPoaOwnerId();
            	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
            	String ownerDetls=ownerList.toString();
            	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
            	String ownerDetlsArr[]=ownerDetls.split(",");
            	
           	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
           	 {
             mapDto.setOwnerOgrNameTrns("-");
             mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
             }
             else
             {
             mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
             mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
           	 }
           	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f")){
           	 //mapDto.setOwnerGendarTrns("Female");
           	 
           	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
     			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
     		 
     		 }else{
     			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
     			
     		 }
           	 
           	 }
           	 else{
                //mapDto.setOwnerGendarTrns("Male");
                if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
        		 
        		 }else{
        			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
        			
        		 }
           	 }
           	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
           	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
           	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
           	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
                  		mapDto.setOwnerEmailIDTrns("-");
                  	 else
               	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
           	
           	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
           	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
           	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));   
           	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
           	 mapDto.setOwnerProofNameTrns(commonBd.getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
            }
            else{
            	mapDto.setPoaHolderFlag(0);
            }
             
             
             
             
             
             int count=form.getMapKeyCount();
     		
 			count = count + 1;
             if(mapTransParties.containsKey(Integer.toString(count))){
                 
            	 count++;
            	 mapTransParties.put(Integer.toString(count), mapDto);
           	 
                }
            else
            	mapTransParties.put(Integer.toString(count), mapDto);
                 
            
            form.setMapKeyCount(count);
            
            //form.setAddMoreCounter(count);
            form.setAddMoreCounter(mapTransParties.size());
            //key="key";
                     
           
            
			 form.setPartyType(regForm.getPartyType());
			 		
			 if(regForm.getShareOfProp().equals("100"))
		     return mapTransParties;
			 else
			 return getApplicantShareHolders(appId,mapTransParties,key,form,languageLocale);		 
	
}
	/**
	 * for getting valuation id corresponding to registration app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getValuationId(String appId) throws Exception {
		
		return commonBd.getValuationId(appId);
	}
	/**
	 * getPropDetlsForDashboard
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getPropDetlsForDashboard(String appId) throws Exception {
				
		CustomArrayList propList = new CustomArrayList();
			
		propList=commonBd.getPropDetlsForDashboard(appId);
		
		if(propList!=null && propList.size()==1){
		//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
			String propDetails=propList.toString();
		String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
	    return propDetsArray;
		}
		else{
			return null;
		}
	}
	/**
	 * getPoaForDashboard
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	/*public String[] getPoaForDashboard(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		
		ArrayList poa=commonBd.getPoaForDashboard(appId);
		String partyType[]=new String[poa.size()];
		ArrayList row_list=new ArrayList();
		String row;
		for(int i=0;i<poa.size();i++){
			//ArrayList row_list=new ArrayList();
			row_list.add(poa.get(i));
			row=row_list.toString();
			row=row.substring(2,(row.length()-2));
			partyType[i]=row;
		
		}
	return partyType;
	}*/
	/**
	 * getPoaListFromDb
	 * for getting poa list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
/*	public ArrayList getPoaListFromDb(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		CommonDTO dto = null;
		ArrayList poaListFinal=new ArrayList();
		ArrayList poaListDB=commonBd.getPoaListFromDb(appId);
		ArrayList row_list=new ArrayList();
		String poaList;
		String poaArr[];
		for(int i=0;i<poaListDB.size();i++){
			//ArrayList row_list=new ArrayList();
			poaList=poaListDB.get(i).toString();
			poaList=poaList.substring(1, poaList.length()-1);
			poaArr=poaList.split(",");
			
			dto = new CommonDTO();
            dto.setId(poaArr[3].trim());
            if(!poaArr[0].equals(null) && !poaArr[0].equalsIgnoreCase(""))
            dto.setName(poaArr[0]+poaArr[1]);
            else
            	dto.setName(poaArr[2]);	
            
			poaListFinal.add(dto);
		}
		
		
		
		
		return poaListFinal;
	}*/
	/**
	 * getOwnerListFromDb
	 * for getting owner list from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
/*	public ArrayList getOwnerListFromDb(String appId) throws Exception {
		
		
		//RegCommonDAO dao = new RegCommonDAO();
		CommonDTO dto = null;
		ArrayList ownerListFinal=new ArrayList();
		ArrayList ownerListDB=commonBd.getOwnerListFromDb(appId);
		for(int i=0;i<ownerListDB.size();i++){
			ArrayList row_list=new ArrayList();
			String ownerList=ownerListDB.get(i).toString();
			ownerList=ownerList.substring(1, ownerList.length()-1);
			String ownerArr[]=ownerList.split(",");
			
			dto = new CommonDTO();
            dto.setId(ownerArr[3].trim());
            if(!ownerArr[0].equals(null) && !ownerArr[0].equalsIgnoreCase(""))
            dto.setName(ownerArr[0]+ownerArr[1]);
            else
            	dto.setName(ownerArr[2]);	
            
            ownerListFinal.add(dto);
		}
		
		return ownerListFinal;
		
		
	}*/
	/**
	 * for getting property id corresponding to registration app id from db.
	 * @param 
	 * @return Long
	 * @author ROOPAM
	 */
	public String getPropertyId(String appId) throws Exception {
		
		return commonBd.getPropertyId(appId);
	}
	/**
	 * getPropertyIdMarketVal
	 * for getting PROPERTY ID AND MARKET VALUE details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketVal(String appId) throws Exception {
		
		return commonBd.getPropertyIdMarketVal(appId);
	}
	/**
	 * getTransPartyDets
	 * for getting transacting party details corresponding to a PROPERTY ID from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getTransPartyDets(String propId, String appId, String languageLocale, int deedId, int inst) throws Exception {
		
		
		ArrayList list=commonBd.getTransPartyDets(propId,appId);
		ArrayList finalList=new ArrayList();
		ArrayList row_list=new ArrayList();
		String row;
		String rowArr[];
		CommonDTO dto;
		if(list != null && list.size()>0){
		for(int i=0;i<list.size();i++){
			row_list=(ArrayList)list.get(i);
			
			row=row_list.toString();
			row=row.substring(1, row.length()-1);
			rowArr=row.split(",");
			
			dto=new CommonDTO();
			//dto.setRole(rowArr[0].trim());
			
			dto.setRole(getRoleName(rowArr[5].trim(),languageLocale,deedId,inst));
			
			if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
				dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
			}
			else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
				dto.setName(rowArr[3].trim());
			}
			dto.setId(rowArr[4].trim());
			dto.setRoleId(rowArr[5].trim());
			finalList.add(dto);
		}
		return finalList;
		}
		else{
			return null;
		}
	}
	/**
	 * for getting payment flag corresponding to registration app id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String[] getPaymentFlag(String appId) throws Exception {
		
		ArrayList list=commonBd.getPaymentFlag(appId);
		
		
		//String arr[]=new String[list.size()];
	
			if(list.size()>0){
				String arr[]=new String[list.size()];
			String row=list.toString();
			row=row.substring(2,(row.length()-2));
			arr=row.split(",");
			return arr;
			}
			else{
				return null;
			}
	
		
		
		
		//return arr;
	}
	/**
	 * for inserting registration initiation payment txn details in db.
	 * @param RegCommonForm
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertPaymentDetails(RegCommonForm regForm) {
		return commonBd.insertPaymentDetails(regForm);
	}
	/**
	 * getPartyDetsForViewConfirm
	 * for getting APPLICANT PARTY details from db.
	 * @param String, String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public HashMap getPartyDetsForViewConfirm(String appId, String partyId,int deedId, int deedTypeFlag,String languageLocale,int inst) throws Exception {
		
		ArrayList list=commonBd.getPartyDetsForViewConfirm(appId,partyId);
		
		String listDetls=list.toString();
		listDetls=listDetls.substring(2, listDetls.length()-2);
		String listArr[]=listDetls.split(",");
		
		HashMap map=new HashMap();
		RegCommonDTO mapDto =new RegCommonDTO();
		
        mapDto.setListAdoptedPartyTrns(listArr[0].trim());
        mapDto.setListAdoptedPartyNameTrns(getAppleteName(listArr[0].trim(), languageLocale));
        
        mapDto.setApplicantOrTransParty("Applicant");
        mapDto.setPartyRoleTypeId(listArr[29].trim());
        mapDto.setPartyTxnId(listArr[24].trim());
        
       
      	 if(listArr[27].trim().equals("") || listArr[27].trim().equals("null") || listArr[28].trim()==null)
      		mapDto.setRelationWithOwnerTrns("-");
      	 else
   	    mapDto.setRelationWithOwnerTrns(listArr[27].trim());
      	 if(listArr[28].trim().equals("") || listArr[28].trim().equals("null") || listArr[28].trim()==null){
      		mapDto.setShareOfPropTrns("-");
      		mapDto.setHdnDeclareShareCheck("N");
      	 }
      	 else{
   	    mapDto.setShareOfPropTrns(listArr[28].trim());
   	 mapDto.setHdnDeclareShareCheck("Y");
      	 }
        
      	int roleId=Integer.parseInt(listArr[29].trim());
      	
      	String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
 		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
 		
 		mapDto.setClaimantFlag(claimantFlag);
        
        if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
       	 //organization
       	 mapDto.setOgrNameTrns(listArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	 mapDto.setAuthPerNameTrns(listArr[19].trim());
       	 
       	 mapDto.setFnameTrns("");
      	 mapDto.setMnameTrns("");
       	 mapDto.setLnameTrns("");
       	// mapDto.setGendarTrns("");
       	mapDto.setAuthPerGendarTrns(listArr[5].trim());
      	 if(listArr[5].trim().equalsIgnoreCase("m")){
      		// mapDto.setSelectedGender("Male");
      		 
      		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
       		 
       		 }else{
       			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
       			 
       		 }
      		 
      	 }
      	 else{
      		 //mapDto.setSelectedGender("Female");
      		 
      		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
       		 
       		 }else{
       			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
       			 
       		 }
      		 
      	 }
      	 
      	 
       //	 mapDto.setSelectedGender("");
       	 mapDto.setUserDOBTrns("");
       	mapDto.setAgeTrns("");
         mapDto.setAuthPerFatherNameTrns(listArr[20].trim());
       	 mapDto.setMotherNameTrns("");
      	 mapDto.setSpouseNameTrns("");
         mapDto.setNationalityTrns("");
       	 mapDto.setPostalCodeTrns("");
      	 mapDto.setEmailIDTrns("");
       	 mapDto.setSelectedPhotoId("");
       	 mapDto.setSelectedPhotoIdName("");
       	mapDto.setListIDTrns("");
       	 mapDto.setIdnoTrns("");
       	 mapDto.setSelectedCategoryName("");
       	 mapDto.setIndCategoryTrns("");
       	 mapDto.setIndDisabilityTrns("");
       	 mapDto.setIndDisabilityDescTrns("");
       	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
       	 mapDto.setIndMinorityTrns("");
       	 mapDto.setIndPovertyLineTrns("");
       	 
       	if(listArr[53].trim()!=null && !listArr[53].trim().equalsIgnoreCase("") && !listArr[53].trim().equalsIgnoreCase("null"))
      	{
      	mapDto.setRelationshipTrns(Integer.parseInt(listArr[53].trim()));
      	mapDto.setRelationshipNameTrns(getRelationshipName(listArr[53].trim(),languageLocale));
      	}else{

          	mapDto.setRelationshipTrns(0);
          	mapDto.setRelationshipNameTrns("");
          	
      	}
       	
       	
       
        mapDto.setAuthPerCountryTrns(listArr[55].trim());
        mapDto.setAuthPerCountryNameTrns(getCountryName("1",languageLocale));
        mapDto.setAuthPerStatenameTrns(listArr[56].trim());
        mapDto.setAuthPerStatenameNameTrns(getStateName("1",languageLocale));
        mapDto.setAuthPerDistrictTrns(listArr[57].trim());
        mapDto.setAuthPerDistrictNameTrns(getDistrictName(listArr[57].trim(),languageLocale));
        
    	mapDto.setAuthPerAddressTrns(listArr[58].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	 
        }
        if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
       	 //individual
       	 mapDto.setFnameTrns(listArr[2].trim());
       	 if(listArr[3].trim().equals("") || listArr[3].trim().equals("null"))
       		 mapDto.setMnameTrns("-");
       	 else
       	     mapDto.setMnameTrns(listArr[3].trim());
       	 mapDto.setLnameTrns(listArr[4].trim());
       	 mapDto.setGendarTrns(listArr[5].trim());
       	 if(listArr[5].trim().equalsIgnoreCase("m")){
       		// mapDto.setSelectedGender("Male");
       		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
       		 
       		 }else{
       			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
       			 
       		 }
       	 }
       	 else{
       		 //mapDto.setSelectedGender("Female");
       		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
       		 
       		 }else{
       			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
       			 
       		 }
       	 }
       	 
       	 
       	 if(listArr[6].trim().equals("") || listArr[6].trim().equals("null")){
       		 mapDto.setAgeTrns("-");
       		 /*mapDto.setUserDayTrns("");
       		 mapDto.setUserMonthTrns("");
       		 mapDto.setUserYearTrns("");*/
       		 
       	 }
       	 else{
       	     //mapDto.setUserDOBTrns(convertDOB(listArr[6].trim())); 
       	  mapDto.setAgeTrns(listArr[6].trim());
       	     
       	     /*String[] date=parseStringDOBfromDB(mapDto.getUserDOBTrns());
       	     System.out.println("day-->"+date[0]+"\nmonth-->"+date[1]);
       	     mapDto.setUserDayTrns(date[0]);
    		 mapDto.setUserMonthTrns(date[1]);
    		 mapDto.setUserYearTrns(date[2]);*/
       	     
       	     
       	 }
       	 
       	 
       	 
       	 mapDto.setFatherNameTrns(listArr[20].trim());
       	 if(listArr[21].trim().equals("") || listArr[21].trim().equals("null"))
       		 mapDto.setMotherNameTrns("-");
       	 else
       	     mapDto.setMotherNameTrns(listArr[21].trim());
       	 if(listArr[31].trim().equals("") || listArr[31].trim().equals("null"))
       		 mapDto.setSpouseNameTrns("-");
       	 else
       	     mapDto.setSpouseNameTrns(listArr[31].trim());
       	 mapDto.setNationalityTrns(listArr[7].trim());
       	 
       	 if(listArr[12].trim().equals("") || listArr[12].trim().equals("null"))
       		 mapDto.setPostalCodeTrns("-");
       	 else
       	     mapDto.setPostalCodeTrns(listArr[12].trim());
       	 
       	 
       	 
       	 if(listArr[15].trim().equals("") || listArr[15].trim().equals("null"))
       		 mapDto.setEmailIDTrns("-");
       	 else
       	     mapDto.setEmailIDTrns(listArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	
       	 mapDto.setListIDTrns(listArr[16].trim());
       	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(listArr[16].trim(),languageLocale));               
       	 if(listArr[17].trim().equals("") || listArr[17].trim().equals("null"))
       		 mapDto.setIdnoTrns("-");
       	 else
       	     mapDto.setIdnoTrns(listArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
         mapDto.setOgrNameTrns("");
       	 mapDto.setAuthPerNameTrns("");
       	 
          	 
          	 
          	 if(listArr[22].trim().equals("") || listArr[22].trim().equals("null"))
          	mapDto.setIndCategoryTrns("-");
          	 else
       	    mapDto.setIndCategoryTrns(listArr[22].trim());
          	 
          	 
          	 mapDto.setSelectedCategoryName(getCategoryName(listArr[22].trim(),languageLocale));
          	 if(listArr[25].trim().equals("") || listArr[25].trim().equals("null"))
          	 mapDto.setIndDisabilityTrns("-");
          	 else if(listArr[25].trim().equalsIgnoreCase("Y")){
       	     mapDto.setIndDisabilityIdTrns("Y");
       	  if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
         		 }else{
         			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
         		 }
          	 }
          	 else if(listArr[25].trim().equalsIgnoreCase("N")){
          		mapDto.setIndDisabilityIdTrns("N");
           	 
           	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
           		 }else{
           			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
           		 }
           	 
          	 }
          	        
          	if( listArr[25].trim().equalsIgnoreCase("N") &&(listArr[26].trim().equals("") || listArr[26].trim().equals("null")))
          		mapDto.setIndDisabilityDescTrns("-");
          	 else
       	    mapDto.setIndDisabilityDescTrns(listArr[26].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
          	 
          	 
          	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
          	
          	mapDto.setSelectedOccupationName(getOccupationName(listArr[33].trim(),languageLocale));
          	mapDto.setOccupationIdTrns(listArr[33].trim());
          //33 occupation id
          	//34 occupation id
          	//35 collector permission no.
          	//36 collector certificate
          	
          	
          	if(listArr[37].trim().equalsIgnoreCase("N")){
          	//mapDto.setIndMinorityTrns("No");
          	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
           		 }else{
           			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
           		 }
          	}
          	else{
          	//mapDto.setIndMinorityTrns("Yes");
          	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
           		 }else{
           			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
           		 }
          	
          	}	
          	
          	if(listArr[38].trim().equalsIgnoreCase("N")){
           // mapDto.setIndPovertyLineTrns("No");
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
           		 }else{
           			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
           		 }
          	}
            else{
            //mapDto.setIndPovertyLineTrns("Yes");
            if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
           		 }else{
           			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
           		 }
            }	
          	
          	mapDto.setIndScheduleAreaTrns(listArr[39].trim());
          	if(listArr[39].trim().equalsIgnoreCase("N")){
          		//mapDto.setIndScheduleAreaTrnsDisplay("No");
          		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
               		 }else{
               			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
               		 }
          		if(listArr[35].trim().equalsIgnoreCase("") || listArr[35].trim().equals("null")){
          			mapDto.setPermissionNoTrns("-");
          		}else{
          			mapDto.setPermissionNoTrns(listArr[35].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
          		}
          		
          		mapDto.setDocumentNameTrns("COLLECTOR'S CERTIFICATE");               //CERTIFICATE  40
           		//mapDto.setDocumentSizeTrns(regForm.getDocumentSizeTrns());
           		mapDto.setFilePathTrns(listArr[40].trim());
           		if(listArr[40].trim()!=null && !listArr[40].trim().equalsIgnoreCase("") && !listArr[40].trim().equalsIgnoreCase("null")){
               		mapDto.setDocContentsTrns(DMSUtility.getDocumentBytes(listArr[40].trim()));
               		}else{
                   		mapDto.setDocContentsTrns(new byte[0]);
        	
               		}
           		mapDto.setUploadTypeTrns(listArr[40].trim().substring(listArr[40].trim().lastIndexOf(".")-2));
           		
           		
          		
          	}else{
          		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
          		
          		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
            	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
               		 }else{
               			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
               		 }
          		
          	}
          	//39 st schedule area
          	
          	if(listArr[53].trim()!=null && !listArr[53].trim().equalsIgnoreCase("") && !listArr[53].trim().equalsIgnoreCase("null"))
          	{
          	mapDto.setRelationshipTrns(Integer.parseInt(listArr[53].trim()));
          	mapDto.setRelationshipNameTrns(getRelationshipName(listArr[53].trim(),languageLocale));
          	}else{

              	mapDto.setRelationshipTrns(0);
              	mapDto.setRelationshipNameTrns("");
              	
          	}
          	
        }
        
        //uploads
 
        mapDto.setU2DocumentNameTrns("PHOTO PROOF");             //PHOTO PROOF    41
   		mapDto.setU2FilePathTrns(listArr[41].trim());
   		if(listArr[41].trim()!=null && !listArr[41].trim().equalsIgnoreCase("") && !listArr[41].trim().equalsIgnoreCase("null")){
       		mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(listArr[41].trim()));
       		}else{
           		mapDto.setU2DocContentsTrns(new byte[0]);
	
       		}
   		mapDto.setU2UploadTypeTrns(listArr[41].trim().substring(listArr[41].trim().lastIndexOf(".")-2));

   		     	
   		/*if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE && deedId!=RegInitConstant.DEED_LEASE_NPV &&
   				deedId!=RegInitConstant.DEED_TRUST && deedId!=RegInitConstant.DEED_ADOPTION &&
   				deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA && deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
   				deedId!=RegInitConstant.DEED_SETTLEMENT_NPV && deedId!=RegInitConstant.DEED_POA &&
   				deedId!=RegInitConstant.DEED_WILL_NPV && deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
   				deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV && deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
   				deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV && deedId!=RegInitConstant.DEED_PARTITION_NPV
   				&& deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV && deedId!=RegInitConstant.DEED_COMPOSITION_NPV
   				&& deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV && deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV
   				&& deedId!=RegInitConstant.DEED_TRANSFER_NPV && deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV)*/  //add deed id check
   			
   			
   			
   		if(deedTypeFlag==0)	// 0 for deeds requiring property details
   			
   		{
      //BELOW CODE FOR EXECUTANT
   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
		{
   			mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");         //NOT AFFD EXEC   42
       		mapDto.setU3FilePathTrns(listArr[42].trim());
       		if(listArr[42].trim()!=null && !listArr[42].trim().equalsIgnoreCase("") && !listArr[42].trim().equalsIgnoreCase("null")){
           		mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(listArr[42].trim()));
           		}else{
               		mapDto.setU3DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU3UploadTypeTrns(listArr[42].trim().substring(listArr[42].trim().lastIndexOf(".")-2));
       		
       		mapDto.setU4DocumentNameTrns("EXECUTANT PHOTO");         //EXEC PHOTO       43
       		mapDto.setU4FilePathTrns(listArr[43].trim());
       		if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
           		mapDto.setU4DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
           		}else{
               		mapDto.setU4DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU4UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
       		
       		if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
       			mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
           		mapDto.setU10FilePathTrns(listArr[47].trim());
           		if(listArr[47].trim()!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
               		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
               		}else{
                   		mapDto.setU10DocContentsTrns(new byte[0]);
        	
               		}
           		mapDto.setU10UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
       		}else{
       		if(!mapDto.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
       		mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
       		mapDto.setU10FilePathTrns(listArr[47].trim());
       		if(listArr[47].trim()!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
           		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
           		}else{
               		mapDto.setU10DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU10UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
       		}
       		}
       	}
   	//BELOW CODE FOR EXECUTANT POA HOLDER
		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
		{
			mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY"); 			//NOT AFFD ATTR		44
       		mapDto.setU5FilePathTrns(listArr[44].trim());
       		if(listArr[44].trim()!=null && !listArr[44].trim().equalsIgnoreCase("") && !listArr[44].trim().equalsIgnoreCase("null")){
           		mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(listArr[44].trim()));
           		}else{
               		mapDto.setU5DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU5UploadTypeTrns(listArr[44].trim().substring(listArr[44].trim().lastIndexOf(".")-2));
       		
       		mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");			//ATTR PHOTO		45
       		mapDto.setU6FilePathTrns(listArr[45].trim());
       		if(listArr[45].trim()!=null && !listArr[45].trim().equalsIgnoreCase("") && !listArr[45].trim().equalsIgnoreCase("null")){
           		mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
           		}else{
               		mapDto.setU6DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU6UploadTypeTrns(listArr[45].trim().substring(listArr[45].trim().lastIndexOf(".")-2));
       		
       		mapDto.setU7DocumentNameTrns("EXECUTANT PHOTO");			//EXEC PHOTO		43
       		mapDto.setU7FilePathTrns(listArr[43].trim());
       		if(listArr[43].trim()!=null && !listArr[43].trim().equalsIgnoreCase("") && !listArr[43].trim().equalsIgnoreCase("null")){
           		mapDto.setU7DocContentsTrns(DMSUtility.getDocumentBytes(listArr[43].trim()));
           		}else{
               		mapDto.setU7DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU7UploadTypeTrns(listArr[43].trim().substring(listArr[43].trim().lastIndexOf(".")-2));
       		
       		mapDto.setSrOfficeNameTrns(listArr[48].trim());
       		mapDto.setPoaRegNoTrns(listArr[49].trim());
       		mapDto.setDatePoaRegTrns(convertDOB(listArr[50].trim()));
		}
   		
		//BELOW CODE FOR CLAIMANT
		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
		{
			mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");			//CLAIMANT PHOTO	46
       		mapDto.setU8FilePathTrns(listArr[46].trim());
       		if(listArr[46].trim()!=null && !listArr[46].trim().equalsIgnoreCase("") && !listArr[46].trim().equalsIgnoreCase("null")){
           		mapDto.setU8DocContentsTrns(DMSUtility.getDocumentBytes(listArr[46].trim()));
           		}else{
               		mapDto.setU8DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU8UploadTypeTrns(listArr[46].trim().substring(listArr[46].trim().lastIndexOf(".")-2));
       		
       		if(listArr[0].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
       			mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
           		mapDto.setU11FilePathTrns(listArr[47].trim());
           		if(listArr[47].trim()!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
               		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
               		}else{
                   		mapDto.setU11DocContentsTrns(new byte[0]);
        	
               		}
           		mapDto.setU11UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
       		}else{
       		if(!mapDto.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
       		mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");		//PAN				47
       		mapDto.setU11FilePathTrns(listArr[47].trim());
       		if(listArr[47].trim()!=null && !listArr[47].trim().equalsIgnoreCase("") && !listArr[47].trim().equalsIgnoreCase("null")){
           		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(listArr[47].trim()));
           		}else{
               		mapDto.setU11DocContentsTrns(new byte[0]);
    	
           		}
       		mapDto.setU11UploadTypeTrns(listArr[47].trim().substring(listArr[47].trim().lastIndexOf(".")-2));
       		}
       		}
       	}
   		
		//BELOW CODE FOR CLAIMANT POA HOLDER
		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
		{
   		
   		mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");				//ATTR PHOTO		45
   		mapDto.setU9FilePathTrns(listArr[45].trim());
   		if(listArr[45].trim()!=null && !listArr[45].trim().equalsIgnoreCase("") && !listArr[45].trim().equalsIgnoreCase("null")){
       		mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(listArr[45].trim()));
       		}else{
           		mapDto.setU9DocContentsTrns(new byte[0]);
	
       		}
   		mapDto.setU9UploadTypeTrns(listArr[45].trim().substring(listArr[45].trim().lastIndexOf(".")-2));
		}
   		}
		//end of uploads
        
   		if(roleId!=0)
   		{
        if(listArr[30].trim().equals("") || listArr[30].trim().equals("null"))
      		 mapDto.setRoleName("-");
      	 else
      	     mapDto.setRoleName(getRoleName(listArr[29].trim(),languageLocale, deedId, inst));
   		}else{
   			mapDto.setRoleName(listArr[52].trim());
   		}
        
        mapDto.setOrgaddressTrns(listArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
      	mapDto.setSelectedCountryName(getCountryName("1",languageLocale));
        mapDto.setSelectedStateName(getStateName("1",languageLocale));
      	mapDto.setSelectedDistrictName(getDistrictName(listArr[51].trim(),languageLocale));
      	mapDto.setSelectedDistrict(listArr[51].trim());
      	if(listArr[14].trim().equals("") || listArr[14].trim().equals("null"))
      		 mapDto.setMobnoTrns("-"); 
      	else
      	     mapDto.setMobnoTrns(listArr[14].trim());
      	if(listArr[13].trim().equals("") || listArr[13].trim().equals("null"))
      		 mapDto.setPhnoTrns("-");
      	else
      	     mapDto.setPhnoTrns(listArr[13].trim());
      	mapDto.setPartyTypeTrns(listArr[29].trim());
      	
      	
      	int poaFlag=0;
      
      	if(listArr[54].trim()!=null && !listArr[54].trim().equalsIgnoreCase("") && !listArr[54].trim().equalsIgnoreCase("null")){
      poaFlag=Integer.parseInt(listArr[54].trim());
      	}
      	
      	
        if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
        		poaFlag==RegInitConstant.CLAIMANT_FLAG_2 ||
        		poaFlag==RegInitConstant.CLAIMANT_FLAG_4
        		)
        {
        	
        	mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
        	String ownerId=listArr[32].trim();
        	
        	mapDto.setOwnerIdTrns(ownerId);
        	
        	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
        	String ownerDetls=ownerList.toString();
        	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
        	String ownerDetlsArr[]=ownerDetls.split(",");
        	
       	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
       	 {
         mapDto.setOwnerOgrNameTrns("-");
         mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
         }
         else
         {
         mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
         mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
       	}
       	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f")){
       	 //mapDto.setOwnerGendarTrns("Female");
       	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
  			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
   		 
   		 }else{
   			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
   			 
   		 }
       	 }
       	 else{
            //mapDto.setOwnerGendarTrns("Male");
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
      			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
       		 
       		 }else{
       			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
       			 
       		 }
       	 }
       	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
       	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
       	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
              		mapDto.setOwnerEmailIDTrns("-");
              	 else
           	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	
       	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
       	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
       	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));   
       	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
       	 mapDto.setOwnerProofNameTrns(commonBd.getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
        }
        else{
        	mapDto.setPoaHolderFlag(0);
        }
        
      	map.put(listArr[24].trim(), mapDto);
      	
      	
		return map;
	}
	/**
	 * for getting photo id proof name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getPhotoIdProofName(String proofId,String languageLocale) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getPhotoIdProofName(proofId,languageLocale);
	}
	/**
	 * getPropDetlsForDashboard
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public HashMap getPropertyDetsForViewConfirm(String appId, String propId,String languageLocale) throws Exception {
		
		HashMap propMap=new HashMap();
		
		
		//valuation id
		String valuationId=commonBd.getPropValuationId(appId,propId);
		
		
		//details corresponding to valuation id
		CustomArrayList propList = new CustomArrayList();
		
		//propList=commonBd.getPropDetlsForDashboard(valuationId);
		//propList=commonBd.getPropDetlsForDashboard(propId);
		propList=getPropDetlsForDisplay(propId);
		
		
		//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
		if(propList!=null && propList.size()==1){
		String propDetails=propList.toString();
		String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
		
		
		RegCompletDTO dto=new RegCompletDTO();
		
		dto.setPropertyTypeId(propDetsArray[13].trim());
		
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		dto.setPropertyTypeName(propDetsArray[14].trim());
		dto.setDistName(propDetsArray[1].trim());
		dto.setTehsilName(propDetsArray[3].trim());
		dto.setAreaTypeName(propDetsArray[5].trim());
		dto.setMunicipalBodyName(propDetsArray[12].trim());
		dto.setWardpatwarName(propDetsArray[7].trim());
		dto.setMohallaName(propDetsArray[10].trim());
		}else{
			dto.setPropertyTypeName(propDetsArray[43].trim());
			dto.setDistName(propDetsArray[37].trim());
			dto.setTehsilName(propDetsArray[38].trim());
			dto.setAreaTypeName(propDetsArray[39].trim());
			dto.setMunicipalBodyName(propDetsArray[42].trim());
			dto.setWardpatwarName(propDetsArray[40].trim());
			dto.setMohallaName(propDetsArray[41].trim());
		}
		
		
		
		if(propDetsArray[15].trim()!=null && !propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")){
		dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
		}else{
			dto.setTotalSqMeter(0);
		}
		dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
		if(propDetsArray[13].trim().equalsIgnoreCase("1") ||   //1 for plot
				propDetsArray[13].trim().equalsIgnoreCase("2"))//2 for building
		{
			
			//dto.setUnit("SQM");
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setUnit(RegInitConstant.SQM_ENGLISH);
				}else{
					dto.setUnit(RegInitConstant.SQM_HINDI);
				}
		}
		else
		{
			dto.setUnit(getUnitName(propDetsArray[16].trim(),languageLocale));
			
			/*String unit=propDetsArray[16].trim();
			if(unit.equalsIgnoreCase("2"))
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setUnit(RegInitConstant.SQM_ENGLISH);
					}else{
						dto.setUnit(RegInitConstant.SQM_HINDI);
					}
			}
			else if(unit.equalsIgnoreCase("3"))
			{
				//dto.setUnit("HECTARE");
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					dto.setUnit(RegInitConstant.HECTARE_ENGLISH);
					}else{
						dto.setUnit(RegInitConstant.HECTARE_HINDI);
					}
			}*/
			
		}
		//dto.setUnit("");
		//dto.setMunicipalDutyName("");
		//dto.setMunicipalBodyName(propDetsArray[12].trim());
		String wardstatus=propDetsArray[8].trim();//////
		
		if (wardstatus.equalsIgnoreCase("1"))
		{
			//wardstatus="Ward";
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				wardstatus=RegInitConstant.WARD_ENGLISH;
				}else{
					wardstatus=RegInitConstant.WARD_HINDI;
				}
		}
		else
		{
			//wardstatus="Patwari/Halka";
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				wardstatus=RegInitConstant.PATWARI_ENGLISH;
				}else{
					wardstatus=RegInitConstant.PATWARI_HINDI;
				}
		}
		dto.setPatwariStatus(wardstatus);
		/*dto.setWardpatwarName(propDetsArray[7].trim());
		dto.setMohallaName(propDetsArray[10].trim());*/
		//no. of floors.
		
		if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null")){
		dto.setVikasId("-");}
		else{
		dto.setVikasId(propDetsArray[17].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));}
		dto.setRicircle(propDetsArray[18].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
		dto.setLayoutdet("-");
		else
		dto.setLayoutdet(propDetsArray[19].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		
		if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
		dto.setSheetnum("-");	
		else
		dto.setSheetnum(propDetsArray[20].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
		dto.setPlotnum("-");
		else
		dto.setPlotnum(propDetsArray[21].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setPropAddress(propDetsArray[22].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setNorth(propDetsArray[23].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setSouth(propDetsArray[24].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setEast(propDetsArray[25].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		dto.setWest(propDetsArray[26].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
			dto.setPropLandmark("-");
			else
			dto.setPropLandmark(propDetsArray[27].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		
		
		if(	!propDetsArray[13].trim().equalsIgnoreCase("2"))// 2 for building
		{
			
			dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
			
		}
		//BELOW CODE FOR UPLOADS
		//28
		
		dto.setPropImage1DocumentName("ANGLE 1.JPG");
		dto.setPropImage1FilePath(propDetsArray[28].trim());
		if(propDetsArray[28].trim()!=null && !propDetsArray[28].trim().equalsIgnoreCase("") && !propDetsArray[28].trim().equalsIgnoreCase("null")){
			dto.setPropImage1DocContents(DMSUtility.getDocumentBytes(propDetsArray[28].trim()));
       		}else{
       			dto.setPropImage1DocContents(new byte[0]);
	
       		}
		dto.setPropImage1UploadType(propDetsArray[28].trim().substring(propDetsArray[28].trim().lastIndexOf(".")-2));
		
		dto.setPropImage2DocumentName("ANGLE 2.JPG");
		dto.setPropImage2FilePath(propDetsArray[29].trim());
		if(propDetsArray[29].trim()!=null && !propDetsArray[29].trim().equalsIgnoreCase("") && !propDetsArray[29].trim().equalsIgnoreCase("null")){
			dto.setPropImage2DocContents(DMSUtility.getDocumentBytes(propDetsArray[29].trim()));
       		}else{
       			dto.setPropImage2DocContents(new byte[0]);
	
       		}
		dto.setPropImage2UploadType(propDetsArray[29].trim().substring(propDetsArray[29].trim().lastIndexOf(".")-2));
		
		dto.setPropImage3DocumentName("ANGLE 3.JPG");
		dto.setPropImage3FilePath(propDetsArray[30].trim());
		if(propDetsArray[30].trim()!=null && !propDetsArray[30].trim().equalsIgnoreCase("") && !propDetsArray[30].trim().equalsIgnoreCase("null")){
			dto.setPropImage3DocContents(DMSUtility.getDocumentBytes(propDetsArray[30].trim()));
       		}else{
       			dto.setPropImage3DocContents(new byte[0]);
	
       		}
		dto.setPropImage3UploadType(propDetsArray[30].trim().substring(propDetsArray[30].trim().lastIndexOf(".")-2));
		
		dto.setPropMapDocumentName("MAP.JPG");
		dto.setPropMapFilePath(propDetsArray[31].trim());
		if(propDetsArray[31].trim()!=null && !propDetsArray[31].trim().equalsIgnoreCase("") && !propDetsArray[31].trim().equalsIgnoreCase("null")){
			dto.setPropMapDocContents(DMSUtility.getDocumentBytes(propDetsArray[31].trim()));
       		}else{
       			dto.setPropMapDocContents(new byte[0]);
	
       		}
		dto.setPropMapUploadType(propDetsArray[31].trim().substring(propDetsArray[31].trim().lastIndexOf(".")-2));
		
		if(	propDetsArray[13].trim().equalsIgnoreCase(RegInitConstant.PROPERTY_TYPE)) // 3 for agri land
		{
		dto.setPropRinDocumentName("RIN.JPG");
		dto.setPropRinFilePath(propDetsArray[32].trim());
		if(propDetsArray[32].trim()!=null && !propDetsArray[32].trim().equalsIgnoreCase("") && !propDetsArray[32].trim().equalsIgnoreCase("null")){
			dto.setPropRinDocContents(DMSUtility.getDocumentBytes(propDetsArray[32].trim()));
			dto.setPropRinUploadType(propDetsArray[32].trim().substring(propDetsArray[32].trim().lastIndexOf(".")-2));
       		}else{
       			dto.setPropRinDocContents(new byte[0]);
       			dto.setPropRinUploadType("");
       		}
		
		//dto.setPropRinUploadType(propDetsArray[32].trim().substring(propDetsArray[32].trim().lastIndexOf(".")-2));
		
		dto.setPropKhasraDocumentName("KHASRA.JPG");
		dto.setPropKhasraFilePath(propDetsArray[33].trim());
		if(propDetsArray[33].trim()!=null && !propDetsArray[33].trim().equalsIgnoreCase("") && !propDetsArray[33].trim().equalsIgnoreCase("null")){
			dto.setPropKhasraDocContents(DMSUtility.getDocumentBytes(propDetsArray[33].trim()));
			dto.setPropKhasraUploadType(propDetsArray[33].trim().substring(propDetsArray[33].trim().lastIndexOf(".")-2));
       		}else{
       			dto.setPropKhasraDocContents(new byte[0]);
       			dto.setPropKhasraUploadType("");
	
       		}
		//dto.setPropKhasraUploadType(propDetsArray[33].trim().substring(propDetsArray[33].trim().lastIndexOf(".")-2));
		}
		
		
		
		//other details corresponding to app id and prop id.
		//ArrayList otherPropList = new ArrayList();
		//otherPropList=commonBd.getOtherPropDetsForViewConfirm(appId,propId);
		
		//otherPropList=getPropKhasraDetlsForDisplay(propId);
		//String otherPropDetails=otherPropList.toString().substring(2, (otherPropList.toString().length()-2));
		
		//String otherPropDetsArray[]=otherPropDetails.split(",");
		
		//FOR GETTING PROPERTY KHASRA DETAILS
		String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propId);
		
		
		//following code for inserting khasra detls into map
		if(otherPropDetsArray!=null){
		String[] khasraNum=otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
		String[] khasraArea=otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
		String[] lagaan=otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
		String[] rinPustika=otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
		
		
		
		int length=khasraNum.length;
		CommonDTO objDto;
		
		for(int i=0;i<length;i++){
			objDto=new CommonDTO();
			if(khasraNum[i].equalsIgnoreCase("null"))
			{
				objDto.setKhasraNum("-");
				objDto.setKhasraArea("-");
				objDto.setLagaan("-");
				objDto.setRinPustika("-");
			}else{
			objDto.setKhasraNum(khasraNum[i].trim());
			objDto.setKhasraArea(khasraArea[i].trim());
			objDto.setLagaan(lagaan[i].trim());
			objDto.setRinPustika(rinPustika[i].trim());
			}
			
			dto.getKhasraDetlsDisplay().add(objDto);
		}
		}else{
			CommonDTO objDto=new CommonDTO();
			objDto.setKhasraNum("-");
			objDto.setKhasraArea("-");
			objDto.setLagaan("-");
			objDto.setRinPustika("-");
			dto.getKhasraDetlsDisplay().add(objDto);
		}
		HashMap buildingMap=new HashMap();
		if(propDetsArray[13].trim().equalsIgnoreCase("2")){  //2 FOR BUILDING
		
			
			//following code for getting building floor details
			ArrayList buildingList=getGuildingFloorDetails(valuationId);
			if(buildingList.size()>0){
				
				dto.setFloorCount(buildingList.size());
				RegCompletDTO dto1=new RegCompletDTO();
				ArrayList row_list=new ArrayList();
				String str;
				String[] strArray;
				for(int j=0;j<buildingList.size();j++){
					dto1=new RegCompletDTO();
					row_list=(ArrayList)buildingList.get(j);
					str=row_list.toString();
					str=str.substring(1,str.length()-1);
					strArray=str.split(",");
					//dto1.setUsageBuilding(strArray[7]);
					//dto1.setCeilingType(strArray[8]);
					//dto1.setTypeFloorDesc(strArray[9]);
					dto1.setUsageBuilding(getPropertyL1Name(strArray[1].trim(),languageLocale));  //l1
					dto1.setCeilingType(getPropertyL2Name(strArray[2].trim(),languageLocale));    //l2
					dto1.setTypeFloorDesc(getFloorName(strArray[3].trim(),languageLocale));      //floor
					
					
					dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
					dto1.setConsiderAmtDisplay(getCurrencyFormat(Double.parseDouble(strArray[4])));
					dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
					dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
					dto1.setConstructedArea(strArray[6]);
					
					dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
					
					dto.getMapBuilding().put(strArray[0], dto1);
					
					
				}
				
				
				
			}
			
			
			
			
		}else{
			dto.setFloorCount(0);
		}
			
		propMap.put(propId, dto);
		}else{
			propMap.put("1", new RegCompletDTO());
		}
		return propMap;
	}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getDutyDetls(String appId) throws Exception {
		
		ArrayList list=commonBd.getDutyDetls(appId);
		
		if(list!=null && list.size()==1){
	
			String dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		String dutyListArr[]=dutyList.split(",");
		return dutyListArr;
		}else{
			return null;
		}
	}
	/**
	 * insrtMultiplePropDetls
	 * Returns boolean value in order to check the insertion.
	 * @param form
	 * @author ROOPAM
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insrtMultiplePropDetls(RegCommonForm form) throws Exception {
		return commonBd.insrtMultiplePropDetls(form);
	}
	/**
	 * for getting role id of applicant from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantRoleId(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getApplicantRoleId(appId);
	}
	/**
	 * getShareOfPropList
	 * for getting share of property details from db.
	 * @param String, String
	 * @return int
	 * @author ROOPAM
	 */
	public int getShareOfPropList(String appId) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		
		int total=0;
		ArrayList list=commonBd.getShareOfPropList(appId);
		ArrayList row_list;
		String shareList;
		for(int j=0;j<list.size();j++){
			
			row_list=(ArrayList)list.get(j);
			shareList=row_list.toString();
			shareList=shareList.substring(1, shareList.length()-1);
			if(!shareList.trim().equals("") && !shareList.trim().equals("null")){
				
				total=total+Integer.parseInt(shareList.trim());
				
			}
		}
		return total;
	}
	/**
	 * getApplicantShareHolders
	 * for getting applicant share holder details from db.
	 * @param String appId, HashMap Applicantmap, String key, 
            int keyCount, int count, RegCommonForm regForm
	 * @return HashMap
	 * @author ROOPAM
	 */
	public HashMap getApplicantShareHolders(String appId, HashMap Applicantmap, String key, 
            RegCommonForm regForm,String languageLocale) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		//HashMap map=new HashMap();
		
		ArrayList list=commonBd.getApplicantShareHolders(appId);
		ArrayList row_list;
		String shareList;
		String shareHolderArr[];
		RegCommonDTO mapDto;
		int count;
		
		for(int j=0;j<list.size();j++){
			
			row_list=(ArrayList)list.get(j);
			shareList=row_list.toString();
			shareList=shareList.substring(1, shareList.length()-1);
			
			shareHolderArr=shareList.split(",");
			
			//code for insertion in map
                 mapDto =new RegCommonDTO();
	             if(shareHolderArr[0].trim().length()==11){
	            	 shareHolderArr[0]="0"+shareHolderArr[0].trim();
	         		}
	         		else{
	         			shareHolderArr[0]=shareHolderArr[0].trim();
	         		}
	             //shareHolderArr[1]="0"+shareHolderArr[1].trim();
	             mapDto.setListAdoptedPartyTrns(shareHolderArr[1].trim());
	            
	             mapDto.setApplicantOrTransParty("Applicant");
	             mapDto.setPartyRoleTypeId(shareHolderArr[23].trim());
	             //mapDto.setPartyTypeFlag("C");
	             mapDto.setPartyTypeFlag("A");
	             /*if(shareHolderArr[38].trim().equals("") || shareHolderArr[38].trim().equals("null"))
	            		mapDto.setOwnershipShareTrns("-");
	            	 else
	         	    mapDto.setOwnershipShareTrns(shareHolderArr[38].trim());*/
	            	 if(shareHolderArr[25].trim().equals("") || shareHolderArr[25].trim().equals("null"))
	            		mapDto.setRelationWithOwnerTrns("-");
	            	 else
	         	    mapDto.setRelationWithOwnerTrns(shareHolderArr[25].trim());
	            	 if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null")){
	            		mapDto.setShareOfPropTrns("-");
	            		mapDto.setHdnDeclareShareCheck("N");
	            	 }
	            	 //else if(shareHolderArr[27].trim().equalsIgnoreCase(Integer.toString(RegInitConstant.ROLE_POA_ACCEPTER)) 
	            	//		 && regForm.getShareOfProp().equalsIgnoreCase("0")){
	            	//	 	mapDto.setHdnDeclareShareCheck("Y");
	                	  
	            	// }
	            	 else{
	         	    mapDto.setShareOfPropTrns(shareHolderArr[26].trim());
	         	   mapDto.setHdnDeclareShareCheck("Y");
	            	 }
	            	 mapDto.setSelectedCountry(shareHolderArr[8].trim());
	            	 mapDto.setSelectedCountryName(getCountryName("1",languageLocale));
	            	 mapDto.setSelectedState(shareHolderArr[9].trim());
	            	 mapDto.setSelectedStateName(getStateName("1",languageLocale));
	            	 mapDto.setSelectedDistrict(shareHolderArr[10].trim());
	            	 mapDto.setSelectedDistrictName(getDistrictName(shareHolderArr[10].trim(),languageLocale));
	            	 mapDto.setOrgaddressTrns(shareHolderArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	            	
	            	 if(shareHolderArr[14].trim().equals("") || shareHolderArr[14].trim().equals("null"))
	            		 mapDto.setMobnoTrns("-"); 
	            	 else
	            	     mapDto.setMobnoTrns(shareHolderArr[14].trim());
	            	 if(shareHolderArr[13].trim().equals("") || shareHolderArr[13].trim().equals("null"))
	            		 mapDto.setPhnoTrns("-");
	            	 else
	            	     mapDto.setPhnoTrns(shareHolderArr[13].trim());
	            	
	            	 
	            	 if(regForm.getCommonDeed()!=1){
	            	     mapDto.setRoleName(getRoleName(shareHolderArr[27].trim(),languageLocale,regForm.getDeedID(),regForm.getInstID()));
	            	 }else{
	            		 mapDto.setRoleName(shareHolderArr[47].trim());
	            	 }
	            	 
	            	     //mapDto.setRoleName(commonBd.getRoleName(shareHolderArr[27].trim()));
	            	 mapDto.setPartyTypeTrns(shareHolderArr[27].trim());
	            	 
	             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	            	 //organization
	            	 
	            	 mapDto.setListAdoptedPartyNameTrns(getAppleteName(shareHolderArr[1].trim(), languageLocale));
	            	 mapDto.setOgrNameTrns(shareHolderArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	            	 mapDto.setAuthPerNameTrns(shareHolderArr[19].trim());
	            	 mapDto.setIndAuthPersn(shareHolderArr[19].trim());
	            	/* mapDto.setOrgaddressTrns(shareHolderArr[11].trim());*/
	            	 mapDto.setFnameTrns("");
	           		 mapDto.setMnameTrns("");
	            	 mapDto.setLnameTrns("");
	            	// mapDto.setGendarTrns("-");
	            	 mapDto.setSelectedGender("-");
	            	 mapDto.setAgeTrns("");
	               	 mapDto.setFatherNameTrns("-");
	            	 mapDto.setMotherNameTrns("");
	           		 mapDto.setSpouseNameTrns("");
	              	 mapDto.setNationalityTrns("");
	            	 mapDto.setPostalCodeTrns("");
	           		 mapDto.setEmailIDTrns("");
	            	 mapDto.setSelectedPhotoId("");
	            	 mapDto.setSelectedPhotoIdName("-");
	            	 mapDto.setIdnoTrns("-");
	            	
	            	 //mapDto.setIndReligionTrns("");
	            	 mapDto.setIndCategoryTrns("");
	            	 mapDto.setIndDisabilityTrns("");
	            	 mapDto.setIndDisabilityDescTrns("");
	            	 mapDto.setIndPovertyLineTrns("");
	            	 mapDto.setIndMinorityTrns("");
	            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
	            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
	            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m")){
	            		 //mapDto.setSelectedGender("Male");
	            		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                 			mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
	                 		 
	                 		 }else{
	                 			mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
	                 			
	                 		 }
	            	 }
	            	 else{
	            		 //mapDto.setSelectedGender("Female");
	            		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	                 			mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
	                 		 
	                 		 }else{
	                 			mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
	                 			
	                 		 }
	            	 }
	            	 
	            		if(shareHolderArr[48].trim()!=null && !shareHolderArr[48].trim().equalsIgnoreCase("") && !shareHolderArr[48].trim().equalsIgnoreCase("null")){
	    	               	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[48].trim()));
	    	               	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[48].trim(),languageLocale));
	    	              	}else{
	    	              		mapDto.setRelationshipTrns(0);
	    		               	mapDto.setRelationshipNameTrns("");
	    	              	}
	            		mapDto.setAuthPerFatherNameTrns(shareHolderArr[20].trim());
	            		
	            		
	   	             mapDto.setAuthPerCountryTrns(shareHolderArr[50].trim());
	   	             mapDto.setAuthPerCountryNameTrns(getCountryName("1",languageLocale));
	   	             mapDto.setAuthPerStatenameTrns(shareHolderArr[51].trim());
	   	             mapDto.setAuthPerStatenameNameTrns(getStateName("1",languageLocale));
	   	             mapDto.setAuthPerDistrictTrns(shareHolderArr[52].trim());
	   	             mapDto.setAuthPerDistrictNameTrns(getDistrictName(shareHolderArr[52].trim(),languageLocale));
	   	          mapDto.setAuthPerAddressTrns(shareHolderArr[53].trim());
	            		
	             }
	             
	             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	            	 //individual
	            	 
	            	 mapDto.setListAdoptedPartyNameTrns(getAppleteName(shareHolderArr[1].trim(), languageLocale));
	            	 
	            	 mapDto.setFnameTrns(shareHolderArr[2].trim());
	            	 if(shareHolderArr[3].trim().equals("") || shareHolderArr[3].trim().equals("null"))
	            		 mapDto.setMnameTrns("-");
	            	 else
	            	     mapDto.setMnameTrns(shareHolderArr[3].trim());
	            	 mapDto.setLnameTrns(shareHolderArr[4].trim());
	            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
	            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m")){
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
	            	/* if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
	            		 mapDto.setUserDOBTrns("-");
	            	 else
	            	     mapDto.setUserDOBTrns(convertDOBfromDb(shareHolderArr[6].trim()));*/
	            	 if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
	            		 mapDto.setAgeTrns("-");
	            	 else
	            	     mapDto.setAgeTrns(shareHolderArr[6].trim());
	            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
	            	 if(shareHolderArr[21].trim().equals("") || shareHolderArr[21].trim().equals("null"))
	            		 mapDto.setMotherNameTrns("-");
	            	 else
	            	     mapDto.setMotherNameTrns(shareHolderArr[21].trim());
	            	 
	            	 if(shareHolderArr[28].trim().equals("") || shareHolderArr[28].trim().equals("null"))
	            		 mapDto.setSpouseNameTrns("-");
	            	 else
	            	     mapDto.setSpouseNameTrns(shareHolderArr[28].trim());
	            	 mapDto.setNationalityTrns(shareHolderArr[7].trim());
	            	 
	            	 if(shareHolderArr[12].trim().equals("") || shareHolderArr[12].trim().equals("null"))
	            		 mapDto.setPostalCodeTrns("-");
	            	 else
	            	     mapDto.setPostalCodeTrns(shareHolderArr[12].trim());
	            	
	            	 if(shareHolderArr[15].trim().equals("") || shareHolderArr[15].trim().equals("null"))
	            		 mapDto.setEmailIDTrns("-");
	            	 else
	            	     mapDto.setEmailIDTrns(shareHolderArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	            	 mapDto.setSelectedPhotoId(shareHolderArr[16].trim());
	            	 
	            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(shareHolderArr[16].trim(),languageLocale));               
	            	 mapDto.setIdnoTrns(shareHolderArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	            	 mapDto.setOgrNameTrns("-");
	            	 mapDto.setAuthPerNameTrns("-");
	            	 mapDto.setIndAuthPersn(shareHolderArr[2].trim()+" "+shareHolderArr[4].trim());
	            	 
	               	 /*if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null"))
	               		mapDto.setIndReligionTrns("-");
	               	 else
	            	    mapDto.setIndReligionTrns(shareHolderArr[26].trim());*/
	               	 if(shareHolderArr[22].trim().equals("") || shareHolderArr[22].trim().equals("null"))
	               	 {
	               		mapDto.setIndCategoryTrns("-");
	               	 }
	               	 else
	               	 {
	            	    mapDto.setIndCategoryTrns(shareHolderArr[22].trim());
	               	 }
	               	 
	               	 mapDto.setSelectedCategoryName(getCategoryName(shareHolderArr[22].trim(),languageLocale));
	               	 
	               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
	               	 {
	               		mapDto.setIndDisabilityTrns("-");
	               	 }
	               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
	               	 {
	            	    //mapDto.setIndDisabilityTrns("Yes");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
                       		 }
	            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
	            	    	mapDto.setIndDisabilityDescTrns("-");
	            	    }else{
	            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	            	    }
	               	 }
	               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
	               	{
	            	   // mapDto.setIndDisabilityTrns("No");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                     	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
                        		 }else{
                        			mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
                        		 }
	               	}
	               	 
	               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
	               		mapDto.setIndPovertyLineTrns("-");}
	               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
	            	   // mapDto.setIndPovertyLineTrns("Yes");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
                       		 }
	            	     }
	               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
	            	   //mapDto.setIndPovertyLineTrns("No");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);
                       		 }else{
                       			mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);
                       		 }
	            	    
	               	}
	               	
	               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
	               		mapDto.setIndMinorityTrns("-");}
	               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
	            	   // mapDto.setIndMinorityTrns("Yes");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
                       		 }
	            	     }
	               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
	            	    //mapDto.setIndMinorityTrns("No");
	            	    if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
                       		 }else{
                       			mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
                       		 }    
	               	}
	               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
	               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
	               	
	               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
	              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
	              		//mapDto.setIndScheduleAreaTrnsDisplay("No");
	              		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
                       		 }else{
                       			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
                       		 }
	              		
	              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
	              			mapDto.setPermissionNoTrns("-");
	              		}else{
	              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
	              		}
	              		
	              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
	              		mapDto.setDocumentNameTrns("CERTIFICATE");
	              		if(shareHolderArr[33].trim()!=null && !shareHolderArr[33].trim().equalsIgnoreCase("") && !shareHolderArr[33].trim().equalsIgnoreCase("null")){
		               		mapDto.setDocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[33].trim()));
		               		}else{
		                   		mapDto.setDocContentsTrns(new byte[0]);
		        	
		               		}
	              		
	              	}else{
	              		//mapDto.setIndScheduleAreaTrnsDisplay("Yes");
	              		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
                    	    mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
                       		 }else{
                       			mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
                       		 }
	              	}
	              	if(shareHolderArr[48].trim()!=null && !shareHolderArr[48].trim().equalsIgnoreCase("") && !shareHolderArr[48].trim().equalsIgnoreCase("null")){
	               	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[48].trim()));
	               	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[48].trim(),languageLocale));
	              	}else{
	              		mapDto.setRelationshipTrns(0);
		               	mapDto.setRelationshipNameTrns("");
	              	}
	             }
	             mapDto.setU2DocumentNameTrns("PHOTO PROOF");
	              	mapDto.setU2FilePathTrns(shareHolderArr[37].trim());         //37 PHOTO_PROOF_PATH
	              	if(shareHolderArr[37].trim()!=null && !shareHolderArr[37].trim().equalsIgnoreCase("") && !shareHolderArr[37].trim().equalsIgnoreCase("null")){
	               		mapDto.setU2DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[37].trim()));
	               		}else{
	                   		mapDto.setU2DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	
	              	mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");
	              	mapDto.setU3FilePathTrns(shareHolderArr[38].trim());			//38 NOT_AFFD_EXEC_PATH
	              	if(shareHolderArr[38].trim()!=null && !shareHolderArr[38].trim().equalsIgnoreCase("") && !shareHolderArr[38].trim().equalsIgnoreCase("null")){
	               		mapDto.setU3DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[38].trim()));
	               		}else{
	                   		mapDto.setU3DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU4DocumentNameTrns("EXECUTANT PHOT0");
	              	mapDto.setU4FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
	              	if(shareHolderArr[39].trim()!=null && !shareHolderArr[39].trim().equalsIgnoreCase("") && !shareHolderArr[39].trim().equalsIgnoreCase("null")){
	               		mapDto.setU4DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[39].trim()));
	               		}else{
	                   		mapDto.setU4DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU7DocumentNameTrns("EXECUTANT PHOT0");
	              	mapDto.setU7FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
	              	if(shareHolderArr[39].trim()!=null && !shareHolderArr[39].trim().equalsIgnoreCase("") && !shareHolderArr[39].trim().equalsIgnoreCase("null")){
	               		mapDto.setU7DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[39].trim()));
	               		}else{
	                   		mapDto.setU7DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY");
	              	mapDto.setU5FilePathTrns(shareHolderArr[40].trim());			//40 NOT_AFFD_ATTR_PATH
	              	if(shareHolderArr[40].trim()!=null && !shareHolderArr[40].trim().equalsIgnoreCase("") && !shareHolderArr[40].trim().equalsIgnoreCase("null")){
	               		mapDto.setU5DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[40].trim()));
	               		}else{
	                   		mapDto.setU5DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");
	              	mapDto.setU6FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
	              	if(shareHolderArr[41].trim()!=null && !shareHolderArr[41].trim().equalsIgnoreCase("") && !shareHolderArr[41].trim().equalsIgnoreCase("null")){
	               		mapDto.setU6DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[41].trim()));
	               		}else{
	                   		mapDto.setU6DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");
	              	mapDto.setU9FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
	              	if(shareHolderArr[41].trim()!=null && !shareHolderArr[41].trim().equalsIgnoreCase("") && !shareHolderArr[41].trim().equalsIgnoreCase("null")){
	               		mapDto.setU9DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[41].trim()));
	               		}else{
	                   		mapDto.setU9DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");
	              	mapDto.setU8FilePathTrns(shareHolderArr[42].trim());			//42 CLAIMNT_PHOTO_PATH
	              	if(shareHolderArr[42].trim()!=null && !shareHolderArr[42].trim().equalsIgnoreCase("") && !shareHolderArr[42].trim().equalsIgnoreCase("null")){
	               		mapDto.setU8DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[42].trim()));
	               		}else{
	                   		mapDto.setU8DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");
	              	mapDto.setU10FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
	              	if(shareHolderArr[43].trim()!=null && !shareHolderArr[43].trim().equalsIgnoreCase("") && !shareHolderArr[43].trim().equalsIgnoreCase("null")){
	               		mapDto.setU10DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[43].trim()));
	               		}else{
	                   		mapDto.setU10DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");
	              	mapDto.setU11FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
	              	if(shareHolderArr[43].trim()!=null && !shareHolderArr[43].trim().equalsIgnoreCase("") && !shareHolderArr[43].trim().equalsIgnoreCase("null")){
	               		mapDto.setU11DocContentsTrns(DMSUtility.getDocumentBytes(shareHolderArr[43].trim()));
	               		}else{
	                   		mapDto.setU11DocContentsTrns(new byte[0]);
	        	
	               		}
	              	
	              	mapDto.setSrOfficeNameTrns(shareHolderArr[44].trim());
	              	mapDto.setPoaRegNoTrns(shareHolderArr[45].trim());
	              	//mapDto.setDatePoaRegTrns(convertDOBfromDb(shareHolderArr[46].trim()));
	              	
	              	if(shareHolderArr[46].trim()==null || shareHolderArr[46].trim().equalsIgnoreCase("") || shareHolderArr[46].trim().equalsIgnoreCase("null")){
	               		
	               		mapDto.setDatePoaRegTrns("");
	               		}
	               		else{
	               			mapDto.setDatePoaRegTrns(convertDOB2(shareHolderArr[46].trim()));
	               		}
	              	
	             int roleId=Integer.parseInt(shareHolderArr[27].trim());
	             String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
	             
	             int poaFlag=0;
	             
	             if(shareHolderArr[49].trim()!=null && 
	            		 !shareHolderArr[49].trim().equalsIgnoreCase("") && 
	            		 !shareHolderArr[49].trim().equalsIgnoreCase("null")){
	            	 
	            	 poaFlag=Integer.parseInt(shareHolderArr[49].trim());
	            	 
	            	 
	             }
	             
	             
	             mapDto.setClaimantFlag(Integer.parseInt(claimantArr[0].trim()));
	             if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG || 
	            		 poaFlag==RegInitConstant.CLAIMANT_FLAG_2 ||     
	            		 poaFlag==RegInitConstant.CLAIMANT_FLAG_4)
	             {
	             	
	            	 mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
	            	 
	            	 
	             	String ownerId=shareHolderArr[29].trim();
	             	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
	             	String ownerDetls=ownerList.toString();
	             	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
	             	String ownerDetlsArr[]=ownerDetls.split(",");
	             	
	            	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
	            	 {
	              mapDto.setOwnerOgrNameTrns("-");
	              mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
	              }
	              else
	              {
	              mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim());
	              mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
	            	}
	            	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f")){
	            	// mapDto.setOwnerGendarTrns("Female");
	            	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	              			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_ENGLISH);
	              		 
	              		 }else{
	              			mapDto.setOwnerGendarTrns(RegInitConstant.FEMALE_HINDI);
	              			
	              		 }
	            	 }
	            	 else{
	                 //mapDto.setOwnerGendarTrns("Male");
	                 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	             			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_ENGLISH);
	             		 
	             		 }else{
	             			mapDto.setOwnerGendarTrns(RegInitConstant.MALE_HINDI);
	             			
	             		 }
	            	 }
	            	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
	            	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim());
	            	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
	            	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
	                   		mapDto.setOwnerEmailIDTrns("-");
	                   	 else
	                	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim());
	            	
	            	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim());
	            	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));
	            	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
	            	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
	            	 mapDto.setOwnerProofNameTrns(commonBd.getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
	             }
	             else{
	             	mapDto.setPoaHolderFlag(0);
	             }
	              
	              
	             
	             count=regForm.getMapKeyCount();
	      		
	  			count = count + 1;
	              if(Applicantmap.containsKey(Integer.toString(count))){
	                  
	            	  count++;
	            	  Applicantmap.put(Integer.toString(count), mapDto);
	            	 
	                 }
	             else
	            	 Applicantmap.put(Integer.toString(count), mapDto);
	                  
	             
	             regForm.setMapKeyCount(count);
	             
	             //regForm.setAddMoreCounter(count);
	             regForm.setAddMoreCounter(Applicantmap.size());
	            //key="key";
		}
		
		return Applicantmap;
		
	}
	/**
	 * for getting ROLE NAME from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getRoleName(String roleId,String languageLocale,int deed,int inst) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		
		String roleName=commonBd.getRoleName(roleId,languageLocale);
		
		int partyId=Integer.parseInt(roleId);
		if(deed==RegInitConstant.DEED_COMPOSITION_NPV || deed==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
        		inst==RegInitConstant.INST_TRANSFER_NPV_1 || inst==RegInitConstant.INST_TRANSFER_NPV_2)// for composition deed, letter of license deed & 2 instruments of transfer deed
        {
         	
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
        	{
        		roleName=roleName+RegInitConstant.CREDITOR_ENGLISH;
        	}else{
        		roleName=roleName+RegInitConstant.DEBTOR_ENGLISH;
        	}
        	}else{
        		

            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
            	{
            		roleName=roleName+RegInitConstant.CREDITOR_HINDI;
            	}else{
            		roleName=roleName+RegInitConstant.DEBTOR_HINDI;
            	}
            	
        		
        	}
        
        }else if(inst==RegInitConstant.INST_TRANSFER_NPV_4)// for 1 instrument of transfer deed
        {
         	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
        	{
        		//roleName=roleName;
        	}else{
        		roleName=roleName+RegInitConstant.BENEFICIARY_ENGLISH;
        	}
         	}else{
         		

            	if(partyId==RegInitConstant.ROLE_EXECUTANT_SELF || partyId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
            	{
            		//dto.setName(subList.get(2).toString());
            	}else{
            		roleName=roleName+RegInitConstant.BENEFICIARY_HINDI;
            	}
             	
         		
         	}
        
        }/*else{
        	
        	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
        	dto.setName(subList.get(1).toString());
        	}else{
        		dto.setName(subList.get(2).toString());
        	}
        }*/
		
		return roleName;
	}
	/**
	 * getTransactingPartyIdSeq
	 * Returns string value for sequence.
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public String getTransactingPartyIdSeq() throws Exception {
		return commonBd.getTransactingPartyIdSeq();
	}
	/**
	 * getTransactingPartyIdSeq
	 * Returns string value for sequence.
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public String getTransPartyPropertyMapIdSeq() throws Exception {
		return commonBd.getTransPartyPropertyMapIdSeq();
	}
	/**
	 * getDutyCalculation
	 * Returns stamp duty of multiple properties
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public double getDutyCalculation(RegCommonForm form) {
		double calculatedDuty = 0.0;
		try {
			NumberFormat obj=new DecimalFormat("#0.00");
			IGRSCommon common = new IGRSCommon();
		
			double dutyPaid=0.0;
			double shareVaue=0.0;
			double annualRent=0.0;    //hard coded               //from form
			double baseValue = 0.0;  //hard coded                                      //from form
			int instId =form.getInstID();                     //from table
			int deedId = form.getDeedID();                        //from table
			if(form.getDutyPaid()!=null && form.getDutyPaid()!=""){
			dutyPaid=Double.parseDouble(form.getDutyPaid());                           //from form
			form.setDutyPaid(obj.format(dutyPaid));
			}
			
			String exemption = form.getHdnExAmount();             //from table
			System.out.println("Exemption Type:-" + exemption);
			double marketValue = Double.parseDouble(form.getTotalMarketValue());         //from table
			
			
			if(form.getMarketValueShares()!=null && form.getMarketValueShares()!=""){
			shareVaue=Double.parseDouble(form.getMarketValueShares());                                       //from form
			form.setMarketValueShares(obj.format(shareVaue));
			}
			
			System.out.println("marketValue:-" + marketValue);
			System.out.println("baseValue:-" + baseValue);
			System.out.println("Duty Already Paid : "+dutyPaid);
			System.out.println("Share Value : "+shareVaue);
			//dutyDTO.setBaseValue(marketValue);
		
			if(form.getLabelAmountFlag()!=null){
			if (form.getLabelAmountFlag().equals(CommonConstant.REGISTRATION_NO_MARKET_VALUE))          // flag from table
			{
				marketValue=form.getConsiAmount();                                             //ASK RISHAB
			}
			}
				
				calculatedDuty = common.getStampDuty(deedId, instId, exemption,
						marketValue,annualRent, dutyPaid);
			
			
				
		//For Conveyance deed --Share value instrument	
		if(form.getLabelAmountFlag()!=null){
		if(form.getLabelAmountFlag().equals(CommonConstant.SHARE_VALUE) && exemption.equals("") ){                                // flag from table
			baseValue=(shareVaue+baseValue)*(0.007);
			if (baseValue>calculatedDuty){
				calculatedDuty=baseValue;
				System.out.println("Share Condition Duty :-" + calculatedDuty);
			}
		}
		}
			System.out.println("Duty :-" + calculatedDuty);
		} catch (Exception x) {
			System.out.println(x);
		}

		return calculatedDuty;
	}
	/**
	 * getMunicipalDutyCalculation
	 * Returns municipal duty of multiple properties
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public double[] getMunicipalDutyCalculation(RegCommonForm form)
	{	
		double calculatedDuty[]=new double[3];
		try {
	
			String[] str=getPropDetlsForDutyCalc(form.getValuationId());
		//String[] propertyTypeL1 = propertyDTO.getUsePlotId().split("~");
		int areaTypeId=0;
		String propertyTypeL1Id = "";
	
		int munBodyId=0;
		
		if (str != null) {
			propertyTypeL1Id = str[0].trim();
			areaTypeId= Integer.parseInt(str[1].trim());
			munBodyId=Integer.parseInt(str[2].trim());
		}
		
		
		
		System.out.println("munBody ID :-"+munBodyId);
		
		IGRSCommon common = new IGRSCommon();
		
			
				int instId =form.getInstID();
	
		
	
		int deedId =form.getDeedID();
		String marketVal="";
		double stampDuty=Double.parseDouble(form.getStampduty1());
		System.out.println("@@@@@@@STAMP DUTY @@@@@@@"+stampDuty);
	//	if (inst != null && inst.length == 2) {
		//	System.out.println("Instrument type:-" + instId + ":" + inst[1]);
			//instId = inst[0];
		//}


		String exemption = form.getHdnExAmount();
		System.out.println("Exemption Type:-" + exemption);
		double marketValue = Double.parseDouble(form.getTotalMarketValue());
		//double baseValue = propertyDTO.getBiggestValue();                     //ask rishab
		System.out.println("marketValue:-" + marketValue);
		//System.out.println("baseValue:-" + baseValue);
		
		System.out.println(" CALLING MUNICIPAL BODY FUNCTIO0N FOR CALCULATING THE DATA");

		
			calculatedDuty = common.getMuncipalCalculatedDuty(deedId,instId,munBodyId,propertyTypeL1Id,
					marketValue,stampDuty);

		
		System.out.println("Duty :-" + calculatedDuty);
	} catch (Exception x) {
		System.out.println(x);
	}
	return calculatedDuty;
	}
	/**
	 * getRegistrationFee
	 * Returns registration fee of multiple properties
	 * @param 
	 * @author ROOPAM
	 * @return string
	 * @throws Exception 
	 *
	 */
	public double getRegistrationFee(RegCommonForm form) {
		double regFee=0.0 ;
		try {
			NumberFormat obj=new DecimalFormat("#0.00");
			IGRSCommon common = new IGRSCommon();
			/*System.out.println("@@@@@@@@" + instDTO.getHdnAmount() + ":"
					+ dutyDTO.getDeedId());*/
			int instId = form.getInstID();
			int deedId = form.getDeedID();
			String exemption = form.getHdnExAmount();                  //GOING BLANK CHECK WITH RISHAB
			System.out.println("Exemption Type:-" + exemption);
			double baseValue = Double.parseDouble(form.getTotalMarketValue());                                             //from confirmation screen
			System.out.println("baseValue:-" + baseValue);
			double duty =Double.parseDouble(form.getStampduty1());
			System.out.println("STAM DUTY:-" + duty);
				regFee = common.getRegistrstionFee(deedId, instId, null,duty,
						baseValue);
				
				System.out.println("Reg Calculated : "+regFee);
				
				if(form.getLabelAmountFlag()!=null){                                                 //flag from where? not decided.
		     		System.out.println("Reg flag"+form.getLabelAmountFlag());
		     	if(form.getLabelAmountFlag().equals(CommonConstant.REG_ALREADY_PAID))	{
					double regPaid=Double.parseDouble(form.getRegPaid());
					form.setRegPaid(obj.format(regPaid));
					System.out.println("Reg Already Paid : "+regPaid);
					regFee=regFee-regPaid;
					System.out.println("Reg Calculated after  Paid : "+regFee);
					
					}
		     	}
			
			System.out.println("Registration Fee :-" + regFee);
		} catch (Exception x) {
			System.out.println(x);
		}
		 
		return regFee;
	}
	/**
	 * getPropDetlsForDutyCalc
	 * for getting property details from db for duty calculation.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getPropDetlsForDutyCalc(String valuationId) throws Exception {
		
		String propArr[]=null;
		ArrayList list=commonBd.getPropDetlsForDutyCalc(valuationId);
		
		if(list.size()!=0){
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		propArr=str.split(",");
		}
		
		return propArr;
	}
	/**
	 * getPaymentTxnId
	 * for getting payment transaction id from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getPaymentTxnId(String appId) throws Exception {
		
		String paymentArr[]=new String[3];
		ArrayList list=commonBd.getPaymentTxnId(appId);
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		paymentArr=str.split(",");
		
		
		return paymentArr;
		
	}
	/**
	 * for inserting registration initiation E STAMP CODE details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertEStampCode(RegCommonForm form) {
		
		
		return commonBd.insertEStampCode(form);	
	}
	/**
	 * for updating registration txn status in db.
	 * @param RegCommonForm
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean updateTransactionStatus(RegCommonForm form) {
		return commonBd.updateTransactionStatus(form);	
	}
	/**
	 * getDeedInstId
	 * for getting deed and instrument ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getDeedInstId(String appId) throws Exception {
		
		ArrayList list=commonBd.getDeedInstId(appId);
		String[] newArray=new String[5];
		
		if(list != null && list.size()>0){
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		String[] array=str.split(",");
		int size=array.length;
		
		newArray[0]=array[0];                       //deed id
		newArray[1]=array[1];                       //instrument id
		
		/*String var="";
		for(int i=2;i<array.length-1;i++){
		var=var+array[i]+"-";
		}*/
		newArray[2]=getExemptionIDList(appId);      //exemptions
		
		
		
		newArray[3]=array[2];                       //adjudication id
		newArray[4]=array[3];                       //cancellation deed radio
		
		return newArray;	
		}
		else
		{
			return null;
		}
	}
	/**
	 * for fetching the adjudication ID from db
	 * @param String
	 * @return String
	 * @author SHREERAJ
	 */
	public String getAdjFlag(String regID) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getAdjFlag(regID);
	}
	/**
	 * for inserting registration initiation stamp duties details in db.
	 * @param String[]
	 * @return boolean
	 * @author ROOPAM
	 */
	public boolean insertStampDuties(RegCommonForm form ,String userID) {
		return commonBd.insertStampDuties(form,userID);	
	}
	/**
	 * for getting country name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getCountryName(String countryId,String languageLocale) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getCountryName(countryId,languageLocale);
	}
	/**
	 * for getting state name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getStateName(String stateId,String languageLocale) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getStateName(stateId,languageLocale);
	}
	/**
	 * for getting district name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getDistrictName(String distId,String languageLocale) throws Exception {
		//RegCommonDAO dao = new RegCommonDAO();
		return commonBd.getDistrictName(distId,languageLocale);
	}
	/**
	 * getDeedInstId
	 * for getting deed and instrument ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getGuildingFloorDetails(String valId) throws Exception {
		return commonBd.getGuildingFloorDetails(valId);	
	}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getDutyDetlsForConfirmation(String appId) throws Exception {
		
		String dutyListArr[]=null;
		ArrayList list=commonBd.getDutyDetlsForConfirmation(appId);
		String dutyList;
		
		if(list!=null && list.size()==1){
			//for(int i=0;i<1;i++){
		dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		dutyListArr=dutyList.split(",");
			//}
		}
		return dutyListArr;
	}
	/**
	 * for getting deed name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getDeedName(String deedId,String languageLocale) throws Exception {
		return commonBd.getDeedName(deedId,languageLocale);
	}
	/**
	 * for getting instrument name from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getInstrumentName(String instId,String languageLocale) throws Exception {
		return commonBd.getInstrumentName(instId,languageLocale);
	}
	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getExemptionList(String exemptions)throws Exception {
		
		if(exemptions!=null && !exemptions.equalsIgnoreCase("")){
		String[] exempArray=exemptions.split("-");
	    ArrayList exemp = new ArrayList();
		if (exempArray != null && exempArray.length > 0) {
			for (int i = 0; i < exempArray.length; i++) {
				ExemptionDTO dto = new ExemptionDTO();
				dto.setExemType(commonBd.getExemptionName(exempArray[i]));
				exemp.add(dto);

			}
			return exemp;
		}else{
			return null;
		}
		}else{
			return null;
		}
		
	}
	/**
	 * getAdjudicationStatus
	 * for getting adjudication id and status from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getAdjudicationStatus(String adjuId) throws Exception {
		
		String[] strArray=null;
		ArrayList list=commonBd.getAdjudicationStatus(adjuId);
		if(list!=null && !(list.size()<1)){
			String str=list.toString();
			str=str.substring(2, str.length()-2);
			strArray=str.split(",");
		}
		return strArray;
	}
	/**
	 * for updating reg init id corresponding to adjudication id in database
	 * @param String regInitId,String adjuId
	 * @param boolean
	 *
	 */
	public boolean updateAdjudicationRecords(String regInitId,String adjuId, String userId){
		return commonBd.updateAdjudicationRecords(regInitId,adjuId,userId);
	}
	/**
	 * getPropertyIdMarketValAdju
	 * for getting PROPERTY ID AND MARKET VALUE details FOR ADJUDICATED APPLICATIONS from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getPropertyIdMarketValAdju(String appId) throws Exception {
		
		return commonBd.getPropertyIdMarketValAdju(appId);
	}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getAdjudicatedDutyDetls(String appId) throws Exception {
		
		String dutyListArr[]=null;
		ArrayList list=commonBd.getAdjudicatedDutyDetls(appId);
		
		if(list!=null && list.size()==1){
		String dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		dutyListArr=dutyList.split(",");
		}
		return dutyListArr;
	}
	//by ankita
	public ArrayList getEstampDet(String tempId) throws Exception{

        // TODO Auto-generated method stub

        return commonBd.getEstampDet( tempId);

}
	/**
	 * for getting applicant district id from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getApplicantDistrict(String appId) throws Exception {
		
		String distId=commonBd.getApplicantDistrict(appId);
		
		if(distId.length()==1){
			distId="0"+distId;
		}
		
		return distId;
	}
	/**
	 * for getting e stamp purpose from db.
	 * @param 
	 * @return String
	 * @author ROOPAM
	 */
	public String getEStampPurpose(String appId) throws Exception {
		return commonBd.getEStampPurpose(appId);
	}
	/**
	 * for updating updating e stamp purpose of registration in database
	 * @param String regInitId,String purpose
	 * @param boolean
	 *
	 */
	public boolean updateEStampPurpose(String regInitId, String purpose){
		return commonBd.updateEStampPurpose(regInitId,purpose);
	}
	/**
	 * getAllValuationIdsExchangeDeed
	 * for getting ALL VALUATION IDS from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getAllValuationIdsExchangeDeed(String finalValId) throws Exception {
		
		//String[] strArray=null;
		ArrayList list=commonBd.getAllValuationIdsExchangeDeed(finalValId);
		/*if(list!=null && list.size()>0)
		{
			strArray=new String[list.size()];
		for(int i=0;i<list.size();i++)
		{
		ArrayList row_list=(ArrayList)list.get(i);
		String str=row_list.toString();
		str=str.substring(1, str.length()-1);
		strArray[i]=str;
		}
		
		}*/
		return list;
	}
	
	/**
	 * getAllValuationIdsExchangeDeed
	 * for getting ALL VALUATION IDS from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getAllPropDetlsExchangeDeed(String valId) throws Exception {
		
		String[] strArray=null;
		ArrayList list=commonBd.getAllPropDetlsExchangeDeed(valId);
		if(list!=null && list.size()>0)
		{
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		strArray=str.split(",");
		}
		return strArray;
	}
	/**
	 * getExchangeDeedDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getExchangeDeedDutyDetls(String appId) throws Exception {
		
		//String refValId=getRefValIdExchngDeed(appId);        //method for getting reference valuation id in case of exchange deed
		
		ArrayList list=commonBd.getExchangeDeedDutyDetls(appId);
		
		if(list!=null && list.size()==1){
		String dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		String dutyListArr[]=dutyList.split(",");
		return dutyListArr;
		}else{
			return null;
		}
	}
	/**
	 * getRefValIdExchngDeed
	 * for getting reference valuation id in case of exchange deed
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getRefValIdExchngDeed(String appId) throws Exception {
		
		
		
		return commonBd.getRefValIdExchngDeed(appId);
		
	}
	/**
	 * getExchangeDeedDutyDetls
	 * for getting final market value of exchange deed from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getExchangeDeedFinalMV(String appId) throws Exception {
		
		return commonBd.getExchangeDeedFinalMV(appId);
		
	}
	/**
	 * getSubClauseListNonBuilding
	 * for getting sub clause list non building from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListNonBuilding(String valId) throws Exception {
		
		ArrayList finalList=new ArrayList();
		ArrayList list=commonBd.getSubClauseListNonBuilding(valId);
		ArrayList row_list=new ArrayList();
		String str="";
		String finalString="";
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
				
				row_list=(ArrayList)list.get(i);
				str=row_list.toString();
				str=str.substring(1, str.length()-1);
				
				finalString=finalString+"-"+str;
				
				
			}
	
			finalList=getSubClauseList(finalString);
		}
		
		
		
		return finalList;

	}
	/**
	 * @param String[]
	 * @return ArrayList
	 */
	public ArrayList getSubClauseList(String subClause)throws Exception {
		
		String[] subClauseArray=subClause.split("-");
	    ArrayList subClauseList = new ArrayList();
		if (subClauseArray != null && subClauseArray.length > 0) {
			for (int i = 0; i < subClauseArray.length; i++) {
				CommonDTO dto = new CommonDTO();
				dto.setName(commonBd.getSubClauseName(subClauseArray[i].trim()));
				subClauseList.add(dto);

			}
		}
		return subClauseList;
	}
	/**
	 * getSubClauseListNonBuilding
	 * for getting sub clause list non building from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public ArrayList getSubClauseListBuilding(String valId, String floorId) throws Exception {
		
		ArrayList finalList=new ArrayList();
		ArrayList list=commonBd.getSubClauseListBuilding(valId,floorId);
		ArrayList row_list=new ArrayList();
		String str="";
		String finalString="";
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
				
				row_list=(ArrayList)list.get(i);
				str=row_list.toString();
				str=str.substring(1, str.length()-1);
				
				finalString=finalString+"-"+str;
				
				
			}
	
			finalList=getSubClauseList(finalString);
		}
		
		
		return finalList;

	}
	
	
	//added by shruti
	public String getDeedId(int duty_txn_id) {
		return commonBd.getDeedId(duty_txn_id);
	}
	public String getExempId(int duty_txn_id) {
		
		
		ArrayList list=commonBd.getExempId(duty_txn_id);
		ArrayList rowList;
		/*if(list!=null && list.size()>0){
			
		}*/
		
		String exemp="";
		String str;
		//ArrayList list=commonBd.getExemptionList(appId);
		
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
			
				rowList=(ArrayList)list.get(i);
				str=rowList.toString();
				str=str.substring(1, str.length()-1);
			
				exemp=exemp+str+"-";
			
			
			
			}
			
		}
		return exemp;
		
		
		
		
		
		//return "";
	}
	public String getInstId(int duty_txn_id) {
		return commonBd.getInstId(duty_txn_id);
	}
	/**
	 * insertDepositDeedApplicantAndBankDetails
	 * Returns boolean value in order to check the insertion.
	 * @param form
	 * @author Shruti
	 * @return boolean
	 * @throws Exception 
	 *
	 */
	public boolean insertDepositDeedApplicantAndBankDetails(RegCommonForm form) throws Exception {
		return commonBd.insertDepositDeedApplicantAndBankDetails(form);
	}
	public ArrayList getPropTypeL1List(String propId,String languageLocale) throws Exception {

        return commonBd.getPropTypeL1List(propId,languageLocale);

}
	/**
	 * getDutyDetls
	 * for getting duty details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getNonPropDutyDetls(String appId) throws Exception {
		
		ArrayList list=commonBd.getNonPropDutyDetls(appId);
		
		if(list!=null && list.size()==1){
		String dutyList=list.toString();
		dutyList=dutyList.substring(2, dutyList.length()-2);
		String dutyListArr[]=dutyList.split(",");
		
		return dutyListArr;
		}else{
			return null;
		}
	}
	//end of code by shruti

	
	//FOLLOWING ADDED BY ROOPAM ON OR AFTER 25TH APRIL 2013
	/**
	 * for getting all Categories from DB.
	 * @return ArrayList
	 * @author Roopam
	 */
	public ArrayList getCategoryList(String languageLocale) {
		return commonBd.getCategoryList(languageLocale);
	}
	/**
	 * for getting category name from db.
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String getCategoryName(String id,String languageLocale) throws Exception {
		return commonBd.getCategoryName(id,languageLocale);
	}
	/**
	 * getPropertyDetailsFromValuation
	 * for getting property details from valuation tables.
	 * @param String
	 * @return String[]
	 * @author ROOPAM
	 */
	public String[] getPropertyDetailsFromValuation(String valId) throws Exception {
		
		ArrayList list=commonBd.getPropertyDetailsFromValuation(valId);
		
		if(list!=null && list.size()>0){
			
			String str=list.toString();
			str=str.substring(2, str.length()-2);
			String strArr[]=str.split(",");
			return strArr;
			
		}else{
			return null;
		}
		
		
		

	}
	/**
	 * getExemptionList
	 * for getting exemption ids from db
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String getExemptionIDList(String appId) throws Exception {
		
		String exemp="";
		ArrayList list=commonBd.getExemptionList(appId);
		ArrayList rowList;
		String str;
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
			
				rowList=(ArrayList)list.get(i);
				str=rowList.toString();
				str=str.substring(1, str.length()-1);
			
				exemp=exemp+str+"-";
			
			
			
			}
			
		}
		return exemp;

	}
	/**
	 * getPropDetlsForDisplay
	 * for getting PROPERTY details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public CustomArrayList getPropDetlsForDisplay(String propId) throws Exception {
		return commonBd.getPropDetlsForDisplay(propId);

	}
	/**
	 * getPropKhasraDetlsForDisplay
	 * for getting PROPERTY KHASRA details from db.
	 * @param String
	 * @return ArrayList
	 * @author ROOPAM
	 */
	public String[] getPropKhasraDetlsForDisplay(String propId) throws Exception {
		
		ArrayList list=commonBd.getPropKhasraDetlsForDisplay(propId);
		ArrayList rowList;
		//ArrayList mainList=new ArrayList();
		String khasraNum="";
		String khasraArea="";
		String lagaan="";
		String rinPustika="";
		String[] finalArr=new String[4];
		String str;
		String[] strArr;
		
		if(list!=null && list.size()>0){
			
			for(int i=0;i<list.size();i++){
			
				rowList=(ArrayList)list.get(i);
				str=rowList.toString();
				str=str.substring(1, str.length()-1);
				strArr=str.split(",");
				if(i==0){
					khasraNum =strArr[0]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
					khasraArea=strArr[1]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
					lagaan    =strArr[2]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
					rinPustika=strArr[3]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
				}else{
				khasraNum =khasraNum+strArr[0]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
				khasraArea=khasraArea+strArr[1]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
				lagaan    =lagaan+strArr[2]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
				rinPustika=rinPustika+strArr[3]+RegInitConstant.SPLIT_CONSTANT_KHASRA;
				}
			}
			finalArr[0]=khasraNum;
			finalArr[1]=khasraArea;
			finalArr[2]=lagaan;
			finalArr[3]=rinPustika;
			
			return finalArr;	
		}else{
		return null;
		}
	}
	/**
	 * convertDOB
	 * for convert dob from db into user readable form
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String convertDOB(String date) throws Exception {
		
		//String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
        Date d1 = date1.parse(date);
        String formatDate = date2.format(d1);

		System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
					
		
	}
	/**
	 * convertDOB
	 * for convert dob from db into user readable form
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String convertDOB(Object date) throws Exception {
		
		//String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MMM/yyyy");
        Date d1 = date1.parse(date.toString());
        String formatDate = date2.format(d1);

		//System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
					
		
	}
	/**
	 * convertDOB2
	 * for convert dob from db into user readable form
	 * @param String
	 * @return String
	 * @author ROOPAM
	 */
	public String convertDOB2(Object date) throws Exception {
		
		//String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
        Date d1 = date1.parse(date.toString());
        String formatDate = date2.format(d1);

		System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
					
		
	}
public String convertDOBfromDb(String date) throws Exception {
		
		//String sysdate=commonBd.getCurrDateTime();
		
		//String transDate1=paymentBD.gettranDate(eForm);
        SimpleDateFormat date1 = new SimpleDateFormat ("MM/dd/yyyy");
       // SimpleDateFormat date2 = new SimpleDateFormat ("dd/MMM/yyyy");
        SimpleDateFormat date2 = new SimpleDateFormat ("dd/MM/yyyy");
        Date d1 = date1.parse(date);
        String formatDate = date2.format(d1);

		System.out.println("formatted date=----->"+formatDate);
		
		
		
		return formatDate;
					
		
	}
public String[] parseStringDatefromDB(String date) throws Exception {
	
	
    SimpleDateFormat date1 = new SimpleDateFormat ("MM/dd/yyyy");
    Date d1 = date1.parse(date);
    
    SimpleDateFormat day = new SimpleDateFormat ("dd");
    SimpleDateFormat month = new SimpleDateFormat ("MM");
    SimpleDateFormat year = new SimpleDateFormat ("yyyy");
    //String formatDate = date2.format(d1);

	//System.out.println("formatted date=----->"+formatDate);
	String[] finalDate=new String[3];
	finalDate[0]=day.format(d1);
	finalDate[1]=month.format(d1);
	finalDate[2]=year.format(d1);
	
	
	return finalDate;
				
	
}
public String[] parseStringDOBfromDB(String date) throws Exception {
	
	
    SimpleDateFormat date1 = new SimpleDateFormat ("dd/MMM/yyyy");
    //SimpleDateFormat date2 = new SimpleDateFormat ("dd/MMM/yyyy");
    Date d1 = date1.parse(date);
    
    SimpleDateFormat day = new SimpleDateFormat ("dd");
    SimpleDateFormat month = new SimpleDateFormat ("MM");
    SimpleDateFormat year = new SimpleDateFormat ("yyyy");
    //String formatDate = date2.format(d1);

	//System.out.println("formatted date=----->"+formatDate);
	String[] finalDate=new String[3];
	finalDate[0]=day.format(d1);
	finalDate[1]=month.format(d1);
	finalDate[2]=year.format(d1);
	
	
	return finalDate;
				
	
}
public String convertCalenderDate(String date) throws Exception {
	
	//String sysdate=commonBd.getCurrDateTime();
	
	//String transDate1=paymentBD.gettranDate(eForm);
    SimpleDateFormat date1 = new SimpleDateFormat ("dd/MM/yyyy");
    SimpleDateFormat date2 = new SimpleDateFormat ("dd/MMM/yyyy");
    Date d1 = date1.parse(date);
    String formatDate = date2.format(d1);

	System.out.println("formatted date=----->"+formatDate);
	
	
	
	return formatDate;
				
	
}
/**
 * for getting all OCCUPATION list
 * @return ArrayList
 */
public ArrayList getOccupationList(String languageLocale) {
	return commonBd.getOccupationList(languageLocale);
}
/**
 * for getting OCCUPATION name from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getOccupationName(String id,String languageLocale) throws Exception {
	return commonBd.getOccupationName(id,languageLocale);
}
/**
 * for getting applicant share from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getApplicantShare(String id) throws Exception {
	return commonBd.getApplicantShare(id);
}

/**
 * getPropertyPartyDetailsForPDF
 * 
 * @param String regTxnId
 * @return HashMap
 * @author ROOPAM
 */
public HashMap getPropertyPartyDetailsForPDF(String regTxnId,int deedSet,String languageLocale) throws Exception {
	
	HashMap map=new HashMap();
	RegCompletDTO dto;
	
	try{
	ArrayList propertyIdList=getPropertyIdMarketVal(regTxnId);
	String[] propIdArr=new String[propertyIdList.size()];
	for(int m=0;m<propertyIdList.size();m++){
		
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)propertyIdList.get(m);
		String propIds=row_list.toString();
		propIds=propIds.substring(1, propIds.length()-1);
		String propId[]=propIds.split(",");
	    propIdArr[m]=propId[0].trim();
	
	}
	
	if(propIdArr!=null && propIdArr.length>0){
		
		for(int i=0;i<propIdArr.length;i++){
			dto=new RegCompletDTO();
			dto.setPartyListPdf(new ArrayList());
			
			if(propIdArr[i].trim().length()==15){
				dto.setSelectedPropId("0"+propIdArr[i].trim());
			}else{
		dto.setSelectedPropId(propIdArr[i].trim());
			}
			
			
			//valuation id
			{/*				
				//ROOPAM
			String valuationId=commonBd.getPropValuationId(regTxnId,propIdArr[i].trim());
			ArrayList considAmnt=getConsidAmtSysMV(valuationId);
		
		if(considAmnt!=null && considAmnt.size()>0){
		String considAmntDetails=considAmnt.toString().substring(2, (considAmnt.toString().length()-2));
		String considAmntDetsArray[]=considAmntDetails.split(",");
		dto.setConsiderAmt(Double.parseDouble(considAmntDetsArray[0]));
		dto.setSystemMV(Double.parseDouble(considAmntDetsArray[1]));
		}else{
			dto.setConsiderAmt(0);
			dto.setSystemMV(0);
		}
	
			
			*/}
			
			
			CustomArrayList propList = new CustomArrayList();
			propList=getPropDetlsForDisplay(propIdArr[i].trim());
			//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
			String propDetails=propList.toString();
			String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
			
			
			
			{/*	                   //ROOPAM
				
				ArrayList considAmnt=getConsidAmtSysMV(valuationId);
			
			if(considAmnt!=null && considAmnt.size()>0){
			String considAmntDetails=considAmnt.toString().substring(2, (considAmnt.toString().length()-2));
			String considAmntDetsArray[]=considAmntDetails.split(",");
			dto.setConsiderAmt(Double.parseDouble(considAmntDetsArray[0]));
			dto.setSystemMV(Double.parseDouble(considAmntDetsArray[1]));
			}else{
				dto.setConsiderAmt(0);
				dto.setSystemMV(0);
			}
		*/}
			dto.setPropertyTypeId(propDetsArray[13].trim());
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			dto.setPropertyTypeName(propDetsArray[14].trim());
			dto.setDistName(propDetsArray[1].trim());
			dto.setTehsilName(propDetsArray[3].trim());
			dto.setAreaTypeName(propDetsArray[5].trim());
			dto.setMunicipalBodyName(propDetsArray[12].trim());
			dto.setWardpatwarName(propDetsArray[7].trim());
			dto.setMohallaName(propDetsArray[10].trim());
			}else{
				
				dto.setPropertyTypeName(propDetsArray[43].trim());
				dto.setDistName(propDetsArray[37].trim());
				dto.setTehsilName(propDetsArray[38].trim());
				dto.setAreaTypeName(propDetsArray[39].trim());
				dto.setMunicipalBodyName(propDetsArray[42].trim());
				dto.setWardpatwarName(propDetsArray[40].trim());
				dto.setMohallaName(propDetsArray[41].trim());
				
				
			}
			if(!propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")){
			dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
			}else{
				dto.setTotalSqMeter(0);
			}
			dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
			if(dto.getPropertyTypeId().equalsIgnoreCase("1") ||// 1 for plot
					dto.getPropertyTypeId().equalsIgnoreCase("2"))// 2 for building
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setUnit(RegInitConstant.SQM_ENGLISH);
				}else{
					dto.setUnit(RegInitConstant.SQM_HINDI);
				}
			}
			else// else for agri land
			{
				
				String unit=propDetsArray[16].trim();
				/*if(unit.equalsIgnoreCase("2"))
				{
					dto.setUnit("SQM");
				}
				else if(unit.equalsIgnoreCase("3"))
				{*/
					dto.setUnit(getUnitName(unit,languageLocale));
				//}
				
			}
			//dto.setUnit("");
			//dto.setMunicipalDutyName("");
			//dto.setMunicipalBodyName(propDetsArray[12].trim());
			String wardstatus=propDetsArray[8].trim();//////
			
			if (wardstatus.equalsIgnoreCase("1"))
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				wardstatus=RegInitConstant.WARD_ENGLISH;
				}else{
					wardstatus=RegInitConstant.WARD_HINDI;	
				}
			}
			else
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				wardstatus=RegInitConstant.PATWARI_ENGLISH;
				}else{
					wardstatus=RegInitConstant.PATWARI_HINDI;	
				}
			}
			dto.setPatwariStatus(wardstatus);
			//dto.setWardpatwarName(propDetsArray[7].trim());
			//dto.setMohallaName(propDetsArray[10].trim());
			//no. of floors.
			
			if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null"))
			dto.setVikasId("-");
			else
			dto.setVikasId(propDetsArray[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setRicircle(propDetsArray[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
			dto.setLayoutdet("-");
			else
			dto.setLayoutdet(propDetsArray[19].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
			dto.setSheetnum("-");	
			else
			dto.setSheetnum(propDetsArray[20].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
			dto.setPlotnum("-");
			else
			dto.setPlotnum(propDetsArray[21].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setPropAddress(propDetsArray[22].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setNorth(propDetsArray[23].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setSouth(propDetsArray[24].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setEast(propDetsArray[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setWest(propDetsArray[26].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
				dto.setPropLandmark("-");
				else
				dto.setPropLandmark(propDetsArray[27].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			
			/*if(	!dto.getPropertyTypeName().equalsIgnoreCase("building"))
			{
				
				dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
				
			}*/
			//BELOW CODE FOR UPLOADS
			//28
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			if(propDetsArray[28].trim().equalsIgnoreCase("") || propDetsArray[28].trim().equalsIgnoreCase("null")){
				dto.setPropImage1DocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropImage1DocumentName(RegInitConstant.YES_ENGLISH);}
			if(propDetsArray[29].trim().equalsIgnoreCase("") || propDetsArray[29].trim().equalsIgnoreCase("null")){
				dto.setPropImage2DocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropImage2DocumentName(RegInitConstant.YES_ENGLISH);}
			if(propDetsArray[30].trim().equalsIgnoreCase("") || propDetsArray[30].trim().equalsIgnoreCase("null")){
				dto.setPropImage3DocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropImage3DocumentName(RegInitConstant.YES_ENGLISH);}
			if(propDetsArray[31].trim().equalsIgnoreCase("") || propDetsArray[31].trim().equalsIgnoreCase("null")){
				dto.setPropMapDocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropMapDocumentName(RegInitConstant.YES_ENGLISH);}
			}else{
				

				if(propDetsArray[28].trim().equalsIgnoreCase("") || propDetsArray[28].trim().equalsIgnoreCase("null")){
					dto.setPropImage1DocumentName(RegInitConstant.NO_HINDI);}
	          		else{
	          			dto.setPropImage1DocumentName(RegInitConstant.YES_HINDI);}
				if(propDetsArray[29].trim().equalsIgnoreCase("") || propDetsArray[29].trim().equalsIgnoreCase("null")){
					dto.setPropImage2DocumentName(RegInitConstant.NO_HINDI);}
	          		else{
	          			dto.setPropImage2DocumentName(RegInitConstant.YES_HINDI);}
				if(propDetsArray[30].trim().equalsIgnoreCase("") || propDetsArray[30].trim().equalsIgnoreCase("null")){
					dto.setPropImage3DocumentName(RegInitConstant.NO_HINDI);}
	          		else{
	          			dto.setPropImage3DocumentName(RegInitConstant.YES_HINDI);}
				if(propDetsArray[31].trim().equalsIgnoreCase("") || propDetsArray[31].trim().equalsIgnoreCase("null")){
					dto.setPropMapDocumentName(RegInitConstant.NO_HINDI);}
	          		else{
	          			dto.setPropMapDocumentName(RegInitConstant.YES_HINDI);}
				
				
			}
			
			
			if(	dto.getPropertyTypeId().equalsIgnoreCase("3"))// 3 for agri land
			{
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
					
			if(propDetsArray[32].trim().equalsIgnoreCase("") || propDetsArray[32].trim().equalsIgnoreCase("null")){
				dto.setPropRinDocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropRinDocumentName(RegInitConstant.YES_ENGLISH);}
			if(propDetsArray[33].trim().equalsIgnoreCase("") || propDetsArray[33].trim().equalsIgnoreCase("null")){
				dto.setPropKhasraDocumentName(RegInitConstant.NO_ENGLISH);}
          		else{
          			dto.setPropKhasraDocumentName(RegInitConstant.YES_ENGLISH);}
			
				}else{
					
					if(propDetsArray[32].trim().equalsIgnoreCase("") || propDetsArray[32].trim().equalsIgnoreCase("null")){
						dto.setPropRinDocumentName(RegInitConstant.NO_HINDI);}
		          		else{
		          			dto.setPropRinDocumentName(RegInitConstant.YES_HINDI);}
					if(propDetsArray[33].trim().equalsIgnoreCase("") || propDetsArray[33].trim().equalsIgnoreCase("null")){
						dto.setPropKhasraDocumentName(RegInitConstant.NO_HINDI);}
		          		else{
		          			dto.setPropKhasraDocumentName(RegInitConstant.YES_HINDI);}
					
					
				}
			}
			
			dto.setMarketValue(propDetsArray[34].trim());
			//dto.setPropTypeL1Id(propDetsArray[35].trim());
			//dto.setPropTypeL2Id(propDetsArray[36].trim());
			
			String[] guidelineArr={propDetsArray[0].trim(),propDetsArray[2].trim(),propDetsArray[6].trim(),propDetsArray[9].trim(),
					               propDetsArray[13].trim(),propDetsArray[35].trim(),propDetsArray[36].trim()};
			
			String guidelineRate=getGuidelineRate(guidelineArr);
			
			if(guidelineRate!=null && !guidelineRate.equalsIgnoreCase("") && !guidelineRate.equalsIgnoreCase("null"))
			{
			dto.setGuidelineValue(guidelineRate);
			}else{
				dto.setGuidelineValue("0");
			}
			//34 MARKET VALUE
			//35 L1
			//36 L2
			
			//dto.setPropImage1DocumentName("ANGLE 1.JPG");
			//dto.setPropImage1FilePath(propDetsArray[28].trim());
			
			//dto.setPropImage2DocumentName("ANGLE 2.JPG");
			//dto.setPropImage2FilePath(propDetsArray[29].trim());
			
			//dto.setPropImage3DocumentName("ANGLE 3.JPG");
			//dto.setPropImage3FilePath(propDetsArray[30].trim());
			
			//dto.setPropMapDocumentName("MAP.JPG");
			//dto.setPropMapFilePath(propDetsArray[31].trim());
			
			/*if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
			{
			dto.setPropRinDocumentName("RIN.JPG");
			dto.setPropRinFilePath(propDetsArray[32].trim());
			
			dto.setPropKhasraDocumentName("KHASRA.JPG");
			dto.setPropKhasraFilePath(propDetsArray[33].trim());
			}*/
			
			
			
			//FOR GETTING PROPERTY KHASRA DETAILS
			String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propIdArr[i].trim());
			
			
			//following code for inserting khasra detls into map
			if(otherPropDetsArray!=null){
			String[] khasraNum=otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] khasraArea=otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] lagaan=otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] rinPustika=otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			
			
			
			int length=khasraNum.length;
			CommonDTO objDto;
			
			for(int j=0;j<length;j++){
				objDto=new CommonDTO();
				if(khasraNum[j].equalsIgnoreCase("null"))
				{
					objDto.setKhasraNum("-");
					objDto.setKhasraArea("-");
					objDto.setLagaan("-");
					objDto.setRinPustika("-");
				}else{
				objDto.setKhasraNum(khasraNum[j]);
				objDto.setKhasraArea(khasraArea[j]);
				objDto.setLagaan(lagaan[j]);
				objDto.setRinPustika(rinPustika[j]);
				}
				
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			}else{
				CommonDTO objDto=new CommonDTO();
				objDto.setKhasraNum("-");
				objDto.setKhasraArea("-");
				objDto.setLagaan("-");
				objDto.setRinPustika("-");
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			
			
			if(deedSet==1)
			
			{	
				
			String valuationId=commonBd.getPropValuationId(regTxnId,propIdArr[i].trim());
			ArrayList considAmnt=getConsidAmtSysMV(valuationId);
		
		if(considAmnt!=null && considAmnt.size()>0){
		String considAmntDetails=considAmnt.toString().substring(2, (considAmnt.toString().length()-2));
		String considAmntDetsArray[]=considAmntDetails.split(",");
		dto.setConsiderAmt(Double.parseDouble(considAmntDetsArray[0]));
		dto.setSystemMV(Double.parseDouble(considAmntDetsArray[1]));
		}else{
			dto.setConsiderAmt(0);
			dto.setSystemMV(0);
		}
	
			
				
				
			if(	!dto.getPropertyTypeId().equalsIgnoreCase("2"))// 2 for building
			{
				
				dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
				
			}
			HashMap buildingMap=new HashMap();
			if(propDetsArray[14].trim().equalsIgnoreCase("2")){// 2 for building
			
				
				//following code for getting building floor details
				ArrayList buildingList=getGuildingFloorDetails(valuationId);
				if(buildingList.size()>0){
					
					dto.setFloorCount(buildingList.size());
					RegCompletDTO dto1;
					for(int j=0;j<buildingList.size();j++){
						dto1=new RegCompletDTO();
						ArrayList row_list=(ArrayList)buildingList.get(j);
						String str=row_list.toString();
						str=str.substring(1,str.length()-1);
						String[] strArray=str.split(",");
						dto1.setUsageBuilding(strArray[7]);
						dto1.setCeilingType(strArray[8]);
						dto1.setTypeFloorDesc(strArray[9]);
						dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
						dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
						dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
						dto1.setConstructedArea(strArray[6]);
						
						dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
						
						dto.getMapBuilding().put(strArray[0], dto1);
						
						
					}
					
					
					
				}
				
				
				
				
			}else{
				dto.setFloorCount(0);
			}
		}else{
			
				if(propDetsArray[14].trim().equalsIgnoreCase("2")){// 2 for building
			
				
				//following code for getting building floor details
				ArrayList buildingList=getBuildingFloorDetailsTitleDeed(propIdArr[i].trim());
				if(buildingList.size()>0){
					
					dto.setFloorCount(buildingList.size());
					
					RegCompletDTO dto1;
					
					for(int j=0;j<buildingList.size();j++){
						dto1=new RegCompletDTO();
						ArrayList row_list=(ArrayList)buildingList.get(j);
						String str=row_list.toString();
						str=str.substring(1,str.length()-1);
						String[] strArray=str.split(",");
						dto1.setUsageBuilding(strArray[7]);
						dto1.setCeilingType(strArray[8]);
						dto1.setTypeFloorDesc(strArray[9]);
						dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
						dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
						dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
						dto1.setConstructedArea(strArray[6]);
						
						//dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
						
						dto.getMapBuilding().put(strArray[0], dto1);
						
						
					}
					
					
					
				}
				
				
				
				
			}else{
				dto.setFloorCount(0);
			}
			
			
			
			
		}
			
			//BELOW CODE FOR GETTING LIST OF PARTY TXN IDS CORRESPONDING TO CURRENT PROPERTY ID IN LOOP
			
			String[] partyArr=getPartyTxnIdList(propIdArr[i].trim());
		
		if(partyArr!=null && partyArr.length>0){
			
			
			
			
			for(int j=0;j<partyArr.length;j++){
				
				ArrayList list=commonBd.getPartyDetailsPdf(partyArr[j]);
				RegCommonDTO mapDto =new RegCommonDTO();
				
			if(list!=null && list.size()>0){
				
			//	for(int k=0;k<list.size();k++)
			//	{
				//ArrayList row_list=(ArrayList)list.get(k);
				String shareList=list.toString();
				shareList=shareList.substring(2, shareList.length()-2);
				
				String shareHolderArr[]=shareList.split(",");
				
				//code for insertion in map
		             
		             if(shareHolderArr[0].trim().length()==11){
		            	 shareHolderArr[0]="0"+shareHolderArr[0].trim();
		         		}
		         		else{
		         			shareHolderArr[0]=shareHolderArr[0].trim();
		         		}
		           
		             mapDto.setListAdoptedPartyTrns(shareHolderArr[1].trim());
		            
		             mapDto.setApplicantOrTransParty("Applicant");
		             mapDto.setPartyRoleTypeId(shareHolderArr[23].trim());
		             mapDto.setPartyTypeFlag("C");
		             
		            	 if(shareHolderArr[25].trim().equals("") || shareHolderArr[25].trim().equals("null"))
		            		mapDto.setRelationWithOwnerTrns("-");
		            	 else
		         	    mapDto.setRelationWithOwnerTrns(shareHolderArr[25].trim());
		            	 if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null")){
		            		 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		            		mapDto.setShareOfPropTrns(RegInitConstant.NOT_DECLARED_ENGLISH);
		            		 }else{
		            			 mapDto.setShareOfPropTrns(RegInitConstant.NOT_DECLARED_HINDI); 
		            		 }
		            		mapDto.setHdnDeclareShareCheck("N");
		            	 }
		            	 else{
		         	    mapDto.setShareOfPropTrns(shareHolderArr[26].trim());
		         	   mapDto.setHdnDeclareShareCheck("Y");
		            	 }
		            	 mapDto.setSelectedCountry(shareHolderArr[8].trim());
		            	 mapDto.setSelectedCountryName(getCountryName(shareHolderArr[8].trim(),languageLocale));
		            	 mapDto.setSelectedState(shareHolderArr[9].trim());
		            	 mapDto.setSelectedStateName(getStateName(shareHolderArr[9].trim(),languageLocale));
		            	 mapDto.setSelectedDistrict(shareHolderArr[10].trim());
		            	 mapDto.setSelectedDistrictName(getDistrictName(shareHolderArr[10].trim(),languageLocale));
		            	 mapDto.setOrgaddressTrns(shareHolderArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 if(shareHolderArr[14].trim().equals("") || shareHolderArr[14].trim().equals("null"))
		            		 mapDto.setMobnoTrns("-"); 
		            	 else
		            	     mapDto.setMobnoTrns(shareHolderArr[14].trim());
		            	 if(shareHolderArr[13].trim().equals("") || shareHolderArr[13].trim().equals("null"))
		            		 mapDto.setPhnoTrns("-");
		            	 else
		            	     mapDto.setPhnoTrns(shareHolderArr[13].trim());
		            	
		            	     mapDto.setRoleName(commonBd.getRoleName(shareHolderArr[27].trim(),languageLocale));//pdf not in hindi
		            	 mapDto.setPartyTypeTrns(shareHolderArr[27].trim());
		            	 
		            	 mapDto.setPartyTxnId(shareHolderArr[23].trim());
		            	 
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
		            	 //organization
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Organization");
		            	 mapDto.setOgrNameTrns(shareHolderArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setAuthPerNameTrns(shareHolderArr[19].trim());
		            	 mapDto.setIndAuthPersn(shareHolderArr[19].trim());
		            	/* mapDto.setOrgaddressTrns(shareHolderArr[11].trim());*/
		            	 mapDto.setFnameTrns("");
		           		 mapDto.setMnameTrns("");
		            	 mapDto.setLnameTrns("");
		            	// mapDto.setGendarTrns("-");
		            	 
		            	 
		            	// mapDto.setSelectedGender("-");
		            	 mapDto.setUserDOBTrns("");
		            	 mapDto.setAgeTrns("");
		               	// mapDto.setFatherNameTrns("-");
		            	 
		            	 mapDto.setMotherNameTrns("");
		           		 mapDto.setSpouseNameTrns("");
		              	 mapDto.setNationalityTrns("");
		            	 mapDto.setPostalCodeTrns("");
		           		 mapDto.setEmailIDTrns("");
		            	 mapDto.setSelectedPhotoId("");
		            	 mapDto.setSelectedPhotoIdName("-");
		            	 mapDto.setIdnoTrns("-");
		            	
		            	 //mapDto.setIndReligionTrns("");
		            	 mapDto.setIndCategoryTrns("");
		            	 mapDto.setIndDisabilityTrns("");
		            	 mapDto.setIndDisabilityDescTrns("");
		            	 mapDto.setIndPovertyLineTrns("");
		            	 mapDto.setIndMinorityTrns("");
		            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
		            	 
		            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
		            	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
		            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
		            		 mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
		            	 else
		            		 mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
		            	 }else{
		            		 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
			            		 mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
			            	 else
			            		 mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
		            	 }
		            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
		            	 if(shareHolderArr[47].trim()!=null && !shareHolderArr[47].trim().equalsIgnoreCase("") && !shareHolderArr[47].trim().equalsIgnoreCase("null")){
				              	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
				              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
				              	}else{
				              		mapDto.setRelationshipTrns(0);
					              	mapDto.setRelationshipNameTrns("");
				              	}
		            	 
		            	 mapDto.setAuthPerCountryNameTrns(getCountryName(shareHolderArr[48].trim(),languageLocale));
		            	 mapDto.setAuthPerStatenameNameTrns(getStateName(shareHolderArr[49].trim(),languageLocale));
		            	 mapDto.setAuthPerDistrictNameTrns(getDistrictName(shareHolderArr[50].trim(),languageLocale));
		            	 mapDto.setAuthPerAddressTrns(shareHolderArr[51].trim());
		             }
		             
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
		            	 //individual
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Individual");
		            	 
		            	 mapDto.setFnameTrns(shareHolderArr[2].trim());
		            	 if(shareHolderArr[3].trim().equals("") || shareHolderArr[3].trim().equals("null"))
		            		 mapDto.setMnameTrns("");
		            	 else
		            	     mapDto.setMnameTrns(shareHolderArr[3].trim());
		            	 mapDto.setLnameTrns(shareHolderArr[4].trim());
		            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
		            	 /*if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
		            		 mapDto.setSelectedGender("Male");
		            	 else
		            		 mapDto.setSelectedGender("Female");*/
		            	 if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
			            		 mapDto.setSelectedGender(RegInitConstant.MALE_ENGLISH);
			            	 else
			            		 mapDto.setSelectedGender(RegInitConstant.FEMALE_ENGLISH);
			            	 }else{
			            		 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
				            		 mapDto.setSelectedGender(RegInitConstant.MALE_HINDI);
				            	 else
				            		 mapDto.setSelectedGender(RegInitConstant.FEMALE_HINDI);
			            	 }
		            	/* if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setUserDOBTrns("-");
		            	 else
		            	     mapDto.setUserDOBTrns(convertDOBfromDb(shareHolderArr[6].trim()));*/
		            	 if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setAgeTrns("-");
		            	 else
		            	     mapDto.setAgeTrns(shareHolderArr[6].trim());
		            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
		            	 if(shareHolderArr[21].trim().equals("") || shareHolderArr[21].trim().equals("null"))
		            		 mapDto.setMotherNameTrns("-");
		            	 else
		            	     mapDto.setMotherNameTrns(shareHolderArr[21].trim());
		            	 
		            	 if(shareHolderArr[28].trim().equals("") || shareHolderArr[28].trim().equals("null"))
		            		 mapDto.setSpouseNameTrns("-");
		            	 else
		            	     mapDto.setSpouseNameTrns(shareHolderArr[28].trim());
		            	 mapDto.setNationalityTrns(shareHolderArr[7].trim());
		            	 
		            	 if(shareHolderArr[12].trim().equals("") || shareHolderArr[12].trim().equals("null"))
		            		 mapDto.setPostalCodeTrns("-");
		            	 else
		            	     mapDto.setPostalCodeTrns(shareHolderArr[12].trim());
		            	
		            	 if(shareHolderArr[15].trim().equals("") || shareHolderArr[15].trim().equals("null"))
		            		 mapDto.setEmailIDTrns("-");
		            	 else
		            	     mapDto.setEmailIDTrns(shareHolderArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setSelectedPhotoId(shareHolderArr[16].trim());
		            	 
		            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(shareHolderArr[16].trim(),languageLocale));               
		            	 mapDto.setIdnoTrns(shareHolderArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOgrNameTrns("-");
		            	 mapDto.setAuthPerNameTrns("-");
		            	 mapDto.setIndAuthPersn(shareHolderArr[2].trim()+" "+shareHolderArr[4].trim());
		            	 
		               	 
		               	 if(shareHolderArr[22].trim().equals("") || shareHolderArr[22].trim().equals("null"))
		               	 {
		               		mapDto.setIndCategoryTrns("-");
		               	 }
		               	 else
		               	 {
		            	    mapDto.setIndCategoryTrns(shareHolderArr[22].trim());
		               	 }
		               	 
		               	 mapDto.setSelectedCategoryName(getCategoryName(shareHolderArr[22].trim(),languageLocale));
		               	 
		               	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
		               	 { 	 
		               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
		               	 {
		               		mapDto.setIndDisabilityTrns("-");
		               	 }
		               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
		               	 {
		            	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_ENGLISH);
		            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
		            	    	mapDto.setIndDisabilityDescTrns("-");
		            	    }else{
		            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	    }
		               	 }
		               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
		               	{
		            	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_ENGLISH);
		               	}
		               	 
		               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
		               		mapDto.setIndPovertyLineTrns("-");}
		               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_ENGLISH);
		            	     }
		               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_ENGLISH);}
		               	
		               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
		               		mapDto.setIndMinorityTrns("-");}
		               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndMinorityTrns(RegInitConstant.YES_ENGLISH);
		            	     }
		               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndMinorityTrns(RegInitConstant.NO_ENGLISH);
		            	    }
		               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
		               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
		               	
		               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
		              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
		              		mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_ENGLISH);
		              		
		              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
		              			mapDto.setPermissionNoTrns("-");
		              		}else{
		              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              		}
		              		
		              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
		              		if(shareHolderArr[33].trim().equalsIgnoreCase("") || shareHolderArr[33].trim().equalsIgnoreCase("null")){
		              		mapDto.setDocumentNameTrns(RegInitConstant.NO_ENGLISH);}
		              		else{
		              			mapDto.setDocumentNameTrns(RegInitConstant.YES_ENGLISH);}
		              		
		              	}else{
		              		mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_ENGLISH);
		              	}
		             }else{
		             	 
		               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
		               	 {
		               		mapDto.setIndDisabilityTrns("-");
		               	 }
		               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
		               	 {
		            	    mapDto.setIndDisabilityTrns(RegInitConstant.YES_HINDI);
		            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
		            	    	mapDto.setIndDisabilityDescTrns("-");
		            	    }else{
		            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	    }
		               	 }
		               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
		               	{
		            	    mapDto.setIndDisabilityTrns(RegInitConstant.NO_HINDI);
		               	}
		               	 
		               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
		               		mapDto.setIndPovertyLineTrns("-");}
		               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndPovertyLineTrns(RegInitConstant.YES_HINDI);
		            	     }
		               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndPovertyLineTrns(RegInitConstant.NO_HINDI);}
		               	
		               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
		               		mapDto.setIndMinorityTrns("-");}
		               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndMinorityTrns(RegInitConstant.YES_HINDI);
		            	     }
		               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndMinorityTrns(RegInitConstant.NO_HINDI);
		            	    }
		               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
		               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
		               	
		               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
		              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
		              		mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.NO_HINDI);
		              		
		              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
		              			mapDto.setPermissionNoTrns("-");
		              		}else{
		              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              		}
		              		
		              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
		              		if(shareHolderArr[33].trim().equalsIgnoreCase("") || shareHolderArr[33].trim().equalsIgnoreCase("null")){
		              		mapDto.setDocumentNameTrns(RegInitConstant.NO_HINDI);}
		              		else{
		              			mapDto.setDocumentNameTrns(RegInitConstant.YES_HINDI);}
		              		
		              	}else{
		              		mapDto.setIndScheduleAreaTrnsDisplay(RegInitConstant.YES_HINDI);
		              	}
		             
		             }
		               	
		              	if(shareHolderArr[47].trim()!=null && !shareHolderArr[47].trim().equalsIgnoreCase("") && !shareHolderArr[47].trim().equalsIgnoreCase("null")){
		              	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
		              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
		              	}else{
		              		mapDto.setRelationshipTrns(0);
			              	mapDto.setRelationshipNameTrns("");
		              	}
		             }
		             //uploads
		             if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH))
		             {
		             if(shareHolderArr[37].trim().equalsIgnoreCase("") || shareHolderArr[37].trim().equalsIgnoreCase("null")){
		              		mapDto.setU2DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU2DocumentNameTrns("Yes");}
		              	if(shareHolderArr[38].trim().equalsIgnoreCase("") || shareHolderArr[38].trim().equalsIgnoreCase("null")){
		              		mapDto.setU3DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU3DocumentNameTrns("Yes");}
		              	if(shareHolderArr[39].trim().equalsIgnoreCase("") || shareHolderArr[39].trim().equalsIgnoreCase("null")){
		              		mapDto.setU4DocumentNameTrns("No");
		              		mapDto.setU7DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU4DocumentNameTrns("Yes");
		              			mapDto.setU7DocumentNameTrns("Yes");	
		              		}
		              	if(shareHolderArr[40].trim().equalsIgnoreCase("") || shareHolderArr[40].trim().equalsIgnoreCase("null")){
		              		mapDto.setU5DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU5DocumentNameTrns("Yes");}
		              	if(shareHolderArr[41].trim().equalsIgnoreCase("") || shareHolderArr[41].trim().equalsIgnoreCase("null")){
		              		mapDto.setU6DocumentNameTrns("No");
		              		mapDto.setU9DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU6DocumentNameTrns("Yes");
		              			mapDto.setU9DocumentNameTrns("Yes");	
		              		}
		              	if(shareHolderArr[42].trim().equalsIgnoreCase("") || shareHolderArr[42].trim().equalsIgnoreCase("null")){
		              		mapDto.setU8DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU8DocumentNameTrns("Yes");}
		              	if(shareHolderArr[43].trim().equalsIgnoreCase("") || shareHolderArr[43].trim().equalsIgnoreCase("null")){
		              		mapDto.setU10DocumentNameTrns("No");
		              		mapDto.setU11DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU10DocumentNameTrns(RegInitConstant.YES_ENGLISH);
		              			mapDto.setU11DocumentNameTrns(RegInitConstant.YES_ENGLISH);	
		              		}
			}else{

	             if(shareHolderArr[37].trim().equalsIgnoreCase("") || shareHolderArr[37].trim().equalsIgnoreCase("null")){
	              		mapDto.setU2DocumentNameTrns(RegInitConstant.NO_HINDI);}
	              		else{
	              			mapDto.setU2DocumentNameTrns(RegInitConstant.YES_HINDI);}
	              	if(shareHolderArr[38].trim().equalsIgnoreCase("") || shareHolderArr[38].trim().equalsIgnoreCase("null")){
	              		mapDto.setU3DocumentNameTrns(RegInitConstant.NO_HINDI);}
	              		else{
	              			mapDto.setU3DocumentNameTrns(RegInitConstant.YES_HINDI);}
	              	if(shareHolderArr[39].trim().equalsIgnoreCase("") || shareHolderArr[39].trim().equalsIgnoreCase("null")){
	              		mapDto.setU4DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		mapDto.setU7DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		
	              	}
	              		else{
	              			mapDto.setU4DocumentNameTrns(RegInitConstant.YES_HINDI);
	              			mapDto.setU7DocumentNameTrns(RegInitConstant.YES_HINDI);	
	              		}
	              	if(shareHolderArr[40].trim().equalsIgnoreCase("") || shareHolderArr[40].trim().equalsIgnoreCase("null")){
	              		mapDto.setU5DocumentNameTrns(RegInitConstant.NO_HINDI);}
	              		else{
	              			mapDto.setU5DocumentNameTrns(RegInitConstant.YES_HINDI);}
	              	if(shareHolderArr[41].trim().equalsIgnoreCase("") || shareHolderArr[41].trim().equalsIgnoreCase("null")){
	              		mapDto.setU6DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		mapDto.setU9DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		
	              	}
	              		else{
	              			mapDto.setU6DocumentNameTrns(RegInitConstant.YES_HINDI);
	              			mapDto.setU9DocumentNameTrns(RegInitConstant.YES_HINDI);	
	              		}
	              	if(shareHolderArr[42].trim().equalsIgnoreCase("") || shareHolderArr[42].trim().equalsIgnoreCase("null")){
	              		mapDto.setU8DocumentNameTrns(RegInitConstant.NO_HINDI);}
	              		else{
	              			mapDto.setU8DocumentNameTrns(RegInitConstant.YES_HINDI);}
	              	if(shareHolderArr[43].trim().equalsIgnoreCase("") || shareHolderArr[43].trim().equalsIgnoreCase("null")){
	              		mapDto.setU10DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		mapDto.setU11DocumentNameTrns(RegInitConstant.NO_HINDI);
	              		
	              	}
	              		else{
	              			mapDto.setU10DocumentNameTrns(RegInitConstant.YES_HINDI);
	              			mapDto.setU11DocumentNameTrns(RegInitConstant.YES_HINDI);	
	              		}
		
			}
		              	//mapDto.setU2DocumentNameTrns("PHOTO PROOF");
		              	//mapDto.setU2FilePathTrns(shareHolderArr[37].trim());         //37 PHOTO_PROOF_PATH
		              	//mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");
		              	//mapDto.setU3FilePathTrns(shareHolderArr[38].trim());			//38 NOT_AFFD_EXEC_PATH
		              	//mapDto.setU4DocumentNameTrns("EXECUTANT PHOT0");
		              	//mapDto.setU4FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
		              	//mapDto.setU7DocumentNameTrns("EXECUTANT PHOT0");
		              	//mapDto.setU7FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
		              	//mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY");
		              	//mapDto.setU5FilePathTrns(shareHolderArr[40].trim());			//40 NOT_AFFD_ATTR_PATH
		              	//mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");
		              	//mapDto.setU6FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
		              	//mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");
		              	//mapDto.setU9FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
		              	//mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");
		              	//mapDto.setU8FilePathTrns(shareHolderArr[42].trim());			//42 CLAIMNT_PHOTO_PATH
		              	//mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");
		              	//mapDto.setU10FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
		              	//mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");
		              	//mapDto.setU11FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
		              	
		              	mapDto.setSrOfficeNameTrns(shareHolderArr[44].trim());
		              	mapDto.setPoaRegNoTrns(shareHolderArr[45].trim());
		              	if(shareHolderArr[46].trim()==null || shareHolderArr[46].trim().equalsIgnoreCase("") || shareHolderArr[46].trim().equalsIgnoreCase("null")){
		              		
		              		mapDto.setDatePoaRegTrns("");
		              		}
		              		else{
		              			mapDto.setDatePoaRegTrns(convertDOB2(shareHolderArr[46].trim()));
		              		}
		              	
		              	//end of uploads
		              	
		             int roleId=Integer.parseInt(shareHolderArr[27].trim());
		             String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
		             
		             if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG) 
		             {
		             	
		             	String ownerId=shareHolderArr[29].trim();
		             	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
		             	String ownerDetls=ownerList.toString();
		             	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
		             	String ownerDetlsArr[]=ownerDetls.split(",");
		             	
		            	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
		            	 {
		              mapDto.setOwnerOgrNameTrns("-");
		              mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
		              }
		              else
		              {
		              mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
		            	}
		            	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f"))
		            	 mapDto.setOwnerGendarTrns("Female");
		            	 else
		                 mapDto.setOwnerGendarTrns("Male");	 
		            	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
		            	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
		            	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
		                   		mapDto.setOwnerEmailIDTrns("-");
		                   	 else
		                	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));
		            	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
		            	 
		            	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
		            	 mapDto.setOwnerProofNameTrns(getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
		             }
		              
		              
		             
		            
			//}
			
			}
			
			
			dto.getPartyListPdf().add(mapDto);
			
		}
		
		
		
		}else{
			throw new Exception();
		}
		
		map.put(dto.getSelectedPropId(), dto);
		
		
	}
	
	
	
	}else{
		throw new Exception();
	}
}catch(Exception e){
	e.printStackTrace();
}
	
	return map;
	
}
/**
 * getPartyTxnIdList
 * for getting party txn id list corresponding to property id given
 * @param String
 * @return String[]
 * @author ROOPAM
 */
public String[] getPartyTxnIdList(String propId) throws Exception {
	return commonBd.getPartyTxnIdList(propId);
}
/**
 * getPartyDetailsPdf
 * 
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPartyDetailsPdf(String partyId) throws Exception {
	//RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getPartyDetailsPdf(partyId);
}

/**
 * getGuidelineRate
 * for getting current guideline rate from db.
 * @param String[]
 * @return String
 * @author ROOPAM
 */
public String getGuidelineRate(String[] param) throws Exception {
	return commonBd.getGuidelineRate(param);
}
/**
 * getPropTypeL2List
 * for getting property type l2 list from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPropTypeL2List(String propL1Id,String languageLocale) throws Exception {

    return commonBd.getPropTypeL2List(propL1Id,languageLocale);

}
/**
 * getFloorType
 * for getting floor list from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getFloorType(String l1ID,String languageLocale) {
	ArrayList list = new ArrayList();

	try {
		ArrayList retList = new IGRSCommon().getFloor(l1ID);
		if (retList != null) {
			for (int i = 0; i < retList.size(); i++) {
				ArrayList lst = (ArrayList) retList.get(i);
				PropertyValuationDTO dto = new PropertyValuationDTO();
				dto.setTypeFloorID((String) lst.get(0));
				dto.setTypeFloorName((String) lst.get(1));
				if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setTypeFloorDesc((String) lst.get(2));
				dto.setHdnTypeFloor((String) lst.get(0) + "~"
						+ (String) lst.get(1) + "~" + (String) lst.get(2));
				}else{
					dto.setTypeFloorDesc((String) lst.get(3));
					dto.setHdnTypeFloor((String) lst.get(0) + "~"
							+ (String) lst.get(3) + "~" + (String) lst.get(3));
				}
				list.add(dto);
			}
		}
	} catch (Exception x) {
		x.printStackTrace();
	}
	return list;
}
/**
 * getUnitList
 * for getting UNIT LIST from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getUnitList(String propL1Id,String languageLocale) throws Exception {

    return commonBd.getUnitList(propL1Id,languageLocale);

}
/**
 * for getting property type name from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getPropertyTypeName(String id,String languageLocale) throws Exception {
	return commonBd.getPropertyTypeName(id,languageLocale);
}
/**
 * insertPropertyDetailsNonPropDeeds
 * for inserting property details of non property deeds in db.
 * @param RegCommonForm regInitForm, RegCompletDTO dto
 * @return boolean
 * @author ROOPAM
 */
public boolean insertPropertyDetailsNonPropDeeds(RegCommonForm regInitForm, RegCompletDTO dto, String status)throws
Exception  
{
	return commonBd.insertPropertyDetailsNonPropDeeds(regInitForm, dto,status);
}
/**
 * getPropertyListNonPropDeed
 * for getting PROPERTY ID list from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPropertyListNonPropDeed(String appId) throws Exception {
	
	//return commonBd.getPropertyListNonPropDeed(appId);
	

	ArrayList list=commonBd.getPropertyListNonPropDeed(appId);
	ArrayList finalList=new ArrayList();
	ArrayList row_list=new ArrayList();
	if(list != null && list.size()>0){
	for(int i=0;i<list.size();i++){
		
		row_list=(ArrayList)list.get(i);
		String row=row_list.toString();
		row=row.substring(1, row.length()-1);
		String rowArr[]=row.split(",");
		
		CommonDTO dto=new CommonDTO();
		
		if(rowArr[0].trim().length()==15){
			rowArr[0]="0"+rowArr[0].trim();
		}
		if(rowArr[0].trim().length()==15){
			dto.setId("0"+rowArr[0].trim());
		}else{
		dto.setId(rowArr[0].trim());
		}
		/*if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
			dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
		}
		else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
			dto.setName(rowArr[3].trim());
		}
		dto.setId(rowArr[4].trim());
		dto.setRoleId(rowArr[5].trim());*/
		finalList.add(dto);
	}
	return finalList;
	}
	else{
		return null;
	}
	
	
}
/**
 * getTransPartyDetsNonPropDeed
 * for getting transacting party details corresponding to a registration ID from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getTransPartyDetsNonPropDeed(String appId, int commonDeedFlag, String languageLocale, int deed, int inst) throws Exception {
	
	ArrayList list;
	
	if(commonDeedFlag==0){
	list=commonBd.getTransPartyDetsNonPropDeed(appId);
	}else{
		list=commonBd.getTransPartyDetsCommonDeed(appId);           // change for common deed	
	}
	ArrayList finalList=new ArrayList();
	ArrayList row_list=new ArrayList();
	if(list != null && list.size()>0){
	for(int i=0;i<list.size();i++){
		row_list=(ArrayList)list.get(i);
		
		String row=row_list.toString();
		row=row.substring(1, row.length()-1);
		String rowArr[]=row.split(",");
		
		CommonDTO dto=new CommonDTO();
		//dto.setRole(rowArr[0].trim());
		
		if(commonDeedFlag==0){
			dto.setRole(getRoleName(rowArr[5].trim(),languageLocale,deed,inst));
			}else{
				dto.setRole(rowArr[0].trim());           // change for common deed	
			}
		
		
		if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
			dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
		}
		else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
			dto.setName(rowArr[3].trim());
		}
		dto.setId(rowArr[4].trim());
		dto.setRoleId(rowArr[5].trim());
		finalList.add(dto);
	}
	return finalList;
	}
	else{
		return null;
	}
}
/**
 * getPropertyDetsForViewConfirmNonProp
 * for getting PROPERTY details from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public HashMap getPropertyDetsForViewConfirmNonProp(String appId, String propId, String languageLocale) throws Exception {
	
	HashMap propMap=new HashMap();
	
	//valuation id
	//String valuationId=commonBd.getPropValuationId(appId,propId);
	
	
	//details corresponding to valuation id
	CustomArrayList propList = new CustomArrayList();
	
	//propList=commonBd.getPropDetlsForDashboard(valuationId);
	//propList=commonBd.getPropDetlsForDashboard(propId);
	propList=getPropDetlsForDisplay(propId);
	//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
	String propDetails=propList.toString();
	String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
    
	
	
	
	RegCompletDTO dto=new RegCompletDTO();
	
	
	dto.setPropertyTypeId(propDetsArray[13].trim());
	
	if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
	dto.setPropertyTypeName(propDetsArray[14].trim());
	dto.setDistName(propDetsArray[1].trim());
	dto.setTehsilName(propDetsArray[3].trim());
	dto.setAreaTypeName(propDetsArray[5].trim());
	dto.setMunicipalBodyName(propDetsArray[12].trim());
	dto.setWardpatwarName(propDetsArray[7].trim());
	dto.setMohallaName(propDetsArray[10].trim());
	}else{
		dto.setPropertyTypeName(propDetsArray[43].trim());
		dto.setDistName(propDetsArray[37].trim());
		dto.setTehsilName(propDetsArray[38].trim());
		dto.setAreaTypeName(propDetsArray[39].trim());
		dto.setMunicipalBodyName(propDetsArray[42].trim());
		dto.setWardpatwarName(propDetsArray[40].trim());
		dto.setMohallaName(propDetsArray[41].trim());
	}
	
	//dto.setPropertyTypeName(propDetsArray[14].trim());
	//dto.setDistName(propDetsArray[1].trim());
	//dto.setTehsilName(propDetsArray[3].trim());
	//dto.setAreaTypeName(propDetsArray[5].trim());
	if(propDetsArray[15].trim()!=null && !propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")){
	dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
	}else{
		dto.setTotalSqMeter(0);
	}

	dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
	if(propDetsArray[13].trim().equalsIgnoreCase("1") ||   //1 for plot
			propDetsArray[13].trim().equalsIgnoreCase("2"))//2 for building
	{
		
		//dto.setUnit("SQM");
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			dto.setUnit(RegInitConstant.SQM_ENGLISH);
			}else{
				dto.setUnit(RegInitConstant.SQM_HINDI);
			}
	}
	else
	{
		dto.setUnit(getUnitName(propDetsArray[16].trim(),languageLocale));
		
		/*String unit=propDetsArray[16].trim();
		if(unit.equalsIgnoreCase("2"))
		{
			//dto.setUnit("SQM");
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setUnit(RegInitConstant.SQM_ENGLISH);
				}else{
					dto.setUnit(RegInitConstant.SQM_HINDI);
				}
		}
		else if(unit.equalsIgnoreCase("3"))
		{
			//dto.setUnit("HECTARE");
			if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
				dto.setUnit(RegInitConstant.HECTARE_ENGLISH);
				}else{
					dto.setUnit(RegInitConstant.HECTARE_HINDI);
				}
		}*/
		
	}
	
	//dto.setMunicipalBodyName(propDetsArray[12].trim());
	String wardstatus=propDetsArray[8].trim();//////
	
	if (wardstatus.equalsIgnoreCase("1"))
	{
		//wardstatus="Ward";
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			wardstatus=RegInitConstant.WARD_ENGLISH;
			}else{
				wardstatus=RegInitConstant.WARD_HINDI;
			}
	}
	else
	{
		//wardstatus="Patwari/Halka";
		if(languageLocale.equalsIgnoreCase(RegInitConstant.LANGUAGE_ENGLISH)){
			wardstatus=RegInitConstant.PATWARI_ENGLISH;
			}else{
				wardstatus=RegInitConstant.PATWARI_HINDI;
			}
	}
	dto.setPatwariStatus(wardstatus);
	//dto.setWardpatwarName(propDetsArray[7].trim());
	//dto.setMohallaName(propDetsArray[10].trim());
	//no. of floors.
	
	if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null"))
	dto.setVikasId("-");
	else
	dto.setVikasId(propDetsArray[17].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setRicircle(propDetsArray[18].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
	dto.setLayoutdet("-");
	else
	dto.setLayoutdet(propDetsArray[19].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	
	if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
	dto.setSheetnum("-");	
	else
	dto.setSheetnum(propDetsArray[20].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
	dto.setPlotnum("-");
	else
	dto.setPlotnum(propDetsArray[21].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setPropAddress(propDetsArray[22].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setNorth(propDetsArray[23].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setSouth(propDetsArray[24].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setEast(propDetsArray[25].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	dto.setWest(propDetsArray[26].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
	if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null")){
		dto.setPropLandmark("-");
	}
		else{
		dto.setPropLandmark(propDetsArray[27].trim().replace( RegInitConstant.SPLIT_CONSTANT,","));
		}
	
	
	//BELOW CODE FOR UPLOADS
	//28
	
	dto.setPropImage1DocumentName("ANGLE 1.JPG");
	dto.setPropImage1FilePath(propDetsArray[28].trim());
	if(propDetsArray[28].trim()!=null && !propDetsArray[28].trim().equalsIgnoreCase("") && !propDetsArray[28].trim().equalsIgnoreCase("null")){
		dto.setPropImage1DocContents(DMSUtility.getDocumentBytes(propDetsArray[28].trim()));
   		}else{
   			dto.setPropImage1DocContents(new byte[0]);

   		}
	dto.setPropImage1UploadType(propDetsArray[28].trim().substring(propDetsArray[28].trim().lastIndexOf(".")-2));
	
	dto.setPropImage2DocumentName("ANGLE 2.JPG");
	dto.setPropImage2FilePath(propDetsArray[29].trim());
	if(propDetsArray[29].trim()!=null && !propDetsArray[29].trim().equalsIgnoreCase("") && !propDetsArray[29].trim().equalsIgnoreCase("null")){
		dto.setPropImage2DocContents(DMSUtility.getDocumentBytes(propDetsArray[29].trim()));
   		}else{
   			dto.setPropImage2DocContents(new byte[0]);

   		}
	dto.setPropImage2UploadType(propDetsArray[29].trim().substring(propDetsArray[29].trim().lastIndexOf(".")-2));
	
	dto.setPropImage3DocumentName("ANGLE 3.JPG");
	dto.setPropImage3FilePath(propDetsArray[30].trim());
	if(propDetsArray[30].trim()!=null && !propDetsArray[30].trim().equalsIgnoreCase("") && !propDetsArray[30].trim().equalsIgnoreCase("null")){
		dto.setPropImage3DocContents(DMSUtility.getDocumentBytes(propDetsArray[30].trim()));
   		}else{
   			dto.setPropImage3DocContents(new byte[0]);

   		}
	dto.setPropImage3UploadType(propDetsArray[30].trim().substring(propDetsArray[30].trim().lastIndexOf(".")-2));
	
	dto.setPropMapDocumentName("MAP.JPG");
	dto.setPropMapFilePath(propDetsArray[31].trim());
	if(propDetsArray[31].trim()!=null && !propDetsArray[31].trim().equalsIgnoreCase("") && !propDetsArray[31].trim().equalsIgnoreCase("null")){
		dto.setPropMapDocContents(DMSUtility.getDocumentBytes(propDetsArray[31].trim()));
   		}else{
   			dto.setPropMapDocContents(new byte[0]);

   		}
	dto.setPropMapUploadType(propDetsArray[31].trim().substring(propDetsArray[31].trim().lastIndexOf(".")-2));
	
	if(	propDetsArray[13].trim().equalsIgnoreCase("3"))//3 FOR AGRI LAND
	{
	dto.setPropRinDocumentName("RIN.JPG");
	dto.setPropRinFilePath(propDetsArray[32].trim());
	if(propDetsArray[32].trim()!=null && !propDetsArray[32].trim().equalsIgnoreCase("") && !propDetsArray[32].trim().equalsIgnoreCase("null")){
		dto.setPropRinDocContents(DMSUtility.getDocumentBytes(propDetsArray[32].trim()));
   		}else{
   			dto.setPropRinDocContents(new byte[0]);

   		}
	dto.setPropRinUploadType(propDetsArray[32].trim().substring(propDetsArray[32].trim().lastIndexOf(".")-2));
	
	dto.setPropKhasraDocumentName("KHASRA.JPG");
	dto.setPropKhasraFilePath(propDetsArray[33].trim());
	if(propDetsArray[33].trim()!=null && !propDetsArray[33].trim().equalsIgnoreCase("") && !propDetsArray[33].trim().equalsIgnoreCase("null")){
		dto.setPropKhasraDocContents(DMSUtility.getDocumentBytes(propDetsArray[33].trim()));
   		}else{
   			dto.setPropKhasraDocContents(new byte[0]);

   		}
	dto.setPropKhasraUploadType(propDetsArray[33].trim().substring(propDetsArray[33].trim().lastIndexOf(".")-2));
	}
	
	/*if(	!dto.getPropertyTypeName().equalsIgnoreCase("building"))
	{
		
		dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
		
	}*/
	//BELOW CODE FOR UPLOADS
	//28
	
	/*dto.setPropImage1DocumentName("ANGLE 1.JPG");
	dto.setPropImage1FilePath(propDetsArray[28].trim());
	
	dto.setPropImage2DocumentName("ANGLE 2.JPG");
	dto.setPropImage2FilePath(propDetsArray[29].trim());
	
	dto.setPropImage3DocumentName("ANGLE 3.JPG");
	dto.setPropImage3FilePath(propDetsArray[30].trim());
	
	dto.setPropMapDocumentName("MAP.JPG");
	dto.setPropMapFilePath(propDetsArray[31].trim());
	
	if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
	{
	dto.setPropRinDocumentName("RIN.JPG");
	dto.setPropRinFilePath(propDetsArray[32].trim());
	
	dto.setPropKhasraDocumentName("KHASRA.JPG");
	dto.setPropKhasraFilePath(propDetsArray[33].trim());
	}*/
	
	
	
	//other details corresponding to app id and prop id.
	//ArrayList otherPropList = new ArrayList();
	//otherPropList=commonBd.getOtherPropDetsForViewConfirm(appId,propId);
	
	//otherPropList=getPropKhasraDetlsForDisplay(propId);
	//String otherPropDetails=otherPropList.toString().substring(2, (otherPropList.toString().length()-2));
	
	//String otherPropDetsArray[]=otherPropDetails.split(",");
	
	//FOR GETTING PROPERTY KHASRA DETAILS
	String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propId);
	
	
	//following code for inserting khasra detls into map
	if(otherPropDetsArray!=null){
	String[] khasraNum=otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
	String[] khasraArea=otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
	String[] lagaan=otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
	String[] rinPustika=otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
	
	
	
	int length=khasraNum.length;
	CommonDTO objDto;
	
	for(int i=0;i<length;i++){
		objDto=new CommonDTO();
		if(khasraNum[i].equalsIgnoreCase("null"))
		{
			objDto.setKhasraNum("-");
			objDto.setKhasraArea("-");
			objDto.setLagaan("-");
			objDto.setRinPustika("-");
		}else{
		objDto.setKhasraNum(khasraNum[i]);
		objDto.setKhasraArea(khasraArea[i]);
		objDto.setLagaan(lagaan[i]);
		objDto.setRinPustika(rinPustika[i]);
		}
		
		dto.getKhasraDetlsDisplay().add(objDto);
	}
	}else{
		CommonDTO objDto=new CommonDTO();
		objDto.setKhasraNum("-");
		objDto.setKhasraArea("-");
		objDto.setLagaan("-");
		objDto.setRinPustika("-");
		dto.getKhasraDetlsDisplay().add(objDto);
	}
	HashMap buildingMap=new HashMap();
	if(propDetsArray[13].trim().equalsIgnoreCase("2")){  // 2 FOR BUILDING
	
		
		//following code for getting building floor details
		ArrayList buildingList=getBuildingFloorDetailsNonProp(propId);
		if(buildingList.size()>0){
			
			dto.setFloorCount(buildingList.size());
			RegCompletDTO dto1=new RegCompletDTO();
			ArrayList row_list=new ArrayList();
			
			for(int j=0;j<buildingList.size();j++){
				dto1=new RegCompletDTO();
				row_list=(ArrayList)buildingList.get(j);
				String str=row_list.toString();
				str=str.substring(1,str.length()-1);
				String[] strArray=str.split(",");
				//dto1.setUsageBuilding(strArray[7]);  //l1
				//dto1.setCeilingType(strArray[8]);    //l2
				//dto1.setTypeFloorDesc(strArray[9]);  //floor
				dto1.setUsageBuilding(getPropertyL1Name(strArray[1].trim(),languageLocale));  //l1
				dto1.setCeilingType(getPropertyL2Name(strArray[2].trim(),languageLocale));    //l2
				dto1.setTypeFloorDesc(getFloorName(strArray[3].trim(),languageLocale));
				//dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
				dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
				dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
				dto1.setConstructedArea(strArray[6]);
				//dto1.setUnitType(strArray[10]);
				dto1.setUnitType(getUnitName(strArray[4].trim(),languageLocale));
				
				//dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
				
				dto.getMapBuilding().put(strArray[0], dto1);
				
				
			}
			
			
			
		}
		
		
		
		
	}else
		dto.setFloorCount(0);
			
	propMap.put(propId, dto);
	
	return propMap;
}
/**
 * getBuildingFloorDetailsNonProp
 * for getting floor details from db in case of non property deed
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getBuildingFloorDetailsNonProp(String propId) throws Exception {
	return commonBd.getBuildingFloorDetailsNonProp(propId);	
}
/**
 * for getting payment table primary key sequence id from db.
 * @param 
 * @return String
 * @author ROOPAM
 */
public String getPaymentTxnIdSeq() throws Exception {
	return commonBd.getPaymentTxnIdSeq();
}
/**
 * getPaidAmounts
 * for getting all paid amounts details from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getAllPaidAmounts(String appId) throws Exception {
	//RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getAllPaidAmounts(appId);
}
/**
 * for getting district id from db that is required for generating e stamp code.
 * @param 
 * @return String
 * @author ROOPAM
 */
public String getDistIdEstamp(String appId) throws Exception {
	return commonBd.getDistIdEstamp(appId);
}
/**
 * for inserting estamp txn id mapped with reg txn id into database.
 * @param String[]
 * @return boolean
 * @author ROOPAM
 */
public boolean insertEstampMappingDetls(String eStampTxnId, RegCommonForm form) {
	
	return commonBd.insertEstampMappingDetls(eStampTxnId,form);
	
}
/**
 * getBankDetails
 * for getting extra details from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public String[] getBankDetails(String appId) throws Exception {
	
	
	ArrayList list=commonBd.getBankDetails(appId);
	
	if(list!=null && list.size()==1){
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		String strArr[]=str.split(",");
		return strArr;
		
	}else{
		return null;
	}
	
	//RegCommonDAO dao = new RegCommonDAO();
	//return commonBd.getBankDetails(appId);
}

/**
 * printToPdf
 * for printing registration details to pdf
 * @param RegCommonForm,HttpServletRequest,HttpServletResponse
 * @return RegCommonForm
 * @author ROOPAM
 */
public RegCommonForm printToPdf(RegCommonForm regForm,HttpServletRequest request,  HttpServletResponse response,String languageLocale) throws Exception {
	
	
	

	
	regForm.setActionName(RegInitConstant.NO_ACTION);
	try{
		
		
			
	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
			
		
		//response.setHeader ("Content-Disposition", "attachment; filename=\"AccountDetails.pdf");
		 Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		 PdfWriter.getInstance(document, baos);
		   
		      //PdfWriter.getInstance(document, new FileOutputStream("AccountDetails.pdf"));
		      document.open();
		      Table datatable = new Table(22);
		      //int headerwidths[] = {35, 50, 50,50, 50, 50,50, 50, 50,50, 50, 50,50,50,50,50,50,50,50,50,50,50};
		      //datatable.setWidths(headerwidths);			      
		      datatable.setWidth(100);
		      datatable.setPadding(3);
		      if(regForm.getFromAdjudication()==1){
		      Cell title = new Cell(new Phrase("ADJUDICATION FORM", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
		      title.setHorizontalAlignment(Element.ALIGN_CENTER);
		      title.setLeading(20);
		      title.setColspan(22);
		      title.setBorder(Rectangle.NO_BORDER);
		      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(title);
		      }
		      else {
			   Cell title = new Cell(new Phrase("REGISTRATION INITIATION FORM", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD)));
			   title.setHorizontalAlignment(Element.ALIGN_CENTER);
			      title.setLeading(20);
			      title.setColspan(22);
			      title.setBorder(Rectangle.NO_BORDER);
			      title.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(title);
		      }
		     
		      datatable.setBorderWidth(2);
		      datatable.setAlignment(1);
		      
		      Cell sectionheader=new Cell(new Phrase("Transaction Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		      sectionheader.setColspan(22);
		      sectionheader.setBorder(Rectangle.NO_BORDER);
		      sectionheader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(sectionheader);
		      datatable.setAlignment(2);     
		   
		      if(regForm.getFromAdjudication()==1){
		      Cell txnno=new Cell(new Phrase("Adjudication No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      txnno.setHeader(true);
		      txnno.setColspan(11);
		      //txnno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(txnno);
		      }
		      else{
			  Cell txnno=new Cell(new Phrase("Registration Initiation No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			  txnno.setHeader(true);
		      txnno.setColspan(11);
		      //txnno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(txnno);
		      }
		     
		      
		      Cell txnnovalue=new Cell(new Phrase(RegInitConstant.TXN_ID_PREFIX+regForm.getHidnRegTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      txnnovalue.setHeader(true);
		      txnnovalue.setColspan(11);
		      //txnnovalue.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(txnnovalue);
		      
		      Cell licenceno=new Cell(new Phrase("Date/Time", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      licenceno.setHeader(true);
		      licenceno.setColspan(11);
		      //l//icenceno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(licenceno);
		      
		      Cell licencenovalue=new Cell(new Phrase(getCurrDateTime(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      licencenovalue.setHeader(true);
		      licencenovalue.setColspan(11);
		     // licencenovalue.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(licencenovalue);
		      
		      Cell place=new Cell(new Phrase("Deed", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      place.setHeader(true);
		      place.setColspan(11);
		      //place.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(place);
		     
		      Cell placevalue=new Cell(new Phrase(regForm.getDeedtype1(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      placevalue.setHeader(true);
		      placevalue.setColspan(11);
		     // placevalue.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(placevalue);
		      
		      Cell name=new Cell(new Phrase("Instrument", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      name.setHeader(true);
		      name.setColspan(11);
		      datatable.addCell(name);
		      Cell namevalue=new Cell(new Phrase(regForm.getInstType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      namevalue.setHeader(true);
		      namevalue.setColspan(11);
		      datatable.addCell(namevalue);
		      
		      Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
		      row.setHeader(true);
		      row.setColspan(22);
		      
		      if(regForm.getSelectedExemptionList()!=null && regForm.getSelectedExemptionList().size()>0){
		      Cell exemptionHdr=new Cell(new Phrase("Exemptions", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      exemptionHdr.setHeader(true);
		      exemptionHdr.setColspan(22);
		      //balanceon.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(exemptionHdr);
		      
		     for(int i=0;i<regForm.getSelectedExemptionList().size();i++){
		    	 
		    	 ExemptionDTO dto = new ExemptionDTO();
		    	 
		    	 dto=(ExemptionDTO)regForm.getSelectedExemptionList().get(i);
		    	 
		    	 
		    	 Cell exemptionValue=new Cell(new Phrase(dto.getExemType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		    	 exemptionValue.setHeader(true);
		    	 exemptionValue.setColspan(22);
			         datatable.addCell(exemptionValue);
		    	 
		    	 
		    	 
		     }
	}
		      
		      int deedId=regForm.getDeedID();
		      if(deedId==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
		    	  
		    	  String str="-";
		    	  
		    	  if(regForm.getCancelDeedRadio()==0){
		    	  str="Will";
		    	  }
		    	  if(regForm.getCancelDeedRadio()==1){
			      str="PoA";
		    	  }
		    	  
		    	  
		    	  
		    	  Cell cancl=new Cell(new Phrase("Cancellation of "+str, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		    	  cancl.setHeader(true);
		    	  cancl.setColspan(22);
			      datatable.addCell(cancl);
			      		    	  
		    	  
		      }
		      
		      if(regForm.getFromAdjudication()==1){
		    	  String remarks=getRemarks(regForm.getHidnRegTxnId());
		    	//  System.out.println("Remarks are::::"+remarks);
		    	  regForm.setAdjuRemarks(remarks);

			      Cell purpose=new Cell(new Phrase("Adjudication Remarks", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      purpose.setHeader(true);
			      purpose.setColspan(11);
			      datatable.addCell(purpose);
			      if(regForm.getAdjuRemarks()!=null && !regForm.getAdjuRemarks().equalsIgnoreCase("")){
			      Cell purposevalue=new Cell(new Phrase(regForm.getAdjuRemarks().replace(RegInitConstant.SPLIT_CONSTANT, ","), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      purposevalue.setHeader(true);
			      purposevalue.setColspan(11);
			      datatable.addCell(purposevalue);
			      }
			      else{
			    	  Cell purposevalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      purposevalue.setHeader(true);
				      purposevalue.setColspan(11);
				      datatable.addCell(purposevalue);
			      }
			      
		    	  
		      }
		      else
		      {
		      Cell purpose=new Cell(new Phrase("Purpose", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      purpose.setHeader(true);
		      purpose.setColspan(11);
		      datatable.addCell(purpose);
		      if(regForm.getPurpose()!=null && !regForm.getPurpose().equalsIgnoreCase("")){
		      Cell purposevalue=new Cell(new Phrase(regForm.getPurpose().replace(RegInitConstant.SPLIT_CONSTANT, ","), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      purposevalue.setHeader(true);
		      purposevalue.setColspan(11);
		      datatable.addCell(purposevalue);
		      }
		      else{
		    	  Cell purposevalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      purposevalue.setHeader(true);
			      purposevalue.setColspan(11);
			      datatable.addCell(purposevalue);
		      }
		      
		      String adjuremarks=getRemarks(regForm.getHidnRegTxnId());
	    	  regForm.setAdjuRemarks(adjuremarks);
	    	  if(regForm.getAdjuRemarks()!=null && !regForm.getAdjuRemarks().equalsIgnoreCase("")){
		      Cell remarks=new Cell(new Phrase("Adjudication Remarks", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      remarks.setHeader(true);
		      remarks.setColspan(11);
		      datatable.addCell(remarks);
		      
		      Cell remarksvalue=new Cell(new Phrase(regForm.getAdjuRemarks().replace(RegInitConstant.SPLIT_CONSTANT, ","), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      remarksvalue.setHeader(true);
		      remarksvalue.setColspan(11);
		      datatable.addCell(remarksvalue);
		      }
		      /*else{
		    	  Cell remarksvalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		    	  remarksvalue.setHeader(true);
		    	  remarksvalue.setColspan(11);
			      datatable.addCell(remarksvalue);
		      }*/
		      
		      }
		      
		      NumberFormat obj=new DecimalFormat("#0.00");
		      //String considAmnt=getConsidAmtTitle(regForm.getHidnRegTxnId());
		      
		      /*if(deedId==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
		    	  
		    	  
		    	  
					
						if(regForm.getConsiAmountTrns()!=null || !regForm.getConsiAmountTrns().equalsIgnoreCase(""))	    	  
						{
		    	  Cell considerAmt=new Cell(new Phrase("Secured Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(obj.format(Double.parseDouble(regForm.getConsiAmountTrns())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
						}
						else{
							Cell considerAmt=new Cell(new Phrase("Secured Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
						      considerAmt.setHeader(true);
						      considerAmt.setColspan(8);
			            	  datatable.addCell(considerAmt);
						      Cell considerAmtvalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
						      considerAmtvalue.setHeader(true);
						      considerAmtvalue.setColspan(14);
						      datatable.addCell(considerAmtvalue);
						}
		    	  
		      }
		      if((deedId==RegInitConstant.DEED_MORTGAGE_NPV && regForm.getInstID()==RegInitConstant.INST_MORTGAGE_NPV_1)||
		    		  (deedId==RegInitConstant.DEED_POA && regForm.getInstID()==RegInitConstant.INST_POA_1) ||
		    		  (deedId==RegInitConstant.DEED_SETTLEMENT_NPV && regForm.getInstID()==RegInitConstant.INST_SETTLEMENT_NPV_1) ||
		    		  (deedId==RegInitConstant.DEED_WILL_NPV && regForm.getInstID()==RegInitConstant.INST_WILL_NPV_1)){
		    	  
		    	  
		    	  //String considAmnt=getConsidAmtTitle(regForm.getHidnRegTxnId());
					
						if(regForm.getConsiAmountTrns()!=null || !regForm.getConsiAmountTrns().equalsIgnoreCase(""))	    	  
						{
		    	  Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(obj.format(Double.parseDouble(regForm.getConsiAmountTrns())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
						}
						else{
							Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
						      considerAmt.setHeader(true);
						      considerAmt.setColspan(8);
			            	  datatable.addCell(considerAmt);
						      Cell considerAmtvalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
						      considerAmtvalue.setHeader(true);
						      considerAmtvalue.setColspan(14);
						      datatable.addCell(considerAmtvalue);
						}
		    	  
		      }
		      if(deedId==RegInitConstant.DEED_POA && regForm.getInstID()==RegInitConstant.INST_POA_2)
		      {
		    	  
		    	  
		    	  //String considAmnt=getConsidAmtTitle(regForm.getHidnRegTxnId());
					
						if(regForm.getConsiAmountTrns()!=null || !regForm.getConsiAmountTrns().equalsIgnoreCase(""))	    	  
						{
		    	  Cell considerAmt=new Cell(new Phrase("Number of persons", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(regForm.getConsiAmountTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
						}
						else{
							Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
						      considerAmt.setHeader(true);
						      considerAmt.setColspan(8);
			            	  datatable.addCell(considerAmt);
						      Cell considerAmtvalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
						      considerAmtvalue.setHeader(true);
						      considerAmtvalue.setColspan(14);
						      datatable.addCell(considerAmtvalue);
						}
		    	  
		      }
		      if(deedId==RegInitConstant.DEED_TRANSFER_LEASE_NPV)
		      {
		    	  
		    	  
		    	  //String considAmnt=getConsidAmtTitle(regForm.getHidnRegTxnId());
					
						if(regForm.getConsiAmountTrns()!=null || !regForm.getConsiAmountTrns().equalsIgnoreCase(""))	    	  
						{
		    	  Cell considerAmt=new Cell(new Phrase("Average Annual Rent(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(regForm.getConsiAmountTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
						}
						else{
							Cell considerAmt=new Cell(new Phrase("Average Annual Rent(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
						      considerAmt.setHeader(true);
						      considerAmt.setColspan(8);
			            	  datatable.addCell(considerAmt);
						      Cell considerAmtvalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
						      considerAmtvalue.setHeader(true);
						      considerAmtvalue.setColspan(14);
						      datatable.addCell(considerAmtvalue);
						}
		    	  
		      }*/
		      
		      if(!regForm.getExtraFieldLabel().equalsIgnoreCase(RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE)){
		    	  Cell considerAmt=new Cell(new Phrase(regForm.getExtraFieldLabel(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(regForm.getConsiAmountTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
		      }
		    
		      
		      datatable.addCell(row);
		      
		      //DUTY DETAILS BEGIN
		      
		      Cell dutyHeader=new Cell(new Phrase("Duty and Registration Fee Details(INR)", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		      dutyHeader.setColspan(22);
		      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		      dutyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(dutyHeader);
		      datatable.setAlignment(2);  
		      
		      Cell stampDuty=new Cell(new Phrase("Stamp Duty", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      stampDuty.setHeader(true);
		      stampDuty.setColspan(3);
		     // sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(stampDuty);
		      
		      Cell municipal=new Cell(new Phrase("Municipal Duty", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      municipal.setHeader(true);
		      municipal.setColspan(4);
		     // referenceid.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(municipal);
		      
		      Cell janpad=new Cell(new Phrase("Janpad Duty", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      janpad.setHeader(true);
		      janpad.setColspan(4);
		      //trasactiondate.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(janpad);
		      
		      Cell upkar=new Cell(new Phrase("Upkar", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      upkar.setHeader(true);
		      upkar.setColspan(3);
		      //des.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(upkar);
		      
		      Cell totalDuty=new Cell(new Phrase("Total Duty", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      totalDuty.setHeader(true);
		      totalDuty.setColspan(3);
		      //sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(totalDuty);
		      
		      Cell regFee=new Cell(new Phrase("Registration Fee", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      regFee.setHeader(true);
		      regFee.setColspan(5);
		      //sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(regFee);
		      
		      Cell stampDutyVal=new Cell(new Phrase(regForm.getStampduty1(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      stampDutyVal.setHeader(true);
		      stampDutyVal.setColspan(3);
		     // sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(stampDutyVal);
		      
		      Cell municipalVal=new Cell(new Phrase(regForm.getNagarPalikaDuty(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      municipalVal.setHeader(true);
		      municipalVal.setColspan(4);
		     // referenceid.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(municipalVal);
		      
		      Cell janpadVal=new Cell(new Phrase(regForm.getPanchayatDuty(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      janpadVal.setHeader(true);
		      janpadVal.setColspan(4);
		      //trasactiondate.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(janpadVal);
		      
		      Cell upkarVal=new Cell(new Phrase(regForm.getUpkarDuty(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      upkarVal.setHeader(true);
		      upkarVal.setColspan(3);
		      //des.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(upkarVal);
		      
		      Cell totalDutyVal=new Cell(new Phrase(regForm.getTotalduty(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      totalDutyVal.setHeader(true);
		      totalDutyVal.setColspan(3);
		      //sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(totalDutyVal);
		      
		      Cell regFeeVal=new Cell(new Phrase(regForm.getRegistrationFee(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      regFeeVal.setHeader(true);
		      regFeeVal.setColspan(5);
		      //sno.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(regFeeVal);
		      
		      
		      datatable.addCell(row);
		      
		      
		      //space for payment matrix
		      
		      ArrayList paymntList=getPaymentDetlsForDisplay(regForm.getHidnRegTxnId());
		      
		      if(paymntList!=null && paymntList.size()>0){
		    	  
		    	  ArrayList rowList;
		      
		    	  Cell payHeader=new Cell(new Phrase("Payment Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		    	  payHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		    	  payHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(payHeader);
			      datatable.setAlignment(2);  
			      
			      Cell srNo=new Cell(new Phrase("Sr. No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      srNo.setHeader(true);
			      srNo.setColspan(2);
			     // sno.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(srNo);
			      
			      Cell mode=new Cell(new Phrase("Mode", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      mode.setHeader(true);
			      mode.setColspan(5);
			     // referenceid.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(mode);
			      
			      Cell payTxn=new Cell(new Phrase("Payment Txn. Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      payTxn.setHeader(true);
			      payTxn.setColspan(5);
			      //trasactiondate.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(payTxn);
			      
			      Cell amnt=new Cell(new Phrase("Amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      amnt.setHeader(true);
			      amnt.setColspan(5);
			      //des.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(amnt);
			      
			      Cell date=new Cell(new Phrase("Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      date.setHeader(true);
			      date.setColspan(5);
			      //sno.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(date);
		      
		      
			      for(int d=0;d<paymntList.size();d++){
			      
			    	  rowList=(ArrayList)paymntList.get(d);
			    	  
			    	  String str=rowList.toString();
			    	  str=str.substring(1, str.length()-1);
			    	  String[] strArr=str.split(",");
			      
			    	  Cell srNoVal=new Cell(new Phrase(Integer.toString(d+1), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    	  srNoVal.setHeader(true);
			    	  srNoVal.setColspan(2);
				     // sno.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(srNoVal);
				      
				      Cell modeVal=new Cell(new Phrase(strArr[1].trim(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      modeVal.setHeader(true);
				      modeVal.setColspan(5);
				     // referenceid.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(modeVal);
				      
				      Cell payTxnVal=new Cell(new Phrase(strArr[2].trim(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      payTxnVal.setHeader(true);
				      payTxnVal.setColspan(5);
				      //trasactiondate.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(payTxnVal);
				      
				      Cell amntVal=new Cell(new Phrase(obj.format(Double.parseDouble(strArr[3].trim())), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      amntVal.setHeader(true);
				      amntVal.setColspan(5);
				      //des.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(amntVal);
				      
				      Cell dateVal=new Cell(new Phrase(convertDOB(strArr[4].trim()), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      dateVal.setHeader(true);
				      dateVal.setColspan(5);
				      //sno.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(dateVal);
			    	  
			    	  
			      }
			      
			      datatable.addCell(row);
			      //below row for Estamp Code
			      
			      String eCode=getEstampCode(regForm.getHidnRegTxnId());
			      
			      Cell ecode=new Cell(new Phrase("E-Stamp Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      ecode.setHeader(true);
			      ecode.setColspan(11);
			      datatable.addCell(ecode);
			      if(eCode!=null && !eCode.equalsIgnoreCase("") && !eCode.equalsIgnoreCase("null")){
			      Cell ecodevalue=new Cell(new Phrase(eCode, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      ecodevalue.setHeader(true);
			      ecodevalue.setColspan(11);
			      datatable.addCell(ecodevalue);
			      }
			      else{
			    	  Cell ecodevalue=new Cell(new Phrase("-", FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			    	  ecodevalue.setHeader(true);
			    	  ecodevalue.setColspan(11);
				      datatable.addCell(ecodevalue);
			      }
			      
			      datatable.addCell(row);
		      }
		     
		      
		      
		      
		      //PROPERTY WISE PARTY DETAILS BEGIN
		      
		      if(deedId==RegInitConstant.DEED_CERTIFICATE_SALE || deedId==RegInitConstant.DEED_CONVEYANCE_P ||
		    		  deedId==RegInitConstant.DEED_GIFT || deedId==RegInitConstant.DEED_POA_P || deedId==RegInitConstant.DEED_LEASE_PV ||
		    		  deedId==RegInitConstant.DEED_TRUST_PV || deedId==RegInitConstant.DEED_SETTLEMENT_PV ||
		    		  deedId==RegInitConstant.DEED_MORTGAGE_PV || deedId==RegInitConstant.DEED_RELEASE_PV ||
		    		  deedId==RegInitConstant.DEED_TRANSFER_LEASE_PV || deedId==RegInitConstant.DEED_AWARD_PV ||
		    		  deedId==RegInitConstant.DEED_AGREEMENT_MEMO_PV || deedId==RegInitConstant.DEED_FURTHER_CHARGE_PV
		    		  || deedId==RegInitConstant.DEED_PARTNERSHIP_PV)
		      {
		    	  
		    	  getExtraDetls(regForm,languageLocale);
		    	  
		    	  
		    	  if(deedId==RegInitConstant.DEED_LEASE_PV){
		    	  Cell bankHeader=new Cell(new Phrase("Lease Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		    	  bankHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		    	  bankHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(bankHeader);
			      datatable.setAlignment(2);  
		    	  
			      Cell bName=new Cell(new Phrase("Rent(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bName.setHeader(true);
			      bName.setColspan(5);
			      datatable.addCell(bName);
			      Cell bNamevalue=new Cell(new Phrase(obj.format(regForm.getRent()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bNamevalue.setHeader(true);
			      bNamevalue.setColspan(6);
			      datatable.addCell(bNamevalue);
			      
			      Cell branchName=new Cell(new Phrase("Advance(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      branchName.setHeader(true);
			      branchName.setColspan(5);
			      datatable.addCell(branchName);
			      Cell branchNamevalue=new Cell(new Phrase(obj.format(regForm.getAdvance()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      branchNamevalue.setHeader(true);
			      branchNamevalue.setColspan(6);
			      datatable.addCell(branchNamevalue);
			      
			      Cell bAddress=new Cell(new Phrase("Development charge(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAddress.setHeader(true);
			      bAddress.setColspan(5);
			      datatable.addCell(bAddress);
			      Cell bAddressvalue=new Cell(new Phrase(obj.format(regForm.getDevlpmtCharge()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAddressvalue.setHeader(true);
			      bAddressvalue.setColspan(6);
			      datatable.addCell(bAddressvalue);
			      
			      Cell bAuth=new Cell(new Phrase("Other recurring charge paid(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAuth.setHeader(true);
			      bAuth.setColspan(5);
			      datatable.addCell(bAuth);
			      Cell bAuthvalue=new Cell(new Phrase(obj.format(regForm.getOtherRecCharge()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAuthvalue.setHeader(true);
			      bAuthvalue.setColspan(6);
			      datatable.addCell(bAuthvalue);
			      
			      Cell loanAmt=new Cell(new Phrase("Premium(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      loanAmt.setHeader(true);
			      loanAmt.setColspan(5);
			      datatable.addCell(loanAmt);
			      Cell loanAmtvalue=new Cell(new Phrase(obj.format(regForm.getPremium()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      loanAmtvalue.setHeader(true);
			      loanAmtvalue.setColspan(6);
			      datatable.addCell(loanAmtvalue);
			      
			      Cell sancAmt=new Cell(new Phrase("Term of lease", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      sancAmt.setHeader(true);
			      sancAmt.setColspan(5);
			      datatable.addCell(sancAmt);
			      Cell sancAmtvalue=new Cell(new Phrase(obj.format(regForm.getTermLease()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      sancAmtvalue.setHeader(true);
			      sancAmtvalue.setColspan(6);
			      datatable.addCell(sancAmtvalue);
		    	  
			      datatable.addCell(row);
		    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_TRUST_PV){
			    	  Cell trustHeader=new Cell(new Phrase("Trust Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Trust Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getTrustName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Trust Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getTrustDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_MORTGAGE_PV){
			    	  Cell trustHeader=new Cell(new Phrase("Mortgage Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("With Possesion", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getWithPos(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Secured Loan Amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(Double.toString(regForm.getSecLoanAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_AWARD_PV){
		    		  
		    		  Cell trustHeader=new Cell(new Phrase("Arbitrator Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  

			    	  
			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    	  indName.setHeader(true);
			    	  indName.setColspan(8);
    			      datatable.addCell(indName);
    			      Cell indNamevalue=new Cell(new Phrase(regForm.getFnameArb()+" "+regForm.getMnameTrns()+" "+regForm.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      indNamevalue.setHeader(true);
    			      indNamevalue.setColspan(14);
    			      datatable.addCell(indNamevalue);
    			      
    			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      gender.setHeader(true);
    			      gender.setColspan(5);
    			      datatable.addCell(gender);
    			      Cell gendervalue=new Cell(new Phrase(regForm.getGendarNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      gendervalue.setHeader(true);
    			      gendervalue.setColspan(6);
    			      datatable.addCell(gendervalue);
    			      
    			      /*Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      dob.setHeader(true);
    			      dob.setColspan(5);
    			      datatable.addCell(dob);
    			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      dobvalue.setHeader(true);
    			      dobvalue.setColspan(6);
    			      datatable.addCell(dobvalue);*/
    			      
    			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      dob.setHeader(true);
    			      dob.setColspan(5);
    			      datatable.addCell(dob);
    			      Cell dobvalue=new Cell(new Phrase(regForm.getAgeArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      dobvalue.setHeader(true);
    			      dobvalue.setColspan(6);
    			      datatable.addCell(dobvalue);
    			      
    			      Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      fname.setHeader(true);
    			      fname.setColspan(5);
    			      datatable.addCell(fname);
    			      Cell fnamevalue=new Cell(new Phrase(regForm.getFatherNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      fnamevalue.setHeader(true);
    			      fnamevalue.setColspan(6);
    			      datatable.addCell(fnamevalue);
    			      
    			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      gname.setHeader(true);
    			      gname.setColspan(5);
    			      datatable.addCell(gname);
    			      Cell gnamevalue=new Cell(new Phrase(regForm.getMotherNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      gnamevalue.setHeader(true);
    			      gnamevalue.setColspan(6);
    			      datatable.addCell(gnamevalue);
    			      
    			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      sname.setHeader(true);
    			      sname.setColspan(5);
    			      datatable.addCell(sname);
    			      Cell snamevalue=new Cell(new Phrase(regForm.getSpouseNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      snamevalue.setHeader(true);
    			      snamevalue.setColspan(6);
    			      datatable.addCell(snamevalue);
    			      
    			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      email.setHeader(true);
    			      email.setColspan(5);
    			      datatable.addCell(email);
    			      Cell emailvalue=new Cell(new Phrase(regForm.getEmailIDArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      emailvalue.setHeader(true);
    			      emailvalue.setColspan(6);
    			      datatable.addCell(emailvalue);
    			      
    			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      category.setHeader(true);
    			      category.setColspan(5);
    			      datatable.addCell(category);
    			      Cell categoryvalue=new Cell(new Phrase(regForm.getIndCategoryNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      categoryvalue.setHeader(true);
    			      categoryvalue.setColspan(6);
    			      datatable.addCell(categoryvalue);
    			      
    			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      disability.setHeader(true);
    			      disability.setColspan(5);
    			      datatable.addCell(disability);
    			      Cell disabilityvalue=new Cell(new Phrase(regForm.getIndDisabilityNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      disabilityvalue.setHeader(true);
    			      disabilityvalue.setColspan(6);
    			      datatable.addCell(disabilityvalue);
    			      
    			      if(regForm.getIndDisabilityArb().equalsIgnoreCase("Y")){
    			    	  
    			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			    	  disabilityDesc.setHeader(true);
    			    	  disabilityDesc.setColspan(8);
        			      datatable.addCell(disabilityDesc);
        			      Cell disabilityDescvalue=new Cell(new Phrase(regForm.getIndDisabilityDescArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			      disabilityDescvalue.setHeader(true);
        			      disabilityDescvalue.setColspan(14);
        			      datatable.addCell(disabilityDescvalue);
    			    	  
    			      }
    			      
    			      
    			      
    			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      nationality.setHeader(true);
    			      nationality.setColspan(8);
    			      datatable.addCell(nationality);
    			      Cell nationalityvalue=new Cell(new Phrase(regForm.getNationalityArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      nationalityvalue.setHeader(true);
    			      nationalityvalue.setColspan(14);
    			      datatable.addCell(nationalityvalue);
    			      
    			      
    			      
    			      
    			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      phNo.setHeader(true);
    			      phNo.setColspan(5);
    			      datatable.addCell(phNo);
    			      Cell phNovalue=new Cell(new Phrase(regForm.getIndphnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      phNovalue.setHeader(true);
    			      phNovalue.setColspan(6);
    			      datatable.addCell(phNovalue);
    			      
    			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      mobNo.setHeader(true);
    			      mobNo.setColspan(5);
    			      datatable.addCell(mobNo);
    			      Cell mobNovalue=new Cell(new Phrase(regForm.getIndmobnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      mobNovalue.setHeader(true);
    			      mobNovalue.setColspan(6);
    			      datatable.addCell(mobNovalue);
    			      
    			      
    			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      address.setHeader(true);
    			      address.setColspan(8);
    			      datatable.addCell(address);
    			      Cell addressvalue=new Cell(new Phrase(regForm.getIndaddressArb()+", "+regForm.getInddistrictNameArb()+", "+regForm.getIndstatenameNameArb()+", "+regForm.getIndcountryNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      addressvalue.setHeader(true);
    			      addressvalue.setColspan(14);
    			      datatable.addCell(addressvalue);
    			      
    			      
    			      
    			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      proof.setHeader(true);
    			      proof.setColspan(5);
    			      datatable.addCell(proof);
    			      Cell proofvalue=new Cell(new Phrase(regForm.getListIDNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      proofvalue.setHeader(true);
    			      proofvalue.setColspan(6);
    			      datatable.addCell(proofvalue);
    			      
    			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      proofId.setHeader(true);
    			      proofId.setColspan(5);
    			      datatable.addCell(proofId);
    			      Cell proofIdvalue=new Cell(new Phrase(regForm.getIdnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      proofIdvalue.setHeader(true);
    			      proofIdvalue.setColspan(6);
    			      datatable.addCell(proofIdvalue);
    			      
    			 
			    	  
			      
		    		  
		    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_AGREEMENT_MEMO_PV){
			    	  Cell trustHeader=new Cell(new Phrase("Agreement Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Advance Payment", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(Double.toString(regForm.getAdvance()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Date of advance payment", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getAdvancePaymntDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      Cell address=new Cell(new Phrase("Possesion given to the buyer", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      address.setHeader(true);
    			      address.setColspan(8);
    			      datatable.addCell(address);
    			      Cell addressvalue=new Cell(new Phrase(regForm.getPossGivenName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      addressvalue.setHeader(true);
    			      addressvalue.setColspan(14);
    			      datatable.addCell(addressvalue);
    			      
				      
				      datatable.addCell(row);
			    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_PARTNERSHIP_PV){
			    	  Cell trustHeader=new Cell(new Phrase("Dissolution of Partnership Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Date of dissolution of partnership", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getDissolutionDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Date of Retirement of  Partner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getRetirmentDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		      
		      Cell propPartyHeader=new Cell(new Phrase("Property Wise Party Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		      propPartyHeader.setColspan(22);
		      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		      propPartyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(propPartyHeader);
		      datatable.setAlignment(2);  
		      
		      
		      HashMap pdfMap=getPropertyPartyDetailsForPDF(regForm.getHidnRegTxnId(),1,languageLocale);
		
		      if(pdfMap!=null){
		      Collection mapCollection=pdfMap.values();
		      Object[] l1=mapCollection.toArray();
              RegCompletDTO dto=new RegCompletDTO();
             ArrayList partyList;
              
           //below loop for property details
              for(int k=0;k<l1.length;k++)
              {
              	
            	  dto=(RegCompletDTO)l1[k];
            	  
            	  datatable.addCell(row);
            	  Cell propertyCount=new Cell(new Phrase("Property "+(k+1) +" : Id-"+dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            	  propertyCount.setHeader(true);
            	  propertyCount.setColspan(22);
			      datatable.addCell(propertyCount);
			      
            	  
            	  if(dto.getPartyListPdf()!=null && dto.getPartyListPdf().size()>0){
                	  partyList=dto.getPartyListPdf();
                	  RegCommonDTO mapDto =new RegCommonDTO();
                	  //below loop for party details
                	  
                	  
    			      int sellerCount=0;
    			      int buyerCount=0;
    			      
                	  for(int j=0;j<partyList.size();j++){                          //seller/owner loop
                		  mapDto=(RegCommonDTO)partyList.get(j);
                		  //WRITING PARTY DETAILS ON PDF
                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                		  
                		  //int claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(roleId)));
                		  
                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
                   		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                		  
                		  if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE1_OWNER_SELF || roleId==RegInitConstant.ROLE2_OWNER_SELF ||
                				  roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE_OWNER_SELF ||
                				  roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER || roleId==RegInitConstant.ROLE_SELLER_SELF ||
                				  roleId==RegInitConstant.ROLE_DONOR || roleId==RegInitConstant.ROLE_DONOR_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_LESSER || roleId==RegInitConstant.ROLE_LESSER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER || roleId==RegInitConstant.ROLE_TRUSTEE ||
                				  roleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER || roleId==RegInitConstant.ROLE_SETTLER_SELF ||
                				  roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER || roleId==RegInitConstant.ROLE_MORTGAGER_SELF ||
                				  roleId==RegInitConstant.ROLE_RELEASER_POA_HOLDER || roleId==RegInitConstant.ROLE_RELEASER_SELF ||
                				  roleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANSFERER_SELF ||
                				  roleId==RegInitConstant.ROLE_AWARDER_POA_HOLDER || roleId==RegInitConstant.ROLE_AWARDER_SELF ||
                				  roleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF)
                		  {
                			  datatable.addCell(row);
                			  sellerCount++;
                			  if(roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE_OWNER_SELF){
                			  Cell ownerHdr=new Cell(new Phrase("Owner "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			  ownerHdr.setHeader(true);
                			  ownerHdr.setColspan(22);
            			      datatable.addCell(ownerHdr);
            			      }
                			  if(roleId==RegInitConstant.ROLE_SELLER_POA_HOLDER || roleId==RegInitConstant.ROLE_SELLER_SELF){
            			      Cell sellerHdr=new Cell(new Phrase("Seller "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			      sellerHdr.setHeader(true);
            			      sellerHdr.setColspan(22);
            			      datatable.addCell(sellerHdr);
            			       }
                			  if(roleId==RegInitConstant.ROLE_DONOR || roleId==RegInitConstant.ROLE_DONOR_POA_HOLDER){
                			      Cell donorHdr=new Cell(new Phrase("Donor "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      donorHdr.setHeader(true);
                			      donorHdr.setColspan(22);
                			      datatable.addCell(donorHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_LESSER || roleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Lesser "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER || roleId==RegInitConstant.ROLE_TRUSTEE){
                    			  Cell trusteeHdr=new Cell(new Phrase("Trustee "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  trusteeHdr.setHeader(true);
                    			  trusteeHdr.setColspan(22);
                			      datatable.addCell(trusteeHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER || roleId==RegInitConstant.ROLE_SETTLER_SELF){
                			      Cell settlerHdr=new Cell(new Phrase("Settler "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      settlerHdr.setHeader(true);
                			      settlerHdr.setColspan(22);
                			      datatable.addCell(settlerHdr);
                			       }
                			  if(roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER || roleId==RegInitConstant.ROLE_MORTGAGER_SELF){
                			      Cell mortgagerHdr=new Cell(new Phrase("Mortgager "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mortgagerHdr.setHeader(true);
                			      mortgagerHdr.setColspan(22);
                			      datatable.addCell(mortgagerHdr);
                			       }
                			  if(roleId==RegInitConstant.ROLE_RELEASER_POA_HOLDER || roleId==RegInitConstant.ROLE_RELEASER_SELF){
                			      Cell mortgagerHdr=new Cell(new Phrase("Releaser "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mortgagerHdr.setHeader(true);
                			      mortgagerHdr.setColspan(22);
                			      datatable.addCell(mortgagerHdr);
                			       }
                			  if(roleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANSFERER_SELF){
                			      Cell mortgagerHdr=new Cell(new Phrase("Transferor "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mortgagerHdr.setHeader(true);
                			      mortgagerHdr.setColspan(22);
                			      datatable.addCell(mortgagerHdr);
                			       }
                			  if(roleId==RegInitConstant.ROLE_AWARDER_POA_HOLDER || roleId==RegInitConstant.ROLE_AWARDER_SELF){
                			      Cell mortgagerHdr=new Cell(new Phrase("Awarder "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mortgagerHdr.setHeader(true);
                			      mortgagerHdr.setColspan(22);
                			      datatable.addCell(mortgagerHdr);
                			       }
                			  if(roleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF){
                			      Cell sellerHdr=new Cell(new Phrase("Retiring Party "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      sellerHdr.setHeader(true);
                			      sellerHdr.setColspan(22);
                			      datatable.addCell(sellerHdr);
                			       }
                			  
                			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			  partyId.setHeader(true);
                			  partyId.setColspan(8);
            			      datatable.addCell(partyId);
            			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      partyIdvalue.setHeader(true);
            			      partyIdvalue.setColspan(14);
            			      datatable.addCell(partyIdvalue);
                			 
                			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			  appType.setHeader(true);
                			  appType.setColspan(8);
            			      datatable.addCell(appType);
            			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      appTypevalue.setHeader(true);
            			      appTypevalue.setColspan(14);
            			      datatable.addCell(appTypevalue);
            			      
            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
            			    	  
            			    	  setCommonOrgOnPdf(mapDto,datatable);
            			    	  
                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      share.setHeader(true);
                			      share.setColspan(8);
                			      datatable.addCell(share);
                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      sharevalue.setHeader(true);
                			      sharevalue.setColspan(14);
                			      datatable.addCell(sharevalue);
            			    	  
            			      }
            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
            			    	  
            			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			    	  indName.setHeader(true);
            			    	  indName.setColspan(8);
                			      datatable.addCell(indName);
                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      indNamevalue.setHeader(true);
                			      indNamevalue.setColspan(14);
                			      datatable.addCell(indNamevalue);
                			      
                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      relation.setHeader(true);
                			      relation.setColspan(5);
                			      datatable.addCell(relation);
                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      relationvalue.setHeader(true);
                			      relationvalue.setColspan(6);
                			      datatable.addCell(relationvalue);
                			   
                			      
                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      fathername.setHeader(true);
                			      fathername.setColspan(5);
                			      datatable.addCell(fathername);
                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      fathernamevalue.setHeader(true);
                			      fathernamevalue.setColspan(6);
                			      datatable.addCell(fathernamevalue);
                			      
                			      
                			      
                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      gender.setHeader(true);
                			      gender.setColspan(5);
                			      datatable.addCell(gender);
                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      gendervalue.setHeader(true);
                			      gendervalue.setColspan(6);
                			      datatable.addCell(gendervalue);
                			      
                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      fname.setHeader(true);
                			      fname.setColspan(5);
                			      datatable.addCell(fname);
                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      fnamevalue.setHeader(true);
                			      fnamevalue.setColspan(6);
                			      datatable.addCell(fnamevalue);*/
                			      
                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      dob.setHeader(true);
                			      dob.setColspan(5);
                			      datatable.addCell(dob);
                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      dobvalue.setHeader(true);
                			      dobvalue.setColspan(6);
                			      datatable.addCell(dobvalue);*/
                			      
                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      dob.setHeader(true);
                			      dob.setColspan(5);
                			      datatable.addCell(dob);
                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      dobvalue.setHeader(true);
                			      dobvalue.setColspan(6);
                			      datatable.addCell(dobvalue);
                			   
                			      
                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      gname.setHeader(true);
                			      gname.setColspan(5);
                			      datatable.addCell(gname);
                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      gnamevalue.setHeader(true);
                			      gnamevalue.setColspan(6);
                			      datatable.addCell(gnamevalue);
                			      
                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      sname.setHeader(true);
                			      sname.setColspan(5);
                			      datatable.addCell(sname);
                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      snamevalue.setHeader(true);
                			      snamevalue.setColspan(6);
                			      datatable.addCell(snamevalue);
                			      
                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      email.setHeader(true);
                			      email.setColspan(5);
                			      datatable.addCell(email);
                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      emailvalue.setHeader(true);
                			      emailvalue.setColspan(6);
                			      datatable.addCell(emailvalue);
                			      
                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      category.setHeader(true);
                			      category.setColspan(5);
                			      datatable.addCell(category);
                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      categoryvalue.setHeader(true);
                			      categoryvalue.setColspan(6);
                			      datatable.addCell(categoryvalue);
                			      
                			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
                			    	  
                			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  scheduleArea.setHeader(true);
                			    	  scheduleArea.setColspan(8);
                    			      datatable.addCell(scheduleArea);
                    			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      scheduleAreavalue.setHeader(true);
                    			      scheduleAreavalue.setColspan(14);
                    			      datatable.addCell(scheduleAreavalue);
                    			      
                    			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                    			    	  
                    			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  permissionNo.setHeader(true);
                    			    	  permissionNo.setColspan(5);
                        			      datatable.addCell(permissionNo);
                        			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      permissionNovalue.setHeader(true);
                        			      permissionNovalue.setColspan(6);
                        			      datatable.addCell(permissionNovalue);
                        			      
                        			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			      certificate.setHeader(true);
                        			      certificate.setColspan(5);
                        			      datatable.addCell(certificate);
                        			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      certificatevalue.setHeader(true);
                        			      certificatevalue.setColspan(6);
                        			      datatable.addCell(certificatevalue);
                    			    	  
                    			      }
                			    	  
                			      }
                			      
                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      nationality.setHeader(true);
                			      nationality.setColspan(5);
                			      datatable.addCell(nationality);
                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      nationalityvalue.setHeader(true);
                			      nationalityvalue.setColspan(6);
                			      datatable.addCell(nationalityvalue);
                			      
                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      disability.setHeader(true);
                			      disability.setColspan(5);
                			      datatable.addCell(disability);
                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      disabilityvalue.setHeader(true);
                			      disabilityvalue.setColspan(6);
                			      datatable.addCell(disabilityvalue);
                			      
                			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
                			    	  
                			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  disabilityDesc.setHeader(true);
                			    	  disabilityDesc.setColspan(8);
                    			      datatable.addCell(disabilityDesc);
                    			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      disabilityDescvalue.setHeader(true);
                    			      disabilityDescvalue.setColspan(14);
                    			      datatable.addCell(disabilityDescvalue);
                			    	  
                			      }
                			      
                			     
                			      
                			      
                			     /* Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      nationality.setHeader(true);
                			      nationality.setColspan(8);
                			      datatable.addCell(nationality);
                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      nationalityvalue.setHeader(true);
                			      nationalityvalue.setColspan(14);
                			      datatable.addCell(nationalityvalue);*/
                			      
                			      
                			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      minority.setHeader(true);
                			      minority.setColspan(5);
                			      datatable.addCell(minority);
                			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      minorityvalue.setHeader(true);
                			      minorityvalue.setColspan(6);
                			      datatable.addCell(minorityvalue);
                			      
                			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      poverty.setHeader(true);
                			      poverty.setColspan(5);
                			      datatable.addCell(poverty);
                			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      povertyvalue.setHeader(true);
                			      povertyvalue.setColspan(6);
                			      datatable.addCell(povertyvalue);
                			      
                			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      phNo.setHeader(true);
                			      phNo.setColspan(5);
                			      datatable.addCell(phNo);
                			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      phNovalue.setHeader(true);
                			      phNovalue.setColspan(6);
                			      datatable.addCell(phNovalue);
                			      
                			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mobNo.setHeader(true);
                			      mobNo.setColspan(5);
                			      datatable.addCell(mobNo);
                			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      mobNovalue.setHeader(true);
                			      mobNovalue.setColspan(6);
                			      datatable.addCell(mobNovalue);
                			      
                			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      address.setHeader(true);
                			      address.setColspan(8);
                			      datatable.addCell(address);
                			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      addressvalue.setHeader(true);
                			      addressvalue.setColspan(14);
                			      datatable.addCell(addressvalue);
                			      
                			      Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      postal.setHeader(true);
                			      postal.setColspan(5);
                			      datatable.addCell(postal);
                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      postalvalue.setHeader(true);
                			      postalvalue.setColspan(6);
                			      datatable.addCell(postalvalue);
                			      
                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      share.setHeader(true);
                			      share.setColspan(5);
                			      datatable.addCell(share);
                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      sharevalue.setHeader(true);
                			      sharevalue.setColspan(6);
                			      datatable.addCell(sharevalue);
                			      
                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      occupation.setHeader(true);
                			      occupation.setColspan(8);
                			      datatable.addCell(occupation);
                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      occupationvalue.setHeader(true);
                			      occupationvalue.setColspan(14);
                			      datatable.addCell(occupationvalue);
                			      
                			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      proof.setHeader(true);
                			      proof.setColspan(5);
                			      datatable.addCell(proof);
                			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      proofvalue.setHeader(true);
                			      proofvalue.setColspan(6);
                			      datatable.addCell(proofvalue);
                			      
                			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      proofId.setHeader(true);
                			      proofId.setColspan(5);
                			      datatable.addCell(proofId);
                			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      proofIdvalue.setHeader(true);
                			      proofIdvalue.setColspan(6);
                			      datatable.addCell(proofIdvalue);
                			      
                			      
  		                   		
  	                    		
  		                   		
  	                    		
                			      
                			      
            			    	  
            			      }
            			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                   		upl2.setHeader(true);
		                   		upl2.setColspan(8);
		                   		datatable.addCell(upl2);
		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                   		upl2value.setHeader(true);
		                   		upl2value.setColspan(14);
		                   		datatable.addCell(upl2value);
          			      
          			    //BELOW CODE FOR EXECUTANT
	                   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
                    		{
	                   			
	                   		Cell upl3=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF EXECUTANT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                   		upl3.setHeader(true);
	                   		upl3.setColspan(8);
	                   		datatable.addCell(upl3);
	                   		Cell upl3value=new Cell(new Phrase(mapDto.getU3DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                   		upl3value.setHeader(true);
	                   		upl3value.setColspan(14);
	                   		datatable.addCell(upl3value);
        			      
        			      Cell upl4=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			      upl4.setHeader(true);
        			      upl4.setColspan(8);
        			      datatable.addCell(upl4);
        			      Cell upl4value=new Cell(new Phrase(mapDto.getU4DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			      upl4value.setHeader(true);
        			      upl4value.setColspan(14);
        			      datatable.addCell(upl4value);
        			      
        			      if(!mapDto.getSelectedPhotoId().equalsIgnoreCase("4")){
        			      Cell upl10=new Cell(new Phrase("PAN OR FORM 60/61", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			      upl10.setHeader(true);
        			      upl10.setColspan(8);
      			      datatable.addCell(upl10);
      			      Cell upl10value=new Cell(new Phrase(mapDto.getU10DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
      			      upl10value.setHeader(true);
      			      upl10value.setColspan(14);
      			      datatable.addCell(upl10value);
        			      }
      			      
		                   		
                    		}
	                   	//BELOW CODE FOR EXECUTANT POA HOLDER
                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
                    		{
                    			
                    			Cell upl5=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF ATTORNEY", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                   		upl5.setHeader(true);
		                   		upl5.setColspan(8);
		                   		datatable.addCell(upl5);
		                   		Cell upl5value=new Cell(new Phrase(mapDto.getU5DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                   		upl5value.setHeader(true);
		                   		upl5value.setColspan(14);
		                   		datatable.addCell(upl5value);
            			      
            			      Cell upl6=new Cell(new Phrase("ATTORNEY PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			      upl6.setHeader(true);
            			      upl6.setColspan(8);
            			      datatable.addCell(upl6);
            			      Cell upl6value=new Cell(new Phrase(mapDto.getU6DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      upl6value.setHeader(true);
            			      upl6value.setColspan(14);
            			      datatable.addCell(upl6value);
            			      
            			      Cell upl7=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			      upl7.setHeader(true);
            			      upl7.setColspan(8);
          			      datatable.addCell(upl7);
          			      Cell upl7value=new Cell(new Phrase(mapDto.getU7DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
          			      upl7value.setHeader(true);
          			      upl7value.setColspan(14);
          			      datatable.addCell(upl7value);
                    			
                    			
                    			/*mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName());
		                   		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
		                   		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName());*/
          			      
          			      Cell officeName=new Cell(new Phrase("SR Office Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
          			      officeName.setHeader(true);
          			      officeName.setColspan(8);
          			      datatable.addCell(officeName);
          			      Cell officeNamevalue=new Cell(new Phrase(mapDto.getSrOfficeNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
          			      officeNamevalue.setHeader(true);
          			      officeNamevalue.setColspan(14);
          			      datatable.addCell(officeNamevalue);
          			      
          			      Cell poaRegno=new Cell(new Phrase("PoA Registration No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
          			      poaRegno.setHeader(true);
          			      poaRegno.setColspan(5);
          			      datatable.addCell(poaRegno);
          			      Cell poaRegnovalue=new Cell(new Phrase(mapDto.getPoaRegNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
          			      poaRegnovalue.setHeader(true);
          			      poaRegnovalue.setColspan(6);
          			      datatable.addCell(poaRegnovalue);
          			      
          			      Cell poaRegDate=new Cell(new Phrase("PoA Registration Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
          			      poaRegDate.setHeader(true);
          			      poaRegDate.setColspan(5);
          			      datatable.addCell(poaRegDate);
          			      Cell poaRegDatevalue=new Cell(new Phrase(mapDto.getDatePoaRegTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
          			      poaRegDatevalue.setHeader(true);
          			      poaRegDatevalue.setColspan(6);
          			      datatable.addCell(poaRegDatevalue);
		                   		
		                   		
		                   		/*mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
		                   		mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
		                   		mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));*/
                    		}
            			     
            			      
            			      
                			  //OWNER DETAILS IN CASE OF POA BELOW
                		
            			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
            			      {
            			    	  
            			    	  setOwnerOnPdf(mapDto,datatable);
            			    	  
            			    	  /*
            			    	  
            			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            			    	  ownerHdr.setHeader(true);
            			    	  ownerHdr.setColspan(22);
                			      datatable.addCell(ownerHdr);
            			    	  
            			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			    	  ownerName.setHeader(true);
            			    	  ownerName.setColspan(5);
                			      datatable.addCell(ownerName);
                			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerNamevalue.setHeader(true);
                			      ownerNamevalue.setColspan(6);
                			      datatable.addCell(ownerNamevalue);
                			      
                			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerOrgName.setHeader(true);
                			      ownerOrgName.setColspan(5);
                			      datatable.addCell(ownerOrgName);
                			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerOrgNamevalue.setHeader(true);
                			      ownerOrgNamevalue.setColspan(6);
                			      datatable.addCell(ownerOrgNamevalue);
                			      
                			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerGender.setHeader(true);
                			      ownerGender.setColspan(5);
                			      datatable.addCell(ownerGender);
                			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerGendervalue.setHeader(true);
                			      ownerGendervalue.setColspan(6);
                			      datatable.addCell(ownerGendervalue);
                			      
                			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerDob.setHeader(true);
                			      ownerDob.setColspan(5);
                			      datatable.addCell(ownerDob);
                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerDobvalue.setHeader(true);
                			      ownerDobvalue.setColspan(6);
                			      datatable.addCell(ownerDobvalue);
                			      
                			      Cell ownerAge=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerAge.setHeader(true);
                			      ownerAge.setColspan(5);
                			      datatable.addCell(ownerAge);
                			      Cell ownerAgevalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerAgevalue.setHeader(true);
                			      ownerAgevalue.setColspan(6);
                			      datatable.addCell(ownerAgevalue);
                			      
                			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerNationality.setHeader(true);
                			      ownerNationality.setColspan(8);
                			      datatable.addCell(ownerNationality);
                			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerNationalityvalue.setHeader(true);
                			      ownerNationalityvalue.setColspan(14);
                			      datatable.addCell(ownerNationalityvalue);
                			      
                			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerAddress.setHeader(true);
                			      ownerAddress.setColspan(8);
                			      datatable.addCell(ownerAddress);
                			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerAddressvalue.setHeader(true);
                			      ownerAddressvalue.setColspan(14);
                			      datatable.addCell(ownerAddressvalue);
                			      
                			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerMobile.setHeader(true);
                			      ownerMobile.setColspan(5);
                			      datatable.addCell(ownerMobile);
                			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerMobilevalue.setHeader(true);
                			      ownerMobilevalue.setColspan(6);
                			      datatable.addCell(ownerMobilevalue);
                			      
                			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerEmail.setHeader(true);
                			      ownerEmail.setColspan(5);
                			      datatable.addCell(ownerEmail);
                			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerEmailvalue.setHeader(true);
                			      ownerEmailvalue.setColspan(6);
                			      datatable.addCell(ownerEmailvalue);
                			      
                			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerProof.setHeader(true);
                			      ownerProof.setColspan(5);
                			      datatable.addCell(ownerProof);
                			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerProofvalue.setHeader(true);
                			      ownerProofvalue.setColspan(6);
                			      datatable.addCell(ownerProofvalue);
                			      
                			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerProofId.setHeader(true);
                			      ownerProofId.setColspan(5);
                			      datatable.addCell(ownerProofId);
                			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerProofIdvalue.setHeader(true);
                			      ownerProofIdvalue.setColspan(6);
                			      datatable.addCell(ownerProofIdvalue);
            			    	  
            			    	  
            			    	  
            			    	  
            			      */}
                		  
                		  
                		  
                		  
                		  
                		  
                	  }
                		  
                		  
                		  
                		  
                	  }
                	 
            			 
            			 
                	  for(int j=0;j<partyList.size();j++){              //Buyer loop
                		  mapDto=(RegCommonDTO)partyList.get(j);
                		  //WRITING PARTY DETAILS ON PDF
                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                		  //int claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(roleId)));
                		  
                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
                   		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                		  
                		  if(roleId==RegInitConstant.ROLE_BUYER || roleId==RegInitConstant.ROLE_BUYER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_DONEE || roleId==RegInitConstant.ROLE_DONEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_POA_ACCEPTER || roleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_LESSEE || roleId==RegInitConstant.ROLE_CLAIMANT_SELF || 
                				  roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER || roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || 
                				  roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER || roleId==RegInitConstant.ROLE_RELEASEE_SELF || 
                				  roleId==RegInitConstant.ROLE_RELEASEE_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANSFEREE_SELF || 
                				  roleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER || roleId==RegInitConstant.ROLE_AWARDEE_SELF || 
                				  roleId==RegInitConstant.ROLE_AWARDEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANS_PARTY_SELF)
                		  {
                			  
                			  datatable.addCell(row);
                			  buyerCount++;
                			  
                			  if(roleId==RegInitConstant.ROLE_BUYER || roleId==RegInitConstant.ROLE_BUYER_POA_HOLDER){
                    			  Cell buyerHdr=new Cell(new Phrase("Buyer "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  buyerHdr.setHeader(true);
                    			  buyerHdr.setColspan(22);
                			      datatable.addCell(buyerHdr);
                			      }
                    			  if(roleId==RegInitConstant.ROLE_DONEE || roleId==RegInitConstant.ROLE_DONEE_POA_HOLDER){
                			      Cell doneeHdr=new Cell(new Phrase("Donee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      doneeHdr.setHeader(true);
                			      doneeHdr.setColspan(22);
                			      datatable.addCell(doneeHdr);
                			       }
                    			  if(roleId==RegInitConstant.ROLE_POA_ACCEPTER){
                    			      Cell poaAccepterHdr=new Cell(new Phrase("PoA Accepter "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      poaAccepterHdr.setHeader(true);
                    			      poaAccepterHdr.setColspan(22);
                    			      datatable.addCell(poaAccepterHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_LESSEE || roleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Lessee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			       }
                    			  if(roleId==RegInitConstant.ROLE_CLAIMANT_SELF || roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                        			  Cell claimantHdr=new Cell(new Phrase("Claimant "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  claimantHdr.setHeader(true);
                        			  claimantHdr.setColspan(22);
                    			      datatable.addCell(claimantHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                        			  Cell mortgageeHdr=new Cell(new Phrase("Mortgagee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  mortgageeHdr.setHeader(true);
                        			  mortgageeHdr.setColspan(22);
                    			      datatable.addCell(mortgageeHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_RELEASEE_SELF || roleId==RegInitConstant.ROLE_RELEASEE_POA_HOLDER){
                        			  Cell mortgageeHdr=new Cell(new Phrase("Releasee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  mortgageeHdr.setHeader(true);
                        			  mortgageeHdr.setColspan(22);
                    			      datatable.addCell(mortgageeHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_TRANSFEREE_SELF || roleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                        			  Cell mortgageeHdr=new Cell(new Phrase("Transferee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  mortgageeHdr.setHeader(true);
                        			  mortgageeHdr.setColspan(22);
                    			      datatable.addCell(mortgageeHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_AWARDEE_SELF || roleId==RegInitConstant.ROLE_AWARDEE_POA_HOLDER){
                        			  Cell mortgageeHdr=new Cell(new Phrase("Awardee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  mortgageeHdr.setHeader(true);
                        			  mortgageeHdr.setColspan(22);
                    			      datatable.addCell(mortgageeHdr);
                    			      }
                    			  if(roleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANS_PARTY_SELF){
                        			  Cell mortgageeHdr=new Cell(new Phrase("Transacting Party "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			  mortgageeHdr.setHeader(true);
                        			  mortgageeHdr.setColspan(22);
                    			      datatable.addCell(mortgageeHdr);
                    			      }
                    			  
                    			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  partyId.setHeader(true);
                    			  partyId.setColspan(8);
                			      datatable.addCell(partyId);
                			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      partyIdvalue.setHeader(true);
                			      partyIdvalue.setColspan(14);
                			      datatable.addCell(partyIdvalue);
                			  
                    			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  appType.setHeader(true);
                    			  appType.setColspan(8);
                			      datatable.addCell(appType);
                			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      appTypevalue.setHeader(true);
                			      appTypevalue.setColspan(14);
                			      datatable.addCell(appTypevalue);
                			      
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
                			    	  
                			    	  setCommonOrgOnPdf(mapDto,datatable);
                    			      
                    			      if(deedId==RegInitConstant.DEED_GIFT){
                    			    	  Cell rel=new Cell(new Phrase("Relation with owner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(8);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(14);
                    			      datatable.addCell(sharevalue);
                			    	  
                			      }
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
                			    	  
                			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  indName.setHeader(true);
                			    	  indName.setColspan(8);
                    			      datatable.addCell(indName);
                    			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      indNamevalue.setHeader(true);
                    			      indNamevalue.setColspan(14);
                    			      datatable.addCell(indNamevalue);
                    			      
                    			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      relation.setHeader(true);
                    			      relation.setColspan(5);
                    			      datatable.addCell(relation);
                    			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      relationvalue.setHeader(true);
                    			      relationvalue.setColspan(6);
                    			      datatable.addCell(relationvalue);
                    			   
                    			      
                    			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      fathername.setHeader(true);
                    			      fathername.setColspan(5);
                    			      datatable.addCell(fathername);
                    			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      fathernamevalue.setHeader(true);
                    			      fathernamevalue.setColspan(6);
                    			      datatable.addCell(fathernamevalue);
                    			      
                    			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      gender.setHeader(true);
                    			      gender.setColspan(5);
                    			      datatable.addCell(gender);
                    			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      gendervalue.setHeader(true);
                    			      gendervalue.setColspan(6);
                    			      datatable.addCell(gendervalue);
                    			      
                    			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      fname.setHeader(true);
                    			      fname.setColspan(5);
                    			      datatable.addCell(fname);
                    			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      fnamevalue.setHeader(true);
                    			      fnamevalue.setColspan(6);
                    			      datatable.addCell(fnamevalue);*/
                    			      
                    			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      dob.setHeader(true);
                    			      dob.setColspan(5);
                    			      datatable.addCell(dob);
                    			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      dobvalue.setHeader(true);
                    			      dobvalue.setColspan(6);
                    			      datatable.addCell(dobvalue);*/
                    			      
                    			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      dob.setHeader(true);
                    			      dob.setColspan(5);
                    			      datatable.addCell(dob);
                    			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      dobvalue.setHeader(true);
                    			      dobvalue.setColspan(6);
                    			      datatable.addCell(dobvalue);
                    			   
                    			      
                    			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      gname.setHeader(true);
                    			      gname.setColspan(5);
                    			      datatable.addCell(gname);
                    			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      gnamevalue.setHeader(true);
                    			      gnamevalue.setColspan(6);
                    			      datatable.addCell(gnamevalue);
                    			      
                    			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      sname.setHeader(true);
                    			      sname.setColspan(5);
                    			      datatable.addCell(sname);
                    			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      snamevalue.setHeader(true);
                    			      snamevalue.setColspan(6);
                    			      datatable.addCell(snamevalue);
                    			      
                    			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      email.setHeader(true);
                    			      email.setColspan(5);
                    			      datatable.addCell(email);
                    			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      emailvalue.setHeader(true);
                    			      emailvalue.setColspan(6);
                    			      datatable.addCell(emailvalue);
                    			      
                    			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      category.setHeader(true);
                    			      category.setColspan(5);
                    			      datatable.addCell(category);
                    			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      categoryvalue.setHeader(true);
                    			      categoryvalue.setColspan(6);
                    			      datatable.addCell(categoryvalue);
                    			      
                    			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
                    			    	  
                    			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  scheduleArea.setHeader(true);
                    			    	  scheduleArea.setColspan(8);
                        			      datatable.addCell(scheduleArea);
                        			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      scheduleAreavalue.setHeader(true);
                        			      scheduleAreavalue.setColspan(14);
                        			      datatable.addCell(scheduleAreavalue);
                        			      
                        			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                        			    	  
                        			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			    	  permissionNo.setHeader(true);
                        			    	  permissionNo.setColspan(5);
                            			      datatable.addCell(permissionNo);
                            			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      permissionNovalue.setHeader(true);
                            			      permissionNovalue.setColspan(6);
                            			      datatable.addCell(permissionNovalue);
                            			      
                            			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                            			      certificate.setHeader(true);
                            			      certificate.setColspan(5);
                            			      datatable.addCell(certificate);
                            			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      certificatevalue.setHeader(true);
                            			      certificatevalue.setColspan(6);
                            			      datatable.addCell(certificatevalue);
                        			    	  
                        			      }
                    			    	  
                    			      }
                    			      
                    			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      nationality.setHeader(true);
                    			      nationality.setColspan(5);
                    			      datatable.addCell(nationality);
                    			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      nationalityvalue.setHeader(true);
                    			      nationalityvalue.setColspan(6);
                    			      datatable.addCell(nationalityvalue);
                    			      
                    			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      disability.setHeader(true);
                    			      disability.setColspan(5);
                    			      datatable.addCell(disability);
                    			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      disabilityvalue.setHeader(true);
                    			      disabilityvalue.setColspan(6);
                    			      datatable.addCell(disabilityvalue);
                    			      
                    			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
                    			    	  
                    			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  disabilityDesc.setHeader(true);
                    			    	  disabilityDesc.setColspan(8);
                        			      datatable.addCell(disabilityDesc);
                        			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      disabilityDescvalue.setHeader(true);
                        			      disabilityDescvalue.setColspan(14);
                        			      datatable.addCell(disabilityDescvalue);
                    			    	  
                    			      }
                    			      
                    			     
                    			      
                    			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      nationality.setHeader(true);
                    			      nationality.setColspan(8);
                    			      datatable.addCell(nationality);
                    			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      nationalityvalue.setHeader(true);
                    			      nationalityvalue.setColspan(14);
                    			      datatable.addCell(nationalityvalue);*/
                    			      
                    			      
                    			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      minority.setHeader(true);
                    			      minority.setColspan(5);
                    			      datatable.addCell(minority);
                    			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      minorityvalue.setHeader(true);
                    			      minorityvalue.setColspan(6);
                    			      datatable.addCell(minorityvalue);
                    			      
                    			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      poverty.setHeader(true);
                    			      poverty.setColspan(5);
                    			      datatable.addCell(poverty);
                    			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      povertyvalue.setHeader(true);
                    			      povertyvalue.setColspan(6);
                    			      datatable.addCell(povertyvalue);
                    			      
                    			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      phNo.setHeader(true);
                    			      phNo.setColspan(5);
                    			      datatable.addCell(phNo);
                    			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      phNovalue.setHeader(true);
                    			      phNovalue.setColspan(6);
                    			      datatable.addCell(phNovalue);
                    			      
                    			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      mobNo.setHeader(true);
                    			      mobNo.setColspan(5);
                    			      datatable.addCell(mobNo);
                    			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      mobNovalue.setHeader(true);
                    			      mobNovalue.setColspan(6);
                    			      datatable.addCell(mobNovalue);
                    			      
                    			      
                    			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      address.setHeader(true);
                    			      address.setColspan(8);
                    			      datatable.addCell(address);
                    			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      addressvalue.setHeader(true);
                    			      addressvalue.setColspan(14);
                    			      datatable.addCell(addressvalue);
                    			      
                    			      Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      postal.setHeader(true);
                    			      postal.setColspan(5);
                    			      datatable.addCell(postal);
                    			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      postalvalue.setHeader(true);
                    			      postalvalue.setColspan(6);
                    			      datatable.addCell(postalvalue);
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(5);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(6);
                    			      datatable.addCell(sharevalue);
                    			      
                    			      if(deedId==RegInitConstant.DEED_GIFT){
                    			    	  Cell rel=new Cell(new Phrase("Relation with owner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }
                    			      
                    			     
                    			      
                    			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      occupation.setHeader(true);
                    			      occupation.setColspan(8);
                    			      datatable.addCell(occupation);
                    			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      occupationvalue.setHeader(true);
                    			      occupationvalue.setColspan(14);
                    			      datatable.addCell(occupationvalue);
                    			      
                    			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proof.setHeader(true);
                    			      proof.setColspan(5);
                    			      datatable.addCell(proof);
                    			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofvalue.setHeader(true);
                    			      proofvalue.setColspan(6);
                    			      datatable.addCell(proofvalue);
                    			      
                    			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proofId.setHeader(true);
                    			      proofId.setColspan(5);
                    			      datatable.addCell(proofId);
                    			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofIdvalue.setHeader(true);
                    			      proofIdvalue.setColspan(6);
                    			      datatable.addCell(proofIdvalue);
                    			      
                    			 
                			    	  
                			      }
                			      
                			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    		                   		upl2.setHeader(true);
    		                   		upl2.setColspan(8);
    		                   		datatable.addCell(upl2);
    		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    		                   		upl2value.setHeader(true);
    		                   		upl2value.setColspan(14);
    		                   		datatable.addCell(upl2value);
    		                   
    	                    		//BELOW CODE FOR CLAIMANT
    	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
    	                    		{
    	                    			
    	                    			Cell upl8=new Cell(new Phrase("CLAIMANT PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    	                    			upl8.setHeader(true);
    	                    			upl8.setColspan(8);
                    			        datatable.addCell(upl8);
                    			        Cell upl8value=new Cell(new Phrase(mapDto.getU8DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			        upl8value.setHeader(true);
                    			        upl8value.setColspan(14);
                    			        datatable.addCell(upl8value);
                    			      
                    			      if(!mapDto.getSelectedPhotoId().equalsIgnoreCase("4")){
                    			      Cell upl11=new Cell(new Phrase("PAN OR FORM 60/61", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      upl11.setHeader(true);
                    			      upl11.setColspan(8);
                    			      datatable.addCell(upl11);
                    			      Cell upl11value=new Cell(new Phrase(mapDto.getU11DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      upl11value.setHeader(true);
                    			      upl11value.setColspan(14);
                    			      datatable.addCell(upl11value);
                    			      }	
    	                    			
    	                    			
    			                   		
    	                    		}
    		                   		
    	                    		//BELOW CODE FOR CLAIMANT POA HOLDER
    	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
    	                    		{
    		                   		
    	                    			Cell upl9=new Cell(new Phrase("ATTORNEY PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    	                    			upl9.setHeader(true);
    	                    			upl9.setColspan(8);
                      			      datatable.addCell(upl9);
                      			      Cell upl9value=new Cell(new Phrase(mapDto.getU9DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                      			      upl9value.setHeader(true);
                      			      upl9value.setColspan(14);
                      			      datatable.addCell(upl9value);
    		                   		
    		                   		
    		                   		
    	                    		}
                			      
                			      
                			    //OWNER DETAILS IN CASE OF POA BELOW
                		  
                			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
                 			      {
                			    	  
                			    	  setOwnerOnPdf(mapDto,datatable);
                			    	  /*
                			    	  
                			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
                			    	  ownerHdr.setHeader(true);
                			    	  ownerHdr.setColspan(22);
                    			      datatable.addCell(ownerHdr);
                			    	  
                			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  ownerName.setHeader(true);
                			    	  ownerName.setColspan(5);
                    			      datatable.addCell(ownerName);
                    			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNamevalue.setHeader(true);
                    			      ownerNamevalue.setColspan(6);
                    			      datatable.addCell(ownerNamevalue);
                    			      
                    			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerOrgName.setHeader(true);
                    			      ownerOrgName.setColspan(5);
                    			      datatable.addCell(ownerOrgName);
                    			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerOrgNamevalue.setHeader(true);
                    			      ownerOrgNamevalue.setColspan(6);
                    			      datatable.addCell(ownerOrgNamevalue);
                    			      
                    			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerGender.setHeader(true);
                    			      ownerGender.setColspan(5);
                    			      datatable.addCell(ownerGender);
                    			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerGendervalue.setHeader(true);
                    			      ownerGendervalue.setColspan(6);
                    			      datatable.addCell(ownerGendervalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerNationality.setHeader(true);
                    			      ownerNationality.setColspan(8);
                    			      datatable.addCell(ownerNationality);
                    			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNationalityvalue.setHeader(true);
                    			      ownerNationalityvalue.setColspan(14);
                    			      datatable.addCell(ownerNationalityvalue);
                    			      
                    			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerAddress.setHeader(true);
                    			      ownerAddress.setColspan(8);
                    			      datatable.addCell(ownerAddress);
                    			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerAddressvalue.setHeader(true);
                    			      ownerAddressvalue.setColspan(14);
                    			      datatable.addCell(ownerAddressvalue);
                    			      
                    			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerMobile.setHeader(true);
                    			      ownerMobile.setColspan(5);
                    			      datatable.addCell(ownerMobile);
                    			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerMobilevalue.setHeader(true);
                    			      ownerMobilevalue.setColspan(6);
                    			      datatable.addCell(ownerMobilevalue);
                    			      
                    			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerEmail.setHeader(true);
                    			      ownerEmail.setColspan(5);
                    			      datatable.addCell(ownerEmail);
                    			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerEmailvalue.setHeader(true);
                    			      ownerEmailvalue.setColspan(6);
                    			      datatable.addCell(ownerEmailvalue);
                    			      
                    			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProof.setHeader(true);
                    			      ownerProof.setColspan(5);
                    			      datatable.addCell(ownerProof);
                    			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofvalue.setHeader(true);
                    			      ownerProofvalue.setColspan(6);
                    			      datatable.addCell(ownerProofvalue);
                    			      
                    			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProofId.setHeader(true);
                    			      ownerProofId.setColspan(5);
                    			      datatable.addCell(ownerProofId);
                    			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofIdvalue.setHeader(true);
                    			      ownerProofIdvalue.setColspan(6);
                    			      datatable.addCell(ownerProofIdvalue);
                			    	  
                			    	  
                 			    	  
                 			      */}
                		  
                		  
                		  
                		  
                		  
                		  
                		  
                		  
                	  }
                		  
                		  
                		  
                	  }
                	  
                	  }
            	  
            	  //WRITING PROPERTY DETAILS ON PDF
            	  
            	  datatable.addCell(row);
            	  Cell propertyHdr=new Cell(new Phrase("Property Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            	  propertyHdr.setHeader(true);
            	  propertyHdr.setColspan(22);
			      datatable.addCell(propertyHdr);
            	  
            	  Cell propertyId=new Cell(new Phrase("Property Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  propertyId.setHeader(true);
            	  propertyId.setColspan(8);
            	  //propertyId.setBackgroundColor(new Color(255,130,171));
			      datatable.addCell(propertyId);
			      Cell propertyIdvalue=new Cell(new Phrase(dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propertyIdvalue.setHeader(true);
			      propertyIdvalue.setColspan(14);
			      //propertyIdvalue.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(propertyIdvalue);
            	  
			      if(dto.getFloorCount()==0){
			      Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(obj.format(dto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
			      }
			      Cell guideline=new Cell(new Phrase("Current guideline value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      guideline.setHeader(true);
			      guideline.setColspan(8);
            	  datatable.addCell(guideline);
			      Cell guidelinevalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto.getGuidelineValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      guidelinevalue.setHeader(true);
			      guidelinevalue.setColspan(14);
			      datatable.addCell(guidelinevalue);
			      
			      Cell system=new Cell(new Phrase("System generated valuation (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      system.setHeader(true);
			      system.setColspan(8);
            	  datatable.addCell(system);
			      Cell systemvalue=new Cell(new Phrase(obj.format(dto.getSystemMV()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      systemvalue.setHeader(true);
			      systemvalue.setColspan(14);
			      datatable.addCell(systemvalue);
			      
			      Cell mv=new Cell(new Phrase("Market Value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      mv.setHeader(true);
			      mv.setColspan(8);
            	  datatable.addCell(mv);
			      Cell mvvalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto.getMarketValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      mvvalue.setHeader(true);
			      mvvalue.setColspan(14);
			      datatable.addCell(mvvalue);
			      
			      /*Cell propType=new Cell(new Phrase("Property Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propType.setHeader(true);
			      propType.setColspan(8);
            	  datatable.addCell(propType);
			      Cell propTypevalue=new Cell(new Phrase(dto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propTypevalue.setHeader(true);
			      propTypevalue.setColspan(14);
			      datatable.addCell(propTypevalue);
            	  
			      Cell propAddress=new Cell(new Phrase("Property Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propAddress.setHeader(true);
			      propAddress.setColspan(8);
            	  datatable.addCell(propAddress);
			      Cell propAddressvalue=new Cell(new Phrase(dto.getPropAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAddressvalue.setHeader(true);
			      propAddressvalue.setColspan(14);
			      datatable.addCell(propAddressvalue);
			      
			      Cell propLandmark=new Cell(new Phrase("Property Landmark", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propLandmark.setHeader(true);
			      propLandmark.setColspan(8);
            	  datatable.addCell(propLandmark);
			      Cell propLandmarkvalue=new Cell(new Phrase(dto.getPropLandmark(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propLandmarkvalue.setHeader(true);
			      propLandmarkvalue.setColspan(14);
			      datatable.addCell(propLandmarkvalue);
			      
			      Cell propMohl=new Cell(new Phrase("Mohalla/Colony name/Society/Road/Gram", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propMohl.setHeader(true);
			      propMohl.setColspan(5);
            	  datatable.addCell(propMohl);
			      Cell propMohlvalue=new Cell(new Phrase(dto.getMohallaName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propMohlvalue.setHeader(true);
			      propMohlvalue.setColspan(6);
			      datatable.addCell(propMohlvalue);
			      
			      Cell propWarkPatwari=new Cell(new Phrase("Ward / Patwari Halka", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWarkPatwari.setHeader(true);
			      propWarkPatwari.setColspan(5);
            	  datatable.addCell(propWarkPatwari);
			      Cell propWarkPatwarivalue=new Cell(new Phrase(dto.getPatwariStatus(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWarkPatwarivalue.setHeader(true);
			      propWarkPatwarivalue.setColspan(6);
			      datatable.addCell(propWarkPatwarivalue);
			      
			      Cell propWarkPatwariNo=new Cell(new Phrase("ward Number/Patwari Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWarkPatwariNo.setHeader(true);
			      propWarkPatwariNo.setColspan(5);
            	  datatable.addCell(propWarkPatwariNo);
			      Cell propWarkPatwariNovalue=new Cell(new Phrase(dto.getWardpatwarName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWarkPatwariNovalue.setHeader(true);
			      propWarkPatwariNovalue.setColspan(6);
			      datatable.addCell(propWarkPatwariNovalue);
			      
			      Cell propGovBody=new Cell(new Phrase("Governing Municipal Body", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propGovBody.setHeader(true);
			      propGovBody.setColspan(5);
            	  datatable.addCell(propGovBody);
			      Cell propGovBodyvalue=new Cell(new Phrase(dto.getMunicipalBodyName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propGovBodyvalue.setHeader(true);
			      propGovBodyvalue.setColspan(6);
			      datatable.addCell(propGovBodyvalue);
			      
			      Cell propAreaType=new Cell(new Phrase("Type of Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propAreaType.setHeader(true);
			      propAreaType.setColspan(8);
            	  datatable.addCell(propAreaType);
			      Cell propAreaTypevalue=new Cell(new Phrase(dto.getAreaTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAreaTypevalue.setHeader(true);
			      propAreaTypevalue.setColspan(14);
			      datatable.addCell(propAreaTypevalue);
			      
			      Cell propTehsil=new Cell(new Phrase("Tehsil", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propTehsil.setHeader(true);
			      propTehsil.setColspan(5);
            	  datatable.addCell(propTehsil);
			      Cell propTehsilvalue=new Cell(new Phrase(dto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propTehsilvalue.setHeader(true);
			      propTehsilvalue.setColspan(6);
			      datatable.addCell(propTehsilvalue);
			      
			      Cell propDist=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propDist.setHeader(true);
			      propDist.setColspan(5);
            	  datatable.addCell(propDist);
			      Cell propDistvalue=new Cell(new Phrase(dto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propDistvalue.setHeader(true);
			      propDistvalue.setColspan(6);
			      datatable.addCell(propDistvalue);
			      
			      Cell propVikas=new Cell(new Phrase("Vikas Khand (development block)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propVikas.setHeader(true);
			      propVikas.setColspan(5);
            	  datatable.addCell(propVikas);
			      Cell propVikasvalue=new Cell(new Phrase(dto.getVikasId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propVikasvalue.setHeader(true);
			      propVikasvalue.setColspan(6);
			      datatable.addCell(propVikasvalue);
			      
			      Cell propRi=new Cell(new Phrase("R. I. Circle", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propRi.setHeader(true);
			      propRi.setColspan(5);
            	  datatable.addCell(propRi);
			      Cell propRivalue=new Cell(new Phrase(dto.getRicircle(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propRivalue.setHeader(true);
			      propRivalue.setColspan(6);
			      datatable.addCell(propRivalue);
			      
			      Cell propLayout=new Cell(new Phrase("Layout Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propLayout.setHeader(true);
			      propLayout.setColspan(5);
            	  datatable.addCell(propLayout);
			      Cell propLayoutvalue=new Cell(new Phrase(dto.getLayoutdet(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propLayoutvalue.setHeader(true);
			      propLayoutvalue.setColspan(6);
			      datatable.addCell(propLayoutvalue);
			      
			      Cell propNazool=new Cell(new Phrase("Nazool/Sheet Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNazool.setHeader(true);
			      propNazool.setColspan(5);
            	  datatable.addCell(propNazool);
			      Cell propNazoolvalue=new Cell(new Phrase(dto.getSheetnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propNazoolvalue.setHeader(true);
			      propNazoolvalue.setColspan(6);
			      datatable.addCell(propNazoolvalue);
			      
			      Cell propPlotNo=new Cell(new Phrase("Plot Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propPlotNo.setHeader(true);
			      propPlotNo.setColspan(8);
            	  datatable.addCell(propPlotNo);
			      Cell propPlotNovalue=new Cell(new Phrase(dto.getPlotnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propPlotNovalue.setHeader(true);
			      propPlotNovalue.setColspan(14);
			      datatable.addCell(propPlotNovalue);
			      
			      Cell khasraHdr=new Cell(new Phrase("Khasra Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      khasraHdr.setHeader(true);
			      khasraHdr.setColspan(22);
			      datatable.addCell(khasraHdr);
            	  
			      //Sr.No. Khasra Number  Area (sq mtr) Lagaan/Land Revenue(INR) RIN Pustika Number  
            	  
			      Cell srNo=new Cell(new Phrase("Sr.No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      srNo.setHeader(true);
			      srNo.setColspan(2);
            	  datatable.addCell(srNo);
            	  
            	  Cell khasraNo=new Cell(new Phrase("Khasra Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  khasraNo.setHeader(true);
            	  khasraNo.setColspan(5);
            	  datatable.addCell(khasraNo);
            	  
            	  Cell khasraArea=new Cell(new Phrase("Khasra Area ("+dto.getUnit()+")", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  khasraArea.setHeader(true);
            	  khasraArea.setColspan(5);
            	  datatable.addCell(khasraArea);
            	  
            	  Cell lagaan=new Cell(new Phrase("Lagaan/Land Revenue(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  lagaan.setHeader(true);
            	  lagaan.setColspan(5);
            	  datatable.addCell(lagaan);
            	  
            	  Cell rinPustika=new Cell(new Phrase("RIN Pustika Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  rinPustika.setHeader(true);
            	  rinPustika.setColspan(5);
            	  datatable.addCell(rinPustika);
            	  
            	  if(dto.getKhasraDetlsDisplay()!=null && dto.getKhasraDetlsDisplay().size()>0){
            		  CommonDTO objDto;
            	  
            	      for(int m=0;m<dto.getKhasraDetlsDisplay().size();m++){
            	    	  objDto=(CommonDTO)dto.getKhasraDetlsDisplay().get(m);
            	    	  
            	    	  Cell srNovalue=new Cell(new Phrase(""+(m+1)+"", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	    	  srNovalue.setHeader(true);
            	    	  srNovalue.setColspan(2);
                    	  datatable.addCell(srNovalue);
                    	  
                    	  Cell khasraNovalue=new Cell(new Phrase(objDto.getKhasraNum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  khasraNovalue.setHeader(true);
                    	  khasraNovalue.setColspan(5);
                    	  datatable.addCell(khasraNovalue);
                    	  
                    	  Cell khasraAreavalue=new Cell(new Phrase(objDto.getKhasraArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  khasraAreavalue.setHeader(true);
                    	  khasraAreavalue.setColspan(5);
                    	  datatable.addCell(khasraAreavalue);
                    	  
                    	  Cell lagaanvalue=new Cell(new Phrase(objDto.getLagaan(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  lagaanvalue.setHeader(true);
                    	  lagaanvalue.setColspan(5);
                    	  datatable.addCell(lagaanvalue);
                    	  
                    	  Cell rinPustikavalue=new Cell(new Phrase(objDto.getRinPustika(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  rinPustikavalue.setHeader(true);
                    	  rinPustikavalue.setColspan(5);
                    	  datatable.addCell(rinPustikavalue);
            	    	  
            	    	  
            	    	  
            	      }
            	  }
            	  
            	  Cell fourBoundryHdr=new Cell(new Phrase("Four Boundary Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  fourBoundryHdr.setHeader(true);
            	  fourBoundryHdr.setColspan(22);
			      datatable.addCell(fourBoundryHdr);
			      
			      Cell propNorth=new Cell(new Phrase("North", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNorth.setHeader(true);
			      propNorth.setColspan(5);
            	  datatable.addCell(propNorth);
			      Cell propNorthvalue=new Cell(new Phrase(dto.getNorth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propNorthvalue.setHeader(true);
			      propNorthvalue.setColspan(6);
			      datatable.addCell(propNorthvalue);
			      
			      Cell propSouth=new Cell(new Phrase("South", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propSouth.setHeader(true);
			      propSouth.setColspan(5);
            	  datatable.addCell(propSouth);
			      Cell propSouthvalue=new Cell(new Phrase(dto.getSouth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propSouthvalue.setHeader(true);
			      propSouthvalue.setColspan(6);
			      datatable.addCell(propSouthvalue);
			      
			      Cell propEast=new Cell(new Phrase("East", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propEast.setHeader(true);
			      propEast.setColspan(5);
            	  datatable.addCell(propEast);
			      Cell propEastvalue=new Cell(new Phrase(dto.getEast(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propEastvalue.setHeader(true);
			      propEastvalue.setColspan(6);
			      datatable.addCell(propEastvalue);
			      
			      Cell propWest=new Cell(new Phrase("West", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWest.setHeader(true);
			      propWest.setColspan(5);
            	  datatable.addCell(propWest);
			      Cell propWestvalue=new Cell(new Phrase(dto.getWest(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWestvalue.setHeader(true);
			      propWestvalue.setColspan(6);
			      datatable.addCell(propWestvalue);
			      

			      datatable.addCell(row);
			        
			      if(dto.getFloorCount()==0){
			      String area=Double.toString(dto.getTotalSqMeter());
			      
			      Cell propArea=new Cell(new Phrase("Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propArea.setHeader(true);
			      propArea.setColspan(8);
            	  datatable.addCell(propArea);
			      Cell propAreavalue=new Cell(new Phrase(area+" "+dto.getUnit(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAreavalue.setHeader(true);
			      propAreavalue.setColspan(14);
			      datatable.addCell(propAreavalue);
			      }
			      
			      Cell propNoFloor=new Cell(new Phrase("Number of Constructed Floors", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNoFloor.setHeader(true);
			      propNoFloor.setColspan(8);
            	  datatable.addCell(propNoFloor);
			      if(dto.getFloorCount()==0){
			    	  Cell propNoFloorvalue=new Cell(new Phrase(RegInitConstant.NOT_APPLICABLE, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNoFloorvalue.setHeader(true);
				      propNoFloorvalue.setColspan(14);
				      datatable.addCell(propNoFloorvalue);
			      }else{
			    	  Cell propNoFloorvalue=new Cell(new Phrase(Integer.toString(dto.getFloorCount()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNoFloorvalue.setHeader(true);
				      propNoFloorvalue.setColspan(14);
				      datatable.addCell(propNoFloorvalue);
			      }
			      
			      
			      
			      if(dto.getFloorCount()==0){
			    	  
			    	  //subclause non building
			    	  if(dto.getSelectedSubClauseList()!=null && dto.getSelectedSubClauseList().size()>0){
			    		  
			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    		  subClauseHdr.setHeader(true);
			    		  subClauseHdr.setColspan(22);
        			      datatable.addCell(subClauseHdr);
        			      
        			     for(int n=0;n<dto.getSelectedSubClauseList().size();n++){
        			    	 
        			    	 CommonDTO subClausedto = new CommonDTO();
        			    	 subClausedto=(CommonDTO)dto.getSelectedSubClauseList().get(n);
        			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			    	 subClauseValue.setHeader(true);
        			    	 subClauseValue.setColspan(22);
           			         datatable.addCell(subClauseValue);
        			    	 
        			    	 
        			    	 
        			     }
			    		  
			    		  
			    	  }
			    	  
			    	  
			    	  
			    	  
			    	  
			      }else if(dto.getFloorCount()>0){
			    	  //floor details
			    	  
			    	  	if(dto.getMapBuilding()!=null && dto.getMapBuilding().size()>0){
			    	  		
			    	  		Cell floorHdr=new Cell(new Phrase("Floor Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    	  		floorHdr.setHeader(true);
			    	  		floorHdr.setColspan(22);
          			      datatable.addCell(floorHdr);
			    	  		Collection mapCollection2=dto.getMapBuilding().values();
          			      Object[] l2=mapCollection2.toArray();
                            
			    	  		RegCompletDTO floordto;
			    		   for(int p=0;p<l2.length;p++){
        			    	 
			    			   floordto = new RegCompletDTO();
			    			   floordto=(RegCompletDTO)l2[p];
        			    	 
        			    	 Cell buildingType=new Cell(new Phrase("Building Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			    	 buildingType.setHeader(true);
        			    	 buildingType.setColspan(5);
           			      	 datatable.addCell(buildingType);
        			    	 Cell buildingTypeValue=new Cell(new Phrase(floordto.getUsageBuilding(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			    	 buildingTypeValue.setHeader(true);
        			    	 buildingTypeValue.setColspan(6);
           			         datatable.addCell(buildingTypeValue);
           			         
           			         Cell roofType=new Cell(new Phrase("Roof Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
           			         roofType.setHeader(true);
           			         roofType.setColspan(5);
           			         datatable.addCell(roofType);
           			         Cell roofTypeValue=new Cell(new Phrase(floordto.getCeilingType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
           			         roofTypeValue.setHeader(true);
           			         roofTypeValue.setColspan(6);
           			         datatable.addCell(roofTypeValue);
        			      
           			         Cell floorType=new Cell(new Phrase("Floor Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
           			         floorType.setHeader(true);
           			         floorType.setColspan(5);
        			      	 datatable.addCell(floorType);
        			      	 Cell floorTypeValue=new Cell(new Phrase(floordto.getTypeFloorDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			      	 floorTypeValue.setHeader(true);
        			      	 floorTypeValue.setColspan(6);
        			         datatable.addCell(floorTypeValue);
        			         
        			         Cell consdAmt=new Cell(new Phrase("Consideration Amount (Rs.)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         consdAmt.setHeader(true);
        			         consdAmt.setColspan(5);
        			         datatable.addCell(consdAmt);
        			         Cell consdAmtValue=new Cell(new Phrase(Double.toString(floordto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         consdAmtValue.setHeader(true);
        			         consdAmtValue.setColspan(6);
        			         datatable.addCell(consdAmtValue);
     			      
        			         Cell floorTotArea=new Cell(new Phrase("Total Area (Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         floorTotArea.setHeader(true);
        			         floorTotArea.setColspan(5);
        			         datatable.addCell(floorTotArea);
        			         Cell floorTotAreaValue=new Cell(new Phrase(Double.toString(floordto.getTotalSqMeter()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         floorTotAreaValue.setHeader(true);
        			         floorTotAreaValue.setColspan(6);
        			         datatable.addCell(floorTotAreaValue);
   			         
        			         Cell floorConstArea=new Cell(new Phrase("Constructed Area(Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         floorConstArea.setHeader(true);
        			         floorConstArea.setColspan(5);
        			         datatable.addCell(floorConstArea);
        			         Cell floorConstAreaValue=new Cell(new Phrase(floordto.getConstructedArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         floorConstAreaValue.setHeader(true);
        			         floorConstAreaValue.setColspan(6);
        			         datatable.addCell(floorConstAreaValue);
        			         
        			         if(floordto.getSelectedSubClauseList()!=null && floordto.getSelectedSubClauseList().size()>0){
       			    		  
       			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
       			    		  subClauseHdr.setHeader(true);
       			    		  subClauseHdr.setColspan(22);
               			      datatable.addCell(subClauseHdr);
               			      
               			     for(int f=0;f<floordto.getSelectedSubClauseList().size();f++){
               			    	 
               			    	 CommonDTO subClausedto2 = new CommonDTO();
               			    	 subClausedto2=(CommonDTO)floordto.getSelectedSubClauseList().get(f);
               			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto2.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
               			    	 subClauseValue.setHeader(true);
               			    	 subClauseValue.setColspan(22);
                  			         datatable.addCell(subClauseValue);
               			    	 
               			    	 
               			    	 
               			     }
       			    		  
       			    		  
       			    	  }
        			          
        			    	 
        			    	 
        			    	 
        			     }
			    		  
			    		  
			    	  }
			    	  
			      }
			      */
			      setPropertyOnPdf(dto,datatable);
			      //property related attachments
			      
			      Cell uplAngle1=new Cell(new Phrase("ANGLE 1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      uplAngle1.setHeader(true);
			      uplAngle1.setColspan(8);
			        datatable.addCell(uplAngle1);
			        Cell uplAngle1value=new Cell(new Phrase(dto.getPropImage1DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplAngle1value.setHeader(true);
			        uplAngle1value.setColspan(14);
			        datatable.addCell(uplAngle1value);
			        
			        Cell uplAngle2=new Cell(new Phrase("ANGLE 2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			      uplAngle2.setHeader(true);
  			      uplAngle2.setColspan(8);
  			        datatable.addCell(uplAngle2);
  			        Cell uplAngle2value=new Cell(new Phrase(dto.getPropImage2DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplAngle2value.setHeader(true);
  			        uplAngle2value.setColspan(14);
  			        datatable.addCell(uplAngle2value);
  			        
  			      Cell uplAngle3=new Cell(new Phrase("ANGLE 3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      uplAngle3.setHeader(true);
			      uplAngle3.setColspan(8);
			        datatable.addCell(uplAngle3);
			        Cell uplAngle3value=new Cell(new Phrase(dto.getPropImage3DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplAngle3value.setHeader(true);
			        uplAngle3value.setColspan(14);
			        datatable.addCell(uplAngle3value);
			        
			        Cell uplMap=new Cell(new Phrase("Property Map as per rules", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			        uplMap.setHeader(true);
			        uplMap.setColspan(8);
  			        datatable.addCell(uplMap);
  			        Cell uplMapvalue=new Cell(new Phrase(dto.getPropMapDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplMapvalue.setHeader(true);
  			        uplMapvalue.setColspan(14);
  			        datatable.addCell(uplMapvalue);
  			        
  			      if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
  				{
  			    	  
  			    	Cell uplRin=new Cell(new Phrase("RIN Pustika for Agriculture Land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			    	uplRin.setHeader(true);
  			    	uplRin.setColspan(8);
  			        datatable.addCell(uplRin);
  			        Cell uplRinvalue=new Cell(new Phrase(dto.getPropRinDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplRinvalue.setHeader(true);
  			        uplRinvalue.setColspan(14);
  			        datatable.addCell(uplRinvalue);
  			        
  			      Cell uplKhasra=new Cell(new Phrase("Computerized Khasra of 1 year certified by revenue official for agricultural land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			      uplKhasra.setHeader(true);
  			      uplKhasra.setColspan(8);
			        datatable.addCell(uplKhasra);
			        Cell uplKhasravalue=new Cell(new Phrase(dto.getPropKhasraDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplKhasravalue.setHeader(true);
			        uplKhasravalue.setColspan(14);
			        datatable.addCell(uplKhasravalue);
			        
  				
  				}
            	  
            	  
              }
		      
		      
		      
	}
		      else{
		
		Cell noDataFound=new Cell(new Phrase("No data found", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		noDataFound.setHeader(true);
		noDataFound.setColspan(22);
	      //balanceon.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(noDataFound);
	      
	      datatable.addCell(row);
		
	}
		      
	}	      
		      
		      
		      //below code for creating pdf for Exchange deed
		      
		      if(deedId==RegInitConstant.DEED_EXCHANGE ||
		    		  deedId==RegInitConstant.DEED_PARTITION_PV)
		      {

			      
			      Cell propPartyHeader=new Cell(new Phrase("Party Wise Property Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			      propPartyHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			      propPartyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(propPartyHeader);
			      datatable.setAlignment(2);  
			      
			      
			      HashMap pdfMap=getExchngDeedPropPartyDetlsForPDF(regForm.getHidnRegTxnId(),regForm.getDeedID(),languageLocale);
			
			      if(pdfMap!=null){
			      /*Collection mapCollection=pdfMap.keySet();
			      Object[] l1=mapCollection.toArray();*/
                  RegCompletDTO dto=new RegCompletDTO();
                 ArrayList partyList;
                 ArrayList propList;
                 
                 Set mapSet = (Set)pdfMap.entrySet();

                 Iterator mapIterator = mapSet.iterator();


                 
               //below loop for property details
                 while(mapIterator.hasNext())


                  {
                	 
                	 Map.Entry mapEntry = (Map.Entry)mapIterator.next();
                	 int key =  (Integer)mapEntry.getKey();



                	  System.out.println("key----->"+key);
                	  dto=(RegCompletDTO)mapEntry.getValue();
                	  
                	  if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE)
                	  {
                	  Cell partyHdr=new Cell(new Phrase("Party "+(key), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  partyHdr.setHeader(true);
                	  partyHdr.setColspan(22);
    			      datatable.addCell(partyHdr);
                	  }
    			      
                	  
                	  if(dto.getPartyListPdf()!=null && dto.getPartyListPdf().size()>0){
                    	  partyList=dto.getPartyListPdf();
                    	  RegCommonDTO mapDto =new RegCommonDTO();
                    	  //below loop for party details
                    	  
                    	  
        			      int sellerCount=0;
        			      int buyerCount=0;
        			      
                    	  for(int j=0;j<partyList.size();j++){                          //seller/owner loop
                    		  
                    		  mapDto=(RegCommonDTO)partyList.get(j);
                    		  //WRITING PARTY DETAILS ON PDF
                    		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                    		  //int claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(roleId)));
                    		  
                    		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
                       		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                    		  
                    		  
                    		  if(roleId==RegInitConstant.ROLE1_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE2_OWNER_POA_HOLDER ||
                    				  roleId==RegInitConstant.ROLE1_OWNER_SELF || roleId==RegInitConstant.ROLE2_OWNER_SELF ||
                    				  roleId==RegInitConstant.ROLE_OWNER_SELF || roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER)
                    		  {
                    			  datatable.addCell(row);
                    			  sellerCount++;
                    		
                    			  Cell ownerHdr1=new Cell(new Phrase("Owner "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  ownerHdr1.setHeader(true);
                    			  ownerHdr1.setColspan(22);
                			      datatable.addCell(ownerHdr1);
                			      
                    			  
                    			  
                			      Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      partyId.setHeader(true);
                			      partyId.setColspan(8);
                			      datatable.addCell(partyId);
                			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      partyIdvalue.setHeader(true);
                			      partyIdvalue.setColspan(14);
                			      datatable.addCell(partyIdvalue);
                    			 
                    			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  appType.setHeader(true);
                    			  appType.setColspan(8);
                			      datatable.addCell(appType);
                			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      appTypevalue.setHeader(true);
                			      appTypevalue.setColspan(14);
                			      datatable.addCell(appTypevalue);
                			      
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
                			    	  
                			    	  setCommonOrgOnPdf(mapDto,datatable);
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(8);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(14);
                    			      datatable.addCell(sharevalue);
                			    	  
                			      }
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
                			    	  
                			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  indName.setHeader(true);
                			    	  indName.setColspan(8);
                    			      datatable.addCell(indName);
                    			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      indNamevalue.setHeader(true);
                    			      indNamevalue.setColspan(14);
                    			      datatable.addCell(indNamevalue);
                    			      
                    			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      relation.setHeader(true);
                    			      relation.setColspan(5);
                    			      datatable.addCell(relation);
                    			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      relationvalue.setHeader(true);
                    			      relationvalue.setColspan(6);
                    			      datatable.addCell(relationvalue);
                    			   
                    			      
                    			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      fathername.setHeader(true);
                    			      fathername.setColspan(5);
                    			      datatable.addCell(fathername);
                    			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      fathernamevalue.setHeader(true);
                    			      fathernamevalue.setColspan(6);
                    			      datatable.addCell(fathernamevalue);
                    			      
                    			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      gender.setHeader(true);
                    			      gender.setColspan(5);
                    			      datatable.addCell(gender);
                    			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      gendervalue.setHeader(true);
                    			      gendervalue.setColspan(6);
                    			      datatable.addCell(gendervalue);
                    			      
                    			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      fname.setHeader(true);
                    			      fname.setColspan(5);
                    			      datatable.addCell(fname);
                    			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      fnamevalue.setHeader(true);
                    			      fnamevalue.setColspan(6);
                    			      datatable.addCell(fnamevalue);*/
                    			      
                    			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      dob.setHeader(true);
                    			      dob.setColspan(5);
                    			      datatable.addCell(dob);
                    			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      dobvalue.setHeader(true);
                    			      dobvalue.setColspan(6);
                    			      datatable.addCell(dobvalue);*/
                    			      
                    			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      dob.setHeader(true);
                    			      dob.setColspan(5);
                    			      datatable.addCell(dob);
                    			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      dobvalue.setHeader(true);
                    			      dobvalue.setColspan(6);
                    			      datatable.addCell(dobvalue);
                    			   
                    			      
                    			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      gname.setHeader(true);
                    			      gname.setColspan(5);
                    			      datatable.addCell(gname);
                    			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      gnamevalue.setHeader(true);
                    			      gnamevalue.setColspan(6);
                    			      datatable.addCell(gnamevalue);
                    			      
                    			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      sname.setHeader(true);
                    			      sname.setColspan(5);
                    			      datatable.addCell(sname);
                    			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      snamevalue.setHeader(true);
                    			      snamevalue.setColspan(6);
                    			      datatable.addCell(snamevalue);
                    			      
                    			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      email.setHeader(true);
                    			      email.setColspan(5);
                    			      datatable.addCell(email);
                    			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      emailvalue.setHeader(true);
                    			      emailvalue.setColspan(6);
                    			      datatable.addCell(emailvalue);
                    			      
                    			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      category.setHeader(true);
                    			      category.setColspan(5);
                    			      datatable.addCell(category);
                    			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      categoryvalue.setHeader(true);
                    			      categoryvalue.setColspan(6);
                    			      datatable.addCell(categoryvalue);
                    			      
                    			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
                    			    	  
                    			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  scheduleArea.setHeader(true);
                    			    	  scheduleArea.setColspan(8);
                        			      datatable.addCell(scheduleArea);
                        			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      scheduleAreavalue.setHeader(true);
                        			      scheduleAreavalue.setColspan(14);
                        			      datatable.addCell(scheduleAreavalue);
                        			      
                        			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                        			    	  
                        			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			    	  permissionNo.setHeader(true);
                        			    	  permissionNo.setColspan(5);
                            			      datatable.addCell(permissionNo);
                            			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      permissionNovalue.setHeader(true);
                            			      permissionNovalue.setColspan(6);
                            			      datatable.addCell(permissionNovalue);
                            			      
                            			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                            			      certificate.setHeader(true);
                            			      certificate.setColspan(5);
                            			      datatable.addCell(certificate);
                            			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      certificatevalue.setHeader(true);
                            			      certificatevalue.setColspan(6);
                            			      datatable.addCell(certificatevalue);
                        			    	  
                        			      }
                    			    	  
                    			      }
                    			      
                    			      
                    			      
                    			     
                    			      
                    			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      nationality.setHeader(true);
                    			      nationality.setColspan(5);
                    			      datatable.addCell(nationality);
                    			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      nationalityvalue.setHeader(true);
                    			      nationalityvalue.setColspan(6);
                    			      datatable.addCell(nationalityvalue);
                    			      
                    			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      disability.setHeader(true);
                    			      disability.setColspan(5);
                    			      datatable.addCell(disability);
                    			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      disabilityvalue.setHeader(true);
                    			      disabilityvalue.setColspan(6);
                    			      datatable.addCell(disabilityvalue);
                    			      
                    			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
                    			    	  
                    			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  disabilityDesc.setHeader(true);
                    			    	  disabilityDesc.setColspan(8);
                        			      datatable.addCell(disabilityDesc);
                        			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      disabilityDescvalue.setHeader(true);
                        			      disabilityDescvalue.setColspan(14);
                        			      datatable.addCell(disabilityDescvalue);
                    			    	  
                    			      }
                    			      
                    			      /*
                    			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      nationality.setHeader(true);
                    			      nationality.setColspan(8);
                    			      datatable.addCell(nationality);
                    			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      nationalityvalue.setHeader(true);
                    			      nationalityvalue.setColspan(14);
                    			      datatable.addCell(nationalityvalue);*/
                    			      
                    			      
                    			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      minority.setHeader(true);
                    			      minority.setColspan(5);
                    			      datatable.addCell(minority);
                    			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      minorityvalue.setHeader(true);
                    			      minorityvalue.setColspan(6);
                    			      datatable.addCell(minorityvalue);
                    			      
                    			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      poverty.setHeader(true);
                    			      poverty.setColspan(5);
                    			      datatable.addCell(poverty);
                    			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      povertyvalue.setHeader(true);
                    			      povertyvalue.setColspan(6);
                    			      datatable.addCell(povertyvalue);
                    			      
                    			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      phNo.setHeader(true);
                    			      phNo.setColspan(5);
                    			      datatable.addCell(phNo);
                    			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      phNovalue.setHeader(true);
                    			      phNovalue.setColspan(6);
                    			      datatable.addCell(phNovalue);
                    			      
                    			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      mobNo.setHeader(true);
                    			      mobNo.setColspan(5);
                    			      datatable.addCell(mobNo);
                    			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      mobNovalue.setHeader(true);
                    			      mobNovalue.setColspan(6);
                    			      datatable.addCell(mobNovalue);
                    			      
                    			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      address.setHeader(true);
                    			      address.setColspan(8);
                    			      datatable.addCell(address);
                    			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      addressvalue.setHeader(true);
                    			      addressvalue.setColspan(14);
                    			      datatable.addCell(addressvalue);
                    			      
                    			      Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      postal.setHeader(true);
                    			      postal.setColspan(5);
                    			      datatable.addCell(postal);
                    			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      postalvalue.setHeader(true);
                    			      postalvalue.setColspan(6);
                    			      datatable.addCell(postalvalue);
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(5);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(6);
                    			      datatable.addCell(sharevalue);
                    			      
                    			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      occupation.setHeader(true);
                    			      occupation.setColspan(8);
                    			      datatable.addCell(occupation);
                    			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      occupationvalue.setHeader(true);
                    			      occupationvalue.setColspan(14);
                    			      datatable.addCell(occupationvalue);
                    			      
                    			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proof.setHeader(true);
                    			      proof.setColspan(5);
                    			      datatable.addCell(proof);
                    			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofvalue.setHeader(true);
                    			      proofvalue.setColspan(6);
                    			      datatable.addCell(proofvalue);
                    			      
                    			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proofId.setHeader(true);
                    			      proofId.setColspan(5);
                    			      datatable.addCell(proofId);
                    			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofIdvalue.setHeader(true);
                    			      proofIdvalue.setColspan(6);
                    			      datatable.addCell(proofIdvalue);
                    			      
                    			      
      		                   		
      	                    		
                    			      
                    			      
                			    	  
                			      }
                			      
                			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  		                   		upl2.setHeader(true);
  		                   		upl2.setColspan(8);
  		                   		datatable.addCell(upl2);
  		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  		                   		upl2value.setHeader(true);
  		                   		upl2value.setColspan(14);
  		                   		datatable.addCell(upl2value);
              			      
              			    //BELOW CODE FOR EXECUTANT
		                   		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	                    		{
		                   			
		                   		Cell upl3=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF EXECUTANT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                   		upl3.setHeader(true);
		                   		upl3.setColspan(8);
		                   		datatable.addCell(upl3);
		                   		Cell upl3value=new Cell(new Phrase(mapDto.getU3DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                   		upl3value.setHeader(true);
		                   		upl3value.setColspan(14);
		                   		datatable.addCell(upl3value);
            			      
            			      Cell upl4=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			      upl4.setHeader(true);
            			      upl4.setColspan(8);
            			      datatable.addCell(upl4);
            			      Cell upl4value=new Cell(new Phrase(mapDto.getU4DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      upl4value.setHeader(true);
            			      upl4value.setColspan(14);
            			      datatable.addCell(upl4value);
            			      
            			      if(!mapDto.getSelectedPhotoId().equalsIgnoreCase("4")){
            			      Cell upl10=new Cell(new Phrase("PAN OR FORM 60/61", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			      upl10.setHeader(true);
            			      upl10.setColspan(8);
          			      datatable.addCell(upl10);
          			      Cell upl10value=new Cell(new Phrase(mapDto.getU10DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
          			      upl10value.setHeader(true);
          			      upl10value.setColspan(14);
          			      datatable.addCell(upl10value);
            			      }
          			      
			                   		
	                    		}
		                   	//BELOW CODE FOR EXECUTANT POA HOLDER
	                    		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	                    		{
	                    			
	                    			Cell upl5=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF ATTORNEY", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    		                   		upl5.setHeader(true);
    		                   		upl5.setColspan(8);
    		                   		datatable.addCell(upl5);
    		                   		Cell upl5value=new Cell(new Phrase(mapDto.getU5DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    		                   		upl5value.setHeader(true);
    		                   		upl5value.setColspan(14);
    		                   		datatable.addCell(upl5value);
                			      
                			      Cell upl6=new Cell(new Phrase("ATTORNEY PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      upl6.setHeader(true);
                			      upl6.setColspan(8);
                			      datatable.addCell(upl6);
                			      Cell upl6value=new Cell(new Phrase(mapDto.getU6DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      upl6value.setHeader(true);
                			      upl6value.setColspan(14);
                			      datatable.addCell(upl6value);
                			      
                			      Cell upl7=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      upl7.setHeader(true);
                			      upl7.setColspan(8);
              			      datatable.addCell(upl7);
              			      Cell upl7value=new Cell(new Phrase(mapDto.getU7DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
              			      upl7value.setHeader(true);
              			      upl7value.setColspan(14);
              			      datatable.addCell(upl7value);
	                    			
	                    			
	                    			/*mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName());
			                   		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
			                   		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName());*/
              			      
              			      Cell officeName=new Cell(new Phrase("SR Office Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
              			      officeName.setHeader(true);
              			      officeName.setColspan(8);
              			      datatable.addCell(officeName);
              			      Cell officeNamevalue=new Cell(new Phrase(mapDto.getSrOfficeNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
              			      officeNamevalue.setHeader(true);
              			      officeNamevalue.setColspan(14);
              			      datatable.addCell(officeNamevalue);
              			      
              			      Cell poaRegno=new Cell(new Phrase("PoA Registration No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
              			      poaRegno.setHeader(true);
              			      poaRegno.setColspan(5);
              			      datatable.addCell(poaRegno);
              			      Cell poaRegnovalue=new Cell(new Phrase(mapDto.getPoaRegNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
              			      poaRegnovalue.setHeader(true);
              			      poaRegnovalue.setColspan(6);
              			      datatable.addCell(poaRegnovalue);
              			      
              			      Cell poaRegDate=new Cell(new Phrase("PoA Registration Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
              			      poaRegDate.setHeader(true);
              			      poaRegDate.setColspan(5);
              			      datatable.addCell(poaRegDate);
              			      Cell poaRegDatevalue=new Cell(new Phrase(mapDto.getDatePoaRegTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
              			      poaRegDatevalue.setHeader(true);
              			      poaRegDatevalue.setColspan(6);
              			      datatable.addCell(poaRegDatevalue);
			                   		
			                   	/*	
			                   		mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
			                   		mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
			                   		mapDto.setDatePoaRegTrns(convertCalenderDate(regForm.getDatePoaReg()));*/
	                    		}
		                   		
	                    		
                			     
                			      
                			      
                    			  //OWNER DETAILS IN CASE OF POA BELOW
                    		
                			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
                			      {
                			    	  
                			    	  setOwnerOnPdf(mapDto,datatable);
                			    	  /*
                			    	  
                			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
                			    	  ownerHdr.setHeader(true);
                			    	  ownerHdr.setColspan(22);
                    			      datatable.addCell(ownerHdr);
                			    	  
                			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  ownerName.setHeader(true);
                			    	  ownerName.setColspan(5);
                    			      datatable.addCell(ownerName);
                    			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNamevalue.setHeader(true);
                    			      ownerNamevalue.setColspan(6);
                    			      datatable.addCell(ownerNamevalue);
                    			      
                    			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerOrgName.setHeader(true);
                    			      ownerOrgName.setColspan(5);
                    			      datatable.addCell(ownerOrgName);
                    			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerOrgNamevalue.setHeader(true);
                    			      ownerOrgNamevalue.setColspan(6);
                    			      datatable.addCell(ownerOrgNamevalue);
                    			      
                    			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerGender.setHeader(true);
                    			      ownerGender.setColspan(5);
                    			      datatable.addCell(ownerGender);
                    			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerGendervalue.setHeader(true);
                    			      ownerGendervalue.setColspan(6);
                    			      datatable.addCell(ownerGendervalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerNationality.setHeader(true);
                    			      ownerNationality.setColspan(8);
                    			      datatable.addCell(ownerNationality);
                    			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNationalityvalue.setHeader(true);
                    			      ownerNationalityvalue.setColspan(14);
                    			      datatable.addCell(ownerNationalityvalue);
                    			      
                    			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerAddress.setHeader(true);
                    			      ownerAddress.setColspan(8);
                    			      datatable.addCell(ownerAddress);
                    			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerAddressvalue.setHeader(true);
                    			      ownerAddressvalue.setColspan(14);
                    			      datatable.addCell(ownerAddressvalue);
                    			      
                    			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerMobile.setHeader(true);
                    			      ownerMobile.setColspan(5);
                    			      datatable.addCell(ownerMobile);
                    			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerMobilevalue.setHeader(true);
                    			      ownerMobilevalue.setColspan(6);
                    			      datatable.addCell(ownerMobilevalue);
                    			      
                    			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerEmail.setHeader(true);
                    			      ownerEmail.setColspan(5);
                    			      datatable.addCell(ownerEmail);
                    			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerEmailvalue.setHeader(true);
                    			      ownerEmailvalue.setColspan(6);
                    			      datatable.addCell(ownerEmailvalue);
                    			      
                    			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProof.setHeader(true);
                    			      ownerProof.setColspan(5);
                    			      datatable.addCell(ownerProof);
                    			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofvalue.setHeader(true);
                    			      ownerProofvalue.setColspan(6);
                    			      datatable.addCell(ownerProofvalue);
                    			      
                    			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProofId.setHeader(true);
                    			      ownerProofId.setColspan(5);
                    			      datatable.addCell(ownerProofId);
                    			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofIdvalue.setHeader(true);
                    			      ownerProofIdvalue.setColspan(6);
                    			      datatable.addCell(ownerProofIdvalue);
                			    	  
                			    	  
                			    	  
                			    	  
                			      */}
                    		  
                    		  
                    		  
                    		  
                    		  
                    		  
                    	  }
                    		  
                    		  
                    		  
                    		  
                    	  }
                    	 
                			 
                    	  
                    	  }
                	  
                	  //WRITING PROPERTY DETAILS ON PDF
                	  
                	  
                	  if(dto.getPropertyListExchngDeedPdf()!=null && dto.getPropertyListExchngDeedPdf().size()>0){
                    	  propList=dto.getPropertyListExchngDeedPdf();
                    	  RegCompletDTO dto1 =new RegCompletDTO();
                    	  //below loop for party details
                    	  
                    	  
        			      int sellerCount=0;
        			      int buyerCount=0;
        			      
                    	  for(int j=0;j<propList.size();j++){                          
                    		  dto1=(RegCompletDTO)propList.get(j);
                    		  
                    		  datatable.addCell(row);
                        	  Cell propertyHdr=new Cell(new Phrase("Property "+(j+1) +" Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
                        	  propertyHdr.setHeader(true);
                        	  propertyHdr.setColspan(22);
            			      datatable.addCell(propertyHdr);
                    		  
                	  datatable.addCell(row);
                	  Cell propertyCount=new Cell(new Phrase("Property Id-"+dto1.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
                	  propertyCount.setHeader(true);
                	  propertyCount.setColspan(22);
    			      datatable.addCell(propertyCount);
                	  
                	  
                	  
                	  Cell propertyId=new Cell(new Phrase("Property Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  propertyId.setHeader(true);
                	  propertyId.setColspan(8);
                	  //propertyId.setBackgroundColor(new Color(255,130,171));
    			      datatable.addCell(propertyId);
    			      Cell propertyIdvalue=new Cell(new Phrase(dto1.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propertyIdvalue.setHeader(true);
    			      propertyIdvalue.setColspan(14);
    			      //propertyIdvalue.setBackgroundColor(new Color(255,255,36));
    			      datatable.addCell(propertyIdvalue);
                	  
    			      if(dto.getFloorCount()==0){
    			      Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      considerAmt.setHeader(true);
    			      considerAmt.setColspan(8);
                	  datatable.addCell(considerAmt);
    			      Cell considerAmtvalue=new Cell(new Phrase(obj.format(dto1.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      considerAmtvalue.setHeader(true);
    			      considerAmtvalue.setColspan(14);
    			      datatable.addCell(considerAmtvalue);
    			      }
    			      
    			      Cell guideline=new Cell(new Phrase("Current guideline value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      guideline.setHeader(true);
    			      guideline.setColspan(8);
                	  datatable.addCell(guideline);
    			      Cell guidelinevalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto1.getGuidelineValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      guidelinevalue.setHeader(true);
    			      guidelinevalue.setColspan(14);
    			      datatable.addCell(guidelinevalue);
    			      
    			      Cell system=new Cell(new Phrase("System generated valuation (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      system.setHeader(true);
    			      system.setColspan(8);
                	  datatable.addCell(system);
    			      Cell systemvalue=new Cell(new Phrase(obj.format(dto1.getSystemMV()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      systemvalue.setHeader(true);
    			      systemvalue.setColspan(14);
    			      datatable.addCell(systemvalue);
    			      
    			      Cell mv=new Cell(new Phrase("Market Value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      mv.setHeader(true);
    			      mv.setColspan(8);
                	  datatable.addCell(mv);
    			      Cell mvvalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto1.getMarketValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      mvvalue.setHeader(true);
    			      mvvalue.setColspan(14);
    			      datatable.addCell(mvvalue);
    			      
    			     /* Cell propType=new Cell(new Phrase("Property Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propType.setHeader(true);
    			      propType.setColspan(8);
                	  datatable.addCell(propType);
    			      Cell propTypevalue=new Cell(new Phrase(dto1.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propTypevalue.setHeader(true);
    			      propTypevalue.setColspan(14);
    			      datatable.addCell(propTypevalue);
                	  
    			      Cell propAddress=new Cell(new Phrase("Property Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propAddress.setHeader(true);
    			      propAddress.setColspan(8);
                	  datatable.addCell(propAddress);
    			      Cell propAddressvalue=new Cell(new Phrase(dto1.getPropAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propAddressvalue.setHeader(true);
    			      propAddressvalue.setColspan(14);
    			      datatable.addCell(propAddressvalue);
    			      
    			      Cell propLandmark=new Cell(new Phrase("Property Landmark", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propLandmark.setHeader(true);
    			      propLandmark.setColspan(8);
                	  datatable.addCell(propLandmark);
    			      Cell propLandmarkvalue=new Cell(new Phrase(dto1.getPropLandmark(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propLandmarkvalue.setHeader(true);
    			      propLandmarkvalue.setColspan(14);
    			      datatable.addCell(propLandmarkvalue);
    			      
    			      Cell propMohl=new Cell(new Phrase("Mohalla/Colony name/Society/Road/Gram", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propMohl.setHeader(true);
    			      propMohl.setColspan(5);
                	  datatable.addCell(propMohl);
    			      Cell propMohlvalue=new Cell(new Phrase(dto1.getMohallaName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propMohlvalue.setHeader(true);
    			      propMohlvalue.setColspan(6);
    			      datatable.addCell(propMohlvalue);
    			      
    			      Cell propWarkPatwari=new Cell(new Phrase("Ward / Patwari Halka", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propWarkPatwari.setHeader(true);
    			      propWarkPatwari.setColspan(5);
                	  datatable.addCell(propWarkPatwari);
    			      Cell propWarkPatwarivalue=new Cell(new Phrase(dto1.getPatwariStatus(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propWarkPatwarivalue.setHeader(true);
    			      propWarkPatwarivalue.setColspan(6);
    			      datatable.addCell(propWarkPatwarivalue);
    			      
    			      Cell propWarkPatwariNo=new Cell(new Phrase("ward Number/Patwari Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propWarkPatwariNo.setHeader(true);
    			      propWarkPatwariNo.setColspan(5);
                	  datatable.addCell(propWarkPatwariNo);
    			      Cell propWarkPatwariNovalue=new Cell(new Phrase(dto1.getWardpatwarName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propWarkPatwariNovalue.setHeader(true);
    			      propWarkPatwariNovalue.setColspan(6);
    			      datatable.addCell(propWarkPatwariNovalue);
    			      
    			      Cell propGovBody=new Cell(new Phrase("Governing Municipal Body", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propGovBody.setHeader(true);
    			      propGovBody.setColspan(5);
                	  datatable.addCell(propGovBody);
    			      Cell propGovBodyvalue=new Cell(new Phrase(dto1.getMunicipalBodyName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propGovBodyvalue.setHeader(true);
    			      propGovBodyvalue.setColspan(6);
    			      datatable.addCell(propGovBodyvalue);
    			      
    			      Cell propAreaType=new Cell(new Phrase("Type of Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propAreaType.setHeader(true);
    			      propAreaType.setColspan(8);
                	  datatable.addCell(propAreaType);
    			      Cell propAreaTypevalue=new Cell(new Phrase(dto1.getAreaTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propAreaTypevalue.setHeader(true);
    			      propAreaTypevalue.setColspan(14);
    			      datatable.addCell(propAreaTypevalue);
    			      
    			      Cell propTehsil=new Cell(new Phrase("Tehsil", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propTehsil.setHeader(true);
    			      propTehsil.setColspan(5);
                	  datatable.addCell(propTehsil);
    			      Cell propTehsilvalue=new Cell(new Phrase(dto1.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propTehsilvalue.setHeader(true);
    			      propTehsilvalue.setColspan(6);
    			      datatable.addCell(propTehsilvalue);
    			      
    			      Cell propDist=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propDist.setHeader(true);
    			      propDist.setColspan(5);
                	  datatable.addCell(propDist);
    			      Cell propDistvalue=new Cell(new Phrase(dto1.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propDistvalue.setHeader(true);
    			      propDistvalue.setColspan(6);
    			      datatable.addCell(propDistvalue);
    			      
    			      Cell propVikas=new Cell(new Phrase("Vikas Khand (development block)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propVikas.setHeader(true);
    			      propVikas.setColspan(5);
                	  datatable.addCell(propVikas);
    			      Cell propVikasvalue=new Cell(new Phrase(dto1.getVikasId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propVikasvalue.setHeader(true);
    			      propVikasvalue.setColspan(6);
    			      datatable.addCell(propVikasvalue);
    			      
    			      Cell propRi=new Cell(new Phrase("R. I. Circle", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propRi.setHeader(true);
    			      propRi.setColspan(5);
                	  datatable.addCell(propRi);
    			      Cell propRivalue=new Cell(new Phrase(dto1.getRicircle(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propRivalue.setHeader(true);
    			      propRivalue.setColspan(6);
    			      datatable.addCell(propRivalue);
    			      
    			      Cell propLayout=new Cell(new Phrase("Layout Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propLayout.setHeader(true);
    			      propLayout.setColspan(5);
                	  datatable.addCell(propLayout);
    			      Cell propLayoutvalue=new Cell(new Phrase(dto1.getLayoutdet(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propLayoutvalue.setHeader(true);
    			      propLayoutvalue.setColspan(6);
    			      datatable.addCell(propLayoutvalue);
    			      
    			      Cell propNazool=new Cell(new Phrase("Nazool/Sheet Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propNazool.setHeader(true);
    			      propNazool.setColspan(5);
                	  datatable.addCell(propNazool);
    			      Cell propNazoolvalue=new Cell(new Phrase(dto1.getSheetnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propNazoolvalue.setHeader(true);
    			      propNazoolvalue.setColspan(6);
    			      datatable.addCell(propNazoolvalue);
    			      
    			      Cell propPlotNo=new Cell(new Phrase("Plot Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propPlotNo.setHeader(true);
    			      propPlotNo.setColspan(8);
                	  datatable.addCell(propPlotNo);
    			      Cell propPlotNovalue=new Cell(new Phrase(dto1.getPlotnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propPlotNovalue.setHeader(true);
    			      propPlotNovalue.setColspan(14);
    			      datatable.addCell(propPlotNovalue);
    			      
    			      Cell khasraHdr=new Cell(new Phrase("Khasra Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      khasraHdr.setHeader(true);
    			      khasraHdr.setColspan(22);
    			      datatable.addCell(khasraHdr);
                	  
    			      //Sr.No. Khasra Number  Area (sq mtr) Lagaan/Land Revenue(INR) RIN Pustika Number  
                	  
    			      Cell srNo=new Cell(new Phrase("Sr.No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      srNo.setHeader(true);
    			      srNo.setColspan(2);
                	  datatable.addCell(srNo);
                	  
                	  Cell khasraNo=new Cell(new Phrase("Khasra Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  khasraNo.setHeader(true);
                	  khasraNo.setColspan(5);
                	  datatable.addCell(khasraNo);
                	  
                	  Cell khasraArea=new Cell(new Phrase("Khasra Area ("+dto1.getUnit()+")", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  khasraArea.setHeader(true);
                	  khasraArea.setColspan(5);
                	  datatable.addCell(khasraArea);
                	  
                	  Cell lagaan=new Cell(new Phrase("Lagaan/Land Revenue(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  lagaan.setHeader(true);
                	  lagaan.setColspan(5);
                	  datatable.addCell(lagaan);
                	  
                	  Cell rinPustika=new Cell(new Phrase("RIN Pustika Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  rinPustika.setHeader(true);
                	  rinPustika.setColspan(5);
                	  datatable.addCell(rinPustika);
                	  
                	  if(dto1.getKhasraDetlsDisplay()!=null && dto1.getKhasraDetlsDisplay().size()>0){
                		  CommonDTO objDto;
                	  
                	      for(int m=0;m<dto1.getKhasraDetlsDisplay().size();m++){
                	    	  objDto=(CommonDTO)dto1.getKhasraDetlsDisplay().get(m);
                	    	  
                	    	  Cell srNovalue=new Cell(new Phrase(""+(m+1)+"", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	    	  srNovalue.setHeader(true);
                	    	  srNovalue.setColspan(2);
                        	  datatable.addCell(srNovalue);
                        	  
                        	  Cell khasraNovalue=new Cell(new Phrase(objDto.getKhasraNum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        	  khasraNovalue.setHeader(true);
                        	  khasraNovalue.setColspan(5);
                        	  datatable.addCell(khasraNovalue);
                        	  
                        	  Cell khasraAreavalue=new Cell(new Phrase(objDto.getKhasraArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        	  khasraAreavalue.setHeader(true);
                        	  khasraAreavalue.setColspan(5);
                        	  datatable.addCell(khasraAreavalue);
                        	  
                        	  Cell lagaanvalue=new Cell(new Phrase(objDto.getLagaan(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        	  lagaanvalue.setHeader(true);
                        	  lagaanvalue.setColspan(5);
                        	  datatable.addCell(lagaanvalue);
                        	  
                        	  Cell rinPustikavalue=new Cell(new Phrase(objDto.getRinPustika(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        	  rinPustikavalue.setHeader(true);
                        	  rinPustikavalue.setColspan(5);
                        	  datatable.addCell(rinPustikavalue);
                	    	  
                	    	  
                	    	  
                	      }
                	  }
                	  
                	  Cell fourBoundryHdr=new Cell(new Phrase("Four Boundary Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                	  fourBoundryHdr.setHeader(true);
                	  fourBoundryHdr.setColspan(22);
    			      datatable.addCell(fourBoundryHdr);
    			      
    			      Cell propNorth=new Cell(new Phrase("North", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propNorth.setHeader(true);
    			      propNorth.setColspan(5);
                	  datatable.addCell(propNorth);
    			      Cell propNorthvalue=new Cell(new Phrase(dto1.getNorth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propNorthvalue.setHeader(true);
    			      propNorthvalue.setColspan(6);
    			      datatable.addCell(propNorthvalue);
    			      
    			      Cell propSouth=new Cell(new Phrase("South", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propSouth.setHeader(true);
    			      propSouth.setColspan(5);
                	  datatable.addCell(propSouth);
    			      Cell propSouthvalue=new Cell(new Phrase(dto1.getSouth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propSouthvalue.setHeader(true);
    			      propSouthvalue.setColspan(6);
    			      datatable.addCell(propSouthvalue);
    			      
    			      Cell propEast=new Cell(new Phrase("East", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propEast.setHeader(true);
    			      propEast.setColspan(5);
                	  datatable.addCell(propEast);
    			      Cell propEastvalue=new Cell(new Phrase(dto1.getEast(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propEastvalue.setHeader(true);
    			      propEastvalue.setColspan(6);
    			      datatable.addCell(propEastvalue);
    			      
    			      Cell propWest=new Cell(new Phrase("West", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propWest.setHeader(true);
    			      propWest.setColspan(5);
                	  datatable.addCell(propWest);
    			      Cell propWestvalue=new Cell(new Phrase(dto1.getWest(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propWestvalue.setHeader(true);
    			      propWestvalue.setColspan(6);
    			      datatable.addCell(propWestvalue);
    			      

    			      datatable.addCell(row);
  			        
    			      if(dto.getFloorCount()==0){
    			      String area=Double.toString(dto1.getTotalSqMeter());
    			      
    			      Cell propArea=new Cell(new Phrase("Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propArea.setHeader(true);
    			      propArea.setColspan(8);
                	  datatable.addCell(propArea);
    			      Cell propAreavalue=new Cell(new Phrase(area+" "+dto1.getUnit(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      propAreavalue.setHeader(true);
    			      propAreavalue.setColspan(14);
    			      datatable.addCell(propAreavalue);
    			      }
    			      
    			      
    			      Cell propNoFloor=new Cell(new Phrase("Number of Constructed Floors", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      propNoFloor.setHeader(true);
    			      propNoFloor.setColspan(8);
                	  datatable.addCell(propNoFloor);
                	  if(dto.getFloorCount()==0){
    			    	  Cell propNoFloorvalue=new Cell(new Phrase(RegInitConstant.NOT_APPLICABLE, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    				      propNoFloorvalue.setHeader(true);
    				      propNoFloorvalue.setColspan(14);
    				      datatable.addCell(propNoFloorvalue);
    			      }else{
    			    	  Cell propNoFloorvalue=new Cell(new Phrase(Integer.toString(dto.getFloorCount()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    				      propNoFloorvalue.setHeader(true);
    				      propNoFloorvalue.setColspan(14);
    				      datatable.addCell(propNoFloorvalue);
    			      }
    			      
    			      if(dto1.getFloorCount()==0){
    			    	  
    			    	  //subclause non building
    			    	  if(dto1.getSelectedSubClauseList()!=null && dto1.getSelectedSubClauseList().size()>0){
    			    		  
    			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			    		  subClauseHdr.setHeader(true);
    			    		  subClauseHdr.setColspan(22);
            			      datatable.addCell(subClauseHdr);
            			      
            			     for(int n=0;n<dto1.getSelectedSubClauseList().size();n++){
            			    	 
            			    	 CommonDTO subClausedto = new CommonDTO();
            			    	 subClausedto=(CommonDTO)dto1.getSelectedSubClauseList().get(n);
            			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			    	 subClauseValue.setHeader(true);
            			    	 subClauseValue.setColspan(22);
               			         datatable.addCell(subClauseValue);
            			    	 
            			    	 
            			    	 
            			     }
    			    		  
    			    		  
    			    	  }
    			    	  
    			    	  
    			    	  
    			    	  
    			    	  
    			      }else if(dto1.getFloorCount()>0){
    			    	  //floor details
    			    	  
    			    	  	if(dto1.getMapBuilding()!=null && dto1.getMapBuilding().size()>0){
    			    	  		
    			    	  		Cell floorHdr=new Cell(new Phrase("Floor Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			    	  		floorHdr.setHeader(true);
    			    	  		floorHdr.setColspan(22);
              			      datatable.addCell(floorHdr);
    			    	  		Collection mapCollection2=dto1.getMapBuilding().values();
              			      Object[] l2=mapCollection2.toArray();
                                
    			    	  		RegCompletDTO floordto;
    			    		   for(int p=0;p<l2.length;p++){
            			    	 
    			    			   floordto = new RegCompletDTO();
    			    			   floordto=(RegCompletDTO)l2[p];
            			    	 
            			    	 Cell buildingType=new Cell(new Phrase("Building Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			    	 buildingType.setHeader(true);
            			    	 buildingType.setColspan(5);
               			      	 datatable.addCell(buildingType);
            			    	 Cell buildingTypeValue=new Cell(new Phrase(floordto.getUsageBuilding(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			    	 buildingTypeValue.setHeader(true);
            			    	 buildingTypeValue.setColspan(6);
               			         datatable.addCell(buildingTypeValue);
               			         
               			         Cell roofType=new Cell(new Phrase("Roof Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
               			         roofType.setHeader(true);
               			         roofType.setColspan(5);
               			         datatable.addCell(roofType);
               			         Cell roofTypeValue=new Cell(new Phrase(floordto.getCeilingType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
               			         roofTypeValue.setHeader(true);
               			         roofTypeValue.setColspan(6);
               			         datatable.addCell(roofTypeValue);
            			      
               			         Cell floorType=new Cell(new Phrase("Floor Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
               			         floorType.setHeader(true);
               			         floorType.setColspan(5);
            			      	 datatable.addCell(floorType);
            			      	 Cell floorTypeValue=new Cell(new Phrase(floordto.getTypeFloorDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      	 floorTypeValue.setHeader(true);
            			      	 floorTypeValue.setColspan(6);
            			         datatable.addCell(floorTypeValue);
            			         
            			         Cell consdAmt=new Cell(new Phrase("Consideration Amount (Rs.)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			         consdAmt.setHeader(true);
            			         consdAmt.setColspan(5);
            			         datatable.addCell(consdAmt);
            			         Cell consdAmtValue=new Cell(new Phrase(Double.toString(floordto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			         consdAmtValue.setHeader(true);
            			         consdAmtValue.setColspan(6);
            			         datatable.addCell(consdAmtValue);
         			      
            			         Cell floorTotArea=new Cell(new Phrase("Total Area (Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			         floorTotArea.setHeader(true);
            			         floorTotArea.setColspan(5);
            			         datatable.addCell(floorTotArea);
            			         Cell floorTotAreaValue=new Cell(new Phrase(Double.toString(floordto.getTotalSqMeter()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			         floorTotAreaValue.setHeader(true);
            			         floorTotAreaValue.setColspan(6);
            			         datatable.addCell(floorTotAreaValue);
       			         
            			         Cell floorConstArea=new Cell(new Phrase("Constructed Area(Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			         floorConstArea.setHeader(true);
            			         floorConstArea.setColspan(5);
            			         datatable.addCell(floorConstArea);
            			         Cell floorConstAreaValue=new Cell(new Phrase(floordto.getConstructedArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			         floorConstAreaValue.setHeader(true);
            			         floorConstAreaValue.setColspan(6);
            			         datatable.addCell(floorConstAreaValue);
            			         
            			         if(floordto.getSelectedSubClauseList()!=null && floordto.getSelectedSubClauseList().size()>0){
           			    		  
           			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
           			    		  subClauseHdr.setHeader(true);
           			    		  subClauseHdr.setColspan(22);
                   			      datatable.addCell(subClauseHdr);
                   			      
                   			     for(int f=0;f<floordto.getSelectedSubClauseList().size();f++){
                   			    	 
                   			    	 CommonDTO subClausedto2 = new CommonDTO();
                   			    	 subClausedto2=(CommonDTO)floordto.getSelectedSubClauseList().get(f);
                   			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto2.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                   			    	 subClauseValue.setHeader(true);
                   			    	 subClauseValue.setColspan(22);
                      			         datatable.addCell(subClauseValue);
                   			    	 
                   			    	 
                   			    	 
                   			     }
           			    		  
           			    		  
           			    	  }
            			          
            			    	 
            			    	 
            			    	 
            			     }
    			    		  
    			    		  
    			    	  }
    			    	  
    			      }*/
    			      setPropertyOnPdf(dto1,datatable);
    			      //property related attachments
    			      
    			      Cell uplAngle1=new Cell(new Phrase("ANGLE 1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      uplAngle1.setHeader(true);
    			      uplAngle1.setColspan(8);
    			        datatable.addCell(uplAngle1);
    			        Cell uplAngle1value=new Cell(new Phrase(dto1.getPropImage1DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			        uplAngle1value.setHeader(true);
    			        uplAngle1value.setColspan(14);
    			        datatable.addCell(uplAngle1value);
    			        
    			        Cell uplAngle2=new Cell(new Phrase("ANGLE 2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
      			      uplAngle2.setHeader(true);
      			      uplAngle2.setColspan(8);
      			        datatable.addCell(uplAngle2);
      			        Cell uplAngle2value=new Cell(new Phrase(dto1.getPropImage2DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
      			        uplAngle2value.setHeader(true);
      			        uplAngle2value.setColspan(14);
      			        datatable.addCell(uplAngle2value);
      			        
      			      Cell uplAngle3=new Cell(new Phrase("ANGLE 3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      uplAngle3.setHeader(true);
    			      uplAngle3.setColspan(8);
    			        datatable.addCell(uplAngle3);
    			        Cell uplAngle3value=new Cell(new Phrase(dto1.getPropImage3DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			        uplAngle3value.setHeader(true);
    			        uplAngle3value.setColspan(14);
    			        datatable.addCell(uplAngle3value);
    			        
    			        Cell uplMap=new Cell(new Phrase("Property Map as per rules", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			        uplMap.setHeader(true);
    			        uplMap.setColspan(8);
      			        datatable.addCell(uplMap);
      			        Cell uplMapvalue=new Cell(new Phrase(dto1.getPropMapDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
      			        uplMapvalue.setHeader(true);
      			        uplMapvalue.setColspan(14);
      			        datatable.addCell(uplMapvalue);
      			        
      			      if(	dto1.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
      				{
      			    	  
      			    	Cell uplRin=new Cell(new Phrase("RIN Pustika for Agriculture Land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
      			    	uplRin.setHeader(true);
      			    	uplRin.setColspan(8);
      			        datatable.addCell(uplRin);
      			        Cell uplRinvalue=new Cell(new Phrase(dto1.getPropRinDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
      			        uplRinvalue.setHeader(true);
      			        uplRinvalue.setColspan(14);
      			        datatable.addCell(uplRinvalue);
      			        
      			      Cell uplKhasra=new Cell(new Phrase("Computerized Khasra of 1 year certified by revenue official for agricultural land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
      			      uplKhasra.setHeader(true);
      			      uplKhasra.setColspan(8);
    			        datatable.addCell(uplKhasra);
    			        Cell uplKhasravalue=new Cell(new Phrase(dto1.getPropKhasraDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			        uplKhasravalue.setHeader(true);
    			        uplKhasravalue.setColspan(14);
    			        datatable.addCell(uplKhasravalue);
    			        
      				
      				}
                	 
      			      
                	  }
                  }
                	  
                  }
			      
			      
			      
		}
			      else{
			
			Cell noDataFound=new Cell(new Phrase("No data found", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			noDataFound.setHeader(true);
			noDataFound.setColspan(22);
		      //balanceon.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(noDataFound);
		      
		      datatable.addCell(row);
			
		}
			      
		 
		      }
		      
		      if(deedId==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
		    		  deedId==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||	
		    		  deedId==RegInitConstant.DEED_PARTITION_NPV ||	
		    		  regForm.getDeedTypeFlag()==1 ||
		    		  regForm.getCommonDeed()==1){                  //DEED TYPE FLAG FOR TRUST/CANCELLATION OF WILL POA/ADOPTION/AGREEMENT OF MEMO NPV NP
		    	  
		    	 
		    	  getExtraDetls(regForm,languageLocale);
		    	  
		    	  if(deedId==RegInitConstant.DEED_AGREEMENT_MEMO_NPV){
			    	  Cell trustHeader=new Cell(new Phrase("Agreement Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Advance Payment", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(Double.toString(regForm.getAdvance()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Date of advance payment", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getAdvancePaymntDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      Cell address=new Cell(new Phrase("Possesion given to the buyer", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      address.setHeader(true);
    			      address.setColspan(8);
    			      datatable.addCell(address);
    			      Cell addressvalue=new Cell(new Phrase(regForm.getPossGivenName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      addressvalue.setHeader(true);
    			      addressvalue.setColspan(14);
    			      datatable.addCell(addressvalue);
    			      
				      
				      datatable.addCell(row);
			    	  }
		    	  if(deedId==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
		    	  Cell bankHeader=new Cell(new Phrase("Bank Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		    	  bankHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		    	  bankHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(bankHeader);
			      datatable.setAlignment(2);  
		    	  
			      Cell bName=new Cell(new Phrase("Bank Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bName.setHeader(true);
			      bName.setColspan(5);
			      datatable.addCell(bName);
			      Cell bNamevalue=new Cell(new Phrase(regForm.getBankName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bNamevalue.setHeader(true);
			      bNamevalue.setColspan(6);
			      datatable.addCell(bNamevalue);
			      
			      Cell branchName=new Cell(new Phrase("Bank Brach Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      branchName.setHeader(true);
			      branchName.setColspan(5);
			      datatable.addCell(branchName);
			      Cell branchNamevalue=new Cell(new Phrase(regForm.getBranchName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      branchNamevalue.setHeader(true);
			      branchNamevalue.setColspan(6);
			      datatable.addCell(branchNamevalue);
			      
			      Cell bAddress=new Cell(new Phrase("Bank Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAddress.setHeader(true);
			      bAddress.setColspan(5);
			      datatable.addCell(bAddress);
			      Cell bAddressvalue=new Cell(new Phrase(regForm.getBankAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAddressvalue.setHeader(true);
			      bAddressvalue.setColspan(6);
			      datatable.addCell(bAddressvalue);
			      
			      Cell bAuth=new Cell(new Phrase("Authorised Person Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAuth.setHeader(true);
			      bAuth.setColspan(5);
			      datatable.addCell(bAuth);
			      Cell bAuthvalue=new Cell(new Phrase(regForm.getBankAuthPer(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAuthvalue.setHeader(true);
			      bAuthvalue.setColspan(6);
			      datatable.addCell(bAuthvalue);
			      
			      Cell loanAmt=new Cell(new Phrase("Loan Amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      loanAmt.setHeader(true);
			      loanAmt.setColspan(5);
			      datatable.addCell(loanAmt);
			      Cell loanAmtvalue=new Cell(new Phrase(obj.format(regForm.getBankLoanAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      loanAmtvalue.setHeader(true);
			      loanAmtvalue.setColspan(6);
			      datatable.addCell(loanAmtvalue);
			      
			      Cell sancAmt=new Cell(new Phrase("Sanctioned Amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      sancAmt.setHeader(true);
			      sancAmt.setColspan(5);
			      datatable.addCell(sancAmt);
			      Cell sancAmtvalue=new Cell(new Phrase(obj.format(regForm.getBankSancAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      sancAmtvalue.setHeader(true);
			      sancAmtvalue.setColspan(6);
			      datatable.addCell(sancAmtvalue);
		    	  
			      datatable.addCell(row);
		    	  }
		    	  if(deedId==RegInitConstant.DEED_TRUST){
			    	  Cell trustHeader=new Cell(new Phrase("Trust Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Trust Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getTrustName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Trust Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getTrustDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		    	  if(deedId==RegInitConstant.DEED_RECONV_MORTGAGE_NPV){
			    	  Cell trustHeader=new Cell(new Phrase("Extra Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Paid loan amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(8);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(obj.format(regForm.getPaidLoanAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(14);
				      datatable.addCell(tNamevalue);
				     
				      
				      datatable.addCell(row);
			    	  }
		    	  
		    	  if(deedId==RegInitConstant.DEED_PARTNERSHIP_NPV && regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV){
			    	  Cell trustHeader=new Cell(new Phrase("Dissolution of Partnership Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("Date of dissolution of partnership", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getDissolutionDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Date of Retirement of  Partner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(regForm.getRetirmentDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
			      
			      
			      
			      
			    //  HashMap pdfMap=getPropertyPartyDetailsForPDF(regForm.getHidnRegTxnId());
			      
			      
			      ArrayList partyList=getPartyDetailsForPDFTitleDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale);
			      //ArrayList commonDeedPartyList=new ArrayList();
			      ArrayList particularList=new ArrayList();
			      ArrayList propList=new ArrayList();
			     
			      
			      if(regForm.getCommonDeed()==1){
			      particularList=getParticularListPdf(regForm.getHidnRegTxnId());
			      
			      }else{
			    	  propList=getPropertyDetailsForPDFTitleDeed(regForm.getHidnRegTxnId()); 
			    	  
			      }
			      
			      if(particularList != null && particularList.size()>0){
			    	  Cell partPartyHeader=new Cell(new Phrase("Party And Particular Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  partPartyHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  partPartyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(partPartyHeader);
				      datatable.setAlignment(2);
			      }else  if(propList != null && propList.size()>0){
			      Cell propPartyHeader=new Cell(new Phrase("Party And Property Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			      propPartyHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			      propPartyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(propPartyHeader);
			      datatable.setAlignment(2);
			      }else{

				      Cell partyHeader=new Cell(new Phrase("Party Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
				      partyHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
				      partyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(partyHeader);
				      datatable.setAlignment(2);
				       
			      }
			
			      //if(partyList!=null && propList!=null){
			      if(partyList!=null && partyList.size()>0){
	            	  
	            	  //if(partyList!=null && partyList.size()>0){
	                	  //partyList=dto.getPartyListPdf();
	                	  RegCommonDTO mapDto =new RegCommonDTO();
	                	  //below loop for party details
	                	  
	                	  
	    			      int sellerCount=0;
	    			      //int buyerCount=0;
	    			      
	                	  for(int j=0;j<partyList.size();j++){                          //seller/owner loop
	                		  mapDto=(RegCommonDTO)partyList.get(j);
	                		  
	                		  //WRITING PARTY DETAILS ON PDF
	                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
	                		  
	                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
	                		  
	                		  if(roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE_OWNER_SELF || 
	                				  roleId==RegInitConstant.ROLE_TRUSTEE || roleId==RegInitConstant.ROLE_ADOPTING_PARENT ||
	                				  roleId==RegInitConstant.ROLE_CANCELLATION_1 || roleId==RegInitConstant.ROLE_CANCELLATION_1_POA_HOLDER ||
	                				  roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER
	                				  || roleId==RegInitConstant.ROLE_ADOPTING_PARENT_POA_HOLDER
	                				  || roleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER || roleId==RegInitConstant.ROLE_EXECUTANT_SELF
	                				  || roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER || roleId==RegInitConstant.ROLE_MORTGAGER_SELF
	                				  || roleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANS_PARTY_SELF 
	                				  || regForm.getCommonDeed()==1)
	                		  {
	                			  datatable.addCell(row);
	                			  sellerCount++;
	                			  if(roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER || roleId==RegInitConstant.ROLE_OWNER_SELF){
	                			  Cell ownerHdr=new Cell(new Phrase("Owner "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  ownerHdr.setHeader(true);
	                			  ownerHdr.setColspan(22);
	            			      datatable.addCell(ownerHdr);
	            			      }
	                			  if(roleId==RegInitConstant.ROLE_TRUSTEE || roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
	                			      Cell trusteeHdr=new Cell(new Phrase("Trustee "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      trusteeHdr.setHeader(true);
	                			      trusteeHdr.setColspan(22);
	                			      datatable.addCell(trusteeHdr);
	                			  }
	                			  if(roleId==RegInitConstant.ROLE_ADOPTING_PARENT || roleId==RegInitConstant.ROLE_ADOPTING_PARENT_POA_HOLDER){
	                			      Cell adoptParentHdr=new Cell(new Phrase("Adopting parent "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      adoptParentHdr.setHeader(true);
	                			      adoptParentHdr.setColspan(22);
	                			      datatable.addCell(adoptParentHdr);
	                			  }
	                			  /*if(roleId==RegInitConstant.ROLE_CANCELLATION_1 || roleId==RegInitConstant.ROLE_CANCELLATION_1_POA_HOLDER){
	                			      Cell adoptParentHdr=new Cell(new Phrase("Executant "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      adoptParentHdr.setHeader(true);
	                			      adoptParentHdr.setColspan(22);
	                			      datatable.addCell(adoptParentHdr);
	                			  }*/
	                			  if(roleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER || roleId==RegInitConstant.ROLE_EXECUTANT_SELF){
		                			  Cell ownerHdr=new Cell(new Phrase("Executant "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			  ownerHdr.setHeader(true);
		                			  ownerHdr.setColspan(22);
		            			      datatable.addCell(ownerHdr);
		            			      }
	                			  if(roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER || roleId==RegInitConstant.ROLE_MORTGAGER_SELF){
		                			  Cell ownerHdr=new Cell(new Phrase("Mortgager "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			  ownerHdr.setHeader(true);
		                			  ownerHdr.setColspan(22);
		            			      datatable.addCell(ownerHdr);
		            			      }
	                			  if(roleId==RegInitConstant.ROLE_TRANS_PARTY_POA_HOLDER || roleId==RegInitConstant.ROLE_TRANS_PARTY_SELF){
		                			  Cell ownerHdr=new Cell(new Phrase("Transacting Party "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			  ownerHdr.setHeader(true);
		                			  ownerHdr.setColspan(22);
		            			      datatable.addCell(ownerHdr);
		            			      }
	                			  
	                			  if(regForm.getCommonDeed()==1){
	                				  Cell ownerHdr=new Cell(new Phrase("Party No: "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			  ownerHdr.setHeader(true);
		                			  ownerHdr.setColspan(22);
		            			      datatable.addCell(ownerHdr);
		            			      Cell roleHdr=new Cell(new Phrase("Role: "+ mapDto.getRoleName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		            			      roleHdr.setHeader(true);
		            			      roleHdr.setColspan(22);
		            			      datatable.addCell(roleHdr);
	                			  }
	                			  
	                			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  partyId.setHeader(true);
	                			  partyId.setColspan(8);
	            			      datatable.addCell(partyId);
	            			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      partyIdvalue.setHeader(true);
	            			      partyIdvalue.setColspan(14);
	            			      datatable.addCell(partyIdvalue);
	                			 
	                			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  appType.setHeader(true);
	                			  appType.setColspan(8);
	            			      datatable.addCell(appType);
	            			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      appTypevalue.setHeader(true);
	            			      appTypevalue.setColspan(14);
	            			      datatable.addCell(appTypevalue);
	            			      
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	            			    	  
	            			    	  setCommonOrgOnPdf(mapDto,datatable);
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(8);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(14);
	                			      datatable.addCell(sharevalue);
	                			      }
	            			    	  
	            			      }
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	            			    	  
	            			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  indName.setHeader(true);
	            			    	  indName.setColspan(8);
	                			      datatable.addCell(indName);
	                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      indNamevalue.setHeader(true);
	                			      indNamevalue.setColspan(14);
	                			      datatable.addCell(indNamevalue);
	                			      
	                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      relation.setHeader(true);
	                			      relation.setColspan(5);
	                			      datatable.addCell(relation);
	                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      relationvalue.setHeader(true);
	                			      relationvalue.setColspan(6);
	                			      datatable.addCell(relationvalue);
	                			   
	                			      
	                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fathername.setHeader(true);
	                			      fathername.setColspan(5);
	                			      datatable.addCell(fathername);
	                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fathernamevalue.setHeader(true);
	                			      fathernamevalue.setColspan(6);
	                			      datatable.addCell(fathernamevalue);
	                			      
	                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gender.setHeader(true);
	                			      gender.setColspan(5);
	                			      datatable.addCell(gender);
	                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gendervalue.setHeader(true);
	                			      gendervalue.setColspan(6);
	                			      datatable.addCell(gendervalue);
	                			      
	                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fname.setHeader(true);
	                			      fname.setColspan(5);
	                			      datatable.addCell(fname);
	                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fnamevalue.setHeader(true);
	                			      fnamevalue.setColspan(6);
	                			      datatable.addCell(fnamevalue);*/
	                			      
	                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);*/
	                			      
	                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);
	                			   
	                			      
	                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gname.setHeader(true);
	                			      gname.setColspan(5);
	                			      datatable.addCell(gname);
	                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gnamevalue.setHeader(true);
	                			      gnamevalue.setColspan(6);
	                			      datatable.addCell(gnamevalue);
	                			      
	                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      sname.setHeader(true);
	                			      sname.setColspan(5);
	                			      datatable.addCell(sname);
	                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      snamevalue.setHeader(true);
	                			      snamevalue.setColspan(6);
	                			      datatable.addCell(snamevalue);
	                			      
	                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      email.setHeader(true);
	                			      email.setColspan(5);
	                			      datatable.addCell(email);
	                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      emailvalue.setHeader(true);
	                			      emailvalue.setColspan(6);
	                			      datatable.addCell(emailvalue);
	                			      
	                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      category.setHeader(true);
	                			      category.setColspan(5);
	                			      datatable.addCell(category);
	                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      categoryvalue.setHeader(true);
	                			      categoryvalue.setColspan(6);
	                			      datatable.addCell(categoryvalue);
	                			      
	                			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
	                			    	  
	                			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  scheduleArea.setHeader(true);
	                			    	  scheduleArea.setColspan(8);
	                    			      datatable.addCell(scheduleArea);
	                    			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      scheduleAreavalue.setHeader(true);
	                    			      scheduleAreavalue.setColspan(14);
	                    			      datatable.addCell(scheduleAreavalue);
	                    			      
	                    			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	                    			    	  
	                    			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    			    	  permissionNo.setHeader(true);
	                    			    	  permissionNo.setColspan(5);
	                        			      datatable.addCell(permissionNo);
	                        			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      permissionNovalue.setHeader(true);
	                        			      permissionNovalue.setColspan(6);
	                        			      datatable.addCell(permissionNovalue);
	                        			      
	                        			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                        			      certificate.setHeader(true);
	                        			      certificate.setColspan(5);
	                        			      datatable.addCell(certificate);
	                        			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      certificatevalue.setHeader(true);
	                        			      certificatevalue.setColspan(6);
	                        			      datatable.addCell(certificatevalue);
	                    			    	  
	                    			      }
	                			    	  
	                			      }
	                			      
	                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(5);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(6);
	                			      datatable.addCell(nationalityvalue);
	                			      
	                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      disability.setHeader(true);
	                			      disability.setColspan(5);
	                			      datatable.addCell(disability);
	                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      disabilityvalue.setHeader(true);
	                			      disabilityvalue.setColspan(6);
	                			      datatable.addCell(disabilityvalue);
	                			      
	                			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
	                			    	  
	                			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  disabilityDesc.setHeader(true);
	                			    	  disabilityDesc.setColspan(8);
	                    			      datatable.addCell(disabilityDesc);
	                    			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      disabilityDescvalue.setHeader(true);
	                    			      disabilityDescvalue.setColspan(14);
	                    			      datatable.addCell(disabilityDescvalue);
	                			    	  
	                			      }
	                			      
	                			      
	                			      
	                			      
	                			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(8);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(14);
	                			      datatable.addCell(nationalityvalue);*/
	                			      
	                			      
	                			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      minority.setHeader(true);
	                			      minority.setColspan(5);
	                			      datatable.addCell(minority);
	                			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      minorityvalue.setHeader(true);
	                			      minorityvalue.setColspan(6);
	                			      datatable.addCell(minorityvalue);
	                			      
	                			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      poverty.setHeader(true);
	                			      poverty.setColspan(5);
	                			      datatable.addCell(poverty);
	                			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      povertyvalue.setHeader(true);
	                			      povertyvalue.setColspan(6);
	                			      datatable.addCell(povertyvalue);
	                			      
	                			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      phNo.setHeader(true);
	                			      phNo.setColspan(5);
	                			      datatable.addCell(phNo);
	                			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      phNovalue.setHeader(true);
	                			      phNovalue.setColspan(6);
	                			      datatable.addCell(phNovalue);
	                			      
	                			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      mobNo.setHeader(true);
	                			      mobNo.setColspan(5);
	                			      datatable.addCell(mobNo);
	                			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      mobNovalue.setHeader(true);
	                			      mobNovalue.setColspan(6);
	                			      datatable.addCell(mobNovalue);
	                			      
	                			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      address.setHeader(true);
	                			      address.setColspan(8);
	                			      datatable.addCell(address);
	                			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      addressvalue.setHeader(true);
	                			      addressvalue.setColspan(14);
	                			      datatable.addCell(addressvalue);
	                			      
	                			      
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(5);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(6);
	                			      datatable.addCell(sharevalue);
	                			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(8);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(14);
	                			      datatable.addCell(occupationvalue);
	                			      }else{
	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			                      			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(5);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(6);
	                			      datatable.addCell(occupationvalue);
	                			    	  
	                			      }
	                			      
	                			      
	                			      
	                			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proof.setHeader(true);
	                			      proof.setColspan(5);
	                			      datatable.addCell(proof);
	                			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofvalue.setHeader(true);
	                			      proofvalue.setColspan(6);
	                			      datatable.addCell(proofvalue);
	                			      
	                			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proofId.setHeader(true);
	                			      proofId.setColspan(5);
	                			      datatable.addCell(proofId);
	                			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofIdvalue.setHeader(true);
	                			      proofIdvalue.setColspan(6);
	                			      datatable.addCell(proofIdvalue);
	                			      
	             		      }
	            			     
	            			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  		                   		upl2.setHeader(true);
  		                   		upl2.setColspan(8);
  		                   		datatable.addCell(upl2);
  		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  		                   		upl2value.setHeader(true);
  		                   		upl2value.setColspan(14);
  		                   		datatable.addCell(upl2value);
  		                   		
  		                   		if(regForm.getDeedTypeFlag()==0){
  		                   			
  		                   		setComplianceListOnPdf(mapDto,datatable);
  		                   			
  		                   		}
	            			      
	            			      
	                			  //OWNER DETAILS IN CASE OF POA BELOW
	                		
	            			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
	            			    		  mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG)
	            			      {
	            			    	  setOwnerOnPdf(mapDto,datatable);
	            			    	  /*
	            			    	  
	            			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	            			    	  ownerHdr.setHeader(true);
	            			    	  ownerHdr.setColspan(22);
	                			      datatable.addCell(ownerHdr);
	            			    	  
	            			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  ownerName.setHeader(true);
	            			    	  ownerName.setColspan(5);
	                			      datatable.addCell(ownerName);
	                			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNamevalue.setHeader(true);
	                			      ownerNamevalue.setColspan(6);
	                			      datatable.addCell(ownerNamevalue);
	                			      
	                			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerOrgName.setHeader(true);
	                			      ownerOrgName.setColspan(5);
	                			      datatable.addCell(ownerOrgName);
	                			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerOrgNamevalue.setHeader(true);
	                			      ownerOrgNamevalue.setColspan(6);
	                			      datatable.addCell(ownerOrgNamevalue);
	                			      
	                			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerGender.setHeader(true);
	                			      ownerGender.setColspan(5);
	                			      datatable.addCell(ownerGender);
	                			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerGendervalue.setHeader(true);
	                			      ownerGendervalue.setColspan(6);
	                			      datatable.addCell(ownerGendervalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerNationality.setHeader(true);
	                			      ownerNationality.setColspan(8);
	                			      datatable.addCell(ownerNationality);
	                			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNationalityvalue.setHeader(true);
	                			      ownerNationalityvalue.setColspan(14);
	                			      datatable.addCell(ownerNationalityvalue);
	                			      
	                			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerAddress.setHeader(true);
	                			      ownerAddress.setColspan(8);
	                			      datatable.addCell(ownerAddress);
	                			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerAddressvalue.setHeader(true);
	                			      ownerAddressvalue.setColspan(14);
	                			      datatable.addCell(ownerAddressvalue);
	                			      
	                			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerMobile.setHeader(true);
	                			      ownerMobile.setColspan(5);
	                			      datatable.addCell(ownerMobile);
	                			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerMobilevalue.setHeader(true);
	                			      ownerMobilevalue.setColspan(6);
	                			      datatable.addCell(ownerMobilevalue);
	                			      
	                			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerEmail.setHeader(true);
	                			      ownerEmail.setColspan(5);
	                			      datatable.addCell(ownerEmail);
	                			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerEmailvalue.setHeader(true);
	                			      ownerEmailvalue.setColspan(6);
	                			      datatable.addCell(ownerEmailvalue);
	                			      
	                			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProof.setHeader(true);
	                			      ownerProof.setColspan(5);
	                			      datatable.addCell(ownerProof);
	                			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofvalue.setHeader(true);
	                			      ownerProofvalue.setColspan(6);
	                			      datatable.addCell(ownerProofvalue);
	                			      
	                			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProofId.setHeader(true);
	                			      ownerProofId.setColspan(5);
	                			      datatable.addCell(ownerProofId);
	                			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofIdvalue.setHeader(true);
	                			      ownerProofIdvalue.setColspan(6);
	                			      datatable.addCell(ownerProofIdvalue);
	            			    	  
	            			    	  
	            			    	  
	            			    	  
	            			      */}
	                		  
	                		  
	                		  
	                		  
	                		  
	                		  
	                	  }
	                		  
	                		  
	                		  
	                		  
	                	  }
	                	  sellerCount=0;
	    			      //int buyerCount=0;
	    			      
	                	  for(int j=0;j<partyList.size();j++){                          //CHILD_ADOPTED loop
	                		  mapDto=(RegCommonDTO)partyList.get(j);
	                		  
	                		  //WRITING PARTY DETAILS ON PDF
	                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
	                		  
	                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
	                		  
	                		  if(roleId==RegInitConstant.ROLE_CHILD_ADOPTED || roleId==RegInitConstant.ROLE_CANCELLATION_2 ||
	                				  roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER || roleId==RegInitConstant.ROLE_CLAIMANT_SELF ||
	                				  roleId==RegInitConstant.ROLE_CHILD_ADOPTED_POA_HOLDER || roleId==RegInitConstant.ROLE_CANCELLATION_2_POA_HOLDER ||
	                				  roleId==RegInitConstant.ROLE_CLAIMANT_SELF || roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER ||
	                				  roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER ||
	                				  roleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || roleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER)
	                		  {
	                			  datatable.addCell(row);
	                			  sellerCount++;
	                			  
	                			  if(roleId==RegInitConstant.ROLE_CHILD_ADOPTED || roleId==RegInitConstant.ROLE_CHILD_ADOPTED_POA_HOLDER){
	                			      Cell childAdptHdr=new Cell(new Phrase("Child Adopted "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      childAdptHdr.setHeader(true);
	                			      childAdptHdr.setColspan(22);
	                			      datatable.addCell(childAdptHdr);
	                			      }
	                			  if(roleId==RegInitConstant.ROLE_CLAIMANT_SELF || roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
	                			      Cell childAdptHdr=new Cell(new Phrase("Claimant "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      childAdptHdr.setHeader(true);
	                			      childAdptHdr.setColspan(22);
	                			      datatable.addCell(childAdptHdr);
	                			      }
	                			  if(roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
	                			      Cell childAdptHdr=new Cell(new Phrase("Mortgagee "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      childAdptHdr.setHeader(true);
	                			      childAdptHdr.setColspan(22);
	                			      datatable.addCell(childAdptHdr);
	                			      }
	                			  if(roleId==RegInitConstant.ROLE_CANCELLATION_2 || roleId==RegInitConstant.ROLE_CANCELLATION_2_POA_HOLDER){
	                			      Cell adoptParentHdr=new Cell(new Phrase("Party "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      adoptParentHdr.setHeader(true);
	                			      adoptParentHdr.setColspan(22);
	                			      datatable.addCell(adoptParentHdr);
	                			  }
	                			  if(roleId==RegInitConstant.ROLE_RETIRING_PARTY_SELF || roleId==RegInitConstant.ROLE_RETIRING_PARTY_POA_HOLDER){
	                			      Cell childAdptHdr=new Cell(new Phrase("Retiring Party "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      childAdptHdr.setHeader(true);
	                			      childAdptHdr.setColspan(22);
	                			      datatable.addCell(childAdptHdr);
	                			      }
	                			  /*if(roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER || roleId==RegInitConstant.ROLE_CLAIMANT_SELF){
		                			  Cell ownerHdr=new Cell(new Phrase("Claimant "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			  ownerHdr.setHeader(true);
		                			  ownerHdr.setColspan(22);
		            			      datatable.addCell(ownerHdr);
		            			      }*/
	                			  
	                			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  partyId.setHeader(true);
	                			  partyId.setColspan(8);
	            			      datatable.addCell(partyId);
	            			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      partyIdvalue.setHeader(true);
	            			      partyIdvalue.setColspan(14);
	            			      datatable.addCell(partyIdvalue);
	                			 
	                			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  appType.setHeader(true);
	                			  appType.setColspan(8);
	            			      datatable.addCell(appType);
	            			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      appTypevalue.setHeader(true);
	            			      appTypevalue.setColspan(14);
	            			      datatable.addCell(appTypevalue);
	            			      
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	            			    	  
	            			    	  setCommonOrgOnPdf(mapDto,datatable);
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(8);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(14);
	                			      datatable.addCell(sharevalue);
	                			      }
	            			    	  
	            			      }
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	            			    	  
	            			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  indName.setHeader(true);
	            			    	  indName.setColspan(8);
	                			      datatable.addCell(indName);
	                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      indNamevalue.setHeader(true);
	                			      indNamevalue.setColspan(14);
	                			      datatable.addCell(indNamevalue);
	                			      
	                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      relation.setHeader(true);
	                			      relation.setColspan(5);
	                			      datatable.addCell(relation);
	                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      relationvalue.setHeader(true);
	                			      relationvalue.setColspan(6);
	                			      datatable.addCell(relationvalue);
	                			   
	                			      
	                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fathername.setHeader(true);
	                			      fathername.setColspan(5);
	                			      datatable.addCell(fathername);
	                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fathernamevalue.setHeader(true);
	                			      fathernamevalue.setColspan(6);
	                			      datatable.addCell(fathernamevalue);
	                			      
	                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gender.setHeader(true);
	                			      gender.setColspan(5);
	                			      datatable.addCell(gender);
	                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gendervalue.setHeader(true);
	                			      gendervalue.setColspan(6);
	                			      datatable.addCell(gendervalue);
	                			      
	                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fname.setHeader(true);
	                			      fname.setColspan(5);
	                			      datatable.addCell(fname);
	                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fnamevalue.setHeader(true);
	                			      fnamevalue.setColspan(6);
	                			      datatable.addCell(fnamevalue);*/
	                			      
	                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);*/
	                			      
	                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);
	                			   
	                			      
	                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gname.setHeader(true);
	                			      gname.setColspan(5);
	                			      datatable.addCell(gname);
	                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gnamevalue.setHeader(true);
	                			      gnamevalue.setColspan(6);
	                			      datatable.addCell(gnamevalue);
	                			      
	                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      sname.setHeader(true);
	                			      sname.setColspan(5);
	                			      datatable.addCell(sname);
	                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      snamevalue.setHeader(true);
	                			      snamevalue.setColspan(6);
	                			      datatable.addCell(snamevalue);
	                			      
	                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      email.setHeader(true);
	                			      email.setColspan(5);
	                			      datatable.addCell(email);
	                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      emailvalue.setHeader(true);
	                			      emailvalue.setColspan(6);
	                			      datatable.addCell(emailvalue);
	                			      
	                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      category.setHeader(true);
	                			      category.setColspan(5);
	                			      datatable.addCell(category);
	                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      categoryvalue.setHeader(true);
	                			      categoryvalue.setColspan(6);
	                			      datatable.addCell(categoryvalue);
	                			      
	                			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
	                			    	  
	                			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  scheduleArea.setHeader(true);
	                			    	  scheduleArea.setColspan(8);
	                    			      datatable.addCell(scheduleArea);
	                    			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      scheduleAreavalue.setHeader(true);
	                    			      scheduleAreavalue.setColspan(14);
	                    			      datatable.addCell(scheduleAreavalue);
	                    			      
	                    			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	                    			    	  
	                    			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    			    	  permissionNo.setHeader(true);
	                    			    	  permissionNo.setColspan(5);
	                        			      datatable.addCell(permissionNo);
	                        			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      permissionNovalue.setHeader(true);
	                        			      permissionNovalue.setColspan(6);
	                        			      datatable.addCell(permissionNovalue);
	                        			      
	                        			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                        			      certificate.setHeader(true);
	                        			      certificate.setColspan(5);
	                        			      datatable.addCell(certificate);
	                        			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      certificatevalue.setHeader(true);
	                        			      certificatevalue.setColspan(6);
	                        			      datatable.addCell(certificatevalue);
	                    			    	  
	                    			      }
	                			    	  
	                			      }
	                			      
	                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(5);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(6);
	                			      datatable.addCell(nationalityvalue);
	                			      
	                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      disability.setHeader(true);
	                			      disability.setColspan(5);
	                			      datatable.addCell(disability);
	                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      disabilityvalue.setHeader(true);
	                			      disabilityvalue.setColspan(6);
	                			      datatable.addCell(disabilityvalue);
	                			      
	                			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
	                			    	  
	                			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  disabilityDesc.setHeader(true);
	                			    	  disabilityDesc.setColspan(8);
	                    			      datatable.addCell(disabilityDesc);
	                    			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      disabilityDescvalue.setHeader(true);
	                    			      disabilityDescvalue.setColspan(14);
	                    			      datatable.addCell(disabilityDescvalue);
	                			    	  
	                			      }
	                			      
	                			     
	                			      
	                			      
	                			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(8);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(14);
	                			      datatable.addCell(nationalityvalue);*/
	                			      
	                			      
	                			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      minority.setHeader(true);
	                			      minority.setColspan(5);
	                			      datatable.addCell(minority);
	                			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      minorityvalue.setHeader(true);
	                			      minorityvalue.setColspan(6);
	                			      datatable.addCell(minorityvalue);
	                			      
	                			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      poverty.setHeader(true);
	                			      poverty.setColspan(5);
	                			      datatable.addCell(poverty);
	                			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      povertyvalue.setHeader(true);
	                			      povertyvalue.setColspan(6);
	                			      datatable.addCell(povertyvalue);
	                			      
	                			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      phNo.setHeader(true);
	                			      phNo.setColspan(5);
	                			      datatable.addCell(phNo);
	                			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      phNovalue.setHeader(true);
	                			      phNovalue.setColspan(6);
	                			      datatable.addCell(phNovalue);
	                			      
	                			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      mobNo.setHeader(true);
	                			      mobNo.setColspan(5);
	                			      datatable.addCell(mobNo);
	                			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      mobNovalue.setHeader(true);
	                			      mobNovalue.setColspan(6);
	                			      datatable.addCell(mobNovalue);
	                			      
	                			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      address.setHeader(true);
	                			      address.setColspan(8);
	                			      datatable.addCell(address);
	                			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      addressvalue.setHeader(true);
	                			      addressvalue.setColspan(14);
	                			      datatable.addCell(addressvalue);
	                			      
	                			      
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
	                			      	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(5);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(6);
	                			      datatable.addCell(sharevalue);
	                			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(8);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(14);
	                			      datatable.addCell(occupationvalue);
	                			      }else{
	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			                      			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(5);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(6);
	                			      datatable.addCell(occupationvalue);
	                			    	  
	                			      }
	                			      
	                			      
	                			      
	                			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proof.setHeader(true);
	                			      proof.setColspan(5);
	                			      datatable.addCell(proof);
	                			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofvalue.setHeader(true);
	                			      proofvalue.setColspan(6);
	                			      datatable.addCell(proofvalue);
	                			      
	                			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proofId.setHeader(true);
	                			      proofId.setColspan(5);
	                			      datatable.addCell(proofId);
	                			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofIdvalue.setHeader(true);
	                			      proofIdvalue.setColspan(6);
	                			      datatable.addCell(proofIdvalue);
	                			      
	             		      }
	            			     
	            			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  		                   		upl2.setHeader(true);
  		                   		upl2.setColspan(8);
  		                   		datatable.addCell(upl2);
  		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  		                   		upl2value.setHeader(true);
  		                   		upl2value.setColspan(14);
  		                   		datatable.addCell(upl2value);
  		                   		

  		                   		if(regForm.getDeedTypeFlag()==0){
  		                   			
  		                   		setComplianceListOnPdf(mapDto,datatable);
  		                   			
  		                   		}
	            			      
	            			      
	                			  //OWNER DETAILS IN CASE OF POA BELOW
	                		
	            			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
	            			      {
	            			    	  
	            			    	  setOwnerOnPdf(mapDto,datatable);
	            			    	  
	            			    	  /*
	            			    	  
	            			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	            			    	  ownerHdr.setHeader(true);
	            			    	  ownerHdr.setColspan(22);
	                			      datatable.addCell(ownerHdr);
	            			    	  
	            			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  ownerName.setHeader(true);
	            			    	  ownerName.setColspan(5);
	                			      datatable.addCell(ownerName);
	                			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNamevalue.setHeader(true);
	                			      ownerNamevalue.setColspan(6);
	                			      datatable.addCell(ownerNamevalue);
	                			      
	                			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerOrgName.setHeader(true);
	                			      ownerOrgName.setColspan(5);
	                			      datatable.addCell(ownerOrgName);
	                			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerOrgNamevalue.setHeader(true);
	                			      ownerOrgNamevalue.setColspan(6);
	                			      datatable.addCell(ownerOrgNamevalue);
	                			      
	                			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerGender.setHeader(true);
	                			      ownerGender.setColspan(5);
	                			      datatable.addCell(ownerGender);
	                			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerGendervalue.setHeader(true);
	                			      ownerGendervalue.setColspan(6);
	                			      datatable.addCell(ownerGendervalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerNationality.setHeader(true);
	                			      ownerNationality.setColspan(8);
	                			      datatable.addCell(ownerNationality);
	                			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNationalityvalue.setHeader(true);
	                			      ownerNationalityvalue.setColspan(14);
	                			      datatable.addCell(ownerNationalityvalue);
	                			      
	                			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerAddress.setHeader(true);
	                			      ownerAddress.setColspan(8);
	                			      datatable.addCell(ownerAddress);
	                			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerAddressvalue.setHeader(true);
	                			      ownerAddressvalue.setColspan(14);
	                			      datatable.addCell(ownerAddressvalue);
	                			      
	                			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerMobile.setHeader(true);
	                			      ownerMobile.setColspan(5);
	                			      datatable.addCell(ownerMobile);
	                			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerMobilevalue.setHeader(true);
	                			      ownerMobilevalue.setColspan(6);
	                			      datatable.addCell(ownerMobilevalue);
	                			      
	                			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerEmail.setHeader(true);
	                			      ownerEmail.setColspan(5);
	                			      datatable.addCell(ownerEmail);
	                			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerEmailvalue.setHeader(true);
	                			      ownerEmailvalue.setColspan(6);
	                			      datatable.addCell(ownerEmailvalue);
	                			      
	                			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProof.setHeader(true);
	                			      ownerProof.setColspan(5);
	                			      datatable.addCell(ownerProof);
	                			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofvalue.setHeader(true);
	                			      ownerProofvalue.setColspan(6);
	                			      datatable.addCell(ownerProofvalue);
	                			      
	                			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProofId.setHeader(true);
	                			      ownerProofId.setColspan(5);
	                			      datatable.addCell(ownerProofId);
	                			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofIdvalue.setHeader(true);
	                			      ownerProofIdvalue.setColspan(6);
	                			      datatable.addCell(ownerProofIdvalue);
	            			    	  
	            			    	  
	            			    	  
	            			    	  
	            			      */}
	                		  
	                		  
	                		  
	                		  
	                		  
	                		  
	                	  }
	                		  
	                		  
	                		  
	                		  
	                	  }
	                	  sellerCount=0;
	    			      //int buyerCount=0;
	    			      
	                	  for(int j=0;j<partyList.size();j++){                          //custodian loop
	                		  mapDto=(RegCommonDTO)partyList.get(j);
	                		  
	                		  //WRITING PARTY DETAILS ON PDF
	                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
	                		  
	                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
	                		  
	                		  if(roleId==RegInitConstant.ROLE_CUSTODIAN ||
	                				  roleId==RegInitConstant.ROLE_CUSTODIAN_POA_HOLDER)
	                		  {
	                			  datatable.addCell(row);
	                			  sellerCount++;
	                			  
	                			  if(roleId==RegInitConstant.ROLE_CUSTODIAN ||
		                				  roleId==RegInitConstant.ROLE_CUSTODIAN_POA_HOLDER){
	                			      Cell custdnHdr=new Cell(new Phrase("Custodian "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      custdnHdr.setHeader(true);
	                			      custdnHdr.setColspan(22);
	                			      datatable.addCell(custdnHdr);
	                			      }
	                			  
	                			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  partyId.setHeader(true);
	                			  partyId.setColspan(8);
	            			      datatable.addCell(partyId);
	            			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      partyIdvalue.setHeader(true);
	            			      partyIdvalue.setColspan(14);
	            			      datatable.addCell(partyIdvalue);
	                			 
	                			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			  appType.setHeader(true);
	                			  appType.setColspan(8);
	            			      datatable.addCell(appType);
	            			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	            			      appTypevalue.setHeader(true);
	            			      appTypevalue.setColspan(14);
	            			      datatable.addCell(appTypevalue);
	            			      
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
	            			    	  
	            			    	  setCommonOrgOnPdf(mapDto,datatable);
	                			      
	                			      if(deedId==RegInitConstant.DEED_ADOPTION){
                    			    	  Cell rel=new Cell(new Phrase("Relation with child", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(8);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(14);
	                			      datatable.addCell(sharevalue);
	                			      }
	            			    	  
	            			      }
	            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
	            			    	  
	            			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  indName.setHeader(true);
	            			    	  indName.setColspan(8);
	                			      datatable.addCell(indName);
	                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      indNamevalue.setHeader(true);
	                			      indNamevalue.setColspan(14);
	                			      datatable.addCell(indNamevalue);
	                			      
	                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      relation.setHeader(true);
	                			      relation.setColspan(5);
	                			      datatable.addCell(relation);
	                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      relationvalue.setHeader(true);
	                			      relationvalue.setColspan(6);
	                			      datatable.addCell(relationvalue);
	                			   
	                			      
	                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fathername.setHeader(true);
	                			      fathername.setColspan(5);
	                			      datatable.addCell(fathername);
	                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fathernamevalue.setHeader(true);
	                			      fathernamevalue.setColspan(6);
	                			      datatable.addCell(fathernamevalue);
	                			      
	                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gender.setHeader(true);
	                			      gender.setColspan(5);
	                			      datatable.addCell(gender);
	                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gendervalue.setHeader(true);
	                			      gendervalue.setColspan(6);
	                			      datatable.addCell(gendervalue);
	                			      
	                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fname.setHeader(true);
	                			      fname.setColspan(5);
	                			      datatable.addCell(fname);
	                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fnamevalue.setHeader(true);
	                			      fnamevalue.setColspan(6);
	                			      datatable.addCell(fnamevalue);*/
	                			      
	                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);*/
	                			      
	                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);
	                			   
	                			      
	                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gname.setHeader(true);
	                			      gname.setColspan(5);
	                			      datatable.addCell(gname);
	                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gnamevalue.setHeader(true);
	                			      gnamevalue.setColspan(6);
	                			      datatable.addCell(gnamevalue);
	                			      
	                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      sname.setHeader(true);
	                			      sname.setColspan(5);
	                			      datatable.addCell(sname);
	                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      snamevalue.setHeader(true);
	                			      snamevalue.setColspan(6);
	                			      datatable.addCell(snamevalue);
	                			      
	                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      email.setHeader(true);
	                			      email.setColspan(5);
	                			      datatable.addCell(email);
	                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      emailvalue.setHeader(true);
	                			      emailvalue.setColspan(6);
	                			      datatable.addCell(emailvalue);
	                			      
	                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      category.setHeader(true);
	                			      category.setColspan(5);
	                			      datatable.addCell(category);
	                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      categoryvalue.setHeader(true);
	                			      categoryvalue.setColspan(6);
	                			      datatable.addCell(categoryvalue);
	                			      
	                			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
	                			    	  
	                			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  scheduleArea.setHeader(true);
	                			    	  scheduleArea.setColspan(8);
	                    			      datatable.addCell(scheduleArea);
	                    			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      scheduleAreavalue.setHeader(true);
	                    			      scheduleAreavalue.setColspan(14);
	                    			      datatable.addCell(scheduleAreavalue);
	                    			      
	                    			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
	                    			    	  
	                    			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    			    	  permissionNo.setHeader(true);
	                    			    	  permissionNo.setColspan(5);
	                        			      datatable.addCell(permissionNo);
	                        			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      permissionNovalue.setHeader(true);
	                        			      permissionNovalue.setColspan(6);
	                        			      datatable.addCell(permissionNovalue);
	                        			      
	                        			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                        			      certificate.setHeader(true);
	                        			      certificate.setColspan(5);
	                        			      datatable.addCell(certificate);
	                        			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                        			      certificatevalue.setHeader(true);
	                        			      certificatevalue.setColspan(6);
	                        			      datatable.addCell(certificatevalue);
	                    			    	  
	                    			      }
	                			    	  
	                			      }
	                			      
	                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(5);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(6);
	                			      datatable.addCell(nationalityvalue);
	                			      
	                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      disability.setHeader(true);
	                			      disability.setColspan(5);
	                			      datatable.addCell(disability);
	                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      disabilityvalue.setHeader(true);
	                			      disabilityvalue.setColspan(6);
	                			      datatable.addCell(disabilityvalue);
	                			      
	                			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
	                			    	  
	                			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			    	  disabilityDesc.setHeader(true);
	                			    	  disabilityDesc.setColspan(8);
	                    			      datatable.addCell(disabilityDesc);
	                    			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                    			      disabilityDescvalue.setHeader(true);
	                    			      disabilityDescvalue.setColspan(14);
	                    			      datatable.addCell(disabilityDescvalue);
	                			    	  
	                			      }
	                			      
	                			      
	                			      
	                			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(8);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(14);
	                			      datatable.addCell(nationalityvalue);
	                			      */
	                			      
	                			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      minority.setHeader(true);
	                			      minority.setColspan(5);
	                			      datatable.addCell(minority);
	                			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      minorityvalue.setHeader(true);
	                			      minorityvalue.setColspan(6);
	                			      datatable.addCell(minorityvalue);
	                			      
	                			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      poverty.setHeader(true);
	                			      poverty.setColspan(5);
	                			      datatable.addCell(poverty);
	                			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      povertyvalue.setHeader(true);
	                			      povertyvalue.setColspan(6);
	                			      datatable.addCell(povertyvalue);
	                			      
	                			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      phNo.setHeader(true);
	                			      phNo.setColspan(5);
	                			      datatable.addCell(phNo);
	                			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      phNovalue.setHeader(true);
	                			      phNovalue.setColspan(6);
	                			      datatable.addCell(phNovalue);
	                			      
	                			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      mobNo.setHeader(true);
	                			      mobNo.setColspan(5);
	                			      datatable.addCell(mobNo);
	                			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      mobNovalue.setHeader(true);
	                			      mobNovalue.setColspan(6);
	                			      datatable.addCell(mobNovalue);
	                			      
	                			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      address.setHeader(true);
	                			      address.setColspan(8);
	                			      datatable.addCell(address);
	                			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      addressvalue.setHeader(true);
	                			      addressvalue.setColspan(14);
	                			      datatable.addCell(addressvalue);
	                			      
	                			      if(deedId==RegInitConstant.DEED_ADOPTION){
                    			    	  Cell rel=new Cell(new Phrase("Relation with child", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }
	                			      
	                			      if(regForm.getDeedTypeFlag()==0){
  	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      share.setHeader(true);
	                			      share.setColspan(5);
	                			      datatable.addCell(share);
	                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      sharevalue.setHeader(true);
	                			      sharevalue.setColspan(6);
	                			      datatable.addCell(sharevalue);
	                			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(8);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(14);
	                			      datatable.addCell(occupationvalue);
	                			      }else{
	                			    	  
	                			    	  Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                			      postal.setHeader(true);
		                			      postal.setColspan(5);
		                			      datatable.addCell(postal);
		                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                			      postalvalue.setHeader(true);
		                			      postalvalue.setColspan(6);
		                			      datatable.addCell(postalvalue);
		                			      
	                			                      			      
	                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      occupation.setHeader(true);
	                			      occupation.setColspan(5);
	                			      datatable.addCell(occupation);
	                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      occupationvalue.setHeader(true);
	                			      occupationvalue.setColspan(6);
	                			      datatable.addCell(occupationvalue);
	                			    	  
	                			      }
	                			      
	                			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proof.setHeader(true);
	                			      proof.setColspan(5);
	                			      datatable.addCell(proof);
	                			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofvalue.setHeader(true);
	                			      proofvalue.setColspan(6);
	                			      datatable.addCell(proofvalue);
	                			      
	                			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      proofId.setHeader(true);
	                			      proofId.setColspan(5);
	                			      datatable.addCell(proofId);
	                			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      proofIdvalue.setHeader(true);
	                			      proofIdvalue.setColspan(6);
	                			      datatable.addCell(proofIdvalue);
	                			      
	             		      }
	            			     
	            			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  		                   		upl2.setHeader(true);
  		                   		upl2.setColspan(8);
  		                   		datatable.addCell(upl2);
  		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  		                   		upl2value.setHeader(true);
  		                   		upl2value.setColspan(14);
  		                   		datatable.addCell(upl2value);
	            			      

  		                   		if(regForm.getDeedTypeFlag()==0){
  		                   			
  		                   		setComplianceListOnPdf(mapDto,datatable);
  		                   			
  		                   		}
	                			  //OWNER DETAILS IN CASE OF POA BELOW
	                		
	            			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
	            			      {
	            			    	  
	            			    	  setOwnerOnPdf(mapDto,datatable);
	            			    	  
	            			    	  /*
	            			    	  
	            			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	            			    	  ownerHdr.setHeader(true);
	            			    	  ownerHdr.setColspan(22);
	                			      datatable.addCell(ownerHdr);
	            			    	  
	            			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  ownerName.setHeader(true);
	            			    	  ownerName.setColspan(5);
	                			      datatable.addCell(ownerName);
	                			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNamevalue.setHeader(true);
	                			      ownerNamevalue.setColspan(6);
	                			      datatable.addCell(ownerNamevalue);
	                			      
	                			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerOrgName.setHeader(true);
	                			      ownerOrgName.setColspan(5);
	                			      datatable.addCell(ownerOrgName);
	                			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerOrgNamevalue.setHeader(true);
	                			      ownerOrgNamevalue.setColspan(6);
	                			      datatable.addCell(ownerOrgNamevalue);
	                			      
	                			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerGender.setHeader(true);
	                			      ownerGender.setColspan(5);
	                			      datatable.addCell(ownerGender);
	                			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerGendervalue.setHeader(true);
	                			      ownerGendervalue.setColspan(6);
	                			      datatable.addCell(ownerGendervalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerDob.setHeader(true);
	                			      ownerDob.setColspan(5);
	                			      datatable.addCell(ownerDob);
	                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerDobvalue.setHeader(true);
	                			      ownerDobvalue.setColspan(6);
	                			      datatable.addCell(ownerDobvalue);
	                			      
	                			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerNationality.setHeader(true);
	                			      ownerNationality.setColspan(8);
	                			      datatable.addCell(ownerNationality);
	                			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerNationalityvalue.setHeader(true);
	                			      ownerNationalityvalue.setColspan(14);
	                			      datatable.addCell(ownerNationalityvalue);
	                			      
	                			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerAddress.setHeader(true);
	                			      ownerAddress.setColspan(8);
	                			      datatable.addCell(ownerAddress);
	                			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerAddressvalue.setHeader(true);
	                			      ownerAddressvalue.setColspan(14);
	                			      datatable.addCell(ownerAddressvalue);
	                			      
	                			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerMobile.setHeader(true);
	                			      ownerMobile.setColspan(5);
	                			      datatable.addCell(ownerMobile);
	                			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerMobilevalue.setHeader(true);
	                			      ownerMobilevalue.setColspan(6);
	                			      datatable.addCell(ownerMobilevalue);
	                			      
	                			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerEmail.setHeader(true);
	                			      ownerEmail.setColspan(5);
	                			      datatable.addCell(ownerEmail);
	                			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerEmailvalue.setHeader(true);
	                			      ownerEmailvalue.setColspan(6);
	                			      datatable.addCell(ownerEmailvalue);
	                			      
	                			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProof.setHeader(true);
	                			      ownerProof.setColspan(5);
	                			      datatable.addCell(ownerProof);
	                			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofvalue.setHeader(true);
	                			      ownerProofvalue.setColspan(6);
	                			      datatable.addCell(ownerProofvalue);
	                			      
	                			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      ownerProofId.setHeader(true);
	                			      ownerProofId.setColspan(5);
	                			      datatable.addCell(ownerProofId);
	                			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      ownerProofIdvalue.setHeader(true);
	                			      ownerProofIdvalue.setColspan(6);
	                			      datatable.addCell(ownerProofIdvalue);
	            			    	  
	            			    	  
	            			    	  
	            			    	  
	            			      */}
	                		  
	                		  
	                		  
	                		  
	                		  
	                		  
	                	  }
	                		  
	                		  
	                		  
	                		  
	                	  }
	                	 
	            		                	  
	                	  //}
	            	  
	            	  //WRITING PROPERTY DETAILS ON PDF
	            	  
	            	  
	            	  if(propList!=null && propList.size()>0){
	                	  //partyList=dto.getPartyListPdf();
	                	  RegCompletDTO dto =new RegCompletDTO();
	                	  //below loop for party details
	                	  
	                	  
	    			    //  int sellerCount=0;
	    			    //  int buyerCount=0;
	                	  datatable.addCell(row);
		            	  Cell propertyHdr=new Cell(new Phrase("Property Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
		            	  propertyHdr.setHeader(true);
		            	  propertyHdr.setColspan(22);
					      datatable.addCell(propertyHdr);
					      
	    			      
	                	  for(int k=0;k<propList.size();k++){                          //seller/owner loop
	                		  dto=(RegCompletDTO)propList.get(k);
	            	  
	            	  
				      datatable.addCell(row);
	            	  Cell propertyCount=new Cell(new Phrase("Property "+(k+1) +" : Id-"+dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	            	  propertyCount.setHeader(true);
	            	  propertyCount.setColspan(22);
				      datatable.addCell(propertyCount);
	            	  
	            	  Cell propertyId=new Cell(new Phrase("Property Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  propertyId.setHeader(true);
	            	  propertyId.setColspan(8);
	            	  //propertyId.setBackgroundColor(new Color(255,130,171));
				      datatable.addCell(propertyId);
				      Cell propertyIdvalue=new Cell(new Phrase(dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propertyIdvalue.setHeader(true);
				      propertyIdvalue.setColspan(14);
				      //propertyIdvalue.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(propertyIdvalue);
	            	  
	            	  				      				      
				      Cell propType=new Cell(new Phrase("Property Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propType.setHeader(true);
				      propType.setColspan(8);
	            	  datatable.addCell(propType);
				      Cell propTypevalue=new Cell(new Phrase(dto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propTypevalue.setHeader(true);
				      propTypevalue.setColspan(14);
				      datatable.addCell(propTypevalue);
	            	  
				      Cell propAddress=new Cell(new Phrase("Property Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propAddress.setHeader(true);
				      propAddress.setColspan(8);
	            	  datatable.addCell(propAddress);
				      Cell propAddressvalue=new Cell(new Phrase(dto.getPropAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propAddressvalue.setHeader(true);
				      propAddressvalue.setColspan(14);
				      datatable.addCell(propAddressvalue);
				      
				      Cell propLandmark=new Cell(new Phrase("Property Landmark", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propLandmark.setHeader(true);
				      propLandmark.setColspan(8);
	            	  datatable.addCell(propLandmark);
				      Cell propLandmarkvalue=new Cell(new Phrase(dto.getPropLandmark(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propLandmarkvalue.setHeader(true);
				      propLandmarkvalue.setColspan(14);
				      datatable.addCell(propLandmarkvalue);
				      
				      Cell propMohl=new Cell(new Phrase("Mohalla/Colony name/Society/Road/Gram", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propMohl.setHeader(true);
				      propMohl.setColspan(5);
	            	  datatable.addCell(propMohl);
				      Cell propMohlvalue=new Cell(new Phrase(dto.getMohallaName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propMohlvalue.setHeader(true);
				      propMohlvalue.setColspan(6);
				      datatable.addCell(propMohlvalue);
				      
				      Cell propWarkPatwari=new Cell(new Phrase("Ward / Patwari Halka", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propWarkPatwari.setHeader(true);
				      propWarkPatwari.setColspan(5);
	            	  datatable.addCell(propWarkPatwari);
				      Cell propWarkPatwarivalue=new Cell(new Phrase(dto.getPatwariStatus(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propWarkPatwarivalue.setHeader(true);
				      propWarkPatwarivalue.setColspan(6);
				      datatable.addCell(propWarkPatwarivalue);
				      
				      Cell propWarkPatwariNo=new Cell(new Phrase("ward Number/Patwari Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propWarkPatwariNo.setHeader(true);
				      propWarkPatwariNo.setColspan(5);
	            	  datatable.addCell(propWarkPatwariNo);
				      Cell propWarkPatwariNovalue=new Cell(new Phrase(dto.getWardpatwarName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propWarkPatwariNovalue.setHeader(true);
				      propWarkPatwariNovalue.setColspan(6);
				      datatable.addCell(propWarkPatwariNovalue);
				      
				      Cell propGovBody=new Cell(new Phrase("Governing Municipal Body", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propGovBody.setHeader(true);
				      propGovBody.setColspan(5);
	            	  datatable.addCell(propGovBody);
				      Cell propGovBodyvalue=new Cell(new Phrase(dto.getMunicipalBodyName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propGovBodyvalue.setHeader(true);
				      propGovBodyvalue.setColspan(6);
				      datatable.addCell(propGovBodyvalue);
				      
				      Cell propAreaType=new Cell(new Phrase("Type of Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propAreaType.setHeader(true);
				      propAreaType.setColspan(8);
	            	  datatable.addCell(propAreaType);
				      Cell propAreaTypevalue=new Cell(new Phrase(dto.getAreaTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propAreaTypevalue.setHeader(true);
				      propAreaTypevalue.setColspan(14);
				      datatable.addCell(propAreaTypevalue);
				      
				      Cell propTehsil=new Cell(new Phrase("Tehsil", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propTehsil.setHeader(true);
				      propTehsil.setColspan(5);
	            	  datatable.addCell(propTehsil);
				      Cell propTehsilvalue=new Cell(new Phrase(dto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propTehsilvalue.setHeader(true);
				      propTehsilvalue.setColspan(6);
				      datatable.addCell(propTehsilvalue);
				      
				      Cell propDist=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propDist.setHeader(true);
				      propDist.setColspan(5);
	            	  datatable.addCell(propDist);
				      Cell propDistvalue=new Cell(new Phrase(dto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propDistvalue.setHeader(true);
				      propDistvalue.setColspan(6);
				      datatable.addCell(propDistvalue);
				      
				      Cell propVikas=new Cell(new Phrase("Vikas Khand (development block)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propVikas.setHeader(true);
				      propVikas.setColspan(5);
	            	  datatable.addCell(propVikas);
				      Cell propVikasvalue=new Cell(new Phrase(dto.getVikasId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propVikasvalue.setHeader(true);
				      propVikasvalue.setColspan(6);
				      datatable.addCell(propVikasvalue);
				      
				      Cell propRi=new Cell(new Phrase("R. I. Circle", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propRi.setHeader(true);
				      propRi.setColspan(5);
	            	  datatable.addCell(propRi);
				      Cell propRivalue=new Cell(new Phrase(dto.getRicircle(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propRivalue.setHeader(true);
				      propRivalue.setColspan(6);
				      datatable.addCell(propRivalue);
				      
				      Cell propLayout=new Cell(new Phrase("Layout Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propLayout.setHeader(true);
				      propLayout.setColspan(5);
	            	  datatable.addCell(propLayout);
				      Cell propLayoutvalue=new Cell(new Phrase(dto.getLayoutdet(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propLayoutvalue.setHeader(true);
				      propLayoutvalue.setColspan(6);
				      datatable.addCell(propLayoutvalue);
				      
				      Cell propNazool=new Cell(new Phrase("Nazool/Sheet Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propNazool.setHeader(true);
				      propNazool.setColspan(5);
	            	  datatable.addCell(propNazool);
				      Cell propNazoolvalue=new Cell(new Phrase(dto.getSheetnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNazoolvalue.setHeader(true);
				      propNazoolvalue.setColspan(6);
				      datatable.addCell(propNazoolvalue);
				      
				      Cell propPlotNo=new Cell(new Phrase("Plot Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propPlotNo.setHeader(true);
				      propPlotNo.setColspan(8);
	            	  datatable.addCell(propPlotNo);
				      Cell propPlotNovalue=new Cell(new Phrase(dto.getPlotnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propPlotNovalue.setHeader(true);
				      propPlotNovalue.setColspan(14);
				      datatable.addCell(propPlotNovalue);
				      
				      Cell khasraHdr=new Cell(new Phrase("Khasra Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      khasraHdr.setHeader(true);
				      khasraHdr.setColspan(22);
				      datatable.addCell(khasraHdr);
	            	  
				      //Sr.No. Khasra Number  Area (sq mtr) Lagaan/Land Revenue(INR) RIN Pustika Number  
	            	  
				      Cell srNo=new Cell(new Phrase("Sr.No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      srNo.setHeader(true);
				      srNo.setColspan(2);
	            	  datatable.addCell(srNo);
	            	  
	            	  Cell khasraNo=new Cell(new Phrase("Khasra Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  khasraNo.setHeader(true);
	            	  khasraNo.setColspan(5);
	            	  datatable.addCell(khasraNo);
	            	  
	            	  Cell khasraArea=new Cell(new Phrase("Khasra Area ("+dto.getUnit()+")", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  khasraArea.setHeader(true);
	            	  khasraArea.setColspan(5);
	            	  datatable.addCell(khasraArea);
	            	  
	            	  Cell lagaan=new Cell(new Phrase("Lagaan/Land Revenue(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  lagaan.setHeader(true);
	            	  lagaan.setColspan(5);
	            	  datatable.addCell(lagaan);
	            	  
	            	  Cell rinPustika=new Cell(new Phrase("RIN Pustika Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  rinPustika.setHeader(true);
	            	  rinPustika.setColspan(5);
	            	  datatable.addCell(rinPustika);
	            	  
	            	  if(dto.getKhasraDetlsDisplay()!=null && dto.getKhasraDetlsDisplay().size()>0){
	            		  CommonDTO objDto;
	            	  
	            	      for(int m=0;m<dto.getKhasraDetlsDisplay().size();m++){
	            	    	  objDto=(CommonDTO)dto.getKhasraDetlsDisplay().get(m);
	            	    	  
	            	    	  Cell srNovalue=new Cell(new Phrase(""+(m+1)+"", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	    	  srNovalue.setHeader(true);
	            	    	  srNovalue.setColspan(2);
	                    	  datatable.addCell(srNovalue);
	                    	  
	                    	  Cell khasraNovalue=new Cell(new Phrase(objDto.getKhasraNum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    	  khasraNovalue.setHeader(true);
	                    	  khasraNovalue.setColspan(5);
	                    	  datatable.addCell(khasraNovalue);
	                    	  
	                    	  Cell khasraAreavalue=new Cell(new Phrase(objDto.getKhasraArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    	  khasraAreavalue.setHeader(true);
	                    	  khasraAreavalue.setColspan(5);
	                    	  datatable.addCell(khasraAreavalue);
	                    	  
	                    	  Cell lagaanvalue=new Cell(new Phrase(objDto.getLagaan(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    	  lagaanvalue.setHeader(true);
	                    	  lagaanvalue.setColspan(5);
	                    	  datatable.addCell(lagaanvalue);
	                    	  
	                    	  Cell rinPustikavalue=new Cell(new Phrase(objDto.getRinPustika(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                    	  rinPustikavalue.setHeader(true);
	                    	  rinPustikavalue.setColspan(5);
	                    	  datatable.addCell(rinPustikavalue);
	            	    	  
	            	    	  
	            	    	  
	            	      }
	            	  }
	            	  
	            	  Cell fourBoundryHdr=new Cell(new Phrase("Four Boundary Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  fourBoundryHdr.setHeader(true);
	            	  fourBoundryHdr.setColspan(22);
				      datatable.addCell(fourBoundryHdr);
				      
				      Cell propNorth=new Cell(new Phrase("North", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propNorth.setHeader(true);
				      propNorth.setColspan(5);
	            	  datatable.addCell(propNorth);
				      Cell propNorthvalue=new Cell(new Phrase(dto.getNorth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNorthvalue.setHeader(true);
				      propNorthvalue.setColspan(6);
				      datatable.addCell(propNorthvalue);
				      
				      Cell propSouth=new Cell(new Phrase("South", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propSouth.setHeader(true);
				      propSouth.setColspan(5);
	            	  datatable.addCell(propSouth);
				      Cell propSouthvalue=new Cell(new Phrase(dto.getSouth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propSouthvalue.setHeader(true);
				      propSouthvalue.setColspan(6);
				      datatable.addCell(propSouthvalue);
				      
				      Cell propEast=new Cell(new Phrase("East", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propEast.setHeader(true);
				      propEast.setColspan(5);
	            	  datatable.addCell(propEast);
				      Cell propEastvalue=new Cell(new Phrase(dto.getEast(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propEastvalue.setHeader(true);
				      propEastvalue.setColspan(6);
				      datatable.addCell(propEastvalue);
				      
				      Cell propWest=new Cell(new Phrase("West", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propWest.setHeader(true);
				      propWest.setColspan(5);
	            	  datatable.addCell(propWest);
				      Cell propWestvalue=new Cell(new Phrase(dto.getWest(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propWestvalue.setHeader(true);
				      propWestvalue.setColspan(6);
				      datatable.addCell(propWestvalue);
				      

				      datatable.addCell(row);
				        
				      if(dto.getFloorCount()==0){
				      String area=dto.getTotalSqMeterDisplay();
				      
				      Cell propArea=new Cell(new Phrase("Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propArea.setHeader(true);
				      propArea.setColspan(8);
	            	  datatable.addCell(propArea);
				      Cell propAreavalue=new Cell(new Phrase(area+" "+dto.getUnit(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propAreavalue.setHeader(true);
				      propAreavalue.setColspan(14);
				      datatable.addCell(propAreavalue);
				      }
				      
				      Cell propNoFloor=new Cell(new Phrase("Number of Constructed Floors", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propNoFloor.setHeader(true);
				      propNoFloor.setColspan(8);
	            	  datatable.addCell(propNoFloor);
	            	  if(dto.getFloorCount()==0){
				    	  Cell propNoFloorvalue=new Cell(new Phrase(RegInitConstant.NOT_APPLICABLE, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
					      propNoFloorvalue.setHeader(true);
					      propNoFloorvalue.setColspan(14);
					      datatable.addCell(propNoFloorvalue);
				      }else{
				    	  Cell propNoFloorvalue=new Cell(new Phrase(Integer.toString(dto.getFloorCount()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
					      propNoFloorvalue.setHeader(true);
					      propNoFloorvalue.setColspan(14);
					      datatable.addCell(propNoFloorvalue);
				      }
				      
				      if(dto.getFloorCount()==0){
				    	  
				    	  //subclause non building
				    	  if(dto.getSelectedSubClauseList()!=null && dto.getSelectedSubClauseList().size()>0){
				    		  
				    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				    		  subClauseHdr.setHeader(true);
				    		  subClauseHdr.setColspan(22);
	        			      datatable.addCell(subClauseHdr);
	        			      
	        			     for(int n=0;n<dto.getSelectedSubClauseList().size();n++){
	        			    	 
	        			    	 CommonDTO subClausedto = new CommonDTO();
	        			    	 subClausedto=(CommonDTO)dto.getSelectedSubClauseList().get(n);
	        			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			    	 subClauseValue.setHeader(true);
	        			    	 subClauseValue.setColspan(22);
	           			         datatable.addCell(subClauseValue);
	        			    	 
	        			    	 
	        			    	 
	        			     }
				    		  
				    		  
				    	  }
				    	  
				    	  
				    	  
				    	  
				    	  
				      }else if(dto.getFloorCount()>0){
				    	  //floor details
				    	  
				    	  	if(dto.getMapBuilding()!=null && dto.getMapBuilding().size()>0){
				    	  		
				    	  		Cell floorHdr=new Cell(new Phrase("Floor Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				    	  		floorHdr.setHeader(true);
				    	  		floorHdr.setColspan(22);
	          			      datatable.addCell(floorHdr);
				    	  		Collection mapCollection2=dto.getMapBuilding().values();
	          			      Object[] l2=mapCollection2.toArray();
	                            
				    	  		RegCompletDTO floordto;
				    		   for(int p=0;p<l2.length;p++){
	        			    	 
				    			   floordto = new RegCompletDTO();
				    			   floordto=(RegCompletDTO)l2[p];
	        			    	 
	        			    	 Cell buildingType=new Cell(new Phrase("Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	        			    	 buildingType.setHeader(true);
	        			    	 buildingType.setColspan(5);
	           			      	 datatable.addCell(buildingType);
	        			    	 Cell buildingTypeValue=new Cell(new Phrase(floordto.getUsageBuilding(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			    	 buildingTypeValue.setHeader(true);
	        			    	 buildingTypeValue.setColspan(6);
	           			         datatable.addCell(buildingTypeValue);
	           			         
	           			         Cell roofType=new Cell(new Phrase("Sub-Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	           			         roofType.setHeader(true);
	           			         roofType.setColspan(5);
	           			         datatable.addCell(roofType);
	           			         Cell roofTypeValue=new Cell(new Phrase(floordto.getCeilingType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	           			         roofTypeValue.setHeader(true);
	           			         roofTypeValue.setColspan(6);
	           			         datatable.addCell(roofTypeValue);
	        			      
	           			         Cell floorType=new Cell(new Phrase("Floor Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	           			         floorType.setHeader(true);
	           			         floorType.setColspan(8);
	        			      	 datatable.addCell(floorType);
	        			      	 Cell floorTypeValue=new Cell(new Phrase(floordto.getTypeFloorDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			      	 floorTypeValue.setHeader(true);
	        			      	 floorTypeValue.setColspan(14);
	        			         datatable.addCell(floorTypeValue);
	        			         
	        			         /*Cell consdAmt=new Cell(new Phrase("Consideration Amount (Rs.)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	        			         consdAmt.setHeader(true);
	        			         consdAmt.setColspan(5);
	        			         datatable.addCell(consdAmt);
	        			         Cell consdAmtValue=new Cell(new Phrase(Double.toString(floordto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			         consdAmtValue.setHeader(true);
	        			         consdAmtValue.setColspan(6);
	        			         datatable.addCell(consdAmtValue);  //check
*/	     			      
	        			         Cell floorTotArea=new Cell(new Phrase("Total Area (Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	        			         floorTotArea.setHeader(true);
	        			         floorTotArea.setColspan(5);
	        			         datatable.addCell(floorTotArea);
	        			         Cell floorTotAreaValue=new Cell(new Phrase(floordto.getTotalSqMeterDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			         floorTotAreaValue.setHeader(true);
	        			         floorTotAreaValue.setColspan(6);
	        			         datatable.addCell(floorTotAreaValue);
	   			         
	        			         Cell floorConstArea=new Cell(new Phrase("Constructed Area(Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	        			         floorConstArea.setHeader(true);
	        			         floorConstArea.setColspan(5);
	        			         datatable.addCell(floorConstArea);
	        			         Cell floorConstAreaValue=new Cell(new Phrase(floordto.getConstructedArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	        			         floorConstAreaValue.setHeader(true);
	        			         floorConstAreaValue.setColspan(6);
	        			         datatable.addCell(floorConstAreaValue);
	        			         
	        			         if(floordto.getSelectedSubClauseList()!=null && floordto.getSelectedSubClauseList().size()>0){
	       			    		  
	       			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	       			    		  subClauseHdr.setHeader(true);
	       			    		  subClauseHdr.setColspan(22);
	               			      datatable.addCell(subClauseHdr);
	               			      
	               			     for(int f=0;f<floordto.getSelectedSubClauseList().size();f++){
	               			    	 
	               			    	 CommonDTO subClausedto2 = new CommonDTO();
	               			    	 subClausedto2=(CommonDTO)floordto.getSelectedSubClauseList().get(f);
	               			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto2.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	               			    	 subClauseValue.setHeader(true);
	               			    	 subClauseValue.setColspan(22);
	                  			         datatable.addCell(subClauseValue);
	               			    	 
	               			    	 
	               			    	 
	               			     }
	       			    		  
	       			    		  
	       			    	  }
	        			          
	        			    	 
	        			    	 
	        			    	 
	        			     }
				    		  
				    		  
				    	  }
				    	  
				      }
				      
				      Cell uplAngle1=new Cell(new Phrase("ANGLE 1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      uplAngle1.setHeader(true);
				      uplAngle1.setColspan(8);
				        datatable.addCell(uplAngle1);
				        Cell uplAngle1value=new Cell(new Phrase(dto.getPropImage1DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				        uplAngle1value.setHeader(true);
				        uplAngle1value.setColspan(14);
				        datatable.addCell(uplAngle1value);
				        
				        Cell uplAngle2=new Cell(new Phrase("ANGLE 2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  			      uplAngle2.setHeader(true);
	  			      uplAngle2.setColspan(8);
	  			        datatable.addCell(uplAngle2);
	  			        Cell uplAngle2value=new Cell(new Phrase(dto.getPropImage2DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	  			        uplAngle2value.setHeader(true);
	  			        uplAngle2value.setColspan(14);
	  			        datatable.addCell(uplAngle2value);
	  			        
	  			      Cell uplAngle3=new Cell(new Phrase("ANGLE 3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      uplAngle3.setHeader(true);
				      uplAngle3.setColspan(8);
				        datatable.addCell(uplAngle3);
				        Cell uplAngle3value=new Cell(new Phrase(dto.getPropImage3DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				        uplAngle3value.setHeader(true);
				        uplAngle3value.setColspan(14);
				        datatable.addCell(uplAngle3value);
				        
				        Cell uplMap=new Cell(new Phrase("Property Map as per rules", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				        uplMap.setHeader(true);
				        uplMap.setColspan(8);
	  			        datatable.addCell(uplMap);
	  			        Cell uplMapvalue=new Cell(new Phrase(dto.getPropMapDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	  			        uplMapvalue.setHeader(true);
	  			        uplMapvalue.setColspan(14);
	  			        datatable.addCell(uplMapvalue);
	  			        
	  			      if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
	  				{
	  			    	  
	  			    	Cell uplRin=new Cell(new Phrase("RIN Pustika for Agriculture Land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  			    	uplRin.setHeader(true);
	  			    	uplRin.setColspan(8);
	  			        datatable.addCell(uplRin);
	  			        Cell uplRinvalue=new Cell(new Phrase(dto.getPropRinDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	  			        uplRinvalue.setHeader(true);
	  			        uplRinvalue.setColspan(14);
	  			        datatable.addCell(uplRinvalue);
	  			        
	  			      Cell uplKhasra=new Cell(new Phrase("Computerized Khasra of 1 year certified by revenue official for agricultural land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  			      uplKhasra.setHeader(true);
	  			      uplKhasra.setColspan(8);
				        datatable.addCell(uplKhasra);
				        Cell uplKhasravalue=new Cell(new Phrase(dto.getPropKhasraDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				        uplKhasravalue.setHeader(true);
				        uplKhasravalue.setColspan(14);
				        datatable.addCell(uplKhasravalue);
				        
	  				
	  				}
	              }
			      }
				      
				     if(particularList != null & particularList.size()>0){
				    	 

	                	  //partyList=dto.getPartyListPdf();
	                	 CommonDTO dto =new CommonDTO();
	                	  //below loop for party details
	                	  
	                	  
	    			    //  int sellerCount=0;
	    			    //  int buyerCount=0;
	                	  datatable.addCell(row);
		            	  Cell propertyHdr=new Cell(new Phrase("Particular Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
		            	  propertyHdr.setHeader(true);
		            	  propertyHdr.setColspan(22);
					      datatable.addCell(propertyHdr);
					      
	    			      
	                	  for(int h=0;h<particularList.size();h++){                      
	                		  dto=(CommonDTO)particularList.get(h);
	            	  
	            	  
				      datatable.addCell(row);
	            	  Cell propertyCount=new Cell(new Phrase("Particular "+(h+1), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	            	  propertyCount.setHeader(true);
	            	  propertyCount.setColspan(22);
				      datatable.addCell(propertyCount);
	            	  
	            	  Cell propertyId=new Cell(new Phrase("Particular Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            	  propertyId.setHeader(true);
	            	  propertyId.setColspan(5);
	            	  //propertyId.setBackgroundColor(new Color(255,130,171));
				      datatable.addCell(propertyId);
				      Cell propertyIdvalue=new Cell(new Phrase(dto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propertyIdvalue.setHeader(true);
				      propertyIdvalue.setColspan(6);
				      //propertyIdvalue.setBackgroundColor(new Color(255,255,36));
				      datatable.addCell(propertyIdvalue);
	            	  
	            	  				      				      
				      Cell propType=new Cell(new Phrase("Particular Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      propType.setHeader(true);
				      propType.setColspan(5);
	            	  datatable.addCell(propType);
				      Cell propTypevalue=new Cell(new Phrase(dto.getParticularDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propTypevalue.setHeader(true);
				      propTypevalue.setColspan(6);
				      datatable.addCell(propTypevalue);
	            	  
				      
				     
				      
				      
	              }
			      
				    	 
				    	 
				    	 
				    	 
				     }
	            	  
	            	  
	             
			      
			      
			      
		}
			      else{
			
			Cell noDataFound=new Cell(new Phrase("No data found", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			noDataFound.setHeader(true);
			noDataFound.setColspan(22);
		      //balanceon.setBackgroundColor(new Color(255,255,36));
		      datatable.addCell(noDataFound);
		      
		      datatable.addCell(row);
			
		}
			      
		
		    	  
		    	  
		      }
		      if(deedId==RegInitConstant.DEED_LEASE_NPV || deedId==RegInitConstant.DEED_MORTGAGE_NPV ||
		    		  deedId==RegInitConstant.DEED_POA || deedId==RegInitConstant.DEED_SETTLEMENT_NPV ||
		    		  deedId==RegInitConstant.DEED_WILL_NPV || deedId==RegInitConstant.DEED_TRANSFER_LEASE_NPV ||
		    		  deedId==RegInitConstant.DEED_SURRENDER_LEASE_NPV || deedId==RegInitConstant.DEED_COMPOSITION_NPV
		    		  || deedId==RegInitConstant.DEED_SECURITY_BOND_NPV 
		    		  || deedId==RegInitConstant.DEED_LETTER_OF_LICENCE_NPV ||
		    		  (deedId==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_P) ||
		    		  (deedId==RegInitConstant.DEED_PARTNERSHIP_NPV && 
	  							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
	  							(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
	  									&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  		  					(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
	  		  							&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  		  		  			(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
	  		  		  				    && regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)
		    		  ){
		    	  

		    	  
		    	  getExtraDetls(regForm,languageLocale);
		    	  
		    	  
		    	  if(deedId==RegInitConstant.DEED_LEASE_NPV){
		    	  Cell bankHeader=new Cell(new Phrase("Lease Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		    	  bankHeader.setColspan(22);
			      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		    	  bankHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
			      datatable.addCell(bankHeader);
			      datatable.setAlignment(2);  
		    	  
			      /*Cell bName=new Cell(new Phrase("Rent(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bName.setHeader(true);
			      bName.setColspan(5);
			      datatable.addCell(bName);
			      Cell bNamevalue=new Cell(new Phrase(obj.format(regForm.getRent()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bNamevalue.setHeader(true);
			      bNamevalue.setColspan(6);
			      datatable.addCell(bNamevalue);*/
			      
			      Cell branchName=new Cell(new Phrase("Advance(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      branchName.setHeader(true);
			      branchName.setColspan(8);
			      datatable.addCell(branchName);
			      Cell branchNamevalue=new Cell(new Phrase(obj.format(regForm.getAdvance()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      branchNamevalue.setHeader(true);
			      branchNamevalue.setColspan(14);
			      datatable.addCell(branchNamevalue);
			      
			      Cell bAddress=new Cell(new Phrase("Development charge(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAddress.setHeader(true);
			      bAddress.setColspan(5);
			      datatable.addCell(bAddress);
			      Cell bAddressvalue=new Cell(new Phrase(obj.format(regForm.getDevlpmtCharge()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAddressvalue.setHeader(true);
			      bAddressvalue.setColspan(6);
			      datatable.addCell(bAddressvalue);
			      
			      Cell bAuth=new Cell(new Phrase("Other recurring charge paid(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      bAuth.setHeader(true);
			      bAuth.setColspan(5);
			      datatable.addCell(bAuth);
			      Cell bAuthvalue=new Cell(new Phrase(obj.format(regForm.getOtherRecCharge()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      bAuthvalue.setHeader(true);
			      bAuthvalue.setColspan(6);
			      datatable.addCell(bAuthvalue);
			      
			      Cell loanAmt=new Cell(new Phrase("Premium(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      loanAmt.setHeader(true);
			      loanAmt.setColspan(5);
			      datatable.addCell(loanAmt);
			      Cell loanAmtvalue=new Cell(new Phrase(obj.format(regForm.getPremium()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      loanAmtvalue.setHeader(true);
			      loanAmtvalue.setColspan(6);
			      datatable.addCell(loanAmtvalue);
			      
			      Cell sancAmt=new Cell(new Phrase("Term of lease", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      sancAmt.setHeader(true);
			      sancAmt.setColspan(5);
			      datatable.addCell(sancAmt);
			      Cell sancAmtvalue=new Cell(new Phrase(obj.format(regForm.getTermLease()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      sancAmtvalue.setHeader(true);
			      sancAmtvalue.setColspan(6);
			      datatable.addCell(sancAmtvalue);
		    	  
			      datatable.addCell(row);
		    	  }
		    	  if(deedId==RegInitConstant.DEED_MORTGAGE_NPV){
			    	  Cell trustHeader=new Cell(new Phrase("Mortgage Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("With Possesion", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getWithPos(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Secured Loan Amount(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(Double.toString(regForm.getSecLoanAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		    	  if(deedId==RegInitConstant.DEED_POA){
			    	  Cell trustHeader=new Cell(new Phrase("PoA Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  
			    	  
				      Cell tName=new Cell(new Phrase("PoA with or without consideration", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tName.setHeader(true);
				      tName.setColspan(5);
				      datatable.addCell(tName);
				      Cell tNamevalue=new Cell(new Phrase(regForm.getPoaWithConsid(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tNamevalue.setHeader(true);
				      tNamevalue.setColspan(6);
				      datatable.addCell(tNamevalue);
				      
				      Cell tDate=new Cell(new Phrase("Period of PoA", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
				      tDate.setHeader(true);
				      tDate.setColspan(5);
				      datatable.addCell(tDate);
				      Cell tDatevalue=new Cell(new Phrase(Double.toString(regForm.getPoaPeriod()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      tDatevalue.setHeader(true);
				      tDatevalue.setColspan(6);
				      datatable.addCell(tDatevalue);
				      
				      datatable.addCell(row);
			    	  }
		    	  	  if(deedId==RegInitConstant.DEED_SURRENDER_LEASE_NPV){
		    		  
		    		  Cell trustHeader=new Cell(new Phrase("Arbitrator Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
			    	  trustHeader.setColspan(22);
				      //dutyHeader.setBorder(Rectangle.NO_BORDER);
			    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
				      datatable.addCell(trustHeader);
				      datatable.setAlignment(2);  

			    	  
			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    	  indName.setHeader(true);
			    	  indName.setColspan(8);
    			      datatable.addCell(indName);
    			      Cell indNamevalue=new Cell(new Phrase(regForm.getFnameArb()+" "+regForm.getMnameTrns()+" "+regForm.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      indNamevalue.setHeader(true);
    			      indNamevalue.setColspan(14);
    			      datatable.addCell(indNamevalue);
    			      
    			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      gender.setHeader(true);
    			      gender.setColspan(5);
    			      datatable.addCell(gender);
    			      Cell gendervalue=new Cell(new Phrase(regForm.getGendarNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      gendervalue.setHeader(true);
    			      gendervalue.setColspan(6);
    			      datatable.addCell(gendervalue);
    			      
    			      /*Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      dob.setHeader(true);
    			      dob.setColspan(5);
    			      datatable.addCell(dob);
    			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      dobvalue.setHeader(true);
    			      dobvalue.setColspan(6);
    			      datatable.addCell(dobvalue);*/
    			      
    			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      dob.setHeader(true);
    			      dob.setColspan(5);
    			      datatable.addCell(dob);
    			      Cell dobvalue=new Cell(new Phrase(regForm.getAgeArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      dobvalue.setHeader(true);
    			      dobvalue.setColspan(6);
    			      datatable.addCell(dobvalue);
    			      
    			      Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      fname.setHeader(true);
    			      fname.setColspan(5);
    			      datatable.addCell(fname);
    			      Cell fnamevalue=new Cell(new Phrase(regForm.getFatherNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      fnamevalue.setHeader(true);
    			      fnamevalue.setColspan(6);
    			      datatable.addCell(fnamevalue);
    			      
    			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      gname.setHeader(true);
    			      gname.setColspan(5);
    			      datatable.addCell(gname);
    			      Cell gnamevalue=new Cell(new Phrase(regForm.getMotherNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      gnamevalue.setHeader(true);
    			      gnamevalue.setColspan(6);
    			      datatable.addCell(gnamevalue);
    			      
    			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      sname.setHeader(true);
    			      sname.setColspan(5);
    			      datatable.addCell(sname);
    			      Cell snamevalue=new Cell(new Phrase(regForm.getSpouseNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      snamevalue.setHeader(true);
    			      snamevalue.setColspan(6);
    			      datatable.addCell(snamevalue);
    			      
    			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      email.setHeader(true);
    			      email.setColspan(5);
    			      datatable.addCell(email);
    			      Cell emailvalue=new Cell(new Phrase(regForm.getEmailIDArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      emailvalue.setHeader(true);
    			      emailvalue.setColspan(6);
    			      datatable.addCell(emailvalue);
    			      
    			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      category.setHeader(true);
    			      category.setColspan(5);
    			      datatable.addCell(category);
    			      Cell categoryvalue=new Cell(new Phrase(regForm.getIndCategoryNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      categoryvalue.setHeader(true);
    			      categoryvalue.setColspan(6);
    			      datatable.addCell(categoryvalue);
    			      
    			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      disability.setHeader(true);
    			      disability.setColspan(5);
    			      datatable.addCell(disability);
    			      Cell disabilityvalue=new Cell(new Phrase(regForm.getIndDisabilityNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      disabilityvalue.setHeader(true);
    			      disabilityvalue.setColspan(6);
    			      datatable.addCell(disabilityvalue);
    			      
    			      if(regForm.getIndDisabilityArb().equalsIgnoreCase("Y")){
    			    	  
    			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			    	  disabilityDesc.setHeader(true);
    			    	  disabilityDesc.setColspan(8);
        			      datatable.addCell(disabilityDesc);
        			      Cell disabilityDescvalue=new Cell(new Phrase(regForm.getIndDisabilityDescArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			      disabilityDescvalue.setHeader(true);
        			      disabilityDescvalue.setColspan(14);
        			      datatable.addCell(disabilityDescvalue);
    			    	  
    			      }
    			      
    			      
    			      
    			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      nationality.setHeader(true);
    			      nationality.setColspan(8);
    			      datatable.addCell(nationality);
    			      Cell nationalityvalue=new Cell(new Phrase(regForm.getNationalityArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      nationalityvalue.setHeader(true);
    			      nationalityvalue.setColspan(14);
    			      datatable.addCell(nationalityvalue);
    			      
    			      
    			      
    			      
    			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      phNo.setHeader(true);
    			      phNo.setColspan(5);
    			      datatable.addCell(phNo);
    			      Cell phNovalue=new Cell(new Phrase(regForm.getIndphnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      phNovalue.setHeader(true);
    			      phNovalue.setColspan(6);
    			      datatable.addCell(phNovalue);
    			      
    			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      mobNo.setHeader(true);
    			      mobNo.setColspan(5);
    			      datatable.addCell(mobNo);
    			      Cell mobNovalue=new Cell(new Phrase(regForm.getIndmobnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      mobNovalue.setHeader(true);
    			      mobNovalue.setColspan(6);
    			      datatable.addCell(mobNovalue);
    			      
    			      
    			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      address.setHeader(true);
    			      address.setColspan(8);
    			      datatable.addCell(address);
    			      Cell addressvalue=new Cell(new Phrase(regForm.getIndaddressArb()+", "+regForm.getInddistrictNameArb()+", "+regForm.getIndstatenameNameArb()+", "+regForm.getIndcountryNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      addressvalue.setHeader(true);
    			      addressvalue.setColspan(14);
    			      datatable.addCell(addressvalue);
    			      
    			      
    			      
    			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      proof.setHeader(true);
    			      proof.setColspan(5);
    			      datatable.addCell(proof);
    			      Cell proofvalue=new Cell(new Phrase(regForm.getListIDNameArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      proofvalue.setHeader(true);
    			      proofvalue.setColspan(6);
    			      datatable.addCell(proofvalue);
    			      
    			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    			      proofId.setHeader(true);
    			      proofId.setColspan(5);
    			      datatable.addCell(proofId);
    			      Cell proofIdvalue=new Cell(new Phrase(regForm.getIdnoArb(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    			      proofIdvalue.setHeader(true);
    			      proofIdvalue.setColspan(6);
    			      datatable.addCell(proofIdvalue);
    			      
    			 
			    	  
			      
		    		  
		    	  }
		    	  	if(deedId==RegInitConstant.DEED_TRUST){
				    	  Cell trustHeader=new Cell(new Phrase("Trust Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
				    	  trustHeader.setColspan(22);
					      //dutyHeader.setBorder(Rectangle.NO_BORDER);
				    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					      datatable.addCell(trustHeader);
					      datatable.setAlignment(2);  
				    	  
					      Cell tName=new Cell(new Phrase("Trust Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
					      tName.setHeader(true);
					      tName.setColspan(5);
					      datatable.addCell(tName);
					      Cell tNamevalue=new Cell(new Phrase(regForm.getTrustName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
					      tNamevalue.setHeader(true);
					      tNamevalue.setColspan(6);
					      datatable.addCell(tNamevalue);
					      
					      Cell tDate=new Cell(new Phrase("Trust Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
					      tDate.setHeader(true);
					      tDate.setColspan(5);
					      datatable.addCell(tDate);
					      Cell tDatevalue=new Cell(new Phrase(regForm.getTrustDate(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
					      tDatevalue.setHeader(true);
					      tDatevalue.setColspan(6);
					      datatable.addCell(tDatevalue);
					      
					      datatable.addCell(row);
				    	  }
		    	  	
		    	  	if((deedId==RegInitConstant.DEED_PARTNERSHIP_NPV && 
 							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))){
				    	  Cell trustHeader=new Cell(new Phrase("Extra Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
				    	  trustHeader.setColspan(22);
					      //dutyHeader.setBorder(Rectangle.NO_BORDER);
				    	  trustHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
					      datatable.addCell(trustHeader);
					      datatable.setAlignment(2);  
				    	  
					      Cell tName=new Cell(new Phrase("Contriutory  property coming in Partnership deed", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
					      tName.setHeader(true);
					      tName.setColspan(8);
					      datatable.addCell(tName);
					      Cell tNamevalue=new Cell(new Phrase(regForm.getContriProp(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
					      tNamevalue.setHeader(true);
					      tNamevalue.setColspan(14);
					      datatable.addCell(tNamevalue);
					      
					      
					      
					      datatable.addCell(row);
				    	  }
		      
		      Cell propPartyHeader=new Cell(new Phrase("Property Wise Party Details", FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD)));	    			      
		      propPartyHeader.setColspan(22);
		      //dutyHeader.setBorder(Rectangle.NO_BORDER);
		      propPartyHeader.setBackgroundColor(new Color(0xC0, 0xC0, 0xC0));
		      datatable.addCell(propPartyHeader);
		      datatable.setAlignment(2);  
		      
		      
		      HashMap pdfMap=getPropertyPartyDetailsForPDF(regForm.getHidnRegTxnId(),2,languageLocale);
		
		      if(pdfMap!=null){
		      Collection mapCollection=pdfMap.values();
		      Object[] l1=mapCollection.toArray();
              RegCompletDTO dto=new RegCompletDTO();
             ArrayList partyList;
              
           //below loop for property details
              for(int k=0;k<l1.length;k++)
              {
              	
            	  dto=(RegCompletDTO)l1[k];
            	  
            	  datatable.addCell(row);
            	  Cell propertyCount=new Cell(new Phrase("Property "+(k+1) +" : Id-"+dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            	  propertyCount.setHeader(true);
            	  propertyCount.setColspan(22);
			      datatable.addCell(propertyCount);
			      
            	  
            	  if(dto.getPartyListPdf()!=null && dto.getPartyListPdf().size()>0){
                	  partyList=dto.getPartyListPdf();
                	  RegCommonDTO mapDto =new RegCommonDTO();
                	  //below loop for party details
                	  
                	  
    			      int sellerCount=0;
    			      int buyerCount=0;
    			      
                	  for(int j=0;j<partyList.size();j++){                          //seller/owner loop
                		  mapDto=(RegCommonDTO)partyList.get(j);
                		  //WRITING PARTY DETAILS ON PDF
                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                		  
                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
                   		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
                		  
                		  if(roleId==RegInitConstant.ROLE_LESSER || roleId==RegInitConstant.ROLE_LESSER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_MORTGAGER_SELF || roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_OWNER_SELF || roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_SETTLER_SELF || roleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TESTATOR_SELF || roleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TRANSFERER_SELF || roleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_SURRENDERER_SELF || roleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TRUSTEE || roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_EXECUTANT_SELF || roleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER)
                		  {
                			  datatable.addCell(row);
                			  sellerCount++;
                			  
                			  if(roleId==RegInitConstant.ROLE_LESSER || roleId==RegInitConstant.ROLE_LESSER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Lesser "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_MORTGAGER_SELF || roleId==RegInitConstant.ROLE_MORTGAGER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Mortgager "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_OWNER_SELF || roleId==RegInitConstant.ROLE_OWNER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Owner "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_SETTLER_SELF || roleId==RegInitConstant.ROLE_SETTLER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Settler "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_TESTATOR_SELF || roleId==RegInitConstant.ROLE_TESTATOR_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Testator "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_TRANSFERER_SELF || roleId==RegInitConstant.ROLE_TRANSFERER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Transferer "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_SURRENDERER_SELF || roleId==RegInitConstant.ROLE_SURRENDERER_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Surrenderer "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_TRUSTEE || roleId==RegInitConstant.ROLE_TRUSTEE_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Trustee "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  if(roleId==RegInitConstant.ROLE_EXECUTANT_SELF || roleId==RegInitConstant.ROLE_EXECUTANT_POA_HOLDER){
                			      Cell lesserHdr=new Cell(new Phrase("Executant "+(sellerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      lesserHdr.setHeader(true);
                			      lesserHdr.setColspan(22);
                			      datatable.addCell(lesserHdr);
                			      }
                			  
                			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			  partyId.setHeader(true);
                			  partyId.setColspan(8);
            			      datatable.addCell(partyId);
            			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      partyIdvalue.setHeader(true);
            			      partyIdvalue.setColspan(14);
            			      datatable.addCell(partyIdvalue);
                			 
                			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			  appType.setHeader(true);
                			  appType.setColspan(8);
            			      datatable.addCell(appType);
            			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
            			      appTypevalue.setHeader(true);
            			      appTypevalue.setColspan(14);
            			      datatable.addCell(appTypevalue);
            			      
            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
            			    	  
            			    	  setCommonOrgOnPdf(mapDto,datatable);
                			      
                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      share.setHeader(true);
                			      share.setColspan(8);
                			      datatable.addCell(share);
                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      sharevalue.setHeader(true);
                			      sharevalue.setColspan(14);
                			      datatable.addCell(sharevalue);
            			    	  
            			      }
            			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
            			    	  
            			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			    	  indName.setHeader(true);
            			    	  indName.setColspan(8);
                			      datatable.addCell(indName);
                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      indNamevalue.setHeader(true);
                			      indNamevalue.setColspan(14);
                			      datatable.addCell(indNamevalue);
                			      
                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      relation.setHeader(true);
                			      relation.setColspan(5);
                			      datatable.addCell(relation);
                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      relationvalue.setHeader(true);
                			      relationvalue.setColspan(6);
                			      datatable.addCell(relationvalue);
                			   
                			      
                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      fathername.setHeader(true);
                			      fathername.setColspan(5);
                			      datatable.addCell(fathername);
                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      fathernamevalue.setHeader(true);
                			      fathernamevalue.setColspan(6);
                			      datatable.addCell(fathernamevalue);
                			      
                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      gender.setHeader(true);
                			      gender.setColspan(5);
                			      datatable.addCell(gender);
                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      gendervalue.setHeader(true);
                			      gendervalue.setColspan(6);
                			      datatable.addCell(gendervalue);
                			      
                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      fname.setHeader(true);
                			      fname.setColspan(5);
                			      datatable.addCell(fname);
                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      fnamevalue.setHeader(true);
                			      fnamevalue.setColspan(6);
                			      datatable.addCell(fnamevalue);*/
                			      
                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      dob.setHeader(true);
                			      dob.setColspan(5);
                			      datatable.addCell(dob);
                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      dobvalue.setHeader(true);
                			      dobvalue.setColspan(6);
                			      datatable.addCell(dobvalue);*/
                			      
                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      dob.setHeader(true);
                			      dob.setColspan(5);
                			      datatable.addCell(dob);
                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      dobvalue.setHeader(true);
                			      dobvalue.setColspan(6);
                			      datatable.addCell(dobvalue);
                			   
                			      
                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      gname.setHeader(true);
                			      gname.setColspan(5);
                			      datatable.addCell(gname);
                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      gnamevalue.setHeader(true);
                			      gnamevalue.setColspan(6);
                			      datatable.addCell(gnamevalue);
                			      
                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      sname.setHeader(true);
                			      sname.setColspan(5);
                			      datatable.addCell(sname);
                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      snamevalue.setHeader(true);
                			      snamevalue.setColspan(6);
                			      datatable.addCell(snamevalue);
                			      
                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      email.setHeader(true);
                			      email.setColspan(5);
                			      datatable.addCell(email);
                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      emailvalue.setHeader(true);
                			      emailvalue.setColspan(6);
                			      datatable.addCell(emailvalue);
                			      
                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      category.setHeader(true);
                			      category.setColspan(5);
                			      datatable.addCell(category);
                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      categoryvalue.setHeader(true);
                			      categoryvalue.setColspan(6);
                			      datatable.addCell(categoryvalue);
                			      
                			      if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
                			    	  
                			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  scheduleArea.setHeader(true);
                			    	  scheduleArea.setColspan(8);
                    			      datatable.addCell(scheduleArea);
                    			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      scheduleAreavalue.setHeader(true);
                    			      scheduleAreavalue.setColspan(14);
                    			      datatable.addCell(scheduleAreavalue);
                    			      
                    			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                    			    	  
                    			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  permissionNo.setHeader(true);
                    			    	  permissionNo.setColspan(5);
                        			      datatable.addCell(permissionNo);
                        			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      permissionNovalue.setHeader(true);
                        			      permissionNovalue.setColspan(6);
                        			      datatable.addCell(permissionNovalue);
                        			      
                        			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			      certificate.setHeader(true);
                        			      certificate.setColspan(5);
                        			      datatable.addCell(certificate);
                        			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      certificatevalue.setHeader(true);
                        			      certificatevalue.setColspan(6);
                        			      datatable.addCell(certificatevalue);
                    			    	  
                    			      }
                			    	  
                			      }
                			      
                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      nationality.setHeader(true);
                			      nationality.setColspan(5);
                			      datatable.addCell(nationality);
                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      nationalityvalue.setHeader(true);
                			      nationalityvalue.setColspan(6);
                			      datatable.addCell(nationalityvalue);
                			      
                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      disability.setHeader(true);
                			      disability.setColspan(5);
                			      datatable.addCell(disability);
                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      disabilityvalue.setHeader(true);
                			      disabilityvalue.setColspan(6);
                			      datatable.addCell(disabilityvalue);
                			      
                			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
                			    	  
                			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  disabilityDesc.setHeader(true);
                			    	  disabilityDesc.setColspan(8);
                    			      datatable.addCell(disabilityDesc);
                    			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      disabilityDescvalue.setHeader(true);
                    			      disabilityDescvalue.setColspan(14);
                    			      datatable.addCell(disabilityDescvalue);
                			    	  
                			      }
                			      
                			      
                			      
                			      
                			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      nationality.setHeader(true);
                			      nationality.setColspan(8);
                			      datatable.addCell(nationality);
                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      nationalityvalue.setHeader(true);
                			      nationalityvalue.setColspan(14);
                			      datatable.addCell(nationalityvalue);*/
                			      
                			      
                			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      minority.setHeader(true);
                			      minority.setColspan(5);
                			      datatable.addCell(minority);
                			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      minorityvalue.setHeader(true);
                			      minorityvalue.setColspan(6);
                			      datatable.addCell(minorityvalue);
                			      
                			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      poverty.setHeader(true);
                			      poverty.setColspan(5);
                			      datatable.addCell(poverty);
                			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      povertyvalue.setHeader(true);
                			      povertyvalue.setColspan(6);
                			      datatable.addCell(povertyvalue);
                			      
                			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      phNo.setHeader(true);
                			      phNo.setColspan(5);
                			      datatable.addCell(phNo);
                			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      phNovalue.setHeader(true);
                			      phNovalue.setColspan(6);
                			      datatable.addCell(phNovalue);
                			      
                			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      mobNo.setHeader(true);
                			      mobNo.setColspan(5);
                			      datatable.addCell(mobNo);
                			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      mobNovalue.setHeader(true);
                			      mobNovalue.setColspan(6);
                			      datatable.addCell(mobNovalue);
                			      
                			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      address.setHeader(true);
                			      address.setColspan(8);
                			      datatable.addCell(address);
                			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      addressvalue.setHeader(true);
                			      addressvalue.setColspan(14);
                			      datatable.addCell(addressvalue);
                			      
                			      Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      postal.setHeader(true);
                			      postal.setColspan(5);
                			      datatable.addCell(postal);
                			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      postalvalue.setHeader(true);
                			      postalvalue.setColspan(6);
                			      datatable.addCell(postalvalue);
                			      
                			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      share.setHeader(true);
                			      share.setColspan(5);
                			      datatable.addCell(share);
                			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      sharevalue.setHeader(true);
                			      sharevalue.setColspan(6);
                			      datatable.addCell(sharevalue);
                			      
                			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      occupation.setHeader(true);
                			      occupation.setColspan(8);
                			      datatable.addCell(occupation);
                			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      occupationvalue.setHeader(true);
                			      occupationvalue.setColspan(14);
                			      datatable.addCell(occupationvalue);
                			      
                			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      proof.setHeader(true);
                			      proof.setColspan(5);
                			      datatable.addCell(proof);
                			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      proofvalue.setHeader(true);
                			      proofvalue.setColspan(6);
                			      datatable.addCell(proofvalue);
                			      
                			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      proofId.setHeader(true);
                			      proofId.setColspan(5);
                			      datatable.addCell(proofId);
                			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      proofIdvalue.setHeader(true);
                			      proofIdvalue.setColspan(6);
                			      datatable.addCell(proofIdvalue);
                			      
                			      
  		                   		
  	                    		
  		                   		
  	                    		
                			      
                			      
            			    	  
            			      }
            			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		                   		upl2.setHeader(true);
		                   		upl2.setColspan(8);
		                   		datatable.addCell(upl2);
		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		                   		upl2value.setHeader(true);
		                   		upl2value.setColspan(14);
		                   		datatable.addCell(upl2value);
          			      

  		                   		if(regForm.getDeedTypeFlag()==0){
  		                   			
  		                   		setComplianceListOnPdf(mapDto,datatable);
  		                   			
  		                   		}
	                   
                			  //OWNER DETAILS IN CASE OF POA BELOW
                		
            			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
            			      {
            			    	  setOwnerOnPdf(mapDto,datatable);
            			    	  
            			    	  /*
            			    	  
            			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            			    	  ownerHdr.setHeader(true);
            			    	  ownerHdr.setColspan(22);
                			      datatable.addCell(ownerHdr);
            			    	  
            			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            			    	  ownerName.setHeader(true);
            			    	  ownerName.setColspan(5);
                			      datatable.addCell(ownerName);
                			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerNamevalue.setHeader(true);
                			      ownerNamevalue.setColspan(6);
                			      datatable.addCell(ownerNamevalue);
                			      
                			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerOrgName.setHeader(true);
                			      ownerOrgName.setColspan(5);
                			      datatable.addCell(ownerOrgName);
                			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerOrgNamevalue.setHeader(true);
                			      ownerOrgNamevalue.setColspan(6);
                			      datatable.addCell(ownerOrgNamevalue);
                			      
                			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerGender.setHeader(true);
                			      ownerGender.setColspan(5);
                			      datatable.addCell(ownerGender);
                			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerGendervalue.setHeader(true);
                			      ownerGendervalue.setColspan(6);
                			      datatable.addCell(ownerGendervalue);
                			      
                			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerDob.setHeader(true);
                			      ownerDob.setColspan(5);
                			      datatable.addCell(ownerDob);
                			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerDobvalue.setHeader(true);
                			      ownerDobvalue.setColspan(6);
                			      datatable.addCell(ownerDobvalue);
                			      
                			      Cell ownerAge=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerAge.setHeader(true);
                			      ownerAge.setColspan(5);
                			      datatable.addCell(ownerAge);
                			      Cell ownerAgevalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerAgevalue.setHeader(true);
                			      ownerAgevalue.setColspan(6);
                			      datatable.addCell(ownerAgevalue);
                			      
                			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerNationality.setHeader(true);
                			      ownerNationality.setColspan(8);
                			      datatable.addCell(ownerNationality);
                			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerNationalityvalue.setHeader(true);
                			      ownerNationalityvalue.setColspan(14);
                			      datatable.addCell(ownerNationalityvalue);
                			      
                			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerAddress.setHeader(true);
                			      ownerAddress.setColspan(8);
                			      datatable.addCell(ownerAddress);
                			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerAddressvalue.setHeader(true);
                			      ownerAddressvalue.setColspan(14);
                			      datatable.addCell(ownerAddressvalue);
                			      
                			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerMobile.setHeader(true);
                			      ownerMobile.setColspan(5);
                			      datatable.addCell(ownerMobile);
                			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerMobilevalue.setHeader(true);
                			      ownerMobilevalue.setColspan(6);
                			      datatable.addCell(ownerMobilevalue);
                			      
                			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerEmail.setHeader(true);
                			      ownerEmail.setColspan(5);
                			      datatable.addCell(ownerEmail);
                			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerEmailvalue.setHeader(true);
                			      ownerEmailvalue.setColspan(6);
                			      datatable.addCell(ownerEmailvalue);
                			      
                			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerProof.setHeader(true);
                			      ownerProof.setColspan(5);
                			      datatable.addCell(ownerProof);
                			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerProofvalue.setHeader(true);
                			      ownerProofvalue.setColspan(6);
                			      datatable.addCell(ownerProofvalue);
                			      
                			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			      ownerProofId.setHeader(true);
                			      ownerProofId.setColspan(5);
                			      datatable.addCell(ownerProofId);
                			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      ownerProofIdvalue.setHeader(true);
                			      ownerProofIdvalue.setColspan(6);
                			      datatable.addCell(ownerProofIdvalue);
            			    	  
            			    	  
            			    	  
            			    	  
            			      */}
                		  
                		  
                		  
                		  
                		  
                		  
                	  }
                		  
                		  
                		  
                		  
                	  }
                	 
            			 
            			 
                	  for(int j=0;j<partyList.size();j++){              //Buyer loop
                		  mapDto=(RegCommonDTO)partyList.get(j);
                		  //WRITING PARTY DETAILS ON PDF
                		  int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
                		  
                		  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
                		  
                		  if(roleId==RegInitConstant.ROLE_LESSEE || roleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_POA_ACCEPTER || roleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_CLAIMANT_SELF || roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER ||
                				  roleId==RegInitConstant.ROLE_TRANSFEREE_SELF || roleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER)
                		  {
                			  
                			  datatable.addCell(row);
                			  buyerCount++;
                			
                    			  if(roleId==RegInitConstant.ROLE_LESSEE || roleId==RegInitConstant.ROLE_LESSEE_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Lessee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			   }
                    			  if(roleId==RegInitConstant.ROLE_MORTGAGEE_SELF || roleId==RegInitConstant.ROLE_MORTGAGEE_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Mortgagee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			   }
                    			  if(roleId==RegInitConstant.ROLE_POA_ACCEPTER || roleId==RegInitConstant.ROLE_ACCEPTER_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Accepter "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			   }
                    			  if(roleId==RegInitConstant.ROLE_CLAIMANT_SELF || roleId==RegInitConstant.ROLE_CLAIMANT_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Claimant "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			   }
                    			  if(roleId==RegInitConstant.ROLE_TRANSFEREE_SELF || roleId==RegInitConstant.ROLE_TRANSFEREE_POA_HOLDER){
                    			      Cell lesseeHdr=new Cell(new Phrase("Mortgagee "+(buyerCount) +" Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      lesseeHdr.setHeader(true);
                    			      lesseeHdr.setColspan(22);
                    			      datatable.addCell(lesseeHdr);
                    			   }
                    			  
                    			  
                    			  Cell partyId=new Cell(new Phrase("Party Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  partyId.setHeader(true);
                    			  partyId.setColspan(8);
                			      datatable.addCell(partyId);
                			      Cell partyIdvalue=new Cell(new Phrase(mapDto.getPartyTxnId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      partyIdvalue.setHeader(true);
                			      partyIdvalue.setColspan(14);
                			      datatable.addCell(partyIdvalue);
                			  
                    			  Cell appType=new Cell(new Phrase("Transacting Party Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			  appType.setHeader(true);
                    			  appType.setColspan(8);
                			      datatable.addCell(appType);
                			      Cell appTypevalue=new Cell(new Phrase(mapDto.getListAdoptedPartyNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                			      appTypevalue.setHeader(true);
                			      appTypevalue.setColspan(14);
                			      datatable.addCell(appTypevalue);
                			      
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
                			    	  
                			    	  setCommonOrgOnPdf(mapDto,datatable);
                    			      
                    			     /* 
                    			      if(deedId==RegInitConstant.DEED_GIFT){
                    			    	  Cell rel=new Cell(new Phrase("Relation with owner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }*/
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(8);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(14);
                    			      datatable.addCell(sharevalue);
                			    	  
                			      }
                			      if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
                			    	  
                			    	  Cell indName=new Cell(new Phrase("Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	            			    	  indName.setHeader(true);
	            			    	  indName.setColspan(8);
	                			      datatable.addCell(indName);
	                			      Cell indNamevalue=new Cell(new Phrase(mapDto.getFnameTrns()+" "+mapDto.getMnameTrns()+" "+mapDto.getLnameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      indNamevalue.setHeader(true);
	                			      indNamevalue.setColspan(14);
	                			      datatable.addCell(indNamevalue);
	                			      
	                			      Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      relation.setHeader(true);
	                			      relation.setColspan(5);
	                			      datatable.addCell(relation);
	                			      Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      relationvalue.setHeader(true);
	                			      relationvalue.setColspan(6);
	                			      datatable.addCell(relationvalue);
	                			   
	                			      
	                			      Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fathername.setHeader(true);
	                			      fathername.setColspan(5);
	                			      datatable.addCell(fathername);
	                			      Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fathernamevalue.setHeader(true);
	                			      fathernamevalue.setColspan(6);
	                			      datatable.addCell(fathernamevalue);
	                			      
	                			      Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gender.setHeader(true);
	                			      gender.setColspan(5);
	                			      datatable.addCell(gender);
	                			      Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gendervalue.setHeader(true);
	                			      gendervalue.setColspan(6);
	                			      datatable.addCell(gendervalue);
	                			      
	                			      /*Cell fname=new Cell(new Phrase("Father's/Gaurdians's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      fname.setHeader(true);
	                			      fname.setColspan(5);
	                			      datatable.addCell(fname);
	                			      Cell fnamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      fnamevalue.setHeader(true);
	                			      fnamevalue.setColspan(6);
	                			      datatable.addCell(fnamevalue);*/
	                			      
	                			    /*  Cell dob=new Cell(new Phrase("Date of birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getUserDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);*/
	                			      
	                			      Cell dob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      dob.setHeader(true);
	                			      dob.setColspan(5);
	                			      datatable.addCell(dob);
	                			      Cell dobvalue=new Cell(new Phrase(mapDto.getAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      dobvalue.setHeader(true);
	                			      dobvalue.setColspan(6);
	                			      datatable.addCell(dobvalue);
	                			   
	                			      
	                			      Cell gname=new Cell(new Phrase("Mother's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      gname.setHeader(true);
	                			      gname.setColspan(5);
	                			      datatable.addCell(gname);
	                			      Cell gnamevalue=new Cell(new Phrase(mapDto.getMotherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      gnamevalue.setHeader(true);
	                			      gnamevalue.setColspan(6);
	                			      datatable.addCell(gnamevalue);
	                			      
	                			      Cell sname=new Cell(new Phrase("Spouse Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      sname.setHeader(true);
	                			      sname.setColspan(5);
	                			      datatable.addCell(sname);
	                			      Cell snamevalue=new Cell(new Phrase(mapDto.getSpouseNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      snamevalue.setHeader(true);
	                			      snamevalue.setColspan(6);
	                			      datatable.addCell(snamevalue);
	                			      
	                			      Cell email=new Cell(new Phrase("E-Mail id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      email.setHeader(true);
	                			      email.setColspan(5);
	                			      datatable.addCell(email);
	                			      Cell emailvalue=new Cell(new Phrase(mapDto.getEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      emailvalue.setHeader(true);
	                			      emailvalue.setColspan(6);
	                			      datatable.addCell(emailvalue);
	                			      
	                			      Cell category=new Cell(new Phrase("Category", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      category.setHeader(true);
	                			      category.setColspan(5);
	                			      datatable.addCell(category);
	                			      Cell categoryvalue=new Cell(new Phrase(mapDto.getSelectedCategoryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      categoryvalue.setHeader(true);
	                			      categoryvalue.setColspan(6);
	                			      datatable.addCell(categoryvalue);
	                			      
	                			      	if(mapDto.getIndCategoryTrns().equalsIgnoreCase("1")){
                    			    	  
                    			    	  Cell scheduleArea=new Cell(new Phrase("Scheduled Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  scheduleArea.setHeader(true);
                    			    	  scheduleArea.setColspan(8);
                        			      datatable.addCell(scheduleArea);
                        			      Cell scheduleAreavalue=new Cell(new Phrase(mapDto.getIndScheduleAreaTrnsDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      scheduleAreavalue.setHeader(true);
                        			      scheduleAreavalue.setColspan(14);
                        			      datatable.addCell(scheduleAreavalue);
                        			      
                        			      if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
                        			    	  
                        			    	  Cell permissionNo=new Cell(new Phrase("Collector's permission no.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                        			    	  permissionNo.setHeader(true);
                        			    	  permissionNo.setColspan(5);
                            			      datatable.addCell(permissionNo);
                            			      Cell permissionNovalue=new Cell(new Phrase(mapDto.getPermissionNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      permissionNovalue.setHeader(true);
                            			      permissionNovalue.setColspan(6);
                            			      datatable.addCell(permissionNovalue);
                            			      
                            			      Cell certificate=new Cell(new Phrase("Collector's certificate present?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                            			      certificate.setHeader(true);
                            			      certificate.setColspan(5);
                            			      datatable.addCell(certificate);
                            			      Cell certificatevalue=new Cell(new Phrase(mapDto.getDocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                            			      certificatevalue.setHeader(true);
                            			      certificatevalue.setColspan(6);
                            			      datatable.addCell(certificatevalue);
                        			    	  
                        			      }
                    			    	  
                    			      }
	                			      
	                			      Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      nationality.setHeader(true);
	                			      nationality.setColspan(5);
	                			      datatable.addCell(nationality);
	                			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      nationalityvalue.setHeader(true);
	                			      nationalityvalue.setColspan(6);
	                			      datatable.addCell(nationalityvalue);
	                			      
	                			      Cell disability=new Cell(new Phrase("Disability", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	                			      disability.setHeader(true);
	                			      disability.setColspan(5);
	                			      datatable.addCell(disability);
	                			      Cell disabilityvalue=new Cell(new Phrase(mapDto.getIndDisabilityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	                			      disabilityvalue.setHeader(true);
	                			      disabilityvalue.setColspan(6);
	                			      datatable.addCell(disabilityvalue);
                    			      
                    			      if(mapDto.getIndDisabilityTrns().equalsIgnoreCase("Yes")){
                    			    	  
                    			    	  Cell disabilityDesc=new Cell(new Phrase("Disability Description", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  disabilityDesc.setHeader(true);
                    			    	  disabilityDesc.setColspan(8);
                        			      datatable.addCell(disabilityDesc);
                        			      Cell disabilityDescvalue=new Cell(new Phrase(mapDto.getIndDisabilityDescTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      disabilityDescvalue.setHeader(true);
                        			      disabilityDescvalue.setColspan(14);
                        			      datatable.addCell(disabilityDescvalue);
                    			    	  
                    			      }
                    			      
                    			     
                    			      
                    			      /*Cell nationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      nationality.setHeader(true);
                    			      nationality.setColspan(8);
                    			      datatable.addCell(nationality);
                    			      Cell nationalityvalue=new Cell(new Phrase(mapDto.getNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      nationalityvalue.setHeader(true);
                    			      nationalityvalue.setColspan(14);
                    			      datatable.addCell(nationalityvalue);*/
                    			      
                    			      
                    			      Cell minority=new Cell(new Phrase("Member of minority?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      minority.setHeader(true);
                    			      minority.setColspan(5);
                    			      datatable.addCell(minority);
                    			      Cell minorityvalue=new Cell(new Phrase(mapDto.getIndMinorityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      minorityvalue.setHeader(true);
                    			      minorityvalue.setColspan(6);
                    			      datatable.addCell(minorityvalue);
                    			      
                    			      Cell poverty=new Cell(new Phrase("Below poverty line?", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      poverty.setHeader(true);
                    			      poverty.setColspan(5);
                    			      datatable.addCell(poverty);
                    			      Cell povertyvalue=new Cell(new Phrase(mapDto.getIndPovertyLineTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      povertyvalue.setHeader(true);
                    			      povertyvalue.setColspan(6);
                    			      datatable.addCell(povertyvalue);
                    			      
                    			      Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      phNo.setHeader(true);
                    			      phNo.setColspan(5);
                    			      datatable.addCell(phNo);
                    			      Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      phNovalue.setHeader(true);
                    			      phNovalue.setColspan(6);
                    			      datatable.addCell(phNovalue);
                    			      
                    			      Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      mobNo.setHeader(true);
                    			      mobNo.setColspan(5);
                    			      datatable.addCell(mobNo);
                    			      Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      mobNovalue.setHeader(true);
                    			      mobNovalue.setColspan(6);
                    			      datatable.addCell(mobNovalue);
                    			      
                    			      
                    			      Cell address=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      address.setHeader(true);
                    			      address.setColspan(8);
                    			      datatable.addCell(address);
                    			      Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      addressvalue.setHeader(true);
                    			      addressvalue.setColspan(14);
                    			      datatable.addCell(addressvalue);
                    			      
                    			      Cell postal=new Cell(new Phrase("Postal Code", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      postal.setHeader(true);
                    			      postal.setColspan(5);
                    			      datatable.addCell(postal);
                    			      Cell postalvalue=new Cell(new Phrase(mapDto.getPostalCodeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      postalvalue.setHeader(true);
                    			      postalvalue.setColspan(6);
                    			      datatable.addCell(postalvalue);
                    			      
                    			    /*  if(deedId==RegInitConstant.DEED_GIFT){
                    			    	  Cell rel=new Cell(new Phrase("Relation with owner", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			    	  rel.setHeader(true);
                    			    	  rel.setColspan(8);
                        			      datatable.addCell(rel);
                        			      Cell relvalue=new Cell(new Phrase(mapDto.getRelationWithOwnerTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                        			      relvalue.setHeader(true);
                        			      relvalue.setColspan(14);
                        			      datatable.addCell(relvalue); 
                    			    	  
                    			      }*/
                    			      
                    			      Cell share=new Cell(new Phrase("Share of property", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      share.setHeader(true);
                    			      share.setColspan(5);
                    			      datatable.addCell(share);
                    			      Cell sharevalue=new Cell(new Phrase(mapDto.getShareOfPropTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      sharevalue.setHeader(true);
                    			      sharevalue.setColspan(6);
                    			      datatable.addCell(sharevalue);
                    			      
                    			      Cell occupation=new Cell(new Phrase("Occupation", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      occupation.setHeader(true);
                    			      occupation.setColspan(8);
                    			      datatable.addCell(occupation);
                    			      Cell occupationvalue=new Cell(new Phrase(mapDto.getSelectedOccupationName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      occupationvalue.setHeader(true);
                    			      occupationvalue.setColspan(14);
                    			      datatable.addCell(occupationvalue);
                    			      
                    			      Cell proof=new Cell(new Phrase("Photo proof ID", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proof.setHeader(true);
                    			      proof.setColspan(5);
                    			      datatable.addCell(proof);
                    			      Cell proofvalue=new Cell(new Phrase(mapDto.getSelectedPhotoIdName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofvalue.setHeader(true);
                    			      proofvalue.setColspan(6);
                    			      datatable.addCell(proofvalue);
                    			      
                    			      Cell proofId=new Cell(new Phrase("ID No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      proofId.setHeader(true);
                    			      proofId.setColspan(5);
                    			      datatable.addCell(proofId);
                    			      Cell proofIdvalue=new Cell(new Phrase(mapDto.getIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      proofIdvalue.setHeader(true);
                    			      proofIdvalue.setColspan(6);
                    			      datatable.addCell(proofIdvalue);
                    			      
                    			 
                			    	  
                			      }
                			      
                			      Cell upl2=new Cell(new Phrase("PHOTO ID PROOF UPLOAD", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    		                   		upl2.setHeader(true);
    		                   		upl2.setColspan(8);
    		                   		datatable.addCell(upl2);
    		                   		Cell upl2value=new Cell(new Phrase(mapDto.getU2DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    		                   		upl2value.setHeader(true);
    		                   		upl2value.setColspan(14);
    		                   		datatable.addCell(upl2value);
    		                   

      		                   		if(regForm.getDeedTypeFlag()==0){
      		                   			
      		                   		setComplianceListOnPdf(mapDto,datatable);
      		                   			
      		                   		}
                			      
                			    //OWNER DETAILS IN CASE OF POA BELOW
                		  
                			      if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
                 			      {
                			    	  
                			    	  
                			    	  setOwnerOnPdf(mapDto,datatable);
                			    	  
                			    	  /*
                			    	 jj
                			    	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
                			    	  ownerHdr.setHeader(true);
                			    	  ownerHdr.setColspan(22);
                    			      datatable.addCell(ownerHdr);
                			    	  
                			    	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                			    	  ownerName.setHeader(true);
                			    	  ownerName.setColspan(5);
                    			      datatable.addCell(ownerName);
                    			      Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNamevalue.setHeader(true);
                    			      ownerNamevalue.setColspan(6);
                    			      datatable.addCell(ownerNamevalue);
                    			      
                    			      Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerOrgName.setHeader(true);
                    			      ownerOrgName.setColspan(5);
                    			      datatable.addCell(ownerOrgName);
                    			      Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerOrgNamevalue.setHeader(true);
                    			      ownerOrgNamevalue.setColspan(6);
                    			      datatable.addCell(ownerOrgNamevalue);
                    			      
                    			      Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerGender.setHeader(true);
                    			      ownerGender.setColspan(5);
                    			      datatable.addCell(ownerGender);
                    			      Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerGendervalue.setHeader(true);
                    			      ownerGendervalue.setColspan(6);
                    			      datatable.addCell(ownerGendervalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerDob.setHeader(true);
                    			      ownerDob.setColspan(5);
                    			      datatable.addCell(ownerDob);
                    			      Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerDobvalue.setHeader(true);
                    			      ownerDobvalue.setColspan(6);
                    			      datatable.addCell(ownerDobvalue);
                    			      
                    			      Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerNationality.setHeader(true);
                    			      ownerNationality.setColspan(8);
                    			      datatable.addCell(ownerNationality);
                    			      Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerNationalityvalue.setHeader(true);
                    			      ownerNationalityvalue.setColspan(14);
                    			      datatable.addCell(ownerNationalityvalue);
                    			      
                    			      Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerAddress.setHeader(true);
                    			      ownerAddress.setColspan(8);
                    			      datatable.addCell(ownerAddress);
                    			      Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerAddressvalue.setHeader(true);
                    			      ownerAddressvalue.setColspan(14);
                    			      datatable.addCell(ownerAddressvalue);
                    			      
                    			      Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerMobile.setHeader(true);
                    			      ownerMobile.setColspan(5);
                    			      datatable.addCell(ownerMobile);
                    			      Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerMobilevalue.setHeader(true);
                    			      ownerMobilevalue.setColspan(6);
                    			      datatable.addCell(ownerMobilevalue);
                    			      
                    			      Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerEmail.setHeader(true);
                    			      ownerEmail.setColspan(5);
                    			      datatable.addCell(ownerEmail);
                    			      Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerEmailvalue.setHeader(true);
                    			      ownerEmailvalue.setColspan(6);
                    			      datatable.addCell(ownerEmailvalue);
                    			      
                    			      Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProof.setHeader(true);
                    			      ownerProof.setColspan(5);
                    			      datatable.addCell(ownerProof);
                    			      Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofvalue.setHeader(true);
                    			      ownerProofvalue.setColspan(6);
                    			      datatable.addCell(ownerProofvalue);
                    			      
                    			      Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    			      ownerProofId.setHeader(true);
                    			      ownerProofId.setColspan(5);
                    			      datatable.addCell(ownerProofId);
                    			      Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
                    			      ownerProofIdvalue.setHeader(true);
                    			      ownerProofIdvalue.setColspan(6);
                    			      datatable.addCell(ownerProofIdvalue);
                			    	  
                			    	  
                 			    	  
                 			      */}
                		  
                		  
                		  
                		  
                		  
                		  
                		  
                		  
                	  }
                		  
                		  
                		  
                	  }
                	  
                	  }
            	  
            	  //WRITING PROPERTY DETAILS ON PDF
            	  
            	  datatable.addCell(row);
            	  Cell propertyHdr=new Cell(new Phrase("Property Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
            	  propertyHdr.setHeader(true);
            	  propertyHdr.setColspan(22);
			      datatable.addCell(propertyHdr);
            	  
            	  Cell propertyId=new Cell(new Phrase("Property Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  propertyId.setHeader(true);
            	  propertyId.setColspan(8);
            	  //propertyId.setBackgroundColor(new Color(255,130,171));
			      datatable.addCell(propertyId);
			      Cell propertyIdvalue=new Cell(new Phrase(dto.getSelectedPropId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propertyIdvalue.setHeader(true);
			      propertyIdvalue.setColspan(14);
			      //propertyIdvalue.setBackgroundColor(new Color(255,255,36));
			      datatable.addCell(propertyIdvalue);
            	  
			      /*if(dto.getFloorCount()==0){
			      Cell considerAmt=new Cell(new Phrase("Consideration Amount (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      considerAmt.setHeader(true);
			      considerAmt.setColspan(8);
            	  datatable.addCell(considerAmt);
			      Cell considerAmtvalue=new Cell(new Phrase(obj.format(dto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      considerAmtvalue.setHeader(true);
			      considerAmtvalue.setColspan(14);
			      datatable.addCell(considerAmtvalue);
			      }*/
			      Cell guideline=new Cell(new Phrase("Current guideline value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      guideline.setHeader(true);
			      guideline.setColspan(8);
            	  datatable.addCell(guideline);
			      Cell guidelinevalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto.getGuidelineValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      guidelinevalue.setHeader(true);
			      guidelinevalue.setColspan(14);
			      datatable.addCell(guidelinevalue);
			      
			      /*Cell system=new Cell(new Phrase("System generated valuation (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      system.setHeader(true);
			      system.setColspan(8);
            	  datatable.addCell(system);
			      Cell systemvalue=new Cell(new Phrase(obj.format(dto.getSystemMV()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      systemvalue.setHeader(true);
			      systemvalue.setColspan(14);
			      datatable.addCell(systemvalue);*/
			      
			      /*Cell mv=new Cell(new Phrase("Market Value (INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      mv.setHeader(true);
			      mv.setColspan(8);
            	  datatable.addCell(mv);
			      Cell mvvalue=new Cell(new Phrase(obj.format(Double.parseDouble(dto.getMarketValue())), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      mvvalue.setHeader(true);
			      mvvalue.setColspan(14);
			      datatable.addCell(mvvalue);*/
			      
			      Cell propType=new Cell(new Phrase("Property Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propType.setHeader(true);
			      propType.setColspan(8);
            	  datatable.addCell(propType);
			      Cell propTypevalue=new Cell(new Phrase(dto.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propTypevalue.setHeader(true);
			      propTypevalue.setColspan(14);
			      datatable.addCell(propTypevalue);
            	  
			      Cell propAddress=new Cell(new Phrase("Property Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propAddress.setHeader(true);
			      propAddress.setColspan(8);
            	  datatable.addCell(propAddress);
			      Cell propAddressvalue=new Cell(new Phrase(dto.getPropAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAddressvalue.setHeader(true);
			      propAddressvalue.setColspan(14);
			      datatable.addCell(propAddressvalue);
			      
			      Cell propLandmark=new Cell(new Phrase("Property Landmark", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propLandmark.setHeader(true);
			      propLandmark.setColspan(8);
            	  datatable.addCell(propLandmark);
			      Cell propLandmarkvalue=new Cell(new Phrase(dto.getPropLandmark(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propLandmarkvalue.setHeader(true);
			      propLandmarkvalue.setColspan(14);
			      datatable.addCell(propLandmarkvalue);
			      
			      Cell propMohl=new Cell(new Phrase("Mohalla/Colony name/Society/Road/Gram", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propMohl.setHeader(true);
			      propMohl.setColspan(5);
            	  datatable.addCell(propMohl);
			      Cell propMohlvalue=new Cell(new Phrase(dto.getMohallaName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propMohlvalue.setHeader(true);
			      propMohlvalue.setColspan(6);
			      datatable.addCell(propMohlvalue);
			      
			      Cell propWarkPatwari=new Cell(new Phrase("Ward / Patwari Halka", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWarkPatwari.setHeader(true);
			      propWarkPatwari.setColspan(5);
            	  datatable.addCell(propWarkPatwari);
			      Cell propWarkPatwarivalue=new Cell(new Phrase(dto.getPatwariStatus(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWarkPatwarivalue.setHeader(true);
			      propWarkPatwarivalue.setColspan(6);
			      datatable.addCell(propWarkPatwarivalue);
			      
			      Cell propWarkPatwariNo=new Cell(new Phrase("ward Number/Patwari Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWarkPatwariNo.setHeader(true);
			      propWarkPatwariNo.setColspan(5);
            	  datatable.addCell(propWarkPatwariNo);
			      Cell propWarkPatwariNovalue=new Cell(new Phrase(dto.getWardpatwarName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWarkPatwariNovalue.setHeader(true);
			      propWarkPatwariNovalue.setColspan(6);
			      datatable.addCell(propWarkPatwariNovalue);
			      
			      Cell propGovBody=new Cell(new Phrase("Governing Municipal Body", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propGovBody.setHeader(true);
			      propGovBody.setColspan(5);
            	  datatable.addCell(propGovBody);
			      Cell propGovBodyvalue=new Cell(new Phrase(dto.getMunicipalBodyName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propGovBodyvalue.setHeader(true);
			      propGovBodyvalue.setColspan(6);
			      datatable.addCell(propGovBodyvalue);
			      
			      Cell propAreaType=new Cell(new Phrase("Type of Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propAreaType.setHeader(true);
			      propAreaType.setColspan(8);
            	  datatable.addCell(propAreaType);
			      Cell propAreaTypevalue=new Cell(new Phrase(dto.getAreaTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAreaTypevalue.setHeader(true);
			      propAreaTypevalue.setColspan(14);
			      datatable.addCell(propAreaTypevalue);
			      
			      Cell propTehsil=new Cell(new Phrase("Tehsil", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propTehsil.setHeader(true);
			      propTehsil.setColspan(5);
            	  datatable.addCell(propTehsil);
			      Cell propTehsilvalue=new Cell(new Phrase(dto.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propTehsilvalue.setHeader(true);
			      propTehsilvalue.setColspan(6);
			      datatable.addCell(propTehsilvalue);
			      
			      Cell propDist=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propDist.setHeader(true);
			      propDist.setColspan(5);
            	  datatable.addCell(propDist);
			      Cell propDistvalue=new Cell(new Phrase(dto.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propDistvalue.setHeader(true);
			      propDistvalue.setColspan(6);
			      datatable.addCell(propDistvalue);
			      
			      Cell propVikas=new Cell(new Phrase("Vikas Khand (development block)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propVikas.setHeader(true);
			      propVikas.setColspan(5);
            	  datatable.addCell(propVikas);
			      Cell propVikasvalue=new Cell(new Phrase(dto.getVikasId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propVikasvalue.setHeader(true);
			      propVikasvalue.setColspan(6);
			      datatable.addCell(propVikasvalue);
			      
			      Cell propRi=new Cell(new Phrase("R. I. Circle", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propRi.setHeader(true);
			      propRi.setColspan(5);
            	  datatable.addCell(propRi);
			      Cell propRivalue=new Cell(new Phrase(dto.getRicircle(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propRivalue.setHeader(true);
			      propRivalue.setColspan(6);
			      datatable.addCell(propRivalue);
			      
			      Cell propLayout=new Cell(new Phrase("Layout Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propLayout.setHeader(true);
			      propLayout.setColspan(5);
            	  datatable.addCell(propLayout);
			      Cell propLayoutvalue=new Cell(new Phrase(dto.getLayoutdet(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propLayoutvalue.setHeader(true);
			      propLayoutvalue.setColspan(6);
			      datatable.addCell(propLayoutvalue);
			      
			      Cell propNazool=new Cell(new Phrase("Nazool/Sheet Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNazool.setHeader(true);
			      propNazool.setColspan(5);
            	  datatable.addCell(propNazool);
			      Cell propNazoolvalue=new Cell(new Phrase(dto.getSheetnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propNazoolvalue.setHeader(true);
			      propNazoolvalue.setColspan(6);
			      datatable.addCell(propNazoolvalue);
			      
			      Cell propPlotNo=new Cell(new Phrase("Plot Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propPlotNo.setHeader(true);
			      propPlotNo.setColspan(8);
            	  datatable.addCell(propPlotNo);
			      Cell propPlotNovalue=new Cell(new Phrase(dto.getPlotnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propPlotNovalue.setHeader(true);
			      propPlotNovalue.setColspan(14);
			      datatable.addCell(propPlotNovalue);
			      
			      Cell khasraHdr=new Cell(new Phrase("Khasra Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      khasraHdr.setHeader(true);
			      khasraHdr.setColspan(22);
			      datatable.addCell(khasraHdr);
            	  
			      //Sr.No. Khasra Number  Area (sq mtr) Lagaan/Land Revenue(INR) RIN Pustika Number  
            	  
			      Cell srNo=new Cell(new Phrase("Sr.No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      srNo.setHeader(true);
			      srNo.setColspan(2);
            	  datatable.addCell(srNo);
            	  
            	  Cell khasraNo=new Cell(new Phrase("Khasra Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  khasraNo.setHeader(true);
            	  khasraNo.setColspan(5);
            	  datatable.addCell(khasraNo);
            	  
            	  Cell khasraArea=new Cell(new Phrase("Khasra Area ("+dto.getUnit()+")", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  khasraArea.setHeader(true);
            	  khasraArea.setColspan(5);
            	  datatable.addCell(khasraArea);
            	  
            	  Cell lagaan=new Cell(new Phrase("Lagaan/Land Revenue(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  lagaan.setHeader(true);
            	  lagaan.setColspan(5);
            	  datatable.addCell(lagaan);
            	  
            	  Cell rinPustika=new Cell(new Phrase("RIN Pustika Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  rinPustika.setHeader(true);
            	  rinPustika.setColspan(5);
            	  datatable.addCell(rinPustika);
            	  
            	  if(dto.getKhasraDetlsDisplay()!=null && dto.getKhasraDetlsDisplay().size()>0){
            		  CommonDTO objDto;
            	  
            	      for(int m=0;m<dto.getKhasraDetlsDisplay().size();m++){
            	    	  objDto=(CommonDTO)dto.getKhasraDetlsDisplay().get(m);
            	    	  
            	    	  Cell srNovalue=new Cell(new Phrase(""+(m+1)+"", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	    	  srNovalue.setHeader(true);
            	    	  srNovalue.setColspan(2);
                    	  datatable.addCell(srNovalue);
                    	  
                    	  Cell khasraNovalue=new Cell(new Phrase(objDto.getKhasraNum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  khasraNovalue.setHeader(true);
                    	  khasraNovalue.setColspan(5);
                    	  datatable.addCell(khasraNovalue);
                    	  
                    	  Cell khasraAreavalue=new Cell(new Phrase(objDto.getKhasraArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  khasraAreavalue.setHeader(true);
                    	  khasraAreavalue.setColspan(5);
                    	  datatable.addCell(khasraAreavalue);
                    	  
                    	  Cell lagaanvalue=new Cell(new Phrase(objDto.getLagaan(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  lagaanvalue.setHeader(true);
                    	  lagaanvalue.setColspan(5);
                    	  datatable.addCell(lagaanvalue);
                    	  
                    	  Cell rinPustikavalue=new Cell(new Phrase(objDto.getRinPustika(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
                    	  rinPustikavalue.setHeader(true);
                    	  rinPustikavalue.setColspan(5);
                    	  datatable.addCell(rinPustikavalue);
            	    	  
            	    	  
            	    	  
            	      }
            	  }
            	  
            	  Cell fourBoundryHdr=new Cell(new Phrase("Four Boundary Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
            	  fourBoundryHdr.setHeader(true);
            	  fourBoundryHdr.setColspan(22);
			      datatable.addCell(fourBoundryHdr);
			      
			      Cell propNorth=new Cell(new Phrase("North", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNorth.setHeader(true);
			      propNorth.setColspan(5);
            	  datatable.addCell(propNorth);
			      Cell propNorthvalue=new Cell(new Phrase(dto.getNorth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propNorthvalue.setHeader(true);
			      propNorthvalue.setColspan(6);
			      datatable.addCell(propNorthvalue);
			      
			      Cell propSouth=new Cell(new Phrase("South", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propSouth.setHeader(true);
			      propSouth.setColspan(5);
            	  datatable.addCell(propSouth);
			      Cell propSouthvalue=new Cell(new Phrase(dto.getSouth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propSouthvalue.setHeader(true);
			      propSouthvalue.setColspan(6);
			      datatable.addCell(propSouthvalue);
			      
			      Cell propEast=new Cell(new Phrase("East", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propEast.setHeader(true);
			      propEast.setColspan(5);
            	  datatable.addCell(propEast);
			      Cell propEastvalue=new Cell(new Phrase(dto.getEast(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propEastvalue.setHeader(true);
			      propEastvalue.setColspan(6);
			      datatable.addCell(propEastvalue);
			      
			      Cell propWest=new Cell(new Phrase("West", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propWest.setHeader(true);
			      propWest.setColspan(5);
            	  datatable.addCell(propWest);
			      Cell propWestvalue=new Cell(new Phrase(dto.getWest(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propWestvalue.setHeader(true);
			      propWestvalue.setColspan(6);
			      datatable.addCell(propWestvalue);
			      

			      datatable.addCell(row);
			        
			      if(dto.getFloorCount()==0){
			      String area=dto.getTotalSqMeterDisplay();
			      
			      Cell propArea=new Cell(new Phrase("Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propArea.setHeader(true);
			      propArea.setColspan(8);
            	  datatable.addCell(propArea);
			      Cell propAreavalue=new Cell(new Phrase(area+" "+dto.getUnit(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			      propAreavalue.setHeader(true);
			      propAreavalue.setColspan(14);
			      datatable.addCell(propAreavalue);
			      }
			      
			      Cell propNoFloor=new Cell(new Phrase("Number of Constructed Floors", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      propNoFloor.setHeader(true);
			      propNoFloor.setColspan(8);
            	  datatable.addCell(propNoFloor);
			      if(dto.getFloorCount()==0){
			    	  Cell propNoFloorvalue=new Cell(new Phrase(RegInitConstant.NOT_APPLICABLE, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNoFloorvalue.setHeader(true);
				      propNoFloorvalue.setColspan(14);
				      datatable.addCell(propNoFloorvalue);
			      }else{
			    	  Cell propNoFloorvalue=new Cell(new Phrase(Integer.toString(dto.getFloorCount()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
				      propNoFloorvalue.setHeader(true);
				      propNoFloorvalue.setColspan(14);
				      datatable.addCell(propNoFloorvalue);
			      }
			      
			      
			      
			      if(dto.getFloorCount()==0){
			    	  
			    	  //subclause non building
			    	  if(dto.getSelectedSubClauseList()!=null && dto.getSelectedSubClauseList().size()>0){
			    		  
			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    		  subClauseHdr.setHeader(true);
			    		  subClauseHdr.setColspan(22);
        			      datatable.addCell(subClauseHdr);
        			      
        			     for(int n=0;n<dto.getSelectedSubClauseList().size();n++){
        			    	 
        			    	 CommonDTO subClausedto = new CommonDTO();
        			    	 subClausedto=(CommonDTO)dto.getSelectedSubClauseList().get(n);
        			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			    	 subClauseValue.setHeader(true);
        			    	 subClauseValue.setColspan(22);
           			         datatable.addCell(subClauseValue);
        			    	 
        			    	 
        			    	 
        			     }
			    		  
			    		  
			    	  }
			    	  
			    	  
			    	  
			    	  
			    	  
			      }else if(dto.getFloorCount()>0){
			    	  //floor details
			    	  
			    	  	if(dto.getMapBuilding()!=null && dto.getMapBuilding().size()>0){
			    	  		
			    	  		Cell floorHdr=new Cell(new Phrase("Floor Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			    	  		floorHdr.setHeader(true);
			    	  		floorHdr.setColspan(22);
          			      datatable.addCell(floorHdr);
			    	  		Collection mapCollection2=dto.getMapBuilding().values();
          			      Object[] l2=mapCollection2.toArray();
                            
			    	  		RegCompletDTO floordto;
			    		   for(int p=0;p<l2.length;p++){
        			    	 
			    			   floordto = new RegCompletDTO();
			    			   floordto=(RegCompletDTO)l2[p];
        			    	 
        			    	 Cell buildingType=new Cell(new Phrase("Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			    	 buildingType.setHeader(true);
        			    	 buildingType.setColspan(5);
           			      	 datatable.addCell(buildingType);
        			    	 Cell buildingTypeValue=new Cell(new Phrase(floordto.getUsageBuilding(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			    	 buildingTypeValue.setHeader(true);
        			    	 buildingTypeValue.setColspan(6);
           			         datatable.addCell(buildingTypeValue);
           			         
           			         Cell roofType=new Cell(new Phrase("Sub-Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
           			         roofType.setHeader(true);
           			         roofType.setColspan(5);
           			         datatable.addCell(roofType);
           			         Cell roofTypeValue=new Cell(new Phrase(floordto.getCeilingType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
           			         roofTypeValue.setHeader(true);
           			         roofTypeValue.setColspan(6);
           			         datatable.addCell(roofTypeValue);
        			      
           			         Cell floorType=new Cell(new Phrase("Floor Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
           			         floorType.setHeader(true);
           			         floorType.setColspan(5);
        			      	 datatable.addCell(floorType);
        			      	 Cell floorTypeValue=new Cell(new Phrase(floordto.getTypeFloorDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			      	 floorTypeValue.setHeader(true);
        			      	 floorTypeValue.setColspan(6);
        			         datatable.addCell(floorTypeValue);
        			         
        			         Cell consdAmt=new Cell(new Phrase("Consideration Amount (Rs.)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         consdAmt.setHeader(true);
        			         consdAmt.setColspan(5);
        			         datatable.addCell(consdAmt);
        			         Cell consdAmtValue=new Cell(new Phrase(Double.toString(floordto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         consdAmtValue.setHeader(true);
        			         consdAmtValue.setColspan(6);
        			         datatable.addCell(consdAmtValue);
     			      
        			         Cell floorTotArea=new Cell(new Phrase("Total Area (Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         floorTotArea.setHeader(true);
        			         floorTotArea.setColspan(5);
        			         datatable.addCell(floorTotArea);
        			         Cell floorTotAreaValue=new Cell(new Phrase(floordto.getTotalSqMeterDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         floorTotAreaValue.setHeader(true);
        			         floorTotAreaValue.setColspan(6);
        			         datatable.addCell(floorTotAreaValue);
   			         
        			         Cell floorConstArea=new Cell(new Phrase("Constructed Area(Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
        			         floorConstArea.setHeader(true);
        			         floorConstArea.setColspan(5);
        			         datatable.addCell(floorConstArea);
        			         Cell floorConstAreaValue=new Cell(new Phrase(floordto.getConstructedArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
        			         floorConstAreaValue.setHeader(true);
        			         floorConstAreaValue.setColspan(6);
        			         datatable.addCell(floorConstAreaValue);
        			         
        			         if(floordto.getSelectedSubClauseList()!=null && floordto.getSelectedSubClauseList().size()>0){
       			    		  
       			    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
       			    		  subClauseHdr.setHeader(true);
       			    		  subClauseHdr.setColspan(22);
               			      datatable.addCell(subClauseHdr);
               			      
               			     for(int f=0;f<floordto.getSelectedSubClauseList().size();f++){
               			    	 
               			    	 CommonDTO subClausedto2 = new CommonDTO();
               			    	 subClausedto2=(CommonDTO)floordto.getSelectedSubClauseList().get(f);
               			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto2.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
               			    	 subClauseValue.setHeader(true);
               			    	 subClauseValue.setColspan(22);
                  			         datatable.addCell(subClauseValue);
               			    	 
               			    	 
               			    	 
               			     }
       			    		  
       			    		  
       			    	  }
        			          
        			    	 
        			    	 
        			    	 
        			     }
			    		  
			    		  
			    	  }
			    	  
			      }
			      
			      Cell uplAngle1=new Cell(new Phrase("ANGLE 1", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      uplAngle1.setHeader(true);
			      uplAngle1.setColspan(8);
			        datatable.addCell(uplAngle1);
			        Cell uplAngle1value=new Cell(new Phrase(dto.getPropImage1DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplAngle1value.setHeader(true);
			        uplAngle1value.setColspan(14);
			        datatable.addCell(uplAngle1value);
			        
			        Cell uplAngle2=new Cell(new Phrase("ANGLE 2", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			      uplAngle2.setHeader(true);
  			      uplAngle2.setColspan(8);
  			        datatable.addCell(uplAngle2);
  			        Cell uplAngle2value=new Cell(new Phrase(dto.getPropImage2DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplAngle2value.setHeader(true);
  			        uplAngle2value.setColspan(14);
  			        datatable.addCell(uplAngle2value);
  			        
  			      Cell uplAngle3=new Cell(new Phrase("ANGLE 3", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			      uplAngle3.setHeader(true);
			      uplAngle3.setColspan(8);
			        datatable.addCell(uplAngle3);
			        Cell uplAngle3value=new Cell(new Phrase(dto.getPropImage3DocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplAngle3value.setHeader(true);
			        uplAngle3value.setColspan(14);
			        datatable.addCell(uplAngle3value);
			        
			        Cell uplMap=new Cell(new Phrase("Property Map as per rules", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
			        uplMap.setHeader(true);
			        uplMap.setColspan(8);
  			        datatable.addCell(uplMap);
  			        Cell uplMapvalue=new Cell(new Phrase(dto.getPropMapDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplMapvalue.setHeader(true);
  			        uplMapvalue.setColspan(14);
  			        datatable.addCell(uplMapvalue);
  			        
  			      if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
  				{
  			    	  
  			    	Cell uplRin=new Cell(new Phrase("RIN Pustika for Agriculture Land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			    	uplRin.setHeader(true);
  			    	uplRin.setColspan(8);
  			        datatable.addCell(uplRin);
  			        Cell uplRinvalue=new Cell(new Phrase(dto.getPropRinDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  			        uplRinvalue.setHeader(true);
  			        uplRinvalue.setColspan(14);
  			        datatable.addCell(uplRinvalue);
  			        
  			      Cell uplKhasra=new Cell(new Phrase("Computerized Khasra of 1 year certified by revenue official for agricultural land", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			      uplKhasra.setHeader(true);
  			      uplKhasra.setColspan(8);
			        datatable.addCell(uplKhasra);
			        Cell uplKhasravalue=new Cell(new Phrase(dto.getPropKhasraDocumentName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			        uplKhasravalue.setHeader(true);
			        uplKhasravalue.setColspan(14);
			        datatable.addCell(uplKhasravalue);
			        
  				
  				} 	  
            	  
              }
		      
		      
		      
	}
		      else{
		
		Cell noDataFound=new Cell(new Phrase("No data found", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		noDataFound.setHeader(true);
		noDataFound.setColspan(22);
	      //balanceon.setBackgroundColor(new Color(255,255,36));
	      datatable.addCell(noDataFound);
	      
	      datatable.addCell(row);
		
	}
		      
	
		    	  
		    	  
		    	  
		    	  
		    	  
		    	  
		      }
		      
		      
		      
		 
		      
		      			      
		      datatable.setCellsFitPage(true);
		      document.add(datatable);	
		      document.close();
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=\"Registration Details.pdf");
				response.setContentLength(baos.size());
				ServletOutputStream out = response.getOutputStream();
				baos.writeTo(out);
				out.flush();
		
		
		
		
		
		}catch(Exception e){
			
			e.printStackTrace();
			
		}
	
	
	

	return regForm;
	
	
}
/**
 * getExchngDeedPropPartyDetlsForPDF
 * @param String regTxnId
 * @return HashMap
 * @author ROOPAM
 */
public HashMap getExchngDeedPropPartyDetlsForPDF(String regTxnId,int deedId,String languageLocale) throws Exception {
	
	HashMap map=new HashMap();
	RegCompletDTO dto;
	RegCompletDTO finalDto;
	
	try{
	
	
	int partyCount=0;
	int loopConstant=1;
	
	if(deedId==RegInitConstant.DEED_EXCHANGE){
		loopConstant=2;
	}
	
	while(partyCount<loopConstant)
	{
		finalDto=new RegCompletDTO();
		partyCount++;
		
		
		String[] propIdArr=getPropIdsExchngPdf(regTxnId,partyCount,deedId);
		
	if(propIdArr!=null && propIdArr.length>0){
		
		for(int i=0;i<propIdArr.length;i++){
			dto=new RegCompletDTO();
			dto.setPartyListPdf(new ArrayList());
			
			
			if(propIdArr[i].trim().length()==15){
				dto.setSelectedPropId("0"+propIdArr[i].trim());
			}else{
				dto.setSelectedPropId(propIdArr[i].trim());
			}
		
			//valuation id
			String valuationId=commonBd.getPropValuationId(regTxnId,propIdArr[i].trim());
			
			
			//details corresponding to valuation id
			CustomArrayList propList = new CustomArrayList();
			
			
			propList=getPropDetlsForDisplay(propIdArr[i].trim());
			//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
			String propDetails=propList.toString();
			String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
			
			ArrayList considAmnt=getConsidAmtSysMV(valuationId);
			
			if(considAmnt!=null){
			String considAmntDetails=considAmnt.toString().substring(2, (considAmnt.toString().length()-2));
			String considAmntDetsArray[]=considAmntDetails.split(",");
			dto.setConsiderAmt(Double.parseDouble(considAmntDetsArray[0]));
			dto.setSystemMV(Double.parseDouble(considAmntDetsArray[1]));
			}else{
				dto.setConsiderAmt(0);
				dto.setSystemMV(0);
			}
		 	
			
			dto.setPropertyTypeName(propDetsArray[14].trim());
			dto.setDistName(propDetsArray[1].trim());
			dto.setTehsilName(propDetsArray[3].trim());
			dto.setAreaTypeName(propDetsArray[5].trim());
			if(propDetsArray[15].trim()!=null && !propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")){
			dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
			}else{
				dto.setTotalSqMeter(0);
			}
			dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
			if(dto.getPropertyTypeName().equalsIgnoreCase("plot") ||
					dto.getPropertyTypeName().equalsIgnoreCase("building"))
			{
				
				dto.setUnit("SQM");
			}
			else
			{
				
				String unit=propDetsArray[16].trim();
				if(unit.equalsIgnoreCase("2"))
				{
					dto.setUnit("SQM");
				}
				else if(unit.equalsIgnoreCase("3"))
				{
					dto.setUnit("HECTARE");
				}
				
			}
			//dto.setUnit("");
			//dto.setMunicipalDutyName("");
			dto.setMunicipalBodyName(propDetsArray[12].trim());
			String wardstatus=propDetsArray[8].trim();//////
			
			if (wardstatus.equalsIgnoreCase("1"))
			{
				wardstatus="Ward";
			}
			else
			{
				wardstatus="Patwari/Halka";
			}
			dto.setPatwariStatus(wardstatus);
			dto.setWardpatwarName(propDetsArray[7].trim());
			dto.setMohallaName(propDetsArray[10].trim());
			//no. of floors.
			
			if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null"))
			dto.setVikasId("-");
			else
			dto.setVikasId(propDetsArray[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setRicircle(propDetsArray[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
			dto.setLayoutdet("-");
			else
			dto.setLayoutdet(propDetsArray[19].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
			dto.setSheetnum("-");	
			else
			dto.setSheetnum(propDetsArray[20].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
			dto.setPlotnum("-");
			else
			dto.setPlotnum(propDetsArray[21].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setPropAddress(propDetsArray[22].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setNorth(propDetsArray[23].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setSouth(propDetsArray[24].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setEast(propDetsArray[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setWest(propDetsArray[26].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
				dto.setPropLandmark("-");
				else
				dto.setPropLandmark(propDetsArray[27].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			
			if(	!dto.getPropertyTypeName().equalsIgnoreCase("building"))
			{
				
				dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
				
			}
			//BELOW CODE FOR UPLOADS
			//28
			if(propDetsArray[28].trim().equalsIgnoreCase("") || propDetsArray[28].trim().equalsIgnoreCase("null")){
				dto.setPropImage1DocumentName("No");}
          		else{
          			dto.setPropImage1DocumentName("Yes");}
			if(propDetsArray[29].trim().equalsIgnoreCase("") || propDetsArray[29].trim().equalsIgnoreCase("null")){
				dto.setPropImage2DocumentName("No");}
          		else{
          			dto.setPropImage2DocumentName("Yes");}
			if(propDetsArray[30].trim().equalsIgnoreCase("") || propDetsArray[30].trim().equalsIgnoreCase("null")){
				dto.setPropImage3DocumentName("No");}
          		else{
          			dto.setPropImage3DocumentName("Yes");}
			if(propDetsArray[31].trim().equalsIgnoreCase("") || propDetsArray[31].trim().equalsIgnoreCase("null")){
				dto.setPropMapDocumentName("No");}
          		else{
          			dto.setPropMapDocumentName("Yes");}
			if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
			{
			if(propDetsArray[32].trim().equalsIgnoreCase("") || propDetsArray[32].trim().equalsIgnoreCase("null")){
				dto.setPropRinDocumentName("No");}
          		else{
          			dto.setPropRinDocumentName("Yes");}
			if(propDetsArray[33].trim().equalsIgnoreCase("") || propDetsArray[33].trim().equalsIgnoreCase("null")){
				dto.setPropKhasraDocumentName("No");}
          		else{
          			dto.setPropKhasraDocumentName("Yes");}
			}
			
			dto.setMarketValue(propDetsArray[34].trim());
			//dto.setPropTypeL1Id(propDetsArray[35].trim());
			//dto.setPropTypeL2Id(propDetsArray[36].trim());
			
			String[] guidelineArr={propDetsArray[0].trim(),propDetsArray[2].trim(),propDetsArray[6].trim(),propDetsArray[9].trim(),
					               propDetsArray[13].trim(),propDetsArray[35].trim(),propDetsArray[36].trim()};
			
			String guidelineRate=getGuidelineRate(guidelineArr);
			
			if(guidelineRate!=null && !guidelineRate.equalsIgnoreCase(""))
			{
			dto.setGuidelineValue(guidelineRate);
			}else{
				dto.setGuidelineValue("0");
			}
			//34 MARKET VALUE
			//35 L1
			//36 L2
			
			//dto.setPropImage1DocumentName("ANGLE 1.JPG");
			//dto.setPropImage1FilePath(propDetsArray[28].trim());
			
			//dto.setPropImage2DocumentName("ANGLE 2.JPG");
			//dto.setPropImage2FilePath(propDetsArray[29].trim());
			
			//dto.setPropImage3DocumentName("ANGLE 3.JPG");
			//dto.setPropImage3FilePath(propDetsArray[30].trim());
			
			//dto.setPropMapDocumentName("MAP.JPG");
			//dto.setPropMapFilePath(propDetsArray[31].trim());
			
			/*if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
			{
			dto.setPropRinDocumentName("RIN.JPG");
			dto.setPropRinFilePath(propDetsArray[32].trim());
			
			dto.setPropKhasraDocumentName("KHASRA.JPG");
			dto.setPropKhasraFilePath(propDetsArray[33].trim());
			}*/
			
			
			
			//FOR GETTING PROPERTY KHASRA DETAILS
			String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propIdArr[i].trim());
			
			
			//following code for inserting khasra detls into map
			if(otherPropDetsArray!=null){
			String[] khasraNum=otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] khasraArea=otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] lagaan=otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] rinPustika=otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			
						
			int length=khasraNum.length;
			CommonDTO objDto;
			
			for(int j=0;j<length;j++){
				objDto=new CommonDTO();
				if(khasraNum[j].equalsIgnoreCase("null"))
				{
					objDto.setKhasraNum("-");
					objDto.setKhasraArea("-");
					objDto.setLagaan("-");
					objDto.setRinPustika("-");
				}else{
				objDto.setKhasraNum(khasraNum[j]);
				objDto.setKhasraArea(khasraArea[j]);
				objDto.setLagaan(lagaan[j]);
				objDto.setRinPustika(rinPustika[j]);
				}
				
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			}else{
				CommonDTO objDto=new CommonDTO();
				objDto.setKhasraNum("-");
				objDto.setKhasraArea("-");
				objDto.setLagaan("-");
				objDto.setRinPustika("-");
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			HashMap buildingMap=new HashMap();
			if(propDetsArray[14].trim().equalsIgnoreCase("Building")){
			
				
				//following code for getting building floor details
				ArrayList buildingList=getGuildingFloorDetails(valuationId);
				if(buildingList.size()>0){
					
					dto.setFloorCount(buildingList.size());
					for(int j=0;j<buildingList.size();j++){
						RegCompletDTO dto1=new RegCompletDTO();
						ArrayList row_list=(ArrayList)buildingList.get(j);
						String str=row_list.toString();
						str=str.substring(1,str.length()-1);
						String[] strArray=str.split(",");
						dto1.setUsageBuilding(strArray[7]);
						dto1.setCeilingType(strArray[8]);
						dto1.setTypeFloorDesc(strArray[9]);
						dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
						dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
						dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
						dto1.setConstructedArea(strArray[6]);
						
						dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
						
						dto.getMapBuilding().put(strArray[0], dto1);
						
						
					}
					
					
					
				}
				
				
				
				
			}else{
				dto.setFloorCount(0);
			}
			
			
		
			finalDto.getPropertyListExchngDeedPdf().add(dto);
		
		
		
	}
	
	
	
	}else{
		throw new Exception();
	}
	
	//BELOW CODE FOR GETTING LIST OF PARTY TXN IDS CORRESPONDING TO CURRENT PROPERTY ID IN LOOP
	
	String[] partyArr=getPartyIdsExchngPdf(regTxnId,partyCount,deedId);

	if(partyArr!=null && partyArr.length>0){
	
	for(int j=0;j<partyArr.length;j++){
		
		ArrayList list=commonBd.getPartyDetailsPdf(partyArr[j]);
		RegCommonDTO mapDto =new RegCommonDTO();
		
	if(list!=null && list.size()>0){
		
	//	for(int k=0;k<list.size();k++)
	//	{
		//ArrayList row_list=(ArrayList)list.get(k);
		String shareList=list.toString();
		shareList=shareList.substring(2, shareList.length()-2);
		
		String shareHolderArr[]=shareList.split(",");
		
		//code for insertion in map
             
             if(shareHolderArr[0].trim().length()==11){
            	 shareHolderArr[0]="0"+shareHolderArr[0].trim();
         		}
         		else{
         			shareHolderArr[0]=shareHolderArr[0].trim();
         		}
           
             mapDto.setListAdoptedPartyTrns(shareHolderArr[1].trim());
            
             mapDto.setApplicantOrTransParty("Applicant");
             mapDto.setPartyRoleTypeId(shareHolderArr[23].trim());
             mapDto.setPartyTypeFlag("C");
             
            	 if(shareHolderArr[25].trim().equals("") || shareHolderArr[25].trim().equals("null"))
            		mapDto.setRelationWithOwnerTrns("-");
            	 else
         	    mapDto.setRelationWithOwnerTrns(shareHolderArr[25].trim());
            	 if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null")){
            		mapDto.setShareOfPropTrns("Not Declared");
            		mapDto.setHdnDeclareShareCheck("N");
            	 }
            	 else{
         	    mapDto.setShareOfPropTrns(shareHolderArr[26].trim());
         	   mapDto.setHdnDeclareShareCheck("Y");
            	 }
            	 mapDto.setSelectedCountry(shareHolderArr[8].trim());
            	 mapDto.setSelectedCountryName(getCountryName(shareHolderArr[8].trim(),languageLocale));
            	 mapDto.setSelectedState(shareHolderArr[9].trim());
            	 mapDto.setSelectedStateName(getStateName(shareHolderArr[9].trim(),languageLocale));
            	 mapDto.setSelectedDistrict(shareHolderArr[10].trim());
            	 mapDto.setSelectedDistrictName(getDistrictName(shareHolderArr[10].trim(),languageLocale));
            	 mapDto.setOrgaddressTrns(shareHolderArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	
            	 if(shareHolderArr[14].trim().equals("") || shareHolderArr[14].trim().equals("null"))
            		 mapDto.setMobnoTrns("-"); 
            	 else
            	     mapDto.setMobnoTrns(shareHolderArr[14].trim());
            	 if(shareHolderArr[13].trim().equals("") || shareHolderArr[13].trim().equals("null"))
            		 mapDto.setPhnoTrns("-");
            	 else
            	     mapDto.setPhnoTrns(shareHolderArr[13].trim());
            	
            	     mapDto.setRoleName(commonBd.getRoleName(shareHolderArr[27].trim(),languageLocale));//pdf not in hindi
            	 mapDto.setPartyTypeTrns(shareHolderArr[27].trim());
            	 
            	 mapDto.setPartyTxnId(shareHolderArr[23].trim());
            	 
             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
            	 //organization
            	 
            	 mapDto.setListAdoptedPartyNameTrns("Organization");
            	 mapDto.setOgrNameTrns(shareHolderArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	 mapDto.setAuthPerNameTrns(shareHolderArr[19].trim());
            	 mapDto.setIndAuthPersn(shareHolderArr[19].trim());
            	/* mapDto.setOrgaddressTrns(shareHolderArr[11].trim());*/
            	 mapDto.setFnameTrns("");
           		 mapDto.setMnameTrns("");
            	 mapDto.setLnameTrns("");
            	// mapDto.setGendarTrns("-");
            	// mapDto.setSelectedGender("-");
            	 mapDto.setUserDOBTrns("");
            	 mapDto.setAgeTrns("");
               //	 mapDto.setFatherNameTrns("-");
            	 mapDto.setMotherNameTrns("");
           		 mapDto.setSpouseNameTrns("");
              	 mapDto.setNationalityTrns("");
            	 mapDto.setPostalCodeTrns("");
           		 mapDto.setEmailIDTrns("");
            	 mapDto.setSelectedPhotoId("");
            	 mapDto.setSelectedPhotoIdName("-");
            	 mapDto.setIdnoTrns("-");
            	
            	 //mapDto.setIndReligionTrns("");
            	 mapDto.setIndCategoryTrns("");
            	 mapDto.setIndDisabilityTrns("");
            	 mapDto.setIndDisabilityDescTrns("");
            	 mapDto.setIndPovertyLineTrns("");
            	 mapDto.setIndMinorityTrns("");
            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
            	 
            	 
				 mapDto.setGendarTrns(shareHolderArr[5].trim());
				 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
            		 mapDto.setSelectedGender("Male");
            	 else
            		 mapDto.setSelectedGender("Female");
            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
            	 if(shareHolderArr[47].trim()!=null && !shareHolderArr[47].trim().equalsIgnoreCase("") && !shareHolderArr[47].trim().equalsIgnoreCase("null")){
		              	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
		              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
		              	}else{
		              		mapDto.setRelationshipTrns(0);
			              	mapDto.setRelationshipNameTrns("");
		              	}
            	 
            	 mapDto.setAuthPerCountryNameTrns(getCountryName(shareHolderArr[48].trim(),languageLocale));
            	 mapDto.setAuthPerStatenameNameTrns(getStateName(shareHolderArr[49].trim(),languageLocale));
            	 mapDto.setAuthPerDistrictNameTrns(getDistrictName(shareHolderArr[50].trim(),languageLocale));
            	 mapDto.setAuthPerAddressTrns(shareHolderArr[51].trim());
             }
             
             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
            	 //individual
            	 
            	 mapDto.setListAdoptedPartyNameTrns("Individual");
            	 
            	 mapDto.setFnameTrns(shareHolderArr[2].trim());
            	 if(shareHolderArr[3].trim().equals("") || shareHolderArr[3].trim().equals("null"))
            		 mapDto.setMnameTrns("");
            	 else
            	     mapDto.setMnameTrns(shareHolderArr[3].trim());
            	 mapDto.setLnameTrns(shareHolderArr[4].trim());
            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
            		 mapDto.setSelectedGender("Male");
            	 else
            		 mapDto.setSelectedGender("Female");
            	 /*if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
            		 mapDto.setUserDOBTrns("-");
            	 else
            	     mapDto.setUserDOBTrns(convertDOBfromDb(shareHolderArr[6].trim()));*/
            	 if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
            		 mapDto.setAgeTrns("-");
            	 else
            	     mapDto.setAgeTrns(shareHolderArr[6].trim());
            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
            	 if(shareHolderArr[21].trim().equals("") || shareHolderArr[21].trim().equals("null"))
            		 mapDto.setMotherNameTrns("-");
            	 else
            	     mapDto.setMotherNameTrns(shareHolderArr[21].trim());
            	 
            	 if(shareHolderArr[28].trim().equals("") || shareHolderArr[28].trim().equals("null"))
            		 mapDto.setSpouseNameTrns("-");
            	 else
            	     mapDto.setSpouseNameTrns(shareHolderArr[28].trim());
            	 mapDto.setNationalityTrns(shareHolderArr[7].trim());
            	 
            	 if(shareHolderArr[12].trim().equals("") || shareHolderArr[12].trim().equals("null"))
            		 mapDto.setPostalCodeTrns("-");
            	 else
            	     mapDto.setPostalCodeTrns(shareHolderArr[12].trim());
            	
            	 if(shareHolderArr[15].trim().equals("") || shareHolderArr[15].trim().equals("null"))
            		 mapDto.setEmailIDTrns("-");
            	 else
            	     mapDto.setEmailIDTrns(shareHolderArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	 mapDto.setSelectedPhotoId(shareHolderArr[16].trim());
            	 
            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(shareHolderArr[16].trim(),languageLocale));               
            	 mapDto.setIdnoTrns(shareHolderArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	 mapDto.setOgrNameTrns("-");
            	 mapDto.setAuthPerNameTrns("-");
            	 mapDto.setIndAuthPersn(shareHolderArr[2].trim()+" "+shareHolderArr[4].trim());
            	 
               	 
               	 if(shareHolderArr[22].trim().equals("") || shareHolderArr[22].trim().equals("null"))
               	 {
               		mapDto.setIndCategoryTrns("-");
               	 }
               	 else
               	 {
            	    mapDto.setIndCategoryTrns(shareHolderArr[22].trim());
               	 }
               	 
               	 mapDto.setSelectedCategoryName(getCategoryName(shareHolderArr[22].trim(),languageLocale));
               	 
               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
               	 {
               		mapDto.setIndDisabilityTrns("-");
               	 }
               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
               	 {
            	    mapDto.setIndDisabilityTrns("Yes");
            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
            	    	mapDto.setIndDisabilityDescTrns("-");
            	    }else{
            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	    }
               	 }
               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
               	{
            	    mapDto.setIndDisabilityTrns("No");
               	}
               	 
               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
               		mapDto.setIndPovertyLineTrns("-");}
               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
            	    mapDto.setIndPovertyLineTrns("Yes");
            	     }
               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
            	    mapDto.setIndPovertyLineTrns("No");}
               	
               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
               		mapDto.setIndMinorityTrns("-");}
               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
            	    mapDto.setIndMinorityTrns("Yes");
            	     }
               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
            	    mapDto.setIndMinorityTrns("No");}
               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
               	
               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
              		mapDto.setIndScheduleAreaTrnsDisplay("No");
              		
              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
              			mapDto.setPermissionNoTrns("-");
              		}else{
              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
              		}
              		
              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
              		if(shareHolderArr[33].trim().equalsIgnoreCase("") || shareHolderArr[33].trim().equalsIgnoreCase("null")){
              		mapDto.setDocumentNameTrns("No");}
              		else{
              			mapDto.setDocumentNameTrns("Yes");}
              		
              	}else{
              		mapDto.setIndScheduleAreaTrnsDisplay("Yes");
              	}
              	
              	if(shareHolderArr[47].trim()!=null && !shareHolderArr[47].trim().equalsIgnoreCase("") && !shareHolderArr[47].trim().equalsIgnoreCase("null")){
	              	mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
	              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
	              	}else{
	              		mapDto.setRelationshipTrns(0);
		              	mapDto.setRelationshipNameTrns("");
	              	}
               	
             }
             //uploads
             if(shareHolderArr[37].trim().equalsIgnoreCase("") || shareHolderArr[37].trim().equalsIgnoreCase("null")){
           		mapDto.setU2DocumentNameTrns("No");}
           		else{
           			mapDto.setU2DocumentNameTrns("Yes");}
           	if(shareHolderArr[38].trim().equalsIgnoreCase("") || shareHolderArr[38].trim().equalsIgnoreCase("null")){
           		mapDto.setU3DocumentNameTrns("No");}
           		else{
           			mapDto.setU3DocumentNameTrns("Yes");}
           	if(shareHolderArr[39].trim().equalsIgnoreCase("") || shareHolderArr[39].trim().equalsIgnoreCase("null")){
           		mapDto.setU4DocumentNameTrns("No");
           		mapDto.setU7DocumentNameTrns("No");
           		
           	}
           		else{
           			mapDto.setU4DocumentNameTrns("Yes");
           			mapDto.setU7DocumentNameTrns("Yes");	
           		}
           	if(shareHolderArr[40].trim().equalsIgnoreCase("") || shareHolderArr[40].trim().equalsIgnoreCase("null")){
           		mapDto.setU5DocumentNameTrns("No");}
           		else{
           			mapDto.setU5DocumentNameTrns("Yes");}
           	if(shareHolderArr[41].trim().equalsIgnoreCase("") || shareHolderArr[41].trim().equalsIgnoreCase("null")){
           		mapDto.setU6DocumentNameTrns("No");
           		mapDto.setU9DocumentNameTrns("No");
           		
           	}
           		else{
           			mapDto.setU6DocumentNameTrns("Yes");
           			mapDto.setU9DocumentNameTrns("Yes");	
           		}
           	if(shareHolderArr[42].trim().equalsIgnoreCase("") || shareHolderArr[42].trim().equalsIgnoreCase("null")){
           		mapDto.setU8DocumentNameTrns("No");}
           		else{
           			mapDto.setU8DocumentNameTrns("Yes");}
           	if(shareHolderArr[43].trim().equalsIgnoreCase("") || shareHolderArr[43].trim().equalsIgnoreCase("null")){
           		mapDto.setU10DocumentNameTrns("No");
           		mapDto.setU11DocumentNameTrns("No");
           		
           	}
           		else{
           			mapDto.setU10DocumentNameTrns("Yes");
           			mapDto.setU11DocumentNameTrns("Yes");	
           		}
           	
           	
           	mapDto.setSrOfficeNameTrns(shareHolderArr[44].trim());
           	mapDto.setPoaRegNoTrns(shareHolderArr[45].trim());
           	if(shareHolderArr[46].trim()==null || shareHolderArr[46].trim().equalsIgnoreCase("") || shareHolderArr[46].trim().equalsIgnoreCase("null")){
           		
           		mapDto.setDatePoaRegTrns("");
           		}
           		else{
           			mapDto.setDatePoaRegTrns(convertDOB2(shareHolderArr[46].trim()));
           		}
             //end of upploads
             int roleId=Integer.parseInt(shareHolderArr[27].trim());
             
             String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
             
             if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
             {
             	
             	String ownerId=shareHolderArr[29].trim();
             	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
             	String ownerDetls=ownerList.toString();
             	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
             	String ownerDetlsArr[]=ownerDetls.split(",");
             	
            	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
            	 {
              mapDto.setOwnerOgrNameTrns("-");
              mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
              }
              else
              {
              mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
              mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
            	}
            	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f"))
            	 mapDto.setOwnerGendarTrns("Female");
            	 else
                 mapDto.setOwnerGendarTrns("Male");	 
            	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
            	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim());
            	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
            	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
                   		mapDto.setOwnerEmailIDTrns("-");
                   	 else
                	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	
            	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
            	// mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));
            	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
            	 
            	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
            	 mapDto.setOwnerProofNameTrns(getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
             }
              
              
             
            
	//}
	
	}
	
	
	finalDto.getPartyListPdf().add(mapDto);
	
}



}else{
	throw new Exception();
}
	
	
	map.put(partyCount, finalDto);
	System.out.println("party count------>"+partyCount);
	
	
	}
	
}catch(Exception e){
	map=null;
	e.printStackTrace();
}
	
	return map;
	
}
/**
 * getPartyIdsExchngPdf
 * for getting party ids of exchange deed for pdf from db.
 * @param String appId,int partyCount
 * @return String[]
 * @author ROOPAM
 */
public String[] getPartyIdsExchngPdf(String appId,int partyCount,int deedId) throws Exception {
	
	
	ArrayList list=commonBd.getPartyIdsExchngPdf(appId,partyCount,deedId);
	
	ArrayList rowList;
	String strArr[]=new String[list.size()];
	
	if(list!=null && list.size()>0){
		
		for(int i=0;i<list.size();i++){
			rowList=(ArrayList)list.get(i);
			
		
		String str=rowList.toString();
		str=str.substring(1, str.length()-1);
		//strArr=str.split(",");
		strArr[i]=str;
		
		}
		
		return strArr;
		
	}else{
		return null;
	}
	
	
	
}
/**
 * getPropIdsExchngPdf
 * for getting prop ids of exchange deed for pdf from db.
 * @param String appId,int partyCount
 * @return String[]
 * @author ROOPAM
 */
public String[] getPropIdsExchngPdf(String appId,int partyCount,int deedId) throws Exception {
	
	
	ArrayList list=commonBd.getPropIdsExchngPdf(appId,partyCount,deedId);
	ArrayList rowList;
	String strArr[]=new String[list.size()];
	
	if(list!=null && list.size()>0){
		
		for(int i=0;i<list.size();i++){
			rowList=(ArrayList)list.get(i);
			
		
		String str=rowList.toString();
		str=str.substring(1, str.length()-1);
		//strArr=str.split(",");
		strArr[i]=str;
		}
		
		return strArr;
		
	}else{
		return null;
	}
	
	
}
/**
 * getPartyDetailsForPDFTitleDeed
 * 
 * @param String regTxnId
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPartyDetailsForPDFTitleDeed(String regTxnId,int commonDeedFlag, String languageLocale) throws Exception {
	
	ArrayList partyList=new ArrayList();
	//RegCompletDTO dto;
	
	try{
	
			
			
			//BELOW CODE FOR GETTING LIST OF PARTY TXN IDS CORRESPONDING TO CURRENT PROPERTY ID IN LOOP
			
			String[] partyArr=getPartyTxnIdListTitleDeed(regTxnId);     
		
		if(partyArr!=null && partyArr.length>0){
			
			
			
			
			for(int j=0;j<partyArr.length;j++){
				
				ArrayList list;
				
				if(commonDeedFlag==0){
				list=commonBd.getPartyDetailsPdf(partyArr[j]);
				}else{
					list=commonBd.getPartyDetailsCommonDeedsPdf(partyArr[j]);
				}
				
				
				RegCommonDTO mapDto =new RegCommonDTO();
				
			if(list!=null && list.size()==1){
				
			//	for(int k=0;k<list.size();k++)
			//	{
				//ArrayList row_list=(ArrayList)list.get(k);
				String shareList=list.toString();
				shareList=shareList.substring(2, shareList.length()-2);
				
				String shareHolderArr[]=shareList.split(",");
				
				//code for insertion in map
		             
		             if(shareHolderArr[0].trim().length()==11){
		            	 shareHolderArr[0]="0"+shareHolderArr[0].trim();
		         		}
		         		else{
		         			shareHolderArr[0]=shareHolderArr[0].trim();
		         		}
		           
		             mapDto.setListAdoptedPartyTrns(shareHolderArr[1].trim());
		            
		             mapDto.setApplicantOrTransParty("Applicant");
		             mapDto.setPartyRoleTypeId(shareHolderArr[23].trim());
		             mapDto.setPartyTypeFlag("C");
		             
		            	 if(shareHolderArr[25].trim().equals("") || shareHolderArr[25].trim().equals("null"))
		            		mapDto.setRelationWithOwnerTrns("-");
		            	 else
		         	    mapDto.setRelationWithOwnerTrns(shareHolderArr[25].trim());
		            	 if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null")){
		            		mapDto.setShareOfPropTrns("Not Declared");
		            		mapDto.setHdnDeclareShareCheck("N");
		            	 }
		            	 else{
		         	    mapDto.setShareOfPropTrns(shareHolderArr[26].trim());
		         	   mapDto.setHdnDeclareShareCheck("Y");
		            	 }
		            	 mapDto.setSelectedCountry(shareHolderArr[8].trim());
		            	 mapDto.setSelectedCountryName(getCountryName(shareHolderArr[8].trim(),languageLocale));
		            	 mapDto.setSelectedState(shareHolderArr[9].trim());
		            	 mapDto.setSelectedStateName(getStateName(shareHolderArr[9].trim(),languageLocale));
		            	 mapDto.setSelectedDistrict(shareHolderArr[10].trim());
		            	 mapDto.setSelectedDistrictName(getDistrictName(shareHolderArr[10].trim(),languageLocale));
		            	 mapDto.setOrgaddressTrns(shareHolderArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 if(shareHolderArr[14].trim().equals("") || shareHolderArr[14].trim().equals("null"))
		            		 mapDto.setMobnoTrns("-"); 
		            	 else
		            	     mapDto.setMobnoTrns(shareHolderArr[14].trim());
		            	 if(shareHolderArr[13].trim().equals("") || shareHolderArr[13].trim().equals("null"))
		            		 mapDto.setPhnoTrns("-");
		            	 else
		            	     mapDto.setPhnoTrns(shareHolderArr[13].trim());
		            	
		            	 if(commonDeedFlag==0){
		            	     mapDto.setRoleName(commonBd.getRoleName(shareHolderArr[27].trim(),languageLocale));//pdf not in hindi
		            	     
		            	 }else{
		            		 mapDto.setRoleName(shareHolderArr[47].trim());
		            		 
		            	 }
		            	 mapDto.setPartyTypeTrns(shareHolderArr[27].trim());
		            	 
		            	 mapDto.setPartyTxnId(shareHolderArr[23].trim());
		            	 
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
		            	 //organization
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Organization");
		            	 mapDto.setOgrNameTrns(shareHolderArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setAuthPerNameTrns(shareHolderArr[19].trim());
		            	 mapDto.setIndAuthPersn(shareHolderArr[19].trim());
		            	/* mapDto.setOrgaddressTrns(shareHolderArr[11].trim());*/
		            	 mapDto.setFnameTrns("");
		           		 mapDto.setMnameTrns("");
		            	 mapDto.setLnameTrns("");
		            	// mapDto.setGendarTrns("-");
		            	// mapDto.setSelectedGender("-");
		            	 mapDto.setUserDOBTrns("");
		            	 mapDto.setAgeTrns("");
		               //	 mapDto.setFatherNameTrns("-");
		            	 mapDto.setMotherNameTrns("");
		           		 mapDto.setSpouseNameTrns("");
		              	 mapDto.setNationalityTrns("");
		            	 mapDto.setPostalCodeTrns("");
		           		 mapDto.setEmailIDTrns("");
		            	 mapDto.setSelectedPhotoId("");
		            	 mapDto.setSelectedPhotoIdName("-");
		            	 mapDto.setIdnoTrns("-");
		            	
		            	 //mapDto.setIndReligionTrns("");
		            	 mapDto.setIndCategoryTrns("");
		            	 mapDto.setIndDisabilityTrns("");
		            	 mapDto.setIndDisabilityDescTrns("");
		            	 mapDto.setIndPovertyLineTrns("");
		            	 mapDto.setIndMinorityTrns("");
		            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
		            	 
		            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
		            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
		            		 mapDto.setSelectedGender("Male");
		            	 else
		            		 mapDto.setSelectedGender("Female");
		            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
		            	 
		            	
		            	 
		            	 if(commonDeedFlag==0){
							    
		            		 if(shareHolderArr[47].trim()!=null && !shareHolderArr[47].trim().equalsIgnoreCase("") && !shareHolderArr[47].trim().equalsIgnoreCase("null")){
		            			mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
				              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
				              	}else{
				              		mapDto.setRelationshipTrns(0);
					              	mapDto.setRelationshipNameTrns("");
				              	}
			            	 
			            	 mapDto.setAuthPerCountryNameTrns(getCountryName(shareHolderArr[48].trim(),languageLocale));
			            	 mapDto.setAuthPerStatenameNameTrns(getStateName(shareHolderArr[49].trim(),languageLocale));
			            	 mapDto.setAuthPerDistrictNameTrns(getDistrictName(shareHolderArr[50].trim(),languageLocale));
			            	 mapDto.setAuthPerAddressTrns(shareHolderArr[51].trim());
		     				}else{
		
		     					if(shareHolderArr[48].trim()!=null && !shareHolderArr[48].trim().equalsIgnoreCase("") && !shareHolderArr[48].trim().equalsIgnoreCase("null")){
			            		 mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[48].trim()));
					              	mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[48].trim(),languageLocale));
					              	}else{
					              		mapDto.setRelationshipTrns(0);
						              	mapDto.setRelationshipNameTrns("");
					              	}
			            	 
				            	 mapDto.setAuthPerCountryNameTrns(getCountryName(shareHolderArr[50].trim(),languageLocale));
				            	 mapDto.setAuthPerStatenameNameTrns(getStateName(shareHolderArr[51].trim(),languageLocale));
				            	 mapDto.setAuthPerDistrictNameTrns(getDistrictName(shareHolderArr[52].trim(),languageLocale));
				            	 mapDto.setAuthPerAddressTrns(shareHolderArr[53].trim());
		     				}
		             }
		             
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
		            	 //individual
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Individual");
		            	 
		            	 mapDto.setFnameTrns(shareHolderArr[2].trim());
		            	 if(shareHolderArr[3].trim().equals("") || shareHolderArr[3].trim().equals("null"))
		            		 mapDto.setMnameTrns("");
		            	 else
		            	     mapDto.setMnameTrns(shareHolderArr[3].trim());
		            	 mapDto.setLnameTrns(shareHolderArr[4].trim());
		            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
		            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
		            		 mapDto.setSelectedGender("Male");
		            	 else
		            		 mapDto.setSelectedGender("Female");
		            	 /*if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setUserDOBTrns("-");
		            	 else
		            	     mapDto.setUserDOBTrns(convertDOBfromDb(shareHolderArr[6].trim()));*/
		            	 if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setAgeTrns("-");
		            	 else
		            	     mapDto.setAgeTrns(shareHolderArr[6].trim());
		            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
		            	 if(shareHolderArr[21].trim().equals("") || shareHolderArr[21].trim().equals("null"))
		            		 mapDto.setMotherNameTrns("-");
		            	 else
		            	     mapDto.setMotherNameTrns(shareHolderArr[21].trim());
		            	 
		            	 if(shareHolderArr[28].trim().equals("") || shareHolderArr[28].trim().equals("null"))
		            		 mapDto.setSpouseNameTrns("-");
		            	 else
		            	     mapDto.setSpouseNameTrns(shareHolderArr[28].trim());
		            	 mapDto.setNationalityTrns(shareHolderArr[7].trim());
		            	 
		            	 if(shareHolderArr[12].trim().equals("") || shareHolderArr[12].trim().equals("null"))
		            		 mapDto.setPostalCodeTrns("-");
		            	 else
		            	     mapDto.setPostalCodeTrns(shareHolderArr[12].trim());
		            	
		            	 if(shareHolderArr[15].trim().equals("") || shareHolderArr[15].trim().equals("null"))
		            		 mapDto.setEmailIDTrns("-");
		            	 else
		            	     mapDto.setEmailIDTrns(shareHolderArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setSelectedPhotoId(shareHolderArr[16].trim());
		            	 
		            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(shareHolderArr[16].trim(),languageLocale));               
		            	 mapDto.setIdnoTrns(shareHolderArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOgrNameTrns("-");
		            	 mapDto.setAuthPerNameTrns("-");
		            	 mapDto.setIndAuthPersn(shareHolderArr[2].trim()+" "+shareHolderArr[4].trim());
		            	 
		               	 
		               	 if(shareHolderArr[22].trim().equals("") || shareHolderArr[22].trim().equals("null"))
		               	 {
		               		mapDto.setIndCategoryTrns("-");
		               	 }
		               	 else
		               	 {
		            	    mapDto.setIndCategoryTrns(shareHolderArr[22].trim());
		               	 }
		               	 
		               	 mapDto.setSelectedCategoryName(getCategoryName(shareHolderArr[22].trim(),languageLocale));
		               	 
		               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
		               	 {
		               		mapDto.setIndDisabilityTrns("-");
		               	 }
		               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
		               	 {
		            	    mapDto.setIndDisabilityTrns("Yes");
		            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
		            	    	mapDto.setIndDisabilityDescTrns("-");
		            	    }else{
		            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	    }
		               	 }
		               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
		               	{
		            	    mapDto.setIndDisabilityTrns("No");
		               	}
		               	 
		               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
		               		mapDto.setIndPovertyLineTrns("-");}
		               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndPovertyLineTrns("Yes");
		            	     }
		               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndPovertyLineTrns("No");}
		               	
		               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
		               		mapDto.setIndMinorityTrns("-");}
		               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndMinorityTrns("Yes");
		            	     }
		               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndMinorityTrns("No");}
		               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
		               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
		               	
		               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
		              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
		              		mapDto.setIndScheduleAreaTrnsDisplay("No");
		              		
		              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
		              			mapDto.setPermissionNoTrns("-");
		              		}else{
		              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              		}
		              		
		              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
		              		if(shareHolderArr[33].trim().equalsIgnoreCase("") || shareHolderArr[33].trim().equalsIgnoreCase("null")){
		              		mapDto.setDocumentNameTrns("No");}
		              		else{
		              			mapDto.setDocumentNameTrns("Yes");}
		              		
		              	}else{
		              		mapDto.setIndScheduleAreaTrnsDisplay("Yes");
		              	}
		              	
		              	if(commonDeedFlag==0){
		            	    
		            	     mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[47].trim()));
		            	     mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[47].trim(),languageLocale));
		            	 }else{
		            		 
		            		 mapDto.setRelationshipTrns(Integer.parseInt(shareHolderArr[48].trim()));
		            		 mapDto.setRelationshipNameTrns(getRelationshipName(shareHolderArr[48].trim(),languageLocale));
		            	 }
		             }
		             if(commonDeedFlag==1){
		            	 mapDto.setExecutantClaimantTrns(Integer.parseInt(shareHolderArr[49].trim()));
	            	   mapDto.setClaimantFlag(Integer.parseInt(shareHolderArr[49].trim()));
	            	   
	            	   if(mapDto.getClaimantFlag()==RegInitConstant.CLAIMANT_FLAG_2 || mapDto.getClaimantFlag()==RegInitConstant.CLAIMANT_FLAG_4){
	            		   mapDto.setPoaHolderFlag(RegInitConstant.POA_HOLDER_FLAG);
	            	   }
	            	   else{
	            		   mapDto.setPoaHolderFlag(0);
	            	   }
	            	 }
		             //uploads
		             if(shareHolderArr[37].trim().equalsIgnoreCase("") || shareHolderArr[37].trim().equalsIgnoreCase("null")){
		              		mapDto.setU2DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU2DocumentNameTrns("Yes");}
		             
		             if(shareHolderArr[38].trim().equalsIgnoreCase("") || shareHolderArr[38].trim().equalsIgnoreCase("null")){
		              		mapDto.setU3DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU3DocumentNameTrns("Yes");}
		              	if(shareHolderArr[39].trim().equalsIgnoreCase("") || shareHolderArr[39].trim().equalsIgnoreCase("null")){
		              		mapDto.setU4DocumentNameTrns("No");
		              		mapDto.setU7DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU4DocumentNameTrns("Yes");
		              			mapDto.setU7DocumentNameTrns("Yes");	
		              		}
		              	if(shareHolderArr[40].trim().equalsIgnoreCase("") || shareHolderArr[40].trim().equalsIgnoreCase("null")){
		              		mapDto.setU5DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU5DocumentNameTrns("Yes");}
		              	if(shareHolderArr[41].trim().equalsIgnoreCase("") || shareHolderArr[41].trim().equalsIgnoreCase("null")){
		              		mapDto.setU6DocumentNameTrns("No");
		              		mapDto.setU9DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU6DocumentNameTrns("Yes");
		              			mapDto.setU9DocumentNameTrns("Yes");	
		              		}
		              	if(shareHolderArr[42].trim().equalsIgnoreCase("") || shareHolderArr[42].trim().equalsIgnoreCase("null")){
		              		mapDto.setU8DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU8DocumentNameTrns("Yes");}
		              	if(shareHolderArr[43].trim().equalsIgnoreCase("") || shareHolderArr[43].trim().equalsIgnoreCase("null")){
		              		mapDto.setU10DocumentNameTrns("No");
		              		mapDto.setU11DocumentNameTrns("No");
		              		
		              	}
		              		else{
		              			mapDto.setU10DocumentNameTrns("Yes");
		              			mapDto.setU11DocumentNameTrns("Yes");	
		              		}
		              	//mapDto.setU2DocumentNameTrns("PHOTO PROOF");
		              	//mapDto.setU2FilePathTrns(shareHolderArr[37].trim());         //37 PHOTO_PROOF_PATH
		              	//mapDto.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");
		              	//mapDto.setU3FilePathTrns(shareHolderArr[38].trim());			//38 NOT_AFFD_EXEC_PATH
		              	//mapDto.setU4DocumentNameTrns("EXECUTANT PHOT0");
		              	//mapDto.setU4FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
		              	//mapDto.setU7DocumentNameTrns("EXECUTANT PHOT0");
		              	//mapDto.setU7FilePathTrns(shareHolderArr[39].trim());			//39 EXEC_PHOTO_PATH
		              	//mapDto.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY");
		              	//mapDto.setU5FilePathTrns(shareHolderArr[40].trim());			//40 NOT_AFFD_ATTR_PATH
		              	//mapDto.setU6DocumentNameTrns("ATTORNEY PHOTO");
		              	//mapDto.setU6FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
		              	//mapDto.setU9DocumentNameTrns("ATTORNEY PHOTO");
		              	//mapDto.setU9FilePathTrns(shareHolderArr[41].trim());			//41 ATTR_PHOTO_PATH
		              	//mapDto.setU8DocumentNameTrns("CLAIMANT PHOTO");
		              	//mapDto.setU8FilePathTrns(shareHolderArr[42].trim());			//42 CLAIMNT_PHOTO_PATH
		              	//mapDto.setU10DocumentNameTrns("PAN OR FORM 60/61");
		              	//mapDto.setU10FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
		              	//mapDto.setU11DocumentNameTrns("PAN OR FORM 60/61");
		              	//mapDto.setU11FilePathTrns(shareHolderArr[43].trim());		//43 PAN_FORM_60_PATH
		              	
		              	mapDto.setSrOfficeNameTrns(shareHolderArr[44].trim());
		              	mapDto.setPoaRegNoTrns(shareHolderArr[45].trim());
		              	if(shareHolderArr[46].trim()==null || shareHolderArr[46].trim().equalsIgnoreCase("") || shareHolderArr[46].trim().equalsIgnoreCase("null")){
		              		
		              		mapDto.setDatePoaRegTrns("");
		              		}
		              		else{
		              			mapDto.setDatePoaRegTrns(convertDOB2(shareHolderArr[46].trim()));
		              		}
		              	
		             
		             //end of uploads
		             int roleId=Integer.parseInt(shareHolderArr[27].trim());
		             
		             String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
		             
		             if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
		            		 mapDto.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG)
		             {
		             	
		             	String ownerId=shareHolderArr[29].trim();
		             	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
		             	String ownerDetls=ownerList.toString();
		             	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
		             	String ownerDetlsArr[]=ownerDetls.split(",");
		             	
		            	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
		            	 {
		              mapDto.setOwnerOgrNameTrns("-");
		              mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
		              }
		              else
		              {
		              mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
		            	}
		            	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f"))
		            	 mapDto.setOwnerGendarTrns("Female");
		            	 else
		                 mapDto.setOwnerGendarTrns("Male");	 
		            	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
		            	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
		            	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
		                   		mapDto.setOwnerEmailIDTrns("-");
		                   	 else
		                	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));
		            	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
		            	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
		            	 mapDto.setOwnerProofNameTrns(getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
		             }
		              
		              
		             
		            
			//}
			
			}
			
			
			partyList.add(mapDto);
			
		}
		
		
		
		}else{
			throw new Exception();
		}
		
	
}catch(Exception e){
	e.printStackTrace();
}
	
	return partyList;
	
}
/**
 * getPropertyDetailsForPDFTitleDeed
 * 
 * @param String regTxnId
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPropertyDetailsForPDFTitleDeed(String regTxnId) throws Exception {
	
	ArrayList propFinalList=new ArrayList();
	RegCompletDTO dto;
	
	try{
	ArrayList propertyIdList=getPropertyIdMarketVal(regTxnId);               
	String[] propIdArr=new String[propertyIdList.size()];
	for(int m=0;m<propertyIdList.size();m++){
		
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)propertyIdList.get(m);
		String propIds=row_list.toString();
		propIds=propIds.substring(1, propIds.length()-1);
		String propId[]=propIds.split(",");
	    propIdArr[m]=propId[0].trim();
	
	}
	
	if(propIdArr!=null && propIdArr.length>0){
		
		for(int i=0;i<propIdArr.length;i++){
			dto=new RegCompletDTO();
			dto.setPartyListPdf(new ArrayList());
			
			if(propIdArr[i].trim().length()==15){
				dto.setSelectedPropId("0"+propIdArr[i].trim());
			}else{
				dto.setSelectedPropId(propIdArr[i].trim());
			}
			//valuation id
			//String valuationId=commonBd.getPropValuationId(regTxnId,propIdArr[i].trim());
			
			
			//details corresponding to valuation id
			CustomArrayList propList = new CustomArrayList();
			
			
			propList=getPropDetlsForDisplay(propIdArr[i].trim());
			//String propDetails=propList.toString().substring(2, (propList.toString().length()-2));
			String propDetails=propList.toString();
			String propDetsArray[]=propDetails.split(CommonConstant.CUSTOM_ARRAYLIST_SEPARATOR);
		 	
			
			dto.setPropertyTypeName(propDetsArray[14].trim());
			dto.setDistName(propDetsArray[1].trim());
			dto.setTehsilName(propDetsArray[3].trim());
			dto.setAreaTypeName(propDetsArray[5].trim());
			if(propDetsArray[15].trim()!=null && !propDetsArray[15].trim().equalsIgnoreCase("") && !propDetsArray[15].trim().equalsIgnoreCase("null")){
			dto.setTotalSqMeter(Double.parseDouble(propDetsArray[15].trim()));
			}else{
				dto.setTotalSqMeter(0);
			}
			dto.setTotalSqMeterDisplay(getCurrencyFormat(dto.getTotalSqMeter()));
			if(dto.getPropertyTypeName().equalsIgnoreCase("plot") ||
					dto.getPropertyTypeName().equalsIgnoreCase("building"))
			{
				
				dto.setUnit("sq mtr");
			}
			else
			{
				
				String unit=propDetsArray[16].trim();
				if(unit.equalsIgnoreCase("2"))
				{
					dto.setUnit("SQM");
				}
				else if(unit.equalsIgnoreCase("3"))
				{
					dto.setUnit("HECTARE");
				}
				
			}
			//dto.setUnit("");
			//dto.setMunicipalDutyName("");
			dto.setMunicipalBodyName(propDetsArray[12].trim());
			String wardstatus=propDetsArray[8].trim();//////
			
			if (wardstatus.equalsIgnoreCase("1"))
			{
				wardstatus="Ward";
			}
			else
			{
				wardstatus="Patwari/Halka";
			}
			dto.setPatwariStatus(wardstatus);
			dto.setWardpatwarName(propDetsArray[7].trim());
			dto.setMohallaName(propDetsArray[10].trim());
			//no. of floors.
			
			if(propDetsArray[17].trim().equals("") || propDetsArray[17].trim().equals("null"))
			dto.setVikasId("-");
			else
			dto.setVikasId(propDetsArray[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setRicircle(propDetsArray[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[19].trim().equals("") || propDetsArray[19].trim().equals("null"))
			dto.setLayoutdet("-");
			else
			dto.setLayoutdet(propDetsArray[19].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			if(propDetsArray[20].trim().equals("") || propDetsArray[20].trim().equals("null"))
			dto.setSheetnum("-");	
			else
			dto.setSheetnum(propDetsArray[20].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[21].trim().equals("") || propDetsArray[21].trim().equals("null"))
			dto.setPlotnum("-");
			else
			dto.setPlotnum(propDetsArray[21].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setPropAddress(propDetsArray[22].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setNorth(propDetsArray[23].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setSouth(propDetsArray[24].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setEast(propDetsArray[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			dto.setWest(propDetsArray[26].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			if(propDetsArray[27].trim().equals("") || propDetsArray[27].trim().equals("null"))
				dto.setPropLandmark("-");
				else
				dto.setPropLandmark(propDetsArray[27].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
			
			/*if(	!dto.getPropertyTypeName().equalsIgnoreCase("building"))
			{
				
				dto.setSelectedSubClauseList(getSubClauseListNonBuilding(valuationId));
				
			}*/
			//BELOW CODE FOR UPLOADS
			//28
			if(propDetsArray[28].trim().equalsIgnoreCase("") || propDetsArray[28].trim().equalsIgnoreCase("null")){
				dto.setPropImage1DocumentName("No");}
          		else{
          			dto.setPropImage1DocumentName("Yes");}
			if(propDetsArray[29].trim().equalsIgnoreCase("") || propDetsArray[29].trim().equalsIgnoreCase("null")){
				dto.setPropImage2DocumentName("No");}
          		else{
          			dto.setPropImage2DocumentName("Yes");}
			if(propDetsArray[30].trim().equalsIgnoreCase("") || propDetsArray[30].trim().equalsIgnoreCase("null")){
				dto.setPropImage3DocumentName("No");}
          		else{
          			dto.setPropImage3DocumentName("Yes");}
			if(propDetsArray[31].trim().equalsIgnoreCase("") || propDetsArray[31].trim().equalsIgnoreCase("null")){
				dto.setPropMapDocumentName("No");}
          		else{
          			dto.setPropMapDocumentName("Yes");}
			if(	dto.getPropertyTypeName().equalsIgnoreCase("AGRI LAND"))
			{
			if(propDetsArray[32].trim().equalsIgnoreCase("") || propDetsArray[32].trim().equalsIgnoreCase("null")){
				dto.setPropRinDocumentName("No");}
          		else{
          			dto.setPropRinDocumentName("Yes");}
			if(propDetsArray[33].trim().equalsIgnoreCase("") || propDetsArray[33].trim().equalsIgnoreCase("null")){
				dto.setPropKhasraDocumentName("No");}
          		else{
          			dto.setPropKhasraDocumentName("Yes");}
			}


			
			//FOR GETTING PROPERTY KHASRA DETAILS
			String otherPropDetsArray[]=getPropKhasraDetlsForDisplay(propIdArr[i].trim());
			
			
			//following code for inserting khasra detls into map
			if(otherPropDetsArray!=null){
			String[] khasraNum=otherPropDetsArray[0].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] khasraArea=otherPropDetsArray[1].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] lagaan=otherPropDetsArray[2].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			String[] rinPustika=otherPropDetsArray[3].trim().split(RegInitConstant.SPLIT_CONSTANT_KHASRA);
			
						
			int length=khasraNum.length;
			CommonDTO objDto;
			
			for(int j=0;j<length;j++){
				objDto=new CommonDTO();
				if(khasraNum[j].equalsIgnoreCase("null"))
				{
					objDto.setKhasraNum("-");
					objDto.setKhasraArea("-");
					objDto.setLagaan("-");
					objDto.setRinPustika("-");
				}else{
				objDto.setKhasraNum(khasraNum[j]);
				objDto.setKhasraArea(khasraArea[j]);
				objDto.setLagaan(lagaan[j]);
				objDto.setRinPustika(rinPustika[j]);
				}
				
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			}else{
				CommonDTO objDto=new CommonDTO();
				objDto.setKhasraNum("-");
				objDto.setKhasraArea("-");
				objDto.setLagaan("-");
				objDto.setRinPustika("-");
				dto.getKhasraDetlsDisplay().add(objDto);
			}
			HashMap buildingMap=new HashMap();
			if(propDetsArray[14].trim().equalsIgnoreCase("Building")){
			
				
				//following code for getting building floor details
				ArrayList buildingList=getBuildingFloorDetailsTitleDeed(propIdArr[i].trim());
				if(buildingList.size()>0){
					
					dto.setFloorCount(buildingList.size());
					
					ArrayList row_list=new ArrayList();
					String[] strArray;
					
					for(int j=0;j<buildingList.size();j++){
						RegCompletDTO dto1=new RegCompletDTO();
						row_list=(ArrayList)buildingList.get(j);
						String str=row_list.toString();
						str=str.substring(1,str.length()-1);
						strArray=str.split(",");
						dto1.setUsageBuilding(strArray[7]);
						dto1.setCeilingType(strArray[8]);
						dto1.setTypeFloorDesc(strArray[9]);
						//dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
						dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
						dto1.setTotalSqMeterDisplay(getCurrencyFormat(dto1.getTotalSqMeter()));
						dto1.setConstructedArea(strArray[6]);
						
						//dto1.setSelectedSubClauseList(getSubClauseListBuilding(valuationId,strArray[0]));
						
						dto.getMapBuilding().put(strArray[0], dto1);
						
						
					}
					
					
					
				}
				
				
				
				
			}else{
				dto.setFloorCount(0);
			}
			
			propFinalList.add(dto);
		
	}
	
	
	
	}else{
		//propFinalList=null;
		//throw new Exception();
	}
}catch(Exception e){
	//propFinalList=null;
	e.printStackTrace();
}
	
	return propFinalList;
	
}
/**
 * getPartyTxnIdListTitleDeed
 * for getting party txn id list corresponding to reg id given
 * @param String
 * @return String[]
 * @author ROOPAM
 */
public String[] getPartyTxnIdListTitleDeed(String appId) throws Exception {
	return commonBd.getPartyTxnIdListTitleDeed(appId);
}
/**
 * getConsidAmtSysMV
 * for getting consideration amount and system calculated MV from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getConsidAmtSysMV(String valId) throws Exception {
	return commonBd.getConsidAmtSysMV(valId);

}
/**
 * getConsidAmtTitle
 * for getting consideration amount from db for Title deed.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getConsidAmtTitle(String regId) throws Exception {
	return commonBd.getConsidAmtTitle(regId);

}
/**
 * getBuildingFloorDetailsTitleDeed
 * for getting building floor details for title deed from db
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getBuildingFloorDetailsTitleDeed(String propId) throws Exception {
	return commonBd.getBuildingFloorDetailsTitleDeed(propId);	
}
/**
 * getPaymentDetlsForDisplay
 * for getting payment matrix details from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPaymentDetlsForDisplay(String regId) throws Exception {
	return commonBd.getPaymentDetlsForDisplay(regId);

}
/**
 * getPartyDetsForViewConfirm
 * for getting APPLICANT PARTY details from db.
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void getPartyDetsForViewConfirmModify(RegCommonForm form) throws Exception {
	
	//HashMap map=form.getMapTransactingPartiesDisp();
	
	
	RegCommonDTO mapDto;
	DMSUtility dmsUtil=new DMSUtility();
	
	Collection mapCollection=form.getMapTransactingPartiesDisp().values();
    Object[] l1=mapCollection.toArray();
	int deedId=form.getDeedID();
    for(int i=0;i<l1.length;i++)
	{
	
	mapDto =(RegCommonDTO)l1[i];
	
    form.setListAdoptedPartyTrns(mapDto.getListAdoptedPartyTrns());
    //mapDto.setListAdoptedPartyNameTrns(listArr[1].trim());
    
    mapDto.setApplicantOrTransParty("Applicant");
    form.setPartyTxnId(mapDto.getPartyTxnId());
    
   if(mapDto.getRelationWithOwnerTrns()!=null){
  	 if(mapDto.getRelationWithOwnerTrns().equalsIgnoreCase("-")){
  		form.setRelationWithOwnerTrns("");
  	 }
  	 else{
  		form.setRelationWithOwnerTrns(mapDto.getRelationWithOwnerTrns());
  	 }
   }
  	 
  	 if(mapDto.getShareOfPropTrns().equalsIgnoreCase("-")){
  		form.setShareOfPropTrns("");
  		form.setHdnDeclareShareCheck("N");
  	 }
  	 else{
	    form.setShareOfPropTrns(mapDto.getShareOfPropTrns());
	    form.setHdnDeclareShareCheck("Y");
  	 }
    
    form.setPoaHolderFlag(mapDto.getPoaHolderFlag());
    
    if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
   	 //organization
    	
   	 form.setOgrNameTrns(mapDto.getOgrNameTrns());
   	 form.setAuthPerNameTrns( mapDto.getAuthPerNameTrns());
   	 
   	form.setFnameTrns("");
   	form.setMnameTrns("");
   	form.setLnameTrns("");
   	form.setGendarTrns("");
   	//form.setSelectedGender("");
   	form.setUserDOBTrns("");
   	form.setAgeTrns("");
   	form.setFatherNameTrns("");
   	form.setMotherNameTrns("");
   	form.setSpouseNameTrns("");
   	form.setNationalityTrns("");
   	form.setPostalCodeTrns("");
   	form.setEmailIDTrns("");
   	//form.setSelectedPhotoId("");
   	//form.setSelectedPhotoIdName("");
   	form.setListIDTrns("");
   	form.setIdnoTrns("");
   	 //mapDto.setSelectedCategoryName("");
   	form.setIndCategoryTrns("");
   	form.setIndDisabilityTrns("");
   	form.setIndDisabilityDescTrns("");
   	//form.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
   	form.setIndMinorityTrns("");
   	form.setIndPovertyLineTrns("");
   	
   	form.setOrgaddressTrns(mapDto.getOrgaddressTrns());
   	//mapDto.setSelectedCountryName();
     //mapDto.setSelectedStateName();
   	//mapDto.setSelectedDistrictName();
   	form.setCountryTrns("1");
    form.setStatenameTrns("1");
  	form.setDistrictTrns(mapDto.getSelectedDistrict());
  	if(mapDto.getMobnoTrns().equals("-"))
 		 form.setMobnoTrns(""); 
 	else
 	    form.setMobnoTrns( mapDto.getMobnoTrns());
 	if(mapDto.getPhnoTrns().equals("-") )
 		 form.setPhnoTrns("");
 	else
 	    form.setPhnoTrns( mapDto.getPhnoTrns());
 	
 	form.setAuthPerGendarTrns(mapDto.getAuthPerGendarTrns()) ;
 	form.setAuthPerFatherNameTrns( mapDto.getAuthPerFatherNameTrns());
 	form.setAuthPerRelationshipTrns(mapDto.getRelationshipTrns());
 	form.setAuthPerCountryTrns(mapDto.getAuthPerCountryTrns());
 	form.setAuthPerStatenameTrns(mapDto.getAuthPerStatenameTrns());
 	form.setAuthPerDistrictTrns(mapDto.getAuthPerDistrictTrns());
 	form.setAuthPerAddressTrns(mapDto.getAuthPerAddressTrns());
    }
    if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
   	 //individual
   	 form.setFnameTrns( mapDto.getFnameTrns());
   	 if(mapDto.getMnameTrns().equals("-"))
   		 form.setMnameTrns("");
   	 else
   	     form.setMnameTrns( mapDto.getMnameTrns());
   	 form.setLnameTrns( mapDto.getLnameTrns());
   	form.setGendarTrns(mapDto.getGendarTrns()) ;
   	 /*if(mapDto.getGendarTrns().equalsIgnoreCase("m"))
   		 mapDto.setSelectedGender("Male");
   	 else
   		 mapDto.setSelectedGender("Female");
   	 */
   	 
   	 if(mapDto.getAgeTrns().equals("-")){
   		form.setAgeTrns("");
   		/*form.setUserDayTrns("");
   		form.setUserMonthTrns("");
   		form.setUserYearTrns("");*/
   		 
   	 }
   	 else{
   	    // mapDto.setUserDOBTrns(convertDOB(listArr[6].trim()));   
   		form.setAgeTrns(mapDto.getAgeTrns());
   	     /*String[] date=parseStringDOBfromDB(mapDto.getUserDOBTrns());
   	     System.out.println("day-->"+date[0]+"\nmonth-->"+date[1]);
   	     form.setUserDayTrns(date[0]);
   	     form.setUserMonthTrns(date[1]);
   	     form.setUserYearTrns(date[2]);*/
   	     
   	     
   	 }
   	 
   	 
   	 
   	 form.setFatherNameTrns( mapDto.getFatherNameTrns());
   	 if(mapDto.getMotherNameTrns().equals("-"))
   		 form.setMotherNameTrns("");
   	 else
   	     form.setMotherNameTrns( mapDto.getMotherNameTrns());
   	 if(mapDto.getSpouseNameTrns().equals("-"))
   		 form.setSpouseNameTrns("");
   	 else
   	     form.setSpouseNameTrns(mapDto.getSpouseNameTrns());
   	 
   	 form.setNationalityTrns( mapDto.getNationalityTrns());
   	 
   	 if(mapDto.getPostalCodeTrns().equals("-") )
   		 form.setPostalCodeTrns("");
   	 else
   	    form.setPostalCodeTrns( mapDto.getPostalCodeTrns());
   	 
   	 
   	 
   	 if(mapDto.getEmailIDTrns().equals("-"))
   		 form.setEmailIDTrns("");
   	 else
   	    form.setEmailIDTrns( mapDto.getEmailIDTrns());
   	
   	 form.setListIDTrns(mapDto.getListIDTrns());
   	 //mapDto.setSelectedPhotoIdName(getPhotoIdProofName(listArr[16].trim()));               
   	 if(mapDto.getIdnoTrns().equals("-") )
   		 form.setIdnoTrns("");
   	 else
   	    form.setIdnoTrns( mapDto.getIdnoTrns());
     form.setOgrNameTrns("");
   	 form.setAuthPerNameTrns("");
   	 
      	 
      	 
      	 if(mapDto.getIndCategoryTrns().equals("-") )
      	form.setIndCategoryTrns("");
      	 else
   	   form.setIndCategoryTrns(mapDto.getIndCategoryTrns());
      	 
      	 
      	// mapDto.setSelectedCategoryName(getCategoryName(listArr[22].trim()));
      	 if(mapDto.getIndDisabilityTrns().equals("-"))
      	 form.setIndDisabilityTrns("");
      	 else if(mapDto.getIndDisabilityTrns().equalsIgnoreCase(RegInitConstant.YES_ENGLISH) ||
      			mapDto.getIndDisabilityTrns().equalsIgnoreCase(RegInitConstant.YES_HINDI))
   	     form.setIndDisabilityTrns("Y");
      	 else if(mapDto.getIndDisabilityTrns().equalsIgnoreCase(RegInitConstant.NO_ENGLISH) ||
      			mapDto.getIndDisabilityTrns().equalsIgnoreCase(RegInitConstant.NO_HINDI))
       	 form.setIndDisabilityTrns("N");
      	        
      	if( form.getIndDisabilityTrns().equalsIgnoreCase("Y")){
      		
      			if(mapDto.getIndDisabilityDescTrns().equals("") || mapDto.getIndDisabilityDescTrns().equals("null")||
      				mapDto.getIndDisabilityDescTrns().equals("-")){
      		form.setIndDisabilityDescTrns("");}
      	 else{
   	   form.setIndDisabilityDescTrns( mapDto.getIndDisabilityDescTrns());}
    }else{
    	form.setIndDisabilityDescTrns("");
    }
      	 
      	 
      	//form.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
      	
      	//mapDto.setSelectedOccupationName(getOccupationName(listArr[33].trim()));
      //33 occupation id
      	//34 occupation id
      	//35 collector permission no.
      	//36 collector certificate
      	form.setOccupationIdTrns(mapDto.getOccupationIdTrns());
      	
      	if(mapDto.getIndMinorityTrns().equalsIgnoreCase(RegInitConstant.NO_ENGLISH) ||
      			mapDto.getIndMinorityTrns().equalsIgnoreCase(RegInitConstant.NO_HINDI)){
      	form.setIndMinorityTrns("N");}
      	else{
      	form.setIndMinorityTrns("Y");}	
      	
      	if(mapDto.getIndPovertyLineTrns().equalsIgnoreCase(RegInitConstant.NO_ENGLISH) ||
      			mapDto.getIndPovertyLineTrns().equalsIgnoreCase(RegInitConstant.NO_HINDI)){
        form.setIndPovertyLineTrns("N");}
        else{
        form.setIndPovertyLineTrns("Y");}	
      	
      //form.setIndScheduleAreaTrns(mapDto.getIndScheduleAreaTrns());
      	if(mapDto.getIndScheduleAreaTrns().equalsIgnoreCase("N")){
      		
      		//form.setIndScheduleAreaTrns("N");
      		if(mapDto.getPermissionNoTrns().equalsIgnoreCase("-")){
      			form.setPermissionNoTrns("");
      		}else{
      			form.setPermissionNoTrns( mapDto.getPermissionNoTrns());
      		}
      		
      		form.setDocumentNameTrns("COLLECTOR'S CERTIFICATE");               //CERTIFICATE  40
       		form.setFilePathTrns(mapDto.getFilePathTrns());
       		form.setDocContentsTrns(mapDto.getDocContentsTrns());
       		form.setPartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
       		form.setUploadTypeTrns(mapDto.getUploadTypeTrns());
     
      	}else{
      		form.setIndScheduleAreaTrns("Y");
      	}
      	//39 st schedule area
      	 form.setIndaddressTrns(mapDto.getOrgaddressTrns());
       	//mapDto.setSelectedCountryName();
         //mapDto.setSelectedStateName();
       	//mapDto.setSelectedDistrictName();
      	form.setIndcountryTrns("1");
        form.setIndstatenameTrns("1");
      	form.setInddistrictTrns(mapDto.getSelectedDistrict());
      	if(mapDto.getMobnoTrns().equals("-"))
     		 form.setIndmobnoTrns(""); 
     	else
     	    form.setIndmobnoTrns( mapDto.getMobnoTrns());
     	if(mapDto.getPhnoTrns().equals("-") )
     		 form.setIndphnoTrns("");
     	else
     	    form.setIndphnoTrns( mapDto.getPhnoTrns());
     	
     	
     	form.setRelationshipTrns(mapDto.getRelationshipTrns());
     	
     	
    }
    
    
    
    
    if(mapDto.getRoleName().equals("-") )
  		 form.setRoleName("");
  	 else
  	   form.setRoleName( mapDto.getRoleName());
  
  
  	
  form.setPartyTypeTrns(mapDto.getPartyTypeTrns());
  	
  	int roleId=Integer.parseInt(form.getPartyTypeTrns());
  	//int claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(roleId)));
  	
  	String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
		int claimantFlag=Integer.parseInt(claimantArr[0].trim());
  	
  	form.setU2DocumentNameTrns("PHOTO PROOF");             //PHOTO PROOF    41
	form.setU2FilePathTrns( mapDto.getU2FilePathTrns());
	form.setU2DocContentsTrns(mapDto.getU2DocContentsTrns());
	form.setU2PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
		form.setU2UploadTypeTrns(mapDto.getU2UploadTypeTrns());
	System.out.println("path from db---------->"+mapDto.getU2FilePathTrns());
		     	
	
	/*if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE && deedId!=RegInitConstant.DEED_LEASE_NPV &&
				deedId!=RegInitConstant.DEED_TRUST && deedId!=RegInitConstant.DEED_ADOPTION &&
				deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA && deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
				deedId!=RegInitConstant.DEED_POA && deedId!=RegInitConstant.DEED_SETTLEMENT_NPV &&
				deedId!=RegInitConstant.DEED_WILL_NPV && deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
				deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV && deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
				deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV && deedId!=RegInitConstant.DEED_PARTITION_NPV
				&& deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV && deedId!=RegInitConstant.DEED_COMPOSITION_NPV
				&& deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV && deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV
				&& deedId!=RegInitConstant.DEED_TRANSFER_NPV && deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV)*/  //add deed id check
	
	if(form.getDeedTypeFlag()==0)
		{
	
	
  //BELOW CODE FOR EXECUTANT
		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	{
			form.setU3DocumentNameTrns("NOTARIZED AFFIDAVIT OF EXECUTANT");         //NOT AFFD EXEC   42
   		form.setU3FilePathTrns(mapDto.getU3FilePathTrns());
   		form.setU3DocContentsTrns(mapDto.getU3DocContentsTrns());
   		form.setU3PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU3UploadTypeTrns(mapDto.getU3UploadTypeTrns());
   		
   		
   		form.setU4DocumentNameTrns("EXECUTANT PHOTO");         //EXEC PHOTO       43
   		form.setU4FilePathTrns(mapDto.getU4FilePathTrns());
   		form.setU4DocContentsTrns(mapDto.getU4DocContentsTrns());
   		form.setU4PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU4UploadTypeTrns(mapDto.getU4UploadTypeTrns());
   		
   		
   		if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
   			form.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
   	   		form.setU10FilePathTrns(mapDto.getU10FilePathTrns());
   	   		form.setU10DocContentsTrns(mapDto.getU10DocContentsTrns());
   	   		form.setU10PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   	   		form.setU10UploadTypeTrns(mapDto.getU10UploadTypeTrns());	
   		}else{
   		if(!form.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
   		form.setU10DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
   		form.setU10FilePathTrns(mapDto.getU10FilePathTrns());
   		form.setU10DocContentsTrns(mapDto.getU10DocContentsTrns());
   		form.setU10PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU10UploadTypeTrns(mapDto.getU10UploadTypeTrns());
   		}
   		}
   	}
	//BELOW CODE FOR EXECUTANT POA HOLDER
	if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	{
		form.setU5DocumentNameTrns("NOTARIZED AFFIDAVIT OF ATTORNEY"); 			//NOT AFFD ATTR		44
   		form.setU5FilePathTrns(mapDto.getU5FilePathTrns());
   		form.setU5DocContentsTrns(mapDto.getU5DocContentsTrns());
   		form.setU5PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU5UploadTypeTrns(mapDto.getU5UploadTypeTrns());
   		
   		form.setU6DocumentNameTrns("ATTORNEY PHOTO");			//ATTR PHOTO		45
   		form.setU6FilePathTrns(mapDto.getU6FilePathTrns());
   		form.setU6DocContentsTrns(mapDto.getU6DocContentsTrns());
   		form.setU6PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU6UploadTypeTrns(mapDto.getU6UploadTypeTrns());
   		
   		form.setU7DocumentNameTrns("EXECUTANT PHOTO");			//EXEC PHOTO		43
   		form.setU7FilePathTrns(mapDto.getU7FilePathTrns());
   		form.setU7DocContentsTrns(mapDto.getU7DocContentsTrns());
   		form.setU7PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU7UploadTypeTrns(mapDto.getU7UploadTypeTrns());
   		
   		form.setSrOfficeNameTrns(mapDto.getSrOfficeNameTrns());
   		form.setPoaRegNoTrns(mapDto.getPoaRegNoTrns());
   		form.setDatePoaRegTrns(mapDto.getDatePoaRegTrns());
	}
		
	//BELOW CODE FOR CLAIMANT
	if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
	{
		form.setU8DocumentNameTrns("CLAIMANT PHOTO");			//CLAIMANT PHOTO	46
   		form.setU8FilePathTrns(mapDto.getU8FilePathTrns());
   		form.setU8DocContentsTrns(mapDto.getU8DocContentsTrns());
   		form.setU8PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU8UploadTypeTrns(mapDto.getU8UploadTypeTrns());
   		
   		if(mapDto.getListAdoptedPartyTrns().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
   			form.setU11DocumentNameTrns("PAN OR FORM 60/61");       //PAN          47
   	   		form.setU11FilePathTrns(mapDto.getU11FilePathTrns());
   	   		form.setU11DocContentsTrns(mapDto.getU11DocContentsTrns());
   	   		form.setU11PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   	   		form.setU11UploadTypeTrns(mapDto.getU11UploadTypeTrns());	
   		}else{
   		if(!form.getListIDTrns().equalsIgnoreCase(RegInitConstant.PAN_CONSTANT)){
   		form.setU11DocumentNameTrns("PAN OR FORM 60/61");		//PAN				47
   		form.setU11FilePathTrns(mapDto.getU11FilePathTrns());
   		form.setU11DocContentsTrns(mapDto.getU11DocContentsTrns());
   		form.setU11PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU11UploadTypeTrns(mapDto.getU11UploadTypeTrns());
   		}
   		}
   	}
		
	//BELOW CODE FOR CLAIMANT POA HOLDER
	if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
	{
		
		form.setU9DocumentNameTrns("ATTORNEY PHOTO");				//ATTR PHOTO		45
		form.setU9FilePathTrns(mapDto.getU9FilePathTrns());
		form.setU9DocContentsTrns(mapDto.getU9DocContentsTrns());
		form.setU9PartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
   		form.setU9UploadTypeTrns(mapDto.getU9UploadTypeTrns());
		
	}
  
		}
  	
    if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG ||
    		form.getPoaHolderFlag()==RegInitConstant.POA_HOLDER_FLAG)
    {
    	
    	form.setOwnerIdTrns(mapDto.getOwnerIdTrns());
    	
    	/*String ownerId=listArr[32].trim();
    	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
    	String ownerDetls=ownerList.toString();
    	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
    	String ownerDetlsArr[]=ownerDetls.split(",");*/
    	
   	 if(mapDto.getOwnerOgrNameTrns().equals("-"))
   	 {
     form.setOwnerOgrNameTrns("");
     
     }
     else
     {
     form.setOwnerOgrNameTrns(mapDto.getOwnerOgrNameTrns());
     
   	}
   	form.setOwnerNameTrns(mapDto.getOwnerNameTrns());
   	 if(mapDto.getOwnerGendarTrns().equalsIgnoreCase(RegInitConstant.FEMALE_ENGLISH) ||
   			mapDto.getOwnerGendarTrns().equalsIgnoreCase(RegInitConstant.FEMALE_HINDI)){
   	 form.setOwnerGendarTrns("F");
   	 }
   	 else{
        form.setOwnerGendarTrns("M");
   	 }
   	 form.setOwnerNationalityTrns( mapDto.getOwnerNationalityTrns());
   	 form.setOwnerAddressTrns(mapDto.getOwnerAddressTrns());
   	 form.setOwnerPhnoTrns(mapDto.getOwnerPhnoTrns());
   	 if(mapDto.getOwnerEmailIDTrns().equals("-") )
          		form.setOwnerEmailIDTrns("");
          	 else
       	    form.setOwnerEmailIDTrns(mapDto.getOwnerEmailIDTrns());
   	
   	 form.setOwnerIdnoTrns(mapDto.getOwnerIdnoTrns());
   	 
   	/* String[] date=parseStringDOBfromDB(mapDto.getOwnerDOBTrns());
   	 form.setOwnerDayTrns(date[0].trim());  
   	form.setOwnerMonthTrns(date[1].trim()); 
   	form.setOwnerYearTrns(date[2].trim()); */
   	 
	 if(mapDto.getOwnerAgeTrns().equals("-")){
	   		form.setOwnerAgeTrns("");
	    	 }
	   	 else{
	   		form.setOwnerAgeTrns(mapDto.getOwnerAgeTrns());
	   	 }
   	 
   	 form.setOwnerListIDTrns(mapDto.getOwnerListIDTrns());
   	 //mapDto.setOwnerProofNameTrns(commonBd.getPhotoIdProofName(ownerDetlsArr[7].trim()));
    }
    
    
  	//map.put(listArr[24].trim(), mapDto);
  	
  	
	//return map;
}
}
/**
 * updateTransPartyDetails
 * Returns boolean value in order to check the insertion.
 * @param RegCommonForm
 * @author ROOPAM
 * @return boolean
 * @throws Exception 
 *
 */
public boolean updateTransPartyDetails(RegCommonForm form) throws Exception {
	return commonBd.updateTransPartyDetails(form);

}
/**
 * getPartyDetsForViewConfirm
 * for getting APPLICANT PARTY details from db.
 * @param String, String
 * @return ArrayList
 * @author ROOPAM
 */
public void getPropertyDetsForViewConfirmModify(RegCompletionForm eForm, HashMap map,int deedId) throws Exception {
	
	//HashMap map=form.getMapTransactingPartiesDisp();
	
	
	RegCompletDTO dto;
	DMSUtility dmsUtil=new DMSUtility();
	
	Collection mapCollection=map.values();
    Object[] l1=mapCollection.toArray();
	
    for(int i=0;i<l1.length;i++)
	{
	
    	dto =(RegCompletDTO)l1[i];
	
   
	
	eForm.setAreaType(dto.getAreaTypeName());
	//else
	//	eForm.setAreaType("-");
	eForm.setDistrict(dto.getDistName());
	eForm.setTehsil(dto.getTehsilName());
	eForm.setPropertyType1(dto.getPropertyTypeName());
	eForm.setPropertyTypeId(dto.getPropertyTypeId());
	
	/*if(eForm.getPropertyType1().equalsIgnoreCase("plot") ||
			eForm.getPropertyType1().equalsIgnoreCase("building"))
	{
		
		eForm.setUnit("sq mtr");
	}
	else
	{
		
		String unit=pform[16].trim();
		if(unit.equalsIgnoreCase("2"))
		{*/
			eForm.setUnit(dto.getUnit());
		/*}
		else if(unit.equalsIgnoreCase("3"))
		{
			eForm.setUnit("HECTARE");
		}
		
	}*/
	
	if(!eForm.getPropertyTypeId().equalsIgnoreCase("2"))  // 2 for building
	{
		/*
		if(eForm.getExchangePropertyList().size()==0)
		{
			eForm.setSelectedSubClauseList(commonBo.getSubClauseListNonBuilding(regInitForm.getValuationId()));
		
		}
		else
		{*/					
			eForm.setSelectedSubClauseList(dto.getSelectedSubClauseList());	
		/*}*/
		
	}
	
	eForm.setMunicipalBody(dto.getMunicipalBodyName());
	eForm.setMahalla(dto.getMohallaName());
	eForm.setWard(dto.getWardpatwarName());
	eForm.setTotalSqMeter(dto.getTotalSqMeter());
	eForm.setTotalSqMeterDisplay(dto.getTotalSqMeterDisplay());
	/*String wardstatus=pform[8].trim();

	if (wardstatus.equalsIgnoreCase("1"))
	{
		wardstatus="Ward";
	}
	else
	{
		wardstatus="Patwari/Halka";
	}*/
	eForm.setPatwariStatus(dto.getPatwariStatus());
	
	
	if(eForm.getPropertyTypeId().equalsIgnoreCase("2")){  // 2 for building
		
		
		//following code for getting building floor details
		//ArrayList buildingList=new ArrayList();
		/*if(eForm.getExchangePropertyList().size()==0)
		{
			buildingList=commonBo.getGuildingFloorDetails(regInitForm.getValuationId());
		}
			else
			{
				buildingList = commonBo.getGuildingFloorDetails(eForm.getRegcompletDto().getSelectedPropValId());	
			}*/
		
		
	/*	if(buildingList.size()>0){
			
			eForm.setNumfloors(buildingList.size());
			for(int j=0;j<buildingList.size();j++){
				RegCompletDTO dto1=new RegCompletDTO();
				ArrayList row_list=(ArrayList)buildingList.get(j);
				String str=row_list.toString();
				str=str.substring(1,str.length()-1);
				String[] strArray=str.split(",");
				dto1.setUsageBuilding(strArray[7]);
				dto1.setCeilingType(strArray[8]);
				dto1.setTypeFloorDesc(strArray[9]);
				dto1.setConsiderAmt(Double.parseDouble(strArray[4]));
				dto1.setTotalSqMeter(Double.parseDouble(strArray[5]));
				dto1.setConstructedArea(strArray[6]);
				
				
				if(eForm.getExchangePropertyList().size()==0)
				{
					dto1.setSelectedSubClauseList(commonBo.getSubClauseListBuilding(regInitForm.getValuationId(),strArray[0]));
					
				}
					else
					{
						dto1.setSelectedSubClauseList(commonBo.getSubClauseListBuilding(eForm.getRegcompletDto().getSelectedPropValId(),strArray[0]));
							
					}*/
				
				
				eForm.setMapBuildingDetails1(dto.getMapBuilding());
				eForm.setNumfloors(dto.getMapBuilding().size());
				
			/*	
			}
				
		}*/
		
	}else{
		eForm.setMapBuildingDetails1(null);
		eForm.setNumfloors(0);
	}
	
	if(dto.getVikasId().equals("-"))
		eForm.getRegcompletDto().setVikasId("");
		else
			eForm.getRegcompletDto().setVikasId(dto.getVikasId());
	
	eForm.getRegcompletDto().setRicircle(dto.getRicircle());
		
		if(dto.getLayoutdet().equals("-"))
			eForm.getRegcompletDto().setLayoutdet("");
		else
			eForm.getRegcompletDto().setLayoutdet(dto.getLayoutdet());
		
		if(dto.getSheetnum().equals("-"))
			eForm.getRegcompletDto().setSheetnum("");	
		else
			eForm.getRegcompletDto().setSheetnum(dto.getSheetnum());
		
		if(dto.getPlotnum().equals("-") )
			eForm.getRegcompletDto().setPlotnum("");
		else
			eForm.getRegcompletDto().setPlotnum(dto.getPlotnum());
		
		eForm.getRegcompletDto().setPropAddress(dto.getPropAddress());
		eForm.getRegcompletDto().setNorth(dto.getNorth());
		eForm.getRegcompletDto().setSouth(dto.getSouth());
		eForm.getRegcompletDto().setEast(dto.getEast());
		eForm.getRegcompletDto().setWest(dto.getWest());
		if(dto.getPropLandmark().equals("-"))
			eForm.getRegcompletDto().setPropLandmark("");
			else
				eForm.getRegcompletDto().setPropLandmark(dto.getPropLandmark());
	

		if(dto.getKhasraDetlsDisplay().size()<=0){
			eForm.getRegcompletDto().setKhasraDetlsDisplay(null);
			eForm.getRegcompletDto().setKhasraPresentFlag(0);
			
		}else if(dto.getKhasraDetlsDisplay().size()==1){
			
			CommonDTO objDto=(CommonDTO)dto.getKhasraDetlsDisplay().get(0);
			
			if(objDto.getKhasraNum().equals("-")){
				eForm.getRegcompletDto().setKhasraDetlsDisplay(null);
				eForm.getRegcompletDto().setKhasraPresentFlag(0);
			}
			else{
				eForm.getRegcompletDto().setKhasraDetlsDisplay(dto.getKhasraDetlsDisplay());
				eForm.getRegcompletDto().setKhasraPresentFlag(1);
			}
	}
		else if(dto.getKhasraDetlsDisplay().size()>1){
			eForm.getRegcompletDto().setKhasraDetlsDisplay(dto.getKhasraDetlsDisplay());
			eForm.getRegcompletDto().setKhasraPresentFlag(1);
		}
	
	
	/*if(deedId!=RegInitConstant.DEED_DEPOSIT_OF_TITLE && deedId!=RegInitConstant.DEED_LEASE_NPV &&
			deedId!=RegInitConstant.DEED_TRUST && deedId!=RegInitConstant.DEED_ADOPTION &&
			deedId!=RegInitConstant.DEED_CANCELLATION_OF_WILL_POA && deedId!=RegInitConstant.DEED_MORTGAGE_NPV &&
			deedId!=RegInitConstant.DEED_POA && deedId!=RegInitConstant.DEED_SETTLEMENT_NPV &&
			deedId!=RegInitConstant.DEED_WILL_NPV && deedId!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
			deedId!=RegInitConstant.DEED_RECONV_MORTGAGE_NPV && deedId!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
			deedId!=RegInitConstant.DEED_PARTNERSHIP_NPV && deedId!=RegInitConstant.DEED_PARTITION_NPV
			&& deedId!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV && deedId!=RegInitConstant.DEED_COMPOSITION_NPV
			&& deedId!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV && deedId!=RegInitConstant.DEED_SECURITY_BOND_NPV
			&& deedId!=RegInitConstant.DEED_TRANSFER_NPV && deedId!=RegInitConstant.DEED_FURTHER_CHARGE_NPV)*/
		if(eForm.getRegInitForm().getDeedTypeFlag()==0)
		{
		eForm.getRegcompletDto().setPropImage1DocumentName(dto.getPropImage1DocumentName());
		eForm.getRegcompletDto().setPropImage1FilePath(dto.getPropImage1FilePath());
		eForm.getRegcompletDto().setPropImage1DocContents(dto.getPropImage1DocContents());
		eForm.getRegcompletDto().setPropImage1PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropImage1UploadType(dto.getPropImage1UploadType());
		
		eForm.getRegcompletDto().setPropImage2DocumentName(dto.getPropImage2DocumentName());
		eForm.getRegcompletDto().setPropImage2FilePath(dto.getPropImage2FilePath());
		eForm.getRegcompletDto().setPropImage2DocContents(dto.getPropImage2DocContents());
		eForm.getRegcompletDto().setPropImage2PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropImage2UploadType(dto.getPropImage2UploadType());
		
		eForm.getRegcompletDto().setPropImage3DocumentName(dto.getPropImage3DocumentName());
		eForm.getRegcompletDto().setPropImage3FilePath(dto.getPropImage3FilePath());
		eForm.getRegcompletDto().setPropImage3DocContents(dto.getPropImage3DocContents());
		eForm.getRegcompletDto().setPropImage3PartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropImage3UploadType(dto.getPropImage3UploadType());
		
		eForm.getRegcompletDto().setPropMapDocumentName(dto.getPropMapDocumentName());
		eForm.getRegcompletDto().setPropMapFilePath(dto.getPropMapFilePath());
		eForm.getRegcompletDto().setPropMapDocContents(dto.getPropMapDocContents());
		eForm.getRegcompletDto().setPropMapPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropMapUploadType(dto.getPropMapUploadType());
		
		if(	eForm.getPropertyTypeId().equalsIgnoreCase("3"))  // 3 for agriland
		{
		eForm.getRegcompletDto().setPropRinDocumentName(dto.getPropRinDocumentName());
		eForm.getRegcompletDto().setPropRinFilePath(dto.getPropRinFilePath());
		eForm.getRegcompletDto().setPropRinDocContents(dto.getPropRinDocContents());
		eForm.getRegcompletDto().setPropRinPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropRinUploadType(dto.getPropRinUploadType());
		
		eForm.getRegcompletDto().setPropKhasraDocumentName(dto.getPropKhasraDocumentName());
		eForm.getRegcompletDto().setPropKhasraFilePath(dto.getPropKhasraFilePath());
		eForm.getRegcompletDto().setPropKhasraDocContents(dto.getPropKhasraDocContents());
		eForm.getRegcompletDto().setPropKhasraPartyOrProp(RegInitConstant.FILE_UPLOAD_PROP);
		eForm.getRegcompletDto().setPropKhasraUploadType(dto.getPropKhasraUploadType());
		}
	
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
      		
      		/*form.setDocumentNameTrns("COLLECTOR'S CERTIFICATE");               //CERTIFICATE  40
       		form.setFilePathTrns(mapDto.getFilePathTrns());
       		form.setDocContentsTrns(dmsUtil.getImageBytes(mapDto.getFilePathTrns()));
       		form.setPartyOrPropTrns(RegInitConstant.FILE_UPLOAD_PARTY);
       		form.setUploadTypeTrns(RegInitConstant.FILE_NAME_CERTIFICATE);*/
     
      
  
  	
  	

}
	
}
/**
 * createNewKhasraDetls
 * Returns boolean value in order to check the insertion.
 * @param RegCommonForm
 * @author ROOPAM
 * @return boolean
 * @throws Exception 
 *
 */
public void createNewKhasraDetls(RegCompletionForm eForm) throws Exception {
	
	try{
	   String[] khasraNum=eForm.getKhasraNoArray().trim().split(",");
		String[] khasraArea=eForm.getKhasraAreaArray().trim().split(",");
		String[] lagaan=eForm.getLagaanArray().trim().split(",");
		String[] rinPustika=eForm.getRinPustikaArray().trim().split(",");
       
       int len=khasraNum.length;
       
       eForm.getRegcompletDto().setKhasraDetlsDisplay(new ArrayList());
       CommonDTO objDto;
       for(int i=0;i<len;i++){
       	
       	objDto=new CommonDTO();
       	
       	objDto.setKhasraNum(khasraNum[i].trim());
			objDto.setKhasraArea(khasraArea[i].trim());
			objDto.setLagaan(lagaan[i].trim());
			objDto.setRinPustika(rinPustika[i].trim());
       	
       	
       	eForm.getRegcompletDto().getKhasraDetlsDisplay().add(objDto);
       	
       }
	}
	catch(Exception e){
		e.printStackTrace();
	}
	

}
/**
 * getEstampCode
 * for getting eStamp code from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getEstampCode(String regId) throws Exception {
	return commonBd.getEstampCode(regId);
}
/**
 * getPendingAppStatus
 * for getting pending reg init application details from db
 * @param String
 * @return String
 * @author ROOPAM
 */
public String[] getPendingAppStatus(String appId) throws Exception {
	
	ArrayList list=commonBd.getPendingAppStatus(appId);
	String[] strArr=new String[2];
	
	if(list!=null && list.size()>0){
		ArrayList rowList;
		
		for(int i=0;i<1;i++){
			rowList=(ArrayList)list.get(i);
			
			String str=rowList.toString();
			str=str.substring(1, str.length()-1);
			
			strArr=str.split(",");
			
			
		}
		
		
		return strArr;
	}else{
		return null;
	}
	
	//return commonBd.getPropertyIdApplicant(appId);

}
/**
 * landTransactingPartyScreen
 * for landing on transacting party screen from dashboard
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void landTransactingPartyScreen(RegCommonForm regForm,String appStatus,String languageLocale) throws Exception {
	
	RegCommonDTO commonDto=regForm.getCommonDto();
	
	//FOLLOWING CODE FOR INSERTING APPLICANT DETAILS IN HASHMAP
	if(regForm.getDeedID()!=RegInitConstant.DEED_TRUST_PV && regForm.getDeedID()!=RegInitConstant.DEED_TRUST &&
			regForm.getInstID()!=RegInitConstant.INST_PARTNERSHIP_NPV_1 && regForm.getInstID()!=RegInitConstant.INST_PARTNERSHIP_NPV_2 ){
	HashMap map=regForm.getMapTransactingParties();
	regForm.setMapTransactingParties(insertApplicantDetsInMap(map,"key", regForm.getHidnRegTxnId(),regForm,languageLocale));
	}else{
		//add extra conditions for trust deed
		regForm.setAddMoreCounter(0);         //if deed is trust
	}
	
	//String[] propId=getPropertyIdApplicant(regForm.getHidnRegTxnId());
	String[] propId=getLatestPropertyId(regForm.getHidnRegTxnId());
	if(propId!=null){
		
		if(propId[0].trim().length()==15){
			regForm.setPropertyId("0"+propId[0].trim());
		}else{
		regForm.setPropertyId(propId[0].trim());
		}
		regForm.setValuationId(propId[1].trim());
	}
	
	//for getting exchange deed property list
	if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
		regForm.setValuationId(getRefValIdExchngDeed(regForm.getHidnRegTxnId()));
		getExchangePropertyList(regForm,1,null);
		regForm.setIsTransactingPartyAddedFlag(1);
	}
	
	commonDto.setPartyType(getPartyType(regForm.getDeedID(),regForm.getInstID(),languageLocale));
	commonDto.setAppTypeTrns(getAppType(languageLocale));
	commonDto.setCountryTrns(getCountry(languageLocale));
	commonDto.setIndcountryTrns(getCountry(languageLocale));
	commonDto.setIdProfTrns(getIdProf(languageLocale));
	commonDto.setCategoryList(getCategoryList(languageLocale));
	commonDto.setOccupationList(getOccupationList(languageLocale));
	commonDto.setRelationshipList(getRelationshipList(regForm.getGendarTrns(),languageLocale));
	commonDto.setAuthPerRelationshipList(getRelationshipList(regForm.getAuthPerGendarTrns(),languageLocale));
	if(regForm.getCommonDeed()==1){
		commonDto.setExecutantClaimant(getExecutantClaimant(languageLocale,regForm.getInstID()));
	}
	regForm.setDeedId(Integer.toString(regForm.getDeedID()));
	regForm.setCountryTrns("1");
	regForm.setCountryNameTrns("INDIA");
	regForm.setStatenameTrns("1");
	regForm.setStatenameNameTrns("MADHYA PRADESH");
	regForm.setIndcountryTrns("1");
	regForm.setIndcountryNameTrns("INDIA");
	regForm.setIndstatenameTrns("1");
	regForm.setIndstatenameNameTrns("MADHYA PRADESH");
	regForm.setAuthPerCountryTrns("1");
	regForm.setAuthPerCountryNameTrns("INDIA");
	regForm.setAuthPerStatenameTrns("1");
	regForm.setAuthPerStatenameNameTrns("MADHYA PRADESH");
	
	
	//FOLLOWING CODE FOR FETCHING APPLICANT ROLE ID.
	//disabling applicant role in multiple properties.
	String applicantRoleId=getApplicantRoleId(regForm.getHidnRegTxnId());
	regForm.setApplicantRoleId2(Integer.parseInt(applicantRoleId));
	
	int totalShare=getShareOfPropList(regForm.getHidnRegTxnId());

	if(regForm.getDeedID()!=RegInitConstant.DEED_TRUST_PV && regForm.getDeedID()!=RegInitConstant.DEED_TRUST &&
			regForm.getInstID()!=RegInitConstant.INST_PARTNERSHIP_NPV_1 && regForm.getInstID()!=RegInitConstant.INST_PARTNERSHIP_NPV_2 ){
	regForm.setTotalShareOfProp(totalShare);
	}else if(!appStatus.equalsIgnoreCase(RegInitConstant.STATUS_APP_SAVED)){
		regForm.setTotalShareOfProp(0);
	}else{
		HashMap map=regForm.getMapTransactingParties();
		regForm.setMapTransactingParties(insertApplicantDetsInMap(map,"key", regForm.getHidnRegTxnId(),regForm,languageLocale));
		regForm.setTotalShareOfProp(totalShare);
	}
	
	
	if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y"))
    {
    
		//following if condition for disabling applicant role once total sum of share is 100%.
		
		if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
			
			if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_POA_ACCEPTER){
				regForm.setIsPoaAddedFlag(1);
				regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
				
			}else if(totalShare==100){
				regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
			}
			
		}else if(regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV || regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
				(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )))
		{
		}
		else if(totalShare==100){
			regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
		}
		
    
		
}else{
	
	if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
		
		if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_POA_ACCEPTER){
			regForm.setIsPoaAddedFlag(1);
			//regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
			
		}
		
	}
	else
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
	else if(regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV || regForm.getDeedID()==RegInitConstant.DEED_TRUST ||
			(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
					/*&& 
					 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )*/
					 ) ||
					 (regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
								&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
					 (regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
								&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
					 (regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
								&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
								regForm.getCommonDeed()==1){
		
	}
	else
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
	else{
		regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));                 //FOR DISSABLING RADIO
	}
	
}
	
	
	
}
/**
 * getPropertyIdApplicant
 * for getting property id of applicant from db
 * @param String
 * @return String[]
 * @author ROOPAM
 */
public String[] getPropertyIdApplicant(String appId) throws Exception {
	
	ArrayList list=commonBd.getPropertyIdApplicant(appId);
	String[] strArr=new String[2];
	String str;
	if(list!=null && list.size()>0){
		ArrayList rowList;
		
		for(int i=0;i<1;i++){
			rowList=(ArrayList)list.get(i);
			
			str=rowList.toString();
			str=str.substring(1, str.length()-1);
			
			strArr=str.split(",");
			
			
		}
		
		
		return strArr;
	}else{
		return null;
	}
	
	//return commonBd.getPropertyIdApplicant(appId);

}
/**
 * landPropertyScreen
 * for landing on property screen from dashboard
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void landPropertyScreen(RegCommonForm regForm,String appStatus) throws Exception {
	
	RegCommonDTO commonDto=regForm.getCommonDto();
	
	
	
	
	regForm.setDeedId(Integer.toString(regForm.getDeedID()));
	
	
	//for getting exchange deed property list
	if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE || 
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
		
		regForm.setValuationId(getRefValIdExchngDeed(regForm.getHidnRegTxnId()));
		getExchangePropertyList(regForm,1,appStatus);
	}else{
		String[] propId=getLatestPropertyId(regForm.getHidnRegTxnId());
		if(propId!=null){
			
			if(propId[0].trim().length()==15){
				regForm.setPropertyId("0"+propId[0].trim());	
			}else{
			regForm.setPropertyId(propId[0].trim());
			}
			regForm.setValuationId(propId[1].trim());
		}
	}
	
	//FOLLOWING CODE FOR FETCHING APPLICANT ROLE ID.
	//disabling applicant role in multiple properties.
	String applicantRoleId=getApplicantRoleId(regForm.getHidnRegTxnId());
	regForm.setApplicantRoleId2(Integer.parseInt(applicantRoleId));
	
	int totalShare=getShareOfPropList(regForm.getHidnRegTxnId());

	regForm.setTotalShareOfProp(totalShare);
	
	
	if(regForm.getHdnDeclareShareCheck().equalsIgnoreCase("Y"))
    {
    
		//following if condition for disabling applicant role once total sum of share is 100%.
		if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
			
			if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_POA_ACCEPTER){
				regForm.setIsPoaAddedFlag(1);
				regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
				
			}else if(totalShare==100){
				regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
			}
			
		}else
		if(totalShare==100){
			regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
		}
}else{
	
	if(regForm.getDeedID()==RegInitConstant.DEED_POA_P){
		
		if(Integer.parseInt(applicantRoleId)==RegInitConstant.ROLE_POA_ACCEPTER){
			regForm.setIsPoaAddedFlag(1);
			//regForm.setApplicantRoleId(Integer.parseInt(applicantRoleId));
			
		}
		
	}
	
}

	
	
	
}
/**
 * getExchangePropertyList
 * for getting property list in case of exchange deed
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void getExchangePropertyList(RegCommonForm regForm,int flag,String appStatus) throws Exception {
	

	
	ArrayList list=new ArrayList();
	ArrayList valIdList=new ArrayList();
	String[] valIdArray=null;
	
	
	if(appStatus!=null){
	if(appStatus.equalsIgnoreCase(RegInitConstant.STATUS_MULTI_PROP_IN_PROGRESS)){
		valIdList=getRemValuationIdsExchangeDeed(regForm.getValuationId());
	}
	if(appStatus.equalsIgnoreCase(RegInitConstant.STATUS_TRNS_SAVED)){
		valIdList=getAllValuationIdsExchangeDeed(regForm.getValuationId());
	}
	}else{
		valIdList=getAllValuationIdsExchangeDeed(regForm.getValuationId());
	}
	
	
	if(valIdList!=null && valIdList.size()>0)
	{
		valIdArray=new String[valIdList.size()];
		ArrayList row_list=new ArrayList();
		String str;
		
	for(int i=0;i<valIdList.size();i++)
	{
	row_list=(ArrayList)valIdList.get(i);
	str=row_list.toString();
	str=str.substring(1, str.length()-1);
	valIdArray[i]=str;
	}
	
	}
	
	
	//following code for getting districts, tehsils, market values and party type(1 0r 2) for all the properties
	if(valIdArray!=null){
		String distId;
		String tehslId;
		String propertyId;
		String[] propDetlsArray;
		
	for(int i=0;i<valIdArray.length;i++){
		CommonDTO dto=new CommonDTO();
		
		//String[] propDetlsArray;
		if(flag==0)                                                              //not from dashboard-- create property id       
		{	
		propDetlsArray=getAllPropDetlsExchangeDeed(valIdArray[i].trim());
		
		distId=propDetlsArray[1].trim();
		if(distId.length()==1)
			distId="0"+distId;
		
		tehslId=propDetlsArray[2].trim();
		if(tehslId.length()==1)
			tehslId="0"+tehslId;
				
		propertyId=distId+tehslId+getPropertyIdSequence();
		dto.setPropertyId(propertyId);
		dto.setValuationId(valIdArray[i].trim());
		dto.setMarketValue(propDetlsArray[3].trim());
		dto.setPartyType(propDetlsArray[4].trim());
	}
		else{                                                                  //from dashboard-- retrieve property id from dashboard
		
			propDetlsArray=getAllPropDetlsExchangeDeedDash(valIdArray[i].trim());         
			
			if(propDetlsArray[0].trim().length()==15){
				dto.setPropertyId("0"+propDetlsArray[0].trim());
			}
			else{
			dto.setPropertyId(propDetlsArray[0].trim());
			}
			dto.setValuationId(propDetlsArray[1].trim());
			dto.setMarketValue(propDetlsArray[2].trim());
			dto.setPartyType(propDetlsArray[3].trim());
			
		}
		
		
		list.add(dto);
	}
	regForm.setExchangePropertyList(list);
	}
	regForm.setPropertyId(null);

	
}
/**
 * getLatestPropertyId
 * for getting latest property id from db
 * @param String
 * @return String[]
 * @author ROOPAM
 */
public String[] getLatestPropertyId(String appId) throws Exception {
	
	ArrayList list=commonBd.getLatestPropertyId(appId);
	String[] strArr=new String[2];
	String str;
	if(list!=null && list.size()>0){
		ArrayList rowList;
		
		for(int i=0;i<1;i++){
			rowList=(ArrayList)list.get(i);
			
			str=rowList.toString();
			str=str.substring(1, str.length()-1);
			
			strArr=str.split(",");
			
			
		}
		
		
		return strArr;
	}else{
		return null;
	}
	
}
/**
 * getAllPropDetlsExchangeDeedDash
 * for getting ALL PROPERTY DETAILS from db for dashboard.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public String[] getAllPropDetlsExchangeDeedDash(String valId) throws Exception {
	
	String[] strArray=null;
	ArrayList list=commonBd.getAllPropDetlsExchangeDeedDash(valId);
	if(list!=null && list.size()>0)
	{
	String str=list.toString();
	str=str.substring(2, str.length()-2);
	strArray=str.split(",");
	}
	return strArray;
}
/**
 * getDutyDetlsAdjuForConfirmation
 * for getting duty details Adju from db.
 * @param String
 * @return String array
 * @author SHREERAJ
 */
/*public String[] getDutyDetlsAdjuForConfirmation(String appId) throws Exception {
	
	String dutyListArr[]=null;
	ArrayList list=commonBd.getDutyDetlsAdjuForConfirmation(appId);
	
	if(list!=null && !(list.size()<1)){
		for(int i=0;i<1;i++){
	String dutyList=list.toString();
	dutyList=dutyList.substring(2, dutyList.length()-2);
	dutyListArr=dutyList.split(",");
		}
	}
	return dutyListArr;
}*/
/**
 * landConfirmationScreen
 * for landing on confirmation screen from dashboard
 * @param RegCommonForm
 * @return void
 * @author ROOPAM
 */
public void landConfirmationScreen(RegCommonForm regForm,String languageLocale) throws Exception {	   //add status for confirmation screen
	
	regForm.setMapPropertyTransParty(new HashMap());
	 
	//String adjuFlag="";
	 
	String[] deedInstArray=getDeedInstId(regForm.getHidnRegTxnId());
	String purpose=getEStampPurpose(regForm.getHidnRegTxnId());    
	//String purpose="";
	if(purpose!=null && !purpose.equalsIgnoreCase("")){
		regForm.setPurpose(purpose.replace(RegInitConstant.SPLIT_CONSTANT, ","));
	}else{
		regForm.setPurpose("");
	}
	
		
	if(deedInstArray!=null && deedInstArray.length>0){
		
		//request.setAttribute("deedId", deedInstArray[0].trim());
		//request.setAttribute("instId", deedInstArray[1].trim());
		regForm.setDeedID(Integer.parseInt(deedInstArray[0].trim()));
		regForm.setCommonDeed(getCommonDeedFlag(deedInstArray[0].trim()));   // setting common deed flag
		regForm.setInstID(Integer.parseInt(deedInstArray[1].trim()));
		if(deedInstArray[2].trim().equals("-") || deedInstArray[2].trim().equalsIgnoreCase("")){
		regForm.setExmpID("");
		regForm.setHdnExAmount("");
		}else{
			regForm.setExmpID(deedInstArray[2].trim());
			//regForm.setHdnExAmount(deedInstArray[2].trim());
		}
		if(deedInstArray[3].trim()!=null && !deedInstArray[3].trim().equalsIgnoreCase("")){
			//adjuFlag=1;
			if(deedInstArray[3].trim().equalsIgnoreCase("A")){
				regForm.setFromAdjudication(1);
			}else{
				regForm.setFromAdjudication(0);
			}
		}else{
			regForm.setFromAdjudication(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA){
			regForm.setCancelDeedRadio(Integer.parseInt(deedInstArray[4].trim()));
			}
		
	}else {
		//request.setAttribute("deedId", 0);
		//request.setAttribute("instId", 0);
		regForm.setDeedID(0);
		regForm.setInstID(0);
		regForm.setExmpID("");
		regForm.setHdnExAmount("");
	}
	
	regForm.setDeedtype1(getDeedName(Integer.toString(regForm.getDeedID()),languageLocale));
	regForm.setInstType(getInstrumentName(Integer.toString(regForm.getInstID()),languageLocale));
	//below code for exemptions
	
	String exemptions = regForm.getExmpID();
	regForm.setSelectedExemptionList(getExemptionList(exemptions));
	
	getExtraDetls(regForm,languageLocale);
	
	NumberFormat obj=new DecimalFormat("#0.00");
	//String dutyListArr[]=new String[9];
	//String dutyListAdjuArr[]=new String[10];
	String dutyListArr[];
	int numberOfProperties;
	
	if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE || 
			(regForm.getDeedID()==RegInitConstant.DEED_TRUST && regForm.getInstID()==RegInitConstant.INST_TRUST_NPV_NP) ||
			regForm.getDeedID()==RegInitConstant.DEED_ADOPTION ||
			regForm.getDeedID()==RegInitConstant.DEED_CANCELLATION_OF_WILL_POA ||
			regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV  ||
			(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
			(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV && 
					regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_NOT_REQ_CONSTANT_2) ||
					(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
							&& regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV) ||
			regForm.getCommonDeed()==1
			){
		
			//regForm.setDeedTypeFlag(0);
		
	
	if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE ||
			regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ||
			regForm.getDeedID()==RegInitConstant.DEED_PARTITION_NPV){      
	regForm.setDeedTypeFlag(0);          // deedTypeFlag 0 for deeds where property details are required.
	ArrayList propertyIdList=getPropertyListNonPropDeed(regForm.getHidnRegTxnId());
	numberOfProperties=propertyIdList.size();
	regForm.setPropListNonPropDeed(propertyIdList);
		}
	else if(regForm.getCommonDeed()==1){                 // for common deeds
		
	      
		//regForm.setDeedTypeFlag("");         
		ArrayList propertyIdList=getParticularList(regForm.getHidnRegTxnId());
		regForm.setPropListNonPropDeed(propertyIdList);
		numberOfProperties=1;
		//regForm.setPropListNonPropDeed(new ArrayList());
			
		
		
	}
	else{
			regForm.setDeedTypeFlag(1);  // deedTypeFlag 1 for deeds where property details are not required.
			regForm.setPropListNonPropDeed(null);
			numberOfProperties=1;
		}
	
	

	ArrayList transPartyDets=getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale, regForm.getDeedID(), regForm.getInstID());
		regForm.setTransPartyListNonPropDeed(transPartyDets);
	
	/*for(int j=0;j<transPartyDets.size();j++){
		
		CommonDTO dto=new CommonDTO();
		dto=(CommonDTO)transPartyDets.get(j);
		logger.debug("transacting party list  role------>"+dto.getRole());
		logger.debug("transacting party list  name------>"+dto.getName());
		logger.debug("transacting party list  id------>"+dto.getId());
	
	}
	*/
		if(regForm.getInitiateAdjuApp()==0){
	dutyListArr=getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
	
	regForm.setAdjuRemarks("");
	
		}
	else{
			dutyListArr=getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());	
			
			regForm.setAdjuRemarks(getRemarks(regForm.getHidnRegTxnId()));
			//get adjudication remarks here...

		}
	//forward="reginitConfirmNonPV";
	
	}
	
	else{
		
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
				(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
							 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 ))||
				(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV 
	  						&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  			(regForm.getDeedID()==RegInitConstant.DEED_TRANSFER_NPV 
	  		  				&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1) ||
	  			(regForm.getDeedID()==RegInitConstant.DEED_FURTHER_CHARGE_NPV 
	  		  		  		&& regForm.getAgreeMemoInstFlag()==RegInitConstant.NPV_PROP_REQ_CONSTANT_1)){
			regForm.setDeedTypeFlag(0);                          //// deedTypeFlag 0 for deeds where property details are required.
		} 
		
		
	HashMap propMap =new HashMap();
	propMap=regForm.getMapPropertyTransParty();
	//logger.debug("in confirmation action----------------------->");
	ArrayList propertyIdList=new ArrayList();
	//if(adjuFlag==1){
	//	propertyIdList=getPropertyIdMarketValAdju(regForm.getAdjudicationNumber());  //to be changed
	//}else{
	    propertyIdList=getPropertyIdMarketVal(regForm.getHidnRegTxnId());
	//}
	double totalMarketVal=0;
	
	numberOfProperties=propertyIdList.size();
	
	for(int i=0;i<propertyIdList.size();i++){
		
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)propertyIdList.get(i);
		String propIds=row_list.toString();
		propIds=propIds.substring(1, propIds.length()-1);
		
		String propId[]=propIds.split(",");
		
		if(regForm.getDeedID()!=RegInitConstant.DEED_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_MORTGAGE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_POA &&
				regForm.getDeedID()!=RegInitConstant.DEED_SETTLEMENT_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_WILL_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_TRANSFER_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_SURRENDER_LEASE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_COMPOSITION_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_SECURITY_BOND_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_LETTER_OF_LICENCE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_AGREEMENT_MEMO_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_TRANSFER_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_FURTHER_CHARGE_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_PARTITION_NPV &&
				regForm.getDeedID()!=RegInitConstant.DEED_PARTNERSHIP_NPV){
		if(!propId[1].trim().equalsIgnoreCase("") && !propId[1].trim().equals(null) && !propId[1].trim().equalsIgnoreCase("null")){
		totalMarketVal=totalMarketVal+Double.parseDouble(propId[1].trim());
		}else{
			totalMarketVal=0;
		}
		}
		
		if(regForm.getDeedID()!=RegInitConstant.DEED_PARTITION_PV)
		{
			regForm.setPropListPVDeed(null);
    	ArrayList transPartyDets=getTransPartyDets(propId[0].trim(),regForm.getHidnRegTxnId(),languageLocale, regForm.getDeedID(), regForm.getInstID());
		/*for(int j=0;j<transPartyDets.size();j++){
			CommonDTO dto=new CommonDTO();
			dto=(CommonDTO)transPartyDets.get(j);
		}*/
		if(propId[0].trim().length()==15){
			propId[0]="0"+propId[0].trim();
		}
		propMap.put(propId[0].trim()+","+propId[1].trim(), transPartyDets);
		
		}
		else{
			regForm.setPropListPVDeed(getPropertyListPVDeed(propertyIdList));
		}
		
	}
	
	if(regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
		ArrayList transPartyDets=getTransPartyDetsNonPropDeed(regForm.getHidnRegTxnId(),regForm.getCommonDeed(),languageLocale, regForm.getDeedID(), regForm.getInstID());
		regForm.setTransPartyListPVDeed(transPartyDets);
	}else{
		regForm.setTransPartyListPVDeed(null);
	}
	
	regForm.setTotalMarketValue(obj.format(totalMarketVal));
	regForm.setMapPropertyTransParty(propMap);
	//if(adjuFlag==1){
	//	dutyListArr=getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());   //to be changed
	//}
	//else{
	if(regForm.getInitiateAdjuApp()==0){
		dutyListArr=getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
		regForm.setAdjuRemarks("");
			}
			else{
				dutyListArr=getDutyDetlsAdjuForConfirmation(regForm.getHidnRegTxnId());		
				
				regForm.setAdjuRemarks(getRemarks(regForm.getHidnRegTxnId()));

			}
	//dutyListArr=getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
	//}
	
	//forward="reginitConfirm";
	}
	
	
	
	//String dutyListArr[]=new String[9];
	
	/*if(adjuFlag==1){
		dutyListArr=commonBo.getAdjudicatedDutyDetls(regForm.getAdjudicationNumber());   //to be changed
	}
	else{
		dutyListArr=commonBo.getDutyDetlsForConfirmation(regForm.getHidnRegTxnId());
	}*/
	
	double totalPaidAmt=0;
	ArrayList paidAmtList=getAllPaidAmounts(regForm.getHidnRegTxnId());
	for(int i=0;i<paidAmtList.size();i++){
		ArrayList row_list=new ArrayList();
		row_list=(ArrayList)paidAmtList.get(i);
		String amnts=row_list.toString();
		amnts=amnts.substring(1, amnts.length()-1);
		//logger.debug("paid amount list-->"+amnts);
		String amntsArr[]=amnts.split(",");
		
		totalPaidAmt=totalPaidAmt+Double.parseDouble(amntsArr[0].trim());
	}
	regForm.setTotalPaidAmount(obj.format(totalPaidAmt));
	
	
	
		if(numberOfProperties==1)
		{
			if(dutyListArr[5].trim()!=null && !dutyListArr[5].trim().equalsIgnoreCase("") && !dutyListArr[5].trim().equalsIgnoreCase("null")){
			regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			}else{
				regForm.setTotalPayableAmount(obj.format(0));
			}
			
			//below code for retrieving estamp code if total payable amount is more than zero
			//if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
			//{
				String eCode=getEstampCode(regForm.getHidnRegTxnId());
				
				if(eCode!=null && !eCode.equalsIgnoreCase("")){
					regForm.setRegInitEstampCode(eCode);	
				}
				else{
					regForm.setRegInitEstampCode(null);
				}
				
				
			//}
			double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
			regForm.setTotalBalanceAmount(obj.format(balance));
			
			if(Double.parseDouble(regForm.getTotalBalanceAmount())<=0){
				regForm.setPaymentCompleteFlag(1);
				//regForm.setRegInitEstampCode(null);
			}else{
				//regForm.setRegInitEstampCode(null);
			}
			
			setDutyInForm(regForm,dutyListArr,languageLocale);
	/*		
		regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
		regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
		regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
		regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
		regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
		regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));*/
		            			
		regForm.setIsMultiplePropsFlag(0);
		regForm.setIsDutyCalculated(1);
		
		//if(adjuFlag==0){
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
				regForm.setRegPaid(Double.toString(0.0));
			
			if(regForm.getDeedID()!=RegInitConstant.DEED_POA){
			if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
				else
					regForm.setConsiAmountTrns(Double.toString(0.0));
			}else if(regForm.getInstID()==RegInitConstant.INST_POA_2){

				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					regForm.setConsiAmountTrns(dutyListArr[9].trim());
					else
						regForm.setConsiAmountTrns("-");
				
			}*/
			/*}
			else{
				regForm.setMarketValueShares(Double.toString(0.0));	
				regForm.setDutyPaid(Double.toString(0.0));
				regForm.setRegPaid(Double.toString(0.0));
			}*/
		
		}else if(numberOfProperties>1 && (dutyListArr==null)){
			
			regForm.setIsDutyCalculated(0);
			regForm.setIsMultiplePropsFlag(1);
			
		}else if(numberOfProperties>1 && dutyListArr!=null){
			
			regForm.setTotalPayableAmount(obj.format(Double.parseDouble(dutyListArr[5].trim())));
			double balance=Double.parseDouble(regForm.getTotalPayableAmount())-Double.parseDouble(regForm.getTotalPaidAmount());
			regForm.setTotalBalanceAmount(obj.format(balance));
			
			//below code for retrieving estamp code if total payable amount is more than zero
			if(Double.parseDouble(regForm.getTotalPayableAmount())>0.0)
			{
				String eCode=getEstampCode(regForm.getHidnRegTxnId());
				
				if(eCode!=null && !eCode.equalsIgnoreCase("") && !eCode.equalsIgnoreCase("null")){
					regForm.setRegInitEstampCode(eCode);	
				}
				else{
					regForm.setRegInitEstampCode(null);
				}
				
				
			}
			
			if(Double.parseDouble(regForm.getTotalBalanceAmount())==0){
				regForm.setPaymentCompleteFlag(1);
				//regForm.setRegInitEstampCode(null);
			}else{
				//regForm.setRegInitEstampCode(null);
			}
			
			regForm.setIsDutyCalculated(1);
			regForm.setIsMultiplePropsFlag(1);
			
			setDutyInForm(regForm,dutyListArr,languageLocale);
			/*regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
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
			
			if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
				regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
				else
					regForm.setConsiAmountTrns(Double.toString(0.0));
			if(regForm.getDeedID()!=RegInitConstant.DEED_POA){
				if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
					regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
					else
						regForm.setConsiAmountTrns(Double.toString(0.0));
				}else if(regForm.getInstID()==RegInitConstant.INST_POA_2){

					if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
						regForm.setConsiAmountTrns(dutyListArr[9].trim());
						else
							regForm.setConsiAmountTrns("-");
					
				}*/
			/*}
			else{
				regForm.setMarketValueShares(Double.toString(0.0));	
				regForm.setDutyPaid(Double.toString(0.0));
				regForm.setRegPaid(Double.toString(0.0));
			}*/
			
		}
		if(regForm.getDeedID()==RegInitConstant.DEED_EXCHANGE ||
				regForm.getDeedID()==RegInitConstant.DEED_PARTITION_PV){
			
			String finalMV=getExchangeDeedFinalMV(regForm.getHidnRegTxnId());
			
		regForm.setExchangeDeedMrktValue(obj.format(Double.parseDouble(finalMV)));
		}
		
		regForm.setRegInitPaymntTxnId(null);
		
		
	}
/**
 * getRemPropDetlsExchangeDeedDash
 * for getting remaining PROPERTY DETAILS from db for dashboard.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 *//*
public String[] getRemPropDetlsExchangeDeedDash(String valId) throws Exception {
	
	String[] strArray=null;
	ArrayList list=commonBd.getRemPropDetlsExchangeDeedDash(valId);
	if(list!=null && list.size()>0)
	{
	String str=list.toString();
	str=str.substring(2, str.length()-2);
	strArray=str.split(",");
	}
	return strArray;
}*/
/**
 * getAllValuationIdsExchangeDeed
 * for getting ALL VALUATION IDS from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getRemValuationIdsExchangeDeed(String finalValId) throws Exception {
	
	//String[] strArray=null;
	ArrayList list=commonBd.getRemValuationIdsExchangeDeed(finalValId);
	/*if(list!=null && list.size()>0)
	{
		strArray=new String[list.size()];
	for(int i=0;i<list.size();i++)
	{
	ArrayList row_list=(ArrayList)list.get(i);
	String str=row_list.toString();
	str=str.substring(1, str.length()-1);
	strArray[i]=str;
	}
	
	}*/
	return list;
}
/**
 * getDutyTxnId
 * for getting duty txn id from db
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getDutyTxnId(String appId) throws Exception {
	return commonBd.getDutyTxnId(appId);

}
/**
 * getApplicantAddress
 * for getting applicant district and address from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public String[] getApplicantAddress(String appId) throws Exception {
	String[] strArray=null;
	ArrayList list=commonBd.getApplicantAddress(appId);
	if(list!=null && list.size()>0)
	{
	String str=list.toString();
	str=str.substring(2, str.length()-2);
	strArray=str.split(",");
	}
	return strArray;
}
/**
 * insertTxnDetailsFinalAction
 * for inserting reg txn status from final action
 * @param String
 * @return boolean
 * @author ROOPAM
 */
public boolean insertTxnDetailsFinalAction(String appId) {
	return commonBd.insertTxnDetailsFinalAction(appId);
}
/**
 * updateBankDetails
 * Returns boolean value in order to check the updation of bank details.
 * @param form
 * @author Roopam
 * @return boolean
 * @throws Exception 
 *
 */
public boolean updateBankDetails(RegCommonForm form) throws Exception {
	return commonBd.updateBankDetails(form);
}
/**
 * getExtraDetls
 * for getting details like bank, trust from db.
 * @param RegCommonForm
 * @author Roopam
 * @return 
 * @throws Exception 
 *
 */
public void getExtraDetls(RegCommonForm regForm,String languageLocale) throws Exception{
	//below code for getting extra details
	String[] extraArr=getBankDetails(regForm.getHidnRegTxnId());
	
	if(extraArr!=null){
		
		if(regForm.getDeedID()==RegInitConstant.DEED_DEPOSIT_OF_TITLE){
		regForm.setBankName(extraArr[0].trim());
		regForm.setBranchName(extraArr[1].trim());
		regForm.setBankAddress(extraArr[2].trim().replace(RegInitConstant.SPLIT_CONSTANT,","));
		regForm.setBankAuthPer(extraArr[3].trim());
		regForm.setBankLoanAmt(Integer.parseInt(extraArr[4].trim()));
		regForm.setBankSancAmt(Integer.parseInt(extraArr[5].trim()));
		}else{

			regForm.setBankName("");
			regForm.setBranchName("");
			regForm.setBankAddress("");
			regForm.setBankAuthPer("");
			regForm.setBankLoanAmt(0);
			regForm.setBankSancAmt(0);
			
		}
		
		
		if(regForm.getDeedID()==RegInitConstant.DEED_TRUST || regForm.getDeedID()==RegInitConstant.DEED_TRUST_PV){
		regForm.setTrustName(extraArr[6].trim());
		if(extraArr[7].trim()!=null && !extraArr[7].trim().equalsIgnoreCase("") && !extraArr[7].trim().equalsIgnoreCase("null")){
		regForm.setTrustDate(convertDOB(extraArr[7].trim()));
		}else{
			regForm.setTrustDate("");
		}
		}else{

			regForm.setTrustName("");
			regForm.setTrustDate("");
			
			
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_LEASE_PV || regForm.getDeedID()==RegInitConstant.DEED_LEASE_NPV){
			if(extraArr[8].trim()!=null && !extraArr[8].trim().equalsIgnoreCase("") && !extraArr[8].trim().equalsIgnoreCase("null")){
			regForm.setRent(Double.parseDouble(extraArr[8].trim()));
			}else{
				regForm.setRent(0);
			}
			regForm.setAdvance(Double.parseDouble(extraArr[9].trim()));
			regForm.setDevlpmtCharge(Double.parseDouble(extraArr[10].trim()));
			
			if(extraArr[11].trim()!=null && !extraArr[11].trim().equalsIgnoreCase("") && !extraArr[11].trim().equalsIgnoreCase("null")){
			regForm.setOtherRecCharge(Double.parseDouble(extraArr[11].trim()));
			}else{
				regForm.setOtherRecCharge(0);
			}
			regForm.setPremium(Double.parseDouble(extraArr[12].trim()));
			regForm.setTermLease(Double.parseDouble(extraArr[13].trim()));
		}else{
			regForm.setRent(0);
			regForm.setAdvance(0);
			regForm.setDevlpmtCharge(0);
			regForm.setOtherRecCharge(0);
			regForm.setPremium(0);
			regForm.setTermLease(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_PV || regForm.getDeedID()==RegInitConstant.DEED_MORTGAGE_NPV ){
			regForm.setWithPos(extraArr[14].trim());
			if(extraArr[15].trim()!=null && !extraArr[15].trim().equalsIgnoreCase("") && !extraArr[15].trim().equalsIgnoreCase("null")){
			regForm.setSecLoanAmt(Double.parseDouble(extraArr[15].trim()));
			}else{
				regForm.setSecLoanAmt(0);
			}
		}else{
            regForm.setWithPos("-");
		    regForm.setSecLoanAmt(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_AWARD_PV || regForm.getDeedID()==RegInitConstant.DEED_SURRENDER_LEASE_NPV ){
			regForm.setFnameArb(extraArr[16].trim());
			if(extraArr[17].trim()!=null && !extraArr[17].trim().equalsIgnoreCase("") && !extraArr[17].trim().equalsIgnoreCase("null")){
				regForm.setMnameArb(extraArr[17].trim());
			}else{
				regForm.setMnameArb("-");
			}
			
			regForm.setLnameArb(extraArr[18].trim());
			regForm.setGendarArb(extraArr[19].trim());
			if(extraArr[19].trim().equalsIgnoreCase("M")){
				regForm.setGendarNameArb("Male");
			}else{
				regForm.setGendarNameArb("Female");
			}
			regForm.setAgeArb(extraArr[20].trim());
			regForm.setFatherNameArb(extraArr[21].trim());
			
			if(extraArr[22].trim()!=null && !extraArr[22].trim().equalsIgnoreCase("") && !extraArr[22].trim().equalsIgnoreCase("null")){
				regForm.setMotherNameArb(extraArr[22].trim());
			}else{
				regForm.setMotherNameArb("-");
			}
			
			
			if(extraArr[23].trim()!=null && !extraArr[23].trim().equalsIgnoreCase("") && !extraArr[23].trim().equalsIgnoreCase("null")){
				regForm.setSpouseNameArb(extraArr[23].trim());
			}
			else{
				regForm.setSpouseNameArb("-");
			}
			
			regForm.setNationalityArb(extraArr[24].trim());
			regForm.setIndaddressArb(extraArr[25].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			regForm.setIndcountryArb(extraArr[26].trim());
			regForm.setIndstatenameArb(extraArr[27].trim());
			regForm.setInddistrictArb(extraArr[28].trim());
			regForm.setInddistrictNameArb(getDistrictName(extraArr[28].trim(),languageLocale));
			regForm.setIndstatenameNameArb(getStateName(extraArr[27].trim(),languageLocale));
			regForm.setIndcountryNameArb(getCountryName(extraArr[26].trim(),languageLocale));
			
			if(extraArr[29].trim()!=null && !extraArr[29].trim().equalsIgnoreCase("") && !extraArr[29].trim().equalsIgnoreCase("null")){
				regForm.setIndphnoArb(extraArr[29].trim());
			}else{
				regForm.setIndphnoArb("-");
			}
			
			
			regForm.setIndmobnoArb(extraArr[30].trim());
			
			if(extraArr[31].trim()!=null && !extraArr[31].trim().equalsIgnoreCase("") && !extraArr[31].trim().equalsIgnoreCase("null")){
				regForm.setEmailIDArb(extraArr[31].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			}else{
				regForm.setEmailIDArb("-");
			}
			
			
			regForm.setIndCategoryArb(extraArr[32].trim());
			regForm.setIndCategoryNameArb(getCategoryName(extraArr[32].trim(),languageLocale));
			regForm.setIndDisabilityArb(extraArr[33].trim());
			if(extraArr[33].trim().equalsIgnoreCase("Y")){
				regForm.setIndDisabilityNameArb("Yes");
				regForm.setHdnIndDisabilityArb("Y");
			}else{
				regForm.setIndDisabilityNameArb("No");
				regForm.setHdnIndDisabilityArb("No");
			}
			regForm.setIndDisabilityDescArb(extraArr[34].trim());
			regForm.setListIDArb(extraArr[35].trim());
			regForm.setListIDNameArb(getPhotoIdProofName(extraArr[35].trim(),languageLocale));
			regForm.setIdnoArb(extraArr[36].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
			
		}else{
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
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_PV ||
				regForm.getDeedID()==RegInitConstant.DEED_AGREEMENT_MEMO_NPV ){
			regForm.setAdvance(Double.parseDouble(extraArr[9].trim()));
			regForm.setAdvancePaymntDate(convertDOB(extraArr[37].trim()));
			regForm.setPossGiven(extraArr[38].trim());
			if(extraArr[38].trim().equalsIgnoreCase("N")){
				regForm.setPossGivenName("No");
			}else{
				regForm.setPossGivenName("Yes");
			}
		}else{
			//regForm.setAdvance(0);
			regForm.setAdvancePaymntDate("");
			regForm.setPossGiven("N");
			regForm.setPossGivenName("");
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_POA ){
			regForm.setPoaWithConsid(extraArr[39].trim());
			regForm.setPoaPeriod(Double.parseDouble(extraArr[40].trim()));
			
		}else{
			regForm.setPoaWithConsid("");
			regForm.setPoaPeriod(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_RECONV_MORTGAGE_NPV ){
			
			regForm.setPaidLoanAmt(Double.parseDouble(extraArr[41].trim()));
		}else{
           
		    regForm.setPaidLoanAmt(0);
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV && 
					 (regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_1 || regForm.getInstID()==RegInitConstant.INST_PARTNERSHIP_NPV_2 )){
			
			if(extraArr[42].trim()!=null && !extraArr[42].trim().equalsIgnoreCase("") && !extraArr[42].trim().equalsIgnoreCase("null")){
			regForm.setContriProp(extraArr[42].trim());
			}else{
				regForm.setContriProp("-");
			}
		}else{
           
		    regForm.setContriProp("-");
		}
		
		if(regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_PV || (regForm.getDeedID()==RegInitConstant.DEED_PARTNERSHIP_NPV 
				&& regForm.getInstID()==RegInitConstant.INST_DISSOLUTION_NPV)){
			
			if(extraArr[43].trim()!=null && !extraArr[43].trim().equalsIgnoreCase("") && !extraArr[43].trim().equalsIgnoreCase("null")){
			regForm.setDissolutionDate(convertDOB(extraArr[43].trim()));
			}else{
				regForm.setDissolutionDate("");
			}
			if(extraArr[44].trim()!=null && !extraArr[44].trim().equalsIgnoreCase("") && !extraArr[44].trim().equalsIgnoreCase("null")){
				regForm.setRetirmentDate(convertDOB(extraArr[44].trim()));
				}else{
					regForm.setRetirmentDate("");
				}
			
		
		}else{

				regForm.setDissolutionDate("");
				regForm.setRetirmentDate("");
				
				
			}
		
		
	}else{
		regForm.setBankName("-");
		regForm.setBranchName("-");
		regForm.setBankAddress("-");
		regForm.setBankAuthPer("-");
		regForm.setBankLoanAmt(Integer.parseInt("0"));
		regForm.setBankSancAmt(Integer.parseInt("0"));
		regForm.setTrustName("-");
		regForm.setTrustDate("-");
		regForm.setWithPos("-");
		regForm.setSecLoanAmt(0);
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
		regForm.setAdvance(0);
		regForm.setAdvancePaymntDate("");
		regForm.setPossGiven("N");
		regForm.setPossGivenName("");
		regForm.setPoaWithConsid("");
		regForm.setPoaPeriod(0);
		regForm.setPaidLoanAmt(0);
		regForm.setContriProp("");
		regForm.setDissolutionDate("");
		regForm.setRetirmentDate("");
		
	}
//end of code for getting extra details
}
/**
 * copyPostalAddress
 * for copying applicant's address as postal address
 * @param RegCommonForm
 * @author Roopam
 * @return 
 * @throws Exception 
 *
 */
public void copyPostalAddress(RegCommonForm regForm) throws Exception{
	if(regForm.getPostalAddress2().equalsIgnoreCase("Y")){
			
			String[] strArr=getApplicantAddress(regForm.getHidnRegTxnId());
			if(strArr!=null){
				regForm.setPostalDistrict(strArr[0].trim());
 			regForm.setPostalAddress(strArr[1].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
 			regForm.setPostalCountry(strArr[2].trim());
 			regForm.setPostalState(strArr[3].trim());
			}else{
				regForm.setPostalDistrict("");
 			regForm.setPostalAddress("");
 			regForm.setPostalCountry("1");
 			regForm.setPostalState("");
			}
			
		}else{
			regForm.setPostalDistrict("");
			regForm.setPostalAddress("");
			regForm.setPostalCountry("1");
 			regForm.setPostalState("");
		}
}
/**
 * getPropertyListPVDeed
 * for getting PROPERTY ID list from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPropertyListPVDeed(ArrayList list) throws Exception {
	
	//return commonBd.getPropertyListNonPropDeed(appId);
	

	//ArrayList list=commonBd.getPropertyListNonPropDeed(appId);
	ArrayList finalList=new ArrayList();
	ArrayList row_list=new ArrayList();
	String row;
	String rowArr[];
	
	if(list != null && list.size()>0){
	for(int i=0;i<list.size();i++){
		row_list=(ArrayList)list.get(i);
		
		row=row_list.toString();
		row=row.substring(1, row.length()-1);
		rowArr=row.split(",");
		
		CommonDTO dto=new CommonDTO();
		
		if(rowArr[0].trim().length()==15){
			rowArr[0]="0"+rowArr[0].trim();
		}
		if(rowArr[0].trim().length()==15){
			dto.setId("0"+rowArr[0].trim());
		}else{
		dto.setId(rowArr[0].trim());
		}
		dto.setMarketValue(rowArr[1].trim());
		
		finalList.add(dto);
	}
	return finalList;
	}
	else{
		return null;
	}
	
	
}
/**
 * setOwnerOnPdf
 * for setting owner details on pdf
 * @param RegCommonDTO,Table
 * @return void
 * @author ROOPAM
 */
public void setOwnerOnPdf(RegCommonDTO mapDto,Table datatable) throws Exception {
	
	

	 
	  Cell ownerHdr=new Cell(new Phrase("Owner Details", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD)));	    			      
	  ownerHdr.setHeader(true);
	  ownerHdr.setColspan(22);
    datatable.addCell(ownerHdr);
	  
	  Cell ownerName=new Cell(new Phrase("Name(Individual/Authorized Person)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  ownerName.setHeader(true);
	  ownerName.setColspan(5);
    datatable.addCell(ownerName);
    Cell ownerNamevalue=new Cell(new Phrase(mapDto.getOwnerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerNamevalue.setHeader(true);
    ownerNamevalue.setColspan(6);
    datatable.addCell(ownerNamevalue);
    
    Cell ownerOrgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerOrgName.setHeader(true);
    ownerOrgName.setColspan(5);
    datatable.addCell(ownerOrgName);
    Cell ownerOrgNamevalue=new Cell(new Phrase(mapDto.getOwnerOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerOrgNamevalue.setHeader(true);
    ownerOrgNamevalue.setColspan(6);
    datatable.addCell(ownerOrgNamevalue);
    
    Cell ownerGender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerGender.setHeader(true);
    ownerGender.setColspan(5);
    datatable.addCell(ownerGender);
    Cell ownerGendervalue=new Cell(new Phrase(mapDto.getOwnerGendarTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerGendervalue.setHeader(true);
    ownerGendervalue.setColspan(6);
    datatable.addCell(ownerGendervalue);
    
    /*Cell ownerDob=new Cell(new Phrase("Date of Birth", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerDob.setHeader(true);
    ownerDob.setColspan(5);
    datatable.addCell(ownerDob);
    Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerDOBTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerDobvalue.setHeader(true);
    ownerDobvalue.setColspan(6);
    datatable.addCell(ownerDobvalue);*/
    
    Cell ownerDob=new Cell(new Phrase("Age", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerDob.setHeader(true);
    ownerDob.setColspan(5);
    datatable.addCell(ownerDob);
    Cell ownerDobvalue=new Cell(new Phrase(mapDto.getOwnerAgeTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerDobvalue.setHeader(true);
    ownerDobvalue.setColspan(6);
    datatable.addCell(ownerDobvalue);
    
    Cell ownerNationality=new Cell(new Phrase("Nationality", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerNationality.setHeader(true);
    ownerNationality.setColspan(8);
    datatable.addCell(ownerNationality);
    Cell ownerNationalityvalue=new Cell(new Phrase(mapDto.getOwnerNationalityTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerNationalityvalue.setHeader(true);
    ownerNationalityvalue.setColspan(14);
    datatable.addCell(ownerNationalityvalue);
    
    Cell ownerAddress=new Cell(new Phrase("Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerAddress.setHeader(true);
    ownerAddress.setColspan(8);
    datatable.addCell(ownerAddress);
    Cell ownerAddressvalue=new Cell(new Phrase(mapDto.getOwnerAddressTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerAddressvalue.setHeader(true);
    ownerAddressvalue.setColspan(14);
    datatable.addCell(ownerAddressvalue);
    
    Cell ownerMobile=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerMobile.setHeader(true);
    ownerMobile.setColspan(5);
    datatable.addCell(ownerMobile);
    Cell ownerMobilevalue=new Cell(new Phrase(mapDto.getOwnerPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerMobilevalue.setHeader(true);
    ownerMobilevalue.setColspan(6);
    datatable.addCell(ownerMobilevalue);
    
    Cell ownerEmail=new Cell(new Phrase("E-Mail", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerEmail.setHeader(true);
    ownerEmail.setColspan(5);
    datatable.addCell(ownerEmail);
    Cell ownerEmailvalue=new Cell(new Phrase(mapDto.getOwnerEmailIDTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerEmailvalue.setHeader(true);
    ownerEmailvalue.setColspan(6);
    datatable.addCell(ownerEmailvalue);
    
    Cell ownerProof=new Cell(new Phrase("Photo Proof Id", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerProof.setHeader(true);
    ownerProof.setColspan(5);
    datatable.addCell(ownerProof);
    Cell ownerProofvalue=new Cell(new Phrase(mapDto.getOwnerProofNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerProofvalue.setHeader(true);
    ownerProofvalue.setColspan(6);
    datatable.addCell(ownerProofvalue);
    
    Cell ownerProofId=new Cell(new Phrase("Photo Proof Id No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    ownerProofId.setHeader(true);
    ownerProofId.setColspan(5);
    datatable.addCell(ownerProofId);
    Cell ownerProofIdvalue=new Cell(new Phrase(mapDto.getOwnerIdnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    ownerProofIdvalue.setHeader(true);
    ownerProofIdvalue.setColspan(6);
    datatable.addCell(ownerProofIdvalue);
	  
	  
	  
 
	
	
	
}
/**
 * setDutyInForm
 * for setting duty details in form
 * @param String
 * @return void
 * @author ROOPAM
 */
public void setDutyInForm(RegCommonForm regForm, String[] dutyListArr, String languageLocale) throws Exception {
	
	NumberFormat obj=new DecimalFormat("#0.00");
	try
	{
		if(dutyListArr!=null)
		{
	regForm.setStampduty1(obj.format(Double.parseDouble(dutyListArr[0].trim())));
	regForm.setNagarPalikaDuty(obj.format(Double.parseDouble(dutyListArr[2].trim())));
	regForm.setPanchayatDuty(obj.format(Double.parseDouble(dutyListArr[1].trim())));
	regForm.setUpkarDuty(obj.format(Double.parseDouble(dutyListArr[3].trim())));
	regForm.setTotalduty(obj.format(Double.parseDouble(dutyListArr[5].trim())));
	regForm.setRegistrationFee(obj.format(Double.parseDouble(dutyListArr[4].trim())));
	
	//if(adjuFlag==0){
	if(dutyListArr[6].trim()!=null && !dutyListArr[6].trim().equalsIgnoreCase("") && !dutyListArr[6].trim().equalsIgnoreCase("null")){
	regForm.setMarketValueShares(obj.format(Double.parseDouble(dutyListArr[6].trim())));
	}
	else{
		regForm.setMarketValueShares(Double.toString(0.0));
	}
	
	if(dutyListArr[7].trim()!=null && !dutyListArr[7].trim().equalsIgnoreCase("") && !dutyListArr[7].trim().equalsIgnoreCase("null")){
	regForm.setDutyPaid(obj.format(Double.parseDouble(dutyListArr[7].trim())));
	}
	else{
		regForm.setDutyPaid(Double.toString(0.0));
	}
	
	if(dutyListArr[8].trim()!=null && !dutyListArr[8].trim().equalsIgnoreCase("") && !dutyListArr[8].trim().equalsIgnoreCase("null")){
	regForm.setRegPaid(obj.format(Double.parseDouble(dutyListArr[8].trim())));
	}
	else{
		regForm.setRegPaid(Double.toString(0.0));
	}
	
	/*if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase(""))
		regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));
		else
			regForm.setConsiAmountTrns(Double.toString(0.0));*/
	if(regForm.getDeedID()!=RegInitConstant.DEED_POA || regForm.getInstID()==RegInitConstant.INST_POA_1){
		if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase("") && !dutyListArr[9].trim().equalsIgnoreCase("null"))
			regForm.setConsiAmountTrns(obj.format(Double.parseDouble(dutyListArr[9].trim())));   //extra field set for all the deeds other than poa
			else
				regForm.setConsiAmountTrns(Double.toString(0.0));
		}else if(regForm.getInstID()==RegInitConstant.INST_POA_2){

			if(dutyListArr[9].trim()!=null && !dutyListArr[9].trim().equalsIgnoreCase("") && !dutyListArr[9].trim().equalsIgnoreCase("null"))
				regForm.setConsiAmountTrns(dutyListArr[9].trim());                               //number of persons for inst 2
				else
					regForm.setConsiAmountTrns("-");
			
		}
	}else{
		

		regForm.setStampduty1(obj.format(0));
		regForm.setNagarPalikaDuty(obj.format(0));
		regForm.setPanchayatDuty(obj.format(0));
		regForm.setUpkarDuty(obj.format(0));
		regForm.setTotalduty(obj.format(0));
		regForm.setRegistrationFee(obj.format(0));
		regForm.setMarketValueShares(Double.toString(0.0));	
		regForm.setDutyPaid(Double.toString(0.0));
		regForm.setRegPaid(Double.toString(0.0));
		regForm.setConsiAmountTrns(Double.toString(0.0));
			
		
		
		
		
	}
	
	regForm.setExtraFieldLabel(getExtraFieldLabel(Integer.toString(regForm.getInstID()),languageLocale));
	
}catch(Exception e){
	e.printStackTrace();
}
}
/**
 * setPropertyOnPdf
 * for setting property details on pdf
 * @param RegCommonDTO,Table
 * @return void
 * @author ROOPAM
 */
public void setPropertyOnPdf(RegCompletDTO dto1,Table datatable) throws Exception { 
	
	
	Cell propType=new Cell(new Phrase("Property Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propType.setHeader(true);
propType.setColspan(8);
datatable.addCell(propType);
Cell propTypevalue=new Cell(new Phrase(dto1.getPropertyTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propTypevalue.setHeader(true);
propTypevalue.setColspan(14);
datatable.addCell(propTypevalue);

Cell propAddress=new Cell(new Phrase("Property Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propAddress.setHeader(true);
propAddress.setColspan(8);
datatable.addCell(propAddress);
Cell propAddressvalue=new Cell(new Phrase(dto1.getPropAddress(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propAddressvalue.setHeader(true);
propAddressvalue.setColspan(14);
datatable.addCell(propAddressvalue);

Cell propLandmark=new Cell(new Phrase("Property Landmark", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propLandmark.setHeader(true);
propLandmark.setColspan(8);
datatable.addCell(propLandmark);
Cell propLandmarkvalue=new Cell(new Phrase(dto1.getPropLandmark(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propLandmarkvalue.setHeader(true);
propLandmarkvalue.setColspan(14);
datatable.addCell(propLandmarkvalue);

Cell propMohl=new Cell(new Phrase("Mohalla/Colony name/Society/Road/Gram", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propMohl.setHeader(true);
propMohl.setColspan(5);
datatable.addCell(propMohl);
Cell propMohlvalue=new Cell(new Phrase(dto1.getMohallaName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propMohlvalue.setHeader(true);
propMohlvalue.setColspan(6);
datatable.addCell(propMohlvalue);

Cell propWarkPatwari=new Cell(new Phrase("Ward / Patwari Halka", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propWarkPatwari.setHeader(true);
propWarkPatwari.setColspan(5);
datatable.addCell(propWarkPatwari);
Cell propWarkPatwarivalue=new Cell(new Phrase(dto1.getPatwariStatus(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propWarkPatwarivalue.setHeader(true);
propWarkPatwarivalue.setColspan(6);
datatable.addCell(propWarkPatwarivalue);

Cell propWarkPatwariNo=new Cell(new Phrase("ward Number/Patwari Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propWarkPatwariNo.setHeader(true);
propWarkPatwariNo.setColspan(5);
datatable.addCell(propWarkPatwariNo);
Cell propWarkPatwariNovalue=new Cell(new Phrase(dto1.getWardpatwarName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propWarkPatwariNovalue.setHeader(true);
propWarkPatwariNovalue.setColspan(6);
datatable.addCell(propWarkPatwariNovalue);

Cell propGovBody=new Cell(new Phrase("Governing Municipal Body", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propGovBody.setHeader(true);
propGovBody.setColspan(5);
datatable.addCell(propGovBody);
Cell propGovBodyvalue=new Cell(new Phrase(dto1.getMunicipalBodyName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propGovBodyvalue.setHeader(true);
propGovBodyvalue.setColspan(6);
datatable.addCell(propGovBodyvalue);

Cell propAreaType=new Cell(new Phrase("Type of Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propAreaType.setHeader(true);
propAreaType.setColspan(8);
datatable.addCell(propAreaType);
Cell propAreaTypevalue=new Cell(new Phrase(dto1.getAreaTypeName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propAreaTypevalue.setHeader(true);
propAreaTypevalue.setColspan(14);
datatable.addCell(propAreaTypevalue);

Cell propTehsil=new Cell(new Phrase("Tehsil", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propTehsil.setHeader(true);
propTehsil.setColspan(5);
datatable.addCell(propTehsil);
Cell propTehsilvalue=new Cell(new Phrase(dto1.getTehsilName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propTehsilvalue.setHeader(true);
propTehsilvalue.setColspan(6);
datatable.addCell(propTehsilvalue);

Cell propDist=new Cell(new Phrase("District", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propDist.setHeader(true);
propDist.setColspan(5);
datatable.addCell(propDist);
Cell propDistvalue=new Cell(new Phrase(dto1.getDistName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propDistvalue.setHeader(true);
propDistvalue.setColspan(6);
datatable.addCell(propDistvalue);

Cell propVikas=new Cell(new Phrase("Vikas Khand (development block)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propVikas.setHeader(true);
propVikas.setColspan(5);
datatable.addCell(propVikas);
Cell propVikasvalue=new Cell(new Phrase(dto1.getVikasId(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propVikasvalue.setHeader(true);
propVikasvalue.setColspan(6);
datatable.addCell(propVikasvalue);

Cell propRi=new Cell(new Phrase("R. I. Circle", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propRi.setHeader(true);
propRi.setColspan(5);
datatable.addCell(propRi);
Cell propRivalue=new Cell(new Phrase(dto1.getRicircle(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propRivalue.setHeader(true);
propRivalue.setColspan(6);
datatable.addCell(propRivalue);

Cell propLayout=new Cell(new Phrase("Layout Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propLayout.setHeader(true);
propLayout.setColspan(5);
datatable.addCell(propLayout);
Cell propLayoutvalue=new Cell(new Phrase(dto1.getLayoutdet(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propLayoutvalue.setHeader(true);
propLayoutvalue.setColspan(6);
datatable.addCell(propLayoutvalue);

Cell propNazool=new Cell(new Phrase("Nazool/Sheet Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propNazool.setHeader(true);
propNazool.setColspan(5);
datatable.addCell(propNazool);
Cell propNazoolvalue=new Cell(new Phrase(dto1.getSheetnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propNazoolvalue.setHeader(true);
propNazoolvalue.setColspan(6);
datatable.addCell(propNazoolvalue);

Cell propPlotNo=new Cell(new Phrase("Plot Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propPlotNo.setHeader(true);
propPlotNo.setColspan(8);
datatable.addCell(propPlotNo);
Cell propPlotNovalue=new Cell(new Phrase(dto1.getPlotnum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propPlotNovalue.setHeader(true);
propPlotNovalue.setColspan(14);
datatable.addCell(propPlotNovalue);

Cell khasraHdr=new Cell(new Phrase("Khasra Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
khasraHdr.setHeader(true);
khasraHdr.setColspan(22);
datatable.addCell(khasraHdr);

//Sr.No. Khasra Number  Area (sq mtr) Lagaan/Land Revenue(INR) RIN Pustika Number  

Cell srNo=new Cell(new Phrase("Sr.No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
srNo.setHeader(true);
srNo.setColspan(2);
datatable.addCell(srNo);

Cell khasraNo=new Cell(new Phrase("Khasra Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
khasraNo.setHeader(true);
khasraNo.setColspan(5);
datatable.addCell(khasraNo);

Cell khasraArea=new Cell(new Phrase("Khasra Area ("+dto1.getUnit()+")", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
khasraArea.setHeader(true);
khasraArea.setColspan(5);
datatable.addCell(khasraArea);

Cell lagaan=new Cell(new Phrase("Lagaan/Land Revenue(INR)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
lagaan.setHeader(true);
lagaan.setColspan(5);
datatable.addCell(lagaan);

Cell rinPustika=new Cell(new Phrase("RIN Pustika Number", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
rinPustika.setHeader(true);
rinPustika.setColspan(5);
datatable.addCell(rinPustika);

if(dto1.getKhasraDetlsDisplay()!=null && dto1.getKhasraDetlsDisplay().size()>0){
	  CommonDTO objDto;

    for(int m=0;m<dto1.getKhasraDetlsDisplay().size();m++){
  	  objDto=(CommonDTO)dto1.getKhasraDetlsDisplay().get(m);
  	  
  	  Cell srNovalue=new Cell(new Phrase(""+(m+1)+"", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  	  srNovalue.setHeader(true);
  	  srNovalue.setColspan(2);
  	  datatable.addCell(srNovalue);
  	  
  	  Cell khasraNovalue=new Cell(new Phrase(objDto.getKhasraNum(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  	  khasraNovalue.setHeader(true);
  	  khasraNovalue.setColspan(5);
  	  datatable.addCell(khasraNovalue);
  	  
  	  Cell khasraAreavalue=new Cell(new Phrase(objDto.getKhasraArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  	  khasraAreavalue.setHeader(true);
  	  khasraAreavalue.setColspan(5);
  	  datatable.addCell(khasraAreavalue);
  	  
  	  Cell lagaanvalue=new Cell(new Phrase(objDto.getLagaan(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  	  lagaanvalue.setHeader(true);
  	  lagaanvalue.setColspan(5);
  	  datatable.addCell(lagaanvalue);
  	  
  	  Cell rinPustikavalue=new Cell(new Phrase(objDto.getRinPustika(), FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  	  rinPustikavalue.setHeader(true);
  	  rinPustikavalue.setColspan(5);
  	  datatable.addCell(rinPustikavalue);
  	  
  	  
  	  
    }
}

Cell fourBoundryHdr=new Cell(new Phrase("Four Boundary Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
fourBoundryHdr.setHeader(true);
fourBoundryHdr.setColspan(22);
datatable.addCell(fourBoundryHdr);

Cell propNorth=new Cell(new Phrase("North", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propNorth.setHeader(true);
propNorth.setColspan(5);
datatable.addCell(propNorth);
Cell propNorthvalue=new Cell(new Phrase(dto1.getNorth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propNorthvalue.setHeader(true);
propNorthvalue.setColspan(6);
datatable.addCell(propNorthvalue);

Cell propSouth=new Cell(new Phrase("South", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propSouth.setHeader(true);
propSouth.setColspan(5);
datatable.addCell(propSouth);
Cell propSouthvalue=new Cell(new Phrase(dto1.getSouth(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propSouthvalue.setHeader(true);
propSouthvalue.setColspan(6);
datatable.addCell(propSouthvalue);

Cell propEast=new Cell(new Phrase("East", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propEast.setHeader(true);
propEast.setColspan(5);
datatable.addCell(propEast);
Cell propEastvalue=new Cell(new Phrase(dto1.getEast(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propEastvalue.setHeader(true);
propEastvalue.setColspan(6);
datatable.addCell(propEastvalue);

Cell propWest=new Cell(new Phrase("West", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propWest.setHeader(true);
propWest.setColspan(5);
datatable.addCell(propWest);
Cell propWestvalue=new Cell(new Phrase(dto1.getWest(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propWestvalue.setHeader(true);
propWestvalue.setColspan(6);
datatable.addCell(propWestvalue);

Cell row=new Cell(new Phrase("", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));
row.setHeader(true);
row.setColspan(22);
datatable.addCell(row);

if(dto1.getFloorCount()==0){
String area=dto1.getTotalSqMeterDisplay();

Cell propArea=new Cell(new Phrase("Area", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propArea.setHeader(true);
propArea.setColspan(8);
datatable.addCell(propArea);
Cell propAreavalue=new Cell(new Phrase(area+" "+dto1.getUnit(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
propAreavalue.setHeader(true);
propAreavalue.setColspan(14);
datatable.addCell(propAreavalue);
}


Cell propNoFloor=new Cell(new Phrase("Number of Constructed Floors", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
propNoFloor.setHeader(true);
propNoFloor.setColspan(8);
datatable.addCell(propNoFloor);
if(dto1.getFloorCount()==0){
	  Cell propNoFloorvalue=new Cell(new Phrase(RegInitConstant.NOT_APPLICABLE, FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    propNoFloorvalue.setHeader(true);
    propNoFloorvalue.setColspan(14);
    datatable.addCell(propNoFloorvalue);
}else{
	  Cell propNoFloorvalue=new Cell(new Phrase(Integer.toString(dto1.getFloorCount()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    propNoFloorvalue.setHeader(true);
    propNoFloorvalue.setColspan(14);
    datatable.addCell(propNoFloorvalue);
}

if(dto1.getFloorCount()==0){
	  
	  //subclause non building
	  if(dto1.getSelectedSubClauseList()!=null && dto1.getSelectedSubClauseList().size()>0){
		  
		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		  subClauseHdr.setHeader(true);
		  subClauseHdr.setColspan(22);
	      datatable.addCell(subClauseHdr);
	      
	     for(int n=0;n<dto1.getSelectedSubClauseList().size();n++){
	    	 
	    	 CommonDTO subClausedto = new CommonDTO();
	    	 subClausedto=(CommonDTO)dto1.getSelectedSubClauseList().get(n);
	    	 Cell subClauseValue=new Cell(new Phrase(subClausedto.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	    	 subClauseValue.setHeader(true);
	    	 subClauseValue.setColspan(22);
		         datatable.addCell(subClauseValue);
	    	 
	    	 
	    	 
	     }
		  
		  
	  }
	  
	  
	  
	  
	  
}else if(dto1.getFloorCount()>0){
	  //floor details
	  
	  	if(dto1.getMapBuilding()!=null && dto1.getMapBuilding().size()>0){
	  		
	  		Cell floorHdr=new Cell(new Phrase("Floor Details", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  		floorHdr.setHeader(true);
	  		floorHdr.setColspan(22);
	      datatable.addCell(floorHdr);
	  		Collection mapCollection2=dto1.getMapBuilding().values();
	      Object[] l2=mapCollection2.toArray();
          
	  		RegCompletDTO floordto;
		   for(int p=0;p<l2.length;p++){
	    	 
			   floordto = new RegCompletDTO();
			   floordto=(RegCompletDTO)l2[p];
	    	 
	    	 Cell buildingType=new Cell(new Phrase("Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	    	 buildingType.setHeader(true);
	    	 buildingType.setColspan(5);
		      	 datatable.addCell(buildingType);
	    	 Cell buildingTypeValue=new Cell(new Phrase(floordto.getUsageBuilding(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	    	 buildingTypeValue.setHeader(true);
	    	 buildingTypeValue.setColspan(6);
		         datatable.addCell(buildingTypeValue);
		         
		         Cell roofType=new Cell(new Phrase("Sub-Type of Usage", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		         roofType.setHeader(true);
		         roofType.setColspan(5);
		         datatable.addCell(roofType);
		         Cell roofTypeValue=new Cell(new Phrase(floordto.getCeilingType(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		         roofTypeValue.setHeader(true);
		         roofTypeValue.setColspan(6);
		         datatable.addCell(roofTypeValue);
	      
		         Cell floorType=new Cell(new Phrase("Floor Type", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		         floorType.setHeader(true);
		         floorType.setColspan(5);
	      	 datatable.addCell(floorType);
	      	 Cell floorTypeValue=new Cell(new Phrase(floordto.getTypeFloorDesc(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	      	 floorTypeValue.setHeader(true);
	      	 floorTypeValue.setColspan(6);
	         datatable.addCell(floorTypeValue);
	         
	         Cell consdAmt=new Cell(new Phrase("Consideration Amount (Rs.)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	         consdAmt.setHeader(true);
	         consdAmt.setColspan(5);
	         datatable.addCell(consdAmt);
	         Cell consdAmtValue=new Cell(new Phrase(Double.toString(floordto.getConsiderAmt()), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	         consdAmtValue.setHeader(true);
	         consdAmtValue.setColspan(6);
	         datatable.addCell(consdAmtValue);
     
	         Cell floorTotArea=new Cell(new Phrase("Total Area (Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	         floorTotArea.setHeader(true);
	         floorTotArea.setColspan(5);
	         datatable.addCell(floorTotArea);
	         Cell floorTotAreaValue=new Cell(new Phrase(floordto.getTotalSqMeterDisplay(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	         floorTotAreaValue.setHeader(true);
	         floorTotAreaValue.setColspan(6);
	         datatable.addCell(floorTotAreaValue);
      
	         Cell floorConstArea=new Cell(new Phrase("Constructed Area(Sq. Meter)", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	         floorConstArea.setHeader(true);
	         floorConstArea.setColspan(5);
	         datatable.addCell(floorConstArea);
	         Cell floorConstAreaValue=new Cell(new Phrase(floordto.getConstructedArea(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
	         floorConstAreaValue.setHeader(true);
	         floorConstAreaValue.setColspan(6);
	         datatable.addCell(floorConstAreaValue);
	         
	         if(floordto.getSelectedSubClauseList()!=null && floordto.getSelectedSubClauseList().size()>0){
	    		  
	    		  Cell subClauseHdr=new Cell(new Phrase("Sub-Clauses", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	    		  subClauseHdr.setHeader(true);
	    		  subClauseHdr.setColspan(22);
			      datatable.addCell(subClauseHdr);
			      
			     for(int f=0;f<floordto.getSelectedSubClauseList().size();f++){
			    	 
			    	 CommonDTO subClausedto2 = new CommonDTO();
			    	 subClausedto2=(CommonDTO)floordto.getSelectedSubClauseList().get(f);
			    	 Cell subClauseValue=new Cell(new Phrase(subClausedto2.getName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
			    	 subClauseValue.setHeader(true);
			    	 subClauseValue.setColspan(22);
			         datatable.addCell(subClauseValue);
			    	 
			    	 
			    	 
			     }
	    		  
	    		  
	    	  }
	          
	    	 
	    	 
	    	 
	     }
		  
		  
	  }
	  
}
}
/**
 * for getting extra field label from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getExtraFieldLabel(String instId, String languageLocale) throws Exception {
	//RegCommonDAO dao = new RegCommonDAO();
	
	String val=commonBd.getExtraFieldLabel(instId,languageLocale);
	if(val!=null && !val.equalsIgnoreCase("") && !val.equalsIgnoreCase("null")){
		return val;
	}else{
		return RegInitConstant.EXTRA_FIELD_NOT_APPLICABLE;
	}
	
}
/**
 * for getting claimant flag  from db.
 * @param String
 * @return String[]
 * @author ROOPAM
 */
public String[] getClaimantFlag(String roleId) throws Exception {
	//RegCommonDAO dao = new RegCommonDAO();
	
	ArrayList list=commonBd.getClaimantFlag(roleId);
	
	if(list!=null & list.size()==1){
	
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		
		String[] strArr=str.split(",");
		
		return strArr;
		
		
		
	}else{
		String[] strArr={"0","0"};
		
		return strArr;
	}
	
	
}

/**
 * for getting user type id from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getUserTypeId(String userId) throws Exception {

	return commonBd.getUserTypeId(userId);

}
/**
 * getDistIdNameRU
 * for getting dist id name of registered user
 * @param String[]
 * @return String[]
 * @author ROOPAM
 */
public String[] getDistIdNameRU(String userId) throws Exception {
	
	ArrayList list = commonBd.getDistIdNameRU(userId);
	
	if(list!=null && list.size()==1){
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		
		String[] strArr=str.split(",");
		
		return strArr;
		
	}else{
		
		return null;
		
	}
	
	
	
	

}
/**
 * getDistIdNameOfficeNameDRS
 * for getting dist id name, office name of DRS
 * @param String[]
 * @return String[]
 * @author ROOPAM
 */
public String[] getDistIdNameOfficeNameDRS(String officeId) throws Exception {
	
	ArrayList list = commonBd.getDistIdNameOfficeNameDRS(officeId);
	
	if(list!=null && list.size()==1){
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		
		String[] strArr=str.split(",");
		
		return strArr;
		
	}else{
		
		return null;
		
	}
	
	
	
	

}

/**
 * getDistIdNameSP
 * for getting dist id name of SP
 * @param String[]
 * @return String[]
 * @author ROOPAM
 */
public String[] getDistIdNameSP(String userId) throws Exception {
	
	ArrayList list = commonBd.getDistIdNameSP(userId);
	
	if(list!=null && list.size()==1){
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		
		String[] strArr=str.split(",");
		
		return strArr;
		
	}else{
		
		return null;
		
	}
	
	
	

}
/**
 * for inserting registration adjudication duties details entered by DR in db.
 * @param RegCommonForm
 * @return boolean
 * @author SHREERAJ
 */
public boolean insertAdjuDuties (RegCommonForm regForm) throws Exception {
	return commonBd.insertAdjuDuties(regForm);
}

/**
 * for inserting registration adjudication duties details in db.
 * @param RegCommonForm
 * @return boolean
 * @author SHREERAJ
 */
/*public boolean insertAdjuDutiesSys (RegCommonForm regForm) throws Exception {
	return commonBd.insertAdjuDutiesSys(regForm);
}*/
/**
 * getDutyDetls
 * for getting duty details Adju from db.
 * @param String
 * @return ArrayList
 * @author SHREERAJ
 */
public String[] getDutyDetlsAdjuForConfirmation(String appId) throws Exception {
	
	String dutyListArr[]=null;
	ArrayList list=commonBd.getDutyDetlsAdjuForConfirmation(appId);
	String dutyList;
	if(list!=null && list.size()==1){
		//for(int i=0;i<1;i++){
	dutyList=list.toString();
	dutyList=dutyList.substring(2, dutyList.length()-2);
	dutyListArr=dutyList.split(",");
		//}
	}
	return dutyListArr;
}
/**
 * getCommonDeedFlag
 * for getting common deed flag from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public int getCommonDeedFlag(String deedId) throws Exception {
	
	int flag=0;
	String val=commonBd.getCommonDeedFlag(deedId);
	
	if(val!=null){
	if(val.equalsIgnoreCase("Y")){
		flag=1;
	}
	}
	
	return flag;
	}
/**
 * insertParticularsDetails
 * for inserting particular details db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public boolean insertParticularsDetails(HashMap map, String regId ,String userId) {
	
	
	
	return commonBd.insertParticularsDetails(map,regId,userId);
}
/**
 * getParticularList
 * for getting particular list from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getParticularList(String appId) throws Exception {
	
	//return commonBd.getPropertyListNonPropDeed(appId);
	

	ArrayList list=commonBd.getParticularList(appId);
	ArrayList finalList=new ArrayList();
	
	if(list != null && list.size()>0){
	for(int i=0;i<list.size();i++){
		ArrayList row_list=(ArrayList)list.get(i);
		
		String row=row_list.toString();
		row=row.substring(1, row.length()-1);
		String rowArr[]=row.split(",");
		
		CommonDTO dto=new CommonDTO();
		
		/*if(rowArr[0].trim().length()==15){
			rowArr[0]="0"+rowArr[0].trim();
		}
		if(rowArr[0].trim().length()==15){
			dto.setId("0"+rowArr[0].trim());
		}else{*/
		dto.setId(rowArr[0].trim());
		dto.setName(rowArr[1].trim());
		//}
		/*if(!(rowArr[1].trim().equals("")) && !(rowArr[1].trim().equals("null"))){
			dto.setName(rowArr[1].trim()+" "+rowArr[2].trim());
		}
		else if(!(rowArr[3].trim().equals("")) && !(rowArr[3].trim().equals("null"))){
			dto.setName(rowArr[3].trim());
		}
		dto.setId(rowArr[4].trim());
		dto.setRoleId(rowArr[5].trim());*/
		finalList.add(dto);
	}
	return finalList;
	}
	else{
		return null;
	}
	
	
}
/**
 * getParticularDetails
 * for getting particular details from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public void getParticularDetails(RegCommonForm regForm) throws Exception {
	
	
	ArrayList list=commonBd.getParticularDetails(regForm.getParticularTxnId());
	
	if(list!=null && list.size()>0){
		
		String str=list.toString();
		str=str.substring(2, str.length()-2);
		String strArr[]=str.split(",");
		regForm.setParticularName(strArr[0].trim());
		regForm.setParticularDesc(strArr[1].trim());
		//return strArr;
		
	}else{
		
		regForm.setParticularName("-");
		regForm.setParticularDesc("-");
		//return null;
	}
	
	//RegCommonDAO dao = new RegCommonDAO();
	//return commonBd.getBankDetails(appId);
}
/**
 * updateParticularDetails
 * for updating particular details in db
 * @param form
 * @return boolean
 * @author Shruti
 */
public boolean updateParticularDetails(RegCommonForm form) throws
Exception   
{
	return commonBd.updateParticularDetails(form);
}
/**
 * getParticularListPdf
 * for getting particular list for pdf from db.
 * @param String
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getParticularListPdf(String appId) throws Exception {
	
	//return commonBd.getPropertyListNonPropDeed(appId);
	

	ArrayList list=commonBd.getParticularList(appId);
	ArrayList finalList=new ArrayList();
	CommonDTO dto=new CommonDTO();
	String row;
	String rowArr[];
	ArrayList row_list=new ArrayList();
	if(list != null && list.size()>0){
	for(int i=0;i<list.size();i++){
		row_list=(ArrayList)list.get(i);
		
		row=row_list.toString();
		row=row.substring(1, row.length()-1);
		rowArr=row.split(",");
		
		dto=new CommonDTO();
		
	
		dto.setId(rowArr[0].trim());
		dto.setName(rowArr[1].trim());
		dto.setParticularDesc(rowArr[2].trim());
		
		finalList.add(dto);
	}
	return finalList;
	}
	else{
		return null;
	}
	
	
}
/**
 * getPartyDetailsForPDFCommonDeed
 * 
 * @param String regTxnId
 * @return ArrayList
 * @author ROOPAM
 */
public ArrayList getPartyDetailsForPDFCommonDeed(String regTxnId,String languageLocale) throws Exception {   // method not being used currently
	
	ArrayList partyList=new ArrayList();
	//RegCompletDTO dto;
	
	try{
	
			
			
			//BELOW CODE FOR GETTING LIST OF PARTY TXN IDS CORRESPONDING TO CURRENT PROPERTY ID IN LOOP
			
			String[] partyArr=getPartyTxnIdListTitleDeed(regTxnId);     
		
		if(partyArr!=null && partyArr.length>0){
			
			
			
			
			for(int j=0;j<partyArr.length;j++){
				
				ArrayList list=commonBd.getPartyDetailsCommonDeedsPdf(partyArr[j]);
				RegCommonDTO mapDto =new RegCommonDTO();
				
			if(list!=null && list.size()>0){
				
			//	for(int k=0;k<list.size();k++)
			//	{
				//ArrayList row_list=(ArrayList)list.get(k);
				String shareList=list.toString();
				shareList=shareList.substring(2, shareList.length()-2);
				
				String shareHolderArr[]=shareList.split(",");
				
				//code for insertion in map
		             
		             if(shareHolderArr[0].trim().length()==11){
		            	 shareHolderArr[0]="0"+shareHolderArr[0].trim();
		         		}
		         		else{
		         			shareHolderArr[0]=shareHolderArr[0].trim();
		         		}
		           
		             mapDto.setListAdoptedPartyTrns(shareHolderArr[1].trim());
		            
		             mapDto.setApplicantOrTransParty("Applicant");
		             mapDto.setPartyRoleTypeId(shareHolderArr[23].trim());
		             mapDto.setPartyTypeFlag("C");
		             
		            	 if(shareHolderArr[25].trim().equals("") || shareHolderArr[25].trim().equals("null"))
		            		mapDto.setRelationWithOwnerTrns("-");
		            	 else
		         	    mapDto.setRelationWithOwnerTrns(shareHolderArr[25].trim());
		            	 if(shareHolderArr[26].trim().equals("") || shareHolderArr[26].trim().equals("null")){
		            		mapDto.setShareOfPropTrns("Not Declared");
		            		mapDto.setHdnDeclareShareCheck("N");
		            	 }
		            	 else{
		         	    mapDto.setShareOfPropTrns(shareHolderArr[26].trim());
		         	   mapDto.setHdnDeclareShareCheck("Y");
		            	 }
		            	 mapDto.setSelectedCountry(shareHolderArr[8].trim());
		            	 mapDto.setSelectedCountryName(getCountryName(shareHolderArr[8].trim(),languageLocale));
		            	 mapDto.setSelectedState(shareHolderArr[9].trim());
		            	 mapDto.setSelectedStateName(getStateName(shareHolderArr[9].trim(),languageLocale));
		            	 mapDto.setSelectedDistrict(shareHolderArr[10].trim());
		            	 mapDto.setSelectedDistrictName(getDistrictName(shareHolderArr[10].trim(),languageLocale));
		            	 mapDto.setOrgaddressTrns(shareHolderArr[11].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 if(shareHolderArr[14].trim().equals("") || shareHolderArr[14].trim().equals("null"))
		            		 mapDto.setMobnoTrns("-"); 
		            	 else
		            	     mapDto.setMobnoTrns(shareHolderArr[14].trim());
		            	 if(shareHolderArr[13].trim().equals("") || shareHolderArr[13].trim().equals("null"))
		            		 mapDto.setPhnoTrns("-");
		            	 else
		            	     mapDto.setPhnoTrns(shareHolderArr[13].trim());
		            	
		            	     mapDto.setRoleName(shareHolderArr[47].trim());
		            	 mapDto.setPartyTypeTrns(shareHolderArr[27].trim());
		            	 
		            	 mapDto.setPartyTxnId(shareHolderArr[23].trim());
		            	 
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.ORGANISATION_ID)){
		            	 //organization
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Organization");
		            	 mapDto.setOgrNameTrns(shareHolderArr[18].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setAuthPerNameTrns(shareHolderArr[19].trim());
		            	 mapDto.setIndAuthPersn(shareHolderArr[19].trim());
		            	/* mapDto.setOrgaddressTrns(shareHolderArr[11].trim());*/
		            	 mapDto.setFnameTrns("");
		           		 mapDto.setMnameTrns("");
		            	 mapDto.setLnameTrns("");
		            	 mapDto.setGendarTrns("-");
		            	 mapDto.setSelectedGender("-");
		            	 mapDto.setUserDOBTrns("");
		            	 mapDto.setAgeTrns("");
		               	 mapDto.setFatherNameTrns("-");
		            	 mapDto.setMotherNameTrns("");
		           		 mapDto.setSpouseNameTrns("");
		              	 mapDto.setNationalityTrns("");
		            	 mapDto.setPostalCodeTrns("");
		           		 mapDto.setEmailIDTrns("");
		            	 mapDto.setSelectedPhotoId("");
		            	 mapDto.setSelectedPhotoIdName("-");
		            	 mapDto.setIdnoTrns("-");
		            	
		            	 //mapDto.setIndReligionTrns("");
		            	 mapDto.setIndCategoryTrns("");
		            	 mapDto.setIndDisabilityTrns("");
		            	 mapDto.setIndDisabilityDescTrns("");
		            	 mapDto.setIndPovertyLineTrns("");
		            	 mapDto.setIndMinorityTrns("");
		            	 mapDto.setIndividualOrOrganization(RegInitConstant.ORGANISATION_ID);
		             }
		             
		             if(shareHolderArr[1].trim().equalsIgnoreCase(RegInitConstant.INDIVIDUAL_ID)){
		            	 //individual
		            	 
		            	 mapDto.setListAdoptedPartyNameTrns("Individual");
		            	 
		            	 mapDto.setFnameTrns(shareHolderArr[2].trim());
		            	 if(shareHolderArr[3].trim().equals("") || shareHolderArr[3].trim().equals("null"))
		            		 mapDto.setMnameTrns("");
		            	 else
		            	     mapDto.setMnameTrns(shareHolderArr[3].trim());
		            	 mapDto.setLnameTrns(shareHolderArr[4].trim());
		            	 mapDto.setGendarTrns(shareHolderArr[5].trim());
		            	 if(shareHolderArr[5].trim().equalsIgnoreCase("m"))
		            		 mapDto.setSelectedGender("Male");
		            	 else
		            		 mapDto.setSelectedGender("Female");
		            	 /*if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setUserDOBTrns("-");
		            	 else
		            	     mapDto.setUserDOBTrns(convertDOBfromDb(shareHolderArr[6].trim()));*/
		            	 if(shareHolderArr[6].trim().equals("") || shareHolderArr[6].trim().equals("null"))
		            		 mapDto.setAgeTrns("-");
		            	 else
		            	     mapDto.setAgeTrns(shareHolderArr[6].trim());
		            	 mapDto.setFatherNameTrns(shareHolderArr[20].trim());
		            	 if(shareHolderArr[21].trim().equals("") || shareHolderArr[21].trim().equals("null"))
		            		 mapDto.setMotherNameTrns("-");
		            	 else
		            	     mapDto.setMotherNameTrns(shareHolderArr[21].trim());
		            	 
		            	 if(shareHolderArr[28].trim().equals("") || shareHolderArr[28].trim().equals("null"))
		            		 mapDto.setSpouseNameTrns("-");
		            	 else
		            	     mapDto.setSpouseNameTrns(shareHolderArr[28].trim());
		            	 mapDto.setNationalityTrns(shareHolderArr[7].trim());
		            	 
		            	 if(shareHolderArr[12].trim().equals("") || shareHolderArr[12].trim().equals("null"))
		            		 mapDto.setPostalCodeTrns("-");
		            	 else
		            	     mapDto.setPostalCodeTrns(shareHolderArr[12].trim());
		            	
		            	 if(shareHolderArr[15].trim().equals("") || shareHolderArr[15].trim().equals("null"))
		            		 mapDto.setEmailIDTrns("-");
		            	 else
		            	     mapDto.setEmailIDTrns(shareHolderArr[15].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setSelectedPhotoId(shareHolderArr[16].trim());
		            	 
		            	 mapDto.setSelectedPhotoIdName(getPhotoIdProofName(shareHolderArr[16].trim(),languageLocale));               
		            	 mapDto.setIdnoTrns(shareHolderArr[17].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOgrNameTrns("-");
		            	 mapDto.setAuthPerNameTrns("-");
		            	 mapDto.setIndAuthPersn(shareHolderArr[2].trim()+" "+shareHolderArr[4].trim());
		            	 
		               	 
		               	 if(shareHolderArr[22].trim().equals("") || shareHolderArr[22].trim().equals("null"))
		               	 {
		               		mapDto.setIndCategoryTrns("-");
		               	 }
		               	 else
		               	 {
		            	    mapDto.setIndCategoryTrns(shareHolderArr[22].trim());
		               	 }
		               	 
		               	 mapDto.setSelectedCategoryName(getCategoryName(shareHolderArr[22].trim(),languageLocale));
		               	 
		               	 if(shareHolderArr[24].trim().equals("") || shareHolderArr[24].trim().equals("null"))
		               	 {
		               		mapDto.setIndDisabilityTrns("-");
		               	 }
		               	 else if(shareHolderArr[24].trim().equalsIgnoreCase("Y") )
		               	 {
		            	    mapDto.setIndDisabilityTrns("Yes");
		            	    if(shareHolderArr[30].trim().equals("") || shareHolderArr[30].trim().equals("null")){
		            	    	mapDto.setIndDisabilityDescTrns("-");
		            	    }else{
		            	    	mapDto.setIndDisabilityDescTrns(shareHolderArr[30].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	    }
		               	 }
		               	else if(shareHolderArr[24].trim().equalsIgnoreCase("N") )
		               	{
		            	    mapDto.setIndDisabilityTrns("No");
		               	}
		               	 
		               	if(shareHolderArr[35].trim().equals("") || shareHolderArr[35].trim().equals("null")){
		               		mapDto.setIndPovertyLineTrns("-");}
		               	 else if(shareHolderArr[35].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndPovertyLineTrns("Yes");
		            	     }
		               	else if(shareHolderArr[35].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndPovertyLineTrns("No");}
		               	
		               	if(shareHolderArr[34].trim().equals("") || shareHolderArr[34].trim().equals("null")){
		               		mapDto.setIndMinorityTrns("-");}
		               	 else if(shareHolderArr[34].trim().equalsIgnoreCase("Y") ){
		            	    mapDto.setIndMinorityTrns("Yes");
		            	     }
		               	else if(shareHolderArr[34].trim().equalsIgnoreCase("N") ){
		            	    mapDto.setIndMinorityTrns("No");}
		               	mapDto.setIndividualOrOrganization(RegInitConstant.INDIVIDUAL_ID);
		               	mapDto.setSelectedOccupationName(getOccupationName(shareHolderArr[31].trim(),languageLocale));
		               	
		               	mapDto.setIndScheduleAreaTrns(shareHolderArr[36].trim());
		              	if(shareHolderArr[36].trim().equalsIgnoreCase("N")){
		              		mapDto.setIndScheduleAreaTrnsDisplay("No");
		              		
		              		if(shareHolderArr[36].trim().equalsIgnoreCase("") || shareHolderArr[36].trim().trim().equals("null")){
		              			mapDto.setPermissionNoTrns("-");
		              		}else{
		              			mapDto.setPermissionNoTrns(shareHolderArr[32].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              		}
		              		
		              		mapDto.setFilePathTrns(shareHolderArr[33].trim());
		              		if(shareHolderArr[33].trim().equalsIgnoreCase("") || shareHolderArr[33].trim().equalsIgnoreCase("null")){
		              		mapDto.setDocumentNameTrns("No");}
		              		else{
		              			mapDto.setDocumentNameTrns("Yes");}
		              		
		              	}else{
		              		mapDto.setIndScheduleAreaTrnsDisplay("Yes");
		              	}
		              	
		             }
		             //uploads
		             if(shareHolderArr[37].trim().equalsIgnoreCase("") || shareHolderArr[37].trim().equalsIgnoreCase("null")){
		              		mapDto.setU2DocumentNameTrns("No");}
		              		else{
		              			mapDto.setU2DocumentNameTrns("Yes");}
		             
		             //end of uploads
		             int roleId=Integer.parseInt(shareHolderArr[27].trim());
		             
		             String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
		             
		             if(Integer.parseInt(claimantArr[1].trim())==RegInitConstant.POA_HOLDER_FLAG)
		             {
		             	
		             	String ownerId=shareHolderArr[29].trim();
		             	ArrayList ownerList=commonBd.getOwnerDetls(ownerId);
		             	String ownerDetls=ownerList.toString();
		             	ownerDetls=ownerDetls.substring(2, ownerDetls.length()-2);
		             	String ownerDetlsArr[]=ownerDetls.split(",");
		             	
		            	 if(ownerDetlsArr[9].trim().equals("") || ownerDetlsArr[9].trim().equals("null"))
		            	 {
		              mapDto.setOwnerOgrNameTrns("-");
		              mapDto.setOwnerNameTrns(ownerDetlsArr[0].trim());
		              }
		              else
		              {
		              mapDto.setOwnerOgrNameTrns(ownerDetlsArr[9].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		              mapDto.setOwnerNameTrns(ownerDetlsArr[10].trim());
		            	}
		            	 if(ownerDetlsArr[1].trim().equalsIgnoreCase("f"))
		            	 mapDto.setOwnerGendarTrns("Female");
		            	 else
		                 mapDto.setOwnerGendarTrns("Male");	 
		            	 mapDto.setOwnerNationalityTrns(ownerDetlsArr[3].trim());
		            	 mapDto.setOwnerAddressTrns(ownerDetlsArr[4].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 mapDto.setOwnerPhnoTrns(ownerDetlsArr[5].trim());
		            	 if(ownerDetlsArr[6].trim().equals("") || ownerDetlsArr[6].trim().equals("null"))
		                   		mapDto.setOwnerEmailIDTrns("-");
		                   	 else
		                	    mapDto.setOwnerEmailIDTrns(ownerDetlsArr[6].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	
		            	 mapDto.setOwnerIdnoTrns(ownerDetlsArr[8].trim().replace(RegInitConstant.SPLIT_CONSTANT, ","));
		            	 //mapDto.setOwnerDOBTrns(convertDOBfromDb(ownerDetlsArr[2].trim()));
		            	 mapDto.setOwnerAgeTrns(ownerDetlsArr[2].trim());
		            	 mapDto.setOwnerListIDTrns(ownerDetlsArr[7].trim());
		            	 mapDto.setOwnerProofNameTrns(getPhotoIdProofName(ownerDetlsArr[7].trim(),languageLocale));
		             }
		              
		              
		             
		            
			//}
			
			}
			
			
			partyList.add(mapDto);
			
		}
		
		
		
		}else{
			throw new Exception();
		}
		
	
}catch(Exception e){
	e.printStackTrace();
}
	
	return partyList;
	
}
/**
 * updateAdjudicationFlag
 * for inserting registration adjudication duties details in db.
 * @param RegCommonForm
 * @return boolean
 * @author SHREERAJ
 */
public boolean updateAdjudicationFlag (String appId, String userId) throws Exception {
	return commonBd.updateAdjudicationFlag(appId,userId);

}
/**
 * refreshKhasraData
 * for refreshing khasra arraylist
 * @param RegCommonForm
 * @return boolean
 * @author SHREERAJ
 */
public void refreshKhasraData(ArrayList<CommonDTO> khasra,
		HttpServletRequest request) {
	try {
		/*
		 * SimpleDateFormat inputFormat = new SimpleDateFormat("ddMMMyyyy");
		 * SimpleDateFormat displayFormat = new
		 * SimpleDateFormat("dd/MM/yyyy"); inputFormat.setLenient(false);
		 * displayFormat.setLenient(false); String dateString;
		 */
		String[] khasraNum = request.getParameterValues("regcompletDto.khasaraNum1");
		String[] khasraArea = request.getParameterValues("regcompletDto.khasraArea1");
		String[] lagaan=request.getParameterValues("regcompletDto.lagaan");
		String[] rinPustika=request.getParameterValues("regcompletDto.rinpustikaNum");

		if (khasraNum == null) {
			khasraNum = new String[khasra.size()];
		}
		if (khasraArea == null) {
			khasraArea = new String[khasra.size()];
		}
		if (lagaan == null) {
			lagaan = new String[khasra.size()];
		}
		if (rinPustika == null) {
			rinPustika = new String[khasra.size()];
		}

		for (int iLoop = 0; iLoop < khasra.size(); iLoop++) {
			CommonDTO member = khasra.get(iLoop);
			member.setKhasraNum(khasraNum[iLoop] != null ? khasraNum[iLoop]
					.trim() : "");
			member.setRinPustika(rinPustika[iLoop] != null ? rinPustika[iLoop]
					.trim() : "");
			member.setKhasraArea(khasraArea[iLoop] != null ? khasraArea[iLoop]
					.trim() : "");
			member.setLagaan(lagaan[iLoop] != null ? lagaan[iLoop]
					.trim() : "");
		}
	} catch (Exception e) {
		//logger.error(e.getMessage(), e);
	}

}
/**
 * for getting all relationship list
 * @return ArrayList
 */
public ArrayList getRelationshipList(String gender, String languageLocale) {
	return commonBd.getRelationshipList(gender,languageLocale);
}
/**
 * for getting relationship name from db.
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getRelationshipName(String id,String languageLocale) throws Exception {
	return commonBd.getRelationshipName(id,languageLocale);
   }


/**
 * setComplianceListOnPdf
 * for setting compliance list on pdf
 * @param RegCommonDTO,Table
 * @return void
 * @author ROOPAM
 */
public void setComplianceListOnPdf(RegCommonDTO mapDto,Table datatable) throws Exception {
	
	

	 
	int roleId=Integer.parseInt(mapDto.getPartyTypeTrns());
	  //int claimantFlag=Integer.parseInt(getClaimantFlag(Integer.toString(roleId)));
	  
	  String[] claimantArr=getClaimantFlag(Integer.toString(roleId));
		int claimantFlag=Integer.parseInt(claimantArr[0].trim());



//BELOW CODE FOR EXECUTANT
		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_1)
	{
			
		Cell upl3=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF EXECUTANT", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		upl3.setHeader(true);
		upl3.setColspan(8);
		datatable.addCell(upl3);
		Cell upl3value=new Cell(new Phrase(mapDto.getU3DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		upl3value.setHeader(true);
		upl3value.setColspan(14);
		datatable.addCell(upl3value);

Cell upl4=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
upl4.setHeader(true);
upl4.setColspan(8);
datatable.addCell(upl4);
Cell upl4value=new Cell(new Phrase(mapDto.getU4DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
upl4value.setHeader(true);
upl4value.setColspan(14);
datatable.addCell(upl4value);

if(!mapDto.getSelectedPhotoId().equalsIgnoreCase("4")){
Cell upl10=new Cell(new Phrase("PAN OR FORM 60/61", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
upl10.setHeader(true);
upl10.setColspan(8);
datatable.addCell(upl10);
Cell upl10value=new Cell(new Phrase(mapDto.getU10DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
upl10value.setHeader(true);
upl10value.setColspan(14);
datatable.addCell(upl10value);
}

 		
	}
	//BELOW CODE FOR EXECUTANT POA HOLDER
	if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_2)
	{
		
		Cell upl5=new Cell(new Phrase("NOTARIZED AFFIDAVIT OF ATTORNEY", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
 		upl5.setHeader(true);
 		upl5.setColspan(8);
 		datatable.addCell(upl5);
 		Cell upl5value=new Cell(new Phrase(mapDto.getU5DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
 		upl5value.setHeader(true);
 		upl5value.setColspan(14);
 		datatable.addCell(upl5value);
    
    Cell upl6=new Cell(new Phrase("ATTORNEY PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    upl6.setHeader(true);
    upl6.setColspan(8);
    datatable.addCell(upl6);
    Cell upl6value=new Cell(new Phrase(mapDto.getU6DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    upl6value.setHeader(true);
    upl6value.setColspan(14);
    datatable.addCell(upl6value);
    
    Cell upl7=new Cell(new Phrase("EXECUTANT PHOT0", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    upl7.setHeader(true);
    upl7.setColspan(8);
  datatable.addCell(upl7);
  Cell upl7value=new Cell(new Phrase(mapDto.getU7DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  upl7value.setHeader(true);
  upl7value.setColspan(14);
  datatable.addCell(upl7value);
		
		
		/*mapDto.setU5DocumentNameTrns(regForm.getU5DocumentName());
 		mapDto.setU6DocumentNameTrns(regForm.getU6DocumentName());
 		mapDto.setU7DocumentNameTrns(regForm.getU7DocumentName());*/
  
  Cell officeName=new Cell(new Phrase("SR Office Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  officeName.setHeader(true);
  officeName.setColspan(8);
  datatable.addCell(officeName);
  Cell officeNamevalue=new Cell(new Phrase(mapDto.getSrOfficeNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  officeNamevalue.setHeader(true);
  officeNamevalue.setColspan(14);
  datatable.addCell(officeNamevalue);
  
  Cell poaRegno=new Cell(new Phrase("PoA Registration No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  poaRegno.setHeader(true);
  poaRegno.setColspan(5);
  datatable.addCell(poaRegno);
  Cell poaRegnovalue=new Cell(new Phrase(mapDto.getPoaRegNoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  poaRegnovalue.setHeader(true);
  poaRegnovalue.setColspan(6);
  datatable.addCell(poaRegnovalue);
  
  Cell poaRegDate=new Cell(new Phrase("PoA Registration Date", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  poaRegDate.setHeader(true);
  poaRegDate.setColspan(5);
  datatable.addCell(poaRegDate);
  Cell poaRegDatevalue=new Cell(new Phrase(mapDto.getDatePoaRegTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
  poaRegDatevalue.setHeader(true);
  poaRegDatevalue.setColspan(6);
  datatable.addCell(poaRegDatevalue);
 		
 		
 		/*mapDto.setSrOfficeNameTrns(regForm.getSrOfficeName());
 		mapDto.setPoaRegNoTrns(regForm.getPoaRegNo());
 		mapDto.setDatePoaRegTrns(commonBo.convertCalenderDate(regForm.getDatePoaReg()));*/
	}


//BELOW CODE FOR CLAIMANT
  		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_3)
  		{
  			
  			Cell upl8=new Cell(new Phrase("CLAIMANT PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			upl8.setHeader(true);
  			upl8.setColspan(8);
		        datatable.addCell(upl8);
		        Cell upl8value=new Cell(new Phrase(mapDto.getU8DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		        upl8value.setHeader(true);
		        upl8value.setColspan(14);
		        datatable.addCell(upl8value);
		      
		      if(!mapDto.getSelectedPhotoId().equalsIgnoreCase("4")){
		      Cell upl11=new Cell(new Phrase("PAN OR FORM 60/61", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
		      upl11.setHeader(true);
		      upl11.setColspan(8);
		      datatable.addCell(upl11);
		      Cell upl11value=new Cell(new Phrase(mapDto.getU11DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      upl11value.setHeader(true);
		      upl11value.setColspan(14);
		      datatable.addCell(upl11value);
		      }	
  			
  			
         		
  		}
     		
  		//BELOW CODE FOR CLAIMANT POA HOLDER
  		if(claimantFlag==RegInitConstant.CLAIMANT_FLAG_4)
  		{
     		
  			Cell upl9=new Cell(new Phrase("ATTORNEY PHOTO", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
  			upl9.setHeader(true);
  			upl9.setColspan(8);
		      datatable.addCell(upl9);
		      Cell upl9value=new Cell(new Phrase(mapDto.getU9DocumentNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
		      upl9value.setHeader(true);
		      upl9value.setColspan(14);
		      datatable.addCell(upl9value);
     		
     		
     		
  		}
	
	
}
/**
 * for getting ExecutantClaimant drop down for universal deeds
 * @author ROOPAM
 * @date 22 October 2013
 * @return ArrayList
 */
public ArrayList getExecutantClaimant(String languageLocale,int instId) {
	return commonBd.getExecutantClaimant(languageLocale,instId);
}
/**
 * for getting ExecutantClaimant name from db.
 * 
 * @param String
 * @return String
 * @author ROOPAM
 */
public String getExecutantClaimantName(String id) throws Exception {
	return commonBd.getExecutantClaimantName(id);
}
/**
 * @param String
 * @return String
 */
public String getCurrencyFormat(double amount) {
	NumberFormat formatter = new DecimalFormat("#0.00");
	return formatter.format(amount).toString();

}
/**
 * setCommonOrgOnPdf
 * for setting common organization details on pdf
 * @param RegCommonDTO,Table
 * @return void
 * @author ROOPAM
 */
public void setCommonOrgOnPdf(RegCommonDTO mapDto,Table datatable) throws Exception {
	
	
	Cell orgName=new Cell(new Phrase("Organization Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
	  orgName.setHeader(true);
	  orgName.setColspan(8);
    datatable.addCell(orgName);
    Cell orgNamevalue=new Cell(new Phrase(mapDto.getOgrNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    orgNamevalue.setHeader(true);
    orgNamevalue.setColspan(14);
    datatable.addCell(orgNamevalue);
    
    Cell authPerName=new Cell(new Phrase("Authorized Person Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    authPerName.setHeader(true);
    authPerName.setColspan(8);
    datatable.addCell(authPerName);
    Cell authPerNamevalue=new Cell(new Phrase(mapDto.getAuthPerNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    authPerNamevalue.setHeader(true);
    authPerNamevalue.setColspan(14);
    datatable.addCell(authPerNamevalue);
    
    
    Cell relation=new Cell(new Phrase("Relationship", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    relation.setHeader(true);
    relation.setColspan(5);
    datatable.addCell(relation);
    Cell relationvalue=new Cell(new Phrase(mapDto.getRelationshipNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    relationvalue.setHeader(true);
    relationvalue.setColspan(6);
    datatable.addCell(relationvalue);
 
    
    Cell fathername=new Cell(new Phrase("Father's/Husband's Name", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    fathername.setHeader(true);
    fathername.setColspan(5);
    datatable.addCell(fathername);
    Cell fathernamevalue=new Cell(new Phrase(mapDto.getFatherNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    fathernamevalue.setHeader(true);
    fathernamevalue.setColspan(6);
    datatable.addCell(fathernamevalue);
    
    
    
    Cell gender=new Cell(new Phrase("Gender", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    gender.setHeader(true);
    gender.setColspan(8);
    datatable.addCell(gender);
    Cell gendervalue=new Cell(new Phrase(mapDto.getSelectedGender(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    gendervalue.setHeader(true);
    gendervalue.setColspan(14);
    datatable.addCell(gendervalue);
    
                			      
    Cell address=new Cell(new Phrase("Organization Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    address.setHeader(true);
    address.setColspan(8);
    datatable.addCell(address);
    Cell addressvalue=new Cell(new Phrase(mapDto.getOrgaddressTrns()+", "+mapDto.getSelectedDistrictName()+", "+mapDto.getSelectedStateName()+", "+mapDto.getSelectedCountryName(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    addressvalue.setHeader(true);
    addressvalue.setColspan(14);
    datatable.addCell(addressvalue);
    
    Cell authAddress=new Cell(new Phrase("Authorized Person Address", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    authAddress.setHeader(true);
    authAddress.setColspan(8);
    datatable.addCell(authAddress);
    Cell authAddressvalue=new Cell(new Phrase(mapDto.getAuthPerAddressTrns()+", "+mapDto.getAuthPerDistrictNameTrns()+", "+mapDto.getAuthPerStatenameNameTrns()+", "+mapDto.getAuthPerCountryNameTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    authAddressvalue.setHeader(true);
    authAddressvalue.setColspan(14);
    datatable.addCell(authAddressvalue);
    
    Cell phNo=new Cell(new Phrase("Phone No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    phNo.setHeader(true);
    phNo.setColspan(5);
    datatable.addCell(phNo);
    Cell phNovalue=new Cell(new Phrase(mapDto.getPhnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    phNovalue.setHeader(true);
    phNovalue.setColspan(6);
    datatable.addCell(phNovalue);
    
    Cell mobNo=new Cell(new Phrase("Mobile No.", FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD)));	    			      
    mobNo.setHeader(true);
    mobNo.setColspan(5);
    datatable.addCell(mobNo);
    Cell mobNovalue=new Cell(new Phrase(mapDto.getMobnoTrns(), FontFactory.getFont(FontFactory.COURIER, 8, Font.NORMAL)));	    			      
    mobNovalue.setHeader(true);
    mobNovalue.setColspan(6);
    datatable.addCell(mobNovalue);
	
}

public String getUnitName(String proofId,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getUnitName(proofId,languageLocale);
}
public String getPropertyL1Name(String id,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getPropertyL1Name(id,languageLocale);
}
public String getPropertyL2Name(String id,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getPropertyL2Name(id,languageLocale);
}
public String getFloorName(String id,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getFloorName(id,languageLocale);
}
public String getAppleteName(String id,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getAppleteName(id,languageLocale);
}
public String getRemarks(String appId) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getRemarks(appId);
}
public String getOfficeName(String id,String languageLocale) throws Exception {
	// RegCommonDAO dao = new RegCommonDAO();
	return commonBd.getOfficeName(id,languageLocale);
}
}