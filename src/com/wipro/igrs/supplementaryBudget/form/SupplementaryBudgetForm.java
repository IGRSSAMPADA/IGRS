package com.wipro.igrs.supplementaryBudget.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

public class SupplementaryBudgetForm extends ActionForm
{
	
	
	
	
	private String officeMaster;
	private String schemeMaster;
	private String objectMaster;
	private String segmentMaster;
	private String majorHeadMaster;
	private String subMajorHeadMaster;
	private String minorHeadMaster;
	private String requestType;

	
	
	private String apr;
	private String may;
	private String jun;
	private String jul;
	private String aug;
	private String sep;
	private String oct;
	private String nov;
	private String dec;
	private String jan;
	private String feb;
	private String mar;
	
	
	private String majorTotalSubMH;
	
	
	
	private String majorTotalMinorHead;
	

	
	
	//private String officeName;
	private String financialYear;
	private String scheme;
	private String segment;
	private String object;
	
	private String majortotal;
	
	
	private String transactionId;
	private String durationFrom;
	private String durationTo;
	private String officeId;
	
	private String majorHeadId;
	private String subMajorHeadId;
	private String minorHeadId;
	private String detailedHead;
	
	private String count;
	private String rowSize;
	private String approvedStatus;
	/**
	 * @return the officeMaster
	 */
	public String getOfficeMaster()
	{
		return officeMaster;
	}
	/**
	 * @param officeMaster the officeMaster to set
	 */
	public void setOfficeMaster(String officeMaster)
	{
		this.officeMaster = officeMaster;
	}
	/**
	 * @return the schemeMaster
	 */
	public String getSchemeMaster()
	{
		return schemeMaster;
	}
	/**
	 * @param schemeMaster the schemeMaster to set
	 */
	public void setSchemeMaster(String schemeMaster)
	{
		this.schemeMaster = schemeMaster;
	}
	/**
	 * @return the objectMaster
	 */
	public String getObjectMaster()
	{
		return objectMaster;
	}
	/**
	 * @param objectMaster the objectMaster to set
	 */
	public void setObjectMaster(String objectMaster)
	{
		this.objectMaster = objectMaster;
	}
	/**
	 * @return the segmentMaster
	 */
	public String getSegmentMaster()
	{
		return segmentMaster;
	}
	/**
	 * @param segmentMaster the segmentMaster to set
	 */
	public void setSegmentMaster(String segmentMaster)
	{
		this.segmentMaster = segmentMaster;
	}
	/**
	 * @return the majorHeadMaster
	 */
	public String getMajorHeadMaster()
	{
		return majorHeadMaster;
	}
	/**
	 * @param majorHeadMaster the majorHeadMaster to set
	 */
	public void setMajorHeadMaster(String majorHeadMaster)
	{
		this.majorHeadMaster = majorHeadMaster;
	}
	/**
	 * @return the subMajorHeadMaster
	 */
	public String getSubMajorHeadMaster()
	{
		return subMajorHeadMaster;
	}
	/**
	 * @param subMajorHeadMaster the subMajorHeadMaster to set
	 */
	public void setSubMajorHeadMaster(String subMajorHeadMaster)
	{
		this.subMajorHeadMaster = subMajorHeadMaster;
	}
	/**
	 * @return the minorHeadMaster
	 */
	public String getMinorHeadMaster()
	{
		return minorHeadMaster;
	}
	/**
	 * @param minorHeadMaster the minorHeadMaster to set
	 */
	public void setMinorHeadMaster(String minorHeadMaster)
	{
		this.minorHeadMaster = minorHeadMaster;
	}
	/**
	 * @return the apr
	 */
	public String getApr()
	{
		return apr;
	}
	/**
	 * @param apr the apr to set
	 */
	public void setApr(String apr)
	{
		this.apr = apr;
	}
	/**
	 * @return the may
	 */
	public String getMay()
	{
		return may;
	}
	/**
	 * @param may the may to set
	 */
	public void setMay(String may)
	{
		this.may = may;
	}
	/**
	 * @return the jun
	 */
	public String getJun()
	{
		return jun;
	}
	/**
	 * @param jun the jun to set
	 */
	public void setJun(String jun)
	{
		this.jun = jun;
	}
	/**
	 * @return the jul
	 */
	public String getJul()
	{
		return jul;
	}
	/**
	 * @param jul the jul to set
	 */
	public void setJul(String jul)
	{
		this.jul = jul;
	}
	/**
	 * @return the aug
	 */
	public String getAug()
	{
		return aug;
	}
	/**
	 * @param aug the aug to set
	 */
	public void setAug(String aug)
	{
		this.aug = aug;
	}
	/**
	 * @return the sep
	 */
	public String getSep()
	{
		return sep;
	}
	/**
	 * @param sep the sep to set
	 */
	public void setSep(String sep)
	{
		this.sep = sep;
	}
	/**
	 * @return the oct
	 */
	public String getOct()
	{
		return oct;
	}
	/**
	 * @param oct the oct to set
	 */
	public void setOct(String oct)
	{
		this.oct = oct;
	}
	/**
	 * @return the nov
	 */
	public String getNov()
	{
		return nov;
	}
	/**
	 * @param nov the nov to set
	 */
	public void setNov(String nov)
	{
		this.nov = nov;
	}
	/**
	 * @return the dec
	 */
	public String getDec()
	{
		return dec;
	}
	/**
	 * @param dec the dec to set
	 */
	public void setDec(String dec)
	{
		this.dec = dec;
	}
	/**
	 * @return the jan
	 */
	public String getJan()
	{
		return jan;
	}
	/**
	 * @param jan the jan to set
	 */
	public void setJan(String jan)
	{
		this.jan = jan;
	}
	/**
	 * @return the feb
	 */
	public String getFeb()
	{
		return feb;
	}
	/**
	 * @param feb the feb to set
	 */
	public void setFeb(String feb)
	{
		this.feb = feb;
	}
	/**
	 * @return the mar
	 */
	public String getMar()
	{
		return mar;
	}
	/**
	 * @param mar the mar to set
	 */
	public void setMar(String mar)
	{
		this.mar = mar;
	}
	
