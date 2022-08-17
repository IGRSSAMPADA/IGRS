package com.wipro.igrs.propertyvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Madan Mohan
 */
public class InstrumentsDTO implements Serializable {
  
	/**
	 * @author Madan Mohan
	 */
	private int instId;
	/**
	 * @author Madan Mohan
	 */
	private String instType;
	/**
	 * @author Madan Mohan
	 */
	private String instAmount;
	/**
	 * @author Madan Mohan
	 */
	private String instAmtType;
	/**
	 * @author Madan Mohan
	 */
	private String instDeedId;
	/**
	 * @author Madan Mohan
	 */
	private String hdnAmount;
	/**
	 * @author Madan Mohan
	 */
	private String instPerAmount;
	/**
	 * @author Madan Mohan
	 */
	private String linkExempted;
	/**
	 * @author Madan Mohan
	 */
	private String instPerFlag;
	
	/**
	 * @return String
	 */
	private String labelDisplay;
			/**
		 * @return String
		 */
   private String labelAmountFlag;
	
	
   
   
	public String getLabelDisplay() {
				return labelDisplay;
			}
	
	public void setLabelDisplay(String labelDisplay) {
				this.labelDisplay = labelDisplay;
			}
	
	public String getLabelAmountFlag() {
				return labelAmountFlag;
			}
	
	public void setLabelAmountFlag(String labelAmountFlag) {
				this.labelAmountFlag = labelAmountFlag;
			}
	
	public String getInstPerAmount() {
		return instPerAmount;
	}
	/**
	 * @param instPerAmount.
	 */
	public void setInstPerAmount(String instPerAmount) {
		this.instPerAmount = instPerAmount;
	}
	/**
	 * @return String
	 */
	public String getLinkExempted() {
		return linkExempted;
	}
	/**
	 * @param linkExempted
	 */
	public void setLinkExempted(String linkExempted) {
		this.linkExempted = linkExempted;
	}
	/**
	 * @return String
	 */
	public String getInstPerFlag() {
		return instPerFlag;
	}
	/**
	 * @param instPerFlag
	 */
	public void setInstPerFlag(String instPerFlag) {
		this.instPerFlag = instPerFlag;
	}
	/**
	 * @return String
	 */
	public String getHdnAmount() {
		return hdnAmount;
	}
	/**
	 * @param hdnAmount
	 */
	public void setHdnAmount(String hdnAmount) {
		this.hdnAmount = hdnAmount;
	}
	/**

	/**
	 * @return String
	 */
	public String getInstType() {
		return instType;
	}
	public int getInstId() {
		return instId;
	}
	public void setInstId(int instId) {
		this.instId = instId;
	}
	/**
	 * @param instType
	 */
	public void setInstType(String instType) {
		this.instType = instType;
	}
	/**
	 * @return String
	 */
	public String getInstAmount() {
		return instAmount;
	}
	/**
	 * @param instAmount
	 */
	public void setInstAmount(String instAmount) {
		this.instAmount = instAmount;
	}
	/**
	 * @return String
	 */
	public String getInstAmtType() {
		return instAmtType;
	}
	/**
	 * @param instAmtType
	 */
	public void setInstAmtType(String instAmtType) {
		this.instAmtType = instAmtType;
	}
	/**
	 * @return String
	 */
	public String getInstDeedId() {
		return instDeedId;
	}
	/**
	 * @param instDeedId
	 */
	public void setInstDeedId(String instDeedId) {
		this.instDeedId = instDeedId;
	}
	
	private String asConsiderationAmount;
	private String inStampDutyAmount;
	private String userEnterableFieldReq;
	private String userEnterableId;
	private String asConsiderationAmountFlag;
	private String inStampDutyAmountFlag;
	private String userEnterableStatus;
	private String userEnterableName;
	private ArrayList userEnterableList;
	private String userEnterableFieldValue;
	private String commonUserField;
	private String alreadyPaidRegFeeFlag;
	private String alreadyPaidStampDutyFlag;
	private String valueOfMovableProp; //added by ankit 
	private String movablePropFlag; // added by ankit
	public String getAsConsiderationAmountFlag() {
		return asConsiderationAmountFlag;
	}

