/**
 * 
 */
package com.wipro.igrs.revenueManagement.dto;

/**
 * @author RA836784
 *
 */

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.payments.dto.ChallanDTO;

public class RevMgtDTO {
	
	 //---------------------------Report details--------------------------------------

	   private String txnDate;
	  
	   //private Double txnAmt;
	   private String txnAmt; // converted into string by Roopam
	   private String txnId;
	   private String hdnTxnId;
	   private String txnType;
	   private String txnPurpose;
	  
	   private String districtName;
	   private String officeName;
	   private String receiptId;
	   private String userId;
	   private String notFound;
	   //satbir
	   
	   private String receiptID;
	   
	   
	   //---------------------------for Search Details--------------------------------------
	   private String durationTo;
	   private String durationFrom; 
	  
	   private String radioSelect;
	   private String radioSelectSecond="1";
	   private String paymentType;// may be challan/online/cash/credit limit
	  
	   
	   
	 //---------------------------for View--------------------------------------
		private ArrayList viewResultList=new ArrayList();
		private String viewComb;
		
		private int isViewEmpty=0;
		
	//---------------------------for Receipt Search--------------------------------------
		 
		
		
		
		private String radioSelectReceipt;
          private String receiptTextBox;	
          private String radioSelectReceipt2="1";
          private ArrayList viewResult=new ArrayList();
          
          //For Cash/Challan/Online Receipt Search
          private String cashId;
        //  private Double grossAmount;
          private String grossAmount;    // converted into string by roopam
          private String cashDate;
          private String cashPurposeId;
          private String cashCreatedBy;
          private String cashOfficeId;
          private String cashStatus;
          private String cashPurpose;
          private String cashOfficeName;
          private String cashUserName;
          private String cashUserId;
          private String paymentModeId;
          private String pdfOfficeName;
          
          
		
          
          // For Revenue Collection Report
          
         
		private ArrayList districtNameList;          
          private ArrayList officeIdList;
          private ArrayList officeNameList;
          private String radioMode;
          private String name;
          private String value;
          private String revenueOfficeName;
          
          //For messages
          private int noRecFound=0;
          
          
          
          // for search criteria
          private String revenueDistrictId;
          private String revenueDistrictName;
          private String revenueToDate;
          private String revenueFromDate;
          private String revenueOfficeId;
          private String revenueOfficeTypeName;
          private String revenueOfficeTypeId;
         
          
          // for details of cash/challan/online revenue collection
          private String cashTransactionId;  //challan serial no stored in case of challan
          private String cashCreatedDate;
          private String cashFunctionId;
          private String cashFunctionName;
          private String cashUserCreatedBy;
          private String cashGrossAmt;    //challan amt stored in case of challan
          private String cashPaymentType;
          private ArrayList viewCashRevenue=new ArrayList();
          private String sum;

		
          //by shreeraj
          private String purposeID;
          private ArrayList purposeList;
          private String totalCash="0";
          private String totalChallan="0";
          private String totalOnline="0";
          private String total="0";
          private String amtTreasury;//challan amt stored in case of challan amount in treasury
          private String sampadaSum;
          private String creditLimitMsg;
          private String challanMsg;
          
