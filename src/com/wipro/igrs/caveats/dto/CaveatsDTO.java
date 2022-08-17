/**
 * Copyright (c) 2009-10 WIPRO INFOTECH. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of WIPRO
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with WIPRO.
 *
 * WIPRO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
 * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. WIPRO SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF
 * USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 *
 * Customer specific copyright notice - NA
 */
package com.wipro.igrs.caveats.dto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.struts.upload.FormFile;

import com.wipro.igrs.util.PropertiesFileReader;
/**
* 
* CaveatsDTO.java <br>
* CaveatsDTO <br>
* 
* @version 1.0
* 
* @author <b>WIPRO JAVA TEAM</b> <br>
*         Created on 12-Apr-2008 <br>
*         Last Modified on 28-Mar-2013
*/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CaveatsDTO implements Serializable {
/**
	 * 
	 */
	private String reqNum;
	private String language;
	private String valId;
	//start | santosh caveat changes
	private String sampadaDocFlag;
	private ArrayList<CaveatsDTO> tehsilList;
	private ArrayList<CaveatsDTO> areaList;
	private ArrayList<CaveatsDTO> subAreaList;
	private ArrayList<CaveatsDTO> wardPatwariList;
	private ArrayList<CaveatsDTO> colonyList;
	private ArrayList<CaveatsDTO> propertyTypeList;
	
	private String tehsilName;
	private String areaName;
	private String subAreaName;
	private String wardPatwariName;
	private String colonyName;
	private String propertyTypeName;
	private String subAreaWardMappingId;
	private String tehsilNameVal;
	

	private String areaNameVal;
	private String subAreaNameVal;
	private String wardPatwariNameVal;
	private String colonyNameVal;
	
	private String tehsilId;
	private String areaId;
	private String subAreaId;
	private String wardPatwariId;
	private String colonyId;
	private String propertyId;
	private String caveatApplicantName;
	private String propertyDetails;
	private String pdAmount;
	private String pblAmount;
	private String paidByUser;
	private String paymentDate;
	private String protestServFee;
	private String protestStatus;
	private String protestID;
	private String userId;
	private String durationFlag;
	private String protestIDFlag;
	private String createdDate;
	private String paymentFlag;
	private String releaseDocPath;
	private String propDistrictId;
	private String protestLogDistrict;
	private String bankChargeId;
	private String caveatStatus;
	private String caveatUploadedDoc;
	private String protestRelDistrict;
	
	  public String getProtestRelDistrict() {
		return protestRelDistrict;
	}
	public void setProtestRelDistrict(String protestRelDistrict) {
		this.protestRelDistrict = protestRelDistrict;
	}

	private String multiRegNumFlag;
	  private String reportUserTypeIG;
	 
		private String protestCourtDistrict;
		private String protestIdValues;
		
		public String getProtestCourtDistrict() {
			return protestCourtDistrict;
		}
		public void setProtestCourtDistrict(String protestCourtDistrict) {
			this.protestCourtDistrict = protestCourtDistrict;
		}
		public String getProtestIdValues() {
			return protestIdValues;
		}
		public void setProtestIdValues(String protestIdValues) {
			this.protestIdValues = protestIdValues;
		}

		

	public String getReportUserTypeIG() {
		return reportUserTypeIG;
	}
	public void setReportUserTypeIG(String reportUserTypeIG) {
		this.reportUserTypeIG = reportUserTypeIG;
	}
		public String getMultiRegNumFlag() {
			return multiRegNumFlag;
		}
		public void setMultiRegNumFlag(String multiRegNumFlag) {
			this.multiRegNumFlag = multiRegNumFlag;
		}

	public String getProtestLogDistrict() {
		return protestLogDistrict;
	}

	public void setProtestLogDistrict(String protestLogDistrict) {
		this.protestLogDistrict = protestLogDistrict;
	}

	public String getPropDistrictId() {
		return propDistrictId;
	}

	public void setPropDistrictId(String propDistrictId) {
		this.propDistrictId = propDistrictId;
	}

	public String getReleaseDocPath() {
		return releaseDocPath;
	}

	public void setReleaseDocPath(String releaseDocPath) {
		this.releaseDocPath = releaseDocPath;
	}

	public String getPaymentFlag() {
		return paymentFlag;
	}

	public void setPaymentFlag(String paymentFlag) {
		this.paymentFlag = paymentFlag;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getTehsilNameVal() {
		return tehsilNameVal;
	}

	public void setTehsilNameVal(String tehsilNameVal) {
		this.tehsilNameVal = tehsilNameVal;
	}

	public String getAreaNameVal() {
		return areaNameVal;
	}

	public void setAreaNameVal(String areaNameVal) {
		this.areaNameVal = areaNameVal;
	}

	public String getSubAreaNameVal() {
		return subAreaNameVal;
	}

	public void setSubAreaNameVal(String subAreaNameVal) {
		this.subAreaNameVal = subAreaNameVal;
	}

	public String getWardPatwariNameVal() {
		return wardPatwariNameVal;
	}

	public void setWardPatwariNameVal(String wardPatwariNameVal) {
		this.wardPatwariNameVal = wardPatwariNameVal;
	}

	public String getColonyNameVal() {
		return colonyNameVal;
	}

	public void setColonyNameVal(String colonyNameVal) {
		this.colonyNameVal = colonyNameVal;
	}
	
	
	public String getDurationFlag() {
		return durationFlag;
	}

	public void setDurationFlag(String durationFlag) {
		this.durationFlag = durationFlag;
	}

	public String getProtestIDFlag() {
		return protestIDFlag;
	}

	public void setProtestIDFlag(String protestIDFlag) {
		this.protestIDFlag = protestIDFlag;
	}
	
	private ArrayList<String> protestIdList;
	
	public ArrayList<String> getProtestIdList() {
		return protestIdList;
	}

	public void setProtestIdList(ArrayList<String> protestIdList) {
		this.protestIdList = protestIdList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProtestID() {
		return protestID;
	}

	public void setProtestID(String protestID) {
		this.protestID = protestID;
	}

	public String getProtestStatus() {
		return protestStatus;
	}

	public void setProtestStatus(String protestStatus) {
		this.protestStatus = protestStatus;
	}

	public String getProtestServFee() {
		return protestServFee;
	}

	public void setProtestServFee(String protestServFee) {
		this.protestServFee = protestServFee;
	}

	public String getPdAmount() {
		return pdAmount;
	}

	public void setPdAmount(String pdAmount) {
		this.pdAmount = pdAmount;
	}

	public String getPblAmount() {
		return pblAmount;
	}

	public void setPblAmount(String pblAmount) {
		this.pblAmount = pblAmount;
	}

	public String getPaidByUser() {
		return paidByUser;
	}

	public void setPaidByUser(String paidByUser) {
		this.paidByUser = paidByUser;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	public String getPropertyDetails() {
		return propertyDetails;
	}

	public void setPropertyDetails(String propertyDetails) {
		this.propertyDetails = propertyDetails;
	}

	public String getCaveatApplicantName() {
		return caveatApplicantName;
	}

	public void setCaveatApplicantName(String caveatApplicantName) {
		this.caveatApplicantName = caveatApplicantName;
	}

	public String getSubAreaWardMappingId() {
		return subAreaWardMappingId;
	}

	public void setSubAreaWardMappingId(String subAreaWardMappingId) {
		this.subAreaWardMappingId = subAreaWardMappingId;
	}
	
	public String getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(String tehsilId) {
		this.tehsilId = tehsilId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getWardPatwariId() {
		return wardPatwariId;
	}

	public void setWardPatwariId(String wardPatwariId) {
		this.wardPatwariId = wardPatwariId;
	}

	public String getColonyId() {
		return colonyId;
	}

	public void setColonyId(String colonyId) {
		this.colonyId = colonyId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	
	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSubAreaName() {
		return subAreaName;
	}

	public void setSubAreaName(String subAreaName) {
		this.subAreaName = subAreaName;
	}

	public String getWardPatwariName() {
		return wardPatwariName;
	}

	public void setWardPatwariName(String wardPatwariName) {
		this.wardPatwariName = wardPatwariName;
	}

	public String getColonyName() {
		return colonyName;
	}

	public void setColonyName(String colonyName) {
		this.colonyName = colonyName;
	}

	public String getPropertyTypeName() {
		return propertyTypeName;
	}

	public void setPropertyTypeName(String propertyTypeName) {
		this.propertyTypeName = propertyTypeName;
	}
	
	
	
	public ArrayList<CaveatsDTO> getTehsilList() {
		return tehsilList;
	}

	public void setTehsilList(ArrayList<CaveatsDTO> tehsilList) {
		this.tehsilList = tehsilList;
	}

	public ArrayList<CaveatsDTO> getAreaList() {
		return areaList;
	}

	public void setAreaList(ArrayList<CaveatsDTO> areaList) {
		this.areaList = areaList;
	}

	public ArrayList<CaveatsDTO> getSubAreaList() {
		return subAreaList;
	}

	public void setSubAreaList(ArrayList<CaveatsDTO> subAreaList) {
		this.subAreaList = subAreaList;
	}

	public ArrayList<CaveatsDTO> getWardPatwariList() {
		return wardPatwariList;
	}

	public void setWardPatwariList(ArrayList<CaveatsDTO> wardPatwariList) {
		this.wardPatwariList = wardPatwariList;
	}

	public ArrayList<CaveatsDTO> getColonyList() {
		return colonyList;
	}

	public void setColonyList(ArrayList<CaveatsDTO> colonyList) {
		this.colonyList = colonyList;
	}

	public ArrayList<CaveatsDTO> getPropertyTypeList() {
		return propertyTypeList;
	}

	public void setPropertyTypeList(ArrayList<CaveatsDTO> propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
	
	
	public String getSampadaDocFlag() {
		return sampadaDocFlag;
	}

	public void setSampadaDocFlag(String sampadaDocFlag) {
		this.sampadaDocFlag = sampadaDocFlag;
	}
	//end | santosh caveat changes
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	private HashMap mapPropertyTransPartyDisp = new HashMap();
	public String getReqNum() {
	return reqNum;
}

public void setReqNum(String reqNum) {
	this.reqNum = reqNum;
}

	private ArrayList pendingApps = new ArrayList();
	private ArrayList partial = new ArrayList();
	
	public ArrayList getPartial() {
		return partial;
	}

	public void setPartial(ArrayList partial) {
		this.partial = partial;
	}

	private ArrayList TransidDetails = new ArrayList();
	public ArrayList getTransidDetails() {
		return TransidDetails;
	}

	public void setTransidDetails(ArrayList transidDetails) {
		TransidDetails = transidDetails;
	}

	public ArrayList getPendingApps() {
	return pendingApps;
}

public void setPendingApps(ArrayList pendingApps) {
	this.pendingApps = pendingApps;
}

	private Object paidAmount;
	   private Object balanceAmount;
	   private Object transactionID;
	   private Object lastTransactionDate;
	   private Object docName;
	   private Object createDate;
	   private Object payableamount;
	   private String stampPaid;
	   private String loanPurpose;
	   private String mortgageType;
	   private String regDate;
	   private String sdocuNumber;
	
	   public String getStampPaid() {
		return stampPaid;
	}

	public void setStampPaid(String stampPaid) {
		this.stampPaid = stampPaid;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getMortgageType() {
		return mortgageType;
	}

	public void setMortgageType(String mortgageType) {
		this.mortgageType = mortgageType;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSdocuNumber() {
		return sdocuNumber;
	}

	public void setSdocuNumber(String sdocuNumber) {
		this.sdocuNumber = sdocuNumber;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	private String option;
	
	
	
	
	

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Object getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public Object getPayableamount() {
		return payableamount;
	}

	public void setPayableamount(Object payableamount) {
		this.payableamount = payableamount;
	}

	public Object getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Object paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Object getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Object balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Object getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Object transactionID) {
		this.transactionID = transactionID;
	}

	public Object getLastTransactionDate() {
		return lastTransactionDate;
	}

	public void setLastTransactionDate(Object lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public Object getDocName() {
		return docName;
	}

	public void setDocName(Object docName) {
		this.docName = docName;
	}

	private static final long serialVersionUID = 633008466299782271L;
	//    CaveatsDTO cdto = new CaveatsDTO();
    private String  typeOfCaveat;
    public String caveatId;
    private ArrayList caveatTypeList;
    private String  courtOrderDate;
    public String getCourtOrderDate() {
		return courtOrderDate;
	}

	public void setCourtOrderDate(String courtOrderDate) {
		this.courtOrderDate = courtOrderDate;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	private String  caseNum;
    private String  bankCourtName;
    private String  bankCourtRepName;
    private String  bankCourtAddress;
    private String  cityDistrict;
    private String  bankCourtCountry;
    private String countryId;
    private String countryName;
    private ArrayList countryMasterList;
    private String  bankCourtStateName;
    private String stateId;
    private String stateName;
    private ArrayList stateMasterList;
    private String districtId;
    private String districtName;
    //private String districtMasterList;
    private ArrayList districtMasterList;
    private String bankCourtPostalCode;
    private String  bankCourtPhoneNumber;
    private String  caveatDetails;
    private String  stayOrderNo;
    private String  stayOrderDetails;
    private String  stayOrderYesNo;
    private String  remarks;
    private String  registrationNumber;
    private String registrationNoSearch;
    private String  docUrl;
    private String documentName;
    private String  caveatsFormLog;
    private String  caveatsLogInsert;
    private String flag;
    private String caveatType;
   
	private ArrayList recordsBuffer=new ArrayList();
    private String caveatSorderStatus;
    private String loggedDate;
    private String reasonForRelease;
    private String remarksForRelease;
    private String releaseDate;
    private String fromDate;
    private String toDate;
    
    private String referenceID;
    private String referenceIDSearch;
    private String pinNumber;
    public String getPinFlag() {
		return pinFlag;
	}

	public void setPinFlag(String pinFlag) {
		this.pinFlag = pinFlag;
	}

	private String pinFlag;

    private String errorMsg;
    private String ottNumber;
    private String ottExpiryDate;
    private String ottStatus;
    private String ottDate;
    private int hidlistten;
    
    private String stayOrderStartDate;
    private String stayOrderUptoDate;
    
    // Getter Setter Variables for CAVEAT LOGGED BY BANK or INSTITUTE for ---> Caveats_Log_By_BankDetails.jsp
    private String nameOfInsti;
    private String addressOfInsti;
    private String contactnoOfInsti;
    private String emailOfInsti;
    private String nameOfBankPerson;
    private String mobOfBankPerson;
    private String emailOfBankPerson;
    private String loanAccountNumber;
    private String loanAmount;
    private String securedAmount;
    private String amountOfInstall;
    private String noOfInstallments;
    private String loanDueDate;
    private String loanStartDate;
    private String loanEndDate;
    private String bankLoggedDate;
    private String bankContactNo;
    private String bankEmail;
   

	// Over
    private String nameOfCourtPerson;
    private String registrationDate;
    private long serialNo;
    private String actionName;
    private String formName;
    private String loggedIn;
    private String propertyTxnId;
    private String propertyTypeId;
    private String propertyTypeLabel;
    private String uploaded_doc_path;
    
    public String getUploaded_doc_path() {
		return uploaded_doc_path;
	}

	public void setUploaded_doc_path(String uploaded_doc_path) {
		this.uploaded_doc_path = uploaded_doc_path;
	}

	private transient FormFile attachedDoc;
    private transient byte[] docContents;
    private String docFileSize;
    private ArrayList selectedItems;
    private ArrayList cloneSelectedItems;
    
    private transient FormFile releaseDoc;
    private transient byte[] releaseContents;
    private String releaseDocName;
    private String releaseFileSize;
    private String propertyTxnLock;
    private String caveatLabel;
    private String pinNumberInDB;
    private String caveatStayOrderFlag;
    
    ArrayList errorList = new ArrayList();
	
    public static ArrayList yesNoList;
    
	public CaveatsDTO(){
    	buildYesNoList();
    	
    }
	
	/**
	 * 
	 */
	private void buildYesNoList() {
		try {
    		if (yesNoList == null) {
				yesNoList = new ArrayList();
			}
			if (yesNoList.isEmpty()) {
				
				PropertiesFileReader property = PropertiesFileReader
				   	.getInstance("resources.igrs");
				property.getKeys();
				yesNoList.add(property.getValue("msg.yes"));
				yesNoList.add(property.getValue("msg.no"));
		
			}
		} catch (Exception e) {
		}
	}

	/**
	 * @return the typeOfCaveat
	 */
	public String getTypeOfCaveat() {
		return typeOfCaveat;
	}

	/**
	 * @param typeOfCaveat the typeOfCaveat to set
	 */
	public void setTypeOfCaveat(String typeOfCaveat) {
		this.typeOfCaveat = typeOfCaveat;
	}

	/**
	 * @return the caveatId
	 */
	public String getCaveatId() {
		return caveatId;
	}

	/**
	 * @param caveatId the caveatId to set
	 */
	public void setCaveatId(String caveatId) {
		this.caveatId = caveatId;
	}

	/**
	 * @return the caveatTypeList
	 */
	public ArrayList getCaveatTypeList() {
		return caveatTypeList;
	}

	/**
	 * @param caveatTypeList the caveatTypeList to set
	 */
	public void setCaveatTypeList(ArrayList caveatTypeList) {
		this.caveatTypeList = caveatTypeList;
	}

	/**
	 * @return the bankCourtName
	 */
	public String getBankCourtName() {
		return bankCourtName;
	}

	/**
	 * @param bankCourtName the bankCourtName to set
	 */
	public void setBankCourtName(String bankCourtName) {
		this.bankCourtName = bankCourtName;
	}

	/**
	 * @return the bankCourtRepName
	 */
	public String getBankCourtRepName() {
		return bankCourtRepName;
	}

	/**
	 * @param bankCourtRepName the bankCourtRepName to set
	 */
	public void setBankCourtRepName(String bankCourtRepName) {
		this.bankCourtRepName = bankCourtRepName;
	}

	/**
	 * @return the bankCourtAddress
	 */
	public String getBankCourtAddress() {
		return bankCourtAddress;
	}

	/**
	 * @param bankCourtAddress the bankCourtAddress to set
	 */
	public void setBankCourtAddress(String bankCourtAddress) {
		this.bankCourtAddress = bankCourtAddress;
	}

	/**
	 * @return the cityDistrict
	 */
	public String getCityDistrict() {
		return cityDistrict;
	}

	/**
	 * @param cityDistrict the cityDistrict to set
	 */
	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
	}

	/**
	 * @return the bankCourtCountry
	 */
	public String getBankCourtCountry() {
		return bankCourtCountry;
	}

	/**
	 * @param bankCourtCountry the bankCourtCountry to set
	 */
	public void setBankCourtCountry(String bankCourtCountry) {
		this.bankCourtCountry = bankCourtCountry;
	}

	/**
	 * @return the countryId
	 */
	public String getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryMasterList
	 */
	public ArrayList getCountryMasterList() {
		return countryMasterList;
	}

	/**
	 * @param countryMasterList the countryMasterList to set
	 */
	public void setCountryMasterList(ArrayList countryMasterList) {
		this.countryMasterList = countryMasterList;
	}

	/**
	 * @return the bankCourtStateName
	 */
	public String getBankCourtStateName() {
		return bankCourtStateName;
	}

	/**
	 * @param bankCourtStateName the bankCourtStateName to set
	 */
	public void setBankCourtStateName(String bankCourtStateName) {
		this.bankCourtStateName = bankCourtStateName;
	}

	/**
	 * @return the stateId
	 */
	public String getStateId() {
		return stateId;
	}

	/**
	 * @param stateId the stateId to set
	 */
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * @param stateName the stateName to set
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * @return the stateMasterList
	 */
	public ArrayList getStateMasterList() {
		return stateMasterList;
	}

	/**
	 * @param stateMasterList the stateMasterList to set
	 */
	public void setStateMasterList(ArrayList stateMasterList) {
		this.stateMasterList = stateMasterList;
	}

	/**
	 * @return the districtId
	 */
	public String getDistrictId() {
		return districtId;
	}

	/**
	 * @param districtId the districtId to set
	 */
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	/**
	 * @return the districtName
	 */
	public String getDistrictName() {
		return districtName;
	}

	/**
	 * @param districtName the districtName to set
	 */
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	/**
	 * @return the districtMasterList
	 */
	public ArrayList getDistrictMasterList() {
		return districtMasterList;
	}

	/**
	 * @param districtMasterList the districtMasterList to set
	 */
	public void setDistrictMasterList(ArrayList districtMasterList) {
		this.districtMasterList = districtMasterList;
	}

	/**
	 * @return the bankCourtPostalCode
	 */
	public String getBankCourtPostalCode() {
		return bankCourtPostalCode;
	}

	/**
	 * @param bankCourtPostalCode the bankCourtPostalCode to set
	 */
	public void setBankCourtPostalCode(String bankCourtPostalCode) {
		this.bankCourtPostalCode = bankCourtPostalCode;
	}

	/**
	 * @return the bankCourtPhoneNumber
	 */
	public String getBankCourtPhoneNumber() {
		return bankCourtPhoneNumber;
	}

	/**
	 * @param bankCourtPhoneNumber the bankCourtPhoneNumber to set
	 */
	public void setBankCourtPhoneNumber(String bankCourtPhoneNumber) {
		this.bankCourtPhoneNumber = bankCourtPhoneNumber;
	}

	/**
	 * @return the caveatDetails
	 */
	public String getCaveatDetails() {
		return caveatDetails;
	}

	/**
	 * @param caveatDetails the caveatDetails to set
	 */
	public void setCaveatDetails(String caveatDetails) {
		this.caveatDetails = caveatDetails;
	}

	/**
	 * @return the stayOrderNo
	 */
	public String getStayOrderNo() {
		return stayOrderNo;
	}

	/**
	 * @param stayOrderNo the stayOrderNo to set
	 */
	public void setStayOrderNo(String stayOrderNo) {
		this.stayOrderNo = stayOrderNo;
	}

	/**
	 * @return the stayOrderDetails
	 */
	public String getStayOrderDetails() {
		return stayOrderDetails;
	}

	/**
	 * @param stayOrderDetails the stayOrderDetails to set
	 */
	public void setStayOrderDetails(String stayOrderDetails) {
		this.stayOrderDetails = stayOrderDetails;
	}

	/**
	 * @return the stayOrderYesNo
	 */
	public String getStayOrderYesNo() {
		return stayOrderYesNo;
	}

	/**
	 * @param stayOrderYesNo the stayOrderYesNo to set
	 */
	public void setStayOrderYesNo(String stayOrderYesNo) {
		this.stayOrderYesNo = stayOrderYesNo;
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
	 * @return the registrationNumber
	 */
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the registrationNoSearch
	 */
	public String getRegistrationNoSearch() {
		return registrationNoSearch;
	}

	/**
	 * @param registrationNoSearch the registrationNoSearch to set
	 */
	public void setRegistrationNoSearch(String registrationNoSearch) {
		this.registrationNoSearch = registrationNoSearch;
	}

	/**
	 * @return the docUrl
	 */
	public String getDocUrl() {
		return docUrl;
	}

	/**
	 * @param docUrl the docUrl to set
	 */
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}

	/**
	 * @return the documentName
	 */
	public String getDocumentName() {
		return documentName;
	}

	/**
	 * @param documentName the documentName to set
	 */
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	/**
	 * @return the caveatsFormLog
	 */
	public String getCaveatsFormLog() {
		return caveatsFormLog;
	}

	/**
	 * @param caveatsFormLog the caveatsFormLog to set
	 */
	public void setCaveatsFormLog(String caveatsFormLog) {
		this.caveatsFormLog = caveatsFormLog;
	}

	/**
	 * @return the caveatsLogInsert
	 */
	public String getCaveatsLogInsert() {
		return caveatsLogInsert;
	}

	/**
	 * @param caveatsLogInsert the caveatsLogInsert to set
	 */
	public void setCaveatsLogInsert(String caveatsLogInsert) {
		this.caveatsLogInsert = caveatsLogInsert;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the caveatType
	 */
	public String getCaveatType() {
		return caveatType;
	}

	/**
	 * @param caveatType the caveatType to set
	 */
	public void setCaveatType(String caveatType) {
		this.caveatType = caveatType;
	}

	/**
	 * @return the recordsBuffer
	 */
	public ArrayList getRecordsBuffer() {
		return recordsBuffer;
	}

	/**
	 * @param recordsBuffer the recordsBuffer to set
	 */
	public void setRecordsBuffer(ArrayList recordsBuffer) {
		this.recordsBuffer = recordsBuffer;
	}

	/**
	 * @return the caveatSorderStatus
	 */
	public String getCaveatSorderStatus() {
		return caveatSorderStatus;
	}

	/**
	 * @param caveatSorderStatus the caveatSorderStatus to set
	 */
	public void setCaveatSorderStatus(String caveatSorderStatus) {
		this.caveatSorderStatus = caveatSorderStatus;
	}

	/**
	 * @return the loggedDate
	 */
	public String getLoggedDate() {
		return loggedDate;
	}

	/**
	 * @param loggedDate the loggedDate to set
	 */
	public void setLoggedDate(String loggedDate) {
		this.loggedDate = loggedDate;
	}

	/**
	 * @return the reasonForRelease
	 */
	public String getReasonForRelease() {
		return reasonForRelease;
	}

	/**
	 * @param reasonForRelease the reasonForRelease to set
	 */
	public void setReasonForRelease(String reasonForRelease) {
		this.reasonForRelease = reasonForRelease;
	}

	/**
	 * @return the remarksForRelease
	 */
	public String getRemarksForRelease() {
		return remarksForRelease;
	}

	/**
	 * @param remarksForRelease the remarksForRelease to set
	 */
	public void setRemarksForRelease(String remarksForRelease) {
		this.remarksForRelease = remarksForRelease;
	}

	/**
	 * @return the releaseDate
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
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
	 * @return the referenceIDSearch
	 */
	public String getReferenceIDSearch() {
		return referenceIDSearch;
	}

	/**
	 * @param referenceIDSearch the referenceIDSearch to set
	 */
	public void setReferenceIDSearch(String referenceIDSearch) {
		this.referenceIDSearch = referenceIDSearch;
	}

	/**
	 * @return the pinNumber
	 */
	public String getPinNumber() {
		return pinNumber;
	}

	/**
	 * @param pinNumber the pinNumber to set
	 */
	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the ottNumber
	 */
	public String getOttNumber() {
		return ottNumber;
	}

	/**
	 * @param ottNumber the ottNumber to set
	 */
	public void setOttNumber(String ottNumber) {
		this.ottNumber = ottNumber;
	}

	/**
	 * @return the ottExpiryDate
	 */
	public String getOttExpiryDate() {
		return ottExpiryDate;
	}

	/**
	 * @param ottExpiryDate the ottExpiryDate to set
	 */
	public void setOttExpiryDate(String ottExpiryDate) {
		this.ottExpiryDate = ottExpiryDate;
	}

	/**
	 * @return the ottStatus
	 */
	public String getOttStatus() {
		return ottStatus;
	}

	/**
	 * @param ottStatus the ottStatus to set
	 */
	public void setOttStatus(String ottStatus) {
		this.ottStatus = ottStatus;
	}

	/**
	 * @return the ottDate
	 */
	public String getOttDate() {
		return ottDate;
	}

	/**
	 * @param ottDate the ottDate to set
	 */
	public void setOttDate(String ottDate) {
		this.ottDate = ottDate;
	}

	/**
	 * @return the hidlistten
	 */
	public int getHidlistten() {
		return hidlistten;
	}

	/**
	 * @param hidlistten the hidlistten to set
	 */
	public void setHidlistten(int hidlistten) {
		this.hidlistten = hidlistten;
	}

	/**
	 * @return the stayOrderStartDate
	 */
	public String getStayOrderStartDate() {
		return stayOrderStartDate;
	}

	/**
	 * @param stayOrderStartDate the stayOrderStartDate to set
	 */
	public void setStayOrderStartDate(String stayOrderStartDate) {
		this.stayOrderStartDate = stayOrderStartDate;
	}

	/**
	 * @return the stayOrderUptoDate
	 */
	public String getStayOrderUptoDate() {
		return stayOrderUptoDate;
	}

	/**
	 * @param stayOrderUptoDate the stayOrderUptoDate to set
	 */
	public void setStayOrderUptoDate(String stayOrderUptoDate) {
		this.stayOrderUptoDate = stayOrderUptoDate;
	}

	/**
	 * @return the nameOfInsti
	 */
	public String getNameOfInsti() {
		return nameOfInsti;
	}

	/**
	 * @param nameOfInsti the nameOfInsti to set
	 */
	public void setNameOfInsti(String nameOfInsti) {
		this.nameOfInsti = nameOfInsti;
	}

	/**
	 * @return the addressOfInsti
	 */
	public String getAddressOfInsti() {
		return addressOfInsti;
	}

	/**
	 * @param addressOfInsti the addressOfInsti to set
	 */
	public void setAddressOfInsti(String addressOfInsti) {
		this.addressOfInsti = addressOfInsti;
	}

	/**
	 * @return the contactnoOfInsti
	 */
	public String getContactnoOfInsti() {
		return contactnoOfInsti;
	}

	/**
	 * @param contactnoOfInsti the contactnoOfInsti to set
	 */
	public void setContactnoOfInsti(String contactnoOfInsti) {
		this.contactnoOfInsti = contactnoOfInsti;
	}

	/**
	 * @return the emailOfInsti
	 */
	public String getEmailOfInsti() {
		return emailOfInsti;
	}

	/**
	 * @param emailOfInsti the emailOfInsti to set
	 */
	public void setEmailOfInsti(String emailOfInsti) {
		this.emailOfInsti = emailOfInsti;
	}

	/**
	 * @return the nameOfBankPerson
	 */
	public String getNameOfBankPerson() {
		return nameOfBankPerson;
	}

	/**
	 * @param nameOfBankPerson the nameOfBankPerson to set
	 */
	public void setNameOfBankPerson(String nameOfBankPerson) {
		this.nameOfBankPerson = nameOfBankPerson;
	}

	/**
	 * @return the mobOfBankPerson
	 */
	public String getMobOfBankPerson() {
		return mobOfBankPerson;
	}

	/**
	 * @param mobOfBankPerson the mobOfBankPerson to set
	 */
	public void setMobOfBankPerson(String mobOfBankPerson) {
		this.mobOfBankPerson = mobOfBankPerson;
	}

	/**
	 * @return the emailOfBankPerson
	 */
	public String getEmailOfBankPerson() {
		return emailOfBankPerson;
	}

	/**
	 * @param emailOfBankPerson the emailOfBankPerson to set
	 */
	public void setEmailOfBankPerson(String emailOfBankPerson) {
		this.emailOfBankPerson = emailOfBankPerson;
	}

	/**
	 * @return the loanAccountNumber
	 */
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}

	/**
	 * @param loanAccountNumber the loanAccountNumber to set
	 */
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}

	/**
	 * @return the loanAmount
	 */
	public String getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the securedAmount
	 */
	public String getSecuredAmount() {
		return securedAmount;
	}

	/**
	 * @param securedAmount the securedAmount to set
	 */
	public void setSecuredAmount(String securedAmount) {
		this.securedAmount = securedAmount;
	}

	/**
	 * @return the amountOfInstall
	 */
	public String getAmountOfInstall() {
		return amountOfInstall;
	}

	/**
	 * @param amountOfInstall the amountOfInstall to set
	 */
	public void setAmountOfInstall(String amountOfInstall) {
		this.amountOfInstall = amountOfInstall;
	}

	/**
	 * @return the noOfInstallments
	 */
	public String getNoOfInstallments() {
		return noOfInstallments;
	}

	/**
	 * @param noOfInstallments the noOfInstallments to set
	 */
	public void setNoOfInstallments(String noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	/**
	 * @return the loanDueDate
	 */
	public String getLoanDueDate() {
		return loanDueDate;
	}

	/**
	 * @param loanDueDate the loanDueDate to set
	 */
	public void setLoanDueDate(String loanDueDate) {
		this.loanDueDate = loanDueDate;
	}

	/**
	 * @return the loanStartDate
	 */
	public String getLoanStartDate() {
		return loanStartDate;
	}

	/**
	 * @param loanStartDate the loanStartDate to set
	 */
	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	/**
	 * @return the loanEndDate
	 */
	public String getLoanEndDate() {
		return loanEndDate;
	}

	/**
	 * @param loanEndDate the loanEndDate to set
	 */
	public void setLoanEndDate(String loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	/**
	 * @return the bankLoggedDate
	 */
	public String getBankLoggedDate() {
		return bankLoggedDate;
	}

	/**
	 * @param bankLoggedDate the bankLoggedDate to set
	 */
	public void setBankLoggedDate(String bankLoggedDate) {
		this.bankLoggedDate = bankLoggedDate;
	}

	/**
	 * @return the bankContactNo
	 */
	public String getBankContactNo() {
		return bankContactNo;
	}

	/**
	 * @param bankContactNo the bankContactNo to set
	 */
	public void setBankContactNo(String bankContactNo) {
		this.bankContactNo = bankContactNo;
	}

	/**
	 * @return the bankEmail
	 */
	public String getBankEmail() {
		return bankEmail;
	}

	/**
	 * @param bankEmail the bankEmail to set
	 */
	public void setBankEmail(String bankEmail) {
		this.bankEmail = bankEmail;
	}

	/**
	 * @return the nameOfCourtPerson
	 */
	public String getNameOfCourtPerson() {
		return nameOfCourtPerson;
	}

	/**
	 * @param nameOfCourtPerson the nameOfCourtPerson to set
	 */
	public void setNameOfCourtPerson(String nameOfCourtPerson) {
		this.nameOfCourtPerson = nameOfCourtPerson;
	}

	/**
	 * @return the registrationDate
	 */
	public String getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate the registrationDate to set
	 */
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	/**
	 * @return the serialNo
	 */
	public long getSerialNo() {
		return serialNo;
	}

	/**
	 * @param serialNo the serialNo to set
	 */
	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the loggedIn
	 */
	public String getLoggedIn() {
		return loggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * @return the propertyTxnId
	 */
	public String getPropertyTxnId() {
		return propertyTxnId;
	}

	/**
	 * @param propertyTxnId the propertyTxnId to set
	 */
	public void setPropertyTxnId(String propertyTxnId) {
		this.propertyTxnId = propertyTxnId;
	}

	/**
	 * @return the attachedDoc
	 */
	public FormFile getAttachedDoc() {
		return attachedDoc;
	}

	/**
	 * @param attachedDoc the attachedDoc to set
	 */
	public void setAttachedDoc(FormFile attachedDoc) {
		this.attachedDoc = attachedDoc;
	}

	/**
	 * @return the docContents
	 */
	public byte[] getDocContents() {
		return docContents;
	}

	/**
	 * @param docContents the docContents to set
	 */
	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}

	/**
	 * @return the docFileSize
	 */
	public String getDocFileSize() {
		return docFileSize;
	}

	/**
	 * @param docFileSize the docFileSize to set
	 */
	public void setDocFileSize(String docFileSize) {
		this.docFileSize = docFileSize;
	}

	/**
	 * @return the selectedItems
	 */
	public ArrayList getSelectedItems() {
		return selectedItems;
	}

	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}

	/**
	 * @return the releaseDoc
	 */
	public FormFile getReleaseDoc() {
		return releaseDoc;
	}

	/**
	 * @param releaseDoc the releaseDoc to set
	 */
	public void setReleaseDoc(FormFile releaseDoc) {
		this.releaseDoc = releaseDoc;
	}

	/**
	 * @return the releaseContents
	 */
	public byte[] getReleaseContents() {
		return releaseContents;
	}

	/**
	 * @param releaseContents the releaseContents to set
	 */
	public void setReleaseContents(byte[] releaseContents) {
		this.releaseContents = releaseContents;
	}

	/**
	 * @return the releaseDocName
	 */
	public String getReleaseDocName() {
		return releaseDocName;
	}

	/**
	 * @param releaseDocName the releaseDocName to set
	 */
	public void setReleaseDocName(String releaseDocName) {
		this.releaseDocName = releaseDocName;
	}

	/**
	 * @return the releaseFileSize
	 */
	public String getReleaseFileSize() {
		return releaseFileSize;
	}

	/**
	 * @param releaseFileSize the releaseFileSize to set
	 */
	public void setReleaseFileSize(String releaseFileSize) {
		this.releaseFileSize = releaseFileSize;
	}

	/**
	 * @return the propertyTxnLock
	 */
	public String getPropertyTxnLock() {
		return propertyTxnLock;
	}

	/**
	 * @param propertyTxnLock the propertyTxnLock to set
	 */
	public void setPropertyTxnLock(String propertyTxnLock) {
		this.propertyTxnLock = propertyTxnLock;
	}

	/**
	 * @return the caveatLabel
	 */
	public String getCaveatLabel() {
		return caveatLabel;
	}

	/**
	 * @param caveatLabel the caveatLabel to set
	 */
	public void setCaveatLabel(String caveatLabel) {
		this.caveatLabel = caveatLabel;
	}

	/**
	 * @return the pinNumberInDB
	 */
	public String getPinNumberInDB() {
		return pinNumberInDB;
	}

	/**
	 * @param pinNumberInDB the pinNumberInDB to set
	 */
	public void setPinNumberInDB(String pinNumberInDB) {
		this.pinNumberInDB = pinNumberInDB;
	}

	/**
	 * @return the caveatStayOrderFlag
	 */
	public String getCaveatStayOrderFlag() {
		return caveatStayOrderFlag;
	}

	/**
	 * @param caveatStayOrderFlag the caveatStayOrderFlag to set
	 */
	public void setCaveatStayOrderFlag(String caveatStayOrderFlag) {
		this.caveatStayOrderFlag = caveatStayOrderFlag;
	}

	/**
	 * @return the errorList
	 */
	public ArrayList getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(ArrayList errorList) {
		this.errorList = errorList;
	}

	/**
	 * @return the yesNoList
	 */
	public ArrayList getYesNoList() {
		return yesNoList;
	}

	/**
	 * @param yesNoList the yesNoList to set
	 */
	public void setYesNoList(ArrayList yesNoList) {
		CaveatsDTO.yesNoList = yesNoList;
	}

	/**
	 * @return the propertyTypeId
	 */
	public String getPropertyTypeId() {
		return propertyTypeId;
	}

	/**
	 * @param propertyTypeId the propertyTypeId to set
	 */
	public void setPropertyTypeId(String propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	/**
	 * @return the propertyTypeLabel
	 */
	public String getPropertyTypeLabel() {
		return propertyTypeLabel;
	}

	/**
	 * @param propertyTypeLabel the propertyTypeLabel to set
	 */
	public void setPropertyTypeLabel(String propertyTypeLabel) {
		this.propertyTypeLabel = propertyTypeLabel;
	}

	/**
	 * @return the cloneSelectedItems
	 */
	public ArrayList getCloneSelectedItems() {
		return cloneSelectedItems;
	}

	/**
	 * @param cloneSelectedItems the cloneSelectedItems to set
	 */
	public void setCloneSelectedItems(ArrayList cloneSelectedItems) {
		this.cloneSelectedItems = cloneSelectedItems;
	}

	private byte[] photo;

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setMapPropertyTransPartyDisp(HashMap mapPropertyTransPartyDisp) {
		this.mapPropertyTransPartyDisp = mapPropertyTransPartyDisp;
	}

	public HashMap getMapPropertyTransPartyDisp() {
		return mapPropertyTransPartyDisp;
	}

	public void setValId(String valId) {
		this.valId = valId;
	}

	public String getValId() {
		return valId;
	}
	public String getBankChargeId() {
		return bankChargeId;
	}
	public void setBankChargeId(String bankChargeId) {
		this.bankChargeId = bankChargeId;
	}
	public String getCaveatStatus() {
		return caveatStatus;
	}
	public void setCaveatStatus(String caveatStatus) {
		this.caveatStatus = caveatStatus;
	}
	public String getCaveatUploadedDoc() {
		return caveatUploadedDoc;
	}
	public void setCaveatUploadedDoc(String caveatUploadedDoc) {
		this.caveatUploadedDoc = caveatUploadedDoc;
	}
	
	
	
	
	
	

	
}
