package com.wipro.igrs.regcompletion.form;

import org.apache.struts.action.ActionForm;

public class RegCompMailForm extends ActionForm
{
	private String mailSendType;
	private String partyTxnId;

	/**
	 * @return the mailSendType
	 */
	public String getMailSendType()
	{
		return mailSendType;
	}

	/**
	 * @param mailSendType the mailSendType to set
	 */
	public void setMailSendType(String mailSendType)
	{
		this.mailSendType = mailSendType;
	}

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
	
}
