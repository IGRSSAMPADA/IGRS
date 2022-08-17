package com.wipro.igrs.newreginit.dto;

import org.codehaus.jackson.annotate.JsonSetter;

public class AadharDTO {
	private String uid;
	private String tid;
	private String usesBio;
	private String isoImage;
	private String domainId;
	private String transactionId;
	private String sa;
	private String name;
	private String lk;
	private String langCode;
	private String lName;
	
	private String imageBase64;
	private String lData;
	private String consent;
	private MetaPojo meta;
	private UsesPojo uses;
	
	private String encHmac;
	private String encPid;
	private String skey;
	private String deptCode;
	private String encTs;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getUsesBio() {
		return usesBio;
	}
	public void setUsesBio(String usesBio) {
		this.usesBio = usesBio;
	}
	public String getIsoImage() {
		return isoImage;
	}
	public void setIsoImage(String isoImage) {
		this.isoImage = isoImage;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getSa() {
		return sa;
	}
	public void setSa(String sa) {
		this.sa = sa;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLk() {
		return lk;
	}
	public void setLk(String lk) {
		this.lk = lk;
	}
	public String getLangCode() {
		return langCode;
	}
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getImageBase64() {
		return imageBase64;
	}
	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public MetaPojo getMeta() {
		return meta;
	}
	public void setMeta(MetaPojo meta) {
		this.meta = meta;
	}
	public UsesPojo getUses() {
		return uses;
	}
	public void setUses(UsesPojo uses) {
		this.uses = uses;
	}
	public String getLName() {
		return lName;
	}
	@JsonSetter("lName")
	public void setLName(String name) {
		lName = name;
	}
	public String getLData() {
		return lData;
	}
	@JsonSetter("lData")
	public void setLData(String data) {
		lData = data;
	}
	public String getEncHmac() {
		return encHmac;
	}
	public void setEncHmac(String encHmac) {
		this.encHmac = encHmac;
	}
	public String getEncPid() {
		return encPid;
	}
	public void setEncPid(String encPid) {
		this.encPid = encPid;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getEncTs() {
		return encTs;
	}
	public void setEncTs(String encTs) {
		this.encTs = encTs;
	}
	

}
