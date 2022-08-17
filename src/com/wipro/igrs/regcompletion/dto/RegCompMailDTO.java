package com.wipro.igrs.regcompletion.dto;

import java.util.ArrayList;

public class RegCompMailDTO
{
	private String partyTxnId;
	
	ArrayList list1;
	

	/**
	 * @return the partyTxnId
	 */
	public String getPartyTxnId()
	{
		return partyTxnId;
	}

	/**
	 * @param partyTxnId the partyTxnId to set
	 */
	public void setPartyTxnId(String partyTxnId)
	{
		this.partyTxnId = partyTxnId;
	}

	/**
	 * @return the list1
	 */
	public ArrayList getList1()
	{
		return list1;
	}

	/**
	 * @param list1 the list1 to set
	 */
	public void setList1(ArrayList list1)
	{
		this.list1 = list1;
	}
	
}
