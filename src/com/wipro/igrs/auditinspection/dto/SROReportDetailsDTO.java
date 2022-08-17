/*
* Copyright Notice
*==============================================================================
* This file contains proprietary information of IGRS Bhopal Madhya Pradesh .
* Copying or reproduction without prior written approval is prohibited.
* Copyright (c) 2007-08
*==============================================================================
*
* File Name   :   
 * Author      :   
 * Description :   
 * ----------------------------------------------------------------------------
* Version         Modified By     Modified On           Modification
* ----------------------------------------------------------------------------
* 1.0             SHRUTI          xxxxx              
 * --------------------------------------------------------------------------------
*/

package com.wipro.igrs.auditinspection.dto;


import java.io.Serializable;
import java.util.ArrayList;

public class SROReportDetailsDTO implements Serializable{
   
	private String	                      inspectionId;
	
	private String	                      inspectionAuthorityName;
	
	private String	                      inspectionStartDate;
	
	private String	                      inspectionEndDate;
	
	private String	                      inspectionDate;
	
	private String	                      inspectedBy;
	
	private String	                      inspectionStatus;
	
	private String	                      lfyAnnualTargets;
	
	private String	                      lfyAnnualReceipts;
	
	private String	                      lfyPctComp;
	
	private String	                      lfyPctIncr;
	
	private String	                      cfyAnnualIncome;
	
	private String	                      cfyAnnualReceipt;
	
	private String	                      lfyIncome;
	
	private String	                      incomReasonCfy;
	
	private String	                      incomeReasonLfy;
	
	private String	                      cashDepositBank;
	
	private String	                      pfyrevenuetaget;
	
	private String	                      percentageincr;
	
	private String	                      noRtiRequestCFY;
	
	private String	                      noRtiDiscloreCFY;
	
	private String	                      reason;
	
	private String	                      sroName;
	
	private String	                      sroId;
	
	private String 						  srName;
	
	private String	                      chkRtiPertaining;
	
	private String                        districtId;
	
	private String                        districtName;
	
	private String                        cfyRevenueFromDate;
	
	private String                        cfyRevenueFromYear;
	
	private String                        cfyRevenueToDate;
	
	private String                        cfyRevenueToYear;
	
	private String                        cfyRevenueColToYear;
	
	private String                        cfyRevenueColToDate;
	
	private String                        cfyRevenueColFromDate;
	
	private String                        cfyRevenueColFromYear;
	
	private String                        pfyRevenueFromDate;
	
	private String                        pfyRevenueFromYear;
	
	private String                        pfyRevenueToDate;
	
	private String                        pfyRevenueToYear;
	
	private ArrayList                     listDistrict;
	
	private String                        fromYear;
	
	private String                        toYear; 
	
	private String                        tenure;
	
	private String                        adhikarAbhilek;
	
	private ArrayList	      listSROEmp;
	
	private ArrayList	      listSRODocMap;
	
	private ArrayList	  listSROComments;
	
	private ArrayList	listSROPendingItem;
	
	private ArrayList	  listSROCheckMap;
	
	
	//added by shruti-17th sep 2013
	private String	                      inspectionEntryDate;
	
	//added by vinay
	
	private String							userId;
	
	private String 							loggedInOffice;  
	
	private String    						cashDepositBankYesNo;
	
	private String inspEntryDate;
	
	private String inspDueDate;
	
	private String objectedEntryDate;
	
	private String pastInspDetails;
	
	private String complianceFlag;
	
	private String srCompliance;
	
	private String drCompliance;
	
	private String digCompliance;
	
	private String igCompliance;
	//added by shruti
	private String srComments; 
	
	private String employeeId; 
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSrComments() {
		return srComments;
	}

	public void setSrComments(String srComments) {
		this.srComments = srComments;
	}

	public String getComplianceFlag() {
		return complianceFlag;
	}

	public void setComplianceFlag(String complianceFlag) {
		this.complianceFlag = complianceFlag;
	}

	public String getSrCompliance() {
		return srCompliance;
	}

	public void setSrCompliance(String srCompliance) {
		this.srCompliance = srCompliance;
	}

	public String getDrCompliance() {
		return drCompliance;
	}

	public void setDrCompliance(String drCompliance) {
		this.drCompliance = drCompliance;
	}

	public String getDigCompliance() {
		return digCompliance;
	}

