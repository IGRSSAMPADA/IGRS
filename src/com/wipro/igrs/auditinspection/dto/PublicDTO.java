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

import org.apache.struts.upload.FormFile;

public class PublicDTO implements Serializable{

	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getPubInspectionId() {
		return pubInspectionId;
	}
	public void setPubInspectionId(String pubInspectionId) {
		this.pubInspectionId = pubInspectionId;
	}
	private String reportType;
	private String toDate;
	private String fromDate;
	private String pubOfficeName;
	private String regFlag1;
	private String paraId;
	private String paraStatus;
	private String objDtls;
	private String valReg;
	private String defStampDuty;
	private String objTxnId;
	private String paraTxnid;
	private ArrayList paraStatusList;
	
	private String srCompliance;
	private String complianceFlag;
	
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
	private String drCompliance;
	
	private String digCompliance;
	
	private String igCompliance;
	public ArrayList getParaStatusList() {
		return paraStatusList;
	}
	public void setParaStatusList(ArrayList paraStatusList) {
		this.paraStatusList = paraStatusList;
	}
	public String getParaTxnid() {
		return paraTxnid;
	}
	public void setParaTxnid(String paraTxnid) {
		this.paraTxnid = paraTxnid;
	}
	public String getObjTxnId() {
		return objTxnId;
	}
	public void setObjTxnId(String objTxnId) {
		this.objTxnId = objTxnId;
	}
	public String getObjDtls() {
		return objDtls;
	}
	public void setObjDtls(String objDtls) {
		this.objDtls = objDtls;
	}
	public String getValReg() {
		return valReg;
	}
	public void setValReg(String valReg) {
		this.valReg = valReg;
	}
	public String getDefStampDuty() {
		return defStampDuty;
	}
	public void setDefStampDuty(String defStampDuty) {
		this.defStampDuty = defStampDuty;
	}
	public String getDefRegFee() {
		return defRegFee;
	}
	public void setDefRegFee(String defRegFee) {
		this.defRegFee = defRegFee;
	}
	public String getObjDate() {
		return objDate;
	}
	public void setObjDate(String objDate) {
		this.objDate = objDate;
	}
	private String defRegFee;
	private String objDate;
	private String valAgmp;
	public String getValAgmp() {
		return valAgmp;
	}
	public void setValAgmp(String valAgmp) {
		this.valAgmp = valAgmp;
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
	public String getRegFlag1() {
		return regFlag1;
	}
	public void setRegFlag1(String regFlag) {
		this.regFlag1 = regFlag;
	}
	private String paraTypeName;
	public String getParaTypeName() {
		return paraTypeName;
	}
	public void setParaTypeName(String paraTypeName) {
		this.paraTypeName = paraTypeName;
	}
	private String objId;
	private String objStatus;
	private String caseId;
	public String getObjId() {
		return objId;
	}
	public void setObjId(String objId) {
		this.objId = objId;
	}
	public String getObjStatus() {
		return objStatus;
	}
	public void setObjStatus(String objStatus) {
		this.objStatus = objStatus;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	private String address;
	private String district;
	private String insDate;
	private String insEntryDate;
	
	private String comments;
	
	private FormFile auditReport;
	private FormFile others;
	
	
	private String districtName;
	private String districtId;
	private ArrayList districtList;
	
	private String officeName;
	private String officeId;
	private ArrayList officeList;
	
	private String reportId;
	
	private String paraType;
	private String paraName;
	private String paraComments;
	
	private String txtDocID;
	private String txtSRname;
	private String txtStampDuty1;
	private String txtRegFee1;
	private String tempValAgmp;
	private String txtStampDuty;
	private String txtRegFee;
	private String txtObjDetails;
	private String txtAgmpComm;
	private String objectionStatus;
	
	private String docType;
	private String regId;
	private String createdDate;
	
	private String txnId;
	private String docTxnId;
	private String docName;
	
	//private ArrayList<POIreportDTO> listPOIReportDto = new ArrayList();
	//private ArrayList<POIreportDTO1>listPOIReportDto1=new ArrayList();
	
	// added by Vinay
	
	private String dispatchDate;
	
	private String dispatchNumber;
	
	private String pubInspectionId;
	
	private String inspectionStatus;
	
	private String actionName;
	
	private String docId;
	private String objDetails;
	private String agmpComments;
	//added by shruti-15th oct 2013
	private String regFlag;
	//added by ShreeraJ 21/Nov-13
	private String docPath;
	private String inspID;
	
	
//Added by rachita
	 // Added by Rachita for Others
    
    
	   private String othersRegIdPoi;    
	    private String othersRegDatePoi;    
	    private String othersSroNamePoi;
	    

		private String othersSrNamePoi;
	    private String othersValAtRegTimePoi;
	    private String othersConsiderationAmtPoi;
	    private String othersDutyFeePoi;
	    private String othersStampDutyPoi;
	    private String othersRegFeePoi;
	   
	    public String getOthersRegIdPoi() {
			return othersRegIdPoi;
		}
		public void setOthersRegIdPoi(String othersRegIdPoi) {
			this.othersRegIdPoi = othersRegIdPoi;
		}
		public String getOthersRegDatePoi() {
			return othersRegDatePoi;
		}
		public void setOthersRegDatePoi(String othersRegDatePoi) {
			this.othersRegDatePoi = othersRegDatePoi;
		}
		public String getOthersSroNamePoi() {
			return othersSroNamePoi;
		}
		public void setOthersSroNamePoi(String othersSroNamePoi) {
			this.othersSroNamePoi = othersSroNamePoi;
		}
		public String getOthersSrNamePoi() {
			return othersSrNamePoi;
		}
		public void setOthersSrNamePoi(String othersSrNamePoi) {
			this.othersSrNamePoi = othersSrNamePoi;
		}
		public String getOthersValAtRegTimePoi() {
			return othersValAtRegTimePoi;
		}
		public void setOthersValAtRegTimePoi(String othersValAtRegTimePoi) {
			this.othersValAtRegTimePoi = othersValAtRegTimePoi;
		}
		public String getOthersConsiderationAmtPoi() {
			return othersConsiderationAmtPoi;
		}
		public void setOthersConsiderationAmtPoi(String othersConsiderationAmtPoi) {
			this.othersConsiderationAmtPoi = othersConsiderationAmtPoi;
		}
		public String getOthersDutyFeePoi() {
			return othersDutyFeePoi;
		}
		public void setOthersDutyFeePoi(String othersDutyFeePoi) {
			this.othersDutyFeePoi = othersDutyFeePoi;
		}
		public String getOthersStampDutyPoi() {
			return othersStampDutyPoi;
		}
		public void setOthersStampDutyPoi(String othersStampDutyPoi) {
			this.othersStampDutyPoi = othersStampDutyPoi;
		}
		public String getOthersRegFeePoi() {
			return othersRegFeePoi;
		}
		public void setOthersRegFeePoi(String othersRegFeePoi) {
			this.othersRegFeePoi = othersRegFeePoi;
		}
		public String getOthersDocTypePoi() {
			return othersDocTypePoi;
		}
		public void setOthersDocTypePoi(String othersDocTypePoi) {
			this.othersDocTypePoi = othersDocTypePoi;
		}
		public String getOthersFlagPoi() {
			return othersFlagPoi;
		}
		public void setOthersFlagPoi(String othersFlagPoi) {
			this.othersFlagPoi = othersFlagPoi;
		}
		private String othersDocTypePoi;
	    private String othersFlagPoi;
	
	/**
	 * @return the inspID
	 */
	public String getInspID() {
		return inspID;
	}
	/**
	 * @param inspID the inspID to set
	 */
	public void setInspID(String inspID) {
		this.inspID = inspID;
	}
	/**
	 * @return the docPath
	 */
	public String getDocPath() {
		return docPath;
	}
	/**
	 * @param docPath the docPath to set
	 */
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getRegFlag() {
		return regFlag;
	}
	public void setRegFlag(String regFlag) {
		this.regFlag = regFlag;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getObjDetails() {
		return objDetails;
	}
	public void setObjDetails(String objDetails) {
		this.objDetails = objDetails;
	}
	public String getAgmpComments() {
		return agmpComments;
	}
	public void setAgmpComments(String agmpComments) {
		this.agmpComments = agmpComments;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getInspectionStatus() {
		return inspectionStatus;
	}
	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getDispatchNumber() {
		return dispatchNumber;
	}
	public void setDispatchNumber(String dispatchNumber) {
		this.dispatchNumber = dispatchNumber;
	}
	/**
     * @return the docTxnId
     */
    public String getDocTxnId() {
    	return docTxnId;
    }
	/**
     * @param docTxnId the docTxnId to set
     */
    public void setDocTxnId(String docTxnId) {
    	this.docTxnId = docTxnId;
    }
	/**
     * @return the docName
     */
    public String getDocName() {
    	return docName;
    }
	/**
     * @param docName the docName to set
     */
    public void setDocName(String docName) {
    	this.docName = docName;
    }
	/**
     * @return the txnId
     */
    public String getTxnId() {
    	return txnId;
    }
	/**
     * @param txnId the txnId to set
     */
    public void setTxnId(String txnId) {
    	this.txnId = txnId;
    }
	/**
     * @return the docType
     */
    public String getDocType() {
    	return docType;
    }
	/**
     * @param docType the docType to set
     */
    public void setDocType(String docType) {
    	this.docType = docType;
    }
	/**
     * @return the regId
     */
    public String getRegId() {
    	return regId;
    }
	/**
     * @param regId the regId to set
     */
    public void setRegId(String regId) {
    	this.regId = regId;
    }
	/**
     * @return the tempValAgmp
     */
    public String getTempValAgmp() {
    	return tempValAgmp;
    }
	/**
     * @param tempValAgmp the tempValAgmp to set
     */
    public void setTempValAgmp(String tempValAgmp) {
    	this.tempValAgmp = tempValAgmp;
    }
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getPubOfficeName() {
		return pubOfficeName;
	}
	public void setPubOfficeName(String pubOfficeName) {
		this.pubOfficeName = pubOfficeName;
	}
	
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public ArrayList getOfficeList() {
		return officeList;
	}
	public void setOfficeList(ArrayList officeList) {
		this.officeList = officeList;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getInsEntryDate() {
		return insEntryDate;
	}
	public void setInsEntryDate(String insEntryDate) {
		this.insEntryDate = insEntryDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public FormFile getAuditReport() {
		return auditReport;
	}
	public void setAuditReport(FormFile auditReport) {
		this.auditReport = auditReport;
	}
	public FormFile getOthers() {
		return others;
	}
	public void setOthers(FormFile others) {
		this.others = others;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
     * @return the paraType
     */
    public String getParaType() {
    	return paraType;
    }
	/**
     * @param paraType the paraType to set
     */
    public void setParaType(String paraType) {
    	this.paraType = paraType;
    }
	/**
     * @return the paraName
     */
    public String getParaName() {
    	return paraName;
    }
	/**
     * @param paraName the paraName to set
     */
    public void setParaName(String paraName) {
    	this.paraName = paraName;
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
     * @return the txtDocID
     */
    public String getTxtDocID() {
    	return txtDocID;
    }
	/**
     * @param txtDocID the txtDocID to set
     */
    public void setTxtDocID(String txtDocID) {
    	this.txtDocID = txtDocID;
    }
	/**
     * @return the txtSRname
     */
    public String getTxtSRname() {
    	return txtSRname;
    }
	/**
     * @param txtSRname the txtSRname to set
     */
    public void setTxtSRname(String txtSRname) {
    	this.txtSRname = txtSRname;
    }
	/**
     * @return the txtStampDuty1
     */
    public String getTxtStampDuty1() {
    	return txtStampDuty1;
    }
	/**
     * @param txtStampDuty1 the txtStampDuty1 to set
     */
    public void setTxtStampDuty1(String txtStampDuty1) {
    	this.txtStampDuty1 = txtStampDuty1;
    }
	/**
     * @return the txtRegFee1
     */
    public String getTxtRegFee1() {
    	return txtRegFee1;
    }
	/**
     * @param txtRegFee1 the txtRegFee1 to set
     */
    public void setTxtRegFee1(String txtRegFee1) {
    	this.txtRegFee1 = txtRegFee1;
    }
	/**
     * @return the txtStampDuty
     */
    public String getTxtStampDuty() {
    	return txtStampDuty;
    }
	/**
     * @param txtStampDuty the txtStampDuty to set
     */
    public void setTxtStampDuty(String txtStampDuty) {
    	this.txtStampDuty = txtStampDuty;
    }
	/**
     * @return the txtRegFee
     */
    public String getTxtRegFee() {
    	return txtRegFee;
    }
	/**
     * @param txtRegFee the txtRegFee to set
     */
    public void setTxtRegFee(String txtRegFee) {
    	this.txtRegFee = txtRegFee;
    }
	/**
     * @return the txtObjDetails
     */
    public String getTxtObjDetails() {
    	return txtObjDetails;
    }
	/**
     * @param txtObjDetails the txtObjDetails to set
     */
    public void setTxtObjDetails(String txtObjDetails) {
    	this.txtObjDetails = txtObjDetails;
    }
	/**
     * @return the txtAgmpComm
     */
    public String getTxtAgmpComm() {
    	return txtAgmpComm;
    }
	/**
     * @param txtAgmpComm the txtAgmpComm to set
     */
    public void setTxtAgmpComm(String txtAgmpComm) {
    	this.txtAgmpComm = txtAgmpComm;
    }
	/**
     * @return the objectionStatus
     */
    public String getObjectionStatus() {
    	return objectionStatus;
    }
	/**
     * @param objectionStatus the objectionStatus to set
     */
    public void setObjectionStatus(String objectionStatus) {
    	this.objectionStatus = objectionStatus;
    }
	/**
     * @return the createdDate
     */
    public String getCreatedDate() {
    	return createdDate;
    }
	/**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
    	this.createdDate = createdDate;
    }
	
  
    private String reportSubType;

	public String getReportSubType() {
		return reportSubType;
	}
	public void setReportSubType(String reportSubType) {
		this.reportSubType = reportSubType;
	}
    
	// for audit add complaince
	
	public String auditDate;
	
	public String auditEntryDate;
	
	public String auditStatus;

	public String role;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public ArrayList auditSearchList= new ArrayList();
	
	
	public ArrayList getAuditSearchList() {
		return auditSearchList;
	}
	public void setAuditSearchList(ArrayList auditSearchList) {
		this.auditSearchList = auditSearchList;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditEntryDate() {
		return auditEntryDate;
	}
	public void setAuditEntryDate(String auditEntryDate) {
		this.auditEntryDate = auditEntryDate;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	 
	
    
}