          public String getCreditLimitMsg() {
			return creditLimitMsg;
		}
		public void setCreditLimitMsg(String creditLimitMsg) {
			this.creditLimitMsg = creditLimitMsg;
		}
		public String getChallanMsg() {
			return challanMsg;
		}
		public void setChallanMsg(String challanMsg) {
			this.challanMsg = challanMsg;
		}
		
          
          
          
		public String getSampadaSum() {
			return sampadaSum;
		}
		public void setSampadaSum(String sampadaSum) {
			this.sampadaSum = sampadaSum;
		}
		public String getAmtTreasury() {
			return amtTreasury;
		}
		public void setAmtTreasury(String amtTreasury) {
			this.amtTreasury = amtTreasury;
		}
		public String getTotalCash() {
			return totalCash;
		}
		public void setTotalCash(String totalCash) {
			this.totalCash = totalCash;
		}
		public String getTotalChallan() {
			return totalChallan;
		}
		public void setTotalChallan(String totalChallan) {
			this.totalChallan = totalChallan;
		}
		public String getTotalOnline() {
			return totalOnline;
		}
		public void setTotalOnline(String totalOnline) {
			this.totalOnline = totalOnline;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		public String getPurposeID() {
			return purposeID;
		}
		public void setPurposeID(String purposeID) {
			this.purposeID = purposeID;
		}
		public ArrayList getPurposeList() {
			return purposeList;
		}
		public void setPurposeList(ArrayList purposeList) {
			this.purposeList = purposeList;
		}
		public String getGrossAmount() {
			return grossAmount;
		}
		public void setGrossAmount(String grossAmount) {
			this.grossAmount = grossAmount;
		}
		public String getTxnAmt() {
			return txnAmt;
		}
		public void setTxnAmt(String txnAmt) {
			this.txnAmt = txnAmt;
		}
		/**
  		 * @return the pdfOfficeName
  		 */
  		public String getPdfOfficeName() {
  			return pdfOfficeName;
  		}
  		/**
  		 * @param pdfOfficeName the pdfOfficeName to set
  		 */
  		public void setPdfOfficeName(String pdfOfficeName) {
  			this.pdfOfficeName = pdfOfficeName;
  		}
     
         
		/**
		 * @param cashGrossAmt the cashGrossAmt to set
		 */
		public void setCashGrossAmt(String cashGrossAmt) {
			this.cashGrossAmt = cashGrossAmt;
		}
		/**
		 * @return the sum
		 */
		public String getSum() {
			return sum;
		}
		/**
		 * @param sum the sum to set
		 */
		public void setSum(String sum) {
			this.sum = sum;
		}
		/**
		 * @return the revenueOfficeTypeName
		 */
		public String getRevenueOfficeTypeName() {
			return revenueOfficeTypeName;
		}
		/**
		 * @param revenueOfficeTypeName the revenueOfficeTypeName to set
		 */
		public void setRevenueOfficeTypeName(String revenueOfficeTypeName) {
			this.revenueOfficeTypeName = revenueOfficeTypeName;
		}
		/**
		 * @return the viewCashRevenue
		 */
		public ArrayList getViewCashRevenue() {
			return viewCashRevenue;
		}
		/**
		 * @param viewCashRevenue the viewCashRevenue to set
		 */
		public void setViewCashRevenue(ArrayList viewCashRevenue) {
			this.viewCashRevenue = viewCashRevenue;
		}
		/**
		 * @return the cashPaymentType
		 */
		public String getCashPaymentType() {
			return cashPaymentType;
		}
		/**
		 * @param cashPaymentType the cashPaymentType to set
		 */
		public void setCashPaymentType(String cashPaymentType) {
			this.cashPaymentType = cashPaymentType;
		}
		/**
		 * @return the cashTransactionId
		 */
		public String getCashTransactionId() {
			return cashTransactionId;
		}
		/**
		 * @param cashTransactionId the cashTransactionId to set
		 */
		public void setCashTransactionId(String cashTransactionId) {
			this.cashTransactionId = cashTransactionId;
		}
		/**
		 * @return the cashCreatedDate
		 */
		public String getCashCreatedDate() {
			return cashCreatedDate;
		}
		/**
		 * @param cashCreatedDate the cashCreatedDate to set
		 */
		public void setCashCreatedDate(String cashCreatedDate) {
			this.cashCreatedDate = cashCreatedDate;
		}
		/**
		 * @return the cashFunctionId
		 */
		public String getCashFunctionId() {
			return cashFunctionId;
		}
		/**
		 * @param cashFunctionId the cashFunctionId to set
		 */
		public void setCashFunctionId(String cashFunctionId) {
			this.cashFunctionId = cashFunctionId;
		}
		/**
		 * @return the cashFunctionName
		 */
		public String getCashFunctionName() {
			return cashFunctionName;
		}
		/**
		 * @param cashFunctionName the cashFunctionName to set
		 */
		public void setCashFunctionName(String cashFunctionName) {
			this.cashFunctionName = cashFunctionName;
		}
		/**
		 * @return the cashUserCreatedBy
		 */
		public String getCashUserCreatedBy() {
			return cashUserCreatedBy;
		}
		/**
		 * @param cashUserCreatedBy the cashUserCreatedBy to set
		 */
		public void setCashUserCreatedBy(String cashUserCreatedBy) {
			this.cashUserCreatedBy = cashUserCreatedBy;
		}
		
		/**
		 * @return the revenueDistrictId
		 */
		public String getRevenueDistrictId() {
			return revenueDistrictId;
		}
		/**
		 * @param revenueDistrictId the revenueDistrictId to set
		 */
		public void setRevenueDistrictId(String revenueDistrictId) {
			this.revenueDistrictId = revenueDistrictId;
		}
		/**
		 * @return the noRecFound
		 */
		public int getNoRecFound() {
			return noRecFound;
		}
		/**
		 * @param noRecFound the noRecFound to set
		 */
		public void setNoRecFound(int noRecFound) {
			this.noRecFound = noRecFound;
		}
		
      
      	
          
          
	
	
	/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}
	/**
		 * @return the radioMode
		 */
		public String getRadioMode() {
			return radioMode;
		}
		/**
		 * @param radioMode the radioMode to set
		 */
		public void setRadioMode(String radioMode) {
			this.radioMode = radioMode;
		}
	/**
		 * @return the officeIdList
		 */
		public ArrayList getOfficeIdList() {
			return officeIdList;
		}
		/**
		 * @param officeIdList the officeIdList to set
		 */
		public void setOfficeIdList(ArrayList officeIdList) {
			this.officeIdList = officeIdList;
		}
		/**
		 * @return the officeNameList
		 */
		public ArrayList getOfficeNameList() {
			return officeNameList;
		}
		/**
		 * @param officeNameList the officeNameList to set
		 */
		public void setOfficeNameList(ArrayList officeNameList) {
			this.officeNameList = officeNameList;
		}
		/**
		 * @return the revenueDistrictName
		 */
		public String getRevenueDistrictName() {
			return revenueDistrictName;
		}
		/**
		 * @param revenueDistrictName the revenueDistrictName to set
		 */
		public void setRevenueDistrictName(String revenueDistrictName) {
			this.revenueDistrictName = revenueDistrictName;
		}
		/**
		 * @return the revenueToDate
		 */
		public String getRevenueToDate() {
			return revenueToDate;
		}
		/**
		 * @param revenueToDate the revenueToDate to set
		 */
		public void setRevenueToDate(String revenueToDate) {
			this.revenueToDate = revenueToDate;
		}
		/**
		 * @return the revenueFromDate
		 */
		public String getRevenueFromDate() {
			return revenueFromDate;
		}
		/**
		 * @param revenueFromDate the revenueFromDate to set
		 */
		public void setRevenueFromDate(String revenueFromDate) {
			this.revenueFromDate = revenueFromDate;
		}
		/**
		 * @return the revenueOfficeId
		 */
		public String getRevenueOfficeId() {
			return revenueOfficeId;
		}
		/**
		 * @param revenueOfficeId the revenueOfficeId to set
		 */
		public void setRevenueOfficeId(String revenueOfficeId) {
			this.revenueOfficeId = revenueOfficeId;
		}
		/**
		 * @return the revenueOfficeName
		 */
		public String getRevenueOfficeName() {
			return revenueOfficeName;
		}
		/**
		 * @param revenueOfficeName the revenueOfficeName to set
		 */
		public void setRevenueOfficeName(String revenueOfficeName) {
			this.revenueOfficeName = revenueOfficeName;
		}
	
		
	/**
		 * @return the districtNameList
		 */
		public ArrayList getDistrictNameList() {
			return districtNameList;
		}
		/**
		 * @param districtNameList the districtNameList to set
		 */
		public void setDistrictNameList(ArrayList districtNameList) {
			this.districtNameList = districtNameList;
		}
	/**
		 * @return the paymentModeId
		 */
		public String getPaymentModeId() {
			return paymentModeId;
		}
		/**
		 * @param paymentModeId the paymentModeId to set
		 */
		public void setPaymentModeId(String paymentModeId) {
			this.paymentModeId = paymentModeId;
		}
	/**
		 * @return the cashUserName
		 */
		public String getCashUserName() {
			return cashUserName;
		}
		/**
		 * @param cashUserName the cashUserName to set
		 */
		public void setCashUserName(String cashUserName) {
			this.cashUserName = cashUserName;
		}
	/**
		 * @return the grossAmount
		 */
		/*public Double getGrossAmount() {
			return grossAmount;
		}*/
		/**
		 * @param grossAmount the grossAmount to set
		 */
		/*public void setGrossAmount(Double grossAmount) {
			this.grossAmount = grossAmount;
		}*/
	/**
		 * @return the cashId
		 */
		public String getCashId() {
			return cashId;
		}
		/**
		 * @param cashId the cashId to set
		 */
		public void setCashId(String cashId) {
			this.cashId = cashId;
		}
		
