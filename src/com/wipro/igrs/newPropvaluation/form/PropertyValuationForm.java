package com.wipro.igrs.newPropvaluation.form;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts.action.ActionForm;

import com.wipro.igrs.newPropvaluation.dto.ClrKhasraDetailsDTO;
import com.wipro.igrs.newPropvaluation.dto.CropDetailDTO;
import com.wipro.igrs.newPropvaluation.dto.KhasraClrDTO;
import com.wipro.igrs.newPropvaluation.dto.KhasraRemarkDTO;
import com.wipro.igrs.newPropvaluation.dto.LandParcelDetailDTO;
import com.wipro.igrs.newPropvaluation.dto.MortgageRemark;
import com.wipro.igrs.newPropvaluation.dto.OwnerDetlsDTO;
import com.wipro.igrs.newPropvaluation.dto.PropertyValuationDTO;
import com.wipro.igrs.newPropvaluation.dto.RemarkList;
import com.wipro.igrs.newPropvaluation.dto.RmkList;
import com.wipro.igrs.newPropvaluation.dto.SourceOfIrrigateDTO;
import com.wipro.igrs.newPropvaluation.dto.TreeDTO;
import com.wipro.igrs.newPropvaluation.dto.TreeNewDTO;
import com.wipro.igrs.newdutycalculation.dto.InstrumentsDTO;
import com.wipro.igrs.newreginit.dto.RegCompletDTO;
import com.wipro.igrs.newreginit.form.RegCommonForm;
import com.wipro.igrs.newPropvaluation.dto.SampadaKhasraClrDTO;;

public class PropertyValuationForm extends ActionForm{

	public PropertyValuationDTO propDTO = new PropertyValuationDTO();
	private KhasraClrDTO clrDto = new KhasraClrDTO();
	private ArrayList<KhasraClrDTO> clrKhasraList = new ArrayList<KhasraClrDTO>();
	private SampadaKhasraClrDTO smapadaClrDTO=new SampadaKhasraClrDTO();
	private ArrayList<SampadaKhasraClrDTO> sampadaClrKhasraList = new ArrayList<SampadaKhasraClrDTO>();
	
	private ArrayList<PropertyValuationDTO> kharaArray=new ArrayList<PropertyValuationDTO>();
	
	private ArrayList<ClrKhasraDetailsDTO> clrArray;
	
	private ArrayList<OwnerDetlsDTO> ownerArray;
	public ArrayList<KhasraClrDTO> getClrKhasraList() {
		return clrKhasraList;
	}

	public void setClrKhasraList(ArrayList<KhasraClrDTO> clrKhasraList) {
		this.clrKhasraList = clrKhasraList;
	}

	private String actionName;
	private String plotEduTcp;
	private String plotHealthTcp;
	 
	private RegCommonForm regInitForm;
	private String regMultiProp;
	
	private String transaction;

	
	

	public String getRegMultiProp() {
		return regMultiProp;
	}

	public void setRegMultiProp(String regMultiProp) {
		this.regMultiProp = regMultiProp;
	}

	public RegCommonForm getRegInitForm() {
		return regInitForm;
	}

	public void setRegInitForm(RegCommonForm regInitForm) {
		this.regInitForm = regInitForm;
	}

	public String getPlotEduTcp() {
		return plotEduTcp;
	}

	public void setPlotEduTcp(String plotEduTcp) {
		this.plotEduTcp = plotEduTcp;
	}

	public String getPlotHealthTcp() {
		return plotHealthTcp;
	}

