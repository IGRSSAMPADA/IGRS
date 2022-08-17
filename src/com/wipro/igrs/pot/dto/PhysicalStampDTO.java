package com.wipro.igrs.pot.dto;

public class PhysicalStampDTO
{
	private String stampNumber;
	private String stampFee;
	private String transactionId;
	public String getStampNumber()
	{
		return stampNumber;
	}
	public void setStampNumber(String stampNumber)
	{
		this.stampNumber = stampNumber;
	}
	public String getStampFee()
	{
		return stampFee;
	}
	public void setStampFee(String stampFee)
	{
		this.stampFee = stampFee;
	}
	public String getTransactionId()
	{
		return transactionId;
	}
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}

}