	public void setDigCompliance(String digCompliance) {
		this.digCompliance = digCompliance;
	}

	public String getIgCompliance() {
		return igCompliance;
	}

	public void setIgCompliance(String igCompliance) {
		this.igCompliance = igCompliance;
	}

	public String getObjectedEntryDate() {
		return objectedEntryDate;
	}

	public void setObjectedEntryDate(String objectedEntryDate) {
		this.objectedEntryDate = objectedEntryDate;
	}

	public String getPastInspDetails() {
		return pastInspDetails;
	}

	public void setPastInspDetails(String pastInspDetails) {
		this.pastInspDetails = pastInspDetails;
	}

	public String getInspEntryDate() {
		return inspEntryDate;
	}

	public void setInspEntryDate(String inspEntryDate) {
		this.inspEntryDate = inspEntryDate;
	}

	public String getInspDueDate() {
		return inspDueDate;
	}

	public void setInspDueDate(String inspDueDate) {
		this.inspDueDate = inspDueDate;
	}

	public String getCashDepositBankYesNo() {
		return cashDepositBankYesNo;
	}

	public void setCashDepositBankYesNo(String cashDepositBankYesNo) {
		this.cashDepositBankYesNo = cashDepositBankYesNo;
	}

	public String getLoggedInOffice() {
		return loggedInOffice;
	}

