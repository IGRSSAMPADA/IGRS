package com.wipro.igrs.payment.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.payment.dto.PaymentServiceDTO;
import com.wipro.igrs.payment.dto.PaymentYearDto;

public class PaymentServiceForm extends ActionForm {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList serviceList=new ArrayList();
	private ArrayList<PaymentServiceDTO> offlineServiceList=new ArrayList<PaymentServiceDTO>();
//	private ArrayList fromYear=new ArrayList();
//	private ArrayList toYear=new ArrayList();
	private String refID;
	private String refAmount;
	private PaymentServiceDTO pDTO=new  PaymentServiceDTO();
	private String actionName;
	private String serviceID;
	private String payableAmt;
	private String uniqueID;
	private String userID;
	private String paidAmt;
	private String payDate;
	private String payMode;
	private String txnID;
	private String withSampada;
	private String offServiceID;
	private String userkey;
	private String userValue;
	private String finalFee="0";
	private String userName;	
	private String serviceName;
	private String paymentID;
	private String paymentDate;
	private String paymentMode;
	private String parentModRefID;
	private String parentModFunID;
	private String parentModAmount;
	private String withService;
	private String fromDate;
	private String toDate;
	private ArrayList<PaymentServiceDTO> paymentView=new ArrayList<PaymentServiceDTO>();
	private String radioPayment;
	private String enterableName;
	private String enterableValue;
	private String consumption;
	private String consumerID;
	private String consumerOffice;
	private String consumeDate;
	private String officeName;
	private String empName;
	private String offlineRefID;
	private String offlineRefValue;
	private String caseNo;
	private String partyName;
	private String tYear;
	private String fYear;
	private ArrayList fromYear=new ArrayList();
	private ArrayList toYear=new ArrayList();
	private ArrayList<PaymentYearDto> paymentYearDto=new ArrayList<PaymentYearDto>();
	
	
	
	public String gettYear() {
		return tYear;
	}

	public void settYear(String tYear) {
		this.tYear = tYear;
	}

	public String getfYear() {
		return fYear;
	}

	public void setfYear(String fYear) {
		this.fYear = fYear;
	}

	public ArrayList<PaymentYearDto> getPaymentYearDto() {
		return paymentYearDto;
	}

	public void setPaymentYearDto(ArrayList<PaymentYearDto> paymentYearDto) {
		this.paymentYearDto = paymentYearDto;
	}
	
	public ArrayList getFromYear() {
		return fromYear;
	}

	public void setFromYear(ArrayList fromYear) {
		this.fromYear = fromYear;
	}

	public ArrayList getToYear() {
		return toYear;
	}

	public void setToYear(ArrayList toYear) {
		this.toYear = toYear;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getOfflineRefValue() {
		return offlineRefValue;
	}

	public void setOfflineRefValue(String offlineRefValue) {
		this.offlineRefValue = offlineRefValue;
	}

	public String getOfflineRefID() {
		return offlineRefID;
	}

	public void setOfflineRefID(String offlineRefID) {
		this.offlineRefID = offlineRefID;
	}

	public String getConsumeDate() {
		return consumeDate;
	}

	public void setConsumeDate(String consumeDate) {
		this.consumeDate = consumeDate;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public String getConsumerID() {
		return consumerID;
	}

	public void setConsumerID(String consumerID) {
		this.consumerID = consumerID;
	}

	public String getConsumerOffice() {
		return consumerOffice;
	}

	public void setConsumerOffice(String consumerOffice) {
		this.consumerOffice = consumerOffice;
	}

	public String getEnterableName() {
		return enterableName;
	}

	public void setEnterableName(String enterableName) {
		this.enterableName = enterableName;
	}

	public String getEnterableValue() {
		return enterableValue;
	}

	public void setEnterableValue(String enterableValue) {
		this.enterableValue = enterableValue;
	}

	public String getRadioPayment() {
		return radioPayment;
	}

	public void setRadioPayment(String radioPayment) {
		this.radioPayment = radioPayment;
	}

	public ArrayList<PaymentServiceDTO> getPaymentView() {
		return paymentView;
	}

	public void setPaymentView(ArrayList<PaymentServiceDTO> paymentView) {
		this.paymentView = paymentView;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getWithService() {
		return withService;
	}

	public void setWithService(String withService) {
		this.withService = withService;
	}

	public String getParentModAmount() {
		return parentModAmount;
	}

	public void setParentModAmount(String parentModAmount) {
		this.parentModAmount = parentModAmount;
	}

	public String getParentModRefID() {
		return parentModRefID;
	}

	public void setParentModRefID(String parentModRefID) {
		this.parentModRefID = parentModRefID;
	}

	public String getParentModFunID() {
		return parentModFunID;
	}

	public void setParentModFunID(String parentModFunID) {
		this.parentModFunID = parentModFunID;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFinalFee() {
		return finalFee;
	}

	public void setFinalFee(String finalFee) {
		this.finalFee = finalFee;
	}

	public String getUserkey() {
		return userkey;
	}

	public void setUserkey(String userkey) {
		this.userkey = userkey;
	}

	public String getUserValue() {
		return userValue;
	}

	public void setUserValue(String userValue) {
		this.userValue = userValue;
	}

	public ArrayList<PaymentServiceDTO> getOfflineServiceList() {
		return offlineServiceList;
	}

	public void setOfflineServiceList(
			ArrayList<PaymentServiceDTO> offlineServiceList) {
		this.offlineServiceList = offlineServiceList;
	}

	public String getOffServiceID() {
		return offServiceID;
	}

	public void setOffServiceID(String offServiceID) {
		this.offServiceID = offServiceID;
	}

	public String getWithSampada() {
		return withSampada;
	}

	public void setWithSampada(String withSampada) {
		this.withSampada = withSampada;
	}

	public String getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(String paidAmt) {
		this.paidAmt = paidAmt;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public String getTxnID() {
		return txnID;
	}

	public void setTxnID(String txnID) {
		this.txnID = txnID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getPayableAmt() {
		return payableAmt;
	}

	public void setPayableAmt(String payableAmt) {
		this.payableAmt = payableAmt;
	}

	public String getServiceID() {
		return serviceID;
	}

	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public PaymentServiceDTO getPDTO() {
		return pDTO;
	}

	public void setPDTO(PaymentServiceDTO pdto) {
		pDTO = pdto;
	}

	public String getRefID() {
		return refID;
	}

	public void setRefID(String refID) {
		this.refID = refID;
	}

	public String getRefAmount() {
		return refAmount;
	}

	public void setRefAmount(String refAmount) {
		this.refAmount = refAmount;
	}

	public ArrayList getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList serviceList) {
		this.serviceList = serviceList;
	}


	private ArrayList<PaymentServiceDTO> districtList=new ArrayList<PaymentServiceDTO>();



	public ArrayList<PaymentServiceDTO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(ArrayList<PaymentServiceDTO> districtList) {
		this.districtList = districtList;
	}

	private String slectedDictrictID;
	public String getSlectedDictrictID() {
		return slectedDictrictID;
	}

	public void setSlectedDictrictID(String slectedDictrictID) {
		this.slectedDictrictID = slectedDictrictID;
	}
}
