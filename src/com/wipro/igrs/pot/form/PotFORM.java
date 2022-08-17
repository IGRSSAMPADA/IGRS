package com.wipro.igrs.pot.form;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.wipro.igrs.caveats.dto.CaveatsDTO;
import com.wipro.igrs.pot.dto.PhysicalStampDTO;
import com.wipro.igrs.pot.dto.PotDistrictDTO;
import com.wipro.igrs.pot.dto.potDTO;
/**
 * ===========================================================================
 * File           :   PotFORM.java
 * Description    :   Represents the DataForm for Public Officer Tool
 * Author         :   Gopi C
 * Created Date   :   Feb 08, 2008

 * ===========================================================================
 */
public class PotFORM extends ActionForm
{
	private String regNumber;
	private String estampCode;
	private String stampFeeDoc;
	private String districtName;
	private String stampRequired;

	private String language;
	private String exdate;
	private String CaseCh;
	private String checkRadio;
	//private String dataShow="N";
	//private String dataShow1="N";
	private String dataShow="";
	private String dataShow1="N";
	private String impundCheck;
	private String errorMsg;
	private String impoundShow="N";
	private String loggedOffice;
	
	private String pstampFirstName;
	private String pstampMiddleName;
	private String pstampLastName;
	private String pstampGender;
	private String  pstampMobileNo;
	private String pstampEmailId;
	private String pstampAddress;
	private String pstampPincode;
	private String pstampAge;
	private String pstampFathersName;
	private String pstampMothersName;
	private String pstampPhoneNo;
	private String pstampNoOfPersons;
	
	private String pstampFirstName1;
	private String pstampMiddleName1;
	private String pstampLastName1;
	private String pstampGender1;
	private String  pstampMobileNo1;
	private String pstampEmailId1;
	private String pstampAddress1;
	private String pstampPincode1;
	private String pstampAge1;
	private String pstampFathersName1;
	private String pstampMothersName1;
	private String pstampPhoneNo1;
	private String pstampNoOfPersons1;
	private String pageName;
	

	public String getPstampFirstName1() {
		return pstampFirstName1;
	}



	public void setPstampFirstName1(String pstampFirstName1) {
		this.pstampFirstName1 = pstampFirstName1;
	}



	public String getPstampMiddleName1() {
		return pstampMiddleName1;
	}



	public void setPstampMiddleName1(String pstampMiddleName1) {
		this.pstampMiddleName1 = pstampMiddleName1;
	}



	public String getPstampLastName1() {
		return pstampLastName1;
	}



	public void setPstampLastName1(String pstampLastName1) {
		this.pstampLastName1 = pstampLastName1;
	}



	public String getPstampGender1() {
		return pstampGender1;
	}



	public void setPstampGender1(String pstampGender1) {
		this.pstampGender1 = pstampGender1;
	}



	public String getPstampMobileNo1() {
		return pstampMobileNo1;
	}



	public void setPstampMobileNo1(String pstampMobileNo1) {
		this.pstampMobileNo1 = pstampMobileNo1;
	}



	public String getPstampEmailId1() {
		return pstampEmailId1;
	}



	public void setPstampEmailId1(String pstampEmailId1) {
		this.pstampEmailId1 = pstampEmailId1;
	}



	public String getPstampAddress1() {
		return pstampAddress1;
	}



	public void setPstampAddress1(String pstampAddress1) {
		this.pstampAddress1 = pstampAddress1;
	}



	public String getPstampPincode1() {
		return pstampPincode1;
	}



	public void setPstampPincode1(String pstampPincode1) {
		this.pstampPincode1 = pstampPincode1;
	}



	public String getPstampAge1() {
		return pstampAge1;
	}



	public void setPstampAge1(String pstampAge1) {
		this.pstampAge1 = pstampAge1;
	}



	public String getPstampFathersName1() {
		return pstampFathersName1;
	}



	public void setPstampFathersName1(String pstampFathersName1) {
		this.pstampFathersName1 = pstampFathersName1;
	}



	public String getPstampMothersName1() {
		return pstampMothersName1;
	}



