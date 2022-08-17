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


public class AGMPReportDetailsDTO implements Serializable {
	
	private String	     txtAuditorName;

	private String	     txtAuditLocation;

	private String	     txtFromDate;

	private String	     txtToDate;

	private String	     txtAuditDate;

	private String	     txtDispatchDate;

	private String	     txtPhysicalId;

	private String	     txtEntryDate;

	private String	     txtComments;

	private String	     fileAuditReport;

	private String	     fileOthers;

	private String	     report;

	private String	     auditBody;

	private String	     auditType;

	private String	     fromDate;

	private String	     toDate;

	private String	     sroName;

	private String	     sroId;

	private String	     textParaName;
	//added by shruti
	private String  	textParaNumber;
	//end

	private String	     textParaComments;

	private String	     listParaType;

	private String	     regId;

	private String	     docType;

	private String	     txtDocID;

	private String	     txtValAgmp;

	private String	     tempValAgmp;

	private String	     txtStampDuty;

	private String	     txtRegFee;

	private String	     txtObjDetails;

	private String	     txtAgmpComm;

	private String	     docStatusId;

	private String	     txtSRname;

	private String	     txtStampDuty1;

	private String	     txtRegFee1;

	private String	     removeAction;

	private String	     closeAction;

	private String	     attach;

	private String	     tempAction;

	private String	     paraType;

	private String	     ParaName;

	private String	     txnId;

	private String	     officeTypeId;

	private String	     officeName;

	private String	     addMore;

	private String	     formName;

	private String	     action;

	RegIdSearch	         regIdsearch[];

	RegIdOtherSearch	 regIdOthers[];

	RegIdOtherSearch	 regIdOther	= new RegIdOtherSearch();

	AGMPReportDetailsDTO	agmpReport[];

	private String	     isFinalPage;
	
	

	private ArrayList	 sroList;
	
	//added by shruti
	private String marketVal;
	private String partyLink;
	private String propertyLink;
	private String regDate;
	private String district; 

	private String dbParaType;
	private String dbParaName;
	private String dbParaId;

	private String dbParaNumber;
	private String dbParaStatus;
	private String caseId;
	private String caseType;
	private String txtCaseID;
	private String caseDate;
	private String caseStatus;
	private String regFlag;
	
	//added by ashima

    private String paraId;
    private String paraStatus;
    private String transId;
    
//added by Shreeraj
    
    private String offcId;    
    private String droId;    
    private String hoId;
    private String droName;
    private String hoName;
    private String auditFromMonth;
    private String auditFromYear;
    private String auditToMonth;
    private String auditToYear;
    private String reportType;
    private String reportStatus;
    private String paraStatusId;
    private String paraTypeName;
    private String auditDate;
    private String audEntryDate;
    private String auditBodyType;
    private String paraNamee;
    private String paraNum;
    private String paraComments;
    private String recFlag;
    //added by shruti
    private String objId;
    private String objDtls;
    private String docId;
    private String objDate;
    private String defRegFee;
    private String defStampDuty;
    private String valReg;
    private String valAgmp;
    private String objStatus;
    
    //docTypeFlag  field created by Roopam for registered/others
    private String docTypeFlag;
    private String finalComments;
    private String hdnStatus;
    
    
    // Added by Rachita for Others
    
     
	   private String othersRegId;    
	    private String othersRegDate;    
	    private String othersSroName;
	    

		private String othersSrName;
	    private String othersValAtRegTime;
	    private String othersConsiderationAmt;
	    private String othersDutyFee;
	    private String othersStampDuty;
	    private String othersRegFee;
	   
	    private String othersDocType;
	    private String othersFlag;
  

	public String getOthersFlag() {
			return othersFlag;
		}

		public void setOthersFlag(String othersFlag) {
			this.othersFlag = othersFlag;
		}

	public String getOthersDocType() {
			return othersDocType;
		}