		/**
		 * @return the cashDate
		 */
		public String getCashDate() {
			return cashDate;
		}
		/**
		 * @param cashDate the cashDate to set
		 */
		public void setCashDate(String cashDate) {
			this.cashDate = cashDate;
		}
		/**
		 * @return the cashPurposeId
		 */
		public String getCashPurposeId() {
			return cashPurposeId;
		}
		/**
		 * @param cashPurposeId the cashPurposeId to set
		 */
		public void setCashPurposeId(String cashPurposeId) {
			this.cashPurposeId = cashPurposeId;
		}
		/**
		 * @return the cashCreatedBy
		 */
		public String getCashCreatedBy() {
			return cashCreatedBy;
		}
		/**
		 * @param cashCreatedBy the cashCreatedBy to set
		 */
		public void setCashCreatedBy(String cashCreatedBy) {
			this.cashCreatedBy = cashCreatedBy;
		}
		/**
		 * @return the cashOfficeId
		 */
		public String getCashOfficeId() {
			return cashOfficeId;
		}
		/**
		 * @param cashOfficeId the cashOfficeId to set
		 */
		public void setCashOfficeId(String cashOfficeId) {
			this.cashOfficeId = cashOfficeId;
		}
		/**
		 * @return the cashStatus
		 */
		public String getCashStatus() {
			return cashStatus;
		}
		/**
		 * @param cashStatus the cashStatus to set
		 */
		public void setCashStatus(String cashStatus) {
			this.cashStatus = cashStatus;
		}
		/**
		 * @return the cashPurpose
		 */
		public String getCashPurpose() {
			return cashPurpose;
		}
		/**
		 * @param cashPurpose the cashPurpose to set
		 */
		public void setCashPurpose(String cashPurpose) {
			this.cashPurpose = cashPurpose;
		}
		/**
		 * @return the cashOfficeName
		 */
		public String getCashOfficeName() {
			return cashOfficeName;
		}
		/**
		 * @param cashOfficeName the cashOfficeName to set
		 */
		public void setCashOfficeName(String cashOfficeName) {
			this.cashOfficeName = cashOfficeName;
		}
	