	public void setLoggedInOffice(String loggedInOffice) {
		this.loggedInOffice = loggedInOffice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInspectionEntryDate() {
		return inspectionEntryDate;
	}

	public void setInspectionEntryDate(String inspectionEntryDate) {
		this.inspectionEntryDate = inspectionEntryDate;
	}

	/**
	 * @return the inspectionId
	 */
	public String getInspectionId() {
		return inspectionId;
	}
	
	/**
	 * @param inspectionId the inspectionId to set
	 */
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	
	/**
	 * @return the inspectionAuthorityName
	 */
	public String getInspectionAuthorityName() {
		return inspectionAuthorityName;
	}
	
	/**
	 * @param inspectionAuthorityName the inspectionAuthorityName to set
	 */
	public void setInspectionAuthorityName(String inspectionAuthorityName) {
		this.inspectionAuthorityName = inspectionAuthorityName;
	}
	
	/**
	 * @return the inspectionStartDate
	 */
	public String getInspectionStartDate() {
		return inspectionStartDate;
	}
	
	/**
	 * @param inspectionStartDate the inspectionStartDate to set
	 */
	public void setInspectionStartDate(String inspectionStartDate) {
		this.inspectionStartDate = inspectionStartDate;
	}
	
	/**
	 * @return the inspectionEndDate
	 */
	public String getInspectionEndDate() {
		return inspectionEndDate;
	}
	
	/**
	 * @param inspectionEndDate the inspectionEndDate to set
	 */
	public void setInspectionEndDate(String inspectionEndDate) {
		this.inspectionEndDate = inspectionEndDate;
	}
	
	/**
	 * @return the inspectionDate
	 */
	public String getInspectionDate() {
		return inspectionDate;
	}
	
	/**
	 * @param inspectionDate the inspectionDate to set
	 */
	public void setInspectionDate(String inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	/**
	 * @return the inspectedBy
	 */
	public String getInspectedBy() {
		return inspectedBy;
	}
	
	/**
	 * @param inspectedBy the inspectedBy to set
	 */
	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}
	
	/**
	 * @return the inspectionStatus
	 */
	public String getInspectionStatus() {
		return inspectionStatus;
	}
	
	/**
	 * @param inspectionStatus the inspectionStatus to set
	 */
	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
	
	/**
	 * @return the lfyAnnualTargets
	 */
	public String getLfyAnnualTargets() {
		return lfyAnnualTargets;
	}
	
	/**
	 * @param lfyAnnualTargets the lfyAnnualTargets to set
	 */
	public void setLfyAnnualTargets(String lfyAnnualTargets) {
		this.lfyAnnualTargets = lfyAnnualTargets;
	}
	
	/**
	 * @return the lfyAnnualReceipts
	 */
	public String getLfyAnnualReceipts() {
		return lfyAnnualReceipts;
	}
	
	/**
	 * @param lfyAnnualReceipts the lfyAnnualReceipts to set
	 */
	public void setLfyAnnualReceipts(String lfyAnnualReceipts) {
		this.lfyAnnualReceipts = lfyAnnualReceipts;
	}
	
	/**
	 * @return the lfyPctComp
	 */
	public String getLfyPctComp() {
		return lfyPctComp;
	}
	
	/**
	 * @param lfyPctComp the lfyPctComp to set
	 */
	public void setLfyPctComp(String lfyPctComp) {
		this.lfyPctComp = lfyPctComp;
	}
	
	/**
	 * @return the lfyPctIncr
	 */
	public String getLfyPctIncr() {
		return lfyPctIncr;
	}
	
	/**
	 * @param lfyPctIncr the lfyPctIncr to set
	 */
	public void setLfyPctIncr(String lfyPctIncr) {
		this.lfyPctIncr = lfyPctIncr;
	}
	
	/**
	 * @return the cfyAnnualIncome
	 */
	public String getCfyAnnualIncome() {
		return cfyAnnualIncome;
	}
	
	/**
	 * @param cfyAnnualIncome the cfyAnnualIncome to set
	 */
	public void setCfyAnnualIncome(String cfyAnnualIncome) {
		this.cfyAnnualIncome = cfyAnnualIncome;
	}
	
	/**
	 * @return the cfyAnnualReceipt
	 */
	public String getCfyAnnualReceipt() {
		return cfyAnnualReceipt;
	}
	
	/**
	 * @param cfyAnnualReceipt the cfyAnnualReceipt to set
	 */
	public void setCfyAnnualReceipt(String cfyAnnualReceipt) {
		this.cfyAnnualReceipt = cfyAnnualReceipt;
	}
	
	/**
	 * @return the lfyIncome
	 */
	public String getLfyIncome() {
		return lfyIncome;
	}
	
	/**
	 * @param lfyIncome the lfyIncome to set
	 */
	public void setLfyIncome(String lfyIncome) {
		this.lfyIncome = lfyIncome;
	}
	
	/**
	 * @return the incomReasonCfy
	 */
	public String getIncomReasonCfy() {
		return incomReasonCfy;
	}
	
	/**
	 * @param incomReasonCfy the incomReasonCfy to set
	 */
	public void setIncomReasonCfy(String incomReasonCfy) {
		this.incomReasonCfy = incomReasonCfy;
	}
	
	/**
	 * @return the incomeReasonLfy
	 */
	public String getIncomeReasonLfy() {
		return incomeReasonLfy;
	}
	
	/**
	 * @param incomeReasonLfy the incomeReasonLfy to set
	 */
	public void setIncomeReasonLfy(String incomeReasonLfy) {
		this.incomeReasonLfy = incomeReasonLfy;
	}
	
	/**
	 * @return the cashDepositBank
	 */
	public String getCashDepositBank() {
		return cashDepositBank;
	}
	
	/**
	 * @param cashDepositBank the cashDepositBank to set
	 */
	public void setCashDepositBank(String cashDepositBank) {
		this.cashDepositBank = cashDepositBank;
	}
	
	/**
	 * @return the pfyrevenuetaget
	 */
	public String getPfyrevenuetaget() {
		return pfyrevenuetaget;
	}
	
	/**
	 * @param pfyrevenuetaget the pfyrevenuetaget to set
	 */
	public void setPfyrevenuetaget(String pfyrevenuetaget) {
		this.pfyrevenuetaget = pfyrevenuetaget;
	}
	
	/**
	 * @return the percentageincr
	 */
	public String getPercentageincr() {
		return percentageincr;
	}
	
	/**
	 * @param percentageincr the percentageincr to set
	 */
	public void setPercentageincr(String percentageincr) {
		this.percentageincr = percentageincr;
	}
	
	/**
	 * @return the noRtiRequestCFY
	 */
	public String getNoRtiRequestCFY() {
		return noRtiRequestCFY;
	}
	
	/**
	 * @param noRtiRequestCFY the noRtiRequestCFY to set
	 */
	public void setNoRtiRequestCFY(String noRtiRequestCFY) {
		this.noRtiRequestCFY = noRtiRequestCFY;
	}
	
	/**
	 * @return the noRtiDiscloreCFY
	 */
	public String getNoRtiDiscloreCFY() {
		return noRtiDiscloreCFY;
	}
	
	/**
	 * @param noRtiDiscloreCFY the noRtiDiscloreCFY to set
	 */
	public void setNoRtiDiscloreCFY(String noRtiDiscloreCFY) {
		this.noRtiDiscloreCFY = noRtiDiscloreCFY;
	}
	
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
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
	 * @return the chkRtiPertaining
	 */
	public String getChkRtiPertaining() {
		return chkRtiPertaining;
	}
	
	/**
	 * @param chkRtiPertaining the chkRtiPertaining to set
	 */
	public void setChkRtiPertaining(String chkRtiPertaining) {
		this.chkRtiPertaining = chkRtiPertaining;
	}
	
	/**
	 * @return the listSROEmp
	 */
	public ArrayList getListSROEmp() {
		return listSROEmp;
	}
	
	/**
	 * @param listSROEmp the listSROEmp to set
	 */
	public void setListSROEmp(ArrayList listSROEmp) {
		this.listSROEmp = listSROEmp;
	}
	
	/**
	 * @return the listSRODocMap
	 */
	public ArrayList getListSRODocMap() {
		return listSRODocMap;
	}
	
	/**
	 * @param listSRODocMap the listSRODocMap to set
	 */
	public void setListSRODocMap(ArrayList listSRODocMap) {
		this.listSRODocMap = listSRODocMap;
	}
	
	/**
	 * @return the listSROComments
	 */
	public ArrayList getListSROComments() {
		return listSROComments;
	}
	
	/**
	 * @param listSROComments the listSROComments to set
	 */
	public void setListSROComments(ArrayList listSROComments) {
		this.listSROComments = listSROComments;
	}
	
	/**
	 * @return the listSROPendingItem
	 */
	public ArrayList getListSROPendingItem() {
		return listSROPendingItem;
	}
	
	/**
	 * @param listSROPendingItem the listSROPendingItem to set
	 */
	public void setListSROPendingItem(
	        ArrayList listSROPendingItem) {
		this.listSROPendingItem = listSROPendingItem;
	}
	
	/**
	 * @return the listSROCheckMap
	 */
	public ArrayList getListSROCheckMap() {
		return listSROCheckMap;
	}
	
	/**
	 * @param listSROCheckMap the listSROCheckMap to set
	 */
	public void setListSROCheckMap(ArrayList listSROCheckMap) {
		this.listSROCheckMap = listSROCheckMap;
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

	public String getDistrictId() {
    	return districtId;
    }

	public void setDistrictId(String districtId) {
    	this.districtId = districtId;
    }

	public String getDistrictName() {
    	return districtName;
    }

	public void setDistrictName(String districtName) {
    	this.districtName = districtName;
    }

	public ArrayList getListDistrict() {
    	return listDistrict;
    }

	public void setListDistrict(ArrayList listDistrict) {
    	this.listDistrict = listDistrict;
    }

	/**
	 * @return the cfyRevenueFromDate
	 */
	public String getCfyRevenueFromDate() {
		return cfyRevenueFromDate;
	}

	/**
	 * @param cfyRevenueFromDate the cfyRevenueFromDate to set
	 */
	public void setCfyRevenueFromDate(String cfyRevenueFromDate) {
		this.cfyRevenueFromDate = cfyRevenueFromDate;
	}

	/**
	 * @return the cfyRevenueFromYear
	 */
	public String getCfyRevenueFromYear() {
		return cfyRevenueFromYear;
	}

	/**
	 * @param cfyRevenueFromYear the cfyRevenueFromYear to set
	 */
	public void setCfyRevenueFromYear(String cfyRevenueFromYear) {
		this.cfyRevenueFromYear = cfyRevenueFromYear;
	}

	/**
	 * @return the cfyRevenueToDate
	 */
	public String getCfyRevenueToDate() {
		return cfyRevenueToDate;
	}

	/**
	 * @param cfyRevenueToDate the cfyRevenueToDate to set
	 */
	public void setCfyRevenueToDate(String cfyRevenueToDate) {
		this.cfyRevenueToDate = cfyRevenueToDate;
	}

	/**
	 * @return the cfyRevenueToYear
	 */
	public String getCfyRevenueToYear() {
		return cfyRevenueToYear;
	}

	/**
	 * @param cfyRevenueToYear the cfyRevenueToYear to set
	 */
	public void setCfyRevenueToYear(String cfyRevenueToYear) {
		this.cfyRevenueToYear = cfyRevenueToYear;
	}

	/**
	 * @return the cfyRevenueColToYear
	 */
	public String getCfyRevenueColToYear() {
		return cfyRevenueColToYear;
	}

	/**
	 * @param cfyRevenueColToYear the cfyRevenueColToYear to set
	 */
	public void setCfyRevenueColToYear(String cfyRevenueColToYear) {
		this.cfyRevenueColToYear = cfyRevenueColToYear;
	}

	/**
	 * @return the cfyRevenueColToDate
	 */
	public String getCfyRevenueColToDate() {
		return cfyRevenueColToDate;
	}

	/**
	 * @param cfyRevenueColToDate the cfyRevenueColToDate to set
	 */
	public void setCfyRevenueColToDate(String cfyRevenueColToDate) {
		this.cfyRevenueColToDate = cfyRevenueColToDate;
	}

	/**
	 * @return the cfyRevenueColFromDate
	 */
	public String getCfyRevenueColFromDate() {
		return cfyRevenueColFromDate;
	}

	/**
	 * @param cfyRevenueColFromDate the cfyRevenueColFromDate to set
	 */
	public void setCfyRevenueColFromDate(String cfyRevenueColFromDate) {
		this.cfyRevenueColFromDate = cfyRevenueColFromDate;
	}

	/**
	 * @return the cfyRevenueColFromYear
	 */
	public String getCfyRevenueColFromYear() {
		return cfyRevenueColFromYear;
	}

	/**
	 * @param cfyRevenueColFromYear the cfyRevenueColFromYear to set
	 */
	public void setCfyRevenueColFromYear(String cfyRevenueColFromYear) {
		this.cfyRevenueColFromYear = cfyRevenueColFromYear;
	}

	/**
	 * @return the pfyRevenueFromDate
	 */
	public String getPfyRevenueFromDate() {
		return pfyRevenueFromDate;
	}

	/**
	 * @param pfyRevenueFromDate the pfyRevenueFromDate to set
	 */
	public void setPfyRevenueFromDate(String pfyRevenueFromDate) {
		this.pfyRevenueFromDate = pfyRevenueFromDate;
	}

	/**
	 * @return the pfyRevenueFromYear
	 */
	public String getPfyRevenueFromYear() {
		return pfyRevenueFromYear;
	}

	/**
	 * @param pfyRevenueFromYear the pfyRevenueFromYear to set
	 */
	public void setPfyRevenueFromYear(String pfyRevenueFromYear) {
		this.pfyRevenueFromYear = pfyRevenueFromYear;
	}

	/**
	 * @return the pfyRevenueToDate
	 */
	public String getPfyRevenueToDate() {
		return pfyRevenueToDate;
	}

	/**
	 * @param pfyRevenueToDate the pfyRevenueToDate to set
	 */
	public void setPfyRevenueToDate(String pfyRevenueToDate) {
		this.pfyRevenueToDate = pfyRevenueToDate;
	}

	/**
	 * @return the pfyRevenueToYear
	 */
	public String getPfyRevenueToYear() {
		return pfyRevenueToYear;
	}

	/**
	 * @param pfyRevenueToYear the pfyRevenueToYear to set
	 */
	public void setPfyRevenueToYear(String pfyRevenueToYear) {
		this.pfyRevenueToYear = pfyRevenueToYear;
	}

	/**
	 * @return the sroId
	 */
	public String getSroId() {
		return sroId;
	}

	/**
	 * @param sroId the sroId to set
	 */
	public void setSroId(String sroId) {
		this.sroId = sroId;
	}

	/**
	 * @return the fromYear
	 */
	public String getFromYear() {
		return fromYear;
	}

	/**
	 * @param fromYear the fromYear to set
	 */
	public void setFromYear(String fromYear) {
		this.fromYear = fromYear;
	}

	/**
	 * @return the toYear
	 */
	public String getToYear() {
		return toYear;
	}

	/**
	 * @param toYear the toYear to set
	 */
	public void setToYear(String toYear) {
		this.toYear = toYear;
	}

	/**
	 * @return the tenure
	 */
	public String getTenure() {
		return tenure;
	}

	/**
	 * @param tenure the tenure to set
	 */
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	/**
	 * @return the adhikarAbhilek
	 */
	public String getAdhikarAbhilek() {
		return adhikarAbhilek;
	}

	/**
	 * @param adhikarAbhilek the adhikarAbhilek to set
	 */
	public void setAdhikarAbhilek(String adhikarAbhilek) {
		this.adhikarAbhilek = adhikarAbhilek;
	}
	
	
	
}