		public void setOthersDocType(String othersDocType) {
			this.othersDocType = othersDocType;
		}

	public String getOthersRegId() {
			return othersRegId;
		}

		public void setOthersRegId(String othersRegId) {
			this.othersRegId = othersRegId;
		}

		public String getOthersRegDate() {
			return othersRegDate;
		}

		public void setOthersRegDate(String othersRegDate) {
			this.othersRegDate = othersRegDate;
		}

		public String getOthersSroName() {
			return othersSroName;
		}

		public void setOthersSroName(String othersSroName) {
			this.othersSroName = othersSroName;
		}

	

		public String getOthersSrName() {
			return othersSrName;
		}

		public void setOthersSrName(String othersSrName) {
			this.othersSrName = othersSrName;
		}

		public String getOthersValAtRegTime() {
			return othersValAtRegTime;
		}

		public void setOthersValAtRegTime(String othersValAtRegTime) {
			this.othersValAtRegTime = othersValAtRegTime;
		}

		public String getOthersConsiderationAmt() {
			return othersConsiderationAmt;
		}

		public void setOthersConsiderationAmt(String othersConsiderationAmt) {
			this.othersConsiderationAmt = othersConsiderationAmt;
		}

		public String getOthersDutyFee() {
			return othersDutyFee;
		}

		public void setOthersDutyFee(String othersDutyFee) {
			this.othersDutyFee = othersDutyFee;
		}

		public String getOthersStampDuty() {
			return othersStampDuty;
		}

		public void setOthersStampDuty(String othersStampDuty) {
			this.othersStampDuty = othersStampDuty;
		}

		public String getOthersRegFee() {
			return othersRegFee;
		}

		public void setOthersRegFee(String othersRegFee) {
			this.othersRegFee = othersRegFee;
		}

	public String getHdnStatus() {
		return hdnStatus;
	}

	public void setHdnStatus(String hdnStatus) {
		this.hdnStatus = hdnStatus;
	}

	public String getFinalComments() {
		return finalComments;
	}

	public void setFinalComments(String finalComments) {
		this.finalComments = finalComments;
	}

	public String getDocTypeFlag() {
		return docTypeFlag;
	}

	public void setDocTypeFlag(String docTypeFlag) {
		this.docTypeFlag = docTypeFlag;
	}

	private ArrayList objectionList=new ArrayList();
   //added by shruti
    private byte[] docContents;
    
    public byte[] getDocContents() {
		return docContents;
	}

	public void setDocContents(byte[] docContents) {
		this.docContents = docContents;
	}

	public String getDbParaId() {
		return dbParaId;
	}

	public void setDbParaId(String dbParaId) {
		this.dbParaId = dbParaId;
	}

    
    public ArrayList getObjectionList() {
		return objectionList;
	}

	public void setObjectionList(ArrayList objectionList) {
		this.objectionList = objectionList;
	}

	public String getObjStatus() {
		return objStatus;
	}

	public void setObjStatus(String objStatus) {
		this.objStatus = objStatus;
	}

	public String getObjId() {
		return objId;
	}

	public void setObjId(String objId) {
		this.objId = objId;
	}

	public String getObjDtls() {
		return objDtls;
	}