		/**
		 * @return the cashUserId
		 */
		public String getCashUserId() {
			return cashUserId;
		}
		/**
		 * @param cashUserId the cashUserId to set
		 */
		public void setCashUserId(String cashUserId) {
			this.cashUserId = cashUserId;
		}
	/**
		 * @return the receiptTextBox
		 */
		public String getReceiptTextBox() {
			return receiptTextBox;
		}
		/**
		 * @param receiptTextBox the receiptTextBox to set
		 */
		public void setReceiptTextBox(String receiptTextBox) {
			this.receiptTextBox = receiptTextBox;
		}
	/**
		 * @return the viewResult
		 */
		public ArrayList getViewResult() {
			return viewResult;
		}
		/**
		 * @param viewResult the viewResult to set
		 */
		public void setViewResult(ArrayList viewResult) {
			this.viewResult = viewResult;
		}
	/**
		 * @return the radioSelectReceipt2
		 */
		public String getRadioSelectReceipt2() {
			return radioSelectReceipt2;
		}
		/**
		 * @param radioSelectReceipt2 the radioSelectReceipt2 to set
		 */
		public void setRadioSelectReceipt2(String radioSelectReceipt2) {
			this.radioSelectReceipt2 = radioSelectReceipt2;
		}
	/**
		 * @return the radioSelectReceipt
		 */
		public String getRadioSelectReceipt() {
			return radioSelectReceipt;
		}
		/**
		 * @param radioSelectReceipt the radioSelectReceipt to set
		 */
		public void setRadioSelectReceipt(String radioSelectReceipt) {
			this.radioSelectReceipt = radioSelectReceipt;
		}
		
