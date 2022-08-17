package com.wipro.igrs.reconciliation.form;

import org.apache.struts.action.ActionForm;

public class ReconciliationForm extends ActionForm
{
	
	private String district;
	private String dro;
	private String financialYear;

	
	private String allocated;
	private String exhausted;
	private String balance;
	
	private String majorHead;
	private String subMajorHead;
	private String minorHead;
	private String scheme;
	private String segment;
	private String object;
	private String detailedHead;
	
	private String durationFrom;
	private String durationTo;
	
	/**
	 * @return the district
	 */
	public String getDistrict()
	{
		return district;
	}
	/**
	 * @param district the district to set
	 */
	public void setDistrict(String district)
	{
		this.district = district;
	}
	/**
	 * @return the dro
	 */
	public String getDro()
	{
		return dro;
	}
	/**
	 * @param dro the dro to set
	 */
	public void setDro(String dro)
	{
		this.dro = dro;
	}
	/**
	 * @return the financialYear
	 */
	public String getFinancialYear()
	{
		return financialYear;
	}
	/**
	 * @param financialYear the financialYear to set
	 */
	public void setFinancialYear(String financialYear)
	{
		this.financialYear = financialYear;
	}
	
	/**
	 * @return the allocated
	 */
	public String getAllocated()
	{
		return allocated;
	}
	/**
	 * @param allocated the allocated to set
	 */
	public void setAllocated(String allocated)
	{
		this.allocated = allocated;
	}
	/**
	 * @return the exhausted
	 */
	public String getExhausted()
	{
		return exhausted;
	}
	/**
	 * @param exhausted the exhausted to set
	 */
	public void setExhausted(String exhausted)
	{
		this.exhausted = exhausted;
	}
	/**
	 * @return the balance
	 */
	public String getBalance()
	{
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(String balance)
	{
		this.balance = balance;
	}
	/**
	 * @return the majorHead
	 */
	public String getMajorHead()
	{
		return majorHead;
	}
	/**
	 * @param majorHead the majorHead to set
	 */
	public void setMajorHead(String majorHead)
	{
		this.majorHead = majorHead;
	}
	/**
	 * @return the subMajorHead
	 */
	public String getSubMajorHead()
	{
		return subMajorHead;
	}
	/**
	 * @param subMajorHead the subMajorHead to set
	 */
	public void setSubMajorHead(String subMajorHead)
	{
		this.subMajorHead = subMajorHead;
	}
	/**
	 * @return the minorHead
	 */
	public String getMinorHead()
	{
		return minorHead;
	}
	/**
	 * @param minorHead the minorHead to set
	 */
	public void setMinorHead(String minorHead)
	{
		this.minorHead = minorHead;
	}
	/**
	 * @return the scheme
	 */
	public String getScheme()
	{
		return scheme;
	}
	/**
	 * @param scheme the scheme to set
	 */
	public void setScheme(String scheme)
	{
		this.scheme = scheme;
	}
	/**
	 * @return the segment
	 */
	public String getSegment()
	{
		return segment;
	}
	/**
	 * @param segment the segment to set
	 */
	public void setSegment(String segment)
	{
		this.segment = segment;
	}
	/**
	 * @return the object
	 */
	public String getObject()
	{
		return object;
	}
	/**
	 * @param object the object to set
	 */
	public void setObject(String object)
	{
		this.object = object;
	}
	/**
	 * @return the detailedHead
	 */
	public String getDetailedHead()
	{
		return detailedHead;
	}
	/**
	 * @param detailedHead the detailedHead to set
	 */
	public void setDetailedHead(String detailedHead)
	{
		this.detailedHead = detailedHead;
	}
	public String getDurationFrom() {
		return durationFrom;
	}
	public void setDurationFrom(String durationFrom) {
		this.durationFrom = durationFrom;
	}
	public String getDurationTo() {
		return durationTo;
	}
	public void setDurationTo(String durationTo) {
		this.durationTo = durationTo;
	}
	

}
