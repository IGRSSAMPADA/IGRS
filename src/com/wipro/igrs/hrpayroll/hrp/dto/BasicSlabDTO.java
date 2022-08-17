package com.wipro.igrs.hrpayroll.hrp.dto;

import java.io.Serializable;

public class BasicSlabDTO implements Serializable {

	private String			basicSlabId;
	private String			basicSlabMin;	
	private String			basicSlabMax;	
	private String          basicSlabIncrement ;	
	private String          basicSlabValidFrom ;
	private String          basicSlabValidTo ;
	private String          basicSlabRemarks ;
	private String 			status;
	
	/**
	 * @return the basicSlabMin
	 */
	public String getBasicSlabMin() {
		return basicSlabMin;
	}
	/**
	 * @param basicSlabMin the basicSlabMin to set
	 */
	public void setBasicSlabMin(String basicSlabMin) {
		this.basicSlabMin = basicSlabMin;
	}
	/**
	 * @return the basicSlabMax
	 */
	public String getBasicSlabMax() {
		return basicSlabMax;
	}
	/**
	 * @param basicSlabMax the basicSlabMax to set
	 */
	public void setBasicSlabMax(String basicSlabMax) {
		this.basicSlabMax = basicSlabMax;
	}
	/**
	 * @return the basicSlabIncrement
	 */
	public String getBasicSlabIncrement() {
		return basicSlabIncrement;
	}
	/**
	 * @param basicSlabIncrement the basicSlabIncrement to set
	 */
	public void setBasicSlabIncrement(String basicSlabIncrement) {
		this.basicSlabIncrement = basicSlabIncrement;
	}
	/**
	 * @return the basicSlabValidFrom
	 */
	public String getBasicSlabValidFrom() {
		return basicSlabValidFrom;
	}
	/**
	 * @param basicSlabValidFrom the basicSlabValidFrom to set
	 */
	public void setBasicSlabValidFrom(String basicSlabValidFrom) {
		this.basicSlabValidFrom = basicSlabValidFrom;
	}
	/**
	 * @return the basicSlabValidTo
	 */
	public String getBasicSlabValidTo() {
		return basicSlabValidTo;
	}
	/**
	 * @param basicSlabValidTo the basicSlabValidTo to set
	 */
	public void setBasicSlabValidTo(String basicSlabValidTo) {
		this.basicSlabValidTo = basicSlabValidTo;
	}
	/**
	 * @return the basicSlabRemarks
	 */
	public String getBasicSlabRemarks() {
		return basicSlabRemarks;
	}
	/**
	 * @param basicSlabRemarks the basicSlabRemarks to set
	 */
	public void setBasicSlabRemarks(String basicSlabRemarks) {
		this.basicSlabRemarks = basicSlabRemarks;
	}
	/**
	 * @return the basicSlabId
	 */
	public String getBasicSlabId() {
		return basicSlabId;
	}
	/**
	 * @param basicSlabId the basicSlabId to set
	 */
	public void setBasicSlabId(String basicSlabId) {
		this.basicSlabId = basicSlabId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
