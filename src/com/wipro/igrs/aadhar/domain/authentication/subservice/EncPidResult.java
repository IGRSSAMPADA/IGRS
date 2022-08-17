package com.wipro.igrs.aadhar.domain.authentication.subservice;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EncPidResult {

	private String skey = "0";
	private String encPid = "0";
	private String encHmac = "0";
	private String encTs = "0";
	/**
	 * @return the skey
	 */
	public String getSkey() {
		return skey;
	}
	/**
	 * @param skey the skey to set
	 */
	public void setSkey(String skey) {
		this.skey = skey;
	}
	/**
	 * @return the encPid
	 */
	public String getEncPid() {
		return encPid;
	}
	/**
	 * @param encPid the encPid to set
	 */
	public void setEncPid(String encPid) {
		this.encPid = encPid;
	}
	/**
	 * @return the encHmac
	 */
	public String getEncHmac() {
		return encHmac;
	}
	/**
	 * @param encHmac the encHmac to set
	 */
	public void setEncHmac(String encHmac) {
		this.encHmac = encHmac;
	}
	/**
	 * @return the encTs
	 */
	public String getEncTs() {
		return encTs;
	}
	/**
	 * @param encTs the encTs to set
	 */
	public void setEncTs(String encTs) {
		this.encTs = encTs;
	}
}