	public void setPlotHealthTcp(String plotHealthTcp) {
		this.plotHealthTcp = plotHealthTcp;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public PropertyValuationDTO getPropDTO() {
		return propDTO;
	}

	public void setPropDTO(PropertyValuationDTO propDTO) {
		this.propDTO = propDTO;
	}

	
	public KhasraClrDTO getClrDto() {
		return clrDto;
	}

	public void setClrDto(KhasraClrDTO clrDto) {
		this.clrDto = clrDto;
	}

	private ArrayList<PropertyValuationDTO> agriAddBuyerList=new ArrayList<PropertyValuationDTO>();
	private ArrayList<TreeDTO> agriTreeList=new ArrayList<TreeDTO>();
	private HashMap<ArrayList,ArrayList> agriPropertyDiffPustikaList=new HashMap<ArrayList,ArrayList>();

	public ArrayList<PropertyValuationDTO> getAgriAddBuyerList() {
		return agriAddBuyerList;
	}

	public void setAgriAddBuyerList(ArrayList<PropertyValuationDTO> agriAddBuyerList) {
		this.agriAddBuyerList = agriAddBuyerList;
	}

	public ArrayList<TreeDTO> getAgriTreeList() {
		return agriTreeList;
	}

	public void setAgriTreeList(ArrayList<TreeDTO> agriTreeList) {
		this.agriTreeList = agriTreeList;
	}

	public HashMap<ArrayList, ArrayList> getAgriPropertyDiffPustikaList() {
		return agriPropertyDiffPustikaList;
	}

	public void setAgriPropertyDiffPustikaList(
			HashMap<ArrayList, ArrayList> agriPropertyDiffPustikaList) {
		this.agriPropertyDiffPustikaList = agriPropertyDiffPustikaList;
	}
	
	
	//CLR work
	
	
	private String clrData;
	private String actionNameSetValue;
	
	private String searchDisplay;
	public void setClrData(String clrData) {
		this.clrData = clrData;
	}

	public String getClrData() {
		return clrData;
	}

	public void setSearchDisplay(String searchDisplay) {
		this.searchDisplay = searchDisplay;
	}

	public String getSearchDisplay() {
		return searchDisplay;
	}
	public String sampadaArea;


	public String getSampadaArea() {
		return sampadaArea;
	}
	public void setSampadaArea(String sampadaArea) {
		this.sampadaArea = sampadaArea;
	}

	public void setSampadaClrKhasraList(ArrayList<SampadaKhasraClrDTO> sampadaClrKhasraList) {
		this.sampadaClrKhasraList = sampadaClrKhasraList;
	}

	public ArrayList<SampadaKhasraClrDTO> getSampadaClrKhasraList() {
		return sampadaClrKhasraList;
	}

	public void setSmapadaClrDTO(SampadaKhasraClrDTO smapadaClrDTO) {
		this.smapadaClrDTO = smapadaClrDTO;
	}

	public SampadaKhasraClrDTO getSmapadaClrDTO() {
		return smapadaClrDTO;
	}

	public void setActionNameSetValue(String actionNameSetValue) {
		this.actionNameSetValue = actionNameSetValue;
	}

	public String getActionNameSetValue() {
		return actionNameSetValue;
	}
	
	public void setUniKhasraArray(ArrayList<String> uniKhasraArray) {
		this.uniKhasraArray = uniKhasraArray;
	}

	public ArrayList<String> getUniKhasraArray() {
		return uniKhasraArray;
	}

	public void setUniRinArrayClr(ArrayList<String> uniRinArrayClr) {
		this.uniRinArrayClr = uniRinArrayClr;
	}

	public ArrayList<String> getUniRinArrayClr() {
		return uniRinArrayClr;
	}

	public void setKharaArray(ArrayList<PropertyValuationDTO> kharaArray) {
		this.kharaArray = kharaArray;
	}

	public ArrayList<PropertyValuationDTO> getKharaArray() {
		return kharaArray;
	}

	private ArrayList<String> uniKhasraArray=new ArrayList<String>();
	
	private ArrayList<String> uniRinArrayClr=new ArrayList<String>();
	
	private LandParcelDetailDTO landParcelDto;
	private String khasraId;
	private ArrayList<MortgageRemark> mortgaeRemarkList;
	private ArrayList<CropDetailDTO> cropDetailList;
	private ArrayList<KhasraRemarkDTO> khasraRemarkList;
	private ArrayList<SourceOfIrrigateDTO> sourceOfIrrList;
	private ArrayList<TreeNewDTO> treeDtoList;
	private ArrayList<RmkList> remarkList;
	private ArrayList<PropertyValuationDTO> pvdto;
	
	public LandParcelDetailDTO getLandParcelDto() {
		return landParcelDto;
	}

	public void setLandParcelDto(LandParcelDetailDTO landParcelDto) {
		this.landParcelDto = landParcelDto;
	}

	public String getKhasraId() {
		return khasraId;
	}

	public void setKhasraId(String khasraId) {
		this.khasraId = khasraId;
	}

	public ArrayList<MortgageRemark> getMortgaeRemarkList() {
		return mortgaeRemarkList;
	}

	public void setMortgaeRemarkList(ArrayList<MortgageRemark> mortgaeRemarkList) {
		this.mortgaeRemarkList = mortgaeRemarkList;
	}

	public ArrayList<CropDetailDTO> getCropDetailList() {
		return cropDetailList;
	}

	public void setCropDetailList(ArrayList<CropDetailDTO> cropDetailList) {
		this.cropDetailList = cropDetailList;
	}

	public ArrayList<KhasraRemarkDTO> getKhasraRemarkList() {
		return khasraRemarkList;
	}

	public void setKhasraRemarkList(ArrayList<KhasraRemarkDTO> khasraRemarkList) {
		this.khasraRemarkList = khasraRemarkList;
	}

	public ArrayList<SourceOfIrrigateDTO> getSourceOfIrrList() {
		return sourceOfIrrList;
	}

	public void setSourceOfIrrList(ArrayList<SourceOfIrrigateDTO> sourceOfIrrList) {
		this.sourceOfIrrList = sourceOfIrrList;
	}

	public ArrayList<TreeNewDTO> getTreeDtoList() {
		return treeDtoList;
	}

	public void setTreeDtoList(ArrayList<TreeNewDTO> treeDtoList) {
		this.treeDtoList = treeDtoList;
	}

	public ArrayList<RmkList> getRemarkList() {
		return remarkList;
	}

	public void setRemarkList(ArrayList<RmkList> remarkList) {
		this.remarkList = remarkList;
	}

	public ArrayList<PropertyValuationDTO> getPvdto() {
		return pvdto;
	}

	public void setPvdto(ArrayList<PropertyValuationDTO> pvdto) {
		this.pvdto = pvdto;
	}

	public void setClrArray(ArrayList<ClrKhasraDetailsDTO> clrArray) {
		this.clrArray = clrArray;
	}

	public ArrayList<ClrKhasraDetailsDTO> getClrArray() {
		return clrArray;
	}

	public void setOwnerArray(ArrayList<OwnerDetlsDTO> ownerArray) {
		this.ownerArray = ownerArray;
	}

	public ArrayList<OwnerDetlsDTO> getOwnerArray() {
		return ownerArray;
	}

	private ClrKhasraDetailsDTO clrDBLinkDto;
	public ClrKhasraDetailsDTO getClrDBLinkDto() {
		return clrDBLinkDto;
	}

	public void setClrDBLinkDto(ClrKhasraDetailsDTO clrDBLinkDto) {
		this.clrDBLinkDto = clrDBLinkDto;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	public String getTransaction() {
		return transaction;
	}
	
	/*//development agreement
	private InstrumentsDTO instDTO;
	public InstrumentsDTO getInstDTO() {
		return instDTO;
	}

	public void setInstDTO(InstrumentsDTO instDTO) {
		this.instDTO = instDTO;
	}*/
	
}
