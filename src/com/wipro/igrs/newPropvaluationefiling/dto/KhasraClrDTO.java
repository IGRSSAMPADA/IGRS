package com.wipro.igrs.newPropvaluationefiling.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "KhasraDetails")
public class KhasraClrDTO implements Serializable {

	// private static final long serialVersionUID = 1L;
	private String clrFlag;
	private double censusCode;
	private String khasraNoClr;
	private String khasraTotalArea;
	private String landIrriOrNot;
	private String landUnIrriOrNot;
	private String landUnirriDouble;
	private String padtiArea;
	private String padtiDuration;
	private String ownership;
	private String nohiyat;
	private String currentStatus;
	private String kehfiyat;
	private String clrKhasraId;
	private String serviceFlag;
	private String irrigatedArea;
	private String nonIrrigatedArea;
	private String doubleCropArea;
	private String mortgage;
	private String padtiFlag;
	private String error;
	private String singleCropArea;
	private String totalSaleableArea;

	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}

	public String getClrFlag() {
		return clrFlag;
	}

	public void setCensusCode(double censusCode) {
		this.censusCode = censusCode;
	}

	@XmlElement(name = "bhucode")
	public double getCensusCode() {
		return censusCode;
	}

	public void setKhasraNoClr(String khasraNoClr) {
		this.khasraNoClr = khasraNoClr;
	}

	@XmlElement(name = "khasraNo")
	public String getKhasraNoClr() {
		return khasraNoClr;
	}

	public void setKhasraTotalArea(String khasraTotalArea) {
		this.khasraTotalArea = khasraTotalArea;
	}

	@XmlElement(name = "surveyArea")
	public String getKhasraTotalArea() {
		return khasraTotalArea;
	}

	public void setLandIrriOrNot(String landIrriOrNot) {
		this.landIrriOrNot = landIrriOrNot;
	}

	@XmlElement(name = "isLandIrrigated")
	public String getLandIrriOrNot() {
		return landIrriOrNot;
	}

	public void setLandUnIrriOrNot(String landUnIrriOrNot) {
		this.landUnIrriOrNot = landUnIrriOrNot;
	}

	@XmlElement(name = "isSingleCrop")
	public String getLandUnIrriOrNot() {
		return landUnIrriOrNot;
	}

	public void setLandUnirriDouble(String landUnirriDouble) {
		this.landUnirriDouble = landUnirriDouble;
	}

	@XmlElement(name = "isDoubleCrop")
	public String getLandUnirriDouble() {
		return landUnirriDouble;
	}

	public void setPadtiArea(String padtiArea) {
		this.padtiArea = padtiArea;
	}

	public String getPadtiArea() {
		return padtiArea;
	}

	public void setPadtiDuration(String padtiDuration) {
		this.padtiDuration = padtiDuration;
	}

	public String getPadtiDuration() {
		return padtiDuration;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	@XmlElement(name = "landOwnershipType")
	public String getOwnership() {
		return ownership;
	}

	public void setNohiyat(String nohiyat) {
		this.nohiyat = nohiyat;
	}

	@XmlElement(name = "noyiyat")
	public String getNohiyat() {
		return nohiyat;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@XmlElement(name = "khasraTransactionStatus")
	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setKehfiyat(String kehfiyat) {
		this.kehfiyat = kehfiyat;
	}

	@XmlElement(name = "remarks")
	public String getKehfiyat() {
		return kehfiyat;
	}

	public void setClrKhasraId(String clrKhasraId) {
		this.clrKhasraId = clrKhasraId;
	}

	@XmlElement(name = "khasraId")
	public String getClrKhasraId() {
		return clrKhasraId;
	}

	public String getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}

	public String getIrrigatedArea() {
		return irrigatedArea;
	}

	public void setIrrigatedArea(String irrigatedArea) {
		this.irrigatedArea = irrigatedArea;
	}

	public String getNonIrrigatedArea() {
		return nonIrrigatedArea;
	}

	public void setNonIrrigatedArea(String nonIrrigatedArea) {
		this.nonIrrigatedArea = nonIrrigatedArea;
	}

	public String getDoubleCropArea() {
		return doubleCropArea;
	}

	public void setDoubleCropArea(String doubleCropArea) {
		this.doubleCropArea = doubleCropArea;
	}

	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public String getPadtiFlag() {
		return padtiFlag;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setPadtiFlag(String padtiFlag) {
		this.padtiFlag = padtiFlag;
	}

	public void setSingleCropArea(String singleCropArea) {
		this.singleCropArea = singleCropArea;
	}

	public String getSingleCropArea() {
		return singleCropArea;
	}

	public void setTotalSaleableArea(String totalSaleableArea) {
		this.totalSaleableArea = totalSaleableArea;
	}

	public String getTotalSaleableArea() {
		return totalSaleableArea;
	}

}
