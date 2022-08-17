package com.wipro.igrs.bankingestamp.dto;

import java.io.Serializable;


/**
 * @author Madan Mohan
 */
public class InstrumentsDTO implements Serializable{
  
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
	private String labelDisplay;
	
	
	/**
	 * @return String
	 */
	
	private String instPerFlag;

	/**
	 * @return String
	 */
    private String labelAmountFlag;
	
	/**
	 * @return String
	 */
    
    
 private String constantlabel;
	
	/**
	 * @return String
	 */
	public String getLabelDisplay() {
		return labelDisplay;
	}
	
	public void setLabelDisplay(String labelDisplay) {
		this.labelDisplay = labelDisplay;
	}
	/**
	 * @return String
	 */
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
	 * @return String
	 */

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
}