	public void setAsConsiderationAmountFlag(String asConsiderationAmountFlag) {
		this.asConsiderationAmountFlag = asConsiderationAmountFlag;
	}

	public String getInStampDutyAmountFlag() {
		return inStampDutyAmountFlag;
	}

	public void setInStampDutyAmountFlag(String inStampDutyAmountFlag) {
		this.inStampDutyAmountFlag = inStampDutyAmountFlag;
	}

	public String getAsConsiderationAmount() {
		return asConsiderationAmount;
	}

	public void setAsConsiderationAmount(String asConsiderationAmount) {
		this.asConsiderationAmount = asConsiderationAmount;
	}

	public String getInStampDutyAmount() {
		return inStampDutyAmount;
	}

	public void setInStampDutyAmount(String inStampDutyAmount) {
		this.inStampDutyAmount = inStampDutyAmount;
	}

	public void setUserEnterableFieldReq(String userEnterableFieldReq) {
		this.userEnterableFieldReq = userEnterableFieldReq;
	}

	public String getUserEnterableFieldReq() {
		return userEnterableFieldReq;
	}

	public void setUserEnterableId(String userEnterableId) {
		this.userEnterableId = userEnterableId;
	}

	public String getUserEnterableId() {
		return userEnterableId;
	}

	public void setUserEnterableStatus(String userEnterableStatus) {
		this.userEnterableStatus = userEnterableStatus;
	}

	public String getUserEnterableStatus() {
		return userEnterableStatus;
	}

	public void setUserEnterableName(String userEnterableName) {
		this.userEnterableName = userEnterableName;
	}

	public String getUserEnterableName() {
		return userEnterableName;
	}

	public void setUserEnterableList(ArrayList userEnterableList) {
		this.userEnterableList = userEnterableList;
	}

	public ArrayList getUserEnterableList() {
		return userEnterableList;
	}

	public void setUserEnterableFieldValue(String userEnterableFieldValue) {
		this.userEnterableFieldValue = userEnterableFieldValue;
	}

	public String getUserEnterableFieldValue() {
		return userEnterableFieldValue;
	}

	public void setCommonUserField(String commonUserField) {
		this.commonUserField = commonUserField;
	}

	public String getCommonUserField() {
		return commonUserField;
	}

	public void setAlreadyPaidRegFeeFlag(String alreadyPaidRegFeeFlag) {
		this.alreadyPaidRegFeeFlag = alreadyPaidRegFeeFlag;
	}

	public String getAlreadyPaidRegFeeFlag() {
		return alreadyPaidRegFeeFlag;
	}

	public void setAlreadyPaidStampDutyFlag(String alreadyPaidStampDutyFlag) {
		this.alreadyPaidStampDutyFlag = alreadyPaidStampDutyFlag;
	}

	public String getAlreadyPaidStampDutyFlag() {
		return alreadyPaidStampDutyFlag;
	}
	public String getValueOfMovableProp() {
		return valueOfMovableProp;
	}

	public void setValueOfMovableProp(String valueOfMovableProp) {
		this.valueOfMovableProp = valueOfMovableProp;
	}

	public String getMovablePropFlag() {
		return movablePropFlag;
	}

	public void setMovablePropFlag(String movablePropFlag) {
		this.movablePropFlag = movablePropFlag;
	}

	public void setLeaseId(String leaseId) {
		this.leaseId = leaseId;
	}

	public String getLeaseId() {
		return leaseId;
	}
	public void setLeaseName(String leaseName) {
		this.leaseName = leaseName;
	}

	public String getLeaseName() {
		return leaseName;
	}

	private String leaseName;	
	private String leaseId;
	private ArrayList leasePeriod=new ArrayList();
	private String leaseFreeze;



	public ArrayList getLeasePeriod() {
		return leasePeriod;
	}

	public void setLeasePeriod(ArrayList leasePeriod) {
		this.leasePeriod = leasePeriod;
	}

	public void setLeaseFreeze(String leaseFreeze) {
		this.leaseFreeze = leaseFreeze;
	}

	public String getLeaseFreeze() {
		return leaseFreeze;
	}
	
}
