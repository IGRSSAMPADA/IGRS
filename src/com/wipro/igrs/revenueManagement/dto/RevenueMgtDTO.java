package com.wipro.igrs.revenueManagement.dto;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class RevenueMgtDTO extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	private String value;
	private String name;
	private String functionId;
	private String functionName;
	private ArrayList functionList=new ArrayList();
	private String serviceId;
	private String serviceName;
	
	//below added by roopam
	
	private String servicePresent;
	private ArrayList matrixView= new ArrayList();
	private String radioView;
	private String editFlag;
	
	//end of addition by roopam
	
	private ArrayList serviceList = new ArrayList();
	private String radioSel="P";
	private String readioSelVal="P";
	private String revMjrHeadId;
	private ArrayList revMjrHeadList = new ArrayList();
	private String revMnrHeadId;
	private ArrayList revMnrHeadList = new ArrayList();
	private String revSubMjrHeadId;
	private ArrayList revSubMjrHeadList = new ArrayList();
	// for registered user
	private String ruRole;
	private String ruBasicVal;
	private String ruServiceVal;
	private String ruServiceDependId;
	private String ruServiceDependName;
	private String ruServiceOperatorId;
	private String ruServicePriorityId;
	private String ruTaxVal;
	private String ruTaxDependId;
	private String ruTaxDependName;
	private String ruTaxOperatorId;
	private String ruTaxPriorityId;
	private String ruCessVal;
	private String ruCessDependId;
	private String ruCessDependName;
	private String ruCessOperatorId;
	private String ruCessPriorityId;
	private String ruOtherVal;
	private String ruOtherDependId;
	private String ruOtherDependName;
	private String ruOtherOperatorId;
	private String ruOtherPriorityId;
	private String ruTotal;
	private String ruCalct;
	private ArrayList ruPriorityList=new ArrayList();
	private ArrayList ruDependList = new ArrayList();
	private ArrayList ruOperatorList=new ArrayList();
	//for SP/SPB/FI/PO
	private String spRole;
	private String spBasicVal;
	private String spServiceVal;
	private String spServiceDependId;
	private String spServiceDependName;
	private String spServiceOperatorId;
	private String spServicePriorityId;
	private String spTaxVal;
	private String spTaxDependId;
	private String spTaxDependName;
	private String spTaxOperatorId;
	private String spTaxPriorityId;
	private String spCessVal;
	private String spCessDependId;
	private String spCessDependName;
	private String spCessOperatorId;
	private String spCessPriorityId;
	private String spOtherVal;
	private String spOtherDependId;
	private String spOtherDependName;
	private String spOtherOperatorId;
	private String spOtherPriorityId;
	private String spTotal;
	private String spCalct;
	private ArrayList spPriorityList=new ArrayList();
	private ArrayList spDependList = new ArrayList();
	private ArrayList spOperatorList=new ArrayList();
	// for DRS Officials
	private String drRole;
	private String drBasicVal;
	private String drServiceVal;
	private String drServiceDependId;
	private String drServiceDependName;
	private String drServiceOperatorId;
	private String drServicePriorityId;
	private String drTaxVal;
	private String drTaxDependId;
	private String drTaxDependName;
	private String drTaxOperatorId;
	private String drTaxPriorityId;
	private String drCessVal;
	private String drCessDependId;
	private String drCessDependName;
	private String drCessOperatorId;
	private String drCessPriorityId;
	private String drOtherVal;
	private String drOtherDependId;
	private String drOtherDependName;
	private String drOtherOperatorId;
	private String drOtherPriorityId;
	private String drTotal;
	private String drCalct;
	private ArrayList drPriorityList=new ArrayList();
	private ArrayList drDependList = new ArrayList();
	private ArrayList drOperatorList=new ArrayList();
	
	
	// for revenue reconciliation 
	private String radioMode="1";
	private String radioModeName;
	private String districtName;    
    private String districtId;
    private String officeName;    
    private String officeId;
    private String officeTypeName;
    private String officeTypeId;
    private String yearId;
    private String yearName;
    private ArrayList districtList=new ArrayList();
    private ArrayList officeTypeList=new ArrayList();
    private ArrayList officeNameList=new ArrayList();
    private ArrayList yearList=new ArrayList();
    private ArrayList reportDetails=new ArrayList();
	
	private String transNo;
	private String transDtDwnld;
	private String transDtTreas;
	private String transAmntDwnld;
	private String transAmntTreas;
	private String transType;
	private String transPurp;
	private String transLocOff;
	private String transLocDis;
	private String transUser;
	private String transTotalDwnld;
	private String transTotalTreas;
	private ArrayList reconcOnChList=new ArrayList();
	
	// for messages
	private int isRuCalc=0;
	private int isSpCalc=0;
	private int isDrCalc=0;
	private int noRecFound=0;
	
	
	private String captureFeeFlag;
	private String feeBasedOnLabel;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getCaptureFeeFlag() {
		return captureFeeFlag;
	}
	public void setCaptureFeeFlag(String captureFeeFlag) {
		this.captureFeeFlag = captureFeeFlag;
	}
	public String getFeeBasedOnLabel() {
		return feeBasedOnLabel;
	}
	public void setFeeBasedOnLabel(String feeBasedOnLabel) {
		this.feeBasedOnLabel = feeBasedOnLabel;
	}
	public String getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public String getRadioView() {
		return radioView;
	}
	public void setRadioView(String radioView) {
		this.radioView = radioView;
	}
	public ArrayList getMatrixView() {
		return matrixView;
	}
	public void setMatrixView(ArrayList matrixView) {
		this.matrixView = matrixView;
	}
	public String getServicePresent() {
		return servicePresent;
	}
	public void setServicePresent(String servicePresent) {
		this.servicePresent = servicePresent;
	}
	public String getRadioModeName() {
		return radioModeName;
	}
	public void setRadioModeName(String radioModeName) {
		this.radioModeName = radioModeName;
	}
	public String getTransLocOff() {
		return transLocOff;
	}
	public void setTransLocOff(String transLocOff) {
		this.transLocOff = transLocOff;
	}
	public String getTransLocDis() {
		return transLocDis;
	}
	public void setTransLocDis(String transLocDis) {
		this.transLocDis = transLocDis;
	}
	public ArrayList getReconcOnChList() {
		return reconcOnChList;
	}
	public void setReconcOnChList(ArrayList reconcOnChList) {
		this.reconcOnChList = reconcOnChList;
	}
	public String getTransDtDwnld() {
		return transDtDwnld;
	}
	public void setTransDtDwnld(String transDtDwnld) {
		this.transDtDwnld = transDtDwnld;
	}
	public String getTransDtTreas() {
		return transDtTreas;
	}
	public void setTransDtTreas(String transDtTreas) {
		this.transDtTreas = transDtTreas;
	}
	public String getTransAmntTreas() {
		return transAmntTreas;
	}
	public void setTransAmntTreas(String transAmntTreas) {
		this.transAmntTreas = transAmntTreas;
	}
	public String getTransTotalDwnld() {
		return transTotalDwnld;
	}
	public void setTransTotalDwnld(String transTotalDwnld) {
		this.transTotalDwnld = transTotalDwnld;
	}
	public String getTransTotalTreas() {
		return transTotalTreas;
	}
	public void setTransTotalTreas(String transTotalTreas) {
		this.transTotalTreas = transTotalTreas;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	public String getTransAmntDwnld() {
		return transAmntDwnld;
	}
	public void setTransAmntDwnld(String transAmntDwnld) {
		this.transAmntDwnld = transAmntDwnld;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransPurp() {
		return transPurp;
	}
	public void setTransPurp(String transPurp) {
		this.transPurp = transPurp;
	}
	
	public String getTransUser() {
		return transUser;
	}
	public void setTransUser(String transUser) {
		this.transUser = transUser;
	}
	public String getYearId() {
		return yearId;
	}
	public void setYearId(String yearId) {
		this.yearId = yearId;
	}
	public String getYearName() {
		return yearName;
	}
	public void setYearName(String yearName) {
		this.yearName = yearName;
	}
	public ArrayList getYearList() {
		return yearList;
	}
	public void setYearList(ArrayList yearList) {
		this.yearList = yearList;
	}
	public ArrayList getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(ArrayList reportDetails) {
		this.reportDetails = reportDetails;
	}
	public int getNoRecFound() {
		return noRecFound;
	}
	public void setNoRecFound(int noRecFound) {
		this.noRecFound = noRecFound;
	}
	public String getRadioMode() {
		return radioMode;
	}
	public void setRadioMode(String radioMode) {
		this.radioMode = radioMode;
	}
	public String getReadioSelVal() {
		return readioSelVal;
	}
	public void setReadioSelVal(String readioSelVal) {
		this.readioSelVal = readioSelVal;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public ArrayList getFunctionList() {
		return functionList;
	}
	public void setFunctionList(ArrayList functionList) {
		this.functionList = functionList;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public ArrayList getServiceList() {
		return serviceList;
	}
	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}
	public String getRadioSel() {
		return radioSel;
	}
	public void setRadioSel(String radioSel) {
		this.radioSel = radioSel;
	}
	public String getRevMjrHeadId() {
		return revMjrHeadId;
	}
	public void setRevMjrHeadId(String revMjrHeadId) {
		this.revMjrHeadId = revMjrHeadId;
	}
	public ArrayList getRevMjrHeadList() {
		return revMjrHeadList;
	}
	public void setRevMjrHeadList(ArrayList revMjrHeadList) {
		this.revMjrHeadList = revMjrHeadList;
	}
	public String getRevMnrHeadId() {
		return revMnrHeadId;
	}
	public void setRevMnrHeadId(String revMnrHeadId) {
		this.revMnrHeadId = revMnrHeadId;
	}
	public ArrayList getRevMnrHeadList() {
		return revMnrHeadList;
	}
	public void setRevMnrHeadList(ArrayList revMnrHeadList) {
		this.revMnrHeadList = revMnrHeadList;
	}
	public String getRevSubMjrHeadId() {
		return revSubMjrHeadId;
	}
	public void setRevSubMjrHeadId(String revSubMjrHeadId) {
		this.revSubMjrHeadId = revSubMjrHeadId;
	}
	public ArrayList getRevSubMjrHeadList() {
		return revSubMjrHeadList;
	}
	public void setRevSubMjrHeadList(ArrayList revSubMjrHeadList) {
		this.revSubMjrHeadList = revSubMjrHeadList;
	}
	public String getRuRole() {
		return ruRole;
	}
	public void setRuRole(String ruRole) {
		this.ruRole = ruRole;
	}
	public String getRuBasicVal() {
		return ruBasicVal;
	}
	public void setRuBasicVal(String ruBasicVal) {
		this.ruBasicVal = ruBasicVal;
	}
	public String getRuServiceVal() {
		return ruServiceVal;
	}
	public void setRuServiceVal(String ruServiceVal) {
		this.ruServiceVal = ruServiceVal;
	}
	public String getRuServiceDependId() {
		return ruServiceDependId;
	}
	public void setRuServiceDependId(String ruServiceDependId) {
		this.ruServiceDependId = ruServiceDependId;
	}
	public String getRuServiceDependName() {
		return ruServiceDependName;
	}
	public void setRuServiceDependName(String ruServiceDependName) {
		this.ruServiceDependName = ruServiceDependName;
	}
	public String getRuServiceOperatorId() {
		return ruServiceOperatorId;
	}
	public void setRuServiceOperatorId(String ruServiceOperatorId) {
		this.ruServiceOperatorId = ruServiceOperatorId;
	}
	public String getRuServicePriorityId() {
		return ruServicePriorityId;
	}
	public void setRuServicePriorityId(String ruServicePriorityId) {
		this.ruServicePriorityId = ruServicePriorityId;
	}
	public String getRuTaxVal() {
		return ruTaxVal;
	}
	public void setRuTaxVal(String ruTaxVal) {
		this.ruTaxVal = ruTaxVal;
	}
	public String getRuTaxDependId() {
		return ruTaxDependId;
	}
	public void setRuTaxDependId(String ruTaxDependId) {
		this.ruTaxDependId = ruTaxDependId;
	}
	public String getRuTaxDependName() {
		return ruTaxDependName;
	}
	public void setRuTaxDependName(String ruTaxDependName) {
		this.ruTaxDependName = ruTaxDependName;
	}
	public String getRuTaxOperatorId() {
		return ruTaxOperatorId;
	}
	public void setRuTaxOperatorId(String ruTaxOperatorId) {
		this.ruTaxOperatorId = ruTaxOperatorId;
	}
	public String getRuTaxPriorityId() {
		return ruTaxPriorityId;
	}
	public void setRuTaxPriorityId(String ruTaxPriorityId) {
		this.ruTaxPriorityId = ruTaxPriorityId;
	}
	public String getRuCessVal() {
		return ruCessVal;
	}
	public void setRuCessVal(String ruCessVal) {
		this.ruCessVal = ruCessVal;
	}
	public String getRuCessDependId() {
		return ruCessDependId;
	}
	public void setRuCessDependId(String ruCessDependId) {
		this.ruCessDependId = ruCessDependId;
	}
	public String getRuCessDependName() {
		return ruCessDependName;
	}
	public void setRuCessDependName(String ruCessDependName) {
		this.ruCessDependName = ruCessDependName;
	}
	public String getRuCessOperatorId() {
		return ruCessOperatorId;
	}
	public void setRuCessOperatorId(String ruCessOperatorId) {
		this.ruCessOperatorId = ruCessOperatorId;
	}
	public String getRuCessPriorityId() {
		return ruCessPriorityId;
	}
	public void setRuCessPriorityId(String ruCessPriorityId) {
		this.ruCessPriorityId = ruCessPriorityId;
	}
	public String getRuOtherVal() {
		return ruOtherVal;
	}
	public void setRuOtherVal(String ruOtherVal) {
		this.ruOtherVal = ruOtherVal;
	}
	public String getRuOtherDependId() {
		return ruOtherDependId;
	}
	public void setRuOtherDependId(String ruOtherDependId) {
		this.ruOtherDependId = ruOtherDependId;
	}
	public String getRuOtherDependName() {
		return ruOtherDependName;
	}
	public void setRuOtherDependName(String ruOtherDependName) {
		this.ruOtherDependName = ruOtherDependName;
	}
	public String getRuOtherOperatorId() {
		return ruOtherOperatorId;
	}
	public void setRuOtherOperatorId(String ruOtherOperatorId) {
		this.ruOtherOperatorId = ruOtherOperatorId;
	}
	public String getRuOtherPriorityId() {
		return ruOtherPriorityId;
	}
	public void setRuOtherPriorityId(String ruOtherPriorityId) {
		this.ruOtherPriorityId = ruOtherPriorityId;
	}
	public String getRuTotal() {
		return ruTotal;
	}
	public void setRuTotal(String ruTotal) {
		this.ruTotal = ruTotal;
	}
	public String getRuCalct() {
		return ruCalct;
	}
	public void setRuCalct(String ruCalct) {
		this.ruCalct = ruCalct;
	}
	public ArrayList getRuPriorityList() {
		return ruPriorityList;
	}
	public void setRuPriorityList(ArrayList ruPriorityList) {
		this.ruPriorityList = ruPriorityList;
	}
	public ArrayList getRuDependList() {
		return ruDependList;
	}
	public void setRuDependList(ArrayList ruDependList) {
		this.ruDependList = ruDependList;
	}
	public ArrayList getRuOperatorList() {
		return ruOperatorList;
	}
	public void setRuOperatorList(ArrayList ruOperatorList) {
		this.ruOperatorList = ruOperatorList;
	}
	public String getSpRole() {
		return spRole;
	}
	public void setSpRole(String spRole) {
		this.spRole = spRole;
	}
	public String getSpBasicVal() {
		return spBasicVal;
	}
	public void setSpBasicVal(String spBasicVal) {
		this.spBasicVal = spBasicVal;
	}
	public String getSpServiceVal() {
		return spServiceVal;
	}
	public void setSpServiceVal(String spServiceVal) {
		this.spServiceVal = spServiceVal;
	}
	public String getSpServiceDependId() {
		return spServiceDependId;
	}
	public void setSpServiceDependId(String spServiceDependId) {
		this.spServiceDependId = spServiceDependId;
	}
	public String getSpServiceDependName() {
		return spServiceDependName;
	}
	public void setSpServiceDependName(String spServiceDependName) {
		this.spServiceDependName = spServiceDependName;
	}
	public String getSpServiceOperatorId() {
		return spServiceOperatorId;
	}
	public void setSpServiceOperatorId(String spServiceOperatorId) {
		this.spServiceOperatorId = spServiceOperatorId;
	}
	public String getSpServicePriorityId() {
		return spServicePriorityId;
	}
	public void setSpServicePriorityId(String spServicePriorityId) {
		this.spServicePriorityId = spServicePriorityId;
	}
	public String getSpTaxVal() {
		return spTaxVal;
	}
	public void setSpTaxVal(String spTaxVal) {
		this.spTaxVal = spTaxVal;
	}
	public String getSpTaxDependId() {
		return spTaxDependId;
	}
	public void setSpTaxDependId(String spTaxDependId) {
		this.spTaxDependId = spTaxDependId;
	}
	public String getSpTaxDependName() {
		return spTaxDependName;
	}
	public void setSpTaxDependName(String spTaxDependName) {
		this.spTaxDependName = spTaxDependName;
	}
	public String getSpTaxOperatorId() {
		return spTaxOperatorId;
	}
	public void setSpTaxOperatorId(String spTaxOperatorId) {
		this.spTaxOperatorId = spTaxOperatorId;
	}
	public String getSpTaxPriorityId() {
		return spTaxPriorityId;
	}
	public void setSpTaxPriorityId(String spTaxPriorityId) {
		this.spTaxPriorityId = spTaxPriorityId;
	}
	public String getSpCessVal() {
		return spCessVal;
	}
	public void setSpCessVal(String spCessVal) {
		this.spCessVal = spCessVal;
	}
	public String getSpCessDependId() {
		return spCessDependId;
	}
	public void setSpCessDependId(String spCessDependId) {
		this.spCessDependId = spCessDependId;
	}
	public String getSpCessDependName() {
		return spCessDependName;
	}
	public void setSpCessDependName(String spCessDependName) {
		this.spCessDependName = spCessDependName;
	}
	public String getSpCessOperatorId() {
		return spCessOperatorId;
	}
	public void setSpCessOperatorId(String spCessOperatorId) {
		this.spCessOperatorId = spCessOperatorId;
	}
	public String getSpCessPriorityId() {
		return spCessPriorityId;
	}
	public void setSpCessPriorityId(String spCessPriorityId) {
		this.spCessPriorityId = spCessPriorityId;
	}
	public String getSpOtherVal() {
		return spOtherVal;
	}
	public void setSpOtherVal(String spOtherVal) {
		this.spOtherVal = spOtherVal;
	}
	public String getSpOtherDependId() {
		return spOtherDependId;
	}
	public void setSpOtherDependId(String spOtherDependId) {
		this.spOtherDependId = spOtherDependId;
	}
	public String getSpOtherDependName() {
		return spOtherDependName;
	}
	public void setSpOtherDependName(String spOtherDependName) {
		this.spOtherDependName = spOtherDependName;
	}
	public String getSpOtherOperatorId() {
		return spOtherOperatorId;
	}
	public void setSpOtherOperatorId(String spOtherOperatorId) {
		this.spOtherOperatorId = spOtherOperatorId;
	}
	public String getSpOtherPriorityId() {
		return spOtherPriorityId;
	}
	public void setSpOtherPriorityId(String spOtherPriorityId) {
		this.spOtherPriorityId = spOtherPriorityId;
	}
	public String getSpTotal() {
		return spTotal;
	}
	public void setSpTotal(String spTotal) {
		this.spTotal = spTotal;
	}
	public String getSpCalct() {
		return spCalct;
	}
	public void setSpCalct(String spCalct) {
		this.spCalct = spCalct;
	}
	public ArrayList getSpPriorityList() {
		return spPriorityList;
	}
	public void setSpPriorityList(ArrayList spPriorityList) {
		this.spPriorityList = spPriorityList;
	}
	public ArrayList getSpDependList() {
		return spDependList;
	}
	public void setSpDependList(ArrayList spDependList) {
		this.spDependList = spDependList;
	}
	public ArrayList getSpOperatorList() {
		return spOperatorList;
	}
	public void setSpOperatorList(ArrayList spOperatorList) {
		this.spOperatorList = spOperatorList;
	}
	public String getDrRole() {
		return drRole;
	}
	public void setDrRole(String drRole) {
		this.drRole = drRole;
	}
	public String getDrBasicVal() {
		return drBasicVal;
	}
	public void setDrBasicVal(String drBasicVal) {
		this.drBasicVal = drBasicVal;
	}
	public String getDrServiceVal() {
		return drServiceVal;
	}
	public void setDrServiceVal(String drServiceVal) {
		this.drServiceVal = drServiceVal;
	}
	public String getDrServiceDependId() {
		return drServiceDependId;
	}
	public void setDrServiceDependId(String drServiceDependId) {
		this.drServiceDependId = drServiceDependId;
	}
	public String getDrServiceDependName() {
		return drServiceDependName;
	}
	public void setDrServiceDependName(String drServiceDependName) {
		this.drServiceDependName = drServiceDependName;
	}
	public String getDrServiceOperatorId() {
		return drServiceOperatorId;
	}
	public void setDrServiceOperatorId(String drServiceOperatorId) {
		this.drServiceOperatorId = drServiceOperatorId;
	}
	public String getDrServicePriorityId() {
		return drServicePriorityId;
	}
	public void setDrServicePriorityId(String drServicePriorityId) {
		this.drServicePriorityId = drServicePriorityId;
	}
	public String getDrTaxVal() {
		return drTaxVal;
	}
	public void setDrTaxVal(String drTaxVal) {
		this.drTaxVal = drTaxVal;
	}
	public String getDrTaxDependId() {
		return drTaxDependId;
	}
	public void setDrTaxDependId(String drTaxDependId) {
		this.drTaxDependId = drTaxDependId;
	}
	public String getDrTaxDependName() {
		return drTaxDependName;
	}
	public void setDrTaxDependName(String drTaxDependName) {
		this.drTaxDependName = drTaxDependName;
	}
	public String getDrTaxOperatorId() {
		return drTaxOperatorId;
	}
	public void setDrTaxOperatorId(String drTaxOperatorId) {
		this.drTaxOperatorId = drTaxOperatorId;
	}
	public String getDrTaxPriorityId() {
		return drTaxPriorityId;
	}
	public void setDrTaxPriorityId(String drTaxPriorityId) {
		this.drTaxPriorityId = drTaxPriorityId;
	}
	public String getDrCessVal() {
		return drCessVal;
	}
	public void setDrCessVal(String drCessVal) {
		this.drCessVal = drCessVal;
	}
	public String getDrCessDependId() {
		return drCessDependId;
	}
	public void setDrCessDependId(String drCessDependId) {
		this.drCessDependId = drCessDependId;
	}
	public String getDrCessDependName() {
		return drCessDependName;
	}
	public void setDrCessDependName(String drCessDependName) {
		this.drCessDependName = drCessDependName;
	}
	public String getDrCessOperatorId() {
		return drCessOperatorId;
	}
	public void setDrCessOperatorId(String drCessOperatorId) {
		this.drCessOperatorId = drCessOperatorId;
	}
	public String getDrCessPriorityId() {
		return drCessPriorityId;
	}
	public void setDrCessPriorityId(String drCessPriorityId) {
		this.drCessPriorityId = drCessPriorityId;
	}
	public String getDrOtherVal() {
		return drOtherVal;
	}
	public void setDrOtherVal(String drOtherVal) {
		this.drOtherVal = drOtherVal;
	}
	public String getDrOtherDependId() {
		return drOtherDependId;
	}
	public void setDrOtherDependId(String drOtherDependId) {
		this.drOtherDependId = drOtherDependId;
	}
	public String getDrOtherDependName() {
		return drOtherDependName;
	}
	public void setDrOtherDependName(String drOtherDependName) {
		this.drOtherDependName = drOtherDependName;
	}
	public String getDrOtherOperatorId() {
		return drOtherOperatorId;
	}
	public void setDrOtherOperatorId(String drOtherOperatorId) {
		this.drOtherOperatorId = drOtherOperatorId;
	}
	public String getDrOtherPriorityId() {
		return drOtherPriorityId;
	}
	public void setDrOtherPriorityId(String drOtherPriorityId) {
		this.drOtherPriorityId = drOtherPriorityId;
	}
	public String getDrTotal() {
		return drTotal;
	}
	public void setDrTotal(String drTotal) {
		this.drTotal = drTotal;
	}
	public String getDrCalct() {
		return drCalct;
	}
	public void setDrCalct(String drCalct) {
		this.drCalct = drCalct;
	}
	public ArrayList getDrPriorityList() {
		return drPriorityList;
	}
	public void setDrPriorityList(ArrayList drPriorityList) {
		this.drPriorityList = drPriorityList;
	}
	public ArrayList getDrDependList() {
		return drDependList;
	}
	public void setDrDependList(ArrayList drDependList) {
		this.drDependList = drDependList;
	}
	public ArrayList getDrOperatorList() {
		return drOperatorList;
	}
	public void setDrOperatorList(ArrayList drOperatorList) {
		this.drOperatorList = drOperatorList;
	}
	public int getIsRuCalc() {
		return isRuCalc;
	}
	public void setIsRuCalc(int isRuCalc) {
		this.isRuCalc = isRuCalc;
	}
	public int getIsSpCalc() {
		return isSpCalc;
	}
	public void setIsSpCalc(int isSpCalc) {
		this.isSpCalc = isSpCalc;
	}
	public int getIsDrCalc() {
		return isDrCalc;
	}
	public void setIsDrCalc(int isDrCalc) {
		this.isDrCalc = isDrCalc;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOfficeTypeName() {
		return officeTypeName;
	}
	public void setOfficeTypeName(String officeTypeName) {
		this.officeTypeName = officeTypeName;
	}
	public String getOfficeTypeId() {
		return officeTypeId;
	}
	public void setOfficeTypeId(String officeTypeId) {
		this.officeTypeId = officeTypeId;
	}
	public ArrayList getDistrictList() {
		return districtList;
	}
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	public ArrayList getOfficeTypeList() {
		return officeTypeList;
	}
	public void setOfficeTypeList(ArrayList officeTypeList) {
		this.officeTypeList = officeTypeList;
	}
	public ArrayList getOfficeNameList() {
		return officeNameList;
	}
	public void setOfficeNameList(ArrayList officeNameList) {
		this.officeNameList = officeNameList;
	}
	
	
	
	
}