	public void setPstampMothersName1(String pstampMothersName1) {
		this.pstampMothersName1 = pstampMothersName1;
	}



	public String getPstampPhoneNo1() {
		return pstampPhoneNo1;
	}



	public void setPstampPhoneNo1(String pstampPhoneNo1) {
		this.pstampPhoneNo1 = pstampPhoneNo1;
	}



	public String getPstampNoOfPersons1() {
		return pstampNoOfPersons1;
	}



	public void setPstampNoOfPersons1(String pstampNoOfPersons1) {
		this.pstampNoOfPersons1 = pstampNoOfPersons1;
	}


	private HashMap pstampDetails = new HashMap();
	public String getCaseCh() {
		return CaseCh;
	}



	public void setCaseCh(String caseCh) {
		CaseCh = caseCh;
	}



	public String getCaseIDCh() {
		return caseIDCh;
	}



	public void setCaseIDCh(String caseIDCh) {
		this.caseIDCh = caseIDCh;
	}



	public String getEstampCodech() {
		return estampCodech;
	}



	public void setEstampCodech(String estampCodech) {
		this.estampCodech = estampCodech;
	}



	public String getEstampAmountch() {
		return estampAmountch;
	}



	public void setEstampAmountch(String estampAmountch) {
		this.estampAmountch = estampAmountch;
	}


