package com.wipro.igrs.rcms.entity;

import javax.xml.bind.annotation.XmlElement;

public class KhasraRecord {

	private SellerDetailsSampadaList sellerDetailsSampadaList;
	private BuyerDetailsSampadaList buyerDetailsSampadaList;
	private String District;
	private String Tehsil;
	private String RICircle;
	private String PatwariHalka;
	private String Village;
	private String KhasraNumber;
	private String KhasraSellableArea;
	private String AreaType;
	private String khasraID;
	private String transactionType;
	private String ulpin;
	private String khasraUniqueId;
	private String lgdcode;
	
	public KhasraRecord() {
		super();
	}
	
	@XmlElement
	public SellerDetailsSampadaList getSellerDetailsSampadaList() {
		return sellerDetailsSampadaList;
	}
	public void setSellerDetailsSampadaList(SellerDetailsSampadaList sellerDetailsSampadaList) {
		this.sellerDetailsSampadaList = sellerDetailsSampadaList;
	}
	
	@XmlElement
	public BuyerDetailsSampadaList getBuyerDetailsSampadaList() {
		return buyerDetailsSampadaList;
	}
	public void setBuyerDetailsSampadaList(BuyerDetailsSampadaList buyerDetailsSampadaList) {
		this.buyerDetailsSampadaList = buyerDetailsSampadaList;
	}
	
	@XmlElement(name="District")
	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
	}
	
	@XmlElement(name="Tehsil")
	public String getTehsil() {
		return Tehsil;
	}

	public void setTehsil(String tehsil) {
		Tehsil = tehsil;
	}
	
	@XmlElement(name="RICircle")
	public String getRICircle() {
		return RICircle;
	}

	public void setRICircle(String circle) {
		RICircle = circle;
	}
	
	@XmlElement(name="PatwariHalka")
	public String getPatwariHalka() {
		return PatwariHalka;
	}

	public void setPatwariHalka(String patwariHalka) {
		PatwariHalka = patwariHalka;
	}
	
	@XmlElement(name="Village")
	public String getVillage() {
		return Village;
	}

	public void setVillage(String village) {
		Village = village;
	}

	@XmlElement(name="KhasraNumber")
	public String getKhasraNumber() {
		return KhasraNumber;
	}

	public void setKhasraNumber(String khasraNumber) {
		KhasraNumber = khasraNumber;
	}

	@XmlElement(name="KhasraSellableArea")
	public String getKhasraSellableArea() {
		return KhasraSellableArea;
	}
	
	public void setKhasraSellableArea(String khasraSellableArea) {
		KhasraSellableArea = khasraSellableArea;
	}

	@XmlElement(name="AreaType")
	public String getAreaType() {
		return AreaType;
	}
	
	public void setAreaType(String areaType) {
		AreaType = areaType;
	}

	@XmlElement(name="khasraID")
	public String getKhasraID() {
		return khasraID;
	}

	public void setKhasraID(String khasraID) {
		this.khasraID = khasraID;
	}

	@XmlElement(name="transactionType")
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@XmlElement(name="ULPIN")
	public String getUlpin() {
		return ulpin;
	}

	public void setUlpin(String ulpin) {
		this.ulpin = ulpin;
	}

	@XmlElement(name="khasraUniqueId")
	public String getKhasraUniqueId() {
		return khasraUniqueId;
	}

	public void setKhasraUniqueId(String khasraUniqueId) {
		this.khasraUniqueId = khasraUniqueId;
	}

	@XmlElement(name="lgdcode")
	public String getLgdcode() {
		return lgdcode;
	}

	public void setLgdcode(String lgdcode) {
		this.lgdcode = lgdcode;
	}
	
	
}
