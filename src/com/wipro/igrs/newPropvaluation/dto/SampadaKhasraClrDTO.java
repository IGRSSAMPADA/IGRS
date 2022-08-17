package com.wipro.igrs.newPropvaluation.dto;

import java.io.Serializable;
import java.util.ArrayList;



public class SampadaKhasraClrDTO implements Serializable {
	
	private String clrFlag;
	private String censusCode;
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
	private String lgdcode;
	private String transactionType;
	
	//Sampdada User Entered	for Undiv
	private double sampadaSingleCrop;
	private double sampadaDoubleCrop;
	private double sampadaIrrigated;
	private double totalSaleableArea;
	public String getClrFlag() {
		return clrFlag;
	}
	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}
	public String getCensusCode() {
		return censusCode;
	}
	public void setCensusCode(String censusCode) {
		this.censusCode = censusCode;
	}
	public String getKhasraNoClr() {
		return khasraNoClr;
	}
	public void setKhasraNoClr(String khasraNoClr) {
		this.khasraNoClr = khasraNoClr;
	}
	public String getKhasraTotalArea() {
		return khasraTotalArea;
	}
	public void setKhasraTotalArea(String khasraTotalArea) {
		this.khasraTotalArea = khasraTotalArea;
	}
	public String getLandIrriOrNot() {
		return landIrriOrNot;
	}
	public void setLandIrriOrNot(String landIrriOrNot) {
		this.landIrriOrNot = landIrriOrNot;
	}
	public String getLandUnIrriOrNot() {
		return landUnIrriOrNot;
	}
	public void setLandUnIrriOrNot(String landUnIrriOrNot) {
		this.landUnIrriOrNot = landUnIrriOrNot;
	}
	public String getLandUnirriDouble() {
		return landUnirriDouble;
	}
	public void setLandUnirriDouble(String landUnirriDouble) {
		this.landUnirriDouble = landUnirriDouble;
	}
	public String getPadtiArea() {
		return padtiArea;
	}
	public void setPadtiArea(String padtiArea) {
		this.padtiArea = padtiArea;
	}
	public String getPadtiDuration() {
		return padtiDuration;
	}
	public void setPadtiDuration(String padtiDuration) {
		this.padtiDuration = padtiDuration;
	}
	public String getOwnership() {
		return ownership;
	}
	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}
	public String getNohiyat() {
		return nohiyat;
	}
	public void setNohiyat(String nohiyat) {
		this.nohiyat = nohiyat;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	public String getKehfiyat() {
		return kehfiyat;
	}
	public void setKehfiyat(String kehfiyat) {
		this.kehfiyat = kehfiyat;
	}
	public String getClrKhasraId() {
		return clrKhasraId;
	}
	public void setClrKhasraId(String clrKhasraId) {
		this.clrKhasraId = clrKhasraId;
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
	public void setPadtiFlag(String padtiFlag) {
		this.padtiFlag = padtiFlag;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getSingleCropArea() {
		return singleCropArea;
	}
	public void setSingleCropArea(String singleCropArea) {
		this.singleCropArea = singleCropArea;
	}
	public double getSampadaSingleCrop() {
		return sampadaSingleCrop;
	}
	public void setSampadaSingleCrop(double sampadaSingleCrop) {
		this.sampadaSingleCrop = sampadaSingleCrop;
	}
	public double getSampadaDoubleCrop() {
		return sampadaDoubleCrop;
	}
	public void setSampadaDoubleCrop(double sampadaDoubleCrop) {
		this.sampadaDoubleCrop = sampadaDoubleCrop;
	}
	public double getSampadaIrrigated() {
		return sampadaIrrigated;
	}
	public void setSampadaIrrigated(double sampadaIrrigated) {
		this.sampadaIrrigated = sampadaIrrigated;
	}
	public double getTotalSaleableArea() {
		return totalSaleableArea;
	}
	public void setTotalSaleableArea(double totalSaleableArea) {
		this.totalSaleableArea = totalSaleableArea;
	}
	
	
	
	
	
	
	//for diverted parameters




	private double sampadaTotalDivertedArea;
	private double sampadaTotalDivertedResidentialArea;
	private double sampadaTotalDivertedCommercialArea;
	private double sampadaTotalDivertedIndustrialArea;
	private double sampadaTotalDivertedEducationalArea;
	private double sampadaTotalDivertedHealthArea;
	private double sampadaTotalDivertedOthersArea;
	
	
	
	public double getSampadaTotalDivertedArea() {
		return sampadaTotalDivertedArea;
	}
	public void setSampadaTotalDivertedArea(double sampadaTotalDivertedArea) {
		this.sampadaTotalDivertedArea = sampadaTotalDivertedArea;
	}
	public double getSampadaTotalDivertedResidentialArea() {
		return sampadaTotalDivertedResidentialArea;
	}
	public void setSampadaTotalDivertedResidentialArea(
			double sampadaTotalDivertedResidentialArea) {
		this.sampadaTotalDivertedResidentialArea = sampadaTotalDivertedResidentialArea;
	}
	public double getSampadaTotalDivertedCommercialArea() {
		return sampadaTotalDivertedCommercialArea;
	}
	public void setSampadaTotalDivertedCommercialArea(
			double sampadaTotalDivertedCommercialArea) {
		this.sampadaTotalDivertedCommercialArea = sampadaTotalDivertedCommercialArea;
	}
	public double getSampadaTotalDivertedIndustrialArea() {
		return sampadaTotalDivertedIndustrialArea;
	}
	public void setSampadaTotalDivertedIndustrialArea(
			double sampadaTotalDivertedIndustrialArea) {
		this.sampadaTotalDivertedIndustrialArea = sampadaTotalDivertedIndustrialArea;
	}
	public double getSampadaTotalDivertedEducationalArea() {
		return sampadaTotalDivertedEducationalArea;
	}
	public void setSampadaTotalDivertedEducationalArea(
			double sampadaTotalDivertedEducationalArea) {
		this.sampadaTotalDivertedEducationalArea = sampadaTotalDivertedEducationalArea;
	}
	public double getSampadaTotalDivertedHealthArea() {
		return sampadaTotalDivertedHealthArea;
	}
	public void setSampadaTotalDivertedHealthArea(
			double sampadaTotalDivertedHealthArea) {
		this.sampadaTotalDivertedHealthArea = sampadaTotalDivertedHealthArea;
	}
	public double getSampadaTotalDivertedOthersArea() {
		return sampadaTotalDivertedOthersArea;
	}
	public void setSampadaTotalDivertedOthersArea(
			double sampadaTotalDivertedOthersArea) {
		this.sampadaTotalDivertedOthersArea = sampadaTotalDivertedOthersArea;
	}
	public void setLgdcode(String lgdcode) {
		this.lgdcode = lgdcode;
	}
	public String getLgdcode() {
		return lgdcode;
	}
	//added by saurav for new Web Service of CLR
	private ArrayList<CropDetailDTO> cropList;
	private ArrayList<MortgageRemark> mortgageRemarkList;
	private ArrayList<SourceOfIrrigateDTO> sourceOfIrrigationList;
	private ArrayList<TreeNewDTO> treeList;
	private ArrayList<RmkList> remarkList;
	private String landUseType;
	private String landRevenue;
	private String landParcelId;
	private String khasraId;
	
	
	
	private ArrayList<OwnerDetlsDTO> ownerDTO;
	private ArrayList<ClrKhasraDetailsDTO> clrDTO;
	
	private ClrKhasraDetailsDTO clrListDto;
	
	private String clrChangedFlag ;
	
	
	public String getClrChangedFlag() {
		return clrChangedFlag;
	}
	public void setClrChangedFlag(String clrChangedFlag) {
		this.clrChangedFlag = clrChangedFlag;
	}
	public ArrayList<OwnerDetlsDTO> getOwnerDTO() {
		return ownerDTO;
	}
	public void setOwnerDTO(ArrayList<OwnerDetlsDTO> ownerDTO) {
		this.ownerDTO = ownerDTO;
	}
	public ArrayList<ClrKhasraDetailsDTO> getClrDTO() {
		return clrDTO;
	}
	public void setClrDTO(ArrayList<ClrKhasraDetailsDTO> clrDTO) {
		this.clrDTO = clrDTO;
	}
	public ArrayList<CropDetailDTO> getCropList() {
		return cropList;
	}
	public void setCropList(ArrayList<CropDetailDTO> cropList) {
		this.cropList = cropList;
	}
	public ArrayList<MortgageRemark> getMortgageRemarkList() {
		return mortgageRemarkList;
	}
	public void setMortgageRemarkList(ArrayList<MortgageRemark> mortgageRemarkList) {
		this.mortgageRemarkList = mortgageRemarkList;
	}
	public ArrayList<SourceOfIrrigateDTO> getSourceOfIrrigationList() {
		return sourceOfIrrigationList;
	}
	public void setSourceOfIrrigationList(ArrayList<SourceOfIrrigateDTO> sourceOfIrrigationList) {
		this.sourceOfIrrigationList = sourceOfIrrigationList;
	}
	public ArrayList<TreeNewDTO> getTreeList() {
		return treeList;
	}
	public void setTreeList(ArrayList<TreeNewDTO> treeList) {
		this.treeList = treeList;
	}
	public ArrayList<RmkList> getRemarkList() {
		return remarkList;
	}
	public void setRemarkList(ArrayList<RmkList> remarkList) {
		this.remarkList = remarkList;
	}
	public String getLandUseType() {
		return landUseType;
	}
	public void setLandUseType(String landUseType) {
		this.landUseType = landUseType;
	}
	public String getLandRevenue() {
		return landRevenue;
	}
	public void setLandRevenue(String landRevenue) {
		this.landRevenue = landRevenue;
	}
	public String getLandParcelId() {
		return landParcelId;
	}
	public void setLandParcelId(String landParcelId) {
		this.landParcelId = landParcelId;
	}
	public String getKhasraId() {
		return khasraId;
	}
	public void setKhasraId(String khasraId) {
		this.khasraId = khasraId;
	}
	public void setClrListDto(ClrKhasraDetailsDTO clrListDto) {
		this.clrListDto = clrListDto;
	}
	public ClrKhasraDetailsDTO getClrListDto() {
		return clrListDto;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
}
