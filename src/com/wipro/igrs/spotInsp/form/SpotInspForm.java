/**
 * ===========================================================================
 * File           :   SpotInspForm.java
 * Description    :   Represents the Bean Class

 * Author         :   Pavani Param
 * Created Date   :   Apr 08,2008.

 * ===========================================================================
 */
package com.wipro.igrs.spotInsp.form;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.wipro.igrs.documentsearch.dto.DocumentSearchDTO;
import com.wipro.igrs.documentsearch.dto.PartyDetailsDTO;
import com.wipro.igrs.regcompletion.dto.RegCompletDTO;
import com.wipro.igrs.spotInsp.dto.SpotInspDTO;

public class SpotInspForm extends ActionForm {
	//--- START---SpotInspecion --View-- Details.
	private String actionName;
	private String question;
	private String viewType="RefId";
	private String applicationNumber;
	private String durFrom;
	private String durTo;
	private String status;
	private String language;
	private String saveInspStatus;// added by akansha
	//added by saurav
	private String loggedInOffice;
	private String loggedInOfficeType;
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private String clauseId;
	private String DeedId;
	private ArrayList<SpotInspDTO>   displayList;
	private ArrayList deedList;
	private ArrayList propertyList;
	private ArrayList tehsilList;
	private ArrayList<SpotInspDTO> subAreaList;
	private HashMap finalValues;
	private String currentSr;
	private HashMap viewList;
	private FormFile photo;
	private String photoCopyName;
	private String photCopyPath;
	private String oldguidelineValue;
	private ArrayList reinspectionList;
	private String checkBoxreassign;
	private String newguidelineValue;
	private String checkBoxData;
	private String wardIds;
	private ArrayList<SpotInspDTO> wardPatwariList;
	private SpotInspDTO dto = new SpotInspDTO();
	public String getOldguidelineValue() {
		return oldguidelineValue;
	}

	public void setOldguidelineValue(String oldguidelineValue) {
		this.oldguidelineValue = oldguidelineValue;
	}

	public String getNewguidelineValue() {
		return newguidelineValue;
	}

	public void setNewguidelineValue(String newguidelineValue) {
		this.newguidelineValue = newguidelineValue;
	}

	public String getPhotoCopyName() {
		return photoCopyName;
	}

	public void setPhotoCopyName(String photoCopyName) {
		this.photoCopyName = photoCopyName;
	}

	public String getCurrentSr() {
		return currentSr;
	}

	public void setCurrentSr(String currentSr) {
		this.currentSr = currentSr;
	}

	private HashMap<String,ArrayList<SpotInspDTO>> srFinalList;
	private String propertyId;
	private String propertyL2Id;
	private String subclauselist;
	private Object clauseIds;
	private String percentage;
	private String checkError;
	private String maintain;
	private ArrayList assignSpotList;
	private String colonyId;
	public ArrayList getAssignSpotList() {
		return assignSpotList;
	}

	public void setAssignSpotList(ArrayList assignSpotList) {
		this.assignSpotList = assignSpotList;
	}

	private ArrayList mainCountList;




	public ArrayList getMainCountList() {
		return mainCountList;
	}

	public void setMainCountList(ArrayList mainCountList) {
		this.mainCountList = mainCountList;
	}

	private String[] selectedClauses;
	public String[] getSelectedClauses() {
		return selectedClauses;
	}

	public void setSelectedClauses(String[] selectedClauses) {
		this.selectedClauses = selectedClauses;
	}

	public String getPropertyL2Id() {
		return propertyL2Id;
	}