	private String caseIDCh;
	private String estampCodech;
	private String estampAmountch;
	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}


	private String actionName;
	private potDTO potDto=new potDTO();
	private PotDistrictDTO potDisDto=new PotDistrictDTO();

	  
	public PotDistrictDTO getPotDisDto() {
		return potDisDto;
	}



	public void setPotDisDto(PotDistrictDTO potDisDto) {
		this.potDisDto = potDisDto;
	}



	public potDTO getPotDto() {
		return potDto;
	}



	public void setPotDto(potDTO potDto) {
		this.potDto = potDto;
	}



	public String getActionName() {
		return actionName;
	}



	public void setActionName(String actionName) {
		this.actionName = actionName;
	}


	 private String caseNum;
	   private String caseStat;

	public String getCaseNum() {
		return caseNum;
	}



	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}



	public String getCaseStat() {
		return caseStat;
	}



	public void setCaseStat(String caseStat) {
		this.caseStat = caseStat;
	}

	private String poFirstName;
	private String poMiddleName;
	private String poLastName;
	public String getPoFirstName() {
		return poFirstName;
	}



	public void setPoFirstName(String poFirstName) {
		this.poFirstName = poFirstName;
	}



	public String getPoMiddleName() {
		return poMiddleName;
	}



	public void setPoMiddleName(String poMiddleName) {
		this.poMiddleName = poMiddleName;
	}



	public String getPoLastName() {
		return poLastName;
	}



	public void setPoLastName(String poLastName) {
		this.poLastName = poLastName;
	}


	private String dateOfObjection;
	//private String impound="";
	private String impound;
	private String poComments;
	private String status;
	private String drComments;
	private String firstName;
	private String middleName;
	private String lastName;
	private String caseOpend;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	private String transactionId;
	private String oldNew;
	private String oldRegStampDate;
	private String oldRegVolumeNo;
	private String oldRegBookNo;
	private String oldRegNo;
	private String oldRegDate;
	private String district;
	
	private String partyChk;
	public String getPartyChk() {
		return partyChk;
	}



	public void setPartyChk(String partyChk) {
		this.partyChk = partyChk;
	}



	//
    private ArrayList searchResultList;

	
	public ArrayList getSearchResultList() {
		return searchResultList;
	}



	public void setSearchResultList(ArrayList searchResultList) {
		this.searchResultList = searchResultList;
	}



	private String stampNumber;
	private String stampFee;
	private String stampDate;
	private String challanNumber;
	
	private String durationFrom;
	private String durationTo;
	private String temp;
	private String publicOfficerComments;
	private String rowCount;
	private String typeOfSlip;
	private ArrayList selectedItems=new ArrayList();
	
	
	private ArrayList physicalStamp = new ArrayList();
    private PhysicalStampDTO stampDTO = new PhysicalStampDTO();
	
    public void setPaymentItems(int index, PhysicalStampDTO vo)
	{
		if(index >= physicalStamp.size())
				{
			         physicalStamp.add(new PhysicalStampDTO());
			    }
		
		System.out.println("8888888888888888"+index);
		physicalStamp.set(index, vo);
	}
    
	
	
	public PhysicalStampDTO getPaymentItems(int index)
	{
		for(; index >= physicalStamp.size(); physicalStamp.add(new PhysicalStampDTO())) 
		{
			;
		}
		return (PhysicalStampDTO) physicalStamp.get(index);
	}
	
	public String getRegNumber()
	{
		return regNumber;
	}
	public void setRegNumber(String regNumber)
	{
		this.regNumber = regNumber;
	}
	public String getEstampCode()
	{
		return estampCode;
	}
	public void setEstampCode(String estampCode)
	{
		this.estampCode = estampCode;
	}
	public String getStampFeeDoc()
	{
		return stampFeeDoc;
	}
	public void setStampFeeDoc(String stampFeeDoc)
	{
		this.stampFeeDoc = stampFeeDoc;
	}
	public String getStampRequired()
	{
		return stampRequired;
	}
	public void setStampRequired(String stampRequired)
	{
		this.stampRequired = stampRequired;
	}
	public String getDateOfObjection()
	{
		return dateOfObjection;
	}
	public void setDateOfObjection(String dateOfObjection)
	{
		this.dateOfObjection = dateOfObjection;
	}
	public String getImpound()
	{
		return impound;
	}
	public void setImpound(String impound)
	{
		this.impound = impound;
	}
	public String getPoComments()
	{
		return poComments;
	}
	public void setPoComments(String poComments)
	{
		this.poComments = poComments;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getDrComments()
	{
		return drComments;
	}
	public void setDrComments(String drComments)
	{
		this.drComments = drComments;
	}
	public String getFirstName()
	{
		return firstName;
	}
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	public String getMiddleName()
	{
		return middleName;
	}
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public String getCaseOpend()
	{
		return caseOpend;
	}
	public void setCaseOpend(String caseOpend)
	{
		this.caseOpend = caseOpend;
	}
	public String getCreatedBy()
	{
		return createdBy;
	}
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}
	public String getCreatedDate()
	{
		return createdDate;
	}
	public void setCreatedDate(String createdDate)
	{
		this.createdDate = createdDate;
	}
	public String getUpdatedBy()
	{
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy)
	{
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate()
	{
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate)
	{
		this.updatedDate = updatedDate;
	}
	public String getTransactionId()
	{
		return transactionId;
	}
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	public String getOldNew()
	{
		return oldNew;
	}
	public void setOldNew(String oldNew)
	{
		this.oldNew = oldNew;
	}
	public String getOldRegStampDate()
	{
		return oldRegStampDate;
	}
	public void setOldRegStampDate(String oldRegStampDate)
	{
		this.oldRegStampDate = oldRegStampDate;
	}
	public String getOldRegVolumeNo()
	{
		return oldRegVolumeNo;
	}
	public void setOldRegVolumeNo(String oldRegVolumeNo)
	{
		this.oldRegVolumeNo = oldRegVolumeNo;
	}
	public String getOldRegBookNo()
	{
		return oldRegBookNo;
	}
	public void setOldRegBookNo(String oldRegBookNo)
	{
		this.oldRegBookNo = oldRegBookNo;
	}
	public String getOldRegNo()
	{
		return oldRegNo;
	}
	public void setOldRegNo(String oldRegNo)
	{
		this.oldRegNo = oldRegNo;
	}
	public String getOldRegDate()
	{
		return oldRegDate;
	}
	public void setOldRegDate(String oldRegDate)
	{
		this.oldRegDate = oldRegDate;
	}
	
	public String getDistrict()
	{
		return district;
	}
	public void setDistrict(String district)
	{
		this.district = district;
	}
	public String getStampNumber()
	{
		return stampNumber;
	}
	public void setStampNumber(String stampNumber)
	{
		this.stampNumber = stampNumber;
	}
	public String getStampFee()
	{
		return stampFee;
	}
	public void setStampFee(String stampFee)
	{
		this.stampFee = stampFee;
	}
	public String getStampDate()
	{
		return stampDate;
	}
	public void setStampDate(String stampDate)
	{
		this.stampDate = stampDate;
	}
	public String getChallanNumber()
	{
		return challanNumber;
	}
	public void setChallanNumber(String challanNumber)
	{
		this.challanNumber = challanNumber;
	}
	public String getDurationFrom()
	{
		return durationFrom;
	}
	public void setDurationFrom(String durationFrom)
	{
		this.durationFrom = durationFrom;
	}
	public String getDurationTo()
	{
		return durationTo;
	}
	public void setDurationTo(String durationTo)
	{
		this.durationTo = durationTo;
	}


	public ArrayList getPhysicalStamp()
	{
		return physicalStamp;
	}


	public ArrayList getSelectedItems() {
		return selectedItems;
	}



	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}



	public void setPhysicalStamp(ArrayList physicalStamp)
	{
		System.out.println("@#@@@"+physicalStamp.size());
		this.physicalStamp = physicalStamp;
	}


	public PhysicalStampDTO getStampDTO()
	{
		return stampDTO;
	}


	public void setStampDTO(PhysicalStampDTO stampDTO)
	{
		this.stampDTO = stampDTO;
	}



	public String getTemp()
	{
		return temp;
	}



	public void setTemp(String temp)
	{
		this.temp = temp;
	}



	


	/**
	 * @return the rowCount
	 */
	public String getRowCount()
	{
		return rowCount;
	}



	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(String rowCount)
	{
		this.rowCount = rowCount;
	}



	/**
	 * @return the publicOfficerComments
	 */
	public String getPublicOfficerComments()
	{
		return publicOfficerComments;
	}



	/**
	 * @param publicOfficerComments the publicOfficerComments to set
	 */
	public void setPublicOfficerComments(String publicOfficerComments)
	{
		this.publicOfficerComments = publicOfficerComments;
	}



	public String getTypeOfSlip() {
		return typeOfSlip;
	}



	public void setTypeOfSlip(String typeOfSlip) {
		this.typeOfSlip = typeOfSlip;
	}


	private String checkBoxPStamp;
	
	private String keysStringPStamp;
	
	private String checkRadioEP;
	
	private potDTO potDTO=new potDTO();

