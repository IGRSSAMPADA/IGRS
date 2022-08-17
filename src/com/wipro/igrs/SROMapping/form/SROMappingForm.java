package com.wipro.igrs.SROMapping.form;

import java.util.ArrayList;
import com.wipro.igrs.SROMapping.dto.SROMappingDTO;

public class SROMappingForm extends org.apache.struts.action.ActionForm {
	private String actionName;
	private String getList;
	private String saveType;
	private String pageType;
	private String linkClickId;
	private String valueCheckBox ;
	private String valueCheckBoxWard;
	private String valueCheckBoxOfc;
	private ArrayList districtList = new ArrayList();
	private ArrayList tehsilList = new ArrayList();
	private ArrayList WardorPatwariList = new ArrayList();
	private ArrayList areaList = new ArrayList();
	private ArrayList subareaList = new ArrayList();
	private ArrayList wardList = new ArrayList();
	private ArrayList sroList = new ArrayList();
	private ArrayList wardListED = new ArrayList();
	
	
	
	
	
	
	public ArrayList getWardListED() {
		return wardListED;
	}
	public void setWardListED(ArrayList wardListED) {
		this.wardListED = wardListED;
	}
	private SROMappingDTO appdto= new SROMappingDTO();
	
	public String getValueCheckBoxWard() {
		return valueCheckBoxWard;
	}
	public void setValueCheckBoxWard(String valueCheckBoxWard) {
		this.valueCheckBoxWard = valueCheckBoxWard;
	}
	public String getValueCheckBoxOfc() {
		return valueCheckBoxOfc;
	}
	public void setValueCheckBoxOfc(String valueCheckBoxOfc) {
		this.valueCheckBoxOfc = valueCheckBoxOfc;
	}
	
	public ArrayList getWardorPatwariList() {
		return WardorPatwariList;
	}
	public void setWardorPatwariList(ArrayList wardorPatwariList) {
		WardorPatwariList = wardorPatwariList;
	}
	
	public ArrayList getSroList() {
		return sroList;
	}
	public void setSroList(ArrayList sroList) {
		this.sroList = sroList;
	}
	
	public String getValueCheckBox() {
		return valueCheckBox;
	}
	public void setValueCheckBox(String valueCheckBox) {
		this.valueCheckBox = valueCheckBox;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getGetList() {
		return getList;
	}
	public void setGetList(String getList) {
		this.getList = getList;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getLinkClickId() {
		return linkClickId;
	}
	public void setLinkClickId(String linkClickId) {
		this.linkClickId = linkClickId;
	}
	public SROMappingDTO getAppdto() {
		return appdto;
	}
	public void setAppdto(SROMappingDTO appdto) {
		this.appdto = appdto;
	}
	private ArrayList stateList = new ArrayList();
	/**
	 * @return the stateList
	 */
	public ArrayList getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(ArrayList stateList) {
		this.stateList = stateList;
	}
	/**
	 * @return the districtList
	 */
	public ArrayList getDistrictList() {
		return districtList;
	}
	/**
	 * @param districtList the districtList to set
	 */
	public void setDistrictList(ArrayList districtList) {
		this.districtList = districtList;
	}
	/**
	 * @return the tehsilList
	 */
	public ArrayList getTehsilList() {
		return tehsilList;
	}
	/**
	 * @param tehsilList the tehsilList to set
	 */
	public void setTehsilList(ArrayList tehsilList) {
		this.tehsilList = tehsilList;
	}
	/**
	 * @return the areaList
	 */
	public ArrayList getAreaList() {
		return areaList;
	}
	/**
	 * @param areaList the areaList to set
	 */
	public void setAreaList(ArrayList areaList) {
		this.areaList = areaList;
	}
	/**
	 * @return the subareaList
	 */
	public ArrayList getSubareaList() {
		return subareaList;
	}
	/**
	 * @param subareaList the subareaList to set
	 */
	public void setSubareaList(ArrayList subareaList) {
		this.subareaList = subareaList;
	}
	/**
	 * @return the wardList
	 */
	public ArrayList getWardList() {
		return wardList;
	}
	/**
	 * @param wardList the wardList to set
	 */
	public void setWardList(ArrayList wardList) {
		this.wardList = wardList;
	}
	
	
}