	/**
	 * @return the majorTotalSubMH
	 */
	public String getMajorTotalSubMH()
	{
		return majorTotalSubMH;
	}
	/**
	 * @param majorTotalSubMH the majorTotalSubMH to set
	 */
	public void setMajorTotalSubMH(String majorTotalSubMH)
	{
		this.majorTotalSubMH = majorTotalSubMH;
	}
	
	/**
	 * @return the majorTotalMinorHead
	 */
	public String getMajorTotalMinorHead()
	{
		return majorTotalMinorHead;
	}
	/**
	 * @param majorTotalMinorHead the majorTotalMinorHead to set
	 */
	public void setMajorTotalMinorHead(String majorTotalMinorHead)
	{
		this.majorTotalMinorHead = majorTotalMinorHead;
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
	 * @return the majortotal
	 */
	public String getMajortotal()
	{
		return majortotal;
	}
	/**
	 * @param majortotal the majortotal to set
	 */
	public void setMajortotal(String majortotal)
	{
		this.majortotal = majortotal;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId()
	{
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	/**
	 * @return the durationFrom
	 */
	public String getDurationFrom()
	{
		return durationFrom;
	}
	/**
	 * @param durationFrom the durationFrom to set
	 */
	public void setDurationFrom(String durationFrom)
	{
		this.durationFrom = durationFrom;
	}
	/**
	 * @return the durationTo
	 */
	public String getDurationTo()
	{
		return durationTo;
	}
	/**
	 * @param durationTo the durationTo to set
	 */
	public void setDurationTo(String durationTo)
	{
		this.durationTo = durationTo;
	}
	/**
	 * @return the officeId
	 */
	public String getOfficeId()
	{
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(String officeId)
	{
		this.officeId = officeId;
	}
	/**
	 * @return the majorHeadId
	 */
	public String getMajorHeadId()
	{
		return majorHeadId;
	}
	/**
	 * @param majorHeadId the majorHeadId to set
	 */
	public void setMajorHeadId(String majorHeadId)
	{
		this.majorHeadId = majorHeadId;
	}
	/**
	 * @return the subMajorHeadId
	 */
	public String getSubMajorHeadId()
	{
		return subMajorHeadId;
	}
	/**
	 * @param subMajorHeadId the subMajorHeadId to set
	 */
	public void setSubMajorHeadId(String subMajorHeadId)
	{
		this.subMajorHeadId = subMajorHeadId;
	}
	/**
	 * @return the minorHeadId
	 */
	public String getMinorHeadId()
	{
		return minorHeadId;
	}
	/**
	 * @param minorHeadId the minorHeadId to set
	 */
	public void setMinorHeadId(String minorHeadId)
	{
		this.minorHeadId = minorHeadId;
	}
	/**
	 * @return the requestType
	 */
	public String getRequestType()
	{
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType)
	{
		this.requestType = requestType;
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
	/**
	 * @return the count
	 */
	public String getCount()
	{
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(String count)
	{
		this.count = count;
	}
	/**
	 * @return the rowSize
	 */
	public String getRowSize()
	{
		return rowSize;
	}
	/**
	 * @param rowSize the rowSize to set
	 */
	public void setRowSize(String rowSize)
	{
		this.rowSize = rowSize;
	}
	public String getApprovedStatus() {
	    return approvedStatus;
	}
	public void setApprovedStatus(String approvedStatus) {
	    this.approvedStatus = approvedStatus;
	}

}