private String stampDutyNow;
	public potDTO getPotDTO() {
		return potDTO;
	}



	public void setPotDTO(potDTO potDTO) {
		this.potDTO = potDTO;
	}



	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}



	public String getDistrictName() {
		return districtName;
	}



	public void setExdate(String exdate) {
		this.exdate = exdate;
	}



	public String getExdate() {
		return exdate;
	}



	public void setCheckRadio(String checkRadio) {
		this.checkRadio = checkRadio;
	}



	public String getCheckRadio() {
		return checkRadio;
	}



	public void setDataShow(String dataShow) {
		this.dataShow = dataShow;
	}



	public String getDataShow() {
		return dataShow;
	}



	public void setImpundCheck(String impundCheck) {
		this.impundCheck = impundCheck;
	}



	public String getImpundCheck() {
		return impundCheck;
	}



	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}



	public String getErrorMsg() {
		return errorMsg;
	}



	public void setImpoundShow(String impoundShow) {
		this.impoundShow = impoundShow;
	}



	public String getImpoundShow() {
		return impoundShow;
	}



	public void setPstampDetails(HashMap pstampDetails) {
		this.pstampDetails = pstampDetails;
	}



	public HashMap getPstampDetails() {
		return pstampDetails;
	}



	public void setStampDutyNow(String stampDutyNow) {
		this.stampDutyNow = stampDutyNow;
	}



	public String getStampDutyNow() {
		return stampDutyNow;
	}



	public void setCheckBoxPStamp(String checkBoxPStamp) {
		this.checkBoxPStamp = checkBoxPStamp;
	}



	public String getCheckBoxPStamp() {
		return checkBoxPStamp;
	}



	public void setKeysStringPStamp(String keysStringPStamp) {
		this.keysStringPStamp = keysStringPStamp;
	}



	public String getKeysStringPStamp() {
		return keysStringPStamp;
	}



	public void setCheckRadioEP(String checkRadioEP) {
		this.checkRadioEP = checkRadioEP;
	}



	public String getCheckRadioEP() {
		return checkRadioEP;
	}



	public void setDataShow1(String dataShow1) {
		this.dataShow1 = dataShow1;
	}



	public String getDataShow1() {
		return dataShow1;
	}



	public void setLoggedOffice(String loggedOffice) {
		this.loggedOffice = loggedOffice;
	}



	public String getLoggedOffice() {
		return loggedOffice;
	}



	


	



	public void setPstampFirstName(String pstampFirstName) {
		this.pstampFirstName = pstampFirstName;
	}



	public String getPstampFirstName() {
		return pstampFirstName;
	}



	public void setPstampPhoneNo(String pstampPhoneNo) {
		this.pstampPhoneNo = pstampPhoneNo;
	}



	public String getPstampPhoneNo() {
		return pstampPhoneNo;
	}



	public void setPstampMothersName(String pstampMothersName) {
		this.pstampMothersName = pstampMothersName;
	}



	public String getPstampMothersName() {
		return pstampMothersName;
	}



	public void setPstampFathersName(String pstampFathersName) {
		this.pstampFathersName = pstampFathersName;
	}



	public String getPstampFathersName() {
		return pstampFathersName;
	}



	public void setPstampAge(String pstampAge) {
		this.pstampAge = pstampAge;
	}



	public String getPstampAge() {
		return pstampAge;
	}



	public void setPstampPincode(String pstampPincode) {
		this.pstampPincode = pstampPincode;
	}



	public String getPstampPincode() {
		return pstampPincode;
	}



	public void setPstampAddress(String pstampAddress) {
		this.pstampAddress = pstampAddress;
	}



	public String getPstampAddress() {
		return pstampAddress;
	}



	public void setPstampEmailId(String pstampEmailId) {
		this.pstampEmailId = pstampEmailId;
	}



	public String getPstampEmailId() {
		return pstampEmailId;
	}



	public void setPstampMobileNo(String pstampMobileNo) {
		this.pstampMobileNo = pstampMobileNo;
	}



	public String getPstampMobileNo() {
		return pstampMobileNo;
	}



	public void setPstampGender(String pstampGender) {
		this.pstampGender = pstampGender;
	}



	public String getPstampGender() {
		return pstampGender;
	}



	public void setPstampLastName(String pstampLastName) {
		this.pstampLastName = pstampLastName;
	}



	public String getPstampLastName() {
		return pstampLastName;
	}



	public void setPstampMiddleName(String pstampMiddleName) {
		this.pstampMiddleName = pstampMiddleName;
	}



	public String getPstampMiddleName() {
		return pstampMiddleName;
	}



	public void setPstampNoOfPersons(String pstampNoOfPersons) {
		this.pstampNoOfPersons = pstampNoOfPersons;
	}



	public String getPstampNoOfPersons() {
		return pstampNoOfPersons;
	}



	public void setPageName(String pageName) {
		this.pageName = pageName;
	}



	public String getPageName() {
		return pageName;
	}
	
	/*@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		// TODO Auto-generated method stub
		//super.reset(mapping, request);
		pstampNoOfPersons="";
		pstampNoOfPersons1="";
		potDto.setCodeStamps("");
		potDto.setDenominationStamps(0.0);
	}*/
	
	
	
//	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
//	{
//		ActionErrors errors = new ActionErrors();
//		
//		if(firstName == null || firstName.length() == 0)
//		{
//			ActionError error = new ActionError("firstNameForm.Msg");
//			errors.add("firstName",error);
//		}
//		
//		
//		return errors;
//		
//	}
	

	
	

}