	/**
		 * @return the notFound
		 */
		public String getNotFound() {
			return notFound;
		}
		/**
		 * @param notFound the notFound to set
		 */
		public void setNotFound(String notFound) {
			this.notFound = notFound;
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
		 * @return the officeName
		 */
		public String getOfficeName() {
			return officeName;
		}
		/**
		 * @param officeName the officeName to set
		 */
		public void setOfficeName(String officeName) {
			this.officeName = officeName;
		}
	/**
		 * @return the hdnTxnId
		 */
		public String getHdnTxnId() {
			return hdnTxnId;
		}
		/**
		 * @param hdnTxnId the hdnTxnId to set
		 */
		public void setHdnTxnId(String hdnTxnId) {
			this.hdnTxnId = hdnTxnId;
		}
	/**
		 * @return the isViewEmpty
		 */
		public int getIsViewEmpty() {
			return isViewEmpty;
		}
		/**
		 * @param isViewEmpty the isViewEmpty to set
		 */
		public void setIsViewEmpty(int isViewEmpty) {
			this.isViewEmpty = isViewEmpty;
		}
	/**
		 * @return the durationTo
		 */
		public String getDurationTo() {
			return durationTo;
		}
		/**
		 * @param durationTo the durationTo to set
		 */
		public void setDurationTo(String durationTo) {
			this.durationTo = durationTo;
		}
		/**
		 * @return the durationFrom
		 */
		public String getDurationFrom() {
			return durationFrom;
		}
		/**
		 * @param durationFrom the durationFrom to set
		 */
		public void setDurationFrom(String durationFrom) {
			this.durationFrom = durationFrom;
		}
	/**
		 * @return the radioSelectSecond
		 */
		public String getRadioSelectSecond() {
			return radioSelectSecond;
		}
		/**
		 * @param radioSelectSecond the radioSelectSecond to set
		 */
		public void setRadioSelectSecond(String radioSelectSecond) {
			this.radioSelectSecond = radioSelectSecond;
		}
	
	
	/**
		 * @return the paymentType
		 */
		public String getPaymentType() {
			return paymentType;
		}
		/**
		 * @param paymentType the paymentType to set
		 */
		public void setPaymentType(String paymentType) {
			this.paymentType = paymentType;
		}
	/**
	 * @return the txnDate
	 */
	public String getTxnDate() {
		return txnDate;
	}
	/**
	 * @param txnDate the txnDate to set
	 */
	public void setTxnDate(String txnDate) {
		this.txnDate = txnDate;
	}
	
	/**
	 * @return the txnAmt
	 */
	/*public Double getTxnAmt() {
		return txnAmt;
	}*/
	/**
	 * @param txnAmt the txnAmt to set
	 */
	/*public void setTxnAmt(Double txnAmt) {
		this.txnAmt = txnAmt;
	}*/
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
	 * @return the txnType
	 */
	public String getTxnType() {
		return txnType;
	}
	/**
	 * @param txnType the txnType to set
	 */
	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
	/**
	 * @return the txnPurpose
	 */
	public String getTxnPurpose() {
		return txnPurpose;
	}
	/**
	 * @param txnPurpose the txnPurpose to set
	 */
	public void setTxnPurpose(String txnPurpose) {
		this.txnPurpose = txnPurpose;
	}
	
	
	/**
	 * @return the receiptId
	 */
	public String getReceiptId() {
		return receiptId;
	}
	/**
	 * @param receiptId the receiptId to set
	 */
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
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
	 * @return the radioSelect
	 */
	public String getRadioSelect() {
		return radioSelect;
	}
	/**
	 * @param radioSelect the radioSelect to set
	 */
	public void setRadioSelect(String radioSelect) {
		this.radioSelect = radioSelect;
	}
	
	

	/**
	 * @return the viewResultList
	 */
	public ArrayList getViewResultList() {
		return viewResultList;
	}
	/**
	 * @param viewResultList the viewResultList to set
	 */
	public void setViewResultList(ArrayList viewResultList) {
		this.viewResultList = viewResultList;
	}
	/**
	 * @return the viewComb
	 */
	public String getViewComb() {
		return viewComb;
	}
	/**
	 * @param viewComb the viewComb to set
	 */
	public void setViewComb(String viewComb) {
		this.viewComb = viewComb;
	}
	/**
	 * @return the cashGrossAmt
	 */
	public String getCashGrossAmt() {
		return cashGrossAmt;
	}
	public String getReceiptID() {
		return receiptID;
	}
	public void setReceiptID(String receiptID) {
		this.receiptID = receiptID;
	}
	/**
	 * @return the revenueOfficeTypeId
	 */
	public String getRevenueOfficeTypeId() {
		return revenueOfficeTypeId;
	}
	/**
	 * @param revenueOfficeTypeId the revenueOfficeTypeId to set
	 */
	public void setRevenueOfficeTypeId(String revenueOfficeTypeId) {
		this.revenueOfficeTypeId = revenueOfficeTypeId;
	}
	
	
	
	  
	  }