	public void setObjDtls(String objDtls) {
		this.objDtls = objDtls;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getObjDate() {
		return objDate;
	}

	public void setObjDate(String objDate) {
		this.objDate = objDate;
	}

	public String getDefRegFee() {
		return defRegFee;
	}

	public void setDefRegFee(String defRegFee) {
		this.defRegFee = defRegFee;
	}

	public String getDefStampDuty() {
		return defStampDuty;
	}

	public void setDefStampDuty(String defStampDuty) {
		this.defStampDuty = defStampDuty;
	}

	public String getValReg() {
		return valReg;
	}

	public void setValReg(String valReg) {
		this.valReg = valReg;
	}

	public String getValAgmp() {
		return valAgmp;
	}

	public void setValAgmp(String valAgmp) {
		this.valAgmp = valAgmp;
	}

	private ArrayList DROList = new ArrayList();	 
    private ArrayList SROList = new ArrayList();	 
    private ArrayList HOList = new ArrayList();	 
    private ArrayList offcList = new ArrayList();	 	 
    private ArrayList paraList = new ArrayList();
    private ArrayList paraStatusList = new ArrayList();
    private ArrayList agmpSearchList = new ArrayList();
    private ArrayList paraSearchList = new ArrayList();

    
    
    /**
	 * @return the recFlag
	 */
	public String getRecFlag() {
		return recFlag;
	}

	/**
	 * @param recFlag the recFlag to set
	 */
	public void setRecFlag(String recFlag) {
		this.recFlag = recFlag;
	}

	/**
	 * @return the offcId
	 */
	public String getOffcId() {
		return offcId;
	}

	/**
	 * @param offcId the offcId to set
	 */
	public void setOffcId(String offcId) {
		this.offcId = offcId;
	}

	/**
	 * @return the droId
	 */
	public String getDroId() {
		return droId;
	}

	/**
	 * @param droId the droId to set
	 */
	public void setDroId(String droId) {
		this.droId = droId;
	}

	/**
	 * @return the hoId
	 */
	public String getHoId() {
		return hoId;
	}

	/**
	 * @param hoId the hoId to set
	 */
	public void setHoId(String hoId) {
		this.hoId = hoId;
	}

	/**
	 * @return the droName
	 */
	public String getDroName() {
		return droName;
	}

	/**
	 * @param droName the droName to set
	 */
	public void setDroName(String droName) {
		this.droName = droName;
	}

	/**
	 * @return the hoName
	 */
	public String getHoName() {
		return hoName;
	}

	/**
	 * @param hoName the hoName to set
	 */
	public void setHoName(String hoName) {
		this.hoName = hoName;
	}

	/**
	 * @return the auditFromMonth
	 */
	public String getAuditFromMonth() {
		return auditFromMonth;
	}

	/**
	 * @param auditFromMonth the auditFromMonth to set
	 */
	public void setAuditFromMonth(String auditFromMonth) {
		this.auditFromMonth = auditFromMonth;
	}

	/**
	 * @return the auditFromYear
	 */
	public String getAuditFromYear() {
		return auditFromYear;
	}

	/**
	 * @param auditFromYear the auditFromYear to set
	 */
	public void setAuditFromYear(String auditFromYear) {
		this.auditFromYear = auditFromYear;
	}

	/**
	 * @return the auditToMonth
	 */
	public String getAuditToMonth() {
		return auditToMonth;
	}

	/**
	 * @param auditToMonth the auditToMonth to set
	 */
	public void setAuditToMonth(String auditToMonth) {
		this.auditToMonth = auditToMonth;
	}

	/**
	 * @return the auditToYear
	 */
	public String getAuditToYear() {
		return auditToYear;
	}

	/**
	 * @param auditToYear the auditToYear to set
	 */
	public void setAuditToYear(String auditToYear) {
		this.auditToYear = auditToYear;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the reportStatus
	 */
	public String getReportStatus() {
		return reportStatus;
	}

	/**
	 * @param reportStatus the reportStatus to set
	 */
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	/**
	 * @return the paraStatusId
	 */
	public String getParaStatusId() {
		return paraStatusId;
	}

	/**
	 * @param paraStatusId the paraStatusId to set
	 */
	public void setParaStatusId(String paraStatusId) {
		this.paraStatusId = paraStatusId;
	}

	/**
	 * @return the paraTypeName
	 */
	public String getParaTypeName() {
		return paraTypeName;
	}

	/**
	 * @param paraTypeName the paraTypeName to set
	 */
	public void setParaTypeName(String paraTypeName) {
		this.paraTypeName = paraTypeName;
	}

	/**
	 * @return the auditDate
	 */
	public String getAuditDate() {
		return auditDate;
	}

	/**
	 * @param auditDate the auditDate to set
	 */
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	/**
	 * @return the audEntryDate
	 */
	public String getAudEntryDate() {
		return audEntryDate;
	}

	/**
	 * @param audEntryDate the audEntryDate to set
	 */
	public void setAudEntryDate(String audEntryDate) {
		this.audEntryDate = audEntryDate;
	}

	/**
	 * @return the auditBodyType
	 */
	public String getAuditBodyType() {
		return auditBodyType;
	}

	/**
	 * @param auditBodyType the auditBodyType to set
	 */
	public void setAuditBodyType(String auditBodyType) {
		this.auditBodyType = auditBodyType;
	}

	/**
	 * @return the paraNamee
	 */
	public String getParaNamee() {
		return paraNamee;
	}

	/**
	 * @param paraNamee the paraNamee to set
	 */
	public void setParaNamee(String paraNamee) {
		this.paraNamee = paraNamee;
	}

	/**
	 * @return the paraNum
	 */
	public String getParaNum() {
		return paraNum;
	}

	/**
	 * @param paraNum the paraNum to set
	 */
	public void setParaNum(String paraNum) {
		this.paraNum = paraNum;
	}

	/**
	 * @return the paraComments
	 */
	public String getParaComments() {
		return paraComments;
	}

	/**
	 * @param paraComments the paraComments to set
	 */
	public void setParaComments(String paraComments) {
		this.paraComments = paraComments;
	}

	/**
	 * @return the dROList
	 */
	public ArrayList getDROList() {
		return DROList;
	}

	/**
	 * @param dROList the dROList to set
	 */
	public void setDROList(ArrayList dROList) {
		DROList = dROList;
	}

	/**
	 * @return the sROList
	 */
	public ArrayList getSROList() {
		return SROList;
	}

	/**
	 * @param sROList the sROList to set
	 */
	public void setSROList(ArrayList sROList) {
		SROList = sROList;
	}

	/**
	 * @return the hOList
	 */
	public ArrayList getHOList() {
		return HOList;
	}

	/**
	 * @param hOList the hOList to set
	 */
	public void setHOList(ArrayList hOList) {
		HOList = hOList;
	}

	/**
	 * @return the offcList
	 */
	public ArrayList getOffcList() {
		return offcList;
	}

	/**
	 * @param offcList the offcList to set
	 */
	public void setOffcList(ArrayList offcList) {
		this.offcList = offcList;
	}

	/**
	 * @return the paraList
	 */
	public ArrayList getParaList() {
		return paraList;
	}

	/**
	 * @param paraList the paraList to set
	 */
	public void setParaList(ArrayList paraList) {
		this.paraList = paraList;
	}

	/**
	 * @return the paraStatusList
	 */
	public ArrayList getParaStatusList() {
		return paraStatusList;
	}

	/**
	 * @param paraStatusList the paraStatusList to set
	 */
	public void setParaStatusList(ArrayList paraStatusList) {
		this.paraStatusList = paraStatusList;
	}

	/**
	 * @return the agmpSearchList
	 */
	public ArrayList getAgmpSearchList() {
		return agmpSearchList;
	}

	/**
	 * @param agmpSearchList the agmpSearchList to set
	 */
	public void setAgmpSearchList(ArrayList agmpSearchList) {
		this.agmpSearchList = agmpSearchList;
	}

	/**
	 * @return the paraSearchList
	 */
	public ArrayList getParaSearchList() {
		return paraSearchList;
	}

	/**
	 * @param paraSearchList the paraSearchList to set
	 */
	public void setParaSearchList(ArrayList paraSearchList) {
		this.paraSearchList = paraSearchList;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getParaStatus() {
		return paraStatus;
	}

	public void setParaStatus(String paraStatus) {
		this.paraStatus = paraStatus;
	}


	public String getParaId() {
		return paraId;
	}

	public void setParaId(String paraId) {
		this.paraId = paraId;
	}

	public ArrayList getDocDetailsList() {
		return docDetailsList;
	}

	public void setDocDetailsList(ArrayList docDetailsList) {
		this.docDetailsList = docDetailsList;
	}

	public ArrayList getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(ArrayList selectedItems) {
		this.selectedItems = selectedItems;
	}

	ArrayList docDetailsList=new ArrayList();

    ArrayList selectedItems=new ArrayList();


	
	public String getRegFlag() {
		return regFlag;
	}

	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}

	public String getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getTxtCaseID() {
		return txtCaseID;
	}

	public void setTxtCaseID(String txtCaseID) {
		this.txtCaseID = txtCaseID;
	}

	CaseIdSearch caseIdsearch[];
	CaseIdOtherSearch caseIdOther	= new CaseIdOtherSearch();
	
	public CaseIdOtherSearch getCaseIdOther() 
	{
		return caseIdOther;
	}

	public void setCaseIdOther(CaseIdOtherSearch caseIdOther) {
		this.caseIdOther = caseIdOther;
	}

	public CaseIdSearch[] getCaseIdsearch() {
		return caseIdsearch;
	}

	public void setCaseIdsearch(CaseIdSearch[] caseIdsearch) {
		this.caseIdsearch = caseIdsearch;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getDbParaType() {
		return dbParaType;
	}

	public void setDbParaType(String dbParaType) {
		this.dbParaType = dbParaType;
	}

	public String getDbParaName() {
		return dbParaName;
	}

	public void setDbParaName(String dbParaName) {
		this.dbParaName = dbParaName;
	}

	public String getDbParaNumber() {
		return dbParaNumber;
	}

	public void setDbParaNumber(String dbParaNumber) {
		this.dbParaNumber = dbParaNumber;
	}

	public String getDbParaStatus() {
		return dbParaStatus;
	}

	public void setDbParaStatus(String dbParaStatus) {
		this.dbParaStatus = dbParaStatus;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getPartyLink() {
		return partyLink;
	}

	public void setPartyLink(String partyLink) {
		this.partyLink = partyLink;
	}

	public String getPropertyLink() {
		return propertyLink;
	}

	public void setPropertyLink(String propertyLink) {
		this.propertyLink = propertyLink;
	}

	public String getMarketVal() {
		return marketVal;
	}

	public void setMarketVal(String marketVal) {
		this.marketVal = marketVal;
	}

	public String getTextParaNumber() {
		return textParaNumber;
	}

	public void setTextParaNumber(String textParaNumber) {
		this.textParaNumber = textParaNumber;
	}
	public String getTxtAuditDate() {
		return txtAuditDate;
	}

	public void setTxtAuditDate(String txtAuditDate) {
		this.txtAuditDate = txtAuditDate;
	}

	public String getTxtAuditLocation() {
		return txtAuditLocation;
	}

	public void setTxtAuditLocation(String txtAuditLocation) {
		this.txtAuditLocation = txtAuditLocation;
	}

	public String getTxtAuditorName() {
		return txtAuditorName;
	}

	public void setTxtAuditorName(String txtAuditorName) {
		this.txtAuditorName = txtAuditorName;
	}

	public String getTxtComments() {
		return txtComments;
	}

	public void setTxtComments(String txtComments) {
		this.txtComments = txtComments;
	}

	public String getTxtDispatchDate() {
		return txtDispatchDate;
	}

	public void setTxtDispatchDate(String txtDispatchDate) {
		this.txtDispatchDate = txtDispatchDate;
	}

	public String getTxtEntryDate() {
		return txtEntryDate;
	}

	public void setTxtEntryDate(String txtEntryDate) {
		this.txtEntryDate = txtEntryDate;
	}

	public String getTxtFromDate() {
		return txtFromDate;
	}

	public void setTxtFromDate(String txtFromDate) {
		this.txtFromDate = txtFromDate;
	}

	public String getTxtPhysicalId() {
		return txtPhysicalId;
	}

	public void setTxtPhysicalId(String txtPhysicalId) {
		this.txtPhysicalId = txtPhysicalId;
	}

	public String getTxtToDate() {
		return txtToDate;
	}

	public void setTxtToDate(String txtToDate) {
		this.txtToDate = txtToDate;
	}

	public String getFileAuditReport() {
		return fileAuditReport;
	}

	public void setFileAuditReport(String fileAuditReport) {
		this.fileAuditReport = fileAuditReport;
	}

	public String getFileOthers() {
		return fileOthers;
	}

	public void setFileOthers(String fileOthers) {
		this.fileOthers = fileOthers;
	}

	public String getAuditBody() {
		return auditBody;
	}

	public void setAuditBody(String auditBody) {
		this.auditBody = auditBody;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getSroName() {
		return sroName;
	}

	public void setSroName(String sroName) {
		this.sroName = sroName;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getTextParaComments() {
		return textParaComments;
	}

	public void setTextParaComments(String textParaComments) {
		this.textParaComments = textParaComments;
	}

	public String getTextParaName() {
		return textParaName;
	}

	public void setTextParaName(String textParaName) {
		this.textParaName = textParaName;
	}

	public String getListParaType() {
		return listParaType;
	}

	public void setListParaType(String listParaType) {
		this.listParaType = listParaType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public RegIdSearch[] getRegIdsearch() {
		return regIdsearch;
	}

	public void setRegIdsearch(RegIdSearch[] regIdsearch) {
		this.regIdsearch = regIdsearch;
	}

	public ArrayList getSroList() {
		return sroList;
	}

	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}

	public String getSroId() {
		return sroId;
	}

	public void setSroId(String sroId) {
		this.sroId = sroId;
	}

	public RegIdOtherSearch[] getRegIdOthers() {
		return regIdOthers;
	}

	public void setRegIdOthers(RegIdOtherSearch[] regIdOthers) {
		this.regIdOthers = regIdOthers;
	}

	public RegIdOtherSearch getRegIdOther() {
		return regIdOther;
	}

	public void setRegIdOther(RegIdOtherSearch regIdOther) {
		this.regIdOther = regIdOther;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getTxtAgmpComm() {
		return txtAgmpComm;
	}

	public void setTxtAgmpComm(String txtAgmpComm) {
		this.txtAgmpComm = txtAgmpComm;
	}

	public String getTxtObjDetails() {
		return txtObjDetails;
	}

	public void setTxtObjDetails(String txtObjDetails) {
		this.txtObjDetails = txtObjDetails;
	}

	public String getTxtRegFee() {
		return txtRegFee;
	}

	public void setTxtRegFee(String txtRegFee) {
		this.txtRegFee = txtRegFee;
	}

	public String getTxtStampDuty() {
		return txtStampDuty;
	}

	public void setTxtStampDuty(String txtStampDuty) {
		this.txtStampDuty = txtStampDuty;
	}

	public String getTxtValAgmp() {
		return txtValAgmp;
	}

	public void setTxtValAgmp(String txtValAgmp) {
		this.txtValAgmp = txtValAgmp;
	}

	public String getAddMore() {
		return addMore;
	}

	public void setAddMore(String addMore) {
		this.addMore = addMore;
	}

	public AGMPReportDetailsDTO[] getAgmpReport() {
		return agmpReport;
	}

	public void setAgmpReport(AGMPReportDetailsDTO[] agmpReport) {
		this.agmpReport = agmpReport;
	}

	public String getTxtDocID() {
		return txtDocID;
	}

	public void setTxtDocID(String txtDocID) {
		this.txtDocID = txtDocID;
	}

	public String getDocStatusId() {
		return docStatusId;
	}

	public void setDocStatusId(String docStatusId) {
		this.docStatusId = docStatusId;
	}

	public String getTempValAgmp() {
		return tempValAgmp;
	}

	public void setTempValAgmp(String tempValAgmp) {
		this.tempValAgmp = tempValAgmp;
	}

	public String getTxtRegFee1() {
		return txtRegFee1;
	}

	public void setTxtRegFee1(String txtRegFee1) {
		this.txtRegFee1 = txtRegFee1;
	}

	public String getTxtSRname() {
		return txtSRname;
	}

	public void setTxtSRname(String txtSRname) {
		this.txtSRname = txtSRname;
	}

	public String getTxtStampDuty1() {
		return txtStampDuty1;
	}

	public void setTxtStampDuty1(String txtStampDuty1) {
		this.txtStampDuty1 = txtStampDuty1;
	}

	public String getRemoveAction() {
		return removeAction;
	}

	public void setRemoveAction(String removeAction) {
		this.removeAction = removeAction;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTempAction() {
		return tempAction;
	}

	public void setTempAction(String tempAction) {
		this.tempAction = tempAction;
	}

	public String getParaType() {
		return paraType;
	}

	public void setParaType(String paraType) {
		this.paraType = paraType;
	}

	public String getParaName() {
		return ParaName;
	}

	public void setParaName(String paraName) {
		ParaName = paraName;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getOfficeTypeId() {
		return officeTypeId;
	}

	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}

	public String getCloseAction() {
		return closeAction;
	}

	public void setCloseAction(String closeAction) {
		this.closeAction = closeAction;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getIsFinalPage() {
		return isFinalPage;
	}

	public void setIsFinalPage(String isFinalPage) {
		this.isFinalPage = isFinalPage;
	}
	
	
	public String paraTxnId;
	public ArrayList paraListView;
	public ArrayList getParaListView() {
		return paraListView;
	}

	public void setParaListView(ArrayList paraListView) {
		this.paraListView = paraListView;
	}

	public String getParaTxnId() {
		return paraTxnId;
	}

	public void setParaTxnId(String paraTxnId) {
		this.paraTxnId = paraTxnId;
	}

	
	
	private String complianceFlag;
	
	private String srCompilance;
	
	private String drComplinace;
	
	private String digCompliance;
	
	private String igCompliance;

	public String getComplianceFlag() {
		return complianceFlag;
	}

	public void setComplianceFlag(String complianceFlag) {
		this.complianceFlag = complianceFlag;
	}

	public String getSrCompilance() {
		return srCompilance;
	}

	public void setSrCompilance(String srCompilance) {
		this.srCompilance = srCompilance;
	}

	public String getDrComplinace() {
		return drComplinace;
	}

	public void setDrComplinace(String drComplinace) {
		this.drComplinace = drComplinace;
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
	private String role;

	private String reportSubType;
	private String reportSubType1;
	private String reportId;
	private String createdDate;
	private String auditStatus;
	private ArrayList auditSearchList;

	public ArrayList getAuditSearchList() {
		return auditSearchList;
	}

	public void setAuditSearchList(ArrayList auditSearchList) {
		this.auditSearchList = auditSearchList;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getReportSubType() {
		return reportSubType;
	}

	public void setReportSubType(String reportSubType) {
		this.reportSubType = reportSubType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}

	public String getCompliance() {
		return compliance;
	}

	private String compliance;
	
	
	/// added by simran for sr/dr chk
	
	private String offcChk;




	public String getOffcChk() {
		return offcChk;
	}

	public void setOffcChk(String offcChk) {
		this.offcChk = offcChk;
	}

	public void setReportSubType1(String reportSubType1) {
		this.reportSubType1 = reportSubType1;
	}

	public String getReportSubType1() {
		return reportSubType1;
	}
	
	

}