	public void setPropertyL2Id(String propertyL2Id) {
		this.propertyL2Id = propertyL2Id;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public ArrayList getDeedList() {
		return deedList;
	}

	public void setDeedList(ArrayList deedList) {
		this.deedList = deedList;
	}

	private String user;
	private String foundStatus;
	private String applicationDate;
	private String selectionDate;
	private String referenceID;
	private String rangeId;
	private String zoneId;
	private String spotId;
	private String districtId;
	private String dateOfRequest;
	private String dateOfVisit;
	private String pageTitle;
	private String pageTitleView;
	private String zoneName;
	private String districtName;
	private String  regId;
	private String  caseId;
	private String  loggedInUserId;
	private String spotref;
	private String tehsilId;
	private String tehsilName;
	private String marketValue;
	private String regFee;
	private String stampDuty;
	private ArrayList mohallaList;
	private String mohallaName;
	private String mohallaId;

	public String getMohallaId() {
		return mohallaId;
	}

	public void setMohallaId(String mohallaId) {
		this.mohallaId = mohallaId;
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

	public ArrayList getAreaTypeList() {
		return areaTypeList;
	}

	public void setAreaTypeList(ArrayList areaTypeList) {
		this.areaTypeList = areaTypeList;
	}
	private String subAreaId;
	private String areaTypeId;
	private String areaTypeName;
	private ArrayList areaTypeList;
	private String  municipalCheckDisplay;
	private String municipalFlag;

	private HashMap<String, ArrayList<SpotInspDTO>> reinspMap;


	private ArrayList reassignFinalList;
	public ArrayList getWardList() {
		return wardList;
	}

	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}



	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardId() {
		return wardId;
	}

	public void setWardId(String wardId) {
		this.wardId = wardId;
	}

	private ArrayList wardList;
	private String wardName;
	private String wardId;


	private String wardPatwariId;

	//--- END---SpotInspecion --View-- Details.
	private DocumentSearchDTO dsearchdto=new DocumentSearchDTO();

	private PartyDetailsDTO partyDto = new PartyDetailsDTO();
	//added by Shreeraj
	private String orderDate;




	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getTehsilId() {
		return tehsilId;
	}

	public String getDeedId() {
		return DeedId;
	}

	public void setDeedId(String deedId) {
		DeedId = deedId;
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

	public String getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public String getRegFee() {
		return regFee;
	}

	public void setRegFee(String regFee) {
		this.regFee = regFee;
	}

	public String getStampDuty() {
		return stampDuty;
	}

	public void setStampDuty(String stampDuty) {
		this.stampDuty = stampDuty;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

	public String getFoundStatus() {
		return foundStatus;
	}

	public void setFoundStatus(String foundStatus) {
		this.foundStatus = foundStatus;
	}

	public String getSpotref() {
		return spotref;
	}

	public void setSpotref(String spotref) {
		this.spotref = spotref;
	}

	public String getLoggedInUserId() {
		return loggedInUserId;
	}

	public void setLoggedInUserId(String loggedInUserId) {
		this.loggedInUserId = loggedInUserId;
	}

	public PartyDetailsDTO getPartyDto() {
		return partyDto;
	}

	public void setPartyDto(PartyDetailsDTO partyDto) {
		this.partyDto = partyDto;
	}

	public DocumentSearchDTO getDsearchdto() {
		return dsearchdto;
	}

	public void setDsearchdto(DocumentSearchDTO dsearchdto) {
		this.dsearchdto = dsearchdto;
	}

	public String getPageTitleView() {
		return pageTitleView;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	public String getActionName() {
		return actionName;
	}



	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getRangeId() {
		return rangeId;
	}

	public void setRangeId(String rangeId) {
		this.rangeId = rangeId;
	}

	public void setPageTitleView(String pageTitleView) {
		this.pageTitleView = pageTitleView;
	}

	private ArrayList rangeList;
	private ArrayList zoneList;
	private ArrayList districtList;
	private ArrayList spotViewList;


	private ArrayList spotCriteriaList;
	private ArrayList assignedCriteriaList;
	private ArrayList srList;
	private ArrayList drList;
	private ArrayList assignList;
	private ArrayList spotCompViewList;
	private ArrayList spotSeList;
	private ArrayList spotCompList;
	private ArrayList clauseList;
	private ArrayList propertyL2List;


	public String getClauseId() {
		return clauseId;
	}

	public void setClauseId(String clauseId) {
		this.clauseId = clauseId;
	}

	public ArrayList getClauseList() {
		return clauseList;
	}

	public void setClauseList(ArrayList clauseList) {
		this.clauseList = clauseList;
	}

	private String    srName;
	private String    drName;
	private String    sroName;
	private String    doUpdte;
	private String    insSelDate;
	private String    insPlanDate;
	private String    insActDate;
	private String    reInsPlanDate;
	private String    reInsActDate;
	private String    chPropDet;
	private String    changeVal;
	private String    remarks;
	private String    reChangeVal;
	private String    reRemarks;
	private String    schApplNo;
	private String    compApplNo;
	private String 	drReqApplNo;
	private String    drViewApplNo;
	private String    srReqType;
	private String    srReason;
	private String    inspStatus;
	private String 	 assDistrict;
	private String    assSroName;
	private String    drRemarks;
	private String    drReason;
	private String    reqType;
	private String    siReason;
	private String    reqiDate;
	private String    propTxnId;
	private String    regTxnId;
	private String    userId;
	private String    inspRemarks;
	private String     offSro;
	private String    spotInspTxnId;
	private String 	district;
	private String    propId;
	private String     drResp;
	private String    nameofParty;
	private String srScheduleRemarks;
	private String propStatus;
	private String selectedClauseName;

	//----END--Registration Application Details


	public String getSelectedClauseName() {
		return selectedClauseName;
	}

	public void setSelectedClauseName(String selectedClauseName) {
		this.selectedClauseName = selectedClauseName;
	}

	public String getPropStatus() {
		return propStatus;
	}

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getReInsPlanDate() {
		return reInsPlanDate;
	}

	public void setReInsPlanDate(String reInsPlanDate) {
		this.reInsPlanDate = reInsPlanDate;
	}

	public String getReInsActDate() {
		return reInsActDate;
	}

	public void setReInsActDate(String reInsActDate) {
		this.reInsActDate = reInsActDate;
	}

	public String getReChangeVal() {
		return reChangeVal;
	}

	public void setReChangeVal(String reChangeVal) {
		this.reChangeVal = reChangeVal;
	}

	public String getReRemarks() {
		return reRemarks;
	}

	public void setReRemarks(String reRemarks) {
		this.reRemarks = reRemarks;
	}

	public ArrayList getSpotCompViewList() {
		return spotCompViewList;
	}

	public void setSpotCompViewList(ArrayList spotCompViewList) {
		this.spotCompViewList = spotCompViewList;
	}

	public ArrayList getAssignedCriteriaList() {
		return assignedCriteriaList;
	}

	public void setAssignedCriteriaList(ArrayList assignedCriteriaList) {
		this.assignedCriteriaList = assignedCriteriaList;
	}

	public ArrayList getSpotCriteriaList() {
		return spotCriteriaList;
	}

	public void setSpotCriteriaList(ArrayList spotCriteriaList) {
		this.spotCriteriaList = spotCriteriaList;
	}

	public ArrayList getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}

	public ArrayList getZoneList() {
		return zoneList;
	}

	public void setZoneList(ArrayList zoneList) {
		this.zoneList = zoneList;
	}

	public void setPropStatus(String propStatus) {
		this.propStatus = propStatus;
	}

	public String getSrScheduleRemarks() {
		return srScheduleRemarks;
	}

	public void setSrScheduleRemarks(String srScheduleRemarks) {
		this.srScheduleRemarks = srScheduleRemarks;
	}

	SpotInspDTO objSIDto= new SpotInspDTO();
	RegCompletDTO regcompletDto = new RegCompletDTO();



	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset( mapping, request);
	}

	//--To check Alphanucmeric characters
	/**
	 * Check  AlphNumeric values.
	 * @return boolean
	 * @throws Exception
	 */
	public boolean checkAlphaN(String strValue){
		boolean blnFlag = false;
		for (int  i = 1; i < strValue.length(); i++)
		{
			char ch = strValue.charAt(i);
			if((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))
			{
				blnFlag=true;
			}
			else
			{
				blnFlag = false;
				break;
			}
		}
		return blnFlag;
	}
	//--To check Numbers.

	public boolean checkNum(String strValue){
		boolean blnFlag = false;
		try
		{
			int i = Integer.parseInt(strValue);
		}
		catch(NumberFormatException e)
		{
			blnFlag = true;
		}
		blnFlag  =  false;
		return blnFlag;
	}


	public String getNameofParty() {
		return nameofParty;
	}

	public void setNameofParty(String nameofParty) {
		this.nameofParty = nameofParty;
	}


	/**
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}


	/**
	 * @return the referenceID
	 */
	public String getReferenceID() {
		return referenceID;
	}

	/**
	 * @param referenceID the referenceID to set
	 */
	public void setReferenceID(String referenceID) {
		this.referenceID = referenceID;
	}


	/**
	 * @return the dateOfRequest
	 */
	public String getDateOfRequest() {
		return dateOfRequest;
	}

	/**
	 * @param dateOfRequest the dateOfRequest to set
	 */
	public void setDateOfRequest(String dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	/**
	 * @return the dateOfVisit
	 */
	public String getDateOfVisit() {
		return dateOfVisit;
	}

	/**
	 * @param dateOfVisit the dateOfVisit to set
	 */
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	/**
	 * @return the durFrom
	 */
	public String getDurFrom() {
		return durFrom;
	}

	/**
	 * @param durFrom the durFrom to set
	 */
	public void setDurFrom(String durFrom) {
		this.durFrom = durFrom;
	}

	/**
	 * @return the durTo
	 */
	public String getDurTo() {
		return durTo;
	}

	/**
	 * @param durTo the durTo to set
	 */
	public void setDurTo(String durTo) {
		this.durTo = durTo;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}





	/**
	 * @return the applicationNumber
	 */
	public String getApplicationNumber() {
		return applicationNumber;
	}

	/**
	 * @param applicationNumber the applicationNumber to set
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * @return the applicationDate
	 */
	public String getApplicationDate() {
		return applicationDate;
	}

	/**
	 * @param applicationDate the applicationDate to set
	 */
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	/**
	 * @return the selectionDate
	 */
	public String getSelectionDate() {
		return selectionDate;
	}

	/**
	 * @param selectionDate the selectionDate to set
	 */
	public void setSelectionDate(String selectionDate) {
		this.selectionDate = selectionDate;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @return the spotViewList
	 */
	public ArrayList getSpotViewList() {
		return spotViewList;
	}

	/**
	 * @param spotViewList the spotViewList to set
	 */
	public void setSpotViewList(ArrayList spotViewList) {
		this.spotViewList = spotViewList;
	}

	/**
	 * @return the spotSeList
	 */
	public ArrayList getSpotSeList() {
		return spotSeList;
	}

	/**
	 * @param spotSeList the spotSeList to set
	 */
	public void setSpotSeList(ArrayList spotSeList) {
		this.spotSeList = spotSeList;
	}

	/**
	 * @return the spotCompList
	 */
	public ArrayList getSpotCompList() {
		return spotCompList;
	}

	/**
	 * @param spotCompList the spotCompList to set
	 */
	public void setSpotCompList(ArrayList spotCompList) {
		this.spotCompList = spotCompList;
	}

	/**
	 * @return the objSIDto
	 */
	public SpotInspDTO getObjSIDto() {
		return objSIDto;
	}

	/**
	 * @param objSIDto the objSIDto to set
	 */
	public void setObjSIDto(SpotInspDTO objSIDto) {
		this.objSIDto = objSIDto;
	}

	/**
	 * @return the srName
	 */
	public String getSrName() {
		return srName;
	}

	/**
	 * @param srName the srName to set
	 */
	public void setSrName(String srName) {
		this.srName = srName;
	}

	/**
	 * @return the sroName
	 */
	public String getSroName() {
		return sroName;
	}

	/**
	 * @param sroName the sroName to set
	 */
	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	/**
	 * @return the insSelDate
	 */
	public String getInsSelDate() {
		return insSelDate;
	}

	/**
	 * @param insSelDate the insSelDate to set
	 */
	public void setInsSelDate(String insSelDate) {
		this.insSelDate = insSelDate;
	}

	/**
	 * @return the insPlanDate
	 */
	public String getInsPlanDate() {
		return insPlanDate;
	}

	/**
	 * @param insPlanDate the insPlanDate to set
	 */
	public void setInsPlanDate(String insPlanDate) {
		this.insPlanDate = insPlanDate;
	}

	/**
	 * @return the insActDate
	 */
	public String getInsActDate() {
		return insActDate;
	}

	/**
	 * @param insActDate the insActDate to set
	 */
	public void setInsActDate(String insActDate) {
		this.insActDate = insActDate;
	}

	/**
	 * @return the chPropDet
	 */
	public String getChPropDet() {
		return chPropDet;
	}

	/**
	 * @param chPropDet the chPropDet to set
	 */
	public void setChPropDet(String chPropDet) {
		this.chPropDet = chPropDet;
	}

	/**
	 * @return the changeVal
	 */
	public String getChangeVal() {
		return changeVal;
	}

	/**
	 * @param changeVal the changeVal to set
	 */
	public void setChangeVal(String changeVal) {
		this.changeVal = changeVal;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the doUpdte
	 */
	public String getDoUpdte() {
		return doUpdte;
	}

	/**
	 * @param doUpdte the doUpdte to set
	 */
	public void setDoUpdte(String doUpdte) {
		this.doUpdte = doUpdte;
	}

	/**
	 * @return the schApplNo
	 */
	public String getSchApplNo() {
		return schApplNo;
	}

	/**
	 * @param schApplNo the schApplNo to set
	 */
	public void setSchApplNo(String schApplNo) {
		this.schApplNo = schApplNo;
	}

	/**
	 * @return the compApplNo
	 */
	public String getCompApplNo() {
		return compApplNo;
	}

	/**
	 * @param compApplNo the compApplNo to set
	 */
	public void setCompApplNo(String compApplNo) {
		this.compApplNo = compApplNo;
	}

	/**
	 * @return the drReqApplNo
	 */
	public String getDrReqApplNo() {
		return drReqApplNo;
	}

	/**
	 * @param drReqApplNo the drReqApplNo to set
	 */
	public void setDrReqApplNo(String drReqApplNo) {
		this.drReqApplNo = drReqApplNo;
	}

	/**
	 * @return the drRemarks
	 */
	public String getDrRemarks() {
		return drRemarks;
	}

	/**
	 * @param drRemarks the drRemarks to set
	 */
	public void setDrRemarks(String drRemarks) {
		this.drRemarks = drRemarks;
	}

	/**
	 * @return the drReason
	 */
	public String getDrReason() {
		return drReason;
	}

	/**
	 * @param drReason the drReason to set
	 */
	public void setDrReason(String drReason) {
		this.drReason = drReason;
	}

	/**
	 * @return the district
	 */
	public String getDistrict() {
		return district;
	}

	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district) {
		this.district = district;
	}

	/**
	 * @return the assSroName
	 */
	public String getAssSroName() {
		return assSroName;
	}

	/**
	 * @param assSroName the assSroName to set
	 */
	public void setAssSroName(String assSroName) {
		this.assSroName = assSroName;
	}

	/**
	 * @return the drViewApplNo
	 */
	public String getDrViewApplNo() {
		return drViewApplNo;
	}

	/**
	 * @param drViewApplNo the drViewApplNo to set
	 */
	public void setDrViewApplNo(String drViewApplNo) {
		this.drViewApplNo = drViewApplNo;
	}


	/**
	 * @return the inspStatus
	 */
	public String getInspStatus() {
		return inspStatus;
	}

	/**
	 * @param inspStatus the inspStatus to set
	 */
	public void setInspStatus(String inspStatus) {
		this.inspStatus = inspStatus;
	}

	/**
	 * @return the assDistrict
	 */
	public String getAssDistrict() {
		return assDistrict;
	}

	/**
	 * @param assDistrict the assDistrict to set
	 */
	public void setAssDistrict(String assDistrict) {
		this.assDistrict = assDistrict;
	}

	/**
	 * @return the srReqType
	 */
	public String getSrReqType() {
		return srReqType;
	}

	/**
	 * @param srReqType the srReqType to set
	 */
	public void setSrReqType(String srReqType) {
		this.srReqType = srReqType;
	}

	/**
	 * @return the srReason
	 */
	public String getSrReason() {
		return srReason;
	}

	/**
	 * @param srReason the srReason to set
	 */
	public void setSrReason(String srReason) {
		this.srReason = srReason;
	}
	/**
	 * @return the reqType
	 */
	public String getReqType() {
		return reqType;
	}

	/**
	 * @param reqType the reqType to set
	 */
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	/**
	 * @return the propTxnId
	 */
	public String getPropTxnId() {
		return propTxnId;
	}

	/**
	 * @param propTxnId the propTxnId to set
	 */
	public void setPropTxnId(String propTxnId) {
		this.propTxnId = propTxnId;
	}

	/**
	 * @return the regTxnId
	 */
	public String getRegTxnId() {
		return regTxnId;
	}

	/**
	 * @param regTxnId the regTxnId to set
	 */
	public void setRegTxnId(String regTxnId) {
		this.regTxnId = regTxnId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the inspRemarks
	 */
	public String getInspRemarks() {
		return inspRemarks;
	}

	/**
	 * @param inspRemarks the inspRemarks to set
	 */
	public void setInspRemarks(String inspRemarks) {
		this.inspRemarks = inspRemarks;
	}

	/**
	 * @return the offSro
	 */
	public String getOffSro() {
		return offSro;
	}

	/**
	 * @param offSro the offSro to set
	 */
	public void setOffSro(String offSro) {
		this.offSro = offSro;
	}

	/**
	 * @return the spotInspTxnId
	 */
	public String getSpotInspTxnId() {
		return spotInspTxnId;
	}

	/**
	 * @param spotInspTxnId the spotInspTxnId to set
	 */
	public void setSpotInspTxnId(String spotInspTxnId) {
		this.spotInspTxnId = spotInspTxnId;
	}

	/**
	 * @return the siReason
	 */
	public String getSiReason() {
		return siReason;
	}

	/**
	 * @param siReason the siReason to set
	 */
	public void setSiReason(String siReason) {
		this.siReason = siReason;
	}

	/**
	 * @return the propId
	 */
	public String getPropId() {
		return propId;
	}
	/**
	 * @param propId the propId to set
	 */
	public void setPropId(String propId) {
		this.propId = propId;
	}

	/**
	 * @return the regcompletDto
	 */
	public RegCompletDTO getRegcompletDto() {
		return regcompletDto;
	}

	public ArrayList getDrList() {
		return drList;
	}

	public void setDrList(ArrayList drList) {
		this.drList = drList;
	}

	/**
	 * @param regcompletDto the regcompletDto to set
	 */
	public void setRegcompletDto(RegCompletDTO regcompletDto) {
		this.regcompletDto = regcompletDto;
	}

	public String getDrResp() {
		return drResp;
	}

	public void setDrResp(String drResp) {
		this.drResp = drResp;
	}



	public ArrayList getSrList() {
		return srList;
	}

	public void setSrList(ArrayList srList) {
		this.srList = srList;
	}

	public ArrayList getAssignList() {
		return assignList;
	}

	public void setAssignList(ArrayList assignList) {
		this.assignList = assignList;
	}

	public ArrayList getRangeList() {
		return rangeList;
	}

	public void setRangeList(ArrayList rangeList) {
		this.rangeList = rangeList;
	}


	public ArrayList getPropertyL2List() {
		return propertyL2List;
	}

	public void setPropertyL2List(ArrayList propertyL2List) {
		this.propertyL2List = propertyL2List;
	}

	public String getReqiDate() {
		return reqiDate;
	}

	public void setReqiDate(String reqiDate) {
		this.reqiDate = reqiDate;
	}

	public ArrayList getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(ArrayList propertyList) {
		this.propertyList = propertyList;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSubclauselist() {
		return subclauselist;
	}

	public void setSubclauselist(String subclauselist) {
		this.subclauselist = subclauselist;
	}

	public Object getClauseIds() {
		return clauseIds;
	}

	public void setClauseIds(Object clauseIds) {
		this.clauseIds = clauseIds;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion() {
		return question;
	}

	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}

	public ArrayList getTehsilList() {
		return tehsilList;
	}

	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}

	public String getWardPatwariId() {
		return wardPatwariId;
	}

	public void setMohallaList(ArrayList mohallaList) {
		this.mohallaList = mohallaList;
	}

	public ArrayList getMohallaList() {
		return mohallaList;
	}

	public void setMohallaName(String mohallaName) {
		this.mohallaName = mohallaName;
	}

	public String getMohallaName() {
		return mohallaName;
	}

	public void setFinalValues(HashMap finalValues) {
		this.finalValues = finalValues;
	}

	public HashMap getFinalValues() {
		return finalValues;
	}

	public void setCheckError(String checkError) {
		this.checkError = checkError;
	}

	public String getCheckError() {
		return checkError;
	}

	public void setMaintain(String maintain) {
		this.maintain = maintain;
	}

	public String getMaintain() {
		return maintain;
	}

	public void setSrFinalList(HashMap<String,ArrayList<SpotInspDTO>> srFinalList) {
		this.srFinalList = srFinalList;
	}

	public HashMap<String,ArrayList<SpotInspDTO>> getSrFinalList() {
		return srFinalList;
	}

	public void setDisplayList(ArrayList<SpotInspDTO> displayList) {
		this.displayList = displayList;
	}

	public ArrayList<SpotInspDTO> getDisplayList() {
		return displayList;
	}

	public void setViewList(HashMap viewList) {
		this.viewList = viewList;
	}

	public HashMap getViewList() {
		return viewList;
	}

	public void setPhoto(FormFile photo) {
		this.photo = photo;
	}

	public FormFile getPhoto() {
		return photo;
	}

	public void setPhotCopyPath(String photCopyPath) {
		this.photCopyPath = photCopyPath;
	}

	public String getPhotCopyPath() {
		return photCopyPath;
	}

	public void setReinspectionList(ArrayList reinspectionList) {
		this.reinspectionList = reinspectionList;
	}

	public ArrayList getReinspectionList() {
		return reinspectionList;
	}

	public void setCheckBoxreassign(String checkBoxreassign) {
		this.checkBoxreassign = checkBoxreassign;
	}

	public String getCheckBoxreassign() {
		return checkBoxreassign;
	}

	public String getCheckBoxData() {
		return checkBoxData;
	}

	public void setCheckBoxData(String checkBoxData) {
		this.checkBoxData = checkBoxData;
	}

	public SpotInspDTO getDto() {
		return dto;
	}

	public void setDto(SpotInspDTO dto) {
		this.dto = dto;
	}

	public void setReinspMap(HashMap<String, ArrayList<SpotInspDTO>> reinspMap) {
		this.reinspMap = reinspMap;
	}

	public HashMap<String, ArrayList<SpotInspDTO>> getReinspMap() {
		return reinspMap;
	}

	public void setReassignFinalList(ArrayList reassignFinalList) {
		this.reassignFinalList = reassignFinalList;
	}

	public ArrayList getReassignFinalList() {
		return reassignFinalList;
	}
	/*	public  int id=0;
		public SpotInspDTO getSpotprop1(int index)
		{
			this.id = index;

			System.out.println(index);
			 return (SpotInspDTO) this.getObjSIDto().getPropList().get(index);



		}


		public SpotInspDTO getSpotprop2(int index)
		{

			try{
			SpotInspDTO a =  (SpotInspDTO) this.getObjSIDto().getPropList().get(this.id);
			return a.getFloorList().get(index);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return null;
			}

		public SpotInspDTO setSpotprop1(int index)
		{
			System.out.println(index);
			return dto;

		}*/

	public void setSubAreaList(ArrayList<SpotInspDTO> subAreaList) {
		this.subAreaList = subAreaList;
	}

	public ArrayList<SpotInspDTO> getSubAreaList() {
		return subAreaList;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setMunicipalCheckDisplay(String municipalCheckDisplay) {
		this.municipalCheckDisplay = municipalCheckDisplay;
	}

	public String getMunicipalCheckDisplay() {
		return municipalCheckDisplay;
	}

	public void setMunicipalFlag(String municipalFlag) {
		this.municipalFlag = municipalFlag;
	}

	public String getMunicipalFlag() {
		return municipalFlag;
	}

	public void setWardPatwariList(ArrayList<SpotInspDTO> wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}

	public ArrayList<SpotInspDTO> getWardPatwariList() {
		return wardPatwariList;
	}

	public void setWardIds(String wardIds) {
		this.wardIds = wardIds;
	}

	public String getWardIds() {
		return wardIds;
	}

	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}

	public String getColonyId() {
		return colonyId;
	}

	public void setSaveInspStatus(String saveInspStatus) {
		this.saveInspStatus = saveInspStatus;
	}

	public String getSaveInspStatus() {
		return saveInspStatus;
	}

	public String getLoggedInOffice() {
		return loggedInOffice;
	}

	public void setLoggedInOffice(String loggedInOffice) {
		this.loggedInOffice = loggedInOffice;
	}

	public String getLoggedInOfficeType() {
		return loggedInOfficeType;
	}

	public void setLoggedInOfficeType(String loggedInOfficeType) {
		this.loggedInOfficeType = loggedInOfficeType;
	}
	
	
}
