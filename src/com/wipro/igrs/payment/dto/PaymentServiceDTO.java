package com.wipro.igrs.payment.dto;

import java.io.Serializable;

/**
 * ===========================================================================
 * File           :   PaymentServiceDTO.java
 * Description    :   Represents the  Payment BD Class
 * Author         :   Shreeraj Khare
 * Created Date   :   Jan 12, 2017

 * ===========================================================================
 */
public class PaymentServiceDTO implements Serializable {
	private String name;
    private String value;
    private String spAmount;
    private String ruAmount;
    private String drAmount;
    private String serviceID;
    private String districtid;
	private String districtname;
	///////////FOR OFFLINE POS
	private String offServiceID;
	private String fees;
	private String userEnterableParam;
	private String userEnterableValue;
	private String serviceDesc;
	private String funID;
	private String paymentTxnID;
	private String refernceID;
	private String paidAmt;
	private String payDate;
	private String payMode;
	private String consumptionStatus;
	private String referenceName;
	
	
	
	
	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public String getConsumptionStatus() {
		return consumptionStatus;
	}

	public void setConsumptionStatus(String consumptionStatus) {
		this.consumptionStatus = consumptionStatus;
	}

	public String getPaymentTxnID() {
		return paymentTxnID;
	}

	public void setPaymentTxnID(String paymentTxnID) {
		this.paymentTxnID = paymentTxnID;
	}

	public String getRefernceID() {
		return refernceID;
	}

	public void setRefernceID(String refernceID) {
		this.refernceID = refernceID;
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

	public String getFunID() {
		return funID;
	}

	public void setFunID(String funID) {
		this.funID = funID;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public String getOffServiceID() {
		return offServiceID;
	}

	public void setOffServiceID(String offServiceID) {
		this.offServiceID = offServiceID;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getUserEnterableParam() {
		return userEnterableParam;
	}

	public void setUserEnterableParam(String userEnterableParam) {
		this.userEnterableParam = userEnterableParam;
	}

	public String getUserEnterableValue() {
		return userEnterableValue;
	}

	public void setUserEnterableValue(String userEnterableValue) {
		this.userEnterableValue = userEnterableValue;
	}


	
	public String getDistrictid() {
		return districtid;
	}
	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}
	public String getDistrictname() {
		return districtname;
	}
	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	public String getDrAmount() {
		return drAmount;
	}
	public void setDrAmount(String drAmount) {
		this.drAmount = drAmount;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getSpAmount() {
		return spAmount;
	}
	public void setSpAmount(String spAmount) {
		this.spAmount = spAmount;
	}
	
	public String getRuAmount() {
		return ruAmount;
	}
	public void setRuAmount(String ruAmount) {
		this.ruAmount = ruAmount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    
    //added by saurav
	private String serviceDistrictName;
	private String districtID;

	public String getServiceDistrictName() {
		return serviceDistrictName;
	}

	public void setServiceDistrictName(String serviceDistrictName) {
		this.serviceDistrictName = serviceDistrictName;
	}

	

	public String getDistrictID() {
		return districtID;
	}

	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	private String slectedDictrictID;
	public String getSlectedDictrictID() {
		return slectedDictrictID;
	}

	public void setSlectedDictrictID(String slectedDictrictID) {
		this.slectedDictrictID = slectedDictrictID;
	}
}
